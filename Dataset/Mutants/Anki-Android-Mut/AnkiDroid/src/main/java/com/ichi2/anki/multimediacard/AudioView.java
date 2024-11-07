/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.multimediacard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import java.io.File;
import java.io.IOException;
import com.ichi2.utils.Permissions;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Not designed for visual editing
@SuppressLint("ViewConstructor")
public class AudioView extends LinearLayout {

    protected final String mAudioPath;

    protected PlayPauseButton mPlayPause = null;

    protected StopButton mStop = null;

    protected RecordButton mRecord = null;

    private MediaRecorder mRecorder = null;

    private MediaPlayer mPlayer = null;

    private OnRecordingFinishEventListener mOnRecordingFinishEventListener = null;

    private Status mStatus = Status.IDLE;

    private final int mResPlayImage;

    private final int mResPauseImage;

    private final int mResStopImage;

    private int mResRecordImage;

    private int mResRecordStopImage;

    private final Context mContext;

    enum Status {

        // Default initial state
        IDLE,
        // When datasource has been set
        INITIALIZED,
        // The different possible states once playing
        PLAYING,
        // The different possible states once playing
        PAUSED,
        // The different possible states once playing
        STOPPED,
        // The recorder being played status
        RECORDING
    }

    public static AudioView createRecorderInstance(Context context, int resPlay, int resPause, int resStop, int resRecord, int resRecordStop, String audioPath) {
        try {
            return new AudioView(context, resPlay, resPause, resStop, resRecord, resRecordStop, audioPath);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2061)) {
                Timber.e(e);
            }
            if (!ListenerUtil.mutListener.listen(2062)) {
                AnkiDroidApp.sendExceptionReport(e, "Unable to create recorder tool bar");
            }
            if (!ListenerUtil.mutListener.listen(2063)) {
                UIUtils.showThemedToast(context, context.getText(R.string.multimedia_editor_audio_view_create_failed).toString(), true);
            }
            return null;
        }
    }

    @Nullable
    public static String generateTempAudioFile(@NonNull Context context) {
        String tempAudioPath;
        try {
            File storingDirectory = context.getCacheDir();
            tempAudioPath = File.createTempFile("ankidroid_audiorec", ".3gp", storingDirectory).getAbsolutePath();
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(2064)) {
                Timber.e(e, "Could not create temporary audio file.");
            }
            tempAudioPath = null;
        }
        return tempAudioPath;
    }

    private AudioView(Context context, int resPlay, int resPause, int resStop, String audioPath) {
        super(context);
        mContext = context;
        mResPlayImage = resPlay;
        mResPauseImage = resPause;
        mResStopImage = resStop;
        mAudioPath = audioPath;
        if (!ListenerUtil.mutListener.listen(2065)) {
            this.setOrientation(HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(2066)) {
            mPlayPause = new PlayPauseButton(context);
        }
        if (!ListenerUtil.mutListener.listen(2067)) {
            addView(mPlayPause, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        if (!ListenerUtil.mutListener.listen(2068)) {
            mStop = new StopButton(context);
        }
        if (!ListenerUtil.mutListener.listen(2069)) {
            addView(mStop, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    }

    private String gtxt(int id) {
        return mContext.getText(id).toString();
    }

    private AudioView(Context context, int resPlay, int resPause, int resStop, int resRecord, int resRecordStop, String audioPath) {
        this(context, resPlay, resPause, resStop, audioPath);
        if (!ListenerUtil.mutListener.listen(2070)) {
            mResRecordImage = resRecord;
        }
        if (!ListenerUtil.mutListener.listen(2071)) {
            mResRecordStopImage = resRecordStop;
        }
        if (!ListenerUtil.mutListener.listen(2072)) {
            this.setOrientation(HORIZONTAL);
        }
        if (!ListenerUtil.mutListener.listen(2073)) {
            this.setGravity(Gravity.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(2074)) {
            mRecord = new RecordButton(context);
        }
        if (!ListenerUtil.mutListener.listen(2075)) {
            addView(mRecord, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    }

    public String getAudioPath() {
        return mAudioPath;
    }

    public void setOnRecordingFinishEventListener(OnRecordingFinishEventListener listener) {
        if (!ListenerUtil.mutListener.listen(2076)) {
            mOnRecordingFinishEventListener = listener;
        }
    }

    public void notifyPlay() {
        if (!ListenerUtil.mutListener.listen(2077)) {
            mPlayPause.update();
        }
        if (!ListenerUtil.mutListener.listen(2078)) {
            mStop.update();
        }
        if (!ListenerUtil.mutListener.listen(2080)) {
            if (mRecord != null) {
                if (!ListenerUtil.mutListener.listen(2079)) {
                    mRecord.update();
                }
            }
        }
    }

    public void notifyStop() {
        if (!ListenerUtil.mutListener.listen(2081)) {
            // Send state change signal to all buttons
            mPlayPause.update();
        }
        if (!ListenerUtil.mutListener.listen(2082)) {
            mStop.update();
        }
        if (!ListenerUtil.mutListener.listen(2084)) {
            if (mRecord != null) {
                if (!ListenerUtil.mutListener.listen(2083)) {
                    mRecord.update();
                }
            }
        }
    }

    public void notifyPause() {
        if (!ListenerUtil.mutListener.listen(2085)) {
            mPlayPause.update();
        }
        if (!ListenerUtil.mutListener.listen(2086)) {
            mStop.update();
        }
        if (!ListenerUtil.mutListener.listen(2088)) {
            if (mRecord != null) {
                if (!ListenerUtil.mutListener.listen(2087)) {
                    mRecord.update();
                }
            }
        }
    }

    public void notifyRecord() {
        if (!ListenerUtil.mutListener.listen(2089)) {
            mPlayPause.update();
        }
        if (!ListenerUtil.mutListener.listen(2090)) {
            mStop.update();
        }
        if (!ListenerUtil.mutListener.listen(2092)) {
            if (mRecord != null) {
                if (!ListenerUtil.mutListener.listen(2091)) {
                    mRecord.update();
                }
            }
        }
    }

    public void notifyStopRecord() {
        if (!ListenerUtil.mutListener.listen(2100)) {
            if ((ListenerUtil.mutListener.listen(2093) ? (mRecorder != null || mStatus == Status.RECORDING) : (mRecorder != null && mStatus == Status.RECORDING))) {
                try {
                    if (!ListenerUtil.mutListener.listen(2096)) {
                        mRecorder.stop();
                    }
                } catch (RuntimeException e) {
                    if (!ListenerUtil.mutListener.listen(2094)) {
                        Timber.i(e, "Recording stop failed, this happens if stop was hit immediately after start");
                    }
                    if (!ListenerUtil.mutListener.listen(2095)) {
                        UIUtils.showThemedToast(mContext, gtxt(R.string.multimedia_editor_audio_view_recording_failed), true);
                    }
                }
                if (!ListenerUtil.mutListener.listen(2097)) {
                    mStatus = Status.IDLE;
                }
                if (!ListenerUtil.mutListener.listen(2099)) {
                    if (mOnRecordingFinishEventListener != null) {
                        if (!ListenerUtil.mutListener.listen(2098)) {
                            mOnRecordingFinishEventListener.onRecordingFinish(AudioView.this);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2101)) {
            mPlayPause.update();
        }
        if (!ListenerUtil.mutListener.listen(2102)) {
            mStop.update();
        }
        if (!ListenerUtil.mutListener.listen(2104)) {
            if (mRecord != null) {
                if (!ListenerUtil.mutListener.listen(2103)) {
                    mRecord.update();
                }
            }
        }
    }

    public void notifyReleaseRecorder() {
        if (!ListenerUtil.mutListener.listen(2106)) {
            if (mRecorder != null) {
                if (!ListenerUtil.mutListener.listen(2105)) {
                    mRecorder.release();
                }
            }
        }
    }

    public void toggleRecord() {
        if (!ListenerUtil.mutListener.listen(2108)) {
            if (mRecord != null) {
                if (!ListenerUtil.mutListener.listen(2107)) {
                    mRecord.callOnClick();
                }
            }
        }
    }

    public void togglePlay() {
        if (!ListenerUtil.mutListener.listen(2110)) {
            if (mPlayPause != null) {
                if (!ListenerUtil.mutListener.listen(2109)) {
                    mPlayPause.callOnClick();
                }
            }
        }
    }

    protected class PlayPauseButton extends AppCompatImageButton {

        private final OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!ListenerUtil.mutListener.listen(2111)) {
                    if (mAudioPath == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(2138)) {
                    switch(mStatus) {
                        case IDLE:
                            try {
                                if (!ListenerUtil.mutListener.listen(2115)) {
                                    mPlayer = new MediaPlayer();
                                }
                                if (!ListenerUtil.mutListener.listen(2116)) {
                                    mPlayer.setDataSource(getAudioPath());
                                }
                                if (!ListenerUtil.mutListener.listen(2117)) {
                                    mPlayer.setOnCompletionListener(mp -> {
                                        mStatus = Status.STOPPED;
                                        mPlayer.stop();
                                        notifyStop();
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(2118)) {
                                    mPlayer.prepare();
                                }
                                if (!ListenerUtil.mutListener.listen(2119)) {
                                    mPlayer.start();
                                }
                                if (!ListenerUtil.mutListener.listen(2120)) {
                                    setImageResource(mResPauseImage);
                                }
                                if (!ListenerUtil.mutListener.listen(2121)) {
                                    mStatus = Status.PLAYING;
                                }
                                if (!ListenerUtil.mutListener.listen(2122)) {
                                    notifyPlay();
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(2112)) {
                                    Timber.e(e);
                                }
                                if (!ListenerUtil.mutListener.listen(2113)) {
                                    UIUtils.showThemedToast(mContext, gtxt(R.string.multimedia_editor_audio_view_playing_failed), true);
                                }
                                if (!ListenerUtil.mutListener.listen(2114)) {
                                    mStatus = Status.IDLE;
                                }
                            }
                            break;
                        case PAUSED:
                            if (!ListenerUtil.mutListener.listen(2123)) {
                                // -> Play, continue playing
                                mStatus = Status.PLAYING;
                            }
                            if (!ListenerUtil.mutListener.listen(2124)) {
                                setImageResource(mResPauseImage);
                            }
                            if (!ListenerUtil.mutListener.listen(2125)) {
                                mPlayer.start();
                            }
                            if (!ListenerUtil.mutListener.listen(2126)) {
                                notifyPlay();
                            }
                            break;
                        case STOPPED:
                            if (!ListenerUtil.mutListener.listen(2127)) {
                                // -> Play, start from beginning
                                mStatus = Status.PLAYING;
                            }
                            if (!ListenerUtil.mutListener.listen(2128)) {
                                setImageResource(mResPauseImage);
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(2130)) {
                                    mPlayer.prepare();
                                }
                                if (!ListenerUtil.mutListener.listen(2131)) {
                                    mPlayer.seekTo(0);
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(2129)) {
                                    Timber.e(e);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2132)) {
                                mPlayer.start();
                            }
                            if (!ListenerUtil.mutListener.listen(2133)) {
                                notifyPlay();
                            }
                            break;
                        case PLAYING:
                            if (!ListenerUtil.mutListener.listen(2134)) {
                                setImageResource(mResPlayImage);
                            }
                            if (!ListenerUtil.mutListener.listen(2135)) {
                                mPlayer.pause();
                            }
                            if (!ListenerUtil.mutListener.listen(2136)) {
                                mStatus = Status.PAUSED;
                            }
                            if (!ListenerUtil.mutListener.listen(2137)) {
                                notifyPause();
                            }
                            break;
                        case RECORDING:
                            // this button should be disabled
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        public PlayPauseButton(Context context) {
            super(context);
            if (!ListenerUtil.mutListener.listen(2139)) {
                setImageResource(mResPlayImage);
            }
            if (!ListenerUtil.mutListener.listen(2140)) {
                setOnClickListener(onClickListener);
            }
        }

        public void update() {
            if (!ListenerUtil.mutListener.listen(2145)) {
                switch(mStatus) {
                    case IDLE:
                    case STOPPED:
                        if (!ListenerUtil.mutListener.listen(2141)) {
                            setImageResource(mResPlayImage);
                        }
                        if (!ListenerUtil.mutListener.listen(2142)) {
                            setEnabled(true);
                        }
                        break;
                    case RECORDING:
                        if (!ListenerUtil.mutListener.listen(2143)) {
                            setEnabled(false);
                        }
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(2144)) {
                            setEnabled(true);
                        }
                        break;
                }
            }
        }
    }

    protected class StopButton extends AppCompatImageButton {

        private final OnClickListener onClickListener = v -> {
            switch(mStatus) {
                case PAUSED:
                case PLAYING:
                    mPlayer.stop();
                    mStatus = Status.STOPPED;
                    notifyStop();
                    break;
                case IDLE:
                case STOPPED:
                case RECORDING:
                case INITIALIZED:
                default:
                    break;
            }
        };

        public StopButton(Context context) {
            super(context);
            if (!ListenerUtil.mutListener.listen(2146)) {
                setImageResource(mResStopImage);
            }
            if (!ListenerUtil.mutListener.listen(2147)) {
                setOnClickListener(onClickListener);
            }
        }

        public void update() {
            if (!ListenerUtil.mutListener.listen(2148)) {
                setEnabled(mStatus != Status.RECORDING);
            }
        }
    }

    protected class RecordButton extends AppCompatImageButton {

        private final OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!ListenerUtil.mutListener.listen(2149)) {
                    // Since mAudioPath is not compulsory, we check if it exists
                    if (mAudioPath == null) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(2152)) {
                    // We can get to this screen without permissions through the "Pronunciation" feature.
                    if (!Permissions.canRecordAudio(mContext)) {
                        if (!ListenerUtil.mutListener.listen(2150)) {
                            Timber.w("Audio recording permission denied.");
                        }
                        if (!ListenerUtil.mutListener.listen(2151)) {
                            UIUtils.showThemedToast(mContext, getResources().getString(R.string.multimedia_editor_audio_permission_denied), true);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(2174)) {
                    switch(mStatus) {
                        // If not already recorded or not already played
                        case IDLE:
                        case // if already recorded or played
                        STOPPED:
                            boolean highSampling = false;
                            try {
                                if (!ListenerUtil.mutListener.listen(2153)) {
                                    // can throw IllegalArgumentException if codec isn't supported
                                    mRecorder = initMediaRecorder();
                                }
                                if (!ListenerUtil.mutListener.listen(2154)) {
                                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                                }
                                if (!ListenerUtil.mutListener.listen(2155)) {
                                    mRecorder.setAudioChannels(2);
                                }
                                if (!ListenerUtil.mutListener.listen(2156)) {
                                    mRecorder.setAudioSamplingRate(44100);
                                }
                                if (!ListenerUtil.mutListener.listen(2157)) {
                                    mRecorder.setAudioEncodingBitRate(192000);
                                }
                                if (!ListenerUtil.mutListener.listen(2158)) {
                                    // this can also throw IOException if output path is invalid
                                    mRecorder.prepare();
                                }
                                if (!ListenerUtil.mutListener.listen(2159)) {
                                    mRecorder.start();
                                }
                                if (!ListenerUtil.mutListener.listen(2160)) {
                                    highSampling = true;
                                }
                            } catch (Exception e) {
                            }
                            if (!ListenerUtil.mutListener.listen(2168)) {
                                if (!highSampling) {
                                    // fall back on default
                                    try {
                                        if (!ListenerUtil.mutListener.listen(2164)) {
                                            mRecorder = initMediaRecorder();
                                        }
                                        if (!ListenerUtil.mutListener.listen(2165)) {
                                            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2166)) {
                                            mRecorder.prepare();
                                        }
                                        if (!ListenerUtil.mutListener.listen(2167)) {
                                            mRecorder.start();
                                        }
                                    } catch (Exception e) {
                                        if (!ListenerUtil.mutListener.listen(2161)) {
                                            // either output file failed or codec didn't work, in any case fail out
                                            Timber.e("RecordButton.onClick() :: error recording to %s\n%s", mAudioPath, e.getMessage());
                                        }
                                        if (!ListenerUtil.mutListener.listen(2162)) {
                                            UIUtils.showThemedToast(mContext, gtxt(R.string.multimedia_editor_audio_view_recording_failed), true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(2163)) {
                                            mStatus = Status.STOPPED;
                                        }
                                        break;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2169)) {
                                mStatus = Status.RECORDING;
                            }
                            if (!ListenerUtil.mutListener.listen(2170)) {
                                setImageResource(mResRecordImage);
                            }
                            if (!ListenerUtil.mutListener.listen(2171)) {
                                notifyRecord();
                            }
                            break;
                        case RECORDING:
                            if (!ListenerUtil.mutListener.listen(2172)) {
                                setImageResource(mResRecordStopImage);
                            }
                            if (!ListenerUtil.mutListener.listen(2173)) {
                                notifyStopRecord();
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

            private MediaRecorder initMediaRecorder() {
                MediaRecorder mr = new MediaRecorder();
                if (!ListenerUtil.mutListener.listen(2175)) {
                    mr.setAudioSource(MediaRecorder.AudioSource.MIC);
                }
                if (!ListenerUtil.mutListener.listen(2176)) {
                    mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                }
                if (!ListenerUtil.mutListener.listen(2177)) {
                    mStatus = Status.INITIALIZED;
                }
                if (!ListenerUtil.mutListener.listen(2178)) {
                    // audioPath
                    mr.setOutputFile(mAudioPath);
                }
                // change
                return mr;
            }
        };

        public RecordButton(Context context) {
            super(context);
            if (!ListenerUtil.mutListener.listen(2179)) {
                setImageResource(mResRecordStopImage);
            }
            if (!ListenerUtil.mutListener.listen(2180)) {
                setOnClickListener(onClickListener);
            }
        }

        public void update() {
            if (!ListenerUtil.mutListener.listen(2183)) {
                switch(mStatus) {
                    case PLAYING:
                    case PAUSED:
                        if (!ListenerUtil.mutListener.listen(2181)) {
                            setEnabled(false);
                        }
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(2182)) {
                            setEnabled(true);
                        }
                        break;
                }
            }
        }
    }

    public interface OnRecordingFinishEventListener {

        void onRecordingFinish(View v);
    }
}
