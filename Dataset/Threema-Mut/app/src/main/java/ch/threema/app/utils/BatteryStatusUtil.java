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

import android.content.Intent;
import android.os.BatteryManager;
import androidx.annotation.Nullable;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BatteryStatusUtil {

    /**
     *  Return whether the device is charging or not.
     *
     *  @param intent An intent from a battery status broadcast.
     */
    @Nullable
    public static Boolean isCharging(Intent intent) {
        final int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if (!ListenerUtil.mutListener.listen(49510)) {
            if ((ListenerUtil.mutListener.listen(49509) ? (status >= -1) : (ListenerUtil.mutListener.listen(49508) ? (status <= -1) : (ListenerUtil.mutListener.listen(49507) ? (status > -1) : (ListenerUtil.mutListener.listen(49506) ? (status < -1) : (ListenerUtil.mutListener.listen(49505) ? (status != -1) : (status == -1))))))) {
                return null;
            }
        }
        return (ListenerUtil.mutListener.listen(49511) ? (status == BatteryManager.BATTERY_STATUS_CHARGING && status == BatteryManager.BATTERY_STATUS_FULL) : (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL));
    }

    @Nullable
    public static Integer getPercent(Intent intent) {
        final int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        final int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        if (!ListenerUtil.mutListener.listen(49523)) {
            if ((ListenerUtil.mutListener.listen(49522) ? ((ListenerUtil.mutListener.listen(49516) ? (level >= -1) : (ListenerUtil.mutListener.listen(49515) ? (level <= -1) : (ListenerUtil.mutListener.listen(49514) ? (level > -1) : (ListenerUtil.mutListener.listen(49513) ? (level < -1) : (ListenerUtil.mutListener.listen(49512) ? (level != -1) : (level == -1)))))) && (ListenerUtil.mutListener.listen(49521) ? (scale >= -1) : (ListenerUtil.mutListener.listen(49520) ? (scale <= -1) : (ListenerUtil.mutListener.listen(49519) ? (scale > -1) : (ListenerUtil.mutListener.listen(49518) ? (scale < -1) : (ListenerUtil.mutListener.listen(49517) ? (scale != -1) : (scale == -1))))))) : ((ListenerUtil.mutListener.listen(49516) ? (level >= -1) : (ListenerUtil.mutListener.listen(49515) ? (level <= -1) : (ListenerUtil.mutListener.listen(49514) ? (level > -1) : (ListenerUtil.mutListener.listen(49513) ? (level < -1) : (ListenerUtil.mutListener.listen(49512) ? (level != -1) : (level == -1)))))) || (ListenerUtil.mutListener.listen(49521) ? (scale >= -1) : (ListenerUtil.mutListener.listen(49520) ? (scale <= -1) : (ListenerUtil.mutListener.listen(49519) ? (scale > -1) : (ListenerUtil.mutListener.listen(49518) ? (scale < -1) : (ListenerUtil.mutListener.listen(49517) ? (scale != -1) : (scale == -1))))))))) {
                return null;
            }
        }
        return (ListenerUtil.mutListener.listen(49531) ? ((ListenerUtil.mutListener.listen(49527) ? (level % 100) : (ListenerUtil.mutListener.listen(49526) ? (level / 100) : (ListenerUtil.mutListener.listen(49525) ? (level - 100) : (ListenerUtil.mutListener.listen(49524) ? (level + 100) : (level * 100))))) % scale) : (ListenerUtil.mutListener.listen(49530) ? ((ListenerUtil.mutListener.listen(49527) ? (level % 100) : (ListenerUtil.mutListener.listen(49526) ? (level / 100) : (ListenerUtil.mutListener.listen(49525) ? (level - 100) : (ListenerUtil.mutListener.listen(49524) ? (level + 100) : (level * 100))))) * scale) : (ListenerUtil.mutListener.listen(49529) ? ((ListenerUtil.mutListener.listen(49527) ? (level % 100) : (ListenerUtil.mutListener.listen(49526) ? (level / 100) : (ListenerUtil.mutListener.listen(49525) ? (level - 100) : (ListenerUtil.mutListener.listen(49524) ? (level + 100) : (level * 100))))) - scale) : (ListenerUtil.mutListener.listen(49528) ? ((ListenerUtil.mutListener.listen(49527) ? (level % 100) : (ListenerUtil.mutListener.listen(49526) ? (level / 100) : (ListenerUtil.mutListener.listen(49525) ? (level - 100) : (ListenerUtil.mutListener.listen(49524) ? (level + 100) : (level * 100))))) + scale) : ((ListenerUtil.mutListener.listen(49527) ? (level % 100) : (ListenerUtil.mutListener.listen(49526) ? (level / 100) : (ListenerUtil.mutListener.listen(49525) ? (level - 100) : (ListenerUtil.mutListener.listen(49524) ? (level + 100) : (level * 100))))) / scale)))));
    }
}
