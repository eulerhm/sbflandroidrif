/**
 * *************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2013 Houssam Salem <houssam.salem.au@gmail.com>                        *
 *  Copyright (c) 2018 Chris Williams <chris@chrispwill.com>                             *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General private License as published by the Free Software       *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General private License for more details.            *
 *                                                                                       *
 *  You should have received a copy of the GNU General private License along with        *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.libanki.sched;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Pair;
import com.ichi2.anki.R;
import com.ichi2.async.CancelListener;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskManager;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.Deck;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.utils.Time;
import com.ichi2.utils.Assert;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import com.ichi2.utils.SyncStatus;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import static com.ichi2.libanki.Consts.CARD_TYPE_RELEARNING;
import static com.ichi2.libanki.Consts.DECK_DYN;
import static com.ichi2.libanki.Consts.DECK_STD;
import static com.ichi2.libanki.Consts.QUEUE_TYPE_DAY_LEARN_RELEARN;
import static com.ichi2.async.CancelListener.isCancelled;
import static com.ichi2.libanki.sched.AbstractSched.UnburyType.*;
import static com.ichi2.libanki.sched.Counts.Queue.*;
import static com.ichi2.libanki.sched.Counts.Queue;
import static com.ichi2.libanki.stats.Stats.SECONDS_PER_DAY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.ExcessiveClassLength", "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.AvoidBranchingStatementAsLastInLoop", "PMD.SwitchStmtsShouldHaveDefault", "PMD.CollapsibleIfStatements", "PMD.EmptyIfStmt" })
public class SchedV2 extends AbstractSched {

    // Not in libanki
    private static final int[] FACTOR_ADDITION_VALUES = { -150, 0, 150 };

    public static final int RESCHEDULE_FACTOR = Consts.STARTING_FACTOR;

    protected final int mQueueLimit;

    protected int mReportLimit;

    private final int mDynReportLimit;

    protected int mReps;

    protected boolean mHaveQueues;

    protected boolean mHaveCounts;

    @Nullable
    protected Integer mToday;

    public long mDayCutoff;

    private long mLrnCutoff;

    protected int mNewCount;

    protected int mLrnCount;

    protected int mRevCount;

    private int mNewCardModulus;

    // The content change, not the array
    @NonNull
    protected final double[] mEtaCache = new double[] { -1, -1, -1, -1, -1, -1 };

    // Queues
    @NonNull
    protected final SimpleCardQueue mNewQueue = new SimpleCardQueue(this);

    @NonNull
    protected final LrnCardQueue mLrnQueue = new LrnCardQueue(this);

    @NonNull
    protected final SimpleCardQueue mLrnDayQueue = new SimpleCardQueue(this);

    @NonNull
    protected final SimpleCardQueue mRevQueue = new SimpleCardQueue(this);

    @NonNull
    private LinkedList<Long> mNewDids = new LinkedList<>();

    @NonNull
    protected LinkedList<Long> mLrnDids = new LinkedList<>();

    // Not in libanki
    @Nullable
    protected WeakReference<Activity> mContextReference;

    /**
     * The card currently being reviewed.
     *
     * Must not be returned during prefetching (as it is currently shown)
     */
    protected Card mCurrentCard;

    /**
     * The list of parent decks of the current card.
     * Cached for performance .
     *
     *        Null iff mNextCard is null.
     */
    @Nullable
    protected List<Long> mCurrentCardParentsDid;

    /**
     * card types: 0=new, 1=lrn, 2=rev, 3=relrn
     * queue types: 0=new, 1=(re)lrn, 2=rev, 3=day (re)lrn,
     *   4=preview, -1=suspended, -2=sibling buried, -3=manually buried
     * revlog types: 0=lrn, 1=rev, 2=relrn, 3=early review
     * positive revlog intervals are in days (rev), negative in seconds (lrn)
     * odue/odid store original due/did when cards moved to filtered deck
     */
    public SchedV2(@NonNull Collection col) {
        super();
        if (!ListenerUtil.mutListener.listen(15622)) {
            mCol = col;
        }
        mQueueLimit = 50;
        if (!ListenerUtil.mutListener.listen(15623)) {
            mReportLimit = 99999;
        }
        mDynReportLimit = 99999;
        if (!ListenerUtil.mutListener.listen(15624)) {
            mReps = 0;
        }
        if (!ListenerUtil.mutListener.listen(15625)) {
            mToday = null;
        }
        if (!ListenerUtil.mutListener.listen(15626)) {
            mHaveQueues = false;
        }
        if (!ListenerUtil.mutListener.listen(15627)) {
            mHaveCounts = false;
        }
        if (!ListenerUtil.mutListener.listen(15628)) {
            mLrnCutoff = 0;
        }
        if (!ListenerUtil.mutListener.listen(15629)) {
            _updateCutoff();
        }
    }

    /**
     * Pop the next card from the queue. null if finished.
     */
    @Nullable
    public Card getCard() {
        if (!ListenerUtil.mutListener.listen(15630)) {
            _checkDay();
        }
        if (!ListenerUtil.mutListener.listen(15632)) {
            if (!mHaveQueues) {
                if (!ListenerUtil.mutListener.listen(15631)) {
                    resetQueues(false);
                }
            }
        }
        @Nullable
        Card card = _getCard();
        if (!ListenerUtil.mutListener.listen(15636)) {
            if ((ListenerUtil.mutListener.listen(15633) ? (card == null || !mHaveCounts) : (card == null && !mHaveCounts))) {
                if (!ListenerUtil.mutListener.listen(15634)) {
                    // sibling. So let's try to set counts and check again.
                    reset();
                }
                if (!ListenerUtil.mutListener.listen(15635)) {
                    card = _getCard();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15643)) {
            if (card != null) {
                if (!ListenerUtil.mutListener.listen(15638)) {
                    mCol.log(card);
                }
                if (!ListenerUtil.mutListener.listen(15639)) {
                    incrReps();
                }
                if (!ListenerUtil.mutListener.listen(15640)) {
                    // the reviewer.
                    decrementCounts(card);
                }
                if (!ListenerUtil.mutListener.listen(15641)) {
                    setCurrentCard(card);
                }
                if (!ListenerUtil.mutListener.listen(15642)) {
                    card.startTimer();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15637)) {
                    discardCurrentCard();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15645)) {
            if (!mHaveCounts) {
                if (!ListenerUtil.mutListener.listen(15644)) {
                    // Need to reset queues once counts are reset
                    TaskManager.launchCollectionTask(new CollectionTask.Reset());
                }
            }
        }
        return card;
    }

    /**
     * Ensures that reset is executed before the next card is selected
     */
    public void deferReset(@NonNull Card card) {
        if (!ListenerUtil.mutListener.listen(15646)) {
            mHaveQueues = false;
        }
        if (!ListenerUtil.mutListener.listen(15647)) {
            mHaveCounts = false;
        }
        if (!ListenerUtil.mutListener.listen(15648)) {
            setCurrentCard(card);
        }
    }

    public void deferReset() {
        if (!ListenerUtil.mutListener.listen(15649)) {
            mHaveQueues = false;
        }
        if (!ListenerUtil.mutListener.listen(15650)) {
            mHaveCounts = false;
        }
        if (!ListenerUtil.mutListener.listen(15651)) {
            discardCurrentCard();
        }
    }

    public void reset() {
        if (!ListenerUtil.mutListener.listen(15652)) {
            _updateCutoff();
        }
        if (!ListenerUtil.mutListener.listen(15653)) {
            resetCounts(false);
        }
        if (!ListenerUtil.mutListener.listen(15654)) {
            resetQueues(false);
        }
    }

    @Override
    public void resetCounts(@NonNull CancelListener cancelListener) {
        if (!ListenerUtil.mutListener.listen(15655)) {
            resetCounts(true);
        }
    }

    public void resetCounts(boolean checkCutoff) {
        if (!ListenerUtil.mutListener.listen(15656)) {
            resetCounts(null, checkCutoff);
        }
    }

    public void resetCounts() {
        if (!ListenerUtil.mutListener.listen(15657)) {
            resetCounts(null, true);
        }
    }

    /**
     * @param checkCutoff whether we should check cutoff before resetting
     */
    private void resetCounts(@Nullable CancelListener cancelListener, boolean checkCutoff) {
        if (!ListenerUtil.mutListener.listen(15659)) {
            if (checkCutoff) {
                if (!ListenerUtil.mutListener.listen(15658)) {
                    _updateCutoff();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15660)) {
            // In theory it is useless, as anything that change counts should have set mHaveCounts to false
            mHaveCounts = false;
        }
        if (!ListenerUtil.mutListener.listen(15661)) {
            _resetLrnCount(cancelListener);
        }
        if (!ListenerUtil.mutListener.listen(15663)) {
            if (isCancelled(cancelListener)) {
                if (!ListenerUtil.mutListener.listen(15662)) {
                    Timber.v("Cancel computing counts of deck %s", mCol.getDecks().current().getString("name"));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15664)) {
            _resetRevCount(cancelListener);
        }
        if (!ListenerUtil.mutListener.listen(15666)) {
            if (isCancelled(cancelListener)) {
                if (!ListenerUtil.mutListener.listen(15665)) {
                    Timber.v("Cancel computing counts of deck %s", mCol.getDecks().current().getString("name"));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15667)) {
            _resetNewCount(cancelListener);
        }
        if (!ListenerUtil.mutListener.listen(15669)) {
            if (isCancelled(cancelListener)) {
                if (!ListenerUtil.mutListener.listen(15668)) {
                    Timber.v("Cancel computing counts of deck %s", mCol.getDecks().current().getString("name"));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15670)) {
            mHaveCounts = true;
        }
    }

    /**
     * @param checkCutoff whether we should check cutoff before resetting
     */
    private void resetQueues(boolean checkCutoff) {
        if (!ListenerUtil.mutListener.listen(15672)) {
            if (checkCutoff) {
                if (!ListenerUtil.mutListener.listen(15671)) {
                    _updateCutoff();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15673)) {
            _resetLrnQueue();
        }
        if (!ListenerUtil.mutListener.listen(15674)) {
            _resetRevQueue();
        }
        if (!ListenerUtil.mutListener.listen(15675)) {
            _resetNewQueue();
        }
        if (!ListenerUtil.mutListener.listen(15676)) {
            mHaveQueues = true;
        }
    }

    /**
     * Does all actions required to answer the card. That is:
     * Change its interval, due value, queue, mod time, usn, number of step left (if in learning)
     * Put it in learning if required
     * Log the review.
     * Remove from filtered if required.
     * Remove the siblings for the queue for same day spacing
     * Bury siblings if required by the options
     * Overriden
     */
    public void answerCard(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(15677)) {
            mCol.log();
        }
        if (!ListenerUtil.mutListener.listen(15678)) {
            discardCurrentCard();
        }
        if (!ListenerUtil.mutListener.listen(15679)) {
            mCol.markReview(card);
        }
        if (!ListenerUtil.mutListener.listen(15680)) {
            _burySiblings(card);
        }
        if (!ListenerUtil.mutListener.listen(15681)) {
            _answerCard(card, ease);
        }
        if (!ListenerUtil.mutListener.listen(15682)) {
            _updateStats(card, "time", card.timeTaken());
        }
        if (!ListenerUtil.mutListener.listen(15683)) {
            card.setMod(getTime().intTime());
        }
        if (!ListenerUtil.mutListener.listen(15684)) {
            card.setUsn(mCol.usn());
        }
        if (!ListenerUtil.mutListener.listen(15685)) {
            card.flushSched();
        }
    }

    public void _answerCard(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(15687)) {
            if (_previewingCard(card)) {
                if (!ListenerUtil.mutListener.listen(15686)) {
                    _answerCardPreview(card, ease);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15688)) {
            card.incrReps();
        }
        if (!ListenerUtil.mutListener.listen(15698)) {
            if ((ListenerUtil.mutListener.listen(15693) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15692) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15691) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15690) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15689) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW))))))) {
                if (!ListenerUtil.mutListener.listen(15694)) {
                    // came from the new queue, move to learning
                    card.setQueue(Consts.QUEUE_TYPE_LRN);
                }
                if (!ListenerUtil.mutListener.listen(15695)) {
                    card.setType(Consts.CARD_TYPE_LRN);
                }
                if (!ListenerUtil.mutListener.listen(15696)) {
                    // init reps to graduation
                    card.setLeft(_startingLeft(card));
                }
                if (!ListenerUtil.mutListener.listen(15697)) {
                    // update daily limit
                    _updateStats(card, "new");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15718)) {
            if ((ListenerUtil.mutListener.listen(15709) ? ((ListenerUtil.mutListener.listen(15703) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15702) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15701) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15700) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15699) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))) && (ListenerUtil.mutListener.listen(15708) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15707) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15706) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15705) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15704) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(15703) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15702) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15701) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15700) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15699) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))) || (ListenerUtil.mutListener.listen(15708) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15707) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15706) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15705) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15704) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))))) {
                if (!ListenerUtil.mutListener.listen(15717)) {
                    _answerLrnCard(card, ease);
                }
            } else if ((ListenerUtil.mutListener.listen(15714) ? (card.getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(15713) ? (card.getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(15712) ? (card.getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(15711) ? (card.getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(15710) ? (card.getQueue() != Consts.QUEUE_TYPE_REV) : (card.getQueue() == Consts.QUEUE_TYPE_REV))))))) {
                if (!ListenerUtil.mutListener.listen(15715)) {
                    _answerRevCard(card, ease);
                }
                if (!ListenerUtil.mutListener.listen(15716)) {
                    // Update daily limit
                    _updateStats(card, "rev");
                }
            } else {
                throw new RuntimeException("Invalid queue");
            }
        }
        if (!ListenerUtil.mutListener.listen(15725)) {
            // no longer applies
            if ((ListenerUtil.mutListener.listen(15723) ? (card.getODue() >= 0) : (ListenerUtil.mutListener.listen(15722) ? (card.getODue() <= 0) : (ListenerUtil.mutListener.listen(15721) ? (card.getODue() < 0) : (ListenerUtil.mutListener.listen(15720) ? (card.getODue() != 0) : (ListenerUtil.mutListener.listen(15719) ? (card.getODue() == 0) : (card.getODue() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(15724)) {
                    card.setODue(0);
                }
            }
        }
    }

    // code deletes the entries
    public void _answerCardPreview(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(15741)) {
            if ((ListenerUtil.mutListener.listen(15730) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15729) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15728) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15727) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15726) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
                if (!ListenerUtil.mutListener.listen(15738)) {
                    // Repeat after delay
                    card.setQueue(Consts.QUEUE_TYPE_PREVIEW);
                }
                if (!ListenerUtil.mutListener.listen(15739)) {
                    card.setDue(getTime().intTime() + _previewDelay(card));
                }
                if (!ListenerUtil.mutListener.listen(15740)) {
                    mLrnCount += 1;
                }
            } else if ((ListenerUtil.mutListener.listen(15735) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15734) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15733) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15732) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15731) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO))))))) {
                if (!ListenerUtil.mutListener.listen(15736)) {
                    // Restore original card state and remove from filtered deck
                    _restorePreviewCard(card);
                }
                if (!ListenerUtil.mutListener.listen(15737)) {
                    _removeFromFiltered(card);
                }
            } else {
                // This is in place of the assert
                throw new RuntimeException("Invalid ease");
            }
        }
    }

    /**
     * new count, lrn count, rev count.
     */
    @NonNull
    public Counts counts() {
        return counts((CancelListener) null);
    }

    @NonNull
    public Counts counts(@Nullable CancelListener cancelListener) {
        if (!ListenerUtil.mutListener.listen(15743)) {
            if (!mHaveCounts) {
                if (!ListenerUtil.mutListener.listen(15742)) {
                    resetCounts(cancelListener);
                }
            }
        }
        return new Counts(mNewCount, mLrnCount, mRevCount);
    }

    /**
     * Same as counts(), but also count `card`. In practice, we use it because `card` is in the reviewer and that is the
     * number we actually want.
     * Overridden: left / 1000 in V1
     */
    @NonNull
    public Counts counts(@NonNull Card card) {
        Counts counts = counts();
        Queue idx = countIdx(card);
        if (!ListenerUtil.mutListener.listen(15744)) {
            counts.changeCount(idx, 1);
        }
        return counts;
    }

    /**
     * Return counts over next DAYS. Includes today.
     */
    public int dueForecast(int days) {
        // TODO:...
        return 0;
    }

    /**
     * Which of the three numbers shown in reviewer/overview should the card be counted. 0:new, 1:rev, 2: any kind of learning.
     * Overidden: V1 does not have preview
     */
    public Queue countIdx(@NonNull Card card) {
        switch(card.getQueue()) {
            case Consts.QUEUE_TYPE_DAY_LEARN_RELEARN:
            case Consts.QUEUE_TYPE_LRN:
            case Consts.QUEUE_TYPE_PREVIEW:
                return LRN;
            case Consts.QUEUE_TYPE_NEW:
                return NEW;
            case Consts.QUEUE_TYPE_REV:
                return REV;
            default:
                throw new RuntimeException("Index " + card.getQueue() + " does not exists.");
        }
    }

    /**
     * Number of buttons to show in the reviewer for `card`.
     * Overridden
     */
    public int answerButtons(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        if (!ListenerUtil.mutListener.listen(15746)) {
            if ((ListenerUtil.mutListener.listen(15745) ? (card.isInDynamicDeck() || !conf.getBoolean("resched")) : (card.isInDynamicDeck() && !conf.getBoolean("resched")))) {
                return 2;
            }
        }
        return 4;
    }

    protected void _updateStats(@NonNull Card card, @NonNull String type) {
        if (!ListenerUtil.mutListener.listen(15747)) {
            _updateStats(card, type, 1);
        }
    }

    public void _updateStats(@NonNull Card card, @NonNull String type, long cnt) {
        String key = type + "Today";
        long did = card.getDid();
        List<Deck> list = mCol.getDecks().parents(did);
        if (!ListenerUtil.mutListener.listen(15748)) {
            list.add(mCol.getDecks().get(did));
        }
        if (!ListenerUtil.mutListener.listen(15755)) {
            {
                long _loopCounter299 = 0;
                for (Deck g : list) {
                    ListenerUtil.loopListener.listen("_loopCounter299", ++_loopCounter299);
                    JSONArray a = g.getJSONArray(key);
                    if (!ListenerUtil.mutListener.listen(15753)) {
                        // add
                        a.put(1, (ListenerUtil.mutListener.listen(15752) ? (a.getLong(1) % cnt) : (ListenerUtil.mutListener.listen(15751) ? (a.getLong(1) / cnt) : (ListenerUtil.mutListener.listen(15750) ? (a.getLong(1) * cnt) : (ListenerUtil.mutListener.listen(15749) ? (a.getLong(1) - cnt) : (a.getLong(1) + cnt))))));
                    }
                    if (!ListenerUtil.mutListener.listen(15754)) {
                        mCol.getDecks().save(g);
                    }
                }
            }
        }
    }

    public void extendLimits(int newc, int rev) {
        Deck cur = mCol.getDecks().current();
        List<Deck> decks = mCol.getDecks().parents(cur.getLong("id"));
        if (!ListenerUtil.mutListener.listen(15756)) {
            decks.add(cur);
        }
        if (!ListenerUtil.mutListener.listen(15758)) {
            {
                long _loopCounter300 = 0;
                for (long did : mCol.getDecks().children(cur.getLong("id")).values()) {
                    ListenerUtil.loopListener.listen("_loopCounter300", ++_loopCounter300);
                    if (!ListenerUtil.mutListener.listen(15757)) {
                        decks.add(mCol.getDecks().get(did));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15771)) {
            {
                long _loopCounter301 = 0;
                for (Deck g : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter301", ++_loopCounter301);
                    // add
                    JSONArray today = g.getJSONArray("newToday");
                    if (!ListenerUtil.mutListener.listen(15763)) {
                        today.put(1, (ListenerUtil.mutListener.listen(15762) ? (today.getInt(1) % newc) : (ListenerUtil.mutListener.listen(15761) ? (today.getInt(1) / newc) : (ListenerUtil.mutListener.listen(15760) ? (today.getInt(1) * newc) : (ListenerUtil.mutListener.listen(15759) ? (today.getInt(1) + newc) : (today.getInt(1) - newc))))));
                    }
                    if (!ListenerUtil.mutListener.listen(15764)) {
                        today = g.getJSONArray("revToday");
                    }
                    if (!ListenerUtil.mutListener.listen(15769)) {
                        today.put(1, (ListenerUtil.mutListener.listen(15768) ? (today.getInt(1) % rev) : (ListenerUtil.mutListener.listen(15767) ? (today.getInt(1) / rev) : (ListenerUtil.mutListener.listen(15766) ? (today.getInt(1) * rev) : (ListenerUtil.mutListener.listen(15765) ? (today.getInt(1) + rev) : (today.getInt(1) - rev))))));
                    }
                    if (!ListenerUtil.mutListener.listen(15770)) {
                        mCol.getDecks().save(g);
                    }
                }
            }
        }
    }

    protected int _walkingCount(@NonNull LimitMethod limFn, @NonNull CountMethod cntFn) {
        return _walkingCount(limFn, cntFn, null);
    }

    /**
     * @param limFn Method sending a deck to the maximal number of card it can have. Normally into account both limits and cards seen today
     * @param cntFn Method sending a deck to the number of card it has got to see today.
     * @param cancelListener Whether the task is not useful anymore
     * @return -1 if it's cancelled. Sum of the results of cntFn, limited by limFn,
     */
    protected int _walkingCount(@NonNull LimitMethod limFn, @NonNull CountMethod cntFn, @Nullable CancelListener cancelListener) {
        int tot = 0;
        HashMap<Long, Integer> pcounts = new HashMap<>(mCol.getDecks().count());
        if (!ListenerUtil.mutListener.listen(15795)) {
            {
                long _loopCounter304 = 0;
                // for each of the active decks
                for (long did : mCol.getDecks().active()) {
                    ListenerUtil.loopListener.listen("_loopCounter304", ++_loopCounter304);
                    if (!ListenerUtil.mutListener.listen(15772)) {
                        if (isCancelled(cancelListener))
                            return -1;
                    }
                    // get the individual deck's limit
                    int lim = limFn.operation(mCol.getDecks().get(did));
                    if (!ListenerUtil.mutListener.listen(15778)) {
                        if ((ListenerUtil.mutListener.listen(15777) ? (lim >= 0) : (ListenerUtil.mutListener.listen(15776) ? (lim <= 0) : (ListenerUtil.mutListener.listen(15775) ? (lim > 0) : (ListenerUtil.mutListener.listen(15774) ? (lim < 0) : (ListenerUtil.mutListener.listen(15773) ? (lim != 0) : (lim == 0))))))) {
                            continue;
                        }
                    }
                    // check the parents
                    List<Deck> parents = mCol.getDecks().parents(did);
                    if (!ListenerUtil.mutListener.listen(15782)) {
                        {
                            long _loopCounter302 = 0;
                            for (Deck p : parents) {
                                ListenerUtil.loopListener.listen("_loopCounter302", ++_loopCounter302);
                                // add if missing
                                long id = p.getLong("id");
                                if (!ListenerUtil.mutListener.listen(15780)) {
                                    if (!pcounts.containsKey(id)) {
                                        if (!ListenerUtil.mutListener.listen(15779)) {
                                            pcounts.put(id, limFn.operation(p));
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(15781)) {
                                    // take minimum of child and parent
                                    lim = Math.min(pcounts.get(id), lim);
                                }
                            }
                        }
                    }
                    // see how many cards we actually have
                    int cnt = cntFn.operation(did, lim);
                    if (!ListenerUtil.mutListener.listen(15788)) {
                        {
                            long _loopCounter303 = 0;
                            // if non-zero, decrement from parents counts
                            for (Deck p : parents) {
                                ListenerUtil.loopListener.listen("_loopCounter303", ++_loopCounter303);
                                long id = p.getLong("id");
                                if (!ListenerUtil.mutListener.listen(15787)) {
                                    pcounts.put(id, (ListenerUtil.mutListener.listen(15786) ? (pcounts.get(id) % cnt) : (ListenerUtil.mutListener.listen(15785) ? (pcounts.get(id) / cnt) : (ListenerUtil.mutListener.listen(15784) ? (pcounts.get(id) * cnt) : (ListenerUtil.mutListener.listen(15783) ? (pcounts.get(id) + cnt) : (pcounts.get(id) - cnt))))));
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(15793)) {
                        // we may also be a parent
                        pcounts.put(did, (ListenerUtil.mutListener.listen(15792) ? (lim % cnt) : (ListenerUtil.mutListener.listen(15791) ? (lim / cnt) : (ListenerUtil.mutListener.listen(15790) ? (lim * cnt) : (ListenerUtil.mutListener.listen(15789) ? (lim + cnt) : (lim - cnt))))));
                    }
                    if (!ListenerUtil.mutListener.listen(15794)) {
                        // and add to running total
                        tot += cnt;
                    }
                }
            }
        }
        return tot;
    }

    /**
     * Returns [deckname, did, rev, lrn, new]
     *
     * Return nulls when deck task is cancelled.
     */
    @NonNull
    public List<DeckDueTreeNode> deckDueList() {
        return deckDueList(null);
    }

    // Overridden
    @Nullable
    public List<DeckDueTreeNode> deckDueList(@Nullable CancelListener collectionTask) {
        if (!ListenerUtil.mutListener.listen(15796)) {
            _checkDay();
        }
        if (!ListenerUtil.mutListener.listen(15797)) {
            mCol.getDecks().checkIntegrity();
        }
        ArrayList<Deck> decks = mCol.getDecks().allSorted();
        HashMap<String, Integer[]> lims = new HashMap<>(decks.size());
        ArrayList<DeckDueTreeNode> deckNodes = new ArrayList<>(decks.size());
        Decks.Node childMap = mCol.getDecks().childMap();
        if (!ListenerUtil.mutListener.listen(15805)) {
            {
                long _loopCounter305 = 0;
                for (Deck deck : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter305", ++_loopCounter305);
                    if (!ListenerUtil.mutListener.listen(15798)) {
                        if (isCancelled(collectionTask)) {
                            return null;
                        }
                    }
                    String deckName = deck.getString("name");
                    String p = Decks.parent(deckName);
                    // new
                    int nlim = _deckNewLimitSingle(deck, false);
                    Integer plim = null;
                    if (!ListenerUtil.mutListener.listen(15802)) {
                        if (!TextUtils.isEmpty(p)) {
                            Integer[] parentLims = lims.get(Decks.normalizeName(p));
                            if (!ListenerUtil.mutListener.listen(15799)) {
                                // 'temporary for diagnosis of bug #6383'
                                Assert.that(parentLims != null, "Deck %s is supposed to have parent %s. It has not be found.", deckName, p);
                            }
                            if (!ListenerUtil.mutListener.listen(15800)) {
                                nlim = Math.min(nlim, parentLims[0]);
                            }
                            if (!ListenerUtil.mutListener.listen(15801)) {
                                // reviews
                                plim = parentLims[1];
                            }
                        }
                    }
                    int _new = _newForDeck(deck.getLong("id"), nlim);
                    // learning
                    int lrn = _lrnForDeck(deck.getLong("id"));
                    // reviews
                    int rlim = _deckRevLimitSingle(deck, plim, false);
                    int rev = _revForDeck(deck.getLong("id"), rlim, childMap);
                    if (!ListenerUtil.mutListener.listen(15803)) {
                        // save to list
                        deckNodes.add(new DeckDueTreeNode(mCol, deck.getString("name"), deck.getLong("id"), rev, lrn, _new));
                    }
                    if (!ListenerUtil.mutListener.listen(15804)) {
                        // add deck as a parent
                        lims.put(Decks.normalizeName(deck.getString("name")), new Integer[] { nlim, rlim });
                    }
                }
            }
        }
        return deckNodes;
    }

    /**
     * Similar to deck due tree, but ignore the number of cards.
     *
     *     It may takes a lot of time to compute the number of card, it
     *     requires multiple database access by deck.  Ignoring this number
     *     lead to the creation of a tree more quickly.
     */
    @Override
    @NonNull
    public List<DeckTreeNode> quickDeckDueTree() {
        ArrayList<Deck> decks = mCol.getDecks().allSorted();
        // Similar to deckDueList
        ArrayList<DeckTreeNode> data = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(15807)) {
            {
                long _loopCounter306 = 0;
                for (JSONObject deck : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter306", ++_loopCounter306);
                    DeckTreeNode g = new DeckTreeNode(mCol, deck.getString("name"), deck.getLong("id"));
                    if (!ListenerUtil.mutListener.listen(15806)) {
                        data.add(g);
                    }
                }
            }
        }
        return _groupChildren(data, false);
    }

    @NonNull
    public List<DeckDueTreeNode> deckDueTree() {
        return deckDueTree(null);
    }

    @Nullable
    public List<DeckDueTreeNode> deckDueTree(@Nullable CancelListener cancelListener) {
        List<DeckDueTreeNode> deckDueTree = deckDueList(cancelListener);
        if (!ListenerUtil.mutListener.listen(15808)) {
            if (deckDueTree == null) {
                return null;
            }
        }
        return _groupChildren(deckDueTree, true);
    }

    @NonNull
    private <T extends AbstractDeckTreeNode<T>> List<T> _groupChildren(@NonNull List<T> decks, boolean checkDone) {
        if (!ListenerUtil.mutListener.listen(15809)) {
            // sort based on name's components
            Collections.sort(decks);
        }
        // then run main function
        return _groupChildrenMain(decks, checkDone);
    }

    @NonNull
    protected <T extends AbstractDeckTreeNode<T>> List<T> _groupChildrenMain(@NonNull List<T> decks, boolean checkDone) {
        return _groupChildrenMain(decks, 0, checkDone);
    }

    /**
     *        @return the tree structure of all decks from @descandants, starting
     *        at specified depth.
     *
     *        @param descendants a list of decks of dept at least depth, having all
     *        the same first depth name elements, sorted in deck order.
     *        @param depth The depth of the tree we are creating
     *        @param checkDone whether the set of deck was checked. If
     *        false, we can't assume all decks have parents and that there
     *        is no duplicate. Instead, we'll ignore problems.
     */
    @NonNull
    protected <T extends AbstractDeckTreeNode<T>> List<T> _groupChildrenMain(@NonNull List<T> descendants, int depth, boolean checkDone) {
        List<T> children = new ArrayList<>();
        // group and recurse
        ListIterator<T> it = descendants.listIterator();
        if (!ListenerUtil.mutListener.listen(15836)) {
            {
                long _loopCounter308 = 0;
                while (it.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter308", ++_loopCounter308);
                    T child = it.next();
                    String head = child.getDeckNameComponent(depth);
                    List<T> descendantsOfChild = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(15817)) {
                        /* Compose the "children" node list. The children is a
             * list of all the nodes that proceed the current one that
             * contain the same at depth `depth`, except for the
             * current one itself.  I.e., they are subdecks that stem
             * from this descendant.  This is our version of python's
             * itertools.groupby. */
                        if ((ListenerUtil.mutListener.listen(15815) ? (!checkDone || (ListenerUtil.mutListener.listen(15814) ? (child.getDepth() >= depth) : (ListenerUtil.mutListener.listen(15813) ? (child.getDepth() <= depth) : (ListenerUtil.mutListener.listen(15812) ? (child.getDepth() > depth) : (ListenerUtil.mutListener.listen(15811) ? (child.getDepth() < depth) : (ListenerUtil.mutListener.listen(15810) ? (child.getDepth() == depth) : (child.getDepth() != depth))))))) : (!checkDone && (ListenerUtil.mutListener.listen(15814) ? (child.getDepth() >= depth) : (ListenerUtil.mutListener.listen(15813) ? (child.getDepth() <= depth) : (ListenerUtil.mutListener.listen(15812) ? (child.getDepth() > depth) : (ListenerUtil.mutListener.listen(15811) ? (child.getDepth() < depth) : (ListenerUtil.mutListener.listen(15810) ? (child.getDepth() == depth) : (child.getDepth() != depth))))))))) {
                            Deck deck = mCol.getDecks().get(child.getDid());
                            if (!ListenerUtil.mutListener.listen(15816)) {
                                Timber.d("Deck %s (%d)'s parent is missing. Ignoring for quick display.", deck.getString("name"), child.getDid());
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(15829)) {
                        {
                            long _loopCounter307 = 0;
                            while (it.hasNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter307", ++_loopCounter307);
                                T descendantOfChild = it.next();
                                if (!ListenerUtil.mutListener.listen(15828)) {
                                    if (head.equals(descendantOfChild.getDeckNameComponent(depth))) {
                                        if (!ListenerUtil.mutListener.listen(15826)) {
                                            // Same head - add to tail of current head.
                                            if ((ListenerUtil.mutListener.listen(15824) ? (!checkDone || (ListenerUtil.mutListener.listen(15823) ? (descendantOfChild.getDepth() >= depth) : (ListenerUtil.mutListener.listen(15822) ? (descendantOfChild.getDepth() <= depth) : (ListenerUtil.mutListener.listen(15821) ? (descendantOfChild.getDepth() > depth) : (ListenerUtil.mutListener.listen(15820) ? (descendantOfChild.getDepth() < depth) : (ListenerUtil.mutListener.listen(15819) ? (descendantOfChild.getDepth() != depth) : (descendantOfChild.getDepth() == depth))))))) : (!checkDone && (ListenerUtil.mutListener.listen(15823) ? (descendantOfChild.getDepth() >= depth) : (ListenerUtil.mutListener.listen(15822) ? (descendantOfChild.getDepth() <= depth) : (ListenerUtil.mutListener.listen(15821) ? (descendantOfChild.getDepth() > depth) : (ListenerUtil.mutListener.listen(15820) ? (descendantOfChild.getDepth() < depth) : (ListenerUtil.mutListener.listen(15819) ? (descendantOfChild.getDepth() != depth) : (descendantOfChild.getDepth() == depth))))))))) {
                                                Deck deck = mCol.getDecks().get(descendantOfChild.getDid());
                                                if (!ListenerUtil.mutListener.listen(15825)) {
                                                    Timber.d("Deck %s (%d)'s is a duplicate name. Ignoring for quick display.", deck.getString("name"), descendantOfChild.getDid());
                                                }
                                                continue;
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(15827)) {
                                            descendantsOfChild.add(descendantOfChild);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(15818)) {
                                            // head in the next iteration of the outer loop.
                                            it.previous();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // the children_sDescendant set contains direct children_sDescendant but not the children_sDescendant of children_sDescendant...
                    List<T> childrenNode = _groupChildrenMain(descendantsOfChild, (ListenerUtil.mutListener.listen(15833) ? (depth % 1) : (ListenerUtil.mutListener.listen(15832) ? (depth / 1) : (ListenerUtil.mutListener.listen(15831) ? (depth * 1) : (ListenerUtil.mutListener.listen(15830) ? (depth - 1) : (depth + 1))))), checkDone);
                    if (!ListenerUtil.mutListener.listen(15834)) {
                        child.setChildren(childrenNode, "std".equals(getName()));
                    }
                    if (!ListenerUtil.mutListener.listen(15835)) {
                        children.add(child);
                    }
                }
            }
        }
        return children;
    }

    /**
     * Return the next due card, or null.
     * Overridden: V1 does not allow dayLearnFirst
     */
    @Nullable
    protected Card _getCard() {
        // learning card due?
        @Nullable
        Card c = _getLrnCard(false);
        if (!ListenerUtil.mutListener.listen(15837)) {
            if (c != null) {
                return c;
            }
        }
        if (!ListenerUtil.mutListener.listen(15840)) {
            // new first, or time for one?
            if (_timeForNewCard()) {
                if (!ListenerUtil.mutListener.listen(15838)) {
                    c = _getNewCard();
                }
                if (!ListenerUtil.mutListener.listen(15839)) {
                    if (c != null) {
                        return c;
                    }
                }
            }
        }
        // Day learning first and card due?
        boolean dayLearnFirst = mCol.getConf().optBoolean("dayLearnFirst", false);
        if (!ListenerUtil.mutListener.listen(15843)) {
            if (dayLearnFirst) {
                if (!ListenerUtil.mutListener.listen(15841)) {
                    c = _getLrnDayCard();
                }
                if (!ListenerUtil.mutListener.listen(15842)) {
                    if (c != null) {
                        return c;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15844)) {
            // Card due for review?
            c = _getRevCard();
        }
        if (!ListenerUtil.mutListener.listen(15845)) {
            if (c != null) {
                return c;
            }
        }
        if (!ListenerUtil.mutListener.listen(15848)) {
            // day learning card due?
            if (!dayLearnFirst) {
                if (!ListenerUtil.mutListener.listen(15846)) {
                    c = _getLrnDayCard();
                }
                if (!ListenerUtil.mutListener.listen(15847)) {
                    if (c != null) {
                        return c;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15849)) {
            // New cards left?
            c = _getNewCard();
        }
        if (!ListenerUtil.mutListener.listen(15850)) {
            if (c != null) {
                return c;
            }
        }
        // collapse or finish
        return _getLrnCard(true);
    }

    /**
     * similar to _getCard but only fill the queues without taking the card.
     * Returns lists that may contain the next cards.
     */
    @NonNull
    protected CardQueue<? extends Card.Cache>[] _fillNextCard() {
        if (!ListenerUtil.mutListener.listen(15851)) {
            // learning card due?
            if (_preloadLrnCard(false)) {
                return new CardQueue<?>[] { mLrnQueue };
            }
        }
        if (!ListenerUtil.mutListener.listen(15853)) {
            // new first, or time for one?
            if (_timeForNewCard()) {
                if (!ListenerUtil.mutListener.listen(15852)) {
                    if (_fillNew()) {
                        return new CardQueue<?>[] { mLrnQueue, mNewQueue };
                    }
                }
            }
        }
        // Day learning first and card due?
        boolean dayLearnFirst = mCol.getConf().optBoolean("dayLearnFirst", false);
        if (!ListenerUtil.mutListener.listen(15855)) {
            if (dayLearnFirst) {
                if (!ListenerUtil.mutListener.listen(15854)) {
                    if (_fillLrnDay()) {
                        return new CardQueue<?>[] { mLrnQueue, mLrnDayQueue };
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15856)) {
            // Card due for review?
            if (_fillRev()) {
                return new CardQueue<?>[] { mLrnQueue, mRevQueue };
            }
        }
        if (!ListenerUtil.mutListener.listen(15858)) {
            // day learning card due?
            if (!dayLearnFirst) {
                if (!ListenerUtil.mutListener.listen(15857)) {
                    if (_fillLrnDay()) {
                        return new CardQueue<?>[] { mLrnQueue, mLrnDayQueue };
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15859)) {
            // New cards left?
            if (_fillNew()) {
                return new CardQueue<?>[] { mLrnQueue, mNewQueue };
            }
        }
        if (!ListenerUtil.mutListener.listen(15860)) {
            // collapse or finish
            if (_preloadLrnCard(true)) {
                return new CardQueue<?>[] { mLrnQueue };
            }
        }
        return new CardQueue<?>[] {};
    }

    /**
     * pre load the potential next card. It may loads many card because, depending on the time taken, the next card may
     * be a card in review or not.
     */
    public void preloadNextCard() {
        if (!ListenerUtil.mutListener.listen(15861)) {
            _checkDay();
        }
        if (!ListenerUtil.mutListener.listen(15863)) {
            if (!mHaveCounts) {
                if (!ListenerUtil.mutListener.listen(15862)) {
                    resetCounts(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15865)) {
            if (!mHaveQueues) {
                if (!ListenerUtil.mutListener.listen(15864)) {
                    resetQueues(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15867)) {
            {
                long _loopCounter309 = 0;
                for (CardQueue<? extends Card.Cache> caches : _fillNextCard()) {
                    ListenerUtil.loopListener.listen("_loopCounter309", ++_loopCounter309);
                    if (!ListenerUtil.mutListener.listen(15866)) {
                        caches.loadFirstCard();
                    }
                }
            }
        }
    }

    protected void _resetNewCount() {
        if (!ListenerUtil.mutListener.listen(15868)) {
            _resetNewCount(null);
        }
    }

    protected void _resetNewCount(@Nullable CancelListener cancelListener) {
        if (!ListenerUtil.mutListener.listen(15869)) {
            mNewCount = _walkingCount(g -> _deckNewLimitSingle(g, true), this::_cntFnNew, cancelListener);
        }
    }

    // Used as an argument for _walkingCount() in _resetNewCount() above
    @SuppressWarnings("unused")
    protected int _cntFnNew(long did, int lim) {
        return mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ? AND queue = " + Consts.QUEUE_TYPE_NEW + " AND id != ? LIMIT ?)", did, currentCardId(), lim);
    }

    private void _resetNew() {
        if (!ListenerUtil.mutListener.listen(15870)) {
            _resetNewCount();
        }
        if (!ListenerUtil.mutListener.listen(15871)) {
            _resetNewQueue();
        }
    }

    private void _resetNewQueue() {
        if (!ListenerUtil.mutListener.listen(15872)) {
            mNewDids = new LinkedList<>(mCol.getDecks().active());
        }
        if (!ListenerUtil.mutListener.listen(15873)) {
            mNewQueue.clear();
        }
        if (!ListenerUtil.mutListener.listen(15874)) {
            _updateNewCardRatio();
        }
    }

    /**
     *        @return The id of the note currently in the reviewer. 0 if no
     *        such card.
     */
    protected long currentCardNid() {
        Card currentCard = mCurrentCard;
        if (!ListenerUtil.mutListener.listen(15875)) {
            /* mCurrentCard may be set to null when the reviewer gets closed. So we copy it to be sure to avoid
           NullPointerException */
            if (mCurrentCard == null) {
                /* This method is used to determine whether two cards are siblings. Since 0 is not a valid nid, all cards
            will have a nid distinct from 0. As it is used in sql statement, it is not possible to just use a function
            areSiblings()*/
                return 0;
            }
        }
        return currentCard.getNid();
    }

    /**
     *        @return The id of the card currently in the reviewer. 0 if no
     *        such card.
     */
    protected long currentCardId() {
        if (!ListenerUtil.mutListener.listen(15876)) {
            if (mCurrentCard == null) {
                /* This method is used to ensure that query don't return current card. Since 0 is not a valid nid, all cards
            will have a nid distinct from 0. As it is used in sql statement, it is not possible to just use a function
            areSiblings()*/
                return 0;
            }
        }
        return mCurrentCard.getId();
    }

    protected boolean _fillNew() {
        return _fillNew(false);
    }

    private boolean _fillNew(boolean allowSibling) {
        if (!ListenerUtil.mutListener.listen(15877)) {
            if (!mNewQueue.isEmpty()) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(15884)) {
            if ((ListenerUtil.mutListener.listen(15883) ? (mHaveCounts || (ListenerUtil.mutListener.listen(15882) ? (mNewCount >= 0) : (ListenerUtil.mutListener.listen(15881) ? (mNewCount <= 0) : (ListenerUtil.mutListener.listen(15880) ? (mNewCount > 0) : (ListenerUtil.mutListener.listen(15879) ? (mNewCount < 0) : (ListenerUtil.mutListener.listen(15878) ? (mNewCount != 0) : (mNewCount == 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(15882) ? (mNewCount >= 0) : (ListenerUtil.mutListener.listen(15881) ? (mNewCount <= 0) : (ListenerUtil.mutListener.listen(15880) ? (mNewCount > 0) : (ListenerUtil.mutListener.listen(15879) ? (mNewCount < 0) : (ListenerUtil.mutListener.listen(15878) ? (mNewCount != 0) : (mNewCount == 0))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15896)) {
            {
                long _loopCounter311 = 0;
                while (!mNewDids.isEmpty()) {
                    ListenerUtil.loopListener.listen("_loopCounter311", ++_loopCounter311);
                    long did = mNewDids.getFirst();
                    int lim = Math.min(mQueueLimit, _deckNewLimit(did, true));
                    if (!ListenerUtil.mutListener.listen(15894)) {
                        if ((ListenerUtil.mutListener.listen(15889) ? (lim >= 0) : (ListenerUtil.mutListener.listen(15888) ? (lim <= 0) : (ListenerUtil.mutListener.listen(15887) ? (lim > 0) : (ListenerUtil.mutListener.listen(15886) ? (lim < 0) : (ListenerUtil.mutListener.listen(15885) ? (lim == 0) : (lim != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(15890)) {
                                mNewQueue.clear();
                            }
                            String idName = (allowSibling) ? "id" : "nid";
                            long id = (allowSibling) ? currentCardId() : currentCardNid();
                            if (!ListenerUtil.mutListener.listen(15892)) {
                                {
                                    long _loopCounter310 = 0;
                                    // fill the queue with the current did
                                    for (long cid : mCol.getDb().queryLongList("SELECT id FROM cards WHERE did = ? AND queue = " + Consts.QUEUE_TYPE_NEW + " AND " + idName + "!= ? ORDER BY due, ord LIMIT ?", did, id, lim)) {
                                        ListenerUtil.loopListener.listen("_loopCounter310", ++_loopCounter310);
                                        if (!ListenerUtil.mutListener.listen(15891)) {
                                            mNewQueue.add(cid);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(15893)) {
                                if (!mNewQueue.isEmpty()) {
                                    // in _getNewCard().
                                    return true;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(15895)) {
                        // nothing left in the deck; move to next
                        mNewDids.remove();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15904)) {
            if ((ListenerUtil.mutListener.listen(15902) ? (mHaveCounts || (ListenerUtil.mutListener.listen(15901) ? (mNewCount >= 0) : (ListenerUtil.mutListener.listen(15900) ? (mNewCount <= 0) : (ListenerUtil.mutListener.listen(15899) ? (mNewCount > 0) : (ListenerUtil.mutListener.listen(15898) ? (mNewCount < 0) : (ListenerUtil.mutListener.listen(15897) ? (mNewCount == 0) : (mNewCount != 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(15901) ? (mNewCount >= 0) : (ListenerUtil.mutListener.listen(15900) ? (mNewCount <= 0) : (ListenerUtil.mutListener.listen(15899) ? (mNewCount > 0) : (ListenerUtil.mutListener.listen(15898) ? (mNewCount < 0) : (ListenerUtil.mutListener.listen(15897) ? (mNewCount == 0) : (mNewCount != 0))))))))) {
                if (!ListenerUtil.mutListener.listen(15903)) {
                    // removed from the queue but not buried
                    _resetNew();
                }
                return _fillNew(true);
            }
        }
        return false;
    }

    @Nullable
    protected Card _getNewCard() {
        if (!ListenerUtil.mutListener.listen(15905)) {
            if (_fillNew()) {
                // mNewCount -= 1; see decrementCounts()
                return mNewQueue.removeFirstCard();
            }
        }
        return null;
    }

    private void _updateNewCardRatio() {
        if (!ListenerUtil.mutListener.listen(15933)) {
            if ((ListenerUtil.mutListener.listen(15910) ? (mCol.getConf().getInt("newSpread") >= Consts.NEW_CARDS_DISTRIBUTE) : (ListenerUtil.mutListener.listen(15909) ? (mCol.getConf().getInt("newSpread") <= Consts.NEW_CARDS_DISTRIBUTE) : (ListenerUtil.mutListener.listen(15908) ? (mCol.getConf().getInt("newSpread") > Consts.NEW_CARDS_DISTRIBUTE) : (ListenerUtil.mutListener.listen(15907) ? (mCol.getConf().getInt("newSpread") < Consts.NEW_CARDS_DISTRIBUTE) : (ListenerUtil.mutListener.listen(15906) ? (mCol.getConf().getInt("newSpread") != Consts.NEW_CARDS_DISTRIBUTE) : (mCol.getConf().getInt("newSpread") == Consts.NEW_CARDS_DISTRIBUTE))))))) {
                if (!ListenerUtil.mutListener.listen(15932)) {
                    if ((ListenerUtil.mutListener.listen(15915) ? (mNewCount >= 0) : (ListenerUtil.mutListener.listen(15914) ? (mNewCount <= 0) : (ListenerUtil.mutListener.listen(15913) ? (mNewCount > 0) : (ListenerUtil.mutListener.listen(15912) ? (mNewCount < 0) : (ListenerUtil.mutListener.listen(15911) ? (mNewCount == 0) : (mNewCount != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(15924)) {
                            mNewCardModulus = (ListenerUtil.mutListener.listen(15923) ? (((ListenerUtil.mutListener.listen(15919) ? (mNewCount % mRevCount) : (ListenerUtil.mutListener.listen(15918) ? (mNewCount / mRevCount) : (ListenerUtil.mutListener.listen(15917) ? (mNewCount * mRevCount) : (ListenerUtil.mutListener.listen(15916) ? (mNewCount - mRevCount) : (mNewCount + mRevCount)))))) % mNewCount) : (ListenerUtil.mutListener.listen(15922) ? (((ListenerUtil.mutListener.listen(15919) ? (mNewCount % mRevCount) : (ListenerUtil.mutListener.listen(15918) ? (mNewCount / mRevCount) : (ListenerUtil.mutListener.listen(15917) ? (mNewCount * mRevCount) : (ListenerUtil.mutListener.listen(15916) ? (mNewCount - mRevCount) : (mNewCount + mRevCount)))))) * mNewCount) : (ListenerUtil.mutListener.listen(15921) ? (((ListenerUtil.mutListener.listen(15919) ? (mNewCount % mRevCount) : (ListenerUtil.mutListener.listen(15918) ? (mNewCount / mRevCount) : (ListenerUtil.mutListener.listen(15917) ? (mNewCount * mRevCount) : (ListenerUtil.mutListener.listen(15916) ? (mNewCount - mRevCount) : (mNewCount + mRevCount)))))) - mNewCount) : (ListenerUtil.mutListener.listen(15920) ? (((ListenerUtil.mutListener.listen(15919) ? (mNewCount % mRevCount) : (ListenerUtil.mutListener.listen(15918) ? (mNewCount / mRevCount) : (ListenerUtil.mutListener.listen(15917) ? (mNewCount * mRevCount) : (ListenerUtil.mutListener.listen(15916) ? (mNewCount - mRevCount) : (mNewCount + mRevCount)))))) + mNewCount) : (((ListenerUtil.mutListener.listen(15919) ? (mNewCount % mRevCount) : (ListenerUtil.mutListener.listen(15918) ? (mNewCount / mRevCount) : (ListenerUtil.mutListener.listen(15917) ? (mNewCount * mRevCount) : (ListenerUtil.mutListener.listen(15916) ? (mNewCount - mRevCount) : (mNewCount + mRevCount)))))) / mNewCount)))));
                        }
                        if (!ListenerUtil.mutListener.listen(15931)) {
                            // if there are cards to review, ensure modulo >= 2
                            if ((ListenerUtil.mutListener.listen(15929) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(15928) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(15927) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(15926) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(15925) ? (mRevCount == 0) : (mRevCount != 0))))))) {
                                if (!ListenerUtil.mutListener.listen(15930)) {
                                    mNewCardModulus = Math.max(2, mNewCardModulus);
                                }
                            }
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15934)) {
            mNewCardModulus = 0;
        }
    }

    /**
     * @return True if it's time to display a new card when distributing.
     */
    protected boolean _timeForNewCard() {
        if ((ListenerUtil.mutListener.listen(15940) ? (mHaveCounts || (ListenerUtil.mutListener.listen(15939) ? (mNewCount >= 0) : (ListenerUtil.mutListener.listen(15938) ? (mNewCount <= 0) : (ListenerUtil.mutListener.listen(15937) ? (mNewCount > 0) : (ListenerUtil.mutListener.listen(15936) ? (mNewCount < 0) : (ListenerUtil.mutListener.listen(15935) ? (mNewCount != 0) : (mNewCount == 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(15939) ? (mNewCount >= 0) : (ListenerUtil.mutListener.listen(15938) ? (mNewCount <= 0) : (ListenerUtil.mutListener.listen(15937) ? (mNewCount > 0) : (ListenerUtil.mutListener.listen(15936) ? (mNewCount < 0) : (ListenerUtil.mutListener.listen(15935) ? (mNewCount != 0) : (mNewCount == 0))))))))) {
            return false;
        }
        @Consts.NEW_CARD_ORDER
        int spread = mCol.getConf().getInt("newSpread");
        if ((ListenerUtil.mutListener.listen(15945) ? (spread >= Consts.NEW_CARDS_LAST) : (ListenerUtil.mutListener.listen(15944) ? (spread <= Consts.NEW_CARDS_LAST) : (ListenerUtil.mutListener.listen(15943) ? (spread > Consts.NEW_CARDS_LAST) : (ListenerUtil.mutListener.listen(15942) ? (spread < Consts.NEW_CARDS_LAST) : (ListenerUtil.mutListener.listen(15941) ? (spread != Consts.NEW_CARDS_LAST) : (spread == Consts.NEW_CARDS_LAST))))))) {
            return false;
        } else if ((ListenerUtil.mutListener.listen(15950) ? (spread >= Consts.NEW_CARDS_FIRST) : (ListenerUtil.mutListener.listen(15949) ? (spread <= Consts.NEW_CARDS_FIRST) : (ListenerUtil.mutListener.listen(15948) ? (spread > Consts.NEW_CARDS_FIRST) : (ListenerUtil.mutListener.listen(15947) ? (spread < Consts.NEW_CARDS_FIRST) : (ListenerUtil.mutListener.listen(15946) ? (spread != Consts.NEW_CARDS_FIRST) : (spread == Consts.NEW_CARDS_FIRST))))))) {
            return true;
        } else if ((ListenerUtil.mutListener.listen(15955) ? (mNewCardModulus >= 0) : (ListenerUtil.mutListener.listen(15954) ? (mNewCardModulus <= 0) : (ListenerUtil.mutListener.listen(15953) ? (mNewCardModulus > 0) : (ListenerUtil.mutListener.listen(15952) ? (mNewCardModulus < 0) : (ListenerUtil.mutListener.listen(15951) ? (mNewCardModulus == 0) : (mNewCardModulus != 0))))))) {
            // random. This will occur only for the first card of review.
            return ((ListenerUtil.mutListener.listen(15970) ? ((ListenerUtil.mutListener.listen(15960) ? (mReps >= 0) : (ListenerUtil.mutListener.listen(15959) ? (mReps <= 0) : (ListenerUtil.mutListener.listen(15958) ? (mReps > 0) : (ListenerUtil.mutListener.listen(15957) ? (mReps < 0) : (ListenerUtil.mutListener.listen(15956) ? (mReps == 0) : (mReps != 0)))))) || ((ListenerUtil.mutListener.listen(15969) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) >= 0) : (ListenerUtil.mutListener.listen(15968) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) <= 0) : (ListenerUtil.mutListener.listen(15967) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) > 0) : (ListenerUtil.mutListener.listen(15966) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) < 0) : (ListenerUtil.mutListener.listen(15965) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) != 0) : ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) == 0)))))))) : ((ListenerUtil.mutListener.listen(15960) ? (mReps >= 0) : (ListenerUtil.mutListener.listen(15959) ? (mReps <= 0) : (ListenerUtil.mutListener.listen(15958) ? (mReps > 0) : (ListenerUtil.mutListener.listen(15957) ? (mReps < 0) : (ListenerUtil.mutListener.listen(15956) ? (mReps == 0) : (mReps != 0)))))) && ((ListenerUtil.mutListener.listen(15969) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) >= 0) : (ListenerUtil.mutListener.listen(15968) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) <= 0) : (ListenerUtil.mutListener.listen(15967) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) > 0) : (ListenerUtil.mutListener.listen(15966) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) < 0) : (ListenerUtil.mutListener.listen(15965) ? ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) != 0) : ((ListenerUtil.mutListener.listen(15964) ? (mReps / mNewCardModulus) : (ListenerUtil.mutListener.listen(15963) ? (mReps * mNewCardModulus) : (ListenerUtil.mutListener.listen(15962) ? (mReps - mNewCardModulus) : (ListenerUtil.mutListener.listen(15961) ? (mReps + mNewCardModulus) : (mReps % mNewCardModulus))))) == 0))))))))));
        } else {
            return false;
        }
    }

    /**
     * @param considerCurrentCard Whether current card should be counted if it is in this deck
     */
    protected int _deckNewLimit(long did, boolean considerCurrentCard) {
        return _deckNewLimit(did, null, considerCurrentCard);
    }

    /**
     * @param considerCurrentCard Whether current card should be counted if it is in this deck
     */
    protected int _deckNewLimit(long did, LimitMethod fn, boolean considerCurrentCard) {
        if (!ListenerUtil.mutListener.listen(15972)) {
            if (fn == null) {
                if (!ListenerUtil.mutListener.listen(15971)) {
                    fn = (g -> _deckNewLimitSingle(g, considerCurrentCard));
                }
            }
        }
        @NonNull
        List<Deck> decks = mCol.getDecks().parents(did);
        if (!ListenerUtil.mutListener.listen(15973)) {
            decks.add(mCol.getDecks().get(did));
        }
        int lim = -1;
        // for the deck and each of its parents
        int rem = 0;
        if (!ListenerUtil.mutListener.listen(15983)) {
            {
                long _loopCounter312 = 0;
                for (Deck g : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter312", ++_loopCounter312);
                    if (!ListenerUtil.mutListener.listen(15974)) {
                        rem = fn.operation(g);
                    }
                    if (!ListenerUtil.mutListener.listen(15982)) {
                        if ((ListenerUtil.mutListener.listen(15979) ? (lim >= -1) : (ListenerUtil.mutListener.listen(15978) ? (lim <= -1) : (ListenerUtil.mutListener.listen(15977) ? (lim > -1) : (ListenerUtil.mutListener.listen(15976) ? (lim < -1) : (ListenerUtil.mutListener.listen(15975) ? (lim != -1) : (lim == -1))))))) {
                            if (!ListenerUtil.mutListener.listen(15981)) {
                                lim = rem;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(15980)) {
                                lim = Math.min(rem, lim);
                            }
                        }
                    }
                }
            }
        }
        return lim;
    }

    /**
     * New count for a single deck.
     */
    public int _newForDeck(long did, int lim) {
        if (!ListenerUtil.mutListener.listen(15989)) {
            if ((ListenerUtil.mutListener.listen(15988) ? (lim >= 0) : (ListenerUtil.mutListener.listen(15987) ? (lim <= 0) : (ListenerUtil.mutListener.listen(15986) ? (lim > 0) : (ListenerUtil.mutListener.listen(15985) ? (lim < 0) : (ListenerUtil.mutListener.listen(15984) ? (lim != 0) : (lim == 0))))))) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(15990)) {
            lim = Math.min(lim, mReportLimit);
        }
        return mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ? AND queue = " + Consts.QUEUE_TYPE_NEW + " LIMIT ?)", did, lim);
    }

    /**
     * Maximal number of new card still to see today in deck g. It's computed as:
     * the number of new card to see by day according to the deck optinos
     * minus the number of new cards seen today in deck d or a descendant
     * plus the number of extra new cards to see today in deck d, a parent or a descendant.
     *
     * Limits of its ancestors are not applied.
     * @param considerCurrentCard whether the current card should be taken from the limit (if it belongs to this deck)
     */
    public int _deckNewLimitSingle(@NonNull Deck g, boolean considerCurrentCard) {
        if (!ListenerUtil.mutListener.listen(15991)) {
            if (g.getInt("dyn") == DECK_DYN) {
                return mDynReportLimit;
            }
        }
        long did = g.getLong("id");
        @NonNull
        DeckConfig c = mCol.getDecks().confForDid(did);
        int lim = Math.max(0, (ListenerUtil.mutListener.listen(15995) ? (c.getJSONObject("new").getInt("perDay") % g.getJSONArray("newToday").getInt(1)) : (ListenerUtil.mutListener.listen(15994) ? (c.getJSONObject("new").getInt("perDay") / g.getJSONArray("newToday").getInt(1)) : (ListenerUtil.mutListener.listen(15993) ? (c.getJSONObject("new").getInt("perDay") * g.getJSONArray("newToday").getInt(1)) : (ListenerUtil.mutListener.listen(15992) ? (c.getJSONObject("new").getInt("perDay") + g.getJSONArray("newToday").getInt(1)) : (c.getJSONObject("new").getInt("perDay") - g.getJSONArray("newToday").getInt(1)))))));
        if (!ListenerUtil.mutListener.listen(15998)) {
            // So currentCard does not have to be taken into consideration in this method
            if ((ListenerUtil.mutListener.listen(15996) ? (considerCurrentCard || currentCardIsInQueueWithDeck(Consts.QUEUE_TYPE_NEW, did)) : (considerCurrentCard && currentCardIsInQueueWithDeck(Consts.QUEUE_TYPE_NEW, did)))) {
                if (!ListenerUtil.mutListener.listen(15997)) {
                    lim--;
                }
            }
        }
        return lim;
    }

    public int totalNewForCurrentDeck() {
        return mCol.getDb().queryScalar("SELECT count() FROM cards WHERE id IN (SELECT id FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_NEW + " LIMIT ?)", mReportLimit);
    }

    private boolean _updateLrnCutoff(boolean force) {
        long nextCutoff = getTime().intTime() + mCol.getConf().getInt("collapseTime");
        if (!ListenerUtil.mutListener.listen(16010)) {
            if ((ListenerUtil.mutListener.listen(16008) ? ((ListenerUtil.mutListener.listen(16007) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) >= 60) : (ListenerUtil.mutListener.listen(16006) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) <= 60) : (ListenerUtil.mutListener.listen(16005) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) < 60) : (ListenerUtil.mutListener.listen(16004) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) != 60) : (ListenerUtil.mutListener.listen(16003) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) == 60) : ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) > 60)))))) && force) : ((ListenerUtil.mutListener.listen(16007) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) >= 60) : (ListenerUtil.mutListener.listen(16006) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) <= 60) : (ListenerUtil.mutListener.listen(16005) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) < 60) : (ListenerUtil.mutListener.listen(16004) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) != 60) : (ListenerUtil.mutListener.listen(16003) ? ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) == 60) : ((ListenerUtil.mutListener.listen(16002) ? (nextCutoff % mLrnCutoff) : (ListenerUtil.mutListener.listen(16001) ? (nextCutoff / mLrnCutoff) : (ListenerUtil.mutListener.listen(16000) ? (nextCutoff * mLrnCutoff) : (ListenerUtil.mutListener.listen(15999) ? (nextCutoff + mLrnCutoff) : (nextCutoff - mLrnCutoff))))) > 60)))))) || force))) {
                if (!ListenerUtil.mutListener.listen(16009)) {
                    mLrnCutoff = nextCutoff;
                }
                return true;
            }
        }
        return false;
    }

    private void _maybeResetLrn(boolean force) {
        if (!ListenerUtil.mutListener.listen(16012)) {
            if (_updateLrnCutoff(force)) {
                if (!ListenerUtil.mutListener.listen(16011)) {
                    _resetLrn();
                }
            }
        }
    }

    // Overridden: V1 has less queues
    protected void _resetLrnCount() {
        if (!ListenerUtil.mutListener.listen(16013)) {
            _resetLrnCount(null);
        }
    }

    protected void _resetLrnCount(@Nullable CancelListener cancelListener) {
        if (!ListenerUtil.mutListener.listen(16014)) {
            _updateLrnCutoff(true);
        }
        if (!ListenerUtil.mutListener.listen(16015)) {
            // sub-day
            mLrnCount = mCol.getDb().queryScalar("SELECT count() FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_LRN + " AND id != ? AND due < ?", currentCardId(), mLrnCutoff);
        }
        if (!ListenerUtil.mutListener.listen(16016)) {
            if (isCancelled(cancelListener))
                return;
        }
        if (!ListenerUtil.mutListener.listen(16017)) {
            // day
            mLrnCount += mCol.getDb().queryScalar("SELECT count() FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ? AND id != ?", mToday, currentCardId());
        }
        if (!ListenerUtil.mutListener.listen(16018)) {
            if (isCancelled(cancelListener))
                return;
        }
        if (!ListenerUtil.mutListener.listen(16019)) {
            // previews
            mLrnCount += mCol.getDb().queryScalar("SELECT count() FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_PREVIEW + " AND id != ? ", currentCardId());
        }
    }

    // Overriden: _updateLrnCutoff not called in V1
    protected void _resetLrn() {
        if (!ListenerUtil.mutListener.listen(16020)) {
            _resetLrnCount();
        }
        if (!ListenerUtil.mutListener.listen(16021)) {
            _resetLrnQueue();
        }
    }

    protected void _resetLrnQueue() {
        if (!ListenerUtil.mutListener.listen(16022)) {
            mLrnQueue.clear();
        }
        if (!ListenerUtil.mutListener.listen(16023)) {
            mLrnDayQueue.clear();
        }
        if (!ListenerUtil.mutListener.listen(16024)) {
            mLrnDids = mCol.getDecks().active();
        }
    }

    // Overridden: a single kind of queue in V1
    protected boolean _fillLrn() {
        if ((ListenerUtil.mutListener.listen(16030) ? (mHaveCounts || (ListenerUtil.mutListener.listen(16029) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16028) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16027) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16026) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16025) ? (mLrnCount != 0) : (mLrnCount == 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(16029) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16028) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16027) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16026) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16025) ? (mLrnCount != 0) : (mLrnCount == 0))))))))) {
            return false;
        }
        if (!mLrnQueue.isEmpty()) {
            return true;
        }
        long cutoff = getTime().intTime() + mCol.getConf().getLong("collapseTime");
        if (!ListenerUtil.mutListener.listen(16031)) {
            mLrnQueue.clear();
        }
        /* Difference with upstream: Current card can't come in the queue.
             *
             * In standard usage, a card is not requested before the previous card is marked as reviewed. However, if we
             * decide to query a second card sooner, we don't want to get the same card a second time. This simulate
             * _getLrnCard which did remove the card from the queue. _sortIntoLrn will add the card back to the queue if
             * required when the card is reviewed.
             */
        try (Cursor cur = mCol.getDb().query("SELECT due, id FROM cards WHERE did IN " + _deckLimit() + " AND queue IN (" + Consts.QUEUE_TYPE_LRN + ", " + Consts.QUEUE_TYPE_PREVIEW + ") AND due < ?" + " AND id != ? LIMIT ?", cutoff, currentCardId(), mReportLimit)) {
            if (!ListenerUtil.mutListener.listen(16032)) {
                mLrnQueue.setFilled();
            }
            if (!ListenerUtil.mutListener.listen(16034)) {
                {
                    long _loopCounter313 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter313", ++_loopCounter313);
                        if (!ListenerUtil.mutListener.listen(16033)) {
                            mLrnQueue.add(cur.getLong(0), cur.getLong(1));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(16035)) {
                // as it arrives sorted by did first, we need to sort it
                mLrnQueue.sort();
            }
            return !mLrnQueue.isEmpty();
        }
    }

    // Overidden: no _maybeResetLrn in V1
    @Nullable
    protected Card _getLrnCard(boolean collapse) {
        if (!ListenerUtil.mutListener.listen(16042)) {
            _maybeResetLrn((ListenerUtil.mutListener.listen(16041) ? (collapse || (ListenerUtil.mutListener.listen(16040) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16039) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16038) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16037) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16036) ? (mLrnCount != 0) : (mLrnCount == 0))))))) : (collapse && (ListenerUtil.mutListener.listen(16040) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16039) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16038) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16037) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16036) ? (mLrnCount != 0) : (mLrnCount == 0)))))))));
        }
        if (!ListenerUtil.mutListener.listen(16051)) {
            if (_fillLrn()) {
                long cutoff = getTime().intTime();
                if (!ListenerUtil.mutListener.listen(16044)) {
                    if (collapse) {
                        if (!ListenerUtil.mutListener.listen(16043)) {
                            cutoff += mCol.getConf().getInt("collapseTime");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16050)) {
                    if ((ListenerUtil.mutListener.listen(16049) ? (mLrnQueue.getFirstDue() >= cutoff) : (ListenerUtil.mutListener.listen(16048) ? (mLrnQueue.getFirstDue() <= cutoff) : (ListenerUtil.mutListener.listen(16047) ? (mLrnQueue.getFirstDue() > cutoff) : (ListenerUtil.mutListener.listen(16046) ? (mLrnQueue.getFirstDue() != cutoff) : (ListenerUtil.mutListener.listen(16045) ? (mLrnQueue.getFirstDue() == cutoff) : (mLrnQueue.getFirstDue() < cutoff))))))) {
                        return mLrnQueue.removeFirstCard();
                    }
                }
            }
        }
        return null;
    }

    protected boolean _preloadLrnCard(boolean collapse) {
        if (!ListenerUtil.mutListener.listen(16058)) {
            _maybeResetLrn((ListenerUtil.mutListener.listen(16057) ? (collapse || (ListenerUtil.mutListener.listen(16056) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16055) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16054) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16053) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16052) ? (mLrnCount != 0) : (mLrnCount == 0))))))) : (collapse && (ListenerUtil.mutListener.listen(16056) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16055) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16054) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16053) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16052) ? (mLrnCount != 0) : (mLrnCount == 0)))))))));
        }
        if (!ListenerUtil.mutListener.listen(16066)) {
            if (_fillLrn()) {
                long cutoff = getTime().intTime();
                if (!ListenerUtil.mutListener.listen(16060)) {
                    if (collapse) {
                        if (!ListenerUtil.mutListener.listen(16059)) {
                            cutoff += mCol.getConf().getInt("collapseTime");
                        }
                    }
                }
                // mLrnCount -= 1; see decrementCounts()
                return (ListenerUtil.mutListener.listen(16065) ? (mLrnQueue.getFirstDue() >= cutoff) : (ListenerUtil.mutListener.listen(16064) ? (mLrnQueue.getFirstDue() <= cutoff) : (ListenerUtil.mutListener.listen(16063) ? (mLrnQueue.getFirstDue() > cutoff) : (ListenerUtil.mutListener.listen(16062) ? (mLrnQueue.getFirstDue() != cutoff) : (ListenerUtil.mutListener.listen(16061) ? (mLrnQueue.getFirstDue() == cutoff) : (mLrnQueue.getFirstDue() < cutoff))))));
            }
        }
        return false;
    }

    // daily learning
    protected boolean _fillLrnDay() {
        if (!ListenerUtil.mutListener.listen(16073)) {
            if ((ListenerUtil.mutListener.listen(16072) ? (mHaveCounts || (ListenerUtil.mutListener.listen(16071) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16070) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16069) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16068) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16067) ? (mLrnCount != 0) : (mLrnCount == 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(16071) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(16070) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(16069) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(16068) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(16067) ? (mLrnCount != 0) : (mLrnCount == 0))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(16074)) {
            if (!mLrnDayQueue.isEmpty()) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(16089)) {
            {
                long _loopCounter315 = 0;
                while (!mLrnDids.isEmpty()) {
                    ListenerUtil.loopListener.listen("_loopCounter315", ++_loopCounter315);
                    long did = mLrnDids.getFirst();
                    if (!ListenerUtil.mutListener.listen(16075)) {
                        // fill the queue with the current did
                        mLrnDayQueue.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(16077)) {
                        {
                            long _loopCounter314 = 0;
                            /* Difference with upstream:
                 * Current card can't come in the queue.
                 *
                 * In standard usage, a card is not requested before
                 * the previous card is marked as reviewed. However,
                 * if we decide to query a second card sooner, we
                 * don't want to get the same card a second time. This
                 * simulate _getLrnDayCard which did remove the card
                 * from the queue.
                 */
                            for (long cid : mCol.getDb().queryLongList("SELECT id FROM cards WHERE did = ? AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ? and id != ? LIMIT ?", did, mToday, currentCardId(), mQueueLimit)) {
                                ListenerUtil.loopListener.listen("_loopCounter314", ++_loopCounter314);
                                if (!ListenerUtil.mutListener.listen(16076)) {
                                    mLrnDayQueue.add(cid);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16087)) {
                        if (!mLrnDayQueue.isEmpty()) {
                            // order
                            Random r = new Random();
                            if (!ListenerUtil.mutListener.listen(16078)) {
                                r.setSeed(mToday);
                            }
                            if (!ListenerUtil.mutListener.listen(16079)) {
                                mLrnDayQueue.shuffle(r);
                            }
                            if (!ListenerUtil.mutListener.listen(16086)) {
                                // is the current did empty?
                                if ((ListenerUtil.mutListener.listen(16084) ? (mLrnDayQueue.size() >= mQueueLimit) : (ListenerUtil.mutListener.listen(16083) ? (mLrnDayQueue.size() <= mQueueLimit) : (ListenerUtil.mutListener.listen(16082) ? (mLrnDayQueue.size() > mQueueLimit) : (ListenerUtil.mutListener.listen(16081) ? (mLrnDayQueue.size() != mQueueLimit) : (ListenerUtil.mutListener.listen(16080) ? (mLrnDayQueue.size() == mQueueLimit) : (mLrnDayQueue.size() < mQueueLimit))))))) {
                                    if (!ListenerUtil.mutListener.listen(16085)) {
                                        mLrnDids.remove();
                                    }
                                }
                            }
                            return true;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16088)) {
                        // nothing left in the deck; move to next
                        mLrnDids.remove();
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    protected Card _getLrnDayCard() {
        if (!ListenerUtil.mutListener.listen(16090)) {
            if (_fillLrnDay()) {
                // mLrnCount -= 1; see decrementCounts()
                return mLrnDayQueue.removeFirstCard();
            }
        }
        return null;
    }

    // Overriden
    protected void _answerLrnCard(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        JSONObject conf = _lrnConf(card);
        @Consts.CARD_TYPE
        int type;
        if ((ListenerUtil.mutListener.listen(16101) ? ((ListenerUtil.mutListener.listen(16095) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16094) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16093) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16092) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16091) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(16100) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16099) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16098) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16097) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16096) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))) : ((ListenerUtil.mutListener.listen(16095) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16094) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16093) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16092) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16091) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(16100) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16099) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16098) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16097) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16096) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))))) {
            type = Consts.CARD_TYPE_REV;
        } else {
            type = Consts.CARD_TYPE_NEW;
        }
        // lrnCount was decremented once when card was fetched
        int lastLeft = card.getLeft();
        boolean leaving = false;
        if (!ListenerUtil.mutListener.listen(16138)) {
            // immediate graduate?
            if ((ListenerUtil.mutListener.listen(16106) ? (ease >= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16105) ? (ease <= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16104) ? (ease > Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16103) ? (ease < Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16102) ? (ease != Consts.BUTTON_FOUR) : (ease == Consts.BUTTON_FOUR))))))) {
                if (!ListenerUtil.mutListener.listen(16136)) {
                    _rescheduleAsRev(card, conf, true);
                }
                if (!ListenerUtil.mutListener.listen(16137)) {
                    leaving = true;
                }
            } else if ((ListenerUtil.mutListener.listen(16111) ? (ease >= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16110) ? (ease <= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16109) ? (ease > Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16108) ? (ease < Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16107) ? (ease != Consts.BUTTON_THREE) : (ease == Consts.BUTTON_THREE))))))) {
                if (!ListenerUtil.mutListener.listen(16135)) {
                    // graduation time?
                    if ((ListenerUtil.mutListener.listen(16131) ? ((ListenerUtil.mutListener.listen(16126) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(16125) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(16124) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(16123) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) >= 0) : (ListenerUtil.mutListener.listen(16130) ? ((ListenerUtil.mutListener.listen(16126) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(16125) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(16124) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(16123) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) > 0) : (ListenerUtil.mutListener.listen(16129) ? ((ListenerUtil.mutListener.listen(16126) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(16125) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(16124) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(16123) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) < 0) : (ListenerUtil.mutListener.listen(16128) ? ((ListenerUtil.mutListener.listen(16126) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(16125) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(16124) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(16123) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) != 0) : (ListenerUtil.mutListener.listen(16127) ? ((ListenerUtil.mutListener.listen(16126) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(16125) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(16124) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(16123) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) == 0) : ((ListenerUtil.mutListener.listen(16126) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(16125) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(16124) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(16123) ? (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(16122) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16121) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16120) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16119) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) <= 0))))))) {
                        if (!ListenerUtil.mutListener.listen(16133)) {
                            _rescheduleAsRev(card, conf, false);
                        }
                        if (!ListenerUtil.mutListener.listen(16134)) {
                            leaving = true;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(16132)) {
                            _moveToNextStep(card, conf);
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(16116) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16115) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16114) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16113) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16112) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO))))))) {
                if (!ListenerUtil.mutListener.listen(16118)) {
                    _repeatStep(card, conf);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16117)) {
                    // move back to first step
                    _moveToFirstStep(card, conf);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16139)) {
            _logLrn(card, ease, conf, leaving, type, lastLeft);
        }
    }

    protected void _updateRevIvlOnFail(@NonNull Card card, @NonNull JSONObject conf) {
        if (!ListenerUtil.mutListener.listen(16140)) {
            card.setLastIvl(card.getIvl());
        }
        if (!ListenerUtil.mutListener.listen(16141)) {
            card.setIvl(_lapseIvl(card, conf));
        }
    }

    private int _moveToFirstStep(@NonNull Card card, @NonNull JSONObject conf) {
        if (!ListenerUtil.mutListener.listen(16142)) {
            card.setLeft(_startingLeft(card));
        }
        if (!ListenerUtil.mutListener.listen(16149)) {
            // relearning card?
            if ((ListenerUtil.mutListener.listen(16147) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16146) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16145) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16144) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16143) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))) {
                if (!ListenerUtil.mutListener.listen(16148)) {
                    _updateRevIvlOnFail(card, conf);
                }
            }
        }
        return _rescheduleLrnCard(card, conf);
    }

    private void _moveToNextStep(@NonNull Card card, @NonNull JSONObject conf) {
        // decrement real left count and recalculate left today
        int left = (ListenerUtil.mutListener.listen(16157) ? (((ListenerUtil.mutListener.listen(16153) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16152) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16151) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16150) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(16156) ? (((ListenerUtil.mutListener.listen(16153) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16152) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16151) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16150) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(16155) ? (((ListenerUtil.mutListener.listen(16153) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16152) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16151) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16150) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(16154) ? (((ListenerUtil.mutListener.listen(16153) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16152) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16151) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16150) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(16153) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16152) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16151) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16150) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1)))));
        if (!ListenerUtil.mutListener.listen(16162)) {
            card.setLeft((ListenerUtil.mutListener.listen(16161) ? (_leftToday(conf.getJSONArray("delays"), left) % 1000) : (ListenerUtil.mutListener.listen(16160) ? (_leftToday(conf.getJSONArray("delays"), left) / 1000) : (ListenerUtil.mutListener.listen(16159) ? (_leftToday(conf.getJSONArray("delays"), left) - 1000) : (ListenerUtil.mutListener.listen(16158) ? (_leftToday(conf.getJSONArray("delays"), left) + 1000) : (_leftToday(conf.getJSONArray("delays"), left) * 1000))))) + left);
        }
        if (!ListenerUtil.mutListener.listen(16163)) {
            _rescheduleLrnCard(card, conf);
        }
    }

    private void _repeatStep(@NonNull Card card, @NonNull JSONObject conf) {
        int delay = _delayForRepeatingGrade(conf, card.getLeft());
        if (!ListenerUtil.mutListener.listen(16164)) {
            _rescheduleLrnCard(card, conf, delay);
        }
    }

    private int _rescheduleLrnCard(@NonNull Card card, @NonNull JSONObject conf) {
        return _rescheduleLrnCard(card, conf, null);
    }

    private int _rescheduleLrnCard(@NonNull Card card, @NonNull JSONObject conf, @Nullable Integer delay) {
        if (!ListenerUtil.mutListener.listen(16166)) {
            // normal delay for the current step?
            if (delay == null) {
                if (!ListenerUtil.mutListener.listen(16165)) {
                    delay = _delayForGrade(conf, card.getLeft());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16167)) {
            card.setDue(getTime().intTime() + delay);
        }
        if (!ListenerUtil.mutListener.listen(16221)) {
            // due today?
            if ((ListenerUtil.mutListener.listen(16172) ? (card.getDue() >= mDayCutoff) : (ListenerUtil.mutListener.listen(16171) ? (card.getDue() <= mDayCutoff) : (ListenerUtil.mutListener.listen(16170) ? (card.getDue() > mDayCutoff) : (ListenerUtil.mutListener.listen(16169) ? (card.getDue() != mDayCutoff) : (ListenerUtil.mutListener.listen(16168) ? (card.getDue() == mDayCutoff) : (card.getDue() < mDayCutoff))))))) {
                // Add some randomness, up to 5 minutes or 25%
                int maxExtra = Math.min(300, (int) ((ListenerUtil.mutListener.listen(16194) ? (delay % 0.25) : (ListenerUtil.mutListener.listen(16193) ? (delay / 0.25) : (ListenerUtil.mutListener.listen(16192) ? (delay - 0.25) : (ListenerUtil.mutListener.listen(16191) ? (delay + 0.25) : (delay * 0.25)))))));
                int fuzz = new Random().nextInt(maxExtra);
                if (!ListenerUtil.mutListener.listen(16203)) {
                    card.setDue(Math.min((ListenerUtil.mutListener.listen(16198) ? (mDayCutoff % 1) : (ListenerUtil.mutListener.listen(16197) ? (mDayCutoff / 1) : (ListenerUtil.mutListener.listen(16196) ? (mDayCutoff * 1) : (ListenerUtil.mutListener.listen(16195) ? (mDayCutoff + 1) : (mDayCutoff - 1))))), (ListenerUtil.mutListener.listen(16202) ? (card.getDue() % fuzz) : (ListenerUtil.mutListener.listen(16201) ? (card.getDue() / fuzz) : (ListenerUtil.mutListener.listen(16200) ? (card.getDue() * fuzz) : (ListenerUtil.mutListener.listen(16199) ? (card.getDue() - fuzz) : (card.getDue() + fuzz)))))));
                }
                if (!ListenerUtil.mutListener.listen(16204)) {
                    card.setQueue(Consts.QUEUE_TYPE_LRN);
                }
                if (!ListenerUtil.mutListener.listen(16220)) {
                    if ((ListenerUtil.mutListener.listen(16209) ? (card.getDue() >= (getTime().intTime() + mCol.getConf().getInt("collapseTime"))) : (ListenerUtil.mutListener.listen(16208) ? (card.getDue() <= (getTime().intTime() + mCol.getConf().getInt("collapseTime"))) : (ListenerUtil.mutListener.listen(16207) ? (card.getDue() > (getTime().intTime() + mCol.getConf().getInt("collapseTime"))) : (ListenerUtil.mutListener.listen(16206) ? (card.getDue() != (getTime().intTime() + mCol.getConf().getInt("collapseTime"))) : (ListenerUtil.mutListener.listen(16205) ? (card.getDue() == (getTime().intTime() + mCol.getConf().getInt("collapseTime"))) : (card.getDue() < (getTime().intTime() + mCol.getConf().getInt("collapseTime"))))))))) {
                        if (!ListenerUtil.mutListener.listen(16210)) {
                            mLrnCount += 1;
                        }
                        if (!ListenerUtil.mutListener.listen(16218)) {
                            // it twice in a row
                            if ((ListenerUtil.mutListener.listen(16212) ? ((ListenerUtil.mutListener.listen(16211) ? (!mLrnQueue.isEmpty() || revCount() == 0) : (!mLrnQueue.isEmpty() && revCount() == 0)) || newCount() == 0) : ((ListenerUtil.mutListener.listen(16211) ? (!mLrnQueue.isEmpty() || revCount() == 0) : (!mLrnQueue.isEmpty() && revCount() == 0)) && newCount() == 0))) {
                                long smallestDue = mLrnQueue.getFirstDue();
                                if (!ListenerUtil.mutListener.listen(16217)) {
                                    card.setDue(Math.max(card.getDue(), (ListenerUtil.mutListener.listen(16216) ? (smallestDue % 1) : (ListenerUtil.mutListener.listen(16215) ? (smallestDue / 1) : (ListenerUtil.mutListener.listen(16214) ? (smallestDue * 1) : (ListenerUtil.mutListener.listen(16213) ? (smallestDue - 1) : (smallestDue + 1)))))));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(16219)) {
                            _sortIntoLrn(card.getDue(), card.getId());
                        }
                    }
                }
            } else {
                // the card is due in one or more days, so we need to use the day learn queue
                long ahead = (ListenerUtil.mutListener.listen(16184) ? (((ListenerUtil.mutListener.listen(16180) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16179) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16178) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16177) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) % 1) : (ListenerUtil.mutListener.listen(16183) ? (((ListenerUtil.mutListener.listen(16180) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16179) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16178) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16177) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) / 1) : (ListenerUtil.mutListener.listen(16182) ? (((ListenerUtil.mutListener.listen(16180) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16179) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16178) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16177) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) * 1) : (ListenerUtil.mutListener.listen(16181) ? (((ListenerUtil.mutListener.listen(16180) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16179) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16178) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16177) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) - 1) : (((ListenerUtil.mutListener.listen(16180) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16179) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16178) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16177) ? (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(16176) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(16175) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(16174) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(16173) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) + 1)))));
                if (!ListenerUtil.mutListener.listen(16189)) {
                    card.setDue((ListenerUtil.mutListener.listen(16188) ? (mToday % ahead) : (ListenerUtil.mutListener.listen(16187) ? (mToday / ahead) : (ListenerUtil.mutListener.listen(16186) ? (mToday * ahead) : (ListenerUtil.mutListener.listen(16185) ? (mToday - ahead) : (mToday + ahead))))));
                }
                if (!ListenerUtil.mutListener.listen(16190)) {
                    card.setQueue(Consts.QUEUE_TYPE_DAY_LEARN_RELEARN);
                }
            }
        }
        return delay;
    }

    protected int _delayForGrade(JSONObject conf, int left) {
        if (!ListenerUtil.mutListener.listen(16226)) {
            left = (ListenerUtil.mutListener.listen(16225) ? (left / 1000) : (ListenerUtil.mutListener.listen(16224) ? (left * 1000) : (ListenerUtil.mutListener.listen(16223) ? (left - 1000) : (ListenerUtil.mutListener.listen(16222) ? (left + 1000) : (left % 1000)))));
        }
        try {
            double delay;
            JSONArray delays = conf.getJSONArray("delays");
            int len = delays.length();
            try {
                delay = delays.getDouble((ListenerUtil.mutListener.listen(16235) ? (len % left) : (ListenerUtil.mutListener.listen(16234) ? (len / left) : (ListenerUtil.mutListener.listen(16233) ? (len * left) : (ListenerUtil.mutListener.listen(16232) ? (len + left) : (len - left))))));
            } catch (JSONException e) {
                if ((ListenerUtil.mutListener.listen(16231) ? (conf.getJSONArray("delays").length() >= 0) : (ListenerUtil.mutListener.listen(16230) ? (conf.getJSONArray("delays").length() <= 0) : (ListenerUtil.mutListener.listen(16229) ? (conf.getJSONArray("delays").length() < 0) : (ListenerUtil.mutListener.listen(16228) ? (conf.getJSONArray("delays").length() != 0) : (ListenerUtil.mutListener.listen(16227) ? (conf.getJSONArray("delays").length() == 0) : (conf.getJSONArray("delays").length() > 0))))))) {
                    delay = conf.getJSONArray("delays").getDouble(0);
                } else {
                    // user deleted final step; use dummy value
                    delay = 1.0;
                }
            }
            return (int) ((ListenerUtil.mutListener.listen(16239) ? (delay % 60.0) : (ListenerUtil.mutListener.listen(16238) ? (delay / 60.0) : (ListenerUtil.mutListener.listen(16237) ? (delay - 60.0) : (ListenerUtil.mutListener.listen(16236) ? (delay + 60.0) : (delay * 60.0))))));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private int _delayForRepeatingGrade(@NonNull JSONObject conf, int left) {
        // halfway between last and  next
        int delay1 = _delayForGrade(conf, left);
        int delay2;
        if ((ListenerUtil.mutListener.listen(16244) ? (conf.getJSONArray("delays").length() >= 1) : (ListenerUtil.mutListener.listen(16243) ? (conf.getJSONArray("delays").length() <= 1) : (ListenerUtil.mutListener.listen(16242) ? (conf.getJSONArray("delays").length() < 1) : (ListenerUtil.mutListener.listen(16241) ? (conf.getJSONArray("delays").length() != 1) : (ListenerUtil.mutListener.listen(16240) ? (conf.getJSONArray("delays").length() == 1) : (conf.getJSONArray("delays").length() > 1))))))) {
            delay2 = _delayForGrade(conf, (ListenerUtil.mutListener.listen(16252) ? (left % 1) : (ListenerUtil.mutListener.listen(16251) ? (left / 1) : (ListenerUtil.mutListener.listen(16250) ? (left * 1) : (ListenerUtil.mutListener.listen(16249) ? (left + 1) : (left - 1))))));
        } else {
            delay2 = (ListenerUtil.mutListener.listen(16248) ? (delay1 % 2) : (ListenerUtil.mutListener.listen(16247) ? (delay1 / 2) : (ListenerUtil.mutListener.listen(16246) ? (delay1 - 2) : (ListenerUtil.mutListener.listen(16245) ? (delay1 + 2) : (delay1 * 2)))));
        }
        return (ListenerUtil.mutListener.listen(16260) ? (((ListenerUtil.mutListener.listen(16256) ? (delay1 % Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16255) ? (delay1 / Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16254) ? (delay1 * Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16253) ? (delay1 - Math.max(delay1, delay2)) : (delay1 + Math.max(delay1, delay2))))))) % 2) : (ListenerUtil.mutListener.listen(16259) ? (((ListenerUtil.mutListener.listen(16256) ? (delay1 % Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16255) ? (delay1 / Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16254) ? (delay1 * Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16253) ? (delay1 - Math.max(delay1, delay2)) : (delay1 + Math.max(delay1, delay2))))))) * 2) : (ListenerUtil.mutListener.listen(16258) ? (((ListenerUtil.mutListener.listen(16256) ? (delay1 % Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16255) ? (delay1 / Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16254) ? (delay1 * Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16253) ? (delay1 - Math.max(delay1, delay2)) : (delay1 + Math.max(delay1, delay2))))))) - 2) : (ListenerUtil.mutListener.listen(16257) ? (((ListenerUtil.mutListener.listen(16256) ? (delay1 % Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16255) ? (delay1 / Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16254) ? (delay1 * Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16253) ? (delay1 - Math.max(delay1, delay2)) : (delay1 + Math.max(delay1, delay2))))))) + 2) : (((ListenerUtil.mutListener.listen(16256) ? (delay1 % Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16255) ? (delay1 / Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16254) ? (delay1 * Math.max(delay1, delay2)) : (ListenerUtil.mutListener.listen(16253) ? (delay1 - Math.max(delay1, delay2)) : (delay1 + Math.max(delay1, delay2))))))) / 2)))));
    }

    // Overridden: RELEARNING does not exists in V1
    @NonNull
    protected JSONObject _lrnConf(@NonNull Card card) {
        if ((ListenerUtil.mutListener.listen(16271) ? ((ListenerUtil.mutListener.listen(16265) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16264) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16263) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16262) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16261) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(16270) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16269) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16268) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16267) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16266) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))) : ((ListenerUtil.mutListener.listen(16265) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16264) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16263) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16262) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16261) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(16270) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16269) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16268) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16267) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16266) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))))) {
            return _lapseConf(card);
        } else {
            return _newConf(card);
        }
    }

    // Overriden
    protected void _rescheduleAsRev(@NonNull Card card, @NonNull JSONObject conf, boolean early) {
        boolean lapse = ((ListenerUtil.mutListener.listen(16282) ? ((ListenerUtil.mutListener.listen(16276) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16275) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16274) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16273) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16272) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(16281) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16280) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16279) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16278) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16277) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))) : ((ListenerUtil.mutListener.listen(16276) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16275) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16274) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16273) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16272) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(16281) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16280) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16279) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16278) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16277) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING)))))))));
        if (!ListenerUtil.mutListener.listen(16285)) {
            if (lapse) {
                if (!ListenerUtil.mutListener.listen(16284)) {
                    _rescheduleGraduatingLapse(card, early);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16283)) {
                    _rescheduleNew(card, conf, early);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16287)) {
            // if we were dynamic, graduating means moving back to the old deck
            if (card.isInDynamicDeck()) {
                if (!ListenerUtil.mutListener.listen(16286)) {
                    _removeFromFiltered(card);
                }
            }
        }
    }

    private void _rescheduleGraduatingLapse(@NonNull Card card, boolean early) {
        if (!ListenerUtil.mutListener.listen(16293)) {
            if (early) {
                if (!ListenerUtil.mutListener.listen(16292)) {
                    card.setIvl((ListenerUtil.mutListener.listen(16291) ? (card.getIvl() % 1) : (ListenerUtil.mutListener.listen(16290) ? (card.getIvl() / 1) : (ListenerUtil.mutListener.listen(16289) ? (card.getIvl() * 1) : (ListenerUtil.mutListener.listen(16288) ? (card.getIvl() - 1) : (card.getIvl() + 1))))));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16298)) {
            card.setDue((ListenerUtil.mutListener.listen(16297) ? (mToday % card.getIvl()) : (ListenerUtil.mutListener.listen(16296) ? (mToday / card.getIvl()) : (ListenerUtil.mutListener.listen(16295) ? (mToday * card.getIvl()) : (ListenerUtil.mutListener.listen(16294) ? (mToday - card.getIvl()) : (mToday + card.getIvl()))))));
        }
        if (!ListenerUtil.mutListener.listen(16299)) {
            card.setQueue(Consts.QUEUE_TYPE_REV);
        }
        if (!ListenerUtil.mutListener.listen(16300)) {
            card.setType(Consts.CARD_TYPE_REV);
        }
    }

    // Overriden: V1 has type rev for relearinng
    protected int _startingLeft(@NonNull Card card) {
        JSONObject conf;
        if ((ListenerUtil.mutListener.listen(16305) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16304) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16303) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16302) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16301) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))) {
            conf = _lapseConf(card);
        } else {
            conf = _lrnConf(card);
        }
        int tot = conf.getJSONArray("delays").length();
        int tod = _leftToday(conf.getJSONArray("delays"), tot);
        return (ListenerUtil.mutListener.listen(16313) ? (tot % (ListenerUtil.mutListener.listen(16309) ? (tod % 1000) : (ListenerUtil.mutListener.listen(16308) ? (tod / 1000) : (ListenerUtil.mutListener.listen(16307) ? (tod - 1000) : (ListenerUtil.mutListener.listen(16306) ? (tod + 1000) : (tod * 1000)))))) : (ListenerUtil.mutListener.listen(16312) ? (tot / (ListenerUtil.mutListener.listen(16309) ? (tod % 1000) : (ListenerUtil.mutListener.listen(16308) ? (tod / 1000) : (ListenerUtil.mutListener.listen(16307) ? (tod - 1000) : (ListenerUtil.mutListener.listen(16306) ? (tod + 1000) : (tod * 1000)))))) : (ListenerUtil.mutListener.listen(16311) ? (tot * (ListenerUtil.mutListener.listen(16309) ? (tod % 1000) : (ListenerUtil.mutListener.listen(16308) ? (tod / 1000) : (ListenerUtil.mutListener.listen(16307) ? (tod - 1000) : (ListenerUtil.mutListener.listen(16306) ? (tod + 1000) : (tod * 1000)))))) : (ListenerUtil.mutListener.listen(16310) ? (tot - (ListenerUtil.mutListener.listen(16309) ? (tod % 1000) : (ListenerUtil.mutListener.listen(16308) ? (tod / 1000) : (ListenerUtil.mutListener.listen(16307) ? (tod - 1000) : (ListenerUtil.mutListener.listen(16306) ? (tod + 1000) : (tod * 1000)))))) : (tot + (ListenerUtil.mutListener.listen(16309) ? (tod % 1000) : (ListenerUtil.mutListener.listen(16308) ? (tod / 1000) : (ListenerUtil.mutListener.listen(16307) ? (tod - 1000) : (ListenerUtil.mutListener.listen(16306) ? (tod + 1000) : (tod * 1000))))))))));
    }

    /**
     * the number of steps that can be completed by the day cutoff
     */
    protected int _leftToday(@NonNull JSONArray delays, int left) {
        return _leftToday(delays, left, 0);
    }

    private int _leftToday(@NonNull JSONArray delays, int left, long now) {
        if (!ListenerUtil.mutListener.listen(16320)) {
            if ((ListenerUtil.mutListener.listen(16318) ? (now >= 0) : (ListenerUtil.mutListener.listen(16317) ? (now <= 0) : (ListenerUtil.mutListener.listen(16316) ? (now > 0) : (ListenerUtil.mutListener.listen(16315) ? (now < 0) : (ListenerUtil.mutListener.listen(16314) ? (now != 0) : (now == 0))))))) {
                if (!ListenerUtil.mutListener.listen(16319)) {
                    now = getTime().intTime();
                }
            }
        }
        int ok = 0;
        int offset = Math.min(left, delays.length());
        if (!ListenerUtil.mutListener.listen(16342)) {
            {
                long _loopCounter316 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(16341) ? (i >= offset) : (ListenerUtil.mutListener.listen(16340) ? (i <= offset) : (ListenerUtil.mutListener.listen(16339) ? (i > offset) : (ListenerUtil.mutListener.listen(16338) ? (i != offset) : (ListenerUtil.mutListener.listen(16337) ? (i == offset) : (i < offset)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter316", ++_loopCounter316);
                    if (!ListenerUtil.mutListener.listen(16329)) {
                        now += (int) ((ListenerUtil.mutListener.listen(16328) ? (delays.getDouble((ListenerUtil.mutListener.listen(16324) ? (delays.length() % offset) : (ListenerUtil.mutListener.listen(16323) ? (delays.length() / offset) : (ListenerUtil.mutListener.listen(16322) ? (delays.length() * offset) : (ListenerUtil.mutListener.listen(16321) ? (delays.length() + offset) : (delays.length() - offset))))) + i) % 60.0) : (ListenerUtil.mutListener.listen(16327) ? (delays.getDouble((ListenerUtil.mutListener.listen(16324) ? (delays.length() % offset) : (ListenerUtil.mutListener.listen(16323) ? (delays.length() / offset) : (ListenerUtil.mutListener.listen(16322) ? (delays.length() * offset) : (ListenerUtil.mutListener.listen(16321) ? (delays.length() + offset) : (delays.length() - offset))))) + i) / 60.0) : (ListenerUtil.mutListener.listen(16326) ? (delays.getDouble((ListenerUtil.mutListener.listen(16324) ? (delays.length() % offset) : (ListenerUtil.mutListener.listen(16323) ? (delays.length() / offset) : (ListenerUtil.mutListener.listen(16322) ? (delays.length() * offset) : (ListenerUtil.mutListener.listen(16321) ? (delays.length() + offset) : (delays.length() - offset))))) + i) - 60.0) : (ListenerUtil.mutListener.listen(16325) ? (delays.getDouble((ListenerUtil.mutListener.listen(16324) ? (delays.length() % offset) : (ListenerUtil.mutListener.listen(16323) ? (delays.length() / offset) : (ListenerUtil.mutListener.listen(16322) ? (delays.length() * offset) : (ListenerUtil.mutListener.listen(16321) ? (delays.length() + offset) : (delays.length() - offset))))) + i) + 60.0) : (delays.getDouble((ListenerUtil.mutListener.listen(16324) ? (delays.length() % offset) : (ListenerUtil.mutListener.listen(16323) ? (delays.length() / offset) : (ListenerUtil.mutListener.listen(16322) ? (delays.length() * offset) : (ListenerUtil.mutListener.listen(16321) ? (delays.length() + offset) : (delays.length() - offset))))) + i) * 60.0))))));
                    }
                    if (!ListenerUtil.mutListener.listen(16335)) {
                        if ((ListenerUtil.mutListener.listen(16334) ? (now >= mDayCutoff) : (ListenerUtil.mutListener.listen(16333) ? (now <= mDayCutoff) : (ListenerUtil.mutListener.listen(16332) ? (now < mDayCutoff) : (ListenerUtil.mutListener.listen(16331) ? (now != mDayCutoff) : (ListenerUtil.mutListener.listen(16330) ? (now == mDayCutoff) : (now > mDayCutoff))))))) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(16336)) {
                        ok = i;
                    }
                }
            }
        }
        return (ListenerUtil.mutListener.listen(16346) ? (ok % 1) : (ListenerUtil.mutListener.listen(16345) ? (ok / 1) : (ListenerUtil.mutListener.listen(16344) ? (ok * 1) : (ListenerUtil.mutListener.listen(16343) ? (ok - 1) : (ok + 1)))));
    }

    protected int _graduatingIvl(@NonNull Card card, @NonNull JSONObject conf, boolean early) {
        return _graduatingIvl(card, conf, early, true);
    }

    private int _graduatingIvl(@NonNull Card card, @NonNull JSONObject conf, boolean early, boolean fuzz) {
        if (!ListenerUtil.mutListener.listen(16362)) {
            if ((ListenerUtil.mutListener.listen(16357) ? ((ListenerUtil.mutListener.listen(16351) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16350) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16349) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16348) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16347) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) && (ListenerUtil.mutListener.listen(16356) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16355) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16354) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16353) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16352) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))) : ((ListenerUtil.mutListener.listen(16351) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16350) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16349) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16348) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16347) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))) || (ListenerUtil.mutListener.listen(16356) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16355) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16354) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16353) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16352) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))))) {
                int bonus = early ? 1 : 0;
                return (ListenerUtil.mutListener.listen(16361) ? (card.getIvl() % bonus) : (ListenerUtil.mutListener.listen(16360) ? (card.getIvl() / bonus) : (ListenerUtil.mutListener.listen(16359) ? (card.getIvl() * bonus) : (ListenerUtil.mutListener.listen(16358) ? (card.getIvl() - bonus) : (card.getIvl() + bonus)))));
            }
        }
        int ideal;
        JSONArray ints = conf.getJSONArray("ints");
        if (!early) {
            // graduate
            ideal = ints.getInt(0);
        } else {
            // early remove
            ideal = ints.getInt(1);
        }
        if (fuzz) {
            ideal = _fuzzedIvl(ideal);
        }
        return ideal;
    }

    /**
     * Reschedule a new card that's graduated for the first time.
     * Overriden: V1 does not set type and queue
     */
    private void _rescheduleNew(@NonNull Card card, @NonNull JSONObject conf, boolean early) {
        if (!ListenerUtil.mutListener.listen(16363)) {
            card.setIvl(_graduatingIvl(card, conf, early));
        }
        if (!ListenerUtil.mutListener.listen(16368)) {
            card.setDue((ListenerUtil.mutListener.listen(16367) ? (mToday % card.getIvl()) : (ListenerUtil.mutListener.listen(16366) ? (mToday / card.getIvl()) : (ListenerUtil.mutListener.listen(16365) ? (mToday * card.getIvl()) : (ListenerUtil.mutListener.listen(16364) ? (mToday - card.getIvl()) : (mToday + card.getIvl()))))));
        }
        if (!ListenerUtil.mutListener.listen(16369)) {
            card.setFactor(conf.getInt("initialFactor"));
        }
        if (!ListenerUtil.mutListener.listen(16370)) {
            card.setType(Consts.CARD_TYPE_REV);
        }
        if (!ListenerUtil.mutListener.listen(16371)) {
            card.setQueue(Consts.QUEUE_TYPE_REV);
        }
    }

    protected void _logLrn(@NonNull Card card, @Consts.BUTTON_TYPE int ease, @NonNull JSONObject conf, boolean leaving, @Consts.REVLOG_TYPE int type, int lastLeft) {
        int lastIvl = -(_delayForGrade(conf, lastLeft));
        int ivl = leaving ? card.getIvl() : -(_delayForGrade(conf, card.getLeft()));
        if (!ListenerUtil.mutListener.listen(16372)) {
            log(card.getId(), mCol.usn(), ease, ivl, lastIvl, card.getFactor(), card.timeTaken(), type);
        }
    }

    @Override
    public int logCount() {
        return mCol.getDb().queryScalar("SELECT count() FROM revlog");
    }

    protected void log(long id, int usn, @Consts.BUTTON_TYPE int ease, int ivl, int lastIvl, int factor, int timeTaken, @Consts.REVLOG_TYPE int type) {
        try {
            if (!ListenerUtil.mutListener.listen(16375)) {
                mCol.getDb().execute("INSERT INTO revlog VALUES (?,?,?,?,?,?,?,?,?)", getTime().intTimeMS(), id, usn, ease, ivl, lastIvl, factor, timeTaken, type);
            }
        } catch (SQLiteConstraintException e) {
            try {
                if (!ListenerUtil.mutListener.listen(16373)) {
                    Thread.sleep(10);
                }
            } catch (InterruptedException e1) {
                throw new RuntimeException(e1);
            }
            if (!ListenerUtil.mutListener.listen(16374)) {
                log(id, usn, ease, ivl, lastIvl, factor, timeTaken, type);
            }
        }
    }

    // Overriden: uses left/1000 in V1
    private int _lrnForDeck(long did) {
        try {
            int cnt = mCol.getDb().queryScalar("SELECT count() FROM (SELECT null FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_LRN + " AND due < ?" + " LIMIT ?)", did, (getTime().intTime() + mCol.getConf().getInt("collapseTime")), mReportLimit);
            return (ListenerUtil.mutListener.listen(16379) ? (cnt % mCol.getDb().queryScalar("SELECT count() FROM (SELECT null FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (ListenerUtil.mutListener.listen(16378) ? (cnt / mCol.getDb().queryScalar("SELECT count() FROM (SELECT null FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (ListenerUtil.mutListener.listen(16377) ? (cnt * mCol.getDb().queryScalar("SELECT count() FROM (SELECT null FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (ListenerUtil.mutListener.listen(16376) ? (cnt - mCol.getDb().queryScalar("SELECT count() FROM (SELECT null FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (cnt + mCol.getDb().queryScalar("SELECT count() FROM (SELECT null FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit))))));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Maximal number of rev card still to see today in current deck. It's computed as:
     * the number of rev card to see by day according to the deck optinos
     * minus the number of rev cards seen today in this deck or a descendant
     * plus the number of extra cards to see today in this deck, a parent or a descendant.
     *
     * Respects the limits of its ancestor. Current card is treated the same way as other cards.
     * @param considerCurrentCard whether the current card should be taken from the limit (if it belongs to this deck)
     */
    private int _currentRevLimit(boolean considerCurrentCard) {
        Deck d = mCol.getDecks().get(mCol.getDecks().selected(), false);
        return _deckRevLimitSingle(d, considerCurrentCard);
    }

    /**
     * Maximal number of rev card still to see today in deck d. It's computed as:
     * the number of rev card to see by day according to the deck optinos
     * minus the number of rev cards seen today in deck d or a descendant
     * plus the number of extra cards to see today in deck d, a parent or a descendant.
     *
     * Respects the limits of its ancestor
     * Overridden: V1 does not consider parents limit
     * @param considerCurrentCard whether the current card should be taken from the limit (if it belongs to this deck)
     */
    protected int _deckRevLimitSingle(@Nullable Deck d, boolean considerCurrentCard) {
        return _deckRevLimitSingle(d, null, considerCurrentCard);
    }

    /**
     * Maximal number of rev card still to see today in deck d. It's computed as:
     * the number of rev card to see by day according to the deck optinos
     * minus the number of rev cards seen today in deck d or a descendant
     * plus the number of extra cards to see today in deck d, a parent or a descendant.
     *
     * Respects the limits of its ancestor, either given as parentLimit, or through direct computation.
     * @param parentLimit Limit of the parent, this is an upper bound on the limit of this deck
     * @param considerCurrentCard whether the current card should be taken from the limit (if it belongs to this deck)
     */
    private int _deckRevLimitSingle(@Nullable Deck d, Integer parentLimit, boolean considerCurrentCard) {
        // invalid deck selected?
        if (d == null) {
            return 0;
        }
        if (d.getInt("dyn") == DECK_DYN) {
            return mDynReportLimit;
        }
        long did = d.getLong("id");
        @NonNull
        DeckConfig c = mCol.getDecks().confForDid(did);
        int lim = Math.max(0, (ListenerUtil.mutListener.listen(16383) ? (c.getJSONObject("rev").getInt("perDay") % d.getJSONArray("revToday").getInt(1)) : (ListenerUtil.mutListener.listen(16382) ? (c.getJSONObject("rev").getInt("perDay") / d.getJSONArray("revToday").getInt(1)) : (ListenerUtil.mutListener.listen(16381) ? (c.getJSONObject("rev").getInt("perDay") * d.getJSONArray("revToday").getInt(1)) : (ListenerUtil.mutListener.listen(16380) ? (c.getJSONObject("rev").getInt("perDay") + d.getJSONArray("revToday").getInt(1)) : (c.getJSONObject("rev").getInt("perDay") - d.getJSONArray("revToday").getInt(1)))))));
        if (!ListenerUtil.mutListener.listen(16386)) {
            // So currentCard does not have to be taken into consideration in this method
            if ((ListenerUtil.mutListener.listen(16384) ? (considerCurrentCard || currentCardIsInQueueWithDeck(Consts.QUEUE_TYPE_REV, did)) : (considerCurrentCard && currentCardIsInQueueWithDeck(Consts.QUEUE_TYPE_REV, did)))) {
                if (!ListenerUtil.mutListener.listen(16385)) {
                    lim--;
                }
            }
        }
        if (parentLimit != null) {
            return Math.min(parentLimit, lim);
        } else if (!d.getString("name").contains("::")) {
            return lim;
        } else {
            if (!ListenerUtil.mutListener.listen(16388)) {
                {
                    long _loopCounter317 = 0;
                    for (@NonNull Deck parent : mCol.getDecks().parents(did)) {
                        ListenerUtil.loopListener.listen("_loopCounter317", ++_loopCounter317);
                        if (!ListenerUtil.mutListener.listen(16387)) {
                            // pass in dummy parentLimit so we don't do parent lookup again
                            lim = Math.min(lim, _deckRevLimitSingle(parent, lim, considerCurrentCard));
                        }
                    }
                }
            }
            return lim;
        }
    }

    protected int _revForDeck(long did, int lim, @NonNull Decks.Node childMap) {
        List<Long> dids = mCol.getDecks().childDids(did, childMap);
        if (!ListenerUtil.mutListener.listen(16389)) {
            dids.add(0, did);
        }
        if (!ListenerUtil.mutListener.listen(16390)) {
            lim = Math.min(lim, mReportLimit);
        }
        return mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did in " + Utils.ids2str(dids) + " AND queue = " + Consts.QUEUE_TYPE_REV + " AND due <= ? LIMIT ?)", mToday, lim);
    }

    // Overriden: V1 uses _walkingCount
    protected void _resetRevCount() {
        if (!ListenerUtil.mutListener.listen(16391)) {
            _resetRevCount(null);
        }
    }

    protected void _resetRevCount(@Nullable CancelListener cancelListener) {
        int lim = _currentRevLimit(true);
        if (!ListenerUtil.mutListener.listen(16392)) {
            if (isCancelled(cancelListener))
                return;
        }
        if (!ListenerUtil.mutListener.listen(16393)) {
            mRevCount = mCol.getDb().queryScalar("SELECT count() FROM (SELECT id FROM cards WHERE did in " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_REV + " AND due <= ? AND id != ? LIMIT ?)", mToday, currentCardId(), lim);
        }
    }

    // Overridden: V1 remove clear
    protected void _resetRev() {
        if (!ListenerUtil.mutListener.listen(16394)) {
            _resetRevCount();
        }
        if (!ListenerUtil.mutListener.listen(16395)) {
            _resetRevQueue();
        }
    }

    protected void _resetRevQueue() {
        if (!ListenerUtil.mutListener.listen(16396)) {
            mRevQueue.clear();
        }
    }

    protected boolean _fillRev() {
        return _fillRev(false);
    }

    // Override: V1 loops over dids
    protected boolean _fillRev(boolean allowSibling) {
        if (!ListenerUtil.mutListener.listen(16397)) {
            if (!mRevQueue.isEmpty()) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(16404)) {
            if ((ListenerUtil.mutListener.listen(16403) ? (mHaveCounts || (ListenerUtil.mutListener.listen(16402) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(16401) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(16400) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(16399) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(16398) ? (mRevCount != 0) : (mRevCount == 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(16402) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(16401) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(16400) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(16399) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(16398) ? (mRevCount != 0) : (mRevCount == 0))))))))) {
                return false;
            }
        }
        int lim = Math.min(mQueueLimit, _currentRevLimit(true));
        if (!ListenerUtil.mutListener.listen(16414)) {
            if ((ListenerUtil.mutListener.listen(16409) ? (lim >= 0) : (ListenerUtil.mutListener.listen(16408) ? (lim <= 0) : (ListenerUtil.mutListener.listen(16407) ? (lim > 0) : (ListenerUtil.mutListener.listen(16406) ? (lim < 0) : (ListenerUtil.mutListener.listen(16405) ? (lim == 0) : (lim != 0))))))) {
                if (!ListenerUtil.mutListener.listen(16410)) {
                    mRevQueue.clear();
                }
                // fill the queue with the current did
                String idName = (allowSibling) ? "id" : "nid";
                long id = (allowSibling) ? currentCardId() : currentCardNid();
                // fill the queue with the current did
                try (Cursor cur = mCol.getDb().query("SELECT id FROM cards WHERE did in " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_REV + " AND due <= ? AND " + idName + " != ?" + " ORDER BY due, random()  LIMIT ?", mToday, id, lim)) {
                    if (!ListenerUtil.mutListener.listen(16412)) {
                        {
                            long _loopCounter318 = 0;
                            while (cur.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter318", ++_loopCounter318);
                                if (!ListenerUtil.mutListener.listen(16411)) {
                                    mRevQueue.add(cur.getLong(0));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16413)) {
                    if (!mRevQueue.isEmpty()) {
                        // in _getRevCard().
                        return true;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16422)) {
            if ((ListenerUtil.mutListener.listen(16420) ? (mHaveCounts || (ListenerUtil.mutListener.listen(16419) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(16418) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(16417) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(16416) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(16415) ? (mRevCount == 0) : (mRevCount != 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(16419) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(16418) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(16417) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(16416) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(16415) ? (mRevCount == 0) : (mRevCount != 0))))))))) {
                if (!ListenerUtil.mutListener.listen(16421)) {
                    // removed from the queue but not buried
                    _resetRev();
                }
                return _fillRev(true);
            }
        }
        return false;
    }

    @Nullable
    protected Card _getRevCard() {
        if (_fillRev()) {
            // mRevCount -= 1; see decrementCounts()
            return mRevQueue.removeFirstCard();
        } else {
            return null;
        }
    }

    public int totalRevForCurrentDeck() {
        return mCol.getDb().queryScalar("SELECT count() FROM cards WHERE id IN (SELECT id FROM cards WHERE did IN " + _deckLimit() + "  AND queue = " + Consts.QUEUE_TYPE_REV + " AND due <= ? LIMIT ?)", mToday, mReportLimit);
    }

    // Overridden: v1 does not deal with early
    protected void _answerRevCard(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        int delay = 0;
        boolean early = (ListenerUtil.mutListener.listen(16428) ? (card.isInDynamicDeck() || ((ListenerUtil.mutListener.listen(16427) ? (card.getODue() >= mToday) : (ListenerUtil.mutListener.listen(16426) ? (card.getODue() <= mToday) : (ListenerUtil.mutListener.listen(16425) ? (card.getODue() < mToday) : (ListenerUtil.mutListener.listen(16424) ? (card.getODue() != mToday) : (ListenerUtil.mutListener.listen(16423) ? (card.getODue() == mToday) : (card.getODue() > mToday)))))))) : (card.isInDynamicDeck() && ((ListenerUtil.mutListener.listen(16427) ? (card.getODue() >= mToday) : (ListenerUtil.mutListener.listen(16426) ? (card.getODue() <= mToday) : (ListenerUtil.mutListener.listen(16425) ? (card.getODue() < mToday) : (ListenerUtil.mutListener.listen(16424) ? (card.getODue() != mToday) : (ListenerUtil.mutListener.listen(16423) ? (card.getODue() == mToday) : (card.getODue() > mToday)))))))));
        int type = early ? 3 : 1;
        if (!ListenerUtil.mutListener.listen(16436)) {
            if ((ListenerUtil.mutListener.listen(16433) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16432) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16431) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16430) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16429) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
                if (!ListenerUtil.mutListener.listen(16435)) {
                    delay = _rescheduleLapse(card);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16434)) {
                    _rescheduleRev(card, ease, early);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16437)) {
            _logRev(card, ease, delay, type);
        }
    }

    // Overriden
    protected int _rescheduleLapse(@NonNull Card card) {
        JSONObject conf = _lapseConf(card);
        if (!ListenerUtil.mutListener.listen(16442)) {
            card.setLapses((ListenerUtil.mutListener.listen(16441) ? (card.getLapses() % 1) : (ListenerUtil.mutListener.listen(16440) ? (card.getLapses() / 1) : (ListenerUtil.mutListener.listen(16439) ? (card.getLapses() * 1) : (ListenerUtil.mutListener.listen(16438) ? (card.getLapses() - 1) : (card.getLapses() + 1))))));
        }
        if (!ListenerUtil.mutListener.listen(16447)) {
            card.setFactor(Math.max(1300, (ListenerUtil.mutListener.listen(16446) ? (card.getFactor() % 200) : (ListenerUtil.mutListener.listen(16445) ? (card.getFactor() / 200) : (ListenerUtil.mutListener.listen(16444) ? (card.getFactor() * 200) : (ListenerUtil.mutListener.listen(16443) ? (card.getFactor() + 200) : (card.getFactor() - 200)))))));
        }
        int delay;
        boolean suspended = (ListenerUtil.mutListener.listen(16453) ? (_checkLeech(card, conf) || (ListenerUtil.mutListener.listen(16452) ? (card.getQueue() >= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16451) ? (card.getQueue() <= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16450) ? (card.getQueue() > Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16449) ? (card.getQueue() < Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16448) ? (card.getQueue() != Consts.QUEUE_TYPE_SUSPENDED) : (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED))))))) : (_checkLeech(card, conf) && (ListenerUtil.mutListener.listen(16452) ? (card.getQueue() >= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16451) ? (card.getQueue() <= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16450) ? (card.getQueue() > Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16449) ? (card.getQueue() < Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(16448) ? (card.getQueue() != Consts.QUEUE_TYPE_SUSPENDED) : (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED))))))));
        if ((ListenerUtil.mutListener.listen(16454) ? (conf.getJSONArray("delays").length() != 0 || !suspended) : (conf.getJSONArray("delays").length() != 0 && !suspended))) {
            if (!ListenerUtil.mutListener.listen(16459)) {
                card.setType(Consts.CARD_TYPE_RELEARNING);
            }
            delay = _moveToFirstStep(card, conf);
        } else {
            if (!ListenerUtil.mutListener.listen(16455)) {
                // no relearning steps
                _updateRevIvlOnFail(card, conf);
            }
            if (!ListenerUtil.mutListener.listen(16456)) {
                _rescheduleAsRev(card, conf, false);
            }
            if (!ListenerUtil.mutListener.listen(16458)) {
                // need to reset the queue after rescheduling
                if (suspended) {
                    if (!ListenerUtil.mutListener.listen(16457)) {
                        card.setQueue(Consts.QUEUE_TYPE_SUSPENDED);
                    }
                }
            }
            delay = 0;
        }
        return delay;
    }

    private int _lapseIvl(@NonNull Card card, @NonNull JSONObject conf) {
        return Math.max(1, Math.max(conf.getInt("minInt"), (int) ((ListenerUtil.mutListener.listen(16463) ? (card.getIvl() % conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(16462) ? (card.getIvl() / conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(16461) ? (card.getIvl() - conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(16460) ? (card.getIvl() + conf.getDouble("mult")) : (card.getIvl() * conf.getDouble("mult")))))))));
    }

    protected void _rescheduleRev(@NonNull Card card, @Consts.BUTTON_TYPE int ease, boolean early) {
        if (!ListenerUtil.mutListener.listen(16464)) {
            // update interval
            card.setLastIvl(card.getIvl());
        }
        if (!ListenerUtil.mutListener.listen(16467)) {
            if (early) {
                if (!ListenerUtil.mutListener.listen(16466)) {
                    _updateEarlyRevIvl(card, ease);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16465)) {
                    _updateRevIvl(card, ease);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16476)) {
            // then the rest
            card.setFactor(Math.max(1300, (ListenerUtil.mutListener.listen(16475) ? (card.getFactor() % FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(16471) ? (ease % 2) : (ListenerUtil.mutListener.listen(16470) ? (ease / 2) : (ListenerUtil.mutListener.listen(16469) ? (ease * 2) : (ListenerUtil.mutListener.listen(16468) ? (ease + 2) : (ease - 2)))))]) : (ListenerUtil.mutListener.listen(16474) ? (card.getFactor() / FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(16471) ? (ease % 2) : (ListenerUtil.mutListener.listen(16470) ? (ease / 2) : (ListenerUtil.mutListener.listen(16469) ? (ease * 2) : (ListenerUtil.mutListener.listen(16468) ? (ease + 2) : (ease - 2)))))]) : (ListenerUtil.mutListener.listen(16473) ? (card.getFactor() * FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(16471) ? (ease % 2) : (ListenerUtil.mutListener.listen(16470) ? (ease / 2) : (ListenerUtil.mutListener.listen(16469) ? (ease * 2) : (ListenerUtil.mutListener.listen(16468) ? (ease + 2) : (ease - 2)))))]) : (ListenerUtil.mutListener.listen(16472) ? (card.getFactor() - FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(16471) ? (ease % 2) : (ListenerUtil.mutListener.listen(16470) ? (ease / 2) : (ListenerUtil.mutListener.listen(16469) ? (ease * 2) : (ListenerUtil.mutListener.listen(16468) ? (ease + 2) : (ease - 2)))))]) : (card.getFactor() + FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(16471) ? (ease % 2) : (ListenerUtil.mutListener.listen(16470) ? (ease / 2) : (ListenerUtil.mutListener.listen(16469) ? (ease * 2) : (ListenerUtil.mutListener.listen(16468) ? (ease + 2) : (ease - 2)))))])))))));
        }
        if (!ListenerUtil.mutListener.listen(16481)) {
            card.setDue((ListenerUtil.mutListener.listen(16480) ? (mToday % card.getIvl()) : (ListenerUtil.mutListener.listen(16479) ? (mToday / card.getIvl()) : (ListenerUtil.mutListener.listen(16478) ? (mToday * card.getIvl()) : (ListenerUtil.mutListener.listen(16477) ? (mToday - card.getIvl()) : (mToday + card.getIvl()))))));
        }
        if (!ListenerUtil.mutListener.listen(16482)) {
            // card leaves filtered deck
            _removeFromFiltered(card);
        }
    }

    protected void _logRev(@NonNull Card card, @Consts.BUTTON_TYPE int ease, int delay, int type) {
        if (!ListenerUtil.mutListener.listen(16488)) {
            log(card.getId(), mCol.usn(), ease, (((ListenerUtil.mutListener.listen(16487) ? (delay >= 0) : (ListenerUtil.mutListener.listen(16486) ? (delay <= 0) : (ListenerUtil.mutListener.listen(16485) ? (delay > 0) : (ListenerUtil.mutListener.listen(16484) ? (delay < 0) : (ListenerUtil.mutListener.listen(16483) ? (delay == 0) : (delay != 0))))))) ? (-delay) : card.getIvl()), card.getLastIvl(), card.getFactor(), card.timeTaken(), type);
        }
    }

    /**
     * Next interval for CARD, given EASE.
     */
    protected int _nextRevIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease, boolean fuzz) {
        long delay = _daysLate(card);
        JSONObject conf = _revConf(card);
        double fct = (ListenerUtil.mutListener.listen(16492) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(16491) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(16490) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(16489) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))));
        double hardFactor = conf.optDouble("hardFactor", 1.2);
        int hardMin;
        if ((ListenerUtil.mutListener.listen(16497) ? (hardFactor >= 1) : (ListenerUtil.mutListener.listen(16496) ? (hardFactor <= 1) : (ListenerUtil.mutListener.listen(16495) ? (hardFactor < 1) : (ListenerUtil.mutListener.listen(16494) ? (hardFactor != 1) : (ListenerUtil.mutListener.listen(16493) ? (hardFactor == 1) : (hardFactor > 1))))))) {
            hardMin = card.getIvl();
        } else {
            hardMin = 0;
        }
        int ivl2 = _constrainedIvl((ListenerUtil.mutListener.listen(16501) ? (card.getIvl() % hardFactor) : (ListenerUtil.mutListener.listen(16500) ? (card.getIvl() / hardFactor) : (ListenerUtil.mutListener.listen(16499) ? (card.getIvl() - hardFactor) : (ListenerUtil.mutListener.listen(16498) ? (card.getIvl() + hardFactor) : (card.getIvl() * hardFactor))))), conf, hardMin, fuzz);
        if (!ListenerUtil.mutListener.listen(16507)) {
            if ((ListenerUtil.mutListener.listen(16506) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16505) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16504) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16503) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16502) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO))))))) {
                return ivl2;
            }
        }
        int ivl3 = _constrainedIvl((ListenerUtil.mutListener.listen(16519) ? (((ListenerUtil.mutListener.listen(16515) ? (card.getIvl() % (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16514) ? (card.getIvl() / (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16513) ? (card.getIvl() * (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16512) ? (card.getIvl() - (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2))))))))))) % fct) : (ListenerUtil.mutListener.listen(16518) ? (((ListenerUtil.mutListener.listen(16515) ? (card.getIvl() % (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16514) ? (card.getIvl() / (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16513) ? (card.getIvl() * (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16512) ? (card.getIvl() - (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2))))))))))) / fct) : (ListenerUtil.mutListener.listen(16517) ? (((ListenerUtil.mutListener.listen(16515) ? (card.getIvl() % (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16514) ? (card.getIvl() / (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16513) ? (card.getIvl() * (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16512) ? (card.getIvl() - (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2))))))))))) - fct) : (ListenerUtil.mutListener.listen(16516) ? (((ListenerUtil.mutListener.listen(16515) ? (card.getIvl() % (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16514) ? (card.getIvl() / (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16513) ? (card.getIvl() * (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16512) ? (card.getIvl() - (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2))))))))))) + fct) : (((ListenerUtil.mutListener.listen(16515) ? (card.getIvl() % (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16514) ? (card.getIvl() / (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16513) ? (card.getIvl() * (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(16512) ? (card.getIvl() - (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(16511) ? (delay % 2) : (ListenerUtil.mutListener.listen(16510) ? (delay * 2) : (ListenerUtil.mutListener.listen(16509) ? (delay - 2) : (ListenerUtil.mutListener.listen(16508) ? (delay + 2) : (delay / 2))))))))))) * fct))))), conf, ivl2, fuzz);
        if (!ListenerUtil.mutListener.listen(16525)) {
            if ((ListenerUtil.mutListener.listen(16524) ? (ease >= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16523) ? (ease <= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16522) ? (ease > Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16521) ? (ease < Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(16520) ? (ease != Consts.BUTTON_THREE) : (ease == Consts.BUTTON_THREE))))))) {
                return ivl3;
            }
        }
        return _constrainedIvl(((ListenerUtil.mutListener.listen(16537) ? ((ListenerUtil.mutListener.listen(16533) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(16532) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(16531) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(16530) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) % conf.getDouble("ease4")) : (ListenerUtil.mutListener.listen(16536) ? ((ListenerUtil.mutListener.listen(16533) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(16532) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(16531) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(16530) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) / conf.getDouble("ease4")) : (ListenerUtil.mutListener.listen(16535) ? ((ListenerUtil.mutListener.listen(16533) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(16532) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(16531) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(16530) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) - conf.getDouble("ease4")) : (ListenerUtil.mutListener.listen(16534) ? ((ListenerUtil.mutListener.listen(16533) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(16532) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(16531) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(16530) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) + conf.getDouble("ease4")) : ((ListenerUtil.mutListener.listen(16533) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(16532) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(16531) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(16530) ? (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(16529) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(16528) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(16527) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(16526) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) * conf.getDouble("ease4"))))))), conf, ivl3, fuzz);
    }

    public int _fuzzedIvl(int ivl) {
        Pair<Integer, Integer> minMax = _fuzzIvlRange(ivl);
        // returns x in [0, b-a), hence the +1 diff with libanki
        return (new Random().nextInt((ListenerUtil.mutListener.listen(16541) ? (minMax.second % minMax.first) : (ListenerUtil.mutListener.listen(16540) ? (minMax.second / minMax.first) : (ListenerUtil.mutListener.listen(16539) ? (minMax.second * minMax.first) : (ListenerUtil.mutListener.listen(16538) ? (minMax.second + minMax.first) : (minMax.second - minMax.first))))) + 1)) + minMax.first;
    }

    @NonNull
    public Pair<Integer, Integer> _fuzzIvlRange(int ivl) {
        int fuzz;
        if ((ListenerUtil.mutListener.listen(16546) ? (ivl >= 2) : (ListenerUtil.mutListener.listen(16545) ? (ivl <= 2) : (ListenerUtil.mutListener.listen(16544) ? (ivl > 2) : (ListenerUtil.mutListener.listen(16543) ? (ivl != 2) : (ListenerUtil.mutListener.listen(16542) ? (ivl == 2) : (ivl < 2))))))) {
            return new Pair<>(1, 1);
        } else if ((ListenerUtil.mutListener.listen(16551) ? (ivl >= 2) : (ListenerUtil.mutListener.listen(16550) ? (ivl <= 2) : (ListenerUtil.mutListener.listen(16549) ? (ivl > 2) : (ListenerUtil.mutListener.listen(16548) ? (ivl < 2) : (ListenerUtil.mutListener.listen(16547) ? (ivl != 2) : (ivl == 2))))))) {
            return new Pair<>(2, 3);
        } else if ((ListenerUtil.mutListener.listen(16556) ? (ivl >= 7) : (ListenerUtil.mutListener.listen(16555) ? (ivl <= 7) : (ListenerUtil.mutListener.listen(16554) ? (ivl > 7) : (ListenerUtil.mutListener.listen(16553) ? (ivl != 7) : (ListenerUtil.mutListener.listen(16552) ? (ivl == 7) : (ivl < 7))))))) {
            fuzz = (int) ((ListenerUtil.mutListener.listen(16573) ? (ivl % 0.25) : (ListenerUtil.mutListener.listen(16572) ? (ivl / 0.25) : (ListenerUtil.mutListener.listen(16571) ? (ivl - 0.25) : (ListenerUtil.mutListener.listen(16570) ? (ivl + 0.25) : (ivl * 0.25))))));
        } else if ((ListenerUtil.mutListener.listen(16561) ? (ivl >= 30) : (ListenerUtil.mutListener.listen(16560) ? (ivl <= 30) : (ListenerUtil.mutListener.listen(16559) ? (ivl > 30) : (ListenerUtil.mutListener.listen(16558) ? (ivl != 30) : (ListenerUtil.mutListener.listen(16557) ? (ivl == 30) : (ivl < 30))))))) {
            fuzz = Math.max(2, (int) ((ListenerUtil.mutListener.listen(16569) ? (ivl % 0.15) : (ListenerUtil.mutListener.listen(16568) ? (ivl / 0.15) : (ListenerUtil.mutListener.listen(16567) ? (ivl - 0.15) : (ListenerUtil.mutListener.listen(16566) ? (ivl + 0.15) : (ivl * 0.15)))))));
        } else {
            fuzz = Math.max(4, (int) ((ListenerUtil.mutListener.listen(16565) ? (ivl % 0.05) : (ListenerUtil.mutListener.listen(16564) ? (ivl / 0.05) : (ListenerUtil.mutListener.listen(16563) ? (ivl - 0.05) : (ListenerUtil.mutListener.listen(16562) ? (ivl + 0.05) : (ivl * 0.05)))))));
        }
        // fuzz at least a day
        fuzz = Math.max(fuzz, 1);
        return new Pair<>((ListenerUtil.mutListener.listen(16577) ? (ivl % fuzz) : (ListenerUtil.mutListener.listen(16576) ? (ivl / fuzz) : (ListenerUtil.mutListener.listen(16575) ? (ivl * fuzz) : (ListenerUtil.mutListener.listen(16574) ? (ivl + fuzz) : (ivl - fuzz))))), (ListenerUtil.mutListener.listen(16581) ? (ivl % fuzz) : (ListenerUtil.mutListener.listen(16580) ? (ivl / fuzz) : (ListenerUtil.mutListener.listen(16579) ? (ivl * fuzz) : (ListenerUtil.mutListener.listen(16578) ? (ivl - fuzz) : (ivl + fuzz))))));
    }

    protected int _constrainedIvl(double ivl, @NonNull JSONObject conf, double prev, boolean fuzz) {
        int newIvl = (int) ((ListenerUtil.mutListener.listen(16585) ? (ivl % conf.optDouble("ivlFct", 1)) : (ListenerUtil.mutListener.listen(16584) ? (ivl / conf.optDouble("ivlFct", 1)) : (ListenerUtil.mutListener.listen(16583) ? (ivl - conf.optDouble("ivlFct", 1)) : (ListenerUtil.mutListener.listen(16582) ? (ivl + conf.optDouble("ivlFct", 1)) : (ivl * conf.optDouble("ivlFct", 1)))))));
        if (!ListenerUtil.mutListener.listen(16587)) {
            if (fuzz) {
                if (!ListenerUtil.mutListener.listen(16586)) {
                    newIvl = _fuzzedIvl(newIvl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16592)) {
            newIvl = (int) Math.max(Math.max(newIvl, (ListenerUtil.mutListener.listen(16591) ? (prev % 1) : (ListenerUtil.mutListener.listen(16590) ? (prev / 1) : (ListenerUtil.mutListener.listen(16589) ? (prev * 1) : (ListenerUtil.mutListener.listen(16588) ? (prev - 1) : (prev + 1)))))), 1);
        }
        if (!ListenerUtil.mutListener.listen(16593)) {
            newIvl = Math.min(newIvl, conf.getInt("maxIvl"));
        }
        return newIvl;
    }

    /**
     * Number of days later than scheduled.
     */
    protected long _daysLate(Card card) {
        long due = card.isInDynamicDeck() ? card.getODue() : card.getDue();
        return Math.max(0, (ListenerUtil.mutListener.listen(16597) ? (mToday % due) : (ListenerUtil.mutListener.listen(16596) ? (mToday / due) : (ListenerUtil.mutListener.listen(16595) ? (mToday * due) : (ListenerUtil.mutListener.listen(16594) ? (mToday + due) : (mToday - due))))));
    }

    // Overriden
    protected void _updateRevIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(16598)) {
            card.setIvl(_nextRevIvl(card, ease, true));
        }
    }

    private void _updateEarlyRevIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(16599)) {
            card.setIvl(_earlyReviewIvl(card, ease));
        }
    }

    /**
     * next interval for card when answered early+correctly
     */
    private int _earlyReviewIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(16612)) {
            if ((ListenerUtil.mutListener.listen(16611) ? ((ListenerUtil.mutListener.listen(16605) ? (!card.isInDynamicDeck() && (ListenerUtil.mutListener.listen(16604) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16603) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16602) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16601) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16600) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV))))))) : (!card.isInDynamicDeck() || (ListenerUtil.mutListener.listen(16604) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16603) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16602) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16601) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16600) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV)))))))) && (ListenerUtil.mutListener.listen(16610) ? (card.getFactor() >= 0) : (ListenerUtil.mutListener.listen(16609) ? (card.getFactor() <= 0) : (ListenerUtil.mutListener.listen(16608) ? (card.getFactor() > 0) : (ListenerUtil.mutListener.listen(16607) ? (card.getFactor() < 0) : (ListenerUtil.mutListener.listen(16606) ? (card.getFactor() != 0) : (card.getFactor() == 0))))))) : ((ListenerUtil.mutListener.listen(16605) ? (!card.isInDynamicDeck() && (ListenerUtil.mutListener.listen(16604) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16603) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16602) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16601) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16600) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV))))))) : (!card.isInDynamicDeck() || (ListenerUtil.mutListener.listen(16604) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16603) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16602) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16601) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(16600) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV)))))))) || (ListenerUtil.mutListener.listen(16610) ? (card.getFactor() >= 0) : (ListenerUtil.mutListener.listen(16609) ? (card.getFactor() <= 0) : (ListenerUtil.mutListener.listen(16608) ? (card.getFactor() > 0) : (ListenerUtil.mutListener.listen(16607) ? (card.getFactor() < 0) : (ListenerUtil.mutListener.listen(16606) ? (card.getFactor() != 0) : (card.getFactor() == 0))))))))) {
                throw new RuntimeException("Unexpected card parameters");
            }
        }
        if (!ListenerUtil.mutListener.listen(16618)) {
            if ((ListenerUtil.mutListener.listen(16617) ? (ease >= 1) : (ListenerUtil.mutListener.listen(16616) ? (ease > 1) : (ListenerUtil.mutListener.listen(16615) ? (ease < 1) : (ListenerUtil.mutListener.listen(16614) ? (ease != 1) : (ListenerUtil.mutListener.listen(16613) ? (ease == 1) : (ease <= 1))))))) {
                throw new RuntimeException("Ease must be greater than 1");
            }
        }
        long elapsed = (ListenerUtil.mutListener.listen(16626) ? (card.getIvl() % ((ListenerUtil.mutListener.listen(16622) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(16621) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(16620) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(16619) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (ListenerUtil.mutListener.listen(16625) ? (card.getIvl() / ((ListenerUtil.mutListener.listen(16622) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(16621) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(16620) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(16619) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (ListenerUtil.mutListener.listen(16624) ? (card.getIvl() * ((ListenerUtil.mutListener.listen(16622) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(16621) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(16620) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(16619) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (ListenerUtil.mutListener.listen(16623) ? (card.getIvl() + ((ListenerUtil.mutListener.listen(16622) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(16621) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(16620) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(16619) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (card.getIvl() - ((ListenerUtil.mutListener.listen(16622) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(16621) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(16620) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(16619) ? (card.getODue() + mToday) : (card.getODue() - mToday)))))))))));
        @NonNull
        JSONObject conf = _revConf(card);
        double easyBonus = 1;
        // early 3/4 reviews shouldn't decrease previous interval
        double minNewIvl = 1;
        double factor;
        if ((ListenerUtil.mutListener.listen(16631) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16630) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16629) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16628) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16627) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO))))))) {
            factor = conf.optDouble("hardFactor", 1.2);
            if (!ListenerUtil.mutListener.listen(16662)) {
                // of the normal factor
                minNewIvl = (ListenerUtil.mutListener.listen(16661) ? (factor % 2) : (ListenerUtil.mutListener.listen(16660) ? (factor * 2) : (ListenerUtil.mutListener.listen(16659) ? (factor - 2) : (ListenerUtil.mutListener.listen(16658) ? (factor + 2) : (factor / 2)))));
            }
        } else if ((ListenerUtil.mutListener.listen(16636) ? (ease >= 3) : (ListenerUtil.mutListener.listen(16635) ? (ease <= 3) : (ListenerUtil.mutListener.listen(16634) ? (ease > 3) : (ListenerUtil.mutListener.listen(16633) ? (ease < 3) : (ListenerUtil.mutListener.listen(16632) ? (ease != 3) : (ease == 3))))))) {
            factor = (ListenerUtil.mutListener.listen(16657) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(16656) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(16655) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(16654) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))));
        } else {
            // ease == 4
            factor = (ListenerUtil.mutListener.listen(16640) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(16639) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(16638) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(16637) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))));
            double ease4 = conf.getDouble("ease4");
            if (!ListenerUtil.mutListener.listen(16653)) {
                // 1.3 -> 1.15
                easyBonus = (ListenerUtil.mutListener.listen(16652) ? (ease4 % (ListenerUtil.mutListener.listen(16648) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) % 2) : (ListenerUtil.mutListener.listen(16647) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) * 2) : (ListenerUtil.mutListener.listen(16646) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) - 2) : (ListenerUtil.mutListener.listen(16645) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) + 2) : (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) / 2)))))) : (ListenerUtil.mutListener.listen(16651) ? (ease4 / (ListenerUtil.mutListener.listen(16648) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) % 2) : (ListenerUtil.mutListener.listen(16647) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) * 2) : (ListenerUtil.mutListener.listen(16646) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) - 2) : (ListenerUtil.mutListener.listen(16645) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) + 2) : (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) / 2)))))) : (ListenerUtil.mutListener.listen(16650) ? (ease4 * (ListenerUtil.mutListener.listen(16648) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) % 2) : (ListenerUtil.mutListener.listen(16647) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) * 2) : (ListenerUtil.mutListener.listen(16646) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) - 2) : (ListenerUtil.mutListener.listen(16645) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) + 2) : (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) / 2)))))) : (ListenerUtil.mutListener.listen(16649) ? (ease4 + (ListenerUtil.mutListener.listen(16648) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) % 2) : (ListenerUtil.mutListener.listen(16647) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) * 2) : (ListenerUtil.mutListener.listen(16646) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) - 2) : (ListenerUtil.mutListener.listen(16645) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) + 2) : (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) / 2)))))) : (ease4 - (ListenerUtil.mutListener.listen(16648) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) % 2) : (ListenerUtil.mutListener.listen(16647) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) * 2) : (ListenerUtil.mutListener.listen(16646) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) - 2) : (ListenerUtil.mutListener.listen(16645) ? (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) + 2) : (((ListenerUtil.mutListener.listen(16644) ? (ease4 % 1) : (ListenerUtil.mutListener.listen(16643) ? (ease4 / 1) : (ListenerUtil.mutListener.listen(16642) ? (ease4 * 1) : (ListenerUtil.mutListener.listen(16641) ? (ease4 + 1) : (ease4 - 1)))))) / 2))))))))));
            }
        }
        double ivl = Math.max((ListenerUtil.mutListener.listen(16666) ? (elapsed % factor) : (ListenerUtil.mutListener.listen(16665) ? (elapsed / factor) : (ListenerUtil.mutListener.listen(16664) ? (elapsed - factor) : (ListenerUtil.mutListener.listen(16663) ? (elapsed + factor) : (elapsed * factor))))), 1);
        if (!ListenerUtil.mutListener.listen(16675)) {
            // cap interval decreases
            ivl = (ListenerUtil.mutListener.listen(16674) ? (Math.max((ListenerUtil.mutListener.listen(16670) ? (card.getIvl() % minNewIvl) : (ListenerUtil.mutListener.listen(16669) ? (card.getIvl() / minNewIvl) : (ListenerUtil.mutListener.listen(16668) ? (card.getIvl() - minNewIvl) : (ListenerUtil.mutListener.listen(16667) ? (card.getIvl() + minNewIvl) : (card.getIvl() * minNewIvl))))), ivl) % easyBonus) : (ListenerUtil.mutListener.listen(16673) ? (Math.max((ListenerUtil.mutListener.listen(16670) ? (card.getIvl() % minNewIvl) : (ListenerUtil.mutListener.listen(16669) ? (card.getIvl() / minNewIvl) : (ListenerUtil.mutListener.listen(16668) ? (card.getIvl() - minNewIvl) : (ListenerUtil.mutListener.listen(16667) ? (card.getIvl() + minNewIvl) : (card.getIvl() * minNewIvl))))), ivl) / easyBonus) : (ListenerUtil.mutListener.listen(16672) ? (Math.max((ListenerUtil.mutListener.listen(16670) ? (card.getIvl() % minNewIvl) : (ListenerUtil.mutListener.listen(16669) ? (card.getIvl() / minNewIvl) : (ListenerUtil.mutListener.listen(16668) ? (card.getIvl() - minNewIvl) : (ListenerUtil.mutListener.listen(16667) ? (card.getIvl() + minNewIvl) : (card.getIvl() * minNewIvl))))), ivl) - easyBonus) : (ListenerUtil.mutListener.listen(16671) ? (Math.max((ListenerUtil.mutListener.listen(16670) ? (card.getIvl() % minNewIvl) : (ListenerUtil.mutListener.listen(16669) ? (card.getIvl() / minNewIvl) : (ListenerUtil.mutListener.listen(16668) ? (card.getIvl() - minNewIvl) : (ListenerUtil.mutListener.listen(16667) ? (card.getIvl() + minNewIvl) : (card.getIvl() * minNewIvl))))), ivl) + easyBonus) : (Math.max((ListenerUtil.mutListener.listen(16670) ? (card.getIvl() % minNewIvl) : (ListenerUtil.mutListener.listen(16669) ? (card.getIvl() / minNewIvl) : (ListenerUtil.mutListener.listen(16668) ? (card.getIvl() - minNewIvl) : (ListenerUtil.mutListener.listen(16667) ? (card.getIvl() + minNewIvl) : (card.getIvl() * minNewIvl))))), ivl) * easyBonus)))));
        }
        return _constrainedIvl(ivl, conf, 0, false);
    }

    /**
     * Rebuild a dynamic deck.
     */
    protected void rebuildDyn() {
        if (!ListenerUtil.mutListener.listen(16676)) {
            rebuildDyn(0);
        }
    }

    // Overridden, because upstream implements exactly the same method in two different way for unknown reason
    public void rebuildDyn(long did) {
        if (!ListenerUtil.mutListener.listen(16683)) {
            if ((ListenerUtil.mutListener.listen(16681) ? (did >= 0) : (ListenerUtil.mutListener.listen(16680) ? (did <= 0) : (ListenerUtil.mutListener.listen(16679) ? (did > 0) : (ListenerUtil.mutListener.listen(16678) ? (did < 0) : (ListenerUtil.mutListener.listen(16677) ? (did != 0) : (did == 0))))))) {
                if (!ListenerUtil.mutListener.listen(16682)) {
                    did = mCol.getDecks().selected();
                }
            }
        }
        Deck deck = mCol.getDecks().get(did);
        if (!ListenerUtil.mutListener.listen(16685)) {
            if (deck.getInt("dyn") == DECK_STD) {
                if (!ListenerUtil.mutListener.listen(16684)) {
                    Timber.e("error: deck is not a filtered deck");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16686)) {
            // move any existing cards back first, then fill
            emptyDyn(did);
        }
        int cnt = _fillDyn(deck);
        if (!ListenerUtil.mutListener.listen(16692)) {
            if ((ListenerUtil.mutListener.listen(16691) ? (cnt >= 0) : (ListenerUtil.mutListener.listen(16690) ? (cnt <= 0) : (ListenerUtil.mutListener.listen(16689) ? (cnt > 0) : (ListenerUtil.mutListener.listen(16688) ? (cnt < 0) : (ListenerUtil.mutListener.listen(16687) ? (cnt != 0) : (cnt == 0))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(16693)) {
            // and change to our new deck
            mCol.getDecks().select(did);
        }
    }

    /**
     * Whether the filtered deck is empty
     * Overriden
     */
    private int _fillDyn(Deck deck) {
        int start = -100000;
        int total = 0;
        List<Long> ids;
        JSONArray terms = deck.getJSONArray("terms");
        {
            long _loopCounter319 = 0;
            for (JSONArray term : terms.jsonArrayIterable()) {
                ListenerUtil.loopListener.listen("_loopCounter319", ++_loopCounter319);
                String search = term.getString(0);
                int limit = term.getInt(1);
                int order = term.getInt(2);
                String orderlimit = _dynOrder(order, limit);
                if (!ListenerUtil.mutListener.listen(16695)) {
                    if (!TextUtils.isEmpty(search.trim())) {
                        if (!ListenerUtil.mutListener.listen(16694)) {
                            search = String.format(Locale.US, "(%s)", search);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16696)) {
                    search = String.format(Locale.US, "%s -is:suspended -is:buried -deck:filtered", search);
                }
                ids = mCol.findCards(search, orderlimit);
                if (!ListenerUtil.mutListener.listen(16697)) {
                    if (ids.isEmpty()) {
                        return total;
                    }
                }
                if (!ListenerUtil.mutListener.listen(16698)) {
                    // move the cards over
                    mCol.log(deck.getLong("id"), ids);
                }
                if (!ListenerUtil.mutListener.listen(16703)) {
                    _moveToDyn(deck.getLong("id"), ids, (ListenerUtil.mutListener.listen(16702) ? (start % total) : (ListenerUtil.mutListener.listen(16701) ? (start / total) : (ListenerUtil.mutListener.listen(16700) ? (start * total) : (ListenerUtil.mutListener.listen(16699) ? (start - total) : (start + total))))));
                }
                if (!ListenerUtil.mutListener.listen(16704)) {
                    total += ids.size();
                }
            }
        }
        return total;
    }

    public void emptyDyn(long did) {
        if (!ListenerUtil.mutListener.listen(16705)) {
            emptyDyn(did, null);
        }
    }

    // Overriden: other queue in V1
    public void emptyDyn(long did, String lim) {
        if (!ListenerUtil.mutListener.listen(16707)) {
            if (lim == null) {
                if (!ListenerUtil.mutListener.listen(16706)) {
                    lim = "did = " + did;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16708)) {
            mCol.log(mCol.getDb().queryLongList("select id from cards where " + lim));
        }
        if (!ListenerUtil.mutListener.listen(16709)) {
            mCol.getDb().execute("update cards set did = odid, " + _restoreQueueWhenEmptyingSnippet() + ", due = (case when odue>0 then odue else due end), odue = 0, odid = 0, usn = ? where " + lim, mCol.usn());
        }
    }

    public void remFromDyn(long[] cids) {
        if (!ListenerUtil.mutListener.listen(16710)) {
            emptyDyn(0, "id IN " + Utils.ids2str(cids) + " AND odid");
        }
    }

    public void remFromDyn(List<Long> cids) {
        if (!ListenerUtil.mutListener.listen(16711)) {
            emptyDyn(0, "id IN " + Utils.ids2str(cids) + " AND odid");
        }
    }

    /**
     * Generates the required SQL for order by and limit clauses, for dynamic decks.
     *
     * @param o deck["order"]
     * @param l deck["limit"]
     * @return The generated SQL to be suffixed to "select ... from ... order by "
     */
    @NonNull
    protected String _dynOrder(@Consts.DYN_PRIORITY int o, int l) {
        String t;
        switch(o) {
            case Consts.DYN_OLDEST:
                t = "c.mod";
                break;
            case Consts.DYN_RANDOM:
                t = "random()";
                break;
            case Consts.DYN_SMALLINT:
                t = "ivl";
                break;
            case Consts.DYN_BIGINT:
                t = "ivl desc";
                break;
            case Consts.DYN_LAPSES:
                t = "lapses desc";
                break;
            case Consts.DYN_ADDED:
                t = "n.id";
                break;
            case Consts.DYN_REVADDED:
                t = "n.id desc";
                break;
            case Consts.DYN_DUEPRIORITY:
                t = String.format(Locale.US, "(case when queue=" + Consts.QUEUE_TYPE_REV + " and due <= %d then (ivl / cast(%d-due+0.001 as real)) else 100000+due end)", mToday, mToday);
                break;
            case Consts.DYN_DUE:
            default:
                // if we don't understand the term, default to due order
                t = "c.due";
                break;
        }
        return t + " limit " + l;
    }

    protected void _moveToDyn(long did, @NonNull List<Long> ids, int start) {
        Deck deck = mCol.getDecks().get(did);
        ArrayList<Object[]> data = new ArrayList<>(ids.size());
        int u = mCol.usn();
        int due = start;
        if (!ListenerUtil.mutListener.listen(16714)) {
            {
                long _loopCounter320 = 0;
                for (Long id : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter320", ++_loopCounter320);
                    if (!ListenerUtil.mutListener.listen(16712)) {
                        data.add(new Object[] { did, due, u, id });
                    }
                    if (!ListenerUtil.mutListener.listen(16713)) {
                        due += 1;
                    }
                }
            }
        }
        String queue = "";
        if (!ListenerUtil.mutListener.listen(16716)) {
            if (!deck.getBoolean("resched")) {
                if (!ListenerUtil.mutListener.listen(16715)) {
                    queue = ", queue = " + Consts.QUEUE_TYPE_REV + "";
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16717)) {
            mCol.getDb().executeMany("UPDATE cards SET odid = did, " + "odue = due, did = ?, due = (case when due <= 0 then due else ? end), usn = ? " + queue + " WHERE id = ?", data);
        }
    }

    private void _removeFromFiltered(@NonNull Card card) {
        if (!ListenerUtil.mutListener.listen(16721)) {
            if (card.isInDynamicDeck()) {
                if (!ListenerUtil.mutListener.listen(16718)) {
                    card.setDid(card.getODid());
                }
                if (!ListenerUtil.mutListener.listen(16719)) {
                    card.setODue(0);
                }
                if (!ListenerUtil.mutListener.listen(16720)) {
                    card.setODid(0);
                }
            }
        }
    }

    private void _restorePreviewCard(@NonNull Card card) {
        if (!ListenerUtil.mutListener.listen(16722)) {
            if (!card.isInDynamicDeck()) {
                throw new RuntimeException("ODid wasn't set");
            }
        }
        if (!ListenerUtil.mutListener.listen(16723)) {
            card.setDue(card.getODue());
        }
        if (!ListenerUtil.mutListener.listen(16744)) {
            // other types map directly to queues
            if ((ListenerUtil.mutListener.listen(16734) ? ((ListenerUtil.mutListener.listen(16728) ? (card.getType() >= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16727) ? (card.getType() <= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16726) ? (card.getType() > Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16725) ? (card.getType() < Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16724) ? (card.getType() != Consts.CARD_TYPE_LRN) : (card.getType() == Consts.CARD_TYPE_LRN)))))) && (ListenerUtil.mutListener.listen(16733) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16732) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16731) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16730) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16729) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))) : ((ListenerUtil.mutListener.listen(16728) ? (card.getType() >= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16727) ? (card.getType() <= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16726) ? (card.getType() > Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16725) ? (card.getType() < Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(16724) ? (card.getType() != Consts.CARD_TYPE_LRN) : (card.getType() == Consts.CARD_TYPE_LRN)))))) || (ListenerUtil.mutListener.listen(16733) ? (card.getType() >= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16732) ? (card.getType() <= Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16731) ? (card.getType() > Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16730) ? (card.getType() < Consts.CARD_TYPE_RELEARNING) : (ListenerUtil.mutListener.listen(16729) ? (card.getType() != Consts.CARD_TYPE_RELEARNING) : (card.getType() == Consts.CARD_TYPE_RELEARNING))))))))) {
                if (!ListenerUtil.mutListener.listen(16743)) {
                    if ((ListenerUtil.mutListener.listen(16740) ? (card.getODue() >= 1000000000) : (ListenerUtil.mutListener.listen(16739) ? (card.getODue() <= 1000000000) : (ListenerUtil.mutListener.listen(16738) ? (card.getODue() < 1000000000) : (ListenerUtil.mutListener.listen(16737) ? (card.getODue() != 1000000000) : (ListenerUtil.mutListener.listen(16736) ? (card.getODue() == 1000000000) : (card.getODue() > 1000000000))))))) {
                        if (!ListenerUtil.mutListener.listen(16742)) {
                            card.setQueue(Consts.QUEUE_TYPE_LRN);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(16741)) {
                            card.setQueue(Consts.QUEUE_TYPE_DAY_LEARN_RELEARN);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(16735)) {
                    card.setQueue(card.getType());
                }
            }
        }
    }

    /**
     * Leech handler. True if card was a leech.
     *        Overridden: in V1, due and did are changed
     */
    protected boolean _checkLeech(@NonNull Card card, @NonNull JSONObject conf) {
        int lf = conf.getInt("leechFails");
        if (!ListenerUtil.mutListener.listen(16750)) {
            if ((ListenerUtil.mutListener.listen(16749) ? (lf >= 0) : (ListenerUtil.mutListener.listen(16748) ? (lf <= 0) : (ListenerUtil.mutListener.listen(16747) ? (lf > 0) : (ListenerUtil.mutListener.listen(16746) ? (lf < 0) : (ListenerUtil.mutListener.listen(16745) ? (lf != 0) : (lf == 0))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(16785)) {
            // if over threshold or every half threshold reps after that
            if ((ListenerUtil.mutListener.listen(16773) ? ((ListenerUtil.mutListener.listen(16755) ? (card.getLapses() <= lf) : (ListenerUtil.mutListener.listen(16754) ? (card.getLapses() > lf) : (ListenerUtil.mutListener.listen(16753) ? (card.getLapses() < lf) : (ListenerUtil.mutListener.listen(16752) ? (card.getLapses() != lf) : (ListenerUtil.mutListener.listen(16751) ? (card.getLapses() == lf) : (card.getLapses() >= lf)))))) || (ListenerUtil.mutListener.listen(16772) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) >= 0) : (ListenerUtil.mutListener.listen(16771) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) <= 0) : (ListenerUtil.mutListener.listen(16770) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) > 0) : (ListenerUtil.mutListener.listen(16769) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) < 0) : (ListenerUtil.mutListener.listen(16768) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) != 0) : ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) == 0))))))) : ((ListenerUtil.mutListener.listen(16755) ? (card.getLapses() <= lf) : (ListenerUtil.mutListener.listen(16754) ? (card.getLapses() > lf) : (ListenerUtil.mutListener.listen(16753) ? (card.getLapses() < lf) : (ListenerUtil.mutListener.listen(16752) ? (card.getLapses() != lf) : (ListenerUtil.mutListener.listen(16751) ? (card.getLapses() == lf) : (card.getLapses() >= lf)))))) && (ListenerUtil.mutListener.listen(16772) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) >= 0) : (ListenerUtil.mutListener.listen(16771) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) <= 0) : (ListenerUtil.mutListener.listen(16770) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) > 0) : (ListenerUtil.mutListener.listen(16769) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) < 0) : (ListenerUtil.mutListener.listen(16768) ? ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) != 0) : ((ListenerUtil.mutListener.listen(16767) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16766) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16765) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(16764) ? (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(16759) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(16758) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(16757) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(16756) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(16763) ? (lf % 2) : (ListenerUtil.mutListener.listen(16762) ? (lf * 2) : (ListenerUtil.mutListener.listen(16761) ? (lf - 2) : (ListenerUtil.mutListener.listen(16760) ? (lf + 2) : (lf / 2))))), 1)))))) == 0))))))))) {
                // add a leech tag
                Note n = card.note();
                if (!ListenerUtil.mutListener.listen(16774)) {
                    n.addTag("leech");
                }
                if (!ListenerUtil.mutListener.listen(16775)) {
                    n.flush();
                }
                if (!ListenerUtil.mutListener.listen(16782)) {
                    // handle
                    if ((ListenerUtil.mutListener.listen(16780) ? (conf.getInt("leechAction") >= Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(16779) ? (conf.getInt("leechAction") <= Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(16778) ? (conf.getInt("leechAction") > Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(16777) ? (conf.getInt("leechAction") < Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(16776) ? (conf.getInt("leechAction") != Consts.LEECH_SUSPEND) : (conf.getInt("leechAction") == Consts.LEECH_SUSPEND))))))) {
                        if (!ListenerUtil.mutListener.listen(16781)) {
                            card.setQueue(Consts.QUEUE_TYPE_SUSPENDED);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(16784)) {
                    // notify UI
                    if (mContextReference != null) {
                        Activity context = mContextReference.get();
                        if (!ListenerUtil.mutListener.listen(16783)) {
                            leech(card, context);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @NonNull
    public DeckConfig _cardConf(@NonNull Card card) {
        return mCol.getDecks().confForDid(card.getDid());
    }

    // Overridden: different delays for filtered cards.
    @NonNull
    protected JSONObject _newConf(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        if (!ListenerUtil.mutListener.listen(16786)) {
            if (!card.isInDynamicDeck()) {
                return conf.getJSONObject("new");
            }
        }
        // dynamic deck; override some attributes, use original deck for others
        DeckConfig oconf = mCol.getDecks().confForDid(card.getODid());
        JSONObject dict = new JSONObject();
        if (!ListenerUtil.mutListener.listen(16787)) {
            // original deck
            dict.put("ints", oconf.getJSONObject("new").getJSONArray("ints"));
        }
        if (!ListenerUtil.mutListener.listen(16788)) {
            dict.put("initialFactor", oconf.getJSONObject("new").getInt("initialFactor"));
        }
        if (!ListenerUtil.mutListener.listen(16789)) {
            dict.put("bury", oconf.getJSONObject("new").optBoolean("bury", true));
        }
        if (!ListenerUtil.mutListener.listen(16790)) {
            dict.put("delays", oconf.getJSONObject("new").getJSONArray("delays"));
        }
        if (!ListenerUtil.mutListener.listen(16791)) {
            // overrides
            dict.put("separate", conf.getBoolean("separate"));
        }
        if (!ListenerUtil.mutListener.listen(16792)) {
            dict.put("order", Consts.NEW_CARDS_DUE);
        }
        if (!ListenerUtil.mutListener.listen(16793)) {
            dict.put("perDay", mReportLimit);
        }
        return dict;
    }

    // Overridden: different delays for filtered cards.
    @NonNull
    protected JSONObject _lapseConf(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        if (!ListenerUtil.mutListener.listen(16794)) {
            if (!card.isInDynamicDeck()) {
                return conf.getJSONObject("lapse");
            }
        }
        // dynamic deck; override some attributes, use original deck for others
        DeckConfig oconf = mCol.getDecks().confForDid(card.getODid());
        JSONObject dict = new JSONObject();
        if (!ListenerUtil.mutListener.listen(16795)) {
            // original deck
            dict.put("minInt", oconf.getJSONObject("lapse").getInt("minInt"));
        }
        if (!ListenerUtil.mutListener.listen(16796)) {
            dict.put("leechFails", oconf.getJSONObject("lapse").getInt("leechFails"));
        }
        if (!ListenerUtil.mutListener.listen(16797)) {
            dict.put("leechAction", oconf.getJSONObject("lapse").getInt("leechAction"));
        }
        if (!ListenerUtil.mutListener.listen(16798)) {
            dict.put("mult", oconf.getJSONObject("lapse").getDouble("mult"));
        }
        if (!ListenerUtil.mutListener.listen(16799)) {
            dict.put("delays", oconf.getJSONObject("lapse").getJSONArray("delays"));
        }
        if (!ListenerUtil.mutListener.listen(16800)) {
            // overrides
            dict.put("resched", conf.getBoolean("resched"));
        }
        return dict;
    }

    @NonNull
    protected JSONObject _revConf(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        if (!ListenerUtil.mutListener.listen(16801)) {
            if (!card.isInDynamicDeck()) {
                return conf.getJSONObject("rev");
            }
        }
        return mCol.getDecks().confForDid(card.getODid()).getJSONObject("rev");
    }

    @NonNull
    public String _deckLimit() {
        return Utils.ids2str(mCol.getDecks().active());
    }

    private boolean _previewingCard(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        return (ListenerUtil.mutListener.listen(16802) ? (conf.getInt("dyn") == DECK_DYN || !conf.getBoolean("resched")) : (conf.getInt("dyn") == DECK_DYN && !conf.getBoolean("resched")));
    }

    private int _previewDelay(@NonNull Card card) {
        return (ListenerUtil.mutListener.listen(16806) ? (_cardConf(card).optInt("previewDelay", 10) % 60) : (ListenerUtil.mutListener.listen(16805) ? (_cardConf(card).optInt("previewDelay", 10) / 60) : (ListenerUtil.mutListener.listen(16804) ? (_cardConf(card).optInt("previewDelay", 10) - 60) : (ListenerUtil.mutListener.listen(16803) ? (_cardConf(card).optInt("previewDelay", 10) + 60) : (_cardConf(card).optInt("previewDelay", 10) * 60)))));
    }

    /* Overriden: other way to count time*/
    public void _updateCutoff() {
        int oldToday = mToday == null ? 0 : mToday;
        if (!ListenerUtil.mutListener.listen(16807)) {
            // days since col created
            mToday = _daysSinceCreation();
        }
        if (!ListenerUtil.mutListener.listen(16808)) {
            // end of day cutoff
            mDayCutoff = _dayCutoff();
        }
        if (!ListenerUtil.mutListener.listen(16815)) {
            if ((ListenerUtil.mutListener.listen(16813) ? (oldToday >= mToday) : (ListenerUtil.mutListener.listen(16812) ? (oldToday <= mToday) : (ListenerUtil.mutListener.listen(16811) ? (oldToday > mToday) : (ListenerUtil.mutListener.listen(16810) ? (oldToday < mToday) : (ListenerUtil.mutListener.listen(16809) ? (oldToday == mToday) : (oldToday != mToday))))))) {
                if (!ListenerUtil.mutListener.listen(16814)) {
                    mCol.log(mToday, mDayCutoff);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16817)) {
            {
                long _loopCounter321 = 0;
                // instead
                for (Deck deck : mCol.getDecks().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter321", ++_loopCounter321);
                    if (!ListenerUtil.mutListener.listen(16816)) {
                        update(deck);
                    }
                }
            }
        }
        // unbury if the day has rolled over
        int unburied = mCol.getConf().optInt("lastUnburied", 0);
        if (!ListenerUtil.mutListener.listen(16825)) {
            if ((ListenerUtil.mutListener.listen(16822) ? (unburied >= mToday) : (ListenerUtil.mutListener.listen(16821) ? (unburied <= mToday) : (ListenerUtil.mutListener.listen(16820) ? (unburied > mToday) : (ListenerUtil.mutListener.listen(16819) ? (unburied != mToday) : (ListenerUtil.mutListener.listen(16818) ? (unburied == mToday) : (unburied < mToday))))))) {
                if (!ListenerUtil.mutListener.listen(16823)) {
                    SyncStatus.ignoreDatabaseModification(this::unburyCards);
                }
                if (!ListenerUtil.mutListener.listen(16824)) {
                    mCol.getConf().put("lastUnburied", mToday);
                }
            }
        }
    }

    private long _dayCutoff() {
        int rolloverTime = mCol.getConf().optInt("rollover", 4);
        if (!ListenerUtil.mutListener.listen(16836)) {
            if ((ListenerUtil.mutListener.listen(16830) ? (rolloverTime >= 0) : (ListenerUtil.mutListener.listen(16829) ? (rolloverTime <= 0) : (ListenerUtil.mutListener.listen(16828) ? (rolloverTime > 0) : (ListenerUtil.mutListener.listen(16827) ? (rolloverTime != 0) : (ListenerUtil.mutListener.listen(16826) ? (rolloverTime == 0) : (rolloverTime < 0))))))) {
                if (!ListenerUtil.mutListener.listen(16835)) {
                    rolloverTime = (ListenerUtil.mutListener.listen(16834) ? (24 % rolloverTime) : (ListenerUtil.mutListener.listen(16833) ? (24 / rolloverTime) : (ListenerUtil.mutListener.listen(16832) ? (24 * rolloverTime) : (ListenerUtil.mutListener.listen(16831) ? (24 - rolloverTime) : (24 + rolloverTime)))));
                }
            }
        }
        Calendar date = getTime().calendar();
        if (!ListenerUtil.mutListener.listen(16837)) {
            date.set(Calendar.HOUR_OF_DAY, rolloverTime);
        }
        if (!ListenerUtil.mutListener.listen(16838)) {
            date.set(Calendar.MINUTE, 0);
        }
        if (!ListenerUtil.mutListener.listen(16839)) {
            date.set(Calendar.SECOND, 0);
        }
        if (!ListenerUtil.mutListener.listen(16840)) {
            date.set(Calendar.MILLISECOND, 0);
        }
        Calendar today = getTime().calendar();
        if (!ListenerUtil.mutListener.listen(16842)) {
            if (date.before(today)) {
                if (!ListenerUtil.mutListener.listen(16841)) {
                    date.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        }
        return (ListenerUtil.mutListener.listen(16846) ? (date.getTimeInMillis() % 1000) : (ListenerUtil.mutListener.listen(16845) ? (date.getTimeInMillis() * 1000) : (ListenerUtil.mutListener.listen(16844) ? (date.getTimeInMillis() - 1000) : (ListenerUtil.mutListener.listen(16843) ? (date.getTimeInMillis() + 1000) : (date.getTimeInMillis() / 1000)))));
    }

    private int _daysSinceCreation() {
        Calendar c = mCol.crtCalendar();
        if (!ListenerUtil.mutListener.listen(16847)) {
            c.set(Calendar.HOUR, mCol.getConf().optInt("rollover", 4));
        }
        if (!ListenerUtil.mutListener.listen(16848)) {
            c.set(Calendar.MINUTE, 0);
        }
        if (!ListenerUtil.mutListener.listen(16849)) {
            c.set(Calendar.SECOND, 0);
        }
        if (!ListenerUtil.mutListener.listen(16850)) {
            c.set(Calendar.MILLISECOND, 0);
        }
        return (int) ((ListenerUtil.mutListener.listen(16862) ? (((ListenerUtil.mutListener.listen(16858) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) % 1000) : (ListenerUtil.mutListener.listen(16857) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) * 1000) : (ListenerUtil.mutListener.listen(16856) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) - 1000) : (ListenerUtil.mutListener.listen(16855) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) + 1000) : (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) / 1000)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16861) ? (((ListenerUtil.mutListener.listen(16858) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) % 1000) : (ListenerUtil.mutListener.listen(16857) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) * 1000) : (ListenerUtil.mutListener.listen(16856) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) - 1000) : (ListenerUtil.mutListener.listen(16855) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) + 1000) : (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) / 1000)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16860) ? (((ListenerUtil.mutListener.listen(16858) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) % 1000) : (ListenerUtil.mutListener.listen(16857) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) * 1000) : (ListenerUtil.mutListener.listen(16856) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) - 1000) : (ListenerUtil.mutListener.listen(16855) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) + 1000) : (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) / 1000)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16859) ? (((ListenerUtil.mutListener.listen(16858) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) % 1000) : (ListenerUtil.mutListener.listen(16857) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) * 1000) : (ListenerUtil.mutListener.listen(16856) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) - 1000) : (ListenerUtil.mutListener.listen(16855) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) + 1000) : (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) / 1000)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(16858) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) % 1000) : (ListenerUtil.mutListener.listen(16857) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) * 1000) : (ListenerUtil.mutListener.listen(16856) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) - 1000) : (ListenerUtil.mutListener.listen(16855) ? (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) + 1000) : (((ListenerUtil.mutListener.listen(16854) ? (getTime().intTimeMS() % c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16853) ? (getTime().intTimeMS() / c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16852) ? (getTime().intTimeMS() * c.getTimeInMillis()) : (ListenerUtil.mutListener.listen(16851) ? (getTime().intTimeMS() + c.getTimeInMillis()) : (getTime().intTimeMS() - c.getTimeInMillis())))))) / 1000)))))) / SECONDS_PER_DAY))))));
    }

    protected void update(@NonNull Deck g) {
        if (!ListenerUtil.mutListener.listen(16866)) {
            {
                long _loopCounter322 = 0;
                for (String t : new String[] { "new", "rev", "lrn", "time" }) {
                    ListenerUtil.loopListener.listen("_loopCounter322", ++_loopCounter322);
                    String key = t + "Today";
                    JSONArray tToday = g.getJSONArray(key);
                    if (!ListenerUtil.mutListener.listen(16865)) {
                        if (g.getJSONArray(key).getInt(0) != mToday) {
                            if (!ListenerUtil.mutListener.listen(16863)) {
                                tToday.put(0, mToday);
                            }
                            if (!ListenerUtil.mutListener.listen(16864)) {
                                tToday.put(1, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    public void _checkDay() {
        if (!ListenerUtil.mutListener.listen(16873)) {
            // check if the day has rolled over
            if ((ListenerUtil.mutListener.listen(16871) ? (getTime().intTime() >= mDayCutoff) : (ListenerUtil.mutListener.listen(16870) ? (getTime().intTime() <= mDayCutoff) : (ListenerUtil.mutListener.listen(16869) ? (getTime().intTime() < mDayCutoff) : (ListenerUtil.mutListener.listen(16868) ? (getTime().intTime() != mDayCutoff) : (ListenerUtil.mutListener.listen(16867) ? (getTime().intTime() == mDayCutoff) : (getTime().intTime() > mDayCutoff))))))) {
                if (!ListenerUtil.mutListener.listen(16872)) {
                    reset();
                }
            }
        }
    }

    @NonNull
    public CharSequence finishedMsg(@NonNull Context context) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        if (!ListenerUtil.mutListener.listen(16874)) {
            sb.append(context.getString(R.string.studyoptions_congrats_finished));
        }
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        if (!ListenerUtil.mutListener.listen(16875)) {
            sb.setSpan(boldSpan, 0, sb.length(), 0);
        }
        if (!ListenerUtil.mutListener.listen(16876)) {
            sb.append(_nextDueMsg(context));
        }
        // sb.append(_tomorrowDueMsg(context));
        return sb;
    }

    @NonNull
    public String _nextDueMsg(@NonNull Context context) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(16879)) {
            if (revDue()) {
                if (!ListenerUtil.mutListener.listen(16877)) {
                    sb.append("\n\n");
                }
                if (!ListenerUtil.mutListener.listen(16878)) {
                    sb.append(context.getString(R.string.studyoptions_congrats_more_rev));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16882)) {
            if (newDue()) {
                if (!ListenerUtil.mutListener.listen(16880)) {
                    sb.append("\n\n");
                }
                if (!ListenerUtil.mutListener.listen(16881)) {
                    sb.append(context.getString(R.string.studyoptions_congrats_more_new));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16885)) {
            if (haveBuried()) {
                String now = " " + context.getString(R.string.sched_unbury_action);
                if (!ListenerUtil.mutListener.listen(16883)) {
                    sb.append("\n\n");
                }
                if (!ListenerUtil.mutListener.listen(16884)) {
                    sb.append("").append(context.getString(R.string.sched_has_buried)).append(now);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(16888)) {
            if (mCol.getDecks().current().getInt("dyn") == DECK_STD) {
                if (!ListenerUtil.mutListener.listen(16886)) {
                    sb.append("\n\n");
                }
                if (!ListenerUtil.mutListener.listen(16887)) {
                    sb.append(context.getString(R.string.studyoptions_congrats_custom));
                }
            }
        }
        return sb.toString();
    }

    /**
     * true if there are any rev cards due.
     */
    public boolean revDue() {
        return mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_REV + " AND due <= ?" + " LIMIT 1", mToday) != 0;
    }

    /* not in upstream anki. As revDue and newDue, it's used to check
     * what to do when a deck is selected in deck picker. When this
     * method is called, we already know that no cards is due
     * immedietly. It answers whether cards will be due later in the
     * same deck. */
    public boolean hasCardsTodayAfterStudyAheadLimit() {
        return mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_LRN + " LIMIT 1") != 0;
    }

    /**
     * true if there are any new cards due.
     */
    public boolean newDue() {
        return mCol.getDb().queryScalar("SELECT 1 FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_NEW + " LIMIT 1") != 0;
    }

    public boolean haveBuriedSiblings() {
        return haveBuriedSiblings(mCol.getDecks().active());
    }

    private boolean haveBuriedSiblings(@NonNull List<Long> allDecks) {
        // Refactored to allow querying an arbitrary deck
        String sdids = Utils.ids2str(allDecks);
        int cnt = mCol.getDb().queryScalar("select 1 from cards where queue = " + Consts.QUEUE_TYPE_SIBLING_BURIED + " and did in " + sdids + " limit 1");
        return (ListenerUtil.mutListener.listen(16893) ? (cnt >= 0) : (ListenerUtil.mutListener.listen(16892) ? (cnt <= 0) : (ListenerUtil.mutListener.listen(16891) ? (cnt > 0) : (ListenerUtil.mutListener.listen(16890) ? (cnt < 0) : (ListenerUtil.mutListener.listen(16889) ? (cnt == 0) : (cnt != 0))))));
    }

    public boolean haveManuallyBuried() {
        return haveManuallyBuried(mCol.getDecks().active());
    }

    private boolean haveManuallyBuried(@NonNull List<Long> allDecks) {
        // Refactored to allow querying an arbitrary deck
        String sdids = Utils.ids2str(allDecks);
        int cnt = mCol.getDb().queryScalar("select 1 from cards where queue = " + Consts.QUEUE_TYPE_MANUALLY_BURIED + " and did in " + sdids + " limit 1");
        return (ListenerUtil.mutListener.listen(16898) ? (cnt >= 0) : (ListenerUtil.mutListener.listen(16897) ? (cnt <= 0) : (ListenerUtil.mutListener.listen(16896) ? (cnt > 0) : (ListenerUtil.mutListener.listen(16895) ? (cnt < 0) : (ListenerUtil.mutListener.listen(16894) ? (cnt == 0) : (cnt != 0))))));
    }

    public boolean haveBuried() {
        return (ListenerUtil.mutListener.listen(16899) ? (haveManuallyBuried() && haveBuriedSiblings()) : (haveManuallyBuried() || haveBuriedSiblings()));
    }

    /**
     * Return the next interval for a card and ease as a string.
     *
     * For a given card and ease, this returns a string that shows when the card will be shown again when the
     * specific ease button (AGAIN, GOOD etc.) is touched. This uses unit symbols like “s” rather than names
     * (“second”), like Anki desktop.
     *
     * @param context The app context, used for localization
     * @param card The card being reviewed
     * @param ease The button number (easy, good etc.)
     * @return A string like “1 min” or “1.7 mo”
     */
    @NonNull
    public String nextIvlStr(@NonNull Context context, @NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        long ivl = nextIvl(card, ease);
        if (!ListenerUtil.mutListener.listen(16905)) {
            if ((ListenerUtil.mutListener.listen(16904) ? (ivl >= 0) : (ListenerUtil.mutListener.listen(16903) ? (ivl <= 0) : (ListenerUtil.mutListener.listen(16902) ? (ivl > 0) : (ListenerUtil.mutListener.listen(16901) ? (ivl < 0) : (ListenerUtil.mutListener.listen(16900) ? (ivl != 0) : (ivl == 0))))))) {
                return context.getString(R.string.sched_end);
            }
        }
        String s = Utils.timeQuantityNextIvl(context, ivl);
        if (!ListenerUtil.mutListener.listen(16912)) {
            if ((ListenerUtil.mutListener.listen(16910) ? (ivl >= mCol.getConf().getInt("collapseTime")) : (ListenerUtil.mutListener.listen(16909) ? (ivl <= mCol.getConf().getInt("collapseTime")) : (ListenerUtil.mutListener.listen(16908) ? (ivl > mCol.getConf().getInt("collapseTime")) : (ListenerUtil.mutListener.listen(16907) ? (ivl != mCol.getConf().getInt("collapseTime")) : (ListenerUtil.mutListener.listen(16906) ? (ivl == mCol.getConf().getInt("collapseTime")) : (ivl < mCol.getConf().getInt("collapseTime")))))))) {
                if (!ListenerUtil.mutListener.listen(16911)) {
                    s = context.getString(R.string.less_than_time, s);
                }
            }
        }
        return s;
    }

    // Overriden
    protected long nextIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        // preview mode?
        if (_previewingCard(card)) {
            if ((ListenerUtil.mutListener.listen(16917) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16916) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16915) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16914) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16913) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
                return _previewDelay(card);
            }
            return 0;
        }
        // (re)learning?
        if ((ListenerUtil.mutListener.listen(16934) ? ((ListenerUtil.mutListener.listen(16928) ? ((ListenerUtil.mutListener.listen(16922) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16921) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16920) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16919) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16918) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(16927) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16926) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16925) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16924) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16923) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN))))))) : ((ListenerUtil.mutListener.listen(16922) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16921) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16920) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16919) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16918) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(16927) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16926) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16925) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16924) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16923) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))))) && (ListenerUtil.mutListener.listen(16933) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16932) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16931) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16930) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16929) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(16928) ? ((ListenerUtil.mutListener.listen(16922) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16921) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16920) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16919) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16918) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(16927) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16926) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16925) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16924) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16923) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN))))))) : ((ListenerUtil.mutListener.listen(16922) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16921) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16920) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16919) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16918) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(16927) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16926) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16925) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16924) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(16923) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))))) || (ListenerUtil.mutListener.listen(16933) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16932) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16931) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16930) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(16929) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))))) {
            return _nextLrnIvl(card, ease);
        } else if ((ListenerUtil.mutListener.listen(16939) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16938) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16937) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16936) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16935) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
            // lapse
            JSONObject conf = _lapseConf(card);
            if ((ListenerUtil.mutListener.listen(16958) ? (conf.getJSONArray("delays").length() >= 0) : (ListenerUtil.mutListener.listen(16957) ? (conf.getJSONArray("delays").length() <= 0) : (ListenerUtil.mutListener.listen(16956) ? (conf.getJSONArray("delays").length() < 0) : (ListenerUtil.mutListener.listen(16955) ? (conf.getJSONArray("delays").length() != 0) : (ListenerUtil.mutListener.listen(16954) ? (conf.getJSONArray("delays").length() == 0) : (conf.getJSONArray("delays").length() > 0))))))) {
                return (long) ((ListenerUtil.mutListener.listen(16962) ? (conf.getJSONArray("delays").getDouble(0) % 60.0) : (ListenerUtil.mutListener.listen(16961) ? (conf.getJSONArray("delays").getDouble(0) / 60.0) : (ListenerUtil.mutListener.listen(16960) ? (conf.getJSONArray("delays").getDouble(0) - 60.0) : (ListenerUtil.mutListener.listen(16959) ? (conf.getJSONArray("delays").getDouble(0) + 60.0) : (conf.getJSONArray("delays").getDouble(0) * 60.0))))));
            }
            return (ListenerUtil.mutListener.listen(16966) ? (_lapseIvl(card, conf) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16965) ? (_lapseIvl(card, conf) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16964) ? (_lapseIvl(card, conf) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16963) ? (_lapseIvl(card, conf) + SECONDS_PER_DAY) : (_lapseIvl(card, conf) * SECONDS_PER_DAY)))));
        } else {
            // review
            boolean early = (ListenerUtil.mutListener.listen(16945) ? (card.isInDynamicDeck() || ((ListenerUtil.mutListener.listen(16944) ? (card.getODue() >= mToday) : (ListenerUtil.mutListener.listen(16943) ? (card.getODue() <= mToday) : (ListenerUtil.mutListener.listen(16942) ? (card.getODue() < mToday) : (ListenerUtil.mutListener.listen(16941) ? (card.getODue() != mToday) : (ListenerUtil.mutListener.listen(16940) ? (card.getODue() == mToday) : (card.getODue() > mToday)))))))) : (card.isInDynamicDeck() && ((ListenerUtil.mutListener.listen(16944) ? (card.getODue() >= mToday) : (ListenerUtil.mutListener.listen(16943) ? (card.getODue() <= mToday) : (ListenerUtil.mutListener.listen(16942) ? (card.getODue() < mToday) : (ListenerUtil.mutListener.listen(16941) ? (card.getODue() != mToday) : (ListenerUtil.mutListener.listen(16940) ? (card.getODue() == mToday) : (card.getODue() > mToday)))))))));
            if (early) {
                return (ListenerUtil.mutListener.listen(16953) ? (_earlyReviewIvl(card, ease) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16952) ? (_earlyReviewIvl(card, ease) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16951) ? (_earlyReviewIvl(card, ease) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16950) ? (_earlyReviewIvl(card, ease) + SECONDS_PER_DAY) : (_earlyReviewIvl(card, ease) * SECONDS_PER_DAY)))));
            } else {
                return (ListenerUtil.mutListener.listen(16949) ? (_nextRevIvl(card, ease, false) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16948) ? (_nextRevIvl(card, ease, false) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16947) ? (_nextRevIvl(card, ease, false) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(16946) ? (_nextRevIvl(card, ease, false) + SECONDS_PER_DAY) : (_nextRevIvl(card, ease, false) * SECONDS_PER_DAY)))));
            }
        }
    }

    // Overriden
    protected long _nextLrnIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(16973)) {
            if ((ListenerUtil.mutListener.listen(16971) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16970) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16969) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16968) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(16967) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW))))))) {
                if (!ListenerUtil.mutListener.listen(16972)) {
                    card.setLeft(_startingLeft(card));
                }
            }
        }
        JSONObject conf = _lrnConf(card);
        if ((ListenerUtil.mutListener.listen(16978) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16977) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16976) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16975) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(16974) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
            // fail
            return _delayForGrade(conf, conf.getJSONArray("delays").length());
        } else if ((ListenerUtil.mutListener.listen(16983) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16982) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16981) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16980) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(16979) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO))))))) {
            return _delayForRepeatingGrade(conf, card.getLeft());
        } else if ((ListenerUtil.mutListener.listen(16988) ? (ease >= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16987) ? (ease <= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16986) ? (ease > Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16985) ? (ease < Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(16984) ? (ease != Consts.BUTTON_FOUR) : (ease == Consts.BUTTON_FOUR))))))) {
            return (ListenerUtil.mutListener.listen(17009) ? (_graduatingIvl(card, conf, true, false) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17008) ? (_graduatingIvl(card, conf, true, false) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17007) ? (_graduatingIvl(card, conf, true, false) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17006) ? (_graduatingIvl(card, conf, true, false) + SECONDS_PER_DAY) : (_graduatingIvl(card, conf, true, false) * SECONDS_PER_DAY)))));
        } else {
            // ease == 3
            int left = (ListenerUtil.mutListener.listen(16996) ? ((ListenerUtil.mutListener.listen(16992) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16991) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16990) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16989) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) % 1) : (ListenerUtil.mutListener.listen(16995) ? ((ListenerUtil.mutListener.listen(16992) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16991) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16990) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16989) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) / 1) : (ListenerUtil.mutListener.listen(16994) ? ((ListenerUtil.mutListener.listen(16992) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16991) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16990) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16989) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) * 1) : (ListenerUtil.mutListener.listen(16993) ? ((ListenerUtil.mutListener.listen(16992) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16991) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16990) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16989) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) + 1) : ((ListenerUtil.mutListener.listen(16992) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(16991) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(16990) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(16989) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) - 1)))));
            if ((ListenerUtil.mutListener.listen(17001) ? (left >= 0) : (ListenerUtil.mutListener.listen(17000) ? (left > 0) : (ListenerUtil.mutListener.listen(16999) ? (left < 0) : (ListenerUtil.mutListener.listen(16998) ? (left != 0) : (ListenerUtil.mutListener.listen(16997) ? (left == 0) : (left <= 0))))))) {
                // graduate
                return (ListenerUtil.mutListener.listen(17005) ? (_graduatingIvl(card, conf, false, false) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17004) ? (_graduatingIvl(card, conf, false, false) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17003) ? (_graduatingIvl(card, conf, false, false) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17002) ? (_graduatingIvl(card, conf, false, false) + SECONDS_PER_DAY) : (_graduatingIvl(card, conf, false, false) * SECONDS_PER_DAY)))));
            } else {
                return _delayForGrade(conf, left);
            }
        }
    }

    /**
     * learning and relearning cards may be seconds-based or day-based;
     * other types map directly to queues
     *
     * Overriden: in V1, queue becomes type.
     */
    @NonNull
    protected String _restoreQueueSnippet() {
        return "queue = (case when type in (" + Consts.CARD_TYPE_LRN + "," + Consts.CARD_TYPE_RELEARNING + ") then\n" + "  (case when (case when odue then odue else due end) > 1000000000 then 1 else " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " end)\n" + "else\n" + "  type\n" + "end)  ";
    }

    /**
     * ugly fix for suspended cards being unsuspended when filtered deck emptied
     * https://github.com/ankitects/anki/commit/fe493e31c4d73ae2bbd0c4d8c6b835974c0e290c
     */
    @NonNull
    protected String _restoreQueueWhenEmptyingSnippet() {
        return "queue = (case when queue < 0 then queue" + "    when type in (1," + CARD_TYPE_RELEARNING + ") then " + "(case when (case when odue then odue else due end) > 1000000000 then 1 else " + "    " + QUEUE_TYPE_DAY_LEARN_RELEARN + " end) " + "else " + "    type " + "end)";
    }

    /**
     * Overridden: in V1 only sibling buried exits.
     */
    @NonNull
    protected String queueIsBuriedSnippet() {
        return " queue in (" + Consts.QUEUE_TYPE_SIBLING_BURIED + ", " + Consts.QUEUE_TYPE_MANUALLY_BURIED + ") ";
    }

    /**
     * Suspend cards.
     *
     * Overridden: in V1 remove from dyn and lrn
     */
    public void suspendCards(@NonNull long[] ids) {
        if (!ListenerUtil.mutListener.listen(17010)) {
            mCol.log(ids);
        }
        if (!ListenerUtil.mutListener.listen(17011)) {
            mCol.getDb().execute("UPDATE cards SET queue = " + Consts.QUEUE_TYPE_SUSPENDED + ", mod = ?, usn = ? WHERE id IN " + Utils.ids2str(ids), getTime().intTime(), mCol.usn());
        }
    }

    /**
     * Unsuspend cards
     */
    public void unsuspendCards(@NonNull long[] ids) {
        if (!ListenerUtil.mutListener.listen(17012)) {
            mCol.log(ids);
        }
        if (!ListenerUtil.mutListener.listen(17013)) {
            mCol.getDb().execute("UPDATE cards SET " + _restoreQueueSnippet() + ", mod = ?, usn = ?" + " WHERE queue = " + Consts.QUEUE_TYPE_SUSPENDED + " AND id IN " + Utils.ids2str(ids), getTime().intTime(), mCol.usn());
        }
    }

    // Overriden. manual is false by default in V1
    public void buryCards(@NonNull long[] cids) {
        if (!ListenerUtil.mutListener.listen(17014)) {
            buryCards(cids, true);
        }
    }

    @Override
    // Overriden: V1 also remove from dyns and lrn
    @VisibleForTesting
    public void buryCards(@NonNull long[] cids, boolean manual) {
        int queue = manual ? Consts.QUEUE_TYPE_MANUALLY_BURIED : Consts.QUEUE_TYPE_SIBLING_BURIED;
        if (!ListenerUtil.mutListener.listen(17015)) {
            mCol.log(cids);
        }
        if (!ListenerUtil.mutListener.listen(17016)) {
            mCol.getDb().execute("update cards set queue=?,mod=?,usn=? where id in " + Utils.ids2str(cids), queue, getTime().intTime(), mCol.usn());
        }
    }

    /**
     * Unbury all buried cards in all decks
     * Overriden: V1 change lastUnburied
     */
    public void unburyCards() {
        if (!ListenerUtil.mutListener.listen(17017)) {
            mCol.log(mCol.getDb().queryLongList("select id from cards where " + queueIsBuriedSnippet()));
        }
        if (!ListenerUtil.mutListener.listen(17018)) {
            mCol.getDb().execute("update cards set " + _restoreQueueSnippet() + " where " + queueIsBuriedSnippet());
        }
    }

    // Overridden
    public void unburyCardsForDeck() {
        if (!ListenerUtil.mutListener.listen(17019)) {
            unburyCardsForDeck(ALL);
        }
    }

    public void unburyCardsForDeck(@NonNull UnburyType type) {
        if (!ListenerUtil.mutListener.listen(17020)) {
            unburyCardsForDeck(type, null);
        }
    }

    public void unburyCardsForDeck(@NonNull UnburyType type, @Nullable List<Long> allDecks) {
        String queue;
        switch(type) {
            case ALL:
                queue = queueIsBuriedSnippet();
                break;
            case MANUAL:
                queue = "queue = " + Consts.QUEUE_TYPE_MANUALLY_BURIED;
                break;
            case SIBLINGS:
                queue = "queue = " + Consts.QUEUE_TYPE_SIBLING_BURIED;
                break;
            default:
                throw new RuntimeException("unknown type");
        }
        String sids = Utils.ids2str(allDecks != null ? allDecks : mCol.getDecks().active());
        if (!ListenerUtil.mutListener.listen(17021)) {
            mCol.log(mCol.getDb().queryLongList("select id from cards where " + queue + " and did in " + sids));
        }
        if (!ListenerUtil.mutListener.listen(17022)) {
            mCol.getDb().execute("update cards set mod=?,usn=?, " + _restoreQueueSnippet() + " where " + queue + " and did in " + sids, getTime().intTime(), mCol.usn());
        }
    }

    /**
     * Bury all cards for note until next session.
     * @param nid The id of the targeted note.
     */
    public void buryNote(long nid) {
        long[] cids = Utils.collection2Array(mCol.getDb().queryLongList("SELECT id FROM cards WHERE nid = ? AND queue >= " + Consts.CARD_TYPE_NEW, nid));
        if (!ListenerUtil.mutListener.listen(17023)) {
            buryCards(cids);
        }
    }

    protected void _burySiblings(@NonNull Card card) {
        ArrayList<Long> toBury = new ArrayList<>();
        JSONObject nconf = _newConf(card);
        boolean buryNew = nconf.optBoolean("bury", true);
        JSONObject rconf = _revConf(card);
        boolean buryRev = rconf.optBoolean("bury", true);
        // loop through and remove from queues
        try (Cursor cur = mCol.getDb().query("select id, queue from cards where nid=? and id!=? " + "and (queue=" + Consts.QUEUE_TYPE_NEW + " or (queue=" + Consts.QUEUE_TYPE_REV + " and due<=?))", card.getNid(), card.getId(), mToday)) {
            if (!ListenerUtil.mutListener.listen(17034)) {
                {
                    long _loopCounter323 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter323", ++_loopCounter323);
                        long cid = cur.getLong(0);
                        int queue = cur.getInt(1);
                        SimpleCardQueue queue_object;
                        if ((ListenerUtil.mutListener.listen(17028) ? (queue >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(17027) ? (queue <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(17026) ? (queue > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(17025) ? (queue < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(17024) ? (queue != Consts.QUEUE_TYPE_REV) : (queue == Consts.QUEUE_TYPE_REV))))))) {
                            queue_object = mRevQueue;
                            if (!ListenerUtil.mutListener.listen(17032)) {
                                if (buryRev) {
                                    if (!ListenerUtil.mutListener.listen(17031)) {
                                        toBury.add(cid);
                                    }
                                }
                            }
                        } else {
                            queue_object = mNewQueue;
                            if (!ListenerUtil.mutListener.listen(17030)) {
                                if (buryNew) {
                                    if (!ListenerUtil.mutListener.listen(17029)) {
                                        toBury.add(cid);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(17033)) {
                            // same-day spacing
                            queue_object.remove(cid);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17036)) {
            // then bury
            if (!toBury.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(17035)) {
                    buryCards(Utils.collection2Array(toBury), false);
                }
            }
        }
    }

    /**
     * Put cards at the end of the new queue.
     */
    public void forgetCards(@NonNull List<Long> ids) {
        if (!ListenerUtil.mutListener.listen(17037)) {
            remFromDyn(ids);
        }
        if (!ListenerUtil.mutListener.listen(17038)) {
            mCol.getDb().execute("update cards set type=" + Consts.CARD_TYPE_NEW + ",queue=" + Consts.QUEUE_TYPE_NEW + ",ivl=0,due=0,odue=0,factor=" + Consts.STARTING_FACTOR + " where id in " + Utils.ids2str(ids));
        }
        int pmax = mCol.getDb().queryScalar("SELECT max(due) FROM cards WHERE type=" + Consts.CARD_TYPE_NEW + "");
        if (!ListenerUtil.mutListener.listen(17043)) {
            // takes care of mod + usn
            sortCards(ids, (ListenerUtil.mutListener.listen(17042) ? (pmax % 1) : (ListenerUtil.mutListener.listen(17041) ? (pmax / 1) : (ListenerUtil.mutListener.listen(17040) ? (pmax * 1) : (ListenerUtil.mutListener.listen(17039) ? (pmax - 1) : (pmax + 1))))));
        }
        if (!ListenerUtil.mutListener.listen(17044)) {
            mCol.log(ids);
        }
    }

    /**
     * Put cards in review queue with a new interval in days (min, max).
     *
     * @param ids The list of card ids to be affected
     * @param imin the minimum interval (inclusive)
     * @param imax The maximum interval (inclusive)
     */
    public void reschedCards(@NonNull List<Long> ids, int imin, int imax) {
        ArrayList<Object[]> d = new ArrayList<>(ids.size());
        int t = mToday;
        long mod = getTime().intTime();
        Random rnd = new Random();
        if (!ListenerUtil.mutListener.listen(17062)) {
            {
                long _loopCounter324 = 0;
                for (long id : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter324", ++_loopCounter324);
                    int r = (ListenerUtil.mutListener.listen(17056) ? (rnd.nextInt((ListenerUtil.mutListener.listen(17052) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) % 1) : (ListenerUtil.mutListener.listen(17051) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) / 1) : (ListenerUtil.mutListener.listen(17050) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) * 1) : (ListenerUtil.mutListener.listen(17049) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) - 1) : ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) + 1)))))) % imin) : (ListenerUtil.mutListener.listen(17055) ? (rnd.nextInt((ListenerUtil.mutListener.listen(17052) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) % 1) : (ListenerUtil.mutListener.listen(17051) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) / 1) : (ListenerUtil.mutListener.listen(17050) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) * 1) : (ListenerUtil.mutListener.listen(17049) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) - 1) : ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) + 1)))))) / imin) : (ListenerUtil.mutListener.listen(17054) ? (rnd.nextInt((ListenerUtil.mutListener.listen(17052) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) % 1) : (ListenerUtil.mutListener.listen(17051) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) / 1) : (ListenerUtil.mutListener.listen(17050) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) * 1) : (ListenerUtil.mutListener.listen(17049) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) - 1) : ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) + 1)))))) * imin) : (ListenerUtil.mutListener.listen(17053) ? (rnd.nextInt((ListenerUtil.mutListener.listen(17052) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) % 1) : (ListenerUtil.mutListener.listen(17051) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) / 1) : (ListenerUtil.mutListener.listen(17050) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) * 1) : (ListenerUtil.mutListener.listen(17049) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) - 1) : ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) + 1)))))) - imin) : (rnd.nextInt((ListenerUtil.mutListener.listen(17052) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) % 1) : (ListenerUtil.mutListener.listen(17051) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) / 1) : (ListenerUtil.mutListener.listen(17050) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) * 1) : (ListenerUtil.mutListener.listen(17049) ? ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) - 1) : ((ListenerUtil.mutListener.listen(17048) ? (imax % imin) : (ListenerUtil.mutListener.listen(17047) ? (imax / imin) : (ListenerUtil.mutListener.listen(17046) ? (imax * imin) : (ListenerUtil.mutListener.listen(17045) ? (imax + imin) : (imax - imin))))) + 1)))))) + imin)))));
                    if (!ListenerUtil.mutListener.listen(17061)) {
                        d.add(new Object[] { Math.max(1, r), (ListenerUtil.mutListener.listen(17060) ? (r % t) : (ListenerUtil.mutListener.listen(17059) ? (r / t) : (ListenerUtil.mutListener.listen(17058) ? (r * t) : (ListenerUtil.mutListener.listen(17057) ? (r - t) : (r + t))))), mCol.usn(), mod, RESCHEDULE_FACTOR, id });
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17063)) {
            remFromDyn(ids);
        }
        if (!ListenerUtil.mutListener.listen(17064)) {
            mCol.getDb().executeMany("update cards set type=" + Consts.CARD_TYPE_REV + ",queue=" + Consts.QUEUE_TYPE_REV + ",ivl=?,due=?,odue=0, " + "usn=?,mod=?,factor=? where id=?", d);
        }
        if (!ListenerUtil.mutListener.listen(17065)) {
            mCol.log(ids);
        }
    }

    /**
     * Completely reset cards for export.
     */
    public void resetCards(@NonNull Long[] ids) {
        List<Long> nonNew = mCol.getDb().queryLongList("select id from cards where id in " + Utils.ids2str(ids) + " and (queue != " + Consts.QUEUE_TYPE_NEW + " or type != " + Consts.CARD_TYPE_NEW + ")");
        if (!ListenerUtil.mutListener.listen(17066)) {
            mCol.getDb().execute("update cards set reps=0, lapses=0 where id in " + Utils.ids2str(nonNew));
        }
        if (!ListenerUtil.mutListener.listen(17067)) {
            forgetCards(nonNew);
        }
        if (!ListenerUtil.mutListener.listen(17068)) {
            // Cast useful to indicate to indicate how to interpret varargs
            mCol.log((Object[]) ids);
        }
    }

    public void sortCards(@NonNull List<Long> cids, int start) {
        if (!ListenerUtil.mutListener.listen(17069)) {
            sortCards(cids, start, 1, false, false);
        }
    }

    public void sortCards(@NonNull List<Long> cids, int start, int step, boolean shuffle, boolean shift) {
        String scids = Utils.ids2str(cids);
        long now = getTime().intTime();
        ArrayList<Long> nids = new ArrayList<>(cids.size());
        if (!ListenerUtil.mutListener.listen(17072)) {
            {
                long _loopCounter325 = 0;
                for (long id : cids) {
                    ListenerUtil.loopListener.listen("_loopCounter325", ++_loopCounter325);
                    long nid = mCol.getDb().queryLongScalar("SELECT nid FROM cards WHERE id = ?", id);
                    if (!ListenerUtil.mutListener.listen(17071)) {
                        if (!nids.contains(nid)) {
                            if (!ListenerUtil.mutListener.listen(17070)) {
                                nids.add(nid);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17073)) {
            if (nids.isEmpty()) {
                // no new cards
                return;
            }
        }
        // determine nid ordering
        HashMap<Long, Long> due = new HashMap<>(nids.size());
        if (!ListenerUtil.mutListener.listen(17075)) {
            if (shuffle) {
                if (!ListenerUtil.mutListener.listen(17074)) {
                    Collections.shuffle(nids);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17090)) {
            {
                long _loopCounter326 = 0;
                for (int c = 0; (ListenerUtil.mutListener.listen(17089) ? (c >= nids.size()) : (ListenerUtil.mutListener.listen(17088) ? (c <= nids.size()) : (ListenerUtil.mutListener.listen(17087) ? (c > nids.size()) : (ListenerUtil.mutListener.listen(17086) ? (c != nids.size()) : (ListenerUtil.mutListener.listen(17085) ? (c == nids.size()) : (c < nids.size())))))); c++) {
                    ListenerUtil.loopListener.listen("_loopCounter326", ++_loopCounter326);
                    if (!ListenerUtil.mutListener.listen(17084)) {
                        due.put(nids.get(c), (long) ((ListenerUtil.mutListener.listen(17083) ? (start % (ListenerUtil.mutListener.listen(17079) ? (c % step) : (ListenerUtil.mutListener.listen(17078) ? (c / step) : (ListenerUtil.mutListener.listen(17077) ? (c - step) : (ListenerUtil.mutListener.listen(17076) ? (c + step) : (c * step)))))) : (ListenerUtil.mutListener.listen(17082) ? (start / (ListenerUtil.mutListener.listen(17079) ? (c % step) : (ListenerUtil.mutListener.listen(17078) ? (c / step) : (ListenerUtil.mutListener.listen(17077) ? (c - step) : (ListenerUtil.mutListener.listen(17076) ? (c + step) : (c * step)))))) : (ListenerUtil.mutListener.listen(17081) ? (start * (ListenerUtil.mutListener.listen(17079) ? (c % step) : (ListenerUtil.mutListener.listen(17078) ? (c / step) : (ListenerUtil.mutListener.listen(17077) ? (c - step) : (ListenerUtil.mutListener.listen(17076) ? (c + step) : (c * step)))))) : (ListenerUtil.mutListener.listen(17080) ? (start - (ListenerUtil.mutListener.listen(17079) ? (c % step) : (ListenerUtil.mutListener.listen(17078) ? (c / step) : (ListenerUtil.mutListener.listen(17077) ? (c - step) : (ListenerUtil.mutListener.listen(17076) ? (c + step) : (c * step)))))) : (start + (ListenerUtil.mutListener.listen(17079) ? (c % step) : (ListenerUtil.mutListener.listen(17078) ? (c / step) : (ListenerUtil.mutListener.listen(17077) ? (c - step) : (ListenerUtil.mutListener.listen(17076) ? (c + step) : (c * step))))))))))));
                    }
                }
            }
        }
        int high = (ListenerUtil.mutListener.listen(17102) ? (start % (ListenerUtil.mutListener.listen(17098) ? (step % ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17097) ? (step / ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17096) ? (step - ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17095) ? (step + ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (step * ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1)))))))))))) : (ListenerUtil.mutListener.listen(17101) ? (start / (ListenerUtil.mutListener.listen(17098) ? (step % ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17097) ? (step / ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17096) ? (step - ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17095) ? (step + ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (step * ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1)))))))))))) : (ListenerUtil.mutListener.listen(17100) ? (start * (ListenerUtil.mutListener.listen(17098) ? (step % ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17097) ? (step / ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17096) ? (step - ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17095) ? (step + ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (step * ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1)))))))))))) : (ListenerUtil.mutListener.listen(17099) ? (start - (ListenerUtil.mutListener.listen(17098) ? (step % ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17097) ? (step / ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17096) ? (step - ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17095) ? (step + ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (step * ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1)))))))))))) : (start + (ListenerUtil.mutListener.listen(17098) ? (step % ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17097) ? (step / ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17096) ? (step - ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (ListenerUtil.mutListener.listen(17095) ? (step + ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))) : (step * ((ListenerUtil.mutListener.listen(17094) ? (nids.size() % 1) : (ListenerUtil.mutListener.listen(17093) ? (nids.size() / 1) : (ListenerUtil.mutListener.listen(17092) ? (nids.size() * 1) : (ListenerUtil.mutListener.listen(17091) ? (nids.size() + 1) : (nids.size() - 1))))))))))))))));
        if (!ListenerUtil.mutListener.listen(17118)) {
            // shift?
            if (shift) {
                int low = mCol.getDb().queryScalar("SELECT min(due) FROM cards WHERE due >= ? AND type = " + Consts.CARD_TYPE_NEW + " AND id NOT IN " + scids, start);
                if (!ListenerUtil.mutListener.listen(17117)) {
                    if ((ListenerUtil.mutListener.listen(17107) ? (low >= 0) : (ListenerUtil.mutListener.listen(17106) ? (low <= 0) : (ListenerUtil.mutListener.listen(17105) ? (low > 0) : (ListenerUtil.mutListener.listen(17104) ? (low < 0) : (ListenerUtil.mutListener.listen(17103) ? (low == 0) : (low != 0))))))) {
                        int shiftby = (ListenerUtil.mutListener.listen(17115) ? ((ListenerUtil.mutListener.listen(17111) ? (high % low) : (ListenerUtil.mutListener.listen(17110) ? (high / low) : (ListenerUtil.mutListener.listen(17109) ? (high * low) : (ListenerUtil.mutListener.listen(17108) ? (high + low) : (high - low))))) % 1) : (ListenerUtil.mutListener.listen(17114) ? ((ListenerUtil.mutListener.listen(17111) ? (high % low) : (ListenerUtil.mutListener.listen(17110) ? (high / low) : (ListenerUtil.mutListener.listen(17109) ? (high * low) : (ListenerUtil.mutListener.listen(17108) ? (high + low) : (high - low))))) / 1) : (ListenerUtil.mutListener.listen(17113) ? ((ListenerUtil.mutListener.listen(17111) ? (high % low) : (ListenerUtil.mutListener.listen(17110) ? (high / low) : (ListenerUtil.mutListener.listen(17109) ? (high * low) : (ListenerUtil.mutListener.listen(17108) ? (high + low) : (high - low))))) * 1) : (ListenerUtil.mutListener.listen(17112) ? ((ListenerUtil.mutListener.listen(17111) ? (high % low) : (ListenerUtil.mutListener.listen(17110) ? (high / low) : (ListenerUtil.mutListener.listen(17109) ? (high * low) : (ListenerUtil.mutListener.listen(17108) ? (high + low) : (high - low))))) - 1) : ((ListenerUtil.mutListener.listen(17111) ? (high % low) : (ListenerUtil.mutListener.listen(17110) ? (high / low) : (ListenerUtil.mutListener.listen(17109) ? (high * low) : (ListenerUtil.mutListener.listen(17108) ? (high + low) : (high - low))))) + 1)))));
                        if (!ListenerUtil.mutListener.listen(17116)) {
                            mCol.getDb().execute("UPDATE cards SET mod = ?, usn = ?, due = due + ?" + " WHERE id NOT IN " + scids + " AND due >= ? AND queue = " + Consts.QUEUE_TYPE_NEW, now, mCol.usn(), shiftby, low);
                        }
                    }
                }
            }
        }
        // reorder cards
        ArrayList<Object[]> d = new ArrayList<>(cids.size());
        try (Cursor cur = mCol.getDb().query("SELECT id, nid FROM cards WHERE type = " + Consts.CARD_TYPE_NEW + " AND id IN " + scids)) {
            if (!ListenerUtil.mutListener.listen(17120)) {
                {
                    long _loopCounter327 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter327", ++_loopCounter327);
                        long nid = cur.getLong(1);
                        if (!ListenerUtil.mutListener.listen(17119)) {
                            d.add(new Object[] { due.get(nid), now, mCol.usn(), cur.getLong(0) });
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17121)) {
            mCol.getDb().executeMany("UPDATE cards SET due = ?, mod = ?, usn = ? WHERE id = ?", d);
        }
    }

    public void randomizeCards(long did) {
        List<Long> cids = mCol.getDb().queryLongList("select id from cards where did = ?", did);
        if (!ListenerUtil.mutListener.listen(17122)) {
            sortCards(cids, 1, 1, true, false);
        }
    }

    public void orderCards(long did) {
        List<Long> cids = mCol.getDb().queryLongList("SELECT id FROM cards WHERE did = ? ORDER BY nid", did);
        if (!ListenerUtil.mutListener.listen(17123)) {
            sortCards(cids, 1, 1, false, false);
        }
    }

    public void resortConf(@NonNull DeckConfig conf) {
        List<Long> dids = mCol.getDecks().didsForConf(conf);
        if (!ListenerUtil.mutListener.listen(17127)) {
            {
                long _loopCounter328 = 0;
                for (long did : dids) {
                    ListenerUtil.loopListener.listen("_loopCounter328", ++_loopCounter328);
                    if (!ListenerUtil.mutListener.listen(17126)) {
                        if (conf.getJSONObject("new").getLong("order") == 0) {
                            if (!ListenerUtil.mutListener.listen(17125)) {
                                randomizeCards(did);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(17124)) {
                                orderCards(did);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * for post-import
     */
    public void maybeRandomizeDeck() {
        if (!ListenerUtil.mutListener.listen(17128)) {
            maybeRandomizeDeck(null);
        }
    }

    public void maybeRandomizeDeck(@Nullable Long did) {
        if (!ListenerUtil.mutListener.listen(17130)) {
            if (did == null) {
                if (!ListenerUtil.mutListener.listen(17129)) {
                    did = mCol.getDecks().selected();
                }
            }
        }
        DeckConfig conf = mCol.getDecks().confForDid(did);
        if (!ListenerUtil.mutListener.listen(17132)) {
            // in order due?
            if (conf.getJSONObject("new").getInt("order") == Consts.NEW_CARDS_RANDOM) {
                if (!ListenerUtil.mutListener.listen(17131)) {
                    randomizeCards(did);
                }
            }
        }
    }

    private void _emptyAllFiltered() {
        if (!ListenerUtil.mutListener.listen(17133)) {
            mCol.getDb().execute("update cards set did = odid, queue = (case when type = " + Consts.CARD_TYPE_LRN + " then " + Consts.QUEUE_TYPE_NEW + " when type = " + Consts.CARD_TYPE_RELEARNING + " then " + Consts.QUEUE_TYPE_REV + " else type end), type = (case when type = " + Consts.CARD_TYPE_LRN + " then " + Consts.CARD_TYPE_NEW + " when type = " + Consts.CARD_TYPE_RELEARNING + " then " + Consts.CARD_TYPE_REV + " else type end), due = odue, odue = 0, odid = 0, usn = ? where odid != 0", mCol.usn());
        }
    }

    private void _removeAllFromLearning() {
        if (!ListenerUtil.mutListener.listen(17134)) {
            _removeAllFromLearning(2);
        }
    }

    private void _removeAllFromLearning(int schedVer) {
        if (!ListenerUtil.mutListener.listen(17142)) {
            // remove review cards from relearning
            if ((ListenerUtil.mutListener.listen(17139) ? (schedVer >= 1) : (ListenerUtil.mutListener.listen(17138) ? (schedVer <= 1) : (ListenerUtil.mutListener.listen(17137) ? (schedVer > 1) : (ListenerUtil.mutListener.listen(17136) ? (schedVer < 1) : (ListenerUtil.mutListener.listen(17135) ? (schedVer != 1) : (schedVer == 1))))))) {
                if (!ListenerUtil.mutListener.listen(17141)) {
                    mCol.getDb().execute("update cards set due = odue, queue = " + Consts.QUEUE_TYPE_REV + ", type = " + Consts.CARD_TYPE_REV + ", mod = ?, usn = ?, odue = 0 where queue in (" + Consts.QUEUE_TYPE_LRN + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ") and type in (" + Consts.CARD_TYPE_REV + "," + Consts.CARD_TYPE_RELEARNING + ")", getTime().intTime(), mCol.usn());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17140)) {
                    mCol.getDb().execute("update cards set due = ?+ivl, queue = " + Consts.QUEUE_TYPE_REV + ", type = " + Consts.CARD_TYPE_REV + ", mod = ?, usn = ?, odue = 0 where queue in (" + Consts.QUEUE_TYPE_LRN + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ") and type in (" + Consts.CARD_TYPE_REV + "," + Consts.CARD_TYPE_RELEARNING + ")", mToday, getTime().intTime(), mCol.usn());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17143)) {
            // remove new cards from learning
            forgetCards(mCol.getDb().queryLongList("select id from cards where queue in (" + Consts.QUEUE_TYPE_LRN + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ")"));
        }
    }

    // v1 doesn't support buried/suspended (re)learning cards
    private void _resetSuspendedLearning() {
        if (!ListenerUtil.mutListener.listen(17144)) {
            mCol.getDb().execute("update cards set type = (case when type = " + Consts.CARD_TYPE_LRN + " then " + Consts.CARD_TYPE_NEW + " when type in (" + Consts.CARD_TYPE_REV + ", " + Consts.CARD_TYPE_RELEARNING + ") then " + Consts.CARD_TYPE_REV + " else type end), due = (case when odue then odue else due end), odue = 0, mod = ?, usn = ? where queue < 0", getTime().intTime(), mCol.usn());
        }
    }

    // no 'manually buried' queue in v1
    private void _moveManuallyBuried() {
        if (!ListenerUtil.mutListener.listen(17145)) {
            mCol.getDb().execute("update cards set queue=" + Consts.QUEUE_TYPE_SIBLING_BURIED + ", mod=? where queue=" + Consts.QUEUE_TYPE_MANUALLY_BURIED, getTime().intTime());
        }
    }

    // up or down
    private void _remapLearningAnswers(@NonNull String sql) {
        if (!ListenerUtil.mutListener.listen(17146)) {
            mCol.getDb().execute("update revlog set " + sql + " and type in (" + Consts.REVLOG_LRN + ", " + Consts.REVLOG_RELRN + ")");
        }
    }

    public void moveToV1() {
        if (!ListenerUtil.mutListener.listen(17147)) {
            _emptyAllFiltered();
        }
        if (!ListenerUtil.mutListener.listen(17148)) {
            _removeAllFromLearning();
        }
        if (!ListenerUtil.mutListener.listen(17149)) {
            _moveManuallyBuried();
        }
        if (!ListenerUtil.mutListener.listen(17150)) {
            _resetSuspendedLearning();
        }
        if (!ListenerUtil.mutListener.listen(17151)) {
            _remapLearningAnswers("ease=ease-1 where ease in (" + Consts.BUTTON_THREE + "," + Consts.BUTTON_FOUR + ")");
        }
    }

    public void moveToV2() {
        if (!ListenerUtil.mutListener.listen(17152)) {
            _emptyAllFiltered();
        }
        if (!ListenerUtil.mutListener.listen(17153)) {
            _removeAllFromLearning(1);
        }
        if (!ListenerUtil.mutListener.listen(17154)) {
            _remapLearningAnswers("ease=ease+1 where ease in (" + Consts.BUTTON_TWO + "," + Consts.BUTTON_THREE + ")");
        }
    }

    // Overriden: In sched v1, a single type of burying exist
    public boolean haveBuried(long did) {
        List<Long> all = new ArrayList<>(mCol.getDecks().children(did).values());
        if (!ListenerUtil.mutListener.listen(17155)) {
            all.add(did);
        }
        return (ListenerUtil.mutListener.listen(17156) ? (haveBuriedSiblings(all) && haveManuallyBuried(all)) : (haveBuriedSiblings(all) || haveManuallyBuried(all)));
    }

    public void unburyCardsForDeck(long did) {
        List<Long> all = new ArrayList<>(mCol.getDecks().children(did).values());
        if (!ListenerUtil.mutListener.listen(17157)) {
            all.add(did);
        }
        if (!ListenerUtil.mutListener.listen(17158)) {
            unburyCardsForDeck(ALL, all);
        }
    }

    @NonNull
    public String getName() {
        return "std2";
    }

    public int getToday() {
        return mToday;
    }

    public void setToday(int today) {
        if (!ListenerUtil.mutListener.listen(17159)) {
            mToday = today;
        }
    }

    public long getDayCutoff() {
        return mDayCutoff;
    }

    public int getReps() {
        return mReps;
    }

    protected void incrReps() {
        if (!ListenerUtil.mutListener.listen(17160)) {
            mReps++;
        }
    }

    protected void decrReps() {
        if (!ListenerUtil.mutListener.listen(17161)) {
            mReps--;
        }
    }

    public int cardCount() {
        String dids = _deckLimit();
        return mCol.getDb().queryScalar("SELECT count() FROM cards WHERE did IN " + dids);
    }

    public int eta(Counts counts) {
        return eta(counts, true);
    }

    // Overridden because of the different queues in SchedV1 and V2
    public int eta(Counts counts, boolean reload) {
        double newRate;
        double newTime;
        double revRate;
        double revTime;
        double relrnRate;
        double relrnTime;
        if ((ListenerUtil.mutListener.listen(17167) ? (reload && (ListenerUtil.mutListener.listen(17166) ? (mEtaCache[0] >= -1) : (ListenerUtil.mutListener.listen(17165) ? (mEtaCache[0] <= -1) : (ListenerUtil.mutListener.listen(17164) ? (mEtaCache[0] > -1) : (ListenerUtil.mutListener.listen(17163) ? (mEtaCache[0] < -1) : (ListenerUtil.mutListener.listen(17162) ? (mEtaCache[0] != -1) : (mEtaCache[0] == -1))))))) : (reload || (ListenerUtil.mutListener.listen(17166) ? (mEtaCache[0] >= -1) : (ListenerUtil.mutListener.listen(17165) ? (mEtaCache[0] <= -1) : (ListenerUtil.mutListener.listen(17164) ? (mEtaCache[0] > -1) : (ListenerUtil.mutListener.listen(17163) ? (mEtaCache[0] < -1) : (ListenerUtil.mutListener.listen(17162) ? (mEtaCache[0] != -1) : (mEtaCache[0] == -1))))))))) {
            try (Cursor cur = mCol.getDb().query("select " + "avg(case when type = " + Consts.CARD_TYPE_NEW + " then case when ease > 1 then 1.0 else 0.0 end else null end) as newRate, avg(case when type = " + Consts.CARD_TYPE_NEW + " then time else null end) as newTime, " + "avg(case when type in (" + Consts.CARD_TYPE_LRN + ", " + Consts.CARD_TYPE_RELEARNING + ") then case when ease > 1 then 1.0 else 0.0 end else null end) as revRate, avg(case when type in (" + Consts.CARD_TYPE_LRN + ", " + Consts.CARD_TYPE_RELEARNING + ") then time else null end) as revTime, " + "avg(case when type = " + Consts.CARD_TYPE_REV + " then case when ease > 1 then 1.0 else 0.0 end else null end) as relrnRate, avg(case when type = " + Consts.CARD_TYPE_REV + " then time else null end) as relrnTime " + "from revlog where id > " + "?", (ListenerUtil.mutListener.listen(17179) ? (((ListenerUtil.mutListener.listen(17175) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17174) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17173) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17172) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) % 1000) : (ListenerUtil.mutListener.listen(17178) ? (((ListenerUtil.mutListener.listen(17175) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17174) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17173) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17172) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) / 1000) : (ListenerUtil.mutListener.listen(17177) ? (((ListenerUtil.mutListener.listen(17175) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17174) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17173) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17172) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) - 1000) : (ListenerUtil.mutListener.listen(17176) ? (((ListenerUtil.mutListener.listen(17175) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17174) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17173) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17172) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) + 1000) : (((ListenerUtil.mutListener.listen(17175) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17174) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17173) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(17172) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(17171) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17170) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17169) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(17168) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) * 1000))))))) {
                if (!ListenerUtil.mutListener.listen(17180)) {
                    if (!cur.moveToFirst()) {
                        return -1;
                    }
                }
                newRate = cur.getDouble(0);
                newTime = cur.getDouble(1);
                revRate = cur.getDouble(2);
                revTime = cur.getDouble(3);
                relrnRate = cur.getDouble(4);
                relrnTime = cur.getDouble(5);
                if (!ListenerUtil.mutListener.listen(17182)) {
                    if (!cur.isClosed()) {
                        if (!ListenerUtil.mutListener.listen(17181)) {
                            cur.close();
                        }
                    }
                }
            }
            // If the collection has no revlog data to work with, assume a 20 second average rep for that type
            newTime = (ListenerUtil.mutListener.listen(17187) ? (newTime >= 0) : (ListenerUtil.mutListener.listen(17186) ? (newTime <= 0) : (ListenerUtil.mutListener.listen(17185) ? (newTime > 0) : (ListenerUtil.mutListener.listen(17184) ? (newTime < 0) : (ListenerUtil.mutListener.listen(17183) ? (newTime != 0) : (newTime == 0)))))) ? 20000 : newTime;
            revTime = (ListenerUtil.mutListener.listen(17192) ? (revTime >= 0) : (ListenerUtil.mutListener.listen(17191) ? (revTime <= 0) : (ListenerUtil.mutListener.listen(17190) ? (revTime > 0) : (ListenerUtil.mutListener.listen(17189) ? (revTime < 0) : (ListenerUtil.mutListener.listen(17188) ? (revTime != 0) : (revTime == 0)))))) ? 20000 : revTime;
            relrnTime = (ListenerUtil.mutListener.listen(17197) ? (relrnTime >= 0) : (ListenerUtil.mutListener.listen(17196) ? (relrnTime <= 0) : (ListenerUtil.mutListener.listen(17195) ? (relrnTime > 0) : (ListenerUtil.mutListener.listen(17194) ? (relrnTime < 0) : (ListenerUtil.mutListener.listen(17193) ? (relrnTime != 0) : (relrnTime == 0)))))) ? 20000 : relrnTime;
            // And a 100% success rate
            newRate = (ListenerUtil.mutListener.listen(17202) ? (newRate >= 0) : (ListenerUtil.mutListener.listen(17201) ? (newRate <= 0) : (ListenerUtil.mutListener.listen(17200) ? (newRate > 0) : (ListenerUtil.mutListener.listen(17199) ? (newRate < 0) : (ListenerUtil.mutListener.listen(17198) ? (newRate != 0) : (newRate == 0)))))) ? 1 : newRate;
            revRate = (ListenerUtil.mutListener.listen(17207) ? (revRate >= 0) : (ListenerUtil.mutListener.listen(17206) ? (revRate <= 0) : (ListenerUtil.mutListener.listen(17205) ? (revRate > 0) : (ListenerUtil.mutListener.listen(17204) ? (revRate < 0) : (ListenerUtil.mutListener.listen(17203) ? (revRate != 0) : (revRate == 0)))))) ? 1 : revRate;
            relrnRate = (ListenerUtil.mutListener.listen(17212) ? (relrnRate >= 0) : (ListenerUtil.mutListener.listen(17211) ? (relrnRate <= 0) : (ListenerUtil.mutListener.listen(17210) ? (relrnRate > 0) : (ListenerUtil.mutListener.listen(17209) ? (relrnRate < 0) : (ListenerUtil.mutListener.listen(17208) ? (relrnRate != 0) : (relrnRate == 0)))))) ? 1 : relrnRate;
            if (!ListenerUtil.mutListener.listen(17213)) {
                mEtaCache[0] = newRate;
            }
            if (!ListenerUtil.mutListener.listen(17214)) {
                mEtaCache[1] = newTime;
            }
            if (!ListenerUtil.mutListener.listen(17215)) {
                mEtaCache[2] = revRate;
            }
            if (!ListenerUtil.mutListener.listen(17216)) {
                mEtaCache[3] = revTime;
            }
            if (!ListenerUtil.mutListener.listen(17217)) {
                mEtaCache[4] = relrnRate;
            }
            if (!ListenerUtil.mutListener.listen(17218)) {
                mEtaCache[5] = relrnTime;
            }
        } else {
            newRate = mEtaCache[0];
            newTime = mEtaCache[1];
            revRate = mEtaCache[2];
            revTime = mEtaCache[3];
            relrnRate = mEtaCache[4];
            relrnTime = mEtaCache[5];
        }
        // Calculate the total time for each queue based on the historical average duration per rep
        double newTotal = (ListenerUtil.mutListener.listen(17222) ? (newTime % counts.getNew()) : (ListenerUtil.mutListener.listen(17221) ? (newTime / counts.getNew()) : (ListenerUtil.mutListener.listen(17220) ? (newTime - counts.getNew()) : (ListenerUtil.mutListener.listen(17219) ? (newTime + counts.getNew()) : (newTime * counts.getNew())))));
        double relrnTotal = (ListenerUtil.mutListener.listen(17226) ? (relrnTime % counts.getLrn()) : (ListenerUtil.mutListener.listen(17225) ? (relrnTime / counts.getLrn()) : (ListenerUtil.mutListener.listen(17224) ? (relrnTime - counts.getLrn()) : (ListenerUtil.mutListener.listen(17223) ? (relrnTime + counts.getLrn()) : (relrnTime * counts.getLrn())))));
        double revTotal = (ListenerUtil.mutListener.listen(17230) ? (revTime % counts.getRev()) : (ListenerUtil.mutListener.listen(17229) ? (revTime / counts.getRev()) : (ListenerUtil.mutListener.listen(17228) ? (revTime - counts.getRev()) : (ListenerUtil.mutListener.listen(17227) ? (revTime + counts.getRev()) : (revTime * counts.getRev())))));
        // Assume every new card becomes 1 relrn
        int toRelrn = counts.getNew();
        if (!ListenerUtil.mutListener.listen(17239)) {
            toRelrn += Math.ceil((ListenerUtil.mutListener.listen(17238) ? (((ListenerUtil.mutListener.listen(17234) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17233) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17232) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17231) ? (1 + relrnRate) : (1 - relrnRate)))))) % counts.getLrn()) : (ListenerUtil.mutListener.listen(17237) ? (((ListenerUtil.mutListener.listen(17234) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17233) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17232) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17231) ? (1 + relrnRate) : (1 - relrnRate)))))) / counts.getLrn()) : (ListenerUtil.mutListener.listen(17236) ? (((ListenerUtil.mutListener.listen(17234) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17233) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17232) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17231) ? (1 + relrnRate) : (1 - relrnRate)))))) - counts.getLrn()) : (ListenerUtil.mutListener.listen(17235) ? (((ListenerUtil.mutListener.listen(17234) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17233) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17232) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17231) ? (1 + relrnRate) : (1 - relrnRate)))))) + counts.getLrn()) : (((ListenerUtil.mutListener.listen(17234) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17233) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17232) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17231) ? (1 + relrnRate) : (1 - relrnRate)))))) * counts.getLrn()))))));
        }
        if (!ListenerUtil.mutListener.listen(17248)) {
            toRelrn += Math.ceil((ListenerUtil.mutListener.listen(17247) ? (((ListenerUtil.mutListener.listen(17243) ? (1 % revRate) : (ListenerUtil.mutListener.listen(17242) ? (1 / revRate) : (ListenerUtil.mutListener.listen(17241) ? (1 * revRate) : (ListenerUtil.mutListener.listen(17240) ? (1 + revRate) : (1 - revRate)))))) % counts.getRev()) : (ListenerUtil.mutListener.listen(17246) ? (((ListenerUtil.mutListener.listen(17243) ? (1 % revRate) : (ListenerUtil.mutListener.listen(17242) ? (1 / revRate) : (ListenerUtil.mutListener.listen(17241) ? (1 * revRate) : (ListenerUtil.mutListener.listen(17240) ? (1 + revRate) : (1 - revRate)))))) / counts.getRev()) : (ListenerUtil.mutListener.listen(17245) ? (((ListenerUtil.mutListener.listen(17243) ? (1 % revRate) : (ListenerUtil.mutListener.listen(17242) ? (1 / revRate) : (ListenerUtil.mutListener.listen(17241) ? (1 * revRate) : (ListenerUtil.mutListener.listen(17240) ? (1 + revRate) : (1 - revRate)))))) - counts.getRev()) : (ListenerUtil.mutListener.listen(17244) ? (((ListenerUtil.mutListener.listen(17243) ? (1 % revRate) : (ListenerUtil.mutListener.listen(17242) ? (1 / revRate) : (ListenerUtil.mutListener.listen(17241) ? (1 * revRate) : (ListenerUtil.mutListener.listen(17240) ? (1 + revRate) : (1 - revRate)))))) + counts.getRev()) : (((ListenerUtil.mutListener.listen(17243) ? (1 % revRate) : (ListenerUtil.mutListener.listen(17242) ? (1 / revRate) : (ListenerUtil.mutListener.listen(17241) ? (1 * revRate) : (ListenerUtil.mutListener.listen(17240) ? (1 + revRate) : (1 - revRate)))))) * counts.getRev()))))));
        }
        // negative for other reasons). 5% seems reasonable to ensure the loop doesn't iterate too much.
        relrnRate = Math.max(relrnRate, 0.05);
        int futureReps = 0;
        if (!ListenerUtil.mutListener.listen(17264)) {
            {
                long _loopCounter329 = 0;
                do {
                    ListenerUtil.loopListener.listen("_loopCounter329", ++_loopCounter329);
                    // Truncation ensures the failure rate always decreases
                    int failures = (int) ((ListenerUtil.mutListener.listen(17256) ? (((ListenerUtil.mutListener.listen(17252) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17251) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17250) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17249) ? (1 + relrnRate) : (1 - relrnRate)))))) % toRelrn) : (ListenerUtil.mutListener.listen(17255) ? (((ListenerUtil.mutListener.listen(17252) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17251) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17250) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17249) ? (1 + relrnRate) : (1 - relrnRate)))))) / toRelrn) : (ListenerUtil.mutListener.listen(17254) ? (((ListenerUtil.mutListener.listen(17252) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17251) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17250) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17249) ? (1 + relrnRate) : (1 - relrnRate)))))) - toRelrn) : (ListenerUtil.mutListener.listen(17253) ? (((ListenerUtil.mutListener.listen(17252) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17251) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17250) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17249) ? (1 + relrnRate) : (1 - relrnRate)))))) + toRelrn) : (((ListenerUtil.mutListener.listen(17252) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(17251) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(17250) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(17249) ? (1 + relrnRate) : (1 - relrnRate)))))) * toRelrn))))));
                    if (!ListenerUtil.mutListener.listen(17257)) {
                        futureReps += failures;
                    }
                    if (!ListenerUtil.mutListener.listen(17258)) {
                        toRelrn = failures;
                    }
                } while ((ListenerUtil.mutListener.listen(17263) ? (toRelrn >= 1) : (ListenerUtil.mutListener.listen(17262) ? (toRelrn <= 1) : (ListenerUtil.mutListener.listen(17261) ? (toRelrn < 1) : (ListenerUtil.mutListener.listen(17260) ? (toRelrn != 1) : (ListenerUtil.mutListener.listen(17259) ? (toRelrn == 1) : (toRelrn > 1)))))));
            }
        }
        double futureRelrnTotal = (ListenerUtil.mutListener.listen(17268) ? (relrnTime % futureReps) : (ListenerUtil.mutListener.listen(17267) ? (relrnTime / futureReps) : (ListenerUtil.mutListener.listen(17266) ? (relrnTime - futureReps) : (ListenerUtil.mutListener.listen(17265) ? (relrnTime + futureReps) : (relrnTime * futureReps)))));
        return (int) Math.round((ListenerUtil.mutListener.listen(17284) ? (((ListenerUtil.mutListener.listen(17280) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(17279) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(17278) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(17277) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) % 60000) : (ListenerUtil.mutListener.listen(17283) ? (((ListenerUtil.mutListener.listen(17280) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(17279) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(17278) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(17277) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) * 60000) : (ListenerUtil.mutListener.listen(17282) ? (((ListenerUtil.mutListener.listen(17280) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(17279) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(17278) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(17277) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) - 60000) : (ListenerUtil.mutListener.listen(17281) ? (((ListenerUtil.mutListener.listen(17280) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(17279) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(17278) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(17277) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) + 60000) : (((ListenerUtil.mutListener.listen(17280) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(17279) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(17278) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(17277) ? ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(17276) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(17275) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(17274) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(17273) ? ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(17272) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(17271) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(17270) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(17269) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) / 60000))))));
    }

    /**
     * Change the counts to reflect that `card` should not be counted anymore. In practice, it means that the card has
     * been sent to the reviewer. Either through `getCard()` or through `undo`. Assumes that card's queue has not yet
     * changed.
     * Overridden
     */
    public void decrementCounts(@Nullable Card discardCard) {
        if (!ListenerUtil.mutListener.listen(17285)) {
            if (discardCard == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17289)) {
            switch(discardCard.getQueue()) {
                case Consts.QUEUE_TYPE_NEW:
                    if (!ListenerUtil.mutListener.listen(17286)) {
                        mNewCount--;
                    }
                    break;
                case Consts.QUEUE_TYPE_LRN:
                case Consts.QUEUE_TYPE_DAY_LEARN_RELEARN:
                case Consts.QUEUE_TYPE_PREVIEW:
                    if (!ListenerUtil.mutListener.listen(17287)) {
                        mLrnCount--;
                    }
                    // In the case of QUEUE_TYPE_LRN, it is -= discardCard.getLeft() / 1000; in sched v1
                    break;
                case Consts.QUEUE_TYPE_REV:
                    if (!ListenerUtil.mutListener.listen(17288)) {
                        mRevCount--;
                    }
                    break;
            }
        }
    }

    /**
     * Sorts a card into the lrn queue LIBANKI: not in libanki
     */
    protected void _sortIntoLrn(long due, long id) {
        if (!ListenerUtil.mutListener.listen(17290)) {
            if (!mLrnQueue.isFilled()) {
                // Adding anything is useless while the queue awaits beeing filled
                return;
            }
        }
        ListIterator<LrnCard> i = mLrnQueue.listIterator();
        if (!ListenerUtil.mutListener.listen(17298)) {
            {
                long _loopCounter330 = 0;
                while (i.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter330", ++_loopCounter330);
                    if (!ListenerUtil.mutListener.listen(17297)) {
                        if ((ListenerUtil.mutListener.listen(17295) ? (i.next().getDue() >= due) : (ListenerUtil.mutListener.listen(17294) ? (i.next().getDue() <= due) : (ListenerUtil.mutListener.listen(17293) ? (i.next().getDue() < due) : (ListenerUtil.mutListener.listen(17292) ? (i.next().getDue() != due) : (ListenerUtil.mutListener.listen(17291) ? (i.next().getDue() == due) : (i.next().getDue() > due))))))) {
                            if (!ListenerUtil.mutListener.listen(17296)) {
                                i.previous();
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17299)) {
            i.add(new LrnCard(mCol, due, id));
        }
    }

    public boolean leechActionSuspend(@NonNull Card card) {
        JSONObject conf = _cardConf(card).getJSONObject("lapse");
        return (ListenerUtil.mutListener.listen(17304) ? (conf.getInt("leechAction") >= Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(17303) ? (conf.getInt("leechAction") <= Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(17302) ? (conf.getInt("leechAction") > Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(17301) ? (conf.getInt("leechAction") < Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(17300) ? (conf.getInt("leechAction") != Consts.LEECH_SUSPEND) : (conf.getInt("leechAction") == Consts.LEECH_SUSPEND))))));
    }

    public void setContext(@Nullable WeakReference<Activity> contextReference) {
        if (!ListenerUtil.mutListener.listen(17305)) {
            mContextReference = contextReference;
        }
    }

    /**
     * not in libAnki. Added due to #5666: inconsistent selected deck card counts on sync
     */
    @Override
    public void setReportLimit(int reportLimit) {
        if (!ListenerUtil.mutListener.listen(17306)) {
            this.mReportLimit = reportLimit;
        }
    }

    @Override
    public void undoReview(@NonNull Card oldCardData, boolean wasLeech) {
        if (!ListenerUtil.mutListener.listen(17310)) {
            // remove leech tag if it didn't have it before
            if ((ListenerUtil.mutListener.listen(17307) ? (!wasLeech || oldCardData.note().hasTag("leech")) : (!wasLeech && oldCardData.note().hasTag("leech")))) {
                if (!ListenerUtil.mutListener.listen(17308)) {
                    oldCardData.note().delTag("leech");
                }
                if (!ListenerUtil.mutListener.listen(17309)) {
                    oldCardData.note().flush();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17311)) {
            Timber.i("Undo Review of card %d, leech: %b", oldCardData.getId(), wasLeech);
        }
        if (!ListenerUtil.mutListener.listen(17312)) {
            // write old data
            oldCardData.flush(false);
        }
        DeckConfig conf = _cardConf(oldCardData);
        boolean previewing = (ListenerUtil.mutListener.listen(17313) ? (conf.getInt("dyn") == DECK_DYN || !conf.getBoolean("resched")) : (conf.getInt("dyn") == DECK_DYN && !conf.getBoolean("resched")));
        if (!ListenerUtil.mutListener.listen(17315)) {
            if (!previewing) {
                // and delete revlog entry
                long last = mCol.getDb().queryLongScalar("SELECT id FROM revlog WHERE cid = ? ORDER BY id DESC LIMIT 1", oldCardData.getId());
                if (!ListenerUtil.mutListener.listen(17314)) {
                    mCol.getDb().execute("DELETE FROM revlog WHERE id = " + last);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17316)) {
            // restore any siblings
            mCol.getDb().execute("update cards set queue=type,mod=?,usn=? where queue=" + Consts.QUEUE_TYPE_SIBLING_BURIED + " and nid=?", getTime().intTime(), mCol.usn(), oldCardData.getNid());
        }
        // and finally, update daily count
        @Consts.CARD_QUEUE
        int n = ((ListenerUtil.mutListener.listen(17327) ? ((ListenerUtil.mutListener.listen(17321) ? (oldCardData.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17320) ? (oldCardData.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17319) ? (oldCardData.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17318) ? (oldCardData.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17317) ? (oldCardData.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (oldCardData.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN)))))) && (ListenerUtil.mutListener.listen(17326) ? (oldCardData.getQueue() >= Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17325) ? (oldCardData.getQueue() <= Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17324) ? (oldCardData.getQueue() > Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17323) ? (oldCardData.getQueue() < Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17322) ? (oldCardData.getQueue() != Consts.QUEUE_TYPE_PREVIEW) : (oldCardData.getQueue() == Consts.QUEUE_TYPE_PREVIEW))))))) : ((ListenerUtil.mutListener.listen(17321) ? (oldCardData.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17320) ? (oldCardData.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17319) ? (oldCardData.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17318) ? (oldCardData.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(17317) ? (oldCardData.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (oldCardData.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN)))))) || (ListenerUtil.mutListener.listen(17326) ? (oldCardData.getQueue() >= Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17325) ? (oldCardData.getQueue() <= Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17324) ? (oldCardData.getQueue() > Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17323) ? (oldCardData.getQueue() < Consts.QUEUE_TYPE_PREVIEW) : (ListenerUtil.mutListener.listen(17322) ? (oldCardData.getQueue() != Consts.QUEUE_TYPE_PREVIEW) : (oldCardData.getQueue() == Consts.QUEUE_TYPE_PREVIEW))))))))) ? Consts.QUEUE_TYPE_LRN : oldCardData.getQueue();
        String type = (new String[] { "new", "lrn", "rev" })[n];
        if (!ListenerUtil.mutListener.listen(17328)) {
            _updateStats(oldCardData, type, -1);
        }
        if (!ListenerUtil.mutListener.listen(17329)) {
            decrReps();
        }
    }

    @NonNull
    public Time getTime() {
        return mCol.getTime();
    }

    /**
     * End #5666
     */
    public void discardCurrentCard() {
        if (!ListenerUtil.mutListener.listen(17330)) {
            mCurrentCard = null;
        }
        if (!ListenerUtil.mutListener.listen(17331)) {
            mCurrentCardParentsDid = null;
        }
    }

    /**
     * This imitate the action of the method answerCard, except that it does not change the state of any card.
     *
     * It means in particular that: + it removes the siblings of card from all queues + change the next card if required
     * it also set variables, so that when querying the next card, the current card can be taken into account.
     */
    public void setCurrentCard(@NonNull Card card) {
        if (!ListenerUtil.mutListener.listen(17332)) {
            mCurrentCard = card;
        }
        long did = card.getDid();
        List<Deck> parents = mCol.getDecks().parents(did);
        List<Long> currentCardParentsDid = new ArrayList<>((ListenerUtil.mutListener.listen(17336) ? (parents.size() % 1) : (ListenerUtil.mutListener.listen(17335) ? (parents.size() / 1) : (ListenerUtil.mutListener.listen(17334) ? (parents.size() * 1) : (ListenerUtil.mutListener.listen(17333) ? (parents.size() - 1) : (parents.size() + 1))))));
        if (!ListenerUtil.mutListener.listen(17338)) {
            {
                long _loopCounter331 = 0;
                for (JSONObject parent : parents) {
                    ListenerUtil.loopListener.listen("_loopCounter331", ++_loopCounter331);
                    if (!ListenerUtil.mutListener.listen(17337)) {
                        currentCardParentsDid.add(parent.getLong("id"));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17339)) {
            currentCardParentsDid.add(did);
        }
        if (!ListenerUtil.mutListener.listen(17340)) {
            // were called during `setCurrentCard`.
            mCurrentCardParentsDid = currentCardParentsDid;
        }
        if (!ListenerUtil.mutListener.listen(17341)) {
            _burySiblings(card);
        }
        if (!ListenerUtil.mutListener.listen(17342)) {
            // if current card is next card or in the queue
            mRevQueue.remove(card.getId());
        }
        if (!ListenerUtil.mutListener.listen(17343)) {
            mNewQueue.remove(card.getId());
        }
    }

    protected boolean currentCardIsInQueueWithDeck(@Consts.CARD_QUEUE int queue, long did) {
        // mCurrentCard may be set to null when the reviewer gets closed. So we copy it to be sure to avoid NullPointerException
        Card currentCard = mCurrentCard;
        List<Long> currentCardParentsDid = mCurrentCardParentsDid;
        return (ListenerUtil.mutListener.listen(17351) ? ((ListenerUtil.mutListener.listen(17350) ? ((ListenerUtil.mutListener.listen(17349) ? (currentCard != null || (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue))))))) : (currentCard != null && (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue)))))))) || currentCardParentsDid != null) : ((ListenerUtil.mutListener.listen(17349) ? (currentCard != null || (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue))))))) : (currentCard != null && (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue)))))))) && currentCardParentsDid != null)) || currentCardParentsDid.contains(did)) : ((ListenerUtil.mutListener.listen(17350) ? ((ListenerUtil.mutListener.listen(17349) ? (currentCard != null || (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue))))))) : (currentCard != null && (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue)))))))) || currentCardParentsDid != null) : ((ListenerUtil.mutListener.listen(17349) ? (currentCard != null || (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue))))))) : (currentCard != null && (ListenerUtil.mutListener.listen(17348) ? (currentCard.getQueue() >= queue) : (ListenerUtil.mutListener.listen(17347) ? (currentCard.getQueue() <= queue) : (ListenerUtil.mutListener.listen(17346) ? (currentCard.getQueue() > queue) : (ListenerUtil.mutListener.listen(17345) ? (currentCard.getQueue() < queue) : (ListenerUtil.mutListener.listen(17344) ? (currentCard.getQueue() != queue) : (currentCard.getQueue() == queue)))))))) && currentCardParentsDid != null)) && currentCardParentsDid.contains(did)));
    }

    @NonNull
    public Collection getCol() {
        return mCol;
    }

    @Override
    @VisibleForTesting
    @Consts.BUTTON_TYPE
    public int getGoodNewButton() {
        return Consts.BUTTON_THREE;
    }
}
