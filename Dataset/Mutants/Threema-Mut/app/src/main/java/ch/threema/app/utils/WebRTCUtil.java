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
package ch.threema.app.utils;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.IceCandidate;
import org.webrtc.Logging;
import org.webrtc.PeerConnectionFactory;
import androidx.annotation.NonNull;
import ch.threema.logging.WebRTCLoggable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This util handles WebRTC initialization.
 */
public class WebRTCUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebRTCUtil.class);

    private static boolean initialized = false;

    /**
     *  If the WebRTC Android globals haven't been initialized yet, initialize them.
     *
     *  @param appContext The Android context to use. Make sure to use the application context!
     */
    public static void initializeAndroidGlobals(final Context appContext) {
        if (!ListenerUtil.mutListener.listen(55870)) {
            if (!initialized) {
                if (!ListenerUtil.mutListener.listen(55867)) {
                    logger.debug("Initializing Android globals");
                }
                // it should only be enabled temporarily.
                final boolean enableVerboseInternalTracing = false;
                if (!ListenerUtil.mutListener.listen(55868)) {
                    // Initialize peer connection factory
                    PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(appContext).setEnableInternalTracer(enableVerboseInternalTracing).setInjectableLogger(new WebRTCLoggable(), Logging.Severity.LS_INFO).createInitializationOptions());
                }
                if (!ListenerUtil.mutListener.listen(55869)) {
                    initialized = true;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(55866)) {
                    logger.debug("Android globals already initialized");
                }
            }
        }
    }

    /**
     *  Convert an ICE candidate to a nice string representation.
     *  @param candidate The ICE candidate
     */
    @NonNull
    public static String iceCandidateToString(@NonNull IceCandidate candidate) {
        final IceCandidateParser.CandidateData parsed = IceCandidateParser.parse(candidate.sdp);
        if (parsed != null) {
            final StringBuilder builder = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(55871)) {
                builder.append("[").append(parsed.candType).append("] ").append(parsed.transport);
            }
            if (!ListenerUtil.mutListener.listen(55873)) {
                if (parsed.tcptype != null) {
                    if (!ListenerUtil.mutListener.listen(55872)) {
                        builder.append("/").append(parsed.tcptype);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(55874)) {
                builder.append(" ").append(parsed.connectionAddress).append(":").append(parsed.port);
            }
            if (!ListenerUtil.mutListener.listen(55877)) {
                if ((ListenerUtil.mutListener.listen(55875) ? (parsed.relAddr != null || parsed.relPort != null) : (parsed.relAddr != null && parsed.relPort != null))) {
                    if (!ListenerUtil.mutListener.listen(55876)) {
                        builder.append(" via ").append(parsed.relAddr).append(":").append(parsed.relPort);
                    }
                }
            }
            return builder.toString();
        } else {
            return candidate.sdp;
        }
    }
}
