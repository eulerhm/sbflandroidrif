/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
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

import android.graphics.Paint;
import android.widget.TextView;
import ch.threema.app.services.GroupService;
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.ConversationModel;
import ch.threema.storage.models.GroupModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AdapterUtil {

    /**
     *  Style a TextView by means of the state
     *  @param view
     *  @param contactModel
     */
    public static void styleContact(TextView view, ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(48701)) {
            if (view != null) {
                int paintFlags = view.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG);
                float alpha = 1f;
                if (!ListenerUtil.mutListener.listen(48698)) {
                    if (contactModel != null) {
                        if (!ListenerUtil.mutListener.listen(48697)) {
                            switch(contactModel.getState()) {
                                case INACTIVE:
                                    if (!ListenerUtil.mutListener.listen(48695)) {
                                        alpha = 0.4f;
                                    }
                                    break;
                                case INVALID:
                                    if (!ListenerUtil.mutListener.listen(48696)) {
                                        paintFlags = paintFlags | Paint.STRIKE_THRU_TEXT_FLAG;
                                    }
                                    break;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48699)) {
                    view.setAlpha(alpha);
                }
                if (!ListenerUtil.mutListener.listen(48700)) {
                    view.setPaintFlags(paintFlags);
                }
            }
        }
    }

    public static void styleGroup(TextView view, GroupService groupService, GroupModel groupModel) {
        if (!ListenerUtil.mutListener.listen(48707)) {
            if (view != null) {
                int paintFlags = view.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG);
                if (!ListenerUtil.mutListener.listen(48704)) {
                    if ((ListenerUtil.mutListener.listen(48702) ? (groupModel != null || !groupService.isGroupMember(groupModel)) : (groupModel != null && !groupService.isGroupMember(groupModel)))) {
                        if (!ListenerUtil.mutListener.listen(48703)) {
                            paintFlags = paintFlags | Paint.STRIKE_THRU_TEXT_FLAG;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48705)) {
                    view.setAlpha(1f);
                }
                if (!ListenerUtil.mutListener.listen(48706)) {
                    view.setPaintFlags(paintFlags);
                }
            }
        }
    }

    public static void styleConversation(TextView view, GroupService groupService, ConversationModel conversationModel) {
        if (!ListenerUtil.mutListener.listen(48710)) {
            if (conversationModel.isContactConversation()) {
                if (!ListenerUtil.mutListener.listen(48709)) {
                    styleContact(view, conversationModel.getContact());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(48708)) {
                    styleGroup(view, groupService, conversationModel.getGroup());
                }
            }
        }
    }
}
