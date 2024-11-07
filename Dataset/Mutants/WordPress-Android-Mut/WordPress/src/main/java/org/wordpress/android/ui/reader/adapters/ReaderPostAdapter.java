package org.wordpress.android.ui.reader.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderPostDiscoverData;
import org.wordpress.android.models.ReaderPostList;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.ReaderInterfaces;
import org.wordpress.android.ui.reader.ReaderInterfaces.OnFollowListener;
import org.wordpress.android.ui.reader.ReaderInterfaces.OnPostListItemButtonListener;
import org.wordpress.android.ui.reader.ReaderTypes;
import org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderTagActions;
import org.wordpress.android.ui.reader.discover.ReaderCardUiState;
import org.wordpress.android.ui.reader.discover.ReaderCardUiState.ReaderPostUiState;
import org.wordpress.android.ui.reader.discover.ReaderPostCardActionType;
import org.wordpress.android.ui.reader.discover.ReaderPostMoreButtonUiStateBuilder;
import org.wordpress.android.ui.reader.discover.ReaderPostUiStateBuilder;
import org.wordpress.android.ui.reader.discover.viewholders.ReaderPostViewHolder;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostId;
import org.wordpress.android.ui.reader.tracker.ReaderTab;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderXPostUtils;
import org.wordpress.android.ui.reader.views.ReaderGapMarkerView;
import org.wordpress.android.ui.reader.views.ReaderSiteHeaderView;
import org.wordpress.android.ui.reader.views.ReaderTagHeaderView;
import org.wordpress.android.ui.reader.views.ReaderTagHeaderViewUiState.ReaderTagHeaderUiState;
import org.wordpress.android.ui.reader.views.uistates.FollowButtonUiState;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.ColorUtils;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.image.BlavatarShape;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.HashSet;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ImageManager mImageManager;

    private final UiHelpers mUiHelpers;

    private ReaderTag mCurrentTag;

    private long mCurrentBlogId;

    private long mCurrentFeedId;

    private int mGapMarkerPosition = -1;

    private final int mPhotonWidth;

    private final int mPhotonHeight;

    private final int mAvatarSzSmall;

    private boolean mCanRequestMorePosts;

    @NonNull
    private final ReaderTypes.ReaderPostListType mPostListType;

    @NonNull
    private String mSource;

    private final ReaderPostList mPosts = new ReaderPostList();

    private final HashSet<String> mRenderedIds = new HashSet<>();

    private ReaderInterfaces.OnPostListItemButtonListener mOnPostListItemButtonListener;

    private ReaderInterfaces.OnFollowListener mFollowListener;

    private ReaderInterfaces.OnPostSelectedListener mPostSelectedListener;

    private ReaderInterfaces.DataLoadedListener mDataLoadedListener;

    private ReaderActions.DataRequestedListener mDataRequestedListener;

    private ReaderSiteHeaderView.OnBlogInfoLoadedListener mBlogInfoLoadedListener;

    // the large "tbl_posts.text" column is unused here, so skip it when querying
    private static final boolean EXCLUDE_TEXT_COLUMN = true;

    private static final int MAX_ROWS = ReaderConstants.READER_MAX_POSTS_TO_DISPLAY;

    private static final int VIEW_TYPE_POST = 0;

    private static final int VIEW_TYPE_XPOST = 1;

    private static final int VIEW_TYPE_SITE_HEADER = 2;

    private static final int VIEW_TYPE_TAG_HEADER = 3;

    private static final int VIEW_TYPE_GAP_MARKER = 4;

    private static final int VIEW_TYPE_REMOVED_POST = 5;

    private static final long ITEM_ID_HEADER = -1L;

    private static final long ITEM_ID_GAP_MARKER = -2L;

    private static final float READER_FEATURED_IMAGE_ASPECT_RATIO = 16 / 9f;

    private final boolean mIsMainReader;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ReaderPostUiStateBuilder mReaderPostUiStateBuilder;

    @Inject
    ReaderPostMoreButtonUiStateBuilder mReaderPostMoreButtonUiStateBuilder;

    @Inject
    ReaderTracker mReaderTracker;

    public String getSource() {
        return mSource;
    }

    /*
     * cross-post
     */
    private class ReaderXPostViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImgAvatar;

        private final ImageView mImgBlavatar;

        private final TextView mTxtTitle;

        private final TextView mTxtSubtitle;

        ReaderXPostViewHolder(View itemView) {
            super(itemView);
            View postContainer = itemView.findViewById(R.id.post_container);
            mImgAvatar = itemView.findViewById(R.id.image_avatar);
            mImgBlavatar = itemView.findViewById(R.id.image_blavatar);
            mTxtTitle = itemView.findViewById(R.id.text_title);
            mTxtSubtitle = itemView.findViewById(R.id.text_subtitle);
            if (!ListenerUtil.mutListener.listen(18582)) {
                postContainer.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    ReaderPost post = getItem(position);
                    if (mPostSelectedListener != null && post != null) {
                        mPostSelectedListener.onPostSelected(post);
                    }
                });
            }
        }
    }

    private static class ReaderRemovedPostViewHolder extends RecyclerView.ViewHolder {

        final View mPostContainer;

        private final ViewGroup mRemovedPostContainer;

        private final TextView mTxtRemovedPostTitle;

        private final TextView mUndoRemoveAction;

        ReaderRemovedPostViewHolder(View itemView) {
            super(itemView);
            mPostContainer = itemView.findViewById(R.id.post_container);
            mTxtRemovedPostTitle = itemView.findViewById(R.id.removed_post_title);
            mRemovedPostContainer = itemView.findViewById(R.id.removed_item_container);
            mUndoRemoveAction = itemView.findViewById(R.id.undo_remove);
        }
    }

    private static class SiteHeaderViewHolder extends RecyclerView.ViewHolder {

        private final ReaderSiteHeaderView mSiteHeaderView;

        SiteHeaderViewHolder(View itemView) {
            super(itemView);
            mSiteHeaderView = (ReaderSiteHeaderView) itemView;
        }
    }

    private static class TagHeaderViewHolder extends RecyclerView.ViewHolder {

        private final ReaderTagHeaderView mTagHeaderView;

        TagHeaderViewHolder(View itemView) {
            super(itemView);
            mTagHeaderView = (ReaderTagHeaderView) itemView;
        }

        public void onBind(ReaderTagHeaderUiState uiState) {
            if (!ListenerUtil.mutListener.listen(18583)) {
                mTagHeaderView.updateUi(uiState);
            }
        }
    }

    private static class GapMarkerViewHolder extends RecyclerView.ViewHolder {

        private final ReaderGapMarkerView mGapMarkerView;

        GapMarkerViewHolder(View itemView) {
            super(itemView);
            mGapMarkerView = (ReaderGapMarkerView) itemView;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if ((ListenerUtil.mutListener.listen(18589) ? ((ListenerUtil.mutListener.listen(18588) ? (position >= 0) : (ListenerUtil.mutListener.listen(18587) ? (position <= 0) : (ListenerUtil.mutListener.listen(18586) ? (position > 0) : (ListenerUtil.mutListener.listen(18585) ? (position < 0) : (ListenerUtil.mutListener.listen(18584) ? (position != 0) : (position == 0)))))) || hasSiteHeader()) : ((ListenerUtil.mutListener.listen(18588) ? (position >= 0) : (ListenerUtil.mutListener.listen(18587) ? (position <= 0) : (ListenerUtil.mutListener.listen(18586) ? (position > 0) : (ListenerUtil.mutListener.listen(18585) ? (position < 0) : (ListenerUtil.mutListener.listen(18584) ? (position != 0) : (position == 0)))))) && hasSiteHeader()))) {
            // first item is a ReaderSiteHeaderView
            return VIEW_TYPE_SITE_HEADER;
        } else if ((ListenerUtil.mutListener.listen(18595) ? ((ListenerUtil.mutListener.listen(18594) ? (position >= 0) : (ListenerUtil.mutListener.listen(18593) ? (position <= 0) : (ListenerUtil.mutListener.listen(18592) ? (position > 0) : (ListenerUtil.mutListener.listen(18591) ? (position < 0) : (ListenerUtil.mutListener.listen(18590) ? (position != 0) : (position == 0)))))) || hasTagHeader()) : ((ListenerUtil.mutListener.listen(18594) ? (position >= 0) : (ListenerUtil.mutListener.listen(18593) ? (position <= 0) : (ListenerUtil.mutListener.listen(18592) ? (position > 0) : (ListenerUtil.mutListener.listen(18591) ? (position < 0) : (ListenerUtil.mutListener.listen(18590) ? (position != 0) : (position == 0)))))) && hasTagHeader()))) {
            // first item is a ReaderTagHeaderView
            return VIEW_TYPE_TAG_HEADER;
        } else if ((ListenerUtil.mutListener.listen(18600) ? (position >= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18599) ? (position <= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18598) ? (position > mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18597) ? (position < mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18596) ? (position != mGapMarkerPosition) : (position == mGapMarkerPosition))))))) {
            return VIEW_TYPE_GAP_MARKER;
        } else {
            ReaderPost post = getItem(position);
            if ((ListenerUtil.mutListener.listen(18601) ? (post != null || post.isXpost()) : (post != null && post.isXpost()))) {
                return VIEW_TYPE_XPOST;
            } else if ((ListenerUtil.mutListener.listen(18603) ? ((ListenerUtil.mutListener.listen(18602) ? (post != null || isBookmarksList()) : (post != null && isBookmarksList())) || !post.isBookmarked) : ((ListenerUtil.mutListener.listen(18602) ? (post != null || isBookmarksList()) : (post != null && isBookmarksList())) && !post.isBookmarked))) {
                return VIEW_TYPE_REMOVED_POST;
            } else {
                return VIEW_TYPE_POST;
            }
        }
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View postView;
        switch(viewType) {
            case VIEW_TYPE_SITE_HEADER:
                ReaderSiteHeaderView readerSiteHeaderView = new ReaderSiteHeaderView(context);
                if (!ListenerUtil.mutListener.listen(18604)) {
                    readerSiteHeaderView.setOnFollowListener(mFollowListener);
                }
                return new SiteHeaderViewHolder(readerSiteHeaderView);
            case VIEW_TYPE_TAG_HEADER:
                return new TagHeaderViewHolder(new ReaderTagHeaderView(context));
            case VIEW_TYPE_GAP_MARKER:
                return new GapMarkerViewHolder(new ReaderGapMarkerView(context));
            case VIEW_TYPE_XPOST:
                postView = LayoutInflater.from(context).inflate(R.layout.reader_cardview_xpost, parent, false);
                return new ReaderXPostViewHolder(postView);
            case VIEW_TYPE_REMOVED_POST:
                postView = LayoutInflater.from(context).inflate(R.layout.reader_cardview_removed_post, parent, false);
                return new ReaderRemovedPostViewHolder(postView);
            default:
                return new ReaderPostViewHolder(mUiHelpers, mImageManager, mReaderTracker, parent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(18614)) {
            if (holder instanceof ReaderPostViewHolder) {
                if (!ListenerUtil.mutListener.listen(18613)) {
                    renderPost(position, (ReaderPostViewHolder) holder, false);
                }
            } else if (holder instanceof ReaderXPostViewHolder) {
                if (!ListenerUtil.mutListener.listen(18612)) {
                    renderXPost(position, (ReaderXPostViewHolder) holder);
                }
            } else if (holder instanceof ReaderRemovedPostViewHolder) {
                if (!ListenerUtil.mutListener.listen(18611)) {
                    renderRemovedPost(position, (ReaderRemovedPostViewHolder) holder);
                }
            } else if (holder instanceof SiteHeaderViewHolder) {
                SiteHeaderViewHolder siteHolder = (SiteHeaderViewHolder) holder;
                if (!ListenerUtil.mutListener.listen(18607)) {
                    siteHolder.mSiteHeaderView.setOnBlogInfoLoadedListener(mBlogInfoLoadedListener);
                }
                if (!ListenerUtil.mutListener.listen(18610)) {
                    if (isDiscover()) {
                        if (!ListenerUtil.mutListener.listen(18609)) {
                            siteHolder.mSiteHeaderView.loadBlogInfo(ReaderConstants.DISCOVER_SITE_ID, 0, mSource);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(18608)) {
                            siteHolder.mSiteHeaderView.loadBlogInfo(mCurrentBlogId, mCurrentFeedId, mSource);
                        }
                    }
                }
            } else if (holder instanceof TagHeaderViewHolder) {
                TagHeaderViewHolder tagHolder = (TagHeaderViewHolder) holder;
                if (!ListenerUtil.mutListener.listen(18606)) {
                    renderTagHeader(mCurrentTag, tagHolder, true);
                }
            } else if (holder instanceof GapMarkerViewHolder) {
                GapMarkerViewHolder gapHolder = (GapMarkerViewHolder) holder;
                if (!ListenerUtil.mutListener.listen(18605)) {
                    gapHolder.mGapMarkerView.setCurrentTag(mCurrentTag);
                }
            }
        }
    }

    private void renderTagHeader(ReaderTag currentTag, TagHeaderViewHolder tagHolder, Boolean isFollowButtonEnabled) {
        if (!ListenerUtil.mutListener.listen(18615)) {
            if (currentTag == null) {
                return;
            }
        }
        Function0<Unit> onFollowButtonClicked = () -> {
            toggleFollowButton(tagHolder.itemView.getContext(), currentTag, tagHolder);
            return Unit.INSTANCE;
        };
        ReaderTagHeaderUiState uiState = new ReaderTagHeaderUiState(currentTag.getLabel(), new FollowButtonUiState(onFollowButtonClicked, ReaderTagTable.isFollowedTagName(currentTag.getTagSlug()), isFollowButtonEnabled, true));
        if (!ListenerUtil.mutListener.listen(18616)) {
            tagHolder.onBind(uiState);
        }
    }

    private void toggleFollowButton(Context context, @NotNull ReaderTag currentTag, TagHeaderViewHolder tagHolder) {
        if (!ListenerUtil.mutListener.listen(18617)) {
            if (!NetworkUtils.checkConnection(context)) {
                return;
            }
        }
        final boolean isAskingToFollow = !ReaderTagTable.isFollowedTagName(currentTag.getTagSlug());
        final String slugForTracking = currentTag.getTagSlug();
        ReaderActions.ActionListener listener = succeeded -> {
            if (!succeeded) {
                int errResId = isAskingToFollow ? R.string.reader_toast_err_add_tag : R.string.reader_toast_err_remove_tag;
                ToastUtils.showToast(context, errResId);
            } else {
                if (isAskingToFollow) {
                    mReaderTracker.trackTag(AnalyticsTracker.Stat.READER_TAG_FOLLOWED, slugForTracking, mSource);
                } else {
                    mReaderTracker.trackTag(AnalyticsTracker.Stat.READER_TAG_UNFOLLOWED, slugForTracking, mSource);
                }
            }
            renderTagHeader(currentTag, tagHolder, true);
        };
        boolean success;
        boolean isLoggedIn = mAccountStore.hasAccessToken();
        if (isAskingToFollow) {
            success = ReaderTagActions.addTag(mCurrentTag, listener, isLoggedIn);
        } else {
            success = ReaderTagActions.deleteTag(mCurrentTag, listener, isLoggedIn);
        }
        if (!ListenerUtil.mutListener.listen(18620)) {
            if ((ListenerUtil.mutListener.listen(18618) ? (isLoggedIn || success) : (isLoggedIn && success))) {
                if (!ListenerUtil.mutListener.listen(18619)) {
                    renderTagHeader(currentTag, tagHolder, false);
                }
            }
        }
    }

    private void renderXPost(int position, ReaderXPostViewHolder holder) {
        final ReaderPost post = getItem(position);
        if (!ListenerUtil.mutListener.listen(18621)) {
            if (post == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18622)) {
            mImageManager.loadIntoCircle(holder.mImgAvatar, ImageType.AVATAR, GravatarUtils.fixGravatarUrl(post.getPostAvatar(), mAvatarSzSmall));
        }
        if (!ListenerUtil.mutListener.listen(18623)) {
            mImageManager.loadIntoCircle(holder.mImgBlavatar, SiteUtils.getSiteImageType(post.isP2orA8C(), BlavatarShape.CIRCULAR), GravatarUtils.fixGravatarUrl(post.getBlogImageUrl(), mAvatarSzSmall));
        }
        if (!ListenerUtil.mutListener.listen(18624)) {
            holder.mTxtTitle.setText(ReaderXPostUtils.getXPostTitle(post));
        }
        if (!ListenerUtil.mutListener.listen(18625)) {
            holder.mTxtSubtitle.setText(ReaderXPostUtils.getXPostSubtitleHtml(post));
        }
        if (!ListenerUtil.mutListener.listen(18626)) {
            checkLoadMore(position);
        }
    }

    private void renderRemovedPost(final int position, final ReaderRemovedPostViewHolder holder) {
        final ReaderPost post = getItem(position);
        final Context context = holder.mRemovedPostContainer.getContext();
        if (!ListenerUtil.mutListener.listen(18627)) {
            holder.mTxtRemovedPostTitle.setText(createTextForRemovedPostContainer(post, context));
        }
        Drawable drawable = ColorUtils.applyTintToDrawable(context, R.drawable.ic_undo_white_24dp, ContextExtensionsKt.getColorResIdFromAttribute(context, R.attr.colorPrimary));
        if (!ListenerUtil.mutListener.listen(18628)) {
            holder.mUndoRemoveAction.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        if (!ListenerUtil.mutListener.listen(18629)) {
            holder.mPostContainer.setOnClickListener(v -> undoPostUnbookmarked(post));
        }
    }

    private void undoPostUnbookmarked(final ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(18631)) {
            if (!post.isBookmarked) {
                if (!ListenerUtil.mutListener.listen(18630)) {
                    mOnPostListItemButtonListener.onButtonClicked(post, ReaderPostCardActionType.BOOKMARK);
                }
            }
        }
    }

    private void renderPost(final int position, final ReaderPostViewHolder holder, boolean showMoreMenu) {
        final ReaderPost post = getItem(position);
        ReaderPostListType postListType = getPostListType();
        if (!ListenerUtil.mutListener.listen(18632)) {
            if (post == null) {
                return;
            }
        }
        Context ctx = holder.getViewContext();
        Function3<Long, Long, ReaderPostCardActionType, Unit> onButtonClicked = (postId, blogId, type) -> {
            mOnPostListItemButtonListener.onButtonClicked(post, type);
            renderPost(position, holder, false);
            return Unit.INSTANCE;
        };
        Function2<Long, Long, Unit> onItemClicked = (postId, blogId) -> {
            if (mPostSelectedListener != null) {
                mPostSelectedListener.onPostSelected(post);
            }
            return Unit.INSTANCE;
        };
        Function1<ReaderCardUiState, Unit> onItemRendered = (item) -> {
            checkLoadMore(position);
            // to the rendered list and record the TrainTracks render event
            if (post.hasRailcar() && !mRenderedIds.contains(post.getPseudoId())) {
                mRenderedIds.add(post.getPseudoId());
                mReaderTracker.trackRailcar(post.getRailcarJson());
            }
            return Unit.INSTANCE;
        };
        Function2<Long, Long, Unit> onDiscoverSectionClicked = (postId, blogId) -> {
            ReaderPostDiscoverData discoverData = post.getDiscoverData();
            switch(discoverData.getDiscoverType()) {
                case EDITOR_PICK:
                    if (mPostSelectedListener != null) {
                        mPostSelectedListener.onPostSelected(post);
                    }
                    break;
                case SITE_PICK:
                    if (discoverData.getBlogId() != 0) {
                        ReaderActivityLauncher.showReaderBlogPreview(ctx, discoverData.getBlogId(), post.isFollowedByCurrentUser, mSource, mReaderTracker);
                    } else if (discoverData.hasBlogUrl()) {
                        ReaderActivityLauncher.openUrl(ctx, discoverData.getBlogUrl());
                    }
                    break;
                case OTHER:
                    // noop
                    break;
            }
            return Unit.INSTANCE;
        };
        Function1<ReaderPostUiState, Unit> onMoreButtonClicked = (uiState) -> {
            renderPost(position, holder, true);
            return Unit.INSTANCE;
        };
        Function1<ReaderPostUiState, Unit> onMoreDismissed = (uiState) -> {
            renderPost(position, holder, false);
            return Unit.INSTANCE;
        };
        Function2<Long, Long, Unit> onVideoOverlayClicked = (postId, blogId) -> {
            ReaderActivityLauncher.showReaderVideoViewer(ctx, post.getFeaturedVideo());
            return Unit.INSTANCE;
        };
        Function2<Long, Long, Unit> onPostHeaderClicked = (postId, blogId) -> {
            ReaderActivityLauncher.showReaderBlogPreview(ctx, post, mSource, mReaderTracker);
            return Unit.INSTANCE;
        };
        Function1<String, Unit> onTagItemClicked = (tagSlug) -> {
            // noop
            return Unit.INSTANCE;
        };
        ReaderPostUiState uiState = mReaderPostUiStateBuilder.mapPostToUiStateBlocking(mSource, post, false, mPhotonWidth, mPhotonHeight, postListType, onButtonClicked, onItemClicked, onItemRendered, onDiscoverSectionClicked, onMoreButtonClicked, onMoreDismissed, onVideoOverlayClicked, onPostHeaderClicked, onTagItemClicked, showMoreMenu ? mReaderPostMoreButtonUiStateBuilder.buildMoreMenuItemsBlocking(post, onButtonClicked) : null);
        if (!ListenerUtil.mutListener.listen(18633)) {
            holder.onBind(uiState);
        }
    }

    /*
     * if we're nearing the end of the posts, fire request to load more
     */
    private void checkLoadMore(int position) {
        if (!ListenerUtil.mutListener.listen(18646)) {
            if ((ListenerUtil.mutListener.listen(18644) ? ((ListenerUtil.mutListener.listen(18634) ? (mCanRequestMorePosts || mDataRequestedListener != null) : (mCanRequestMorePosts && mDataRequestedListener != null)) || ((ListenerUtil.mutListener.listen(18643) ? (position <= (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18642) ? (position > (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18641) ? (position < (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18640) ? (position != (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18639) ? (position == (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))))) : ((ListenerUtil.mutListener.listen(18634) ? (mCanRequestMorePosts || mDataRequestedListener != null) : (mCanRequestMorePosts && mDataRequestedListener != null)) && ((ListenerUtil.mutListener.listen(18643) ? (position <= (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18642) ? (position > (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18641) ? (position < (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18640) ? (position != (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (ListenerUtil.mutListener.listen(18639) ? (position == (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1)))))) : (position >= (ListenerUtil.mutListener.listen(18638) ? (getItemCount() % 1) : (ListenerUtil.mutListener.listen(18637) ? (getItemCount() / 1) : (ListenerUtil.mutListener.listen(18636) ? (getItemCount() * 1) : (ListenerUtil.mutListener.listen(18635) ? (getItemCount() + 1) : (getItemCount() - 1))))))))))))))) {
                if (!ListenerUtil.mutListener.listen(18645)) {
                    mDataRequestedListener.onRequestData();
                }
            }
        }
    }

    public ReaderPostAdapter(Context context, ReaderPostListType postListType, ImageManager imageManager, UiHelpers uiHelpers, boolean isMainReader) {
        super();
        if (!ListenerUtil.mutListener.listen(18647)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        this.mImageManager = imageManager;
        mPostListType = postListType;
        if (!ListenerUtil.mutListener.listen(18648)) {
            mSource = mReaderTracker.getSource(mPostListType);
        }
        mUiHelpers = uiHelpers;
        mAvatarSzSmall = context.getResources().getDimensionPixelSize(R.dimen.avatar_sz_small);
        mIsMainReader = isMainReader;
        int displayWidth = DisplayUtils.getWindowPixelWidth(context);
        int cardMargin = context.getResources().getDimensionPixelSize(R.dimen.reader_card_margin);
        mPhotonWidth = (ListenerUtil.mutListener.listen(18656) ? (displayWidth % ((ListenerUtil.mutListener.listen(18652) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18651) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18650) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18649) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18655) ? (displayWidth / ((ListenerUtil.mutListener.listen(18652) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18651) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18650) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18649) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18654) ? (displayWidth * ((ListenerUtil.mutListener.listen(18652) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18651) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18650) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18649) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18653) ? (displayWidth + ((ListenerUtil.mutListener.listen(18652) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18651) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18650) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18649) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18652) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18651) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18650) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18649) ? (cardMargin + 2) : (cardMargin * 2)))))))))));
        mPhotonHeight = (int) ((ListenerUtil.mutListener.listen(18660) ? (mPhotonWidth % READER_FEATURED_IMAGE_ASPECT_RATIO) : (ListenerUtil.mutListener.listen(18659) ? (mPhotonWidth * READER_FEATURED_IMAGE_ASPECT_RATIO) : (ListenerUtil.mutListener.listen(18658) ? (mPhotonWidth - READER_FEATURED_IMAGE_ASPECT_RATIO) : (ListenerUtil.mutListener.listen(18657) ? (mPhotonWidth + READER_FEATURED_IMAGE_ASPECT_RATIO) : (mPhotonWidth / READER_FEATURED_IMAGE_ASPECT_RATIO))))));
        if (!ListenerUtil.mutListener.listen(18661)) {
            setHasStableIds(true);
        }
    }

    private boolean hasHeader() {
        return (ListenerUtil.mutListener.listen(18662) ? (hasSiteHeader() && hasTagHeader()) : (hasSiteHeader() || hasTagHeader()));
    }

    private boolean hasSiteHeader() {
        return (ListenerUtil.mutListener.listen(18664) ? (!mIsMainReader || ((ListenerUtil.mutListener.listen(18663) ? (isDiscover() && getPostListType() == ReaderTypes.ReaderPostListType.BLOG_PREVIEW) : (isDiscover() || getPostListType() == ReaderTypes.ReaderPostListType.BLOG_PREVIEW)))) : (!mIsMainReader && ((ListenerUtil.mutListener.listen(18663) ? (isDiscover() && getPostListType() == ReaderTypes.ReaderPostListType.BLOG_PREVIEW) : (isDiscover() || getPostListType() == ReaderTypes.ReaderPostListType.BLOG_PREVIEW)))));
    }

    private boolean hasTagHeader() {
        return (ListenerUtil.mutListener.listen(18665) ? ((getPostListType() == ReaderPostListType.TAG_PREVIEW) || !isEmpty()) : ((getPostListType() == ReaderPostListType.TAG_PREVIEW) && !isEmpty()));
    }

    private boolean isDiscover() {
        return (ListenerUtil.mutListener.listen(18666) ? (mCurrentTag != null || mCurrentTag.isDiscover()) : (mCurrentTag != null && mCurrentTag.isDiscover()));
    }

    public void setOnPostListItemButtonListener(OnPostListItemButtonListener listener) {
        if (!ListenerUtil.mutListener.listen(18667)) {
            mOnPostListItemButtonListener = listener;
        }
    }

    public void setOnFollowListener(OnFollowListener listener) {
        if (!ListenerUtil.mutListener.listen(18668)) {
            mFollowListener = listener;
        }
    }

    public void setOnPostSelectedListener(ReaderInterfaces.OnPostSelectedListener listener) {
        if (!ListenerUtil.mutListener.listen(18669)) {
            mPostSelectedListener = listener;
        }
    }

    public void setOnDataLoadedListener(ReaderInterfaces.DataLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(18670)) {
            mDataLoadedListener = listener;
        }
    }

    public void setOnDataRequestedListener(ReaderActions.DataRequestedListener listener) {
        if (!ListenerUtil.mutListener.listen(18671)) {
            mDataRequestedListener = listener;
        }
    }

    public void setOnBlogInfoLoadedListener(ReaderSiteHeaderView.OnBlogInfoLoadedListener listener) {
        if (!ListenerUtil.mutListener.listen(18672)) {
            mBlogInfoLoadedListener = listener;
        }
    }

    private ReaderTypes.ReaderPostListType getPostListType() {
        return (mPostListType != null ? mPostListType : ReaderTypes.DEFAULT_POST_LIST_TYPE);
    }

    // used when the viewing tagged posts
    public void setCurrentTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(18673)) {
            mSource = mReaderTracker.getSource(mPostListType, ReaderTab.transformTagToTab(tag));
        }
        if (!ListenerUtil.mutListener.listen(18677)) {
            if (!ReaderTag.isSameTag(tag, mCurrentTag)) {
                if (!ListenerUtil.mutListener.listen(18674)) {
                    mCurrentTag = tag;
                }
                if (!ListenerUtil.mutListener.listen(18675)) {
                    mRenderedIds.clear();
                }
                if (!ListenerUtil.mutListener.listen(18676)) {
                    reload();
                }
            }
        }
    }

    public boolean isCurrentTag(ReaderTag tag) {
        return ReaderTag.isSameTag(tag, mCurrentTag);
    }

    // used when the list type is ReaderPostListType.BLOG_PREVIEW
    public void setCurrentBlogAndFeed(long blogId, long feedId) {
        if (!ListenerUtil.mutListener.listen(18693)) {
            if ((ListenerUtil.mutListener.listen(18688) ? ((ListenerUtil.mutListener.listen(18682) ? (blogId >= mCurrentBlogId) : (ListenerUtil.mutListener.listen(18681) ? (blogId <= mCurrentBlogId) : (ListenerUtil.mutListener.listen(18680) ? (blogId > mCurrentBlogId) : (ListenerUtil.mutListener.listen(18679) ? (blogId < mCurrentBlogId) : (ListenerUtil.mutListener.listen(18678) ? (blogId == mCurrentBlogId) : (blogId != mCurrentBlogId)))))) && (ListenerUtil.mutListener.listen(18687) ? (feedId >= mCurrentFeedId) : (ListenerUtil.mutListener.listen(18686) ? (feedId <= mCurrentFeedId) : (ListenerUtil.mutListener.listen(18685) ? (feedId > mCurrentFeedId) : (ListenerUtil.mutListener.listen(18684) ? (feedId < mCurrentFeedId) : (ListenerUtil.mutListener.listen(18683) ? (feedId == mCurrentFeedId) : (feedId != mCurrentFeedId))))))) : ((ListenerUtil.mutListener.listen(18682) ? (blogId >= mCurrentBlogId) : (ListenerUtil.mutListener.listen(18681) ? (blogId <= mCurrentBlogId) : (ListenerUtil.mutListener.listen(18680) ? (blogId > mCurrentBlogId) : (ListenerUtil.mutListener.listen(18679) ? (blogId < mCurrentBlogId) : (ListenerUtil.mutListener.listen(18678) ? (blogId == mCurrentBlogId) : (blogId != mCurrentBlogId)))))) || (ListenerUtil.mutListener.listen(18687) ? (feedId >= mCurrentFeedId) : (ListenerUtil.mutListener.listen(18686) ? (feedId <= mCurrentFeedId) : (ListenerUtil.mutListener.listen(18685) ? (feedId > mCurrentFeedId) : (ListenerUtil.mutListener.listen(18684) ? (feedId < mCurrentFeedId) : (ListenerUtil.mutListener.listen(18683) ? (feedId == mCurrentFeedId) : (feedId != mCurrentFeedId))))))))) {
                if (!ListenerUtil.mutListener.listen(18689)) {
                    mCurrentBlogId = blogId;
                }
                if (!ListenerUtil.mutListener.listen(18690)) {
                    mCurrentFeedId = feedId;
                }
                if (!ListenerUtil.mutListener.listen(18691)) {
                    mRenderedIds.clear();
                }
                if (!ListenerUtil.mutListener.listen(18692)) {
                    reload();
                }
            }
        }
    }

    public void clear() {
        if (!ListenerUtil.mutListener.listen(18694)) {
            mGapMarkerPosition = -1;
        }
        if (!ListenerUtil.mutListener.listen(18697)) {
            if (!mPosts.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(18695)) {
                    mPosts.clear();
                }
                if (!ListenerUtil.mutListener.listen(18696)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    public void refresh() {
        if (!ListenerUtil.mutListener.listen(18698)) {
            loadPosts();
        }
    }

    /*
     * same as refresh() above but first clears the existing posts
     */
    public void reload() {
        if (!ListenerUtil.mutListener.listen(18699)) {
            clear();
        }
        if (!ListenerUtil.mutListener.listen(18700)) {
            loadPosts();
        }
    }

    private void loadPosts() {
        if (!ListenerUtil.mutListener.listen(18702)) {
            if (mIsTaskRunning) {
                if (!ListenerUtil.mutListener.listen(18701)) {
                    AppLog.w(AppLog.T.READER, "reader posts task already running");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18703)) {
            new LoadPostsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private ReaderPost getItem(int position) {
        if (!ListenerUtil.mutListener.listen(18710)) {
            if ((ListenerUtil.mutListener.listen(18709) ? ((ListenerUtil.mutListener.listen(18708) ? (position >= getHeaderPosition()) : (ListenerUtil.mutListener.listen(18707) ? (position <= getHeaderPosition()) : (ListenerUtil.mutListener.listen(18706) ? (position > getHeaderPosition()) : (ListenerUtil.mutListener.listen(18705) ? (position < getHeaderPosition()) : (ListenerUtil.mutListener.listen(18704) ? (position != getHeaderPosition()) : (position == getHeaderPosition())))))) || hasHeader()) : ((ListenerUtil.mutListener.listen(18708) ? (position >= getHeaderPosition()) : (ListenerUtil.mutListener.listen(18707) ? (position <= getHeaderPosition()) : (ListenerUtil.mutListener.listen(18706) ? (position > getHeaderPosition()) : (ListenerUtil.mutListener.listen(18705) ? (position < getHeaderPosition()) : (ListenerUtil.mutListener.listen(18704) ? (position != getHeaderPosition()) : (position == getHeaderPosition())))))) && hasHeader()))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(18716)) {
            if ((ListenerUtil.mutListener.listen(18715) ? (position >= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18714) ? (position <= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18713) ? (position > mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18712) ? (position < mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18711) ? (position != mGapMarkerPosition) : (position == mGapMarkerPosition))))))) {
                return null;
            }
        }
        int arrayPos = (ListenerUtil.mutListener.listen(18720) ? (position % getItemPositionOffset()) : (ListenerUtil.mutListener.listen(18719) ? (position / getItemPositionOffset()) : (ListenerUtil.mutListener.listen(18718) ? (position * getItemPositionOffset()) : (ListenerUtil.mutListener.listen(18717) ? (position + getItemPositionOffset()) : (position - getItemPositionOffset())))));
        if (!ListenerUtil.mutListener.listen(18733)) {
            if ((ListenerUtil.mutListener.listen(18731) ? ((ListenerUtil.mutListener.listen(18725) ? (mGapMarkerPosition >= -1) : (ListenerUtil.mutListener.listen(18724) ? (mGapMarkerPosition <= -1) : (ListenerUtil.mutListener.listen(18723) ? (mGapMarkerPosition < -1) : (ListenerUtil.mutListener.listen(18722) ? (mGapMarkerPosition != -1) : (ListenerUtil.mutListener.listen(18721) ? (mGapMarkerPosition == -1) : (mGapMarkerPosition > -1)))))) || (ListenerUtil.mutListener.listen(18730) ? (position >= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18729) ? (position <= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18728) ? (position < mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18727) ? (position != mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18726) ? (position == mGapMarkerPosition) : (position > mGapMarkerPosition))))))) : ((ListenerUtil.mutListener.listen(18725) ? (mGapMarkerPosition >= -1) : (ListenerUtil.mutListener.listen(18724) ? (mGapMarkerPosition <= -1) : (ListenerUtil.mutListener.listen(18723) ? (mGapMarkerPosition < -1) : (ListenerUtil.mutListener.listen(18722) ? (mGapMarkerPosition != -1) : (ListenerUtil.mutListener.listen(18721) ? (mGapMarkerPosition == -1) : (mGapMarkerPosition > -1)))))) && (ListenerUtil.mutListener.listen(18730) ? (position >= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18729) ? (position <= mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18728) ? (position < mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18727) ? (position != mGapMarkerPosition) : (ListenerUtil.mutListener.listen(18726) ? (position == mGapMarkerPosition) : (position > mGapMarkerPosition))))))))) {
                if (!ListenerUtil.mutListener.listen(18732)) {
                    arrayPos--;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18740)) {
            if ((ListenerUtil.mutListener.listen(18738) ? (mPosts.size() >= arrayPos) : (ListenerUtil.mutListener.listen(18737) ? (mPosts.size() > arrayPos) : (ListenerUtil.mutListener.listen(18736) ? (mPosts.size() < arrayPos) : (ListenerUtil.mutListener.listen(18735) ? (mPosts.size() != arrayPos) : (ListenerUtil.mutListener.listen(18734) ? (mPosts.size() == arrayPos) : (mPosts.size() <= arrayPos))))))) {
                if (!ListenerUtil.mutListener.listen(18739)) {
                    AppLog.d(T.READER, "Trying to read an element out of bounds of the posts list");
                }
                return null;
            }
        }
        return mPosts.get(arrayPos);
    }

    private int getItemPositionOffset() {
        return hasHeader() ? 1 : 0;
    }

    private int getHeaderPosition() {
        return hasHeader() ? 0 : -1;
    }

    @Override
    public int getItemCount() {
        int size = mPosts.size();
        if (!ListenerUtil.mutListener.listen(18747)) {
            if ((ListenerUtil.mutListener.listen(18745) ? (mGapMarkerPosition >= -1) : (ListenerUtil.mutListener.listen(18744) ? (mGapMarkerPosition <= -1) : (ListenerUtil.mutListener.listen(18743) ? (mGapMarkerPosition > -1) : (ListenerUtil.mutListener.listen(18742) ? (mGapMarkerPosition < -1) : (ListenerUtil.mutListener.listen(18741) ? (mGapMarkerPosition == -1) : (mGapMarkerPosition != -1))))))) {
                if (!ListenerUtil.mutListener.listen(18746)) {
                    size++;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18749)) {
            if (hasHeader()) {
                if (!ListenerUtil.mutListener.listen(18748)) {
                    size++;
                }
            }
        }
        return size;
    }

    public boolean isEmpty() {
        return ((ListenerUtil.mutListener.listen(18750) ? (mPosts == null && mPosts.size() == 0) : (mPosts == null || mPosts.size() == 0)));
    }

    private boolean isBookmarksList() {
        return ((ListenerUtil.mutListener.listen(18752) ? (getPostListType() == ReaderPostListType.TAG_FOLLOWED || ((ListenerUtil.mutListener.listen(18751) ? (mCurrentTag != null || mCurrentTag.isBookmarked()) : (mCurrentTag != null && mCurrentTag.isBookmarked())))) : (getPostListType() == ReaderPostListType.TAG_FOLLOWED && ((ListenerUtil.mutListener.listen(18751) ? (mCurrentTag != null || mCurrentTag.isBookmarked()) : (mCurrentTag != null && mCurrentTag.isBookmarked()))))));
    }

    @Override
    public long getItemId(int position) {
        switch(getItemViewType(position)) {
            case VIEW_TYPE_TAG_HEADER:
            case VIEW_TYPE_SITE_HEADER:
                return ITEM_ID_HEADER;
            case VIEW_TYPE_GAP_MARKER:
                return ITEM_ID_GAP_MARKER;
            default:
                ReaderPost post = getItem(position);
                return post != null ? post.getStableId() : 0;
        }
    }

    /**
     * Creates 'Removed [post title]' text, with the '[post title]' in bold.
     */
    @NonNull
    private SpannableStringBuilder createTextForRemovedPostContainer(ReaderPost post, Context context) {
        String removedString = context.getString(R.string.removed);
        String removedPostTitle = removedString + " " + post.getTitle();
        SpannableStringBuilder str = new SpannableStringBuilder(removedPostTitle);
        if (!ListenerUtil.mutListener.listen(18753)) {
            str.setSpan(new StyleSpan(Typeface.BOLD), removedString.length(), removedPostTitle.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return str;
    }

    public void setFollowStatusForBlog(long blogId, boolean isFollowing) {
        ReaderPost post;
        {
            long _loopCounter300 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(18763) ? (i >= mPosts.size()) : (ListenerUtil.mutListener.listen(18762) ? (i <= mPosts.size()) : (ListenerUtil.mutListener.listen(18761) ? (i > mPosts.size()) : (ListenerUtil.mutListener.listen(18760) ? (i != mPosts.size()) : (ListenerUtil.mutListener.listen(18759) ? (i == mPosts.size()) : (i < mPosts.size())))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter300", ++_loopCounter300);
                post = mPosts.get(i);
                if (!ListenerUtil.mutListener.listen(18758)) {
                    if ((ListenerUtil.mutListener.listen(18754) ? (post.blogId == blogId || post.isFollowedByCurrentUser != isFollowing) : (post.blogId == blogId && post.isFollowedByCurrentUser != isFollowing))) {
                        if (!ListenerUtil.mutListener.listen(18755)) {
                            post.isFollowedByCurrentUser = isFollowing;
                        }
                        if (!ListenerUtil.mutListener.listen(18756)) {
                            mPosts.set(i, post);
                        }
                        if (!ListenerUtil.mutListener.listen(18757)) {
                            notifyItemChanged(i);
                        }
                    }
                }
            }
        }
    }

    public void removeGapMarker() {
        if (!ListenerUtil.mutListener.listen(18769)) {
            if ((ListenerUtil.mutListener.listen(18768) ? (mGapMarkerPosition >= -1) : (ListenerUtil.mutListener.listen(18767) ? (mGapMarkerPosition <= -1) : (ListenerUtil.mutListener.listen(18766) ? (mGapMarkerPosition > -1) : (ListenerUtil.mutListener.listen(18765) ? (mGapMarkerPosition < -1) : (ListenerUtil.mutListener.listen(18764) ? (mGapMarkerPosition != -1) : (mGapMarkerPosition == -1))))))) {
                return;
            }
        }
        int position = mGapMarkerPosition;
        if (!ListenerUtil.mutListener.listen(18770)) {
            mGapMarkerPosition = -1;
        }
        if (!ListenerUtil.mutListener.listen(18777)) {
            if ((ListenerUtil.mutListener.listen(18775) ? (position >= getItemCount()) : (ListenerUtil.mutListener.listen(18774) ? (position <= getItemCount()) : (ListenerUtil.mutListener.listen(18773) ? (position > getItemCount()) : (ListenerUtil.mutListener.listen(18772) ? (position != getItemCount()) : (ListenerUtil.mutListener.listen(18771) ? (position == getItemCount()) : (position < getItemCount()))))))) {
                if (!ListenerUtil.mutListener.listen(18776)) {
                    notifyItemRemoved(position);
                }
            }
        }
    }

    /*
     * AsyncTask to load posts in the current tag
     */
    private boolean mIsTaskRunning = false;

    @SuppressLint("StaticFieldLeak")
    private class LoadPostsTask extends AsyncTask<Void, Void, Boolean> {

        private ReaderPostList mAllPosts;

        private boolean mCanRequestMorePostsTemp;

        private int mGapMarkerPositionTemp;

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(18778)) {
                mIsTaskRunning = true;
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(18779)) {
                mIsTaskRunning = false;
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int numExisting;
            switch(getPostListType()) {
                case TAG_PREVIEW:
                case TAG_FOLLOWED:
                case SEARCH_RESULTS:
                    if (!ListenerUtil.mutListener.listen(18780)) {
                        mAllPosts = ReaderPostTable.getPostsWithTag(mCurrentTag, MAX_ROWS, EXCLUDE_TEXT_COLUMN);
                    }
                    numExisting = ReaderPostTable.getNumPostsWithTag(mCurrentTag);
                    break;
                case BLOG_PREVIEW:
                    if (mCurrentFeedId != 0) {
                        if (!ListenerUtil.mutListener.listen(18782)) {
                            mAllPosts = ReaderPostTable.getPostsInFeed(mCurrentFeedId, MAX_ROWS, EXCLUDE_TEXT_COLUMN);
                        }
                        numExisting = ReaderPostTable.getNumPostsInFeed(mCurrentFeedId);
                    } else {
                        if (!ListenerUtil.mutListener.listen(18781)) {
                            mAllPosts = ReaderPostTable.getPostsInBlog(mCurrentBlogId, MAX_ROWS, EXCLUDE_TEXT_COLUMN);
                        }
                        numExisting = ReaderPostTable.getNumPostsInBlog(mCurrentBlogId);
                    }
                    break;
                default:
                    return false;
            }
            if (!ListenerUtil.mutListener.listen(18783)) {
                if (mPosts.isSameListWithBookmark(mAllPosts)) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(18789)) {
                // the user scrolls to the end of the list
                mCanRequestMorePostsTemp = ((ListenerUtil.mutListener.listen(18788) ? (numExisting >= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(18787) ? (numExisting <= ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(18786) ? (numExisting > ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(18785) ? (numExisting != ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (ListenerUtil.mutListener.listen(18784) ? (numExisting == ReaderConstants.READER_MAX_POSTS_TO_DISPLAY) : (numExisting < ReaderConstants.READER_MAX_POSTS_TO_DISPLAY)))))));
            }
            if (!ListenerUtil.mutListener.listen(18790)) {
                // determine whether a gap marker exists - only applies to tagged posts
                mGapMarkerPositionTemp = getGapMarkerPosition();
            }
            return true;
        }

        private int getGapMarkerPosition() {
            if (!ListenerUtil.mutListener.listen(18791)) {
                if (!getPostListType().isTagType()) {
                    return -1;
                }
            }
            ReaderBlogIdPostId gapMarkerIds = ReaderPostTable.getGapMarkerIdsForTag(mCurrentTag);
            if (!ListenerUtil.mutListener.listen(18792)) {
                if (gapMarkerIds == null) {
                    return -1;
                }
            }
            int gapMarkerPostPosition = mAllPosts.indexOfIds(gapMarkerIds);
            int gapMarkerPosition = -1;
            if (!ListenerUtil.mutListener.listen(18812)) {
                if ((ListenerUtil.mutListener.listen(18797) ? (gapMarkerPostPosition >= -1) : (ListenerUtil.mutListener.listen(18796) ? (gapMarkerPostPosition <= -1) : (ListenerUtil.mutListener.listen(18795) ? (gapMarkerPostPosition < -1) : (ListenerUtil.mutListener.listen(18794) ? (gapMarkerPostPosition != -1) : (ListenerUtil.mutListener.listen(18793) ? (gapMarkerPostPosition == -1) : (gapMarkerPostPosition > -1))))))) {
                    if (!ListenerUtil.mutListener.listen(18811)) {
                        // it can happen following a purge)
                        if (gapMarkerPostPosition == (ListenerUtil.mutListener.listen(18801) ? (mAllPosts.size() % 1) : (ListenerUtil.mutListener.listen(18800) ? (mAllPosts.size() / 1) : (ListenerUtil.mutListener.listen(18799) ? (mAllPosts.size() * 1) : (ListenerUtil.mutListener.listen(18798) ? (mAllPosts.size() + 1) : (mAllPosts.size() - 1)))))) {
                            if (!ListenerUtil.mutListener.listen(18809)) {
                                AppLog.w(AppLog.T.READER, "gap marker at/after last post, removed");
                            }
                            if (!ListenerUtil.mutListener.listen(18810)) {
                                ReaderPostTable.removeGapMarkerForTag(mCurrentTag);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(18806)) {
                                // we want the gap marker to appear *below* this post
                                gapMarkerPosition = (ListenerUtil.mutListener.listen(18805) ? (gapMarkerPostPosition % 1) : (ListenerUtil.mutListener.listen(18804) ? (gapMarkerPostPosition / 1) : (ListenerUtil.mutListener.listen(18803) ? (gapMarkerPostPosition * 1) : (ListenerUtil.mutListener.listen(18802) ? (gapMarkerPostPosition - 1) : (gapMarkerPostPosition + 1)))));
                            }
                            if (!ListenerUtil.mutListener.listen(18807)) {
                                // increment it if there are custom items at the top of the list (header)
                                gapMarkerPosition += getItemPositionOffset();
                            }
                            if (!ListenerUtil.mutListener.listen(18808)) {
                                AppLog.d(AppLog.T.READER, "gap marker at position " + gapMarkerPostPosition);
                            }
                        }
                    }
                }
            }
            return gapMarkerPosition;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!ListenerUtil.mutListener.listen(18818)) {
                if (result) {
                    if (!ListenerUtil.mutListener.listen(18813)) {
                        ReaderPostAdapter.this.mGapMarkerPosition = mGapMarkerPositionTemp;
                    }
                    if (!ListenerUtil.mutListener.listen(18814)) {
                        ReaderPostAdapter.this.mCanRequestMorePosts = mCanRequestMorePostsTemp;
                    }
                    if (!ListenerUtil.mutListener.listen(18815)) {
                        mPosts.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(18816)) {
                        mPosts.addAll(mAllPosts);
                    }
                    if (!ListenerUtil.mutListener.listen(18817)) {
                        notifyDataSetChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18820)) {
                if (mDataLoadedListener != null) {
                    if (!ListenerUtil.mutListener.listen(18819)) {
                        mDataLoadedListener.onDataLoaded(isEmpty());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18821)) {
                mIsTaskRunning = false;
            }
        }
    }
}
