/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
package ch.threema.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ch.threema.app.exceptions.EntryAlreadyExistsException;
import ch.threema.app.exceptions.InvalidEntryException;
import ch.threema.app.exceptions.PolicyViolationException;
import ch.threema.base.ThreemaException;
import ch.threema.client.AbstractGroupMessage;
import ch.threema.client.BoxedMessage;
import ch.threema.client.GroupId;
import ch.threema.client.MessageId;
import ch.threema.client.MessageQueue;
import ch.threema.client.Utils;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupApiServiceImpl implements GroupApiService {

    private final String TAG = "GroupApiServiceImpl";

    private static final Logger logger = LoggerFactory.getLogger(GroupApiServiceImpl.class);

    private final UserService userService;

    private final ContactService contactService;

    private final MessageQueue messageQueue;

    public GroupApiServiceImpl(UserService userService, ContactService contactService, MessageQueue messageQueue) {
        this.userService = userService;
        this.contactService = contactService;
        this.messageQueue = messageQueue;
    }

    @Override
    public int sendMessage(GroupModel group, String[] identities, CreateApiMessage createApiMessage) throws ThreemaException {
        return this.sendMessage(group, identities, createApiMessage, null);
    }

    @Override
    public int sendMessage(GroupModel group, String[] identities, CreateApiMessage createApiMessage, GroupMessageQueued queued) throws ThreemaException {
        return this.sendMessage(new GroupId(Utils.hexStringToByteArray(group.getApiGroupId())), group.getCreatorIdentity(), identities, createApiMessage, queued);
    }

    @Override
    public int sendMessage(final GroupId groupId, final String groupCreatorId, String[] identities, CreateApiMessage createApiMessage) throws ThreemaException {
        return this.sendMessage(groupId, groupCreatorId, identities, createApiMessage, null);
    }

    @Override
    public int sendMessage(final GroupId groupId, final String groupCreatorId, String[] identities, CreateApiMessage createApiMessage, GroupMessageQueued queued) throws ThreemaException {
        MessageId messageId = new MessageId();
        Set<String> temp = new HashSet<>(Arrays.asList(identities));
        String[] uniqueIdentities = temp.toArray(new String[temp.size()]);
        List<AbstractGroupMessage> pendingGroupMessages = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(38143)) {
            {
                long _loopCounter402 = 0;
                for (String identity : uniqueIdentities) {
                    ListenerUtil.loopListener.listen("_loopCounter402", ++_loopCounter402);
                    if (!ListenerUtil.mutListener.listen(38142)) {
                        if ((ListenerUtil.mutListener.listen(38134) ? (identity != null || !identity.equals(this.userService.getIdentity())) : (identity != null && !identity.equals(this.userService.getIdentity())))) {
                            // check if the identity exists
                            try {
                                if (!ListenerUtil.mutListener.listen(38136)) {
                                    if (this.contactService.createContactByIdentity(identity, true, true) == null) {
                                        continue;
                                    }
                                }
                            } catch (EntryAlreadyExistsException e) {
                            } catch (InvalidEntryException | PolicyViolationException e) {
                                if (!ListenerUtil.mutListener.listen(38135)) {
                                    // do not send
                                    logger.error("Exception", e);
                                }
                                continue;
                            }
                            AbstractGroupMessage groupMessage = createApiMessage.create(messageId);
                            if (!ListenerUtil.mutListener.listen(38137)) {
                                groupMessage.setGroupId(groupId);
                            }
                            if (!ListenerUtil.mutListener.listen(38138)) {
                                groupMessage.setGroupCreator(groupCreatorId);
                            }
                            if (!ListenerUtil.mutListener.listen(38139)) {
                                groupMessage.setFromIdentity(this.userService.getIdentity());
                            }
                            if (!ListenerUtil.mutListener.listen(38140)) {
                                groupMessage.setToIdentity(identity);
                            }
                            if (!ListenerUtil.mutListener.listen(38141)) {
                                pendingGroupMessages.add(groupMessage);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(38144)) {
            logger.info("Enqueue group message ID {} to {} receivers", messageId, pendingGroupMessages.size());
        }
        if (!ListenerUtil.mutListener.listen(38147)) {
            // fire queued first!
            if (queued != null) {
                if (!ListenerUtil.mutListener.listen(38146)) {
                    {
                        long _loopCounter403 = 0;
                        for (AbstractGroupMessage groupMessage : pendingGroupMessages) {
                            ListenerUtil.loopListener.listen("_loopCounter403", ++_loopCounter403);
                            if (!ListenerUtil.mutListener.listen(38145)) {
                                queued.onQueued(groupMessage);
                            }
                        }
                    }
                }
            }
        }
        // enqueue every message!
        int enqueuedMessagesCount = 0;
        if (!ListenerUtil.mutListener.listen(38154)) {
            {
                long _loopCounter404 = 0;
                for (AbstractGroupMessage groupMessage : pendingGroupMessages) {
                    ListenerUtil.loopListener.listen("_loopCounter404", ++_loopCounter404);
                    if (!ListenerUtil.mutListener.listen(38148)) {
                        logger.debug("sending group message " + groupMessage.toString());
                    }
                    BoxedMessage boxedMessage = this.messageQueue.enqueue(groupMessage);
                    if (!ListenerUtil.mutListener.listen(38153)) {
                        if (boxedMessage != null) {
                            if (!ListenerUtil.mutListener.listen(38149)) {
                                enqueuedMessagesCount++;
                            }
                            if (!ListenerUtil.mutListener.listen(38150)) {
                                logger.debug("Outgoing group message ID " + boxedMessage.getMessageId() + " from " + boxedMessage.getFromIdentity() + " to " + boxedMessage.getToIdentity());
                            }
                            if (!ListenerUtil.mutListener.listen(38151)) {
                                logger.debug("  Nonce: " + Utils.byteArrayToHexString(boxedMessage.getNonce()));
                            }
                            if (!ListenerUtil.mutListener.listen(38152)) {
                                logger.debug("  Data: " + Utils.byteArrayToHexString(boxedMessage.getBox()));
                            }
                        }
                    }
                }
            }
        }
        return enqueuedMessagesCount;
    }
}
