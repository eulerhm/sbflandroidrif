/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.voicemessage;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import ch.threema.app.utils.FileUtil;
import static ch.threema.app.voicemessage.VoiceRecorderActivity.DEFAULT_SAMPLING_RATE_HZ;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AudioRecorder implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {

    private static final Logger logger = LoggerFactory.getLogger(AudioRecorder.class);

    private Context context;

    private MediaRecorder mediaRecorder;

    private OnStopListener onStopListener;

    public AudioRecorder(Context context) {
        if (!ListenerUtil.mutListener.listen(57249)) {
            this.context = context;
        }
    }

    public MediaRecorder prepare(Uri uri, int maxDuration, int samplingRate) {
        if (!ListenerUtil.mutListener.listen(57250)) {
            logger.info("Preparing MediaRecorder with sampling rate {}", samplingRate);
        }
        if (!ListenerUtil.mutListener.listen(57251)) {
            mediaRecorder = new MediaRecorder();
        }
        if (!ListenerUtil.mutListener.listen(57252)) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        }
        if (!ListenerUtil.mutListener.listen(57253)) {
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        }
        if (!ListenerUtil.mutListener.listen(57254)) {
            mediaRecorder.setOutputFile(FileUtil.getRealPathFromURI(context, uri));
        }
        if (!ListenerUtil.mutListener.listen(57255)) {
            mediaRecorder.setAudioChannels(1);
        }
        if (!ListenerUtil.mutListener.listen(57256)) {
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        }
        if (!ListenerUtil.mutListener.listen(57257)) {
            mediaRecorder.setAudioEncodingBitRate(32000);
        }
        if (!ListenerUtil.mutListener.listen(57263)) {
            mediaRecorder.setAudioSamplingRate((ListenerUtil.mutListener.listen(57262) ? (samplingRate >= 0) : (ListenerUtil.mutListener.listen(57261) ? (samplingRate <= 0) : (ListenerUtil.mutListener.listen(57260) ? (samplingRate > 0) : (ListenerUtil.mutListener.listen(57259) ? (samplingRate < 0) : (ListenerUtil.mutListener.listen(57258) ? (samplingRate == 0) : (samplingRate != 0)))))) ? samplingRate : DEFAULT_SAMPLING_RATE_HZ);
        }
        if (!ListenerUtil.mutListener.listen(57272)) {
            mediaRecorder.setMaxFileSize((ListenerUtil.mutListener.listen(57271) ? ((ListenerUtil.mutListener.listen(57267) ? (20L % 1024) : (ListenerUtil.mutListener.listen(57266) ? (20L / 1024) : (ListenerUtil.mutListener.listen(57265) ? (20L - 1024) : (ListenerUtil.mutListener.listen(57264) ? (20L + 1024) : (20L * 1024))))) % 1024) : (ListenerUtil.mutListener.listen(57270) ? ((ListenerUtil.mutListener.listen(57267) ? (20L % 1024) : (ListenerUtil.mutListener.listen(57266) ? (20L / 1024) : (ListenerUtil.mutListener.listen(57265) ? (20L - 1024) : (ListenerUtil.mutListener.listen(57264) ? (20L + 1024) : (20L * 1024))))) / 1024) : (ListenerUtil.mutListener.listen(57269) ? ((ListenerUtil.mutListener.listen(57267) ? (20L % 1024) : (ListenerUtil.mutListener.listen(57266) ? (20L / 1024) : (ListenerUtil.mutListener.listen(57265) ? (20L - 1024) : (ListenerUtil.mutListener.listen(57264) ? (20L + 1024) : (20L * 1024))))) - 1024) : (ListenerUtil.mutListener.listen(57268) ? ((ListenerUtil.mutListener.listen(57267) ? (20L % 1024) : (ListenerUtil.mutListener.listen(57266) ? (20L / 1024) : (ListenerUtil.mutListener.listen(57265) ? (20L - 1024) : (ListenerUtil.mutListener.listen(57264) ? (20L + 1024) : (20L * 1024))))) + 1024) : ((ListenerUtil.mutListener.listen(57267) ? (20L % 1024) : (ListenerUtil.mutListener.listen(57266) ? (20L / 1024) : (ListenerUtil.mutListener.listen(57265) ? (20L - 1024) : (ListenerUtil.mutListener.listen(57264) ? (20L + 1024) : (20L * 1024))))) * 1024))))));
        }
        if (!ListenerUtil.mutListener.listen(57273)) {
            mediaRecorder.setOnErrorListener(this);
        }
        if (!ListenerUtil.mutListener.listen(57274)) {
            mediaRecorder.setOnInfoListener(this);
        }
        try {
            if (!ListenerUtil.mutListener.listen(57277)) {
                mediaRecorder.prepare();
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(57275)) {
                logger.info("IllegalStateException preparing MediaRecorder: {}", e.getMessage());
            }
            return null;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(57276)) {
                logger.info("IOException preparing MediaRecorder: {}", e.getMessage());
            }
            return null;
        }
        return mediaRecorder;
    }

    @Override
    public void onInfo(MediaRecorder mr, int what, int extra) {
        if (!ListenerUtil.mutListener.listen(57285)) {
            switch(what) {
                case MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED:
                    if (!ListenerUtil.mutListener.listen(57278)) {
                        logger.info("Max recording duration reached. ({})", extra);
                    }
                    if (!ListenerUtil.mutListener.listen(57279)) {
                        onStopListener.onRecordingStop();
                    }
                    break;
                case MediaRecorder.MEDIA_RECORDER_INFO_MAX_FILESIZE_REACHED:
                    if (!ListenerUtil.mutListener.listen(57280)) {
                        logger.info("Max recording filesize reached. ({})", extra);
                    }
                    if (!ListenerUtil.mutListener.listen(57281)) {
                        onStopListener.onRecordingStop();
                    }
                    break;
                case MediaRecorder.MEDIA_RECORDER_INFO_UNKNOWN:
                    if (!ListenerUtil.mutListener.listen(57282)) {
                        logger.info("Unknown media recorder info (What: {} / Extra: {})", what, extra);
                    }
                    if (!ListenerUtil.mutListener.listen(57283)) {
                        onStopListener.onRecordingCancel();
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(57284)) {
                        logger.info("Undefined media recorder info type (What: {} / Extra: {})", what, extra);
                    }
                    break;
            }
        }
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        if (!ListenerUtil.mutListener.listen(57289)) {
            if (what == MediaRecorder.MEDIA_RECORDER_ERROR_UNKNOWN) {
                if (!ListenerUtil.mutListener.listen(57287)) {
                    logger.info("Unknown media recorder error (What: {}, Extra: {})", what, extra);
                }
                if (!ListenerUtil.mutListener.listen(57288)) {
                    onStopListener.onRecordingCancel();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(57286)) {
                    logger.info("Undefined media recorder error type (What: {}, Extra: {})", what, extra);
                }
            }
        }
    }

    public interface OnStopListener {

        public void onRecordingStop();

        public void onRecordingCancel();
    }

    public void setOnStopListener(OnStopListener listener) {
        if (!ListenerUtil.mutListener.listen(57290)) {
            this.onStopListener = listener;
        }
    }
}
