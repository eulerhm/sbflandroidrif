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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import org.saltyrtc.client.SaltyRTCBuilder;
import org.saltyrtc.client.exceptions.ConnectionException;
import org.saltyrtc.client.exceptions.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.state.WebClientSessionState;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The session is connecting.
 */
@WorkerThread
final class SessionStateConnecting extends SessionState {

    @NonNull
    private final SessionConnectionContext cctx;

    SessionStateConnecting(@NonNull final SessionContext ctx, @NonNull final SaltyRTCBuilder builder, @Nullable final String affiliationId) throws InvalidStateTransition {
        super(WebClientSessionState.CONNECTING, ctx);
        if (!ListenerUtil.mutListener.listen(64078)) {
            logger.info("Initializing");
        }
        if (!ListenerUtil.mutListener.listen(64079)) {
            // Update affiliation id
            ctx.affiliationId = affiliationId;
        }
        if (!ListenerUtil.mutListener.listen(64080)) {
            // Acquire resources
            logger.info("Acquire session resources...");
        }
        if (!ListenerUtil.mutListener.listen(64081)) {
            this.ctx.acquireResources();
        }
        // Create session connection context
        try {
            this.cctx = new SessionConnectionContext(ctx, builder);
        } catch (NoSuchAlgorithmException | InvalidKeyException error) {
            if (!ListenerUtil.mutListener.listen(64082)) {
                logger.error("Cannot create session connection context:", error);
            }
            throw new InvalidStateTransition(error.getMessage());
        }
        // Increment connection ID
        final int connectionId = ++this.ctx.connectionId;
        if (!ListenerUtil.mutListener.listen(64083)) {
            logger.info("Starting connection {} of session {}", connectionId, this.ctx.sessionId);
        }
        // Connect to the SaltyRTC server asynchronously
        try {
            if (!ListenerUtil.mutListener.listen(64085)) {
                this.cctx.salty.connect();
            }
        } catch (ConnectionException error) {
            if (!ListenerUtil.mutListener.listen(64084)) {
                logger.error("SaltyRTC connect failed", error);
            }
            throw new InvalidStateTransition(error.getMessage());
        }
        if (!ListenerUtil.mutListener.listen(64088)) {
            // Create timer for the client-to-client connection
            this.ctx.handler.postDelayed(new Runnable() {

                @Override
                @WorkerThread
                public void run() {
                    if (!ListenerUtil.mutListener.listen(64087)) {
                        // Only error out when we're still in this state
                        if (SessionStateConnecting.this.ctx.manager.getInternalState() == SessionStateConnecting.this) {
                            if (!ListenerUtil.mutListener.listen(64086)) {
                                SessionStateConnecting.this.ctx.manager.setError("Timeout while connecting to remote client");
                            }
                        }
                    }
                }
            }, SessionConnectionContext.C2C_CONNECT_TIMEOUT_MS);
        }
    }

    @Override
    @NonNull
    SessionStateConnected setConnected() {
        if (!ListenerUtil.mutListener.listen(64089)) {
            logger.info("Connected");
        }
        return new SessionStateConnected(this.ctx, this.cctx);
    }

    @Override
    @NonNull
    SessionStateDisconnected setDisconnected(@NonNull final DisconnectContext reason) {
        if (!ListenerUtil.mutListener.listen(64090)) {
            logger.info("Disconnected (reason: {})", reason);
        }
        return new SessionStateDisconnected(this.ctx, this.cctx, reason);
    }

    @Override
    @NonNull
    SessionStateError setError(@NonNull final String reason) {
        if (!ListenerUtil.mutListener.listen(64091)) {
            logger.error("Error: {}", reason);
        }
        return new SessionStateError(this.ctx, this.cctx);
    }
}
