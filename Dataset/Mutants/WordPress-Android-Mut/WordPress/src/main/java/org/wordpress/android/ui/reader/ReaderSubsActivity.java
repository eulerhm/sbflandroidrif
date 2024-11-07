package org.wordpress.android.ui.reader;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderBlogActions;
import org.wordpress.android.ui.reader.actions.ReaderTagActions;
import org.wordpress.android.ui.reader.adapters.ReaderBlogAdapter.ReaderBlogType;
import org.wordpress.android.ui.reader.adapters.ReaderTagAdapter;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.UpdateTask;
import org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.ui.reader.views.ReaderFollowButton;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.EditTextUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.widgets.WPSnackbar;
import org.wordpress.android.widgets.WPViewPager;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * activity which shows the user's subscriptions - includes
 * followed tags and followed blogs
 */
public class ReaderSubsActivity extends LocaleAwareActivity implements ReaderTagAdapter.TagDeletedListener {

    private EditText mEditAdd;

    private FloatingActionButton mFabButton;

    private ReaderFollowButton mBtnAdd;

    private WPViewPager mViewPager;

    private SubsPageAdapter mPageAdapter;

    private String mLastAddedTagName;

    private boolean mHasPerformedUpdate;

    private static final String KEY_LAST_ADDED_TAG_NAME = "last_added_tag_name";

    private static final int NUM_TABS = 3;

    public static final int TAB_IDX_FOLLOWED_TAGS = 0;

    public static final int TAB_IDX_FOLLOWED_BLOGS = 1;

    @Inject
    AccountStore mAccountStore;

