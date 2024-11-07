package org.wordpress.android.ui.notifications.blocks;

import android.annotation.SuppressLint;
import android.content.Context;
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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A block that displays information about a User (such as a user that liked a post)
 */
public class UserNoteBlock extends NoteBlock {

    private final OnGravatarClickedListener mGravatarClickedListener;

    private int mAvatarSz;

    public interface OnGravatarClickedListener {

        // userId is currently unused, but will be handy once a profile view is added to the app
        void onGravatarClicked(long siteId, long userId, String siteUrl);
    }

    public UserNoteBlock(Context context, FormattableContent noteObject, OnNoteBlockTextClickListener onNoteBlockTextClickListener, OnGravatarClickedListener onGravatarClickedListener, ImageManager imageManager, NotificationsUtilsWrapper notificationsUtilsWrapper) {
        super(noteObject, imageManager, notificationsUtilsWrapper, onNoteBlockTextClickListener);
        if (!ListenerUtil.mutListener.listen(8564)) {
            if (context != null) {
                if (!ListenerUtil.mutListener.listen(8563)) {
                    setAvatarSize(context.getResources().getDimensionPixelSize(R.dimen.notifications_avatar_sz));
                }
            }
        }
        mGravatarClickedListener = onGravatarClickedListener;
    }

    void setAvatarSize(int size) {
        if (!ListenerUtil.mutListener.listen(8565)) {
            mAvatarSz = size;
        }
    }

    int getAvatarSize() {
        return mAvatarSz;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.USER;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.note_block_user;
    }

    // fixed by setting a click listener to avatarImageView
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View configureView(View view) {
        final UserActionNoteBlockHolder noteBlockHolder = (UserActionNoteBlockHolder) view.getTag();
        if (!ListenerUtil.mutListener.listen(8566)) {
            noteBlockHolder.mNameTextView.setText(getNoteText().toString());
        }
        String linkedText = null;
        if (!ListenerUtil.mutListener.listen(8569)) {
            if (hasUserUrlAndTitle()) {
                if (!ListenerUtil.mutListener.listen(8568)) {
                    linkedText = getUserBlogTitle();
                }
            } else if (hasUserUrl()) {
                if (!ListenerUtil.mutListener.listen(8567)) {
                    linkedText = getUserUrl();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8573)) {
            if (!TextUtils.isEmpty(linkedText)) {
                if (!ListenerUtil.mutListener.listen(8571)) {
                    noteBlockHolder.mUrlTextView.setText(linkedText);
                }
                if (!ListenerUtil.mutListener.listen(8572)) {
                    noteBlockHolder.mUrlTextView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8570)) {
                    noteBlockHolder.mUrlTextView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8577)) {
            if (hasUserBlogTagline()) {
                if (!ListenerUtil.mutListener.listen(8575)) {
                    noteBlockHolder.mTaglineTextView.setText(getUserBlogTagline());
                }
                if (!ListenerUtil.mutListener.listen(8576)) {
                    noteBlockHolder.mTaglineTextView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8574)) {
                    noteBlockHolder.mTaglineTextView.setVisibility(View.GONE);
                }
            }
        }
        String imageUrl = "";
        if (!ListenerUtil.mutListener.listen(8589)) {
            if (hasImageMediaItem()) {
                if (!ListenerUtil.mutListener.listen(8581)) {
                    imageUrl = GravatarUtils.fixGravatarUrl(getNoteMediaItem().getUrl(), getAvatarSize());
                }
                if (!ListenerUtil.mutListener.listen(8588)) {
                    if (!TextUtils.isEmpty(getUserUrl())) {
                        if (!ListenerUtil.mutListener.listen(8585)) {
                            // noinspection AndroidLintClickableViewAccessibility
                            noteBlockHolder.mAvatarImageView.setOnTouchListener(mOnGravatarTouchListener);
                        }
                        if (!ListenerUtil.mutListener.listen(8586)) {
                            noteBlockHolder.mRootView.setEnabled(true);
                        }
                        if (!ListenerUtil.mutListener.listen(8587)) {
                            noteBlockHolder.mRootView.setOnClickListener(mOnClickListener);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8582)) {
                            // noinspection AndroidLintClickableViewAccessibility
                            noteBlockHolder.mAvatarImageView.setOnTouchListener(null);
                        }
                        if (!ListenerUtil.mutListener.listen(8583)) {
                            noteBlockHolder.mRootView.setEnabled(false);
                        }
                        if (!ListenerUtil.mutListener.listen(8584)) {
                            noteBlockHolder.mRootView.setOnClickListener(null);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8578)) {
                    noteBlockHolder.mRootView.setEnabled(false);
                }
                if (!ListenerUtil.mutListener.listen(8579)) {
                    noteBlockHolder.mRootView.setOnClickListener(null);
                }
                if (!ListenerUtil.mutListener.listen(8580)) {
                    // noinspection AndroidLintClickableViewAccessibility
                    noteBlockHolder.mAvatarImageView.setOnTouchListener(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8590)) {
            mImageManager.loadIntoCircle(noteBlockHolder.mAvatarImageView, ImageType.AVATAR_WITH_BACKGROUND, imageUrl);
        }
        return view;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!ListenerUtil.mutListener.listen(8591)) {
                showBlogPreview();
            }
        }
    };

