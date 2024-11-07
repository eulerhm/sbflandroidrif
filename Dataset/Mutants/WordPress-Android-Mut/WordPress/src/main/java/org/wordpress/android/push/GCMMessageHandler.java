package org.wordpress.android.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import org.apache.commons.text.StringEscapeUtils;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.models.Note;
import org.wordpress.android.ui.main.WPMainActivity;
import org.wordpress.android.ui.notifications.NotificationEvents;
import org.wordpress.android.ui.notifications.NotificationsListFragment;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.notifications.utils.NotificationsActions;
import org.wordpress.android.ui.notifications.utils.NotificationsUtils;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.ImageUtils;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import static org.wordpress.android.push.GCMMessageService.EXTRA_VOICE_OR_INLINE_REPLY;
import static org.wordpress.android.push.GCMMessageService.PUSH_ARG_NOTE_ID;
import static org.wordpress.android.push.NotificationPushIds.AUTH_PUSH_NOTIFICATION_ID;
import static org.wordpress.android.push.NotificationPushIds.GROUP_NOTIFICATION_ID;
import static org.wordpress.android.push.NotificationPushIds.PUSH_NOTIFICATION_ID;
import static org.wordpress.android.push.NotificationPushIds.ZENDESK_PUSH_NOTIFICATION_ID;
import static org.wordpress.android.push.NotificationType.UNKNOWN_NOTE;
import static org.wordpress.android.push.NotificationsProcessingService.ARG_NOTIFICATION_TYPE;
import static org.wordpress.android.ui.notifications.services.NotificationsUpdateServiceStarter.IS_TAPPED_ON_NOTIFICATION;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class GCMMessageHandler {

    private static final String NOTIFICATION_GROUP_KEY = "notification_group_key";

    private static final int AUTH_PUSH_REQUEST_CODE_APPROVE = 0;

    private static final int AUTH_PUSH_REQUEST_CODE_IGNORE = 1;

    private static final int AUTH_PUSH_REQUEST_CODE_OPEN_DIALOG = 2;

    private static final int MAX_INBOX_ITEMS = 5;

    private static final String PUSH_ARG_TYPE = "type";

    private static final String PUSH_ARG_USER = "user";

    private static final String PUSH_ARG_TITLE = "title";

    private static final String PUSH_ARG_MSG = "msg";

    private static final String PUSH_TYPE_COMMENT = "c";

    private static final String PUSH_TYPE_LIKE = "like";

    private static final String PUSH_TYPE_COMMENT_LIKE = "comment_like";

    private static final String PUSH_TYPE_AUTOMATTCHER = "automattcher";

    private static final String PUSH_TYPE_FOLLOW = "follow";

    private static final String PUSH_TYPE_REBLOG = "reblog";

    private static final String PUSH_TYPE_PUSH_AUTH = "push_auth";

    private static final String PUSH_TYPE_BADGE_RESET = "badge-reset";

    private static final String PUSH_TYPE_NOTE_DELETE = "note-delete";

    private static final String PUSH_TYPE_TEST_NOTE = "push_test";

    static final String PUSH_TYPE_ZENDESK = "zendesk";

    private static final String KEY_CATEGORY_COMMENT_LIKE = "comment-like";

    private static final String KEY_CATEGORY_COMMENT_REPLY = "comment-reply";

    private static final String KEY_CATEGORY_COMMENT_MODERATE = "comment-moderate";

    // Add to the analytics properties map a subset of the push notification payload.
    private static final String[] PROPERTIES_TO_COPY_INTO_ANALYTICS = { PUSH_ARG_NOTE_ID, PUSH_ARG_TYPE, "blog_id", "post_id", "comment_id" };

    private final ArrayMap<Integer, Bundle> mActiveNotificationsMap;

    private final NotificationHelper mNotificationHelper;

    @Inject
    GCMMessageHandler(SystemNotificationsTracker systemNotificationsTracker) {
        mActiveNotificationsMap = new ArrayMap<>();
        mNotificationHelper = new NotificationHelper(this, systemNotificationsTracker);
    }

    synchronized void rebuildAndUpdateNotificationsOnSystemBarForThisNote(Context context, String noteId) {
        if (!ListenerUtil.mutListener.listen(2728)) {
            if ((ListenerUtil.mutListener.listen(2724) ? (mActiveNotificationsMap.size() >= 0) : (ListenerUtil.mutListener.listen(2723) ? (mActiveNotificationsMap.size() <= 0) : (ListenerUtil.mutListener.listen(2722) ? (mActiveNotificationsMap.size() < 0) : (ListenerUtil.mutListener.listen(2721) ? (mActiveNotificationsMap.size() != 0) : (ListenerUtil.mutListener.listen(2720) ? (mActiveNotificationsMap.size() == 0) : (mActiveNotificationsMap.size() > 0))))))) {
                // using a copy of the ArrayMap to iterate over on, as we might need to modify the original array
                ArrayMap<Integer, Bundle> tmpMap = new ArrayMap(mActiveNotificationsMap);
                if (!ListenerUtil.mutListener.listen(2727)) {
                    {
                        long _loopCounter105 = 0;
                        for (Bundle noteBundle : tmpMap.values()) {
                            ListenerUtil.loopListener.listen("_loopCounter105", ++_loopCounter105);
                            if (!ListenerUtil.mutListener.listen(2726)) {
                                if (noteBundle.getString(PUSH_ARG_NOTE_ID, "").equals(noteId)) {
                                    if (!ListenerUtil.mutListener.listen(2725)) {
                                        mNotificationHelper.rebuildAndUpdateNotificationsOnSystemBar(context, noteBundle);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public synchronized void rebuildAndUpdateNotifsOnSystemBarForRemainingNote(Context context) {
        if (!ListenerUtil.mutListener.listen(2735)) {
            if ((ListenerUtil.mutListener.listen(2733) ? (mActiveNotificationsMap.size() >= 0) : (ListenerUtil.mutListener.listen(2732) ? (mActiveNotificationsMap.size() <= 0) : (ListenerUtil.mutListener.listen(2731) ? (mActiveNotificationsMap.size() < 0) : (ListenerUtil.mutListener.listen(2730) ? (mActiveNotificationsMap.size() != 0) : (ListenerUtil.mutListener.listen(2729) ? (mActiveNotificationsMap.size() == 0) : (mActiveNotificationsMap.size() > 0))))))) {
                Bundle remainingNote = mActiveNotificationsMap.values().iterator().next();
                if (!ListenerUtil.mutListener.listen(2734)) {
                    mNotificationHelper.rebuildAndUpdateNotificationsOnSystemBar(context, remainingNote);
                }
            }
        }
    }

    private synchronized Bundle getCurrentNoteBundleForNoteId(String noteId) {
        if (!ListenerUtil.mutListener.listen(2743)) {
            if ((ListenerUtil.mutListener.listen(2740) ? (mActiveNotificationsMap.size() >= 0) : (ListenerUtil.mutListener.listen(2739) ? (mActiveNotificationsMap.size() <= 0) : (ListenerUtil.mutListener.listen(2738) ? (mActiveNotificationsMap.size() < 0) : (ListenerUtil.mutListener.listen(2737) ? (mActiveNotificationsMap.size() != 0) : (ListenerUtil.mutListener.listen(2736) ? (mActiveNotificationsMap.size() == 0) : (mActiveNotificationsMap.size() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(2742)) {
                    {
                        long _loopCounter106 = 0;
                        // get the corresponding bundle for this noteId
                        for (Bundle noteBundle : mActiveNotificationsMap.values()) {
                            ListenerUtil.loopListener.listen("_loopCounter106", ++_loopCounter106);
                            if (!ListenerUtil.mutListener.listen(2741)) {
                                if (noteBundle.getString(PUSH_ARG_NOTE_ID, "").equals(noteId)) {
                                    return noteBundle;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    synchronized void clearNotifications() {
        List<Integer> removedNotifications = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2746)) {
            {
                long _loopCounter107 = 0;
                for (Integer pushId : mActiveNotificationsMap.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter107", ++_loopCounter107);
                    if (!ListenerUtil.mutListener.listen(2745)) {
                        // don't cancel or remove the AUTH notification if it exists
                        if (!pushId.equals(AUTH_PUSH_NOTIFICATION_ID)) {
                            if (!ListenerUtil.mutListener.listen(2744)) {
                                removedNotifications.add(pushId);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2747)) {
            mActiveNotificationsMap.removeAll(removedNotifications);
        }
    }

    public synchronized int getNotificationsCount() {
        return mActiveNotificationsMap.size();
    }

    synchronized boolean hasNotifications() {
        return !mActiveNotificationsMap.isEmpty();
    }

    // the user has dismissed the app by swiping it off the screen
    synchronized void removeNotification(int notificationId) {
        if (!ListenerUtil.mutListener.listen(2748)) {
            mActiveNotificationsMap.remove(notificationId);
        }
    }

    // Removes a specific notification from the system bar
    public synchronized void removeNotificationWithNoteIdFromSystemBar(Context context, String noteID) {
        if (!ListenerUtil.mutListener.listen(2751)) {
            if ((ListenerUtil.mutListener.listen(2750) ? ((ListenerUtil.mutListener.listen(2749) ? (context == null && TextUtils.isEmpty(noteID)) : (context == null || TextUtils.isEmpty(noteID))) && !hasNotifications()) : ((ListenerUtil.mutListener.listen(2749) ? (context == null && TextUtils.isEmpty(noteID)) : (context == null || TextUtils.isEmpty(noteID))) || !hasNotifications()))) {
                return;
            }
        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        List<Integer> removedNotifications = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2755)) {
            {
                long _loopCounter108 = 0;
                // mActiveNotificationsMap as we find it suitable
                for (Entry<Integer, Bundle> row : mActiveNotificationsMap.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter108", ++_loopCounter108);
                    Integer pushId = row.getKey();
                    Bundle noteBundle = row.getValue();
                    if (!ListenerUtil.mutListener.listen(2754)) {
                        if (noteBundle.getString(PUSH_ARG_NOTE_ID, "").equals(noteID)) {
                            if (!ListenerUtil.mutListener.listen(2752)) {
                                notificationManager.cancel(pushId);
                            }
                            if (!ListenerUtil.mutListener.listen(2753)) {
                                removedNotifications.add(pushId);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2756)) {
            mActiveNotificationsMap.removeAll(removedNotifications);
        }
        if (!ListenerUtil.mutListener.listen(2758)) {
            if (mActiveNotificationsMap.size() == 0) {
                if (!ListenerUtil.mutListener.listen(2757)) {
                    notificationManager.cancel(GROUP_NOTIFICATION_ID);
                }
            }
        }
    }

    // Removes all app notifications from the system bar
    public synchronized void removeAllNotifications(Context context) {
        if (!ListenerUtil.mutListener.listen(2760)) {
            if ((ListenerUtil.mutListener.listen(2759) ? (context == null && !hasNotifications()) : (context == null || !hasNotifications()))) {
                return;
            }
        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        List<Integer> removedNotifications = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2764)) {
            {
                long _loopCounter109 = 0;
                for (Integer pushId : mActiveNotificationsMap.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter109", ++_loopCounter109);
                    if (!ListenerUtil.mutListener.listen(2763)) {
                        // don't cancel or remove the AUTH notification if it exists
                        if (!pushId.equals(AUTH_PUSH_NOTIFICATION_ID)) {
                            if (!ListenerUtil.mutListener.listen(2761)) {
                                notificationManager.cancel(pushId);
                            }
                            if (!ListenerUtil.mutListener.listen(2762)) {
                                removedNotifications.add(pushId);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2765)) {
            mActiveNotificationsMap.removeAll(removedNotifications);
        }
        if (!ListenerUtil.mutListener.listen(2766)) {
            notificationManager.cancel(GROUP_NOTIFICATION_ID);
        }
    }

    public synchronized void remove2FANotification(Context context) {
        if (!ListenerUtil.mutListener.listen(2768)) {
            if ((ListenerUtil.mutListener.listen(2767) ? (context == null && !hasNotifications()) : (context == null || !hasNotifications()))) {
                return;
            }
        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (!ListenerUtil.mutListener.listen(2769)) {
            notificationManager.cancel(AUTH_PUSH_NOTIFICATION_ID);
        }
        if (!ListenerUtil.mutListener.listen(2770)) {
            mActiveNotificationsMap.remove(AUTH_PUSH_NOTIFICATION_ID);
        }
    }

    // NoteID is the ID if the note in WordPress
    public synchronized void bumpPushNotificationsTappedAnalytics(String noteID) {
        if (!ListenerUtil.mutListener.listen(2774)) {
            {
                long _loopCounter110 = 0;
                for (Bundle noteBundle : mActiveNotificationsMap.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter110", ++_loopCounter110);
                    if (!ListenerUtil.mutListener.listen(2773)) {
                        if (noteBundle.getString(PUSH_ARG_NOTE_ID, "").equals(noteID)) {
                            if (!ListenerUtil.mutListener.listen(2771)) {
                                bumpPushNotificationsAnalytics(Stat.PUSH_NOTIFICATION_TAPPED, noteBundle, null);
                            }
                            if (!ListenerUtil.mutListener.listen(2772)) {
                                AnalyticsTracker.flush();
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    // Mark all notifications as tapped
    public synchronized void bumpPushNotificationsTappedAllAnalytics() {
        if (!ListenerUtil.mutListener.listen(2776)) {
            {
                long _loopCounter111 = 0;
                for (Bundle noteBundle : mActiveNotificationsMap.values()) {
                    ListenerUtil.loopListener.listen("_loopCounter111", ++_loopCounter111);
                    if (!ListenerUtil.mutListener.listen(2775)) {
                        bumpPushNotificationsAnalytics(Stat.PUSH_NOTIFICATION_TAPPED, noteBundle, null);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2777)) {
            AnalyticsTracker.flush();
        }
    }

    private void bumpPushNotificationsAnalytics(Stat stat, Bundle noteBundle, Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(2778)) {
            // Bump Analytics for PNs if "Show notifications" setting is checked (default). Skip otherwise.
            if (!NotificationsUtils.isNotificationsEnabled(WordPress.getContext())) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2780)) {
            if (properties == null) {
                if (!ListenerUtil.mutListener.listen(2779)) {
                    properties = new HashMap<>();
                }
            }
        }
        String notificationID = noteBundle.getString(PUSH_ARG_NOTE_ID, "");
        if (!ListenerUtil.mutListener.listen(2786)) {
            if (!TextUtils.isEmpty(notificationID)) {
                if (!ListenerUtil.mutListener.listen(2783)) {
                    {
                        long _loopCounter112 = 0;
                        for (String currentPropertyToCopy : PROPERTIES_TO_COPY_INTO_ANALYTICS) {
                            ListenerUtil.loopListener.listen("_loopCounter112", ++_loopCounter112);
                            if (!ListenerUtil.mutListener.listen(2782)) {
                                if (noteBundle.containsKey(currentPropertyToCopy)) {
                                    if (!ListenerUtil.mutListener.listen(2781)) {
                                        properties.put("push_notification_" + currentPropertyToCopy, noteBundle.get(currentPropertyToCopy));
                                    }
                                }
                            }
                        }
                    }
                }
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(WordPress.getContext());
                String lastRegisteredGCMToken = preferences.getString(NotificationsUtils.WPCOM_PUSH_DEVICE_TOKEN, null);
                if (!ListenerUtil.mutListener.listen(2784)) {
                    properties.put("push_notification_token", lastRegisteredGCMToken);
                }
                if (!ListenerUtil.mutListener.listen(2785)) {
                    AnalyticsTracker.track(stat, properties);
                }
            }
        }
    }

    private void addAuthPushNotificationToNotificationMap(Bundle data) {
        if (!ListenerUtil.mutListener.listen(2787)) {
            mActiveNotificationsMap.put(AUTH_PUSH_NOTIFICATION_ID, data);
        }
    }

    void handleDefaultPush(Context context, @NonNull Bundle data, long wpcomUserId) {
        if (!ListenerUtil.mutListener.listen(2788)) {
            mNotificationHelper.handleDefaultPush(context, data, wpcomUserId);
        }
    }

    void handleZendeskNotification(Context context) {
        if (!ListenerUtil.mutListener.listen(2789)) {
            mNotificationHelper.handleZendeskNotification(context);
        }
    }

    public static class NotificationHelper {

        private GCMMessageHandler mGCMMessageHandler;

        private SystemNotificationsTracker mSystemNotificationsTracker;

        NotificationHelper(GCMMessageHandler gCMMessageHandler, SystemNotificationsTracker systemNotificationsTracker) {
            if (!ListenerUtil.mutListener.listen(2790)) {
                mGCMMessageHandler = gCMMessageHandler;
            }
            if (!ListenerUtil.mutListener.listen(2791)) {
                mSystemNotificationsTracker = systemNotificationsTracker;
            }
        }

        void handleDefaultPush(Context context, @NonNull Bundle data, long wpcomUserId) {
            String pushUserId = data.getString(PUSH_ARG_USER);
            if (!ListenerUtil.mutListener.listen(2793)) {
                // pushUserId is always set server side, but better to double check it here.
                if (!String.valueOf(wpcomUserId).equals(pushUserId)) {
                    if (!ListenerUtil.mutListener.listen(2792)) {
                        AppLog.e(T.NOTIFS, "wpcom userId found in the app doesn't match with the ID in the PN. Aborting.");
                    }
                    return;
                }
            }
            String noteType = StringUtils.notNullStr(data.getString(PUSH_ARG_TYPE));
            if (!ListenerUtil.mutListener.listen(2796)) {
                // Check for wpcom auth push, if so we will process this push differently
                if (noteType.equals(PUSH_TYPE_PUSH_AUTH)) {
                    if (!ListenerUtil.mutListener.listen(2794)) {
                        mGCMMessageHandler.addAuthPushNotificationToNotificationMap(data);
                    }
                    if (!ListenerUtil.mutListener.listen(2795)) {
                        handlePushAuth(context, data);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(2798)) {
                if (noteType.equals(PUSH_TYPE_BADGE_RESET)) {
                    if (!ListenerUtil.mutListener.listen(2797)) {
                        handleBadgeResetPN(context, data);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(2800)) {
                if (noteType.equals(PUSH_TYPE_NOTE_DELETE)) {
                    if (!ListenerUtil.mutListener.listen(2799)) {
                        handleNoteDeletePN(context, data);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(2802)) {
                if (noteType.equals(PUSH_TYPE_TEST_NOTE)) {
                    if (!ListenerUtil.mutListener.listen(2801)) {
                        buildAndShowNotificationFromTestPushData(context, data);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(2803)) {
                buildAndShowNotificationFromNoteData(context, data);
            }
        }

        private void buildAndShowNotificationFromTestPushData(Context context, Bundle data) {
            if (!ListenerUtil.mutListener.listen(2805)) {
                if (data == null) {
                    if (!ListenerUtil.mutListener.listen(2804)) {
                        AppLog.e(T.NOTIFS, "Test push notification received without a valid Bundle!");
                    }
                    return;
                }
            }
            String title = context.getString(R.string.app_name);
            String message = StringEscapeUtils.unescapeHtml4(data.getString(PUSH_ARG_MSG));
            int pushId = PUSH_NOTIFICATION_ID + mGCMMessageHandler.mActiveNotificationsMap.size();
            if (!ListenerUtil.mutListener.listen(2806)) {
                mGCMMessageHandler.mActiveNotificationsMap.put(pushId, data);
            }
            Intent resultIntent = new Intent(context, WPMainActivity.class);
            if (!ListenerUtil.mutListener.listen(2807)) {
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            if (!ListenerUtil.mutListener.listen(2808)) {
                showSimpleNotification(context, title, message, resultIntent, pushId, NotificationType.TEST_NOTE);
            }
        }

        private void buildAndShowNotificationFromNoteData(Context context, Bundle data) {
            if (!ListenerUtil.mutListener.listen(2810)) {
                if (data == null) {
                    if (!ListenerUtil.mutListener.listen(2809)) {
                        AppLog.e(T.NOTIFS, "Push notification received without a valid Bundle!");
                    }
                    return;
                }
            }
            final String wpcomNoteID = data.getString(PUSH_ARG_NOTE_ID, "");
            if (!ListenerUtil.mutListener.listen(2812)) {
                if (TextUtils.isEmpty(wpcomNoteID)) {
                    if (!ListenerUtil.mutListener.listen(2811)) {
                        // At this point 'note_id' is always available in the notification bundle.
                        AppLog.e(T.NOTIFS, "Push notification received without a valid note_id in in payload!");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(2813)) {
                // Try to build the note object from the PN payload, and save it to the DB.
                NotificationsUtils.buildNoteObjectFromBundleAndSaveIt(data);
            }
            if (!ListenerUtil.mutListener.listen(2814)) {
                EventBus.getDefault().post(new NotificationEvents.NotificationsChanged(true));
            }
            String noteType = StringUtils.notNullStr(data.getString(PUSH_ARG_TYPE));
            String title = StringEscapeUtils.unescapeHtml4(data.getString(PUSH_ARG_TITLE));
            if (!ListenerUtil.mutListener.listen(2816)) {
                if (title == null) {
                    if (!ListenerUtil.mutListener.listen(2815)) {
                        title = context.getString(R.string.app_name);
                    }
                }
            }
            String message = StringEscapeUtils.unescapeHtml4(data.getString(PUSH_ARG_MSG));
            /*
             * if this has the same note_id as the previous notification, and the previous notification
             * was received within the last second, then skip showing it - this handles duplicate
             * notifications being shown due to the device being registered multiple times with different tokens.
             * (still investigating how this could happen - 21-Oct-13)
             *
             * this also handles the (rare) case where the user receives rapid-fire sub-second like notifications
             * due to sudden popularity (post gets added to FP and is liked by many people all at once, etc.),
             * which we also want to avoid since it would drain the battery and annoy the user
             *
             * NOTE: different comments on the same post will have a different note_id, but different likes
             * on the same post will have the same note_id, so don't assume that the note_id is unique
             */
            long thisTime = System.currentTimeMillis();
            if (!ListenerUtil.mutListener.listen(2828)) {
                if (AppPrefs.getLastPushNotificationWpcomNoteId().equals(wpcomNoteID)) {
                    long seconds = TimeUnit.MILLISECONDS.toSeconds((ListenerUtil.mutListener.listen(2820) ? (thisTime % AppPrefs.getLastPushNotificationTime()) : (ListenerUtil.mutListener.listen(2819) ? (thisTime / AppPrefs.getLastPushNotificationTime()) : (ListenerUtil.mutListener.listen(2818) ? (thisTime * AppPrefs.getLastPushNotificationTime()) : (ListenerUtil.mutListener.listen(2817) ? (thisTime + AppPrefs.getLastPushNotificationTime()) : (thisTime - AppPrefs.getLastPushNotificationTime()))))));
                    if (!ListenerUtil.mutListener.listen(2827)) {
                        if ((ListenerUtil.mutListener.listen(2825) ? (seconds >= 1) : (ListenerUtil.mutListener.listen(2824) ? (seconds > 1) : (ListenerUtil.mutListener.listen(2823) ? (seconds < 1) : (ListenerUtil.mutListener.listen(2822) ? (seconds != 1) : (ListenerUtil.mutListener.listen(2821) ? (seconds == 1) : (seconds <= 1))))))) {
                            if (!ListenerUtil.mutListener.listen(2826)) {
                                AppLog.w(T.NOTIFS, "skipped potential duplicate notification");
                            }
                            return;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2829)) {
                AppPrefs.setLastPushNotificationTime(thisTime);
            }
            if (!ListenerUtil.mutListener.listen(2830)) {
                AppPrefs.setLastPushNotificationWpcomNoteId(wpcomNoteID);
            }
            // Update notification content for the same noteId if it is already showing
            int pushId = getPushIdForWpcomeNoteID(wpcomNoteID);
            if (!ListenerUtil.mutListener.listen(2831)) {
                mGCMMessageHandler.mActiveNotificationsMap.put(pushId, data);
            }
            if (!ListenerUtil.mutListener.listen(2838)) {
                // Bump Analytics for PNs if "Show notifications" setting is checked (default). Skip otherwise.
                if (NotificationsUtils.isNotificationsEnabled(context)) {
                    Map<String, Object> properties = new HashMap<>();
                    if (!ListenerUtil.mutListener.listen(2835)) {
                        if (!TextUtils.isEmpty(noteType)) {
                            if (!ListenerUtil.mutListener.listen(2834)) {
                                // 'comment' and 'comment_pingback' types are sent in PN as type = "c"
                                if (noteType.equals(PUSH_TYPE_COMMENT)) {
                                    if (!ListenerUtil.mutListener.listen(2833)) {
                                        properties.put("notification_type", "comment");
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2832)) {
                                        properties.put("notification_type", noteType);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2836)) {
                        mGCMMessageHandler.bumpPushNotificationsAnalytics(Stat.PUSH_NOTIFICATION_RECEIVED, data, properties);
                    }
                    if (!ListenerUtil.mutListener.listen(2837)) {
                        AnalyticsTracker.flush();
                    }
                }
            }
            // Build the new notification, add group to support wearable stacking
            NotificationCompat.Builder builder = getNotificationBuilder(context, title, message);
            Bitmap largeIconBitmap = getLargeIconBitmap(context, data.getString("icon"), shouldCircularizeNoteIcon(noteType));
            if (!ListenerUtil.mutListener.listen(2840)) {
                if (largeIconBitmap != null) {
                    if (!ListenerUtil.mutListener.listen(2839)) {
                        builder.setLargeIcon(largeIconBitmap);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2841)) {
                // Always do this, since a note can be updated on the server after a PN is sent
                NotificationsActions.downloadNoteAndUpdateDB(wpcomNoteID, success -> showNotificationForNoteData(context, data, builder), error -> showNotificationForNoteData(context, data, builder));
            }
        }

        private void showNotificationForNoteData(Context context, Bundle noteData, NotificationCompat.Builder builder) {
            String noteType = StringUtils.notNullStr(noteData.getString(PUSH_ARG_TYPE));
            String wpcomNoteID = noteData.getString(PUSH_ARG_NOTE_ID, "");
            String message = StringEscapeUtils.unescapeHtml4(noteData.getString(PUSH_ARG_MSG));
            int pushId = getPushIdForWpcomeNoteID(wpcomNoteID);
            if (!ListenerUtil.mutListener.listen(2842)) {
                showSingleNotificationForBuilder(context, builder, noteType, wpcomNoteID, pushId, true);
            }
            if (!ListenerUtil.mutListener.listen(2843)) {
                // Do not need to play the sound again. We've already played it in the individual builder.
                showGroupNotificationForBuilder(context, builder, wpcomNoteID, message);
            }
        }

        private int getPushIdForWpcomeNoteID(String wpcomNoteID) {
            int pushId = 0;
            if (!ListenerUtil.mutListener.listen(2848)) {
                {
                    long _loopCounter113 = 0;
                    for (Integer id : mGCMMessageHandler.mActiveNotificationsMap.keySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter113", ++_loopCounter113);
                        if (!ListenerUtil.mutListener.listen(2844)) {
                            if (id == null) {
                                continue;
                            }
                        }
                        Bundle noteBundle = mGCMMessageHandler.mActiveNotificationsMap.get(id);
                        if (!ListenerUtil.mutListener.listen(2847)) {
                            if ((ListenerUtil.mutListener.listen(2845) ? (noteBundle != null || noteBundle.getString(PUSH_ARG_NOTE_ID, "").equals(wpcomNoteID)) : (noteBundle != null && noteBundle.getString(PUSH_ARG_NOTE_ID, "").equals(wpcomNoteID)))) {
                                if (!ListenerUtil.mutListener.listen(2846)) {
                                    pushId = id;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2855)) {
                if ((ListenerUtil.mutListener.listen(2853) ? (pushId >= 0) : (ListenerUtil.mutListener.listen(2852) ? (pushId <= 0) : (ListenerUtil.mutListener.listen(2851) ? (pushId > 0) : (ListenerUtil.mutListener.listen(2850) ? (pushId < 0) : (ListenerUtil.mutListener.listen(2849) ? (pushId != 0) : (pushId == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(2854)) {
                        pushId = PUSH_NOTIFICATION_ID + mGCMMessageHandler.mActiveNotificationsMap.size();
                    }
                }
            }
            return pushId;
        }

        private void showSimpleNotification(Context context, String title, String message, Intent resultIntent, int pushId, NotificationType notificationType) {
            NotificationCompat.Builder builder = getNotificationBuilder(context, title, message);
            if (!ListenerUtil.mutListener.listen(2856)) {
                showNotificationForBuilder(builder, context, resultIntent, pushId, true, notificationType);
            }
        }

        private void addActionsForCommentNotification(Context context, NotificationCompat.Builder builder, String noteId) {
            // Add some actions if this is a comment notification
            boolean areActionsSet = false;
            Note note = NotificationsTable.getNoteById(noteId);
            if (!ListenerUtil.mutListener.listen(2865)) {
                if (note != null) {
                    if (!ListenerUtil.mutListener.listen(2858)) {
                        // if note can be replied to, we'll always add this action first
                        if (note.canReply()) {
                            if (!ListenerUtil.mutListener.listen(2857)) {
                                addCommentReplyActionForCommentNotification(context, builder, noteId);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2863)) {
                        // if the comment is lacking approval, offer moderation actions
                        if (note.getCommentStatus() == CommentStatus.UNAPPROVED) {
                            if (!ListenerUtil.mutListener.listen(2862)) {
                                if (note.canModerate()) {
                                    if (!ListenerUtil.mutListener.listen(2861)) {
                                        addCommentApproveActionForCommentNotification(context, builder, noteId);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2860)) {
                                // LIKE can only be enabled for wp.com sites, so if this is a Jetpack site don't enable LIKEs
                                if (note.canLike()) {
                                    if (!ListenerUtil.mutListener.listen(2859)) {
                                        addCommentLikeActionForCommentNotification(context, builder, noteId);
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2864)) {
                        areActionsSet = true;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2867)) {
                // we can make at this point
                if (!areActionsSet) {
                    if (!ListenerUtil.mutListener.listen(2866)) {
                        addCommentReplyActionForCommentNotification(context, builder, noteId);
                    }
                }
            }
        }

        private void addCommentReplyActionForCommentNotification(Context context, NotificationCompat.Builder builder, String noteId) {
            // adding comment reply action
            Intent commentReplyIntent = getCommentActionReplyIntent(context, noteId);
            if (!ListenerUtil.mutListener.listen(2868)) {
                commentReplyIntent.addCategory(KEY_CATEGORY_COMMENT_REPLY);
            }
            if (!ListenerUtil.mutListener.listen(2869)) {
                commentReplyIntent.putExtra(NotificationsProcessingService.ARG_ACTION_TYPE, NotificationsProcessingService.ARG_ACTION_REPLY);
            }
            if (!ListenerUtil.mutListener.listen(2871)) {
                if (noteId != null) {
                    if (!ListenerUtil.mutListener.listen(2870)) {
                        commentReplyIntent.putExtra(NotificationsProcessingService.ARG_NOTE_ID, noteId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2872)) {
                commentReplyIntent.putExtra(NotificationsProcessingService.ARG_NOTE_BUNDLE, mGCMMessageHandler.getCurrentNoteBundleForNoteId(noteId));
            }
            PendingIntent commentReplyPendingIntent = getCommentActionPendingIntent(context, commentReplyIntent);
            // Using backward compatibility with NotificationCompat.
            String replyLabel = context.getString(R.string.reply);
            RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_OR_INLINE_REPLY).setLabel(replyLabel).build();
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_reply_white_24dp, context.getString(R.string.reply), commentReplyPendingIntent).addRemoteInput(remoteInput).build();
            if (!ListenerUtil.mutListener.listen(2873)) {
                // now add the action corresponding to direct-reply
                builder.addAction(action);
            }
        }

        private void addCommentLikeActionForCommentNotification(Context context, NotificationCompat.Builder builder, String noteId) {
            // adding comment like action
            Intent commentLikeIntent = getCommentActionIntent(context);
            if (!ListenerUtil.mutListener.listen(2874)) {
                commentLikeIntent.addCategory(KEY_CATEGORY_COMMENT_LIKE);
            }
            if (!ListenerUtil.mutListener.listen(2875)) {
                commentLikeIntent.putExtra(NotificationsProcessingService.ARG_ACTION_TYPE, NotificationsProcessingService.ARG_ACTION_LIKE);
            }
            if (!ListenerUtil.mutListener.listen(2877)) {
                if (noteId != null) {
                    if (!ListenerUtil.mutListener.listen(2876)) {
                        commentLikeIntent.putExtra(NotificationsProcessingService.ARG_NOTE_ID, noteId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2878)) {
                commentLikeIntent.putExtra(NotificationsProcessingService.ARG_NOTE_BUNDLE, mGCMMessageHandler.getCurrentNoteBundleForNoteId(noteId));
            }
            PendingIntent commentLikePendingIntent = getCommentActionPendingIntentForService(context, commentLikeIntent);
            if (!ListenerUtil.mutListener.listen(2879)) {
                builder.addAction(R.drawable.ic_star_white_24dp, context.getText(R.string.like), commentLikePendingIntent);
            }
        }

        private void addCommentApproveActionForCommentNotification(Context context, NotificationCompat.Builder builder, String noteId) {
            // adding comment approve action
            Intent commentApproveIntent = getCommentActionIntent(context);
            if (!ListenerUtil.mutListener.listen(2880)) {
                commentApproveIntent.addCategory(KEY_CATEGORY_COMMENT_MODERATE);
            }
            if (!ListenerUtil.mutListener.listen(2881)) {
                commentApproveIntent.putExtra(NotificationsProcessingService.ARG_ACTION_TYPE, NotificationsProcessingService.ARG_ACTION_APPROVE);
            }
            if (!ListenerUtil.mutListener.listen(2883)) {
                if (noteId != null) {
                    if (!ListenerUtil.mutListener.listen(2882)) {
                        commentApproveIntent.putExtra(NotificationsProcessingService.ARG_NOTE_ID, noteId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2884)) {
                commentApproveIntent.putExtra(NotificationsProcessingService.ARG_NOTE_BUNDLE, mGCMMessageHandler.getCurrentNoteBundleForNoteId(noteId));
            }
            PendingIntent commentApprovePendingIntent = getCommentActionPendingIntentForService(context, commentApproveIntent);
            if (!ListenerUtil.mutListener.listen(2885)) {
                builder.addAction(R.drawable.ic_checkmark_white_24dp, context.getText(R.string.approve), commentApprovePendingIntent);
            }
        }

        private PendingIntent getCommentActionPendingIntent(Context context, Intent intent) {
            return getCommentActionPendingIntentForService(context, intent);
        }

        private PendingIntent getCommentActionPendingIntentForService(Context context, Intent intent) {
            int flags = PendingIntent.FLAG_CANCEL_CURRENT;
            if (!ListenerUtil.mutListener.listen(2892)) {
                if ((ListenerUtil.mutListener.listen(2890) ? (Build.VERSION.SDK_INT <= 31) : (ListenerUtil.mutListener.listen(2889) ? (Build.VERSION.SDK_INT > 31) : (ListenerUtil.mutListener.listen(2888) ? (Build.VERSION.SDK_INT < 31) : (ListenerUtil.mutListener.listen(2887) ? (Build.VERSION.SDK_INT != 31) : (ListenerUtil.mutListener.listen(2886) ? (Build.VERSION.SDK_INT == 31) : (Build.VERSION.SDK_INT >= 31)))))))
                    if (!ListenerUtil.mutListener.listen(2891)) {
                        flags |= PendingIntent.FLAG_MUTABLE;
                    }
            }
            return PendingIntent.getService(context, 0, intent, flags);
        }

        private Intent getCommentActionReplyIntent(Context context, String noteId) {
            return getCommentActionIntentForService(context);
        }

        private Intent getCommentActionIntent(Context context) {
            return getCommentActionIntentForService(context);
        }

        private Intent getCommentActionIntentForService(Context context) {
            return new Intent(context, NotificationsProcessingService.class);
        }

        private Bitmap getLargeIconBitmap(Context context, String iconUrl, boolean shouldCircularizeIcon) {
            Bitmap largeIconBitmap = null;
            if (!ListenerUtil.mutListener.listen(2899)) {
                if (iconUrl != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(2894)) {
                            iconUrl = URLDecoder.decode(iconUrl, "UTF-8");
                        }
                        int largeIconSize = context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height);
                        String resizedUrl = PhotonUtils.getPhotonImageUrl(iconUrl, largeIconSize, largeIconSize);
                        if (!ListenerUtil.mutListener.listen(2895)) {
                            largeIconBitmap = ImageUtils.downloadBitmap(resizedUrl);
                        }
                        if (!ListenerUtil.mutListener.listen(2898)) {
                            if ((ListenerUtil.mutListener.listen(2896) ? (largeIconBitmap != null || shouldCircularizeIcon) : (largeIconBitmap != null && shouldCircularizeIcon))) {
                                if (!ListenerUtil.mutListener.listen(2897)) {
                                    largeIconBitmap = ImageUtils.getCircularBitmap(largeIconBitmap);
                                }
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                        if (!ListenerUtil.mutListener.listen(2893)) {
                            AppLog.e(T.NOTIFS, e);
                        }
                    }
                }
            }
            return largeIconBitmap;
        }

        private NotificationCompat.Builder getNotificationBuilder(Context context, String title, String message) {
            // Build the new notification, add group to support wearable stacking
            return new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_normal_id)).setSmallIcon(R.drawable.ic_app_white_24dp).setColor(context.getResources().getColor(R.color.primary_50)).setContentTitle(title).setContentText(message).setTicker(message).setOnlyAlertOnce(true).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setGroup(NOTIFICATION_GROUP_KEY);
        }

        private void showGroupNotificationForBuilder(Context context, NotificationCompat.Builder builder, String wpcomNoteID, String message) {
            if (!ListenerUtil.mutListener.listen(2901)) {
                if ((ListenerUtil.mutListener.listen(2900) ? (builder == null && context == null) : (builder == null || context == null))) {
                    return;
                }
            }
            // using a copy of the map to avoid concurrency problems
            ArrayMap<Integer, Bundle> tmpMap = new ArrayMap<>(mGCMMessageHandler.mActiveNotificationsMap);
            if (!ListenerUtil.mutListener.listen(2902)) {
                // first remove 2fa push from the map, so it's not shown in the inbox style group notif
                tmpMap.remove(AUTH_PUSH_NOTIFICATION_ID);
            }
            if (!ListenerUtil.mutListener.listen(2935)) {
                if ((ListenerUtil.mutListener.listen(2907) ? (tmpMap.size() >= 1) : (ListenerUtil.mutListener.listen(2906) ? (tmpMap.size() <= 1) : (ListenerUtil.mutListener.listen(2905) ? (tmpMap.size() < 1) : (ListenerUtil.mutListener.listen(2904) ? (tmpMap.size() != 1) : (ListenerUtil.mutListener.listen(2903) ? (tmpMap.size() == 1) : (tmpMap.size() > 1))))))) {
                    NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                    int noteCtr = 1;
                    if (!ListenerUtil.mutListener.listen(2922)) {
                        {
                            long _loopCounter114 = 0;
                            for (Bundle pushBundle : tmpMap.values()) {
                                ListenerUtil.loopListener.listen("_loopCounter114", ++_loopCounter114);
                                if (!ListenerUtil.mutListener.listen(2915)) {
                                    // InboxStyle notification is limited to 5 lines
                                    if ((ListenerUtil.mutListener.listen(2914) ? (noteCtr >= MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2913) ? (noteCtr <= MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2912) ? (noteCtr < MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2911) ? (noteCtr != MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2910) ? (noteCtr == MAX_INBOX_ITEMS) : (noteCtr > MAX_INBOX_ITEMS))))))) {
                                        break;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2917)) {
                                    if ((ListenerUtil.mutListener.listen(2916) ? (pushBundle == null && pushBundle.getString(PUSH_ARG_MSG) == null) : (pushBundle == null || pushBundle.getString(PUSH_ARG_MSG) == null))) {
                                        continue;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2920)) {
                                    if (pushBundle.getString(PUSH_ARG_TYPE, "").equals(PUSH_TYPE_COMMENT)) {
                                        String pnTitle = StringEscapeUtils.unescapeHtml4((pushBundle.getString(PUSH_ARG_TITLE)));
                                        String pnMessage = StringEscapeUtils.unescapeHtml4((pushBundle.getString(PUSH_ARG_MSG)));
                                        if (!ListenerUtil.mutListener.listen(2919)) {
                                            inboxStyle.addLine(pnTitle + ": " + pnMessage);
                                        }
                                    } else {
                                        String pnMessage = StringEscapeUtils.unescapeHtml4((pushBundle.getString(PUSH_ARG_MSG)));
                                        if (!ListenerUtil.mutListener.listen(2918)) {
                                            inboxStyle.addLine(pnMessage);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(2921)) {
                                    noteCtr++;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2933)) {
                        if ((ListenerUtil.mutListener.listen(2927) ? (tmpMap.size() >= MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2926) ? (tmpMap.size() <= MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2925) ? (tmpMap.size() < MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2924) ? (tmpMap.size() != MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2923) ? (tmpMap.size() == MAX_INBOX_ITEMS) : (tmpMap.size() > MAX_INBOX_ITEMS))))))) {
                            if (!ListenerUtil.mutListener.listen(2932)) {
                                inboxStyle.setSummaryText(String.format(context.getString(R.string.more_notifications), (ListenerUtil.mutListener.listen(2931) ? (tmpMap.size() % MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2930) ? (tmpMap.size() / MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2929) ? (tmpMap.size() * MAX_INBOX_ITEMS) : (ListenerUtil.mutListener.listen(2928) ? (tmpMap.size() + MAX_INBOX_ITEMS) : (tmpMap.size() - MAX_INBOX_ITEMS)))))));
                            }
                        }
                    }
                    String subject = String.format(context.getString(R.string.new_notifications), tmpMap.size());
                    NotificationCompat.Builder groupBuilder = new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_normal_id)).setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN).setSmallIcon(R.drawable.ic_app_white_24dp).setColor(context.getResources().getColor(R.color.primary_50)).setGroup(NOTIFICATION_GROUP_KEY).setGroupSummary(true).setAutoCancel(true).setTicker(message).setContentTitle(context.getString(R.string.app_name)).setContentText(subject).setStyle(inboxStyle);
                    if (!ListenerUtil.mutListener.listen(2934)) {
                        showWPComNotificationForBuilder(groupBuilder, context, wpcomNoteID, GROUP_NOTIFICATION_ID, false, NotificationType.GROUP_NOTIFICATION);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2908)) {
                        // Set the individual notification we've already built as the group summary
                        builder.setGroupSummary(true).setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN);
                    }
                    if (!ListenerUtil.mutListener.listen(2909)) {
                        showWPComNotificationForBuilder(builder, context, wpcomNoteID, GROUP_NOTIFICATION_ID, false, NotificationType.GROUP_NOTIFICATION);
                    }
                }
            }
        }

        private void showSingleNotificationForBuilder(Context context, NotificationCompat.Builder builder, String noteType, String wpcomNoteID, int pushId, boolean notifyUser) {
            if (!ListenerUtil.mutListener.listen(2937)) {
                if ((ListenerUtil.mutListener.listen(2936) ? (builder == null && context == null) : (builder == null || context == null))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(2939)) {
                if (noteType.equals(PUSH_TYPE_COMMENT)) {
                    if (!ListenerUtil.mutListener.listen(2938)) {
                        addActionsForCommentNotification(context, builder, wpcomNoteID);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2940)) {
                showWPComNotificationForBuilder(builder, context, wpcomNoteID, pushId, notifyUser, fromNoteType(noteType));
            }
        }

        private NotificationType fromNoteType(String noteType) {
            switch(noteType) {
                case PUSH_TYPE_COMMENT:
                    return NotificationType.COMMENT;
                case PUSH_TYPE_LIKE:
                    return NotificationType.LIKE;
                case PUSH_TYPE_COMMENT_LIKE:
                    return NotificationType.COMMENT_LIKE;
                case PUSH_TYPE_AUTOMATTCHER:
                    return NotificationType.AUTOMATTCHER;
                case PUSH_TYPE_FOLLOW:
                    return NotificationType.FOLLOW;
                case PUSH_TYPE_REBLOG:
                    return NotificationType.REBLOG;
                case PUSH_TYPE_PUSH_AUTH:
                    return NotificationType.AUTHENTICATION;
                case PUSH_TYPE_BADGE_RESET:
                    return NotificationType.BADGE_RESET;
                case PUSH_TYPE_NOTE_DELETE:
                    return NotificationType.NOTE_DELETE;
                case PUSH_TYPE_TEST_NOTE:
                    return NotificationType.TEST_NOTE;
                case PUSH_TYPE_ZENDESK:
                    return NotificationType.ZENDESK;
                default:
                    return UNKNOWN_NOTE;
            }
        }

        private void showWPComNotificationForBuilder(NotificationCompat.Builder builder, Context context, String wpcomNoteID, int pushId, boolean notifyUser, NotificationType notificationType) {
            Intent resultIntent = new Intent(context, WPMainActivity.class);
            if (!ListenerUtil.mutListener.listen(2941)) {
                resultIntent.putExtra(WPMainActivity.ARG_OPENED_FROM_PUSH, true);
            }
            if (!ListenerUtil.mutListener.listen(2942)) {
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            if (!ListenerUtil.mutListener.listen(2943)) {
                resultIntent.setAction("android.intent.action.MAIN");
            }
            if (!ListenerUtil.mutListener.listen(2944)) {
                resultIntent.addCategory("android.intent.category.LAUNCHER");
            }
            if (!ListenerUtil.mutListener.listen(2945)) {
                resultIntent.putExtra(NotificationsListFragment.NOTE_ID_EXTRA, wpcomNoteID);
            }
            if (!ListenerUtil.mutListener.listen(2946)) {
                resultIntent.putExtra(IS_TAPPED_ON_NOTIFICATION, true);
            }
            if (!ListenerUtil.mutListener.listen(2947)) {
                showNotificationForBuilder(builder, context, resultIntent, pushId, notifyUser, notificationType);
            }
        }

        // Displays a notification to the user
        private void showNotificationForBuilder(NotificationCompat.Builder builder, Context context, Intent resultIntent, int pushId, boolean notifyUser, NotificationType notificationType) {
            if (!ListenerUtil.mutListener.listen(2950)) {
                if ((ListenerUtil.mutListener.listen(2949) ? ((ListenerUtil.mutListener.listen(2948) ? (builder == null && context == null) : (builder == null || context == null)) && resultIntent == null) : ((ListenerUtil.mutListener.listen(2948) ? (builder == null && context == null) : (builder == null || context == null)) || resultIntent == null))) {
                    return;
                }
            }
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            boolean shouldReceiveNotifications = prefs.getBoolean(context.getString(R.string.wp_pref_notifications_main), true);
            if (!ListenerUtil.mutListener.listen(2967)) {
                if (shouldReceiveNotifications) {
                    if (!ListenerUtil.mutListener.listen(2960)) {
                        if (notifyUser) {
                            boolean shouldVibrate = prefs.getBoolean(context.getString(R.string.wp_pref_notification_vibrate), false);
                            boolean shouldBlinkLight = prefs.getBoolean(context.getString(R.string.wp_pref_notification_light), true);
                            String notificationSound = prefs.getString(context.getString(R.string.wp_pref_custom_notification_sound), context.getString(R.string.notification_settings_item_sights_and_sounds_choose_sound_default));
                            if (!ListenerUtil.mutListener.listen(2955)) {
                                if ((ListenerUtil.mutListener.listen(2953) ? (!TextUtils.isEmpty(notificationSound) || !notificationSound.trim().toLowerCase(Locale.ROOT).startsWith("file://")) : (!TextUtils.isEmpty(notificationSound) && !notificationSound.trim().toLowerCase(Locale.ROOT).startsWith("file://")))) {
                                    if (!ListenerUtil.mutListener.listen(2954)) {
                                        builder.setSound(Uri.parse(notificationSound));
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2957)) {
                                if (shouldVibrate) {
                                    if (!ListenerUtil.mutListener.listen(2956)) {
                                        builder.setVibrate(new long[] { 500, 500, 500 });
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2959)) {
                                if (shouldBlinkLight) {
                                    if (!ListenerUtil.mutListener.listen(2958)) {
                                        builder.setLights(0xff0000ff, 1000, 5000);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2951)) {
                                builder.setVibrate(null);
                            }
                            if (!ListenerUtil.mutListener.listen(2952)) {
                                builder.setSound(null);
                            }
                        }
                    }
                    // Call processing service when notification is dismissed
                    PendingIntent pendingDeleteIntent = NotificationsProcessingService.getPendingIntentForNotificationDismiss(context, pushId, notificationType);
                    if (!ListenerUtil.mutListener.listen(2961)) {
                        builder.setDeleteIntent(pendingDeleteIntent);
                    }
                    if (!ListenerUtil.mutListener.listen(2962)) {
                        builder.setCategory(NotificationCompat.CATEGORY_SOCIAL);
                    }
                    if (!ListenerUtil.mutListener.listen(2963)) {
                        resultIntent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
                    }
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, pushId, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    if (!ListenerUtil.mutListener.listen(2964)) {
                        builder.setContentIntent(pendingIntent);
                    }
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                    if (!ListenerUtil.mutListener.listen(2965)) {
                        notificationManager.notify(pushId, builder.build());
                    }
                    if (!ListenerUtil.mutListener.listen(2966)) {
                        mSystemNotificationsTracker.trackShownNotification(notificationType);
                    }
                }
            }
        }

        private void rebuildAndUpdateNotificationsOnSystemBar(Context context, Bundle data) {
            String noteType = StringUtils.notNullStr(data.getString(PUSH_ARG_TYPE));
            // and we'll remove the auth special notif out of the map while we re-build the remaining notifs
            ArrayMap<Integer, Bundle> tmpMap = new ArrayMap<>(mGCMMessageHandler.mActiveNotificationsMap);
            Bundle authPNBundle = tmpMap.remove(AUTH_PUSH_NOTIFICATION_ID);
            if (!ListenerUtil.mutListener.listen(2977)) {
                if (authPNBundle != null) {
                    if (!ListenerUtil.mutListener.listen(2968)) {
                        handlePushAuth(context, authPNBundle);
                    }
                    if (!ListenerUtil.mutListener.listen(2976)) {
                        if ((ListenerUtil.mutListener.listen(2974) ? ((ListenerUtil.mutListener.listen(2973) ? (tmpMap.size() >= 0) : (ListenerUtil.mutListener.listen(2972) ? (tmpMap.size() <= 0) : (ListenerUtil.mutListener.listen(2971) ? (tmpMap.size() < 0) : (ListenerUtil.mutListener.listen(2970) ? (tmpMap.size() != 0) : (ListenerUtil.mutListener.listen(2969) ? (tmpMap.size() == 0) : (tmpMap.size() > 0)))))) || noteType.equals(PUSH_TYPE_PUSH_AUTH)) : ((ListenerUtil.mutListener.listen(2973) ? (tmpMap.size() >= 0) : (ListenerUtil.mutListener.listen(2972) ? (tmpMap.size() <= 0) : (ListenerUtil.mutListener.listen(2971) ? (tmpMap.size() < 0) : (ListenerUtil.mutListener.listen(2970) ? (tmpMap.size() != 0) : (ListenerUtil.mutListener.listen(2969) ? (tmpMap.size() == 0) : (tmpMap.size() > 0)))))) && noteType.equals(PUSH_TYPE_PUSH_AUTH)))) {
                            if (!ListenerUtil.mutListener.listen(2975)) {
                                // because otherwise we would be keeping the PUSH_AUTH type note in `data`
                                data = tmpMap.values().iterator().next();
                            }
                        } else if (noteType.equals(PUSH_TYPE_PUSH_AUTH)) {
                            // only note is the 2fa note, just return
                            return;
                        }
                    }
                }
            }
            Bitmap largeIconBitmap = null;
            // here notify the existing group notification by eliminating the line that is now gone
            String title = getNotificationTitleOrAppNameFromBundle(context, data);
            String message = StringEscapeUtils.unescapeHtml4(data.getString(PUSH_ARG_MSG));
            NotificationCompat.Builder builder = null;
            String wpcomNoteID = null;
            if (!ListenerUtil.mutListener.listen(2996)) {
                if (tmpMap.size() == 1) {
                    // in the system dashboard
                    Bundle remainingNote = tmpMap.values().iterator().next();
                    if (!ListenerUtil.mutListener.listen(2995)) {
                        if (remainingNote != null) {
                            String remainingNoteTitle = StringEscapeUtils.unescapeHtml4(remainingNote.getString(PUSH_ARG_TITLE));
                            if (!ListenerUtil.mutListener.listen(2979)) {
                                if (!TextUtils.isEmpty(remainingNoteTitle)) {
                                    if (!ListenerUtil.mutListener.listen(2978)) {
                                        title = remainingNoteTitle;
                                    }
                                }
                            }
                            String remainingNoteMessage = StringEscapeUtils.unescapeHtml4(remainingNote.getString(PUSH_ARG_MSG));
                            if (!ListenerUtil.mutListener.listen(2981)) {
                                if (!TextUtils.isEmpty(remainingNoteMessage)) {
                                    if (!ListenerUtil.mutListener.listen(2980)) {
                                        message = remainingNoteMessage;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(2982)) {
                                largeIconBitmap = getLargeIconBitmap(context, remainingNote.getString("icon"), shouldCircularizeNoteIcon(remainingNote.getString(PUSH_ARG_TYPE)));
                            }
                            if (!ListenerUtil.mutListener.listen(2983)) {
                                builder = getNotificationBuilder(context, title, message);
                            }
                            // else fails (not likely)
                            long timeStampToShow = DateTimeUtils.timestampFromIso8601Millis(remainingNote.getString("note_timestamp"));
                            if (!ListenerUtil.mutListener.listen(2989)) {
                                timeStampToShow = (ListenerUtil.mutListener.listen(2988) ? (timeStampToShow >= 0) : (ListenerUtil.mutListener.listen(2987) ? (timeStampToShow <= 0) : (ListenerUtil.mutListener.listen(2986) ? (timeStampToShow > 0) : (ListenerUtil.mutListener.listen(2985) ? (timeStampToShow < 0) : (ListenerUtil.mutListener.listen(2984) ? (timeStampToShow == 0) : (timeStampToShow != 0)))))) ? timeStampToShow : remainingNote.getLong("google.sent_time", System.currentTimeMillis());
                            }
                            if (!ListenerUtil.mutListener.listen(2990)) {
                                builder.setWhen(timeStampToShow);
                            }
                            if (!ListenerUtil.mutListener.listen(2991)) {
                                noteType = StringUtils.notNullStr(remainingNote.getString(PUSH_ARG_TYPE));
                            }
                            if (!ListenerUtil.mutListener.listen(2992)) {
                                wpcomNoteID = remainingNote.getString(PUSH_ARG_NOTE_ID, "");
                            }
                            if (!ListenerUtil.mutListener.listen(2994)) {
                                if (!tmpMap.isEmpty()) {
                                    if (!ListenerUtil.mutListener.listen(2993)) {
                                        showSingleNotificationForBuilder(context, builder, noteType, wpcomNoteID, tmpMap.keyAt(0), false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2998)) {
                if (builder == null) {
                    if (!ListenerUtil.mutListener.listen(2997)) {
                        builder = getNotificationBuilder(context, title, message);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3000)) {
                if (largeIconBitmap == null) {
                    if (!ListenerUtil.mutListener.listen(2999)) {
                        largeIconBitmap = getLargeIconBitmap(context, data.getString("icon"), shouldCircularizeNoteIcon(PUSH_TYPE_BADGE_RESET));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3002)) {
                if (wpcomNoteID == null) {
                    if (!ListenerUtil.mutListener.listen(3001)) {
                        wpcomNoteID = AppPrefs.getLastPushNotificationWpcomNoteId();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3004)) {
                if (largeIconBitmap != null) {
                    if (!ListenerUtil.mutListener.listen(3003)) {
                        builder.setLargeIcon(largeIconBitmap);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3005)) {
                showGroupNotificationForBuilder(context, builder, wpcomNoteID, message);
            }
        }

        private String getNotificationTitleOrAppNameFromBundle(Context context, Bundle data) {
            String title = StringEscapeUtils.unescapeHtml4(data.getString(PUSH_ARG_TITLE));
            if (!ListenerUtil.mutListener.listen(3007)) {
                if (title == null) {
                    if (!ListenerUtil.mutListener.listen(3006)) {
                        title = context.getString(R.string.app_name);
                    }
                }
            }
            return title;
        }

        // Clear all notifications
        private void handleBadgeResetPN(Context context, Bundle data) {
            if (!ListenerUtil.mutListener.listen(3008)) {
                if (data == null) {
                    return;
                }
            }
            String noteID = data.getString(PUSH_ARG_NOTE_ID, "");
            if (!ListenerUtil.mutListener.listen(3022)) {
                if (!TextUtils.isEmpty(noteID)) {
                    Note note = NotificationsTable.getNoteById(noteID);
                    if (!ListenerUtil.mutListener.listen(3013)) {
                        // mark the note as read if it's unread and update the DB silently
                        if ((ListenerUtil.mutListener.listen(3010) ? (note != null || note.isUnread()) : (note != null && note.isUnread()))) {
                            if (!ListenerUtil.mutListener.listen(3011)) {
                                note.setRead();
                            }
                            if (!ListenerUtil.mutListener.listen(3012)) {
                                NotificationsTable.saveNote(note);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3014)) {
                        mGCMMessageHandler.removeNotificationWithNoteIdFromSystemBar(context, noteID);
                    }
                    if (!ListenerUtil.mutListener.listen(3021)) {
                        // now that we cleared the specific notif, we can check and make any visual updates
                        if ((ListenerUtil.mutListener.listen(3019) ? (mGCMMessageHandler.mActiveNotificationsMap.size() >= 0) : (ListenerUtil.mutListener.listen(3018) ? (mGCMMessageHandler.mActiveNotificationsMap.size() <= 0) : (ListenerUtil.mutListener.listen(3017) ? (mGCMMessageHandler.mActiveNotificationsMap.size() < 0) : (ListenerUtil.mutListener.listen(3016) ? (mGCMMessageHandler.mActiveNotificationsMap.size() != 0) : (ListenerUtil.mutListener.listen(3015) ? (mGCMMessageHandler.mActiveNotificationsMap.size() == 0) : (mGCMMessageHandler.mActiveNotificationsMap.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(3020)) {
                                rebuildAndUpdateNotificationsOnSystemBar(context, data);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3009)) {
                        mGCMMessageHandler.removeAllNotifications(context);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3028)) {
                EventBus.getDefault().post(new NotificationEvents.NotificationsChanged((ListenerUtil.mutListener.listen(3027) ? (mGCMMessageHandler.mActiveNotificationsMap.size() >= 0) : (ListenerUtil.mutListener.listen(3026) ? (mGCMMessageHandler.mActiveNotificationsMap.size() <= 0) : (ListenerUtil.mutListener.listen(3025) ? (mGCMMessageHandler.mActiveNotificationsMap.size() < 0) : (ListenerUtil.mutListener.listen(3024) ? (mGCMMessageHandler.mActiveNotificationsMap.size() != 0) : (ListenerUtil.mutListener.listen(3023) ? (mGCMMessageHandler.mActiveNotificationsMap.size() == 0) : (mGCMMessageHandler.mActiveNotificationsMap.size() > 0))))))));
            }
        }

        private void handleNoteDeletePN(Context context, Bundle data) {
            if (!ListenerUtil.mutListener.listen(3030)) {
                if ((ListenerUtil.mutListener.listen(3029) ? (data == null && !data.containsKey(PUSH_ARG_NOTE_ID)) : (data == null || !data.containsKey(PUSH_ARG_NOTE_ID)))) {
                    return;
                }
            }
            String noteID = data.getString(PUSH_ARG_NOTE_ID, "");
            if (!ListenerUtil.mutListener.listen(3032)) {
                if (!TextUtils.isEmpty(noteID)) {
                    if (!ListenerUtil.mutListener.listen(3031)) {
                        NotificationsTable.deleteNoteById(noteID);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3033)) {
                mGCMMessageHandler.removeNotificationWithNoteIdFromSystemBar(context, noteID);
            }
            if (!ListenerUtil.mutListener.listen(3040)) {
                // now that we cleared the specific notif, we can check and make any visual updates
                if ((ListenerUtil.mutListener.listen(3038) ? (mGCMMessageHandler.mActiveNotificationsMap.size() >= 0) : (ListenerUtil.mutListener.listen(3037) ? (mGCMMessageHandler.mActiveNotificationsMap.size() <= 0) : (ListenerUtil.mutListener.listen(3036) ? (mGCMMessageHandler.mActiveNotificationsMap.size() < 0) : (ListenerUtil.mutListener.listen(3035) ? (mGCMMessageHandler.mActiveNotificationsMap.size() != 0) : (ListenerUtil.mutListener.listen(3034) ? (mGCMMessageHandler.mActiveNotificationsMap.size() == 0) : (mGCMMessageHandler.mActiveNotificationsMap.size() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(3039)) {
                        rebuildAndUpdateNotificationsOnSystemBar(context, data);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3046)) {
                EventBus.getDefault().post(new NotificationEvents.NotificationsChanged((ListenerUtil.mutListener.listen(3045) ? (mGCMMessageHandler.mActiveNotificationsMap.size() >= 0) : (ListenerUtil.mutListener.listen(3044) ? (mGCMMessageHandler.mActiveNotificationsMap.size() <= 0) : (ListenerUtil.mutListener.listen(3043) ? (mGCMMessageHandler.mActiveNotificationsMap.size() < 0) : (ListenerUtil.mutListener.listen(3042) ? (mGCMMessageHandler.mActiveNotificationsMap.size() != 0) : (ListenerUtil.mutListener.listen(3041) ? (mGCMMessageHandler.mActiveNotificationsMap.size() == 0) : (mGCMMessageHandler.mActiveNotificationsMap.size() > 0))))))));
            }
        }

        // Show a notification for two-step auth users who log in from a web browser
        private void handlePushAuth(Context context, Bundle data) {
            if (!ListenerUtil.mutListener.listen(3047)) {
                if (data == null) {
                    return;
                }
            }
            String pushAuthToken = data.getString("push_auth_token", "");
            String title = data.getString("title", "");
            String message = data.getString("msg", "");
            long expirationTimestamp = Long.valueOf(data.getString("expires", "0"));
            if (!ListenerUtil.mutListener.listen(3050)) {
                // No strings, no service
                if ((ListenerUtil.mutListener.listen(3049) ? ((ListenerUtil.mutListener.listen(3048) ? (TextUtils.isEmpty(pushAuthToken) && TextUtils.isEmpty(title)) : (TextUtils.isEmpty(pushAuthToken) || TextUtils.isEmpty(title))) && TextUtils.isEmpty(message)) : ((ListenerUtil.mutListener.listen(3048) ? (TextUtils.isEmpty(pushAuthToken) && TextUtils.isEmpty(title)) : (TextUtils.isEmpty(pushAuthToken) || TextUtils.isEmpty(title))) || TextUtils.isEmpty(message)))) {
                    return;
                }
            }
            // Show authorization intent
            Intent pushAuthIntent = new Intent(context, WPMainActivity.class);
            if (!ListenerUtil.mutListener.listen(3051)) {
                pushAuthIntent.putExtra(WPMainActivity.ARG_OPENED_FROM_PUSH, true);
            }
            if (!ListenerUtil.mutListener.listen(3052)) {
                pushAuthIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_TOKEN, pushAuthToken);
            }
            if (!ListenerUtil.mutListener.listen(3053)) {
                pushAuthIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_TITLE, title);
            }
            if (!ListenerUtil.mutListener.listen(3054)) {
                pushAuthIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_MESSAGE, message);
            }
            if (!ListenerUtil.mutListener.listen(3055)) {
                pushAuthIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_EXPIRES, expirationTimestamp);
            }
            if (!ListenerUtil.mutListener.listen(3056)) {
                pushAuthIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            if (!ListenerUtil.mutListener.listen(3057)) {
                pushAuthIntent.setAction("android.intent.action.MAIN");
            }
            if (!ListenerUtil.mutListener.listen(3058)) {
                pushAuthIntent.addCategory("android.intent.category.LAUNCHER");
            }
            NotificationType notificationType = NotificationType.AUTHENTICATION;
            if (!ListenerUtil.mutListener.listen(3059)) {
                pushAuthIntent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_important_id)).setSmallIcon(R.drawable.ic_app_white_24dp).setColor(context.getResources().getColor(R.color.primary_50)).setContentTitle(title).setContentText(message).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setOnlyAlertOnce(true).setPriority(NotificationCompat.PRIORITY_MAX);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, AUTH_PUSH_REQUEST_CODE_OPEN_DIALOG, pushAuthIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            if (!ListenerUtil.mutListener.listen(3060)) {
                builder.setContentIntent(pendingIntent);
            }
            // adding ignore / approve quick actions
            Intent authApproveIntent = new Intent(context, WPMainActivity.class);
            if (!ListenerUtil.mutListener.listen(3061)) {
                authApproveIntent.putExtra(WPMainActivity.ARG_OPENED_FROM_PUSH, true);
            }
            if (!ListenerUtil.mutListener.listen(3062)) {
                authApproveIntent.putExtra(NotificationsProcessingService.ARG_ACTION_TYPE, NotificationsProcessingService.ARG_ACTION_AUTH_APPROVE);
            }
            if (!ListenerUtil.mutListener.listen(3063)) {
                authApproveIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_TOKEN, pushAuthToken);
            }
            if (!ListenerUtil.mutListener.listen(3064)) {
                authApproveIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_TITLE, title);
            }
            if (!ListenerUtil.mutListener.listen(3065)) {
                authApproveIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_MESSAGE, message);
            }
            if (!ListenerUtil.mutListener.listen(3066)) {
                authApproveIntent.putExtra(NotificationsUtils.ARG_PUSH_AUTH_EXPIRES, expirationTimestamp);
            }
            if (!ListenerUtil.mutListener.listen(3067)) {
                authApproveIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            if (!ListenerUtil.mutListener.listen(3068)) {
                authApproveIntent.setAction("android.intent.action.MAIN");
            }
            if (!ListenerUtil.mutListener.listen(3069)) {
                authApproveIntent.addCategory("android.intent.category.LAUNCHER");
            }
            PendingIntent authApprovePendingIntent = PendingIntent.getActivity(context, AUTH_PUSH_REQUEST_CODE_APPROVE, authApproveIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            if (!ListenerUtil.mutListener.listen(3070)) {
                builder.addAction(R.drawable.ic_checkmark_white_24dp, context.getText(R.string.approve), authApprovePendingIntent);
            }
            Intent authIgnoreIntent = new Intent(context, NotificationsProcessingService.class);
            if (!ListenerUtil.mutListener.listen(3071)) {
                authIgnoreIntent.putExtra(NotificationsProcessingService.ARG_ACTION_TYPE, NotificationsProcessingService.ARG_ACTION_AUTH_IGNORE);
            }
            PendingIntent authIgnorePendingIntent = PendingIntent.getService(context, AUTH_PUSH_REQUEST_CODE_IGNORE, authIgnoreIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            if (!ListenerUtil.mutListener.listen(3072)) {
                builder.addAction(R.drawable.ic_close_white_24dp, context.getText(R.string.ignore), authIgnorePendingIntent);
            }
            // Call processing service when notification is dismissed
            PendingIntent pendingDeleteIntent = NotificationsProcessingService.getPendingIntentForNotificationDismiss(context, AUTH_PUSH_NOTIFICATION_ID, notificationType);
            if (!ListenerUtil.mutListener.listen(3073)) {
                builder.setDeleteIntent(pendingDeleteIntent);
            }
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            if (!ListenerUtil.mutListener.listen(3074)) {
                notificationManager.notify(AUTH_PUSH_NOTIFICATION_ID, builder.build());
            }
            if (!ListenerUtil.mutListener.listen(3075)) {
                mSystemNotificationsTracker.trackShownNotification(notificationType);
            }
        }

        // Returns true if the note type is known to have a gravatar
        private boolean shouldCircularizeNoteIcon(String noteType) {
            if (TextUtils.isEmpty(noteType)) {
                return false;
            }
            switch(noteType) {
                case PUSH_TYPE_COMMENT:
                case PUSH_TYPE_LIKE:
                case PUSH_TYPE_COMMENT_LIKE:
                case PUSH_TYPE_AUTOMATTCHER:
                case PUSH_TYPE_FOLLOW:
                case PUSH_TYPE_REBLOG:
                    return true;
                default:
                    return false;
            }
        }

        /**
         * Shows a notification stating that the user has a reply pending from Zendesk. Since Zendesk always sends a
         * notification with the same title and message, we use our own localized messaging. For the same reason,
         * we use a static push notification ID. Tapping on the notification will open the `ME` fragment.
         */
        void handleZendeskNotification(Context context) {
            if (!ListenerUtil.mutListener.listen(3076)) {
                if (context == null) {
                    return;
                }
            }
            String title = context.getString(R.string.support_push_notification_title);
            String message = context.getString(R.string.support_push_notification_message);
            Intent resultIntent = new Intent(context, WPMainActivity.class);
            if (!ListenerUtil.mutListener.listen(3077)) {
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            if (!ListenerUtil.mutListener.listen(3078)) {
                resultIntent.putExtra(WPMainActivity.ARG_SHOW_ZENDESK_NOTIFICATIONS, true);
            }
            if (!ListenerUtil.mutListener.listen(3079)) {
                showSimpleNotification(context, title, message, resultIntent, ZENDESK_PUSH_NOTIFICATION_ID, NotificationType.ZENDESK);
            }
        }
    }
}
