package org.wordpress.android.ui.notifications.blocks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.tools.FormattableContent;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.FormattableContentUtilsKt;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Note header, displayed at top of detail view
public class HeaderNoteBlock extends NoteBlock {

    private final List<FormattableContent> mHeadersList;

    private final UserNoteBlock.OnGravatarClickedListener mGravatarClickedListener;

    private Boolean mIsComment;

    private int mAvatarSize;

    private ImageType mImageType;

    public HeaderNoteBlock(Context context, List<FormattableContent> headerArray, ImageType imageType, OnNoteBlockTextClickListener onNoteBlockTextClickListener, UserNoteBlock.OnGravatarClickedListener onGravatarClickedListener, ImageManager imageManager, NotificationsUtilsWrapper notificationsUtilsWrapper) {
        super(new FormattableContent(), imageManager, notificationsUtilsWrapper, onNoteBlockTextClickListener);
        mHeadersList = headerArray;
        if (!ListenerUtil.mutListener.listen(8351)) {
            mImageType = imageType;
        }
        mGravatarClickedListener = onGravatarClickedListener;
        if (!ListenerUtil.mutListener.listen(8353)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(8352)) {
                    mAvatarSize = context.getResources().getDimensionPixelSize(R.dimen.avatar_sz_small);
                }
            }
        }
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.USER_HEADER;
    }

    public int getLayoutResourceId() {
        return R.layout.note_block_header;
    }

    // fixed by setting a click listener to avatarImageView
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View configureView(View view) {
        final NoteHeaderBlockHolder noteBlockHolder = (NoteHeaderBlockHolder) view.getTag();
        Spannable spannable = mNotificationsUtilsWrapper.getSpannableContentForRanges(mHeadersList.get(0));
        NoteBlockClickableSpan[] spans = spannable.getSpans(0, spannable.length(), NoteBlockClickableSpan.class);
        if (!ListenerUtil.mutListener.listen(8355)) {
            {
                long _loopCounter171 = 0;
                for (NoteBlockClickableSpan span : spans) {
                    ListenerUtil.loopListener.listen("_loopCounter171", ++_loopCounter171);
                    if (!ListenerUtil.mutListener.listen(8354)) {
                        span.enableColors(view.getContext());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8356)) {
            noteBlockHolder.mNameTextView.setText(spannable);
        }
        if (!ListenerUtil.mutListener.listen(8359)) {
            if (mImageType == ImageType.AVATAR_WITH_BACKGROUND) {
                if (!ListenerUtil.mutListener.listen(8358)) {
                    mImageManager.loadIntoCircle(noteBlockHolder.mAvatarImageView, mImageType, getAvatarUrl());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8357)) {
                    mImageManager.load(noteBlockHolder.mAvatarImageView, mImageType, getAvatarUrl());
                }
            }
        }
        final long siteId = FormattableContentUtilsKt.getRangeSiteIdOrZero(getHeader(0), 0);
        final long userId = FormattableContentUtilsKt.getRangeIdOrZero(getHeader(0), 0);
        if (!ListenerUtil.mutListener.listen(8388)) {
            if ((ListenerUtil.mutListener.listen(8371) ? ((ListenerUtil.mutListener.listen(8365) ? (!TextUtils.isEmpty(getUserUrl()) || (ListenerUtil.mutListener.listen(8364) ? (siteId >= 0) : (ListenerUtil.mutListener.listen(8363) ? (siteId <= 0) : (ListenerUtil.mutListener.listen(8362) ? (siteId < 0) : (ListenerUtil.mutListener.listen(8361) ? (siteId != 0) : (ListenerUtil.mutListener.listen(8360) ? (siteId == 0) : (siteId > 0))))))) : (!TextUtils.isEmpty(getUserUrl()) && (ListenerUtil.mutListener.listen(8364) ? (siteId >= 0) : (ListenerUtil.mutListener.listen(8363) ? (siteId <= 0) : (ListenerUtil.mutListener.listen(8362) ? (siteId < 0) : (ListenerUtil.mutListener.listen(8361) ? (siteId != 0) : (ListenerUtil.mutListener.listen(8360) ? (siteId == 0) : (siteId > 0)))))))) || (ListenerUtil.mutListener.listen(8370) ? (userId >= 0) : (ListenerUtil.mutListener.listen(8369) ? (userId <= 0) : (ListenerUtil.mutListener.listen(8368) ? (userId < 0) : (ListenerUtil.mutListener.listen(8367) ? (userId != 0) : (ListenerUtil.mutListener.listen(8366) ? (userId == 0) : (userId > 0))))))) : ((ListenerUtil.mutListener.listen(8365) ? (!TextUtils.isEmpty(getUserUrl()) || (ListenerUtil.mutListener.listen(8364) ? (siteId >= 0) : (ListenerUtil.mutListener.listen(8363) ? (siteId <= 0) : (ListenerUtil.mutListener.listen(8362) ? (siteId < 0) : (ListenerUtil.mutListener.listen(8361) ? (siteId != 0) : (ListenerUtil.mutListener.listen(8360) ? (siteId == 0) : (siteId > 0))))))) : (!TextUtils.isEmpty(getUserUrl()) && (ListenerUtil.mutListener.listen(8364) ? (siteId >= 0) : (ListenerUtil.mutListener.listen(8363) ? (siteId <= 0) : (ListenerUtil.mutListener.listen(8362) ? (siteId < 0) : (ListenerUtil.mutListener.listen(8361) ? (siteId != 0) : (ListenerUtil.mutListener.listen(8360) ? (siteId == 0) : (siteId > 0)))))))) && (ListenerUtil.mutListener.listen(8370) ? (userId >= 0) : (ListenerUtil.mutListener.listen(8369) ? (userId <= 0) : (ListenerUtil.mutListener.listen(8368) ? (userId < 0) : (ListenerUtil.mutListener.listen(8367) ? (userId != 0) : (ListenerUtil.mutListener.listen(8366) ? (userId == 0) : (userId > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(8377)) {
                    noteBlockHolder.mAvatarImageView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            String siteUrl = getUserUrl();
                            if (!ListenerUtil.mutListener.listen(8376)) {
                                mGravatarClickedListener.onGravatarClicked(siteId, userId, siteUrl);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(8378)) {
                    noteBlockHolder.mAvatarImageView.setContentDescription(view.getContext().getString(R.string.profile_picture, spannable));
                }
                if (!ListenerUtil.mutListener.listen(8379)) {
                    // noinspection AndroidLintClickableViewAccessibility
                    noteBlockHolder.mAvatarImageView.setOnTouchListener(mOnGravatarTouchListener);
                }
                if (!ListenerUtil.mutListener.listen(8387)) {
                    if ((ListenerUtil.mutListener.listen(8384) ? (siteId >= userId) : (ListenerUtil.mutListener.listen(8383) ? (siteId <= userId) : (ListenerUtil.mutListener.listen(8382) ? (siteId > userId) : (ListenerUtil.mutListener.listen(8381) ? (siteId < userId) : (ListenerUtil.mutListener.listen(8380) ? (siteId != userId) : (siteId == userId))))))) {
                        if (!ListenerUtil.mutListener.listen(8386)) {
                            noteBlockHolder.mAvatarImageView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8385)) {
                            noteBlockHolder.mAvatarImageView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8372)) {
                    noteBlockHolder.mAvatarImageView.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
                }
                if (!ListenerUtil.mutListener.listen(8373)) {
                    noteBlockHolder.mAvatarImageView.setContentDescription(null);
                }
                if (!ListenerUtil.mutListener.listen(8374)) {
                    noteBlockHolder.mAvatarImageView.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(8375)) {
                    // noinspection AndroidLintClickableViewAccessibility
                    noteBlockHolder.mAvatarImageView.setOnTouchListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8389)) {
            noteBlockHolder.mSnippetTextView.setText(getSnippet());
        }
        if (!ListenerUtil.mutListener.listen(8392)) {
            if (mIsComment) {
                View footerView = view.findViewById(R.id.header_footer);
                View footerCommentView = view.findViewById(R.id.header_footer_comment);
                if (!ListenerUtil.mutListener.listen(8390)) {
                    footerView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(8391)) {
                    footerCommentView.setVisibility(View.VISIBLE);
                }
            }
        }
        return view;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(8394)) {
                if (getOnNoteBlockTextClickListener() != null) {
                    if (!ListenerUtil.mutListener.listen(8393)) {
                        getOnNoteBlockTextClickListener().showDetailForNoteIds();
                    }
                }
            }
        }
    };

    private String getAvatarUrl() {
        return GravatarUtils.fixGravatarUrl(FormattableContentUtilsKt.getMediaUrlOrEmpty(getHeader(0), 0), mAvatarSize);
    }

    private String getUserUrl() {
        return FormattableContentUtilsKt.getRangeUrlOrEmpty(getHeader(0), 0);
    }

    private String getSnippet() {
        return FormattableContentUtilsKt.getTextOrEmpty(getHeader(1));
    }

    @Override
    public Object getViewHolder(View view) {
        return new NoteHeaderBlockHolder(view);
    }

    public void setIsComment(Boolean isComment) {
        if (!ListenerUtil.mutListener.listen(8395)) {
            mIsComment = isComment;
        }
    }

    private class NoteHeaderBlockHolder {

        private final TextView mNameTextView;

        private final TextView mSnippetTextView;

        private final ImageView mAvatarImageView;

        NoteHeaderBlockHolder(View view) {
            View rootView = view.findViewById(R.id.header_root_view);
            if (!ListenerUtil.mutListener.listen(8396)) {
                rootView.setOnClickListener(mOnClickListener);
            }
            mNameTextView = view.findViewById(R.id.header_user);
            mSnippetTextView = view.findViewById(R.id.header_snippet);
            mAvatarImageView = view.findViewById(R.id.header_avatar);
        }
    }

    private final View.OnTouchListener mOnGravatarTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int animationDuration = 150;
            if (!ListenerUtil.mutListener.listen(8403)) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!ListenerUtil.mutListener.listen(8402)) {
                        v.animate().scaleX(0.9f).scaleY(0.9f).alpha(0.5f).setDuration(animationDuration).setInterpolator(new DecelerateInterpolator());
                    }
                } else if ((ListenerUtil.mutListener.listen(8397) ? (event.getActionMasked() == MotionEvent.ACTION_UP && event.getActionMasked() == MotionEvent.ACTION_CANCEL) : (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL))) {
                    if (!ListenerUtil.mutListener.listen(8398)) {
                        v.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(animationDuration).setInterpolator(new DecelerateInterpolator());
                    }
                    if (!ListenerUtil.mutListener.listen(8401)) {
                        if ((ListenerUtil.mutListener.listen(8399) ? (event.getActionMasked() == MotionEvent.ACTION_UP || mGravatarClickedListener != null) : (event.getActionMasked() == MotionEvent.ACTION_UP && mGravatarClickedListener != null))) {
                            if (!ListenerUtil.mutListener.listen(8400)) {
                                // In the future we can use this to load a 'profile view' (currently in R&D)
                                v.performClick();
                            }
                        }
                    }
                }
            }
            return true;
        }
    };

    public FormattableContent getHeader(int headerIndex) {
        if (!ListenerUtil.mutListener.listen(8410)) {
            if ((ListenerUtil.mutListener.listen(8409) ? (mHeadersList != null || (ListenerUtil.mutListener.listen(8408) ? (headerIndex >= mHeadersList.size()) : (ListenerUtil.mutListener.listen(8407) ? (headerIndex <= mHeadersList.size()) : (ListenerUtil.mutListener.listen(8406) ? (headerIndex > mHeadersList.size()) : (ListenerUtil.mutListener.listen(8405) ? (headerIndex != mHeadersList.size()) : (ListenerUtil.mutListener.listen(8404) ? (headerIndex == mHeadersList.size()) : (headerIndex < mHeadersList.size()))))))) : (mHeadersList != null && (ListenerUtil.mutListener.listen(8408) ? (headerIndex >= mHeadersList.size()) : (ListenerUtil.mutListener.listen(8407) ? (headerIndex <= mHeadersList.size()) : (ListenerUtil.mutListener.listen(8406) ? (headerIndex > mHeadersList.size()) : (ListenerUtil.mutListener.listen(8405) ? (headerIndex != mHeadersList.size()) : (ListenerUtil.mutListener.listen(8404) ? (headerIndex == mHeadersList.size()) : (headerIndex < mHeadersList.size()))))))))) {
                return mHeadersList.get(headerIndex);
            }
        }
        return null;
    }
}
