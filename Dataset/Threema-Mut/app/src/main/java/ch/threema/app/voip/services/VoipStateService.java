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
package ch.threema.app.voip.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import androidx.annotation.AnyThread;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.push.PushService;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DNDUtil;
import ch.threema.app.utils.IdUtil;
import ch.threema.app.utils.MediaPlayerStateWrapper;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.voip.CallState;
import ch.threema.app.voip.CallStateSnapshot;
import ch.threema.app.voip.Config;
import ch.threema.app.voip.activities.CallActivity;
import ch.threema.app.voip.managers.VoipListenerManager;
import ch.threema.app.voip.receivers.CallRejectReceiver;
import ch.threema.app.voip.receivers.VoipMediaButtonReceiver;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.app.wearable.WearableHandler;
import ch.threema.base.ThreemaException;
import ch.threema.client.MessageQueue;
import ch.threema.client.voip.VoipCallAnswerData;
import ch.threema.client.voip.VoipCallAnswerMessage;
import ch.threema.client.voip.VoipCallHangupData;
import ch.threema.client.voip.VoipCallHangupMessage;
import ch.threema.client.voip.VoipCallOfferData;
import ch.threema.client.voip.VoipCallOfferMessage;
import ch.threema.client.voip.VoipCallRingingData;
import ch.threema.client.voip.VoipCallRingingMessage;
import ch.threema.client.voip.VoipICECandidatesData;
import ch.threema.client.voip.VoipICECandidatesMessage;
import ch.threema.client.voip.features.VideoFeature;
import ch.threema.storage.models.ContactModel;
import java8.util.concurrent.CompletableFuture;
import static ch.threema.app.ThreemaApplication.INCOMING_CALL_NOTIFICATION_ID;
import static ch.threema.app.ThreemaApplication.getAppContext;
import static ch.threema.app.notifications.NotificationBuilderWrapper.VIBRATE_PATTERN_INCOMING_CALL;
import static ch.threema.app.notifications.NotificationBuilderWrapper.VIBRATE_PATTERN_SILENT;
import static ch.threema.app.services.NotificationService.NOTIFICATION_CHANNEL_CALL;
import static ch.threema.app.voip.services.VoipCallService.ACTION_ICE_CANDIDATES;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_ACTIVITY_MODE;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_CALL_ID;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_CANCEL_WEAR;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_CANDIDATES;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_CONTACT_IDENTITY;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_IS_INITIATOR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The service keeping track of VoIP call state.
 *
 * This class is (intended to be) thread safe.
 */
