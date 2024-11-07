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
package ch.threema.app.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import androidx.annotation.NonNull;
import ch.threema.app.ThreemaApplication;
import ch.threema.client.APIConnector;
import ch.threema.logging.ThreemaLogger;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TurnServerCache {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(TurnServerCache.class);

    private final String type;

    private final int minSpareValidity;

    private APIConnector.TurnServerInfo cachedTurnServerInfo;

    /**
     *  Create a new TURN server cache of the specified type and with a given minimum validity
     *  before the cache is refreshed.
     *
     *  @param type TURN server type, e.g. "voip" or "web"
     *  @param minSpareValidity minimum spare validity (in ms)
     */
    public TurnServerCache(@NonNull String type, int minSpareValidity) {
        if (!ListenerUtil.mutListener.listen(55814)) {
            if (this.logger instanceof ThreemaLogger) {
                if (!ListenerUtil.mutListener.listen(55813)) {
                    ((ThreemaLogger) this.logger).setPrefix("[type=" + type + "]");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55815)) {
            logger.info("Init (type={}, minSpareValidity={})", type, minSpareValidity);
        }
        this.type = type;
        this.minSpareValidity = minSpareValidity;
    }

    /**
     *  Start fetching TURN servers asynchronously, so they are more likely to be available
     *  without delay when a call is accepted.
     */
    public void prefetchTurnServers() {
        if (!ListenerUtil.mutListener.listen(55816)) {
            logger.info("prefetchTurnServers");
        }
        if (!ListenerUtil.mutListener.listen(55817)) {
            new Thread(() -> {
                try {
                    getTurnServers();
                } catch (Exception ignored) {
                }
            }).start();
        }
    }

    /**
     *  Get TURN servers for use with VoIP. This call may block if no cached information is available.
     *
     *  @return TURN server URLs/credentials
     *  @throws Exception
     */
    @NonNull
    public synchronized APIConnector.TurnServerInfo getTurnServers() throws Exception {
        if (!ListenerUtil.mutListener.listen(55831)) {
            if (cachedTurnServerInfo != null) {
                if (!ListenerUtil.mutListener.listen(55818)) {
                    logger.debug("Found cached TURN server info");
                }
                Date minExpiration = new Date((ListenerUtil.mutListener.listen(55822) ? (new Date().getTime() % minSpareValidity) : (ListenerUtil.mutListener.listen(55821) ? (new Date().getTime() / minSpareValidity) : (ListenerUtil.mutListener.listen(55820) ? (new Date().getTime() * minSpareValidity) : (ListenerUtil.mutListener.listen(55819) ? (new Date().getTime() - minSpareValidity) : (new Date().getTime() + minSpareValidity))))));
                if (!ListenerUtil.mutListener.listen(55829)) {
                    if ((ListenerUtil.mutListener.listen(55827) ? (cachedTurnServerInfo.expirationDate.getTime() >= minExpiration.getTime()) : (ListenerUtil.mutListener.listen(55826) ? (cachedTurnServerInfo.expirationDate.getTime() <= minExpiration.getTime()) : (ListenerUtil.mutListener.listen(55825) ? (cachedTurnServerInfo.expirationDate.getTime() < minExpiration.getTime()) : (ListenerUtil.mutListener.listen(55824) ? (cachedTurnServerInfo.expirationDate.getTime() != minExpiration.getTime()) : (ListenerUtil.mutListener.listen(55823) ? (cachedTurnServerInfo.expirationDate.getTime() == minExpiration.getTime()) : (cachedTurnServerInfo.expirationDate.getTime() > minExpiration.getTime()))))))) {
                        if (!ListenerUtil.mutListener.listen(55828)) {
                            logger.info("Returning cached TURN server info");
                        }
                        return cachedTurnServerInfo;
                    }
                }
                if (!ListenerUtil.mutListener.listen(55830)) {
                    logger.debug("Cached TURN server info expired");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(55832)) {
            logger.info("Returning fresh TURN server info");
        }
        if (!ListenerUtil.mutListener.listen(55833)) {
            cachedTurnServerInfo = ThreemaApplication.getServiceManager().getAPIConnector().obtainTurnServers(ThreemaApplication.getServiceManager().getIdentityStore(), type);
        }
        return cachedTurnServerInfo;
    }
}
