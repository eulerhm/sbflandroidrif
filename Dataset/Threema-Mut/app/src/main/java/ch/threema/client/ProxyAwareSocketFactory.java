/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
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
package ch.threema.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.URI;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ProxyAwareSocketFactory {

    private static final Logger logger = LoggerFactory.getLogger(ProxyAwareSocketFactory.class);

    public static boolean shouldUseProxy(String hostname, int port) {
        List<Proxy> proxies = ProxySelector.getDefault().select(URI.create("https://" + hostname + ":" + port + "/"));
        if (!ListenerUtil.mutListener.listen(68722)) {
            if ((ListenerUtil.mutListener.listen(68721) ? ((ListenerUtil.mutListener.listen(68720) ? ((ListenerUtil.mutListener.listen(68719) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68718) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68717) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68716) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68715) ? (proxies.size() != 0) : (proxies.size() == 0)))))) && proxies.get(0) == Proxy.NO_PROXY) : ((ListenerUtil.mutListener.listen(68719) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68718) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68717) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68716) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68715) ? (proxies.size() != 0) : (proxies.size() == 0)))))) || proxies.get(0) == Proxy.NO_PROXY)) && proxies.get(0).type() == Proxy.Type.DIRECT) : ((ListenerUtil.mutListener.listen(68720) ? ((ListenerUtil.mutListener.listen(68719) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68718) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68717) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68716) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68715) ? (proxies.size() != 0) : (proxies.size() == 0)))))) && proxies.get(0) == Proxy.NO_PROXY) : ((ListenerUtil.mutListener.listen(68719) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68718) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68717) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68716) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68715) ? (proxies.size() != 0) : (proxies.size() == 0)))))) || proxies.get(0) == Proxy.NO_PROXY)) || proxies.get(0).type() == Proxy.Type.DIRECT))) {
                return false;
            }
        }
        return true;
    }

    public static Socket makeSocket(InetSocketAddress address) {
        List<Proxy> proxies = ProxySelector.getDefault().select(URI.create("https://" + address.getHostName() + ":" + address.getPort() + "/"));
        if ((ListenerUtil.mutListener.listen(68729) ? ((ListenerUtil.mutListener.listen(68728) ? ((ListenerUtil.mutListener.listen(68727) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68726) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68725) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68724) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68723) ? (proxies.size() != 0) : (proxies.size() == 0)))))) && proxies.get(0) == Proxy.NO_PROXY) : ((ListenerUtil.mutListener.listen(68727) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68726) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68725) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68724) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68723) ? (proxies.size() != 0) : (proxies.size() == 0)))))) || proxies.get(0) == Proxy.NO_PROXY)) && proxies.get(0).type() == Proxy.Type.DIRECT) : ((ListenerUtil.mutListener.listen(68728) ? ((ListenerUtil.mutListener.listen(68727) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68726) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68725) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68724) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68723) ? (proxies.size() != 0) : (proxies.size() == 0)))))) && proxies.get(0) == Proxy.NO_PROXY) : ((ListenerUtil.mutListener.listen(68727) ? (proxies.size() >= 0) : (ListenerUtil.mutListener.listen(68726) ? (proxies.size() <= 0) : (ListenerUtil.mutListener.listen(68725) ? (proxies.size() > 0) : (ListenerUtil.mutListener.listen(68724) ? (proxies.size() < 0) : (ListenerUtil.mutListener.listen(68723) ? (proxies.size() != 0) : (proxies.size() == 0)))))) || proxies.get(0) == Proxy.NO_PROXY)) || proxies.get(0).type() == Proxy.Type.DIRECT))) {
            if (!ListenerUtil.mutListener.listen(68730)) {
                // No proxy
                logger.info("No proxy configured");
            }
            return new Socket();
        }
        // Look for a SOCKS proxy first, as we prefer that
        Proxy chosenProxy = null;
        if (!ListenerUtil.mutListener.listen(68733)) {
            {
                long _loopCounter870 = 0;
                for (Proxy proxy : proxies) {
                    ListenerUtil.loopListener.listen("_loopCounter870", ++_loopCounter870);
                    if (!ListenerUtil.mutListener.listen(68732)) {
                        if (proxy.type() == Proxy.Type.SOCKS) {
                            if (!ListenerUtil.mutListener.listen(68731)) {
                                chosenProxy = proxy;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68737)) {
            if (chosenProxy == null) {
                if (!ListenerUtil.mutListener.listen(68736)) {
                    {
                        long _loopCounter871 = 0;
                        // Fall back to the first HTTP proxy
                        for (Proxy proxy : proxies) {
                            ListenerUtil.loopListener.listen("_loopCounter871", ++_loopCounter871);
                            if (!ListenerUtil.mutListener.listen(68735)) {
                                if (proxy.type() == Proxy.Type.HTTP) {
                                    if (!ListenerUtil.mutListener.listen(68734)) {
                                        chosenProxy = proxy;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (chosenProxy == null) {
            if (!ListenerUtil.mutListener.listen(68738)) {
                logger.info("No proxy chosen");
            }
            return new Socket();
        }
        if (!ListenerUtil.mutListener.listen(68739)) {
            // implementation as JDK 7 does not support HTTP for Socket.
            logger.info("Using proxy: " + chosenProxy);
        }
        switch(chosenProxy.type()) {
            case SOCKS:
                return new Socket(chosenProxy);
            case HTTP:
                return new HttpProxySocket(chosenProxy);
            default:
                return null;
        }
    }
}
