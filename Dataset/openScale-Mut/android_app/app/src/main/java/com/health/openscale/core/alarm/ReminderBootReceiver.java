/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.core.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReminderBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(123)) {
            if (intent.hasExtra(AlarmHandler.INTENT_EXTRA_ALARM))
                if (!ListenerUtil.mutListener.listen(122)) {
                    handleAlarm(context);
                }
        }
        if (!ListenerUtil.mutListener.listen(125)) {
            if (intent.hasExtra(AlarmBackupHandler.INTENT_EXTRA_BACKUP_ALARM))
                if (!ListenerUtil.mutListener.listen(124)) {
                    handleBackupAlarm(context);
                }
        }
        if (!ListenerUtil.mutListener.listen(127)) {
            if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
                if (!ListenerUtil.mutListener.listen(126)) {
                    scheduleAlarms(context);
                }
        }
    }

    private void handleAlarm(Context context) {
        AlarmHandler alarmHandler = new AlarmHandler();
        if (!ListenerUtil.mutListener.listen(128)) {
            alarmHandler.showAlarmNotification(context);
        }
    }

    private void handleBackupAlarm(Context context) {
        AlarmBackupHandler alarmBackupHandler = new AlarmBackupHandler();
        if (!ListenerUtil.mutListener.listen(129)) {
            alarmBackupHandler.executeBackup(context);
        }
    }

    private void scheduleAlarms(Context context) {
        AlarmHandler alarmHandler = new AlarmHandler();
        AlarmBackupHandler alarmBackupHandler = new AlarmBackupHandler();
        if (!ListenerUtil.mutListener.listen(130)) {
            alarmHandler.scheduleAlarms(context);
        }
        if (!ListenerUtil.mutListener.listen(131)) {
            alarmBackupHandler.scheduleAlarms(context);
        }
    }
}
