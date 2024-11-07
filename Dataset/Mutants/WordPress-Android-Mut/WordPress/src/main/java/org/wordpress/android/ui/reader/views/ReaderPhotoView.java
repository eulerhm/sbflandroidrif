package org.wordpress.android.ui.reader.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import org.wordpress.android.R;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.util.image.ImageManager.RequestListener;
import org.wordpress.android.util.image.ImageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * used by ReaderPhotoViewerActivity to show full-width images - based on Volley's ImageView
 * but adds pinch/zoom and the ability to first load a lo-res version of the image
 */
public class ReaderPhotoView extends RelativeLayout {

    public interface PhotoViewListener {

        void onTapPhotoView();
    }

    private PhotoViewListener mPhotoViewListener;

    private String mLoResImageUrl;

    private String mHiResImageUrl;

    private final PhotoView mImageView;

    private final ProgressBar mProgress;

    private final TextView mTxtError;

    private boolean mIsInitialLayout = true;

    private final ImageManager mImageManager;

    public ReaderPhotoView(Context context) {
        this(context, null);
    }

    public ReaderPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(20063)) {
            inflate(context, R.layout.reader_photo_view, this);
        }
        // ImageView which contains the downloaded image
        mImageView = findViewById(R.id.image_photo);
        // error text that appears when download fails
        mTxtError = findViewById(R.id.text_error);
        // progress bar which appears while downloading
        mProgress = findViewById(R.id.progress_loading);
        mImageManager = ImageManager.getInstance();
    }

    /**
     * @param imageUrl   the url of the image to load
     * @param hiResWidth maximum width of the full-size image
     * @param isPrivate  whether this is an image from a private blog
     * @param listener   listener for taps on this view
     */
    public void setImageUrl(String imageUrl, int hiResWidth, boolean isPrivate, boolean isPrivateAtSite, PhotoViewListener listener) {
        int loResWidth = (int) ((ListenerUtil.mutListener.listen(20067) ? (hiResWidth % 0.10f) : (ListenerUtil.mutListener.listen(20066) ? (hiResWidth / 0.10f) : (ListenerUtil.mutListener.listen(20065) ? (hiResWidth - 0.10f) : (ListenerUtil.mutListener.listen(20064) ? (hiResWidth + 0.10f) : (hiResWidth * 0.10f))))));
        if (!ListenerUtil.mutListener.listen(20068)) {
            mLoResImageUrl = ReaderUtils.getResizedImageUrl(imageUrl, loResWidth, 0, isPrivate, isPrivateAtSite, PhotonUtils.Quality.LOW);
        }
        if (!ListenerUtil.mutListener.listen(20069)) {
            mHiResImageUrl = ReaderUtils.getResizedImageUrl(imageUrl, hiResWidth, 0, isPrivate, isPrivateAtSite, PhotonUtils.Quality.MEDIUM);
        }
        if (!ListenerUtil.mutListener.listen(20070)) {
            mPhotoViewListener = listener;
        }
        if (!ListenerUtil.mutListener.listen(20071)) {
            loadImage();
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean hasLayout() {
        if (!ListenerUtil.mutListener.listen(20076)) {
            // view, hold off on loading the image.
            if ((ListenerUtil.mutListener.listen(20072) ? (getWidth() == 0 || getHeight() == 0) : (getWidth() == 0 && getHeight() == 0))) {
                boolean isFullyWrapContent = (ListenerUtil.mutListener.listen(20074) ? ((ListenerUtil.mutListener.listen(20073) ? (getLayoutParams() != null || getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) : (getLayoutParams() != null && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT)) || getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) : ((ListenerUtil.mutListener.listen(20073) ? (getLayoutParams() != null || getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) : (getLayoutParams() != null && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT)) && getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT));
                if (!ListenerUtil.mutListener.listen(20075)) {
                    if (!isFullyWrapContent) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void loadImage() {
        if (!ListenerUtil.mutListener.listen(20077)) {
            if (!hasLayout()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20078)) {
            showProgress();
        }
        if (!ListenerUtil.mutListener.listen(20085)) {
            mImageManager.loadWithResultListener(mImageView, ImageType.IMAGE, mHiResImageUrl, ScaleType.CENTER, mLoResImageUrl, new RequestListener<Drawable>() {

                @Override
                public void onLoadFailed(@Nullable Exception e, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(20080)) {
                        if (e != null) {
                            if (!ListenerUtil.mutListener.listen(20079)) {
                                AppLog.e(AppLog.T.READER, e);
                            }
                        }
                    }
                    boolean lowResNotLoadedYet = isLoading();
                    if (!ListenerUtil.mutListener.listen(20083)) {
                        if (lowResNotLoadedYet) {
                            if (!ListenerUtil.mutListener.listen(20081)) {
                                hideProgress();
                            }
                            if (!ListenerUtil.mutListener.listen(20082)) {
                                showError();
                            }
                        }
                    }
                }

                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Object model) {
                    if (!ListenerUtil.mutListener.listen(20084)) {
                        handleResponse();
                    }
                }
            });
        }
    }

    private void handleResponse() {
        if (!ListenerUtil.mutListener.listen(20086)) {
            hideProgress();
        }
        if (!ListenerUtil.mutListener.listen(20087)) {
            hideError();
        }
        if (!ListenerUtil.mutListener.listen(20088)) {
            // attach the pinch/zoom handler
            setupOnTapListeners();
        }
    }

    private void setupOnTapListeners() {
        PhotoViewAttacher attacher = mImageView.getAttacher();
        if (!ListenerUtil.mutListener.listen(20089)) {
            attacher.setOnPhotoTapListener((view, v, v2) -> {
                if (mPhotoViewListener != null) {
                    mPhotoViewListener.onTapPhotoView();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(20090)) {
            attacher.setOnViewTapListener((view, v, v2) -> {
                if (mPhotoViewListener != null) {
                    mPhotoViewListener.onTapPhotoView();
                }
            });
        }
    }

    private void showError() {
        if (!ListenerUtil.mutListener.listen(20091)) {
            hideProgress();
        }
        if (!ListenerUtil.mutListener.listen(20093)) {
            if (mTxtError != null) {
                if (!ListenerUtil.mutListener.listen(20092)) {
                    mTxtError.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void hideError() {
        if (!ListenerUtil.mutListener.listen(20094)) {
            hideProgress();
        }
        if (!ListenerUtil.mutListener.listen(20096)) {
            if (mTxtError != null) {
                if (!ListenerUtil.mutListener.listen(20095)) {
                    mTxtError.setVisibility(View.GONE);
                }
            }
        }
    }

    private void showProgress() {
        if (!ListenerUtil.mutListener.listen(20098)) {
            if (mProgress != null) {
                if (!ListenerUtil.mutListener.listen(20097)) {
                    mProgress.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void hideProgress() {
        if (!ListenerUtil.mutListener.listen(20100)) {
            if (mProgress != null) {
                if (!ListenerUtil.mutListener.listen(20099)) {
                    mProgress.setVisibility(View.GONE);
                }
            }
        }
    }

    private boolean isLoading() {
        return (ListenerUtil.mutListener.listen(20101) ? (mProgress != null || mProgress.getVisibility() == VISIBLE) : (mProgress != null && mProgress.getVisibility() == VISIBLE));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!ListenerUtil.mutListener.listen(20102)) {
            super.onLayout(changed, left, top, right, bottom);
        }
        if (!ListenerUtil.mutListener.listen(20109)) {
            if (!isInEditMode()) {
                if (!ListenerUtil.mutListener.listen(20108)) {
                    if (mIsInitialLayout) {
                        if (!ListenerUtil.mutListener.listen(20103)) {
                            mIsInitialLayout = false;
                        }
                        if (!ListenerUtil.mutListener.listen(20104)) {
                            AppLog.d(AppLog.T.READER, "reader photo > initial layout");
                        }
                        if (!ListenerUtil.mutListener.listen(20107)) {
                            if ((ListenerUtil.mutListener.listen(20105) ? (mLoResImageUrl != null || mHiResImageUrl != null) : (mLoResImageUrl != null && mHiResImageUrl != null))) {
                                if (!ListenerUtil.mutListener.listen(20106)) {
                                    loadImage();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(20110)) {
            mIsInitialLayout = true;
        }
        if (!ListenerUtil.mutListener.listen(20111)) {
            super.onDetachedFromWindow();
        }
    }

    @Override
    protected void drawableStateChanged() {
        if (!ListenerUtil.mutListener.listen(20112)) {
            super.drawableStateChanged();
        }
        if (!ListenerUtil.mutListener.listen(20113)) {
            invalidate();
        }
    }
}
