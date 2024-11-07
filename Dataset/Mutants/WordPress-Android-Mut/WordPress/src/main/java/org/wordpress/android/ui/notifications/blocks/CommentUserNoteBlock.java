package org.wordpress.android.ui.notifications.blocks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.fluxc.tools.FormattableContent;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.extensions.ContextExtensionsKt;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// A user block with slightly different formatting for display in a comment detail
public class CommentUserNoteBlock extends UserNoteBlock {

    private static final String EMPTY_LINE = "\n\t";

    private static final String DOUBLE_EMPTY_LINE = "\n\t\n\t";

    private CommentStatus mCommentStatus = CommentStatus.APPROVED;

    private int mNormalBackgroundColor;

    private int mIndentedLeftPadding;

    private final Context mContext;

    private boolean mStatusChanged;

    private FormattableContent mCommentData;

    private final long mTimestamp;

    private CommentUserNoteBlockHolder mNoteBlockHolder;

    public interface OnCommentStatusChangeListener {

        void onCommentStatusChanged(CommentStatus newStatus);
    }

    public CommentUserNoteBlock(Context context, FormattableContent noteObject, FormattableContent commentTextBlock, long timestamp, OnNoteBlockTextClickListener onNoteBlockTextClickListener, OnGravatarClickedListener onGravatarClickedListener, ImageManager imageManager, NotificationsUtilsWrapper notificationsUtilsWrapper) {
        super(context, noteObject, onNoteBlockTextClickListener, onGravatarClickedListener, imageManager, notificationsUtilsWrapper);
        mContext = context;
        if (!ListenerUtil.mutListener.listen(8244)) {
            mCommentData = commentTextBlock;
        }
        mTimestamp = timestamp;
        if (!ListenerUtil.mutListener.listen(8246)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(8245)) {
                    setAvatarSize(context.getResources().getDimensionPixelSize(R.dimen.avatar_sz_small));
                }
            }
        }
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.USER_COMMENT;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.note_block_comment_user;
    }

    // fixed by setting a click listener to avatarImageView
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View configureView(View view) {
        if (!ListenerUtil.mutListener.listen(8247)) {
            mNoteBlockHolder = (CommentUserNoteBlockHolder) view.getTag();
        }
        if (!ListenerUtil.mutListener.listen(8248)) {
            setUserName();
        }
        if (!ListenerUtil.mutListener.listen(8249)) {
            setUserCommentAgo();
        }
        if (!ListenerUtil.mutListener.listen(8250)) {
            setUserCommentSite();
        }
        if (!ListenerUtil.mutListener.listen(8251)) {
            setUserAvatar();
        }
        if (!ListenerUtil.mutListener.listen(8252)) {
            setUserComment();
        }
        if (!ListenerUtil.mutListener.listen(8253)) {
            setCommentStatus(view);
        }
        return view;
    }

    private void setUserName() {
        if (!ListenerUtil.mutListener.listen(8254)) {
            mNoteBlockHolder.mNameTextView.setText(Html.fromHtml("<strong>" + getNoteText().toString() + "</strong>"));
        }
    }

    private void setUserCommentAgo() {
        if (!ListenerUtil.mutListener.listen(8255)) {
            mNoteBlockHolder.mAgoTextView.setText(DateTimeUtils.timeSpanFromTimestamp(getTimestamp(), mNoteBlockHolder.mAgoTextView.getContext()));
        }
    }

    private void setUserCommentSite() {
        if (!ListenerUtil.mutListener.listen(8264)) {
            if ((ListenerUtil.mutListener.listen(8256) ? (!TextUtils.isEmpty(getMetaHomeTitle()) && !TextUtils.isEmpty(getMetaSiteUrl())) : (!TextUtils.isEmpty(getMetaHomeTitle()) || !TextUtils.isEmpty(getMetaSiteUrl())))) {
                if (!ListenerUtil.mutListener.listen(8259)) {
                    mNoteBlockHolder.mBulletTextView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8260)) {
                    mNoteBlockHolder.mSiteTextView.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8263)) {
                    if (!TextUtils.isEmpty(getMetaHomeTitle())) {
                        if (!ListenerUtil.mutListener.listen(8262)) {
                            mNoteBlockHolder.mSiteTextView.setText(getMetaHomeTitle());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8261)) {
                            mNoteBlockHolder.mSiteTextView.setText(getMetaSiteUrl().replace("http://", "").replace("https://", ""));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8257)) {
                    mNoteBlockHolder.mBulletTextView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(8258)) {
                    mNoteBlockHolder.mSiteTextView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8265)) {
            mNoteBlockHolder.mSiteTextView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        }
    }

    private void setUserAvatar() {
        String imageUrl = "";
        if (!ListenerUtil.mutListener.listen(8278)) {
            if (hasImageMediaItem()) {
                if (!ListenerUtil.mutListener.listen(8269)) {
                    imageUrl = GravatarUtils.fixGravatarUrl(getNoteMediaItem().getUrl(), getAvatarSize());
                }
                if (!ListenerUtil.mutListener.listen(8270)) {
                    mNoteBlockHolder.mAvatarImageView.setContentDescription(mContext.getString(R.string.profile_picture, getNoteText().toString()));
                }
                if (!ListenerUtil.mutListener.listen(8277)) {
                    if (!TextUtils.isEmpty(getUserUrl())) {
                        if (!ListenerUtil.mutListener.listen(8275)) {
                            mNoteBlockHolder.mAvatarImageView.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    if (!ListenerUtil.mutListener.listen(8274)) {
                                        showBlogPreview();
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(8276)) {
                            // noinspection AndroidLintClickableViewAccessibility
                            mNoteBlockHolder.mAvatarImageView.setOnTouchListener(mOnGravatarTouchListener);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8271)) {
                            mNoteBlockHolder.mAvatarImageView.setOnClickListener(null);
                        }
                        if (!ListenerUtil.mutListener.listen(8272)) {
                            // noinspection AndroidLintClickableViewAccessibility
                            mNoteBlockHolder.mAvatarImageView.setOnTouchListener(null);
                        }
                        if (!ListenerUtil.mutListener.listen(8273)) {
                            mNoteBlockHolder.mAvatarImageView.setContentDescription(null);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8266)) {
                    mNoteBlockHolder.mAvatarImageView.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(8267)) {
                    // noinspection AndroidLintClickableViewAccessibility
                    mNoteBlockHolder.mAvatarImageView.setOnTouchListener(null);
                }
                if (!ListenerUtil.mutListener.listen(8268)) {
                    mNoteBlockHolder.mAvatarImageView.setContentDescription(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8279)) {
            mImageManager.loadIntoCircle(mNoteBlockHolder.mAvatarImageView, ImageType.AVATAR_WITH_BACKGROUND, imageUrl);
        }
    }

    private void setUserComment() {
        Spannable spannable = getCommentTextOfNotification(mNoteBlockHolder);
        NoteBlockClickableSpan[] spans = spannable.getSpans(0, spannable.length(), NoteBlockClickableSpan.class);
        if (!ListenerUtil.mutListener.listen(8281)) {
            {
                long _loopCounter168 = 0;
                for (NoteBlockClickableSpan span : spans) {
                    ListenerUtil.loopListener.listen("_loopCounter168", ++_loopCounter168);
                    if (!ListenerUtil.mutListener.listen(8280)) {
                        span.enableColors(mContext);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8282)) {
            mNoteBlockHolder.mCommentTextView.setText(spannable);
        }
    }

    private void setCommentStatus(@NonNull final View view) {
        // 2. Unapproved comments have different background and text color
        int paddingStart = ViewCompat.getPaddingStart(view);
        int paddingTop = view.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(view);
        int paddingBottom = view.getPaddingBottom();
        if (!ListenerUtil.mutListener.listen(8294)) {
            if (mCommentStatus == CommentStatus.UNAPPROVED) {
                if (!ListenerUtil.mutListener.listen(8292)) {
                    if (hasCommentNestingLevel()) {
                        if (!ListenerUtil.mutListener.listen(8290)) {
                            paddingStart = mIndentedLeftPadding;
                        }
                        if (!ListenerUtil.mutListener.listen(8291)) {
                            view.setBackgroundResource(R.drawable.bg_rectangle_warning_surface_with_padding);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8289)) {
                            view.setBackgroundResource(R.drawable.bg_rectangle_warning_surface);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8293)) {
                    mNoteBlockHolder.mDividerView.setVisibility(View.INVISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8288)) {
                    if (hasCommentNestingLevel()) {
                        if (!ListenerUtil.mutListener.listen(8285)) {
                            paddingStart = mIndentedLeftPadding;
                        }
                        if (!ListenerUtil.mutListener.listen(8286)) {
                            view.setBackgroundResource(R.drawable.comment_reply_background);
                        }
                        if (!ListenerUtil.mutListener.listen(8287)) {
                            mNoteBlockHolder.mDividerView.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8283)) {
                            view.setBackgroundColor(mNormalBackgroundColor);
                        }
                        if (!ListenerUtil.mutListener.listen(8284)) {
                            mNoteBlockHolder.mDividerView.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8295)) {
            ViewCompat.setPaddingRelative(view, paddingStart, paddingTop, paddingEnd, paddingBottom);
        }
        if (!ListenerUtil.mutListener.listen(8299)) {
            // If status was changed, fade in the view
            if (mStatusChanged) {
                if (!ListenerUtil.mutListener.listen(8296)) {
                    mStatusChanged = false;
                }
                if (!ListenerUtil.mutListener.listen(8297)) {
                    view.setAlpha(0.4f);
                }
                if (!ListenerUtil.mutListener.listen(8298)) {
                    view.animate().alpha(1.0f).start();
                }
            }
        }
    }

    private Spannable getCommentTextOfNotification(CommentUserNoteBlockHolder noteBlockHolder) {
        SpannableStringBuilder builder = mNotificationsUtilsWrapper.getSpannableContentForRanges(mCommentData, noteBlockHolder.mCommentTextView, getOnNoteBlockTextClickListener(), false);
        return removeNewLineInList(builder);
    }

    private Spannable removeNewLineInList(SpannableStringBuilder builder) {
        String content = builder.toString();
        if (!ListenerUtil.mutListener.listen(8306)) {
            {
                long _loopCounter169 = 0;
                while (content.contains(DOUBLE_EMPTY_LINE)) {
                    ListenerUtil.loopListener.listen("_loopCounter169", ++_loopCounter169);
                    int doubleSpaceIndex = content.indexOf(DOUBLE_EMPTY_LINE);
                    if (!ListenerUtil.mutListener.listen(8304)) {
                        builder.replace(doubleSpaceIndex, (ListenerUtil.mutListener.listen(8303) ? (doubleSpaceIndex % DOUBLE_EMPTY_LINE.length()) : (ListenerUtil.mutListener.listen(8302) ? (doubleSpaceIndex / DOUBLE_EMPTY_LINE.length()) : (ListenerUtil.mutListener.listen(8301) ? (doubleSpaceIndex * DOUBLE_EMPTY_LINE.length()) : (ListenerUtil.mutListener.listen(8300) ? (doubleSpaceIndex - DOUBLE_EMPTY_LINE.length()) : (doubleSpaceIndex + DOUBLE_EMPTY_LINE.length()))))), EMPTY_LINE);
                    }
                    if (!ListenerUtil.mutListener.listen(8305)) {
                        content = builder.toString();
                    }
                }
            }
        }
        return builder;
    }

    private long getTimestamp() {
        return mTimestamp;
    }

    private boolean hasCommentNestingLevel() {
        return (ListenerUtil.mutListener.listen(8312) ? (mCommentData.getNestLevel() != null || (ListenerUtil.mutListener.listen(8311) ? (mCommentData.getNestLevel() >= 0) : (ListenerUtil.mutListener.listen(8310) ? (mCommentData.getNestLevel() <= 0) : (ListenerUtil.mutListener.listen(8309) ? (mCommentData.getNestLevel() < 0) : (ListenerUtil.mutListener.listen(8308) ? (mCommentData.getNestLevel() != 0) : (ListenerUtil.mutListener.listen(8307) ? (mCommentData.getNestLevel() == 0) : (mCommentData.getNestLevel() > 0))))))) : (mCommentData.getNestLevel() != null && (ListenerUtil.mutListener.listen(8311) ? (mCommentData.getNestLevel() >= 0) : (ListenerUtil.mutListener.listen(8310) ? (mCommentData.getNestLevel() <= 0) : (ListenerUtil.mutListener.listen(8309) ? (mCommentData.getNestLevel() < 0) : (ListenerUtil.mutListener.listen(8308) ? (mCommentData.getNestLevel() != 0) : (ListenerUtil.mutListener.listen(8307) ? (mCommentData.getNestLevel() == 0) : (mCommentData.getNestLevel() > 0))))))));
    }

    @Override
    public Object getViewHolder(View view) {
        return new CommentUserNoteBlockHolder(view);
    }

    private class CommentUserNoteBlockHolder {

        private final ImageView mAvatarImageView;

        private final TextView mNameTextView;

        private final TextView mAgoTextView;

        private final TextView mBulletTextView;

        private final TextView mSiteTextView;

        private final TextView mCommentTextView;

        private final View mDividerView;

        CommentUserNoteBlockHolder(View view) {
            mNameTextView = view.findViewById(R.id.user_name);
            mAgoTextView = view.findViewById(R.id.user_comment_ago);
            if (!ListenerUtil.mutListener.listen(8313)) {
                mAgoTextView.setVisibility(View.VISIBLE);
            }
            mBulletTextView = view.findViewById(R.id.user_comment_bullet);
            mSiteTextView = view.findViewById(R.id.user_comment_site);
            mCommentTextView = view.findViewById(R.id.user_comment);
            if (!ListenerUtil.mutListener.listen(8314)) {
                mCommentTextView.setMovementMethod(new NoteBlockLinkMovementMethod());
            }
            mAvatarImageView = view.findViewById(R.id.user_avatar);
            mDividerView = view.findViewById(R.id.divider_view);
            if (!ListenerUtil.mutListener.listen(8317)) {
                mSiteTextView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(8316)) {
                            if (getOnNoteBlockTextClickListener() != null) {
                                if (!ListenerUtil.mutListener.listen(8315)) {
                                    getOnNoteBlockTextClickListener().showSitePreview(getMetaSiteId(), getMetaSiteUrl());
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(8320)) {
                // show all comments on this post when user clicks the comment text
                mCommentTextView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(8319)) {
                            if (getOnNoteBlockTextClickListener() != null) {
                                if (!ListenerUtil.mutListener.listen(8318)) {
                                    getOnNoteBlockTextClickListener().showReaderPostComments();
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public void configureResources(Context context) {
        if (!ListenerUtil.mutListener.listen(8321)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8322)) {
            mNormalBackgroundColor = ContextExtensionsKt.getColorFromAttribute(context, R.attr.colorSurface);
        }
        if (!ListenerUtil.mutListener.listen(8327)) {
            // Double margin_extra_large for increased indent in comment replies
            mIndentedLeftPadding = (ListenerUtil.mutListener.listen(8326) ? (context.getResources().getDimensionPixelSize(R.dimen.margin_extra_large) % 2) : (ListenerUtil.mutListener.listen(8325) ? (context.getResources().getDimensionPixelSize(R.dimen.margin_extra_large) / 2) : (ListenerUtil.mutListener.listen(8324) ? (context.getResources().getDimensionPixelSize(R.dimen.margin_extra_large) - 2) : (ListenerUtil.mutListener.listen(8323) ? (context.getResources().getDimensionPixelSize(R.dimen.margin_extra_large) + 2) : (context.getResources().getDimensionPixelSize(R.dimen.margin_extra_large) * 2)))));
        }
    }

    private final OnCommentStatusChangeListener mOnCommentChangedListener = new OnCommentStatusChangeListener() {

        @Override
        public void onCommentStatusChanged(CommentStatus newStatus) {
            if (!ListenerUtil.mutListener.listen(8328)) {
                mCommentStatus = newStatus;
            }
            if (!ListenerUtil.mutListener.listen(8329)) {
                mStatusChanged = true;
            }
        }
    };

    public void setCommentStatus(CommentStatus status) {
        if (!ListenerUtil.mutListener.listen(8330)) {
            mCommentStatus = status;
        }
    }

    public OnCommentStatusChangeListener getOnCommentChangeListener() {
        return mOnCommentChangedListener;
    }
}
