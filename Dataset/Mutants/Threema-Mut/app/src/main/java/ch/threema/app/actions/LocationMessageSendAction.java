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
package ch.threema.app.actions;

import android.location.Location;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.MessageUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationMessageSendAction extends SendAction {

    protected static volatile LocationMessageSendAction instance;

    private static final Object instanceLock = new Object();

    private MessageService messageService;

    private LocationMessageSendAction() {
    }

    public static LocationMessageSendAction getInstance() {
        if (!ListenerUtil.mutListener.listen(4)) {
            if (instance == null) {
                synchronized (instanceLock) {
                    if (!ListenerUtil.mutListener.listen(3)) {
                        if (instance == null) {
                            if (!ListenerUtil.mutListener.listen(2)) {
                                instance = new LocationMessageSendAction();
                            }
                        }
                    }
                }
            }
        }
        return instance;
    }

    public boolean sendLocationMessage(final MessageReceiver[] allReceivers, final Location location, final String poiName, final ActionHandler actionHandler) {
        if (!ListenerUtil.mutListener.listen(5)) {
            if (actionHandler == null) {
                return false;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(7)) {
                messageService = this.getServiceManager().getMessageService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(6)) {
                actionHandler.onError(e.getMessage());
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(10)) {
            if ((ListenerUtil.mutListener.listen(8) ? (messageService == null && location == null) : (messageService == null || location == null))) {
                if (!ListenerUtil.mutListener.listen(9)) {
                    actionHandler.onError("Nothing to send");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(17)) {
            if ((ListenerUtil.mutListener.listen(15) ? (allReceivers.length >= 1) : (ListenerUtil.mutListener.listen(14) ? (allReceivers.length <= 1) : (ListenerUtil.mutListener.listen(13) ? (allReceivers.length > 1) : (ListenerUtil.mutListener.listen(12) ? (allReceivers.length != 1) : (ListenerUtil.mutListener.listen(11) ? (allReceivers.length == 1) : (allReceivers.length < 1))))))) {
                if (!ListenerUtil.mutListener.listen(16)) {
                    actionHandler.onError("no message receiver");
                }
                return false;
            }
        }
        // add distribution list members to list of receivers
        final MessageReceiver[] resolvedReceivers = MessageUtil.addDistributionListReceivers(allReceivers);
        final int numReceivers = resolvedReceivers.length;
        if (!ListenerUtil.mutListener.listen(34)) {
            sendSingleMessage(resolvedReceivers[0], location, poiName, new ActionHandler() {

                int receiverIndex = 0;

                @Override
                public void onError(String errorMessage) {
                    if (!ListenerUtil.mutListener.listen(18)) {
                        actionHandler.onError(errorMessage);
                    }
                }

                @Override
                public void onWarning(String warning, boolean continueAction) {
                }

                @Override
                public void onProgress(int progress, int total) {
                    if (!ListenerUtil.mutListener.listen(19)) {
                        actionHandler.onProgress(progress + receiverIndex, numReceivers);
                    }
                }

                @Override
                public void onCompleted() {
                    if (!ListenerUtil.mutListener.listen(33)) {
                        if ((ListenerUtil.mutListener.listen(28) ? (receiverIndex >= (ListenerUtil.mutListener.listen(23) ? (numReceivers % 1) : (ListenerUtil.mutListener.listen(22) ? (numReceivers / 1) : (ListenerUtil.mutListener.listen(21) ? (numReceivers * 1) : (ListenerUtil.mutListener.listen(20) ? (numReceivers + 1) : (numReceivers - 1)))))) : (ListenerUtil.mutListener.listen(27) ? (receiverIndex <= (ListenerUtil.mutListener.listen(23) ? (numReceivers % 1) : (ListenerUtil.mutListener.listen(22) ? (numReceivers / 1) : (ListenerUtil.mutListener.listen(21) ? (numReceivers * 1) : (ListenerUtil.mutListener.listen(20) ? (numReceivers + 1) : (numReceivers - 1)))))) : (ListenerUtil.mutListener.listen(26) ? (receiverIndex > (ListenerUtil.mutListener.listen(23) ? (numReceivers % 1) : (ListenerUtil.mutListener.listen(22) ? (numReceivers / 1) : (ListenerUtil.mutListener.listen(21) ? (numReceivers * 1) : (ListenerUtil.mutListener.listen(20) ? (numReceivers + 1) : (numReceivers - 1)))))) : (ListenerUtil.mutListener.listen(25) ? (receiverIndex != (ListenerUtil.mutListener.listen(23) ? (numReceivers % 1) : (ListenerUtil.mutListener.listen(22) ? (numReceivers / 1) : (ListenerUtil.mutListener.listen(21) ? (numReceivers * 1) : (ListenerUtil.mutListener.listen(20) ? (numReceivers + 1) : (numReceivers - 1)))))) : (ListenerUtil.mutListener.listen(24) ? (receiverIndex == (ListenerUtil.mutListener.listen(23) ? (numReceivers % 1) : (ListenerUtil.mutListener.listen(22) ? (numReceivers / 1) : (ListenerUtil.mutListener.listen(21) ? (numReceivers * 1) : (ListenerUtil.mutListener.listen(20) ? (numReceivers + 1) : (numReceivers - 1)))))) : (receiverIndex < (ListenerUtil.mutListener.listen(23) ? (numReceivers % 1) : (ListenerUtil.mutListener.listen(22) ? (numReceivers / 1) : (ListenerUtil.mutListener.listen(21) ? (numReceivers * 1) : (ListenerUtil.mutListener.listen(20) ? (numReceivers + 1) : (numReceivers - 1)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(31)) {
                                receiverIndex++;
                            }
                            if (!ListenerUtil.mutListener.listen(32)) {
                                sendSingleMessage(resolvedReceivers[receiverIndex], location, poiName, this);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(29)) {
                                actionHandler.onCompleted();
                            }
                            if (!ListenerUtil.mutListener.listen(30)) {
                                messageService.sendProfilePicture(resolvedReceivers);
                            }
                        }
                    }
                }
            });
        }
        return true;
    }

    private void sendSingleMessage(final MessageReceiver messageReceiver, final Location location, final String poiName, final ActionHandler actionHandler) {
        if (!ListenerUtil.mutListener.listen(36)) {
            if (messageReceiver == null) {
                if (!ListenerUtil.mutListener.listen(35)) {
                    actionHandler.onError("No receiver");
                }
                return;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(40)) {
                messageService.sendLocation(location, poiName, messageReceiver, new MessageService.CompletionHandler() {

                    @Override
                    public void sendComplete(AbstractMessageModel messageModel) {
                    }

                    @Override
                    public void sendQueued(AbstractMessageModel messageModel) {
                        if (!ListenerUtil.mutListener.listen(38)) {
                            actionHandler.onCompleted();
                        }
                    }

                    @Override
                    public void sendError(int reason) {
                        if (!ListenerUtil.mutListener.listen(39)) {
                            actionHandler.onError(String.format(ThreemaApplication.getAppContext().getString(R.string.an_error_occurred_more), Integer.toString(reason)));
                        }
                    }
                });
            }
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(37)) {
                actionHandler.onError(e.getMessage());
            }
        }
    }
}
