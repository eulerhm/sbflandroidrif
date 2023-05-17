/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.services;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;
import androidx.core.app.TaskStackBuilder;
import androidx.core.graphics.drawable.IconCompat;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.BackupAdminActivity;
import ch.threema.app.activities.ComposeMessageActivity;
import ch.threema.app.activities.HomeActivity;
import ch.threema.app.activities.ServerMessageActivity;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.notifications.NotificationBuilderWrapper;
import ch.threema.app.receivers.AcknowledgeActionBroadcastReceiver;
import ch.threema.app.receivers.DeclineActionBroadcastReceiver;
import ch.threema.app.receivers.MarkReadActionBroadcastReceiver;
import ch.threema.app.receivers.ReSendMessagesBroadcastReceiver;
import ch.threema.app.receivers.ReplyActionBroadcastReceiver;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.DNDUtil;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.app.utils.WidgetUtil;
import ch.threema.app.voip.activities.CallActivity;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.ServerMessageModel;
import static androidx.core.app.NotificationCompat.MessagingStyle.MAXIMUM_RETAINED_MESSAGES;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_ACTIVITY_MODE;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_CALL_ID;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_CONTACT_IDENTITY;
import static ch.threema.app.voip.services.VoipCallService.EXTRA_IS_INITIATOR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private static final long NOTIFY_AGAIN_TIMEOUT = 30 * DateUtils.SECOND_IN_MILLIS;

    private final Context context;

    private final LockAppService lockAppService;

    private final DeadlineListService hiddenChatsListService;

    private final PreferenceService preferenceService;

    private final RingtoneService ringtoneService;

    private ContactService contactService = null;

    private static final int MAX_TICKER_TEXT_LENGTH = 256;

    public static final int APP_RESTART_NOTIFICATION_ID = 481773;

    private static final String GROUP_KEY_MESSAGES = "threema_messages_key";

    private static final String PIN_LOCKED_NOTIFICATION_ID = "(transition to locked state)";

    private AsyncQueryHandler queryHandler;

    private final NotificationManagerCompat notificationManagerCompat;

    private final NotificationManager notificationManager;

    private final LinkedList<ConversationNotification> conversationNotifications = new LinkedList<>();

    private MessageReceiver visibleConversationReceiver;

    public static class NotificationSchemaImpl implements NotificationSchema {

        private boolean vibrate = false;

        private int ringerMode = 0;

        private Uri soundUri = null;

        private int color = 0;

        public NotificationSchemaImpl(Context context) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (!ListenerUtil.mutListener.listen(41571)) {
                this.setRingerMode(audioManager.getRingerMode());
            }
        }

        @Override
        public boolean vibrate() {
            return this.vibrate;
        }

        @Override
        public int getRingerMode() {
            return this.ringerMode;
        }

        @Override
        public Uri getSoundUri() {
            return this.soundUri;
        }

        @Override
        public int getColor() {
            return this.color;
        }

        public NotificationSchemaImpl setColor(int color) {
            if (!ListenerUtil.mutListener.listen(41572)) {
                this.color = color;
            }
            return this;
        }

        public NotificationSchemaImpl setSoundUri(Uri soundUri) {
            if (!ListenerUtil.mutListener.listen(41573)) {
                this.soundUri = soundUri;
            }
            return this;
        }

        public NotificationSchemaImpl setRingerMode(int ringerMode) {
            if (!ListenerUtil.mutListener.listen(41574)) {
                this.ringerMode = ringerMode;
            }
            return this;
        }

        public NotificationSchemaImpl setVibrate(boolean vibrate) {
            if (!ListenerUtil.mutListener.listen(41575)) {
                this.vibrate = vibrate;
            }
            return this;
        }
    }

    public NotificationServiceImpl(Context context, LockAppService lockAppService, DeadlineListService hiddenChatsListService, PreferenceService preferenceService, RingtoneService ringtoneService) {
        this.context = context;
        this.lockAppService = lockAppService;
        this.hiddenChatsListService = hiddenChatsListService;
        this.preferenceService = preferenceService;
        this.ringtoneService = ringtoneService;
        this.notificationManagerCompat = NotificationManagerCompat.from(context);
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(41578)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(41577)) {
                        this.contactService = serviceManager.getContactService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(41576)) {
                        logger.error("Exception", e);
                    }
                    return;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41579)) {
            /* create notification channels */
            createNotificationChannels();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void deleteNotificationChannels() {
        if (!ListenerUtil.mutListener.listen(41580)) {
            if (!ConfigUtils.supportsNotificationChannels()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(41581)) {
            notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_PASSPHRASE);
        }
        if (!ListenerUtil.mutListener.listen(41582)) {
            notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_WEBCLIENT);
        }
        if (!ListenerUtil.mutListener.listen(41583)) {
            notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_IN_CALL);
        }
        if (!ListenerUtil.mutListener.listen(41584)) {
            notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_ALERT);
        }
        if (!ListenerUtil.mutListener.listen(41585)) {
            notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_NOTICE);
        }
        if (!ListenerUtil.mutListener.listen(41586)) {
            notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_BACKUP_RESTORE_IN_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(41588)) {
            if (ConfigUtils.isWorkBuild()) {
                if (!ListenerUtil.mutListener.listen(41587)) {
                    notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_WORK_SYNC);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41589)) {
            notificationManager.deleteNotificationChannelGroup(NOTIFICATION_CHANNELGROUP_CHAT);
        }
        if (!ListenerUtil.mutListener.listen(41590)) {
            notificationManager.deleteNotificationChannelGroup(NOTIFICATION_CHANNELGROUP_CHAT_UPDATE);
        }
        if (!ListenerUtil.mutListener.listen(41591)) {
            notificationManager.deleteNotificationChannelGroup(NOTIFICATION_CHANNELGROUP_VOIP);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void createNotificationChannels() {
        if (!ListenerUtil.mutListener.listen(41592)) {
            if (!ConfigUtils.supportsNotificationChannels()) {
                return;
            }
        }
        NotificationChannel notificationChannel;
        // passphrase notification
        notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_PASSPHRASE, context.getString(R.string.passphrase_service_name), NotificationManager.IMPORTANCE_LOW);
        if (!ListenerUtil.mutListener.listen(41593)) {
            notificationChannel.setDescription(context.getString(R.string.passphrase_service_description));
        }
        if (!ListenerUtil.mutListener.listen(41594)) {
            notificationChannel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(41595)) {
            notificationChannel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(41596)) {
            notificationChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(41597)) {
            notificationChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(41598)) {
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
        }
        if (!ListenerUtil.mutListener.listen(41599)) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // webclient notifications
        notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_WEBCLIENT, context.getString(R.string.webclient), NotificationManager.IMPORTANCE_LOW);
        if (!ListenerUtil.mutListener.listen(41600)) {
            notificationChannel.setDescription(context.getString(R.string.webclient_service_description));
        }
        if (!ListenerUtil.mutListener.listen(41601)) {
            notificationChannel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(41602)) {
            notificationChannel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(41603)) {
            notificationChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(41604)) {
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        if (!ListenerUtil.mutListener.listen(41605)) {
            notificationChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(41606)) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // in call notifications
        notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_IN_CALL, context.getString(R.string.call_ongoing), NotificationManager.IMPORTANCE_LOW);
        if (!ListenerUtil.mutListener.listen(41607)) {
            notificationChannel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(41608)) {
            notificationChannel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(41609)) {
            notificationChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(41610)) {
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        if (!ListenerUtil.mutListener.listen(41611)) {
            notificationChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(41612)) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // alert notification
        notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ALERT, context.getString(R.string.notification_channel_alerts), NotificationManager.IMPORTANCE_HIGH);
        if (!ListenerUtil.mutListener.listen(41613)) {
            notificationChannel.enableLights(true);
        }
        if (!ListenerUtil.mutListener.listen(41614)) {
            notificationChannel.enableVibration(true);
        }
        if (!ListenerUtil.mutListener.listen(41615)) {
            notificationChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(41616)) {
            notificationChannel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null);
        }
        if (!ListenerUtil.mutListener.listen(41617)) {
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        if (!ListenerUtil.mutListener.listen(41618)) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // notice notification
        notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_NOTICE, context.getString(R.string.notification_channel_notices), NotificationManager.IMPORTANCE_LOW);
        if (!ListenerUtil.mutListener.listen(41619)) {
            notificationChannel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(41620)) {
            notificationChannel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(41621)) {
            notificationChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(41622)) {
            notificationChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(41623)) {
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        }
        if (!ListenerUtil.mutListener.listen(41624)) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // backup notification
        notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_BACKUP_RESTORE_IN_PROGRESS, context.getString(R.string.backup_or_restore_progress), NotificationManager.IMPORTANCE_LOW);
        if (!ListenerUtil.mutListener.listen(41625)) {
            notificationChannel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(41626)) {
            notificationChannel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(41627)) {
            notificationChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(41628)) {
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
        }
        if (!ListenerUtil.mutListener.listen(41629)) {
            notificationChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(41630)) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // work sync notification
        if (ConfigUtils.isWorkBuild()) {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_WORK_SYNC, context.getString(R.string.work_data_sync), NotificationManager.IMPORTANCE_LOW);
            if (!ListenerUtil.mutListener.listen(41631)) {
                notificationChannel.setDescription(context.getString(R.string.work_data_sync_desc));
            }
            if (!ListenerUtil.mutListener.listen(41632)) {
                notificationChannel.enableLights(false);
            }
            if (!ListenerUtil.mutListener.listen(41633)) {
                notificationChannel.enableVibration(false);
            }
            if (!ListenerUtil.mutListener.listen(41634)) {
                notificationChannel.setShowBadge(false);
            }
            if (!ListenerUtil.mutListener.listen(41635)) {
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            }
            if (!ListenerUtil.mutListener.listen(41636)) {
                notificationChannel.setSound(null, null);
            }
            if (!ListenerUtil.mutListener.listen(41637)) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        // new synced contact notification
        notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_NEW_SYNCED_CONTACTS, context.getString(R.string.notification_channel_new_contact), NotificationManager.IMPORTANCE_HIGH);
        if (!ListenerUtil.mutListener.listen(41638)) {
            notificationChannel.setDescription(context.getString(R.string.notification_channel_new_contact_desc));
        }
        if (!ListenerUtil.mutListener.listen(41639)) {
            notificationChannel.enableLights(true);
        }
        if (!ListenerUtil.mutListener.listen(41640)) {
            notificationChannel.enableVibration(true);
        }
        if (!ListenerUtil.mutListener.listen(41641)) {
            notificationChannel.setShowBadge(false);
        }
        if (!ListenerUtil.mutListener.listen(41642)) {
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
        }
        if (!ListenerUtil.mutListener.listen(41643)) {
            notificationChannel.setSound(null, null);
        }
        if (!ListenerUtil.mutListener.listen(41644)) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void setVisibleReceiver(MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(41646)) {
            if (receiver != null) {
                if (!ListenerUtil.mutListener.listen(41645)) {
                    // cancel
                    this.cancel(receiver);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41647)) {
            this.visibleConversationReceiver = receiver;
        }
    }

    @SuppressLint({ "StaticFieldLeak" })
    @Override
    public void addConversationNotification(final ConversationNotification conversationNotification, boolean updateExisting) {
        if (!ListenerUtil.mutListener.listen(41648)) {
            logger.debug("addConversationNotifications");
        }
        synchronized (this.conversationNotifications) {
            if (!ListenerUtil.mutListener.listen(41651)) {
                // check if current receiver is the receiver of the group
                if ((ListenerUtil.mutListener.listen(41649) ? (this.visibleConversationReceiver != null || conversationNotification.getGroup().getMessageReceiver().isEqual(this.visibleConversationReceiver)) : (this.visibleConversationReceiver != null && conversationNotification.getGroup().getMessageReceiver().isEqual(this.visibleConversationReceiver)))) {
                    if (!ListenerUtil.mutListener.listen(41650)) {
                        // ignore notification
                        logger.info("No notification - chat visible");
                    }
                    return;
                }
            }
            String uniqueId = null;
            if (!ListenerUtil.mutListener.listen(41656)) {
                // check if notification not exist
                if (Functional.select(this.conversationNotifications, conversationNotification1 -> TestUtil.compare(conversationNotification1.getUid(), conversationNotification.getUid())) == null) {
                    if (!ListenerUtil.mutListener.listen(41653)) {
                        uniqueId = conversationNotification.getGroup().getMessageReceiver().getUniqueIdString();
                    }
                    if (!ListenerUtil.mutListener.listen(41655)) {
                        if (!DNDUtil.getInstance().isMuted(conversationNotification.getGroup().getMessageReceiver(), conversationNotification.getRawMessage())) {
                            if (!ListenerUtil.mutListener.listen(41654)) {
                                this.conversationNotifications.addFirst(conversationNotification);
                            }
                        }
                    }
                } else if (updateExisting) {
                    if (!ListenerUtil.mutListener.listen(41652)) {
                        uniqueId = conversationNotification.getGroup().getMessageReceiver().getUniqueIdString();
                    }
                }
            }
            Map<String, ConversationNotificationGroup> uniqueNotificationGroups = new HashMap<>();
            // to refactor on merge update and add
            final ConversationNotificationGroup newestGroup = conversationNotification.getGroup();
            int numberOfNotificationsForCurrentChat = 0;
            if (!ListenerUtil.mutListener.listen(41660)) {
                {
                    long _loopCounter474 = 0;
                    for (ConversationNotification notification : this.conversationNotifications) {
                        ListenerUtil.loopListener.listen("_loopCounter474", ++_loopCounter474);
                        ConversationNotificationGroup group = notification.getGroup();
                        if (!ListenerUtil.mutListener.listen(41657)) {
                            uniqueNotificationGroups.put(group.getGroupUid(), group);
                        }
                        if (!ListenerUtil.mutListener.listen(41659)) {
                            if (notification.getGroup().equals(newestGroup)) {
                                if (!ListenerUtil.mutListener.listen(41658)) {
                                    numberOfNotificationsForCurrentChat++;
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(41662)) {
                if (!TestUtil.required(conversationNotification, newestGroup)) {
                    if (!ListenerUtil.mutListener.listen(41661)) {
                        logger.info("No notification - missing data");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(41673)) {
                if (updateExisting) {
                    if (!ListenerUtil.mutListener.listen(41669)) {
                        if ((ListenerUtil.mutListener.listen(41668) ? (!ConfigUtils.canDoGroupedNotifications() && (ListenerUtil.mutListener.listen(41667) ? (numberOfNotificationsForCurrentChat >= 1) : (ListenerUtil.mutListener.listen(41666) ? (numberOfNotificationsForCurrentChat <= 1) : (ListenerUtil.mutListener.listen(41665) ? (numberOfNotificationsForCurrentChat < 1) : (ListenerUtil.mutListener.listen(41664) ? (numberOfNotificationsForCurrentChat != 1) : (ListenerUtil.mutListener.listen(41663) ? (numberOfNotificationsForCurrentChat == 1) : (numberOfNotificationsForCurrentChat > 1))))))) : (!ConfigUtils.canDoGroupedNotifications() || (ListenerUtil.mutListener.listen(41667) ? (numberOfNotificationsForCurrentChat >= 1) : (ListenerUtil.mutListener.listen(41666) ? (numberOfNotificationsForCurrentChat <= 1) : (ListenerUtil.mutListener.listen(41665) ? (numberOfNotificationsForCurrentChat < 1) : (ListenerUtil.mutListener.listen(41664) ? (numberOfNotificationsForCurrentChat != 1) : (ListenerUtil.mutListener.listen(41663) ? (numberOfNotificationsForCurrentChat == 1) : (numberOfNotificationsForCurrentChat > 1))))))))) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(41671)) {
                        if ((ListenerUtil.mutListener.listen(41670) ? (!this.preferenceService.isShowMessagePreview() && hiddenChatsListService.has(uniqueId)) : (!this.preferenceService.isShowMessagePreview() || hiddenChatsListService.has(uniqueId)))) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(41672)) {
                        if (this.lockAppService.isLocked()) {
                            return;
                        }
                    }
                }
            }
            final String latestFullName = newestGroup.getName();
            int unreadMessagesCount = this.conversationNotifications.size();
            int unreadConversationsCount = uniqueNotificationGroups.size();
            NotificationSchema notificationSchema = this.createNotificationSchema(newestGroup, conversationNotification.getRawMessage());
            Bitmap latestThumbnail = null;
            if (!ListenerUtil.mutListener.listen(41675)) {
                if (notificationSchema == null) {
                    if (!ListenerUtil.mutListener.listen(41674)) {
                        logger.warn("No notification - no notification schema");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(41677)) {
                if (this.lockAppService.isLocked()) {
                    if (!ListenerUtil.mutListener.listen(41676)) {
                        this.showPinLockedNewMessageNotification(notificationSchema, conversationNotification.getUid(), false);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(41678)) {
                // make sure pin locked notification is canceled
                cancelPinLockedNewMessagesNotification();
            }
            CharSequence tickerText;
            CharSequence singleMessageText;
            String summaryText = (ListenerUtil.mutListener.listen(41683) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(41682) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(41681) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(41680) ? (unreadMessagesCount != 1) : (ListenerUtil.mutListener.listen(41679) ? (unreadMessagesCount == 1) : (unreadMessagesCount > 1)))))) ? ((ListenerUtil.mutListener.listen(41688) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(41687) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(41686) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(41685) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(41684) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) ? String.format(context.getString(R.string.new_messages_in_chats), unreadMessagesCount, unreadConversationsCount) : unreadMessagesCount + " " + context.getString(R.string.new_messages)) : context.getString(R.string.new_message);
            String contentTitle;
            Intent notificationIntent;
            Bitmap summaryAvatar;
            NotificationCompat.InboxStyle inboxStyle = null;
            /* set avatar, intent and contentTitle */
            if ((ListenerUtil.mutListener.listen(41694) ? ((ListenerUtil.mutListener.listen(41693) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(41692) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(41691) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(41690) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(41689) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) || !ConfigUtils.canDoGroupedNotifications()) : ((ListenerUtil.mutListener.listen(41693) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(41692) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(41691) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(41690) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(41689) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) && !ConfigUtils.canDoGroupedNotifications()))) {
                /* notification is for more than one chat */
                summaryAvatar = getConversationNotificationAvatar();
                notificationIntent = new Intent(context, HomeActivity.class);
                contentTitle = context.getString(R.string.app_name);
            } else {
                /* notification is for single chat */
                summaryAvatar = newestGroup.getAvatar();
                notificationIntent = new Intent(context, ComposeMessageActivity.class);
                if (!ListenerUtil.mutListener.listen(41695)) {
                    newestGroup.getMessageReceiver().prepareIntent(notificationIntent);
                }
                contentTitle = latestFullName;
            }
            if (hiddenChatsListService.has(uniqueId)) {
                tickerText = summaryText;
                singleMessageText = summaryText;
            } else {
                if (this.preferenceService.isShowMessagePreview()) {
                    tickerText = latestFullName + ": " + TextUtil.trim(conversationNotification.getMessage(), MAX_TICKER_TEXT_LENGTH, "...");
                    if (!ListenerUtil.mutListener.listen(41696)) {
                        inboxStyle = new NotificationCompat.InboxStyle();
                    }
                    if (!ListenerUtil.mutListener.listen(41697)) {
                        getInboxStyle(inboxStyle, unreadConversationsCount);
                    }
                    if (!ListenerUtil.mutListener.listen(41698)) {
                        inboxStyle.setBigContentTitle(contentTitle);
                    }
                    if (!ListenerUtil.mutListener.listen(41699)) {
                        inboxStyle.setSummaryText(summaryText);
                    }
                    if (!ListenerUtil.mutListener.listen(41700)) {
                        latestThumbnail = conversationNotification.getThumbnail();
                    }
                    singleMessageText = conversationNotification.getMessage();
                } else {
                    tickerText = latestFullName + ": " + summaryText;
                    singleMessageText = summaryText;
                }
            }
            if (!ListenerUtil.mutListener.listen(41701)) {
                // Create PendingIntent for notification tab
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_USER_ACTION);
            }
            PendingIntent openPendingIntent = createPendingIntentWithTaskStack(notificationIntent);
            // Intent for ack action
            Intent ackIntent = new Intent(context, AcknowledgeActionBroadcastReceiver.class);
            if (!ListenerUtil.mutListener.listen(41702)) {
                newestGroup.getMessageReceiver().prepareIntent(ackIntent);
            }
            if (!ListenerUtil.mutListener.listen(41703)) {
                ackIntent.putExtra(ThreemaApplication.INTENT_DATA_MESSAGE_ID, conversationNotification.getId());
            }
            PendingIntent ackPendingIntent = PendingIntent.getBroadcast(context, // http://stackoverflow.com/questions/19031861/pendingintent-not-opening-activity-in-android-4-3
            getRandomRequestCode(), ackIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            // Intent for dec action
            Intent decIntent = new Intent(context, DeclineActionBroadcastReceiver.class);
            if (!ListenerUtil.mutListener.listen(41704)) {
                newestGroup.getMessageReceiver().prepareIntent(decIntent);
            }
            if (!ListenerUtil.mutListener.listen(41705)) {
                decIntent.putExtra(ThreemaApplication.INTENT_DATA_MESSAGE_ID, conversationNotification.getId());
            }
            PendingIntent decPendingIntent = PendingIntent.getBroadcast(context, getRandomRequestCode(), decIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            // Intent for reply action
            Intent replyIntent = new Intent(context, ReplyActionBroadcastReceiver.class);
            if (!ListenerUtil.mutListener.listen(41706)) {
                newestGroup.getMessageReceiver().prepareIntent(replyIntent);
            }
            if (!ListenerUtil.mutListener.listen(41707)) {
                replyIntent.putExtra(ThreemaApplication.INTENT_DATA_MESSAGE_ID, conversationNotification.getId());
            }
            PendingIntent replyPendingIntent = PendingIntent.getBroadcast(context, getRandomRequestCode(), replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            // Intent for "Mark read" action
            Intent markReadIntent = new Intent(context, MarkReadActionBroadcastReceiver.class);
            if (!ListenerUtil.mutListener.listen(41708)) {
                newestGroup.getMessageReceiver().prepareIntent(markReadIntent);
            }
            PendingIntent markReadPendingIntent = PendingIntent.getBroadcast(context, getRandomRequestCode(), markReadIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            long timestamp = System.currentTimeMillis();
            boolean onlyAlertOnce = (ListenerUtil.mutListener.listen(41717) ? (((ListenerUtil.mutListener.listen(41712) ? (timestamp % newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41711) ? (timestamp / newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41710) ? (timestamp * newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41709) ? (timestamp + newestGroup.getLastNotificationDate()) : (timestamp - newestGroup.getLastNotificationDate())))))) >= NOTIFY_AGAIN_TIMEOUT) : (ListenerUtil.mutListener.listen(41716) ? (((ListenerUtil.mutListener.listen(41712) ? (timestamp % newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41711) ? (timestamp / newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41710) ? (timestamp * newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41709) ? (timestamp + newestGroup.getLastNotificationDate()) : (timestamp - newestGroup.getLastNotificationDate())))))) <= NOTIFY_AGAIN_TIMEOUT) : (ListenerUtil.mutListener.listen(41715) ? (((ListenerUtil.mutListener.listen(41712) ? (timestamp % newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41711) ? (timestamp / newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41710) ? (timestamp * newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41709) ? (timestamp + newestGroup.getLastNotificationDate()) : (timestamp - newestGroup.getLastNotificationDate())))))) > NOTIFY_AGAIN_TIMEOUT) : (ListenerUtil.mutListener.listen(41714) ? (((ListenerUtil.mutListener.listen(41712) ? (timestamp % newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41711) ? (timestamp / newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41710) ? (timestamp * newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41709) ? (timestamp + newestGroup.getLastNotificationDate()) : (timestamp - newestGroup.getLastNotificationDate())))))) != NOTIFY_AGAIN_TIMEOUT) : (ListenerUtil.mutListener.listen(41713) ? (((ListenerUtil.mutListener.listen(41712) ? (timestamp % newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41711) ? (timestamp / newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41710) ? (timestamp * newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41709) ? (timestamp + newestGroup.getLastNotificationDate()) : (timestamp - newestGroup.getLastNotificationDate())))))) == NOTIFY_AGAIN_TIMEOUT) : (((ListenerUtil.mutListener.listen(41712) ? (timestamp % newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41711) ? (timestamp / newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41710) ? (timestamp * newestGroup.getLastNotificationDate()) : (ListenerUtil.mutListener.listen(41709) ? (timestamp + newestGroup.getLastNotificationDate()) : (timestamp - newestGroup.getLastNotificationDate())))))) < NOTIFY_AGAIN_TIMEOUT))))));
            if (!ListenerUtil.mutListener.listen(41718)) {
                newestGroup.setLastNotificationDate(timestamp);
            }
            final NotificationCompat.Builder builder;
            if (ConfigUtils.canDoGroupedNotifications()) {
                if (!ListenerUtil.mutListener.listen(41751)) {
                    summaryText = (ListenerUtil.mutListener.listen(41750) ? (numberOfNotificationsForCurrentChat >= 1) : (ListenerUtil.mutListener.listen(41749) ? (numberOfNotificationsForCurrentChat <= 1) : (ListenerUtil.mutListener.listen(41748) ? (numberOfNotificationsForCurrentChat < 1) : (ListenerUtil.mutListener.listen(41747) ? (numberOfNotificationsForCurrentChat != 1) : (ListenerUtil.mutListener.listen(41746) ? (numberOfNotificationsForCurrentChat == 1) : (numberOfNotificationsForCurrentChat > 1)))))) ? numberOfNotificationsForCurrentChat + " " + context.getString(R.string.new_messages) : context.getString(R.string.new_message);
                }
                if ((ListenerUtil.mutListener.listen(41752) ? (!this.preferenceService.isShowMessagePreview() && hiddenChatsListService.has(uniqueId)) : (!this.preferenceService.isShowMessagePreview() || hiddenChatsListService.has(uniqueId)))) {
                    singleMessageText = summaryText;
                    tickerText = summaryText;
                }
                // public version of the notification
                NotificationCompat.Builder publicBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_CHAT).setContentTitle(summaryText).setContentText(context.getString(R.string.notification_hidden_text)).setSmallIcon(R.drawable.ic_notification_small).setColor(context.getResources().getColor(R.color.accent_light)).setOnlyAlertOnce(onlyAlertOnce);
                // private version
                builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_CHAT, notificationSchema, publicBuilder).setContentTitle(contentTitle).setContentText(singleMessageText).setTicker(tickerText).setSmallIcon(R.drawable.ic_notification_small).setLargeIcon(newestGroup.getAvatar()).setColor(context.getResources().getColor(R.color.accent_light)).setGroup(newestGroup.getGroupUid()).setGroupSummary(false).setOnlyAlertOnce(onlyAlertOnce).setPriority(this.preferenceService.getNotificationPriority()).setCategory(NotificationCompat.CATEGORY_MESSAGE).setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                if (!ListenerUtil.mutListener.listen(41754)) {
                    // Add identity to notification for system DND priority override
                    if (newestGroup.getLookupUri() != null) {
                        if (!ListenerUtil.mutListener.listen(41753)) {
                            builder.addPerson(newestGroup.getLookupUri());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41769)) {
                    if ((ListenerUtil.mutListener.listen(41755) ? (this.preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (this.preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId)))) {
                        if (!ListenerUtil.mutListener.listen(41756)) {
                            builder.setStyle(getMessagingStyle(newestGroup, getConversationNotificationsForGroup(newestGroup)));
                        }
                        if (!ListenerUtil.mutListener.listen(41757)) {
                            latestThumbnail = conversationNotification.getThumbnail();
                        }
                        if (!ListenerUtil.mutListener.listen(41766)) {
                            if ((ListenerUtil.mutListener.listen(41764) ? ((ListenerUtil.mutListener.listen(41758) ? (latestThumbnail != null || !latestThumbnail.isRecycled()) : (latestThumbnail != null && !latestThumbnail.isRecycled())) || (ListenerUtil.mutListener.listen(41763) ? (numberOfNotificationsForCurrentChat >= 1) : (ListenerUtil.mutListener.listen(41762) ? (numberOfNotificationsForCurrentChat <= 1) : (ListenerUtil.mutListener.listen(41761) ? (numberOfNotificationsForCurrentChat > 1) : (ListenerUtil.mutListener.listen(41760) ? (numberOfNotificationsForCurrentChat < 1) : (ListenerUtil.mutListener.listen(41759) ? (numberOfNotificationsForCurrentChat != 1) : (numberOfNotificationsForCurrentChat == 1))))))) : ((ListenerUtil.mutListener.listen(41758) ? (latestThumbnail != null || !latestThumbnail.isRecycled()) : (latestThumbnail != null && !latestThumbnail.isRecycled())) && (ListenerUtil.mutListener.listen(41763) ? (numberOfNotificationsForCurrentChat >= 1) : (ListenerUtil.mutListener.listen(41762) ? (numberOfNotificationsForCurrentChat <= 1) : (ListenerUtil.mutListener.listen(41761) ? (numberOfNotificationsForCurrentChat > 1) : (ListenerUtil.mutListener.listen(41760) ? (numberOfNotificationsForCurrentChat < 1) : (ListenerUtil.mutListener.listen(41759) ? (numberOfNotificationsForCurrentChat != 1) : (numberOfNotificationsForCurrentChat == 1))))))))) {
                                if (!ListenerUtil.mutListener.listen(41765)) {
                                    // add image preview
                                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(latestThumbnail).setSummaryText(conversationNotification.getMessage()));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(41767)) {
                            addConversationNotificationActions(builder, replyPendingIntent, ackPendingIntent, markReadPendingIntent, conversationNotification, numberOfNotificationsForCurrentChat, unreadConversationsCount, uniqueId, newestGroup);
                        }
                        if (!ListenerUtil.mutListener.listen(41768)) {
                            addWearableExtender(builder, newestGroup, ackPendingIntent, decPendingIntent, replyPendingIntent, markReadPendingIntent, timestamp, latestThumbnail, numberOfNotificationsForCurrentChat, singleMessageText != null ? singleMessageText.toString() : "", uniqueId);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41770)) {
                    builder.setContentIntent(openPendingIntent);
                }
                if (!ListenerUtil.mutListener.listen(41778)) {
                    if ((ListenerUtil.mutListener.listen(41771) ? (this.conversationNotifications.size() == 1 || updateExisting) : (this.conversationNotifications.size() == 1 && updateExisting))) {
                        if (!ListenerUtil.mutListener.listen(41777)) {
                            // we need to delay the updated notification to allow the sound of the first notification to play properly
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                                @TargetApi(Build.VERSION_CODES.M)
                                @Override
                                public void run() {
                                    try {
                                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                                        if (!ListenerUtil.mutListener.listen(41776)) {
                                            if (notificationManager != null) {
                                                StatusBarNotification[] notifications = notificationManager.getActiveNotifications();
                                                if (!ListenerUtil.mutListener.listen(41775)) {
                                                    {
                                                        long _loopCounter475 = 0;
                                                        for (StatusBarNotification notification : notifications) {
                                                            ListenerUtil.loopListener.listen("_loopCounter475", ++_loopCounter475);
                                                            if (!ListenerUtil.mutListener.listen(41774)) {
                                                                if (notification.getId() == newestGroup.getNotificationId()) {
                                                                    if (!ListenerUtil.mutListener.listen(41773)) {
                                                                        NotificationServiceImpl.this.notify(newestGroup.getNotificationId(), builder);
                                                                    }
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }, 4000);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(41772)) {
                            this.notify(newestGroup.getNotificationId(), builder);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(41719)) {
                    createSingleNotification(newestGroup, conversationNotification, openPendingIntent, ackPendingIntent, decPendingIntent, replyPendingIntent, markReadPendingIntent, timestamp, latestThumbnail, numberOfNotificationsForCurrentChat, uniqueId);
                }
                // summary notification
                builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_CHAT, notificationSchema).setContentTitle(contentTitle).setContentText((ListenerUtil.mutListener.listen(41724) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(41723) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(41722) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(41721) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(41720) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) ? summaryText : singleMessageText).setTicker(tickerText).setLargeIcon(summaryAvatar).setColor(context.getResources().getColor(R.color.accent_light)).setNumber(unreadMessagesCount).setGroup(GROUP_KEY_MESSAGES).setGroupSummary(true).setWhen(timestamp).setPriority(this.preferenceService.getNotificationPriority()).setCategory(NotificationCompat.CATEGORY_MESSAGE).setOnlyAlertOnce(onlyAlertOnce);
                int smallIcon = getSmallIconResource(unreadConversationsCount);
                if (!ListenerUtil.mutListener.listen(41731)) {
                    if ((ListenerUtil.mutListener.listen(41729) ? (smallIcon >= 0) : (ListenerUtil.mutListener.listen(41728) ? (smallIcon <= 0) : (ListenerUtil.mutListener.listen(41727) ? (smallIcon < 0) : (ListenerUtil.mutListener.listen(41726) ? (smallIcon != 0) : (ListenerUtil.mutListener.listen(41725) ? (smallIcon == 0) : (smallIcon > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(41730)) {
                            builder.setSmallIcon(smallIcon);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41735)) {
                    if ((ListenerUtil.mutListener.listen(41732) ? (this.preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (this.preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId)))) {
                        if (!ListenerUtil.mutListener.listen(41733)) {
                            addConversationNotificationPreviews(builder, latestThumbnail, singleMessageText, contentTitle, conversationNotification.getMessage(), unreadMessagesCount);
                        }
                        if (!ListenerUtil.mutListener.listen(41734)) {
                            addConversationNotificationActions(builder, replyPendingIntent, ackPendingIntent, markReadPendingIntent, conversationNotification, unreadMessagesCount, unreadConversationsCount, uniqueId, newestGroup);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41743)) {
                    if ((ListenerUtil.mutListener.listen(41741) ? ((ListenerUtil.mutListener.listen(41740) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(41739) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(41738) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(41737) ? (unreadMessagesCount != 1) : (ListenerUtil.mutListener.listen(41736) ? (unreadMessagesCount == 1) : (unreadMessagesCount > 1)))))) || inboxStyle != null) : ((ListenerUtil.mutListener.listen(41740) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(41739) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(41738) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(41737) ? (unreadMessagesCount != 1) : (ListenerUtil.mutListener.listen(41736) ? (unreadMessagesCount == 1) : (unreadMessagesCount > 1)))))) && inboxStyle != null))) {
                        if (!ListenerUtil.mutListener.listen(41742)) {
                            builder.setStyle(inboxStyle);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41744)) {
                    builder.setContentIntent(openPendingIntent);
                }
                if (!ListenerUtil.mutListener.listen(41745)) {
                    notificationManagerCompat.notify(ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID, builder.build());
                }
            }
            if (!ListenerUtil.mutListener.listen(41779)) {
                logger.info("Showing notification {} sound: {}", conversationNotification.getUid(), notificationSchema.getSoundUri() != null ? notificationSchema.getSoundUri().toString() : "null");
            }
            if (!ListenerUtil.mutListener.listen(41780)) {
                showIconBadge(unreadMessagesCount);
            }
        }
    }

    private int getRandomRequestCode() {
        return (int) System.nanoTime();
    }

    private void getInboxStyle(NotificationCompat.InboxStyle inboxStyle, int unreadConversationsCount) {
        if (!ListenerUtil.mutListener.listen(41801)) {
            {
                long _loopCounter476 = 0;
                // show 8 lines max
                for (int i = 0; (ListenerUtil.mutListener.listen(41800) ? ((ListenerUtil.mutListener.listen(41794) ? (i >= this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41793) ? (i <= this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41792) ? (i > this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41791) ? (i != this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41790) ? (i == this.conversationNotifications.size()) : (i < this.conversationNotifications.size())))))) || (ListenerUtil.mutListener.listen(41799) ? (i >= 8) : (ListenerUtil.mutListener.listen(41798) ? (i <= 8) : (ListenerUtil.mutListener.listen(41797) ? (i > 8) : (ListenerUtil.mutListener.listen(41796) ? (i != 8) : (ListenerUtil.mutListener.listen(41795) ? (i == 8) : (i < 8))))))) : ((ListenerUtil.mutListener.listen(41794) ? (i >= this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41793) ? (i <= this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41792) ? (i > this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41791) ? (i != this.conversationNotifications.size()) : (ListenerUtil.mutListener.listen(41790) ? (i == this.conversationNotifications.size()) : (i < this.conversationNotifications.size())))))) && (ListenerUtil.mutListener.listen(41799) ? (i >= 8) : (ListenerUtil.mutListener.listen(41798) ? (i <= 8) : (ListenerUtil.mutListener.listen(41797) ? (i > 8) : (ListenerUtil.mutListener.listen(41796) ? (i != 8) : (ListenerUtil.mutListener.listen(41795) ? (i == 8) : (i < 8)))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter476", ++_loopCounter476);
                    CharSequence message = conversationNotifications.get(i).getMessage();
                    if (!ListenerUtil.mutListener.listen(41788)) {
                        if ((ListenerUtil.mutListener.listen(41786) ? ((ListenerUtil.mutListener.listen(41785) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(41784) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(41783) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(41782) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(41781) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) || !conversationNotifications.get(i).getGroup().getGroupUid().startsWith("g")) : ((ListenerUtil.mutListener.listen(41785) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(41784) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(41783) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(41782) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(41781) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) && !conversationNotifications.get(i).getGroup().getGroupUid().startsWith("g")))) {
                            // we need to add a name prefix manually if a contact notification is part of a grouped notifications
                            CharSequence shortName = conversationNotifications.get(i).getGroup().getShortName();
                            if (!ListenerUtil.mutListener.listen(41787)) {
                                message = TextUtils.concat(shortName, ": ", message);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(41789)) {
                        inboxStyle.addLine(message);
                    }
                }
            }
        }
    }

    private NotificationCompat.MessagingStyle getMessagingStyle(ConversationNotificationGroup group, ArrayList<ConversationNotification> notifications) {
        String chatName = group.getName();
        boolean isGroupChat = group.getMessageReceiver() instanceof GroupMessageReceiver;
        Person.Builder builder = new Person.Builder().setName(context.getString(R.string.me_myself_and_i)).setKey(contactService.getUniqueIdString(contactService.getMe()));
        Bitmap avatar = contactService.getAvatar(contactService.getMe(), false);
        if (!ListenerUtil.mutListener.listen(41803)) {
            if (avatar != null) {
                IconCompat iconCompat = IconCompat.createWithBitmap(avatar);
                if (!ListenerUtil.mutListener.listen(41802)) {
                    builder.setIcon(iconCompat);
                }
            }
        }
        Person me = builder.build();
        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(me);
        if (!ListenerUtil.mutListener.listen(41806)) {
            if (isGroupChat) {
                if (!ListenerUtil.mutListener.listen(41804)) {
                    // bug: setting a conversation title implies a group chat
                    messagingStyle.setConversationTitle(chatName);
                }
                if (!ListenerUtil.mutListener.listen(41805)) {
                    messagingStyle.setGroupConversation(isGroupChat);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41832)) {
            {
                long _loopCounter477 = 0;
                for (int i = (ListenerUtil.mutListener.listen(41823) ? (notifications.size() >= MAXIMUM_RETAINED_MESSAGES) : (ListenerUtil.mutListener.listen(41822) ? (notifications.size() <= MAXIMUM_RETAINED_MESSAGES) : (ListenerUtil.mutListener.listen(41821) ? (notifications.size() > MAXIMUM_RETAINED_MESSAGES) : (ListenerUtil.mutListener.listen(41820) ? (notifications.size() != MAXIMUM_RETAINED_MESSAGES) : (ListenerUtil.mutListener.listen(41819) ? (notifications.size() == MAXIMUM_RETAINED_MESSAGES) : (notifications.size() < MAXIMUM_RETAINED_MESSAGES)))))) ? (ListenerUtil.mutListener.listen(41831) ? (notifications.size() % 1) : (ListenerUtil.mutListener.listen(41830) ? (notifications.size() / 1) : (ListenerUtil.mutListener.listen(41829) ? (notifications.size() * 1) : (ListenerUtil.mutListener.listen(41828) ? (notifications.size() + 1) : (notifications.size() - 1))))) : (ListenerUtil.mutListener.listen(41827) ? (MAXIMUM_RETAINED_MESSAGES % 1) : (ListenerUtil.mutListener.listen(41826) ? (MAXIMUM_RETAINED_MESSAGES / 1) : (ListenerUtil.mutListener.listen(41825) ? (MAXIMUM_RETAINED_MESSAGES * 1) : (ListenerUtil.mutListener.listen(41824) ? (MAXIMUM_RETAINED_MESSAGES + 1) : (MAXIMUM_RETAINED_MESSAGES - 1))))); (ListenerUtil.mutListener.listen(41818) ? (i <= 0) : (ListenerUtil.mutListener.listen(41817) ? (i > 0) : (ListenerUtil.mutListener.listen(41816) ? (i < 0) : (ListenerUtil.mutListener.listen(41815) ? (i != 0) : (ListenerUtil.mutListener.listen(41814) ? (i == 0) : (i >= 0)))))); i--) {
                    ListenerUtil.loopListener.listen("_loopCounter477", ++_loopCounter477);
                    CharSequence message = notifications.get(i).getMessage();
                    Date date = notifications.get(i).getWhen();
                    Person person = notifications.get(i).getSenderPerson();
                    if (!ListenerUtil.mutListener.listen(41810)) {
                        // hack to show full name in non-group chats
                        if (!isGroupChat) {
                            if (!ListenerUtil.mutListener.listen(41809)) {
                                if (person == null) {
                                    if (!ListenerUtil.mutListener.listen(41808)) {
                                        person = new Person.Builder().setName(chatName).build();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(41807)) {
                                        person = person.toBuilder().setName(chatName).build();
                                    }
                                }
                            }
                        }
                    }
                    long created = 0;
                    if (!ListenerUtil.mutListener.listen(41812)) {
                        if (date != null) {
                            if (!ListenerUtil.mutListener.listen(41811)) {
                                created = date.getTime();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(41813)) {
                        messagingStyle.addMessage(message, created, person);
                    }
                }
            }
        }
        return messagingStyle;
    }

    private ArrayList<ConversationNotification> getConversationNotificationsForGroup(ConversationNotificationGroup group) {
        ArrayList<ConversationNotification> notifications = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(41835)) {
            {
                long _loopCounter478 = 0;
                for (ConversationNotification notification : conversationNotifications) {
                    ListenerUtil.loopListener.listen("_loopCounter478", ++_loopCounter478);
                    if (!ListenerUtil.mutListener.listen(41834)) {
                        if (notification.getGroup().getGroupUid().equals(group.getGroupUid())) {
                            if (!ListenerUtil.mutListener.listen(41833)) {
                                notifications.add(notification);
                            }
                        }
                    }
                }
            }
        }
        return notifications;
    }

    private void addConversationNotificationPreviews(NotificationCompat.Builder builder, Bitmap latestThumbnail, CharSequence singleMessageText, String contentTitle, CharSequence message, int unreadMessagesCount) {
        if (!ListenerUtil.mutListener.listen(41845)) {
            if ((ListenerUtil.mutListener.listen(41836) ? (latestThumbnail != null || !latestThumbnail.isRecycled()) : (latestThumbnail != null && !latestThumbnail.isRecycled()))) {
                if (!ListenerUtil.mutListener.listen(41844)) {
                    // add image preview
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(latestThumbnail).setSummaryText(message));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(41843)) {
                    // add big text preview
                    if ((ListenerUtil.mutListener.listen(41841) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(41840) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(41839) ? (unreadMessagesCount > 1) : (ListenerUtil.mutListener.listen(41838) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(41837) ? (unreadMessagesCount != 1) : (unreadMessagesCount == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(41842)) {
                            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(singleMessageText).setBigContentTitle(contentTitle));
                        }
                    }
                }
            }
        }
    }

    private void addConversationNotificationActions(NotificationCompat.Builder builder, PendingIntent replyPendingIntent, PendingIntent ackPendingIntent, PendingIntent markReadPendingIntent, ConversationNotification conversationNotification, int unreadMessagesCount, int unreadGroupsCount, String uniqueId, ConversationNotificationGroup newestGroup) {
        if (!ListenerUtil.mutListener.listen(41886)) {
            // add action buttons
            if ((ListenerUtil.mutListener.listen(41846) ? (preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId)))) {
                if (!ListenerUtil.mutListener.listen(41855)) {
                    if (ConfigUtils.canDoGroupedNotifications()) {
                        RemoteInput remoteInput = new RemoteInput.Builder(ThreemaApplication.EXTRA_VOICE_REPLY).setLabel(context.getString(R.string.compose_message_and_enter)).build();
                        NotificationCompat.Action.Builder replyActionBuilder = new NotificationCompat.Action.Builder(R.drawable.ic_reply_black_18dp, context.getString(R.string.wearable_reply), replyPendingIntent).addRemoteInput(remoteInput).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_REPLY);
                        if (!ListenerUtil.mutListener.listen(41853)) {
                            if ((ListenerUtil.mutListener.listen(41851) ? (Build.VERSION.SDK_INT <= 29) : (ListenerUtil.mutListener.listen(41850) ? (Build.VERSION.SDK_INT > 29) : (ListenerUtil.mutListener.listen(41849) ? (Build.VERSION.SDK_INT < 29) : (ListenerUtil.mutListener.listen(41848) ? (Build.VERSION.SDK_INT != 29) : (ListenerUtil.mutListener.listen(41847) ? (Build.VERSION.SDK_INT == 29) : (Build.VERSION.SDK_INT >= 29))))))) {
                                if (!ListenerUtil.mutListener.listen(41852)) {
                                    replyActionBuilder.setAllowGeneratedReplies(!preferenceService.getDisableSmartReplies());
                                }
                            }
                        }
                        NotificationCompat.Action replyAction = replyActionBuilder.build();
                        if (!ListenerUtil.mutListener.listen(41854)) {
                            builder.addAction(replyAction);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41862)) {
                    if ((ListenerUtil.mutListener.listen(41860) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(41859) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(41858) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(41857) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(41856) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                        if (!ListenerUtil.mutListener.listen(41861)) {
                            builder.addInvisibleAction(new NotificationCompat.Action.Builder(R.drawable.ic_mark_read_bitmap, context.getString(R.string.mark_read), markReadPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ).setShowsUserInterface(false).build());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41885)) {
                    if (newestGroup.getMessageReceiver() instanceof GroupMessageReceiver) {
                        if (!ListenerUtil.mutListener.listen(41884)) {
                            builder.addAction(getMarkAsReadAction(markReadPendingIntent));
                        }
                    } else if (newestGroup.getMessageReceiver() instanceof ContactMessageReceiver) {
                        if (!ListenerUtil.mutListener.listen(41883)) {
                            if (conversationNotification.getMessageType().equals(MessageType.VOIP_STATUS)) {
                                // Create an intent for the call action
                                Intent callActivityIntent = new Intent(context, CallActivity.class);
                                if (!ListenerUtil.mutListener.listen(41871)) {
                                    callActivityIntent.putExtra(EXTRA_ACTIVITY_MODE, CallActivity.MODE_OUTGOING_CALL);
                                }
                                if (!ListenerUtil.mutListener.listen(41872)) {
                                    callActivityIntent.putExtra(EXTRA_CONTACT_IDENTITY, ((ContactMessageReceiver) newestGroup.getMessageReceiver()).getContact().getIdentity());
                                }
                                if (!ListenerUtil.mutListener.listen(41873)) {
                                    callActivityIntent.putExtra(EXTRA_IS_INITIATOR, true);
                                }
                                if (!ListenerUtil.mutListener.listen(41874)) {
                                    callActivityIntent.putExtra(EXTRA_CALL_ID, -1L);
                                }
                                PendingIntent callPendingIntent = PendingIntent.getActivity(context, // http://stackoverflow.com/questions/19031861/pendingintent-not-opening-activity-in-android-4-3
                                getRandomRequestCode(), callActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                if (!ListenerUtil.mutListener.listen(41882)) {
                                    if ((ListenerUtil.mutListener.listen(41880) ? ((ListenerUtil.mutListener.listen(41879) ? (unreadGroupsCount >= 1) : (ListenerUtil.mutListener.listen(41878) ? (unreadGroupsCount <= 1) : (ListenerUtil.mutListener.listen(41877) ? (unreadGroupsCount > 1) : (ListenerUtil.mutListener.listen(41876) ? (unreadGroupsCount < 1) : (ListenerUtil.mutListener.listen(41875) ? (unreadGroupsCount != 1) : (unreadGroupsCount == 1)))))) && ConfigUtils.canDoGroupedNotifications()) : ((ListenerUtil.mutListener.listen(41879) ? (unreadGroupsCount >= 1) : (ListenerUtil.mutListener.listen(41878) ? (unreadGroupsCount <= 1) : (ListenerUtil.mutListener.listen(41877) ? (unreadGroupsCount > 1) : (ListenerUtil.mutListener.listen(41876) ? (unreadGroupsCount < 1) : (ListenerUtil.mutListener.listen(41875) ? (unreadGroupsCount != 1) : (unreadGroupsCount == 1)))))) || ConfigUtils.canDoGroupedNotifications()))) {
                                        if (!ListenerUtil.mutListener.listen(41881)) {
                                            builder.addAction(new NotificationCompat.Action.Builder(R.drawable.ic_call_white_24dp, context.getString(R.string.voip_return_call), callPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_CALL).build());
                                        }
                                    }
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(41869)) {
                                    if ((ListenerUtil.mutListener.listen(41867) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(41866) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(41865) ? (unreadMessagesCount > 1) : (ListenerUtil.mutListener.listen(41864) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(41863) ? (unreadMessagesCount != 1) : (unreadMessagesCount == 1))))))) {
                                        if (!ListenerUtil.mutListener.listen(41868)) {
                                            builder.addAction(new NotificationCompat.Action.Builder(R.drawable.ic_thumb_up_white_24dp, context.getString(R.string.acknowledge), ackPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_THUMBS_UP).build());
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(41870)) {
                                    builder.addAction(getMarkAsReadAction(markReadPendingIntent));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private NotificationCompat.Action getMarkAsReadAction(PendingIntent markReadPendingIntent) {
        return new NotificationCompat.Action.Builder(R.drawable.ic_mark_read_bitmap, context.getString(R.string.mark_read_short), markReadPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ).build();
    }

    private void createSingleNotification(ConversationNotificationGroup newestGroup, ConversationNotification newestNotification, PendingIntent openPendingIntent, PendingIntent ackPendingIntent, PendingIntent decPendingIntent, PendingIntent replyPendingIntent, PendingIntent markReadPendingIntent, long timestamp, Bitmap latestThumbnail, int numberOfUnreadMessagesForThisChat, String uniqueId) {
        CharSequence messageText;
        if (this.preferenceService.isShowMessagePreview()) {
            messageText = newestNotification.getMessage();
        } else {
            messageText = (ListenerUtil.mutListener.listen(41891) ? (this.conversationNotifications.size() >= 1) : (ListenerUtil.mutListener.listen(41890) ? (this.conversationNotifications.size() <= 1) : (ListenerUtil.mutListener.listen(41889) ? (this.conversationNotifications.size() < 1) : (ListenerUtil.mutListener.listen(41888) ? (this.conversationNotifications.size() != 1) : (ListenerUtil.mutListener.listen(41887) ? (this.conversationNotifications.size() == 1) : (this.conversationNotifications.size() > 1)))))) ? this.conversationNotifications.size() + " " + context.getString(R.string.new_messages) : context.getString(R.string.new_message);
        }
        // create a unified single notification for wearables and auto
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_notification_small).setLargeIcon(newestGroup.getAvatar()).setContentText(messageText).setWhen(timestamp).setContentTitle(newestGroup.getName()).setContentIntent(openPendingIntent).setGroup(GROUP_KEY_MESSAGES).setGroupSummary(false).setCategory(NotificationCompat.CATEGORY_MESSAGE);
        if (!ListenerUtil.mutListener.listen(41892)) {
            addWearableExtender(builder, newestGroup, ackPendingIntent, decPendingIntent, replyPendingIntent, markReadPendingIntent, timestamp, latestThumbnail, numberOfUnreadMessagesForThisChat, messageText, uniqueId);
        }
        if (!ListenerUtil.mutListener.listen(41893)) {
            notificationManagerCompat.notify(newestGroup.getNotificationId(), builder.build());
        }
    }

    private void addWearableExtender(NotificationCompat.Builder builder, ConversationNotificationGroup newestGroup, PendingIntent ackPendingIntent, PendingIntent decPendingIntent, PendingIntent replyPendingIntent, PendingIntent markReadPendingIntent, long timestamp, Bitmap latestThumbnail, int numberOfUnreadMessagesForThisChat, CharSequence messageText, String uniqueId) {
        String replyLabel = String.format(context.getString(R.string.wearable_reply_label), newestGroup.getName());
        RemoteInput remoteInput = new RemoteInput.Builder(ThreemaApplication.EXTRA_VOICE_REPLY).setLabel(replyLabel).setChoices(context.getResources().getStringArray(R.array.wearable_reply_choices)).build();
        int numMessages = this.conversationNotifications.size();
        List<Notification> wearablePages = new ArrayList<>();
        // from a particular sender.
        NotificationCompat.CarExtender.UnreadConversation.Builder unreadConvBuilder = new NotificationCompat.CarExtender.UnreadConversation.Builder(newestGroup.getName()).setReadPendingIntent(markReadPendingIntent).setReplyAction(replyPendingIntent, remoteInput).setLatestTimestamp(timestamp);
        if (!ListenerUtil.mutListener.listen(41927)) {
            if ((ListenerUtil.mutListener.listen(41894) ? (preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId)))) {
                String wearableSummaryText = "";
                if (!ListenerUtil.mutListener.listen(41915)) {
                    {
                        long _loopCounter479 = 0;
                        // Note: Add messages from oldest to newest to the UnreadConversation.Builder
                        for (int i = (ListenerUtil.mutListener.listen(41914) ? (numMessages % 1) : (ListenerUtil.mutListener.listen(41913) ? (numMessages / 1) : (ListenerUtil.mutListener.listen(41912) ? (numMessages * 1) : (ListenerUtil.mutListener.listen(41911) ? (numMessages + 1) : (numMessages - 1))))); (ListenerUtil.mutListener.listen(41910) ? (i <= 0) : (ListenerUtil.mutListener.listen(41909) ? (i > 0) : (ListenerUtil.mutListener.listen(41908) ? (i < 0) : (ListenerUtil.mutListener.listen(41907) ? (i != 0) : (ListenerUtil.mutListener.listen(41906) ? (i == 0) : (i >= 0)))))); i--) {
                            ListenerUtil.loopListener.listen("_loopCounter479", ++_loopCounter479);
                            if (!ListenerUtil.mutListener.listen(41905)) {
                                if (conversationNotifications.get(i).getGroup() == newestGroup) {
                                    CharSequence message = conversationNotifications.get(i).getMessage();
                                    if (!ListenerUtil.mutListener.listen(41896)) {
                                        // auto
                                        unreadConvBuilder.addMessage(message.toString());
                                    }
                                    if (!ListenerUtil.mutListener.listen(41903)) {
                                        // wearable
                                        if ((ListenerUtil.mutListener.listen(41901) ? (wearableSummaryText.length() >= 0) : (ListenerUtil.mutListener.listen(41900) ? (wearableSummaryText.length() <= 0) : (ListenerUtil.mutListener.listen(41899) ? (wearableSummaryText.length() < 0) : (ListenerUtil.mutListener.listen(41898) ? (wearableSummaryText.length() != 0) : (ListenerUtil.mutListener.listen(41897) ? (wearableSummaryText.length() == 0) : (wearableSummaryText.length() > 0))))))) {
                                            if (!ListenerUtil.mutListener.listen(41902)) {
                                                wearableSummaryText += "\n\n";
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(41904)) {
                                        wearableSummaryText += message;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41918)) {
                    // add pic if image message
                    if ((ListenerUtil.mutListener.listen(41916) ? (latestThumbnail != null || !latestThumbnail.isRecycled()) : (latestThumbnail != null && !latestThumbnail.isRecycled()))) {
                        NotificationCompat.Builder wearableBuilder = new NotificationCompat.Builder(context).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(latestThumbnail)).extend(new NotificationCompat.WearableExtender().setHintShowBackgroundOnly(true));
                        if (!ListenerUtil.mutListener.listen(41917)) {
                            wearablePages.add(wearableBuilder.build());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(41926)) {
                    if ((ListenerUtil.mutListener.listen(41923) ? (wearableSummaryText.length() >= 0) : (ListenerUtil.mutListener.listen(41922) ? (wearableSummaryText.length() <= 0) : (ListenerUtil.mutListener.listen(41921) ? (wearableSummaryText.length() < 0) : (ListenerUtil.mutListener.listen(41920) ? (wearableSummaryText.length() != 0) : (ListenerUtil.mutListener.listen(41919) ? (wearableSummaryText.length() == 0) : (wearableSummaryText.length() > 0))))))) {
                        NotificationCompat.BigTextStyle wearablePageStyle = new NotificationCompat.BigTextStyle();
                        if (!ListenerUtil.mutListener.listen(41924)) {
                            wearablePageStyle.setBigContentTitle(newestGroup.getName()).bigText(wearableSummaryText);
                        }
                        NotificationCompat.Builder wearableBuilder = new NotificationCompat.Builder(context).setStyle(wearablePageStyle);
                        if (!ListenerUtil.mutListener.listen(41925)) {
                            wearablePages.add(wearableBuilder.build());
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(41895)) {
                    unreadConvBuilder.addMessage(messageText.toString());
                }
            }
        }
        NotificationCompat.Action.Builder replyActionBuilder = new NotificationCompat.Action.Builder(R.drawable.ic_wear_full_reply, context.getString(R.string.wearable_reply), replyPendingIntent).addRemoteInput(remoteInput).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_REPLY);
        NotificationCompat.Action.WearableExtender replyActionExtender = new NotificationCompat.Action.WearableExtender().setHintDisplayActionInline(true);
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender().addPages(wearablePages).addAction(replyActionBuilder.extend(replyActionExtender).build());
        if (!ListenerUtil.mutListener.listen(41940)) {
            if ((ListenerUtil.mutListener.listen(41928) ? (this.preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (this.preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId)))) {
                if (!ListenerUtil.mutListener.listen(41938)) {
                    if ((ListenerUtil.mutListener.listen(41935) ? ((ListenerUtil.mutListener.listen(41934) ? ((ListenerUtil.mutListener.listen(41933) ? (numberOfUnreadMessagesForThisChat >= 1) : (ListenerUtil.mutListener.listen(41932) ? (numberOfUnreadMessagesForThisChat <= 1) : (ListenerUtil.mutListener.listen(41931) ? (numberOfUnreadMessagesForThisChat > 1) : (ListenerUtil.mutListener.listen(41930) ? (numberOfUnreadMessagesForThisChat < 1) : (ListenerUtil.mutListener.listen(41929) ? (numberOfUnreadMessagesForThisChat != 1) : (numberOfUnreadMessagesForThisChat == 1)))))) || newestGroup.getMessageReceiver() instanceof ContactMessageReceiver) : ((ListenerUtil.mutListener.listen(41933) ? (numberOfUnreadMessagesForThisChat >= 1) : (ListenerUtil.mutListener.listen(41932) ? (numberOfUnreadMessagesForThisChat <= 1) : (ListenerUtil.mutListener.listen(41931) ? (numberOfUnreadMessagesForThisChat > 1) : (ListenerUtil.mutListener.listen(41930) ? (numberOfUnreadMessagesForThisChat < 1) : (ListenerUtil.mutListener.listen(41929) ? (numberOfUnreadMessagesForThisChat != 1) : (numberOfUnreadMessagesForThisChat == 1)))))) && newestGroup.getMessageReceiver() instanceof ContactMessageReceiver)) || !hiddenChatsListService.has(uniqueId)) : ((ListenerUtil.mutListener.listen(41934) ? ((ListenerUtil.mutListener.listen(41933) ? (numberOfUnreadMessagesForThisChat >= 1) : (ListenerUtil.mutListener.listen(41932) ? (numberOfUnreadMessagesForThisChat <= 1) : (ListenerUtil.mutListener.listen(41931) ? (numberOfUnreadMessagesForThisChat > 1) : (ListenerUtil.mutListener.listen(41930) ? (numberOfUnreadMessagesForThisChat < 1) : (ListenerUtil.mutListener.listen(41929) ? (numberOfUnreadMessagesForThisChat != 1) : (numberOfUnreadMessagesForThisChat == 1)))))) || newestGroup.getMessageReceiver() instanceof ContactMessageReceiver) : ((ListenerUtil.mutListener.listen(41933) ? (numberOfUnreadMessagesForThisChat >= 1) : (ListenerUtil.mutListener.listen(41932) ? (numberOfUnreadMessagesForThisChat <= 1) : (ListenerUtil.mutListener.listen(41931) ? (numberOfUnreadMessagesForThisChat > 1) : (ListenerUtil.mutListener.listen(41930) ? (numberOfUnreadMessagesForThisChat < 1) : (ListenerUtil.mutListener.listen(41929) ? (numberOfUnreadMessagesForThisChat != 1) : (numberOfUnreadMessagesForThisChat == 1)))))) && newestGroup.getMessageReceiver() instanceof ContactMessageReceiver)) && !hiddenChatsListService.has(uniqueId)))) {
                        NotificationCompat.Action ackAction = new NotificationCompat.Action.Builder(R.drawable.ic_wear_full_ack, context.getString(R.string.acknowledge), ackPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_THUMBS_UP).build();
                        if (!ListenerUtil.mutListener.listen(41936)) {
                            wearableExtender.addAction(ackAction);
                        }
                        NotificationCompat.Action decAction = new NotificationCompat.Action.Builder(R.drawable.ic_wear_full_decline, context.getString(R.string.decline), decPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_THUMBS_DOWN).build();
                        if (!ListenerUtil.mutListener.listen(41937)) {
                            wearableExtender.addAction(decAction);
                        }
                    }
                }
                NotificationCompat.Action markReadAction = new NotificationCompat.Action.Builder(R.drawable.ic_mark_read, context.getString(R.string.mark_read), markReadPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ).build();
                if (!ListenerUtil.mutListener.listen(41939)) {
                    wearableExtender.addAction(markReadAction);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41941)) {
            builder.extend(wearableExtender);
        }
        if (!ListenerUtil.mutListener.listen(41942)) {
            builder.extend(new NotificationCompat.CarExtender().setLargeIcon(newestGroup.getAvatar()).setUnreadConversation(unreadConvBuilder.build()).setColor(context.getResources().getColor(R.color.accent_light)));
        }
    }

    public void cancelConversationNotificationsOnLockApp() {
        if (!ListenerUtil.mutListener.listen(41953)) {
            // cancel cached notification ids if still available
            if (!conversationNotifications.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(41952)) {
                    cancelCachedConversationNotifications();
                }
            } else // get and cancel active conversations notifications trough notificationManager
            if ((ListenerUtil.mutListener.listen(41948) ? ((ListenerUtil.mutListener.listen(41947) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41946) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41945) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41944) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41943) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || cancelAllMessageCategoryNotifications()) : ((ListenerUtil.mutListener.listen(41947) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41946) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41945) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41944) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(41943) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && cancelAllMessageCategoryNotifications()))) {
                if (!ListenerUtil.mutListener.listen(41951)) {
                    showDefaultPinLockedNewMessageNotification();
                }
            } else // hack to detect active conversation Notifications by checking for active pending Intent
            if (isConversationNotificationVisible()) {
                if (!ListenerUtil.mutListener.listen(41949)) {
                    cancel(ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID);
                }
                if (!ListenerUtil.mutListener.listen(41950)) {
                    showDefaultPinLockedNewMessageNotification();
                }
            }
        }
    }

    @Override
    public void cancelConversationNotification(final String... uids) {
        synchronized (this.conversationNotifications) {
            if (!ListenerUtil.mutListener.listen(41959)) {
                {
                    long _loopCounter480 = 0;
                    for (final String uid : uids) {
                        ListenerUtil.loopListener.listen("_loopCounter480", ++_loopCounter480);
                        ConversationNotification conversationNotification = Functional.select(this.conversationNotifications, new IPredicateNonNull<ConversationNotification>() {

                            @Override
                            public boolean apply(@NonNull ConversationNotification conversationNotification1) {
                                return TestUtil.compare(conversationNotification1.getUid(), uid);
                            }
                        });
                        if (!ListenerUtil.mutListener.listen(41958)) {
                            if (conversationNotification != null) {
                                if (!ListenerUtil.mutListener.listen(41954)) {
                                    conversationNotifications.remove(conversationNotification);
                                }
                                if (!ListenerUtil.mutListener.listen(41955)) {
                                    cancelAndDestroyConversationNotification(conversationNotification);
                                }
                                if (!ListenerUtil.mutListener.listen(41957)) {
                                    if (ConfigUtils.canDoGroupedNotifications()) {
                                        if (!ListenerUtil.mutListener.listen(41956)) {
                                            notificationManagerCompat.cancel(conversationNotification.getGroup().getNotificationId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(41962)) {
                if (!ConfigUtils.canDoGroupedNotifications()) {
                    if (!ListenerUtil.mutListener.listen(41961)) {
                        this.updateConversationNotifications();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(41960)) {
                        showIconBadge(this.conversationNotifications.size());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(41964)) {
                // no unread conversations left. make sure PIN locked notification is canceled as well
                if (this.conversationNotifications.size() == 0) {
                    if (!ListenerUtil.mutListener.listen(41963)) {
                        cancelPinLockedNewMessagesNotification();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41965)) {
            WidgetUtil.updateWidgets(context);
        }
    }

    private void cancelAndDestroyConversationNotification(ConversationNotification conversationNotification) {
        if (!ListenerUtil.mutListener.listen(41968)) {
            if (conversationNotification != null) {
                if (!ListenerUtil.mutListener.listen(41966)) {
                    // remove wearable
                    cancel(conversationNotification.getGroup().getNotificationId());
                }
                if (!ListenerUtil.mutListener.listen(41967)) {
                    conversationNotification.destroy();
                }
            }
        }
    }

    @Override
    public void updateConversationNotifications() {
        int unreadMessagesCount = 0;
        if (!ListenerUtil.mutListener.listen(42011)) {
            if (!ConfigUtils.canDoGroupedNotifications()) {
                if (!ListenerUtil.mutListener.listen(42010)) {
                    updateConversationNotificationsPreN();
                }
            } else {
                synchronized (this.conversationNotifications) {
                    if (!ListenerUtil.mutListener.listen(41969)) {
                        cancelPinLockedNewMessagesNotification();
                    }
                    // check if more than one group in the notification
                    ConversationNotification newestNotification = null;
                    HashSet<ConversationNotificationGroup> uniqueNotificationGroups = new HashSet<>();
                    if (!ListenerUtil.mutListener.listen(42008)) {
                        if (this.conversationNotifications.size() != 0) {
                            if (!ListenerUtil.mutListener.listen(41973)) {
                                {
                                    long _loopCounter481 = 0;
                                    for (ConversationNotification notification : this.conversationNotifications) {
                                        ListenerUtil.loopListener.listen("_loopCounter481", ++_loopCounter481);
                                        ConversationNotificationGroup group = notification.getGroup();
                                        if (!ListenerUtil.mutListener.listen(41971)) {
                                            newestNotification = notification;
                                        }
                                        if (!ListenerUtil.mutListener.listen(41972)) {
                                            uniqueNotificationGroups.add(group);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(41975)) {
                                if (newestNotification == null) {
                                    if (!ListenerUtil.mutListener.listen(41974)) {
                                        logger.info("Aborting notification update");
                                    }
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(41976)) {
                                unreadMessagesCount = this.conversationNotifications.size();
                            }
                            if (!ListenerUtil.mutListener.listen(42007)) {
                                if (!this.lockAppService.isLocked()) {
                                    NotificationCompat.Builder builder;
                                    String summaryText;
                                    CharSequence singleMessageText;
                                    {
                                        long _loopCounter482 = 0;
                                        for (ConversationNotificationGroup group : uniqueNotificationGroups) {
                                            ListenerUtil.loopListener.listen("_loopCounter482", ++_loopCounter482);
                                            ArrayList<ConversationNotification> notifications = getConversationNotificationsForGroup(group);
                                            if ((ListenerUtil.mutListener.listen(41981) ? (notifications.size() >= 0) : (ListenerUtil.mutListener.listen(41980) ? (notifications.size() <= 0) : (ListenerUtil.mutListener.listen(41979) ? (notifications.size() < 0) : (ListenerUtil.mutListener.listen(41978) ? (notifications.size() != 0) : (ListenerUtil.mutListener.listen(41977) ? (notifications.size() == 0) : (notifications.size() > 0))))))) {
                                                ConversationNotification mostRecentNotification = notifications.get((ListenerUtil.mutListener.listen(41985) ? (notifications.size() % 1) : (ListenerUtil.mutListener.listen(41984) ? (notifications.size() / 1) : (ListenerUtil.mutListener.listen(41983) ? (notifications.size() * 1) : (ListenerUtil.mutListener.listen(41982) ? (notifications.size() + 1) : (notifications.size() - 1))))));
                                                String uniqueId = group.getMessageReceiver().getUniqueIdString();
                                                summaryText = (ListenerUtil.mutListener.listen(41990) ? (notifications.size() >= 1) : (ListenerUtil.mutListener.listen(41989) ? (notifications.size() <= 1) : (ListenerUtil.mutListener.listen(41988) ? (notifications.size() < 1) : (ListenerUtil.mutListener.listen(41987) ? (notifications.size() != 1) : (ListenerUtil.mutListener.listen(41986) ? (notifications.size() == 1) : (notifications.size() > 1)))))) ? notifications.size() + " " + context.getString(R.string.new_messages) : context.getString(R.string.new_message);
                                                if ((ListenerUtil.mutListener.listen(41991) ? (this.preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (this.preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId)))) {
                                                    singleMessageText = mostRecentNotification.getMessage();
                                                } else {
                                                    singleMessageText = summaryText;
                                                }
                                                NotificationCompat.Builder publicBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_CHAT_UPDATE).setContentTitle(summaryText).setContentText(context.getString(R.string.notification_hidden_text)).setSmallIcon(R.drawable.ic_notification_small).setColor(context.getResources().getColor(R.color.accent_light));
                                                builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_CHAT_UPDATE, null, publicBuilder).setContentTitle(group.getName()).setContentText(singleMessageText).setSmallIcon(R.drawable.ic_notification_small).setLargeIcon(group.getAvatar()).setColor(context.getResources().getColor(R.color.accent_light)).setGroup(group.getGroupUid()).setGroupSummary(false).setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                                                if (!ListenerUtil.mutListener.listen(42000)) {
                                                    if ((ListenerUtil.mutListener.listen(41998) ? ((ListenerUtil.mutListener.listen(41992) ? (this.preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (this.preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId))) || (ListenerUtil.mutListener.listen(41997) ? (notifications.size() >= 1) : (ListenerUtil.mutListener.listen(41996) ? (notifications.size() <= 1) : (ListenerUtil.mutListener.listen(41995) ? (notifications.size() < 1) : (ListenerUtil.mutListener.listen(41994) ? (notifications.size() != 1) : (ListenerUtil.mutListener.listen(41993) ? (notifications.size() == 1) : (notifications.size() > 1))))))) : ((ListenerUtil.mutListener.listen(41992) ? (this.preferenceService.isShowMessagePreview() || !hiddenChatsListService.has(uniqueId)) : (this.preferenceService.isShowMessagePreview() && !hiddenChatsListService.has(uniqueId))) && (ListenerUtil.mutListener.listen(41997) ? (notifications.size() >= 1) : (ListenerUtil.mutListener.listen(41996) ? (notifications.size() <= 1) : (ListenerUtil.mutListener.listen(41995) ? (notifications.size() < 1) : (ListenerUtil.mutListener.listen(41994) ? (notifications.size() != 1) : (ListenerUtil.mutListener.listen(41993) ? (notifications.size() == 1) : (notifications.size() > 1))))))))) {
                                                        if (!ListenerUtil.mutListener.listen(41999)) {
                                                            builder.setStyle(getMessagingStyle(group, notifications));
                                                        }
                                                    }
                                                }
                                                Intent notificationIntent = new Intent(context, ComposeMessageActivity.class);
                                                if (!ListenerUtil.mutListener.listen(42001)) {
                                                    group.getMessageReceiver().prepareIntent(notificationIntent);
                                                }
                                                if (!ListenerUtil.mutListener.listen(42002)) {
                                                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                }
                                                PendingIntent pendingIntent = createPendingIntentWithTaskStack(notificationIntent);
                                                if (!ListenerUtil.mutListener.listen(42003)) {
                                                    builder.setContentIntent(pendingIntent);
                                                }
                                                try {
                                                    if (!ListenerUtil.mutListener.listen(42005)) {
                                                        notificationManagerCompat.notify(group.getNotificationId(), builder.build());
                                                    }
                                                } catch (RuntimeException e) {
                                                    if (!ListenerUtil.mutListener.listen(42004)) {
                                                        logger.error("Exception", e);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(42006)) {
                                        logger.info("Updating notification {}", newestNotification.getUid());
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(41970)) {
                                this.cancelAllCachedConversationNotifications();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(42009)) {
                        showIconBadge(unreadMessagesCount);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42012)) {
            // update widgets
            WidgetUtil.updateWidgets(context);
        }
    }

    private void updateConversationNotificationsPreN() {
        int unreadMessagesCount = 0;
        synchronized (this.conversationNotifications) {
            if (!ListenerUtil.mutListener.listen(42013)) {
                cancelPinLockedNewMessagesNotification();
            }
            // check if more than one group in the notification
            ConversationNotificationGroup newestGroup = null;
            ConversationNotification newestNotification = null;
            Map<String, ConversationNotificationGroup> uniqueNotificationGroups = new HashMap<>();
            if (!ListenerUtil.mutListener.listen(42069)) {
                if (this.conversationNotifications.size() != 0) {
                    if (!ListenerUtil.mutListener.listen(42018)) {
                        {
                            long _loopCounter483 = 0;
                            for (ConversationNotification notification : this.conversationNotifications) {
                                ListenerUtil.loopListener.listen("_loopCounter483", ++_loopCounter483);
                                ConversationNotificationGroup group = notification.getGroup();
                                if (!ListenerUtil.mutListener.listen(42015)) {
                                    newestGroup = group;
                                }
                                if (!ListenerUtil.mutListener.listen(42016)) {
                                    newestNotification = notification;
                                }
                                if (!ListenerUtil.mutListener.listen(42017)) {
                                    uniqueNotificationGroups.put(group.getGroupUid(), group);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(42021)) {
                        if (newestNotification == null) {
                            if (!ListenerUtil.mutListener.listen(42019)) {
                                logger.info("Aborting notification update");
                            }
                            if (!ListenerUtil.mutListener.listen(42020)) {
                                showIconBadge(0);
                            }
                            return;
                        }
                    }
                    final String latestFullName = newestGroup.getName();
                    if (!ListenerUtil.mutListener.listen(42022)) {
                        unreadMessagesCount = this.conversationNotifications.size();
                    }
                    int unreadConversationsCount = uniqueNotificationGroups.size();
                    if (!ListenerUtil.mutListener.listen(42068)) {
                        if (!this.lockAppService.isLocked()) {
                            String summaryText = (ListenerUtil.mutListener.listen(42027) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(42026) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(42025) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(42024) ? (unreadMessagesCount != 1) : (ListenerUtil.mutListener.listen(42023) ? (unreadMessagesCount == 1) : (unreadMessagesCount > 1)))))) ? ((ListenerUtil.mutListener.listen(42032) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(42031) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(42030) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(42029) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(42028) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) ? String.format(context.getString(R.string.new_messages_in_chats), unreadMessagesCount, unreadConversationsCount) : unreadMessagesCount + " " + context.getString(R.string.new_messages)) : context.getString(R.string.new_message);
                            CharSequence singleMessageText;
                            String contentTitle;
                            Intent notificationIntent;
                            Bitmap avatar;
                            NotificationCompat.InboxStyle inboxStyle = null;
                            /* set avatar, intent and contentTitle */
                            if ((ListenerUtil.mutListener.listen(42037) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(42036) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(42035) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(42034) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(42033) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1))))))) {
                                // HACK
                                avatar = getConversationNotificationAvatar();
                                notificationIntent = new Intent(context, HomeActivity.class);
                                contentTitle = context.getString(R.string.app_name);
                            } else {
                                /* notification is for single chat */
                                avatar = newestGroup.getAvatar();
                                notificationIntent = new Intent(context, ComposeMessageActivity.class);
                                if (!ListenerUtil.mutListener.listen(42038)) {
                                    newestGroup.getMessageReceiver().prepareIntent(notificationIntent);
                                }
                                contentTitle = latestFullName;
                            }
                            if (!ListenerUtil.mutListener.listen(42039)) {
                                /* fix for <4.1 - keeps android from re-using existing intent and stripping extras */
                                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            }
                            if (this.preferenceService.isShowMessagePreview()) {
                                if (!ListenerUtil.mutListener.listen(42040)) {
                                    inboxStyle = new NotificationCompat.InboxStyle();
                                }
                                if (!ListenerUtil.mutListener.listen(42041)) {
                                    getInboxStyle(inboxStyle, unreadConversationsCount);
                                }
                                if (!ListenerUtil.mutListener.listen(42042)) {
                                    inboxStyle.setBigContentTitle(contentTitle);
                                }
                                if (!ListenerUtil.mutListener.listen(42043)) {
                                    inboxStyle.setSummaryText(summaryText);
                                }
                                singleMessageText = newestNotification.getMessage();
                            } else {
                                singleMessageText = summaryText;
                            }
                            PendingIntent pendingIntent = createPendingIntentWithTaskStack(notificationIntent);
                            // update summary notification
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentTitle(contentTitle).setContentText((ListenerUtil.mutListener.listen(42048) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(42047) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(42046) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(42045) ? (unreadMessagesCount != 1) : (ListenerUtil.mutListener.listen(42044) ? (unreadMessagesCount == 1) : (unreadMessagesCount > 1)))))) ? summaryText : singleMessageText).setLargeIcon(avatar).setColor(context.getResources().getColor(R.color.accent_light)).setNumber(unreadMessagesCount).setGroup(GROUP_KEY_MESSAGES).setOnlyAlertOnce(false);
                            int smallIcon = getSmallIconResource(unreadConversationsCount);
                            if (!ListenerUtil.mutListener.listen(42055)) {
                                if ((ListenerUtil.mutListener.listen(42053) ? (smallIcon >= 0) : (ListenerUtil.mutListener.listen(42052) ? (smallIcon <= 0) : (ListenerUtil.mutListener.listen(42051) ? (smallIcon < 0) : (ListenerUtil.mutListener.listen(42050) ? (smallIcon != 0) : (ListenerUtil.mutListener.listen(42049) ? (smallIcon == 0) : (smallIcon > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(42054)) {
                                        builder.setSmallIcon(smallIcon);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(42056)) {
                                // https://code.google.com/p/android/issues/detail?id=219876
                                builder.setGroupSummary(true);
                            }
                            if (!ListenerUtil.mutListener.listen(42064)) {
                                if ((ListenerUtil.mutListener.listen(42062) ? ((ListenerUtil.mutListener.listen(42061) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(42060) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(42059) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(42058) ? (unreadMessagesCount != 1) : (ListenerUtil.mutListener.listen(42057) ? (unreadMessagesCount == 1) : (unreadMessagesCount > 1)))))) || inboxStyle != null) : ((ListenerUtil.mutListener.listen(42061) ? (unreadMessagesCount >= 1) : (ListenerUtil.mutListener.listen(42060) ? (unreadMessagesCount <= 1) : (ListenerUtil.mutListener.listen(42059) ? (unreadMessagesCount < 1) : (ListenerUtil.mutListener.listen(42058) ? (unreadMessagesCount != 1) : (ListenerUtil.mutListener.listen(42057) ? (unreadMessagesCount == 1) : (unreadMessagesCount > 1)))))) && inboxStyle != null))) {
                                    if (!ListenerUtil.mutListener.listen(42063)) {
                                        builder.setStyle(inboxStyle);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(42065)) {
                                builder.setContentIntent(pendingIntent);
                            }
                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                            if (!ListenerUtil.mutListener.listen(42066)) {
                                notificationManager.notify(ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID, builder.build());
                            }
                            if (!ListenerUtil.mutListener.listen(42067)) {
                                logger.info("Updating notification {}", newestNotification.getUid());
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(42014)) {
                        // cancel all
                        this.cancel(ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(42070)) {
                // update widgets
                showIconBadge(unreadMessagesCount);
            }
        }
    }

    @Override
    public void cancelAllCachedConversationNotifications() {
        if (!ListenerUtil.mutListener.listen(42071)) {
            this.cancel(ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID);
        }
        synchronized (this.conversationNotifications) {
            if (!ListenerUtil.mutListener.listen(42076)) {
                if (!conversationNotifications.isEmpty()) {
                    if (!ListenerUtil.mutListener.listen(42074)) {
                        {
                            long _loopCounter484 = 0;
                            for (ConversationNotification conversationNotification : conversationNotifications) {
                                ListenerUtil.loopListener.listen("_loopCounter484", ++_loopCounter484);
                                if (!ListenerUtil.mutListener.listen(42072)) {
                                    this.conversationNotifications.remove(conversationNotification);
                                }
                                if (!ListenerUtil.mutListener.listen(42073)) {
                                    this.cancelAndDestroyConversationNotification(conversationNotification);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(42075)) {
                        showDefaultPinLockedNewMessageNotification();
                    }
                }
            }
        }
    }

    private NotificationSchema createNotificationSchema(ConversationNotificationGroup notificationGroup, CharSequence rawMessage) {
        NotificationSchemaImpl notificationSchema = new NotificationSchemaImpl(this.context);
        MessageReceiver r = notificationGroup.getMessageReceiver();
        if (!ListenerUtil.mutListener.listen(42081)) {
            if (r instanceof GroupMessageReceiver) {
                if (!ListenerUtil.mutListener.listen(42079)) {
                    if (DNDUtil.getInstance().isMuted(r, rawMessage)) {
                        return null;
                    }
                }
                if (!ListenerUtil.mutListener.listen(42080)) {
                    notificationSchema.setSoundUri(this.ringtoneService.getGroupRingtone(r.getUniqueIdString())).setColor(getColorValue(this.preferenceService.getGroupNotificationLight())).setVibrate(this.preferenceService.isGroupVibrate());
                }
            } else if (r instanceof ContactMessageReceiver) {
                if (!ListenerUtil.mutListener.listen(42077)) {
                    if (DNDUtil.getInstance().isMuted(r, null)) {
                        return null;
                    }
                }
                if (!ListenerUtil.mutListener.listen(42078)) {
                    notificationSchema.setSoundUri(this.ringtoneService.getContactRingtone(r.getUniqueIdString())).setColor(getColorValue(this.preferenceService.getNotificationLight())).setVibrate(this.preferenceService.isVibrate());
                }
            }
        }
        return notificationSchema;
    }

    private Bitmap getConversationNotificationAvatar() {
        /* notification is for more than one chat */
        if ((ListenerUtil.mutListener.listen(42086) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(42085) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(42084) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(42083) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(42082) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notification_multi_color);
        } else {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notification_multi);
        }
    }

    private int getSmallIconResource(int unreadConversationsCount) {
        return (ListenerUtil.mutListener.listen(42091) ? (unreadConversationsCount >= 1) : (ListenerUtil.mutListener.listen(42090) ? (unreadConversationsCount <= 1) : (ListenerUtil.mutListener.listen(42089) ? (unreadConversationsCount < 1) : (ListenerUtil.mutListener.listen(42088) ? (unreadConversationsCount != 1) : (ListenerUtil.mutListener.listen(42087) ? (unreadConversationsCount == 1) : (unreadConversationsCount > 1)))))) ? R.drawable.ic_notification_multi : R.drawable.ic_notification_small;
    }

    @Override
    public void cancel(ConversationModel conversationModel) {
        if (!ListenerUtil.mutListener.listen(42093)) {
            if (conversationModel != null) {
                if (!ListenerUtil.mutListener.listen(42092)) {
                    this.cancel(conversationModel.getReceiver());
                }
            }
        }
    }

    @Override
    public void cancel(final MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(42107)) {
            if (receiver != null) {
                int id = receiver.getUniqueId();
                if (!ListenerUtil.mutListener.listen(42100)) {
                    if ((ListenerUtil.mutListener.listen(42098) ? (id >= 0) : (ListenerUtil.mutListener.listen(42097) ? (id <= 0) : (ListenerUtil.mutListener.listen(42096) ? (id > 0) : (ListenerUtil.mutListener.listen(42095) ? (id < 0) : (ListenerUtil.mutListener.listen(42094) ? (id == 0) : (id != 0))))))) {
                        if (!ListenerUtil.mutListener.listen(42099)) {
                            this.cancel(id);
                        }
                    }
                }
                // remove all cached notifications from the receiver
                synchronized (this.conversationNotifications) {
                    if (!ListenerUtil.mutListener.listen(42106)) {
                        {
                            long _loopCounter485 = 0;
                            for (Iterator<ConversationNotification> iterator = this.conversationNotifications.iterator(); iterator.hasNext(); ) {
                                ListenerUtil.loopListener.listen("_loopCounter485", ++_loopCounter485);
                                ConversationNotification conversationNotification = iterator.next();
                                if (!ListenerUtil.mutListener.listen(42105)) {
                                    if ((ListenerUtil.mutListener.listen(42102) ? ((ListenerUtil.mutListener.listen(42101) ? (conversationNotification != null || conversationNotification.getGroup() != null) : (conversationNotification != null && conversationNotification.getGroup() != null)) || conversationNotification.getGroup().getMessageReceiver().isEqual(receiver)) : ((ListenerUtil.mutListener.listen(42101) ? (conversationNotification != null || conversationNotification.getGroup() != null) : (conversationNotification != null && conversationNotification.getGroup() != null)) && conversationNotification.getGroup().getMessageReceiver().isEqual(receiver)))) {
                                        if (!ListenerUtil.mutListener.listen(42103)) {
                                            iterator.remove();
                                        }
                                        if (!ListenerUtil.mutListener.listen(42104)) {
                                            // call destroy
                                            this.cancelAndDestroyConversationNotification(conversationNotification);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42108)) {
            this.cancel(ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID);
        }
    }

    @Override
    public void cancelCachedConversationNotifications() {
        /* called when pin lock becomes active */
        synchronized (this.conversationNotifications) {
            if (!ListenerUtil.mutListener.listen(42109)) {
                cancelAllCachedConversationNotifications();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean cancelAllMessageCategoryNotifications() {
        boolean cancelledIDs = false;
        try {
            if (!ListenerUtil.mutListener.listen(42122)) {
                if (notificationManager != null) {
                    StatusBarNotification[] notifications = notificationManager.getActiveNotifications();
                    if (!ListenerUtil.mutListener.listen(42121)) {
                        if ((ListenerUtil.mutListener.listen(42115) ? (notifications.length >= 0) : (ListenerUtil.mutListener.listen(42114) ? (notifications.length <= 0) : (ListenerUtil.mutListener.listen(42113) ? (notifications.length < 0) : (ListenerUtil.mutListener.listen(42112) ? (notifications.length != 0) : (ListenerUtil.mutListener.listen(42111) ? (notifications.length == 0) : (notifications.length > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(42120)) {
                                {
                                    long _loopCounter486 = 0;
                                    for (StatusBarNotification notification : notifications) {
                                        ListenerUtil.loopListener.listen("_loopCounter486", ++_loopCounter486);
                                        if (!ListenerUtil.mutListener.listen(42119)) {
                                            if ((ListenerUtil.mutListener.listen(42116) ? (notification.getNotification() != null || Notification.CATEGORY_MESSAGE.equals(notification.getNotification().category)) : (notification.getNotification() != null && Notification.CATEGORY_MESSAGE.equals(notification.getNotification().category)))) {
                                                if (!ListenerUtil.mutListener.listen(42117)) {
                                                    notificationManager.cancel(notification.getId());
                                                }
                                                if (!ListenerUtil.mutListener.listen(42118)) {
                                                    cancelledIDs = true;
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
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42110)) {
                logger.error("Could not cancel notifications of CATEGORY_MESSAGE ", e);
            }
        }
        return cancelledIDs;
    }

    private NotificationSchema getDefaultNotificationSchema() {
        NotificationSchemaImpl notificationSchema = new NotificationSchemaImpl(this.context);
        if (!ListenerUtil.mutListener.listen(42123)) {
            notificationSchema.setVibrate(this.preferenceService.isVibrate()).setColor(this.getColorValue(preferenceService.getNotificationLight())).setSoundUri(this.preferenceService.getNotificationSound());
        }
        return notificationSchema;
    }

    public boolean isConversationNotificationVisible() {
        Intent notificationIntent = new Intent(context, ComposeMessageActivity.class);
        PendingIntent test = PendingIntent.getActivity(context, ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_NO_CREATE);
        return test != null;
    }

    private void showDefaultPinLockedNewMessageNotification() {
        if (!ListenerUtil.mutListener.listen(42124)) {
            logger.debug("showDefaultPinLockedNewMessageNotification");
        }
        if (!ListenerUtil.mutListener.listen(42125)) {
            this.showPinLockedNewMessageNotification(new NotificationService.NotificationSchema() {

                @Override
                public boolean vibrate() {
                    return false;
                }

                @Override
                public int getRingerMode() {
                    return 0;
                }

                @Override
                public Uri getSoundUri() {
                    return null;
                }

                @Override
                public int getColor() {
                    return 0;
                }
            }, PIN_LOCKED_NOTIFICATION_ID, true);
        }
    }

    public void showPinLockedNewMessageNotification(NotificationSchema notificationSchema, String uid, boolean quiet) {
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this.context, quiet ? NOTIFICATION_CHANNEL_CHAT_UPDATE : NOTIFICATION_CHANNEL_CHAT, notificationSchema).setSmallIcon(R.drawable.ic_notification_small).setContentTitle(this.context.getString(R.string.new_messages_locked)).setContentText(this.context.getString(R.string.new_messages_locked_description)).setTicker(this.context.getString(R.string.new_messages_locked)).setCategory(NotificationCompat.CATEGORY_MESSAGE).setPriority(this.preferenceService.getNotificationPriority()).setOnlyAlertOnce(false).setContentIntent(getPendingIntentForActivity(HomeActivity.class));
        if (!ListenerUtil.mutListener.listen(42126)) {
            this.notify(ThreemaApplication.NEW_MESSAGE_PIN_LOCKED_NOTIFICATION_ID, builder);
        }
        if (!ListenerUtil.mutListener.listen(42127)) {
            showIconBadge(0);
        }
        if (!ListenerUtil.mutListener.listen(42131)) {
            // cancel this message as soon as the app is unlocked
            this.lockAppService.addOnLockAppStateChanged(new LockAppService.OnLockAppStateChanged() {

                @Override
                public boolean changed(boolean locked) {
                    if (!ListenerUtil.mutListener.listen(42128)) {
                        logger.debug("LockAppState changed. locked = " + locked);
                    }
                    if (!ListenerUtil.mutListener.listen(42130)) {
                        if (!locked) {
                            if (!ListenerUtil.mutListener.listen(42129)) {
                                cancelPinLockedNewMessagesNotification();
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(42132)) {
            logger.info("Showing generic notification (pin locked) = {} quiet (unprotected > pin) = {} ", uid, quiet);
        }
    }

    @Override
    public void showMasterKeyLockedNewMessageNotification() {
        if (!ListenerUtil.mutListener.listen(42133)) {
            this.showMasterKeyLockedNewMessageNotification(this.getDefaultNotificationSchema());
        }
    }

    private void showMasterKeyLockedNewMessageNotification(NotificationSchema notificationSchema) {
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this.context, NOTIFICATION_CHANNEL_CHAT, notificationSchema).setSmallIcon(R.drawable.ic_notification_small).setContentTitle(this.context.getString(R.string.new_messages_locked)).setContentText(this.context.getString(R.string.new_messages_locked_description)).setTicker(this.context.getString(R.string.new_messages_locked)).setCategory(NotificationCompat.CATEGORY_MESSAGE).setOnlyAlertOnce(false).setContentIntent(getPendingIntentForActivity(HomeActivity.class));
        if (!ListenerUtil.mutListener.listen(42134)) {
            this.notify(ThreemaApplication.NEW_MESSAGE_LOCKED_NOTIFICATION_ID, builder);
        }
        if (!ListenerUtil.mutListener.listen(42135)) {
            logger.info("Showing generic notification (master key locked)");
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.N)
    public void showNetworkBlockedNotification(boolean noisy) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (!ListenerUtil.mutListener.listen(42144)) {
            if (connectivityManager != null) {
                if (!ListenerUtil.mutListener.listen(42143)) {
                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
                        String message = String.format(this.context.getString(R.string.network_blocked_body), this.context.getString(R.string.app_name));
                        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this.context, noisy ? NOTIFICATION_CHANNEL_ALERT : NOTIFICATION_CHANNEL_NOTICE, noisy ? this.getDefaultNotificationSchema() : null).setSmallIcon(R.drawable.ic_error_red_24dp).setContentTitle(this.context.getString(R.string.network_blocked_title)).setContentText(message).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setCategory(NotificationCompat.CATEGORY_ERROR).setPriority(noisy ? preferenceService.getNotificationPriority() : NotificationCompat.PRIORITY_LOW).setAutoCancel(true).setTimeoutAfter(DateUtils.HOUR_IN_MILLIS).setLocalOnly(true).setOnlyAlertOnce(true);
                        Intent notificationIntent = new Intent(Settings.ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS);
                        if (!ListenerUtil.mutListener.listen(42136)) {
                            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                        if (!ListenerUtil.mutListener.listen(42137)) {
                            notificationIntent.setData(Uri.parse("package:" + BuildConfig.APPLICATION_ID));
                        }
                        PackageManager packageManager = context.getPackageManager();
                        if (!ListenerUtil.mutListener.listen(42142)) {
                            if (notificationIntent.resolveActivity(packageManager) != null) {
                                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                if (!ListenerUtil.mutListener.listen(42138)) {
                                    createPendingIntentWithTaskStack(notificationIntent);
                                }
                                if (!ListenerUtil.mutListener.listen(42139)) {
                                    builder.setContentIntent(pendingIntent);
                                }
                                if (!ListenerUtil.mutListener.listen(42140)) {
                                    this.notify(ThreemaApplication.NETWORK_BLOCKED_NOTIFICATION_ID, builder);
                                }
                                if (!ListenerUtil.mutListener.listen(42141)) {
                                    logger.info("Showing network blocked notification");
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42145)) {
            logger.warn("Failed showing network blocked notification");
        }
    }

    @Override
    public void cancelNetworkBlockedNotification() {
        if (!ListenerUtil.mutListener.listen(42146)) {
            this.cancel(ThreemaApplication.NETWORK_BLOCKED_NOTIFICATION_ID);
        }
        if (!ListenerUtil.mutListener.listen(42147)) {
            logger.info("Cancel network blocked notification");
        }
    }

    private void cancelPinLockedNewMessagesNotification() {
        if (!ListenerUtil.mutListener.listen(42148)) {
            logger.debug("cancel Pin Locked New Messages");
        }
        if (!ListenerUtil.mutListener.listen(42149)) {
            this.cancel(ThreemaApplication.NEW_MESSAGE_PIN_LOCKED_NOTIFICATION_ID);
        }
    }

    @Override
    public void showServerMessage(ServerMessageModel m) {
        Intent intent = new Intent(context, ServerMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(42150)) {
            IntentDataUtil.append(m, intent);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_NOTICE, null).setSmallIcon(R.drawable.ic_error_red_24dp).setTicker(this.context.getString(R.string.server_message_title)).setContentTitle(this.context.getString(R.string.server_message_title)).setContentText(this.context.getString(R.string.tap_here_for_more)).setContentIntent(pendingIntent).setLocalOnly(true).setPriority(NotificationCompat.PRIORITY_MAX).setAutoCancel(true);
        if (!ListenerUtil.mutListener.listen(42151)) {
            this.notify(ThreemaApplication.SERVER_MESSAGE_NOTIFICATION_ID, builder);
        }
    }

    @Override
    public void cancelServerMessage() {
        if (!ListenerUtil.mutListener.listen(42152)) {
            this.cancel(ThreemaApplication.SERVER_MESSAGE_NOTIFICATION_ID);
        }
    }

    @Override
    public void showNotEnoughDiskSpace(long availableSpace, long requiredSpace) {
        if (!ListenerUtil.mutListener.listen(42153)) {
            logger.warn("Not enough diskspace. Available: {} required: {}", availableSpace, requiredSpace);
        }
        String text = this.context.getString(R.string.not_enough_disk_space_text, Formatter.formatFileSize(this.context, requiredSpace));
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_notification_small).setTicker(this.context.getString(R.string.not_enough_disk_space_title)).setPriority(NotificationCompat.PRIORITY_MAX).setContentTitle(this.context.getString(R.string.not_enough_disk_space_title)).setLocalOnly(true).setContentText(text).setStyle(new NotificationCompat.BigTextStyle().bigText(text));
        if (!ListenerUtil.mutListener.listen(42160)) {
            if ((ListenerUtil.mutListener.listen(42158) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(42157) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(42156) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(42155) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.N_MR1) : (ListenerUtil.mutListener.listen(42154) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1))))))) {
                Intent cleanupIntent = new Intent(StorageManager.ACTION_MANAGE_STORAGE);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, cleanupIntent, 0);
                if (!ListenerUtil.mutListener.listen(42159)) {
                    builder.addAction(R.drawable.ic_sd_card_black_24dp, context.getString(R.string.check_now), pendingIntent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42161)) {
            this.notify(ThreemaApplication.NOT_ENOUGH_DISK_SPACE_NOTIFICATION_ID, builder);
        }
    }

    private PendingIntent createPendingIntentWithTaskStack(Intent intent) {
        if (!ListenerUtil.mutListener.listen(42162)) {
            intent.setData((Uri.parse("foobar://" + SystemClock.elapsedRealtime())));
        }
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        if (!ListenerUtil.mutListener.listen(42163)) {
            stackBuilder.addNextIntentWithParentStack(intent);
        }
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private PendingIntent getPendingIntentForActivity(Class<? extends Activity> activityClass) {
        Intent notificationIntent = new Intent(this.context, activityClass);
        if (!ListenerUtil.mutListener.listen(42164)) {
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return createPendingIntentWithTaskStack(notificationIntent);
    }

    @Override
    public void showUnsentMessageNotification(ArrayList<AbstractMessageModel> failedMessages) {
        int num = failedMessages != null ? failedMessages.size() : 0;
        if (!ListenerUtil.mutListener.listen(42174)) {
            if ((ListenerUtil.mutListener.listen(42169) ? (num >= 0) : (ListenerUtil.mutListener.listen(42168) ? (num <= 0) : (ListenerUtil.mutListener.listen(42167) ? (num < 0) : (ListenerUtil.mutListener.listen(42166) ? (num != 0) : (ListenerUtil.mutListener.listen(42165) ? (num == 0) : (num > 0))))))) {
                Intent sendIntent = new Intent(context, ReSendMessagesBroadcastReceiver.class);
                if (!ListenerUtil.mutListener.listen(42171)) {
                    IntentDataUtil.appendMultipleMessageTypes(failedMessages, sendIntent);
                }
                PendingIntent sendPendingIntent = PendingIntent.getBroadcast(context, ThreemaApplication.UNSENT_MESSAGE_NOTIFICATION_ID, sendIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Action tryAgainAction = new NotificationCompat.Action.Builder(R.drawable.ic_wear_full_retry, context.getString(R.string.try_again), sendPendingIntent).build();
                NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
                if (!ListenerUtil.mutListener.listen(42172)) {
                    wearableExtender.addAction(tryAgainAction);
                }
                String content = String.format(this.context.getString(R.string.sending_message_failed), num);
                NotificationCompat.Builder builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_error_red_24dp).setTicker(content).setPriority(NotificationCompat.PRIORITY_HIGH).setCategory(NotificationCompat.CATEGORY_ERROR).setColor(context.getResources().getColor(R.color.material_red)).setContentIntent(getPendingIntentForActivity(HomeActivity.class)).extend(wearableExtender).setContentTitle(this.context.getString(R.string.app_name)).setContentText(content).setStyle(new NotificationCompat.BigTextStyle().bigText(content)).addAction(R.drawable.ic_refresh_white_24dp, context.getString(R.string.try_again), sendPendingIntent);
                if (!ListenerUtil.mutListener.listen(42173)) {
                    this.notify(ThreemaApplication.UNSENT_MESSAGE_NOTIFICATION_ID, builder);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42170)) {
                    this.cancel(ThreemaApplication.UNSENT_MESSAGE_NOTIFICATION_ID);
                }
            }
        }
    }

    @Override
    public void showSafeBackupFailed(int numDays) {
        if (!ListenerUtil.mutListener.listen(42183)) {
            if ((ListenerUtil.mutListener.listen(42180) ? ((ListenerUtil.mutListener.listen(42179) ? (numDays >= 0) : (ListenerUtil.mutListener.listen(42178) ? (numDays <= 0) : (ListenerUtil.mutListener.listen(42177) ? (numDays < 0) : (ListenerUtil.mutListener.listen(42176) ? (numDays != 0) : (ListenerUtil.mutListener.listen(42175) ? (numDays == 0) : (numDays > 0)))))) || preferenceService.getThreemaSafeEnabled()) : ((ListenerUtil.mutListener.listen(42179) ? (numDays >= 0) : (ListenerUtil.mutListener.listen(42178) ? (numDays <= 0) : (ListenerUtil.mutListener.listen(42177) ? (numDays < 0) : (ListenerUtil.mutListener.listen(42176) ? (numDays != 0) : (ListenerUtil.mutListener.listen(42175) ? (numDays == 0) : (numDays > 0)))))) && preferenceService.getThreemaSafeEnabled()))) {
                Intent intent = new Intent(context, BackupAdminActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                String content = String.format(this.context.getString(R.string.safe_failed_notification), numDays);
                NotificationCompat.Builder builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_ALERT, null).setSmallIcon(R.drawable.ic_error_red_24dp).setTicker(content).setLocalOnly(true).setPriority(NotificationCompat.PRIORITY_HIGH).setCategory(NotificationCompat.CATEGORY_ERROR).setColor(context.getResources().getColor(R.color.material_red)).setContentIntent(pendingIntent).setContentTitle(this.context.getString(R.string.app_name)).setContentText(content).setStyle(new NotificationCompat.BigTextStyle().bigText(content));
                if (!ListenerUtil.mutListener.listen(42182)) {
                    this.notify(ThreemaApplication.SAFE_FAILED_NOTIFICATION_ID, builder);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42181)) {
                    this.cancel(ThreemaApplication.SAFE_FAILED_NOTIFICATION_ID);
                }
            }
        }
    }

    public void showWorkSyncProgress() {
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (!ListenerUtil.mutListener.listen(42184)) {
            if (notificationManager == null) {
                return;
            }
        }
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_WORK_SYNC, null).setSound(null).setSmallIcon(R.drawable.ic_sync_notification).setContentTitle(this.context.getString(R.string.wizard1_sync_work)).setProgress(0, 0, true).setPriority(Notification.PRIORITY_LOW).setAutoCancel(true).setLocalOnly(true).setOnlyAlertOnce(true);
        if (!ListenerUtil.mutListener.listen(42185)) {
            this.notify(ThreemaApplication.WORK_SYNC_NOTIFICATION_ID, builder);
        }
    }

    @Override
    public void cancelWorkSyncProgress() {
        if (!ListenerUtil.mutListener.listen(42186)) {
            this.cancel(ThreemaApplication.WORK_SYNC_NOTIFICATION_ID);
        }
    }

    @Override
    public void showNewSyncedContactsNotification(List<ContactModel> contactModels) {
        if (!ListenerUtil.mutListener.listen(42211)) {
            if ((ListenerUtil.mutListener.listen(42191) ? (contactModels.size() >= 0) : (ListenerUtil.mutListener.listen(42190) ? (contactModels.size() <= 0) : (ListenerUtil.mutListener.listen(42189) ? (contactModels.size() < 0) : (ListenerUtil.mutListener.listen(42188) ? (contactModels.size() != 0) : (ListenerUtil.mutListener.listen(42187) ? (contactModels.size() == 0) : (contactModels.size() > 0))))))) {
                String message;
                Intent notificationIntent;
                if ((ListenerUtil.mutListener.listen(42196) ? (contactModels.size() >= 1) : (ListenerUtil.mutListener.listen(42195) ? (contactModels.size() <= 1) : (ListenerUtil.mutListener.listen(42194) ? (contactModels.size() < 1) : (ListenerUtil.mutListener.listen(42193) ? (contactModels.size() != 1) : (ListenerUtil.mutListener.listen(42192) ? (contactModels.size() == 1) : (contactModels.size() > 1))))))) {
                    StringBuilder contactListBuilder = new StringBuilder();
                    if (!ListenerUtil.mutListener.listen(42206)) {
                        {
                            long _loopCounter487 = 0;
                            for (ContactModel contactModel : contactModels) {
                                ListenerUtil.loopListener.listen("_loopCounter487", ++_loopCounter487);
                                if (!ListenerUtil.mutListener.listen(42204)) {
                                    if ((ListenerUtil.mutListener.listen(42202) ? (contactListBuilder.length() >= 0) : (ListenerUtil.mutListener.listen(42201) ? (contactListBuilder.length() <= 0) : (ListenerUtil.mutListener.listen(42200) ? (contactListBuilder.length() < 0) : (ListenerUtil.mutListener.listen(42199) ? (contactListBuilder.length() != 0) : (ListenerUtil.mutListener.listen(42198) ? (contactListBuilder.length() == 0) : (contactListBuilder.length() > 0))))))) {
                                        if (!ListenerUtil.mutListener.listen(42203)) {
                                            contactListBuilder.append(", ");
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(42205)) {
                                    contactListBuilder.append(NameUtil.getDisplayName(contactModel));
                                }
                            }
                        }
                    }
                    message = this.context.getString(R.string.notification_contact_has_joined_multiple, contactModels.size(), this.context.getString(R.string.app_name), contactListBuilder.toString());
                    notificationIntent = new Intent(context, HomeActivity.class);
                    if (!ListenerUtil.mutListener.listen(42207)) {
                        notificationIntent.putExtra(HomeActivity.EXTRA_SHOW_CONTACTS, true);
                    }
                } else {
                    String name = NameUtil.getDisplayName(contactModels.get(0));
                    message = String.format(this.context.getString(R.string.notification_contact_has_joined), name, this.context.getString(R.string.app_name));
                    notificationIntent = new Intent(context, ComposeMessageActivity.class);
                    if (!ListenerUtil.mutListener.listen(42197)) {
                        contactService.createReceiver(contactModels.get(0)).prepareIntent(notificationIntent);
                    }
                }
                if (!ListenerUtil.mutListener.listen(42208)) {
                    notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                }
                PendingIntent openPendingIntent = createPendingIntentWithTaskStack(notificationIntent);
                NotificationSchemaImpl notificationSchema = new NotificationSchemaImpl(this.context);
                if (!ListenerUtil.mutListener.listen(42209)) {
                    notificationSchema.setVibrate(this.preferenceService.isVibrate()).setColor(this.getColorValue(preferenceService.getNotificationLight()));
                }
                NotificationCompat.Builder builder = new NotificationBuilderWrapper(context, NOTIFICATION_CHANNEL_NEW_SYNCED_CONTACTS, notificationSchema).setSmallIcon(R.drawable.ic_notification_small).setContentTitle(this.context.getString(R.string.notification_channel_new_contact)).setContentText(message).setContentIntent(openPendingIntent).setStyle(new NotificationCompat.BigTextStyle().bigText(message)).setPriority(NotificationCompat.PRIORITY_HIGH).setAutoCancel(true);
                if (!ListenerUtil.mutListener.listen(42210)) {
                    this.notify(ThreemaApplication.NEW_SYNCED_CONTACTS_NOTIFICATION_ID, builder);
                }
            }
        }
    }

    private void notify(int id, NotificationCompat.Builder builder) {
        try {
            if (!ListenerUtil.mutListener.listen(42213)) {
                notificationManagerCompat.notify(id, builder.build());
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(42212)) {
                // catch FileUriExposedException - see https://commonsware.com/blog/2016/09/07/notifications-sounds-android-7p0-aggravation.html
                logger.error("Exception", e);
            }
        }
    }

    public void cancel(int id) {
        // make sure that pending intent is also cancelled to allow to check for active conversation notifications pre SDK 23
        Intent intent = new Intent(context, ComposeMessageActivity.class);
        if (!ListenerUtil.mutListener.listen(42216)) {
            if (id == ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID) {
                PendingIntent pendingConversationIntent = PendingIntent.getActivity(context, ThreemaApplication.NEW_MESSAGE_NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                if (!ListenerUtil.mutListener.listen(42215)) {
                    if (pendingConversationIntent != null) {
                        if (!ListenerUtil.mutListener.listen(42214)) {
                            pendingConversationIntent.cancel();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42217)) {
            notificationManager.cancel(id);
        }
    }

    private int getColorValue(String colorString) {
        int[] colorsHex = context.getResources().getIntArray(R.array.list_light_color_hex);
        if (!ListenerUtil.mutListener.listen(42224)) {
            if ((ListenerUtil.mutListener.listen(42223) ? (colorString != null || (ListenerUtil.mutListener.listen(42222) ? (colorString.length() >= 0) : (ListenerUtil.mutListener.listen(42221) ? (colorString.length() <= 0) : (ListenerUtil.mutListener.listen(42220) ? (colorString.length() < 0) : (ListenerUtil.mutListener.listen(42219) ? (colorString.length() != 0) : (ListenerUtil.mutListener.listen(42218) ? (colorString.length() == 0) : (colorString.length() > 0))))))) : (colorString != null && (ListenerUtil.mutListener.listen(42222) ? (colorString.length() >= 0) : (ListenerUtil.mutListener.listen(42221) ? (colorString.length() <= 0) : (ListenerUtil.mutListener.listen(42220) ? (colorString.length() < 0) : (ListenerUtil.mutListener.listen(42219) ? (colorString.length() != 0) : (ListenerUtil.mutListener.listen(42218) ? (colorString.length() == 0) : (colorString.length() > 0))))))))) {
                return colorsHex[Integer.valueOf(colorString)];
            }
        }
        return -1;
    }

    private void showIconBadge(int unreadMessages) {
        if (!ListenerUtil.mutListener.listen(42225)) {
            logger.debug("Badge: showing " + unreadMessages + " unread");
        }
        if (!ListenerUtil.mutListener.listen(42260)) {
            if (context.getPackageManager().resolveContentProvider("com.teslacoilsw.notifier", 0) != null) {
                // nova launcher / teslaunread
                try {
                    String launcherClassName = context.getPackageManager().getLaunchIntentForPackage(BuildConfig.APPLICATION_ID).getComponent().getClassName();
                    final ContentValues contentValues = new ContentValues();
                    if (!ListenerUtil.mutListener.listen(42257)) {
                        contentValues.put("tag", BuildConfig.APPLICATION_ID + "/" + launcherClassName);
                    }
                    if (!ListenerUtil.mutListener.listen(42258)) {
                        contentValues.put("count", unreadMessages);
                    }
                    if (!ListenerUtil.mutListener.listen(42259)) {
                        context.getApplicationContext().getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), contentValues);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(42256)) {
                        logger.error("Exception", e);
                    }
                }
            } else if (ConfigUtils.isHuaweiDevice()) {
                try {
                    String launcherClassName = context.getPackageManager().getLaunchIntentForPackage(BuildConfig.APPLICATION_ID).getComponent().getClassName();
                    Bundle localBundle = new Bundle();
                    if (!ListenerUtil.mutListener.listen(42252)) {
                        localBundle.putString("package", BuildConfig.APPLICATION_ID);
                    }
                    if (!ListenerUtil.mutListener.listen(42253)) {
                        localBundle.putString("class", launcherClassName);
                    }
                    if (!ListenerUtil.mutListener.listen(42254)) {
                        localBundle.putInt("badgenumber", unreadMessages);
                    }
                    if (!ListenerUtil.mutListener.listen(42255)) {
                        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, localBundle);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(42251)) {
                        logger.error("Exception", e);
                    }
                }
            } else if (ConfigUtils.isSonyDevice()) {
                try {
                    String launcherClassName = context.getPackageManager().getLaunchIntentForPackage(BuildConfig.APPLICATION_ID).getComponent().getClassName();
                    if (!ListenerUtil.mutListener.listen(42250)) {
                        if (context.getPackageManager().resolveContentProvider("com.sonymobile.home.resourceprovider", 0) != null) {
                            // use content provider
                            final ContentValues contentValues = new ContentValues();
                            if (!ListenerUtil.mutListener.listen(42242)) {
                                contentValues.put("badge_count", unreadMessages);
                            }
                            if (!ListenerUtil.mutListener.listen(42243)) {
                                contentValues.put("package_name", BuildConfig.APPLICATION_ID);
                            }
                            if (!ListenerUtil.mutListener.listen(42244)) {
                                contentValues.put("activity_name", launcherClassName);
                            }
                            if (!ListenerUtil.mutListener.listen(42249)) {
                                if (RuntimeUtil.isOnUiThread()) {
                                    if (!ListenerUtil.mutListener.listen(42247)) {
                                        if (queryHandler == null) {
                                            if (!ListenerUtil.mutListener.listen(42246)) {
                                                queryHandler = new AsyncQueryHandler(context.getApplicationContext().getContentResolver()) {
                                                };
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(42248)) {
                                        queryHandler.startInsert(0, null, Uri.parse("content://com.sonymobile.home.resourceprovider/badge"), contentValues);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(42245)) {
                                        context.getApplicationContext().getContentResolver().insert(Uri.parse("content://com.sonymobile.home.resourceprovider/badge"), contentValues);
                                    }
                                }
                            }
                        } else {
                            // use broadcast
                            Intent intent = new Intent("com.sonyericsson.home.action.UPDATE_BADGE");
                            if (!ListenerUtil.mutListener.listen(42232)) {
                                intent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
                            }
                            if (!ListenerUtil.mutListener.listen(42233)) {
                                intent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", launcherClassName);
                            }
                            if (!ListenerUtil.mutListener.listen(42234)) {
                                intent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(unreadMessages));
                            }
                            if (!ListenerUtil.mutListener.listen(42240)) {
                                intent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", (ListenerUtil.mutListener.listen(42239) ? (unreadMessages >= 0) : (ListenerUtil.mutListener.listen(42238) ? (unreadMessages <= 0) : (ListenerUtil.mutListener.listen(42237) ? (unreadMessages < 0) : (ListenerUtil.mutListener.listen(42236) ? (unreadMessages != 0) : (ListenerUtil.mutListener.listen(42235) ? (unreadMessages == 0) : (unreadMessages > 0)))))));
                            }
                            if (!ListenerUtil.mutListener.listen(42241)) {
                                context.sendBroadcast(intent);
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(42231)) {
                        logger.error("Exception", e);
                    }
                }
            } else {
                // also works on LG and later HTC devices
                try {
                    String launcherClassName = context.getPackageManager().getLaunchIntentForPackage(BuildConfig.APPLICATION_ID).getComponent().getClassName();
                    Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
                    if (!ListenerUtil.mutListener.listen(42227)) {
                        intent.putExtra("badge_count", unreadMessages);
                    }
                    if (!ListenerUtil.mutListener.listen(42228)) {
                        intent.putExtra("badge_count_package_name", BuildConfig.APPLICATION_ID);
                    }
                    if (!ListenerUtil.mutListener.listen(42229)) {
                        intent.putExtra("badge_count_class_name", launcherClassName);
                    }
                    if (!ListenerUtil.mutListener.listen(42230)) {
                        context.sendBroadcast(intent);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(42226)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }

    @Override
    public void showWebclientResumeFailed(String msg) {
        NotificationCompat.Builder builder = new NotificationBuilderWrapper(this.context, NOTIFICATION_CHANNEL_NOTICE, null).setSmallIcon(R.drawable.ic_web_notification).setTicker(msg).setLocalOnly(true).setPriority(NotificationCompat.PRIORITY_HIGH).setCategory(NotificationCompat.CATEGORY_ERROR).setColor(this.context.getResources().getColor(R.color.material_red)).setContentTitle(this.context.getString(R.string.app_name)).setContentText(msg).setStyle(new NotificationCompat.BigTextStyle().bigText(msg));
        if (!ListenerUtil.mutListener.listen(42261)) {
            this.notify(ThreemaApplication.WEB_RESUME_FAILED_NOTIFICATION_ID, builder);
        }
    }

    @Override
    public void cancelRestartNotification() {
        if (!ListenerUtil.mutListener.listen(42262)) {
            cancel(APP_RESTART_NOTIFICATION_ID);
        }
    }

    public void resetConversationNotifications() {
        if (!ListenerUtil.mutListener.listen(42263)) {
            conversationNotifications.clear();
        }
    }
}
