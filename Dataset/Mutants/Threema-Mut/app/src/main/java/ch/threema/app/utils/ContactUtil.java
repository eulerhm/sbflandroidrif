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
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import androidx.annotation.DrawableRes;
import androidx.appcompat.content.res.AppCompatResources;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.services.FileService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.PreferenceService;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactUtil {

    private static final Logger logger = LoggerFactory.getLogger(ContactUtil.class);

    public static final int CHANNEL_NAME_MAX_LENGTH_BYTES = 256;

    /**
     *  check if this contact is *currently* linked to an android contact
     *  @param contact
     *  @return
     */
    public static boolean isLinked(ContactModel contact) {
        return (ListenerUtil.mutListener.listen(50741) ? (contact != null || !TestUtil.empty(contact.getAndroidContactLookupKey())) : (contact != null && !TestUtil.empty(contact.getAndroidContactLookupKey())));
    }

    /**
     *  @param contact
     *  @return
     */
    public static boolean canChangeFirstName(ContactModel contact) {
        return (ListenerUtil.mutListener.listen(50742) ? (contact != null || !isLinked(contact)) : (contact != null && !isLinked(contact)));
    }

    /**
     *  @param contact
     *  @return
     */
    public static boolean canChangeLastName(ContactModel contact) {
        return (ListenerUtil.mutListener.listen(50744) ? ((ListenerUtil.mutListener.listen(50743) ? (contact != null || !isLinked(contact)) : (contact != null && !isLinked(contact))) || !isChannelContact(contact)) : ((ListenerUtil.mutListener.listen(50743) ? (contact != null || !isLinked(contact)) : (contact != null && !isLinked(contact))) && !isChannelContact(contact)));
    }

    public static boolean canChangeAvatar(ContactModel contactModel, PreferenceService preferenceService, FileService fileService) {
        return (ListenerUtil.mutListener.listen(50746) ? (canHaveCustomAvatar(contactModel) || !((ListenerUtil.mutListener.listen(50745) ? (preferenceService.getProfilePicReceive() || fileService.hasContactPhotoFile(contactModel)) : (preferenceService.getProfilePicReceive() && fileService.hasContactPhotoFile(contactModel))))) : (canHaveCustomAvatar(contactModel) && !((ListenerUtil.mutListener.listen(50745) ? (preferenceService.getProfilePicReceive() || fileService.hasContactPhotoFile(contactModel)) : (preferenceService.getProfilePicReceive() && fileService.hasContactPhotoFile(contactModel))))));
    }

    /**
     *  check if this contact was added during a synchronization run.
     *  note that the contact may no longer be linked to a system contact
     *  @param contact
     *  @return
     */
    public static boolean isSynchronized(ContactModel contact) {
        return (ListenerUtil.mutListener.listen(50747) ? (contact != null || contact.isSynchronized()) : (contact != null && contact.isSynchronized()));
    }

    /**
     *  return true on channel-type contact (i.e. gateway, threema broadcast)
     *
     *  @param contactModel
     *  @return if channel contact
     */
    public static boolean isChannelContact(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(50748)) {
            if (contactModel != null) {
                return isChannelContact(contactModel.getIdentity());
            }
        }
        return false;
    }

    /**
     *  return true on channel-type contact (i.e. gateway, threema broadcast)
     *
     *  @param identity
     *  @return if channel contact
     */
    public static boolean isChannelContact(String identity) {
        return (ListenerUtil.mutListener.listen(50749) ? (identity != null || identity.startsWith("*")) : (identity != null && identity.startsWith("*")));
    }

    public static boolean canReceiveProfilePics(ContactModel contactModel) {
        return (ListenerUtil.mutListener.listen(50751) ? ((ListenerUtil.mutListener.listen(50750) ? (contactModel != null || !isChannelContact(contactModel)) : (contactModel != null && !isChannelContact(contactModel))) || !contactModel.getIdentity().equals(ThreemaApplication.ECHO_USER_IDENTITY)) : ((ListenerUtil.mutListener.listen(50750) ? (contactModel != null || !isChannelContact(contactModel)) : (contactModel != null && !isChannelContact(contactModel))) && !contactModel.getIdentity().equals(ThreemaApplication.ECHO_USER_IDENTITY)));
    }

    public static boolean canReceiveVoipMessages(ContactModel contactModel, IdListService blackListIdentityService) {
        return (ListenerUtil.mutListener.listen(50755) ? ((ListenerUtil.mutListener.listen(50754) ? ((ListenerUtil.mutListener.listen(50753) ? ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) || !blackListIdentityService.has(contactModel.getIdentity())) : ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) && !blackListIdentityService.has(contactModel.getIdentity()))) || !isChannelContact(contactModel)) : ((ListenerUtil.mutListener.listen(50753) ? ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) || !blackListIdentityService.has(contactModel.getIdentity())) : ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) && !blackListIdentityService.has(contactModel.getIdentity()))) && !isChannelContact(contactModel))) || !contactModel.getIdentity().equals(ThreemaApplication.ECHO_USER_IDENTITY)) : ((ListenerUtil.mutListener.listen(50754) ? ((ListenerUtil.mutListener.listen(50753) ? ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) || !blackListIdentityService.has(contactModel.getIdentity())) : ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) && !blackListIdentityService.has(contactModel.getIdentity()))) || !isChannelContact(contactModel)) : ((ListenerUtil.mutListener.listen(50753) ? ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) || !blackListIdentityService.has(contactModel.getIdentity())) : ((ListenerUtil.mutListener.listen(50752) ? (contactModel != null || blackListIdentityService != null) : (contactModel != null && blackListIdentityService != null)) && !blackListIdentityService.has(contactModel.getIdentity()))) && !isChannelContact(contactModel))) && !contactModel.getIdentity().equals(ThreemaApplication.ECHO_USER_IDENTITY)));
    }

    public static boolean allowedChangeToState(ContactModel contactModel, ContactModel.State newState) {
        if (!ListenerUtil.mutListener.listen(50759)) {
            if ((ListenerUtil.mutListener.listen(50756) ? (contactModel != null || contactModel.getState() != newState) : (contactModel != null && contactModel.getState() != newState))) {
                ContactModel.State oldState = contactModel.getState();
                if (!ListenerUtil.mutListener.listen(50758)) {
                    switch(newState) {
                        // never change to temporary
                        case TEMPORARY:
                            return false;
                        // change to active is always allowed
                        case ACTIVE:
                            return true;
                        case INACTIVE:
                            return (ListenerUtil.mutListener.listen(50757) ? (oldState == ContactModel.State.TEMPORARY && oldState == ContactModel.State.ACTIVE) : (oldState == ContactModel.State.TEMPORARY || oldState == ContactModel.State.ACTIVE));
                        case INVALID:
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  @param contact
     *  @return
     */
    public static boolean canHaveCustomAvatar(ContactModel contact) {
        return (ListenerUtil.mutListener.listen(50761) ? ((ListenerUtil.mutListener.listen(50760) ? (contact != null || !isLinked(contact)) : (contact != null && !isLinked(contact))) || !isChannelContact(contact)) : ((ListenerUtil.mutListener.listen(50760) ? (contact != null || !isLinked(contact)) : (contact != null && !isLinked(contact))) && !isChannelContact(contact)));
    }

    /**
     *  check if the avatar is expired (or no date set)
     *
     *  @param contactModel
     *  @return
     */
    public static boolean isAvatarExpired(ContactModel contactModel) {
        return (ListenerUtil.mutListener.listen(50763) ? (contactModel != null || ((ListenerUtil.mutListener.listen(50762) ? (contactModel.getAvatarExpires() == null && contactModel.getAvatarExpires().before(new Date())) : (contactModel.getAvatarExpires() == null || contactModel.getAvatarExpires().before(new Date()))))) : (contactModel != null && ((ListenerUtil.mutListener.listen(50762) ? (contactModel.getAvatarExpires() == null && contactModel.getAvatarExpires().before(new Date())) : (contactModel.getAvatarExpires() == null || contactModel.getAvatarExpires().before(new Date()))))));
    }

    /**
     *  returns a representation of the contact's name according to sort settings,
     *  suitable for comparing
     */
    public static String getSafeNameString(ContactModel c, PreferenceService preferenceService) {
        if (preferenceService.isContactListSortingFirstName()) {
            return (c.getFirstName() != null ? c.getFirstName() : "") + (c.getLastName() != null ? c.getLastName() : "") + (c.getPublicNickName() != null ? c.getPublicNickName() : "") + c.getIdentity();
        } else {
            return (c.getLastName() != null ? c.getLastName() : "") + (c.getFirstName() != null ? c.getFirstName() : "") + (c.getPublicNickName() != null ? c.getPublicNickName() : "") + c.getIdentity();
        }
    }

    public static String getSafeNameStringNoNickname(ContactModel c, PreferenceService preferenceService) {
        if (preferenceService.isContactListSortingFirstName()) {
            return (c.getFirstName() != null ? c.getFirstName() : "") + (c.getLastName() != null ? c.getLastName() : "") + c.getIdentity();
        } else {
            return (c.getLastName() != null ? c.getLastName() : "") + (c.getFirstName() != null ? c.getFirstName() : "") + c.getIdentity();
        }
    }

    @DrawableRes
    public static int getVerificationResource(ContactModel contactModel) {
        int iconResource = R.drawable.ic_verification_none;
        if (!ListenerUtil.mutListener.listen(50773)) {
            if (contactModel != null) {
                if (!ListenerUtil.mutListener.listen(50772)) {
                    switch(contactModel.getVerificationLevel()) {
                        case SERVER_VERIFIED:
                            if (!ListenerUtil.mutListener.listen(50767)) {
                                if ((ListenerUtil.mutListener.listen(50764) ? (ConfigUtils.isWorkBuild() || contactModel.isWork()) : (ConfigUtils.isWorkBuild() && contactModel.isWork()))) {
                                    if (!ListenerUtil.mutListener.listen(50766)) {
                                        iconResource = R.drawable.ic_verification_server_work;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(50765)) {
                                        iconResource = R.drawable.ic_verification_server;
                                    }
                                }
                            }
                            break;
                        case FULLY_VERIFIED:
                            if (!ListenerUtil.mutListener.listen(50771)) {
                                if ((ListenerUtil.mutListener.listen(50768) ? (ConfigUtils.isWorkBuild() || contactModel.isWork()) : (ConfigUtils.isWorkBuild() && contactModel.isWork()))) {
                                    if (!ListenerUtil.mutListener.listen(50770)) {
                                        iconResource = R.drawable.ic_verification_full_work;
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(50769)) {
                                        iconResource = R.drawable.ic_verification_full;
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }
        return iconResource;
    }

    public static Drawable getVerificationDrawable(Context context, ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(50774)) {
            if (context != null) {
                return AppCompatResources.getDrawable(context, getVerificationResource(contactModel));
            }
        }
        return null;
    }

    public static String getIdentityFromViewIntent(Context context, Intent intent) {
        if (!ListenerUtil.mutListener.listen(50782)) {
            if ((ListenerUtil.mutListener.listen(50775) ? (Intent.ACTION_VIEW.equals(intent.getAction()) || context.getString(R.string.contacts_mime_type).equals(intent.getType())) : (Intent.ACTION_VIEW.equals(intent.getAction()) && context.getString(R.string.contacts_mime_type).equals(intent.getType())))) {
                Cursor cursor = null;
                try {
                    if (!ListenerUtil.mutListener.listen(50779)) {
                        cursor = context.getContentResolver().query(intent.getData(), null, null, null, null);
                    }
                    if (!ListenerUtil.mutListener.listen(50781)) {
                        if (cursor != null) {
                            if (!ListenerUtil.mutListener.listen(50780)) {
                                if (cursor.moveToNext()) {
                                    return cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA1));
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(50776)) {
                        logger.error("Exception", e);
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(50778)) {
                        if (cursor != null) {
                            if (!ListenerUtil.mutListener.listen(50777)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
