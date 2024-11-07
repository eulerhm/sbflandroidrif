package org.wordpress.android.ui.notifications.blocks;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.wordpress.android.R;
import org.wordpress.android.fluxc.tools.FormattableContent;
import org.wordpress.android.fluxc.tools.FormattableMedia;
import org.wordpress.android.fluxc.tools.FormattableRange;
import org.wordpress.android.ui.notifications.utils.NotificationsUtilsWrapper;
import org.wordpress.android.util.AccessibilityUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.FormattableContentUtilsKt;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageType;
import org.wordpress.android.widgets.WPTextView;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A block of data displayed in a notification.
 * This basic block can support a media item (image/video) and/or text.
 */
public class NoteBlock {

    private final FormattableContent mNoteData;

    private final OnNoteBlockTextClickListener mOnNoteBlockTextClickListener;

    protected final ImageManager mImageManager;

    protected final NotificationsUtilsWrapper mNotificationsUtilsWrapper;

    private boolean mIsBadge;

    private boolean mIsPingback;

    private boolean mHasAnimatedBadge;

    private boolean mIsViewMilestone;

    public interface OnNoteBlockTextClickListener {

        void onNoteBlockTextClicked(NoteBlockClickableSpan clickedSpan);

        void showDetailForNoteIds();

        void showReaderPostComments();

        void showSitePreview(long siteId, String siteUrl);
    }

    public NoteBlock(FormattableContent noteObject, ImageManager imageManager, NotificationsUtilsWrapper notificationsUtilsWrapper, OnNoteBlockTextClickListener onNoteBlockTextClickListener) {
        mNoteData = noteObject;
        mOnNoteBlockTextClickListener = onNoteBlockTextClickListener;
        mImageManager = imageManager;
        mNotificationsUtilsWrapper = notificationsUtilsWrapper;
    }

    OnNoteBlockTextClickListener getOnNoteBlockTextClickListener() {
        return mOnNoteBlockTextClickListener;
    }

    public BlockType getBlockType() {
        return BlockType.BASIC;
    }

    FormattableContent getNoteData() {
        return mNoteData;
    }

    Spannable getNoteText() {
        return mNotificationsUtilsWrapper.getSpannableContentForRanges(mNoteData, null, mOnNoteBlockTextClickListener, false);
    }

    String getMetaHomeTitle() {
        return FormattableContentUtilsKt.getMetaTitlesHomeOrEmpty(mNoteData);
    }

    long getMetaSiteId() {
        return FormattableContentUtilsKt.getMetaIdsSiteIdOrZero(mNoteData);
    }

    public String getMetaSiteUrl() {
        return FormattableContentUtilsKt.getMetaLinksHomeOrEmpty(mNoteData);
    }

    private boolean isPingBack() {
        return mIsPingback;
    }

    public void setIsPingback() {
        if (!ListenerUtil.mutListener.listen(8411)) {
            mIsPingback = true;
        }
    }

    FormattableMedia getNoteMediaItem() {
        return FormattableContentUtilsKt.getMediaOrNull(mNoteData, 0);
    }

    public void setIsBadge() {
        if (!ListenerUtil.mutListener.listen(8412)) {
            mIsBadge = true;
        }
    }

    public void setIsViewMilestone() {
        if (!ListenerUtil.mutListener.listen(8413)) {
            mIsViewMilestone = true;
        }
    }

    public int getLayoutResourceId() {
        return R.layout.note_block_basic;
    }

    private boolean hasMediaArray() {
        return (ListenerUtil.mutListener.listen(8414) ? (mNoteData.getMedia() != null || !mNoteData.getMedia().isEmpty()) : (mNoteData.getMedia() != null && !mNoteData.getMedia().isEmpty()));
    }

