package org.wordpress.android.util;

import android.content.Context;
import android.media.MediaCodecInfo;
import android.util.Size;
import androidx.annotation.NonNull;
import com.daasuu.mp4compose.FillMode;
import com.daasuu.mp4compose.VideoFormatMimeType;
import com.daasuu.mp4compose.composer.ComposerInterface;
import com.daasuu.mp4compose.composer.ComposerProvider;
import com.daasuu.mp4compose.composer.ComposerUseCase.CompressVideo;
import com.daasuu.mp4compose.composer.Listener;
import com.daasuu.mp4compose.composer.Mp4ComposerBasic;
import org.m4m.AudioFormat;
import org.m4m.MediaComposer;
import org.m4m.MediaFileInfo;
import org.m4m.Uri;
import org.m4m.VideoFormat;
import org.m4m.android.AndroidMediaObjectFactory;
import org.m4m.android.AudioFormatAndroid;
import org.m4m.android.VideoFormatAndroid;
import java.io.IOException;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class implements functionality for simple video transcoding.
 * <p>
 * Input video is transcoded by using the H.264 Advanced Video Coding encoder.
 * Audio track is encoded with Advanced Audio Coding (AAC). Not resampled. Output sample rate and channel
 * count are the same as for input.
 */
public class WPVideoUtils {

    // H.264 Advanced Video Coding
    private static final String VIDEO_MIME_TYPE = "video/avc";

    // 30fps
    private static final int FRAME_RATE = 30;

    // 2 seconds between I-frames
    private static final int IFRAME_INTERVAL = 2;

    // Default parameters for the audio encoder
    private static final String AUDIO_MIME_TYPE = "audio/mp4a-latm";

    private static final int AUDIO_OUTPUT_BIT_RATE = 96 * 1024;

