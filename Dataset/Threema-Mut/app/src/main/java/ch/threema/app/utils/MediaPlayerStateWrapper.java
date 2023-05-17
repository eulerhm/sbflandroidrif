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
package ch.threema.app.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.EnumSet;
import androidx.annotation.RequiresApi;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A wrapper class for {@link android.media.MediaPlayer}.
 * <p>
 * Encapsulates an instance of MediaPlayer, and makes a record of its internal state accessible via a
 * {@link #getState()} accessor.
 * </p>
 */
public class MediaPlayerStateWrapper {

    private static final Logger logger = LoggerFactory.getLogger(MediaPlayerStateWrapper.class);

    private MediaPlayer mediaPlayer;

    private State currentState;

    private MediaPlayerStateWrapper stateWrapper;

    private StateListener stateListener;

    public MediaPlayerStateWrapper() {
        if (!ListenerUtil.mutListener.listen(54737)) {
            stateWrapper = this;
        }
        if (!ListenerUtil.mutListener.listen(54738)) {
            mediaPlayer = new MediaPlayer();
        }
        if (!ListenerUtil.mutListener.listen(54739)) {
            stateListener = null;
        }
        if (!ListenerUtil.mutListener.listen(54740)) {
            currentState = State.IDLE;
        }
        if (!ListenerUtil.mutListener.listen(54741)) {
            mediaPlayer.setOnPreparedListener(onPreparedListener);
        }
        if (!ListenerUtil.mutListener.listen(54742)) {
            mediaPlayer.setOnCompletionListener(onCompletionListener);
        }
        if (!ListenerUtil.mutListener.listen(54743)) {
            mediaPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
        }
        if (!ListenerUtil.mutListener.listen(54744)) {
            mediaPlayer.setOnErrorListener(onErrorListener);
        }
        if (!ListenerUtil.mutListener.listen(54745)) {
            mediaPlayer.setOnInfoListener(onInfoListener);
        }
    }

    public enum State {

        IDLE,
        ERROR,
        INITIALIZED,
        PREPARING,
        PREPARED,
        STARTED,
        STOPPED,
        PLAYBACK_COMPLETE,
        PAUSED
    }

    public void setDataSource(Context context, Uri uri) {
        if (!ListenerUtil.mutListener.listen(54749)) {
            if (currentState == State.IDLE) {
                try {
                    if (!ListenerUtil.mutListener.listen(54747)) {
                        mediaPlayer.setDataSource(context, uri);
                    }
                    if (!ListenerUtil.mutListener.listen(54748)) {
                        currentState = State.INITIALIZED;
                    }
                } catch (IllegalArgumentException | IllegalStateException | IOException e) {
                    if (!ListenerUtil.mutListener.listen(54746)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    public void setDataSource(AssetFileDescriptor afd) {
        if (!ListenerUtil.mutListener.listen(54753)) {
            if (currentState == State.IDLE) {
                try {
                    if (!ListenerUtil.mutListener.listen(54751)) {
                        mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
                    }
                    if (!ListenerUtil.mutListener.listen(54752)) {
                        currentState = State.INITIALIZED;
                    }
                } catch (IllegalArgumentException | IllegalStateException | IOException e) {
                    if (!ListenerUtil.mutListener.listen(54750)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    public void prepareAsync() {
        if (!ListenerUtil.mutListener.listen(54754)) {
            logger.debug("prepareAsync()");
        }
        if (!ListenerUtil.mutListener.listen(54757)) {
            if (EnumSet.of(State.INITIALIZED, State.STOPPED).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54755)) {
                    mediaPlayer.prepareAsync();
                }
                if (!ListenerUtil.mutListener.listen(54756)) {
                    currentState = State.PREPARING;
                }
            }
        }
    }

    public void prepare() throws IOException, IllegalStateException {
        if (!ListenerUtil.mutListener.listen(54758)) {
            logger.debug("prepare()");
        }
        if (!ListenerUtil.mutListener.listen(54762)) {
            if (EnumSet.of(State.INITIALIZED, State.STOPPED).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54759)) {
                    currentState = State.PREPARING;
                }
                if (!ListenerUtil.mutListener.listen(54760)) {
                    mediaPlayer.prepare();
                }
                if (!ListenerUtil.mutListener.listen(54761)) {
                    currentState = State.PREPARED;
                }
            }
        }
    }

    public boolean isPlaying() {
        if (!ListenerUtil.mutListener.listen(54763)) {
            logger.debug("isPlaying()");
        }
        if (!ListenerUtil.mutListener.listen(54764)) {
            if (currentState != State.ERROR) {
                return mediaPlayer.isPlaying();
            }
        }
        return false;
    }

    public void seekTo(int msec) {
        if (!ListenerUtil.mutListener.listen(54765)) {
            logger.debug("seekTo()");
        }
        if (!ListenerUtil.mutListener.listen(54767)) {
            if (EnumSet.of(State.PREPARED, State.STARTED, State.PAUSED, State.PLAYBACK_COMPLETE).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54766)) {
                    mediaPlayer.seekTo(msec);
                }
            }
        }
    }

    public void pause() {
        if (!ListenerUtil.mutListener.listen(54768)) {
            logger.debug("pause()");
        }
        if (!ListenerUtil.mutListener.listen(54771)) {
            if (EnumSet.of(State.STARTED, State.PAUSED).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54769)) {
                    mediaPlayer.pause();
                }
                if (!ListenerUtil.mutListener.listen(54770)) {
                    currentState = State.PAUSED;
                }
            }
        }
    }

    public void start() {
        if (!ListenerUtil.mutListener.listen(54772)) {
            logger.debug("start()");
        }
        if (!ListenerUtil.mutListener.listen(54775)) {
            if (EnumSet.of(State.PREPARED, State.STARTED, State.PAUSED, State.PLAYBACK_COMPLETE).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54773)) {
                    mediaPlayer.start();
                }
                if (!ListenerUtil.mutListener.listen(54774)) {
                    currentState = State.STARTED;
                }
            }
        }
    }

    public void stop() {
        if (!ListenerUtil.mutListener.listen(54776)) {
            logger.debug("stop()");
        }
        if (!ListenerUtil.mutListener.listen(54779)) {
            if (EnumSet.of(State.PREPARED, State.STARTED, State.STOPPED, State.PAUSED, State.PLAYBACK_COMPLETE).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54777)) {
                    mediaPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(54778)) {
                    currentState = State.STOPPED;
                }
            }
        }
    }

    public void reset() {
        if (!ListenerUtil.mutListener.listen(54780)) {
            // Idle, Initialized, Prepared, Started, Paused, Stopped, PlaybackCompleted, Error
            logger.debug("reset()");
        }
        if (!ListenerUtil.mutListener.listen(54783)) {
            if (EnumSet.of(State.PREPARED, State.STARTED, State.STOPPED, State.PAUSED, State.PLAYBACK_COMPLETE, State.IDLE, State.INITIALIZED).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54781)) {
                    mediaPlayer.reset();
                }
                if (!ListenerUtil.mutListener.listen(54782)) {
                    currentState = State.IDLE;
                }
            }
        }
    }

    /**
     *  @return The current state of the mediaplayer state machine.
     */
    public State getState() {
        if (!ListenerUtil.mutListener.listen(54784)) {
            logger.debug("getState()");
        }
        return currentState;
    }

    public void release() {
        if (!ListenerUtil.mutListener.listen(54785)) {
            logger.debug("release()");
        }
        if (!ListenerUtil.mutListener.listen(54786)) {
            mediaPlayer.release();
        }
    }

    /* INTERNAL LISTENERS */
    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {

        @Override
        public void onPrepared(MediaPlayer mp) {
            if (!ListenerUtil.mutListener.listen(54787)) {
                logger.debug("on prepared");
            }
            if (!ListenerUtil.mutListener.listen(54791)) {
                if (EnumSet.of(State.PREPARING).contains(currentState)) {
                    if (!ListenerUtil.mutListener.listen(54788)) {
                        currentState = State.PREPARED;
                    }
                    if (!ListenerUtil.mutListener.listen(54790)) {
                        if (stateListener != null) {
                            if (!ListenerUtil.mutListener.listen(54789)) {
                                stateListener.onPrepared(mp);
                            }
                        }
                    }
                }
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            if (!ListenerUtil.mutListener.listen(54792)) {
                logger.debug("on completion");
            }
            if (!ListenerUtil.mutListener.listen(54793)) {
                currentState = State.PLAYBACK_COMPLETE;
            }
            if (!ListenerUtil.mutListener.listen(54795)) {
                if (stateListener != null) {
                    if (!ListenerUtil.mutListener.listen(54794)) {
                        stateListener.onCompletion(mp);
                    }
                }
            }
        }
    };

    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {

        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            if (!ListenerUtil.mutListener.listen(54796)) {
                logger.debug("on buffering update");
            }
            if (!ListenerUtil.mutListener.listen(54797)) {
                stateWrapper.onBufferingUpdate(mp, percent);
            }
        }
    };

    private MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            if (!ListenerUtil.mutListener.listen(54798)) {
                logger.debug("on error");
            }
            if (!ListenerUtil.mutListener.listen(54799)) {
                currentState = State.ERROR;
            }
            if (!ListenerUtil.mutListener.listen(54800)) {
                stateWrapper.onError(mp, what, extra);
            }
            return false;
        }
    };

    private MediaPlayer.OnInfoListener onInfoListener = new MediaPlayer.OnInfoListener() {

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (!ListenerUtil.mutListener.listen(54801)) {
                logger.debug("on info");
            }
            if (!ListenerUtil.mutListener.listen(54802)) {
                stateWrapper.onInfo(mp, what, extra);
            }
            return false;
        }
    };

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
    }

    boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    public interface StateListener {

        void onCompletion(MediaPlayer mp);

        void onPrepared(MediaPlayer mp);
    }

    public void setStateListener(StateListener listener) {
        if (!ListenerUtil.mutListener.listen(54803)) {
            stateListener = listener;
        }
    }

    /* OTHER STUFF */
    public int getCurrentPosition() {
        if (currentState != State.ERROR) {
            return mediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    public int getDuration() {
        // Prepared, Started, Paused, Stopped, PlaybackCompleted
        if (EnumSet.of(State.PREPARED, State.STARTED, State.PAUSED, State.STOPPED, State.PLAYBACK_COMPLETE).contains(currentState)) {
            return mediaPlayer.getDuration();
        } else {
            return 100;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setAudioAttributes(AudioAttributes attributes) {
        if (!ListenerUtil.mutListener.listen(54805)) {
            // Idle, Initialized, Stopped, Prepared, Started, Paused, PlaybackCompleted
            if (EnumSet.of(State.IDLE, State.INITIALIZED, State.STOPPED, State.PREPARED, State.STARTED, State.PAUSED, State.PLAYBACK_COMPLETE).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54804)) {
                    mediaPlayer.setAudioAttributes(attributes);
                }
            }
        }
    }

    public void setAudioStreamType(int streamType) {
        if (!ListenerUtil.mutListener.listen(54807)) {
            // Idle, Initialized, Stopped, Prepared, Started, Paused, PlaybackCompleted
            if (EnumSet.of(State.IDLE, State.INITIALIZED, State.STOPPED, State.PREPARED, State.STARTED, State.PAUSED, State.PLAYBACK_COMPLETE).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54806)) {
                    mediaPlayer.setAudioStreamType(streamType);
                }
            }
        }
    }

    public void setVolume(float leftVolume, float rightVolume) {
        if (!ListenerUtil.mutListener.listen(54809)) {
            // Idle, Initialized, Stopped, Prepared, Started, Paused, PlaybackCompleted
            if (EnumSet.of(State.IDLE, State.INITIALIZED, State.STOPPED, State.PREPARED, State.STARTED, State.PAUSED, State.PLAYBACK_COMPLETE).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54808)) {
                    mediaPlayer.setVolume(leftVolume, rightVolume);
                }
            }
        }
    }

    public void setLooping(boolean looping) {
        if (!ListenerUtil.mutListener.listen(54811)) {
            if (EnumSet.of(State.IDLE, State.INITIALIZED, State.STOPPED, State.PREPARED, State.STARTED, State.PAUSED, State.PLAYBACK_COMPLETE).contains(currentState)) {
                if (!ListenerUtil.mutListener.listen(54810)) {
                    mediaPlayer.setLooping(looping);
                }
            }
        }
    }

    public void setScreenOnWhilePlaying(boolean screenOn) {
        if (!ListenerUtil.mutListener.listen(54812)) {
            mediaPlayer.setScreenOnWhilePlaying(screenOn);
        }
    }

    /**
     *  Set playback parameters of MediaPlayer instance
     *  @param playbackParams PlaybackParams to set
     *  @return true if setting parameters was successful, false if MediaPlayer was in an invalid state or setting PlaybackParams failed.
     */
    @RequiresApi(Build.VERSION_CODES.M)
    public boolean setPlaybackParams(PlaybackParams playbackParams) {
        if (!ListenerUtil.mutListener.listen(54815)) {
            // will not work with states Idle or Stopped
            if (EnumSet.of(State.INITIALIZED, State.PREPARED, State.STARTED, State.PAUSED, State.PLAYBACK_COMPLETE, State.ERROR).contains(currentState)) {
                try {
                    if (!ListenerUtil.mutListener.listen(54814)) {
                        mediaPlayer.setPlaybackParams(playbackParams);
                    }
                    return true;
                } catch (IllegalArgumentException e) {
                    if (!ListenerUtil.mutListener.listen(54813)) {
                        logger.info("Unable to set playback params {}", e.getMessage());
                    }
                }
            }
        }
        return false;
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public PlaybackParams getPlaybackParams() {
        return mediaPlayer.getPlaybackParams();
    }
}
