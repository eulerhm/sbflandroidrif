package org.wordpress.android.ui.comments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.appbar.AppBarLayout;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.action.CommentAction;
import org.wordpress.android.fluxc.generated.CommentActionBuilder;
import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.CommentStore.FetchCommentsPayload;
import org.wordpress.android.fluxc.store.CommentStore.OnCommentChanged;
import org.wordpress.android.models.CommentList;
import org.wordpress.android.ui.CollapseFullScreenDialogFragment;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.ScrollableViewInitializedListener;
import org.wordpress.android.ui.comments.unified.CommentConstants;
import org.wordpress.android.ui.comments.unified.OnLoadMoreListener;
import org.wordpress.android.ui.comments.unified.CommentsStoreAdapter;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource;
import org.wordpress.android.widgets.WPViewPager;
import org.wordpress.android.widgets.WPViewPagerTransformer;
import javax.inject.Inject;
import static org.wordpress.android.ui.comments.unified.CommentConstants.COMMENTS_PER_PAGE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @deprecated
 * Comments are being refactored as part of Comments Unification project. If you are adding any
 * features or modifying this class, please ping develric or klymyam
 */
@Deprecated
public class CommentsDetailActivity extends LocaleAwareActivity implements OnLoadMoreListener, CommentActions.OnCommentActionListener, ScrollableViewInitializedListener {

    public static final String COMMENT_ID_EXTRA = "commentId";

    public static final String COMMENT_STATUS_FILTER_EXTRA = "commentStatusFilter";

    @Inject
    CommentsStoreAdapter mCommentsStoreAdapter;

    private WPViewPager mViewPager;

    private AppBarLayout mAppBarLayout;

    private ProgressBar mProgressBar;

    private long mCommentId;

    private CommentStatus mStatusFilter;

    private SiteModel mSite;

    private CommentDetailFragmentAdapter mAdapter;

    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    private boolean mIsLoadingComments;

    private boolean mIsUpdatingComments;

    private boolean mCanLoadMoreComments = true;

