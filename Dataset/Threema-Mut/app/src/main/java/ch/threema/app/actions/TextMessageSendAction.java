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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.utils.TextUtil;
import ch.threema.base.ThreemaException;
import ch.threema.client.ProtocolDefines;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TextMessageSendAction extends SendAction {

    protected static volatile TextMessageSendAction instance;

    private static final Object instanceLock = new Object();

    private TextMessageSendAction() {
    }

    public static TextMessageSendAction getInstance() {
        if (!ListenerUtil.mutListener.listen(43)) {
            if (instance == null) {
                synchronized (instanceLock) {
                    if (!ListenerUtil.mutListener.listen(42)) {
                        if (instance == null) {
                            if (!ListenerUtil.mutListener.listen(41)) {
                                instance = new TextMessageSendAction();
                            }
                        }
                    }
                }
            }
        }
        return instance;
    }

    public boolean sendTextMessage(final MessageReceiver[] allReceivers, String text, final ActionHandler actionHandler) {
        if (!ListenerUtil.mutListener.listen(44)) {
            if (actionHandler == null) {
                return false;
            }
        }
        MessageService messageService;
        try {
            messageService = this.getServiceManager().getMessageService();
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(45)) {
                actionHandler.onError(e.getMessage());
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(48)) {
            if ((ListenerUtil.mutListener.listen(46) ? (messageService == null && TestUtil.empty(text)) : (messageService == null || TestUtil.empty(text)))) {
                if (!ListenerUtil.mutListener.listen(47)) {
                    actionHandler.onError("Nothing to send");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(55)) {
            if ((ListenerUtil.mutListener.listen(53) ? (allReceivers.length >= 1) : (ListenerUtil.mutListener.listen(52) ? (allReceivers.length <= 1) : (ListenerUtil.mutListener.listen(51) ? (allReceivers.length > 1) : (ListenerUtil.mutListener.listen(50) ? (allReceivers.length != 1) : (ListenerUtil.mutListener.listen(49) ? (allReceivers.length == 1) : (allReceivers.length < 1))))))) {
                if (!ListenerUtil.mutListener.listen(54)) {
                    actionHandler.onError("No message receiver");
                }
                return false;
            }
        }
        /* split input text into multiple strings if necessary */
        ArrayList<String> messageTexts = TextUtil.splitEmojiText(text, ProtocolDefines.MAX_TEXT_MESSAGE_LEN);
        // add distribution list members to list of receivers
        final MessageReceiver[] resolvedReceivers = MessageUtil.addDistributionListReceivers(allReceivers);
        final int numReceivers = resolvedReceivers.length;
        if (!ListenerUtil.mutListener.listen(68)) {
            if ((ListenerUtil.mutListener.listen(60) ? (numReceivers >= 0) : (ListenerUtil.mutListener.listen(59) ? (numReceivers <= 0) : (ListenerUtil.mutListener.listen(58) ? (numReceivers < 0) : (ListenerUtil.mutListener.listen(57) ? (numReceivers != 0) : (ListenerUtil.mutListener.listen(56) ? (numReceivers == 0) : (numReceivers > 0))))))) {
                if (!ListenerUtil.mutListener.listen(61)) {
                    actionHandler.onProgress(100, 100);
                }
                if (!ListenerUtil.mutListener.listen(65)) {
                    {
                        long _loopCounter1 = 0;
                        for (MessageReceiver receiver : resolvedReceivers) {
                            ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                            try {
                                if (!ListenerUtil.mutListener.listen(64)) {
                                    {
                                        long _loopCounter0 = 0;
                                        for (String messageText : messageTexts) {
                                            ListenerUtil.loopListener.listen("_loopCounter0", ++_loopCounter0);
                                            if (!ListenerUtil.mutListener.listen(63)) {
                                                messageService.sendText(messageText, receiver);
                                            }
                                        }
                                    }
                                }
                            } catch (final Exception e) {
                                if (!ListenerUtil.mutListener.listen(62)) {
                                    actionHandler.onError(e.getMessage());
                                }
                                return false;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(66)) {
                    actionHandler.onCompleted();
                }
                if (!ListenerUtil.mutListener.listen(67)) {
                    messageService.sendProfilePicture(resolvedReceivers);
                }
                return true;
            }
        }
        return false;
    }
}
