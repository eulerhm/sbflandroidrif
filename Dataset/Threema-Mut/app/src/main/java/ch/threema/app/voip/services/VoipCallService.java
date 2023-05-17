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
package ch.threema.app.voip.services;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.IceCandidate;
import org.webrtc.PeerConnection;
import org.webrtc.RTCStatsCollectorCallback;
import org.webrtc.RTCStatsReport;
import org.webrtc.SessionDescription;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSink;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.lifecycle.LifecycleService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.PreferenceManager;
import ch.threema.annotation.SameThread;
import ch.threema.app.BuildConfig;
import ch.threema.app.push.PushService;
import ch.threema.app.R;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.notifications.BackgroundErrorNotification;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.utils.CloseableLock;
import ch.threema.app.utils.CloseableReadWriteLock;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.MediaPlayerStateWrapper;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RandomUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.CallStateSnapshot;
import ch.threema.app.voip.CpuMonitor;
import ch.threema.app.voip.PeerConnectionClient;
import ch.threema.app.voip.VoipAudioManager;
import ch.threema.app.voip.VoipAudioManager.AudioDevice;
import ch.threema.app.voip.activities.CallActivity;
import ch.threema.app.voip.listeners.VoipAudioManagerListener;
import ch.threema.app.voip.listeners.VoipMessageListener;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.voip.receivers.IncomingMobileCallReceiver;
import ch.threema.app.voip.receivers.MeteredStatusChangedReceiver;
import ch.threema.app.voip.util.SdpPatcher;
import ch.threema.app.voip.util.SdpUtil;
import ch.threema.app.voip.util.VideoCapturerUtil;
import ch.threema.app.voip.util.VoipStats;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.app.voip.util.VoipVideoParams;
import ch.threema.app.wearable.WearableHandler;
import ch.threema.base.ThreemaException;
import ch.threema.base.VerificationLevel;
import ch.threema.client.ThreemaFeature;
import ch.threema.client.voip.VoipCallAnswerData;
import ch.threema.client.voip.VoipCallHangupData;
import ch.threema.client.voip.VoipCallOfferData;
import ch.threema.client.voip.VoipCallRingingData;
import ch.threema.client.voip.VoipICECandidatesData;
import ch.threema.client.voip.features.FeatureList;
import ch.threema.client.voip.features.VideoFeature;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.protobuf.callsignaling.CallSignaling;
import ch.threema.storage.models.ContactModel;
import java8.util.function.Supplier;
import java8.util.stream.StreamSupport;
import static ch.threema.app.ThreemaApplication.getAppContext;
import static ch.threema.app.ThreemaApplication.getServiceManager;
import static ch.threema.app.voip.services.VideoContext.CAMERA_BACK;
import static ch.threema.app.voip.services.VideoContext.CAMERA_FRONT;
import static ch.threema.app.voip.services.VoipStateService.VIDEO_RENDER_FLAG_NONE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The service keeping track of VoIP call state and the corresponding WebRTC peer connection.
 */
public class VoipCallService extends LifecycleService implements PeerConnectionClient.Events {

    private static final Logger logger = LoggerFactory.getLogger(VoipCallService.class);

    // Intent extras
    public static final String EXTRA_CALL_ID = "CALL_ID";

    public static final String EXTRA_CONTACT_IDENTITY = "CONTACT_IDENTITY";

    public static final String EXTRA_IS_INITIATOR = "IS_INITIATOR";

    public static final String EXTRA_ACTIVITY_MODE = "ACTIVITY_MODE";

    public static final String EXTRA_CANDIDATES = "CANDIDATES";

    public static final String EXTRA_AUDIO_DEVICE = "AUDIO_DEVICE";

    public static final String EXTRA_START_TIME = "START_TIME";

    public static final String EXTRA_LAUNCH_VIDEO = "LAUNCH_VIDEO";

    public static final String EXTRA_CANCEL_WEAR = "CANCEL_ACTIVITY_ON_WATCH";

    // Broadcast actions
    public static final String ACTION_HANGUP = BuildConfig.APPLICATION_ID + ".HANGUP";

    public static final String ACTION_ICE_CANDIDATES = BuildConfig.APPLICATION_ID + ".ICE_CANDIDATES";

    public static final String ACTION_MUTE_TOGGLE = BuildConfig.APPLICATION_ID + ".MUTE_TOGGLE";

    public static final String ACTION_SET_AUDIO_DEVICE = BuildConfig.APPLICATION_ID + ".SET_AUDIO_DEVICE";

    public static final String ACTION_QUERY_AUDIO_DEVICES = BuildConfig.APPLICATION_ID + ".QUERY_AUDIO_DEVICES";

    public static final String ACTION_QUERY_MIC_ENABLED = BuildConfig.APPLICATION_ID + ".QUERY_MIC_ENABLED";

    public static final String ACTION_ENABLE_DEBUG_INFO = BuildConfig.APPLICATION_ID + ".ENABLE_DEBUG_INFO";

    public static final String ACTION_DISABLE_DEBUG_INFO = BuildConfig.APPLICATION_ID + ".DISABLE_DEBUG_INFO";

    public static final String ACTION_START_CAPTURING = BuildConfig.APPLICATION_ID + ".START_CAPTURING";

    public static final String ACTION_STOP_CAPTURING = BuildConfig.APPLICATION_ID + ".STOP_CAPTURING";

    public static final String ACTION_SWITCH_CAMERA = BuildConfig.APPLICATION_ID + ".SWITCH_CAMERA";

    // Notification IDs
    private static final int INCALL_NOTIFICATION_ID = 41991;

    // Peer connection
    @Nullable
    private PeerConnectionClient peerConnectionClient = null;

    // Audio
    @Nullable
    private VoipAudioManager audioManager = null;

    // Video
    private boolean videoEnabled = true;

    @NonNull
    private final CloseableReadWriteLock videoQualityNegotiation = new CloseableReadWriteLock(new ReentrantReadWriteLock());

    @Nullable
    private VoipVideoParams localVideoQualityProfile;

    @Nullable
    private VoipVideoParams remoteVideoQualityProfile;

    @Nullable
    private VoipVideoParams commonVideoQualityProfile;

    private static boolean isRunning = false;

    private boolean foregroundStarted = false;

    private boolean iceConnected = false;

    private boolean iceWasConnected = false;

    private boolean isError = false;

    private boolean micEnabled = true;

    private boolean uiDebugStatsEnabled = false;

    // The contact that is being called
    @Nullable
    private static ContactModel contact = null;

    // Offer SDP
    private SessionDescription offerSessionDescription;

    // Services
    private VoipStateService voipStateService;

    private PreferenceService preferenceService;

    private ContactService contactService;

    // Listeners
    private VoipMessageListener voipMessageListener;

    private PhoneStateListener hangUpRtcOnDeviceCallAnswered = new PSTNCallStateListener();

    // Receivers
    private IncomingMobileCallReceiver incomingMobileCallReceiver;

    // Media players
    @Nullable
    private MediaPlayerStateWrapper mediaPlayer;

    // PeerConnection configuration
    private Boolean useOpenSLES = null;

    private Boolean disableBuiltInAEC = null;

    // Only used for change detection!
    @Nullable
    private Boolean networkIsMetered;

    private volatile boolean networkIsRelayed = false;

    // Diagnostics
    private CpuMonitor cpuMonitor;

    private long callStartedTimeMs = 0;

    private static long callStartedRealtimeMs = 0;

    private static final long ACTIVITY_STATS_INTERVAL_MS = 1000;

    private static final long LOG_STATS_INTERVAL_MS_CONNECTING = 2000;

    private static final long LOG_STATS_INTERVAL_MS_CONNECTED = 30000;

    private static final long FRAME_DETECTOR_QUERY_INTERVAL_MS = 750;

    // Timeouts
    private final Timer iceDisconnectedSoundTimer = new Timer();

    private TimerTask iceDisconnectedSoundTimeout;

    private static final int ICE_DISCONNECTED_SOUND_TIMEOUT_MS = 1000;

    // Camera handling
    @NonNull
    private AtomicBoolean switchCamInProgress = new AtomicBoolean(false);

    // Lock whenever modifying capturing
    private final Object capturingLock = new Object();

    // Always synchronize on capturingLock!
    private volatile boolean isCapturing = false;

    // Managers
    private NotificationManager notificationManager;

    private TelephonyManager telephonyManager;

    private SharedPreferences sharedPreferences;

    // Broadcast receivers
    private MeteredStatusChangedReceiver meteredStatusChangedReceiver;

