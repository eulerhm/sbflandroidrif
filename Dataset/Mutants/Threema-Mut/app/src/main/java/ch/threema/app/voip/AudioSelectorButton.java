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
package ch.threema.app.voip;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.HashSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.threema.app.R;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.voip.VoipAudioManager.AudioDevice;
import ch.threema.app.voip.listeners.VoipAudioManagerListener;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.voip.services.VoipCallService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AudioSelectorButton extends AppCompatImageView implements View.OnClickListener {

    private static final Logger logger = LoggerFactory.getLogger(AudioSelectorButton.class);

    // Constants for Drawable.setAlpha()
    private static final int HIDDEN = 0;

    private static final int VISIBLE = 255;

    private AudioDevice selectedAudioDevice;

    private HashSet<AudioDevice> availableAudioDevices;

    private AudioDeviceMultiSelectListener selectionListener;

    public interface AudioDeviceMultiSelectListener {

        void onShowSelected(HashSet<AudioDevice> audioDevices, AudioDevice selectedDevice);
    }

    public AudioSelectorButton(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(60588)) {
            init();
        }
    }

    public AudioSelectorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(60589)) {
            init();
        }
    }

    public AudioSelectorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!ListenerUtil.mutListener.listen(60590)) {
            init();
        }
    }

    // Listeners
    private VoipAudioManagerListener audioManagerListener = new VoipAudioManagerListener() {

        @Override
        public void onAudioDeviceChanged(@Nullable final AudioDevice audioDevice, @NonNull final HashSet<AudioDevice> availableAudioDevices) {
            if (!ListenerUtil.mutListener.listen(60591)) {
                logger.debug("Audio devices changed, selected=" + selectedAudioDevice + ", available=" + availableAudioDevices);
            }
            if (!ListenerUtil.mutListener.listen(60592)) {
                selectedAudioDevice = audioDevice;
            }
            if (!ListenerUtil.mutListener.listen(60593)) {
                RuntimeUtil.runOnUiThread(() -> updateAudioSelectorButton(audioDevice, availableAudioDevices));
            }
        }
    };

    private void updateAudioSelectorButton(AudioDevice audioDevice, HashSet<AudioDevice> availableAudioDevices) {
        if (!ListenerUtil.mutListener.listen(60594)) {
            this.selectedAudioDevice = audioDevice;
        }
        if (!ListenerUtil.mutListener.listen(60595)) {
            this.availableAudioDevices = availableAudioDevices;
        }
        final LayerDrawable layers = (LayerDrawable) getBackground();
        if (!ListenerUtil.mutListener.listen(60606)) {
            if ((ListenerUtil.mutListener.listen(60600) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(60599) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(60598) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(60597) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(60596) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT))))))) {
                if (!ListenerUtil.mutListener.listen(60601)) {
                    layers.findDrawableByLayerId(R.id.moreIndicatorItem).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
                if (!ListenerUtil.mutListener.listen(60602)) {
                    layers.findDrawableByLayerId(R.id.bluetoothItem).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
                if (!ListenerUtil.mutListener.listen(60603)) {
                    layers.findDrawableByLayerId(R.id.handsetItem).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
                if (!ListenerUtil.mutListener.listen(60604)) {
                    layers.findDrawableByLayerId(R.id.headsetItem).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
                if (!ListenerUtil.mutListener.listen(60605)) {
                    layers.findDrawableByLayerId(R.id.speakerphoneItem).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60612)) {
            layers.findDrawableByLayerId(R.id.moreIndicatorItem).setAlpha((ListenerUtil.mutListener.listen(60611) ? (availableAudioDevices.size() >= 2) : (ListenerUtil.mutListener.listen(60610) ? (availableAudioDevices.size() <= 2) : (ListenerUtil.mutListener.listen(60609) ? (availableAudioDevices.size() < 2) : (ListenerUtil.mutListener.listen(60608) ? (availableAudioDevices.size() != 2) : (ListenerUtil.mutListener.listen(60607) ? (availableAudioDevices.size() == 2) : (availableAudioDevices.size() > 2)))))) ? VISIBLE : HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(60613)) {
            layers.findDrawableByLayerId(R.id.bluetoothItem).setAlpha(selectedAudioDevice.equals(AudioDevice.BLUETOOTH) ? VISIBLE : HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(60614)) {
            layers.findDrawableByLayerId(R.id.handsetItem).setAlpha(selectedAudioDevice.equals(AudioDevice.EARPIECE) ? VISIBLE : HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(60615)) {
            layers.findDrawableByLayerId(R.id.headsetItem).setAlpha(selectedAudioDevice.equals(AudioDevice.WIRED_HEADSET) ? VISIBLE : HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(60616)) {
            layers.findDrawableByLayerId(R.id.speakerphoneItem).setAlpha(selectedAudioDevice.equals(AudioDevice.SPEAKER_PHONE) ? VISIBLE : HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(60629)) {
            if (!RuntimeUtil.isInTest()) {
                if (!ListenerUtil.mutListener.listen(60622)) {
                    setClickable((ListenerUtil.mutListener.listen(60621) ? (availableAudioDevices.size() >= 1) : (ListenerUtil.mutListener.listen(60620) ? (availableAudioDevices.size() <= 1) : (ListenerUtil.mutListener.listen(60619) ? (availableAudioDevices.size() < 1) : (ListenerUtil.mutListener.listen(60618) ? (availableAudioDevices.size() != 1) : (ListenerUtil.mutListener.listen(60617) ? (availableAudioDevices.size() == 1) : (availableAudioDevices.size() > 1)))))));
                }
                if (!ListenerUtil.mutListener.listen(60628)) {
                    setEnabled((ListenerUtil.mutListener.listen(60627) ? (availableAudioDevices.size() >= 1) : (ListenerUtil.mutListener.listen(60626) ? (availableAudioDevices.size() <= 1) : (ListenerUtil.mutListener.listen(60625) ? (availableAudioDevices.size() < 1) : (ListenerUtil.mutListener.listen(60624) ? (availableAudioDevices.size() != 1) : (ListenerUtil.mutListener.listen(60623) ? (availableAudioDevices.size() == 1) : (availableAudioDevices.size() > 1)))))));
                }
            }
        }
    }

    private void init() {
        if (!ListenerUtil.mutListener.listen(60630)) {
            setOnClickListener(this);
        }
        AudioDevice initialAudioDevice = !RuntimeUtil.isInTest() ? AudioDevice.NONE : AudioDevice.SPEAKER_PHONE;
        if (!ListenerUtil.mutListener.listen(60631)) {
            updateAudioSelectorButton(initialAudioDevice, new HashSet<>(Collections.singletonList(initialAudioDevice)));
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (!ListenerUtil.mutListener.listen(60632)) {
            super.onAttachedToWindow();
        }
        if (!ListenerUtil.mutListener.listen(60633)) {
            VoipListenerManager.audioManagerListener.add(this.audioManagerListener);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (!ListenerUtil.mutListener.listen(60634)) {
            VoipListenerManager.audioManagerListener.remove(this.audioManagerListener);
        }
        if (!ListenerUtil.mutListener.listen(60635)) {
            super.onDetachedFromWindow();
        }
    }

    @Override
    public void onClick(View v) {
        if (!ListenerUtil.mutListener.listen(60648)) {
            if (this.availableAudioDevices != null) {
                if (!ListenerUtil.mutListener.listen(60647)) {
                    if ((ListenerUtil.mutListener.listen(60640) ? (this.availableAudioDevices.size() >= 2) : (ListenerUtil.mutListener.listen(60639) ? (this.availableAudioDevices.size() <= 2) : (ListenerUtil.mutListener.listen(60638) ? (this.availableAudioDevices.size() < 2) : (ListenerUtil.mutListener.listen(60637) ? (this.availableAudioDevices.size() != 2) : (ListenerUtil.mutListener.listen(60636) ? (this.availableAudioDevices.size() == 2) : (this.availableAudioDevices.size() > 2))))))) {
                        if (!ListenerUtil.mutListener.listen(60646)) {
                            if (selectionListener != null) {
                                if (!ListenerUtil.mutListener.listen(60645)) {
                                    selectionListener.onShowSelected(availableAudioDevices, selectedAudioDevice);
                                }
                            }
                        }
                    } else {
                        AudioDevice newAudioDevice = AudioDevice.EARPIECE;
                        if (!ListenerUtil.mutListener.listen(60643)) {
                            {
                                long _loopCounter729 = 0;
                                for (AudioDevice device : availableAudioDevices) {
                                    ListenerUtil.loopListener.listen("_loopCounter729", ++_loopCounter729);
                                    if (!ListenerUtil.mutListener.listen(60642)) {
                                        if (!device.equals(selectedAudioDevice)) {
                                            if (!ListenerUtil.mutListener.listen(60641)) {
                                                newAudioDevice = device;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(60644)) {
                            sendBroadcastToService(newAudioDevice);
                        }
                    }
                }
            }
        }
    }

    public void setAudioDeviceMultiSelectListener(AudioDeviceMultiSelectListener listener) {
        if (!ListenerUtil.mutListener.listen(60649)) {
            this.selectionListener = listener;
        }
    }

    private void sendBroadcastToService(AudioDevice device) {
        Intent intent = new Intent();
        if (!ListenerUtil.mutListener.listen(60650)) {
            intent.setAction(VoipCallService.ACTION_SET_AUDIO_DEVICE);
        }
        if (!ListenerUtil.mutListener.listen(60651)) {
            intent.putExtra(VoipCallService.EXTRA_AUDIO_DEVICE, device);
        }
        if (!ListenerUtil.mutListener.listen(60652)) {
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }
    }
}
