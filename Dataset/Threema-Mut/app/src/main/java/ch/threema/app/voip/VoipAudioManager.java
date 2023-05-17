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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.ThreadUtils;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.notifications.BackgroundErrorNotification;
import ch.threema.app.voip.listeners.VoipAudioManagerListener;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.voip.util.AppRTCUtils;
import java8.util.concurrent.CompletableFuture;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * VoipAudioManager manages all audio related parts of the Threema VoIP calls.
 */
public class VoipAudioManager {

    private static final Logger logger = LoggerFactory.getLogger(VoipAudioManager.class);

    private static final String TAG = "VoipAudioManager";

    /**
     *  AudioDevice is the names of possible audio devices that we currently
     *  support.
     */
    public enum AudioDevice {

        SPEAKER_PHONE, WIRED_HEADSET, EARPIECE, BLUETOOTH, NONE
    }

    /**
     *  AudioManager state.
     */
    public enum AudioManagerState {

        UNINITIALIZED, PREINITIALIZED, RUNNING
    }

    private final Context apprtcContext;

    private CompletableFuture<AudioManager> audioManagerFuture;

    private AudioManager audioManager;

    private AudioManagerState amState;

    private int savedAudioMode = AudioManager.MODE_INVALID;

    private boolean savedIsSpeakerPhoneOn = false;

    private boolean savedIsMicrophoneMute = false;

    private boolean hasWiredHeadset = false;

    private boolean micEnabled = true;

    // (e.g. tablets) or earpiece for devices with telephony features.
    private AudioDevice defaultAudioDevice;

    // See |userSelectedAudioDevice| for details.
    @Nullable
    private AudioDevice selectedAudioDevice;

    // selection scheme.
    private AudioDevice userSelectedAudioDevice;

    // available, far from ear <=> use speaker phone).
    private VoipProximitySensor proximitySensor = null;

    // Handles all tasks related to Bluetooth headset devices.
    private final VoipBluetoothManager bluetoothManager;

    // avoid duplicate elements.
    @NonNull
    private HashSet<AudioDevice> audioDevices = new HashSet<>();

    // Broadcast receiver for wired headset intent broadcasts.
    private BroadcastReceiver wiredHeadsetReceiver;

    // Callback method for changes in audio focus.
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;

