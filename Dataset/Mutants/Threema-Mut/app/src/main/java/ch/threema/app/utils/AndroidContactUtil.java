/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.PatternSyntaxException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.FileService;
import ch.threema.app.services.UserService;
import ch.threema.base.ThreemaException;
import ch.threema.storage.models.ContactModel;
import static ch.threema.storage.models.ContactModel.DEFAULT_ANDROID_CONTACT_AVATAR_EXPIRY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AndroidContactUtil {

    private static final Logger logger = LoggerFactory.getLogger(AndroidContactUtil.class);

    private UserService userService;

    private FileService fileService;

    private static AndroidContactUtil sInstance = null;

    public static synchronized AndroidContactUtil getInstance() {
        if (!ListenerUtil.mutListener.listen(48712)) {
            if (sInstance == null) {
                if (!ListenerUtil.mutListener.listen(48711)) {
                    sInstance = new AndroidContactUtil();
                }
            }
        }
        return sInstance;
    }

    private AndroidContactUtil() {
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(48715)) {
            if (serviceManager != null) {
                if (!ListenerUtil.mutListener.listen(48713)) {
                    this.userService = serviceManager.getUserService();
                }
                try {
                    if (!ListenerUtil.mutListener.listen(48714)) {
                        this.fileService = serviceManager.getFileService();
                    }
                } catch (FileSystemNotPresentException ignored) {
                }
            }
        }
    }

    private static final String[] NAME_PROJECTION = new String[] { ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.SORT_KEY_ALTERNATIVE, ContactsContract.Contacts._ID };

    private static final String[] RAW_CONTACT_PROJECTION = new String[] { ContactsContract.RawContacts.CONTACT_ID, ContactsContract.RawContacts.SYNC1 };

    private static final String[] STRUCTURED_NAME_FIELDS = new String[] { ContactsContract.CommonDataKinds.StructuredName.PREFIX, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME, ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, ContactsContract.CommonDataKinds.StructuredName.SUFFIX, ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME };

    private final ContentResolver contentResolver = ThreemaApplication.getAppContext().getContentResolver();

    @Nullable
    private Account getAccount() {
        if (!ListenerUtil.mutListener.listen(48717)) {
            if (userService == null) {
                if (!ListenerUtil.mutListener.listen(48716)) {
                    logger.info("UserService not available");
                }
                return null;
            }
        }
        return userService.getAccount();
    }

    private static class ContactName {

        final String firstName;

        final String lastName;

        public ContactName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    /**
     *  Return a valid uri to the given contact that can be used to build an intent for the contact app
     *  It is safe to call this method if permission to access contacts is not granted
     *
     *  @param contactModel ContactModel for which to get the Android contact URI
     *  @return a valid uri pointing to the android contact or null if permission was not granted, no android contact is linked or android contact could not be looked up
     */
    @Nullable
    public Uri getAndroidContactUri(@Nullable ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(48724)) {
            if ((ListenerUtil.mutListener.listen(48723) ? ((ListenerUtil.mutListener.listen(48722) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48721) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48720) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48719) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48718) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) || ContextCompat.checkSelfPermission(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(48722) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48721) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48720) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48719) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(48718) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)))))) && ContextCompat.checkSelfPermission(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(48734)) {
            if (contactModel != null) {
                String contactLookupKey = contactModel.getAndroidContactLookupKey();
                if (!ListenerUtil.mutListener.listen(48733)) {
                    if (!TestUtil.empty(contactLookupKey)) {
                        Uri contactLookupUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, contactLookupKey);
                        if (!ListenerUtil.mutListener.listen(48732)) {
                            if ((ListenerUtil.mutListener.listen(48729) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(48728) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(48727) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(48726) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP_MR1) : (ListenerUtil.mutListener.listen(48725) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) : (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1))))))) {
                                try {
                                    if (!ListenerUtil.mutListener.listen(48731)) {
                                        contactLookupUri = ContactsContract.Contacts.lookupContact(contentResolver, contactLookupUri);
                                    }
                                } catch (Exception e) {
                                    if (!ListenerUtil.mutListener.listen(48730)) {
                                        logger.error("Exception", e);
                                    }
                                    return null;
                                }
                            }
                        }
                        return contactLookupUri;
                    }
                }
            }
        }
        return null;
    }

    /**
     *  Update the avatar for the specified contact from Android's contact database, if any
     *  If there's no avatar for this Android contact, any current avatar on file will be deleted
     *
     *  It is safe to call this method even if permission to read contacts is not given
     *
     *  @param contactModel ContactModel
     *  @return true if setting or deleting the avatar was successful, false otherwise
     */
    public boolean updateAvatarByAndroidContact(ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(48736)) {
            if (fileService == null) {
                if (!ListenerUtil.mutListener.listen(48735)) {
                    logger.info("FileService not available");
                }
                return false;
            }
        }
        String androidContactId = contactModel.getAndroidContactLookupKey();
        if (!ListenerUtil.mutListener.listen(48737)) {
            if (TestUtil.empty(androidContactId)) {
                return false;
            }
        }
        Uri contactUri = getAndroidContactUri(contactModel);
        if (!ListenerUtil.mutListener.listen(48738)) {
            if (contactUri == null) {
                return false;
            }
        }
        Bitmap bitmap = null;
        if (!ListenerUtil.mutListener.listen(48740)) {
            if (ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.READ_CONTACTS)) {
                if (!ListenerUtil.mutListener.listen(48739)) {
                    bitmap = AvatarConverterUtil.convert(ThreemaApplication.getAppContext(), contactUri);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48746)) {
            if (bitmap != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(48744)) {
                        fileService.writeAndroidContactAvatar(contactModel, BitmapUtil.bitmapToByteArray(bitmap, Bitmap.CompressFormat.PNG, 100));
                    }
                    if (!ListenerUtil.mutListener.listen(48745)) {
                        contactModel.setAvatarExpires(new Date(System.currentTimeMillis() + DEFAULT_ANDROID_CONTACT_AVATAR_EXPIRY));
                    }
                    return true;
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(48743)) {
                        logger.error("Exception", e);
                    }
                }
            } else {
                // delete old avatar
                boolean success = fileService.removeAndroidContactAvatar(contactModel);
                if (!ListenerUtil.mutListener.listen(48742)) {
                    if (success) {
                        if (!ListenerUtil.mutListener.listen(48741)) {
                            contactModel.setAvatarExpires(new Date(System.currentTimeMillis() + DEFAULT_ANDROID_CONTACT_AVATAR_EXPIRY));
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Update the name of this contact according to the name of the Android contact
     *  Note that the ContactModel needs to be saved to the ContactStore to apply the changes
     *
     *  @param contactModel ContactModel
     *  @return true if setting the name was successful, false otherwise
     */
    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    public boolean updateNameByAndroidContact(@NonNull ContactModel contactModel) throws ThreemaException {
        Uri namedContactUri = getAndroidContactUri(contactModel);
        if (!ListenerUtil.mutListener.listen(48752)) {
            if (TestUtil.required(contactModel, namedContactUri)) {
                ContactName contactName = this.getContactName(namedContactUri);
                if (!ListenerUtil.mutListener.listen(48747)) {
                    if (contactName == null) {
                        throw new ThreemaException("Unable to get contact name");
                    }
                }
                if (!ListenerUtil.mutListener.listen(48751)) {
                    if ((ListenerUtil.mutListener.listen(48748) ? (!TestUtil.compare(contactModel.getFirstName(), contactName.firstName) && !TestUtil.compare(contactModel.getLastName(), contactName.lastName)) : (!TestUtil.compare(contactModel.getFirstName(), contactName.firstName) || !TestUtil.compare(contactModel.getLastName(), contactName.lastName)))) {
                        if (!ListenerUtil.mutListener.listen(48749)) {
                            contactModel.setFirstName(contactName.firstName);
                        }
                        if (!ListenerUtil.mutListener.listen(48750)) {
                            contactModel.setLastName(contactName.lastName);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *  Get the contact name for a system contact specified by the specified Uri
     *  	 First we will consider the Structured Name of the contact
     *  	 If the Structured Name is lacking either a first name, a last name, or both, we will fall back to the Display Name
     *  	 If there's still neither first nor last name available, we will resort to the alternative representation of the full name (for Western names, it is the one using the "last, first" format)
     *
     *  @param contactUri Uri pointing to the contact
     *  @return ContactName object containing first and last name or null if lookup failed
     */
    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    @Nullable
    private ContactName getContactName(Uri contactUri) {
        if (!ListenerUtil.mutListener.listen(48753)) {
            if (!TestUtil.required(this.contentResolver)) {
                return null;
            }
        }
        ContactName contactName = null;
        Cursor nameCursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(48757)) {
                nameCursor = this.contentResolver.query(contactUri, NAME_PROJECTION, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(48773)) {
                if ((ListenerUtil.mutListener.listen(48758) ? (nameCursor != null || nameCursor.moveToFirst()) : (nameCursor != null && nameCursor.moveToFirst()))) {
                    long contactId = nameCursor.getLong(nameCursor.getColumnIndex(ContactsContract.Contacts._ID));
                    if (!ListenerUtil.mutListener.listen(48760)) {
                        contactName = this.getContactNameFromContactId(contactId);
                    }
                    if (!ListenerUtil.mutListener.listen(48772)) {
                        // fallback
                        if ((ListenerUtil.mutListener.listen(48761) ? (contactName.firstName == null || contactName.lastName == null) : (contactName.firstName == null && contactName.lastName == null))) {
                            // lastname, firstname
                            String alternativeSortKey = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.Contacts.SORT_KEY_ALTERNATIVE));
                            if (!ListenerUtil.mutListener.listen(48771)) {
                                if (!TestUtil.empty(alternativeSortKey)) {
                                    String[] lastNameFirstName = alternativeSortKey.split(",");
                                    if (!ListenerUtil.mutListener.listen(48770)) {
                                        if ((ListenerUtil.mutListener.listen(48766) ? (lastNameFirstName.length >= 2) : (ListenerUtil.mutListener.listen(48765) ? (lastNameFirstName.length <= 2) : (ListenerUtil.mutListener.listen(48764) ? (lastNameFirstName.length > 2) : (ListenerUtil.mutListener.listen(48763) ? (lastNameFirstName.length < 2) : (ListenerUtil.mutListener.listen(48762) ? (lastNameFirstName.length != 2) : (lastNameFirstName.length == 2))))))) {
                                            String lastName = lastNameFirstName[0].trim();
                                            String firstName = lastNameFirstName[1].trim();
                                            if (!ListenerUtil.mutListener.listen(48769)) {
                                                if ((ListenerUtil.mutListener.listen(48767) ? (!TestUtil.compare(lastName, "") || !TestUtil.compare(firstName, "")) : (!TestUtil.compare(lastName, "") && !TestUtil.compare(firstName, "")))) {
                                                    if (!ListenerUtil.mutListener.listen(48768)) {
                                                        contactName = new ContactName(firstName, lastName);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    // no contact name found
                                    return null;
                                }
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(48759)) {
                        logger.debug("Contact not found: {}", contactUri.toString());
                    }
                }
            }
        } catch (PatternSyntaxException e) {
            if (!ListenerUtil.mutListener.listen(48754)) {
                logger.error("Exception", e);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(48756)) {
                if (nameCursor != null) {
                    if (!ListenerUtil.mutListener.listen(48755)) {
                        nameCursor.close();
                    }
                }
            }
        }
        return contactName;
    }

    /**
     *  Get the contact name for a system contact specified by contactId
     *  - First we will consider the Structured Name of the contact
     *  - If the Structured Name is lacking either a first name, a last name, or both, we will fall back to the Display Name
     *
     *  @param contactId Id of the Android contact
     *  @return ContactName object containing first and last name
     */
    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    @NonNull
    private ContactName getContactNameFromContactId(long contactId) {
        Map<String, String> structure = this.getStructuredNameByContactId(contactId);
        String firstName = structure.get(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
        String lastName = structure.get(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
        String prefix = structure.get(ContactsContract.CommonDataKinds.StructuredName.PREFIX);
        String middleName = structure.get(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME);
        String suffix = structure.get(ContactsContract.CommonDataKinds.StructuredName.SUFFIX);
        String displayName = structure.get(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME);
        StringBuilder contactFirstName = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(48781)) {
            if ((ListenerUtil.mutListener.listen(48779) ? (prefix != null || (ListenerUtil.mutListener.listen(48778) ? (prefix.length() >= 0) : (ListenerUtil.mutListener.listen(48777) ? (prefix.length() <= 0) : (ListenerUtil.mutListener.listen(48776) ? (prefix.length() < 0) : (ListenerUtil.mutListener.listen(48775) ? (prefix.length() != 0) : (ListenerUtil.mutListener.listen(48774) ? (prefix.length() == 0) : (prefix.length() > 0))))))) : (prefix != null && (ListenerUtil.mutListener.listen(48778) ? (prefix.length() >= 0) : (ListenerUtil.mutListener.listen(48777) ? (prefix.length() <= 0) : (ListenerUtil.mutListener.listen(48776) ? (prefix.length() < 0) : (ListenerUtil.mutListener.listen(48775) ? (prefix.length() != 0) : (ListenerUtil.mutListener.listen(48774) ? (prefix.length() == 0) : (prefix.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(48780)) {
                    contactFirstName.append(prefix);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48790)) {
            if (firstName != null) {
                if (!ListenerUtil.mutListener.listen(48788)) {
                    if ((ListenerUtil.mutListener.listen(48786) ? (contactFirstName.length() >= 0) : (ListenerUtil.mutListener.listen(48785) ? (contactFirstName.length() <= 0) : (ListenerUtil.mutListener.listen(48784) ? (contactFirstName.length() < 0) : (ListenerUtil.mutListener.listen(48783) ? (contactFirstName.length() != 0) : (ListenerUtil.mutListener.listen(48782) ? (contactFirstName.length() == 0) : (contactFirstName.length() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(48787)) {
                            contactFirstName.append(" ");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48789)) {
                    contactFirstName.append(firstName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48805)) {
            if ((ListenerUtil.mutListener.listen(48796) ? (middleName != null || (ListenerUtil.mutListener.listen(48795) ? (middleName.length() >= 0) : (ListenerUtil.mutListener.listen(48794) ? (middleName.length() <= 0) : (ListenerUtil.mutListener.listen(48793) ? (middleName.length() < 0) : (ListenerUtil.mutListener.listen(48792) ? (middleName.length() != 0) : (ListenerUtil.mutListener.listen(48791) ? (middleName.length() == 0) : (middleName.length() > 0))))))) : (middleName != null && (ListenerUtil.mutListener.listen(48795) ? (middleName.length() >= 0) : (ListenerUtil.mutListener.listen(48794) ? (middleName.length() <= 0) : (ListenerUtil.mutListener.listen(48793) ? (middleName.length() < 0) : (ListenerUtil.mutListener.listen(48792) ? (middleName.length() != 0) : (ListenerUtil.mutListener.listen(48791) ? (middleName.length() == 0) : (middleName.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(48803)) {
                    if ((ListenerUtil.mutListener.listen(48801) ? (contactFirstName.length() >= 0) : (ListenerUtil.mutListener.listen(48800) ? (contactFirstName.length() <= 0) : (ListenerUtil.mutListener.listen(48799) ? (contactFirstName.length() < 0) : (ListenerUtil.mutListener.listen(48798) ? (contactFirstName.length() != 0) : (ListenerUtil.mutListener.listen(48797) ? (contactFirstName.length() == 0) : (contactFirstName.length() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(48802)) {
                            contactFirstName.append(' ');
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48804)) {
                    contactFirstName.append(middleName);
                }
            }
        }
        StringBuilder contactLastName = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(48807)) {
            if (lastName != null) {
                if (!ListenerUtil.mutListener.listen(48806)) {
                    contactLastName.append(lastName);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48822)) {
            if ((ListenerUtil.mutListener.listen(48813) ? (suffix != null || (ListenerUtil.mutListener.listen(48812) ? (suffix.length() >= 0) : (ListenerUtil.mutListener.listen(48811) ? (suffix.length() <= 0) : (ListenerUtil.mutListener.listen(48810) ? (suffix.length() < 0) : (ListenerUtil.mutListener.listen(48809) ? (suffix.length() != 0) : (ListenerUtil.mutListener.listen(48808) ? (suffix.length() == 0) : (suffix.length() > 0))))))) : (suffix != null && (ListenerUtil.mutListener.listen(48812) ? (suffix.length() >= 0) : (ListenerUtil.mutListener.listen(48811) ? (suffix.length() <= 0) : (ListenerUtil.mutListener.listen(48810) ? (suffix.length() < 0) : (ListenerUtil.mutListener.listen(48809) ? (suffix.length() != 0) : (ListenerUtil.mutListener.listen(48808) ? (suffix.length() == 0) : (suffix.length() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(48820)) {
                    if ((ListenerUtil.mutListener.listen(48818) ? (contactLastName.length() >= 0) : (ListenerUtil.mutListener.listen(48817) ? (contactLastName.length() <= 0) : (ListenerUtil.mutListener.listen(48816) ? (contactLastName.length() < 0) : (ListenerUtil.mutListener.listen(48815) ? (contactLastName.length() != 0) : (ListenerUtil.mutListener.listen(48814) ? (contactLastName.length() == 0) : (contactLastName.length() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(48819)) {
                            contactLastName.append(", ");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(48821)) {
                    contactLastName.append(suffix);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48834)) {
            /* Only use this structured name if we have a first or last name. Otherwise use display name (below) */
            if ((ListenerUtil.mutListener.listen(48833) ? ((ListenerUtil.mutListener.listen(48827) ? (contactFirstName.length() >= 0) : (ListenerUtil.mutListener.listen(48826) ? (contactFirstName.length() <= 0) : (ListenerUtil.mutListener.listen(48825) ? (contactFirstName.length() < 0) : (ListenerUtil.mutListener.listen(48824) ? (contactFirstName.length() != 0) : (ListenerUtil.mutListener.listen(48823) ? (contactFirstName.length() == 0) : (contactFirstName.length() > 0)))))) && (ListenerUtil.mutListener.listen(48832) ? (contactLastName.length() >= 0) : (ListenerUtil.mutListener.listen(48831) ? (contactLastName.length() <= 0) : (ListenerUtil.mutListener.listen(48830) ? (contactLastName.length() < 0) : (ListenerUtil.mutListener.listen(48829) ? (contactLastName.length() != 0) : (ListenerUtil.mutListener.listen(48828) ? (contactLastName.length() == 0) : (contactLastName.length() > 0))))))) : ((ListenerUtil.mutListener.listen(48827) ? (contactFirstName.length() >= 0) : (ListenerUtil.mutListener.listen(48826) ? (contactFirstName.length() <= 0) : (ListenerUtil.mutListener.listen(48825) ? (contactFirstName.length() < 0) : (ListenerUtil.mutListener.listen(48824) ? (contactFirstName.length() != 0) : (ListenerUtil.mutListener.listen(48823) ? (contactFirstName.length() == 0) : (contactFirstName.length() > 0)))))) || (ListenerUtil.mutListener.listen(48832) ? (contactLastName.length() >= 0) : (ListenerUtil.mutListener.listen(48831) ? (contactLastName.length() <= 0) : (ListenerUtil.mutListener.listen(48830) ? (contactLastName.length() < 0) : (ListenerUtil.mutListener.listen(48829) ? (contactLastName.length() != 0) : (ListenerUtil.mutListener.listen(48828) ? (contactLastName.length() == 0) : (contactLastName.length() > 0))))))))) {
                return new ContactName(contactFirstName.toString(), contactLastName.toString());
            }
        }
        final Pair<String, String> firstLastName = NameUtil.getFirstLastNameFromDisplayName(displayName);
        return new ContactName(firstLastName.first, firstLastName.second);
    }

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    @NonNull
    private Map<String, String> getStructuredNameByContactId(long id) {
        Map<String, String> structuredName = new TreeMap<String, String>();
        Cursor cursor = this.contentResolver.query(ContactsContract.Data.CONTENT_URI, STRUCTURED_NAME_FIELDS, ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?", new String[] { String.valueOf(id), ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE }, null);
        if (!ListenerUtil.mutListener.listen(48844)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(48843)) {
                        if (cursor.moveToFirst()) {
                            if (!ListenerUtil.mutListener.listen(48842)) {
                                {
                                    long _loopCounter559 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(48841) ? (i >= STRUCTURED_NAME_FIELDS.length) : (ListenerUtil.mutListener.listen(48840) ? (i <= STRUCTURED_NAME_FIELDS.length) : (ListenerUtil.mutListener.listen(48839) ? (i > STRUCTURED_NAME_FIELDS.length) : (ListenerUtil.mutListener.listen(48838) ? (i != STRUCTURED_NAME_FIELDS.length) : (ListenerUtil.mutListener.listen(48837) ? (i == STRUCTURED_NAME_FIELDS.length) : (i < STRUCTURED_NAME_FIELDS.length)))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter559", ++_loopCounter559);
                                        if (!ListenerUtil.mutListener.listen(48836)) {
                                            structuredName.put(STRUCTURED_NAME_FIELDS[i], cursor.getString(i));
                                        }
                                    }
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(48835)) {
                        cursor.close();
                    }
                }
            }
        }
        return structuredName;
    }

    /**
     *  Add ContentProviderOperations to create a raw contact for the given identity to a provided List of ContentProviderOperations.
     *  Put the identity into the SYNC1 column and set data records for messaging and calling
     *
     *  @param contentProviderOperations List of ContentProviderOperations to add this operation to
     *  @param systemRawContactId The raw contact that matched the criteria for aggregation (i.e. email or phone number)
     *  @param contactModel ContactModel to create a raw contact for
     *  @param supportsVoiceCalls Whether the user has voice calls enabled
     */
    @RequiresPermission(allOf = { Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS })
    public void createThreemaRawContact(@NonNull List<ContentProviderOperation> contentProviderOperations, long systemRawContactId, @NonNull ContactModel contactModel, boolean supportsVoiceCalls) {
        String identity = contactModel.getIdentity();
        Context context = ThreemaApplication.getAppContext();
        Account account = this.getAccount();
        if (!ListenerUtil.mutListener.listen(48845)) {
            if (!TestUtil.required(account, identity)) {
                return;
            }
        }
        int backReference = contentProviderOperations.size();
        if (!ListenerUtil.mutListener.listen(48846)) {
            logger.debug("Adding contact: " + identity);
        }
        if (!ListenerUtil.mutListener.listen(48847)) {
            logger.debug("Create our RawContact");
        }
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
        if (!ListenerUtil.mutListener.listen(48848)) {
            builder.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, account.name);
        }
        if (!ListenerUtil.mutListener.listen(48849)) {
            builder.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, account.type);
        }
        if (!ListenerUtil.mutListener.listen(48850)) {
            builder.withValue(ContactsContract.RawContacts.SYNC1, identity);
        }
        if (!ListenerUtil.mutListener.listen(48851)) {
            contentProviderOperations.add(builder.build());
        }
        Uri insertUri = ContactsContract.Data.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true").build();
        if (!ListenerUtil.mutListener.listen(48852)) {
            logger.debug("Create a Data record of custom type");
        }
        if (!ListenerUtil.mutListener.listen(48853)) {
            builder = ContentProviderOperation.newInsert(insertUri);
        }
        if (!ListenerUtil.mutListener.listen(48854)) {
            builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, backReference);
        }
        if (!ListenerUtil.mutListener.listen(48855)) {
            builder.withValue(ContactsContract.Data.MIMETYPE, context.getString(R.string.contacts_mime_type));
        }
        if (!ListenerUtil.mutListener.listen(48856)) {
            builder.withValue(ContactsContract.Data.DATA1, identity);
        }
        if (!ListenerUtil.mutListener.listen(48857)) {
            builder.withValue(ContactsContract.Data.DATA2, context.getString(R.string.app_name));
        }
        if (!ListenerUtil.mutListener.listen(48858)) {
            builder.withValue(ContactsContract.Data.DATA3, context.getString(R.string.threema_message_to, identity));
        }
        if (!ListenerUtil.mutListener.listen(48859)) {
            builder.withYieldAllowed(true);
        }
        if (!ListenerUtil.mutListener.listen(48860)) {
            contentProviderOperations.add(builder.build());
        }
        if (!ListenerUtil.mutListener.listen(48870)) {
            if (supportsVoiceCalls) {
                if (!ListenerUtil.mutListener.listen(48861)) {
                    logger.debug("Create a Data record of custom type for call");
                }
                if (!ListenerUtil.mutListener.listen(48862)) {
                    builder = ContentProviderOperation.newInsert(insertUri);
                }
                if (!ListenerUtil.mutListener.listen(48863)) {
                    builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, backReference);
                }
                if (!ListenerUtil.mutListener.listen(48864)) {
                    builder.withValue(ContactsContract.Data.MIMETYPE, context.getString(R.string.call_mime_type));
                }
                if (!ListenerUtil.mutListener.listen(48865)) {
                    builder.withValue(ContactsContract.Data.DATA1, identity);
                }
                if (!ListenerUtil.mutListener.listen(48866)) {
                    builder.withValue(ContactsContract.Data.DATA2, context.getString(R.string.app_name));
                }
                if (!ListenerUtil.mutListener.listen(48867)) {
                    builder.withValue(ContactsContract.Data.DATA3, context.getString(R.string.threema_call_with, identity));
                }
                if (!ListenerUtil.mutListener.listen(48868)) {
                    builder.withYieldAllowed(true);
                }
                if (!ListenerUtil.mutListener.listen(48869)) {
                    contentProviderOperations.add(builder.build());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48871)) {
            builder = ContentProviderOperation.newUpdate(ContactsContract.AggregationExceptions.CONTENT_URI);
        }
        if (!ListenerUtil.mutListener.listen(48872)) {
            builder.withValue(ContactsContract.AggregationExceptions.RAW_CONTACT_ID1, systemRawContactId);
        }
        if (!ListenerUtil.mutListener.listen(48873)) {
            builder.withValueBackReference(ContactsContract.AggregationExceptions.RAW_CONTACT_ID2, backReference);
        }
        if (!ListenerUtil.mutListener.listen(48874)) {
            builder.withValue(ContactsContract.AggregationExceptions.TYPE, ContactsContract.AggregationExceptions.TYPE_KEEP_TOGETHER);
        }
        if (!ListenerUtil.mutListener.listen(48875)) {
            contentProviderOperations.add(builder.build());
        }
    }

    /**
     *  Delete the raw contact where the given identity matches the entry in the contact's SYNC1 column
     *  It's safe to call this method without contacts permission
     *
     *  @param contactModel ContactModel whose raw contact we want to be deleted
     *  @return number of raw contacts deleted
     */
    public int deleteThreemaRawContact(@NonNull ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(48876)) {
            if (!ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.WRITE_CONTACTS)) {
                return 0;
            }
        }
        Account account = this.getAccount();
        if (!ListenerUtil.mutListener.listen(48877)) {
            if (account == null) {
                return 0;
            }
        }
        Uri rawContactUri = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true").appendQueryParameter(ContactsContract.RawContacts.SYNC1, contactModel.getIdentity()).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_NAME, account.name).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_TYPE, account.type).build();
        try {
            return contentResolver.delete(rawContactUri, null, null);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(48878)) {
                logger.error("Exception", e);
            }
        }
        return 0;
    }

    /**
     *  Delete all raw contacts specified in rawContacts Map
     *
     *  @param rawContacts HashMap of the rawContacts to delete. The key of the map entry contains the identity
     *  @return Number of raw contacts that were supposed to be deleted. Does not necessarily represent the real number of deleted raw contacts.
     */
    public int deleteThreemaRawContacts(@NonNull HashMap<String, Long> rawContacts) {
        if (!ListenerUtil.mutListener.listen(48879)) {
            if (!ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.WRITE_CONTACTS)) {
                return 0;
            }
        }
        Account account = this.getAccount();
        if (!ListenerUtil.mutListener.listen(48880)) {
            if (account == null) {
                return 0;
            }
        }
        if (!ListenerUtil.mutListener.listen(48881)) {
            if (rawContacts.isEmpty()) {
                return 0;
            }
        }
        ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(48884)) {
            {
                long _loopCounter560 = 0;
                for (Map.Entry<String, Long> rawContact : rawContacts.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter560", ++_loopCounter560);
                    if (!ListenerUtil.mutListener.listen(48883)) {
                        if (!TestUtil.empty(rawContact.getKey())) {
                            ContentProviderOperation.Builder builder = ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true").appendQueryParameter(ContactsContract.RawContacts.SYNC1, rawContact.getKey()).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_NAME, account.name).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_TYPE, account.type).build());
                            if (!ListenerUtil.mutListener.listen(48882)) {
                                contentProviderOperations.add(builder.build());
                            }
                        }
                    }
                }
            }
        }
        int operationCount = contentProviderOperations.size();
        if (!ListenerUtil.mutListener.listen(48893)) {
            if ((ListenerUtil.mutListener.listen(48889) ? (operationCount >= 0) : (ListenerUtil.mutListener.listen(48888) ? (operationCount <= 0) : (ListenerUtil.mutListener.listen(48887) ? (operationCount < 0) : (ListenerUtil.mutListener.listen(48886) ? (operationCount != 0) : (ListenerUtil.mutListener.listen(48885) ? (operationCount == 0) : (operationCount > 0))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(48891)) {
                        ThreemaApplication.getAppContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(48890)) {
                        logger.error("Error during raw contact deletion! ", e);
                    }
                }
                if (!ListenerUtil.mutListener.listen(48892)) {
                    contentProviderOperations.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48894)) {
            logger.debug("Deleted {} raw contacts", operationCount);
        }
        return operationCount;
    }

    /**
     *  Delete all raw contacts associated with Threema (including stray ones)
     *  Safe to be called without permission
     *
     *  @return number of raw contacts deleted
     */
    public int deleteAllThreemaRawContacts() {
        if (!ListenerUtil.mutListener.listen(48895)) {
            if (!ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.WRITE_CONTACTS)) {
                return 0;
            }
        }
        Account account = this.getAccount();
        if (!ListenerUtil.mutListener.listen(48896)) {
            if (account == null) {
                return 0;
            }
        }
        Uri rawContactUri = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER, "true").appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_NAME, account.name).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_TYPE, account.type).build();
        try {
            return contentResolver.delete(rawContactUri, null, null);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(48897)) {
                logger.error("Exception", e);
            }
        }
        return 0;
    }

    /**
     *  Get a list of all Threema raw contacts from the contact database. This may include "stray" contacts.
     *
     *  @return HashMap containing identity as key and android contact id as value
     */
    @Nullable
    public HashMap<String, Long> getAllThreemaRawContacts() {
        if (!ListenerUtil.mutListener.listen(48898)) {
            if (!ConfigUtils.isPermissionGranted(ThreemaApplication.getAppContext(), Manifest.permission.WRITE_CONTACTS)) {
                return null;
            }
        }
        Account account = this.getAccount();
        if (!ListenerUtil.mutListener.listen(48899)) {
            if (account == null) {
                return null;
            }
        }
        Uri rawContactUri = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_NAME, account.name).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_TYPE, account.type).build();
        HashMap<String, Long> rawContacts = new HashMap<>();
        Cursor cursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(48903)) {
                cursor = contentResolver.query(rawContactUri, RAW_CONTACT_PROJECTION, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(48906)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(48905)) {
                        {
                            long _loopCounter561 = 0;
                            while (cursor.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter561", ++_loopCounter561);
                                Long contactId = cursor.getLong(0);
                                String identity = cursor.getString(1);
                                if (!ListenerUtil.mutListener.listen(48904)) {
                                    rawContacts.put(identity, contactId);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(48900)) {
                logger.error("Exception", e);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(48902)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(48901)) {
                        cursor.close();
                    }
                }
            }
        }
        return rawContacts;
    }

    /**
     *  Get the "main" raw contact representing the Android contact specified by the lookup key
     *  We consider the contact referenced as display name source for the Android contact as the "main" contact
     *
     *  @param lookupKey The lookup key of the contact
     *  @return ID of the raw contact or 0 if none is found
     */
    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    public long getMainRawContact(String lookupKey) {
        long rawContactId = 0;
        Uri lookupUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
        Cursor cursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(48914)) {
                cursor = ThreemaApplication.getAppContext().getContentResolver().query(lookupUri, new String[] { (ListenerUtil.mutListener.listen(48913) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(48912) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(48911) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(48910) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(48909) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)))))) ? ContactsContract.Contacts.NAME_RAW_CONTACT_ID : "name_raw_contact_id" }, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(48917)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(48916)) {
                        if (cursor.moveToFirst()) {
                            if (!ListenerUtil.mutListener.listen(48915)) {
                                rawContactId = cursor.getLong(0);
                            }
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(48908)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(48907)) {
                        cursor.close();
                    }
                }
            }
        }
        return rawContactId;
    }

    @RequiresPermission(allOf = { Manifest.permission.READ_CONTACTS, Manifest.permission.GET_ACCOUNTS })
    @Nullable
    @WorkerThread
    public Drawable getAccountIcon(@NonNull ContactModel contactModel) {
        final PackageManager pm = ThreemaApplication.getAppContext().getPackageManager();
        Account myAccount = this.getAccount();
        if (!ListenerUtil.mutListener.listen(48918)) {
            if (myAccount == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(48920)) {
            if ((ListenerUtil.mutListener.listen(48919) ? (!contactModel.isSynchronized() && contactModel.getAndroidContactLookupKey() == null) : (!contactModel.isSynchronized() || contactModel.getAndroidContactLookupKey() == null))) {
                return null;
            }
        }
        long nameSourceRawContactId = getMainRawContact(contactModel.getAndroidContactLookupKey());
        if (!ListenerUtil.mutListener.listen(48926)) {
            if ((ListenerUtil.mutListener.listen(48925) ? (nameSourceRawContactId >= 0) : (ListenerUtil.mutListener.listen(48924) ? (nameSourceRawContactId <= 0) : (ListenerUtil.mutListener.listen(48923) ? (nameSourceRawContactId > 0) : (ListenerUtil.mutListener.listen(48922) ? (nameSourceRawContactId < 0) : (ListenerUtil.mutListener.listen(48921) ? (nameSourceRawContactId != 0) : (nameSourceRawContactId == 0))))))) {
                return null;
            }
        }
        AccountManager accountManager = AccountManager.get(ThreemaApplication.getAppContext());
        AuthenticatorDescription[] descriptions = accountManager.getAuthenticatorTypes();
        Drawable drawable = null;
        Uri nameSourceRawContactUri = ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, nameSourceRawContactId);
        Cursor cursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(48929)) {
                cursor = this.contentResolver.query(nameSourceRawContactUri, new String[] { ContactsContract.RawContacts.ACCOUNT_TYPE }, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(48934)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(48933)) {
                        if (cursor.moveToNext()) {
                            String accountType = cursor.getString(0);
                            if (!ListenerUtil.mutListener.listen(48932)) {
                                {
                                    long _loopCounter562 = 0;
                                    for (AuthenticatorDescription description : descriptions) {
                                        ListenerUtil.loopListener.listen("_loopCounter562", ++_loopCounter562);
                                        if (!ListenerUtil.mutListener.listen(48931)) {
                                            if (description.type.equalsIgnoreCase(accountType)) {
                                                if (!ListenerUtil.mutListener.listen(48930)) {
                                                    drawable = pm.getDrawable(description.packageName, description.iconId, null);
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(48928)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(48927)) {
                        cursor.close();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(48937)) {
            // if no icon found, display the icon of the phone or contacts app
            if (drawable == null) {
                if (!ListenerUtil.mutListener.listen(48936)) {
                    {
                        long _loopCounter563 = 0;
                        for (String substitutePackageName : new String[] { "com.android.contacts", "com.android.providers.contacts", "com.android.phone" }) {
                            ListenerUtil.loopListener.listen("_loopCounter563", ++_loopCounter563);
                            try {
                                if (!ListenerUtil.mutListener.listen(48935)) {
                                    drawable = pm.getApplicationIcon(substitutePackageName);
                                }
                                break;
                            } catch (PackageManager.NameNotFoundException x) {
                            }
                        }
                    }
                }
            }
        }
        return drawable;
    }

    /**
     *  Open the system's contact editor for the provided Threema contact
     *  @param context Context
     *  @param contact Threema contact
     *  @return true if the contact is linked with a system contact (even if no app is available for an ACTION_EDIT intent in the system), false otherwise
     */
    public boolean openContactEditor(Context context, ContactModel contact) {
        Uri contactUri = AndroidContactUtil.getInstance().getAndroidContactUri(contact);
        if (!ListenerUtil.mutListener.listen(48944)) {
            if (contactUri != null) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                if (!ListenerUtil.mutListener.listen(48938)) {
                    intent.setDataAndType(contactUri, ContactsContract.Contacts.CONTENT_ITEM_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(48939)) {
                    intent.putExtra("finishActivityOnSaveCompleted", true);
                }
                if (!ListenerUtil.mutListener.listen(48940)) {
                    // make sure users are coming back to threema and not the external activity
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                }
                if (!ListenerUtil.mutListener.listen(48943)) {
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        if (!ListenerUtil.mutListener.listen(48942)) {
                            context.startActivity(intent);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(48941)) {
                            Toast.makeText(context, "No contact editor found on device.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
