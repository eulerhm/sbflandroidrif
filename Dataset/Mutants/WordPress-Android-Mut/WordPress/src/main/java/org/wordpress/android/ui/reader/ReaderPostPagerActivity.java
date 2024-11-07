package org.wordpress.android.ui.reader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.PostStore.OnPostUploaded;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.WPLaunchActivity;
import org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction.OpenInReader;
import org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderPostActions;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostId;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostIdList;
import org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.tracker.ReaderTrackerType;
import org.wordpress.android.ui.reader.utils.ReaderPostSeenStatusWrapper;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtils;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UriWrapper;
import org.wordpress.android.util.UrlUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
import org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig;
import org.wordpress.android.widgets.WPSwipeSnackbar;
import org.wordpress.android.widgets.WPViewPager;
import org.wordpress.android.widgets.WPViewPagerTransformer;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * shows reader post detail fragments in a ViewPager - primarily used for easy swiping between
 * posts with a specific tag or in a specific blog, but can also be used to show a single
 * post detail.
 *
 * It also displays intercepted WordPress.com URls in the following forms
 *
 * http[s]://wordpress.com/read/blogs/{blogId}/posts/{postId}
 * http[s]://wordpress.com/read/feeds/{feedId}/posts/{feedItemId}
 * http[s]://{username}.wordpress.com/{year}/{month}/{day}/{postSlug}
 *
 * Will also handle jumping to the comments section, liking a commend and liking a post directly
 */
public class ReaderPostPagerActivity extends LocaleAwareActivity {

    /**
     * Type of URL intercepted
     */
    private enum InterceptType {

        READER_BLOG, READER_FEED, WPCOM_POST_SLUG
    }

    /**
     * operation to perform automatically when opened via deeplinking
     */
    public enum DirectOperation {

        COMMENT_JUMP, COMMENT_REPLY, COMMENT_LIKE, POST_LIKE
    }

    private WPViewPager mViewPager;

    private ProgressBar mProgress;

    private ReaderTag mCurrentTag;

    private boolean mIsFeed;

    private long mBlogId;

    private long mPostId;

    private int mCommentId;

    private DirectOperation mDirectOperation;

    private String mInterceptedUri;

    private int mLastSelectedPosition = -1;

    private ReaderPostListType mPostListType;

    private boolean mPostSlugsResolutionUnderway;

    private boolean mIsRequestingMorePosts;

    private boolean mIsSinglePostView;

    private boolean mIsRelatedPostView;

    private boolean mBackFromLogin;

    private final HashSet<Integer> mTrackedPositions = new HashSet<>();

    @Inject
    SiteStore mSiteStore;

    @Inject
    ReaderTracker mReaderTracker;

    @Inject
    AnalyticsUtilsWrapper mAnalyticsUtilsWrapper;

    @Inject
    ReaderPostTableWrapper mReaderPostTableWrapper;

    @Inject
    PostStore mPostStore;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    UploadActionUseCase mUploadActionUseCase;

    @Inject
    UploadUtilsWrapper mUploadUtilsWrapper;

    @Inject
    ReaderPostSeenStatusWrapper mPostSeenStatusWrapper;

    @Inject
    SeenUnseenWithCounterFeatureConfig mSeenUnseenWithCounterFeatureConfig;

    @Inject
    UrlUtilsWrapper mUrlUtilsWrapper;

