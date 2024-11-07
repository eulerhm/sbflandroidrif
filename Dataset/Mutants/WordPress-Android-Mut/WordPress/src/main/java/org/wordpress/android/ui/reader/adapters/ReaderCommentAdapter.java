package org.wordpress.android.ui.reader.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.ReaderCommentTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.ReaderComment;
import org.wordpress.android.models.ReaderCommentList;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.ui.comments.CommentUtils;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.reader.ReaderActivityLauncher;
import org.wordpress.android.ui.reader.ReaderAnim;
import org.wordpress.android.ui.reader.ReaderInterfaces;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderCommentActions;
import org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuActionType;
import org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem;
import org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem.Divider;
import org.wordpress.android.ui.reader.adapters.ReaderCommentMenuActionAdapter.ReaderCommentMenuItem.PrimaryItemMenu;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderCommentLeveler;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils;
import org.wordpress.android.ui.reader.views.ReaderCommentsPostHeaderView;
import org.wordpress.android.ui.reader.views.ReaderIconCountView;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.ui.utils.UiString.UiStringRes;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource;
import org.wordpress.android.util.config.ReaderCommentsModerationFeatureConfig;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ReaderPost mPost;

    private boolean mMoreCommentsExist;

    private static final int MAX_INDENT_LEVEL = 2;

    private final int mIndentPerLevel;

    private final int mAvatarSz;

    private final int mContentWidth;

    private long mHighlightCommentId = 0;

    private long mReplyTargetComment = 0;

    private long mAnimateLikeCommentId = 0;

    private boolean mShowProgressForHighlightedComment = false;

    private final boolean mIsPrivatePost;

    private boolean mIsHeaderClickEnabled;

    private final int mColorHighlight;

    private final ColorStateList mReplyButtonHighlightedColor;

    private final ColorStateList mReplyButtonNormalColorColor;

    private static final int VIEW_TYPE_HEADER = 1;

    private static final int VIEW_TYPE_COMMENT = 2;

    private static final long ID_HEADER = -1L;

    private static final int NUM_HEADERS = 1;

    private SiteModel mPostsSite;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ImageManager mImageManager;

    @Inject
    ReaderTracker mReaderTracker;

    @Inject
    ThreadedCommentsUtils mThreadedCommentsUtils;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    @Inject
    UiHelpers mUiHelpers;

    @Inject
    ReaderCommentsModerationFeatureConfig mReaderCommentsModerationFeatureConfig;

    public interface RequestReplyListener {

        void onRequestReply(long commentId);
    }

    public interface CommentMenuActionListener {

        void onCommentMenuItemTapped(ReaderComment comment, ReaderCommentMenuActionType actionType);
    }

    private ReaderCommentList mComments = new ReaderCommentList();

    private RequestReplyListener mReplyListener;

    private CommentMenuActionListener mCommentMenuActionListener;

    private ReaderInterfaces.DataLoadedListener mDataLoadedListener;

    private ReaderActions.DataRequestedListener mDataRequestedListener;

    private PostHeaderHolder mHeaderHolder;

    class CommentHolder extends RecyclerView.ViewHolder {

        private final ViewGroup mCommentContainer;

        private final TextView mTxtAuthor;

        private final TextView mTxtText;

        private final TextView mTxtDate;

        private final ImageView mImgAvatar;

        private final View mSpacerIndent;

        private final View mSelectedCommentIndicator;

        private final View mTopCommentDivider;

        private final View mAuthorContainer;

        private final View mAuthorBadge;

        private final View mActionButtonContainer;

        private final ImageView mActionButton;

        private final ProgressBar mProgress;

        private final ViewGroup mReplyView;

        private final ImageView mReplyButtonIcon;

        private final TextView mReplyButtonLabel;

        private final ReaderIconCountView mCountLikes;

        CommentHolder(View view) {
            super(view);
            mCommentContainer = view.findViewById(R.id.comment_container);
            mSelectedCommentIndicator = view.findViewById(R.id.selected_comment_indicator);
            mTxtAuthor = view.findViewById(R.id.text_comment_author);
            mTxtText = view.findViewById(R.id.text_comment_text);
            mTxtDate = view.findViewById(R.id.text_comment_date);
            mImgAvatar = view.findViewById(R.id.image_comment_avatar);
            mSpacerIndent = view.findViewById(R.id.spacer_comment_indent);
            mProgress = view.findViewById(R.id.progress_comment);
            mTopCommentDivider = view.findViewById(R.id.divider);
            mAuthorContainer = view.findViewById(R.id.layout_author);
            mAuthorBadge = view.findViewById(R.id.author_badge);
            mActionButtonContainer = view.findViewById(R.id.comment_action_button_container);
            mActionButton = view.findViewById(R.id.comment_action_button);
            mReplyView = view.findViewById(R.id.reply_container);
            mReplyButtonLabel = view.findViewById(R.id.reply_button_label);
            mReplyButtonIcon = view.findViewById(R.id.reply_button_icon);
            mCountLikes = view.findViewById(R.id.count_likes);
            if (!ListenerUtil.mutListener.listen(18300)) {
                mThreadedCommentsUtils.setLinksClickable(mTxtText, mIsPrivatePost);
            }
        }
    }

    class PostHeaderHolder extends RecyclerView.ViewHolder {

        private final ReaderCommentsPostHeaderView mHeaderView;

        PostHeaderHolder(View view) {
            super(view);
            mHeaderView = (ReaderCommentsPostHeaderView) view;
        }
    }

    public ReaderCommentAdapter(Context context, ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(18301)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(18302)) {
            mPost = post;
        }
        mIsPrivatePost = mThreadedCommentsUtils.isPrivatePost(post);
        mIndentPerLevel = context.getResources().getDimensionPixelSize(R.dimen.reader_comment_indent_per_level);
        mAvatarSz = context.getResources().getDimensionPixelSize(R.dimen.avatar_sz_extra_small);
        if (!ListenerUtil.mutListener.listen(18303)) {
            mPostsSite = mSiteStore.getSiteBySiteId(post.blogId);
        }
        // calculate the max width of comment content
        int displayWidth = DisplayUtils.getWindowPixelWidth(context);
        int cardMargin = context.getResources().getDimensionPixelSize(R.dimen.reader_card_margin);
        int contentPadding = context.getResources().getDimensionPixelSize(R.dimen.reader_card_content_padding);
        int mediumMargin = context.getResources().getDimensionPixelSize(R.dimen.margin_medium);
        mContentWidth = (ListenerUtil.mutListener.listen(18327) ? ((ListenerUtil.mutListener.listen(18319) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) % ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18318) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) / ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18317) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) * ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18316) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) + ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) - ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))))))) % ((ListenerUtil.mutListener.listen(18323) ? (mediumMargin % 2) : (ListenerUtil.mutListener.listen(18322) ? (mediumMargin / 2) : (ListenerUtil.mutListener.listen(18321) ? (mediumMargin - 2) : (ListenerUtil.mutListener.listen(18320) ? (mediumMargin + 2) : (mediumMargin * 2))))))) : (ListenerUtil.mutListener.listen(18326) ? ((ListenerUtil.mutListener.listen(18319) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) % ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18318) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) / ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18317) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) * ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18316) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) + ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) - ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))))))) / ((ListenerUtil.mutListener.listen(18323) ? (mediumMargin % 2) : (ListenerUtil.mutListener.listen(18322) ? (mediumMargin / 2) : (ListenerUtil.mutListener.listen(18321) ? (mediumMargin - 2) : (ListenerUtil.mutListener.listen(18320) ? (mediumMargin + 2) : (mediumMargin * 2))))))) : (ListenerUtil.mutListener.listen(18325) ? ((ListenerUtil.mutListener.listen(18319) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) % ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18318) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) / ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18317) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) * ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18316) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) + ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) - ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))))))) * ((ListenerUtil.mutListener.listen(18323) ? (mediumMargin % 2) : (ListenerUtil.mutListener.listen(18322) ? (mediumMargin / 2) : (ListenerUtil.mutListener.listen(18321) ? (mediumMargin - 2) : (ListenerUtil.mutListener.listen(18320) ? (mediumMargin + 2) : (mediumMargin * 2))))))) : (ListenerUtil.mutListener.listen(18324) ? ((ListenerUtil.mutListener.listen(18319) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) % ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18318) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) / ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18317) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) * ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18316) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) + ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) - ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))))))) + ((ListenerUtil.mutListener.listen(18323) ? (mediumMargin % 2) : (ListenerUtil.mutListener.listen(18322) ? (mediumMargin / 2) : (ListenerUtil.mutListener.listen(18321) ? (mediumMargin - 2) : (ListenerUtil.mutListener.listen(18320) ? (mediumMargin + 2) : (mediumMargin * 2))))))) : ((ListenerUtil.mutListener.listen(18319) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) % ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18318) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) / ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18317) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) * ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : (ListenerUtil.mutListener.listen(18316) ? ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) + ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))) : ((ListenerUtil.mutListener.listen(18311) ? (displayWidth % ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18310) ? (displayWidth / ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18309) ? (displayWidth * ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (ListenerUtil.mutListener.listen(18308) ? (displayWidth + ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(18307) ? (cardMargin % 2) : (ListenerUtil.mutListener.listen(18306) ? (cardMargin / 2) : (ListenerUtil.mutListener.listen(18305) ? (cardMargin - 2) : (ListenerUtil.mutListener.listen(18304) ? (cardMargin + 2) : (cardMargin * 2))))))))))) - ((ListenerUtil.mutListener.listen(18315) ? (contentPadding % 2) : (ListenerUtil.mutListener.listen(18314) ? (contentPadding / 2) : (ListenerUtil.mutListener.listen(18313) ? (contentPadding - 2) : (ListenerUtil.mutListener.listen(18312) ? (contentPadding + 2) : (contentPadding * 2))))))))))) - ((ListenerUtil.mutListener.listen(18323) ? (mediumMargin % 2) : (ListenerUtil.mutListener.listen(18322) ? (mediumMargin / 2) : (ListenerUtil.mutListener.listen(18321) ? (mediumMargin - 2) : (ListenerUtil.mutListener.listen(18320) ? (mediumMargin + 2) : (mediumMargin * 2)))))))))));
        mColorHighlight = ColorUtils.setAlphaComponent(ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorPrimary), context.getResources().getInteger(R.integer.selected_list_item_opacity));
        mReplyButtonHighlightedColor = ContextExtensionsKt.getColorStateListFromAttribute(context, R.attr.colorPrimary);
        mReplyButtonNormalColorColor = ContextExtensionsKt.getColorStateListFromAttribute(context, R.attr.wpColorOnSurfaceMedium);
        if (!ListenerUtil.mutListener.listen(18328)) {
            setHasStableIds(true);
        }
    }

    public void setReplyListener(RequestReplyListener replyListener) {
        if (!ListenerUtil.mutListener.listen(18329)) {
            mReplyListener = replyListener;
        }
    }

    public void setCommentMenuActionListener(CommentMenuActionListener commentMenuActionListener) {
        if (!ListenerUtil.mutListener.listen(18330)) {
            mCommentMenuActionListener = commentMenuActionListener;
        }
    }

    public void setDataLoadedListener(ReaderInterfaces.DataLoadedListener dataLoadedListener) {
        if (!ListenerUtil.mutListener.listen(18331)) {
            mDataLoadedListener = dataLoadedListener;
        }
    }

    public void setDataRequestedListener(ReaderActions.DataRequestedListener dataRequestedListener) {
        if (!ListenerUtil.mutListener.listen(18332)) {
            mDataRequestedListener = dataRequestedListener;
        }
    }

    public void enableHeaderClicks() {
        if (!ListenerUtil.mutListener.listen(18333)) {
            mIsHeaderClickEnabled = true;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (ListenerUtil.mutListener.listen(18338) ? (position >= 0) : (ListenerUtil.mutListener.listen(18337) ? (position <= 0) : (ListenerUtil.mutListener.listen(18336) ? (position > 0) : (ListenerUtil.mutListener.listen(18335) ? (position < 0) : (ListenerUtil.mutListener.listen(18334) ? (position != 0) : (position == 0)))))) ? VIEW_TYPE_HEADER : VIEW_TYPE_COMMENT;
    }

    public void refreshComments() {
        if (!ListenerUtil.mutListener.listen(18340)) {
            if (mIsTaskRunning) {
                if (!ListenerUtil.mutListener.listen(18339)) {
                    AppLog.w(T.READER, "reader comment adapter > Load comments task already running");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18341)) {
            new LoadCommentsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public int getItemCount() {
        return mComments.size() + NUM_HEADERS;
    }

    public boolean isEmpty() {
        return mComments.size() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch(viewType) {
            case VIEW_TYPE_HEADER:
                View headerView = new ReaderCommentsPostHeaderView(parent.getContext());
                if (!ListenerUtil.mutListener.listen(18342)) {
                    headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
                return new PostHeaderHolder(headerView);
            default:
                View commentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reader_listitem_comment, parent, false);
                return new CommentHolder(commentView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(18348)) {
            if (holder instanceof PostHeaderHolder) {
                if (!ListenerUtil.mutListener.listen(18343)) {
                    mHeaderHolder = (PostHeaderHolder) holder;
                }
                if (!ListenerUtil.mutListener.listen(18344)) {
                    mHeaderHolder.mHeaderView.setPost(mPost);
                }
                if (!ListenerUtil.mutListener.listen(18347)) {
                    if (mIsHeaderClickEnabled) {
                        if (!ListenerUtil.mutListener.listen(18346)) {
                            mHeaderHolder.mHeaderView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    if (!ListenerUtil.mutListener.listen(18345)) {
                                        ReaderActivityLauncher.showReaderPostDetail(view.getContext(), mPost.blogId, mPost.postId);
                                    }
                                }
                            });
                        }
                    }
                }
                return;
            }
        }
        final ReaderComment comment = getItem(position);
        if (!ListenerUtil.mutListener.listen(18349)) {
            if (comment == null) {
                return;
            }
        }
        final CommentHolder commentHolder = (CommentHolder) holder;
        if (!ListenerUtil.mutListener.listen(18350)) {
            commentHolder.mTxtAuthor.setText(comment.getAuthorName());
        }
        java.util.Date dtPublished;
        if ((ListenerUtil.mutListener.listen(18351) ? (mShowProgressForHighlightedComment || mHighlightCommentId == comment.commentId) : (mShowProgressForHighlightedComment && mHighlightCommentId == comment.commentId))) {
            dtPublished = new Date();
        } else {
            dtPublished = DateTimeUtils.dateFromIso8601(comment.getPublished());
        }
        if (!ListenerUtil.mutListener.listen(18352)) {
            commentHolder.mTxtDate.setText(DateTimeUtils.javaDateToTimeSpan(dtPublished, WordPress.getContext()));
        }
        String avatarUrl = GravatarUtils.fixGravatarUrl(comment.getAuthorAvatar(), mAvatarSz);
        if (!ListenerUtil.mutListener.listen(18353)) {
            mImageManager.loadIntoCircle(commentHolder.mImgAvatar, ImageType.AVATAR, avatarUrl);
        }
        if (!ListenerUtil.mutListener.listen(18359)) {
            // tapping avatar or author name opens blog preview
            if (comment.hasAuthorBlogId()) {
                View.OnClickListener authorListener = new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (!ListenerUtil.mutListener.listen(18356)) {
                            ReaderActivityLauncher.showReaderBlogPreview(view.getContext(), comment.authorBlogId, mPost.isFollowedByCurrentUser, ReaderTracker.SOURCE_COMMENT, mReaderTracker);
                        }
                    }
                };
                if (!ListenerUtil.mutListener.listen(18357)) {
                    commentHolder.mAuthorContainer.setOnClickListener(authorListener);
                }
                if (!ListenerUtil.mutListener.listen(18358)) {
                    commentHolder.mAuthorContainer.setOnClickListener(authorListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18354)) {
                    commentHolder.mAuthorContainer.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(18355)) {
                    commentHolder.mAuthorContainer.setOnClickListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18362)) {
            // author name uses different color for comments from the post's author
            if (comment.authorId == mPost.authorId) {
                if (!ListenerUtil.mutListener.listen(18361)) {
                    commentHolder.mAuthorBadge.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18360)) {
                    commentHolder.mAuthorBadge.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18369)) {
            if ((ListenerUtil.mutListener.listen(18364) ? (mReaderCommentsModerationFeatureConfig.isEnabled() || ((ListenerUtil.mutListener.listen(18363) ? (mPostsSite != null || mPostsSite.getHasCapabilityEditOthersPosts()) : (mPostsSite != null && mPostsSite.getHasCapabilityEditOthersPosts())))) : (mReaderCommentsModerationFeatureConfig.isEnabled() && ((ListenerUtil.mutListener.listen(18363) ? (mPostsSite != null || mPostsSite.getHasCapabilityEditOthersPosts()) : (mPostsSite != null && mPostsSite.getHasCapabilityEditOthersPosts())))))) {
                if (!ListenerUtil.mutListener.listen(18367)) {
                    commentHolder.mActionButton.setImageResource(R.drawable.ic_more_vert_white_24dp);
                }
                if (!ListenerUtil.mutListener.listen(18368)) {
                    commentHolder.mActionButtonContainer.setOnClickListener(v -> {
                        Context context = commentHolder.mActionButton.getContext();
                        ListPopupWindow menuPopup = new ListPopupWindow(context);
                        ArrayList<ReaderCommentMenuItem> actions = new ArrayList<>();
                        actions.add(new PrimaryItemMenu(ReaderCommentMenuActionType.UNAPPROVE, new UiStringRes(R.string.reader_comment_menu_unapprove), new UiStringRes(R.string.reader_comment_menu_unapprove), R.drawable.ic_cross_in_circle_white_24dp));
                        actions.add(new PrimaryItemMenu(ReaderCommentMenuActionType.SPAM, new UiStringRes(R.string.reader_comment_menu_spam), new UiStringRes(R.string.reader_comment_menu_spam), R.drawable.ic_spam_white_24dp));
                        actions.add(new PrimaryItemMenu(ReaderCommentMenuActionType.TRASH, new UiStringRes(R.string.reader_comment_menu_trash), new UiStringRes(R.string.reader_comment_menu_trash), R.drawable.ic_trash_white_24dp));
                        actions.add(new Divider());
                        actions.add(new PrimaryItemMenu(ReaderCommentMenuActionType.EDIT, new UiStringRes(R.string.reader_comment_menu_edit), new UiStringRes(R.string.reader_comment_menu_edit), R.drawable.ic_pencil_white_24dp));
                        actions.add(new PrimaryItemMenu(ReaderCommentMenuActionType.SHARE, new UiStringRes(R.string.reader_comment_menu_share), new UiStringRes(R.string.reader_comment_menu_share), R.drawable.ic_share_white_24dp));
                        menuPopup.setWidth(context.getResources().getDimensionPixelSize(R.dimen.menu_item_width));
                        menuPopup.setAdapter(new ReaderCommentMenuActionAdapter(context, mUiHelpers, actions));
                        menuPopup.setDropDownGravity(Gravity.END);
                        menuPopup.setAnchorView(commentHolder.mActionButton);
                        menuPopup.setModal(true);
                        menuPopup.setOnItemClickListener((parent, view, position1, id) -> {
                            mCommentMenuActionListener.onCommentMenuItemTapped(comment, actions.get(position1).getType());
                            menuPopup.dismiss();
                        });
                        menuPopup.show();
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18365)) {
                    commentHolder.mActionButton.setImageResource(R.drawable.ic_share_white_24dp);
                }
                if (!ListenerUtil.mutListener.listen(18366)) {
                    commentHolder.mActionButtonContainer.setOnClickListener(v -> mCommentMenuActionListener.onCommentMenuItemTapped(comment, ReaderCommentMenuActionType.SHARE));
                }
            }
        }
        // show indentation spacer for comments with parents and indent it based on comment level
        int indentWidth;
        if ((ListenerUtil.mutListener.listen(18375) ? (comment.parentId != 0 || (ListenerUtil.mutListener.listen(18374) ? (comment.level >= 0) : (ListenerUtil.mutListener.listen(18373) ? (comment.level <= 0) : (ListenerUtil.mutListener.listen(18372) ? (comment.level < 0) : (ListenerUtil.mutListener.listen(18371) ? (comment.level != 0) : (ListenerUtil.mutListener.listen(18370) ? (comment.level == 0) : (comment.level > 0))))))) : (comment.parentId != 0 && (ListenerUtil.mutListener.listen(18374) ? (comment.level >= 0) : (ListenerUtil.mutListener.listen(18373) ? (comment.level <= 0) : (ListenerUtil.mutListener.listen(18372) ? (comment.level < 0) : (ListenerUtil.mutListener.listen(18371) ? (comment.level != 0) : (ListenerUtil.mutListener.listen(18370) ? (comment.level == 0) : (comment.level > 0))))))))) {
            indentWidth = (ListenerUtil.mutListener.listen(18381) ? (Math.min(MAX_INDENT_LEVEL, comment.level) % mIndentPerLevel) : (ListenerUtil.mutListener.listen(18380) ? (Math.min(MAX_INDENT_LEVEL, comment.level) / mIndentPerLevel) : (ListenerUtil.mutListener.listen(18379) ? (Math.min(MAX_INDENT_LEVEL, comment.level) - mIndentPerLevel) : (ListenerUtil.mutListener.listen(18378) ? (Math.min(MAX_INDENT_LEVEL, comment.level) + mIndentPerLevel) : (Math.min(MAX_INDENT_LEVEL, comment.level) * mIndentPerLevel)))));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) commentHolder.mSpacerIndent.getLayoutParams();
            if (!ListenerUtil.mutListener.listen(18382)) {
                params.width = indentWidth;
            }
            if (!ListenerUtil.mutListener.listen(18383)) {
                commentHolder.mSpacerIndent.setVisibility(View.VISIBLE);
            }
            if (!ListenerUtil.mutListener.listen(18384)) {
                commentHolder.mTopCommentDivider.setVisibility(View.GONE);
            }
        } else {
            indentWidth = 0;
            if (!ListenerUtil.mutListener.listen(18376)) {
                commentHolder.mSpacerIndent.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(18377)) {
                commentHolder.mTopCommentDivider.setVisibility(View.VISIBLE);
            }
        }
        int maxImageWidth = (ListenerUtil.mutListener.listen(18388) ? (mContentWidth % indentWidth) : (ListenerUtil.mutListener.listen(18387) ? (mContentWidth / indentWidth) : (ListenerUtil.mutListener.listen(18386) ? (mContentWidth * indentWidth) : (ListenerUtil.mutListener.listen(18385) ? (mContentWidth + indentWidth) : (mContentWidth - indentWidth)))));
        String renderingError = commentHolder.mTxtText.getResources().getString(R.string.comment_unable_to_show_error);
        if (!ListenerUtil.mutListener.listen(18389)) {
            CommentUtils.displayHtmlComment(commentHolder.mTxtText, comment.getText(), maxImageWidth, commentHolder.mTxtText.getLineHeight(), renderingError);
        }
        if (!ListenerUtil.mutListener.listen(18402)) {
            // different background for highlighted comment, with optional progress bar
            if ((ListenerUtil.mutListener.listen(18395) ? ((ListenerUtil.mutListener.listen(18394) ? (mHighlightCommentId >= 0) : (ListenerUtil.mutListener.listen(18393) ? (mHighlightCommentId <= 0) : (ListenerUtil.mutListener.listen(18392) ? (mHighlightCommentId > 0) : (ListenerUtil.mutListener.listen(18391) ? (mHighlightCommentId < 0) : (ListenerUtil.mutListener.listen(18390) ? (mHighlightCommentId == 0) : (mHighlightCommentId != 0)))))) || mHighlightCommentId == comment.commentId) : ((ListenerUtil.mutListener.listen(18394) ? (mHighlightCommentId >= 0) : (ListenerUtil.mutListener.listen(18393) ? (mHighlightCommentId <= 0) : (ListenerUtil.mutListener.listen(18392) ? (mHighlightCommentId > 0) : (ListenerUtil.mutListener.listen(18391) ? (mHighlightCommentId < 0) : (ListenerUtil.mutListener.listen(18390) ? (mHighlightCommentId == 0) : (mHighlightCommentId != 0)))))) && mHighlightCommentId == comment.commentId))) {
                if (!ListenerUtil.mutListener.listen(18399)) {
                    commentHolder.mCommentContainer.setBackgroundColor(mColorHighlight);
                }
                if (!ListenerUtil.mutListener.listen(18400)) {
                    commentHolder.mProgress.setVisibility(mShowProgressForHighlightedComment ? View.VISIBLE : View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(18401)) {
                    commentHolder.mSelectedCommentIndicator.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18396)) {
                    commentHolder.mCommentContainer.setBackgroundColor(0);
                }
                if (!ListenerUtil.mutListener.listen(18397)) {
                    commentHolder.mProgress.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(18398)) {
                    commentHolder.mSelectedCommentIndicator.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18413)) {
            if ((ListenerUtil.mutListener.listen(18408) ? ((ListenerUtil.mutListener.listen(18407) ? (mReplyTargetComment >= 0) : (ListenerUtil.mutListener.listen(18406) ? (mReplyTargetComment <= 0) : (ListenerUtil.mutListener.listen(18405) ? (mReplyTargetComment > 0) : (ListenerUtil.mutListener.listen(18404) ? (mReplyTargetComment < 0) : (ListenerUtil.mutListener.listen(18403) ? (mReplyTargetComment == 0) : (mReplyTargetComment != 0)))))) || mReplyTargetComment == comment.commentId) : ((ListenerUtil.mutListener.listen(18407) ? (mReplyTargetComment >= 0) : (ListenerUtil.mutListener.listen(18406) ? (mReplyTargetComment <= 0) : (ListenerUtil.mutListener.listen(18405) ? (mReplyTargetComment > 0) : (ListenerUtil.mutListener.listen(18404) ? (mReplyTargetComment < 0) : (ListenerUtil.mutListener.listen(18403) ? (mReplyTargetComment == 0) : (mReplyTargetComment != 0)))))) && mReplyTargetComment == comment.commentId))) {
                if (!ListenerUtil.mutListener.listen(18411)) {
                    commentHolder.mReplyButtonLabel.setTextColor(mReplyButtonHighlightedColor);
                }
                if (!ListenerUtil.mutListener.listen(18412)) {
                    commentHolder.mReplyButtonIcon.setImageTintList(mReplyButtonHighlightedColor);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18409)) {
                    commentHolder.mReplyButtonLabel.setTextColor(mReplyButtonNormalColorColor);
                }
                if (!ListenerUtil.mutListener.listen(18410)) {
                    commentHolder.mReplyButtonIcon.setImageTintList(mReplyButtonNormalColorColor);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18428)) {
            if (!mAccountStore.hasAccessToken()) {
                if (!ListenerUtil.mutListener.listen(18427)) {
                    commentHolder.mReplyView.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18416)) {
                    // tapping reply tells activity to show reply box
                    commentHolder.mReplyView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(18415)) {
                                if (mReplyListener != null) {
                                    if (!ListenerUtil.mutListener.listen(18414)) {
                                        mReplyListener.onRequestReply(comment.commentId);
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(18426)) {
                    if ((ListenerUtil.mutListener.listen(18422) ? ((ListenerUtil.mutListener.listen(18421) ? (mAnimateLikeCommentId >= 0) : (ListenerUtil.mutListener.listen(18420) ? (mAnimateLikeCommentId <= 0) : (ListenerUtil.mutListener.listen(18419) ? (mAnimateLikeCommentId > 0) : (ListenerUtil.mutListener.listen(18418) ? (mAnimateLikeCommentId < 0) : (ListenerUtil.mutListener.listen(18417) ? (mAnimateLikeCommentId == 0) : (mAnimateLikeCommentId != 0)))))) || mAnimateLikeCommentId == comment.commentId) : ((ListenerUtil.mutListener.listen(18421) ? (mAnimateLikeCommentId >= 0) : (ListenerUtil.mutListener.listen(18420) ? (mAnimateLikeCommentId <= 0) : (ListenerUtil.mutListener.listen(18419) ? (mAnimateLikeCommentId > 0) : (ListenerUtil.mutListener.listen(18418) ? (mAnimateLikeCommentId < 0) : (ListenerUtil.mutListener.listen(18417) ? (mAnimateLikeCommentId == 0) : (mAnimateLikeCommentId != 0)))))) && mAnimateLikeCommentId == comment.commentId))) {
                        if (!ListenerUtil.mutListener.listen(18424)) {
                            // simulate tapping on the "Like" button. Add a delay to help the user notice it.
                            commentHolder.mCountLikes.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(18423)) {
                                        ReaderAnim.animateLikeButton(commentHolder.mCountLikes.getImageView(), true);
                                    }
                                }
                            }, 400);
                        }
                        if (!ListenerUtil.mutListener.listen(18425)) {
                            // clear the "command" to like a comment
                            mAnimateLikeCommentId = 0;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18429)) {
            showLikeStatus(commentHolder, position);
        }
        if (!ListenerUtil.mutListener.listen(18442)) {
            // fire request to load more
            if ((ListenerUtil.mutListener.listen(18440) ? ((ListenerUtil.mutListener.listen(18430) ? (mMoreCommentsExist || mDataRequestedListener != null) : (mMoreCommentsExist && mDataRequestedListener != null)) || ((ListenerUtil.mutListener.listen(18439) ? (position <= (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18438) ? (position > (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18437) ? (position < (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18436) ? (position != (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18435) ? (position == (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (position >= (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS))))))))))))) : ((ListenerUtil.mutListener.listen(18430) ? (mMoreCommentsExist || mDataRequestedListener != null) : (mMoreCommentsExist && mDataRequestedListener != null)) && ((ListenerUtil.mutListener.listen(18439) ? (position <= (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18438) ? (position > (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18437) ? (position < (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18436) ? (position != (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (ListenerUtil.mutListener.listen(18435) ? (position == (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS)))))) : (position >= (ListenerUtil.mutListener.listen(18434) ? (getItemCount() % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18433) ? (getItemCount() / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18432) ? (getItemCount() * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18431) ? (getItemCount() + NUM_HEADERS) : (getItemCount() - NUM_HEADERS))))))))))))))) {
                if (!ListenerUtil.mutListener.listen(18441)) {
                    mDataRequestedListener.onRequestData();
                }
            }
        }
    }

    @Override
    public long getItemId(int position) {
        switch(getItemViewType(position)) {
            case VIEW_TYPE_HEADER:
                return ID_HEADER;
            default:
                ReaderComment comment = getItem(position);
                return comment != null ? comment.commentId : 0;
        }
    }

    private ReaderComment getItem(int position) {
        return (ListenerUtil.mutListener.listen(18447) ? (position >= 0) : (ListenerUtil.mutListener.listen(18446) ? (position <= 0) : (ListenerUtil.mutListener.listen(18445) ? (position > 0) : (ListenerUtil.mutListener.listen(18444) ? (position < 0) : (ListenerUtil.mutListener.listen(18443) ? (position != 0) : (position == 0)))))) ? null : mComments.get((ListenerUtil.mutListener.listen(18451) ? (position % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18450) ? (position / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18449) ? (position * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18448) ? (position + NUM_HEADERS) : (position - NUM_HEADERS))))));
    }

    /*
     * refresh the post from the database - used to reflect changes to comment counts, etc.
     */
    public void refreshPost() {
        if (!ListenerUtil.mutListener.listen(18453)) {
            if (mPost != null) {
                ReaderPost post = ReaderPostTable.getBlogPost(mPost.blogId, mPost.postId, true);
                if (!ListenerUtil.mutListener.listen(18452)) {
                    setPost(post);
                }
            }
        }
    }

    private void showLikeStatus(final CommentHolder holder, int position) {
        ReaderComment comment = getItem(position);
        if (!ListenerUtil.mutListener.listen(18454)) {
            if (comment == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18466)) {
            if (mPost.canLikePost()) {
                if (!ListenerUtil.mutListener.listen(18457)) {
                    holder.mCountLikes.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(18458)) {
                    holder.mCountLikes.setSelected(comment.isLikedByCurrentUser);
                }
                if (!ListenerUtil.mutListener.listen(18459)) {
                    holder.mCountLikes.setTextCount(comment.numLikes);
                }
                if (!ListenerUtil.mutListener.listen(18460)) {
                    holder.mCountLikes.setContentDescription(ReaderUtils.getLongLikeLabelText(holder.mCountLikes.getContext(), comment.numLikes, comment.isLikedByCurrentUser));
                }
                if (!ListenerUtil.mutListener.listen(18465)) {
                    if (!mAccountStore.hasAccessToken()) {
                        if (!ListenerUtil.mutListener.listen(18464)) {
                            holder.mCountLikes.setEnabled(false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(18461)) {
                            holder.mCountLikes.setEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(18463)) {
                            holder.mCountLikes.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    int clickedPosition = holder.getAdapterPosition();
                                    if (!ListenerUtil.mutListener.listen(18462)) {
                                        toggleLike(v.getContext(), holder, clickedPosition);
                                    }
                                }
                            });
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18455)) {
                    holder.mCountLikes.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(18456)) {
                    holder.mCountLikes.setOnClickListener(null);
                }
            }
        }
    }

    private void toggleLike(Context context, CommentHolder holder, int position) {
        if (!ListenerUtil.mutListener.listen(18467)) {
            if (!NetworkUtils.checkConnection(context)) {
                return;
            }
        }
        ReaderComment comment = getItem(position);
        if (!ListenerUtil.mutListener.listen(18469)) {
            if (comment == null) {
                if (!ListenerUtil.mutListener.listen(18468)) {
                    ToastUtils.showToast(context, R.string.reader_toast_err_generic);
                }
                return;
            }
        }
        boolean isAskingToLike = !comment.isLikedByCurrentUser;
        if (!ListenerUtil.mutListener.listen(18470)) {
            ReaderAnim.animateLikeButton(holder.mCountLikes.getImageView(), isAskingToLike);
        }
        if (!ListenerUtil.mutListener.listen(18472)) {
            if (!ReaderCommentActions.performLikeAction(comment, isAskingToLike, mAccountStore.getAccount().getUserId())) {
                if (!ListenerUtil.mutListener.listen(18471)) {
                    ToastUtils.showToast(context, R.string.reader_toast_err_generic);
                }
                return;
            }
        }
        ReaderComment updatedComment = ReaderCommentTable.getComment(comment.blogId, comment.postId, comment.commentId);
        if (!ListenerUtil.mutListener.listen(18479)) {
            if (updatedComment != null) {
                if (!ListenerUtil.mutListener.listen(18477)) {
                    mComments.set((ListenerUtil.mutListener.listen(18476) ? (position % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18475) ? (position / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18474) ? (position * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18473) ? (position + NUM_HEADERS) : (position - NUM_HEADERS))))), updatedComment);
                }
                if (!ListenerUtil.mutListener.listen(18478)) {
                    showLikeStatus(holder, position);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18480)) {
            mReaderTracker.trackPost(isAskingToLike ? AnalyticsTracker.Stat.READER_ARTICLE_COMMENT_LIKED : AnalyticsTracker.Stat.READER_ARTICLE_COMMENT_UNLIKED, mPost);
        }
        if (!ListenerUtil.mutListener.listen(18481)) {
            mReaderTracker.trackPost(isAskingToLike ? AnalyticsTracker.Stat.COMMENT_LIKED : AnalyticsTracker.Stat.COMMENT_UNLIKED, mPost, AnalyticsCommentActionSource.READER.toString());
        }
    }

    public boolean refreshComment(long commentId) {
        int position = positionOfCommentId(commentId);
        if (!ListenerUtil.mutListener.listen(18487)) {
            if ((ListenerUtil.mutListener.listen(18486) ? (position >= -1) : (ListenerUtil.mutListener.listen(18485) ? (position <= -1) : (ListenerUtil.mutListener.listen(18484) ? (position > -1) : (ListenerUtil.mutListener.listen(18483) ? (position < -1) : (ListenerUtil.mutListener.listen(18482) ? (position != -1) : (position == -1))))))) {
                return false;
            }
        }
        ReaderComment comment = getItem(position);
        if (!ListenerUtil.mutListener.listen(18488)) {
            if (comment == null) {
                return false;
            }
        }
        ReaderComment updatedComment = ReaderCommentTable.getComment(comment.blogId, comment.postId, comment.commentId);
        if (!ListenerUtil.mutListener.listen(18496)) {
            if (updatedComment != null) {
                if (!ListenerUtil.mutListener.listen(18489)) {
                    // copy the comment level over since loading from the DB always has it as 0
                    updatedComment.level = comment.level;
                }
                if (!ListenerUtil.mutListener.listen(18494)) {
                    mComments.set((ListenerUtil.mutListener.listen(18493) ? (position % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18492) ? (position / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18491) ? (position * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18490) ? (position + NUM_HEADERS) : (position - NUM_HEADERS))))), updatedComment);
                }
                if (!ListenerUtil.mutListener.listen(18495)) {
                    notifyItemChanged(position);
                }
            }
        }
        return true;
    }

    /*
     * called from post detail activity when user submits a comment
     */
    public void addComment(ReaderComment comment) {
        if (!ListenerUtil.mutListener.listen(18497)) {
            if (comment == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18501)) {
            // appears under its parent and is correctly indented
            if (comment.parentId == 0) {
                if (!ListenerUtil.mutListener.listen(18499)) {
                    mComments.add(comment);
                }
                if (!ListenerUtil.mutListener.listen(18500)) {
                    notifyDataSetChanged();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18498)) {
                    refreshComments();
                }
            }
        }
    }

    /*
     * called from post detail when submitted a comment fails - this removes the "fake" comment
     * that was inserted while the API call was still being processed
     */
    public void removeComment(long commentId) {
        if (!ListenerUtil.mutListener.listen(18508)) {
            if ((ListenerUtil.mutListener.listen(18506) ? (commentId >= mHighlightCommentId) : (ListenerUtil.mutListener.listen(18505) ? (commentId <= mHighlightCommentId) : (ListenerUtil.mutListener.listen(18504) ? (commentId > mHighlightCommentId) : (ListenerUtil.mutListener.listen(18503) ? (commentId < mHighlightCommentId) : (ListenerUtil.mutListener.listen(18502) ? (commentId != mHighlightCommentId) : (commentId == mHighlightCommentId))))))) {
                if (!ListenerUtil.mutListener.listen(18507)) {
                    setHighlightCommentId(0, false);
                }
            }
        }
        int index = mComments.indexOfCommentId(commentId);
        if (!ListenerUtil.mutListener.listen(18517)) {
            if ((ListenerUtil.mutListener.listen(18513) ? (index >= -1) : (ListenerUtil.mutListener.listen(18512) ? (index <= -1) : (ListenerUtil.mutListener.listen(18511) ? (index < -1) : (ListenerUtil.mutListener.listen(18510) ? (index != -1) : (ListenerUtil.mutListener.listen(18509) ? (index == -1) : (index > -1))))))) {
                if (!ListenerUtil.mutListener.listen(18514)) {
                    mComments.remove(index);
                }
                if (!ListenerUtil.mutListener.listen(18515)) {
                    // re-level comments
                    mComments = new ReaderCommentLeveler(mComments).createLevelList();
                }
                if (!ListenerUtil.mutListener.listen(18516)) {
                    notifyDataSetChanged();
                }
            }
        }
    }

    /*
     * replace the comment that has the passed commentId with another comment
     */
    public void replaceComment(long commentId, ReaderComment comment) {
        int positionOfTargetComment = positionOfCommentId(comment.commentId);
        if (!ListenerUtil.mutListener.listen(18532)) {
            if ((ListenerUtil.mutListener.listen(18522) ? (positionOfTargetComment >= -1) : (ListenerUtil.mutListener.listen(18521) ? (positionOfTargetComment <= -1) : (ListenerUtil.mutListener.listen(18520) ? (positionOfTargetComment > -1) : (ListenerUtil.mutListener.listen(18519) ? (positionOfTargetComment < -1) : (ListenerUtil.mutListener.listen(18518) ? (positionOfTargetComment != -1) : (positionOfTargetComment == -1))))))) {
                int position = positionOfCommentId(commentId);
                if (!ListenerUtil.mutListener.listen(18531)) {
                    if ((ListenerUtil.mutListener.listen(18529) ? ((ListenerUtil.mutListener.listen(18528) ? (position >= -1) : (ListenerUtil.mutListener.listen(18527) ? (position <= -1) : (ListenerUtil.mutListener.listen(18526) ? (position < -1) : (ListenerUtil.mutListener.listen(18525) ? (position != -1) : (ListenerUtil.mutListener.listen(18524) ? (position == -1) : (position > -1)))))) || mComments.replaceComment(commentId, comment)) : ((ListenerUtil.mutListener.listen(18528) ? (position >= -1) : (ListenerUtil.mutListener.listen(18527) ? (position <= -1) : (ListenerUtil.mutListener.listen(18526) ? (position < -1) : (ListenerUtil.mutListener.listen(18525) ? (position != -1) : (ListenerUtil.mutListener.listen(18524) ? (position == -1) : (position > -1)))))) && mComments.replaceComment(commentId, comment)))) {
                        if (!ListenerUtil.mutListener.listen(18530)) {
                            notifyItemChanged(position);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18523)) {
                    removeComment(commentId);
                }
            }
        }
    }

    /*
     * sets the passed comment as highlighted with a different background color and an optional
     * progress bar (used when posting new comments) - note that we don't call notifyDataSetChanged()
     * here since in most cases it's unnecessary, so we leave it up to the caller to do that
     */
    public void setHighlightCommentId(long commentId, boolean showProgress) {
        if (!ListenerUtil.mutListener.listen(18533)) {
            mHighlightCommentId = commentId;
        }
        if (!ListenerUtil.mutListener.listen(18534)) {
            mShowProgressForHighlightedComment = showProgress;
        }
    }

    public void setReplyTargetComment(long commentId) {
        if (!ListenerUtil.mutListener.listen(18535)) {
            mReplyTargetComment = commentId;
        }
    }

    /*
     * returns the position of the passed comment in the adapter, taking the header into account
     */
    public int positionOfCommentId(long commentId) {
        int index = mComments.indexOfCommentId(commentId);
        return (ListenerUtil.mutListener.listen(18540) ? (index >= -1) : (ListenerUtil.mutListener.listen(18539) ? (index <= -1) : (ListenerUtil.mutListener.listen(18538) ? (index > -1) : (ListenerUtil.mutListener.listen(18537) ? (index < -1) : (ListenerUtil.mutListener.listen(18536) ? (index != -1) : (index == -1)))))) ? -1 : (ListenerUtil.mutListener.listen(18544) ? (index % NUM_HEADERS) : (ListenerUtil.mutListener.listen(18543) ? (index / NUM_HEADERS) : (ListenerUtil.mutListener.listen(18542) ? (index * NUM_HEADERS) : (ListenerUtil.mutListener.listen(18541) ? (index - NUM_HEADERS) : (index + NUM_HEADERS)))));
    }

    /*
     * sets the passed comment as the one to perform a "Like" on when the list comment list has completed loading
     */
    public void setAnimateLikeCommentId(long commentId) {
        if (!ListenerUtil.mutListener.listen(18545)) {
            mAnimateLikeCommentId = commentId;
        }
    }

    /*
     * AsyncTask to load comments for this post
     */
    private boolean mIsTaskRunning = false;

    @SuppressLint("StaticFieldLeak")
    private class LoadCommentsTask extends AsyncTask<Void, Void, Boolean> {

        private ReaderCommentList mTmpComments;

        private boolean mTmpMoreCommentsExist;

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(18546)) {
                mIsTaskRunning = true;
            }
        }

        @Override
        protected void onCancelled() {
            if (!ListenerUtil.mutListener.listen(18547)) {
                mIsTaskRunning = false;
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if (!ListenerUtil.mutListener.listen(18548)) {
                if (mPost == null) {
                    return false;
                }
            }
            // locally for this post
            int numServerComments = ReaderPostTable.getNumCommentsForPost(mPost);
            int numLocalComments = ReaderCommentTable.getNumCommentsForPost(mPost);
            if (!ListenerUtil.mutListener.listen(18554)) {
                mTmpMoreCommentsExist = ((ListenerUtil.mutListener.listen(18553) ? (numServerComments >= numLocalComments) : (ListenerUtil.mutListener.listen(18552) ? (numServerComments <= numLocalComments) : (ListenerUtil.mutListener.listen(18551) ? (numServerComments < numLocalComments) : (ListenerUtil.mutListener.listen(18550) ? (numServerComments != numLocalComments) : (ListenerUtil.mutListener.listen(18549) ? (numServerComments == numLocalComments) : (numServerComments > numLocalComments)))))));
            }
            if (!ListenerUtil.mutListener.listen(18555)) {
                mTmpComments = ReaderCommentTable.getCommentsForPost(mPost);
            }
            return !mComments.isSameList(mTmpComments);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!ListenerUtil.mutListener.listen(18556)) {
                mMoreCommentsExist = mTmpMoreCommentsExist;
            }
            if (!ListenerUtil.mutListener.listen(18559)) {
                if (result) {
                    if (!ListenerUtil.mutListener.listen(18557)) {
                        // assign the comments with children sorted under their parents and indent levels applied
                        mComments = new ReaderCommentLeveler(mTmpComments).createLevelList();
                    }
                    if (!ListenerUtil.mutListener.listen(18558)) {
                        notifyDataSetChanged();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18561)) {
                if (mDataLoadedListener != null) {
                    if (!ListenerUtil.mutListener.listen(18560)) {
                        mDataLoadedListener.onDataLoaded(isEmpty());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(18562)) {
                mIsTaskRunning = false;
            }
        }
    }

    /*
     * Set a post to adapter and update relevant information in the post header
     */
    public void setPost(ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(18565)) {
            if (post != null) {
                if (!ListenerUtil.mutListener.listen(18563)) {
                    mPost = post;
                }
                if (!ListenerUtil.mutListener.listen(18564)) {
                    // notify header to update itself
                    notifyItemChanged(0);
                }
            }
        }
    }
}