    private BroadcastReceiver localBroadcastReceiver = new BroadcastReceiver() {

        @Override
        @UiThread
        public void onReceive(Context context, Intent intent) {
            if (!ListenerUtil.mutListener.listen(58775)) {
                if (intent != null) {
                    final String action = intent.getAction();
                    if (!ListenerUtil.mutListener.listen(58774)) {
                        if (action != null) {
                            if (!ListenerUtil.mutListener.listen(58773)) {
                                switch(action) {
                                    case ACTION_HANGUP:
                                        if (!ListenerUtil.mutListener.listen(58744)) {
                                            onCallHangUp();
                                        }
                                        break;
                                    case ACTION_ICE_CANDIDATES:
                                        if (!ListenerUtil.mutListener.listen(58746)) {
                                            if (!intent.hasExtra(EXTRA_CALL_ID)) {
                                                if (!ListenerUtil.mutListener.listen(58745)) {
                                                    logger.warn("Received broadcast intent without EXTRA_CALL_ID: action={}", action);
                                                }
                                            }
                                        }
                                        final long callId = intent.getLongExtra(EXTRA_CALL_ID, 0L);
                                        final String contactIdentity = intent.getStringExtra(VoipCallService.EXTRA_CONTACT_IDENTITY);
                                        final VoipICECandidatesData candidatesData = (VoipICECandidatesData) intent.getSerializableExtra(EXTRA_CANDIDATES);
                                        if (!ListenerUtil.mutListener.listen(58756)) {
                                            if ((ListenerUtil.mutListener.listen(58747) ? (contactIdentity != null || candidatesData != null) : (contactIdentity != null && candidatesData != null))) {
                                                long dataCallId = candidatesData.getCallIdOrDefault(0L);
                                                if (!ListenerUtil.mutListener.listen(58755)) {
                                                    if ((ListenerUtil.mutListener.listen(58752) ? (callId >= dataCallId) : (ListenerUtil.mutListener.listen(58751) ? (callId <= dataCallId) : (ListenerUtil.mutListener.listen(58750) ? (callId > dataCallId) : (ListenerUtil.mutListener.listen(58749) ? (callId < dataCallId) : (ListenerUtil.mutListener.listen(58748) ? (callId == dataCallId) : (callId != dataCallId))))))) {
                                                        if (!ListenerUtil.mutListener.listen(58754)) {
                                                            logger.error("Mismatch between intent call ID ({}) and data call ID ({})", callId, dataCallId);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(58753)) {
                                                            handleNewCandidate(contactIdentity, candidatesData);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case ACTION_MUTE_TOGGLE:
                                        if (!ListenerUtil.mutListener.listen(58757)) {
                                            onToggleMic();
                                        }
                                        break;
                                    case ACTION_SET_AUDIO_DEVICE:
                                        if (!ListenerUtil.mutListener.listen(58759)) {
                                            if (intent.hasExtra(EXTRA_AUDIO_DEVICE)) {
                                                if (!ListenerUtil.mutListener.listen(58758)) {
                                                    onToggleAudioDevice((AudioDevice) intent.getSerializableExtra(EXTRA_AUDIO_DEVICE));
                                                }
                                            }
                                        }
                                        break;
                                    case ACTION_QUERY_AUDIO_DEVICES:
                                        if (!ListenerUtil.mutListener.listen(58763)) {
                                            if (audioManager != null) {
                                                if (!ListenerUtil.mutListener.listen(58761)) {
                                                    logger.debug("Requesting audio manager notify");
                                                }
                                                if (!ListenerUtil.mutListener.listen(58762)) {
                                                    audioManager.requestAudioManagerNotify();
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(58760)) {
                                                    logger.error("Cannot request audio manager notify: Audio manager is null");
                                                }
                                            }
                                        }
                                        break;
                                    case ACTION_QUERY_MIC_ENABLED:
                                        if (!ListenerUtil.mutListener.listen(58767)) {
                                            if (audioManager != null) {
                                                if (!ListenerUtil.mutListener.listen(58765)) {
                                                    logger.debug("Requesting mute status notify");
                                                }
                                                if (!ListenerUtil.mutListener.listen(58766)) {
                                                    audioManager.requestMicEnabledNotify();
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(58764)) {
                                                    logger.error("Cannot request mute status notify: Audio manager is null");
                                                }
                                            }
                                        }
                                        break;
                                    case ACTION_ENABLE_DEBUG_INFO:
                                        if (!ListenerUtil.mutListener.listen(58768)) {
                                            enableUIDebugStats(true);
                                        }
                                        break;
                                    case ACTION_DISABLE_DEBUG_INFO:
                                        if (!ListenerUtil.mutListener.listen(58769)) {
                                            enableUIDebugStats(false);
                                        }
                                        break;
                                    case ACTION_START_CAPTURING:
                                        if (!ListenerUtil.mutListener.listen(58770)) {
                                            startCapturing();
                                        }
                                        break;
                                    case ACTION_STOP_CAPTURING:
                                        if (!ListenerUtil.mutListener.listen(58771)) {
                                            stopCapturing();
                                        }
                                        break;
                                    case ACTION_SWITCH_CAMERA:
                                        if (!ListenerUtil.mutListener.listen(58772)) {
                                            switchCamera();
                                        }
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    // preference change receiver
    SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = (sharedPreferences, key) -> {
        if (getString(R.string.preferences__voip_video_profile).equals(key)) {
            // profile has changed
            this.updateOwnVideoQualityProfile(Boolean.TRUE.equals(this.meteredStatusChangedReceiver.getMetered().getValue()), this.networkIsRelayed);
        }
    };

    /**
     *  The activity stats collector is enabled when long-pressing on the callee name.
     *  It then periodically collects and shows debug information on the call screen.
     */
    private final RTCStatsCollectorCallback activityStatsCollector = new RTCStatsCollectorCallback() {

        private final VoipStats.Builder builder = new VoipStats.Builder().withSelectedCandidatePair(true).withTransport(true).withCrypto(true).withRtp(true).withTracks(true).withCodecs(false).withCandidatePairs(VoipStats.CandidatePairVariant.OVERVIEW);

        @Nullable
        private VoipStats.State previousState;

        @Override
        public void onStatsDelivered(RTCStatsReport report) {
            // Extract stats report
            final VoipStats.Extractor extractor = this.builder.extractor();
            if (!ListenerUtil.mutListener.listen(58777)) {
                if (peerConnectionClient != null) {
                    if (!ListenerUtil.mutListener.listen(58776)) {
                        extractor.withRtpTransceivers(peerConnectionClient.getTransceivers());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(58779)) {
                if (this.previousState != null) {
                    if (!ListenerUtil.mutListener.listen(58778)) {
                        extractor.comparedTo(this.previousState);
                    }
                }
            }
            final VoipStats stats = extractor.extract(report);
            // Determine whether a TURN relay is being used
            final boolean usesRelay = stats.usesRelay();
            if (!ListenerUtil.mutListener.listen(58780)) {
                RuntimeUtil.runInAsyncTask(() -> updateNetworkRelayState(usesRelay));
            }
            // Create debug text
            final StringBuilder builder = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(58781)) {
                stats.addShortRepresentation(builder);
            }
            if (!ListenerUtil.mutListener.listen(58782)) {
                builder.append("\n\nopensl=");
            }
            if (!ListenerUtil.mutListener.listen(58783)) {
                builder.append(useOpenSLES ? "yes" : "no");
            }
            if (!ListenerUtil.mutListener.listen(58784)) {
                builder.append(" aec=");
            }
            if (!ListenerUtil.mutListener.listen(58785)) {
                builder.append(disableBuiltInAEC ? "no" : "yes");
            }
            try (CloseableLock locked = videoQualityNegotiation.tryRead(50, TimeUnit.MILLISECONDS)) {
                if (!ListenerUtil.mutListener.listen(58786)) {
                    builder.append("\nL=").append(localVideoQualityProfile);
                }
                if (!ListenerUtil.mutListener.listen(58787)) {
                    builder.append("\nR=").append(remoteVideoQualityProfile);
                }
                if (!ListenerUtil.mutListener.listen(58788)) {
                    builder.append("\nC=").append(commonVideoQualityProfile);
                }
            } catch (CloseableReadWriteLock.NotLocked ignored) {
            }
            if (!ListenerUtil.mutListener.listen(58789)) {
                // Store previous state
                this.previousState = stats.getState();
            }
            if (!ListenerUtil.mutListener.listen(58790)) {
                // Notify listeners about new debug text
                VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_DEBUG_INFO, "TEXT", builder.toString());
            }
        }
    };

    /**
     *  The debugStatsCollector collects stats periodically and writes them
     *  to the debug log.
     */
    private CallStatsCollectorCallback debugStatsCollector = null;

    class CallStatsCollectorCallback implements RTCStatsCollectorCallback {

        @NonNull
        private final VoipStats.Builder builder;

        @Nullable
        private VoipStats.State previousState;

        private boolean includeTransceivers = true;

        CallStatsCollectorCallback(@NonNull VoipStats.Builder builder) {
            this.builder = builder;
        }

        @Override
        public void onStatsDelivered(RTCStatsReport report) {
            // Get extracted stats
            final VoipStats.Extractor extractor = this.builder.extractor();
            if (!ListenerUtil.mutListener.listen(58793)) {
                if ((ListenerUtil.mutListener.listen(58791) ? (this.includeTransceivers || peerConnectionClient != null) : (this.includeTransceivers && peerConnectionClient != null))) {
                    if (!ListenerUtil.mutListener.listen(58792)) {
                        extractor.withRtpTransceivers(peerConnectionClient.getTransceivers());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(58795)) {
                if (this.previousState != null) {
                    if (!ListenerUtil.mutListener.listen(58794)) {
                        extractor.comparedTo(this.previousState);
                    }
                }
            }
            final VoipStats stats = extractor.extract(report);
            final StringBuilder builder = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(58796)) {
                builder.append("Stats\n");
            }
            if (!ListenerUtil.mutListener.listen(58797)) {
                stats.addRepresentation(builder);
            }
            // Determine whether a TURN relay is being used
            final boolean usesRelay = stats.usesRelay();
            if (!ListenerUtil.mutListener.listen(58798)) {
                RuntimeUtil.runInAsyncTask(() -> updateNetworkRelayState(usesRelay));
            }
            if (!ListenerUtil.mutListener.listen(58799)) {
                // Update state
                this.previousState = stats.getState();
            }
            if (!ListenerUtil.mutListener.listen(58800)) {
                // Don't log transceivers in subsequent runs
                this.includeTransceivers = false;
            }
            if (!ListenerUtil.mutListener.listen(58801)) {
                // Log stats
                logger.info(builder.toString());
            }
        }
    }

    private void updateNetworkRelayState(final boolean networkIsRelayed) {
        boolean changed = this.networkIsRelayed != networkIsRelayed;
        if (!ListenerUtil.mutListener.listen(58802)) {
            this.networkIsRelayed = networkIsRelayed;
        }
        if (!ListenerUtil.mutListener.listen(58804)) {
            if (changed) {
                if (!ListenerUtil.mutListener.listen(58803)) {
                    // If relay status changes, update video quality profile
                    this.updateOwnVideoQualityProfile(Boolean.TRUE.equals(meteredStatusChangedReceiver.getMetered().getValue()), networkIsRelayed);
                }
            }
        }
    }

    private FrameDetectorCallback frameDetector = null;

    /**
     *  This class tracks incoming frames. It can notify the passed in runnables
     *  when the call partner starts and stops sending video frames.
     *
     *  Instances of this class are not thread safe. The methods may be called from different
     *  threads, but not concurrently.
     *
     *  Note: Due to frequent changes to the stats API spec[1], this might require
     *  changes when updating WebRTC!
     *
     *  [1]: https://www.w3.org/TR/webrtc-stats/
     */
    @SameThread
    static class FrameDetectorCallback implements RTCStatsCollectorCallback {

        @NonNull
        private State state = State.STOPPED;

        @Nullable
        private Long lastFrameDetectionTimestampMs;

        private long lastFrameCount = 0;

        @NonNull
        private final Runnable framesStarted;

        @NonNull
        private final Runnable framesStopped;

        /**
         *  If no frames are sent for at least the specified number of milliseconds,
         *  consider that a "video frames stopped" event.
         */
        @SuppressWarnings("FieldCanBeLocal")
        private static long STOP_THRESHOLD_MS = 1000;

        FrameDetectorCallback(@NonNull Runnable framesStarted, @NonNull Runnable framesStopped) {
            this.framesStarted = framesStarted;
            this.framesStopped = framesStopped;
        }

        @Override
        public void onStatsDelivered(RTCStatsReport report) {
            if (!ListenerUtil.mutListener.listen(58805)) {
                if (!ConfigUtils.isVideoCallsEnabled()) {
                    return;
                }
            }
            final long totalFramesReceived = getTotalFramesReceived(report);
            if (!ListenerUtil.mutListener.listen(58806)) {
                logger.trace("FrameDetectorCallback: Total frames received = {}", totalFramesReceived);
            }
            if (!ListenerUtil.mutListener.listen(58848)) {
                if ((ListenerUtil.mutListener.listen(58811) ? (totalFramesReceived >= this.lastFrameCount) : (ListenerUtil.mutListener.listen(58810) ? (totalFramesReceived <= this.lastFrameCount) : (ListenerUtil.mutListener.listen(58809) ? (totalFramesReceived < this.lastFrameCount) : (ListenerUtil.mutListener.listen(58808) ? (totalFramesReceived != this.lastFrameCount) : (ListenerUtil.mutListener.listen(58807) ? (totalFramesReceived == this.lastFrameCount) : (totalFramesReceived > this.lastFrameCount))))))) {
                    if (!ListenerUtil.mutListener.listen(58838)) {
                        // Frame count increased
                        this.lastFrameCount = totalFramesReceived;
                    }
                    if (!ListenerUtil.mutListener.listen(58843)) {
                        this.lastFrameDetectionTimestampMs = (ListenerUtil.mutListener.listen(58842) ? (System.nanoTime() % 1000) : (ListenerUtil.mutListener.listen(58841) ? (System.nanoTime() * 1000) : (ListenerUtil.mutListener.listen(58840) ? (System.nanoTime() - 1000) : (ListenerUtil.mutListener.listen(58839) ? (System.nanoTime() + 1000) : (System.nanoTime() / 1000)))));
                    }
                    if (!ListenerUtil.mutListener.listen(58847)) {
                        if (this.state == State.STOPPED) {
                            if (!ListenerUtil.mutListener.listen(58844)) {
                                this.state = State.STARTED;
                            }
                            if (!ListenerUtil.mutListener.listen(58845)) {
                                logger.debug("FrameDetectorCallback: Started");
                            }
                            if (!ListenerUtil.mutListener.listen(58846)) {
                                this.framesStarted.run();
                            }
                        }
                    }
                } else if ((ListenerUtil.mutListener.listen(58816) ? (totalFramesReceived >= this.lastFrameCount) : (ListenerUtil.mutListener.listen(58815) ? (totalFramesReceived <= this.lastFrameCount) : (ListenerUtil.mutListener.listen(58814) ? (totalFramesReceived > this.lastFrameCount) : (ListenerUtil.mutListener.listen(58813) ? (totalFramesReceived < this.lastFrameCount) : (ListenerUtil.mutListener.listen(58812) ? (totalFramesReceived != this.lastFrameCount) : (totalFramesReceived == this.lastFrameCount))))))) {
                    if (!ListenerUtil.mutListener.listen(58837)) {
                        // Frame count stayed the same
                        if ((ListenerUtil.mutListener.listen(58819) ? (this.state == State.STARTED || this.lastFrameDetectionTimestampMs != null) : (this.state == State.STARTED && this.lastFrameDetectionTimestampMs != null))) {
                            final long msElapsed = (ListenerUtil.mutListener.listen(58827) ? (((ListenerUtil.mutListener.listen(58823) ? (System.nanoTime() % 1000) : (ListenerUtil.mutListener.listen(58822) ? (System.nanoTime() * 1000) : (ListenerUtil.mutListener.listen(58821) ? (System.nanoTime() - 1000) : (ListenerUtil.mutListener.listen(58820) ? (System.nanoTime() + 1000) : (System.nanoTime() / 1000)))))) % this.lastFrameDetectionTimestampMs) : (ListenerUtil.mutListener.listen(58826) ? (((ListenerUtil.mutListener.listen(58823) ? (System.nanoTime() % 1000) : (ListenerUtil.mutListener.listen(58822) ? (System.nanoTime() * 1000) : (ListenerUtil.mutListener.listen(58821) ? (System.nanoTime() - 1000) : (ListenerUtil.mutListener.listen(58820) ? (System.nanoTime() + 1000) : (System.nanoTime() / 1000)))))) / this.lastFrameDetectionTimestampMs) : (ListenerUtil.mutListener.listen(58825) ? (((ListenerUtil.mutListener.listen(58823) ? (System.nanoTime() % 1000) : (ListenerUtil.mutListener.listen(58822) ? (System.nanoTime() * 1000) : (ListenerUtil.mutListener.listen(58821) ? (System.nanoTime() - 1000) : (ListenerUtil.mutListener.listen(58820) ? (System.nanoTime() + 1000) : (System.nanoTime() / 1000)))))) * this.lastFrameDetectionTimestampMs) : (ListenerUtil.mutListener.listen(58824) ? (((ListenerUtil.mutListener.listen(58823) ? (System.nanoTime() % 1000) : (ListenerUtil.mutListener.listen(58822) ? (System.nanoTime() * 1000) : (ListenerUtil.mutListener.listen(58821) ? (System.nanoTime() - 1000) : (ListenerUtil.mutListener.listen(58820) ? (System.nanoTime() + 1000) : (System.nanoTime() / 1000)))))) + this.lastFrameDetectionTimestampMs) : (((ListenerUtil.mutListener.listen(58823) ? (System.nanoTime() % 1000) : (ListenerUtil.mutListener.listen(58822) ? (System.nanoTime() * 1000) : (ListenerUtil.mutListener.listen(58821) ? (System.nanoTime() - 1000) : (ListenerUtil.mutListener.listen(58820) ? (System.nanoTime() + 1000) : (System.nanoTime() / 1000)))))) - this.lastFrameDetectionTimestampMs)))));
                            if (!ListenerUtil.mutListener.listen(58836)) {
                                if ((ListenerUtil.mutListener.listen(58832) ? (msElapsed >= STOP_THRESHOLD_MS) : (ListenerUtil.mutListener.listen(58831) ? (msElapsed <= STOP_THRESHOLD_MS) : (ListenerUtil.mutListener.listen(58830) ? (msElapsed < STOP_THRESHOLD_MS) : (ListenerUtil.mutListener.listen(58829) ? (msElapsed != STOP_THRESHOLD_MS) : (ListenerUtil.mutListener.listen(58828) ? (msElapsed == STOP_THRESHOLD_MS) : (msElapsed > STOP_THRESHOLD_MS))))))) {
                                    if (!ListenerUtil.mutListener.listen(58833)) {
                                        this.state = State.STOPPED;
                                    }
                                    if (!ListenerUtil.mutListener.listen(58834)) {
                                        logger.debug("FrameDetectorCallback: Stopped");
                                    }
                                    if (!ListenerUtil.mutListener.listen(58835)) {
                                        this.framesStopped.run();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(58817)) {
                        // Frame count decreased?!
                        logger.warn("FrameDetectorCallback: Frame count decreased from {} to {}", this.lastFrameCount, totalFramesReceived);
                    }
                    if (!ListenerUtil.mutListener.listen(58818)) {
                        this.lastFrameCount = totalFramesReceived;
                    }
                }
            }
        }

        /**
         *  Extract the total number of frames received across all incoming video tracks.
         */
        private long getTotalFramesReceived(RTCStatsReport report) {
            return StreamSupport.parallelStream(report.getStatsMap().values()).filter(stats -> "track".equals(stats.getType())).filter(stats -> {
                final Map<String, Object> members = stats.getMembers();
                final Object isRemoteSrc = members.get("remoteSource");
                final Object hasEnded = members.get("ended");
                final Object kind = members.get("kind");
                final Object framesReceived = stats.getMembers().get("framesReceived");
                final Object trackIdentifier = stats.getMembers().get("trackIdentifier");
                return isRemoteSrc instanceof Boolean && hasEnded instanceof Boolean && kind instanceof String && framesReceived instanceof Long && trackIdentifier instanceof String && (Boolean) isRemoteSrc && !((Boolean) hasEnded) && kind.equals("video");
            }).mapToLong(stats -> {
                // noinspection ConstantConditions
                return (Long) stats.getMembers().get("framesReceived");
            }).sum();
        }

        public enum State {

            STOPPED, STARTED
        }
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static long getStartTime() {
        return callStartedRealtimeMs;
    }

    public static String getOtherPartysIdentity() {
        return contact != null ? contact.getIdentity() : null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (!ListenerUtil.mutListener.listen(58849)) {
            super.onBind(intent);
        }
        return null;
    }

    @Override
    public int onStartCommand(@NonNull Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(58850)) {
            super.onStartCommand(intent, flags, startId);
        }
        if (!ListenerUtil.mutListener.listen(58851)) {
            logger.info("onStartCommand");
        }
        // here.
        final int RESTART_BEHAVIOR = START_NOT_STICKY;
        // and stop ourselves.
        final String action = intent.getAction();
        if (!ListenerUtil.mutListener.listen(58853)) {
            if (VoipCallService.ACTION_HANGUP.equals(action)) {
                if (!ListenerUtil.mutListener.listen(58852)) {
                    onCallHangUp();
                }
                return RESTART_BEHAVIOR;
            }
        }
        final String contactIdentity = intent.getStringExtra(EXTRA_CONTACT_IDENTITY);
        if (!ListenerUtil.mutListener.listen(58855)) {
            if (contactIdentity == null) {
                if (!ListenerUtil.mutListener.listen(58854)) {
                    logger.error("Missing contact identity in intent!");
                }
                return RESTART_BEHAVIOR;
            }
        }
        // if the intent creation was initiated from the phone we additionally cancel a potentially already opened activity on the watch
        final boolean cancelActivityOnWearable = intent.getBooleanExtra(EXTRA_CANCEL_WEAR, false);
        if (!ListenerUtil.mutListener.listen(58858)) {
            if ((ListenerUtil.mutListener.listen(58856) ? (cancelActivityOnWearable || PushService.playServicesInstalled(getAppContext())) : (cancelActivityOnWearable && PushService.playServicesInstalled(getAppContext())))) {
                if (!ListenerUtil.mutListener.listen(58857)) {
                    WearableHandler.cancelOnWearable(VoipStateService.TYPE_ACTIVITY);
                }
            }
        }
        final VoipICECandidatesData candidatesData = (VoipICECandidatesData) intent.getSerializableExtra(EXTRA_CANDIDATES);
        if (!ListenerUtil.mutListener.listen(58860)) {
            // that means "generate a new one".
            if (!intent.hasExtra(EXTRA_CALL_ID)) {
                if (!ListenerUtil.mutListener.listen(58859)) {
                    logger.warn("onStartCommand intent without Call ID");
                }
            }
        }
        long callId = intent.getLongExtra(EXTRA_CALL_ID, 0L);
        if (!ListenerUtil.mutListener.listen(58867)) {
            if ((ListenerUtil.mutListener.listen(58865) ? (callId >= -1) : (ListenerUtil.mutListener.listen(58864) ? (callId <= -1) : (ListenerUtil.mutListener.listen(58863) ? (callId > -1) : (ListenerUtil.mutListener.listen(58862) ? (callId < -1) : (ListenerUtil.mutListener.listen(58861) ? (callId != -1) : (callId == -1))))))) {
                if (!ListenerUtil.mutListener.listen(58866)) {
                    callId = RandomUtil.generateRandomU32();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58870)) {
            // to the existing peer connection.
            if (candidatesData != null) {
                if (!ListenerUtil.mutListener.listen(58869)) {
                    handleNewCandidate(contactIdentity, candidatesData);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(58868)) {
                    // Otherwise, we handle a new call.
                    handleNewCall(callId, contactIdentity, intent);
                }
            }
        }
        return RESTART_BEHAVIOR;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(58871)) {
            logger.info("onCreate");
        }
        if (!ListenerUtil.mutListener.listen(58872)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(58873)) {
            isRunning = true;
        }
        if (!ListenerUtil.mutListener.listen(58881)) {
            // access to /proc is not possible anymore in Oreo)
            if ((ListenerUtil.mutListener.listen(58879) ? (BuildConfig.DEBUG || (ListenerUtil.mutListener.listen(58878) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58877) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58876) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58875) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58874) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) : (BuildConfig.DEBUG && (ListenerUtil.mutListener.listen(58878) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58877) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58876) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58875) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(58874) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))))) {
                if (!ListenerUtil.mutListener.listen(58880)) {
                    this.cpuMonitor = new CpuMonitor(this);
                }
            }
        }
        // Get services
        try {
            final ServiceManager serviceManager = getServiceManager();
            if (!ListenerUtil.mutListener.listen(58883)) {
                this.voipStateService = serviceManager.getVoipStateService();
            }
            if (!ListenerUtil.mutListener.listen(58884)) {
                this.preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(58885)) {
                this.contactService = serviceManager.getContactService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(58882)) {
                this.abortCall(R.string.voip_error_init_call, "Cannot instantiate services", e, false);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(58886)) {
            this.notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(58887)) {
            // Create video context
            logger.debug("Creating video context");
        }
        if (!ListenerUtil.mutListener.listen(58888)) {
            this.voipStateService.createVideoContext();
        }
        // Register intent filters
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(58889)) {
            filter.addAction(ACTION_HANGUP);
        }
        if (!ListenerUtil.mutListener.listen(58890)) {
            filter.addAction(ACTION_ICE_CANDIDATES);
        }
        if (!ListenerUtil.mutListener.listen(58891)) {
            filter.addAction(ACTION_MUTE_TOGGLE);
        }
        if (!ListenerUtil.mutListener.listen(58892)) {
            filter.addAction(ACTION_SET_AUDIO_DEVICE);
        }
        if (!ListenerUtil.mutListener.listen(58893)) {
            filter.addAction(ACTION_QUERY_AUDIO_DEVICES);
        }
        if (!ListenerUtil.mutListener.listen(58894)) {
            filter.addAction(ACTION_QUERY_MIC_ENABLED);
        }
        if (!ListenerUtil.mutListener.listen(58895)) {
            filter.addAction(ACTION_ENABLE_DEBUG_INFO);
        }
        if (!ListenerUtil.mutListener.listen(58896)) {
            filter.addAction(ACTION_DISABLE_DEBUG_INFO);
        }
        if (!ListenerUtil.mutListener.listen(58897)) {
            filter.addAction(ACTION_START_CAPTURING);
        }
        if (!ListenerUtil.mutListener.listen(58898)) {
            filter.addAction(ACTION_STOP_CAPTURING);
        }
        if (!ListenerUtil.mutListener.listen(58899)) {
            filter.addAction(ACTION_SWITCH_CAMERA);
        }
        if (!ListenerUtil.mutListener.listen(58900)) {
            LocalBroadcastManager.getInstance(this).registerReceiver(localBroadcastReceiver, filter);
        }
        if (!ListenerUtil.mutListener.listen(58901)) {
            // let lifecycle take care of resource management
            meteredStatusChangedReceiver = new MeteredStatusChangedReceiver(this, this);
        }
        if (!ListenerUtil.mutListener.listen(58902)) {
            meteredStatusChangedReceiver.getMetered().observe(this, metered -> {
                // the connectivity status has changed - adjust parameters
                logger.info("Metered status changed to {}", metered);
                if (metered == null) {
                    return;
                }
                boolean changed = !metered.equals(this.networkIsMetered);
                this.networkIsMetered = metered;
                if (changed && peerConnectionClient != null && preferenceService != null) {
                    this.updateOwnVideoQualityProfile(metered, this.networkIsRelayed);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(58903)) {
            telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(58905)) {
            if (telephonyManager != null) {
                if (!ListenerUtil.mutListener.listen(58904)) {
                    telephonyManager.listen(hangUpRtcOnDeviceCallAnswered, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58908)) {
            if (preferenceService.isRejectMobileCalls()) {
                if (!ListenerUtil.mutListener.listen(58906)) {
                    incomingMobileCallReceiver = new IncomingMobileCallReceiver();
                }
                if (!ListenerUtil.mutListener.listen(58907)) {
                    registerReceiver(incomingMobileCallReceiver, new IntentFilter("android.intent.action.PHONE_STATE"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58909)) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        if (!ListenerUtil.mutListener.listen(58910)) {
            sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(58911)) {
            logger.info("onDestroy");
        }
        if (!ListenerUtil.mutListener.listen(58913)) {
            if (localBroadcastReceiver != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(58912)) {
                        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastReceiver);
                    }
                } catch (IllegalArgumentException e) {
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58915)) {
            if (incomingMobileCallReceiver != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(58914)) {
                        unregisterReceiver(incomingMobileCallReceiver);
                    }
                } catch (IllegalArgumentException e) {
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58917)) {
            // clear telephony listener
            if (telephonyManager != null) {
                if (!ListenerUtil.mutListener.listen(58916)) {
                    telephonyManager.listen(hangUpRtcOnDeviceCallAnswered, PhoneStateListener.LISTEN_NONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58919)) {
            if (sharedPreferences != null) {
                if (!ListenerUtil.mutListener.listen(58918)) {
                    sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58920)) {
            this.cancelInCallNotification();
        }
        if (!ListenerUtil.mutListener.listen(58921)) {
            isRunning = false;
        }
        if (!ListenerUtil.mutListener.listen(58922)) {
            // Clean up resources
            this.cleanup();
        }
        if (!ListenerUtil.mutListener.listen(58923)) {
            super.onDestroy();
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        if (!ListenerUtil.mutListener.listen(58924)) {
            logger.trace("onTaskRemoved");
        }
        if (!ListenerUtil.mutListener.listen(58925)) {
            super.onTaskRemoved(rootIntent);
        }
    }

    @UiThread
    public void onCallHangUp() {
        final CallStateSnapshot callState = this.voipStateService.getCallState();
        if (!ListenerUtil.mutListener.listen(58926)) {
            logCallInfo(callState.getCallId(), "Hanging up call");
        }
        if (!ListenerUtil.mutListener.listen(58931)) {
            if ((ListenerUtil.mutListener.listen(58927) ? (callState.isInitializing() && callState.isCalling()) : (callState.isInitializing() || callState.isCalling()))) {
                if (!ListenerUtil.mutListener.listen(58930)) {
                    new AsyncTask<Pair<ContactModel, Long>, Void, Void>() {

                        @Override
                        protected Void doInBackground(Pair<ContactModel, Long>... params) {
                            try {
                                if (!ListenerUtil.mutListener.listen(58929)) {
                                    voipStateService.sendCallHangupMessage(params[0].first, params[0].second);
                                }
                            } catch (ThreemaException e) {
                                if (!ListenerUtil.mutListener.listen(58928)) {
                                    abortCall(R.string.an_error_occurred, "Could not send hangup message", e, false);
                                }
                            }
                            return null;
                        }
                    }.execute(new Pair<>(contact, callState.getCallId()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58933)) {
            if (PushService.playServicesInstalled(getAppContext())) {
                if (!ListenerUtil.mutListener.listen(58932)) {
                    WearableHandler.cancelOnWearable(VoipStateService.TYPE_ACTIVITY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58934)) {
            disconnect();
        }
    }

    /**
     *  Handle a new incoming or outgoing call.
     */
    @UiThread
    private void handleNewCall(final long callId, final String contactIdentity, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(58935)) {
            logger.trace("handleNewCall ({} / {})", callId, contactIdentity);
        }
        if (!ListenerUtil.mutListener.listen(58937)) {
            if (this.voipStateService == null) {
                if (!ListenerUtil.mutListener.listen(58936)) {
                    logger.debug("voipStateService not available.");
                }
                return;
            }
        }
        // Do not initiate a new call if one is still running
        final CallStateSnapshot callState = this.voipStateService.getCallState();
        if (!ListenerUtil.mutListener.listen(58939)) {
            if (callState.isCalling()) {
                if (!ListenerUtil.mutListener.listen(58938)) {
                    logCallInfo(callId, "Call with ID {} is currently ongoing. Ignoring request to initiate new call.", callState.getCallId());
                }
                return;
            }
        }
        // Detect whether we're initiator or responder
        final boolean isInitiator = intent.getBooleanExtra(EXTRA_IS_INITIATOR, false);
        if (!ListenerUtil.mutListener.listen(58940)) {
            this.voipStateService.setInitiator(isInitiator);
        }
        if (!ListenerUtil.mutListener.listen(58941)) {
            logCallInfo(callId, "Handle new call with {}, we are the {}", contactIdentity, isInitiator ? "caller" : "callee");
        }
        if (!ListenerUtil.mutListener.listen(58943)) {
            // Cancel any pending notifications
            if (!isInitiator) {
                if (!ListenerUtil.mutListener.listen(58942)) {
                    this.voipStateService.cancelCallNotificationsForNewCall();
                }
            }
        }
        // Get contact model from intent parameters
        ContactModel newContact = null;
        try {
            if (!ListenerUtil.mutListener.listen(58945)) {
                newContact = getServiceManager().getContactService().getByIdentity(contactIdentity);
            }
        } catch (MasterKeyLockedException | FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(58944)) {
                logCallError(callId, "Could not get contact model", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(58948)) {
            if (newContact == null) {
                if (!ListenerUtil.mutListener.listen(58947)) {
                    // We cannot initialize a new call if the contact cannot be looked up.
                    this.abortCall(R.string.voip_error_init_call, "Cannot retrieve contact for ID " + contactIdentity, false);
                }
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(58946)) {
                    contact = newContact;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58949)) {
            // Initialize state variables
            this.iceConnected = false;
        }
        if (!ListenerUtil.mutListener.listen(58950)) {
            this.isError = false;
        }
        if (!ListenerUtil.mutListener.listen(58951)) {
            this.voipStateService.setStateInitializing(callId);
        }
        if (!ListenerUtil.mutListener.listen(58955)) {
            // Can we use videocalls?
            if ((ListenerUtil.mutListener.listen(58952) ? (this.videoEnabled || !ConfigUtils.isVideoCallsEnabled()) : (this.videoEnabled && !ConfigUtils.isVideoCallsEnabled()))) {
                if (!ListenerUtil.mutListener.listen(58953)) {
                    logCallInfo(callId, "videoEnabled=false, diabled via user config");
                }
                if (!ListenerUtil.mutListener.listen(58954)) {
                    this.videoEnabled = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58959)) {
            if ((ListenerUtil.mutListener.listen(58956) ? (this.videoEnabled || !ThreemaFeature.canVideocall(contact.getFeatureMask())) : (this.videoEnabled && !ThreemaFeature.canVideocall(contact.getFeatureMask())))) {
                if (!ListenerUtil.mutListener.listen(58957)) {
                    logCallInfo(callId, "videoEnabled=false, remote feature mask does not support video calls");
                }
                if (!ListenerUtil.mutListener.listen(58958)) {
                    this.videoEnabled = false;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58972)) {
            // If we're the responder, we also got the SDP data from the initial offer.
            if (!isInitiator) {
                // Look up CallOffer
                final VoipCallOfferData callOfferData = this.voipStateService.getCallOffer(callId);
                if (!ListenerUtil.mutListener.listen(58961)) {
                    if (callOfferData == null) {
                        if (!ListenerUtil.mutListener.listen(58960)) {
                            abortCall(R.string.voip_error_init_call, "Call offer for Call ID " + callId + " not found", false);
                        }
                        return;
                    }
                }
                // Ensure that offer SDP exists
                final VoipCallOfferData.OfferData offerData = callOfferData.getOfferData();
                if (!ListenerUtil.mutListener.listen(58965)) {
                    if ((ListenerUtil.mutListener.listen(58963) ? ((ListenerUtil.mutListener.listen(58962) ? (offerData == null && offerData.getSdp() == null) : (offerData == null || offerData.getSdp() == null)) && offerData.getSdpType() == null) : ((ListenerUtil.mutListener.listen(58962) ? (offerData == null && offerData.getSdp() == null) : (offerData == null || offerData.getSdp() == null)) || offerData.getSdpType() == null))) {
                        if (!ListenerUtil.mutListener.listen(58964)) {
                            abortCall(R.string.voip_error_init_call, "Call offer does not contain SDP", true);
                        }
                        return;
                    }
                }
                final SessionDescription.Type sdpType = SdpUtil.getSdpType(offerData.getSdpType());
                if (!ListenerUtil.mutListener.listen(58967)) {
                    if (sdpType == null) {
                        if (!ListenerUtil.mutListener.listen(58966)) {
                            abortCall(R.string.voip_error_init_call, String.format("handleNewCall: Invalid sdpType: {}", offerData.getSdpType()), true);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(58968)) {
                    this.offerSessionDescription = new SessionDescription(sdpType, offerData.getSdp());
                }
                // If the offerer does not signal video support, disable it
                final FeatureList offerCallFeatures = callOfferData.getFeatures();
                if (!ListenerUtil.mutListener.listen(58971)) {
                    if (!offerCallFeatures.hasFeature(VideoFeature.NAME)) {
                        if (!ListenerUtil.mutListener.listen(58969)) {
                            logCallInfo(callId, "videoEnabled=false, remote does not signal support for video calls");
                        }
                        if (!ListenerUtil.mutListener.listen(58970)) {
                            this.videoEnabled = false;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58973)) {
            // Initialize peer connection parameters
            this.useOpenSLES = this.preferenceService.getAECMode().equals("sw");
        }
        if (!ListenerUtil.mutListener.listen(58974)) {
            // Hardware acoustic echo cancelation
            this.disableBuiltInAEC = this.preferenceService.getAECMode().equals("sw");
        }
        // Automatic gain control
        final boolean disableBuiltInAGC = false;
        // Noise suppression
        final boolean disableBuiltInNS = false;
        final boolean enableLevelControl = false;
        final boolean videoCallEnabled = this.videoEnabled;
        final String videoCodec = this.preferenceService.getVideoCodec();
        final boolean videoCodecHwAcceleration = (ListenerUtil.mutListener.listen(58975) ? (this.videoEnabled || !videoCodec.equals(PreferenceService.VIDEO_CODEC_SW)) : (this.videoEnabled && !videoCodec.equals(PreferenceService.VIDEO_CODEC_SW)));
        final boolean videoCodecEnableVP8 = !videoCodec.equals(PreferenceService.VIDEO_CODEC_NO_VP8);
        final boolean videoCodecEnableH264HiP = !videoCodec.equals(PreferenceService.VIDEO_CODEC_NO_H264HIP);
        final SdpPatcher.RtpHeaderExtensionConfig rtpHeaderExtensionConfig = this.videoEnabled ? SdpPatcher.RtpHeaderExtensionConfig.ENABLE_WITH_ONE_AND_TWO_BYTE_HEADER : SdpPatcher.RtpHeaderExtensionConfig.DISABLE;
        final boolean gatherContinually = true;
        boolean forceTurn;
        if (contact.getVerificationLevel() == VerificationLevel.UNVERIFIED) {
            if (!ListenerUtil.mutListener.listen(58978)) {
                // This makes sure that a stranger cannot find out your IP simply by calling you.
                logCallInfo(callId, "Force TURN since contact is unverified");
            }
            forceTurn = true;
        } else {
            // the setting.
            forceTurn = this.preferenceService.getForceTURN();
            if (!ListenerUtil.mutListener.listen(58977)) {
                if (forceTurn) {
                    if (!ListenerUtil.mutListener.listen(58976)) {
                        logCallInfo(callId, "Force TURN as requested by user");
                    }
                }
            }
        }
        final PeerConnectionClient.PeerConnectionParameters peerConnectionParameters = new PeerConnectionClient.PeerConnectionParameters(false, this.useOpenSLES, this.disableBuiltInAEC, disableBuiltInAGC, disableBuiltInNS, enableLevelControl, videoCallEnabled, videoCodecHwAcceleration, videoCodecEnableVP8, videoCodecEnableH264HiP, rtpHeaderExtensionConfig, forceTurn, gatherContinually, this.preferenceService.allowWebrtcIpv6());
        if (!ListenerUtil.mutListener.listen(58979)) {
            // Initialize peer connection
            if (this.voipStateService.getVideoContext() == null) {
                throw new IllegalStateException("Video context is null");
            }
        }
        if (!ListenerUtil.mutListener.listen(58980)) {
            this.peerConnectionClient = new PeerConnectionClient(getAppContext(), peerConnectionParameters, this.voipStateService.getVideoContext().getEglBaseContext(), callId);
        }
        if (!ListenerUtil.mutListener.listen(58981)) {
            this.peerConnectionClient.setEventHandler(VoipCallService.this);
        }
        try {
            boolean factoryCreated = this.peerConnectionClient.createPeerConnectionFactory().get(10, TimeUnit.SECONDS);
            if (!ListenerUtil.mutListener.listen(58986)) {
                if (!factoryCreated) {
                    if (!ListenerUtil.mutListener.listen(58985)) {
                        this.abortCall(R.string.voip_error_init_call, "Peer connection factory could not be created", true);
                    }
                }
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(58982)) {
                this.abortCall(R.string.voip_error_init_call, "Interrupted while creating peer connection factory", e, false);
            }
            return;
        } catch (ExecutionException e) {
            if (!ListenerUtil.mutListener.listen(58983)) {
                this.abortCall(R.string.voip_error_init_call, "Exception while waiting for peer connection factory", e, true);
            }
            return;
        } catch (TimeoutException e) {
            if (!ListenerUtil.mutListener.listen(58984)) {
                this.abortCall(R.string.voip_error_init_call, "Failed to create peer connection factory within 10 seconds", e, true);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(58987)) {
            // Maybe enable statistics callback
            this.enableUIDebugStats(this.uiDebugStatsEnabled);
        }
        // directly enable camera if desired
        boolean launchVideo = false;
        if (!ListenerUtil.mutListener.listen(58992)) {
            if (isInitiator) {
                if (!ListenerUtil.mutListener.listen(58991)) {
                    if (intent.getBooleanExtra(VoipCallService.EXTRA_LAUNCH_VIDEO, false)) {
                        if (!ListenerUtil.mutListener.listen(58990)) {
                            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                                if (!ListenerUtil.mutListener.listen(58988)) {
                                    launchVideo = true;
                                }
                                if (!ListenerUtil.mutListener.listen(58989)) {
                                    intent.putExtra(VoipCallService.EXTRA_LAUNCH_VIDEO, false);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58993)) {
            // Start the call
            startCall(!isInitiator, launchVideo);
        }
    }

    @UiThread
    public boolean onToggleMic() {
        if (!ListenerUtil.mutListener.listen(58994)) {
            micEnabled = !micEnabled;
        }
        final long callId = this.voipStateService.getCallState().getCallId();
        if (!ListenerUtil.mutListener.listen(58995)) {
            logCallDebug(callId, "onToggleMic enabled = {}", micEnabled);
        }
        if (!ListenerUtil.mutListener.listen(58997)) {
            if (peerConnectionClient != null) {
                if (!ListenerUtil.mutListener.listen(58996)) {
                    peerConnectionClient.setLocalAudioTrackEnabled(micEnabled);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(58998)) {
            this.audioManager.setMicEnabled(micEnabled);
        }
        return micEnabled;
    }

    @UiThread
    public synchronized void onToggleAudioDevice(AudioDevice audioDevice) {
        final long callId = this.voipStateService.getCallState().getCallId();
        if (!ListenerUtil.mutListener.listen(58999)) {
            logCallInfo(callId, "Change audio device to {}", audioDevice);
        }
        if (!ListenerUtil.mutListener.listen(59006)) {
            if (this.audioManager != null) {
                if (!ListenerUtil.mutListener.listen(59005)) {
                    // Do the switch if possible
                    if (this.audioManager.hasAudioDevice(audioDevice)) {
                        if (!ListenerUtil.mutListener.listen(59004)) {
                            this.audioManager.selectAudioDevice(audioDevice);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(59002)) {
                            this.showSingleToast("Cannot switch to " + audioDevice, Toast.LENGTH_LONG);
                        }
                        if (!ListenerUtil.mutListener.listen(59003)) {
                            logCallError(callId, "Cannot switch to {}: Device not available", audioDevice);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59000)) {
                    this.showSingleToast("Cannot change audio device", Toast.LENGTH_LONG);
                }
                if (!ListenerUtil.mutListener.listen(59001)) {
                    logCallError(callId, "Cannot change audio device: Audio manager is null");
                }
            }
        }
    }

    @AnyThread
    private synchronized void enableUIDebugStats(boolean enable) {
        if (!ListenerUtil.mutListener.listen(59007)) {
            logger.info("Enable UI debug stats: {}", enable);
        }
        if (!ListenerUtil.mutListener.listen(59009)) {
            if (this.peerConnectionClient == null) {
                if (!ListenerUtil.mutListener.listen(59008)) {
                    logger.error("Cannot enable/disable UI debug stats: Peer connection client is null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59010)) {
            this.uiDebugStatsEnabled = enable;
        }
        if (!ListenerUtil.mutListener.listen(59014)) {
            if (enable) {
                if (!ListenerUtil.mutListener.listen(59013)) {
                    if (!this.peerConnectionClient.isPeriodicStatsRegistered(this.activityStatsCollector)) {
                        if (!ListenerUtil.mutListener.listen(59012)) {
                            this.peerConnectionClient.registerPeriodicStats(this.activityStatsCollector, VoipCallService.ACTIVITY_STATS_INTERVAL_MS);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59011)) {
                    this.peerConnectionClient.unregisterPeriodicStats(this.activityStatsCollector);
                }
            }
        }
    }

    @UiThread
    private synchronized void startCall(boolean startActivity, boolean launchVideo) {
        final long callId = this.voipStateService.getCallState().getCallId();
        if (!ListenerUtil.mutListener.listen(59015)) {
            logCallInfo(callId, "Start call");
        }
        if (!ListenerUtil.mutListener.listen(59016)) {
            this.callStartedTimeMs = System.currentTimeMillis();
        }
        if (!ListenerUtil.mutListener.listen(59017)) {
            callStartedRealtimeMs = SystemClock.elapsedRealtime();
        }
        if (!ListenerUtil.mutListener.listen(59018)) {
            // Show notification
            this.showInCallNotification(this.callStartedTimeMs, callStartedRealtimeMs);
        }
        if (!ListenerUtil.mutListener.listen(59019)) {
            logCallInfo(callId, "Video calls are {}", this.videoEnabled ? "enabled" : "disabled");
        }
        // Make sure that the peerConnectionClient is initialized
        @StringRes
        final int initError = R.string.voip_error_init_call;
        if (!ListenerUtil.mutListener.listen(59024)) {
            if (this.peerConnectionClient == null) {
                if (!ListenerUtil.mutListener.listen(59023)) {
                    this.abortCall(initError, "Cannot start call: peerConnectionClient is not initialized", false);
                }
                return;
            } else if (contact == null) {
                if (!ListenerUtil.mutListener.listen(59022)) {
                    this.abortCall(initError, "Cannot start call: contact is not initialized", false);
                }
                return;
            } else if ((ListenerUtil.mutListener.listen(59020) ? (this.videoEnabled || this.voipStateService.getVideoContext() == null) : (this.videoEnabled && this.voipStateService.getVideoContext() == null))) {
                if (!ListenerUtil.mutListener.listen(59021)) {
                    this.abortCall(initError, "Cannot start call: video context is not initialized", false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59025)) {
            logCallInfo(callId, "Setting up call with {}", contact.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59030)) {
            // Start activity if desired
            if (startActivity) {
                final Intent intent = new Intent(this.getApplicationContext(), CallActivity.class);
                if (!ListenerUtil.mutListener.listen(59026)) {
                    intent.putExtra(EXTRA_ACTIVITY_MODE, CallActivity.MODE_ACTIVE_CALL);
                }
                if (!ListenerUtil.mutListener.listen(59027)) {
                    intent.putExtra(EXTRA_CONTACT_IDENTITY, contact.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(59028)) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if (!ListenerUtil.mutListener.listen(59029)) {
                    this.getApplicationContext().startActivity(intent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59031)) {
            // audio modes, audio device enumeration etc.
            this.audioManager = VoipAudioManager.create(getApplicationContext(), voipStateService.getRingtoneAudioFocusAbandoned());
        }
        if (!ListenerUtil.mutListener.listen(59032)) {
            VoipListenerManager.audioManagerListener.add(this.audioManagerListener);
        }
        if (!ListenerUtil.mutListener.listen(59033)) {
            logCallInfo(callId, "Starting the audio manager...");
        }
        if (!ListenerUtil.mutListener.listen(59034)) {
            this.audioManager.start();
        }
        if (!ListenerUtil.mutListener.listen(59039)) {
            // Create peer connection
            logCallInfo(callId, "Creating peer connection, delay={}ms", (ListenerUtil.mutListener.listen(59038) ? (System.currentTimeMillis() % this.callStartedTimeMs) : (ListenerUtil.mutListener.listen(59037) ? (System.currentTimeMillis() / this.callStartedTimeMs) : (ListenerUtil.mutListener.listen(59036) ? (System.currentTimeMillis() * this.callStartedTimeMs) : (ListenerUtil.mutListener.listen(59035) ? (System.currentTimeMillis() + this.callStartedTimeMs) : (System.currentTimeMillis() - this.callStartedTimeMs))))));
        }
        final VideoSink localVideoSink = this.voipStateService.getVideoContext().getLocalVideoSinkProxy();
        final VideoSink remoteVideoSink = this.voipStateService.getVideoContext().getRemoteVideoSinkProxy();
        if (!ListenerUtil.mutListener.listen(59040)) {
            peerConnectionClient.createPeerConnection(localVideoSink, remoteVideoSink);
        }
        if (!ListenerUtil.mutListener.listen(59041)) {
            // Set initial video quality parameters
            this.updateOwnVideoQualityProfile(Boolean.TRUE.equals(this.meteredStatusChangedReceiver.getMetered().getValue()), this.networkIsRelayed);
        }
        if (!ListenerUtil.mutListener.listen(59044)) {
            // Initialize peer connection
            if (this.voipStateService.isInitiator() == Boolean.TRUE) {
                if (!ListenerUtil.mutListener.listen(59043)) {
                    this.initAsInitiator(callId, launchVideo);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59042)) {
                    this.initAsResponder(callId);
                }
            }
        }
    }

    @UiThread
    private void initAsInitiator(long callId, final boolean launchVideo) {
        if (!ListenerUtil.mutListener.listen(59045)) {
            logCallInfo(callId, "Init call as initiator");
        }
        if (!ListenerUtil.mutListener.listen(59047)) {
            // Make sure that the peerConnectionClient is initialized
            if (this.peerConnectionClient == null) {
                if (!ListenerUtil.mutListener.listen(59046)) {
                    this.abortCall(R.string.voip_error_init_call, "Cannot initialize: peerConnectionClient is null", false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59084)) {
            // Register listeners
            this.voipMessageListener = new VoipMessageListener() {

                @Override
                public synchronized void onOffer(final String identity, final VoipCallOfferData data) {
                    if (!ListenerUtil.mutListener.listen(59048)) {
                        logCallError(data.getCallIdOrDefault(0L), "Received offer as initiator");
                    }
                }

                @Override
                public synchronized void onAnswer(final String identity, final VoipCallAnswerData data) {
                    final long callId = data.getCallIdOrDefault(0L);
                    if (!ListenerUtil.mutListener.listen(59049)) {
                        logCallInfo(callId, "Received answer: {}", data.getAction());
                    }
                    if (!ListenerUtil.mutListener.listen(59051)) {
                        // Make sure that the peerConnectionClient is initialized
                        if (peerConnectionClient == null) {
                            if (!ListenerUtil.mutListener.listen(59050)) {
                                logCallError(callId, "Ignoring answer: peerConnectionClient is not initialized");
                            }
                            return;
                        }
                    }
                    // Check state
                    final CallStateSnapshot callState = voipStateService.getCallState();
                    if (!ListenerUtil.mutListener.listen(59053)) {
                        if (!callState.isInitializing()) {
                            if (!ListenerUtil.mutListener.listen(59052)) {
                                logCallError(callId, "Ignoring answer: callState is {}", callState);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(59056)) {
                        // Check contact in answer
                        if (contact == null) {
                            if (!ListenerUtil.mutListener.listen(59055)) {
                                logCallError(callId, "Ignoring answer: contact is not initialized");
                            }
                            return;
                        } else if (!TestUtil.compare(contact.getIdentity(), identity)) {
                            if (!ListenerUtil.mutListener.listen(59054)) {
                                logCallError(callId, "Ignoring answer: Does not match current contact");
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(59058)) {
                        // Parse action
                        if (data.getAction() == null) {
                            if (!ListenerUtil.mutListener.listen(59057)) {
                                logCallError(callId, "Ignoring answer: Action is null");
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(59066)) {
                        switch(data.getAction()) {
                            case VoipCallAnswerData.Action.ACCEPT:
                                break;
                            case VoipCallAnswerData.Action.REJECT:
                                if (!ListenerUtil.mutListener.listen(59059)) {
                                    // Log event
                                    logCallInfo(callId, "Call to {} was rejected (reason code: {})", contact.getIdentity(), data.getRejectReason());
                                }
                                if (!ListenerUtil.mutListener.listen(59060)) {
                                    // Stop ringing tone
                                    stopLoopingSound(callId);
                                }
                                if (!ListenerUtil.mutListener.listen(59061)) {
                                    // Update UI to show disconnecting status
                                    preDisconnect(callId);
                                }
                                if (!ListenerUtil.mutListener.listen(59062)) {
                                    // Disconnect after a while
                                    new Handler(Looper.getMainLooper()).postDelayed(() -> disconnect(), 4050);
                                }
                                // Play busy sound
                                final boolean played = playSound(callId, R.raw.busy_tone, "busy");
                                if (!ListenerUtil.mutListener.listen(59064)) {
                                    if (!played) {
                                        if (!ListenerUtil.mutListener.listen(59063)) {
                                            logger.error("Could not play busy tone!");
                                        }
                                    }
                                }
                                return;
                            default:
                                if (!ListenerUtil.mutListener.listen(59065)) {
                                    abortCall("An error occured while processing the call answer", "Invalid call answer action: " + data.getAction(), false);
                                }
                                return;
                        }
                    }
                    // Parse session description
                    final VoipCallAnswerData.AnswerData answerData = data.getAnswerData();
                    if (!ListenerUtil.mutListener.listen(59068)) {
                        if (answerData == null) {
                            if (!ListenerUtil.mutListener.listen(59067)) {
                                logCallError(callId, "Ignoring answer: Answer data is null");
                            }
                            return;
                        }
                    }
                    final SessionDescription sd = SdpUtil.getAnswerSessionDescription(answerData);
                    if (!ListenerUtil.mutListener.listen(59070)) {
                        if (sd == null) {
                            if (!ListenerUtil.mutListener.listen(59069)) {
                                abortCall("An error occurred while processing the call answer", String.format("Received invalid answer SDP: {} / {}", answerData.getSdpType(), answerData.getSdp()), false);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(59074)) {
                        // Detect video support in answer
                        if (!data.getFeatures().hasFeature(VideoFeature.NAME)) {
                            if (!ListenerUtil.mutListener.listen(59071)) {
                                logCallInfo(callId, "videoEnabled=false, remote does not signal support for video calls");
                            }
                            if (!ListenerUtil.mutListener.listen(59072)) {
                                videoEnabled = false;
                            }
                            if (!ListenerUtil.mutListener.listen(59073)) {
                                VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_DISABLE_VIDEO);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(59075)) {
                        // Set remote description
                        peerConnectionClient.setRemoteDescription(sd);
                    }
                    if (!ListenerUtil.mutListener.listen(59076)) {
                        // Now that the answer is set, we don't need to listen for further messages.
                        VoipListenerManager.messageListener.remove(VoipCallService.this.voipMessageListener);
                    }
                }

                @Override
                public void onRinging(String identity, final VoipCallRingingData data) {
                    long callId = data.getCallIdOrDefault(0L);
                    if (!ListenerUtil.mutListener.listen(59077)) {
                        logCallInfo(callId, "Peer device is ringing");
                    }
                    if (!ListenerUtil.mutListener.listen(59078)) {
                        startLoopingSound(callId, R.raw.ringing_tone, "ringing");
                    }
                    if (!ListenerUtil.mutListener.listen(59079)) {
                        VoipUtil.sendVoipBroadcast(getAppContext(), CallActivity.ACTION_PEER_RINGING);
                    }
                    if (!ListenerUtil.mutListener.listen(59081)) {
                        if (launchVideo) {
                            if (!ListenerUtil.mutListener.listen(59080)) {
                                startCapturing();
                            }
                        }
                    }
                }

                @Override
                public void onHangup(String identity, final VoipCallHangupData data) {
                    if (!ListenerUtil.mutListener.listen(59082)) {
                        logCallInfo(data.getCallIdOrDefault(0L), "Received hangup from peer");
                    }
                }

                @Override
                public boolean handle(final String identity) {
                    return (ListenerUtil.mutListener.listen(59083) ? (contact != null || TestUtil.compare(contact.getIdentity(), identity)) : (contact != null && TestUtil.compare(contact.getIdentity(), identity)));
                }
            };
        }
        if (!ListenerUtil.mutListener.listen(59085)) {
            VoipListenerManager.messageListener.add(this.voipMessageListener);
        }
        if (!ListenerUtil.mutListener.listen(59086)) {
            logCallInfo(callId, "Creating offer...");
        }
        if (!ListenerUtil.mutListener.listen(59087)) {
            this.peerConnectionClient.createOffer();
        }
    }

    @UiThread
    private void initAsResponder(long callId) {
        if (!ListenerUtil.mutListener.listen(59088)) {
            logCallInfo(callId, "Init call as responder");
        }
        if (!ListenerUtil.mutListener.listen(59090)) {
            // Make sure that the peerConnectionClient is initialized
            if (this.peerConnectionClient == null) {
                if (!ListenerUtil.mutListener.listen(59089)) {
                    abortCall(R.string.voip_error_init_call, "this.peerConnectionClient is null, even though it should be initialized", true);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59092)) {
            // Parse offer session description
            if (this.offerSessionDescription == null) {
                if (!ListenerUtil.mutListener.listen(59091)) {
                    abortCall(R.string.voip_error_init_call, "this.offerSessionDescription is null, even though it should be initialized", true);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59093)) {
            // Set remote description
            logCallInfo(callId, "Setting remote description");
        }
        if (!ListenerUtil.mutListener.listen(59094)) {
            this.peerConnectionClient.setRemoteDescription(this.offerSessionDescription);
        }
    }

    /**
     *  A new candidate message was received.
     */
    @UiThread
    private void handleNewCandidate(final String contactIdentity, @NonNull final VoipICECandidatesData candidatesData) {
        final long currentCallId = this.voipStateService.getCallState().getCallId();
        if (!ListenerUtil.mutListener.listen(59096)) {
            // Sanity checks
            if (contact == null) {
                if (!ListenerUtil.mutListener.listen(59095)) {
                    logCallInfo(currentCallId, "Ignore candidates from broadcast, contact hasn't been initialized yet");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59098)) {
            if (!TestUtil.compare(contactIdentity, contact.getIdentity())) {
                if (!ListenerUtil.mutListener.listen(59097)) {
                    logCallInfo(currentCallId, "Ignore candidates from broadcast targeted at another identity (current {}, target {})", contact.getIdentity(), contactIdentity);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59099)) {
            logCallInfo(currentCallId, "Process candidates from broadcast");
        }
        if (!ListenerUtil.mutListener.listen(59100)) {
            this.processCandidates(currentCallId, candidatesData);
        }
    }

    /**
     *  Called as soon as the peer connection has been established.
     */
    @AnyThread
    private synchronized void callConnected(final long callId) {
        final long delta = (ListenerUtil.mutListener.listen(59104) ? (System.currentTimeMillis() % this.callStartedTimeMs) : (ListenerUtil.mutListener.listen(59103) ? (System.currentTimeMillis() / this.callStartedTimeMs) : (ListenerUtil.mutListener.listen(59102) ? (System.currentTimeMillis() * this.callStartedTimeMs) : (ListenerUtil.mutListener.listen(59101) ? (System.currentTimeMillis() + this.callStartedTimeMs) : (System.currentTimeMillis() - this.callStartedTimeMs)))));
        if (!ListenerUtil.mutListener.listen(59106)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(59105)) {
                    this.showSingleToast("Call " + callId + " connected: delay=" + delta + "ms", Toast.LENGTH_LONG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59109)) {
            if ((ListenerUtil.mutListener.listen(59107) ? (this.peerConnectionClient == null && this.isError) : (this.peerConnectionClient == null || this.isError))) {
                if (!ListenerUtil.mutListener.listen(59108)) {
                    this.abortCall(R.string.voip_error_call, callId + ": Call is connected in closed or error state", false);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59110)) {
            // Update state
            this.voipStateService.setStateCalling(callId);
        }
        if (!ListenerUtil.mutListener.listen(59111)) {
            // Stop ringing tone
            this.stopLoopingSound(callId);
        }
        // Play pickup sound
        final boolean played = this.playSound(callId, R.raw.threema_pickup, "pickup");
        if (!ListenerUtil.mutListener.listen(59113)) {
            if (!played) {
                if (!ListenerUtil.mutListener.listen(59112)) {
                    logCallError(callId, "Could not play pickup sound!");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59114)) {
            // Start call duration counter
            VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_CONNECTED);
        }
        // Send inital configuration
        try (CloseableLock ignored = this.videoQualityNegotiation.read()) {
            if (!ListenerUtil.mutListener.listen(59116)) {
                if (this.localVideoQualityProfile != null) {
                    if (!ListenerUtil.mutListener.listen(59115)) {
                        this.peerConnectionClient.sendSignalingMessage(this.localVideoQualityProfile);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59119)) {
            // Notify listeners
            if (contact == null) {
                if (!ListenerUtil.mutListener.listen(59118)) {
                    logCallError(callId, "contact is null in callConnected()");
                }
            } else {
                final String contactIdentity = contact.getIdentity();
                final Boolean isInitiator = this.voipStateService.isInitiator();
                if (!ListenerUtil.mutListener.listen(59117)) {
                    VoipListenerManager.callEventListener.handle(listener -> {
                        if (isInitiator == null) {
                            logCallError(callId, "voipStateService.isInitiator() is null in callConnected()");
                        } else {
                            listener.onStarted(contactIdentity, isInitiator);
                        }
                    });
                }
            }
        }
    }

    /**
     *  This is run to initialize the disconnecting process.
     *
     *  The method is only needed if there is a delay between starting and finishing the
     *  disconnection, e.g. when blocking the UI for a few seconds to play the "busy" sound.
     */
    @AnyThread
    private synchronized void preDisconnect(long callId) {
        if (!ListenerUtil.mutListener.listen(59120)) {
            logCallInfo(callId, "Pre-disconnect");
        }
        if (!ListenerUtil.mutListener.listen(59124)) {
            if ((ListenerUtil.mutListener.listen(59121) ? (this.voipStateService != null || !this.voipStateService.getCallState().isIdle()) : (this.voipStateService != null && !this.voipStateService.getCallState().isIdle()))) {
                if (!ListenerUtil.mutListener.listen(59122)) {
                    this.voipStateService.setStateDisconnecting(callId);
                }
                if (!ListenerUtil.mutListener.listen(59123)) {
                    VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_PRE_DISCONNECT);
                }
            }
        }
    }

    /**
     *  Clean up resources if they haven't been cleaned yet.
     *
     *  This method can safely be called multiple times.
     */
    private void cleanup() {
        if (!ListenerUtil.mutListener.listen(59125)) {
            logger.info("Cleaning up resources");
        }
        // Stop timers
        synchronized (this.iceDisconnectedSoundTimer) {
            if (!ListenerUtil.mutListener.listen(59126)) {
                logger.info("Cancel iceDisconnectedSoundTimeout");
            }
            if (!ListenerUtil.mutListener.listen(59129)) {
                if (this.iceDisconnectedSoundTimeout != null) {
                    if (!ListenerUtil.mutListener.listen(59127)) {
                        this.iceDisconnectedSoundTimeout.cancel();
                    }
                    if (!ListenerUtil.mutListener.listen(59128)) {
                        this.iceDisconnectedSoundTimeout = null;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59132)) {
            // Remove listeners
            if (this.voipMessageListener != null) {
                if (!ListenerUtil.mutListener.listen(59130)) {
                    VoipListenerManager.messageListener.remove(this.voipMessageListener);
                }
                if (!ListenerUtil.mutListener.listen(59131)) {
                    this.voipMessageListener = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59144)) {
            // Close peerConnectionClient
            if (this.peerConnectionClient != null) {
                if (!ListenerUtil.mutListener.listen(59133)) {
                    // candidate pairs.
                    this.iceConnected = false;
                }
                synchronized (this) {
                    if (!ListenerUtil.mutListener.listen(59134)) {
                        logger.info("Unregister debug stats collector");
                    }
                    // Unregister debug stats collector & do a final stats collection
                    final VoipStats.Builder statsBuilder = new VoipStats.Builder().withSelectedCandidatePair(false).withTransport(true).withCrypto(true).withRtp(true).withTracks(true).withCodecs(false).withCandidatePairs(VoipStats.CandidatePairVariant.OVERVIEW_AND_DETAILED);
                    if (!ListenerUtil.mutListener.listen(59135)) {
                        this.peerConnectionClient.unregisterPeriodicStats(this.debugStatsCollector);
                    }
                    if (!ListenerUtil.mutListener.listen(59136)) {
                        this.debugStatsCollector = new CallStatsCollectorCallback(statsBuilder);
                    }
                    if (!ListenerUtil.mutListener.listen(59137)) {
                        this.peerConnectionClient.setAfterClosingStatsCallback(this.debugStatsCollector);
                    }
                    if (!ListenerUtil.mutListener.listen(59140)) {
                        // Unregister frame detector
                        if (this.frameDetector != null) {
                            if (!ListenerUtil.mutListener.listen(59138)) {
                                this.peerConnectionClient.unregisterPeriodicStats(this.frameDetector);
                            }
                            if (!ListenerUtil.mutListener.listen(59139)) {
                                this.frameDetector = null;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(59141)) {
                    logger.info("Closing peer connection client");
                }
                if (!ListenerUtil.mutListener.listen(59142)) {
                    this.peerConnectionClient.close();
                }
                if (!ListenerUtil.mutListener.listen(59143)) {
                    this.peerConnectionClient = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59149)) {
            // Stop audio manager
            if (this.audioManager != null) {
                if (!ListenerUtil.mutListener.listen(59145)) {
                    logger.info("Stopping audio manager");
                }
                if (!ListenerUtil.mutListener.listen(59146)) {
                    VoipListenerManager.audioManagerListener.remove(this.audioManagerListener);
                }
                if (!ListenerUtil.mutListener.listen(59147)) {
                    this.audioManager.stop();
                }
                if (!ListenerUtil.mutListener.listen(59148)) {
                    this.audioManager = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59154)) {
            // Stop media players
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(59150)) {
                    logger.info("Stopping and releasing ringing tone media player");
                }
                if (!ListenerUtil.mutListener.listen(59151)) {
                    this.mediaPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(59152)) {
                    this.mediaPlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(59153)) {
                    this.mediaPlayer = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59156)) {
            // Stop CPU monitor
            if (this.cpuMonitor != null) {
                if (!ListenerUtil.mutListener.listen(59155)) {
                    this.cpuMonitor.pause();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59161)) {
            // Update state
            if (this.voipStateService != null) {
                if (!ListenerUtil.mutListener.listen(59157)) {
                    logger.info("Releasing video context, transition to IDLE state");
                }
                if (!ListenerUtil.mutListener.listen(59158)) {
                    // Release video context
                    this.voipStateService.releaseVideoContext();
                }
                if (!ListenerUtil.mutListener.listen(59159)) {
                    this.voipStateService.setVideoRenderMode(VIDEO_RENDER_FLAG_NONE);
                }
                if (!ListenerUtil.mutListener.listen(59160)) {
                    this.voipStateService.setStateIdle();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59162)) {
            logger.info("Cleanup done");
        }
    }

    /**
     *  Disconnect from remote resources, dispose of local resources, and exit.
     *
     *  If a message is specified, it is appended to the "call has ended" toast.
     */
    @UiThread
    private synchronized void disconnect(@Nullable String message) {
        // Get call ID (note: May already be reset to 0)
        final CallStateSnapshot callState = this.voipStateService.getCallState();
        final long callId = callState.getCallId();
        if (!ListenerUtil.mutListener.listen(59163)) {
            logCallInfo(callId, "disconnect (isConnected? {} | isError? {} | message: {})", this.iceConnected, this.isError, message);
        }
        if (!ListenerUtil.mutListener.listen(59168)) {
            // If the call is still connected, notify listeners about the finishing
            if (this.voipStateService != null) {
                if (!ListenerUtil.mutListener.listen(59166)) {
                    if ((ListenerUtil.mutListener.listen(59164) ? (callState.isCalling() || contact != null) : (callState.isCalling() && contact != null))) {
                        // Notify listeners
                        final String contactIdentity = contact.getIdentity();
                        final Boolean isInitiator = this.voipStateService.isInitiator();
                        final Integer duration = this.voipStateService.getCallDuration();
                        if (!ListenerUtil.mutListener.listen(59165)) {
                            VoipListenerManager.callEventListener.handle(listener -> {
                                if (isInitiator == null) {
                                    logger.error("isInitiator is null in disconnect()");
                                } else if (duration == null) {
                                    logger.error("duration is null in disconnect()");
                                } else {
                                    listener.onFinished(contactIdentity, isInitiator, duration);
                                }
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(59167)) {
                    WearableHandler.cancelOnWearable(VoipStateService.TYPE_ACTIVITY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59170)) {
            if (PushService.playServicesInstalled(getAppContext())) {
                if (!ListenerUtil.mutListener.listen(59169)) {
                    WearableHandler.cancelOnWearable(VoipStateService.TYPE_ACTIVITY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59171)) {
            this.preDisconnect(callId);
        }
        if (!ListenerUtil.mutListener.listen(59172)) {
            this.cleanup();
        }
        if (!ListenerUtil.mutListener.listen(59173)) {
            stopForeground(true);
        }
        if (!ListenerUtil.mutListener.listen(59177)) {
            if ((ListenerUtil.mutListener.listen(59174) ? (this.iceConnected || !this.isError) : (this.iceConnected && !this.isError))) {
                if (!ListenerUtil.mutListener.listen(59176)) {
                    VoipUtil.sendVoipBroadcast(this, CallActivity.ACTION_DISCONNECTED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59175)) {
                    VoipUtil.sendVoipBroadcast(this, CallActivity.ACTION_CANCELLED);
                }
            }
        }
        String toastMsg = getString(R.string.voip_call_finished);
        if (!ListenerUtil.mutListener.listen(59179)) {
            if (message != null) {
                if (!ListenerUtil.mutListener.listen(59178)) {
                    toastMsg += ": " + message;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59180)) {
            this.showSingleToast(toastMsg, Toast.LENGTH_LONG);
        }
        if (!ListenerUtil.mutListener.listen(59181)) {
            stopSelf();
        }
    }

    @UiThread
    private synchronized void disconnect() {
        if (!ListenerUtil.mutListener.listen(59182)) {
            this.disconnect(null);
        }
    }

    /**
     *  Add or remove ICE candidates.
     */
    private void processCandidates(long callId, @NonNull VoipICECandidatesData data) {
        if (!ListenerUtil.mutListener.listen(59184)) {
            // Null check
            if (this.peerConnectionClient == null) {
                if (!ListenerUtil.mutListener.listen(59183)) {
                    logCallWarning(callId, "Ignored ICE candidate message, peerConnectionClient is null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59197)) {
            // IPv6 check
            if (!this.preferenceService.allowWebrtcIpv6()) {
                final int prevSize = data.getCandidates().length;
                if (!ListenerUtil.mutListener.listen(59185)) {
                    data.filter(candidate -> !SdpUtil.isIpv6Candidate(candidate.getCandidate()));
                }
                final int newSize = data.getCandidates().length;
                if (!ListenerUtil.mutListener.listen(59196)) {
                    if ((ListenerUtil.mutListener.listen(59190) ? (newSize >= prevSize) : (ListenerUtil.mutListener.listen(59189) ? (newSize <= prevSize) : (ListenerUtil.mutListener.listen(59188) ? (newSize > prevSize) : (ListenerUtil.mutListener.listen(59187) ? (newSize != prevSize) : (ListenerUtil.mutListener.listen(59186) ? (newSize == prevSize) : (newSize < prevSize))))))) {
                        if (!ListenerUtil.mutListener.listen(59195)) {
                            logCallInfo(callId, "Ignored {} remote IPv6 candidate (disabled via preferences)", (ListenerUtil.mutListener.listen(59194) ? (prevSize % newSize) : (ListenerUtil.mutListener.listen(59193) ? (prevSize / newSize) : (ListenerUtil.mutListener.listen(59192) ? (prevSize * newSize) : (ListenerUtil.mutListener.listen(59191) ? (prevSize + newSize) : (prevSize - newSize))))));
                        }
                    }
                }
            }
        }
        // Add or remove candidates
        final IceCandidate[] candidates = SdpUtil.getIceCandidates(data.getCandidates());
        if (!ListenerUtil.mutListener.listen(59199)) {
            {
                long _loopCounter699 = 0;
                for (IceCandidate candidate : candidates) {
                    ListenerUtil.loopListener.listen("_loopCounter699", ++_loopCounter699);
                    if (!ListenerUtil.mutListener.listen(59198)) {
                        this.peerConnectionClient.addRemoteIceCandidate(candidate);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59200)) {
            // Log candidates
            logCallInfo(callId, "Added {} VoIP ICE candidate(s):", candidates.length);
        }
        if (!ListenerUtil.mutListener.listen(59202)) {
            {
                long _loopCounter700 = 0;
                for (IceCandidate candidate : candidates) {
                    ListenerUtil.loopListener.listen("_loopCounter700", ++_loopCounter700);
                    if (!ListenerUtil.mutListener.listen(59201)) {
                        logCallInfo(callId, "  Incoming candidate: {}", candidate.sdp);
                    }
                }
            }
        }
    }

    /**
     *  Show a toast. Runs on the UI thread.
     */
    @AnyThread
    private void showSingleToast(final String msg, final int length) {
        if (!ListenerUtil.mutListener.listen(59203)) {
            RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showBottom(msg, length));
        }
    }

    public VoipCallService() {
        super();
    }

    /**
     *  Abort call due to an error.
     *
     *  The message(s) will be logged, followed by a disconnect.
     *
     *  @param userMessage A user facing message that's shown in the post-call toast message.
     *  @param internalMessage An (optional) internal message that's being logged.
     *  @param throwable A (optional) {@link Throwable} that's logged.
     *  @param showErrorNotification If set to true, the message and throwable will be shown as a {@link BackgroundErrorNotification}.
     */
    @AnyThread
    private synchronized void abortCall(@NonNull final String userMessage, @Nullable final String internalMessage, @Nullable final Throwable throwable, boolean showErrorNotification) {
        // If internal message is not specified, reuse user message
        final String description = internalMessage != null ? internalMessage : userMessage;
        if (!ListenerUtil.mutListener.listen(59210)) {
            if (this.voipStateService != null) {
                // Log error
                final long callId = this.voipStateService.getCallState().getCallId();
                if (!ListenerUtil.mutListener.listen(59209)) {
                    if (throwable != null) {
                        if (!ListenerUtil.mutListener.listen(59208)) {
                            logCallError(callId, "Aborting call: " + description, throwable);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(59207)) {
                            logCallError(callId, "Aborting call: {}", description);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59206)) {
                    if (throwable != null) {
                        if (!ListenerUtil.mutListener.listen(59205)) {
                            logger.error("Aborting call: " + description, throwable);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(59204)) {
                            logger.error("Aborting call: {}", description);
                        }
                    }
                }
            }
        }
        // Update isError
        boolean wasError = this.isError;
        if (!ListenerUtil.mutListener.listen(59211)) {
            this.isError = true;
        }
        if (!ListenerUtil.mutListener.listen(59213)) {
            // as soon as we disconnect).
            if (!this.foregroundStarted) {
                if (!ListenerUtil.mutListener.listen(59212)) {
                    this.showInCallNotification(this.callStartedTimeMs, callStartedRealtimeMs);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59214)) {
            RuntimeUtil.runOnUiThread(() -> {
                // one notification is generated by checking `wasError`)
                if (showErrorNotification && !wasError) {
                    BackgroundErrorNotification.showNotification(getAppContext(), getString(R.string.voip_error_call), description, "VoipCallService", true, throwable);
                }
                // Disconnect
                disconnect(userMessage);
            });
        }
    }

    /**
     *  User message string resource will be resolved.
     *  @see #abortCall(String, String, Throwable, boolean)
     */
    @AnyThread
    private synchronized void abortCall(@StringRes final int userMessage, @Nullable final String internalMessage, @Nullable final Throwable throwable, boolean showErrorNotification) {
        if (!ListenerUtil.mutListener.listen(59215)) {
            this.abortCall(getString(userMessage), internalMessage, throwable, showErrorNotification);
        }
    }

    /**
     *  Throwable defaults to `null`.
     *  @see #abortCall(String, String, Throwable, boolean)
     */
    @AnyThread
    private synchronized void abortCall(@NonNull final String userMessage, @Nullable final String internalMessage, boolean showErrorNotification) {
        if (!ListenerUtil.mutListener.listen(59216)) {
            this.abortCall(userMessage, internalMessage, null, showErrorNotification);
        }
    }

    /**
     *  Throwable defaults to `null`.
     *  User message string resource will be resolved.
     *  @see #abortCall(String, String, Throwable, boolean)
     */
    @AnyThread
    private synchronized void abortCall(@StringRes final int userMessage, @Nullable final String internalMessage, boolean showErrorNotification) {
        if (!ListenerUtil.mutListener.listen(59217)) {
            this.abortCall(userMessage, internalMessage, null, showErrorNotification);
        }
        if (!ListenerUtil.mutListener.listen(59219)) {
            if (PushService.playServicesInstalled(getAppContext())) {
                if (!ListenerUtil.mutListener.listen(59218)) {
                    WearableHandler.cancelOnWearable(VoipStateService.TYPE_ACTIVITY);
                }
            }
        }
    }

    private static void logCallTrace(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59220)) {
            logger.trace("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallTrace(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59221)) {
            logger.trace("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallDebug(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59222)) {
            logger.debug("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallDebug(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59223)) {
            logger.debug("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallInfo(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59224)) {
            logger.info("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallInfo(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59225)) {
            logger.info("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallWarning(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59226)) {
            logger.warn("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallWarning(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59227)) {
            logger.warn("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallError(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59228)) {
            logger.error("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallError(long callId, String message, Throwable t) {
        if (!ListenerUtil.mutListener.listen(59229)) {
            logger.error("[cid=" + callId + "]: " + message, t);
        }
    }

    private static void logCallError(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59230)) {
            logger.error("[cid=" + callId + "]: " + message, arguments);
        }
    }

    @Override
    @AnyThread
    public void onLocalDescription(long callId, final SessionDescription sdp) {
        if (!ListenerUtil.mutListener.listen(59231)) {
            new Thread(() -> {
                logCallInfo(callId, "onLocalDescription");
                synchronized (VoipCallService.this) {
                    final CallStateSnapshot callState = voipStateService.getCallState();
                    logCallInfo(callId, "Sending {} in call state {}", sdp.type, callState.getName());
                    if (callState.isInitializing() || callState.isRinging()) {
                        try {
                            if (this.voipStateService.isInitiator() == Boolean.TRUE) {
                                this.voipStateService.sendCallOfferMessage(contact, callId, sdp, this.videoEnabled);
                            } else {
                                this.voipStateService.sendAcceptCallAnswerMessage(contact, callId, sdp, this.videoEnabled);
                            }
                        } catch (ThreemaException | IllegalArgumentException e) {
                            this.abortCall(R.string.voip_error_init_call, "Could not send offer or answer message", e, false);
                        }
                    } else {
                        logCallInfo(callId, "Discarding local description (wrong state)");
                    }
                }
            }, callId + ".onLocalDescription").start();
        }
    }

    @Override
    @AnyThread
    public void onRemoteDescriptionSet(long callId) {
        if (!ListenerUtil.mutListener.listen(59232)) {
            logCallInfo(callId, "onRemoteDescriptionSet");
        }
        if (!ListenerUtil.mutListener.listen(59234)) {
            if (this.peerConnectionClient == null) {
                if (!ListenerUtil.mutListener.listen(59233)) {
                    logCallError(callId, "Cannot create answer: peerConnectionClient is not initialized");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59237)) {
            if (this.voipStateService.isInitiator() == Boolean.FALSE) {
                if (!ListenerUtil.mutListener.listen(59235)) {
                    logCallInfo(callId, "Creating answer...");
                }
                if (!ListenerUtil.mutListener.listen(59236)) {
                    peerConnectionClient.createAnswer();
                }
            }
        }
    }

    /**
     *  Send a local ICE candidates.
     *
     *  Note: If the callState is not RINGING, INITIALIZING or CALLING,
     *        then the candidate will be disposed.
     */
    private void sendIceCandidate(long callId, @NonNull final IceCandidate candidate) {
        try {
            // to prevent a "candidate leak" otherwise.
            final CallStateSnapshot callState = this.voipStateService.getCallState();
            if (!ListenerUtil.mutListener.listen(59242)) {
                if (!((ListenerUtil.mutListener.listen(59240) ? ((ListenerUtil.mutListener.listen(59239) ? (callState.isRinging() && callState.isInitializing()) : (callState.isRinging() || callState.isInitializing())) && callState.isCalling()) : ((ListenerUtil.mutListener.listen(59239) ? (callState.isRinging() && callState.isInitializing()) : (callState.isRinging() || callState.isInitializing())) || callState.isCalling())))) {
                    if (!ListenerUtil.mutListener.listen(59241)) {
                        logCallInfo(callId, "Disposing ICE candidate, callState is {}", callState.getName());
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(59243)) {
                // Log candidate
                logCallInfo(callId, "Sending VoIP ICE candidate: {}", candidate.sdp);
            }
            if (!ListenerUtil.mutListener.listen(59244)) {
                // Send
                this.voipStateService.sendICECandidatesMessage(contact, callId, new IceCandidate[] { candidate });
            }
        } catch (ThreemaException | IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(59238)) {
                logCallError(callId, "Could not send ICE candidate", e);
            }
        }
    }

    @Override
    @AnyThread
    public void onIceCandidate(long callId, final IceCandidate candidate) {
        if (!ListenerUtil.mutListener.listen(59245)) {
            logCallTrace(callId, "onIceCandidate");
        }
        if (!ListenerUtil.mutListener.listen(59246)) {
            // Send candidate
            logCallTrace(callId, "onIceCandidate: {}", candidate.sdp);
        }
        if (!ListenerUtil.mutListener.listen(59247)) {
            VoipCallService.this.sendIceCandidate(callId, candidate);
        }
    }

    @Override
    @AnyThread
    public void onIceChecking(long callId) {
        if (!ListenerUtil.mutListener.listen(59248)) {
            logCallInfo(callId, "ICE checking");
        }
        synchronized (this) {
            if (!ListenerUtil.mutListener.listen(59252)) {
                if (this.peerConnectionClient != null) {
                    // Register debug stats collector (fast interval until connected)
                    final VoipStats.Builder statsBuilder = new VoipStats.Builder().withSelectedCandidatePair(false).withTransport(true).withCrypto(true).withRtp(true).withTracks(true).withCodecs(false).withCandidatePairs(VoipStats.CandidatePairVariant.OVERVIEW_AND_DETAILED);
                    if (!ListenerUtil.mutListener.listen(59249)) {
                        this.peerConnectionClient.unregisterPeriodicStats(this.debugStatsCollector);
                    }
                    if (!ListenerUtil.mutListener.listen(59250)) {
                        this.debugStatsCollector = new CallStatsCollectorCallback(statsBuilder);
                    }
                    if (!ListenerUtil.mutListener.listen(59251)) {
                        this.peerConnectionClient.registerPeriodicStats(this.debugStatsCollector, VoipCallService.LOG_STATS_INTERVAL_MS_CONNECTING);
                    }
                }
            }
        }
    }

    @Override
    @AnyThread
    public void onIceConnected(long callId) {
        if (!ListenerUtil.mutListener.listen(59253)) {
            logCallInfo(callId, "ICE connected");
        }
        if (!ListenerUtil.mutListener.listen(59254)) {
            this.iceConnected = true;
        }
        if (!ListenerUtil.mutListener.listen(59272)) {
            if (this.iceWasConnected) {
                // is scheduled or playing right now. Cancel and stop it.
                synchronized (this.iceDisconnectedSoundTimer) {
                    if (!ListenerUtil.mutListener.listen(59266)) {
                        if (this.iceDisconnectedSoundTimeout != null) {
                            if (!ListenerUtil.mutListener.listen(59264)) {
                                this.iceDisconnectedSoundTimeout.cancel();
                            }
                            if (!ListenerUtil.mutListener.listen(59265)) {
                                this.iceDisconnectedSoundTimeout = null;
                            }
                        }
                    }
                }
                boolean wasPlaying = this.mediaPlayer != null;
                if (!ListenerUtil.mutListener.listen(59267)) {
                    this.stopLoopingSound(callId);
                }
                if (!ListenerUtil.mutListener.listen(59268)) {
                    // Notify activity about reconnection
                    VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_RECONNECTED);
                }
                if (!ListenerUtil.mutListener.listen(59271)) {
                    // Play pickup sound
                    if (wasPlaying) {
                        final boolean played = this.playSound(callId, R.raw.threema_pickup, "pickup");
                        if (!ListenerUtil.mutListener.listen(59270)) {
                            if (!played) {
                                if (!ListenerUtil.mutListener.listen(59269)) {
                                    logCallError(callId, "Could not play pickup sound!");
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59255)) {
                    // This is the initial "connected" event
                    this.iceWasConnected = true;
                }
                if (!ListenerUtil.mutListener.listen(59256)) {
                    this.callConnected(callId);
                }
                synchronized (this) {
                    if (!ListenerUtil.mutListener.listen(59263)) {
                        // Register debug stats collector (slow interval since we're connected)
                        if (this.peerConnectionClient != null) {
                            final VoipStats.Builder statsBuilder = new VoipStats.Builder().withSelectedCandidatePair(true).withTransport(true).withCrypto(true).withRtp(true).withTracks(true).withCodecs(false).withCandidatePairs(VoipStats.CandidatePairVariant.OVERVIEW);
                            if (!ListenerUtil.mutListener.listen(59257)) {
                                this.peerConnectionClient.unregisterPeriodicStats(this.debugStatsCollector);
                            }
                            if (!ListenerUtil.mutListener.listen(59258)) {
                                this.debugStatsCollector = new CallStatsCollectorCallback(statsBuilder);
                            }
                            if (!ListenerUtil.mutListener.listen(59259)) {
                                this.peerConnectionClient.registerPeriodicStats(this.debugStatsCollector, VoipCallService.LOG_STATS_INTERVAL_MS_CONNECTED);
                            }
                            if (!ListenerUtil.mutListener.listen(59262)) {
                                if (this.videoEnabled) {
                                    if (!ListenerUtil.mutListener.listen(59260)) {
                                        this.frameDetector = new FrameDetectorCallback(this.remoteVideoStateDetector::onIncomingVideoFramesStarted, this.remoteVideoStateDetector::onIncomingVideoFramesStopped);
                                    }
                                    if (!ListenerUtil.mutListener.listen(59261)) {
                                        this.peerConnectionClient.registerPeriodicStats(this.frameDetector, VoipCallService.FRAME_DETECTOR_QUERY_INTERVAL_MS);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    @AnyThread
    public void onIceDisconnected(final long callId) {
        if (!ListenerUtil.mutListener.listen(59273)) {
            // or just a temporary connectivity issue that can be recovered.
            logCallInfo(callId, "ICE disconnected");
        }
        if (!ListenerUtil.mutListener.listen(59274)) {
            this.iceConnected = false;
        }
        if (!ListenerUtil.mutListener.listen(59275)) {
            // Notify activity about connectivity problems
            VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_RECONNECTING);
        }
        // Start problem sound with some delay
        synchronized (this.iceDisconnectedSoundTimer) {
            if (!ListenerUtil.mutListener.listen(59278)) {
                this.iceDisconnectedSoundTimeout = new TimerTask() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(59276)) {
                            VoipCallService.this.startLoopingSound(callId, R.raw.threema_problem, "problem");
                        }
                        if (!ListenerUtil.mutListener.listen(59277)) {
                            VoipCallService.this.iceDisconnectedSoundTimeout = null;
                        }
                    }
                };
            }
            if (!ListenerUtil.mutListener.listen(59279)) {
                this.iceDisconnectedSoundTimer.schedule(this.iceDisconnectedSoundTimeout, ICE_DISCONNECTED_SOUND_TIMEOUT_MS);
            }
        }
    }

    @Override
    public void onIceFailed(long callId) {
        if (!ListenerUtil.mutListener.listen(59280)) {
            logCallWarning(callId, "ICE failed");
        }
        if (!ListenerUtil.mutListener.listen(59281)) {
            this.iceConnected = false;
        }
        if (!ListenerUtil.mutListener.listen(59289)) {
            if (this.iceWasConnected) {
                if (!ListenerUtil.mutListener.listen(59288)) {
                    // If we were previously connected, this means that the connection was closed.
                    RuntimeUtil.runOnUiThread(() -> VoipCallService.this.disconnect(getString(R.string.voip_connection_lost)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59282)) {
                    // Otherwise we could never establish a connection in the first place.
                    VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_CONNECTING_FAILED);
                }
                if (!ListenerUtil.mutListener.listen(59285)) {
                    // Send hangup message to notify peer that the connection attempt was aborted
                    if (contact != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(59284)) {
                                this.voipStateService.sendCallHangupMessage(contact, callId);
                            }
                        } catch (ThreemaException e) {
                            if (!ListenerUtil.mutListener.listen(59283)) {
                                logger.error(callId + ": Could not send hangup message", e);
                            }
                        }
                    }
                }
                // Play problem sound and disconnect
                final boolean played = playSound(callId, R.raw.threema_problem, "problem", () -> RuntimeUtil.runOnUiThread(() -> VoipCallService.this.disconnect(getString(R.string.voip_connection_failed))));
                if (!ListenerUtil.mutListener.listen(59287)) {
                    if (!played) {
                        if (!ListenerUtil.mutListener.listen(59286)) {
                            logCallError(callId, "Could not play problem sound!");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onIceGatheringStateChange(long callId, PeerConnection.IceGatheringState newState) {
        if (!ListenerUtil.mutListener.listen(59290)) {
            logCallTrace(callId, "onIceGatheringStateChange");
        }
    }

    @Override
    @AnyThread
    public void onPeerConnectionClosed(long callId) {
        if (!ListenerUtil.mutListener.listen(59291)) {
            logCallTrace(callId, "onPeerConnectionClosed");
        }
        if (!ListenerUtil.mutListener.listen(59292)) {
            logCallInfo(callId, "Peer connection closed");
        }
        // Play disconnect sound
        final boolean played = this.playSound(callId, R.raw.threema_hangup, "disconnect");
        if (!ListenerUtil.mutListener.listen(59294)) {
            if (!played) {
                if (!ListenerUtil.mutListener.listen(59293)) {
                    logCallError(callId, "Could not play disconnect sound!");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59295)) {
            // Call disconnect method
            RuntimeUtil.runOnUiThread(VoipCallService.this::disconnect);
        }
    }

    @Override
    @AnyThread
    public void onError(long callId, @NonNull final String description, boolean abortCall) {
        if (!ListenerUtil.mutListener.listen(59297)) {
            // abort the call.
            if (abortCall) {
                if (!ListenerUtil.mutListener.listen(59296)) {
                    this.abortCall("Peer connection error: " + description, callId + ": " + description, false);
                }
            }
        }
    }

    @Override
    @WorkerThread
    public void onSignalingMessage(long callId, @NonNull CallSignaling.Envelope envelope) {
        if (!ListenerUtil.mutListener.listen(59301)) {
            if (envelope.hasCaptureStateChange()) {
                if (!ListenerUtil.mutListener.listen(59300)) {
                    this.handleCaptureStateChange(callId, envelope.getCaptureStateChange());
                }
            } else if (envelope.hasVideoQualityProfile()) {
                if (!ListenerUtil.mutListener.listen(59299)) {
                    this.handleVideoQualityProfileChange(callId, envelope.getVideoQualityProfile());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59298)) {
                    logCallWarning(callId, "onSignalingMessage: Unknown envelope variant");
                }
            }
        }
    }

    @Override
    public void onCameraFirstFrameAvailable() {
        if (!ListenerUtil.mutListener.listen(59302)) {
            // is received when the capturing state is assumed to be off.
            new Thread(() -> {
                // Start a thread to reduce the chance for deadlocks.
                synchronized (this.capturingLock) {
                    if (!this.isCapturing) {
                        logger.error("WARNING: Received 'onCameraFirstFrameAvailable' event even though capturing should be off!");
                        VoipUtil.sendVoipBroadcast(getAppContext(), CallActivity.ACTION_OUTGOING_VIDEO_STARTED);
                    }
                }
            }).start();
        }
    }

    private interface OnSoundComplete {

        void onComplete();
    }

    /**
     *  Instantiate and start the looping sound media player.
     */
    @AnyThread
    private synchronized void startLoopingSound(long callId, int rawResource, final String soundName) {
        if (!ListenerUtil.mutListener.listen(59304)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(59303)) {
                    logCallError(callId, "Not looping {} sound, mediaPlayer is not null!", soundName);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59305)) {
            logCallInfo(callId, "Looping {} sound...", soundName);
        }
        if (!ListenerUtil.mutListener.listen(59306)) {
            // Initialize media player
            this.mediaPlayer = new MediaPlayerStateWrapper();
        }
        if (!ListenerUtil.mutListener.listen(59307)) {
            this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        }
        if (!ListenerUtil.mutListener.listen(59308)) {
            this.mediaPlayer.setLooping(true);
        }
        // Load and play resource
        AssetFileDescriptor afd = null;
        try {
            if (!ListenerUtil.mutListener.listen(59315)) {
                afd = getResources().openRawResourceFd(rawResource);
            }
            if (!ListenerUtil.mutListener.listen(59316)) {
                this.mediaPlayer.setDataSource(afd);
            }
            if (!ListenerUtil.mutListener.listen(59317)) {
                this.mediaPlayer.prepare();
            }
            if (!ListenerUtil.mutListener.listen(59318)) {
                this.mediaPlayer.start();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(59309)) {
                logger.error("I/O Error", e);
            }
            if (!ListenerUtil.mutListener.listen(59312)) {
                if (this.mediaPlayer != null) {
                    if (!ListenerUtil.mutListener.listen(59310)) {
                        this.mediaPlayer.release();
                    }
                    if (!ListenerUtil.mutListener.listen(59311)) {
                        this.mediaPlayer = null;
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(59314)) {
                if (afd != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(59313)) {
                            afd.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    /**
     *  Stop the currently playing looping sound.
     */
    @AnyThread
    private synchronized void stopLoopingSound(long callId) {
        if (!ListenerUtil.mutListener.listen(59322)) {
            if (this.mediaPlayer != null) {
                if (!ListenerUtil.mutListener.listen(59319)) {
                    logCallInfo(callId, "Stopping looping sound...");
                }
                if (!ListenerUtil.mutListener.listen(59320)) {
                    this.mediaPlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(59321)) {
                    this.mediaPlayer.release();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59323)) {
            this.mediaPlayer = null;
        }
    }

    /**
     *  Play a one-time sound.
     */
    @AnyThread
    private synchronized boolean playSound(long callId, int rawResource, final String soundName, @Nullable final OnSoundComplete onComplete) {
        if (!ListenerUtil.mutListener.listen(59324)) {
            logCallInfo(callId, "Playing {} sound...", soundName);
        }
        // Initialize media player
        final MediaPlayerStateWrapper soundPlayer = new MediaPlayerStateWrapper();
        if (!ListenerUtil.mutListener.listen(59325)) {
            soundPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        }
        if (!ListenerUtil.mutListener.listen(59326)) {
            soundPlayer.setLooping(false);
        }
        // Load and play resource
        AssetFileDescriptor afd = null;
        try {
            if (!ListenerUtil.mutListener.listen(59331)) {
                afd = getResources().openRawResourceFd(rawResource);
            }
            if (!ListenerUtil.mutListener.listen(59332)) {
                soundPlayer.setDataSource(afd);
            }
            if (!ListenerUtil.mutListener.listen(59333)) {
                soundPlayer.prepare();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(59327)) {
                logCallError(callId, "Could not play " + soundName + " sound", e);
            }
            if (!ListenerUtil.mutListener.listen(59328)) {
                soundPlayer.release();
            }
            return false;
        } finally {
            if (!ListenerUtil.mutListener.listen(59330)) {
                if (afd != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(59329)) {
                            afd.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59337)) {
            soundPlayer.setStateListener(new MediaPlayerStateWrapper.StateListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (!ListenerUtil.mutListener.listen(59334)) {
                        mp.release();
                    }
                    if (!ListenerUtil.mutListener.listen(59336)) {
                        if (onComplete != null) {
                            if (!ListenerUtil.mutListener.listen(59335)) {
                                onComplete.onComplete();
                            }
                        }
                    }
                }

                @Override
                public void onPrepared(MediaPlayer mp) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(59338)) {
            soundPlayer.start();
        }
        return true;
    }

    @AnyThread
    private synchronized boolean playSound(long callId, final int rawResource, final String soundName) {
        return this.playSound(callId, rawResource, soundName, null);
    }

    /**
     *  Show the ongoing notification that is shown as long as the call is active.
     *  @param callStartedTimeMs Timestamp at which the call was started (wall time).
     *  @param elapsedTimeMs Timestamp at which the call was started (elapsed monotonic time since boot).
     */
    private synchronized void showInCallNotification(long callStartedTimeMs, long elapsedTimeMs) {
        if (!ListenerUtil.mutListener.listen(59339)) {
            logger.info("Show onging in-call notification");
        }
        // Prepare hangup action
        final Intent hangupIntent = new Intent(this, VoipCallService.class);
        if (!ListenerUtil.mutListener.listen(59340)) {
            hangupIntent.setAction(ACTION_HANGUP);
        }
        final PendingIntent hangupPendingIntent = PendingIntent.getService(this, (int) System.currentTimeMillis(), hangupIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Prepare open action
        final Intent openIntent = new Intent(this, CallActivity.class);
        if (!ListenerUtil.mutListener.listen(59341)) {
            openIntent.putExtra(EXTRA_ACTIVITY_MODE, CallActivity.MODE_ACTIVE_CALL);
        }
        if (!ListenerUtil.mutListener.listen(59342)) {
            openIntent.putExtra(EXTRA_CONTACT_IDENTITY, contact.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59343)) {
            openIntent.putExtra(EXTRA_START_TIME, elapsedTimeMs);
        }
        final PendingIntent openPendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), openIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Prepare notification
        final NotificationCompat.Builder notificationBuilder = new NotificationBuilderWrapper(this, NotificationService.NOTIFICATION_CHANNEL_IN_CALL, null).setContentTitle(NameUtil.getDisplayNameOrNickname(contact, true)).setContentText(getString(R.string.voip_title)).setColor(getResources().getColor(R.color.accent_light)).setLocalOnly(true).setOngoing(true).setUsesChronometer(true).setWhen(callStartedTimeMs).setSmallIcon(R.drawable.ic_phone_locked_white_24dp).setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(openPendingIntent).addAction(R.drawable.ic_call_end_grey600_24dp, getString(R.string.voip_hangup), hangupPendingIntent);
        final Bitmap avatar = contactService.getAvatar(contact, false);
        if (!ListenerUtil.mutListener.listen(59344)) {
            notificationBuilder.setLargeIcon(avatar);
        }
        Notification notification = notificationBuilder.build();
        if (!ListenerUtil.mutListener.listen(59345)) {
            notification.flags |= NotificationCompat.FLAG_NO_CLEAR | NotificationCompat.FLAG_ONGOING_EVENT;
        }
        if (!ListenerUtil.mutListener.listen(59346)) {
            // Launch notification
            this.foregroundStarted = true;
        }
        if (!ListenerUtil.mutListener.listen(59347)) {
            startForeground(INCALL_NOTIFICATION_ID, notification);
        }
        if (!ListenerUtil.mutListener.listen(59348)) {
            // call listener
            ListenerManager.voipCallListeners.handle(listener -> listener.onStart(contact.getIdentity(), elapsedTimeMs));
        }
    }

    private void cancelInCallNotification() {
        if (!ListenerUtil.mutListener.listen(59351)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(59349)) {
                    notificationManager.cancel(INCALL_NOTIFICATION_ID);
                }
                if (!ListenerUtil.mutListener.listen(59350)) {
                    // call listener
                    ListenerManager.voipCallListeners.handle(listener -> listener.onEnd());
                }
            }
        }
    }

    /**
     *  Start capturing (asynchronously).
     */
    @AnyThread
    private void startCapturing() {
        if (!ListenerUtil.mutListener.listen(59352)) {
            new Thread(() -> {
                if (this.peerConnectionClient != null) {
                    try (CloseableLock ignored = this.videoQualityNegotiation.read()) {
                        synchronized (this.capturingLock) {
                            // Start capturing
                            final VideoCapturer videoCapturer = this.peerConnectionClient.startCapturing(this.commonVideoQualityProfile);
                            this.isCapturing = true;
                            // Query cameras
                            if (videoCapturer instanceof CameraVideoCapturer) {
                                final VideoContext videoContext = this.voipStateService.getVideoContext();
                                if (videoContext != null) {
                                    Pair<String, String> primaryCameraNames = VideoCapturerUtil.getPrimaryCameraNames(getAppContext());
                                    videoContext.setFrontCameraName(primaryCameraNames.first);
                                    videoContext.setBackCameraName(primaryCameraNames.second);
                                    videoContext.setCameraVideoCapturer((CameraVideoCapturer) videoCapturer);
                                }
                            }
                            // Notify listeners
                            VoipUtil.sendVoipBroadcast(getAppContext(), CallActivity.ACTION_OUTGOING_VIDEO_STARTED);
                        }
                    }
                }
            }, "StartCapturingThread").start();
        }
    }

    /**
     *  Stop capturing (asynchronously).
     */
    @AnyThread
    private void stopCapturing() {
        if (!ListenerUtil.mutListener.listen(59353)) {
            new Thread(() -> {
                if (peerConnectionClient != null) {
                    synchronized (this.capturingLock) {
                        peerConnectionClient.stopCapturing();
                        this.isCapturing = false;
                        VoipUtil.sendVoipBroadcast(getAppContext(), CallActivity.ACTION_OUTGOING_VIDEO_STOPPED);
                    }
                }
            }, "StopCapturingThread").start();
        }
    }

    /**
     *  Update our own video quality profile, change outgoing video parameters
     *  and notify the peer about this change.
     */
    @AnyThread
    private void updateOwnVideoQualityProfile(boolean networkIsMetered, boolean networkIsRelayed) {
        if (!ListenerUtil.mutListener.listen(59354)) {
            logger.debug("updateOwnVideoQualityProfile: metered={} relayed={}", networkIsMetered, networkIsRelayed);
        }
        try (CloseableLock ignored = this.videoQualityNegotiation.write()) {
            // Get own params from settings
            final VoipVideoParams ownParams = VoipVideoParams.getParamsFromSetting(preferenceService.getVideoCallsProfile(), networkIsMetered);
            if (!ListenerUtil.mutListener.listen(59355)) {
                this.localVideoQualityProfile = ownParams;
            }
            if (!ListenerUtil.mutListener.listen(59357)) {
                if (this.commonVideoQualityProfile == null) {
                    if (!ListenerUtil.mutListener.listen(59356)) {
                        this.commonVideoQualityProfile = ownParams;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(59363)) {
                if (this.peerConnectionClient != null) {
                    if (!ListenerUtil.mutListener.listen(59359)) {
                        // profile signaling messages in states other than CALLING can be skipped.
                        if (this.voipStateService.getCallState().isCalling()) {
                            if (!ListenerUtil.mutListener.listen(59358)) {
                                this.peerConnectionClient.sendSignalingMessage(ownParams);
                            }
                        }
                    }
                    // Adjust outgoing video stream
                    try {
                        final VoipVideoParams common = ownParams.findCommonProfile(this.remoteVideoQualityProfile, networkIsRelayed);
                        if (!ListenerUtil.mutListener.listen(59361)) {
                            this.commonVideoQualityProfile = common;
                        }
                        synchronized (this.capturingLock) {
                            try {
                                if (!ListenerUtil.mutListener.listen(59362)) {
                                    this.peerConnectionClient.changeOutgoingVideoParams(common);
                                }
                            } catch (NullPointerException e) {
                            }
                        }
                    } catch (RuntimeException e) {
                        if (!ListenerUtil.mutListener.listen(59360)) {
                            this.abortCall("Could not determine common video quality profile", null, e, true);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Update the peer video quality profile and change outgoing video parameters.
     */
    @AnyThread
    private void updatePeerVideoQualityProfile(@NonNull VoipVideoParams peerParams) {
        try (CloseableLock ignored = this.videoQualityNegotiation.write()) {
            if (!ListenerUtil.mutListener.listen(59364)) {
                this.remoteVideoQualityProfile = peerParams;
            }
            if (!ListenerUtil.mutListener.listen(59368)) {
                if (this.peerConnectionClient != null) {
                    // Adjust outgoing video stream
                    try {
                        final VoipVideoParams common = peerParams.findCommonProfile(this.localVideoQualityProfile, this.networkIsRelayed);
                        if (!ListenerUtil.mutListener.listen(59366)) {
                            this.commonVideoQualityProfile = common;
                        }
                        synchronized (this.capturingLock) {
                            try {
                                if (!ListenerUtil.mutListener.listen(59367)) {
                                    this.peerConnectionClient.changeOutgoingVideoParams(common);
                                }
                            } catch (NullPointerException e) {
                            }
                        }
                    } catch (RuntimeException e) {
                        if (!ListenerUtil.mutListener.listen(59365)) {
                            this.abortCall("Could not determine common video quality profile", null, e, true);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Switch between front- and back-camera.
     */
    private void switchCamera() {
        if (!ListenerUtil.mutListener.listen(59370)) {
            if (switchCamInProgress.get()) {
                if (!ListenerUtil.mutListener.listen(59369)) {
                    logger.debug("Ignoring camera switch request, already in progress");
                }
                return;
            }
        }
        synchronized (this.capturingLock) {
            final CameraVideoCapturer capturer = this.voipStateService.getVideoContext().getCameraVideoCapturer();
            if (!ListenerUtil.mutListener.listen(59372)) {
                if (capturer == null) {
                    if (!ListenerUtil.mutListener.listen(59371)) {
                        logger.debug("Ignoring camera switch request, no capturer initialized");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(59373)) {
                logger.debug("Switching camera");
            }
            if (!ListenerUtil.mutListener.listen(59374)) {
                switchCamInProgress.set(true);
            }
            @VideoContext.CameraOrientation
            final int newCameraOrientation;
            final String newCameraName;
            if (this.voipStateService.getVideoContext().getCameraOrientation() == CAMERA_FRONT) {
                newCameraOrientation = CAMERA_BACK;
                newCameraName = this.voipStateService.getVideoContext().getBackCameraName();
            } else {
                newCameraOrientation = CAMERA_FRONT;
                newCameraName = this.voipStateService.getVideoContext().getFrontCameraName();
            }
            if (!ListenerUtil.mutListener.listen(59376)) {
                if (newCameraName == null) {
                    if (!ListenerUtil.mutListener.listen(59375)) {
                        logger.debug("Ignoring camera switch request, no camera with orientation='{}'", newCameraOrientation);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(59385)) {
                capturer.switchCamera(new CameraVideoCapturer.CameraSwitchHandler() {

                    @Override
                    public void onCameraSwitchDone(boolean isFront) {
                        if (!ListenerUtil.mutListener.listen(59377)) {
                            voipStateService.getVideoContext().setCameraOrientation(newCameraOrientation);
                        }
                        if (!ListenerUtil.mutListener.listen(59378)) {
                            logger.info("Switched camera to {}", isFront ? "front cam" : "rear cam");
                        }
                        if (!ListenerUtil.mutListener.listen(59379)) {
                            VoipUtil.sendVoipBroadcast(getApplicationContext(), CallActivity.ACTION_CAMERA_CHANGED);
                        }
                        if (!ListenerUtil.mutListener.listen(59380)) {
                            Toast.makeText(getAppContext(), isFront ? R.string.voip_switch_cam_front : R.string.voip_switch_cam_rear, Toast.LENGTH_SHORT).show();
                        }
                        if (!ListenerUtil.mutListener.listen(59381)) {
                            this.resetInProgress();
                        }
                    }

                    @Override
                    public void onCameraSwitchError(String s) {
                        if (!ListenerUtil.mutListener.listen(59382)) {
                            logger.info("Error while switching camera: {}", s);
                        }
                        if (!ListenerUtil.mutListener.listen(59383)) {
                            this.resetInProgress();
                        }
                    }

                    private void resetInProgress() {
                        if (!ListenerUtil.mutListener.listen(59384)) {
                            switchCamInProgress.set(false);
                        }
                    }
                }, newCameraName);
            }
        }
    }

    @NonNull
    private final RemoteVideoStateDetector remoteVideoStateDetector = new RemoteVideoStateDetector(this::getApplicationContext);

    /**
     *  This class handles remote video state changes (combining the
     *  information from the capturing state signaling messages and
     *  the frame detector).
     *
     *  The ACTION_INCOMING_VIDEO_STARTED and ACTION_INCOMING_VIDEO_STOPPED broadcasts
     *  should never be sent outside this class.
     */
    private static class RemoteVideoStateDetector {

        @NonNull
        private final Supplier<Context> appContextSupplier;

        // methods to avoid data races!
        private volatile boolean incomingVideoFrames = false;

        private volatile boolean incomingVideoSignaled = false;

        private volatile boolean incomingVideo = false;

        RemoteVideoStateDetector(@NonNull Supplier<Context> appContextSupplier) {
            this.appContextSupplier = appContextSupplier;
        }

        /**
         *  Called by the {@link #frameDetector}. Remote has started sending video frames.
         *
         *  Notify application if {@link #incomingVideo} was false.
         */
        synchronized void onIncomingVideoFramesStarted() {
            if (!ListenerUtil.mutListener.listen(59386)) {
                this.incomingVideoFrames = true;
            }
            if (!ListenerUtil.mutListener.listen(59390)) {
                if (!this.incomingVideo) {
                    if (!ListenerUtil.mutListener.listen(59387)) {
                        // Incoming video was false
                        this.incomingVideo = true;
                    }
                    if (!ListenerUtil.mutListener.listen(59388)) {
                        logger.info("Incoming video started (reason: frames)");
                    }
                    if (!ListenerUtil.mutListener.listen(59389)) {
                        VoipUtil.sendVoipBroadcast(this.appContextSupplier.get(), CallActivity.ACTION_INCOMING_VIDEO_STARTED);
                    }
                }
            }
        }

        /**
         *  Called by the {@link #frameDetector}. Remote has stopped sending video frames.
         *
         *  Notify application if {@link #incomingVideo} was false.
         */
        synchronized void onIncomingVideoFramesStopped() {
            if (!ListenerUtil.mutListener.listen(59391)) {
                this.incomingVideoFrames = false;
            }
            if (!ListenerUtil.mutListener.listen(59396)) {
                if (this.incomingVideo) {
                    if (!ListenerUtil.mutListener.listen(59395)) {
                        // Incoming video was true...
                        if (!this.incomingVideoSignaled) {
                            if (!ListenerUtil.mutListener.listen(59392)) {
                                // ...due to the frame detector
                                this.incomingVideo = false;
                            }
                            if (!ListenerUtil.mutListener.listen(59393)) {
                                logger.info("Incoming video stopped (reason: frames)");
                            }
                            if (!ListenerUtil.mutListener.listen(59394)) {
                                VoipUtil.sendVoipBroadcast(this.appContextSupplier.get(), CallActivity.ACTION_INCOMING_VIDEO_STOPPED);
                            }
                        }
                    }
                }
            }
        }

        /**
         *  Called by {@link #handleCaptureStateChange(long, CallSignaling.CaptureState)}.
         *  Remote has signaled that video capturing has started.
         */
        synchronized void onRemoteVideoCapturingEnabled() {
            if (!ListenerUtil.mutListener.listen(59397)) {
                this.incomingVideoSignaled = true;
            }
            if (!ListenerUtil.mutListener.listen(59401)) {
                if (!this.incomingVideo) {
                    if (!ListenerUtil.mutListener.listen(59398)) {
                        // Signaling always results in the video being shown
                        this.incomingVideo = true;
                    }
                    if (!ListenerUtil.mutListener.listen(59399)) {
                        logger.info("Incoming video started (reason: signaling)");
                    }
                    if (!ListenerUtil.mutListener.listen(59400)) {
                        VoipUtil.sendVoipBroadcast(this.appContextSupplier.get(), CallActivity.ACTION_INCOMING_VIDEO_STARTED);
                    }
                }
            }
        }

        /**
         *  Called by {@link #handleCaptureStateChange(long, CallSignaling.CaptureState)}.
         *  Remote has signaled that video capturing has stopped.
         */
        synchronized void onRemoteVideoCapturingDisabled() {
            if (!ListenerUtil.mutListener.listen(59402)) {
                this.incomingVideoSignaled = false;
            }
            if (!ListenerUtil.mutListener.listen(59407)) {
                if (this.incomingVideo) {
                    if (!ListenerUtil.mutListener.listen(59406)) {
                        // Incoming video was true...
                        if (!this.incomingVideoFrames) {
                            if (!ListenerUtil.mutListener.listen(59403)) {
                                // ...due to the signaling state
                                this.incomingVideo = false;
                            }
                            if (!ListenerUtil.mutListener.listen(59404)) {
                                logger.info("Incoming video stopped (reason: signaling)");
                            }
                            if (!ListenerUtil.mutListener.listen(59405)) {
                                VoipUtil.sendVoipBroadcast(this.appContextSupplier.get(), CallActivity.ACTION_INCOMING_VIDEO_STOPPED);
                            }
                        }
                    }
                }
            }
        }
    }

    private VoipAudioManagerListener audioManagerListener = new VoipAudioManagerListener() {

        @Override
        public void onAudioFocusLost(boolean temporary) {
            if (!ListenerUtil.mutListener.listen(59408)) {
                logger.info("Audio focus lost. Transient = " + temporary);
            }
            if (!ListenerUtil.mutListener.listen(59415)) {
                if (temporary) {
                    if (!ListenerUtil.mutListener.listen(59414)) {
                        if (peerConnectionClient != null) {
                            if (!ListenerUtil.mutListener.listen(59411)) {
                                peerConnectionClient.setLocalAudioTrackEnabled(false);
                            }
                            if (!ListenerUtil.mutListener.listen(59412)) {
                                peerConnectionClient.setRemoteAudioTrackEnabled(false);
                            }
                            if (!ListenerUtil.mutListener.listen(59413)) {
                                showSingleToast(getAppContext().getString(R.string.audio_mute_due_to_focus_loss), Toast.LENGTH_LONG);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(59409)) {
                        // lost forever - disconnect
                        BackgroundErrorNotification.showNotification(getAppContext(), R.string.audio_focus_loss, R.string.audio_focus_loss_complete, "VoipCallService", false, null);
                    }
                    if (!ListenerUtil.mutListener.listen(59410)) {
                        RuntimeUtil.runOnUiThread(() -> disconnect("Audio Focus lost"));
                    }
                }
            }
        }

        @Override
        public void onAudioFocusGained() {
            if (!ListenerUtil.mutListener.listen(59416)) {
                logger.info("Audio focus gained");
            }
            if (!ListenerUtil.mutListener.listen(59419)) {
                if (peerConnectionClient != null) {
                    if (!ListenerUtil.mutListener.listen(59417)) {
                        peerConnectionClient.setLocalAudioTrackEnabled(micEnabled);
                    }
                    if (!ListenerUtil.mutListener.listen(59418)) {
                        peerConnectionClient.setRemoteAudioTrackEnabled(true);
                    }
                }
            }
        }
    };

    private class PSTNCallStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            if (!ListenerUtil.mutListener.listen(59420)) {
                super.onCallStateChanged(state, phoneNumber);
            }
            if (!ListenerUtil.mutListener.listen(59424)) {
                if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    if (!ListenerUtil.mutListener.listen(59421)) {
                        Toast.makeText(getAppContext(), R.string.voip_another_pstn_call, Toast.LENGTH_LONG).show();
                    }
                    if (!ListenerUtil.mutListener.listen(59422)) {
                        onCallHangUp();
                    }
                    if (!ListenerUtil.mutListener.listen(59423)) {
                        logger.info("hanging up due to regular phone call");
                    }
                }
            }
        }
    }

    /**
     *  The call partner enabled or disabled capturing for a device.
     *
     *  @param captureStateChange The received signaling message.
     */
    @AnyThread
    private void handleCaptureStateChange(long callId, @NonNull CallSignaling.CaptureState captureStateChange) {
        if (!ListenerUtil.mutListener.listen(59425)) {
            logCallInfo(callId, "Signaling: Call partner changed {} capturing state to {}", captureStateChange.getDevice(), captureStateChange.getState());
        }
        if (!ListenerUtil.mutListener.listen(59430)) {
            // Handle camera capturing state changes
            if (CallSignaling.CaptureState.CaptureDevice.CAMERA == captureStateChange.getDevice()) {
                if (!ListenerUtil.mutListener.listen(59429)) {
                    switch(captureStateChange.getState()) {
                        case ON:
                            if (!ListenerUtil.mutListener.listen(59426)) {
                                this.remoteVideoStateDetector.onRemoteVideoCapturingEnabled();
                            }
                            break;
                        case OFF:
                            if (!ListenerUtil.mutListener.listen(59427)) {
                                this.remoteVideoStateDetector.onRemoteVideoCapturingDisabled();
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(59428)) {
                                logCallWarning(callId, "Unknown capture state received");
                            }
                    }
                }
            }
        }
    }

    /**
     *  The call partner changed the video quality profile.
     *
     *  @param videoQualityProfile The received signaling message.
     */
    @AnyThread
    private void handleVideoQualityProfileChange(long callId, @NonNull CallSignaling.VideoQualityProfile videoQualityProfile) {
        if (!ListenerUtil.mutListener.listen(59431)) {
            logCallInfo(callId, "Signaling: Call partner changed video profile to {}", videoQualityProfile.getProfile());
        }
        final VoipVideoParams profile = VoipVideoParams.fromSignalingMessage(videoQualityProfile);
        if (!ListenerUtil.mutListener.listen(59433)) {
            if (profile != null) {
                if (!ListenerUtil.mutListener.listen(59432)) {
                    this.updatePeerVideoQualityProfile(profile);
                }
            }
        }
    }
}
