/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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
package ch.threema.app.webclient.webrtc;

import android.content.Context;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import org.saltyrtc.client.exceptions.ConnectionException;
import org.saltyrtc.tasks.webrtc.WebRTCTask;
import org.saltyrtc.tasks.webrtc.messages.Answer;
import org.saltyrtc.tasks.webrtc.messages.Candidate;
import org.saltyrtc.tasks.webrtc.messages.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnection.IceConnectionState;
import org.webrtc.PeerConnection.IceGatheringState;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import ch.threema.annotation.SameThread;
import ch.threema.app.voip.util.SdpUtil;
import ch.threema.app.utils.WebRTCUtil;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.Config;
import ch.threema.app.webclient.listeners.PeerConnectionListener;
import ch.threema.app.webclient.state.PeerConnectionState;
import ch.threema.client.APIConnector;
import ch.threema.logging.ThreemaLogger;
import java8.util.concurrent.CompletableFuture;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Wrapper around the WebRTC PeerConnection.
 *
 * This handles everything from creating the peer connection
 * to destroying it afterwards.
 */
@SameThread
public class PeerConnectionWrapper {

    private static final String THREEMA_DC_LABEL = "THREEMA";

    // Logger
    private final Logger logger = LoggerFactory.getLogger(PeerConnectionWrapper.class);

    // Worker thread handler
    @NonNull
    private final HandlerExecutor handler;

    // WebRTC / SaltyRTC
    @NonNull
    private final PeerConnectionFactory factory;

    @NonNull
    private final org.webrtc.PeerConnection pc;

    @NonNull
    private final WebRTCTask task;

    private final boolean allowIpv6;

    // State
    @NonNull
    private CompletableFuture<Void> readyToSetRemoteDescription = new CompletableFuture<>();

    @NonNull
    private CompletableFuture<Void> readyToAddRemoteCandidates = new CompletableFuture<>();

    @NonNull
    private CompletableFuture<Void> readyToSendLocalCandidates = new CompletableFuture<>();

    @NonNull
    private PeerConnectionState state = PeerConnectionState.NEW;

    private boolean disposed = false;

    // Listener
    @NonNull
    private final PeerConnectionListener listener;

    /**
     *  Return a PeerConnectionFactory instance used for Threema Web.
     */
    public static PeerConnectionFactory getPeerConnectionFactory() {
        return PeerConnectionFactory.builder().createPeerConnectionFactory();
    }

    /**
     *  Return the RTCConfiguration used for Threema Web.
     */
    public static PeerConnection.RTCConfiguration getRTCConfiguration(@NonNull final Logger logger) throws Exception {
        // Set ICE servers
        final List<org.webrtc.PeerConnection.IceServer> iceServers = new ArrayList<>();
        final APIConnector.TurnServerInfo turnServerInfo = Config.getTurnServerCache().getTurnServers();
        final List<String> turnServers = Arrays.asList(turnServerInfo.turnUrls);
        if (!ListenerUtil.mutListener.listen(64849)) {
            StreamSupport.stream(turnServers).map(server -> PeerConnection.IceServer.builder(server).setUsername(turnServerInfo.turnUsername).setPassword(turnServerInfo.turnPassword).createIceServer()).forEach(iceServers::add);
        }
        if (!ListenerUtil.mutListener.listen(64850)) {
            logger.debug("Using ICE servers: {}", turnServers);
        }
        // Set up RTC configuration
        final PeerConnection.RTCConfiguration rtcConfig = new PeerConnection.RTCConfiguration(iceServers);
        if (!ListenerUtil.mutListener.listen(64851)) {
            rtcConfig.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_ONCE;
        }
        if (!ListenerUtil.mutListener.listen(64852)) {
            rtcConfig.sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN;
        }
        return rtcConfig;
    }

