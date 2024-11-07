/**
 * *************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General private License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General private License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General private License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.anki.analytics.UsageAnalytics;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.async.CancelListener;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.ProgressSender;
import com.ichi2.async.TaskManager;
import com.ichi2.libanki.exception.NoSuchDeckException;
import com.ichi2.libanki.exception.UnknownDatabaseVersionException;
import com.ichi2.libanki.hooks.ChessFilter;
import com.ichi2.libanki.sched.AbstractSched;
import com.ichi2.libanki.sched.Sched;
import com.ichi2.libanki.sched.SchedV2;
import com.ichi2.libanki.template.ParsedNode;
import com.ichi2.libanki.template.TemplateError;
import com.ichi2.libanki.utils.Time;
import com.ichi2.upgrade.Upgrade;
import com.ichi2.utils.DatabaseChangeDecorator;
import com.ichi2.utils.FunctionalInterfaces;
import com.ichi2.utils.LanguageUtil;
import com.ichi2.utils.VersionUtils;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Set;
import java.util.regex.Pattern;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import timber.log.Timber;
import static com.ichi2.async.CancelListener.isCancelled;
import static com.ichi2.libanki.Collection.DismissType.REVIEW;
import static com.ichi2.libanki.Consts.DECK_DYN;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.ExcessiveClassLength", "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.AvoidBranchingStatementAsLastInLoop", "PMD.SwitchStmtsShouldHaveDefault", "PMD.CollapsibleIfStatements", "PMD.EmptyIfStmt", "PMD.ExcessiveMethodLength" })
public class Collection {

    private final Context mContext;

    private DB mDb;

    private boolean mServer;

    // private double mLastSave;
    private final Media mMedia;

    private final Decks mDecks;

    private Models mModels;

    private final Tags mTags;

    private AbstractSched mSched;

    private long mStartTime;

    private int mStartReps;

    // BEGIN: SQL table columns
    private long mCrt;

    private long mMod;

    private long mScm;

    private boolean mDty;

    private int mUsn;

    private long mLs;

    private JSONObject mConf;

    // API 21: Use a ConcurrentLinkedDeque
    private LinkedBlockingDeque<Undoable> mUndo;

    private final String mPath;

    private boolean mDebugLog;

    private PrintWriter mLogHnd;

    private static final Pattern fClozePatternQ = Pattern.compile("\\{\\{(?!type:)(.*?)cloze:");

    private static final Pattern fClozePatternA = Pattern.compile("\\{\\{(.*?)cloze:");

    private static final Pattern fClozeTagStart = Pattern.compile("<%cloze:");

    private static final int fDefaultSchedulerVersion = 1;

    private static final List<Integer> fSupportedSchedulerVersions = Arrays.asList(1, 2);

    // Not in libAnki.
    private final Time mTime;

    // other options
    public static final String defaultConf = "{" + // review options
    "'activeDecks': [1], " + "'curDeck': 1, " + "'newSpread': " + Consts.NEW_CARDS_DISTRIBUTE + ", " + "'collapseTime': 1200, " + "'timeLim': 0, " + "'estTimes': True, " + "'dueCounts': True, " + // other config
    "'curModel': null, " + "'nextPos': 1, " + "'sortType': \"noteFld\", " + // add new to currently selected deck?
    "'sortBackwards': False, 'addToCur': True }";

    public enum DismissType {

        REVIEW(R.string.undo_action_review),
        BURY_CARD(R.string.menu_bury_card),
        BURY_NOTE(R.string.menu_bury_note),
        SUSPEND_CARD(R.string.menu_suspend_card),
        SUSPEND_CARD_MULTI(R.string.menu_suspend_card),
        UNSUSPEND_CARD_MULTI(R.string.card_browser_unsuspend_card),
        SUSPEND_NOTE(R.string.menu_suspend_note),
        DELETE_NOTE(R.string.menu_delete_note),
        DELETE_NOTE_MULTI(R.string.card_browser_delete_card),
        CHANGE_DECK_MULTI(R.string.undo_action_change_deck_multi),
        MARK_NOTE_MULTI(R.string.card_browser_mark_card),
        UNMARK_NOTE_MULTI(R.string.card_browser_unmark_card),
        FLAG(R.string.menu_flag),
        REPOSITION_CARDS(R.string.card_editor_reposition_card),
        RESCHEDULE_CARDS(R.string.card_editor_reschedule_card),
        RESET_CARDS(R.string.card_editor_reset_card);

        private final int mUndoNameId;

        DismissType(int undoNameId) {
            this.mUndoNameId = undoNameId;
        }

        private Locale getLocale(Resources resources) {
            return LanguageUtil.getLocaleCompat(resources);
        }

        public String getString(Resources res) {
            return res.getString(mUndoNameId).toLowerCase(getLocale(res));
        }
    }

    private static final int UNDO_SIZE_MAX = 20;

    @VisibleForTesting
    public Collection(Context context, DB db, String path, boolean server, boolean log, @NonNull Time time) {
        mContext = context;
        if (!ListenerUtil.mutListener.listen(21201)) {
            mDebugLog = log;
        }
        if (!ListenerUtil.mutListener.listen(21202)) {
            mDb = db;
        }
        mPath = path;
        mTime = time;
        if (!ListenerUtil.mutListener.listen(21203)) {
            _openLog();
        }
        if (!ListenerUtil.mutListener.listen(21204)) {
            log(path, VersionUtils.getPkgVersionName());
        }
        if (!ListenerUtil.mutListener.listen(21205)) {
            mServer = server;
        }
        if (!ListenerUtil.mutListener.listen(21206)) {
            // mLastSave = getTime().now(); // assigned but never accessed - only leaving in for upstream comparison
            clearUndo();
        }
        mMedia = new Media(this, server);
        mDecks = new Decks(this);
        mTags = new Tags(this);
        if (!ListenerUtil.mutListener.listen(21207)) {
            load();
        }
        if (!ListenerUtil.mutListener.listen(21218)) {
            if ((ListenerUtil.mutListener.listen(21212) ? (mCrt >= 0) : (ListenerUtil.mutListener.listen(21211) ? (mCrt <= 0) : (ListenerUtil.mutListener.listen(21210) ? (mCrt > 0) : (ListenerUtil.mutListener.listen(21209) ? (mCrt < 0) : (ListenerUtil.mutListener.listen(21208) ? (mCrt != 0) : (mCrt == 0))))))) {
                if (!ListenerUtil.mutListener.listen(21217)) {
                    mCrt = (ListenerUtil.mutListener.listen(21216) ? (UIUtils.getDayStart(getTime()) % 1000) : (ListenerUtil.mutListener.listen(21215) ? (UIUtils.getDayStart(getTime()) * 1000) : (ListenerUtil.mutListener.listen(21214) ? (UIUtils.getDayStart(getTime()) - 1000) : (ListenerUtil.mutListener.listen(21213) ? (UIUtils.getDayStart(getTime()) + 1000) : (UIUtils.getDayStart(getTime()) / 1000)))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21219)) {
            mStartReps = 0;
        }
        if (!ListenerUtil.mutListener.listen(21220)) {
            mStartTime = 0;
        }
        if (!ListenerUtil.mutListener.listen(21221)) {
            _loadScheduler();
        }
        if (!ListenerUtil.mutListener.listen(21224)) {
            if (!mConf.optBoolean("newBury", false)) {
                if (!ListenerUtil.mutListener.listen(21222)) {
                    mConf.put("newBury", true);
                }
                if (!ListenerUtil.mutListener.listen(21223)) {
                    setMod();
                }
            }
        }
    }

    public String name() {
        // TODO:
        return (new File(mPath)).getName().replace(".anki2", "");
    }

    public int schedVer() {
        int ver = mConf.optInt("schedVer", fDefaultSchedulerVersion);
        if (fSupportedSchedulerVersions.contains(ver)) {
            return ver;
        } else {
            throw new RuntimeException("Unsupported scheduler version");
        }
    }

    // Note: Additional members in the class duplicate this
    private void _loadScheduler() {
        int ver = schedVer();
        if (!ListenerUtil.mutListener.listen(21237)) {
            if ((ListenerUtil.mutListener.listen(21229) ? (ver >= 1) : (ListenerUtil.mutListener.listen(21228) ? (ver <= 1) : (ListenerUtil.mutListener.listen(21227) ? (ver > 1) : (ListenerUtil.mutListener.listen(21226) ? (ver < 1) : (ListenerUtil.mutListener.listen(21225) ? (ver != 1) : (ver == 1))))))) {
                if (!ListenerUtil.mutListener.listen(21236)) {
                    mSched = new Sched(this);
                }
            } else if ((ListenerUtil.mutListener.listen(21234) ? (ver >= 2) : (ListenerUtil.mutListener.listen(21233) ? (ver <= 2) : (ListenerUtil.mutListener.listen(21232) ? (ver > 2) : (ListenerUtil.mutListener.listen(21231) ? (ver < 2) : (ListenerUtil.mutListener.listen(21230) ? (ver != 2) : (ver == 2))))))) {
                if (!ListenerUtil.mutListener.listen(21235)) {
                    mSched = new SchedV2(this);
                }
            }
        }
    }

    public void changeSchedulerVer(Integer ver) throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(21243)) {
            if ((ListenerUtil.mutListener.listen(21242) ? (ver >= schedVer()) : (ListenerUtil.mutListener.listen(21241) ? (ver <= schedVer()) : (ListenerUtil.mutListener.listen(21240) ? (ver > schedVer()) : (ListenerUtil.mutListener.listen(21239) ? (ver < schedVer()) : (ListenerUtil.mutListener.listen(21238) ? (ver != schedVer()) : (ver == schedVer()))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(21244)) {
            if (!fSupportedSchedulerVersions.contains(ver)) {
                throw new RuntimeException("Unsupported scheduler version");
            }
        }
        if (!ListenerUtil.mutListener.listen(21245)) {
            modSchema();
        }
        @SuppressLint("VisibleForTests")
        SchedV2 v2Sched = new SchedV2(this);
        if (!ListenerUtil.mutListener.listen(21246)) {
            clearUndo();
        }
        if (!ListenerUtil.mutListener.listen(21254)) {
            if ((ListenerUtil.mutListener.listen(21251) ? (ver >= 1) : (ListenerUtil.mutListener.listen(21250) ? (ver <= 1) : (ListenerUtil.mutListener.listen(21249) ? (ver > 1) : (ListenerUtil.mutListener.listen(21248) ? (ver < 1) : (ListenerUtil.mutListener.listen(21247) ? (ver != 1) : (ver == 1))))))) {
                if (!ListenerUtil.mutListener.listen(21253)) {
                    v2Sched.moveToV1();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(21252)) {
                    v2Sched.moveToV2();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21255)) {
            mConf.put("schedVer", ver);
        }
        if (!ListenerUtil.mutListener.listen(21256)) {
            setMod();
        }
        if (!ListenerUtil.mutListener.listen(21257)) {
            _loadScheduler();
        }
    }

    public void load() {
        Cursor cursor = null;
        String deckConf = "";
        try {
            if (!ListenerUtil.mutListener.listen(21260)) {
                // Read in deck table columns
                cursor = mDb.query("SELECT crt, mod, scm, dty, usn, ls, " + "conf, dconf, tags FROM col");
            }
            if (!ListenerUtil.mutListener.listen(21261)) {
                if (!cursor.moveToFirst()) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(21262)) {
                mCrt = cursor.getLong(0);
            }
            if (!ListenerUtil.mutListener.listen(21263)) {
                mMod = cursor.getLong(1);
            }
            if (!ListenerUtil.mutListener.listen(21264)) {
                mScm = cursor.getLong(2);
            }
            if (!ListenerUtil.mutListener.listen(21265)) {
                // No longer used
                mDty = cursor.getInt(3) == 1;
            }
            if (!ListenerUtil.mutListener.listen(21266)) {
                mUsn = cursor.getInt(4);
            }
            if (!ListenerUtil.mutListener.listen(21267)) {
                mLs = cursor.getLong(5);
            }
            if (!ListenerUtil.mutListener.listen(21268)) {
                mConf = new JSONObject(cursor.getString(6));
            }
            if (!ListenerUtil.mutListener.listen(21269)) {
                deckConf = cursor.getString(7);
            }
            if (!ListenerUtil.mutListener.listen(21270)) {
                mTags.load(cursor.getString(8));
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(21259)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(21258)) {
                        cursor.close();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21271)) {
            // otherwise they get loaded when required.
            mDecks.load(loadColumn("decks"), deckConf);
        }
    }

    private static int sChunk = 0;

    private int getChunk() {
        if (!ListenerUtil.mutListener.listen(21277)) {
            if ((ListenerUtil.mutListener.listen(21276) ? (sChunk >= 0) : (ListenerUtil.mutListener.listen(21275) ? (sChunk <= 0) : (ListenerUtil.mutListener.listen(21274) ? (sChunk > 0) : (ListenerUtil.mutListener.listen(21273) ? (sChunk < 0) : (ListenerUtil.mutListener.listen(21272) ? (sChunk == 0) : (sChunk != 0))))))) {
                return sChunk;
            }
        }
        // Values are copied here. Ideally, a getter would allow to access it.
        final int WINDOW_SIZE_KB = 2048;
        int sCursorWindowSize = (ListenerUtil.mutListener.listen(21281) ? (WINDOW_SIZE_KB % 1024) : (ListenerUtil.mutListener.listen(21280) ? (WINDOW_SIZE_KB / 1024) : (ListenerUtil.mutListener.listen(21279) ? (WINDOW_SIZE_KB - 1024) : (ListenerUtil.mutListener.listen(21278) ? (WINDOW_SIZE_KB + 1024) : (WINDOW_SIZE_KB * 1024)))));
        // as a ceiling. Try it, with a reasonable fallback in case of failure
        SupportSQLiteDatabase db = mDb.getDatabase();
        if (!ListenerUtil.mutListener.listen(21282)) {
            if (!(db instanceof DatabaseChangeDecorator)) {
                return sChunk;
            }
        }
        String db_name = ((DatabaseChangeDecorator) db).getWrapped().getClass().getName();
        if (!ListenerUtil.mutListener.listen(21294)) {
            if ("io.requery.android.database.sqlite.SQLiteDatabase".equals(db_name)) {
                try {
                    Field cursorWindowSize = io.requery.android.database.CursorWindow.class.getDeclaredField("sDefaultCursorWindowSize");
                    if (!ListenerUtil.mutListener.listen(21284)) {
                        cursorWindowSize.setAccessible(true);
                    }
                    int possibleCursorWindowSize = cursorWindowSize.getInt(null);
                    if (!ListenerUtil.mutListener.listen(21285)) {
                        Timber.d("Reflectively discovered database default cursor window size %d", possibleCursorWindowSize);
                    }
                    if (!ListenerUtil.mutListener.listen(21293)) {
                        if ((ListenerUtil.mutListener.listen(21290) ? (possibleCursorWindowSize >= 0) : (ListenerUtil.mutListener.listen(21289) ? (possibleCursorWindowSize <= 0) : (ListenerUtil.mutListener.listen(21288) ? (possibleCursorWindowSize < 0) : (ListenerUtil.mutListener.listen(21287) ? (possibleCursorWindowSize != 0) : (ListenerUtil.mutListener.listen(21286) ? (possibleCursorWindowSize == 0) : (possibleCursorWindowSize > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(21292)) {
                                sCursorWindowSize = possibleCursorWindowSize;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(21291)) {
                                Timber.w("Obtained unusable cursor window size: %d. Using default %d", possibleCursorWindowSize, sCursorWindowSize);
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(21283)) {
                        Timber.w(e, "Unable to get window size from requery cursor.");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21303)) {
            // reduce the actual size a little bit.
            sChunk = (int) ((ListenerUtil.mutListener.listen(21302) ? ((ListenerUtil.mutListener.listen(21298) ? (sCursorWindowSize % 15.) : (ListenerUtil.mutListener.listen(21297) ? (sCursorWindowSize / 15.) : (ListenerUtil.mutListener.listen(21296) ? (sCursorWindowSize - 15.) : (ListenerUtil.mutListener.listen(21295) ? (sCursorWindowSize + 15.) : (sCursorWindowSize * 15.))))) % 16.) : (ListenerUtil.mutListener.listen(21301) ? ((ListenerUtil.mutListener.listen(21298) ? (sCursorWindowSize % 15.) : (ListenerUtil.mutListener.listen(21297) ? (sCursorWindowSize / 15.) : (ListenerUtil.mutListener.listen(21296) ? (sCursorWindowSize - 15.) : (ListenerUtil.mutListener.listen(21295) ? (sCursorWindowSize + 15.) : (sCursorWindowSize * 15.))))) * 16.) : (ListenerUtil.mutListener.listen(21300) ? ((ListenerUtil.mutListener.listen(21298) ? (sCursorWindowSize % 15.) : (ListenerUtil.mutListener.listen(21297) ? (sCursorWindowSize / 15.) : (ListenerUtil.mutListener.listen(21296) ? (sCursorWindowSize - 15.) : (ListenerUtil.mutListener.listen(21295) ? (sCursorWindowSize + 15.) : (sCursorWindowSize * 15.))))) - 16.) : (ListenerUtil.mutListener.listen(21299) ? ((ListenerUtil.mutListener.listen(21298) ? (sCursorWindowSize % 15.) : (ListenerUtil.mutListener.listen(21297) ? (sCursorWindowSize / 15.) : (ListenerUtil.mutListener.listen(21296) ? (sCursorWindowSize - 15.) : (ListenerUtil.mutListener.listen(21295) ? (sCursorWindowSize + 15.) : (sCursorWindowSize * 15.))))) + 16.) : ((ListenerUtil.mutListener.listen(21298) ? (sCursorWindowSize % 15.) : (ListenerUtil.mutListener.listen(21297) ? (sCursorWindowSize / 15.) : (ListenerUtil.mutListener.listen(21296) ? (sCursorWindowSize - 15.) : (ListenerUtil.mutListener.listen(21295) ? (sCursorWindowSize + 15.) : (sCursorWindowSize * 15.))))) / 16.))))));
        }
        return sChunk;
    }

    public String loadColumn(String columnName) {
        int pos = 1;
        StringBuilder buf = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(21319)) {
            {
                long _loopCounter457 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter457", ++_loopCounter457);
                    try (Cursor cursor = mDb.query("SELECT substr(" + columnName + ", ?, ?) FROM col", Integer.toString(pos), Integer.toString(getChunk()))) {
                        if (!ListenerUtil.mutListener.listen(21304)) {
                            if (!cursor.moveToFirst()) {
                                return buf.toString();
                            }
                        }
                        String res = cursor.getString(0);
                        if (!ListenerUtil.mutListener.listen(21310)) {
                            if ((ListenerUtil.mutListener.listen(21309) ? (res.length() >= 0) : (ListenerUtil.mutListener.listen(21308) ? (res.length() <= 0) : (ListenerUtil.mutListener.listen(21307) ? (res.length() > 0) : (ListenerUtil.mutListener.listen(21306) ? (res.length() < 0) : (ListenerUtil.mutListener.listen(21305) ? (res.length() != 0) : (res.length() == 0))))))) {
                                break;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(21311)) {
                            buf.append(res);
                        }
                        if (!ListenerUtil.mutListener.listen(21317)) {
                            if ((ListenerUtil.mutListener.listen(21316) ? (res.length() >= getChunk()) : (ListenerUtil.mutListener.listen(21315) ? (res.length() <= getChunk()) : (ListenerUtil.mutListener.listen(21314) ? (res.length() > getChunk()) : (ListenerUtil.mutListener.listen(21313) ? (res.length() != getChunk()) : (ListenerUtil.mutListener.listen(21312) ? (res.length() == getChunk()) : (res.length() < getChunk()))))))) {
                                break;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(21318)) {
                            pos += getChunk();
                        }
                    }
                }
            }
        }
        return buf.toString();
    }

    /**
     * Mark DB modified. DB operations and the deck/tag/model managers do this automatically, so this is only necessary
     * if you modify properties of this object or the conf dict.
     */
    public void setMod() {
        if (!ListenerUtil.mutListener.listen(21320)) {
            mDb.setMod(true);
        }
    }

    public void flush() {
        if (!ListenerUtil.mutListener.listen(21321)) {
            flush(0);
        }
    }

    /**
     * Flush state to DB, updating mod time.
     */
    public void flush(long mod) {
        if (!ListenerUtil.mutListener.listen(21322)) {
            Timber.i("flush - Saving information to DB...");
        }
        if (!ListenerUtil.mutListener.listen(21328)) {
            mMod = ((ListenerUtil.mutListener.listen(21327) ? (mod >= 0) : (ListenerUtil.mutListener.listen(21326) ? (mod <= 0) : (ListenerUtil.mutListener.listen(21325) ? (mod > 0) : (ListenerUtil.mutListener.listen(21324) ? (mod < 0) : (ListenerUtil.mutListener.listen(21323) ? (mod != 0) : (mod == 0)))))) ? getTime().intTimeMS() : mod);
        }
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(21329)) {
            values.put("crt", mCrt);
        }
        if (!ListenerUtil.mutListener.listen(21330)) {
            values.put("mod", mMod);
        }
        if (!ListenerUtil.mutListener.listen(21331)) {
            values.put("scm", mScm);
        }
        if (!ListenerUtil.mutListener.listen(21332)) {
            values.put("dty", mDty ? 1 : 0);
        }
        if (!ListenerUtil.mutListener.listen(21333)) {
            values.put("usn", mUsn);
        }
        if (!ListenerUtil.mutListener.listen(21334)) {
            values.put("ls", mLs);
        }
        if (!ListenerUtil.mutListener.listen(21335)) {
            values.put("conf", Utils.jsonToString(mConf));
        }
        if (!ListenerUtil.mutListener.listen(21336)) {
            mDb.update("col", values);
        }
    }

    /**
     * Flush, commit DB, and take out another write lock.
     */
    public synchronized void save() {
        if (!ListenerUtil.mutListener.listen(21337)) {
            save(null, 0);
        }
    }

    public synchronized void save(long mod) {
        if (!ListenerUtil.mutListener.listen(21338)) {
            save(null, mod);
        }
    }

    public synchronized void save(String name) {
        if (!ListenerUtil.mutListener.listen(21339)) {
            save(name, 0);
        }
    }

    public synchronized void save(String name, long mod) {
        if (!ListenerUtil.mutListener.listen(21340)) {
            // let the managers conditionally flush
            getModels().flush();
        }
        if (!ListenerUtil.mutListener.listen(21341)) {
            mDecks.flush();
        }
        if (!ListenerUtil.mutListener.listen(21342)) {
            mTags.flush();
        }
        if (!ListenerUtil.mutListener.listen(21346)) {
            // and flush deck + bump mod if db has been changed
            if (mDb.getMod()) {
                if (!ListenerUtil.mutListener.listen(21343)) {
                    flush(mod);
                }
                if (!ListenerUtil.mutListener.listen(21344)) {
                    mDb.commit();
                }
                if (!ListenerUtil.mutListener.listen(21345)) {
                    mDb.setMod(false);
                }
            }
        }
    }

    /**
     * Disconnect from DB.
     */
    public synchronized void close() {
        if (!ListenerUtil.mutListener.listen(21347)) {
            close(true);
        }
    }

    public synchronized void close(boolean save) {
        if (!ListenerUtil.mutListener.listen(21359)) {
            if (mDb != null) {
                try {
                    SupportSQLiteDatabase db = mDb.getDatabase();
                    if (!ListenerUtil.mutListener.listen(21351)) {
                        if (save) {
                            if (!ListenerUtil.mutListener.listen(21350)) {
                                mDb.executeInTransaction(this::save);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(21349)) {
                                DB.safeEndInTransaction(db);
                            }
                        }
                    }
                } catch (RuntimeException e) {
                    if (!ListenerUtil.mutListener.listen(21348)) {
                        AnkiDroidApp.sendExceptionReport(e, "closeDB");
                    }
                }
                if (!ListenerUtil.mutListener.listen(21353)) {
                    if (!mServer) {
                        if (!ListenerUtil.mutListener.listen(21352)) {
                            mDb.getDatabase().disableWriteAheadLogging();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(21354)) {
                    mDb.close();
                }
                if (!ListenerUtil.mutListener.listen(21355)) {
                    mDb = null;
                }
                if (!ListenerUtil.mutListener.listen(21356)) {
                    mMedia.close();
                }
                if (!ListenerUtil.mutListener.listen(21357)) {
                    _closeLog();
                }
                if (!ListenerUtil.mutListener.listen(21358)) {
                    Timber.i("Collection closed");
                }
            }
        }
    }

    public void reopen() {
        if (!ListenerUtil.mutListener.listen(21360)) {
            Timber.i("Reopening Database");
        }
        if (!ListenerUtil.mutListener.listen(21364)) {
            if (mDb == null) {
                if (!ListenerUtil.mutListener.listen(21361)) {
                    mDb = new DB(mPath);
                }
                if (!ListenerUtil.mutListener.listen(21362)) {
                    mMedia.connect();
                }
                if (!ListenerUtil.mutListener.listen(21363)) {
                    _openLog();
                }
            }
        }
    }

    /**
     * Note: not in libanki.  Mark schema modified to force a full
     * sync, but with the confirmation checking function disabled This
     * is equivalent to `modSchema(False)` in Anki. A distinct method
     * is used so that the type does not states that an exception is
     * thrown when in fact it is never thrown.
     */
    public void modSchemaNoCheck() {
        if (!ListenerUtil.mutListener.listen(21365)) {
            mScm = getTime().intTimeMS();
        }
        if (!ListenerUtil.mutListener.listen(21366)) {
            setMod();
        }
    }

    /**
     * Mark schema modified to force a full sync.
     * ConfirmModSchemaException will be thrown if the user needs to be prompted to confirm the action.
     * If the user chooses to confirm then modSchemaNoCheck should be called, after which the exception can
     * be safely ignored, and the outer code called again.
     *
     * @throws ConfirmModSchemaException
     */
    public void modSchema() throws ConfirmModSchemaException {
        if (!ListenerUtil.mutListener.listen(21367)) {
            if (!schemaChanged()) {
                /* In Android we can't show a dialog which blocks the main UI thread
             Therefore we can't wait for the user to confirm if they want to do
             a full sync here, and we instead throw an exception asking the outer
             code to handle the user's choice */
                throw new ConfirmModSchemaException();
            }
        }
        if (!ListenerUtil.mutListener.listen(21368)) {
            modSchemaNoCheck();
        }
    }

    /**
     * True if schema changed since last sync.
     */
    public boolean schemaChanged() {
        return (ListenerUtil.mutListener.listen(21373) ? (mScm >= mLs) : (ListenerUtil.mutListener.listen(21372) ? (mScm <= mLs) : (ListenerUtil.mutListener.listen(21371) ? (mScm < mLs) : (ListenerUtil.mutListener.listen(21370) ? (mScm != mLs) : (ListenerUtil.mutListener.listen(21369) ? (mScm == mLs) : (mScm > mLs))))));
    }

    public int usn() {
        if (mServer) {
            return mUsn;
        } else {
            return -1;
        }
    }

    /**
     * called before a full upload
     */
    public void beforeUpload() {
        String[] tables = new String[] { "notes", "cards", "revlog" };
        if (!ListenerUtil.mutListener.listen(21375)) {
            {
                long _loopCounter458 = 0;
                for (String t : tables) {
                    ListenerUtil.loopListener.listen("_loopCounter458", ++_loopCounter458);
                    if (!ListenerUtil.mutListener.listen(21374)) {
                        mDb.execute("UPDATE " + t + " SET usn=0 WHERE usn=-1");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21376)) {
            // we can save space by removing the log of deletions
            mDb.execute("delete from graves");
        }
        if (!ListenerUtil.mutListener.listen(21377)) {
            mUsn += 1;
        }
        if (!ListenerUtil.mutListener.listen(21378)) {
            getModels().beforeUpload();
        }
        if (!ListenerUtil.mutListener.listen(21379)) {
            mTags.beforeUpload();
        }
        if (!ListenerUtil.mutListener.listen(21380)) {
            mDecks.beforeUpload();
        }
        if (!ListenerUtil.mutListener.listen(21381)) {
            modSchemaNoCheck();
        }
        if (!ListenerUtil.mutListener.listen(21382)) {
            mLs = mScm;
        }
        if (!ListenerUtil.mutListener.listen(21383)) {
            Timber.i("Compacting database before full upload");
        }
        if (!ListenerUtil.mutListener.listen(21384)) {
            // ensure db is compacted before upload
            mDb.execute("vacuum");
        }
        if (!ListenerUtil.mutListener.listen(21385)) {
            mDb.execute("analyze");
        }
        if (!ListenerUtil.mutListener.listen(21386)) {
            close();
        }
    }

    public Card getCard(long id) {
        return new Card(this, id);
    }

    public Card.Cache getCardCache(long id) {
        return new Card.Cache(this, id);
    }

    public Note getNote(long id) {
        return new Note(this, id);
    }

    public int nextID(String type) {
        if (!ListenerUtil.mutListener.listen(21387)) {
            type = "next" + Character.toUpperCase(type.charAt(0)) + type.substring(1);
        }
        int id;
        try {
            id = mConf.getInt(type);
        } catch (JSONException e) {
            id = 1;
        }
        if (!ListenerUtil.mutListener.listen(21392)) {
            mConf.put(type, (ListenerUtil.mutListener.listen(21391) ? (id % 1) : (ListenerUtil.mutListener.listen(21390) ? (id / 1) : (ListenerUtil.mutListener.listen(21389) ? (id * 1) : (ListenerUtil.mutListener.listen(21388) ? (id - 1) : (id + 1))))));
        }
        return id;
    }

    /**
     * Rebuild the queue and reload data after DB modified.
     */
    public void reset() {
        if (!ListenerUtil.mutListener.listen(21393)) {
            mSched.deferReset();
        }
    }

    public void _logRem(long[] ids, int type) {
        if (!ListenerUtil.mutListener.listen(21398)) {
            {
                long _loopCounter459 = 0;
                for (long id : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter459", ++_loopCounter459);
                    ContentValues values = new ContentValues();
                    if (!ListenerUtil.mutListener.listen(21394)) {
                        values.put("usn", usn());
                    }
                    if (!ListenerUtil.mutListener.listen(21395)) {
                        values.put("oid", id);
                    }
                    if (!ListenerUtil.mutListener.listen(21396)) {
                        values.put("type", type);
                    }
                    if (!ListenerUtil.mutListener.listen(21397)) {
                        mDb.insert("graves", values);
                    }
                }
            }
        }
    }

    public void _logRem(java.util.Collection<Long> ids, @Consts.REM_TYPE int type) {
        if (!ListenerUtil.mutListener.listen(21403)) {
            {
                long _loopCounter460 = 0;
                for (long id : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter460", ++_loopCounter460);
                    ContentValues values = new ContentValues();
                    if (!ListenerUtil.mutListener.listen(21399)) {
                        values.put("usn", usn());
                    }
                    if (!ListenerUtil.mutListener.listen(21400)) {
                        values.put("oid", id);
                    }
                    if (!ListenerUtil.mutListener.listen(21401)) {
                        values.put("type", type);
                    }
                    if (!ListenerUtil.mutListener.listen(21402)) {
                        mDb.insert("graves", values);
                    }
                }
            }
        }
    }

    public int noteCount() {
        return mDb.queryScalar("SELECT count() FROM notes");
    }

    /**
     * Return a new note with the default model from the deck
     * @return The new note
     */
    public Note newNote() {
        return newNote(true);
    }

    /**
     * Return a new note with the model derived from the deck or the configuration
     * @param forDeck When true it uses the model specified in the deck (mid), otherwise it uses the model specified in
     *                the configuration (curModel)
     * @return The new note
     */
    public Note newNote(boolean forDeck) {
        return newNote(getModels().current(forDeck));
    }

    /**
     * Return a new note with a specific model
     * @param m The model to use for the new note
     * @return The new note
     */
    public Note newNote(Model m) {
        return new Note(this, m);
    }

    /**
     * Add a note to the collection. Return number of new cards.
     */
    public int addNote(Note note) {
        // check we have card models available, then save
        ArrayList<JSONObject> cms = findTemplates(note);
        if (!ListenerUtil.mutListener.listen(21409)) {
            // Todo: upstream, we accept to add a not even if it generates no card. Should be ported to ankidroid
            if ((ListenerUtil.mutListener.listen(21408) ? (cms.size() >= 0) : (ListenerUtil.mutListener.listen(21407) ? (cms.size() <= 0) : (ListenerUtil.mutListener.listen(21406) ? (cms.size() > 0) : (ListenerUtil.mutListener.listen(21405) ? (cms.size() < 0) : (ListenerUtil.mutListener.listen(21404) ? (cms.size() != 0) : (cms.size() == 0))))))) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(21410)) {
            note.flush();
        }
        // deck conf governs which of these are used
        int due = nextID("pos");
        // add cards
        int ncards = 0;
        if (!ListenerUtil.mutListener.listen(21413)) {
            {
                long _loopCounter461 = 0;
                for (JSONObject template : cms) {
                    ListenerUtil.loopListener.listen("_loopCounter461", ++_loopCounter461);
                    if (!ListenerUtil.mutListener.listen(21411)) {
                        _newCard(note, template, due);
                    }
                    if (!ListenerUtil.mutListener.listen(21412)) {
                        ncards += 1;
                    }
                }
            }
        }
        return ncards;
    }

    public void remNotes(long[] ids) {
        ArrayList<Long> list = mDb.queryLongList("SELECT id FROM cards WHERE nid IN " + Utils.ids2str(ids));
        if (!ListenerUtil.mutListener.listen(21414)) {
            remCards(list);
        }
    }

    /**
     * Bulk delete notes by ID. Don't call this directly.
     */
    public void _remNotes(java.util.Collection<Long> ids) {
        if (!ListenerUtil.mutListener.listen(21420)) {
            if ((ListenerUtil.mutListener.listen(21419) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21418) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21417) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(21416) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21415) ? (ids.size() != 0) : (ids.size() == 0))))))) {
                return;
            }
        }
        String strids = Utils.ids2str(ids);
        if (!ListenerUtil.mutListener.listen(21421)) {
            // more card templates
            _logRem(ids, Consts.REM_NOTE);
        }
        if (!ListenerUtil.mutListener.listen(21422)) {
            mDb.execute("DELETE FROM notes WHERE id IN " + strids);
        }
    }

    /**
     * @param note A note
     * @return (active), non-empty templates.
     */
    public ArrayList<JSONObject> findTemplates(Note note) {
        Model model = note.model();
        ArrayList<Integer> avail = Models.availOrds(model, note.getFields());
        return _tmplsFromOrds(model, avail);
    }

    /**
     * @param model A note type
     * @param avail Ords of cards from this note type.
     * @return One template by element i of avail, for the i-th card. For standard template, avail should contains only existing ords.
     * for cloze, avail should contains only non-negative numbers, and the i-th card is a copy of the first card, with a different ord.
     */
    private ArrayList<JSONObject> _tmplsFromOrds(Model model, ArrayList<Integer> avail) {
        JSONArray tmpls;
        if (model.isStd()) {
            tmpls = model.getJSONArray("tmpls");
            ArrayList<JSONObject> ok = new ArrayList<>(avail.size());
            if (!ListenerUtil.mutListener.listen(21427)) {
                {
                    long _loopCounter463 = 0;
                    for (Integer ord : avail) {
                        ListenerUtil.loopListener.listen("_loopCounter463", ++_loopCounter463);
                        if (!ListenerUtil.mutListener.listen(21426)) {
                            ok.add(tmpls.getJSONObject(ord));
                        }
                    }
                }
            }
            return ok;
        } else {
            // cloze - generate temporary templates from first
            JSONObject template0 = model.getJSONArray("tmpls").getJSONObject(0);
            ArrayList<JSONObject> ok = new ArrayList<>(avail.size());
            if (!ListenerUtil.mutListener.listen(21425)) {
                {
                    long _loopCounter462 = 0;
                    for (int ord : avail) {
                        ListenerUtil.loopListener.listen("_loopCounter462", ++_loopCounter462);
                        JSONObject t = template0.deepClone();
                        if (!ListenerUtil.mutListener.listen(21423)) {
                            t.put("ord", ord);
                        }
                        if (!ListenerUtil.mutListener.listen(21424)) {
                            ok.add(t);
                        }
                    }
                }
            }
            return ok;
        }
    }

    /**
     * Generate cards for non-empty templates, return ids to remove.
     */
    public ArrayList<Long> genCards(java.util.Collection<Long> nids, @NonNull Model model) {
        return genCards(Utils.collection2Array(nids), model);
    }

    public <T extends ProgressSender<Integer> & CancelListener> ArrayList<Long> genCards(java.util.Collection<Long> nids, @NonNull Model model, @Nullable T task) {
        return genCards(Utils.collection2Array(nids), model, task);
    }

    public ArrayList<Long> genCards(java.util.Collection<Long> nids, long mid) {
        return genCards(nids, getModels().get(mid));
    }

    public ArrayList<Long> genCards(long[] nids, @NonNull Model model) {
        return genCards(nids, model, null);
    }

    public ArrayList<Long> genCards(long nid, @NonNull Model model) {
        return genCards(nid, model, null);
    }

    public <T extends ProgressSender<Integer> & CancelListener> ArrayList<Long> genCards(long nid, @NonNull Model model, @Nullable T task) {
        return genCards("(" + nid + ")", model, task);
    }

    /**
     * @param nids All ids of nodes of a note type
     * @param task Task to check for cancellation and update number of card processed
     * @return Cards that should be removed because they should not be generated
     */
    public <T extends ProgressSender<Integer> & CancelListener> ArrayList<Long> genCards(long[] nids, @NonNull Model model, @Nullable T task) {
        // build map of (nid,ord) so we don't create dupes
        String snids = Utils.ids2str(nids);
        return genCards(snids, model, task);
    }

    /**
     * @param snids All ids of nodes of a note type, separated by comma
     * @param model
     * @param task Task to check for cancellation and update number of card processed
     * @return Cards that should be removed because they should not be generated
     * @param <T>
     */
    public <T extends ProgressSender<Integer> & CancelListener> ArrayList<Long> genCards(String snids, @NonNull Model model, @Nullable T task) {
        int nbCount = noteCount();
        // For each note, indicates ords of cards it contains
        HashMap<Long, HashMap<Integer, Long>> have = new HashMap<>(nbCount);
        // For each note, the deck containing all of its cards, or 0 if siblings in multiple deck
        HashMap<Long, Long> dids = new HashMap<>(nbCount);
        // For each note, an arbitrary due of one of its due card processed, if any exists
        HashMap<Long, Long> dues = new HashMap<>(nbCount);
        List<ParsedNode> nodes = null;
        if (!ListenerUtil.mutListener.listen(21429)) {
            if (model.getInt("type") != Consts.MODEL_CLOZE) {
                if (!ListenerUtil.mutListener.listen(21428)) {
                    nodes = model.parsedNodes();
                }
            }
        }
        try (Cursor cur = mDb.query("select id, nid, ord, (CASE WHEN odid != 0 THEN odid ELSE did END), (CASE WHEN odid != 0 THEN odue ELSE due END), type from cards where nid in " + snids)) {
            if (!ListenerUtil.mutListener.listen(21453)) {
                {
                    long _loopCounter464 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter464", ++_loopCounter464);
                        if (!ListenerUtil.mutListener.listen(21431)) {
                            if (isCancelled(task)) {
                                if (!ListenerUtil.mutListener.listen(21430)) {
                                    Timber.v("Empty card cancelled");
                                }
                                return null;
                            }
                        }
                        @NonNull
                        Long id = cur.getLong(0);
                        @NonNull
                        Long nid = cur.getLong(1);
                        @NonNull
                        Integer ord = cur.getInt(2);
                        @NonNull
                        Long did = cur.getLong(3);
                        @NonNull
                        Long due = cur.getLong(4);
                        @Consts.CARD_TYPE
                        int type = cur.getInt(5);
                        if (!ListenerUtil.mutListener.listen(21433)) {
                            // existing cards
                            if (!have.containsKey(nid)) {
                                if (!ListenerUtil.mutListener.listen(21432)) {
                                    have.put(nid, new HashMap<>());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(21434)) {
                            have.get(nid).put(ord, id);
                        }
                        if (!ListenerUtil.mutListener.listen(21444)) {
                            // and their dids
                            if (dids.containsKey(nid)) {
                                if (!ListenerUtil.mutListener.listen(21443)) {
                                    if ((ListenerUtil.mutListener.listen(21441) ? ((ListenerUtil.mutListener.listen(21440) ? (dids.get(nid) >= 0) : (ListenerUtil.mutListener.listen(21439) ? (dids.get(nid) <= 0) : (ListenerUtil.mutListener.listen(21438) ? (dids.get(nid) > 0) : (ListenerUtil.mutListener.listen(21437) ? (dids.get(nid) < 0) : (ListenerUtil.mutListener.listen(21436) ? (dids.get(nid) == 0) : (dids.get(nid) != 0)))))) || !Utils.equals(dids.get(nid), did)) : ((ListenerUtil.mutListener.listen(21440) ? (dids.get(nid) >= 0) : (ListenerUtil.mutListener.listen(21439) ? (dids.get(nid) <= 0) : (ListenerUtil.mutListener.listen(21438) ? (dids.get(nid) > 0) : (ListenerUtil.mutListener.listen(21437) ? (dids.get(nid) < 0) : (ListenerUtil.mutListener.listen(21436) ? (dids.get(nid) == 0) : (dids.get(nid) != 0)))))) && !Utils.equals(dids.get(nid), did)))) {
                                        if (!ListenerUtil.mutListener.listen(21442)) {
                                            // cards are in two or more different decks; revert to model default
                                            dids.put(nid, 0L);
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(21435)) {
                                    // first card or multiple cards in same deck
                                    dids.put(nid, did);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(21452)) {
                            if ((ListenerUtil.mutListener.listen(21450) ? (!dues.containsKey(nid) || (ListenerUtil.mutListener.listen(21449) ? (type >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21448) ? (type <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21447) ? (type > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21446) ? (type < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21445) ? (type != Consts.CARD_TYPE_NEW) : (type == Consts.CARD_TYPE_NEW))))))) : (!dues.containsKey(nid) && (ListenerUtil.mutListener.listen(21449) ? (type >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21448) ? (type <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21447) ? (type > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21446) ? (type < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21445) ? (type != Consts.CARD_TYPE_NEW) : (type == Consts.CARD_TYPE_NEW))))))))) {
                                if (!ListenerUtil.mutListener.listen(21451)) {
                                    dues.put(nid, due);
                                }
                            }
                        }
                    }
                }
            }
        }
        // build cards for each note
        ArrayList<Object[]> data = new ArrayList<>();
        long ts = getTime().maxID(mDb);
        long now = getTime().intTime();
        ArrayList<Long> rem = new ArrayList<>(mDb.queryScalar("SELECT count() FROM notes where id in " + snids));
        int usn = usn();
        try (Cursor cur = mDb.query("SELECT id, flds FROM notes WHERE id IN " + snids)) {
            if (!ListenerUtil.mutListener.listen(21485)) {
                {
                    long _loopCounter467 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter467", ++_loopCounter467);
                        if (!ListenerUtil.mutListener.listen(21455)) {
                            if (isCancelled(task)) {
                                if (!ListenerUtil.mutListener.listen(21454)) {
                                    Timber.v("Empty card cancelled");
                                }
                                return null;
                            }
                        }
                        @NonNull
                        Long nid = cur.getLong(0);
                        String flds = cur.getString(1);
                        ArrayList<Integer> avail = Models.availOrds(model, Utils.splitFields(flds), nodes);
                        if (!ListenerUtil.mutListener.listen(21457)) {
                            if (task != null) {
                                if (!ListenerUtil.mutListener.listen(21456)) {
                                    task.doProgress(avail.size());
                                }
                            }
                        }
                        Long did = dids.get(nid);
                        // use sibling due if there is one, else use a new id
                        @NonNull
                        Long due;
                        if (dues.containsKey(nid)) {
                            due = dues.get(nid);
                        } else {
                            due = (long) nextID("pos");
                        }
                        if (!ListenerUtil.mutListener.listen(21465)) {
                            if ((ListenerUtil.mutListener.listen(21463) ? (did == null && (ListenerUtil.mutListener.listen(21462) ? (did >= 0L) : (ListenerUtil.mutListener.listen(21461) ? (did <= 0L) : (ListenerUtil.mutListener.listen(21460) ? (did > 0L) : (ListenerUtil.mutListener.listen(21459) ? (did < 0L) : (ListenerUtil.mutListener.listen(21458) ? (did != 0L) : (did == 0L))))))) : (did == null || (ListenerUtil.mutListener.listen(21462) ? (did >= 0L) : (ListenerUtil.mutListener.listen(21461) ? (did <= 0L) : (ListenerUtil.mutListener.listen(21460) ? (did > 0L) : (ListenerUtil.mutListener.listen(21459) ? (did < 0L) : (ListenerUtil.mutListener.listen(21458) ? (did != 0L) : (did == 0L))))))))) {
                                if (!ListenerUtil.mutListener.listen(21464)) {
                                    did = model.getLong("did");
                                }
                            }
                        }
                        // add any missing cards
                        ArrayList<JSONObject> tmpls = _tmplsFromOrds(model, avail);
                        if (!ListenerUtil.mutListener.listen(21480)) {
                            {
                                long _loopCounter465 = 0;
                                for (JSONObject t : tmpls) {
                                    ListenerUtil.loopListener.listen("_loopCounter465", ++_loopCounter465);
                                    int tord = t.getInt("ord");
                                    boolean doHave = (ListenerUtil.mutListener.listen(21466) ? (have.containsKey(nid) || have.get(nid).containsKey(tord)) : (have.containsKey(nid) && have.get(nid).containsKey(tord)));
                                    if (!ListenerUtil.mutListener.listen(21479)) {
                                        if (!doHave) {
                                            // check deck is not a cram deck
                                            long ndid;
                                            try {
                                                ndid = t.getLong("did");
                                                if (!ListenerUtil.mutListener.listen(21473)) {
                                                    if ((ListenerUtil.mutListener.listen(21471) ? (ndid >= 0) : (ListenerUtil.mutListener.listen(21470) ? (ndid <= 0) : (ListenerUtil.mutListener.listen(21469) ? (ndid > 0) : (ListenerUtil.mutListener.listen(21468) ? (ndid < 0) : (ListenerUtil.mutListener.listen(21467) ? (ndid == 0) : (ndid != 0))))))) {
                                                        if (!ListenerUtil.mutListener.listen(21472)) {
                                                            did = ndid;
                                                        }
                                                    }
                                                }
                                            } catch (JSONException e) {
                                            }
                                            if (!ListenerUtil.mutListener.listen(21475)) {
                                                if (getDecks().isDyn(did)) {
                                                    if (!ListenerUtil.mutListener.listen(21474)) {
                                                        did = 1L;
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(21476)) {
                                                // if the deck doesn't exist, use default instead
                                                did = mDecks.get(did).getLong("id");
                                            }
                                            if (!ListenerUtil.mutListener.listen(21477)) {
                                                // give it a new id instead
                                                data.add(new Object[] { ts, nid, did, tord, now, usn, due });
                                            }
                                            if (!ListenerUtil.mutListener.listen(21478)) {
                                                ts += 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(21484)) {
                            // note any cards that need removing
                            if (have.containsKey(nid)) {
                                if (!ListenerUtil.mutListener.listen(21483)) {
                                    {
                                        long _loopCounter466 = 0;
                                        for (Map.Entry<Integer, Long> n : have.get(nid).entrySet()) {
                                            ListenerUtil.loopListener.listen("_loopCounter466", ++_loopCounter466);
                                            if (!ListenerUtil.mutListener.listen(21482)) {
                                                if (!avail.contains(n.getKey())) {
                                                    if (!ListenerUtil.mutListener.listen(21481)) {
                                                        rem.add(n.getValue());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21486)) {
            // bulk update
            mDb.executeMany("INSERT INTO cards VALUES (?,?,?,?,?,?,0,0,?,0,0,0,0,0,0,0,0,\"\")", data);
        }
        return rem;
    }

    /**
     * Create a new card.
     */
    private Card _newCard(Note note, JSONObject template, int due) {
        boolean flush = true;
        return _newCard(note, template, due, flush);
    }

    private Card _newCard(Note note, JSONObject template, int due, long did) {
        boolean flush = true;
        return _newCard(note, template, due, did, flush);
    }

    private Card _newCard(Note note, JSONObject template, int due, boolean flush) {
        long did = 0L;
        return _newCard(note, template, due, did, flush);
    }

    private Card _newCard(Note note, JSONObject template, int due, long parameterDid, boolean flush) {
        Card card = new Card(this);
        return getNewLinkedCard(card, note, template, due, parameterDid, flush);
    }

    // TODO: use an interface that we implement for card viewing, vs subclassing an active model to workaround libAnki
    public Card getNewLinkedCard(Card card, Note note, JSONObject template, int due, long parameterDid, boolean flush) {
        long nid = note.getId();
        if (!ListenerUtil.mutListener.listen(21487)) {
            card.setNid(nid);
        }
        int ord = template.getInt("ord");
        if (!ListenerUtil.mutListener.listen(21488)) {
            card.setOrd(ord);
        }
        long did = mDb.queryLongScalar("select did from cards where nid = ? and ord = ?", nid, ord);
        if (!ListenerUtil.mutListener.listen(21509)) {
            // Use template did (deck override) if valid, otherwise did in argument, otherwise model did
            if ((ListenerUtil.mutListener.listen(21493) ? (did >= 0) : (ListenerUtil.mutListener.listen(21492) ? (did <= 0) : (ListenerUtil.mutListener.listen(21491) ? (did > 0) : (ListenerUtil.mutListener.listen(21490) ? (did < 0) : (ListenerUtil.mutListener.listen(21489) ? (did != 0) : (did == 0))))))) {
                if (!ListenerUtil.mutListener.listen(21494)) {
                    did = template.optLong("did", 0);
                }
                if (!ListenerUtil.mutListener.listen(21508)) {
                    if ((ListenerUtil.mutListener.listen(21500) ? ((ListenerUtil.mutListener.listen(21499) ? (did >= 0) : (ListenerUtil.mutListener.listen(21498) ? (did <= 0) : (ListenerUtil.mutListener.listen(21497) ? (did < 0) : (ListenerUtil.mutListener.listen(21496) ? (did != 0) : (ListenerUtil.mutListener.listen(21495) ? (did == 0) : (did > 0)))))) || mDecks.getDecks().containsKey(did)) : ((ListenerUtil.mutListener.listen(21499) ? (did >= 0) : (ListenerUtil.mutListener.listen(21498) ? (did <= 0) : (ListenerUtil.mutListener.listen(21497) ? (did < 0) : (ListenerUtil.mutListener.listen(21496) ? (did != 0) : (ListenerUtil.mutListener.listen(21495) ? (did == 0) : (did > 0)))))) && mDecks.getDecks().containsKey(did)))) {
                    } else if ((ListenerUtil.mutListener.listen(21505) ? (parameterDid >= 0) : (ListenerUtil.mutListener.listen(21504) ? (parameterDid <= 0) : (ListenerUtil.mutListener.listen(21503) ? (parameterDid > 0) : (ListenerUtil.mutListener.listen(21502) ? (parameterDid < 0) : (ListenerUtil.mutListener.listen(21501) ? (parameterDid == 0) : (parameterDid != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(21507)) {
                            did = parameterDid;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(21506)) {
                            did = note.model().optLong("did", 0);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21510)) {
            card.setDid(did);
        }
        // if invalid did, use default instead
        Deck deck = mDecks.get(card.getDid());
        if (!ListenerUtil.mutListener.listen(21513)) {
            if (deck.getInt("dyn") == DECK_DYN) {
                if (!ListenerUtil.mutListener.listen(21512)) {
                    // must not be a filtered deck
                    card.setDid(1);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(21511)) {
                    card.setDid(deck.getLong("id"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21514)) {
            card.setDue(_dueForDid(card.getDid(), due));
        }
        if (!ListenerUtil.mutListener.listen(21516)) {
            if (flush) {
                if (!ListenerUtil.mutListener.listen(21515)) {
                    card.flush();
                }
            }
        }
        return card;
    }

    public int _dueForDid(long did, int due) {
        DeckConfig conf = mDecks.confForDid(did);
        // in order due?
        if (conf.getJSONObject("new").getInt("order") == Consts.NEW_CARDS_DUE) {
            return due;
        } else {
            // the same random number
            Random r = new Random();
            if (!ListenerUtil.mutListener.listen(21517)) {
                r.setSeed(due);
            }
            return (ListenerUtil.mutListener.listen(21525) ? (r.nextInt((ListenerUtil.mutListener.listen(21521) ? (Math.max(due, 1000) % 1) : (ListenerUtil.mutListener.listen(21520) ? (Math.max(due, 1000) / 1) : (ListenerUtil.mutListener.listen(21519) ? (Math.max(due, 1000) * 1) : (ListenerUtil.mutListener.listen(21518) ? (Math.max(due, 1000) + 1) : (Math.max(due, 1000) - 1)))))) % 1) : (ListenerUtil.mutListener.listen(21524) ? (r.nextInt((ListenerUtil.mutListener.listen(21521) ? (Math.max(due, 1000) % 1) : (ListenerUtil.mutListener.listen(21520) ? (Math.max(due, 1000) / 1) : (ListenerUtil.mutListener.listen(21519) ? (Math.max(due, 1000) * 1) : (ListenerUtil.mutListener.listen(21518) ? (Math.max(due, 1000) + 1) : (Math.max(due, 1000) - 1)))))) / 1) : (ListenerUtil.mutListener.listen(21523) ? (r.nextInt((ListenerUtil.mutListener.listen(21521) ? (Math.max(due, 1000) % 1) : (ListenerUtil.mutListener.listen(21520) ? (Math.max(due, 1000) / 1) : (ListenerUtil.mutListener.listen(21519) ? (Math.max(due, 1000) * 1) : (ListenerUtil.mutListener.listen(21518) ? (Math.max(due, 1000) + 1) : (Math.max(due, 1000) - 1)))))) * 1) : (ListenerUtil.mutListener.listen(21522) ? (r.nextInt((ListenerUtil.mutListener.listen(21521) ? (Math.max(due, 1000) % 1) : (ListenerUtil.mutListener.listen(21520) ? (Math.max(due, 1000) / 1) : (ListenerUtil.mutListener.listen(21519) ? (Math.max(due, 1000) * 1) : (ListenerUtil.mutListener.listen(21518) ? (Math.max(due, 1000) + 1) : (Math.max(due, 1000) - 1)))))) - 1) : (r.nextInt((ListenerUtil.mutListener.listen(21521) ? (Math.max(due, 1000) % 1) : (ListenerUtil.mutListener.listen(21520) ? (Math.max(due, 1000) / 1) : (ListenerUtil.mutListener.listen(21519) ? (Math.max(due, 1000) * 1) : (ListenerUtil.mutListener.listen(21518) ? (Math.max(due, 1000) + 1) : (Math.max(due, 1000) - 1)))))) + 1)))));
        }
    }

    public boolean isEmpty() {
        return mDb.queryScalar("SELECT 1 FROM cards LIMIT 1") == 0;
    }

    public int cardCount() {
        return mDb.queryScalar("SELECT count() FROM cards");
    }

    // NOT IN LIBANKI //
    public int cardCount(Long... dids) {
        return mDb.queryScalar("SELECT count() FROM cards WHERE did IN " + Utils.ids2str(dids));
    }

    public boolean isEmptyDeck(Long... dids) {
        return (ListenerUtil.mutListener.listen(21530) ? (cardCount(dids) >= 0) : (ListenerUtil.mutListener.listen(21529) ? (cardCount(dids) <= 0) : (ListenerUtil.mutListener.listen(21528) ? (cardCount(dids) > 0) : (ListenerUtil.mutListener.listen(21527) ? (cardCount(dids) < 0) : (ListenerUtil.mutListener.listen(21526) ? (cardCount(dids) != 0) : (cardCount(dids) == 0))))));
    }

    /**
     * Bulk delete cards by ID.
     */
    public void remCards(List<Long> ids) {
        if (!ListenerUtil.mutListener.listen(21531)) {
            remCards(ids, true);
        }
    }

    public void remCards(java.util.Collection<Long> ids, boolean notes) {
        if (!ListenerUtil.mutListener.listen(21537)) {
            if ((ListenerUtil.mutListener.listen(21536) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21535) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21534) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(21533) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21532) ? (ids.size() != 0) : (ids.size() == 0))))))) {
                return;
            }
        }
        String sids = Utils.ids2str(ids);
        List<Long> nids = mDb.queryLongList("SELECT nid FROM cards WHERE id IN " + sids);
        if (!ListenerUtil.mutListener.listen(21538)) {
            // remove cards
            _logRem(ids, Consts.REM_CARD);
        }
        if (!ListenerUtil.mutListener.listen(21539)) {
            mDb.execute("DELETE FROM cards WHERE id IN " + sids);
        }
        if (!ListenerUtil.mutListener.listen(21540)) {
            // then notes
            if (!notes) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(21541)) {
            nids = mDb.queryLongList("SELECT id FROM notes WHERE id IN " + Utils.ids2str(nids) + " AND id NOT IN (SELECT nid FROM cards)");
        }
        if (!ListenerUtil.mutListener.listen(21542)) {
            _remNotes(nids);
        }
    }

    public <T extends ProgressSender<Integer> & CancelListener> List<Long> emptyCids(@Nullable T task) {
        List<Long> rem = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(21544)) {
            {
                long _loopCounter468 = 0;
                for (Model m : getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter468", ++_loopCounter468);
                    if (!ListenerUtil.mutListener.listen(21543)) {
                        rem.addAll(genCards(getModels().nids(m), m, task));
                    }
                }
            }
        }
        return rem;
    }

    public String emptyCardReport(List<Long> cids) {
        StringBuilder rep = new StringBuilder();
        try (Cursor cur = mDb.query("select group_concat(ord+1), count(), flds from cards c, notes n " + "where c.nid = n.id and c.id in " + Utils.ids2str(cids) + " group by nid")) {
            if (!ListenerUtil.mutListener.listen(21546)) {
                {
                    long _loopCounter469 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter469", ++_loopCounter469);
                        String ords = cur.getString(0);
                        // int cnt = cur.getInt(1);  // present but unused upstream as well
                        String flds = cur.getString(2);
                        if (!ListenerUtil.mutListener.listen(21545)) {
                            rep.append(String.format("Empty card numbers: %s\nFields: %s\n\n", ords, flds.replace("\u001F", " / ")));
                        }
                    }
                }
            }
        }
        return rep.toString();
    }

    private ArrayList<Object[]> _fieldData(String snids) {
        ArrayList<Object[]> result = new ArrayList<>(mDb.queryScalar("SELECT count() FROM notes WHERE id IN" + snids));
        try (Cursor cur = mDb.query("SELECT id, mid, flds FROM notes WHERE id IN " + snids)) {
            if (!ListenerUtil.mutListener.listen(21548)) {
                {
                    long _loopCounter470 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter470", ++_loopCounter470);
                        if (!ListenerUtil.mutListener.listen(21547)) {
                            result.add(new Object[] { cur.getLong(0), cur.getLong(1), cur.getString(2) });
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Update field checksums and sort cache, after find&replace, etc.
     * @param nids
     */
    public void updateFieldCache(java.util.Collection<Long> nids) {
        String snids = Utils.ids2str(nids);
        if (!ListenerUtil.mutListener.listen(21549)) {
            updateFieldCache(snids);
        }
    }

    /**
     * Update field checksums and sort cache, after find&replace, etc.
     * @param nids
     */
    public void updateFieldCache(long[] nids) {
        String snids = Utils.ids2str(nids);
        if (!ListenerUtil.mutListener.listen(21550)) {
            updateFieldCache(snids);
        }
    }

    /**
     * Update field checksums and sort cache, after find&replace, etc.
     * @param snids comma separated nids
     */
    public void updateFieldCache(String snids) {
        ArrayList<Object[]> data = _fieldData(snids);
        ArrayList<Object[]> r = new ArrayList<>(data.size());
        if (!ListenerUtil.mutListener.listen(21553)) {
            {
                long _loopCounter471 = 0;
                for (Object[] o : data) {
                    ListenerUtil.loopListener.listen("_loopCounter471", ++_loopCounter471);
                    String[] fields = Utils.splitFields((String) o[2]);
                    Model model = getModels().get((Long) o[1]);
                    if (!ListenerUtil.mutListener.listen(21551)) {
                        if (model == null) {
                            // note point to invalid model
                            continue;
                        }
                    }
                    Pair<String, Long> csumAndStrippedFieldField = Utils.sfieldAndCsum(fields, getModels().sortIdx(model));
                    if (!ListenerUtil.mutListener.listen(21552)) {
                        r.add(new Object[] { csumAndStrippedFieldField.first, csumAndStrippedFieldField.second, o[0] });
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21554)) {
            // apply, relying on calling code to bump usn+mod
            mDb.executeMany("UPDATE notes SET sfld=?, csum=? WHERE id=?", r);
        }
    }

    /**
     * Returns hash of id, question, answer.
     */
    public HashMap<String, String> _renderQA(long cid, Model model, long did, int ord, String tags, String[] flist, int flags) {
        return _renderQA(cid, model, did, ord, tags, flist, flags, false, null, null);
    }

    public HashMap<String, String> _renderQA(long cid, Model model, long did, int ord, String tags, String[] flist, int flags, boolean browser, String qfmt, String afmt) {
        // unpack fields and create dict
        Map<String, Pair<Integer, JSONObject>> fmap = Models.fieldMap(model);
        Set<Map.Entry<String, Pair<Integer, JSONObject>>> maps = fmap.entrySet();
        Map<String, String> fields = new HashMap<>(maps.size() + 8);
        if (!ListenerUtil.mutListener.listen(21556)) {
            {
                long _loopCounter472 = 0;
                for (Map.Entry<String, Pair<Integer, JSONObject>> entry : maps) {
                    ListenerUtil.loopListener.listen("_loopCounter472", ++_loopCounter472);
                    if (!ListenerUtil.mutListener.listen(21555)) {
                        fields.put(entry.getKey(), flist[entry.getValue().first]);
                    }
                }
            }
        }
        int cardNum = (ListenerUtil.mutListener.listen(21560) ? (ord % 1) : (ListenerUtil.mutListener.listen(21559) ? (ord / 1) : (ListenerUtil.mutListener.listen(21558) ? (ord * 1) : (ListenerUtil.mutListener.listen(21557) ? (ord - 1) : (ord + 1)))));
        if (!ListenerUtil.mutListener.listen(21561)) {
            fields.put("Tags", tags.trim());
        }
        if (!ListenerUtil.mutListener.listen(21562)) {
            fields.put("Type", model.getString("name"));
        }
        if (!ListenerUtil.mutListener.listen(21563)) {
            fields.put("Deck", mDecks.name(did));
        }
        String baseName = Decks.basename(fields.get("Deck"));
        if (!ListenerUtil.mutListener.listen(21564)) {
            fields.put("Subdeck", baseName);
        }
        if (!ListenerUtil.mutListener.listen(21565)) {
            fields.put("CardFlag", _flagNameFromCardFlags(flags));
        }
        JSONObject template;
        if (model.isStd()) {
            template = model.getJSONArray("tmpls").getJSONObject(ord);
        } else {
            template = model.getJSONArray("tmpls").getJSONObject(0);
        }
        if (!ListenerUtil.mutListener.listen(21566)) {
            fields.put("Card", template.getString("name"));
        }
        if (!ListenerUtil.mutListener.listen(21567)) {
            fields.put(String.format(Locale.US, "c%d", cardNum), "1");
        }
        // render q & a
        HashMap<String, String> d = new HashMap<>(2);
        if (!ListenerUtil.mutListener.listen(21568)) {
            d.put("id", Long.toString(cid));
        }
        if (!ListenerUtil.mutListener.listen(21569)) {
            qfmt = TextUtils.isEmpty(qfmt) ? template.getString("qfmt") : qfmt;
        }
        if (!ListenerUtil.mutListener.listen(21570)) {
            afmt = TextUtils.isEmpty(afmt) ? template.getString("afmt") : afmt;
        }
        if (!ListenerUtil.mutListener.listen(21587)) {
            {
                long _loopCounter473 = 0;
                for (Pair<String, String> p : new Pair[] { new Pair<>("q", qfmt), new Pair<>("a", afmt) }) {
                    ListenerUtil.loopListener.listen("_loopCounter473", ++_loopCounter473);
                    String type = p.first;
                    String format = p.second;
                    if (!ListenerUtil.mutListener.listen(21576)) {
                        if ("q".equals(type)) {
                            if (!ListenerUtil.mutListener.listen(21574)) {
                                format = fClozePatternQ.matcher(format).replaceAll(String.format(Locale.US, "{{$1cq-%d:", cardNum));
                            }
                            if (!ListenerUtil.mutListener.listen(21575)) {
                                format = fClozeTagStart.matcher(format).replaceAll(String.format(Locale.US, "<%%cq:%d:", cardNum));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(21571)) {
                                format = fClozePatternA.matcher(format).replaceAll(String.format(Locale.US, "{{$1ca-%d:", cardNum));
                            }
                            if (!ListenerUtil.mutListener.listen(21572)) {
                                format = fClozeTagStart.matcher(format).replaceAll(String.format(Locale.US, "<%%ca:%d:", cardNum));
                            }
                            if (!ListenerUtil.mutListener.listen(21573)) {
                                // fields.put("FrontSide", mMedia.stripAudio(d.get("q")));
                                fields.put("FrontSide", d.get("q"));
                            }
                        }
                    }
                    String html;
                    try {
                        html = ParsedNode.parse_inner(format).render(fields, "q".equals(type), getContext());
                    } catch (TemplateError er) {
                        html = er.message(getContext());
                    }
                    html = ChessFilter.fenToChessboard(html, getContext());
                    if (!browser) {
                        // browser don't show image. So compiling LaTeX actually remove information.
                        html = LaTeX.mungeQA(html, this, model);
                    }
                    if (!ListenerUtil.mutListener.listen(21577)) {
                        d.put(type, html);
                    }
                    if (!ListenerUtil.mutListener.listen(21586)) {
                        // empty cloze?
                        if ((ListenerUtil.mutListener.listen(21578) ? ("q".equals(type) || model.isCloze()) : ("q".equals(type) && model.isCloze()))) {
                            if (!ListenerUtil.mutListener.listen(21585)) {
                                if ((ListenerUtil.mutListener.listen(21583) ? (Models._availClozeOrds(model, flist, false).size() >= 0) : (ListenerUtil.mutListener.listen(21582) ? (Models._availClozeOrds(model, flist, false).size() <= 0) : (ListenerUtil.mutListener.listen(21581) ? (Models._availClozeOrds(model, flist, false).size() > 0) : (ListenerUtil.mutListener.listen(21580) ? (Models._availClozeOrds(model, flist, false).size() < 0) : (ListenerUtil.mutListener.listen(21579) ? (Models._availClozeOrds(model, flist, false).size() != 0) : (Models._availClozeOrds(model, flist, false).size() == 0))))))) {
                                    String link = String.format("<a href=%s#cloze>%s</a>", Consts.HELP_SITE, "help");
                                    if (!ListenerUtil.mutListener.listen(21584)) {
                                        d.put("q", mContext.getString(R.string.empty_cloze_warning, link));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return d;
    }

    /**
     * Return [cid, nid, mid, did, ord, tags, flds, flags] db query
     */
    public ArrayList<Object[]> _qaData() {
        return _qaData("");
    }

    public ArrayList<Object[]> _qaData(String where) {
        ArrayList<Object[]> data = new ArrayList<>();
        try (Cursor cur = mDb.query("SELECT c.id, n.id, n.mid, c.did, c.ord, " + "n.tags, n.flds, c.flags FROM cards c, notes n WHERE c.nid == n.id " + where)) {
            if (!ListenerUtil.mutListener.listen(21589)) {
                {
                    long _loopCounter474 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter474", ++_loopCounter474);
                        if (!ListenerUtil.mutListener.listen(21588)) {
                            data.add(new Object[] { cur.getLong(0), cur.getLong(1), getModels().get(cur.getLong(2)), cur.getLong(3), cur.getInt(4), cur.getString(5), cur.getString(6), cur.getInt(7) });
                        }
                    }
                }
            }
        }
        return data;
    }

    public String _flagNameFromCardFlags(int flags) {
        int flag = flags & 0b111;
        if (!ListenerUtil.mutListener.listen(21595)) {
            if ((ListenerUtil.mutListener.listen(21594) ? (flag >= 0) : (ListenerUtil.mutListener.listen(21593) ? (flag <= 0) : (ListenerUtil.mutListener.listen(21592) ? (flag > 0) : (ListenerUtil.mutListener.listen(21591) ? (flag < 0) : (ListenerUtil.mutListener.listen(21590) ? (flag != 0) : (flag == 0))))))) {
                return "";
            }
        }
        return "flag" + flag;
    }

    /**
     * Return a list of card ids
     */
    public List<Long> findCards(String search) {
        return new Finder(this).findCards(search, null);
    }

    /**
     * Return a list of card ids
     */
    public List<Long> findCards(String search, String order) {
        return new Finder(this).findCards(search, order);
    }

    public List<Long> findCards(String search, boolean order) {
        return findCards(search, order, null);
    }

    public List<Long> findCards(String search, boolean order, CollectionTask.PartialSearch task) {
        return new Finder(this).findCards(search, order, task);
    }

    /**
     * Return a list of note ids
     */
    public List<Long> findNotes(String query) {
        return new Finder(this).findNotes(query);
    }

    public int findReplace(List<Long> nids, String src, String dst) {
        return Finder.findReplace(this, nids, src, dst);
    }

    public int findReplace(List<Long> nids, String src, String dst, boolean regex) {
        return Finder.findReplace(this, nids, src, dst, regex);
    }

    public int findReplace(List<Long> nids, String src, String dst, String field) {
        return Finder.findReplace(this, nids, src, dst, field);
    }

    public int findReplace(List<Long> nids, String src, String dst, boolean regex, String field, boolean fold) {
        return Finder.findReplace(this, nids, src, dst, regex, field, fold);
    }

    public List<Pair<String, List<Long>>> findDupes(String fieldName) {
        return Finder.findDupes(this, fieldName, "");
    }

    public List<Pair<String, List<Long>>> findDupes(String fieldName, String search) {
        return Finder.findDupes(this, fieldName, search);
    }

    public void setTimeLimit(long seconds) {
        if (!ListenerUtil.mutListener.listen(21596)) {
            mConf.put("timeLim", seconds);
        }
    }

    public long getTimeLimit() {
        return mConf.getLong("timeLim");
    }

    public void startTimebox() {
        if (!ListenerUtil.mutListener.listen(21597)) {
            mStartTime = getTime().intTime();
        }
        if (!ListenerUtil.mutListener.listen(21598)) {
            mStartReps = mSched.getReps();
        }
    }

    /* Return (elapsedTime, reps) if timebox reached, or null. */
    public Pair<Integer, Integer> timeboxReached() {
        if (!ListenerUtil.mutListener.listen(21604)) {
            if ((ListenerUtil.mutListener.listen(21603) ? (mConf.getLong("timeLim") >= 0) : (ListenerUtil.mutListener.listen(21602) ? (mConf.getLong("timeLim") <= 0) : (ListenerUtil.mutListener.listen(21601) ? (mConf.getLong("timeLim") > 0) : (ListenerUtil.mutListener.listen(21600) ? (mConf.getLong("timeLim") < 0) : (ListenerUtil.mutListener.listen(21599) ? (mConf.getLong("timeLim") != 0) : (mConf.getLong("timeLim") == 0))))))) {
                // timeboxing disabled
                return null;
            }
        }
        long elapsed = (ListenerUtil.mutListener.listen(21608) ? (getTime().intTime() % mStartTime) : (ListenerUtil.mutListener.listen(21607) ? (getTime().intTime() / mStartTime) : (ListenerUtil.mutListener.listen(21606) ? (getTime().intTime() * mStartTime) : (ListenerUtil.mutListener.listen(21605) ? (getTime().intTime() + mStartTime) : (getTime().intTime() - mStartTime)))));
        if (!ListenerUtil.mutListener.listen(21618)) {
            if ((ListenerUtil.mutListener.listen(21613) ? (elapsed >= mConf.getLong("timeLim")) : (ListenerUtil.mutListener.listen(21612) ? (elapsed <= mConf.getLong("timeLim")) : (ListenerUtil.mutListener.listen(21611) ? (elapsed < mConf.getLong("timeLim")) : (ListenerUtil.mutListener.listen(21610) ? (elapsed != mConf.getLong("timeLim")) : (ListenerUtil.mutListener.listen(21609) ? (elapsed == mConf.getLong("timeLim")) : (elapsed > mConf.getLong("timeLim")))))))) {
                return new Pair<>(mConf.getInt("timeLim"), (ListenerUtil.mutListener.listen(21617) ? (mSched.getReps() % mStartReps) : (ListenerUtil.mutListener.listen(21616) ? (mSched.getReps() / mStartReps) : (ListenerUtil.mutListener.listen(21615) ? (mSched.getReps() * mStartReps) : (ListenerUtil.mutListener.listen(21614) ? (mSched.getReps() + mStartReps) : (mSched.getReps() - mStartReps))))));
            }
        }
        return null;
    }

    /* Note from upstream:
     * this data structure is a mess, and will be updated soon
     * in the review case, [1, "Review", [firstReviewedCard, secondReviewedCard, ...], wasLeech]
     * in the checkpoint case, [2, "action name"]
     * wasLeech should have been recorded for each card, not globally
     */
    public void clearUndo() {
        if (!ListenerUtil.mutListener.listen(21619)) {
            mUndo = new LinkedBlockingDeque<>();
        }
    }

    /**
     * Undo menu item name, or "" if undo unavailable.
     */
    @VisibleForTesting
    @Nullable
    public DismissType undoType() {
        if (!ListenerUtil.mutListener.listen(21625)) {
            if ((ListenerUtil.mutListener.listen(21624) ? (mUndo.size() >= 0) : (ListenerUtil.mutListener.listen(21623) ? (mUndo.size() <= 0) : (ListenerUtil.mutListener.listen(21622) ? (mUndo.size() < 0) : (ListenerUtil.mutListener.listen(21621) ? (mUndo.size() != 0) : (ListenerUtil.mutListener.listen(21620) ? (mUndo.size() == 0) : (mUndo.size() > 0))))))) {
                return mUndo.getLast().getDismissType();
            }
        }
        return null;
    }

    public String undoName(Resources res) {
        DismissType type = undoType();
        if (!ListenerUtil.mutListener.listen(21626)) {
            if (type != null) {
                return type.getString(res);
            }
        }
        return "";
    }

    public boolean undoAvailable() {
        if (!ListenerUtil.mutListener.listen(21627)) {
            Timber.d("undoAvailable() undo size: %s", mUndo.size());
        }
        return (ListenerUtil.mutListener.listen(21632) ? (mUndo.size() >= 0) : (ListenerUtil.mutListener.listen(21631) ? (mUndo.size() <= 0) : (ListenerUtil.mutListener.listen(21630) ? (mUndo.size() < 0) : (ListenerUtil.mutListener.listen(21629) ? (mUndo.size() != 0) : (ListenerUtil.mutListener.listen(21628) ? (mUndo.size() == 0) : (mUndo.size() > 0))))));
    }

    @Nullable
    public Card undo() {
        Undoable lastUndo = mUndo.removeLast();
        if (!ListenerUtil.mutListener.listen(21633)) {
            Timber.d("undo() of type %s", lastUndo.getDismissType());
        }
        return lastUndo.undo(this);
    }

    public void markUndo(@NonNull Undoable undo) {
        if (!ListenerUtil.mutListener.listen(21634)) {
            Timber.d("markUndo() of type %s", undo.getDismissType());
        }
        if (!ListenerUtil.mutListener.listen(21635)) {
            mUndo.add(undo);
        }
        if (!ListenerUtil.mutListener.listen(21642)) {
            {
                long _loopCounter475 = 0;
                while ((ListenerUtil.mutListener.listen(21641) ? (mUndo.size() >= UNDO_SIZE_MAX) : (ListenerUtil.mutListener.listen(21640) ? (mUndo.size() <= UNDO_SIZE_MAX) : (ListenerUtil.mutListener.listen(21639) ? (mUndo.size() < UNDO_SIZE_MAX) : (ListenerUtil.mutListener.listen(21638) ? (mUndo.size() != UNDO_SIZE_MAX) : (ListenerUtil.mutListener.listen(21637) ? (mUndo.size() == UNDO_SIZE_MAX) : (mUndo.size() > UNDO_SIZE_MAX))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter475", ++_loopCounter475);
                    if (!ListenerUtil.mutListener.listen(21636)) {
                        mUndo.removeFirst();
                    }
                }
            }
        }
    }

    public void markReview(Card card) {
        boolean wasLeech = card.note().hasTag("leech");
        Card clonedCard = card.clone();
        Undoable undoableReview = new Undoable(REVIEW) {

            @Nullable
            public Card undo(@NonNull Collection col) {
                if (!ListenerUtil.mutListener.listen(21643)) {
                    col.getSched().undoReview(clonedCard, wasLeech);
                }
                return clonedCard;
            }
        };
        if (!ListenerUtil.mutListener.listen(21644)) {
            markUndo(undoableReview);
        }
    }

    /*
     * Basic integrity check for syncing. True if ok.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean basicCheck() {
        if (!ListenerUtil.mutListener.listen(21650)) {
            // cards without notes
            if ((ListenerUtil.mutListener.listen(21649) ? (mDb.queryScalar("select 1 from cards where nid not in (select id from notes) limit 1") >= 0) : (ListenerUtil.mutListener.listen(21648) ? (mDb.queryScalar("select 1 from cards where nid not in (select id from notes) limit 1") <= 0) : (ListenerUtil.mutListener.listen(21647) ? (mDb.queryScalar("select 1 from cards where nid not in (select id from notes) limit 1") < 0) : (ListenerUtil.mutListener.listen(21646) ? (mDb.queryScalar("select 1 from cards where nid not in (select id from notes) limit 1") != 0) : (ListenerUtil.mutListener.listen(21645) ? (mDb.queryScalar("select 1 from cards where nid not in (select id from notes) limit 1") == 0) : (mDb.queryScalar("select 1 from cards where nid not in (select id from notes) limit 1") > 0))))))) {
                return false;
            }
        }
        boolean badNotes = (ListenerUtil.mutListener.listen(21655) ? (mDb.queryScalar("select 1 from notes where id not in (select distinct nid from cards) " + "or mid not in " + Utils.ids2str(getModels().ids()) + " limit 1") >= 0) : (ListenerUtil.mutListener.listen(21654) ? (mDb.queryScalar("select 1 from notes where id not in (select distinct nid from cards) " + "or mid not in " + Utils.ids2str(getModels().ids()) + " limit 1") <= 0) : (ListenerUtil.mutListener.listen(21653) ? (mDb.queryScalar("select 1 from notes where id not in (select distinct nid from cards) " + "or mid not in " + Utils.ids2str(getModels().ids()) + " limit 1") < 0) : (ListenerUtil.mutListener.listen(21652) ? (mDb.queryScalar("select 1 from notes where id not in (select distinct nid from cards) " + "or mid not in " + Utils.ids2str(getModels().ids()) + " limit 1") != 0) : (ListenerUtil.mutListener.listen(21651) ? (mDb.queryScalar("select 1 from notes where id not in (select distinct nid from cards) " + "or mid not in " + Utils.ids2str(getModels().ids()) + " limit 1") == 0) : (mDb.queryScalar("select 1 from notes where id not in (select distinct nid from cards) " + "or mid not in " + Utils.ids2str(getModels().ids()) + " limit 1") > 0))))));
        if (!ListenerUtil.mutListener.listen(21656)) {
            // notes without cards or models
            if (badNotes) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(21669)) {
            {
                long _loopCounter476 = 0;
                // invalid ords
                for (JSONObject m : getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter476", ++_loopCounter476);
                    if (!ListenerUtil.mutListener.listen(21662)) {
                        // ignore clozes
                        if ((ListenerUtil.mutListener.listen(21661) ? (m.getInt("type") >= Consts.MODEL_STD) : (ListenerUtil.mutListener.listen(21660) ? (m.getInt("type") <= Consts.MODEL_STD) : (ListenerUtil.mutListener.listen(21659) ? (m.getInt("type") > Consts.MODEL_STD) : (ListenerUtil.mutListener.listen(21658) ? (m.getInt("type") < Consts.MODEL_STD) : (ListenerUtil.mutListener.listen(21657) ? (m.getInt("type") == Consts.MODEL_STD) : (m.getInt("type") != Consts.MODEL_STD))))))) {
                            continue;
                        }
                    }
                    // Make a list of valid ords for this model
                    JSONArray tmpls = m.getJSONArray("tmpls");
                    boolean badOrd = (ListenerUtil.mutListener.listen(21667) ? (mDb.queryScalar("select 1 from cards where (ord < 0 or ord >= ?) and nid in ( " + "select id from notes where mid = ?) limit 1", tmpls.length(), m.getLong("id")) >= 0) : (ListenerUtil.mutListener.listen(21666) ? (mDb.queryScalar("select 1 from cards where (ord < 0 or ord >= ?) and nid in ( " + "select id from notes where mid = ?) limit 1", tmpls.length(), m.getLong("id")) <= 0) : (ListenerUtil.mutListener.listen(21665) ? (mDb.queryScalar("select 1 from cards where (ord < 0 or ord >= ?) and nid in ( " + "select id from notes where mid = ?) limit 1", tmpls.length(), m.getLong("id")) < 0) : (ListenerUtil.mutListener.listen(21664) ? (mDb.queryScalar("select 1 from cards where (ord < 0 or ord >= ?) and nid in ( " + "select id from notes where mid = ?) limit 1", tmpls.length(), m.getLong("id")) != 0) : (ListenerUtil.mutListener.listen(21663) ? (mDb.queryScalar("select 1 from cards where (ord < 0 or ord >= ?) and nid in ( " + "select id from notes where mid = ?) limit 1", tmpls.length(), m.getLong("id")) == 0) : (mDb.queryScalar("select 1 from cards where (ord < 0 or ord >= ?) and nid in ( " + "select id from notes where mid = ?) limit 1", tmpls.length(), m.getLong("id")) > 0))))));
                    if (!ListenerUtil.mutListener.listen(21668)) {
                        if (badOrd) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Fix possible problems and rebuild caches.
     */
    public CheckDatabaseResult fixIntegrity(TaskManager.ProgressCallback<String> progressCallback) {
        File file = new File(mPath);
        CheckDatabaseResult result = new CheckDatabaseResult(file.length());
        final int[] currentTask = { 1 };
        // a few fixes are in all-models loops, the rest are one-offs
        int totalTasks = (ListenerUtil.mutListener.listen(21677) ? (((ListenerUtil.mutListener.listen(21673) ? (getModels().all().size() % 4) : (ListenerUtil.mutListener.listen(21672) ? (getModels().all().size() / 4) : (ListenerUtil.mutListener.listen(21671) ? (getModels().all().size() - 4) : (ListenerUtil.mutListener.listen(21670) ? (getModels().all().size() + 4) : (getModels().all().size() * 4)))))) % 27) : (ListenerUtil.mutListener.listen(21676) ? (((ListenerUtil.mutListener.listen(21673) ? (getModels().all().size() % 4) : (ListenerUtil.mutListener.listen(21672) ? (getModels().all().size() / 4) : (ListenerUtil.mutListener.listen(21671) ? (getModels().all().size() - 4) : (ListenerUtil.mutListener.listen(21670) ? (getModels().all().size() + 4) : (getModels().all().size() * 4)))))) / 27) : (ListenerUtil.mutListener.listen(21675) ? (((ListenerUtil.mutListener.listen(21673) ? (getModels().all().size() % 4) : (ListenerUtil.mutListener.listen(21672) ? (getModels().all().size() / 4) : (ListenerUtil.mutListener.listen(21671) ? (getModels().all().size() - 4) : (ListenerUtil.mutListener.listen(21670) ? (getModels().all().size() + 4) : (getModels().all().size() * 4)))))) * 27) : (ListenerUtil.mutListener.listen(21674) ? (((ListenerUtil.mutListener.listen(21673) ? (getModels().all().size() % 4) : (ListenerUtil.mutListener.listen(21672) ? (getModels().all().size() / 4) : (ListenerUtil.mutListener.listen(21671) ? (getModels().all().size() - 4) : (ListenerUtil.mutListener.listen(21670) ? (getModels().all().size() + 4) : (getModels().all().size() * 4)))))) - 27) : (((ListenerUtil.mutListener.listen(21673) ? (getModels().all().size() % 4) : (ListenerUtil.mutListener.listen(21672) ? (getModels().all().size() / 4) : (ListenerUtil.mutListener.listen(21671) ? (getModels().all().size() - 4) : (ListenerUtil.mutListener.listen(21670) ? (getModels().all().size() + 4) : (getModels().all().size() * 4)))))) + 27)))));
        Runnable notifyProgress = () -> fixIntegrityProgress(progressCallback, currentTask[0]++, totalTasks);
        FunctionalInterfaces.Consumer<FunctionalInterfaces.FunctionThrowable<Runnable, List<String>, JSONException>> executeIntegrityTask = function -> {
            // DEFECT: notifyProgress will lag if an exception is thrown.
            try {
                mDb.getDatabase().beginTransaction();
                result.addAll(function.apply(notifyProgress));
                mDb.getDatabase().setTransactionSuccessful();
            } catch (Exception e) {
                Timber.e(e, "Failed to execute integrity check");
                AnkiDroidApp.sendExceptionReport(e, "fixIntegrity");
            } finally {
                try {
                    mDb.getDatabase().endTransaction();
                } catch (Exception e) {
                    Timber.e(e, "Failed to end integrity check transaction");
                    AnkiDroidApp.sendExceptionReport(e, "fixIntegrity - endTransaction");
                }
            }
        };
        try {
            if (!ListenerUtil.mutListener.listen(21683)) {
                mDb.getDatabase().beginTransaction();
            }
            if (!ListenerUtil.mutListener.listen(21684)) {
                save();
            }
            if (!ListenerUtil.mutListener.listen(21685)) {
                notifyProgress.run();
            }
            if (!ListenerUtil.mutListener.listen(21686)) {
                if (!mDb.getDatabase().isDatabaseIntegrityOk()) {
                    return result.markAsFailed();
                }
            }
            if (!ListenerUtil.mutListener.listen(21687)) {
                mDb.getDatabase().setTransactionSuccessful();
            }
        } catch (SQLiteDatabaseLockedException ex) {
            if (!ListenerUtil.mutListener.listen(21678)) {
                Timber.e("doInBackgroundCheckDatabase - Database locked");
            }
            return result.markAsLocked();
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(21679)) {
                Timber.e(e, "doInBackgroundCheckDatabase - RuntimeException on marking card");
            }
            if (!ListenerUtil.mutListener.listen(21680)) {
                AnkiDroidApp.sendExceptionReport(e, "doInBackgroundCheckDatabase");
            }
            return result.markAsFailed();
        } finally {
            if (!ListenerUtil.mutListener.listen(21682)) {
                // if the database was locked, we never got the transaction.
                if (mDb.getDatabase().inTransaction()) {
                    if (!ListenerUtil.mutListener.listen(21681)) {
                        mDb.getDatabase().endTransaction();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21688)) {
            executeIntegrityTask.consume(this::deleteNotesWithMissingModel);
        }
        if (!ListenerUtil.mutListener.listen(21691)) {
            {
                long _loopCounter477 = 0;
                // for each model
                for (Model m : getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter477", ++_loopCounter477);
                    if (!ListenerUtil.mutListener.listen(21689)) {
                        executeIntegrityTask.consume((callback) -> deleteCardsWithInvalidModelOrdinals(callback, m));
                    }
                    if (!ListenerUtil.mutListener.listen(21690)) {
                        executeIntegrityTask.consume((callback) -> deleteNotesWithWrongFieldCounts(callback, m));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21692)) {
            executeIntegrityTask.consume(this::deleteNotesWithMissingCards);
        }
        if (!ListenerUtil.mutListener.listen(21693)) {
            executeIntegrityTask.consume(this::deleteCardsWithMissingNotes);
        }
        if (!ListenerUtil.mutListener.listen(21694)) {
            executeIntegrityTask.consume(this::removeOriginalDuePropertyWhereInvalid);
        }
        if (!ListenerUtil.mutListener.listen(21695)) {
            executeIntegrityTask.consume(this::removeDynamicPropertyFromNonDynamicDecks);
        }
        if (!ListenerUtil.mutListener.listen(21696)) {
            executeIntegrityTask.consume(this::removeDeckOptionsFromDynamicDecks);
        }
        if (!ListenerUtil.mutListener.listen(21697)) {
            executeIntegrityTask.consume(this::resetInvalidDeckOptions);
        }
        if (!ListenerUtil.mutListener.listen(21698)) {
            executeIntegrityTask.consume(this::rebuildTags);
        }
        if (!ListenerUtil.mutListener.listen(21699)) {
            executeIntegrityTask.consume(this::updateFieldCache);
        }
        if (!ListenerUtil.mutListener.listen(21700)) {
            executeIntegrityTask.consume(this::fixNewCardDuePositionOverflow);
        }
        if (!ListenerUtil.mutListener.listen(21701)) {
            executeIntegrityTask.consume(this::resetNewCardInsertionPosition);
        }
        if (!ListenerUtil.mutListener.listen(21702)) {
            executeIntegrityTask.consume(this::fixExcessiveReviewDueDates);
        }
        if (!ListenerUtil.mutListener.listen(21703)) {
            // v2 sched had a bug that could create decimal intervals
            executeIntegrityTask.consume(this::fixDecimalCardsData);
        }
        if (!ListenerUtil.mutListener.listen(21704)) {
            executeIntegrityTask.consume(this::fixDecimalRevLogData);
        }
        if (!ListenerUtil.mutListener.listen(21705)) {
            executeIntegrityTask.consume(this::restoreMissingDatabaseIndices);
        }
        if (!ListenerUtil.mutListener.listen(21706)) {
            executeIntegrityTask.consume(this::ensureModelsAreNotEmpty);
        }
        if (!ListenerUtil.mutListener.listen(21707)) {
            executeIntegrityTask.consume((progressNotifier) -> this.ensureCardsHaveHomeDeck(progressNotifier, result));
        }
        // and finally, optimize (unable to be done inside transaction).
        try {
            if (!ListenerUtil.mutListener.listen(21710)) {
                optimize(notifyProgress);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(21708)) {
                Timber.e(e, "optimize");
            }
            if (!ListenerUtil.mutListener.listen(21709)) {
                AnkiDroidApp.sendExceptionReport(e, "fixIntegrity - optimize");
            }
        }
        if (!ListenerUtil.mutListener.listen(21711)) {
            file = new File(mPath);
        }
        long newSize = file.length();
        if (!ListenerUtil.mutListener.listen(21712)) {
            result.setNewSize(newSize);
        }
        if (!ListenerUtil.mutListener.listen(21714)) {
            // if any problems were found, force a full sync
            if (result.hasProblems()) {
                if (!ListenerUtil.mutListener.listen(21713)) {
                    modSchemaNoCheck();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21715)) {
            logProblems(result.getProblems());
        }
        return result;
    }

    private List<String> resetInvalidDeckOptions(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21716)) {
            Timber.d("resetInvalidDeckOptions");
        }
        if (!ListenerUtil.mutListener.listen(21717)) {
            // 6454
            notifyProgress.run();
        }
        // obtain a list of all valid dconf IDs
        List<DeckConfig> allConf = getDecks().allConf();
        HashSet<Long> configIds = new HashSet<>(allConf.size());
        if (!ListenerUtil.mutListener.listen(21719)) {
            {
                long _loopCounter478 = 0;
                for (DeckConfig conf : allConf) {
                    ListenerUtil.loopListener.listen("_loopCounter478", ++_loopCounter478);
                    if (!ListenerUtil.mutListener.listen(21718)) {
                        configIds.add(conf.getLong("id"));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21720)) {
            notifyProgress.run();
        }
        int changed = 0;
        if (!ListenerUtil.mutListener.listen(21726)) {
            {
                long _loopCounter479 = 0;
                for (Deck d : getDecks().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter479", ++_loopCounter479);
                    if (!ListenerUtil.mutListener.listen(21721)) {
                        // dynamic decks do not have dconf
                        if (Decks.isDynamic(d)) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(21725)) {
                        if (!configIds.contains(d.getLong("conf"))) {
                            if (!ListenerUtil.mutListener.listen(21722)) {
                                Timber.d("Reset %s's config to default", d.optString("name", "unknown deck"));
                            }
                            if (!ListenerUtil.mutListener.listen(21723)) {
                                d.put("conf", Consts.DEFAULT_DECK_CONFIG_ID);
                            }
                            if (!ListenerUtil.mutListener.listen(21724)) {
                                changed++;
                            }
                        }
                    }
                }
            }
        }
        List<String> ret = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21734)) {
            if ((ListenerUtil.mutListener.listen(21731) ? (changed >= 0) : (ListenerUtil.mutListener.listen(21730) ? (changed <= 0) : (ListenerUtil.mutListener.listen(21729) ? (changed < 0) : (ListenerUtil.mutListener.listen(21728) ? (changed != 0) : (ListenerUtil.mutListener.listen(21727) ? (changed == 0) : (changed > 0))))))) {
                if (!ListenerUtil.mutListener.listen(21732)) {
                    ret.add("Fixed " + changed + " decks with invalid config");
                }
                if (!ListenerUtil.mutListener.listen(21733)) {
                    getDecks().save();
                }
            }
        }
        return ret;
    }

    /**
     * #5932 - a card may not have a home deck if:
     * <ul>>
     * <li>It is in a dynamic deck, and has odid = 0.</li>
     * <li>It is in a dynamic deck, and the odid refers to a dynamic deck.</li>
     * </ul>
     * Both of these cases can be fixed by moving the decks to a known-good deck
     */
    private List<String> ensureCardsHaveHomeDeck(Runnable notifyProgress, CheckDatabaseResult result) {
        if (!ListenerUtil.mutListener.listen(21735)) {
            Timber.d("ensureCardsHaveHomeDeck()");
        }
        if (!ListenerUtil.mutListener.listen(21736)) {
            notifyProgress.run();
        }
        // get the deck Ids to query
        Long[] dynDeckIds = getDecks().allDynamicDeckIds();
        // make it mutable
        List<Long> dynIdsAndZero = new ArrayList<>(Arrays.asList(dynDeckIds));
        if (!ListenerUtil.mutListener.listen(21737)) {
            dynIdsAndZero.add(0L);
        }
        ArrayList<Long> cardIds = mDb.queryLongList("select id from cards where did in " + Utils.ids2str(dynDeckIds) + "and odid in " + Utils.ids2str(dynIdsAndZero));
        if (!ListenerUtil.mutListener.listen(21738)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21739)) {
            if (cardIds.isEmpty()) {
                return Collections.emptyList();
            }
        }
        // we use a ! prefix to keep it at the top of the deck list
        String recoveredDeckName = "! " + mContext.getString(R.string.check_integrity_recovered_deck_name);
        Long nextDeckId = getDecks().id(recoveredDeckName, true);
        if (!ListenerUtil.mutListener.listen(21740)) {
            if (nextDeckId == null) {
                throw new IllegalStateException("Unable to create deck");
            }
        }
        if (!ListenerUtil.mutListener.listen(21741)) {
            getDecks().flush();
        }
        if (!ListenerUtil.mutListener.listen(21742)) {
            mDb.execute("update cards " + "set did = ?, " + "odid = 0," + "mod = ?, " + "usn = ? " + "where did in " + Utils.ids2str(dynDeckIds) + "and odid in " + Utils.ids2str(dynIdsAndZero), nextDeckId, getTime().intTime(), usn());
        }
        if (!ListenerUtil.mutListener.listen(21743)) {
            result.setCardsWithFixedHomeDeckCount(cardIds.size());
        }
        String message = String.format(Locale.US, "Fixed %d cards with no home deck", cardIds.size());
        return Collections.singletonList(message);
    }

    private ArrayList<String> ensureModelsAreNotEmpty(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21744)) {
            Timber.d("ensureModelsAreNotEmpty()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21745)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21747)) {
            if (getModels().ensureNotEmpty()) {
                if (!ListenerUtil.mutListener.listen(21746)) {
                    problems.add("Added missing note type.");
                }
            }
        }
        return problems;
    }

    private ArrayList<String> restoreMissingDatabaseIndices(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21748)) {
            Timber.d("restoreMissingDatabaseIndices");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21749)) {
            // DB must have indices. Older versions of AnkiDroid didn't create them for new collections.
            notifyProgress.run();
        }
        int ixs = mDb.queryScalar("select count(name) from sqlite_master where type = 'index'");
        if (!ListenerUtil.mutListener.listen(21757)) {
            if ((ListenerUtil.mutListener.listen(21754) ? (ixs >= 7) : (ListenerUtil.mutListener.listen(21753) ? (ixs <= 7) : (ListenerUtil.mutListener.listen(21752) ? (ixs > 7) : (ListenerUtil.mutListener.listen(21751) ? (ixs != 7) : (ListenerUtil.mutListener.listen(21750) ? (ixs == 7) : (ixs < 7))))))) {
                if (!ListenerUtil.mutListener.listen(21755)) {
                    problems.add("Indices were missing.");
                }
                if (!ListenerUtil.mutListener.listen(21756)) {
                    Storage.addIndices(mDb);
                }
            }
        }
        return problems;
    }

    private ArrayList<String> fixDecimalCardsData(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21758)) {
            Timber.d("fixDecimalCardsData");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21759)) {
            notifyProgress.run();
        }
        SupportSQLiteStatement s = mDb.getDatabase().compileStatement("update cards set ivl=round(ivl),due=round(due) where ivl!=round(ivl) or due!=round(due)");
        int rowCount = s.executeUpdateDelete();
        if (!ListenerUtil.mutListener.listen(21766)) {
            if ((ListenerUtil.mutListener.listen(21764) ? (rowCount >= 0) : (ListenerUtil.mutListener.listen(21763) ? (rowCount <= 0) : (ListenerUtil.mutListener.listen(21762) ? (rowCount < 0) : (ListenerUtil.mutListener.listen(21761) ? (rowCount != 0) : (ListenerUtil.mutListener.listen(21760) ? (rowCount == 0) : (rowCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(21765)) {
                    problems.add("Fixed " + rowCount + " cards with v2 scheduler bug.");
                }
            }
        }
        return problems;
    }

    private ArrayList<String> fixDecimalRevLogData(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21767)) {
            Timber.d("fixDecimalRevLogData()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21768)) {
            notifyProgress.run();
        }
        SupportSQLiteStatement s = mDb.getDatabase().compileStatement("update revlog set ivl=round(ivl),lastIvl=round(lastIvl) where ivl!=round(ivl) or lastIvl!=round(lastIvl)");
        int rowCount = s.executeUpdateDelete();
        if (!ListenerUtil.mutListener.listen(21775)) {
            if ((ListenerUtil.mutListener.listen(21773) ? (rowCount >= 0) : (ListenerUtil.mutListener.listen(21772) ? (rowCount <= 0) : (ListenerUtil.mutListener.listen(21771) ? (rowCount < 0) : (ListenerUtil.mutListener.listen(21770) ? (rowCount != 0) : (ListenerUtil.mutListener.listen(21769) ? (rowCount == 0) : (rowCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(21774)) {
                    problems.add("Fixed " + rowCount + " review history entries with v2 scheduler bug.");
                }
            }
        }
        return problems;
    }

    private ArrayList<String> fixExcessiveReviewDueDates(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21776)) {
            Timber.d("fixExcessiveReviewDueDates()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21777)) {
            notifyProgress.run();
        }
        // reviews should have a reasonable due #
        ArrayList<Long> ids = mDb.queryLongList("SELECT id FROM cards WHERE queue = " + Consts.QUEUE_TYPE_REV + " AND due > 100000");
        if (!ListenerUtil.mutListener.listen(21778)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21786)) {
            if ((ListenerUtil.mutListener.listen(21783) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21782) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21781) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21780) ? (ids.size() != 0) : (ListenerUtil.mutListener.listen(21779) ? (ids.size() == 0) : (ids.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(21784)) {
                    problems.add("Reviews had incorrect due date.");
                }
                if (!ListenerUtil.mutListener.listen(21785)) {
                    mDb.execute("UPDATE cards SET due = ?, ivl = 1, mod = ?, usn = ? WHERE id IN " + Utils.ids2str(ids), mSched.getToday(), getTime().intTime(), usn());
                }
            }
        }
        return problems;
    }

    private List<String> resetNewCardInsertionPosition(Runnable notifyProgress) throws JSONException {
        if (!ListenerUtil.mutListener.listen(21787)) {
            Timber.d("resetNewCardInsertionPosition");
        }
        if (!ListenerUtil.mutListener.listen(21788)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21789)) {
            // new card position
            mConf.put("nextPos", mDb.queryScalar("SELECT max(due) + 1 FROM cards WHERE type = " + Consts.CARD_TYPE_NEW));
        }
        return Collections.emptyList();
    }

    private List<String> fixNewCardDuePositionOverflow(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21790)) {
            Timber.d("fixNewCardDuePositionOverflow");
        }
        if (!ListenerUtil.mutListener.listen(21791)) {
            // new cards can't have a due position > 32 bits
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21792)) {
            mDb.execute("UPDATE cards SET due = 1000000, mod = ?, usn = ? WHERE due > 1000000 AND type = " + Consts.CARD_TYPE_NEW, getTime().intTime(), usn());
        }
        return Collections.emptyList();
    }

    private List<String> updateFieldCache(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21793)) {
            Timber.d("updateFieldCache");
        }
        if (!ListenerUtil.mutListener.listen(21796)) {
            {
                long _loopCounter480 = 0;
                // field cache
                for (Model m : getModels().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter480", ++_loopCounter480);
                    if (!ListenerUtil.mutListener.listen(21794)) {
                        notifyProgress.run();
                    }
                    if (!ListenerUtil.mutListener.listen(21795)) {
                        updateFieldCache(getModels().nids(m));
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private List<String> rebuildTags(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21797)) {
            Timber.d("rebuildTags");
        }
        if (!ListenerUtil.mutListener.listen(21798)) {
            // tags
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21799)) {
            mTags.registerNotes();
        }
        return Collections.emptyList();
    }

    private ArrayList<String> removeDeckOptionsFromDynamicDecks(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21800)) {
            Timber.d("removeDeckOptionsFromDynamicDecks()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21801)) {
            // #5708 - a dynamic deck should not have "Deck Options"
            notifyProgress.run();
        }
        int fixCount = 0;
        if (!ListenerUtil.mutListener.listen(21806)) {
            {
                long _loopCounter481 = 0;
                for (long id : mDecks.allDynamicDeckIds()) {
                    ListenerUtil.loopListener.listen("_loopCounter481", ++_loopCounter481);
                    try {
                        if (!ListenerUtil.mutListener.listen(21805)) {
                            if (mDecks.hasDeckOptions(id)) {
                                if (!ListenerUtil.mutListener.listen(21803)) {
                                    mDecks.removeDeckOptions(id);
                                }
                                if (!ListenerUtil.mutListener.listen(21804)) {
                                    fixCount++;
                                }
                            }
                        }
                    } catch (NoSuchDeckException e) {
                        if (!ListenerUtil.mutListener.listen(21802)) {
                            Timber.e("Unable to find dynamic deck %d", id);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21814)) {
            if ((ListenerUtil.mutListener.listen(21811) ? (fixCount >= 0) : (ListenerUtil.mutListener.listen(21810) ? (fixCount <= 0) : (ListenerUtil.mutListener.listen(21809) ? (fixCount < 0) : (ListenerUtil.mutListener.listen(21808) ? (fixCount != 0) : (ListenerUtil.mutListener.listen(21807) ? (fixCount == 0) : (fixCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(21812)) {
                    mDecks.save();
                }
                if (!ListenerUtil.mutListener.listen(21813)) {
                    problems.add(String.format(Locale.US, "%d dynamic deck(s) had deck options.", fixCount));
                }
            }
        }
        return problems;
    }

    private ArrayList<String> removeDynamicPropertyFromNonDynamicDecks(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21815)) {
            Timber.d("removeDynamicPropertyFromNonDynamicDecks()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        ArrayList<Long> dids = new ArrayList<>(mDecks.count());
        if (!ListenerUtil.mutListener.listen(21818)) {
            {
                long _loopCounter482 = 0;
                for (long id : mDecks.allIds()) {
                    ListenerUtil.loopListener.listen("_loopCounter482", ++_loopCounter482);
                    if (!ListenerUtil.mutListener.listen(21817)) {
                        if (!mDecks.isDyn(id)) {
                            if (!ListenerUtil.mutListener.listen(21816)) {
                                dids.add(id);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21819)) {
            notifyProgress.run();
        }
        // cards with odid set when not in a dyn deck
        ArrayList<Long> ids = mDb.queryLongList("select id from cards where odid > 0 and did in " + Utils.ids2str(dids));
        if (!ListenerUtil.mutListener.listen(21820)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21828)) {
            if ((ListenerUtil.mutListener.listen(21825) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21824) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21823) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(21822) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21821) ? (ids.size() == 0) : (ids.size() != 0))))))) {
                if (!ListenerUtil.mutListener.listen(21826)) {
                    problems.add("Fixed " + ids.size() + " card(s) with invalid properties.");
                }
                if (!ListenerUtil.mutListener.listen(21827)) {
                    mDb.execute("update cards set odid=0, odue=0 where id in " + Utils.ids2str(ids));
                }
            }
        }
        return problems;
    }

    private ArrayList<String> removeOriginalDuePropertyWhereInvalid(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21829)) {
            Timber.d("removeOriginalDuePropertyWhereInvalid()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21830)) {
            notifyProgress.run();
        }
        // cards with odue set when it shouldn't be
        ArrayList<Long> ids = mDb.queryLongList("select id from cards where odue > 0 and (type= " + Consts.CARD_TYPE_LRN + " or queue=" + Consts.QUEUE_TYPE_REV + ") and not odid");
        if (!ListenerUtil.mutListener.listen(21831)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21839)) {
            if ((ListenerUtil.mutListener.listen(21836) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21835) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21834) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(21833) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21832) ? (ids.size() == 0) : (ids.size() != 0))))))) {
                if (!ListenerUtil.mutListener.listen(21837)) {
                    problems.add("Fixed " + ids.size() + " card(s) with invalid properties.");
                }
                if (!ListenerUtil.mutListener.listen(21838)) {
                    mDb.execute("update cards set odue=0 where id in " + Utils.ids2str(ids));
                }
            }
        }
        return problems;
    }

    private ArrayList<String> deleteCardsWithMissingNotes(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21840)) {
            Timber.d("deleteCardsWithMissingNotes()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21841)) {
            notifyProgress.run();
        }
        // cards with missing notes
        ArrayList<Long> ids = mDb.queryLongList("SELECT id FROM cards WHERE nid NOT IN (SELECT id FROM notes)");
        if (!ListenerUtil.mutListener.listen(21842)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21850)) {
            if ((ListenerUtil.mutListener.listen(21847) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21846) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21845) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(21844) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21843) ? (ids.size() == 0) : (ids.size() != 0))))))) {
                if (!ListenerUtil.mutListener.listen(21848)) {
                    problems.add("Deleted " + ids.size() + " card(s) with missing note.");
                }
                if (!ListenerUtil.mutListener.listen(21849)) {
                    remCards(ids);
                }
            }
        }
        return problems;
    }

    private ArrayList<String> deleteNotesWithMissingCards(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21851)) {
            Timber.d("deleteNotesWithMissingCards()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21852)) {
            notifyProgress.run();
        }
        // delete any notes with missing cards
        ArrayList<Long> ids = mDb.queryLongList("SELECT id FROM notes WHERE id NOT IN (SELECT DISTINCT nid FROM cards)");
        if (!ListenerUtil.mutListener.listen(21853)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21861)) {
            if ((ListenerUtil.mutListener.listen(21858) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21857) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21856) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(21855) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21854) ? (ids.size() == 0) : (ids.size() != 0))))))) {
                if (!ListenerUtil.mutListener.listen(21859)) {
                    problems.add("Deleted " + ids.size() + " note(s) with missing no cards.");
                }
                if (!ListenerUtil.mutListener.listen(21860)) {
                    _remNotes(ids);
                }
            }
        }
        return problems;
    }

    private ArrayList<String> deleteNotesWithWrongFieldCounts(Runnable notifyProgress, JSONObject m) throws JSONException {
        if (!ListenerUtil.mutListener.listen(21862)) {
            Timber.d("deleteNotesWithWrongFieldCounts");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        // notes with invalid field counts
        ArrayList<Long> ids = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(21863)) {
            notifyProgress.run();
        }
        try (Cursor cur = mDb.query("select id, flds from notes where mid = ?", m.getLong("id"))) {
            if (!ListenerUtil.mutListener.listen(21864)) {
                Timber.i("cursor size: %d", cur.getCount());
            }
            int currentRow = 0;
            // Since we loop through all rows, we only want one exception
            @Nullable
            Exception firstException = null;
            if (!ListenerUtil.mutListener.listen(21884)) {
                {
                    long _loopCounter484 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter484", ++_loopCounter484);
                        try {
                            String flds = cur.getString(1);
                            long id = cur.getLong(0);
                            int fldsCount = 0;
                            if (!ListenerUtil.mutListener.listen(21876)) {
                                {
                                    long _loopCounter483 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(21875) ? (i >= flds.length()) : (ListenerUtil.mutListener.listen(21874) ? (i <= flds.length()) : (ListenerUtil.mutListener.listen(21873) ? (i > flds.length()) : (ListenerUtil.mutListener.listen(21872) ? (i != flds.length()) : (ListenerUtil.mutListener.listen(21871) ? (i == flds.length()) : (i < flds.length())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter483", ++_loopCounter483);
                                        if (!ListenerUtil.mutListener.listen(21870)) {
                                            if (flds.charAt(i) == 0x1f) {
                                                if (!ListenerUtil.mutListener.listen(21869)) {
                                                    fldsCount++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(21882)) {
                                if ((ListenerUtil.mutListener.listen(21880) ? (fldsCount % 1) : (ListenerUtil.mutListener.listen(21879) ? (fldsCount / 1) : (ListenerUtil.mutListener.listen(21878) ? (fldsCount * 1) : (ListenerUtil.mutListener.listen(21877) ? (fldsCount - 1) : (fldsCount + 1))))) != m.getJSONArray("flds").length()) {
                                    if (!ListenerUtil.mutListener.listen(21881)) {
                                        ids.add(id);
                                    }
                                }
                            }
                        } catch (IllegalStateException ex) {
                            if (!ListenerUtil.mutListener.listen(21865)) {
                                // We store one exception to stop excessive logging
                                Timber.i(ex, "deleteNotesWithWrongFieldCounts - Exception on row %d. Columns: %d", currentRow, cur.getColumnCount());
                            }
                            if (!ListenerUtil.mutListener.listen(21868)) {
                                if (firstException == null) {
                                    String details = String.format(Locale.ROOT, "deleteNotesWithWrongFieldCounts row: %d col: %d", currentRow, cur.getColumnCount());
                                    if (!ListenerUtil.mutListener.listen(21866)) {
                                        AnkiDroidApp.sendExceptionReport(ex, details);
                                    }
                                    if (!ListenerUtil.mutListener.listen(21867)) {
                                        firstException = ex;
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(21883)) {
                            currentRow++;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(21885)) {
                Timber.i("deleteNotesWithWrongFieldCounts - completed successfully");
            }
            if (!ListenerUtil.mutListener.listen(21886)) {
                notifyProgress.run();
            }
            if (!ListenerUtil.mutListener.listen(21894)) {
                if ((ListenerUtil.mutListener.listen(21891) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21890) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21889) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21888) ? (ids.size() != 0) : (ListenerUtil.mutListener.listen(21887) ? (ids.size() == 0) : (ids.size() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(21892)) {
                        problems.add("Deleted " + ids.size() + " note(s) with wrong field count.");
                    }
                    if (!ListenerUtil.mutListener.listen(21893)) {
                        _remNotes(ids);
                    }
                }
            }
        }
        return problems;
    }

    private ArrayList<String> deleteCardsWithInvalidModelOrdinals(Runnable notifyProgress, Model m) throws JSONException {
        if (!ListenerUtil.mutListener.listen(21895)) {
            Timber.d("deleteCardsWithInvalidModelOrdinals()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21896)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21907)) {
            if (m.isStd()) {
                JSONArray tmpls = m.getJSONArray("tmpls");
                ArrayList<Integer> ords = new ArrayList<>(tmpls.length());
                if (!ListenerUtil.mutListener.listen(21898)) {
                    {
                        long _loopCounter485 = 0;
                        for (JSONObject tmpl : tmpls.jsonObjectIterable()) {
                            ListenerUtil.loopListener.listen("_loopCounter485", ++_loopCounter485);
                            if (!ListenerUtil.mutListener.listen(21897)) {
                                ords.add(tmpl.getInt("ord"));
                            }
                        }
                    }
                }
                // cards with invalid ordinal
                ArrayList<Long> ids = mDb.queryLongList("SELECT id FROM cards WHERE ord NOT IN " + Utils.ids2str(ords) + " AND nid IN ( " + "SELECT id FROM notes WHERE mid = ?)", m.getLong("id"));
                if (!ListenerUtil.mutListener.listen(21906)) {
                    if ((ListenerUtil.mutListener.listen(21903) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21902) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21901) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21900) ? (ids.size() != 0) : (ListenerUtil.mutListener.listen(21899) ? (ids.size() == 0) : (ids.size() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(21904)) {
                            problems.add("Deleted " + ids.size() + " card(s) with missing template.");
                        }
                        if (!ListenerUtil.mutListener.listen(21905)) {
                            remCards(ids);
                        }
                    }
                }
            }
        }
        return problems;
    }

    private ArrayList<String> deleteNotesWithMissingModel(Runnable notifyProgress) {
        if (!ListenerUtil.mutListener.listen(21908)) {
            Timber.d("deleteNotesWithMissingModel()");
        }
        ArrayList<String> problems = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(21909)) {
            // note types with a missing model
            notifyProgress.run();
        }
        ArrayList<Long> ids = mDb.queryLongList("SELECT id FROM notes WHERE mid NOT IN " + Utils.ids2str(getModels().ids()));
        if (!ListenerUtil.mutListener.listen(21910)) {
            notifyProgress.run();
        }
        if (!ListenerUtil.mutListener.listen(21918)) {
            if ((ListenerUtil.mutListener.listen(21915) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(21914) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(21913) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(21912) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(21911) ? (ids.size() == 0) : (ids.size() != 0))))))) {
                if (!ListenerUtil.mutListener.listen(21916)) {
                    problems.add("Deleted " + ids.size() + " note(s) with missing note type.");
                }
                if (!ListenerUtil.mutListener.listen(21917)) {
                    _remNotes(ids);
                }
            }
        }
        return problems;
    }

    public void optimize(Runnable progressCallback) {
        if (!ListenerUtil.mutListener.listen(21919)) {
            Timber.i("executing VACUUM statement");
        }
        if (!ListenerUtil.mutListener.listen(21920)) {
            progressCallback.run();
        }
        if (!ListenerUtil.mutListener.listen(21921)) {
            mDb.execute("VACUUM");
        }
        if (!ListenerUtil.mutListener.listen(21922)) {
            Timber.i("executing ANALYZE statement");
        }
        if (!ListenerUtil.mutListener.listen(21923)) {
            progressCallback.run();
        }
        if (!ListenerUtil.mutListener.listen(21924)) {
            mDb.execute("ANALYZE");
        }
    }

    private void fixIntegrityProgress(TaskManager.ProgressCallback<String> progressCallback, int current, int total) {
        if (!ListenerUtil.mutListener.listen(21925)) {
            progressCallback.publishProgress(progressCallback.getResources().getString(R.string.check_db_message) + " " + current + " / " + total);
        }
    }

    /**
     * Track database corruption problems and post analytics events for tracking
     *
     * @param integrityCheckProblems list of problems, the first 10 will be used
     */
    private void logProblems(List<String> integrityCheckProblems) {
        if (!ListenerUtil.mutListener.listen(21947)) {
            if ((ListenerUtil.mutListener.listen(21930) ? (integrityCheckProblems.size() >= 0) : (ListenerUtil.mutListener.listen(21929) ? (integrityCheckProblems.size() <= 0) : (ListenerUtil.mutListener.listen(21928) ? (integrityCheckProblems.size() < 0) : (ListenerUtil.mutListener.listen(21927) ? (integrityCheckProblems.size() != 0) : (ListenerUtil.mutListener.listen(21926) ? (integrityCheckProblems.size() == 0) : (integrityCheckProblems.size() > 0))))))) {
                StringBuffer additionalInfo = new StringBuffer();
                if (!ListenerUtil.mutListener.listen(21945)) {
                    {
                        long _loopCounter486 = 0;
                        for (int i = 0; ((ListenerUtil.mutListener.listen(21944) ? (((ListenerUtil.mutListener.listen(21938) ? (i >= 10) : (ListenerUtil.mutListener.listen(21937) ? (i <= 10) : (ListenerUtil.mutListener.listen(21936) ? (i > 10) : (ListenerUtil.mutListener.listen(21935) ? (i != 10) : (ListenerUtil.mutListener.listen(21934) ? (i == 10) : (i < 10))))))) || ((ListenerUtil.mutListener.listen(21943) ? (integrityCheckProblems.size() >= i) : (ListenerUtil.mutListener.listen(21942) ? (integrityCheckProblems.size() <= i) : (ListenerUtil.mutListener.listen(21941) ? (integrityCheckProblems.size() < i) : (ListenerUtil.mutListener.listen(21940) ? (integrityCheckProblems.size() != i) : (ListenerUtil.mutListener.listen(21939) ? (integrityCheckProblems.size() == i) : (integrityCheckProblems.size() > i)))))))) : (((ListenerUtil.mutListener.listen(21938) ? (i >= 10) : (ListenerUtil.mutListener.listen(21937) ? (i <= 10) : (ListenerUtil.mutListener.listen(21936) ? (i > 10) : (ListenerUtil.mutListener.listen(21935) ? (i != 10) : (ListenerUtil.mutListener.listen(21934) ? (i == 10) : (i < 10))))))) && ((ListenerUtil.mutListener.listen(21943) ? (integrityCheckProblems.size() >= i) : (ListenerUtil.mutListener.listen(21942) ? (integrityCheckProblems.size() <= i) : (ListenerUtil.mutListener.listen(21941) ? (integrityCheckProblems.size() < i) : (ListenerUtil.mutListener.listen(21940) ? (integrityCheckProblems.size() != i) : (ListenerUtil.mutListener.listen(21939) ? (integrityCheckProblems.size() == i) : (integrityCheckProblems.size() > i)))))))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter486", ++_loopCounter486);
                            if (!ListenerUtil.mutListener.listen(21932)) {
                                additionalInfo.append(integrityCheckProblems.get(i)).append("\n");
                            }
                            if (!ListenerUtil.mutListener.listen(21933)) {
                                // log analytics event so we can see trends if user allows it
                                UsageAnalytics.sendAnalyticsEvent("DatabaseCorruption", integrityCheckProblems.get(i));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(21946)) {
                    Timber.i("fixIntegrity() Problem list (limited to first 10):\n%s", additionalInfo);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(21931)) {
                    Timber.i("fixIntegrity() no problems found");
                }
            }
        }
    }

    public void log(Object... args) {
        if (!ListenerUtil.mutListener.listen(21948)) {
            if (!mDebugLog) {
                return;
            }
        }
        StackTraceElement trace = Thread.currentThread().getStackTrace()[3];
        if (!ListenerUtil.mutListener.listen(21956)) {
            {
                long _loopCounter487 = 0;
                // Overwrite any args that need special handling for an appropriate string representation
                for (int i = 0; (ListenerUtil.mutListener.listen(21955) ? (i >= args.length) : (ListenerUtil.mutListener.listen(21954) ? (i <= args.length) : (ListenerUtil.mutListener.listen(21953) ? (i > args.length) : (ListenerUtil.mutListener.listen(21952) ? (i != args.length) : (ListenerUtil.mutListener.listen(21951) ? (i == args.length) : (i < args.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter487", ++_loopCounter487);
                    if (!ListenerUtil.mutListener.listen(21950)) {
                        if (args[i] instanceof long[]) {
                            if (!ListenerUtil.mutListener.listen(21949)) {
                                args[i] = Arrays.toString((long[]) args[i]);
                            }
                        }
                    }
                }
            }
        }
        String s = String.format("[%s] %s:%s(): %s", getTime().intTime(), trace.getFileName(), trace.getMethodName(), TextUtils.join(",  ", args));
        if (!ListenerUtil.mutListener.listen(21957)) {
            writeLog(s);
        }
    }

    private void writeLog(String s) {
        if (!ListenerUtil.mutListener.listen(21960)) {
            if (mLogHnd != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(21959)) {
                        mLogHnd.println(s);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(21958)) {
                        Timber.w(e, "Failed to write to collection log");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21961)) {
            Timber.d(s);
        }
    }

    private void _openLog() {
        if (!ListenerUtil.mutListener.listen(21962)) {
            Timber.i("Opening Collection Log");
        }
        if (!ListenerUtil.mutListener.listen(21963)) {
            if (!mDebugLog) {
                return;
            }
        }
        try {
            File lpath = new File(mPath.replaceFirst("\\.anki2$", ".log"));
            if (!ListenerUtil.mutListener.listen(21983)) {
                if ((ListenerUtil.mutListener.listen(21979) ? (lpath.exists() || (ListenerUtil.mutListener.listen(21978) ? (lpath.length() >= (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21977) ? (lpath.length() <= (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21976) ? (lpath.length() < (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21975) ? (lpath.length() != (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21974) ? (lpath.length() == (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (lpath.length() > (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))))))))) : (lpath.exists() && (ListenerUtil.mutListener.listen(21978) ? (lpath.length() >= (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21977) ? (lpath.length() <= (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21976) ? (lpath.length() < (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21975) ? (lpath.length() != (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (ListenerUtil.mutListener.listen(21974) ? (lpath.length() == (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))) : (lpath.length() > (ListenerUtil.mutListener.listen(21973) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(21972) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(21971) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(21970) ? ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(21969) ? (10 % 1024) : (ListenerUtil.mutListener.listen(21968) ? (10 / 1024) : (ListenerUtil.mutListener.listen(21967) ? (10 - 1024) : (ListenerUtil.mutListener.listen(21966) ? (10 + 1024) : (10 * 1024))))) * 1024)))))))))))))) {
                    File lpath2 = new File(lpath + ".old");
                    if (!ListenerUtil.mutListener.listen(21981)) {
                        if (lpath2.exists()) {
                            if (!ListenerUtil.mutListener.listen(21980)) {
                                lpath2.delete();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(21982)) {
                        lpath.renameTo(lpath2);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(21984)) {
                mLogHnd = new PrintWriter(new BufferedWriter(new FileWriter(lpath, true)), true);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(21964)) {
                // turn off logging if we can't open the log file
                Timber.e("Failed to open collection.log file - disabling logging");
            }
            if (!ListenerUtil.mutListener.listen(21965)) {
                mDebugLog = false;
            }
        }
    }

    private void _closeLog() {
        if (!ListenerUtil.mutListener.listen(21985)) {
            Timber.i("Closing Collection Log");
        }
        if (!ListenerUtil.mutListener.listen(21988)) {
            if (mLogHnd != null) {
                if (!ListenerUtil.mutListener.listen(21986)) {
                    mLogHnd.close();
                }
                if (!ListenerUtil.mutListener.listen(21987)) {
                    mLogHnd = null;
                }
            }
        }
    }

    /**
     * Card Flags *****************************************************************************************************
     */
    public void setUserFlag(int flag, List<Long> cids) {
        assert ((ListenerUtil.mutListener.listen(21999) ? ((ListenerUtil.mutListener.listen(21993) ? (0 >= flag) : (ListenerUtil.mutListener.listen(21992) ? (0 > flag) : (ListenerUtil.mutListener.listen(21991) ? (0 < flag) : (ListenerUtil.mutListener.listen(21990) ? (0 != flag) : (ListenerUtil.mutListener.listen(21989) ? (0 == flag) : (0 <= flag)))))) || (ListenerUtil.mutListener.listen(21998) ? (flag >= 7) : (ListenerUtil.mutListener.listen(21997) ? (flag > 7) : (ListenerUtil.mutListener.listen(21996) ? (flag < 7) : (ListenerUtil.mutListener.listen(21995) ? (flag != 7) : (ListenerUtil.mutListener.listen(21994) ? (flag == 7) : (flag <= 7))))))) : ((ListenerUtil.mutListener.listen(21993) ? (0 >= flag) : (ListenerUtil.mutListener.listen(21992) ? (0 > flag) : (ListenerUtil.mutListener.listen(21991) ? (0 < flag) : (ListenerUtil.mutListener.listen(21990) ? (0 != flag) : (ListenerUtil.mutListener.listen(21989) ? (0 == flag) : (0 <= flag)))))) && (ListenerUtil.mutListener.listen(21998) ? (flag >= 7) : (ListenerUtil.mutListener.listen(21997) ? (flag > 7) : (ListenerUtil.mutListener.listen(21996) ? (flag < 7) : (ListenerUtil.mutListener.listen(21995) ? (flag != 7) : (ListenerUtil.mutListener.listen(21994) ? (flag == 7) : (flag <= 7)))))))));
        if (!ListenerUtil.mutListener.listen(22000)) {
            mDb.execute("update cards set flags = (flags & ~?) | ?, usn=?, mod=? where id in " + Utils.ids2str(cids), 0b111, flag, usn(), getTime().intTime());
        }
    }

    public DB getDb() {
        return mDb;
    }

    public Decks getDecks() {
        return mDecks;
    }

    public Media getMedia() {
        return mMedia;
    }

    /**
     * On first call, load the model if it was not loaded.
     *
     * Synchronized to ensure that loading does not occur twice.
     * Normally the first call occurs in the background when
     * collection is loaded.  The only exception being if the user
     * perform an action (e.g. review) so quickly that
     * loadModelsInBackground had no time to be called. In this case
     * it will instantly finish. Note that loading model is a
     * bottleneck anyway, so background call lose all interest.
     *
     * @return The model manager
     */
    public synchronized Models getModels() {
        if (!ListenerUtil.mutListener.listen(22003)) {
            if (mModels == null) {
                if (!ListenerUtil.mutListener.listen(22001)) {
                    mModels = new Models(this);
                }
                if (!ListenerUtil.mutListener.listen(22002)) {
                    mModels.load(loadColumn("models"));
                }
            }
        }
        return mModels;
    }

    /**
     * Check if this collection is valid.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean validCollection() {
        // TODO: more validation code
        return getModels().validateModel();
    }

    public JSONObject getConf() {
        return mConf;
    }

    public void setConf(JSONObject conf) {
        if (!ListenerUtil.mutListener.listen(22004)) {
            // dae/anki#347
            Upgrade.upgradeJSONIfNecessary(this, conf, "sortBackwards", false);
        }
        if (!ListenerUtil.mutListener.listen(22005)) {
            mConf = conf;
        }
    }

    public long getScm() {
        return mScm;
    }

    public boolean getServer() {
        return mServer;
    }

    public void setLs(long ls) {
        if (!ListenerUtil.mutListener.listen(22006)) {
            mLs = ls;
        }
    }

    public void setUsnAfterSync(int usn) {
        if (!ListenerUtil.mutListener.listen(22007)) {
            mUsn = usn;
        }
    }

    public long getMod() {
        return mMod;
    }

    /* this getter is only for syncing routines, use usn() instead elsewhere */
    public int getUsnForSync() {
        return mUsn;
    }

    public Tags getTags() {
        return mTags;
    }

    public long getCrt() {
        return mCrt;
    }

    public Calendar crtCalendar() {
        return Time.calendar((ListenerUtil.mutListener.listen(22011) ? (getCrt() % 1000) : (ListenerUtil.mutListener.listen(22010) ? (getCrt() / 1000) : (ListenerUtil.mutListener.listen(22009) ? (getCrt() - 1000) : (ListenerUtil.mutListener.listen(22008) ? (getCrt() + 1000) : (getCrt() * 1000))))));
    }

    public GregorianCalendar crtGregorianCalendar() {
        return Time.gregorianCalendar((ListenerUtil.mutListener.listen(22015) ? (getCrt() % 1000) : (ListenerUtil.mutListener.listen(22014) ? (getCrt() / 1000) : (ListenerUtil.mutListener.listen(22013) ? (getCrt() - 1000) : (ListenerUtil.mutListener.listen(22012) ? (getCrt() + 1000) : (getCrt() * 1000))))));
    }

    public void setCrt(long crt) {
        if (!ListenerUtil.mutListener.listen(22016)) {
            mCrt = crt;
        }
    }

    public AbstractSched getSched() {
        return mSched;
    }

    public String getPath() {
        return mPath;
    }

    public void setServer(boolean server) {
        if (!ListenerUtil.mutListener.listen(22017)) {
            mServer = server;
        }
    }

    public boolean getDirty() {
        return mDty;
    }

    /**
     * @return The context that created this Collection.
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Not in libAnki
     */
    @CheckResult
    public List<Long> filterToValidCards(long[] cards) {
        return getDb().queryLongList("select id from cards where id in " + Utils.ids2str(cards));
    }

    public int queryVer() throws UnknownDatabaseVersionException {
        try {
            return getDb().queryScalar("select ver from col");
        } catch (Exception e) {
            throw new UnknownDatabaseVersionException(e);
        }
    }

    // This duplicates _loadScheduler (but returns the value and sets the report limit).
    public AbstractSched createScheduler(int reportLimit) {
        int ver = schedVer();
        if (!ListenerUtil.mutListener.listen(22030)) {
            if ((ListenerUtil.mutListener.listen(22022) ? (ver >= 1) : (ListenerUtil.mutListener.listen(22021) ? (ver <= 1) : (ListenerUtil.mutListener.listen(22020) ? (ver > 1) : (ListenerUtil.mutListener.listen(22019) ? (ver < 1) : (ListenerUtil.mutListener.listen(22018) ? (ver != 1) : (ver == 1))))))) {
                if (!ListenerUtil.mutListener.listen(22029)) {
                    mSched = new Sched(this);
                }
            } else if ((ListenerUtil.mutListener.listen(22027) ? (ver >= 2) : (ListenerUtil.mutListener.listen(22026) ? (ver <= 2) : (ListenerUtil.mutListener.listen(22025) ? (ver > 2) : (ListenerUtil.mutListener.listen(22024) ? (ver < 2) : (ListenerUtil.mutListener.listen(22023) ? (ver != 2) : (ver == 2))))))) {
                if (!ListenerUtil.mutListener.listen(22028)) {
                    mSched = new SchedV2(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22031)) {
            mSched.setReportLimit(reportLimit);
        }
        return mSched;
    }

    /**
     * Allows a mock db to be inserted for testing
     */
    @VisibleForTesting
    public void setDb(DB database) {
        if (!ListenerUtil.mutListener.listen(22032)) {
            this.mDb = database;
        }
    }

    public static class CheckDatabaseResult {

        private final List<String> mProblems = new ArrayList<>();

        private final long mOldSize;

        private int mFixedCardsWithNoHomeDeckCount;

        private long mNewSize;

        /**
         * When the database was locked
         */
        private boolean mLocked = false;

        /**
         * When the check failed with an error (or was locked)
         */
        private boolean mFailed = false;

        public CheckDatabaseResult(long oldSize) {
            mOldSize = oldSize;
        }

        public void addAll(List<String> strings) {
            if (!ListenerUtil.mutListener.listen(22033)) {
                mProblems.addAll(strings);
            }
        }

        public void setCardsWithFixedHomeDeckCount(int count) {
            if (!ListenerUtil.mutListener.listen(22034)) {
                this.mFixedCardsWithNoHomeDeckCount = count;
            }
        }

        public boolean hasProblems() {
            return (ListenerUtil.mutListener.listen(22039) ? (mProblems.size() >= 0) : (ListenerUtil.mutListener.listen(22038) ? (mProblems.size() <= 0) : (ListenerUtil.mutListener.listen(22037) ? (mProblems.size() < 0) : (ListenerUtil.mutListener.listen(22036) ? (mProblems.size() != 0) : (ListenerUtil.mutListener.listen(22035) ? (mProblems.size() == 0) : (mProblems.size() > 0))))));
        }

        public List<String> getProblems() {
            return mProblems;
        }

        public int getCardsWithFixedHomeDeckCount() {
            return mFixedCardsWithNoHomeDeckCount;
        }

        public void setNewSize(long size) {
            if (!ListenerUtil.mutListener.listen(22040)) {
                this.mNewSize = size;
            }
        }

        public double getSizeChangeInKb() {
            return (ListenerUtil.mutListener.listen(22048) ? (((ListenerUtil.mutListener.listen(22044) ? (mOldSize % mNewSize) : (ListenerUtil.mutListener.listen(22043) ? (mOldSize / mNewSize) : (ListenerUtil.mutListener.listen(22042) ? (mOldSize * mNewSize) : (ListenerUtil.mutListener.listen(22041) ? (mOldSize + mNewSize) : (mOldSize - mNewSize)))))) % 1024.0) : (ListenerUtil.mutListener.listen(22047) ? (((ListenerUtil.mutListener.listen(22044) ? (mOldSize % mNewSize) : (ListenerUtil.mutListener.listen(22043) ? (mOldSize / mNewSize) : (ListenerUtil.mutListener.listen(22042) ? (mOldSize * mNewSize) : (ListenerUtil.mutListener.listen(22041) ? (mOldSize + mNewSize) : (mOldSize - mNewSize)))))) * 1024.0) : (ListenerUtil.mutListener.listen(22046) ? (((ListenerUtil.mutListener.listen(22044) ? (mOldSize % mNewSize) : (ListenerUtil.mutListener.listen(22043) ? (mOldSize / mNewSize) : (ListenerUtil.mutListener.listen(22042) ? (mOldSize * mNewSize) : (ListenerUtil.mutListener.listen(22041) ? (mOldSize + mNewSize) : (mOldSize - mNewSize)))))) - 1024.0) : (ListenerUtil.mutListener.listen(22045) ? (((ListenerUtil.mutListener.listen(22044) ? (mOldSize % mNewSize) : (ListenerUtil.mutListener.listen(22043) ? (mOldSize / mNewSize) : (ListenerUtil.mutListener.listen(22042) ? (mOldSize * mNewSize) : (ListenerUtil.mutListener.listen(22041) ? (mOldSize + mNewSize) : (mOldSize - mNewSize)))))) + 1024.0) : (((ListenerUtil.mutListener.listen(22044) ? (mOldSize % mNewSize) : (ListenerUtil.mutListener.listen(22043) ? (mOldSize / mNewSize) : (ListenerUtil.mutListener.listen(22042) ? (mOldSize * mNewSize) : (ListenerUtil.mutListener.listen(22041) ? (mOldSize + mNewSize) : (mOldSize - mNewSize)))))) / 1024.0)))));
        }

        public void setFailed(boolean failedIntegrity) {
            if (!ListenerUtil.mutListener.listen(22049)) {
                this.mFailed = failedIntegrity;
            }
        }

        public CheckDatabaseResult markAsFailed() {
            if (!ListenerUtil.mutListener.listen(22050)) {
                this.setFailed(true);
            }
            return this;
        }

        public CheckDatabaseResult markAsLocked() {
            if (!ListenerUtil.mutListener.listen(22051)) {
                this.setLocked(true);
            }
            return markAsFailed();
        }

        private void setLocked(boolean value) {
            if (!ListenerUtil.mutListener.listen(22052)) {
                mLocked = value;
            }
        }

        public boolean getDatabaseLocked() {
            return mLocked;
        }

        public boolean getFailed() {
            return mFailed;
        }
    }

    @NonNull
    public Time getTime() {
        return mTime;
    }
}
