/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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
package ch.threema.app.receivers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.core.app.RemoteInput;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReplyActionBroadcastReceiver extends ActionBroadcastReceiver {

    private static final Logger logger = LoggerFactory.getLogger(ReplyActionBroadcastReceiver.class);

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(34414)) {
            if (!requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(34413)) {
                    Toast.makeText(context, R.string.verify_failed, Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
        final PendingResult pendingResult = goAsync();
        if (!ListenerUtil.mutListener.listen(34433)) {
            new AsyncTask<Void, Void, Boolean>() {

                MessageReceiver messageReceiver = null;

                AbstractMessageModel messageModel = null;

                CharSequence message = null;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(34415)) {
                        super.onPreExecute();
                    }
                    if (!ListenerUtil.mutListener.listen(34416)) {
                        messageReceiver = IntentDataUtil.getMessageReceiverFromIntent(context, intent);
                    }
                    if (!ListenerUtil.mutListener.listen(34419)) {
                        if (messageReceiver != null) {
                            if (!ListenerUtil.mutListener.listen(34417)) {
                                messageModel = IntentDataUtil.getMessageModelFromReceiver(intent, messageReceiver);
                            }
                            if (!ListenerUtil.mutListener.listen(34418)) {
                                message = getMessageText(intent);
                            }
                        }
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    if (!ListenerUtil.mutListener.listen(34427)) {
                        if ((ListenerUtil.mutListener.listen(34420) ? (messageModel != null || message != null) : (messageModel != null && message != null))) {
                            if (!ListenerUtil.mutListener.listen(34421)) {
                                // we need to make sure there's a connection during delivery
                                lifetimeService.acquireConnection(TAG);
                            }
                            try {
                                if (!ListenerUtil.mutListener.listen(34423)) {
                                    messageService.sendText(message.toString(), messageReceiver);
                                }
                                if (!ListenerUtil.mutListener.listen(34424)) {
                                    messageService.markConversationAsRead(messageReceiver, notificationService);
                                }
                                if (!ListenerUtil.mutListener.listen(34425)) {
                                    lifetimeService.releaseConnectionLinger(TAG, WEARABLE_CONNECTION_LINGER);
                                }
                                if (!ListenerUtil.mutListener.listen(34426)) {
                                    logger.debug("Message replied: " + messageModel.getUid());
                                }
                                return true;
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(34422)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(34428)) {
                        lifetimeService.releaseConnectionLinger(TAG, WEARABLE_CONNECTION_LINGER);
                    }
                    return false;
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (!ListenerUtil.mutListener.listen(34430)) {
                        if (success != null) {
                            if (!ListenerUtil.mutListener.listen(34429)) {
                                Toast.makeText(context, success ? R.string.message_sent : R.string.verify_failed, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(34431)) {
                        notificationService.cancel(messageReceiver);
                    }
                    if (!ListenerUtil.mutListener.listen(34432)) {
                        pendingResult.finish();
                    }
                }
            }.execute();
        }
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (!ListenerUtil.mutListener.listen(34434)) {
            if (remoteInput != null) {
                return remoteInput.getCharSequence(ThreemaApplication.EXTRA_VOICE_REPLY);
            }
        }
        return null;
    }
}
