/**
 * *************************************************************************************
 *  Copyright (c) 2011 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *  Copyright (c) 2014 Bruno Romero de Azevedo <brunodea@inf.ufsm.br>                    *
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
package com.ichi2.anki;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.ichi2.anki.cardviewer.CardAppearance;
import com.ichi2.anki.dialogs.ConfirmationDialog;
import com.ichi2.anki.multimediacard.AudioView;
import com.ichi2.anki.dialogs.RescheduleDialog;
import com.ichi2.anki.reviewer.PeripheralKeymap;
import com.ichi2.anki.reviewer.ReviewerUi;
import com.ichi2.anki.workarounds.FirefoxSnackbarWorkaround;
import com.ichi2.anki.reviewer.ActionButtons;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListener;
import com.ichi2.async.TaskManager;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Collection.DismissType;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.sched.Counts;
import com.ichi2.themes.Themes;
import com.ichi2.utils.AndroidUiUtils;
import com.ichi2.utils.FunctionalInterfaces.Consumer;
import com.ichi2.utils.PairWithBoolean;
import com.ichi2.utils.Permissions;
import com.ichi2.widget.WidgetStatus;
import java.lang.ref.WeakReference;
import java.util.Collections;
import timber.log.Timber;
import static com.ichi2.anki.reviewer.CardMarker.*;
import static com.ichi2.anki.cardviewer.ViewerCommand.COMMAND_NOTHING;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Reviewer extends AbstractFlashcardViewer {

    private boolean mHasDrawerSwipeConflicts = false;

    private boolean mShowWhiteboard = true;

    private boolean mPrefFullscreenReview = false;

    private static final int ADD_NOTE = 12;

    private static final int REQUEST_AUDIO_PERMISSION = 0;

    private LinearLayout colorPalette;

    // Card counts
    private SpannableString newCount;

    private SpannableString lrnCount;

    private SpannableString revCount;

    private TextView mTextBarNew;

    private TextView mTextBarLearn;

    private TextView mTextBarReview;

    private boolean mPrefHideDueCount;

    // ETA
    private int eta;

    private boolean mPrefShowETA;

    // Preferences from the collection
    private boolean mShowRemainingCardCount;

    private final ActionButtons mActionButtons = new ActionButtons(this);

    private final ScheduleCollectionTaskListener mRescheduleCardHandler = new ScheduleCollectionTaskListener() {

        protected int getToastResourceId() {
            return R.plurals.reschedule_cards_dialog_acknowledge;
        }
    };

    private final ScheduleCollectionTaskListener mResetProgressCardHandler = new ScheduleCollectionTaskListener() {

        protected int getToastResourceId() {
            return R.plurals.reset_cards_dialog_acknowledge;
        }
    };

    @VisibleForTesting
    protected final PeripheralKeymap mProcessor = new PeripheralKeymap(this, this);

    /**
     * We need to listen for and handle reschedules / resets very similarly
     */
    abstract class ScheduleCollectionTaskListener extends NextCardHandler<PairWithBoolean<Card[]>> {

        protected abstract int getToastResourceId();

        @Override
        public void onPostExecute(PairWithBoolean<Card[]> result) {
            if (!ListenerUtil.mutListener.listen(10961)) {
                super.onPostExecute(result);
            }
            if (!ListenerUtil.mutListener.listen(10962)) {
                invalidateOptionsMenu();
            }
            int cardCount = result.other.length;
            if (!ListenerUtil.mutListener.listen(10963)) {
                UIUtils.showThemedToast(Reviewer.this, getResources().getQuantityString(getToastResourceId(), cardCount, cardCount), true);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10964)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10965)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(10966)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(10969)) {
            if (FirefoxSnackbarWorkaround.handledLaunchFromWebBrowser(getIntent(), this)) {
                if (!ListenerUtil.mutListener.listen(10967)) {
                    this.setResult(RESULT_CANCELED);
                }
                if (!ListenerUtil.mutListener.listen(10968)) {
                    finishWithAnimation(RIGHT);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10972)) {
            if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
                if (!ListenerUtil.mutListener.listen(10970)) {
                    Timber.d("onCreate() :: received Intent with action = %s", getIntent().getAction());
                }
                if (!ListenerUtil.mutListener.listen(10971)) {
                    selectDeckFromExtra();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10973)) {
            colorPalette = findViewById(R.id.whiteboard_pen_color);
        }
        if (!ListenerUtil.mutListener.listen(10974)) {
            startLoadingCollection();
        }
    }

    @Override
    protected int getFlagToDisplay() {
        int actualValue = super.getFlagToDisplay();
        if (!ListenerUtil.mutListener.listen(10980)) {
            if ((ListenerUtil.mutListener.listen(10979) ? (actualValue >= FLAG_NONE) : (ListenerUtil.mutListener.listen(10978) ? (actualValue <= FLAG_NONE) : (ListenerUtil.mutListener.listen(10977) ? (actualValue > FLAG_NONE) : (ListenerUtil.mutListener.listen(10976) ? (actualValue < FLAG_NONE) : (ListenerUtil.mutListener.listen(10975) ? (actualValue != FLAG_NONE) : (actualValue == FLAG_NONE))))))) {
                return FLAG_NONE;
            }
        }
        Boolean isShownInActionBar = mActionButtons.isShownInActionBar(ActionButtons.RES_FLAG);
        if (!ListenerUtil.mutListener.listen(10982)) {
            if ((ListenerUtil.mutListener.listen(10981) ? (isShownInActionBar != null || isShownInActionBar) : (isShownInActionBar != null && isShownInActionBar))) {
                return FLAG_NONE;
            }
        }
        return actualValue;
    }

    @Override
    protected WebView createWebView() {
        WebView ret = super.createWebView();
        if (!ListenerUtil.mutListener.listen(10984)) {
            if (AndroidUiUtils.isRunningOnTv(this)) {
                if (!ListenerUtil.mutListener.listen(10983)) {
                    ret.setFocusable(false);
                }
            }
        }
        return ret;
    }

    @Override
    protected boolean shouldDisplayMark() {
        boolean markValue = super.shouldDisplayMark();
        if (!ListenerUtil.mutListener.listen(10985)) {
            if (!markValue) {
                return false;
            }
        }
        Boolean isShownInActionBar = mActionButtons.isShownInActionBar(ActionButtons.RES_MARK);
        // Otherwise, if it's in the action bar, don't show it again.
        return (ListenerUtil.mutListener.listen(10986) ? (isShownInActionBar == null && !isShownInActionBar) : (isShownInActionBar == null || !isShownInActionBar));
    }

    private void selectDeckFromExtra() {
        Bundle extras = getIntent().getExtras();
        long did = Long.MIN_VALUE;
        if (!ListenerUtil.mutListener.listen(10988)) {
            if (extras != null) {
                if (!ListenerUtil.mutListener.listen(10987)) {
                    did = extras.getLong("deckId", Long.MIN_VALUE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10994)) {
            if ((ListenerUtil.mutListener.listen(10993) ? (did >= Long.MIN_VALUE) : (ListenerUtil.mutListener.listen(10992) ? (did <= Long.MIN_VALUE) : (ListenerUtil.mutListener.listen(10991) ? (did > Long.MIN_VALUE) : (ListenerUtil.mutListener.listen(10990) ? (did < Long.MIN_VALUE) : (ListenerUtil.mutListener.listen(10989) ? (did != Long.MIN_VALUE) : (did == Long.MIN_VALUE))))))) {
                // deckId is not set, load default
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10995)) {
            Timber.d("selectDeckFromExtra() with deckId = %d", did);
        }
        if (!ListenerUtil.mutListener.listen(10997)) {
            // Clear the undo history when selecting a new deck
            if (getCol().getDecks().selected() != did) {
                if (!ListenerUtil.mutListener.listen(10996)) {
                    getCol().clearUndo();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10998)) {
            // Select the deck
            getCol().getDecks().select(did);
        }
        if (!ListenerUtil.mutListener.listen(10999)) {
            // Reset the schedule so that we get the counts for the currently selected deck
            getCol().getSched().deferReset();
        }
    }

    @Override
    protected void setTitle() {
        String title;
        if (colIsOpen()) {
            title = Decks.basename(getCol().getDecks().current().getString("name"));
        } else {
            if (!ListenerUtil.mutListener.listen(11000)) {
                Timber.e("Could not set title in reviewer because collection closed");
            }
            title = "";
        }
        if (!ListenerUtil.mutListener.listen(11001)) {
            getSupportActionBar().setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(11002)) {
            super.setTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(11003)) {
            getSupportActionBar().setSubtitle("");
        }
    }

    @Override
    protected int getContentViewAttr(int fullscreenMode) {
        switch(fullscreenMode) {
            case 1:
                return R.layout.reviewer_fullscreen;
            case 2:
                return R.layout.reviewer_fullscreen_noanswers;
            default:
                return R.layout.reviewer;
        }
    }

    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(11004)) {
            super.onCollectionLoaded(col);
        }
        if (!ListenerUtil.mutListener.listen(11005)) {
            mPrefWhiteboard = MetaDB.getWhiteboardState(this, getParentDid());
        }
        if (!ListenerUtil.mutListener.listen(11008)) {
            if (mPrefWhiteboard) {
                // DEFECT: Slight inefficiency here, as we set the database using these methods
                boolean whiteboardVisibility = MetaDB.getWhiteboardVisibility(this, getParentDid());
                if (!ListenerUtil.mutListener.listen(11006)) {
                    setWhiteboardEnabledState(true);
                }
                if (!ListenerUtil.mutListener.listen(11007)) {
                    setWhiteboardVisibility(whiteboardVisibility);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11009)) {
            // Reset schedule in case card was previously loaded
            col.getSched().deferReset();
        }
        if (!ListenerUtil.mutListener.listen(11010)) {
            getCol().startTimebox();
        }
        if (!ListenerUtil.mutListener.listen(11011)) {
            TaskManager.launchCollectionTask(new CollectionTask.GetCard(), mAnswerCardHandler(false));
        }
        if (!ListenerUtil.mutListener.listen(11012)) {
            disableDrawerSwipeOnConflicts();
        }
        if (!ListenerUtil.mutListener.listen(11013)) {
            // Add a weak reference to current activity so that scheduler can talk to to Activity
            mSched.setContext(new WeakReference<>(this));
        }
        if (!ListenerUtil.mutListener.listen(11015)) {
            // Set full screen/immersive mode if needed
            if (mPrefFullscreenReview) {
                if (!ListenerUtil.mutListener.listen(11014)) {
                    setFullScreen(this);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(11016)) {
            if (getDrawerToggle().onOptionsItemSelected(item)) {
                return true;
            }
        }
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(11081)) {
            if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(11079)) {
                    Timber.i("Reviewer:: Home button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11080)) {
                    closeReviewer(RESULT_OK, true);
                }
            } else if (itemId == R.id.action_undo) {
                if (!ListenerUtil.mutListener.listen(11073)) {
                    Timber.i("Reviewer:: Undo button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11078)) {
                    if ((ListenerUtil.mutListener.listen(11075) ? ((ListenerUtil.mutListener.listen(11074) ? (mShowWhiteboard || mWhiteboard != null) : (mShowWhiteboard && mWhiteboard != null)) || !mWhiteboard.undoEmpty()) : ((ListenerUtil.mutListener.listen(11074) ? (mShowWhiteboard || mWhiteboard != null) : (mShowWhiteboard && mWhiteboard != null)) && !mWhiteboard.undoEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(11077)) {
                            mWhiteboard.undo();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11076)) {
                            undo();
                        }
                    }
                }
            } else if (itemId == R.id.action_reset_card_progress) {
                if (!ListenerUtil.mutListener.listen(11071)) {
                    Timber.i("Reviewer:: Reset progress button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11072)) {
                    showResetCardDialog();
                }
            } else if (itemId == R.id.action_mark_card) {
                if (!ListenerUtil.mutListener.listen(11069)) {
                    Timber.i("Reviewer:: Mark button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11070)) {
                    onMark(mCurrentCard);
                }
            } else if (itemId == R.id.action_replay) {
                if (!ListenerUtil.mutListener.listen(11067)) {
                    Timber.i("Reviewer:: Replay audio button pressed (from menu)");
                }
                if (!ListenerUtil.mutListener.listen(11068)) {
                    playSounds(true);
                }
            } else if (itemId == R.id.action_toggle_mic_tool_bar) {
                if (!ListenerUtil.mutListener.listen(11065)) {
                    Timber.i("Reviewer:: Record mic");
                }
                if (!ListenerUtil.mutListener.listen(11066)) {
                    // Check permission to record and request if not granted
                    openOrToggleMicToolbar();
                }
            } else if (itemId == R.id.action_tag) {
                if (!ListenerUtil.mutListener.listen(11063)) {
                    Timber.i("Reviewer:: Tag button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11064)) {
                    showTagsDialog();
                }
            } else if (itemId == R.id.action_edit) {
                if (!ListenerUtil.mutListener.listen(11061)) {
                    Timber.i("Reviewer:: Edit note button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11062)) {
                    editCard();
                }
                return true;
            } else if (itemId == R.id.action_bury) {
                if (!ListenerUtil.mutListener.listen(11057)) {
                    Timber.i("Reviewer:: Bury button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11060)) {
                    if (!MenuItemCompat.getActionProvider(item).hasSubMenu()) {
                        if (!ListenerUtil.mutListener.listen(11058)) {
                            Timber.d("Bury card due to no submenu");
                        }
                        if (!ListenerUtil.mutListener.listen(11059)) {
                            dismiss(DismissType.BURY_CARD);
                        }
                    }
                }
            } else if (itemId == R.id.action_suspend) {
                if (!ListenerUtil.mutListener.listen(11053)) {
                    Timber.i("Reviewer:: Suspend button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11056)) {
                    if (!MenuItemCompat.getActionProvider(item).hasSubMenu()) {
                        if (!ListenerUtil.mutListener.listen(11054)) {
                            Timber.d("Suspend card due to no submenu");
                        }
                        if (!ListenerUtil.mutListener.listen(11055)) {
                            dismiss(DismissType.SUSPEND_CARD);
                        }
                    }
                }
            } else if (itemId == R.id.action_delete) {
                if (!ListenerUtil.mutListener.listen(11051)) {
                    Timber.i("Reviewer:: Delete note button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11052)) {
                    showDeleteNoteDialog();
                }
            } else if (itemId == R.id.action_change_whiteboard_pen_color) {
                if (!ListenerUtil.mutListener.listen(11047)) {
                    Timber.i("Reviewer:: Pen Color button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11050)) {
                    if (colorPalette.getVisibility() == View.GONE) {
                        if (!ListenerUtil.mutListener.listen(11049)) {
                            colorPalette.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11048)) {
                            colorPalette.setVisibility(View.GONE);
                        }
                    }
                }
            } else if (itemId == R.id.action_save_whiteboard) {
                if (!ListenerUtil.mutListener.listen(11043)) {
                    Timber.i("Reviewer:: Save whiteboard button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11046)) {
                    if (mWhiteboard != null) {
                        try {
                            String savedWhiteboardFileName = mWhiteboard.saveWhiteboard(getCol().getTime());
                            if (!ListenerUtil.mutListener.listen(11045)) {
                                UIUtils.showThemedToast(Reviewer.this, getString(R.string.white_board_image_saved, savedWhiteboardFileName), true);
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(11044)) {
                                UIUtils.showThemedToast(Reviewer.this, getString(R.string.white_board_image_save_failed, e.getLocalizedMessage()), true);
                            }
                        }
                    }
                }
            } else if (itemId == R.id.action_clear_whiteboard) {
                if (!ListenerUtil.mutListener.listen(11040)) {
                    Timber.i("Reviewer:: Clear whiteboard button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11042)) {
                    if (mWhiteboard != null) {
                        if (!ListenerUtil.mutListener.listen(11041)) {
                            mWhiteboard.clear();
                        }
                    }
                }
            } else if (itemId == R.id.action_hide_whiteboard) {
                if (!ListenerUtil.mutListener.listen(11037)) {
                    // toggle whiteboard visibility
                    Timber.i("Reviewer:: Whiteboard visibility set to %b", !mShowWhiteboard);
                }
                if (!ListenerUtil.mutListener.listen(11038)) {
                    setWhiteboardVisibility(!mShowWhiteboard);
                }
                if (!ListenerUtil.mutListener.listen(11039)) {
                    refreshActionBar();
                }
            } else if (itemId == R.id.action_toggle_whiteboard) {
                if (!ListenerUtil.mutListener.listen(11036)) {
                    toggleWhiteboard();
                }
            } else if (itemId == R.id.action_search_dictionary) {
                if (!ListenerUtil.mutListener.listen(11034)) {
                    Timber.i("Reviewer:: Search dictionary button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11035)) {
                    lookUpOrSelectText();
                }
            } else if (itemId == R.id.action_open_deck_options) {
                Intent i = new Intent(this, DeckOptions.class);
                if (!ListenerUtil.mutListener.listen(11033)) {
                    startActivityForResultWithAnimation(i, DECK_OPTIONS, FADE);
                }
            } else if (itemId == R.id.action_select_tts) {
                if (!ListenerUtil.mutListener.listen(11031)) {
                    Timber.i("Reviewer:: Select TTS button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11032)) {
                    showSelectTtsDialogue();
                }
            } else if (itemId == R.id.action_add_note_reviewer) {
                if (!ListenerUtil.mutListener.listen(11029)) {
                    Timber.i("Reviewer:: Add note button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11030)) {
                    addNote();
                }
            } else if (itemId == R.id.action_flag_zero) {
                if (!ListenerUtil.mutListener.listen(11027)) {
                    Timber.i("Reviewer:: No flag");
                }
                if (!ListenerUtil.mutListener.listen(11028)) {
                    onFlag(mCurrentCard, FLAG_NONE);
                }
            } else if (itemId == R.id.action_flag_one) {
                if (!ListenerUtil.mutListener.listen(11025)) {
                    Timber.i("Reviewer:: Flag one");
                }
                if (!ListenerUtil.mutListener.listen(11026)) {
                    onFlag(mCurrentCard, FLAG_RED);
                }
            } else if (itemId == R.id.action_flag_two) {
                if (!ListenerUtil.mutListener.listen(11023)) {
                    Timber.i("Reviewer:: Flag two");
                }
                if (!ListenerUtil.mutListener.listen(11024)) {
                    onFlag(mCurrentCard, FLAG_ORANGE);
                }
            } else if (itemId == R.id.action_flag_three) {
                if (!ListenerUtil.mutListener.listen(11021)) {
                    Timber.i("Reviewer:: Flag three");
                }
                if (!ListenerUtil.mutListener.listen(11022)) {
                    onFlag(mCurrentCard, FLAG_GREEN);
                }
            } else if (itemId == R.id.action_flag_four) {
                if (!ListenerUtil.mutListener.listen(11019)) {
                    Timber.i("Reviewer:: Flag four");
                }
                if (!ListenerUtil.mutListener.listen(11020)) {
                    onFlag(mCurrentCard, FLAG_BLUE);
                }
            } else if (itemId == R.id.action_card_info) {
                if (!ListenerUtil.mutListener.listen(11017)) {
                    Timber.i("Card Viewer:: Card Info");
                }
                if (!ListenerUtil.mutListener.listen(11018)) {
                    openCardInfo();
                }
            } else {
                return super.onOptionsItemSelected(item);
            }
        }
        return true;
    }

    @Override
    protected void toggleWhiteboard() {
        if (!ListenerUtil.mutListener.listen(11082)) {
            mPrefWhiteboard = !mPrefWhiteboard;
        }
        if (!ListenerUtil.mutListener.listen(11083)) {
            Timber.i("Reviewer:: Whiteboard enabled state set to %b", mPrefWhiteboard);
        }
        if (!ListenerUtil.mutListener.listen(11084)) {
            // on the enabled status
            setWhiteboardEnabledState(mPrefWhiteboard);
        }
        if (!ListenerUtil.mutListener.listen(11085)) {
            setWhiteboardVisibility(mPrefWhiteboard);
        }
        if (!ListenerUtil.mutListener.listen(11087)) {
            if (!mPrefWhiteboard) {
                if (!ListenerUtil.mutListener.listen(11086)) {
                    colorPalette.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11088)) {
            refreshActionBar();
        }
    }

    @Override
    protected void replayVoice() {
        if (!ListenerUtil.mutListener.listen(11089)) {
            if (!openMicToolbar()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11090)) {
            mMicToolBar.togglePlay();
        }
    }

    @Override
    protected void recordVoice() {
        if (!ListenerUtil.mutListener.listen(11091)) {
            if (!openMicToolbar()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11092)) {
            mMicToolBar.toggleRecord();
        }
    }

    /**
     * @return Whether the mic toolbar is usable
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean openMicToolbar() {
        if (!ListenerUtil.mutListener.listen(11095)) {
            if ((ListenerUtil.mutListener.listen(11093) ? (mMicToolBar == null && mMicToolBar.getVisibility() != View.VISIBLE) : (mMicToolBar == null || mMicToolBar.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(11094)) {
                    openOrToggleMicToolbar();
                }
            }
        }
        return mMicToolBar != null;
    }

    protected void openOrToggleMicToolbar() {
        if (!ListenerUtil.mutListener.listen(11098)) {
            if (!Permissions.canRecordAudio(this)) {
                if (!ListenerUtil.mutListener.listen(11097)) {
                    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO }, REQUEST_AUDIO_PERMISSION);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11096)) {
                    toggleMicToolBar();
                }
            }
        }
    }

    private void toggleMicToolBar() {
        if (!ListenerUtil.mutListener.listen(11109)) {
            if (mMicToolBar != null) {
                if (!ListenerUtil.mutListener.listen(11108)) {
                    // It exists swap visibility status
                    if (mMicToolBar.getVisibility() != View.VISIBLE) {
                        if (!ListenerUtil.mutListener.listen(11107)) {
                            mMicToolBar.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11106)) {
                            mMicToolBar.setVisibility(View.GONE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11099)) {
                    // Record mic tool bar does not exist yet
                    mTempAudioPath = AudioView.generateTempAudioFile(this);
                }
                if (!ListenerUtil.mutListener.listen(11100)) {
                    if (mTempAudioPath == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(11101)) {
                    mMicToolBar = AudioView.createRecorderInstance(this, R.drawable.av_play, R.drawable.av_pause, R.drawable.av_stop, R.drawable.av_rec, R.drawable.av_rec_stop, mTempAudioPath);
                }
                if (!ListenerUtil.mutListener.listen(11103)) {
                    if (mMicToolBar == null) {
                        if (!ListenerUtil.mutListener.listen(11102)) {
                            mTempAudioPath = null;
                        }
                        return;
                    }
                }
                FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                if (!ListenerUtil.mutListener.listen(11104)) {
                    mMicToolBar.setLayoutParams(lp2);
                }
                LinearLayout micToolBarLayer = findViewById(R.id.mic_tool_bar_layer);
                if (!ListenerUtil.mutListener.listen(11105)) {
                    micToolBarLayer.addView(mMicToolBar);
                }
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(11123)) {
            if ((ListenerUtil.mutListener.listen(11121) ? ((ListenerUtil.mutListener.listen(11120) ? (((ListenerUtil.mutListener.listen(11114) ? (requestCode >= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11113) ? (requestCode <= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11112) ? (requestCode > REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11111) ? (requestCode < REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11110) ? (requestCode != REQUEST_AUDIO_PERMISSION) : (requestCode == REQUEST_AUDIO_PERMISSION))))))) || ((ListenerUtil.mutListener.listen(11119) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(11118) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(11117) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(11116) ? (permissions.length != 1) : (ListenerUtil.mutListener.listen(11115) ? (permissions.length == 1) : (permissions.length >= 1)))))))) : (((ListenerUtil.mutListener.listen(11114) ? (requestCode >= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11113) ? (requestCode <= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11112) ? (requestCode > REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11111) ? (requestCode < REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11110) ? (requestCode != REQUEST_AUDIO_PERMISSION) : (requestCode == REQUEST_AUDIO_PERMISSION))))))) && ((ListenerUtil.mutListener.listen(11119) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(11118) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(11117) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(11116) ? (permissions.length != 1) : (ListenerUtil.mutListener.listen(11115) ? (permissions.length == 1) : (permissions.length >= 1))))))))) || (grantResults[0] == PackageManager.PERMISSION_GRANTED)) : ((ListenerUtil.mutListener.listen(11120) ? (((ListenerUtil.mutListener.listen(11114) ? (requestCode >= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11113) ? (requestCode <= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11112) ? (requestCode > REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11111) ? (requestCode < REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11110) ? (requestCode != REQUEST_AUDIO_PERMISSION) : (requestCode == REQUEST_AUDIO_PERMISSION))))))) || ((ListenerUtil.mutListener.listen(11119) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(11118) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(11117) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(11116) ? (permissions.length != 1) : (ListenerUtil.mutListener.listen(11115) ? (permissions.length == 1) : (permissions.length >= 1)))))))) : (((ListenerUtil.mutListener.listen(11114) ? (requestCode >= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11113) ? (requestCode <= REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11112) ? (requestCode > REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11111) ? (requestCode < REQUEST_AUDIO_PERMISSION) : (ListenerUtil.mutListener.listen(11110) ? (requestCode != REQUEST_AUDIO_PERMISSION) : (requestCode == REQUEST_AUDIO_PERMISSION))))))) && ((ListenerUtil.mutListener.listen(11119) ? (permissions.length <= 1) : (ListenerUtil.mutListener.listen(11118) ? (permissions.length > 1) : (ListenerUtil.mutListener.listen(11117) ? (permissions.length < 1) : (ListenerUtil.mutListener.listen(11116) ? (permissions.length != 1) : (ListenerUtil.mutListener.listen(11115) ? (permissions.length == 1) : (permissions.length >= 1))))))))) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)))) {
                if (!ListenerUtil.mutListener.listen(11122)) {
                    // Get get audio record permission, so we can create the record tool bar
                    toggleMicToolBar();
                }
            }
        }
    }

    private void showRescheduleCardDialog() {
        Consumer<Integer> runnable = days -> TaskManager.launchCollectionTask(new CollectionTask.RescheduleCards(Collections.singletonList(mCurrentCard.getId()), days), mRescheduleCardHandler);
        RescheduleDialog dialog = RescheduleDialog.rescheduleSingleCard(getResources(), mCurrentCard, runnable);
        if (!ListenerUtil.mutListener.listen(11124)) {
            showDialogFragment(dialog);
        }
    }

    private void showResetCardDialog() {
        if (!ListenerUtil.mutListener.listen(11125)) {
            // Show confirmation dialog before resetting card progress
            Timber.i("showResetCardDialog() Reset progress button pressed");
        }
        // Show confirmation dialog before resetting card progress
        ConfirmationDialog dialog = new ConfirmationDialog();
        String title = getResources().getString(R.string.reset_card_dialog_title);
        String message = getResources().getString(R.string.reset_card_dialog_message);
        if (!ListenerUtil.mutListener.listen(11126)) {
            dialog.setArgs(title, message);
        }
        Runnable confirm = () -> {
            Timber.i("NoteEditor:: ResetProgress button pressed");
            TaskManager.launchCollectionTask(new CollectionTask.ResetCards(Collections.singletonList(mCurrentCard.getId())), mResetProgressCardHandler);
        };
        if (!ListenerUtil.mutListener.listen(11127)) {
            dialog.setConfirm(confirm);
        }
        if (!ListenerUtil.mutListener.listen(11128)) {
            showDialogFragment(dialog);
        }
    }

    private void addNote() {
        Intent intent = new Intent(this, NoteEditor.class);
        if (!ListenerUtil.mutListener.listen(11129)) {
            intent.putExtra(NoteEditor.EXTRA_CALLER, NoteEditor.CALLER_REVIEWER_ADD);
        }
        if (!ListenerUtil.mutListener.listen(11130)) {
            startActivityForResultWithAnimation(intent, ADD_NOTE, LEFT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(11131)) {
            // NOTE: This is called every time a new question is shown via invalidate options menu
            getMenuInflater().inflate(R.menu.reviewer, menu);
        }
        if (!ListenerUtil.mutListener.listen(11132)) {
            displayIconsOnTv(menu);
        }
        if (!ListenerUtil.mutListener.listen(11133)) {
            mActionButtons.setCustomButtonsStatus(menu);
        }
        int alpha = (getControlBlocked() != ReviewerUi.ControlBlock.SLOW) ? Themes.ALPHA_ICON_ENABLED_LIGHT : Themes.ALPHA_ICON_DISABLED_LIGHT;
        MenuItem markCardIcon = menu.findItem(R.id.action_mark_card);
        if (!ListenerUtil.mutListener.listen(11137)) {
            if ((ListenerUtil.mutListener.listen(11134) ? (mCurrentCard != null || mCurrentCard.note().hasTag("marked")) : (mCurrentCard != null && mCurrentCard.note().hasTag("marked")))) {
                if (!ListenerUtil.mutListener.listen(11136)) {
                    markCardIcon.setTitle(R.string.menu_unmark_note).setIcon(R.drawable.ic_star_white_24dp);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11135)) {
                    markCardIcon.setTitle(R.string.menu_mark_note).setIcon(R.drawable.ic_star_outline_white_24dp);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11138)) {
            markCardIcon.getIcon().mutate().setAlpha(alpha);
        }
        // 1643 - currently null on a TV
        @Nullable
        MenuItem flag_icon = menu.findItem(R.id.action_flag);
        if (!ListenerUtil.mutListener.listen(11147)) {
            if (flag_icon != null) {
                if (!ListenerUtil.mutListener.listen(11145)) {
                    if (mCurrentCard != null) {
                        if (!ListenerUtil.mutListener.listen(11144)) {
                            switch(mCurrentCard.userFlag()) {
                                case 1:
                                    if (!ListenerUtil.mutListener.listen(11139)) {
                                        flag_icon.setIcon(R.drawable.ic_flag_red);
                                    }
                                    break;
                                case 2:
                                    if (!ListenerUtil.mutListener.listen(11140)) {
                                        flag_icon.setIcon(R.drawable.ic_flag_orange);
                                    }
                                    break;
                                case 3:
                                    if (!ListenerUtil.mutListener.listen(11141)) {
                                        flag_icon.setIcon(R.drawable.ic_flag_green);
                                    }
                                    break;
                                case 4:
                                    if (!ListenerUtil.mutListener.listen(11142)) {
                                        flag_icon.setIcon(R.drawable.ic_flag_blue);
                                    }
                                    break;
                                default:
                                    if (!ListenerUtil.mutListener.listen(11143)) {
                                        flag_icon.setIcon(R.drawable.ic_flag_transparent);
                                    }
                                    break;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11146)) {
                    flag_icon.getIcon().mutate().setAlpha(alpha);
                }
            }
        }
        // Undo button
        @DrawableRes
        int undoIconId;
        boolean undoEnabled;
        if ((ListenerUtil.mutListener.listen(11149) ? ((ListenerUtil.mutListener.listen(11148) ? (mShowWhiteboard || mWhiteboard != null) : (mShowWhiteboard && mWhiteboard != null)) || mWhiteboard.isUndoModeActive()) : ((ListenerUtil.mutListener.listen(11148) ? (mShowWhiteboard || mWhiteboard != null) : (mShowWhiteboard && mWhiteboard != null)) && mWhiteboard.isUndoModeActive()))) {
            // Whiteboard is here and strokes have been added at some point
            undoIconId = R.drawable.ic_eraser_variant_white_24dp;
            undoEnabled = !mWhiteboard.undoEmpty();
        } else {
            // mWhiteboard != null` if no stroke had ever been made
            undoIconId = R.drawable.ic_undo_white_24dp;
            undoEnabled = ((ListenerUtil.mutListener.listen(11150) ? (colIsOpen() || getCol().undoAvailable()) : (colIsOpen() && getCol().undoAvailable())));
        }
        int alpha_undo = ((ListenerUtil.mutListener.listen(11151) ? (undoEnabled || getControlBlocked() != ReviewerUi.ControlBlock.SLOW) : (undoEnabled && getControlBlocked() != ReviewerUi.ControlBlock.SLOW))) ? Themes.ALPHA_ICON_ENABLED_LIGHT : Themes.ALPHA_ICON_DISABLED_LIGHT;
        MenuItem undoIcon = menu.findItem(R.id.action_undo);
        if (!ListenerUtil.mutListener.listen(11152)) {
            undoIcon.setIcon(undoIconId);
        }
        if (!ListenerUtil.mutListener.listen(11153)) {
            undoIcon.setEnabled(undoEnabled).getIcon().mutate().setAlpha(alpha_undo);
        }
        if (!ListenerUtil.mutListener.listen(11155)) {
            if (colIsOpen()) {
                if (!ListenerUtil.mutListener.listen(11154)) {
                    // Required mostly because there are tests where `col` is null
                    undoIcon.setTitle(getResources().getString(R.string.studyoptions_congrats_undo, getCol().undoName(getResources())));
                }
            }
        }
        MenuItem toggle_whiteboard_icon = menu.findItem(R.id.action_toggle_whiteboard);
        MenuItem hide_whiteboard_icon = menu.findItem(R.id.action_hide_whiteboard);
        MenuItem change_pen_color_icon = menu.findItem(R.id.action_change_whiteboard_pen_color);
        if (!ListenerUtil.mutListener.listen(11180)) {
            // White board button
            if (mPrefWhiteboard) {
                if (!ListenerUtil.mutListener.listen(11157)) {
                    // Configure the whiteboard related items in the action bar
                    toggle_whiteboard_icon.setTitle(R.string.disable_whiteboard);
                }
                if (!ListenerUtil.mutListener.listen(11158)) {
                    // Always allow "Disable Whiteboard", even if "Enable Whiteboard" is disabled
                    toggle_whiteboard_icon.setVisible(true);
                }
                if (!ListenerUtil.mutListener.listen(11160)) {
                    if (!mActionButtons.getStatus().hideWhiteboardIsDisabled()) {
                        if (!ListenerUtil.mutListener.listen(11159)) {
                            hide_whiteboard_icon.setVisible(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11162)) {
                    if (!mActionButtons.getStatus().clearWhiteboardIsDisabled()) {
                        if (!ListenerUtil.mutListener.listen(11161)) {
                            menu.findItem(R.id.action_clear_whiteboard).setVisible(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11164)) {
                    if (!mActionButtons.getStatus().saveWhiteboardIsDisabled()) {
                        if (!ListenerUtil.mutListener.listen(11163)) {
                            menu.findItem(R.id.action_save_whiteboard).setVisible(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11166)) {
                    if (!mActionButtons.getStatus().whiteboardPenColorIsDisabled()) {
                        if (!ListenerUtil.mutListener.listen(11165)) {
                            change_pen_color_icon.setVisible(true);
                        }
                    }
                }
                Drawable whiteboardIcon = ContextCompat.getDrawable(this, R.drawable.ic_gesture_white_24dp).mutate();
                Drawable whiteboardColorPaletteIcon = VectorDrawableCompat.create(getResources(), R.drawable.ic_color_lens_white_24dp, null).mutate();
                if (!ListenerUtil.mutListener.listen(11179)) {
                    if (mShowWhiteboard) {
                        if (!ListenerUtil.mutListener.listen(11174)) {
                            whiteboardIcon.setAlpha(Themes.ALPHA_ICON_ENABLED_LIGHT);
                        }
                        if (!ListenerUtil.mutListener.listen(11175)) {
                            hide_whiteboard_icon.setIcon(whiteboardIcon);
                        }
                        if (!ListenerUtil.mutListener.listen(11176)) {
                            hide_whiteboard_icon.setTitle(R.string.hide_whiteboard);
                        }
                        if (!ListenerUtil.mutListener.listen(11177)) {
                            whiteboardColorPaletteIcon.setAlpha(Themes.ALPHA_ICON_ENABLED_LIGHT);
                        }
                        if (!ListenerUtil.mutListener.listen(11178)) {
                            change_pen_color_icon.setIcon(whiteboardColorPaletteIcon);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11167)) {
                            whiteboardIcon.setAlpha(Themes.ALPHA_ICON_DISABLED_LIGHT);
                        }
                        if (!ListenerUtil.mutListener.listen(11168)) {
                            hide_whiteboard_icon.setIcon(whiteboardIcon);
                        }
                        if (!ListenerUtil.mutListener.listen(11169)) {
                            hide_whiteboard_icon.setTitle(R.string.show_whiteboard);
                        }
                        if (!ListenerUtil.mutListener.listen(11170)) {
                            whiteboardColorPaletteIcon.setAlpha(Themes.ALPHA_ICON_DISABLED_LIGHT);
                        }
                        if (!ListenerUtil.mutListener.listen(11171)) {
                            change_pen_color_icon.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(11172)) {
                            change_pen_color_icon.setIcon(whiteboardColorPaletteIcon);
                        }
                        if (!ListenerUtil.mutListener.listen(11173)) {
                            colorPalette.setVisibility(View.GONE);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11156)) {
                    toggle_whiteboard_icon.setTitle(R.string.enable_whiteboard);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11183)) {
            if ((ListenerUtil.mutListener.listen(11181) ? (colIsOpen() || getCol().getDecks().isDyn(getParentDid())) : (colIsOpen() && getCol().getDecks().isDyn(getParentDid())))) {
                if (!ListenerUtil.mutListener.listen(11182)) {
                    menu.findItem(R.id.action_open_deck_options).setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11186)) {
            if ((ListenerUtil.mutListener.listen(11184) ? (mSpeakText || !mActionButtons.getStatus().selectTtsIsDisabled()) : (mSpeakText && !mActionButtons.getStatus().selectTtsIsDisabled()))) {
                if (!ListenerUtil.mutListener.listen(11185)) {
                    menu.findItem(R.id.action_select_tts).setVisible(true);
                }
            }
        }
        // Setup bury / suspend providers
        MenuItem suspend_icon = menu.findItem(R.id.action_suspend);
        MenuItem bury_icon = menu.findItem(R.id.action_bury);
        if (!ListenerUtil.mutListener.listen(11187)) {
            setupSubMenu(menu, R.id.action_suspend, new SuspendProvider(this));
        }
        if (!ListenerUtil.mutListener.listen(11188)) {
            setupSubMenu(menu, R.id.action_bury, new BuryProvider(this));
        }
        if (!ListenerUtil.mutListener.listen(11193)) {
            if (suspendNoteAvailable()) {
                if (!ListenerUtil.mutListener.listen(11191)) {
                    suspend_icon.setIcon(R.drawable.ic_action_suspend_dropdown);
                }
                if (!ListenerUtil.mutListener.listen(11192)) {
                    suspend_icon.setTitle(R.string.menu_suspend);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11189)) {
                    suspend_icon.setIcon(R.drawable.ic_action_suspend);
                }
                if (!ListenerUtil.mutListener.listen(11190)) {
                    suspend_icon.setTitle(R.string.menu_suspend_card);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11198)) {
            if (buryNoteAvailable()) {
                if (!ListenerUtil.mutListener.listen(11196)) {
                    bury_icon.setIcon(R.drawable.ic_flip_to_back_white_24px_dropdown);
                }
                if (!ListenerUtil.mutListener.listen(11197)) {
                    bury_icon.setTitle(R.string.menu_bury);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11194)) {
                    bury_icon.setIcon(R.drawable.ic_flip_to_back_white_24dp);
                }
                if (!ListenerUtil.mutListener.listen(11195)) {
                    bury_icon.setTitle(R.string.menu_bury_card);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11199)) {
            alpha = (getControlBlocked() != ReviewerUi.ControlBlock.SLOW) ? Themes.ALPHA_ICON_ENABLED_LIGHT : Themes.ALPHA_ICON_DISABLED_LIGHT;
        }
        if (!ListenerUtil.mutListener.listen(11200)) {
            bury_icon.getIcon().mutate().setAlpha(alpha);
        }
        if (!ListenerUtil.mutListener.listen(11201)) {
            suspend_icon.getIcon().mutate().setAlpha(alpha);
        }
        if (!ListenerUtil.mutListener.listen(11202)) {
            setupSubMenu(menu, R.id.action_schedule, new ScheduleProvider(this));
        }
        return super.onCreateOptionsMenu(menu);
    }

    // setOptionalIconsVisible
    @SuppressLint("RestrictedApi")
    private void displayIconsOnTv(Menu menu) {
        if (!ListenerUtil.mutListener.listen(11203)) {
            if (!AndroidUiUtils.isRunningOnTv(this)) {
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(11206)) {
                if (menu instanceof MenuBuilder) {
                    MenuBuilder m = (MenuBuilder) menu;
                    if (!ListenerUtil.mutListener.listen(11205)) {
                        m.setOptionalIconsVisible(true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(11221)) {
                if ((ListenerUtil.mutListener.listen(11211) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11210) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11209) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11208) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(11207) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                    if (!ListenerUtil.mutListener.listen(11220)) {
                        {
                            long _loopCounter185 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(11219) ? (i >= menu.size()) : (ListenerUtil.mutListener.listen(11218) ? (i <= menu.size()) : (ListenerUtil.mutListener.listen(11217) ? (i > menu.size()) : (ListenerUtil.mutListener.listen(11216) ? (i != menu.size()) : (ListenerUtil.mutListener.listen(11215) ? (i == menu.size()) : (i < menu.size())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter185", ++_loopCounter185);
                                MenuItem m = menu.getItem(i);
                                if (!ListenerUtil.mutListener.listen(11213)) {
                                    if ((ListenerUtil.mutListener.listen(11212) ? (m == null && isFlagResource(m.getItemId())) : (m == null || isFlagResource(m.getItemId())))) {
                                        continue;
                                    }
                                }
                                int color = Themes.getColorFromAttr(this, R.attr.navDrawerItemColor);
                                if (!ListenerUtil.mutListener.listen(11214)) {
                                    MenuItemCompat.setIconTintList(m, ColorStateList.valueOf(color));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception | Error e) {
            if (!ListenerUtil.mutListener.listen(11204)) {
                Timber.w(e, "Failed to display icons");
            }
        }
    }

    private boolean isFlagResource(int itemId) {
        return (ListenerUtil.mutListener.listen(11224) ? ((ListenerUtil.mutListener.listen(11223) ? ((ListenerUtil.mutListener.listen(11222) ? (itemId == R.id.action_flag_four && itemId == R.id.action_flag_three) : (itemId == R.id.action_flag_four || itemId == R.id.action_flag_three)) && itemId == R.id.action_flag_two) : ((ListenerUtil.mutListener.listen(11222) ? (itemId == R.id.action_flag_four && itemId == R.id.action_flag_three) : (itemId == R.id.action_flag_four || itemId == R.id.action_flag_three)) || itemId == R.id.action_flag_two)) && itemId == R.id.action_flag_one) : ((ListenerUtil.mutListener.listen(11223) ? ((ListenerUtil.mutListener.listen(11222) ? (itemId == R.id.action_flag_four && itemId == R.id.action_flag_three) : (itemId == R.id.action_flag_four || itemId == R.id.action_flag_three)) && itemId == R.id.action_flag_two) : ((ListenerUtil.mutListener.listen(11222) ? (itemId == R.id.action_flag_four && itemId == R.id.action_flag_three) : (itemId == R.id.action_flag_four || itemId == R.id.action_flag_three)) || itemId == R.id.action_flag_two)) || itemId == R.id.action_flag_one));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(11226)) {
            if ((ListenerUtil.mutListener.listen(11225) ? (mProcessor.onKeyDown(keyCode, event) && super.onKeyDown(keyCode, event)) : (mProcessor.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(11227)) {
            if (!AndroidUiUtils.isRunningOnTv(this)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11229)) {
            // Process DPAD Up/Down to focus the TV Controls
            if ((ListenerUtil.mutListener.listen(11228) ? (keyCode != KeyEvent.KEYCODE_DPAD_DOWN || keyCode != KeyEvent.KEYCODE_DPAD_UP) : (keyCode != KeyEvent.KEYCODE_DPAD_DOWN && keyCode != KeyEvent.KEYCODE_DPAD_UP))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11230)) {
            // HACK: This shouldn't be required, as the navigation should handle this.
            if (isDrawerOpen()) {
                return false;
            }
        }
        View view = keyCode == KeyEvent.KEYCODE_DPAD_UP ? findViewById(R.id.tv_nav_view) : findViewById(R.id.answer_options_layout);
        if (!ListenerUtil.mutListener.listen(11231)) {
            // I couldn't get either to work
            if (view == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11232)) {
            view.requestFocus();
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(11233)) {
            if (answerFieldIsFocused()) {
                return super.onKeyUp(keyCode, event);
            }
        }
        if (!ListenerUtil.mutListener.listen(11234)) {
            if (mProcessor.onKeyUp(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private <T extends ActionProvider & SubMenuProvider> void setupSubMenu(Menu menu, @IdRes int parentMenu, T subMenuProvider) {
        if (!ListenerUtil.mutListener.listen(11236)) {
            if (!AndroidUiUtils.isRunningOnTv(this)) {
                if (!ListenerUtil.mutListener.listen(11235)) {
                    MenuItemCompat.setActionProvider(menu.findItem(parentMenu), subMenuProvider);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11237)) {
            // Don't do anything if the menu is hidden (bury for example)
            if (!subMenuProvider.hasSubMenu()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11238)) {
            // 7227 - If we're running on a TV, then we can't show submenus until AOSP is fixed
            menu.removeItem(parentMenu);
        }
        int count = menu.size();
        if (!ListenerUtil.mutListener.listen(11239)) {
            // move the menu to the bottom of the page
            getMenuInflater().inflate(subMenuProvider.getSubMenu(), menu);
        }
        if (!ListenerUtil.mutListener.listen(11254)) {
            {
                long _loopCounter186 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(11253) ? (i >= (ListenerUtil.mutListener.listen(11248) ? (menu.size() % count) : (ListenerUtil.mutListener.listen(11247) ? (menu.size() / count) : (ListenerUtil.mutListener.listen(11246) ? (menu.size() * count) : (ListenerUtil.mutListener.listen(11245) ? (menu.size() + count) : (menu.size() - count)))))) : (ListenerUtil.mutListener.listen(11252) ? (i <= (ListenerUtil.mutListener.listen(11248) ? (menu.size() % count) : (ListenerUtil.mutListener.listen(11247) ? (menu.size() / count) : (ListenerUtil.mutListener.listen(11246) ? (menu.size() * count) : (ListenerUtil.mutListener.listen(11245) ? (menu.size() + count) : (menu.size() - count)))))) : (ListenerUtil.mutListener.listen(11251) ? (i > (ListenerUtil.mutListener.listen(11248) ? (menu.size() % count) : (ListenerUtil.mutListener.listen(11247) ? (menu.size() / count) : (ListenerUtil.mutListener.listen(11246) ? (menu.size() * count) : (ListenerUtil.mutListener.listen(11245) ? (menu.size() + count) : (menu.size() - count)))))) : (ListenerUtil.mutListener.listen(11250) ? (i != (ListenerUtil.mutListener.listen(11248) ? (menu.size() % count) : (ListenerUtil.mutListener.listen(11247) ? (menu.size() / count) : (ListenerUtil.mutListener.listen(11246) ? (menu.size() * count) : (ListenerUtil.mutListener.listen(11245) ? (menu.size() + count) : (menu.size() - count)))))) : (ListenerUtil.mutListener.listen(11249) ? (i == (ListenerUtil.mutListener.listen(11248) ? (menu.size() % count) : (ListenerUtil.mutListener.listen(11247) ? (menu.size() / count) : (ListenerUtil.mutListener.listen(11246) ? (menu.size() * count) : (ListenerUtil.mutListener.listen(11245) ? (menu.size() + count) : (menu.size() - count)))))) : (i < (ListenerUtil.mutListener.listen(11248) ? (menu.size() % count) : (ListenerUtil.mutListener.listen(11247) ? (menu.size() / count) : (ListenerUtil.mutListener.listen(11246) ? (menu.size() * count) : (ListenerUtil.mutListener.listen(11245) ? (menu.size() + count) : (menu.size() - count))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter186", ++_loopCounter186);
                    MenuItem item = menu.getItem((ListenerUtil.mutListener.listen(11243) ? (count % i) : (ListenerUtil.mutListener.listen(11242) ? (count / i) : (ListenerUtil.mutListener.listen(11241) ? (count * i) : (ListenerUtil.mutListener.listen(11240) ? (count - i) : (count + i))))));
                    if (!ListenerUtil.mutListener.listen(11244)) {
                        item.setOnMenuItemClickListener(subMenuProvider);
                    }
                }
            }
        }
    }

    @Override
    protected boolean canAccessScheduler() {
        return true;
    }

    @Override
    protected void performReload() {
        if (!ListenerUtil.mutListener.listen(11255)) {
            getCol().getSched().deferReset();
        }
        if (!ListenerUtil.mutListener.listen(11256)) {
            TaskManager.launchCollectionTask(new CollectionTask.GetCard(), mAnswerCardHandler(false));
        }
    }

    @Override
    protected void displayAnswerBottomBar() {
        if (!ListenerUtil.mutListener.listen(11257)) {
            super.displayAnswerBottomBar();
        }
        int buttonCount;
        try {
            buttonCount = mSched.answerButtons(mCurrentCard);
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(11258)) {
                AnkiDroidApp.sendExceptionReport(e, "AbstractReviewer-showEaseButtons");
            }
            if (!ListenerUtil.mutListener.listen(11259)) {
                closeReviewer(DeckPicker.RESULT_DB_ERROR, true);
            }
            return;
        }
        // (which libanki expects ease to be 2 and 3) can either be hard, good, or easy - depending on num buttons shown
        int[] backgroundIds;
        if (animationEnabled()) {
            backgroundIds = new int[] { R.attr.againButtonRippleRef, R.attr.hardButtonRippleRef, R.attr.goodButtonRippleRef, R.attr.easyButtonRippleRef };
        } else {
            backgroundIds = new int[] { R.attr.againButtonRef, R.attr.hardButtonRef, R.attr.goodButtonRef, R.attr.easyButtonRef };
        }
        final int[] background = Themes.getResFromAttr(this, backgroundIds);
        final int[] textColor = Themes.getColorFromAttr(this, new int[] { R.attr.againButtonTextColor, R.attr.hardButtonTextColor, R.attr.goodButtonTextColor, R.attr.easyButtonTextColor });
        if (!ListenerUtil.mutListener.listen(11260)) {
            mEase1Layout.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(11261)) {
            mEase1Layout.setBackgroundResource(background[0]);
        }
        if (!ListenerUtil.mutListener.listen(11262)) {
            mEase4Layout.setBackgroundResource(background[3]);
        }
        if (!ListenerUtil.mutListener.listen(11294)) {
            switch(buttonCount) {
                case 2:
                    if (!ListenerUtil.mutListener.listen(11263)) {
                        // Ease 2 is "good"
                        mEase2Layout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11264)) {
                        mEase2Layout.setBackgroundResource(background[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11265)) {
                        mEase2.setText(R.string.ease_button_good);
                    }
                    if (!ListenerUtil.mutListener.listen(11266)) {
                        mEase2.setTextColor(textColor[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11267)) {
                        mNext2.setTextColor(textColor[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11268)) {
                        mEase2Layout.requestFocus();
                    }
                    break;
                case 3:
                    if (!ListenerUtil.mutListener.listen(11269)) {
                        // Ease 2 is good
                        mEase2Layout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11270)) {
                        mEase2Layout.setBackgroundResource(background[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11271)) {
                        mEase2.setText(R.string.ease_button_good);
                    }
                    if (!ListenerUtil.mutListener.listen(11272)) {
                        mEase2.setTextColor(textColor[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11273)) {
                        mNext2.setTextColor(textColor[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11274)) {
                        // Ease 3 is easy
                        mEase3Layout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11275)) {
                        mEase3Layout.setBackgroundResource(background[3]);
                    }
                    if (!ListenerUtil.mutListener.listen(11276)) {
                        mEase3.setText(R.string.ease_button_easy);
                    }
                    if (!ListenerUtil.mutListener.listen(11277)) {
                        mEase3.setTextColor(textColor[3]);
                    }
                    if (!ListenerUtil.mutListener.listen(11278)) {
                        mNext3.setTextColor(textColor[3]);
                    }
                    if (!ListenerUtil.mutListener.listen(11279)) {
                        mEase2Layout.requestFocus();
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(11280)) {
                        mEase2Layout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11281)) {
                        // Ease 2 is "hard"
                        mEase2Layout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11282)) {
                        mEase2Layout.setBackgroundResource(background[1]);
                    }
                    if (!ListenerUtil.mutListener.listen(11283)) {
                        mEase2.setText(R.string.ease_button_hard);
                    }
                    if (!ListenerUtil.mutListener.listen(11284)) {
                        mEase2.setTextColor(textColor[1]);
                    }
                    if (!ListenerUtil.mutListener.listen(11285)) {
                        mNext2.setTextColor(textColor[1]);
                    }
                    if (!ListenerUtil.mutListener.listen(11286)) {
                        mEase2Layout.requestFocus();
                    }
                    if (!ListenerUtil.mutListener.listen(11287)) {
                        // Ease 3 is good
                        mEase3Layout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11288)) {
                        mEase3Layout.setBackgroundResource(background[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11289)) {
                        mEase3.setText(R.string.ease_button_good);
                    }
                    if (!ListenerUtil.mutListener.listen(11290)) {
                        mEase3.setTextColor(textColor[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11291)) {
                        mNext3.setTextColor(textColor[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(11292)) {
                        mEase4Layout.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(11293)) {
                        mEase3Layout.requestFocus();
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(11311)) {
            // Show next review time
            if (shouldShowNextReviewTime()) {
                if (!ListenerUtil.mutListener.listen(11295)) {
                    mNext1.setText(mSched.nextIvlStr(this, mCurrentCard, Consts.BUTTON_ONE));
                }
                if (!ListenerUtil.mutListener.listen(11296)) {
                    mNext2.setText(mSched.nextIvlStr(this, mCurrentCard, Consts.BUTTON_TWO));
                }
                if (!ListenerUtil.mutListener.listen(11303)) {
                    if ((ListenerUtil.mutListener.listen(11301) ? (buttonCount >= 2) : (ListenerUtil.mutListener.listen(11300) ? (buttonCount <= 2) : (ListenerUtil.mutListener.listen(11299) ? (buttonCount < 2) : (ListenerUtil.mutListener.listen(11298) ? (buttonCount != 2) : (ListenerUtil.mutListener.listen(11297) ? (buttonCount == 2) : (buttonCount > 2))))))) {
                        if (!ListenerUtil.mutListener.listen(11302)) {
                            mNext3.setText(mSched.nextIvlStr(this, mCurrentCard, Consts.BUTTON_THREE));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11310)) {
                    if ((ListenerUtil.mutListener.listen(11308) ? (buttonCount >= 3) : (ListenerUtil.mutListener.listen(11307) ? (buttonCount <= 3) : (ListenerUtil.mutListener.listen(11306) ? (buttonCount < 3) : (ListenerUtil.mutListener.listen(11305) ? (buttonCount != 3) : (ListenerUtil.mutListener.listen(11304) ? (buttonCount == 3) : (buttonCount > 3))))))) {
                        if (!ListenerUtil.mutListener.listen(11309)) {
                            mNext4.setText(mSched.nextIvlStr(this, mCurrentCard, Consts.BUTTON_FOUR));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected SharedPreferences restorePreferences() {
        SharedPreferences preferences = super.restorePreferences();
        if (!ListenerUtil.mutListener.listen(11312)) {
            mPrefHideDueCount = preferences.getBoolean("hideDueCount", false);
        }
        if (!ListenerUtil.mutListener.listen(11313)) {
            mPrefShowETA = preferences.getBoolean("showETA", true);
        }
        if (!ListenerUtil.mutListener.listen(11314)) {
            this.mProcessor.setup();
        }
        if (!ListenerUtil.mutListener.listen(11320)) {
            mPrefFullscreenReview = (ListenerUtil.mutListener.listen(11319) ? (Integer.parseInt(preferences.getString("fullscreenMode", "0")) >= 0) : (ListenerUtil.mutListener.listen(11318) ? (Integer.parseInt(preferences.getString("fullscreenMode", "0")) <= 0) : (ListenerUtil.mutListener.listen(11317) ? (Integer.parseInt(preferences.getString("fullscreenMode", "0")) < 0) : (ListenerUtil.mutListener.listen(11316) ? (Integer.parseInt(preferences.getString("fullscreenMode", "0")) != 0) : (ListenerUtil.mutListener.listen(11315) ? (Integer.parseInt(preferences.getString("fullscreenMode", "0")) == 0) : (Integer.parseInt(preferences.getString("fullscreenMode", "0")) > 0))))));
        }
        if (!ListenerUtil.mutListener.listen(11321)) {
            mActionButtons.setup(preferences);
        }
        return preferences;
    }

    @Override
    protected void updateActionBar() {
        if (!ListenerUtil.mutListener.listen(11322)) {
            super.updateActionBar();
        }
        if (!ListenerUtil.mutListener.listen(11323)) {
            updateScreenCounts();
        }
    }

    protected void updateScreenCounts() {
        if (!ListenerUtil.mutListener.listen(11324)) {
            if (mCurrentCard == null)
                return;
        }
        if (!ListenerUtil.mutListener.listen(11325)) {
            super.updateActionBar();
        }
        ActionBar actionBar = getSupportActionBar();
        Counts counts = mSched.counts(mCurrentCard);
        if (!ListenerUtil.mutListener.listen(11333)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(11332)) {
                    if (mPrefShowETA) {
                        if (!ListenerUtil.mutListener.listen(11326)) {
                            eta = mSched.eta(counts, false);
                        }
                        if (!ListenerUtil.mutListener.listen(11331)) {
                            actionBar.setSubtitle(Utils.remainingTime(AnkiDroidApp.getInstance(), (ListenerUtil.mutListener.listen(11330) ? (eta % 60) : (ListenerUtil.mutListener.listen(11329) ? (eta / 60) : (ListenerUtil.mutListener.listen(11328) ? (eta - 60) : (ListenerUtil.mutListener.listen(11327) ? (eta + 60) : (eta * 60)))))));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11334)) {
            newCount = new SpannableString(String.valueOf(counts.getNew()));
        }
        if (!ListenerUtil.mutListener.listen(11335)) {
            lrnCount = new SpannableString(String.valueOf(counts.getLrn()));
        }
        if (!ListenerUtil.mutListener.listen(11336)) {
            revCount = new SpannableString(String.valueOf(counts.getRev()));
        }
        if (!ListenerUtil.mutListener.listen(11338)) {
            if (mPrefHideDueCount) {
                if (!ListenerUtil.mutListener.listen(11337)) {
                    revCount = new SpannableString("???");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11343)) {
            switch(mSched.countIdx(mCurrentCard)) {
                case NEW:
                    if (!ListenerUtil.mutListener.listen(11339)) {
                        newCount.setSpan(new UnderlineSpan(), 0, newCount.length(), 0);
                    }
                    break;
                case LRN:
                    if (!ListenerUtil.mutListener.listen(11340)) {
                        lrnCount.setSpan(new UnderlineSpan(), 0, lrnCount.length(), 0);
                    }
                    break;
                case REV:
                    if (!ListenerUtil.mutListener.listen(11341)) {
                        revCount.setSpan(new UnderlineSpan(), 0, revCount.length(), 0);
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(11342)) {
                        Timber.w("Unknown card type %s", mSched.countIdx(mCurrentCard));
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(11344)) {
            mTextBarNew.setText(newCount);
        }
        if (!ListenerUtil.mutListener.listen(11345)) {
            mTextBarLearn.setText(lrnCount);
        }
        if (!ListenerUtil.mutListener.listen(11346)) {
            mTextBarReview.setText(revCount);
        }
    }

    @Override
    public void fillFlashcard() {
        if (!ListenerUtil.mutListener.listen(11347)) {
            super.fillFlashcard();
        }
        if (!ListenerUtil.mutListener.listen(11351)) {
            if ((ListenerUtil.mutListener.listen(11349) ? ((ListenerUtil.mutListener.listen(11348) ? (!sDisplayAnswer || mShowWhiteboard) : (!sDisplayAnswer && mShowWhiteboard)) || mWhiteboard != null) : ((ListenerUtil.mutListener.listen(11348) ? (!sDisplayAnswer || mShowWhiteboard) : (!sDisplayAnswer && mShowWhiteboard)) && mWhiteboard != null))) {
                if (!ListenerUtil.mutListener.listen(11350)) {
                    mWhiteboard.clear();
                }
            }
        }
    }

    @Override
    public void displayCardQuestion() {
        if (!ListenerUtil.mutListener.listen(11352)) {
            // show timer, if activated in the deck's preferences
            initTimer();
        }
        if (!ListenerUtil.mutListener.listen(11353)) {
            super.displayCardQuestion();
        }
    }

    @Override
    protected void initLayout() {
        if (!ListenerUtil.mutListener.listen(11354)) {
            mTextBarNew = findViewById(R.id.new_number);
        }
        if (!ListenerUtil.mutListener.listen(11355)) {
            mTextBarLearn = findViewById(R.id.learn_number);
        }
        if (!ListenerUtil.mutListener.listen(11356)) {
            mTextBarReview = findViewById(R.id.review_number);
        }
        if (!ListenerUtil.mutListener.listen(11357)) {
            super.initLayout();
        }
        if (!ListenerUtil.mutListener.listen(11361)) {
            if (!mShowRemainingCardCount) {
                if (!ListenerUtil.mutListener.listen(11358)) {
                    mTextBarNew.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(11359)) {
                    mTextBarLearn.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(11360)) {
                    mTextBarReview.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    protected void switchTopBarVisibility(int visible) {
        if (!ListenerUtil.mutListener.listen(11362)) {
            super.switchTopBarVisibility(visible);
        }
        if (!ListenerUtil.mutListener.listen(11366)) {
            if (mShowRemainingCardCount) {
                if (!ListenerUtil.mutListener.listen(11363)) {
                    mTextBarNew.setVisibility(visible);
                }
                if (!ListenerUtil.mutListener.listen(11364)) {
                    mTextBarLearn.setVisibility(visible);
                }
                if (!ListenerUtil.mutListener.listen(11365)) {
                    mTextBarReview.setVisibility(visible);
                }
            }
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(11367)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(11371)) {
            if ((ListenerUtil.mutListener.listen(11369) ? ((ListenerUtil.mutListener.listen(11368) ? (!isFinishing() || colIsOpen()) : (!isFinishing() && colIsOpen())) || mSched != null) : ((ListenerUtil.mutListener.listen(11368) ? (!isFinishing() || colIsOpen()) : (!isFinishing() && colIsOpen())) && mSched != null))) {
                if (!ListenerUtil.mutListener.listen(11370)) {
                    WidgetStatus.update(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11372)) {
            UIUtils.saveCollectionInBackground();
        }
    }

    @Override
    protected void initControls() {
        if (!ListenerUtil.mutListener.listen(11373)) {
            super.initControls();
        }
        if (!ListenerUtil.mutListener.listen(11375)) {
            if (mPrefWhiteboard) {
                if (!ListenerUtil.mutListener.listen(11374)) {
                    setWhiteboardVisibility(mShowWhiteboard);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11379)) {
            if (mShowRemainingCardCount) {
                if (!ListenerUtil.mutListener.listen(11376)) {
                    mTextBarNew.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(11377)) {
                    mTextBarLearn.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(11378)) {
                    mTextBarReview.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected void restoreCollectionPreferences() {
        if (!ListenerUtil.mutListener.listen(11380)) {
            super.restoreCollectionPreferences();
        }
        if (!ListenerUtil.mutListener.listen(11381)) {
            mShowRemainingCardCount = getCol().getConf().getBoolean("dueCounts");
        }
    }

    @Override
    protected boolean onSingleTap() {
        if (!ListenerUtil.mutListener.listen(11384)) {
            if ((ListenerUtil.mutListener.listen(11382) ? (mPrefFullscreenReview || isImmersiveSystemUiVisible(this)) : (mPrefFullscreenReview && isImmersiveSystemUiVisible(this)))) {
                if (!ListenerUtil.mutListener.listen(11383)) {
                    delayedHide(INITIAL_HIDE_DELAY);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onFling() {
        if (!ListenerUtil.mutListener.listen(11387)) {
            if ((ListenerUtil.mutListener.listen(11385) ? (mPrefFullscreenReview || isImmersiveSystemUiVisible(this)) : (mPrefFullscreenReview && isImmersiveSystemUiVisible(this)))) {
                if (!ListenerUtil.mutListener.listen(11386)) {
                    delayedHide(INITIAL_HIDE_DELAY);
                }
            }
        }
    }

    protected final Handler mFullScreenHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (!ListenerUtil.mutListener.listen(11389)) {
                if (mPrefFullscreenReview) {
                    if (!ListenerUtil.mutListener.listen(11388)) {
                        setFullScreen(Reviewer.this);
                    }
                }
            }
        }
    };

    protected void delayedHide(int delayMillis) {
        if (!ListenerUtil.mutListener.listen(11390)) {
            Timber.d("Fullscreen delayed hide in %dms", delayMillis);
        }
        if (!ListenerUtil.mutListener.listen(11391)) {
            mFullScreenHandler.removeMessages(0);
        }
        if (!ListenerUtil.mutListener.listen(11392)) {
            mFullScreenHandler.sendEmptyMessageDelayed(0, delayMillis);
        }
    }

    private void setWhiteboardEnabledState(boolean state) {
        if (!ListenerUtil.mutListener.listen(11393)) {
            mPrefWhiteboard = state;
        }
        if (!ListenerUtil.mutListener.listen(11394)) {
            MetaDB.storeWhiteboardState(this, getParentDid(), state);
        }
        if (!ListenerUtil.mutListener.listen(11397)) {
            if ((ListenerUtil.mutListener.listen(11395) ? (state || mWhiteboard == null) : (state && mWhiteboard == null))) {
                if (!ListenerUtil.mutListener.listen(11396)) {
                    createWhiteboard();
                }
            }
        }
    }

    private static final int FULLSCREEN_ALL_GONE = 2;

    private void setFullScreen(final AbstractFlashcardViewer a) {
        if (!ListenerUtil.mutListener.listen(11398)) {
            // Set appropriate flags to enable Sticky Immersive mode.
            a.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // temporarily disabled due to #5245
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
        // Show / hide the Action bar together with the status bar
        SharedPreferences prefs = AnkiDroidApp.getSharedPrefs(a);
        final int fullscreenMode = Integer.parseInt(prefs.getString("fullscreenMode", "0"));
        if (!ListenerUtil.mutListener.listen(11399)) {
            a.getWindow().setStatusBarColor(Themes.getColorFromAttr(a, R.attr.colorPrimaryDark));
        }
        View decorView = a.getWindow().getDecorView();
        if (!ListenerUtil.mutListener.listen(11400)) {
            decorView.setOnSystemUiVisibilityChangeListener(flags -> {
                final View toolbar = a.findViewById(R.id.toolbar);
                final View answerButtons = a.findViewById(R.id.answer_options_layout);
                final View topbar = a.findViewById(R.id.top_bar);
                if (toolbar == null || topbar == null || answerButtons == null) {
                    return;
                }
                // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                boolean visible = (flags & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
                Timber.d("System UI visibility change. Visible: %b", visible);
                if (visible) {
                    showViewWithAnimation(toolbar);
                    if (fullscreenMode >= FULLSCREEN_ALL_GONE) {
                        showViewWithAnimation(topbar);
                        showViewWithAnimation(answerButtons);
                    }
                } else {
                    hideViewWithAnimation(toolbar);
                    if (fullscreenMode >= FULLSCREEN_ALL_GONE) {
                        hideViewWithAnimation(topbar);
                        hideViewWithAnimation(answerButtons);
                    }
                }
            });
        }
    }

    private static final int ANIMATION_DURATION = 200;

    private static final float TRANSPARENCY = 0.90f;

    private void showViewWithAnimation(final View view) {
        if (!ListenerUtil.mutListener.listen(11401)) {
            view.setAlpha(0.0f);
        }
        if (!ListenerUtil.mutListener.listen(11402)) {
            view.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(11403)) {
            view.animate().alpha(TRANSPARENCY).setDuration(ANIMATION_DURATION).setListener(null);
        }
    }

    private void hideViewWithAnimation(final View view) {
        if (!ListenerUtil.mutListener.listen(11405)) {
            view.animate().alpha(0f).setDuration(ANIMATION_DURATION).setListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!ListenerUtil.mutListener.listen(11404)) {
                        view.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private boolean isImmersiveSystemUiVisible(AnkiActivity activity) {
        return (activity.getWindow().getDecorView().getSystemUiVisibility() & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
    }

    private void createWhiteboard() {
        SharedPreferences sharedPrefs = AnkiDroidApp.getSharedPrefs(this);
        if (!ListenerUtil.mutListener.listen(11406)) {
            mWhiteboard = new Whiteboard(this, isInNightMode());
        }
        FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (!ListenerUtil.mutListener.listen(11407)) {
            mWhiteboard.setLayoutParams(lp2);
        }
        FrameLayout fl = findViewById(R.id.whiteboard);
        if (!ListenerUtil.mutListener.listen(11408)) {
            fl.addView(mWhiteboard);
        }
        // This is how all other whiteboard settings are
        Integer whiteboardPenColor = MetaDB.getWhiteboardPenColor(this, getParentDid()).fromPreferences(sharedPrefs);
        if (!ListenerUtil.mutListener.listen(11410)) {
            if (whiteboardPenColor != null) {
                if (!ListenerUtil.mutListener.listen(11409)) {
                    mWhiteboard.setPenColor(whiteboardPenColor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11411)) {
            mWhiteboard.setOnPaintColorChangeListener(color -> MetaDB.storeWhiteboardPenColor(this, getParentDid(), !CardAppearance.isInNightMode(sharedPrefs), color));
        }
        if (!ListenerUtil.mutListener.listen(11412)) {
            mWhiteboard.setOnTouchListener((v, event) -> {
                // If the whiteboard is currently drawing, and triggers the system UI to show, we want to continue drawing.
                if (!mWhiteboard.isCurrentlyDrawing() && (!mShowWhiteboard || (mPrefFullscreenReview && isImmersiveSystemUiVisible(Reviewer.this)))) {
                    // Bypass whiteboard listener when it's hidden or fullscreen immersive mode is temporarily suspended
                    v.performClick();
                    return getGestureDetector().onTouchEvent(event);
                }
                return mWhiteboard.handleTouchEvent(event);
            });
        }
        if (!ListenerUtil.mutListener.listen(11413)) {
            mWhiteboard.setEnabled(true);
        }
    }

    // Show or hide the whiteboard
    private void setWhiteboardVisibility(boolean state) {
        if (!ListenerUtil.mutListener.listen(11414)) {
            mShowWhiteboard = state;
        }
        if (!ListenerUtil.mutListener.listen(11415)) {
            MetaDB.storeWhiteboardVisibility(this, getParentDid(), state);
        }
        if (!ListenerUtil.mutListener.listen(11421)) {
            if (state) {
                if (!ListenerUtil.mutListener.listen(11419)) {
                    mWhiteboard.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(11420)) {
                    disableDrawerSwipe();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11416)) {
                    mWhiteboard.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(11418)) {
                    if (!mHasDrawerSwipeConflicts) {
                        if (!ListenerUtil.mutListener.listen(11417)) {
                            enableDrawerSwipe();
                        }
                    }
                }
            }
        }
    }

    private void disableDrawerSwipeOnConflicts() {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        boolean gesturesEnabled = AnkiDroidApp.initiateGestures(preferences);
        if (!ListenerUtil.mutListener.listen(11442)) {
            if (gesturesEnabled) {
                int gestureSwipeUp = Integer.parseInt(preferences.getString("gestureSwipeUp", "9"));
                int gestureSwipeDown = Integer.parseInt(preferences.getString("gestureSwipeDown", "0"));
                int gestureSwipeRight = Integer.parseInt(preferences.getString("gestureSwipeRight", "17"));
                if (!ListenerUtil.mutListener.listen(11441)) {
                    if ((ListenerUtil.mutListener.listen(11438) ? ((ListenerUtil.mutListener.listen(11432) ? ((ListenerUtil.mutListener.listen(11426) ? (gestureSwipeUp >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11425) ? (gestureSwipeUp <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11424) ? (gestureSwipeUp > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11423) ? (gestureSwipeUp < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11422) ? (gestureSwipeUp == COMMAND_NOTHING) : (gestureSwipeUp != COMMAND_NOTHING)))))) && (ListenerUtil.mutListener.listen(11431) ? (gestureSwipeDown >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11430) ? (gestureSwipeDown <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11429) ? (gestureSwipeDown > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11428) ? (gestureSwipeDown < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11427) ? (gestureSwipeDown == COMMAND_NOTHING) : (gestureSwipeDown != COMMAND_NOTHING))))))) : ((ListenerUtil.mutListener.listen(11426) ? (gestureSwipeUp >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11425) ? (gestureSwipeUp <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11424) ? (gestureSwipeUp > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11423) ? (gestureSwipeUp < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11422) ? (gestureSwipeUp == COMMAND_NOTHING) : (gestureSwipeUp != COMMAND_NOTHING)))))) || (ListenerUtil.mutListener.listen(11431) ? (gestureSwipeDown >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11430) ? (gestureSwipeDown <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11429) ? (gestureSwipeDown > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11428) ? (gestureSwipeDown < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11427) ? (gestureSwipeDown == COMMAND_NOTHING) : (gestureSwipeDown != COMMAND_NOTHING)))))))) && (ListenerUtil.mutListener.listen(11437) ? (gestureSwipeRight >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11436) ? (gestureSwipeRight <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11435) ? (gestureSwipeRight > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11434) ? (gestureSwipeRight < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11433) ? (gestureSwipeRight == COMMAND_NOTHING) : (gestureSwipeRight != COMMAND_NOTHING))))))) : ((ListenerUtil.mutListener.listen(11432) ? ((ListenerUtil.mutListener.listen(11426) ? (gestureSwipeUp >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11425) ? (gestureSwipeUp <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11424) ? (gestureSwipeUp > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11423) ? (gestureSwipeUp < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11422) ? (gestureSwipeUp == COMMAND_NOTHING) : (gestureSwipeUp != COMMAND_NOTHING)))))) && (ListenerUtil.mutListener.listen(11431) ? (gestureSwipeDown >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11430) ? (gestureSwipeDown <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11429) ? (gestureSwipeDown > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11428) ? (gestureSwipeDown < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11427) ? (gestureSwipeDown == COMMAND_NOTHING) : (gestureSwipeDown != COMMAND_NOTHING))))))) : ((ListenerUtil.mutListener.listen(11426) ? (gestureSwipeUp >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11425) ? (gestureSwipeUp <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11424) ? (gestureSwipeUp > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11423) ? (gestureSwipeUp < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11422) ? (gestureSwipeUp == COMMAND_NOTHING) : (gestureSwipeUp != COMMAND_NOTHING)))))) || (ListenerUtil.mutListener.listen(11431) ? (gestureSwipeDown >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11430) ? (gestureSwipeDown <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11429) ? (gestureSwipeDown > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11428) ? (gestureSwipeDown < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11427) ? (gestureSwipeDown == COMMAND_NOTHING) : (gestureSwipeDown != COMMAND_NOTHING)))))))) || (ListenerUtil.mutListener.listen(11437) ? (gestureSwipeRight >= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11436) ? (gestureSwipeRight <= COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11435) ? (gestureSwipeRight > COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11434) ? (gestureSwipeRight < COMMAND_NOTHING) : (ListenerUtil.mutListener.listen(11433) ? (gestureSwipeRight == COMMAND_NOTHING) : (gestureSwipeRight != COMMAND_NOTHING))))))))) {
                        if (!ListenerUtil.mutListener.listen(11439)) {
                            mHasDrawerSwipeConflicts = true;
                        }
                        if (!ListenerUtil.mutListener.listen(11440)) {
                            super.disableDrawerSwipe();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected Long getCurrentCardId() {
        return mCurrentCard.getId();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!ListenerUtil.mutListener.listen(11443)) {
            super.onWindowFocusChanged(hasFocus);
        }
        if (!ListenerUtil.mutListener.listen(11446)) {
            // Restore full screen once we regain focus
            if (hasFocus) {
                if (!ListenerUtil.mutListener.listen(11445)) {
                    delayedHide(INITIAL_HIDE_DELAY);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11444)) {
                    mFullScreenHandler.removeMessages(0);
                }
            }
        }
    }

    /**
     * Whether or not dismiss note is available for current card and specified DismissType
     * @return true if there is another card of same note that could be dismissed
     */
    private boolean suspendNoteAvailable() {
        if (!ListenerUtil.mutListener.listen(11448)) {
            if ((ListenerUtil.mutListener.listen(11447) ? (mCurrentCard == null && isControlBlocked()) : (mCurrentCard == null || isControlBlocked()))) {
                return false;
            }
        }
        // whether there exists a sibling not buried.
        return getCol().getDb().queryScalar("select 1 from cards where nid = ? and id != ? and queue != " + Consts.QUEUE_TYPE_SUSPENDED + " limit 1", mCurrentCard.getNid(), mCurrentCard.getId()) == 1;
    }

    private boolean buryNoteAvailable() {
        if (!ListenerUtil.mutListener.listen(11450)) {
            if ((ListenerUtil.mutListener.listen(11449) ? (mCurrentCard == null && isControlBlocked()) : (mCurrentCard == null || isControlBlocked()))) {
                return false;
            }
        }
        // Whether there exists a sibling which is neither susbended nor buried
        return getCol().getDb().queryScalar("select 1 from cards where nid = ? and id != ? and queue >=  " + Consts.QUEUE_TYPE_NEW + " limit 1", mCurrentCard.getNid(), mCurrentCard.getId()) == 1;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    @CheckResult
    Whiteboard getWhiteboard() {
        return mWhiteboard;
    }

    /**
     * Inner class which implements the submenu for the Suspend button
     */
    class SuspendProvider extends ActionProvider implements SubMenuProvider {

        public SuspendProvider(Context context) {
            super(context);
        }

        @Override
        public View onCreateActionView() {
            // Just return null for a simple dropdown menu
            return null;
        }

        @Override
        public int getSubMenu() {
            return R.menu.reviewer_suspend;
        }

        @Override
        public boolean hasSubMenu() {
            return suspendNoteAvailable();
        }

        @Override
        public void onPrepareSubMenu(SubMenu subMenu) {
            if (!ListenerUtil.mutListener.listen(11451)) {
                subMenu.clear();
            }
            if (!ListenerUtil.mutListener.listen(11452)) {
                getMenuInflater().inflate(getSubMenu(), subMenu);
            }
            if (!ListenerUtil.mutListener.listen(11459)) {
                {
                    long _loopCounter187 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(11458) ? (i >= subMenu.size()) : (ListenerUtil.mutListener.listen(11457) ? (i <= subMenu.size()) : (ListenerUtil.mutListener.listen(11456) ? (i > subMenu.size()) : (ListenerUtil.mutListener.listen(11455) ? (i != subMenu.size()) : (ListenerUtil.mutListener.listen(11454) ? (i == subMenu.size()) : (i < subMenu.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter187", ++_loopCounter187);
                        if (!ListenerUtil.mutListener.listen(11453)) {
                            subMenu.getItem(i).setOnMenuItemClickListener(this);
                        }
                    }
                }
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int itemId = item.getItemId();
            if (!ListenerUtil.mutListener.listen(11462)) {
                if (itemId == R.id.action_suspend_card) {
                    if (!ListenerUtil.mutListener.listen(11461)) {
                        dismiss(DismissType.SUSPEND_CARD);
                    }
                    return true;
                } else if (itemId == R.id.action_suspend_note) {
                    if (!ListenerUtil.mutListener.listen(11460)) {
                        dismiss(DismissType.SUSPEND_NOTE);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Inner class which implements the submenu for the Bury button
     */
    class BuryProvider extends ActionProvider implements SubMenuProvider {

        public BuryProvider(Context context) {
            super(context);
        }

        @Override
        public View onCreateActionView() {
            // Just return null for a simple dropdown menu
            return null;
        }

        @Override
        public int getSubMenu() {
            return R.menu.reviewer_bury;
        }

        @Override
        public boolean hasSubMenu() {
            return buryNoteAvailable();
        }

        @Override
        public void onPrepareSubMenu(SubMenu subMenu) {
            if (!ListenerUtil.mutListener.listen(11463)) {
                subMenu.clear();
            }
            if (!ListenerUtil.mutListener.listen(11464)) {
                getMenuInflater().inflate(getSubMenu(), subMenu);
            }
            if (!ListenerUtil.mutListener.listen(11471)) {
                {
                    long _loopCounter188 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(11470) ? (i >= subMenu.size()) : (ListenerUtil.mutListener.listen(11469) ? (i <= subMenu.size()) : (ListenerUtil.mutListener.listen(11468) ? (i > subMenu.size()) : (ListenerUtil.mutListener.listen(11467) ? (i != subMenu.size()) : (ListenerUtil.mutListener.listen(11466) ? (i == subMenu.size()) : (i < subMenu.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter188", ++_loopCounter188);
                        if (!ListenerUtil.mutListener.listen(11465)) {
                            subMenu.getItem(i).setOnMenuItemClickListener(this);
                        }
                    }
                }
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int itemId = item.getItemId();
            if (!ListenerUtil.mutListener.listen(11474)) {
                if (itemId == R.id.action_bury_card) {
                    if (!ListenerUtil.mutListener.listen(11473)) {
                        dismiss(DismissType.BURY_CARD);
                    }
                    return true;
                } else if (itemId == R.id.action_bury_note) {
                    if (!ListenerUtil.mutListener.listen(11472)) {
                        dismiss(DismissType.BURY_NOTE);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Inner class which implements the submenu for the Schedule button
     */
    class ScheduleProvider extends ActionProvider implements SubMenuProvider {

        public ScheduleProvider(Context context) {
            super(context);
        }

        @Override
        public View onCreateActionView() {
            // Just return null for a simple dropdown menu
            return null;
        }

        @Override
        public boolean hasSubMenu() {
            return true;
        }

        @Override
        public void onPrepareSubMenu(SubMenu subMenu) {
            if (!ListenerUtil.mutListener.listen(11475)) {
                subMenu.clear();
            }
            if (!ListenerUtil.mutListener.listen(11476)) {
                getMenuInflater().inflate(getSubMenu(), subMenu);
            }
            if (!ListenerUtil.mutListener.listen(11483)) {
                {
                    long _loopCounter189 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(11482) ? (i >= subMenu.size()) : (ListenerUtil.mutListener.listen(11481) ? (i <= subMenu.size()) : (ListenerUtil.mutListener.listen(11480) ? (i > subMenu.size()) : (ListenerUtil.mutListener.listen(11479) ? (i != subMenu.size()) : (ListenerUtil.mutListener.listen(11478) ? (i == subMenu.size()) : (i < subMenu.size())))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter189", ++_loopCounter189);
                        if (!ListenerUtil.mutListener.listen(11477)) {
                            subMenu.getItem(i).setOnMenuItemClickListener(this);
                        }
                    }
                }
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int itemId = item.getItemId();
            if (!ListenerUtil.mutListener.listen(11486)) {
                if (itemId == R.id.action_reschedule_card) {
                    if (!ListenerUtil.mutListener.listen(11485)) {
                        showRescheduleCardDialog();
                    }
                    return true;
                } else if (itemId == R.id.action_reset_card_progress) {
                    if (!ListenerUtil.mutListener.listen(11484)) {
                        showResetCardDialog();
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        public int getSubMenu() {
            return R.menu.reviewer_schedule;
        }
    }

    private interface SubMenuProvider extends MenuItem.OnMenuItemClickListener {

        @MenuRes
        int getSubMenu();

        boolean hasSubMenu();
    }

    public ReviewerJavaScriptFunction javaScriptFunction() {
        return new ReviewerJavaScriptFunction();
    }

    public class ReviewerJavaScriptFunction extends JavaScriptFunction {

        @JavascriptInterface
        @Override
        public String ankiGetNewCardCount() {
            return newCount.toString();
        }

        @JavascriptInterface
        @Override
        public String ankiGetLrnCardCount() {
            return lrnCount.toString();
        }

        @JavascriptInterface
        @Override
        public String ankiGetRevCardCount() {
            return revCount.toString();
        }

        @JavascriptInterface
        @Override
        public int ankiGetETA() {
            return eta;
        }
    }
}
