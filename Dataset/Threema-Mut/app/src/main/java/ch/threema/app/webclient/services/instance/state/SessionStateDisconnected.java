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
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.state.WebClientSessionState;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The session is disconnected.
 */
@WorkerThread
final class SessionStateDisconnected extends SessionState {

    @AnyThread
    SessionStateDisconnected(@NonNull final SessionContext ctx) {
        super(WebClientSessionState.DISCONNECTED, ctx);
        if (!ListenerUtil.mutListener.listen(64092)) {
            logger.info("Initializing with no connection");
        }
    }

    SessionStateDisconnected(@NonNull final SessionContext ctx, @NonNull final SessionConnectionContext cctx, @NonNull final DisconnectContext reason) {
        super(WebClientSessionState.DISCONNECTED, ctx);
        if (!ListenerUtil.mutListener.listen(64093)) {
            logger.info("Initializing with existing connection, reason: {}", reason);
        }
        if (!ListenerUtil.mutListener.listen(64094)) {
            // Tear down the existing connection
            logger.info("Cleanup");
        }
        if (!ListenerUtil.mutListener.listen(64095)) {
            CleanupHelper.cleanupSessionConnectionContext(logger, cctx);
        }
        if (!ListenerUtil.mutListener.listen(64096)) {
            CleanupHelper.cleanupSessionContext(logger, this.ctx);
        }
    }

    @Override
    @NonNull
    SessionStateDisconnected setDisconnected(@NonNull final DisconnectContext reason) throws IgnoredStateTransition {
        throw new IgnoredStateTransition("Already disconnected");
    }

    @Override
    @NonNull
    SessionStateConnecting setConnecting(@NonNull final SaltyRTCBuilder builder, @Nullable final String affiliationId) throws InvalidStateTransition {
        if (!ListenerUtil.mutListener.listen(64097)) {
            logger.info("Connecting");
        }
        return new SessionStateConnecting(this.ctx, builder, affiliationId);
    }

    @Override
    @NonNull
    SessionStateError setError(@NonNull final String reason) {
        if (!ListenerUtil.mutListener.listen(64098)) {
            logger.error("Error: {}", reason);
        }
        return new SessionStateError(this.ctx);
    }
}
