/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import androidx.annotation.AnyThread;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.DistributionListMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.services.ConversationService;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.ConversationModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class CleanReceiverConversationRequestHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(CleanReceiverConversationRequestHandler.class);

    private final MessageDispatcher responseDispatcher;

    private final ConversationService conversationService;

    // Error codes
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ERROR_INVALID_IDENTITY, Protocol.ERROR_BAD_REQUEST, Protocol.ERROR_INTERNAL })
    private @interface ErrorCode {
    }

    @AnyThread
    public CleanReceiverConversationRequestHandler(MessageDispatcher responseDispatcher, ConversationService conversationService) {
        super(Protocol.SUB_TYPE_CLEAN_RECEIVER_CONVERSATION);
        this.responseDispatcher = responseDispatcher;
        this.conversationService = conversationService;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63101)) {
            logger.debug("Received clean receiver request");
        }
        final Map<String, Value> args = this.getArguments(message, false, new String[] { Protocol.ARGUMENT_TEMPORARY_ID });
        // Get temporary ID
        final String temporaryId = args.get(Protocol.ARGUMENT_TEMPORARY_ID).asStringValue().toString();
        // Get receiver
        final ch.threema.app.messagereceiver.MessageReceiver receiver;
        try {
            receiver = this.getReceiver(args);
        } catch (ConversionException | MessagePackException e) {
            if (!ListenerUtil.mutListener.listen(63102)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(63103)) {
                this.failed(temporaryId, Protocol.ERROR_BAD_REQUEST);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(63106)) {
            if (receiver == null) {
                if (!ListenerUtil.mutListener.listen(63104)) {
                    logger.error("invalid receiver");
                }
                if (!ListenerUtil.mutListener.listen(63105)) {
                    this.failed(temporaryId, Protocol.ERROR_INVALID_IDENTITY);
                }
                return;
            }
        }
        try {
            ConversationModel conversationModel = null;
            if (!ListenerUtil.mutListener.listen(63112)) {
                switch(receiver.getType()) {
                    case ContactMessageReceiver.Type_CONTACT:
                        if (!ListenerUtil.mutListener.listen(63109)) {
                            conversationModel = this.conversationService.refresh(((ContactMessageReceiver) receiver).getContact());
                        }
                        break;
                    case ContactMessageReceiver.Type_GROUP:
                        if (!ListenerUtil.mutListener.listen(63110)) {
                            conversationModel = this.conversationService.refresh(((GroupMessageReceiver) receiver).getGroup());
                        }
                        break;
                    case ContactMessageReceiver.Type_DISTRIBUTION_LIST:
                        if (!ListenerUtil.mutListener.listen(63111)) {
                            conversationModel = this.conversationService.refresh(((DistributionListMessageReceiver) receiver).getDistributionList());
                        }
                        break;
                }
            }
            if (!ListenerUtil.mutListener.listen(63113)) {
                if (conversationModel == null) {
                    throw new ThreemaException("invalid conversation/receiver");
                }
            }
            if (!ListenerUtil.mutListener.listen(63114)) {
                this.conversationService.clear(conversationModel);
            }
            if (!ListenerUtil.mutListener.listen(63115)) {
                this.success(temporaryId);
            }
        } catch (Exception x) {
            if (!ListenerUtil.mutListener.listen(63107)) {
                logger.error("Exception", x);
            }
            if (!ListenerUtil.mutListener.listen(63108)) {
                this.failed(temporaryId, Protocol.ERROR_INTERNAL);
            }
        }
    }

    private void success(String temporaryId) {
        if (!ListenerUtil.mutListener.listen(63116)) {
            logger.debug("Respond with clean receiver success");
        }
        if (!ListenerUtil.mutListener.listen(63117)) {
            this.sendConfirmActionSuccess(this.responseDispatcher, temporaryId);
        }
    }

    private void failed(String temporaryId, @ErrorCode String errorCode) {
        if (!ListenerUtil.mutListener.listen(63118)) {
            logger.warn("Respond with clean receiver failed ({})", errorCode);
        }
        if (!ListenerUtil.mutListener.listen(63119)) {
            this.sendConfirmActionFailure(this.responseDispatcher, temporaryId, errorCode);
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        return false;
    }
}
