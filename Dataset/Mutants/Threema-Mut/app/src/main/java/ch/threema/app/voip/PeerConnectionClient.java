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
import android.os.Build;
import android.widget.Toast;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.CryptoOptions;
import org.webrtc.DataChannel;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnection.IceConnectionState;
import org.webrtc.PeerConnection.IceGatheringState;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RTCStatsCollectorCallback;
import org.webrtc.RtpParameters;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.SoftwareVideoEncoderFactory;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoEncoderFactory;
import org.webrtc.VideoSink;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;
import org.webrtc.voiceengine.WebRtcAudioManager;
import org.webrtc.voiceengine.WebRtcAudioUtils;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.R;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.WebRTCUtil;
import ch.threema.app.voip.services.VoipCallService;
import ch.threema.app.voip.signaling.CaptureState;
import ch.threema.app.voip.signaling.ToSignalingMessage;
import ch.threema.app.voip.util.SdpPatcher;
import ch.threema.app.voip.util.SdpUtil;
import ch.threema.app.voip.util.VideoCapturerUtil;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.app.voip.util.VoipVideoParams;
import ch.threema.app.webrtc.DataChannelObserver;
import ch.threema.app.webrtc.UnboundedFlowControlledDataChannel;
import ch.threema.client.APIConnector;
import ch.threema.protobuf.callsignaling.CallSignaling;
import java8.util.concurrent.CompletableFuture;
import java8.util.stream.StreamSupport;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Peer connection client implementation.
 *
 * All public methods are routed to local looper thread.
 * All PeerConnectionEvents callbacks are invoked from the same looper thread.
 */
public class PeerConnectionClient {

    // Note: Not static, because we want to set a prefix
    private final Logger logger = LoggerFactory.getLogger(PeerConnectionClient.class);

    private static final String AUDIO_TRACK_ID = "3MACALLa0";

    private static final String AUDIO_CODEC_OPUS = "opus";

    private static final String AUDIO_LEVEL_CONTROL_CONSTRAINT = "levelControl";

    private static final String VIDEO_TRACK_ID = "3MACALLv0";

    // Capturing settings. What's being sent may be lower.
    private static final int VIDEO_WIDTH = 1920;

    private static final int VIDEO_HEIGHT = 1080;

    private static final int VIDEO_FPS = 25;

    private static final String SIGNALING_CHANNEL_ID = "3MACALLdc0";

    // Stats semaphore (wait for all pending stats to be complete)
    private final Semaphore statsLock = new Semaphore(1);

    private int statsCounter = 0;

    // General
    @NonNull
    private final Context appContext;

    private final long callId;

    // Peer connection
    @Nullable
    private PeerConnectionFactory factory = null;

    @NonNull
    private final Semaphore factoryInitializing = new Semaphore(1);

    private PeerConnection peerConnection;

    @NonNull
    private final PeerConnectionParameters peerConnectionParameters;

    @NonNull
    private final SdpPatcher sdpPatcher;

    // Observers and events
    @Nullable
    private Events events;

    private final PCObserver pcObserver = new PCObserver();

    private final SDPObserver sdpObserver = new SDPObserver();

    private final DCObserver dcObserver = new DCObserver();

    // Signaling
    @Nullable
    private UnboundedFlowControlledDataChannel signalingDataChannel;

    // Executor service for everything that has to do with libwebrtc
    private final ScheduledExecutorService executor;

    // remote peer after both local and remote description are set.
    private LinkedList<IceCandidate> queuedRemoteCandidates = null;

    // Always render remote video, to avoid masking bugs
    private final boolean renderVideo = true;

    @Nullable
    private final EglBase.Context eglBaseContext;

    @Nullable
    private VideoSink localVideoSink;

    @Nullable
    private VideoSink remoteVideoSink;

    @Nullable
    private VideoTrack localVideoTrack;

    @Nullable
    private VideoTrack remoteVideoTrack;

    @Nullable
    private RtpSender localVideoSender;

    @Nullable
    private SurfaceTextureHelper surfaceTextureHelper;

    @Nullable
    private VideoSource videoSource;

    // Video capturer. Always lock the `capturingLock` when modifying the capturer in any way!
    @Nullable
    private VideoCapturer videoCapturer;

    private final Object capturingLock = new Object();

    // Audio
    private boolean enableAudio = true;

    @Nullable
    private AudioTrack localAudioTrack;

    @Nullable
    private AudioSource audioSource;

    // Outgoing audio
    private boolean enableLocalAudioTrack = true;

    // Media constraints
    private MediaConstraints audioConstraints;

    private MediaConstraints sdpMediaConstraints;

    // State
    private boolean isInitiator;

    private boolean isError = false;

    // Stats
    private RTCStatsCollectorCallback afterClosingStatsCallback = null;

    private final Map<RTCStatsCollectorCallback, Timer> periodicStatsTimers = new HashMap<>();

    // either offer or answer SDP
    private SessionDescription localSdp = null;

    // Workaround for ANDR-1079 / CRBUG 935905
    @Nullable
    private Long setRemoteDescriptionNanotime = null;

    @Nullable
    private ScheduledFuture<?> iceFailedFuture = null;

    // Workaround for ANDR-1119
    @Nullable
    private List<RtpTransceiver> cachedRtpTransceivers = null;

    // Flag for disabling the use of ICE servers (TURN) for testing purposes
    private boolean enableIceServers = true;

    /**
     *  Peer connection parameters.
     */
    public static class PeerConnectionParameters {

        // Trace logging
        final boolean tracing;

        // Audio
        final boolean useOpenSLES;

        final boolean disableBuiltInAEC;

        final boolean disableBuiltInAGC;

        final boolean disableBuiltInNS;

        final boolean enableLevelControl;

        // Video
        final boolean videoCallEnabled;

        final boolean videoCodecHwAcceleration;

        final boolean videoCodecEnableVP8;

        final boolean videoCodecEnableH264HiP;

        // RTP
        @NonNull
        final SdpPatcher.RtpHeaderExtensionConfig rtpHeaderExtensionConfig;

        // Networking
        final boolean forceTurn;

        final boolean gatherContinually;

        final boolean allowIpv6;

        /**
         *  Initialize the peer connection client.
         *
         *  @param tracing Enable WebRTC trace logging. Should only be used for internal debugging.
         *  @param useOpenSLES Use OpenSL ES
         *  @param disableBuiltInAEC Disable acoustic echo cancelation
         *  @param disableBuiltInAGC Disable automatic gain control
         *  @param disableBuiltInNS Disable noise suppression
         *  @param enableLevelControl Enable level control
         *  @param videoCallEnabled Enable video calls
         *  @param videoCodecHwAcceleration Enable video codec hardware acceleration
         *  @param rtpHeaderExtensionConfig See {@link SdpPatcher}
         *  @param forceTurn Whether TURN servers should be forced (relay only).
         *  @param gatherContinually Whether ICE candidates should be gathered continually.
         *  @param allowIpv6 Whether IPv6 should be allowed
         */
        public PeerConnectionParameters(boolean tracing, boolean useOpenSLES, boolean disableBuiltInAEC, boolean disableBuiltInAGC, boolean disableBuiltInNS, boolean enableLevelControl, boolean videoCallEnabled, boolean videoCodecHwAcceleration, boolean videoCodecEnableVP8, boolean videoCodecEnableH264HiP, @NonNull SdpPatcher.RtpHeaderExtensionConfig rtpHeaderExtensionConfig, boolean forceTurn, boolean gatherContinually, boolean allowIpv6) {
            // Logging
            this.tracing = tracing;
            // Audio
            this.useOpenSLES = useOpenSLES;
            this.disableBuiltInAEC = disableBuiltInAEC;
            this.disableBuiltInAGC = disableBuiltInAGC;
            this.disableBuiltInNS = disableBuiltInNS;
            this.enableLevelControl = enableLevelControl;
            // Video
            this.videoCallEnabled = videoCallEnabled;
            this.videoCodecHwAcceleration = videoCodecHwAcceleration;
            this.videoCodecEnableVP8 = videoCodecEnableVP8;
            this.videoCodecEnableH264HiP = videoCodecEnableH264HiP;
            // RTP
            this.rtpHeaderExtensionConfig = rtpHeaderExtensionConfig;
            // Networking
            this.forceTurn = forceTurn;
            this.gatherContinually = gatherContinually;
            this.allowIpv6 = allowIpv6;
        }
    }

    /**
     *  Subscribe to this event handler to be notified about events
     *  happening in the PeerConnectionClient.
     */
    public interface Events {

        /**
         *  Callback fired once local SDP is created and set.
         */
        void onLocalDescription(long callId, final SessionDescription sdp);

        /**
         *  Callback fired once remote SDP is set.
         */
        void onRemoteDescriptionSet(long callId);

        /**
         *  Callback fired once local Ice candidate is generated.
         */
        void onIceCandidate(long callId, final IceCandidate candidate);

        /**
         *  Callback fired once connection is starting to check candidate pairs
         *  (IceConnectionState is CHECKING).
         */
        void onIceChecking(long callId);

        /**
         *  Callback fired once connection is established (IceConnectionState is
         *  CONNECTED).
         */
        void onIceConnected(long callId);

        /**
         *  Callback fired once connection is closed (IceConnectionState is
         *  DISCONNECTED).
         */
        void onIceDisconnected(long callId);

        /**
         *  Callback fired if connection fails (IceConnectionState is
         *  FAILED).
         *
         *  NOTE: Due to ANDR-1079 (CRBUG 935905), this will not be called
         *        earlier than 15 seconds after the connection attempt was started.
         */
        @AnyThread
        void onIceFailed(long callId);

        /**
         *  Callback fired if the ICE gathering state changes.
         */
        void onIceGatheringStateChange(long callId, IceGatheringState newState);

        /**
         *  Callback fired once peer connection is closed.
         */
        void onPeerConnectionClosed(long callId);

        /**
         *  Callback fired when an error occurred.
         *
         *  If the `abortCall` flag is set, the error is critical
         *  and the call should be aborted.
         */
        void onError(long callId, @NonNull final String description, boolean abortCall);

        /**
         *  Called when a new signaling message from the peer arrives.
         *
         *  @param envelope The protobuf envelope.
         */
        @WorkerThread
        default void onSignalingMessage(long callId, @NonNull final CallSignaling.Envelope envelope) {
        }

        /**
         *  This is triggered whenever a capturing camera reports the first available frame.
         */
        default void onCameraFirstFrameAvailable() {
        }
    }

