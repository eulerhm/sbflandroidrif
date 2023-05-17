/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
package ch.threema.app.asynctasks;

import android.os.AsyncTask;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.ContactService;
import ch.threema.base.VerificationLevel;
import ch.threema.client.IdentityType;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class AddContactAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(AddContactAsyncTask.class);

    private ContactService contactService;

    private final Runnable runOnCompletion;

    private final String firstName, lastName, threemaId;

    private final boolean markAsWorkVerified;

    public AddContactAsyncTask(String firstname, String lastname, String identity, boolean markAsWorkVerified, Runnable runOnCompletion) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.threemaId = identity.toUpperCase();
        this.runOnCompletion = runOnCompletion;
        this.markAsWorkVerified = markAsWorkVerified;
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(10023)) {
                this.contactService = serviceManager.getContactService();
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if (!ListenerUtil.mutListener.listen(10025)) {
            if (this.contactService == null) {
                if (!ListenerUtil.mutListener.listen(10024)) {
                    logger.error("ContactService not available");
                }
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(10037)) {
            if (this.contactService.getByIdentity(this.threemaId) == null) {
                ContactModel contactModel;
                try {
                    contactModel = contactService.createContactByIdentity(this.threemaId, false);
                    if (!ListenerUtil.mutListener.listen(10030)) {
                        if ((ListenerUtil.mutListener.listen(10026) ? (this.firstName != null || this.lastName != null) : (this.firstName != null && this.lastName != null))) {
                            if (!ListenerUtil.mutListener.listen(10027)) {
                                contactModel.setFirstName(this.firstName);
                            }
                            if (!ListenerUtil.mutListener.listen(10028)) {
                                contactModel.setLastName(this.lastName);
                            }
                            if (!ListenerUtil.mutListener.listen(10029)) {
                                contactService.save(contactModel);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(10036)) {
                        if ((ListenerUtil.mutListener.listen(10031) ? (contactModel.getType() == IdentityType.WORK && markAsWorkVerified) : (contactModel.getType() == IdentityType.WORK || markAsWorkVerified))) {
                            if (!ListenerUtil.mutListener.listen(10032)) {
                                contactModel.setIsWork(true);
                            }
                            if (!ListenerUtil.mutListener.listen(10034)) {
                                if (contactModel.getVerificationLevel() != VerificationLevel.FULLY_VERIFIED) {
                                    if (!ListenerUtil.mutListener.listen(10033)) {
                                        contactModel.setVerificationLevel(VerificationLevel.SERVER_VERIFIED);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10035)) {
                                contactService.save(contactModel);
                            }
                        }
                    }
                    return true;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        // contact already exists
        return false;
    }

    @Override
    protected void onPostExecute(Boolean added) {
        if (!ListenerUtil.mutListener.listen(10043)) {
            if (added != null) {
                if (!ListenerUtil.mutListener.listen(10040)) {
                    if (added) {
                        if (!ListenerUtil.mutListener.listen(10039)) {
                            Toast.makeText(ThreemaApplication.getAppContext(), R.string.creating_contact_successful, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10042)) {
                    if (runOnCompletion != null) {
                        if (!ListenerUtil.mutListener.listen(10041)) {
                            runOnCompletion.run();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10038)) {
                    Toast.makeText(ThreemaApplication.getAppContext(), R.string.contact_not_found, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
