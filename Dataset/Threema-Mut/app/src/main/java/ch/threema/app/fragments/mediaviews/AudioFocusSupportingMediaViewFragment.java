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
package ch.threema.app.fragments.mediaviews;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.mediaattacher.PreviewFragmentInterface;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class AudioFocusSupportingMediaViewFragment extends MediaViewFragment implements AudioManager.OnAudioFocusChangeListener, PreviewFragmentInterface.AudioFocusActions {

    private static final Logger logger = LoggerFactory.getLogger(AudioFocusSupportingMediaViewFragment.class);

    private AudioManager audioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(23486)) {
            this.audioManager = (AudioManager) getContext().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (!ListenerUtil.mutListener.listen(23496)) {
            switch(focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    if (!ListenerUtil.mutListener.listen(23487)) {
                        // resume playback
                        logger.debug("AUDIOFOCUS_GAIN");
                    }
                    if (!ListenerUtil.mutListener.listen(23488)) {
                        resumeAudio();
                    }
                    if (!ListenerUtil.mutListener.listen(23489)) {
                        setVolume(1.0f);
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    if (!ListenerUtil.mutListener.listen(23490)) {
                        // Lost focus for an unbounded amount of time: stop playback and release media player
                        logger.debug("AUDIOFOCUS_LOSS");
                    }
                    if (!ListenerUtil.mutListener.listen(23491)) {
                        stopAudio();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    if (!ListenerUtil.mutListener.listen(23492)) {
                        // is likely to resume
                        logger.debug("AUDIOFOCUS_LOSS_TRANSIENT");
                    }
                    if (!ListenerUtil.mutListener.listen(23493)) {
                        pauseAudio();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    if (!ListenerUtil.mutListener.listen(23494)) {
                        // at an attenuated level
                        logger.debug("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    }
                    if (!ListenerUtil.mutListener.listen(23495)) {
                        setVolume(0.2f);
                    }
                    break;
            }
        }
    }

    protected boolean requestFocus() {
        if (!ListenerUtil.mutListener.listen(23498)) {
            if (audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                if (!ListenerUtil.mutListener.listen(23497)) {
                    Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        }
        return true;
    }

    protected void abandonFocus() {
        if (!ListenerUtil.mutListener.listen(23499)) {
            audioManager.abandonAudioFocus(this);
        }
    }
}