    @Override
    public Object getViewHolder(View view) {
        return new UserActionNoteBlockHolder(view);
    }

    private class UserActionNoteBlockHolder {

        private final View mRootView;

        private final TextView mNameTextView;

        private final TextView mUrlTextView;

        private final TextView mTaglineTextView;

        private final ImageView mAvatarImageView;

        UserActionNoteBlockHolder(View view) {
            mRootView = view.findViewById(R.id.user_block_root_view);
            mNameTextView = view.findViewById(R.id.user_name);
            mUrlTextView = view.findViewById(R.id.user_blog_url);
            mTaglineTextView = view.findViewById(R.id.user_blog_tagline);
            mAvatarImageView = view.findViewById(R.id.user_avatar);
        }
    }

    String getUserUrl() {
        return FormattableContentUtilsKt.getMetaLinksHomeOrEmpty(getNoteData());
    }

    private String getUserBlogTitle() {
        return FormattableContentUtilsKt.getMetaTitlesHomeOrEmpty(getNoteData());
    }

    private String getUserBlogTagline() {
        return FormattableContentUtilsKt.getMetaTitlesTaglineOrEmpty(getNoteData());
    }

    private boolean hasUserUrl() {
        return !TextUtils.isEmpty(getUserUrl());
    }

    private boolean hasUserUrlAndTitle() {
        return (ListenerUtil.mutListener.listen(8592) ? (hasUserUrl() || !TextUtils.isEmpty(getUserBlogTitle())) : (hasUserUrl() && !TextUtils.isEmpty(getUserBlogTitle())));
    }

    private boolean hasUserBlogTagline() {
        return !TextUtils.isEmpty(getUserBlogTagline());
    }

    final View.OnTouchListener mOnGravatarTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int animationDuration = 150;
            if (!ListenerUtil.mutListener.listen(8599)) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!ListenerUtil.mutListener.listen(8598)) {
                        v.animate().scaleX(0.9f).scaleY(0.9f).alpha(0.5f).setDuration(animationDuration).setInterpolator(new DecelerateInterpolator());
                    }
                } else if ((ListenerUtil.mutListener.listen(8593) ? (event.getActionMasked() == MotionEvent.ACTION_UP && event.getActionMasked() == MotionEvent.ACTION_CANCEL) : (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL))) {
                    if (!ListenerUtil.mutListener.listen(8594)) {
                        v.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(animationDuration).setInterpolator(new DecelerateInterpolator());
                    }
                    if (!ListenerUtil.mutListener.listen(8597)) {
                        if ((ListenerUtil.mutListener.listen(8595) ? (event.getActionMasked() == MotionEvent.ACTION_UP || mGravatarClickedListener != null) : (event.getActionMasked() == MotionEvent.ACTION_UP && mGravatarClickedListener != null))) {
                            if (!ListenerUtil.mutListener.listen(8596)) {
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

    protected void showBlogPreview() {
        String siteUrl = getUserUrl();
        if (!ListenerUtil.mutListener.listen(8601)) {
            if (mGravatarClickedListener != null) {
                if (!ListenerUtil.mutListener.listen(8600)) {
                    mGravatarClickedListener.onGravatarClicked(FormattableContentUtilsKt.getMetaIdsSiteIdOrZero(getNoteData()), FormattableContentUtilsKt.getMetaIdsUserIdOrZero(getNoteData()), siteUrl);
                }
            }
        }
    }
}
