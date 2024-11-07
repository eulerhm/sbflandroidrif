/**
 * *************************************************************************************
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
 *  this program. If not, see <http://www.gnu.org/licenses/>.                            *
 * **************************************************************************************
 */
package com.ichi2.anki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.anim.ActivityTransitionAnimation;
import com.ichi2.anki.dialogs.CustomStudyDialog;
import com.ichi2.async.CollectionTask;
import com.ichi2.async.TaskListener;
import com.ichi2.async.TaskManager;
import com.ichi2.compat.CompatHelper;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Utils;
import com.ichi2.libanki.Deck;
import com.ichi2.themes.StyledProgressDialog;
import com.ichi2.utils.BooleanGetter;
import com.ichi2.utils.HtmlUtils;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import static com.ichi2.libanki.Consts.DECK_DYN;
import static com.ichi2.libanki.Consts.DECK_STD;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StudyOptionsFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    /**
     * Available options performed by other activities
     */
    private static final int BROWSE_CARDS = 3;

    private static final int STATISTICS = 4;

    private static final int DECK_OPTIONS = 5;

    /**
     * Constants for selecting which content view to display
     */
    private static final int CONTENT_STUDY_OPTIONS = 0;

    private static final int CONTENT_CONGRATS = 1;

    private static final int CONTENT_EMPTY = 2;

    // Threshold at which the total number of new cards is truncated by libanki
    private static final int NEW_CARD_COUNT_TRUNCATE_THRESHOLD = 99999;

    /**
     * Preferences
     */
    private int mCurrentContentView = CONTENT_STUDY_OPTIONS;

    /**
     * Alerts to inform the user about different situations
     */
    private MaterialDialog mProgressDialog;

    /**
     * Whether we are closing in order to go to the reviewer. If it's the case, UPDATE_VALUES_FROM_DECK should not be
     *     cancelled as the counts will be used in review.
     */
    private boolean mToReviewer = false;

    /**
     * UI elements for "Study Options" view
     */
    @Nullable
    private View mStudyOptionsView;

    private View mDeckInfoLayout;

    private Button mButtonStart;

    private TextView mTextDeckName;

    private TextView mTextDeckDescription;

    private TextView mTextTodayNew;

    private TextView mTextTodayLrn;

    private TextView mTextTodayRev;

    private TextView mTextNewTotal;

    private TextView mTextTotal;

    private TextView mTextETA;

    private TextView mTextCongratsMessage;

    private Toolbar mToolbar;

    // Flag to indicate if the fragment should load the deck options immediately after it loads
    private boolean mLoadWithDeckOptions;

    private boolean mFragmented;

    private Thread mFullNewCountThread = null;

    private StudyOptionsListener mListener;

    /**
     * Callbacks for UI events
     */
    private final View.OnClickListener mButtonClickListener = v -> {
        if (v.getId() == R.id.studyoptions_start) {
            Timber.i("StudyOptionsFragment:: start study button pressed");
            if (mCurrentContentView != CONTENT_CONGRATS) {
                openReviewer();
            } else {
                showCustomStudyContextMenu();
            }
        }
    };

    public interface StudyOptionsListener {

        void onRequireDeckListUpdate();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (!ListenerUtil.mutListener.listen(11744)) {
            super.onAttach(context);
        }
        try {
            if (!ListenerUtil.mutListener.listen(11745)) {
                mListener = (StudyOptionsListener) context;
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement StudyOptionsListener");
        }
    }

    private void openFilteredDeckOptions() {
        if (!ListenerUtil.mutListener.listen(11746)) {
            openFilteredDeckOptions(false);
        }
    }

    /**
     * Open the FilteredDeckOptions activity to allow the user to modify the parameters of the
     * filtered deck.
     * @param defaultConfig If true, signals to the FilteredDeckOptions activity that the filtered
     *                      deck has no options associated with it yet and should use a default
     *                      set of values.
     */
    private void openFilteredDeckOptions(boolean defaultConfig) {
        Intent i = new Intent(getActivity(), FilteredDeckOptions.class);
        if (!ListenerUtil.mutListener.listen(11747)) {
            i.putExtra("defaultConfig", defaultConfig);
        }
        if (!ListenerUtil.mutListener.listen(11748)) {
            getActivity().startActivityForResult(i, DECK_OPTIONS);
        }
        if (!ListenerUtil.mutListener.listen(11749)) {
            ActivityTransitionAnimation.slide(getActivity(), FADE);
        }
    }

    /**
     * Get a new instance of the fragment.
     * @param withDeckOptions If true, the fragment will load a new activity on top of itself
     *                        which shows the current deck's options. Set to true when programmatically
     *                        opening a new filtered deck for the first time.
     */
    public static StudyOptionsFragment newInstance(boolean withDeckOptions) {
        StudyOptionsFragment f = new StudyOptionsFragment();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(11750)) {
            args.putBoolean("withDeckOptions", withDeckOptions);
        }
        if (!ListenerUtil.mutListener.listen(11751)) {
            f.setArguments(args);
        }
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11752)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11755)) {
            // If we're being restored, don't launch deck options again.
            if ((ListenerUtil.mutListener.listen(11753) ? (savedInstanceState == null || getArguments() != null) : (savedInstanceState == null && getArguments() != null))) {
                if (!ListenerUtil.mutListener.listen(11754)) {
                    mLoadWithDeckOptions = getArguments().getBoolean("withDeckOptions");
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11756)) {
            if (container == null) {
                // Currently in a layout without a container, so no reason to create our view.
                return null;
            }
        }
        View studyOptionsView = inflater.inflate(R.layout.studyoptions_fragment, container, false);
        if (!ListenerUtil.mutListener.listen(11757)) {
            mStudyOptionsView = studyOptionsView;
        }
        if (!ListenerUtil.mutListener.listen(11758)) {
            mFragmented = getActivity().getClass() != StudyOptionsActivity.class;
        }
        if (!ListenerUtil.mutListener.listen(11759)) {
            initAllContentViews(studyOptionsView);
        }
        if (!ListenerUtil.mutListener.listen(11760)) {
            mToolbar = studyOptionsView.findViewById(R.id.studyOptionsToolbar);
        }
        if (!ListenerUtil.mutListener.listen(11761)) {
            mToolbar.inflateMenu(R.menu.study_options_fragment);
        }
        if (!ListenerUtil.mutListener.listen(11763)) {
            if (mToolbar != null) {
                if (!ListenerUtil.mutListener.listen(11762)) {
                    configureToolbar();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11764)) {
            refreshInterface(true);
        }
        return studyOptionsView;
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(11765)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(11767)) {
            if (mFullNewCountThread != null) {
                if (!ListenerUtil.mutListener.listen(11766)) {
                    mFullNewCountThread.interrupt();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11768)) {
            Timber.d("onDestroy()");
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(11769)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(11770)) {
            Timber.d("onResume()");
        }
        if (!ListenerUtil.mutListener.listen(11771)) {
            refreshInterface(true);
        }
    }

    private void closeStudyOptions(int result) {
        Activity a = getActivity();
        if (!ListenerUtil.mutListener.listen(11777)) {
            if ((ListenerUtil.mutListener.listen(11772) ? (!mFragmented || a != null) : (!mFragmented && a != null))) {
                if (!ListenerUtil.mutListener.listen(11774)) {
                    a.setResult(result);
                }
                if (!ListenerUtil.mutListener.listen(11775)) {
                    a.finish();
                }
                if (!ListenerUtil.mutListener.listen(11776)) {
                    ActivityTransitionAnimation.slide(a, RIGHT);
                }
            } else if (a == null) {
                if (!ListenerUtil.mutListener.listen(11773)) {
                    // which is particularly relevant when using AsyncTasks.
                    Timber.e("closeStudyOptions() failed due to getActivity() returning null");
                }
            }
        }
    }

    private void openReviewer() {
        Intent reviewer = new Intent(getActivity(), Reviewer.class);
        if (!ListenerUtil.mutListener.listen(11783)) {
            if (mFragmented) {
                if (!ListenerUtil.mutListener.listen(11781)) {
                    mToReviewer = true;
                }
                if (!ListenerUtil.mutListener.listen(11782)) {
                    getActivity().startActivityForResult(reviewer, AnkiActivity.REQUEST_REVIEW);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11778)) {
                    // Go to DeckPicker after studying when not tablet
                    reviewer.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                }
                if (!ListenerUtil.mutListener.listen(11779)) {
                    startActivity(reviewer);
                }
                if (!ListenerUtil.mutListener.listen(11780)) {
                    getActivity().finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11784)) {
            animateLeft();
        }
    }

    private void animateLeft() {
        if (!ListenerUtil.mutListener.listen(11785)) {
            ActivityTransitionAnimation.slide(getActivity(), LEFT);
        }
    }

    private void initAllContentViews(@NonNull View studyOptionsView) {
        if (!ListenerUtil.mutListener.listen(11787)) {
            if (mFragmented) {
                if (!ListenerUtil.mutListener.listen(11786)) {
                    studyOptionsView.findViewById(R.id.studyoptions_gradient).setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11788)) {
            mDeckInfoLayout = studyOptionsView.findViewById(R.id.studyoptions_deckinformation);
        }
        if (!ListenerUtil.mutListener.listen(11789)) {
            mTextDeckName = studyOptionsView.findViewById(R.id.studyoptions_deck_name);
        }
        if (!ListenerUtil.mutListener.listen(11790)) {
            mTextDeckDescription = studyOptionsView.findViewById(R.id.studyoptions_deck_description);
        }
        if (!ListenerUtil.mutListener.listen(11791)) {
            // make links clickable
            mTextDeckDescription.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (!ListenerUtil.mutListener.listen(11792)) {
            mButtonStart = studyOptionsView.findViewById(R.id.studyoptions_start);
        }
        if (!ListenerUtil.mutListener.listen(11793)) {
            mTextCongratsMessage = studyOptionsView.findViewById(R.id.studyoptions_congrats_message);
        }
        if (!ListenerUtil.mutListener.listen(11794)) {
            // Code common to both fragmented and non-fragmented view
            mTextTodayNew = studyOptionsView.findViewById(R.id.studyoptions_new);
        }
        if (!ListenerUtil.mutListener.listen(11795)) {
            mTextTodayLrn = studyOptionsView.findViewById(R.id.studyoptions_lrn);
        }
        if (!ListenerUtil.mutListener.listen(11796)) {
            mTextTodayRev = studyOptionsView.findViewById(R.id.studyoptions_rev);
        }
        if (!ListenerUtil.mutListener.listen(11797)) {
            mTextNewTotal = studyOptionsView.findViewById(R.id.studyoptions_total_new);
        }
        if (!ListenerUtil.mutListener.listen(11798)) {
            mTextTotal = studyOptionsView.findViewById(R.id.studyoptions_total);
        }
        if (!ListenerUtil.mutListener.listen(11799)) {
            mTextETA = studyOptionsView.findViewById(R.id.studyoptions_eta);
        }
        if (!ListenerUtil.mutListener.listen(11800)) {
            mButtonStart.setOnClickListener(mButtonClickListener);
        }
    }

    /**
     * Show the context menu for the custom study options
     */
    private void showCustomStudyContextMenu() {
        CustomStudyDialog d = CustomStudyDialog.newInstance(CustomStudyDialog.CONTEXT_MENU_STANDARD, getCol().getDecks().selected());
        if (!ListenerUtil.mutListener.listen(11801)) {
            ((AnkiActivity) getActivity()).showDialogFragment(d);
        }
    }

    void setFragmentContentView(View newView) {
        ViewGroup parent = (ViewGroup) this.getView();
        if (!ListenerUtil.mutListener.listen(11802)) {
            parent.removeAllViews();
        }
        if (!ListenerUtil.mutListener.listen(11803)) {
            parent.addView(newView);
        }
    }

    private final TaskListener<Card, BooleanGetter> undoListener = new TaskListener<Card, BooleanGetter>() {

        @Override
        public void onPreExecute() {
        }

        @Override
        public void onPostExecute(BooleanGetter v) {
            if (!ListenerUtil.mutListener.listen(11804)) {
                openReviewer();
            }
        }
    };

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(11827)) {
            if (itemId == R.id.action_undo) {
                if (!ListenerUtil.mutListener.listen(11825)) {
                    Timber.i("StudyOptionsFragment:: Undo button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11826)) {
                    TaskManager.launchCollectionTask(new CollectionTask.Undo(), undoListener);
                }
                return true;
            } else if (itemId == R.id.action_deck_or_study_options) {
                if (!ListenerUtil.mutListener.listen(11820)) {
                    Timber.i("StudyOptionsFragment:: Deck or study options button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11824)) {
                    if (getCol().getDecks().isDyn(getCol().getDecks().selected())) {
                        if (!ListenerUtil.mutListener.listen(11823)) {
                            openFilteredDeckOptions();
                        }
                    } else {
                        Intent i = new Intent(getActivity(), DeckOptions.class);
                        if (!ListenerUtil.mutListener.listen(11821)) {
                            getActivity().startActivityForResult(i, DECK_OPTIONS);
                        }
                        if (!ListenerUtil.mutListener.listen(11822)) {
                            ActivityTransitionAnimation.slide(getActivity(), FADE);
                        }
                    }
                }
                return true;
            } else if (itemId == R.id.action_custom_study) {
                if (!ListenerUtil.mutListener.listen(11818)) {
                    Timber.i("StudyOptionsFragment:: custom study button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11819)) {
                    showCustomStudyContextMenu();
                }
                return true;
            } else if (itemId == R.id.action_unbury) {
                if (!ListenerUtil.mutListener.listen(11814)) {
                    Timber.i("StudyOptionsFragment:: unbury button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11815)) {
                    getCol().getSched().unburyCardsForDeck();
                }
                if (!ListenerUtil.mutListener.listen(11816)) {
                    refreshInterfaceAndDecklist(true);
                }
                if (!ListenerUtil.mutListener.listen(11817)) {
                    item.setVisible(false);
                }
                return true;
            } else if (itemId == R.id.action_rebuild) {
                if (!ListenerUtil.mutListener.listen(11811)) {
                    Timber.i("StudyOptionsFragment:: rebuild cram deck button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11812)) {
                    mProgressDialog = StyledProgressDialog.show(getActivity(), "", getResources().getString(R.string.rebuild_filtered_deck), true);
                }
                if (!ListenerUtil.mutListener.listen(11813)) {
                    TaskManager.launchCollectionTask(new CollectionTask.RebuildCram(), getCollectionTaskListener(true));
                }
                return true;
            } else if (itemId == R.id.action_empty) {
                if (!ListenerUtil.mutListener.listen(11808)) {
                    Timber.i("StudyOptionsFragment:: empty cram deck button pressed");
                }
                if (!ListenerUtil.mutListener.listen(11809)) {
                    mProgressDialog = StyledProgressDialog.show(getActivity(), "", getResources().getString(R.string.empty_filtered_deck), false);
                }
                if (!ListenerUtil.mutListener.listen(11810)) {
                    TaskManager.launchCollectionTask(new CollectionTask.EmptyCram(), getCollectionTaskListener(true));
                }
                return true;
            } else if (itemId == R.id.action_rename) {
                if (!ListenerUtil.mutListener.listen(11807)) {
                    ((DeckPicker) getActivity()).renameDeckDialog(getCol().getDecks().selected());
                }
                return true;
            } else if (itemId == R.id.action_delete) {
                if (!ListenerUtil.mutListener.listen(11806)) {
                    ((DeckPicker) getActivity()).confirmDeckDeletion(getCol().getDecks().selected());
                }
                return true;
            } else if (itemId == R.id.action_export) {
                if (!ListenerUtil.mutListener.listen(11805)) {
                    ((DeckPicker) getActivity()).exportDeck(getCol().getDecks().selected());
                }
                return true;
            }
        }
        return false;
    }

    public void configureToolbar() {
        if (!ListenerUtil.mutListener.listen(11828)) {
            configureToolbarInternal(true);
        }
    }

    // caused by sync on startup where this might be running then have the collection close
    private void configureToolbarInternal(boolean recur) {
        try {
            if (!ListenerUtil.mutListener.listen(11837)) {
                mToolbar.setOnMenuItemClickListener(this);
            }
            Menu menu = mToolbar.getMenu();
            if (!ListenerUtil.mutListener.listen(11846)) {
                // Switch on or off rebuild/empty/custom study depending on whether or not filtered deck
                if (getCol().getDecks().isDyn(getCol().getDecks().selected())) {
                    if (!ListenerUtil.mutListener.listen(11842)) {
                        menu.findItem(R.id.action_rebuild).setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(11843)) {
                        menu.findItem(R.id.action_empty).setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(11844)) {
                        menu.findItem(R.id.action_custom_study).setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(11845)) {
                        menu.findItem(R.id.action_deck_or_study_options).setTitle(R.string.menu__study_options);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11838)) {
                        menu.findItem(R.id.action_rebuild).setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(11839)) {
                        menu.findItem(R.id.action_empty).setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(11840)) {
                        menu.findItem(R.id.action_custom_study).setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(11841)) {
                        menu.findItem(R.id.action_deck_or_study_options).setTitle(R.string.menu__deck_options);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(11853)) {
                // Don't show custom study icon if congrats shown
                if ((ListenerUtil.mutListener.listen(11851) ? (mCurrentContentView >= CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11850) ? (mCurrentContentView <= CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11849) ? (mCurrentContentView > CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11848) ? (mCurrentContentView < CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11847) ? (mCurrentContentView != CONTENT_CONGRATS) : (mCurrentContentView == CONTENT_CONGRATS))))))) {
                    if (!ListenerUtil.mutListener.listen(11852)) {
                        menu.findItem(R.id.action_custom_study).setVisible(false);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(11860)) {
                // Switch on rename / delete / export if tablet layout
                if (mFragmented) {
                    if (!ListenerUtil.mutListener.listen(11857)) {
                        menu.findItem(R.id.action_rename).setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(11858)) {
                        menu.findItem(R.id.action_delete).setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(11859)) {
                        menu.findItem(R.id.action_export).setVisible(true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11854)) {
                        menu.findItem(R.id.action_rename).setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(11855)) {
                        menu.findItem(R.id.action_delete).setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(11856)) {
                        menu.findItem(R.id.action_export).setVisible(false);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(11861)) {
                // Switch on or off unbury depending on if there are cards to unbury
                menu.findItem(R.id.action_unbury).setVisible(getCol().getSched().haveBuried());
            }
            if (!ListenerUtil.mutListener.listen(11865)) {
                // Switch on or off undo depending on whether undo is available
                if (!getCol().undoAvailable()) {
                    if (!ListenerUtil.mutListener.listen(11864)) {
                        menu.findItem(R.id.action_undo).setVisible(false);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(11862)) {
                        menu.findItem(R.id.action_undo).setVisible(true);
                    }
                    Resources res = AnkiDroidApp.getAppResources();
                    if (!ListenerUtil.mutListener.listen(11863)) {
                        menu.findItem(R.id.action_undo).setTitle(res.getString(R.string.studyoptions_congrats_undo, getCol().undoName(res)));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(11868)) {
                // Set the back button listener
                if (!mFragmented) {
                    if (!ListenerUtil.mutListener.listen(11866)) {
                        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
                    }
                    if (!ListenerUtil.mutListener.listen(11867)) {
                        mToolbar.setNavigationOnClickListener(v -> ((AnkiActivity) getActivity()).finishWithAnimation(RIGHT));
                    }
                }
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(11836)) {
                if (!CollectionHelper.getInstance().colIsOpen()) {
                    if (!ListenerUtil.mutListener.listen(11835)) {
                        if (recur) {
                            if (!ListenerUtil.mutListener.listen(11830)) {
                                Timber.i(e, "Database closed while working. Probably auto-sync. Will re-try after sleep.");
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(11833)) {
                                    Thread.sleep(1000);
                                }
                            } catch (InterruptedException ex) {
                                if (!ListenerUtil.mutListener.listen(11831)) {
                                    Timber.i(ex, "Thread interrupted while waiting to retry. Likely unimportant.");
                                }
                                if (!ListenerUtil.mutListener.listen(11832)) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11834)) {
                                configureToolbarInternal(false);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11829)) {
                                Timber.w(e, "Database closed while working. No re-tries left.");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(11869)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
        if (!ListenerUtil.mutListener.listen(11870)) {
            Timber.d("onActivityResult (requestCode = %d, resultCode = %d)", requestCode, resultCode);
        }
        if (!ListenerUtil.mutListener.listen(11871)) {
            // rebuild action bar
            configureToolbar();
        }
        if (!ListenerUtil.mutListener.listen(11884)) {
            // boot back to deck picker if there was an error
            if ((ListenerUtil.mutListener.listen(11882) ? ((ListenerUtil.mutListener.listen(11876) ? (resultCode >= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11875) ? (resultCode <= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11874) ? (resultCode > DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11873) ? (resultCode < DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11872) ? (resultCode != DeckPicker.RESULT_DB_ERROR) : (resultCode == DeckPicker.RESULT_DB_ERROR)))))) && (ListenerUtil.mutListener.listen(11881) ? (resultCode >= DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11880) ? (resultCode <= DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11879) ? (resultCode > DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11878) ? (resultCode < DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11877) ? (resultCode != DeckPicker.RESULT_MEDIA_EJECTED) : (resultCode == DeckPicker.RESULT_MEDIA_EJECTED))))))) : ((ListenerUtil.mutListener.listen(11876) ? (resultCode >= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11875) ? (resultCode <= DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11874) ? (resultCode > DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11873) ? (resultCode < DeckPicker.RESULT_DB_ERROR) : (ListenerUtil.mutListener.listen(11872) ? (resultCode != DeckPicker.RESULT_DB_ERROR) : (resultCode == DeckPicker.RESULT_DB_ERROR)))))) || (ListenerUtil.mutListener.listen(11881) ? (resultCode >= DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11880) ? (resultCode <= DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11879) ? (resultCode > DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11878) ? (resultCode < DeckPicker.RESULT_MEDIA_EJECTED) : (ListenerUtil.mutListener.listen(11877) ? (resultCode != DeckPicker.RESULT_MEDIA_EJECTED) : (resultCode == DeckPicker.RESULT_MEDIA_EJECTED))))))))) {
                if (!ListenerUtil.mutListener.listen(11883)) {
                    closeStudyOptions(resultCode);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11898)) {
            // perform some special actions depending on which activity we're returning from
            if ((ListenerUtil.mutListener.listen(11895) ? ((ListenerUtil.mutListener.listen(11889) ? (requestCode >= STATISTICS) : (ListenerUtil.mutListener.listen(11888) ? (requestCode <= STATISTICS) : (ListenerUtil.mutListener.listen(11887) ? (requestCode > STATISTICS) : (ListenerUtil.mutListener.listen(11886) ? (requestCode < STATISTICS) : (ListenerUtil.mutListener.listen(11885) ? (requestCode != STATISTICS) : (requestCode == STATISTICS)))))) && (ListenerUtil.mutListener.listen(11894) ? (requestCode >= BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11893) ? (requestCode <= BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11892) ? (requestCode > BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11891) ? (requestCode < BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11890) ? (requestCode != BROWSE_CARDS) : (requestCode == BROWSE_CARDS))))))) : ((ListenerUtil.mutListener.listen(11889) ? (requestCode >= STATISTICS) : (ListenerUtil.mutListener.listen(11888) ? (requestCode <= STATISTICS) : (ListenerUtil.mutListener.listen(11887) ? (requestCode > STATISTICS) : (ListenerUtil.mutListener.listen(11886) ? (requestCode < STATISTICS) : (ListenerUtil.mutListener.listen(11885) ? (requestCode != STATISTICS) : (requestCode == STATISTICS)))))) || (ListenerUtil.mutListener.listen(11894) ? (requestCode >= BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11893) ? (requestCode <= BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11892) ? (requestCode > BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11891) ? (requestCode < BROWSE_CARDS) : (ListenerUtil.mutListener.listen(11890) ? (requestCode != BROWSE_CARDS) : (requestCode == BROWSE_CARDS))))))))) {
                if (!ListenerUtil.mutListener.listen(11897)) {
                    // which can change the selected deck
                    if (intent.hasExtra("originalDeck")) {
                        if (!ListenerUtil.mutListener.listen(11896)) {
                            getCol().getDecks().select(intent.getLongExtra("originalDeck", 0L));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11945)) {
            if ((ListenerUtil.mutListener.listen(11903) ? (requestCode >= DECK_OPTIONS) : (ListenerUtil.mutListener.listen(11902) ? (requestCode <= DECK_OPTIONS) : (ListenerUtil.mutListener.listen(11901) ? (requestCode > DECK_OPTIONS) : (ListenerUtil.mutListener.listen(11900) ? (requestCode < DECK_OPTIONS) : (ListenerUtil.mutListener.listen(11899) ? (requestCode != DECK_OPTIONS) : (requestCode == DECK_OPTIONS))))))) {
                if (!ListenerUtil.mutListener.listen(11944)) {
                    if (mLoadWithDeckOptions) {
                        if (!ListenerUtil.mutListener.listen(11938)) {
                            mLoadWithDeckOptions = false;
                        }
                        Deck deck = getCol().getDecks().current();
                        if (!ListenerUtil.mutListener.listen(11941)) {
                            if ((ListenerUtil.mutListener.listen(11939) ? (deck.getInt("dyn") == DECK_DYN || deck.has("empty")) : (deck.getInt("dyn") == DECK_DYN && deck.has("empty")))) {
                                if (!ListenerUtil.mutListener.listen(11940)) {
                                    deck.remove("empty");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11942)) {
                            mProgressDialog = StyledProgressDialog.show(getActivity(), "", getResources().getString(R.string.rebuild_filtered_deck), true);
                        }
                        if (!ListenerUtil.mutListener.listen(11943)) {
                            TaskManager.launchCollectionTask(new CollectionTask.RebuildCram(), getCollectionTaskListener(true));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11936)) {
                            TaskManager.waitToFinish();
                        }
                        if (!ListenerUtil.mutListener.listen(11937)) {
                            refreshInterface(true);
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(11908) ? (requestCode >= AnkiActivity.REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(11907) ? (requestCode <= AnkiActivity.REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(11906) ? (requestCode > AnkiActivity.REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(11905) ? (requestCode < AnkiActivity.REQUEST_REVIEW) : (ListenerUtil.mutListener.listen(11904) ? (requestCode != AnkiActivity.REQUEST_REVIEW) : (requestCode == AnkiActivity.REQUEST_REVIEW))))))) {
                if (!ListenerUtil.mutListener.listen(11935)) {
                    if ((ListenerUtil.mutListener.listen(11926) ? (resultCode >= Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(11925) ? (resultCode <= Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(11924) ? (resultCode > Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(11923) ? (resultCode < Reviewer.RESULT_NO_MORE_CARDS) : (ListenerUtil.mutListener.listen(11922) ? (resultCode != Reviewer.RESULT_NO_MORE_CARDS) : (resultCode == Reviewer.RESULT_NO_MORE_CARDS))))))) {
                        if (!ListenerUtil.mutListener.listen(11934)) {
                            // If no more cards getting returned while counts > 0 (due to learn ahead limit) then show a snackbar
                            if ((ListenerUtil.mutListener.listen(11932) ? ((ListenerUtil.mutListener.listen(11931) ? (getCol().getSched().count() >= 0) : (ListenerUtil.mutListener.listen(11930) ? (getCol().getSched().count() <= 0) : (ListenerUtil.mutListener.listen(11929) ? (getCol().getSched().count() < 0) : (ListenerUtil.mutListener.listen(11928) ? (getCol().getSched().count() != 0) : (ListenerUtil.mutListener.listen(11927) ? (getCol().getSched().count() == 0) : (getCol().getSched().count() > 0)))))) || mStudyOptionsView != null) : ((ListenerUtil.mutListener.listen(11931) ? (getCol().getSched().count() >= 0) : (ListenerUtil.mutListener.listen(11930) ? (getCol().getSched().count() <= 0) : (ListenerUtil.mutListener.listen(11929) ? (getCol().getSched().count() < 0) : (ListenerUtil.mutListener.listen(11928) ? (getCol().getSched().count() != 0) : (ListenerUtil.mutListener.listen(11927) ? (getCol().getSched().count() == 0) : (getCol().getSched().count() > 0)))))) && mStudyOptionsView != null))) {
                                View rootLayout = mStudyOptionsView.findViewById(R.id.studyoptions_main);
                                if (!ListenerUtil.mutListener.listen(11933)) {
                                    UIUtils.showSnackbar(getActivity(), R.string.studyoptions_no_cards_due, false, 0, null, rootLayout);
                                }
                            }
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(11919) ? ((ListenerUtil.mutListener.listen(11913) ? (requestCode >= STATISTICS) : (ListenerUtil.mutListener.listen(11912) ? (requestCode <= STATISTICS) : (ListenerUtil.mutListener.listen(11911) ? (requestCode > STATISTICS) : (ListenerUtil.mutListener.listen(11910) ? (requestCode < STATISTICS) : (ListenerUtil.mutListener.listen(11909) ? (requestCode != STATISTICS) : (requestCode == STATISTICS)))))) || (ListenerUtil.mutListener.listen(11918) ? (mCurrentContentView >= CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11917) ? (mCurrentContentView <= CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11916) ? (mCurrentContentView > CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11915) ? (mCurrentContentView < CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11914) ? (mCurrentContentView != CONTENT_CONGRATS) : (mCurrentContentView == CONTENT_CONGRATS))))))) : ((ListenerUtil.mutListener.listen(11913) ? (requestCode >= STATISTICS) : (ListenerUtil.mutListener.listen(11912) ? (requestCode <= STATISTICS) : (ListenerUtil.mutListener.listen(11911) ? (requestCode > STATISTICS) : (ListenerUtil.mutListener.listen(11910) ? (requestCode < STATISTICS) : (ListenerUtil.mutListener.listen(11909) ? (requestCode != STATISTICS) : (requestCode == STATISTICS)))))) && (ListenerUtil.mutListener.listen(11918) ? (mCurrentContentView >= CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11917) ? (mCurrentContentView <= CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11916) ? (mCurrentContentView > CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11915) ? (mCurrentContentView < CONTENT_CONGRATS) : (ListenerUtil.mutListener.listen(11914) ? (mCurrentContentView != CONTENT_CONGRATS) : (mCurrentContentView == CONTENT_CONGRATS))))))))) {
                if (!ListenerUtil.mutListener.listen(11920)) {
                    mCurrentContentView = CONTENT_STUDY_OPTIONS;
                }
                if (!ListenerUtil.mutListener.listen(11921)) {
                    setFragmentContentView(mStudyOptionsView);
                }
            }
        }
    }

    private void dismissProgressDialog() {
        if (!ListenerUtil.mutListener.listen(11948)) {
            if ((ListenerUtil.mutListener.listen(11946) ? (mStudyOptionsView != null || mStudyOptionsView.findViewById(R.id.progress_bar) != null) : (mStudyOptionsView != null && mStudyOptionsView.findViewById(R.id.progress_bar) != null))) {
                if (!ListenerUtil.mutListener.listen(11947)) {
                    mStudyOptionsView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11952)) {
            // for rebuilding cram decks
            if ((ListenerUtil.mutListener.listen(11949) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                try {
                    if (!ListenerUtil.mutListener.listen(11951)) {
                        mProgressDialog.dismiss();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(11950)) {
                        Timber.e("onPostExecute - Dialog dismiss Exception = %s", e.getMessage());
                    }
                }
            }
        }
    }

    private void refreshInterfaceAndDecklist(boolean resetSched) {
        if (!ListenerUtil.mutListener.listen(11953)) {
            refreshInterface(resetSched, true);
        }
    }

    protected void refreshInterface() {
        if (!ListenerUtil.mutListener.listen(11954)) {
            refreshInterface(false, false);
        }
    }

    protected void refreshInterface(boolean resetSched) {
        if (!ListenerUtil.mutListener.listen(11955)) {
            refreshInterface(resetSched, false);
        }
    }

    /**
     * Rebuild the fragment's interface to reflect the status of the currently selected deck.
     *
     * @param resetSched    Indicates whether to rebuild the queues as well. Set to true for any
     *                      task that modifies queues (e.g., unbury or empty filtered deck).
     * @param resetDecklist Indicates whether to call back to the parent activity in order to
     *                      also refresh the deck list.
     */
    protected void refreshInterface(boolean resetSched, boolean resetDecklist) {
        if (!ListenerUtil.mutListener.listen(11956)) {
            Timber.d("Refreshing StudyOptionsFragment");
        }
        if (!ListenerUtil.mutListener.listen(11957)) {
            TaskManager.cancelAllTasks(CollectionTask.UpdateValuesFromDeck.class);
        }
        if (!ListenerUtil.mutListener.listen(11958)) {
            // Load the deck counts for the deck from Collection asynchronously
            TaskManager.launchCollectionTask(new CollectionTask.UpdateValuesFromDeck(resetSched), getCollectionTaskListener(resetDecklist));
        }
    }

    /**
     * Returns a listener that rebuilds the interface after execute.
     *
     * @param refreshDecklist If true, the listener notifies the parent activity to update its deck list
     *                        to reflect the latest values.
     */
    private TaskListener<Void, int[]> getCollectionTaskListener(final boolean refreshDecklist) {
        return new TaskListener<Void, int[]>() {

            @Override
            public void onPreExecute() {
            }

            @Override
            public void onPostExecute(int[] obj) {
                if (!ListenerUtil.mutListener.listen(11959)) {
                    dismissProgressDialog();
                }
                if (!ListenerUtil.mutListener.listen(12071)) {
                    if (obj != null) {
                        // Get the return values back from the AsyncTask
                        int newCards = obj[0];
                        int lrnCards = obj[1];
                        int revCards = obj[2];
                        int totalNew = obj[3];
                        int totalCards = obj[4];
                        int eta = obj[5];
                        if (!ListenerUtil.mutListener.listen(11961)) {
                            // Don't do anything if the fragment is no longer attached to it's Activity or col has been closed
                            if (getActivity() == null) {
                                if (!ListenerUtil.mutListener.listen(11960)) {
                                    Timber.e("StudyOptionsFragment.mRefreshFragmentListener :: can't refresh");
                                }
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11963)) {
                            // #5506 If we have no view, short circuit all UI logic
                            if (mStudyOptionsView == null) {
                                if (!ListenerUtil.mutListener.listen(11962)) {
                                    tryOpenCramDeckOptions();
                                }
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11964)) {
                            // Reinitialize controls incase changed to filtered deck
                            initAllContentViews(mStudyOptionsView);
                        }
                        // Set the deck name
                        Deck deck = getCol().getDecks().current();
                        // Main deck name
                        String fullName = deck.getString("name");
                        String[] name = Decks.path(fullName);
                        StringBuilder nameBuilder = new StringBuilder();
                        if (!ListenerUtil.mutListener.listen(11971)) {
                            if ((ListenerUtil.mutListener.listen(11969) ? (name.length >= 0) : (ListenerUtil.mutListener.listen(11968) ? (name.length <= 0) : (ListenerUtil.mutListener.listen(11967) ? (name.length < 0) : (ListenerUtil.mutListener.listen(11966) ? (name.length != 0) : (ListenerUtil.mutListener.listen(11965) ? (name.length == 0) : (name.length > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(11970)) {
                                    nameBuilder.append(name[0]);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11978)) {
                            if ((ListenerUtil.mutListener.listen(11976) ? (name.length >= 1) : (ListenerUtil.mutListener.listen(11975) ? (name.length <= 1) : (ListenerUtil.mutListener.listen(11974) ? (name.length < 1) : (ListenerUtil.mutListener.listen(11973) ? (name.length != 1) : (ListenerUtil.mutListener.listen(11972) ? (name.length == 1) : (name.length > 1))))))) {
                                if (!ListenerUtil.mutListener.listen(11977)) {
                                    nameBuilder.append("\n").append(name[1]);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11985)) {
                            if ((ListenerUtil.mutListener.listen(11983) ? (name.length >= 3) : (ListenerUtil.mutListener.listen(11982) ? (name.length <= 3) : (ListenerUtil.mutListener.listen(11981) ? (name.length < 3) : (ListenerUtil.mutListener.listen(11980) ? (name.length != 3) : (ListenerUtil.mutListener.listen(11979) ? (name.length == 3) : (name.length > 3))))))) {
                                if (!ListenerUtil.mutListener.listen(11984)) {
                                    nameBuilder.append("...");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11996)) {
                            if ((ListenerUtil.mutListener.listen(11990) ? (name.length >= 2) : (ListenerUtil.mutListener.listen(11989) ? (name.length <= 2) : (ListenerUtil.mutListener.listen(11988) ? (name.length < 2) : (ListenerUtil.mutListener.listen(11987) ? (name.length != 2) : (ListenerUtil.mutListener.listen(11986) ? (name.length == 2) : (name.length > 2))))))) {
                                if (!ListenerUtil.mutListener.listen(11995)) {
                                    nameBuilder.append("\n").append(name[(ListenerUtil.mutListener.listen(11994) ? (name.length % 1) : (ListenerUtil.mutListener.listen(11993) ? (name.length / 1) : (ListenerUtil.mutListener.listen(11992) ? (name.length * 1) : (ListenerUtil.mutListener.listen(11991) ? (name.length + 1) : (name.length - 1)))))]);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11997)) {
                            mTextDeckName.setText(nameBuilder.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(11998)) {
                            if (tryOpenCramDeckOptions()) {
                                return;
                            }
                        }
                        // Switch between the empty view, the ordinary view, and the "congratulations" view
                        boolean isDynamic = deck.optInt("dyn", DECK_STD) == DECK_DYN;
                        if (!ListenerUtil.mutListener.listen(12036)) {
                            if ((ListenerUtil.mutListener.listen(12004) ? ((ListenerUtil.mutListener.listen(12003) ? (totalCards >= 0) : (ListenerUtil.mutListener.listen(12002) ? (totalCards <= 0) : (ListenerUtil.mutListener.listen(12001) ? (totalCards > 0) : (ListenerUtil.mutListener.listen(12000) ? (totalCards < 0) : (ListenerUtil.mutListener.listen(11999) ? (totalCards != 0) : (totalCards == 0)))))) || !isDynamic) : ((ListenerUtil.mutListener.listen(12003) ? (totalCards >= 0) : (ListenerUtil.mutListener.listen(12002) ? (totalCards <= 0) : (ListenerUtil.mutListener.listen(12001) ? (totalCards > 0) : (ListenerUtil.mutListener.listen(12000) ? (totalCards < 0) : (ListenerUtil.mutListener.listen(11999) ? (totalCards != 0) : (totalCards == 0)))))) && !isDynamic))) {
                                if (!ListenerUtil.mutListener.listen(12031)) {
                                    mCurrentContentView = CONTENT_EMPTY;
                                }
                                if (!ListenerUtil.mutListener.listen(12032)) {
                                    mDeckInfoLayout.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(12033)) {
                                    mTextCongratsMessage.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(12034)) {
                                    mTextCongratsMessage.setText(R.string.studyoptions_empty);
                                }
                                if (!ListenerUtil.mutListener.listen(12035)) {
                                    mButtonStart.setVisibility(View.GONE);
                                }
                            } else if ((ListenerUtil.mutListener.listen(12017) ? ((ListenerUtil.mutListener.listen(12012) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) % revCards) : (ListenerUtil.mutListener.listen(12011) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) / revCards) : (ListenerUtil.mutListener.listen(12010) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) * revCards) : (ListenerUtil.mutListener.listen(12009) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) - revCards) : ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) + revCards))))) >= 0) : (ListenerUtil.mutListener.listen(12016) ? ((ListenerUtil.mutListener.listen(12012) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) % revCards) : (ListenerUtil.mutListener.listen(12011) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) / revCards) : (ListenerUtil.mutListener.listen(12010) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) * revCards) : (ListenerUtil.mutListener.listen(12009) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) - revCards) : ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) + revCards))))) <= 0) : (ListenerUtil.mutListener.listen(12015) ? ((ListenerUtil.mutListener.listen(12012) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) % revCards) : (ListenerUtil.mutListener.listen(12011) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) / revCards) : (ListenerUtil.mutListener.listen(12010) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) * revCards) : (ListenerUtil.mutListener.listen(12009) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) - revCards) : ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) + revCards))))) > 0) : (ListenerUtil.mutListener.listen(12014) ? ((ListenerUtil.mutListener.listen(12012) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) % revCards) : (ListenerUtil.mutListener.listen(12011) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) / revCards) : (ListenerUtil.mutListener.listen(12010) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) * revCards) : (ListenerUtil.mutListener.listen(12009) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) - revCards) : ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) + revCards))))) < 0) : (ListenerUtil.mutListener.listen(12013) ? ((ListenerUtil.mutListener.listen(12012) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) % revCards) : (ListenerUtil.mutListener.listen(12011) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) / revCards) : (ListenerUtil.mutListener.listen(12010) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) * revCards) : (ListenerUtil.mutListener.listen(12009) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) - revCards) : ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) + revCards))))) != 0) : ((ListenerUtil.mutListener.listen(12012) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) % revCards) : (ListenerUtil.mutListener.listen(12011) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) / revCards) : (ListenerUtil.mutListener.listen(12010) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) * revCards) : (ListenerUtil.mutListener.listen(12009) ? ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) - revCards) : ((ListenerUtil.mutListener.listen(12008) ? (newCards % lrnCards) : (ListenerUtil.mutListener.listen(12007) ? (newCards / lrnCards) : (ListenerUtil.mutListener.listen(12006) ? (newCards * lrnCards) : (ListenerUtil.mutListener.listen(12005) ? (newCards - lrnCards) : (newCards + lrnCards))))) + revCards))))) == 0))))))) {
                                if (!ListenerUtil.mutListener.listen(12023)) {
                                    mCurrentContentView = CONTENT_CONGRATS;
                                }
                                if (!ListenerUtil.mutListener.listen(12028)) {
                                    if (!isDynamic) {
                                        if (!ListenerUtil.mutListener.listen(12025)) {
                                            mDeckInfoLayout.setVisibility(View.GONE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(12026)) {
                                            mButtonStart.setVisibility(View.VISIBLE);
                                        }
                                        if (!ListenerUtil.mutListener.listen(12027)) {
                                            mButtonStart.setText(R.string.custom_study);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(12024)) {
                                            mButtonStart.setVisibility(View.GONE);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(12029)) {
                                    mTextCongratsMessage.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(12030)) {
                                    mTextCongratsMessage.setText(getCol().getSched().finishedMsg(getActivity()));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12018)) {
                                    mCurrentContentView = CONTENT_STUDY_OPTIONS;
                                }
                                if (!ListenerUtil.mutListener.listen(12019)) {
                                    mDeckInfoLayout.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(12020)) {
                                    mTextCongratsMessage.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(12021)) {
                                    mButtonStart.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(12022)) {
                                    mButtonStart.setText(R.string.studyoptions_start);
                                }
                            }
                        }
                        // Set deck description
                        String desc;
                        if (isDynamic) {
                            desc = getResources().getString(R.string.dyn_deck_desc);
                        } else {
                            desc = getCol().getDecks().getActualDescription();
                        }
                        if (!ListenerUtil.mutListener.listen(12045)) {
                            if ((ListenerUtil.mutListener.listen(12041) ? (desc.length() >= 0) : (ListenerUtil.mutListener.listen(12040) ? (desc.length() <= 0) : (ListenerUtil.mutListener.listen(12039) ? (desc.length() < 0) : (ListenerUtil.mutListener.listen(12038) ? (desc.length() != 0) : (ListenerUtil.mutListener.listen(12037) ? (desc.length() == 0) : (desc.length() > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(12043)) {
                                    mTextDeckDescription.setText(formatDescription(desc));
                                }
                                if (!ListenerUtil.mutListener.listen(12044)) {
                                    mTextDeckDescription.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12042)) {
                                    mTextDeckDescription.setVisibility(View.GONE);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12046)) {
                            // Set new/learn/review card counts
                            mTextTodayNew.setText(String.valueOf(newCards));
                        }
                        if (!ListenerUtil.mutListener.listen(12047)) {
                            mTextTodayLrn.setText(String.valueOf(lrnCards));
                        }
                        if (!ListenerUtil.mutListener.listen(12048)) {
                            mTextTodayRev.setText(String.valueOf(revCards));
                        }
                        if (!ListenerUtil.mutListener.listen(12060)) {
                            // Set the total number of new cards in deck
                            if ((ListenerUtil.mutListener.listen(12053) ? (totalNew >= NEW_CARD_COUNT_TRUNCATE_THRESHOLD) : (ListenerUtil.mutListener.listen(12052) ? (totalNew <= NEW_CARD_COUNT_TRUNCATE_THRESHOLD) : (ListenerUtil.mutListener.listen(12051) ? (totalNew > NEW_CARD_COUNT_TRUNCATE_THRESHOLD) : (ListenerUtil.mutListener.listen(12050) ? (totalNew != NEW_CARD_COUNT_TRUNCATE_THRESHOLD) : (ListenerUtil.mutListener.listen(12049) ? (totalNew == NEW_CARD_COUNT_TRUNCATE_THRESHOLD) : (totalNew < NEW_CARD_COUNT_TRUNCATE_THRESHOLD))))))) {
                                if (!ListenerUtil.mutListener.listen(12059)) {
                                    // if it hasn't been truncated by libanki then just set it usually
                                    mTextNewTotal.setText(String.valueOf(totalNew));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12054)) {
                                    // if truncated then make a thread to allow full count to load
                                    mTextNewTotal.setText(">1000");
                                }
                                if (!ListenerUtil.mutListener.listen(12056)) {
                                    if (mFullNewCountThread != null) {
                                        if (!ListenerUtil.mutListener.listen(12055)) {
                                            // a thread was previously made -- interrupt it
                                            mFullNewCountThread.interrupt();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(12057)) {
                                    mFullNewCountThread = new Thread(() -> {
                                        Collection collection = getCol();
                                        // TODO: refactor code to not rewrite this query, add to Sched.totalNewForCurrentDeck()
                                        String query = "SELECT count(*) FROM cards WHERE did IN " + Utils.ids2str(collection.getDecks().active()) + " AND queue = " + Consts.QUEUE_TYPE_NEW;
                                        final int fullNewCount = collection.getDb().queryScalar(query);
                                        if (fullNewCount > 0) {
                                            Runnable setNewTotalText = () -> mTextNewTotal.setText(String.valueOf(fullNewCount));
                                            if (!Thread.currentThread().isInterrupted()) {
                                                mTextNewTotal.post(setNewTotalText);
                                            }
                                        }
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(12058)) {
                                    mFullNewCountThread.start();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12061)) {
                            // Set total number of cards
                            mTextTotal.setText(String.valueOf(totalCards));
                        }
                        if (!ListenerUtil.mutListener.listen(12069)) {
                            // Set estimated time remaining
                            if ((ListenerUtil.mutListener.listen(12066) ? (eta >= -1) : (ListenerUtil.mutListener.listen(12065) ? (eta <= -1) : (ListenerUtil.mutListener.listen(12064) ? (eta > -1) : (ListenerUtil.mutListener.listen(12063) ? (eta < -1) : (ListenerUtil.mutListener.listen(12062) ? (eta == -1) : (eta != -1))))))) {
                                if (!ListenerUtil.mutListener.listen(12068)) {
                                    mTextETA.setText(Integer.toString(eta));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(12067)) {
                                    mTextETA.setText("-");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12070)) {
                            // Rebuild the options menu
                            configureToolbar();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12074)) {
                    // If in fragmented mode, refresh the deck list
                    if ((ListenerUtil.mutListener.listen(12072) ? (mFragmented || refreshDecklist) : (mFragmented && refreshDecklist))) {
                        if (!ListenerUtil.mutListener.listen(12073)) {
                            mListener.onRequireDeckListUpdate();
                        }
                    }
                }
            }
        };
    }

    /**
     * Open cram deck option if deck is opened for the first time
     * @return Whether we opened the deck options
     */
    private boolean tryOpenCramDeckOptions() {
        if (!ListenerUtil.mutListener.listen(12075)) {
            if (!mLoadWithDeckOptions) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(12076)) {
            openFilteredDeckOptions(true);
        }
        if (!ListenerUtil.mutListener.listen(12077)) {
            mLoadWithDeckOptions = false;
        }
        return true;
    }

    @VisibleForTesting()
    static Spanned formatDescription(String desc) {
        // Since we don't currently execute the JS/CSS, it's not worth displaying.
        String withStrippedTags = Utils.stripHTMLScriptAndStyleTags(desc);
        // #5188 - fromHtml displays newlines as " "
        String withFixedNewlines = HtmlUtils.convertNewlinesToHtml(withStrippedTags);
        return HtmlCompat.fromHtml(withFixedNewlines, HtmlCompat.FROM_HTML_MODE_LEGACY);
    }

    private Collection getCol() {
        return CollectionHelper.getInstance().getCol(getContext());
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(12078)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(12080)) {
            if (!mToReviewer) {
                if (!ListenerUtil.mutListener.listen(12079)) {
                    // deck) cancel counts.
                    TaskManager.cancelAllTasks(CollectionTask.UpdateValuesFromDeck.class);
                }
            }
        }
    }
}