    /**
     *  Initialize a peer connection.
     */
    public PeerConnectionWrapper(@NonNull final String logPrefix, @NonNull final Context appContext, @NonNull final HandlerExecutor handler, @NonNull final WebRTCTask task, @NonNull final TemporaryTaskEventHandler temporaryTaskEventHandler, final boolean allowIpv6, @NonNull final PeerConnectionListener listener) throws Exception {
        if (!ListenerUtil.mutListener.listen(64854)) {
            // Set logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(64853)) {
                    ((ThreemaLogger) logger).setPrefix(logPrefix);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64855)) {
            logger.info("Initialize WebRTC PeerConnection");
        }
        if (!ListenerUtil.mutListener.listen(64856)) {
            // Initialise WebRTC for Android
            WebRTCUtil.initializeAndroidGlobals(appContext);
        }
        this.factory = getPeerConnectionFactory();
        // Store handler, listener, task and set message handler
        this.handler = handler;
        this.listener = listener;
        this.task = task;
        if (!ListenerUtil.mutListener.listen(64857)) {
            temporaryTaskEventHandler.replace(this.task, new TaskMessageHandler());
        }
        this.allowIpv6 = allowIpv6;
        // Create peer connection
        final PeerConnection peerConnection = factory.createPeerConnection(getRTCConfiguration(logger), new PeerConnectionObserver());
        if (!ListenerUtil.mutListener.listen(64858)) {
            if (peerConnection == null) {
                throw new RuntimeException("Could not create peer connection: createPeerConnection returned null");
            }
        }
        this.pc = peerConnection;
    }

    /**
     *  If the instance is disposed, throw an exception.
     */
    private void ensureNotDisposed() {
        if (!ListenerUtil.mutListener.listen(64859)) {
            if (this.disposed) {
                throw new IllegalStateException("PeerConnection is disposed");
            }
        }
    }

    /**
     *  Handler for incoming task messages.
     */
    @AnyThread
    private class TaskMessageHandler implements org.saltyrtc.tasks.webrtc.events.MessageHandler {

        @Override
        public void onOffer(@NonNull final Offer offer) {
            if (!ListenerUtil.mutListener.listen(64863)) {
                PeerConnectionWrapper.this.readyToSetRemoteDescription.thenRunAsync(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(64862)) {
                            if (PeerConnectionWrapper.this.disposed) {
                                if (!ListenerUtil.mutListener.listen(64861)) {
                                    logger.warn("Ignoring offer, peer connection already disposed");
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(64860)) {
                                    PeerConnectionWrapper.this.onOfferReceived(offer);
                                }
                            }
                        }
                    }
                }, PeerConnectionWrapper.this.handler.getExecutor());
            }
        }

        @Override
        public void onAnswer(@NonNull final Answer answer) {
            if (!ListenerUtil.mutListener.listen(64864)) {
                logger.warn("Ignoring answer");
            }
        }

        @Override
        public void onCandidates(@NonNull final Candidate[] candidates) {
            if (!ListenerUtil.mutListener.listen(64868)) {
                PeerConnectionWrapper.this.readyToAddRemoteCandidates.thenRunAsync(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(64867)) {
                            if (PeerConnectionWrapper.this.disposed) {
                                if (!ListenerUtil.mutListener.listen(64866)) {
                                    logger.warn("Ignoring candidates, peer connection already disposed");
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(64865)) {
                                    PeerConnectionWrapper.this.onIceCandidatesReceived(candidates);
                                }
                            }
                        }
                    }
                }, PeerConnectionWrapper.this.handler.getExecutor());
            }
        }
    }

    /**
     *  A WebRTC offer was received. Set the remote description.
     */
    @AnyThread
    private void onOfferReceived(@NonNull final Offer offer) {
        if (!ListenerUtil.mutListener.listen(64869)) {
            logger.info("Offer received, applying as remote description");
        }
        if (!ListenerUtil.mutListener.listen(64872)) {
            this.pc.setRemoteDescription(new SdpObserver() {

                @Override
                @AnyThread
                public void onCreateSuccess(@NonNull final SessionDescription description) {
                }

                @Override
                @AnyThread
                public void onCreateFailure(@NonNull final String error) {
                }

                @Override
                @AnyThread
                public void onSetSuccess() {
                    if (!ListenerUtil.mutListener.listen(64870)) {
                        PeerConnectionWrapper.this.onRemoteDescriptionSet();
                    }
                }

                @Override
                @AnyThread
                public void onSetFailure(@NonNull final String error) {
                    if (!ListenerUtil.mutListener.listen(64871)) {
                        logger.error("Could not apply remote description: {}", error);
                    }
                }
            }, new SessionDescription(SessionDescription.Type.OFFER, offer.getSdp()));
        }
    }

    /**
     *  The remote description was set. Create and send an answer.
     */
    @AnyThread
    private void onRemoteDescriptionSet() {
        if (!ListenerUtil.mutListener.listen(64873)) {
            logger.info("Remote description applied successfully, creating answer");
        }
        if (!ListenerUtil.mutListener.listen(64885)) {
            this.pc.createAnswer(new SdpObserver() {

                @Nullable
                private SessionDescription description;

                @Override
                @AnyThread
                public synchronized void onCreateSuccess(@NonNull final SessionDescription description) {
                    if (!ListenerUtil.mutListener.listen(64874)) {
                        logger.info("Created answer");
                    }
                    if (!ListenerUtil.mutListener.listen(64875)) {
                        this.description = description;
                    }
                    if (!ListenerUtil.mutListener.listen(64876)) {
                        PeerConnectionWrapper.this.pc.setLocalDescription(this, description);
                    }
                }

                @Override
                @AnyThread
                public void onCreateFailure(@NonNull final String error) {
                    if (!ListenerUtil.mutListener.listen(64877)) {
                        logger.error("Could not create answer: {}", error);
                    }
                }

                @Override
                @AnyThread
                public synchronized void onSetSuccess() {
                    if (!ListenerUtil.mutListener.listen(64878)) {
                        logger.info("Local description applied successfully, sending answer");
                    }
                    final Answer answer = new Answer(Objects.requireNonNull(this.description).description);
                    if (!ListenerUtil.mutListener.listen(64883)) {
                        PeerConnectionWrapper.this.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(64879)) {
                                    logger.debug("Sending answer");
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(64881)) {
                                        // Send the answer
                                        PeerConnectionWrapper.this.task.sendAnswer(answer);
                                    }
                                    if (!ListenerUtil.mutListener.listen(64882)) {
                                        // Signal that local ICE candidates may be sent now
                                        PeerConnectionWrapper.this.readyToSendLocalCandidates.complete(null);
                                    }
                                } catch (ConnectionException error) {
                                    if (!ListenerUtil.mutListener.listen(64880)) {
                                        logger.error("Could not send answer", error);
                                    }
                                }
                            }
                        });
                    }
                }

                @Override
                @AnyThread
                public void onSetFailure(@NonNull final String error) {
                    if (!ListenerUtil.mutListener.listen(64884)) {
                        logger.error("Could not set local description: {}", error);
                    }
                }
            }, new MediaConstraints());
        }
        if (!ListenerUtil.mutListener.listen(64887)) {
            // out weird state bugs in libwebrtc)
            PeerConnectionWrapper.this.handler.post(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    if (!ListenerUtil.mutListener.listen(64886)) {
                        PeerConnectionWrapper.this.readyToAddRemoteCandidates.complete(null);
                    }
                }
            });
        }
    }

    /**
     *  One or more ICE candidates were received. Add them.
     */
    @AnyThread
    private void onIceCandidatesReceived(@NonNull final Candidate[] candidates) {
        int added = 0;
        if (!ListenerUtil.mutListener.listen(64899)) {
            {
                long _loopCounter787 = 0;
                for (Candidate candidate : candidates) {
                    ListenerUtil.loopListener.listen("_loopCounter787", ++_loopCounter787);
                    if (!ListenerUtil.mutListener.listen(64889)) {
                        // Ignore without m-line
                        if (candidate.getSdpMLineIndex() == null) {
                            if (!ListenerUtil.mutListener.listen(64888)) {
                                logger.warn("Received candidate without SdpMLineIndex, ignoring: {}", candidate.getSdp());
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64892)) {
                        // Ignore candidates with empty SDP
                        if ((ListenerUtil.mutListener.listen(64890) ? (candidate.getSdp() == null && candidate.getSdp().trim().equals("")) : (candidate.getSdp() == null || candidate.getSdp().trim().equals("")))) {
                            if (!ListenerUtil.mutListener.listen(64891)) {
                                logger.warn("Received candidate with empty SDP, ignoring");
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64895)) {
                        // Ignore IPv6 (if requested)
                        if ((ListenerUtil.mutListener.listen(64893) ? (!this.allowIpv6 || SdpUtil.isIpv6Candidate(candidate.getSdp())) : (!this.allowIpv6 && SdpUtil.isIpv6Candidate(candidate.getSdp())))) {
                            if (!ListenerUtil.mutListener.listen(64894)) {
                                logger.info("Ignoring IPv6 candidate due to settings: {}", candidate.getSdp());
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64896)) {
                        // Add candidate
                        logger.info("Adding peer ICE candidate: {}", candidate.getSdp());
                    }
                    if (!ListenerUtil.mutListener.listen(64897)) {
                        this.pc.addIceCandidate(new IceCandidate(candidate.getSdpMid(), candidate.getSdpMLineIndex(), candidate.getSdp()));
                    }
                    if (!ListenerUtil.mutListener.listen(64898)) {
                        added++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(64900)) {
            logger.info("Added {} ICE candidate(s) from peer", added);
        }
        if (!ListenerUtil.mutListener.listen(64911)) {
            if ((ListenerUtil.mutListener.listen(64905) ? (added >= candidates.length) : (ListenerUtil.mutListener.listen(64904) ? (added <= candidates.length) : (ListenerUtil.mutListener.listen(64903) ? (added > candidates.length) : (ListenerUtil.mutListener.listen(64902) ? (added != candidates.length) : (ListenerUtil.mutListener.listen(64901) ? (added == candidates.length) : (added < candidates.length))))))) {
                if (!ListenerUtil.mutListener.listen(64910)) {
                    logger.info("Ignored {} remote candidate(s) from peer", (ListenerUtil.mutListener.listen(64909) ? (candidates.length % added) : (ListenerUtil.mutListener.listen(64908) ? (candidates.length / added) : (ListenerUtil.mutListener.listen(64907) ? (candidates.length * added) : (ListenerUtil.mutListener.listen(64906) ? (candidates.length + added) : (candidates.length - added))))));
                }
            }
        }
    }

    /**
     *  Return the wrapped PeerConnection.
     */
    public org.webrtc.PeerConnection getPeerConnection() {
        if (!ListenerUtil.mutListener.listen(64912)) {
            this.ensureNotDisposed();
        }
        return this.pc;
    }

    /**
     *  Return the peer connection state.
     */
    @NonNull
    public synchronized PeerConnectionState getState() {
        return this.state;
    }

    /**
     *  Set the peer connection state and notify listeners.
     */
    @AnyThread
    private synchronized void setState(@NonNull final PeerConnectionState state) {
        final PeerConnectionState current = this.state;
        if (!ListenerUtil.mutListener.listen(64914)) {
            if (this.disposed) {
                if (!ListenerUtil.mutListener.listen(64913)) {
                    logger.warn("PeerConnection is disposed, ignoring state change from {} to {}", current, state);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(64915)) {
            // Update state
            this.state = state;
        }
        if (!ListenerUtil.mutListener.listen(64916)) {
            logger.info("PeerConnectionState changed to {}", state);
        }
        if (!ListenerUtil.mutListener.listen(64917)) {
            // Fire state event
            this.listener.onStateChanged(current, state);
        }
    }

    /**
     *  Close the peer connection and dispose allocated resources.
     *
     *  This results in a terminal state. After calling this method,
     *  the instance MUST not be used anymore.
     */
    public void dispose() {
        if (!ListenerUtil.mutListener.listen(64918)) {
            logger.info("dispose()");
        }
        if (!ListenerUtil.mutListener.listen(64920)) {
            if (this.disposed) {
                if (!ListenerUtil.mutListener.listen(64919)) {
                    logger.warn("Not disposing: Already disposed");
                }
                return;
            }
        }
        synchronized (this) {
            if (!ListenerUtil.mutListener.listen(64921)) {
                // Mark this instance as disposed
                this.disposed = true;
            }
        }
        if (!ListenerUtil.mutListener.listen(64922)) {
            // (The `dispose()` method implicitly calls `close()`)
            logger.trace("Closing peer connection");
        }
        if (!ListenerUtil.mutListener.listen(64923)) {
            pc.close();
        }
        if (!ListenerUtil.mutListener.listen(64924)) {
            logger.trace("Disposing peer connection");
        }
        if (!ListenerUtil.mutListener.listen(64925)) {
            pc.dispose();
        }
        if (!ListenerUtil.mutListener.listen(64926)) {
            logger.trace("Disposed peer connection");
        }
        if (!ListenerUtil.mutListener.listen(64927)) {
            // Dispose the peer connection factory.
            logger.trace("Disposing factory");
        }
        if (!ListenerUtil.mutListener.listen(64928)) {
            factory.dispose();
        }
        if (!ListenerUtil.mutListener.listen(64929)) {
            logger.trace("Disposed factory");
        }
        if (!ListenerUtil.mutListener.listen(64930)) {
            logger.info("All native resources disposed");
        }
        synchronized (this) {
            if (!ListenerUtil.mutListener.listen(64931)) {
                // Set state to CLOSED
                this.state = PeerConnectionState.CLOSED;
            }
            if (!ListenerUtil.mutListener.listen(64932)) {
                // Fire state event
                this.listener.onStateChanged(this.state, PeerConnectionState.CLOSED);
            }
        }
    }

    private class PeerConnectionObserver implements org.webrtc.PeerConnection.Observer {

        @Override
        @AnyThread
        public void onSignalingChange(@NonNull final org.webrtc.PeerConnection.SignalingState state) {
            if (!ListenerUtil.mutListener.listen(64933)) {
                logger.info("Signaling state change to {}", state.name());
            }
        }

        @Override
        @AnyThread
        public void onIceConnectionChange(@NonNull final IceConnectionState state) {
            if (!ListenerUtil.mutListener.listen(64934)) {
                logger.info("ICE connection state change to {}", state.name());
            }
            if (!ListenerUtil.mutListener.listen(64942)) {
                switch(state) {
                    case NEW:
                        if (!ListenerUtil.mutListener.listen(64935)) {
                            PeerConnectionWrapper.this.setState(PeerConnectionState.NEW);
                        }
                        break;
                    case CHECKING:
                    case DISCONNECTED:
                        if (!ListenerUtil.mutListener.listen(64936)) {
                            PeerConnectionWrapper.this.setState(PeerConnectionState.CONNECTING);
                        }
                        break;
                    case CONNECTED:
                    case COMPLETED:
                        if (!ListenerUtil.mutListener.listen(64937)) {
                            PeerConnectionWrapper.this.setState(PeerConnectionState.CONNECTED);
                        }
                        break;
                    case FAILED:
                        if (!ListenerUtil.mutListener.listen(64938)) {
                            PeerConnectionWrapper.this.setState(PeerConnectionState.FAILED);
                        }
                        if (!ListenerUtil.mutListener.listen(64939)) {
                            PeerConnectionWrapper.this.logStatus();
                        }
                        break;
                    case CLOSED:
                        if (!ListenerUtil.mutListener.listen(64940)) {
                            PeerConnectionWrapper.this.setState(PeerConnectionState.CLOSED);
                        }
                        break;
                    default:
                        if (!ListenerUtil.mutListener.listen(64941)) {
                            logger.error("Unknown ICE connection state: {}", state);
                        }
                }
            }
        }

        @Override
        @AnyThread
        public void onIceConnectionReceivingChange(final boolean noIdeaWhatThisIs) {
        }

        @Override
        @AnyThread
        public void onIceGatheringChange(@NonNull final IceGatheringState state) {
            if (!ListenerUtil.mutListener.listen(64943)) {
                logger.info("ICE gathering state change to {}", state.name());
            }
        }

        /**
         *  A new ICE candidate was generated. Send it to the peer.
         */
        @Override
        @AnyThread
        public void onIceCandidate(@NonNull final IceCandidate candidate) {
            if (!ListenerUtil.mutListener.listen(64944)) {
                logger.info("New local ICE candidate: {}", candidate.sdp);
            }
            if (!ListenerUtil.mutListener.listen(64946)) {
                // Check if loopback
                if (SdpUtil.isLoopbackCandidate(candidate.sdp)) {
                    if (!ListenerUtil.mutListener.listen(64945)) {
                        logger.info("Ignored local loopback candidate");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(64949)) {
                // Check if IPv6
                if ((ListenerUtil.mutListener.listen(64947) ? (!allowIpv6 || SdpUtil.isIpv6Candidate(candidate.sdp)) : (!allowIpv6 && SdpUtil.isIpv6Candidate(candidate.sdp)))) {
                    if (!ListenerUtil.mutListener.listen(64948)) {
                        logger.info("Ignored local IPv6 candidate (disabled via preferences)");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(64953)) {
                // Send candidate when ready
                PeerConnectionWrapper.this.readyToSendLocalCandidates.thenRunAsync(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(64950)) {
                            logger.debug("Sending ICE candidate");
                        }
                        try {
                            final Candidate[] candidates = new Candidate[] { new Candidate(candidate.sdp, candidate.sdpMid, candidate.sdpMLineIndex) };
                            if (!ListenerUtil.mutListener.listen(64952)) {
                                PeerConnectionWrapper.this.task.sendCandidates(candidates);
                            }
                        } catch (ConnectionException error) {
                            if (!ListenerUtil.mutListener.listen(64951)) {
                                logger.error("Could not send ICE candidate", error);
                            }
                        }
                    }
                }, PeerConnectionWrapper.this.handler.getExecutor());
            }
        }

        @Override
        @AnyThread
        public void onIceCandidatesRemoved(@NonNull final IceCandidate[] iceCandidates) {
            if (!ListenerUtil.mutListener.listen(64955)) {
                // Legacy nonsense
                if (logger.isInfoEnabled()) {
                    if (!ListenerUtil.mutListener.listen(64954)) {
                        logger.info("Ignoring removed candidates: {}", Arrays.toString(iceCandidates));
                    }
                }
            }
        }

        @Override
        @AnyThread
        public void onRenegotiationNeeded() {
            if (!ListenerUtil.mutListener.listen(64956)) {
                logger.info("Negotiation needed");
            }
            if (!ListenerUtil.mutListener.listen(64957)) {
                PeerConnectionWrapper.this.setState(PeerConnectionState.CONNECTING);
            }
            if (!ListenerUtil.mutListener.listen(64959)) {
                // out weird state bugs in libwebrtc)
                PeerConnectionWrapper.this.handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(64958)) {
                            PeerConnectionWrapper.this.readyToSetRemoteDescription.complete(null);
                        }
                    }
                });
            }
        }

        @Override
        @AnyThread
        public void onAddTrack(@NonNull final RtpReceiver rtpReceiver, @NonNull final MediaStream[] mediaStreams) {
            if (!ListenerUtil.mutListener.listen(64960)) {
                logger.error("onAddTrack (in web client)");
            }
        }

        @Override
        @AnyThread
        public void onAddStream(@NonNull final MediaStream mediaStream) {
            if (!ListenerUtil.mutListener.listen(64961)) {
                logger.error("onAddStream (in web client)");
            }
        }

        @Override
        @AnyThread
        public void onRemoveStream(@NonNull final MediaStream mediaStream) {
            if (!ListenerUtil.mutListener.listen(64962)) {
                logger.error("onRemoveStream (in web client)");
            }
        }

        @Override
        @AnyThread
        public void onDataChannel(@NonNull final DataChannel dc) {
            final String label = dc.label();
            if (!ListenerUtil.mutListener.listen(64963)) {
                logger.info("New data channel: {}", label);
            }
            if (!ListenerUtil.mutListener.listen(64965)) {
                if (!THREEMA_DC_LABEL.equals(label)) {
                    if (!ListenerUtil.mutListener.listen(64964)) {
                        logger.warn("Ignoring new data channel (wrong label).");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(64966)) {
                // Fire data channel event
                PeerConnectionWrapper.this.listener.onDataChannel(dc);
            }
        }
    }

    @AnyThread
    public long getMaxMessageSize() {
        // well-known (and, frankly, terribly small) value.
        return (ListenerUtil.mutListener.listen(64970) ? (64 % 1024) : (ListenerUtil.mutListener.listen(64969) ? (64 / 1024) : (ListenerUtil.mutListener.listen(64968) ? (64 - 1024) : (ListenerUtil.mutListener.listen(64967) ? (64 + 1024) : (64 * 1024)))));
    }

    /**
     *  Log connection status info to the Android log.
     */
    @AnyThread
    private synchronized void logStatus() {
        if (!ListenerUtil.mutListener.listen(64971)) {
            logger.debug("*** CONNECTION STATUS");
        }
        if (!ListenerUtil.mutListener.listen(64972)) {
            logger.debug("Aggregated state: {}", this.state);
        }
        if (!ListenerUtil.mutListener.listen(64973)) {
            logger.debug("ICE connection state: {}", this.pc.iceConnectionState());
        }
        if (!ListenerUtil.mutListener.listen(64974)) {
            logger.debug("ICE gathering state: {}", this.pc.iceGatheringState());
        }
        if (!ListenerUtil.mutListener.listen(64975)) {
            logger.debug("Signaling state: {}", this.pc.signalingState());
        }
        if (!ListenerUtil.mutListener.listen(64976)) {
            logger.debug("*** END CONNECTION STATUS");
        }
    }
}
