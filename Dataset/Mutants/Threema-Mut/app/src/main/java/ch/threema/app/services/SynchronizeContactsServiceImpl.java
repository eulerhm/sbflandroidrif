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
package ch.threema.app.services;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.ContentResolver;
import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.listeners.NewSyncedContactsListener;
import ch.threema.app.listeners.SynchronizeContactsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.routines.UpdateBusinessAvatarRoutine;
import ch.threema.app.services.license.LicenseService;
import ch.threema.app.utils.AndroidContactUtil;
import ch.threema.app.utils.ContactUtil;
import ch.threema.base.VerificationLevel;
import ch.threema.client.APIConnector;
import ch.threema.client.IdentityStoreInterface;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SynchronizeContactsServiceImpl implements SynchronizeContactsService {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeContactsServiceImpl.class);

    private final ContentResolver contentResolver;

    private final APIConnector apiConnector;

    private final ContactService contactService;

    private final UserService userService;

    private final LocaleService localeService;

    private final IdentityStoreInterface identityStore;

    private final List<SynchronizeContactsRoutine> pendingRoutines = new ArrayList<SynchronizeContactsRoutine>();

    private final IdListService excludedIdentityListService;

    private final PreferenceService preferenceService;

    private final DeviceService deviceService;

    private final Context context;

    private final FileService fileService;

    private final IdListService blackListIdentityService;

    private final LicenseService licenseService;

    private Date latestFullSync;

    public SynchronizeContactsServiceImpl(Context context, APIConnector apiConnector, ContactService contactService, UserService userService, LocaleService localeService, IdListService excludedIdentityListService, PreferenceService preferenceService, DeviceService deviceService, FileService fileService, IdentityStoreInterface identityStore, IdListService blackListIdentityService, LicenseService licenseService) {
        this.excludedIdentityListService = excludedIdentityListService;
        this.preferenceService = preferenceService;
        this.deviceService = deviceService;
        this.context = context;
        this.fileService = fileService;
        this.contentResolver = context.getContentResolver();
        this.apiConnector = apiConnector;
        this.contactService = contactService;
        this.userService = userService;
        this.localeService = localeService;
        this.identityStore = identityStore;
        this.licenseService = licenseService;
        this.blackListIdentityService = blackListIdentityService;
    }

    @Override
    public boolean instantiateSynchronizationAndRun() {
        final SynchronizeContactsRoutine sync = this.instantiateSynchronization();
        if (!ListenerUtil.mutListener.listen(41051)) {
            if (sync != null) {
                if (!ListenerUtil.mutListener.listen(41050)) {
                    if ((ListenerUtil.mutListener.listen(41036) ? (this.deviceService != null || this.deviceService.isOnline()) : (this.deviceService != null && this.deviceService.isOnline()))) {
                        if (!ListenerUtil.mutListener.listen(41040)) {
                            sync.addOnFinished(new SynchronizeContactsRoutine.OnFinished() {

                                @Override
                                public void finished(boolean success, long modifiedAccounts, List<ContactModel> createdContacts, long deletedAccounts) {
                                    if (!ListenerUtil.mutListener.listen(41039)) {
                                        // let user know that contact was added
                                        ListenerManager.newSyncedContactListener.handle(new ListenerManager.HandleListener<NewSyncedContactsListener>() {

                                            @Override
                                            public void handle(NewSyncedContactsListener listener) {
                                                if (!ListenerUtil.mutListener.listen(41038)) {
                                                    listener.onNew(createdContacts);
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(41049)) {
                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(41041)) {
                                        sync.run();
                                    }
                                    // disable contact changed event handler
                                    boolean enableState = ListenerManager.contactListeners.isEnabled();
                                    try {
                                        if (!ListenerUtil.mutListener.listen(41045)) {
                                            if (enableState) {
                                                if (!ListenerUtil.mutListener.listen(41044)) {
                                                    ListenerManager.contactListeners.enabled(false);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(41048)) {
                                            {
                                                long _loopCounter469 = 0;
                                                for (ContactModel contactModel : contactService.getAll(true, true)) {
                                                    ListenerUtil.loopListener.listen("_loopCounter469", ++_loopCounter469);
                                                    if (!ListenerUtil.mutListener.listen(41047)) {
                                                        if (ContactUtil.isChannelContact(contactModel)) {
                                                            if (!ListenerUtil.mutListener.listen(41046)) {
                                                                UpdateBusinessAvatarRoutine.start(contactModel, fileService, contactService, true);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } catch (Exception x) {
                                        if (!ListenerUtil.mutListener.listen(41042)) {
                                            // log exception and ignore
                                            logger.error("Ignoring exception", x);
                                        }
                                    } finally {
                                        if (!ListenerUtil.mutListener.listen(41043)) {
                                            // enable contact listener again
                                            ListenerManager.contactListeners.enabled(enableState);
                                        }
                                    }
                                }
                            }, "SynchronizeContactsRoutine").start();
                        }
                        return true;
                    } else {
                        if (!ListenerUtil.mutListener.listen(41037)) {
                            this.finishedRoutine(sync);
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public SynchronizeContactsRoutine instantiateSynchronization() {
        Account account = this.userService.getAccount();
        if (!ListenerUtil.mutListener.listen(41052)) {
            if (account != null) {
                return this.instantiateSynchronization(account);
            }
        }
        return null;
    }

    @Override
    public SynchronizeContactsRoutine instantiateSynchronization(Account account) {
        if (!ListenerUtil.mutListener.listen(41053)) {
            logger.info("Running contact sync");
        }
        if (!ListenerUtil.mutListener.listen(41054)) {
            logger.debug("instantiateSynchronization with account {}", account);
        }
        final SynchronizeContactsRoutine routine = new SynchronizeContactsRoutine(this.context, this.apiConnector, this.contactService, this.userService, this.localeService, this.contentResolver, this.excludedIdentityListService, this.deviceService, this.preferenceService, this.identityStore, this.blackListIdentityService, this.licenseService);
        synchronized (this.pendingRoutines) {
            if (!ListenerUtil.mutListener.listen(41055)) {
                this.pendingRoutines.add(routine);
            }
        }
        if (!ListenerUtil.mutListener.listen(41057)) {
            routine.addOnFinished(new SynchronizeContactsRoutine.OnFinished() {

                @Override
                public void finished(boolean success, long modifiedAccounts, List<ContactModel> createdContacts, long deletedAccounts) {
                    if (!ListenerUtil.mutListener.listen(41056)) {
                        finishedRoutine(routine);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(41060)) {
            routine.addOnStarted(new SynchronizeContactsRoutine.OnStarted() {

                @Override
                public void started(boolean fullSync) {
                    if (!ListenerUtil.mutListener.listen(41059)) {
                        if (fullSync) {
                            if (!ListenerUtil.mutListener.listen(41058)) {
                                latestFullSync = new Date();
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(41062)) {
            ListenerManager.synchronizeContactsListeners.handle(new ListenerManager.HandleListener<SynchronizeContactsListener>() {

                @Override
                public void handle(SynchronizeContactsListener listener) {
                    if (!ListenerUtil.mutListener.listen(41061)) {
                        listener.onStarted(routine);
                    }
                }
            });
        }
        return routine;
    }

    @Override
    public boolean isSynchronizationInProgress() {
        return (ListenerUtil.mutListener.listen(41067) ? (this.pendingRoutines.size() >= 0) : (ListenerUtil.mutListener.listen(41066) ? (this.pendingRoutines.size() <= 0) : (ListenerUtil.mutListener.listen(41065) ? (this.pendingRoutines.size() < 0) : (ListenerUtil.mutListener.listen(41064) ? (this.pendingRoutines.size() != 0) : (ListenerUtil.mutListener.listen(41063) ? (this.pendingRoutines.size() == 0) : (this.pendingRoutines.size() > 0))))));
    }

    @Override
    public boolean isFullSyncInProgress() {
        synchronized (this.pendingRoutines) {
            return Functional.select(this.pendingRoutines, new IPredicateNonNull<SynchronizeContactsRoutine>() {

                @Override
                public boolean apply(@NonNull SynchronizeContactsRoutine routine) {
                    return (ListenerUtil.mutListener.listen(41069) ? ((ListenerUtil.mutListener.listen(41068) ? (routine != null || routine.running()) : (routine != null && routine.running())) || routine.fullSync()) : ((ListenerUtil.mutListener.listen(41068) ? (routine != null || routine.running()) : (routine != null && routine.running())) && routine.fullSync()));
                }
            }) != null;
        }
    }

    @Override
    public boolean enableSync() {
        boolean success = false;
        if (!ListenerUtil.mutListener.listen(41071)) {
            if (this.userService != null) {
                Account account = this.userService.getAccount(true);
                if (!ListenerUtil.mutListener.listen(41070)) {
                    success = account != null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41074)) {
            if ((ListenerUtil.mutListener.listen(41072) ? (success || this.preferenceService != null) : (success && this.preferenceService != null))) {
                if (!ListenerUtil.mutListener.listen(41073)) {
                    this.preferenceService.setSyncContacts(true);
                }
            }
        }
        return success;
    }

    @Override
    public boolean disableSync(final Runnable runAfterRemovedAccount) {
        if (!ListenerUtil.mutListener.listen(41090)) {
            if (this.userService != null) {
                // cancel all syncs!
                synchronized (this.pendingRoutines) {
                    if (!ListenerUtil.mutListener.listen(41085)) {
                        {
                            long _loopCounter470 = 0;
                            for (int n = (ListenerUtil.mutListener.listen(41084) ? (this.pendingRoutines.size() % 1) : (ListenerUtil.mutListener.listen(41083) ? (this.pendingRoutines.size() / 1) : (ListenerUtil.mutListener.listen(41082) ? (this.pendingRoutines.size() * 1) : (ListenerUtil.mutListener.listen(41081) ? (this.pendingRoutines.size() + 1) : (this.pendingRoutines.size() - 1))))); (ListenerUtil.mutListener.listen(41080) ? (n <= 0) : (ListenerUtil.mutListener.listen(41079) ? (n > 0) : (ListenerUtil.mutListener.listen(41078) ? (n < 0) : (ListenerUtil.mutListener.listen(41077) ? (n != 0) : (ListenerUtil.mutListener.listen(41076) ? (n == 0) : (n >= 0)))))); n--) {
                                ListenerUtil.loopListener.listen("_loopCounter470", ++_loopCounter470);
                                if (!ListenerUtil.mutListener.listen(41075)) {
                                    this.pendingRoutines.get(n).abort();
                                }
                            }
                        }
                    }
                }
                int numDeleted = AndroidContactUtil.getInstance().deleteAllThreemaRawContacts();
                if (!ListenerUtil.mutListener.listen(41086)) {
                    logger.debug("*** deleted {} raw contacts", numDeleted);
                }
                if (!ListenerUtil.mutListener.listen(41089)) {
                    if (!this.userService.removeAccount(new AccountManagerCallback<Boolean>() {

                        @Override
                        public void run(AccountManagerFuture<Boolean> future) {
                            if (!ListenerUtil.mutListener.listen(41087)) {
                                disableSyncFinished(runAfterRemovedAccount);
                            }
                        }
                    })) {
                        if (!ListenerUtil.mutListener.listen(41088)) {
                            this.disableSyncFinished(runAfterRemovedAccount);
                        }
                    }
                }
            }
        }
        return true;
    }

    private void disableSyncFinished(Runnable run) {
        if (!ListenerUtil.mutListener.listen(41092)) {
            if (this.preferenceService != null) {
                if (!ListenerUtil.mutListener.listen(41091)) {
                    this.preferenceService.setSyncContacts(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41104)) {
            if (contactService != null) {
                if (!ListenerUtil.mutListener.listen(41093)) {
                    contactService.removeAllThreemaContactIds();
                }
                // cleanup / degrade remaining identities that are still server verified
                List<String> identities = contactService.getIdentitiesByVerificationLevel(VerificationLevel.SERVER_VERIFIED);
                if (!ListenerUtil.mutListener.listen(41103)) {
                    if ((ListenerUtil.mutListener.listen(41099) ? (identities != null || (ListenerUtil.mutListener.listen(41098) ? (identities.size() >= 0) : (ListenerUtil.mutListener.listen(41097) ? (identities.size() <= 0) : (ListenerUtil.mutListener.listen(41096) ? (identities.size() < 0) : (ListenerUtil.mutListener.listen(41095) ? (identities.size() != 0) : (ListenerUtil.mutListener.listen(41094) ? (identities.size() == 0) : (identities.size() > 0))))))) : (identities != null && (ListenerUtil.mutListener.listen(41098) ? (identities.size() >= 0) : (ListenerUtil.mutListener.listen(41097) ? (identities.size() <= 0) : (ListenerUtil.mutListener.listen(41096) ? (identities.size() < 0) : (ListenerUtil.mutListener.listen(41095) ? (identities.size() != 0) : (ListenerUtil.mutListener.listen(41094) ? (identities.size() == 0) : (identities.size() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(41102)) {
                            {
                                long _loopCounter471 = 0;
                                for (ContactModel contactModel : contactService.getByIdentities(identities)) {
                                    ListenerUtil.loopListener.listen("_loopCounter471", ++_loopCounter471);
                                    if (!ListenerUtil.mutListener.listen(41100)) {
                                        contactModel.setVerificationLevel(VerificationLevel.UNVERIFIED);
                                    }
                                    if (!ListenerUtil.mutListener.listen(41101)) {
                                        contactService.save(contactModel);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(41106)) {
            if (run != null) {
                if (!ListenerUtil.mutListener.listen(41105)) {
                    run.run();
                }
            }
        }
    }

    private void finishedRoutine(final SynchronizeContactsRoutine routine) {
        // remove from pending
        synchronized (this.pendingRoutines) {
            if (!ListenerUtil.mutListener.listen(41107)) {
                this.pendingRoutines.remove(routine);
            }
        }
        if (!ListenerUtil.mutListener.listen(41108)) {
            logger.info("Contact sync finished");
        }
        if (!ListenerUtil.mutListener.listen(41110)) {
            // fire on finished
            ListenerManager.synchronizeContactsListeners.handle(new ListenerManager.HandleListener<SynchronizeContactsListener>() {

                @Override
                public void handle(SynchronizeContactsListener listener) {
                    if (!ListenerUtil.mutListener.listen(41109)) {
                        listener.onFinished(routine);
                    }
                }
            });
        }
    }
}
