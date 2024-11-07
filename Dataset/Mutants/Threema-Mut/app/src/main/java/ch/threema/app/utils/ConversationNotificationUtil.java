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
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.os.Build;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.HashMap;
import androidx.core.app.Person;
import androidx.core.graphics.drawable.IconCompat;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.FileService;
import ch.threema.app.services.GroupService;
import ch.threema.app.services.MessageService;
import ch.threema.app.services.NotificationService;
import ch.threema.app.services.ShortcutService;
import ch.threema.base.ThreemaException;
import ch.threema.client.file.FileData;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageModel;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.MessageContentsType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConversationNotificationUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConversationNotificationUtil.class);

    protected static final HashMap<String, NotificationService.ConversationNotificationGroup> notificationGroupHashMap = new HashMap<>();

    public static NotificationService.ConversationNotification convert(Context context, AbstractMessageModel messageModel, ContactService contactService, GroupService groupService, DeadlineListService hiddenChatsListService) {
        NotificationService.ConversationNotification conversationNotification = null;
        if (!ListenerUtil.mutListener.listen(50785)) {
            if (messageModel instanceof MessageModel) {
                if (!ListenerUtil.mutListener.listen(50784)) {
                    conversationNotification = create(context, (MessageModel) messageModel, contactService, hiddenChatsListService);
                }
            } else if (messageModel instanceof GroupMessageModel) {
                if (!ListenerUtil.mutListener.listen(50783)) {
                    conversationNotification = create(context, (GroupMessageModel) messageModel, groupService, hiddenChatsListService);
                }
            }
        }
        return conversationNotification;
    }

    private static MessageService.MessageString getMessage(AbstractMessageModel messageModel) {
        // load lazy
        try {
            return ThreemaApplication.getServiceManager().getMessageService().getMessageString(messageModel, -1, !ConfigUtils.canDoGroupedNotifications());
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(50786)) {
                logger.error("Exception", e);
            }
            return new MessageService.MessageString(null);
        }
    }

    private static Person getSender(AbstractMessageModel messageModel) {
        // load lazy
        try {
            final ContactService contactService = ThreemaApplication.getServiceManager().getContactService();
            final ContactModel contactModel = contactService.getByIdentity(messageModel.getIdentity());
            Person.Builder builder = new Person.Builder().setKey(contactService.getUniqueIdString(contactModel)).setName(NameUtil.getShortName(ThreemaApplication.getAppContext(), messageModel, contactService));
            Bitmap avatar = contactService.getAvatar(contactModel, false);
            if (!ListenerUtil.mutListener.listen(50789)) {
                if (avatar != null) {
                    IconCompat iconCompat = IconCompat.createWithBitmap(avatar);
                    if (!ListenerUtil.mutListener.listen(50788)) {
                        builder.setIcon(iconCompat);
                    }
                }
            }
            return builder.build();
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(50787)) {
                logger.error("ThreemaException", e);
            }
            return null;
        }
    }

    private static MessageType getMessageType(AbstractMessageModel messageModel) {
        return messageModel.getType();
    }

    private static Date getWhen(AbstractMessageModel messageModel) {
        return messageModel.getCreatedAt();
    }

    private static NotificationService.ConversationNotification create(final Context context, final MessageModel messageModel, final ContactService contactService, final DeadlineListService hiddenChatsListService) {
        final ContactModel contactModel = contactService.getByIdentity(messageModel.getIdentity());
        String groupUid = "i" + messageModel.getIdentity();
        synchronized (notificationGroupHashMap) {
            NotificationService.ConversationNotificationGroup group = notificationGroupHashMap.get(groupUid);
            String longName = hiddenChatsListService.has(contactService.getUniqueIdString(contactModel)) ? context.getString(R.string.private_chat_subject) : NameUtil.getDisplayNameOrNickname(contactModel, true);
            String shortName = hiddenChatsListService.has(contactService.getUniqueIdString(contactModel)) ? context.getString(R.string.private_chat_subject) : NameUtil.getShortName(contactModel);
            String contactLookupUri = contactService.getAndroidContactLookupUriString(contactModel);
            if (group == null) {
                if (!ListenerUtil.mutListener.listen(50793)) {
                    group = new NotificationService.ConversationNotificationGroup(groupUid, longName, shortName, contactService.createReceiver(contactModel), new NotificationService.FetchBitmap() {

                        @Override
                        public Bitmap fetch() {
                            if (!ListenerUtil.mutListener.listen(50792)) {
                                // lacy stuff
                                if (contactService != null) {
                                    return contactService.getAvatar(hiddenChatsListService.has(contactService.getUniqueIdString(contactModel)) ? null : contactModel, false);
                                }
                            }
                            return null;
                        }
                    }, contactLookupUri);
                }
                if (!ListenerUtil.mutListener.listen(50794)) {
                    notificationGroupHashMap.put(groupUid, group);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50790)) {
                    // contact name may change between notifications - set it again
                    group.setName(longName);
                }
                if (!ListenerUtil.mutListener.listen(50791)) {
                    group.setShortName(shortName);
                }
            }
            return new NotificationService.ConversationNotification(getMessage(messageModel), getWhen(messageModel), getId(messageModel), getUid(messageModel), group, getFetchThumbnail(messageModel), getShortcut(messageModel), getSender(messageModel), getMessageType(messageModel));
        }
    }

    private static NotificationService.ConversationNotification create(final Context context, final GroupMessageModel messageModel, final GroupService groupService, final DeadlineListService hiddenChatsListService) {
        final GroupModel groupModel = groupService.getById(messageModel.getGroupId());
        String groupUid = "g" + messageModel.getGroupId();
        synchronized (notificationGroupHashMap) {
            NotificationService.ConversationNotificationGroup group = notificationGroupHashMap.get(groupUid);
            String name = hiddenChatsListService.has(groupService.getUniqueIdString(groupModel)) ? context.getString(R.string.private_chat_subject) : NameUtil.getDisplayName(groupService.getById(messageModel.getGroupId()), groupService);
            if (group == null) {
                if (!ListenerUtil.mutListener.listen(50798)) {
                    group = new NotificationService.ConversationNotificationGroup(groupUid, name, name, groupService.createReceiver(groupModel), new NotificationService.FetchBitmap() {

                        @Override
                        public Bitmap fetch() {
                            if (!ListenerUtil.mutListener.listen(50797)) {
                                if (groupService != null) {
                                    return groupService.getAvatar(hiddenChatsListService.has(groupService.getUniqueIdString(groupModel)) ? null : groupModel, false);
                                }
                            }
                            return null;
                        }
                    }, null);
                }
                if (!ListenerUtil.mutListener.listen(50799)) {
                    notificationGroupHashMap.put(groupUid, group);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(50795)) {
                    // group name may change between notifications - set it again
                    group.setName(name);
                }
                if (!ListenerUtil.mutListener.listen(50796)) {
                    group.setShortName(name);
                }
            }
            return new NotificationService.ConversationNotification(getMessage(messageModel), getWhen(messageModel), getId(messageModel), getUid(messageModel), group, getFetchThumbnail(messageModel), getShortcut(messageModel), getSender(messageModel), getMessageType(messageModel));
        }
    }

    public static String getUid(AbstractMessageModel messageModel) {
        return messageModel.getUid();
    }

    public static int getId(AbstractMessageModel messageModel) {
        return messageModel.getId();
    }

    public static NotificationService.FetchBitmap getFetchThumbnail(final AbstractMessageModel messageModel) {
        if (!ListenerUtil.mutListener.listen(50808)) {
            if ((ListenerUtil.mutListener.listen(50800) ? (messageModel.getMessageContentsType() == MessageContentsType.IMAGE && messageModel.getMessageContentsType() == MessageContentsType.VIDEO) : (messageModel.getMessageContentsType() == MessageContentsType.IMAGE || messageModel.getMessageContentsType() == MessageContentsType.VIDEO))) {
                if (!ListenerUtil.mutListener.listen(50802)) {
                    if ((ListenerUtil.mutListener.listen(50801) ? (messageModel.getType() == MessageType.FILE || messageModel.getFileData().getRenderingType() != FileData.RENDERING_MEDIA) : (messageModel.getType() == MessageType.FILE && messageModel.getFileData().getRenderingType() != FileData.RENDERING_MEDIA))) {
                        return null;
                    }
                }
                return new NotificationService.FetchBitmap() {

                    @Override
                    public Bitmap fetch() {
                        // ... its the evil way!
                        FileService f = null;
                        if (!ListenerUtil.mutListener.listen(50805)) {
                            if (ThreemaApplication.getServiceManager() != null) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(50804)) {
                                        f = ThreemaApplication.getServiceManager().getFileService();
                                    }
                                } catch (FileSystemNotPresentException e) {
                                    if (!ListenerUtil.mutListener.listen(50803)) {
                                        logger.error("FileSystemNotPresentException", e);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(50807)) {
                            if (f != null) {
                                try {
                                    return f.getMessageThumbnailBitmap(messageModel, null);
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(50806)) {
                                        logger.debug("cannot fetch thumbnail, abort");
                                    }
                                }
                            }
                        }
                        return null;
                    }
                };
            }
        }
        return null;
    }

    public static ShortcutInfo getShortcut(final AbstractMessageModel messageModel) {
        ShortcutService shortcutService = null;
        ContactService contactService = null;
        GroupService groupService = null;
        try {
            if (!ListenerUtil.mutListener.listen(50810)) {
                shortcutService = ThreemaApplication.getServiceManager().getShortcutService();
            }
            if (!ListenerUtil.mutListener.listen(50811)) {
                contactService = ThreemaApplication.getServiceManager().getContactService();
            }
            if (!ListenerUtil.mutListener.listen(50812)) {
                groupService = ThreemaApplication.getServiceManager().getGroupService();
            }
        } catch (ThreemaException e) {
            if (!ListenerUtil.mutListener.listen(50809)) {
                logger.error("Exception", e);
            }
            return null;
        }
        if (!ListenerUtil.mutListener.listen(50822)) {
            if ((ListenerUtil.mutListener.listen(50818) ? (TestUtil.required(shortcutService, contactService, groupService) || ((ListenerUtil.mutListener.listen(50817) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50816) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50815) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50814) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50813) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)))))))) : (TestUtil.required(shortcutService, contactService, groupService) && ((ListenerUtil.mutListener.listen(50817) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50816) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50815) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50814) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(50813) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)))))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(50820)) {
                        if (messageModel instanceof MessageModel) {
                            return shortcutService.getShortcutInfo(contactService.getByIdentity(messageModel.getIdentity()), ShortcutService.TYPE_NONE);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(50821)) {
                        if (messageModel instanceof GroupMessageModel) {
                            return shortcutService.getShortcutInfo(groupService.getById(((GroupMessageModel) messageModel).getGroupId()));
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(50819)) {
                        logger.error("cannot fetch shortcut, abort");
                    }
                    return null;
                }
            }
        }
        return null;
    }
}
