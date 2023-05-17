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

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import ch.threema.app.services.ContactService;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactLookupUtil {

    public static ContactModel phoneNumberToContact(final Context context, final ContactService contactService, final String phoneNumber) {
        Cursor phonesCursor = null;
        String lookupKey = null;
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
            if (!ListenerUtil.mutListener.listen(50736)) {
                phonesCursor = context.getContentResolver().query(uri, new String[] { ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.LOOKUP_KEY }, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(50739)) {
                if (phonesCursor != null) {
                    if (!ListenerUtil.mutListener.listen(50738)) {
                        if (phonesCursor.moveToFirst()) {
                            if (!ListenerUtil.mutListener.listen(50737)) {
                                lookupKey = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.PhoneLookup.LOOKUP_KEY));
                            }
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(50735)) {
                if (phonesCursor != null) {
                    if (!ListenerUtil.mutListener.listen(50734)) {
                        phonesCursor.close();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(50740)) {
            if (!TestUtil.empty(lookupKey)) {
                return contactService.getByLookupKey(lookupKey);
            }
        }
        return null;
    }
}
