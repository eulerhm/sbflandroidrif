/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema Java Client
 * Copyright (c) 2013-2021 Threema GmbH
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

import com.neilalexander.jnacl.NaCl;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class NonceCounter {

    private final byte[] cookie;

    private long nextNonce;

    public NonceCounter(byte[] cookie) {
        this.cookie = cookie;
        if (!ListenerUtil.mutListener.listen(68567)) {
            nextNonce = 1;
        }
    }

    public synchronized byte[] nextNonce() {
        byte[] nonce = new byte[NaCl.NONCEBYTES];
        if (!ListenerUtil.mutListener.listen(68568)) {
            System.arraycopy(cookie, 0, nonce, 0, ProtocolDefines.COOKIE_LEN);
        }
        if (!ListenerUtil.mutListener.listen(68579)) {
            {
                long _loopCounter865 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(68578) ? (i >= 8) : (ListenerUtil.mutListener.listen(68577) ? (i <= 8) : (ListenerUtil.mutListener.listen(68576) ? (i > 8) : (ListenerUtil.mutListener.listen(68575) ? (i != 8) : (ListenerUtil.mutListener.listen(68574) ? (i == 8) : (i < 8)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter865", ++_loopCounter865);
                    if (!ListenerUtil.mutListener.listen(68573)) {
                        nonce[i + ProtocolDefines.COOKIE_LEN] = (byte) (nextNonce >> ((ListenerUtil.mutListener.listen(68572) ? (i % 8) : (ListenerUtil.mutListener.listen(68571) ? (i / 8) : (ListenerUtil.mutListener.listen(68570) ? (i - 8) : (ListenerUtil.mutListener.listen(68569) ? (i + 8) : (i * 8)))))));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(68580)) {
            nextNonce++;
        }
        return nonce;
    }
}
