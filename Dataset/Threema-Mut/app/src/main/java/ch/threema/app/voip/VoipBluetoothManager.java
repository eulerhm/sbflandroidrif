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

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.ThreadUtils;
import java.util.List;
import java.util.Set;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.voip.services.VoipCallService;
import ch.threema.app.voip.util.AppRTCUtils;
import ch.threema.app.voip.util.VoipUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * VoipBluetoothManager manages functions related to Bluetoth devices in
 * Threema voice calls.
 */
public class VoipBluetoothManager {

    private static final Logger logger = LoggerFactory.getLogger(VoipBluetoothManager.class);

    // Timeout interval for starting or stopping audio to a Bluetooth SCO device.
    private static final int BLUETOOTH_SCO_TIMEOUT_MS = 4000;

    // Maximum number of SCO connection attempts.
    private static final int MAX_SCO_CONNECTION_ATTEMPTS = 2;

    // Bluetooth connection state.
    public enum State {

        // Bluetooth is not available; no adapter or Bluetooth is off.
        UNINITIALIZED,
        // Bluetooth error happened when trying to start Bluetooth.
        ERROR,
        // SCO is not started or disconnected.
        HEADSET_UNAVAILABLE,
        // present, but SCO is not started or disconnected.
        HEADSET_AVAILABLE,
        // Bluetooth audio SCO connection with remote device is closing.
        SCO_DISCONNECTING,
        // Bluetooth audio SCO connection with remote device is initiated.
        SCO_CONNECTING,
        // Bluetooth audio SCO connection with remote device is established.
        SCO_CONNECTED
    }

    private final Context apprtcContext;

    private final VoipAudioManager voipAudioManager;

    private final AudioManager audioManager;

    private final Handler handler;

    int scoConnectionAttempts;

    private State bluetoothState;

    private Long bluetoothAudioConnectedAt;

    private final BluetoothProfile.ServiceListener bluetoothServiceListener;

    private BluetoothAdapter bluetoothAdapter;

    private BluetoothHeadset bluetoothHeadset;

    private BluetoothDevice bluetoothDevice;

    private final BroadcastReceiver bluetoothHeadsetReceiver;

    // callback after those calls.
    private final Runnable bluetoothTimeoutRunnable = new Runnable() {

        @Override
        public void run() {
            if (!ListenerUtil.mutListener.listen(61730)) {
                bluetoothTimeout();
            }
        }
    };

    /**
     *  Implementation of an interface that notifies BluetoothProfile IPC clients when they have been
     *  connected to or disconnected from the service.
     */
    private class BluetoothServiceListener implements BluetoothProfile.ServiceListener {

        @Override
        public // connection and perform other operations that are relevant to the headset profile.
        void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (!ListenerUtil.mutListener.listen(61732)) {
                if ((ListenerUtil.mutListener.listen(61731) ? (profile != BluetoothProfile.HEADSET && bluetoothState == State.UNINITIALIZED) : (profile != BluetoothProfile.HEADSET || bluetoothState == State.UNINITIALIZED))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(61733)) {
                logger.debug("BluetoothServiceListener.onServiceConnected: BT state=" + bluetoothState);
            }
            if (!ListenerUtil.mutListener.listen(61734)) {
                // Android only supports one connected Bluetooth Headset at a time.
                bluetoothHeadset = (BluetoothHeadset) proxy;
            }
            if (!ListenerUtil.mutListener.listen(61735)) {
                updateAudioDeviceState();
            }
            if (!ListenerUtil.mutListener.listen(61736)) {
                logger.debug("onServiceConnected done: BT state=" + bluetoothState);
            }
        }

        @Override
        public /**
         * Notifies the client when the proxy object has been disconnected from the service.
         */
        void onServiceDisconnected(int profile) {
            if (!ListenerUtil.mutListener.listen(61738)) {
                if ((ListenerUtil.mutListener.listen(61737) ? (profile != BluetoothProfile.HEADSET && bluetoothState == State.UNINITIALIZED) : (profile != BluetoothProfile.HEADSET || bluetoothState == State.UNINITIALIZED))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(61739)) {
                logger.debug("BluetoothServiceListener.onServiceDisconnected: BT state=" + bluetoothState);
            }
            if (!ListenerUtil.mutListener.listen(61740)) {
                stopScoAudio();
            }
            if (!ListenerUtil.mutListener.listen(61741)) {
                bluetoothHeadset = null;
            }
            if (!ListenerUtil.mutListener.listen(61742)) {
                bluetoothDevice = null;
            }
            if (!ListenerUtil.mutListener.listen(61743)) {
                bluetoothState = State.HEADSET_UNAVAILABLE;
            }
            if (!ListenerUtil.mutListener.listen(61744)) {
                updateAudioDeviceState();
            }
            if (!ListenerUtil.mutListener.listen(61745)) {
                logger.debug("onServiceDisconnected done: BT state=" + bluetoothState);
            }
        }
    }

