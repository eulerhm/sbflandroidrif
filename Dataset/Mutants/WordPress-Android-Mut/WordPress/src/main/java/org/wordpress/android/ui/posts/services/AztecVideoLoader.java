package org.wordpress.android.ui.posts.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore.Images.Thumbnails;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.util.ImageUtils;
import org.wordpress.aztec.Html;
import java.io.File;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AztecVideoLoader implements Html.VideoThumbnailGetter {

    private final Context mContext;

    private final Drawable mLoadingInProgress;

    @Inject
    AuthenticationUtils mAuthenticationUtils;

    public AztecVideoLoader(Context context, Drawable loadingInProgressDrawable) {
        if (!ListenerUtil.mutListener.listen(11233)) {
            ((WordPress) WordPress.getContext().getApplicationContext()).component().inject(this);
        }
        mContext = context;
        mLoadingInProgress = loadingInProgressDrawable;
    }

    public void loadVideoThumbnail(final String url, final Html.VideoThumbnailGetter.Callbacks callbacks, final int maxWidth) {
        if (!ListenerUtil.mutListener.listen(11234)) {
            loadVideoThumbnail(url, callbacks, maxWidth, 0);
        }
    }

    public void loadVideoThumbnail(final String url, final Html.VideoThumbnailGetter.Callbacks callbacks, final int maxWidth, final int minWidth) {
        if (!ListenerUtil.mutListener.listen(11242)) {
            if ((ListenerUtil.mutListener.listen(11240) ? (TextUtils.isEmpty(url) && (ListenerUtil.mutListener.listen(11239) ? (maxWidth >= 0) : (ListenerUtil.mutListener.listen(11238) ? (maxWidth > 0) : (ListenerUtil.mutListener.listen(11237) ? (maxWidth < 0) : (ListenerUtil.mutListener.listen(11236) ? (maxWidth != 0) : (ListenerUtil.mutListener.listen(11235) ? (maxWidth == 0) : (maxWidth <= 0))))))) : (TextUtils.isEmpty(url) || (ListenerUtil.mutListener.listen(11239) ? (maxWidth >= 0) : (ListenerUtil.mutListener.listen(11238) ? (maxWidth > 0) : (ListenerUtil.mutListener.listen(11237) ? (maxWidth < 0) : (ListenerUtil.mutListener.listen(11236) ? (maxWidth != 0) : (ListenerUtil.mutListener.listen(11235) ? (maxWidth == 0) : (maxWidth <= 0))))))))) {
                if (!ListenerUtil.mutListener.listen(11241)) {
                    callbacks.onThumbnailFailed();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11243)) {
            callbacks.onThumbnailLoading(mLoadingInProgress);
        }
        if (!ListenerUtil.mutListener.listen(11244)) {
            new LoadAztecVideoTask(mContext, mAuthenticationUtils, url, maxWidth, callbacks).execute();
        }
    }

    private static class LoadAztecVideoTask extends AsyncTask<Void, Void, Bitmap> {

        final String mUrl;

        final int mMaxWidth;

        final Html.VideoThumbnailGetter.Callbacks mCallbacks;

        final AuthenticationUtils mAuthenticationUtils;

        final WeakReference<Context> mContext;

        LoadAztecVideoTask(Context context, AuthenticationUtils authenticationUtils, String url, int maxWidth, Html.VideoThumbnailGetter.Callbacks callbacks) {
            mContext = new WeakReference<>(context);
            mAuthenticationUtils = authenticationUtils;
            mUrl = url;
            mMaxWidth = maxWidth;
            mCallbacks = callbacks;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            if (!ListenerUtil.mutListener.listen(11245)) {
                // If local file
                if (new File(mUrl).exists()) {
                    return ThumbnailUtils.createVideoThumbnail(mUrl, Thumbnails.FULL_SCREEN_KIND);
                }
            }
            return ImageUtils.getVideoFrameFromVideo(mUrl, mMaxWidth, mAuthenticationUtils.getAuthHeaders(mUrl));
        }

        @Override
        protected void onPostExecute(Bitmap thumb) {
            if (!ListenerUtil.mutListener.listen(11246)) {
                if (mContext.get() == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(11248)) {
                if (thumb == null) {
                    if (!ListenerUtil.mutListener.listen(11247)) {
                        mCallbacks.onThumbnailFailed();
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(11249)) {
                thumb = ImageUtils.getScaledBitmapAtLongestSide(thumb, mMaxWidth);
            }
            if (!ListenerUtil.mutListener.listen(11250)) {
                thumb.setDensity(DisplayMetrics.DENSITY_DEFAULT);
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(mContext.get().getResources(), thumb);
            if (!ListenerUtil.mutListener.listen(11251)) {
                mCallbacks.onThumbnailLoaded(bitmapDrawable);
            }
        }
    }
}
