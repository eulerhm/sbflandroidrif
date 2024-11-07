package org.wordpress.android.ui.uploads;

import android.content.Context;
import androidx.annotation.NonNull;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.FileUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.MEDIA_VIDEO_OPTIMIZED;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.MEDIA_VIDEO_OPTIMIZE_ERROR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class VideoOptimizerBase implements VideoOptimizerProvider {

    protected final File mCacheDir;

    protected final MediaModel mMedia;

    protected final VideoOptimizationListener mListener;

    protected final String mFilename;

    protected final String mInputPath;

    protected String mOutputPath;

    protected long mStartTimeMS;

    protected float mLastProgress;

    public VideoOptimizerBase(@NonNull MediaModel media, @NonNull VideoOptimizationListener listener) {
        mCacheDir = getContext().getCacheDir();
        mListener = listener;
        mMedia = media;
        mInputPath = mMedia.getFilePath();
        mFilename = MediaUtils.generateTimeStampedFileName("video/mp4");
    }

    protected Context getContext() {
        return WordPress.getContext();
    }

    protected boolean arePathsValidated() {
        if (!ListenerUtil.mutListener.listen(25244)) {
            if (mInputPath == null) {
                if (!ListenerUtil.mutListener.listen(25242)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > empty input path");
                }
                if (!ListenerUtil.mutListener.listen(25243)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25247)) {
            if (mCacheDir == null) {
                if (!ListenerUtil.mutListener.listen(25245)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > null cache dir");
                }
                if (!ListenerUtil.mutListener.listen(25246)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25251)) {
            if ((ListenerUtil.mutListener.listen(25248) ? (!mCacheDir.exists() || !mCacheDir.mkdirs()) : (!mCacheDir.exists() && !mCacheDir.mkdirs()))) {
                if (!ListenerUtil.mutListener.listen(25249)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > cannot create cache dir");
                }
                if (!ListenerUtil.mutListener.listen(25250)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(25252)) {
            mOutputPath = mCacheDir.getPath() + "/" + mFilename;
        }
        return true;
    }

    protected void trackVideoProcessingEvents(boolean isError, Exception exception) {
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> inputVideoProperties = AnalyticsUtils.getMediaProperties(getContext(), true, null, mInputPath);
        if (!ListenerUtil.mutListener.listen(25253)) {
            putAllWithPrefix("input_video_", inputVideoProperties, properties);
        }
        if (!ListenerUtil.mutListener.listen(25268)) {
            if (mOutputPath != null) {
                Map<String, Object> outputVideoProperties = AnalyticsUtils.getMediaProperties(getContext(), true, null, mOutputPath);
                if (!ListenerUtil.mutListener.listen(25254)) {
                    putAllWithPrefix("output_video_", outputVideoProperties, properties);
                }
                String savedMegabytes = String.valueOf((ListenerUtil.mutListener.listen(25266) ? (((ListenerUtil.mutListener.listen(25258) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25257) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25256) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25255) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) % ((ListenerUtil.mutListener.listen(25262) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25261) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25260) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25259) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(25265) ? (((ListenerUtil.mutListener.listen(25258) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25257) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25256) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25255) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) * ((ListenerUtil.mutListener.listen(25262) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25261) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25260) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25259) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(25264) ? (((ListenerUtil.mutListener.listen(25258) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25257) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25256) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25255) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) - ((ListenerUtil.mutListener.listen(25262) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25261) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25260) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25259) ? (1024 + 1024) : (1024 * 1024))))))) : (ListenerUtil.mutListener.listen(25263) ? (((ListenerUtil.mutListener.listen(25258) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25257) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25256) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25255) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) + ((ListenerUtil.mutListener.listen(25262) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25261) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25260) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25259) ? (1024 + 1024) : (1024 * 1024))))))) : (((ListenerUtil.mutListener.listen(25258) ? (FileUtils.length(mInputPath) % FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25257) ? (FileUtils.length(mInputPath) / FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25256) ? (FileUtils.length(mInputPath) * FileUtils.length(mOutputPath)) : (ListenerUtil.mutListener.listen(25255) ? (FileUtils.length(mInputPath) + FileUtils.length(mOutputPath)) : (FileUtils.length(mInputPath) - FileUtils.length(mOutputPath))))))) / ((ListenerUtil.mutListener.listen(25262) ? (1024 % 1024) : (ListenerUtil.mutListener.listen(25261) ? (1024 / 1024) : (ListenerUtil.mutListener.listen(25260) ? (1024 - 1024) : (ListenerUtil.mutListener.listen(25259) ? (1024 + 1024) : (1024 * 1024))))))))))));
                if (!ListenerUtil.mutListener.listen(25267)) {
                    properties.put("saved_megabytes", savedMegabytes);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(25273)) {
            properties.put("elapsed_time_ms", (ListenerUtil.mutListener.listen(25272) ? (endTime % mStartTimeMS) : (ListenerUtil.mutListener.listen(25271) ? (endTime / mStartTimeMS) : (ListenerUtil.mutListener.listen(25270) ? (endTime * mStartTimeMS) : (ListenerUtil.mutListener.listen(25269) ? (endTime + mStartTimeMS) : (endTime - mStartTimeMS))))));
        }
        if (!ListenerUtil.mutListener.listen(25277)) {
            if (isError) {
                if (!ListenerUtil.mutListener.listen(25274)) {
                    properties.put("exception_name", exception.getClass().getCanonicalName());
                }
                if (!ListenerUtil.mutListener.listen(25275)) {
                    properties.put("exception_message", exception.getMessage());
                }
                if (!ListenerUtil.mutListener.listen(25276)) {
                    AppLog.e(T.MEDIA, exception);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25278)) {
            properties.put("optimizer_lib", "mp4composer");
        }
        AnalyticsTracker.Stat currentStatToTrack = isError ? MEDIA_VIDEO_OPTIMIZE_ERROR : MEDIA_VIDEO_OPTIMIZED;
        if (!ListenerUtil.mutListener.listen(25279)) {
            AnalyticsTracker.track(currentStatToTrack, properties);
        }
    }

    private void putAllWithPrefix(String prefix, Map<String, Object> inputMap, Map<String, Object> targetMap) {
        if (!ListenerUtil.mutListener.listen(25283)) {
            if ((ListenerUtil.mutListener.listen(25280) ? (inputMap != null || targetMap != null) : (inputMap != null && targetMap != null))) {
                if (!ListenerUtil.mutListener.listen(25282)) {
                    {
                        long _loopCounter394 = 0;
                        for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter394", ++_loopCounter394);
                            if (!ListenerUtil.mutListener.listen(25281)) {
                                targetMap.put(prefix + entry.getKey(), entry.getValue());
                            }
                        }
                    }
                }
            }
        }
    }

    protected void selectMediaAndSendCompletionToListener() {
        long originalFileSize = FileUtils.length(mInputPath);
        long optimizedFileSize = FileUtils.length(mOutputPath);
        long savings = (ListenerUtil.mutListener.listen(25287) ? (originalFileSize % optimizedFileSize) : (ListenerUtil.mutListener.listen(25286) ? (originalFileSize / optimizedFileSize) : (ListenerUtil.mutListener.listen(25285) ? (originalFileSize * optimizedFileSize) : (ListenerUtil.mutListener.listen(25284) ? (originalFileSize + optimizedFileSize) : (originalFileSize - optimizedFileSize)))));
        double savingsKb = (ListenerUtil.mutListener.listen(25291) ? (Math.abs(savings) % 1024) : (ListenerUtil.mutListener.listen(25290) ? (Math.abs(savings) * 1024) : (ListenerUtil.mutListener.listen(25289) ? (Math.abs(savings) - 1024) : (ListenerUtil.mutListener.listen(25288) ? (Math.abs(savings) + 1024) : (Math.abs(savings) / 1024)))));
        String strSavingsKb = new DecimalFormat("0.00").format(savingsKb).concat("KB");
        if (!ListenerUtil.mutListener.listen(25303)) {
            // make sure the resulting file is smaller than the original
            if ((ListenerUtil.mutListener.listen(25296) ? (savings >= 0) : (ListenerUtil.mutListener.listen(25295) ? (savings > 0) : (ListenerUtil.mutListener.listen(25294) ? (savings < 0) : (ListenerUtil.mutListener.listen(25293) ? (savings != 0) : (ListenerUtil.mutListener.listen(25292) ? (savings == 0) : (savings <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(25301)) {
                    AppLog.w(AppLog.T.MEDIA, "VideoOptimizer > no savings, optimized file is " + strSavingsKb + " larger");
                }
                if (!ListenerUtil.mutListener.listen(25302)) {
                    // no savings, so use original unoptimized media
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25297)) {
                    AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > reduced by " + strSavingsKb);
                }
                if (!ListenerUtil.mutListener.listen(25298)) {
                    // update media object to point to optimized video
                    mMedia.setFilePath(mOutputPath);
                }
                if (!ListenerUtil.mutListener.listen(25299)) {
                    mMedia.setFileName(mFilename);
                }
                if (!ListenerUtil.mutListener.listen(25300)) {
                    mListener.onVideoOptimizationCompleted(mMedia);
                }
            }
        }
    }

    protected void sendProgressIfNeeded(float progress) {
        if (!ListenerUtil.mutListener.listen(25322)) {
            // this event fires quite often so we only call the listener when progress increases by 1% or more
            if ((ListenerUtil.mutListener.listen(25318) ? ((ListenerUtil.mutListener.listen(25308) ? (mLastProgress >= 0) : (ListenerUtil.mutListener.listen(25307) ? (mLastProgress <= 0) : (ListenerUtil.mutListener.listen(25306) ? (mLastProgress > 0) : (ListenerUtil.mutListener.listen(25305) ? (mLastProgress < 0) : (ListenerUtil.mutListener.listen(25304) ? (mLastProgress != 0) : (mLastProgress == 0)))))) && ((ListenerUtil.mutListener.listen(25317) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) >= 0.01F) : (ListenerUtil.mutListener.listen(25316) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) <= 0.01F) : (ListenerUtil.mutListener.listen(25315) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) < 0.01F) : (ListenerUtil.mutListener.listen(25314) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) != 0.01F) : (ListenerUtil.mutListener.listen(25313) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) == 0.01F) : ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) > 0.01F)))))))) : ((ListenerUtil.mutListener.listen(25308) ? (mLastProgress >= 0) : (ListenerUtil.mutListener.listen(25307) ? (mLastProgress <= 0) : (ListenerUtil.mutListener.listen(25306) ? (mLastProgress > 0) : (ListenerUtil.mutListener.listen(25305) ? (mLastProgress < 0) : (ListenerUtil.mutListener.listen(25304) ? (mLastProgress != 0) : (mLastProgress == 0)))))) || ((ListenerUtil.mutListener.listen(25317) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) >= 0.01F) : (ListenerUtil.mutListener.listen(25316) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) <= 0.01F) : (ListenerUtil.mutListener.listen(25315) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) < 0.01F) : (ListenerUtil.mutListener.listen(25314) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) != 0.01F) : (ListenerUtil.mutListener.listen(25313) ? ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) == 0.01F) : ((ListenerUtil.mutListener.listen(25312) ? (progress % mLastProgress) : (ListenerUtil.mutListener.listen(25311) ? (progress / mLastProgress) : (ListenerUtil.mutListener.listen(25310) ? (progress * mLastProgress) : (ListenerUtil.mutListener.listen(25309) ? (progress + mLastProgress) : (progress - mLastProgress))))) > 0.01F)))))))))) {
                if (!ListenerUtil.mutListener.listen(25319)) {
                    AppLog.d(AppLog.T.MEDIA, "VideoOptimizer > " + mMedia.getId() + " - progress: " + progress);
                }
                if (!ListenerUtil.mutListener.listen(25320)) {
                    mLastProgress = progress;
                }
                if (!ListenerUtil.mutListener.listen(25321)) {
                    mListener.onVideoOptimizationProgress(mMedia, progress);
                }
            }
        }
    }
}
