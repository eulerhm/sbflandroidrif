/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
import androidx.annotation.NonNull;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.RingtoneService;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.storage.models.ConversationModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class NotificationSettings extends Converter {

    // Constants
    private static final String SOUND = "sound";

    private static final String DND = "dnd";

    private static final String MODE = "mode";

    private static final String MENTION_ONLY = "mentionOnly";

    private static final String UNTIL = "until";

    private static final String MODE_DEFAULT = "default";

    private static final String MODE_MUTED = "muted";

    private static final String MODE_ON = "on";

    private static final String MODE_OFF = "off";

    private static final String MODE_UNTIL = "until";

    public static MsgpackObjectBuilder convert(@NonNull ConversationModel conversation) throws ConversionException {
        // Prepare objects
        final MsgpackObjectBuilder data = new MsgpackObjectBuilder();
        final MsgpackObjectBuilder sound = new MsgpackObjectBuilder();
        final MsgpackObjectBuilder dnd = new MsgpackObjectBuilder();
        // Services
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(62950)) {
            if (serviceManager == null) {
                throw new ConversionException("Could not get service manager");
            }
        }
        final DeadlineListService mutedChats = serviceManager.getMutedChatsListService();
        final DeadlineListService mentionOnlyChats = serviceManager.getMentionOnlyChatsListService();
        final RingtoneService ringtoneService = serviceManager.getRingtoneService();
        // Conversation UID
        final String uid = conversation.getReceiver().getUniqueIdString();
        if (!ListenerUtil.mutListener.listen(62954)) {
            // Sound settings
            if ((ListenerUtil.mutListener.listen(62951) ? (ringtoneService.hasCustomRingtone(uid) || ringtoneService.isSilent(uid, conversation.isGroupConversation())) : (ringtoneService.hasCustomRingtone(uid) && ringtoneService.isSilent(uid, conversation.isGroupConversation())))) {
                if (!ListenerUtil.mutListener.listen(62953)) {
                    sound.put(MODE, MODE_MUTED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(62952)) {
                    sound.put(MODE, MODE_DEFAULT);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62968)) {
            // DND settings
            if (mutedChats.has(uid)) {
                long deadline = mutedChats.getDeadline(uid);
                if (!ListenerUtil.mutListener.listen(62966)) {
                    if ((ListenerUtil.mutListener.listen(62962) ? (deadline >= 0) : (ListenerUtil.mutListener.listen(62961) ? (deadline <= 0) : (ListenerUtil.mutListener.listen(62960) ? (deadline > 0) : (ListenerUtil.mutListener.listen(62959) ? (deadline < 0) : (ListenerUtil.mutListener.listen(62958) ? (deadline != 0) : (deadline == 0))))))) {
                        throw new ConversionException("Deadline is 0 even though the chat is muted");
                    } else if (deadline == DeadlineListService.DEADLINE_INDEFINITE) {
                        if (!ListenerUtil.mutListener.listen(62965)) {
                            dnd.put(MODE, MODE_ON);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(62963)) {
                            dnd.put(MODE, MODE_UNTIL);
                        }
                        if (!ListenerUtil.mutListener.listen(62964)) {
                            dnd.put(UNTIL, deadline);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(62967)) {
                    dnd.put(MENTION_ONLY, false);
                }
            } else if (mentionOnlyChats.has(uid)) {
                if (!ListenerUtil.mutListener.listen(62956)) {
                    dnd.put(MODE, MODE_ON);
                }
                if (!ListenerUtil.mutListener.listen(62957)) {
                    dnd.put(MENTION_ONLY, true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(62955)) {
                    dnd.put(MODE, MODE_OFF);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62969)) {
            data.put(SOUND, sound);
        }
        if (!ListenerUtil.mutListener.listen(62970)) {
            data.put(DND, dnd);
        }
        return data;
    }
}
