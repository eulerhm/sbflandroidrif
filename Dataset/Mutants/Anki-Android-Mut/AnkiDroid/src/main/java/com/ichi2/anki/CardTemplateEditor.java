/**
 * ************************************************************************************
 *                                                                                       *
 *  Copyright (c) 2014 Timothy Rae <perceptualchaos2@gmail.com>                          *
 *  Copyright (c) 2018 Mike Hardy <mike@mikehardy.net>                                   *
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

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ichi2.anki.dialogs.ConfirmationDialog;
import com.ichi2.anki.dialogs.DeckSelectionDialog;
import com.ichi2.anki.dialogs.DeckSelectionDialog.SelectableDeck;
import com.ichi2.anki.dialogs.DiscardChangesDialog;
import com.ichi2.anki.exception.ConfirmModSchemaException;
import com.ichi2.async.TaskListenerWithContext;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Consts;
import com.ichi2.libanki.Deck;
import com.ichi2.libanki.Decks;
import com.ichi2.libanki.Model;
import com.ichi2.libanki.Models;
import com.ichi2.themes.StyledProgressDialog;
import com.ichi2.utils.FunctionalInterfaces;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONException;
import com.ichi2.utils.JSONObject;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import timber.log.Timber;
import static com.ichi2.anim.ActivityTransitionAnimation.Direction.*;
import static com.ichi2.libanki.Models.NOT_FOUND_NOTE_TYPE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Allows the user to view the template for the current note type
 */
@SuppressWarnings({ "PMD.AvoidThrowingRawExceptionTypes" })
public class CardTemplateEditor extends AnkiActivity implements DeckSelectionDialog.DeckSelectionListener {

    @VisibleForTesting
    protected ViewPager2 mViewPager;

    private TabLayout mSlidingTabLayout;

    private TemporaryModel mTempModel;

    private long mModelId;

    private long mNoteId;

    private int mStartingOrdId;

    private static final int REQUEST_PREVIEWER = 0;

