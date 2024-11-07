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
package ch.threema.app.stores;

import android.os.NetworkOnMainThreadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.listeners.ContactListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.utils.ColorUtil;
import ch.threema.app.utils.SynchronizeContactsUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.Contact;
import ch.threema.base.ThreemaException;
import ch.threema.base.VerificationLevel;
import ch.threema.client.APIConnector;
import ch.threema.client.ContactStoreInterface;
import ch.threema.client.ContactStoreObserver;
import ch.threema.client.IdentityState;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.factories.ContactModelFactory;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactStore implements ContactStoreInterface {

    private static final Logger logger = LoggerFactory.getLogger(ContactStore.class);

    private APIConnector apiConnector;

    private final PreferenceService preferenceService;

    private DatabaseServiceNew databaseServiceNew;

    private final IdListService blackListService;

    private final IdListService excludeListService;

    private final HashMap<String, ContactModel> cache = new HashMap<>();

    public ContactStore(APIConnector apiConnector, PreferenceService preferenceService, DatabaseServiceNew databaseServiceNew, IdListService blackListService, IdListService excludeListService) {
        if (!ListenerUtil.mutListener.listen(42264)) {
            this.apiConnector = apiConnector;
        }
        this.preferenceService = preferenceService;
        if (!ListenerUtil.mutListener.listen(42265)) {
            this.databaseServiceNew = databaseServiceNew;
        }
        this.blackListService = blackListService;
        this.excludeListService = excludeListService;
    }

    @Override
    public byte[] getPublicKeyForIdentity(String identity, boolean fetch) {
        Contact contact = this.getContactForIdentity(identity);
        if (!ListenerUtil.mutListener.listen(42278)) {
            if (contact == null) {
                if (!ListenerUtil.mutListener.listen(42277)) {
                    if (fetch) {
                        try {
                            if (!ListenerUtil.mutListener.listen(42268)) {
                                // check if identity is on black list
                                if ((ListenerUtil.mutListener.listen(42267) ? (this.blackListService != null || this.blackListService.has(identity)) : (this.blackListService != null && this.blackListService.has(identity)))) {
                                    return null;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(42276)) {
                                if (this.preferenceService != null) {
                                    if (!ListenerUtil.mutListener.listen(42274)) {
                                        if (this.preferenceService.isSyncContacts()) {
                                            if (!ListenerUtil.mutListener.listen(42273)) {
                                                // check if is on exclude list
                                                if ((ListenerUtil.mutListener.listen(42269) ? (this.excludeListService != null || !this.excludeListService.has(identity)) : (this.excludeListService != null && !this.excludeListService.has(identity)))) {
                                                    if (!ListenerUtil.mutListener.listen(42270)) {
                                                        SynchronizeContactsUtil.startDirectly(identity);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(42271)) {
                                                        // try to select again
                                                        contact = this.getContactForIdentity(identity);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(42272)) {
                                                        if (contact != null) {
                                                            return contact.getPublicKey();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(42275)) {
                                        // do not fetch if block unknown is enabled
                                        if (this.preferenceService.isBlockUnknown()) {
                                            return null;
                                        }
                                    }
                                }
                            }
                            return this.fetchPublicKeyForIdentity(identity);
                        } catch (Exception e) {
                            if (!ListenerUtil.mutListener.listen(42266)) {
                                logger.error("Exception", e);
                            }
                            return null;
                        }
                    }
                }
                return null;
            }
        }
        return contact.getPublicKey();
    }

    /**
     *  Fetch a public key for a identity and save the contact
     *
     *  @param identity
     *  @throws ThreemaException if a contact with this identity already exists
     *  @return
     */
    public byte[] fetchPublicKeyForIdentity(String identity) throws FileNotFoundException {
        APIConnector.FetchIdentityResult result = null;
        try {
            Contact contact = this.getContactForIdentity(identity);
            if (!ListenerUtil.mutListener.listen(42279)) {
                if (contact != null) {
                    // cannot fetch and save... contact already exists
                    throw new ThreemaException("contact already exists, cannot fetch and save");
                }
            }
            if (!ListenerUtil.mutListener.listen(42280)) {
                result = this.apiConnector.fetchIdentity(identity);
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (NetworkOnMainThreadException e) {
            throw e;
        } catch (Exception e) {
            // do nothing
            return null;
        }
        byte[] b = result.publicKey;
        if (!ListenerUtil.mutListener.listen(42290)) {
            if (b != null) {
                ContactModel contact = new ContactModel(identity, b);
                if (!ListenerUtil.mutListener.listen(42281)) {
                    contact.setFeatureMask(result.featureMask);
                }
                if (!ListenerUtil.mutListener.listen(42282)) {
                    contact.setVerificationLevel(VerificationLevel.UNVERIFIED);
                }
                if (!ListenerUtil.mutListener.listen(42283)) {
                    contact.setDateCreated(new Date());
                }
                if (!ListenerUtil.mutListener.listen(42284)) {
                    contact.setType(result.type);
                }
                if (!ListenerUtil.mutListener.listen(42288)) {
                    switch(result.state) {
                        case IdentityState.ACTIVE:
                            if (!ListenerUtil.mutListener.listen(42285)) {
                                contact.setState(ContactModel.State.ACTIVE);
                            }
                            break;
                        case IdentityState.INACTIVE:
                            if (!ListenerUtil.mutListener.listen(42286)) {
                                contact.setState(ContactModel.State.INACTIVE);
                            }
                            break;
                        case IdentityState.INVALID:
                            if (!ListenerUtil.mutListener.listen(42287)) {
                                contact.setState(ContactModel.State.INVALID);
                            }
                            break;
                    }
                }
                if (!ListenerUtil.mutListener.listen(42289)) {
                    this.addContact(contact);
                }
                return b;
            }
        }
        return null;
    }

    @Override
    @Nullable
    public Contact getContactForIdentity(String identity) {
        return this.databaseServiceNew.getContactModelFactory().getByIdentity(identity);
    }

    /**
     *  @param identity
     *  @return
     */
    @Nullable
    public ContactModel getContactModelForIdentity(String identity) {
        if (!ListenerUtil.mutListener.listen(42291)) {
            if (!this.cache.containsKey(identity)) {
                return this.databaseServiceNew.getContactModelFactory().getByIdentity(identity);
            }
        }
        return this.cache.get(identity);
    }

    @Nullable
    public ContactModel getContactModelForPublicKey(final byte[] publicKey) {
        if (!ListenerUtil.mutListener.listen(42293)) {
            {
                long _loopCounter488 = 0;
                // check cache first
                for (String identity : this.cache.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter488", ++_loopCounter488);
                    if (!ListenerUtil.mutListener.listen(42292)) {
                        if (Arrays.equals(publicKey, this.cache.get(identity).getPublicKey())) {
                            return this.cache.get(identity);
                        }
                    }
                }
            }
        }
        return this.databaseServiceNew.getContactModelFactory().getByPublicKey(publicKey);
    }

    @Nullable
    public ContactModel getContactModelForLookupKey(final String lookupKey) {
        return this.databaseServiceNew.getContactModelFactory().getByLookupKey(lookupKey);
    }

    @Override
    public Collection<Contact> getAllContacts() {
        Collection<Contact> contacts = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(42294)) {
            contacts.addAll(this.databaseServiceNew.getContactModelFactory().getAll());
        }
        return contacts;
    }

    @Override
    public void addContact(Contact contact) {
        ContactModel contactModel = (ContactModel) contact;
        boolean isUpdate = false;
        ContactModelFactory contactModelFactory = this.databaseServiceNew.getContactModelFactory();
        // get db record
        ContactModel existingModel = contactModelFactory.getByIdentity(contactModel.getIdentity());
        if (!ListenerUtil.mutListener.listen(42298)) {
            if (existingModel != null) {
                if (!ListenerUtil.mutListener.listen(42295)) {
                    isUpdate = true;
                }
                if (!ListenerUtil.mutListener.listen(42297)) {
                    // check for modifications!
                    if (TestUtil.compare(contactModel.getModifiedValueCandidates(), existingModel.getModifiedValueCandidates())) {
                        if (!ListenerUtil.mutListener.listen(42296)) {
                            logger.debug("do not save unmodified contact");
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42300)) {
            if (contactModel.getColor() == 0) {
                // rebuild color for the contact model
                long count = this.databaseServiceNew.getContactModelFactory().count();
                if (!ListenerUtil.mutListener.listen(42299)) {
                    contactModel.setColor(ColorUtil.getInstance().getRecordColor((int) count));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(42301)) {
            contactModelFactory.createOrUpdate(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(42304)) {
            if (!isUpdate) {
                if (!ListenerUtil.mutListener.listen(42303)) {
                    this.fireOnNewContact(contactModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42302)) {
                    this.fireOnModifiedContact(contactModel);
                }
            }
        }
    }

    @Override
    public void hideContact(Contact contact, boolean hide) {
        ContactModel contactModel = (ContactModel) contact;
        if (!ListenerUtil.mutListener.listen(42305)) {
            contactModel.setIsHidden(hide);
        }
        ContactModelFactory contactModelFactory = this.databaseServiceNew.getContactModelFactory();
        if (!ListenerUtil.mutListener.listen(42306)) {
            contactModelFactory.createOrUpdate(contactModel);
        }
        if (!ListenerUtil.mutListener.listen(42309)) {
            if (hide) {
                if (!ListenerUtil.mutListener.listen(42308)) {
                    this.fireOnRemovedContact(contactModel);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(42307)) {
                    this.fireOnNewContact(contactModel);
                }
            }
        }
    }

    @Override
    public void removeContact(Contact contact) {
        if (!ListenerUtil.mutListener.listen(42310)) {
            this.removeContact((ContactModel) contact);
        }
    }

    public void removeContact(final ContactModel contactModel) {
        if (!ListenerUtil.mutListener.listen(42311)) {
            this.databaseServiceNew.getContactModelFactory().delete(contactModel);
        }
        synchronized (this.cache) {
            if (!ListenerUtil.mutListener.listen(42312)) {
                this.cache.remove(contactModel.getIdentity());
            }
        }
        if (!ListenerUtil.mutListener.listen(42313)) {
            fireOnRemovedContact(contactModel);
        }
    }

    @Override
    @Deprecated
    public void addContactStoreObserver(ContactStoreObserver observer) {
    }

    @Override
    @Deprecated
    public void removeContactStoreObserver(ContactStoreObserver observer) {
    }

    private void fireOnNewContact(final ContactModel createdContactModel) {
        if (!ListenerUtil.mutListener.listen(42316)) {
            ListenerManager.contactListeners.handle(new ListenerManager.HandleListener<ContactListener>() {

                @Override
                public void handle(ContactListener listener) {
                    if (!ListenerUtil.mutListener.listen(42315)) {
                        if (listener.handle(createdContactModel.getIdentity())) {
                            if (!ListenerUtil.mutListener.listen(42314)) {
                                listener.onNew(createdContactModel);
                            }
                        }
                    }
                }
            });
        }
    }

    private void fireOnModifiedContact(final ContactModel modifiedContactModel) {
        if (!ListenerUtil.mutListener.listen(42319)) {
            ListenerManager.contactListeners.handle(new ListenerManager.HandleListener<ContactListener>() {

                @Override
                public void handle(ContactListener listener) {
                    if (!ListenerUtil.mutListener.listen(42318)) {
                        if (listener.handle(modifiedContactModel.getIdentity())) {
                            if (!ListenerUtil.mutListener.listen(42317)) {
                                listener.onModified(modifiedContactModel);
                            }
                        }
                    }
                }
            });
        }
    }

    private void fireOnRemovedContact(final ContactModel removedContactModel) {
        if (!ListenerUtil.mutListener.listen(42322)) {
            ListenerManager.contactListeners.handle(new ListenerManager.HandleListener<ContactListener>() {

                @Override
                public void handle(ContactListener listener) {
                    if (!ListenerUtil.mutListener.listen(42321)) {
                        if (listener.handle(removedContactModel.getIdentity())) {
                            if (!ListenerUtil.mutListener.listen(42320)) {
                                listener.onRemoved(removedContactModel);
                            }
                        }
                    }
                }
            });
        }
    }

    public void reset(final ContactModel contactModel) {
        synchronized (this.cache) {
            ContactModel cached = Functional.select(this.cache, new IPredicateNonNull<ContactModel>() {

                @Override
                public boolean apply(@NonNull ContactModel contact) {
                    return contact.getIdentity().equals(contactModel.getIdentity());
                }
            });
            if (!ListenerUtil.mutListener.listen(42323)) {
                if (cached != null) {
                }
            }
        }
    }
}
