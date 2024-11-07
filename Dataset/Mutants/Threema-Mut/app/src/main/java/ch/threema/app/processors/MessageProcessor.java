/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
package ch.threema.app.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.WorkerThread;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.ballot.BallotService;
import ch.threema.app.services.ballot.BallotVoteResult;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.MessageDiskSizeUtil;
import ch.threema.app.voip.services.VoipStateService;
import ch.threema.client.AbstractGroupMessage;
import ch.threema.client.AbstractMessage;
import ch.threema.client.BadMessageException;
import ch.threema.client.BoxTextMessage;
import ch.threema.client.BoxedMessage;
import ch.threema.client.ContactDeletePhotoMessage;
import ch.threema.client.ContactRequestPhotoMessage;
import ch.threema.client.ContactSetPhotoMessage;
import ch.threema.client.ContactStoreInterface;
import ch.threema.client.DeliveryReceiptMessage;
import ch.threema.client.GroupCreateMessage;
import ch.threema.client.GroupDeletePhotoMessage;
import ch.threema.client.GroupLeaveMessage;
import ch.threema.client.GroupRenameMessage;
import ch.threema.client.GroupRequestSyncMessage;
import ch.threema.client.GroupSetPhotoMessage;
import ch.threema.client.GroupTextMessage;
import ch.threema.client.IdentityStoreInterface;
import ch.threema.client.MessageId;
import ch.threema.client.MessageProcessorInterface;
import ch.threema.client.MissingPublicKeyException;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.TypingIndicatorMessage;
import ch.threema.client.Utils;
import ch.threema.client.ballot.BallotVoteInterface;
import ch.threema.client.voip.VoipCallAnswerMessage;
import ch.threema.client.voip.VoipCallHangupMessage;
import ch.threema.client.voip.VoipCallOfferMessage;
import ch.threema.client.voip.VoipCallRingingMessage;
import ch.threema.client.voip.VoipICECandidatesMessage;
import ch.threema.client.voip.VoipMessage;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.ServerMessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageProcessor implements MessageProcessorInterface {

    private static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    private static final Logger validationLogger = LoggerFactory.getLogger("Validation");

    private final MessageService messageService;

    private final ContactService contactService;

    private final IdentityStoreInterface identityStore;

    private final ContactStoreInterface contactStore;

    private final PreferenceService preferenceService;

    private final GroupService groupService;

    private final IdListService blackListService;

    private final BallotService ballotService;

    private final VoipStateService voipStateService;

    private FileService fileService;

    private NotificationService notificationService;

    private final List<AbstractMessage> pendingMessages = new ArrayList<AbstractMessage>();

    public MessageProcessor(MessageService messageService, ContactService contactService, IdentityStoreInterface identityStore, ContactStoreInterface contactStore, PreferenceService preferenceService, GroupService groupService, IdListService blackListService, BallotService ballotService, FileService fileService, NotificationService notificationService, VoipStateService voipStateService) {
        this.messageService = messageService;
        this.contactService = contactService;
        this.identityStore = identityStore;
        this.contactStore = contactStore;
        this.preferenceService = preferenceService;
        this.groupService = groupService;
        this.blackListService = blackListService;
        this.ballotService = ballotService;
        if (!ListenerUtil.mutListener.listen(33180)) {
            this.fileService = fileService;
        }
        if (!ListenerUtil.mutListener.listen(33181)) {
            this.notificationService = notificationService;
        }
        this.voipStateService = voipStateService;
    }

    @Override
    @WorkerThread
    public ProcessIncomingResult processIncomingMessage(BoxedMessage boxmsg) {
        AbstractMessage msg;
        try {
            if (!ListenerUtil.mutListener.listen(33190)) {
                if ((ListenerUtil.mutListener.listen(33188) ? (ConfigUtils.isWorkBuild() || preferenceService.isBlockUnknown()) : (ConfigUtils.isWorkBuild() && preferenceService.isBlockUnknown()))) {
                    if (!ListenerUtil.mutListener.listen(33189)) {
                        contactService.createWorkContact(boxmsg.getFromIdentity());
                    }
                }
            }
            // try to fetch the key - throws MissingPublicKeyException if contact is blocked or fetching failed
            msg = AbstractMessage.decodeFromBox(boxmsg, this.contactStore, this.identityStore, true);
            if (msg == null) {
                if (!ListenerUtil.mutListener.listen(33191)) {
                    logger.warn("Message {} from {} error: decodeFromBox failed", boxmsg.getMessageId(), boxmsg.getFromIdentity());
                }
                return ProcessIncomingResult.failed();
            }
            if (!ListenerUtil.mutListener.listen(33192)) {
                logger.info("Incoming message {} from {} to {} (type {})", boxmsg.getMessageId(), boxmsg.getFromIdentity(), boxmsg.getToIdentity(), Utils.byteToHex((byte) msg.getType(), true, true));
            }
            if (!ListenerUtil.mutListener.listen(33199)) {
                /* validation logging (for text messages only) */
                if ((ListenerUtil.mutListener.listen(33193) ? (msg instanceof BoxTextMessage && msg instanceof GroupTextMessage) : (msg instanceof BoxTextMessage || msg instanceof GroupTextMessage))) {
                    if (!ListenerUtil.mutListener.listen(33198)) {
                        if (validationLogger.isInfoEnabled()) {
                            if (!ListenerUtil.mutListener.listen(33194)) {
                                validationLogger.info("< Nonce: {}", Utils.byteArrayToHexString(boxmsg.getNonce()));
                            }
                            if (!ListenerUtil.mutListener.listen(33195)) {
                                validationLogger.info("< Data: {}", Utils.byteArrayToHexString(boxmsg.getBox()));
                            }
                            byte[] publicKey = contactStore.getPublicKeyForIdentity(boxmsg.getFromIdentity(), true);
                            if (!ListenerUtil.mutListener.listen(33197)) {
                                if (publicKey != null) {
                                    if (!ListenerUtil.mutListener.listen(33196)) {
                                        validationLogger.info("< Public key ({}): {}", boxmsg.getFromIdentity(), Utils.byteArrayToHexString(publicKey));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // check if sender is on blacklist
            if (!(msg instanceof AbstractGroupMessage)) {
                if ((ListenerUtil.mutListener.listen(33200) ? (this.blackListService != null || this.blackListService.has(msg.getFromIdentity())) : (this.blackListService != null && this.blackListService.has(msg.getFromIdentity())))) {
                    if (!ListenerUtil.mutListener.listen(33201)) {
                        logger.debug("Direct message from {}: Contact blacklisted. Ignoring", msg.getFromIdentity());
                    }
                    // ignore message of blacklisted member
                    return ProcessIncomingResult.ignore();
                }
            }
            if (!ListenerUtil.mutListener.listen(33202)) {
                this.contactService.setActive(msg.getFromIdentity());
            }
            if (msg instanceof TypingIndicatorMessage) {
                if (!ListenerUtil.mutListener.listen(33203)) {
                    this.contactService.setIsTyping(boxmsg.getFromIdentity(), ((TypingIndicatorMessage) msg).isTyping());
                }
                return ProcessIncomingResult.ok(msg);
            }
            if (msg instanceof DeliveryReceiptMessage) {
                // this.messageService.
                MessageState state = null;
                if (!ListenerUtil.mutListener.listen(33209)) {
                    switch(((DeliveryReceiptMessage) msg).getReceiptType()) {
                        case ProtocolDefines.DELIVERYRECEIPT_MSGRECEIVED:
                            if (!ListenerUtil.mutListener.listen(33204)) {
                                state = MessageState.DELIVERED;
                            }
                            break;
                        case ProtocolDefines.DELIVERYRECEIPT_MSGREAD:
                            if (!ListenerUtil.mutListener.listen(33205)) {
                                state = MessageState.READ;
                            }
                            break;
                        case ProtocolDefines.DELIVERYRECEIPT_MSGUSERACK:
                            if (!ListenerUtil.mutListener.listen(33206)) {
                                state = MessageState.USERACK;
                            }
                            break;
                        case ProtocolDefines.DELIVERYRECEIPT_MSGUSERDEC:
                            if (!ListenerUtil.mutListener.listen(33207)) {
                                state = MessageState.USERDEC;
                            }
                            break;
                        case ProtocolDefines.DELIVERYRECEIPT_MSGCONSUMED:
                            if (!ListenerUtil.mutListener.listen(33208)) {
                                state = MessageState.CONSUMED;
                            }
                            break;
                    }
                }
                if (state != null) {
                    if (!ListenerUtil.mutListener.listen(33213)) {
                        {
                            long _loopCounter232 = 0;
                            for (MessageId msgId : ((DeliveryReceiptMessage) msg).getReceiptMessageIds()) {
                                ListenerUtil.loopListener.listen("_loopCounter232", ++_loopCounter232);
                                if (!ListenerUtil.mutListener.listen(33211)) {
                                    logger.info("Message " + boxmsg.getMessageId() + ": delivery receipt for " + msgId + " (state = " + state + ")");
                                }
                                if (!ListenerUtil.mutListener.listen(33212)) {
                                    this.messageService.updateMessageState(msgId, msg.getFromIdentity(), state, msg.getDate());
                                }
                            }
                        }
                    }
                    return ProcessIncomingResult.ok(msg);
                } else {
                    if (!ListenerUtil.mutListener.listen(33210)) {
                        logger.warn("Message {} error: unknown delivery receipt type", boxmsg.getMessageId());
                    }
                }
                return ProcessIncomingResult.ignore();
            }
            /* send delivery receipt (but not for immediate messages or delivery receipts) */
            if (!msg.isImmediate()) {
                /* throw away messages from hidden contacts if block unknown is enabled - except for group messages */
                if ((ListenerUtil.mutListener.listen(33215) ? ((ListenerUtil.mutListener.listen(33214) ? (this.preferenceService.isBlockUnknown() || this.contactService.getIsHidden(msg.getFromIdentity())) : (this.preferenceService.isBlockUnknown() && this.contactService.getIsHidden(msg.getFromIdentity()))) || !(msg instanceof AbstractGroupMessage)) : ((ListenerUtil.mutListener.listen(33214) ? (this.preferenceService.isBlockUnknown() || this.contactService.getIsHidden(msg.getFromIdentity())) : (this.preferenceService.isBlockUnknown() && this.contactService.getIsHidden(msg.getFromIdentity()))) && !(msg instanceof AbstractGroupMessage)))) {
                    if (!ListenerUtil.mutListener.listen(33216)) {
                        logger.info("Message {} discarded - from hidden contact with block unknown enabled", boxmsg.getMessageId());
                    }
                    return ProcessIncomingResult.ignore();
                }
                if (!this.processAbstractMessage(msg)) {
                    // only if failed, return false
                    return ProcessIncomingResult.failed();
                }
            }
            return ProcessIncomingResult.ok(msg);
        } catch (MissingPublicKeyException e) {
            if (this.preferenceService.isBlockUnknown()) {
                // its ok, return true and save nothing;
                return ProcessIncomingResult.ignore();
            }
            if ((ListenerUtil.mutListener.listen(33183) ? ((ListenerUtil.mutListener.listen(33182) ? (this.blackListService != null || boxmsg != null) : (this.blackListService != null && boxmsg != null)) || this.blackListService.has(boxmsg.getFromIdentity())) : ((ListenerUtil.mutListener.listen(33182) ? (this.blackListService != null || boxmsg != null) : (this.blackListService != null && boxmsg != null)) && this.blackListService.has(boxmsg.getFromIdentity())))) {
                // its ok, a black listed identity, save NOTHING
                return ProcessIncomingResult.ignore();
            }
            if (!ListenerUtil.mutListener.listen(33184)) {
                logger.error("Missing public key", e);
            }
            return ProcessIncomingResult.failed();
        } catch (BadMessageException e) {
            if (!ListenerUtil.mutListener.listen(33185)) {
                logger.error("Bad message", e);
            }
            if (e.shouldDrop()) {
                if (!ListenerUtil.mutListener.listen(33186)) {
                    logger.warn("Message {} error: invalid - dropping msg.", boxmsg.getMessageId());
                }
                return ProcessIncomingResult.ignore();
            }
            return ProcessIncomingResult.failed();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(33187)) {
                logger.error("Unknown exception", e);
            }
            return ProcessIncomingResult.failed();
        }
    }

    /**
     *  @param msg incoming message
     *  @return true if message has been properly processed, false if unsuccessful (e.g. network error) and processing/download should be attempted again later
     */
    @WorkerThread
    private boolean processAbstractMessage(AbstractMessage msg) {
        try {
            if (!ListenerUtil.mutListener.listen(33219)) {
                logger.trace("processAbstractMessage {}", msg.getMessageId());
            }
            if (!ListenerUtil.mutListener.listen(33220)) {
                // try to update public nickname
                this.contactService.updatePublicNickName(msg);
            }
            // check available size on device..
            long useableSpace = this.fileService.getInternalStorageFree();
            long requiredSpace = MessageDiskSizeUtil.getSize(msg);
            if ((ListenerUtil.mutListener.listen(33225) ? (useableSpace >= requiredSpace) : (ListenerUtil.mutListener.listen(33224) ? (useableSpace <= requiredSpace) : (ListenerUtil.mutListener.listen(33223) ? (useableSpace > requiredSpace) : (ListenerUtil.mutListener.listen(33222) ? (useableSpace != requiredSpace) : (ListenerUtil.mutListener.listen(33221) ? (useableSpace == requiredSpace) : (useableSpace < requiredSpace))))))) {
                if (!ListenerUtil.mutListener.listen(33226)) {
                    // show notification and do not try to save the message
                    this.notificationService.showNotEnoughDiskSpace(useableSpace, requiredSpace);
                }
                if (!ListenerUtil.mutListener.listen(33227)) {
                    logger.error("Abstract Message {}: error - out of disk space {}/{}", msg.getMessageId(), requiredSpace, useableSpace);
                }
                return false;
            }
            if (msg instanceof BallotVoteInterface) {
                BallotVoteResult r = this.ballotService.vote((BallotVoteInterface) msg);
                return (ListenerUtil.mutListener.listen(33228) ? (r != null || r.isSuccess()) : (r != null && r.isSuccess()));
            }
            if (msg instanceof AbstractGroupMessage) {
                if (msg instanceof GroupCreateMessage) {
                    // new group or sync it!
                    GroupService.GroupCreateMessageResult result = this.groupService.processGroupCreateMessage((GroupCreateMessage) msg);
                    if (!ListenerUtil.mutListener.listen(33243)) {
                        if ((ListenerUtil.mutListener.listen(33234) ? (result.success() || result.getGroupModel() != null) : (result.success() && result.getGroupModel() != null))) {
                            // process unprocessed message
                            synchronized (this.pendingMessages) {
                                Iterator<AbstractMessage> i = this.pendingMessages.iterator();
                                if (!ListenerUtil.mutListener.listen(33242)) {
                                    {
                                        long _loopCounter233 = 0;
                                        while (i.hasNext()) {
                                            ListenerUtil.loopListener.listen("_loopCounter233", ++_loopCounter233);
                                            AbstractMessage s = i.next();
                                            if (!ListenerUtil.mutListener.listen(33241)) {
                                                if ((ListenerUtil.mutListener.listen(33236) ? ((ListenerUtil.mutListener.listen(33235) ? (s != null || s instanceof AbstractGroupMessage) : (s != null && s instanceof AbstractGroupMessage)) || !(s instanceof GroupCreateMessage)) : ((ListenerUtil.mutListener.listen(33235) ? (s != null || s instanceof AbstractGroupMessage) : (s != null && s instanceof AbstractGroupMessage)) && !(s instanceof GroupCreateMessage)))) {
                                                    AbstractGroupMessage as = (AbstractGroupMessage) s;
                                                    if (!ListenerUtil.mutListener.listen(33240)) {
                                                        if ((ListenerUtil.mutListener.listen(33237) ? (as.getGroupCreator().equals(((GroupCreateMessage) msg).getGroupCreator()) || as.getGroupId().toString().equals(((GroupCreateMessage) msg).getGroupId().toString())) : (as.getGroupCreator().equals(((GroupCreateMessage) msg).getGroupCreator()) && as.getGroupId().toString().equals(((GroupCreateMessage) msg).getGroupId().toString())))) {
                                                            if (!ListenerUtil.mutListener.listen(33238)) {
                                                                this.processAbstractMessage(s);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(33239)) {
                                                                i.remove();
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
                    return result.success();
                } else if (msg instanceof GroupRenameMessage) {
                    return this.groupService.renameGroup((GroupRenameMessage) msg);
                } else if (msg instanceof GroupSetPhotoMessage) {
                    return this.groupService.updateGroupPhoto((GroupSetPhotoMessage) msg);
                } else if (msg instanceof GroupDeletePhotoMessage) {
                    return this.groupService.deleteGroupPhoto((GroupDeletePhotoMessage) msg);
                } else if (msg instanceof GroupLeaveMessage) {
                    return this.groupService.removeMemberFromGroup((GroupLeaveMessage) msg);
                } else if (msg instanceof GroupRequestSyncMessage) {
                    return this.groupService.processRequestSync((GroupRequestSyncMessage) msg);
                } else {
                    GroupModel groupModel = this.groupService.getGroup((AbstractGroupMessage) msg);
                    // group model not found
                    if ((ListenerUtil.mutListener.listen(33231) ? (groupModel == null && // or i am not a member of this group
                    !this.groupService.isGroupMember(groupModel)) : (groupModel == null || // or i am not a member of this group
                    !this.groupService.isGroupMember(groupModel)))) {
                        // if the request sync process ok, ack the message
                        if (this.groupService.requestSync((AbstractGroupMessage) msg, true)) {
                            // save message in cache
                            synchronized (this.pendingMessages) {
                                if (!ListenerUtil.mutListener.listen(33233)) {
                                    this.pendingMessages.add(msg);
                                }
                            }
                            return true;
                        }
                        return false;
                    } else if (groupModel.isDeleted()) {
                        if (!ListenerUtil.mutListener.listen(33232)) {
                            // send leave message
                            this.groupService.sendLeave((AbstractGroupMessage) msg);
                        }
                        // ack every time!
                        return true;
                    } else {
                        return this.messageService.processIncomingGroupMessage((AbstractGroupMessage) msg);
                    }
                }
            } else if (msg instanceof ContactSetPhotoMessage) {
                return this.contactService.updateContactPhoto((ContactSetPhotoMessage) msg);
            } else if (msg instanceof ContactDeletePhotoMessage) {
                return this.contactService.deleteContactPhoto((ContactDeletePhotoMessage) msg);
            } else if (msg instanceof ContactRequestPhotoMessage) {
                return this.contactService.requestContactPhoto((ContactRequestPhotoMessage) msg);
            } else if (msg instanceof VoipMessage) {
                if (preferenceService.isVoipEnabled()) {
                    if (!ListenerUtil.mutListener.listen(33229)) {
                        /* as soon as we get a voip message, unhide the contact */
                        this.contactService.setIsHidden(msg.getFromIdentity(), false);
                    }
                    if (msg instanceof VoipCallOfferMessage) {
                        return this.voipStateService.handleCallOffer((VoipCallOfferMessage) msg);
                    } else if (msg instanceof VoipCallAnswerMessage) {
                        return this.voipStateService.handleCallAnswer((VoipCallAnswerMessage) msg);
                    } else if (msg instanceof VoipICECandidatesMessage) {
                        return this.voipStateService.handleICECandidates((VoipICECandidatesMessage) msg);
                    } else if (msg instanceof VoipCallRingingMessage) {
                        return this.voipStateService.handleCallRinging((VoipCallRingingMessage) msg);
                    } else if (msg instanceof VoipCallHangupMessage) {
                        return this.voipStateService.handleRemoteCallHangup((VoipCallHangupMessage) msg);
                    }
                } else if (msg instanceof VoipCallOfferMessage) {
                    // If calls are disabled, only react to offers.
                    return this.voipStateService.handleCallOffer((VoipCallOfferMessage) msg);
                }
                if (!ListenerUtil.mutListener.listen(33230)) {
                    // ignore other VoIP related messages
                    logger.debug("Ignoring VoIP related message, since calls are disabled");
                }
                return true;
            } else {
                return this.messageService.processIncomingContactMessage(msg);
            }
        } catch (FileNotFoundException f) {
            if (!ListenerUtil.mutListener.listen(33217)) {
                // do nothing
                logger.error("File not found", f);
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(33218)) {
                logger.error("Unknown exception", e);
            }
            return false;
        }
    }

    @Override
    public void processServerAlert(String s) {
        ServerMessageModel msg = new ServerMessageModel(s, ServerMessageModel.Type.ALERT);
        if (!ListenerUtil.mutListener.listen(33244)) {
            this.messageService.saveIncomingServerMessage(msg);
        }
    }

    @Override
    public void processServerError(String s, boolean b) {
        ServerMessageModel msg = new ServerMessageModel(s, ServerMessageModel.Type.ERROR);
        if (!ListenerUtil.mutListener.listen(33245)) {
            this.messageService.saveIncomingServerMessage(msg);
        }
    }
}
