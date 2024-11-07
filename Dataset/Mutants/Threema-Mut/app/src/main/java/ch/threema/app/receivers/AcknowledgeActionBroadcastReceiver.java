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

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import ch.threema.app.R;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AcknowledgeActionBroadcastReceiver extends ActionBroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final PendingResult pendingResult = goAsync();
        if (!ListenerUtil.mutListener.listen(34316)) {
            new AsyncTask<Void, Void, Boolean>() {

                MessageReceiver messageReceiver = null;

                AbstractMessageModel messageModel = null;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(34303)) {
                        super.onPreExecute();
                    }
                    if (!ListenerUtil.mutListener.listen(34304)) {
                        messageReceiver = IntentDataUtil.getMessageReceiverFromIntent(context, intent);
                    }
                    if (!ListenerUtil.mutListener.listen(34306)) {
                        if (messageReceiver != null) {
                            if (!ListenerUtil.mutListener.listen(34305)) {
                                messageModel = IntentDataUtil.getMessageModelFromReceiver(intent, messageReceiver);
                            }
                        }
                    }
                }

                @Override
                protected Boolean doInBackground(Void... params) {
                    if (!ListenerUtil.mutListener.listen(34311)) {
                        if (messageModel != null) {
                            if (!ListenerUtil.mutListener.listen(34307)) {
                                // we need to make sure there's a connection during delivery
                                lifetimeService.acquireConnection(TAG);
                            }
                            if (!ListenerUtil.mutListener.listen(34308)) {
                                messageService.sendUserAcknowledgement(messageModel);
                            }
                            if (!ListenerUtil.mutListener.listen(34309)) {
                                messageService.markMessageAsRead(messageModel, notificationService);
                            }
                            if (!ListenerUtil.mutListener.listen(34310)) {
                                lifetimeService.releaseConnectionLinger(TAG, WEARABLE_CONNECTION_LINGER);
                            }
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                protected void onPostExecute(Boolean success) {
                    if (!ListenerUtil.mutListener.listen(34313)) {
                        if (success != null) {
                            if (!ListenerUtil.mutListener.listen(34312)) {
                                Toast.makeText(context, success ? R.string.message_acknowledged : R.string.an_error_occurred, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(34314)) {
                        notificationService.cancel(messageReceiver);
                    }
                    if (!ListenerUtil.mutListener.listen(34315)) {
                        pendingResult.finish();
                    }
                }
            }.execute();
        }
    }
}
