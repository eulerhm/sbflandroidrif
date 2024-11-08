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
package ch.threema.app.webclient.converter;

import androidx.annotation.AnyThread;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class BatteryStatus extends Converter {

    private static final String PERCENT = "percent";

    private static final String IS_CHARGING = "isCharging";

    public static MsgpackObjectBuilder convert(int percent, boolean isCharging) {
        MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62599)) {
            builder.put(PERCENT, percent);
        }
        if (!ListenerUtil.mutListener.listen(62600)) {
            builder.put(IS_CHARGING, isCharging);
        }
        return builder;
    }
}
