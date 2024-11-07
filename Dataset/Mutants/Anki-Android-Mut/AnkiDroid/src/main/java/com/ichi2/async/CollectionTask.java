/**
 * *************************************************************************************
 *  Copyright (c) 2009 Daniel Svärd <daniel.svard@gmail.com>                             *
 *  Copyright (c) 2009 Edu Zamora <edu.zasu@gmail.com>                                   *
 *  Copyright (c) 2011 Norbert Nagold <norbert.nagold@gmail.com>                         *
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
package com.ichi2.async;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Pair;
import com.google.gson.stream.JsonReader;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.BackupManager;
import com.ichi2.anki.CardBrowser;
import com.ichi2.anki.CardUtils;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.R;
import com.ichi2.anki.TemporaryModel;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.anki.exception.ImportExportException;
import com.ichi2.libanki.Media;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Undoable;
import com.ichi2.libanki.WrongId;
import com.ichi2.libanki.sched.AbstractSched;
import com.ichi2.libanki.AnkiPackageExporter;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.DB;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.Storage;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.Deck;
import com.ichi2.libanki.importer.AnkiPackageImporter;
import com.ichi2.libanki.sched.Counts;
import com.ichi2.libanki.sched.DeckDueTreeNode;
import com.ichi2.libanki.sched.DeckTreeNode;
import com.ichi2.libanki.utils.Time;
import com.ichi2.utils.BooleanGetter;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import com.ichi2.utils.PairWithBoolean;
import com.ichi2.utils.PairWithCard;
import com.ichi2.utils.SyncStatus;
import com.ichi2.utils.ThreadUtil;
import com.ichi2.utils.Triple;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.compress.archivers.zip.ZipFile;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import timber.log.Timber;
import static com.ichi2.async.TaskManager.setLatestInstance;
import static com.ichi2.libanki.Card.deepCopyCardArray;
import static com.ichi2.libanki.Collection.DismissType.BURY_CARD;
import static com.ichi2.libanki.Collection.DismissType.BURY_NOTE;
import static com.ichi2.libanki.Collection.DismissType.REPOSITION_CARDS;
import static com.ichi2.libanki.Collection.DismissType.RESCHEDULE_CARDS;
import static com.ichi2.libanki.Collection.DismissType.RESET_CARDS;
import static com.ichi2.libanki.Collection.DismissType.SUSPEND_NOTE;
import static com.ichi2.libanki.Consts.DECK_DYN;
import static com.ichi2.libanki.Undoable.*;
import static com.ichi2.utils.BooleanGetter.False;
import static com.ichi2.utils.BooleanGetter.True;
import static com.ichi2.utils.BooleanGetter.fromBoolean;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Loading in the background, so that AnkiDroid does not look like frozen.
 */
public class CollectionTask<ProgressListener, ProgressBackground extends ProgressListener, ResultListener, ResultBackground extends ResultListener> extends BaseAsyncTask<Void, ProgressBackground, ResultBackground> {

    public abstract static class Task<ProgressBackground, ResultBackground> {

        protected abstract ResultBackground task(Collection col, ProgressSenderAndCancelListener<ProgressBackground> collectionTask);
    }

    /**
     * A reference to the application context to use to fetch the current Collection object.
     */
    private Context mContext;

    /**
     * Block the current thread until all CollectionTasks have finished.
     * @param timeoutSeconds timeout in seconds
     * @return whether all tasks exited successfully
     */
    @SuppressWarnings("UnusedReturnValue")
    public static boolean waitForAllToFinish(Integer timeoutSeconds) {
        // This should work in all reasonable cases given how few tasks we have concurrently blocking.
        boolean result;
        result = TaskManager.waitToFinish((ListenerUtil.mutListener.listen(12621) ? (timeoutSeconds % 4) : (ListenerUtil.mutListener.listen(12620) ? (timeoutSeconds * 4) : (ListenerUtil.mutListener.listen(12619) ? (timeoutSeconds - 4) : (ListenerUtil.mutListener.listen(12618) ? (timeoutSeconds + 4) : (timeoutSeconds / 4))))));
        if (!ListenerUtil.mutListener.listen(12622)) {
            ThreadUtil.sleep(10);
        }
        if (!ListenerUtil.mutListener.listen(12627)) {
            result &= TaskManager.waitToFinish((ListenerUtil.mutListener.listen(12626) ? (timeoutSeconds % 4) : (ListenerUtil.mutListener.listen(12625) ? (timeoutSeconds * 4) : (ListenerUtil.mutListener.listen(12624) ? (timeoutSeconds - 4) : (ListenerUtil.mutListener.listen(12623) ? (timeoutSeconds + 4) : (timeoutSeconds / 4))))));
        }
        if (!ListenerUtil.mutListener.listen(12628)) {
            ThreadUtil.sleep(10);
        }
        if (!ListenerUtil.mutListener.listen(12633)) {
            result &= TaskManager.waitToFinish((ListenerUtil.mutListener.listen(12632) ? (timeoutSeconds % 4) : (ListenerUtil.mutListener.listen(12631) ? (timeoutSeconds * 4) : (ListenerUtil.mutListener.listen(12630) ? (timeoutSeconds - 4) : (ListenerUtil.mutListener.listen(12629) ? (timeoutSeconds + 4) : (timeoutSeconds / 4))))));
        }
        if (!ListenerUtil.mutListener.listen(12634)) {
            ThreadUtil.sleep(10);
        }
        if (!ListenerUtil.mutListener.listen(12639)) {
            result &= TaskManager.waitToFinish((ListenerUtil.mutListener.listen(12638) ? (timeoutSeconds % 4) : (ListenerUtil.mutListener.listen(12637) ? (timeoutSeconds * 4) : (ListenerUtil.mutListener.listen(12636) ? (timeoutSeconds - 4) : (ListenerUtil.mutListener.listen(12635) ? (timeoutSeconds + 4) : (timeoutSeconds / 4))))));
        }
        if (!ListenerUtil.mutListener.listen(12640)) {
            ThreadUtil.sleep(10);
        }
        if (!ListenerUtil.mutListener.listen(12641)) {
            Timber.i("Waited for all tasks to finish");
        }
        return result;
    }

