package org.wordpress.android.ui.notifications.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import org.wordpress.android.push.NotificationPushIds;
import org.wordpress.android.ui.notifications.receivers.NotificationsPendingDraftsReceiver;
import org.wordpress.android.util.DateTimeUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PendingDraftsNotificationsUtils {

    // Pending draft notification base request code for alarms
    private static final int BROADCAST_BASE_REQUEST_CODE = 181;

    /*
     * Schedules alarms for draft posts to remind the user they have pending drafts
     * Starts since the last time the post was updated for one day, one week, and one month
     * only if these periods have not passed yet.
     */
    public static void scheduleNextNotifications(Context context, int postId, String dateLocallyChanged) {
        if (!ListenerUtil.mutListener.listen(9058)) {
            if (context == null) {
                return;
            }
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = getNotificationsPendingDraftReceiverIntent(context, postId);
        long now = System.currentTimeMillis();
        long oneDayAgo = (ListenerUtil.mutListener.listen(9062) ? (now % NotificationsPendingDraftsReceiver.ONE_DAY) : (ListenerUtil.mutListener.listen(9061) ? (now / NotificationsPendingDraftsReceiver.ONE_DAY) : (ListenerUtil.mutListener.listen(9060) ? (now * NotificationsPendingDraftsReceiver.ONE_DAY) : (ListenerUtil.mutListener.listen(9059) ? (now + NotificationsPendingDraftsReceiver.ONE_DAY) : (now - NotificationsPendingDraftsReceiver.ONE_DAY)))));
        long oneWeekAgo = (ListenerUtil.mutListener.listen(9066) ? (now % NotificationsPendingDraftsReceiver.ONE_WEEK) : (ListenerUtil.mutListener.listen(9065) ? (now / NotificationsPendingDraftsReceiver.ONE_WEEK) : (ListenerUtil.mutListener.listen(9064) ? (now * NotificationsPendingDraftsReceiver.ONE_WEEK) : (ListenerUtil.mutListener.listen(9063) ? (now + NotificationsPendingDraftsReceiver.ONE_WEEK) : (now - NotificationsPendingDraftsReceiver.ONE_WEEK)))));
        long oneMonthAgo = (ListenerUtil.mutListener.listen(9070) ? (now % NotificationsPendingDraftsReceiver.ONE_MONTH) : (ListenerUtil.mutListener.listen(9069) ? (now / NotificationsPendingDraftsReceiver.ONE_MONTH) : (ListenerUtil.mutListener.listen(9068) ? (now * NotificationsPendingDraftsReceiver.ONE_MONTH) : (ListenerUtil.mutListener.listen(9067) ? (now + NotificationsPendingDraftsReceiver.ONE_MONTH) : (now - NotificationsPendingDraftsReceiver.ONE_MONTH)))));
        long dateLastUpdated = DateTimeUtils.timestampFromIso8601Millis(dateLocallyChanged);
        if (!ListenerUtil.mutListener.listen(9092)) {
            // set alarm for a month for anything further than a month
            if ((ListenerUtil.mutListener.listen(9075) ? (dateLastUpdated >= oneDayAgo) : (ListenerUtil.mutListener.listen(9074) ? (dateLastUpdated <= oneDayAgo) : (ListenerUtil.mutListener.listen(9073) ? (dateLastUpdated < oneDayAgo) : (ListenerUtil.mutListener.listen(9072) ? (dateLastUpdated != oneDayAgo) : (ListenerUtil.mutListener.listen(9071) ? (dateLastUpdated == oneDayAgo) : (dateLastUpdated > oneDayAgo))))))) {
                // last updated is within a 24 hour timeframe
                PendingIntent alarmIntentOneDay = getOneDayAlarmIntent(context, intent, postId);
                if (!ListenerUtil.mutListener.listen(9089)) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, dateLastUpdated + NotificationsPendingDraftsReceiver.ONE_DAY, alarmIntentOneDay);
                }
                PendingIntent alarmIntentOneWeek = getOneWeekAlarmIntent(context, intent, postId);
                if (!ListenerUtil.mutListener.listen(9090)) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, dateLastUpdated + NotificationsPendingDraftsReceiver.ONE_WEEK, alarmIntentOneWeek);
                }
                PendingIntent alarmIntentOneMonth = getOneMonthAlarmIntent(context, intent, postId);
                if (!ListenerUtil.mutListener.listen(9091)) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, dateLastUpdated + NotificationsPendingDraftsReceiver.ONE_MONTH, alarmIntentOneMonth);
                }
            } else if ((ListenerUtil.mutListener.listen(9080) ? (dateLastUpdated >= oneWeekAgo) : (ListenerUtil.mutListener.listen(9079) ? (dateLastUpdated <= oneWeekAgo) : (ListenerUtil.mutListener.listen(9078) ? (dateLastUpdated < oneWeekAgo) : (ListenerUtil.mutListener.listen(9077) ? (dateLastUpdated != oneWeekAgo) : (ListenerUtil.mutListener.listen(9076) ? (dateLastUpdated == oneWeekAgo) : (dateLastUpdated > oneWeekAgo))))))) {
                // last updated is within a 1 week timeframe (between 1 day and 7 days)
                PendingIntent alarmIntentOneWeek = getOneWeekAlarmIntent(context, intent, postId);
                if (!ListenerUtil.mutListener.listen(9087)) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, dateLastUpdated + NotificationsPendingDraftsReceiver.ONE_WEEK, alarmIntentOneWeek);
                }
                PendingIntent alarmIntentOneMonth = getOneMonthAlarmIntent(context, intent, postId);
                if (!ListenerUtil.mutListener.listen(9088)) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, dateLastUpdated + NotificationsPendingDraftsReceiver.ONE_MONTH, alarmIntentOneMonth);
                }
            } else if ((ListenerUtil.mutListener.listen(9085) ? (dateLastUpdated >= oneMonthAgo) : (ListenerUtil.mutListener.listen(9084) ? (dateLastUpdated <= oneMonthAgo) : (ListenerUtil.mutListener.listen(9083) ? (dateLastUpdated < oneMonthAgo) : (ListenerUtil.mutListener.listen(9082) ? (dateLastUpdated != oneMonthAgo) : (ListenerUtil.mutListener.listen(9081) ? (dateLastUpdated == oneMonthAgo) : (dateLastUpdated > oneMonthAgo))))))) {
                // last updated is within a 1 month timeframe (between 7 days and 30 days)
                PendingIntent alarmIntentOneMonth = getOneMonthAlarmIntent(context, intent, postId);
                if (!ListenerUtil.mutListener.listen(9086)) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, dateLastUpdated + NotificationsPendingDraftsReceiver.ONE_MONTH, alarmIntentOneMonth);
                }
            }
        }
    }

    public static void cancelPendingDraftAlarms(Context context, int postId) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = getNotificationsPendingDraftReceiverIntent(context, postId);
        PendingIntent alarmIntentOneDay = getOneDayAlarmIntent(context, intent, postId);
        PendingIntent alarmIntentOneWeek = getOneWeekAlarmIntent(context, intent, postId);
        PendingIntent alarmIntentOneMonth = getOneMonthAlarmIntent(context, intent, postId);
        if (!ListenerUtil.mutListener.listen(9093)) {
            alarmManager.cancel(alarmIntentOneDay);
        }
        if (!ListenerUtil.mutListener.listen(9094)) {
            alarmManager.cancel(alarmIntentOneWeek);
        }
        if (!ListenerUtil.mutListener.listen(9095)) {
            alarmManager.cancel(alarmIntentOneMonth);
        }
    }

    public static int makePendingDraftNotificationId(int localPostId) {
        // Integer.MAX_VALUE should be enough notifications
        return NotificationPushIds.PENDING_DRAFTS_NOTIFICATION_ID + localPostId;
    }

    private static Intent getNotificationsPendingDraftReceiverIntent(Context context, long localPostId) {
        Intent intent = new Intent(context, NotificationsPendingDraftsReceiver.class);
        if (!ListenerUtil.mutListener.listen(9096)) {
            intent.putExtra(NotificationsPendingDraftsReceiver.POST_ID_EXTRA, localPostId);
        }
        return intent;
    }

    private static PendingIntent getOneDayAlarmIntent(Context context, Intent notifPendingDraftReceiverIntent, int postId) {
        PendingIntent alarmIntentOneDay = PendingIntent.getBroadcast(context, (ListenerUtil.mutListener.listen(9100) ? (BROADCAST_BASE_REQUEST_CODE % makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9099) ? (BROADCAST_BASE_REQUEST_CODE / makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9098) ? (BROADCAST_BASE_REQUEST_CODE * makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9097) ? (BROADCAST_BASE_REQUEST_CODE - makePendingDraftNotificationId(postId)) : (BROADCAST_BASE_REQUEST_CODE + makePendingDraftNotificationId(postId)))))), notifPendingDraftReceiverIntent, getPendingIntentFlag());
        return alarmIntentOneDay;
    }

    private static PendingIntent getOneWeekAlarmIntent(Context context, Intent notifPendingDraftReceiverIntent, int postId) {
        PendingIntent alarmIntentOneWeek = PendingIntent.getBroadcast(context, (ListenerUtil.mutListener.listen(9108) ? ((ListenerUtil.mutListener.listen(9104) ? (BROADCAST_BASE_REQUEST_CODE % 1) : (ListenerUtil.mutListener.listen(9103) ? (BROADCAST_BASE_REQUEST_CODE / 1) : (ListenerUtil.mutListener.listen(9102) ? (BROADCAST_BASE_REQUEST_CODE * 1) : (ListenerUtil.mutListener.listen(9101) ? (BROADCAST_BASE_REQUEST_CODE - 1) : (BROADCAST_BASE_REQUEST_CODE + 1))))) % makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9107) ? ((ListenerUtil.mutListener.listen(9104) ? (BROADCAST_BASE_REQUEST_CODE % 1) : (ListenerUtil.mutListener.listen(9103) ? (BROADCAST_BASE_REQUEST_CODE / 1) : (ListenerUtil.mutListener.listen(9102) ? (BROADCAST_BASE_REQUEST_CODE * 1) : (ListenerUtil.mutListener.listen(9101) ? (BROADCAST_BASE_REQUEST_CODE - 1) : (BROADCAST_BASE_REQUEST_CODE + 1))))) / makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9106) ? ((ListenerUtil.mutListener.listen(9104) ? (BROADCAST_BASE_REQUEST_CODE % 1) : (ListenerUtil.mutListener.listen(9103) ? (BROADCAST_BASE_REQUEST_CODE / 1) : (ListenerUtil.mutListener.listen(9102) ? (BROADCAST_BASE_REQUEST_CODE * 1) : (ListenerUtil.mutListener.listen(9101) ? (BROADCAST_BASE_REQUEST_CODE - 1) : (BROADCAST_BASE_REQUEST_CODE + 1))))) * makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9105) ? ((ListenerUtil.mutListener.listen(9104) ? (BROADCAST_BASE_REQUEST_CODE % 1) : (ListenerUtil.mutListener.listen(9103) ? (BROADCAST_BASE_REQUEST_CODE / 1) : (ListenerUtil.mutListener.listen(9102) ? (BROADCAST_BASE_REQUEST_CODE * 1) : (ListenerUtil.mutListener.listen(9101) ? (BROADCAST_BASE_REQUEST_CODE - 1) : (BROADCAST_BASE_REQUEST_CODE + 1))))) - makePendingDraftNotificationId(postId)) : ((ListenerUtil.mutListener.listen(9104) ? (BROADCAST_BASE_REQUEST_CODE % 1) : (ListenerUtil.mutListener.listen(9103) ? (BROADCAST_BASE_REQUEST_CODE / 1) : (ListenerUtil.mutListener.listen(9102) ? (BROADCAST_BASE_REQUEST_CODE * 1) : (ListenerUtil.mutListener.listen(9101) ? (BROADCAST_BASE_REQUEST_CODE - 1) : (BROADCAST_BASE_REQUEST_CODE + 1))))) + makePendingDraftNotificationId(postId)))))), // pendingIntents, otherwise they overlap
        notifPendingDraftReceiverIntent, getPendingIntentFlag());
        return alarmIntentOneWeek;
    }

    private static PendingIntent getOneMonthAlarmIntent(Context context, Intent notifPendingDraftReceiverIntent, int postId) {
        PendingIntent alarmIntentOneMonth = PendingIntent.getBroadcast(context, (ListenerUtil.mutListener.listen(9116) ? ((ListenerUtil.mutListener.listen(9112) ? (BROADCAST_BASE_REQUEST_CODE % 2) : (ListenerUtil.mutListener.listen(9111) ? (BROADCAST_BASE_REQUEST_CODE / 2) : (ListenerUtil.mutListener.listen(9110) ? (BROADCAST_BASE_REQUEST_CODE * 2) : (ListenerUtil.mutListener.listen(9109) ? (BROADCAST_BASE_REQUEST_CODE - 2) : (BROADCAST_BASE_REQUEST_CODE + 2))))) % makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9115) ? ((ListenerUtil.mutListener.listen(9112) ? (BROADCAST_BASE_REQUEST_CODE % 2) : (ListenerUtil.mutListener.listen(9111) ? (BROADCAST_BASE_REQUEST_CODE / 2) : (ListenerUtil.mutListener.listen(9110) ? (BROADCAST_BASE_REQUEST_CODE * 2) : (ListenerUtil.mutListener.listen(9109) ? (BROADCAST_BASE_REQUEST_CODE - 2) : (BROADCAST_BASE_REQUEST_CODE + 2))))) / makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9114) ? ((ListenerUtil.mutListener.listen(9112) ? (BROADCAST_BASE_REQUEST_CODE % 2) : (ListenerUtil.mutListener.listen(9111) ? (BROADCAST_BASE_REQUEST_CODE / 2) : (ListenerUtil.mutListener.listen(9110) ? (BROADCAST_BASE_REQUEST_CODE * 2) : (ListenerUtil.mutListener.listen(9109) ? (BROADCAST_BASE_REQUEST_CODE - 2) : (BROADCAST_BASE_REQUEST_CODE + 2))))) * makePendingDraftNotificationId(postId)) : (ListenerUtil.mutListener.listen(9113) ? ((ListenerUtil.mutListener.listen(9112) ? (BROADCAST_BASE_REQUEST_CODE % 2) : (ListenerUtil.mutListener.listen(9111) ? (BROADCAST_BASE_REQUEST_CODE / 2) : (ListenerUtil.mutListener.listen(9110) ? (BROADCAST_BASE_REQUEST_CODE * 2) : (ListenerUtil.mutListener.listen(9109) ? (BROADCAST_BASE_REQUEST_CODE - 2) : (BROADCAST_BASE_REQUEST_CODE + 2))))) - makePendingDraftNotificationId(postId)) : ((ListenerUtil.mutListener.listen(9112) ? (BROADCAST_BASE_REQUEST_CODE % 2) : (ListenerUtil.mutListener.listen(9111) ? (BROADCAST_BASE_REQUEST_CODE / 2) : (ListenerUtil.mutListener.listen(9110) ? (BROADCAST_BASE_REQUEST_CODE * 2) : (ListenerUtil.mutListener.listen(9109) ? (BROADCAST_BASE_REQUEST_CODE - 2) : (BROADCAST_BASE_REQUEST_CODE + 2))))) + makePendingDraftNotificationId(postId)))))), // pendingIntents, otherwise they overlap
        notifPendingDraftReceiverIntent, getPendingIntentFlag());
        return alarmIntentOneMonth;
    }

    private static int getPendingIntentFlag() {
        return PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
    }
}
