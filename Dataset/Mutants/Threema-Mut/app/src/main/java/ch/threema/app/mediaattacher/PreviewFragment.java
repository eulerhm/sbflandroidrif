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
package ch.threema.app.mediaattacher;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ch.threema.app.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class PreviewFragment extends Fragment implements AudioManager.OnAudioFocusChangeListener, PreviewFragmentInterface.AudioFocusActions {

    private AudioManager audioManager;

    protected MediaAttachItem mediaItem;

    protected MediaAttachViewModel mediaAttachViewModel;

    protected View rootView;

    protected boolean isChecked = false;

    public PreviewFragment(MediaAttachItem mediaItem, MediaAttachViewModel mediaAttachViewModel) {
        if (!ListenerUtil.mutListener.listen(30263)) {
            this.mediaItem = mediaItem;
        }
        if (!ListenerUtil.mutListener.listen(30264)) {
            this.mediaAttachViewModel = mediaAttachViewModel;
        }
        if (!ListenerUtil.mutListener.listen(30265)) {
            setRetainInstance(true);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(30266)) {
            this.audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(30267)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (!ListenerUtil.mutListener.listen(30273)) {
            switch(focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    if (!ListenerUtil.mutListener.listen(30268)) {
                        resumeAudio();
                    }
                    if (!ListenerUtil.mutListener.listen(30269)) {
                        setVolume(1.0f);
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    if (!ListenerUtil.mutListener.listen(30270)) {
                        stopAudio();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    if (!ListenerUtil.mutListener.listen(30271)) {
                        pauseAudio();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    if (!ListenerUtil.mutListener.listen(30272)) {
                        setVolume(0.2f);
                    }
                    break;
            }
        }
    }

    protected boolean requestFocus() {
        if (!ListenerUtil.mutListener.listen(30275)) {
            if (audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                if (!ListenerUtil.mutListener.listen(30274)) {
                    Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        }
        return true;
    }

    protected void abandonFocus() {
        if (!ListenerUtil.mutListener.listen(30276)) {
            audioManager.abandonAudioFocus(this);
        }
    }
}
