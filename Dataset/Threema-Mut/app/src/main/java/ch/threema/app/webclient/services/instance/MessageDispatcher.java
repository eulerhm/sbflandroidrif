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
package ch.threema.app.webclient.services.instance;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.msgpack.core.MessagePackException;
import org.msgpack.value.Value;
import org.slf4j.Logger;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import androidx.annotation.WorkerThread;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.SendMode;
import ch.threema.app.webclient.converter.MsgpackBuilder;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.exceptions.DispatchException;
import ch.threema.client.MessageQueue;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Dispatch incoming messages to the receivers or send outgoing messages to the webclient.
 *
 * An incoming message is offered to each receiver based on the subtype and message. The process is
 * done once a receiver accepts the message.
 */
@WorkerThread
public class MessageDispatcher {

    @NonNull
    private static final String TAG = "WebClientMessageDispatcher";

    @NonNull
    protected final SessionInstanceService service;

    @NonNull
    protected final Logger logger;

    @NonNull
    protected final MessageQueue messageQueue;

    @NonNull
    protected final LifetimeService lifetimeService;

    @NonNull
    protected final String type;

    @NonNull
    protected final Map<String, MessageReceiver> receivers = new ConcurrentHashMap<>();

    @AnyThread
    public MessageDispatcher(@NonNull final String type, @NonNull final SessionInstanceServiceImpl service, @NonNull final LifetimeService lifetimeService, @NonNull final MessageQueue messageQueue) {
        this.service = service;
        this.logger = service.logger;
        this.lifetimeService = lifetimeService;
        this.type = type;
        this.messageQueue = messageQueue;
    }

    /**
     *  Add a new message receiver.
     */
    @AnyThread
    public void addReceiver(@NonNull final MessageReceiver receiver) {
        if (!ListenerUtil.mutListener.listen(64133)) {
            this.receivers.put(receiver.getSubType(), receiver);
        }
    }

    /**
     *  Dispatch according to subtype and message.
     */
    private void dispatch(@NonNull final String subType, @NonNull final Map<String, Value> message) throws DispatchException, MessagePackException {
        if (!ListenerUtil.mutListener.listen(64134)) {
            if (!this.receivers.containsKey(subType)) {
                throw new DispatchException("No receiver for type '" + this.type + "' with sub type '" + subType + "' found");
            }
        }
        final MessageReceiver receiver = Objects.requireNonNull(this.receivers.get(subType));
        if (!ListenerUtil.mutListener.listen(64135)) {
            receiver.receive(message);
        }
        if (!ListenerUtil.mutListener.listen(64148)) {
            // for a short while to send those messages.
            if (receiver.maybeNeedsConnection()) {
                if (!ListenerUtil.mutListener.listen(64147)) {
                    if ((ListenerUtil.mutListener.listen(64140) ? (this.messageQueue.getQueueSize() >= 0) : (ListenerUtil.mutListener.listen(64139) ? (this.messageQueue.getQueueSize() <= 0) : (ListenerUtil.mutListener.listen(64138) ? (this.messageQueue.getQueueSize() < 0) : (ListenerUtil.mutListener.listen(64137) ? (this.messageQueue.getQueueSize() != 0) : (ListenerUtil.mutListener.listen(64136) ? (this.messageQueue.getQueueSize() == 0) : (this.messageQueue.getQueueSize() > 0))))))) {
                        int timeoutMs = Math.min(30000, 5000 + (ListenerUtil.mutListener.listen(64144) ? (100 % this.messageQueue.getQueueSize()) : (ListenerUtil.mutListener.listen(64143) ? (100 / this.messageQueue.getQueueSize()) : (ListenerUtil.mutListener.listen(64142) ? (100 - this.messageQueue.getQueueSize()) : (ListenerUtil.mutListener.listen(64141) ? (100 + this.messageQueue.getQueueSize()) : (100 * this.messageQueue.getQueueSize()))))));
                        if (!ListenerUtil.mutListener.listen(64145)) {
                            this.lifetimeService.acquireConnection(TAG);
                        }
                        if (!ListenerUtil.mutListener.listen(64146)) {
                            this.lifetimeService.releaseConnectionLinger(TAG, timeoutMs);
                        }
                    }
                }
            }
        }
    }

    /**
     *  Dispatch according to type, subtype and message.
     */
    public boolean dispatch(@NonNull final String type, @NonNull final String subType, @NonNull final Map<String, Value> message) throws DispatchException, MessagePackException {
        // Are we receiving this type?
        if (type.equals(this.type)) {
            if (!ListenerUtil.mutListener.listen(64149)) {
                this.dispatch(subType, message);
            }
            return true;
        } else {
            return false;
        }
    }

