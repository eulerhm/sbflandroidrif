/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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

import android.Manifest;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;
import java.util.Set;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.messagereceiver.ContactMessageReceiver;
import ch.threema.app.messagereceiver.GroupMessageReceiver;
import ch.threema.app.messagereceiver.MessageReceiver;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeadlineListService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.stores.IdentityStore;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DNDUtil {

    private static final Logger logger = LoggerFactory.getLogger(DNDUtil.class);

    private DeadlineListService mutedChatsListService;

    private DeadlineListService mentionOnlyChatsListService;

    private final IdentityStore identityStore;

    private final Context context;

    // Singleton stuff
    private static DNDUtil sInstance = null;

    public static synchronized DNDUtil getInstance() {
        if (!ListenerUtil.mutListener.listen(50999)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(50998)) {
                    sInstance = new DNDUtil();
                }
            }
        }
        return sInstance;
    }

    private DNDUtil() {
        this.context = ThreemaApplication.getAppContext();
        if (!ListenerUtil.mutListener.listen(51000)) {
            this.mutedChatsListService = ThreemaApplication.getServiceManager().getMutedChatsListService();
        }
        if (!ListenerUtil.mutListener.listen(51001)) {
            this.mentionOnlyChatsListService = ThreemaApplication.getServiceManager().getMentionOnlyChatsListService();
        }
        this.identityStore = ThreemaApplication.getServiceManager().getIdentityStore();
    }

    /**
     *  Returns true if the user is mentioned in the provided message text or the text contains an "@All" mention
     *  @param rawMessageText Raw message text without san substitutions for mentions
     *  @return true if the user is addressed by a mention, false otherwise
     */
    private boolean isUserMentioned(@Nullable CharSequence rawMessageText) {
        if (!ListenerUtil.mutListener.listen(51009)) {
            if (rawMessageText != null) {
                if (!ListenerUtil.mutListener.listen(51008)) {
                    if ((ListenerUtil.mutListener.listen(51006) ? (rawMessageText.length() >= 10) : (ListenerUtil.mutListener.listen(51005) ? (rawMessageText.length() <= 10) : (ListenerUtil.mutListener.listen(51004) ? (rawMessageText.length() < 10) : (ListenerUtil.mutListener.listen(51003) ? (rawMessageText.length() != 10) : (ListenerUtil.mutListener.listen(51002) ? (rawMessageText.length() == 10) : (rawMessageText.length() > 10))))))) {
                        return (ListenerUtil.mutListener.listen(51007) ? (rawMessageText.toString().contains("@[" + ContactService.ALL_USERS_PLACEHOLDER_ID + "]") && rawMessageText.toString().contains("@[" + identityStore.getIdentity() + "]")) : (rawMessageText.toString().contains("@[" + ContactService.ALL_USERS_PLACEHOLDER_ID + "]") || rawMessageText.toString().contains("@[" + identityStore.getIdentity() + "]")));
                    }
                }
            }
        }
        // no message text - no mention
        return false;
    }

    /**
     *  Returns true if the chat for the provided MessageReceiver is permanently or temporarily muted AT THIS TIME and
     *  no intrusive notification should be shown for an incoming message
     *  If a message text is provided it is checked for possible mentions - group messages only
     *  @param messageReceiver MessageReceiver to check for DND status
     *  @param rawMessageText Text of the incoming message (optional, group messages only)
     *  @return true if chat is muted
     */
    public boolean isMuted(MessageReceiver messageReceiver, CharSequence rawMessageText) {
        // ok, it's muted
        return (ListenerUtil.mutListener.listen(51010) ? (isMutedPrivate(messageReceiver, rawMessageText) && isMutedWork()) : (isMutedPrivate(messageReceiver, rawMessageText) || isMutedWork()));
    }

    public boolean isMutedPrivate(MessageReceiver messageReceiver, CharSequence rawMessageText) {
        String uniqueId = messageReceiver.getUniqueIdString();
        if (!ListenerUtil.mutListener.listen(51013)) {
            if ((ListenerUtil.mutListener.listen(51011) ? (this.mutedChatsListService != null || this.mutedChatsListService.has(uniqueId)) : (this.mutedChatsListService != null && this.mutedChatsListService.has(uniqueId)))) {
                if (!ListenerUtil.mutListener.listen(51012)) {
                    // user has set DND option on this chat
                    logger.info("Chat is muted");
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(51017)) {
            if (messageReceiver instanceof GroupMessageReceiver) {
                if (!ListenerUtil.mutListener.listen(51016)) {
                    if ((ListenerUtil.mutListener.listen(51014) ? (this.mentionOnlyChatsListService != null || this.mentionOnlyChatsListService.has(uniqueId)) : (this.mentionOnlyChatsListService != null && this.mentionOnlyChatsListService.has(uniqueId)))) {
                        if (!ListenerUtil.mutListener.listen(51015)) {
                            // user has "DND except when mentioned" option enabled on this chat
                            logger.info("Chat is mention only");
                        }
                        // user is not mentioned => mute
                        return !isUserMentioned(rawMessageText);
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Check if Work DND schedule is currently active
     *  @return true if we're currently outside of the working hours set by the user and Work DND is currently enabled, false otherwise
     */
    public boolean isMutedWork() {
        if (!ListenerUtil.mutListener.listen(51061)) {
            if (ConfigUtils.isWorkBuild()) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                if (!ListenerUtil.mutListener.listen(51060)) {
                    if (sharedPreferences.getBoolean(context.getString(R.string.preferences__working_days_enable), false)) {
                        // day of week starts with 1 in Java
                        int dayOfWeek = (ListenerUtil.mutListener.listen(51021) ? (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) % 1) : (ListenerUtil.mutListener.listen(51020) ? (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) / 1) : (ListenerUtil.mutListener.listen(51019) ? (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) * 1) : (ListenerUtil.mutListener.listen(51018) ? (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 1) : (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1)))));
                        Set<String> selectedWorkingDays = sharedPreferences.getStringSet(context.getString(R.string.preferences__working_days), null);
                        if (!ListenerUtil.mutListener.listen(51059)) {
                            if (selectedWorkingDays != null) {
                                if (!ListenerUtil.mutListener.listen(51058)) {
                                    if (!selectedWorkingDays.contains(String.valueOf(dayOfWeek))) {
                                        // it's not a working day today
                                        return true;
                                    } else {
                                        // check if hours match as well
                                        int currentTimeStamp = (ListenerUtil.mutListener.listen(51029) ? ((ListenerUtil.mutListener.listen(51025) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) % 60) : (ListenerUtil.mutListener.listen(51024) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) / 60) : (ListenerUtil.mutListener.listen(51023) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 60) : (ListenerUtil.mutListener.listen(51022) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 60) : (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60))))) % Calendar.getInstance().get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(51028) ? ((ListenerUtil.mutListener.listen(51025) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) % 60) : (ListenerUtil.mutListener.listen(51024) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) / 60) : (ListenerUtil.mutListener.listen(51023) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 60) : (ListenerUtil.mutListener.listen(51022) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 60) : (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60))))) / Calendar.getInstance().get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(51027) ? ((ListenerUtil.mutListener.listen(51025) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) % 60) : (ListenerUtil.mutListener.listen(51024) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) / 60) : (ListenerUtil.mutListener.listen(51023) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 60) : (ListenerUtil.mutListener.listen(51022) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 60) : (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60))))) * Calendar.getInstance().get(Calendar.MINUTE)) : (ListenerUtil.mutListener.listen(51026) ? ((ListenerUtil.mutListener.listen(51025) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) % 60) : (ListenerUtil.mutListener.listen(51024) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) / 60) : (ListenerUtil.mutListener.listen(51023) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 60) : (ListenerUtil.mutListener.listen(51022) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 60) : (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60))))) - Calendar.getInstance().get(Calendar.MINUTE)) : ((ListenerUtil.mutListener.listen(51025) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) % 60) : (ListenerUtil.mutListener.listen(51024) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) / 60) : (ListenerUtil.mutListener.listen(51023) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 60) : (ListenerUtil.mutListener.listen(51022) ? (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 60) : (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * 60))))) + Calendar.getInstance().get(Calendar.MINUTE))))));
                                        try {
                                            String[] startTime = sharedPreferences.getString(context.getString(R.string.preferences__work_time_start), "00:00").split(":");
                                            String[] endTime = sharedPreferences.getString(context.getString(R.string.preferences__work_time_end), "23:59").split(":");
                                            int startTimeStamp = (ListenerUtil.mutListener.listen(51037) ? ((ListenerUtil.mutListener.listen(51033) ? (Integer.parseInt(startTime[0]) % 60) : (ListenerUtil.mutListener.listen(51032) ? (Integer.parseInt(startTime[0]) / 60) : (ListenerUtil.mutListener.listen(51031) ? (Integer.parseInt(startTime[0]) - 60) : (ListenerUtil.mutListener.listen(51030) ? (Integer.parseInt(startTime[0]) + 60) : (Integer.parseInt(startTime[0]) * 60))))) % Integer.parseInt(startTime[1])) : (ListenerUtil.mutListener.listen(51036) ? ((ListenerUtil.mutListener.listen(51033) ? (Integer.parseInt(startTime[0]) % 60) : (ListenerUtil.mutListener.listen(51032) ? (Integer.parseInt(startTime[0]) / 60) : (ListenerUtil.mutListener.listen(51031) ? (Integer.parseInt(startTime[0]) - 60) : (ListenerUtil.mutListener.listen(51030) ? (Integer.parseInt(startTime[0]) + 60) : (Integer.parseInt(startTime[0]) * 60))))) / Integer.parseInt(startTime[1])) : (ListenerUtil.mutListener.listen(51035) ? ((ListenerUtil.mutListener.listen(51033) ? (Integer.parseInt(startTime[0]) % 60) : (ListenerUtil.mutListener.listen(51032) ? (Integer.parseInt(startTime[0]) / 60) : (ListenerUtil.mutListener.listen(51031) ? (Integer.parseInt(startTime[0]) - 60) : (ListenerUtil.mutListener.listen(51030) ? (Integer.parseInt(startTime[0]) + 60) : (Integer.parseInt(startTime[0]) * 60))))) * Integer.parseInt(startTime[1])) : (ListenerUtil.mutListener.listen(51034) ? ((ListenerUtil.mutListener.listen(51033) ? (Integer.parseInt(startTime[0]) % 60) : (ListenerUtil.mutListener.listen(51032) ? (Integer.parseInt(startTime[0]) / 60) : (ListenerUtil.mutListener.listen(51031) ? (Integer.parseInt(startTime[0]) - 60) : (ListenerUtil.mutListener.listen(51030) ? (Integer.parseInt(startTime[0]) + 60) : (Integer.parseInt(startTime[0]) * 60))))) - Integer.parseInt(startTime[1])) : ((ListenerUtil.mutListener.listen(51033) ? (Integer.parseInt(startTime[0]) % 60) : (ListenerUtil.mutListener.listen(51032) ? (Integer.parseInt(startTime[0]) / 60) : (ListenerUtil.mutListener.listen(51031) ? (Integer.parseInt(startTime[0]) - 60) : (ListenerUtil.mutListener.listen(51030) ? (Integer.parseInt(startTime[0]) + 60) : (Integer.parseInt(startTime[0]) * 60))))) + Integer.parseInt(startTime[1]))))));
                                            int endTimeStamp = (ListenerUtil.mutListener.listen(51045) ? ((ListenerUtil.mutListener.listen(51041) ? (Integer.parseInt(endTime[0]) % 60) : (ListenerUtil.mutListener.listen(51040) ? (Integer.parseInt(endTime[0]) / 60) : (ListenerUtil.mutListener.listen(51039) ? (Integer.parseInt(endTime[0]) - 60) : (ListenerUtil.mutListener.listen(51038) ? (Integer.parseInt(endTime[0]) + 60) : (Integer.parseInt(endTime[0]) * 60))))) % Integer.parseInt(endTime[1])) : (ListenerUtil.mutListener.listen(51044) ? ((ListenerUtil.mutListener.listen(51041) ? (Integer.parseInt(endTime[0]) % 60) : (ListenerUtil.mutListener.listen(51040) ? (Integer.parseInt(endTime[0]) / 60) : (ListenerUtil.mutListener.listen(51039) ? (Integer.parseInt(endTime[0]) - 60) : (ListenerUtil.mutListener.listen(51038) ? (Integer.parseInt(endTime[0]) + 60) : (Integer.parseInt(endTime[0]) * 60))))) / Integer.parseInt(endTime[1])) : (ListenerUtil.mutListener.listen(51043) ? ((ListenerUtil.mutListener.listen(51041) ? (Integer.parseInt(endTime[0]) % 60) : (ListenerUtil.mutListener.listen(51040) ? (Integer.parseInt(endTime[0]) / 60) : (ListenerUtil.mutListener.listen(51039) ? (Integer.parseInt(endTime[0]) - 60) : (ListenerUtil.mutListener.listen(51038) ? (Integer.parseInt(endTime[0]) + 60) : (Integer.parseInt(endTime[0]) * 60))))) * Integer.parseInt(endTime[1])) : (ListenerUtil.mutListener.listen(51042) ? ((ListenerUtil.mutListener.listen(51041) ? (Integer.parseInt(endTime[0]) % 60) : (ListenerUtil.mutListener.listen(51040) ? (Integer.parseInt(endTime[0]) / 60) : (ListenerUtil.mutListener.listen(51039) ? (Integer.parseInt(endTime[0]) - 60) : (ListenerUtil.mutListener.listen(51038) ? (Integer.parseInt(endTime[0]) + 60) : (Integer.parseInt(endTime[0]) * 60))))) - Integer.parseInt(endTime[1])) : ((ListenerUtil.mutListener.listen(51041) ? (Integer.parseInt(endTime[0]) % 60) : (ListenerUtil.mutListener.listen(51040) ? (Integer.parseInt(endTime[0]) / 60) : (ListenerUtil.mutListener.listen(51039) ? (Integer.parseInt(endTime[0]) - 60) : (ListenerUtil.mutListener.listen(51038) ? (Integer.parseInt(endTime[0]) + 60) : (Integer.parseInt(endTime[0]) * 60))))) + Integer.parseInt(endTime[1]))))));
                                            if (!ListenerUtil.mutListener.listen(51057)) {
                                                if ((ListenerUtil.mutListener.listen(51056) ? ((ListenerUtil.mutListener.listen(51050) ? (currentTimeStamp >= startTimeStamp) : (ListenerUtil.mutListener.listen(51049) ? (currentTimeStamp <= startTimeStamp) : (ListenerUtil.mutListener.listen(51048) ? (currentTimeStamp > startTimeStamp) : (ListenerUtil.mutListener.listen(51047) ? (currentTimeStamp != startTimeStamp) : (ListenerUtil.mutListener.listen(51046) ? (currentTimeStamp == startTimeStamp) : (currentTimeStamp < startTimeStamp)))))) && (ListenerUtil.mutListener.listen(51055) ? (currentTimeStamp >= endTimeStamp) : (ListenerUtil.mutListener.listen(51054) ? (currentTimeStamp <= endTimeStamp) : (ListenerUtil.mutListener.listen(51053) ? (currentTimeStamp < endTimeStamp) : (ListenerUtil.mutListener.listen(51052) ? (currentTimeStamp != endTimeStamp) : (ListenerUtil.mutListener.listen(51051) ? (currentTimeStamp == endTimeStamp) : (currentTimeStamp > endTimeStamp))))))) : ((ListenerUtil.mutListener.listen(51050) ? (currentTimeStamp >= startTimeStamp) : (ListenerUtil.mutListener.listen(51049) ? (currentTimeStamp <= startTimeStamp) : (ListenerUtil.mutListener.listen(51048) ? (currentTimeStamp > startTimeStamp) : (ListenerUtil.mutListener.listen(51047) ? (currentTimeStamp != startTimeStamp) : (ListenerUtil.mutListener.listen(51046) ? (currentTimeStamp == startTimeStamp) : (currentTimeStamp < startTimeStamp)))))) || (ListenerUtil.mutListener.listen(51055) ? (currentTimeStamp >= endTimeStamp) : (ListenerUtil.mutListener.listen(51054) ? (currentTimeStamp <= endTimeStamp) : (ListenerUtil.mutListener.listen(51053) ? (currentTimeStamp < endTimeStamp) : (ListenerUtil.mutListener.listen(51052) ? (currentTimeStamp != endTimeStamp) : (ListenerUtil.mutListener.listen(51051) ? (currentTimeStamp == endTimeStamp) : (currentTimeStamp > endTimeStamp))))))))) {
                                                    return true;
                                                }
                                            }
                                        } catch (Exception ignored) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean isStarredContact(MessageReceiver messageReceiver) {
        if (!ListenerUtil.mutListener.listen(51062)) {
            if (!(messageReceiver instanceof ContactMessageReceiver)) {
                return false;
            }
        }
        ContactModel contactModel = ((ContactMessageReceiver) messageReceiver).getContact();
        PreferenceService preferenceService;
        try {
            preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
        } catch (NullPointerException e) {
            return false;
        }
        if (!ListenerUtil.mutListener.listen(51063)) {
            if (!preferenceService.isSyncContacts()) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(51064)) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        Uri contactUri;
        String lookupKey = contactModel.getAndroidContactLookupKey();
        if (lookupKey != null) {
            try {
                contactUri = AndroidContactUtil.getInstance().getAndroidContactUri(contactModel);
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(51065)) {
                    logger.error("Could not get Android contact URI", e);
                }
                return false;
            }
            if (!ListenerUtil.mutListener.listen(51075)) {
                if (contactUri != null) {
                    String[] projection = { ContactsContract.Contacts._ID };
                    String selection = ContactsContract.Contacts.STARRED + "=1";
                    try (Cursor cursor = context.getContentResolver().query(contactUri, projection, selection, null, null)) {
                        if (!ListenerUtil.mutListener.listen(51074)) {
                            if ((ListenerUtil.mutListener.listen(51072) ? (cursor != null || (ListenerUtil.mutListener.listen(51071) ? (cursor.getCount() >= 0) : (ListenerUtil.mutListener.listen(51070) ? (cursor.getCount() <= 0) : (ListenerUtil.mutListener.listen(51069) ? (cursor.getCount() < 0) : (ListenerUtil.mutListener.listen(51068) ? (cursor.getCount() != 0) : (ListenerUtil.mutListener.listen(51067) ? (cursor.getCount() == 0) : (cursor.getCount() > 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(51071) ? (cursor.getCount() >= 0) : (ListenerUtil.mutListener.listen(51070) ? (cursor.getCount() <= 0) : (ListenerUtil.mutListener.listen(51069) ? (cursor.getCount() < 0) : (ListenerUtil.mutListener.listen(51068) ? (cursor.getCount() != 0) : (ListenerUtil.mutListener.listen(51067) ? (cursor.getCount() == 0) : (cursor.getCount() > 0))))))))) {
                                if (!ListenerUtil.mutListener.listen(51073)) {
                                    logger.info("Contact is starred");
                                }
                                return true;
                            }
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(51066)) {
                            logger.error("Contact lookup failed", e);
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Check if the contact specified in messageReceiver is muted in the system. "Starred" contacts may override the global DND setting in "priority" mode
     *  and should be signalled.
     *  @param messageReceiver A MessageReceiver representing a ContactModel
     *  @param notificationManager
     *  @param notificationManagerCompat
     *  @return false if the contact is not muted in the system and a ringtone should be played, false otherwise
     */
    public boolean isSystemMuted(MessageReceiver messageReceiver, NotificationManager notificationManager, NotificationManagerCompat notificationManagerCompat) {
        boolean isSystemMuted = !notificationManagerCompat.areNotificationsEnabled();
        if (!ListenerUtil.mutListener.listen(51085)) {
            if (messageReceiver instanceof ContactMessageReceiver) {
                if (!ListenerUtil.mutListener.listen(51084)) {
                    if ((ListenerUtil.mutListener.listen(51080) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(51079) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(51078) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(51077) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(51076) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(51083)) {
                            /* we do not play a ringtone sound if system-wide DND is enabled - except for starred contacts */
                            switch(notificationManager.getCurrentInterruptionFilter()) {
                                case NotificationManager.INTERRUPTION_FILTER_NONE:
                                    if (!ListenerUtil.mutListener.listen(51081)) {
                                        isSystemMuted = true;
                                    }
                                    break;
                                case NotificationManager.INTERRUPTION_FILTER_PRIORITY:
                                    if (!ListenerUtil.mutListener.listen(51082)) {
                                        isSystemMuted = !isStarredContact(messageReceiver);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return isSystemMuted;
    }
}
