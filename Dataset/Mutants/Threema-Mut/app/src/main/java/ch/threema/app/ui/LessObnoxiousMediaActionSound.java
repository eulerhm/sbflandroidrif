/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LessObnoxiousMediaActionSound {

    private static final int NUM_MEDIA_SOUND_STREAMS = 1;

    private SoundPool mSoundPool;

    private SoundState[] mSounds;

    private static final float volume = 0.1f;

    private static final String[] SOUND_DIRS = { "/product/media/audio/ui/", "/system/media/audio/ui/" };

    private static final String[] SOUND_FILES = { "camera_click.ogg", "camera_focus.ogg", "VideoRecord.ogg", "VideoStop.ogg" };

    private static final String TAG = "MediaActionSound";

    /**
     *  The sound used by
     *  {@link android.hardware.Camera#takePicture Camera.takePicture} to
     *  indicate still image capture.
     *  @see #play
     */
    public static final int SHUTTER_CLICK = 0;

    /**
     *  A sound to indicate that focusing has completed. Because deciding
     *  when this occurs is application-dependent, this sound is not used by
     *  any methods in the media or camera APIs.
     *  @see #play
     */
    public static final int FOCUS_COMPLETE = 1;

    /**
     *  The sound used by
     *  {@link android.media.MediaRecorder#start MediaRecorder.start()} to
     *  indicate the start of video recording.
     *  @see #play
     */
    public static final int START_VIDEO_RECORDING = 2;

    /**
     *  The sound used by
     *  {@link android.media.MediaRecorder#stop MediaRecorder.stop()} to
     *  indicate the end of video recording.
     *  @see #play
     */
    public static final int STOP_VIDEO_RECORDING = 3;

    /**
     *  States for SoundState.
     *  STATE_NOT_LOADED             : sample not loaded
     *  STATE_LOADING                : sample being loaded: waiting for load completion callback
     *  STATE_LOADING_PLAY_REQUESTED : sample being loaded and playback request received
     *  STATE_LOADED                 : sample loaded, ready for playback
     */
    private static final int STATE_NOT_LOADED = 0;

    private static final int STATE_LOADING = 1;

    private static final int STATE_LOADING_PLAY_REQUESTED = 2;

    private static final int STATE_LOADED = 3;

    private class SoundState {

        public final int name;

        public int id;

        public int state;

        public SoundState(int name) {
            this.name = name;
            if (!ListenerUtil.mutListener.listen(45462)) {
                // 0 is an invalid sample ID.
                id = 0;
            }
            if (!ListenerUtil.mutListener.listen(45463)) {
                state = STATE_NOT_LOADED;
            }
        }
    }

    /**
     *  Construct a new MediaActionSound instance. Only a single instance is
     *  needed for playing any platform media action sound; you do not need a
     *  separate instance for each sound type.
     */
    public LessObnoxiousMediaActionSound() {
        if (!ListenerUtil.mutListener.listen(45479)) {
            if ((ListenerUtil.mutListener.listen(45468) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45467) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45466) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45465) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45464) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(45469)) {
                    mSoundPool = new SoundPool.Builder().setMaxStreams(NUM_MEDIA_SOUND_STREAMS).setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()).build();
                }
                if (!ListenerUtil.mutListener.listen(45470)) {
                    mSoundPool.setOnLoadCompleteListener(mLoadCompleteListener);
                }
                if (!ListenerUtil.mutListener.listen(45471)) {
                    mSounds = new SoundState[SOUND_FILES.length];
                }
                if (!ListenerUtil.mutListener.listen(45478)) {
                    {
                        long _loopCounter534 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(45477) ? (i >= mSounds.length) : (ListenerUtil.mutListener.listen(45476) ? (i <= mSounds.length) : (ListenerUtil.mutListener.listen(45475) ? (i > mSounds.length) : (ListenerUtil.mutListener.listen(45474) ? (i != mSounds.length) : (ListenerUtil.mutListener.listen(45473) ? (i == mSounds.length) : (i < mSounds.length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter534", ++_loopCounter534);
                            if (!ListenerUtil.mutListener.listen(45472)) {
                                mSounds[i] = new SoundState(i);
                            }
                        }
                    }
                }
            }
        }
    }

    private int loadSound(SoundState sound) {
        final String soundFileName = SOUND_FILES[sound.name];
        if (!ListenerUtil.mutListener.listen(45488)) {
            {
                long _loopCounter535 = 0;
                for (String soundDir : SOUND_DIRS) {
                    ListenerUtil.loopListener.listen("_loopCounter535", ++_loopCounter535);
                    int id = mSoundPool.load(soundDir + soundFileName, 1);
                    if (!ListenerUtil.mutListener.listen(45487)) {
                        if ((ListenerUtil.mutListener.listen(45484) ? (id >= 0) : (ListenerUtil.mutListener.listen(45483) ? (id <= 0) : (ListenerUtil.mutListener.listen(45482) ? (id < 0) : (ListenerUtil.mutListener.listen(45481) ? (id != 0) : (ListenerUtil.mutListener.listen(45480) ? (id == 0) : (id > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(45485)) {
                                sound.state = STATE_LOADING;
                            }
                            if (!ListenerUtil.mutListener.listen(45486)) {
                                sound.id = id;
                            }
                            return id;
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     *  Preload a predefined platform sound to minimize latency when the sound is
     *  played later by {@link #play}.
     *  @param soundName The type of sound to preload, selected from
     *          SHUTTER_CLICK, FOCUS_COMPLETE, START_VIDEO_RECORDING, or
     *          STOP_VIDEO_RECORDING.
     *  @see #play
     *  @see #SHUTTER_CLICK
     *  @see #FOCUS_COMPLETE
     *  @see #START_VIDEO_RECORDING
     *  @see #STOP_VIDEO_RECORDING
     */
    public void load(int soundName) {
        if (!ListenerUtil.mutListener.listen(45515)) {
            if ((ListenerUtil.mutListener.listen(45493) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45492) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45491) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45490) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45489) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(45505)) {
                    if ((ListenerUtil.mutListener.listen(45504) ? ((ListenerUtil.mutListener.listen(45498) ? (soundName >= 0) : (ListenerUtil.mutListener.listen(45497) ? (soundName <= 0) : (ListenerUtil.mutListener.listen(45496) ? (soundName > 0) : (ListenerUtil.mutListener.listen(45495) ? (soundName != 0) : (ListenerUtil.mutListener.listen(45494) ? (soundName == 0) : (soundName < 0)))))) && (ListenerUtil.mutListener.listen(45503) ? (soundName <= SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45502) ? (soundName > SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45501) ? (soundName < SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45500) ? (soundName != SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45499) ? (soundName == SOUND_FILES.length) : (soundName >= SOUND_FILES.length))))))) : ((ListenerUtil.mutListener.listen(45498) ? (soundName >= 0) : (ListenerUtil.mutListener.listen(45497) ? (soundName <= 0) : (ListenerUtil.mutListener.listen(45496) ? (soundName > 0) : (ListenerUtil.mutListener.listen(45495) ? (soundName != 0) : (ListenerUtil.mutListener.listen(45494) ? (soundName == 0) : (soundName < 0)))))) || (ListenerUtil.mutListener.listen(45503) ? (soundName <= SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45502) ? (soundName > SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45501) ? (soundName < SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45500) ? (soundName != SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45499) ? (soundName == SOUND_FILES.length) : (soundName >= SOUND_FILES.length))))))))) {
                        throw new RuntimeException("Unknown sound requested: " + soundName);
                    }
                }
                SoundState sound = mSounds[soundName];
                synchronized (sound) {
                    if (!ListenerUtil.mutListener.listen(45514)) {
                        switch(sound.state) {
                            case STATE_NOT_LOADED:
                                if (!ListenerUtil.mutListener.listen(45512)) {
                                    if ((ListenerUtil.mutListener.listen(45510) ? (loadSound(sound) >= 0) : (ListenerUtil.mutListener.listen(45509) ? (loadSound(sound) > 0) : (ListenerUtil.mutListener.listen(45508) ? (loadSound(sound) < 0) : (ListenerUtil.mutListener.listen(45507) ? (loadSound(sound) != 0) : (ListenerUtil.mutListener.listen(45506) ? (loadSound(sound) == 0) : (loadSound(sound) <= 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(45511)) {
                                            Log.e(TAG, "load() error loading sound: " + soundName);
                                        }
                                    }
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(45513)) {
                                    Log.e(TAG, "load() called in wrong state: " + sound + " for sound: " + soundName);
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     *  <p>Play one of the predefined platform sounds for media actions.</p>
     *
     *  <p>Use this method to play a platform-specific sound for various media
     *  actions. The sound playback is done asynchronously, with the same
     *  behavior and content as the sounds played by
     *  {@link android.hardware.Camera#takePicture Camera.takePicture},
     *  {@link android.media.MediaRecorder#start MediaRecorder.start}, and
     *  {@link android.media.MediaRecorder#stop MediaRecorder.stop}.</p>
     *
     *  <p>With the {@link android.hardware.camera2 camera2} API, this method can be used to play
     *  standard camera operation sounds with the appropriate system behavior for such sounds.</p>
     *
     *  <p>With the older {@link android.hardware.Camera} API, using this method makes it easy to
     *  match the default device sounds when recording or capturing data through the preview
     *  callbacks, or when implementing custom camera-like features in your application.</p>
     *
     *  <p>If the sound has not been loaded by {@link #load} before calling play,
     *  play will load the sound at the cost of some additional latency before
     *  sound playback begins. </p>
     *
     *  @param soundName The type of sound to play, selected from
     *          SHUTTER_CLICK, FOCUS_COMPLETE, START_VIDEO_RECORDING, or
     *          STOP_VIDEO_RECORDING.
     *  @see android.hardware.Camera#takePicture
     *  @see android.media.MediaRecorder
     *  @see #SHUTTER_CLICK
     *  @see #FOCUS_COMPLETE
     *  @see #START_VIDEO_RECORDING
     *  @see #STOP_VIDEO_RECORDING
     */
    public void play(int soundName) {
        if (!ListenerUtil.mutListener.listen(45545)) {
            if ((ListenerUtil.mutListener.listen(45520) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45519) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45518) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45517) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45516) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(45532)) {
                    if ((ListenerUtil.mutListener.listen(45531) ? ((ListenerUtil.mutListener.listen(45525) ? (soundName >= 0) : (ListenerUtil.mutListener.listen(45524) ? (soundName <= 0) : (ListenerUtil.mutListener.listen(45523) ? (soundName > 0) : (ListenerUtil.mutListener.listen(45522) ? (soundName != 0) : (ListenerUtil.mutListener.listen(45521) ? (soundName == 0) : (soundName < 0)))))) && (ListenerUtil.mutListener.listen(45530) ? (soundName <= SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45529) ? (soundName > SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45528) ? (soundName < SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45527) ? (soundName != SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45526) ? (soundName == SOUND_FILES.length) : (soundName >= SOUND_FILES.length))))))) : ((ListenerUtil.mutListener.listen(45525) ? (soundName >= 0) : (ListenerUtil.mutListener.listen(45524) ? (soundName <= 0) : (ListenerUtil.mutListener.listen(45523) ? (soundName > 0) : (ListenerUtil.mutListener.listen(45522) ? (soundName != 0) : (ListenerUtil.mutListener.listen(45521) ? (soundName == 0) : (soundName < 0)))))) || (ListenerUtil.mutListener.listen(45530) ? (soundName <= SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45529) ? (soundName > SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45528) ? (soundName < SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45527) ? (soundName != SOUND_FILES.length) : (ListenerUtil.mutListener.listen(45526) ? (soundName == SOUND_FILES.length) : (soundName >= SOUND_FILES.length))))))))) {
                        throw new RuntimeException("Unknown sound requested: " + soundName);
                    }
                }
                SoundState sound = mSounds[soundName];
                synchronized (sound) {
                    if (!ListenerUtil.mutListener.listen(45544)) {
                        switch(sound.state) {
                            case STATE_NOT_LOADED:
                                if (!ListenerUtil.mutListener.listen(45533)) {
                                    loadSound(sound);
                                }
                                if (!ListenerUtil.mutListener.listen(45540)) {
                                    if ((ListenerUtil.mutListener.listen(45538) ? (loadSound(sound) >= 0) : (ListenerUtil.mutListener.listen(45537) ? (loadSound(sound) > 0) : (ListenerUtil.mutListener.listen(45536) ? (loadSound(sound) < 0) : (ListenerUtil.mutListener.listen(45535) ? (loadSound(sound) != 0) : (ListenerUtil.mutListener.listen(45534) ? (loadSound(sound) == 0) : (loadSound(sound) <= 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(45539)) {
                                            Log.e(TAG, "play() error loading sound: " + soundName);
                                        }
                                        break;
                                    }
                                }
                            case STATE_LOADING:
                                if (!ListenerUtil.mutListener.listen(45541)) {
                                    sound.state = STATE_LOADING_PLAY_REQUESTED;
                                }
                                break;
                            case STATE_LOADED:
                                if (!ListenerUtil.mutListener.listen(45542)) {
                                    mSoundPool.play(sound.id, volume, volume, 0, 0, 1.0f);
                                }
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(45543)) {
                                    Log.e(TAG, "play() called in wrong state: " + sound.state + " for sound: " + soundName);
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

    private SoundPool.OnLoadCompleteListener mLoadCompleteListener = new SoundPool.OnLoadCompleteListener() {

        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            if (!ListenerUtil.mutListener.listen(45568)) {
                {
                    long _loopCounter536 = 0;
                    for (SoundState sound : mSounds) {
                        ListenerUtil.loopListener.listen("_loopCounter536", ++_loopCounter536);
                        if (!ListenerUtil.mutListener.listen(45546)) {
                            if (sound.id != sampleId) {
                                continue;
                            }
                        }
                        int playSoundId = 0;
                        synchronized (sound) {
                            if (!ListenerUtil.mutListener.listen(45555)) {
                                if ((ListenerUtil.mutListener.listen(45551) ? (status >= 0) : (ListenerUtil.mutListener.listen(45550) ? (status <= 0) : (ListenerUtil.mutListener.listen(45549) ? (status > 0) : (ListenerUtil.mutListener.listen(45548) ? (status < 0) : (ListenerUtil.mutListener.listen(45547) ? (status == 0) : (status != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(45552)) {
                                        sound.state = STATE_NOT_LOADED;
                                    }
                                    if (!ListenerUtil.mutListener.listen(45553)) {
                                        sound.id = 0;
                                    }
                                    if (!ListenerUtil.mutListener.listen(45554)) {
                                        Log.e(TAG, "OnLoadCompleteListener() error: " + status + " loading sound: " + sound.name);
                                    }
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(45560)) {
                                switch(sound.state) {
                                    case STATE_LOADING:
                                        if (!ListenerUtil.mutListener.listen(45556)) {
                                            sound.state = STATE_LOADED;
                                        }
                                        break;
                                    case STATE_LOADING_PLAY_REQUESTED:
                                        if (!ListenerUtil.mutListener.listen(45557)) {
                                            playSoundId = sound.id;
                                        }
                                        if (!ListenerUtil.mutListener.listen(45558)) {
                                            sound.state = STATE_LOADED;
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(45559)) {
                                            Log.e(TAG, "OnLoadCompleteListener() called in wrong state: " + sound.state + " for sound: " + sound.name);
                                        }
                                        break;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(45567)) {
                            if ((ListenerUtil.mutListener.listen(45565) ? (playSoundId >= 0) : (ListenerUtil.mutListener.listen(45564) ? (playSoundId <= 0) : (ListenerUtil.mutListener.listen(45563) ? (playSoundId > 0) : (ListenerUtil.mutListener.listen(45562) ? (playSoundId < 0) : (ListenerUtil.mutListener.listen(45561) ? (playSoundId == 0) : (playSoundId != 0))))))) {
                                if (!ListenerUtil.mutListener.listen(45566)) {
                                    soundPool.play(playSoundId, volume, volume, 0, 0, 1.0f);
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    };

    /**
     *  Free up all audio resources used by this MediaActionSound instance. Do
     *  not call any other methods on a MediaActionSound instance after calling
     *  release().
     */
    public void release() {
        if (!ListenerUtil.mutListener.listen(45580)) {
            if ((ListenerUtil.mutListener.listen(45573) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45572) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45571) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45570) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(45569) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(45579)) {
                    if (mSoundPool != null) {
                        if (!ListenerUtil.mutListener.listen(45576)) {
                            {
                                long _loopCounter537 = 0;
                                for (SoundState sound : mSounds) {
                                    ListenerUtil.loopListener.listen("_loopCounter537", ++_loopCounter537);
                                    synchronized (sound) {
                                        if (!ListenerUtil.mutListener.listen(45574)) {
                                            sound.state = STATE_NOT_LOADED;
                                        }
                                        if (!ListenerUtil.mutListener.listen(45575)) {
                                            sound.id = 0;
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(45577)) {
                            mSoundPool.release();
                        }
                        if (!ListenerUtil.mutListener.listen(45578)) {
                            mSoundPool = null;
                        }
                    }
                }
            }
        }
    }
}