    public void send(@NonNull final String subType, @NonNull final MsgpackBuilder data, @NonNull final MsgpackBuilder args) {
        if (!ListenerUtil.mutListener.listen(64150)) {
            this.send(this.type, subType, data, args);
        }
    }

    public void send(@NonNull final String subType, @NonNull final List<MsgpackBuilder> data, @NonNull final MsgpackBuilder args) {
        if (!ListenerUtil.mutListener.listen(64151)) {
            this.send(this.type, subType, data, args);
        }
    }

    public void send(@NonNull final String subType, @NonNull final String data, @NonNull final MsgpackBuilder args) {
        if (!ListenerUtil.mutListener.listen(64152)) {
            this.send(this.type, subType, data, args);
        }
    }

    public void send(@NonNull final String subType, @NonNull final byte[] data, @NonNull final MsgpackBuilder args) {
        if (!ListenerUtil.mutListener.listen(64153)) {
            this.send(this.type, subType, data, args);
        }
    }

    public void send(@NonNull final String type, @NonNull final String subType, @Nullable final MsgpackBuilder data, @Nullable final MsgpackBuilder args) {
        final MsgpackObjectBuilder message = this.createMessage(type, subType, args);
        if (!ListenerUtil.mutListener.listen(64154)) {
            message.maybePut(Protocol.FIELD_DATA, data);
        }
        if (!ListenerUtil.mutListener.listen(64155)) {
            logger.debug("Sending {}/{}", type, subType);
        }
        if (!ListenerUtil.mutListener.listen(64156)) {
            this.send(message);
        }
    }

    public void send(@NonNull final String type, @NonNull final String subType, @Nullable final List<MsgpackBuilder> data, @Nullable final MsgpackBuilder args) {
        final MsgpackObjectBuilder message = this.createMessage(type, subType, args);
        if (!ListenerUtil.mutListener.listen(64157)) {
            message.maybePut(Protocol.FIELD_DATA, data);
        }
        if (!ListenerUtil.mutListener.listen(64158)) {
            logger.debug("Sending {}/{}", type, subType);
        }
        if (!ListenerUtil.mutListener.listen(64159)) {
            this.send(message);
        }
    }

    public void send(@NonNull final String type, @NonNull final String subType, @Nullable final String data, @Nullable final MsgpackBuilder args) {
        final MsgpackObjectBuilder message = this.createMessage(type, subType, args);
        if (!ListenerUtil.mutListener.listen(64160)) {
            message.maybePut(Protocol.FIELD_DATA, data);
        }
        if (!ListenerUtil.mutListener.listen(64161)) {
            logger.debug("Sending {}/{}", type, subType);
        }
        if (!ListenerUtil.mutListener.listen(64162)) {
            this.send(message);
        }
    }

    public void send(@NonNull final String type, @NonNull final String subType, @Nullable final byte[] data, @Nullable final MsgpackBuilder args) {
        final MsgpackObjectBuilder message = this.createMessage(type, subType, args);
        if (!ListenerUtil.mutListener.listen(64163)) {
            message.maybePut(Protocol.FIELD_DATA, data);
        }
        if (!ListenerUtil.mutListener.listen(64164)) {
            logger.debug("Sending {}/{}", type, subType);
        }
        if (!ListenerUtil.mutListener.listen(64165)) {
            this.send(message);
        }
    }

    /**
     *  Send a message to the webclient.
     */
    private void send(@NonNull final MsgpackObjectBuilder message) {
        try {
            if (!ListenerUtil.mutListener.listen(64168)) {
                this.service.send(message.consume(), SendMode.ASYNC);
            }
        } catch (OutOfMemoryError error) {
            if (!ListenerUtil.mutListener.listen(64166)) {
                logger.error("Out of memory while encoding outgoing data channel message");
            }
            if (!ListenerUtil.mutListener.listen(64167)) {
                this.service.stop(DisconnectContext.byUs(DisconnectContext.REASON_OUT_OF_MEMORY));
            }
        }
    }

    /**
     *  Create a new message.
     */
    private MsgpackObjectBuilder createMessage(@NonNull final String type, @NonNull final String subType, @Nullable final MsgpackBuilder args) {
        final MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(64169)) {
            builder.put(Protocol.FIELD_TYPE, type);
        }
        if (!ListenerUtil.mutListener.listen(64170)) {
            builder.put(Protocol.FIELD_SUB_TYPE, subType);
        }
        if (!ListenerUtil.mutListener.listen(64171)) {
            builder.maybePut(Protocol.FIELD_ARGUMENTS, args);
        }
        return builder;
    }
}