    boolean hasImageMediaItem() {
        return (ListenerUtil.mutListener.listen(8419) ? ((ListenerUtil.mutListener.listen(8418) ? ((ListenerUtil.mutListener.listen(8416) ? ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) || ((ListenerUtil.mutListener.listen(8417) ? (getNoteMediaItem().getType().startsWith("image") && getNoteMediaItem().getType().equals("badge")) : (getNoteMediaItem().getType().startsWith("image") || getNoteMediaItem().getType().equals("badge"))))) : ((ListenerUtil.mutListener.listen(8416) ? ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) && ((ListenerUtil.mutListener.listen(8417) ? (getNoteMediaItem().getType().startsWith("image") && getNoteMediaItem().getType().equals("badge")) : (getNoteMediaItem().getType().startsWith("image") || getNoteMediaItem().getType().equals("badge")))))) || !TextUtils.isEmpty(getNoteMediaItem().getUrl())) : ((ListenerUtil.mutListener.listen(8418) ? ((ListenerUtil.mutListener.listen(8416) ? ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) || ((ListenerUtil.mutListener.listen(8417) ? (getNoteMediaItem().getType().startsWith("image") && getNoteMediaItem().getType().equals("badge")) : (getNoteMediaItem().getType().startsWith("image") || getNoteMediaItem().getType().equals("badge"))))) : ((ListenerUtil.mutListener.listen(8416) ? ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8415) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) && ((ListenerUtil.mutListener.listen(8417) ? (getNoteMediaItem().getType().startsWith("image") && getNoteMediaItem().getType().equals("badge")) : (getNoteMediaItem().getType().startsWith("image") || getNoteMediaItem().getType().equals("badge")))))) && !TextUtils.isEmpty(getNoteMediaItem().getUrl())));
    }

    private boolean hasVideoMediaItem() {
        return (ListenerUtil.mutListener.listen(8423) ? ((ListenerUtil.mutListener.listen(8422) ? ((ListenerUtil.mutListener.listen(8421) ? ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) || getNoteMediaItem().getType().startsWith("video")) : ((ListenerUtil.mutListener.listen(8421) ? ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) && getNoteMediaItem().getType().startsWith("video"))) || !TextUtils.isEmpty(getNoteMediaItem().getUrl())) : ((ListenerUtil.mutListener.listen(8422) ? ((ListenerUtil.mutListener.listen(8421) ? ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) || getNoteMediaItem().getType().startsWith("video")) : ((ListenerUtil.mutListener.listen(8421) ? ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) || !TextUtils.isEmpty(getNoteMediaItem().getType())) : ((ListenerUtil.mutListener.listen(8420) ? (hasMediaArray() || getNoteMediaItem() != null) : (hasMediaArray() && getNoteMediaItem() != null)) && !TextUtils.isEmpty(getNoteMediaItem().getType()))) && getNoteMediaItem().getType().startsWith("video"))) && !TextUtils.isEmpty(getNoteMediaItem().getUrl())));
    }

    public boolean containsBadgeMediaType() {
        if (!ListenerUtil.mutListener.listen(8426)) {
            if (mNoteData.getMedia() != null) {
                if (!ListenerUtil.mutListener.listen(8425)) {
                    {
                        long _loopCounter172 = 0;
                        for (FormattableMedia mediaObject : mNoteData.getMedia()) {
                            ListenerUtil.loopListener.listen("_loopCounter172", ++_loopCounter172);
                            if (!ListenerUtil.mutListener.listen(8424)) {
                                if ("badge".equals(mediaObject.getType())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public View configureView(final View view) {
        final BasicNoteBlockHolder noteBlockHolder = (BasicNoteBlockHolder) view.getTag();
        if (!ListenerUtil.mutListener.listen(8440)) {
            // Note image
            if (hasImageMediaItem()) {
                if (!ListenerUtil.mutListener.listen(8429)) {
                    noteBlockHolder.getImageView().setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(8437)) {
                    // Request image, and animate it when loaded
                    mImageManager.loadWithResultListener(noteBlockHolder.getImageView(), ImageType.IMAGE, StringUtils.notNullStr(getNoteMediaItem().getUrl()), ScaleType.CENTER, null, new ImageManager.RequestListener<Drawable>() {

                        @Override
                        public void onLoadFailed(@Nullable Exception e, @Nullable Object model) {
                            if (!ListenerUtil.mutListener.listen(8431)) {
                                if (e != null) {
                                    if (!ListenerUtil.mutListener.listen(8430)) {
                                        AppLog.e(T.NOTIFS, e);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8432)) {
                                noteBlockHolder.hideImageView();
                            }
                        }

                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Object model) {
                            if (!ListenerUtil.mutListener.listen(8436)) {
                                if ((ListenerUtil.mutListener.listen(8433) ? (!mHasAnimatedBadge || view.getContext() != null) : (!mHasAnimatedBadge && view.getContext() != null))) {
                                    if (!ListenerUtil.mutListener.listen(8434)) {
                                        mHasAnimatedBadge = true;
                                    }
                                    Animation pop = AnimationUtils.loadAnimation(view.getContext(), R.anim.pop);
                                    if (!ListenerUtil.mutListener.listen(8435)) {
                                        noteBlockHolder.getImageView().startAnimation(pop);
                                    }
                                }
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(8439)) {
                    if (mIsBadge) {
                        if (!ListenerUtil.mutListener.listen(8438)) {
                            noteBlockHolder.getImageView().setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8427)) {
                    mImageManager.cancelRequestAndClearImageView(noteBlockHolder.getImageView());
                }
                if (!ListenerUtil.mutListener.listen(8428)) {
                    noteBlockHolder.hideImageView();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8444)) {
            // Note video
            if (hasVideoMediaItem()) {
                if (!ListenerUtil.mutListener.listen(8442)) {
                    noteBlockHolder.getVideoView().setVideoURI(Uri.parse(StringUtils.notNullStr(getNoteMediaItem().getUrl())));
                }
                if (!ListenerUtil.mutListener.listen(8443)) {
                    noteBlockHolder.getVideoView().setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8441)) {
                    noteBlockHolder.hideVideoView();
                }
            }
        }
        // Note text
        Spannable noteText = getNoteText();
        if (!ListenerUtil.mutListener.listen(8479)) {
            if (!TextUtils.isEmpty(noteText)) {
                if (!ListenerUtil.mutListener.listen(8478)) {
                    if (isPingBack()) {
                        if (!ListenerUtil.mutListener.listen(8472)) {
                            noteBlockHolder.getTextView().setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(8473)) {
                            noteBlockHolder.getMaterialButton().setVisibility(View.GONE);
                        }
                        if (!ListenerUtil.mutListener.listen(8474)) {
                            noteBlockHolder.getDivider().setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(8475)) {
                            noteBlockHolder.getButton().setVisibility(View.VISIBLE);
                        }
                        if (!ListenerUtil.mutListener.listen(8476)) {
                            noteBlockHolder.getButton().setText(noteText.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(8477)) {
                            noteBlockHolder.getButton().setOnClickListener(v -> {
                                if (getOnNoteBlockTextClickListener() != null) {
                                    getOnNoteBlockTextClickListener().showSitePreview(0, getMetaSiteUrl());
                                }
                            });
                        }
                    } else {
                        int textViewVisibility = View.VISIBLE;
                        if (!ListenerUtil.mutListener.listen(8467)) {
                            if (mIsBadge) {
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                if (!ListenerUtil.mutListener.listen(8451)) {
                                    params.gravity = Gravity.CENTER_HORIZONTAL;
                                }
                                if (!ListenerUtil.mutListener.listen(8452)) {
                                    noteBlockHolder.getTextView().setLayoutParams(params);
                                }
                                if (!ListenerUtil.mutListener.listen(8453)) {
                                    noteBlockHolder.getTextView().setGravity(Gravity.CENTER_HORIZONTAL);
                                }
                                int padding;
                                if (mIsViewMilestone) {
                                    padding = 40;
                                } else {
                                    padding = 8;
                                }
                                if (!ListenerUtil.mutListener.listen(8454)) {
                                    noteBlockHolder.getTextView().setPadding(0, DisplayUtils.dpToPx(view.getContext(), padding), 0, 0);
                                }
                                if (!ListenerUtil.mutListener.listen(8457)) {
                                    if (AccessibilityUtils.isAccessibilityEnabled(noteBlockHolder.getTextView().getContext())) {
                                        if (!ListenerUtil.mutListener.listen(8455)) {
                                            noteBlockHolder.getTextView().setClickable(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(8456)) {
                                            noteBlockHolder.getTextView().setLongClickable(false);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(8466)) {
                                    if (mIsViewMilestone) {
                                        if (!ListenerUtil.mutListener.listen(8465)) {
                                            if (FormattableContentUtilsKt.isMobileButton(mNoteData)) {
                                                if (!ListenerUtil.mutListener.listen(8460)) {
                                                    textViewVisibility = View.GONE;
                                                }
                                                if (!ListenerUtil.mutListener.listen(8461)) {
                                                    noteBlockHolder.getButton().setVisibility(View.GONE);
                                                }
                                                if (!ListenerUtil.mutListener.listen(8462)) {
                                                    noteBlockHolder.getMaterialButton().setVisibility(View.VISIBLE);
                                                }
                                                if (!ListenerUtil.mutListener.listen(8463)) {
                                                    noteBlockHolder.getMaterialButton().setText(noteText.toString());
                                                }
                                                if (!ListenerUtil.mutListener.listen(8464)) {
                                                    noteBlockHolder.getMaterialButton().setOnClickListener(v -> {
                                                        FormattableRange buttonRange = FormattableContentUtilsKt.getMobileButtonRange(mNoteData);
                                                        if (getOnNoteBlockTextClickListener() != null && buttonRange != null) {
                                                            NoteBlockClickableSpan clickableSpan = new NoteBlockClickableSpan(buttonRange, true, false);
                                                            getOnNoteBlockTextClickListener().onNoteBlockTextClicked(clickableSpan);
                                                        }
                                                    });
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(8458)) {
                                                    noteBlockHolder.getTextView().setTextSize(28);
                                                }
                                                TypefaceSpan typefaceSpan = new TypefaceSpan("serif");
                                                if (!ListenerUtil.mutListener.listen(8459)) {
                                                    noteText.setSpan(typefaceSpan, 0, noteText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(8449)) {
                                    noteBlockHolder.getTextView().setGravity(Gravity.NO_GRAVITY);
                                }
                                if (!ListenerUtil.mutListener.listen(8450)) {
                                    noteBlockHolder.getTextView().setPadding(0, 0, 0, 0);
                                }
                            }
                        }
                        NoteBlockClickableSpan[] spans = noteText.getSpans(0, noteText.length(), NoteBlockClickableSpan.class);
                        if (!ListenerUtil.mutListener.listen(8469)) {
                            {
                                long _loopCounter173 = 0;
                                for (NoteBlockClickableSpan span : spans) {
                                    ListenerUtil.loopListener.listen("_loopCounter173", ++_loopCounter173);
                                    if (!ListenerUtil.mutListener.listen(8468)) {
                                        span.enableColors(view.getContext());
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8470)) {
                            noteBlockHolder.getTextView().setText(noteText);
                        }
                        if (!ListenerUtil.mutListener.listen(8471)) {
                            noteBlockHolder.getTextView().setVisibility(textViewVisibility);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8445)) {
                    noteBlockHolder.getButton().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(8446)) {
                    noteBlockHolder.getDivider().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(8447)) {
                    noteBlockHolder.getMaterialButton().setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(8448)) {
                    noteBlockHolder.getTextView().setVisibility(View.GONE);
                }
            }
        }
        return view;
    }

    public Object getViewHolder(View view) {
        return new BasicNoteBlockHolder(view);
    }

    static class BasicNoteBlockHolder {

        private final LinearLayout mRootLayout;

        private final WPTextView mTextView;

        private final Button mButton;

        private final Button mMaterialButton;

        private final View mDivider;

        private ImageView mImageView;

        private VideoView mVideoView;

        BasicNoteBlockHolder(View view) {
            mRootLayout = (LinearLayout) view;
            mTextView = view.findViewById(R.id.note_text);
            if (!ListenerUtil.mutListener.listen(8480)) {
                mTextView.setMovementMethod(new NoteBlockLinkMovementMethod());
            }
            mButton = view.findViewById(R.id.note_button);
            mMaterialButton = view.findViewById(R.id.note_material_button);
            mDivider = view.findViewById(R.id.divider_view);
        }

        public WPTextView getTextView() {
            return mTextView;
        }

        public Button getButton() {
            return mButton;
        }

        public Button getMaterialButton() {
            return mMaterialButton;
        }

        public View getDivider() {
            return mDivider;
        }

        public ImageView getImageView() {
            if (!ListenerUtil.mutListener.listen(8486)) {
                if (mImageView == null) {
                    if (!ListenerUtil.mutListener.listen(8481)) {
                        mImageView = new ImageView(mRootLayout.getContext());
                    }
                    int imageSize = DisplayUtils.dpToPx(mRootLayout.getContext(), 180);
                    int imagePadding = mRootLayout.getContext().getResources().getDimensionPixelSize(R.dimen.margin_large);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(imageSize, imageSize);
                    if (!ListenerUtil.mutListener.listen(8482)) {
                        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
                    }
                    if (!ListenerUtil.mutListener.listen(8483)) {
                        mImageView.setLayoutParams(layoutParams);
                    }
                    if (!ListenerUtil.mutListener.listen(8484)) {
                        mImageView.setPadding(0, imagePadding, 0, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(8485)) {
                        mRootLayout.addView(mImageView, 0);
                    }
                }
            }
            return mImageView;
        }

        public VideoView getVideoView() {
            if (!ListenerUtil.mutListener.listen(8495)) {
                if (mVideoView == null) {
                    if (!ListenerUtil.mutListener.listen(8487)) {
                        mVideoView = new VideoView(mRootLayout.getContext());
                    }
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dpToPx(mRootLayout.getContext(), 220));
                    if (!ListenerUtil.mutListener.listen(8488)) {
                        mVideoView.setLayoutParams(layoutParams);
                    }
                    if (!ListenerUtil.mutListener.listen(8489)) {
                        mRootLayout.addView(mVideoView, 0);
                    }
                    // Attach a mediaController if we are displaying a video.
                    final MediaController mediaController = new MediaController(mRootLayout.getContext());
                    if (!ListenerUtil.mutListener.listen(8490)) {
                        mediaController.setMediaPlayer(mVideoView);
                    }
                    if (!ListenerUtil.mutListener.listen(8491)) {
                        mVideoView.setMediaController(mediaController);
                    }
                    if (!ListenerUtil.mutListener.listen(8492)) {
                        mediaController.requestFocus();
                    }
                    if (!ListenerUtil.mutListener.listen(8494)) {
                        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                if (!ListenerUtil.mutListener.listen(8493)) {
                                    // Show the media controls when the video is ready to be played.
                                    mediaController.show(0);
                                }
                            }
                        });
                    }
                }
            }
            return mVideoView;
        }

        public void hideImageView() {
            if (!ListenerUtil.mutListener.listen(8497)) {
                if (mImageView != null) {
                    if (!ListenerUtil.mutListener.listen(8496)) {
                        mImageView.setVisibility(View.GONE);
                    }
                }
            }
        }

        public void hideVideoView() {
            if (!ListenerUtil.mutListener.listen(8499)) {
                if (mVideoView != null) {
                    if (!ListenerUtil.mutListener.listen(8498)) {
                        mVideoView.setVisibility(View.GONE);
                    }
                }
            }
        }
    }
}
