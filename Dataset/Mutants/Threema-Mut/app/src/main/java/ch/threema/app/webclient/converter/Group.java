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
import androidx.annotation.NonNull;
import ch.threema.app.services.GroupService;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class Group extends Converter {

    static final String NAME = "name";

    static final String ADMINISTRATOR = "administrator";

    static final String MEMBERS = "members";

    static final String CREATED_AT = "createdAt";

    static final String CAN_CHANGE_NAME = "canChangeName";

    static final String CAN_CHANGE_MEMBERS = "canChangeMembers";

    static final String CAN_LEAVE = "canLeave";

    static final String CAN_CHANGE_AVATAR = "canChangeAvatar";

    static final String CAN_SYNC = "canSync";

    /**
     *  Converts multiple group models to MsgpackObjectBuilder instances.
     */
    static List<MsgpackBuilder> convert(List<GroupModel> groups) throws ConversionException {
        List<MsgpackBuilder> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(62727)) {
            {
                long _loopCounter760 = 0;
                for (GroupModel group : groups) {
                    ListenerUtil.loopListener.listen("_loopCounter760", ++_loopCounter760);
                    if (!ListenerUtil.mutListener.listen(62726)) {
                        list.add(convert(group));
                    }
                }
            }
        }
        return list;
    }

    /**
     *  Converts a group model to a MsgpackObjectBuilder instance.
     */
    public static MsgpackObjectBuilder convert(GroupModel group) throws ConversionException {
        MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        try {
            final boolean isDisabled = !getGroupService().isGroupMember(group);
            final boolean isSecretChat = getHiddenChatListService().has(getGroupService().getUniqueIdString(group));
            final boolean isVisible = (ListenerUtil.mutListener.listen(62728) ? (!isSecretChat && !getPreferenceService().isPrivateChatsHidden()) : (!isSecretChat || !getPreferenceService().isPrivateChatsHidden()));
            if (!ListenerUtil.mutListener.listen(62729)) {
                builder.put(Receiver.ID, String.valueOf(group.getId()));
            }
            if (!ListenerUtil.mutListener.listen(62730)) {
                builder.put(Receiver.DISPLAY_NAME, getDisplayName(group));
            }
            if (!ListenerUtil.mutListener.listen(62731)) {
                builder.put(NAME, group.getName());
            }
            if (!ListenerUtil.mutListener.listen(62732)) {
                builder.put(Receiver.COLOR, getColor(group));
            }
            if (!ListenerUtil.mutListener.listen(62734)) {
                if (isDisabled) {
                    if (!ListenerUtil.mutListener.listen(62733)) {
                        builder.put(Receiver.DISABLED, true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(62739)) {
                builder.put(CREATED_AT, group.getCreatedAt() != null ? (ListenerUtil.mutListener.listen(62738) ? (group.getCreatedAt().getTime() % 1000) : (ListenerUtil.mutListener.listen(62737) ? (group.getCreatedAt().getTime() * 1000) : (ListenerUtil.mutListener.listen(62736) ? (group.getCreatedAt().getTime() - 1000) : (ListenerUtil.mutListener.listen(62735) ? (group.getCreatedAt().getTime() + 1000) : (group.getCreatedAt().getTime() / 1000))))) : 0);
            }
            if (!ListenerUtil.mutListener.listen(62741)) {
                if (isSecretChat) {
                    if (!ListenerUtil.mutListener.listen(62740)) {
                        builder.put(Receiver.LOCKED, true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(62743)) {
                if (!isVisible) {
                    if (!ListenerUtil.mutListener.listen(62742)) {
                        builder.put(Receiver.VISIBLE, false);
                    }
                }
            }
            final MsgpackArrayBuilder memberBuilder = new MsgpackArrayBuilder();
            if (!ListenerUtil.mutListener.listen(62745)) {
                {
                    long _loopCounter761 = 0;
                    for (ContactModel contactModel : getGroupService().getMembers(group)) {
                        ListenerUtil.loopListener.listen("_loopCounter761", ++_loopCounter761);
                        if (!ListenerUtil.mutListener.listen(62744)) {
                            memberBuilder.put(contactModel.getIdentity());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(62746)) {
                builder.put(MEMBERS, memberBuilder);
            }
            if (!ListenerUtil.mutListener.listen(62747)) {
                builder.put(ADMINISTRATOR, group.getCreatorIdentity());
            }
            // create util class or use access object
            boolean admin = getGroupService().isGroupOwner(group);
            boolean leaved = !getGroupService().isGroupMember(group);
            boolean enabled = !((ListenerUtil.mutListener.listen(62748) ? (group.isDeleted() && isDisabled) : (group.isDeleted() || isDisabled)));
            if (!ListenerUtil.mutListener.listen(62754)) {
                // define access
                builder.put(Receiver.ACCESS, (new MsgpackObjectBuilder()).put(Receiver.CAN_DELETE, (ListenerUtil.mutListener.listen(62753) ? (admin && leaved) : (admin || leaved))).put(CAN_CHANGE_AVATAR, (ListenerUtil.mutListener.listen(62752) ? (admin || enabled) : (admin && enabled))).put(CAN_CHANGE_NAME, (ListenerUtil.mutListener.listen(62751) ? (admin || enabled) : (admin && enabled))).put(CAN_CHANGE_MEMBERS, (ListenerUtil.mutListener.listen(62750) ? (admin || enabled) : (admin && enabled))).put(CAN_SYNC, (ListenerUtil.mutListener.listen(62749) ? (admin || enabled) : (admin && enabled))).put(CAN_LEAVE, enabled));
            }
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
        return builder;
    }

    private static String getDisplayName(GroupModel group) throws ConversionException {
        return NameUtil.getDisplayName(group, getGroupService());
    }

    private static String getColor(GroupModel group) throws ConversionException {
        return String.format("#%06X", (0xFFFFFF & getGroupService().getPrimaryColor(group)));
    }

    /**
     *  Return the filter used to query groups from the group service.
     */
    @NonNull
    public static GroupService.GroupFilter getGroupFilter() {
        return new GroupService.GroupFilter() {

            @Override
            public boolean sortingByDate() {
                return false;
            }

            @Override
            public boolean sortingAscending() {
                return false;
            }

            @Override
            public boolean sortingByName() {
                return false;
            }

            @Override
            public boolean withDeleted() {
                return false;
            }

            @Override
            public boolean withDeserted() {
                return true;
            }
        };
    }
}
