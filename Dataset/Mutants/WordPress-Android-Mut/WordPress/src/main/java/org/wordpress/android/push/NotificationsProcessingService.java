package org.wordpress.android.push;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.action.CommentAction;
import org.wordpress.android.fluxc.generated.CommentActionBuilder;
import org.wordpress.android.fluxc.model.CommentModel;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.CommentStore.OnCommentChanged;
import org.wordpress.android.fluxc.store.CommentStore.RemoteCommentPayload;
import org.wordpress.android.fluxc.store.CommentStore.RemoteCreateCommentPayload;
import org.wordpress.android.fluxc.store.CommentStore.RemoteLikeCommentPayload;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.Note;
import org.wordpress.android.ui.comments.unified.CommentsStoreAdapter;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.notifications.receivers.NotificationsPendingDraftsReceiver;
import org.wordpress.android.ui.notifications.utils.NotificationsActions;
import org.wordpress.android.ui.notifications.utils.NotificationsUtils;
import org.wordpress.android.ui.notifications.utils.PendingDraftsNotificationsUtils;
import org.wordpress.android.ui.quickstart.QuickStartTracker;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.LocaleManager;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource;
import org.wordpress.android.util.analytics.AnalyticsUtils.QuickActionTrackPropertyValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import static org.wordpress.android.push.NotificationPushIds.GROUP_NOTIFICATION_ID;
import static org.wordpress.android.push.NotificationPushIds.QUICK_START_REMINDER_NOTIFICATION_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsProcessingService extends Service {

    public static final String ARG_ACTION_TYPE = "action_type";

    public static final String ARG_ACTION_LIKE = "action_like";

    public static final String ARG_ACTION_REPLY = "action_reply";

    public static final String ARG_ACTION_APPROVE = "action_approve";

    public static final String ARG_ACTION_AUTH_APPROVE = "action_auth_aprove";

    public static final String ARG_ACTION_AUTH_IGNORE = "action_auth_ignore";

    public static final String ARG_ACTION_DRAFT_PENDING_DISMISS = "action_draft_pending_dismiss";

    public static final String ARG_ACTION_DRAFT_PENDING_IGNORE = "action_draft_pending_ignore";

    public static final String ARG_ACTION_REPLY_TEXT = "action_reply_text";

    public static final String ARG_ACTION_NOTIFICATION_DISMISS = "action_dismiss";

    public static final String ARG_NOTE_ID = "note_id";

    public static final String ARG_PUSH_ID = "notificationId";

    public static final String ARG_NOTIFICATION_TYPE = "notificationType";

    // bundle and push ID, as they are held in the system dashboard
    public static final String ARG_NOTE_BUNDLE = "note_bundle";

    private QuickActionProcessor mQuickActionProcessor;

    private List<Long> mActionedCommentsRemoteIds = new ArrayList<>();

    @Inject
    CommentsStoreAdapter mCommentsStoreAdapter;

    @Inject
    SiteStore mSiteStore;

    @Inject
    SystemNotificationsTracker mSystemNotificationsTracker;

    @Inject
    GCMMessageHandler mGCMMessageHandler;

    @Inject
    QuickStartTracker mQuickStartTracker;

    /*
    * Use this if you want the service to handle a background note Like.
    * */
    public static void startServiceForLike(Context context, String noteId) {
        Intent intent = new Intent(context, NotificationsProcessingService.class);
        if (!ListenerUtil.mutListener.listen(3124)) {
            intent.putExtra(ARG_ACTION_TYPE, ARG_ACTION_LIKE);
        }
        if (!ListenerUtil.mutListener.listen(3125)) {
            intent.putExtra(ARG_NOTE_ID, noteId);
        }
        if (!ListenerUtil.mutListener.listen(3126)) {
            context.startService(intent);
        }
    }

    /*
    * Use this if you want the service to handle a background note Approve.
    * */
    public static void startServiceForApprove(Context context, String noteId) {
        Intent intent = new Intent(context, NotificationsProcessingService.class);
        if (!ListenerUtil.mutListener.listen(3127)) {
            intent.putExtra(ARG_ACTION_TYPE, ARG_ACTION_APPROVE);
        }
        if (!ListenerUtil.mutListener.listen(3128)) {
            intent.putExtra(ARG_NOTE_ID, noteId);
        }
        if (!ListenerUtil.mutListener.listen(3129)) {
            context.startService(intent);
        }
    }

    /*
    * Use this if you want the service to handle a background note reply.
    * */
    public static void startServiceForReply(Context context, String noteId, String replyToComment) {
        Intent intent = new Intent(context, NotificationsProcessingService.class);
        if (!ListenerUtil.mutListener.listen(3130)) {
            intent.putExtra(ARG_ACTION_TYPE, ARG_ACTION_REPLY);
        }
        if (!ListenerUtil.mutListener.listen(3131)) {
            intent.putExtra(ARG_NOTE_ID, noteId);
        }
        if (!ListenerUtil.mutListener.listen(3132)) {
            intent.putExtra(ARG_ACTION_REPLY_TEXT, replyToComment);
        }
        if (!ListenerUtil.mutListener.listen(3133)) {
            context.startService(intent);
        }
    }

    public static PendingIntent getPendingIntentForNotificationDismiss(Context context, int pushId, NotificationType notificationType) {
        Intent intent = new Intent(context, NotificationsProcessingService.class);
        if (!ListenerUtil.mutListener.listen(3134)) {
            intent.putExtra(ARG_ACTION_TYPE, ARG_ACTION_NOTIFICATION_DISMISS);
        }
        if (!ListenerUtil.mutListener.listen(3135)) {
            intent.putExtra(ARG_PUSH_ID, pushId);
        }
        if (!ListenerUtil.mutListener.listen(3136)) {
            intent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
        }
        if (!ListenerUtil.mutListener.listen(3137)) {
            intent.addCategory(ARG_ACTION_NOTIFICATION_DISMISS);
        }
        return PendingIntent.getService(context, pushId, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    public static void stopService(Context context) {
        if (!ListenerUtil.mutListener.listen(3138)) {
            if (context == null) {
                return;
            }
        }
        Intent intent = new Intent(context, NotificationsProcessingService.class);
        if (!ListenerUtil.mutListener.listen(3139)) {
            context.stopService(intent);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(3140)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(3141)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(3142)) {
            mCommentsStoreAdapter.register(this);
        }
        if (!ListenerUtil.mutListener.listen(3143)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications action processing service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3144)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications action processing service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(3145)) {
            mCommentsStoreAdapter.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(3146)) {
            super.onDestroy();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(3147)) {
            mQuickActionProcessor = new QuickActionProcessor(this, mSystemNotificationsTracker, mGCMMessageHandler, intent, startId);
        }
        if (!ListenerUtil.mutListener.listen(3148)) {
            new Thread(() -> mQuickActionProcessor.process()).start();
        }
        return START_NOT_STICKY;
    }

    private class QuickActionProcessor {

        private SystemNotificationsTracker mSystemNotificationsTracker;

        private GCMMessageHandler mGCMMessageHandler;

        private String mNoteId;

        private String mReplyText;

        private String mActionType;

        private NotificationType mNotificationType;

        private int mPushId;

        private Note mNote;

        private final int mTaskId;

        private final Context mContext;

        private final Intent mIntent;

        QuickActionProcessor(Context ctx, SystemNotificationsTracker notificationsTracker, GCMMessageHandler gcmMessageHandler, Intent intent, int taskId) {
            mContext = ctx;
            if (!ListenerUtil.mutListener.listen(3149)) {
                mSystemNotificationsTracker = notificationsTracker;
            }
            mIntent = intent;
            mTaskId = taskId;
            if (!ListenerUtil.mutListener.listen(3150)) {
                this.mGCMMessageHandler = gcmMessageHandler;
            }
        }

        public void process() {
            if (!ListenerUtil.mutListener.listen(3151)) {
                getDataFromIntent();
            }
            if (!ListenerUtil.mutListener.listen(3189)) {
                // now handle each action
                if (mActionType != null) {
                    if (!ListenerUtil.mutListener.listen(3158)) {
                        // check special cases for authorization push
                        if (mActionType.equals(ARG_ACTION_AUTH_IGNORE)) {
                            if (!ListenerUtil.mutListener.listen(3153)) {
                                // dismiss notifs
                                NativeNotificationsUtils.dismissNotification(NotificationPushIds.ACTIONS_RESULT_NOTIFICATION_ID, mContext);
                            }
                            if (!ListenerUtil.mutListener.listen(3154)) {
                                NativeNotificationsUtils.dismissNotification(NotificationPushIds.AUTH_PUSH_NOTIFICATION_ID, mContext);
                            }
                            if (!ListenerUtil.mutListener.listen(3155)) {
                                NativeNotificationsUtils.dismissNotification(NotificationPushIds.ACTIONS_PROGRESS_NOTIFICATION_ID, mContext);
                            }
                            if (!ListenerUtil.mutListener.listen(3156)) {
                                mGCMMessageHandler.removeNotification(NotificationPushIds.AUTH_PUSH_NOTIFICATION_ID);
                            }
                            if (!ListenerUtil.mutListener.listen(3157)) {
                                AnalyticsTracker.track(AnalyticsTracker.Stat.PUSH_AUTHENTICATION_IGNORED);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3167)) {
                        // check notification dismissed pending intent
                        if (mActionType.equals(ARG_ACTION_NOTIFICATION_DISMISS)) {
                            if (!ListenerUtil.mutListener.listen(3160)) {
                                if (mNotificationType != null) {
                                    if (!ListenerUtil.mutListener.listen(3159)) {
                                        mSystemNotificationsTracker.trackDismissedNotification(mNotificationType);
                                    }
                                }
                            }
                            int notificationId = mIntent.getIntExtra(ARG_PUSH_ID, 0);
                            if (!ListenerUtil.mutListener.listen(3166)) {
                                if (notificationId == GROUP_NOTIFICATION_ID) {
                                    if (!ListenerUtil.mutListener.listen(3165)) {
                                        mGCMMessageHandler.clearNotifications();
                                    }
                                } else if (notificationId == QUICK_START_REMINDER_NOTIFICATION_ID) {
                                    if (!ListenerUtil.mutListener.listen(3164)) {
                                        mQuickStartTracker.track(Stat.QUICK_START_NOTIFICATION_DISMISSED);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3161)) {
                                        mGCMMessageHandler.removeNotification(notificationId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3163)) {
                                        // Dismiss the grouped notification if a user dismisses all notifications from a wear device
                                        if (!mGCMMessageHandler.hasNotifications()) {
                                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
                                            if (!ListenerUtil.mutListener.listen(3162)) {
                                                notificationManager.cancel(GROUP_NOTIFICATION_ID);
                                            }
                                        }
                                    }
                                }
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3176)) {
                        // check special cases for pending draft notifications - ignore
                        if (mActionType.equals(ARG_ACTION_DRAFT_PENDING_IGNORE)) {
                            // dismiss notif
                            int postId = mIntent.getIntExtra(NotificationsPendingDraftsReceiver.POST_ID_EXTRA, 0);
                            if (!ListenerUtil.mutListener.listen(3174)) {
                                if ((ListenerUtil.mutListener.listen(3172) ? (postId >= 0) : (ListenerUtil.mutListener.listen(3171) ? (postId <= 0) : (ListenerUtil.mutListener.listen(3170) ? (postId > 0) : (ListenerUtil.mutListener.listen(3169) ? (postId < 0) : (ListenerUtil.mutListener.listen(3168) ? (postId == 0) : (postId != 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(3173)) {
                                        NativeNotificationsUtils.dismissNotification(PendingDraftsNotificationsUtils.makePendingDraftNotificationId(postId), mContext);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3175)) {
                                AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_PENDING_DRAFTS_IGNORED);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3178)) {
                        // check special cases for pending draft notifications - dismiss
                        if (mActionType.equals(ARG_ACTION_DRAFT_PENDING_DISMISS)) {
                            if (!ListenerUtil.mutListener.listen(3177)) {
                                AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_PENDING_DRAFTS_DISMISSED);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3180)) {
                        if (mActionType.equals(ARG_ACTION_REPLY)) {
                        } else {
                            if (!ListenerUtil.mutListener.listen(3179)) {
                                NativeNotificationsUtils.showIntermediateMessageToUser(getProcessingTitleForAction(mActionType), mContext, mNotificationType);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3188)) {
                        // if we still don't have a Note, go get it from the REST API
                        if (mNote == null) {
                            RestRequest.Listener listener = new RestRequest.Listener() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    if (!ListenerUtil.mutListener.listen(3185)) {
                                        if ((ListenerUtil.mutListener.listen(3182) ? (response != null || !response.optBoolean("success")) : (response != null && !response.optBoolean("success")))) {
                                            if (!ListenerUtil.mutListener.listen(3183)) {
                                                // build the Note object here
                                                buildNoteFromJSONObject(response);
                                            }
                                            if (!ListenerUtil.mutListener.listen(3184)) {
                                                performRequestedAction();
                                            }
                                        }
                                    }
                                }
                            };
                            RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    if (!ListenerUtil.mutListener.listen(3186)) {
                                        requestFailed(mActionType);
                                    }
                                }
                            };
                            if (!ListenerUtil.mutListener.listen(3187)) {
                                getNoteFromNoteId(mNoteId, listener, errorListener);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3181)) {
                                // we have a Note! just go ahead and perform the requested action
                                performRequestedAction();
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3152)) {
                        requestFailed(null);
                    }
                }
            }
        }

        private void getNoteFromBundleIfExists() {
            if (!ListenerUtil.mutListener.listen(3191)) {
                if (mIntent.hasExtra(ARG_NOTE_BUNDLE)) {
                    Bundle payload = mIntent.getBundleExtra(ARG_NOTE_BUNDLE);
                    if (!ListenerUtil.mutListener.listen(3190)) {
                        mNote = NotificationsUtils.buildNoteObjectFromBundle(payload);
                    }
                }
            }
        }

        private void getDataFromIntent() {
            if (!ListenerUtil.mutListener.listen(3192)) {
                if (mIntent == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(3193)) {
                // get all needed data from intent
                mNoteId = mIntent.getStringExtra(ARG_NOTE_ID);
            }
            if (!ListenerUtil.mutListener.listen(3194)) {
                mActionType = mIntent.getStringExtra(ARG_ACTION_TYPE);
            }
            if (!ListenerUtil.mutListener.listen(3195)) {
                // notif in active notifs map (there is only one notif if quick actions are available)
                mPushId = GROUP_NOTIFICATION_ID;
            }
            if (!ListenerUtil.mutListener.listen(3197)) {
                if (mIntent.hasExtra(ARG_ACTION_REPLY_TEXT)) {
                    if (!ListenerUtil.mutListener.listen(3196)) {
                        mReplyText = mIntent.getStringExtra(ARG_ACTION_REPLY_TEXT);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3199)) {
                if (mIntent.hasExtra(ARG_NOTIFICATION_TYPE)) {
                    if (!ListenerUtil.mutListener.listen(3198)) {
                        mNotificationType = (NotificationType) mIntent.getSerializableExtra(ARG_NOTIFICATION_TYPE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3202)) {
                if (TextUtils.isEmpty(mReplyText)) {
                    // same thing with direct-reply in Android 7
                    Bundle remoteInput = RemoteInput.getResultsFromIntent(mIntent);
                    if (!ListenerUtil.mutListener.listen(3201)) {
                        if (remoteInput != null) {
                            if (!ListenerUtil.mutListener.listen(3200)) {
                                obtainReplyTextFromRemoteInputBundle(remoteInput);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3203)) {
                // bundle. If we have it, no need to go fetch it through REST API.
                getNoteFromBundleIfExists();
            }
        }

        private String getProcessingTitleForAction(String actionType) {
            if (actionType != null) {
                switch(actionType) {
                    case ARG_ACTION_LIKE:
                        return getString(R.string.comment_q_action_liking);
                    case ARG_ACTION_APPROVE:
                        return getString(R.string.comment_q_action_approving);
                    case ARG_ACTION_REPLY:
                        return getString(R.string.comment_q_action_replying);
                    default:
                        // default, generic "processing"
                        return getString(R.string.comment_q_action_processing);
                }
            } else {
                // default, generic "processing"
                return getString(R.string.comment_q_action_processing);
            }
        }

        private void obtainReplyTextFromRemoteInputBundle(Bundle bundle) {
            CharSequence replyText = bundle.getCharSequence(GCMMessageService.EXTRA_VOICE_OR_INLINE_REPLY);
            if (!ListenerUtil.mutListener.listen(3205)) {
                if (replyText != null) {
                    if (!ListenerUtil.mutListener.listen(3204)) {
                        mReplyText = replyText.toString();
                    }
                }
            }
        }

        private void buildNoteFromJSONObject(JSONObject jsonObject) {
            try {
                if (!ListenerUtil.mutListener.listen(3210)) {
                    if (jsonObject.has("notes")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("notes");
                        if (!ListenerUtil.mutListener.listen(3209)) {
                            if ((ListenerUtil.mutListener.listen(3207) ? (jsonArray != null || jsonArray.length() == 1) : (jsonArray != null && jsonArray.length() == 1))) {
                                if (!ListenerUtil.mutListener.listen(3208)) {
                                    jsonObject = jsonArray.getJSONObject(0);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3211)) {
                    mNote = new Note(mNoteId, jsonObject);
                }
            } catch (JSONException e) {
                if (!ListenerUtil.mutListener.listen(3206)) {
                    AppLog.e(AppLog.T.NOTIFS, e.getMessage());
                }
            }
        }

        private void performRequestedAction() {
            if (!ListenerUtil.mutListener.listen(3220)) {
                /**
                 * *****************************************************
                 */
                if (mNote != null) {
                    if (!ListenerUtil.mutListener.listen(3219)) {
                        if (mActionType != null) {
                            if (!ListenerUtil.mutListener.listen(3218)) {
                                switch(mActionType) {
                                    case ARG_ACTION_LIKE:
                                        if (!ListenerUtil.mutListener.listen(3214)) {
                                            likeComment();
                                        }
                                        break;
                                    case ARG_ACTION_APPROVE:
                                        if (!ListenerUtil.mutListener.listen(3215)) {
                                            approveComment();
                                        }
                                        break;
                                    case ARG_ACTION_REPLY:
                                        if (!ListenerUtil.mutListener.listen(3216)) {
                                            replyToComment();
                                        }
                                        break;
                                    default:
                                        if (!ListenerUtil.mutListener.listen(3217)) {
                                            // no op
                                            requestFailed(null);
                                        }
                                        break;
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3213)) {
                                // no op
                                requestFailed(null);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3212)) {
                        requestFailed(null);
                    }
                }
            }
        }

        /*
         * called when action has been completed successfully
         */
        private void requestCompleted(String actionType) {
            String successMessage = null;
            if (!ListenerUtil.mutListener.listen(3226)) {
                if (actionType != null) {
                    if (!ListenerUtil.mutListener.listen(3225)) {
                        if (actionType.equals(ARG_ACTION_LIKE)) {
                            if (!ListenerUtil.mutListener.listen(3224)) {
                                successMessage = getString(R.string.comment_liked);
                            }
                        } else if (actionType.equals(ARG_ACTION_APPROVE)) {
                            if (!ListenerUtil.mutListener.listen(3223)) {
                                successMessage = getString(R.string.comment_moderated_approved);
                            }
                        } else if (actionType.equals(ARG_ACTION_REPLY)) {
                            if (!ListenerUtil.mutListener.listen(3222)) {
                                successMessage = getString(R.string.note_reply_successful);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3221)) {
                        // show generic success message here
                        successMessage = getString(R.string.comment_q_action_done_generic);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3227)) {
                NotificationsActions.markNoteAsRead(mNote);
            }
            if (!ListenerUtil.mutListener.listen(3228)) {
                // dismiss any other pending result notification
                NativeNotificationsUtils.dismissNotification(NotificationPushIds.ACTIONS_RESULT_NOTIFICATION_ID, mContext);
            }
            if (!ListenerUtil.mutListener.listen(3229)) {
                // update notification indicating the operation succeeded
                NativeNotificationsUtils.showFinalMessageToUser(successMessage, NotificationPushIds.ACTIONS_PROGRESS_NOTIFICATION_ID, mContext, NotificationType.ACTIONS_PROGRESS);
            }
            if (!ListenerUtil.mutListener.listen(3230)) {
                // remove the original notification from the system bar
                mGCMMessageHandler.removeNotificationWithNoteIdFromSystemBar(mContext, mNoteId);
            }
            // after 3 seconds, dismiss the notification that indicated success
            Handler handler = new Handler(getMainLooper());
            if (!ListenerUtil.mutListener.listen(3232)) {
                handler.postDelayed(new Runnable() {

                    public void run() {
                        if (!ListenerUtil.mutListener.listen(3231)) {
                            NativeNotificationsUtils.dismissNotification(NotificationPushIds.ACTIONS_PROGRESS_NOTIFICATION_ID, mContext);
                        }
                    }
                }, // show the success message for 3 seconds, then dismiss
                3000);
            }
            if (!ListenerUtil.mutListener.listen(3233)) {
                stopSelf(mTaskId);
            }
        }

        /*
         * called when action failed
         */
        private void requestFailed(String actionType) {
            String errorMessage = null;
            if (!ListenerUtil.mutListener.listen(3239)) {
                if (actionType != null) {
                    if (!ListenerUtil.mutListener.listen(3238)) {
                        if (actionType.equals(ARG_ACTION_LIKE)) {
                            if (!ListenerUtil.mutListener.listen(3237)) {
                                errorMessage = getString(R.string.error_notif_q_action_like);
                            }
                        } else if (actionType.equals(ARG_ACTION_APPROVE)) {
                            if (!ListenerUtil.mutListener.listen(3236)) {
                                errorMessage = getString(R.string.error_notif_q_action_approve);
                            }
                        } else if (actionType.equals(ARG_ACTION_REPLY)) {
                            if (!ListenerUtil.mutListener.listen(3235)) {
                                errorMessage = getString(R.string.error_notif_q_action_reply);
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3234)) {
                        // show generic error here
                        errorMessage = getString(R.string.error_generic);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3240)) {
                resetOriginalNotification();
            }
            if (!ListenerUtil.mutListener.listen(3241)) {
                NativeNotificationsUtils.dismissNotification(NotificationPushIds.ACTIONS_PROGRESS_NOTIFICATION_ID, mContext);
            }
            if (!ListenerUtil.mutListener.listen(3242)) {
                NativeNotificationsUtils.showFinalMessageToUser(errorMessage, NotificationPushIds.ACTIONS_RESULT_NOTIFICATION_ID, mContext, NotificationType.ACTIONS_RESULT);
            }
            // after 3 seconds, dismiss the error message notification
            Handler handler = new Handler(getMainLooper());
            if (!ListenerUtil.mutListener.listen(3244)) {
                handler.postDelayed(new Runnable() {

                    public void run() {
                        if (!ListenerUtil.mutListener.listen(3243)) {
                            // remove the error notification from the system bar
                            NativeNotificationsUtils.dismissNotification(NotificationPushIds.ACTIONS_RESULT_NOTIFICATION_ID, mContext);
                        }
                    }
                }, // show the success message for 3 seconds, then dismiss
                3000);
            }
            if (!ListenerUtil.mutListener.listen(3245)) {
                stopSelf(mTaskId);
            }
        }

        private void requestFailedWithMessage(String errorMessage, boolean autoDismiss) {
            if (!ListenerUtil.mutListener.listen(3247)) {
                if (errorMessage == null) {
                    if (!ListenerUtil.mutListener.listen(3246)) {
                        // show generic error here
                        errorMessage = getString(R.string.error_generic);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3248)) {
                resetOriginalNotification();
            }
            if (!ListenerUtil.mutListener.listen(3249)) {
                NativeNotificationsUtils.showFinalMessageToUser(errorMessage, NotificationPushIds.ACTIONS_RESULT_NOTIFICATION_ID, mContext, NotificationType.ACTIONS_RESULT);
            }
            if (!ListenerUtil.mutListener.listen(3252)) {
                if (autoDismiss) {
                    // after 3 seconds, dismiss the error message notification
                    Handler handler = new Handler(getMainLooper());
                    if (!ListenerUtil.mutListener.listen(3251)) {
                        handler.postDelayed(new Runnable() {

                            public void run() {
                                if (!ListenerUtil.mutListener.listen(3250)) {
                                    // remove the error notification from the system bar
                                    NativeNotificationsUtils.dismissNotification(NotificationPushIds.ACTIONS_RESULT_NOTIFICATION_ID, mContext);
                                }
                            }
                        }, // show the success message for 3 seconds, then dismiss
                        3000);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3253)) {
                stopSelf(mTaskId);
            }
        }

        private void keepRemoteCommentIdForPostProcessing(long remoteCommendId) {
            if (!ListenerUtil.mutListener.listen(3255)) {
                if (!mActionedCommentsRemoteIds.contains(remoteCommendId)) {
                    if (!ListenerUtil.mutListener.listen(3254)) {
                        mActionedCommentsRemoteIds.add(remoteCommendId);
                    }
                }
            }
        }

        private void getNoteFromNoteId(String noteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
            if (!ListenerUtil.mutListener.listen(3256)) {
                if (noteId == null) {
                    return;
                }
            }
            HashMap<String, String> params = new HashMap<>();
            if (!ListenerUtil.mutListener.listen(3257)) {
                params.put("locale", LocaleManager.getLanguage(mContext));
            }
            if (!ListenerUtil.mutListener.listen(3258)) {
                WordPress.getRestClientUtils().getNotification(params, noteId, listener, errorListener);
            }
        }

        // Like or unlike a comment via the REST API
        private void likeComment() {
            if (!ListenerUtil.mutListener.listen(3260)) {
                if (mNote == null) {
                    if (!ListenerUtil.mutListener.listen(3259)) {
                        requestFailed(ARG_ACTION_LIKE);
                    }
                    return;
                }
            }
            SiteModel site = mSiteStore.getSiteBySiteId(mNote.getSiteId());
            if (!ListenerUtil.mutListener.listen(3261)) {
                // TODO klymyam remove legacy comment tracking after new comments are shipped and new funnels are made
                AnalyticsUtils.trackWithBlogPostDetails(AnalyticsTracker.Stat.NOTIFICATION_QUICK_ACTIONS_LIKED, mNote.getSiteId(), mNote.getPostId());
            }
            if (!ListenerUtil.mutListener.listen(3262)) {
                AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_QUICK_ACTION_LIKED, AnalyticsCommentActionSource.NOTIFICATIONS, site);
            }
            if (!ListenerUtil.mutListener.listen(3263)) {
                AnalyticsUtils.trackQuickActionTouched(QuickActionTrackPropertyValue.LIKE, site, mNote.buildComment());
            }
            if (!ListenerUtil.mutListener.listen(3267)) {
                if (site != null) {
                    if (!ListenerUtil.mutListener.listen(3266)) {
                        mCommentsStoreAdapter.dispatch(CommentActionBuilder.newLikeCommentAction(new RemoteLikeCommentPayload(site, mNote.getCommentId(), true)));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3264)) {
                        requestFailed(ARG_ACTION_LIKE);
                    }
                    if (!ListenerUtil.mutListener.listen(3265)) {
                        AppLog.e(T.NOTIFS, "Site with id: " + mNote.getSiteId() + " doesn't exist in the Site store");
                    }
                }
            }
        }

        private void approveComment() {
            if (!ListenerUtil.mutListener.listen(3269)) {
                if (mNote == null) {
                    if (!ListenerUtil.mutListener.listen(3268)) {
                        requestFailed(ARG_ACTION_APPROVE);
                    }
                    return;
                }
            }
            SiteModel site = mSiteStore.getSiteBySiteId(mNote.getSiteId());
            if (!ListenerUtil.mutListener.listen(3270)) {
                // TODO klymyam remove legacy comment tracking after new comments are shipped and new funnels are made
                AnalyticsUtils.trackWithBlogPostDetails(AnalyticsTracker.Stat.NOTIFICATION_QUICK_ACTIONS_APPROVED, mNote.getSiteId(), mNote.getPostId());
            }
            if (!ListenerUtil.mutListener.listen(3271)) {
                AnalyticsUtils.trackCommentActionWithSiteDetails(Stat.COMMENT_QUICK_ACTION_APPROVED, AnalyticsCommentActionSource.NOTIFICATIONS, site);
            }
            if (!ListenerUtil.mutListener.listen(3272)) {
                AnalyticsUtils.trackQuickActionTouched(QuickActionTrackPropertyValue.APPROVE, site, mNote.buildComment());
            }
            // Update pseudo comment (built from the note)
            CommentModel comment = mNote.buildComment();
            if (!ListenerUtil.mutListener.listen(3273)) {
                comment.setStatus(CommentStatus.APPROVED.toString());
            }
            if (!ListenerUtil.mutListener.listen(3276)) {
                if (site == null) {
                    if (!ListenerUtil.mutListener.listen(3274)) {
                        AppLog.e(T.NOTIFS, "Impossible to approve a comment on a site that is not in the App. SiteId: " + mNote.getSiteId());
                    }
                    if (!ListenerUtil.mutListener.listen(3275)) {
                        requestFailed(ARG_ACTION_APPROVE);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(3277)) {
                // or not
                keepRemoteCommentIdForPostProcessing(comment.getRemoteCommentId());
            }
            if (!ListenerUtil.mutListener.listen(3278)) {
                // Push the comment
                mCommentsStoreAdapter.dispatch(CommentActionBuilder.newPushCommentAction(new RemoteCommentPayload(site, comment)));
            }
        }

        private void replyToComment() {
            if (!ListenerUtil.mutListener.listen(3280)) {
                if (mNote == null) {
                    if (!ListenerUtil.mutListener.listen(3279)) {
                        requestFailed(ARG_ACTION_APPROVE);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(3281)) {
                if (TextUtils.isEmpty(mReplyText))
                    return;
            }
            SiteModel site = mSiteStore.getSiteBySiteId(mNote.getSiteId());
            if (!ListenerUtil.mutListener.listen(3284)) {
                if (site == null) {
                    if (!ListenerUtil.mutListener.listen(3282)) {
                        AppLog.e(T.NOTIFS, "Impossible to reply to a comment on a site that is not in the App." + " SiteId: " + mNote.getSiteId());
                    }
                    if (!ListenerUtil.mutListener.listen(3283)) {
                        requestFailed(ARG_ACTION_APPROVE);
                    }
                    return;
                }
            }
            // Pseudo comment (built from the note)
            CommentModel comment = mNote.buildComment();
            // Pseudo comment reply
            CommentModel reply = new CommentModel();
            if (!ListenerUtil.mutListener.listen(3285)) {
                reply.setContent(mReplyText);
            }
            // Push the reply
            RemoteCreateCommentPayload payload = new RemoteCreateCommentPayload(site, comment, reply);
            if (!ListenerUtil.mutListener.listen(3286)) {
                mCommentsStoreAdapter.dispatch(CommentActionBuilder.newCreateNewCommentAction(payload));
            }
            if (!ListenerUtil.mutListener.listen(3287)) {
                // Bump analytics
                AnalyticsUtils.trackCommentReplyWithDetails(true, site, comment, AnalyticsCommentActionSource.NOTIFICATIONS);
            }
            if (!ListenerUtil.mutListener.listen(3288)) {
                AnalyticsUtils.trackQuickActionTouched(QuickActionTrackPropertyValue.REPLY_TO, site, comment);
            }
        }

        private void resetOriginalNotification() {
            if (!ListenerUtil.mutListener.listen(3289)) {
                mGCMMessageHandler.rebuildAndUpdateNotificationsOnSystemBarForThisNote(mContext, mNoteId);
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCommentChanged(OnCommentChanged event) {
        if (!ListenerUtil.mutListener.listen(3290)) {
            if (mQuickActionProcessor == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3313)) {
            // quick action that corresponds to the resulting FluxC event.
            if (event.causeOfChange == CommentAction.PUSH_COMMENT) {
                List<Long> eventChangedCommentsRemoteIds = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(3310)) {
                    if ((ListenerUtil.mutListener.listen(3301) ? (mActionedCommentsRemoteIds.size() >= 0) : (ListenerUtil.mutListener.listen(3300) ? (mActionedCommentsRemoteIds.size() <= 0) : (ListenerUtil.mutListener.listen(3299) ? (mActionedCommentsRemoteIds.size() < 0) : (ListenerUtil.mutListener.listen(3298) ? (mActionedCommentsRemoteIds.size() != 0) : (ListenerUtil.mutListener.listen(3297) ? (mActionedCommentsRemoteIds.size() == 0) : (mActionedCommentsRemoteIds.size() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(3304)) {
                            {
                                long _loopCounter116 = 0;
                                // prepare a comparable list of Ids
                                for (Integer commentLocalId : event.changedCommentsLocalIds) {
                                    ListenerUtil.loopListener.listen("_loopCounter116", ++_loopCounter116);
                                    CommentModel localComment = mCommentsStoreAdapter.getCommentByLocalId(commentLocalId);
                                    if (!ListenerUtil.mutListener.listen(3303)) {
                                        if (localComment != null) {
                                            if (!ListenerUtil.mutListener.listen(3302)) {
                                                eventChangedCommentsRemoteIds.add(localComment.getRemoteCommentId());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3309)) {
                            {
                                long _loopCounter117 = 0;
                                // here we need to check ids: is an event corresponding to an action triggered from this Service?
                                for (Long oneEventCommentRemoteId : eventChangedCommentsRemoteIds) {
                                    ListenerUtil.loopListener.listen("_loopCounter117", ++_loopCounter117);
                                    if (!ListenerUtil.mutListener.listen(3308)) {
                                        if (mActionedCommentsRemoteIds.contains(oneEventCommentRemoteId)) {
                                            if (!ListenerUtil.mutListener.listen(3307)) {
                                                if (event.isError()) {
                                                    if (!ListenerUtil.mutListener.listen(3306)) {
                                                        mQuickActionProcessor.requestFailed(ARG_ACTION_APPROVE);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(3305)) {
                                                        mQuickActionProcessor.requestCompleted(ARG_ACTION_APPROVE);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3312)) {
                    {
                        long _loopCounter118 = 0;
                        // now remove any remoteIds for already processed actions
                        for (Long remoteId : eventChangedCommentsRemoteIds) {
                            ListenerUtil.loopListener.listen("_loopCounter118", ++_loopCounter118);
                            if (!ListenerUtil.mutListener.listen(3311)) {
                                mActionedCommentsRemoteIds.remove(remoteId);
                            }
                        }
                    }
                }
            } else if (event.causeOfChange == CommentAction.LIKE_COMMENT) {
                if (!ListenerUtil.mutListener.listen(3296)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(3295)) {
                            mQuickActionProcessor.requestFailed(ARG_ACTION_LIKE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3294)) {
                            mQuickActionProcessor.requestCompleted(ARG_ACTION_LIKE);
                        }
                    }
                }
            } else if (event.causeOfChange == CommentAction.CREATE_NEW_COMMENT) {
                if (!ListenerUtil.mutListener.listen(3293)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(3292)) {
                            mQuickActionProcessor.requestFailed(ARG_ACTION_REPLY);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3291)) {
                            mQuickActionProcessor.requestCompleted(ARG_ACTION_REPLY);
                        }
                    }
                }
            }
        }
    }
}