@AnyThread
public class VoipStateService implements AudioManager.OnAudioFocusChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(VoipStateService.class);

    private static final String TAG = "VoipStateService";

    public static final int VIDEO_RENDER_FLAG_NONE = 0x00;

    public static final int VIDEO_RENDER_FLAG_INCOMING = 0x01;

    public static final int VIDEO_RENDER_FLAG_OUTGOING = 0x02;

    // component type for wearable
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ TYPE_NOTIFICATION, TYPE_ACTIVITY })
    public @interface Component {
    }

    public static final int TYPE_NOTIFICATION = 0;

    public static final int TYPE_ACTIVITY = 1;

    // system managers
    private final AudioManager audioManager;

    private final NotificationManagerCompat notificationManagerCompat;

    private final NotificationManager notificationManager;

    // Threema services
    private ContactService contactService;

    private RingtoneService ringtoneService;

    private PreferenceService preferenceService;

    private MessageService messageService;

    private LifetimeService lifetimeService;

    // Message sending
    private MessageQueue messageQueue;

    // App context
    private Context appContext;

    // State
    private volatile Boolean initiator = null;

    private CallState callState = new CallState();

    private Long callStartTimestamp = null;

    // Map that stores incoming offers
    private final HashMap<Long, VoipCallOfferData> offerMap = new HashMap<>();

    // Flag for designating current user configuration
    private int videoRenderMode = VIDEO_RENDER_FLAG_NONE;

    // Candidate cache
    private final Map<String, List<VoipICECandidatesData>> candidatesCache;

    // Notifications
    private final List<String> callNotificationTags = new ArrayList<>();

    private MediaPlayerStateWrapper ringtonePlayer;

    @NonNull
    private CompletableFuture<Void> ringtoneAudioFocusAbandoned = CompletableFuture.completedFuture(null);

    // Video
    @Nullable
    private VideoContext videoContext;

    @NonNull
    private CompletableFuture<VideoContext> videoContextFuture = new CompletableFuture<>();

    // Pending intents
    @Nullable
    private PendingIntent acceptIntent;

    // Connection status
    private boolean connectionAcquired = false;

    // Timeouts
    private static final int RINGING_TIMEOUT_SECONDS = 60;

    private static final int VOIP_CONNECTION_LINGER = 1000 * 5;

    private final WearableHandler wearableHandler;

    public VoipStateService(ContactService contactService, RingtoneService ringtoneService, PreferenceService preferenceService, MessageService messageService, MessageQueue messageQueue, LifetimeService lifetimeService, final Context appContext) {
        if (!ListenerUtil.mutListener.listen(59434)) {
            this.contactService = contactService;
        }
        if (!ListenerUtil.mutListener.listen(59435)) {
            this.ringtoneService = ringtoneService;
        }
        if (!ListenerUtil.mutListener.listen(59436)) {
            this.preferenceService = preferenceService;
        }
        if (!ListenerUtil.mutListener.listen(59437)) {
            this.messageService = messageService;
        }
        if (!ListenerUtil.mutListener.listen(59438)) {
            this.lifetimeService = lifetimeService;
        }
        if (!ListenerUtil.mutListener.listen(59439)) {
            this.messageQueue = messageQueue;
        }
        if (!ListenerUtil.mutListener.listen(59440)) {
            this.appContext = appContext;
        }
        this.candidatesCache = new HashMap<>();
        this.notificationManagerCompat = NotificationManagerCompat.from(appContext);
        this.notificationManager = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        this.audioManager = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
        this.wearableHandler = new WearableHandler(appContext);
    }

    private static void logCallTrace(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59441)) {
            logger.trace("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallTrace(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59442)) {
            logger.trace("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallDebug(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59443)) {
            logger.debug("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallDebug(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59444)) {
            logger.debug("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallInfo(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59445)) {
            logger.info("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallInfo(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59446)) {
            logger.info("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallWarning(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59447)) {
            logger.warn("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallWarning(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59448)) {
            logger.warn("[cid=" + callId + "]: " + message, arguments);
        }
    }

    private static void logCallError(long callId, String message) {
        if (!ListenerUtil.mutListener.listen(59449)) {
            logger.error("[cid={}]: {}", callId, message);
        }
    }

    private static void logCallError(long callId, String message, Throwable t) {
        if (!ListenerUtil.mutListener.listen(59450)) {
            logger.error("[cid=" + callId + "]: " + message, t);
        }
    }

    private static void logCallError(long callId, @NonNull String message, Object... arguments) {
        if (!ListenerUtil.mutListener.listen(59451)) {
            logger.error("[cid=" + callId + "]: " + message, arguments);
        }
    }

    /**
     *  Get the current call state as an immutable snapshot.
     *
     *  Note: Does not require locking, since the {@link CallState}
     *  class is thread safe.
     */
    public CallStateSnapshot getCallState() {
        return this.callState.getStateSnapshot();
    }

    /**
     *  Called for every state transition.
     *
     *  Note: Most reactions to state changes should be done in the `setStateXXX` methods.
     *        This method should only be used for actions that apply to multiple state transitions.
     *
     *  @param oldState The previous call state.
     *  @param newState The new call state.
     */
    private void onStateChange(@NonNull CallStateSnapshot oldState, @NonNull CallStateSnapshot newState) {
        if (!ListenerUtil.mutListener.listen(59452)) {
            logger.info("Call state change from {} to {}", oldState.getName(), newState.getName());
        }
        if (!ListenerUtil.mutListener.listen(59453)) {
            logger.debug("  State{{},id={},counter={}} → State{{},id={},counter={}}", oldState.getName(), oldState.getCallId(), oldState.getIncomingCallCounter(), newState.getName(), newState.getCallId(), newState.getIncomingCallCounter());
        }
        if (!ListenerUtil.mutListener.listen(59456)) {
            // Clear pending accept intent
            if (!newState.isRinging()) {
                if (!ListenerUtil.mutListener.listen(59454)) {
                    this.acceptIntent = null;
                }
                if (!ListenerUtil.mutListener.listen(59455)) {
                    this.stopRingtone();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59459)) {
            // Ensure bluetooth media button receiver is registered when a call starts
            if ((ListenerUtil.mutListener.listen(59457) ? (newState.isRinging() && newState.isInitializing()) : (newState.isRinging() || newState.isInitializing()))) {
                if (!ListenerUtil.mutListener.listen(59458)) {
                    audioManager.registerMediaButtonEventReceiver(new ComponentName(appContext, VoipMediaButtonReceiver.class));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59462)) {
            // Ensure bluetooth media button receiver is deregistered when a call ends
            if ((ListenerUtil.mutListener.listen(59460) ? (newState.isDisconnecting() && newState.isIdle()) : (newState.isDisconnecting() || newState.isIdle()))) {
                if (!ListenerUtil.mutListener.listen(59461)) {
                    audioManager.unregisterMediaButtonEventReceiver(new ComponentName(appContext, VoipMediaButtonReceiver.class));
                }
            }
        }
    }

    /**
     *  Set the current call state to RINGING.
     */
    public synchronized void setStateRinging(long callId) {
        if (!ListenerUtil.mutListener.listen(59463)) {
            if (this.callState.isRinging()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(59464)) {
            this.ringtoneAudioFocusAbandoned = new CompletableFuture<>();
        }
        // Transition call state
        final CallStateSnapshot prevState = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59465)) {
            this.callState.setRinging(callId);
        }
        if (!ListenerUtil.mutListener.listen(59466)) {
            this.onStateChange(prevState, this.callState.getStateSnapshot());
        }
    }

    /**
     *  Set the current call state to INITIALIZING.
     */
    public synchronized void setStateInitializing(long callId) {
        if (!ListenerUtil.mutListener.listen(59467)) {
            if (this.callState.isInitializing()) {
                return;
            }
        }
        // Transition call state
        final CallStateSnapshot prevState = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59468)) {
            this.callState.setInitializing(callId);
        }
        if (!ListenerUtil.mutListener.listen(59469)) {
            this.onStateChange(prevState, this.callState.getStateSnapshot());
        }
        if (!ListenerUtil.mutListener.listen(59472)) {
            // Make sure connection is open
            if (!this.connectionAcquired) {
                if (!ListenerUtil.mutListener.listen(59470)) {
                    this.lifetimeService.acquireConnection(TAG);
                }
                if (!ListenerUtil.mutListener.listen(59471)) {
                    this.connectionAcquired = true;
                }
            }
        }
        // Send cached candidates and clear cache
        synchronized (this.candidatesCache) {
            if (!ListenerUtil.mutListener.listen(59473)) {
                logCallInfo(callId, "Processing cached candidates for {} ID(s)", this.candidatesCache.size());
            }
            if (!ListenerUtil.mutListener.listen(59481)) {
                {
                    long _loopCounter702 = 0;
                    // is responsible for dropping the ones that aren't of interest.
                    for (Map.Entry<String, List<VoipICECandidatesData>> entry : this.candidatesCache.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter702", ++_loopCounter702);
                        if (!ListenerUtil.mutListener.listen(59474)) {
                            logCallInfo(callId, "Broadcasting {} candidates data messages from {}", entry.getValue().size(), entry.getKey());
                        }
                        if (!ListenerUtil.mutListener.listen(59480)) {
                            {
                                long _loopCounter701 = 0;
                                for (VoipICECandidatesData data : entry.getValue()) {
                                    ListenerUtil.loopListener.listen("_loopCounter701", ++_loopCounter701);
                                    // Broadcast candidates
                                    Intent intent = new Intent();
                                    if (!ListenerUtil.mutListener.listen(59475)) {
                                        intent.setAction(ACTION_ICE_CANDIDATES);
                                    }
                                    if (!ListenerUtil.mutListener.listen(59476)) {
                                        intent.putExtra(EXTRA_CALL_ID, data.getCallIdOrDefault(0L));
                                    }
                                    if (!ListenerUtil.mutListener.listen(59477)) {
                                        intent.putExtra(EXTRA_CONTACT_IDENTITY, entry.getKey());
                                    }
                                    if (!ListenerUtil.mutListener.listen(59478)) {
                                        intent.putExtra(EXTRA_CANDIDATES, data);
                                    }
                                    if (!ListenerUtil.mutListener.listen(59479)) {
                                        LocalBroadcastManager.getInstance(appContext).sendBroadcast(intent);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(59482)) {
                this.clearCandidatesCache();
            }
        }
    }

    /**
     *  Set the current call state to CALLING.
     */
    public synchronized void setStateCalling(long callId) {
        if (!ListenerUtil.mutListener.listen(59483)) {
            if (this.callState.isCalling()) {
                return;
            }
        }
        // Transition call state
        final CallStateSnapshot prevState = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59484)) {
            this.callState.setCalling(callId);
        }
        if (!ListenerUtil.mutListener.listen(59485)) {
            this.onStateChange(prevState, this.callState.getStateSnapshot());
        }
        if (!ListenerUtil.mutListener.listen(59486)) {
            // is guaranteed to be monotonic.
            this.callStartTimestamp = SystemClock.elapsedRealtime();
        }
    }

    /**
     *  Set the current call state to DISCONNECTING.
     */
    public synchronized void setStateDisconnecting(long callId) {
        if (!ListenerUtil.mutListener.listen(59487)) {
            if (this.callState.isDisconnecting()) {
                return;
            }
        }
        // Transition call state
        final CallStateSnapshot prevState = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59488)) {
            this.callState.setDisconnecting(callId);
        }
        if (!ListenerUtil.mutListener.listen(59489)) {
            this.onStateChange(prevState, this.callState.getStateSnapshot());
        }
        if (!ListenerUtil.mutListener.listen(59490)) {
            // Reset start timestamp
            this.callStartTimestamp = null;
        }
        if (!ListenerUtil.mutListener.listen(59491)) {
            // Clear the candidates cache
            this.clearCandidatesCache();
        }
    }

    /**
     *  Set the current call state to IDLE.
     */
    public synchronized void setStateIdle() {
        if (!ListenerUtil.mutListener.listen(59492)) {
            if (this.callState.isIdle()) {
                return;
            }
        }
        // Transition call state
        final CallStateSnapshot prevState = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59493)) {
            this.callState.setIdle();
        }
        if (!ListenerUtil.mutListener.listen(59494)) {
            this.onStateChange(prevState, this.callState.getStateSnapshot());
        }
        if (!ListenerUtil.mutListener.listen(59495)) {
            // Reset start timestamp
            this.callStartTimestamp = null;
        }
        if (!ListenerUtil.mutListener.listen(59496)) {
            // Reset initiator flag
            this.initiator = null;
        }
        // Remove offer data
        long callId = prevState.getCallId();
        if (!ListenerUtil.mutListener.listen(59497)) {
            logger.debug("Removing information for call {} from offerMap", callId);
        }
        if (!ListenerUtil.mutListener.listen(59498)) {
            this.offerMap.remove(callId);
        }
        if (!ListenerUtil.mutListener.listen(59501)) {
            // Release Threema connection
            if (this.connectionAcquired) {
                if (!ListenerUtil.mutListener.listen(59499)) {
                    this.lifetimeService.releaseConnectionLinger(TAG, VOIP_CONNECTION_LINGER);
                }
                if (!ListenerUtil.mutListener.listen(59500)) {
                    this.connectionAcquired = false;
                }
            }
        }
    }

    /**
     *  Return whether the VoIP service is currently initialized as initiator or responder.
     *
     *  Note: This is only initialized once a call is being set up. That means that the flag
     *  will be `null` when a call is ringing, but hasn't been accepted yet.
     */
    @Nullable
    public Boolean isInitiator() {
        return this.initiator;
    }

    /**
     *  Return whether the VoIP service is currently initialized as initiator or responder.
     */
    public void setInitiator(boolean isInitiator) {
        if (!ListenerUtil.mutListener.listen(59502)) {
            this.initiator = isInitiator;
        }
    }

    /**
     *  Create a new accept intent for the specified call ID / identity.
     */
    public static Intent createAcceptIntent(long callId, @NonNull String identity) {
        final Intent intent = new Intent(getAppContext(), VoipCallService.class);
        if (!ListenerUtil.mutListener.listen(59503)) {
            intent.putExtra(EXTRA_CALL_ID, callId);
        }
        if (!ListenerUtil.mutListener.listen(59504)) {
            intent.putExtra(EXTRA_CONTACT_IDENTITY, identity);
        }
        if (!ListenerUtil.mutListener.listen(59505)) {
            intent.putExtra(EXTRA_IS_INITIATOR, false);
        }
        return intent;
    }

    /**
     *  Create a new reject intent for the specified call ID / identity.
     */
    public static Intent createRejectIntent(long callId, @NonNull String identity, byte rejectReason) {
        final Intent intent = new Intent(getAppContext(), CallRejectReceiver.class);
        if (!ListenerUtil.mutListener.listen(59506)) {
            intent.putExtra(EXTRA_CALL_ID, callId);
        }
        if (!ListenerUtil.mutListener.listen(59507)) {
            intent.putExtra(EXTRA_CONTACT_IDENTITY, identity);
        }
        if (!ListenerUtil.mutListener.listen(59508)) {
            intent.putExtra(EXTRA_IS_INITIATOR, false);
        }
        if (!ListenerUtil.mutListener.listen(59509)) {
            intent.putExtra(CallRejectService.EXTRA_REJECT_REASON, rejectReason);
        }
        return intent;
    }

    /**
     *  Validate offer data, return true if it's valid.
     */
    private boolean validateOfferData(@Nullable VoipCallOfferData.OfferData offer) {
        if (!ListenerUtil.mutListener.listen(59511)) {
            if (offer == null) {
                if (!ListenerUtil.mutListener.listen(59510)) {
                    logger.error("Offer data is null");
                }
                return false;
            }
        }
        final String sdpType = offer.getSdpType();
        if (!ListenerUtil.mutListener.listen(59513)) {
            if (!sdpType.equals("offer")) {
                if (!ListenerUtil.mutListener.listen(59512)) {
                    logger.error("Offer data is invalid: Sdp type is {}, not offer", sdpType);
                }
                return false;
            }
        }
        final String sdp = offer.getSdp();
        if (!ListenerUtil.mutListener.listen(59515)) {
            if (sdp == null) {
                if (!ListenerUtil.mutListener.listen(59514)) {
                    logger.error("Offer data is invalid: Sdp is null");
                }
                return false;
            }
        }
        return true;
    }

    /**
     *  Return the {@link VoipCallOfferData} associated with this Call ID (if any).
     */
    @Nullable
    public VoipCallOfferData getCallOffer(long callId) {
        return this.offerMap.get(callId);
    }

    /**
     *  Handle an incoming VoipCallOfferMessage.
     *  @return true if messages was successfully processed
     */
    @WorkerThread
    public synchronized boolean handleCallOffer(final VoipCallOfferMessage msg) {
        // Unwrap data
        final VoipCallOfferData callOfferData = msg.getData();
        if (!ListenerUtil.mutListener.listen(59517)) {
            if (callOfferData == null) {
                if (!ListenerUtil.mutListener.listen(59516)) {
                    logger.warn("Call offer received from {}. Data is null, ignoring.", msg.getFromIdentity());
                }
                return true;
            }
        }
        final long callId = callOfferData.getCallIdOrDefault(0L);
        if (!ListenerUtil.mutListener.listen(59518)) {
            logCallInfo(callId, "Call offer received from {} (Features: {})", msg.getFromIdentity(), callOfferData.getFeatures());
        }
        if (!ListenerUtil.mutListener.listen(59519)) {
            logCallInfo(callId, "{}", callOfferData.getOfferData());
        }
        // Get contact and receiver
        final ContactModel contact = this.contactService.getByIdentity(msg.getFromIdentity());
        if (!ListenerUtil.mutListener.listen(59521)) {
            if (contact == null) {
                if (!ListenerUtil.mutListener.listen(59520)) {
                    logCallError(callId, "Could not fetch contact for identity {}", msg.getFromIdentity());
                }
                return true;
            }
        }
        // Set to non-null in order to reject the call
        Byte rejectReason = null;
        // Set to true if you don't want a "missed call" chat message
        boolean silentReject = false;
        if (!ListenerUtil.mutListener.listen(59534)) {
            if (!this.preferenceService.isVoipEnabled()) {
                if (!ListenerUtil.mutListener.listen(59531)) {
                    // Calls disabled
                    logCallInfo(callId, "Rejecting call from {} (disabled)", contact.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(59532)) {
                    rejectReason = VoipCallAnswerData.RejectReason.DISABLED;
                }
                if (!ListenerUtil.mutListener.listen(59533)) {
                    silentReject = true;
                }
            } else if (!this.validateOfferData(callOfferData.getOfferData())) {
                if (!ListenerUtil.mutListener.listen(59528)) {
                    // Offer invalid
                    logCallWarning(callId, "Rejecting call from {} (invalid offer)", contact.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(59529)) {
                    rejectReason = VoipCallAnswerData.RejectReason.UNKNOWN;
                }
                if (!ListenerUtil.mutListener.listen(59530)) {
                    silentReject = true;
                }
            } else if (!this.callState.isIdle()) {
                if (!ListenerUtil.mutListener.listen(59526)) {
                    // Another call is already active
                    logCallInfo(callId, "Rejecting call from {} (busy)", contact.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(59527)) {
                    rejectReason = VoipCallAnswerData.RejectReason.BUSY;
                }
            } else if (VoipUtil.isPSTNCallOngoing(this.appContext)) {
                if (!ListenerUtil.mutListener.listen(59524)) {
                    // A PSTN call is ongoing
                    logCallInfo(callId, "Rejecting call from {} (PSTN call ongoing)", contact.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(59525)) {
                    rejectReason = VoipCallAnswerData.RejectReason.BUSY;
                }
            } else if (DNDUtil.getInstance().isMutedWork()) {
                if (!ListenerUtil.mutListener.listen(59522)) {
                    // Called outside working hours
                    logCallInfo(callId, "Rejecting call from {} (called outside of working hours)", contact.getIdentity());
                }
                if (!ListenerUtil.mutListener.listen(59523)) {
                    rejectReason = VoipCallAnswerData.RejectReason.OFF_HOURS;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59537)) {
            if (rejectReason != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(59536)) {
                        this.sendRejectCallAnswerMessage(contact, callId, rejectReason, !silentReject);
                    }
                } catch (ThreemaException e) {
                    if (!ListenerUtil.mutListener.listen(59535)) {
                        logger.error(callId + ": Could not send reject call message", e);
                    }
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(59538)) {
            // Prefetch TURN servers
            Config.getTurnServerCache().prefetchTurnServers();
        }
        if (!ListenerUtil.mutListener.listen(59539)) {
            // Reset fetch cache
            ch.threema.app.routines.UpdateFeatureLevelRoutine.removeTimeCache(contact);
        }
        if (!ListenerUtil.mutListener.listen(59540)) {
            // Store offer in offer map
            logger.debug("Adding information for call {} to offerMap", callId);
        }
        if (!ListenerUtil.mutListener.listen(59541)) {
            this.offerMap.put(callId, callOfferData);
        }
        // and set flag to cancel on watch to true as this call flow is initiated and handled from the Phone
        final Intent answerIntent = createAcceptIntent(callId, msg.getFromIdentity());
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(59542)) {
            bundle.putBoolean(EXTRA_CANCEL_WEAR, true);
        }
        if (!ListenerUtil.mutListener.listen(59543)) {
            answerIntent.putExtras(bundle);
        }
        final PendingIntent accept;
        if ((ListenerUtil.mutListener.listen(59548) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(59547) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(59546) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(59545) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(59544) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
            accept = PendingIntent.getForegroundService(this.appContext, IdUtil.getTempId(contact), answerIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        } else {
            accept = PendingIntent.getService(this.appContext, IdUtil.getTempId(contact), answerIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        }
        if (!ListenerUtil.mutListener.listen(59549)) {
            this.acceptIntent = accept;
        }
        // If the call is rejected, start the CallRejectService
        final Intent rejectIntent = this.createRejectIntent(callId, msg.getFromIdentity(), VoipCallAnswerData.RejectReason.REJECTED);
        final PendingIntent reject = PendingIntent.getBroadcast(this.appContext, -IdUtil.getTempId(contact), rejectIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_ONE_SHOT);
        final ContactMessageReceiver messageReceiver = this.contactService.createReceiver(contact);
        boolean isMuted = DNDUtil.getInstance().isMutedPrivate(messageReceiver, null);
        if (!ListenerUtil.mutListener.listen(59550)) {
            // Set state to RINGING
            this.setStateRinging(callId);
        }
        if (!ListenerUtil.mutListener.listen(59551)) {
            // Play ringtone
            this.playRingtone(messageReceiver, isMuted);
        }
        if (!ListenerUtil.mutListener.listen(59552)) {
            // Show call notification
            this.showNotification(contact, callId, accept, reject, msg, callOfferData, isMuted);
        }
        // Send "ringing" message to caller
        try {
            if (!ListenerUtil.mutListener.listen(59554)) {
                this.sendCallRingingMessage(contact, callId);
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(59553)) {
                logger.error(callId + ": Could not send ringing message", e);
            }
        }
        // Start timer to reject the call after a while
        final long originalCallCounter = this.callState.getIncomingCallCounter();
        final Runnable ringerTimeoutRunnable = () -> {
            final CallStateSnapshot currentCallState = this.getCallState();
            // Only reject the call if the state is still initializing with the same call id.
            if (!currentCallState.isRinging()) {
                logger.info("Ignoring ringer timeout for call #{} (state is {}, not RINGING)", originalCallCounter, currentCallState.getName());
            } else if (currentCallState.getIncomingCallCounter() != originalCallCounter) {
                logger.info("Ignoring ringer timeout for call #{} (current: #{})", originalCallCounter, currentCallState.getIncomingCallCounter());
            } else {
                logger.info("Ringer timeout for call #{} reached after {}s", originalCallCounter, RINGING_TIMEOUT_SECONDS);
                // Reject call
                final Intent rejectIntent1 = createRejectIntent(currentCallState.getCallId(), msg.getFromIdentity(), VoipCallAnswerData.RejectReason.TIMEOUT);
                CallRejectService.enqueueWork(appContext, rejectIntent1);
            }
        };
        if (!ListenerUtil.mutListener.listen(59559)) {
            (new Handler(Looper.getMainLooper())).postDelayed(ringerTimeoutRunnable, (ListenerUtil.mutListener.listen(59558) ? (RINGING_TIMEOUT_SECONDS % 1000) : (ListenerUtil.mutListener.listen(59557) ? (RINGING_TIMEOUT_SECONDS / 1000) : (ListenerUtil.mutListener.listen(59556) ? (RINGING_TIMEOUT_SECONDS - 1000) : (ListenerUtil.mutListener.listen(59555) ? (RINGING_TIMEOUT_SECONDS + 1000) : (RINGING_TIMEOUT_SECONDS * 1000))))));
        }
        if (!ListenerUtil.mutListener.listen(59560)) {
            // Notify listeners
            VoipListenerManager.messageListener.handle(listener -> {
                final String identity = msg.getFromIdentity();
                if (listener.handle(identity)) {
                    listener.onOffer(identity, msg.getData());
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(59561)) {
            VoipListenerManager.callEventListener.handle(listener -> listener.onRinging(msg.getFromIdentity()));
        }
        return true;
    }

    /**
     *  Handle an incoming VoipCallAnswerMessage.
     *  @return true if messages was successfully processed
     */
    @WorkerThread
    public synchronized boolean handleCallAnswer(final VoipCallAnswerMessage msg) {
        final VoipCallAnswerData callAnswerData = msg.getData();
        if (!ListenerUtil.mutListener.listen(59578)) {
            if (callAnswerData != null) {
                // Validate Call ID
                final long callId = callAnswerData.getCallIdOrDefault(0L);
                if (!ListenerUtil.mutListener.listen(59563)) {
                    if (!this.isCallIdValid(callId)) {
                        if (!ListenerUtil.mutListener.listen(59562)) {
                            logger.info("Call answer received for an invalid call ID ({}, local={}), ignoring", callId, this.callState.getCallId());
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(59565)) {
                    // Ensure that an answer wasn't already received
                    if (this.callState.answerReceived()) {
                        if (!ListenerUtil.mutListener.listen(59564)) {
                            logCallWarning(callId, "Received extra answer, ignoring");
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(59567)) {
                    // Ensure that action was set
                    if (callAnswerData.getAction() == null) {
                        if (!ListenerUtil.mutListener.listen(59566)) {
                            logCallWarning(callId, "Call answer received without action, ignoring");
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(59575)) {
                    switch(callAnswerData.getAction()) {
                        // Call was accepted
                        case VoipCallAnswerData.Action.ACCEPT:
                            if (!ListenerUtil.mutListener.listen(59568)) {
                                logCallInfo(callId, "Call answer received from {}: accept", msg.getFromIdentity());
                            }
                            if (!ListenerUtil.mutListener.listen(59569)) {
                                logCallInfo(callId, "Answer features: {}", callAnswerData.getFeatures());
                            }
                            if (!ListenerUtil.mutListener.listen(59570)) {
                                logCallInfo(callId, "Answer data: {}", callAnswerData.getAnswerData());
                            }
                            if (!ListenerUtil.mutListener.listen(59571)) {
                                VoipUtil.sendVoipBroadcast(this.appContext, CallActivity.ACTION_CALL_ACCEPTED);
                            }
                            break;
                        // Call was rejected
                        case VoipCallAnswerData.Action.REJECT:
                            if (!ListenerUtil.mutListener.listen(59572)) {
                                // TODO: only for tests!
                                VoipListenerManager.callEventListener.handle(listener -> {
                                    listener.onRejected(msg.getFromIdentity(), false, callAnswerData.getRejectReason());
                                });
                            }
                            if (!ListenerUtil.mutListener.listen(59573)) {
                                logCallInfo(callId, "Call answer received from {}: reject/{}", msg.getFromIdentity(), callAnswerData.getRejectReasonName());
                            }
                            break;
                        default:
                            if (!ListenerUtil.mutListener.listen(59574)) {
                                logCallInfo(callId, "Call answer received from {}: Unknown action: {}", callAnswerData.getAction());
                            }
                            break;
                    }
                }
                if (!ListenerUtil.mutListener.listen(59576)) {
                    // Mark answer as received
                    this.callState.setAnswerReceived();
                }
                if (!ListenerUtil.mutListener.listen(59577)) {
                    // Notify listeners
                    VoipListenerManager.messageListener.handle(listener -> {
                        final String identity = msg.getFromIdentity();
                        if (listener.handle(identity)) {
                            listener.onAnswer(identity, callAnswerData);
                        }
                    });
                }
            }
        }
        return true;
    }

    /**
     *  Handle an incoming VoipICECandidatesMessage.
     *  @return true if messages was successfully processed
     */
    @WorkerThread
    public synchronized boolean handleICECandidates(final VoipICECandidatesMessage msg) {
        // Unwrap data
        final VoipICECandidatesData candidatesData = msg.getData();
        if (!ListenerUtil.mutListener.listen(59580)) {
            if (candidatesData == null) {
                if (!ListenerUtil.mutListener.listen(59579)) {
                    logger.warn("Call ICE candidate message received from {}. Data is null, ignoring", msg.getFromIdentity());
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(59582)) {
            if (candidatesData.getCandidates() == null) {
                if (!ListenerUtil.mutListener.listen(59581)) {
                    logger.warn("Call ICE candidate message received from {}. Candidates are null, ignoring", msg.getFromIdentity());
                }
                return true;
            }
        }
        // Validate Call ID
        final long callId = candidatesData.getCallIdOrDefault(0L);
        if (!ListenerUtil.mutListener.listen(59584)) {
            if (!this.isCallIdValid(callId)) {
                if (!ListenerUtil.mutListener.listen(59583)) {
                    logger.info("Call ICE candidate message received from {} for an invalid Call ID ({}, local={}), ignoring", msg.getFromIdentity(), callId, this.callState.getCallId());
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(59586)) {
            // The "removed" flag is deprecated, see ANDR-1145 / SE-66
            if (candidatesData.isRemoved()) {
                if (!ListenerUtil.mutListener.listen(59585)) {
                    logCallInfo(callId, "Call ICE candidate message received from {} with removed=true, ignoring");
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(59587)) {
            logCallInfo(callId, "Call ICE candidate message received from {} ({} candidates)", msg.getFromIdentity(), candidatesData.getCandidates().length);
        }
        if (!ListenerUtil.mutListener.listen(59589)) {
            {
                long _loopCounter703 = 0;
                for (VoipICECandidatesData.Candidate candidate : candidatesData.getCandidates()) {
                    ListenerUtil.loopListener.listen("_loopCounter703", ++_loopCounter703);
                    if (!ListenerUtil.mutListener.listen(59588)) {
                        logCallInfo(callId, "  Incoming ICE candidate: {}", candidate.getCandidate());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59599)) {
            // Handle candidates depending on state
            if ((ListenerUtil.mutListener.listen(59590) ? (this.callState.isIdle() && this.callState.isRinging()) : (this.callState.isIdle() || this.callState.isRinging()))) {
                if (!ListenerUtil.mutListener.listen(59598)) {
                    // If the call hasn't been started yet, cache the candidate(s)
                    this.cacheCandidate(msg.getFromIdentity(), candidatesData);
                }
            } else if ((ListenerUtil.mutListener.listen(59591) ? (this.callState.isInitializing() && this.callState.isCalling()) : (this.callState.isInitializing() || this.callState.isCalling()))) {
                // Otherwise, send candidate(s) directly to call service via broadcast
                Intent intent = new Intent();
                if (!ListenerUtil.mutListener.listen(59593)) {
                    intent.setAction(ACTION_ICE_CANDIDATES);
                }
                if (!ListenerUtil.mutListener.listen(59594)) {
                    intent.putExtra(EXTRA_CALL_ID, msg.getData().getCallIdOrDefault(0L));
                }
                if (!ListenerUtil.mutListener.listen(59595)) {
                    intent.putExtra(EXTRA_CONTACT_IDENTITY, msg.getFromIdentity());
                }
                if (!ListenerUtil.mutListener.listen(59596)) {
                    intent.putExtra(EXTRA_CANDIDATES, candidatesData);
                }
                if (!ListenerUtil.mutListener.listen(59597)) {
                    LocalBroadcastManager.getInstance(appContext).sendBroadcast(intent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(59592)) {
                    logCallWarning(callId, "Received ICE candidates in invalid call state ({})", this.callState);
                }
            }
        }
        return true;
    }

    /**
     *  Handle incoming Call Ringing message
     *  @return true if message was successfully processed
     */
    @WorkerThread
    public synchronized boolean handleCallRinging(final VoipCallRingingMessage msg) {
        final CallStateSnapshot state = this.callState.getStateSnapshot();
        // NOTE: Ringing messages from older Threema versions may not have any associated data!
        final long callId = msg.getData() == null ? 0L : msg.getData().getCallIdOrDefault(0L);
        if (!ListenerUtil.mutListener.listen(59601)) {
            if (!this.isCallIdValid(callId)) {
                if (!ListenerUtil.mutListener.listen(59600)) {
                    logger.info("Call ringing message received from {} for an invalid Call ID ({}, local={}), ignoring", msg.getFromIdentity(), callId, state.getCallId());
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(59602)) {
            logCallInfo(callId, "Call ringing message received from {}", msg.getFromIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59604)) {
            // Check whether we're in the correct state for a ringing message
            if (!state.isInitializing()) {
                if (!ListenerUtil.mutListener.listen(59603)) {
                    logCallWarning(callId, "Call ringing message from {} ignored, call state is {}", msg.getFromIdentity(), state.getName());
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(59605)) {
            // Notify listeners
            VoipListenerManager.messageListener.handle(listener -> {
                final String identity = msg.getFromIdentity();
                if (listener.handle(identity)) {
                    listener.onRinging(identity, msg.getData());
                }
            });
        }
        return true;
    }

    /**
     *  Handle remote call hangup messages.
     *  A hangup can happen either before or during a call.
     *  @return true if message was successfully processed
     */
    @WorkerThread
    public synchronized boolean handleRemoteCallHangup(final VoipCallHangupMessage msg) {
        // NOTE: Hangup messages from older Threema versions may not have any associated data!
        final long callId = msg.getData() == null ? 0L : msg.getData().getCallIdOrDefault(0L);
        if (!ListenerUtil.mutListener.listen(59607)) {
            if (!this.isCallIdValid(callId)) {
                if (!ListenerUtil.mutListener.listen(59606)) {
                    logger.info("Call hangup message received from {} for an invalid Call ID ({}, local={}), ignoring", msg.getFromIdentity(), callId, this.callState.getCallId());
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(59608)) {
            logger.info("Call hangup message received from {}", msg.getFromIdentity());
        }
        final String identity = msg.getFromIdentity();
        final CallStateSnapshot prevState = this.callState.getStateSnapshot();
        final Integer duration = getCallDuration();
        // however, in that case we can be sure that it's an incoming call.
        final boolean incoming = this.isInitiator() != Boolean.TRUE;
        if (!ListenerUtil.mutListener.listen(59609)) {
            // Reset state
            this.setStateIdle();
        }
        if (!ListenerUtil.mutListener.listen(59610)) {
            // Cancel call notification for that person
            this.cancelCallNotification(msg.getFromIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59611)) {
            // Notify listeners
            VoipListenerManager.messageListener.handle(listener -> {
                if (listener.handle(identity)) {
                    listener.onHangup(identity, msg.getData());
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(59618)) {
            if ((ListenerUtil.mutListener.listen(59614) ? (incoming || ((ListenerUtil.mutListener.listen(59613) ? ((ListenerUtil.mutListener.listen(59612) ? (prevState.isIdle() && prevState.isRinging()) : (prevState.isIdle() || prevState.isRinging())) && prevState.isInitializing()) : ((ListenerUtil.mutListener.listen(59612) ? (prevState.isIdle() && prevState.isRinging()) : (prevState.isIdle() || prevState.isRinging())) || prevState.isInitializing())))) : (incoming && ((ListenerUtil.mutListener.listen(59613) ? ((ListenerUtil.mutListener.listen(59612) ? (prevState.isIdle() && prevState.isRinging()) : (prevState.isIdle() || prevState.isRinging())) && prevState.isInitializing()) : ((ListenerUtil.mutListener.listen(59612) ? (prevState.isIdle() && prevState.isRinging()) : (prevState.isIdle() || prevState.isRinging())) || prevState.isInitializing())))))) {
                if (!ListenerUtil.mutListener.listen(59617)) {
                    VoipListenerManager.callEventListener.handle(listener -> {
                        final boolean accepted = prevState.isInitializing();
                        listener.onMissed(identity, accepted);
                    });
                }
            } else if ((ListenerUtil.mutListener.listen(59615) ? (prevState.isCalling() || duration != null) : (prevState.isCalling() && duration != null))) {
                if (!ListenerUtil.mutListener.listen(59616)) {
                    VoipListenerManager.callEventListener.handle(listener -> {
                        listener.onFinished(msg.getFromIdentity(), !incoming, duration);
                    });
                }
            }
        }
        return true;
    }

    /**
     *  Return whether the specified call ID belongs to the current call.
     *
     *  NOTE: Do not use this method to validate the call ID in an offer,
     *        that doesn't make sense :)
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private synchronized boolean isCallIdValid(long callId) {
        // If the passed in Call ID matches the current Call ID, everything is fine
        final long currentCallId = this.callState.getCallId();
        if (!ListenerUtil.mutListener.listen(59624)) {
            if ((ListenerUtil.mutListener.listen(59623) ? (callId >= currentCallId) : (ListenerUtil.mutListener.listen(59622) ? (callId <= currentCallId) : (ListenerUtil.mutListener.listen(59621) ? (callId > currentCallId) : (ListenerUtil.mutListener.listen(59620) ? (callId < currentCallId) : (ListenerUtil.mutListener.listen(59619) ? (callId != currentCallId) : (callId == currentCallId))))))) {
                return true;
            }
        }
        // messages will not contain a Call ID. Accept the messages anyways.
        final boolean isInitiatior = this.isInitiator() == Boolean.TRUE;
        if (!ListenerUtil.mutListener.listen(59631)) {
            if ((ListenerUtil.mutListener.listen(59630) ? (isInitiatior || (ListenerUtil.mutListener.listen(59629) ? (callId >= 0L) : (ListenerUtil.mutListener.listen(59628) ? (callId <= 0L) : (ListenerUtil.mutListener.listen(59627) ? (callId > 0L) : (ListenerUtil.mutListener.listen(59626) ? (callId < 0L) : (ListenerUtil.mutListener.listen(59625) ? (callId != 0L) : (callId == 0L))))))) : (isInitiatior && (ListenerUtil.mutListener.listen(59629) ? (callId >= 0L) : (ListenerUtil.mutListener.listen(59628) ? (callId <= 0L) : (ListenerUtil.mutListener.listen(59627) ? (callId > 0L) : (ListenerUtil.mutListener.listen(59626) ? (callId < 0L) : (ListenerUtil.mutListener.listen(59625) ? (callId != 0L) : (callId == 0L))))))))) {
                return true;
            }
        }
        // Otherwise, there's a call ID mismatch.
        return false;
    }

    /**
     *  Send a call offer to the specified contact.
     *
     *  @param videoCall Whether to enable video calls in this offer.
     *  @throws ThreemaException if enqueuing the message fails.
     *  @throws IllegalArgumentException if the session description is not valid for an offer message.
     *  @throws IllegalStateException if the call state is not INITIALIZING
     */
    public synchronized void sendCallOfferMessage(@NonNull ContactModel receiver, final long callId, @NonNull SessionDescription sessionDescription, boolean videoCall) throws ThreemaException, IllegalArgumentException, IllegalStateException {
        if (!ListenerUtil.mutListener.listen(59632)) {
            switch(sessionDescription.type) {
                case OFFER:
                    // OK
                    break;
                case ANSWER:
                case PRANSWER:
                    throw new IllegalArgumentException("A " + sessionDescription.type + " session description is not valid for an offer message");
            }
        }
        final CallStateSnapshot state = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59633)) {
            if (!state.isInitializing()) {
                throw new IllegalStateException("Called sendCallOfferMessage in state " + state.getName());
            }
        }
        final VoipCallOfferData callOfferData = new VoipCallOfferData().setCallId(callId).setOfferData(new VoipCallOfferData.OfferData().setSdpType(sessionDescription.type.canonicalForm()).setSdp(sessionDescription.description));
        if (!ListenerUtil.mutListener.listen(59635)) {
            if (videoCall) {
                if (!ListenerUtil.mutListener.listen(59634)) {
                    callOfferData.addFeature(new VideoFeature());
                }
            }
        }
        final VoipCallOfferMessage voipCallOfferMessage = new VoipCallOfferMessage();
        if (!ListenerUtil.mutListener.listen(59636)) {
            voipCallOfferMessage.setData(callOfferData);
        }
        if (!ListenerUtil.mutListener.listen(59637)) {
            voipCallOfferMessage.setToIdentity(receiver.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59638)) {
            this.messageQueue.enqueue(voipCallOfferMessage);
        }
        if (!ListenerUtil.mutListener.listen(59639)) {
            logCallInfo(callId, "Call offer enqueued to {}", voipCallOfferMessage.getToIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59640)) {
            logCallInfo(callId, "  Offer features: {}", callOfferData.getFeatures());
        }
        if (!ListenerUtil.mutListener.listen(59641)) {
            logCallInfo(callId, "  Offer data: {}", callOfferData.getOfferData());
        }
        if (!ListenerUtil.mutListener.listen(59642)) {
            this.messageService.sendProfilePicture(new MessageReceiver[] { contactService.createReceiver(receiver) });
        }
    }

    /**
     *  Accept a call from the specified contact.
     *  @throws ThreemaException if enqueuing the message fails.
     *  @throws IllegalArgumentException if the session description is not valid for an offer message.
     */
    public void sendAcceptCallAnswerMessage(@NonNull ContactModel receiver, final long callId, @NonNull SessionDescription sessionDescription, boolean videoCall) throws ThreemaException, IllegalArgumentException {
        if (!ListenerUtil.mutListener.listen(59643)) {
            this.sendCallAnswerMessage(receiver, callId, sessionDescription, VoipCallAnswerData.Action.ACCEPT, null, videoCall);
        }
    }

    /**
     *  Reject a call from the specified contact.
     *  @throws ThreemaException if enqueuing the message fails.
     */
    public void sendRejectCallAnswerMessage(@NonNull final ContactModel receiver, final long callId, byte reason) throws ThreemaException, IllegalArgumentException {
        if (!ListenerUtil.mutListener.listen(59644)) {
            logger.info("VoipStateService sendRejectCallAnswerMessage");
        }
        if (!ListenerUtil.mutListener.listen(59645)) {
            this.sendRejectCallAnswerMessage(receiver, callId, reason, true);
        }
    }

    /**
     *  Reject a call from the specified contact.
     *  @throws ThreemaException if enqueuing the message fails.
     */
    public void sendRejectCallAnswerMessage(@NonNull final ContactModel receiver, final long callId, byte reason, boolean notifyListeners) throws ThreemaException, IllegalArgumentException {
        if (!ListenerUtil.mutListener.listen(59646)) {
            logger.info("VoipStateService sendRejectCallAnswerMessage listener true");
        }
        if (!ListenerUtil.mutListener.listen(59647)) {
            this.sendCallAnswerMessage(receiver, callId, null, VoipCallAnswerData.Action.REJECT, reason, null);
        }
        if (!ListenerUtil.mutListener.listen(59649)) {
            // Notify listeners
            if (notifyListeners) {
                if (!ListenerUtil.mutListener.listen(59648)) {
                    VoipListenerManager.callEventListener.handle(listener -> {
                        switch(reason) {
                            case VoipCallAnswerData.RejectReason.BUSY:
                            case VoipCallAnswerData.RejectReason.TIMEOUT:
                            case VoipCallAnswerData.RejectReason.OFF_HOURS:
                                listener.onMissed(receiver.getIdentity(), false);
                                break;
                            default:
                                listener.onRejected(receiver.getIdentity(), true, reason);
                                break;
                        }
                    });
                }
            }
        }
    }

    /**
     *  Send a call answer method.
     *
     *  @param videoCall If set to TRUE, then the `video` call feature
     *      will be sent along in the answer.
     *  @throws ThreemaException
     *  @throws IllegalArgumentException
     *  @throws IllegalStateException
     */
    private void sendCallAnswerMessage(@NonNull ContactModel receiver, final long callId, @Nullable SessionDescription sessionDescription, byte action, @Nullable Byte rejectReason, @Nullable Boolean videoCall) throws ThreemaException, IllegalArgumentException, IllegalStateException {
        if (!ListenerUtil.mutListener.listen(59650)) {
            logger.info("VoipStateService sendCallAnswerMessage");
        }
        final VoipCallAnswerData callAnswerData = new VoipCallAnswerData().setCallId(callId).setAction(action);
        if (!ListenerUtil.mutListener.listen(59658)) {
            if ((ListenerUtil.mutListener.listen(59651) ? (action == VoipCallAnswerData.Action.ACCEPT || sessionDescription != null) : (action == VoipCallAnswerData.Action.ACCEPT && sessionDescription != null))) {
                if (!ListenerUtil.mutListener.listen(59654)) {
                    switch(sessionDescription.type) {
                        case ANSWER:
                        case PRANSWER:
                            // OK
                            break;
                        case OFFER:
                            throw new IllegalArgumentException("A " + sessionDescription.type + " session description is not valid for an answer message");
                    }
                }
                if (!ListenerUtil.mutListener.listen(59655)) {
                    callAnswerData.setAnswerData(new VoipCallAnswerData.AnswerData().setSdpType(sessionDescription.type.canonicalForm()).setSdp(sessionDescription.description));
                }
                if (!ListenerUtil.mutListener.listen(59657)) {
                    if (Boolean.TRUE.equals(videoCall)) {
                        if (!ListenerUtil.mutListener.listen(59656)) {
                            callAnswerData.addFeature(new VideoFeature());
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(59652) ? (action == VoipCallAnswerData.Action.REJECT || rejectReason != null) : (action == VoipCallAnswerData.Action.REJECT && rejectReason != null))) {
                if (!ListenerUtil.mutListener.listen(59653)) {
                    callAnswerData.setRejectReason(rejectReason);
                }
            } else {
                throw new IllegalArgumentException("Invalid action, missing session description or missing reject reason");
            }
        }
        final VoipCallAnswerMessage voipCallAnswerMessage = new VoipCallAnswerMessage();
        if (!ListenerUtil.mutListener.listen(59659)) {
            voipCallAnswerMessage.setData(callAnswerData);
        }
        if (!ListenerUtil.mutListener.listen(59660)) {
            voipCallAnswerMessage.setToIdentity(receiver.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59661)) {
            logCallInfo(callId, "Call answer enqueued to {}: {}", voipCallAnswerMessage.getToIdentity(), callAnswerData.getAction());
        }
        if (!ListenerUtil.mutListener.listen(59662)) {
            logCallInfo(callId, "  Answer features: {}", callAnswerData.getFeatures());
        }
        if (!ListenerUtil.mutListener.listen(59663)) {
            messageQueue.enqueue(voipCallAnswerMessage);
        }
        if (!ListenerUtil.mutListener.listen(59664)) {
            this.messageService.sendProfilePicture(new MessageReceiver[] { contactService.createReceiver(receiver) });
        }
    }

    /**
     *  Send ice candidates to the specified contact.
     *  @throws ThreemaException if enqueuing the message fails.
     */
    synchronized void sendICECandidatesMessage(@NonNull ContactModel receiver, final long callId, @NonNull IceCandidate[] iceCandidates) throws ThreemaException {
        final CallStateSnapshot state = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59668)) {
            if (!((ListenerUtil.mutListener.listen(59666) ? ((ListenerUtil.mutListener.listen(59665) ? (state.isRinging() && state.isInitializing()) : (state.isRinging() || state.isInitializing())) && state.isCalling()) : ((ListenerUtil.mutListener.listen(59665) ? (state.isRinging() && state.isInitializing()) : (state.isRinging() || state.isInitializing())) || state.isCalling())))) {
                if (!ListenerUtil.mutListener.listen(59667)) {
                    logger.warn("Called sendICECandidatesMessage in state {}, ignoring", state.getName());
                }
                return;
            }
        }
        // Build message
        final List<VoipICECandidatesData.Candidate> candidates = new LinkedList<>();
        if (!ListenerUtil.mutListener.listen(59671)) {
            {
                long _loopCounter704 = 0;
                for (IceCandidate c : iceCandidates) {
                    ListenerUtil.loopListener.listen("_loopCounter704", ++_loopCounter704);
                    if (!ListenerUtil.mutListener.listen(59670)) {
                        if (c != null) {
                            if (!ListenerUtil.mutListener.listen(59669)) {
                                candidates.add(new VoipICECandidatesData.Candidate(c.sdp, c.sdpMid, c.sdpMLineIndex, null));
                            }
                        }
                    }
                }
            }
        }
        final VoipICECandidatesData voipICECandidatesData = new VoipICECandidatesData().setCallId(callId).setCandidates(candidates.toArray(new VoipICECandidatesData.Candidate[candidates.size()]));
        final VoipICECandidatesMessage voipICECandidatesMessage = new VoipICECandidatesMessage();
        if (!ListenerUtil.mutListener.listen(59672)) {
            voipICECandidatesMessage.setData(voipICECandidatesData);
        }
        if (!ListenerUtil.mutListener.listen(59673)) {
            voipICECandidatesMessage.setToIdentity(receiver.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59674)) {
            // Enqueue
            messageQueue.enqueue(voipICECandidatesMessage);
        }
        if (!ListenerUtil.mutListener.listen(59675)) {
            // Log
            logCallInfo(callId, "Call ICE candidate message enqueued to {}", voipICECandidatesMessage.getToIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59677)) {
            {
                long _loopCounter705 = 0;
                for (VoipICECandidatesData.Candidate candidate : Objects.requireNonNull(voipICECandidatesData.getCandidates())) {
                    ListenerUtil.loopListener.listen("_loopCounter705", ++_loopCounter705);
                    if (!ListenerUtil.mutListener.listen(59676)) {
                        logCallInfo(callId, "  Outgoing ICE candidate: {}", candidate.getCandidate());
                    }
                }
            }
        }
    }

    /**
     *  Send a ringing message to the specified contact.
     */
    private synchronized void sendCallRingingMessage(@NonNull ContactModel contactModel, final long callId) throws ThreemaException, IllegalStateException {
        final CallStateSnapshot state = this.callState.getStateSnapshot();
        if (!ListenerUtil.mutListener.listen(59678)) {
            if (!state.isRinging()) {
                throw new IllegalStateException("Called sendCallRingingMessage in state " + state.getName());
            }
        }
        final VoipCallRingingData callRingingData = new VoipCallRingingData().setCallId(callId);
        final VoipCallRingingMessage msg = new VoipCallRingingMessage();
        if (!ListenerUtil.mutListener.listen(59679)) {
            msg.setToIdentity(contactModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(59680)) {
            msg.setData(callRingingData);
        }
        if (!ListenerUtil.mutListener.listen(59681)) {
            messageQueue.enqueue(msg);
        }
        if (!ListenerUtil.mutListener.listen(59682)) {
            logCallInfo(callId, "Call ringing message enqueued to {}", msg.getToIdentity());
        }
    }

    /**
     *  Send a hangup message to the specified contact.
     */
    synchronized void sendCallHangupMessage(@NonNull final ContactModel contactModel, final long callId) throws ThreemaException {
        final CallStateSnapshot state = this.callState.getStateSnapshot();
        final String peerIdentity = contactModel.getIdentity();
        final VoipCallHangupData callHangupData = new VoipCallHangupData().setCallId(callId);
        final VoipCallHangupMessage msg = new VoipCallHangupMessage();
        if (!ListenerUtil.mutListener.listen(59683)) {
            msg.setData(callHangupData);
        }
        if (!ListenerUtil.mutListener.listen(59684)) {
            msg.setToIdentity(peerIdentity);
        }
        final Integer duration = getCallDuration();
        final boolean outgoing = this.isInitiator() == Boolean.TRUE;
        if (!ListenerUtil.mutListener.listen(59685)) {
            messageQueue.enqueue(msg);
        }
        if (!ListenerUtil.mutListener.listen(59686)) {
            logCallInfo(callId, "Call hangup message enqueued to {} (prevState={}, duration={})", msg.getToIdentity(), state, duration);
        }
        if (!ListenerUtil.mutListener.listen(59691)) {
            // Notify the VoIP call event listener
            if ((ListenerUtil.mutListener.listen(59689) ? (duration == null || ((ListenerUtil.mutListener.listen(59688) ? ((ListenerUtil.mutListener.listen(59687) ? (state.isInitializing() && state.isCalling()) : (state.isInitializing() || state.isCalling())) && state.isDisconnecting()) : ((ListenerUtil.mutListener.listen(59687) ? (state.isInitializing() && state.isCalling()) : (state.isInitializing() || state.isCalling())) || state.isDisconnecting())))) : (duration == null && ((ListenerUtil.mutListener.listen(59688) ? ((ListenerUtil.mutListener.listen(59687) ? (state.isInitializing() && state.isCalling()) : (state.isInitializing() || state.isCalling())) && state.isDisconnecting()) : ((ListenerUtil.mutListener.listen(59687) ? (state.isInitializing() && state.isCalling()) : (state.isInitializing() || state.isCalling())) || state.isDisconnecting())))))) {
                if (!ListenerUtil.mutListener.listen(59690)) {
                    // Connection was never established
                    VoipListenerManager.callEventListener.handle(listener -> {
                        if (outgoing) {
                            listener.onAborted(peerIdentity);
                        } else {
                            listener.onMissed(peerIdentity, true);
                        }
                    });
                }
            }
        }
    }

    /**
     *  Accept an incoming call.
     *  @return true if call was accepted, false otherwise (e.g. if no incoming call was active)
     */
    public boolean acceptIncomingCall() {
        if (this.acceptIntent == null) {
            return false;
        }
        try {
            if (!ListenerUtil.mutListener.listen(59694)) {
                this.acceptIntent.send();
            }
            if (!ListenerUtil.mutListener.listen(59695)) {
                this.acceptIntent = null;
            }
            return true;
        } catch (PendingIntent.CanceledException e) {
            if (!ListenerUtil.mutListener.listen(59692)) {
                logger.error("Cannot send pending accept intent: It was cancelled");
            }
            if (!ListenerUtil.mutListener.listen(59693)) {
                this.acceptIntent = null;
            }
            return false;
        }
    }

    /**
     *  Clear the canddidates cache for the specified identity.
     */
    void clearCandidatesCache(String identity) {
        if (!ListenerUtil.mutListener.listen(59696)) {
            logger.debug("Clearing candidates cache for {}", identity);
        }
        synchronized (this.candidatesCache) {
            if (!ListenerUtil.mutListener.listen(59697)) {
                this.candidatesCache.remove(identity);
            }
        }
    }

    /**
     *  Clear the candidates cache for all identities.
     */
    private void clearCandidatesCache() {
        if (!ListenerUtil.mutListener.listen(59698)) {
            logger.debug("Clearing candidates cache for all identities");
        }
        synchronized (this.candidatesCache) {
            if (!ListenerUtil.mutListener.listen(59699)) {
                this.candidatesCache.clear();
            }
        }
    }

    /**
     *  Cancel a pending call notification for the specified identity.
     */
    void cancelCallNotification(@NonNull String identity) {
        if (!ListenerUtil.mutListener.listen(59700)) {
            // Cancel fullscreen activity launched by notification first
            VoipUtil.sendVoipBroadcast(appContext, CallActivity.ACTION_CANCELLED);
        }
        if (!ListenerUtil.mutListener.listen(59701)) {
            ThreemaApplication.getAppContext().stopService(new Intent(ThreemaApplication.getAppContext(), VoipCallService.class));
        }
        if (!ListenerUtil.mutListener.listen(59702)) {
            this.stopRingtone();
        }
        synchronized (this.callNotificationTags) {
            if (!ListenerUtil.mutListener.listen(59707)) {
                if (this.callNotificationTags.contains(identity)) {
                    if (!ListenerUtil.mutListener.listen(59704)) {
                        logger.info("Cancelling call notification for {}", identity);
                    }
                    if (!ListenerUtil.mutListener.listen(59705)) {
                        this.notificationManagerCompat.cancel(identity, INCOMING_CALL_NOTIFICATION_ID);
                    }
                    if (!ListenerUtil.mutListener.listen(59706)) {
                        this.callNotificationTags.remove(identity);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(59703)) {
                        logger.warn("No call notification found for {}", identity);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59710)) {
            if (PushService.playServicesInstalled(appContext)) {
                if (!ListenerUtil.mutListener.listen(59708)) {
                    WearableHandler.cancelOnWearable(TYPE_NOTIFICATION);
                }
                if (!ListenerUtil.mutListener.listen(59709)) {
                    WearableHandler.cancelOnWearable(TYPE_ACTIVITY);
                }
            }
        }
    }

    /**
     *  Cancel all pending call notifications.
     */
    void cancelCallNotificationsForNewCall() {
        synchronized (this.callNotificationTags) {
            if (!ListenerUtil.mutListener.listen(59711)) {
                logger.info("Cancelling all {} call notifications", this.callNotificationTags.size());
            }
            if (!ListenerUtil.mutListener.listen(59713)) {
                {
                    long _loopCounter706 = 0;
                    for (String tag : this.callNotificationTags) {
                        ListenerUtil.loopListener.listen("_loopCounter706", ++_loopCounter706);
                        if (!ListenerUtil.mutListener.listen(59712)) {
                            this.notificationManagerCompat.cancel(tag, INCOMING_CALL_NOTIFICATION_ID);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(59714)) {
                this.callNotificationTags.clear();
            }
        }
        if (!ListenerUtil.mutListener.listen(59716)) {
            if (PushService.playServicesInstalled(appContext)) {
                if (!ListenerUtil.mutListener.listen(59715)) {
                    WearableHandler.cancelOnWearable(TYPE_NOTIFICATION);
                }
            }
        }
    }

    /**
     *  Return the current call duration in seconds.
     *
     *  Return null if the call state is not CALLING.
     */
    @Nullable
    Integer getCallDuration() {
        final Long start = this.callStartTimestamp;
        if (start == null) {
            return null;
        } else {
            final long seconds = (ListenerUtil.mutListener.listen(59724) ? (((ListenerUtil.mutListener.listen(59720) ? (SystemClock.elapsedRealtime() % start) : (ListenerUtil.mutListener.listen(59719) ? (SystemClock.elapsedRealtime() / start) : (ListenerUtil.mutListener.listen(59718) ? (SystemClock.elapsedRealtime() * start) : (ListenerUtil.mutListener.listen(59717) ? (SystemClock.elapsedRealtime() + start) : (SystemClock.elapsedRealtime() - start)))))) % 1000) : (ListenerUtil.mutListener.listen(59723) ? (((ListenerUtil.mutListener.listen(59720) ? (SystemClock.elapsedRealtime() % start) : (ListenerUtil.mutListener.listen(59719) ? (SystemClock.elapsedRealtime() / start) : (ListenerUtil.mutListener.listen(59718) ? (SystemClock.elapsedRealtime() * start) : (ListenerUtil.mutListener.listen(59717) ? (SystemClock.elapsedRealtime() + start) : (SystemClock.elapsedRealtime() - start)))))) * 1000) : (ListenerUtil.mutListener.listen(59722) ? (((ListenerUtil.mutListener.listen(59720) ? (SystemClock.elapsedRealtime() % start) : (ListenerUtil.mutListener.listen(59719) ? (SystemClock.elapsedRealtime() / start) : (ListenerUtil.mutListener.listen(59718) ? (SystemClock.elapsedRealtime() * start) : (ListenerUtil.mutListener.listen(59717) ? (SystemClock.elapsedRealtime() + start) : (SystemClock.elapsedRealtime() - start)))))) - 1000) : (ListenerUtil.mutListener.listen(59721) ? (((ListenerUtil.mutListener.listen(59720) ? (SystemClock.elapsedRealtime() % start) : (ListenerUtil.mutListener.listen(59719) ? (SystemClock.elapsedRealtime() / start) : (ListenerUtil.mutListener.listen(59718) ? (SystemClock.elapsedRealtime() * start) : (ListenerUtil.mutListener.listen(59717) ? (SystemClock.elapsedRealtime() + start) : (SystemClock.elapsedRealtime() - start)))))) + 1000) : (((ListenerUtil.mutListener.listen(59720) ? (SystemClock.elapsedRealtime() % start) : (ListenerUtil.mutListener.listen(59719) ? (SystemClock.elapsedRealtime() / start) : (ListenerUtil.mutListener.listen(59718) ? (SystemClock.elapsedRealtime() * start) : (ListenerUtil.mutListener.listen(59717) ? (SystemClock.elapsedRealtime() + start) : (SystemClock.elapsedRealtime() - start)))))) / 1000)))));
            if ((ListenerUtil.mutListener.listen(59729) ? (seconds >= Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(59728) ? (seconds <= Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(59727) ? (seconds < Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(59726) ? (seconds != Integer.MAX_VALUE) : (ListenerUtil.mutListener.listen(59725) ? (seconds == Integer.MAX_VALUE) : (seconds > Integer.MAX_VALUE))))))) {
                return Integer.MAX_VALUE;
            }
            return (int) seconds;
        }
    }

    /**
     *  Show a call notification.
     */
    @WorkerThread
    private void showNotification(@NonNull ContactModel contact, long callId, @Nullable PendingIntent accept, @NonNull PendingIntent reject, final VoipCallOfferMessage msg, final VoipCallOfferData offerData, boolean isMuted) {
        final long timestamp = System.currentTimeMillis();
        final Bitmap avatar = this.contactService.getAvatar(contact, false);
        final PendingIntent inCallPendingIntent = createLaunchPendingIntent(contact.getIdentity(), msg);
        if (!ListenerUtil.mutListener.listen(59750)) {
            if (notificationManagerCompat.areNotificationsEnabled()) {
                final NotificationCompat.Builder nbuilder = new NotificationBuilderWrapper(this.appContext, NOTIFICATION_CHANNEL_CALL, isMuted);
                if (!ListenerUtil.mutListener.listen(59732)) {
                    // Content
                    nbuilder.setContentTitle(appContext.getString(R.string.voip_notification_title)).setContentText(appContext.getString(R.string.voip_notification_text, NameUtil.getDisplayNameOrNickname(contact, true))).setOngoing(true).setWhen(timestamp).setAutoCancel(false).setShowWhen(true);
                }
                if (!ListenerUtil.mutListener.listen(59733)) {
                    // Set up the main intent to send the user to the incoming call screen
                    nbuilder.setFullScreenIntent(inCallPendingIntent, true);
                }
                if (!ListenerUtil.mutListener.listen(59734)) {
                    nbuilder.setContentIntent(inCallPendingIntent);
                }
                if (!ListenerUtil.mutListener.listen(59735)) {
                    // Icons and colors
                    nbuilder.setLargeIcon(avatar).setSmallIcon(R.drawable.ic_phone_locked_white_24dp).setColor(this.appContext.getResources().getColor(R.color.accent_light));
                }
                if (!ListenerUtil.mutListener.listen(59736)) {
                    // Alerting
                    nbuilder.setPriority(NotificationCompat.PRIORITY_MAX).setCategory(NotificationCompat.CATEGORY_CALL);
                }
                if (!ListenerUtil.mutListener.listen(59737)) {
                    // Privacy
                    nbuilder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE).setPublicVersion(new NotificationCompat.Builder(appContext, ConfigUtils.supportsNotificationChannels() ? NOTIFICATION_CHANNEL_CALL : null).setContentTitle(appContext.getString(R.string.voip_notification_title)).setContentText(appContext.getString(R.string.notification_hidden_text)).setSmallIcon(R.drawable.ic_phone_locked_white_24dp).setColor(appContext.getResources().getColor(R.color.accent_light)).build());
                }
                // Add identity to notification for DND priority override
                String contactLookupUri = contactService.getAndroidContactLookupUriString(contact);
                if (!ListenerUtil.mutListener.listen(59739)) {
                    if (contactLookupUri != null) {
                        if (!ListenerUtil.mutListener.listen(59738)) {
                            nbuilder.addPerson(contactLookupUri);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(59743)) {
                    if ((ListenerUtil.mutListener.listen(59740) ? (preferenceService.isVoiceCallVibrate() || !isMuted) : (preferenceService.isVoiceCallVibrate() && !isMuted))) {
                        if (!ListenerUtil.mutListener.listen(59742)) {
                            nbuilder.setVibrate(VIBRATE_PATTERN_INCOMING_CALL);
                        }
                    } else if (!ConfigUtils.supportsNotificationChannels()) {
                        if (!ListenerUtil.mutListener.listen(59741)) {
                            nbuilder.setVibrate(VIBRATE_PATTERN_SILENT);
                        }
                    }
                }
                // Actions
                final SpannableString rejectString = new SpannableString(appContext.getString(R.string.voip_reject));
                if (!ListenerUtil.mutListener.listen(59744)) {
                    rejectString.setSpan(new ForegroundColorSpan(Color.RED), 0, rejectString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                final SpannableString acceptString = new SpannableString(appContext.getString(R.string.voip_accept));
                if (!ListenerUtil.mutListener.listen(59745)) {
                    acceptString.setSpan(new ForegroundColorSpan(Color.GREEN), 0, acceptString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                if (!ListenerUtil.mutListener.listen(59746)) {
                    nbuilder.addAction(R.drawable.ic_call_end_grey600_24dp, rejectString, reject).addAction(R.drawable.ic_call_grey600_24dp, acceptString, accept != null ? accept : inCallPendingIntent);
                }
                // Build notification
                final Notification notification = nbuilder.build();
                if (!ListenerUtil.mutListener.listen(59747)) {
                    // Set flags
                    notification.flags |= NotificationCompat.FLAG_INSISTENT | NotificationCompat.FLAG_NO_CLEAR | NotificationCompat.FLAG_ONGOING_EVENT;
                }
                synchronized (this.callNotificationTags) {
                    if (!ListenerUtil.mutListener.listen(59748)) {
                        this.notificationManagerCompat.notify(contact.getIdentity(), INCOMING_CALL_NOTIFICATION_ID, notification);
                    }
                    if (!ListenerUtil.mutListener.listen(59749)) {
                        this.callNotificationTags.add(contact.getIdentity());
                    }
                }
            } else {
                // notifications disabled in system settings - fire inCall pending intent to show CallActivity
                try {
                    if (!ListenerUtil.mutListener.listen(59731)) {
                        inCallPendingIntent.send();
                    }
                } catch (PendingIntent.CanceledException e) {
                    if (!ListenerUtil.mutListener.listen(59730)) {
                        logger.error("Could not send inCallPendingIntent", e);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(59752)) {
            // WEARABLE
            if (PushService.playServicesInstalled(appContext)) {
                if (!ListenerUtil.mutListener.listen(59751)) {
                    wearableHandler.showWearableNotification(contact, callId, avatar);
                }
            }
        }
    }

    private void playRingtone(MessageReceiver messageReceiver, boolean isMuted) {
        final Uri ringtoneUri = this.ringtoneService.getVoiceCallRingtone(messageReceiver.getUniqueIdString());
        if (!ListenerUtil.mutListener.listen(59766)) {
            if (ringtoneUri != null) {
                if (!ListenerUtil.mutListener.listen(59754)) {
                    if (ringtonePlayer != null) {
                        if (!ListenerUtil.mutListener.listen(59753)) {
                            stopRingtone();
                        }
                    }
                }
                boolean isSystemMuted = DNDUtil.getInstance().isSystemMuted(messageReceiver, notificationManager, notificationManagerCompat);
                if (!ListenerUtil.mutListener.listen(59765)) {
                    if ((ListenerUtil.mutListener.listen(59755) ? (!isMuted || !isSystemMuted) : (!isMuted && !isSystemMuted))) {
                        if (!ListenerUtil.mutListener.listen(59756)) {
                            audioManager.requestAudioFocus(this, AudioManager.STREAM_RING, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                        }
                        if (!ListenerUtil.mutListener.listen(59757)) {
                            ringtonePlayer = new MediaPlayerStateWrapper();
                        }
                        if (!ListenerUtil.mutListener.listen(59759)) {
                            ringtonePlayer.setStateListener(new MediaPlayerStateWrapper.StateListener() {

                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                }

                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    if (!ListenerUtil.mutListener.listen(59758)) {
                                        ringtonePlayer.start();
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(59760)) {
                            ringtonePlayer.setLooping(true);
                        }
                        if (!ListenerUtil.mutListener.listen(59761)) {
                            ringtonePlayer.setAudioStreamType(AudioManager.STREAM_RING);
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(59763)) {
                                ringtonePlayer.setDataSource(appContext, ringtoneUri);
                            }
                            if (!ListenerUtil.mutListener.listen(59764)) {
                                ringtonePlayer.prepareAsync();
                            }
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(59762)) {
                                stopRingtone();
                            }
                        }
                    }
                }
            }
        }
    }

    private synchronized void stopRingtone() {
        if (!ListenerUtil.mutListener.listen(59771)) {
            if (ringtonePlayer != null) {
                if (!ListenerUtil.mutListener.listen(59767)) {
                    ringtonePlayer.stop();
                }
                if (!ListenerUtil.mutListener.listen(59768)) {
                    ringtonePlayer.reset();
                }
                if (!ListenerUtil.mutListener.listen(59769)) {
                    ringtonePlayer.release();
                }
                if (!ListenerUtil.mutListener.listen(59770)) {
                    ringtonePlayer = null;
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(59774)) {
                audioManager.abandonAudioFocus(this);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(59772)) {
                logger.info("Failed to abandon audio focus");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(59773)) {
                this.ringtoneAudioFocusAbandoned.complete(null);
            }
        }
    }

    private PendingIntent createLaunchPendingIntent(@NonNull String identity, @Nullable VoipCallOfferMessage msg) {
        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
        if (!ListenerUtil.mutListener.listen(59775)) {
            intent.setClass(appContext, CallActivity.class);
        }
        if (!ListenerUtil.mutListener.listen(59776)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(59777)) {
            intent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
        }
        if (!ListenerUtil.mutListener.listen(59778)) {
            intent.putExtra(EXTRA_ACTIVITY_MODE, CallActivity.MODE_INCOMING_CALL);
        }
        if (!ListenerUtil.mutListener.listen(59779)) {
            intent.putExtra(EXTRA_CONTACT_IDENTITY, identity);
        }
        if (!ListenerUtil.mutListener.listen(59780)) {
            intent.putExtra(EXTRA_IS_INITIATOR, false);
        }
        if (!ListenerUtil.mutListener.listen(59782)) {
            if (msg != null) {
                final VoipCallOfferData data = msg.getData();
                if (!ListenerUtil.mutListener.listen(59781)) {
                    intent.putExtra(EXTRA_CALL_ID, data.getCallIdOrDefault(0L));
                }
            }
        }
        // call (see the "fullScreenIntent" field below).
        return PendingIntent.getActivity(appContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     *  Add a new ICE candidate to the cache.
     */
    private void cacheCandidate(String identity, VoipICECandidatesData data) {
        if (!ListenerUtil.mutListener.listen(59783)) {
            logCallDebug(data.getCallIdOrDefault(0L), "Caching candidate from {}", identity);
        }
        synchronized (this.candidatesCache) {
            if (!ListenerUtil.mutListener.listen(59787)) {
                if (this.candidatesCache.containsKey(identity)) {
                    List<VoipICECandidatesData> candidates = this.candidatesCache.get(identity);
                    if (!ListenerUtil.mutListener.listen(59786)) {
                        candidates.add(data);
                    }
                } else {
                    List<VoipICECandidatesData> candidates = new LinkedList<>();
                    if (!ListenerUtil.mutListener.listen(59784)) {
                        candidates.add(data);
                    }
                    if (!ListenerUtil.mutListener.listen(59785)) {
                        this.candidatesCache.put(identity, candidates);
                    }
                }
            }
        }
    }

    /**
     *  Create a new video context.
     *
     *  Throws an `IllegalStateException` if a video context already exists.
     */
    void createVideoContext() throws IllegalStateException {
        if (!ListenerUtil.mutListener.listen(59788)) {
            logger.trace("createVideoContext");
        }
        if (!ListenerUtil.mutListener.listen(59789)) {
            if (this.videoContext != null) {
                throw new IllegalStateException("Video context already exists");
            }
        }
        if (!ListenerUtil.mutListener.listen(59790)) {
            this.videoContext = new VideoContext();
        }
        if (!ListenerUtil.mutListener.listen(59791)) {
            this.videoContextFuture.complete(this.videoContext);
        }
    }

    /**
     *  Return a reference to the video context instance.
     */
    @Nullable
    public VideoContext getVideoContext() {
        return this.videoContext;
    }

    /**
     *  Return a future that resolves with the video context instance.
     */
    @NonNull
    public CompletableFuture<VideoContext> getVideoContextFuture() {
        return this.videoContextFuture;
    }

    /**
     *  Release resources associated with the video context instance.
     *
     *  It's safe to call this method multiple times.
     */
    void releaseVideoContext() {
        if (!ListenerUtil.mutListener.listen(59795)) {
            if (this.videoContext != null) {
                if (!ListenerUtil.mutListener.listen(59792)) {
                    this.videoContext.release();
                }
                if (!ListenerUtil.mutListener.listen(59793)) {
                    this.videoContext = null;
                }
                if (!ListenerUtil.mutListener.listen(59794)) {
                    this.videoContextFuture = new CompletableFuture<>();
                }
            }
        }
    }

    public int getVideoRenderMode() {
        return videoRenderMode;
    }

    public void setVideoRenderMode(int videoRenderMode) {
        if (!ListenerUtil.mutListener.listen(59796)) {
            this.videoRenderMode = videoRenderMode;
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (!ListenerUtil.mutListener.listen(59797)) {
            logger.info("Audio Focus change: " + focusChange);
        }
    }

    public synchronized CompletableFuture<Void> getRingtoneAudioFocusAbandoned() {
        return this.ringtoneAudioFocusAbandoned;
    }
}