    /**
     *  This method is called when the proximity sensor reports a state change,
     *  e.g. from "NEAR to FAR" or from "FAR to NEAR".
     */
    private void onProximitySensorChangedState() {
        if (!ListenerUtil.mutListener.listen(61560)) {
            // available audio devices.
            if ((ListenerUtil.mutListener.listen(61556) ? ((ListenerUtil.mutListener.listen(61555) ? ((ListenerUtil.mutListener.listen(61554) ? (audioDevices.size() >= 2) : (ListenerUtil.mutListener.listen(61553) ? (audioDevices.size() <= 2) : (ListenerUtil.mutListener.listen(61552) ? (audioDevices.size() > 2) : (ListenerUtil.mutListener.listen(61551) ? (audioDevices.size() < 2) : (ListenerUtil.mutListener.listen(61550) ? (audioDevices.size() != 2) : (audioDevices.size() == 2)))))) || audioDevices.contains(VoipAudioManager.AudioDevice.EARPIECE)) : ((ListenerUtil.mutListener.listen(61554) ? (audioDevices.size() >= 2) : (ListenerUtil.mutListener.listen(61553) ? (audioDevices.size() <= 2) : (ListenerUtil.mutListener.listen(61552) ? (audioDevices.size() > 2) : (ListenerUtil.mutListener.listen(61551) ? (audioDevices.size() < 2) : (ListenerUtil.mutListener.listen(61550) ? (audioDevices.size() != 2) : (audioDevices.size() == 2)))))) && audioDevices.contains(VoipAudioManager.AudioDevice.EARPIECE))) || audioDevices.contains(VoipAudioManager.AudioDevice.SPEAKER_PHONE)) : ((ListenerUtil.mutListener.listen(61555) ? ((ListenerUtil.mutListener.listen(61554) ? (audioDevices.size() >= 2) : (ListenerUtil.mutListener.listen(61553) ? (audioDevices.size() <= 2) : (ListenerUtil.mutListener.listen(61552) ? (audioDevices.size() > 2) : (ListenerUtil.mutListener.listen(61551) ? (audioDevices.size() < 2) : (ListenerUtil.mutListener.listen(61550) ? (audioDevices.size() != 2) : (audioDevices.size() == 2)))))) || audioDevices.contains(VoipAudioManager.AudioDevice.EARPIECE)) : ((ListenerUtil.mutListener.listen(61554) ? (audioDevices.size() >= 2) : (ListenerUtil.mutListener.listen(61553) ? (audioDevices.size() <= 2) : (ListenerUtil.mutListener.listen(61552) ? (audioDevices.size() > 2) : (ListenerUtil.mutListener.listen(61551) ? (audioDevices.size() < 2) : (ListenerUtil.mutListener.listen(61550) ? (audioDevices.size() != 2) : (audioDevices.size() == 2)))))) && audioDevices.contains(VoipAudioManager.AudioDevice.EARPIECE))) && audioDevices.contains(VoipAudioManager.AudioDevice.SPEAKER_PHONE)))) {
                if (!ListenerUtil.mutListener.listen(61559)) {
                    if (proximitySensor.sensorReportsNearState()) {
                        if (!ListenerUtil.mutListener.listen(61558)) {
                            // or "something is covering the light sensor".
                            setAudioDeviceInternal(VoipAudioManager.AudioDevice.EARPIECE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(61557)) {
                            // "the light sensor is no longer covered".
                            setAudioDeviceInternal(VoipAudioManager.AudioDevice.SPEAKER_PHONE);
                        }
                    }
                }
            }
        }
    }

    /* Receiver which handles changes in wired headset availability. */
    private class WiredHeadsetReceiver extends BroadcastReceiver {

        private static final int STATE_UNPLUGGED = 0;

        private static final int STATE_PLUGGED = 1;

        private static final int HAS_NO_MIC = 0;

        private static final int HAS_MIC = 1;

        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra("state", STATE_UNPLUGGED);
            int microphone = intent.getIntExtra("microphone", HAS_NO_MIC);
            String name = intent.getStringExtra("name");
            if (!ListenerUtil.mutListener.listen(61571)) {
                logger.debug("WiredHeadsetReceiver.onReceive" + AppRTCUtils.getThreadInfo() + ": " + "a=" + intent.getAction() + ", s=" + ((ListenerUtil.mutListener.listen(61565) ? (state >= STATE_UNPLUGGED) : (ListenerUtil.mutListener.listen(61564) ? (state <= STATE_UNPLUGGED) : (ListenerUtil.mutListener.listen(61563) ? (state > STATE_UNPLUGGED) : (ListenerUtil.mutListener.listen(61562) ? (state < STATE_UNPLUGGED) : (ListenerUtil.mutListener.listen(61561) ? (state != STATE_UNPLUGGED) : (state == STATE_UNPLUGGED)))))) ? "unplugged" : "plugged") + ", m=" + ((ListenerUtil.mutListener.listen(61570) ? (microphone >= HAS_MIC) : (ListenerUtil.mutListener.listen(61569) ? (microphone <= HAS_MIC) : (ListenerUtil.mutListener.listen(61568) ? (microphone > HAS_MIC) : (ListenerUtil.mutListener.listen(61567) ? (microphone < HAS_MIC) : (ListenerUtil.mutListener.listen(61566) ? (microphone != HAS_MIC) : (microphone == HAS_MIC)))))) ? "mic" : "no mic") + ", n=" + name + ", sb=" + isInitialStickyBroadcast());
            }
            if (!ListenerUtil.mutListener.listen(61577)) {
                hasWiredHeadset = ((ListenerUtil.mutListener.listen(61576) ? (state >= STATE_PLUGGED) : (ListenerUtil.mutListener.listen(61575) ? (state <= STATE_PLUGGED) : (ListenerUtil.mutListener.listen(61574) ? (state > STATE_PLUGGED) : (ListenerUtil.mutListener.listen(61573) ? (state < STATE_PLUGGED) : (ListenerUtil.mutListener.listen(61572) ? (state != STATE_PLUGGED) : (state == STATE_PLUGGED)))))));
            }
            if (!ListenerUtil.mutListener.listen(61578)) {
                updateAudioDeviceState();
            }
        }
    }

    public static VoipAudioManager create(Context context, CompletableFuture<Void> audioFocusAbandonedFuture) {
        return new VoipAudioManager(context, audioFocusAbandonedFuture);
    }

