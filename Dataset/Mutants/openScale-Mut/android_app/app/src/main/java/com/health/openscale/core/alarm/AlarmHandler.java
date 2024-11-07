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

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import com.health.openscale.R;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.gui.MainActivity;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import androidx.core.app.NotificationCompat;
import timber.log.Timber;
import static android.content.Context.NOTIFICATION_SERVICE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AlarmHandler {

    public static final String INTENT_EXTRA_ALARM = "alarmIntent";

    private static final int ALARM_NOTIFICATION_ID = 0x01;

    public void scheduleAlarms(Context context) {
        AlarmEntryReader reader = AlarmEntryReader.construct(context);
        Set<AlarmEntry> alarmEntries = reader.getEntries();
        if (!ListenerUtil.mutListener.listen(72)) {
            disableAllAlarms(context);
        }
        if (!ListenerUtil.mutListener.listen(73)) {
            enableAlarms(context, alarmEntries);
        }
    }

    public void entryChanged(Context context, ScaleMeasurement data) {
        long dataMillis = data.getDateTime().getTime();
        Calendar dataTimestamp = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(74)) {
            dataTimestamp.setTimeInMillis(dataMillis);
        }
        if (!ListenerUtil.mutListener.listen(77)) {
            if (AlarmHandler.isSameDate(dataTimestamp, Calendar.getInstance())) {
                if (!ListenerUtil.mutListener.listen(75)) {
                    cancelAlarmNotification(context);
                }
                if (!ListenerUtil.mutListener.listen(76)) {
                    cancelAndRescheduleAlarmForNextWeek(context, dataTimestamp);
                }
            }
        }
    }

    private static boolean isSameDate(Calendar c1, Calendar c2) {
        int[] dateFields = { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH };
        if (!ListenerUtil.mutListener.listen(84)) {
            {
                long _loopCounter1 = 0;
                for (int dateField : dateFields) {
                    ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                    if (!ListenerUtil.mutListener.listen(83)) {
                        if ((ListenerUtil.mutListener.listen(82) ? (c1.get(dateField) >= c2.get(dateField)) : (ListenerUtil.mutListener.listen(81) ? (c1.get(dateField) <= c2.get(dateField)) : (ListenerUtil.mutListener.listen(80) ? (c1.get(dateField) > c2.get(dateField)) : (ListenerUtil.mutListener.listen(79) ? (c1.get(dateField) < c2.get(dateField)) : (ListenerUtil.mutListener.listen(78) ? (c1.get(dateField) == c2.get(dateField)) : (c1.get(dateField) != c2.get(dateField))))))))
                            return false;
                    }
                }
            }
        }
        return true;
    }

    private void enableAlarms(Context context, Set<AlarmEntry> alarmEntries) {
        if (!ListenerUtil.mutListener.listen(86)) {
            {
                long _loopCounter2 = 0;
                for (AlarmEntry alarmEntry : alarmEntries) {
                    ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                    if (!ListenerUtil.mutListener.listen(85)) {
                        enableAlarm(context, alarmEntry);
                    }
                }
            }
        }
    }

    private void enableAlarm(Context context, AlarmEntry alarmEntry) {
        int dayOfWeek = alarmEntry.getDayOfWeek();
        Calendar nextAlarmTimestamp = alarmEntry.getNextTimestamp();
        if (!ListenerUtil.mutListener.listen(87)) {
            setRepeatingAlarm(context, dayOfWeek, nextAlarmTimestamp);
        }
    }

    private void setRepeatingAlarm(Context context, int dayOfWeek, Calendar nextAlarmTimestamp) {
        if (!ListenerUtil.mutListener.listen(88)) {
            Timber.d("Set repeating alarm for %s", nextAlarmTimestamp.getTime());
        }
        PendingIntent alarmPendingIntent = getPendingAlarmIntent(context, dayOfWeek);
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (!ListenerUtil.mutListener.listen(93)) {
            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, nextAlarmTimestamp.getTimeInMillis(), (ListenerUtil.mutListener.listen(92) ? (AlarmManager.INTERVAL_DAY % 7) : (ListenerUtil.mutListener.listen(91) ? (AlarmManager.INTERVAL_DAY / 7) : (ListenerUtil.mutListener.listen(90) ? (AlarmManager.INTERVAL_DAY - 7) : (ListenerUtil.mutListener.listen(89) ? (AlarmManager.INTERVAL_DAY + 7) : (AlarmManager.INTERVAL_DAY * 7))))), alarmPendingIntent);
        }
    }

    private List<PendingIntent> getWeekdaysPendingAlarmIntent(Context context) {
        final int[] dayOfWeeks = { Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY };
        List<PendingIntent> pendingIntents = new LinkedList<>();
        if (!ListenerUtil.mutListener.listen(95)) {
            {
                long _loopCounter3 = 0;
                for (int dayOfWeek : dayOfWeeks) {
                    ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                    if (!ListenerUtil.mutListener.listen(94)) {
                        pendingIntents.add(getPendingAlarmIntent(context, dayOfWeek));
                    }
                }
            }
        }
        return pendingIntents;
    }

    private PendingIntent getPendingAlarmIntent(Context context, int dayOfWeek) {
        Intent alarmIntent = new Intent(context, ReminderBootReceiver.class);
        if (!ListenerUtil.mutListener.listen(96)) {
            alarmIntent.putExtra(INTENT_EXTRA_ALARM, true);
        }
        return PendingIntent.getBroadcast(context, dayOfWeek, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void disableAllAlarms(Context context) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        List<PendingIntent> pendingIntents = getWeekdaysPendingAlarmIntent(context);
        if (!ListenerUtil.mutListener.listen(98)) {
            {
                long _loopCounter4 = 0;
                for (PendingIntent pendingIntent : pendingIntents) {
                    ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                    if (!ListenerUtil.mutListener.listen(97)) {
                        alarmMgr.cancel(pendingIntent);
                    }
                }
            }
        }
    }

    private void cancelAndRescheduleAlarmForNextWeek(Context context, Calendar timestamp) {
        AlarmEntryReader reader = AlarmEntryReader.construct(context);
        Set<AlarmEntry> alarmEntries = reader.getEntries();
        if (!ListenerUtil.mutListener.listen(103)) {
            {
                long _loopCounter5 = 0;
                for (AlarmEntry entry : alarmEntries) {
                    ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                    Calendar nextAlarmTimestamp = entry.getNextTimestamp();
                    if (!ListenerUtil.mutListener.listen(102)) {
                        if (isSameDate(timestamp, nextAlarmTimestamp)) {
                            int dayOfWeek = entry.getDayOfWeek();
                            PendingIntent alarmPendingIntent = getPendingAlarmIntent(context, dayOfWeek);
                            AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                            if (!ListenerUtil.mutListener.listen(99)) {
                                alarmMgr.cancel(alarmPendingIntent);
                            }
                            if (!ListenerUtil.mutListener.listen(100)) {
                                nextAlarmTimestamp.add(Calendar.DATE, 7);
                            }
                            if (!ListenerUtil.mutListener.listen(101)) {
                                setRepeatingAlarm(context, dayOfWeek, nextAlarmTimestamp);
                            }
                        }
                    }
                }
            }
        }
    }

    public void showAlarmNotification(Context context) {
        AlarmEntryReader reader = AlarmEntryReader.construct(context);
        String notifyText = reader.getNotificationText();
        Intent notifyIntent = new Intent(context, MainActivity.class);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "openScale_notify");
        if (!ListenerUtil.mutListener.listen(110)) {
            if ((ListenerUtil.mutListener.listen(108) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(107) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(106) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(105) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(104) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel("openScale_notify", "openScale weight notification", NotificationManager.IMPORTANCE_DEFAULT);
                if (!ListenerUtil.mutListener.listen(109)) {
                    notificationManager.createNotificationChannel(channel);
                }
            }
        }
        Notification notification = mBuilder.setSmallIcon(R.drawable.ic_launcher_openscale).setContentTitle(context.getString(R.string.app_name)).setContentText(notifyText).setAutoCancel(true).setContentIntent(notifyPendingIntent).build();
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(111)) {
            mNotifyMgr.notify(ALARM_NOTIFICATION_ID, notification);
        }
    }

    private void cancelAlarmNotification(Context context) {
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(121)) {
            if ((ListenerUtil.mutListener.listen(116) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(115) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(114) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(113) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(112) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                StatusBarNotification[] activeNotifications = mNotifyMgr.getActiveNotifications();
                if (!ListenerUtil.mutListener.listen(120)) {
                    {
                        long _loopCounter6 = 0;
                        for (StatusBarNotification notification : activeNotifications) {
                            ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                            if (!ListenerUtil.mutListener.listen(119)) {
                                if (notification.getId() == ALARM_NOTIFICATION_ID)
                                    if (!ListenerUtil.mutListener.listen(118)) {
                                        mNotifyMgr.cancel(ALARM_NOTIFICATION_ID);
                                    }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(117)) {
                    mNotifyMgr.cancel(ALARM_NOTIFICATION_ID);
                }
            }
        }
    }
}
