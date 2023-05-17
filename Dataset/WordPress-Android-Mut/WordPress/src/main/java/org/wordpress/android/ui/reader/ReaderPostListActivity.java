package org.wordpress.android.ui.reader;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.AppBarLayout;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.PostStore.OnPostUploaded;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.ReaderBlog;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtils;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.util.ToastUtils;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * serves as the host for ReaderPostListFragment when showing blog preview & tag preview
 */
public class ReaderPostListActivity extends LocaleAwareActivity {

    private String mSource;

    private ReaderPostListType mPostListType;

    private long mSiteId;

    @Inject
    SiteStore mSiteStore;

    @Inject
    PostStore mPostStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    UploadActionUseCase mUploadActionUseCase;

    @Inject
    UploadUtilsWrapper mUploadUtilsWrapper;

    @Inject
    ReaderTracker mReaderTracker;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(20968)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(20969)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(20970)) {
            setContentView(R.layout.reader_activity_post_list);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(20971)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(20974)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(20972)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(20973)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20975)) {
            mSource = getIntent().getStringExtra(ReaderConstants.ARG_SOURCE);
        }
        if (!ListenerUtil.mutListener.listen(20978)) {
            if (getIntent().hasExtra(ReaderConstants.ARG_POST_LIST_TYPE)) {
                if (!ListenerUtil.mutListener.listen(20977)) {
                    mPostListType = (ReaderPostListType) getIntent().getSerializableExtra(ReaderConstants.ARG_POST_LIST_TYPE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20976)) {
                    mPostListType = ReaderTypes.DEFAULT_POST_LIST_TYPE;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21002)) {
            if ((ListenerUtil.mutListener.listen(20979) ? (getPostListType() == ReaderPostListType.TAG_PREVIEW && getPostListType() == ReaderPostListType.BLOG_PREVIEW) : (getPostListType() == ReaderPostListType.TAG_PREVIEW || getPostListType() == ReaderPostListType.BLOG_PREVIEW))) {
                // show an X in the toolbar which closes the activity - if this is blog preview
                boolean showCrossButton = getPostListType() == ReaderPostListType.BLOG_PREVIEW;
                if (!ListenerUtil.mutListener.listen(20981)) {
                    if (showCrossButton) {
                        if (!ListenerUtil.mutListener.listen(20980)) {
                            toolbar.setNavigationIcon(R.drawable.ic_cross_white_24dp);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(20983)) {
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            if (!ListenerUtil.mutListener.listen(20982)) {
                                finish();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(21001)) {
                    if (getPostListType() == ReaderPostListType.BLOG_PREVIEW) {
                        if (!ListenerUtil.mutListener.listen(20988)) {
                            setTitle(R.string.reader_title_blog_preview);
                        }
                        if (!ListenerUtil.mutListener.listen(21000)) {
                            if (savedInstanceState == null) {
                                long blogId = getIntent().getLongExtra(ReaderConstants.ARG_BLOG_ID, 0);
                                long feedId = getIntent().getLongExtra(ReaderConstants.ARG_FEED_ID, 0);
                                if (!ListenerUtil.mutListener.listen(20999)) {
                                    if ((ListenerUtil.mutListener.listen(20994) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(20993) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(20992) ? (feedId > 0) : (ListenerUtil.mutListener.listen(20991) ? (feedId < 0) : (ListenerUtil.mutListener.listen(20990) ? (feedId == 0) : (feedId != 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(20997)) {
                                            showListFragmentForFeed(feedId);
                                        }
                                        if (!ListenerUtil.mutListener.listen(20998)) {
                                            mSiteId = feedId;
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(20995)) {
                                            showListFragmentForBlog(blogId);
                                        }
                                        if (!ListenerUtil.mutListener.listen(20996)) {
                                            mSiteId = blogId;
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(20989)) {
                                    mSiteId = savedInstanceState.getLong(ReaderConstants.KEY_SITE_ID);
                                }
                            }
                        }
                    } else if (getPostListType() == ReaderPostListType.TAG_PREVIEW) {
                        if (!ListenerUtil.mutListener.listen(20984)) {
                            setTitle(R.string.reader_title_tag_preview);
                        }
                        ReaderTag tag = (ReaderTag) getIntent().getSerializableExtra(ReaderConstants.ARG_TAG);
                        if (!ListenerUtil.mutListener.listen(20987)) {
                            if ((ListenerUtil.mutListener.listen(20985) ? (tag != null || savedInstanceState == null) : (tag != null && savedInstanceState == null))) {
                                if (!ListenerUtil.mutListener.listen(20986)) {
                                    showListFragmentForTag(tag, mPostListType);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21005)) {
            // restore the activity title
            if ((ListenerUtil.mutListener.listen(21003) ? (savedInstanceState != null || savedInstanceState.containsKey(ReaderConstants.KEY_ACTIVITY_TITLE)) : (savedInstanceState != null && savedInstanceState.containsKey(ReaderConstants.KEY_ACTIVITY_TITLE)))) {
                if (!ListenerUtil.mutListener.listen(21004)) {
                    setTitle(savedInstanceState.getString(ReaderConstants.KEY_ACTIVITY_TITLE));
                }
            }
        }
    }

    @Override
    protected void onResumeFragments() {
        if (!ListenerUtil.mutListener.listen(21006)) {
            super.onResumeFragments();
        }
        if (!ListenerUtil.mutListener.listen(21007)) {
            // this particular Activity doesn't show filtering, so we'll disable the FilteredRecyclerView toolbar here
            disableFilteredRecyclerViewToolbar();
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(21008)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(21009)) {
            // We register the dispatcher in order to receive the OnPostUploaded event and show the snackbar
            mDispatcher.register(this);
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(21010)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(21011)) {
            mDispatcher.unregister(this);
        }
    }

    /*
     * This method hides the FilteredRecyclerView toolbar with spinner so to disable content filtering, for reusability
     */
    private void disableFilteredRecyclerViewToolbar() {
        // occupied space, as otherwise expected
        AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout);
        if (!ListenerUtil.mutListener.listen(21014)) {
            if (appBarLayout != null) {
                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(21012)) {
                    lp.height = 0;
                }
                if (!ListenerUtil.mutListener.listen(21013)) {
                    appBarLayout.setLayoutParams(lp);
                }
            }
        }
        // disabling any CoordinatorLayout behavior for scrolling
        Toolbar toolbarWithSpinner = findViewById(R.id.toolbar_with_spinner);
        if (!ListenerUtil.mutListener.listen(21017)) {
            if (toolbarWithSpinner != null) {
                AppBarLayout.LayoutParams p = (AppBarLayout.LayoutParams) toolbarWithSpinner.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(21015)) {
                    p.setScrollFlags(0);
                }
                if (!ListenerUtil.mutListener.listen(21016)) {
                    toolbarWithSpinner.setLayoutParams(p);
                }
            }
        }
    }

    private ReaderPostListType getPostListType() {
        return (mPostListType != null ? mPostListType : ReaderTypes.DEFAULT_POST_LIST_TYPE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(21019)) {
            if (outState.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(21018)) {
                    outState.putBoolean("bug_19917_fix", true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21023)) {
            // store the title for blog/tag preview so we can restore it upon recreation
            if ((ListenerUtil.mutListener.listen(21020) ? (getPostListType() == ReaderPostListType.BLOG_PREVIEW && getPostListType() == ReaderPostListType.TAG_PREVIEW) : (getPostListType() == ReaderPostListType.BLOG_PREVIEW || getPostListType() == ReaderPostListType.TAG_PREVIEW))) {
                if (!ListenerUtil.mutListener.listen(21021)) {
                    outState.putString(ReaderConstants.KEY_ACTIVITY_TITLE, getTitle().toString());
                }
                if (!ListenerUtil.mutListener.listen(21022)) {
                    outState.putLong(ReaderConstants.KEY_SITE_ID, mSiteId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21024)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        ReaderPostListFragment fragment = getListFragment();
        if (!ListenerUtil.mutListener.listen(21027)) {
            if ((ListenerUtil.mutListener.listen(21025) ? (fragment == null && !fragment.onActivityBackPressed()) : (fragment == null || !fragment.onActivityBackPressed()))) {
                if (!ListenerUtil.mutListener.listen(21026)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(21029)) {
            if (getPostListType() == ReaderPostListType.BLOG_PREVIEW) {
                if (!ListenerUtil.mutListener.listen(21028)) {
                    getMenuInflater().inflate(R.menu.share, menu);
                }
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(21032)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(21030)) {
                        onBackPressed();
                    }
                    return true;
                case R.id.menu_share:
                    if (!ListenerUtil.mutListener.listen(21031)) {
                        shareSite();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareSite() {
        ReaderBlog blog = ReaderBlogTable.getBlogInfo(mSiteId);
        if (!ListenerUtil.mutListener.listen(21042)) {
            if ((ListenerUtil.mutListener.listen(21033) ? (blog != null || blog.hasUrl()) : (blog != null && blog.hasUrl()))) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                if (!ListenerUtil.mutListener.listen(21035)) {
                    intent.setType("text/plain");
                }
                if (!ListenerUtil.mutListener.listen(21036)) {
                    intent.putExtra(Intent.EXTRA_TEXT, blog.getUrl());
                }
                if (!ListenerUtil.mutListener.listen(21038)) {
                    if (blog.hasName()) {
                        if (!ListenerUtil.mutListener.listen(21037)) {
                            intent.putExtra(Intent.EXTRA_SUBJECT, blog.getName());
                        }
                    }
                }
                try {
                    if (!ListenerUtil.mutListener.listen(21040)) {
                        mReaderTracker.trackBlog(AnalyticsTracker.Stat.READER_SITE_SHARED, blog.blogId, blog.feedId, blog.isFollowing, mSource);
                    }
                    if (!ListenerUtil.mutListener.listen(21041)) {
                        startActivity(Intent.createChooser(intent, getString(R.string.share_link)));
                    }
                } catch (ActivityNotFoundException exception) {
                    if (!ListenerUtil.mutListener.listen(21039)) {
                        ToastUtils.showToast(ReaderPostListActivity.this, R.string.reader_toast_err_share_intent);
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(21034)) {
                    ToastUtils.showToast(ReaderPostListActivity.this, R.string.reader_toast_err_share_intent);
                }
            }
        }
    }

    /*
     * show fragment containing list of latest posts for a specific tag
     */
    private void showListFragmentForTag(@NonNull final ReaderTag tag, ReaderPostListType listType) {
        if (!ListenerUtil.mutListener.listen(21043)) {
            if (isFinishing()) {
                return;
            }
        }
        Fragment fragment = ReaderPostListFragment.newInstanceForTag(tag, listType);
        if (!ListenerUtil.mutListener.listen(21044)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, getString(R.string.fragment_tag_reader_post_list)).commit();
        }
    }

    /*
     * show fragment containing list of latest posts in a specific blog
     */
    private void showListFragmentForBlog(long blogId) {
        if (!ListenerUtil.mutListener.listen(21045)) {
            if (isFinishing()) {
                return;
            }
        }
        Fragment fragment = ReaderPostListFragment.newInstanceForBlog(blogId);
        if (!ListenerUtil.mutListener.listen(21046)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, getString(R.string.fragment_tag_reader_post_list)).commit();
        }
        String title = ReaderBlogTable.getBlogName(blogId);
        if (!ListenerUtil.mutListener.listen(21048)) {
            if (title.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(21047)) {
                    title = getString(R.string.reader_title_blog_preview);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21049)) {
            setTitle(title);
        }
    }

    private void showListFragmentForFeed(long feedId) {
        if (!ListenerUtil.mutListener.listen(21050)) {
            if (isFinishing()) {
                return;
            }
        }
        Fragment fragment = ReaderPostListFragment.newInstanceForFeed(feedId);
        if (!ListenerUtil.mutListener.listen(21051)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, getString(R.string.fragment_tag_reader_post_list)).commit();
        }
        String title = ReaderBlogTable.getFeedName(feedId);
        if (!ListenerUtil.mutListener.listen(21053)) {
            if (title.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(21052)) {
                    title = getString(R.string.reader_title_blog_preview);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21054)) {
            setTitle(title);
        }
    }

    private ReaderPostListFragment getListFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.fragment_tag_reader_post_list));
        if (!ListenerUtil.mutListener.listen(21055)) {
            if (fragment == null) {
                return null;
            }
        }
        return ((ReaderPostListFragment) fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(21056)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(21068)) {
            switch(requestCode) {
                case RequestCodes.NO_REBLOG_SITE:
                    if (!ListenerUtil.mutListener.listen(21058)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(21057)) {
                                // Finish activity to make My Site page visible
                                finish();
                            }
                        }
                    }
                    break;
                case RequestCodes.EDIT_POST:
                    if (!ListenerUtil.mutListener.listen(21067)) {
                        if ((ListenerUtil.mutListener.listen(21060) ? ((ListenerUtil.mutListener.listen(21059) ? (resultCode == Activity.RESULT_OK || data != null) : (resultCode == Activity.RESULT_OK && data != null)) || !isFinishing()) : ((ListenerUtil.mutListener.listen(21059) ? (resultCode == Activity.RESULT_OK || data != null) : (resultCode == Activity.RESULT_OK && data != null)) && !isFinishing()))) {
                            int localId = data.getIntExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, 0);
                            final SiteModel site = (SiteModel) data.getSerializableExtra(WordPress.SITE);
                            final PostModel post = mPostStore.getPostByLocalPostId(localId);
                            if (!ListenerUtil.mutListener.listen(21062)) {
                                if (EditPostActivity.checkToRestart(data)) {
                                    if (!ListenerUtil.mutListener.listen(21061)) {
                                        ActivityLauncher.editPostOrPageForResult(data, ReaderPostListActivity.this, site, data.getIntExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, 0));
                                    }
                                    // a restart will happen so, no need to continue here
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(21066)) {
                                if ((ListenerUtil.mutListener.listen(21063) ? (site != null || post != null) : (site != null && post != null))) {
                                    if (!ListenerUtil.mutListener.listen(21065)) {
                                        mUploadUtilsWrapper.handleEditPostResultSnackbars(this, findViewById(R.id.coordinator), data, post, site, mUploadActionUseCase.getUploadAction(post), new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                if (!ListenerUtil.mutListener.listen(21064)) {
                                                    UploadUtils.publishPost(ReaderPostListActivity.this, post, site, mDispatcher);
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostUploaded(OnPostUploaded event) {
        SiteModel site = mSiteStore.getSiteByLocalId(mSelectedSiteRepository.getSelectedSiteLocalId());
        if (!ListenerUtil.mutListener.listen(21071)) {
            if ((ListenerUtil.mutListener.listen(21069) ? (site != null || event.post != null) : (site != null && event.post != null))) {
                if (!ListenerUtil.mutListener.listen(21070)) {
                    mUploadUtilsWrapper.onPostUploadedSnackbarHandler(this, findViewById(R.id.coordinator), event.isError(), event.isFirstTimePublish, event.post, null, site);
                }
            }
        }
    }
}
