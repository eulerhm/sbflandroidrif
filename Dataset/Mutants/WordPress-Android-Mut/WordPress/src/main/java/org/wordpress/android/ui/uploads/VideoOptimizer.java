package org.wordpress.android.ui.uploads;

import android.content.Context;
import androidx.annotation.NonNull;
import org.m4m.MediaComposer;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.FileUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.WPVideoUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.MEDIA_VIDEO_CANT_OPTIMIZE;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.MEDIA_VIDEO_OPTIMIZED;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.MEDIA_VIDEO_OPTIMIZE_ERROR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VideoOptimizer implements org.m4m.IProgressListener {

    private final File mCacheDir;

    private final MediaModel mMedia;

    private final VideoOptimizationListener mListener;

    private final String mFilename;

    private final String mInputPath;

    private String mOutputPath;

    private long mStartTimeMS;

    private float mLastProgress;

    public VideoOptimizer(@NonNull MediaModel media, @NonNull VideoOptimizationListener listener) {
        mCacheDir = getContext().getCacheDir();
        mListener = listener;
        mMedia = media;
        mInputPath = mMedia.getFilePath();
        mFilename = MediaUtils.generateTimeStampedFileName("video/mp4");
    }

    private Context getContext() {
        return WordPress.getContext();
    }

    public void start() {
        if (!ListenerUtil.mutListener.listen(25143)) {
            if (mInputPath == null) {
                if (!ListenerUtil.mutListener.listen(25141)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > empty input path");
                }
                if (!ListenerUtil.mutListener.listen(25142)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25146)) {
            if (mCacheDir == null) {
                if (!ListenerUtil.mutListener.listen(25144)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > null cache dir");
                }
                if (!ListenerUtil.mutListener.listen(25145)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25150)) {
            if ((ListenerUtil.mutListener.listen(25147) ? (!mCacheDir.exists() || !mCacheDir.mkdirs()) : (!mCacheDir.exists() && !mCacheDir.mkdirs()))) {
                if (!ListenerUtil.mutListener.listen(25148)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > cannot create cache dir");
                }
                if (!ListenerUtil.mutListener.listen(25149)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(25151)) {
            mOutputPath = mCacheDir.getPath() + "/" + mFilename;
        }
        MediaComposer mediaComposer = null;
        boolean wasNpeDetected = false;
        try {
            if (!ListenerUtil.mutListener.listen(25154)) {
                mediaComposer = WPVideoUtils.getVideoOptimizationComposer(getContext(), mInputPath, mOutputPath, this, AppPrefs.getVideoOptimizeWidth(), AppPrefs.getVideoOptimizeQuality());
            }
        } catch (NullPointerException npe) {
            if (!ListenerUtil.mutListener.listen(25152)) {
                AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > NullPointerException while getting composer " + npe.getMessage());
            }
            if (!ListenerUtil.mutListener.listen(25153)) {
                wasNpeDetected = true;
            }
        }
        if (!ListenerUtil.mutListener.listen(25160)) {
            if (mediaComposer == null) {
                if (!ListenerUtil.mutListener.listen(25155)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > null composer");
                }
                Map<String, Object> properties = AnalyticsUtils.getMediaProperties(getContext(), true, null, mInputPath);
                if (!ListenerUtil.mutListener.listen(25156)) {
                    properties.put("was_npe_detected", wasNpeDetected);
                }
                if (!ListenerUtil.mutListener.listen(25157)) {
                    properties.put("optimizer_lib", "m4m");
                }
                if (!ListenerUtil.mutListener.listen(25158)) {
                    AnalyticsTracker.track(MEDIA_VIDEO_CANT_OPTIMIZE, properties);
                }
                if (!ListenerUtil.mutListener.listen(25159)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return;
            }
        }
        // setup done. We're ready to optimize!
        try {
            if (!ListenerUtil.mutListener.listen(25163)) {
                mediaComposer.start();
            }
            if (!ListenerUtil.mutListener.listen(25164)) {
                AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > composer started");
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(25161)) {
                AppLog.e(AppLog.T.MEDIA, "VideoOptimizer > failed to start composer", e);
            }
            if (!ListenerUtil.mutListener.listen(25162)) {
                mListener.onVideoOptimizationCompleted(mMedia);
            }
        }
    }

    private void trackVideoProcessingEvents(boolean isError, Exception exception) {
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> inputVideoProperties = AnalyticsUtils.getMediaProperties(getContext(), true, null, mInputPath);
        if (!ListenerUtil.mutListener.listen(25165)) {
            putAllWithPrefix("input_video_", inputVideoProperties, properties);
        }
        if (!ListenerUtil.mutListener.listen(25180)) {
            if (mOutputPath != null) {
                Map<String, Object> outputVideoProperties = AnalyticsUtils.getMediaProperties(getContext(), true, null, mOutputPath);
                if (!ListenerUtil.mutListener.listen(25166)) {
                    putAllWithPrefix("output_video_", outputVideoProperties, properties);
                }
                String savedMegabytes = String.valueOf((ListenerUtil.mutListener.listen(25178) ? (((ListenerUtil.mutListener.listen(25170) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25169) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25168) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25167) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) % ((ListenerUtil.mutListener.listen(25174) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25173) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25172) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25171) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(25177) ? (((ListenerUtil.mutListener.listen(25170) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25169) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25168) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25167) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) * ((ListenerUtil.mutListener.listen(25174) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25173) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25172) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25171) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(25176) ? (((ListenerUtil.mutListener.listen(25170) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25169) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25168) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25167) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) - ((ListenerUtil.mutListener.listen(25174) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25173) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25172) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25171) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(25175) ? (((ListenerUtil.mutListener.listen(25170) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25169) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25168) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25167) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) + ((ListenerUtil.mutListener.listen(25174) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25173) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25172) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25171) ? (1024 + 1024) : (1024 * 1024))))))) : (((ListenerUtil.mutListener.listen(25170) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25169) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25168) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25167) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) / ((ListenerUtil.mutListener.listen(25174) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25173) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25172) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25171) ? (1024 + 1024) : (1024 * 1024))))))))))));
                if (!ListenerUtil.mutListener.listen(25179)) {
                    properties.put("saved_megabytes", savedMegabytes);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(25185)) {
            properties.put("elapsed_time_ms", (ListenerUtil.mutListener.listen(25184) ? (endTime % mStartTimeMS) : (ListenerUtil.mutListener.listen(25183) ? (endTime / mStartTimeMS) : (ListenerUtil.mutListener.listen(25182) ? (endTime * mStartTimeMS) : (ListenerUtil.mutListener.listen(25181) ? (endTime + mStartTimeMS) : (endTime - mStartTimeMS))))));
        }
        if (!ListenerUtil.mutListener.listen(25189)) {
            if (isError) {
                if (!ListenerUtil.mutListener.listen(25186)) {
                    properties.put("exception_name", exception.getClass().getCanonicalName());
                }
                if (!ListenerUtil.mutListener.listen(25187)) {
                    properties.put("exception_message", exception.getMessage());
                }
                if (!ListenerUtil.mutListener.listen(25188)) {
                    AppLog.e(T.MEDIA, exception);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25190)) {
            properties.put("optimizer_lib", "m4m");
        }
        AnalyticsTracker.Stat currentStatToTrack = isError ? MEDIA_VIDEO_OPTIMIZE_ERROR : MEDIA_VIDEO_OPTIMIZED;
        if (!ListenerUtil.mutListener.listen(25191)) {
            AnalyticsTracker.track(currentStatToTrack, properties);
        }
    }

    private void putAllWithPrefix(String prefix, Map<String, Object> inputMap, Map<String, Object> targetMap) {
        if (!ListenerUtil.mutListener.listen(25195)) {
            if ((ListenerUtil.mutListener.listen(25192) ? (inputMap != null || targetMap != null) : (inputMap != null && targetMap != null))) {
                if (!ListenerUtil.mutListener.listen(25194)) {
                    {
                        long _loopCounter393 = 0;
                        for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter393", ++_loopCounter393);
                            if (!ListenerUtil.mutListener.listen(25193)) {
                                targetMap.put(prefix + entry.getKey(), entry.getValue());
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * IProgressListener handlers
     */
    @Override
    public void onMediaStart() {
        if (!ListenerUtil.mutListener.listen(25196)) {
            mStartTimeMS = System.currentTimeMillis();
        }
    }

    @Override
    public void onMediaProgress(float progress) {
        if (!ListenerUtil.mutListener.listen(25215)) {
            // this event fires quite often so we only call the listener when progress increases by 1% or more
            if ((ListenerUtil.mutListener.listen(25211) ? ((ListenerUtil.mutListener.listen(25201) ? (mLastProgress >= 0) : (ListenerUtil.mutListener.listen(25200) ? (mLastProgress <= 0) : (ListenerUtil.mutListener.listen(25199) ? (mLastProgress > 0) : (ListenerUtil.mutListener.listen(25198) ? (mLastProgress < 0) : (ListenerUtil.mutListener.listen(25197) ? (mLastProgress != 0) : (mLastProgress == 0)))))) && ((ListenerUtil.mutListener.listen(25210) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) >= 0.01F) : (ListenerUtil.mutListener.listen(25209) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) <= 0.01F) : (ListenerUtil.mutListener.listen(25208) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) < 0.01F) : (ListenerUtil.mutListener.listen(25207) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) != 0.01F) : (ListenerUtil.mutListener.listen(25206) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) == 0.01F) : ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) > 0.01F)))))))) : ((ListenerUtil.mutListener.listen(25201) ? (mLastProgress >= 0) : (ListenerUtil.mutListener.listen(25200) ? (mLastProgress <= 0) : (ListenerUtil.mutListener.listen(25199) ? (mLastProgress > 0) : (ListenerUtil.mutListener.listen(25198) ? (mLastProgress < 0) : (ListenerUtil.mutListener.listen(25197) ? (mLastProgress != 0) : (mLastProgress == 0)))))) || ((ListenerUtil.mutListener.listen(25210) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) >= 0.01F) : (ListenerUtil.mutListener.listen(25209) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) <= 0.01F) : (ListenerUtil.mutListener.listen(25208) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) < 0.01F) : (ListenerUtil.mutListener.listen(25207) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) != 0.01F) : (ListenerUtil.mutListener.listen(25206) ? ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) == 0.01F) : ((ListenerUtil.mutListener.listen(25205) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25204) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25203) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25202) ? (progress + mLastProgress) : (progress - mLastProgress))))) > 0.01F)))))))))) {
                if (!ListenerUtil.mutListener.listen(25212)) {
                    AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > " + mMedia.getId() + " - progress: " + progress);
                }
                if (!ListenerUtil.mutListener.listen(25213)) {
                    mLastProgress = progress;
                }
                if (!ListenerUtil.mutListener.listen(25214)) {
                    mListener.onVideoOptimizationProgress(mMedia, progress);
                }
            }
        }
    }

    @Override
    public void onMediaDone() {
        if (!ListenerUtil.mutListener.listen(25216)) {
            trackVideoProcessingEvents(false, null);
        }
        long originalFileSize = FileUtils.length(mInputPath);
        long optimizedFileSize = FileUtils.length(mOutputPath);
        long savings = (ListenerUtil.mutListener.listen(25220) ? (originalFileSize % optimizedFileSize) : (ListenerUtil.mutListener.listen(25219) ? (originalFileSize / optimizedFileSize) : (ListenerUtil.mutListener.listen(25218) ? (originalFileSize * optimizedFileSize) : (ListenerUtil.mutListener.listen(25217) ? (originalFileSize + optimizedFileSize) : (originalFileSize - optimizedFileSize)))));
        double savingsKb = (ListenerUtil.mutListener.listen(25224) ? (Math.abs(savings) % 1024) : (ListenerUtil.mutListener.listen(25223) ? (Math.abs(savings) * 1024) : (ListenerUtil.mutListener.listen(25222) ? (Math.abs(savings) - 1024) : (ListenerUtil.mutListener.listen(25221) ? (Math.abs(savings) + 1024) : (Math.abs(savings) / 1024)))));
        String strSavingsKb = new DecimalFormat("0.00").format(savingsKb).concat("KB");
        if (!ListenerUtil.mutListener.listen(25236)) {
            // make sure the resulting file is smaller than the original
            if ((ListenerUtil.mutListener.listen(25229) ? (savings >= 0) : (ListenerUtil.mutListener.listen(25228) ? (savings > 0) : (ListenerUtil.mutListener.listen(25227) ? (savings < 0) : (ListenerUtil.mutListener.listen(25226) ? (savings != 0) : (ListenerUtil.mutListener.listen(25225) ? (savings == 0) : (savings <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(25234)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > no savings, optimized file is " + strSavingsKb + " larger");
                }
                if (!ListenerUtil.mutListener.listen(25235)) {
                    // no savings, so use original unoptimized media
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25230)) {
                    AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > reduced by " + strSavingsKb);
                }
                if (!ListenerUtil.mutListener.listen(25231)) {
                    // update media object to point to optimized video
                    mMedia.setFilePath(mOutputPath);
                }
                if (!ListenerUtil.mutListener.listen(25232)) {
                    mMedia.setFileName(mFilename);
                }
                if (!ListenerUtil.mutListener.listen(25233)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
            }
        }
    }

    @Override
    public void onMediaPause() {
        if (!ListenerUtil.mutListener.listen(25237)) {
            AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > paused");
        }
    }

    @Override
    public void onMediaStop() {
        if (!ListenerUtil.mutListener.listen(25238)) {
            // 2. When we call 'stop' on the media composer
            AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > stopped");
        }
    }

    @Override
    public void onError(Exception e) {
        if (!ListenerUtil.mutListener.listen(25239)) {
            AppLog.e(AppLog.T.MEDIA, "VideoOptimizer > Can't optimize the video", e);
        }
        if (!ListenerUtil.mutListener.listen(25240)) {
            trackVideoProcessingEvents(true, e);
        }
        if (!ListenerUtil.mutListener.listen(25241)) {
            mListener.onVideoOptimizationCompleted(mMedia);
        }
    }
}
