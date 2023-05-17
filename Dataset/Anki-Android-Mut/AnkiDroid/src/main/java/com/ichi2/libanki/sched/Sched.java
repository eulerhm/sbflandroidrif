/**
 * *************************************************************************************
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
 *  Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2013 Houssam Salem <houssam.salem.au@gmail.com>                        *
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
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.ichi2.async.CancelListener;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.Deck;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.utils.Assert;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import com.ichi2.utils.SyncStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import static com.ichi2.async.CancelListener.isCancelled;
import static com.ichi2.libanki.Consts.DECK_DYN;
import static com.ichi2.libanki.Consts.DECK_STD;
import static com.ichi2.libanki.sched.Counts.Queue.*;
import static com.ichi2.libanki.sched.Counts.Queue;
import static com.ichi2.libanki.stats.Stats.SECONDS_PER_DAY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@SuppressWarnings({ "PMD.ExcessiveClassLength", "PMD.AvoidThrowingRawExceptionTypes", "PMD.AvoidReassigningParameters", "PMD.NPathComplexity", "PMD.MethodNamingConventions", "PMD.AvoidBranchingStatementAsLastInLoop", "PMD.SwitchStmtsShouldHaveDefault", "PMD.CollapsibleIfStatements", "PMD.EmptyIfStmt" })
public class Sched extends SchedV2 {

    // Not in libanki
    private static final int[] FACTOR_ADDITION_VALUES = { -150, 0, 150 };

    // Queues
    @NonNull
    private LinkedList<Long> mRevDids = new LinkedList<>();

    public Sched(@NonNull Collection col) {
        super(col);
    }

    @Override
    public void answerCard(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(14636)) {
            mCol.log();
        }
        if (!ListenerUtil.mutListener.listen(14637)) {
            mCol.markReview(card);
        }
        if (!ListenerUtil.mutListener.listen(14638)) {
            discardCurrentCard();
        }
        if (!ListenerUtil.mutListener.listen(14639)) {
            _burySiblings(card);
        }
        if (!ListenerUtil.mutListener.listen(14640)) {
            card.incrReps();
        }
        if (!ListenerUtil.mutListener.listen(14646)) {
            // former is for logging new cards, latter also covers filt. decks
            card.setWasNew(((ListenerUtil.mutListener.listen(14645) ? (card.getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14644) ? (card.getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14643) ? (card.getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14642) ? (card.getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14641) ? (card.getType() != Consts.CARD_TYPE_NEW) : (card.getType() == Consts.CARD_TYPE_NEW))))))));
        }
        boolean wasNewQ = ((ListenerUtil.mutListener.listen(14651) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(14650) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(14649) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(14648) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(14647) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))));
        if (!ListenerUtil.mutListener.listen(14676)) {
            if (wasNewQ) {
                if (!ListenerUtil.mutListener.listen(14652)) {
                    // came from the new queue, move to learning
                    card.setQueue(Consts.QUEUE_TYPE_LRN);
                }
                if (!ListenerUtil.mutListener.listen(14659)) {
                    // if it was a new card, it's now a learning card
                    if ((ListenerUtil.mutListener.listen(14657) ? (card.getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14656) ? (card.getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14655) ? (card.getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14654) ? (card.getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14653) ? (card.getType() != Consts.CARD_TYPE_NEW) : (card.getType() == Consts.CARD_TYPE_NEW))))))) {
                        if (!ListenerUtil.mutListener.listen(14658)) {
                            card.setType(Consts.CARD_TYPE_LRN);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14660)) {
                    // init reps to graduation
                    card.setLeft(_startingLeft(card));
                }
                if (!ListenerUtil.mutListener.listen(14674)) {
                    // dynamic?
                    if ((ListenerUtil.mutListener.listen(14666) ? (card.isInDynamicDeck() || (ListenerUtil.mutListener.listen(14665) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14664) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14663) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14662) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14661) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV))))))) : (card.isInDynamicDeck() && (ListenerUtil.mutListener.listen(14665) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14664) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14663) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14662) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14661) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV))))))))) {
                        if (!ListenerUtil.mutListener.listen(14673)) {
                            if (_resched(card)) {
                                if (!ListenerUtil.mutListener.listen(14667)) {
                                    // reviews get their ivl boosted on first sight
                                    card.setIvl(_dynIvlBoost(card));
                                }
                                if (!ListenerUtil.mutListener.listen(14672)) {
                                    card.setODue((ListenerUtil.mutListener.listen(14671) ? (mToday % card.getIvl()) : (ListenerUtil.mutListener.listen(14670) ? (mToday / card.getIvl()) : (ListenerUtil.mutListener.listen(14669) ? (mToday * card.getIvl()) : (ListenerUtil.mutListener.listen(14668) ? (mToday - card.getIvl()) : (mToday + card.getIvl()))))));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14675)) {
                    _updateStats(card, "new");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14698)) {
            if ((ListenerUtil.mutListener.listen(14687) ? ((ListenerUtil.mutListener.listen(14681) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14680) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14679) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14678) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14677) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))) && (ListenerUtil.mutListener.listen(14686) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14685) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14684) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14683) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14682) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(14681) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14680) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14679) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14678) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(14677) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))) || (ListenerUtil.mutListener.listen(14686) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14685) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14684) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14683) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(14682) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))))) {
                if (!ListenerUtil.mutListener.listen(14695)) {
                    _answerLrnCard(card, ease);
                }
                if (!ListenerUtil.mutListener.listen(14697)) {
                    if (!wasNewQ) {
                        if (!ListenerUtil.mutListener.listen(14696)) {
                            _updateStats(card, "lrn");
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(14692) ? (card.getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14691) ? (card.getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14690) ? (card.getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14689) ? (card.getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14688) ? (card.getQueue() != Consts.QUEUE_TYPE_REV) : (card.getQueue() == Consts.QUEUE_TYPE_REV))))))) {
                if (!ListenerUtil.mutListener.listen(14693)) {
                    _answerRevCard(card, ease);
                }
                if (!ListenerUtil.mutListener.listen(14694)) {
                    _updateStats(card, "rev");
                }
            } else {
                throw new RuntimeException("Invalid queue");
            }
        }
        if (!ListenerUtil.mutListener.listen(14699)) {
            _updateStats(card, "time", card.timeTaken());
        }
        if (!ListenerUtil.mutListener.listen(14700)) {
            card.setMod(getTime().intTime());
        }
        if (!ListenerUtil.mutListener.listen(14701)) {
            card.setUsn(mCol.usn());
        }
        if (!ListenerUtil.mutListener.listen(14702)) {
            card.flushSched();
        }
    }

    @Override
    @NonNull
    public Counts counts(@NonNull Card card) {
        Counts counts = counts();
        Counts.Queue idx = countIdx(card);
        if (!ListenerUtil.mutListener.listen(14709)) {
            if (idx == LRN) {
                if (!ListenerUtil.mutListener.listen(14708)) {
                    counts.addLrn((ListenerUtil.mutListener.listen(14707) ? (card.getLeft() % 1000) : (ListenerUtil.mutListener.listen(14706) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14705) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14704) ? (card.getLeft() + 1000) : (card.getLeft() / 1000))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14703)) {
                    counts.changeCount(idx, 1);
                }
            }
        }
        return counts;
    }

    @Override
    public Queue countIdx(@NonNull Card card) {
        switch(card.getQueue()) {
            case Consts.QUEUE_TYPE_DAY_LEARN_RELEARN:
            case Consts.QUEUE_TYPE_LRN:
                return LRN;
            case Consts.QUEUE_TYPE_NEW:
                return NEW;
            case Consts.QUEUE_TYPE_REV:
                return REV;
            default:
                throw new RuntimeException("Index " + card.getQueue() + " does not exists.");
        }
    }

    @Override
    public int answerButtons(@NonNull Card card) {
        if ((ListenerUtil.mutListener.listen(14714) ? (card.getODue() >= 0) : (ListenerUtil.mutListener.listen(14713) ? (card.getODue() <= 0) : (ListenerUtil.mutListener.listen(14712) ? (card.getODue() > 0) : (ListenerUtil.mutListener.listen(14711) ? (card.getODue() < 0) : (ListenerUtil.mutListener.listen(14710) ? (card.getODue() == 0) : (card.getODue() != 0))))))) {
            // normal review in dyn deck?
            if ((ListenerUtil.mutListener.listen(14725) ? (card.isInDynamicDeck() || (ListenerUtil.mutListener.listen(14724) ? (card.getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14723) ? (card.getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14722) ? (card.getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14721) ? (card.getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14720) ? (card.getQueue() != Consts.QUEUE_TYPE_REV) : (card.getQueue() == Consts.QUEUE_TYPE_REV))))))) : (card.isInDynamicDeck() && (ListenerUtil.mutListener.listen(14724) ? (card.getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14723) ? (card.getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14722) ? (card.getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14721) ? (card.getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14720) ? (card.getQueue() != Consts.QUEUE_TYPE_REV) : (card.getQueue() == Consts.QUEUE_TYPE_REV))))))))) {
                return 4;
            }
            JSONObject conf = _lrnConf(card);
            if ((ListenerUtil.mutListener.listen(14742) ? ((ListenerUtil.mutListener.listen(14736) ? ((ListenerUtil.mutListener.listen(14730) ? (card.getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14729) ? (card.getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14728) ? (card.getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14727) ? (card.getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14726) ? (card.getType() != Consts.CARD_TYPE_NEW) : (card.getType() == Consts.CARD_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(14735) ? (card.getType() >= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14734) ? (card.getType() <= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14733) ? (card.getType() > Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14732) ? (card.getType() < Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14731) ? (card.getType() != Consts.CARD_TYPE_LRN) : (card.getType() == Consts.CARD_TYPE_LRN))))))) : ((ListenerUtil.mutListener.listen(14730) ? (card.getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14729) ? (card.getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14728) ? (card.getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14727) ? (card.getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14726) ? (card.getType() != Consts.CARD_TYPE_NEW) : (card.getType() == Consts.CARD_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(14735) ? (card.getType() >= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14734) ? (card.getType() <= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14733) ? (card.getType() > Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14732) ? (card.getType() < Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14731) ? (card.getType() != Consts.CARD_TYPE_LRN) : (card.getType() == Consts.CARD_TYPE_LRN)))))))) && (ListenerUtil.mutListener.listen(14741) ? (conf.getJSONArray("delays").length() >= 1) : (ListenerUtil.mutListener.listen(14740) ? (conf.getJSONArray("delays").length() <= 1) : (ListenerUtil.mutListener.listen(14739) ? (conf.getJSONArray("delays").length() < 1) : (ListenerUtil.mutListener.listen(14738) ? (conf.getJSONArray("delays").length() != 1) : (ListenerUtil.mutListener.listen(14737) ? (conf.getJSONArray("delays").length() == 1) : (conf.getJSONArray("delays").length() > 1))))))) : ((ListenerUtil.mutListener.listen(14736) ? ((ListenerUtil.mutListener.listen(14730) ? (card.getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14729) ? (card.getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14728) ? (card.getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14727) ? (card.getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14726) ? (card.getType() != Consts.CARD_TYPE_NEW) : (card.getType() == Consts.CARD_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(14735) ? (card.getType() >= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14734) ? (card.getType() <= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14733) ? (card.getType() > Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14732) ? (card.getType() < Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14731) ? (card.getType() != Consts.CARD_TYPE_LRN) : (card.getType() == Consts.CARD_TYPE_LRN))))))) : ((ListenerUtil.mutListener.listen(14730) ? (card.getType() >= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14729) ? (card.getType() <= Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14728) ? (card.getType() > Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14727) ? (card.getType() < Consts.CARD_TYPE_NEW) : (ListenerUtil.mutListener.listen(14726) ? (card.getType() != Consts.CARD_TYPE_NEW) : (card.getType() == Consts.CARD_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(14735) ? (card.getType() >= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14734) ? (card.getType() <= Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14733) ? (card.getType() > Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14732) ? (card.getType() < Consts.CARD_TYPE_LRN) : (ListenerUtil.mutListener.listen(14731) ? (card.getType() != Consts.CARD_TYPE_LRN) : (card.getType() == Consts.CARD_TYPE_LRN)))))))) || (ListenerUtil.mutListener.listen(14741) ? (conf.getJSONArray("delays").length() >= 1) : (ListenerUtil.mutListener.listen(14740) ? (conf.getJSONArray("delays").length() <= 1) : (ListenerUtil.mutListener.listen(14739) ? (conf.getJSONArray("delays").length() < 1) : (ListenerUtil.mutListener.listen(14738) ? (conf.getJSONArray("delays").length() != 1) : (ListenerUtil.mutListener.listen(14737) ? (conf.getJSONArray("delays").length() == 1) : (conf.getJSONArray("delays").length() > 1))))))))) {
                return 3;
            }
            return 2;
        } else if ((ListenerUtil.mutListener.listen(14719) ? (card.getQueue() >= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14718) ? (card.getQueue() <= Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14717) ? (card.getQueue() > Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14716) ? (card.getQueue() < Consts.QUEUE_TYPE_REV) : (ListenerUtil.mutListener.listen(14715) ? (card.getQueue() != Consts.QUEUE_TYPE_REV) : (card.getQueue() == Consts.QUEUE_TYPE_REV))))))) {
            return 4;
        } else {
            return 3;
        }
    }

    /*
     * Unbury cards.
     */
    @Override
    public void unburyCards() {
        if (!ListenerUtil.mutListener.listen(14743)) {
            mCol.getConf().put("lastUnburied", mToday);
        }
        if (!ListenerUtil.mutListener.listen(14744)) {
            mCol.log(mCol.getDb().queryLongList("select id from cards where " + queueIsBuriedSnippet()));
        }
        if (!ListenerUtil.mutListener.listen(14745)) {
            mCol.getDb().execute("update cards set " + _restoreQueueSnippet() + " where " + queueIsBuriedSnippet());
        }
    }

    @Override
    public void unburyCardsForDeck() {
        if (!ListenerUtil.mutListener.listen(14746)) {
            unburyCardsForDeck(mCol.getDecks().active());
        }
    }

    private void unburyCardsForDeck(@NonNull List<Long> allDecks) {
        // Refactored to allow unburying an arbitrary deck
        String sids = Utils.ids2str(allDecks);
        if (!ListenerUtil.mutListener.listen(14747)) {
            mCol.log(mCol.getDb().queryLongList("select id from cards where " + queueIsBuriedSnippet() + " and did in " + sids));
        }
        if (!ListenerUtil.mutListener.listen(14748)) {
            mCol.getDb().execute("update cards set mod=?,usn=?," + _restoreQueueSnippet() + " where " + queueIsBuriedSnippet() + " and did in " + sids, getTime().intTime(), mCol.usn());
        }
    }

    /**
     * Returns [deckname, did, rev, lrn, new]
     */
    @Override
    @Nullable
    public List<DeckDueTreeNode> deckDueList(@Nullable CancelListener cancelListener) {
        if (!ListenerUtil.mutListener.listen(14749)) {
            _checkDay();
        }
        if (!ListenerUtil.mutListener.listen(14750)) {
            mCol.getDecks().checkIntegrity();
        }
        ArrayList<Deck> decks = mCol.getDecks().allSorted();
        HashMap<String, Integer[]> lims = new HashMap<>(decks.size());
        ArrayList<DeckDueTreeNode> deckNodes = new ArrayList<>(decks.size());
        if (!ListenerUtil.mutListener.listen(14758)) {
            {
                long _loopCounter292 = 0;
                for (Deck deck : decks) {
                    ListenerUtil.loopListener.listen("_loopCounter292", ++_loopCounter292);
                    if (!ListenerUtil.mutListener.listen(14751)) {
                        if (isCancelled(cancelListener)) {
                            return null;
                        }
                    }
                    String deckName = deck.getString("name");
                    String p = Decks.parent(deckName);
                    // new
                    int nlim = _deckNewLimitSingle(deck, false);
                    int rlim = _deckRevLimitSingle(deck, false);
                    if (!ListenerUtil.mutListener.listen(14755)) {
                        if (!TextUtils.isEmpty(p)) {
                            Integer[] parentLims = lims.get(Decks.normalizeName(p));
                            if (!ListenerUtil.mutListener.listen(14752)) {
                                // 'temporary for diagnosis of bug #6383'
                                Assert.that(parentLims != null, "Deck %s is supposed to have parent %s. It has not be found.", deckName, p);
                            }
                            if (!ListenerUtil.mutListener.listen(14753)) {
                                nlim = Math.min(nlim, parentLims[0]);
                            }
                            if (!ListenerUtil.mutListener.listen(14754)) {
                                // review
                                rlim = Math.min(rlim, parentLims[1]);
                            }
                        }
                    }
                    int _new = _newForDeck(deck.getLong("id"), nlim);
                    // learning
                    int lrn = _lrnForDeck(deck.getLong("id"));
                    // reviews
                    int rev = _revForDeck(deck.getLong("id"), rlim);
                    if (!ListenerUtil.mutListener.listen(14756)) {
                        // save to list
                        deckNodes.add(new DeckDueTreeNode(mCol, deck.getString("name"), deck.getLong("id"), rev, lrn, _new));
                    }
                    if (!ListenerUtil.mutListener.listen(14757)) {
                        // add deck as a parent
                        lims.put(Decks.normalizeName(deck.getString("name")), new Integer[] { nlim, rlim });
                    }
                }
            }
        }
        return deckNodes;
    }

    /**
     * Return the next due card, or null.
     */
    @Override
    @Nullable
    protected Card _getCard() {
        // learning card due?
        @Nullable
        Card c = _getLrnCard(false);
        if (!ListenerUtil.mutListener.listen(14759)) {
            if (c != null) {
                return c;
            }
        }
        if (!ListenerUtil.mutListener.listen(14762)) {
            // new first, or time for one?
            if (_timeForNewCard()) {
                if (!ListenerUtil.mutListener.listen(14760)) {
                    c = _getNewCard();
                }
                if (!ListenerUtil.mutListener.listen(14761)) {
                    if (c != null) {
                        return c;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14763)) {
            // Card due for review?
            c = _getRevCard();
        }
        if (!ListenerUtil.mutListener.listen(14764)) {
            if (c != null) {
                return c;
            }
        }
        if (!ListenerUtil.mutListener.listen(14765)) {
            // day learning card due?
            c = _getLrnDayCard();
        }
        if (!ListenerUtil.mutListener.listen(14766)) {
            if (c != null) {
                return c;
            }
        }
        if (!ListenerUtil.mutListener.listen(14767)) {
            // New cards left?
            c = _getNewCard();
        }
        if (!ListenerUtil.mutListener.listen(14768)) {
            if (c != null) {
                return c;
            }
        }
        // collapse or finish
        return _getLrnCard(true);
    }

    @NonNull
    protected CardQueue<? extends Card.Cache>[] _fillNextCard() {
        if (!ListenerUtil.mutListener.listen(14769)) {
            // learning card due?
            if (_preloadLrnCard(false)) {
                return new CardQueue<?>[] { mLrnQueue };
            }
        }
        if (!ListenerUtil.mutListener.listen(14771)) {
            // new first, or time for one?
            if (_timeForNewCard()) {
                if (!ListenerUtil.mutListener.listen(14770)) {
                    if (_fillNew()) {
                        return new CardQueue<?>[] { mLrnQueue, mNewQueue };
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14772)) {
            // Card due for review?
            if (_fillRev()) {
                return new CardQueue<?>[] { mLrnQueue, mRevQueue };
            }
        }
        if (!ListenerUtil.mutListener.listen(14773)) {
            // day learning card due?
            if (_fillLrnDay()) {
                return new CardQueue<?>[] { mLrnQueue, mLrnDayQueue };
            }
        }
        if (!ListenerUtil.mutListener.listen(14774)) {
            // New cards left?
            if (_fillNew()) {
                return new CardQueue<?>[] { mLrnQueue, mNewQueue };
            }
        }
        if (!ListenerUtil.mutListener.listen(14775)) {
            // collapse or finish
            if (_preloadLrnCard(true)) {
                return new CardQueue<?>[] { mLrnQueue };
            }
        }
        return new CardQueue<?>[] {};
    }

    @Override
    protected void _resetLrnCount() {
        if (!ListenerUtil.mutListener.listen(14776)) {
            _resetLrnCount(null);
        }
    }

    protected void _resetLrnCount(@Nullable CancelListener cancelListener) {
        if (!ListenerUtil.mutListener.listen(14777)) {
            // sub-day
            mLrnCount = mCol.getDb().queryScalar("SELECT sum(left / 1000) FROM (SELECT left FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_LRN + " AND due < ? and id != ? LIMIT ?)", mDayCutoff, currentCardId(), mReportLimit);
        }
        if (!ListenerUtil.mutListener.listen(14778)) {
            if (isCancelled(cancelListener))
                return;
        }
        if (!ListenerUtil.mutListener.listen(14779)) {
            // day
            mLrnCount += mCol.getDb().queryScalar("SELECT count() FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ? " + "AND id != ? LIMIT ?", mToday, currentCardId(), mReportLimit);
        }
    }

    @Override
    protected void _resetLrnQueue() {
        if (!ListenerUtil.mutListener.listen(14780)) {
            mLrnQueue.clear();
        }
        if (!ListenerUtil.mutListener.listen(14781)) {
            mLrnDayQueue.clear();
        }
        if (!ListenerUtil.mutListener.listen(14782)) {
            mLrnDids = mCol.getDecks().active();
        }
    }

    // sub-day learning
    @Override
    protected boolean _fillLrn() {
        if ((ListenerUtil.mutListener.listen(14788) ? (mHaveCounts || (ListenerUtil.mutListener.listen(14787) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(14786) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(14785) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(14784) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(14783) ? (mLrnCount != 0) : (mLrnCount == 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(14787) ? (mLrnCount >= 0) : (ListenerUtil.mutListener.listen(14786) ? (mLrnCount <= 0) : (ListenerUtil.mutListener.listen(14785) ? (mLrnCount > 0) : (ListenerUtil.mutListener.listen(14784) ? (mLrnCount < 0) : (ListenerUtil.mutListener.listen(14783) ? (mLrnCount != 0) : (mLrnCount == 0))))))))) {
            return false;
        }
        if (!mLrnQueue.isEmpty()) {
            return true;
        }
        if (!ListenerUtil.mutListener.listen(14789)) {
            mLrnQueue.clear();
        }
        if (!ListenerUtil.mutListener.listen(14790)) {
            /* Difference with upstream:
         * Current card can't come in the queue.
         *
         * In standard usage, a card is not requested before the previous card is marked as reviewed. However, if we
         * decide to query a second card sooner, we don't want to get the same card a second time. This simulate
         * _getLrnCard which did remove the card from the queue. _sortIntoLrn will add the card back to the queue if
         * required when the card is reviewed.
         */
            mLrnQueue.setFilled();
        }
        try (Cursor cur = mCol.getDb().query("SELECT due, id FROM cards WHERE did IN " + _deckLimit() + " AND queue = " + Consts.QUEUE_TYPE_LRN + " AND due < ? AND id != ? LIMIT ?", mDayCutoff, currentCardId(), mReportLimit)) {
            if (!ListenerUtil.mutListener.listen(14792)) {
                {
                    long _loopCounter293 = 0;
                    while (cur.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter293", ++_loopCounter293);
                        if (!ListenerUtil.mutListener.listen(14791)) {
                            mLrnQueue.add(cur.getLong(0), cur.getLong(1));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(14793)) {
                // as it arrives sorted by did first, we need to sort it
                mLrnQueue.sort();
            }
            return !mLrnQueue.isEmpty();
        }
    }

    @Override
    @Nullable
    protected Card _getLrnCard(boolean collapse) {
        if (!ListenerUtil.mutListener.listen(14802)) {
            if (_fillLrn()) {
                long cutoff = getTime().intTime();
                if (!ListenerUtil.mutListener.listen(14795)) {
                    if (collapse) {
                        if (!ListenerUtil.mutListener.listen(14794)) {
                            cutoff += mCol.getConf().getInt("collapseTime");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14801)) {
                    if ((ListenerUtil.mutListener.listen(14800) ? (mLrnQueue.getFirstDue() >= cutoff) : (ListenerUtil.mutListener.listen(14799) ? (mLrnQueue.getFirstDue() <= cutoff) : (ListenerUtil.mutListener.listen(14798) ? (mLrnQueue.getFirstDue() > cutoff) : (ListenerUtil.mutListener.listen(14797) ? (mLrnQueue.getFirstDue() != cutoff) : (ListenerUtil.mutListener.listen(14796) ? (mLrnQueue.getFirstDue() == cutoff) : (mLrnQueue.getFirstDue() < cutoff))))))) {
                        return mLrnQueue.removeFirstCard();
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param ease 1=no, 2=yes, 3=remove
     */
    @Override
    protected void _answerLrnCard(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        JSONObject conf = _lrnConf(card);
        @Consts.CARD_TYPE
        int type;
        if ((ListenerUtil.mutListener.listen(14803) ? (card.isInDynamicDeck() || !card.getWasNew()) : (card.isInDynamicDeck() && !card.getWasNew()))) {
            type = Consts.CARD_TYPE_RELEARNING;
        } else if ((ListenerUtil.mutListener.listen(14808) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14807) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14806) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14805) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14804) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV))))))) {
            type = Consts.CARD_TYPE_REV;
        } else {
            type = Consts.CARD_TYPE_NEW;
        }
        boolean leaving = false;
        // lrnCount was decremented once when card was fetched
        int lastLeft = card.getLeft();
        if (!ListenerUtil.mutListener.listen(14918)) {
            // immediate graduate?
            if ((ListenerUtil.mutListener.listen(14813) ? (ease >= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(14812) ? (ease <= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(14811) ? (ease > Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(14810) ? (ease < Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(14809) ? (ease != Consts.BUTTON_THREE) : (ease == Consts.BUTTON_THREE))))))) {
                if (!ListenerUtil.mutListener.listen(14916)) {
                    _rescheduleAsRev(card, conf, true);
                }
                if (!ListenerUtil.mutListener.listen(14917)) {
                    leaving = true;
                }
            } else if ((ListenerUtil.mutListener.listen(14832) ? ((ListenerUtil.mutListener.listen(14818) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14817) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14816) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14815) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14814) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO)))))) || (ListenerUtil.mutListener.listen(14831) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) >= 0) : (ListenerUtil.mutListener.listen(14830) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) > 0) : (ListenerUtil.mutListener.listen(14829) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) < 0) : (ListenerUtil.mutListener.listen(14828) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) != 0) : (ListenerUtil.mutListener.listen(14827) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) == 0) : ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) <= 0))))))) : ((ListenerUtil.mutListener.listen(14818) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14817) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14816) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14815) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14814) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO)))))) && (ListenerUtil.mutListener.listen(14831) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) >= 0) : (ListenerUtil.mutListener.listen(14830) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) > 0) : (ListenerUtil.mutListener.listen(14829) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) < 0) : (ListenerUtil.mutListener.listen(14828) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) != 0) : (ListenerUtil.mutListener.listen(14827) ? ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) == 0) : ((ListenerUtil.mutListener.listen(14826) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14825) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14824) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14823) ? (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14822) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14821) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14820) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14819) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1))))) <= 0))))))))) {
                if (!ListenerUtil.mutListener.listen(14914)) {
                    _rescheduleAsRev(card, conf, false);
                }
                if (!ListenerUtil.mutListener.listen(14915)) {
                    leaving = true;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14866)) {
                    // one step towards graduation
                    if ((ListenerUtil.mutListener.listen(14837) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14836) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14835) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14834) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(14833) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO))))))) {
                        // decrement real left count and recalculate left today
                        int left = (ListenerUtil.mutListener.listen(14860) ? (((ListenerUtil.mutListener.listen(14856) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14855) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14854) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14853) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) % 1) : (ListenerUtil.mutListener.listen(14859) ? (((ListenerUtil.mutListener.listen(14856) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14855) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14854) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14853) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) / 1) : (ListenerUtil.mutListener.listen(14858) ? (((ListenerUtil.mutListener.listen(14856) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14855) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14854) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14853) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) * 1) : (ListenerUtil.mutListener.listen(14857) ? (((ListenerUtil.mutListener.listen(14856) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14855) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14854) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14853) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) + 1) : (((ListenerUtil.mutListener.listen(14856) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(14855) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14854) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14853) ? (card.getLeft() + 1000) : (card.getLeft() % 1000)))))) - 1)))));
                        if (!ListenerUtil.mutListener.listen(14865)) {
                            card.setLeft((ListenerUtil.mutListener.listen(14864) ? (_leftToday(conf.getJSONArray("delays"), left) % 1000) : (ListenerUtil.mutListener.listen(14863) ? (_leftToday(conf.getJSONArray("delays"), left) / 1000) : (ListenerUtil.mutListener.listen(14862) ? (_leftToday(conf.getJSONArray("delays"), left) - 1000) : (ListenerUtil.mutListener.listen(14861) ? (_leftToday(conf.getJSONArray("delays"), left) + 1000) : (_leftToday(conf.getJSONArray("delays"), left) * 1000))))) + left);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(14838)) {
                            card.setLeft(_startingLeft(card));
                        }
                        boolean resched = _resched(card);
                        if (!ListenerUtil.mutListener.listen(14845)) {
                            if ((ListenerUtil.mutListener.listen(14839) ? (conf.has("mult") || resched) : (conf.has("mult") && resched))) {
                                if (!ListenerUtil.mutListener.listen(14844)) {
                                    // review that's lapsed
                                    card.setIvl(Math.max(Math.max(1, (int) ((ListenerUtil.mutListener.listen(14843) ? (card.getIvl() % conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(14842) ? (card.getIvl() / conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(14841) ? (card.getIvl() - conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(14840) ? (card.getIvl() + conf.getDouble("mult")) : (card.getIvl() * conf.getDouble("mult")))))))), conf.getInt("minInt")));
                                }
                            } else {
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14852)) {
                            if ((ListenerUtil.mutListener.listen(14846) ? (resched || card.isInDynamicDeck()) : (resched && card.isInDynamicDeck()))) {
                                if (!ListenerUtil.mutListener.listen(14851)) {
                                    card.setODue((ListenerUtil.mutListener.listen(14850) ? (mToday % 1) : (ListenerUtil.mutListener.listen(14849) ? (mToday / 1) : (ListenerUtil.mutListener.listen(14848) ? (mToday * 1) : (ListenerUtil.mutListener.listen(14847) ? (mToday - 1) : (mToday + 1))))));
                                }
                            }
                        }
                    }
                }
                int delay = _delayForGrade(conf, card.getLeft());
                if (!ListenerUtil.mutListener.listen(14873)) {
                    if ((ListenerUtil.mutListener.listen(14871) ? (card.getDue() >= getTime().intTime()) : (ListenerUtil.mutListener.listen(14870) ? (card.getDue() <= getTime().intTime()) : (ListenerUtil.mutListener.listen(14869) ? (card.getDue() > getTime().intTime()) : (ListenerUtil.mutListener.listen(14868) ? (card.getDue() != getTime().intTime()) : (ListenerUtil.mutListener.listen(14867) ? (card.getDue() == getTime().intTime()) : (card.getDue() < getTime().intTime()))))))) {
                        if (!ListenerUtil.mutListener.listen(14872)) {
                            // not collapsed; add some randomness
                            delay *= Utils.randomFloatInRange(1f, 1.25f);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14874)) {
                    card.setDue(getTime().intTime() + delay);
                }
                if (!ListenerUtil.mutListener.listen(14913)) {
                    // due today?
                    if ((ListenerUtil.mutListener.listen(14879) ? (card.getDue() >= mDayCutoff) : (ListenerUtil.mutListener.listen(14878) ? (card.getDue() <= mDayCutoff) : (ListenerUtil.mutListener.listen(14877) ? (card.getDue() > mDayCutoff) : (ListenerUtil.mutListener.listen(14876) ? (card.getDue() != mDayCutoff) : (ListenerUtil.mutListener.listen(14875) ? (card.getDue() == mDayCutoff) : (card.getDue() < mDayCutoff))))))) {
                        if (!ListenerUtil.mutListener.listen(14902)) {
                            mLrnCount += (ListenerUtil.mutListener.listen(14901) ? (card.getLeft() % 1000) : (ListenerUtil.mutListener.listen(14900) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(14899) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(14898) ? (card.getLeft() + 1000) : (card.getLeft() / 1000)))));
                        }
                        if (!ListenerUtil.mutListener.listen(14903)) {
                            // it twice in a row
                            card.setQueue(Consts.QUEUE_TYPE_LRN);
                        }
                        if (!ListenerUtil.mutListener.listen(14911)) {
                            if ((ListenerUtil.mutListener.listen(14905) ? ((ListenerUtil.mutListener.listen(14904) ? (!mLrnQueue.isEmpty() || revCount() == 0) : (!mLrnQueue.isEmpty() && revCount() == 0)) || newCount() == 0) : ((ListenerUtil.mutListener.listen(14904) ? (!mLrnQueue.isEmpty() || revCount() == 0) : (!mLrnQueue.isEmpty() && revCount() == 0)) && newCount() == 0))) {
                                long smallestDue = mLrnQueue.getFirstDue();
                                if (!ListenerUtil.mutListener.listen(14910)) {
                                    card.setDue(Math.max(card.getDue(), (ListenerUtil.mutListener.listen(14909) ? (smallestDue % 1) : (ListenerUtil.mutListener.listen(14908) ? (smallestDue / 1) : (ListenerUtil.mutListener.listen(14907) ? (smallestDue * 1) : (ListenerUtil.mutListener.listen(14906) ? (smallestDue - 1) : (smallestDue + 1)))))));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(14912)) {
                            _sortIntoLrn(card.getDue(), card.getId());
                        }
                    } else {
                        // the card is due in one or more days, so we need to use the day learn queue
                        long ahead = (ListenerUtil.mutListener.listen(14891) ? (((ListenerUtil.mutListener.listen(14887) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14886) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14885) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14884) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) % 1) : (ListenerUtil.mutListener.listen(14890) ? (((ListenerUtil.mutListener.listen(14887) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14886) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14885) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14884) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) / 1) : (ListenerUtil.mutListener.listen(14889) ? (((ListenerUtil.mutListener.listen(14887) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14886) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14885) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14884) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) * 1) : (ListenerUtil.mutListener.listen(14888) ? (((ListenerUtil.mutListener.listen(14887) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14886) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14885) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14884) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) - 1) : (((ListenerUtil.mutListener.listen(14887) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14886) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14885) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(14884) ? (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(14883) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(14882) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(14881) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(14880) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) + 1)))));
                        if (!ListenerUtil.mutListener.listen(14896)) {
                            card.setDue((ListenerUtil.mutListener.listen(14895) ? (mToday % ahead) : (ListenerUtil.mutListener.listen(14894) ? (mToday / ahead) : (ListenerUtil.mutListener.listen(14893) ? (mToday * ahead) : (ListenerUtil.mutListener.listen(14892) ? (mToday - ahead) : (mToday + ahead))))));
                        }
                        if (!ListenerUtil.mutListener.listen(14897)) {
                            card.setQueue(Consts.QUEUE_TYPE_DAY_LEARN_RELEARN);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14919)) {
            _logLrn(card, ease, conf, leaving, type, lastLeft);
        }
    }

    @Override
    @NonNull
    protected JSONObject _lrnConf(@NonNull Card card) {
        if ((ListenerUtil.mutListener.listen(14924) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14923) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14922) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14921) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14920) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV))))))) {
            return _lapseConf(card);
        } else {
            return _newConf(card);
        }
    }

    @Override
    protected void _rescheduleAsRev(@NonNull Card card, @NonNull JSONObject conf, boolean early) {
        boolean lapse = ((ListenerUtil.mutListener.listen(14929) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14928) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14927) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14926) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14925) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV)))))));
        if (!ListenerUtil.mutListener.listen(14939)) {
            if (lapse) {
                if (!ListenerUtil.mutListener.listen(14937)) {
                    if (_resched(card)) {
                        if (!ListenerUtil.mutListener.listen(14936)) {
                            card.setDue(Math.max((ListenerUtil.mutListener.listen(14935) ? (mToday % 1) : (ListenerUtil.mutListener.listen(14934) ? (mToday / 1) : (ListenerUtil.mutListener.listen(14933) ? (mToday * 1) : (ListenerUtil.mutListener.listen(14932) ? (mToday - 1) : (mToday + 1))))), card.getODue()));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(14931)) {
                            card.setDue(card.getODue());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(14938)) {
                    card.setODue(0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(14930)) {
                    _rescheduleNew(card, conf, early);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(14940)) {
            card.setQueue(Consts.QUEUE_TYPE_REV);
        }
        if (!ListenerUtil.mutListener.listen(14941)) {
            card.setType(Consts.CARD_TYPE_REV);
        }
        // if we were dynamic, graduating means moving back to the old deck
        boolean resched = _resched(card);
        if (!ListenerUtil.mutListener.listen(14950)) {
            if (card.isInDynamicDeck()) {
                if (!ListenerUtil.mutListener.listen(14942)) {
                    card.setDid(card.getODid());
                }
                if (!ListenerUtil.mutListener.listen(14943)) {
                    card.setODue(0);
                }
                if (!ListenerUtil.mutListener.listen(14944)) {
                    card.setODid(0);
                }
                if (!ListenerUtil.mutListener.listen(14949)) {
                    // if rescheduling is off, it needs to be set back to a new card
                    if ((ListenerUtil.mutListener.listen(14945) ? (!resched || !lapse) : (!resched && !lapse))) {
                        if (!ListenerUtil.mutListener.listen(14946)) {
                            card.setType(Consts.CARD_TYPE_NEW);
                        }
                        if (!ListenerUtil.mutListener.listen(14947)) {
                            card.setQueue(Consts.QUEUE_TYPE_NEW);
                        }
                        if (!ListenerUtil.mutListener.listen(14948)) {
                            card.setDue(mCol.nextID("pos"));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected int _startingLeft(@NonNull Card card) {
        JSONObject conf;
        if ((ListenerUtil.mutListener.listen(14955) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14954) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14953) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14952) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14951) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV))))))) {
            conf = _lapseConf(card);
        } else {
            conf = _lrnConf(card);
        }
        int tot = conf.getJSONArray("delays").length();
        int tod = _leftToday(conf.getJSONArray("delays"), tot);
        return (ListenerUtil.mutListener.listen(14963) ? (tot % (ListenerUtil.mutListener.listen(14959) ? (tod % 1000) : (ListenerUtil.mutListener.listen(14958) ? (tod / 1000) : (ListenerUtil.mutListener.listen(14957) ? (tod - 1000) : (ListenerUtil.mutListener.listen(14956) ? (tod + 1000) : (tod * 1000)))))) : (ListenerUtil.mutListener.listen(14962) ? (tot / (ListenerUtil.mutListener.listen(14959) ? (tod % 1000) : (ListenerUtil.mutListener.listen(14958) ? (tod / 1000) : (ListenerUtil.mutListener.listen(14957) ? (tod - 1000) : (ListenerUtil.mutListener.listen(14956) ? (tod + 1000) : (tod * 1000)))))) : (ListenerUtil.mutListener.listen(14961) ? (tot * (ListenerUtil.mutListener.listen(14959) ? (tod % 1000) : (ListenerUtil.mutListener.listen(14958) ? (tod / 1000) : (ListenerUtil.mutListener.listen(14957) ? (tod - 1000) : (ListenerUtil.mutListener.listen(14956) ? (tod + 1000) : (tod * 1000)))))) : (ListenerUtil.mutListener.listen(14960) ? (tot - (ListenerUtil.mutListener.listen(14959) ? (tod % 1000) : (ListenerUtil.mutListener.listen(14958) ? (tod / 1000) : (ListenerUtil.mutListener.listen(14957) ? (tod - 1000) : (ListenerUtil.mutListener.listen(14956) ? (tod + 1000) : (tod * 1000)))))) : (tot + (ListenerUtil.mutListener.listen(14959) ? (tod % 1000) : (ListenerUtil.mutListener.listen(14958) ? (tod / 1000) : (ListenerUtil.mutListener.listen(14957) ? (tod - 1000) : (ListenerUtil.mutListener.listen(14956) ? (tod + 1000) : (tod * 1000))))))))));
    }

    private int _graduatingIvl(@NonNull Card card, @NonNull JSONObject conf, boolean early, boolean adj) {
        if ((ListenerUtil.mutListener.listen(14968) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14967) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14966) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14965) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(14964) ? (card.getType() != Consts.CARD_TYPE_REV) : (card.getType() == Consts.CARD_TYPE_REV))))))) {
            // lapsed card being relearnt
            if (card.isInDynamicDeck()) {
                if (conf.getBoolean("resched")) {
                    return _dynIvlBoost(card);
                }
            }
            return card.getIvl();
        }
        int ideal;
        JSONArray ints = conf.getJSONArray("ints");
        if (!early) {
            // graduate
            ideal = ints.getInt(0);
        } else {
            ideal = ints.getInt(1);
        }
        if (adj) {
            return _adjRevIvl(card, ideal);
        } else {
            return ideal;
        }
    }

    /* Reschedule a new card that's graduated for the first time. */
    private void _rescheduleNew(@NonNull Card card, @NonNull JSONObject conf, boolean early) {
        if (!ListenerUtil.mutListener.listen(14969)) {
            card.setIvl(_graduatingIvl(card, conf, early));
        }
        if (!ListenerUtil.mutListener.listen(14974)) {
            card.setDue((ListenerUtil.mutListener.listen(14973) ? (mToday % card.getIvl()) : (ListenerUtil.mutListener.listen(14972) ? (mToday / card.getIvl()) : (ListenerUtil.mutListener.listen(14971) ? (mToday * card.getIvl()) : (ListenerUtil.mutListener.listen(14970) ? (mToday - card.getIvl()) : (mToday + card.getIvl()))))));
        }
        if (!ListenerUtil.mutListener.listen(14975)) {
            card.setFactor(conf.getInt("initialFactor"));
        }
    }

    @VisibleForTesting
    public void removeLrn() {
        if (!ListenerUtil.mutListener.listen(14976)) {
            removeLrn(null);
        }
    }

    /**
     * Remove cards from the learning queues.
     */
    private void removeLrn(@NonNull long[] ids) {
        String extra;
        if ((ListenerUtil.mutListener.listen(14982) ? (ids != null || (ListenerUtil.mutListener.listen(14981) ? (ids.length >= 0) : (ListenerUtil.mutListener.listen(14980) ? (ids.length <= 0) : (ListenerUtil.mutListener.listen(14979) ? (ids.length < 0) : (ListenerUtil.mutListener.listen(14978) ? (ids.length != 0) : (ListenerUtil.mutListener.listen(14977) ? (ids.length == 0) : (ids.length > 0))))))) : (ids != null && (ListenerUtil.mutListener.listen(14981) ? (ids.length >= 0) : (ListenerUtil.mutListener.listen(14980) ? (ids.length <= 0) : (ListenerUtil.mutListener.listen(14979) ? (ids.length < 0) : (ListenerUtil.mutListener.listen(14978) ? (ids.length != 0) : (ListenerUtil.mutListener.listen(14977) ? (ids.length == 0) : (ids.length > 0))))))))) {
            extra = " AND id IN " + Utils.ids2str(ids);
        } else {
            // benchmarks indicate it's about 10x faster to search all decks with the index than scan the table
            extra = " AND did IN " + Utils.ids2str(mCol.getDecks().allIds());
        }
        if (!ListenerUtil.mutListener.listen(14983)) {
            // review cards in relearning
            mCol.getDb().execute("update cards set due = odue, queue = " + Consts.QUEUE_TYPE_REV + ", mod = ?" + ", usn = ?, odue = 0 where queue IN (" + Consts.QUEUE_TYPE_LRN + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ") and type = " + Consts.CARD_TYPE_REV + " " + extra, getTime().intTime(), mCol.usn());
        }
        if (!ListenerUtil.mutListener.listen(14984)) {
            // new cards in learning
            forgetCards(mCol.getDb().queryLongList("SELECT id FROM cards WHERE queue IN (" + Consts.QUEUE_TYPE_LRN + "," + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + ") " + extra));
        }
    }

    private int _lrnForDeck(long did) {
        try {
            int cnt = mCol.getDb().queryScalar("SELECT sum(left / 1000) FROM (SELECT left FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_LRN + " AND due < ?" + " LIMIT ?)", did, (getTime().intTime() + mCol.getConf().getInt("collapseTime")), mReportLimit);
            return (ListenerUtil.mutListener.listen(14988) ? (cnt % mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (ListenerUtil.mutListener.listen(14987) ? (cnt / mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (ListenerUtil.mutListener.listen(14986) ? (cnt * mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (ListenerUtil.mutListener.listen(14985) ? (cnt - mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit)) : (cnt + mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ?" + " AND queue = " + Consts.QUEUE_TYPE_DAY_LEARN_RELEARN + " AND due <= ?" + " LIMIT ?)", did, mToday, mReportLimit))))));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param considerCurrentCard Whether current card should be conted if it is in this deck
     */
    protected int _deckRevLimit(long did, boolean considerCurrentCard) {
        return _deckNewLimit(did, d -> _deckRevLimitSingle(d, considerCurrentCard), considerCurrentCard);
    }

    /**
     * Maximal number of rev card still to see today in deck d. It's computed as:
     * the number of rev card to see by day according
     * minus the number of rev cards seen today in deck d or a descendant
     * plus the number of extra cards to see today in deck d, a parent or a descendant.
     *
     * Limits of its ancestors are not applied.  Current card is treated the same way as other cards.
     * @param considerCurrentCard Whether current card should be conted if it is in this deck
     */
    @Override
    protected int _deckRevLimitSingle(@NonNull Deck d, boolean considerCurrentCard) {
        if (!ListenerUtil.mutListener.listen(14989)) {
            if (d.getInt("dyn") == DECK_DYN) {
                return mReportLimit;
            }
        }
        long did = d.getLong("id");
        DeckConfig c = mCol.getDecks().confForDid(did);
        int lim = Math.max(0, (ListenerUtil.mutListener.listen(14993) ? (c.getJSONObject("rev").getInt("perDay") % d.getJSONArray("revToday").getInt(1)) : (ListenerUtil.mutListener.listen(14992) ? (c.getJSONObject("rev").getInt("perDay") / d.getJSONArray("revToday").getInt(1)) : (ListenerUtil.mutListener.listen(14991) ? (c.getJSONObject("rev").getInt("perDay") * d.getJSONArray("revToday").getInt(1)) : (ListenerUtil.mutListener.listen(14990) ? (c.getJSONObject("rev").getInt("perDay") + d.getJSONArray("revToday").getInt(1)) : (c.getJSONObject("rev").getInt("perDay") - d.getJSONArray("revToday").getInt(1)))))));
        if (!ListenerUtil.mutListener.listen(14996)) {
            if ((ListenerUtil.mutListener.listen(14994) ? (considerCurrentCard || currentCardIsInQueueWithDeck(Consts.QUEUE_TYPE_REV, did)) : (considerCurrentCard && currentCardIsInQueueWithDeck(Consts.QUEUE_TYPE_REV, did)))) {
                if (!ListenerUtil.mutListener.listen(14995)) {
                    lim--;
                }
            }
        }
        // So currentCard does not have to be taken into consideration in this method
        return lim;
    }

    private int _revForDeck(long did, int lim) {
        if (!ListenerUtil.mutListener.listen(14997)) {
            lim = Math.min(lim, mReportLimit);
        }
        return mCol.getDb().queryScalar("SELECT count() FROM (SELECT 1 FROM cards WHERE did = ? AND queue = " + Consts.QUEUE_TYPE_REV + " AND due <= ? LIMIT ?)", did, mToday, lim);
    }

    @Override
    protected void _resetRevCount() {
        if (!ListenerUtil.mutListener.listen(14998)) {
            _resetRevCount(null);
        }
    }

    protected void _resetRevCount(@Nullable CancelListener cancelListener) {
        if (!ListenerUtil.mutListener.listen(14999)) {
            mRevCount = _walkingCount(d -> _deckRevLimitSingle(d, true), this::_cntFnRev, cancelListener);
        }
    }

    // Dynamically invoked in _walkingCount, passed as a parameter in _resetRevCount
    @SuppressWarnings("unused")
    protected int _cntFnRev(long did, int lim) {
        // protected because _walkingCount need to be able to access it.
        return mCol.getDb().queryScalar("SELECT count() FROM (SELECT id FROM cards WHERE did = ? AND queue = " + Consts.QUEUE_TYPE_REV + " and due <= ? " + " AND id != ? LIMIT ?)", did, mToday, currentCardId(), lim);
    }

    @Override
    protected void _resetRevQueue() {
        if (!ListenerUtil.mutListener.listen(15000)) {
            mRevQueue.clear();
        }
        if (!ListenerUtil.mutListener.listen(15001)) {
            mRevDids = mCol.getDecks().active();
        }
    }

    @Override
    protected boolean _fillRev(boolean allowSibling) {
        if (!ListenerUtil.mutListener.listen(15002)) {
            if (!mRevQueue.isEmpty()) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(15009)) {
            if ((ListenerUtil.mutListener.listen(15008) ? (mHaveCounts || (ListenerUtil.mutListener.listen(15007) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(15006) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(15005) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(15004) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(15003) ? (mRevCount != 0) : (mRevCount == 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(15007) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(15006) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(15005) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(15004) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(15003) ? (mRevCount != 0) : (mRevCount == 0))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15031)) {
            {
                long _loopCounter295 = 0;
                while (!mRevDids.isEmpty()) {
                    ListenerUtil.loopListener.listen("_loopCounter295", ++_loopCounter295);
                    long did = mRevDids.getFirst();
                    int lim = Math.min(mQueueLimit, _deckRevLimit(did, false));
                    if (!ListenerUtil.mutListener.listen(15029)) {
                        if ((ListenerUtil.mutListener.listen(15014) ? (lim >= 0) : (ListenerUtil.mutListener.listen(15013) ? (lim <= 0) : (ListenerUtil.mutListener.listen(15012) ? (lim > 0) : (ListenerUtil.mutListener.listen(15011) ? (lim < 0) : (ListenerUtil.mutListener.listen(15010) ? (lim == 0) : (lim != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(15015)) {
                                mRevQueue.clear();
                            }
                            // fill the queue with the current did
                            String idName = (allowSibling) ? "id" : "nid";
                            long id = (allowSibling) ? currentCardId() : currentCardNid();
                            if (!ListenerUtil.mutListener.listen(15017)) {
                                {
                                    long _loopCounter294 = 0;
                                    for (long cid : mCol.getDb().queryLongList("SELECT id FROM cards WHERE did = ? AND queue = " + Consts.QUEUE_TYPE_REV + " AND due <= ?" + " AND " + idName + " != ? LIMIT ?", did, mToday, id, lim)) {
                                        ListenerUtil.loopListener.listen("_loopCounter294", ++_loopCounter294);
                                        if (!ListenerUtil.mutListener.listen(15016)) {
                                            /* Difference with upstream: we take current card into account.
                     *
                     * When current card is answered, the card is not due anymore, so does not belong to the queue.
                     * Furthermore, _burySiblings ensure that the siblings of the current cards are removed from the
                     * queue to ensure same day spacing. We simulate this action by ensuring that those siblings are not
                     * filled, except if we know there are cards and we didn't find any non-sibling card. This way, the
                     * queue is not empty if it should not be empty (important for the conditional belows), but the
                     * front of the queue contains distinct card.
                     */
                                            mRevQueue.add(cid);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(15028)) {
                                if (!mRevQueue.isEmpty()) {
                                    if (!ListenerUtil.mutListener.listen(15020)) {
                                        // ordering
                                        if (mCol.getDecks().get(did).getInt("dyn") == DECK_DYN) {
                                        } else {
                                            Random r = new Random();
                                            if (!ListenerUtil.mutListener.listen(15018)) {
                                                r.setSeed(mToday);
                                            }
                                            if (!ListenerUtil.mutListener.listen(15019)) {
                                                mRevQueue.shuffle(r);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(15027)) {
                                        // is the current did empty?
                                        if ((ListenerUtil.mutListener.listen(15025) ? (mRevQueue.size() >= lim) : (ListenerUtil.mutListener.listen(15024) ? (mRevQueue.size() <= lim) : (ListenerUtil.mutListener.listen(15023) ? (mRevQueue.size() > lim) : (ListenerUtil.mutListener.listen(15022) ? (mRevQueue.size() != lim) : (ListenerUtil.mutListener.listen(15021) ? (mRevQueue.size() == lim) : (mRevQueue.size() < lim))))))) {
                                            if (!ListenerUtil.mutListener.listen(15026)) {
                                                mRevDids.remove();
                                            }
                                        }
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(15030)) {
                        // nothing left in the deck; move to next
                        mRevDids.remove();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15039)) {
            if ((ListenerUtil.mutListener.listen(15037) ? (mHaveCounts || (ListenerUtil.mutListener.listen(15036) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(15035) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(15034) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(15033) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(15032) ? (mRevCount == 0) : (mRevCount != 0))))))) : (mHaveCounts && (ListenerUtil.mutListener.listen(15036) ? (mRevCount >= 0) : (ListenerUtil.mutListener.listen(15035) ? (mRevCount <= 0) : (ListenerUtil.mutListener.listen(15034) ? (mRevCount > 0) : (ListenerUtil.mutListener.listen(15033) ? (mRevCount < 0) : (ListenerUtil.mutListener.listen(15032) ? (mRevCount == 0) : (mRevCount != 0))))))))) {
                if (!ListenerUtil.mutListener.listen(15038)) {
                    // removed from the queue but not buried
                    _resetRev();
                }
                return _fillRev(true);
            }
        }
        return false;
    }

    @Override
    protected void _answerRevCard(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        int delay = 0;
        if (!ListenerUtil.mutListener.listen(15047)) {
            if ((ListenerUtil.mutListener.listen(15044) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15043) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15042) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15041) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15040) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
                if (!ListenerUtil.mutListener.listen(15046)) {
                    delay = _rescheduleLapse(card);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15045)) {
                    _rescheduleRev(card, ease);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15048)) {
            _logRev(card, ease, delay, Consts.REVLOG_REV);
        }
    }

    @Override
    protected int _rescheduleLapse(@NonNull Card card) {
        JSONObject conf = _lapseConf(card);
        if (!ListenerUtil.mutListener.listen(15049)) {
            card.setLastIvl(card.getIvl());
        }
        if (!ListenerUtil.mutListener.listen(15068)) {
            if (_resched(card)) {
                if (!ListenerUtil.mutListener.listen(15054)) {
                    card.setLapses((ListenerUtil.mutListener.listen(15053) ? (card.getLapses() % 1) : (ListenerUtil.mutListener.listen(15052) ? (card.getLapses() / 1) : (ListenerUtil.mutListener.listen(15051) ? (card.getLapses() * 1) : (ListenerUtil.mutListener.listen(15050) ? (card.getLapses() - 1) : (card.getLapses() + 1))))));
                }
                if (!ListenerUtil.mutListener.listen(15055)) {
                    card.setIvl(_nextLapseIvl(card, conf));
                }
                if (!ListenerUtil.mutListener.listen(15060)) {
                    card.setFactor(Math.max(1300, (ListenerUtil.mutListener.listen(15059) ? (card.getFactor() % 200) : (ListenerUtil.mutListener.listen(15058) ? (card.getFactor() / 200) : (ListenerUtil.mutListener.listen(15057) ? (card.getFactor() * 200) : (ListenerUtil.mutListener.listen(15056) ? (card.getFactor() + 200) : (card.getFactor() - 200)))))));
                }
                if (!ListenerUtil.mutListener.listen(15065)) {
                    card.setDue((ListenerUtil.mutListener.listen(15064) ? (mToday % card.getIvl()) : (ListenerUtil.mutListener.listen(15063) ? (mToday / card.getIvl()) : (ListenerUtil.mutListener.listen(15062) ? (mToday * card.getIvl()) : (ListenerUtil.mutListener.listen(15061) ? (mToday - card.getIvl()) : (mToday + card.getIvl()))))));
                }
                if (!ListenerUtil.mutListener.listen(15067)) {
                    // if it's a filtered deck, update odue as well
                    if (card.isInDynamicDeck()) {
                        if (!ListenerUtil.mutListener.listen(15066)) {
                            card.setODue(card.getDue());
                        }
                    }
                }
            }
        }
        // if suspended as a leech, nothing to do
        int delay = 0;
        if (!ListenerUtil.mutListener.listen(15075)) {
            if ((ListenerUtil.mutListener.listen(15074) ? (_checkLeech(card, conf) || (ListenerUtil.mutListener.listen(15073) ? (card.getQueue() >= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15072) ? (card.getQueue() <= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15071) ? (card.getQueue() > Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15070) ? (card.getQueue() < Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15069) ? (card.getQueue() != Consts.QUEUE_TYPE_SUSPENDED) : (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED))))))) : (_checkLeech(card, conf) && (ListenerUtil.mutListener.listen(15073) ? (card.getQueue() >= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15072) ? (card.getQueue() <= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15071) ? (card.getQueue() > Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15070) ? (card.getQueue() < Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(15069) ? (card.getQueue() != Consts.QUEUE_TYPE_SUSPENDED) : (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED))))))))) {
                return delay;
            }
        }
        if (!ListenerUtil.mutListener.listen(15076)) {
            // if no relearning steps, nothing to do
            if (conf.getJSONArray("delays").length() == 0) {
                return delay;
            }
        }
        if (!ListenerUtil.mutListener.listen(15083)) {
            // record rev due date for later
            if ((ListenerUtil.mutListener.listen(15081) ? (card.getODue() >= 0) : (ListenerUtil.mutListener.listen(15080) ? (card.getODue() <= 0) : (ListenerUtil.mutListener.listen(15079) ? (card.getODue() > 0) : (ListenerUtil.mutListener.listen(15078) ? (card.getODue() < 0) : (ListenerUtil.mutListener.listen(15077) ? (card.getODue() != 0) : (card.getODue() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(15082)) {
                    card.setODue(card.getDue());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15084)) {
            delay = _delayForGrade(conf, 0);
        }
        if (!ListenerUtil.mutListener.listen(15085)) {
            card.setDue(delay + getTime().intTime());
        }
        if (!ListenerUtil.mutListener.listen(15086)) {
            card.setLeft(_startingLeft(card));
        }
        if (!ListenerUtil.mutListener.listen(15117)) {
            // queue 1
            if ((ListenerUtil.mutListener.listen(15091) ? (card.getDue() >= mDayCutoff) : (ListenerUtil.mutListener.listen(15090) ? (card.getDue() <= mDayCutoff) : (ListenerUtil.mutListener.listen(15089) ? (card.getDue() > mDayCutoff) : (ListenerUtil.mutListener.listen(15088) ? (card.getDue() != mDayCutoff) : (ListenerUtil.mutListener.listen(15087) ? (card.getDue() == mDayCutoff) : (card.getDue() < mDayCutoff))))))) {
                if (!ListenerUtil.mutListener.listen(15114)) {
                    mLrnCount += (ListenerUtil.mutListener.listen(15113) ? (card.getLeft() % 1000) : (ListenerUtil.mutListener.listen(15112) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(15111) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(15110) ? (card.getLeft() + 1000) : (card.getLeft() / 1000)))));
                }
                if (!ListenerUtil.mutListener.listen(15115)) {
                    card.setQueue(Consts.QUEUE_TYPE_LRN);
                }
                if (!ListenerUtil.mutListener.listen(15116)) {
                    _sortIntoLrn(card.getDue(), card.getId());
                }
            } else {
                // day learn queue
                long ahead = (ListenerUtil.mutListener.listen(15103) ? (((ListenerUtil.mutListener.listen(15099) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15098) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15097) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15096) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) % 1) : (ListenerUtil.mutListener.listen(15102) ? (((ListenerUtil.mutListener.listen(15099) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15098) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15097) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15096) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) / 1) : (ListenerUtil.mutListener.listen(15101) ? (((ListenerUtil.mutListener.listen(15099) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15098) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15097) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15096) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) * 1) : (ListenerUtil.mutListener.listen(15100) ? (((ListenerUtil.mutListener.listen(15099) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15098) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15097) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15096) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) - 1) : (((ListenerUtil.mutListener.listen(15099) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15098) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15097) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15096) ? (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15095) ? (card.getDue() % mDayCutoff) : (ListenerUtil.mutListener.listen(15094) ? (card.getDue() / mDayCutoff) : (ListenerUtil.mutListener.listen(15093) ? (card.getDue() * mDayCutoff) : (ListenerUtil.mutListener.listen(15092) ? (card.getDue() + mDayCutoff) : (card.getDue() - mDayCutoff)))))) / SECONDS_PER_DAY)))))) + 1)))));
                if (!ListenerUtil.mutListener.listen(15108)) {
                    card.setDue((ListenerUtil.mutListener.listen(15107) ? (mToday % ahead) : (ListenerUtil.mutListener.listen(15106) ? (mToday / ahead) : (ListenerUtil.mutListener.listen(15105) ? (mToday * ahead) : (ListenerUtil.mutListener.listen(15104) ? (mToday - ahead) : (mToday + ahead))))));
                }
                if (!ListenerUtil.mutListener.listen(15109)) {
                    card.setQueue(Consts.QUEUE_TYPE_DAY_LEARN_RELEARN);
                }
            }
        }
        return delay;
    }

    private int _nextLapseIvl(@NonNull Card card, @NonNull JSONObject conf) {
        return Math.max(conf.getInt("minInt"), (int) ((ListenerUtil.mutListener.listen(15121) ? (card.getIvl() % conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(15120) ? (card.getIvl() / conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(15119) ? (card.getIvl() - conf.getDouble("mult")) : (ListenerUtil.mutListener.listen(15118) ? (card.getIvl() + conf.getDouble("mult")) : (card.getIvl() * conf.getDouble("mult"))))))));
    }

    private void _rescheduleRev(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(15122)) {
            // update interval
            card.setLastIvl(card.getIvl());
        }
        if (!ListenerUtil.mutListener.listen(15139)) {
            if (_resched(card)) {
                if (!ListenerUtil.mutListener.listen(15124)) {
                    _updateRevIvl(card, ease);
                }
                if (!ListenerUtil.mutListener.listen(15133)) {
                    // then the rest
                    card.setFactor(Math.max(1300, (ListenerUtil.mutListener.listen(15132) ? (card.getFactor() % FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(15128) ? (ease % 2) : (ListenerUtil.mutListener.listen(15127) ? (ease / 2) : (ListenerUtil.mutListener.listen(15126) ? (ease * 2) : (ListenerUtil.mutListener.listen(15125) ? (ease + 2) : (ease - 2)))))]) : (ListenerUtil.mutListener.listen(15131) ? (card.getFactor() / FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(15128) ? (ease % 2) : (ListenerUtil.mutListener.listen(15127) ? (ease / 2) : (ListenerUtil.mutListener.listen(15126) ? (ease * 2) : (ListenerUtil.mutListener.listen(15125) ? (ease + 2) : (ease - 2)))))]) : (ListenerUtil.mutListener.listen(15130) ? (card.getFactor() * FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(15128) ? (ease % 2) : (ListenerUtil.mutListener.listen(15127) ? (ease / 2) : (ListenerUtil.mutListener.listen(15126) ? (ease * 2) : (ListenerUtil.mutListener.listen(15125) ? (ease + 2) : (ease - 2)))))]) : (ListenerUtil.mutListener.listen(15129) ? (card.getFactor() - FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(15128) ? (ease % 2) : (ListenerUtil.mutListener.listen(15127) ? (ease / 2) : (ListenerUtil.mutListener.listen(15126) ? (ease * 2) : (ListenerUtil.mutListener.listen(15125) ? (ease + 2) : (ease - 2)))))]) : (card.getFactor() + FACTOR_ADDITION_VALUES[(ListenerUtil.mutListener.listen(15128) ? (ease % 2) : (ListenerUtil.mutListener.listen(15127) ? (ease / 2) : (ListenerUtil.mutListener.listen(15126) ? (ease * 2) : (ListenerUtil.mutListener.listen(15125) ? (ease + 2) : (ease - 2)))))])))))));
                }
                if (!ListenerUtil.mutListener.listen(15138)) {
                    card.setDue((ListenerUtil.mutListener.listen(15137) ? (mToday % card.getIvl()) : (ListenerUtil.mutListener.listen(15136) ? (mToday / card.getIvl()) : (ListenerUtil.mutListener.listen(15135) ? (mToday * card.getIvl()) : (ListenerUtil.mutListener.listen(15134) ? (mToday - card.getIvl()) : (mToday + card.getIvl()))))));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(15123)) {
                    card.setDue(card.getODue());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15143)) {
            if (card.isInDynamicDeck()) {
                if (!ListenerUtil.mutListener.listen(15140)) {
                    card.setDid(card.getODid());
                }
                if (!ListenerUtil.mutListener.listen(15141)) {
                    card.setODid(0);
                }
                if (!ListenerUtil.mutListener.listen(15142)) {
                    card.setODue(0);
                }
            }
        }
    }

    /**
     * Ideal next interval for CARD, given EASE.
     */
    private int _nextRevIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        long delay = _daysLate(card);
        int interval = 0;
        JSONObject conf = _revConf(card);
        double fct = (ListenerUtil.mutListener.listen(15147) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15146) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15145) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15144) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))));
        int ivl2 = _constrainedIvl((int) ((ListenerUtil.mutListener.listen(15159) ? (((ListenerUtil.mutListener.listen(15155) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15154) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15153) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15152) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4))))))))))) % 1.2) : (ListenerUtil.mutListener.listen(15158) ? (((ListenerUtil.mutListener.listen(15155) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15154) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15153) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15152) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4))))))))))) / 1.2) : (ListenerUtil.mutListener.listen(15157) ? (((ListenerUtil.mutListener.listen(15155) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15154) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15153) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15152) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4))))))))))) - 1.2) : (ListenerUtil.mutListener.listen(15156) ? (((ListenerUtil.mutListener.listen(15155) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15154) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15153) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15152) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4))))))))))) + 1.2) : (((ListenerUtil.mutListener.listen(15155) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15154) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15153) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (ListenerUtil.mutListener.listen(15152) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15151) ? (delay % 4) : (ListenerUtil.mutListener.listen(15150) ? (delay * 4) : (ListenerUtil.mutListener.listen(15149) ? (delay - 4) : (ListenerUtil.mutListener.listen(15148) ? (delay + 4) : (delay / 4))))))))))) * 1.2)))))), conf, card.getIvl());
        int ivl3 = _constrainedIvl((int) ((ListenerUtil.mutListener.listen(15171) ? (((ListenerUtil.mutListener.listen(15167) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15166) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15165) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15164) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2))))))))))) % fct) : (ListenerUtil.mutListener.listen(15170) ? (((ListenerUtil.mutListener.listen(15167) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15166) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15165) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15164) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2))))))))))) / fct) : (ListenerUtil.mutListener.listen(15169) ? (((ListenerUtil.mutListener.listen(15167) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15166) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15165) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15164) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2))))))))))) - fct) : (ListenerUtil.mutListener.listen(15168) ? (((ListenerUtil.mutListener.listen(15167) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15166) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15165) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15164) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2))))))))))) + fct) : (((ListenerUtil.mutListener.listen(15167) ? (card.getIvl() % (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15166) ? (card.getIvl() / (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15165) ? (card.getIvl() * (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (ListenerUtil.mutListener.listen(15164) ? (card.getIvl() - (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2)))))) : (card.getIvl() + (ListenerUtil.mutListener.listen(15163) ? (delay % 2) : (ListenerUtil.mutListener.listen(15162) ? (delay * 2) : (ListenerUtil.mutListener.listen(15161) ? (delay - 2) : (ListenerUtil.mutListener.listen(15160) ? (delay + 2) : (delay / 2))))))))))) * fct)))))), conf, ivl2);
        int ivl4 = _constrainedIvl((int) ((ListenerUtil.mutListener.listen(15183) ? ((ListenerUtil.mutListener.listen(15179) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(15178) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(15177) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(15176) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) % conf.getDouble("ease4")) : (ListenerUtil.mutListener.listen(15182) ? ((ListenerUtil.mutListener.listen(15179) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(15178) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(15177) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(15176) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) / conf.getDouble("ease4")) : (ListenerUtil.mutListener.listen(15181) ? ((ListenerUtil.mutListener.listen(15179) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(15178) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(15177) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(15176) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) - conf.getDouble("ease4")) : (ListenerUtil.mutListener.listen(15180) ? ((ListenerUtil.mutListener.listen(15179) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(15178) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(15177) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(15176) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) + conf.getDouble("ease4")) : ((ListenerUtil.mutListener.listen(15179) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) % fct) : (ListenerUtil.mutListener.listen(15178) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) / fct) : (ListenerUtil.mutListener.listen(15177) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) - fct) : (ListenerUtil.mutListener.listen(15176) ? (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) + fct) : (((ListenerUtil.mutListener.listen(15175) ? (card.getIvl() % delay) : (ListenerUtil.mutListener.listen(15174) ? (card.getIvl() / delay) : (ListenerUtil.mutListener.listen(15173) ? (card.getIvl() * delay) : (ListenerUtil.mutListener.listen(15172) ? (card.getIvl() - delay) : (card.getIvl() + delay)))))) * fct))))) * conf.getDouble("ease4"))))))), conf, ivl3);
        if (!ListenerUtil.mutListener.listen(15202)) {
            if ((ListenerUtil.mutListener.listen(15188) ? (ease >= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15187) ? (ease <= Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15186) ? (ease > Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15185) ? (ease < Consts.BUTTON_TWO) : (ListenerUtil.mutListener.listen(15184) ? (ease != Consts.BUTTON_TWO) : (ease == Consts.BUTTON_TWO))))))) {
                if (!ListenerUtil.mutListener.listen(15201)) {
                    interval = ivl2;
                }
            } else if ((ListenerUtil.mutListener.listen(15193) ? (ease >= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15192) ? (ease <= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15191) ? (ease > Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15190) ? (ease < Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15189) ? (ease != Consts.BUTTON_THREE) : (ease == Consts.BUTTON_THREE))))))) {
                if (!ListenerUtil.mutListener.listen(15200)) {
                    interval = ivl3;
                }
            } else if ((ListenerUtil.mutListener.listen(15198) ? (ease >= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(15197) ? (ease <= Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(15196) ? (ease > Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(15195) ? (ease < Consts.BUTTON_FOUR) : (ListenerUtil.mutListener.listen(15194) ? (ease != Consts.BUTTON_FOUR) : (ease == Consts.BUTTON_FOUR))))))) {
                if (!ListenerUtil.mutListener.listen(15199)) {
                    interval = ivl4;
                }
            }
        }
        // interval capped?
        return Math.min(interval, conf.getInt("maxIvl"));
    }

    /**
     * Integer interval after interval factor and prev+1 constraints applied
     */
    private int _constrainedIvl(int ivl, @NonNull JSONObject conf, double prev) {
        double newIvl = (ListenerUtil.mutListener.listen(15206) ? (ivl % conf.optDouble("ivlFct", 1.0)) : (ListenerUtil.mutListener.listen(15205) ? (ivl / conf.optDouble("ivlFct", 1.0)) : (ListenerUtil.mutListener.listen(15204) ? (ivl - conf.optDouble("ivlFct", 1.0)) : (ListenerUtil.mutListener.listen(15203) ? (ivl + conf.optDouble("ivlFct", 1.0)) : (ivl * conf.optDouble("ivlFct", 1.0))))));
        return (int) Math.max(newIvl, (ListenerUtil.mutListener.listen(15210) ? (prev % 1) : (ListenerUtil.mutListener.listen(15209) ? (prev / 1) : (ListenerUtil.mutListener.listen(15208) ? (prev * 1) : (ListenerUtil.mutListener.listen(15207) ? (prev - 1) : (prev + 1))))));
    }

    @Override
    protected void _updateRevIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        try {
            int idealIvl = _nextRevIvl(card, ease);
            JSONObject conf = _revConf(card);
            if (!ListenerUtil.mutListener.listen(15215)) {
                card.setIvl(Math.min(Math.max(_adjRevIvl(card, idealIvl), (ListenerUtil.mutListener.listen(15214) ? (card.getIvl() % 1) : (ListenerUtil.mutListener.listen(15213) ? (card.getIvl() / 1) : (ListenerUtil.mutListener.listen(15212) ? (card.getIvl() * 1) : (ListenerUtil.mutListener.listen(15211) ? (card.getIvl() - 1) : (card.getIvl() + 1)))))), conf.getInt("maxIvl")));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // it's unused upstream as well
    @SuppressWarnings("PMD.UnusedFormalParameter")
    private int _adjRevIvl(@NonNull Card card, int idealIvl) {
        if (!ListenerUtil.mutListener.listen(15216)) {
            idealIvl = _fuzzedIvl(idealIvl);
        }
        return idealIvl;
    }

    /* Rebuild a dynamic deck. */
    @Override
    public void rebuildDyn() {
        if (!ListenerUtil.mutListener.listen(15217)) {
            rebuildDyn(0);
        }
    }

    @Override
    public void rebuildDyn(long did) {
        if (!ListenerUtil.mutListener.listen(15224)) {
            if ((ListenerUtil.mutListener.listen(15222) ? (did >= 0) : (ListenerUtil.mutListener.listen(15221) ? (did <= 0) : (ListenerUtil.mutListener.listen(15220) ? (did > 0) : (ListenerUtil.mutListener.listen(15219) ? (did < 0) : (ListenerUtil.mutListener.listen(15218) ? (did != 0) : (did == 0))))))) {
                if (!ListenerUtil.mutListener.listen(15223)) {
                    did = mCol.getDecks().selected();
                }
            }
        }
        Deck deck = mCol.getDecks().get(did);
        if (!ListenerUtil.mutListener.listen(15226)) {
            if (deck.getInt("dyn") == DECK_STD) {
                if (!ListenerUtil.mutListener.listen(15225)) {
                    Timber.e("error: deck is not a filtered deck");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15227)) {
            // move any existing cards back first, then fill
            emptyDyn(did);
        }
        List<Long> ids = _fillDyn(deck);
        if (!ListenerUtil.mutListener.listen(15228)) {
            if (ids.isEmpty()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15229)) {
            // and change to our new deck
            mCol.getDecks().select(did);
        }
    }

    private List<Long> _fillDyn(@NonNull Deck deck) {
        JSONArray terms = deck.getJSONArray("terms").getJSONArray(0);
        String search = terms.getString(0);
        int limit = terms.getInt(1);
        int order = terms.getInt(2);
        String orderlimit = _dynOrder(order, limit);
        if (!ListenerUtil.mutListener.listen(15231)) {
            if (!TextUtils.isEmpty(search.trim())) {
                if (!ListenerUtil.mutListener.listen(15230)) {
                    search = String.format(Locale.US, "(%s)", search);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15232)) {
            search = String.format(Locale.US, "%s -is:suspended -is:buried -deck:filtered -is:learn", search);
        }
        List<Long> ids = mCol.findCards(search, orderlimit);
        if (!ListenerUtil.mutListener.listen(15233)) {
            if (ids.isEmpty()) {
                return ids;
            }
        }
        if (!ListenerUtil.mutListener.listen(15234)) {
            // move the cards over
            mCol.log(deck.getLong("id"), ids);
        }
        if (!ListenerUtil.mutListener.listen(15235)) {
            _moveToDyn(deck.getLong("id"), ids);
        }
        return ids;
    }

    @Override
    public void emptyDyn(long did, String lim) {
        if (!ListenerUtil.mutListener.listen(15237)) {
            if (lim == null) {
                if (!ListenerUtil.mutListener.listen(15236)) {
                    lim = "did = " + did;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15238)) {
            mCol.log(mCol.getDb().queryLongList("select id from cards where " + lim));
        }
        if (!ListenerUtil.mutListener.listen(15239)) {
            // move out of cram queue
            mCol.getDb().execute("update cards set did = odid, queue = (case when type = " + Consts.CARD_TYPE_LRN + " then " + Consts.QUEUE_TYPE_NEW + " " + "else type end), type = (case when type = " + Consts.CARD_TYPE_LRN + " then " + Consts.CARD_TYPE_NEW + " else type end), " + "due = odue, odue = 0, odid = 0, usn = ? where " + lim, mCol.usn());
        }
    }

    private void _moveToDyn(long did, @NonNull List<Long> ids) {
        ArrayList<Object[]> data = new ArrayList<>(ids.size());
        // long t = getTime().intTime(); // unused variable present (and unused) upstream
        int u = mCol.usn();
        if (!ListenerUtil.mutListener.listen(15250)) {
            {
                long _loopCounter296 = 0;
                for (long c = 0; (ListenerUtil.mutListener.listen(15249) ? (c >= ids.size()) : (ListenerUtil.mutListener.listen(15248) ? (c <= ids.size()) : (ListenerUtil.mutListener.listen(15247) ? (c > ids.size()) : (ListenerUtil.mutListener.listen(15246) ? (c != ids.size()) : (ListenerUtil.mutListener.listen(15245) ? (c == ids.size()) : (c < ids.size())))))); c++) {
                    ListenerUtil.loopListener.listen("_loopCounter296", ++_loopCounter296);
                    if (!ListenerUtil.mutListener.listen(15244)) {
                        // start at -100000 so that reviews are all due
                        data.add(new Object[] { did, (ListenerUtil.mutListener.listen(15243) ? (-100000 % c) : (ListenerUtil.mutListener.listen(15242) ? (-100000 / c) : (ListenerUtil.mutListener.listen(15241) ? (-100000 * c) : (ListenerUtil.mutListener.listen(15240) ? (-100000 - c) : (-100000 + c))))), u, ids.get((int) c) });
                    }
                }
            }
        }
        // due reviews stay in the review queue. careful: can't use "odid or did", as sqlite converts to boolean
        String queue = "(CASE WHEN type = " + Consts.CARD_TYPE_REV + " AND (CASE WHEN odue THEN odue <= " + mToday + " ELSE due <= " + mToday + " END) THEN " + Consts.QUEUE_TYPE_REV + " ELSE " + Consts.QUEUE_TYPE_NEW + " END)";
        if (!ListenerUtil.mutListener.listen(15251)) {
            mCol.getDb().executeMany("UPDATE cards SET odid = (CASE WHEN odid THEN odid ELSE did END), " + "odue = (CASE WHEN odue THEN odue ELSE due END), did = ?, queue = " + queue + ", due = ?, usn = ? WHERE id = ?", data);
        }
    }

    private int _dynIvlBoost(@NonNull Card card) {
        if (!ListenerUtil.mutListener.listen(15265)) {
            if ((ListenerUtil.mutListener.listen(15263) ? ((ListenerUtil.mutListener.listen(15257) ? (!card.isInDynamicDeck() && (ListenerUtil.mutListener.listen(15256) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15255) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15254) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15253) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15252) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV))))))) : (!card.isInDynamicDeck() || (ListenerUtil.mutListener.listen(15256) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15255) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15254) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15253) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15252) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV)))))))) && (ListenerUtil.mutListener.listen(15262) ? (card.getFactor() >= 0) : (ListenerUtil.mutListener.listen(15261) ? (card.getFactor() <= 0) : (ListenerUtil.mutListener.listen(15260) ? (card.getFactor() > 0) : (ListenerUtil.mutListener.listen(15259) ? (card.getFactor() < 0) : (ListenerUtil.mutListener.listen(15258) ? (card.getFactor() != 0) : (card.getFactor() == 0))))))) : ((ListenerUtil.mutListener.listen(15257) ? (!card.isInDynamicDeck() && (ListenerUtil.mutListener.listen(15256) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15255) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15254) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15253) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15252) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV))))))) : (!card.isInDynamicDeck() || (ListenerUtil.mutListener.listen(15256) ? (card.getType() >= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15255) ? (card.getType() <= Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15254) ? (card.getType() > Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15253) ? (card.getType() < Consts.CARD_TYPE_REV) : (ListenerUtil.mutListener.listen(15252) ? (card.getType() == Consts.CARD_TYPE_REV) : (card.getType() != Consts.CARD_TYPE_REV)))))))) || (ListenerUtil.mutListener.listen(15262) ? (card.getFactor() >= 0) : (ListenerUtil.mutListener.listen(15261) ? (card.getFactor() <= 0) : (ListenerUtil.mutListener.listen(15260) ? (card.getFactor() > 0) : (ListenerUtil.mutListener.listen(15259) ? (card.getFactor() < 0) : (ListenerUtil.mutListener.listen(15258) ? (card.getFactor() != 0) : (card.getFactor() == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(15264)) {
                    Timber.e("error: deck is not a filtered deck");
                }
                return 0;
            }
        }
        long elapsed = (ListenerUtil.mutListener.listen(15273) ? (card.getIvl() % ((ListenerUtil.mutListener.listen(15269) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(15268) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(15267) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(15266) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (ListenerUtil.mutListener.listen(15272) ? (card.getIvl() / ((ListenerUtil.mutListener.listen(15269) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(15268) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(15267) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(15266) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (ListenerUtil.mutListener.listen(15271) ? (card.getIvl() * ((ListenerUtil.mutListener.listen(15269) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(15268) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(15267) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(15266) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (ListenerUtil.mutListener.listen(15270) ? (card.getIvl() + ((ListenerUtil.mutListener.listen(15269) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(15268) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(15267) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(15266) ? (card.getODue() + mToday) : (card.getODue() - mToday))))))) : (card.getIvl() - ((ListenerUtil.mutListener.listen(15269) ? (card.getODue() % mToday) : (ListenerUtil.mutListener.listen(15268) ? (card.getODue() / mToday) : (ListenerUtil.mutListener.listen(15267) ? (card.getODue() * mToday) : (ListenerUtil.mutListener.listen(15266) ? (card.getODue() + mToday) : (card.getODue() - mToday)))))))))));
        double factor = (ListenerUtil.mutListener.listen(15285) ? (((ListenerUtil.mutListener.listen(15281) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) % 1.2) : (ListenerUtil.mutListener.listen(15280) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) / 1.2) : (ListenerUtil.mutListener.listen(15279) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) * 1.2) : (ListenerUtil.mutListener.listen(15278) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) - 1.2) : (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) + 1.2)))))) % 2.0) : (ListenerUtil.mutListener.listen(15284) ? (((ListenerUtil.mutListener.listen(15281) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) % 1.2) : (ListenerUtil.mutListener.listen(15280) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) / 1.2) : (ListenerUtil.mutListener.listen(15279) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) * 1.2) : (ListenerUtil.mutListener.listen(15278) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) - 1.2) : (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) + 1.2)))))) * 2.0) : (ListenerUtil.mutListener.listen(15283) ? (((ListenerUtil.mutListener.listen(15281) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) % 1.2) : (ListenerUtil.mutListener.listen(15280) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) / 1.2) : (ListenerUtil.mutListener.listen(15279) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) * 1.2) : (ListenerUtil.mutListener.listen(15278) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) - 1.2) : (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) + 1.2)))))) - 2.0) : (ListenerUtil.mutListener.listen(15282) ? (((ListenerUtil.mutListener.listen(15281) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) % 1.2) : (ListenerUtil.mutListener.listen(15280) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) / 1.2) : (ListenerUtil.mutListener.listen(15279) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) * 1.2) : (ListenerUtil.mutListener.listen(15278) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) - 1.2) : (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) + 1.2)))))) + 2.0) : (((ListenerUtil.mutListener.listen(15281) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) % 1.2) : (ListenerUtil.mutListener.listen(15280) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) / 1.2) : (ListenerUtil.mutListener.listen(15279) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) * 1.2) : (ListenerUtil.mutListener.listen(15278) ? (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) - 1.2) : (((ListenerUtil.mutListener.listen(15277) ? (card.getFactor() % 1000.0) : (ListenerUtil.mutListener.listen(15276) ? (card.getFactor() * 1000.0) : (ListenerUtil.mutListener.listen(15275) ? (card.getFactor() - 1000.0) : (ListenerUtil.mutListener.listen(15274) ? (card.getFactor() + 1000.0) : (card.getFactor() / 1000.0)))))) + 1.2)))))) / 2.0)))));
        int ivl = Math.max(1, Math.max(card.getIvl(), (int) ((ListenerUtil.mutListener.listen(15289) ? (elapsed % factor) : (ListenerUtil.mutListener.listen(15288) ? (elapsed / factor) : (ListenerUtil.mutListener.listen(15287) ? (elapsed - factor) : (ListenerUtil.mutListener.listen(15286) ? (elapsed + factor) : (elapsed * factor))))))));
        JSONObject conf = _revConf(card);
        return Math.min(conf.getInt("maxIvl"), ivl);
    }

    /**
     * Leech handler. True if card was a leech.
     */
    @Override
    protected boolean _checkLeech(@NonNull Card card, @NonNull JSONObject conf) {
        int lf = conf.getInt("leechFails");
        if (!ListenerUtil.mutListener.listen(15295)) {
            if ((ListenerUtil.mutListener.listen(15294) ? (lf >= 0) : (ListenerUtil.mutListener.listen(15293) ? (lf <= 0) : (ListenerUtil.mutListener.listen(15292) ? (lf > 0) : (ListenerUtil.mutListener.listen(15291) ? (lf < 0) : (ListenerUtil.mutListener.listen(15290) ? (lf != 0) : (lf == 0))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(15341)) {
            // if over threshold or every half threshold reps after that
            if ((ListenerUtil.mutListener.listen(15318) ? ((ListenerUtil.mutListener.listen(15300) ? (card.getLapses() <= lf) : (ListenerUtil.mutListener.listen(15299) ? (card.getLapses() > lf) : (ListenerUtil.mutListener.listen(15298) ? (card.getLapses() < lf) : (ListenerUtil.mutListener.listen(15297) ? (card.getLapses() != lf) : (ListenerUtil.mutListener.listen(15296) ? (card.getLapses() == lf) : (card.getLapses() >= lf)))))) || (ListenerUtil.mutListener.listen(15317) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) >= 0) : (ListenerUtil.mutListener.listen(15316) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) <= 0) : (ListenerUtil.mutListener.listen(15315) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) > 0) : (ListenerUtil.mutListener.listen(15314) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) < 0) : (ListenerUtil.mutListener.listen(15313) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) != 0) : ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) == 0))))))) : ((ListenerUtil.mutListener.listen(15300) ? (card.getLapses() <= lf) : (ListenerUtil.mutListener.listen(15299) ? (card.getLapses() > lf) : (ListenerUtil.mutListener.listen(15298) ? (card.getLapses() < lf) : (ListenerUtil.mutListener.listen(15297) ? (card.getLapses() != lf) : (ListenerUtil.mutListener.listen(15296) ? (card.getLapses() == lf) : (card.getLapses() >= lf)))))) && (ListenerUtil.mutListener.listen(15317) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) >= 0) : (ListenerUtil.mutListener.listen(15316) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) <= 0) : (ListenerUtil.mutListener.listen(15315) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) > 0) : (ListenerUtil.mutListener.listen(15314) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) < 0) : (ListenerUtil.mutListener.listen(15313) ? ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) != 0) : ((ListenerUtil.mutListener.listen(15312) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) / Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15311) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) * Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15310) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) - Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (ListenerUtil.mutListener.listen(15309) ? (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) + Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)) : (((ListenerUtil.mutListener.listen(15304) ? (card.getLapses() % lf) : (ListenerUtil.mutListener.listen(15303) ? (card.getLapses() / lf) : (ListenerUtil.mutListener.listen(15302) ? (card.getLapses() * lf) : (ListenerUtil.mutListener.listen(15301) ? (card.getLapses() + lf) : (card.getLapses() - lf)))))) % Math.max((ListenerUtil.mutListener.listen(15308) ? (lf % 2) : (ListenerUtil.mutListener.listen(15307) ? (lf * 2) : (ListenerUtil.mutListener.listen(15306) ? (lf - 2) : (ListenerUtil.mutListener.listen(15305) ? (lf + 2) : (lf / 2))))), 1)))))) == 0))))))))) {
                // add a leech tag
                Note n = card.note();
                if (!ListenerUtil.mutListener.listen(15319)) {
                    n.addTag("leech");
                }
                if (!ListenerUtil.mutListener.listen(15320)) {
                    n.flush();
                }
                if (!ListenerUtil.mutListener.listen(15338)) {
                    // handle
                    if ((ListenerUtil.mutListener.listen(15325) ? (conf.getInt("leechAction") >= Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(15324) ? (conf.getInt("leechAction") <= Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(15323) ? (conf.getInt("leechAction") > Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(15322) ? (conf.getInt("leechAction") < Consts.LEECH_SUSPEND) : (ListenerUtil.mutListener.listen(15321) ? (conf.getInt("leechAction") != Consts.LEECH_SUSPEND) : (conf.getInt("leechAction") == Consts.LEECH_SUSPEND))))))) {
                        if (!ListenerUtil.mutListener.listen(15332)) {
                            // if it has an old due, remove it from cram/relearning
                            if ((ListenerUtil.mutListener.listen(15330) ? (card.getODue() >= 0) : (ListenerUtil.mutListener.listen(15329) ? (card.getODue() <= 0) : (ListenerUtil.mutListener.listen(15328) ? (card.getODue() > 0) : (ListenerUtil.mutListener.listen(15327) ? (card.getODue() < 0) : (ListenerUtil.mutListener.listen(15326) ? (card.getODue() == 0) : (card.getODue() != 0))))))) {
                                if (!ListenerUtil.mutListener.listen(15331)) {
                                    card.setDue(card.getODue());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(15334)) {
                            if (card.isInDynamicDeck()) {
                                if (!ListenerUtil.mutListener.listen(15333)) {
                                    card.setDid(card.getODid());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(15335)) {
                            card.setODue(0);
                        }
                        if (!ListenerUtil.mutListener.listen(15336)) {
                            card.setODid(0);
                        }
                        if (!ListenerUtil.mutListener.listen(15337)) {
                            card.setQueue(Consts.QUEUE_TYPE_SUSPENDED);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(15340)) {
                    // notify UI
                    if (mContextReference != null) {
                        Activity context = mContextReference.get();
                        if (!ListenerUtil.mutListener.listen(15339)) {
                            leech(card, context);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    @NonNull
    protected JSONObject _newConf(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        if (!ListenerUtil.mutListener.listen(15342)) {
            if (!card.isInDynamicDeck()) {
                return conf.getJSONObject("new");
            }
        }
        // dynamic deck; override some attributes, use original deck for others
        DeckConfig oconf = mCol.getDecks().confForDid(card.getODid());
        JSONArray delays = conf.optJSONArray("delays");
        if (!ListenerUtil.mutListener.listen(15344)) {
            if (delays == null) {
                if (!ListenerUtil.mutListener.listen(15343)) {
                    delays = oconf.getJSONObject("new").getJSONArray("delays");
                }
            }
        }
        JSONObject dict = new JSONObject();
        if (!ListenerUtil.mutListener.listen(15345)) {
            // original deck
            dict.put("ints", oconf.getJSONObject("new").getJSONArray("ints"));
        }
        if (!ListenerUtil.mutListener.listen(15346)) {
            dict.put("initialFactor", oconf.getJSONObject("new").getInt("initialFactor"));
        }
        if (!ListenerUtil.mutListener.listen(15347)) {
            dict.put("bury", oconf.getJSONObject("new").optBoolean("bury", true));
        }
        if (!ListenerUtil.mutListener.listen(15348)) {
            // overrides
            dict.put("delays", delays);
        }
        if (!ListenerUtil.mutListener.listen(15349)) {
            dict.put("separate", conf.getBoolean("separate"));
        }
        if (!ListenerUtil.mutListener.listen(15350)) {
            dict.put("order", Consts.NEW_CARDS_DUE);
        }
        if (!ListenerUtil.mutListener.listen(15351)) {
            dict.put("perDay", mReportLimit);
        }
        return dict;
    }

    @Override
    @NonNull
    protected JSONObject _lapseConf(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        if (!ListenerUtil.mutListener.listen(15352)) {
            if (!card.isInDynamicDeck()) {
                return conf.getJSONObject("lapse");
            }
        }
        // dynamic deck; override some attributes, use original deck for others
        DeckConfig oconf = mCol.getDecks().confForDid(card.getODid());
        JSONArray delays = conf.optJSONArray("delays");
        if (!ListenerUtil.mutListener.listen(15354)) {
            if (delays == null) {
                if (!ListenerUtil.mutListener.listen(15353)) {
                    delays = oconf.getJSONObject("lapse").getJSONArray("delays");
                }
            }
        }
        JSONObject dict = new JSONObject();
        if (!ListenerUtil.mutListener.listen(15355)) {
            // original deck
            dict.put("minInt", oconf.getJSONObject("lapse").getInt("minInt"));
        }
        if (!ListenerUtil.mutListener.listen(15356)) {
            dict.put("leechFails", oconf.getJSONObject("lapse").getInt("leechFails"));
        }
        if (!ListenerUtil.mutListener.listen(15357)) {
            dict.put("leechAction", oconf.getJSONObject("lapse").getInt("leechAction"));
        }
        if (!ListenerUtil.mutListener.listen(15358)) {
            dict.put("mult", oconf.getJSONObject("lapse").getDouble("mult"));
        }
        if (!ListenerUtil.mutListener.listen(15359)) {
            // overrides
            dict.put("delays", delays);
        }
        if (!ListenerUtil.mutListener.listen(15360)) {
            dict.put("resched", conf.getBoolean("resched"));
        }
        return dict;
    }

    private boolean _resched(@NonNull Card card) {
        DeckConfig conf = _cardConf(card);
        if (!ListenerUtil.mutListener.listen(15361)) {
            if (conf.getInt("dyn") == DECK_STD) {
                return true;
            }
        }
        return conf.getBoolean("resched");
    }

    @Override
    public void _updateCutoff() {
        Integer oldToday = mToday;
        if (!ListenerUtil.mutListener.listen(15370)) {
            // days since col created
            mToday = (int) ((ListenerUtil.mutListener.listen(15369) ? (((ListenerUtil.mutListener.listen(15365) ? (getTime().intTime() % mCol.getCrt()) : (ListenerUtil.mutListener.listen(15364) ? (getTime().intTime() / mCol.getCrt()) : (ListenerUtil.mutListener.listen(15363) ? (getTime().intTime() * mCol.getCrt()) : (ListenerUtil.mutListener.listen(15362) ? (getTime().intTime() + mCol.getCrt()) : (getTime().intTime() - mCol.getCrt())))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15368) ? (((ListenerUtil.mutListener.listen(15365) ? (getTime().intTime() % mCol.getCrt()) : (ListenerUtil.mutListener.listen(15364) ? (getTime().intTime() / mCol.getCrt()) : (ListenerUtil.mutListener.listen(15363) ? (getTime().intTime() * mCol.getCrt()) : (ListenerUtil.mutListener.listen(15362) ? (getTime().intTime() + mCol.getCrt()) : (getTime().intTime() - mCol.getCrt())))))) * SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15367) ? (((ListenerUtil.mutListener.listen(15365) ? (getTime().intTime() % mCol.getCrt()) : (ListenerUtil.mutListener.listen(15364) ? (getTime().intTime() / mCol.getCrt()) : (ListenerUtil.mutListener.listen(15363) ? (getTime().intTime() * mCol.getCrt()) : (ListenerUtil.mutListener.listen(15362) ? (getTime().intTime() + mCol.getCrt()) : (getTime().intTime() - mCol.getCrt())))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15366) ? (((ListenerUtil.mutListener.listen(15365) ? (getTime().intTime() % mCol.getCrt()) : (ListenerUtil.mutListener.listen(15364) ? (getTime().intTime() / mCol.getCrt()) : (ListenerUtil.mutListener.listen(15363) ? (getTime().intTime() * mCol.getCrt()) : (ListenerUtil.mutListener.listen(15362) ? (getTime().intTime() + mCol.getCrt()) : (getTime().intTime() - mCol.getCrt())))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15365) ? (getTime().intTime() % mCol.getCrt()) : (ListenerUtil.mutListener.listen(15364) ? (getTime().intTime() / mCol.getCrt()) : (ListenerUtil.mutListener.listen(15363) ? (getTime().intTime() * mCol.getCrt()) : (ListenerUtil.mutListener.listen(15362) ? (getTime().intTime() + mCol.getCrt()) : (getTime().intTime() - mCol.getCrt())))))) / SECONDS_PER_DAY))))));
        }
        if (!ListenerUtil.mutListener.listen(15383)) {
            // end of day cutoff
            mDayCutoff = (ListenerUtil.mutListener.listen(15382) ? (mCol.getCrt() % ((ListenerUtil.mutListener.listen(15378) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15377) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15376) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15375) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15381) ? (mCol.getCrt() / ((ListenerUtil.mutListener.listen(15378) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15377) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15376) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15375) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15380) ? (mCol.getCrt() * ((ListenerUtil.mutListener.listen(15378) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15377) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15376) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15375) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15379) ? (mCol.getCrt() - ((ListenerUtil.mutListener.listen(15378) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15377) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15376) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15375) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) * SECONDS_PER_DAY))))))) : (mCol.getCrt() + ((ListenerUtil.mutListener.listen(15378) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15377) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15376) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15375) ? (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) + SECONDS_PER_DAY) : (((ListenerUtil.mutListener.listen(15374) ? (mToday % 1) : (ListenerUtil.mutListener.listen(15373) ? (mToday / 1) : (ListenerUtil.mutListener.listen(15372) ? (mToday * 1) : (ListenerUtil.mutListener.listen(15371) ? (mToday - 1) : (mToday + 1)))))) * SECONDS_PER_DAY)))))))))));
        }
        if (!ListenerUtil.mutListener.listen(15385)) {
            if (!mToday.equals(oldToday)) {
                if (!ListenerUtil.mutListener.listen(15384)) {
                    mCol.log(mToday, mDayCutoff);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(15387)) {
            {
                long _loopCounter297 = 0;
                // instead
                for (Deck deck : mCol.getDecks().all()) {
                    ListenerUtil.loopListener.listen("_loopCounter297", ++_loopCounter297);
                    if (!ListenerUtil.mutListener.listen(15386)) {
                        update(deck);
                    }
                }
            }
        }
        // unbury if the day has rolled over
        int unburied = mCol.getConf().optInt("lastUnburied", 0);
        if (!ListenerUtil.mutListener.listen(15394)) {
            if ((ListenerUtil.mutListener.listen(15392) ? (unburied >= mToday) : (ListenerUtil.mutListener.listen(15391) ? (unburied <= mToday) : (ListenerUtil.mutListener.listen(15390) ? (unburied > mToday) : (ListenerUtil.mutListener.listen(15389) ? (unburied != mToday) : (ListenerUtil.mutListener.listen(15388) ? (unburied == mToday) : (unburied < mToday))))))) {
                if (!ListenerUtil.mutListener.listen(15393)) {
                    SyncStatus.ignoreDatabaseModification(this::unburyCards);
                }
            }
        }
    }

    @Override
    public boolean haveBuried() {
        return haveBuried(mCol.getDecks().active());
    }

    private boolean haveBuried(@NonNull List<Long> allDecks) {
        // Refactored to allow querying an arbitrary deck
        String sdids = Utils.ids2str(allDecks);
        int cnt = mCol.getDb().queryScalar("select 1 from cards where " + queueIsBuriedSnippet() + " and did in " + sdids + " limit 1");
        return (ListenerUtil.mutListener.listen(15399) ? (cnt >= 0) : (ListenerUtil.mutListener.listen(15398) ? (cnt <= 0) : (ListenerUtil.mutListener.listen(15397) ? (cnt > 0) : (ListenerUtil.mutListener.listen(15396) ? (cnt < 0) : (ListenerUtil.mutListener.listen(15395) ? (cnt == 0) : (cnt != 0))))));
    }

    /**
     * Return the next interval for CARD, in seconds.
     */
    @Override
    protected long nextIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if ((ListenerUtil.mutListener.listen(15416) ? ((ListenerUtil.mutListener.listen(15410) ? ((ListenerUtil.mutListener.listen(15404) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15403) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15402) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15401) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15400) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(15409) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15408) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15407) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15406) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15405) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN))))))) : ((ListenerUtil.mutListener.listen(15404) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15403) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15402) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15401) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15400) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(15409) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15408) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15407) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15406) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15405) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))))) && (ListenerUtil.mutListener.listen(15415) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15414) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15413) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15412) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15411) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))) : ((ListenerUtil.mutListener.listen(15410) ? ((ListenerUtil.mutListener.listen(15404) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15403) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15402) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15401) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15400) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) && (ListenerUtil.mutListener.listen(15409) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15408) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15407) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15406) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15405) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN))))))) : ((ListenerUtil.mutListener.listen(15404) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15403) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15402) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15401) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15400) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW)))))) || (ListenerUtil.mutListener.listen(15409) ? (card.getQueue() >= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15408) ? (card.getQueue() <= Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15407) ? (card.getQueue() > Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15406) ? (card.getQueue() < Consts.QUEUE_TYPE_LRN) : (ListenerUtil.mutListener.listen(15405) ? (card.getQueue() != Consts.QUEUE_TYPE_LRN) : (card.getQueue() == Consts.QUEUE_TYPE_LRN)))))))) || (ListenerUtil.mutListener.listen(15415) ? (card.getQueue() >= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15414) ? (card.getQueue() <= Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15413) ? (card.getQueue() > Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15412) ? (card.getQueue() < Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (ListenerUtil.mutListener.listen(15411) ? (card.getQueue() != Consts.QUEUE_TYPE_DAY_LEARN_RELEARN) : (card.getQueue() == Consts.QUEUE_TYPE_DAY_LEARN_RELEARN))))))))) {
            return _nextLrnIvl(card, ease);
        } else if ((ListenerUtil.mutListener.listen(15421) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15420) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15419) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15418) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15417) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
            // lapsed
            JSONObject conf = _lapseConf(card);
            if ((ListenerUtil.mutListener.listen(15430) ? (conf.getJSONArray("delays").length() >= 0) : (ListenerUtil.mutListener.listen(15429) ? (conf.getJSONArray("delays").length() <= 0) : (ListenerUtil.mutListener.listen(15428) ? (conf.getJSONArray("delays").length() < 0) : (ListenerUtil.mutListener.listen(15427) ? (conf.getJSONArray("delays").length() != 0) : (ListenerUtil.mutListener.listen(15426) ? (conf.getJSONArray("delays").length() == 0) : (conf.getJSONArray("delays").length() > 0))))))) {
                return (long) ((ListenerUtil.mutListener.listen(15434) ? (conf.getJSONArray("delays").getDouble(0) % 60.0) : (ListenerUtil.mutListener.listen(15433) ? (conf.getJSONArray("delays").getDouble(0) / 60.0) : (ListenerUtil.mutListener.listen(15432) ? (conf.getJSONArray("delays").getDouble(0) - 60.0) : (ListenerUtil.mutListener.listen(15431) ? (conf.getJSONArray("delays").getDouble(0) + 60.0) : (conf.getJSONArray("delays").getDouble(0) * 60.0))))));
            }
            return (ListenerUtil.mutListener.listen(15438) ? (_nextLapseIvl(card, conf) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15437) ? (_nextLapseIvl(card, conf) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15436) ? (_nextLapseIvl(card, conf) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15435) ? (_nextLapseIvl(card, conf) + SECONDS_PER_DAY) : (_nextLapseIvl(card, conf) * SECONDS_PER_DAY)))));
        } else {
            // review
            return (ListenerUtil.mutListener.listen(15425) ? (_nextRevIvl(card, ease) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15424) ? (_nextRevIvl(card, ease) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15423) ? (_nextRevIvl(card, ease) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15422) ? (_nextRevIvl(card, ease) + SECONDS_PER_DAY) : (_nextRevIvl(card, ease) * SECONDS_PER_DAY)))));
        }
    }

    @Override
    protected long _nextLrnIvl(@NonNull Card card, @Consts.BUTTON_TYPE int ease) {
        if (!ListenerUtil.mutListener.listen(15445)) {
            // this isn't easily extracted from the learn code
            if ((ListenerUtil.mutListener.listen(15443) ? (card.getQueue() >= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15442) ? (card.getQueue() <= Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15441) ? (card.getQueue() > Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15440) ? (card.getQueue() < Consts.QUEUE_TYPE_NEW) : (ListenerUtil.mutListener.listen(15439) ? (card.getQueue() != Consts.QUEUE_TYPE_NEW) : (card.getQueue() == Consts.QUEUE_TYPE_NEW))))))) {
                if (!ListenerUtil.mutListener.listen(15444)) {
                    card.setLeft(_startingLeft(card));
                }
            }
        }
        JSONObject conf = _lrnConf(card);
        if ((ListenerUtil.mutListener.listen(15450) ? (ease >= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15449) ? (ease <= Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15448) ? (ease > Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15447) ? (ease < Consts.BUTTON_ONE) : (ListenerUtil.mutListener.listen(15446) ? (ease != Consts.BUTTON_ONE) : (ease == Consts.BUTTON_ONE))))))) {
            // fail
            return _delayForGrade(conf, conf.getJSONArray("delays").length());
        } else if ((ListenerUtil.mutListener.listen(15455) ? (ease >= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15454) ? (ease <= Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15453) ? (ease > Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15452) ? (ease < Consts.BUTTON_THREE) : (ListenerUtil.mutListener.listen(15451) ? (ease != Consts.BUTTON_THREE) : (ease == Consts.BUTTON_THREE))))))) {
            // early removal
            if (!_resched(card)) {
                return 0;
            }
            return (ListenerUtil.mutListener.listen(15476) ? (_graduatingIvl(card, conf, true, false) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15475) ? (_graduatingIvl(card, conf, true, false) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15474) ? (_graduatingIvl(card, conf, true, false) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15473) ? (_graduatingIvl(card, conf, true, false) + SECONDS_PER_DAY) : (_graduatingIvl(card, conf, true, false) * SECONDS_PER_DAY)))));
        } else {
            int left = (ListenerUtil.mutListener.listen(15463) ? ((ListenerUtil.mutListener.listen(15459) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(15458) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(15457) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(15456) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) % 1) : (ListenerUtil.mutListener.listen(15462) ? ((ListenerUtil.mutListener.listen(15459) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(15458) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(15457) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(15456) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) / 1) : (ListenerUtil.mutListener.listen(15461) ? ((ListenerUtil.mutListener.listen(15459) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(15458) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(15457) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(15456) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) * 1) : (ListenerUtil.mutListener.listen(15460) ? ((ListenerUtil.mutListener.listen(15459) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(15458) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(15457) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(15456) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) + 1) : ((ListenerUtil.mutListener.listen(15459) ? (card.getLeft() / 1000) : (ListenerUtil.mutListener.listen(15458) ? (card.getLeft() * 1000) : (ListenerUtil.mutListener.listen(15457) ? (card.getLeft() - 1000) : (ListenerUtil.mutListener.listen(15456) ? (card.getLeft() + 1000) : (card.getLeft() % 1000))))) - 1)))));
            if ((ListenerUtil.mutListener.listen(15468) ? (left >= 0) : (ListenerUtil.mutListener.listen(15467) ? (left > 0) : (ListenerUtil.mutListener.listen(15466) ? (left < 0) : (ListenerUtil.mutListener.listen(15465) ? (left != 0) : (ListenerUtil.mutListener.listen(15464) ? (left == 0) : (left <= 0))))))) {
                // graduate
                if (!_resched(card)) {
                    return 0;
                }
                return (ListenerUtil.mutListener.listen(15472) ? (_graduatingIvl(card, conf, false, false) % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15471) ? (_graduatingIvl(card, conf, false, false) / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15470) ? (_graduatingIvl(card, conf, false, false) - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15469) ? (_graduatingIvl(card, conf, false, false) + SECONDS_PER_DAY) : (_graduatingIvl(card, conf, false, false) * SECONDS_PER_DAY)))));
            } else {
                return _delayForGrade(conf, left);
            }
        }
    }

    /**
     * Suspend cards.
     */
    @Override
    public void suspendCards(@NonNull long[] ids) {
        if (!ListenerUtil.mutListener.listen(15477)) {
            mCol.log(ids);
        }
        if (!ListenerUtil.mutListener.listen(15478)) {
            remFromDyn(ids);
        }
        if (!ListenerUtil.mutListener.listen(15479)) {
            removeLrn(ids);
        }
        if (!ListenerUtil.mutListener.listen(15480)) {
            mCol.getDb().execute("UPDATE cards SET queue = " + Consts.QUEUE_TYPE_SUSPENDED + ", mod = ?, usn = ? WHERE id IN " + Utils.ids2str(ids), getTime().intTime(), mCol.usn());
        }
    }

    @NonNull
    protected String queueIsBuriedSnippet() {
        return "queue = " + Consts.QUEUE_TYPE_SIBLING_BURIED;
    }

    @NonNull
    protected String _restoreQueueSnippet() {
        return "queue = type";
    }

    /**
     * Unsuspend cards
     */
    @Override
    public void buryCards(@NonNull long[] cids) {
        if (!ListenerUtil.mutListener.listen(15481)) {
            buryCards(cids, false);
        }
    }

    @Override
    public void buryCards(@NonNull long[] cids, boolean manual) {
        if (!ListenerUtil.mutListener.listen(15482)) {
            // The boolean is useless here. However, it ensures that we are override the method with same parameter in SchedV2.
            mCol.log(cids);
        }
        if (!ListenerUtil.mutListener.listen(15483)) {
            remFromDyn(cids);
        }
        if (!ListenerUtil.mutListener.listen(15484)) {
            removeLrn(cids);
        }
        if (!ListenerUtil.mutListener.listen(15485)) {
            mCol.getDb().execute("update cards set " + queueIsBuriedSnippet() + ",mod=?,usn=? where id in " + Utils.ids2str(cids), getTime().intTime(), mCol.usn());
        }
    }

    /*
     * ***********************************************************
     * The methods below are not in LibAnki.
     * ***********************************************************
     */
    @Override
    public boolean haveBuried(long did) {
        List<Long> all = new ArrayList<>(mCol.getDecks().children(did).values());
        if (!ListenerUtil.mutListener.listen(15486)) {
            all.add(did);
        }
        return haveBuried(all);
    }

    @Override
    public void unburyCardsForDeck(long did) {
        List<Long> all = new ArrayList<>(mCol.getDecks().children(did).values());
        if (!ListenerUtil.mutListener.listen(15487)) {
            all.add(did);
        }
        if (!ListenerUtil.mutListener.listen(15488)) {
            unburyCardsForDeck(all);
        }
    }

    /* Need to override. Otherwise it get SchedV2.mName variable*/
    @NonNull
    @Override
    public String getName() {
        return "std";
    }

    /**
     * Return an estimate, in minutes, for how long it will take to complete all the reps in {@code counts}.
     *
     * The estimator builds rates for each queue type by looking at 10 days of history from the revlog table. For
     * efficiency, and to maintain the same rates for a review session, the rates are cached and reused until a
     * reload is forced.
     *
     * Notes:
     * - Because the revlog table does not record deck IDs, the rates cannot be reduced to a single deck and thus cover
     * the whole collection which may be inaccurate for some decks.
     * - There is no efficient way to determine how many lrn cards are generated by each new card. This estimator
     * assumes 1 card is generated as a compromise.
     * - If there is no revlog data to work with, reasonable defaults are chosen as a compromise to predicting 0 minutes.
     *
     * @param counts An array of [new, lrn, rev] counts from the scheduler's counts() method.
     * @param reload Force rebuild of estimator rates using the revlog.
     */
    @Override
    public int eta(@NonNull Counts counts, boolean reload) {
        double newRate;
        double newTime;
        double revRate;
        double revTime;
        double relrnRate;
        double relrnTime;
        if ((ListenerUtil.mutListener.listen(15494) ? (reload && (ListenerUtil.mutListener.listen(15493) ? (mEtaCache[0] >= -1) : (ListenerUtil.mutListener.listen(15492) ? (mEtaCache[0] <= -1) : (ListenerUtil.mutListener.listen(15491) ? (mEtaCache[0] > -1) : (ListenerUtil.mutListener.listen(15490) ? (mEtaCache[0] < -1) : (ListenerUtil.mutListener.listen(15489) ? (mEtaCache[0] != -1) : (mEtaCache[0] == -1))))))) : (reload || (ListenerUtil.mutListener.listen(15493) ? (mEtaCache[0] >= -1) : (ListenerUtil.mutListener.listen(15492) ? (mEtaCache[0] <= -1) : (ListenerUtil.mutListener.listen(15491) ? (mEtaCache[0] > -1) : (ListenerUtil.mutListener.listen(15490) ? (mEtaCache[0] < -1) : (ListenerUtil.mutListener.listen(15489) ? (mEtaCache[0] != -1) : (mEtaCache[0] == -1))))))))) {
            try (Cursor cur = mCol.getDb().query("select " + "avg(case when type = " + Consts.CARD_TYPE_NEW + " then case when ease > 1 then 1.0 else 0.0 end else null end) as newRate, avg(case when type = " + Consts.CARD_TYPE_NEW + " then time else null end) as newTime, " + "avg(case when type in (" + Consts.CARD_TYPE_LRN + ", " + Consts.CARD_TYPE_RELEARNING + ") then case when ease > 1 then 1.0 else 0.0 end else null end) as revRate, avg(case when type in (" + Consts.CARD_TYPE_LRN + ", " + Consts.CARD_TYPE_RELEARNING + ") then time else null end) as revTime, " + "avg(case when type = " + Consts.CARD_TYPE_REV + " then case when ease > 1 then 1.0 else 0.0 end else null end) as relrnRate, avg(case when type = " + Consts.CARD_TYPE_REV + " then time else null end) as relrnTime " + "from revlog where id > " + ((ListenerUtil.mutListener.listen(15506) ? (((ListenerUtil.mutListener.listen(15502) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15501) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15500) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15499) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) % 1000) : (ListenerUtil.mutListener.listen(15505) ? (((ListenerUtil.mutListener.listen(15502) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15501) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15500) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15499) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) / 1000) : (ListenerUtil.mutListener.listen(15504) ? (((ListenerUtil.mutListener.listen(15502) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15501) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15500) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15499) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) - 1000) : (ListenerUtil.mutListener.listen(15503) ? (((ListenerUtil.mutListener.listen(15502) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15501) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15500) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15499) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) + 1000) : (((ListenerUtil.mutListener.listen(15502) ? (mCol.getSched().getDayCutoff() % ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15501) ? (mCol.getSched().getDayCutoff() / ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15500) ? (mCol.getSched().getDayCutoff() * ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (ListenerUtil.mutListener.listen(15499) ? (mCol.getSched().getDayCutoff() + ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY))))))) : (mCol.getSched().getDayCutoff() - ((ListenerUtil.mutListener.listen(15498) ? (10 % SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15497) ? (10 / SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15496) ? (10 - SECONDS_PER_DAY) : (ListenerUtil.mutListener.listen(15495) ? (10 + SECONDS_PER_DAY) : (10 * SECONDS_PER_DAY)))))))))))) * 1000)))))))) {
                if (!ListenerUtil.mutListener.listen(15507)) {
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
                if (!ListenerUtil.mutListener.listen(15509)) {
                    if (!cur.isClosed()) {
                        if (!ListenerUtil.mutListener.listen(15508)) {
                            cur.close();
                        }
                    }
                }
            }
            // If the collection has no revlog data to work with, assume a 20 second average rep for that type
            newTime = (ListenerUtil.mutListener.listen(15514) ? (newTime >= 0) : (ListenerUtil.mutListener.listen(15513) ? (newTime <= 0) : (ListenerUtil.mutListener.listen(15512) ? (newTime > 0) : (ListenerUtil.mutListener.listen(15511) ? (newTime < 0) : (ListenerUtil.mutListener.listen(15510) ? (newTime != 0) : (newTime == 0)))))) ? 20000 : newTime;
            revTime = (ListenerUtil.mutListener.listen(15519) ? (revTime >= 0) : (ListenerUtil.mutListener.listen(15518) ? (revTime <= 0) : (ListenerUtil.mutListener.listen(15517) ? (revTime > 0) : (ListenerUtil.mutListener.listen(15516) ? (revTime < 0) : (ListenerUtil.mutListener.listen(15515) ? (revTime != 0) : (revTime == 0)))))) ? 20000 : revTime;
            relrnTime = (ListenerUtil.mutListener.listen(15524) ? (relrnTime >= 0) : (ListenerUtil.mutListener.listen(15523) ? (relrnTime <= 0) : (ListenerUtil.mutListener.listen(15522) ? (relrnTime > 0) : (ListenerUtil.mutListener.listen(15521) ? (relrnTime < 0) : (ListenerUtil.mutListener.listen(15520) ? (relrnTime != 0) : (relrnTime == 0)))))) ? 20000 : relrnTime;
            // And a 100% success rate
            newRate = (ListenerUtil.mutListener.listen(15529) ? (newRate >= 0) : (ListenerUtil.mutListener.listen(15528) ? (newRate <= 0) : (ListenerUtil.mutListener.listen(15527) ? (newRate > 0) : (ListenerUtil.mutListener.listen(15526) ? (newRate < 0) : (ListenerUtil.mutListener.listen(15525) ? (newRate != 0) : (newRate == 0)))))) ? 1 : newRate;
            revRate = (ListenerUtil.mutListener.listen(15534) ? (revRate >= 0) : (ListenerUtil.mutListener.listen(15533) ? (revRate <= 0) : (ListenerUtil.mutListener.listen(15532) ? (revRate > 0) : (ListenerUtil.mutListener.listen(15531) ? (revRate < 0) : (ListenerUtil.mutListener.listen(15530) ? (revRate != 0) : (revRate == 0)))))) ? 1 : revRate;
            relrnRate = (ListenerUtil.mutListener.listen(15539) ? (relrnRate >= 0) : (ListenerUtil.mutListener.listen(15538) ? (relrnRate <= 0) : (ListenerUtil.mutListener.listen(15537) ? (relrnRate > 0) : (ListenerUtil.mutListener.listen(15536) ? (relrnRate < 0) : (ListenerUtil.mutListener.listen(15535) ? (relrnRate != 0) : (relrnRate == 0)))))) ? 1 : relrnRate;
            if (!ListenerUtil.mutListener.listen(15540)) {
                mEtaCache[0] = newRate;
            }
            if (!ListenerUtil.mutListener.listen(15541)) {
                mEtaCache[1] = newTime;
            }
            if (!ListenerUtil.mutListener.listen(15542)) {
                mEtaCache[2] = revRate;
            }
            if (!ListenerUtil.mutListener.listen(15543)) {
                mEtaCache[3] = revTime;
            }
            if (!ListenerUtil.mutListener.listen(15544)) {
                mEtaCache[4] = relrnRate;
            }
            if (!ListenerUtil.mutListener.listen(15545)) {
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
        double newTotal = (ListenerUtil.mutListener.listen(15549) ? (newTime % counts.getNew()) : (ListenerUtil.mutListener.listen(15548) ? (newTime / counts.getNew()) : (ListenerUtil.mutListener.listen(15547) ? (newTime - counts.getNew()) : (ListenerUtil.mutListener.listen(15546) ? (newTime + counts.getNew()) : (newTime * counts.getNew())))));
        double relrnTotal = (ListenerUtil.mutListener.listen(15553) ? (relrnTime % counts.getLrn()) : (ListenerUtil.mutListener.listen(15552) ? (relrnTime / counts.getLrn()) : (ListenerUtil.mutListener.listen(15551) ? (relrnTime - counts.getLrn()) : (ListenerUtil.mutListener.listen(15550) ? (relrnTime + counts.getLrn()) : (relrnTime * counts.getLrn())))));
        double revTotal = (ListenerUtil.mutListener.listen(15557) ? (revTime % counts.getRev()) : (ListenerUtil.mutListener.listen(15556) ? (revTime / counts.getRev()) : (ListenerUtil.mutListener.listen(15555) ? (revTime - counts.getRev()) : (ListenerUtil.mutListener.listen(15554) ? (revTime + counts.getRev()) : (revTime * counts.getRev())))));
        // Assume every new card becomes 1 relrn
        int toRelrn = counts.getNew();
        if (!ListenerUtil.mutListener.listen(15566)) {
            toRelrn += Math.ceil((ListenerUtil.mutListener.listen(15565) ? (((ListenerUtil.mutListener.listen(15561) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15560) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15559) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15558) ? (1 + relrnRate) : (1 - relrnRate)))))) % counts.getLrn()) : (ListenerUtil.mutListener.listen(15564) ? (((ListenerUtil.mutListener.listen(15561) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15560) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15559) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15558) ? (1 + relrnRate) : (1 - relrnRate)))))) / counts.getLrn()) : (ListenerUtil.mutListener.listen(15563) ? (((ListenerUtil.mutListener.listen(15561) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15560) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15559) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15558) ? (1 + relrnRate) : (1 - relrnRate)))))) - counts.getLrn()) : (ListenerUtil.mutListener.listen(15562) ? (((ListenerUtil.mutListener.listen(15561) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15560) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15559) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15558) ? (1 + relrnRate) : (1 - relrnRate)))))) + counts.getLrn()) : (((ListenerUtil.mutListener.listen(15561) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15560) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15559) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15558) ? (1 + relrnRate) : (1 - relrnRate)))))) * counts.getLrn()))))));
        }
        if (!ListenerUtil.mutListener.listen(15575)) {
            toRelrn += Math.ceil((ListenerUtil.mutListener.listen(15574) ? (((ListenerUtil.mutListener.listen(15570) ? (1 % revRate) : (ListenerUtil.mutListener.listen(15569) ? (1 / revRate) : (ListenerUtil.mutListener.listen(15568) ? (1 * revRate) : (ListenerUtil.mutListener.listen(15567) ? (1 + revRate) : (1 - revRate)))))) % counts.getRev()) : (ListenerUtil.mutListener.listen(15573) ? (((ListenerUtil.mutListener.listen(15570) ? (1 % revRate) : (ListenerUtil.mutListener.listen(15569) ? (1 / revRate) : (ListenerUtil.mutListener.listen(15568) ? (1 * revRate) : (ListenerUtil.mutListener.listen(15567) ? (1 + revRate) : (1 - revRate)))))) / counts.getRev()) : (ListenerUtil.mutListener.listen(15572) ? (((ListenerUtil.mutListener.listen(15570) ? (1 % revRate) : (ListenerUtil.mutListener.listen(15569) ? (1 / revRate) : (ListenerUtil.mutListener.listen(15568) ? (1 * revRate) : (ListenerUtil.mutListener.listen(15567) ? (1 + revRate) : (1 - revRate)))))) - counts.getRev()) : (ListenerUtil.mutListener.listen(15571) ? (((ListenerUtil.mutListener.listen(15570) ? (1 % revRate) : (ListenerUtil.mutListener.listen(15569) ? (1 / revRate) : (ListenerUtil.mutListener.listen(15568) ? (1 * revRate) : (ListenerUtil.mutListener.listen(15567) ? (1 + revRate) : (1 - revRate)))))) + counts.getRev()) : (((ListenerUtil.mutListener.listen(15570) ? (1 % revRate) : (ListenerUtil.mutListener.listen(15569) ? (1 / revRate) : (ListenerUtil.mutListener.listen(15568) ? (1 * revRate) : (ListenerUtil.mutListener.listen(15567) ? (1 + revRate) : (1 - revRate)))))) * counts.getRev()))))));
        }
        // negative for other reasons). 5% seems reasonable to ensure the loop doesn't iterate too much.
        relrnRate = Math.max(relrnRate, 0.05);
        int futureReps = 0;
        if (!ListenerUtil.mutListener.listen(15591)) {
            {
                long _loopCounter298 = 0;
                do {
                    ListenerUtil.loopListener.listen("_loopCounter298", ++_loopCounter298);
                    // Truncation ensures the failure rate always decreases
                    int failures = (int) ((ListenerUtil.mutListener.listen(15583) ? (((ListenerUtil.mutListener.listen(15579) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15578) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15577) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15576) ? (1 + relrnRate) : (1 - relrnRate)))))) % toRelrn) : (ListenerUtil.mutListener.listen(15582) ? (((ListenerUtil.mutListener.listen(15579) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15578) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15577) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15576) ? (1 + relrnRate) : (1 - relrnRate)))))) / toRelrn) : (ListenerUtil.mutListener.listen(15581) ? (((ListenerUtil.mutListener.listen(15579) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15578) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15577) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15576) ? (1 + relrnRate) : (1 - relrnRate)))))) - toRelrn) : (ListenerUtil.mutListener.listen(15580) ? (((ListenerUtil.mutListener.listen(15579) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15578) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15577) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15576) ? (1 + relrnRate) : (1 - relrnRate)))))) + toRelrn) : (((ListenerUtil.mutListener.listen(15579) ? (1 % relrnRate) : (ListenerUtil.mutListener.listen(15578) ? (1 / relrnRate) : (ListenerUtil.mutListener.listen(15577) ? (1 * relrnRate) : (ListenerUtil.mutListener.listen(15576) ? (1 + relrnRate) : (1 - relrnRate)))))) * toRelrn))))));
                    if (!ListenerUtil.mutListener.listen(15584)) {
                        futureReps += failures;
                    }
                    if (!ListenerUtil.mutListener.listen(15585)) {
                        toRelrn = failures;
                    }
                } while ((ListenerUtil.mutListener.listen(15590) ? (toRelrn >= 1) : (ListenerUtil.mutListener.listen(15589) ? (toRelrn <= 1) : (ListenerUtil.mutListener.listen(15588) ? (toRelrn < 1) : (ListenerUtil.mutListener.listen(15587) ? (toRelrn != 1) : (ListenerUtil.mutListener.listen(15586) ? (toRelrn == 1) : (toRelrn > 1)))))));
            }
        }
        double futureRelrnTotal = (ListenerUtil.mutListener.listen(15595) ? (relrnTime % futureReps) : (ListenerUtil.mutListener.listen(15594) ? (relrnTime / futureReps) : (ListenerUtil.mutListener.listen(15593) ? (relrnTime - futureReps) : (ListenerUtil.mutListener.listen(15592) ? (relrnTime + futureReps) : (relrnTime * futureReps)))));
        return (int) Math.round((ListenerUtil.mutListener.listen(15611) ? (((ListenerUtil.mutListener.listen(15607) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(15606) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(15605) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(15604) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) % 60000) : (ListenerUtil.mutListener.listen(15610) ? (((ListenerUtil.mutListener.listen(15607) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(15606) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(15605) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(15604) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) * 60000) : (ListenerUtil.mutListener.listen(15609) ? (((ListenerUtil.mutListener.listen(15607) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(15606) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(15605) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(15604) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) - 60000) : (ListenerUtil.mutListener.listen(15608) ? (((ListenerUtil.mutListener.listen(15607) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(15606) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(15605) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(15604) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) + 60000) : (((ListenerUtil.mutListener.listen(15607) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) % futureRelrnTotal) : (ListenerUtil.mutListener.listen(15606) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) / futureRelrnTotal) : (ListenerUtil.mutListener.listen(15605) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) * futureRelrnTotal) : (ListenerUtil.mutListener.listen(15604) ? ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) - futureRelrnTotal) : ((ListenerUtil.mutListener.listen(15603) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) % revTotal) : (ListenerUtil.mutListener.listen(15602) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) / revTotal) : (ListenerUtil.mutListener.listen(15601) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) * revTotal) : (ListenerUtil.mutListener.listen(15600) ? ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) - revTotal) : ((ListenerUtil.mutListener.listen(15599) ? (newTotal % relrnTotal) : (ListenerUtil.mutListener.listen(15598) ? (newTotal / relrnTotal) : (ListenerUtil.mutListener.listen(15597) ? (newTotal * relrnTotal) : (ListenerUtil.mutListener.listen(15596) ? (newTotal - relrnTotal) : (newTotal + relrnTotal))))) + revTotal))))) + futureRelrnTotal)))))) / 60000))))));
    }

    /**
     * This is used when card is currently in the reviewer, to adapt the counts by removing this card from it.
     *
     * @param discardCard A card sent to reviewer that should not be
     * counted.
     */
    @Override
    public void decrementCounts(@Nullable Card discardCard) {
        if (!ListenerUtil.mutListener.listen(15612)) {
            if (discardCard == null) {
                return;
            }
        }
        @Consts.CARD_QUEUE
        int type = discardCard.getQueue();
        if (!ListenerUtil.mutListener.listen(15621)) {
            switch(type) {
                case Consts.QUEUE_TYPE_NEW:
                    if (!ListenerUtil.mutListener.listen(15613)) {
                        mNewCount--;
                    }
                    break;
                case Consts.QUEUE_TYPE_LRN:
                    if (!ListenerUtil.mutListener.listen(15618)) {
                        mLrnCount -= (ListenerUtil.mutListener.listen(15617) ? (discardCard.getLeft() % 1000) : (ListenerUtil.mutListener.listen(15616) ? (discardCard.getLeft() * 1000) : (ListenerUtil.mutListener.listen(15615) ? (discardCard.getLeft() - 1000) : (ListenerUtil.mutListener.listen(15614) ? (discardCard.getLeft() + 1000) : (discardCard.getLeft() / 1000)))));
                    }
                    break;
                case Consts.QUEUE_TYPE_REV:
                    if (!ListenerUtil.mutListener.listen(15619)) {
                        mRevCount--;
                    }
                    break;
                case Consts.QUEUE_TYPE_DAY_LEARN_RELEARN:
                    if (!ListenerUtil.mutListener.listen(15620)) {
                        mLrnCount--;
                    }
                    break;
            }
        }
    }

    /**
     * The button to press on a new card to answer "good".
     */
    @Override
    @VisibleForTesting
    @Consts.BUTTON_TYPE
    public int getGoodNewButton() {
        return Consts.BUTTON_TWO;
    }
}
