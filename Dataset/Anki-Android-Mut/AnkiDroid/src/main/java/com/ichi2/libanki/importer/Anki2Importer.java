/**
 * ************************************************************************************
 *  Copyright (c) 2012 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2016 Houssam Salem <houssam.salem.au@gmail.com>                        *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki.importer;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.anki.R;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.anki.exception.ImportExportException;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.DB;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Media;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.Storage;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.Deck;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import timber.log.Timber;
import static com.ichi2.libanki.Consts.CARD_TYPE_LRN;
import static com.ichi2.libanki.Consts.CARD_TYPE_NEW;
import static com.ichi2.libanki.Consts.CARD_TYPE_REV;
import static com.ichi2.libanki.Consts.QUEUE_TYPE_DAY_LEARN_RELEARN;
import static com.ichi2.libanki.Consts.QUEUE_TYPE_NEW;
import static com.ichi2.libanki.Consts.QUEUE_TYPE_REV;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.ExcessiveMethodLength", "PMD.SwitchStmtsShouldHaveDefault", "PMD.CollapsibleIfStatements", "PMD.EmptyIfStmt" })
public class Anki2Importer extends Importer {

    private static final int MEDIAPICKLIMIT = 1024;

    private final String mDeckPrefix;

    private final boolean mAllowUpdate;

    private boolean mDupeOnSchemaChange;

    private static class NoteTriple {

        public final long mNid;

        public final long mMid;

        public final long mMod;

        public NoteTriple(long nid, long mod, long mid) {
            mNid = nid;
            mMod = mod;
            mMid = mid;
        }
    }

    private Map<String, NoteTriple> mNotes;

    private Map<Long, Long> mDecks;

    private Map<Long, Long> mModelMap;

    private Set<String> mIgnoredGuids;

    private int mDupes;

    private int mAdded;

    private int mUpdated;

    /**
     * If importing SchedV1 into SchedV2 we need to reset the learning cards
     */
    private boolean mMustResetLearning;

    public Anki2Importer(Collection col, String file) {
        super(col, file);
        if (!ListenerUtil.mutListener.listen(13737)) {
            mNeedMapper = false;
        }
        mDeckPrefix = null;
        mAllowUpdate = true;
        if (!ListenerUtil.mutListener.listen(13738)) {
            mDupeOnSchemaChange = false;
        }
    }

    @Override
    public void run() throws ImportExportException {
        if (!ListenerUtil.mutListener.listen(13739)) {
            publishProgress(0, 0, 0);
        }
        try {
            if (!ListenerUtil.mutListener.listen(13741)) {
                _prepareFiles();
            }
            try {
                if (!ListenerUtil.mutListener.listen(13743)) {
                    _import();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(13742)) {
                    mSrc.close(false);
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(13740)) {
                Timber.e(e, "Exception while importing");
            }
            throw new ImportExportException(e.getMessage());
        }
    }

    private void _prepareFiles() {
        boolean importingV2 = mFile.endsWith(".anki21");
        if (!ListenerUtil.mutListener.listen(13744)) {
            this.mMustResetLearning = false;
        }
        if (!ListenerUtil.mutListener.listen(13745)) {
            mDst = mCol;
        }
        if (!ListenerUtil.mutListener.listen(13746)) {
            mSrc = Storage.Collection(mContext, mFile);
        }
        if (!ListenerUtil.mutListener.listen(13760)) {
            if ((ListenerUtil.mutListener.listen(13752) ? (!importingV2 || (ListenerUtil.mutListener.listen(13751) ? (mCol.schedVer() >= 1) : (ListenerUtil.mutListener.listen(13750) ? (mCol.schedVer() <= 1) : (ListenerUtil.mutListener.listen(13749) ? (mCol.schedVer() > 1) : (ListenerUtil.mutListener.listen(13748) ? (mCol.schedVer() < 1) : (ListenerUtil.mutListener.listen(13747) ? (mCol.schedVer() == 1) : (mCol.schedVer() != 1))))))) : (!importingV2 && (ListenerUtil.mutListener.listen(13751) ? (mCol.schedVer() >= 1) : (ListenerUtil.mutListener.listen(13750) ? (mCol.schedVer() <= 1) : (ListenerUtil.mutListener.listen(13749) ? (mCol.schedVer() > 1) : (ListenerUtil.mutListener.listen(13748) ? (mCol.schedVer() < 1) : (ListenerUtil.mutListener.listen(13747) ? (mCol.schedVer() == 1) : (mCol.schedVer() != 1))))))))) {
                if (!ListenerUtil.mutListener.listen(13759)) {
                    // any scheduling included?
                    if ((ListenerUtil.mutListener.listen(13757) ? (mSrc.getDb().queryScalar("select 1 from cards where queue != " + QUEUE_TYPE_NEW + " limit 1") >= 0) : (ListenerUtil.mutListener.listen(13756) ? (mSrc.getDb().queryScalar("select 1 from cards where queue != " + QUEUE_TYPE_NEW + " limit 1") <= 0) : (ListenerUtil.mutListener.listen(13755) ? (mSrc.getDb().queryScalar("select 1 from cards where queue != " + QUEUE_TYPE_NEW + " limit 1") < 0) : (ListenerUtil.mutListener.listen(13754) ? (mSrc.getDb().queryScalar("select 1 from cards where queue != " + QUEUE_TYPE_NEW + " limit 1") != 0) : (ListenerUtil.mutListener.listen(13753) ? (mSrc.getDb().queryScalar("select 1 from cards where queue != " + QUEUE_TYPE_NEW + " limit 1") == 0) : (mSrc.getDb().queryScalar("select 1 from cards where queue != " + QUEUE_TYPE_NEW + " limit 1") > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(13758)) {
                            this.mMustResetLearning = true;
                        }
                    }
                }
            }
        }
    }

    private void _import() {
        if (!ListenerUtil.mutListener.listen(13761)) {
            mDecks = new HashMap<>(mSrc.getDecks().count());
        }
        try {
            if (!ListenerUtil.mutListener.listen(13765)) {
                // Use transactions for performance and rollbacks in case of error
                mDst.getDb().getDatabase().beginTransaction();
            }
            if (!ListenerUtil.mutListener.listen(13766)) {
                mDst.getMedia().getDb().getDatabase().beginTransaction();
            }
            if (!ListenerUtil.mutListener.listen(13768)) {
                if (!TextUtils.isEmpty(mDeckPrefix)) {
                    long id = mDst.getDecks().id(mDeckPrefix);
                    if (!ListenerUtil.mutListener.listen(13767)) {
                        mDst.getDecks().select(id);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(13769)) {
                Timber.i("Preparing Import");
            }
            if (!ListenerUtil.mutListener.listen(13770)) {
                _prepareTS();
            }
            if (!ListenerUtil.mutListener.listen(13771)) {
                _prepareModels();
            }
            if (!ListenerUtil.mutListener.listen(13772)) {
                Timber.i("Importing notes");
            }
            if (!ListenerUtil.mutListener.listen(13773)) {
                _importNotes();
            }
            if (!ListenerUtil.mutListener.listen(13774)) {
                Timber.i("Importing Cards");
            }
            if (!ListenerUtil.mutListener.listen(13775)) {
                _importCards();
            }
            if (!ListenerUtil.mutListener.listen(13776)) {
                Timber.i("Importing Media");
            }
            if (!ListenerUtil.mutListener.listen(13777)) {
                _importStaticMedia();
            }
            if (!ListenerUtil.mutListener.listen(13778)) {
                publishProgress(100, 100, 25);
            }
            if (!ListenerUtil.mutListener.listen(13779)) {
                Timber.i("Performing post-import");
            }
            if (!ListenerUtil.mutListener.listen(13780)) {
                _postImport();
            }
            if (!ListenerUtil.mutListener.listen(13781)) {
                publishProgress(100, 100, 50);
            }
            if (!ListenerUtil.mutListener.listen(13782)) {
                mDst.getDb().getDatabase().setTransactionSuccessful();
            }
            if (!ListenerUtil.mutListener.listen(13783)) {
                mDst.getMedia().getDb().getDatabase().setTransactionSuccessful();
            }
        } catch (Exception err) {
            if (!ListenerUtil.mutListener.listen(13762)) {
                Timber.e(err, "_import() exception");
            }
            throw err;
        } finally {
            if (!ListenerUtil.mutListener.listen(13763)) {
                // endTransaction throws about invalid transaction even when you check first!
                DB.safeEndInTransaction(mDst.getDb());
            }
            if (!ListenerUtil.mutListener.listen(13764)) {
                DB.safeEndInTransaction(mDst.getMedia().getDb());
            }
        }
        if (!ListenerUtil.mutListener.listen(13784)) {
            Timber.i("Performing vacuum/analyze");
        }
        try {
            if (!ListenerUtil.mutListener.listen(13786)) {
                mDst.getDb().execute("vacuum");
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(13785)) {
                // Allow the import to succeed but recommend the user run check database
                mLog.add(getRes().getString(R.string.import_succeeded_but_check_database, e.getLocalizedMessage()));
            }
        }
        if (!ListenerUtil.mutListener.listen(13787)) {
            publishProgress(100, 100, 65);
        }
        try {
            if (!ListenerUtil.mutListener.listen(13789)) {
                mDst.getDb().execute("analyze");
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(13788)) {
                // Allow the import to succeed but recommend the user run check database
                mLog.add(getRes().getString(R.string.import_succeeded_but_check_database, e.getLocalizedMessage()));
            }
        }
        if (!ListenerUtil.mutListener.listen(13790)) {
            publishProgress(100, 100, 75);
        }
    }

    private void _importNotes() {
        int noteCount = mDst.noteCount();
        if (!ListenerUtil.mutListener.listen(13791)) {
            // build guid -> (id,mod,mid) hash & map of existing note ids
            mNotes = new HashMap<>(noteCount);
        }
        Set<Long> existing = new HashSet<>(noteCount);
        try (Cursor cur = mDst.getDb().query("select id, guid, mod, mid from notes")) {
            if (!ListenerUtil.mutListener.listen(13794)) {
                {
                    long _loopCounter261 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter261", ++_loopCounter261);
                        long id = cur.getLong(0);
                        String guid = cur.getString(1);
                        long mod = cur.getLong(2);
                        long mid = cur.getLong(3);
                        if (!ListenerUtil.mutListener.listen(13792)) {
                            mNotes.put(guid, new NoteTriple(id, mod, mid));
                        }
                        if (!ListenerUtil.mutListener.listen(13793)) {
                            existing.add(id);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13795)) {
            // guids, so we avoid importing invalid cards
            mIgnoredGuids = new HashSet<>();
        }
        // iterate over source collection
        int nbNoteToImport = mSrc.noteCount();
        ArrayList<Object[]> add = new ArrayList<>(nbNoteToImport);
        int totalAddCount = 0;
        final int thresExecAdd = 1000;
        ArrayList<Object[]> update = new ArrayList<>(nbNoteToImport);
        int totalUpdateCount = 0;
        final int thresExecUpdate = 1000;
        ArrayList<Long> dirty = new ArrayList<>(nbNoteToImport);
        int totalDirtyCount = 0;
        final int thresExecDirty = 1000;
        int usn = mDst.usn();
        int dupes = 0;
        ArrayList<String> dupesIgnored = new ArrayList<>(nbNoteToImport);
        if (!ListenerUtil.mutListener.listen(13796)) {
            mDst.getDb().getDatabase().beginTransaction();
        }
        try (Cursor cur = mSrc.getDb().getDatabase().query("select id, guid, mid, mod, tags, flds, sfld, csum, flags, data  from notes", null)) {
            // Counters for progress updates
            int total = cur.getCount();
            boolean largeCollection = (ListenerUtil.mutListener.listen(13802) ? (total >= 200) : (ListenerUtil.mutListener.listen(13801) ? (total <= 200) : (ListenerUtil.mutListener.listen(13800) ? (total < 200) : (ListenerUtil.mutListener.listen(13799) ? (total != 200) : (ListenerUtil.mutListener.listen(13798) ? (total == 200) : (total > 200))))));
            int onePercent = (ListenerUtil.mutListener.listen(13806) ? (total % 100) : (ListenerUtil.mutListener.listen(13805) ? (total * 100) : (ListenerUtil.mutListener.listen(13804) ? (total - 100) : (ListenerUtil.mutListener.listen(13803) ? (total + 100) : (total / 100)))));
            int i = 0;
            if (!ListenerUtil.mutListener.listen(13894)) {
                {
                    long _loopCounter263 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter263", ++_loopCounter263);
                        // turn the db result into a mutable list
                        long nid = cur.getLong(0);
                        String guid = cur.getString(1);
                        long mid = cur.getLong(2);
                        long mod = cur.getLong(3);
                        String tags = cur.getString(4);
                        String flds = cur.getString(5);
                        String sfld = cur.getString(6);
                        long csum = cur.getLong(7);
                        int flag = cur.getInt(8);
                        String data = cur.getString(9);
                        Pair<Boolean, Long> shouldAddAndNewMid = _uniquifyNote(guid, mid);
                        boolean shouldAdd = shouldAddAndNewMid.first;
                        if (!ListenerUtil.mutListener.listen(13807)) {
                            mid = shouldAddAndNewMid.second;
                        }
                        if (!ListenerUtil.mutListener.listen(13835)) {
                            if (shouldAdd) {
                                if (!ListenerUtil.mutListener.listen(13829)) {
                                    {
                                        long _loopCounter262 = 0;
                                        // ensure nid is unique
                                        while (existing.contains(nid)) {
                                            ListenerUtil.loopListener.listen("_loopCounter262", ++_loopCounter262);
                                            if (!ListenerUtil.mutListener.listen(13828)) {
                                                nid += 999;
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(13830)) {
                                    existing.add(nid);
                                }
                                if (!ListenerUtil.mutListener.listen(13831)) {
                                    // update media references in case of dupes
                                    flds = _mungeMedia(mid, flds);
                                }
                                if (!ListenerUtil.mutListener.listen(13832)) {
                                    add.add(new Object[] { nid, guid, mid, mod, usn, tags, flds, sfld, csum, flag, data });
                                }
                                if (!ListenerUtil.mutListener.listen(13833)) {
                                    dirty.add(nid);
                                }
                                if (!ListenerUtil.mutListener.listen(13834)) {
                                    // note we have the added guid
                                    mNotes.put(guid, new NoteTriple(nid, mod, mid));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(13808)) {
                                    // a duplicate or changed schema - safe to update?
                                    dupes += 1;
                                }
                                if (!ListenerUtil.mutListener.listen(13827)) {
                                    if (mAllowUpdate) {
                                        NoteTriple n = mNotes.get(guid);
                                        long oldNid = n.mNid;
                                        long oldMod = n.mMod;
                                        long oldMid = n.mMid;
                                        if (!ListenerUtil.mutListener.listen(13826)) {
                                            // will update if incoming note more recent
                                            if ((ListenerUtil.mutListener.listen(13813) ? (oldMod >= mod) : (ListenerUtil.mutListener.listen(13812) ? (oldMod <= mod) : (ListenerUtil.mutListener.listen(13811) ? (oldMod > mod) : (ListenerUtil.mutListener.listen(13810) ? (oldMod != mod) : (ListenerUtil.mutListener.listen(13809) ? (oldMod == mod) : (oldMod < mod))))))) {
                                                if (!ListenerUtil.mutListener.listen(13825)) {
                                                    // safe if note types identical
                                                    if ((ListenerUtil.mutListener.listen(13818) ? (oldMid >= mid) : (ListenerUtil.mutListener.listen(13817) ? (oldMid <= mid) : (ListenerUtil.mutListener.listen(13816) ? (oldMid > mid) : (ListenerUtil.mutListener.listen(13815) ? (oldMid < mid) : (ListenerUtil.mutListener.listen(13814) ? (oldMid != mid) : (oldMid == mid))))))) {
                                                        if (!ListenerUtil.mutListener.listen(13821)) {
                                                            // incoming note should use existing id
                                                            nid = oldNid;
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(13822)) {
                                                            flds = _mungeMedia(mid, flds);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(13823)) {
                                                            update.add(new Object[] { nid, guid, mid, mod, usn, tags, flds, sfld, csum, flag, data });
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(13824)) {
                                                            dirty.add(nid);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(13819)) {
                                                            dupesIgnored.add(String.format("%s: %s", mCol.getModels().get(oldMid).getString("name"), flds.replace('\u001f', ',')));
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(13820)) {
                                                            mIgnoredGuids.add(guid);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13836)) {
                            i++;
                        }
                        if (!ListenerUtil.mutListener.listen(13846)) {
                            // add to col partially, so as to avoid OOM
                            if ((ListenerUtil.mutListener.listen(13841) ? (add.size() <= thresExecAdd) : (ListenerUtil.mutListener.listen(13840) ? (add.size() > thresExecAdd) : (ListenerUtil.mutListener.listen(13839) ? (add.size() < thresExecAdd) : (ListenerUtil.mutListener.listen(13838) ? (add.size() != thresExecAdd) : (ListenerUtil.mutListener.listen(13837) ? (add.size() == thresExecAdd) : (add.size() >= thresExecAdd))))))) {
                                if (!ListenerUtil.mutListener.listen(13842)) {
                                    totalAddCount += add.size();
                                }
                                if (!ListenerUtil.mutListener.listen(13843)) {
                                    addNotes(add);
                                }
                                if (!ListenerUtil.mutListener.listen(13844)) {
                                    add.clear();
                                }
                                if (!ListenerUtil.mutListener.listen(13845)) {
                                    Timber.d("add notes: %d", totalAddCount);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13856)) {
                            // add to col partially, so as to avoid OOM
                            if ((ListenerUtil.mutListener.listen(13851) ? (update.size() <= thresExecUpdate) : (ListenerUtil.mutListener.listen(13850) ? (update.size() > thresExecUpdate) : (ListenerUtil.mutListener.listen(13849) ? (update.size() < thresExecUpdate) : (ListenerUtil.mutListener.listen(13848) ? (update.size() != thresExecUpdate) : (ListenerUtil.mutListener.listen(13847) ? (update.size() == thresExecUpdate) : (update.size() >= thresExecUpdate))))))) {
                                if (!ListenerUtil.mutListener.listen(13852)) {
                                    totalUpdateCount += update.size();
                                }
                                if (!ListenerUtil.mutListener.listen(13853)) {
                                    updateNotes(update);
                                }
                                if (!ListenerUtil.mutListener.listen(13854)) {
                                    update.clear();
                                }
                                if (!ListenerUtil.mutListener.listen(13855)) {
                                    Timber.d("update notes: %d", totalUpdateCount);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13867)) {
                            // add to col partially, so as to avoid OOM
                            if ((ListenerUtil.mutListener.listen(13861) ? (dirty.size() <= thresExecDirty) : (ListenerUtil.mutListener.listen(13860) ? (dirty.size() > thresExecDirty) : (ListenerUtil.mutListener.listen(13859) ? (dirty.size() < thresExecDirty) : (ListenerUtil.mutListener.listen(13858) ? (dirty.size() != thresExecDirty) : (ListenerUtil.mutListener.listen(13857) ? (dirty.size() == thresExecDirty) : (dirty.size() >= thresExecDirty))))))) {
                                if (!ListenerUtil.mutListener.listen(13862)) {
                                    totalDirtyCount += dirty.size();
                                }
                                if (!ListenerUtil.mutListener.listen(13863)) {
                                    mDst.updateFieldCache(dirty);
                                }
                                if (!ListenerUtil.mutListener.listen(13864)) {
                                    mDst.getTags().registerNotes(dirty);
                                }
                                if (!ListenerUtil.mutListener.listen(13865)) {
                                    dirty.clear();
                                }
                                if (!ListenerUtil.mutListener.listen(13866)) {
                                    Timber.d("dirty notes: %d", totalDirtyCount);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13893)) {
                            if ((ListenerUtil.mutListener.listen(13883) ? ((ListenerUtil.mutListener.listen(13872) ? (total >= 0) : (ListenerUtil.mutListener.listen(13871) ? (total <= 0) : (ListenerUtil.mutListener.listen(13870) ? (total > 0) : (ListenerUtil.mutListener.listen(13869) ? (total < 0) : (ListenerUtil.mutListener.listen(13868) ? (total == 0) : (total != 0)))))) || ((ListenerUtil.mutListener.listen(13882) ? (!largeCollection && (ListenerUtil.mutListener.listen(13881) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(13880) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(13879) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(13878) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(13877) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) == 0))))))) : (!largeCollection || (ListenerUtil.mutListener.listen(13881) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(13880) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(13879) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(13878) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(13877) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) == 0)))))))))) : ((ListenerUtil.mutListener.listen(13872) ? (total >= 0) : (ListenerUtil.mutListener.listen(13871) ? (total <= 0) : (ListenerUtil.mutListener.listen(13870) ? (total > 0) : (ListenerUtil.mutListener.listen(13869) ? (total < 0) : (ListenerUtil.mutListener.listen(13868) ? (total == 0) : (total != 0)))))) && ((ListenerUtil.mutListener.listen(13882) ? (!largeCollection && (ListenerUtil.mutListener.listen(13881) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(13880) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(13879) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(13878) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(13877) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) == 0))))))) : (!largeCollection || (ListenerUtil.mutListener.listen(13881) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(13880) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(13879) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(13878) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(13877) ? ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(13876) ? (i / onePercent) : (ListenerUtil.mutListener.listen(13875) ? (i * onePercent) : (ListenerUtil.mutListener.listen(13874) ? (i - onePercent) : (ListenerUtil.mutListener.listen(13873) ? (i + onePercent) : (i % onePercent))))) == 0)))))))))))) {
                                if (!ListenerUtil.mutListener.listen(13892)) {
                                    // Calls to publishProgress are reasonably expensive due to res.getString()
                                    publishProgress((ListenerUtil.mutListener.listen(13891) ? ((ListenerUtil.mutListener.listen(13887) ? (i % 100) : (ListenerUtil.mutListener.listen(13886) ? (i / 100) : (ListenerUtil.mutListener.listen(13885) ? (i - 100) : (ListenerUtil.mutListener.listen(13884) ? (i + 100) : (i * 100))))) % total) : (ListenerUtil.mutListener.listen(13890) ? ((ListenerUtil.mutListener.listen(13887) ? (i % 100) : (ListenerUtil.mutListener.listen(13886) ? (i / 100) : (ListenerUtil.mutListener.listen(13885) ? (i - 100) : (ListenerUtil.mutListener.listen(13884) ? (i + 100) : (i * 100))))) * total) : (ListenerUtil.mutListener.listen(13889) ? ((ListenerUtil.mutListener.listen(13887) ? (i % 100) : (ListenerUtil.mutListener.listen(13886) ? (i / 100) : (ListenerUtil.mutListener.listen(13885) ? (i - 100) : (ListenerUtil.mutListener.listen(13884) ? (i + 100) : (i * 100))))) - total) : (ListenerUtil.mutListener.listen(13888) ? ((ListenerUtil.mutListener.listen(13887) ? (i % 100) : (ListenerUtil.mutListener.listen(13886) ? (i / 100) : (ListenerUtil.mutListener.listen(13885) ? (i - 100) : (ListenerUtil.mutListener.listen(13884) ? (i + 100) : (i * 100))))) + total) : ((ListenerUtil.mutListener.listen(13887) ? (i % 100) : (ListenerUtil.mutListener.listen(13886) ? (i / 100) : (ListenerUtil.mutListener.listen(13885) ? (i - 100) : (ListenerUtil.mutListener.listen(13884) ? (i + 100) : (i * 100))))) / total))))), 0, 0);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(13895)) {
                publishProgress(100, 0, 0);
            }
            if (!ListenerUtil.mutListener.listen(13896)) {
                // summarize partial add/update/dirty results for total values
                totalAddCount += add.size();
            }
            if (!ListenerUtil.mutListener.listen(13897)) {
                totalUpdateCount += update.size();
            }
            if (!ListenerUtil.mutListener.listen(13898)) {
                totalDirtyCount += dirty.size();
            }
            if (!ListenerUtil.mutListener.listen(13912)) {
                if ((ListenerUtil.mutListener.listen(13903) ? (dupes >= 0) : (ListenerUtil.mutListener.listen(13902) ? (dupes <= 0) : (ListenerUtil.mutListener.listen(13901) ? (dupes < 0) : (ListenerUtil.mutListener.listen(13900) ? (dupes != 0) : (ListenerUtil.mutListener.listen(13899) ? (dupes == 0) : (dupes > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(13904)) {
                        mLog.add(getRes().getString(R.string.import_update_details, totalUpdateCount, dupes));
                    }
                    if (!ListenerUtil.mutListener.listen(13911)) {
                        if ((ListenerUtil.mutListener.listen(13909) ? (dupesIgnored.size() >= 0) : (ListenerUtil.mutListener.listen(13908) ? (dupesIgnored.size() <= 0) : (ListenerUtil.mutListener.listen(13907) ? (dupesIgnored.size() < 0) : (ListenerUtil.mutListener.listen(13906) ? (dupesIgnored.size() != 0) : (ListenerUtil.mutListener.listen(13905) ? (dupesIgnored.size() == 0) : (dupesIgnored.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(13910)) {
                                mLog.add(getRes().getString(R.string.import_update_ignored));
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(13913)) {
                // export info for calling code
                mDupes = dupes;
            }
            if (!ListenerUtil.mutListener.listen(13914)) {
                mAdded = totalAddCount;
            }
            if (!ListenerUtil.mutListener.listen(13915)) {
                mUpdated = totalUpdateCount;
            }
            if (!ListenerUtil.mutListener.listen(13916)) {
                Timber.d("add notes total:    %d", totalAddCount);
            }
            if (!ListenerUtil.mutListener.listen(13917)) {
                Timber.d("update notes total: %d", totalUpdateCount);
            }
            if (!ListenerUtil.mutListener.listen(13918)) {
                Timber.d("dirty notes total:  %d", totalDirtyCount);
            }
            if (!ListenerUtil.mutListener.listen(13919)) {
                // add to col (for last chunk)
                addNotes(add);
            }
            if (!ListenerUtil.mutListener.listen(13920)) {
                add.clear();
            }
            if (!ListenerUtil.mutListener.listen(13921)) {
                updateNotes(update);
            }
            if (!ListenerUtil.mutListener.listen(13922)) {
                update.clear();
            }
            if (!ListenerUtil.mutListener.listen(13923)) {
                mDst.getDb().getDatabase().setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(13797)) {
                DB.safeEndInTransaction(mDst.getDb());
            }
        }
        if (!ListenerUtil.mutListener.listen(13924)) {
            mDst.updateFieldCache(dirty);
        }
        if (!ListenerUtil.mutListener.listen(13925)) {
            mDst.getTags().registerNotes(dirty);
        }
    }

    private void addNotes(List<Object[]> add) {
        if (!ListenerUtil.mutListener.listen(13926)) {
            mDst.getDb().executeManyNoTransaction("insert or replace into notes values (?,?,?,?,?,?,?,?,?,?,?)", add);
        }
    }

    private void updateNotes(List<Object[]> update) {
        if (!ListenerUtil.mutListener.listen(13927)) {
            mDst.getDb().executeManyNoTransaction("insert or replace into notes values (?,?,?,?,?,?,?,?,?,?,?)", update);
        }
    }

    // returns true if note should be added and its mid
    private Pair<Boolean, Long> _uniquifyNote(@NonNull String origGuid, long srcMid) {
        long dstMid = _mid(srcMid);
        if (!ListenerUtil.mutListener.listen(13933)) {
            // duplicate Schemas?
            if ((ListenerUtil.mutListener.listen(13932) ? (srcMid >= dstMid) : (ListenerUtil.mutListener.listen(13931) ? (srcMid <= dstMid) : (ListenerUtil.mutListener.listen(13930) ? (srcMid > dstMid) : (ListenerUtil.mutListener.listen(13929) ? (srcMid < dstMid) : (ListenerUtil.mutListener.listen(13928) ? (srcMid != dstMid) : (srcMid == dstMid))))))) {
                return new Pair<>(!mNotes.containsKey(origGuid), srcMid);
            }
        }
        if (!ListenerUtil.mutListener.listen(13934)) {
            // differing schemas and note doesn't exist?
            if (!mNotes.containsKey(origGuid)) {
                return new Pair<>(true, dstMid);
            }
        }
        if (!ListenerUtil.mutListener.listen(13935)) {
            // schema changed; don't import
            mIgnoredGuids.add(origGuid);
        }
        return new Pair<>(false, dstMid);
    }

    /**
     * Prepare index of schema hashes.
     */
    private void _prepareModels() {
        if (!ListenerUtil.mutListener.listen(13936)) {
            mModelMap = new HashMap<>(mSrc.getModels().count());
        }
    }

    /**
     * Return local id for remote MID.
     */
    private long _mid(long srcMid) {
        if (!ListenerUtil.mutListener.listen(13937)) {
            // already processed this mid?
            if (mModelMap.containsKey(srcMid)) {
                return mModelMap.get(srcMid);
            }
        }
        long mid = srcMid;
        Model srcModel = mSrc.getModels().get(srcMid);
        String srcScm = mSrc.getModels().scmhash(srcModel);
        if (!ListenerUtil.mutListener.listen(13949)) {
            {
                long _loopCounter264 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter264", ++_loopCounter264);
                    if (!ListenerUtil.mutListener.listen(13942)) {
                        // missing from target col?
                        if (!mDst.getModels().have(mid)) {
                            // copy it over
                            Model model = srcModel.deepClone();
                            if (!ListenerUtil.mutListener.listen(13938)) {
                                model.put("id", mid);
                            }
                            if (!ListenerUtil.mutListener.listen(13939)) {
                                model.put("mod", mCol.getTime().intTime());
                            }
                            if (!ListenerUtil.mutListener.listen(13940)) {
                                model.put("usn", mCol.usn());
                            }
                            if (!ListenerUtil.mutListener.listen(13941)) {
                                mDst.getModels().update(model);
                            }
                            break;
                        }
                    }
                    // there's an existing model; do the schemas match?
                    Model dstModel = mDst.getModels().get(mid);
                    String dstScm = mDst.getModels().scmhash(dstModel);
                    if (!ListenerUtil.mutListener.listen(13947)) {
                        if (srcScm.equals(dstScm)) {
                            // they do; we can reuse this mid
                            Model model = srcModel.deepClone();
                            if (!ListenerUtil.mutListener.listen(13943)) {
                                model.put("id", mid);
                            }
                            if (!ListenerUtil.mutListener.listen(13944)) {
                                model.put("mod", mCol.getTime().intTime());
                            }
                            if (!ListenerUtil.mutListener.listen(13945)) {
                                model.put("usn", mCol.usn());
                            }
                            if (!ListenerUtil.mutListener.listen(13946)) {
                                mDst.getModels().update(model);
                            }
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13948)) {
                        // as they don't match, try next id
                        mid += 1;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13950)) {
            // save map and return new mid
            mModelMap.put(srcMid, mid);
        }
        return mid;
    }

    /**
     * Given did in src col, return local id.
     */
    private long _did(long did) {
        if (!ListenerUtil.mutListener.listen(13951)) {
            // already converted?
            if (mDecks.containsKey(did)) {
                return mDecks.get(did);
            }
        }
        // get the name in src
        Deck g = mSrc.getDecks().get(did);
        String name = g.getString("name");
        if (!ListenerUtil.mutListener.listen(13955)) {
            // if there's a prefix, replace the top level deck
            if (!TextUtils.isEmpty(mDeckPrefix)) {
                List<String> parts = Arrays.asList(Decks.path(name));
                String tmpname = TextUtils.join("::", parts.subList(1, parts.size()));
                if (!ListenerUtil.mutListener.listen(13952)) {
                    name = mDeckPrefix;
                }
                if (!ListenerUtil.mutListener.listen(13954)) {
                    if (!TextUtils.isEmpty(tmpname)) {
                        if (!ListenerUtil.mutListener.listen(13953)) {
                            name += "::" + tmpname;
                        }
                    }
                }
            }
        }
        // Manually create any parents so we can pull in descriptions
        String head = "";
        List<String> parents = Arrays.asList(Decks.path(name));
        if (!ListenerUtil.mutListener.listen(13964)) {
            {
                long _loopCounter265 = 0;
                for (String parent : parents.subList(0, (ListenerUtil.mutListener.listen(13963) ? (parents.size() % 1) : (ListenerUtil.mutListener.listen(13962) ? (parents.size() / 1) : (ListenerUtil.mutListener.listen(13961) ? (parents.size() * 1) : (ListenerUtil.mutListener.listen(13960) ? (parents.size() + 1) : (parents.size() - 1))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter265", ++_loopCounter265);
                    if (!ListenerUtil.mutListener.listen(13957)) {
                        if (!TextUtils.isEmpty(head)) {
                            if (!ListenerUtil.mutListener.listen(13956)) {
                                head += "::";
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13958)) {
                        head += parent;
                    }
                    long idInSrc = mSrc.getDecks().id(head);
                    if (!ListenerUtil.mutListener.listen(13959)) {
                        _did(idInSrc);
                    }
                }
            }
        }
        // create in local
        long newid = mDst.getDecks().id(name);
        if (!ListenerUtil.mutListener.listen(13970)) {
            // pull conf over
            if ((ListenerUtil.mutListener.listen(13965) ? (g.has("conf") || g.getLong("conf") != 1) : (g.has("conf") && g.getLong("conf") != 1))) {
                DeckConfig conf = mSrc.getDecks().getConf(g.getLong("conf"));
                if (!ListenerUtil.mutListener.listen(13966)) {
                    mDst.getDecks().save(conf);
                }
                if (!ListenerUtil.mutListener.listen(13967)) {
                    mDst.getDecks().updateConf(conf);
                }
                Deck g2 = mDst.getDecks().get(newid);
                if (!ListenerUtil.mutListener.listen(13968)) {
                    g2.put("conf", g.getLong("conf"));
                }
                if (!ListenerUtil.mutListener.listen(13969)) {
                    mDst.getDecks().save(g2);
                }
            }
        }
        // save desc
        Deck deck = mDst.getDecks().get(newid);
        if (!ListenerUtil.mutListener.listen(13971)) {
            deck.put("desc", g.getString("desc"));
        }
        if (!ListenerUtil.mutListener.listen(13972)) {
            mDst.getDecks().save(deck);
        }
        if (!ListenerUtil.mutListener.listen(13973)) {
            // add to deck map and return
            mDecks.put(did, newid);
        }
        return newid;
    }

    private void _importCards() {
        if (!ListenerUtil.mutListener.listen(13975)) {
            if (mMustResetLearning) {
                try {
                    if (!ListenerUtil.mutListener.listen(13974)) {
                        mSrc.changeSchedulerVer(2);
                    }
                } catch (ConfirmModSchemaException e) {
                    throw new RuntimeException("Changing the scheduler of an import should not cause schema modification", e);
                }
            }
        }
        /*
         * Since we can't use a tuple as a key in Java, we resort to indexing twice with nested maps.
         * Python: (guid, ord) -> cid
         * Java: guid -> ord -> cid
         */
        int nbCard = mDst.cardCount();
        Map<String, Map<Integer, Long>> mCards = new HashMap<>(nbCard);
        Set<Long> existing = new HashSet<>(nbCard);
        try (Cursor cur = mDst.getDb().query("select f.guid, c.ord, c.id from cards c, notes f " + "where c.nid = f.id")) {
            if (!ListenerUtil.mutListener.listen(13981)) {
                {
                    long _loopCounter266 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter266", ++_loopCounter266);
                        String guid = cur.getString(0);
                        int ord = cur.getInt(1);
                        long cid = cur.getLong(2);
                        if (!ListenerUtil.mutListener.listen(13976)) {
                            existing.add(cid);
                        }
                        if (!ListenerUtil.mutListener.listen(13980)) {
                            if (mCards.containsKey(guid)) {
                                if (!ListenerUtil.mutListener.listen(13979)) {
                                    mCards.get(guid).put(ord, cid);
                                }
                            } else {
                                // The size is at most the number of card type in the note type.
                                Map<Integer, Long> map = new HashMap<>();
                                if (!ListenerUtil.mutListener.listen(13977)) {
                                    map.put(ord, cid);
                                }
                                if (!ListenerUtil.mutListener.listen(13978)) {
                                    mCards.put(guid, map);
                                }
                            }
                        }
                    }
                }
            }
        }
        // loop through src
        int nbCardsToImport = mSrc.cardCount();
        List<Object[]> cards = new ArrayList<>(nbCardsToImport);
        int totalCardCount = 0;
        final int thresExecCards = 1000;
        List<Object[]> revlog = new ArrayList<>(mSrc.getSched().logCount());
        int totalRevlogCount = 0;
        final int thresExecRevlog = 1000;
        int usn = mDst.usn();
        long aheadBy = (ListenerUtil.mutListener.listen(13985) ? (mSrc.getSched().getToday() % mDst.getSched().getToday()) : (ListenerUtil.mutListener.listen(13984) ? (mSrc.getSched().getToday() / mDst.getSched().getToday()) : (ListenerUtil.mutListener.listen(13983) ? (mSrc.getSched().getToday() * mDst.getSched().getToday()) : (ListenerUtil.mutListener.listen(13982) ? (mSrc.getSched().getToday() + mDst.getSched().getToday()) : (mSrc.getSched().getToday() - mDst.getSched().getToday())))));
        if (!ListenerUtil.mutListener.listen(13986)) {
            mDst.getDb().getDatabase().beginTransaction();
        }
        try (Cursor cur = mSrc.getDb().query("select f.guid, c.id, c.did, c.ord, c.type, c.queue, c.due, c.ivl, c.factor, c.reps, c.lapses, c.left, c.odue, c.odid, c.flags, c.data from cards c, notes f " + "where c.nid = f.id")) {
            // Counters for progress updates
            int total = cur.getCount();
            boolean largeCollection = (ListenerUtil.mutListener.listen(13992) ? (total >= 200) : (ListenerUtil.mutListener.listen(13991) ? (total <= 200) : (ListenerUtil.mutListener.listen(13990) ? (total < 200) : (ListenerUtil.mutListener.listen(13989) ? (total != 200) : (ListenerUtil.mutListener.listen(13988) ? (total == 200) : (total > 200))))));
            int onePercent = (ListenerUtil.mutListener.listen(13996) ? (total % 100) : (ListenerUtil.mutListener.listen(13995) ? (total * 100) : (ListenerUtil.mutListener.listen(13994) ? (total - 100) : (ListenerUtil.mutListener.listen(13993) ? (total + 100) : (total / 100)))));
            int i = 0;
            if (!ListenerUtil.mutListener.listen(14107)) {
                {
                    long _loopCounter269 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter269", ++_loopCounter269);
                        String guid = cur.getString(0);
                        long cid = cur.getLong(1);
                        // To keep track of card id in source
                        long scid = cid;
                        long did = cur.getLong(2);
                        int ord = cur.getInt(3);
                        @Consts.CARD_TYPE
                        int type = cur.getInt(4);
                        @Consts.CARD_QUEUE
                        int queue = cur.getInt(5);
                        long due = cur.getLong(6);
                        long ivl = cur.getLong(7);
                        long factor = cur.getLong(8);
                        int reps = cur.getInt(9);
                        int lapses = cur.getInt(10);
                        int left = cur.getInt(11);
                        long odue = cur.getLong(12);
                        long odid = cur.getLong(13);
                        int flags = cur.getInt(14);
                        String data = cur.getString(15);
                        if (!ListenerUtil.mutListener.listen(13997)) {
                            if (mIgnoredGuids.contains(guid)) {
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13998)) {
                            // does the card's note exist in dst col?
                            if (!mNotes.containsKey(guid)) {
                                continue;
                            }
                        }
                        NoteTriple dnid = mNotes.get(guid);
                        if (!ListenerUtil.mutListener.listen(14000)) {
                            // does the card already exist in the dst col?
                            if ((ListenerUtil.mutListener.listen(13999) ? (mCards.containsKey(guid) || mCards.get(guid).containsKey(ord)) : (mCards.containsKey(guid) && mCards.get(guid).containsKey(ord)))) {
                                // fixme: in future, could update if newer mod time
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14002)) {
                            {
                                long _loopCounter267 = 0;
                                // ensure the card id is unique
                                while (existing.contains(cid)) {
                                    ListenerUtil.loopListener.listen("_loopCounter267", ++_loopCounter267);
                                    if (!ListenerUtil.mutListener.listen(14001)) {
                                        cid += 999;
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14003)) {
                            existing.add(cid);
                        }
                        // update cid, nid, etc
                        long nid = mNotes.get(guid).mNid;
                        if (!ListenerUtil.mutListener.listen(14004)) {
                            did = _did(did);
                        }
                        long mod = mCol.getTime().intTime();
                        if (!ListenerUtil.mutListener.listen(14023)) {
                            // review cards have a due date relative to collection
                            if ((ListenerUtil.mutListener.listen(14021) ? ((ListenerUtil.mutListener.listen(14015) ? ((ListenerUtil.mutListener.listen(14009) ? (queue >= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14008) ? (queue <= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14007) ? (queue > QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14006) ? (queue < QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14005) ? (queue != QUEUE_TYPE_REV) : (queue == QUEUE_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(14014) ? (queue >= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14013) ? (queue <= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14012) ? (queue > QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14011) ? (queue < QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14010) ? (queue != QUEUE_TYPE_DAY_LEARN_RELEARN) : (queue == QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(14009) ? (queue >= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14008) ? (queue <= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14007) ? (queue > QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14006) ? (queue < QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14005) ? (queue != QUEUE_TYPE_REV) : (queue == QUEUE_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(14014) ? (queue >= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14013) ? (queue <= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14012) ? (queue > QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14011) ? (queue < QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14010) ? (queue != QUEUE_TYPE_DAY_LEARN_RELEARN) : (queue == QUEUE_TYPE_DAY_LEARN_RELEARN)))))))) && (ListenerUtil.mutListener.listen(14020) ? (type >= CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14019) ? (type <= CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14018) ? (type > CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14017) ? (type < CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14016) ? (type != CARD_TYPE_REV) : (type == CARD_TYPE_REV))))))) : ((ListenerUtil.mutListener.listen(14015) ? ((ListenerUtil.mutListener.listen(14009) ? (queue >= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14008) ? (queue <= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14007) ? (queue > QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14006) ? (queue < QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14005) ? (queue != QUEUE_TYPE_REV) : (queue == QUEUE_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(14014) ? (queue >= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14013) ? (queue <= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14012) ? (queue > QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14011) ? (queue < QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14010) ? (queue != QUEUE_TYPE_DAY_LEARN_RELEARN) : (queue == QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(14009) ? (queue >= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14008) ? (queue <= QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14007) ? (queue > QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14006) ? (queue < QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14005) ? (queue != QUEUE_TYPE_REV) : (queue == QUEUE_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(14014) ? (queue >= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14013) ? (queue <= QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14012) ? (queue > QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14011) ? (queue < QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14010) ? (queue != QUEUE_TYPE_DAY_LEARN_RELEARN) : (queue == QUEUE_TYPE_DAY_LEARN_RELEARN)))))))) || (ListenerUtil.mutListener.listen(14020) ? (type >= CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14019) ? (type <= CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14018) ? (type > CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14017) ? (type < CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14016) ? (type != CARD_TYPE_REV) : (type == CARD_TYPE_REV))))))))) {
                                if (!ListenerUtil.mutListener.listen(14022)) {
                                    due -= aheadBy;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14030)) {
                            // odue needs updating too
                            if ((ListenerUtil.mutListener.listen(14028) ? (odue >= 0) : (ListenerUtil.mutListener.listen(14027) ? (odue <= 0) : (ListenerUtil.mutListener.listen(14026) ? (odue > 0) : (ListenerUtil.mutListener.listen(14025) ? (odue < 0) : (ListenerUtil.mutListener.listen(14024) ? (odue == 0) : (odue != 0))))))) {
                                if (!ListenerUtil.mutListener.listen(14029)) {
                                    odue -= aheadBy;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14054)) {
                            // if odid true, convert card from filtered to normal
                            if ((ListenerUtil.mutListener.listen(14035) ? (odid >= 0) : (ListenerUtil.mutListener.listen(14034) ? (odid <= 0) : (ListenerUtil.mutListener.listen(14033) ? (odid > 0) : (ListenerUtil.mutListener.listen(14032) ? (odid < 0) : (ListenerUtil.mutListener.listen(14031) ? (odid == 0) : (odid != 0))))))) {
                                if (!ListenerUtil.mutListener.listen(14036)) {
                                    // odid
                                    odid = 0;
                                }
                                if (!ListenerUtil.mutListener.listen(14037)) {
                                    // odue
                                    due = odue;
                                }
                                if (!ListenerUtil.mutListener.listen(14038)) {
                                    odue = 0;
                                }
                                if (!ListenerUtil.mutListener.listen(14046)) {
                                    // queue
                                    if ((ListenerUtil.mutListener.listen(14043) ? (type >= CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14042) ? (type <= CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14041) ? (type > CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14040) ? (type < CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14039) ? (type != CARD_TYPE_LRN) : (type == CARD_TYPE_LRN))))))) {
                                        if (!ListenerUtil.mutListener.listen(14045)) {
                                            // type
                                            queue = QUEUE_TYPE_NEW;
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(14044)) {
                                            queue = type;
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(14053)) {
                                    // type
                                    if ((ListenerUtil.mutListener.listen(14051) ? (type >= CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14050) ? (type <= CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14049) ? (type > CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14048) ? (type < CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14047) ? (type != CARD_TYPE_LRN) : (type == CARD_TYPE_LRN))))))) {
                                        if (!ListenerUtil.mutListener.listen(14052)) {
                                            type = CARD_TYPE_NEW;
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14055)) {
                            cards.add(new Object[] { cid, nid, did, ord, mod, usn, type, queue, due, ivl, factor, reps, lapses, left, odue, odid, flags, data });
                        }
                        // we need to import revlog, rewriting card ids and bumping usn
                        try (Cursor cur2 = mSrc.getDb().query("select * from revlog where cid = " + scid)) {
                            if (!ListenerUtil.mutListener.listen(14059)) {
                                {
                                    long _loopCounter268 = 0;
                                    while (cur2.moveToNext()) {
                                        ListenerUtil.loopListener.listen("_loopCounter268", ++_loopCounter268);
                                        Object[] rev = new Object[] { cur2.getLong(0), cur2.getLong(1), cur2.getInt(2), cur2.getInt(3), cur2.getLong(4), cur2.getLong(5), cur2.getLong(6), cur2.getLong(7), cur2.getInt(8) };
                                        if (!ListenerUtil.mutListener.listen(14056)) {
                                            rev[1] = cid;
                                        }
                                        if (!ListenerUtil.mutListener.listen(14057)) {
                                            rev[2] = mDst.usn();
                                        }
                                        if (!ListenerUtil.mutListener.listen(14058)) {
                                            revlog.add(rev);
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14060)) {
                            i++;
                        }
                        if (!ListenerUtil.mutListener.listen(14070)) {
                            // apply card changes partially
                            if ((ListenerUtil.mutListener.listen(14065) ? (cards.size() <= thresExecCards) : (ListenerUtil.mutListener.listen(14064) ? (cards.size() > thresExecCards) : (ListenerUtil.mutListener.listen(14063) ? (cards.size() < thresExecCards) : (ListenerUtil.mutListener.listen(14062) ? (cards.size() != thresExecCards) : (ListenerUtil.mutListener.listen(14061) ? (cards.size() == thresExecCards) : (cards.size() >= thresExecCards))))))) {
                                if (!ListenerUtil.mutListener.listen(14066)) {
                                    totalCardCount += cards.size();
                                }
                                if (!ListenerUtil.mutListener.listen(14067)) {
                                    insertCards(cards);
                                }
                                if (!ListenerUtil.mutListener.listen(14068)) {
                                    cards.clear();
                                }
                                if (!ListenerUtil.mutListener.listen(14069)) {
                                    Timber.d("add cards: %d", totalCardCount);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14080)) {
                            // apply revlog changes partially
                            if ((ListenerUtil.mutListener.listen(14075) ? (revlog.size() <= thresExecRevlog) : (ListenerUtil.mutListener.listen(14074) ? (revlog.size() > thresExecRevlog) : (ListenerUtil.mutListener.listen(14073) ? (revlog.size() < thresExecRevlog) : (ListenerUtil.mutListener.listen(14072) ? (revlog.size() != thresExecRevlog) : (ListenerUtil.mutListener.listen(14071) ? (revlog.size() == thresExecRevlog) : (revlog.size() >= thresExecRevlog))))))) {
                                if (!ListenerUtil.mutListener.listen(14076)) {
                                    totalRevlogCount += revlog.size();
                                }
                                if (!ListenerUtil.mutListener.listen(14077)) {
                                    insertRevlog(revlog);
                                }
                                if (!ListenerUtil.mutListener.listen(14078)) {
                                    revlog.clear();
                                }
                                if (!ListenerUtil.mutListener.listen(14079)) {
                                    Timber.d("add revlog: %d", totalRevlogCount);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14106)) {
                            if ((ListenerUtil.mutListener.listen(14096) ? ((ListenerUtil.mutListener.listen(14085) ? (total >= 0) : (ListenerUtil.mutListener.listen(14084) ? (total <= 0) : (ListenerUtil.mutListener.listen(14083) ? (total > 0) : (ListenerUtil.mutListener.listen(14082) ? (total < 0) : (ListenerUtil.mutListener.listen(14081) ? (total == 0) : (total != 0)))))) || ((ListenerUtil.mutListener.listen(14095) ? (!largeCollection && (ListenerUtil.mutListener.listen(14094) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(14093) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(14092) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(14091) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(14090) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) == 0))))))) : (!largeCollection || (ListenerUtil.mutListener.listen(14094) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(14093) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(14092) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(14091) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(14090) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) == 0)))))))))) : ((ListenerUtil.mutListener.listen(14085) ? (total >= 0) : (ListenerUtil.mutListener.listen(14084) ? (total <= 0) : (ListenerUtil.mutListener.listen(14083) ? (total > 0) : (ListenerUtil.mutListener.listen(14082) ? (total < 0) : (ListenerUtil.mutListener.listen(14081) ? (total == 0) : (total != 0)))))) && ((ListenerUtil.mutListener.listen(14095) ? (!largeCollection && (ListenerUtil.mutListener.listen(14094) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(14093) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(14092) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(14091) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(14090) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) == 0))))))) : (!largeCollection || (ListenerUtil.mutListener.listen(14094) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) >= 0) : (ListenerUtil.mutListener.listen(14093) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) <= 0) : (ListenerUtil.mutListener.listen(14092) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) > 0) : (ListenerUtil.mutListener.listen(14091) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) < 0) : (ListenerUtil.mutListener.listen(14090) ? ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) != 0) : ((ListenerUtil.mutListener.listen(14089) ? (i / onePercent) : (ListenerUtil.mutListener.listen(14088) ? (i * onePercent) : (ListenerUtil.mutListener.listen(14087) ? (i - onePercent) : (ListenerUtil.mutListener.listen(14086) ? (i + onePercent) : (i % onePercent))))) == 0)))))))))))) {
                                if (!ListenerUtil.mutListener.listen(14105)) {
                                    publishProgress(100, (ListenerUtil.mutListener.listen(14104) ? ((ListenerUtil.mutListener.listen(14100) ? (i % 100) : (ListenerUtil.mutListener.listen(14099) ? (i / 100) : (ListenerUtil.mutListener.listen(14098) ? (i - 100) : (ListenerUtil.mutListener.listen(14097) ? (i + 100) : (i * 100))))) % total) : (ListenerUtil.mutListener.listen(14103) ? ((ListenerUtil.mutListener.listen(14100) ? (i % 100) : (ListenerUtil.mutListener.listen(14099) ? (i / 100) : (ListenerUtil.mutListener.listen(14098) ? (i - 100) : (ListenerUtil.mutListener.listen(14097) ? (i + 100) : (i * 100))))) * total) : (ListenerUtil.mutListener.listen(14102) ? ((ListenerUtil.mutListener.listen(14100) ? (i % 100) : (ListenerUtil.mutListener.listen(14099) ? (i / 100) : (ListenerUtil.mutListener.listen(14098) ? (i - 100) : (ListenerUtil.mutListener.listen(14097) ? (i + 100) : (i * 100))))) - total) : (ListenerUtil.mutListener.listen(14101) ? ((ListenerUtil.mutListener.listen(14100) ? (i % 100) : (ListenerUtil.mutListener.listen(14099) ? (i / 100) : (ListenerUtil.mutListener.listen(14098) ? (i - 100) : (ListenerUtil.mutListener.listen(14097) ? (i + 100) : (i * 100))))) + total) : ((ListenerUtil.mutListener.listen(14100) ? (i % 100) : (ListenerUtil.mutListener.listen(14099) ? (i / 100) : (ListenerUtil.mutListener.listen(14098) ? (i - 100) : (ListenerUtil.mutListener.listen(14097) ? (i + 100) : (i * 100))))) / total))))), 0);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14108)) {
                publishProgress(100, 100, 0);
            }
            if (!ListenerUtil.mutListener.listen(14109)) {
                // count total values
                totalCardCount += cards.size();
            }
            if (!ListenerUtil.mutListener.listen(14110)) {
                totalRevlogCount += revlog.size();
            }
            if (!ListenerUtil.mutListener.listen(14111)) {
                Timber.d("add cards total:  %d", totalCardCount);
            }
            if (!ListenerUtil.mutListener.listen(14112)) {
                Timber.d("add revlog total: %d", totalRevlogCount);
            }
            if (!ListenerUtil.mutListener.listen(14113)) {
                // apply (for last chunk)
                insertCards(cards);
            }
            if (!ListenerUtil.mutListener.listen(14114)) {
                cards.clear();
            }
            if (!ListenerUtil.mutListener.listen(14115)) {
                insertRevlog(revlog);
            }
            if (!ListenerUtil.mutListener.listen(14116)) {
                revlog.clear();
            }
            if (!ListenerUtil.mutListener.listen(14117)) {
                mLog.add(getRes().getString(R.string.import_complete_count, totalCardCount));
            }
            if (!ListenerUtil.mutListener.listen(14118)) {
                mDst.getDb().getDatabase().setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(13987)) {
                DB.safeEndInTransaction(mDst.getDb());
            }
        }
    }

    private void insertCards(List<Object[]> cards) {
        if (!ListenerUtil.mutListener.listen(14119)) {
            mDst.getDb().executeManyNoTransaction("insert or ignore into cards values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", cards);
        }
    }

    private void insertRevlog(List<Object[]> revlog) {
        if (!ListenerUtil.mutListener.listen(14120)) {
            mDst.getDb().executeManyNoTransaction("insert or ignore into revlog values (?,?,?,?,?,?,?,?,?)", revlog);
        }
    }

    // apkg importer does the copying
    private void _importStaticMedia() {
        // they're used on notes or not
        String dir = mSrc.getMedia().dir();
        if (!ListenerUtil.mutListener.listen(14121)) {
            if (!new File(dir).exists()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(14126)) {
            {
                long _loopCounter270 = 0;
                for (File f : new File(dir).listFiles()) {
                    ListenerUtil.loopListener.listen("_loopCounter270", ++_loopCounter270);
                    String fname = f.getName();
                    if (!ListenerUtil.mutListener.listen(14125)) {
                        if ((ListenerUtil.mutListener.listen(14122) ? (fname.startsWith("_") || !mDst.getMedia().have(fname)) : (fname.startsWith("_") && !mDst.getMedia().have(fname)))) {
                            try (BufferedInputStream data = _srcMediaData(fname)) {
                                if (!ListenerUtil.mutListener.listen(14124)) {
                                    _writeDstMedia(fname, data);
                                }
                            } catch (IOException e) {
                                if (!ListenerUtil.mutListener.listen(14123)) {
                                    Timber.w(e, "Failed to close stream");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private BufferedInputStream _mediaData(String fname, String dir) {
        if (!ListenerUtil.mutListener.listen(14128)) {
            if (dir == null) {
                if (!ListenerUtil.mutListener.listen(14127)) {
                    dir = mSrc.getMedia().dir();
                }
            }
        }
        String path = new File(dir, fname).getAbsolutePath();
        try {
            return new BufferedInputStream(new FileInputStream(path), (ListenerUtil.mutListener.listen(14132) ? (MEDIAPICKLIMIT % 2) : (ListenerUtil.mutListener.listen(14131) ? (MEDIAPICKLIMIT / 2) : (ListenerUtil.mutListener.listen(14130) ? (MEDIAPICKLIMIT - 2) : (ListenerUtil.mutListener.listen(14129) ? (MEDIAPICKLIMIT + 2) : (MEDIAPICKLIMIT * 2))))));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Data for FNAME in src collection.
     */
    protected BufferedInputStream _srcMediaData(String fname) {
        return _mediaData(fname, mSrc.getMedia().dir());
    }

    /**
     * Data for FNAME in dst collection.
     */
    private BufferedInputStream _dstMediaData(String fname) {
        return _mediaData(fname, mDst.getMedia().dir());
    }

    private void _writeDstMedia(String fname, BufferedInputStream data) {
        try {
            String path = new File(mDst.getMedia().dir(), Utils.nfcNormalized(fname)).getAbsolutePath();
            if (!ListenerUtil.mutListener.listen(14137)) {
                Utils.writeToFile(data, path);
            }
            if (!ListenerUtil.mutListener.listen(14138)) {
                // Mark file addition to media db (see note in Media.java)
                mDst.getMedia().markFileAdd(fname);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(14133)) {
                // the user likely used subdirectories
                Timber.e(e, "Error copying file %s.", fname);
            }
            if (!ListenerUtil.mutListener.listen(14136)) {
                // If we are out of space, we should re-throw
                if ((ListenerUtil.mutListener.listen(14134) ? (e.getCause() != null || e.getCause().getMessage().contains("No space left on device")) : (e.getCause() != null && e.getCause().getMessage().contains("No space left on device")))) {
                    if (!ListenerUtil.mutListener.listen(14135)) {
                        // we need to let the user know why we are failing
                        Timber.e("We are out of space, bubbling up the file copy exception");
                    }
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // running splitFields() on every note is fairly expensive and actually not necessary
    private String _mungeMedia(long mid, String fields) {
        if (!ListenerUtil.mutListener.listen(14153)) {
            {
                long _loopCounter272 = 0;
                for (Pattern p : Media.mRegexps) {
                    ListenerUtil.loopListener.listen("_loopCounter272", ++_loopCounter272);
                    Matcher m = p.matcher(fields);
                    StringBuffer sb = new StringBuffer();
                    int fnameIdx = Media.indexOfFname(p);
                    if (!ListenerUtil.mutListener.listen(14150)) {
                        {
                            long _loopCounter271 = 0;
                            while (m.find()) {
                                ListenerUtil.loopListener.listen("_loopCounter271", ++_loopCounter271);
                                String fname = m.group(fnameIdx);
                                try (BufferedInputStream srcData = _srcMediaData(fname);
                                    BufferedInputStream dstData = _dstMediaData(fname)) {
                                    if (!ListenerUtil.mutListener.listen(14141)) {
                                        if (srcData == null) {
                                            if (!ListenerUtil.mutListener.listen(14140)) {
                                                // file was not in source, ignore
                                                m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0)));
                                            }
                                            continue;
                                        }
                                    }
                                    // if model-local file exists from a previous import, use that
                                    String[] split = Utils.splitFilename(fname);
                                    String name = split[0];
                                    String ext = split[1];
                                    String lname = String.format(Locale.US, "%s_%s%s", name, mid, ext);
                                    if (!ListenerUtil.mutListener.listen(14147)) {
                                        if (mDst.getMedia().have(lname)) {
                                            if (!ListenerUtil.mutListener.listen(14146)) {
                                                m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0).replace(fname, lname)));
                                            }
                                            continue;
                                        } else if ((ListenerUtil.mutListener.listen(14142) ? (dstData == null && compareMedia(srcData, dstData)) : (dstData == null || compareMedia(srcData, dstData)))) {
                                            if (!ListenerUtil.mutListener.listen(14144)) {
                                                // need to copy?
                                                if (dstData == null) {
                                                    if (!ListenerUtil.mutListener.listen(14143)) {
                                                        _writeDstMedia(fname, srcData);
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(14145)) {
                                                m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0)));
                                            }
                                            continue;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(14148)) {
                                        // exists but does not match, so we need to dedupe
                                        _writeDstMedia(lname, srcData);
                                    }
                                    if (!ListenerUtil.mutListener.listen(14149)) {
                                        m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0).replace(fname, lname)));
                                    }
                                } catch (IOException e) {
                                    if (!ListenerUtil.mutListener.listen(14139)) {
                                        Timber.w(e, "Failed to close stream");
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14151)) {
                        m.appendTail(sb);
                    }
                    if (!ListenerUtil.mutListener.listen(14152)) {
                        fields = sb.toString();
                    }
                }
            }
        }
        return fields;
    }

    private void _postImport() {
        if (!ListenerUtil.mutListener.listen(14155)) {
            {
                long _loopCounter273 = 0;
                for (long did : mDecks.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter273", ++_loopCounter273);
                    if (!ListenerUtil.mutListener.listen(14154)) {
                        mCol.getSched().maybeRandomizeDeck(did);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14156)) {
            // make sure new position is correct
            mDst.getConf().put("nextPos", mDst.getDb().queryLongScalar("select max(due)+1 from cards where type = " + CARD_TYPE_NEW));
        }
        if (!ListenerUtil.mutListener.listen(14157)) {
            mDst.save();
        }
    }

    private boolean compareMedia(BufferedInputStream lhis, BufferedInputStream rhis) {
        byte[] lhbytes = _mediaPick(lhis);
        byte[] rhbytes = _mediaPick(rhis);
        return Arrays.equals(lhbytes, rhbytes);
    }

    /**
     * Return the contents of the given input stream, limited to Anki2Importer.MEDIAPICKLIMIT bytes This is only used
     * for comparison of media files with the limited resources of mobile devices
     */
    private byte[] _mediaPick(BufferedInputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream((ListenerUtil.mutListener.listen(14161) ? (MEDIAPICKLIMIT % 2) : (ListenerUtil.mutListener.listen(14160) ? (MEDIAPICKLIMIT / 2) : (ListenerUtil.mutListener.listen(14159) ? (MEDIAPICKLIMIT - 2) : (ListenerUtil.mutListener.listen(14158) ? (MEDIAPICKLIMIT + 2) : (MEDIAPICKLIMIT * 2))))));
            byte[] buf = new byte[MEDIAPICKLIMIT];
            int readLen;
            int readSoFar = 0;
            if (!ListenerUtil.mutListener.listen(14166)) {
                is.mark((ListenerUtil.mutListener.listen(14165) ? (MEDIAPICKLIMIT % 2) : (ListenerUtil.mutListener.listen(14164) ? (MEDIAPICKLIMIT / 2) : (ListenerUtil.mutListener.listen(14163) ? (MEDIAPICKLIMIT - 2) : (ListenerUtil.mutListener.listen(14162) ? (MEDIAPICKLIMIT + 2) : (MEDIAPICKLIMIT * 2))))));
            }
            {
                long _loopCounter274 = 0;
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter274", ++_loopCounter274);
                    readLen = is.read(buf);
                    if (!ListenerUtil.mutListener.listen(14167)) {
                        baos.write(buf);
                    }
                    if (!ListenerUtil.mutListener.listen(14173)) {
                        if ((ListenerUtil.mutListener.listen(14172) ? (readLen >= -1) : (ListenerUtil.mutListener.listen(14171) ? (readLen <= -1) : (ListenerUtil.mutListener.listen(14170) ? (readLen > -1) : (ListenerUtil.mutListener.listen(14169) ? (readLen < -1) : (ListenerUtil.mutListener.listen(14168) ? (readLen != -1) : (readLen == -1))))))) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(14174)) {
                        readSoFar += readLen;
                    }
                    if (!ListenerUtil.mutListener.listen(14180)) {
                        if ((ListenerUtil.mutListener.listen(14179) ? (readSoFar >= MEDIAPICKLIMIT) : (ListenerUtil.mutListener.listen(14178) ? (readSoFar <= MEDIAPICKLIMIT) : (ListenerUtil.mutListener.listen(14177) ? (readSoFar < MEDIAPICKLIMIT) : (ListenerUtil.mutListener.listen(14176) ? (readSoFar != MEDIAPICKLIMIT) : (ListenerUtil.mutListener.listen(14175) ? (readSoFar == MEDIAPICKLIMIT) : (readSoFar > MEDIAPICKLIMIT))))))) {
                            break;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14181)) {
                is.reset();
            }
            byte[] result = new byte[MEDIAPICKLIMIT];
            if (!ListenerUtil.mutListener.listen(14182)) {
                System.arraycopy(baos.toByteArray(), 0, result, 0, Math.min(baos.size(), MEDIAPICKLIMIT));
            }
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * @param notesDone Percentage of notes complete.
     * @param cardsDone Percentage of cards complete.
     * @param postProcess Percentage of remaining tasks complete.
     */
    protected void publishProgress(int notesDone, int cardsDone, int postProcess) {
        if (!ListenerUtil.mutListener.listen(14184)) {
            if (mProgress != null) {
                if (!ListenerUtil.mutListener.listen(14183)) {
                    mProgress.publishProgress(getRes().getString(R.string.import_progress, notesDone, cardsDone, postProcess));
                }
            }
        }
    }

    public void setDupeOnSchemaChange(boolean b) {
        if (!ListenerUtil.mutListener.listen(14185)) {
            mDupeOnSchemaChange = b;
        }
    }

    public int getDupes() {
        return mDupes;
    }

    public int getAdded() {
        return mAdded;
    }

    public int getUpdated() {
        return mUpdated;
    }
}
