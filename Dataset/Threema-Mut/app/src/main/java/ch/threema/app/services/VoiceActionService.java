/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2016-2021 Threema GmbH
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

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.search.verification.client.SearchActionVerificationClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.RecipientListBaseActivity;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.ui.MediaItem;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class VoiceActionService extends SearchActionVerificationClientService {

    private static final Logger logger = LoggerFactory.getLogger(VoiceActionService.class);

    private static final String TAG = "VoiceActionService";

    private MessageService messageService;

    private LifetimeService lifetimeService;

    private NotificationService notificationService;

    private ContactService contactService;

    private LockAppService lockAppService;

    private static final String CHANNEL_ID_GOOGLE_ASSISTANT = "Voice_Actions";

    private static final int NOTIFICATION_ID = 10000;

    @Override
    public void performAction(Intent intent, boolean isVerified, Bundle options) {
        if (!ListenerUtil.mutListener.listen(41384)) {
            logger.debug(String.format("performAction: intent - %s, isVerified - %s", intent, isVerified));
        }
        if (!ListenerUtil.mutListener.listen(41385)) {
            this.instantiate();
        }
        if (!ListenerUtil.mutListener.listen(41388)) {
            if (!lockAppService.isLocked()) {
                if (!ListenerUtil.mutListener.listen(41387)) {
                    doPerformAction(intent, isVerified);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(41386)) {
                    RuntimeUtil.runOnUiThread(() -> Toast.makeText(VoiceActionService.this, R.string.pin_locked_cannot_send, Toast.LENGTH_LONG).show());
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    protected void postForegroundNotification() {
        if (!ListenerUtil.mutListener.listen(41389)) {
            this.createChannel();
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this.getApplicationContext(), CHANNEL_ID_GOOGLE_ASSISTANT).setGroup(CHANNEL_ID_GOOGLE_ASSISTANT).setContentTitle(this.getApplicationContext().getResources().getString(R.string.voice_action_title)).setContentText(this.getApplicationContext().getResources().getString(R.string.voice_action_body)).setSmallIcon(R.drawable.ic_notification_small).setPriority(NotificationCompat.PRIORITY_MIN).setVisibility(NotificationCompat.VISIBILITY_PUBLIC).setLocalOnly(true);
        if (!ListenerUtil.mutListener.listen(41390)) {
            this.startForeground(NOTIFICATION_ID, notificationBuilder.build());
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID_GOOGLE_ASSISTANT, this.getApplicationContext().getResources().getString(R.string.voice_action_title), NotificationManager.IMPORTANCE_LOW);
        if (!ListenerUtil.mutListener.listen(41391)) {
            channel.enableVibration(false);
        }
        if (!ListenerUtil.mutListener.listen(41392)) {
            channel.enableLights(false);
        }
        if (!ListenerUtil.mutListener.listen(41393)) {
            channel.setShowBadge(false);
        }
        NotificationManager notificationManager = this.getApplicationContext().getSystemService(NotificationManager.class);
        if (!ListenerUtil.mutListener.listen(41395)) {
            if (notificationManager != null) {
                if (!ListenerUtil.mutListener.listen(41394)) {
                    notificationManager.createNotificationChannel(channel);
                }
            }
        }
    }

    private boolean sendAudioMessage(final MessageReceiver messageReceiver, Intent intent, String caption) {
        ClipData clipData;
        clipData = intent.getClipData();
        if (!ListenerUtil.mutListener.listen(41396)) {
            if (clipData == null) {
                return false;
            }
        }
        ClipData.Item item = clipData.getItemAt(0);
        if (!ListenerUtil.mutListener.listen(41397)) {
            if (item == null) {
                return false;
            }
        }
        Uri uri = item.getUri();
        if (!ListenerUtil.mutListener.listen(41398)) {
            if (uri == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(41399)) {
            logger.debug("Audio uri: " + uri);
        }
        MediaItem mediaItem = new MediaItem(uri, MediaItem.TYPE_VOICEMESSAGE);
        if (!ListenerUtil.mutListener.listen(41400)) {
            mediaItem.setCaption(caption);
        }
        if (!ListenerUtil.mutListener.listen(41406)) {
            messageService.sendMediaAsync(Collections.singletonList(mediaItem), Collections.singletonList(messageReceiver), new MessageServiceImpl.SendResultListener() {

                @Override
                public void onError(String errorMessage) {
                    if (!ListenerUtil.mutListener.listen(41401)) {
                        logger.debug("Error sending audio message: " + errorMessage);
                    }
                    if (!ListenerUtil.mutListener.listen(41402)) {
                        lifetimeService.releaseConnectionLinger(TAG, PollingHelper.CONNECTION_LINGER);
                    }
                }

                @Override
                public void onCompleted() {
                    if (!ListenerUtil.mutListener.listen(41403)) {
                        logger.debug("Audio message sent");
                    }
                    if (!ListenerUtil.mutListener.listen(41404)) {
                        messageService.markConversationAsRead(messageReceiver, notificationService);
                    }
                    if (!ListenerUtil.mutListener.listen(41405)) {
                        lifetimeService.releaseConnectionLinger(TAG, PollingHelper.CONNECTION_LINGER);
                    }
                }
            });
        }
        return true;
    }

    public void doPerformAction(Intent intent, boolean isVerified) {
        if (!ListenerUtil.mutListener.listen(41418)) {
            if (isVerified) {
                Bundle bundle = intent.getExtras();
                if (!ListenerUtil.mutListener.listen(41417)) {
                    if (bundle != null) {
                        String identity = bundle.getString("com.google.android.voicesearch.extra.RECIPIENT_CONTACT_CHAT_ID");
                        String message = bundle.getString("android.intent.extra.TEXT");
                        if (!ListenerUtil.mutListener.listen(41416)) {
                            if (!TestUtil.empty(identity, message)) {
                                ContactModel contactModel = contactService.getByIdentity(identity);
                                if (!ListenerUtil.mutListener.listen(41415)) {
                                    if (contactModel != null) {
                                        final MessageReceiver messageReceiver = contactService.createReceiver(contactModel);
                                        if (!ListenerUtil.mutListener.listen(41414)) {
                                            if (messageReceiver != null) {
                                                if (!ListenerUtil.mutListener.listen(41407)) {
                                                    lifetimeService.acquireConnection(TAG);
                                                }
                                                if (!ListenerUtil.mutListener.listen(41413)) {
                                                    if (!sendAudioMessage(messageReceiver, intent, message)) {
                                                        try {
                                                            if (!ListenerUtil.mutListener.listen(41409)) {
                                                                messageService.sendText(message, messageReceiver);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(41410)) {
                                                                messageService.markConversationAsRead(messageReceiver, notificationService);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(41411)) {
                                                                logger.debug("Message sent to: " + identity);
                                                            }
                                                        } catch (Exception e) {
                                                            if (!ListenerUtil.mutListener.listen(41408)) {
                                                                logger.error("Exception", e);
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(41412)) {
                                                            lifetimeService.releaseConnectionLinger(TAG, PollingHelper.CONNECTION_LINGER);
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
                }
            }
        }
    }

    /*	@Override
	public boolean isTestingMode() {
		return true;
	}
*/
    protected final boolean requiredInstances() {
        if (!ListenerUtil.mutListener.listen(41420)) {
            if (!this.checkInstances()) {
                if (!ListenerUtil.mutListener.listen(41419)) {
                    this.instantiate();
                }
            }
        }
        return this.checkInstances();
    }

    protected boolean checkInstances() {
        return TestUtil.required(this.messageService, this.lifetimeService, this.notificationService, this.contactService, this.lockAppService);
    }

    protected void instantiate() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(41427)) {
            if (serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(41422)) {
                        this.messageService = serviceManager.getMessageService();
                    }
                    if (!ListenerUtil.mutListener.listen(41423)) {
                        this.lifetimeService = serviceManager.getLifetimeService();
                    }
                    if (!ListenerUtil.mutListener.listen(41424)) {
                        this.notificationService = serviceManager.getNotificationService();
                    }
                    if (!ListenerUtil.mutListener.listen(41425)) {
                        this.contactService = serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(41426)) {
                        this.lockAppService = serviceManager.getLockAppService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(41421)) {
                        logger.error("Exception", e);
                    }
                }
            }
        }
    }
}
