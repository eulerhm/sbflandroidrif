package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.models.ReaderBlog;
import org.wordpress.android.ui.reader.ReaderInterfaces.OnFollowListener;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderBlogActions;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.LocaleManager;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.PhotonUtils.Quality;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.image.BlavatarShape;
import org.wordpress.android.util.image.ImageManager;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * topmost view in post adapter when showing blog preview - displays description, follower
 * count, and follow button
 */
public class ReaderSiteHeaderView extends LinearLayout {

    private final int mBlavatarSz;

    public interface OnBlogInfoLoadedListener {

        void onBlogInfoLoaded(ReaderBlog blogInfo);
    }

    private long mBlogId;

    private long mFeedId;

    private boolean mIsFeed;

    private ReaderFollowButton mFollowButton;

    private ReaderBlog mBlogInfo;

    private OnBlogInfoLoadedListener mBlogInfoListener;

    private OnFollowListener mFollowListener;

    @Inject
    AccountStore mAccountStore;

    @Inject
    ImageManager mImageManager;

    @Inject
    ReaderTracker mReaderTracker;

    public ReaderSiteHeaderView(Context context) {
        this(context, null);
    }

    public ReaderSiteHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReaderSiteHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(20119)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        mBlavatarSz = getResources().getDimensionPixelSize(R.dimen.blavatar_sz_extra_large);
        if (!ListenerUtil.mutListener.listen(20120)) {
            initView(context);
        }
    }

    private void initView(Context context) {
        View view = inflate(context, R.layout.reader_site_header_view, this);
        if (!ListenerUtil.mutListener.listen(20121)) {
            mFollowButton = view.findViewById(R.id.follow_button);
        }
    }

    public void setOnFollowListener(OnFollowListener listener) {
        if (!ListenerUtil.mutListener.listen(20122)) {
            mFollowListener = listener;
        }
    }

    public void setOnBlogInfoLoadedListener(OnBlogInfoLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(20123)) {
            mBlogInfoListener = listener;
        }
    }

    public void loadBlogInfo(final long blogId, final long feedId, final String source) {
        if (!ListenerUtil.mutListener.listen(20124)) {
            mBlogId = blogId;
        }
        if (!ListenerUtil.mutListener.listen(20125)) {
            mFeedId = feedId;
        }
        final ReaderBlog localBlogInfo;
        if (!ListenerUtil.mutListener.listen(20138)) {
            if ((ListenerUtil.mutListener.listen(20136) ? ((ListenerUtil.mutListener.listen(20130) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(20129) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(20128) ? (blogId > 0) : (ListenerUtil.mutListener.listen(20127) ? (blogId < 0) : (ListenerUtil.mutListener.listen(20126) ? (blogId != 0) : (blogId == 0)))))) || (ListenerUtil.mutListener.listen(20135) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(20134) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(20133) ? (feedId > 0) : (ListenerUtil.mutListener.listen(20132) ? (feedId < 0) : (ListenerUtil.mutListener.listen(20131) ? (feedId != 0) : (feedId == 0))))))) : ((ListenerUtil.mutListener.listen(20130) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(20129) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(20128) ? (blogId > 0) : (ListenerUtil.mutListener.listen(20127) ? (blogId < 0) : (ListenerUtil.mutListener.listen(20126) ? (blogId != 0) : (blogId == 0)))))) && (ListenerUtil.mutListener.listen(20135) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(20134) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(20133) ? (feedId > 0) : (ListenerUtil.mutListener.listen(20132) ? (feedId < 0) : (ListenerUtil.mutListener.listen(20131) ? (feedId != 0) : (feedId == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(20137)) {
                    ToastUtils.showToast(getContext(), R.string.reader_toast_err_get_blog_info);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20139)) {
            mIsFeed = ReaderUtils.isExternalFeed(mBlogId, mFeedId);
        }
        if (mIsFeed) {
            localBlogInfo = ReaderBlogTable.getFeedInfo(mFeedId);
        } else {
            localBlogInfo = ReaderBlogTable.getBlogInfo(mBlogId);
        }
        if (!ListenerUtil.mutListener.listen(20141)) {
            if (localBlogInfo != null) {
                if (!ListenerUtil.mutListener.listen(20140)) {
                    showBlogInfo(localBlogInfo, source);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20148)) {
            // then get from server if doesn't exist locally or is time to update it
            if ((ListenerUtil.mutListener.listen(20142) ? (localBlogInfo == null && ReaderBlogTable.isTimeToUpdateBlogInfo(localBlogInfo)) : (localBlogInfo == null || ReaderBlogTable.isTimeToUpdateBlogInfo(localBlogInfo)))) {
                ReaderActions.UpdateBlogInfoListener listener = new ReaderActions.UpdateBlogInfoListener() {

                    @Override
                    public void onResult(ReaderBlog serverBlogInfo) {
                        if (!ListenerUtil.mutListener.listen(20144)) {
                            if (isAttachedToWindow()) {
                                if (!ListenerUtil.mutListener.listen(20143)) {
                                    showBlogInfo(serverBlogInfo, source);
                                }
                            }
                        }
                    }
                };
                if (!ListenerUtil.mutListener.listen(20147)) {
                    if (mIsFeed) {
                        if (!ListenerUtil.mutListener.listen(20146)) {
                            ReaderBlogActions.updateFeedInfo(mFeedId, null, listener);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(20145)) {
                            ReaderBlogActions.updateBlogInfo(mBlogId, null, listener);
                        }
                    }
                }
            }
        }
    }

    private void showBlogInfo(ReaderBlog blogInfo, String source) {
        if (!ListenerUtil.mutListener.listen(20150)) {
            // do nothing if unchanged
            if ((ListenerUtil.mutListener.listen(20149) ? (blogInfo == null && blogInfo.isSameAs(mBlogInfo)) : (blogInfo == null || blogInfo.isSameAs(mBlogInfo)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20151)) {
            mBlogInfo = blogInfo;
        }
        ViewGroup layoutInfo = findViewById(R.id.layout_blog_info);
        TextView txtBlogName = layoutInfo.findViewById(R.id.text_blog_name);
        TextView txtDomain = layoutInfo.findViewById(R.id.text_domain);
        TextView txtDescription = layoutInfo.findViewById(R.id.text_blog_description);
        TextView txtFollowCount = layoutInfo.findViewById(R.id.text_blog_follow_count);
        ImageView blavatarImg = layoutInfo.findViewById(R.id.image_blavatar);
        if (!ListenerUtil.mutListener.listen(20154)) {
            if (blogInfo.hasName()) {
                if (!ListenerUtil.mutListener.listen(20153)) {
                    txtBlogName.setText(blogInfo.getName());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20152)) {
                    txtBlogName.setText(R.string.reader_untitled_post);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20158)) {
            if (blogInfo.hasUrl()) {
                if (!ListenerUtil.mutListener.listen(20156)) {
                    txtDomain.setText(UrlUtils.getHost(blogInfo.getUrl()));
                }
                if (!ListenerUtil.mutListener.listen(20157)) {
                    txtDomain.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20155)) {
                    txtDomain.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20162)) {
            if (blogInfo.hasDescription()) {
                if (!ListenerUtil.mutListener.listen(20160)) {
                    txtDescription.setText(blogInfo.getDescription());
                }
                if (!ListenerUtil.mutListener.listen(20161)) {
                    txtDescription.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20159)) {
                    txtDescription.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20163)) {
            mImageManager.loadIntoCircle(blavatarImg, SiteUtils.getSiteImageType(blogInfo.isP2orA8C(), BlavatarShape.CIRCULAR), PhotonUtils.getPhotonImageUrl(blogInfo.getImageUrl(), mBlavatarSz, mBlavatarSz, Quality.HIGH));
        }
        if (!ListenerUtil.mutListener.listen(20164)) {
            txtFollowCount.setText(String.format(LocaleManager.getSafeLocale(getContext()), getContext().getString(R.string.reader_label_follow_count), blogInfo.numSubscribers));
        }
        if (!ListenerUtil.mutListener.listen(20170)) {
            if (!mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(20169)) {
                    mFollowButton.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(20165)) {
                    mFollowButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(20166)) {
                    mFollowButton.setIsFollowed(blogInfo.isFollowing);
                }
                if (!ListenerUtil.mutListener.listen(20168)) {
                    mFollowButton.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(20167)) {
                                toggleFollowStatus(v, source);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20172)) {
            if (layoutInfo.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(20171)) {
                    layoutInfo.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(20174)) {
            if (mBlogInfoListener != null) {
                if (!ListenerUtil.mutListener.listen(20173)) {
                    mBlogInfoListener.onBlogInfoLoaded(blogInfo);
                }
            }
        }
    }

    private void toggleFollowStatus(final View followButton, final String source) {
        if (!ListenerUtil.mutListener.listen(20175)) {
            if (!NetworkUtils.checkConnection(getContext())) {
                return;
            }
        }
        final boolean isAskingToFollow;
        if (mIsFeed) {
            isAskingToFollow = !ReaderBlogTable.isFollowedFeed(mFeedId);
        } else {
            isAskingToFollow = !ReaderBlogTable.isFollowedBlog(mBlogId);
        }
        if (!ListenerUtil.mutListener.listen(20179)) {
            if (mFollowListener != null) {
                if (!ListenerUtil.mutListener.listen(20178)) {
                    if (isAskingToFollow) {
                        if (!ListenerUtil.mutListener.listen(20177)) {
                            mFollowListener.onFollowTapped(followButton, mBlogInfo.getName(), mIsFeed ? 0 : mBlogInfo.blogId, mBlogInfo.feedId);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(20176)) {
                            mFollowListener.onFollowingTapped();
                        }
                    }
                }
            }
        }
        ReaderActions.ActionListener listener = new ReaderActions.ActionListener() {

            @Override
            public void onActionResult(boolean succeeded) {
                if (!ListenerUtil.mutListener.listen(20180)) {
                    if (getContext() == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(20181)) {
                    mFollowButton.setEnabled(true);
                }
                if (!ListenerUtil.mutListener.listen(20184)) {
                    if (!succeeded) {
                        int errResId = isAskingToFollow ? R.string.reader_toast_err_follow_blog : R.string.reader_toast_err_unfollow_blog;
                        if (!ListenerUtil.mutListener.listen(20182)) {
                            ToastUtils.showToast(getContext(), errResId);
                        }
                        if (!ListenerUtil.mutListener.listen(20183)) {
                            mFollowButton.setIsFollowed(!isAskingToFollow);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(20185)) {
            // disable follow button until API call returns
            mFollowButton.setEnabled(false);
        }
        boolean result;
        if (mIsFeed) {
            result = ReaderBlogActions.followFeedById(mBlogId, mFeedId, isAskingToFollow, listener, source, mReaderTracker);
        } else {
            result = ReaderBlogActions.followBlogById(mBlogId, mFeedId, isAskingToFollow, listener, source, mReaderTracker);
        }
        if (!ListenerUtil.mutListener.listen(20187)) {
            if (result) {
                if (!ListenerUtil.mutListener.listen(20186)) {
                    mFollowButton.setIsFollowed(isAskingToFollow);
                }
            }
        }
    }
}