    private VoipAudioManager(Context context, CompletableFuture<Void> audioFocusAbandonedFuture) {
        if (!ListenerUtil.mutListener.listen(61579)) {
            logger.info("Initializing");
        }
        if (!ListenerUtil.mutListener.listen(61580)) {
            ThreadUtils.checkIsOnMainThread();
        }
        this.apprtcContext = context;
        if (!ListenerUtil.mutListener.listen(61581)) {
            this.audioManagerFuture = audioFocusAbandonedFuture.thenApply(x -> {
                return (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            });
        }
        this.bluetoothManager = VoipBluetoothManager.create(context, this);
        if (!ListenerUtil.mutListener.listen(61582)) {
            this.wiredHeadsetReceiver = new WiredHeadsetReceiver();
        }
        if (!ListenerUtil.mutListener.listen(61583)) {
            this.amState = AudioManagerState.UNINITIALIZED;
        }
        if (!ListenerUtil.mutListener.listen(61586)) {
            // Set default audio device
            if (this.hasEarpiece()) {
                if (!ListenerUtil.mutListener.listen(61585)) {
                    this.defaultAudioDevice = AudioDevice.EARPIECE;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61584)) {
                    this.defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61588)) {
            // Note that, the sensor will not be active until start() has been called.
            this.proximitySensor = VoipProximitySensor.create(context, new Runnable() {

                // or removes his hand from the device.
                public void run() {
                    if (!ListenerUtil.mutListener.listen(61587)) {
                        onProximitySensorChangedState();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(61589)) {
            logger.info("defaultAudioDevice: {}", defaultAudioDevice);
        }
        if (!ListenerUtil.mutListener.listen(61590)) {
            AppRTCUtils.logDeviceInfo(TAG);
        }
    }

    public void start() {
        if (!ListenerUtil.mutListener.listen(61591)) {
            logger.debug("start");
        }
        if (!ListenerUtil.mutListener.listen(61592)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61594)) {
            if (amState == AudioManagerState.RUNNING) {
                if (!ListenerUtil.mutListener.listen(61593)) {
                    logger.error("AudioManager is already active");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61595)) {
            logger.debug("AudioManager starts...");
        }
        if (!ListenerUtil.mutListener.listen(61596)) {
            amState = AudioManagerState.RUNNING;
        }
        // Store current audio state so we can restore it when stop() is called.
        try {
            if (!ListenerUtil.mutListener.listen(61602)) {
                audioManager = audioManagerFuture.get();
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(61597)) {
                logger.error("AudioManager Future error", e);
            }
            if (!ListenerUtil.mutListener.listen(61598)) {
                BackgroundErrorNotification.showNotification(ThreemaApplication.getAppContext(), "AudioManager initialization error", "AudioManager Future failed", TAG, true, e);
            }
            if (!ListenerUtil.mutListener.listen(61599)) {
                // Restore interrupted state...
                Thread.currentThread().interrupt();
            }
        } catch (ExecutionException e) {
            if (!ListenerUtil.mutListener.listen(61600)) {
                logger.error("AudioManager Future error", e);
            }
            if (!ListenerUtil.mutListener.listen(61601)) {
                BackgroundErrorNotification.showNotification(ThreemaApplication.getAppContext(), "AudioManager initialization error", "AudioManager Future failed", TAG, true, e);
            }
        }
        if (!ListenerUtil.mutListener.listen(61603)) {
            savedAudioMode = audioManager.getMode();
        }
        if (!ListenerUtil.mutListener.listen(61604)) {
            savedIsSpeakerPhoneOn = audioManager.isSpeakerphoneOn();
        }
        if (!ListenerUtil.mutListener.listen(61605)) {
            savedIsMicrophoneMute = audioManager.isMicrophoneMute();
        }
        if (!ListenerUtil.mutListener.listen(61606)) {
            hasWiredHeadset = hasWiredHeadset();
        }
        if (!ListenerUtil.mutListener.listen(61620)) {
            // Create an AudioManager.OnAudioFocusChangeListener instance.
            audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {

                // unknown amount of time.
                @Override
                public void onAudioFocusChange(int focusChange) {
                    String typeOfChange;
                    switch(focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                            if (!ListenerUtil.mutListener.listen(61607)) {
                                logger.info("Audio Focus gain");
                            }
                            typeOfChange = "AUDIOFOCUS_GAIN";
                            if (!ListenerUtil.mutListener.listen(61609)) {
                                VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {

                                    @Override
                                    public void handle(VoipAudioManagerListener listener) {
                                        if (!ListenerUtil.mutListener.listen(61608)) {
                                            listener.onAudioFocusGained();
                                        }
                                    }
                                });
                            }
                            break;
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                            if (!ListenerUtil.mutListener.listen(61610)) {
                                logger.info("Audio Focus gain transient");
                            }
                            typeOfChange = "AUDIOFOCUS_GAIN_TRANSIENT";
                            if (!ListenerUtil.mutListener.listen(61612)) {
                                VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {

                                    @Override
                                    public void handle(VoipAudioManagerListener listener) {
                                        if (!ListenerUtil.mutListener.listen(61611)) {
                                            listener.onAudioFocusGained();
                                        }
                                    }
                                });
                            }
                            break;
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE:
                            if (!ListenerUtil.mutListener.listen(61613)) {
                                logger.info("Audio Focus gain transient exclusive");
                            }
                            typeOfChange = "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE";
                            if (!ListenerUtil.mutListener.listen(61615)) {
                                VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {

                                    @Override
                                    public void handle(VoipAudioManagerListener listener) {
                                        if (!ListenerUtil.mutListener.listen(61614)) {
                                            listener.onAudioFocusGained();
                                        }
                                    }
                                });
                            }
                            break;
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                            typeOfChange = "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK";
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS:
                            typeOfChange = "AUDIOFOCUS_LOSS";
                            if (!ListenerUtil.mutListener.listen(61616)) {
                                logger.info("Audio Focus loss");
                            }
                            /* TODO: Currently disabled because of side effects
						VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {
							@Override
							public void handle(VoipAudioManagerListener listener) {
								listener.onAudioFocusLost(false);
							}
						});
						*/
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            if (!ListenerUtil.mutListener.listen(61617)) {
                                logger.info("Audio Focus loss transient");
                            }
                            typeOfChange = "AUDIOFOCUS_LOSS_TRANSIENT";
                            /* TODO: Currently disabled because of side effects
						VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {
							@Override
							public void handle(VoipAudioManagerListener listener) {
								listener.onAudioFocusLost(true);
							}
						});
						 */
                            break;
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            if (!ListenerUtil.mutListener.listen(61618)) {
                                logger.info("Audio Focus loss transient can duck");
                            }
                            typeOfChange = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
                            // we continue in case of ducking
                            break;
                        default:
                            typeOfChange = "AUDIOFOCUS_INVALID";
                            break;
                    }
                    if (!ListenerUtil.mutListener.listen(61619)) {
                        logger.debug("onAudioFocusChange: " + typeOfChange);
                    }
                }
            };
        }
        // Request audio playout focus (without ducking) and install listener for changes in focus.
        final int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_VOICE_CALL, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE);
        if (!ListenerUtil.mutListener.listen(61623)) {
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                if (!ListenerUtil.mutListener.listen(61622)) {
                    logger.info("Audio focus request granted for VOICE_CALL streams");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61621)) {
                    logger.info("Audio focus request for VOICE_CALL failed");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61624)) {
            // best possible VoIP performance.
            this.audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        }
        if (!ListenerUtil.mutListener.listen(61625)) {
            // Always disable microphone mute during a WebRTC call.
            this.setMicrophoneMute(false);
        }
        if (!ListenerUtil.mutListener.listen(61626)) {
            // Set initial device states.
            this.userSelectedAudioDevice = AudioDevice.NONE;
        }
        if (!ListenerUtil.mutListener.listen(61627)) {
            this.selectedAudioDevice = AudioDevice.NONE;
        }
        if (!ListenerUtil.mutListener.listen(61628)) {
            this.audioDevices.clear();
        }
        if (!ListenerUtil.mutListener.listen(61629)) {
            // detection of new (enabled) BT devices.
            this.bluetoothManager.start();
        }
        if (!ListenerUtil.mutListener.listen(61630)) {
            // the proximity sensor.
            this.updateAudioDeviceState();
        }
        if (!ListenerUtil.mutListener.listen(61631)) {
            // Register receiver for broadcast intents related to adding/removing a wired headset.
            this.registerReceiver(this.wiredHeadsetReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
        }
        if (!ListenerUtil.mutListener.listen(61632)) {
            logger.debug("AudioManager started");
        }
    }

    public void stop() {
        if (!ListenerUtil.mutListener.listen(61633)) {
            logger.debug("stop");
        }
        if (!ListenerUtil.mutListener.listen(61634)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61636)) {
            if (this.amState != AudioManagerState.RUNNING) {
                if (!ListenerUtil.mutListener.listen(61635)) {
                    logger.error("Trying to stop AudioManager in incorrect state: " + amState);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61637)) {
            this.amState = AudioManagerState.UNINITIALIZED;
        }
        if (!ListenerUtil.mutListener.listen(61638)) {
            this.unregisterReceiver(this.wiredHeadsetReceiver);
        }
        if (!ListenerUtil.mutListener.listen(61639)) {
            this.bluetoothManager.stop();
        }
        if (!ListenerUtil.mutListener.listen(61640)) {
            // Restore previously stored audio states.
            this.setSpeakerphoneOn(this.savedIsSpeakerPhoneOn);
        }
        if (!ListenerUtil.mutListener.listen(61641)) {
            this.setMicrophoneMute(this.savedIsMicrophoneMute);
        }
        if (!ListenerUtil.mutListener.listen(61642)) {
            this.audioManager.setMode(this.savedAudioMode);
        }
        if (!ListenerUtil.mutListener.listen(61643)) {
            // Abandon audio focus. Gives the previous focus owner, if any, focus.
            this.audioManager.abandonAudioFocus(this.audioFocusChangeListener);
        }
        if (!ListenerUtil.mutListener.listen(61644)) {
            this.audioFocusChangeListener = null;
        }
        if (!ListenerUtil.mutListener.listen(61645)) {
            logger.info("Abandoned audio focus for VOICE_CALL streams");
        }
        if (!ListenerUtil.mutListener.listen(61648)) {
            if (this.proximitySensor != null) {
                if (!ListenerUtil.mutListener.listen(61646)) {
                    this.proximitySensor.stop();
                }
                if (!ListenerUtil.mutListener.listen(61647)) {
                    this.proximitySensor = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61649)) {
            logger.info("Stopped");
        }
    }

    /**
     *  Changes selection of the currently active audio device.
     */
    private void setAudioDeviceInternal(AudioDevice device) {
        if (!ListenerUtil.mutListener.listen(61650)) {
            logger.info("Changing audio device to {}", device);
        }
        if (!ListenerUtil.mutListener.listen(61653)) {
            if (!audioDevices.contains(device)) {
                if (!ListenerUtil.mutListener.listen(61651)) {
                    logger.error("Trying to call setAudioDeviceInternal with an invalid device:");
                }
                if (!ListenerUtil.mutListener.listen(61652)) {
                    logger.error("{} is not contained in {}", device, audioDevices);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61659)) {
            switch(device) {
                case SPEAKER_PHONE:
                    if (!ListenerUtil.mutListener.listen(61654)) {
                        setSpeakerphoneOn(true);
                    }
                    break;
                case EARPIECE:
                    if (!ListenerUtil.mutListener.listen(61655)) {
                        setSpeakerphoneOn(false);
                    }
                    break;
                case WIRED_HEADSET:
                    if (!ListenerUtil.mutListener.listen(61656)) {
                        setSpeakerphoneOn(false);
                    }
                    break;
                case BLUETOOTH:
                    if (!ListenerUtil.mutListener.listen(61657)) {
                        setSpeakerphoneOn(false);
                    }
                    break;
                case NONE:
                default:
                    if (!ListenerUtil.mutListener.listen(61658)) {
                        logger.error("Invalid audio device selection");
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(61660)) {
            selectedAudioDevice = device;
        }
    }

    /**
     *  Changes selection of the currently active audio device.
     */
    public void selectAudioDevice(AudioDevice device) {
        if (!ListenerUtil.mutListener.listen(61661)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61663)) {
            if (!this.audioDevices.contains(device)) {
                if (!ListenerUtil.mutListener.listen(61662)) {
                    logger.error("Can not select " + device + " from available " + this.audioDevices);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61664)) {
            this.userSelectedAudioDevice = device;
        }
        if (!ListenerUtil.mutListener.listen(61665)) {
            this.updateAudioDeviceState();
        }
    }

    public void setMicEnabled(boolean micEnabled) {
        if (!ListenerUtil.mutListener.listen(61669)) {
            if (this.micEnabled != micEnabled) {
                if (!ListenerUtil.mutListener.listen(61666)) {
                    this.micEnabled = micEnabled;
                }
                if (!ListenerUtil.mutListener.listen(61668)) {
                    // Notify listeners
                    VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {

                        @Override
                        public void handle(VoipAudioManagerListener listener) {
                            if (!ListenerUtil.mutListener.listen(61667)) {
                                listener.onMicEnabledChanged(micEnabled);
                            }
                        }
                    });
                }
            }
        }
    }

    /**
     *  Notify listeners of the VoipAudioManagerListener.
     */
    public void requestAudioManagerNotify() {
        if (!ListenerUtil.mutListener.listen(61671)) {
            VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {

                @Override
                public void handle(VoipAudioManagerListener listener) {
                    if (!ListenerUtil.mutListener.listen(61670)) {
                        listener.onAudioDeviceChanged(selectedAudioDevice, audioDevices);
                    }
                }
            });
        }
    }

    public void requestMicEnabledNotify() {
        if (!ListenerUtil.mutListener.listen(61673)) {
            VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {

                @Override
                public void handle(VoipAudioManagerListener listener) {
                    if (!ListenerUtil.mutListener.listen(61672)) {
                        listener.onMicEnabledChanged(micEnabled);
                    }
                }
            });
        }
    }

    /**
     *  Return whether the specified device is available.
     */
    public boolean hasAudioDevice(AudioDevice device) {
        return this.audioDevices.contains(device);
    }

    /**
     *  Helper method for receiver registration.
     */
    private void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        if (!ListenerUtil.mutListener.listen(61674)) {
            apprtcContext.registerReceiver(receiver, filter);
        }
    }

    /**
     *  Helper method for unregistration of an existing receiver.
     */
    private void unregisterReceiver(BroadcastReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(61675)) {
            apprtcContext.unregisterReceiver(receiver);
        }
    }

