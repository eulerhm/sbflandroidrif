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
package ch.threema.storage.models;

import java.util.Date;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.DistributionListMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver.MessageReceiverType;
import ch.threema.app.utils.ConversationUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConversationModel {

    private long messageCount;

    private AbstractMessageModel latestMessage;

    private final MessageReceiver receiver;

    private long unreadCount;

    private String uid = null;

    private int position = -1;

    private boolean isTyping = false;

    public ConversationModel(MessageReceiver receiver) {
        this.receiver = receiver;
    }

    public long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(long messageCount) {
        if (!ListenerUtil.mutListener.listen(70935)) {
            this.messageCount = messageCount;
        }
    }

    public Date getSortDate() {
        if (!ListenerUtil.mutListener.listen(70936)) {
            if (this.getLatestMessage() != null) {
                return this.getLatestMessage().getCreatedAt();
            }
        }
        if (!ListenerUtil.mutListener.listen(70943)) {
            if (this.isGroupConversation()) {
                if (!ListenerUtil.mutListener.listen(70942)) {
                    if ((ListenerUtil.mutListener.listen(70941) ? (getMessageCount() >= 0) : (ListenerUtil.mutListener.listen(70940) ? (getMessageCount() <= 0) : (ListenerUtil.mutListener.listen(70939) ? (getMessageCount() < 0) : (ListenerUtil.mutListener.listen(70938) ? (getMessageCount() != 0) : (ListenerUtil.mutListener.listen(70937) ? (getMessageCount() == 0) : (getMessageCount() > 0))))))) {
                        return this.getGroup().getCreatedAt();
                    }
                }
                return new Date(0);
            }
        }
        if (!ListenerUtil.mutListener.listen(70950)) {
            if (this.isDistributionListConversation()) {
                if (!ListenerUtil.mutListener.listen(70949)) {
                    if ((ListenerUtil.mutListener.listen(70948) ? (getMessageCount() >= 0) : (ListenerUtil.mutListener.listen(70947) ? (getMessageCount() <= 0) : (ListenerUtil.mutListener.listen(70946) ? (getMessageCount() < 0) : (ListenerUtil.mutListener.listen(70945) ? (getMessageCount() != 0) : (ListenerUtil.mutListener.listen(70944) ? (getMessageCount() == 0) : (getMessageCount() > 0))))))) {
                        return this.getDistributionList().getCreatedAt();
                    }
                }
                return new Date(0);
            }
        }
        return null;
    }

    public AbstractMessageModel getLatestMessage() {
        return latestMessage;
    }

    public long getUnreadCount() {
        return this.unreadCount;
    }

    public void setUnreadCount(long unreadCount) {
        if (!ListenerUtil.mutListener.listen(70951)) {
            this.unreadCount = unreadCount;
        }
    }

    public void setLatestMessage(AbstractMessageModel latestMessage) {
        if (!ListenerUtil.mutListener.listen(70952)) {
            this.latestMessage = latestMessage;
        }
    }

    public boolean hasUnreadMessage() {
        return (ListenerUtil.mutListener.listen(70957) ? (this.unreadCount >= 0) : (ListenerUtil.mutListener.listen(70956) ? (this.unreadCount <= 0) : (ListenerUtil.mutListener.listen(70955) ? (this.unreadCount < 0) : (ListenerUtil.mutListener.listen(70954) ? (this.unreadCount != 0) : (ListenerUtil.mutListener.listen(70953) ? (this.unreadCount == 0) : (this.unreadCount > 0))))));
    }

    public GroupModel getGroup() {
        if (!ListenerUtil.mutListener.listen(70958)) {
            if (this.isGroupConversation()) {
                return ((GroupMessageReceiver) this.receiver).getGroup();
            }
        }
        return null;
    }

    public ContactModel getContact() {
        if (!ListenerUtil.mutListener.listen(70959)) {
            if (this.isContactConversation()) {
                return ((ContactMessageReceiver) this.receiver).getContact();
            }
        }
        return null;
    }

    public DistributionListModel getDistributionList() {
        if (!ListenerUtil.mutListener.listen(70960)) {
            if (this.isDistributionListConversation()) {
                return ((DistributionListMessageReceiver) this.receiver).getDistributionList();
            }
        }
        return null;
    }

    public boolean isContactConversation() {
        return this.receiver.getType() == MessageReceiver.Type_CONTACT;
    }

    public boolean isGroupConversation() {
        return this.receiver.getType() == MessageReceiver.Type_GROUP;
    }

    public boolean isDistributionListConversation() {
        return this.receiver.getType() == MessageReceiver.Type_DISTRIBUTION_LIST;
    }

    @MessageReceiverType
    public int getReceiverType() {
        return this.receiver.getType();
    }

    public MessageReceiver getReceiver() {
        return this.receiver;
    }

    public String getUid() {
        if (!ListenerUtil.mutListener.listen(70965)) {
            if (this.uid == null) {
                if (!ListenerUtil.mutListener.listen(70964)) {
                    if (this.isContactConversation()) {
                        if (!ListenerUtil.mutListener.listen(70963)) {
                            this.uid = ConversationUtil.getIdentityConversationUid(this.getContact() != null ? this.getContact().getIdentity() : null);
                        }
                    } else if (this.isGroupConversation()) {
                        if (!ListenerUtil.mutListener.listen(70962)) {
                            this.uid = ConversationUtil.getGroupConversationUid(this.getGroup() != null ? this.getGroup().getId() : null);
                        }
                    } else if (this.isDistributionListConversation()) {
                        if (!ListenerUtil.mutListener.listen(70961)) {
                            this.uid = ConversationUtil.getDistributionListConversationUid(this.getDistributionList() != null ? this.getDistributionList().getId() : null);
                        }
                    }
                }
            }
        }
        return this.uid;
    }

    public int getPosition() {
        return this.position;
    }

    public ConversationModel setPosition(int position) {
        if (!ListenerUtil.mutListener.listen(70966)) {
            this.position = position;
        }
        return this;
    }

    public boolean isTyping() {
        return this.isTyping;
    }

    public ConversationModel setIsTyping(boolean is) {
        if (!ListenerUtil.mutListener.listen(70967)) {
            this.isTyping = is;
        }
        return this;
    }

    @Override
    public String toString() {
        return getReceiver().getDisplayName();
    }
}
