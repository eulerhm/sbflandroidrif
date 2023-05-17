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
import java.util.List;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.DistributionListModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class Receiver extends Converter {

    public static final String TYPE = "type";

    public static final String ID = "id";

    public static final String DISPLAY_NAME = "displayName";

    public static final String COLOR = "color";

    public static final String DISABLED = "disabled";

    public static final String ACCESS = "access";

    public static final String CAN_DELETE = "canDelete";

    public static final String LOCKED = "locked";

    public static final String VISIBLE = "visible";

    /**
     *  Assembles and converts a list of contacts, groups and distribution lists.
     */
    public static MsgpackObjectBuilder convert(List<ContactModel> contacts, List<GroupModel> groups, List<DistributionListModel> distributionLists) throws ConversionException {
        MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        try {
            if (!ListenerUtil.mutListener.listen(62985)) {
                builder.put(Type.CONTACT, Contact.convert(contacts));
            }
            if (!ListenerUtil.mutListener.listen(62986)) {
                builder.put(Type.GROUP, Group.convert(groups));
            }
            if (!ListenerUtil.mutListener.listen(62987)) {
                builder.put(Type.DISTRIBUTION_LIST, DistributionList.convert(distributionLists));
            }
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
        return builder;
    }

    public static Utils.ModelWrapper getModel(String type, String id) throws ConversionException {
        return new Utils.ModelWrapper(type, id);
    }

    public static MessageReceiver getReceiver(String type, String id) throws ConversionException {
        return getModel(type, id).getReceiver();
    }

    public static MsgpackObjectBuilder getArguments(MessageReceiver receiver) throws ConversionException {
        Utils.ModelWrapper model = Utils.ModelWrapper.getModel(receiver);
        return getArguments(model);
    }

    public static MsgpackObjectBuilder getArguments(Utils.ModelWrapper model) throws ConversionException {
        MsgpackObjectBuilder args = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62988)) {
            args.put(TYPE, model.getType());
        }
        if (!ListenerUtil.mutListener.listen(62989)) {
            args.put(ID, model.getId());
        }
        return args;
    }

    public static MsgpackObjectBuilder getArguments(String type) {
        MsgpackObjectBuilder args = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62990)) {
            args.put(TYPE, type);
        }
        return args;
    }

    public class Type {

        public static final String CONTACT = "contact";

        public static final String GROUP = "group";

        public static final String DISTRIBUTION_LIST = "distributionList";
    }
}
