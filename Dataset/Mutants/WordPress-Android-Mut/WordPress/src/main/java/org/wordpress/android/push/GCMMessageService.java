package org.wordpress.android.push;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.util.Map;
import javax.inject.Inject;
import static org.wordpress.android.push.GCMMessageHandler.PUSH_TYPE_ZENDESK;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GCMMessageService extends FirebaseMessagingService {

    public static final String EXTRA_VOICE_OR_INLINE_REPLY = "extra_voice_or_inline_reply";

    public static final String PUSH_ARG_NOTE_ID = "note_id";

    public static final String PUSH_ARG_NOTE_FULL_DATA = "note_full_data";

    private static final String PUSH_ARG_ZENDESK_REQUEST_ID = "zendesk_sdk_request_id";

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    ZendeskHelper mZendeskHelper;

    @Inject
    SystemNotificationsTracker mSystemNotificationsTracker;

    @Inject
    GCMMessageHandler mGCMMessageHandler;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(3080)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(3081)) {
            ((WordPress) getApplication()).component().inject(this);
        }
    }

    private void synchronizedHandleDefaultPush(@NonNull Map<String, String> data) {
        if (!ListenerUtil.mutListener.listen(3082)) {
            // ACTIVE_NOTIFICATIONS_MAP being static, we can't just synchronize the method
            mSystemNotificationsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_RECEIVED_PROCESSING_START);
        }
        synchronized (GCMMessageService.class) {
            if (!ListenerUtil.mutListener.listen(3083)) {
                mGCMMessageHandler.handleDefaultPush(this, convertMapToBundle(data), mAccountStore.getAccount().getUserId());
            }
        }
        if (!ListenerUtil.mutListener.listen(3084)) {
            mSystemNotificationsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_RECEIVED_PROCESSING_END);
        }
    }

    // convert FCM RemoteMessage's Map into legacy GCM Bundle to keep code changes to a minimum
    private Bundle convertMapToBundle(@NonNull Map<String, String> data) {
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(3086)) {
            {
                long _loopCounter115 = 0;
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter115", ++_loopCounter115);
                    if (!ListenerUtil.mutListener.listen(3085)) {
                        bundle.putString(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return bundle;
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        Map data = message.getData();
        if (!ListenerUtil.mutListener.listen(3087)) {
            AppLog.v(T.NOTIFS, "Received Message");
        }
        if (!ListenerUtil.mutListener.listen(3089)) {
            if (data == null) {
                if (!ListenerUtil.mutListener.listen(3088)) {
                    AppLog.v(T.NOTIFS, "No notification message content received. Aborting.");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3090)) {
            if (!mAccountStore.hasAccessToken()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3093)) {
            if (PUSH_TYPE_ZENDESK.equals(String.valueOf(data.get("type")))) {
                String zendeskRequestId = String.valueOf(data.get(PUSH_ARG_ZENDESK_REQUEST_ID));
                if (!ListenerUtil.mutListener.listen(3092)) {
                    // Try to refresh the Zendesk request page if it's currently being displayed; otherwise show a notification
                    if (!mZendeskHelper.refreshRequest(this, zendeskRequestId)) {
                        if (!ListenerUtil.mutListener.listen(3091)) {
                            mGCMMessageHandler.handleZendeskNotification(this);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3094)) {
            synchronizedHandleDefaultPush(data);
        }
    }
}
