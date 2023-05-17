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
import java.util.List;
import java.util.Map;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.DistributionList;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.converter.Receiver;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.storage.models.DistributionListModel;
import static java.nio.charset.StandardCharsets.UTF_8;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class ModifyDistributionListHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(ModifyDistributionListHandler.class);

    private final MessageDispatcher dispatcher;

    private final DistributionListService distributionListService;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ERROR_INVALID_DISTRIBUTION_LIST, Protocol.ERROR_NO_MEMBERS, Protocol.ERROR_BAD_REQUEST, Protocol.ERROR_VALUE_TOO_LONG, Protocol.ERROR_INTERNAL })
    private @interface ErrorCode {
    }

    @AnyThread
    public ModifyDistributionListHandler(MessageDispatcher dispatcher, DistributionListService distributionListService) {
        super(Protocol.SUB_TYPE_DISTRIBUTION_LIST);
        this.dispatcher = dispatcher;
        this.distributionListService = distributionListService;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63459)) {
            logger.debug("Received modify distribution list");
        }
        final Map<String, Value> args = this.getArguments(message, false, new String[] { Protocol.ARGUMENT_TEMPORARY_ID });
        final String temporaryId = args.get(Protocol.ARGUMENT_TEMPORARY_ID).asStringValue().toString();
        if (!ListenerUtil.mutListener.listen(63462)) {
            // Get args
            if (!args.containsKey(Receiver.ID)) {
                if (!ListenerUtil.mutListener.listen(63460)) {
                    logger.error("Invalid distribution list update request, id not set");
                }
                if (!ListenerUtil.mutListener.listen(63461)) {
                    this.failed(temporaryId, Protocol.ERROR_BAD_REQUEST);
                }
                return;
            }
        }
        final int distributionListId = Integer.parseInt(args.get(Receiver.ID).asStringValue().toString());
        // Get distribution list
        final DistributionListModel distributionListModel = this.distributionListService.getById(distributionListId);
        if (!ListenerUtil.mutListener.listen(63464)) {
            if (distributionListModel == null) {
                if (!ListenerUtil.mutListener.listen(63463)) {
                    this.failed(temporaryId, Protocol.ERROR_INVALID_DISTRIBUTION_LIST);
                }
                return;
            }
        }
        // Process data
        final Map<String, Value> data = this.getData(message, false);
        if (!ListenerUtil.mutListener.listen(63466)) {
            if (!data.containsKey(Protocol.ARGUMENT_MEMBERS)) {
                if (!ListenerUtil.mutListener.listen(63465)) {
                    this.failed(temporaryId, Protocol.ERROR_NO_MEMBERS);
                }
                return;
            }
        }
        // Update members
        final List<Value> members = data.get(Protocol.ARGUMENT_MEMBERS).asArrayValue().list();
        final String[] identities = new String[members.size()];
        if (!ListenerUtil.mutListener.listen(63473)) {
            {
                long _loopCounter767 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(63472) ? (n >= members.size()) : (ListenerUtil.mutListener.listen(63471) ? (n <= members.size()) : (ListenerUtil.mutListener.listen(63470) ? (n > members.size()) : (ListenerUtil.mutListener.listen(63469) ? (n != members.size()) : (ListenerUtil.mutListener.listen(63468) ? (n == members.size()) : (n < members.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter767", ++_loopCounter767);
                    if (!ListenerUtil.mutListener.listen(63467)) {
                        identities[n] = members.get(n).asStringValue().toString();
                    }
                }
            }
        }
        // Update name
        String name = distributionListModel.getName();
        if (!ListenerUtil.mutListener.listen(63482)) {
            if (data.containsKey(Protocol.ARGUMENT_NAME)) {
                if (!ListenerUtil.mutListener.listen(63474)) {
                    name = this.getValueString(data.get(Protocol.ARGUMENT_NAME));
                }
                if (!ListenerUtil.mutListener.listen(63481)) {
                    if ((ListenerUtil.mutListener.listen(63479) ? (name.getBytes(UTF_8).length >= Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63478) ? (name.getBytes(UTF_8).length <= Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63477) ? (name.getBytes(UTF_8).length < Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63476) ? (name.getBytes(UTF_8).length != Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (ListenerUtil.mutListener.listen(63475) ? (name.getBytes(UTF_8).length == Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME) : (name.getBytes(UTF_8).length > Protocol.LIMIT_BYTES_DISTRIBUTION_LIST_NAME))))))) {
                        if (!ListenerUtil.mutListener.listen(63480)) {
                            this.failed(temporaryId, Protocol.ERROR_VALUE_TOO_LONG);
                        }
                        return;
                    }
                }
            }
        }
        // Save changes
        try {
            if (!ListenerUtil.mutListener.listen(63484)) {
                this.distributionListService.updateDistributionList(distributionListModel, name, identities);
            }
            if (!ListenerUtil.mutListener.listen(63485)) {
                this.success(temporaryId, distributionListModel);
            }
        } catch (Exception e1) {
            if (!ListenerUtil.mutListener.listen(63483)) {
                this.failed(temporaryId, Protocol.ERROR_INTERNAL);
            }
        }
    }

    private void success(String temporaryId, DistributionListModel distributionListModel) {
        if (!ListenerUtil.mutListener.listen(63486)) {
            logger.debug("Respond modify distribution list success");
        }
        try {
            if (!ListenerUtil.mutListener.listen(63488)) {
                this.send(this.dispatcher, new MsgpackObjectBuilder().put(Protocol.SUB_TYPE_RECEIVER, DistributionList.convert(distributionListModel)), new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, true).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(63487)) {
                logger.error("Exception", e);
            }
        }
    }

    private void failed(String temporaryId, @ErrorCode String errorCode) {
        if (!ListenerUtil.mutListener.listen(63489)) {
            logger.warn("Respond modify distribution list failed ({})", errorCode);
        }
        if (!ListenerUtil.mutListener.listen(63490)) {
            this.send(this.dispatcher, new MsgpackObjectBuilder().putNull(Protocol.SUB_TYPE_RECEIVER), new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, false).put(Protocol.ARGUMENT_ERROR, errorCode).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        return false;
    }
}
