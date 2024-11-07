/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.routines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.utils.ConversationNotificationUtil;
import ch.threema.app.utils.MessageUtil;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReadMessagesRoutine implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ReadMessagesRoutine.class);

    private final List<AbstractMessageModel> messages;

    private final MessageService messageService;

    private NotificationService notificationService;

    private List<OnFinished> onFinished = new ArrayList<>();

    public interface OnFinished {

        void finished(boolean success);
    }

    public ReadMessagesRoutine(List<AbstractMessageModel> messages, MessageService messageService, NotificationService notificationService) {
        this.messages = messages;
        this.messageService = messageService;
        if (!ListenerUtil.mutListener.listen(34547)) {
            this.notificationService = notificationService;
        }
    }

    @Override
    public void run() {
        if (!ListenerUtil.mutListener.listen(34548)) {
            logger.debug("ReadMessagesRoutine.run()");
        }
        boolean success = true;
        if (!ListenerUtil.mutListener.listen(34578)) {
            if ((ListenerUtil.mutListener.listen(34554) ? (this.messages != null || (ListenerUtil.mutListener.listen(34553) ? (this.messages.size() >= 0) : (ListenerUtil.mutListener.listen(34552) ? (this.messages.size() <= 0) : (ListenerUtil.mutListener.listen(34551) ? (this.messages.size() < 0) : (ListenerUtil.mutListener.listen(34550) ? (this.messages.size() != 0) : (ListenerUtil.mutListener.listen(34549) ? (this.messages.size() == 0) : (this.messages.size() > 0))))))) : (this.messages != null && (ListenerUtil.mutListener.listen(34553) ? (this.messages.size() >= 0) : (ListenerUtil.mutListener.listen(34552) ? (this.messages.size() <= 0) : (ListenerUtil.mutListener.listen(34551) ? (this.messages.size() < 0) : (ListenerUtil.mutListener.listen(34550) ? (this.messages.size() != 0) : (ListenerUtil.mutListener.listen(34549) ? (this.messages.size() == 0) : (this.messages.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(34577)) {
                    {
                        long _loopCounter248 = 0;
                        /* It is possible that the list gets modified while we're iterating. In that case,
		   we repeat the operation on exception (to avoid the overhead of copying the whole
		   list before we start).
		 */
                        for (int tries = 0; (ListenerUtil.mutListener.listen(34576) ? (tries >= 10) : (ListenerUtil.mutListener.listen(34575) ? (tries <= 10) : (ListenerUtil.mutListener.listen(34574) ? (tries > 10) : (ListenerUtil.mutListener.listen(34573) ? (tries != 10) : (ListenerUtil.mutListener.listen(34572) ? (tries == 10) : (tries < 10)))))); tries++) {
                            ListenerUtil.loopListener.listen("_loopCounter248", ++_loopCounter248);
                            try {
                                final List<AbstractMessageModel> modifiedMessageModels = new ArrayList<>();
                                if (!ListenerUtil.mutListener.listen(34561)) {
                                    {
                                        long _loopCounter246 = 0;
                                        for (AbstractMessageModel messageModel : this.messages) {
                                            ListenerUtil.loopListener.listen("_loopCounter246", ++_loopCounter246);
                                            if (!ListenerUtil.mutListener.listen(34560)) {
                                                if (MessageUtil.canMarkAsRead(messageModel)) {
                                                    try {
                                                        if (!ListenerUtil.mutListener.listen(34559)) {
                                                            if (this.messageService.markAsRead(messageModel, true)) {
                                                                if (!ListenerUtil.mutListener.listen(34558)) {
                                                                    modifiedMessageModels.add(messageModel);
                                                                }
                                                            }
                                                        }
                                                    } catch (ThreemaException e) {
                                                        if (!ListenerUtil.mutListener.listen(34556)) {
                                                            logger.error("Exception", e);
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(34557)) {
                                                            success = false;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(34571)) {
                                    if ((ListenerUtil.mutListener.listen(34566) ? (modifiedMessageModels.size() >= 0) : (ListenerUtil.mutListener.listen(34565) ? (modifiedMessageModels.size() <= 0) : (ListenerUtil.mutListener.listen(34564) ? (modifiedMessageModels.size() < 0) : (ListenerUtil.mutListener.listen(34563) ? (modifiedMessageModels.size() != 0) : (ListenerUtil.mutListener.listen(34562) ? (modifiedMessageModels.size() == 0) : (modifiedMessageModels.size() > 0))))))) {
                                        // Get notification UIDs of the messages that have just been marked as read
                                        final String[] notificationUids = new String[modifiedMessageModels.size()];
                                        int pos = 0;
                                        if (!ListenerUtil.mutListener.listen(34568)) {
                                            {
                                                long _loopCounter247 = 0;
                                                for (AbstractMessageModel m : modifiedMessageModels) {
                                                    ListenerUtil.loopListener.listen("_loopCounter247", ++_loopCounter247);
                                                    if (!ListenerUtil.mutListener.listen(34567)) {
                                                        notificationUids[pos++] = ConversationNotificationUtil.getUid(m);
                                                    }
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(34569)) {
                                            // Notify listeners
                                            ListenerManager.messageListeners.handle(listener -> listener.onModified(modifiedMessageModels));
                                        }
                                        if (!ListenerUtil.mutListener.listen(34570)) {
                                            // Cancel notifications
                                            this.notificationService.cancelConversationNotification(notificationUids);
                                        }
                                    }
                                }
                                break;
                            } catch (ConcurrentModificationException e) {
                                if (!ListenerUtil.mutListener.listen(34555)) {
                                    logger.error("Exception", e);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(34580)) {
            {
                long _loopCounter249 = 0;
                for (OnFinished f : this.onFinished) {
                    ListenerUtil.loopListener.listen("_loopCounter249", ++_loopCounter249);
                    if (!ListenerUtil.mutListener.listen(34579)) {
                        f.finished(success);
                    }
                }
            }
        }
    }

    public ReadMessagesRoutine addOnFinished(OnFinished onFinished) {
        if (!ListenerUtil.mutListener.listen(34581)) {
            this.onFinished.add(onFinished);
        }
        return this;
    }

    public void removeOnFinished(OnFinished onFinished) {
        if (!ListenerUtil.mutListener.listen(34582)) {
            this.onFinished.remove(onFinished);
        }
    }
}