    /**
     *  Create a PeerConnectionClient with the specified parameters.
     */
    public PeerConnectionClient(@NonNull final Context appContext, @NonNull final PeerConnectionParameters peerConnectionParameters, @Nullable final EglBase.Context eglBaseContext, final long callId) {
        if (!ListenerUtil.mutListener.listen(61143)) {
            // Set logging prefix
            VoipUtil.setLoggerPrefix(logger, callId);
        }
        // Create logger for SdpPatcher
        final Logger sdpPatcherLogger = LoggerFactory.getLogger(PeerConnectionClient.class + ":" + "SdpPatcher");
        if (!ListenerUtil.mutListener.listen(61144)) {
            VoipUtil.setLoggerPrefix(sdpPatcherLogger, callId);
        }
        // Initialize instance variables
        this.appContext = appContext;
        this.peerConnectionParameters = peerConnectionParameters;
        this.sdpPatcher = new SdpPatcher().withLogger(sdpPatcherLogger).withRtpHeaderExtensions(this.peerConnectionParameters.rtpHeaderExtensionConfig);
        this.eglBaseContext = eglBaseContext;
        this.callId = callId;
        // created on the same thread as previously destroyed factory.
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     *  Set the `PeerConnectionEvents` handler.
     */
    public void setEventHandler(@Nullable final Events events) {
        if (!ListenerUtil.mutListener.listen(61145)) {
            this.events = events;
        }
    }

    /**
     *  Enable or disable the use of ICE servers (defaults to enabled).
     */
    public void setEnableIceServers(boolean enableIceServers) {
        if (!ListenerUtil.mutListener.listen(61146)) {
            this.enableIceServers = enableIceServers;
        }
    }

    /**
     *  Create a peer connection factory.
     *
     *  Return a future that resolves to true if the factory could be created,
     *  or to false otherwise.
     */
    @AnyThread
    public CompletableFuture<Boolean> createPeerConnectionFactory() {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        if (!ListenerUtil.mutListener.listen(61147)) {
            this.executor.execute(() -> createPeerConnectionFactoryInternal(future));
        }
        return future;
    }

    @AnyThread
    public void createPeerConnection() {
        if (!ListenerUtil.mutListener.listen(61148)) {
            this.createPeerConnection(null, null);
        }
    }

    @AnyThread
    public void createPeerConnection(@Nullable VideoSink localVideoSink, @Nullable VideoSink remoteVideoSink) {
        try {
            if (!ListenerUtil.mutListener.listen(61151)) {
                // Ensure that the factory is not currently being initialized.
                this.factoryInitializing.acquire();
            }
            if (!ListenerUtil.mutListener.listen(61152)) {
                this.factoryInitializing.release();
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(61149)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(61150)) {
                Thread.currentThread().interrupt();
            }
        }
        if (!ListenerUtil.mutListener.listen(61154)) {
            if (this.factory == null) {
                if (!ListenerUtil.mutListener.listen(61153)) {
                    logger.error("Cannot create peer connection without initializing factory first");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61155)) {
            this.localVideoSink = localVideoSink;
        }
        if (!ListenerUtil.mutListener.listen(61156)) {
            this.remoteVideoSink = remoteVideoSink;
        }
        if (!ListenerUtil.mutListener.listen(61157)) {
            this.executor.execute(() -> {
                try {
                    this.createMediaConstraintsInternal();
                    this.createPeerConnectionInternal();
                } catch (Exception e) {
                    this.reportError("Failed to create peer connection: " + e.getMessage(), e, true);
                }
            });
        }
    }

    @AnyThread
    public void close() {
        if (!ListenerUtil.mutListener.listen(61158)) {
            executor.execute(this::closeInternal);
        }
    }

    private boolean isVideoCallEnabled() {
        return this.peerConnectionParameters.videoCallEnabled;
    }

    /**
     *  Create the peer connection factory.
     *
     *  The future completes with true if the factory was created, false otherwise.
     */
    @WorkerThread
    private void createPeerConnectionFactoryInternal(@NonNull CompletableFuture<Boolean> future) {
        if (!ListenerUtil.mutListener.listen(61159)) {
            logger.info("Create peer connection factory");
        }
        if (!ListenerUtil.mutListener.listen(61162)) {
            if (this.factory != null) {
                if (!ListenerUtil.mutListener.listen(61160)) {
                    logger.error("Peer connetion factory already initialized");
                }
                if (!ListenerUtil.mutListener.listen(61161)) {
                    future.complete(false);
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(61165)) {
                this.factoryInitializing.acquire();
            }
        } catch (InterruptedException e) {
            if (!ListenerUtil.mutListener.listen(61163)) {
                logger.error("Interrupted while acquiring semaphore", e);
            }
            if (!ListenerUtil.mutListener.listen(61164)) {
                future.complete(false);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(61166)) {
            this.isError = false;
        }
        if (!ListenerUtil.mutListener.listen(61167)) {
            // Initialize peer connection factory
            WebRTCUtil.initializeAndroidGlobals(this.appContext);
        }
        if (!ListenerUtil.mutListener.listen(61170)) {
            // in `WebRTCUtil#initializeAndroidGlobals`.
            if (this.peerConnectionParameters.tracing) {
                final String tracingFilePath = this.appContext.getCacheDir().getAbsolutePath() + File.separator + "webrtc-trace.log";
                if (!ListenerUtil.mutListener.listen(61168)) {
                    logger.info("Writing WebRTC trace to {}", tracingFilePath);
                }
                if (!ListenerUtil.mutListener.listen(61169)) {
                    PeerConnectionFactory.startInternalTracingCapture(tracingFilePath);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61175)) {
            // Enable/disable OpenSL ES playback
            if (!peerConnectionParameters.useOpenSLES) {
                if (!ListenerUtil.mutListener.listen(61173)) {
                    logger.info("Disable OpenSL ES audio even if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61174)) {
                    WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61171)) {
                    logger.info("Allow OpenSL ES audio if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61172)) {
                    WebRtcAudioManager.setBlacklistDeviceForOpenSLESUsage(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61180)) {
            // Enable/disable acoustic echo cancelation
            if (peerConnectionParameters.disableBuiltInAEC) {
                if (!ListenerUtil.mutListener.listen(61178)) {
                    logger.info("Disable built-in AEC even if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61179)) {
                    WebRtcAudioUtils.setWebRtcBasedAcousticEchoCanceler(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61176)) {
                    logger.info("Enable built-in AEC if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61177)) {
                    WebRtcAudioUtils.setWebRtcBasedAcousticEchoCanceler(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61185)) {
            // Enable/disable automatic gain control
            if (peerConnectionParameters.disableBuiltInAGC) {
                if (!ListenerUtil.mutListener.listen(61183)) {
                    logger.info("Disable built-in AGC even if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61184)) {
                    WebRtcAudioUtils.setWebRtcBasedAutomaticGainControl(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61181)) {
                    logger.info("Enable built-in AGC if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61182)) {
                    WebRtcAudioUtils.setWebRtcBasedAutomaticGainControl(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61190)) {
            // Enable/disable built-in noise suppressor
            if (peerConnectionParameters.disableBuiltInNS) {
                if (!ListenerUtil.mutListener.listen(61188)) {
                    logger.info("Disable built-in NS even if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61189)) {
                    WebRtcAudioUtils.setWebRtcBasedNoiseSuppressor(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61186)) {
                    logger.info("Enable built-in NS if device supports it");
                }
                if (!ListenerUtil.mutListener.listen(61187)) {
                    WebRtcAudioUtils.setWebRtcBasedNoiseSuppressor(false);
                }
            }
        }
        // Determine video encoder/decoder factory
        final VideoEncoderFactory encoderFactory;
        final VideoDecoderFactory decoderFactory;
        boolean useHardwareVideoCodec = peerConnectionParameters.videoCodecHwAcceleration;
        if (!ListenerUtil.mutListener.listen(61193)) {
            if (!Config.allowHardwareVideoCodec()) {
                if (!ListenerUtil.mutListener.listen(61191)) {
                    this.logger.info("Video codec: Device {} is on hardware codec exclusion list", Build.MODEL);
                }
                if (!ListenerUtil.mutListener.listen(61192)) {
                    useHardwareVideoCodec = false;
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(61194) ? (useHardwareVideoCodec || this.eglBaseContext != null) : (useHardwareVideoCodec && this.eglBaseContext != null))) {
            if (!ListenerUtil.mutListener.listen(61196)) {
                logger.info("Video codec: HW acceleration (VP8={}, H264HiP={})", peerConnectionParameters.videoCodecEnableVP8, peerConnectionParameters.videoCodecEnableH264HiP);
            }
            final boolean enableIntelVp8Encoder = peerConnectionParameters.videoCodecEnableVP8;
            final boolean enableH264HighProfile = peerConnectionParameters.videoCodecEnableH264HiP;
            encoderFactory = new DefaultVideoEncoderFactory(this.eglBaseContext, enableIntelVp8Encoder, enableH264HighProfile);
            decoderFactory = new DefaultVideoDecoderFactory(this.eglBaseContext);
        } else {
            if (!ListenerUtil.mutListener.listen(61195)) {
                logger.info("Video codec: SW acceleration");
            }
            encoderFactory = new SoftwareVideoEncoderFactory();
            decoderFactory = new SoftwareVideoDecoderFactory();
        }
        if (!ListenerUtil.mutListener.listen(61197)) {
            // Create peer connection factor
            logger.debug("Creating peer connection factory");
        }
        final PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
        if (!ListenerUtil.mutListener.listen(61198)) {
            this.factory = PeerConnectionFactory.builder().setOptions(options).setVideoDecoderFactory(decoderFactory).setVideoEncoderFactory(encoderFactory).createPeerConnectionFactory();
        }
        if (!ListenerUtil.mutListener.listen(61200)) {
            if (this.factory == null) {
                if (!ListenerUtil.mutListener.listen(61199)) {
                    logger.error("Could not create peer connection factory");
                }
                throw new RuntimeException("createPeerConnectionFactoryInternal: createPeerConnectionFactory returned null");
            }
        }
        if (!ListenerUtil.mutListener.listen(61201)) {
            logger.info("Peer connection factory created");
        }
        if (!ListenerUtil.mutListener.listen(61202)) {
            this.factoryInitializing.release();
        }
        if (!ListenerUtil.mutListener.listen(61203)) {
            future.complete(true);
        }
    }

    @WorkerThread
    private void createMediaConstraintsInternal() {
        if (!ListenerUtil.mutListener.listen(61204)) {
            // Create audio constraints.
            this.audioConstraints = new MediaConstraints();
        }
        if (!ListenerUtil.mutListener.listen(61207)) {
            // Added for audio performance measurements
            if (this.peerConnectionParameters.enableLevelControl) {
                if (!ListenerUtil.mutListener.listen(61205)) {
                    logger.info("Enabling level control");
                }
                if (!ListenerUtil.mutListener.listen(61206)) {
                    this.audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair(AUDIO_LEVEL_CONTROL_CONSTRAINT, "true"));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61208)) {
            // Create SDP constraints.
            this.sdpMediaConstraints = new MediaConstraints();
        }
        if (!ListenerUtil.mutListener.listen(61209)) {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        }
        if (!ListenerUtil.mutListener.listen(61210)) {
            this.sdpMediaConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", Boolean.toString(this.isVideoCallEnabled())));
        }
    }

    @WorkerThread
    private void createPeerConnectionInternal() throws Exception {
        if (!ListenerUtil.mutListener.listen(61211)) {
            logger.info("Create peer connection");
        }
        if (!ListenerUtil.mutListener.listen(61213)) {
            if (this.factory == null) {
                if (!ListenerUtil.mutListener.listen(61212)) {
                    logger.error("createPeerConnectionInternal: Peer connection factory is null");
                }
                throw new IllegalStateException("Peer connection factory is null");
            }
        }
        if (!ListenerUtil.mutListener.listen(61215)) {
            if (this.isError) {
                if (!ListenerUtil.mutListener.listen(61214)) {
                    logger.error("createPeerConnectionInternal: isError = true");
                }
                throw new IllegalStateException("isError=true when creating peer connection");
            }
        }
        if (!ListenerUtil.mutListener.listen(61216)) {
            // Determine ICE servers
            this.queuedRemoteCandidates = new LinkedList<>();
        }
        if (!ListenerUtil.mutListener.listen(61219)) {
            if (this.peerConnectionParameters.allowIpv6) {
                if (!ListenerUtil.mutListener.listen(61218)) {
                    logger.info("Using dual-stack mode");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61217)) {
                    logger.info("Using v4 only mode");
                }
            }
        }
        final PeerConnection.RTCConfiguration rtcConfig = new PeerConnection.RTCConfiguration(getIceServers());
        if (!ListenerUtil.mutListener.listen(61220)) {
            // a problem with a bad connection.
            rtcConfig.tcpCandidatePolicy = PeerConnection.TcpCandidatePolicy.DISABLED;
        }
        if (!ListenerUtil.mutListener.listen(61221)) {
            rtcConfig.bundlePolicy = PeerConnection.BundlePolicy.MAXBUNDLE;
        }
        if (!ListenerUtil.mutListener.listen(61222)) {
            rtcConfig.rtcpMuxPolicy = PeerConnection.RtcpMuxPolicy.REQUIRE;
        }
        if (!ListenerUtil.mutListener.listen(61225)) {
            if (this.peerConnectionParameters.gatherContinually) {
                if (!ListenerUtil.mutListener.listen(61224)) {
                    rtcConfig.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61223)) {
                    rtcConfig.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_ONCE;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61228)) {
            // set the ICE transport type to RELAY only.
            if (this.peerConnectionParameters.forceTurn) {
                if (!ListenerUtil.mutListener.listen(61227)) {
                    rtcConfig.iceTransportsType = PeerConnection.IceTransportsType.RELAY;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61226)) {
                    rtcConfig.iceTransportsType = PeerConnection.IceTransportsType.ALL;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61229)) {
            // Use ECDSA encryption.
            rtcConfig.keyType = PeerConnection.KeyType.ECDSA;
        }
        if (!ListenerUtil.mutListener.listen(61230)) {
            // Opt-in to unified plan SDP
            rtcConfig.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        }
        // Crypto options
        final CryptoOptions.Builder cryptoOptions = CryptoOptions.builder();
        if (!ListenerUtil.mutListener.listen(61231)) {
            // disable AES 128 SHA1 32 but keep AES 128 SHA1 80 for backwards compatibility.
            cryptoOptions.setEnableGcmCryptoSuites(true).setEnableAes128Sha1_80CryptoCipher(true).setEnableAes128Sha1_32CryptoCipher(false);
        }
        if (!ListenerUtil.mutListener.listen(61232)) {
            // being stripped when setting `twoByteRtpHeaderSupport` to `false`
            cryptoOptions.setEnableEncryptedRtpHeaderExtensions(true);
        }
        if (!ListenerUtil.mutListener.listen(61233)) {
            // NEVER disable this or you will see crashes!
            rtcConfig.offerExtmapAllowMixed = true;
        }
        if (!ListenerUtil.mutListener.listen(61234)) {
            // Apply crypto options
            rtcConfig.cryptoOptions = cryptoOptions.createCryptoOptions();
        }
        if (!ListenerUtil.mutListener.listen(61235)) {
            // Create peer connection
            this.peerConnection = factory.createPeerConnection(rtcConfig, pcObserver);
        }
        if (!ListenerUtil.mutListener.listen(61237)) {
            if (this.peerConnection == null) {
                if (!ListenerUtil.mutListener.listen(61236)) {
                    logger.error("Could not create peer connection (factory.createPeerConnection returned null");
                }
                throw new RuntimeException("createPeerConnectionInternal: createPeerConnection returned null");
            }
        }
        if (!ListenerUtil.mutListener.listen(61238)) {
            this.isInitiator = false;
        }
        // Determine media stream label
        final List<String> mediaStreamLabels = Collections.singletonList("3MACALL");
        // Add an audio track
        final AudioTrack audioTrack = this.createAudioTrack();
        if (!ListenerUtil.mutListener.listen(61239)) {
            this.peerConnection.addTrack(audioTrack, mediaStreamLabels);
        }
        if (!ListenerUtil.mutListener.listen(61251)) {
            // Add a video track
            if (this.isVideoCallEnabled()) {
                if (!ListenerUtil.mutListener.listen(61240)) {
                    logger.debug("Adding video track");
                }
                final VideoTrack videoTrack = this.createVideoTrack();
                if (!ListenerUtil.mutListener.listen(61243)) {
                    if (videoTrack != null) {
                        if (!ListenerUtil.mutListener.listen(61242)) {
                            this.localVideoSender = this.peerConnection.addTrack(videoTrack, mediaStreamLabels);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(61241)) {
                            logger.error("Could not create local video track");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(61244)) {
                    // answer to get the remote track.
                    this.remoteVideoTrack = this.getRemoteVideoTrack();
                }
                if (!ListenerUtil.mutListener.listen(61250)) {
                    if (this.remoteVideoTrack != null) {
                        if (!ListenerUtil.mutListener.listen(61246)) {
                            this.remoteVideoTrack.setEnabled(this.renderVideo);
                        }
                        if (!ListenerUtil.mutListener.listen(61249)) {
                            if (this.remoteVideoSink != null) {
                                if (!ListenerUtil.mutListener.listen(61248)) {
                                    this.remoteVideoTrack.addSink(this.remoteVideoSink);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(61247)) {
                                    logger.error("Could not add sink to remote video track");
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(61245)) {
                            logger.error("Could not get remote video track");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61252)) {
            logger.info("Peer connection created");
        }
        // immediately start queueing messages.
        final DataChannel.Init init = new DataChannel.Init();
        if (!ListenerUtil.mutListener.listen(61253)) {
            init.id = 0;
        }
        if (!ListenerUtil.mutListener.listen(61254)) {
            init.negotiated = true;
        }
        if (!ListenerUtil.mutListener.listen(61255)) {
            init.ordered = true;
        }
        final DataChannel dc = peerConnection.createDataChannel(SIGNALING_CHANNEL_ID, init);
        if (!ListenerUtil.mutListener.listen(61256)) {
            this.dcObserver.register(dc);
        }
        if (!ListenerUtil.mutListener.listen(61257)) {
            this.signalingDataChannel = new UnboundedFlowControlledDataChannel("SignalingDataChannel", dc, this.dcObserver.openFuture);
        }
        if (!ListenerUtil.mutListener.listen(61258)) {
            logger.info("Data channel created");
        }
    }

    @WorkerThread
    private List<PeerConnection.IceServer> getIceServers() throws Exception {
        if (!ListenerUtil.mutListener.listen(61260)) {
            if (!enableIceServers) {
                if (!ListenerUtil.mutListener.listen(61259)) {
                    logger.debug("ICE servers disabled");
                }
                return new ArrayList<>();
            }
        }
        final List<org.webrtc.PeerConnection.IceServer> iceServers = new ArrayList<>();
        // to reach our TURN servers via IPv6 or no connection can be established at all.
        final APIConnector.TurnServerInfo turnServerInfo = Config.getTurnServerCache().getTurnServers();
        final List<String> turnServers = Arrays.asList(this.peerConnectionParameters.forceTurn ? turnServerInfo.turnUrlsDualStack : turnServerInfo.turnUrls);
        if (!ListenerUtil.mutListener.listen(61261)) {
            StreamSupport.stream(turnServers).map(server -> PeerConnection.IceServer.builder(server).setUsername(turnServerInfo.turnUsername).setPassword(turnServerInfo.turnPassword).createIceServer()).forEach(iceServers::add);
        }
        if (!ListenerUtil.mutListener.listen(61262)) {
            logger.debug("Using ICE servers: {}", turnServers);
        }
        return iceServers;
    }

    /**
     *  Set the outgoing video encoder limits.
     *
     *  @param maxBitrate The max bitrate in bits per second. If set to null,
     *                    any limit will be removed.
     *  @param maxFps Max frame rate (e.g. 25 or 20)
     */
    @WorkerThread
    private void setOutgoingVideoEncoderLimits(@Nullable Integer maxBitrate, int maxFps) {
        if (!ListenerUtil.mutListener.listen(61263)) {
            if (!this.isVideoCallEnabled()) {
                // Video calls not enabled, ignoring
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61264)) {
            logger.info("setOutgoingVideoBandwidthLimit: " + maxBitrate);
        }
        final RtpSender sender = this.localVideoSender;
        if (!ListenerUtil.mutListener.listen(61266)) {
            if (sender == null) {
                if (!ListenerUtil.mutListener.listen(61265)) {
                    logger.error("setOutgoingVideoBandwidthLimit: Could not find local video sender");
                }
                return;
            }
        }
        // Get current parameters
        final RtpParameters parameters = sender.getParameters();
        if (!ListenerUtil.mutListener.listen(61268)) {
            if (parameters == null) {
                if (!ListenerUtil.mutListener.listen(61267)) {
                    logger.error("setOutgoingVideoBandwidthLimit: Video sender has no parameters");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(61269)) {
            // Configure parameters
            parameters.degradationPreference = RtpParameters.DegradationPreference.BALANCED;
        }
        if (!ListenerUtil.mutListener.listen(61274)) {
            {
                long _loopCounter735 = 0;
                for (RtpParameters.Encoding encoding : parameters.encodings) {
                    ListenerUtil.loopListener.listen("_loopCounter735", ++_loopCounter735);
                    if (!ListenerUtil.mutListener.listen(61270)) {
                        this.logRtpEncoding("before", encoding);
                    }
                    if (!ListenerUtil.mutListener.listen(61271)) {
                        encoding.maxBitrateBps = maxBitrate;
                    }
                    if (!ListenerUtil.mutListener.listen(61272)) {
                        encoding.maxFramerate = maxFps;
                    }
                    if (!ListenerUtil.mutListener.listen(61273)) {
                        this.logRtpEncoding("after", encoding);
                    }
                }
            }
        }
        boolean success = sender.setParameters(parameters);
        if (!ListenerUtil.mutListener.listen(61277)) {
            if (success) {
                if (!ListenerUtil.mutListener.listen(61276)) {
                    logger.debug("Updated RtpParameters");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61275)) {
                    logger.error("Failed to update RtpParameters");
                }
            }
        }
    }

    @AnyThread
    private void logRtpEncoding(@NonNull String tag, @NonNull RtpParameters.Encoding encoding) {
        if (!ListenerUtil.mutListener.listen(61278)) {
            logger.debug("RtpParameters[{}]: Encoding: ssrc={} maxBitrate={} maxFramerate={} scale={} active={}", tag, encoding.ssrc, encoding.maxBitrateBps, encoding.maxFramerate, encoding.scaleResolutionDownBy, encoding.active);
        }
    }

    @WorkerThread
    private void closeInternal() {
        if (!ListenerUtil.mutListener.listen(61282)) {
            // Cancel ICE failed future and reset time variables
            if (this.iceFailedFuture != null) {
                if (!ListenerUtil.mutListener.listen(61279)) {
                    this.iceFailedFuture.cancel(true);
                }
                if (!ListenerUtil.mutListener.listen(61280)) {
                    this.iceFailedFuture = null;
                }
                if (!ListenerUtil.mutListener.listen(61281)) {
                    logger.info("iceFailedFuture: Cancelled (closeInternal)");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61283)) {
            this.setRemoteDescriptionNanotime = null;
        }
        if (!ListenerUtil.mutListener.listen(61284)) {
            // Stop creating further stats requests
            logger.debug("Clearing periodic stats timers");
        }
        if (!ListenerUtil.mutListener.listen(61286)) {
            {
                long _loopCounter736 = 0;
                for (Timer timer : this.periodicStatsTimers.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter736", ++_loopCounter736);
                    if (!ListenerUtil.mutListener.listen(61285)) {
                        timer.cancel();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61287)) {
            this.periodicStatsTimers.clear();
        }
        if (!ListenerUtil.mutListener.listen(61289)) {
            // Requests stats after having closed the peer connection (if requested)
            if (this.afterClosingStatsCallback != null) {
                if (!ListenerUtil.mutListener.listen(61288)) {
                    this.getStats(this.afterClosingStatsCallback);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61290)) {
            // Wait for asynchronous stats to finish
            logger.debug("Waiting for {} pending stats to finish", this.statsCounter);
        }
        boolean acquired = false;
        try {
            if (!ListenerUtil.mutListener.listen(61292)) {
                acquired = this.statsLock.tryAcquire(5, TimeUnit.SECONDS);
            }
        } catch (InterruptedException ignored) {
            if (!ListenerUtil.mutListener.listen(61291)) {
                logger.error("Spurious wakeup!");
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(61295)) {
                logger.info("Closing signaling data channel");
            }
            if (!ListenerUtil.mutListener.listen(61300)) {
                if (signalingDataChannel != null) {
                    if (!ListenerUtil.mutListener.listen(61296)) {
                        signalingDataChannel.dc.close();
                    }
                    if (!ListenerUtil.mutListener.listen(61297)) {
                        signalingDataChannel.dc.unregisterObserver();
                    }
                    if (!ListenerUtil.mutListener.listen(61298)) {
                        signalingDataChannel.dc.dispose();
                    }
                    if (!ListenerUtil.mutListener.listen(61299)) {
                        signalingDataChannel = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61301)) {
                logger.info("Closing peer connection");
            }
            if (!ListenerUtil.mutListener.listen(61303)) {
                if (peerConnection != null) {
                    if (!ListenerUtil.mutListener.listen(61302)) {
                        peerConnection.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61304)) {
                logger.info("Disposing peer connection");
            }
            if (!ListenerUtil.mutListener.listen(61307)) {
                if (peerConnection != null) {
                    if (!ListenerUtil.mutListener.listen(61305)) {
                        peerConnection.dispose();
                    }
                    if (!ListenerUtil.mutListener.listen(61306)) {
                        peerConnection = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61308)) {
                logger.info("Disposing audio source");
            }
            if (!ListenerUtil.mutListener.listen(61311)) {
                if (audioSource != null) {
                    if (!ListenerUtil.mutListener.listen(61309)) {
                        audioSource.dispose();
                    }
                    if (!ListenerUtil.mutListener.listen(61310)) {
                        audioSource = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61312)) {
                logger.info("Stopping and disposing capturer");
            }
            synchronized (this.capturingLock) {
                if (!ListenerUtil.mutListener.listen(61317)) {
                    if (this.videoCapturer != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(61314)) {
                                this.videoCapturer.stopCapture();
                            }
                        } catch (InterruptedException e) {
                            if (!ListenerUtil.mutListener.listen(61313)) {
                                logger.error("Spurious wakeup!");
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(61315)) {
                            this.videoCapturer.dispose();
                        }
                        if (!ListenerUtil.mutListener.listen(61316)) {
                            this.videoCapturer = null;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61318)) {
                logger.info("Disposing video source");
            }
            if (!ListenerUtil.mutListener.listen(61321)) {
                if (this.videoSource != null) {
                    if (!ListenerUtil.mutListener.listen(61319)) {
                        this.videoSource.dispose();
                    }
                    if (!ListenerUtil.mutListener.listen(61320)) {
                        this.videoSource = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61324)) {
                if (this.surfaceTextureHelper != null) {
                    if (!ListenerUtil.mutListener.listen(61322)) {
                        this.surfaceTextureHelper.dispose();
                    }
                    if (!ListenerUtil.mutListener.listen(61323)) {
                        this.surfaceTextureHelper = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61325)) {
                this.localVideoSink = null;
            }
            if (!ListenerUtil.mutListener.listen(61326)) {
                this.remoteVideoSink = null;
            }
            if (!ListenerUtil.mutListener.listen(61327)) {
                logger.info("Disposing peer connection factory");
            }
            if (!ListenerUtil.mutListener.listen(61330)) {
                if (factory != null) {
                    if (!ListenerUtil.mutListener.listen(61328)) {
                        factory.dispose();
                    }
                    if (!ListenerUtil.mutListener.listen(61329)) {
                        factory = null;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61332)) {
                if (this.events != null) {
                    if (!ListenerUtil.mutListener.listen(61331)) {
                        this.events.onPeerConnectionClosed(this.callId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61335)) {
                if (this.peerConnectionParameters.tracing) {
                    if (!ListenerUtil.mutListener.listen(61333)) {
                        PeerConnectionFactory.stopInternalTracingCapture();
                    }
                    if (!ListenerUtil.mutListener.listen(61334)) {
                        PeerConnectionFactory.shutdownInternalTracer();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61336)) {
                this.events = null;
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(61294)) {
                // Release
                if (acquired) {
                    if (!ListenerUtil.mutListener.listen(61293)) {
                        this.statsLock.release();
                    }
                }
            }
        }
    }

    public void getStats(@NonNull RTCStatsCollectorCallback callback) {
        if (!ListenerUtil.mutListener.listen(61338)) {
            if ((ListenerUtil.mutListener.listen(61337) ? (this.peerConnection == null && this.isError) : (this.peerConnection == null || this.isError))) {
                return;
            }
        }
        // Lock until all pending stats have been retrieved
        synchronized (this.statsLock) {
            if (!ListenerUtil.mutListener.listen(61346)) {
                if ((ListenerUtil.mutListener.listen(61343) ? (this.statsCounter >= 0) : (ListenerUtil.mutListener.listen(61342) ? (this.statsCounter <= 0) : (ListenerUtil.mutListener.listen(61341) ? (this.statsCounter > 0) : (ListenerUtil.mutListener.listen(61340) ? (this.statsCounter < 0) : (ListenerUtil.mutListener.listen(61339) ? (this.statsCounter != 0) : (this.statsCounter == 0))))))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(61345)) {
                            this.statsLock.acquire();
                        }
                    } catch (InterruptedException ignored) {
                        if (!ListenerUtil.mutListener.listen(61344)) {
                            logger.warn("Spurious wakeup!");
                        }
                        return;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61347)) {
                ++this.statsCounter;
            }
        }
        if (!ListenerUtil.mutListener.listen(61348)) {
            this.peerConnection.getStats(report -> {
                try {
                    callback.onStatsDelivered(report);
                } finally {
                    synchronized (this.statsLock) {
                        --this.statsCounter;
                        if (this.statsCounter == 0) {
                            this.statsLock.release();
                        }
                    }
                }
            });
        }
    }

    public void setAfterClosingStatsCallback(@NonNull RTCStatsCollectorCallback callback) {
        if (!ListenerUtil.mutListener.listen(61349)) {
            this.afterClosingStatsCallback = callback;
        }
    }

    public boolean isPeriodicStatsRegistered(@Nullable RTCStatsCollectorCallback callback) {
        if (!ListenerUtil.mutListener.listen(61350)) {
            if (callback == null) {
                return false;
            }
        }
        return this.periodicStatsTimers.containsKey(callback);
    }

    @AnyThread
    public void registerPeriodicStats(@NonNull RTCStatsCollectorCallback callback, long periodMs) {
        if (!ListenerUtil.mutListener.listen(61351)) {
            logger.debug("Registering stats every " + periodMs + "ms for callback " + callback);
        }
        Timer timer;
        try {
            timer = new Timer();
            if (!ListenerUtil.mutListener.listen(61354)) {
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(61353)) {
                            executor.execute(() -> PeerConnectionClient.this.getStats(callback));
                        }
                    }
                }, periodMs, periodMs);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(61352)) {
                logger.error("Cannot schedule statistics timer", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(61355)) {
            this.periodicStatsTimers.put(callback, timer);
        }
        if (!ListenerUtil.mutListener.listen(61356)) {
            // Run immediately (once)
            this.getStats(callback);
        }
    }

    public void unregisterPeriodicStats(@Nullable RTCStatsCollectorCallback callback) {
        if (!ListenerUtil.mutListener.listen(61357)) {
            if (callback == null) {
                return;
            }
        }
        final Timer timer = this.periodicStatsTimers.remove(callback);
        if (!ListenerUtil.mutListener.listen(61360)) {
            if (timer != null) {
                if (!ListenerUtil.mutListener.listen(61358)) {
                    timer.cancel();
                }
                if (!ListenerUtil.mutListener.listen(61359)) {
                    logger.debug("Unregistered stats for callback " + callback);
                }
            }
        }
    }

    /**
     *  Mute or unmute outgoing audio.
     */
    public void setLocalAudioTrackEnabled(final boolean enable) {
        if (!ListenerUtil.mutListener.listen(61361)) {
            executor.execute(() -> {
                enableLocalAudioTrack = enable;
                if (localAudioTrack != null) {
                    if (localAudioTrack.enabled() != enableLocalAudioTrack) {
                        localAudioTrack.setEnabled(enableLocalAudioTrack);
                        this.sendSignalingMessage(CaptureState.microphone(enableLocalAudioTrack));
                    }
                }
            });
        }
    }

    /**
     *  Mute or unmute incoming audio.
     */
    public void setRemoteAudioTrackEnabled(final boolean enable) {
        if (!ListenerUtil.mutListener.listen(61362)) {
            executor.execute(() -> {
                if (this.peerConnection != null) {
                    {
                        long _loopCounter737 = 0;
                        for (RtpTransceiver transceiver : this.getTransceivers()) {
                            ListenerUtil.loopListener.listen("_loopCounter737", ++_loopCounter737);
                            final MediaStreamTrack track = transceiver.getReceiver().track();
                            if (track instanceof AudioTrack) {
                                if (track.enabled() != enable) {
                                    if (enable) {
                                        logger.debug("Unmuting remote audio track");
                                    } else {
                                        logger.debug("Muting remote audio track");
                                    }
                                    track.setEnabled(enable);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    @AnyThread
    public void createOffer() {
        if (!ListenerUtil.mutListener.listen(61363)) {
            executor.execute(() -> {
                if (peerConnection != null && !isError) {
                    logger.debug("createOffer()");
                    isInitiator = true;
                    peerConnection.createOffer(sdpObserver, sdpMediaConstraints);
                } else {
                    logger.debug("skipping createOffer()");
                }
            });
        }
    }

    @AnyThread
    public void createAnswer() {
        if (!ListenerUtil.mutListener.listen(61364)) {
            executor.execute(() -> {
                if (peerConnection != null && !isError) {
                    logger.debug("createAnswer()");
                    isInitiator = false;
                    peerConnection.createAnswer(sdpObserver, sdpMediaConstraints);
                } else {
                    logger.debug("skipping createAnswer()");
                }
            });
        }
    }

    @AnyThread
    public void addRemoteIceCandidate(final IceCandidate candidate) {
        if (!ListenerUtil.mutListener.listen(61365)) {
            executor.execute(() -> {
                if (peerConnection != null && !isError) {
                    if (queuedRemoteCandidates != null) {
                        logger.debug("Queueing remote candidate");
                        queuedRemoteCandidates.add(candidate);
                    } else {
                        logger.debug("addRemoteIceCandidate()");
                        peerConnection.addIceCandidate(candidate);
                    }
                } else {
                    logger.debug("skipping addRemoteIceCandidate()");
                }
            });
        }
    }

    @AnyThread
    public void removeRemoteIceCandidates(final IceCandidate[] candidates) {
        if (!ListenerUtil.mutListener.listen(61366)) {
            executor.execute(() -> {
                if (peerConnection == null || isError) {
                    logger.debug("skipping removeRemoteIceCandidates()");
                    return;
                }
                // they are processed in the proper order.
                drainCandidates();
                logger.debug("removeRemoteIceCandidates()");
                peerConnection.removeIceCandidates(candidates);
            });
        }
    }

    @AnyThread
    public void setRemoteDescription(final SessionDescription sdp) {
        if (!ListenerUtil.mutListener.listen(61367)) {
            executor.execute(() -> {
                if (peerConnection == null || isError) {
                    logger.debug("skipping setRemoteDescription()");
                    return;
                }
                String sdpDescription = sdp.description;
                // TODO(ANDR-1109): Move this into SDPUtil!
                sdpDescription = preferCodec(this.logger, sdpDescription, AUDIO_CODEC_OPUS, true);
                try {
                    sdpDescription = PeerConnectionClient.this.sdpPatcher.patch(SdpPatcher.Type.LOCAL_ANSWER_OR_REMOTE_SDP, sdpDescription);
                } catch (SdpPatcher.InvalidSdpException e) {
                    this.reportError("Invalid remote SDP: " + e.getMessage(), e, true);
                    return;
                } catch (IOException e) {
                    this.reportError("Unable to patch remote SDP", e, true);
                    return;
                }
                SessionDescription sdpRemote = new SessionDescription(sdp.type, sdpDescription);
                logger.debug("Set remote SDP from {}", sdpRemote.type.canonicalForm());
                logger.debug("SDP:\n{}", sdpRemote.description);
                peerConnection.setRemoteDescription(sdpObserver, sdpRemote);
            });
        }
    }

    @AnyThread
    private void reportError(@NonNull final String errorMessage, boolean abortCall) {
        if (!ListenerUtil.mutListener.listen(61368)) {
            this.reportError(errorMessage, null, abortCall);
        }
    }

    @AnyThread
    private void reportError(@NonNull final String errorMessage, @Nullable final Throwable t, boolean abortCall) {
        if (!ListenerUtil.mutListener.listen(61371)) {
            if (t != null) {
                if (!ListenerUtil.mutListener.listen(61370)) {
                    logger.error("Error: " + errorMessage, t);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(61369)) {
                    logger.error("Error: " + errorMessage);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61372)) {
            executor.execute(() -> {
                if (events != null) {
                    events.onError(callId, errorMessage, abortCall);
                }
                isError = true;
            });
        }
    }

    /**
     *  Create and return a local audio track.
     *
     *  The track will be stored in the `localAudioTrack` instance variable.
     */
    private AudioTrack createAudioTrack() {
        if (!ListenerUtil.mutListener.listen(61373)) {
            logger.trace("createAudioTrack");
        }
        if (!ListenerUtil.mutListener.listen(61374)) {
            this.audioSource = this.factory.createAudioSource(this.audioConstraints);
        }
        if (!ListenerUtil.mutListener.listen(61375)) {
            this.localAudioTrack = this.factory.createAudioTrack(AUDIO_TRACK_ID, this.audioSource);
        }
        if (!ListenerUtil.mutListener.listen(61376)) {
            this.localAudioTrack.setEnabled(this.enableLocalAudioTrack);
        }
        return this.localAudioTrack;
    }

    /**
     *  Create and return a local video track.
     *
     *  The track will be stored in the `localVideoTrack` instance variable.
     *
     *  Note that the created video track does not yet require or start a camera capturer.
     *  It's an "empty" track initially.
     */
    @Nullable
    private VideoTrack createVideoTrack() {
        if (!ListenerUtil.mutListener.listen(61378)) {
            // Check preconditions
            if (!this.isVideoCallEnabled()) {
                if (!ListenerUtil.mutListener.listen(61377)) {
                    logger.error("Cannot create video track, isVideoCallEnabled() returns false");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(61380)) {
            if (this.eglBaseContext == null) {
                if (!ListenerUtil.mutListener.listen(61379)) {
                    logger.error("Cannot create video track, eglBaseContext is null");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(61382)) {
            if (this.factory == null) {
                if (!ListenerUtil.mutListener.listen(61381)) {
                    logger.error("Cannot create video track, factory is null");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(61384)) {
            if (this.localVideoSink == null) {
                if (!ListenerUtil.mutListener.listen(61383)) {
                    logger.error("Cannot create video track, local video sink is null");
                }
                return null;
            }
        }
        // Not yet supported
        boolean isScreencast = false;
        if (!ListenerUtil.mutListener.listen(61385)) {
            // Create helpers and a video source
            this.surfaceTextureHelper = SurfaceTextureHelper.create("VideoCaptureThread", this.eglBaseContext);
        }
        if (!ListenerUtil.mutListener.listen(61386)) {
            this.videoSource = this.factory.createVideoSource(isScreencast);
        }
        if (!ListenerUtil.mutListener.listen(61388)) {
            if (this.videoSource == null) {
                if (!ListenerUtil.mutListener.listen(61387)) {
                    logger.error("Could not create video source");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(61389)) {
            // Create local video track
            logger.trace("Creating local video track");
        }
        if (!ListenerUtil.mutListener.listen(61390)) {
            this.localVideoTrack = factory.createVideoTrack(VIDEO_TRACK_ID, this.videoSource);
        }
        if (!ListenerUtil.mutListener.listen(61392)) {
            if (this.localVideoTrack == null) {
                if (!ListenerUtil.mutListener.listen(61391)) {
                    logger.error("Could not create local video track");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(61393)) {
            this.localVideoTrack.setEnabled(this.renderVideo);
        }
        if (!ListenerUtil.mutListener.listen(61394)) {
            logger.trace("Adding sink to local video track: {}", this.localVideoSink);
        }
        if (!ListenerUtil.mutListener.listen(61395)) {
            this.localVideoTrack.addSink(this.localVideoSink);
        }
        return this.localVideoTrack;
    }

    /**
     *  Return the remote VideoTrack, assuming there is only one.
     */
    @Nullable
    private VideoTrack getRemoteVideoTrack() {
        if (!ListenerUtil.mutListener.listen(61397)) {
            {
                long _loopCounter738 = 0;
                for (RtpTransceiver transceiver : this.getTransceivers()) {
                    ListenerUtil.loopListener.listen("_loopCounter738", ++_loopCounter738);
                    final MediaStreamTrack track = transceiver.getReceiver().track();
                    if (!ListenerUtil.mutListener.listen(61396)) {
                        if (track instanceof VideoTrack) {
                            return (VideoTrack) track;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     *  Get list of transceivers.
     *
     *  WARNING: ALWAYS use this method to access transceivers! Otherwise,
     *           audio/video will be lost! DO NOT use getSenders/getReceivers,
     *           either! See ANDR-1119 for an explanation.
     */
    @NonNull
    public List<RtpTransceiver> getTransceivers() {
        if (!ListenerUtil.mutListener.listen(61398)) {
            if (this.peerConnection == null) {
                return Collections.emptyList();
            }
        }
        if (!ListenerUtil.mutListener.listen(61400)) {
            // Permanent workaround for ANDR-1119
            if (this.cachedRtpTransceivers == null) {
                if (!ListenerUtil.mutListener.listen(61399)) {
                    this.cachedRtpTransceivers = this.peerConnection.getTransceivers();
                }
            }
        }
        return this.cachedRtpTransceivers;
    }

    /**
     *  Returns the line number containing "m=audio|video", or -1 if no such line exists.
     */
    private static int findMediaDescriptionLine(boolean isAudio, String[] sdpLines) {
        final String mediaDescription = isAudio ? "m=audio " : "m=video ";
        if (!ListenerUtil.mutListener.listen(61407)) {
            {
                long _loopCounter739 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(61406) ? (i >= sdpLines.length) : (ListenerUtil.mutListener.listen(61405) ? (i <= sdpLines.length) : (ListenerUtil.mutListener.listen(61404) ? (i > sdpLines.length) : (ListenerUtil.mutListener.listen(61403) ? (i != sdpLines.length) : (ListenerUtil.mutListener.listen(61402) ? (i == sdpLines.length) : (i < sdpLines.length)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter739", ++_loopCounter739);
                    if (!ListenerUtil.mutListener.listen(61401)) {
                        if (sdpLines[i].startsWith(mediaDescription)) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static String joinString(Iterable<? extends CharSequence> s, String delimiter, boolean delimiterAtEnd) {
        Iterator<? extends CharSequence> iter = s.iterator();
        if (!ListenerUtil.mutListener.listen(61408)) {
            if (!iter.hasNext()) {
                return "";
            }
        }
        StringBuilder buffer = new StringBuilder(iter.next());
        if (!ListenerUtil.mutListener.listen(61410)) {
            {
                long _loopCounter740 = 0;
                while (iter.hasNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter740", ++_loopCounter740);
                    if (!ListenerUtil.mutListener.listen(61409)) {
                        buffer.append(delimiter).append(iter.next());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61412)) {
            if (delimiterAtEnd) {
                if (!ListenerUtil.mutListener.listen(61411)) {
                    buffer.append(delimiter);
                }
            }
        }
        return buffer.toString();
    }

    private static String movePayloadTypesToFront(@NonNull Logger logger, List<String> preferredPayloadTypes, String mLine) {
        // The format of the media description line should be: m=<media> <port> <proto> <fmt> ...
        final List<String> origLineParts = Arrays.asList(mLine.split(" "));
        if (!ListenerUtil.mutListener.listen(61419)) {
            if ((ListenerUtil.mutListener.listen(61417) ? (origLineParts.size() >= 3) : (ListenerUtil.mutListener.listen(61416) ? (origLineParts.size() > 3) : (ListenerUtil.mutListener.listen(61415) ? (origLineParts.size() < 3) : (ListenerUtil.mutListener.listen(61414) ? (origLineParts.size() != 3) : (ListenerUtil.mutListener.listen(61413) ? (origLineParts.size() == 3) : (origLineParts.size() <= 3))))))) {
                if (!ListenerUtil.mutListener.listen(61418)) {
                    logger.error("Wrong SDP media description format: {}", mLine);
                }
                return null;
            }
        }
        final List<String> header = origLineParts.subList(0, 3);
        final List<String> unpreferredPayloadTypes = new ArrayList<>(origLineParts.subList(3, origLineParts.size()));
        if (!ListenerUtil.mutListener.listen(61420)) {
            unpreferredPayloadTypes.removeAll(preferredPayloadTypes);
        }
        // types.
        final List<String> newLineParts = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(61421)) {
            newLineParts.addAll(header);
        }
        if (!ListenerUtil.mutListener.listen(61422)) {
            newLineParts.addAll(preferredPayloadTypes);
        }
        if (!ListenerUtil.mutListener.listen(61423)) {
            newLineParts.addAll(unpreferredPayloadTypes);
        }
        return joinString(newLineParts, " ", false);
    }

    private static String preferCodec(@NonNull Logger logger, String sdpDescription, String codec, boolean isAudio) {
        final String[] lines = sdpDescription.split("\r\n");
        final int mLineIndex = findMediaDescriptionLine(isAudio, lines);
        if (!ListenerUtil.mutListener.listen(61430)) {
            if ((ListenerUtil.mutListener.listen(61428) ? (mLineIndex >= -1) : (ListenerUtil.mutListener.listen(61427) ? (mLineIndex <= -1) : (ListenerUtil.mutListener.listen(61426) ? (mLineIndex > -1) : (ListenerUtil.mutListener.listen(61425) ? (mLineIndex < -1) : (ListenerUtil.mutListener.listen(61424) ? (mLineIndex != -1) : (mLineIndex == -1))))))) {
                if (!ListenerUtil.mutListener.listen(61429)) {
                    logger.warn("Warning: No mediaDescription line, so can't prefer {}", codec);
                }
                return sdpDescription;
            }
        }
        // range 96-127, but they are stored as strings here.
        final List<String> codecPayloadTypes = new ArrayList<>();
        // a=rtpmap:<payload type> <encoding name>/<clock rate> [/<encoding parameters>]
        final Pattern codecPattern = Pattern.compile("^a=rtpmap:(\\d+) " + codec + "(/\\d+)+[\r]?$");
        if (!ListenerUtil.mutListener.listen(61433)) {
            {
                long _loopCounter741 = 0;
                for (String line : lines) {
                    ListenerUtil.loopListener.listen("_loopCounter741", ++_loopCounter741);
                    Matcher codecMatcher = codecPattern.matcher(line);
                    if (!ListenerUtil.mutListener.listen(61432)) {
                        if (codecMatcher.matches()) {
                            if (!ListenerUtil.mutListener.listen(61431)) {
                                codecPayloadTypes.add(codecMatcher.group(1));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61435)) {
            if (codecPayloadTypes.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(61434)) {
                    logger.warn("Warning: No payload types with name {}", codec);
                }
                return sdpDescription;
            }
        }
        final String newMLine = movePayloadTypesToFront(logger, codecPayloadTypes, lines[mLineIndex]);
        if (!ListenerUtil.mutListener.listen(61436)) {
            if (newMLine == null) {
                return sdpDescription;
            }
        }
        if (!ListenerUtil.mutListener.listen(61437)) {
            logger.warn("Change media description from {} to {}", lines[mLineIndex], newMLine);
        }
        if (!ListenerUtil.mutListener.listen(61438)) {
            lines[mLineIndex] = newMLine;
        }
        return joinString(Arrays.asList(lines), "\r\n", true);
    }

    private void drainCandidates() {
        if (!ListenerUtil.mutListener.listen(61439)) {
            logger.trace("drainCandidates()");
        }
        if (!ListenerUtil.mutListener.listen(61444)) {
            if (queuedRemoteCandidates != null) {
                if (!ListenerUtil.mutListener.listen(61440)) {
                    logger.debug("Add {} remote candidates", queuedRemoteCandidates.size());
                }
                if (!ListenerUtil.mutListener.listen(61442)) {
                    {
                        long _loopCounter742 = 0;
                        for (IceCandidate candidate : queuedRemoteCandidates) {
                            ListenerUtil.loopListener.listen("_loopCounter742", ++_loopCounter742);
                            if (!ListenerUtil.mutListener.listen(61441)) {
                                peerConnection.addIceCandidate(candidate);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(61443)) {
                    queuedRemoteCandidates = null;
                }
            }
        }
    }

    // Implementation detail: observe ICE & stream changes and react accordingly.
    private class PCObserver implements PeerConnection.Observer {

        @NonNull
        private Set<String> relatedAddresses = new HashSet<>();

        @Override
        public void onIceCandidate(final IceCandidate candidate) {
            if (!ListenerUtil.mutListener.listen(61445)) {
                logger.info("New local ICE candidate: {}", candidate.sdp);
            }
            if (!ListenerUtil.mutListener.listen(61447)) {
                // Discard loopback candidates
                if (SdpUtil.isLoopbackCandidate(candidate.sdp)) {
                    if (!ListenerUtil.mutListener.listen(61446)) {
                        logger.info("Ignoring local ICE candidate (loopback): {}", candidate.sdp);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(61450)) {
                // Discard IPv6 candidates if disabled
                if ((ListenerUtil.mutListener.listen(61448) ? (!PeerConnectionClient.this.peerConnectionParameters.allowIpv6 || SdpUtil.isIpv6Candidate(candidate.sdp)) : (!PeerConnectionClient.this.peerConnectionParameters.allowIpv6 && SdpUtil.isIpv6Candidate(candidate.sdp)))) {
                    if (!ListenerUtil.mutListener.listen(61449)) {
                        logger.info("Ignoring local ICE candidate (ipv6_disabled): {}", candidate.sdp);
                    }
                    return;
                }
            }
            // Important: This only works as long as we don't do ICE restarts and don't add further transports!
            final String relatedAddress = SdpUtil.getRelatedAddress(candidate.sdp);
            if (!ListenerUtil.mutListener.listen(61455)) {
                if ((ListenerUtil.mutListener.listen(61451) ? (relatedAddress != null || !relatedAddress.equals("0.0.0.0")) : (relatedAddress != null && !relatedAddress.equals("0.0.0.0")))) {
                    if (!ListenerUtil.mutListener.listen(61454)) {
                        if (this.relatedAddresses.contains(relatedAddress)) {
                            if (!ListenerUtil.mutListener.listen(61453)) {
                                logger.info("Ignoring local ICE candidate (duplicate_related_addr {}): {}", relatedAddress, candidate.sdp);
                            }
                            return;
                        } else {
                            if (!ListenerUtil.mutListener.listen(61452)) {
                                this.relatedAddresses.add(relatedAddress);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(61456)) {
                // Dispatch event
                executor.execute(() -> events.onIceCandidate(callId, candidate));
            }
        }

        @Override
        public void onIceCandidatesRemoved(final IceCandidate[] candidates) {
            if (!ListenerUtil.mutListener.listen(61458)) {
                if (logger.isInfoEnabled()) {
                    if (!ListenerUtil.mutListener.listen(61457)) {
                        logger.info("Ignoring removed candidates: {}", Arrays.toString(candidates));
                    }
                }
            }
        }

        @Override
        public void onSignalingChange(PeerConnection.SignalingState newState) {
            if (!ListenerUtil.mutListener.listen(61459)) {
                logger.info("Signaling state change to {}", newState);
            }
        }

        @Override
        public void onIceConnectionChange(final PeerConnection.IceConnectionState newState) {
            if (!ListenerUtil.mutListener.listen(61460)) {
                executor.execute(() -> {
                    logger.info("ICE connection state change to {}", newState);
                    if (newState == IceConnectionState.CHECKING) {
                        events.onIceChecking(callId);
                    } else if (newState == IceConnectionState.CONNECTED) {
                        if (iceFailedFuture != null) {
                            // already running.
                            iceFailedFuture.cancel(false);
                            logger.info("iceFailedFuture: Cancelled (connected)");
                            iceFailedFuture = null;
                        }
                        events.onIceConnected(callId);
                    } else if (newState == IceConnectionState.DISCONNECTED) {
                        events.onIceDisconnected(callId);
                    } else if (newState == IceConnectionState.FAILED) {
                        logger.warn("IceConnectionState changed to FAILED");
                        // to CONNECTED within 15s after setting the remote description.
                        long minimalWaitingTimeSeconds = 15;
                        if (setRemoteDescriptionNanotime == null) {
                            // This should not happen
                            logger.error("createOfferAnswerNanotime is null in onIceConnectionState");
                            events.onIceFailed(callId);
                        } else {
                            // Elapsed nanoseconds since the remote description was set
                            final long elapsedNs = System.nanoTime() - setRemoteDescriptionNanotime;
                            // Max waiting time in nanoseconds
                            final long waitingTimeNs = minimalWaitingTimeSeconds * 1000000000L;
                            if (elapsedNs > waitingTimeNs) {
                                // Minimal waiting time already exceeded, trigger event immediately
                                events.onIceFailed(callId);
                            } else {
                                // events.onIceFailed unless it's already scheduled.
                                if (iceFailedFuture == null) {
                                    final long remainingNs = waitingTimeNs - elapsedNs;
                                    logger.info("iceFailedFuture: Delaying onIceFailed call, {} ms remaining", remainingNs / 1000000);
                                    // noinspection Convert2Lambda
                                    iceFailedFuture = executor.schedule(new Runnable() {

                                        @Override
                                        @AnyThread
                                        public void run() {
                                            logger.info("iceFailedFuture: Time's up, calling onIceFailed");
                                            events.onIceFailed(callId);
                                        }
                                    }, remainingNs, TimeUnit.NANOSECONDS);
                                }
                            }
                        }
                    }
                });
            }
        }

        @Override
        public void onIceGatheringChange(PeerConnection.IceGatheringState newState) {
            if (!ListenerUtil.mutListener.listen(61461)) {
                logger.info("ICE gathering state change to {}", newState);
            }
            if (!ListenerUtil.mutListener.listen(61462)) {
                events.onIceGatheringStateChange(callId, newState);
            }
        }

        @Override
        public void onIceConnectionReceivingChange(boolean receiving) {
            if (!ListenerUtil.mutListener.listen(61463)) {
                logger.info("ICe connection receiving state change to {}", receiving);
            }
        }

        @Override
        public void onAddStream(final MediaStream stream) {
            if (!ListenerUtil.mutListener.listen(61464)) {
                logger.warn("Warning: onAddStream (even though we use unified plan)");
            }
        }

        @Override
        public void onRemoveStream(final MediaStream stream) {
            if (!ListenerUtil.mutListener.listen(61465)) {
                logger.warn("Warning: onRemoveStream (even though we use unified plan)");
            }
        }

        @Override
        public void onDataChannel(final DataChannel dc) {
            try {
                if (!ListenerUtil.mutListener.listen(61467)) {
                    logger.warn("New unexpected data channel: {} (id={})", dc.label(), dc.id());
                }
            } catch (IllegalStateException e) {
                if (!ListenerUtil.mutListener.listen(61466)) {
                    logger.error("New unexpected data channel (could not fetch information)", e);
                }
            }
        }

        @Override
        public void onRenegotiationNeeded() {
            if (!ListenerUtil.mutListener.listen(61468)) {
                logger.info("Renegotiation needed");
            }
        }

        @Override
        public void onAddTrack(final RtpReceiver receiver, final MediaStream[] mediaStreams) {
            if (!ListenerUtil.mutListener.listen(61469)) {
                logger.debug("onAddTrack");
            }
        }

        @Override
        public void onTrack(RtpTransceiver transceiver) {
            if (!ListenerUtil.mutListener.listen(61470)) {
                logger.debug("onTrack");
            }
        }
    }

    // as well as adding remote ICE candidates once the answer SDP is set.
    private class SDPObserver implements SdpObserver {

        @Override
        public void onCreateSuccess(final SessionDescription origSdp) {
            if (!ListenerUtil.mutListener.listen(61472)) {
                if (localSdp != null) {
                    if (!ListenerUtil.mutListener.listen(61471)) {
                        // (with showErrorNotification=true).
                        logger.error("onCreateSuccess while localSdp is not null");
                    }
                    return;
                }
            }
            final String sdpDescription;
            final SdpPatcher.Type sdpPatcherType = isInitiator ? SdpPatcher.Type.LOCAL_OFFER : SdpPatcher.Type.LOCAL_ANSWER_OR_REMOTE_SDP;
            try {
                sdpDescription = PeerConnectionClient.this.sdpPatcher.patch(sdpPatcherType, origSdp.description);
            } catch (SdpPatcher.InvalidSdpException e) {
                if (!ListenerUtil.mutListener.listen(61473)) {
                    reportError("Invalid remote SDP: " + e.getMessage(), e, true);
                }
                return;
            } catch (IOException e) {
                if (!ListenerUtil.mutListener.listen(61474)) {
                    reportError("Unable to patch remote SDP", e, true);
                }
                return;
            }
            final SessionDescription sdp = new SessionDescription(origSdp.type, sdpDescription);
            if (!ListenerUtil.mutListener.listen(61475)) {
                localSdp = sdp;
            }
            if (!ListenerUtil.mutListener.listen(61476)) {
                executor.execute(() -> {
                    if (peerConnection != null && !isError) {
                        logger.debug("Set local SDP from {}", sdp.type.canonicalForm());
                        logger.debug("SDP:\n{}", sdp.description);
                        peerConnection.setLocalDescription(sdpObserver, sdp);
                    }
                });
            }
        }

        @Override
        public void onSetSuccess() {
            if (!ListenerUtil.mutListener.listen(61477)) {
                executor.execute(() -> {
                    if (peerConnection == null || isError) {
                        return;
                    }
                    if (isInitiator) {
                        // local SDP, then after receiving answer set remote SDP.
                        if (peerConnection.getRemoteDescription() == null) {
                            // We've just set our local SDP so time to send it.
                            logger.info("Local SDP set succesfully");
                            if (events != null) {
                                events.onLocalDescription(callId, localSdp);
                            }
                        } else {
                            // and send local ICE candidates.
                            logger.info("Remote SDP set succesfully");
                            setRemoteDescriptionNanotime = System.nanoTime();
                            if (events != null) {
                                events.onRemoteDescriptionSet(callId);
                            }
                            drainCandidates();
                        }
                    } else {
                        // create answer and set local SDP.
                        if (peerConnection.getLocalDescription() != null) {
                            // remote and send local ICE candidates.
                            logger.info("Local SDP set succesfully");
                            if (events != null) {
                                events.onLocalDescription(callId, localSdp);
                            }
                            drainCandidates();
                        } else {
                            // answer will be created soon.
                            logger.info("Remote SDP set succesfully");
                            setRemoteDescriptionNanotime = System.nanoTime();
                            if (events != null) {
                                events.onRemoteDescriptionSet(callId);
                            }
                        }
                    }
                });
            }
        }

        @Override
        public void onCreateFailure(final String error) {
            if (!ListenerUtil.mutListener.listen(61478)) {
                reportError("SDP onCreateFailure: " + error, true);
            }
        }

        @Override
        public void onSetFailure(final String error) {
            if (!ListenerUtil.mutListener.listen(61479)) {
                // while a call is already established. This should get resolved by SE-49.
                logger.warn("onSetFailure: " + error);
            }
            if (!ListenerUtil.mutListener.listen(61482)) {
                if ((ListenerUtil.mutListener.listen(61480) ? (error != null || error.contains("Called in wrong state: kStable")) : (error != null && error.contains("Called in wrong state: kStable")))) {
                    if (!ListenerUtil.mutListener.listen(61481)) {
                        reportError("SDP onSetFailure: " + error, false);
                    }
                }
            }
        }
    }

    private class DCObserver extends DataChannelObserver {

        @NonNull
        private final Logger logger = LoggerFactory.getLogger("SignalingDataChannel");

        @NonNull
        final CompletableFuture<?> openFuture = new CompletableFuture<>();

        @Override
        public void onBufferedAmountChange(long l) {
            if (!ListenerUtil.mutListener.listen(61483)) {
                logger.debug("onBufferedAmountChange: {}", l);
            }
            final UnboundedFlowControlledDataChannel ufcdc = PeerConnectionClient.this.signalingDataChannel;
            if (!ListenerUtil.mutListener.listen(61485)) {
                if (ufcdc == null) {
                    if (!ListenerUtil.mutListener.listen(61484)) {
                        logger.warn("onBufferedAmountChange, but signalingDataChannel is null");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(61486)) {
                // Important: ALWAYS dispatch this event to another thread because webrtc.org!
                RuntimeUtil.runInAsyncTask(ufcdc::bufferedAmountChange);
            }
        }

        @Override
        public synchronized void onStateChange(@NonNull DataChannel.State state) {
            if (!ListenerUtil.mutListener.listen(61487)) {
                logger.debug("onStateChange: {}", state);
            }
            if (!ListenerUtil.mutListener.listen(61494)) {
                switch(state) {
                    case CONNECTING:
                        // Ignore
                        break;
                    case OPEN:
                        if (!ListenerUtil.mutListener.listen(61488)) {
                            logger.info("Data channel is open");
                        }
                        if (!ListenerUtil.mutListener.listen(61491)) {
                            if (signalingDataChannel != null) {
                                if (!ListenerUtil.mutListener.listen(61490)) {
                                    this.openFuture.complete(null);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(61489)) {
                                    logger.error("onStateChange: data channel is null!");
                                }
                            }
                        }
                        break;
                    case CLOSING:
                        if (!ListenerUtil.mutListener.listen(61492)) {
                            logger.info("Data channel is closing");
                        }
                        break;
                    case CLOSED:
                        if (!ListenerUtil.mutListener.listen(61493)) {
                            logger.info("Data channel is closed");
                        }
                        break;
                }
            }
        }

        @Override
        public synchronized void onMessage(DataChannel.Buffer buffer) {
            if (!ListenerUtil.mutListener.listen(61495)) {
                logger.debug("Received message ({} bytes)", buffer.data.remaining());
            }
            if (!ListenerUtil.mutListener.listen(61497)) {
                if (!buffer.binary) {
                    if (!ListenerUtil.mutListener.listen(61496)) {
                        logger.warn("Received non-binary data channel message, discarding");
                    }
                    return;
                }
            }
            // Copy the message since the ByteBuffer will be reused immediately
            final ByteBuffer copy = ByteBuffer.allocate(buffer.data.remaining());
            if (!ListenerUtil.mutListener.listen(61498)) {
                copy.put(buffer.data);
            }
            if (!ListenerUtil.mutListener.listen(61499)) {
                copy.flip();
            }
            if (!ListenerUtil.mutListener.listen(61500)) {
                // Notify event listener asychronously
                RuntimeUtil.runInAsyncTask(() -> {
                    try {
                        @NonNull
                        final CallSignaling.Envelope envelope = CallSignaling.Envelope.parseFrom(copy);
                        if (events != null) {
                            events.onSignalingMessage(callId, envelope);
                        }
                    } catch (InvalidProtocolBufferException e) {
                        logger.warn("Could not parse incoming signaling message", e);
                    }
                });
            }
        }
    }

    /**
     *  Create a video capturer. Return whether the action succeeded.
     *
     *  @locks {@link #capturingLock}
     */
    @AnyThread
    private boolean setupCapturer() {
        if (!ListenerUtil.mutListener.listen(61501)) {
            logger.info("Set up capturer");
        }
        synchronized (this.capturingLock) {
            if (!ListenerUtil.mutListener.listen(61502)) {
                // Create video capturer
                this.videoCapturer = VideoCapturerUtil.createVideoCapturer(this.appContext, new CameraEventsHandler());
            }
            if (this.videoCapturer == null) {
                if (!ListenerUtil.mutListener.listen(61503)) {
                    logger.error("Could not create camera video capturer");
                }
                return false;
            }
            if (!ListenerUtil.mutListener.listen(61504)) {
                logger.info("Video capturer created");
            }
            // Initialize capturer
            if (this.videoSource == null) {
                if (!ListenerUtil.mutListener.listen(61505)) {
                    logger.error("Could not start capturing, video source is null");
                }
                return false;
            }
            if (!ListenerUtil.mutListener.listen(61506)) {
                this.videoCapturer.initialize(this.surfaceTextureHelper, this.appContext, this.videoSource.getCapturerObserver());
            }
            return true;
        }
    }

    /**
     *  Create a video capturer (if necessary) and start capturing.
     *
     *  Return VideoCapturer on success, null otherwise.
     *
     *  Note: WebRTC's CameraCapturer may block so it's better to call this method from a worker thread
     *
     *  @locks {@link #capturingLock}
     */
    @WorkerThread
    @Nullable
    public VideoCapturer startCapturing(@Nullable VoipVideoParams params) {
        if (!ListenerUtil.mutListener.listen(61507)) {
            logger.info("Start capturing");
        }
        synchronized (this.capturingLock) {
            if (!ListenerUtil.mutListener.listen(61509)) {
                // Initialize capturer
                if (this.videoCapturer == null) {
                    if (!ListenerUtil.mutListener.listen(61508)) {
                        if (!setupCapturer()) {
                            return null;
                        }
                    }
                }
            }
            // Start capturing
            try {
                if (!ListenerUtil.mutListener.listen(61518)) {
                    if (params != null) {
                        if (!ListenerUtil.mutListener.listen(61517)) {
                            this.videoCapturer.startCapture(params.getMaxWidth(), params.getMaxHeight(), params.getMaxFps());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(61516)) {
                            this.videoCapturer.startCapture(VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_FPS);
                        }
                    }
                }
            } catch (RuntimeException e) {
                if (!ListenerUtil.mutListener.listen(61510)) {
                    this.videoCapturer = null;
                }
                if (!ListenerUtil.mutListener.listen(61511)) {
                    if (!setupCapturer()) {
                        return null;
                    }
                }
                // try again after setting up capturer again
                try {
                    if (!ListenerUtil.mutListener.listen(61515)) {
                        if (params != null) {
                            if (!ListenerUtil.mutListener.listen(61514)) {
                                this.videoCapturer.startCapture(params.getMaxWidth(), params.getMaxHeight(), params.getMaxFps());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(61513)) {
                                this.videoCapturer.startCapture(VIDEO_WIDTH, VIDEO_HEIGHT, VIDEO_FPS);
                            }
                        }
                    }
                } catch (RuntimeException ignored) {
                    if (!ListenerUtil.mutListener.listen(61512)) {
                        this.videoCapturer = null;
                    }
                    return null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61520)) {
            // Notify peer
            if (this.videoCapturer != null) {
                if (!ListenerUtil.mutListener.listen(61519)) {
                    this.sendSignalingMessage(CaptureState.camera(true));
                }
            }
        }
        // Return capturer
        return this.videoCapturer;
    }

    /**
     *  Change the capture format on the fly.
     *  This will do nothing if no video capturer is set up, or if it wasn't started.
     *
     *  Note: WebRTC's CameraCapturer may block so it's better to call this method from a worker thread
     *
     *  @locks {@link #capturingLock}
     */
    @WorkerThread
    private void changeCapturingFormat(int width, int height, int fps) {
        if (!ListenerUtil.mutListener.listen(61521)) {
            logger.debug("Change capturing format");
        }
        synchronized (this.capturingLock) {
            if (!ListenerUtil.mutListener.listen(61525)) {
                if ((ListenerUtil.mutListener.listen(61522) ? (this.videoCapturer != null || this.videoCapturer.isCapturing()) : (this.videoCapturer != null && this.videoCapturer.isCapturing()))) {
                    if (!ListenerUtil.mutListener.listen(61524)) {
                        // WARNING: If the capturer is not started, this will implicitly start it!
                        this.videoCapturer.changeCaptureFormat(width, height, fps);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(61523)) {
                        logger.debug("Ignoring capturing format change, not currently capturing");
                    }
                }
            }
        }
    }

    /**
     *  Stop capturing asynchronously.
     *
     *  Note: WebRTC's CameraCapturer may block so it's better to call this method from a worker thread
     *
     *  @locks {@link #capturingLock}
     */
    @WorkerThread
    public void stopCapturing() {
        if (!ListenerUtil.mutListener.listen(61526)) {
            logger.info("Stop capturing");
        }
        synchronized (this.capturingLock) {
            if (!ListenerUtil.mutListener.listen(61532)) {
                if (this.videoCapturer != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(61530)) {
                            this.videoCapturer.stopCapture();
                        }
                        if (!ListenerUtil.mutListener.listen(61531)) {
                            logger.info("Stopped capturing");
                        }
                    } catch (InterruptedException e) {
                        if (!ListenerUtil.mutListener.listen(61528)) {
                            logger.error("Interrupted while stopping video capturer", e);
                        }
                        if (!ListenerUtil.mutListener.listen(61529)) {
                            Thread.currentThread().interrupt();
                        }
                        return;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(61527)) {
                        logger.warn("stopCapturing: Video capturer is null");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61533)) {
            // Notify peer
            this.sendSignalingMessage(CaptureState.camera(false));
        }
    }

    /**
     *  Change the outgoing video parameters by setting the appropriate RTP parameters.
     */
    @AnyThread
    public void changeOutgoingVideoParams(@NonNull VoipVideoParams params) {
        if (!ListenerUtil.mutListener.listen(61534)) {
            logger.info("Changing outgoing video params to {}.", params);
        }
        if (!ListenerUtil.mutListener.listen(61535)) {
            executor.execute(() -> {
                // Adjust capturer
                this.changeCapturingFormat(params.getMaxWidth(), params.getMaxHeight(), params.getMaxFps());
                // Adjust encoder
                this.setOutgoingVideoEncoderLimits(params.getMaxBitrateKbps() * 1000, params.getMaxFps());
            });
        }
    }

    class CameraEventsHandler implements CameraVideoCapturer.CameraEventsHandler {

        @Override
        public void onCameraError(String s) {
            if (!ListenerUtil.mutListener.listen(61536)) {
                logger.error("Camera error: {}", s);
            }
            final String msg = appContext.getString(R.string.msg_camera_framework_bug);
            if (!ListenerUtil.mutListener.listen(61537)) {
                RuntimeUtil.runOnUiThread(() -> SingleToast.getInstance().showBottom(msg, Toast.LENGTH_LONG));
            }
        }

        @Override
        public void onCameraDisconnected() {
            if (!ListenerUtil.mutListener.listen(61538)) {
                logger.info("Camera disconnected");
            }
            if (!ListenerUtil.mutListener.listen(61539)) {
                // Let's stop the capturing session.
                VoipUtil.sendVoipBroadcast(appContext, VoipCallService.ACTION_STOP_CAPTURING);
            }
        }

        @Override
        public void onCameraFreezed(String s) {
            if (!ListenerUtil.mutListener.listen(61540)) {
                logger.error("Camera frozen: {}", s);
            }
        }

        @Override
        public void onCameraOpening(String s) {
            if (!ListenerUtil.mutListener.listen(61541)) {
                logger.info("Camera opening: {}", s);
            }
        }

        @Override
        public void onFirstFrameAvailable() {
            if (!ListenerUtil.mutListener.listen(61542)) {
                logger.debug("Camera first frame available");
            }
            if (!ListenerUtil.mutListener.listen(61544)) {
                if (events != null) {
                    if (!ListenerUtil.mutListener.listen(61543)) {
                        events.onCameraFirstFrameAvailable();
                    }
                }
            }
        }

        @Override
        public void onCameraClosed() {
            if (!ListenerUtil.mutListener.listen(61545)) {
                logger.info("Camera closed");
            }
        }
    }

    /**
     *  Enqueue a signaling message for sending it through the signaling data channel
     *  once it's open and ready to send.
     */
    @AnyThread
    public void sendSignalingMessage(@NonNull ToSignalingMessage message) {
        if (!ListenerUtil.mutListener.listen(61547)) {
            if (this.signalingDataChannel == null) {
                if (!ListenerUtil.mutListener.listen(61546)) {
                    logger.warn("queueSignalingMessage: Data channel is null");
                }
                return;
            }
        }
        final ByteBuffer buffer = message.toSignalingMessageByteBuffer();
        if (!ListenerUtil.mutListener.listen(61548)) {
            logger.debug("Enqueuing signaling message: ({}, {} bytes)", message, buffer.remaining());
        }
        if (!ListenerUtil.mutListener.listen(61549)) {
            this.signalingDataChannel.write(new DataChannel.Buffer(buffer, true));
        }
    }
}
