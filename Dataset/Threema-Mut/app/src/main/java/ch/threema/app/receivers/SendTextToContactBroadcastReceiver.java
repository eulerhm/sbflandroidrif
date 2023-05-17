/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.core.app.NotificationManagerCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.notifications.BackgroundErrorNotification;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SendTextToContactBroadcastReceiver extends ActionBroadcastReceiver {

    private static final Logger logger = LoggerFactory.getLogger(SendTextToContactBroadcastReceiver.class);

    @Override
    @SuppressLint("StaticFieldLeak")
    public void onReceive(final Context context, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(34455)) {
            if (intent == null) {
                return;
            }
        }
        int id = intent.getIntExtra(BackgroundErrorNotification.EXTRA_NOTIFICATION_ID, 0);
        if (!ListenerUtil.mutListener.listen(34462)) {
            if ((ListenerUtil.mutListener.listen(34460) ? (id >= 0) : (ListenerUtil.mutListener.listen(34459) ? (id <= 0) : (ListenerUtil.mutListener.listen(34458) ? (id > 0) : (ListenerUtil.mutListener.listen(34457) ? (id < 0) : (ListenerUtil.mutListener.listen(34456) ? (id == 0) : (id != 0))))))) {
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                if (!ListenerUtil.mutListener.listen(34461)) {
                    notificationManagerCompat.cancel(id);
                }
            }
        }
        String text = intent.getStringExtra(BackgroundErrorNotification.EXTRA_TEXT_TO_SEND);
        if (!ListenerUtil.mutListener.listen(34475)) {
            if (text != null) {
                final PendingResult pendingResult = goAsync();
                if (!ListenerUtil.mutListener.listen(34474)) {
                    new AsyncTask<Void, Void, Boolean>() {

                        @Override
                        protected Boolean doInBackground(Void... params) {
                            try {
                                if (!ListenerUtil.mutListener.listen(34465)) {
                                    // we need to make sure there's a connection during delivery
                                    lifetimeService.acquireConnection(TAG);
                                }
                                String identity = intent.getStringExtra(ThreemaApplication.INTENT_DATA_CONTACT);
                                if (!ListenerUtil.mutListener.listen(34470)) {
                                    if (identity != null) {
                                        final ContactModel contactModel = contactService.getOrCreateByIdentity(identity, true);
                                        MessageReceiver messageReceiver = contactService.createReceiver(contactModel);
                                        if (!ListenerUtil.mutListener.listen(34469)) {
                                            if (messageReceiver != null) {
                                                if (!ListenerUtil.mutListener.listen(34466)) {
                                                    messageService.sendText(text, messageReceiver);
                                                }
                                                if (!ListenerUtil.mutListener.listen(34467)) {
                                                    messageService.markConversationAsRead(messageReceiver, notificationService);
                                                }
                                                if (!ListenerUtil.mutListener.listen(34468)) {
                                                    logger.debug("Message sent to: " + messageReceiver.getShortName());
                                                }
                                                return true;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                if (!ListenerUtil.mutListener.listen(34463)) {
                                    logger.error("Exception", e);
                                }
                            } finally {
                                if (!ListenerUtil.mutListener.listen(34464)) {
                                    lifetimeService.releaseConnectionLinger(TAG, WEARABLE_CONNECTION_LINGER);
                                }
                            }
                            return false;
                        }

                        @Override
                        protected void onPostExecute(Boolean success) {
                            if (!ListenerUtil.mutListener.listen(34472)) {
                                if (success != null) {
                                    if (!ListenerUtil.mutListener.listen(34471)) {
                                        Toast.makeText(context, success ? R.string.message_sent : R.string.verify_failed, Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(34473)) {
                                pendingResult.finish();
                            }
                        }
                    }.execute();
                }
            }
        }
    }
}
