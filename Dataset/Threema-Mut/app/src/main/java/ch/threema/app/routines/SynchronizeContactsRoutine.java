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
package ch.threema.app.routines;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.RequiresPermission;
import ch.threema.app.services.ContactService;
import ch.threema.app.services.DeviceService;
import ch.threema.app.services.IdListService;
import ch.threema.app.services.LocaleService;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.services.UserService;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.stores.MatchTokenStore;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.ContactUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.base.ThreemaException;
import ch.threema.base.VerificationLevel;
import ch.threema.client.APIConnector;
import ch.threema.client.IdentityStoreInterface;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SynchronizeContactsRoutine implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeContactsRoutine.class);

    private final UserService userService;

    private final Context context;

    private final APIConnector apiConnector;

    private final ContactService contactService;

    private final LocaleService localeService;

    private final ContentResolver contentResolver;

    private final IdListService excludedSyncList;

    private final DeviceService deviceService;

    private final PreferenceService preferenceService;

    private final IdentityStoreInterface identityStore;

    private final IdListService blackListIdentityService;

    private final LicenseService<?> licenseService;

    private OnStatusUpdate onStatusUpdate;

    private final List<OnFinished> onFinished = new ArrayList<OnFinished>();

    private final List<OnStarted> onStarted = new ArrayList<OnStarted>();

    private final List<String> processingIdentities = new ArrayList<>();

    private boolean abort = false;

    private boolean running = false;

    public interface OnStatusUpdate {

        void newStatus(final long percent, final String message);

        void error(final Exception x);
    }

    public interface OnFinished {

        void finished(boolean success, long modifiedAccounts, List<ContactModel> createdContacts, long deletedAccounts);
    }

    public interface OnStarted {

        void started(boolean fullSync);
    }

    public SynchronizeContactsRoutine(Context context, APIConnector apiConnector, ContactService contactService, UserService userService, LocaleService localeService, ContentResolver contentResolver, IdListService excludedSyncList, DeviceService deviceService, PreferenceService preferenceService, IdentityStoreInterface identityStore, IdListService blackListIdentityService, LicenseService<?> licenseService) {
        this.context = context;
        this.apiConnector = apiConnector;
        this.userService = userService;
        this.contactService = contactService;
        this.localeService = localeService;
        this.contentResolver = contentResolver;
        this.excludedSyncList = excludedSyncList;
        this.deviceService = deviceService;
        this.preferenceService = preferenceService;
        this.identityStore = identityStore;
        this.licenseService = licenseService;
        this.blackListIdentityService = blackListIdentityService;
    }

    public SynchronizeContactsRoutine addProcessIdentity(String identity) {
        if (!ListenerUtil.mutListener.listen(34585)) {
            if ((ListenerUtil.mutListener.listen(34583) ? (!TestUtil.empty(identity) || !this.processingIdentities.contains(identity)) : (!TestUtil.empty(identity) && !this.processingIdentities.contains(identity)))) {
                if (!ListenerUtil.mutListener.listen(34584)) {
                    this.processingIdentities.add(identity);
                }
            }
        }
        return this;
    }

    public void abort() {
        if (!ListenerUtil.mutListener.listen(34586)) {
            this.abort = true;
        }
    }

    public boolean running() {
        return this.running;
    }

    public boolean fullSync() {
        return (ListenerUtil.mutListener.listen(34591) ? (this.processingIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(34590) ? (this.processingIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(34589) ? (this.processingIdentities.size() > 0) : (ListenerUtil.mutListener.listen(34588) ? (this.processingIdentities.size() < 0) : (ListenerUtil.mutListener.listen(34587) ? (this.processingIdentities.size() != 0) : (this.processingIdentities.size() == 0))))));
    }

    @Override
    @RequiresPermission(Manifest.permission.WRITE_CONTACTS)
    public void run() {
        if (!ListenerUtil.mutListener.listen(34592)) {
            logger.info("SynchronizeContacts run started.");
        }
        if (!ListenerUtil.mutListener.listen(34594)) {
            if (!ConfigUtils.isPermissionGranted(context, Manifest.permission.WRITE_CONTACTS)) {
                if (!ListenerUtil.mutListener.listen(34593)) {
                    logger.info("No contacts permission. Aborting.");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(34595)) {
            this.running = true;
        }
        if (!ListenerUtil.mutListener.listen(34602)) {
            {
                long _loopCounter250 = 0;
                for (OnStarted s : this.onStarted) {
                    ListenerUtil.loopListener.listen("_loopCounter250", ++_loopCounter250);
                    if (!ListenerUtil.mutListener.listen(34601)) {
                        s.started((ListenerUtil.mutListener.listen(34600) ? (this.processingIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(34599) ? (this.processingIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(34598) ? (this.processingIdentities.size() > 0) : (ListenerUtil.mutListener.listen(34597) ? (this.processingIdentities.size() < 0) : (ListenerUtil.mutListener.listen(34596) ? (this.processingIdentities.size() != 0) : (this.processingIdentities.size() == 0)))))));
                    }
                }
            }
        }
        boolean success = false;
        long deletedCount = 0;
        long modifiedCount = 0;
        List<ContactModel> insertedContacts = new ArrayList<>();
        try {
            if (!ListenerUtil.mutListener.listen(34611)) {
                if (!this.preferenceService.isSyncContacts()) {
                    throw new ThreemaException("sync is disabled in preferences, not allowed to call synchronizecontacts routine");
                }
            }
            if (!ListenerUtil.mutListener.listen(34613)) {
                if ((ListenerUtil.mutListener.listen(34612) ? (this.deviceService != null || !this.deviceService.isOnline()) : (this.deviceService != null && !this.deviceService.isOnline()))) {
                    throw new ThreemaException("no connection");
                }
            }
            // read emails
            final Map<String, ContactMatchKeyEmail> emails = this.readEmails();
            // read phone numbers
            final Map<String, ContactMatchKeyPhone> phoneNumbers = this.readPhoneNumbers();
            // send hashes to server and get result
            MatchTokenStore matchTokenStore = new MatchTokenStore(this.preferenceService);
            Map<String, APIConnector.MatchIdentityResult> foundIds = this.apiConnector.matchIdentities(emails, phoneNumbers, this.localeService.getCountryIsoCode(), false, identityStore, matchTokenStore);
            final List<String> preSynchronizedIdentities = new ArrayList<>();
            HashMap<String, Long> existingRawContacts = new HashMap<>();
            if (!ListenerUtil.mutListener.listen(34619)) {
                if (this.fullSync()) {
                    List<String> synchronizedIdentities = this.contactService.getSynchronizedIdentities();
                    if (!ListenerUtil.mutListener.listen(34615)) {
                        if (synchronizedIdentities != null) {
                            if (!ListenerUtil.mutListener.listen(34614)) {
                                preSynchronizedIdentities.addAll(synchronizedIdentities);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(34616)) {
                        existingRawContacts = AndroidContactUtil.getInstance().getAllThreemaRawContacts();
                    }
                    if (!ListenerUtil.mutListener.listen(34618)) {
                        if (existingRawContacts != null) {
                            if (!ListenerUtil.mutListener.listen(34617)) {
                                logger.debug("Number of existing raw contacts {}", existingRawContacts.size());
                            }
                        }
                    }
                }
            }
            // looping result and create/update contacts
            ArrayList<ContentProviderOperation> contentProviderOperations = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(34674)) {
                {
                    long _loopCounter253 = 0;
                    for (Map.Entry<String, APIConnector.MatchIdentityResult> id : foundIds.entrySet()) {
                        ListenerUtil.loopListener.listen("_loopCounter253", ++_loopCounter253);
                        if (!ListenerUtil.mutListener.listen(34622)) {
                            if (this.abort) {
                                if (!ListenerUtil.mutListener.listen(34621)) {
                                    {
                                        long _loopCounter252 = 0;
                                        // abort!
                                        for (OnFinished f : this.onFinished) {
                                            ListenerUtil.loopListener.listen("_loopCounter252", ++_loopCounter252);
                                            if (!ListenerUtil.mutListener.listen(34620)) {
                                                f.finished(false, modifiedCount, insertedContacts, deletedCount);
                                            }
                                        }
                                    }
                                }
                                return;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34623)) {
                            // Do not add own ID as contact
                            if (TestUtil.compare(id.getKey(), this.userService.getIdentity())) {
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34625)) {
                            // Do not sync contacts on exclude list
                            if ((ListenerUtil.mutListener.listen(34624) ? (this.excludedSyncList != null || this.excludedSyncList.has(id.getKey())) : (this.excludedSyncList != null && this.excludedSyncList.has(id.getKey())))) {
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34632)) {
                            if ((ListenerUtil.mutListener.listen(34631) ? ((ListenerUtil.mutListener.listen(34630) ? (this.processingIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(34629) ? (this.processingIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(34628) ? (this.processingIdentities.size() < 0) : (ListenerUtil.mutListener.listen(34627) ? (this.processingIdentities.size() != 0) : (ListenerUtil.mutListener.listen(34626) ? (this.processingIdentities.size() == 0) : (this.processingIdentities.size() > 0)))))) || !this.processingIdentities.contains(id.getKey())) : ((ListenerUtil.mutListener.listen(34630) ? (this.processingIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(34629) ? (this.processingIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(34628) ? (this.processingIdentities.size() < 0) : (ListenerUtil.mutListener.listen(34627) ? (this.processingIdentities.size() != 0) : (ListenerUtil.mutListener.listen(34626) ? (this.processingIdentities.size() == 0) : (this.processingIdentities.size() > 0)))))) && !this.processingIdentities.contains(id.getKey())))) {
                                continue;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34633)) {
                            // remove if list contains this key
                            preSynchronizedIdentities.remove(id.getKey());
                        }
                        final ContactMatchKeyEmail matchKeyEmail = (ContactMatchKeyEmail) id.getValue().refObjectEmail;
                        final ContactMatchKeyPhone matchKeyPhone = (ContactMatchKeyPhone) id.getValue().refObjectMobileNo;
                        long contactId;
                        String lookupKey;
                        if (matchKeyEmail != null) {
                            contactId = matchKeyEmail.contactId;
                            lookupKey = matchKeyEmail.lookupKey;
                        } else {
                            contactId = matchKeyPhone.contactId;
                            lookupKey = matchKeyPhone.lookupKey;
                        }
                        // try to get the contact
                        ContactModel contact = this.contactService.getByIdentity(id.getKey());
                        if (!ListenerUtil.mutListener.listen(34639)) {
                            // contact does not exist, create a new one
                            if (contact == null) {
                                if (!ListenerUtil.mutListener.listen(34635)) {
                                    contact = new ContactModel(id.getKey(), id.getValue().publicKey);
                                }
                                if (!ListenerUtil.mutListener.listen(34636)) {
                                    contact.setVerificationLevel(VerificationLevel.SERVER_VERIFIED);
                                }
                                if (!ListenerUtil.mutListener.listen(34637)) {
                                    contact.setDateCreated(new Date());
                                }
                                if (!ListenerUtil.mutListener.listen(34638)) {
                                    insertedContacts.add(contact);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(34634)) {
                                    modifiedCount++;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34641)) {
                            if (contact.getVerificationLevel() == VerificationLevel.UNVERIFIED) {
                                if (!ListenerUtil.mutListener.listen(34640)) {
                                    contact.setVerificationLevel(VerificationLevel.SERVER_VERIFIED);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34642)) {
                            contact.setIsSynchronized(true);
                        }
                        if (!ListenerUtil.mutListener.listen(34643)) {
                            contact.setIsHidden(false);
                        }
                        if (!ListenerUtil.mutListener.listen(34650)) {
                            if ((ListenerUtil.mutListener.listen(34648) ? (contactId >= 0L) : (ListenerUtil.mutListener.listen(34647) ? (contactId <= 0L) : (ListenerUtil.mutListener.listen(34646) ? (contactId < 0L) : (ListenerUtil.mutListener.listen(34645) ? (contactId != 0L) : (ListenerUtil.mutListener.listen(34644) ? (contactId == 0L) : (contactId > 0L))))))) {
                                if (!ListenerUtil.mutListener.listen(34649)) {
                                    // It can optionally also have a "/" and last known contact ID appended after that. This "complete" format is an important optimization and is highly recommended.
                                    contact.setAndroidContactLookupKey(lookupKey + "/" + contactId);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(34668)) {
                            if (fullSync()) {
                                Long parentContactId = existingRawContacts.get(contact.getIdentity());
                                if (!ListenerUtil.mutListener.listen(34667)) {
                                    if ((ListenerUtil.mutListener.listen(34656) ? (parentContactId == null && (ListenerUtil.mutListener.listen(34655) ? (parentContactId >= contactId) : (ListenerUtil.mutListener.listen(34654) ? (parentContactId <= contactId) : (ListenerUtil.mutListener.listen(34653) ? (parentContactId > contactId) : (ListenerUtil.mutListener.listen(34652) ? (parentContactId < contactId) : (ListenerUtil.mutListener.listen(34651) ? (parentContactId == contactId) : (parentContactId != contactId))))))) : (parentContactId == null || (ListenerUtil.mutListener.listen(34655) ? (parentContactId >= contactId) : (ListenerUtil.mutListener.listen(34654) ? (parentContactId <= contactId) : (ListenerUtil.mutListener.listen(34653) ? (parentContactId > contactId) : (ListenerUtil.mutListener.listen(34652) ? (parentContactId < contactId) : (ListenerUtil.mutListener.listen(34651) ? (parentContactId == contactId) : (parentContactId != contactId))))))))) {
                                        if (!ListenerUtil.mutListener.listen(34666)) {
                                            if ((ListenerUtil.mutListener.listen(34662) ? (contactId >= 0L) : (ListenerUtil.mutListener.listen(34661) ? (contactId <= 0L) : (ListenerUtil.mutListener.listen(34660) ? (contactId < 0L) : (ListenerUtil.mutListener.listen(34659) ? (contactId != 0L) : (ListenerUtil.mutListener.listen(34658) ? (contactId == 0L) : (contactId > 0L))))))) {
                                                // raw contact does not exist yet, create it
                                                boolean supportsVoiceCalls = (ListenerUtil.mutListener.listen(34663) ? (ContactUtil.canReceiveVoipMessages(contact, this.blackListIdentityService) || ConfigUtils.isCallsEnabled(context, preferenceService, licenseService)) : (ContactUtil.canReceiveVoipMessages(contact, this.blackListIdentityService) && ConfigUtils.isCallsEnabled(context, preferenceService, licenseService)));
                                                if (!ListenerUtil.mutListener.listen(34664)) {
                                                    // create a raw contact for our stuff and aggregate it
                                                    AndroidContactUtil.getInstance().createThreemaRawContact(contentProviderOperations, matchKeyEmail != null ? matchKeyEmail.rawContactId : matchKeyPhone.rawContactId, contact, supportsVoiceCalls);
                                                }
                                                if (!ListenerUtil.mutListener.listen(34665)) {
                                                    // delete entry after processing - remaining ("stray") entries will be deleted from raw contacts after this run
                                                    existingRawContacts.remove(contact.getIdentity());
                                                }
                                            }
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(34657)) {
                                            // all good - we can delete the hash
                                            existingRawContacts.remove(contact.getIdentity());
                                        }
                                    }
                                }
                            }
                        }
                        try {
                            if (!ListenerUtil.mutListener.listen(34671)) {
                                AndroidContactUtil.getInstance().updateNameByAndroidContact(contact);
                            }
                            if (!ListenerUtil.mutListener.listen(34672)) {
                                AndroidContactUtil.getInstance().updateAvatarByAndroidContact(contact);
                            }
                            if (!ListenerUtil.mutListener.listen(34673)) {
                                // save the contact
                                this.contactService.save(contact);
                            }
                        } catch (ThreemaException e) {
                            if (!ListenerUtil.mutListener.listen(34669)) {
                                existingRawContacts.put(contact.getIdentity(), 0L);
                            }
                            if (!ListenerUtil.mutListener.listen(34670)) {
                                logger.error("Contact lookup Exception", e);
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(34683)) {
                if ((ListenerUtil.mutListener.listen(34679) ? (contentProviderOperations.size() >= 0) : (ListenerUtil.mutListener.listen(34678) ? (contentProviderOperations.size() <= 0) : (ListenerUtil.mutListener.listen(34677) ? (contentProviderOperations.size() < 0) : (ListenerUtil.mutListener.listen(34676) ? (contentProviderOperations.size() != 0) : (ListenerUtil.mutListener.listen(34675) ? (contentProviderOperations.size() == 0) : (contentProviderOperations.size() > 0))))))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(34681)) {
                            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
                        }
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(34680)) {
                            logger.error("Error during raw contact creation! ", e);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(34682)) {
                        contentProviderOperations.clear();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(34690)) {
                // delete remaining / stray raw contacts
                if ((ListenerUtil.mutListener.listen(34688) ? (existingRawContacts.size() >= 0) : (ListenerUtil.mutListener.listen(34687) ? (existingRawContacts.size() <= 0) : (ListenerUtil.mutListener.listen(34686) ? (existingRawContacts.size() < 0) : (ListenerUtil.mutListener.listen(34685) ? (existingRawContacts.size() != 0) : (ListenerUtil.mutListener.listen(34684) ? (existingRawContacts.size() == 0) : (existingRawContacts.size() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(34689)) {
                        AndroidContactUtil.getInstance().deleteThreemaRawContacts(existingRawContacts);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(34699)) {
                if ((ListenerUtil.mutListener.listen(34695) ? (preSynchronizedIdentities.size() >= 0) : (ListenerUtil.mutListener.listen(34694) ? (preSynchronizedIdentities.size() <= 0) : (ListenerUtil.mutListener.listen(34693) ? (preSynchronizedIdentities.size() < 0) : (ListenerUtil.mutListener.listen(34692) ? (preSynchronizedIdentities.size() != 0) : (ListenerUtil.mutListener.listen(34691) ? (preSynchronizedIdentities.size() == 0) : (preSynchronizedIdentities.size() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(34696)) {
                        logger.debug("Degrade contact(s). found {} synchronized contacts that are not synchronized", preSynchronizedIdentities.size());
                    }
                    List<ContactModel> contactModels = this.contactService.getByIdentities(preSynchronizedIdentities);
                    if (!ListenerUtil.mutListener.listen(34698)) {
                        modifiedCount += this.contactService.save(contactModels, new ContactService.ContactProcessor() {

                            @Override
                            public boolean process(ContactModel contactModel) {
                                if (!ListenerUtil.mutListener.listen(34697)) {
                                    contactModel.setIsSynchronized(false);
                                }
                                return true;
                            }
                        });
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(34700)) {
                success = true;
            }
        } catch (final Exception x) {
            if (!ListenerUtil.mutListener.listen(34603)) {
                success = false;
            }
            if (!ListenerUtil.mutListener.listen(34604)) {
                logger.error("Exception", x);
            }
            if (!ListenerUtil.mutListener.listen(34606)) {
                if (this.onStatusUpdate != null) {
                    if (!ListenerUtil.mutListener.listen(34605)) {
                        this.onStatusUpdate.error(x);
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(34607)) {
                logger.debug("Finished [success=" + success + ", modified=" + modifiedCount + ", inserted =" + insertedContacts.size() + ", deleted =" + deletedCount + "]");
            }
            if (!ListenerUtil.mutListener.listen(34609)) {
                {
                    long _loopCounter251 = 0;
                    for (OnFinished f : this.onFinished) {
                        ListenerUtil.loopListener.listen("_loopCounter251", ++_loopCounter251);
                        if (!ListenerUtil.mutListener.listen(34608)) {
                            f.finished(success, modifiedCount, insertedContacts, deletedCount);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(34610)) {
                this.running = false;
            }
        }
    }

    public SynchronizeContactsRoutine setOnStatusUpdate(OnStatusUpdate onStatusUpdate) {
        if (!ListenerUtil.mutListener.listen(34701)) {
            this.onStatusUpdate = onStatusUpdate;
        }
        return this;
    }

    public SynchronizeContactsRoutine addOnFinished(OnFinished onFinished) {
        if (!ListenerUtil.mutListener.listen(34702)) {
            this.onFinished.add(onFinished);
        }
        return this;
    }

    public SynchronizeContactsRoutine addOnStarted(OnStarted onStarted) {
        if (!ListenerUtil.mutListener.listen(34703)) {
            this.onStarted.add(onStarted);
        }
        return this;
    }

    private Map<String, ContactMatchKeyPhone> readPhoneNumbers() {
        Map<String, ContactMatchKeyPhone> phoneNumbers = new HashMap<>();
        String selection;
        if ((ListenerUtil.mutListener.listen(34708) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34707) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34706) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34705) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34704) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
            selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " > 0 AND " + ContactsContract.CommonDataKinds.Phone.IN_DEFAULT_DIRECTORY + " = 1";
        } else {
            selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " > 0";
        }
        try (Cursor phonesCursor = this.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] { ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY, ContactsContract.CommonDataKinds.Phone.NUMBER }, selection, null, null)) {
            if (!ListenerUtil.mutListener.listen(34723)) {
                if ((ListenerUtil.mutListener.listen(34714) ? (phonesCursor != null || (ListenerUtil.mutListener.listen(34713) ? (phonesCursor.getCount() >= 0) : (ListenerUtil.mutListener.listen(34712) ? (phonesCursor.getCount() <= 0) : (ListenerUtil.mutListener.listen(34711) ? (phonesCursor.getCount() < 0) : (ListenerUtil.mutListener.listen(34710) ? (phonesCursor.getCount() != 0) : (ListenerUtil.mutListener.listen(34709) ? (phonesCursor.getCount() == 0) : (phonesCursor.getCount() > 0))))))) : (phonesCursor != null && (ListenerUtil.mutListener.listen(34713) ? (phonesCursor.getCount() >= 0) : (ListenerUtil.mutListener.listen(34712) ? (phonesCursor.getCount() <= 0) : (ListenerUtil.mutListener.listen(34711) ? (phonesCursor.getCount() < 0) : (ListenerUtil.mutListener.listen(34710) ? (phonesCursor.getCount() != 0) : (ListenerUtil.mutListener.listen(34709) ? (phonesCursor.getCount() == 0) : (phonesCursor.getCount() > 0))))))))) {
                    final int rawContactIdIndex = phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID);
                    final int idColumnIndex = phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
                    final int lookupKeyColumnIndex = phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY);
                    final int phoneNumberIndex = phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    if (!ListenerUtil.mutListener.listen(34722)) {
                        {
                            long _loopCounter254 = 0;
                            while (phonesCursor.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter254", ++_loopCounter254);
                                long rawContactId = phonesCursor.getLong(rawContactIdIndex);
                                long contactId = phonesCursor.getLong(idColumnIndex);
                                String lookupKey = phonesCursor.getString(lookupKeyColumnIndex);
                                String phoneNumber = phonesCursor.getString(phoneNumberIndex);
                                if (!ListenerUtil.mutListener.listen(34721)) {
                                    if ((ListenerUtil.mutListener.listen(34715) ? (lookupKey != null || !TestUtil.empty(phoneNumber)) : (lookupKey != null && !TestUtil.empty(phoneNumber)))) {
                                        ContactMatchKeyPhone matchKey = new ContactMatchKeyPhone();
                                        if (!ListenerUtil.mutListener.listen(34716)) {
                                            matchKey.contactId = contactId;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34717)) {
                                            matchKey.lookupKey = lookupKey;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34718)) {
                                            matchKey.rawContactId = rawContactId;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34719)) {
                                            matchKey.phoneNumber = phoneNumber;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34720)) {
                                            phoneNumbers.put(phoneNumber, matchKey);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return phoneNumbers;
    }

    private Map<String, ContactMatchKeyEmail> readEmails() {
        Map<String, ContactMatchKeyEmail> emails = new HashMap<>();
        String selection = null;
        if (!ListenerUtil.mutListener.listen(34730)) {
            if ((ListenerUtil.mutListener.listen(34728) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34727) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34726) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34725) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(34724) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(34729)) {
                    selection = ContactsContract.CommonDataKinds.Email.IN_DEFAULT_DIRECTORY + " = 1";
                }
            }
        }
        try (Cursor emailsCursor = this.contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, new String[] { ContactsContract.CommonDataKinds.Email.RAW_CONTACT_ID, ContactsContract.CommonDataKinds.Email.CONTACT_ID, ContactsContract.CommonDataKinds.Email.LOOKUP_KEY, ContactsContract.CommonDataKinds.Email.DATA }, selection, null, null)) {
            if (!ListenerUtil.mutListener.listen(34745)) {
                if ((ListenerUtil.mutListener.listen(34736) ? (emailsCursor != null || (ListenerUtil.mutListener.listen(34735) ? (emailsCursor.getCount() >= 0) : (ListenerUtil.mutListener.listen(34734) ? (emailsCursor.getCount() <= 0) : (ListenerUtil.mutListener.listen(34733) ? (emailsCursor.getCount() < 0) : (ListenerUtil.mutListener.listen(34732) ? (emailsCursor.getCount() != 0) : (ListenerUtil.mutListener.listen(34731) ? (emailsCursor.getCount() == 0) : (emailsCursor.getCount() > 0))))))) : (emailsCursor != null && (ListenerUtil.mutListener.listen(34735) ? (emailsCursor.getCount() >= 0) : (ListenerUtil.mutListener.listen(34734) ? (emailsCursor.getCount() <= 0) : (ListenerUtil.mutListener.listen(34733) ? (emailsCursor.getCount() < 0) : (ListenerUtil.mutListener.listen(34732) ? (emailsCursor.getCount() != 0) : (ListenerUtil.mutListener.listen(34731) ? (emailsCursor.getCount() == 0) : (emailsCursor.getCount() > 0))))))))) {
                    final int rawContactIdIndex = emailsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.RAW_CONTACT_ID);
                    final int idColumnIndex = emailsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID);
                    final int lookupKeyColumnIndex = emailsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.LOOKUP_KEY);
                    final int emailIndex = emailsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
                    if (!ListenerUtil.mutListener.listen(34744)) {
                        {
                            long _loopCounter255 = 0;
                            while (emailsCursor.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter255", ++_loopCounter255);
                                long rawContactId = emailsCursor.getLong(rawContactIdIndex);
                                long contactId = emailsCursor.getLong(idColumnIndex);
                                String lookupKey = emailsCursor.getString(lookupKeyColumnIndex);
                                String email = emailsCursor.getString(emailIndex);
                                if (!ListenerUtil.mutListener.listen(34743)) {
                                    if ((ListenerUtil.mutListener.listen(34737) ? (lookupKey != null || !TestUtil.empty(email)) : (lookupKey != null && !TestUtil.empty(email)))) {
                                        ContactMatchKeyEmail matchKey = new ContactMatchKeyEmail();
                                        if (!ListenerUtil.mutListener.listen(34738)) {
                                            matchKey.contactId = contactId;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34739)) {
                                            matchKey.lookupKey = lookupKey;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34740)) {
                                            matchKey.rawContactId = rawContactId;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34741)) {
                                            matchKey.email = email;
                                        }
                                        if (!ListenerUtil.mutListener.listen(34742)) {
                                            emails.put(email, matchKey);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return emails;
    }

    private static class ContactMatchKey {

        long contactId;

        String lookupKey;

        long rawContactId;
    }

    private static class ContactMatchKeyEmail extends ContactMatchKey {

        String email;
    }

    private static class ContactMatchKeyPhone extends ContactMatchKey {

        String phoneNumber;
    }
}
