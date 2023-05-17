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
import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Convert a message event.
 */
@AnyThread
public class MessageEvent extends Converter {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ TYPE_CREATED, TYPE_SENT, TYPE_DELIVERED, TYPE_READ, TYPE_ACKED, TYPE_MODIFIED })
    private @interface EventType {
    }

    public static final String KEY_TYPE = "type";

    public static final String KEY_DATE = "date";

    public static final String TYPE_CREATED = "created";

    public static final String TYPE_SENT = "sent";

    public static final String TYPE_DELIVERED = "delivered";

    public static final String TYPE_READ = "read";

    public static final String TYPE_ACKED = "acked";

    public static final String TYPE_MODIFIED = "modified";

    @NonNull
    public static MsgpackObjectBuilder convert(@EventType String type, @NonNull Date date) {
        final MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62865)) {
            builder.put(KEY_TYPE, type);
        }
        if (!ListenerUtil.mutListener.listen(62870)) {
            builder.put(KEY_DATE, (ListenerUtil.mutListener.listen(62869) ? (date.getTime() % 1000) : (ListenerUtil.mutListener.listen(62868) ? (date.getTime() * 1000) : (ListenerUtil.mutListener.listen(62867) ? (date.getTime() - 1000) : (ListenerUtil.mutListener.listen(62866) ? (date.getTime() + 1000) : (date.getTime() / 1000))))));
        }
        return builder;
    }
}
