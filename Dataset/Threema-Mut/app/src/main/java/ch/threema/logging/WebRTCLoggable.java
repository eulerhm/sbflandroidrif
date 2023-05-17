/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.logging;

import android.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.Loggable;
import org.webrtc.Logging;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * An adapter that sends WebRTC native logs to the SLFJ logger.
 */
public class WebRTCLoggable implements Loggable {

    private static final Logger logger = LoggerFactory.getLogger("libwebrtc");

    private static int minLevel = Log.WARN;

    /**
     *  Set the minimal log level that will be forwarded. Default is {@link Log#WARN}.
     *
     *  Note: For the log level to be actually logged, the log level in
     *  {@link ch.threema.app.utils.WebRTCUtil#initializeAndroidGlobals} must be set accordingly.
     */
    public static void setMinLevelFilter(int level) {
        if (!ListenerUtil.mutListener.listen(69539)) {
            minLevel = level;
        }
    }

    @Override
    public void onLogMessage(String msg, Logging.Severity severity, String file) {
        final String fullMsg = file + msg.trim();
        if (!ListenerUtil.mutListener.listen(69568)) {
            switch(severity) {
                case LS_VERBOSE:
                    if (!ListenerUtil.mutListener.listen(69546)) {
                        if ((ListenerUtil.mutListener.listen(69544) ? (minLevel >= Log.DEBUG) : (ListenerUtil.mutListener.listen(69543) ? (minLevel > Log.DEBUG) : (ListenerUtil.mutListener.listen(69542) ? (minLevel < Log.DEBUG) : (ListenerUtil.mutListener.listen(69541) ? (minLevel != Log.DEBUG) : (ListenerUtil.mutListener.listen(69540) ? (minLevel == Log.DEBUG) : (minLevel <= Log.DEBUG))))))) {
                            if (!ListenerUtil.mutListener.listen(69545)) {
                                logger.debug(fullMsg);
                            }
                        }
                    }
                    break;
                case LS_INFO:
                    if (!ListenerUtil.mutListener.listen(69553)) {
                        if ((ListenerUtil.mutListener.listen(69551) ? (minLevel >= Log.INFO) : (ListenerUtil.mutListener.listen(69550) ? (minLevel > Log.INFO) : (ListenerUtil.mutListener.listen(69549) ? (minLevel < Log.INFO) : (ListenerUtil.mutListener.listen(69548) ? (minLevel != Log.INFO) : (ListenerUtil.mutListener.listen(69547) ? (minLevel == Log.INFO) : (minLevel <= Log.INFO))))))) {
                            if (!ListenerUtil.mutListener.listen(69552)) {
                                logger.info(fullMsg);
                            }
                        }
                    }
                    break;
                case LS_WARNING:
                    if (!ListenerUtil.mutListener.listen(69560)) {
                        if ((ListenerUtil.mutListener.listen(69558) ? (minLevel >= Log.WARN) : (ListenerUtil.mutListener.listen(69557) ? (minLevel > Log.WARN) : (ListenerUtil.mutListener.listen(69556) ? (minLevel < Log.WARN) : (ListenerUtil.mutListener.listen(69555) ? (minLevel != Log.WARN) : (ListenerUtil.mutListener.listen(69554) ? (minLevel == Log.WARN) : (minLevel <= Log.WARN))))))) {
                            if (!ListenerUtil.mutListener.listen(69559)) {
                                logger.warn(fullMsg);
                            }
                        }
                    }
                    break;
                case LS_ERROR:
                    if (!ListenerUtil.mutListener.listen(69567)) {
                        if ((ListenerUtil.mutListener.listen(69565) ? (minLevel >= Log.ERROR) : (ListenerUtil.mutListener.listen(69564) ? (minLevel > Log.ERROR) : (ListenerUtil.mutListener.listen(69563) ? (minLevel < Log.ERROR) : (ListenerUtil.mutListener.listen(69562) ? (minLevel != Log.ERROR) : (ListenerUtil.mutListener.listen(69561) ? (minLevel == Log.ERROR) : (minLevel <= Log.ERROR))))))) {
                            if (!ListenerUtil.mutListener.listen(69566)) {
                                logger.error(fullMsg);
                            }
                        }
                    }
                    break;
                case LS_NONE:
                    // No log
                    break;
            }
        }
    }
}
