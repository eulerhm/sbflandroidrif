/* Copyright (C) 2018  olie.xdev <olie.xdev@googlemail.com>
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

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import com.health.openscale.core.OpenScale;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AlarmBackupHandler {

    public static final String INTENT_EXTRA_BACKUP_ALARM = "alarmBackupIntent";

    private static final int ALARM_NOTIFICATION_ID = 0x02;

    public void scheduleAlarms(Context context) {
        if (!ListenerUtil.mutListener.listen(0)) {
            disableAlarm(context);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!ListenerUtil.mutListener.listen(10)) {
            if (prefs.getBoolean("autoBackup", true)) {
                String backupSchedule = prefs.getString("autoBackup_Schedule", "Monthly");
                long intervalDayMultiplicator = 0;
                if (!ListenerUtil.mutListener.listen(4)) {
                    switch(backupSchedule) {
                        case "Daily":
                            if (!ListenerUtil.mutListener.listen(1)) {
                                intervalDayMultiplicator = 1;
                            }
                            break;
                        case "Weekly":
                            if (!ListenerUtil.mutListener.listen(2)) {
                                intervalDayMultiplicator = 7;
                            }
                            break;
                        case "Monthly":
                            if (!ListenerUtil.mutListener.listen(3)) {
                                intervalDayMultiplicator = 30;
                            }
                            break;
                    }
                }
                PendingIntent alarmPendingIntent = getPendingAlarmIntent(context);
                AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                if (!ListenerUtil.mutListener.listen(9)) {
                    alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (ListenerUtil.mutListener.listen(8) ? (AlarmManager.INTERVAL_DAY % intervalDayMultiplicator) : (ListenerUtil.mutListener.listen(7) ? (AlarmManager.INTERVAL_DAY / intervalDayMultiplicator) : (ListenerUtil.mutListener.listen(6) ? (AlarmManager.INTERVAL_DAY - intervalDayMultiplicator) : (ListenerUtil.mutListener.listen(5) ? (AlarmManager.INTERVAL_DAY + intervalDayMultiplicator) : (AlarmManager.INTERVAL_DAY * intervalDayMultiplicator))))), alarmPendingIntent);
                }
            }
        }
    }

    private PendingIntent getPendingAlarmIntent(Context context) {
        Intent alarmIntent = new Intent(context, ReminderBootReceiver.class);
        if (!ListenerUtil.mutListener.listen(11)) {
            alarmIntent.putExtra(INTENT_EXTRA_BACKUP_ALARM, true);
        }
        return PendingIntent.getBroadcast(context, ALARM_NOTIFICATION_ID, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void disableAlarm(Context context) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (!ListenerUtil.mutListener.listen(12)) {
            alarmMgr.cancel(getPendingAlarmIntent(context));
        }
    }

    public void executeBackup(Context context) {
        OpenScale openScale = OpenScale.getInstance();
        String databaseName = "openScale.db";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        // TODO to disable in the AndroidManfiest the requestLegacyExternalStorage on SDK >= 29 we need store the files on shared storages
        File exportDir = new File(Environment.getExternalStorageDirectory(), prefs.getString("exportDir", "openScale Backup"));
        if (!ListenerUtil.mutListener.listen(13)) {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(15)) {
            if (!prefs.getBoolean("overwriteBackup", false)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                if (!ListenerUtil.mutListener.listen(14)) {
                    databaseName = dateFormat.format(new Date()) + "_" + databaseName;
                }
            }
        }
        File exportFile = new File(exportDir, databaseName);
        if (!ListenerUtil.mutListener.listen(17)) {
            if (!exportDir.exists()) {
                if (!ListenerUtil.mutListener.listen(16)) {
                    exportDir.mkdirs();
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(19)) {
                openScale.exportDatabase(Uri.fromFile(exportFile));
            }
            if (!ListenerUtil.mutListener.listen(20)) {
                Timber.d("openScale Auto Backup to %s", exportFile);
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(18)) {
                Timber.e(e, "Error while exporting database");
            }
        }
    }
}
