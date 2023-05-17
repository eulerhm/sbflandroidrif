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

import org.saltyrtc.client.signaling.state.SignalingState;
import org.slf4j.Logger;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper to clean up data related to a webclient session.
 */
@WorkerThread
class CleanupHelper {

    /**
     *  Dispose the session context.
     */
    static void cleanupSessionContext(@NonNull final Logger logger, @NonNull final SessionContext ctx) {
        if (!ListenerUtil.mutListener.listen(63880)) {
            logger.debug("CleanupHelper: Release session resources...");
        }
        if (!ListenerUtil.mutListener.listen(63881)) {
            ctx.releaseResources();
        }
    }

    /**
     *  Dispose the session connection context and discard any further events.
     */
    static void cleanupSessionConnectionContext(@NonNull final Logger logger, @Nullable final SessionConnectionContext cctx) {
        if (!ListenerUtil.mutListener.listen(63882)) {
            if (cctx == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63883)) {
            // Mark the session connection closed to ignore all further events
            cctx.closed.set(true);
        }
        if (!ListenerUtil.mutListener.listen(63884)) {
            // Warning: Keep it in this order, otherwise, you'll see deadlocks!
            CleanupHelper.cleanupSaltyRTC(logger, cctx);
        }
        if (!ListenerUtil.mutListener.listen(63885)) {
            CleanupHelper.cleanupPeerConnection(logger, cctx);
        }
    }

    private static void cleanupPeerConnection(@NonNull final Logger logger, @NonNull final SessionConnectionContext cctx) {
        if (!ListenerUtil.mutListener.listen(63886)) {
            if (cctx.pc == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(63887)) {
            // Note: This will eventually dispose the data channel once the close event fires on the channel.
            logger.debug("CleanupHelper: Disposing peer connection wrapper");
        }
        if (!ListenerUtil.mutListener.listen(63888)) {
            cctx.pc.dispose();
        }
        if (!ListenerUtil.mutListener.listen(63889)) {
            cctx.pc = null;
        }
    }

    /**
     *  Clear all SaltyRTC event listeners.
     */
    private static void cleanupSaltyRTC(@NonNull final Logger logger, @NonNull final SessionConnectionContext cctx) {
        if (!ListenerUtil.mutListener.listen(63890)) {
            // Clear all SaltyRTC event listeners
            cctx.salty.events.clearAll();
        }
        if (!ListenerUtil.mutListener.listen(63894)) {
            // Make sure that SaltyRTC is disconnected
            if ((ListenerUtil.mutListener.listen(63891) ? (cctx.salty.getSignalingState() != SignalingState.CLOSED || cctx.salty.getSignalingState() != SignalingState.CLOSING) : (cctx.salty.getSignalingState() != SignalingState.CLOSED && cctx.salty.getSignalingState() != SignalingState.CLOSING))) {
                if (!ListenerUtil.mutListener.listen(63892)) {
                    logger.debug("CleanupHelper: Disconnecting SaltyRTC (signaling state was {})", cctx.salty.getSignalingState());
                }
                if (!ListenerUtil.mutListener.listen(63893)) {
                    cctx.salty.disconnect();
                }
            }
        }
    }
}
