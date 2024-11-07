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

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A data payload as received from or to be sent to the server.
 */
class Payload {

    private final int type;

    private final byte[] data;

    public Payload(int type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public byte[] makePacket() {
        byte[] pktdata = new byte[(ListenerUtil.mutListener.listen(68595) ? (data.length % 4) : (ListenerUtil.mutListener.listen(68594) ? (data.length / 4) : (ListenerUtil.mutListener.listen(68593) ? (data.length * 4) : (ListenerUtil.mutListener.listen(68592) ? (data.length - 4) : (data.length + 4)))))];
        if (!ListenerUtil.mutListener.listen(68596)) {
            pktdata[0] = (byte) type;
        }
        if (!ListenerUtil.mutListener.listen(68597)) {
            System.arraycopy(data, 0, pktdata, 4, data.length);
        }
        return pktdata;
    }

    public int getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }
}