    // Detects headset changes and Bluetooth SCO state changes.
    private class BluetoothHeadsetBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!ListenerUtil.mutListener.listen(61746)) {
                if (bluetoothState == State.UNINITIALIZED) {
                    return;
                }
            }
            final String action = intent.getAction();
            if (!ListenerUtil.mutListener.listen(61750)) {
                if (action.equals(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED)) {
                    if (!ListenerUtil.mutListener.listen(61749)) {
                        this.onConnectionStateChange(intent);
                    }
                } else if (action.equals(BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED)) {
                    if (!ListenerUtil.mutListener.listen(61748)) {
                        this.onAudioStateChange(intent);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(61747)) {
                        logger.warn("Unknown bluetooth broadcast action: {}", action);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61751)) {
                logger.debug("onReceive done: BT state={}", bluetoothState);
            }
        }

        /**
         *  Change in connection state of the Headset profile. Note that the
         *  change does not tell us anything about whether we're streaming
         *  audio to BT over SCO. Typically received when user turns on a BT
         *  headset while audio is active using another audio device.
         */
        private void onConnectionStateChange(Intent intent) {
            final int state = intent.getIntExtra(BluetoothHeadset.EXTRA_STATE, BluetoothHeadset.STATE_DISCONNECTED);
            if (!ListenerUtil.mutListener.listen(61752)) {
                logger.debug("BluetoothHeadsetBroadcastReceiver.onReceive: " + "a=ACTION_CONNECTION_STATE_CHANGED, " + "s=" + headsetStateToString(state) + ", " + "sb=" + isInitialStickyBroadcast() + ", " + "BT state: " + bluetoothState);
            }
            if (!ListenerUtil.mutListener.listen(61757)) {
                switch(state) {
                    case BluetoothHeadset.STATE_CONNECTED:
                        if (!ListenerUtil.mutListener.listen(61753)) {
                            scoConnectionAttempts = 0;
                        }
                        if (!ListenerUtil.mutListener.listen(61754)) {
                            updateAudioDeviceState();
                        }
                        break;
                    case BluetoothHeadset.STATE_CONNECTING:
                    case BluetoothHeadset.STATE_DISCONNECTING:
                        // No action needed
                        break;
                    case BluetoothHeadset.STATE_DISCONNECTED:
                        if (!ListenerUtil.mutListener.listen(61755)) {
                            // Bluetooth is probably powered off during the call.
                            stopScoAudio();
                        }
                        if (!ListenerUtil.mutListener.listen(61756)) {
                            updateAudioDeviceState();
                        }
                        break;
                }
            }
        }

