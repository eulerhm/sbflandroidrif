package org.wordpress.android.ui.uploads;

import androidx.annotation.NonNull;
import com.daasuu.mp4compose.composer.ComposerInterface;
import com.daasuu.mp4compose.composer.Listener;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.WPVideoUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.util.Map;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.MEDIA_VIDEO_CANT_OPTIMIZE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Mp4ComposerVideoOptimizer extends VideoOptimizerBase implements Listener {

    public Mp4ComposerVideoOptimizer(@NonNull MediaModel media, @NonNull VideoOptimizationListener listener) {
        super(media, listener);
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(23830)) {
            mStartTimeMS = System.currentTimeMillis();
        }
    }

    @Override
    public void onProgress(double progress) {
        if (!ListenerUtil.mutListener.listen(23836)) {
            // NOTE: progress can be -1 with Mp4Composer library
            if ((ListenerUtil.mutListener.listen(23835) ? (progress >= 0) : (ListenerUtil.mutListener.listen(23834) ? (progress <= 0) : (ListenerUtil.mutListener.listen(23833) ? (progress > 0) : (ListenerUtil.mutListener.listen(23832) ? (progress != 0) : (ListenerUtil.mutListener.listen(23831) ? (progress == 0) : (progress < 0)))))))
                return;
        }
        if (!ListenerUtil.mutListener.listen(23837)) {
            sendProgressIfNeeded((float) progress);
        }
    }

    @Override
    public void onCompleted() {
        if (!ListenerUtil.mutListener.listen(23838)) {
            trackVideoProcessingEvents(false, null);
        }
        if (!ListenerUtil.mutListener.listen(23839)) {
            selectMediaAndSendCompletionToListener();
        }
    }

    @Override
    public void onCanceled() {
        if (!ListenerUtil.mutListener.listen(23840)) {
            AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > stopped");
        }
    }

    @Override
    public void onFailed(@NotNull Exception exception) {
        if (!ListenerUtil.mutListener.listen(23841)) {
            AppLog.e(AppLog.T.MEDIA, "VideoOptimizer > Can't optimize the video", exception);
        }
        if (!ListenerUtil.mutListener.listen(23842)) {
            trackVideoProcessingEvents(true, exception);
        }
        if (!ListenerUtil.mutListener.listen(23843)) {
            mListener.onVideoOptimizationCompleted(mMedia);
        }
    }

    @Override
    public void start() {
        if (!ListenerUtil.mutListener.listen(23844)) {
            if (!arePathsValidated())
                return;
        }
        ComposerInterface composer = null;
        try {
            if (!ListenerUtil.mutListener.listen(23847)) {
                composer = WPVideoUtils.getVideoOptimizationComposer(mInputPath, mOutputPath, this, AppPrefs.getVideoOptimizeWidth(), AppPrefs.getVideoOptimizeQuality());
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(23845)) {
                AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > Exception while getting composer " + e.getMessage());
            }
            if (!ListenerUtil.mutListener.listen(23846)) {
                composer = null;
            }
        }
        if (!ListenerUtil.mutListener.listen(23852)) {
            if (composer == null) {
                if (!ListenerUtil.mutListener.listen(23848)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > null composer");
                }
                Map<String, Object> properties = AnalyticsUtils.getMediaProperties(getContext(), true, null, mInputPath);
                if (!ListenerUtil.mutListener.listen(23849)) {
                    properties.put("optimizer_lib", "mp4composer");
                }
                if (!ListenerUtil.mutListener.listen(23850)) {
                    AnalyticsTracker.track(MEDIA_VIDEO_CANT_OPTIMIZE, properties);
                }
                if (!ListenerUtil.mutListener.listen(23851)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23853)) {
            // setup done. We're ready to optimize!
            composer.start();
        }
    }
}