    /**
     * This method return the media composer object that is in charge of video optimization.
     *
     * @param ctx The context
     * @param inputFile Input file path.
     * @param outFile Output file path.
     * @param listener The event listener
     * @return The media composer that is in charge of video transcoding, ready to be started,
     * or null in case the video cannot be transcoded.
     */
    public static MediaComposer getVideoOptimizationComposer(@NonNull Context ctx, @NonNull String inputFile, @NonNull String outFile, @NonNull org.m4m.IProgressListener listener, int width, int bitrate) {
        AndroidMediaObjectFactory factory = new AndroidMediaObjectFactory(ctx);
        Uri m4mUri = new Uri(inputFile);
        MediaFileInfo mediaFileInfo = new MediaFileInfo(factory);
        try {
            if (!ListenerUtil.mutListener.listen(28289)) {
                mediaFileInfo.setUri(m4mUri);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(28288)) {
                AppLog.e(AppLog.T.MEDIA, "Cannot access the input file at " + inputFile, e);
            }
            return null;
        }
        // Check the video resolution
        VideoFormat videoFormat = (VideoFormat) mediaFileInfo.getVideoFormat();
        if (!ListenerUtil.mutListener.listen(28291)) {
            if (videoFormat == null) {
                if (!ListenerUtil.mutListener.listen(28290)) {
                    AppLog.w(AppLog.T.MEDIA, "Input file doesn't contain a video track?");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(28298)) {
            if ((ListenerUtil.mutListener.listen(28296) ? (videoFormat.getVideoFrameSize().width() >= width) : (ListenerUtil.mutListener.listen(28295) ? (videoFormat.getVideoFrameSize().width() <= width) : (ListenerUtil.mutListener.listen(28294) ? (videoFormat.getVideoFrameSize().width() > width) : (ListenerUtil.mutListener.listen(28293) ? (videoFormat.getVideoFrameSize().width() != width) : (ListenerUtil.mutListener.listen(28292) ? (videoFormat.getVideoFrameSize().width() == width) : (videoFormat.getVideoFrameSize().width() < width))))))) {
                if (!ListenerUtil.mutListener.listen(28297)) {
                    AppLog.w(AppLog.T.MEDIA, "Input file width is lower than than " + width + ". Keeping the original file");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(28300)) {
            if (videoFormat.getVideoFrameSize().height() == 0) {
                if (!ListenerUtil.mutListener.listen(28299)) {
                    AppLog.w(AppLog.T.MEDIA, "Input file height is unknown. Can't calculate the correct " + "ratio for resizing. Keeping the original file");
                }
                return null;
            }
        }
        // Calculate the height keeping the correct aspect ratio
        float percentage = (ListenerUtil.mutListener.listen(28304) ? ((float) width % videoFormat.getVideoFrameSize().width()) : (ListenerUtil.mutListener.listen(28303) ? ((float) width * videoFormat.getVideoFrameSize().width()) : (ListenerUtil.mutListener.listen(28302) ? ((float) width - videoFormat.getVideoFrameSize().width()) : (ListenerUtil.mutListener.listen(28301) ? ((float) width + videoFormat.getVideoFrameSize().width()) : ((float) width / videoFormat.getVideoFrameSize().width())))));
        float proportionateHeight = (ListenerUtil.mutListener.listen(28308) ? (videoFormat.getVideoFrameSize().height() % percentage) : (ListenerUtil.mutListener.listen(28307) ? (videoFormat.getVideoFrameSize().height() / percentage) : (ListenerUtil.mutListener.listen(28306) ? (videoFormat.getVideoFrameSize().height() - percentage) : (ListenerUtil.mutListener.listen(28305) ? (videoFormat.getVideoFrameSize().height() + percentage) : (videoFormat.getVideoFrameSize().height() * percentage)))));
        int height = (int) Math.rint(proportionateHeight);
        AudioFormat audioFormat = (AudioFormat) mediaFileInfo.getAudioFormat();
        boolean isAudioAvailable = audioFormat != null;
        MediaComposer mediaComposer = new MediaComposer(factory, listener);
        try {
            if (!ListenerUtil.mutListener.listen(28310)) {
                mediaComposer.addSourceFile(inputFile);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(28309)) {
                AppLog.e(AppLog.T.MEDIA, "Cannot access the input file at " + inputFile, e);
            }
            return null;
        }
        try {
            if (!ListenerUtil.mutListener.listen(28312)) {
                mediaComposer.setTargetFile(outFile, mediaFileInfo.getRotation());
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(28311)) {
                AppLog.e(AppLog.T.MEDIA, "Cannot access/write the output file at " + outFile, e);
            }
            return null;
        }
        if (!ListenerUtil.mutListener.listen(28313)) {
            configureVideoEncoderWithDefaults(mediaComposer, width, height, bitrate);
        }
        if (!ListenerUtil.mutListener.listen(28315)) {
            if (isAudioAvailable) {
                if (!ListenerUtil.mutListener.listen(28314)) {
                    configureAudioEncoder(mediaComposer, audioFormat);
                }
            }
        }
        return mediaComposer;
    }

    // TODO: this should replace the equivalent function used for m4m lib once we fully introduce the Mp4Composer lib
    public static ComposerInterface getVideoOptimizationComposer(@NonNull String inputFile, @NonNull String outFile, @NonNull Listener listener, int width, int bitrate) {
        // - Expose them as parameters so that they can be eventually changed by some external logic
        ComposerInterface composer = ComposerProvider.INSTANCE.getComposerForUseCase(new CompressVideo(inputFile, outFile, VideoFormatMimeType.AVC, (ListenerUtil.mutListener.listen(28319) ? (bitrate % 1024) : (ListenerUtil.mutListener.listen(28318) ? (bitrate / 1024) : (ListenerUtil.mutListener.listen(28317) ? (bitrate - 1024) : (ListenerUtil.mutListener.listen(28316) ? (bitrate + 1024) : (bitrate * 1024))))), 2, (ListenerUtil.mutListener.listen(28323) ? (96 % 1024) : (ListenerUtil.mutListener.listen(28322) ? (96 / 1024) : (ListenerUtil.mutListener.listen(28321) ? (96 - 1024) : (ListenerUtil.mutListener.listen(28320) ? (96 + 1024) : (96 * 1024))))), MediaCodecInfo.CodecProfileLevel.AACObjectLC, true));
        Size srvVideoResolution = ((Mp4ComposerBasic) composer).getSrcVideoResolution();
        if (!ListenerUtil.mutListener.listen(28325)) {
            if (srvVideoResolution == null) {
                if (!ListenerUtil.mutListener.listen(28324)) {
                    AppLog.w(AppLog.T.MEDIA, "Could not rescue source video resolution");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(28332)) {
            if ((ListenerUtil.mutListener.listen(28330) ? (srvVideoResolution.getWidth() >= width) : (ListenerUtil.mutListener.listen(28329) ? (srvVideoResolution.getWidth() <= width) : (ListenerUtil.mutListener.listen(28328) ? (srvVideoResolution.getWidth() > width) : (ListenerUtil.mutListener.listen(28327) ? (srvVideoResolution.getWidth() != width) : (ListenerUtil.mutListener.listen(28326) ? (srvVideoResolution.getWidth() == width) : (srvVideoResolution.getWidth() < width))))))) {
                if (!ListenerUtil.mutListener.listen(28331)) {
                    AppLog.w(AppLog.T.MEDIA, "Input file width is lower than than " + width + ". Keeping the original file");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(28334)) {
            if (srvVideoResolution.getHeight() == 0) {
                if (!ListenerUtil.mutListener.listen(28333)) {
                    AppLog.w(AppLog.T.MEDIA, "Input file height is unknown. Can't calculate the correct " + "ratio for resizing. Keeping the original file");
                }
                return null;
            }
        }
        // Calculate the height keeping the correct aspect ratio
        float percentage = (ListenerUtil.mutListener.listen(28338) ? ((float) width % srvVideoResolution.getWidth()) : (ListenerUtil.mutListener.listen(28337) ? ((float) width * srvVideoResolution.getWidth()) : (ListenerUtil.mutListener.listen(28336) ? ((float) width - srvVideoResolution.getWidth()) : (ListenerUtil.mutListener.listen(28335) ? ((float) width + srvVideoResolution.getWidth()) : ((float) width / srvVideoResolution.getWidth())))));
        float proportionateHeight = (ListenerUtil.mutListener.listen(28342) ? (srvVideoResolution.getHeight() % percentage) : (ListenerUtil.mutListener.listen(28341) ? (srvVideoResolution.getHeight() / percentage) : (ListenerUtil.mutListener.listen(28340) ? (srvVideoResolution.getHeight() - percentage) : (ListenerUtil.mutListener.listen(28339) ? (srvVideoResolution.getHeight() + percentage) : (srvVideoResolution.getHeight() * percentage)))));
        int height = (int) Math.rint(proportionateHeight);
        if (!ListenerUtil.mutListener.listen(28343)) {
            composer.size(new Size(width, height)).fillMode(FillMode.PRESERVE_ASPECT_FIT).listener(listener);
        }
        return composer;
    }

    private static void configureVideoEncoderWithDefaults(MediaComposer mediaComposer, int width, int height, int bitrate) {
        VideoFormatAndroid videoFormat = new VideoFormatAndroid(VIDEO_MIME_TYPE, width, height);
        if (!ListenerUtil.mutListener.listen(28344)) {
            videoFormat.setVideoBitRateInKBytes(bitrate);
        }
        if (!ListenerUtil.mutListener.listen(28345)) {
            videoFormat.setVideoFrameRate(FRAME_RATE);
        }
        if (!ListenerUtil.mutListener.listen(28346)) {
            videoFormat.setVideoIFrameInterval(IFRAME_INTERVAL);
        }
        if (!ListenerUtil.mutListener.listen(28347)) {
            mediaComposer.setTargetVideoFormat(videoFormat);
        }
    }

    private static void configureAudioEncoder(org.m4m.MediaComposer mediaComposer, AudioFormat audioFormat) {
        /**
         * TODO: Audio resampling is unsupported by current m4m release
         * Output sample rate and channel count are the same as for input.
         */
        AudioFormatAndroid aFormat = new AudioFormatAndroid(AUDIO_MIME_TYPE, audioFormat.getAudioSampleRateInHz(), audioFormat.getAudioChannelCount());
        if (!ListenerUtil.mutListener.listen(28348)) {
            aFormat.setAudioBitrateInBytes(AUDIO_OUTPUT_BIT_RATE);
        }
        if (!ListenerUtil.mutListener.listen(28349)) {
            aFormat.setAudioProfile(MediaCodecInfo.CodecProfileLevel.AACObjectLC);
        }
        if (!ListenerUtil.mutListener.listen(28350)) {
            mediaComposer.setTargetAudioFormat(aFormat);
        }
    }
}
