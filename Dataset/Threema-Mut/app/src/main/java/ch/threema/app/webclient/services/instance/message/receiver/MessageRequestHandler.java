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
package ch.threema.app.webclient.services.instance.message.receiver;

import org.msgpack.core.MessagePackException;
import org.msgpack.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import androidx.annotation.AnyThread;
import androidx.annotation.WorkerThread;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.MessageService;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.Message;
import ch.threema.app.webclient.converter.MsgpackBuilder;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.filters.MessageFilter;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.storage.models.AbstractMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Webclient is requesting messages.
 */
@WorkerThread
public class MessageRequestHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageRequestHandler.class);

    private final MessageDispatcher dispatcher;

    private final MessageService messageService;

    private final DeadlineListService hiddenChatService;

    private final Listener listener;

    @WorkerThread
    public interface Listener {

        void onReceive(ch.threema.app.messagereceiver.MessageReceiver receiver);
    }

    @AnyThread
    public MessageRequestHandler(MessageDispatcher dispatcher, MessageService messageService, DeadlineListService hiddenChatService, Listener listener) {
        super(Protocol.SUB_TYPE_MESSAGES);
        this.dispatcher = dispatcher;
        this.messageService = messageService;
        this.hiddenChatService = hiddenChatService;
        this.listener = listener;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63355)) {
            logger.debug("Received message request");
        }
        Map<String, Value> args = this.getArguments(message, false);
        // Get required arguments
        final String type = args.get(Protocol.ARGUMENT_RECEIVER_TYPE).asStringValue().asString();
        final String receiverId = args.get(Protocol.ARGUMENT_RECEIVER_ID).asStringValue().asString();
        // Get reference id
        Integer refMsgId = null;
        if (!ListenerUtil.mutListener.listen(63357)) {
            if (args.containsKey(Protocol.ARGUMENT_REFERENCE_MSG_ID)) {
                final String refMsgIdStr = args.get(Protocol.ARGUMENT_REFERENCE_MSG_ID).asStringValue().asString();
                if (!ListenerUtil.mutListener.listen(63356)) {
                    refMsgId = Integer.valueOf(refMsgIdStr);
                }
            }
        }
        try {
            ch.threema.app.messagereceiver.MessageReceiver receiver = this.getReceiver(args);
            if (!ListenerUtil.mutListener.listen(63360)) {
                if (this.hiddenChatService.has(receiver.getUniqueIdString())) {
                    if (!ListenerUtil.mutListener.listen(63359)) {
                        // ignore it
                        logger.debug("do not reply with messages on hidden chat");
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(63362)) {
                if (this.listener != null) {
                    if (!ListenerUtil.mutListener.listen(63361)) {
                        this.listener.onReceive(receiver);
                    }
                }
            }
            final MsgpackObjectBuilder responseArgs = new MsgpackObjectBuilder().put(Protocol.ARGUMENT_RECEIVER_TYPE, type).put(Protocol.ARGUMENT_RECEIVER_ID, receiverId).maybePut(Protocol.ARGUMENT_REFERENCE_MSG_ID, String.valueOf(refMsgId), refMsgId != null);
            if (!ListenerUtil.mutListener.listen(63363)) {
                this.respond(receiver, refMsgId, responseArgs);
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(63358)) {
                logger.error("Exception", e);
            }
        }
    }

    private void respond(ch.threema.app.messagereceiver.MessageReceiver receiver, Integer refMsgId, MsgpackObjectBuilder args) {
        // Set refMsgId in filter
        MessageFilter messageFilter = new MessageFilter();
        if (!ListenerUtil.mutListener.listen(63364)) {
            messageFilter.setPageReferenceId(refMsgId);
        }
        try {
            // Get messages
            final List<AbstractMessageModel> messages = messageService.getMessagesForReceiver(receiver, messageFilter, false);
            // Set additional args
            boolean hasMore = (ListenerUtil.mutListener.listen(63377) ? ((ListenerUtil.mutListener.listen(63371) ? (messages != null || (ListenerUtil.mutListener.listen(63370) ? (// if the filter defined with a page size
            messageFilter.getPageSize() >= 0) : (ListenerUtil.mutListener.listen(63369) ? (// if the filter defined with a page size
            messageFilter.getPageSize() <= 0) : (ListenerUtil.mutListener.listen(63368) ? (// if the filter defined with a page size
            messageFilter.getPageSize() < 0) : (ListenerUtil.mutListener.listen(63367) ? (// if the filter defined with a page size
            messageFilter.getPageSize() != 0) : (ListenerUtil.mutListener.listen(63366) ? (// if the filter defined with a page size
            messageFilter.getPageSize() == 0) : (// if the filter defined with a page size
            messageFilter.getPageSize() > 0))))))) : (messages != null && (ListenerUtil.mutListener.listen(63370) ? (// if the filter defined with a page size
            messageFilter.getPageSize() >= 0) : (ListenerUtil.mutListener.listen(63369) ? (// if the filter defined with a page size
            messageFilter.getPageSize() <= 0) : (ListenerUtil.mutListener.listen(63368) ? (// if the filter defined with a page size
            messageFilter.getPageSize() < 0) : (ListenerUtil.mutListener.listen(63367) ? (// if the filter defined with a page size
            messageFilter.getPageSize() != 0) : (ListenerUtil.mutListener.listen(63366) ? (// if the filter defined with a page size
            messageFilter.getPageSize() == 0) : (// if the filter defined with a page size
            messageFilter.getPageSize() > 0)))))))) || (ListenerUtil.mutListener.listen(63376) ? (messages.size() >= messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63375) ? (messages.size() <= messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63374) ? (messages.size() < messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63373) ? (messages.size() != messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63372) ? (messages.size() == messageFilter.getRealPageSize()) : (messages.size() > messageFilter.getRealPageSize()))))))) : ((ListenerUtil.mutListener.listen(63371) ? (messages != null || (ListenerUtil.mutListener.listen(63370) ? (// if the filter defined with a page size
            messageFilter.getPageSize() >= 0) : (ListenerUtil.mutListener.listen(63369) ? (// if the filter defined with a page size
            messageFilter.getPageSize() <= 0) : (ListenerUtil.mutListener.listen(63368) ? (// if the filter defined with a page size
            messageFilter.getPageSize() < 0) : (ListenerUtil.mutListener.listen(63367) ? (// if the filter defined with a page size
            messageFilter.getPageSize() != 0) : (ListenerUtil.mutListener.listen(63366) ? (// if the filter defined with a page size
            messageFilter.getPageSize() == 0) : (// if the filter defined with a page size
            messageFilter.getPageSize() > 0))))))) : (messages != null && (ListenerUtil.mutListener.listen(63370) ? (// if the filter defined with a page size
            messageFilter.getPageSize() >= 0) : (ListenerUtil.mutListener.listen(63369) ? (// if the filter defined with a page size
            messageFilter.getPageSize() <= 0) : (ListenerUtil.mutListener.listen(63368) ? (// if the filter defined with a page size
            messageFilter.getPageSize() < 0) : (ListenerUtil.mutListener.listen(63367) ? (// if the filter defined with a page size
            messageFilter.getPageSize() != 0) : (ListenerUtil.mutListener.listen(63366) ? (// if the filter defined with a page size
            messageFilter.getPageSize() == 0) : (// if the filter defined with a page size
            messageFilter.getPageSize() > 0)))))))) && (ListenerUtil.mutListener.listen(63376) ? (messages.size() >= messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63375) ? (messages.size() <= messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63374) ? (messages.size() < messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63373) ? (messages.size() != messageFilter.getRealPageSize()) : (ListenerUtil.mutListener.listen(63372) ? (messages.size() == messageFilter.getRealPageSize()) : (messages.size() > messageFilter.getRealPageSize()))))))));
            if (!ListenerUtil.mutListener.listen(63378)) {
                args.put("more", hasMore);
            }
            if (!ListenerUtil.mutListener.listen(63385)) {
                if ((ListenerUtil.mutListener.listen(63379) ? (messages != null || hasMore) : (messages != null && hasMore))) {
                    if (!ListenerUtil.mutListener.listen(63384)) {
                        messages.remove((ListenerUtil.mutListener.listen(63383) ? (messages.size() % 1) : (ListenerUtil.mutListener.listen(63382) ? (messages.size() / 1) : (ListenerUtil.mutListener.listen(63381) ? (messages.size() * 1) : (ListenerUtil.mutListener.listen(63380) ? (messages.size() + 1) : (messages.size() - 1))))));
                    }
                }
            }
            // Convert and send messages
            List<MsgpackBuilder> data = Message.convert(messages, receiver.getType(), true);
            if (!ListenerUtil.mutListener.listen(63386)) {
                logger.debug("Sending message response");
            }
            if (!ListenerUtil.mutListener.listen(63387)) {
                this.send(this.dispatcher, data, args);
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(63365)) {
                logger.error("Exception", e);
            }
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        return false;
    }
}
