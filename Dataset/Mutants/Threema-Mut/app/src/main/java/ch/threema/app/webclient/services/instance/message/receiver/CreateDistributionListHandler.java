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

import androidx.annotation.AnyThread;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;
import org.msgpack.core.MessagePackException;
import org.msgpack.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.DistributionList;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.storage.models.DistributionListModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class CreateDistributionListHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(CreateDistributionListHandler.class);

    private final MessageDispatcher dispatcher;

    private final DistributionListService distributionListService;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ERROR_BAD_REQUEST, Protocol.ERROR_VALUE_TOO_LONG, Protocol.ERROR_INTERNAL })
    private @interface ErrorCode {
    }

    @AnyThread
    public CreateDistributionListHandler(MessageDispatcher dispatcher, DistributionListService distributionListService) {
        super(Protocol.SUB_TYPE_DISTRIBUTION_LIST);
        this.dispatcher = dispatcher;
        this.distributionListService = distributionListService;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63176)) {
            logger.debug("Received create distribution list create");
        }
        final Map<String, Value> args = this.getArguments(message, false, new String[] { Protocol.ARGUMENT_TEMPORARY_ID });
        final Map<String, Value> data = this.getData(message, false);
        final String temporaryId = args.get(Protocol.ARGUMENT_TEMPORARY_ID).asStringValue().toString();
        if (!ListenerUtil.mutListener.listen(63179)) {
            // Parse members
            if (!data.containsKey(Protocol.ARGUMENT_MEMBERS)) {
                if (!ListenerUtil.mutListener.listen(63177)) {
                    logger.error("Invalid request, members not set");
                }
                if (!ListenerUtil.mutListener.listen(63178)) {
                    this.failed(temporaryId, Protocol.ERROR_BAD_REQUEST);
                }
                return;
            }
        }
        final List<Value> members = data.get(Protocol.ARGUMENT_MEMBERS).asArrayValue().list();
        final String[] identities = new String[members.size()];
        if (!ListenerUtil.mutListener.listen(63186)) {
            {
                long _loopCounter765 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(63185) ? (n >= members.size()) : (ListenerUtil.mutListener.listen(63184) ? (n <= members.size()) : (ListenerUtil.mutListener.listen(63183) ? (n > members.size()) : (ListenerUtil.mutListener.listen(63182) ? (n != members.size()) : (ListenerUtil.mutListener.listen(63181) ? (n == members.size()) : (n < members.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter765", ++_loopCounter765);
                    if (!ListenerUtil.mutListener.listen(63180)) {
                        identities[n] = members.get(n).asStringValue().toString();
                    }
                }
            }
        }
        // Parse distribution list name
        String name = null;
        if (!ListenerUtil.mutListener.listen(63196)) {
            if ((ListenerUtil.mutListener.listen(63187) ? (data.containsKey(Protocol.ARGUMENT_NAME) || !data.get(Protocol.ARGUMENT_NAME).isNilValue()) : (data.containsKey(Protocol.ARGUMENT_NAME) && !data.get(Protocol.ARGUMENT_NAME).isNilValue()))) {
                if (!ListenerUtil.mutListener.listen(63188)) {
                    name = data.get(Protocol.ARGUMENT_NAME).asStringValue().toString();
                }
                if (!ListenerUtil.mutListener.listen(63195)) {
                    if ((ListenerUtil.mutListener.listen(63193) ? (name.getBytes(StandardCharsets.UTF_8).length >= Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63192) ? (name.getBytes(StandardCharsets.UTF_8).length <= Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63191) ? (name.getBytes(StandardCharsets.UTF_8).length < Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63190) ? (name.getBytes(StandardCharsets.UTF_8).length != Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63189) ? (name.getBytes(StandardCharsets.UTF_8).length == Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (name.getBytes(StandardCharsets.UTF_8).length > Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME))))))) {
                        if (!ListenerUtil.mutListener.listen(63194)) {
                            this.failed(temporaryId, Protocol.ERROR_VALUE_TOO_LONG);
                        }
                        return;
                    }
                }
            }
        }
        try {
            final DistributionListModel distributionListModel = this.distributionListService.createDistributionList(name, identities);
            if (!ListenerUtil.mutListener.listen(63198)) {
                this.success(temporaryId, distributionListModel);
            }
        } catch (Exception e1) {
            if (!ListenerUtil.mutListener.listen(63197)) {
                this.failed(temporaryId, Protocol.ERROR_INTERNAL);
            }
        }
    }

    private void success(String temporaryId, DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(63199)) {
            logger.debug("Respond create distribution list success");
        }
        try {
            if (!ListenerUtil.mutListener.listen(63201)) {
                this.send(this.dispatcher, new MsgpackObjectBuilder().put(Protocol.SUB_TYPE_RECEIVER, DistributionList.convert(distributionListModel)), new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, true).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(63200)) {
                logger.error("Exception", e);
            }
        }
    }

    private void failed(String temporaryId, @ErrorCode String errorCode) {
        if (!ListenerUtil.mutListener.listen(63202)) {
            logger.warn("Respond create distribution list failed ({})", errorCode);
        }
        if (!ListenerUtil.mutListener.listen(63203)) {
            this.send(this.dispatcher, (MsgpackObjectBuilder) null, new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, false).put(Protocol.ARGUMENT_ERROR, errorCode).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        return false;
    }
}
