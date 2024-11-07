/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.threema.app.qrscanner.assit;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import java.io.Closeable;
import java.io.IOException;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * @date 2016-11-24 9:52
 * @auther GuoJinyu
 * @description modified
 */
public final class BeepManager implements Closeable {

    private static final String TAG = BeepManager.class.getSimpleName();

    private static final float BEEP_VOLUME = 0.10f;

    private final Activity activity;

    private MediaPlayer mediaPlayer;

    private boolean playBeep;

    public BeepManager(Activity activity, boolean playBeep) {
        this.activity = activity;
        if (!ListenerUtil.mutListener.listen(33386)) {
            this.mediaPlayer = null;
        }
        if (!ListenerUtil.mutListener.listen(33387)) {
            this.playBeep = playBeep;
        }
    }

    private boolean shouldBeep(Context activity) {
        boolean shouldPlayBeep = playBeep;
        if (!ListenerUtil.mutListener.listen(33390)) {
            if (shouldPlayBeep) {
                // See if sound settings overrides this
                AudioManager audioService = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
                if (!ListenerUtil.mutListener.listen(33389)) {
                    if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
                        if (!ListenerUtil.mutListener.listen(33388)) {
                            shouldPlayBeep = false;
                        }
                    }
                }
            }
        }
        return shouldPlayBeep;
    }

    public synchronized void updatePrefs() {
        if (!ListenerUtil.mutListener.listen(33391)) {
            playBeep = shouldBeep(activity);
        }
        if (!ListenerUtil.mutListener.listen(33395)) {
            if ((ListenerUtil.mutListener.listen(33392) ? (playBeep || mediaPlayer == null) : (playBeep && mediaPlayer == null))) {
                if (!ListenerUtil.mutListener.listen(33393)) {
                    // so we now play on the music stream.
                    activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
                }
                if (!ListenerUtil.mutListener.listen(33394)) {
                    mediaPlayer = buildMediaPlayer(activity);
                }
            }
        }
    }

    public synchronized void playBeepSoundAndVibrate() {
        if (!ListenerUtil.mutListener.listen(33398)) {
            if ((ListenerUtil.mutListener.listen(33396) ? (playBeep || mediaPlayer != null) : (playBeep && mediaPlayer != null))) {
                if (!ListenerUtil.mutListener.listen(33397)) {
                    mediaPlayer.start();
                }
            }
        }
    }

    private MediaPlayer buildMediaPlayer(Context activity) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor file = activity.getResources().openRawResourceFd(R.raw.qrscanner_beep);
            try {
                if (!ListenerUtil.mutListener.listen(33401)) {
                    mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(33400)) {
                    file.close();
                }
            }
            if (!ListenerUtil.mutListener.listen(33402)) {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            if (!ListenerUtil.mutListener.listen(33403)) {
                mediaPlayer.setLooping(false);
            }
            if (!ListenerUtil.mutListener.listen(33404)) {
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
            }
            if (!ListenerUtil.mutListener.listen(33405)) {
                mediaPlayer.prepare();
            }
            return mediaPlayer;
        } catch (IOException ioe) {
            if (!ListenerUtil.mutListener.listen(33399)) {
                // Log.w(TAG, ioe);
                mediaPlayer.release();
            }
            return null;
        }
    }

    @Override
    public synchronized void close() {
        if (!ListenerUtil.mutListener.listen(33408)) {
            if (mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(33406)) {
                    mediaPlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(33407)) {
                    mediaPlayer = null;
                }
            }
        }
    }
}