    /**
     * Cancel the current task.
     * @return whether cancelling did occur.
     */
    public boolean safeCancel() {
        try {
            if (!ListenerUtil.mutListener.listen(12644)) {
                if (getStatus() != AsyncTask.Status.FINISHED) {
                    return cancel(true);
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(12642)) {
                // AsyncTask.cancel
                Timber.w(e, "Exception cancelling task");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(12643)) {
                TaskManager.removeTask(this);
            }
        }
        return false;
    }

    private Collection getCol() {
        return CollectionHelper.getInstance().getCol(mContext);
    }

    protected Context getContext() {
        return mContext;
    }

    private final Task<ProgressBackground, ResultBackground> mTask;

    public Task<ProgressBackground, ResultBackground> getTask() {
        return mTask;
    }

    private final TaskListener<ProgressListener, ResultListener> mListener;

    private CollectionTask mPreviousTask;

    protected CollectionTask(Task<ProgressBackground, ResultBackground> task, TaskListener<ProgressListener, ResultListener> listener, CollectionTask previousTask) {
        mTask = task;
        mListener = listener;
        if (!ListenerUtil.mutListener.listen(12645)) {
            mPreviousTask = previousTask;
        }
        if (!ListenerUtil.mutListener.listen(12646)) {
            TaskManager.addTasks(this);
        }
    }

    @Override
    protected ResultBackground doInBackground(Void... params) {
        try {
            return actualDoInBackground();
        } finally {
            if (!ListenerUtil.mutListener.listen(12647)) {
                TaskManager.removeTask(this);
            }
        }
    }

    // This method and those that are called here are executed in a new thread
    protected ResultBackground actualDoInBackground() {
        if (!ListenerUtil.mutListener.listen(12648)) {
            super.doInBackground();
        }
        if (!ListenerUtil.mutListener.listen(12657)) {
            // Wait for previous thread (if any) to finish before continuing
            if ((ListenerUtil.mutListener.listen(12649) ? (mPreviousTask != null || mPreviousTask.getStatus() != AsyncTask.Status.FINISHED) : (mPreviousTask != null && mPreviousTask.getStatus() != AsyncTask.Status.FINISHED))) {
                if (!ListenerUtil.mutListener.listen(12650)) {
                    Timber.d("Waiting for %s to finish before starting %s", mPreviousTask.mTask, mTask.getClass());
                }
                try {
                    if (!ListenerUtil.mutListener.listen(12655)) {
                        mPreviousTask.get();
                    }
                    if (!ListenerUtil.mutListener.listen(12656)) {
                        Timber.d("Finished waiting for %s to finish. Status= %s", mPreviousTask.mTask, mPreviousTask.getStatus());
                    }
                } catch (InterruptedException e) {
                    if (!ListenerUtil.mutListener.listen(12651)) {
                        Thread.currentThread().interrupt();
                    }
                    if (!ListenerUtil.mutListener.listen(12652)) {
                        // We have been interrupted, return immediately.
                        Timber.d(e, "interrupted while waiting for previous task: %s", mPreviousTask.mTask.getClass());
                    }
                    return null;
                } catch (ExecutionException e) {
                    if (!ListenerUtil.mutListener.listen(12653)) {
                        // Ignore failures in the previous task.
                        Timber.e(e, "previously running task failed with exception: %s", mPreviousTask.mTask.getClass());
                    }
                } catch (CancellationException e) {
                    if (!ListenerUtil.mutListener.listen(12654)) {
                        // Ignore cancellation of previous task
                        Timber.d(e, "previously running task was cancelled: %s", mPreviousTask.mTask.getClass());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12658)) {
            setLatestInstance(this);
        }
        if (!ListenerUtil.mutListener.listen(12659)) {
            mContext = AnkiDroidApp.getInstance().getApplicationContext();
        }
        if (!ListenerUtil.mutListener.listen(12663)) {
            // Skip the task if the collection cannot be opened
            if ((ListenerUtil.mutListener.listen(12661) ? ((ListenerUtil.mutListener.listen(12660) ? (mTask.getClass() != RepairCollectionn.class || mTask.getClass() != ImportReplace.class) : (mTask.getClass() != RepairCollectionn.class && mTask.getClass() != ImportReplace.class)) || CollectionHelper.getInstance().getColSafe(mContext) == null) : ((ListenerUtil.mutListener.listen(12660) ? (mTask.getClass() != RepairCollectionn.class || mTask.getClass() != ImportReplace.class) : (mTask.getClass() != RepairCollectionn.class && mTask.getClass() != ImportReplace.class)) && CollectionHelper.getInstance().getColSafe(mContext) == null))) {
                if (!ListenerUtil.mutListener.listen(12662)) {
                    Timber.e("CollectionTask CollectionTask %s as Collection could not be opened", mTask.getClass());
                }
                return null;
            }
        }
        // Actually execute the task now that we are at the front of the queue.
        return mTask.task(getCol(), this);
    }

    /**
     * Delegates to the {@link TaskListener} for this task.
     */
    @Override
    protected void onPreExecute() {
        if (!ListenerUtil.mutListener.listen(12664)) {
            super.onPreExecute();
        }
        if (!ListenerUtil.mutListener.listen(12666)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(12665)) {
                    mListener.onPreExecute();
                }
            }
        }
    }

    /**
     * Delegates to the {@link TaskListener} for this task.
     */
    @Override
    protected void onProgressUpdate(ProgressBackground... values) {
        if (!ListenerUtil.mutListener.listen(12667)) {
            super.onProgressUpdate(values);
        }
        if (!ListenerUtil.mutListener.listen(12669)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(12668)) {
                    mListener.onProgressUpdate(values[0]);
                }
            }
        }
    }

    /**
     * Delegates to the {@link TaskListener} for this task.
     */
    @Override
    protected void onPostExecute(ResultBackground result) {
        if (!ListenerUtil.mutListener.listen(12670)) {
            super.onPostExecute(result);
        }
        if (!ListenerUtil.mutListener.listen(12672)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(12671)) {
                    mListener.onPostExecute(result);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12673)) {
            Timber.d("enabling garbage collection of mPreviousTask...");
        }
        if (!ListenerUtil.mutListener.listen(12674)) {
            mPreviousTask = null;
        }
    }

    @Override
    protected void onCancelled() {
        if (!ListenerUtil.mutListener.listen(12675)) {
            TaskManager.removeTask(this);
        }
        if (!ListenerUtil.mutListener.listen(12677)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(12676)) {
                    mListener.onCancelled();
                }
            }
        }
    }

    public static class AddNote extends Task<Integer, Boolean> {

        private final Note note;

        public AddNote(Note note) {
            this.note = note;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Integer> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12678)) {
                Timber.d("doInBackgroundAddNote");
            }
            try {
                DB db = col.getDb();
                if (!ListenerUtil.mutListener.listen(12681)) {
                    db.executeInTransaction(() -> {
                        int value = col.addNote(note);
                        collectionTask.doProgress(value);
                    });
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12679)) {
                    Timber.e(e, "doInBackgroundAddNote - RuntimeException on adding note");
                }
                if (!ListenerUtil.mutListener.listen(12680)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundAddNote");
                }
                return false;
            }
            return true;
        }
    }

    public static class UpdateNote extends Task<PairWithCard<String>, BooleanGetter> {

        private final Card editCard;

        private final boolean fromReviewer;

        private final boolean canAccessScheduler;

        public UpdateNote(Card editCard, boolean fromReviewer, boolean canAccessScheduler) {
            this.editCard = editCard;
            this.fromReviewer = fromReviewer;
            this.canAccessScheduler = canAccessScheduler;
        }

        protected BooleanGetter task(Collection col, ProgressSenderAndCancelListener<PairWithCard<String>> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12682)) {
                Timber.d("doInBackgroundUpdateNote");
            }
            // Save the note
            AbstractSched sched = col.getSched();
            Note editNote = editCard.note();
            try {
                if (!ListenerUtil.mutListener.listen(12685)) {
                    col.getDb().executeInTransaction(() -> {
                        // TODO: undo integration
                        editNote.flush();
                        // flush card too, in case, did has been changed
                        editCard.flush();
                        if (fromReviewer) {
                            Card newCard;
                            if (col.getDecks().active().contains(editCard.getDid()) || !canAccessScheduler) {
                                newCard = editCard;
                                newCard.load();
                                // reload qa-cache
                                newCard.q(true);
                            } else {
                                newCard = sched.getCard();
                            }
                            // check: are there deleted too?
                            collectionTask.doProgress(new PairWithCard<>(newCard, null));
                        } else {
                            collectionTask.doProgress(new PairWithCard<>(editCard, editNote.stringTags()));
                        }
                    });
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12683)) {
                    Timber.e(e, "doInBackgroundUpdateNote - RuntimeException on updating note");
                }
                if (!ListenerUtil.mutListener.listen(12684)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundUpdateNote");
                }
                return False;
            }
            return True;
        }

        public boolean isFromReviewer() {
            return fromReviewer;
        }
    }

    public static class GetCard extends Task<Card, BooleanGetter> {

        protected BooleanGetter task(Collection col, ProgressSenderAndCancelListener<Card> collectionTask) {
            AbstractSched sched = col.getSched();
            if (!ListenerUtil.mutListener.listen(12686)) {
                Timber.i("Obtaining card");
            }
            Card newCard = sched.getCard();
            if (!ListenerUtil.mutListener.listen(12688)) {
                if (newCard != null) {
                    if (!ListenerUtil.mutListener.listen(12687)) {
                        // render cards before locking database
                        newCard._getQA(true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12689)) {
                collectionTask.doProgress(newCard);
            }
            return True;
        }
    }

    public static class AnswerAndGetCard extends GetCard {

        @NonNull
        private final Card oldCard;

        @Consts.BUTTON_TYPE
        private final int ease;

        public AnswerAndGetCard(@NonNull Card oldCard, @Consts.BUTTON_TYPE int ease) {
            this.oldCard = oldCard;
            this.ease = ease;
        }

        protected BooleanGetter task(Collection col, ProgressSenderAndCancelListener<Card> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12690)) {
                Timber.i("Answering card %d", oldCard.getId());
            }
            if (!ListenerUtil.mutListener.listen(12691)) {
                col.getSched().answerCard(oldCard, ease);
            }
            return super.task(col, collectionTask);
        }
    }

    public static class LoadDeck extends Task<Void, List<DeckTreeNode>> {

        protected List<DeckTreeNode> task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12692)) {
                Timber.d("doInBackgroundLoadDeckCounts");
            }
            try {
                // Get due tree
                return col.getSched().quickDeckDueTree();
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12693)) {
                    Timber.w(e, "doInBackgroundLoadDeckCounts - error");
                }
                return null;
            }
        }
    }

    public static class LoadDeckCounts extends Task<Void, List<DeckDueTreeNode>> {

        protected List<DeckDueTreeNode> task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12694)) {
                Timber.d("doInBackgroundLoadDeckCounts");
            }
            try {
                // Get due tree
                return col.getSched().deckDueTree(collectionTask);
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12695)) {
                    Timber.e(e, "doInBackgroundLoadDeckCounts - error");
                }
                return null;
            }
        }
    }

    public static class SaveCollection extends Task<Void, Void> {

        private final boolean syncIgnoresDatabaseModification;

        public SaveCollection(boolean syncIgnoresDatabaseModification) {
            this.syncIgnoresDatabaseModification = syncIgnoresDatabaseModification;
        }

        protected Void task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12696)) {
                Timber.d("doInBackgroundSaveCollection");
            }
            if (!ListenerUtil.mutListener.listen(12701)) {
                if (col != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(12700)) {
                            if (syncIgnoresDatabaseModification) {
                                if (!ListenerUtil.mutListener.listen(12699)) {
                                    SyncStatus.ignoreDatabaseModification(col::save);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12698)) {
                                    col.save();
                                }
                            }
                        }
                    } catch (RuntimeException e) {
                        if (!ListenerUtil.mutListener.listen(12697)) {
                            Timber.e(e, "Error on saving deck in background");
                        }
                    }
                }
            }
            return null;
        }
    }

    private static class UndoSuspendCard extends Undoable {

        private final Card suspendedCard;

        public UndoSuspendCard(Card suspendedCard) {
            super(Collection.DismissType.SUSPEND_CARD);
            this.suspendedCard = suspendedCard;
        }

        @Nullable
        public Card undo(@NonNull Collection col) {
            if (!ListenerUtil.mutListener.listen(12702)) {
                Timber.i("UNDO: Suspend Card %d", suspendedCard.getId());
            }
            if (!ListenerUtil.mutListener.listen(12703)) {
                suspendedCard.flush(false);
            }
            return suspendedCard;
        }
    }

    private static class UndoDeleteNote extends Undoable {

        private final Note note;

        private final ArrayList<Card> allCs;

        @NonNull
        private final Card card;

        public UndoDeleteNote(Note note, ArrayList<Card> allCs, @NonNull Card card) {
            super(Collection.DismissType.DELETE_NOTE);
            this.note = note;
            this.allCs = allCs;
            this.card = card;
        }

        @Nullable
        public Card undo(@NonNull Collection col) {
            if (!ListenerUtil.mutListener.listen(12704)) {
                Timber.i("Undo: Delete note");
            }
            ArrayList<Long> ids = new ArrayList<>((ListenerUtil.mutListener.listen(12708) ? (allCs.size() % 1) : (ListenerUtil.mutListener.listen(12707) ? (allCs.size() / 1) : (ListenerUtil.mutListener.listen(12706) ? (allCs.size() * 1) : (ListenerUtil.mutListener.listen(12705) ? (allCs.size() - 1) : (allCs.size() + 1))))));
            if (!ListenerUtil.mutListener.listen(12709)) {
                note.flush(note.getMod(), false);
            }
            if (!ListenerUtil.mutListener.listen(12710)) {
                ids.add(note.getId());
            }
            if (!ListenerUtil.mutListener.listen(12713)) {
                {
                    long _loopCounter204 = 0;
                    for (Card c : allCs) {
                        ListenerUtil.loopListener.listen("_loopCounter204", ++_loopCounter204);
                        if (!ListenerUtil.mutListener.listen(12711)) {
                            c.flush(false);
                        }
                        if (!ListenerUtil.mutListener.listen(12712)) {
                            ids.add(c.getId());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12714)) {
                col.getDb().execute("DELETE FROM graves WHERE oid IN " + Utils.ids2str(ids));
            }
            return card;
        }
    }

    public static class DismissNote extends Task<Card, BooleanGetter> {

        private final Card card;

        private final Collection.DismissType type;

        public DismissNote(Card card, Collection.DismissType type) {
            this.card = card;
            this.type = type;
        }

        protected BooleanGetter task(Collection col, ProgressSenderAndCancelListener<Card> collectionTask) {
            AbstractSched sched = col.getSched();
            Note note = card.note();
            try {
                if (!ListenerUtil.mutListener.listen(12717)) {
                    col.getDb().executeInTransaction(() -> {
                        sched.deferReset();
                        switch(type) {
                            case BURY_CARD:
                                // collect undo information
                                col.markUndo(revertToProvidedState(BURY_CARD, card));
                                // then bury
                                sched.buryCards(new long[] { card.getId() });
                                break;
                            case BURY_NOTE:
                                // collect undo information
                                col.markUndo(revertToProvidedState(BURY_NOTE, card));
                                // then bury
                                sched.buryNote(note.getId());
                                break;
                            case SUSPEND_CARD:
                                // collect undo information
                                Card suspendedCard = card.clone();
                                col.markUndo(new UndoSuspendCard(suspendedCard));
                                // suspend card
                                if (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED) {
                                    sched.unsuspendCards(new long[] { card.getId() });
                                } else {
                                    sched.suspendCards(new long[] { card.getId() });
                                }
                                break;
                            case SUSPEND_NOTE:
                                {
                                    // collect undo information
                                    ArrayList<Card> cards = note.cards();
                                    long[] cids = new long[cards.size()];
                                    {
                                        long _loopCounter205 = 0;
                                        for (int i = 0; i < cards.size(); i++) {
                                            ListenerUtil.loopListener.listen("_loopCounter205", ++_loopCounter205);
                                            cids[i] = cards.get(i).getId();
                                        }
                                    }
                                    col.markUndo(revertToProvidedState(SUSPEND_NOTE, card));
                                    // suspend note
                                    sched.suspendCards(cids);
                                    break;
                                }
                            case DELETE_NOTE:
                                {
                                    // collect undo information
                                    ArrayList<Card> allCs = note.cards();
                                    col.markUndo(new UndoDeleteNote(note, allCs, card));
                                    // delete note
                                    col.remNotes(new long[] { note.getId() });
                                    break;
                                }
                        }
                        // With sHadCardQueue set, getCard() resets the scheduler prior to getting the next card
                        collectionTask.doProgress(col.getSched().getCard());
                    });
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12715)) {
                    Timber.e(e, "doInBackgroundDismissNote - RuntimeException on dismissing note, dismiss type %s", type);
                }
                if (!ListenerUtil.mutListener.listen(12716)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundDismissNote");
                }
                return False;
            }
            return True;
        }
    }

    protected static class UndoSuspendCardMulti extends Undoable {

        private final Card[] cards;

        private final boolean[] originalSuspended;

        /**
         * @param hasUnsuspended  whether there were any unsuspended card (in which card the action was "Suspend",
         *                          otherwise the action was "Unsuspend")
         */
        public UndoSuspendCardMulti(Card[] cards, boolean[] originalSuspended, boolean hasUnsuspended) {
            super((hasUnsuspended) ? Collection.DismissType.SUSPEND_CARD_MULTI : Collection.DismissType.UNSUSPEND_CARD_MULTI);
            this.cards = cards;
            this.originalSuspended = originalSuspended;
        }

        @Nullable
        public Card undo(@NonNull Collection col) {
            if (!ListenerUtil.mutListener.listen(12718)) {
                Timber.i("Undo: Suspend multiple cards");
            }
            int nbOfCards = cards.length;
            List<Long> toSuspendIds = new ArrayList<>(nbOfCards);
            List<Long> toUnsuspendIds = new ArrayList<>(nbOfCards);
            if (!ListenerUtil.mutListener.listen(12727)) {
                {
                    long _loopCounter206 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(12726) ? (i >= nbOfCards) : (ListenerUtil.mutListener.listen(12725) ? (i <= nbOfCards) : (ListenerUtil.mutListener.listen(12724) ? (i > nbOfCards) : (ListenerUtil.mutListener.listen(12723) ? (i != nbOfCards) : (ListenerUtil.mutListener.listen(12722) ? (i == nbOfCards) : (i < nbOfCards)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter206", ++_loopCounter206);
                        Card card = cards[i];
                        if (!ListenerUtil.mutListener.listen(12721)) {
                            if (originalSuspended[i]) {
                                if (!ListenerUtil.mutListener.listen(12720)) {
                                    toSuspendIds.add(card.getId());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12719)) {
                                    toUnsuspendIds.add(card.getId());
                                }
                            }
                        }
                    }
                }
            }
            // unboxing
            long[] toSuspendIdsArray = new long[toSuspendIds.size()];
            long[] toUnsuspendIdsArray = new long[toUnsuspendIds.size()];
            if (!ListenerUtil.mutListener.listen(12734)) {
                {
                    long _loopCounter207 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(12733) ? (i >= toSuspendIds.size()) : (ListenerUtil.mutListener.listen(12732) ? (i <= toSuspendIds.size()) : (ListenerUtil.mutListener.listen(12731) ? (i > toSuspendIds.size()) : (ListenerUtil.mutListener.listen(12730) ? (i != toSuspendIds.size()) : (ListenerUtil.mutListener.listen(12729) ? (i == toSuspendIds.size()) : (i < toSuspendIds.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter207", ++_loopCounter207);
                        if (!ListenerUtil.mutListener.listen(12728)) {
                            toSuspendIdsArray[i] = toSuspendIds.get(i);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12741)) {
                {
                    long _loopCounter208 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(12740) ? (i >= toUnsuspendIds.size()) : (ListenerUtil.mutListener.listen(12739) ? (i <= toUnsuspendIds.size()) : (ListenerUtil.mutListener.listen(12738) ? (i > toUnsuspendIds.size()) : (ListenerUtil.mutListener.listen(12737) ? (i != toUnsuspendIds.size()) : (ListenerUtil.mutListener.listen(12736) ? (i == toUnsuspendIds.size()) : (i < toUnsuspendIds.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter208", ++_loopCounter208);
                        if (!ListenerUtil.mutListener.listen(12735)) {
                            toUnsuspendIdsArray[i] = toUnsuspendIds.get(i);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12742)) {
                col.getSched().suspendCards(toSuspendIdsArray);
            }
            if (!ListenerUtil.mutListener.listen(12743)) {
                col.getSched().unsuspendCards(toUnsuspendIdsArray);
            }
            // don't fetch new card
            return null;
        }
    }

    private static class UndoDeleteNoteMulti extends Undoable {

        private final Note[] notesArr;

        private final List<Card> allCards;

        public UndoDeleteNoteMulti(Note[] notesArr, List<Card> allCards) {
            super(Collection.DismissType.DELETE_NOTE_MULTI);
            this.notesArr = notesArr;
            this.allCards = allCards;
        }

        @Nullable
        public Card undo(@NonNull Collection col) {
            if (!ListenerUtil.mutListener.listen(12744)) {
                Timber.i("Undo: Delete notes");
            }
            // undo all of these at once instead of one-by-one
            ArrayList<Long> ids = new ArrayList<>((ListenerUtil.mutListener.listen(12748) ? (notesArr.length % allCards.size()) : (ListenerUtil.mutListener.listen(12747) ? (notesArr.length / allCards.size()) : (ListenerUtil.mutListener.listen(12746) ? (notesArr.length * allCards.size()) : (ListenerUtil.mutListener.listen(12745) ? (notesArr.length - allCards.size()) : (notesArr.length + allCards.size()))))));
            if (!ListenerUtil.mutListener.listen(12751)) {
                {
                    long _loopCounter209 = 0;
                    for (Note n : notesArr) {
                        ListenerUtil.loopListener.listen("_loopCounter209", ++_loopCounter209);
                        if (!ListenerUtil.mutListener.listen(12749)) {
                            n.flush(n.getMod(), false);
                        }
                        if (!ListenerUtil.mutListener.listen(12750)) {
                            ids.add(n.getId());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12754)) {
                {
                    long _loopCounter210 = 0;
                    for (Card c : allCards) {
                        ListenerUtil.loopListener.listen("_loopCounter210", ++_loopCounter210);
                        if (!ListenerUtil.mutListener.listen(12752)) {
                            c.flush(false);
                        }
                        if (!ListenerUtil.mutListener.listen(12753)) {
                            ids.add(c.getId());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12755)) {
                col.getDb().execute("DELETE FROM graves WHERE oid IN " + Utils.ids2str(ids));
            }
            // don't fetch new card
            return null;
        }
    }

    private static class UndoChangeDeckMulti extends Undoable {

        private final Card[] cards;

        private final long[] originalDids;

        public UndoChangeDeckMulti(Card[] cards, long[] originalDids) {
            super(Collection.DismissType.CHANGE_DECK_MULTI);
            this.cards = cards;
            this.originalDids = originalDids;
        }

        @Nullable
        public Card undo(@NonNull Collection col) {
            if (!ListenerUtil.mutListener.listen(12756)) {
                Timber.i("Undo: Change Decks");
            }
            if (!ListenerUtil.mutListener.listen(12766)) {
                {
                    long _loopCounter211 = 0;
                    // move cards to original deck
                    for (int i = 0; (ListenerUtil.mutListener.listen(12765) ? (i >= cards.length) : (ListenerUtil.mutListener.listen(12764) ? (i <= cards.length) : (ListenerUtil.mutListener.listen(12763) ? (i > cards.length) : (ListenerUtil.mutListener.listen(12762) ? (i != cards.length) : (ListenerUtil.mutListener.listen(12761) ? (i == cards.length) : (i < cards.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter211", ++_loopCounter211);
                        Card card = cards[i];
                        if (!ListenerUtil.mutListener.listen(12757)) {
                            card.load();
                        }
                        if (!ListenerUtil.mutListener.listen(12758)) {
                            card.setDid(originalDids[i]);
                        }
                        Note note = card.note();
                        if (!ListenerUtil.mutListener.listen(12759)) {
                            note.flush();
                        }
                        if (!ListenerUtil.mutListener.listen(12760)) {
                            card.flush();
                        }
                    }
                }
            }
            // don't fetch new card
            return null;
        }
    }

    private static class UndoMarkNoteMulti extends Undoable {

        private final List<Note> originalMarked;

        private final List<Note> originalUnmarked;

        /**
         * @param hasUnmarked whether there were any unmarked card (in which card the action was "mark",
         *                      otherwise the action was "Unmark")
         */
        public UndoMarkNoteMulti(List<Note> originalMarked, List<Note> originalUnmarked, boolean hasUnmarked) {
            super((hasUnmarked) ? Collection.DismissType.MARK_NOTE_MULTI : Collection.DismissType.UNMARK_NOTE_MULTI);
            this.originalMarked = originalMarked;
            this.originalUnmarked = originalUnmarked;
        }

        @Nullable
        public Card undo(@NonNull Collection col) {
            if (!ListenerUtil.mutListener.listen(12767)) {
                Timber.i("Undo: Mark notes");
            }
            if (!ListenerUtil.mutListener.listen(12768)) {
                CardUtils.markAll(originalMarked, true);
            }
            if (!ListenerUtil.mutListener.listen(12769)) {
                CardUtils.markAll(originalUnmarked, false);
            }
            // don't fetch new card
            return null;
        }
    }

    private static class UndoRepositionRescheduleResetCards extends Undoable {

        private final Card[] cards_copied;

        public UndoRepositionRescheduleResetCards(Collection.DismissType type, Card[] cards_copied) {
            super(type);
            this.cards_copied = cards_copied;
        }

        @Nullable
        public Card undo(@NonNull Collection col) {
            if (!ListenerUtil.mutListener.listen(12770)) {
                Timber.i("Undoing action of type %s on %d cards", getDismissType(), cards_copied.length);
            }
            if (!ListenerUtil.mutListener.listen(12772)) {
                {
                    long _loopCounter212 = 0;
                    for (Card card : cards_copied) {
                        ListenerUtil.loopListener.listen("_loopCounter212", ++_loopCounter212);
                        if (!ListenerUtil.mutListener.listen(12771)) {
                            card.flush(false);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12773)) {
                // new card */
                Timber.d("Single card non-review change undo succeeded");
            }
            if (!ListenerUtil.mutListener.listen(12774)) {
                col.reset();
            }
            return col.getSched().getCard();
        }
    }

    private abstract static class DismissNotes<Progress> extends Task<Progress, PairWithBoolean<Card[]>> {

        protected final List<Long> mCardIds;

        public DismissNotes(List<Long> cardIds) {
            this.mCardIds = cardIds;
        }

        protected PairWithBoolean<Card[]> task(Collection col, ProgressSenderAndCancelListener<Progress> collectionTask) {
            // query cards
            Card[] cards = new Card[mCardIds.size()];
            if (!ListenerUtil.mutListener.listen(12781)) {
                {
                    long _loopCounter213 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(12780) ? (i >= mCardIds.size()) : (ListenerUtil.mutListener.listen(12779) ? (i <= mCardIds.size()) : (ListenerUtil.mutListener.listen(12778) ? (i > mCardIds.size()) : (ListenerUtil.mutListener.listen(12777) ? (i != mCardIds.size()) : (ListenerUtil.mutListener.listen(12776) ? (i == mCardIds.size()) : (i < mCardIds.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter213", ++_loopCounter213);
                        if (!ListenerUtil.mutListener.listen(12775)) {
                            cards[i] = col.getCard(mCardIds.get(i));
                        }
                    }
                }
            }
            try {
                if (!ListenerUtil.mutListener.listen(12784)) {
                    col.getDb().getDatabase().beginTransaction();
                }
                try {
                    PairWithBoolean<Card[]> ret = actualTask(col, collectionTask, cards);
                    if (!ListenerUtil.mutListener.listen(12786)) {
                        if (ret != null) {
                            return ret;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12787)) {
                        col.getDb().getDatabase().setTransactionSuccessful();
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(12785)) {
                        DB.safeEndInTransaction(col.getDb());
                    }
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12782)) {
                    Timber.e(e, "doInBackgroundSuspendCard - RuntimeException on suspending card");
                }
                if (!ListenerUtil.mutListener.listen(12783)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundSuspendCard");
                }
                return new PairWithBoolean<>(false, null);
            }
            // (querying the cards again is unnecessarily expensive)
            return new PairWithBoolean<>(true, cards);
        }

        /**
         * @param col The collection
         * @param collectionTask, where to send progress and listen for cancellation
         * @param cards Cards to which the task should be applied
         * @return value to return, or null if `task` should deal with it directly.
         */
        protected abstract PairWithBoolean<Card[]> actualTask(Collection col, ProgressSenderAndCancelListener<Progress> collectionTask, Card[] cards);
    }

    public static class SuspendCardMulti extends DismissNotes<Void> {

        public SuspendCardMulti(List<Long> cardIds) {
            super(cardIds);
        }

        protected PairWithBoolean<Card[]> actualTask(Collection col, ProgressSenderAndCancelListener<Void> collectionTask, Card[] cards) {
            AbstractSched sched = col.getSched();
            // collect undo information
            long[] cids = new long[cards.length];
            boolean[] originalSuspended = new boolean[cards.length];
            boolean hasUnsuspended = false;
            if (!ListenerUtil.mutListener.listen(12803)) {
                {
                    long _loopCounter214 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(12802) ? (i >= cards.length) : (ListenerUtil.mutListener.listen(12801) ? (i <= cards.length) : (ListenerUtil.mutListener.listen(12800) ? (i > cards.length) : (ListenerUtil.mutListener.listen(12799) ? (i != cards.length) : (ListenerUtil.mutListener.listen(12798) ? (i == cards.length) : (i < cards.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter214", ++_loopCounter214);
                        Card card = cards[i];
                        if (!ListenerUtil.mutListener.listen(12788)) {
                            cids[i] = card.getId();
                        }
                        if (!ListenerUtil.mutListener.listen(12797)) {
                            if ((ListenerUtil.mutListener.listen(12793) ? (card.getQueue() >= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(12792) ? (card.getQueue() <= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(12791) ? (card.getQueue() > Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(12790) ? (card.getQueue() < Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(12789) ? (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED) : (card.getQueue() != Consts.QUEUE_TYPE_SUSPENDED))))))) {
                                if (!ListenerUtil.mutListener.listen(12795)) {
                                    hasUnsuspended = true;
                                }
                                if (!ListenerUtil.mutListener.listen(12796)) {
                                    originalSuspended[i] = false;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12794)) {
                                    originalSuspended[i] = true;
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12806)) {
                // otherwise unsuspend all
                if (hasUnsuspended) {
                    if (!ListenerUtil.mutListener.listen(12805)) {
                        sched.suspendCards(cids);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(12804)) {
                        sched.unsuspendCards(cids);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12807)) {
                // mark undo for all at once
                col.markUndo(new UndoSuspendCardMulti(cards, originalSuspended, hasUnsuspended));
            }
            if (!ListenerUtil.mutListener.listen(12809)) {
                {
                    long _loopCounter215 = 0;
                    // reload cards because they'll be passed back to caller
                    for (Card c : cards) {
                        ListenerUtil.loopListener.listen("_loopCounter215", ++_loopCounter215);
                        if (!ListenerUtil.mutListener.listen(12808)) {
                            c.load();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12810)) {
                sched.deferReset();
            }
            return null;
        }
    }

    public static class Flag extends DismissNotes<Void> {

        private final int mFlag;

        public Flag(List<Long> cardIds, int flag) {
            super(cardIds);
            mFlag = flag;
        }

        protected PairWithBoolean<Card[]> actualTask(Collection col, ProgressSenderAndCancelListener<Void> collectionTask, Card[] cards) {
            if (!ListenerUtil.mutListener.listen(12811)) {
                col.setUserFlag(mFlag, mCardIds);
            }
            if (!ListenerUtil.mutListener.listen(12813)) {
                {
                    long _loopCounter216 = 0;
                    for (Card c : cards) {
                        ListenerUtil.loopListener.listen("_loopCounter216", ++_loopCounter216);
                        if (!ListenerUtil.mutListener.listen(12812)) {
                            c.load();
                        }
                    }
                }
            }
            return null;
        }
    }

    public static class MarkNoteMulti extends DismissNotes<Void> {

        public MarkNoteMulti(List<Long> cardIds) {
            super(cardIds);
        }

        protected PairWithBoolean<Card[]> actualTask(Collection col, ProgressSenderAndCancelListener<Void> collectionTask, Card[] cards) {
            Set<Note> notes = CardUtils.getNotes(Arrays.asList(cards));
            // collect undo information
            List<Note> originalMarked = new ArrayList<>();
            List<Note> originalUnmarked = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(12817)) {
                {
                    long _loopCounter217 = 0;
                    for (Note n : notes) {
                        ListenerUtil.loopListener.listen("_loopCounter217", ++_loopCounter217);
                        if (!ListenerUtil.mutListener.listen(12816)) {
                            if (n.hasTag("marked")) {
                                if (!ListenerUtil.mutListener.listen(12815)) {
                                    originalMarked.add(n);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12814)) {
                                    originalUnmarked.add(n);
                                }
                            }
                        }
                    }
                }
            }
            boolean hasUnmarked = !originalUnmarked.isEmpty();
            if (!ListenerUtil.mutListener.listen(12818)) {
                CardUtils.markAll(new ArrayList<>(notes), hasUnmarked);
            }
            Undoable markNoteMulti = new UndoMarkNoteMulti(originalMarked, originalUnmarked, hasUnmarked);
            if (!ListenerUtil.mutListener.listen(12819)) {
                // mark undo for all at once
                col.markUndo(new UndoMarkNoteMulti(originalMarked, originalUnmarked, hasUnmarked));
            }
            if (!ListenerUtil.mutListener.listen(12821)) {
                {
                    long _loopCounter218 = 0;
                    // reload cards because they'll be passed back to caller
                    for (Card c : cards) {
                        ListenerUtil.loopListener.listen("_loopCounter218", ++_loopCounter218);
                        if (!ListenerUtil.mutListener.listen(12820)) {
                            c.load();
                        }
                    }
                }
            }
            return null;
        }
    }

    public static class DeleteNoteMulti extends DismissNotes<Card[]> {

        public DeleteNoteMulti(List<Long> cardIds) {
            super(cardIds);
        }

        protected PairWithBoolean<Card[]> actualTask(Collection col, ProgressSenderAndCancelListener<Card[]> collectionTask, Card[] cards) {
            AbstractSched sched = col.getSched();
            // Need Set (-> unique) so we don't pass duplicates to col.remNotes()
            Set<Note> notes = CardUtils.getNotes(Arrays.asList(cards));
            List<Card> allCards = CardUtils.getAllCards(notes);
            // delete note
            long[] uniqueNoteIds = new long[notes.size()];
            Note[] notesArr = notes.toArray(new Note[notes.size()]);
            int count = 0;
            if (!ListenerUtil.mutListener.listen(12824)) {
                {
                    long _loopCounter219 = 0;
                    for (Note note : notes) {
                        ListenerUtil.loopListener.listen("_loopCounter219", ++_loopCounter219);
                        if (!ListenerUtil.mutListener.listen(12822)) {
                            uniqueNoteIds[count] = note.getId();
                        }
                        if (!ListenerUtil.mutListener.listen(12823)) {
                            count++;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12825)) {
                col.markUndo(new UndoDeleteNoteMulti(notesArr, allCards));
            }
            if (!ListenerUtil.mutListener.listen(12826)) {
                col.remNotes(uniqueNoteIds);
            }
            if (!ListenerUtil.mutListener.listen(12827)) {
                sched.deferReset();
            }
            if (!ListenerUtil.mutListener.listen(12828)) {
                // pass back all cards because they can't be retrieved anymore by the caller (since the note is deleted)
                collectionTask.doProgress(allCards.toArray(new Card[allCards.size()]));
            }
            return null;
        }
    }

    public static class ChangeDeckMulti extends DismissNotes<Void> {

        private final long mNewDid;

        public ChangeDeckMulti(List<Long> cardIds, long newDid) {
            super(cardIds);
            mNewDid = newDid;
        }

        protected PairWithBoolean<Card[]> actualTask(Collection col, ProgressSenderAndCancelListener<Void> collectionTask, Card[] cards) {
            if (!ListenerUtil.mutListener.listen(12829)) {
                Timber.i("Changing %d cards to deck: '%d'", cards.length, mNewDid);
            }
            Deck deckData = col.getDecks().get(mNewDid);
            if (!ListenerUtil.mutListener.listen(12831)) {
                if (Decks.isDynamic(deckData)) {
                    if (!ListenerUtil.mutListener.listen(12830)) {
                        // #5932 - can't change to a dynamic deck. Use "Rebuild"
                        Timber.w("Attempted to move to dynamic deck. Cancelling task.");
                    }
                    return new PairWithBoolean<>(false, null);
                }
            }
            // Confirm that the deck exists (and is not the default)
            try {
                long actualId = deckData.getLong("id");
                if (!ListenerUtil.mutListener.listen(12839)) {
                    if ((ListenerUtil.mutListener.listen(12837) ? (actualId >= mNewDid) : (ListenerUtil.mutListener.listen(12836) ? (actualId <= mNewDid) : (ListenerUtil.mutListener.listen(12835) ? (actualId > mNewDid) : (ListenerUtil.mutListener.listen(12834) ? (actualId < mNewDid) : (ListenerUtil.mutListener.listen(12833) ? (actualId == mNewDid) : (actualId != mNewDid))))))) {
                        if (!ListenerUtil.mutListener.listen(12838)) {
                            Timber.w("Attempted to move to deck %d, but got %d", mNewDid, actualId);
                        }
                        return new PairWithBoolean<>(false, null);
                    }
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(12832)) {
                    Timber.e(e, "failed to check deck");
                }
                return new PairWithBoolean<>(false, null);
            }
            long[] changedCardIds = new long[cards.length];
            if (!ListenerUtil.mutListener.listen(12846)) {
                {
                    long _loopCounter220 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(12845) ? (i >= cards.length) : (ListenerUtil.mutListener.listen(12844) ? (i <= cards.length) : (ListenerUtil.mutListener.listen(12843) ? (i > cards.length) : (ListenerUtil.mutListener.listen(12842) ? (i != cards.length) : (ListenerUtil.mutListener.listen(12841) ? (i == cards.length) : (i < cards.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter220", ++_loopCounter220);
                        if (!ListenerUtil.mutListener.listen(12840)) {
                            changedCardIds[i] = cards[i].getId();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12847)) {
                col.getSched().remFromDyn(changedCardIds);
            }
            long[] originalDids = new long[cards.length];
            if (!ListenerUtil.mutListener.listen(12858)) {
                {
                    long _loopCounter221 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(12857) ? (i >= cards.length) : (ListenerUtil.mutListener.listen(12856) ? (i <= cards.length) : (ListenerUtil.mutListener.listen(12855) ? (i > cards.length) : (ListenerUtil.mutListener.listen(12854) ? (i != cards.length) : (ListenerUtil.mutListener.listen(12853) ? (i == cards.length) : (i < cards.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter221", ++_loopCounter221);
                        Card card = cards[i];
                        if (!ListenerUtil.mutListener.listen(12848)) {
                            card.load();
                        }
                        if (!ListenerUtil.mutListener.listen(12849)) {
                            // save original did for undo
                            originalDids[i] = card.getDid();
                        }
                        if (!ListenerUtil.mutListener.listen(12850)) {
                            // then set the card ID to the new deck
                            card.setDid(mNewDid);
                        }
                        Note note = card.note();
                        if (!ListenerUtil.mutListener.listen(12851)) {
                            note.flush();
                        }
                        if (!ListenerUtil.mutListener.listen(12852)) {
                            // flush card too, in case, did has been changed
                            card.flush();
                        }
                    }
                }
            }
            Undoable changeDeckMulti = new UndoChangeDeckMulti(cards, originalDids);
            if (!ListenerUtil.mutListener.listen(12859)) {
                // mark undo for all at once
                col.markUndo(changeDeckMulti);
            }
            return null;
        }
    }

    private abstract static class RescheduleRepositionReset extends DismissNotes<Card> {

        private final Collection.DismissType mType;

        public RescheduleRepositionReset(List<Long> cardIds, Collection.DismissType type) {
            super(cardIds);
            mType = type;
        }

        protected PairWithBoolean<Card[]> actualTask(Collection col, ProgressSenderAndCancelListener<Card> collectionTask, Card[] cards) {
            AbstractSched sched = col.getSched();
            // collect undo information, sensitive to memory pressure, same for all 3 cases
            try {
                if (!ListenerUtil.mutListener.listen(12861)) {
                    Timber.d("Saving undo information of type %s on %d cards", mType, cards.length);
                }
                Card[] cards_copied = deepCopyCardArray(cards, collectionTask);
                Undoable repositionRescheduleResetCards = new UndoRepositionRescheduleResetCards(mType, cards_copied);
                if (!ListenerUtil.mutListener.listen(12862)) {
                    col.markUndo(repositionRescheduleResetCards);
                }
            } catch (CancellationException ce) {
                if (!ListenerUtil.mutListener.listen(12860)) {
                    Timber.i(ce, "Cancelled while handling type %s, skipping undo", mType);
                }
            }
            if (!ListenerUtil.mutListener.listen(12863)) {
                actualActualTask(sched);
            }
            if (!ListenerUtil.mutListener.listen(12864)) {
                // In all cases schedule a new card so Reviewer doesn't sit on the old one
                col.reset();
            }
            if (!ListenerUtil.mutListener.listen(12865)) {
                collectionTask.doProgress(sched.getCard());
            }
            return null;
        }

        protected abstract void actualActualTask(AbstractSched sched);
    }

    public static class RescheduleCards extends RescheduleRepositionReset {

        private final int mSchedule;

        public RescheduleCards(List<Long> cardIds, int schedule) {
            super(cardIds, RESCHEDULE_CARDS);
            this.mSchedule = schedule;
        }

        @Override
        protected void actualActualTask(AbstractSched sched) {
            if (!ListenerUtil.mutListener.listen(12866)) {
                sched.reschedCards(mCardIds, mSchedule, mSchedule);
            }
        }
    }

    public static class RepositionCards extends RescheduleRepositionReset {

        private final int mPosition;

        public RepositionCards(List<Long> cardIds, int position) {
            super(cardIds, REPOSITION_CARDS);
            this.mPosition = position;
        }

        @Override
        protected void actualActualTask(AbstractSched sched) {
            if (!ListenerUtil.mutListener.listen(12867)) {
                sched.sortCards(mCardIds, mPosition, 1, false, true);
            }
        }
    }

    public static class ResetCards extends RescheduleRepositionReset {

        public ResetCards(List<Long> cardIds) {
            super(cardIds, RESET_CARDS);
        }

        @Override
        protected void actualActualTask(AbstractSched sched) {
            if (!ListenerUtil.mutListener.listen(12868)) {
                sched.forgetCards(mCardIds);
            }
        }
    }

    @VisibleForTesting
    public static Card nonTaskUndo(Collection col) {
        AbstractSched sched = col.getSched();
        Card card = col.undo();
        if (!ListenerUtil.mutListener.listen(12874)) {
            if (card == null) {
                if (!ListenerUtil.mutListener.listen(12873)) {
                    /* multi-card action undone, no action to take here */
                    Timber.d("Multi-select undo succeeded");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12869)) {
                    /* card review undone, set up to review that card again */
                    Timber.d("Single card review undo succeeded");
                }
                if (!ListenerUtil.mutListener.listen(12870)) {
                    card.startTimer();
                }
                if (!ListenerUtil.mutListener.listen(12871)) {
                    col.reset();
                }
                if (!ListenerUtil.mutListener.listen(12872)) {
                    sched.deferReset(card);
                }
            }
        }
        return card;
    }

    public static class Undo extends Task<Card, BooleanGetter> {

        protected BooleanGetter task(Collection col, ProgressSenderAndCancelListener<Card> collectionTask) {
            try {
                if (!ListenerUtil.mutListener.listen(12877)) {
                    col.getDb().executeInTransaction(() -> {
                        Card card = nonTaskUndo(col);
                        collectionTask.doProgress(card);
                    });
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12875)) {
                    Timber.e(e, "doInBackgroundUndo - RuntimeException on undoing");
                }
                if (!ListenerUtil.mutListener.listen(12876)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundUndo");
                }
                return False;
            }
            return True;
        }
    }

    /**
     * A class allowing to send partial search result to the browser to display while the search ends
     */
    public static class PartialSearch implements ProgressSenderAndCancelListener<List<Long>> {

        private final List<CardBrowser.CardCache> mCards;

        private final int mColumn1Index, mColumn2Index;

        private final int mNumCardsToRender;

        private final ProgressSenderAndCancelListener<List<CardBrowser.CardCache>> mCollectionTask;

        private final Collection mCol;

        public PartialSearch(List<CardBrowser.CardCache> cards, int columnIndex1, int columnIndex2, int numCardsToRender, ProgressSenderAndCancelListener<List<CardBrowser.CardCache>> collectionTask, Collection col) {
            mCards = cards;
            mColumn1Index = columnIndex1;
            mColumn2Index = columnIndex2;
            mNumCardsToRender = numCardsToRender;
            mCollectionTask = collectionTask;
            mCol = col;
        }

        @Override
        public boolean isCancelled() {
            return mCollectionTask.isCancelled();
        }

        /**
         * @param cards Card ids to display in the browser. It is assumed that it is as least as long as mCards, and that
         *             mCards[i].cid = cards[i].  It add the cards in cards after `mPosition` to mCards
         */
        public void add(@NonNull List<Long> cards) {
            if (!ListenerUtil.mutListener.listen(12884)) {
                {
                    long _loopCounter222 = 0;
                    while ((ListenerUtil.mutListener.listen(12883) ? (mCards.size() >= cards.size()) : (ListenerUtil.mutListener.listen(12882) ? (mCards.size() <= cards.size()) : (ListenerUtil.mutListener.listen(12881) ? (mCards.size() > cards.size()) : (ListenerUtil.mutListener.listen(12880) ? (mCards.size() != cards.size()) : (ListenerUtil.mutListener.listen(12879) ? (mCards.size() == cards.size()) : (mCards.size() < cards.size()))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter222", ++_loopCounter222);
                        if (!ListenerUtil.mutListener.listen(12878)) {
                            mCards.add(new CardBrowser.CardCache(cards.get(mCards.size()), mCol, mCards.size()));
                        }
                    }
                }
            }
        }

        @Override
        public void doProgress(@NonNull List<Long> value) {
            if (!ListenerUtil.mutListener.listen(12885)) {
                add(value);
            }
            if (!ListenerUtil.mutListener.listen(12889)) {
                {
                    long _loopCounter223 = 0;
                    for (CardBrowser.CardCache card : mCards) {
                        ListenerUtil.loopListener.listen("_loopCounter223", ++_loopCounter223);
                        if (!ListenerUtil.mutListener.listen(12887)) {
                            if (isCancelled()) {
                                if (!ListenerUtil.mutListener.listen(12886)) {
                                    Timber.d("doInBackgroundSearchCards was cancelled so return");
                                }
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12888)) {
                            card.load(false, mColumn1Index, mColumn2Index);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12890)) {
                mCollectionTask.doProgress(mCards);
            }
        }

        public int getNumCardsToRender() {
            return mNumCardsToRender;
        }
    }

    public static class SearchCards extends Task<List<CardBrowser.CardCache>, List<CardBrowser.CardCache>> {

        private final String query;

        private final boolean order;

        private final int numCardsToRender;

        private final int column1Index;

        private final int column2Index;

        public SearchCards(String query, boolean order, int numCardsToRender, int column1Index, int column2Index) {
            this.query = query;
            this.order = order;
            this.numCardsToRender = numCardsToRender;
            this.column1Index = column1Index;
            this.column2Index = column2Index;
        }

        protected List<CardBrowser.CardCache> task(Collection col, ProgressSenderAndCancelListener<List<CardBrowser.CardCache>> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12891)) {
                Timber.d("doInBackgroundSearchCards");
            }
            if (collectionTask.isCancelled()) {
                if (!ListenerUtil.mutListener.listen(12892)) {
                    Timber.d("doInBackgroundSearchCards was cancelled so return null");
                }
                return null;
            }
            List<CardBrowser.CardCache> searchResult = new ArrayList<>();
            List<Long> searchResult_ = col.findCards(query, order, new PartialSearch(searchResult, column1Index, column2Index, numCardsToRender, collectionTask, col));
            if (!ListenerUtil.mutListener.listen(12893)) {
                Timber.d("The search found %d cards", searchResult_.size());
            }
            int position = 0;
            if (!ListenerUtil.mutListener.listen(12895)) {
                {
                    long _loopCounter224 = 0;
                    for (Long cid : searchResult_) {
                        ListenerUtil.loopListener.listen("_loopCounter224", ++_loopCounter224);
                        CardBrowser.CardCache card = new CardBrowser.CardCache(cid, col, position++);
                        if (!ListenerUtil.mutListener.listen(12894)) {
                            searchResult.add(card);
                        }
                    }
                }
            }
            {
                long _loopCounter225 = 0;
                // Render the first few items
                for (int i = 0; (ListenerUtil.mutListener.listen(12902) ? (i >= Math.min(numCardsToRender, searchResult.size())) : (ListenerUtil.mutListener.listen(12901) ? (i <= Math.min(numCardsToRender, searchResult.size())) : (ListenerUtil.mutListener.listen(12900) ? (i > Math.min(numCardsToRender, searchResult.size())) : (ListenerUtil.mutListener.listen(12899) ? (i != Math.min(numCardsToRender, searchResult.size())) : (ListenerUtil.mutListener.listen(12898) ? (i == Math.min(numCardsToRender, searchResult.size())) : (i < Math.min(numCardsToRender, searchResult.size()))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter225", ++_loopCounter225);
                    if (collectionTask.isCancelled()) {
                        if (!ListenerUtil.mutListener.listen(12896)) {
                            Timber.d("doInBackgroundSearchCards was cancelled so return null");
                        }
                        return null;
                    }
                    if (!ListenerUtil.mutListener.listen(12897)) {
                        searchResult.get(i).load(false, column1Index, column2Index);
                    }
                }
            }
            // Finish off the task
            if (collectionTask.isCancelled()) {
                if (!ListenerUtil.mutListener.listen(12903)) {
                    Timber.d("doInBackgroundSearchCards was cancelled so return null");
                }
                return null;
            } else {
                return searchResult;
            }
        }
    }

    public static class RenderBrowserQA extends Task<Integer, Pair<CardBrowser.CardCollection<CardBrowser.CardCache>, List<Long>>> {

        private final CardBrowser.CardCollection<CardBrowser.CardCache> cards;

        private final Integer startPos;

        private final Integer n;

        private final int column1Index;

        private final int column2Index;

        public RenderBrowserQA(CardBrowser.CardCollection<CardBrowser.CardCache> cards, Integer mStartPos, Integer n, int column1Index, int column2Index) {
            this.cards = cards;
            this.startPos = mStartPos;
            this.n = n;
            this.column1Index = column1Index;
            this.column2Index = column2Index;
        }

        protected Pair<CardBrowser.CardCollection<CardBrowser.CardCache>, List<Long>> task(Collection col, ProgressSenderAndCancelListener<Integer> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12904)) {
                Timber.d("doInBackgroundRenderBrowserQA");
            }
            List<Long> invalidCardIds = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(12942)) {
                {
                    long _loopCounter226 = 0;
                    // for each specified card in the browser list
                    for (int i = startPos; (ListenerUtil.mutListener.listen(12941) ? (i >= (ListenerUtil.mutListener.listen(12936) ? (startPos % n) : (ListenerUtil.mutListener.listen(12935) ? (startPos / n) : (ListenerUtil.mutListener.listen(12934) ? (startPos * n) : (ListenerUtil.mutListener.listen(12933) ? (startPos - n) : (startPos + n)))))) : (ListenerUtil.mutListener.listen(12940) ? (i <= (ListenerUtil.mutListener.listen(12936) ? (startPos % n) : (ListenerUtil.mutListener.listen(12935) ? (startPos / n) : (ListenerUtil.mutListener.listen(12934) ? (startPos * n) : (ListenerUtil.mutListener.listen(12933) ? (startPos - n) : (startPos + n)))))) : (ListenerUtil.mutListener.listen(12939) ? (i > (ListenerUtil.mutListener.listen(12936) ? (startPos % n) : (ListenerUtil.mutListener.listen(12935) ? (startPos / n) : (ListenerUtil.mutListener.listen(12934) ? (startPos * n) : (ListenerUtil.mutListener.listen(12933) ? (startPos - n) : (startPos + n)))))) : (ListenerUtil.mutListener.listen(12938) ? (i != (ListenerUtil.mutListener.listen(12936) ? (startPos % n) : (ListenerUtil.mutListener.listen(12935) ? (startPos / n) : (ListenerUtil.mutListener.listen(12934) ? (startPos * n) : (ListenerUtil.mutListener.listen(12933) ? (startPos - n) : (startPos + n)))))) : (ListenerUtil.mutListener.listen(12937) ? (i == (ListenerUtil.mutListener.listen(12936) ? (startPos % n) : (ListenerUtil.mutListener.listen(12935) ? (startPos / n) : (ListenerUtil.mutListener.listen(12934) ? (startPos * n) : (ListenerUtil.mutListener.listen(12933) ? (startPos - n) : (startPos + n)))))) : (i < (ListenerUtil.mutListener.listen(12936) ? (startPos % n) : (ListenerUtil.mutListener.listen(12935) ? (startPos / n) : (ListenerUtil.mutListener.listen(12934) ? (startPos * n) : (ListenerUtil.mutListener.listen(12933) ? (startPos - n) : (startPos + n))))))))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter226", ++_loopCounter226);
                        if (!ListenerUtil.mutListener.listen(12906)) {
                            // Stop if cancelled
                            if (collectionTask.isCancelled()) {
                                if (!ListenerUtil.mutListener.listen(12905)) {
                                    Timber.d("doInBackgroundRenderBrowserQA was aborted");
                                }
                                return null;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12918)) {
                            if ((ListenerUtil.mutListener.listen(12917) ? ((ListenerUtil.mutListener.listen(12911) ? (i >= 0) : (ListenerUtil.mutListener.listen(12910) ? (i <= 0) : (ListenerUtil.mutListener.listen(12909) ? (i > 0) : (ListenerUtil.mutListener.listen(12908) ? (i != 0) : (ListenerUtil.mutListener.listen(12907) ? (i == 0) : (i < 0)))))) && (ListenerUtil.mutListener.listen(12916) ? (i <= cards.size()) : (ListenerUtil.mutListener.listen(12915) ? (i > cards.size()) : (ListenerUtil.mutListener.listen(12914) ? (i < cards.size()) : (ListenerUtil.mutListener.listen(12913) ? (i != cards.size()) : (ListenerUtil.mutListener.listen(12912) ? (i == cards.size()) : (i >= cards.size()))))))) : ((ListenerUtil.mutListener.listen(12911) ? (i >= 0) : (ListenerUtil.mutListener.listen(12910) ? (i <= 0) : (ListenerUtil.mutListener.listen(12909) ? (i > 0) : (ListenerUtil.mutListener.listen(12908) ? (i != 0) : (ListenerUtil.mutListener.listen(12907) ? (i == 0) : (i < 0)))))) || (ListenerUtil.mutListener.listen(12916) ? (i <= cards.size()) : (ListenerUtil.mutListener.listen(12915) ? (i > cards.size()) : (ListenerUtil.mutListener.listen(12914) ? (i < cards.size()) : (ListenerUtil.mutListener.listen(12913) ? (i != cards.size()) : (ListenerUtil.mutListener.listen(12912) ? (i == cards.size()) : (i >= cards.size()))))))))) {
                                continue;
                            }
                        }
                        CardBrowser.CardCache card;
                        try {
                            card = cards.get(i);
                        } catch (IndexOutOfBoundsException e) {
                            // we won't reach any more cards.
                            continue;
                        }
                        if (!ListenerUtil.mutListener.listen(12919)) {
                            if (card.isLoaded()) {
                                // We've already rendered the answer, we don't need to do it again.
                                continue;
                            }
                        }
                        // Extract card item
                        try {
                            if (!ListenerUtil.mutListener.listen(12922)) {
                                // Ensure that card still exists.
                                card.getCard();
                            }
                        } catch (WrongId e) {
                            // process
                            long cardId = card.getId();
                            if (!ListenerUtil.mutListener.listen(12920)) {
                                Timber.e(e, "Could not process card '%d' - skipping and removing from sight", cardId);
                            }
                            if (!ListenerUtil.mutListener.listen(12921)) {
                                invalidCardIds.add(cardId);
                            }
                            continue;
                        }
                        if (!ListenerUtil.mutListener.listen(12923)) {
                            // Update item
                            card.load(false, column1Index, column2Index);
                        }
                        float progress = (ListenerUtil.mutListener.listen(12931) ? ((ListenerUtil.mutListener.listen(12927) ? ((float) i % n) : (ListenerUtil.mutListener.listen(12926) ? ((float) i * n) : (ListenerUtil.mutListener.listen(12925) ? ((float) i - n) : (ListenerUtil.mutListener.listen(12924) ? ((float) i + n) : ((float) i / n))))) % 100) : (ListenerUtil.mutListener.listen(12930) ? ((ListenerUtil.mutListener.listen(12927) ? ((float) i % n) : (ListenerUtil.mutListener.listen(12926) ? ((float) i * n) : (ListenerUtil.mutListener.listen(12925) ? ((float) i - n) : (ListenerUtil.mutListener.listen(12924) ? ((float) i + n) : ((float) i / n))))) / 100) : (ListenerUtil.mutListener.listen(12929) ? ((ListenerUtil.mutListener.listen(12927) ? ((float) i % n) : (ListenerUtil.mutListener.listen(12926) ? ((float) i * n) : (ListenerUtil.mutListener.listen(12925) ? ((float) i - n) : (ListenerUtil.mutListener.listen(12924) ? ((float) i + n) : ((float) i / n))))) - 100) : (ListenerUtil.mutListener.listen(12928) ? ((ListenerUtil.mutListener.listen(12927) ? ((float) i % n) : (ListenerUtil.mutListener.listen(12926) ? ((float) i * n) : (ListenerUtil.mutListener.listen(12925) ? ((float) i - n) : (ListenerUtil.mutListener.listen(12924) ? ((float) i + n) : ((float) i / n))))) + 100) : ((ListenerUtil.mutListener.listen(12927) ? ((float) i % n) : (ListenerUtil.mutListener.listen(12926) ? ((float) i * n) : (ListenerUtil.mutListener.listen(12925) ? ((float) i - n) : (ListenerUtil.mutListener.listen(12924) ? ((float) i + n) : ((float) i / n))))) * 100)))));
                        if (!ListenerUtil.mutListener.listen(12932)) {
                            collectionTask.doProgress((int) progress);
                        }
                    }
                }
            }
            return new Pair<>(cards, invalidCardIds);
        }
    }

    public static class CheckDatabase extends Task<String, Pair<Boolean, Collection.CheckDatabaseResult>> {

        protected Pair<Boolean, Collection.CheckDatabaseResult> task(Collection col, ProgressSenderAndCancelListener<String> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12943)) {
                Timber.d("doInBackgroundCheckDatabase");
            }
            // Don't proceed if collection closed
            if (col == null) {
                if (!ListenerUtil.mutListener.listen(12944)) {
                    Timber.e("doInBackgroundCheckDatabase :: supplied collection was null");
                }
                return new Pair<>(false, null);
            }
            Collection.CheckDatabaseResult result = col.fixIntegrity(new TaskManager.ProgressCallback(collectionTask, AnkiDroidApp.getAppResources()));
            if (result.getFailed()) {
                // we can fail due to a locked database, which requires knowledge of the failure.
                return new Pair<>(false, result);
            } else {
                if (!ListenerUtil.mutListener.listen(12945)) {
                    // Close the collection and we restart the app to reload
                    CollectionHelper.getInstance().closeCollection(true, "Check Database Completed");
                }
                return new Pair<>(true, result);
            }
        }
    }

    public static class RepairCollectionn extends Task<Void, Boolean> {

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12946)) {
                Timber.d("doInBackgroundRepairCollection");
            }
            if (!ListenerUtil.mutListener.listen(12949)) {
                if (col != null) {
                    if (!ListenerUtil.mutListener.listen(12947)) {
                        Timber.i("RepairCollection: Closing collection");
                    }
                    if (!ListenerUtil.mutListener.listen(12948)) {
                        col.close(false);
                    }
                }
            }
            return BackupManager.repairCollection(col);
        }
    }

    public static class UpdateValuesFromDeck extends Task<Void, int[]> {

        private final boolean reset;

        public UpdateValuesFromDeck(boolean reset) {
            this.reset = reset;
        }

        public int[] task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12950)) {
                Timber.d("doInBackgroundUpdateValuesFromDeck");
            }
            try {
                AbstractSched sched = col.getSched();
                if (!ListenerUtil.mutListener.listen(12953)) {
                    if (reset) {
                        if (!ListenerUtil.mutListener.listen(12952)) {
                            // reset actually required because of counts, which is used in getCollectionTaskListener
                            sched.resetCounts();
                        }
                    }
                }
                Counts counts = sched.counts();
                int totalNewCount = sched.totalNewForCurrentDeck();
                int totalCount = sched.cardCount();
                return new int[] { counts.getNew(), counts.getLrn(), counts.getRev(), totalNewCount, totalCount, sched.eta(counts) };
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12951)) {
                    Timber.e(e, "doInBackgroundUpdateValuesFromDeck - an error occurred");
                }
                return null;
            }
        }
    }

    public static class DeleteDeck extends Task<Void, int[]> {

        private final long did;

        public DeleteDeck(long did) {
            this.did = did;
        }

        protected int[] task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12954)) {
                Timber.d("doInBackgroundDeleteDeck");
            }
            if (!ListenerUtil.mutListener.listen(12955)) {
                col.getDecks().rem(did, true);
            }
            if (!ListenerUtil.mutListener.listen(12956)) {
                // TODO: if we had "undo delete note" like desktop client then we won't need this.
                col.clearUndo();
            }
            return null;
        }
    }

    public static class RebuildCram extends Task<Void, int[]> {

        protected int[] task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12957)) {
                Timber.d("doInBackgroundRebuildCram");
            }
            if (!ListenerUtil.mutListener.listen(12958)) {
                col.getSched().rebuildDyn(col.getDecks().selected());
            }
            return new UpdateValuesFromDeck(true).task(col, collectionTask);
        }
    }

    public static class EmptyCram extends Task<Void, int[]> {

        protected int[] task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12959)) {
                Timber.d("doInBackgroundEmptyCram");
            }
            if (!ListenerUtil.mutListener.listen(12960)) {
                col.getSched().emptyDyn(col.getDecks().selected());
            }
            return new UpdateValuesFromDeck(true).task(col, collectionTask);
        }
    }

    public static class ImportAdd extends Task<String, Triple<AnkiPackageImporter, Boolean, String>> {

        private final String path;

        public ImportAdd(String path) {
            this.path = path;
        }

        protected Triple<AnkiPackageImporter, Boolean, String> task(Collection col, ProgressSenderAndCancelListener<String> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12961)) {
                Timber.d("doInBackgroundImportAdd");
            }
            Resources res = AnkiDroidApp.getInstance().getBaseContext().getResources();
            AnkiPackageImporter imp = new AnkiPackageImporter(col, path);
            if (!ListenerUtil.mutListener.listen(12962)) {
                imp.setProgressCallback(new TaskManager.ProgressCallback(collectionTask, res));
            }
            try {
                if (!ListenerUtil.mutListener.listen(12963)) {
                    imp.run();
                }
            } catch (ImportExportException e) {
                return new Triple(null, true, e.getMessage());
            }
            return new Triple<>(imp, false, null);
        }
    }

    public static class ImportReplace extends Task<String, BooleanGetter> {

        private final String path;

        public ImportReplace(String path) {
            this.path = path;
        }

        protected BooleanGetter task(Collection col, ProgressSenderAndCancelListener<String> collectionTask) {
            if (!ListenerUtil.mutListener.listen(12964)) {
                Timber.d("doInBackgroundImportReplace");
            }
            Resources res = AnkiDroidApp.getInstance().getBaseContext().getResources();
            Context context = col.getContext();
            // extract the deck from the zip file
            String colPath = CollectionHelper.getCollectionPath(context);
            File dir = new File(new File(colPath).getParentFile(), "tmpzip");
            if (!ListenerUtil.mutListener.listen(12966)) {
                if (dir.exists()) {
                    if (!ListenerUtil.mutListener.listen(12965)) {
                        BackupManager.removeDir(dir);
                    }
                }
            }
            // from anki2.py
            String colname = "collection.anki21";
            ZipFile zip;
            try {
                zip = new ZipFile(new File(path));
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(12967)) {
                    Timber.e(e, "doInBackgroundImportReplace - Error while unzipping");
                }
                if (!ListenerUtil.mutListener.listen(12968)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundImportReplace0");
                }
                return False;
            }
            try {
                if (!ListenerUtil.mutListener.listen(12971)) {
                    // v2 scheduler?
                    if (zip.getEntry(colname) == null) {
                        if (!ListenerUtil.mutListener.listen(12970)) {
                            colname = CollectionHelper.COLLECTION_FILENAME;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12972)) {
                    Utils.unzipFiles(zip, dir.getAbsolutePath(), new String[] { colname, "media" }, null);
                }
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(12969)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundImportReplace - unzip");
                }
                return False;
            }
            String colFile = new File(dir, colname).getAbsolutePath();
            if (!(new File(colFile)).exists()) {
                return False;
            }
            Collection tmpCol = null;
            try {
                if (!ListenerUtil.mutListener.listen(12978)) {
                    tmpCol = Storage.Collection(context, colFile);
                }
                if (!tmpCol.validCollection()) {
                    if (!ListenerUtil.mutListener.listen(12979)) {
                        tmpCol.close();
                    }
                    return False;
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(12973)) {
                    Timber.e("Error opening new collection file... probably it's invalid");
                }
                try {
                    if (!ListenerUtil.mutListener.listen(12974)) {
                        tmpCol.close();
                    }
                } catch (Exception e2) {
                }
                if (!ListenerUtil.mutListener.listen(12975)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundImportReplace - open col");
                }
                return False;
            } finally {
                if (!ListenerUtil.mutListener.listen(12977)) {
                    if (tmpCol != null) {
                        if (!ListenerUtil.mutListener.listen(12976)) {
                            tmpCol.close();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(12980)) {
                collectionTask.doProgress(res.getString(R.string.importing_collection));
            }
            try {
                if (!ListenerUtil.mutListener.listen(12981)) {
                    CollectionHelper.getInstance().getCol(context);
                }
                // unload collection and trigger a backup
                Time time = CollectionHelper.getInstance().getTimeSafe(context);
                if (!ListenerUtil.mutListener.listen(12982)) {
                    CollectionHelper.getInstance().closeCollection(true, "Importing new collection");
                }
                if (!ListenerUtil.mutListener.listen(12983)) {
                    CollectionHelper.getInstance().lockCollection();
                }
                if (!ListenerUtil.mutListener.listen(12984)) {
                    BackupManager.performBackupInBackground(colPath, true, time);
                }
            } catch (Exception e) {
            }
            // overwrite collection
            File f = new File(colFile);
            if (!f.renameTo(new File(colPath))) {
                // Exit early if this didn't work
                return False;
            }
            int addedCount = -1;
            try {
                if (!ListenerUtil.mutListener.listen(12991)) {
                    CollectionHelper.getInstance().unlockCollection();
                }
                // import media
                HashMap<String, String> nameToNum = new HashMap<>();
                HashMap<String, String> numToName = new HashMap<>();
                File mediaMapFile = new File(dir.getAbsolutePath(), "media");
                if (!ListenerUtil.mutListener.listen(12997)) {
                    if (mediaMapFile.exists()) {
                        JsonReader jr = new JsonReader(new FileReader(mediaMapFile));
                        if (!ListenerUtil.mutListener.listen(12992)) {
                            jr.beginObject();
                        }
                        String name;
                        String num;
                        {
                            long _loopCounter227 = 0;
                            while (jr.hasNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter227", ++_loopCounter227);
                                num = jr.nextName();
                                name = jr.nextString();
                                if (!ListenerUtil.mutListener.listen(12993)) {
                                    nameToNum.put(name, num);
                                }
                                if (!ListenerUtil.mutListener.listen(12994)) {
                                    numToName.put(num, name);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12995)) {
                            jr.endObject();
                        }
                        if (!ListenerUtil.mutListener.listen(12996)) {
                            jr.close();
                        }
                    }
                }
                String mediaDir = Media.getCollectionMediaPath(colPath);
                int total = nameToNum.size();
                int i = 0;
                if (!ListenerUtil.mutListener.listen(13014)) {
                    {
                        long _loopCounter228 = 0;
                        for (Map.Entry<String, String> entry : nameToNum.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter228", ++_loopCounter228);
                            String file = entry.getKey();
                            String c = entry.getValue();
                            File of = new File(mediaDir, file);
                            if (!ListenerUtil.mutListener.listen(12999)) {
                                if (!of.exists()) {
                                    if (!ListenerUtil.mutListener.listen(12998)) {
                                        Utils.unzipFiles(zip, mediaDir, new String[] { c }, numToName);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(13000)) {
                                ++i;
                            }
                            if (!ListenerUtil.mutListener.listen(13013)) {
                                collectionTask.doProgress(res.getString(R.string.import_media_count, (ListenerUtil.mutListener.listen(13012) ? ((ListenerUtil.mutListener.listen(13008) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) % 100) : (ListenerUtil.mutListener.listen(13007) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) / 100) : (ListenerUtil.mutListener.listen(13006) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) - 100) : (ListenerUtil.mutListener.listen(13005) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) + 100) : (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) * 100))))) % total) : (ListenerUtil.mutListener.listen(13011) ? ((ListenerUtil.mutListener.listen(13008) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) % 100) : (ListenerUtil.mutListener.listen(13007) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) / 100) : (ListenerUtil.mutListener.listen(13006) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) - 100) : (ListenerUtil.mutListener.listen(13005) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) + 100) : (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) * 100))))) * total) : (ListenerUtil.mutListener.listen(13010) ? ((ListenerUtil.mutListener.listen(13008) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) % 100) : (ListenerUtil.mutListener.listen(13007) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) / 100) : (ListenerUtil.mutListener.listen(13006) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) - 100) : (ListenerUtil.mutListener.listen(13005) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) + 100) : (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) * 100))))) - total) : (ListenerUtil.mutListener.listen(13009) ? ((ListenerUtil.mutListener.listen(13008) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) % 100) : (ListenerUtil.mutListener.listen(13007) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) / 100) : (ListenerUtil.mutListener.listen(13006) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) - 100) : (ListenerUtil.mutListener.listen(13005) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) + 100) : (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) * 100))))) + total) : ((ListenerUtil.mutListener.listen(13008) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) % 100) : (ListenerUtil.mutListener.listen(13007) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) / 100) : (ListenerUtil.mutListener.listen(13006) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) - 100) : (ListenerUtil.mutListener.listen(13005) ? (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) + 100) : (((ListenerUtil.mutListener.listen(13004) ? (i % 1) : (ListenerUtil.mutListener.listen(13003) ? (i / 1) : (ListenerUtil.mutListener.listen(13002) ? (i * 1) : (ListenerUtil.mutListener.listen(13001) ? (i - 1) : (i + 1)))))) * 100))))) / total)))))));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13015)) {
                    zip.close();
                }
                if (!ListenerUtil.mutListener.listen(13016)) {
                    // delete tmp dir
                    BackupManager.removeDir(dir);
                }
                return True;
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(12985)) {
                    Timber.e(e, "doInBackgroundImportReplace - RuntimeException");
                }
                if (!ListenerUtil.mutListener.listen(12986)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundImportReplace1");
                }
                return False;
            } catch (FileNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(12987)) {
                    Timber.e(e, "doInBackgroundImportReplace - FileNotFoundException");
                }
                if (!ListenerUtil.mutListener.listen(12988)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundImportReplace2");
                }
                return False;
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(12989)) {
                    Timber.e(e, "doInBackgroundImportReplace - IOException");
                }
                if (!ListenerUtil.mutListener.listen(12990)) {
                    AnkiDroidApp.sendExceptionReport(e, "doInBackgroundImportReplace3");
                }
                return False;
            }
        }
    }

    public static class ExportApkg extends Task<Void, Pair<Boolean, String>> {

        private final String apkgPath;

        private final Long did;

        private final Boolean includeSched;

        private final Boolean includeMedia;

        public ExportApkg(String apkgPath, Long did, Boolean includeSched, Boolean includeMedia) {
            this.apkgPath = apkgPath;
            this.did = did;
            this.includeSched = includeSched;
            this.includeMedia = includeMedia;
        }

        protected Pair<Boolean, String> task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13017)) {
                Timber.d("doInBackgroundExportApkg");
            }
            try {
                AnkiPackageExporter exporter = new AnkiPackageExporter(col);
                if (!ListenerUtil.mutListener.listen(13022)) {
                    exporter.setIncludeSched(includeSched);
                }
                if (!ListenerUtil.mutListener.listen(13023)) {
                    exporter.setIncludeMedia(includeMedia);
                }
                if (!ListenerUtil.mutListener.listen(13024)) {
                    exporter.setDid(did);
                }
                if (!ListenerUtil.mutListener.listen(13025)) {
                    exporter.exportInto(apkgPath, col.getContext());
                }
            } catch (FileNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(13018)) {
                    Timber.e(e, "FileNotFoundException in doInBackgroundExportApkg");
                }
                return new Pair<>(false, null);
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(13019)) {
                    Timber.e(e, "IOException in doInBackgroundExportApkg");
                }
                return new Pair<>(false, null);
            } catch (JSONException e) {
                if (!ListenerUtil.mutListener.listen(13020)) {
                    Timber.e(e, "JSOnException in doInBackgroundExportApkg");
                }
                return new Pair<>(false, null);
            } catch (ImportExportException e) {
                if (!ListenerUtil.mutListener.listen(13021)) {
                    Timber.e(e, "ImportExportException in doInBackgroundExportApkg");
                }
                return new Pair<>(true, e.getMessage());
            }
            return new Pair<>(false, apkgPath);
        }
    }

    public static class Reorder extends Task<Void, Boolean> {

        private final DeckConfig conf;

        public Reorder(DeckConfig conf) {
            this.conf = conf;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13026)) {
                Timber.d("doInBackgroundReorder");
            }
            if (!ListenerUtil.mutListener.listen(13027)) {
                col.getSched().resortConf(conf);
            }
            return true;
        }
    }

    public static class ConfChange extends Task<Void, Boolean> {

        private final Deck deck;

        private final DeckConfig conf;

        public ConfChange(Deck deck, DeckConfig conf) {
            this.deck = deck;
            this.conf = conf;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13028)) {
                Timber.d("doInBackgroundConfChange");
            }
            try {
                long newConfId = conf.getLong("id");
                // If new config has a different sorting order, reorder the cards
                int oldOrder = col.getDecks().getConf(deck.getLong("conf")).getJSONObject("new").getInt("order");
                int newOrder = col.getDecks().getConf(newConfId).getJSONObject("new").getInt("order");
                if (!ListenerUtil.mutListener.listen(13037)) {
                    if ((ListenerUtil.mutListener.listen(13033) ? (oldOrder >= newOrder) : (ListenerUtil.mutListener.listen(13032) ? (oldOrder <= newOrder) : (ListenerUtil.mutListener.listen(13031) ? (oldOrder > newOrder) : (ListenerUtil.mutListener.listen(13030) ? (oldOrder < newOrder) : (ListenerUtil.mutListener.listen(13029) ? (oldOrder == newOrder) : (oldOrder != newOrder))))))) {
                        if (!ListenerUtil.mutListener.listen(13036)) {
                            switch(newOrder) {
                                case 0:
                                    if (!ListenerUtil.mutListener.listen(13034)) {
                                        col.getSched().randomizeCards(deck.getLong("id"));
                                    }
                                    break;
                                case 1:
                                    if (!ListenerUtil.mutListener.listen(13035)) {
                                        col.getSched().orderCards(deck.getLong("id"));
                                    }
                                    break;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13038)) {
                    col.getDecks().setConf(deck, newConfId);
                }
                if (!ListenerUtil.mutListener.listen(13039)) {
                    col.save();
                }
                return true;
            } catch (JSONException e) {
                return false;
            }
        }
    }

    public static class ConfReset extends Task<Void, Boolean> {

        private final DeckConfig conf;

        public ConfReset(DeckConfig conf) {
            this.conf = conf;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13040)) {
                Timber.d("doInBackgroundConfReset");
            }
            if (!ListenerUtil.mutListener.listen(13041)) {
                col.getDecks().restoreToDefault(conf);
            }
            if (!ListenerUtil.mutListener.listen(13042)) {
                col.save();
            }
            return null;
        }
    }

    public static class ConfRemove extends Task<Void, Boolean> {

        private final DeckConfig conf;

        public ConfRemove(DeckConfig conf) {
            this.conf = conf;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13043)) {
                Timber.d("doInBackgroundConfRemove");
            }
            try {
                // Cards must be reordered according to the default conf.
                int order = conf.getJSONObject("new").getInt("order");
                int defaultOrder = col.getDecks().getConf(1).getJSONObject("new").getInt("order");
                if (!ListenerUtil.mutListener.listen(13051)) {
                    if ((ListenerUtil.mutListener.listen(13048) ? (order >= defaultOrder) : (ListenerUtil.mutListener.listen(13047) ? (order <= defaultOrder) : (ListenerUtil.mutListener.listen(13046) ? (order > defaultOrder) : (ListenerUtil.mutListener.listen(13045) ? (order < defaultOrder) : (ListenerUtil.mutListener.listen(13044) ? (order == defaultOrder) : (order != defaultOrder))))))) {
                        if (!ListenerUtil.mutListener.listen(13049)) {
                            conf.getJSONObject("new").put("order", defaultOrder);
                        }
                        if (!ListenerUtil.mutListener.listen(13050)) {
                            col.getSched().resortConf(conf);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13052)) {
                    col.save();
                }
                return true;
            } catch (JSONException e) {
                return false;
            }
        }
    }

    public static class ConfSetSubdecks extends Task<Void, Boolean> {

        private final Deck deck;

        private final DeckConfig conf;

        public ConfSetSubdecks(Deck deck, DeckConfig conf) {
            this.deck = deck;
            this.conf = conf;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13053)) {
                Timber.d("doInBackgroundConfSetSubdecks");
            }
            try {
                TreeMap<String, Long> children = col.getDecks().children(deck.getLong("id"));
                {
                    long _loopCounter229 = 0;
                    for (long childDid : children.values()) {
                        ListenerUtil.loopListener.listen("_loopCounter229", ++_loopCounter229);
                        Deck child = col.getDecks().get(childDid);
                        if (!ListenerUtil.mutListener.listen(13054)) {
                            if (child.getInt("dyn") == DECK_DYN) {
                                continue;
                            }
                        }
                        boolean changed = new ConfChange(child, conf).task(col, collectionTask);
                        if (!changed) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (JSONException e) {
                return false;
            }
        }
    }

    /**
     * @return The results list from the check, or false if any errors.
     */
    public static class CheckMedia extends Task<Void, PairWithBoolean<List<List<String>>>> {

        @Override
        protected PairWithBoolean<List<List<String>>> task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13055)) {
                Timber.d("doInBackgroundCheckMedia");
            }
            // Ensure that the DB is valid - unknown why, but some users were missing the meta table.
            try {
                if (!ListenerUtil.mutListener.listen(13056)) {
                    col.getMedia().rebuildIfInvalid();
                }
            } catch (IOException e) {
                return new PairWithBoolean<>(false, null);
            }
            if (!ListenerUtil.mutListener.listen(13057)) {
                // A media check on AnkiDroid will also update the media db
                col.getMedia().findChanges(true);
            }
            // Then do the actual check
            return new PairWithBoolean<>(true, col.getMedia().check());
        }
    }

    public static class DeleteMedia extends Task<Void, Integer> {

        private final List<String> unused;

        public DeleteMedia(List<String> unused) {
            this.unused = unused;
        }

        protected Integer task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            com.ichi2.libanki.Media m = col.getMedia();
            if (!ListenerUtil.mutListener.listen(13059)) {
                {
                    long _loopCounter230 = 0;
                    for (String fname : unused) {
                        ListenerUtil.loopListener.listen("_loopCounter230", ++_loopCounter230);
                        if (!ListenerUtil.mutListener.listen(13058)) {
                            m.removeFile(fname);
                        }
                    }
                }
            }
            return unused.size();
        }
    }

    /**
     * Handles everything for a model change at once - template add / deletes as well as content updates
     */
    public static class SaveModel extends Task<Void, Pair<Boolean, String>> {

        private final Model model;

        private final ArrayList<Object[]> templateChanges;

        public SaveModel(Model model, ArrayList<Object[]> templateChanges) {
            this.model = model;
            this.templateChanges = templateChanges;
        }

        protected Pair<Boolean, String> task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13060)) {
                Timber.d("doInBackgroundSaveModel");
            }
            Model oldModel = col.getModels().get(model.getLong("id"));
            // - undo (except for cards) could just be Models.update(model) / Models.flush() / Collection.reset() (that was prior "undo")
            JSONArray newTemplates = model.getJSONArray("tmpls");
            if (!ListenerUtil.mutListener.listen(13061)) {
                col.getDb().getDatabase().beginTransaction();
            }
            try {
                if (!ListenerUtil.mutListener.listen(13071)) {
                    {
                        long _loopCounter231 = 0;
                        for (Object[] change : templateChanges) {
                            ListenerUtil.loopListener.listen("_loopCounter231", ++_loopCounter231);
                            JSONArray oldTemplates = oldModel.getJSONArray("tmpls");
                            if (!ListenerUtil.mutListener.listen(13070)) {
                                switch((TemporaryModel.ChangeType) change[1]) {
                                    case ADD:
                                        if (!ListenerUtil.mutListener.listen(13063)) {
                                            Timber.d("doInBackgroundSaveModel() adding template %s", change[0]);
                                        }
                                        try {
                                            if (!ListenerUtil.mutListener.listen(13065)) {
                                                col.getModels().addTemplate(oldModel, newTemplates.getJSONObject((int) change[0]));
                                            }
                                        } catch (Exception e) {
                                            if (!ListenerUtil.mutListener.listen(13064)) {
                                                Timber.e(e, "Unable to add template %s to model %s", change[0], model.getLong("id"));
                                            }
                                            return new Pair<>(false, e.getLocalizedMessage());
                                        }
                                        break;
                                    case DELETE:
                                        if (!ListenerUtil.mutListener.listen(13066)) {
                                            Timber.d("doInBackgroundSaveModel() deleting template currently at ordinal %s", change[0]);
                                        }
                                        try {
                                            if (!ListenerUtil.mutListener.listen(13068)) {
                                                col.getModels().remTemplate(oldModel, oldTemplates.getJSONObject((int) change[0]));
                                            }
                                        } catch (Exception e) {
                                            if (!ListenerUtil.mutListener.listen(13067)) {
                                                Timber.e(e, "Unable to delete template %s from model %s", change[0], model.getLong("id"));
                                            }
                                            return new Pair<>(false, e.getLocalizedMessage());
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(13069)) {
                                            Timber.w("Unknown change type? %s", change[1]);
                                        }
                                        break;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(13072)) {
                    col.getModels().save(model, true);
                }
                if (!ListenerUtil.mutListener.listen(13073)) {
                    col.getModels().update(model);
                }
                if (!ListenerUtil.mutListener.listen(13074)) {
                    col.reset();
                }
                if (!ListenerUtil.mutListener.listen(13075)) {
                    col.save();
                }
                if (!ListenerUtil.mutListener.listen(13078)) {
                    if (col.getDb().getDatabase().inTransaction()) {
                        if (!ListenerUtil.mutListener.listen(13077)) {
                            col.getDb().getDatabase().setTransactionSuccessful();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(13076)) {
                            Timber.i("CollectionTask::SaveModel was not in a transaction? Cannot mark transaction successful.");
                        }
                    }
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(13062)) {
                    DB.safeEndInTransaction(col.getDb());
                }
            }
            return new Pair<>(true, null);
        }
    }

    /*
     * Async task for the ModelBrowser Class
     * Returns an ArrayList of all models alphabetically ordered and the number of notes
     * associated with each model.
     *
     * @return {ArrayList<JSONObject> models, ArrayList<Integer> cardCount}
     */
    public static class CountModels extends Task<Void, Pair<ArrayList<Model>, ArrayList<Integer>>> {

        protected Pair<ArrayList<Model>, ArrayList<Integer>> task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13079)) {
                Timber.d("doInBackgroundLoadModels");
            }
            ArrayList<Model> models = col.getModels().all();
            ArrayList<Integer> cardCount = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(13080)) {
                Collections.sort(models, (Comparator<JSONObject>) (a, b) -> a.getString("name").compareTo(b.getString("name")));
            }
            if (!ListenerUtil.mutListener.listen(13084)) {
                {
                    long _loopCounter232 = 0;
                    for (Model n : models) {
                        ListenerUtil.loopListener.listen("_loopCounter232", ++_loopCounter232);
                        if (!ListenerUtil.mutListener.listen(13082)) {
                            if (collectionTask.isCancelled()) {
                                if (!ListenerUtil.mutListener.listen(13081)) {
                                    Timber.e("doInBackgroundLoadModels :: Cancelled");
                                }
                                // onPostExecute not executed if cancelled. Return value not used.
                                return null;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(13083)) {
                            cardCount.add(col.getModels().useCount(n));
                        }
                    }
                }
            }
            return new Pair<>(models, cardCount);
        }
    }

    /**
     * Deletes the given model
     * and all notes associated with it
     */
    public static class DeleteModel extends Task<Void, Boolean> {

        private final long modID;

        public DeleteModel(long modID) {
            this.modID = modID;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13085)) {
                Timber.d("doInBackGroundDeleteModel");
            }
            try {
                if (!ListenerUtil.mutListener.listen(13087)) {
                    col.getModels().rem(col.getModels().get(modID));
                }
                if (!ListenerUtil.mutListener.listen(13088)) {
                    col.save();
                }
            } catch (ConfirmModSchemaException e) {
                if (!ListenerUtil.mutListener.listen(13086)) {
                    Timber.e("doInBackGroundDeleteModel :: ConfirmModSchemaException");
                }
                return false;
            }
            return true;
        }
    }

    /**
     * Deletes the given field in the given model
     */
    public static class DeleteField extends Task<Void, Boolean> {

        private final Model model;

        private final JSONObject field;

        public DeleteField(Model model, JSONObject field) {
            this.model = model;
            this.field = field;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13089)) {
                Timber.d("doInBackGroundDeleteField");
            }
            try {
                if (!ListenerUtil.mutListener.listen(13090)) {
                    col.getModels().remField(model, field);
                }
                if (!ListenerUtil.mutListener.listen(13091)) {
                    col.save();
                }
            } catch (ConfirmModSchemaException e) {
                // Should never be reached
                return false;
            }
            return true;
        }
    }

    /**
     * Repositions the given field in the given model
     */
    public static class RepositionField extends Task<Void, Boolean> {

        private final Model model;

        private final JSONObject field;

        private final int index;

        public RepositionField(Model model, JSONObject field, int index) {
            this.model = model;
            this.field = field;
            this.index = index;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13092)) {
                Timber.d("doInBackgroundRepositionField");
            }
            try {
                if (!ListenerUtil.mutListener.listen(13093)) {
                    col.getModels().moveField(model, field, index);
                }
                if (!ListenerUtil.mutListener.listen(13094)) {
                    col.save();
                }
            } catch (ConfirmModSchemaException e) {
                // Should never be reached
                return false;
            }
            return true;
        }
    }

    /**
     * Adds a field with name in given model
     */
    public static class AddField extends Task<Void, Boolean> {

        private final Model model;

        private final String fieldName;

        public AddField(Model model, String fieldName) {
            this.model = model;
            this.fieldName = fieldName;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13095)) {
                Timber.d("doInBackgroundRepositionField");
            }
            if (!ListenerUtil.mutListener.listen(13096)) {
                col.getModels().addFieldModChanged(model, col.getModels().newField(fieldName));
            }
            if (!ListenerUtil.mutListener.listen(13097)) {
                col.save();
            }
            return true;
        }
    }

    /**
     * Adds a field of with name in given model
     */
    public static class ChangeSortField extends Task<Void, Boolean> {

        private final Model model;

        private final int idx;

        public ChangeSortField(Model model, int idx) {
            this.model = model;
            this.idx = idx;
        }

        protected Boolean task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            try {
                if (!ListenerUtil.mutListener.listen(13099)) {
                    Timber.d("doInBackgroundChangeSortField");
                }
                if (!ListenerUtil.mutListener.listen(13100)) {
                    col.getModels().setSortIdx(model, idx);
                }
                if (!ListenerUtil.mutListener.listen(13101)) {
                    col.save();
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(13098)) {
                    Timber.e(e, "Error changing sort field");
                }
                return false;
            }
            return true;
        }
    }

    public static class FindEmptyCards extends Task<Integer, List<Long>> {

        protected List<Long> task(Collection col, ProgressSenderAndCancelListener<Integer> collectionTask) {
            return col.emptyCids(collectionTask);
        }
    }

    /**
     * Goes through selected cards and checks selected and marked attribute
     * @return If there are unselected cards, if there are unmarked cards
     */
    public static class CheckCardSelection extends Task<Void, Pair<Boolean, Boolean>> {

        private final CardBrowser.CardCollection<CardBrowser.CardCache> checkedCards;

        public CheckCardSelection(CardBrowser.CardCollection<CardBrowser.CardCache> checkedCards) {
            this.checkedCards = checkedCards;
        }

        @Nullable
        protected Pair<Boolean, Boolean> task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            boolean hasUnsuspended = false;
            boolean hasUnmarked = false;
            if (!ListenerUtil.mutListener.listen(13115)) {
                {
                    long _loopCounter233 = 0;
                    for (CardBrowser.CardCache c : checkedCards) {
                        ListenerUtil.loopListener.listen("_loopCounter233", ++_loopCounter233);
                        if (!ListenerUtil.mutListener.listen(13103)) {
                            if (collectionTask.isCancelled()) {
                                if (!ListenerUtil.mutListener.listen(13102)) {
                                    Timber.v("doInBackgroundCheckCardSelection: cancelled.");
                                }
                                return null;
                            }
                        }
                        Card card = c.getCard();
                        if (!ListenerUtil.mutListener.listen(13110)) {
                            hasUnsuspended = (ListenerUtil.mutListener.listen(13109) ? (hasUnsuspended && (ListenerUtil.mutListener.listen(13108) ? (card.getQueue() >= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13107) ? (card.getQueue() <= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13106) ? (card.getQueue() > Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13105) ? (card.getQueue() < Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13104) ? (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED) : (card.getQueue() != Consts.QUEUE_TYPE_SUSPENDED))))))) : (hasUnsuspended || (ListenerUtil.mutListener.listen(13108) ? (card.getQueue() >= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13107) ? (card.getQueue() <= Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13106) ? (card.getQueue() > Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13105) ? (card.getQueue() < Consts.QUEUE_TYPE_SUSPENDED) : (ListenerUtil.mutListener.listen(13104) ? (card.getQueue() == Consts.QUEUE_TYPE_SUSPENDED) : (card.getQueue() != Consts.QUEUE_TYPE_SUSPENDED))))))));
                        }
                        if (!ListenerUtil.mutListener.listen(13112)) {
                            hasUnmarked = (ListenerUtil.mutListener.listen(13111) ? (hasUnmarked && !card.note().hasTag("marked")) : (hasUnmarked || !card.note().hasTag("marked")));
                        }
                        if (!ListenerUtil.mutListener.listen(13114)) {
                            if ((ListenerUtil.mutListener.listen(13113) ? (hasUnsuspended || hasUnmarked) : (hasUnsuspended && hasUnmarked)))
                                break;
                        }
                    }
                }
            }
            return new Pair<>(hasUnsuspended, hasUnmarked);
        }
    }

    public static class PreloadNextCard extends Task<Void, Void> {

        public Void task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            try {
                if (!ListenerUtil.mutListener.listen(13117)) {
                    // Ensure counts are recomputed if necessary, to know queue to look for
                    col.getSched().counts();
                }
                if (!ListenerUtil.mutListener.listen(13118)) {
                    col.getSched().preloadNextCard();
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(13116)) {
                    Timber.e(e, "doInBackgroundPreloadNextCard - RuntimeException on preloading card");
                }
            }
            return null;
        }
    }

    public static class LoadCollectionComplete extends Task<Void, Void> {

        protected Void task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13120)) {
                if (col != null) {
                    if (!ListenerUtil.mutListener.listen(13119)) {
                        CollectionHelper.loadCollectionComplete(col);
                    }
                }
            }
            return null;
        }
    }

    public static class Reset extends Task<Void, Void> {

        public Void task(Collection col, ProgressSenderAndCancelListener<Void> collectionTask) {
            if (!ListenerUtil.mutListener.listen(13121)) {
                col.getSched().reset();
            }
            return null;
        }
    }
}
