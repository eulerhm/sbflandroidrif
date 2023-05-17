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

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import ch.threema.app.services.ContactService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.NameUtil;
import ch.threema.app.webclient.exceptions.ConversionException;
import ch.threema.client.IdentityType;
import ch.threema.client.ThreemaFeature;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@AnyThread
public class Contact extends Converter {

    private static final String PUBLIC_NICKNAME = "publicNickname";

    private static final String VERIFICATION_LEVEL = "verificationLevel";

    private static final String STATE = "state";

    private static final String HIDDEN = "hidden";

    private static final String FEATURE_MASK = "featureMask";

    private static final String FEATURE_LEVEL = "featureLevel";

    private static final String PUBLIC_KEY = "publicKey";

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    private static final String SYSTEM_CONTACT = "systemContact";

    private static final String SYSTEM_CONTACT_EMAILS = "emails";

    private static final String SYSTEM_CONTACT_EMAIL = "address";

    private static final String SYSTEM_CONTACT_LABEL = "label";

    private static final String SYSTEM_CONTACT_PHONE_NUMBERS = "phoneNumbers";

    private static final String SYSTEM_CONTACT_PHONE_NUMBER = "number";

    private static final String IS_WORK = "isWork";

    private static final String IDENTITY_TYPE = "identityType";

    private static final String IS_BLOCKED = "isBlocked";

    private static final String CAN_CHANGE_AVATAR = "canChangeAvatar";

    private static final String CAN_CHANGE_FIRST_NAME = "canChangeFirstName";

    private static final String CAN_CHANGE_LAST_NAME = "canChangeLastName";

