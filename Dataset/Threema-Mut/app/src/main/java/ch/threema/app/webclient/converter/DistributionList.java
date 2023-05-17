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

import androidx.annotation.AnyThread;
import java.util.ArrayList;
import java.util.List;
import ch.threema.app.services.DistributionListService;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class DistributionList extends Converter {

    private static final String MEMBERS = "members";

    private static final String CAN_CHANGE_MEMBERS = "canChangeMembers";

    /**
     *  Converts multiple distribution list models to MsgpackObjectBuilder instances.
     */
    public static List<MsgpackBuilder> convert(List<DistributionListModel> distributionLists) throws ConversionException {
        List<MsgpackBuilder> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(62715)) {
            {
                long _loopCounter758 = 0;
                for (DistributionListModel distributionList : distributionLists) {
                    ListenerUtil.loopListener.listen("_loopCounter758", ++_loopCounter758);
                    if (!ListenerUtil.mutListener.listen(62714)) {
                        list.add(convert(distributionList));
                    }
                }
            }
        }
        return list;
    }

    /**
     *  Converts a distribution list model to a MsgpackObjectBuilder instance.
     */
    public static MsgpackObjectBuilder convert(DistributionListModel distributionList) throws ConversionException {
        final DistributionListService distributionListService = getDistributionListService();
        final MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        try {
            if (!ListenerUtil.mutListener.listen(62716)) {
                builder.put(Receiver.ID, getId(distributionList));
            }
            if (!ListenerUtil.mutListener.listen(62717)) {
                builder.put(Receiver.DISPLAY_NAME, getName(distributionList));
            }
            if (!ListenerUtil.mutListener.listen(62718)) {
                builder.put(Receiver.COLOR, getColor(distributionList));
            }
            if (!ListenerUtil.mutListener.listen(62719)) {
                builder.put(Receiver.ACCESS, (new MsgpackObjectBuilder()).put(Receiver.CAN_DELETE, true).put(CAN_CHANGE_MEMBERS, true));
            }
            final boolean isSecretChat = getHiddenChatListService().has(distributionListService.getUniqueIdString(distributionList));
            final boolean isVisible = (ListenerUtil.mutListener.listen(62720) ? (!isSecretChat && !getPreferenceService().isPrivateChatsHidden()) : (!isSecretChat || !getPreferenceService().isPrivateChatsHidden()));
            if (!ListenerUtil.mutListener.listen(62721)) {
                builder.put(Receiver.LOCKED, isSecretChat);
            }
            if (!ListenerUtil.mutListener.listen(62722)) {
                builder.put(Receiver.VISIBLE, isVisible);
            }
            final MsgpackArrayBuilder memberBuilder = new MsgpackArrayBuilder();
            if (!ListenerUtil.mutListener.listen(62724)) {
                {
                    long _loopCounter759 = 0;
                    for (ContactModel contactModel : distributionListService.getMembers(distributionList)) {
                        ListenerUtil.loopListener.listen("_loopCounter759", ++_loopCounter759);
                        if (!ListenerUtil.mutListener.listen(62723)) {
                            memberBuilder.put(contactModel.getIdentity());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(62725)) {
                builder.put(MEMBERS, memberBuilder);
            }
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
        return builder;
    }

    public static String getId(DistributionListModel distributionList) throws ConversionException {
        try {
            return String.valueOf(distributionList.getId());
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
    }

    public static String getName(DistributionListModel distributionList) throws ConversionException {
        try {
            return NameUtil.getDisplayName(distributionList, getDistributionListService());
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
    }

    public static String getColor(DistributionListModel distributionList) throws ConversionException {
        try {
            return String.format("#%06X", (0xFFFFFF & getDistributionListService().getPrimaryColor(distributionList)));
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
    }
}
