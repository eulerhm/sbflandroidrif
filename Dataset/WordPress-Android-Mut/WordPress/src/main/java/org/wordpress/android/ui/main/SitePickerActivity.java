package org.wordpress.android.ui.main;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged;
import org.wordpress.android.fluxc.store.SiteStore.OnSiteRemoved;
import org.wordpress.android.fluxc.store.StatsStore;
import org.wordpress.android.ui.ActionableEmptyView;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.main.SitePickerAdapter.SiteList;
import org.wordpress.android.ui.main.SitePickerAdapter.SitePickerMode;
import org.wordpress.android.ui.main.SitePickerAdapter.SiteRecord;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.prefs.EmptyViewRecyclerView;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationSource;
import org.wordpress.android.util.AccessibilityUtils;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.DeviceUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.helpers.Debouncer;
import org.wordpress.android.util.helpers.SwipeToRefreshHelper;
import org.wordpress.android.viewmodel.main.SitePickerViewModel;
import org.wordpress.android.viewmodel.main.SitePickerViewModel.Action.ContinueReblogTo;
import org.wordpress.android.viewmodel.main.SitePickerViewModel.Action.NavigateToState;
import org.wordpress.android.widgets.WPDialogSnackbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import static org.wordpress.android.util.WPSwipeToRefreshHelper.buildSwipeToRefreshHelper;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SitePickerActivity extends LocaleAwareActivity implements SitePickerAdapter.OnSiteClickListener, SitePickerAdapter.OnSelectedCountChangedListener, SearchView.OnQueryTextListener {

    public static final String KEY_SITE_LOCAL_ID = "local_id";

    public static final String KEY_SITE_CREATED_BUT_NOT_FETCHED = "key_site_created_but_not_fetched";

    public static final String KEY_SITE_TITLE_TASK_COMPLETED = "key_site_title_task_completed";

    public static final String KEY_SITE_PICKER_MODE = "key_site_picker_mode";

    private static final String KEY_IS_IN_SEARCH_MODE = "is_in_search_mode";

    private static final String KEY_LAST_SEARCH = "last_search";

    private static final String KEY_REFRESHING = "refreshing_sites";

    // Used for preserving selection states after configuration change.
    private static final String KEY_SELECTED_POSITIONS = "selected_positions";

    private static final String KEY_IS_IN_EDIT_MODE = "is_in_edit_mode";

    private static final String KEY_IS_SHOW_MENU_ENABLED = "is_show_menu_enabled";

    private static final String KEY_IS_HIDE_MENU_ENABLED = "is_hide_menu_enabled";

    private static final String ARG_SITE_CREATION_SOURCE = "ARG_SITE_CREATION_SOURCE";

    private static final String SOURCE = "source";

    private static final String TRACK_PROPERTY_STATE = "state";

    private static final String TRACK_PROPERTY_STATE_EDIT = "edit";

    private static final String TRACK_PROPERTY_STATE_DONE = "done";

    private static final String TRACK_PROPERTY_BLOG_ID = "blog_id";

    private static final String TRACK_PROPERTY_VISIBLE = "visible";

    private SitePickerAdapter mAdapter;

    private EmptyViewRecyclerView mRecycleView;

    private SwipeToRefreshHelper mSwipeToRefreshHelper;

    private ActionMode mActionMode;

    private ActionMode mReblogActionMode;

    private MenuItem mMenuEdit;

    private MenuItem mMenuAdd;

    private MenuItem mMenuSearch;

    private SearchView mSearchView;

    private int mCurrentLocalId;

    private SitePickerMode mSitePickerMode;

    private final Debouncer mDebouncer = new Debouncer();

    private SitePickerViewModel mViewModel;

    private HashSet<Integer> mSelectedPositions = new HashSet<>();

    private boolean mIsInEditMode;

    private boolean mShowMenuEnabled = false;

    private boolean mHideMenuEnabled = false;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    StatsStore mStatsStore;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    BuildConfigWrapper mBuildConfigWrapper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(5202)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5203)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(5204)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(SitePickerViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(5205)) {
            setContentView(R.layout.site_picker_activity);
        }
        if (!ListenerUtil.mutListener.listen(5206)) {
            restoreSavedInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(5207)) {
            setupActionBar();
        }
        if (!ListenerUtil.mutListener.listen(5208)) {
            setupRecycleView();
        }
        if (!ListenerUtil.mutListener.listen(5209)) {
            initSwipeToRefreshHelper(findViewById(android.R.id.content));
        }
        if (!ListenerUtil.mutListener.listen(5212)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(5211)) {
                    mSwipeToRefreshHelper.setRefreshing(savedInstanceState.getBoolean(KEY_REFRESHING, false));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5210)) {
                    AnalyticsTracker.track(Stat.SITE_SWITCHER_DISPLAYED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5214)) {
            if (mSitePickerMode.isReblogMode()) {
                if (!ListenerUtil.mutListener.listen(5213)) {
                    mViewModel.getOnActionTriggered().observe(this, unitEvent -> unitEvent.applyIfNotHandled(action -> {
                        switch(action.getActionType()) {
                            case NAVIGATE_TO_STATE:
                                switch(((NavigateToState) action).getNavigateState()) {
                                    case TO_SITE_SELECTED:
                                        mSitePickerMode = SitePickerMode.REBLOG_CONTINUE_MODE;
                                        if (getAdapter().getIsInSearchMode()) {
                                            disableSearchMode();
                                        }
                                        if (mReblogActionMode == null) {
                                            startSupportActionMode(new ReblogActionModeCallback());
                                        }
                                        SiteRecord site = ((NavigateToState) action).getSiteForReblog();
                                        if (site != null) {
                                            mReblogActionMode.setTitle(site.getBlogNameOrHomeURL());
                                        }
                                        break;
                                    case TO_NO_SITE_SELECTED:
                                        mSitePickerMode = SitePickerMode.REBLOG_SELECT_MODE;
                                        getAdapter().clearReblogSelection();
                                        break;
                                }
                                break;
                            case CONTINUE_REBLOG_TO:
                                SiteRecord siteToReblog = ((ContinueReblogTo) action).getSiteForReblog();
                                selectSiteAndFinish(siteToReblog);
                                break;
                            case ASK_FOR_SITE_SELECTION:
                                if (BuildConfig.DEBUG) {
                                    throw new IllegalStateException("SitePickerActivity > Selected site was null while attempting to reblog");
                                } else {
                                    AppLog.e(AppLog.T.READER, "SitePickerActivity > Selected site was null while attempting to reblog");
                                    ToastUtils.showToast(this, R.string.site_picker_ask_site_select);
                                }
                                break;
                        }
                        return null;
                    }));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5216)) {
            // If the picker is already in editing mode from previous configuration, re-enable the editing mode.
            if (mIsInEditMode) {
                if (!ListenerUtil.mutListener.listen(5215)) {
                    startEditingVisibility();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(5217)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(5218)) {
            ActivityId.trackLastActivity(ActivityId.SITE_PICKER);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(5219)) {
            outState.putInt(KEY_SITE_LOCAL_ID, mCurrentLocalId);
        }
        if (!ListenerUtil.mutListener.listen(5220)) {
            outState.putBoolean(KEY_IS_IN_SEARCH_MODE, getAdapter().getIsInSearchMode());
        }
        if (!ListenerUtil.mutListener.listen(5221)) {
            outState.putString(KEY_LAST_SEARCH, getAdapter().getLastSearch());
        }
        if (!ListenerUtil.mutListener.listen(5222)) {
            outState.putBoolean(KEY_REFRESHING, mSwipeToRefreshHelper.isRefreshing());
        }
        if (!ListenerUtil.mutListener.listen(5223)) {
            outState.putSerializable(KEY_SITE_PICKER_MODE, mSitePickerMode);
        }
        if (!ListenerUtil.mutListener.listen(5224)) {
            outState.putSerializable(KEY_SELECTED_POSITIONS, getAdapter().getSelectedPositions());
        }
        if (!ListenerUtil.mutListener.listen(5225)) {
            outState.putBoolean(KEY_IS_IN_EDIT_MODE, mIsInEditMode);
        }
        if (!ListenerUtil.mutListener.listen(5226)) {
            outState.putBoolean(KEY_IS_SHOW_MENU_ENABLED, mShowMenuEnabled);
        }
        if (!ListenerUtil.mutListener.listen(5227)) {
            outState.putBoolean(KEY_IS_HIDE_MENU_ENABLED, mHideMenuEnabled);
        }
        if (!ListenerUtil.mutListener.listen(5228)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(5229)) {
            super.onCreateOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(5230)) {
            getMenuInflater().inflate(R.menu.site_picker, menu);
        }
        if (!ListenerUtil.mutListener.listen(5231)) {
            mMenuSearch = menu.findItem(R.id.menu_search);
        }
        if (!ListenerUtil.mutListener.listen(5232)) {
            mMenuEdit = menu.findItem(R.id.menu_edit);
        }
        if (!ListenerUtil.mutListener.listen(5233)) {
            mMenuAdd = menu.findItem(R.id.menu_add);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(5234)) {
            super.onPrepareOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(5235)) {
            updateMenuItemVisibility();
        }
        if (!ListenerUtil.mutListener.listen(5236)) {
            setupSearchView();
        }
        return true;
    }

    private void updateMenuItemVisibility() {
        if (!ListenerUtil.mutListener.listen(5239)) {
            if ((ListenerUtil.mutListener.listen(5238) ? ((ListenerUtil.mutListener.listen(5237) ? (mMenuAdd == null && mMenuEdit == null) : (mMenuAdd == null || mMenuEdit == null)) && mMenuSearch == null) : ((ListenerUtil.mutListener.listen(5237) ? (mMenuAdd == null && mMenuEdit == null) : (mMenuAdd == null || mMenuEdit == null)) || mMenuSearch == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5251)) {
            if ((ListenerUtil.mutListener.listen(5241) ? ((ListenerUtil.mutListener.listen(5240) ? (getAdapter().getIsInSearchMode() && mSitePickerMode.isReblogMode()) : (getAdapter().getIsInSearchMode() || mSitePickerMode.isReblogMode())) && mSitePickerMode.isBloggingPromptsMode()) : ((ListenerUtil.mutListener.listen(5240) ? (getAdapter().getIsInSearchMode() && mSitePickerMode.isReblogMode()) : (getAdapter().getIsInSearchMode() || mSitePickerMode.isReblogMode())) || mSitePickerMode.isBloggingPromptsMode()))) {
                if (!ListenerUtil.mutListener.listen(5249)) {
                    mMenuEdit.setVisible(false);
                }
                if (!ListenerUtil.mutListener.listen(5250)) {
                    mMenuAdd.setVisible(false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5247)) {
                    // don't allow editing visibility unless there are multiple wp.com and jetpack sites
                    mMenuEdit.setVisible((ListenerUtil.mutListener.listen(5246) ? (mSiteStore.getSitesAccessedViaWPComRestCount() >= 1) : (ListenerUtil.mutListener.listen(5245) ? (mSiteStore.getSitesAccessedViaWPComRestCount() <= 1) : (ListenerUtil.mutListener.listen(5244) ? (mSiteStore.getSitesAccessedViaWPComRestCount() < 1) : (ListenerUtil.mutListener.listen(5243) ? (mSiteStore.getSitesAccessedViaWPComRestCount() != 1) : (ListenerUtil.mutListener.listen(5242) ? (mSiteStore.getSitesAccessedViaWPComRestCount() == 1) : (mSiteStore.getSitesAccessedViaWPComRestCount() > 1)))))));
                }
                if (!ListenerUtil.mutListener.listen(5248)) {
                    mMenuAdd.setVisible(mBuildConfigWrapper.isSiteCreationEnabled());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5257)) {
            // no point showing search if there aren't multiple blogs
            mMenuSearch.setVisible((ListenerUtil.mutListener.listen(5256) ? (mSiteStore.getSitesCount() >= 1) : (ListenerUtil.mutListener.listen(5255) ? (mSiteStore.getSitesCount() <= 1) : (ListenerUtil.mutListener.listen(5254) ? (mSiteStore.getSitesCount() < 1) : (ListenerUtil.mutListener.listen(5253) ? (mSiteStore.getSitesCount() != 1) : (ListenerUtil.mutListener.listen(5252) ? (mSiteStore.getSitesCount() == 1) : (mSiteStore.getSitesCount() > 1)))))));
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(5265)) {
            if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(5263)) {
                    AnalyticsTracker.track(Stat.SITE_SWITCHER_DISMISSED);
                }
                if (!ListenerUtil.mutListener.listen(5264)) {
                    onBackPressed();
                }
                return true;
            } else if (itemId == R.id.menu_edit) {
                if (!ListenerUtil.mutListener.listen(5261)) {
                    AnalyticsTracker.track(Stat.SITE_SWITCHER_TOGGLED_EDIT_TAPPED, Collections.singletonMap(TRACK_PROPERTY_STATE, TRACK_PROPERTY_STATE_EDIT));
                }
                if (!ListenerUtil.mutListener.listen(5262)) {
                    startEditingVisibility();
                }
                return true;
            } else if (itemId == R.id.menu_add) {
                if (!ListenerUtil.mutListener.listen(5259)) {
                    AnalyticsTracker.track(Stat.SITE_SWITCHER_ADD_SITE_TAPPED);
                }
                if (!ListenerUtil.mutListener.listen(5260)) {
                    addSite(this, mAccountStore.hasAccessToken(), SiteCreationSource.MY_SITE);
                }
                return true;
            } else if (itemId == R.id.continue_flow) {
                if (!ListenerUtil.mutListener.listen(5258)) {
                    mViewModel.onContinueFlowSelected();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(5266)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(5276)) {
            switch(requestCode) {
                case RequestCodes.ADD_ACCOUNT:
                case RequestCodes.CREATE_SITE:
                    if (!ListenerUtil.mutListener.listen(5275)) {
                        if (resultCode == RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(5267)) {
                                debounceLoadSites();
                            }
                            if (!ListenerUtil.mutListener.listen(5269)) {
                                if (data == null) {
                                    if (!ListenerUtil.mutListener.listen(5268)) {
                                        data = new Intent();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5274)) {
                                if (data.getBooleanExtra(KEY_SITE_CREATED_BUT_NOT_FETCHED, false)) {
                                    if (!ListenerUtil.mutListener.listen(5273)) {
                                        showSiteCreatedButNotFetchedSnackbar();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(5270)) {
                                        data.putExtra(WPMainActivity.ARG_CREATE_SITE, RequestCodes.CREATE_SITE);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5271)) {
                                        setResult(resultCode, data);
                                    }
                                    if (!ListenerUtil.mutListener.listen(5272)) {
                                        finish();
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(5279)) {
            // Enable the block editor on sites created on mobile
            switch(requestCode) {
                case RequestCodes.CREATE_SITE:
                    if (!ListenerUtil.mutListener.listen(5278)) {
                        if (data != null) {
                            int newSiteLocalID = data.getIntExtra(SitePickerActivity.KEY_SITE_LOCAL_ID, SelectedSiteRepository.UNAVAILABLE);
                            if (!ListenerUtil.mutListener.listen(5277)) {
                                SiteUtils.enableBlockEditorOnSiteCreation(mDispatcher, mSiteStore, newSiteLocalID);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(5280)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(5281)) {
            mDebouncer.shutdown();
        }
        if (!ListenerUtil.mutListener.listen(5282)) {
            super.onStop();
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(5283)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(5284)) {
            mDispatcher.register(this);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteRemoved(OnSiteRemoved event) {
        if (!ListenerUtil.mutListener.listen(5288)) {
            if (!event.isError()) {
                if (!ListenerUtil.mutListener.listen(5287)) {
                    debounceLoadSites();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5285)) {
                    // shouldn't happen
                    AppLog.e(AppLog.T.DB, "Encountered unexpected error while attempting to remove site: " + event.error);
                }
                if (!ListenerUtil.mutListener.listen(5286)) {
                    ToastUtils.showToast(this, R.string.site_picker_remove_site_error);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSiteChanged(OnSiteChanged event) {
        if (!ListenerUtil.mutListener.listen(5290)) {
            if (mSwipeToRefreshHelper.isRefreshing()) {
                if (!ListenerUtil.mutListener.listen(5289)) {
                    mSwipeToRefreshHelper.setRefreshing(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5291)) {
            debounceLoadSites();
        }
    }

    private void debounceLoadSites() {
        if (!ListenerUtil.mutListener.listen(5292)) {
            mDebouncer.debounce(Void.class, () -> {
                if (!isFinishing()) {
                    getAdapter().loadSites();
                }
            }, 200, TimeUnit.MILLISECONDS);
        }
    }

    private void initSwipeToRefreshHelper(View view) {
        if (!ListenerUtil.mutListener.listen(5293)) {
            if (view == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5294)) {
            mSwipeToRefreshHelper = buildSwipeToRefreshHelper(view.findViewById(R.id.ptr_layout), () -> {
                if (isFinishing()) {
                    return;
                }
                if (!NetworkUtils.checkConnection(SitePickerActivity.this) || !mAccountStore.hasAccessToken()) {
                    mSwipeToRefreshHelper.setRefreshing(false);
                    return;
                }
                mDispatcher.dispatch(SiteActionBuilder.newFetchSitesAction(SiteUtils.getFetchSitesPayload()));
            });
        }
    }

    private void setupRecycleView() {
        if (!ListenerUtil.mutListener.listen(5295)) {
            mRecycleView = findViewById(R.id.recycler_view);
        }
        if (!ListenerUtil.mutListener.listen(5296)) {
            mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(5297)) {
            mRecycleView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        }
        if (!ListenerUtil.mutListener.listen(5298)) {
            mRecycleView.setItemAnimator(mSitePickerMode.isReblogMode() ? new DefaultItemAnimator() : null);
        }
        if (!ListenerUtil.mutListener.listen(5299)) {
            mRecycleView.setAdapter(getAdapter());
        }
        ActionableEmptyView actionableEmptyView = findViewById(R.id.actionable_empty_view);
        if (!ListenerUtil.mutListener.listen(5300)) {
            actionableEmptyView.updateLayoutForSearch(true, 0);
        }
        if (!ListenerUtil.mutListener.listen(5301)) {
            mRecycleView.setEmptyView(actionableEmptyView);
        }
    }

    private void restoreSavedInstanceState(Bundle savedInstanceState) {
        boolean isInSearchMode = false;
        String lastSearch = "";
        if (!ListenerUtil.mutListener.listen(5312)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(5304)) {
                    mCurrentLocalId = savedInstanceState.getInt(KEY_SITE_LOCAL_ID);
                }
                if (!ListenerUtil.mutListener.listen(5305)) {
                    isInSearchMode = savedInstanceState.getBoolean(KEY_IS_IN_SEARCH_MODE);
                }
                if (!ListenerUtil.mutListener.listen(5306)) {
                    lastSearch = savedInstanceState.getString(KEY_LAST_SEARCH);
                }
                if (!ListenerUtil.mutListener.listen(5307)) {
                    mSitePickerMode = (SitePickerMode) savedInstanceState.getSerializable(KEY_SITE_PICKER_MODE);
                }
                if (!ListenerUtil.mutListener.listen(5308)) {
                    mSelectedPositions = (HashSet<Integer>) savedInstanceState.getSerializable(KEY_SELECTED_POSITIONS);
                }
                if (!ListenerUtil.mutListener.listen(5309)) {
                    mIsInEditMode = savedInstanceState.getBoolean(KEY_IS_IN_EDIT_MODE);
                }
                if (!ListenerUtil.mutListener.listen(5310)) {
                    mShowMenuEnabled = savedInstanceState.getBoolean(KEY_IS_SHOW_MENU_ENABLED);
                }
                if (!ListenerUtil.mutListener.listen(5311)) {
                    mHideMenuEnabled = savedInstanceState.getBoolean(KEY_IS_HIDE_MENU_ENABLED);
                }
            } else if (getIntent() != null) {
                if (!ListenerUtil.mutListener.listen(5302)) {
                    mCurrentLocalId = getIntent().getIntExtra(KEY_SITE_LOCAL_ID, SelectedSiteRepository.UNAVAILABLE);
                }
                if (!ListenerUtil.mutListener.listen(5303)) {
                    mSitePickerMode = (SitePickerMode) getIntent().getSerializableExtra(KEY_SITE_PICKER_MODE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5313)) {
            setNewAdapter(lastSearch, isInSearchMode);
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(5314)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(5321)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(5315)) {
                    actionBar.setHomeButtonEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(5316)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(5317)) {
                    actionBar.setTitle(R.string.site_picker_title);
                }
                if (!ListenerUtil.mutListener.listen(5320)) {
                    if ((ListenerUtil.mutListener.listen(5318) ? (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE || mReblogActionMode == null) : (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE && mReblogActionMode == null))) {
                        if (!ListenerUtil.mutListener.listen(5319)) {
                            mViewModel.onRefreshReblogActionMode();
                        }
                    }
                }
            }
        }
    }

    private void setIsInSearchModeAndSetNewAdapter(boolean isInSearchMode) {
        String lastSearch = getAdapter().getLastSearch();
        if (!ListenerUtil.mutListener.listen(5322)) {
            setNewAdapter(lastSearch, isInSearchMode);
        }
    }

    private SitePickerAdapter getAdapter() {
        if (!ListenerUtil.mutListener.listen(5324)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(5323)) {
                    setNewAdapter("", false);
                }
            }
        }
        return mAdapter;
    }

    private void setNewAdapter(String lastSearch, boolean isInSearchMode) {
        if (!ListenerUtil.mutListener.listen(5339)) {
            mAdapter = new SitePickerAdapter(this, R.layout.site_picker_listitem, mCurrentLocalId, lastSearch, isInSearchMode, new SitePickerAdapter.OnDataLoadedListener() {

                @Override
                public void onBeforeLoad(boolean isEmpty) {
                    if (!ListenerUtil.mutListener.listen(5326)) {
                        if (isEmpty) {
                            if (!ListenerUtil.mutListener.listen(5325)) {
                                showProgress(true);
                            }
                        }
                    }
                }

                @Override
                public void onAfterLoad() {
                    if (!ListenerUtil.mutListener.listen(5327)) {
                        showProgress(false);
                    }
                    if (!ListenerUtil.mutListener.listen(5338)) {
                        if ((ListenerUtil.mutListener.listen(5328) ? (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE || !isInSearchMode) : (mSitePickerMode == SitePickerMode.REBLOG_CONTINUE_MODE && !isInSearchMode))) {
                            if (!ListenerUtil.mutListener.listen(5329)) {
                                mAdapter.findAndSelect(mCurrentLocalId);
                            }
                            int scrollPos = mAdapter.getItemPosByLocalId(mCurrentLocalId);
                            if (!ListenerUtil.mutListener.listen(5337)) {
                                if ((ListenerUtil.mutListener.listen(5335) ? ((ListenerUtil.mutListener.listen(5334) ? (scrollPos >= -1) : (ListenerUtil.mutListener.listen(5333) ? (scrollPos <= -1) : (ListenerUtil.mutListener.listen(5332) ? (scrollPos < -1) : (ListenerUtil.mutListener.listen(5331) ? (scrollPos != -1) : (ListenerUtil.mutListener.listen(5330) ? (scrollPos == -1) : (scrollPos > -1)))))) || mRecycleView != null) : ((ListenerUtil.mutListener.listen(5334) ? (scrollPos >= -1) : (ListenerUtil.mutListener.listen(5333) ? (scrollPos <= -1) : (ListenerUtil.mutListener.listen(5332) ? (scrollPos < -1) : (ListenerUtil.mutListener.listen(5331) ? (scrollPos != -1) : (ListenerUtil.mutListener.listen(5330) ? (scrollPos == -1) : (scrollPos > -1)))))) && mRecycleView != null))) {
                                    if (!ListenerUtil.mutListener.listen(5336)) {
                                        mRecycleView.scrollToPosition(scrollPos);
                                    }
                                }
                            }
                        }
                    }
                }
            }, mSitePickerMode, mIsInEditMode);
        }
        if (!ListenerUtil.mutListener.listen(5340)) {
            mAdapter.setOnSiteClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(5341)) {
            mAdapter.setOnSelectedCountChangedListener(this);
        }
    }

    private void saveSiteVisibility(SiteRecord siteRecord) {
        Set<SiteRecord> siteRecords = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(5342)) {
            siteRecords.add(siteRecord);
        }
        if (!ListenerUtil.mutListener.listen(5343)) {
            saveSitesVisibility(siteRecords);
        }
    }

    private void saveSitesVisibility(Set<SiteRecord> changeSet) {
        boolean skippedCurrentSite = false;
        String currentSiteName = null;
        SiteList hiddenSites = getAdapter().getHiddenSites();
        List<SiteModel> siteList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(5354)) {
            {
                long _loopCounter127 = 0;
                for (SiteRecord siteRecord : changeSet) {
                    ListenerUtil.loopListener.listen("_loopCounter127", ++_loopCounter127);
                    SiteModel siteModel = mSiteStore.getSiteByLocalId(siteRecord.getLocalId());
                    if (!ListenerUtil.mutListener.listen(5350)) {
                        if (hiddenSites.contains(siteRecord)) {
                            if (!ListenerUtil.mutListener.listen(5347)) {
                                if (siteRecord.getLocalId() == mCurrentLocalId) {
                                    if (!ListenerUtil.mutListener.listen(5345)) {
                                        skippedCurrentSite = true;
                                    }
                                    if (!ListenerUtil.mutListener.listen(5346)) {
                                        currentSiteName = siteRecord.getBlogNameOrHomeURL();
                                    }
                                    continue;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5348)) {
                                siteModel.setIsVisible(false);
                            }
                            if (!ListenerUtil.mutListener.listen(5349)) {
                                // Remove stats data for hidden sites
                                mStatsStore.deleteSiteData(siteModel);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(5344)) {
                                siteModel.setIsVisible(true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5351)) {
                        // Save the site
                        mDispatcher.dispatch(SiteActionBuilder.newUpdateSiteAction(siteModel));
                    }
                    if (!ListenerUtil.mutListener.listen(5352)) {
                        siteList.add(siteModel);
                    }
                    if (!ListenerUtil.mutListener.listen(5353)) {
                        trackVisibility(Long.toString(siteModel.getSiteId()), siteModel.isVisible());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5355)) {
            updateVisibilityOfSitesOnRemote(siteList);
        }
        if (!ListenerUtil.mutListener.listen(5357)) {
            // let user know the current site wasn't hidden
            if (skippedCurrentSite) {
                String cantHideCurrentSite = getString(R.string.site_picker_cant_hide_current_site);
                if (!ListenerUtil.mutListener.listen(5356)) {
                    ToastUtils.showToast(this, String.format(cantHideCurrentSite, currentSiteName), ToastUtils.Duration.LONG);
                }
            }
        }
    }

    private void updateVisibilityOfSitesOnRemote(List<SiteModel> siteList) {
        // Example json format for the request: {"sites":{"100001":{"visible":false}}}
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject sites = new JSONObject();
            if (!ListenerUtil.mutListener.listen(5361)) {
                {
                    long _loopCounter128 = 0;
                    for (SiteModel siteModel : siteList) {
                        ListenerUtil.loopListener.listen("_loopCounter128", ++_loopCounter128);
                        JSONObject visible = new JSONObject();
                        if (!ListenerUtil.mutListener.listen(5359)) {
                            visible.put("visible", siteModel.isVisible());
                        }
                        if (!ListenerUtil.mutListener.listen(5360)) {
                            sites.put(Long.toString(siteModel.getSiteId()), visible);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5362)) {
                jsonObject.put("sites", sites);
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(5358)) {
                AppLog.e(AppLog.T.API, "Could not build me/sites json object");
            }
        }
        if (!ListenerUtil.mutListener.listen(5363)) {
            if (jsonObject.length() == 0) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(5364)) {
            WordPress.getRestClientUtilsV1_1().post("me/sites", jsonObject, null, response -> AppLog.v(AppLog.T.API, "Site visibility successfully updated"), volleyError -> AppLog.e(AppLog.T.API, "An error occurred while updating site visibility: " + volleyError));
        }
    }

    private void updateActionModeTitle() {
        if (!ListenerUtil.mutListener.listen(5366)) {
            if (mActionMode != null) {
                int numSelected = getAdapter().getNumSelected();
                String cabSelected = getString(R.string.cab_selected);
                if (!ListenerUtil.mutListener.listen(5365)) {
                    mActionMode.setTitle(String.format(cabSelected, numSelected));
                }
            }
        }
    }

    private void setupSearchView() {
        if (!ListenerUtil.mutListener.listen(5367)) {
            mSearchView = (SearchView) mMenuSearch.getActionView();
        }
        if (!ListenerUtil.mutListener.listen(5368)) {
            mSearchView.setMaxWidth(Integer.MAX_VALUE);
        }
        if (!ListenerUtil.mutListener.listen(5376)) {
            mMenuSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(5373)) {
                        if (!getAdapter().getIsInSearchMode()) {
                            if (!ListenerUtil.mutListener.listen(5369)) {
                                enableSearchMode();
                            }
                            if (!ListenerUtil.mutListener.listen(5370)) {
                                mMenuEdit.setVisible(false);
                            }
                            if (!ListenerUtil.mutListener.listen(5371)) {
                                mMenuAdd.setVisible(false);
                            }
                            if (!ListenerUtil.mutListener.listen(5372)) {
                                mSearchView.setOnQueryTextListener(SitePickerActivity.this);
                            }
                        }
                    }
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(5374)) {
                        disableSearchMode();
                    }
                    if (!ListenerUtil.mutListener.listen(5375)) {
                        mSearchView.setOnQueryTextListener(null);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(5377)) {
            setQueryIfInSearch();
        }
    }

    private void setQueryIfInSearch() {
        if (!ListenerUtil.mutListener.listen(5381)) {
            if (getAdapter().getIsInSearchMode()) {
                if (!ListenerUtil.mutListener.listen(5378)) {
                    mMenuSearch.expandActionView();
                }
                if (!ListenerUtil.mutListener.listen(5379)) {
                    mSearchView.setOnQueryTextListener(SitePickerActivity.this);
                }
                if (!ListenerUtil.mutListener.listen(5380)) {
                    mSearchView.setQuery(getAdapter().getLastSearch(), true);
                }
            }
        }
    }

    private void enableSearchMode() {
        if (!ListenerUtil.mutListener.listen(5382)) {
            setIsInSearchModeAndSetNewAdapter(true);
        }
        if (!ListenerUtil.mutListener.listen(5383)) {
            mRecycleView.swapAdapter(getAdapter(), true);
        }
    }

    private void disableSearchMode() {
        if (!ListenerUtil.mutListener.listen(5384)) {
            hideSoftKeyboard();
        }
        if (!ListenerUtil.mutListener.listen(5385)) {
            setIsInSearchModeAndSetNewAdapter(false);
        }
        if (!ListenerUtil.mutListener.listen(5386)) {
            mRecycleView.swapAdapter(getAdapter(), true);
        }
        if (!ListenerUtil.mutListener.listen(5387)) {
            invalidateOptionsMenu();
        }
    }

    private void hideSoftKeyboard() {
        if (!ListenerUtil.mutListener.listen(5389)) {
            if (!DeviceUtils.getInstance().hasHardwareKeyboard(this)) {
                if (!ListenerUtil.mutListener.listen(5388)) {
                    ActivityUtils.hideKeyboardForced(mSearchView);
                }
            }
        }
    }

    @Override
    public void onSelectedCountChanged(int numSelected) {
        if (!ListenerUtil.mutListener.listen(5404)) {
            if (mActionMode != null) {
                if (!ListenerUtil.mutListener.listen(5390)) {
                    updateActionModeTitle();
                }
                if (!ListenerUtil.mutListener.listen(5396)) {
                    mShowMenuEnabled = (ListenerUtil.mutListener.listen(5395) ? (getAdapter().getNumHiddenSelected() >= 0) : (ListenerUtil.mutListener.listen(5394) ? (getAdapter().getNumHiddenSelected() <= 0) : (ListenerUtil.mutListener.listen(5393) ? (getAdapter().getNumHiddenSelected() < 0) : (ListenerUtil.mutListener.listen(5392) ? (getAdapter().getNumHiddenSelected() != 0) : (ListenerUtil.mutListener.listen(5391) ? (getAdapter().getNumHiddenSelected() == 0) : (getAdapter().getNumHiddenSelected() > 0))))));
                }
                if (!ListenerUtil.mutListener.listen(5402)) {
                    mHideMenuEnabled = (ListenerUtil.mutListener.listen(5401) ? (getAdapter().getNumVisibleSelected() >= 0) : (ListenerUtil.mutListener.listen(5400) ? (getAdapter().getNumVisibleSelected() <= 0) : (ListenerUtil.mutListener.listen(5399) ? (getAdapter().getNumVisibleSelected() < 0) : (ListenerUtil.mutListener.listen(5398) ? (getAdapter().getNumVisibleSelected() != 0) : (ListenerUtil.mutListener.listen(5397) ? (getAdapter().getNumVisibleSelected() == 0) : (getAdapter().getNumVisibleSelected() > 0))))));
                }
                if (!ListenerUtil.mutListener.listen(5403)) {
                    mActionMode.invalidate();
                }
            }
        }
    }

    @Override
    public boolean onSiteLongClick(final SiteRecord siteRecord) {
        final SiteModel site = mSiteStore.getSiteByLocalId(siteRecord.getLocalId());
        if (!ListenerUtil.mutListener.listen(5405)) {
            if (site == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(5409)) {
            if (site.isUsingWpComRestApi()) {
                if (!ListenerUtil.mutListener.listen(5407)) {
                    if (mActionMode != null) {
                        return false;
                    }
                }
                if (!ListenerUtil.mutListener.listen(5408)) {
                    startEditingVisibility();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5406)) {
                    showRemoveSelfHostedSiteDialog(site);
                }
            }
        }
        return true;
    }

    @Override
    public void onSiteClick(SiteRecord siteRecord) {
        if (!ListenerUtil.mutListener.listen(5413)) {
            if (mSitePickerMode.isReblogMode()) {
                if (!ListenerUtil.mutListener.listen(5411)) {
                    mCurrentLocalId = siteRecord.getLocalId();
                }
                if (!ListenerUtil.mutListener.listen(5412)) {
                    mViewModel.onSiteForReblogSelected(siteRecord);
                }
            } else if (mActionMode == null) {
                if (!ListenerUtil.mutListener.listen(5410)) {
                    selectSiteAndFinish(siteRecord);
                }
            }
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (!ListenerUtil.mutListener.listen(5414)) {
            hideSoftKeyboard();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (!ListenerUtil.mutListener.listen(5418)) {
            if (getAdapter().getIsInSearchMode()) {
                if (!ListenerUtil.mutListener.listen(5415)) {
                    AnalyticsTracker.track(Stat.SITE_SWITCHER_SEARCH_PERFORMED);
                }
                if (!ListenerUtil.mutListener.listen(5416)) {
                    getAdapter().setLastSearch(s);
                }
                if (!ListenerUtil.mutListener.listen(5417)) {
                    getAdapter().searchSites(s);
                }
            }
        }
        return true;
    }

    public void showProgress(boolean show) {
        if (!ListenerUtil.mutListener.listen(5419)) {
            findViewById(R.id.progress).setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    private void selectSiteAndFinish(SiteRecord siteRecord) {
        if (!ListenerUtil.mutListener.listen(5420)) {
            hideSoftKeyboard();
        }
        if (!ListenerUtil.mutListener.listen(5421)) {
            AppPrefs.addRecentlyPickedSiteId(siteRecord.getLocalId());
        }
        if (!ListenerUtil.mutListener.listen(5422)) {
            setResult(RESULT_OK, new Intent().putExtra(KEY_SITE_LOCAL_ID, siteRecord.getLocalId()));
        }
        if (!ListenerUtil.mutListener.listen(5425)) {
            // If the site is hidden, make sure to make it visible
            if (siteRecord.isHidden()) {
                if (!ListenerUtil.mutListener.listen(5423)) {
                    siteRecord.setHidden(false);
                }
                if (!ListenerUtil.mutListener.listen(5424)) {
                    saveSiteVisibility(siteRecord);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5426)) {
            finish();
        }
    }

    private final class ReblogActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(5427)) {
                mReblogActionMode = mode;
            }
            if (!ListenerUtil.mutListener.listen(5428)) {
                mode.getMenuInflater().inflate(R.menu.site_picker_reblog_action_mode, menu);
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int itemId = item.getItemId();
            if (!ListenerUtil.mutListener.listen(5430)) {
                if (itemId == R.id.continue_flow) {
                    if (!ListenerUtil.mutListener.listen(5429)) {
                        mViewModel.onContinueFlowSelected();
                    }
                }
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (!ListenerUtil.mutListener.listen(5431)) {
                mViewModel.onReblogActionBackSelected();
            }
            if (!ListenerUtil.mutListener.listen(5432)) {
                mReblogActionMode = null;
            }
        }
    }

    private final class ActionModeCallback implements ActionMode.Callback {

        private boolean mHasChanges;

        private Set<SiteRecord> mChangeSet;

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            if (!ListenerUtil.mutListener.listen(5433)) {
                mActionMode = actionMode;
            }
            if (!ListenerUtil.mutListener.listen(5434)) {
                mHasChanges = false;
            }
            if (!ListenerUtil.mutListener.listen(5435)) {
                mChangeSet = new HashSet<>();
            }
            if (!ListenerUtil.mutListener.listen(5436)) {
                updateActionModeTitle();
            }
            if (!ListenerUtil.mutListener.listen(5437)) {
                actionMode.getMenuInflater().inflate(R.menu.site_picker_action_mode, menu);
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            MenuItem mnuShow = menu.findItem(R.id.menu_show);
            if (!ListenerUtil.mutListener.listen(5438)) {
                mnuShow.setEnabled(mShowMenuEnabled);
            }
            MenuItem mnuHide = menu.findItem(R.id.menu_hide);
            if (!ListenerUtil.mutListener.listen(5439)) {
                mnuHide.setEnabled(mHideMenuEnabled);
            }
            MenuItem mnuSelectAll = menu.findItem(R.id.menu_select_all);
            if (!ListenerUtil.mutListener.listen(5440)) {
                mnuSelectAll.setEnabled(getAdapter().getNumSelected() != getAdapter().getItemCount());
            }
            MenuItem mnuDeselectAll = menu.findItem(R.id.menu_deselect_all);
            if (!ListenerUtil.mutListener.listen(5446)) {
                mnuDeselectAll.setEnabled((ListenerUtil.mutListener.listen(5445) ? (getAdapter().getNumSelected() >= 0) : (ListenerUtil.mutListener.listen(5444) ? (getAdapter().getNumSelected() <= 0) : (ListenerUtil.mutListener.listen(5443) ? (getAdapter().getNumSelected() < 0) : (ListenerUtil.mutListener.listen(5442) ? (getAdapter().getNumSelected() != 0) : (ListenerUtil.mutListener.listen(5441) ? (getAdapter().getNumSelected() == 0) : (getAdapter().getNumSelected() > 0)))))));
            }
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (!ListenerUtil.mutListener.listen(5455)) {
                if (itemId == R.id.menu_show) {
                    Set<SiteRecord> changeSet = getAdapter().setVisibilityForSelectedSites(true);
                    if (!ListenerUtil.mutListener.listen(5452)) {
                        mChangeSet.addAll(changeSet);
                    }
                    if (!ListenerUtil.mutListener.listen(5453)) {
                        mHasChanges = true;
                    }
                    if (!ListenerUtil.mutListener.listen(5454)) {
                        mActionMode.finish();
                    }
                } else if (itemId == R.id.menu_hide) {
                    Set<SiteRecord> changeSet = getAdapter().setVisibilityForSelectedSites(false);
                    if (!ListenerUtil.mutListener.listen(5449)) {
                        mChangeSet.addAll(changeSet);
                    }
                    if (!ListenerUtil.mutListener.listen(5450)) {
                        mHasChanges = true;
                    }
                    if (!ListenerUtil.mutListener.listen(5451)) {
                        mActionMode.finish();
                    }
                } else if (itemId == R.id.menu_select_all) {
                    if (!ListenerUtil.mutListener.listen(5448)) {
                        getAdapter().selectAll();
                    }
                } else if (itemId == R.id.menu_deselect_all) {
                    if (!ListenerUtil.mutListener.listen(5447)) {
                        getAdapter().deselectAll();
                    }
                }
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            if (!ListenerUtil.mutListener.listen(5457)) {
                if (mHasChanges) {
                    if (!ListenerUtil.mutListener.listen(5456)) {
                        saveSitesVisibility(mChangeSet);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(5458)) {
                AnalyticsTracker.track(Stat.SITE_SWITCHER_TOGGLED_EDIT_TAPPED, Collections.singletonMap(TRACK_PROPERTY_STATE, TRACK_PROPERTY_STATE_DONE));
            }
            if (!ListenerUtil.mutListener.listen(5459)) {
                getAdapter().setEnableEditMode(false, mSelectedPositions);
            }
            if (!ListenerUtil.mutListener.listen(5460)) {
                mActionMode = null;
            }
            if (!ListenerUtil.mutListener.listen(5461)) {
                mIsInEditMode = false;
            }
            if (!ListenerUtil.mutListener.listen(5462)) {
                mSelectedPositions.clear();
            }
        }
    }

    public static void addSite(Activity activity, boolean hasAccessToken, SiteCreationSource source) {
        if (!ListenerUtil.mutListener.listen(5467)) {
            if (hasAccessToken) {
                if (!ListenerUtil.mutListener.listen(5466)) {
                    if (!BuildConfig.ENABLE_ADD_SELF_HOSTED_SITE) {
                        if (!ListenerUtil.mutListener.listen(5465)) {
                            ActivityLauncher.newBlogForResult(activity, source);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5464)) {
                            // create a new wp.com blog or add a self-hosted one
                            showAddSiteDialog(activity, source);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(5463)) {
                    // user doesn't have an access token, so simply enable adding self-hosted
                    ActivityLauncher.addSelfHostedSiteForResult(activity);
                }
            }
        }
    }

    private static void showAddSiteDialog(Activity activity, SiteCreationSource source) {
        DialogFragment dialog = new AddSiteDialog();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(5468)) {
            args.putString(ARG_SITE_CREATION_SOURCE, source.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(5469)) {
            dialog.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(5470)) {
            dialog.show(activity.getFragmentManager(), AddSiteDialog.ADD_SITE_DIALOG_TAG);
        }
    }

    /*
     * dialog which appears after user taps "Add site" - enables choosing whether to create
     * a new wp.com blog or add an existing self-hosted one
     */
    public static class AddSiteDialog extends DialogFragment {

        static final String ADD_SITE_DIALOG_TAG = "add_site_dialog";

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            SiteCreationSource source = SiteCreationSource.fromString(getArguments().getString(ARG_SITE_CREATION_SOURCE));
            CharSequence[] items = { getString(R.string.site_picker_create_wpcom), getString(R.string.site_picker_add_self_hosted) };
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
            if (!ListenerUtil.mutListener.listen(5471)) {
                builder.setTitle(R.string.site_picker_add_site);
            }
            if (!ListenerUtil.mutListener.listen(5472)) {
                builder.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.add_new_site_dialog_item, R.id.text, items), (dialog, which) -> {
                    if (which == 0) {
                        ActivityLauncher.newBlogForResult(getActivity(), source);
                    } else {
                        ActivityLauncher.addSelfHostedSiteForResult(getActivity());
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(5473)) {
                AnalyticsTracker.track(Stat.ADD_SITE_ALERT_DISPLAYED, Collections.singletonMap(SOURCE, source.getLabel()));
            }
            return builder.create();
        }
    }

    private void startEditingVisibility() {
        if (!ListenerUtil.mutListener.listen(5474)) {
            mRecycleView.setItemAnimator(new DefaultItemAnimator());
        }
        if (!ListenerUtil.mutListener.listen(5475)) {
            getAdapter().setEnableEditMode(true, mSelectedPositions);
        }
        if (!ListenerUtil.mutListener.listen(5476)) {
            startSupportActionMode(new ActionModeCallback());
        }
        if (!ListenerUtil.mutListener.listen(5477)) {
            mIsInEditMode = true;
        }
    }

    private void showRemoveSelfHostedSiteDialog(@NonNull final SiteModel site) {
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
        if (!ListenerUtil.mutListener.listen(5478)) {
            dialogBuilder.setTitle(getResources().getText(R.string.remove_account));
        }
        if (!ListenerUtil.mutListener.listen(5479)) {
            dialogBuilder.setMessage(getResources().getText(R.string.sure_to_remove_account));
        }
        if (!ListenerUtil.mutListener.listen(5480)) {
            dialogBuilder.setPositiveButton(getResources().getText(R.string.yes), (dialog, whichButton) -> mDispatcher.dispatch(SiteActionBuilder.newRemoveSiteAction(site)));
        }
        if (!ListenerUtil.mutListener.listen(5481)) {
            dialogBuilder.setNegativeButton(getResources().getText(R.string.no), null);
        }
        if (!ListenerUtil.mutListener.listen(5482)) {
            dialogBuilder.setCancelable(false);
        }
        if (!ListenerUtil.mutListener.listen(5483)) {
            dialogBuilder.create().show();
        }
    }

    private void showSiteCreatedButNotFetchedSnackbar() {
        int duration = AccessibilityUtils.getSnackbarDuration(this, getResources().getInteger(R.integer.site_creation_snackbar_duration));
        String message = getString(R.string.site_created_but_not_fetched_snackbar_message);
        if (!ListenerUtil.mutListener.listen(5484)) {
            WPDialogSnackbar.make(findViewById(R.id.coordinatorLayout), message, duration).show();
        }
    }

    private void trackVisibility(String blogId, boolean isVisible) {
        Map<String, String> props = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(5485)) {
            props.put(TRACK_PROPERTY_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(5486)) {
            props.put(TRACK_PROPERTY_VISIBLE, isVisible ? "1" : "0");
        }
        if (!ListenerUtil.mutListener.listen(5487)) {
            AnalyticsTracker.track(Stat.SITE_SWITCHER_TOGGLE_BLOG_VISIBLE, props);
        }
    }
}