        /**
         *  Change in the audio (SCO) connection state of the Headset profile.
         *  Typically received after call to startScoAudio() has finalized.
         */
        private void onAudioStateChange(Intent intent) {
            final int state = intent.getIntExtra(BluetoothHeadset.EXTRA_STATE, BluetoothHeadset.STATE_AUDIO_DISCONNECTED);
            if (!ListenerUtil.mutListener.listen(61758)) {
                logger.debug("BluetoothHeadsetBroadcastReceiver.onReceive: " + "a=ACTION_AUDIO_STATE_CHANGED, " + "s=" + headsetStateToString(state) + ", " + "sb=" + isInitialStickyBroadcast() + ", " + "BT state: " + bluetoothState);
            }
            if (!ListenerUtil.mutListener.listen(61799)) {
                if (state == BluetoothHeadset.STATE_AUDIO_CONNECTED) {
                    if (!ListenerUtil.mutListener.listen(61791)) {
                        cancelTimer();
                    }
                    if (!ListenerUtil.mutListener.listen(61798)) {
                        if (bluetoothState == State.SCO_CONNECTING) {
                            if (!ListenerUtil.mutListener.listen(61793)) {
                                logger.debug("+++ Bluetooth audio SCO is now connected");
                            }
                            if (!ListenerUtil.mutListener.listen(61794)) {
                                bluetoothState = State.SCO_CONNECTED;
                            }
                            if (!ListenerUtil.mutListener.listen(61795)) {
                                bluetoothAudioConnectedAt = System.nanoTime();
                            }
                            if (!ListenerUtil.mutListener.listen(61796)) {
                                scoConnectionAttempts = 0;
                            }
                            if (!ListenerUtil.mutListener.listen(61797)) {
                                updateAudioDeviceState();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(61792)) {
                                logger.warn("Unexpected state BluetoothHeadset.STATE_AUDIO_CONNECTED");
                            }
                        }
                    }
                } else if (state == BluetoothHeadset.STATE_AUDIO_CONNECTING) {
                    if (!ListenerUtil.mutListener.listen(61790)) {
                        logger.debug("+++ Bluetooth audio SCO is now connecting...");
                    }
                } else if (state == BluetoothHeadset.STATE_AUDIO_DISCONNECTED) {
                    if (!ListenerUtil.mutListener.listen(61759)) {
                        logger.debug("+++ Bluetooth audio SCO is now disconnected");
                    }
                    if (!ListenerUtil.mutListener.listen(61789)) {
                        if (isInitialStickyBroadcast()) {
                            if (!ListenerUtil.mutListener.listen(61788)) {
                                logger.debug("Ignore STATE_AUDIO_DISCONNECTED initial sticky broadcast.");
                            }
                        } else if (bluetoothState == State.SCO_CONNECTED) {
                            // headset disconnects after 1050 ms.
                            Long msElapsed = null;
                            long msElapsedThreshold = 1500;
                            if (!ListenerUtil.mutListener.listen(61774)) {
                                if (bluetoothAudioConnectedAt != null) {
                                    if (!ListenerUtil.mutListener.listen(61773)) {
                                        msElapsed = (ListenerUtil.mutListener.listen(61772) ? ((ListenerUtil.mutListener.listen(61768) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) % 1000) : (ListenerUtil.mutListener.listen(61767) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) * 1000) : (ListenerUtil.mutListener.listen(61766) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) - 1000) : (ListenerUtil.mutListener.listen(61765) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) + 1000) : (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) / 1000))))) % 1000) : (ListenerUtil.mutListener.listen(61771) ? ((ListenerUtil.mutListener.listen(61768) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) % 1000) : (ListenerUtil.mutListener.listen(61767) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) * 1000) : (ListenerUtil.mutListener.listen(61766) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) - 1000) : (ListenerUtil.mutListener.listen(61765) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) + 1000) : (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) / 1000))))) * 1000) : (ListenerUtil.mutListener.listen(61770) ? ((ListenerUtil.mutListener.listen(61768) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) % 1000) : (ListenerUtil.mutListener.listen(61767) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) * 1000) : (ListenerUtil.mutListener.listen(61766) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) - 1000) : (ListenerUtil.mutListener.listen(61765) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) + 1000) : (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) / 1000))))) - 1000) : (ListenerUtil.mutListener.listen(61769) ? ((ListenerUtil.mutListener.listen(61768) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) % 1000) : (ListenerUtil.mutListener.listen(61767) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) * 1000) : (ListenerUtil.mutListener.listen(61766) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) - 1000) : (ListenerUtil.mutListener.listen(61765) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) + 1000) : (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) / 1000))))) + 1000) : ((ListenerUtil.mutListener.listen(61768) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) % 1000) : (ListenerUtil.mutListener.listen(61767) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) * 1000) : (ListenerUtil.mutListener.listen(61766) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) - 1000) : (ListenerUtil.mutListener.listen(61765) ? (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) + 1000) : (((ListenerUtil.mutListener.listen(61764) ? (System.nanoTime() % bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61763) ? (System.nanoTime() / bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61762) ? (System.nanoTime() * bluetoothAudioConnectedAt) : (ListenerUtil.mutListener.listen(61761) ? (System.nanoTime() + bluetoothAudioConnectedAt) : (System.nanoTime() - bluetoothAudioConnectedAt)))))) / 1000))))) / 1000)))));
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(61775)) {
                                logger.info("Time elapsed since bluetooth audio connected: {} ms", msElapsed);
                            }
                            if (!ListenerUtil.mutListener.listen(61787)) {
                                if ((ListenerUtil.mutListener.listen(61781) ? (msElapsed == null && (ListenerUtil.mutListener.listen(61780) ? (msElapsed >= msElapsedThreshold) : (ListenerUtil.mutListener.listen(61779) ? (msElapsed <= msElapsedThreshold) : (ListenerUtil.mutListener.listen(61778) ? (msElapsed > msElapsedThreshold) : (ListenerUtil.mutListener.listen(61777) ? (msElapsed != msElapsedThreshold) : (ListenerUtil.mutListener.listen(61776) ? (msElapsed == msElapsedThreshold) : (msElapsed < msElapsedThreshold))))))) : (msElapsed == null || (ListenerUtil.mutListener.listen(61780) ? (msElapsed >= msElapsedThreshold) : (ListenerUtil.mutListener.listen(61779) ? (msElapsed <= msElapsedThreshold) : (ListenerUtil.mutListener.listen(61778) ? (msElapsed > msElapsedThreshold) : (ListenerUtil.mutListener.listen(61777) ? (msElapsed != msElapsedThreshold) : (ListenerUtil.mutListener.listen(61776) ? (msElapsed == msElapsedThreshold) : (msElapsed < msElapsedThreshold))))))))) {
                                    if (!ListenerUtil.mutListener.listen(61784)) {
                                        logger.info("Bluetooth headset disconnected. Switching to phone audio.");
                                    }
                                    if (!ListenerUtil.mutListener.listen(61785)) {
                                        VoipBluetoothManager.this.stop();
                                    }
                                    if (!ListenerUtil.mutListener.listen(61786)) {
                                        VoipBluetoothManager.this.updateAudioDeviceState();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(61782)) {
                                        logger.info("Bluetooth headset disconnected after {} ms. Ending call.", msElapsed);
                                    }
                                    if (!ListenerUtil.mutListener.listen(61783)) {
                                        VoipUtil.sendVoipCommand(ThreemaApplication.getAppContext(), VoipCallService.class, VoipCallService.ACTION_HANGUP);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(61760)) {
                                // The output device was probably switched via UI
                                VoipBluetoothManager.this.updateAudioDeviceState();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *  Construction.
     */
    static VoipBluetoothManager create(Context context, VoipAudioManager audioManager) {
        if (!ListenerUtil.mutListener.listen(61800)) {
            logger.debug("create" + AppRTCUtils.getThreadInfo());
        }
        return new VoipBluetoothManager(context, audioManager);
    }

    protected VoipBluetoothManager(Context context, VoipAudioManager audioManager) {
        if (!ListenerUtil.mutListener.listen(61801)) {
            logger.debug("ctor");
        }
        if (!ListenerUtil.mutListener.listen(61802)) {
            ThreadUtils.checkIsOnMainThread();
        }
        this.apprtcContext = context;
        this.voipAudioManager = audioManager;
        this.audioManager = getAudioManager(context);
        if (!ListenerUtil.mutListener.listen(61803)) {
            this.bluetoothState = State.UNINITIALIZED;
        }
        this.bluetoothServiceListener = new BluetoothServiceListener();
        this.bluetoothHeadsetReceiver = new BluetoothHeadsetBroadcastReceiver();
        this.handler = new Handler(Looper.getMainLooper());
    }

    /**
     *  Returns the internal state.
     */
    public State getState() {
        if (!ListenerUtil.mutListener.listen(61804)) {
            ThreadUtils.checkIsOnMainThread();
        }
        return bluetoothState;
    }

    /**
     *  Activates components required to detect Bluetooth devices and to enable
     *  BT SCO (audio is routed via BT SCO) for the headset profile. The end
     *  state will be HEADSET_UNAVAILABLE but a state machine has started which
     *  will start a state change sequence where the final outcome depends on
     *  if/when the BT headset is enabled.
     *  Example of state change sequence when start() is called while BT device
     *  is connected and enabled:
     *  UNINITIALIZED --> HEADSET_UNAVAILABLE --> HEADSET_AVAILABLE -->
     *  SCO_CONNECTING --> SCO_CONNECTED <==> audio is now routed via BT SCO.
     *  Note that the AppRTCAudioManager is also involved in driving this state
     *  change.
     */
    public void start() {
        if (!ListenerUtil.mutListener.listen(61805)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61806)) {
            logger.debug("start");
        }
        if (!ListenerUtil.mutListener.listen(61808)) {
            if (!hasPermission(apprtcContext, android.Manifest.permission.BLUETOOTH)) {
                if (!ListenerUtil.mutListener.listen(61807)) {
                    logger.warn("Process (pid=" + Process.myPid() + ") lacks BLUETOOTH permission");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61810)) {
            if (this.bluetoothState != State.UNINITIALIZED) {
                if (!ListenerUtil.mutListener.listen(61809)) {
                    logger.warn("Invalid BT state");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61811)) {
            this.bluetoothHeadset = null;
        }
        if (!ListenerUtil.mutListener.listen(61812)) {
            this.bluetoothDevice = null;
        }
        if (!ListenerUtil.mutListener.listen(61813)) {
            this.scoConnectionAttempts = 0;
        }
        if (!ListenerUtil.mutListener.listen(61814)) {
            // Get a handle to the default local Bluetooth adapter.
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (!ListenerUtil.mutListener.listen(61816)) {
            if (this.bluetoothAdapter == null) {
                if (!ListenerUtil.mutListener.listen(61815)) {
                    logger.warn("Device does not support Bluetooth");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61818)) {
            // Ensure that the device supports use of BT SCO audio for off call use cases.
            if (!this.audioManager.isBluetoothScoAvailableOffCall()) {
                if (!ListenerUtil.mutListener.listen(61817)) {
                    logger.error("Bluetooth SCO audio is not available off call");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61819)) {
            this.logBluetoothAdapterInfo(bluetoothAdapter);
        }
        if (!ListenerUtil.mutListener.listen(61821)) {
            // Hands-Free) proxy object and install a listener.
            if (!this.getBluetoothProfileProxy(this.apprtcContext, this.bluetoothServiceListener, BluetoothProfile.HEADSET)) {
                if (!ListenerUtil.mutListener.listen(61820)) {
                    logger.error("BluetoothAdapter.getProfileProxy(HEADSET) failed");
                }
                return;
            }
        }
        // Register receivers for BluetoothHeadset change notifications.
        final IntentFilter bluetoothHeadsetFilter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(61822)) {
            // Register receiver for change in connection state of the Headset profile.
            bluetoothHeadsetFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(61823)) {
            // Register receiver for change in audio connection state of the Headset profile.
            bluetoothHeadsetFilter.addAction(BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(61824)) {
            registerReceiver(bluetoothHeadsetReceiver, bluetoothHeadsetFilter);
        }
        if (!ListenerUtil.mutListener.listen(61825)) {
            logger.debug("HEADSET profile state: " + headsetStateToString(bluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET)));
        }
        if (!ListenerUtil.mutListener.listen(61826)) {
            logger.debug("Bluetooth proxy for headset profile has started");
        }
        if (!ListenerUtil.mutListener.listen(61827)) {
            this.bluetoothState = State.HEADSET_UNAVAILABLE;
        }
        if (!ListenerUtil.mutListener.listen(61828)) {
            logger.debug("start done: BT state=" + bluetoothState);
        }
    }

    /**
     *  Stops and closes all components related to Bluetooth audio.
     */
    public void stop() {
        if (!ListenerUtil.mutListener.listen(61829)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61830)) {
            unregisterReceiver(bluetoothHeadsetReceiver);
        }
        if (!ListenerUtil.mutListener.listen(61831)) {
            logger.debug("stop: BT state=" + bluetoothState);
        }
        if (!ListenerUtil.mutListener.listen(61841)) {
            if (bluetoothAdapter != null) {
                if (!ListenerUtil.mutListener.listen(61832)) {
                    // Stop BT SCO connection with remote device if needed.
                    stopScoAudio();
                }
                if (!ListenerUtil.mutListener.listen(61840)) {
                    // Close down remaining BT resources.
                    if (bluetoothState != State.UNINITIALIZED) {
                        if (!ListenerUtil.mutListener.listen(61833)) {
                            cancelTimer();
                        }
                        if (!ListenerUtil.mutListener.listen(61836)) {
                            if (bluetoothHeadset != null) {
                                if (!ListenerUtil.mutListener.listen(61834)) {
                                    bluetoothAdapter.closeProfileProxy(BluetoothProfile.HEADSET, bluetoothHeadset);
                                }
                                if (!ListenerUtil.mutListener.listen(61835)) {
                                    bluetoothHeadset = null;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(61837)) {
                            bluetoothAdapter = null;
                        }
                        if (!ListenerUtil.mutListener.listen(61838)) {
                            bluetoothDevice = null;
                        }
                        if (!ListenerUtil.mutListener.listen(61839)) {
                            bluetoothState = State.UNINITIALIZED;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61842)) {
            logger.debug("stop done: BT state=" + bluetoothState);
        }
    }

    /**
     *  Starts Bluetooth SCO connection with remote device.
     *  Note that the phone application always has the priority on the usage of the SCO connection
     *  for telephony. If this method is called while the phone is in call it will be ignored.
     *  Similarly, if a call is received or sent while an application is using the SCO connection,
     *  the connection will be lost for the application and NOT returned automatically when the call
     *  ends. Also note that: up to and including API version JELLY_BEAN_MR1, this method initiates a
     *  virtual voice call to the Bluetooth headset. After API version JELLY_BEAN_MR2 only a raw SCO
     *  audio connection is established.
     *  TODO(henrika): should we add support for virtual voice call to BT headset also for JBMR2 and
     *  higher. It might be required to initiates a virtual voice call since many devices do not
     *  accept SCO audio without a "call".
     */
    public boolean startScoAudio() {
        if (!ListenerUtil.mutListener.listen(61843)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61844)) {
            logger.debug("startSco: BT state=" + bluetoothState + ", " + "attempts: " + scoConnectionAttempts + ", " + "SCO is on: " + isScoOn());
        }
        if (!ListenerUtil.mutListener.listen(61851)) {
            if ((ListenerUtil.mutListener.listen(61849) ? (scoConnectionAttempts <= MAX_SCO_CONNECTION_ATTEMPTS) : (ListenerUtil.mutListener.listen(61848) ? (scoConnectionAttempts > MAX_SCO_CONNECTION_ATTEMPTS) : (ListenerUtil.mutListener.listen(61847) ? (scoConnectionAttempts < MAX_SCO_CONNECTION_ATTEMPTS) : (ListenerUtil.mutListener.listen(61846) ? (scoConnectionAttempts != MAX_SCO_CONNECTION_ATTEMPTS) : (ListenerUtil.mutListener.listen(61845) ? (scoConnectionAttempts == MAX_SCO_CONNECTION_ATTEMPTS) : (scoConnectionAttempts >= MAX_SCO_CONNECTION_ATTEMPTS))))))) {
                if (!ListenerUtil.mutListener.listen(61850)) {
                    logger.error("BT SCO connection fails - no more attempts");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(61853)) {
            if (bluetoothState != State.HEADSET_AVAILABLE) {
                if (!ListenerUtil.mutListener.listen(61852)) {
                    logger.error("BT SCO connection fails - no headset available");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(61854)) {
            // Start BT SCO channel and wait for ACTION_AUDIO_STATE_CHANGED.
            logger.debug("Starting Bluetooth SCO and waits for ACTION_AUDIO_STATE_CHANGED...");
        }
        if (!ListenerUtil.mutListener.listen(61855)) {
            // intent ACTION_SCO_AUDIO_STATE_UPDATED and wait for the state to be SCO_AUDIO_STATE_CONNECTED.
            this.bluetoothState = State.SCO_CONNECTING;
        }
        try {
            if (!ListenerUtil.mutListener.listen(61857)) {
                this.audioManager.startBluetoothSco();
            }
        } catch (RuntimeException e) {
            if (!ListenerUtil.mutListener.listen(61856)) {
                logger.error("Could not start bluetooth SCO", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(61858)) {
            this.scoConnectionAttempts++;
        }
        if (!ListenerUtil.mutListener.listen(61859)) {
            this.startTimer();
        }
        if (!ListenerUtil.mutListener.listen(61860)) {
            logger.debug("startScoAudio done: BT state=" + bluetoothState);
        }
        return true;
    }

    /**
     *  Stops Bluetooth SCO connection with remote device.
     */
    public void stopScoAudio() {
        if (!ListenerUtil.mutListener.listen(61861)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61862)) {
            logger.debug("stopScoAudio: BT state=" + bluetoothState + ", " + "SCO is on: " + isScoOn());
        }
        if (!ListenerUtil.mutListener.listen(61864)) {
            if ((ListenerUtil.mutListener.listen(61863) ? (bluetoothState != State.SCO_CONNECTING || bluetoothState != State.SCO_CONNECTED) : (bluetoothState != State.SCO_CONNECTING && bluetoothState != State.SCO_CONNECTED))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61865)) {
            cancelTimer();
        }
        if (!ListenerUtil.mutListener.listen(61866)) {
            audioManager.stopBluetoothSco();
        }
        if (!ListenerUtil.mutListener.listen(61867)) {
            bluetoothState = State.SCO_DISCONNECTING;
        }
        if (!ListenerUtil.mutListener.listen(61868)) {
            logger.debug("stopScoAudio done: BT state=" + bluetoothState);
        }
    }

    /**
     *  Use the BluetoothHeadset proxy object (controls the Bluetooth Headset
     *  Service via IPC) to update the list of connected devices for the HEADSET
     *  profile. The internal state will change to HEADSET_UNAVAILABLE or to
     *  HEADSET_AVAILABLE and |bluetoothDevice| will be mapped to the connected
     *  device if available.
     */
    public void updateDevice() {
        if (!ListenerUtil.mutListener.listen(61870)) {
            if ((ListenerUtil.mutListener.listen(61869) ? (bluetoothState == State.UNINITIALIZED && bluetoothHeadset == null) : (bluetoothState == State.UNINITIALIZED || bluetoothHeadset == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61871)) {
            logger.debug("updateDevice");
        }
        // is just a thin wrapper for a Bluetooth hardware address.
        List<BluetoothDevice> devices = bluetoothHeadset.getConnectedDevices();
        if (!ListenerUtil.mutListener.listen(61878)) {
            if (devices.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(61875)) {
                    bluetoothDevice = null;
                }
                if (!ListenerUtil.mutListener.listen(61876)) {
                    bluetoothState = State.HEADSET_UNAVAILABLE;
                }
                if (!ListenerUtil.mutListener.listen(61877)) {
                    logger.debug("No connected bluetooth headset");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61872)) {
                    // Always use first device is list. Android only supports one device.
                    bluetoothDevice = devices.get(0);
                }
                if (!ListenerUtil.mutListener.listen(61873)) {
                    bluetoothState = State.HEADSET_AVAILABLE;
                }
                if (!ListenerUtil.mutListener.listen(61874)) {
                    logger.debug("Connected bluetooth headset: " + "name=" + bluetoothDevice.getName() + ", " + "state=" + headsetStateToString(bluetoothHeadset.getConnectionState(bluetoothDevice)) + ", SCO audio=" + bluetoothHeadset.isAudioConnected(bluetoothDevice));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61879)) {
            logger.debug("updateDevice done: BT state=" + bluetoothState);
        }
    }

    /**
     *  Stubs for test mocks.
     */
    protected AudioManager getAudioManager(Context context) {
        return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    protected void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        if (!ListenerUtil.mutListener.listen(61880)) {
            apprtcContext.registerReceiver(receiver, filter);
        }
    }

    protected void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            if (!ListenerUtil.mutListener.listen(61882)) {
                apprtcContext.unregisterReceiver(receiver);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(61881)) {
                // receiver not registered
                logger.error("Could not unregister receiver", e);
            }
        }
    }

    protected boolean getBluetoothProfileProxy(Context context, BluetoothProfile.ServiceListener listener, int profile) {
        return bluetoothAdapter.getProfileProxy(context, listener, profile);
    }

    protected boolean hasPermission(Context context, String permission) {
        return apprtcContext.checkPermission(permission, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     *  Logs the state of the local Bluetooth adapter.
     */
    @SuppressLint("HardwareIds")
    protected void logBluetoothAdapterInfo(BluetoothAdapter localAdapter) {
        try {
            if (!ListenerUtil.mutListener.listen(61884)) {
                logger.debug("BluetoothAdapter: " + "enabled=" + localAdapter.isEnabled() + ", " + "state=" + adapterStateToString(localAdapter.getState()) + ", " + "name=" + localAdapter.getName() + ", " + "address=" + localAdapter.getAddress());
            }
            // Log the set of BluetoothDevice objects that are bonded (paired) to the local adapter.
            Set<BluetoothDevice> pairedDevices = localAdapter.getBondedDevices();
            if (!ListenerUtil.mutListener.listen(61888)) {
                if (!pairedDevices.isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(61885)) {
                        logger.debug("paired devices:");
                    }
                    if (!ListenerUtil.mutListener.listen(61887)) {
                        {
                            long _loopCounter743 = 0;
                            for (BluetoothDevice device : pairedDevices) {
                                ListenerUtil.loopListener.listen("_loopCounter743", ++_loopCounter743);
                                if (!ListenerUtil.mutListener.listen(61886)) {
                                    logger.debug(" name=" + device.getName() + ", address=" + device.getAddress());
                                }
                            }
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(61883)) {
                // some calls on localAdapter may cause SecurityExceptions on some devices
                logger.error("BT logging failed", e);
            }
        }
    }

    /**
     *  Ensures that the audio manager updates its list of available audio devices.
     */
    private void updateAudioDeviceState() {
        if (!ListenerUtil.mutListener.listen(61889)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61890)) {
            logger.debug("updateAudioDeviceState");
        }
        if (!ListenerUtil.mutListener.listen(61891)) {
            voipAudioManager.updateAudioDeviceState();
        }
    }

    /**
     *  Starts timer which times out after BLUETOOTH_SCO_TIMEOUT_MS milliseconds.
     */
    private void startTimer() {
        if (!ListenerUtil.mutListener.listen(61892)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61893)) {
            logger.debug("startTimer");
        }
        if (!ListenerUtil.mutListener.listen(61894)) {
            handler.postDelayed(bluetoothTimeoutRunnable, BLUETOOTH_SCO_TIMEOUT_MS);
        }
    }

    /**
     *  Cancels any outstanding timer tasks.
     */
    private void cancelTimer() {
        if (!ListenerUtil.mutListener.listen(61895)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61896)) {
            logger.debug("cancelTimer");
        }
        if (!ListenerUtil.mutListener.listen(61897)) {
            handler.removeCallbacks(bluetoothTimeoutRunnable);
        }
    }

    /**
     *  Called when start of the BT SCO channel takes too long time. Usually
     *  happens when the BT device has been turned on during an ongoing call.
     */
    private void bluetoothTimeout() {
        if (!ListenerUtil.mutListener.listen(61898)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61900)) {
            if ((ListenerUtil.mutListener.listen(61899) ? (bluetoothState == State.UNINITIALIZED && bluetoothHeadset == null) : (bluetoothState == State.UNINITIALIZED || bluetoothHeadset == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61901)) {
            logger.debug("bluetoothTimeout: BT state=" + bluetoothState + ", " + "attempts: " + scoConnectionAttempts + ", " + "SCO is on: " + isScoOn());
        }
        if (!ListenerUtil.mutListener.listen(61902)) {
            if (bluetoothState != State.SCO_CONNECTING) {
                return;
            }
        }
        // Bluetooth SCO should be connecting; check the latest result.
        boolean scoConnected = false;
        final List<BluetoothDevice> devices = bluetoothHeadset.getConnectedDevices();
        if (!ListenerUtil.mutListener.listen(61913)) {
            if ((ListenerUtil.mutListener.listen(61907) ? (devices.size() >= 0) : (ListenerUtil.mutListener.listen(61906) ? (devices.size() <= 0) : (ListenerUtil.mutListener.listen(61905) ? (devices.size() < 0) : (ListenerUtil.mutListener.listen(61904) ? (devices.size() != 0) : (ListenerUtil.mutListener.listen(61903) ? (devices.size() == 0) : (devices.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(61908)) {
                    bluetoothDevice = devices.get(0);
                }
                if (!ListenerUtil.mutListener.listen(61912)) {
                    if (bluetoothHeadset.isAudioConnected(bluetoothDevice)) {
                        if (!ListenerUtil.mutListener.listen(61910)) {
                            logger.debug("SCO connected with " + bluetoothDevice.getName());
                        }
                        if (!ListenerUtil.mutListener.listen(61911)) {
                            scoConnected = true;
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(61909)) {
                            logger.debug("SCO is not connected with " + bluetoothDevice.getName());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61918)) {
            if (scoConnected) {
                if (!ListenerUtil.mutListener.listen(61916)) {
                    // We thought BT had timed out, but it's actually on; updating state.
                    bluetoothState = State.SCO_CONNECTED;
                }
                if (!ListenerUtil.mutListener.listen(61917)) {
                    scoConnectionAttempts = 0;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61914)) {
                    // Give up and "cancel" our request by calling stopBluetoothSco().
                    logger.warn("BT failed to connect after timeout");
                }
                if (!ListenerUtil.mutListener.listen(61915)) {
                    stopScoAudio();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61919)) {
            updateAudioDeviceState();
        }
        if (!ListenerUtil.mutListener.listen(61920)) {
            logger.debug("bluetoothTimeout done: BT state=" + bluetoothState);
        }
    }

    /**
     *  Checks whether audio uses Bluetooth SCO.
     */
    private boolean isScoOn() {
        return audioManager.isBluetoothScoOn();
    }

    /**
     *  Converts BluetoothAdapter states into local string representations.
     */
    private String adapterStateToString(int state) {
        switch(state) {
            case BluetoothAdapter.STATE_DISCONNECTED:
                return "DISCONNECTED";
            case BluetoothAdapter.STATE_CONNECTED:
                return "CONNECTED";
            case BluetoothAdapter.STATE_CONNECTING:
                return "CONNECTING";
            case BluetoothAdapter.STATE_DISCONNECTING:
                return "DISCONNECTING";
            case BluetoothAdapter.STATE_OFF:
                return "OFF";
            case BluetoothAdapter.STATE_ON:
                return "ON";
            case BluetoothAdapter.STATE_TURNING_OFF:
                // attempt graceful disconnection of any remote links.
                return "TURNING_OFF";
            case BluetoothAdapter.STATE_TURNING_ON:
                // for STATE_ON before attempting to use the adapter.
                return "TURNING_ON";
            default:
                return "INVALID";
        }
    }

    /**
     *  Converts BluetoothHeadset states into local string representations.
     */
    private String headsetStateToString(int state) {
        switch(state) {
            case BluetoothHeadset.STATE_CONNECTING:
                return "CONNECTING";
            case BluetoothHeadset.STATE_AUDIO_CONNECTING:
                return "A_CONNECTING";
            case BluetoothHeadset.STATE_CONNECTED:
                return "CONNECTED";
            case BluetoothHeadset.STATE_AUDIO_CONNECTED:
                return "A_CONNECTED";
            case BluetoothHeadset.STATE_DISCONNECTING:
                return "DISCONNECTING";
            case BluetoothHeadset.STATE_DISCONNECTED:
                return "DISCONNECTED";
            case BluetoothHeadset.STATE_AUDIO_DISCONNECTED:
                return "A_DISCONNECTED";
            default:
                return "INVALID_STATE";
        }
    }
}
