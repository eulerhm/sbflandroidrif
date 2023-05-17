/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
package ch.threema.app.voip.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import androidx.annotation.Nullable;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactLookupUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.app.voip.util.VoipUtil;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handle call action intents (sent by calling someone through the Android phonebook),
 * start the call activity.
 */
public class CallActionIntentActivity extends ThreemaActivity {

    private static final Logger logger = LoggerFactory.getLogger(CallActionIntentActivity.class);

    private ServiceManager serviceManager;

    private ContactService contactService;

    private PreferenceService preferenceService;

    private LicenseService licenseService;

    @Override
    protected boolean checkInstances() {
        return TestUtil.required(this.serviceManager, this.contactService, this.preferenceService, this.licenseService);
    }

    @Override
    protected void instantiate() {
        if (!ListenerUtil.mutListener.listen(57705)) {
            this.serviceManager = ThreemaApplication.getServiceManager();
        }
        if (!ListenerUtil.mutListener.listen(57710)) {
            if (this.serviceManager != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(57707)) {
                        this.contactService = this.serviceManager.getContactService();
                    }
                    if (!ListenerUtil.mutListener.listen(57708)) {
                        this.preferenceService = this.serviceManager.getPreferenceService();
                    }
                    if (!ListenerUtil.mutListener.listen(57709)) {
                        this.licenseService = this.serviceManager.getLicenseService();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(57706)) {
                        logger.error("Could not instantiate services", e);
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(57711)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(57713)) {
            if (!this.requiredInstances()) {
                if (!ListenerUtil.mutListener.listen(57712)) {
                    this.finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(57716)) {
            if (!ConfigUtils.isCallsEnabled(this, preferenceService, licenseService)) {
                if (!ListenerUtil.mutListener.listen(57714)) {
                    Toast.makeText(getApplicationContext(), R.string.voip_disabled, Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(57715)) {
                    this.finish();
                }
                return;
            }
        }
        // String contactIdentity = null;
        ContactModel contact = null;
        // Validate intent
        final Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(57727)) {
            if (Intent.ACTION_VIEW.equals(intent.getAction())) {
                if (!ListenerUtil.mutListener.listen(57726)) {
                    if (getString(R.string.call_mime_type).equals(intent.getType())) {
                        Uri uri = intent.getData();
                        if (!ListenerUtil.mutListener.listen(57725)) {
                            if ((ListenerUtil.mutListener.listen(57720) ? (uri != null || ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())) : (uri != null && ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(uri.getScheme())))) {
                                try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                                    if (!ListenerUtil.mutListener.listen(57724)) {
                                        if ((ListenerUtil.mutListener.listen(57722) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext()))) {
                                            String contactIdentity = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.RawContacts.Data.DATA1));
                                            if (!ListenerUtil.mutListener.listen(57723)) {
                                                contact = contactService.getByIdentity(contactIdentity);
                                            }
                                        }
                                    }
                                } catch (SecurityException e) {
                                    if (!ListenerUtil.mutListener.listen(57721)) {
                                        logger.error("SecurityException", e);
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (Intent.ACTION_CALL.equals(intent.getAction())) {
                final Uri uri = intent.getData();
                if (!ListenerUtil.mutListener.listen(57719)) {
                    if ((ListenerUtil.mutListener.listen(57717) ? (uri != null || "tel".equals(uri.getScheme())) : (uri != null && "tel".equals(uri.getScheme())))) {
                        if (!ListenerUtil.mutListener.listen(57718)) {
                            // Look up contact identity
                            contact = ContactLookupUtil.phoneNumberToContact(this, contactService, uri.getSchemeSpecificPart());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(57731)) {
            if (contact == null) {
                if (!ListenerUtil.mutListener.listen(57728)) {
                    Toast.makeText(this, R.string.voip_contact_not_found, Toast.LENGTH_LONG).show();
                }
                if (!ListenerUtil.mutListener.listen(57729)) {
                    logger.warn("Invalid call intent: Contact not found");
                }
                if (!ListenerUtil.mutListener.listen(57730)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(57732)) {
            logger.info("Calling {} via call intent action", contact.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(57735)) {
            if (!VoipUtil.initiateCall(this, contact, false, new Runnable() {

                @Override
                public void run() {
                    if (!ListenerUtil.mutListener.listen(57733)) {
                        finish();
                    }
                }
            })) {
                if (!ListenerUtil.mutListener.listen(57734)) {
                    finish();
                }
            }
        }
    }
}
