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

import androidx.annotation.AnyThread;
import androidx.annotation.StringDef;
import androidx.annotation.WorkerThread;
import org.msgpack.core.MessagePackException;
import org.msgpack.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import ch.threema.app.services.GroupService;
import ch.threema.app.webclient.Protocol;
import ch.threema.app.webclient.services.instance.MessageDispatcher;
import ch.threema.app.webclient.services.instance.MessageReceiver;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@WorkerThread
public class DeleteGroupHandler extends MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(DeleteGroupHandler.class);

    private static final String TYPE_LEAVE = "leave";

    private static final String TYPE_DELETE = "delete";

    // Error codes
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ Protocol.ERROR_INVALID_GROUP, Protocol.ERROR_ALREADY_LEFT, Protocol.ERROR_NOT_ALLOWED, Protocol.ERROR_BAD_REQUEST })
    private @interface ErrorCode {
    }

    private final MessageDispatcher responseDispatcher;

    private final GroupService groupService;

    @AnyThread
    public DeleteGroupHandler(MessageDispatcher responseDispatcher, GroupService groupService) {
        super(Protocol.SUB_TYPE_GROUP);
        this.responseDispatcher = responseDispatcher;
        this.groupService = groupService;
    }

    @Override
    protected void receive(Map<String, Value> message) throws MessagePackException {
        if (!ListenerUtil.mutListener.listen(63256)) {
            logger.debug("Received delete request");
        }
        final Map<String, Value> args = this.getArguments(message, false, new String[] { Protocol.ARGUMENT_RECEIVER_ID, Protocol.ARGUMENT_DELETE_TYPE, Protocol.ARGUMENT_TEMPORARY_ID });
        // Get temporary ID
        final String temporaryId = args.get(Protocol.ARGUMENT_TEMPORARY_ID).asStringValue().toString();
        // Load group
        final Integer groupId = Integer.valueOf(args.get(Protocol.ARGUMENT_RECEIVER_ID).asStringValue().toString());
        final GroupModel group = this.groupService.getById(groupId);
        if (!ListenerUtil.mutListener.listen(63260)) {
            if ((ListenerUtil.mutListener.listen(63257) ? (group == null && group.isDeleted()) : (group == null || group.isDeleted()))) {
                if (!ListenerUtil.mutListener.listen(63258)) {
                    logger.error("invalid group, aborting");
                }
                if (!ListenerUtil.mutListener.listen(63259)) {
                    this.failed(temporaryId, Protocol.ERROR_INVALID_GROUP);
                }
                return;
            }
        }
        final boolean groupLeft = !this.groupService.isGroupMember(group);
        if (!ListenerUtil.mutListener.listen(63276)) {
            // There are two delete types: Either we want to delete the group, or just leave it.
            switch(args.get(Protocol.ARGUMENT_DELETE_TYPE).asStringValue().toString()) {
                case TYPE_LEAVE:
                    if (!ListenerUtil.mutListener.listen(63263)) {
                        if (groupLeft) {
                            if (!ListenerUtil.mutListener.listen(63261)) {
                                logger.error("group already left");
                            }
                            if (!ListenerUtil.mutListener.listen(63262)) {
                                this.failed(temporaryId, Protocol.ERROR_ALREADY_LEFT);
                            }
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(63264)) {
                        this.groupService.leaveGroup(group);
                    }
                    if (!ListenerUtil.mutListener.listen(63265)) {
                        this.success(temporaryId);
                    }
                    return;
                case TYPE_DELETE:
                    if (!ListenerUtil.mutListener.listen(63273)) {
                        if (!groupLeft) {
                            if (!ListenerUtil.mutListener.listen(63270)) {
                                // Allowed only for administrators
                                if (!this.groupService.isGroupOwner(group)) {
                                    if (!ListenerUtil.mutListener.listen(63268)) {
                                        logger.error("you are not the administrator of this group");
                                    }
                                    if (!ListenerUtil.mutListener.listen(63269)) {
                                        this.failed(temporaryId, Protocol.ERROR_NOT_ALLOWED);
                                    }
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(63271)) {
                                this.groupService.removeAllMembersAndLeave(group);
                            }
                            if (!ListenerUtil.mutListener.listen(63272)) {
                                this.success(temporaryId);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(63266)) {
                                // Allow to remove left groups!
                                this.groupService.remove(group);
                            }
                            if (!ListenerUtil.mutListener.listen(63267)) {
                                this.success(temporaryId);
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(63274)) {
                        logger.error("invalid delete type argument");
                    }
                    if (!ListenerUtil.mutListener.listen(63275)) {
                        this.failed(temporaryId, Protocol.ERROR_BAD_REQUEST);
                    }
            }
        }
    }

    /**
     *  Respond with success true.
     */
    private void success(String temporaryId) {
        if (!ListenerUtil.mutListener.listen(63277)) {
            logger.debug("Respond with leave group success");
        }
        if (!ListenerUtil.mutListener.listen(63278)) {
            this.sendConfirmActionSuccess(this.responseDispatcher, temporaryId);
        }
    }

    /**
     *  Respond with an error code.
     */
    private void failed(String temporaryId, @ErrorCode String errorCode) {
        if (!ListenerUtil.mutListener.listen(63279)) {
            logger.warn("Respond with modify group failed ({})", errorCode);
        }
        if (!ListenerUtil.mutListener.listen(63280)) {
            this.sendConfirmActionFailure(this.responseDispatcher, temporaryId, errorCode);
        }
    }

    @Override
    protected boolean maybeNeedsConnection() {
        return true;
    }
}
