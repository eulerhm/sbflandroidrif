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
package ch.threema.app.utils;

import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.client.ProtocolDefines;
import ch.threema.client.file.FileData;
import ch.threema.client.voip.VoipCallAnswerData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.MessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.status.VoipStatusDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageUtil {

    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    private static final java.util.Set<MessageType> fileMessageModelTypes = EnumSet.of(MessageType.IMAGE, MessageType.VOICEMESSAGE, MessageType.VIDEO, MessageType.FILE);

    private static final java.util.Set<MessageType> thumbnailFileMessageModelTypes = EnumSet.of(MessageType.IMAGE, MessageType.VIDEO, MessageType.FILE);

    private static final java.util.Set<MessageType> lowProfileMessageModelTypes = EnumSet.of(MessageType.IMAGE, MessageType.VOICEMESSAGE);

    public static String getDisplayDate(Context context, AbstractMessageModel messageModel, boolean full) {
        if (messageModel == null) {
            return "";
        }
        Date d = messageModel.getPostedAt();
        if (!ListenerUtil.mutListener.listen(54818)) {
            if (messageModel.isOutbox()) {
                if (!ListenerUtil.mutListener.listen(54817)) {
                    if (messageModel.getModifiedAt() != null) {
                        if (!ListenerUtil.mutListener.listen(54816)) {
                            d = messageModel.getModifiedAt();
                        }
                    }
                }
            }
        }
        if (d != null) {
            return LocaleUtil.formatTimeStampString(context, d.getTime(), full);
        } else {
            return "";
        }
    }

    /**
     *  @param messageModel
     *  @return
     */
    public static boolean hasDataFile(AbstractMessageModel messageModel) {
        return (ListenerUtil.mutListener.listen(54819) ? (messageModel != null || fileMessageModelTypes.contains(messageModel.getType())) : (messageModel != null && fileMessageModelTypes.contains(messageModel.getType())));
    }

    /**
     *  This method indicates whether the message is a type that can have a thumbnail.
     *  Note that it's still possible that a message does not (yet) have a thumbnail stored,
     *  even though this method returns true.
     */
    public static boolean canHaveThumbnailFile(AbstractMessageModel messageModel) {
        return (ListenerUtil.mutListener.listen(54820) ? (messageModel != null || thumbnailFileMessageModelTypes.contains(messageModel.getType())) : (messageModel != null && thumbnailFileMessageModelTypes.contains(messageModel.getType())));
    }

    public static Set<MessageType> getFileTypes() {
        return fileMessageModelTypes;
    }

    public static Set<MessageType> getLowProfileMessageModelTypes() {
        return lowProfileMessageModelTypes;
    }

    public static boolean canSendDeliveryReceipt(AbstractMessageModel message) {
        return (ListenerUtil.mutListener.listen(54826) ? ((ListenerUtil.mutListener.listen(54825) ? ((ListenerUtil.mutListener.listen(54824) ? ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) || !message.isStatusMessage()) : ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) && !message.isStatusMessage())) || message.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54824) ? ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) || !message.isStatusMessage()) : ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) && !message.isStatusMessage())) && message.getType() != MessageType.VOIP_STATUS)) || !((message.getMessageFlags() & ProtocolDefines.MESSAGE_FLAG_NO_DELIVERY_RECEIPTS) == ProtocolDefines.MESSAGE_FLAG_NO_DELIVERY_RECEIPTS)) : ((ListenerUtil.mutListener.listen(54825) ? ((ListenerUtil.mutListener.listen(54824) ? ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) || !message.isStatusMessage()) : ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) && !message.isStatusMessage())) || message.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54824) ? ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) || !message.isStatusMessage()) : ((ListenerUtil.mutListener.listen(54823) ? ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54822) ? ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54821) ? (message != null || message instanceof MessageModel) : (message != null && message instanceof MessageModel)) && !message.isOutbox())) && !message.isRead())) && !message.isStatusMessage())) && message.getType() != MessageType.VOIP_STATUS)) && !((message.getMessageFlags() & ProtocolDefines.MESSAGE_FLAG_NO_DELIVERY_RECEIPTS) == ProtocolDefines.MESSAGE_FLAG_NO_DELIVERY_RECEIPTS)));
    }

    /**
     *  return true if the message model can mark as read
     *  @param message
     *  @return
     */
    public static boolean canMarkAsRead(AbstractMessageModel message) {
        return (ListenerUtil.mutListener.listen(54828) ? ((ListenerUtil.mutListener.listen(54827) ? (message != null || !message.isOutbox()) : (message != null && !message.isOutbox())) || !message.isRead()) : ((ListenerUtil.mutListener.listen(54827) ? (message != null || !message.isOutbox()) : (message != null && !message.isOutbox())) && !message.isRead()));
    }

    /**
     *  return true if the message model can mark as consumed
     *  @param message
     *  @return
     */
    public static boolean canMarkAsConsumed(@Nullable AbstractMessageModel message) {
        return (ListenerUtil.mutListener.listen(54836) ? ((ListenerUtil.mutListener.listen(54834) ? ((ListenerUtil.mutListener.listen(54832) ? ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) || message.getState() != MessageState.CONSUMED) : ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) && message.getState() != MessageState.CONSUMED)) || ((ListenerUtil.mutListener.listen(54833) ? (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE && message.getMessageContentsType() == MessageContentsType.AUDIO) : (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE || message.getMessageContentsType() == MessageContentsType.AUDIO)))) : ((ListenerUtil.mutListener.listen(54832) ? ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) || message.getState() != MessageState.CONSUMED) : ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) && message.getState() != MessageState.CONSUMED)) && ((ListenerUtil.mutListener.listen(54833) ? (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE && message.getMessageContentsType() == MessageContentsType.AUDIO) : (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE || message.getMessageContentsType() == MessageContentsType.AUDIO))))) || ((ListenerUtil.mutListener.listen(54835) ? (message.getState() == null && canChangeToState(message.getState(), MessageState.CONSUMED, message.isOutbox())) : (message.getState() == null || canChangeToState(message.getState(), MessageState.CONSUMED, message.isOutbox()))))) : ((ListenerUtil.mutListener.listen(54834) ? ((ListenerUtil.mutListener.listen(54832) ? ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) || message.getState() != MessageState.CONSUMED) : ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) && message.getState() != MessageState.CONSUMED)) || ((ListenerUtil.mutListener.listen(54833) ? (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE && message.getMessageContentsType() == MessageContentsType.AUDIO) : (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE || message.getMessageContentsType() == MessageContentsType.AUDIO)))) : ((ListenerUtil.mutListener.listen(54832) ? ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) || message.getState() != MessageState.CONSUMED) : ((ListenerUtil.mutListener.listen(54831) ? ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) || !message.isOutbox()) : ((ListenerUtil.mutListener.listen(54830) ? (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) || !message.isStatusMessage()) : (((ListenerUtil.mutListener.listen(54829) ? (message instanceof MessageModel && message instanceof GroupMessageModel) : (message instanceof MessageModel || message instanceof GroupMessageModel))) && !message.isStatusMessage())) && !message.isOutbox())) && message.getState() != MessageState.CONSUMED)) && ((ListenerUtil.mutListener.listen(54833) ? (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE && message.getMessageContentsType() == MessageContentsType.AUDIO) : (message.getMessageContentsType() == MessageContentsType.VOICE_MESSAGE || message.getMessageContentsType() == MessageContentsType.AUDIO))))) && ((ListenerUtil.mutListener.listen(54835) ? (message.getState() == null && canChangeToState(message.getState(), MessageState.CONSUMED, message.isOutbox())) : (message.getState() == null || canChangeToState(message.getState(), MessageState.CONSUMED, message.isOutbox()))))));
    }

    /**
     *  return true, if the user-acknowledge flag can be set
     *  @param messageModel
     *  @return
     */
    public static boolean canSendUserAcknowledge(AbstractMessageModel messageModel) {
        return (ListenerUtil.mutListener.listen(54841) ? ((ListenerUtil.mutListener.listen(54840) ? ((ListenerUtil.mutListener.listen(54839) ? ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) && messageModel.getType() != MessageType.VOIP_STATUS)) || !(messageModel instanceof DistributionListMessageModel)) : ((ListenerUtil.mutListener.listen(54839) ? ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) && messageModel.getType() != MessageType.VOIP_STATUS)) && !(messageModel instanceof DistributionListMessageModel))) || !(messageModel instanceof GroupMessageModel)) : ((ListenerUtil.mutListener.listen(54840) ? ((ListenerUtil.mutListener.listen(54839) ? ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) && messageModel.getType() != MessageType.VOIP_STATUS)) || !(messageModel instanceof DistributionListMessageModel)) : ((ListenerUtil.mutListener.listen(54839) ? ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54838) ? ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERACK) : ((ListenerUtil.mutListener.listen(54837) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERACK)) && messageModel.getType() != MessageType.VOIP_STATUS)) && !(messageModel instanceof DistributionListMessageModel))) && !(messageModel instanceof GroupMessageModel)));
    }

    /**
     *  return true, if the user-decline flag can be set
     *  @param messageModel
     *  @return
     */
    public static boolean canSendUserDecline(AbstractMessageModel messageModel) {
        return (ListenerUtil.mutListener.listen(54846) ? ((ListenerUtil.mutListener.listen(54845) ? ((ListenerUtil.mutListener.listen(54844) ? ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) && messageModel.getType() != MessageType.VOIP_STATUS)) || !(messageModel instanceof DistributionListMessageModel)) : ((ListenerUtil.mutListener.listen(54844) ? ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) && messageModel.getType() != MessageType.VOIP_STATUS)) && !(messageModel instanceof DistributionListMessageModel))) || !(messageModel instanceof GroupMessageModel)) : ((ListenerUtil.mutListener.listen(54845) ? ((ListenerUtil.mutListener.listen(54844) ? ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) && messageModel.getType() != MessageType.VOIP_STATUS)) || !(messageModel instanceof DistributionListMessageModel)) : ((ListenerUtil.mutListener.listen(54844) ? ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) || messageModel.getType() != MessageType.VOIP_STATUS) : ((ListenerUtil.mutListener.listen(54843) ? ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) || messageModel.getState() != MessageState.USERDEC) : ((ListenerUtil.mutListener.listen(54842) ? (messageModel != null || !messageModel.isOutbox()) : (messageModel != null && !messageModel.isOutbox())) && messageModel.getState() != MessageState.USERDEC)) && messageModel.getType() != MessageType.VOIP_STATUS)) && !(messageModel instanceof DistributionListMessageModel))) && !(messageModel instanceof GroupMessageModel)));
    }

    /**
     *  return true if the user-acknowledge flag visible
     *  @param messageModel
     *  @return
     */
    public static boolean showStatusIcon(AbstractMessageModel messageModel) {
        boolean showState = false;
        if (!ListenerUtil.mutListener.listen(54871)) {
            if (messageModel != null) {
                if (!ListenerUtil.mutListener.listen(54847)) {
                    if (messageModel.getType() == MessageType.VOIP_STATUS) {
                        return false;
                    }
                }
                MessageState messageState = messageModel.getState();
                if (!ListenerUtil.mutListener.listen(54870)) {
                    // group message/distribution list message icons only on pending or failing states
                    if (messageModel instanceof GroupMessageModel) {
                        if (!ListenerUtil.mutListener.listen(54869)) {
                            showState = (ListenerUtil.mutListener.listen(54868) ? (messageState != null || ((ListenerUtil.mutListener.listen(54867) ? ((ListenerUtil.mutListener.listen(54865) ? ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) && ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT)))) : ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) || ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT))))) && ((ListenerUtil.mutListener.listen(54866) ? (!messageModel.isOutbox() || messageModel.getState() == MessageState.CONSUMED) : (!messageModel.isOutbox() && messageModel.getState() == MessageState.CONSUMED)))) : ((ListenerUtil.mutListener.listen(54865) ? ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) && ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT)))) : ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) || ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT))))) || ((ListenerUtil.mutListener.listen(54866) ? (!messageModel.isOutbox() || messageModel.getState() == MessageState.CONSUMED) : (!messageModel.isOutbox() && messageModel.getState() == MessageState.CONSUMED))))))) : (messageState != null && ((ListenerUtil.mutListener.listen(54867) ? ((ListenerUtil.mutListener.listen(54865) ? ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) && ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT)))) : ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) || ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT))))) && ((ListenerUtil.mutListener.listen(54866) ? (!messageModel.isOutbox() || messageModel.getState() == MessageState.CONSUMED) : (!messageModel.isOutbox() && messageModel.getState() == MessageState.CONSUMED)))) : ((ListenerUtil.mutListener.listen(54865) ? ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) && ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT)))) : ((ListenerUtil.mutListener.listen(54862) ? (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) && ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING)))) : (((ListenerUtil.mutListener.listen(54860) ? (messageModel.isOutbox() || messageState == MessageState.SENDFAILED) : (messageModel.isOutbox() && messageState == MessageState.SENDFAILED))) || ((ListenerUtil.mutListener.listen(54861) ? (messageModel.isOutbox() || messageState == MessageState.SENDING) : (messageModel.isOutbox() && messageState == MessageState.SENDING))))) || ((ListenerUtil.mutListener.listen(54864) ? ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) || messageModel.getType() != MessageType.BALLOT) : ((ListenerUtil.mutListener.listen(54863) ? (messageModel.isOutbox() || messageState == MessageState.PENDING) : (messageModel.isOutbox() && messageState == MessageState.PENDING)) && messageModel.getType() != MessageType.BALLOT))))) || ((ListenerUtil.mutListener.listen(54866) ? (!messageModel.isOutbox() || messageModel.getState() == MessageState.CONSUMED) : (!messageModel.isOutbox() && messageModel.getState() == MessageState.CONSUMED))))))));
                        }
                    } else if (messageModel instanceof DistributionListMessageModel) {
                        if (!ListenerUtil.mutListener.listen(54859)) {
                            showState = false;
                        }
                    } else if (messageModel instanceof MessageModel) {
                        if (!ListenerUtil.mutListener.listen(54858)) {
                            if (!messageModel.isOutbox()) {
                                if (!ListenerUtil.mutListener.listen(54857)) {
                                    // inbox show icon only on acknowledged/declined or consumed
                                    showState = (ListenerUtil.mutListener.listen(54856) ? (messageState != null || ((ListenerUtil.mutListener.listen(54855) ? ((ListenerUtil.mutListener.listen(54854) ? (messageModel.getState() == MessageState.USERACK && messageModel.getState() == MessageState.USERDEC) : (messageModel.getState() == MessageState.USERACK || messageModel.getState() == MessageState.USERDEC)) && messageModel.getState() == MessageState.CONSUMED) : ((ListenerUtil.mutListener.listen(54854) ? (messageModel.getState() == MessageState.USERACK && messageModel.getState() == MessageState.USERDEC) : (messageModel.getState() == MessageState.USERACK || messageModel.getState() == MessageState.USERDEC)) || messageModel.getState() == MessageState.CONSUMED)))) : (messageState != null && ((ListenerUtil.mutListener.listen(54855) ? ((ListenerUtil.mutListener.listen(54854) ? (messageModel.getState() == MessageState.USERACK && messageModel.getState() == MessageState.USERDEC) : (messageModel.getState() == MessageState.USERACK || messageModel.getState() == MessageState.USERDEC)) && messageModel.getState() == MessageState.CONSUMED) : ((ListenerUtil.mutListener.listen(54854) ? (messageModel.getState() == MessageState.USERACK && messageModel.getState() == MessageState.USERDEC) : (messageModel.getState() == MessageState.USERACK || messageModel.getState() == MessageState.USERDEC)) || messageModel.getState() == MessageState.CONSUMED)))));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(54853)) {
                                    // on outgoing message
                                    if (ContactUtil.isChannelContact(messageModel.getIdentity())) {
                                        if (!ListenerUtil.mutListener.listen(54852)) {
                                            showState = (ListenerUtil.mutListener.listen(54851) ? (messageState != null || ((ListenerUtil.mutListener.listen(54850) ? ((ListenerUtil.mutListener.listen(54849) ? (messageState == MessageState.SENDFAILED && messageState == MessageState.PENDING) : (messageState == MessageState.SENDFAILED || messageState == MessageState.PENDING)) && messageState == MessageState.SENDING) : ((ListenerUtil.mutListener.listen(54849) ? (messageState == MessageState.SENDFAILED && messageState == MessageState.PENDING) : (messageState == MessageState.SENDFAILED || messageState == MessageState.PENDING)) || messageState == MessageState.SENDING)))) : (messageState != null && ((ListenerUtil.mutListener.listen(54850) ? ((ListenerUtil.mutListener.listen(54849) ? (messageState == MessageState.SENDFAILED && messageState == MessageState.PENDING) : (messageState == MessageState.SENDFAILED || messageState == MessageState.PENDING)) && messageState == MessageState.SENDING) : ((ListenerUtil.mutListener.listen(54849) ? (messageState == MessageState.SENDFAILED && messageState == MessageState.PENDING) : (messageState == MessageState.SENDFAILED || messageState == MessageState.PENDING)) || messageState == MessageState.SENDING)))));
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(54848)) {
                                            showState = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return showState;
    }

    public static boolean isUnread(@Nullable AbstractMessageModel messageModel) {
        return (ListenerUtil.mutListener.listen(54874) ? ((ListenerUtil.mutListener.listen(54873) ? ((ListenerUtil.mutListener.listen(54872) ? (messageModel != null || !messageModel.isStatusMessage()) : (messageModel != null && !messageModel.isStatusMessage())) || !messageModel.isOutbox()) : ((ListenerUtil.mutListener.listen(54872) ? (messageModel != null || !messageModel.isStatusMessage()) : (messageModel != null && !messageModel.isStatusMessage())) && !messageModel.isOutbox())) || !messageModel.isRead()) : ((ListenerUtil.mutListener.listen(54873) ? ((ListenerUtil.mutListener.listen(54872) ? (messageModel != null || !messageModel.isStatusMessage()) : (messageModel != null && !messageModel.isStatusMessage())) || !messageModel.isOutbox()) : ((ListenerUtil.mutListener.listen(54872) ? (messageModel != null || !messageModel.isStatusMessage()) : (messageModel != null && !messageModel.isStatusMessage())) && !messageModel.isOutbox())) && !messageModel.isRead()));
    }

    /**
     *  return true, if the "system" automatically can generate a thumbnail file
     *  @param messageModel
     *  @return
     */
    public static boolean autoGenerateThumbnail(AbstractMessageModel messageModel) {
        return (ListenerUtil.mutListener.listen(54875) ? (messageModel != null || messageModel.getType() == MessageType.IMAGE) : (messageModel != null && messageModel.getType() == MessageType.IMAGE));
    }

    /**
     *  Returns all affected receivers of a distribution list (including myself)
     *  @param messageReceiver
     *  @return ArrayList of all MessageReceivers
     */
    public static ArrayList<MessageReceiver> getAllReceivers(final MessageReceiver messageReceiver) {
        ArrayList<MessageReceiver> allReceivers = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(54876)) {
            allReceivers.add(messageReceiver);
        }
        List<MessageReceiver> affectedReceivers = messageReceiver.getAffectedMessageReceivers();
        if (!ListenerUtil.mutListener.listen(54885)) {
            if ((ListenerUtil.mutListener.listen(54882) ? (affectedReceivers != null || (ListenerUtil.mutListener.listen(54881) ? (affectedReceivers.size() >= 0) : (ListenerUtil.mutListener.listen(54880) ? (affectedReceivers.size() <= 0) : (ListenerUtil.mutListener.listen(54879) ? (affectedReceivers.size() < 0) : (ListenerUtil.mutListener.listen(54878) ? (affectedReceivers.size() != 0) : (ListenerUtil.mutListener.listen(54877) ? (affectedReceivers.size() == 0) : (affectedReceivers.size() > 0))))))) : (affectedReceivers != null && (ListenerUtil.mutListener.listen(54881) ? (affectedReceivers.size() >= 0) : (ListenerUtil.mutListener.listen(54880) ? (affectedReceivers.size() <= 0) : (ListenerUtil.mutListener.listen(54879) ? (affectedReceivers.size() < 0) : (ListenerUtil.mutListener.listen(54878) ? (affectedReceivers.size() != 0) : (ListenerUtil.mutListener.listen(54877) ? (affectedReceivers.size() == 0) : (affectedReceivers.size() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(54884)) {
                    allReceivers.addAll(Functional.filter(affectedReceivers, new IPredicateNonNull<MessageReceiver>() {

                        @Override
                        public boolean apply(@NonNull MessageReceiver type) {
                            return (ListenerUtil.mutListener.listen(54883) ? (type != null || !type.isEqual(messageReceiver)) : (type != null && !type.isEqual(messageReceiver)));
                        }
                    }));
                }
            }
        }
        return allReceivers;
    }

    /**
     *  Expand list of MessageReceivers to contain distribution list receivers as single recipients
     *  @param allReceivers - list of MessageReceivers including distrubtion lists
     *  @return - expanded list of receivers with duplicates removed
     */
    public static MessageReceiver[] addDistributionListReceivers(MessageReceiver[] allReceivers) {
        Set<MessageReceiver> resolvedReceivers = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(54889)) {
            {
                long _loopCounter665 = 0;
                for (MessageReceiver receiver : allReceivers) {
                    ListenerUtil.loopListener.listen("_loopCounter665", ++_loopCounter665);
                    if (!ListenerUtil.mutListener.listen(54888)) {
                        if (receiver.getType() == MessageReceiver.Type_DISTRIBUTION_LIST) {
                            if (!ListenerUtil.mutListener.listen(54887)) {
                                resolvedReceivers.addAll(MessageUtil.getAllReceivers(receiver));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(54886)) {
                                resolvedReceivers.add(receiver);
                            }
                        }
                    }
                }
            }
        }
        return resolvedReceivers.toArray(new MessageReceiver[resolvedReceivers.size()]);
    }

    /**
     *  Check if a MessageState change from fromState to toState is allowed
     *  @param fromState State from which a state change is requested
     *  @param toState State to which a state change is requested
     *  @param isOutbox true, if it's an outgoing message
     *  @return true if a state change is allowed, false otherwise
     */
    public static boolean canChangeToState(@Nullable MessageState fromState, @Nullable MessageState toState, boolean isOutbox) {
        if ((ListenerUtil.mutListener.listen(54890) ? (fromState == null && toState == null) : (fromState == null || toState == null))) {
            // invalid data
            return false;
        }
        if (fromState == toState) {
            return false;
        }
        switch(toState) {
            case DELIVERED:
                return (ListenerUtil.mutListener.listen(54893) ? ((ListenerUtil.mutListener.listen(54892) ? ((ListenerUtil.mutListener.listen(54891) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54891) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) && fromState == MessageState.SENT) : ((ListenerUtil.mutListener.listen(54892) ? ((ListenerUtil.mutListener.listen(54891) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54891) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) || fromState == MessageState.SENT));
            case READ:
                return (ListenerUtil.mutListener.listen(54897) ? ((ListenerUtil.mutListener.listen(54896) ? ((ListenerUtil.mutListener.listen(54895) ? ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) && fromState == MessageState.SENT) : ((ListenerUtil.mutListener.listen(54895) ? ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) || fromState == MessageState.SENT)) && fromState == MessageState.DELIVERED) : ((ListenerUtil.mutListener.listen(54896) ? ((ListenerUtil.mutListener.listen(54895) ? ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) && fromState == MessageState.SENT) : ((ListenerUtil.mutListener.listen(54895) ? ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54894) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) || fromState == MessageState.SENT)) || fromState == MessageState.DELIVERED));
            case SENDFAILED:
                return (ListenerUtil.mutListener.listen(54899) ? ((ListenerUtil.mutListener.listen(54898) ? (fromState == MessageState.SENDING && fromState == MessageState.PENDING) : (fromState == MessageState.SENDING || fromState == MessageState.PENDING)) && fromState == MessageState.TRANSCODING) : ((ListenerUtil.mutListener.listen(54898) ? (fromState == MessageState.SENDING && fromState == MessageState.PENDING) : (fromState == MessageState.SENDING || fromState == MessageState.PENDING)) || fromState == MessageState.TRANSCODING));
            case SENT:
                return (ListenerUtil.mutListener.listen(54902) ? ((ListenerUtil.mutListener.listen(54901) ? ((ListenerUtil.mutListener.listen(54900) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54900) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) && fromState == MessageState.TRANSCODING) : ((ListenerUtil.mutListener.listen(54901) ? ((ListenerUtil.mutListener.listen(54900) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) && fromState == MessageState.PENDING) : ((ListenerUtil.mutListener.listen(54900) ? (fromState == MessageState.SENDING && fromState == MessageState.SENDFAILED) : (fromState == MessageState.SENDING || fromState == MessageState.SENDFAILED)) || fromState == MessageState.PENDING)) || fromState == MessageState.TRANSCODING));
            case USERACK:
                return true;
            case USERDEC:
                return true;
            case CONSUMED:
                return (ListenerUtil.mutListener.listen(54903) ? (fromState != MessageState.USERACK || fromState != MessageState.USERDEC) : (fromState != MessageState.USERACK && fromState != MessageState.USERDEC));
            case PENDING:
                return fromState == MessageState.SENDFAILED;
            case SENDING:
                return (ListenerUtil.mutListener.listen(54905) ? ((ListenerUtil.mutListener.listen(54904) ? (fromState == MessageState.SENDFAILED && fromState == MessageState.PENDING) : (fromState == MessageState.SENDFAILED || fromState == MessageState.PENDING)) && fromState == MessageState.TRANSCODING) : ((ListenerUtil.mutListener.listen(54904) ? (fromState == MessageState.SENDFAILED && fromState == MessageState.PENDING) : (fromState == MessageState.SENDFAILED || fromState == MessageState.PENDING)) || fromState == MessageState.TRANSCODING));
            default:
                if (!ListenerUtil.mutListener.listen(54906)) {
                    logger.debug("message state " + toState.toString() + " not handled");
                }
                return false;
        }
    }

    public static String getCaption(List<String> captionList, int index) {
        String captionText = null;
        if (!ListenerUtil.mutListener.listen(54916)) {
            if ((ListenerUtil.mutListener.listen(54914) ? ((ListenerUtil.mutListener.listen(54913) ? ((ListenerUtil.mutListener.listen(54907) ? (captionList != null || !captionList.isEmpty()) : (captionList != null && !captionList.isEmpty())) || (ListenerUtil.mutListener.listen(54912) ? (index >= captionList.size()) : (ListenerUtil.mutListener.listen(54911) ? (index <= captionList.size()) : (ListenerUtil.mutListener.listen(54910) ? (index > captionList.size()) : (ListenerUtil.mutListener.listen(54909) ? (index != captionList.size()) : (ListenerUtil.mutListener.listen(54908) ? (index == captionList.size()) : (index < captionList.size()))))))) : ((ListenerUtil.mutListener.listen(54907) ? (captionList != null || !captionList.isEmpty()) : (captionList != null && !captionList.isEmpty())) && (ListenerUtil.mutListener.listen(54912) ? (index >= captionList.size()) : (ListenerUtil.mutListener.listen(54911) ? (index <= captionList.size()) : (ListenerUtil.mutListener.listen(54910) ? (index > captionList.size()) : (ListenerUtil.mutListener.listen(54909) ? (index != captionList.size()) : (ListenerUtil.mutListener.listen(54908) ? (index == captionList.size()) : (index < captionList.size())))))))) || captionList.get(index) != null) : ((ListenerUtil.mutListener.listen(54913) ? ((ListenerUtil.mutListener.listen(54907) ? (captionList != null || !captionList.isEmpty()) : (captionList != null && !captionList.isEmpty())) || (ListenerUtil.mutListener.listen(54912) ? (index >= captionList.size()) : (ListenerUtil.mutListener.listen(54911) ? (index <= captionList.size()) : (ListenerUtil.mutListener.listen(54910) ? (index > captionList.size()) : (ListenerUtil.mutListener.listen(54909) ? (index != captionList.size()) : (ListenerUtil.mutListener.listen(54908) ? (index == captionList.size()) : (index < captionList.size()))))))) : ((ListenerUtil.mutListener.listen(54907) ? (captionList != null || !captionList.isEmpty()) : (captionList != null && !captionList.isEmpty())) && (ListenerUtil.mutListener.listen(54912) ? (index >= captionList.size()) : (ListenerUtil.mutListener.listen(54911) ? (index <= captionList.size()) : (ListenerUtil.mutListener.listen(54910) ? (index > captionList.size()) : (ListenerUtil.mutListener.listen(54909) ? (index != captionList.size()) : (ListenerUtil.mutListener.listen(54908) ? (index == captionList.size()) : (index < captionList.size())))))))) && captionList.get(index) != null))) {
                if (!ListenerUtil.mutListener.listen(54915)) {
                    captionText = captionList.get(index);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(54917)) {
            if (captionText != null) {
                return captionText.trim();
            }
        }
        return null;
    }

    public static ArrayList<String> getCaptionList(String captionText) {
        ArrayList<String> captions = null;
        if (!ListenerUtil.mutListener.listen(54920)) {
            if (!TestUtil.empty(captionText)) {
                if (!ListenerUtil.mutListener.listen(54918)) {
                    captions = new ArrayList<>();
                }
                if (!ListenerUtil.mutListener.listen(54919)) {
                    captions.add(captionText);
                }
            }
        }
        return captions;
    }

    public static String getCaptionText(AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(54922)) {
            if (messageModel != null) {
                if (!ListenerUtil.mutListener.listen(54921)) {
                    switch(messageModel.getType()) {
                        case FILE:
                            return messageModel.getFileData().getCaption();
                        case IMAGE:
                            return messageModel.getCaption();
                        default:
                            break;
                    }
                }
            }
        }
        return null;
    }

    public static class MessageViewElement {

        @Nullable
        @DrawableRes
        public final Integer icon;

        @Nullable
        public final String placeholder;

        @Nullable
        public final Integer color;

        @Nullable
        public final String text;

        @Nullable
        public final String contentDescription;

        protected MessageViewElement(@Nullable @DrawableRes Integer icon, @Nullable String placeholder, @Nullable String text, @Nullable String contentDescription, @Nullable Integer color) {
            this.icon = icon;
            this.placeholder = placeholder;
            this.color = color;
            this.text = text;
            this.contentDescription = contentDescription;
        }
    }

    @NonNull
    public static MessageViewElement getViewElement(Context context, AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(54942)) {
            if (messageModel != null) {
                if (!ListenerUtil.mutListener.listen(54941)) {
                    switch(messageModel.getType()) {
                        case TEXT:
                            return new MessageViewElement(null, null, QuoteUtil.getMessageBody(messageModel, false), null, null);
                        case IMAGE:
                            return new MessageViewElement(R.drawable.ic_photo_filled, context.getString(R.string.image_placeholder), TestUtil.empty(messageModel.getCaption()) ? null : messageModel.getCaption(), null, null);
                        case VIDEO:
                            return new MessageViewElement(R.drawable.ic_movie_filled, context.getString(R.string.video_placeholder), messageModel.getVideoData().getDurationString(), null, null);
                        case LOCATION:
                            String locationText = null;
                            if (!ListenerUtil.mutListener.listen(54925)) {
                                if (!TestUtil.empty(messageModel.getLocationData().getPoi())) {
                                    if (!ListenerUtil.mutListener.listen(54924)) {
                                        locationText = messageModel.getLocationData().getPoi();
                                    }
                                } else if (!TestUtil.empty(messageModel.getLocationData().getAddress())) {
                                    if (!ListenerUtil.mutListener.listen(54923)) {
                                        locationText = messageModel.getLocationData().getAddress();
                                    }
                                }
                            }
                            return new MessageViewElement(R.drawable.ic_location_on_filled, context.getString(R.string.location_placeholder), locationText, null, null);
                        case VOICEMESSAGE:
                            return new MessageViewElement(R.drawable.ic_mic_filled, context.getString(R.string.audio_placeholder), StringConversionUtil.secondsToString(messageModel.getAudioData().getDuration(), false), ". " + context.getString(R.string.duration) + " " + StringConversionUtil.getDurationStringHuman(context, messageModel.getAudioData().getDuration()) + ". ", null);
                        case FILE:
                            String durationString = messageModel.getFileData().getDurationString();
                            if (!ListenerUtil.mutListener.listen(54926)) {
                                if (MimeUtil.isImageFile(messageModel.getFileData().getMimeType())) {
                                    return new MessageViewElement(R.drawable.ic_photo_filled, context.getString(R.string.image_placeholder), TestUtil.empty(messageModel.getFileData().getCaption()) ? null : messageModel.getFileData().getCaption(), null, null);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(54927)) {
                                if (MimeUtil.isVideoFile(messageModel.getFileData().getMimeType())) {
                                    return new MessageViewElement(R.drawable.ic_movie_filled, context.getString(R.string.video_placeholder), TestUtil.empty(messageModel.getFileData().getCaption()) ? durationString : messageModel.getFileData().getCaption(), null, null);
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(54929)) {
                                if (MimeUtil.isAudioFile(messageModel.getFileData().getMimeType())) {
                                    if (!ListenerUtil.mutListener.listen(54928)) {
                                        if (messageModel.getFileData().getRenderingType() == FileData.RENDERING_MEDIA) {
                                            return new MessageViewElement(R.drawable.ic_mic_filled, context.getString(R.string.audio_placeholder), StringConversionUtil.secondsToString(messageModel.getFileData().getDuration(), false), ". " + context.getString(R.string.duration) + " " + StringConversionUtil.getDurationStringHuman(context, messageModel.getFileData().getDuration()) + ". ", null);
                                        } else {
                                            return new MessageViewElement(R.drawable.ic_doc_audio, context.getString(R.string.audio_placeholder), TestUtil.empty(messageModel.getFileData().getCaption()) ? durationString : messageModel.getFileData().getCaption(), null, null);
                                        }
                                    }
                                }
                            }
                            return new MessageViewElement(R.drawable.ic_file_filled, context.getString(R.string.file_placeholder), TestUtil.empty(messageModel.getFileData().getCaption()) ? messageModel.getFileData().getFileName() : messageModel.getFileData().getCaption(), null, null);
                        case BALLOT:
                            String messageString = BallotUtil.getNotificationString(context, messageModel);
                            return new MessageViewElement(R.drawable.ic_baseline_rule, context.getString(R.string.ballot_placeholder), TestUtil.empty(messageString) ? null : messageString, null, null);
                        case VOIP_STATUS:
                            if (!ListenerUtil.mutListener.listen(54940)) {
                                if (messageModel.getVoipStatusData() != null) {
                                    if (!ListenerUtil.mutListener.listen(54939)) {
                                        switch(messageModel.getVoipStatusData().getStatus()) {
                                            case VoipStatusDataModel.REJECTED:
                                                // Determine reject reason
                                                final Byte reasonCodeByte = messageModel.getVoipStatusData().getReason();
                                                final byte reasonCode = reasonCodeByte == null ? VoipCallAnswerData.RejectReason.UNKNOWN : reasonCodeByte;
                                                // Default values
                                                int rejectColor = R.color.material_red;
                                                String rejectPlaceholder = messageModel.isOutbox() ? context.getString(R.string.voip_call_status_rejected) : context.getString(R.string.voip_call_status_missed);
                                                if (!ListenerUtil.mutListener.listen(54938)) {
                                                    // noinspection NestedSwitchStatement
                                                    switch(reasonCode) {
                                                        case VoipCallAnswerData.RejectReason.BUSY:
                                                            if (!ListenerUtil.mutListener.listen(54930)) {
                                                                rejectPlaceholder = messageModel.isOutbox() ? context.getString(R.string.voip_call_status_busy) : context.getString(R.string.voip_call_status_missed) + " (" + context.getString(R.string.voip_call_status_busy_short) + ")";
                                                            }
                                                            break;
                                                        case VoipCallAnswerData.RejectReason.TIMEOUT:
                                                            if (!ListenerUtil.mutListener.listen(54931)) {
                                                                rejectPlaceholder = messageModel.isOutbox() ? context.getString(R.string.voip_call_status_unavailable) : context.getString(R.string.voip_call_status_missed);
                                                            }
                                                            break;
                                                        case VoipCallAnswerData.RejectReason.REJECTED:
                                                            if (!ListenerUtil.mutListener.listen(54932)) {
                                                                rejectPlaceholder = context.getString(R.string.voip_call_status_rejected);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(54933)) {
                                                                rejectColor = messageModel.isOutbox() ? R.color.material_red : R.color.material_orange;
                                                            }
                                                            break;
                                                        case VoipCallAnswerData.RejectReason.DISABLED:
                                                            if (!ListenerUtil.mutListener.listen(54934)) {
                                                                rejectPlaceholder = messageModel.isOutbox() ? context.getString(R.string.voip_call_status_disabled) : context.getString(R.string.voip_call_status_rejected);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(54935)) {
                                                                rejectColor = messageModel.isOutbox() ? R.color.material_red : R.color.material_orange;
                                                            }
                                                            break;
                                                        case VoipCallAnswerData.RejectReason.OFF_HOURS:
                                                            if (!ListenerUtil.mutListener.listen(54936)) {
                                                                rejectPlaceholder = context.getString(R.string.voip_call_status_off_hours);
                                                            }
                                                            if (!ListenerUtil.mutListener.listen(54937)) {
                                                                rejectColor = messageModel.isOutbox() ? R.color.material_red : R.color.material_orange;
                                                            }
                                                            break;
                                                    }
                                                }
                                                return new MessageViewElement(messageModel.isOutbox() ? R.drawable.ic_call_missed_outgoing_black_24dp : R.drawable.ic_call_missed_black_24dp, rejectPlaceholder, rejectPlaceholder, null, rejectColor);
                                            case VoipStatusDataModel.ABORTED:
                                                return new MessageViewElement(R.drawable.ic_call_missed_outgoing_black_24dp, context.getString(R.string.voip_call_status_aborted), context.getString(R.string.voip_call_status_aborted), null, R.color.material_orange);
                                            case VoipStatusDataModel.MISSED:
                                                return new MessageViewElement(messageModel.isOutbox() ? R.drawable.ic_call_missed_outgoing_black_24dp : R.drawable.ic_call_missed_black_24dp, context.getString(R.string.voip_call_status_missed), context.getString(R.string.voip_call_status_missed), null, R.color.material_red);
                                            case VoipStatusDataModel.FINISHED:
                                                return new MessageViewElement(messageModel.isOutbox() ? R.drawable.ic_call_made_black_24dp : R.drawable.ic_call_received_black_24dp, context.getString(messageModel.isOutbox() ? R.string.voip_call_finished_outbox : R.string.voip_call_finished_inbox), context.getString(messageModel.isOutbox() ? R.string.voip_call_finished_outbox : R.string.voip_call_finished_inbox), null, R.color.material_green);
                                        }
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }
        return new MessageViewElement(null, null, null, null, null);
    }
}
