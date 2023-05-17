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
package ch.threema.app.webclient.services.instance.state;

import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.value.Value;
import org.saltyrtc.chunkedDc.Unchunker;
import org.saltyrtc.client.SaltyRTC;
import org.saltyrtc.client.SaltyRTCBuilder;
import org.saltyrtc.client.events.ApplicationDataEvent;
import org.saltyrtc.client.events.EventHandler;
import org.saltyrtc.client.events.SignalingStateChangedEvent;
import org.saltyrtc.client.exceptions.ConnectionException;
import org.saltyrtc.client.exceptions.InvalidKeyException;
import org.saltyrtc.client.signaling.CloseCode;
import org.saltyrtc.client.signaling.state.SignalingState;
import org.saltyrtc.client.tasks.Task;
import org.saltyrtc.tasks.webrtc.WebRTCTask;
import org.saltyrtc.tasks.webrtc.WebRTCTaskBuilder;
import org.saltyrtc.tasks.webrtc.WebRTCTaskVersion;
import org.saltyrtc.tasks.webrtc.exceptions.UntiedException;
import org.saltyrtc.tasks.webrtc.transport.SignalingTransportHandler;
import org.saltyrtc.tasks.webrtc.transport.SignalingTransportLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.DataChannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.SSLSocketFactory;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import ch.threema.app.BuildConfig;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.SendMode;
import ch.threema.app.webclient.converter.ConnectionDisconnect;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.listeners.PeerConnectionListener;
import ch.threema.app.webclient.listeners.WebClientMessageListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.state.PeerConnectionState;
import ch.threema.app.webclient.webrtc.DataChannelContext;
import ch.threema.app.webclient.webrtc.PeerConnectionWrapper;
import ch.threema.app.webclient.webrtc.TemporaryDataChannelObserver;
import ch.threema.app.webclient.webrtc.TemporaryTaskEventHandler;
import ch.threema.app.webrtc.DataChannelObserver;
import ch.threema.app.webrtc.UnboundedFlowControlledDataChannel;
import ch.threema.logging.ThreemaLogger;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Context passed around by the session state classes that have an active connection open.
 *
 * This contains everything that needs to be initialized on connect and cleaned up on disconnect.
 *
 * It also holds the authoritative SessionState instance.
 */
@WorkerThread
class SessionConnectionContext {

    // WebSocket connect timeout
    static final int WS_CONNECT_TIMEOUT_MS = 5000;

    // WebSocket ping interval
    static final int WS_PING_INTERVAL_S = 60;

    // SaltyRTC client-to-client connection timeout
    static final int C2C_CONNECT_TIMEOUT_MS = 42000;

    // Logger
    private final Logger logger = LoggerFactory.getLogger(SessionConnectionContext.class);

    // Session context
    @NonNull
    final SessionContext ctx;

    // SaltyRTC
    @NonNull
    final SaltyRTC salty;

    @Nullable
    DataChannel sdc = null;

    // WebRTC
    @Nullable
    PeerConnectionWrapper pc = null;

    @Nullable
    DataChannelContext dcc = null;

    // If set to true, ignore all further events!
    @NonNull
    AtomicBoolean closed = new AtomicBoolean(false);

