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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import androidx.annotation.AnyThread;
import androidx.annotation.WorkerThread;
import ch.threema.app.BuildConfig;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.listeners.ServerMessageListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.LifetimeService;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.QuoteUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.client.ProtocolDefines;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ServerMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class TextMessageCreateHandler extends MessageCreateHandler {

    private static final Logger logger = LoggerFactory.getLogger(TextMessageCreateHandler.class);

    private static final String FIELD_TEXT = "text";

    private static final String FIELD_QUOTE = "quote";

    private static final String FIELD_QUOTE_IDENTITY = "identity";

    private static final String FIELD_QUOTE_TEXT = "text";

    @AnyThread
    public TextMessageCreateHandler(MessageDispatcher dispatcher, MessageService messageService, LifetimeService lifetimeService, IdListService blackListService) {
        super(Protocol.SUB_TYPE_TEXT_MESSAGE, dispatcher, messageService, lifetimeService, blackListService);
    }

    @Override
    protected AbstractMessageModel handle(List<ch.threema.app.messagereceiver.MessageReceiver> receivers, Map<String, Value> message) throws Exception {
        final Map<String, Value> messageData = this.getData(message, false, new String[] { TextMessageCreateHandler.FIELD_TEXT });
        // Get text
        String text = messageData.get(FIELD_TEXT).asStringValue().asString();
        if (!ListenerUtil.mutListener.listen(63607)) {
            // Handle quoted messages
            if (messageData.containsKey(FIELD_QUOTE)) {
                try {
                    final Map<String, Value> quoteMap = this.getMap(messageData, FIELD_QUOTE, false, new String[] { FIELD_QUOTE_IDENTITY, FIELD_QUOTE_TEXT });
                    if (!ListenerUtil.mutListener.listen(63606)) {
                        // TODO: provide AbstractMessageModel of quoted message
                        text = QuoteUtil.quote(text, quoteMap.get(FIELD_QUOTE_IDENTITY).toString(), quoteMap.get(FIELD_TEXT).toString(), null);
                    }
                } catch (MessagePackException x) {
                    if (!ListenerUtil.mutListener.listen(63605)) {
                        logger.error("Ignoring MessagePackException when handling quote", x);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(63613)) {
            // Validate message length
            if ((ListenerUtil.mutListener.listen(63612) ? (text.getBytes(StandardCharsets.UTF_8).length >= ProtocolDefines.MAX_TEXT_MESSAGE_LEN) : (ListenerUtil.mutListener.listen(63611) ? (text.getBytes(StandardCharsets.UTF_8).length <= ProtocolDefines.MAX_TEXT_MESSAGE_LEN) : (ListenerUtil.mutListener.listen(63610) ? (text.getBytes(StandardCharsets.UTF_8).length < ProtocolDefines.MAX_TEXT_MESSAGE_LEN) : (ListenerUtil.mutListener.listen(63609) ? (text.getBytes(StandardCharsets.UTF_8).length != ProtocolDefines.MAX_TEXT_MESSAGE_LEN) : (ListenerUtil.mutListener.listen(63608) ? (text.getBytes(StandardCharsets.UTF_8).length == ProtocolDefines.MAX_TEXT_MESSAGE_LEN) : (text.getBytes(StandardCharsets.UTF_8).length > ProtocolDefines.MAX_TEXT_MESSAGE_LEN))))))) {
                throw new MessageCreateHandler.MessageValidationException(Protocol.ERROR_VALUE_TOO_LONG, true);
            }
        }
        AbstractMessageModel firstMessageModel = null;
        if (!ListenerUtil.mutListener.listen(63652)) {
            {
                long _loopCounter770 = 0;
                for (ch.threema.app.messagereceiver.MessageReceiver receiver : receivers) {
                    ListenerUtil.loopListener.listen("_loopCounter770", ++_loopCounter770);
                    AbstractMessageModel model = this.messageService.sendText(text, receiver);
                    if (!ListenerUtil.mutListener.listen(63615)) {
                        if (firstMessageModel == null) {
                            if (!ListenerUtil.mutListener.listen(63614)) {
                                firstMessageModel = model;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(63651)) {
                        // enabled only in debug version
                        if (BuildConfig.DEBUG) {
                            if (!ListenerUtil.mutListener.listen(63650)) {
                                if (receiver instanceof ContactMessageReceiver) {
                                    if (!ListenerUtil.mutListener.listen(63649)) {
                                        if ((ListenerUtil.mutListener.listen(63616) ? (((ContactMessageReceiver) receiver).getContact().getIdentity().equals(ThreemaApplication.ECHO_USER_IDENTITY) || text.startsWith("alert")) : (((ContactMessageReceiver) receiver).getContact().getIdentity().equals(ThreemaApplication.ECHO_USER_IDENTITY) && text.startsWith("alert")))) {
                                            String[] pieces = text.split("\\s+");
                                            if (!ListenerUtil.mutListener.listen(63648)) {
                                                if ((ListenerUtil.mutListener.listen(63621) ? (pieces.length <= 2) : (ListenerUtil.mutListener.listen(63620) ? (pieces.length > 2) : (ListenerUtil.mutListener.listen(63619) ? (pieces.length < 2) : (ListenerUtil.mutListener.listen(63618) ? (pieces.length != 2) : (ListenerUtil.mutListener.listen(63617) ? (pieces.length == 2) : (pieces.length >= 2))))))) {
                                                    String alertMessageTmp = "";
                                                    if (!ListenerUtil.mutListener.listen(63633)) {
                                                        {
                                                            long _loopCounter769 = 0;
                                                            for (int n = 2; (ListenerUtil.mutListener.listen(63632) ? (n >= pieces.length) : (ListenerUtil.mutListener.listen(63631) ? (n <= pieces.length) : (ListenerUtil.mutListener.listen(63630) ? (n > pieces.length) : (ListenerUtil.mutListener.listen(63629) ? (n != pieces.length) : (ListenerUtil.mutListener.listen(63628) ? (n == pieces.length) : (n < pieces.length)))))); n++) {
                                                                ListenerUtil.loopListener.listen("_loopCounter769", ++_loopCounter769);
                                                                if (!ListenerUtil.mutListener.listen(63627)) {
                                                                    alertMessageTmp += pieces[n] + ((ListenerUtil.mutListener.listen(63626) ? (n >= 2) : (ListenerUtil.mutListener.listen(63625) ? (n <= 2) : (ListenerUtil.mutListener.listen(63624) ? (n < 2) : (ListenerUtil.mutListener.listen(63623) ? (n != 2) : (ListenerUtil.mutListener.listen(63622) ? (n == 2) : (n > 2)))))) ? " " : "");
                                                                }
                                                            }
                                                        }
                                                    }
                                                    final String alertMessage;
                                                    switch(pieces[1]) {
                                                        case "error":
                                                            if ((ListenerUtil.mutListener.listen(63638) ? (alertMessageTmp.length() >= 0) : (ListenerUtil.mutListener.listen(63637) ? (alertMessageTmp.length() <= 0) : (ListenerUtil.mutListener.listen(63636) ? (alertMessageTmp.length() > 0) : (ListenerUtil.mutListener.listen(63635) ? (alertMessageTmp.length() < 0) : (ListenerUtil.mutListener.listen(63634) ? (alertMessageTmp.length() != 0) : (alertMessageTmp.length() == 0))))))) {
                                                                alertMessage = "test error message";
                                                            } else {
                                                                alertMessage = alertMessageTmp;
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(63640)) {
                                                                ListenerManager.serverMessageListeners.handle(new ListenerManager.HandleListener<ServerMessageListener>() {

                                                                    @Override
                                                                    public void handle(ServerMessageListener listener) {
                                                                        if (!ListenerUtil.mutListener.listen(63639)) {
                                                                            listener.onError(new ServerMessageModel(alertMessage, ServerMessageModel.Type.ERROR));
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                            break;
                                                        case "alert":
                                                            if ((ListenerUtil.mutListener.listen(63645) ? (alertMessageTmp.length() >= 0) : (ListenerUtil.mutListener.listen(63644) ? (alertMessageTmp.length() <= 0) : (ListenerUtil.mutListener.listen(63643) ? (alertMessageTmp.length() > 0) : (ListenerUtil.mutListener.listen(63642) ? (alertMessageTmp.length() < 0) : (ListenerUtil.mutListener.listen(63641) ? (alertMessageTmp.length() != 0) : (alertMessageTmp.length() == 0))))))) {
                                                                alertMessage = "test alert message";
                                                            } else {
                                                                alertMessage = alertMessageTmp;
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(63647)) {
                                                                ListenerManager.serverMessageListeners.handle(new ListenerManager.HandleListener<ServerMessageListener>() {

                                                                    @Override
                                                                    public void handle(ServerMessageListener listener) {
                                                                        if (!ListenerUtil.mutListener.listen(63646)) {
                                                                            listener.onError(new ServerMessageModel(alertMessage, ServerMessageModel.Type.ALERT));
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return firstMessageModel;
    }
}