    private static final int REQUEST_CARD_BROWSER_APPEARANCE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6289)) {
            if (showedActivityFailedScreen(savedInstanceState)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6290)) {
            Timber.d("onCreate()");
        }
        if (!ListenerUtil.mutListener.listen(6291)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(6292)) {
            setContentView(R.layout.card_template_editor_activity);
        }
        if (!ListenerUtil.mutListener.listen(6308)) {
            // Load the args either from the intent or savedInstanceState bundle
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(6297)) {
                    // get model id
                    mModelId = getIntent().getLongExtra("modelId", NOT_FOUND_NOTE_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(6305)) {
                    if ((ListenerUtil.mutListener.listen(6302) ? (mModelId >= NOT_FOUND_NOTE_TYPE) : (ListenerUtil.mutListener.listen(6301) ? (mModelId <= NOT_FOUND_NOTE_TYPE) : (ListenerUtil.mutListener.listen(6300) ? (mModelId > NOT_FOUND_NOTE_TYPE) : (ListenerUtil.mutListener.listen(6299) ? (mModelId < NOT_FOUND_NOTE_TYPE) : (ListenerUtil.mutListener.listen(6298) ? (mModelId != NOT_FOUND_NOTE_TYPE) : (mModelId == NOT_FOUND_NOTE_TYPE))))))) {
                        if (!ListenerUtil.mutListener.listen(6303)) {
                            Timber.e("CardTemplateEditor :: no model ID was provided");
                        }
                        if (!ListenerUtil.mutListener.listen(6304)) {
                            finishWithoutAnimation();
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(6306)) {
                    // get id for currently edited note (optional)
                    mNoteId = getIntent().getLongExtra("noteId", -1L);
                }
                if (!ListenerUtil.mutListener.listen(6307)) {
                    // get id for currently edited template (optional)
                    mStartingOrdId = getIntent().getIntExtra("ordId", -1);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6293)) {
                    mModelId = savedInstanceState.getLong("modelId");
                }
                if (!ListenerUtil.mutListener.listen(6294)) {
                    mNoteId = savedInstanceState.getLong("noteId");
                }
                if (!ListenerUtil.mutListener.listen(6295)) {
                    mStartingOrdId = savedInstanceState.getInt("ordId");
                }
                if (!ListenerUtil.mutListener.listen(6296)) {
                    mTempModel = TemporaryModel.fromBundle(savedInstanceState);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6309)) {
            // Disable the home icon
            enableToolbar();
        }
        if (!ListenerUtil.mutListener.listen(6310)) {
            startLoadingCollection();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6311)) {
            outState.putAll(getTempModel().toBundle());
        }
        if (!ListenerUtil.mutListener.listen(6312)) {
            outState.putLong("modelId", mModelId);
        }
        if (!ListenerUtil.mutListener.listen(6313)) {
            outState.putLong("noteId", mNoteId);
        }
        if (!ListenerUtil.mutListener.listen(6314)) {
            outState.putInt("ordId", mStartingOrdId);
        }
        if (!ListenerUtil.mutListener.listen(6315)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(6318)) {
            if (modelHasChanged()) {
                if (!ListenerUtil.mutListener.listen(6317)) {
                    showDiscardChangesDialog();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6316)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(6320)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(6319)) {
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback used to finish initializing the activity after the collection has been correctly loaded
     * @param col Collection which has been loaded
     */
    @Override
    protected void onCollectionLoaded(Collection col) {
        if (!ListenerUtil.mutListener.listen(6321)) {
            super.onCollectionLoaded(col);
        }
        if (!ListenerUtil.mutListener.listen(6323)) {
            // take the passed model id load it up for editing
            if (getTempModel() == null) {
                if (!ListenerUtil.mutListener.listen(6322)) {
                    mTempModel = new TemporaryModel(new Model(col.getModels().get(mModelId).toString()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6324)) {
            // Set up the ViewPager with the sections adapter.
            mViewPager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(6325)) {
            mViewPager.setAdapter(new TemplatePagerAdapter((this)));
        }
        if (!ListenerUtil.mutListener.listen(6326)) {
            mSlidingTabLayout = findViewById(R.id.sliding_tabs);
        }
        if (!ListenerUtil.mutListener.listen(6329)) {
            // Set activity title
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(6327)) {
                    getSupportActionBar().setTitle(R.string.title_activity_template_editor);
                }
                if (!ListenerUtil.mutListener.listen(6328)) {
                    getSupportActionBar().setSubtitle(mTempModel.getModel().optString("name"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6330)) {
            // Close collection opening dialog if needed
            Timber.i("CardTemplateEditor:: Card template editor successfully started for model id %d", mModelId);
        }
        if (!ListenerUtil.mutListener.listen(6331)) {
            // Set the tab to the current template if an ord id was provided
            Timber.d("Setting starting tab to %d", mStartingOrdId);
        }
        if (!ListenerUtil.mutListener.listen(6338)) {
            if ((ListenerUtil.mutListener.listen(6336) ? (mStartingOrdId >= -1) : (ListenerUtil.mutListener.listen(6335) ? (mStartingOrdId <= -1) : (ListenerUtil.mutListener.listen(6334) ? (mStartingOrdId > -1) : (ListenerUtil.mutListener.listen(6333) ? (mStartingOrdId < -1) : (ListenerUtil.mutListener.listen(6332) ? (mStartingOrdId == -1) : (mStartingOrdId != -1))))))) {
                if (!ListenerUtil.mutListener.listen(6337)) {
                    mViewPager.setCurrentItem(mStartingOrdId, animationDisabled());
                }
            }
        }
    }

    public boolean modelHasChanged() {
        JSONObject oldModel = getCol().getModels().get(mModelId);
        return (ListenerUtil.mutListener.listen(6339) ? (getTempModel() != null || !getTempModel().getModel().toString().equals(oldModel.toString())) : (getTempModel() != null && !getTempModel().getModel().toString().equals(oldModel.toString())));
    }

    public TemporaryModel getTempModel() {
        return mTempModel;
    }

    @VisibleForTesting
    public MaterialDialog showDiscardChangesDialog() {
        MaterialDialog discardDialog = DiscardChangesDialog.getDefault(this).onPositive((dialog, which) -> {
            Timber.i("TemplateEditor:: OK button pressed to confirm discard changes");
            // Clear the edited model from any cache files, and clear it from this objects memory to discard changes
            TemporaryModel.clearTempModelFiles();
            mTempModel = null;
            finishWithAnimation(RIGHT);
        }).build();
        if (!ListenerUtil.mutListener.listen(6340)) {
            discardDialog.show();
        }
        return discardDialog;
    }

    /**
     * When a deck is selected via Deck Override
     */
    @Override
    public void onDeckSelected(@Nullable SelectableDeck deck) {
        if (!ListenerUtil.mutListener.listen(6343)) {
            if (getTempModel().getModel().isCloze()) {
                if (!ListenerUtil.mutListener.listen(6341)) {
                    Timber.w("Attempted to set deck for cloze model");
                }
                if (!ListenerUtil.mutListener.listen(6342)) {
                    UIUtils.showThemedToast(this, getString(R.string.multimedia_editor_something_wrong), true);
                }
                return;
            }
        }
        int ordinal = mViewPager.getCurrentItem();
        JSONObject template = getTempModel().getTemplate(ordinal);
        String templateName = template.getString("name");
        if (!ListenerUtil.mutListener.listen(6347)) {
            if ((ListenerUtil.mutListener.listen(6344) ? (deck != null || Decks.isDynamic(getCol(), deck.getDeckId())) : (deck != null && Decks.isDynamic(getCol(), deck.getDeckId())))) {
                if (!ListenerUtil.mutListener.listen(6345)) {
                    Timber.w("Attempted to set default deck of %s to dynamic deck %s", templateName, deck.getName());
                }
                if (!ListenerUtil.mutListener.listen(6346)) {
                    UIUtils.showThemedToast(this, getString(R.string.multimedia_editor_something_wrong), true);
                }
                return;
            }
        }
        String message;
        if (deck == null) {
            if (!ListenerUtil.mutListener.listen(6350)) {
                Timber.i("Removing default template from template '%s'", templateName);
            }
            if (!ListenerUtil.mutListener.listen(6351)) {
                template.put("did", JSONObject.NULL);
            }
            message = getString(R.string.model_manager_deck_override_removed_message, templateName);
        } else {
            if (!ListenerUtil.mutListener.listen(6348)) {
                Timber.i("Setting template '%s' to '%s'", templateName, deck.getName());
            }
            if (!ListenerUtil.mutListener.listen(6349)) {
                template.put("did", deck.getDeckId());
            }
            message = getString(R.string.model_manager_deck_override_added_message, templateName, deck.getName());
        }
        if (!ListenerUtil.mutListener.listen(6352)) {
            UIUtils.showThemedToast(this, message, true);
        }
        if (!ListenerUtil.mutListener.listen(6353)) {
            // Deck Override can change from "on" <-> "off"
            supportInvalidateOptionsMenu();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!ListenerUtil.mutListener.listen(6357)) {
            if (keyCode == KeyEvent.KEYCODE_P) {
                if (!ListenerUtil.mutListener.listen(6356)) {
                    if (event.isCtrlPressed()) {
                        CardTemplateFragment currentFragment = getCurrentFragment();
                        if (!ListenerUtil.mutListener.listen(6355)) {
                            if (currentFragment != null) {
                                if (!ListenerUtil.mutListener.listen(6354)) {
                                    currentFragment.performPreview();
                                }
                            }
                        }
                    }
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Nullable
    private CardTemplateFragment getCurrentFragment() {
        try {
            return (CardTemplateFragment) getSupportFragmentManager().findFragmentByTag("f" + mViewPager.getCurrentItem());
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(6358)) {
                Timber.w("Failed to get current fragment");
            }
            return null;
        }
    }

    /**
     * A {@link androidx.viewpager2.adapter.FragmentStateAdapter} that returns a fragment corresponding to
     * one of the tabs.
     */
    public class TemplatePagerAdapter extends FragmentStateAdapter {

        private long baseId = 0;

        public TemplatePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return CardTemplateFragment.newInstance(position, mNoteId);
        }

        @Override
        public int getItemCount() {
            if (!ListenerUtil.mutListener.listen(6359)) {
                if (getTempModel() != null) {
                    return getTempModel().getTemplateCount();
                }
            }
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return (ListenerUtil.mutListener.listen(6363) ? (baseId % position) : (ListenerUtil.mutListener.listen(6362) ? (baseId / position) : (ListenerUtil.mutListener.listen(6361) ? (baseId * position) : (ListenerUtil.mutListener.listen(6360) ? (baseId - position) : (baseId + position)))));
        }

        @Override
        public boolean containsItem(long id) {
            return ((ListenerUtil.mutListener.listen(6382) ? ((ListenerUtil.mutListener.listen(6372) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) >= getItemCount()) : (ListenerUtil.mutListener.listen(6371) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) <= getItemCount()) : (ListenerUtil.mutListener.listen(6370) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) > getItemCount()) : (ListenerUtil.mutListener.listen(6369) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) != getItemCount()) : (ListenerUtil.mutListener.listen(6368) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) == getItemCount()) : ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) < getItemCount())))))) || (ListenerUtil.mutListener.listen(6381) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) <= 0) : (ListenerUtil.mutListener.listen(6380) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) > 0) : (ListenerUtil.mutListener.listen(6379) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) < 0) : (ListenerUtil.mutListener.listen(6378) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) != 0) : (ListenerUtil.mutListener.listen(6377) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) == 0) : ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) >= 0))))))) : ((ListenerUtil.mutListener.listen(6372) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) >= getItemCount()) : (ListenerUtil.mutListener.listen(6371) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) <= getItemCount()) : (ListenerUtil.mutListener.listen(6370) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) > getItemCount()) : (ListenerUtil.mutListener.listen(6369) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) != getItemCount()) : (ListenerUtil.mutListener.listen(6368) ? ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) == getItemCount()) : ((ListenerUtil.mutListener.listen(6367) ? (id % baseId) : (ListenerUtil.mutListener.listen(6366) ? (id / baseId) : (ListenerUtil.mutListener.listen(6365) ? (id * baseId) : (ListenerUtil.mutListener.listen(6364) ? (id + baseId) : (id - baseId))))) < getItemCount())))))) && (ListenerUtil.mutListener.listen(6381) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) <= 0) : (ListenerUtil.mutListener.listen(6380) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) > 0) : (ListenerUtil.mutListener.listen(6379) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) < 0) : (ListenerUtil.mutListener.listen(6378) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) != 0) : (ListenerUtil.mutListener.listen(6377) ? ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) == 0) : ((ListenerUtil.mutListener.listen(6376) ? (id % baseId) : (ListenerUtil.mutListener.listen(6375) ? (id / baseId) : (ListenerUtil.mutListener.listen(6374) ? (id * baseId) : (ListenerUtil.mutListener.listen(6373) ? (id + baseId) : (id - baseId))))) >= 0)))))))));
        }

        /**
         * Force fragments to reinitialize contents by invalidating previous set of ordinal-based ids
         */
        public void ordinalShift() {
            if (!ListenerUtil.mutListener.listen(6387)) {
                baseId += (ListenerUtil.mutListener.listen(6386) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(6385) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(6384) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(6383) ? (getItemCount() - 1) : (getItemCount() + 1)))));
            }
        }
    }

    public static class CardTemplateFragment extends Fragment {

        private EditText mFront;

        private EditText mCss;

        private EditText mBack;

        private CardTemplateEditor mTemplateEditor;

        private TabLayoutMediator mTabLayoutMediator;

        public static CardTemplateFragment newInstance(int position, long noteId) {
            CardTemplateFragment f = new CardTemplateFragment();
            Bundle args = new Bundle();
            if (!ListenerUtil.mutListener.listen(6388)) {
                args.putInt("position", position);
            }
            if (!ListenerUtil.mutListener.listen(6389)) {
                args.putLong("noteId", noteId);
            }
            if (!ListenerUtil.mutListener.listen(6390)) {
                f.setArguments(args);
            }
            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(6391)) {
                // Storing a reference to the templateEditor allows us to use member variables
                mTemplateEditor = (CardTemplateEditor) getActivity();
            }
            View mainView = inflater.inflate(R.layout.card_template_editor_item, container, false);
            final int position = getArguments().getInt("position");
            TemporaryModel tempModel = mTemplateEditor.getTempModel();
            // Load template
            final JSONObject template;
            try {
                template = tempModel.getTemplate(position);
            } catch (JSONException e) {
                if (!ListenerUtil.mutListener.listen(6392)) {
                    Timber.d(e, "Exception loading template in CardTemplateFragment. Probably stale fragment.");
                }
                return mainView;
            }
            if (!ListenerUtil.mutListener.listen(6393)) {
                // Load EditText Views
                mFront = mainView.findViewById(R.id.front_edit);
            }
            if (!ListenerUtil.mutListener.listen(6394)) {
                mCss = mainView.findViewById(R.id.styling_edit);
            }
            if (!ListenerUtil.mutListener.listen(6395)) {
                mBack = mainView.findViewById(R.id.back_edit);
            }
            if (!ListenerUtil.mutListener.listen(6396)) {
                // Set EditText content
                mFront.setText(template.getString("qfmt"));
            }
            if (!ListenerUtil.mutListener.listen(6397)) {
                mCss.setText(tempModel.getCss());
            }
            if (!ListenerUtil.mutListener.listen(6398)) {
                mBack.setText(template.getString("afmt"));
            }
            // Set text change listeners
            TextWatcher templateEditorWatcher = new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {
                    if (!ListenerUtil.mutListener.listen(6399)) {
                        template.put("qfmt", mFront.getText());
                    }
                    if (!ListenerUtil.mutListener.listen(6400)) {
                        template.put("afmt", mBack.getText());
                    }
                    if (!ListenerUtil.mutListener.listen(6401)) {
                        mTemplateEditor.getTempModel().updateCss(mCss.getText().toString());
                    }
                    if (!ListenerUtil.mutListener.listen(6402)) {
                        mTemplateEditor.getTempModel().updateTemplate(position, template);
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                }
            };
            if (!ListenerUtil.mutListener.listen(6403)) {
                mFront.addTextChangedListener(templateEditorWatcher);
            }
            if (!ListenerUtil.mutListener.listen(6404)) {
                mCss.addTextChangedListener(templateEditorWatcher);
            }
            if (!ListenerUtil.mutListener.listen(6405)) {
                mBack.addTextChangedListener(templateEditorWatcher);
            }
            if (!ListenerUtil.mutListener.listen(6406)) {
                // Enable menu
                setHasOptionsMenu(true);
            }
            return mainView;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(6407)) {
                initTabLayoutMediator();
            }
        }

        private void initTabLayoutMediator() {
            if (!ListenerUtil.mutListener.listen(6409)) {
                if (mTabLayoutMediator != null) {
                    if (!ListenerUtil.mutListener.listen(6408)) {
                        mTabLayoutMediator.detach();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6410)) {
                mTabLayoutMediator = new TabLayoutMediator(mTemplateEditor.mSlidingTabLayout, mTemplateEditor.mViewPager, (tab, position) -> tab.setText(mTemplateEditor.getTempModel().getTemplate(position).getString("name")));
            }
            if (!ListenerUtil.mutListener.listen(6411)) {
                mTabLayoutMediator.attach();
            }
        }

        @Override
        public void onResume() {
            if (!ListenerUtil.mutListener.listen(6412)) {
                // initTabLayoutMediator();
                super.onResume();
            }
        }

        @Override
        public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
            if (!ListenerUtil.mutListener.listen(6413)) {
                menu.clear();
            }
            if (!ListenerUtil.mutListener.listen(6414)) {
                inflater.inflate(R.menu.card_template_editor, menu);
            }
            if (!ListenerUtil.mutListener.listen(6420)) {
                if (mTemplateEditor.getTempModel().getModel().isCloze()) {
                    if (!ListenerUtil.mutListener.listen(6417)) {
                        Timber.d("Editing cloze model, disabling add/delete card template and deck override functionality");
                    }
                    if (!ListenerUtil.mutListener.listen(6418)) {
                        menu.findItem(R.id.action_add).setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(6419)) {
                        menu.findItem(R.id.action_add_deck_override).setVisible(false);
                    }
                } else {
                    JSONObject template = getCurrentTemplate();
                    @StringRes
                    int overrideStringRes = (ListenerUtil.mutListener.listen(6415) ? (template.has("did") || !template.isNull("did")) : (template.has("did") && !template.isNull("did"))) ? R.string.card_template_editor_deck_override_on : R.string.card_template_editor_deck_override_off;
                    if (!ListenerUtil.mutListener.listen(6416)) {
                        menu.findItem(R.id.action_add_deck_override).setTitle(overrideStringRes);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6427)) {
                // It is invalid to delete if there is only one card template, remove the option from UI
                if ((ListenerUtil.mutListener.listen(6425) ? (mTemplateEditor.getTempModel().getTemplateCount() >= 2) : (ListenerUtil.mutListener.listen(6424) ? (mTemplateEditor.getTempModel().getTemplateCount() <= 2) : (ListenerUtil.mutListener.listen(6423) ? (mTemplateEditor.getTempModel().getTemplateCount() > 2) : (ListenerUtil.mutListener.listen(6422) ? (mTemplateEditor.getTempModel().getTemplateCount() != 2) : (ListenerUtil.mutListener.listen(6421) ? (mTemplateEditor.getTempModel().getTemplateCount() == 2) : (mTemplateEditor.getTempModel().getTemplateCount() < 2))))))) {
                    if (!ListenerUtil.mutListener.listen(6426)) {
                        menu.findItem(R.id.action_delete).setVisible(false);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6428)) {
                super.onCreateOptionsMenu(menu, inflater);
            }
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            final Collection col = mTemplateEditor.getCol();
            TemporaryModel tempModel = mTemplateEditor.getTempModel();
            int itemId = item.getItemId();
            if (!ListenerUtil.mutListener.listen(6457)) {
                if (itemId == R.id.action_add) {
                    if (!ListenerUtil.mutListener.listen(6455)) {
                        Timber.i("CardTemplateEditor:: Add template button pressed");
                    }
                    if (!ListenerUtil.mutListener.listen(6456)) {
                        // AnkiDroid never had this so it isn't a regression but it is a miss for AnkiDesktop parity
                        addNewTemplateWithCheck(tempModel.getModel());
                    }
                    return true;
                } else if (itemId == R.id.action_delete) {
                    if (!ListenerUtil.mutListener.listen(6442)) {
                        Timber.i("CardTemplateEditor:: Delete template button pressed");
                    }
                    Resources res = getResources();
                    int ordinal = mTemplateEditor.mViewPager.getCurrentItem();
                    final JSONObject template = tempModel.getTemplate(ordinal);
                    if (!ListenerUtil.mutListener.listen(6449)) {
                        // Don't do anything if only one template
                        if ((ListenerUtil.mutListener.listen(6447) ? (tempModel.getTemplateCount() >= 2) : (ListenerUtil.mutListener.listen(6446) ? (tempModel.getTemplateCount() <= 2) : (ListenerUtil.mutListener.listen(6445) ? (tempModel.getTemplateCount() > 2) : (ListenerUtil.mutListener.listen(6444) ? (tempModel.getTemplateCount() != 2) : (ListenerUtil.mutListener.listen(6443) ? (tempModel.getTemplateCount() == 2) : (tempModel.getTemplateCount() < 2))))))) {
                            if (!ListenerUtil.mutListener.listen(6448)) {
                                mTemplateEditor.showSimpleMessageDialog(res.getString(R.string.card_template_editor_cant_delete));
                            }
                            return true;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6450)) {
                        if (deletionWouldOrphanNote(col, tempModel, ordinal)) {
                            return true;
                        }
                    }
                    // Show confirmation dialog
                    int numAffectedCards = 0;
                    if (!ListenerUtil.mutListener.listen(6453)) {
                        if (!TemporaryModel.isOrdinalPendingAdd(tempModel, ordinal)) {
                            if (!ListenerUtil.mutListener.listen(6451)) {
                                Timber.d("Ordinal is not a pending add, so we'll get the current card count for confirmation");
                            }
                            if (!ListenerUtil.mutListener.listen(6452)) {
                                numAffectedCards = col.getModels().tmplUseCount(tempModel.getModel(), ordinal);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6454)) {
                        confirmDeleteCards(template, tempModel.getModel(), numAffectedCards);
                    }
                    return true;
                } else if (itemId == R.id.action_add_deck_override) {
                    if (!ListenerUtil.mutListener.listen(6441)) {
                        displayDeckOverrideDialog(col, tempModel);
                    }
                    return true;
                } else if (itemId == R.id.action_preview) {
                    if (!ListenerUtil.mutListener.listen(6440)) {
                        performPreview();
                    }
                    return true;
                } else if (itemId == R.id.action_confirm) {
                    if (!ListenerUtil.mutListener.listen(6431)) {
                        Timber.i("CardTemplateEditor:: Save model button pressed");
                    }
                    if (!ListenerUtil.mutListener.listen(6439)) {
                        if (modelHasChanged()) {
                            View confirmButton = mTemplateEditor.findViewById(R.id.action_confirm);
                            if (!ListenerUtil.mutListener.listen(6437)) {
                                if (confirmButton != null) {
                                    if (!ListenerUtil.mutListener.listen(6435)) {
                                        if (!confirmButton.isEnabled()) {
                                            if (!ListenerUtil.mutListener.listen(6434)) {
                                                Timber.d("CardTemplateEditor::discarding extra click after button disabled");
                                            }
                                            return true;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(6436)) {
                                        confirmButton.setEnabled(false);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6438)) {
                                tempModel.saveToDatabase(saveModelAndExitHandler());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(6432)) {
                                Timber.d("CardTemplateEditor:: model has not changed, exiting");
                            }
                            if (!ListenerUtil.mutListener.listen(6433)) {
                                mTemplateEditor.finishWithAnimation(RIGHT);
                            }
                        }
                    }
                    return true;
                } else if (itemId == R.id.action_card_browser_appearance) {
                    if (!ListenerUtil.mutListener.listen(6429)) {
                        Timber.i("CardTemplateEditor::Card Browser Template button pressed");
                    }
                    if (!ListenerUtil.mutListener.listen(6430)) {
                        launchCardBrowserAppearance(getCurrentTemplate());
                    }
                    return super.onOptionsItemSelected(item);
                }
            }
            return super.onOptionsItemSelected(item);
        }

        private void performPreview() {
            Collection col = mTemplateEditor.getCol();
            TemporaryModel tempModel = mTemplateEditor.getTempModel();
            if (!ListenerUtil.mutListener.listen(6458)) {
                Timber.i("CardTemplateEditor:: Preview on tab %s", mTemplateEditor.mViewPager.getCurrentItem());
            }
            // Create intent for the previewer and add some arguments
            Intent i = new Intent(mTemplateEditor, CardTemplatePreviewer.class);
            int ordinal = mTemplateEditor.mViewPager.getCurrentItem();
            long noteId = getArguments().getLong("noteId");
            if (!ListenerUtil.mutListener.listen(6459)) {
                i.putExtra("ordinal", ordinal);
            }
            if (!ListenerUtil.mutListener.listen(6472)) {
                // If we have a card for this position, send it, otherwise an empty cardlist signals to show a blank
                if ((ListenerUtil.mutListener.listen(6464) ? (noteId >= -1L) : (ListenerUtil.mutListener.listen(6463) ? (noteId <= -1L) : (ListenerUtil.mutListener.listen(6462) ? (noteId > -1L) : (ListenerUtil.mutListener.listen(6461) ? (noteId < -1L) : (ListenerUtil.mutListener.listen(6460) ? (noteId == -1L) : (noteId != -1L))))))) {
                    List<Long> cids = col.getNote(noteId).cids();
                    if (!ListenerUtil.mutListener.listen(6471)) {
                        if ((ListenerUtil.mutListener.listen(6469) ? (ordinal >= cids.size()) : (ListenerUtil.mutListener.listen(6468) ? (ordinal <= cids.size()) : (ListenerUtil.mutListener.listen(6467) ? (ordinal > cids.size()) : (ListenerUtil.mutListener.listen(6466) ? (ordinal != cids.size()) : (ListenerUtil.mutListener.listen(6465) ? (ordinal == cids.size()) : (ordinal < cids.size()))))))) {
                            if (!ListenerUtil.mutListener.listen(6470)) {
                                i.putExtra("cardList", new long[] { cids.get(ordinal) });
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6473)) {
                // Save the model and pass the filename if updated
                tempModel.setEditedModelFileName(TemporaryModel.saveTempModel(mTemplateEditor, tempModel.getModel()));
            }
            if (!ListenerUtil.mutListener.listen(6474)) {
                i.putExtra(TemporaryModel.INTENT_MODEL_FILENAME, tempModel.getEditedModelFileName());
            }
            if (!ListenerUtil.mutListener.listen(6475)) {
                startActivityForResult(i, REQUEST_PREVIEWER);
            }
        }

        private void displayDeckOverrideDialog(Collection col, TemporaryModel tempModel) {
            AnkiActivity activity = (AnkiActivity) requireActivity();
            if (!ListenerUtil.mutListener.listen(6477)) {
                if (tempModel.getModel().isCloze()) {
                    if (!ListenerUtil.mutListener.listen(6476)) {
                        UIUtils.showThemedToast(activity, getString(R.string.multimedia_editor_something_wrong), true);
                    }
                    return;
                }
            }
            String name = getCurrentTemplateName(tempModel);
            String explanation = getString(R.string.deck_override_explanation, name);
            // https://forums.ankiweb.net/t/minor-bug-deck-override-to-filtered-deck/1493
            FunctionalInterfaces.Filter<Deck> nonDynamic = (d) -> !Decks.isDynamic(d);
            List<SelectableDeck> decks = SelectableDeck.fromCollection(col, nonDynamic);
            String title = getString(R.string.card_template_editor_deck_override);
            DeckSelectionDialog dialog = DeckSelectionDialog.newInstance(title, explanation, decks);
            if (!ListenerUtil.mutListener.listen(6478)) {
                AnkiActivity.showDialogFragment(activity, dialog);
            }
        }

        private String getCurrentTemplateName(TemporaryModel tempModel) {
            try {
                int ordinal = mTemplateEditor.mViewPager.getCurrentItem();
                final JSONObject template = tempModel.getTemplate(ordinal);
                return template.getString("name");
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(6479)) {
                    Timber.w(e, "Failed to get name for template");
                }
                return "";
            }
        }

        private void launchCardBrowserAppearance(JSONObject currentTemplate) {
            Context context = AnkiDroidApp.getInstance().getBaseContext();
            if (!ListenerUtil.mutListener.listen(6481)) {
                if (context == null) {
                    if (!ListenerUtil.mutListener.listen(6480)) {
                        // Catch-22, we can't notify failure as there's no context. Shouldn't happen anyway
                        Timber.w("Context was null - couldn't launch Card Browser Appearance window");
                    }
                    return;
                }
            }
            Intent browserAppearanceIntent = CardTemplateBrowserAppearanceEditor.getIntentFromTemplate(context, currentTemplate);
            if (!ListenerUtil.mutListener.listen(6482)) {
                startActivityForResult(browserAppearanceIntent, REQUEST_CARD_BROWSER_APPEARANCE);
            }
        }

        @CheckResult
        @NonNull
        private JSONObject getCurrentTemplate() {
            int currentCardTemplateIndex = getCurrentCardTemplateIndex();
            return mTemplateEditor.getTempModel().getModel().getJSONArray("tmpls").getJSONObject(currentCardTemplateIndex);
        }

        /**
         * @return The index of the card template which is currently referred to by the fragment
         */
        @CheckResult
        private int getCurrentCardTemplateIndex() {
            // COULD_BE_BETTER: Lots of duplicate code could call this. Hold off on the refactor until #5151 goes in.
            return getArguments().getInt("position");
        }

        private boolean deletionWouldOrphanNote(Collection col, TemporaryModel tempModel, int position) {
            if (!ListenerUtil.mutListener.listen(6485)) {
                // pending deletes could orphan cards
                if (!TemporaryModel.isOrdinalPendingAdd(tempModel, position)) {
                    int[] currentDeletes = tempModel.getDeleteDbOrds(position);
                    if (!ListenerUtil.mutListener.listen(6484)) {
                        // TODO - this is a SQL query on GUI thread - should see a DeckTask conversion ideally
                        if (col.getModels().getCardIdsForModel(tempModel.getModelId(), currentDeletes) == null) {
                            if (!ListenerUtil.mutListener.listen(6483)) {
                                // not already have cards generated making it safe will see this error message:
                                mTemplateEditor.showSimpleMessageDialog(getResources().getString(R.string.card_template_editor_would_delete_note));
                            }
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if (!ListenerUtil.mutListener.listen(6486)) {
                super.onActivityResult(requestCode, resultCode, data);
            }
            if (!ListenerUtil.mutListener.listen(6493)) {
                if ((ListenerUtil.mutListener.listen(6491) ? (requestCode >= REQUEST_CARD_BROWSER_APPEARANCE) : (ListenerUtil.mutListener.listen(6490) ? (requestCode <= REQUEST_CARD_BROWSER_APPEARANCE) : (ListenerUtil.mutListener.listen(6489) ? (requestCode > REQUEST_CARD_BROWSER_APPEARANCE) : (ListenerUtil.mutListener.listen(6488) ? (requestCode < REQUEST_CARD_BROWSER_APPEARANCE) : (ListenerUtil.mutListener.listen(6487) ? (requestCode != REQUEST_CARD_BROWSER_APPEARANCE) : (requestCode == REQUEST_CARD_BROWSER_APPEARANCE))))))) {
                    if (!ListenerUtil.mutListener.listen(6492)) {
                        onCardBrowserAppearanceResult(resultCode, data);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(6502)) {
                if ((ListenerUtil.mutListener.listen(6498) ? (requestCode >= REQUEST_PREVIEWER) : (ListenerUtil.mutListener.listen(6497) ? (requestCode <= REQUEST_PREVIEWER) : (ListenerUtil.mutListener.listen(6496) ? (requestCode > REQUEST_PREVIEWER) : (ListenerUtil.mutListener.listen(6495) ? (requestCode < REQUEST_PREVIEWER) : (ListenerUtil.mutListener.listen(6494) ? (requestCode != REQUEST_PREVIEWER) : (requestCode == REQUEST_PREVIEWER))))))) {
                    if (!ListenerUtil.mutListener.listen(6499)) {
                        TemporaryModel.clearTempModelFiles();
                    }
                    if (!ListenerUtil.mutListener.listen(6500)) {
                        // Make sure the fragments reinitialize, otherwise there is staleness on return
                        ((TemplatePagerAdapter) mTemplateEditor.mViewPager.getAdapter()).ordinalShift();
                    }
                    if (!ListenerUtil.mutListener.listen(6501)) {
                        mTemplateEditor.mViewPager.getAdapter().notifyDataSetChanged();
                    }
                }
            }
        }

        private void onCardBrowserAppearanceResult(int resultCode, @Nullable Intent data) {
            if (!ListenerUtil.mutListener.listen(6504)) {
                if (resultCode != RESULT_OK) {
                    if (!ListenerUtil.mutListener.listen(6503)) {
                        Timber.i("Activity Cancelled: Card Template Browser Appearance");
                    }
                    return;
                }
            }
            CardTemplateBrowserAppearanceEditor.Result result = CardTemplateBrowserAppearanceEditor.Result.fromIntent(data);
            if (!ListenerUtil.mutListener.listen(6506)) {
                if (result == null) {
                    if (!ListenerUtil.mutListener.listen(6505)) {
                        Timber.w("Error processing Card Template Browser Appearance result");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(6507)) {
                Timber.i("Applying Card Template Browser Appearance result");
            }
            JSONObject currentTemplate = getCurrentTemplate();
            if (!ListenerUtil.mutListener.listen(6508)) {
                result.applyTo(currentTemplate);
            }
        }

        /* Used for updating the collection when a model has been edited */
        private SaveModelAndExitHandler saveModelAndExitHandler() {
            return new SaveModelAndExitHandler(this);
        }

        static class SaveModelAndExitHandler extends TaskListenerWithContext<CardTemplateFragment, Void, Pair<Boolean, String>> {

            public SaveModelAndExitHandler(CardTemplateFragment templateFragment) {
                super(templateFragment);
            }

            private MaterialDialog mProgressDialog = null;

            @Override
            public void actualOnPreExecute(@NonNull CardTemplateFragment templateFragment) {
                if (!ListenerUtil.mutListener.listen(6509)) {
                    Timber.d("saveModelAndExitHandler::preExecute called");
                }
                if (!ListenerUtil.mutListener.listen(6510)) {
                    mProgressDialog = StyledProgressDialog.show(templateFragment.mTemplateEditor, AnkiDroidApp.getAppResources().getString(R.string.saving_model), templateFragment.getResources().getString(R.string.saving_changes), false);
                }
            }

            @Override
            public void actualOnPostExecute(@NonNull CardTemplateFragment templateFragment, Pair<Boolean, String> result) {
                if (!ListenerUtil.mutListener.listen(6511)) {
                    Timber.d("saveModelAndExitHandler::postExecute called");
                }
                View button = templateFragment.mTemplateEditor.findViewById(R.id.action_confirm);
                if (!ListenerUtil.mutListener.listen(6513)) {
                    if (button != null) {
                        if (!ListenerUtil.mutListener.listen(6512)) {
                            button.setEnabled(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6516)) {
                    if ((ListenerUtil.mutListener.listen(6514) ? (mProgressDialog != null || mProgressDialog.isShowing()) : (mProgressDialog != null && mProgressDialog.isShowing()))) {
                        if (!ListenerUtil.mutListener.listen(6515)) {
                            mProgressDialog.dismiss();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(6517)) {
                    templateFragment.mTemplateEditor.mTempModel = null;
                }
                if (!ListenerUtil.mutListener.listen(6522)) {
                    if (result.first) {
                        if (!ListenerUtil.mutListener.listen(6521)) {
                            templateFragment.mTemplateEditor.finishWithAnimation(RIGHT);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6518)) {
                            Timber.w("CardTemplateFragment:: save model task failed: %s", result.second);
                        }
                        if (!ListenerUtil.mutListener.listen(6519)) {
                            UIUtils.showThemedToast(templateFragment.mTemplateEditor, templateFragment.getString(R.string.card_template_editor_save_error, result.second), false);
                        }
                        if (!ListenerUtil.mutListener.listen(6520)) {
                            templateFragment.mTemplateEditor.finishWithoutAnimation();
                        }
                    }
                }
            }
        }

        private boolean modelHasChanged() {
            return mTemplateEditor.modelHasChanged();
        }

        /**
         * Confirm if the user wants to delete all the cards associated with current template
         *
         * @param tmpl template to remove
         * @param model model to remove template from, modified in place by reference
         * @param numAffectedCards number of cards which will be affected
         */
        private void confirmDeleteCards(final JSONObject tmpl, final Model model, int numAffectedCards) {
            ConfirmationDialog d = new ConfirmationDialog();
            Resources res = getResources();
            String msg = String.format(res.getQuantityString(R.plurals.card_template_editor_confirm_delete, numAffectedCards), numAffectedCards, tmpl.optString("name"));
            if (!ListenerUtil.mutListener.listen(6523)) {
                d.setArgs(msg);
            }
            Runnable confirm = () -> deleteTemplateWithCheck(tmpl, model);
            if (!ListenerUtil.mutListener.listen(6524)) {
                d.setConfirm(confirm);
            }
            if (!ListenerUtil.mutListener.listen(6525)) {
                mTemplateEditor.showDialogFragment(d);
            }
        }

        /**
         * Delete tmpl from model, asking user to confirm again if it's going to require a full sync
         *
         * @param tmpl template to remove
         * @param model model to remove template from, modified in place by reference
         */
        private void deleteTemplateWithCheck(final JSONObject tmpl, final Model model) {
            try {
                if (!ListenerUtil.mutListener.listen(6530)) {
                    mTemplateEditor.getCol().modSchema();
                }
                if (!ListenerUtil.mutListener.listen(6531)) {
                    deleteTemplate(tmpl, model);
                }
            } catch (ConfirmModSchemaException e) {
                ConfirmationDialog d = new ConfirmationDialog();
                if (!ListenerUtil.mutListener.listen(6526)) {
                    d.setArgs(getResources().getString(R.string.full_sync_confirmation));
                }
                Runnable confirm = () -> {
                    mTemplateEditor.getCol().modSchemaNoCheck();
                    deleteTemplate(tmpl, model);
                };
                Runnable cancel = () -> mTemplateEditor.dismissAllDialogFragments();
                if (!ListenerUtil.mutListener.listen(6527)) {
                    d.setConfirm(confirm);
                }
                if (!ListenerUtil.mutListener.listen(6528)) {
                    d.setCancel(cancel);
                }
                if (!ListenerUtil.mutListener.listen(6529)) {
                    mTemplateEditor.showDialogFragment(d);
                }
            }
        }

        /**
         * @param tmpl template to remove
         * @param model model to remove from, updated in place by reference
         */
        private void deleteTemplate(JSONObject tmpl, Model model) {
            JSONArray oldTemplates = model.getJSONArray("tmpls");
            JSONArray newTemplates = new JSONArray();
            if (!ListenerUtil.mutListener.listen(6541)) {
                {
                    long _loopCounter115 = 0;
                    for (JSONObject possibleMatch : oldTemplates.jsonObjectIterable()) {
                        ListenerUtil.loopListener.listen("_loopCounter115", ++_loopCounter115);
                        if (!ListenerUtil.mutListener.listen(6540)) {
                            if ((ListenerUtil.mutListener.listen(6536) ? (possibleMatch.getInt("ord") >= tmpl.getInt("ord")) : (ListenerUtil.mutListener.listen(6535) ? (possibleMatch.getInt("ord") <= tmpl.getInt("ord")) : (ListenerUtil.mutListener.listen(6534) ? (possibleMatch.getInt("ord") > tmpl.getInt("ord")) : (ListenerUtil.mutListener.listen(6533) ? (possibleMatch.getInt("ord") < tmpl.getInt("ord")) : (ListenerUtil.mutListener.listen(6532) ? (possibleMatch.getInt("ord") == tmpl.getInt("ord")) : (possibleMatch.getInt("ord") != tmpl.getInt("ord")))))))) {
                                if (!ListenerUtil.mutListener.listen(6539)) {
                                    newTemplates.put(possibleMatch);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(6537)) {
                                    Timber.d("deleteTemplate() found match - removing template with ord %s", possibleMatch.getInt("ord"));
                                }
                                if (!ListenerUtil.mutListener.listen(6538)) {
                                    mTemplateEditor.getTempModel().removeTemplate(possibleMatch.getInt("ord"));
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6542)) {
                model.put("tmpls", newTemplates);
            }
            if (!ListenerUtil.mutListener.listen(6543)) {
                Models._updateTemplOrds(model);
            }
            if (!ListenerUtil.mutListener.listen(6544)) {
                // Make sure the fragments reinitialize, otherwise the reused ordinal causes staleness
                ((TemplatePagerAdapter) mTemplateEditor.mViewPager.getAdapter()).ordinalShift();
            }
            if (!ListenerUtil.mutListener.listen(6545)) {
                mTemplateEditor.mViewPager.getAdapter().notifyDataSetChanged();
            }
            if (!ListenerUtil.mutListener.listen(6550)) {
                mTemplateEditor.mViewPager.setCurrentItem((ListenerUtil.mutListener.listen(6549) ? (newTemplates.length() % 1) : (ListenerUtil.mutListener.listen(6548) ? (newTemplates.length() / 1) : (ListenerUtil.mutListener.listen(6547) ? (newTemplates.length() * 1) : (ListenerUtil.mutListener.listen(6546) ? (newTemplates.length() + 1) : (newTemplates.length() - 1))))), mTemplateEditor.animationDisabled());
            }
            if (!ListenerUtil.mutListener.listen(6552)) {
                if (getActivity() != null) {
                    if (!ListenerUtil.mutListener.listen(6551)) {
                        ((CardTemplateEditor) getActivity()).dismissAllDialogFragments();
                    }
                }
            }
        }

        /**
         * Add new template to model, asking user to confirm if it's going to require a full sync
         *
         * @param model model to add new template to
         */
        private void addNewTemplateWithCheck(final JSONObject model) {
            try {
                if (!ListenerUtil.mutListener.listen(6556)) {
                    mTemplateEditor.getCol().modSchema();
                }
                if (!ListenerUtil.mutListener.listen(6557)) {
                    Timber.d("addNewTemplateWithCheck() called and no CMSE?");
                }
                if (!ListenerUtil.mutListener.listen(6558)) {
                    addNewTemplate(model);
                }
            } catch (ConfirmModSchemaException e) {
                ConfirmationDialog d = new ConfirmationDialog();
                if (!ListenerUtil.mutListener.listen(6553)) {
                    d.setArgs(getResources().getString(R.string.full_sync_confirmation));
                }
                Runnable confirm = () -> {
                    mTemplateEditor.getCol().modSchemaNoCheck();
                    addNewTemplate(model);
                };
                if (!ListenerUtil.mutListener.listen(6554)) {
                    d.setConfirm(confirm);
                }
                if (!ListenerUtil.mutListener.listen(6555)) {
                    mTemplateEditor.showDialogFragment(d);
                }
            }
        }

        /**
         * Add new template to a given model
         * @param model model to add new template to
         */
        private void addNewTemplate(JSONObject model) {
            // Build new template
            int oldPosition = getArguments().getInt("position");
            JSONArray templates = model.getJSONArray("tmpls");
            JSONObject oldTemplate = templates.getJSONObject(oldPosition);
            JSONObject newTemplate = Models.newTemplate(newCardName(templates));
            if (!ListenerUtil.mutListener.listen(6559)) {
                // Set up question & answer formats
                newTemplate.put("qfmt", oldTemplate.getString("qfmt"));
            }
            if (!ListenerUtil.mutListener.listen(6560)) {
                newTemplate.put("afmt", oldTemplate.getString("afmt"));
            }
            if (!ListenerUtil.mutListener.listen(6562)) {
                // Reverse the front and back if only one template
                if (templates.length() == 1) {
                    if (!ListenerUtil.mutListener.listen(6561)) {
                        flipQA(newTemplate);
                    }
                }
            }
            int lastExistingOrd = templates.getJSONObject((ListenerUtil.mutListener.listen(6566) ? (templates.length() % 1) : (ListenerUtil.mutListener.listen(6565) ? (templates.length() / 1) : (ListenerUtil.mutListener.listen(6564) ? (templates.length() * 1) : (ListenerUtil.mutListener.listen(6563) ? (templates.length() + 1) : (templates.length() - 1)))))).getInt("ord");
            if (!ListenerUtil.mutListener.listen(6567)) {
                Timber.d("addNewTemplate() lastExistingOrd was %s", lastExistingOrd);
            }
            if (!ListenerUtil.mutListener.listen(6572)) {
                newTemplate.put("ord", (ListenerUtil.mutListener.listen(6571) ? (lastExistingOrd % 1) : (ListenerUtil.mutListener.listen(6570) ? (lastExistingOrd / 1) : (ListenerUtil.mutListener.listen(6569) ? (lastExistingOrd * 1) : (ListenerUtil.mutListener.listen(6568) ? (lastExistingOrd - 1) : (lastExistingOrd + 1))))));
            }
            if (!ListenerUtil.mutListener.listen(6573)) {
                templates.put(newTemplate);
            }
            if (!ListenerUtil.mutListener.listen(6574)) {
                mTemplateEditor.getTempModel().addNewTemplate(newTemplate);
            }
            if (!ListenerUtil.mutListener.listen(6575)) {
                mTemplateEditor.mViewPager.getAdapter().notifyDataSetChanged();
            }
            if (!ListenerUtil.mutListener.listen(6580)) {
                mTemplateEditor.mViewPager.setCurrentItem((ListenerUtil.mutListener.listen(6579) ? (templates.length() % 1) : (ListenerUtil.mutListener.listen(6578) ? (templates.length() / 1) : (ListenerUtil.mutListener.listen(6577) ? (templates.length() * 1) : (ListenerUtil.mutListener.listen(6576) ? (templates.length() + 1) : (templates.length() - 1))))), mTemplateEditor.animationDisabled());
            }
        }

        /**
         * Flip the question and answer side of the template
         * @param template template to flip
         */
        private void flipQA(JSONObject template) {
            String qfmt = template.getString("qfmt");
            String afmt = template.getString("afmt");
            Matcher m = Pattern.compile("(?s)(.+)<hr id=answer>(.+)").matcher(afmt);
            if (!ListenerUtil.mutListener.listen(6583)) {
                if (!m.find()) {
                    if (!ListenerUtil.mutListener.listen(6582)) {
                        template.put("qfmt", afmt.replace("{{FrontSide}}", ""));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(6581)) {
                        template.put("qfmt", m.group(2).trim());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(6584)) {
                template.put("afmt", "{{FrontSide}}\n\n<hr id=answer>\n\n" + qfmt);
            }
        }

        /**
         * Get name for new template
         * @param templates array of templates which is being added to
         * @return name for new template
         */
        private String newCardName(JSONArray templates) {
            String name;
            // Start by trying to set the name to "Card n" where n is the new num of templates
            int n = templates.length() + 1;
            {
                long _loopCounter117 = 0;
                // If the starting point for name already exists, iteratively increase n until we find a unique name
                while (true) {
                    ListenerUtil.loopListener.listen("_loopCounter117", ++_loopCounter117);
                    // Get new name
                    name = getResources().getString(R.string.card_n_name, n);
                    // Cycle through all templates checking if new name exists
                    boolean exists = false;
                    if (!ListenerUtil.mutListener.listen(6587)) {
                        {
                            long _loopCounter116 = 0;
                            for (JSONObject template : templates.jsonObjectIterable()) {
                                ListenerUtil.loopListener.listen("_loopCounter116", ++_loopCounter116);
                                if (!ListenerUtil.mutListener.listen(6586)) {
                                    if (name.equals(template.getString("name"))) {
                                        if (!ListenerUtil.mutListener.listen(6585)) {
                                            exists = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6588)) {
                        if (!exists) {
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6589)) {
                        n += 1;
                    }
                }
            }
            return name;
        }
    }
}