    @Override
    public void onBackPressed() {
        CollapseFullScreenDialogFragment fragment = (CollapseFullScreenDialogFragment) getSupportFragmentManager().findFragmentByTag(CollapseFullScreenDialogFragment.TAG);
        if (!ListenerUtil.mutListener.listen(4810)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(4809)) {
                    fragment.onBackPressed();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4808)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4811)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4812)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(4813)) {
            mCommentsStoreAdapter.register(this);
        }
        if (!ListenerUtil.mutListener.listen(4814)) {
            AppLog.i(AppLog.T.COMMENTS, "Creating CommentsDetailActivity");
        }
        if (!ListenerUtil.mutListener.listen(4815)) {
            setContentView(R.layout.comments_detail_activity);
        }
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        if (!ListenerUtil.mutListener.listen(4816)) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(4819)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(4817)) {
                    actionBar.setDisplayShowTitleEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(4818)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4826)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(4823)) {
                    mCommentId = getIntent().getLongExtra(COMMENT_ID_EXTRA, -1);
                }
                if (!ListenerUtil.mutListener.listen(4824)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(4825)) {
                    mStatusFilter = (CommentStatus) getIntent().getSerializableExtra(COMMENT_STATUS_FILTER_EXTRA);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4820)) {
                    mCommentId = savedInstanceState.getLong(COMMENT_ID_EXTRA);
                }
                if (!ListenerUtil.mutListener.listen(4821)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
                if (!ListenerUtil.mutListener.listen(4822)) {
                    mStatusFilter = (CommentStatus) savedInstanceState.getSerializable(COMMENT_STATUS_FILTER_EXTRA);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4827)) {
            // set up the viewpager and adapter for lateral navigation
            mViewPager = findViewById(R.id.viewpager);
        }
        if (!ListenerUtil.mutListener.listen(4828)) {
            mViewPager.setPageTransformer(false, new WPViewPagerTransformer(WPViewPagerTransformer.TransformType.SLIDE_OVER));
        }
        if (!ListenerUtil.mutListener.listen(4829)) {
            mProgressBar = findViewById(R.id.progress_loading);
        }
        if (!ListenerUtil.mutListener.listen(4830)) {
            mAppBarLayout = findViewById(R.id.appbar_main);
        }
        if (!ListenerUtil.mutListener.listen(4831)) {
            // Asynchronously loads comments and build the adapter
            loadDataInViewPager();
        }
        if (!ListenerUtil.mutListener.listen(4833)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(4832)) {
                    // track initial comment view
                    AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_VIEWED, AnalyticsCommentActionSource.SITE_COMMENTS, mSite);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(4834)) {
            outState.putLong(COMMENT_ID_EXTRA, mCommentId);
        }
        if (!ListenerUtil.mutListener.listen(4835)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(4836)) {
            outState.putSerializable(COMMENT_STATUS_FILTER_EXTRA, mStatusFilter);
        }
        if (!ListenerUtil.mutListener.listen(4837)) {
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4838)) {
            mCommentsStoreAdapter.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(4839)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4841)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(4840)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadMore() {
        if (!ListenerUtil.mutListener.listen(4842)) {
            updateComments();
        }
    }

    private void updateComments() {
        if (!ListenerUtil.mutListener.listen(4846)) {
            if (mIsUpdatingComments) {
                if (!ListenerUtil.mutListener.listen(4845)) {
                    AppLog.w(AppLog.T.COMMENTS, "update comments task already running");
                }
                return;
            } else if (!NetworkUtils.isNetworkAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(4844)) {
                    ToastUtils.showToast(this, getString(R.string.error_refresh_comments_showing_older));
                }
                return;
            } else if (!mCanLoadMoreComments) {
                if (!ListenerUtil.mutListener.listen(4843)) {
                    AppLog.w(AppLog.T.COMMENTS, "no more comments to be loaded");
                }
                return;
            }
        }
        final int offset = mAdapter.getCount();
        if (!ListenerUtil.mutListener.listen(4847)) {
            mCommentsStoreAdapter.dispatch(CommentActionBuilder.newFetchCommentsAction(new FetchCommentsPayload(mSite, mStatusFilter, COMMENTS_PER_PAGE, offset)));
        }
        if (!ListenerUtil.mutListener.listen(4848)) {
            mIsUpdatingComments = true;
        }
        if (!ListenerUtil.mutListener.listen(4849)) {
            setLoadingState(true);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCommentChanged(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(4850)) {
            mIsUpdatingComments = false;
        }
        if (!ListenerUtil.mutListener.listen(4851)) {
            setLoadingState(false);
        }
        if (!ListenerUtil.mutListener.listen(4860)) {
            // Don't refresh the list on push, we already updated comments
            if (event.causeOfChange != CommentAction.PUSH_COMMENT) {
                if (!ListenerUtil.mutListener.listen(4859)) {
                    if ((ListenerUtil.mutListener.listen(4856) ? (event.changedCommentsLocalIds.size() >= 0) : (ListenerUtil.mutListener.listen(4855) ? (event.changedCommentsLocalIds.size() <= 0) : (ListenerUtil.mutListener.listen(4854) ? (event.changedCommentsLocalIds.size() < 0) : (ListenerUtil.mutListener.listen(4853) ? (event.changedCommentsLocalIds.size() != 0) : (ListenerUtil.mutListener.listen(4852) ? (event.changedCommentsLocalIds.size() == 0) : (event.changedCommentsLocalIds.size() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(4858)) {
                            loadDataInViewPager();
                        }
                    } else if (!event.isError()) {
                        if (!ListenerUtil.mutListener.listen(4857)) {
                            // There are no more comments to load
                            mCanLoadMoreComments = false;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4863)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(4862)) {
                    if (!TextUtils.isEmpty(event.error.message)) {
                        if (!ListenerUtil.mutListener.listen(4861)) {
                            ToastUtils.showToast(this, event.error.message);
                        }
                    }
                }
            }
        }
    }

    private void loadDataInViewPager() {
        if (!ListenerUtil.mutListener.listen(4870)) {
            if (mIsLoadingComments) {
                if (!ListenerUtil.mutListener.listen(4869)) {
                    AppLog.w(AppLog.T.COMMENTS, "load comments task already active");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4868)) {
                    new LoadCommentsTask(mCommentsStoreAdapter, mStatusFilter, mSite, new LoadCommentsTask.LoadingCallback() {

                        @Override
                        public void isLoading(boolean loading) {
                            if (!ListenerUtil.mutListener.listen(4864)) {
                                setLoadingState(loading);
                            }
                            if (!ListenerUtil.mutListener.listen(4865)) {
                                mIsLoadingComments = loading;
                            }
                        }

                        @Override
                        public void loadingFinished(CommentList commentList) {
                            if (!ListenerUtil.mutListener.listen(4867)) {
                                if (!commentList.isEmpty()) {
                                    if (!ListenerUtil.mutListener.listen(4866)) {
                                        showCommentList(commentList);
                                    }
                                }
                            }
                        }
                    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        }
    }

    private void showCommentList(CommentList commentList) {
        if (!ListenerUtil.mutListener.listen(4871)) {
            if (isFinishing()) {
                return;
            }
        }
        final int previousItem = mViewPager.getCurrentItem();
        if (!ListenerUtil.mutListener.listen(4876)) {
            // Only notify adapter when loading new page
            if ((ListenerUtil.mutListener.listen(4872) ? (mAdapter != null || mAdapter.isAddingNewComments(commentList)) : (mAdapter != null && mAdapter.isAddingNewComments(commentList)))) {
                if (!ListenerUtil.mutListener.listen(4875)) {
                    mAdapter.onNewItems(commentList);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4873)) {
                    // If current items change, rebuild the adapter
                    mAdapter = new CommentDetailFragmentAdapter(getSupportFragmentManager(), commentList, mSite, CommentsDetailActivity.this);
                }
                if (!ListenerUtil.mutListener.listen(4874)) {
                    mViewPager.setAdapter(mAdapter);
                }
            }
        }
        final int commentIndex = mAdapter.commentIndex(mCommentId);
        if (!ListenerUtil.mutListener.listen(4883)) {
            if ((ListenerUtil.mutListener.listen(4881) ? (commentIndex >= 0) : (ListenerUtil.mutListener.listen(4880) ? (commentIndex <= 0) : (ListenerUtil.mutListener.listen(4879) ? (commentIndex > 0) : (ListenerUtil.mutListener.listen(4878) ? (commentIndex != 0) : (ListenerUtil.mutListener.listen(4877) ? (commentIndex == 0) : (commentIndex < 0))))))) {
                if (!ListenerUtil.mutListener.listen(4882)) {
                    showErrorToastAndFinish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4890)) {
            if (mOnPageChangeListener != null) {
                if (!ListenerUtil.mutListener.listen(4889)) {
                    mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4888)) {
                    mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

                        @Override
                        public void onPageSelected(int position) {
                            if (!ListenerUtil.mutListener.listen(4884)) {
                                super.onPageSelected(position);
                            }
                            final CommentModel comment = mAdapter.getCommentAtPosition(position);
                            if (!ListenerUtil.mutListener.listen(4887)) {
                                if (comment != null) {
                                    if (!ListenerUtil.mutListener.listen(4885)) {
                                        mCommentId = comment.getRemoteCommentId();
                                    }
                                    if (!ListenerUtil.mutListener.listen(4886)) {
                                        // track subsequent comment views
                                        AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_VIEWED, AnalyticsCommentActionSource.SITE_COMMENTS, mSite);
                                    }
                                }
                            }
                        }
                    };
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4897)) {
            if ((ListenerUtil.mutListener.listen(4895) ? (commentIndex >= previousItem) : (ListenerUtil.mutListener.listen(4894) ? (commentIndex <= previousItem) : (ListenerUtil.mutListener.listen(4893) ? (commentIndex > previousItem) : (ListenerUtil.mutListener.listen(4892) ? (commentIndex < previousItem) : (ListenerUtil.mutListener.listen(4891) ? (commentIndex == previousItem) : (commentIndex != previousItem))))))) {
                if (!ListenerUtil.mutListener.listen(4896)) {
                    mViewPager.setCurrentItem(commentIndex);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4898)) {
            mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        }
    }

    private void showErrorToastAndFinish() {
        if (!ListenerUtil.mutListener.listen(4899)) {
            AppLog.e(AppLog.T.COMMENTS, "Comment could not be found.");
        }
        if (!ListenerUtil.mutListener.listen(4900)) {
            ToastUtils.showToast(this, R.string.error_load_comment);
        }
        if (!ListenerUtil.mutListener.listen(4901)) {
            finish();
        }
    }

    private void setLoadingState(boolean visible) {
        if (!ListenerUtil.mutListener.listen(4903)) {
            if (mProgressBar != null) {
                if (!ListenerUtil.mutListener.listen(4902)) {
                    mProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
                }
            }
        }
    }

    @Override
    public void onModerateComment(final SiteModel site, final CommentModel comment, final CommentStatus newStatus) {
        Intent resultIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(4904)) {
            resultIntent.putExtra(CommentConstants.COMMENT_MODERATE_ID_EXTRA, comment.getRemoteCommentId());
        }
        if (!ListenerUtil.mutListener.listen(4905)) {
            resultIntent.putExtra(CommentConstants.COMMENT_MODERATE_STATUS_EXTRA, newStatus.toString());
        }
        if (!ListenerUtil.mutListener.listen(4906)) {
            setResult(RESULT_OK, resultIntent);
        }
        if (!ListenerUtil.mutListener.listen(4907)) {
            finish();
        }
    }

    @Override
    public void onScrollableViewInitialized(int containerId) {
        if (!ListenerUtil.mutListener.listen(4908)) {
            mAppBarLayout.setLiftOnScrollTargetViewId(containerId);
        }
    }
}
