package org.wordpress.android.ui.notifications.receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.post.PostStatus;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.push.NativeNotificationsUtils;
import org.wordpress.android.push.NotificationType;
import org.wordpress.android.push.NotificationsProcessingService;
import org.wordpress.android.ui.main.WPMainActivity;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.notifications.utils.PendingDraftsNotificationsUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DateTimeUtils;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import static org.wordpress.android.push.NotificationsProcessingService.ARG_NOTIFICATION_TYPE;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsPendingDraftsReceiver extends BroadcastReceiver {

    public static final String POST_ID_EXTRA = "postId";

    public static final String IS_PAGE_EXTRA = "isPage";

    public static final long ONE_DAY = 24 * 60 * 60 * 1000;

    public static final long ONE_WEEK = ONE_DAY * 7;

    public static final long ONE_MONTH = ONE_WEEK * 4;

    // just over a month
    private static final long MAX_DAYS_TO_SHOW_DAYS_IN_MESSAGE = 40;

    private static final int BASE_REQUEST_CODE = 100;

    @Inject
    PostStore mPostStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    SystemNotificationsTracker mSystemNotificationsTracker;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(8602)) {
            ((WordPress) context.getApplicationContext()).component().inject(this);
        }
        // and check the lastUpdated
        String action = intent.getAction();
        if (!ListenerUtil.mutListener.listen(8610)) {
            if ((ListenerUtil.mutListener.listen(8603) ? (action != null || action.equals("android.intent.action.BOOT_COMPLETED")) : (action != null && action.equals("android.intent.action.BOOT_COMPLETED")))) {
                if (!ListenerUtil.mutListener.listen(8606)) {
                    AppLog.i(AppLog.T.NOTIFS, "entering Pending Drafts Receiver from BOOT_COMPLETED");
                }
                // build notifications for existing local drafts
                SiteModel site = mSiteStore.getSiteByLocalId(mSelectedSiteRepository.getSelectedSiteLocalId());
                if (!ListenerUtil.mutListener.listen(8609)) {
                    if (site != null) {
                        List<PostModel> draftPosts = mPostStore.getPostsForSite(site);
                        if (!ListenerUtil.mutListener.listen(8608)) {
                            {
                                long _loopCounter174 = 0;
                                for (PostModel post : draftPosts) {
                                    ListenerUtil.loopListener.listen("_loopCounter174", ++_loopCounter174);
                                    if (!ListenerUtil.mutListener.listen(8607)) {
                                        // just been rebooted
                                        PendingDraftsNotificationsUtils.scheduleNextNotifications(context, post.getId(), post.getDateLocallyChanged());
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8604)) {
                    AppLog.i(AppLog.T.NOTIFS, "entering Pending Drafts Receiver from alarm");
                }
                if (!ListenerUtil.mutListener.listen(8605)) {
                    // get extras from intent in order to build notification
                    buildNotificationForPostId(intent.getIntExtra(POST_ID_EXTRA, 0), context);
                }
            }
        }
    }

    private void buildNotificationForPostId(int postId, Context context) {
        if (!ListenerUtil.mutListener.listen(8679)) {
            if ((ListenerUtil.mutListener.listen(8615) ? (postId >= 0) : (ListenerUtil.mutListener.listen(8614) ? (postId <= 0) : (ListenerUtil.mutListener.listen(8613) ? (postId > 0) : (ListenerUtil.mutListener.listen(8612) ? (postId < 0) : (ListenerUtil.mutListener.listen(8611) ? (postId == 0) : (postId != 0))))))) {
                PostModel post = mPostStore.getPostByLocalPostId(postId);
                if (!ListenerUtil.mutListener.listen(8678)) {
                    if ((ListenerUtil.mutListener.listen(8616) ? (post != null || PostStatus.DRAFT.toString().equals(post.getStatus())) : (post != null && PostStatus.DRAFT.toString().equals(post.getStatus())))) {
                        long now = System.currentTimeMillis();
                        long dateLastUpdated = DateTimeUtils.timestampFromIso8601(post.getDateLocallyChanged());
                        long daysInDraft = (ListenerUtil.mutListener.listen(8624) ? (((ListenerUtil.mutListener.listen(8620) ? (now % dateLastUpdated) : (ListenerUtil.mutListener.listen(8619) ? (now / dateLastUpdated) : (ListenerUtil.mutListener.listen(8618) ? (now * dateLastUpdated) : (ListenerUtil.mutListener.listen(8617) ? (now + dateLastUpdated) : (now - dateLastUpdated)))))) % ONE_DAY) : (ListenerUtil.mutListener.listen(8623) ? (((ListenerUtil.mutListener.listen(8620) ? (now % dateLastUpdated) : (ListenerUtil.mutListener.listen(8619) ? (now / dateLastUpdated) : (ListenerUtil.mutListener.listen(8618) ? (now * dateLastUpdated) : (ListenerUtil.mutListener.listen(8617) ? (now + dateLastUpdated) : (now - dateLastUpdated)))))) * ONE_DAY) : (ListenerUtil.mutListener.listen(8622) ? (((ListenerUtil.mutListener.listen(8620) ? (now % dateLastUpdated) : (ListenerUtil.mutListener.listen(8619) ? (now / dateLastUpdated) : (ListenerUtil.mutListener.listen(8618) ? (now * dateLastUpdated) : (ListenerUtil.mutListener.listen(8617) ? (now + dateLastUpdated) : (now - dateLastUpdated)))))) - ONE_DAY) : (ListenerUtil.mutListener.listen(8621) ? (((ListenerUtil.mutListener.listen(8620) ? (now % dateLastUpdated) : (ListenerUtil.mutListener.listen(8619) ? (now / dateLastUpdated) : (ListenerUtil.mutListener.listen(8618) ? (now * dateLastUpdated) : (ListenerUtil.mutListener.listen(8617) ? (now + dateLastUpdated) : (now - dateLastUpdated)))))) + ONE_DAY) : (((ListenerUtil.mutListener.listen(8620) ? (now % dateLastUpdated) : (ListenerUtil.mutListener.listen(8619) ? (now / dateLastUpdated) : (ListenerUtil.mutListener.listen(8618) ? (now * dateLastUpdated) : (ListenerUtil.mutListener.listen(8617) ? (now + dateLastUpdated) : (now - dateLastUpdated)))))) / ONE_DAY)))));
                        boolean isPage = post.isPage();
                        if (!ListenerUtil.mutListener.listen(8677)) {
                            if ((ListenerUtil.mutListener.listen(8629) ? (daysInDraft >= MAX_DAYS_TO_SHOW_DAYS_IN_MESSAGE) : (ListenerUtil.mutListener.listen(8628) ? (daysInDraft <= MAX_DAYS_TO_SHOW_DAYS_IN_MESSAGE) : (ListenerUtil.mutListener.listen(8627) ? (daysInDraft > MAX_DAYS_TO_SHOW_DAYS_IN_MESSAGE) : (ListenerUtil.mutListener.listen(8626) ? (daysInDraft != MAX_DAYS_TO_SHOW_DAYS_IN_MESSAGE) : (ListenerUtil.mutListener.listen(8625) ? (daysInDraft == MAX_DAYS_TO_SHOW_DAYS_IN_MESSAGE) : (daysInDraft < MAX_DAYS_TO_SHOW_DAYS_IN_MESSAGE))))))) {
                                String formattedString = context.getString(R.string.pending_draft_one_generic);
                                long oneDayAgo = (ListenerUtil.mutListener.listen(8634) ? (now % ONE_DAY) : (ListenerUtil.mutListener.listen(8633) ? (now / ONE_DAY) : (ListenerUtil.mutListener.listen(8632) ? (now * ONE_DAY) : (ListenerUtil.mutListener.listen(8631) ? (now + ONE_DAY) : (now - ONE_DAY)))));
                                long oneWeekAgo = (ListenerUtil.mutListener.listen(8638) ? (now % ONE_WEEK) : (ListenerUtil.mutListener.listen(8637) ? (now / ONE_WEEK) : (ListenerUtil.mutListener.listen(8636) ? (now * ONE_WEEK) : (ListenerUtil.mutListener.listen(8635) ? (now + ONE_WEEK) : (now - ONE_WEEK)))));
                                long oneMonthAgo = (ListenerUtil.mutListener.listen(8642) ? (now % ONE_MONTH) : (ListenerUtil.mutListener.listen(8641) ? (now / ONE_MONTH) : (ListenerUtil.mutListener.listen(8640) ? (now * ONE_MONTH) : (ListenerUtil.mutListener.listen(8639) ? (now + ONE_MONTH) : (now - ONE_MONTH)))));
                                if (!ListenerUtil.mutListener.listen(8675)) {
                                    if ((ListenerUtil.mutListener.listen(8647) ? (dateLastUpdated >= oneMonthAgo) : (ListenerUtil.mutListener.listen(8646) ? (dateLastUpdated <= oneMonthAgo) : (ListenerUtil.mutListener.listen(8645) ? (dateLastUpdated > oneMonthAgo) : (ListenerUtil.mutListener.listen(8644) ? (dateLastUpdated != oneMonthAgo) : (ListenerUtil.mutListener.listen(8643) ? (dateLastUpdated == oneMonthAgo) : (dateLastUpdated < oneMonthAgo))))))) {
                                        if (!ListenerUtil.mutListener.listen(8674)) {
                                            formattedString = context.getString(R.string.pending_draft_one_month);
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(8652) ? (dateLastUpdated >= oneWeekAgo) : (ListenerUtil.mutListener.listen(8651) ? (dateLastUpdated <= oneWeekAgo) : (ListenerUtil.mutListener.listen(8650) ? (dateLastUpdated > oneWeekAgo) : (ListenerUtil.mutListener.listen(8649) ? (dateLastUpdated != oneWeekAgo) : (ListenerUtil.mutListener.listen(8648) ? (dateLastUpdated == oneWeekAgo) : (dateLastUpdated < oneWeekAgo))))))) {
                                        // use any of the available 2 string formats, randomly
                                        Random randomNum = new Random();
                                        int result = randomNum.nextInt(2);
                                        if (!ListenerUtil.mutListener.listen(8673)) {
                                            if ((ListenerUtil.mutListener.listen(8670) ? (result >= 0) : (ListenerUtil.mutListener.listen(8669) ? (result <= 0) : (ListenerUtil.mutListener.listen(8668) ? (result > 0) : (ListenerUtil.mutListener.listen(8667) ? (result < 0) : (ListenerUtil.mutListener.listen(8666) ? (result != 0) : (result == 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(8672)) {
                                                    formattedString = context.getString(R.string.pending_draft_one_week_1);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(8671)) {
                                                    formattedString = context.getString(R.string.pending_draft_one_week_2);
                                                }
                                            }
                                        }
                                    } else if ((ListenerUtil.mutListener.listen(8657) ? (dateLastUpdated >= oneDayAgo) : (ListenerUtil.mutListener.listen(8656) ? (dateLastUpdated <= oneDayAgo) : (ListenerUtil.mutListener.listen(8655) ? (dateLastUpdated > oneDayAgo) : (ListenerUtil.mutListener.listen(8654) ? (dateLastUpdated != oneDayAgo) : (ListenerUtil.mutListener.listen(8653) ? (dateLastUpdated == oneDayAgo) : (dateLastUpdated < oneDayAgo))))))) {
                                        // use any of the available 2 string formats, randomly
                                        Random randomNum = new Random();
                                        int result = randomNum.nextInt(2);
                                        if (!ListenerUtil.mutListener.listen(8665)) {
                                            if ((ListenerUtil.mutListener.listen(8662) ? (result >= 0) : (ListenerUtil.mutListener.listen(8661) ? (result <= 0) : (ListenerUtil.mutListener.listen(8660) ? (result > 0) : (ListenerUtil.mutListener.listen(8659) ? (result < 0) : (ListenerUtil.mutListener.listen(8658) ? (result != 0) : (result == 0))))))) {
                                                if (!ListenerUtil.mutListener.listen(8664)) {
                                                    formattedString = context.getString(R.string.pending_draft_one_day_1);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(8663)) {
                                                    formattedString = context.getString(R.string.pending_draft_one_day_2);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(8676)) {
                                    buildSinglePendingDraftNotification(context, post.getTitle(), formattedString, postId, isPage);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(8630)) {
                                    // (i.e. value for lastUpdated is zero) then just show a generic message
                                    buildSinglePendingDraftNotificationGeneric(context, post.getTitle(), postId, isPage);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String getPostTitle(Context context, String postTitle) {
        String title = postTitle;
        if (!ListenerUtil.mutListener.listen(8681)) {
            if (TextUtils.isEmpty(postTitle)) {
                if (!ListenerUtil.mutListener.listen(8680)) {
                    title = "(" + context.getResources().getText(R.string.untitled) + ")";
                }
            }
        }
        return title;
    }

    private void buildSinglePendingDraftNotification(Context context, String postTitle, String formattedMessage, int postId, boolean isPage) {
        if (!ListenerUtil.mutListener.listen(8682)) {
            buildSinglePendingDraftNotification(context, getResultIntentForOnePost(context, postId, isPage), String.format(formattedMessage, getPostTitle(context, postTitle)), postId, isPage);
        }
    }

    private void buildSinglePendingDraftNotificationGeneric(Context context, String postTitle, int postId, boolean isPage) {
        if (!ListenerUtil.mutListener.listen(8683)) {
            buildSinglePendingDraftNotification(context, getResultIntentForOnePost(context, postId, isPage), String.format(context.getString(R.string.pending_draft_one_generic), getPostTitle(context, postTitle)), postId, isPage);
        }
    }

    private PendingIntent getResultIntentForOnePost(Context context, int postId, boolean isPage) {
        Intent resultIntent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(8684)) {
            resultIntent.putExtra(WPMainActivity.ARG_OPENED_FROM_PUSH, true);
        }
        if (!ListenerUtil.mutListener.listen(8685)) {
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (!ListenerUtil.mutListener.listen(8686)) {
            resultIntent.setAction("android.intent.action.MAIN");
        }
        if (!ListenerUtil.mutListener.listen(8687)) {
            resultIntent.addCategory("android.intent.category.LAUNCHER");
        }
        if (!ListenerUtil.mutListener.listen(8688)) {
            resultIntent.putExtra(POST_ID_EXTRA, postId);
        }
        if (!ListenerUtil.mutListener.listen(8689)) {
            resultIntent.putExtra(IS_PAGE_EXTRA, isPage);
        }
        if (!ListenerUtil.mutListener.listen(8690)) {
            resultIntent.putExtra(ARG_NOTIFICATION_TYPE, NotificationType.PENDING_DRAFTS);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, BASE_REQUEST_CODE + PendingDraftsNotificationsUtils.makePendingDraftNotificationId(postId), resultIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        return pendingIntent;
    }

    private void buildSinglePendingDraftNotification(Context context, PendingIntent intent, String message, int postId, boolean isPage) {
        NotificationCompat.Builder builder = NativeNotificationsUtils.getBuilder(context, context.getString(R.string.notification_channel_important_id));
        if (!ListenerUtil.mutListener.listen(8691)) {
            builder.setContentText(message).setPriority(NotificationCompat.PRIORITY_MAX).setOnlyAlertOnce(true);
        }
        if (!ListenerUtil.mutListener.listen(8692)) {
            builder.setContentIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(8700)) {
            if ((ListenerUtil.mutListener.listen(8697) ? (postId >= 0) : (ListenerUtil.mutListener.listen(8696) ? (postId <= 0) : (ListenerUtil.mutListener.listen(8695) ? (postId > 0) : (ListenerUtil.mutListener.listen(8694) ? (postId < 0) : (ListenerUtil.mutListener.listen(8693) ? (postId == 0) : (postId != 0))))))) {
                if (!ListenerUtil.mutListener.listen(8698)) {
                    addOpenDraftActionForNotification(context, builder, postId, isPage);
                }
                if (!ListenerUtil.mutListener.listen(8699)) {
                    addIgnoreActionForNotification(context, builder, postId, isPage);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8701)) {
            addDismissActionForNotification(context, builder, postId, isPage);
        }
        if (!ListenerUtil.mutListener.listen(8702)) {
            NativeNotificationsUtils.showMessageToUserWithBuilder(builder, message, false, PendingDraftsNotificationsUtils.makePendingDraftNotificationId(postId), context);
        }
        if (!ListenerUtil.mutListener.listen(8703)) {
            mSystemNotificationsTracker.trackShownNotification(NotificationType.PENDING_DRAFTS);
        }
    }

    private void addOpenDraftActionForNotification(Context context, NotificationCompat.Builder builder, int postId, boolean isPage) {
        // adding open draft action
        Intent openDraftIntent = new Intent(context, WPMainActivity.class);
        if (!ListenerUtil.mutListener.listen(8704)) {
            openDraftIntent.putExtra(WPMainActivity.ARG_OPENED_FROM_PUSH, true);
        }
        if (!ListenerUtil.mutListener.listen(8705)) {
            openDraftIntent.putExtra(POST_ID_EXTRA, postId);
        }
        if (!ListenerUtil.mutListener.listen(8706)) {
            openDraftIntent.putExtra(IS_PAGE_EXTRA, isPage);
        }
        if (!ListenerUtil.mutListener.listen(8707)) {
            openDraftIntent.putExtra(ARG_NOTIFICATION_TYPE, NotificationType.PENDING_DRAFTS);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, // need to add + 1 so the request code is different, otherwise they overlap
        (ListenerUtil.mutListener.listen(8711) ? (BASE_REQUEST_CODE % 1) : (ListenerUtil.mutListener.listen(8710) ? (BASE_REQUEST_CODE / 1) : (ListenerUtil.mutListener.listen(8709) ? (BASE_REQUEST_CODE * 1) : (ListenerUtil.mutListener.listen(8708) ? (BASE_REQUEST_CODE - 1) : (BASE_REQUEST_CODE + 1))))) + PendingDraftsNotificationsUtils.makePendingDraftNotificationId(postId), openDraftIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(8712)) {
            builder.addAction(R.drawable.ic_pencil_white_24dp, context.getText(R.string.edit), pendingIntent);
        }
    }

    private void addIgnoreActionForNotification(Context context, NotificationCompat.Builder builder, int postId, boolean isPage) {
        // Call processing service when user taps on IGNORE - we should remember this decision for this post
        Intent ignoreIntent = new Intent(context, NotificationsProcessingService.class);
        if (!ListenerUtil.mutListener.listen(8713)) {
            ignoreIntent.putExtra(NotificationsProcessingService.ARG_ACTION_TYPE, NotificationsProcessingService.ARG_ACTION_DRAFT_PENDING_IGNORE);
        }
        if (!ListenerUtil.mutListener.listen(8714)) {
            ignoreIntent.putExtra(POST_ID_EXTRA, postId);
        }
        if (!ListenerUtil.mutListener.listen(8715)) {
            ignoreIntent.putExtra(IS_PAGE_EXTRA, isPage);
        }
        if (!ListenerUtil.mutListener.listen(8716)) {
            ignoreIntent.putExtra(ARG_NOTIFICATION_TYPE, NotificationType.PENDING_DRAFTS);
        }
        PendingIntent ignorePendingIntent = PendingIntent.getService(context, // need to add + 2 so the request code is different, otherwise they overlap
        (ListenerUtil.mutListener.listen(8720) ? (BASE_REQUEST_CODE % 2) : (ListenerUtil.mutListener.listen(8719) ? (BASE_REQUEST_CODE / 2) : (ListenerUtil.mutListener.listen(8718) ? (BASE_REQUEST_CODE * 2) : (ListenerUtil.mutListener.listen(8717) ? (BASE_REQUEST_CODE - 2) : (BASE_REQUEST_CODE + 2))))) + PendingDraftsNotificationsUtils.makePendingDraftNotificationId(postId), ignoreIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(8721)) {
            builder.addAction(R.drawable.ic_close_white_24dp, context.getText(R.string.ignore), ignorePendingIntent);
        }
    }

    private void addDismissActionForNotification(Context context, NotificationCompat.Builder builder, int postId, boolean isPage) {
        // Call processing service when notification is dismissed
        Intent notificationDeletedIntent = new Intent(context, NotificationsProcessingService.class);
        if (!ListenerUtil.mutListener.listen(8722)) {
            notificationDeletedIntent.putExtra(NotificationsProcessingService.ARG_ACTION_TYPE, NotificationsProcessingService.ARG_ACTION_DRAFT_PENDING_DISMISS);
        }
        if (!ListenerUtil.mutListener.listen(8723)) {
            notificationDeletedIntent.putExtra(POST_ID_EXTRA, postId);
        }
        if (!ListenerUtil.mutListener.listen(8724)) {
            notificationDeletedIntent.putExtra(IS_PAGE_EXTRA, isPage);
        }
        if (!ListenerUtil.mutListener.listen(8725)) {
            notificationDeletedIntent.putExtra(NotificationsProcessingService.ARG_NOTIFICATION_TYPE, NotificationType.PENDING_DRAFTS);
        }
        PendingIntent dismissPendingIntent = PendingIntent.getService(context, // need to add + 3 so the request code is different, otherwise they overlap
        (ListenerUtil.mutListener.listen(8729) ? (BASE_REQUEST_CODE % 3) : (ListenerUtil.mutListener.listen(8728) ? (BASE_REQUEST_CODE / 3) : (ListenerUtil.mutListener.listen(8727) ? (BASE_REQUEST_CODE * 3) : (ListenerUtil.mutListener.listen(8726) ? (BASE_REQUEST_CODE - 3) : (BASE_REQUEST_CODE + 3))))) + PendingDraftsNotificationsUtils.makePendingDraftNotificationId(postId), notificationDeletedIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(8730)) {
            builder.setDeleteIntent(dismissPendingIntent);
        }
    }
}
