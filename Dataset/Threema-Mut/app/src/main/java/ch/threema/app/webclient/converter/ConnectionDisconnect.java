/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.webclient.converter;

import androidx.annotation.AnyThread;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.DisconnectContext;
import ch.threema.app.webclient.services.instance.DisconnectContext.DisconnectReason;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * https://threema-ch.github.io/app-remote-protocol/message-update-connectionDisconnect-bidirectional.html
 */
@AnyThread
public class ConnectionDisconnect extends Converter {

    // Top level keys
    public static final String REASON = "reason";

    public static final String REASON_SESSION_STOPPED = "stop";

    public static final String REASON_SESSION_DELETED = "delete";

    public static final String REASON_WEBCLIENT_DISABLED = "disable";

    public static final String REASON_SESSION_REPLACED = "replace";

    public static final String REASON_OUT_OF_MEMORY = "oom";

    public static final String REASON_ERROR = "error";

    public static MsgpackObjectBuilder convert(@DisconnectReason int disconnectReason) throws ConversionException {
        final MsgpackObjectBuilder data = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62651)) {
            switch(disconnectReason) {
                case DisconnectContext.REASON_SESSION_STOPPED:
                    if (!ListenerUtil.mutListener.listen(62645)) {
                        data.put(REASON, REASON_SESSION_STOPPED);
                    }
                    break;
                case DisconnectContext.REASON_SESSION_DELETED:
                    if (!ListenerUtil.mutListener.listen(62646)) {
                        data.put(REASON, REASON_SESSION_DELETED);
                    }
                    break;
                case DisconnectContext.REASON_WEBCLIENT_DISABLED:
                    if (!ListenerUtil.mutListener.listen(62647)) {
                        data.put(REASON, REASON_WEBCLIENT_DISABLED);
                    }
                    break;
                case DisconnectContext.REASON_SESSION_REPLACED:
                    if (!ListenerUtil.mutListener.listen(62648)) {
                        data.put(REASON, REASON_SESSION_REPLACED);
                    }
                    break;
                case DisconnectContext.REASON_OUT_OF_MEMORY:
                    if (!ListenerUtil.mutListener.listen(62649)) {
                        data.put(REASON, REASON_OUT_OF_MEMORY);
                    }
                    break;
                case DisconnectContext.REASON_ERROR:
                    if (!ListenerUtil.mutListener.listen(62650)) {
                        data.put(REASON, REASON_ERROR);
                    }
                    break;
                default:
                    throw new ConversionException("Invalid disconnect reason: " + disconnectReason);
            }
        }
        return data;
    }
}
