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
package ch.threema.app.webclient.services.instance.message.updater;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import org.msgpack.core.MessagePackException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.listeners.ConversationListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.services.GroupService;
import ch.threema.app.utils.executor.HandlerExecutor;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.Conversation;
import ch.threema.app.webclient.converter.MsgpackBuilder;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageUpdater;
import ch.threema.storage.models.ConversationModel;
import static ch.threema.app.webclient.Protocol.ARGUMENT_MODE;
import static ch.threema.app.webclient.Protocol.ARGUMENT_MODE_MODIFIED;
import static ch.threema.app.webclient.Protocol.ARGUMENT_MODE_NEW;
import static ch.threema.app.webclient.Protocol.ARGUMENT_MODE_REMOVED;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class ConversationUpdateHandler extends MessageUpdater {

    private static final Logger logger = LoggerFactory.getLogger(ConversationUpdateHandler.class);

    // Handler
    @NonNull
    private final HandlerExecutor handler;

    // Listeners
    private final ConversationListener listener;

    // Dispatchers
    private MessageDispatcher updateDispatcher;

    // Services
    private final ContactService contactService;

    private final GroupService groupService;

    private final DistributionListService distributionListService;

    private final DeadlineListService hiddenChatsListService;

    private final int sessionId;

    @AnyThread
    public ConversationUpdateHandler(@NonNull HandlerExecutor handler, MessageDispatcher updateDispatcher, ContactService contactService, GroupService groupService, DistributionListService distributionListService, DeadlineListService hiddenChatsListService, int sessionId) {
        super(Protocol.SUB_TYPE_CONVERSATION);
        this.handler = handler;
        if (!ListenerUtil.mutListener.listen(63711)) {
            this.updateDispatcher = updateDispatcher;
        }
        this.contactService = contactService;
        this.groupService = groupService;
        this.distributionListService = distributionListService;
        this.hiddenChatsListService = hiddenChatsListService;
        this.listener = new Listener();
        this.sessionId = sessionId;
    }

    @Override
    public void register() {
        if (!ListenerUtil.mutListener.listen(63712)) {
            logger.debug("register({})", this.sessionId);
        }
        if (!ListenerUtil.mutListener.listen(63713)) {
            ListenerManager.conversationListeners.add(this.listener);
        }
    }

    /**
     *  This method can be safely called multiple times without any negative side effects
     */
    @Override
    public void unregister() {
        if (!ListenerUtil.mutListener.listen(63714)) {
            logger.debug("unregister({})", this.sessionId);
        }
        if (!ListenerUtil.mutListener.listen(63715)) {
            ListenerManager.conversationListeners.remove(this.listener);
        }
    }

    private void respond(final ConversationModel model, final String mode) {
        // Respond only if the conversation is not a private chat
        String uniqueId = null;
        if (!ListenerUtil.mutListener.listen(63719)) {
            if (model.isGroupConversation()) {
                if (!ListenerUtil.mutListener.listen(63718)) {
                    uniqueId = this.groupService.getUniqueIdString(model.getGroup());
                }
            } else if (model.isContactConversation()) {
                if (!ListenerUtil.mutListener.listen(63717)) {
                    uniqueId = this.contactService.getUniqueIdString(model.getContact());
                }
            } else if (model.isDistributionListConversation()) {
                if (!ListenerUtil.mutListener.listen(63716)) {
                    uniqueId = this.distributionListService.getUniqueIdString(model.getDistributionList());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(63722)) {
            if (TestUtil.empty(uniqueId)) {
                if (!ListenerUtil.mutListener.listen(63721)) {
                    logger.warn("Cannot send updates, unique ID is null");
                }
                return;
            } else if (this.hiddenChatsListService.has(uniqueId)) {
                if (!ListenerUtil.mutListener.listen(63720)) {
                    logger.debug("Don't send updates for a private conversation");
                }
                return;
            }
        }
        try {
            final MsgpackObjectBuilder args = new MsgpackObjectBuilder().put(ARGUMENT_MODE, mode);
            final MsgpackBuilder data = Conversation.convert(model);
            if (!ListenerUtil.mutListener.listen(63724)) {
                logger.debug("Sending conversation update ({} mode {})", model.getUid(), mode);
            }
            if (!ListenerUtil.mutListener.listen(63725)) {
                send(updateDispatcher, data, args);
            }
        } catch (ConversionException | MessagePackException e) {
            if (!ListenerUtil.mutListener.listen(63723)) {
                logger.error("Exception", e);
            }
        }
    }

    @AnyThread
    private class Listener implements ConversationListener {

        @Override
        @AnyThread
        public void onNew(ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(63727)) {
                // Notify webclient in background thread
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63726)) {
                            ConversationUpdateHandler.this.respond(conversationModel, ARGUMENT_MODE_NEW);
                        }
                    }
                });
            }
        }

        @Override
        @AnyThread
        public void onModified(ConversationModel modifiedConversationModel, Integer oldPosition) {
            if (!ListenerUtil.mutListener.listen(63728)) {
                logger.debug("Move item from: {} to {}", oldPosition, modifiedConversationModel.getPosition());
            }
            if (!ListenerUtil.mutListener.listen(63730)) {
                // Notify webclient in background thread
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63729)) {
                            ConversationUpdateHandler.this.respond(modifiedConversationModel, ARGUMENT_MODE_MODIFIED);
                        }
                    }
                });
            }
        }

        @Override
        @AnyThread
        public void onRemoved(ConversationModel conversationModel) {
            if (!ListenerUtil.mutListener.listen(63732)) {
                // Notify webclient in background thread
                handler.post(new Runnable() {

                    @Override
                    @WorkerThread
                    public void run() {
                        if (!ListenerUtil.mutListener.listen(63731)) {
                            ConversationUpdateHandler.this.respond(conversationModel, ARGUMENT_MODE_REMOVED);
                        }
                    }
                });
            }
        }

        @Override
        @AnyThread
        public void onModifiedAll() {
        }
    }
}