    SessionConnectionContext(@NonNull final SessionContext ctx, @NonNull final SaltyRTCBuilder builder) throws NoSuchAlgorithmException, InvalidKeyException {
        // Create SSL socket factory
        final SSLSocketFactory sslSocketFactory = ConfigUtils.getSSLSocketFactory(ctx.model.getSaltyRtcHost());
        // Create SaltyRTC tasks
        final Task[] tasks = new Task[] { new WebRTCTaskBuilder().withVersion(WebRTCTaskVersion.V1).withHandover(true).build(), new WebRTCTaskBuilder().withVersion(WebRTCTaskVersion.V0).withHandover(true).build() };
        if (!ListenerUtil.mutListener.listen(63895)) {
            // Set connection information
            builder.connectTo(ctx.model.getSaltyRtcHost(), ctx.model.getSaltyRtcPort(), sslSocketFactory);
        }
        if (!ListenerUtil.mutListener.listen(63897)) {
            // Set a server key if available
            if (ctx.model.getServerKey() != null) {
                if (!ListenerUtil.mutListener.listen(63896)) {
                    builder.withServerKey(ctx.model.getServerKey());
                }
            }
        }
        // Create SaltyRTC instance
        SaltyRTC salty = builder.usingTasks(tasks).withPingInterval(WS_PING_INTERVAL_S).withWebsocketConnectTimeout(WS_CONNECT_TIMEOUT_MS).asResponder();
        if (!ListenerUtil.mutListener.listen(63898)) {
            // Enable debugging in a debug build
            salty.setDebug(BuildConfig.DEBUG);
        }
        // Store instances
        this.ctx = ctx;
        this.salty = salty;
        if (!ListenerUtil.mutListener.listen(63900)) {
            // Set logger prefix
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(63899)) {
                    ((ThreemaLogger) logger).setPrefix(ctx.sessionId + "." + ctx.affiliationId);
                }
            }
        }
        // Create temporary task event handler
        final TemporaryTaskEventHandler temporaryTaskEventHandler = new TemporaryTaskEventHandler();
        if (!ListenerUtil.mutListener.listen(63916)) {
            // Handle signaling state
            this.salty.events.signalingStateChanged.register(new EventHandler<SignalingStateChangedEvent>() {

                @Override
                @AnyThread
                public boolean handle(SignalingStateChangedEvent event) {
                    if (!ListenerUtil.mutListener.listen(63901)) {
                        // Unregister event handler when already closed
                        if (SessionConnectionContext.this.closed.get()) {
                            return true;
                        }
                    }
                    final SignalingState state = event.getState();
                    if (!ListenerUtil.mutListener.listen(63903)) {
                        // Register the temporary task event handler, so no events are being lost
                        if (state == SignalingState.TASK) {
                            final WebRTCTask task = (WebRTCTask) SessionConnectionContext.this.salty.getTask();
                            if (!ListenerUtil.mutListener.listen(63902)) {
                                task.setMessageHandler(temporaryTaskEventHandler);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(63915)) {
                        // Dispatch event to worker thread
                        SessionConnectionContext.this.ctx.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(63904)) {
                                    logger.info("Signaling state changed to {}", state.name());
                                }
                                if (!ListenerUtil.mutListener.listen(63914)) {
                                    switch(state) {
                                        case NEW:
                                            break;
                                        case WS_CONNECTING:
                                        case SERVER_HANDSHAKE:
                                        case PEER_HANDSHAKE:
                                            if (!ListenerUtil.mutListener.listen(63906)) {
                                                if (!(SessionConnectionContext.this.ctx.manager.getInternalState() instanceof SessionStateConnecting)) {
                                                    if (!ListenerUtil.mutListener.listen(63905)) {
                                                        SessionConnectionContext.this.ctx.manager.setError("Signaling state changed to " + state.name() + " in session state " + SessionConnectionContext.this.ctx.manager.getInternalState().state.name());
                                                    }
                                                }
                                            }
                                            break;
                                        case TASK:
                                            // Create WebRTC peer connection
                                            try {
                                                if (!ListenerUtil.mutListener.listen(63909)) {
                                                    SessionConnectionContext.this.createPeerConnection(temporaryTaskEventHandler);
                                                }
                                            } catch (Exception error) {
                                                if (!ListenerUtil.mutListener.listen(63907)) {
                                                    logger.error(error.toString());
                                                }
                                                if (!ListenerUtil.mutListener.listen(63908)) {
                                                    SessionConnectionContext.this.ctx.manager.setError(error.toString());
                                                }
                                            }
                                            break;
                                        case CLOSING:
                                            if (!ListenerUtil.mutListener.listen(63910)) {
                                                SessionConnectionContext.this.salty.disconnect();
                                            }
                                            if (!ListenerUtil.mutListener.listen(63911)) {
                                                SessionConnectionContext.this.ctx.manager.setDisconnected(DisconnectContext.unknown());
                                            }
                                            break;
                                        case CLOSED:
                                            if (!ListenerUtil.mutListener.listen(63912)) {
                                                SessionConnectionContext.this.ctx.manager.setDisconnected(DisconnectContext.unknown());
                                            }
                                            break;
                                        case ERROR:
                                            if (!ListenerUtil.mutListener.listen(63913)) {
                                                SessionConnectionContext.this.ctx.manager.setError("Signaling state changed to ERROR");
                                            }
                                            break;
                                    }
                                }
                            }
                        });
                    }
                    // Don't unregister event handler
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(63919)) {
            // Handle application data
            salty.events.applicationData.register(new EventHandler<ApplicationDataEvent>() {

                @Override
                @AnyThread
                public boolean handle(ApplicationDataEvent event) {
                    if (!ListenerUtil.mutListener.listen(63917)) {
                        // Unregister event handler when already closed
                        if (SessionConnectionContext.this.closed.get()) {
                            return true;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(63918)) {
                        // Note: No dispatching required as this is only being logged
                        logger.error("Unexpected incoming application message");
                    }
                    // Don't unregister event handler
                    return false;
                }
            });
        }
    }

    /**
     *  Create a WebRTC peer-to-peer connection.
     */
    private void createPeerConnection(TemporaryTaskEventHandler temporaryTaskEventHandler) throws Exception {
        if (!ListenerUtil.mutListener.listen(63920)) {
            // we must be in the connecting state at this point.
            if (!(this.ctx.manager.getInternalState() instanceof SessionStateConnecting)) {
                throw new IllegalStateException("Expected 'connecting' state");
            }
        }
        if (!ListenerUtil.mutListener.listen(63921)) {
            // Sanity-check: Ensure a WebRTC task has been negotiated
            if (!(this.salty.getTask() instanceof WebRTCTask)) {
                throw new ConnectionException("Expected a WebRTC task to be negotiated");
            }
        }
        final WebRTCTask task = (WebRTCTask) this.salty.getTask();
        if (!ListenerUtil.mutListener.listen(63922)) {
            // Make sure that we're starting from a clean state
            if (this.pc != null) {
                throw new IllegalStateException("Peer connection wrapper is not null");
            }
        }
        if (!ListenerUtil.mutListener.listen(63923)) {
            if (this.dcc != null) {
                throw new IllegalStateException("Data channel is not null");
            }
        }
        // Create WebRTC peer connection
        final String logPrefix = this.ctx.sessionId + "." + this.ctx.affiliationId;
        if (!ListenerUtil.mutListener.listen(63936)) {
            this.pc = new PeerConnectionWrapper(logPrefix, this.ctx.services.appContext, this.ctx.handler, task, temporaryTaskEventHandler, this.ctx.allowIpv6(), new PeerConnectionListener() {

                @Override
                @AnyThread
                public synchronized void onStateChanged(PeerConnectionState oldState, PeerConnectionState newState) {
                    if (!ListenerUtil.mutListener.listen(63928)) {
                        // Dispatch event to worker thread
                        SessionConnectionContext.this.ctx.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(63924)) {
                                    // Ignore events when already closed
                                    if (SessionConnectionContext.this.closed.get()) {
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(63925)) {
                                    logger.info("Peer connection state changed from {} to {} and signaling state = {}", oldState, newState, salty.getSignalingState());
                                }
                                if (!ListenerUtil.mutListener.listen(63927)) {
                                    switch(newState) {
                                        case CONNECTING:
                                        case CONNECTED:
                                            break;
                                        case FAILED:
                                        case CLOSED:
                                            if (!ListenerUtil.mutListener.listen(63926)) {
                                                SessionConnectionContext.this.ctx.manager.setDisconnected(DisconnectContext.unknown());
                                            }
                                            break;
                                    }
                                }
                            }
                        });
                    }
                }

                @Override
                @AnyThread
                public void onDataChannel(@NonNull final DataChannel dc) {
                    // Register the temporary data channel observer, so no events are being lost
                    final TemporaryDataChannelObserver temporaryDataChannelObserver = new TemporaryDataChannelObserver();
                    if (!ListenerUtil.mutListener.listen(63929)) {
                        temporaryDataChannelObserver.register(dc);
                    }
                    if (!ListenerUtil.mutListener.listen(63935)) {
                        // Dispatch event to worker thread
                        SessionConnectionContext.this.ctx.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(63930)) {
                                    // Ignore events when already closed
                                    if (SessionConnectionContext.this.closed.get()) {
                                        return;
                                    }
                                }
                                // Ensure the channel is connecting or open
                                final DataChannel.State state = dc.state();
                                if (!ListenerUtil.mutListener.listen(63933)) {
                                    if ((ListenerUtil.mutListener.listen(63931) ? (state != DataChannel.State.CONNECTING || state != DataChannel.State.OPEN) : (state != DataChannel.State.CONNECTING && state != DataChannel.State.OPEN))) {
                                        final String label = dc.label();
                                        if (!ListenerUtil.mutListener.listen(63932)) {
                                            logger.error("Received data channel {} is in the state {}", label, state);
                                        }
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(63934)) {
                                    // Bind or discard data channel
                                    SessionConnectionContext.this.handleDataChannel(dc, temporaryDataChannelObserver);
                                }
                            }
                        });
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(63937)) {
            // Attempt to hand over the signalling data channel
            this.createSignalingChannelForHandover(task, logPrefix);
        }
    }

    /**
     *  Create a signalling data channel and attempt to hand the signalling
     *  channel over to it.
     */
    private void createSignalingChannelForHandover(@NonNull final WebRTCTask task, @NonNull final String logPrefix) {
        if (!ListenerUtil.mutListener.listen(63939)) {
            // Ensure this is only called once per connection
            if (this.sdc != null) {
                if (!ListenerUtil.mutListener.listen(63938)) {
                    logger.error("Attempted to create another signalling data channel");
                }
                return;
            }
        }
        // Create signalling data channel
        final SignalingTransportLink link = task.getTransportLink();
        final DataChannel.Init parameters = new DataChannel.Init();
        if (!ListenerUtil.mutListener.listen(63940)) {
            parameters.id = link.getId();
        }
        if (!ListenerUtil.mutListener.listen(63941)) {
            parameters.negotiated = true;
        }
        if (!ListenerUtil.mutListener.listen(63942)) {
            parameters.ordered = true;
        }
        if (!ListenerUtil.mutListener.listen(63943)) {
            parameters.protocol = link.getProtocol();
        }
        if (!ListenerUtil.mutListener.listen(63944)) {
            this.sdc = Objects.requireNonNull(this.pc).getPeerConnection().createDataChannel(link.getLabel(), parameters);
        }
        if (!ListenerUtil.mutListener.listen(63945)) {
            Objects.requireNonNull(this.sdc);
        }
        // Wrap as unbounded, flow-controlled data channel
        final UnboundedFlowControlledDataChannel ufcdc = new UnboundedFlowControlledDataChannel(logPrefix, this.sdc);
        // Create signalling data channel logger
        final Logger sdcLogger = LoggerFactory.getLogger("SignalingDataChannel");
        if (!ListenerUtil.mutListener.listen(63947)) {
            if (sdcLogger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(63946)) {
                    ((ThreemaLogger) sdcLogger).setPrefix(logPrefix + "." + this.sdc.label() + "/" + this.sdc.id());
                }
            }
        }
        // Create transport handler
        final SignalingTransportHandler handler = new SignalingTransportHandler() {

            @Override
            @AnyThread
            public long getMaxMessageSize() {
                return SessionConnectionContext.this.pc.getMaxMessageSize();
            }

            @Override
            @AnyThread
            public void close() {
                if (!ListenerUtil.mutListener.listen(63948)) {
                    // Ignore events when already closed
                    if (SessionConnectionContext.this.closed.get()) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(63950)) {
                    // Sanity-check
                    if (SessionConnectionContext.this.sdc == null) {
                        if (!ListenerUtil.mutListener.listen(63949)) {
                            logger.error("SignalingTransportHandler.close event but data channel has already been disposed!");
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(63951)) {
                    // Close data channel
                    sdcLogger.info("Data channel {} close request", SessionConnectionContext.this.sdc.label());
                }
                if (!ListenerUtil.mutListener.listen(63952)) {
                    SessionConnectionContext.this.sdc.close();
                }
            }

            @Override
            @AnyThread
            public void send(@NonNull final ByteBuffer message) {
                if (!ListenerUtil.mutListener.listen(63953)) {
                    // Ignore events when already closed
                    if (SessionConnectionContext.this.closed.get()) {
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(63955)) {
                    // Sanity-check
                    if (SessionConnectionContext.this.sdc == null) {
                        if (!ListenerUtil.mutListener.listen(63954)) {
                            logger.error("SignalingTransportHandler.send event but data channel has already been disposed!");
                        }
                        return;
                    }
                }
                // Copy the message since the ByteBuffer will be reused immediately
                final ByteBuffer copy = ByteBuffer.allocate(message.remaining());
                if (!ListenerUtil.mutListener.listen(63956)) {
                    copy.put(message);
                }
                if (!ListenerUtil.mutListener.listen(63957)) {
                    copy.flip();
                }
                if (!ListenerUtil.mutListener.listen(63958)) {
                    // Send message via data channel
                    sdcLogger.debug("Data channel {} outgoing signaling message of length {}", SessionConnectionContext.this.sdc.label(), copy.remaining());
                }
                if (!ListenerUtil.mutListener.listen(63959)) {
                    ufcdc.write(new DataChannel.Buffer(copy, true));
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(63986)) {
            // Bind events
            DataChannelObserver.register(this.sdc, new DataChannelObserver() {

                @Override
                @AnyThread
                public void onBufferedAmountChange(final long bufferedAmount) {
                    if (!ListenerUtil.mutListener.listen(63961)) {
                        // Sanity-check
                        if (SessionConnectionContext.this.sdc == null) {
                            if (!ListenerUtil.mutListener.listen(63960)) {
                                logger.error("SignalingTransportHandler.onBufferedAmountChange event but data channel has already been disposed!");
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(63962)) {
                        // Important: ALWAYS dispatch this event to another thread because webrtc.org!
                        RuntimeUtil.runInAsyncTask(ufcdc::bufferedAmountChange);
                    }
                }

                @Override
                @AnyThread
                public synchronized void onStateChange(@NonNull DataChannel.State state) {
                    if (!ListenerUtil.mutListener.listen(63977)) {
                        // Dispatch event to worker thread
                        SessionConnectionContext.this.ctx.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(63963)) {
                                    // Ignore events when already closed
                                    if (SessionConnectionContext.this.closed.get()) {
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(63965)) {
                                    // Sanity-check
                                    if (SessionConnectionContext.this.sdc == null) {
                                        if (!ListenerUtil.mutListener.listen(63964)) {
                                            logger.error("SignalingTransportHandler.onStateChange event but data channel has already been disposed!");
                                        }
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(63976)) {
                                    // Handle state change
                                    switch(state) {
                                        case CONNECTING:
                                            if (!ListenerUtil.mutListener.listen(63966)) {
                                                sdcLogger.debug("Connecting");
                                            }
                                            break;
                                        case OPEN:
                                            if (!ListenerUtil.mutListener.listen(63967)) {
                                                sdcLogger.info("Open");
                                            }
                                            if (!ListenerUtil.mutListener.listen(63968)) {
                                                task.handover(handler);
                                            }
                                            break;
                                        case CLOSING:
                                            if (!ListenerUtil.mutListener.listen(63969)) {
                                                sdcLogger.debug("Closing");
                                            }
                                            try {
                                                if (!ListenerUtil.mutListener.listen(63971)) {
                                                    link.closing();
                                                }
                                            } catch (UntiedException e) {
                                                if (!ListenerUtil.mutListener.listen(63970)) {
                                                    sdcLogger.warn("Could not move into closing state", e);
                                                }
                                            }
                                            break;
                                        case CLOSED:
                                            if (!ListenerUtil.mutListener.listen(63972)) {
                                                sdcLogger.info("Closed");
                                            }
                                            try {
                                                if (!ListenerUtil.mutListener.listen(63973)) {
                                                    link.closed();
                                                }
                                            } catch (UntiedException e) {
                                            }
                                            // Note: The data channel MUST NOT be used after this point!
                                            final DataChannel dc = SessionConnectionContext.this.sdc;
                                            if (!ListenerUtil.mutListener.listen(63974)) {
                                                SessionConnectionContext.this.sdc = null;
                                            }
                                            if (!ListenerUtil.mutListener.listen(63975)) {
                                                RuntimeUtil.runInAsyncTask(dc::dispose);
                                            }
                                            break;
                                    }
                                }
                            }
                        });
                    }
                }

                @Override
                @AnyThread
                public synchronized void onMessage(@NonNull final DataChannel.Buffer buffer) {
                    // Copy the message since the ByteBuffer will be reused immediately
                    final boolean isBinary = buffer.binary;
                    final ByteBuffer copy = ByteBuffer.allocate(buffer.data.remaining());
                    if (!ListenerUtil.mutListener.listen(63978)) {
                        copy.put(buffer.data);
                    }
                    if (!ListenerUtil.mutListener.listen(63979)) {
                        copy.flip();
                    }
                    if (!ListenerUtil.mutListener.listen(63985)) {
                        // Dispatch event to worker thread
                        SessionConnectionContext.this.ctx.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(63984)) {
                                    if (!isBinary) {
                                        if (!ListenerUtil.mutListener.listen(63982)) {
                                            sdcLogger.error("Received non-binary message");
                                        }
                                        if (!ListenerUtil.mutListener.listen(63983)) {
                                            task.close(CloseCode.PROTOCOL_ERROR);
                                        }
                                    } else {
                                        try {
                                            if (!ListenerUtil.mutListener.listen(63981)) {
                                                link.receive(copy);
                                            }
                                        } catch (UntiedException error) {
                                            if (!ListenerUtil.mutListener.listen(63980)) {
                                                sdcLogger.warn("Could not feed incoming data to the transport link", error);
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     *  Handle an incoming data channel as the ARP channel.
     */
    private void handleDataChannel(@NonNull final DataChannel dc, @NonNull final TemporaryDataChannelObserver temporaryDataChannelObserver) {
        if (!ListenerUtil.mutListener.listen(63988)) {
            // that is being created by us (negotiated).
            if (this.dcc != null) {
                if (!ListenerUtil.mutListener.listen(63987)) {
                    this.ctx.manager.setError("A DataChannel instance is already registered");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63990)) {
            // Sanity-check: Ensure there is a peer connection
            if (this.pc == null) {
                if (!ListenerUtil.mutListener.listen(63989)) {
                    this.ctx.manager.setError("PeerConnection instance is null");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63992)) {
            // Sanity-check: Ensure a WebRTC task has been negotiated
            if (!(this.salty.getTask() instanceof WebRTCTask)) {
                if (!ListenerUtil.mutListener.listen(63991)) {
                    this.ctx.manager.setError("Expected a WebRTC task to be negotiated");
                }
                return;
            }
        }
        final WebRTCTask task = (WebRTCTask) this.salty.getTask();
        if (!ListenerUtil.mutListener.listen(64004)) {
            // Create data channel context
            this.dcc = new DataChannelContext(this.ctx.sessionId + "." + this.ctx.affiliationId, dc, task, this.pc.getMaxMessageSize(), new Unchunker.MessageListener() {

                /**
                 *  Handle a reassembled message.
                 *
                 *  Note: Since we call .receive on the worker thread, the message event
                 *        will also fire on the worker thread.
                 */
                @Override
                @WorkerThread
                public void onMessage(@NonNull final ByteBuffer message) {
                    if (!ListenerUtil.mutListener.listen(63993)) {
                        // Ignore events when already closed
                        if (SessionConnectionContext.this.closed.get()) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(63995)) {
                        // Sanity-check
                        if (SessionConnectionContext.this.dcc == null) {
                            if (!ListenerUtil.mutListener.listen(63994)) {
                                logger.error("onMessage (full message) event but data channel has already been disposed!");
                            }
                            return;
                        }
                    }
                    // Decode msgpack bytes
                    final Value val;
                    try (MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(message)) {
                        val = unpacker.unpackValue();
                    } catch (IOException e) {
                        if (!ListenerUtil.mutListener.listen(63996)) {
                            ctx.manager.setError("IOException while decoding incoming data channel message");
                        }
                        return;
                    } catch (OutOfMemoryError e) {
                        if (!ListenerUtil.mutListener.listen(63997)) {
                            SessionConnectionContext.this.sendBestEffortConnectionDisconnect(DisconnectContext.REASON_OUT_OF_MEMORY);
                        }
                        if (!ListenerUtil.mutListener.listen(63998)) {
                            ctx.manager.setError("Out of memory while decoding incoming data channel message");
                        }
                        return;
                    }
                    if (!ListenerUtil.mutListener.listen(64003)) {
                        if (val.isMapValue()) {
                            if (!ListenerUtil.mutListener.listen(64002)) {
                                // Notify listeners about new message
                                WebClientListenerManager.messageListener.handle(new ListenerManager.HandleListener<WebClientMessageListener>() {

                                    @Override
                                    @WorkerThread
                                    public void handle(WebClientMessageListener listener) {
                                        if (!ListenerUtil.mutListener.listen(64001)) {
                                            if (listener.handle(ctx.model)) {
                                                if (!ListenerUtil.mutListener.listen(64000)) {
                                                    listener.onMessage(val.asMapValue());
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(63999)) {
                                logger.warn("Received invalid msgpack packet, not a MapValue");
                            }
                        }
                    }
                }
            });
        }
        // Get label
        final String label = dc.label();
        if (!ListenerUtil.mutListener.listen(64036)) {
            // Bind events
            temporaryDataChannelObserver.replace(dc, new DataChannelObserver() {

                @Override
                @AnyThread
                public void onBufferedAmountChange(final long bufferedAmount) {
                    if (!ListenerUtil.mutListener.listen(64006)) {
                        // Sanity-check
                        if (SessionConnectionContext.this.dcc == null) {
                            if (!ListenerUtil.mutListener.listen(64005)) {
                                logger.error("onBufferedAmountChange event but data channel has already been disposed!");
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(64007)) {
                        // Important: ALWAYS dispatch this event to another thread because webrtc.org!
                        RuntimeUtil.runInAsyncTask(SessionConnectionContext.this.dcc.fcdc::bufferedAmountChange);
                    }
                }

                @Override
                @AnyThread
                public synchronized void onStateChange(@NonNull final DataChannel.State state) {
                    if (!ListenerUtil.mutListener.listen(64021)) {
                        // Dispatch event to worker thread
                        SessionConnectionContext.this.ctx.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(64008)) {
                                    // Ignore events when already closed
                                    if (SessionConnectionContext.this.closed.get()) {
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(64010)) {
                                    // Sanity-check
                                    if (SessionConnectionContext.this.dcc == null) {
                                        if (!ListenerUtil.mutListener.listen(64009)) {
                                            logger.error("onStateChange event but data channel has already been disposed!");
                                        }
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(64020)) {
                                    // Handle state change
                                    switch(state) {
                                        case CONNECTING:
                                            if (!ListenerUtil.mutListener.listen(64011)) {
                                                logger.debug("Data channel {} connecting", label);
                                            }
                                            break;
                                        case OPEN:
                                            if (!ListenerUtil.mutListener.listen(64012)) {
                                                logger.info("Data channel {} open", label);
                                            }
                                            if (!ListenerUtil.mutListener.listen(64013)) {
                                                // Ready to exchange data
                                                ctx.manager.setConnected();
                                            }
                                            break;
                                        case CLOSING:
                                            if (!ListenerUtil.mutListener.listen(64014)) {
                                                logger.debug("Data channel {} closing", label);
                                            }
                                            if (!ListenerUtil.mutListener.listen(64015)) {
                                                // Cannot exchange any further data
                                                SessionConnectionContext.this.salty.disconnect();
                                            }
                                            if (!ListenerUtil.mutListener.listen(64016)) {
                                                ctx.manager.setDisconnected(DisconnectContext.unknown());
                                            }
                                            break;
                                        case CLOSED:
                                            if (!ListenerUtil.mutListener.listen(64017)) {
                                                logger.info("Data channel {} closed", label);
                                            }
                                            if (!ListenerUtil.mutListener.listen(64018)) {
                                                // Note: The data channel MUST NOT be used after this point!
                                                SessionConnectionContext.this.dcc = null;
                                            }
                                            if (!ListenerUtil.mutListener.listen(64019)) {
                                                RuntimeUtil.runInAsyncTask(dc::dispose);
                                            }
                                            break;
                                    }
                                }
                            }
                        });
                    }
                }

                @Override
                @AnyThread
                public synchronized void onMessage(@NonNull final DataChannel.Buffer buffer) {
                    // Copy the message since the ByteBuffer will be reused immediately
                    final boolean isBinary = buffer.binary;
                    final ByteBuffer copy = ByteBuffer.allocate(buffer.data.remaining());
                    if (!ListenerUtil.mutListener.listen(64022)) {
                        copy.put(buffer.data);
                    }
                    if (!ListenerUtil.mutListener.listen(64023)) {
                        copy.flip();
                    }
                    if (!ListenerUtil.mutListener.listen(64035)) {
                        // Dispatch to handler thread
                        SessionConnectionContext.this.ctx.handler.post(new Runnable() {

                            @Override
                            @WorkerThread
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(64024)) {
                                    // Ignore events when already closed
                                    if (SessionConnectionContext.this.closed.get()) {
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(64026)) {
                                    // Sanity-check
                                    if (SessionConnectionContext.this.dcc == null) {
                                        if (!ListenerUtil.mutListener.listen(64025)) {
                                            logger.error("onMessage (chunk) event but data channel has already been disposed!");
                                        }
                                        return;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(64029)) {
                                    // Ensure binary
                                    if (!isBinary) {
                                        if (!ListenerUtil.mutListener.listen(64027)) {
                                            ctx.manager.setError("Error: Received non-binary message through signaling data channel.");
                                        }
                                        if (!ListenerUtil.mutListener.listen(64028)) {
                                            dc.close();
                                        }
                                        return;
                                    }
                                }
                                try {
                                    if (!ListenerUtil.mutListener.listen(64034)) {
                                        // Reassemble chunks to message
                                        SessionConnectionContext.this.dcc.receive(copy);
                                    }
                                } catch (OutOfMemoryError error) {
                                    if (!ListenerUtil.mutListener.listen(64030)) {
                                        SessionConnectionContext.this.sendBestEffortConnectionDisconnect(DisconnectContext.REASON_OUT_OF_MEMORY);
                                    }
                                    if (!ListenerUtil.mutListener.listen(64031)) {
                                        ctx.manager.setError("Out of memory while reassembling incoming data channel message");
                                    }
                                } catch (Exception error) {
                                    if (!ListenerUtil.mutListener.listen(64032)) {
                                        logger.error("Unhandled exception", error);
                                    }
                                    if (!ListenerUtil.mutListener.listen(64033)) {
                                        ctx.manager.setError("Exception encountered");
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     *  Send a connection disconnect message.
     *
     *  Important: This is meant as a "last resort" mechanism before a session is
     *             being torn down. Do not use this for regular disconnects!
     */
    private void sendBestEffortConnectionDisconnect(@DisconnectContext.DisconnectReason int reason) {
        // Ensure connected
        final SessionState state = this.ctx.manager.getInternalState();
        if (!ListenerUtil.mutListener.listen(64038)) {
            if (!(state instanceof SessionStateConnected)) {
                if (!ListenerUtil.mutListener.listen(64037)) {
                    logger.info("Could not send alert, not connected");
                }
                return;
            }
        }
        // Send alert synchronously
        final MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(64039)) {
            builder.put(Protocol.FIELD_TYPE, Protocol.TYPE_UPDATE);
        }
        if (!ListenerUtil.mutListener.listen(64040)) {
            builder.put(Protocol.FIELD_SUB_TYPE, Protocol.SUB_TYPE_CONNECTION_DISCONNECT);
        }
        try {
            if (!ListenerUtil.mutListener.listen(64042)) {
                builder.put(Protocol.FIELD_DATA, ConnectionDisconnect.convert(reason));
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(64041)) {
                logger.warn("ConversionException in sendBestEffortConnectionDisconnect", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(64043)) {
            logger.debug("Sending alert");
        }
        if (!ListenerUtil.mutListener.listen(64044)) {
            state.send(builder.consume(), SendMode.UNSAFE_SYNC);
        }
    }
}