    @Inject
    DeepLinkTrackingUtils mDeepLinkTrackingUtils;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(21961)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(21962)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(21963)) {
            setContentView(R.layout.reader_activity_post_pager);
        }
        if (!ListenerUtil.mutListener.listen(21964)) {
            mViewPager = findViewById(R.id.viewpager);
        }
        if (!ListenerUtil.mutListener.listen(21965)) {
            mProgress = findViewById(R.id.progress_loading);
        }
        if (!ListenerUtil.mutListener.listen(21994)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(21978)) {
                    mIsFeed = savedInstanceState.getBoolean(ReaderConstants.ARG_IS_FEED);
                }
                if (!ListenerUtil.mutListener.listen(21979)) {
                    mBlogId = savedInstanceState.getLong(ReaderConstants.ARG_BLOG_ID);
                }
                if (!ListenerUtil.mutListener.listen(21980)) {
                    mPostId = savedInstanceState.getLong(ReaderConstants.ARG_POST_ID);
                }
                if (!ListenerUtil.mutListener.listen(21981)) {
                    mDirectOperation = (DirectOperation) savedInstanceState.getSerializable(ReaderConstants.ARG_DIRECT_OPERATION);
                }
                if (!ListenerUtil.mutListener.listen(21982)) {
                    mCommentId = savedInstanceState.getInt(ReaderConstants.ARG_COMMENT_ID);
                }
                if (!ListenerUtil.mutListener.listen(21983)) {
                    mIsSinglePostView = savedInstanceState.getBoolean(ReaderConstants.ARG_IS_SINGLE_POST);
                }
                if (!ListenerUtil.mutListener.listen(21984)) {
                    mIsRelatedPostView = savedInstanceState.getBoolean(ReaderConstants.ARG_IS_RELATED_POST);
                }
                if (!ListenerUtil.mutListener.listen(21985)) {
                    mInterceptedUri = savedInstanceState.getString(ReaderConstants.ARG_INTERCEPTED_URI);
                }
                if (!ListenerUtil.mutListener.listen(21987)) {
                    if (savedInstanceState.containsKey(ReaderConstants.ARG_POST_LIST_TYPE)) {
                        if (!ListenerUtil.mutListener.listen(21986)) {
                            mPostListType = (ReaderPostListType) savedInstanceState.getSerializable(ReaderConstants.ARG_POST_LIST_TYPE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(21989)) {
                    if (savedInstanceState.containsKey(ReaderConstants.ARG_TAG)) {
                        if (!ListenerUtil.mutListener.listen(21988)) {
                            mCurrentTag = (ReaderTag) savedInstanceState.getSerializable(ReaderConstants.ARG_TAG);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(21990)) {
                    mPostSlugsResolutionUnderway = savedInstanceState.getBoolean(ReaderConstants.KEY_POST_SLUGS_RESOLUTION_UNDERWAY);
                }
                if (!ListenerUtil.mutListener.listen(21993)) {
                    if (savedInstanceState.containsKey(ReaderConstants.KEY_TRACKED_POSITIONS)) {
                        Serializable positions = savedInstanceState.getSerializable(ReaderConstants.KEY_TRACKED_POSITIONS);
                        if (!ListenerUtil.mutListener.listen(21992)) {
                            if (positions instanceof HashSet) {
                                if (!ListenerUtil.mutListener.listen(21991)) {
                                    mTrackedPositions.addAll((HashSet<Integer>) positions);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(21966)) {
                    mIsFeed = getIntent().getBooleanExtra(ReaderConstants.ARG_IS_FEED, false);
                }
                if (!ListenerUtil.mutListener.listen(21967)) {
                    mBlogId = getIntent().getLongExtra(ReaderConstants.ARG_BLOG_ID, 0);
                }
                if (!ListenerUtil.mutListener.listen(21968)) {
                    mPostId = getIntent().getLongExtra(ReaderConstants.ARG_POST_ID, 0);
                }
                if (!ListenerUtil.mutListener.listen(21969)) {
                    mDirectOperation = (DirectOperation) getIntent().getSerializableExtra(ReaderConstants.ARG_DIRECT_OPERATION);
                }
                if (!ListenerUtil.mutListener.listen(21970)) {
                    mCommentId = getIntent().getIntExtra(ReaderConstants.ARG_COMMENT_ID, 0);
                }
                if (!ListenerUtil.mutListener.listen(21971)) {
                    mIsSinglePostView = getIntent().getBooleanExtra(ReaderConstants.ARG_IS_SINGLE_POST, false);
                }
                if (!ListenerUtil.mutListener.listen(21972)) {
                    mIsRelatedPostView = getIntent().getBooleanExtra(ReaderConstants.ARG_IS_RELATED_POST, false);
                }
                if (!ListenerUtil.mutListener.listen(21973)) {
                    mInterceptedUri = getIntent().getStringExtra(ReaderConstants.ARG_INTERCEPTED_URI);
                }
                if (!ListenerUtil.mutListener.listen(21975)) {
                    if (getIntent().hasExtra(ReaderConstants.ARG_POST_LIST_TYPE)) {
                        if (!ListenerUtil.mutListener.listen(21974)) {
                            mPostListType = (ReaderPostListType) getIntent().getSerializableExtra(ReaderConstants.ARG_POST_LIST_TYPE);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(21977)) {
                    if (getIntent().hasExtra(ReaderConstants.ARG_TAG)) {
                        if (!ListenerUtil.mutListener.listen(21976)) {
                            mCurrentTag = (ReaderTag) getIntent().getSerializableExtra(ReaderConstants.ARG_TAG);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(21996)) {
            if (mPostListType == null) {
                if (!ListenerUtil.mutListener.listen(21995)) {
                    mPostListType = ReaderPostListType.TAG_FOLLOWED;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22011)) {
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(21997)) {
                        super.onPageSelected(position);
                    }
                    if (!ListenerUtil.mutListener.listen(21998)) {
                        trackPostAtPositionIfNeeded(position);
                    }
                    if (!ListenerUtil.mutListener.listen(22007)) {
                        if ((ListenerUtil.mutListener.listen(22004) ? ((ListenerUtil.mutListener.listen(22003) ? (mLastSelectedPosition >= -1) : (ListenerUtil.mutListener.listen(22002) ? (mLastSelectedPosition <= -1) : (ListenerUtil.mutListener.listen(22001) ? (mLastSelectedPosition < -1) : (ListenerUtil.mutListener.listen(22000) ? (mLastSelectedPosition != -1) : (ListenerUtil.mutListener.listen(21999) ? (mLastSelectedPosition == -1) : (mLastSelectedPosition > -1)))))) || mLastSelectedPosition != position) : ((ListenerUtil.mutListener.listen(22003) ? (mLastSelectedPosition >= -1) : (ListenerUtil.mutListener.listen(22002) ? (mLastSelectedPosition <= -1) : (ListenerUtil.mutListener.listen(22001) ? (mLastSelectedPosition < -1) : (ListenerUtil.mutListener.listen(22000) ? (mLastSelectedPosition != -1) : (ListenerUtil.mutListener.listen(21999) ? (mLastSelectedPosition == -1) : (mLastSelectedPosition > -1)))))) && mLastSelectedPosition != position))) {
                            // will continue to play
                            ReaderPostDetailFragment lastFragment = getDetailFragmentAtPosition(mLastSelectedPosition);
                            if (!ListenerUtil.mutListener.listen(22006)) {
                                if (lastFragment != null) {
                                    if (!ListenerUtil.mutListener.listen(22005)) {
                                        lastFragment.pauseWebView();
                                    }
                                }
                            }
                        }
                    }
                    // resume the newly active webView if it was previously paused
                    ReaderPostDetailFragment thisFragment = getDetailFragmentAtPosition(position);
                    if (!ListenerUtil.mutListener.listen(22009)) {
                        if (thisFragment != null) {
                            if (!ListenerUtil.mutListener.listen(22008)) {
                                thisFragment.resumeWebViewIfPaused();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22010)) {
                        mLastSelectedPosition = position;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(22012)) {
            mViewPager.setPageTransformer(false, new WPViewPagerTransformer(WPViewPagerTransformer.TransformType.SLIDE_OVER));
        }
    }

    private void handleDeepLinking() {
        String action = getIntent().getAction();
        Uri uri = getIntent().getData();
        String host = "";
        if (!ListenerUtil.mutListener.listen(22014)) {
            if (uri != null) {
                if (!ListenerUtil.mutListener.listen(22013)) {
                    host = uri.getHost();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22019)) {
            if (uri == null) {
                if (!ListenerUtil.mutListener.listen(22015)) {
                    mReaderTracker.trackDeepLink(AnalyticsTracker.Stat.DEEP_LINKED, action, host, uri);
                }
                // invalid uri so, just show the entry screen
                Intent intent = new Intent(this, WPLaunchActivity.class);
                if (!ListenerUtil.mutListener.listen(22016)) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if (!ListenerUtil.mutListener.listen(22017)) {
                    startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(22018)) {
                    finish();
                }
                return;
            }
        }
        InterceptType interceptType = InterceptType.READER_BLOG;
        // can be an id or a slug
        String blogIdentifier = null;
        // can be an id or a slug
        String postIdentifier = null;
        if (!ListenerUtil.mutListener.listen(22020)) {
            mInterceptedUri = uri.toString();
        }
        List<String> segments = uri.getPathSegments();
        if (!ListenerUtil.mutListener.listen(22058)) {
            // with the first segment being 'read'.
            if (segments != null) {
                // Builds stripped URI for tracking purposes
                UriWrapper wrappedUri = new UriWrapper(uri);
                if (!ListenerUtil.mutListener.listen(22057)) {
                    if (segments.get(0).equals("read")) {
                        if (!ListenerUtil.mutListener.listen(22045)) {
                            if ((ListenerUtil.mutListener.listen(22039) ? (segments.size() >= 2) : (ListenerUtil.mutListener.listen(22038) ? (segments.size() <= 2) : (ListenerUtil.mutListener.listen(22037) ? (segments.size() < 2) : (ListenerUtil.mutListener.listen(22036) ? (segments.size() != 2) : (ListenerUtil.mutListener.listen(22035) ? (segments.size() == 2) : (segments.size() > 2))))))) {
                                if (!ListenerUtil.mutListener.listen(22040)) {
                                    blogIdentifier = segments.get(2);
                                }
                                if (!ListenerUtil.mutListener.listen(22044)) {
                                    if (segments.get(1).equals("blogs")) {
                                        if (!ListenerUtil.mutListener.listen(22043)) {
                                            interceptType = InterceptType.READER_BLOG;
                                        }
                                    } else if (segments.get(1).equals("feeds")) {
                                        if (!ListenerUtil.mutListener.listen(22041)) {
                                            interceptType = InterceptType.READER_FEED;
                                        }
                                        if (!ListenerUtil.mutListener.listen(22042)) {
                                            mIsFeed = true;
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22053)) {
                            if ((ListenerUtil.mutListener.listen(22051) ? ((ListenerUtil.mutListener.listen(22050) ? (segments.size() >= 4) : (ListenerUtil.mutListener.listen(22049) ? (segments.size() <= 4) : (ListenerUtil.mutListener.listen(22048) ? (segments.size() < 4) : (ListenerUtil.mutListener.listen(22047) ? (segments.size() != 4) : (ListenerUtil.mutListener.listen(22046) ? (segments.size() == 4) : (segments.size() > 4)))))) || segments.get(3).equals("posts")) : ((ListenerUtil.mutListener.listen(22050) ? (segments.size() >= 4) : (ListenerUtil.mutListener.listen(22049) ? (segments.size() <= 4) : (ListenerUtil.mutListener.listen(22048) ? (segments.size() < 4) : (ListenerUtil.mutListener.listen(22047) ? (segments.size() != 4) : (ListenerUtil.mutListener.listen(22046) ? (segments.size() == 4) : (segments.size() > 4)))))) && segments.get(3).equals("posts")))) {
                                if (!ListenerUtil.mutListener.listen(22052)) {
                                    postIdentifier = segments.get(4);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22054)) {
                            parseFragment(uri);
                        }
                        if (!ListenerUtil.mutListener.listen(22055)) {
                            mDeepLinkTrackingUtils.track(action, new OpenInReader(wrappedUri), wrappedUri);
                        }
                        if (!ListenerUtil.mutListener.listen(22056)) {
                            showPost(interceptType, blogIdentifier, postIdentifier);
                        }
                        return;
                    } else if ((ListenerUtil.mutListener.listen(22025) ? (segments.size() <= 4) : (ListenerUtil.mutListener.listen(22024) ? (segments.size() > 4) : (ListenerUtil.mutListener.listen(22023) ? (segments.size() < 4) : (ListenerUtil.mutListener.listen(22022) ? (segments.size() != 4) : (ListenerUtil.mutListener.listen(22021) ? (segments.size() == 4) : (segments.size() >= 4))))))) {
                        if (!ListenerUtil.mutListener.listen(22026)) {
                            blogIdentifier = uri.getHost();
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(22029)) {
                                postIdentifier = URLEncoder.encode(segments.get(3), "UTF-8");
                            }
                        } catch (UnsupportedEncodingException e) {
                            if (!ListenerUtil.mutListener.listen(22027)) {
                                AppLog.e(AppLog.T.READER, e);
                            }
                            if (!ListenerUtil.mutListener.listen(22028)) {
                                ToastUtils.showToast(this, R.string.error_generic);
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(22030)) {
                            parseFragment(uri);
                        }
                        if (!ListenerUtil.mutListener.listen(22031)) {
                            detectLike(uri);
                        }
                        if (!ListenerUtil.mutListener.listen(22032)) {
                            interceptType = InterceptType.WPCOM_POST_SLUG;
                        }
                        if (!ListenerUtil.mutListener.listen(22033)) {
                            mDeepLinkTrackingUtils.track(action, new OpenInReader(wrappedUri), wrappedUri);
                        }
                        if (!ListenerUtil.mutListener.listen(22034)) {
                            showPost(interceptType, blogIdentifier, postIdentifier);
                        }
                        return;
                    }
                }
            }
        }
        // at this point, just show the entry screen
        Intent intent = new Intent(this, WPLaunchActivity.class);
        if (!ListenerUtil.mutListener.listen(22059)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(22060)) {
            startActivity(intent);
        }
    }

    private void showPost(@NonNull InterceptType interceptType, final String blogIdentifier, final String postIdentifier) {
        if (!ListenerUtil.mutListener.listen(22084)) {
            if ((ListenerUtil.mutListener.listen(22061) ? (!TextUtils.isEmpty(blogIdentifier) || !TextUtils.isEmpty(postIdentifier)) : (!TextUtils.isEmpty(blogIdentifier) && !TextUtils.isEmpty(postIdentifier)))) {
                if (!ListenerUtil.mutListener.listen(22063)) {
                    mIsSinglePostView = true;
                }
                if (!ListenerUtil.mutListener.listen(22064)) {
                    mIsRelatedPostView = false;
                }
                if (!ListenerUtil.mutListener.listen(22083)) {
                    switch(interceptType) {
                        case READER_BLOG:
                            if (!ListenerUtil.mutListener.listen(22067)) {
                                if (parseIds(blogIdentifier, postIdentifier)) {
                                    if (!ListenerUtil.mutListener.listen(22066)) {
                                        mReaderTracker.trackBlogPost(AnalyticsTracker.Stat.READER_BLOG_POST_INTERCEPTED, mBlogId, mPostId);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(22065)) {
                                        ToastUtils.showToast(this, R.string.error_generic);
                                    }
                                }
                            }
                            break;
                        case READER_FEED:
                            if (!ListenerUtil.mutListener.listen(22070)) {
                                if (parseIds(blogIdentifier, postIdentifier)) {
                                    if (!ListenerUtil.mutListener.listen(22069)) {
                                        mReaderTracker.trackFeedPost(AnalyticsTracker.Stat.READER_FEED_POST_INTERCEPTED, mBlogId, mPostId);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(22068)) {
                                        ToastUtils.showToast(this, R.string.error_generic);
                                    }
                                }
                            }
                            break;
                        case WPCOM_POST_SLUG:
                            if (!ListenerUtil.mutListener.listen(22071)) {
                                mReaderTracker.trackBlogPost(AnalyticsTracker.Stat.READER_WPCOM_BLOG_POST_INTERCEPTED, blogIdentifier, postIdentifier, mCommentId);
                            }
                            // try to get the post from the local db
                            ReaderPost post = ReaderPostTable.getBlogPost(blogIdentifier, postIdentifier, true);
                            if (!ListenerUtil.mutListener.listen(22082)) {
                                if (post != null) {
                                    if (!ListenerUtil.mutListener.listen(22080)) {
                                        // set the IDs and let ReaderPostPagerActivity normally display the post
                                        mBlogId = post.blogId;
                                    }
                                    if (!ListenerUtil.mutListener.listen(22081)) {
                                        mPostId = post.postId;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(22078)) {
                                        // not stored locally, so request it
                                        ReaderPostActions.requestBlogPost(blogIdentifier, postIdentifier, new ReaderActions.OnRequestListener<String>() {

                                            @Override
                                            public void onSuccess(String blogUrl) {
                                                if (!ListenerUtil.mutListener.listen(22072)) {
                                                    mPostSlugsResolutionUnderway = false;
                                                }
                                                // .getBlogPost
                                                String primaryBlogIdentifier = mUrlUtilsWrapper.removeScheme(blogUrl);
                                                // in the ReaderPostTable query.
                                                ReaderPost post = ReaderPostTable.getBlogPost(primaryBlogIdentifier, postIdentifier, true);
                                                ReaderEvents.PostSlugsRequestCompleted slugsResolved = (post != null) ? new ReaderEvents.PostSlugsRequestCompleted(200, post.blogId, post.postId) : new ReaderEvents.PostSlugsRequestCompleted(200, 0, 0);
                                                if (!ListenerUtil.mutListener.listen(22073)) {
                                                    // notify that the slug resolution request has completed
                                                    EventBus.getDefault().post(slugsResolved);
                                                }
                                                if (!ListenerUtil.mutListener.listen(22075)) {
                                                    // post wasn't available locally earlier so, track it now
                                                    if (post != null) {
                                                        if (!ListenerUtil.mutListener.listen(22074)) {
                                                            trackPost(post.blogId, post.postId);
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(int statusCode) {
                                                if (!ListenerUtil.mutListener.listen(22076)) {
                                                    mPostSlugsResolutionUnderway = false;
                                                }
                                                if (!ListenerUtil.mutListener.listen(22077)) {
                                                    // notify that the slug resolution request has completed
                                                    EventBus.getDefault().post(new ReaderEvents.PostSlugsRequestCompleted(statusCode, 0, 0));
                                                }
                                            }
                                        });
                                    }
                                    if (!ListenerUtil.mutListener.listen(22079)) {
                                        mPostSlugsResolutionUnderway = true;
                                    }
                                }
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22062)) {
                    ToastUtils.showToast(this, R.string.error_generic);
                }
            }
        }
    }

    private boolean parseIds(String blogIdentifier, String postIdentifier) {
        try {
            if (!ListenerUtil.mutListener.listen(22086)) {
                mBlogId = Long.parseLong(blogIdentifier);
            }
            if (!ListenerUtil.mutListener.listen(22087)) {
                mPostId = Long.parseLong(postIdentifier);
            }
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(22085)) {
                AppLog.e(AppLog.T.READER, e);
            }
            return false;
        }
        return true;
    }

    /**
     * Parse the URL fragment and interpret it as an operation to perform. For example, a "#comments" fragment is
     * interpreted as a direct jump into the comments section of the post.
     *
     * @param uri the full URI input, including the fragment
     */
    private void parseFragment(Uri uri) {
        if (!ListenerUtil.mutListener.listen(22088)) {
            // default to do-nothing w.r.t. comments
            mDirectOperation = null;
        }
        if (!ListenerUtil.mutListener.listen(22090)) {
            if ((ListenerUtil.mutListener.listen(22089) ? (uri == null && uri.getFragment() == null) : (uri == null || uri.getFragment() == null))) {
                return;
            }
        }
        final String fragment = uri.getFragment();
        final Pattern fragmentCommentsPattern = Pattern.compile("comments", Pattern.CASE_INSENSITIVE);
        final Pattern fragmentCommentIdPattern = Pattern.compile("comment-(\\d+)", Pattern.CASE_INSENSITIVE);
        final Pattern fragmentRespondPattern = Pattern.compile("respond", Pattern.CASE_INSENSITIVE);
        // check for the general "#comments" fragment to jump to the comments section
        Matcher commentsMatcher = fragmentCommentsPattern.matcher(fragment);
        if (!ListenerUtil.mutListener.listen(22093)) {
            if (commentsMatcher.matches()) {
                if (!ListenerUtil.mutListener.listen(22091)) {
                    mDirectOperation = DirectOperation.COMMENT_JUMP;
                }
                if (!ListenerUtil.mutListener.listen(22092)) {
                    mCommentId = 0;
                }
                return;
            }
        }
        // check for the "#respond" fragment to jump to the reply box
        Matcher respondMatcher = fragmentRespondPattern.matcher(fragment);
        if (!ListenerUtil.mutListener.listen(22098)) {
            if (respondMatcher.matches()) {
                if (!ListenerUtil.mutListener.listen(22094)) {
                    mDirectOperation = DirectOperation.COMMENT_REPLY;
                }
                // check whether we are to reply to a specific comment
                final String replyToCommentId = uri.getQueryParameter("replytocom");
                if (!ListenerUtil.mutListener.listen(22097)) {
                    if (replyToCommentId != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(22096)) {
                                mCommentId = Integer.parseInt(replyToCommentId);
                            }
                        } catch (NumberFormatException e) {
                            if (!ListenerUtil.mutListener.listen(22095)) {
                                AppLog.e(AppLog.T.UTILS, "replytocom cannot be converted to int" + replyToCommentId, e);
                            }
                        }
                    }
                }
                return;
            }
        }
        // check for the "#comment-xyz" fragment to jump to a specific comment
        Matcher commentIdMatcher = fragmentCommentIdPattern.matcher(fragment);
        if (!ListenerUtil.mutListener.listen(22107)) {
            if ((ListenerUtil.mutListener.listen(22104) ? (commentIdMatcher.find() || (ListenerUtil.mutListener.listen(22103) ? (commentIdMatcher.groupCount() >= 0) : (ListenerUtil.mutListener.listen(22102) ? (commentIdMatcher.groupCount() <= 0) : (ListenerUtil.mutListener.listen(22101) ? (commentIdMatcher.groupCount() < 0) : (ListenerUtil.mutListener.listen(22100) ? (commentIdMatcher.groupCount() != 0) : (ListenerUtil.mutListener.listen(22099) ? (commentIdMatcher.groupCount() == 0) : (commentIdMatcher.groupCount() > 0))))))) : (commentIdMatcher.find() && (ListenerUtil.mutListener.listen(22103) ? (commentIdMatcher.groupCount() >= 0) : (ListenerUtil.mutListener.listen(22102) ? (commentIdMatcher.groupCount() <= 0) : (ListenerUtil.mutListener.listen(22101) ? (commentIdMatcher.groupCount() < 0) : (ListenerUtil.mutListener.listen(22100) ? (commentIdMatcher.groupCount() != 0) : (ListenerUtil.mutListener.listen(22099) ? (commentIdMatcher.groupCount() == 0) : (commentIdMatcher.groupCount() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(22105)) {
                    mCommentId = Integer.valueOf(commentIdMatcher.group(1));
                }
                if (!ListenerUtil.mutListener.listen(22106)) {
                    mDirectOperation = DirectOperation.COMMENT_JUMP;
                }
            }
        }
    }

    /**
     * Parse the URL query parameters and detect attempt to like a post or a comment
     *
     * @param uri the full URI input, including the query parameters
     */
    private void detectLike(Uri uri) {
        // check whether we are to like something
        final boolean doLike = "1".equals(uri.getQueryParameter("like"));
        final String likeActor = uri.getQueryParameter("like_actor");
        if (!ListenerUtil.mutListener.listen(22120)) {
            if ((ListenerUtil.mutListener.listen(22114) ? ((ListenerUtil.mutListener.listen(22108) ? (doLike || likeActor != null) : (doLike && likeActor != null)) || (ListenerUtil.mutListener.listen(22113) ? (likeActor.trim().length() >= 0) : (ListenerUtil.mutListener.listen(22112) ? (likeActor.trim().length() <= 0) : (ListenerUtil.mutListener.listen(22111) ? (likeActor.trim().length() < 0) : (ListenerUtil.mutListener.listen(22110) ? (likeActor.trim().length() != 0) : (ListenerUtil.mutListener.listen(22109) ? (likeActor.trim().length() == 0) : (likeActor.trim().length() > 0))))))) : ((ListenerUtil.mutListener.listen(22108) ? (doLike || likeActor != null) : (doLike && likeActor != null)) && (ListenerUtil.mutListener.listen(22113) ? (likeActor.trim().length() >= 0) : (ListenerUtil.mutListener.listen(22112) ? (likeActor.trim().length() <= 0) : (ListenerUtil.mutListener.listen(22111) ? (likeActor.trim().length() < 0) : (ListenerUtil.mutListener.listen(22110) ? (likeActor.trim().length() != 0) : (ListenerUtil.mutListener.listen(22109) ? (likeActor.trim().length() == 0) : (likeActor.trim().length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(22115)) {
                    mDirectOperation = DirectOperation.POST_LIKE;
                }
                // check whether we are to like a specific comment
                final String likeCommentId = uri.getQueryParameter("commentid");
                if (!ListenerUtil.mutListener.listen(22119)) {
                    if (likeCommentId != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(22117)) {
                                mCommentId = Integer.parseInt(likeCommentId);
                            }
                            if (!ListenerUtil.mutListener.listen(22118)) {
                                mDirectOperation = DirectOperation.COMMENT_LIKE;
                            }
                        } catch (NumberFormatException e) {
                            if (!ListenerUtil.mutListener.listen(22116)) {
                                AppLog.e(AppLog.T.UTILS, "commentid cannot be converted to int" + likeCommentId, e);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(22121)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(22122)) {
            AppLog.d(T.READER, "TRACK READER ReaderPostPagerActivity > START Count");
        }
        if (!ListenerUtil.mutListener.listen(22123)) {
            mReaderTracker.start(ReaderTrackerType.PAGED_POST);
        }
        if (!ListenerUtil.mutListener.listen(22124)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(22125)) {
            // We register the dispatcher in order to receive the OnPostUploaded event and show the snackbar
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(22132)) {
            if ((ListenerUtil.mutListener.listen(22126) ? (!hasPagerAdapter() && mBackFromLogin) : (!hasPagerAdapter() || mBackFromLogin))) {
                if (!ListenerUtil.mutListener.listen(22129)) {
                    if ((ListenerUtil.mutListener.listen(22127) ? (ActivityUtils.isDeepLinking(getIntent()) && ReaderConstants.ACTION_VIEW_POST.equals(getIntent().getAction())) : (ActivityUtils.isDeepLinking(getIntent()) || ReaderConstants.ACTION_VIEW_POST.equals(getIntent().getAction())))) {
                        if (!ListenerUtil.mutListener.listen(22128)) {
                            handleDeepLinking();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(22130)) {
                    loadPosts(mBlogId, mPostId);
                }
                if (!ListenerUtil.mutListener.listen(22131)) {
                    // clear up the back-from-login flag anyway
                    mBackFromLogin = false;
                }
            }
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(22133)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(22134)) {
            AppLog.d(T.READER, "TRACK READER ReaderPostPagerActivity > STOP Count");
        }
        if (!ListenerUtil.mutListener.listen(22135)) {
            mReaderTracker.stop(ReaderTrackerType.PAGED_POST);
        }
        if (!ListenerUtil.mutListener.listen(22136)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(22137)) {
            mDispatcher.unregister(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (!ListenerUtil.mutListener.listen(22139)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(22138)) {
                    finish();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean hasPagerAdapter() {
        return ((ListenerUtil.mutListener.listen(22140) ? (mViewPager != null || mViewPager.getAdapter() != null) : (mViewPager != null && mViewPager.getAdapter() != null)));
    }

    private PostPagerAdapter getPagerAdapter() {
        if ((ListenerUtil.mutListener.listen(22141) ? (mViewPager != null || mViewPager.getAdapter() != null) : (mViewPager != null && mViewPager.getAdapter() != null))) {
            return (PostPagerAdapter) mViewPager.getAdapter();
        } else {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (!ListenerUtil.mutListener.listen(22142)) {
            outState.putBoolean(ReaderConstants.ARG_IS_SINGLE_POST, mIsSinglePostView);
        }
        if (!ListenerUtil.mutListener.listen(22143)) {
            outState.putBoolean(ReaderConstants.ARG_IS_RELATED_POST, mIsRelatedPostView);
        }
        if (!ListenerUtil.mutListener.listen(22144)) {
            outState.putString(ReaderConstants.ARG_INTERCEPTED_URI, mInterceptedUri);
        }
        if (!ListenerUtil.mutListener.listen(22145)) {
            outState.putSerializable(ReaderConstants.ARG_DIRECT_OPERATION, mDirectOperation);
        }
        if (!ListenerUtil.mutListener.listen(22146)) {
            outState.putInt(ReaderConstants.ARG_COMMENT_ID, mCommentId);
        }
        if (!ListenerUtil.mutListener.listen(22148)) {
            if (hasCurrentTag()) {
                if (!ListenerUtil.mutListener.listen(22147)) {
                    outState.putSerializable(ReaderConstants.ARG_TAG, getCurrentTag());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22150)) {
            if (getPostListType() != null) {
                if (!ListenerUtil.mutListener.listen(22149)) {
                    outState.putSerializable(ReaderConstants.ARG_POST_LIST_TYPE, getPostListType());
                }
            }
        }
        ReaderBlogIdPostId id = getAdapterCurrentBlogIdPostId();
        if (!ListenerUtil.mutListener.listen(22153)) {
            if (id != null) {
                if (!ListenerUtil.mutListener.listen(22151)) {
                    outState.putLong(ReaderConstants.ARG_BLOG_ID, id.getBlogId());
                }
                if (!ListenerUtil.mutListener.listen(22152)) {
                    outState.putLong(ReaderConstants.ARG_POST_ID, id.getPostId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22154)) {
            outState.putBoolean(ReaderConstants.KEY_POST_SLUGS_RESOLUTION_UNDERWAY, mPostSlugsResolutionUnderway);
        }
        if (!ListenerUtil.mutListener.listen(22161)) {
            if ((ListenerUtil.mutListener.listen(22159) ? (mTrackedPositions.size() >= 0) : (ListenerUtil.mutListener.listen(22158) ? (mTrackedPositions.size() <= 0) : (ListenerUtil.mutListener.listen(22157) ? (mTrackedPositions.size() < 0) : (ListenerUtil.mutListener.listen(22156) ? (mTrackedPositions.size() != 0) : (ListenerUtil.mutListener.listen(22155) ? (mTrackedPositions.size() == 0) : (mTrackedPositions.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(22160)) {
                    outState.putSerializable(ReaderConstants.KEY_TRACKED_POSITIONS, mTrackedPositions);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22162)) {
            super.onSaveInstanceState(outState);
        }
    }

    private ReaderBlogIdPostId getAdapterCurrentBlogIdPostId() {
        PostPagerAdapter adapter = getPagerAdapter();
        if (!ListenerUtil.mutListener.listen(22163)) {
            if (adapter == null) {
                return null;
            }
        }
        return adapter.getCurrentBlogIdPostId();
    }

    private ReaderBlogIdPostId getAdapterBlogIdPostIdAtPosition(int position) {
        PostPagerAdapter adapter = getPagerAdapter();
        if (!ListenerUtil.mutListener.listen(22164)) {
            if (adapter == null) {
                return null;
            }
        }
        return adapter.getBlogIdPostIdAtPosition(position);
    }

    @Override
    public void onBackPressed() {
        ReaderPostDetailFragment fragment = getActiveDetailFragment();
        if (!ListenerUtil.mutListener.listen(22170)) {
            if ((ListenerUtil.mutListener.listen(22165) ? (fragment != null || fragment.isCustomViewShowing()) : (fragment != null && fragment.isCustomViewShowing()))) {
                if (!ListenerUtil.mutListener.listen(22169)) {
                    // if full screen video is showing, hide the custom view rather than navigate back
                    fragment.hideCustomView();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22168)) {
                    if ((ListenerUtil.mutListener.listen(22166) ? (fragment != null || fragment.goBackInPostHistory()) : (fragment != null && fragment.goBackInPostHistory()))) {
                    } else {
                        if (!ListenerUtil.mutListener.listen(22167)) {
                            super.onBackPressed();
                        }
                    }
                }
            }
        }
    }

    /*
     * perform analytics tracking and bump the page view for the post at the passed position
     * if it hasn't already been done
     */
    private void trackPostAtPositionIfNeeded(int position) {
        if (!ListenerUtil.mutListener.listen(22172)) {
            if ((ListenerUtil.mutListener.listen(22171) ? (!hasPagerAdapter() && mTrackedPositions.contains(position)) : (!hasPagerAdapter() || mTrackedPositions.contains(position)))) {
                return;
            }
        }
        ReaderBlogIdPostId idPair = getAdapterBlogIdPostIdAtPosition(position);
        if (!ListenerUtil.mutListener.listen(22173)) {
            if (idPair == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22174)) {
            AppLog.d(AppLog.T.READER, "reader pager > tracking post at position " + position);
        }
        if (!ListenerUtil.mutListener.listen(22175)) {
            mTrackedPositions.add(position);
        }
        if (!ListenerUtil.mutListener.listen(22176)) {
            trackPost(idPair.getBlogId(), idPair.getPostId());
        }
    }

    /*
     * perform analytics tracking and bump the page view for the post
     */
    private void trackPost(long blogId, long postId) {
        if (!ListenerUtil.mutListener.listen(22177)) {
            // bump the page view
            ReaderPostActions.bumpPageViewForPost(mSiteStore, blogId, postId);
        }
        if (!ListenerUtil.mutListener.listen(22180)) {
            if (mSeenUnseenWithCounterFeatureConfig.isEnabled()) {
                ReaderPost currentPost = ReaderPostTable.getBlogPost(blogId, postId, true);
                if (!ListenerUtil.mutListener.listen(22179)) {
                    if (currentPost != null) {
                        if (!ListenerUtil.mutListener.listen(22178)) {
                            mPostSeenStatusWrapper.markPostAsSeenSilently(currentPost);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(22181)) {
            // analytics tracking
            mReaderTracker.trackPost(AnalyticsTracker.Stat.READER_ARTICLE_OPENED, mReaderPostTableWrapper.getBlogPost(blogId, postId, true));
        }
    }

    /*
     * loads the blogId/postId pairs used to populate the pager adapter - passed blogId/postId will
     * be made active after loading unless gotoNext=true, in which case the post after the passed
     * one will be made active
     */
    private void loadPosts(final long blogId, final long postId) {
        if (!ListenerUtil.mutListener.listen(22184)) {
            new Thread() {

                @Override
                public void run() {
                    final ReaderBlogIdPostIdList idList;
                    if (mIsSinglePostView) {
                        idList = new ReaderBlogIdPostIdList();
                        if (!ListenerUtil.mutListener.listen(22182)) {
                            idList.add(new ReaderBlogIdPostId(blogId, postId));
                        }
                    } else {
                        int maxPosts = ReaderConstants.READER_MAX_POSTS_TO_DISPLAY;
                        switch(getPostListType()) {
                            case TAG_FOLLOWED:
                            case TAG_PREVIEW:
                                idList = ReaderPostTable.getBlogIdPostIdsWithTag(getCurrentTag(), maxPosts);
                                break;
                            case BLOG_PREVIEW:
                                idList = ReaderPostTable.getBlogIdPostIdsInBlog(blogId, maxPosts);
                                break;
                            case SEARCH_RESULTS:
                            default:
                                return;
                        }
                    }
                    final int currentPosition = mViewPager.getCurrentItem();
                    final int newPosition = idList.indexOf(blogId, postId);
                    if (!ListenerUtil.mutListener.listen(22183)) {
                        runOnUiThread(() -> {
                            if (isFinishing()) {
                                return;
                            }
                            AppLog.d(T.READER, "reader pager > creating adapter");
                            PostPagerAdapter adapter = new PostPagerAdapter(getSupportFragmentManager(), idList);
                            mViewPager.setAdapter(adapter);
                            if (adapter.isValidPosition(newPosition)) {
                                mViewPager.setCurrentItem(newPosition);
                                trackPostAtPositionIfNeeded(newPosition);
                            } else if (adapter.isValidPosition(currentPosition)) {
                                mViewPager.setCurrentItem(currentPosition);
                                trackPostAtPositionIfNeeded(currentPosition);
                            }
                            // let the user know they can swipe between posts
                            if (adapter.getCount() > 1 && !AppPrefs.isReaderSwipeToNavigateShown()) {
                                WPSwipeSnackbar.show(mViewPager);
                                AppPrefs.setReaderSwipeToNavigateShown(true);
                            }
                        });
                    }
                }
            }.start();
        }
    }

    private ReaderTag getCurrentTag() {
        return mCurrentTag;
    }

    private boolean hasCurrentTag() {
        return mCurrentTag != null;
    }

    private ReaderPostListType getPostListType() {
        return mPostListType;
    }

    private Fragment getActivePagerFragment() {
        PostPagerAdapter adapter = getPagerAdapter();
        if (!ListenerUtil.mutListener.listen(22185)) {
            if (adapter == null) {
                return null;
            }
        }
        return adapter.getActiveFragment();
    }

    private ReaderPostDetailFragment getActiveDetailFragment() {
        Fragment fragment = getActivePagerFragment();
        if (fragment instanceof ReaderPostDetailFragment) {
            return (ReaderPostDetailFragment) fragment;
        } else {
            return null;
        }
    }

    private Fragment getPagerFragmentAtPosition(int position) {
        PostPagerAdapter adapter = getPagerAdapter();
        if (!ListenerUtil.mutListener.listen(22186)) {
            if (adapter == null) {
                return null;
            }
        }
        return adapter.getFragmentAtPosition(position);
    }

    private ReaderPostDetailFragment getDetailFragmentAtPosition(int position) {
        Fragment fragment = getPagerFragmentAtPosition(position);
        if (fragment instanceof ReaderPostDetailFragment) {
            return (ReaderPostDetailFragment) fragment;
        } else {
            return null;
        }
    }

    /*
     * called when user scrolls towards the last posts - requests older posts with the
     * current tag or in the current blog
     */
    private void requestMorePosts() {
        if (!ListenerUtil.mutListener.listen(22187)) {
            if (mIsRequestingMorePosts) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22188)) {
            AppLog.d(AppLog.T.READER, "reader pager > requesting older posts");
        }
        if (!ListenerUtil.mutListener.listen(22191)) {
            switch(getPostListType()) {
                case TAG_PREVIEW:
                case TAG_FOLLOWED:
                    if (!ListenerUtil.mutListener.listen(22189)) {
                        ReaderPostServiceStarter.startServiceForTag(this, getCurrentTag(), ReaderPostServiceStarter.UpdateAction.REQUEST_OLDER);
                    }
                    break;
                case BLOG_PREVIEW:
                    if (!ListenerUtil.mutListener.listen(22190)) {
                        ReaderPostServiceStarter.startServiceForBlog(this, mBlogId, ReaderPostServiceStarter.UpdateAction.REQUEST_OLDER);
                    }
                    break;
                case SEARCH_RESULTS:
                    break;
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.UpdatePostsStarted event) {
        if (!ListenerUtil.mutListener.listen(22192)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22193)) {
            mIsRequestingMorePosts = true;
        }
        if (!ListenerUtil.mutListener.listen(22194)) {
            mProgress.setVisibility(View.VISIBLE);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.UpdatePostsEnded event) {
        if (!ListenerUtil.mutListener.listen(22195)) {
            if (isFinishing()) {
                return;
            }
        }
        PostPagerAdapter adapter = getPagerAdapter();
        if (!ListenerUtil.mutListener.listen(22196)) {
            if (adapter == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22197)) {
            mIsRequestingMorePosts = false;
        }
        if (!ListenerUtil.mutListener.listen(22198)) {
            mProgress.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(22203)) {
            if (event.getResult() == ReaderActions.UpdateResult.HAS_NEW) {
                if (!ListenerUtil.mutListener.listen(22201)) {
                    AppLog.d(AppLog.T.READER, "reader pager > older posts received");
                }
                // remember which post to keep active
                ReaderBlogIdPostId id = adapter.getCurrentBlogIdPostId();
                long blogId = (id != null ? id.getBlogId() : 0);
                long postId = (id != null ? id.getPostId() : 0);
                if (!ListenerUtil.mutListener.listen(22202)) {
                    loadPosts(blogId, postId);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(22199)) {
                    AppLog.d(AppLog.T.READER, "reader pager > all posts loaded");
                }
                if (!ListenerUtil.mutListener.listen(22200)) {
                    adapter.mAllPostsLoaded = true;
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ReaderEvents.DoSignIn event) {
        if (!ListenerUtil.mutListener.listen(22204)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(22205)) {
            mReaderTracker.trackUri(AnalyticsTracker.Stat.READER_SIGN_IN_INITIATED, mInterceptedUri);
        }
        if (!ListenerUtil.mutListener.listen(22206)) {
            ActivityLauncher.loginWithoutMagicLink(this);
        }
    }

    /**
     * pager adapter containing post detail fragments
     */
    private class PostPagerAdapter extends FragmentStatePagerAdapter {

        private final ReaderBlogIdPostIdList mIdList;

        private boolean mAllPostsLoaded;

        // retain *every* fragment
        private final SparseArray<Fragment> mFragmentMap = new SparseArray<>();

        @SuppressLint("WrongConstant")
        PostPagerAdapter(FragmentManager fm, ReaderBlogIdPostIdList ids) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mIdList = (ReaderBlogIdPostIdList) ids.clone();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            // https://code.google.com/p/android/issues/detail?id=42601
            try {
                if (!ListenerUtil.mutListener.listen(22208)) {
                    AppLog.d(AppLog.T.READER, "reader pager > adapter restoreState");
                }
                if (!ListenerUtil.mutListener.listen(22209)) {
                    super.restoreState(state, loader);
                }
            } catch (IllegalStateException e) {
                if (!ListenerUtil.mutListener.listen(22207)) {
                    AppLog.e(AppLog.T.READER, e);
                }
            }
        }

        @Override
        public Parcelable saveState() {
            if (!ListenerUtil.mutListener.listen(22210)) {
                AppLog.d(AppLog.T.READER, "reader pager > adapter saveState");
            }
            return super.saveState();
        }

        private boolean canRequestMostPosts() {
            return (ListenerUtil.mutListener.listen(22219) ? ((ListenerUtil.mutListener.listen(22218) ? ((ListenerUtil.mutListener.listen(22211) ? (!mAllPostsLoaded || !mIsSinglePostView) : (!mAllPostsLoaded && !mIsSinglePostView)) || ((ListenerUtil.mutListener.listen(22217) ? (mIdList != null || (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY))))))) : (mIdList != null && (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY)))))))))) : ((ListenerUtil.mutListener.listen(22211) ? (!mAllPostsLoaded || !mIsSinglePostView) : (!mAllPostsLoaded && !mIsSinglePostView)) && ((ListenerUtil.mutListener.listen(22217) ? (mIdList != null || (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY))))))) : (mIdList != null && (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY))))))))))) || NetworkUtils.isNetworkAvailable(ReaderPostPagerActivity.this)) : ((ListenerUtil.mutListener.listen(22218) ? ((ListenerUtil.mutListener.listen(22211) ? (!mAllPostsLoaded || !mIsSinglePostView) : (!mAllPostsLoaded && !mIsSinglePostView)) || ((ListenerUtil.mutListener.listen(22217) ? (mIdList != null || (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY))))))) : (mIdList != null && (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY)))))))))) : ((ListenerUtil.mutListener.listen(22211) ? (!mAllPostsLoaded || !mIsSinglePostView) : (!mAllPostsLoaded && !mIsSinglePostView)) && ((ListenerUtil.mutListener.listen(22217) ? (mIdList != null || (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY))))))) : (mIdList != null && (ListenerUtil.mutListener.listen(22216) ? (mIdList.size() >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22215) ? (mIdList.size() <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22214) ? (mIdList.size() > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22213) ? (mIdList.size() != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(22212) ? (mIdList.size() == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (mIdList.size() < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY))))))))))) && NetworkUtils.isNetworkAvailable(ReaderPostPagerActivity.this)));
        }

        boolean isValidPosition(int position) {
            return ((ListenerUtil.mutListener.listen(22230) ? ((ListenerUtil.mutListener.listen(22224) ? (position <= 0) : (ListenerUtil.mutListener.listen(22223) ? (position > 0) : (ListenerUtil.mutListener.listen(22222) ? (position < 0) : (ListenerUtil.mutListener.listen(22221) ? (position != 0) : (ListenerUtil.mutListener.listen(22220) ? (position == 0) : (position >= 0)))))) || (ListenerUtil.mutListener.listen(22229) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(22228) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(22227) ? (position > getCount()) : (ListenerUtil.mutListener.listen(22226) ? (position != getCount()) : (ListenerUtil.mutListener.listen(22225) ? (position == getCount()) : (position < getCount()))))))) : ((ListenerUtil.mutListener.listen(22224) ? (position <= 0) : (ListenerUtil.mutListener.listen(22223) ? (position > 0) : (ListenerUtil.mutListener.listen(22222) ? (position < 0) : (ListenerUtil.mutListener.listen(22221) ? (position != 0) : (ListenerUtil.mutListener.listen(22220) ? (position == 0) : (position >= 0)))))) && (ListenerUtil.mutListener.listen(22229) ? (position >= getCount()) : (ListenerUtil.mutListener.listen(22228) ? (position <= getCount()) : (ListenerUtil.mutListener.listen(22227) ? (position > getCount()) : (ListenerUtil.mutListener.listen(22226) ? (position != getCount()) : (ListenerUtil.mutListener.listen(22225) ? (position == getCount()) : (position < getCount())))))))));
        }

        @Override
        public int getCount() {
            return mIdList.size();
        }

        @Override
        public Fragment getItem(int position) {
            if (!ListenerUtil.mutListener.listen(22242)) {
                if ((ListenerUtil.mutListener.listen(22240) ? (((ListenerUtil.mutListener.listen(22239) ? (position >= (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22238) ? (position <= (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22237) ? (position > (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22236) ? (position < (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22235) ? (position != (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))))))))) || canRequestMostPosts()) : (((ListenerUtil.mutListener.listen(22239) ? (position >= (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22238) ? (position <= (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22237) ? (position > (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22236) ? (position < (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (ListenerUtil.mutListener.listen(22235) ? (position != (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))) : (position == (ListenerUtil.mutListener.listen(22234) ? (getCount() % 1) : (ListenerUtil.mutListener.listen(22233) ? (getCount() / 1) : (ListenerUtil.mutListener.listen(22232) ? (getCount() * 1) : (ListenerUtil.mutListener.listen(22231) ? (getCount() + 1) : (getCount() - 1)))))))))))) && canRequestMostPosts()))) {
                    if (!ListenerUtil.mutListener.listen(22241)) {
                        requestMorePosts();
                    }
                }
            }
            return ReaderPostDetailFragment.Companion.newInstance(mIsFeed, mIdList.get(position).getBlogId(), mIdList.get(position).getPostId(), mDirectOperation, mCommentId, mIsRelatedPostView, mInterceptedUri, getPostListType(), mPostSlugsResolutionUnderway);
        }

        @Override
        @NonNull
        public Object instantiateItem(ViewGroup container, int position) {
            Object item = super.instantiateItem(container, position);
            if (!ListenerUtil.mutListener.listen(22244)) {
                if (item instanceof Fragment) {
                    if (!ListenerUtil.mutListener.listen(22243)) {
                        mFragmentMap.put(position, (Fragment) item);
                    }
                }
            }
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (!ListenerUtil.mutListener.listen(22245)) {
                mFragmentMap.remove(position);
            }
            if (!ListenerUtil.mutListener.listen(22246)) {
                super.destroyItem(container, position, object);
            }
        }

        private Fragment getActiveFragment() {
            return getFragmentAtPosition(mViewPager.getCurrentItem());
        }

        private Fragment getFragmentAtPosition(int position) {
            if (isValidPosition(position)) {
                return mFragmentMap.get(position);
            } else {
                return null;
            }
        }

        private ReaderBlogIdPostId getCurrentBlogIdPostId() {
            return getBlogIdPostIdAtPosition(mViewPager.getCurrentItem());
        }

        ReaderBlogIdPostId getBlogIdPostIdAtPosition(int position) {
            if (isValidPosition(position)) {
                return mIdList.get(position);
            } else {
                return null;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(22247)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(22260)) {
            switch(requestCode) {
                case RequestCodes.EDIT_POST:
                    if (!ListenerUtil.mutListener.listen(22250)) {
                        if ((ListenerUtil.mutListener.listen(22249) ? ((ListenerUtil.mutListener.listen(22248) ? (resultCode != Activity.RESULT_OK && data == null) : (resultCode != Activity.RESULT_OK || data == null)) && isFinishing()) : ((ListenerUtil.mutListener.listen(22248) ? (resultCode != Activity.RESULT_OK && data == null) : (resultCode != Activity.RESULT_OK || data == null)) || isFinishing()))) {
                            return;
                        }
                    }
                    int localId = data.getIntExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, 0);
                    final SiteModel site = (SiteModel) data.getSerializableExtra(WordPress.SITE);
                    final PostModel post = mPostStore.getPostByLocalPostId(localId);
                    if (!ListenerUtil.mutListener.listen(22252)) {
                        if (EditPostActivity.checkToRestart(data)) {
                            if (!ListenerUtil.mutListener.listen(22251)) {
                                ActivityLauncher.editPostOrPageForResult(data, ReaderPostPagerActivity.this, site, data.getIntExtra(EditPostActivity.EXTRA_POST_LOCAL_ID, 0));
                            }
                            // a restart will happen so, no need to continue here
                            break;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(22255)) {
                        if ((ListenerUtil.mutListener.listen(22253) ? (site != null || post != null) : (site != null && post != null))) {
                            if (!ListenerUtil.mutListener.listen(22254)) {
                                mUploadUtilsWrapper.handleEditPostResultSnackbars(this, findViewById(R.id.coordinator), data, post, site, mUploadActionUseCase.getUploadAction(post), v -> UploadUtils.publishPost(ReaderPostPagerActivity.this, post, site, mDispatcher));
                            }
                        }
                    }
                    break;
                case RequestCodes.DO_LOGIN:
                    if (!ListenerUtil.mutListener.listen(22257)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(22256)) {
                                mBackFromLogin = true;
                            }
                        }
                    }
                    break;
                case RequestCodes.NO_REBLOG_SITE:
                    if (!ListenerUtil.mutListener.listen(22259)) {
                        if (resultCode == Activity.RESULT_OK) {
                            if (!ListenerUtil.mutListener.listen(22258)) {
                                // Finish activity to make My Site page visible
                                finish();
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
        if (!ListenerUtil.mutListener.listen(22263)) {
            if ((ListenerUtil.mutListener.listen(22261) ? (site != null || event.post != null) : (site != null && event.post != null))) {
                if (!ListenerUtil.mutListener.listen(22262)) {
                    mUploadUtilsWrapper.onPostUploadedSnackbarHandler(this, findViewById(R.id.coordinator), event.isError(), event.isFirstTimePublish, event.post, null, site);
                }
            }
        }
    }
}
