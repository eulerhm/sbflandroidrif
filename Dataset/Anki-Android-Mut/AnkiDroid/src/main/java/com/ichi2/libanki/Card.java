/**
 * *************************************************************************************
 *  Copyright (c) 2009 Daniel Svärd <daniel.svard@gmail.com>                             *
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2014 Houssam Salem <houssam.salem.au@gmail.com>                        *
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
package com.ichi2.libanki;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.async.CancelListener;
import com.ichi2.libanki.template.TemplateError;
import com.ichi2.utils.Assert;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.utils.LanguageUtil;
import com.ichi2.utils.JSONObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CancellationException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import static com.ichi2.libanki.stats.Stats.SECONDS_PER_DAY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A Card is the ultimate entity subject to review; it encapsulates the scheduling parameters (from which to derive
 * the next interval), the note it is derived from (from which field data is retrieved), its own ownership (which deck it
 * currently belongs to), and the retrieval of presentation elements (filled-in templates).
 *
 * Card presentation has two components: the question (front) side and the answer (back) side. The presentation of the
 * card is derived from the template of the card's Card Type. The Card Type is a component of the Note Type (see Models)
 * that this card is derived from.
 *
 * This class is responsible for:
 * - Storing and retrieving database entries that map to Cards in the Collection
 * - Providing the HTML representation of the Card's question and answer
 * - Recording the results of review (answer chosen, time taken, etc)
 *
 * It does not:
 * - Generate new cards (see Collection)
 * - Store the templates or the style sheet (see Models)
 *
 * Type: 0=new, 1=learning, 2=due
 * Queue: same as above, and:
 *        -1=suspended, -2=user buried, -3=sched buried
 * Due is used differently for different queues.
 * - new queue: note id or random int
 * - rev queue: integer day
 * - lrn queue: integer timestamp
 */
@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes", "PMD.ExcessiveMethodLength", "PMD.FieldDeclarationsShouldBeAtStartOfClass", "PMD.MethodNamingConventions" })
public class Card implements Cloneable {

    public static final int TYPE_REV = 2;

    private Collection mCol;

    // When timer was started, in MS
    private long mTimerStarted;

    // Not in LibAnki. Record time spent reviewing in MS in order to restore when resuming.
    private long mElapsedTime;

    // BEGIN SQL table entries
    private long mId;

    private long mNid;

    private long mDid;

    private int mOrd;

    private long mMod;

    private int mUsn;

    @Consts.CARD_TYPE
    private int mType;

    @Consts.CARD_QUEUE
    private int mQueue;

    private long mDue;

    private int mIvl;

    private int mFactor;

    private int mReps;

    private int mLapses;

    private int mLeft;

    private long mODue;

    private long mODid;

    private int mFlags;

    private String mData;

    private HashMap<String, String> mQA;

    private Note mNote;

    // Used by Sched to determine which queue to move the card to after answering.
    private boolean mWasNew;

    // Used by Sched to record the original interval in the revlog after answering.
    private int mLastIvl;

    public Card(Collection col) {
        this(col, null);
    }

