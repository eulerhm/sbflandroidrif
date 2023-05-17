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

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import org.saltyrtc.client.SaltyRTCBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.ByteBuffer;
import java.util.Objects;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.webclient.SendMode;
import ch.threema.app.webclient.listeners.WebClientServiceListener;
import ch.threema.app.webclient.manager.WebClientListenerManager;
import ch.threema.app.webclient.services.ServicesContainer;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.state.WebClientSessionState;
import ch.threema.logging.ThreemaLogger;
import ch.threema.storage.models.WebClientSessionModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class manages and holds the state for a session.
 */
@WorkerThread
public class SessionStateManager {

    /**
     *  Called in-sync when the session state is moving into the stopped state
     *  and there are no pending wakeups for this session.
     */
    public interface StopHandler {

        void onStopped(@NonNull DisconnectContext reason);
    }

    private final Logger logger = LoggerFactory.getLogger(SessionStateManager.class);

    // Stop event handler
    @NonNull
    private final StopHandler stopHandler;

    // Session context that is passed from one state to another
    @NonNull
    private final SessionContext ctx;

    // Current state
    @NonNull
    private SessionState state;

    @AnyThread
    public SessionStateManager(final int sessionId, @NonNull final WebClientSessionModel model, @NonNull final HandlerExecutor handler, @NonNull final ServicesContainer services, @NonNull final StopHandler stopHandler) {
        if (!ListenerUtil.mutListener.listen(64107)) {
            // Note: <session-id>.<affiliation-id>, where the affiliation id is initially not provided
            if (logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(64106)) {
                    ((ThreemaLogger) logger).setPrefix(sessionId + ".null");
                }
            }
        }
        // Store stop event handler
        this.stopHandler = stopHandler;
        // Create initial session context
        this.ctx = new SessionContext(this, sessionId, model, handler, services);
        if (!ListenerUtil.mutListener.listen(64108)) {
            // Create initial state
            this.state = new SessionStateDisconnected(this.ctx);
        }
    }

    /**
     *  Get the current session state instance.
     *
     *  Note: Don't expose the SessionState instance to the outside!
     */
    @NonNull
    SessionState getInternalState() {
        return this.state;
    }

    /**
     *  Get the current session state as an enum (not the instance).
     */
    @NonNull
    public WebClientSessionState getState() {
        return this.state.state;
    }

    // State transitions
    public void setConnecting(@NonNull final SaltyRTCBuilder builder, @Nullable final String affiliationId) {
        if (!ListenerUtil.mutListener.listen(64109)) {
            this.updateState(WebClientSessionState.CONNECTING, builder, affiliationId, null, null);
        }
    }

    public void setConnected() {
        if (!ListenerUtil.mutListener.listen(64110)) {
            this.updateState(WebClientSessionState.CONNECTED, null, null, null, null);
        }
    }

    public void setDisconnected(@NonNull final DisconnectContext reason) {
        if (!ListenerUtil.mutListener.listen(64111)) {
            this.updateState(WebClientSessionState.DISCONNECTED, null, null, reason, null);
        }
    }

    public void setError(@NonNull final String reason) {
        if (!ListenerUtil.mutListener.listen(64112)) {
            this.updateState(WebClientSessionState.ERROR, null, null, null, reason);
        }
    }

    /**
     *  Update the current session state and fire state events.
     *
     *  Warning: This is a critical code section! Be careful when you touch this!
     */
    private void updateState(@NonNull final WebClientSessionState desiredState, @Nullable final SaltyRTCBuilder connectionBuilder, @Nullable final String connectionAffiliationId, @Nullable DisconnectContext disconnectReason, @Nullable final String errorReason) {
        // Attempt to replace the state
        final SessionState currentState = this.state;
        SessionState newState;
        if (!ListenerUtil.mutListener.listen(64113)) {
            logger.info("Changing state from {} to {}", currentState.state, desiredState);
        }
        try {
            switch(desiredState) {
                case CONNECTING:
                    newState = currentState.setConnecting(Objects.requireNonNull(connectionBuilder), connectionAffiliationId);
                    break;
                case CONNECTED:
                    newState = currentState.setConnected();
                    break;
                case DISCONNECTED:
                    newState = currentState.setDisconnected(Objects.requireNonNull(disconnectReason));
                    break;
                case ERROR:
                    newState = currentState.setError(Objects.requireNonNull(errorReason));
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(64117)) {
                        logger.error("Unknown state: {}", desiredState);
                    }
                    return;
            }
        } catch (SessionState.IgnoredStateTransition error) {
            if (!ListenerUtil.mutListener.listen(64114)) {
                // Transition has been ignored - just log it
                logger.info(error.getMessage());
            }
            return;
        } catch (SessionState.InvalidStateTransition error) {
            if (!ListenerUtil.mutListener.listen(64116)) {
                // Transition was not possible, move into error state
                if (logger.isErrorEnabled()) {
                    if (!ListenerUtil.mutListener.listen(64115)) {
                        logger.error("Could not perform state transition from {} to {}: {}", currentState.state, desiredState, error.getMessage());
                    }
                }
            }
            newState = currentState.setError("Could not perform state transition from " + currentState.state + " to " + desiredState);
        }
        if (!ListenerUtil.mutListener.listen(64118)) {
            this.state = newState;
        }
        // WARNING: If you start a state transition in a state event handler, it will break your neck!
        SessionState finalNewState = newState;
        if (!ListenerUtil.mutListener.listen(64120)) {
            WebClientListenerManager.serviceListener.handle(new ListenerManager.HandleListener<WebClientServiceListener>() {

                @Override
                @WorkerThread
                public void handle(WebClientServiceListener listener) {
                    if (!ListenerUtil.mutListener.listen(64119)) {
                        listener.onStateChanged(SessionStateManager.this.ctx.model, currentState.state, finalNewState.state);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(64130)) {
            // would otherwise stop the foreground service!
            if ((ListenerUtil.mutListener.listen(64121) ? (newState.state == WebClientSessionState.DISCONNECTED && newState.state == WebClientSessionState.ERROR) : (newState.state == WebClientSessionState.DISCONNECTED || newState.state == WebClientSessionState.ERROR))) {
                if (!ListenerUtil.mutListener.listen(64122)) {
                    logger.info("Processing pending wakeups");
                }
                if (!ListenerUtil.mutListener.listen(64123)) {
                    this.ctx.services.sessionWakeUp.processPendingWakeups();
                }
                if (!ListenerUtil.mutListener.listen(64129)) {
                    // instance (!) remained the same.
                    if (this.ctx.manager.state == newState) {
                        if (!ListenerUtil.mutListener.listen(64125)) {
                            logger.info("No pending wakeups, stopping");
                        }
                        final DisconnectContext reason = disconnectReason != null ? disconnectReason : DisconnectContext.unknown();
                        if (!ListenerUtil.mutListener.listen(64126)) {
                            // Raise to session instance first, so it can unregister events
                            this.stopHandler.onStopped(reason);
                        }
                        if (!ListenerUtil.mutListener.listen(64128)) {
                            // Raise to all listeners
                            WebClientListenerManager.serviceListener.handle(new ListenerManager.HandleListener<WebClientServiceListener>() {

                                @Override
                                @WorkerThread
                                public void handle(WebClientServiceListener listener) {
                                    if (!ListenerUtil.mutListener.listen(64127)) {
                                        listener.onStopped(SessionStateManager.this.ctx.model, reason);
                                    }
                                }
                            });
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(64124)) {
                            logger.debug("Pending wakeups processed, continuing");
                        }
                    }
                }
            }
        }
    }

    /**
     *  Send a msgpack encoded message to the peer through the secure data channel.
     */
    public void send(@NonNull final ByteBuffer message, @NonNull final SendMode mode) {
        if (!ListenerUtil.mutListener.listen(64131)) {
            this.state.send(message, mode);
        }
    }
}
