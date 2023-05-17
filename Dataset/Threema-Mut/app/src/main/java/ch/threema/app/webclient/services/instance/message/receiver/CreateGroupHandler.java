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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.msgpack.core.MessagePackException;
import org.msgpack.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import androidx.annotation.AnyThread;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;
import ch.threema.app.dialogs.ContactEditDialog;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.app.services.GroupService;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.converter.Group;
import ch.threema.app.webclient.converter.MsgpackObjectBuilder;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class CreateGroupHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(CreateGroupHandler.class);

    private final MessageDispatcher dispatcher;

    private final GroupService groupService;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ERROR_DISABLED_BY_POLICY, Protocol.ERROR_BAD_REQUEST, Protocol.ERROR_VALUE_TOO_LONG, Protocol.ERROR_INTERNAL })
    private @interface ErrorCode {
    }

    @AnyThread
    public CreateGroupHandler(MessageDispatcher dispatcher, GroupService groupService) {
        super(Protocol.SUB_TYPE_GROUP);
        this.dispatcher = dispatcher;
        this.groupService = groupService;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63204)) {
            logger.debug("Received create group create");
        }
        final Map<String, Value> args = this.getArguments(message, false, new String[] { Protocol.ARGUMENT_TEMPORARY_ID });
        final Map<String, Value> data = this.getData(message, false);
        final String temporaryId = args.get(Protocol.ARGUMENT_TEMPORARY_ID).asStringValue().toString();
        if (!ListenerUtil.mutListener.listen(63207)) {
            // Parse members
            if (!data.containsKey(Protocol.ARGUMENT_MEMBERS)) {
                if (!ListenerUtil.mutListener.listen(63205)) {
                    logger.error("Invalid request, members not set");
                }
                if (!ListenerUtil.mutListener.listen(63206)) {
                    this.failed(temporaryId, Protocol.ERROR_BAD_REQUEST);
                }
                return;
            }
        }
        final List<Value> members = data.get(Protocol.ARGUMENT_MEMBERS).asArrayValue().list();
        final String[] identities = new String[members.size()];
        if (!ListenerUtil.mutListener.listen(63214)) {
            {
                long _loopCounter766 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(63213) ? (n >= members.size()) : (ListenerUtil.mutListener.listen(63212) ? (n <= members.size()) : (ListenerUtil.mutListener.listen(63211) ? (n > members.size()) : (ListenerUtil.mutListener.listen(63210) ? (n != members.size()) : (ListenerUtil.mutListener.listen(63209) ? (n == members.size()) : (n < members.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter766", ++_loopCounter766);
                    if (!ListenerUtil.mutListener.listen(63208)) {
                        identities[n] = members.get(n).asStringValue().toString();
                    }
                }
            }
        }
        // Parse group name
        String name = null;
        if (!ListenerUtil.mutListener.listen(63224)) {
            if ((ListenerUtil.mutListener.listen(63215) ? (data.containsKey(Protocol.ARGUMENT_NAME) || !data.get(Protocol.ARGUMENT_NAME).isNilValue()) : (data.containsKey(Protocol.ARGUMENT_NAME) && !data.get(Protocol.ARGUMENT_NAME).isNilValue()))) {
                if (!ListenerUtil.mutListener.listen(63216)) {
                    name = data.get(Protocol.ARGUMENT_NAME).asStringValue().toString();
                }
                if (!ListenerUtil.mutListener.listen(63223)) {
                    if ((ListenerUtil.mutListener.listen(63221) ? (name.getBytes(StandardCharsets.UTF_8).length >= Protocol.LIMIT_BYTES_GROUP_NAME) : (ListenerUtil.mutListener.listen(63220) ? (name.getBytes(StandardCharsets.UTF_8).length <= Protocol.LIMIT_BYTES_GROUP_NAME) : (ListenerUtil.mutListener.listen(63219) ? (name.getBytes(StandardCharsets.UTF_8).length < Protocol.LIMIT_BYTES_GROUP_NAME) : (ListenerUtil.mutListener.listen(63218) ? (name.getBytes(StandardCharsets.UTF_8).length != Protocol.LIMIT_BYTES_GROUP_NAME) : (ListenerUtil.mutListener.listen(63217) ? (name.getBytes(StandardCharsets.UTF_8).length == Protocol.LIMIT_BYTES_GROUP_NAME) : (name.getBytes(StandardCharsets.UTF_8).length > Protocol.LIMIT_BYTES_GROUP_NAME))))))) {
                        if (!ListenerUtil.mutListener.listen(63222)) {
                            this.failed(temporaryId, Protocol.ERROR_VALUE_TOO_LONG);
                        }
                        return;
                    }
                }
            }
        }
        // Parse avatar
        Bitmap avatar = null;
        if (!ListenerUtil.mutListener.listen(63234)) {
            if ((ListenerUtil.mutListener.listen(63225) ? (data.containsKey(Protocol.ARGUMENT_AVATAR) || !data.get(Protocol.ARGUMENT_AVATAR).isNilValue()) : (data.containsKey(Protocol.ARGUMENT_AVATAR) && !data.get(Protocol.ARGUMENT_AVATAR).isNilValue()))) {
                byte[] bmp = data.get(Protocol.ARGUMENT_AVATAR).asBinaryValue().asByteArray();
                if (!ListenerUtil.mutListener.listen(63233)) {
                    if ((ListenerUtil.mutListener.listen(63230) ? (bmp.length >= 0) : (ListenerUtil.mutListener.listen(63229) ? (bmp.length <= 0) : (ListenerUtil.mutListener.listen(63228) ? (bmp.length < 0) : (ListenerUtil.mutListener.listen(63227) ? (bmp.length != 0) : (ListenerUtil.mutListener.listen(63226) ? (bmp.length == 0) : (bmp.length > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(63231)) {
                            avatar = BitmapFactory.decodeByteArray(bmp, 0, bmp.length);
                        }
                        if (!ListenerUtil.mutListener.listen(63232)) {
                            // Resize to max allowed size
                            avatar = BitmapUtil.resizeBitmap(avatar, ContactEditDialog.CONTACT_AVATAR_WIDTH_PX, ContactEditDialog.CONTACT_AVATAR_HEIGHT_PX);
                        }
                    }
                }
            }
        }
        // Greate group
        try {
            final GroupModel groupModel = this.groupService.createGroup(name, identities, avatar);
            if (!ListenerUtil.mutListener.listen(63237)) {
                this.success(temporaryId, groupModel);
            }
        } catch (PolicyViolationException e) {
            if (!ListenerUtil.mutListener.listen(63235)) {
                this.failed(temporaryId, Protocol.ERROR_DISABLED_BY_POLICY);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(63236)) {
                this.failed(temporaryId, Protocol.ERROR_INTERNAL);
            }
        }
    }

    private void success(String temporaryId, GroupModel group) {
        if (!ListenerUtil.mutListener.listen(63238)) {
            logger.debug("Respond create group success");
        }
        try {
            if (!ListenerUtil.mutListener.listen(63240)) {
                this.send(this.dispatcher, new MsgpackObjectBuilder().put(Protocol.SUB_TYPE_RECEIVER, Group.convert(group)), new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, true).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
            }
        } catch (ConversionException e) {
            if (!ListenerUtil.mutListener.listen(63239)) {
                logger.error("Exception", e);
            }
        }
    }

    private void failed(String temporaryId, @ErrorCode String errorCode) {
        if (!ListenerUtil.mutListener.listen(63241)) {
            logger.warn("Respond create group failed ({})", errorCode);
        }
        if (!ListenerUtil.mutListener.listen(63242)) {
            this.send(this.dispatcher, (MsgpackObjectBuilder) null, new MsgpackObjectBuilder().put(Protocol.ARGUMENT_SUCCESS, false).put(Protocol.ARGUMENT_ERROR, errorCode).put(Protocol.ARGUMENT_TEMPORARY_ID, temporaryId));
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        return true;
    }
}
