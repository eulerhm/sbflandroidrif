package com.ichi2.anki.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.CollectionHelper;
import com.ichi2.anki.Preferences;
import com.ichi2.anki.R;
import com.ichi2.anki.UIUtils;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.DeckConfig;
import com.ichi2.libanki.utils.Time;
import com.ichi2.utils.Permissions;
import com.ichi2.utils.JSONObject;
import java.util.Calendar;
import androidx.annotation.NonNull;
import timber.log.Timber;
import static com.ichi2.anki.DeckOptions.reminderToCalendar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BootService extends BroadcastReceiver {

    /**
     * This service is also run when the app is started (from {@link com.ichi2.anki.AnkiDroidApp},
     * so we need to make sure that it isn't run twice.
     */
    private static boolean sWasRun = false;

    private boolean mFailedToShowNotifications = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(3133)) {
            if (sWasRun) {
                if (!ListenerUtil.mutListener.listen(3132)) {
                    Timber.d("BootService - Already run");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3135)) {
            if (!Permissions.hasStorageAccessPermission(context)) {
                if (!ListenerUtil.mutListener.listen(3134)) {
                    Timber.w("Boot Service did not execute - no permissions");
                }
                return;
            }
        }
        // There are cases where the app is installed, and we have access, but nothing exist yet
        Collection col = getColSafe(context);
        if (!ListenerUtil.mutListener.listen(3138)) {
            if ((ListenerUtil.mutListener.listen(3136) ? (col == null && col.getDecks() == null) : (col == null || col.getDecks() == null))) {
                if (!ListenerUtil.mutListener.listen(3137)) {
                    Timber.w("Boot Service did not execute - error loading collection");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3139)) {
            Timber.i("Executing Boot Service");
        }
        if (!ListenerUtil.mutListener.listen(3140)) {
            catchAlarmManagerErrors(context, () -> scheduleDeckReminder(context));
        }
        if (!ListenerUtil.mutListener.listen(3141)) {
            catchAlarmManagerErrors(context, () -> scheduleNotification(col.getTime(), context));
        }
        if (!ListenerUtil.mutListener.listen(3142)) {
            mFailedToShowNotifications = false;
        }
        if (!ListenerUtil.mutListener.listen(3143)) {
            sWasRun = true;
        }
    }

    private void catchAlarmManagerErrors(@NonNull Context context, @NonNull Runnable runnable) {
        // We warn the user if they breach this limit
        Integer error = null;
        try {
            if (!ListenerUtil.mutListener.listen(3146)) {
                runnable.run();
            }
        } catch (SecurityException ex) {
            if (!ListenerUtil.mutListener.listen(3144)) {
                error = R.string.boot_service_too_many_notifications;
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(3145)) {
                error = R.string.boot_service_failed_to_schedule_notifications;
            }
        }
        if (!ListenerUtil.mutListener.listen(3150)) {
            if (error != null) {
                if (!ListenerUtil.mutListener.listen(3148)) {
                    if (!mFailedToShowNotifications) {
                        if (!ListenerUtil.mutListener.listen(3147)) {
                            UIUtils.showThemedToast(context, context.getString(error), false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3149)) {
                    mFailedToShowNotifications = true;
                }
            }
        }
    }

    private Collection getColSafe(Context context) {
        // getInstance().getColSafe
        try {
            return CollectionHelper.getInstance().getCol(context);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(3151)) {
                Timber.e(e, "Failed to get collection for boot service - possibly media ejecting");
            }
            return null;
        }
    }

    private void scheduleDeckReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (!ListenerUtil.mutListener.listen(3155)) {
            {
                long _loopCounter82 = 0;
                for (DeckConfig deckConfiguration : CollectionHelper.getInstance().getCol(context).getDecks().allConf()) {
                    ListenerUtil.loopListener.listen("_loopCounter82", ++_loopCounter82);
                    Collection col = CollectionHelper.getInstance().getCol(context);
                    if (!ListenerUtil.mutListener.listen(3154)) {
                        if (deckConfiguration.has("reminder")) {
                            final JSONObject reminder = deckConfiguration.getJSONObject("reminder");
                            if (!ListenerUtil.mutListener.listen(3153)) {
                                if (reminder.getBoolean("enabled")) {
                                    final PendingIntent reminderIntent = PendingIntent.getBroadcast(context, (int) deckConfiguration.getLong("id"), new Intent(context, ReminderService.class).putExtra(ReminderService.EXTRA_DECK_OPTION_ID, deckConfiguration.getLong("id")), 0);
                                    final Calendar calendar = reminderToCalendar(col.getTime(), reminder);
                                    if (!ListenerUtil.mutListener.listen(3152)) {
                                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, reminderIntent);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void scheduleNotification(Time time, Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        SharedPreferences sp = AnkiDroidApp.getSharedPrefs(context);
        if (!ListenerUtil.mutListener.listen(3161)) {
            // Don't schedule a notification if the due reminders setting is not enabled
            if ((ListenerUtil.mutListener.listen(3160) ? (Integer.parseInt(sp.getString("minimumCardsDueForNotification", Integer.toString(Preferences.PENDING_NOTIFICATIONS_ONLY))) <= Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(3159) ? (Integer.parseInt(sp.getString("minimumCardsDueForNotification", Integer.toString(Preferences.PENDING_NOTIFICATIONS_ONLY))) > Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(3158) ? (Integer.parseInt(sp.getString("minimumCardsDueForNotification", Integer.toString(Preferences.PENDING_NOTIFICATIONS_ONLY))) < Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(3157) ? (Integer.parseInt(sp.getString("minimumCardsDueForNotification", Integer.toString(Preferences.PENDING_NOTIFICATIONS_ONLY))) != Preferences.PENDING_NOTIFICATIONS_ONLY) : (ListenerUtil.mutListener.listen(3156) ? (Integer.parseInt(sp.getString("minimumCardsDueForNotification", Integer.toString(Preferences.PENDING_NOTIFICATIONS_ONLY))) == Preferences.PENDING_NOTIFICATIONS_ONLY) : (Integer.parseInt(sp.getString("minimumCardsDueForNotification", Integer.toString(Preferences.PENDING_NOTIFICATIONS_ONLY))) >= Preferences.PENDING_NOTIFICATIONS_ONLY))))))) {
                return;
            }
        }
        final Calendar calendar = time.calendar();
        if (!ListenerUtil.mutListener.listen(3162)) {
            calendar.set(Calendar.HOUR_OF_DAY, sp.getInt("dayOffset", 0));
        }
        if (!ListenerUtil.mutListener.listen(3163)) {
            calendar.set(Calendar.MINUTE, 0);
        }
        if (!ListenerUtil.mutListener.listen(3164)) {
            calendar.set(Calendar.SECOND, 0);
        }
        final PendingIntent notificationIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, NotificationService.class), 0);
        if (!ListenerUtil.mutListener.listen(3165)) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, notificationIntent);
        }
    }
}
