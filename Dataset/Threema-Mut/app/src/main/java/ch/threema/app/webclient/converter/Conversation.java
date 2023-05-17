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
package ch.threema.app.webclient.converter;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.AnyThread;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ConversationTagServiceImpl;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.TagModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class Conversation extends Converter {

    public static final String POSITION = "position";

    public static final String MESSAGE_COUNT = "messageCount";

    public static final String UNREAD_COUNT = "unreadCount";

    public static final String LATEST_MESSAGE = "latestMessage";

    public static final String NOTIFICATIONS = "notifications";

    public static final String IS_STARRED = "isStarred";

    public static final String IS_UNREAD = "isUnread";

    public interface Append {

        void append(MsgpackObjectBuilder builder, ConversationModel conversation, Utils.ModelWrapper modelWrapper);
    }

    /**
     *  Converts multiple conversations to MsgpackObjectBuilder instances.
     */
    public static List<MsgpackBuilder> convert(List<ConversationModel> conversations, Append append) throws ConversionException {
        List<MsgpackBuilder> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(62696)) {
            {
                long _loopCounter757 = 0;
                for (ConversationModel conversation : conversations) {
                    ListenerUtil.loopListener.listen("_loopCounter757", ++_loopCounter757);
                    if (!ListenerUtil.mutListener.listen(62695)) {
                        list.add(convert(conversation, append));
                    }
                }
            }
        }
        return list;
    }

    /**
     *  Converts a conversation to a MsgpackObjectBuilder.
     */
    public static MsgpackBuilder convert(ConversationModel conversation) throws ConversionException {
        return convert(conversation, null);
    }

    /**
     *  Converts a conversation to a MsgpackObjectBuilder.
     */
    public static MsgpackBuilder convert(ConversationModel conversation, Append append) throws ConversionException {
        MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        final ServiceManager serviceManager = getServiceManager();
        if (!ListenerUtil.mutListener.listen(62697)) {
            if (serviceManager == null) {
                throw new ConversionException("Service manager is null");
            }
        }
        try {
            final Utils.ModelWrapper model = Utils.ModelWrapper.getModel(conversation);
            if (!ListenerUtil.mutListener.listen(62698)) {
                builder.put(Receiver.TYPE, model.getType());
            }
            if (!ListenerUtil.mutListener.listen(62699)) {
                builder.put(Receiver.ID, model.getId());
            }
            if (!ListenerUtil.mutListener.listen(62700)) {
                builder.put(POSITION, conversation.getPosition());
            }
            if (!ListenerUtil.mutListener.listen(62701)) {
                builder.put(MESSAGE_COUNT, conversation.getMessageCount());
            }
            if (!ListenerUtil.mutListener.listen(62702)) {
                builder.put(UNREAD_COUNT, conversation.getUnreadCount());
            }
            if (!ListenerUtil.mutListener.listen(62703)) {
                maybePutLatestMessage(builder, LATEST_MESSAGE, conversation);
            }
            if (!ListenerUtil.mutListener.listen(62704)) {
                builder.put(NOTIFICATIONS, NotificationSettings.convert(conversation));
            }
            final TagModel starTagModel = serviceManager.getConversationTagService().getTagModel(ConversationTagServiceImpl.FIXED_TAG_PIN);
            final boolean isStarred = serviceManager.getConversationTagService().isTaggedWith(conversation, starTagModel);
            if (!ListenerUtil.mutListener.listen(62706)) {
                if (isStarred) {
                    if (!ListenerUtil.mutListener.listen(62705)) {
                        builder.put(IS_STARRED, isStarred);
                    }
                }
            }
            final TagModel unreadTagModel = serviceManager.getConversationTagService().getTagModel(ConversationTagServiceImpl.FIXED_TAG_UNREAD);
            final boolean isUnread = serviceManager.getConversationTagService().isTaggedWith(conversation, unreadTagModel);
            if (!ListenerUtil.mutListener.listen(62707)) {
                builder.put(IS_UNREAD, isUnread);
            }
            if (!ListenerUtil.mutListener.listen(62709)) {
                if (append != null) {
                    if (!ListenerUtil.mutListener.listen(62708)) {
                        append.append(builder, conversation, model);
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
        return builder;
    }

    private static void maybePutLatestMessage(MsgpackObjectBuilder builder, String field, ConversationModel conversation) throws ConversionException {
        AbstractMessageModel message = conversation.getLatestMessage();
        if (!ListenerUtil.mutListener.listen(62711)) {
            if (message != null) {
                if (!ListenerUtil.mutListener.listen(62710)) {
                    builder.put(field, Message.convert(message, conversation.getReceiverType(), false, Message.DETAILS_NO_QUOTE));
                }
            }
        }
    }
}