    @Inject
    ReaderTracker mReaderTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(22500)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22501)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(22502)) {
            setContentView(R.layout.reader_activity_subs);
        }
        if (!ListenerUtil.mutListener.listen(22503)) {
            restoreState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(22504)) {
            mViewPager = findViewById(R.id.viewpager);
        }
        if (!ListenerUtil.mutListener.listen(22509)) {
            mViewPager.setOffscreenPageLimit((ListenerUtil.mutListener.listen(22508) ? (NUM_TABS % 1) : (ListenerUtil.mutListener.listen(22507) ? (NUM_TABS / 1) : (ListenerUtil.mutListener.listen(22506) ? (NUM_TABS * 1) : (ListenerUtil.mutListener.listen(22505) ? (NUM_TABS + 1) : (NUM_TABS - 1))))));
        }
        if (!ListenerUtil.mutListener.listen(22510)) {
            mViewPager.setAdapter(getPageAdapter());
        }
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        if (!ListenerUtil.mutListener.listen(22511)) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        if (!ListenerUtil.mutListener.listen(22512)) {
            tabLayout.setupWithViewPager(mViewPager);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(22515)) {
            if (toolbar != null) {
                if (!ListenerUtil.mutListener.listen(22513)) {
                    setSupportActionBar(toolbar);
                }
                if (!ListenerUtil.mutListener.listen(22514)) {
                    toolbar.setNavigationOnClickListener(v -> onBackPressed());
                }
            }
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(22518)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(22516)) {
                    // Shadow removed on Activities with a tab toolbar
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(22517)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        View bottomBar = findViewById(R.id.layout_bottom);
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(this);
        float appbarElevation = getResources().getDimension(R.dimen.appbar_elevation);
        int elevatedColor = elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(appbarElevation);
        if (!ListenerUtil.mutListener.listen(22519)) {
            bottomBar.setBackgroundColor(elevatedColor);
        }
        if (!ListenerUtil.mutListener.listen(22520)) {
            mEditAdd = findViewById(R.id.edit_add);
        }
        if (!ListenerUtil.mutListener.listen(22521)) {
            mEditAdd.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addCurrentEntry();
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(22522)) {
            mFabButton = findViewById(R.id.fab_button);
        }
        if (!ListenerUtil.mutListener.listen(22523)) {
            mFabButton.setOnClickListener(view -> ReaderActivityLauncher.showReaderInterests(this));
        }
        if (!ListenerUtil.mutListener.listen(22524)) {
            mBtnAdd = findViewById(R.id.btn_add);
        }
        if (!ListenerUtil.mutListener.listen(22525)) {
            mBtnAdd.setOnClickListener(v -> addCurrentEntry());
        }
        if (!ListenerUtil.mutListener.listen(22527)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(22526)) {
                    // return to the page the user was on the last time they viewed this activity
                    restorePreviousPage();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22529)) {
            // note this listener must be assigned after we've already called restorePreviousPage()
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    // remember which page the user last viewed
                    String pageTitle = (String) getPageAdapter().getPageTitle(position);
                    if (!ListenerUtil.mutListener.listen(22528)) {
                        AppPrefs.setReaderSubsPageTitle(pageTitle);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(22530)) {
            mReaderTracker.track(Stat.READER_MANAGE_VIEW_DISPLAYED);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(22531)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(22532)) {
            super.onPause();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(22533)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(22534)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(22536)) {
            // update list of tags and blogs from the server
            if (!mHasPerformedUpdate) {
                if (!ListenerUtil.mutListener.listen(22535)) {
                    performUpdate();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.FollowedTagsChanged event) {
        if (!ListenerUtil.mutListener.listen(22537)) {
            AppLog.d(AppLog.T.READER, "reader subs > followed tags changed");
        }
        if (!ListenerUtil.mutListener.listen(22538)) {
            getPageAdapter().refreshFollowedTagFragment();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.FollowedBlogsChanged event) {
        if (!ListenerUtil.mutListener.listen(22539)) {
            AppLog.d(AppLog.T.READER, "reader subs > followed blogs changed");
        }
        if (!ListenerUtil.mutListener.listen(22540)) {
            getPageAdapter().refreshBlogFragments(ReaderBlogType.FOLLOWED);
        }
    }

    private void performUpdate() {
        if (!ListenerUtil.mutListener.listen(22541)) {
            performUpdate(EnumSet.of(UpdateTask.TAGS, UpdateTask.FOLLOWED_BLOGS));
        }
    }

    private void performUpdate(EnumSet<UpdateTask> tasks) {
        if (!ListenerUtil.mutListener.listen(22542)) {
            if (!NetworkUtils.isNetworkAvailable(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22543)) {
            ReaderUpdateServiceStarter.startService(this, tasks);
        }
        if (!ListenerUtil.mutListener.listen(22544)) {
            mHasPerformedUpdate = true;
        }
    }

    private void restoreState(Bundle state) {
        if (!ListenerUtil.mutListener.listen(22547)) {
            if (state != null) {
                if (!ListenerUtil.mutListener.listen(22545)) {
                    mLastAddedTagName = state.getString(KEY_LAST_ADDED_TAG_NAME);
                }
                if (!ListenerUtil.mutListener.listen(22546)) {
                    mHasPerformedUpdate = state.getBoolean(ReaderConstants.KEY_ALREADY_UPDATED);
                }
            }
        }
    }

    private SubsPageAdapter getPageAdapter() {
        if (!ListenerUtil.mutListener.listen(22551)) {
            if (mPageAdapter == null) {
                List<Fragment> fragments = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(22548)) {
                    fragments.add(ReaderTagFragment.newInstance());
                }
                if (!ListenerUtil.mutListener.listen(22549)) {
                    fragments.add(ReaderBlogFragment.newInstance(ReaderBlogType.FOLLOWED));
                }
                FragmentManager fm = getSupportFragmentManager();
                if (!ListenerUtil.mutListener.listen(22550)) {
                    mPageAdapter = new SubsPageAdapter(fm, fragments);
                }
            }
        }
        return mPageAdapter;
    }

    private boolean hasPageAdapter() {
        return mPageAdapter != null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(22552)) {
            outState.putBoolean(ReaderConstants.KEY_ALREADY_UPDATED, mHasPerformedUpdate);
        }
        if (!ListenerUtil.mutListener.listen(22554)) {
            if (mLastAddedTagName != null) {
                if (!ListenerUtil.mutListener.listen(22553)) {
                    outState.putString(KEY_LAST_ADDED_TAG_NAME, mLastAddedTagName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22555)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(22557)) {
            if (!TextUtils.isEmpty(mLastAddedTagName)) {
                if (!ListenerUtil.mutListener.listen(22556)) {
                    EventBus.getDefault().postSticky(new ReaderEvents.TagAdded(mLastAddedTagName));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22558)) {
            mReaderTracker.track(Stat.READER_MANAGE_VIEW_DISMISSED);
        }
        if (!ListenerUtil.mutListener.listen(22559)) {
            super.onBackPressed();
        }
    }

    /*
     * follow the tag or url the user typed into the EditText
     */
    private void addCurrentEntry() {
        String entry = EditTextUtils.getText(mEditAdd).trim();
        if (!ListenerUtil.mutListener.listen(22560)) {
            if (TextUtils.isEmpty(entry)) {
                return;
            }
        }
        // is it a url or a tag?
        boolean isUrl = (ListenerUtil.mutListener.listen(22562) ? (!entry.contains(" ") || ((ListenerUtil.mutListener.listen(22561) ? (entry.contains(".") && entry.contains("://")) : (entry.contains(".") || entry.contains("://"))))) : (!entry.contains(" ") && ((ListenerUtil.mutListener.listen(22561) ? (entry.contains(".") && entry.contains("://")) : (entry.contains(".") || entry.contains("://"))))));
        if (!ListenerUtil.mutListener.listen(22565)) {
            if (isUrl) {
                if (!ListenerUtil.mutListener.listen(22564)) {
                    addAsUrl(entry);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22563)) {
                    addAsTag(entry);
                }
            }
        }
    }

    /*
     * follow editText entry as a tag
     */
    private void addAsTag(final String entry) {
        if (!ListenerUtil.mutListener.listen(22566)) {
            if (TextUtils.isEmpty(entry)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22568)) {
            if (!ReaderTag.isValidTagName(entry)) {
                if (!ListenerUtil.mutListener.listen(22567)) {
                    showInfoSnackbar(getString(R.string.reader_toast_err_tag_invalid));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22570)) {
            if (ReaderTagTable.isFollowedTagName(entry)) {
                if (!ListenerUtil.mutListener.listen(22569)) {
                    showInfoSnackbar(getString(R.string.reader_toast_err_tag_exists));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22571)) {
            // tag is valid, follow it
            mEditAdd.setText(null);
        }
        if (!ListenerUtil.mutListener.listen(22572)) {
            EditTextUtils.hideSoftInput(mEditAdd);
        }
        if (!ListenerUtil.mutListener.listen(22573)) {
            performAddTag(entry);
        }
    }

    /*
     * follow editText entry as a url
     */
    private void addAsUrl(final String entry) {
        if (!ListenerUtil.mutListener.listen(22574)) {
            if (TextUtils.isEmpty(entry)) {
                return;
            }
        }
        // normalize the url and prepend protocol if not supplied
        final String normUrl;
        if (!entry.contains("://")) {
            normUrl = UrlUtils.normalizeUrl("http://" + entry);
        } else {
            normUrl = UrlUtils.normalizeUrl(entry);
        }
        if (!ListenerUtil.mutListener.listen(22576)) {
            // if this isn't a valid URL, add original entry as a tag
            if (!URLUtil.isNetworkUrl(normUrl)) {
                if (!ListenerUtil.mutListener.listen(22575)) {
                    addAsTag(entry);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22579)) {
            // make sure it isn't already followed
            if ((ListenerUtil.mutListener.listen(22577) ? (ReaderBlogTable.isFollowedBlogUrl(normUrl) && ReaderBlogTable.isFollowedFeedUrl(normUrl)) : (ReaderBlogTable.isFollowedBlogUrl(normUrl) || ReaderBlogTable.isFollowedFeedUrl(normUrl)))) {
                if (!ListenerUtil.mutListener.listen(22578)) {
                    showInfoSnackbar(getString(R.string.reader_toast_err_already_follow_blog));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22580)) {
            // URL is valid, so follow it
            performAddUrl(normUrl);
        }
    }

    /*
     * called when user manually enters a tag - passed tag is assumed to be validated
     */
    private void performAddTag(final String tagName) {
        if (!ListenerUtil.mutListener.listen(22581)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22582)) {
            showProgress();
        }
        final ReaderTag tag = ReaderUtils.createTagFromTagName(tagName, ReaderTagType.FOLLOWED);
        ReaderActions.ActionListener actionListener = succeeded -> {
            if (isFinishing()) {
                return;
            }
            hideProgress();
            getPageAdapter().refreshFollowedTagFragment();
            if (succeeded) {
                showInfoSnackbar(getString(R.string.reader_label_added_tag, tag.getLabel()));
                mLastAddedTagName = tag.getTagSlug();
                mReaderTracker.trackTag(AnalyticsTracker.Stat.READER_TAG_FOLLOWED, mLastAddedTagName, ReaderTracker.SOURCE_SETTINGS);
            } else {
                showInfoSnackbar(getString(R.string.reader_toast_err_add_tag));
                mLastAddedTagName = null;
            }
        };
        if (!ListenerUtil.mutListener.listen(22583)) {
            ReaderTagActions.addTag(tag, actionListener, mAccountStore.hasAccessToken());
        }
    }

    /*
     * start a two-step process to follow a blog by url:
     * 1. test whether the url is reachable (API will follow any url, even if it doesn't exist)
     * 2. perform the actual follow
     * note that the passed URL is assumed to be normalized and validated
     */
    private void performAddUrl(final String blogUrl) {
        if (!ListenerUtil.mutListener.listen(22584)) {
            if (!NetworkUtils.checkConnection(this)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22585)) {
            showProgress();
        }
        ReaderActions.OnRequestListener<Void> requestListener = new ReaderActions.OnRequestListener<Void>() {

            @Override
            public void onSuccess(Void result) {
                if (!ListenerUtil.mutListener.listen(22587)) {
                    if (!isFinishing()) {
                        if (!ListenerUtil.mutListener.listen(22586)) {
                            followBlogUrl(blogUrl);
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode) {
                if (!ListenerUtil.mutListener.listen(22590)) {
                    if (!isFinishing()) {
                        if (!ListenerUtil.mutListener.listen(22588)) {
                            hideProgress();
                        }
                        String errMsg;
                        switch(statusCode) {
                            case 401:
                                errMsg = getString(R.string.reader_toast_err_follow_blog_not_authorized);
                                break;
                            // can happen when host name not found
                            case 0:
                            case 404:
                                errMsg = getString(R.string.reader_toast_err_follow_blog_not_found);
                                break;
                            default:
                                errMsg = getString(R.string.reader_toast_err_follow_blog) + " (" + statusCode + ")";
                                break;
                        }
                        if (!ListenerUtil.mutListener.listen(22589)) {
                            showInfoSnackbar(errMsg);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(22591)) {
            ReaderBlogActions.checkUrlReachable(blogUrl, requestListener);
        }
    }

    private void followBlogUrl(String normUrl) {
        ReaderActions.ActionListener followListener = succeeded -> {
            if (isFinishing()) {
                return;
            }
            hideProgress();
            if (succeeded) {
                // clear the edit text and hide the soft keyboard
                mEditAdd.setText(null);
                EditTextUtils.hideSoftInput(mEditAdd);
                showInfoSnackbar(getString(R.string.reader_label_followed_blog));
                getPageAdapter().refreshBlogFragments(ReaderBlogType.FOLLOWED);
                // in bottom sheet reader filtering
                performUpdate(EnumSet.of(UpdateTask.TAGS, UpdateTask.FOLLOWED_BLOGS));
            } else {
                showInfoSnackbar(getString(R.string.reader_toast_err_follow_blog));
            }
        };
        if (!ListenerUtil.mutListener.listen(22592)) {
            // follow it as a blog if it is one)
            ReaderBlogActions.followFeedByUrl(normUrl, followListener, ReaderTracker.SOURCE_SETTINGS, mReaderTracker);
        }
    }

    /*
     * called prior to following to show progress and disable controls
     */
    private void showProgress() {
        final ProgressBar progress = findViewById(R.id.progress_follow);
        if (!ListenerUtil.mutListener.listen(22593)) {
            progress.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(22594)) {
            mEditAdd.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(22595)) {
            mBtnAdd.setEnabled(false);
        }
    }

    /*
     * called after following to hide progress and re-enable controls
     */
    private void hideProgress() {
        final ProgressBar progress = findViewById(R.id.progress_follow);
        if (!ListenerUtil.mutListener.listen(22596)) {
            progress.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(22597)) {
            mEditAdd.setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(22598)) {
            mBtnAdd.setEnabled(true);
        }
    }

    /*
     * Snackbar message shown when adding/removing or something goes wrong
     */
    private void showInfoSnackbar(String text) {
        View bottomView = findViewById(R.id.layout_bottom);
        Snackbar snackbar = WPSnackbar.make(bottomView, text, Snackbar.LENGTH_LONG);
        if (!ListenerUtil.mutListener.listen(22599)) {
            snackbar.setAnchorView(bottomView);
        }
        if (!ListenerUtil.mutListener.listen(22600)) {
            snackbar.show();
        }
    }

    /*
     * triggered by a tag fragment's adapter after user removes a tag - note that the network
     * request has already been made when this is called
     */
    @Override
    public void onTagDeleted(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(22601)) {
            mReaderTracker.trackTag(AnalyticsTracker.Stat.READER_TAG_UNFOLLOWED, tag.getTagSlug(), ReaderTracker.SOURCE_SETTINGS);
        }
        if (!ListenerUtil.mutListener.listen(22604)) {
            if ((ListenerUtil.mutListener.listen(22602) ? (mLastAddedTagName != null || mLastAddedTagName.equalsIgnoreCase(tag.getTagSlug())) : (mLastAddedTagName != null && mLastAddedTagName.equalsIgnoreCase(tag.getTagSlug())))) {
                if (!ListenerUtil.mutListener.listen(22603)) {
                    mLastAddedTagName = null;
                }
            }
        }
        String labelRemovedTag = getString(R.string.reader_label_removed_tag);
        if (!ListenerUtil.mutListener.listen(22605)) {
            showInfoSnackbar(String.format(labelRemovedTag, tag.getLabel()));
        }
    }

    /*
     * return to the previously selected page in the viewPager
     */
    private void restorePreviousPage() {
        if (!ListenerUtil.mutListener.listen(22607)) {
            if ((ListenerUtil.mutListener.listen(22606) ? (mViewPager == null && !hasPageAdapter()) : (mViewPager == null || !hasPageAdapter()))) {
                return;
            }
        }
        String pageTitle = AppPrefs.getReaderSubsPageTitle();
        if (!ListenerUtil.mutListener.listen(22611)) {
            if (getIntent().hasExtra(ReaderConstants.ARG_SUBS_TAB_POSITION)) {
                PagerAdapter adapter = getPageAdapter();
                int tabIndex = getIntent().getIntExtra(ReaderConstants.ARG_SUBS_TAB_POSITION, TAB_IDX_FOLLOWED_TAGS);
                if (!ListenerUtil.mutListener.listen(22608)) {
                    pageTitle = (String) adapter.getPageTitle(tabIndex);
                }
                if (!ListenerUtil.mutListener.listen(22610)) {
                    if (!TextUtils.isEmpty(pageTitle))
                        if (!ListenerUtil.mutListener.listen(22609)) {
                            AppPrefs.setReaderSubsPageTitle(pageTitle);
                        }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22612)) {
            if (TextUtils.isEmpty(pageTitle)) {
                return;
            }
        }
        PagerAdapter adapter = getPageAdapter();
        if (!ListenerUtil.mutListener.listen(22620)) {
            {
                long _loopCounter339 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(22619) ? (i >= adapter.getCount()) : (ListenerUtil.mutListener.listen(22618) ? (i <= adapter.getCount()) : (ListenerUtil.mutListener.listen(22617) ? (i > adapter.getCount()) : (ListenerUtil.mutListener.listen(22616) ? (i != adapter.getCount()) : (ListenerUtil.mutListener.listen(22615) ? (i == adapter.getCount()) : (i < adapter.getCount())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter339", ++_loopCounter339);
                    if (!ListenerUtil.mutListener.listen(22614)) {
                        if (pageTitle.equals(adapter.getPageTitle(i))) {
                            if (!ListenerUtil.mutListener.listen(22613)) {
                                mViewPager.setCurrentItem(i);
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!ListenerUtil.mutListener.listen(22621)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(22623)) {
            if (requestCode == RequestCodes.READER_INTERESTS) {
                if (!ListenerUtil.mutListener.listen(22622)) {
                    performUpdate(EnumSet.of(UpdateTask.TAGS));
                }
            }
        }
    }

    private class SubsPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments;

        SubsPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case TAB_IDX_FOLLOWED_TAGS:
                    return getString(R.string.reader_page_followed_tags);
                case TAB_IDX_FOLLOWED_BLOGS:
                    return getString(R.string.reader_page_followed_blogs);
                default:
                    return super.getPageTitle(position);
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object ret = super.instantiateItem(container, position);
            if (!ListenerUtil.mutListener.listen(22624)) {
                mFragments.set(position, (Fragment) ret);
            }
            return ret;
        }

        private void refreshFollowedTagFragment() {
            if (!ListenerUtil.mutListener.listen(22627)) {
                {
                    long _loopCounter340 = 0;
                    for (Fragment fragment : mFragments) {
                        ListenerUtil.loopListener.listen("_loopCounter340", ++_loopCounter340);
                        if (!ListenerUtil.mutListener.listen(22626)) {
                            if (fragment instanceof ReaderTagFragment) {
                                ReaderTagFragment tagFragment = (ReaderTagFragment) fragment;
                                if (!ListenerUtil.mutListener.listen(22625)) {
                                    tagFragment.refresh();
                                }
                            }
                        }
                    }
                }
            }
        }

        private void refreshBlogFragments(ReaderBlogType blogType) {
            if (!ListenerUtil.mutListener.listen(22632)) {
                {
                    long _loopCounter341 = 0;
                    for (Fragment fragment : mFragments) {
                        ListenerUtil.loopListener.listen("_loopCounter341", ++_loopCounter341);
                        if (!ListenerUtil.mutListener.listen(22631)) {
                            if (fragment instanceof ReaderBlogFragment) {
                                ReaderBlogFragment blogFragment = (ReaderBlogFragment) fragment;
                                if (!ListenerUtil.mutListener.listen(22630)) {
                                    if ((ListenerUtil.mutListener.listen(22628) ? (blogType == null && blogType.equals(blogFragment.getBlogType())) : (blogType == null || blogType.equals(blogFragment.getBlogType())))) {
                                        if (!ListenerUtil.mutListener.listen(22629)) {
                                            blogFragment.refresh();
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
