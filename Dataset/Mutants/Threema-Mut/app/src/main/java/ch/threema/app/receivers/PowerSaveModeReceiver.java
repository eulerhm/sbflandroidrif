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
package ch.threema.app.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PowerSaveModeReceiver extends BroadcastReceiver {

    private static final Logger logger = LoggerFactory.getLogger(AlarmManagerBroadcastReceiver.class);

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(34412)) {
            if ((ListenerUtil.mutListener.listen(34401) ? ((ListenerUtil.mutListener.listen(34400) ? (intent != null || intent.getAction() != null) : (intent != null && intent.getAction() != null)) || intent.getAction().equals(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED)) : ((ListenerUtil.mutListener.listen(34400) ? (intent != null || intent.getAction() != null) : (intent != null && intent.getAction() != null)) && intent.getAction().equals(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED)))) {
                if (!ListenerUtil.mutListener.listen(34411)) {
                    if ((ListenerUtil.mutListener.listen(34406) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34405) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34404) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34403) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34402) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        final PowerManager pm = (PowerManager) context.getApplicationContext().getSystemService(Context.POWER_SERVICE);
                        if (!ListenerUtil.mutListener.listen(34410)) {
                            if ((ListenerUtil.mutListener.listen(34407) ? (pm != null || pm.isPowerSaveMode()) : (pm != null && pm.isPowerSaveMode()))) {
                                if (!ListenerUtil.mutListener.listen(34409)) {
                                    logger.info("Power Save Mode enabled");
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(34408)) {
                                    logger.info("Power Save Mode disabled");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