    /**
     *  Converts multiple contact models to MsgpackBuilder instances.
     */
    public static List<MsgpackBuilder> convert(List<ContactModel> contacts) throws ConversionException {
        List<MsgpackBuilder> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(62655)) {
            {
                long _loopCounter754 = 0;
                for (ContactModel contact : contacts) {
                    ListenerUtil.loopListener.listen("_loopCounter754", ++_loopCounter754);
                    if (!ListenerUtil.mutListener.listen(62654)) {
                        list.add(convert(contact));
                    }
                }
            }
        }
        return list;
    }

    /**
     *  Converts a contact model to a MsgpackObjectBuilder.
     */
    public static MsgpackObjectBuilder convert(ContactModel contact) throws ConversionException {
        MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        try {
            if (!ListenerUtil.mutListener.listen(62656)) {
                builder.put(Receiver.ID, getId(contact));
            }
            if (!ListenerUtil.mutListener.listen(62657)) {
                builder.put(Receiver.DISPLAY_NAME, getName(contact));
            }
            if (!ListenerUtil.mutListener.listen(62658)) {
                builder.put(Receiver.COLOR, getColor(contact));
            }
            if (!ListenerUtil.mutListener.listen(62659)) {
                builder.maybePut(FIRST_NAME, Utils.nullIfEmpty(contact.getFirstName()));
            }
            if (!ListenerUtil.mutListener.listen(62660)) {
                builder.maybePut(LAST_NAME, Utils.nullIfEmpty(contact.getLastName()));
            }
            if (!ListenerUtil.mutListener.listen(62661)) {
                builder.maybePut(PUBLIC_NICKNAME, Utils.nullIfEmpty(contact.getPublicNickName()));
            }
            if (!ListenerUtil.mutListener.listen(62662)) {
                builder.put(VERIFICATION_LEVEL, VerificationLevel.convert(contact.getVerificationLevel()));
            }
            if (!ListenerUtil.mutListener.listen(62663)) {
                builder.put(STATE, contact.getState().toString());
            }
            if (!ListenerUtil.mutListener.listen(62664)) {
                builder.put(HIDDEN, contact.isHidden());
            }
            if (!ListenerUtil.mutListener.listen(62666)) {
                builder.maybePut(IS_WORK, (ListenerUtil.mutListener.listen(62665) ? (ConfigUtils.isWorkBuild() || contact.isWork()) : (ConfigUtils.isWorkBuild() && contact.isWork())));
            }
            if (!ListenerUtil.mutListener.listen(62667)) {
                builder.put(PUBLIC_KEY, contact.getPublicKey());
            }
            if (!ListenerUtil.mutListener.listen(62668)) {
                builder.put(IDENTITY_TYPE, contact.getType() == IdentityType.WORK ? 1 : 0);
            }
            if (!ListenerUtil.mutListener.listen(62669)) {
                builder.put(IS_BLOCKED, getBlackListService().has(contact.getIdentity()));
            }
            final int featureMask = contact.getFeatureMask();
            if (!ListenerUtil.mutListener.listen(62670)) {
                builder.put(FEATURE_MASK, featureMask);
            }
            if (!ListenerUtil.mutListener.listen(62671)) {
                builder.put(FEATURE_LEVEL, ThreemaFeature.featureMaskToLevel(featureMask));
            }
            boolean isSecretChat = getHiddenChatListService().has(getContactService().getUniqueIdString(contact));
            if (!ListenerUtil.mutListener.listen(62672)) {
                builder.put(Receiver.LOCKED, isSecretChat);
            }
            if (!ListenerUtil.mutListener.listen(62674)) {
                builder.put(Receiver.VISIBLE, (ListenerUtil.mutListener.listen(62673) ? (!isSecretChat && !getPreferenceService().isPrivateChatsHidden()) : (!isSecretChat || !getPreferenceService().isPrivateChatsHidden())));
            }
            if (!ListenerUtil.mutListener.listen(62675)) {
                // define access
                builder.put(Receiver.ACCESS, (new MsgpackObjectBuilder()).put(Receiver.CAN_DELETE, getContactService().getAccess(contact).canDelete()).put(CAN_CHANGE_AVATAR, ContactUtil.canChangeAvatar(contact, getPreferenceService(), getFileService())).put(CAN_CHANGE_FIRST_NAME, ContactUtil.canChangeFirstName(contact)).put(CAN_CHANGE_LAST_NAME, ContactUtil.canChangeLastName(contact)));
            }
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
        return builder;
    }

    public static MsgpackObjectBuilder convertDetails(ContactModel contact) throws ConversionException {
        final MsgpackObjectBuilder builder = new MsgpackObjectBuilder();
        final MsgpackArrayBuilder phoneNumberBuilder = new MsgpackArrayBuilder();
        final MsgpackArrayBuilder emailBuilder = new MsgpackArrayBuilder();
        if (!ListenerUtil.mutListener.listen(62691)) {
            if (ContactUtil.isLinked(contact)) {
                if (!ListenerUtil.mutListener.listen(62690)) {
                    // if android is older than version M or read contacts permission granted
                    if ((ListenerUtil.mutListener.listen(62681) ? ((ListenerUtil.mutListener.listen(62680) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62679) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62678) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62677) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62676) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(62680) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62679) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62678) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62677) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(62676) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)))))) || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED))) {
                        final String lookupKey = contact.getAndroidContactLookupKey();
                        // Get phone details
                        {
                            final String[] projection = { ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.LABEL };
                            final String selection = ContactsContract.Data.LOOKUP_KEY + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'";
                            final String[] selectionArgs = new String[] { String.valueOf(lookupKey) };
                            final Cursor cursor = getContext().getContentResolver().query(ContactsContract.Data.CONTENT_URI, projection, selection, selectionArgs, null);
                            if (!ListenerUtil.mutListener.listen(62685)) {
                                if (cursor != null) {
                                    if (!ListenerUtil.mutListener.listen(62683)) {
                                        {
                                            long _loopCounter755 = 0;
                                            while (cursor.moveToNext()) {
                                                ListenerUtil.loopListener.listen("_loopCounter755", ++_loopCounter755);
                                                // Determine phone number
                                                final String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
                                                // Determine label
                                                int type = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                                                String label;
                                                if (type == ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM) {
                                                    label = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
                                                } else {
                                                    label = (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(getContext().getResources(), type, "");
                                                }
                                                if (!ListenerUtil.mutListener.listen(62682)) {
                                                    phoneNumberBuilder.put((new MsgpackObjectBuilder()).put(SYSTEM_CONTACT_LABEL, label).put(SYSTEM_CONTACT_PHONE_NUMBER, phoneNumber));
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(62684)) {
                                        cursor.close();
                                    }
                                }
                            }
                        }
                        // Get e-mail details
                        {
                            final String[] projection = new String[] { ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.LABEL };
                            final String selection = ContactsContract.Data.LOOKUP_KEY + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE + "'";
                            final String[] selectionArgs = new String[] { String.valueOf(lookupKey) };
                            final Cursor cursor = getContext().getContentResolver().query(ContactsContract.Data.CONTENT_URI, projection, selection, selectionArgs, null);
                            if (!ListenerUtil.mutListener.listen(62689)) {
                                if (cursor != null) {
                                    if (!ListenerUtil.mutListener.listen(62687)) {
                                        {
                                            long _loopCounter756 = 0;
                                            while (cursor.moveToNext()) {
                                                ListenerUtil.loopListener.listen("_loopCounter756", ++_loopCounter756);
                                                String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                                                int type = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                                                String label;
                                                if (type == ContactsContract.CommonDataKinds.BaseTypes.TYPE_CUSTOM) {
                                                    label = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.LABEL));
                                                } else {
                                                    label = (String) ContactsContract.CommonDataKinds.Email.getTypeLabel(getContext().getResources(), type, "");
                                                }
                                                if (!ListenerUtil.mutListener.listen(62686)) {
                                                    emailBuilder.put((new MsgpackObjectBuilder()).put(SYSTEM_CONTACT_LABEL, label).put(SYSTEM_CONTACT_EMAIL, email));
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(62688)) {
                                        cursor.close();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(62692)) {
            // append system contact information
            builder.put(SYSTEM_CONTACT, (new MsgpackObjectBuilder()).put(SYSTEM_CONTACT_PHONE_NUMBERS, phoneNumberBuilder).put(SYSTEM_CONTACT_EMAILS, emailBuilder));
        }
        return builder;
    }

    public static MsgpackObjectBuilder getArguments(ContactModel contact) throws ConversionException {
        MsgpackObjectBuilder args = new MsgpackObjectBuilder();
        if (!ListenerUtil.mutListener.listen(62693)) {
            args.put(Receiver.ID, getId(contact));
        }
        return args;
    }

    public static String getId(ContactModel contact) throws ConversionException {
        try {
            return contact.getIdentity();
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
    }

    @NonNull
    public static String getName(ContactModel contact) throws ConversionException {
        try {
            return NameUtil.getDisplayNameOrNickname(contact, true);
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
    }

    @NonNull
    public static String getColor(ContactModel contact) throws ConversionException {
        try {
            return String.format("#%06X", (0xFFFFFF & contact.getColor()));
        } catch (NullPointerException e) {
            throw new ConversionException(e.toString());
        }
    }

    /**
     *  Return the filter used to query contacts from the contact service.
     */
    @NonNull
    public static ContactService.Filter getContactFilter() {
        return new ContactService.Filter() {

            @Override
            public ContactModel.State[] states() {
                return new ContactModel.State[] { ContactModel.State.ACTIVE, ContactModel.State.INACTIVE, ContactModel.State.INVALID };
            }

            @Override
            public Integer requiredFeature() {
                return null;
            }

            @Override
            public Boolean fetchMissingFeatureLevel() {
                return null;
            }

            @Override
            public Boolean includeMyself() {
                return false;
            }

            @Override
            public Boolean includeHidden() {
                return true;
            }
        };
    }
}