    public Card(Collection col, Long id) {
        if (!ListenerUtil.mutListener.listen(20946)) {
            mCol = col;
        }
        if (!ListenerUtil.mutListener.listen(20947)) {
            mTimerStarted = 0L;
        }
        if (!ListenerUtil.mutListener.listen(20948)) {
            mQA = null;
        }
        if (!ListenerUtil.mutListener.listen(20949)) {
            mNote = null;
        }
        if (!ListenerUtil.mutListener.listen(20965)) {
            if (id != null) {
                if (!ListenerUtil.mutListener.listen(20963)) {
                    mId = id;
                }
                if (!ListenerUtil.mutListener.listen(20964)) {
                    load();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20950)) {
                    // to flush, set nid, ord, and due
                    mId = mCol.getTime().timestampID(mCol.getDb(), "cards");
                }
                if (!ListenerUtil.mutListener.listen(20951)) {
                    mDid = 1;
                }
                if (!ListenerUtil.mutListener.listen(20952)) {
                    mType = Consts.CARD_TYPE_NEW;
                }
                if (!ListenerUtil.mutListener.listen(20953)) {
                    mQueue = Consts.QUEUE_TYPE_NEW;
                }
                if (!ListenerUtil.mutListener.listen(20954)) {
                    mIvl = 0;
                }
                if (!ListenerUtil.mutListener.listen(20955)) {
                    mFactor = 0;
                }
                if (!ListenerUtil.mutListener.listen(20956)) {
                    mReps = 0;
                }
                if (!ListenerUtil.mutListener.listen(20957)) {
                    mLapses = 0;
                }
                if (!ListenerUtil.mutListener.listen(20958)) {
                    mLeft = 0;
                }
                if (!ListenerUtil.mutListener.listen(20959)) {
                    mODue = 0;
                }
                if (!ListenerUtil.mutListener.listen(20960)) {
                    mODid = 0;
                }
                if (!ListenerUtil.mutListener.listen(20961)) {
                    mFlags = 0;
                }
                if (!ListenerUtil.mutListener.listen(20962)) {
                    mData = "";
                }
            }
        }
    }

    public void load() {
        try (Cursor cursor = mCol.getDb().query("SELECT * FROM cards WHERE id = ?", mId)) {
            if (!ListenerUtil.mutListener.listen(20966)) {
                if (!cursor.moveToFirst()) {
                    throw new WrongId(mId, "card");
                }
            }
            if (!ListenerUtil.mutListener.listen(20967)) {
                mId = cursor.getLong(0);
            }
            if (!ListenerUtil.mutListener.listen(20968)) {
                mNid = cursor.getLong(1);
            }
            if (!ListenerUtil.mutListener.listen(20969)) {
                mDid = cursor.getLong(2);
            }
            if (!ListenerUtil.mutListener.listen(20970)) {
                mOrd = cursor.getInt(3);
            }
            if (!ListenerUtil.mutListener.listen(20971)) {
                mMod = cursor.getLong(4);
            }
            if (!ListenerUtil.mutListener.listen(20972)) {
                mUsn = cursor.getInt(5);
            }
            if (!ListenerUtil.mutListener.listen(20973)) {
                mType = cursor.getInt(6);
            }
            if (!ListenerUtil.mutListener.listen(20974)) {
                mQueue = cursor.getInt(7);
            }
            if (!ListenerUtil.mutListener.listen(20975)) {
                mDue = cursor.getInt(8);
            }
            if (!ListenerUtil.mutListener.listen(20976)) {
                mIvl = cursor.getInt(9);
            }
            if (!ListenerUtil.mutListener.listen(20977)) {
                mFactor = cursor.getInt(10);
            }
            if (!ListenerUtil.mutListener.listen(20978)) {
                mReps = cursor.getInt(11);
            }
            if (!ListenerUtil.mutListener.listen(20979)) {
                mLapses = cursor.getInt(12);
            }
            if (!ListenerUtil.mutListener.listen(20980)) {
                mLeft = cursor.getInt(13);
            }
            if (!ListenerUtil.mutListener.listen(20981)) {
                mODue = cursor.getLong(14);
            }
            if (!ListenerUtil.mutListener.listen(20982)) {
                mODid = cursor.getLong(15);
            }
            if (!ListenerUtil.mutListener.listen(20983)) {
                mFlags = cursor.getInt(16);
            }
            if (!ListenerUtil.mutListener.listen(20984)) {
                mData = cursor.getString(17);
            }
        }
        if (!ListenerUtil.mutListener.listen(20985)) {
            mQA = null;
        }
        if (!ListenerUtil.mutListener.listen(20986)) {
            mNote = null;
        }
    }

    public void flush() {
        if (!ListenerUtil.mutListener.listen(20987)) {
            flush(true);
        }
    }

    public void flush(boolean changeModUsn) {
        if (!ListenerUtil.mutListener.listen(20990)) {
            if (changeModUsn) {
                if (!ListenerUtil.mutListener.listen(20988)) {
                    mMod = getCol().getTime().intTime();
                }
                if (!ListenerUtil.mutListener.listen(20989)) {
                    mUsn = mCol.usn();
                }
            }
        }
        // }
        assert ((ListenerUtil.mutListener.listen(20995) ? (mDue >= Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(20994) ? (mDue <= Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(20993) ? (mDue > Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(20992) ? (mDue != Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(20991) ? (mDue == Long.parseLong("4294967296")) : (mDue < Long.parseLong("4294967296"))))))));
        if (!ListenerUtil.mutListener.listen(20996)) {
            mCol.getDb().execute("insert or replace into cards values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", mId, mNid, mDid, mOrd, mMod, mUsn, mType, mQueue, mDue, mIvl, mFactor, mReps, mLapses, mLeft, mODue, mODid, mFlags, mData);
        }
        if (!ListenerUtil.mutListener.listen(20997)) {
            mCol.log(this);
        }
    }

    public void flushSched() {
        if (!ListenerUtil.mutListener.listen(20998)) {
            mMod = getCol().getTime().intTime();
        }
        if (!ListenerUtil.mutListener.listen(20999)) {
            mUsn = mCol.usn();
        }
        // }
        assert ((ListenerUtil.mutListener.listen(21004) ? (mDue >= Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(21003) ? (mDue <= Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(21002) ? (mDue > Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(21001) ? (mDue != Long.parseLong("4294967296")) : (ListenerUtil.mutListener.listen(21000) ? (mDue == Long.parseLong("4294967296")) : (mDue < Long.parseLong("4294967296"))))))));
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(21005)) {
            values.put("mod", mMod);
        }
        if (!ListenerUtil.mutListener.listen(21006)) {
            values.put("usn", mUsn);
        }
        if (!ListenerUtil.mutListener.listen(21007)) {
            values.put("type", mType);
        }
        if (!ListenerUtil.mutListener.listen(21008)) {
            values.put("queue", mQueue);
        }
        if (!ListenerUtil.mutListener.listen(21009)) {
            values.put("due", mDue);
        }
        if (!ListenerUtil.mutListener.listen(21010)) {
            values.put("ivl", mIvl);
        }
        if (!ListenerUtil.mutListener.listen(21011)) {
            values.put("factor", mFactor);
        }
        if (!ListenerUtil.mutListener.listen(21012)) {
            values.put("reps", mReps);
        }
        if (!ListenerUtil.mutListener.listen(21013)) {
            values.put("lapses", mLapses);
        }
        if (!ListenerUtil.mutListener.listen(21014)) {
            values.put("left", mLeft);
        }
        if (!ListenerUtil.mutListener.listen(21015)) {
            values.put("odue", mODue);
        }
        if (!ListenerUtil.mutListener.listen(21016)) {
            values.put("odid", mODid);
        }
        if (!ListenerUtil.mutListener.listen(21017)) {
            values.put("did", mDid);
        }
        if (!ListenerUtil.mutListener.listen(21018)) {
            // TODO: The update DB call sets mod=true. Verify if this is intended.
            mCol.getDb().update("cards", values, "id = ?", new String[] { Long.toString(mId) });
        }
        if (!ListenerUtil.mutListener.listen(21019)) {
            mCol.log(this);
        }
    }

    public String q() {
        return q(false);
    }

    public String q(boolean reload) {
        return q(reload, false);
    }

    public String q(boolean reload, boolean browser) {
        return css() + _getQA(reload, browser).get("q");
    }

    public String a() {
        return css() + _getQA().get("a");
    }

    public String css() {
        return String.format(Locale.US, "<style>%s</style>", model().getString("css"));
    }

    public HashMap<String, String> _getQA() {
        return _getQA(false);
    }

    public HashMap<String, String> _getQA(boolean reload) {
        return _getQA(reload, false);
    }

    public HashMap<String, String> _getQA(boolean reload, boolean browser) {
        if (!ListenerUtil.mutListener.listen(21024)) {
            if ((ListenerUtil.mutListener.listen(21020) ? (mQA == null && reload) : (mQA == null || reload))) {
                Note f = note(reload);
                Model m = model();
                JSONObject t = template();
                long did = isInDynamicDeck() ? mODid : mDid;
                if (!ListenerUtil.mutListener.listen(21023)) {
                    if (browser) {
                        String bqfmt = t.getString("bqfmt");
                        String bafmt = t.getString("bafmt");
                        if (!ListenerUtil.mutListener.listen(21022)) {
                            mQA = mCol._renderQA(mId, m, did, mOrd, f.stringTags(), f.getFields(), mFlags, browser, bqfmt, bafmt);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(21021)) {
                            mQA = mCol._renderQA(mId, m, did, mOrd, f.stringTags(), f.getFields(), mFlags);
                        }
                    }
                }
            }
        }
        return mQA;
    }

    public Note note() {
        return note(false);
    }

    public Note note(boolean reload) {
        if (!ListenerUtil.mutListener.listen(21027)) {
            if ((ListenerUtil.mutListener.listen(21025) ? (mNote == null && reload) : (mNote == null || reload))) {
                if (!ListenerUtil.mutListener.listen(21026)) {
                    mNote = mCol.getNote(mNid);
                }
            }
        }
        return mNote;
    }

    // not in upstream
    public Model model() {
        return note().model();
    }

    public JSONObject template() {
        Model m = model();
        if (m.isStd()) {
            return m.getJSONArray("tmpls").getJSONObject(mOrd);
        } else {
            return model().getJSONArray("tmpls").getJSONObject(0);
        }
    }

    public void startTimer() {
        if (!ListenerUtil.mutListener.listen(21028)) {
            mTimerStarted = getCol().getTime().intTimeMS();
        }
    }

    /**
     * Time limit for answering in milliseconds.
     */
    public int timeLimit() {
        DeckConfig conf = mCol.getDecks().confForDid(!isInDynamicDeck() ? mDid : mODid);
        return (ListenerUtil.mutListener.listen(21032) ? (conf.getInt("maxTaken") % 1000) : (ListenerUtil.mutListener.listen(21031) ? (conf.getInt("maxTaken") / 1000) : (ListenerUtil.mutListener.listen(21030) ? (conf.getInt("maxTaken") - 1000) : (ListenerUtil.mutListener.listen(21029) ? (conf.getInt("maxTaken") + 1000) : (conf.getInt("maxTaken") * 1000)))));
    }

    /*
     * Time taken to answer card, in integer MS.
     */
    public int timeTaken() {
        // Indeed an int. Difference between two big numbers is still small.
        int total = (int) ((ListenerUtil.mutListener.listen(21036) ? (getCol().getTime().intTimeMS() % mTimerStarted) : (ListenerUtil.mutListener.listen(21035) ? (getCol().getTime().intTimeMS() / mTimerStarted) : (ListenerUtil.mutListener.listen(21034) ? (getCol().getTime().intTimeMS() * mTimerStarted) : (ListenerUtil.mutListener.listen(21033) ? (getCol().getTime().intTimeMS() + mTimerStarted) : (getCol().getTime().intTimeMS() - mTimerStarted))))));
        return Math.min(total, timeLimit());
    }

    public boolean isEmpty() {
        try {
            return Models.emptyCard(model(), mOrd, note().getFields());
        } catch (TemplateError er) {
            if (!ListenerUtil.mutListener.listen(21037)) {
                Timber.w("Card is empty because the card's template has an error: %s.", er.message(getCol().getContext()));
            }
            return true;
        }
    }

    public String qSimple() {
        return _getQA(false).get("q");
    }

    /*
     * Returns the answer with anything before the <hr id=answer> tag removed
     */
    public String getPureAnswer() {
        String s = _getQA(false).get("a");
        String target = "<hr id=answer>";
        int pos = s.indexOf(target);
        if (!ListenerUtil.mutListener.listen(21043)) {
            if ((ListenerUtil.mutListener.listen(21042) ? (pos >= -1) : (ListenerUtil.mutListener.listen(21041) ? (pos <= -1) : (ListenerUtil.mutListener.listen(21040) ? (pos > -1) : (ListenerUtil.mutListener.listen(21039) ? (pos < -1) : (ListenerUtil.mutListener.listen(21038) ? (pos != -1) : (pos == -1))))))) {
                return s;
            }
        }
        return s.substring((ListenerUtil.mutListener.listen(21047) ? (pos % target.length()) : (ListenerUtil.mutListener.listen(21046) ? (pos / target.length()) : (ListenerUtil.mutListener.listen(21045) ? (pos * target.length()) : (ListenerUtil.mutListener.listen(21044) ? (pos - target.length()) : (pos + target.length())))))).trim();
    }

    /**
     * Save the currently elapsed reviewing time so it can be restored on resume.
     *
     * Use this method whenever a review session (activity) has been paused. Use the resumeTimer()
     * method when the session resumes to start counting review time again.
     */
    public void stopTimer() {
        if (!ListenerUtil.mutListener.listen(21052)) {
            mElapsedTime = (ListenerUtil.mutListener.listen(21051) ? (getCol().getTime().intTimeMS() % mTimerStarted) : (ListenerUtil.mutListener.listen(21050) ? (getCol().getTime().intTimeMS() / mTimerStarted) : (ListenerUtil.mutListener.listen(21049) ? (getCol().getTime().intTimeMS() * mTimerStarted) : (ListenerUtil.mutListener.listen(21048) ? (getCol().getTime().intTimeMS() + mTimerStarted) : (getCol().getTime().intTimeMS() - mTimerStarted)))));
        }
    }

    /**
     * Resume the timer that counts the time spent reviewing this card.
     *
     * Unlike the desktop client, AnkiDroid must pause and resume the process in the middle of
     * reviewing. This method is required to keep track of the actual amount of time spent in
     * the reviewer and *must* be called on resume before any calls to timeTaken() take place
     * or the result of timeTaken() will be wrong.
     */
    public void resumeTimer() {
        if (!ListenerUtil.mutListener.listen(21057)) {
            mTimerStarted = (ListenerUtil.mutListener.listen(21056) ? (getCol().getTime().intTimeMS() % mElapsedTime) : (ListenerUtil.mutListener.listen(21055) ? (getCol().getTime().intTimeMS() / mElapsedTime) : (ListenerUtil.mutListener.listen(21054) ? (getCol().getTime().intTimeMS() * mElapsedTime) : (ListenerUtil.mutListener.listen(21053) ? (getCol().getTime().intTimeMS() + mElapsedTime) : (getCol().getTime().intTimeMS() - mElapsedTime)))));
        }
    }

    /**
     * @param timeStarted Time in MS when timer was started
     */
    public void setTimerStarted(long timeStarted) {
        if (!ListenerUtil.mutListener.listen(21058)) {
            mTimerStarted = timeStarted;
        }
    }

    public long getId() {
        return mId;
    }

    @VisibleForTesting
    public void setId(long id) {
        if (!ListenerUtil.mutListener.listen(21059)) {
            mId = id;
        }
    }

    public void setMod(long mod) {
        if (!ListenerUtil.mutListener.listen(21060)) {
            mMod = mod;
        }
    }

    public long getMod() {
        return mMod;
    }

    public void setUsn(int usn) {
        if (!ListenerUtil.mutListener.listen(21061)) {
            mUsn = usn;
        }
    }

    public long getNid() {
        return mNid;
    }

    @Consts.CARD_TYPE
    public int getType() {
        return mType;
    }

    public void setType(@Consts.CARD_TYPE int type) {
        if (!ListenerUtil.mutListener.listen(21062)) {
            mType = type;
        }
    }

    public void setLeft(int left) {
        if (!ListenerUtil.mutListener.listen(21063)) {
            mLeft = left;
        }
    }

    public int getLeft() {
        return mLeft;
    }

    @Consts.CARD_QUEUE
    public int getQueue() {
        return mQueue;
    }

    public void setQueue(@Consts.CARD_QUEUE int queue) {
        if (!ListenerUtil.mutListener.listen(21064)) {
            mQueue = queue;
        }
    }

    public long getODue() {
        return mODue;
    }

    public void setODid(long odid) {
        if (!ListenerUtil.mutListener.listen(21065)) {
            mODid = odid;
        }
    }

    public long getODid() {
        return mODid;
    }

    public void setODue(long odue) {
        if (!ListenerUtil.mutListener.listen(21066)) {
            mODue = odue;
        }
    }

    public long getDue() {
        return mDue;
    }

    public void setDue(long due) {
        if (!ListenerUtil.mutListener.listen(21067)) {
            mDue = due;
        }
    }

    public int getIvl() {
        return mIvl;
    }

    public void setIvl(int ivl) {
        if (!ListenerUtil.mutListener.listen(21068)) {
            mIvl = ivl;
        }
    }

    public int getFactor() {
        return mFactor;
    }

    public void setFactor(int factor) {
        if (!ListenerUtil.mutListener.listen(21069)) {
            mFactor = factor;
        }
    }

    public int getReps() {
        return mReps;
    }

    @VisibleForTesting
    public int setReps(int reps) {
        return mReps = reps;
    }

    public int incrReps() {
        return ++mReps;
    }

    public int getLapses() {
        return mLapses;
    }

    public void setLapses(int lapses) {
        if (!ListenerUtil.mutListener.listen(21070)) {
            mLapses = lapses;
        }
    }

    public void setNid(long nid) {
        if (!ListenerUtil.mutListener.listen(21071)) {
            mNid = nid;
        }
    }

    public void setOrd(int ord) {
        if (!ListenerUtil.mutListener.listen(21072)) {
            mOrd = ord;
        }
    }

    public int getOrd() {
        return mOrd;
    }

    public void setDid(long did) {
        if (!ListenerUtil.mutListener.listen(21073)) {
            mDid = did;
        }
    }

    public long getDid() {
        return mDid;
    }

    public boolean getWasNew() {
        return mWasNew;
    }

    public void setWasNew(boolean wasNew) {
        if (!ListenerUtil.mutListener.listen(21074)) {
            mWasNew = wasNew;
        }
    }

    public int getLastIvl() {
        return mLastIvl;
    }

    public void setLastIvl(int ivl) {
        if (!ListenerUtil.mutListener.listen(21075)) {
            mLastIvl = ivl;
        }
    }

    // Needed for tests
    public Collection getCol() {
        return mCol;
    }

    // Needed for tests
    public void setCol(Collection col) {
        if (!ListenerUtil.mutListener.listen(21076)) {
            mCol = col;
        }
    }

    public boolean showTimer() {
        DeckConfig options = mCol.getDecks().confForDid(!isInDynamicDeck() ? mDid : mODid);
        return DeckConfig.parseTimerOpt(options, true);
    }

    public Card clone() {
        try {
            return (Card) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    // A list of class members to skip in the toString() representation
    public static final Set<String> SKIP_PRINT = new HashSet<>(Arrays.asList("SKIP_PRINT", "$assertionsDisabled", "TYPE_LRN", "TYPE_NEW", "TYPE_REV", "mNote", "mQA", "mCol", "mTimerStarted", "mTimerStopped"));

    @NonNull
    public String toString() {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        List<String> members = new ArrayList<>(declaredFields.length);
        if (!ListenerUtil.mutListener.listen(21080)) {
            {
                long _loopCounter455 = 0;
                for (Field f : declaredFields) {
                    ListenerUtil.loopListener.listen("_loopCounter455", ++_loopCounter455);
                    try {
                        if (!ListenerUtil.mutListener.listen(21078)) {
                            // skip non-useful elements
                            if (SKIP_PRINT.contains(f.getName())) {
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(21079)) {
                            members.add(String.format("'%s': %s", f.getName(), f.get(this)));
                        }
                    } catch (IllegalAccessException | IllegalArgumentException e) {
                        if (!ListenerUtil.mutListener.listen(21077)) {
                            members.add(String.format("'%s': %s", f.getName(), "N/A"));
                        }
                    }
                }
            }
        }
        return TextUtils.join(",  ", members);
    }

    @Override
    public boolean equals(Object obj) {
        if (!ListenerUtil.mutListener.listen(21086)) {
            if (obj instanceof Card) {
                return (ListenerUtil.mutListener.listen(21085) ? (this.getId() >= ((Card) obj).getId()) : (ListenerUtil.mutListener.listen(21084) ? (this.getId() <= ((Card) obj).getId()) : (ListenerUtil.mutListener.listen(21083) ? (this.getId() > ((Card) obj).getId()) : (ListenerUtil.mutListener.listen(21082) ? (this.getId() < ((Card) obj).getId()) : (ListenerUtil.mutListener.listen(21081) ? (this.getId() != ((Card) obj).getId()) : (this.getId() == ((Card) obj).getId()))))));
            }
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // Map a long to an int. For API>=24 you would just do `Long.hashCode(this.getId())`
        return (int) (this.getId() ^ (this.getId() >>> 32));
    }

    public static int intToFlag(int flags) {
        // equivalent to `mFlags % 8`. Used this way to copy Anki.
        return flags & 0b111;
    }

    public int userFlag() {
        return Card.intToFlag(mFlags);
    }

    public static int setFlagInInt(int mFlags, int flag) {
        if (!ListenerUtil.mutListener.listen(21092)) {
            Assert.that((ListenerUtil.mutListener.listen(21091) ? (0 >= flag) : (ListenerUtil.mutListener.listen(21090) ? (0 > flag) : (ListenerUtil.mutListener.listen(21089) ? (0 < flag) : (ListenerUtil.mutListener.listen(21088) ? (0 != flag) : (ListenerUtil.mutListener.listen(21087) ? (0 == flag) : (0 <= flag)))))), "flag to set is negative");
        }
        if (!ListenerUtil.mutListener.listen(21098)) {
            Assert.that((ListenerUtil.mutListener.listen(21097) ? (flag >= 7) : (ListenerUtil.mutListener.listen(21096) ? (flag > 7) : (ListenerUtil.mutListener.listen(21095) ? (flag < 7) : (ListenerUtil.mutListener.listen(21094) ? (flag != 7) : (ListenerUtil.mutListener.listen(21093) ? (flag == 7) : (flag <= 7)))))), "flag to set is greater than 7.");
        }
        // Setting the 3 firsts bits to 0, keeping the remaining.
        int extraData = (mFlags & ~0b111);
        // flag in 3 fist bits, same data as in mFlags everywhere else
        return extraData | flag;
    }

    @VisibleForTesting
    public void setFlag(int flag) {
        if (!ListenerUtil.mutListener.listen(21099)) {
            mFlags = flag;
        }
    }

    public void setUserFlag(int flag) {
        if (!ListenerUtil.mutListener.listen(21100)) {
            mFlags = setFlagInInt(mFlags, flag);
        }
    }

    // not in Anki.
    public String getDueString() {
        String t = nextDue();
        if (!ListenerUtil.mutListener.listen(21107)) {
            if ((ListenerUtil.mutListener.listen(21105) ? (getQueue() >= 0) : (ListenerUtil.mutListener.listen(21104) ? (getQueue() <= 0) : (ListenerUtil.mutListener.listen(21103) ? (getQueue() > 0) : (ListenerUtil.mutListener.listen(21102) ? (getQueue() != 0) : (ListenerUtil.mutListener.listen(21101) ? (getQueue() == 0) : (getQueue() < 0))))))) {
                if (!ListenerUtil.mutListener.listen(21106)) {
                    t = "(" + t + ")";
                }
            }
        }
        return t;
    }

    // as in Anki aqt/browser.py
    private String nextDue() {
        long date;
        long due = getDue();
        if (isInDynamicDeck()) {
            return AnkiDroidApp.getAppResources().getString(R.string.card_browser_due_filtered_card);
        } else if ((ListenerUtil.mutListener.listen(21112) ? (getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(21111) ? (getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(21110) ? (getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(21109) ? (getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(21108) ? (getQueue() != Consts.QUEUE_TYPE_LRN) : (getQueue() == Consts.QUEUE_TYPE_LRN))))))) {
            date = due;
        } else if ((ListenerUtil.mutListener.listen(21123) ? ((ListenerUtil.mutListener.listen(21117) ? (getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21116) ? (getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21115) ? (getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21114) ? (getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21113) ? (getQueue() != Consts.QUEUE_TYPE_NEW) : (getQueue() == Consts.QUEUE_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(21122) ? (getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21121) ? (getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21120) ? (getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21119) ? (getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21118) ? (getType() != Consts.CARD_TYPE_NEW) : (getType() == Consts.CARD_TYPE_NEW))))))) : ((ListenerUtil.mutListener.listen(21117) ? (getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21116) ? (getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21115) ? (getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21114) ? (getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(21113) ? (getQueue() != Consts.QUEUE_TYPE_NEW) : (getQueue() == Consts.QUEUE_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(21122) ? (getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21121) ? (getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21120) ? (getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21119) ? (getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21118) ? (getType() != Consts.CARD_TYPE_NEW) : (getType() == Consts.CARD_TYPE_NEW))))))))) {
            return (Long.valueOf(due)).toString();
        } else if ((ListenerUtil.mutListener.listen(21146) ? ((ListenerUtil.mutListener.listen(21134) ? ((ListenerUtil.mutListener.listen(21128) ? (getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21127) ? (getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21126) ? (getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21125) ? (getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21124) ? (getQueue() != Consts.QUEUE_TYPE_REV) : (getQueue() == Consts.QUEUE_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(21133) ? (getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21132) ? (getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21131) ? (getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21130) ? (getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21129) ? (getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(21128) ? (getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21127) ? (getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21126) ? (getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21125) ? (getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21124) ? (getQueue() != Consts.QUEUE_TYPE_REV) : (getQueue() == Consts.QUEUE_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(21133) ? (getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21132) ? (getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21131) ? (getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21130) ? (getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21129) ? (getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN)))))))) && ((ListenerUtil.mutListener.listen(21145) ? ((ListenerUtil.mutListener.listen(21139) ? (getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21138) ? (getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21137) ? (getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21136) ? (getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21135) ? (getType() != Consts.CARD_TYPE_REV) : (getType() == Consts.CARD_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(21144) ? (getQueue() >= 0) : (ListenerUtil.mutListener.listen(21143) ? (getQueue() <= 0) : (ListenerUtil.mutListener.listen(21142) ? (getQueue() > 0) : (ListenerUtil.mutListener.listen(21141) ? (getQueue() != 0) : (ListenerUtil.mutListener.listen(21140) ? (getQueue() == 0) : (getQueue() < 0))))))) : ((ListenerUtil.mutListener.listen(21139) ? (getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21138) ? (getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21137) ? (getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21136) ? (getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21135) ? (getType() != Consts.CARD_TYPE_REV) : (getType() == Consts.CARD_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(21144) ? (getQueue() >= 0) : (ListenerUtil.mutListener.listen(21143) ? (getQueue() <= 0) : (ListenerUtil.mutListener.listen(21142) ? (getQueue() > 0) : (ListenerUtil.mutListener.listen(21141) ? (getQueue() != 0) : (ListenerUtil.mutListener.listen(21140) ? (getQueue() == 0) : (getQueue() < 0)))))))))) : ((ListenerUtil.mutListener.listen(21134) ? ((ListenerUtil.mutListener.listen(21128) ? (getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21127) ? (getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21126) ? (getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21125) ? (getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21124) ? (getQueue() != Consts.QUEUE_TYPE_REV) : (getQueue() == Consts.QUEUE_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(21133) ? (getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21132) ? (getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21131) ? (getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21130) ? (getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21129) ? (getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(21128) ? (getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21127) ? (getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21126) ? (getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21125) ? (getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21124) ? (getQueue() != Consts.QUEUE_TYPE_REV) : (getQueue() == Consts.QUEUE_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(21133) ? (getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21132) ? (getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21131) ? (getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21130) ? (getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(21129) ? (getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN)))))))) || ((ListenerUtil.mutListener.listen(21145) ? ((ListenerUtil.mutListener.listen(21139) ? (getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21138) ? (getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21137) ? (getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21136) ? (getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21135) ? (getType() != Consts.CARD_TYPE_REV) : (getType() == Consts.CARD_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(21144) ? (getQueue() >= 0) : (ListenerUtil.mutListener.listen(21143) ? (getQueue() <= 0) : (ListenerUtil.mutListener.listen(21142) ? (getQueue() > 0) : (ListenerUtil.mutListener.listen(21141) ? (getQueue() != 0) : (ListenerUtil.mutListener.listen(21140) ? (getQueue() == 0) : (getQueue() < 0))))))) : ((ListenerUtil.mutListener.listen(21139) ? (getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21138) ? (getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21137) ? (getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21136) ? (getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21135) ? (getType() != Consts.CARD_TYPE_REV) : (getType() == Consts.CARD_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(21144) ? (getQueue() >= 0) : (ListenerUtil.mutListener.listen(21143) ? (getQueue() <= 0) : (ListenerUtil.mutListener.listen(21142) ? (getQueue() > 0) : (ListenerUtil.mutListener.listen(21141) ? (getQueue() != 0) : (ListenerUtil.mutListener.listen(21140) ? (getQueue() == 0) : (getQueue() < 0)))))))))))) {
            long time = mCol.getTime().intTime();
            long nbDaySinceCreation = ((ListenerUtil.mutListener.listen(21150) ? (due % getCol().getSched().getToday()) : (ListenerUtil.mutListener.listen(21149) ? (due / getCol().getSched().getToday()) : (ListenerUtil.mutListener.listen(21148) ? (due * getCol().getSched().getToday()) : (ListenerUtil.mutListener.listen(21147) ? (due + getCol().getSched().getToday()) : (due - getCol().getSched().getToday()))))));
            date = (ListenerUtil.mutListener.listen(21158) ? (time % ((ListenerUtil.mutListener.listen(21154) ? (nbDaySinceCreation % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21153) ? (nbDaySinceCreation / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21152) ? (nbDaySinceCreation - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21151) ? (nbDaySinceCreation + SECONDS_PER_DAY) : (nbDaySinceCreation * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(21157) ? (time / ((ListenerUtil.mutListener.listen(21154) ? (nbDaySinceCreation % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21153) ? (nbDaySinceCreation / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21152) ? (nbDaySinceCreation - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21151) ? (nbDaySinceCreation + SECONDS_PER_DAY) : (nbDaySinceCreation * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(21156) ? (time * ((ListenerUtil.mutListener.listen(21154) ? (nbDaySinceCreation % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21153) ? (nbDaySinceCreation / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21152) ? (nbDaySinceCreation - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21151) ? (nbDaySinceCreation + SECONDS_PER_DAY) : (nbDaySinceCreation * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(21155) ? (time - ((ListenerUtil.mutListener.listen(21154) ? (nbDaySinceCreation % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21153) ? (nbDaySinceCreation / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21152) ? (nbDaySinceCreation - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21151) ? (nbDaySinceCreation + SECONDS_PER_DAY) : (nbDaySinceCreation * SECONDS_PER_DAY))))))) : (time + ((ListenerUtil.mutListener.listen(21154) ? (nbDaySinceCreation % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21153) ? (nbDaySinceCreation / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21152) ? (nbDaySinceCreation - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(21151) ? (nbDaySinceCreation + SECONDS_PER_DAY) : (nbDaySinceCreation * SECONDS_PER_DAY)))))))))));
        } else {
            return "";
        }
        return LanguageUtil.getShortDateFormatFromS(date);
    }

    /**
     * Non libAnki
     */
    public boolean isInDynamicDeck() {
        // In Anki Desktop, a card with oDue <> 0 && oDid == 0 is not marked as dynamic.
        return (ListenerUtil.mutListener.listen(21163) ? (this.getODid() >= 0) : (ListenerUtil.mutListener.listen(21162) ? (this.getODid() <= 0) : (ListenerUtil.mutListener.listen(21161) ? (this.getODid() > 0) : (ListenerUtil.mutListener.listen(21160) ? (this.getODid() < 0) : (ListenerUtil.mutListener.listen(21159) ? (this.getODid() == 0) : (this.getODid() != 0))))));
    }

    public boolean isReview() {
        return (ListenerUtil.mutListener.listen(21174) ? ((ListenerUtil.mutListener.listen(21168) ? (this.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21167) ? (this.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21166) ? (this.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21165) ? (this.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21164) ? (this.getType() != Consts.CARD_TYPE_REV) : (this.getType() == Consts.CARD_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(21173) ? (this.getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21172) ? (this.getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21171) ? (this.getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21170) ? (this.getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21169) ? (this.getQueue() != Consts.QUEUE_TYPE_REV) : (this.getQueue() == Consts.QUEUE_TYPE_REV))))))) : ((ListenerUtil.mutListener.listen(21168) ? (this.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21167) ? (this.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21166) ? (this.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21165) ? (this.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(21164) ? (this.getType() != Consts.CARD_TYPE_REV) : (this.getType() == Consts.CARD_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(21173) ? (this.getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21172) ? (this.getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21171) ? (this.getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21170) ? (this.getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(21169) ? (this.getQueue() != Consts.QUEUE_TYPE_REV) : (this.getQueue() == Consts.QUEUE_TYPE_REV))))))));
    }

    public boolean isNew() {
        return (ListenerUtil.mutListener.listen(21179) ? (this.getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21178) ? (this.getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21177) ? (this.getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21176) ? (this.getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(21175) ? (this.getType() != Consts.CARD_TYPE_NEW) : (this.getType() == Consts.CARD_TYPE_NEW))))));
    }

    /**
     * A cache represents an intermediary step between a card id and a card object. Creating a Card has some fixed cost
     * in term of database access. Using an id has an unknown cost: none if the card is never accessed, heavy if the
     * card is accessed a lot of time. CardCache ensure that the cost is paid at most once, by waiting for first access
     * to load the data, and then saving them. Since CPU and RAM is usually less of a bottleneck than database access,
     * it may often be worth using this cache.
     *
     * Beware that the card is loaded only once. Change in the database are not reflected, so use it only if you can
     * safely assume that the card has not changed. That is
     * long id;
     * Card card = col.getCard(id);
     * ....
     * Card card2 = col.getCard(id);
     * is not equivalent to
     * long id;
     * Card.Cache cache = new Cache(col, id);
     * Card card = cache.getCard();
     * ....
     * Card card2 = cache.getCard();
     *
     * It is equivalent to:
     * long id;
     * Card.Cache cache = new Cache(col, id);
     * Card card = cache.getCard();
     * ....
     * cache.reload();
     * Card card2 = cache.getCard();
     */
    public static class Cache implements Cloneable {

        @NonNull
        private final Collection mCol;

        private final long mId;

        @Nullable
        private Card mCard;

        public Cache(@NonNull Collection col, long id) {
            mCol = col;
            mId = id;
        }

        /**
         * Copy of cache. Useful to create a copy of a subclass without loosing card if it is loaded.
         */
        protected Cache(Cache cache) {
            mCol = cache.mCol;
            mId = cache.mId;
            if (!ListenerUtil.mutListener.listen(21180)) {
                mCard = cache.mCard;
            }
        }

        /**
         * Copy of cache. Useful to create a copy of a subclass without loosing card if it is loaded.
         */
        public Cache(Card card) {
            mCol = card.mCol;
            mId = card.getId();
            if (!ListenerUtil.mutListener.listen(21181)) {
                mCard = card;
            }
        }

        /**
         * The card with id given at creation. Note that it has content of the time at which the card was loaded, which
         * may have changed in database. So it is not equivalent to getCol().getCard(getId()). If you need fresh data, reload
         * first.
         */
        @NonNull
        public synchronized Card getCard() {
            if (!ListenerUtil.mutListener.listen(21183)) {
                if (mCard == null) {
                    if (!ListenerUtil.mutListener.listen(21182)) {
                        mCard = mCol.getCard(mId);
                    }
                }
            }
            return mCard;
        }

        /**
         * Next access to card will reload the card from the database.
         */
        public synchronized void reload() {
            if (!ListenerUtil.mutListener.listen(21184)) {
                mCard = null;
            }
        }

        public long getId() {
            return mId;
        }

        @NonNull
        public Collection getCol() {
            return mCol;
        }

        @Override
        public int hashCode() {
            return Long.valueOf(mId).hashCode();
        }

        /**
         * The cloned version represents the same card but data are not loaded.
         */
        @NonNull
        public Cache clone() {
            return new Cache(mCol, mId);
        }

        public boolean equals(Object cache) {
            if (!ListenerUtil.mutListener.listen(21185)) {
                if (!(cache instanceof Cache)) {
                    return false;
                }
            }
            return (ListenerUtil.mutListener.listen(21190) ? (mId >= ((Cache) cache).mId) : (ListenerUtil.mutListener.listen(21189) ? (mId <= ((Cache) cache).mId) : (ListenerUtil.mutListener.listen(21188) ? (mId > ((Cache) cache).mId) : (ListenerUtil.mutListener.listen(21187) ? (mId < ((Cache) cache).mId) : (ListenerUtil.mutListener.listen(21186) ? (mId != ((Cache) cache).mId) : (mId == ((Cache) cache).mId))))));
        }

        public void loadQA(boolean reload, boolean browser) {
            if (!ListenerUtil.mutListener.listen(21191)) {
                getCard()._getQA(reload, browser);
            }
        }
    }

    @NonNull
    public static Card[] deepCopyCardArray(@NonNull Card[] originals, @NonNull CancelListener cancelListener) throws CancellationException {
        Collection col = CollectionHelper.getInstance().getCol(AnkiDroidApp.getInstance());
        Card[] copies = new Card[originals.length];
        if (!ListenerUtil.mutListener.listen(21200)) {
            {
                long _loopCounter456 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(21199) ? (i >= originals.length) : (ListenerUtil.mutListener.listen(21198) ? (i <= originals.length) : (ListenerUtil.mutListener.listen(21197) ? (i > originals.length) : (ListenerUtil.mutListener.listen(21196) ? (i != originals.length) : (ListenerUtil.mutListener.listen(21195) ? (i == originals.length) : (i < originals.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter456", ++_loopCounter456);
                    if (!ListenerUtil.mutListener.listen(21193)) {
                        if (cancelListener.isCancelled()) {
                            if (!ListenerUtil.mutListener.listen(21192)) {
                                Timber.i("Cancelled during deep copy, probably memory pressure?");
                            }
                            throw new CancellationException("Cancelled during deep copy");
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(21194)) {
                        // the high performance version would implement .clone() on Card and test it well
                        copies[i] = new Card(col, originals[i].getId());
                    }
                }
            }
        }
        return copies;
    }
}
