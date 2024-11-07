package org.wordpress.android.ui.uploads;

import androidx.annotation.NonNull;
import org.m4m.MediaComposer;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.WPVideoUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.Map;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.MEDIA_VIDEO_CANT_OPTIMIZE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class M4mVideoOptimizer extends VideoOptimizerBase implements org.m4m.IProgressListener {

    public M4mVideoOptimizer(@NonNull MediaModel media, @NonNull VideoOptimizationListener listener) {
        super(media, listener);
    }

    /*
     * IProgressListener handlers
     */
    @Override
    public void onMediaStart() {
        if (!ListenerUtil.mutListener.listen(23662)) {
            mStartTimeMS = System.currentTimeMillis();
        }
    }

    @Override
    public void onMediaProgress(float progress) {
        if (!ListenerUtil.mutListener.listen(23663)) {
            sendProgressIfNeeded(progress);
        }
    }

    @Override
    public void onMediaDone() {
        if (!ListenerUtil.mutListener.listen(23664)) {
            trackVideoProcessingEvents(false, null);
        }
        if (!ListenerUtil.mutListener.listen(23665)) {
            selectMediaAndSendCompletionToListener();
        }
    }

    @Override
    public void onMediaPause() {
        if (!ListenerUtil.mutListener.listen(23666)) {
            AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > paused");
        }
    }

    @Override
    public void onMediaStop() {
        if (!ListenerUtil.mutListener.listen(23667)) {
            // 2. When we call 'stop' on the media composer
            AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > stopped");
        }
    }

    @Override
    public void onError(Exception e) {
        if (!ListenerUtil.mutListener.listen(23668)) {
            AppLog.e(AppLog.T.MEDIA, "VideoOptimizer > Can't optimize the video", e);
        }
        if (!ListenerUtil.mutListener.listen(23669)) {
            trackVideoProcessingEvents(true, e);
        }
        if (!ListenerUtil.mutListener.listen(23670)) {
            mListener.onVideoOptimizationCompleted(mMedia);
        }
    }

    @Override
    public void start() {
        if (!ListenerUtil.mutListener.listen(23671)) {
            if (!arePathsValidated())
                return;
        }
        MediaComposer mediaComposer = null;
        boolean wasNpeDetected = false;
        try {
            if (!ListenerUtil.mutListener.listen(23674)) {
                mediaComposer = WPVideoUtils.getVideoOptimizationComposer(getContext(), mInputPath, mOutputPath, this, AppPrefs.getVideoOptimizeWidth(), AppPrefs.getVideoOptimizeQuality());
            }
        } catch (NullPointerException npe) {
            if (!ListenerUtil.mutListener.listen(23672)) {
                AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > NullPointerException while getting composer " + npe.getMessage());
            }
            if (!ListenerUtil.mutListener.listen(23673)) {
                wasNpeDetected = true;
            }
        }
        if (!ListenerUtil.mutListener.listen(23680)) {
            if (mediaComposer == null) {
                if (!ListenerUtil.mutListener.listen(23675)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > null composer");
                }
                Map<String, Object> properties = AnalyticsUtils.getMediaProperties(getContext(), true, null, mInputPath);
                if (!ListenerUtil.mutListener.listen(23676)) {
                    properties.put("was_npe_detected", wasNpeDetected);
                }
                if (!ListenerUtil.mutListener.listen(23677)) {
                    properties.put("optimizer_lib", "m4m");
                }
                if (!ListenerUtil.mutListener.listen(23678)) {
                    AnalyticsTracker.track(MEDIA_VIDEO_CANT_OPTIMIZE, properties);
                }
                if (!ListenerUtil.mutListener.listen(23679)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return;
            }
        }
        // setup done. We're ready to optimize!
        try {
            if (!ListenerUtil.mutListener.listen(23683)) {
                mediaComposer.start();
            }
            if (!ListenerUtil.mutListener.listen(23684)) {
                AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > composer started");
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(23681)) {
                AppLog.e(AppLog.T.MEDIA, "VideoOptimizer > failed to start composer", e);
            }
            if (!ListenerUtil.mutListener.listen(23682)) {
                mListener.onVideoOptimizationCompleted(mMedia);
            }
        }
    }
}