    /**
     *  Sets the speaker phone mode.
     */
    private void setSpeakerphoneOn(boolean on) {
        boolean wasOn = audioManager.isSpeakerphoneOn();
        if (!ListenerUtil.mutListener.listen(61676)) {
            if (wasOn == on) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61677)) {
            audioManager.setSpeakerphoneOn(on);
        }
    }

    /**
     *  Sets the microphone mute state.
     */
    private void setMicrophoneMute(boolean on) {
        boolean wasMuted = audioManager.isMicrophoneMute();
        if (!ListenerUtil.mutListener.listen(61678)) {
            if (wasMuted == on) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61679)) {
            logger.info("{} microphone", on ? "Mute" : "Unmute");
        }
        if (!ListenerUtil.mutListener.listen(61680)) {
            audioManager.setMicrophoneMute(on);
        }
    }

    /**
     *  Checks whether the device has an earpiece.
     *  This should be the case if the telephony feature is available.
     */
    private boolean hasEarpiece() {
        return apprtcContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
    }

    /**
     *  Checks whether a wired headset is connected or not.
     *  This is not a valid indication that audio playback is actually over
     *  the wired headset as audio routing depends on other conditions. We
     *  only use it as an early indicator (during initialization) of an attached
     *  wired headset.
     */
    @Deprecated
    private boolean hasWiredHeadset() {
        return audioManager.isWiredHeadsetOn();
    }

    /**
     *  Updates list of possible audio devices and make new device selection.
     */
    public synchronized void updateAudioDeviceState() {
        if (!ListenerUtil.mutListener.listen(61681)) {
            ThreadUtils.checkIsOnMainThread();
        }
        if (!ListenerUtil.mutListener.listen(61682)) {
            logger.debug("Updating audio device state, initial state: wired_headset={}, bt_state={}, available={}, selected={}, user_selected={}", this.hasWiredHeadset, this.bluetoothManager.getState(), this.audioDevices, this.selectedAudioDevice, this.userSelectedAudioDevice);
        }
        // Query for available audio devices
        final HashSet<AudioDevice> newAudioDevices = this.queryAvailableAudioDevices();
        // Store state which is set to true if the device list has changed.
        boolean audioDeviceSetUpdated = !this.audioDevices.equals(newAudioDevices);
        if (!ListenerUtil.mutListener.listen(61683)) {
            // Update the existing audio device set.
            this.audioDevices = newAudioDevices;
        }
        if (!ListenerUtil.mutListener.listen(61684)) {
            // Correct user selected audio devices if needed
            this.validateUserSelection();
        }
        if (!ListenerUtil.mutListener.listen(61685)) {
            // Start bluetooth stack if necessary
            audioDeviceSetUpdated = this.initBluetooth(audioDeviceSetUpdated);
        }
        // Update selected audio device.
        AudioDevice newAudioDevice;
        if (this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTED) {
            // If bluetooth connection is active, switch over to it
            newAudioDevice = AudioDevice.BLUETOOTH;
        } else if (this.hasWiredHeadset) {
            // If there's a wired headset, set this as default device
            newAudioDevice = AudioDevice.WIRED_HEADSET;
        } else {
            // depending on device configuration.
            newAudioDevice = this.defaultAudioDevice;
        }
        switch(this.userSelectedAudioDevice) {
            case BLUETOOTH:
                if (bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTED) {
                    // an active SCO channel must also be up and running.
                    newAudioDevice = AudioDevice.BLUETOOTH;
                }
                break;
            case EARPIECE:
            case SPEAKER_PHONE:
            case WIRED_HEADSET:
                newAudioDevice = this.userSelectedAudioDevice;
                break;
            case NONE:
                break;
            default:
                if (!ListenerUtil.mutListener.listen(61686)) {
                    logger.error(": Invalid user selected audio device: " + this.userSelectedAudioDevice);
                }
        }
        if (!ListenerUtil.mutListener.listen(61692)) {
            // Switch to new device but only if there has been any changes.
            if ((ListenerUtil.mutListener.listen(61687) ? (newAudioDevice != this.selectedAudioDevice && audioDeviceSetUpdated) : (newAudioDevice != this.selectedAudioDevice || audioDeviceSetUpdated))) {
                if (!ListenerUtil.mutListener.listen(61688)) {
                    // Do the required device switch.
                    this.setAudioDeviceInternal(newAudioDevice);
                }
                if (!ListenerUtil.mutListener.listen(61689)) {
                    logger.info("New device status: available={}, selected={}", this.audioDevices, newAudioDevice);
                }
                if (!ListenerUtil.mutListener.listen(61691)) {
                    // Notify listeners
                    VoipListenerManager.audioManagerListener.handle(new ListenerManager.HandleListener<VoipAudioManagerListener>() {

                        @Override
                        public void handle(VoipAudioManagerListener listener) {
                            if (!ListenerUtil.mutListener.listen(61690)) {
                                listener.onAudioDeviceChanged(selectedAudioDevice, audioDevices);
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61693)) {
            logger.debug("Done updating audio device state");
        }
    }

    private boolean initBluetooth(boolean audioDeviceSetUpdated) {
        // user did not select any output device.
        boolean needBluetoothAudioStart = (ListenerUtil.mutListener.listen(61695) ? (this.bluetoothManager.getState() == VoipBluetoothManager.State.HEADSET_AVAILABLE || ((ListenerUtil.mutListener.listen(61694) ? (this.userSelectedAudioDevice == AudioDevice.NONE && this.userSelectedAudioDevice == AudioDevice.BLUETOOTH) : (this.userSelectedAudioDevice == AudioDevice.NONE || this.userSelectedAudioDevice == AudioDevice.BLUETOOTH)))) : (this.bluetoothManager.getState() == VoipBluetoothManager.State.HEADSET_AVAILABLE && ((ListenerUtil.mutListener.listen(61694) ? (this.userSelectedAudioDevice == AudioDevice.NONE && this.userSelectedAudioDevice == AudioDevice.BLUETOOTH) : (this.userSelectedAudioDevice == AudioDevice.NONE || this.userSelectedAudioDevice == AudioDevice.BLUETOOTH)))));
        // Bluetooth SCO connection is established or in the process.
        boolean needBluetoothAudioStop = (ListenerUtil.mutListener.listen(61698) ? (((ListenerUtil.mutListener.listen(61696) ? (this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTED && this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTING) : (this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTED || this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTING))) || ((ListenerUtil.mutListener.listen(61697) ? (this.userSelectedAudioDevice != AudioDevice.NONE || this.userSelectedAudioDevice != AudioDevice.BLUETOOTH) : (this.userSelectedAudioDevice != AudioDevice.NONE && this.userSelectedAudioDevice != AudioDevice.BLUETOOTH)))) : (((ListenerUtil.mutListener.listen(61696) ? (this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTED && this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTING) : (this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTED || this.bluetoothManager.getState() == VoipBluetoothManager.State.SCO_CONNECTING))) && ((ListenerUtil.mutListener.listen(61697) ? (this.userSelectedAudioDevice != AudioDevice.NONE || this.userSelectedAudioDevice != AudioDevice.BLUETOOTH) : (this.userSelectedAudioDevice != AudioDevice.NONE && this.userSelectedAudioDevice != AudioDevice.BLUETOOTH)))));
        if (!ListenerUtil.mutListener.listen(61700)) {
            if (this.audioDevices.contains(AudioDevice.BLUETOOTH)) {
                if (!ListenerUtil.mutListener.listen(61699)) {
                    logger.debug("Need BT audio: start=" + needBluetoothAudioStart + ", " + "stop=" + needBluetoothAudioStop + ", " + "BT state=" + this.bluetoothManager.getState());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61703)) {
            // Start or stop Bluetooth SCO connection given states set earlier.
            if (needBluetoothAudioStop) {
                if (!ListenerUtil.mutListener.listen(61701)) {
                    this.bluetoothManager.stopScoAudio();
                }
                if (!ListenerUtil.mutListener.listen(61702)) {
                    this.bluetoothManager.updateDevice();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61708)) {
            if ((ListenerUtil.mutListener.listen(61704) ? (needBluetoothAudioStart || !needBluetoothAudioStop) : (needBluetoothAudioStart && !needBluetoothAudioStop))) {
                if (!ListenerUtil.mutListener.listen(61707)) {
                    // Attempt to start Bluetooth SCO audio (takes a few second to start).
                    if (!this.bluetoothManager.startScoAudio()) {
                        if (!ListenerUtil.mutListener.listen(61705)) {
                            // Remove BLUETOOTH from list of available devices since SCO failed.
                            this.audioDevices.remove(AudioDevice.BLUETOOTH);
                        }
                        if (!ListenerUtil.mutListener.listen(61706)) {
                            audioDeviceSetUpdated = true;
                        }
                    }
                }
            }
        }
        return audioDeviceSetUpdated;
    }

    /**
     *  Return the set of available audio devices.
     */
    @NonNull
    private HashSet<AudioDevice> queryAvailableAudioDevices() {
        final HashSet<AudioDevice> devices = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(61710)) {
            // TODO(henrika): perhaps wrap required state into BT manager.
            switch(bluetoothManager.getState()) {
                case HEADSET_AVAILABLE:
                case HEADSET_UNAVAILABLE:
                case SCO_DISCONNECTING:
                    if (!ListenerUtil.mutListener.listen(61709)) {
                        bluetoothManager.updateDevice();
                    }
                    break;
                default:
            }
        }
        if (!ListenerUtil.mutListener.listen(61712)) {
            // Check for a bluetooth device
            switch(bluetoothManager.getState()) {
                case SCO_CONNECTED:
                case SCO_CONNECTING:
                case HEADSET_AVAILABLE:
                    if (!ListenerUtil.mutListener.listen(61711)) {
                        devices.add(AudioDevice.BLUETOOTH);
                    }
                    break;
                default:
            }
        }
        if (!ListenerUtil.mutListener.listen(61717)) {
            if (this.hasWiredHeadset) {
                if (!ListenerUtil.mutListener.listen(61716)) {
                    // If a wired headset is connected, then it is the only possible option.
                    devices.add(AudioDevice.WIRED_HEADSET);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61713)) {
                    // phone (on a tablet), or speaker phone and earpiece (on mobile phone).
                    devices.add(AudioDevice.SPEAKER_PHONE);
                }
                if (!ListenerUtil.mutListener.listen(61715)) {
                    if (this.hasEarpiece()) {
                        if (!ListenerUtil.mutListener.listen(61714)) {
                            devices.add(AudioDevice.EARPIECE);
                        }
                    }
                }
            }
        }
        return devices;
    }

    /**
     *  Validate and fix the user selected audio device.
     *  For example if the user selected bluetooth but there is no bluetooth device available,
     *  reset the selection to NONE.
     */
    private void validateUserSelection() {
        if (!ListenerUtil.mutListener.listen(61720)) {
            if ((ListenerUtil.mutListener.listen(61718) ? (this.bluetoothManager.getState() == VoipBluetoothManager.State.HEADSET_UNAVAILABLE || this.userSelectedAudioDevice == AudioDevice.BLUETOOTH) : (this.bluetoothManager.getState() == VoipBluetoothManager.State.HEADSET_UNAVAILABLE && this.userSelectedAudioDevice == AudioDevice.BLUETOOTH))) {
                if (!ListenerUtil.mutListener.listen(61719)) {
                    // If BT is not available, it can't be the user selection.
                    this.userSelectedAudioDevice = AudioDevice.NONE;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61723)) {
            if ((ListenerUtil.mutListener.listen(61721) ? (this.hasWiredHeadset || this.userSelectedAudioDevice == AudioDevice.SPEAKER_PHONE) : (this.hasWiredHeadset && this.userSelectedAudioDevice == AudioDevice.SPEAKER_PHONE))) {
                if (!ListenerUtil.mutListener.listen(61722)) {
                    // wired headset as user selected device.
                    this.userSelectedAudioDevice = AudioDevice.WIRED_HEADSET;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61726)) {
            if ((ListenerUtil.mutListener.listen(61724) ? (this.hasWiredHeadset || this.userSelectedAudioDevice == AudioDevice.EARPIECE) : (this.hasWiredHeadset && this.userSelectedAudioDevice == AudioDevice.EARPIECE))) {
                if (!ListenerUtil.mutListener.listen(61725)) {
                    // output audio to both outputs at once).
                    this.userSelectedAudioDevice = AudioDevice.WIRED_HEADSET;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61729)) {
            if ((ListenerUtil.mutListener.listen(61727) ? (!this.hasWiredHeadset || this.userSelectedAudioDevice == AudioDevice.WIRED_HEADSET) : (!this.hasWiredHeadset && this.userSelectedAudioDevice == AudioDevice.WIRED_HEADSET))) {
                if (!ListenerUtil.mutListener.listen(61728)) {
                    // unset the user selection so that the default device will be picked again.
                    this.userSelectedAudioDevice = AudioDevice.NONE;
                }
            }
        }
    }
}
