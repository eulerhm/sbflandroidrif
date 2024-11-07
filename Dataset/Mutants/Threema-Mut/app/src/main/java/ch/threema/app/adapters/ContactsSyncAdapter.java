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
package ch.threema.app.adapters;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.exceptions.FileSystemNotPresentException;
import ch.threema.app.listeners.NewSyncedContactsListener;
import ch.threema.app.managers.ListenerManager;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.routines.SynchronizeContactsRoutine;
import ch.threema.app.services.SynchronizeContactsService;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.localcrypto.MasterKeyLockedException;
import ch.threema.storage.models.ContactModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ContactsSyncAdapter extends AbstractThreadedSyncAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ContactsSyncAdapter.class);

    private Context context;

    public ContactsSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        if (!ListenerUtil.mutListener.listen(8918)) {
            this.context = context;
        }
    }

    public ContactsSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        if (!ListenerUtil.mutListener.listen(8919)) {
            this.context = context;
        }
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        if (!ListenerUtil.mutListener.listen(8920)) {
            logger.info("onPerformSync");
        }
        try {
            ServiceManager serviceManager = ThreemaApplication.getServiceManager();
            if (!ListenerUtil.mutListener.listen(8924)) {
                if (serviceManager == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(8943)) {
                if (serviceManager.getPreferenceService().isSyncContacts()) {
                    if (!ListenerUtil.mutListener.listen(8925)) {
                        logger.info("Start sync adapter run");
                    }
                    SynchronizeContactsService synchronizeContactsService = serviceManager.getSynchronizeContactsService();
                    if (!ListenerUtil.mutListener.listen(8926)) {
                        if (synchronizeContactsService == null) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8932)) {
                        if (synchronizeContactsService.isFullSyncInProgress()) {
                            if (!ListenerUtil.mutListener.listen(8927)) {
                                logger.info("A full sync is already running");
                            }
                            if (!ListenerUtil.mutListener.listen(8928)) {
                                syncResult.stats.numUpdates = 0;
                            }
                            if (!ListenerUtil.mutListener.listen(8929)) {
                                syncResult.stats.numInserts = 0;
                            }
                            if (!ListenerUtil.mutListener.listen(8930)) {
                                syncResult.stats.numDeletes = 0;
                            }
                            if (!ListenerUtil.mutListener.listen(8931)) {
                                syncResult.stats.numEntries = 0;
                            }
                            return;
                        }
                    }
                    SynchronizeContactsRoutine routine = synchronizeContactsService.instantiateSynchronization(account);
                    if (!ListenerUtil.mutListener.listen(8941)) {
                        routine.addOnFinished(new SynchronizeContactsRoutine.OnFinished() {

                            @Override
                            public void finished(boolean success, long modifiedAccounts, List<ContactModel> createdContacts, long deletedAccounts) {
                                if (!ListenerUtil.mutListener.listen(8934)) {
                                    // let user know that contact was added
                                    ListenerManager.newSyncedContactListener.handle(new ListenerManager.HandleListener<NewSyncedContactsListener>() {

                                        @Override
                                        public void handle(NewSyncedContactsListener listener) {
                                            if (!ListenerUtil.mutListener.listen(8933)) {
                                                listener.onNew(createdContacts);
                                            }
                                        }
                                    });
                                }
                                if (!ListenerUtil.mutListener.listen(8935)) {
                                    // modifiedAccounts;
                                    syncResult.stats.numUpdates = 0;
                                }
                                if (!ListenerUtil.mutListener.listen(8936)) {
                                    // createdAccounts;
                                    syncResult.stats.numInserts = 0;
                                }
                                if (!ListenerUtil.mutListener.listen(8937)) {
                                    // deletedAccounts;
                                    syncResult.stats.numDeletes = 0;
                                }
                                if (!ListenerUtil.mutListener.listen(8938)) {
                                    // createdAccounts;
                                    syncResult.stats.numEntries = 0;
                                }
                                if (!ListenerUtil.mutListener.listen(8939)) {
                                    serviceManager.getPreferenceService().setLastSyncadapterRun(System.currentTimeMillis());
                                }
                                if (!ListenerUtil.mutListener.listen(8940)) {
                                    // send a broadcast to let others know that the list has changed
                                    LocalBroadcastManager.getInstance(ThreemaApplication.getAppContext()).sendBroadcast(IntentDataUtil.createActionIntentContactsChanged());
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(8942)) {
                        // not in a thread!
                        routine.run();
                    }
                }
            }
        } catch (FileSystemNotPresentException e) {
            if (!ListenerUtil.mutListener.listen(8921)) {
                logger.error("Exception", e);
            }
        } catch (MasterKeyLockedException e) {
            if (!ListenerUtil.mutListener.listen(8922)) {
                logger.debug("MasterKeyLockedException [" + e.getMessage() + "]");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(8923)) {
                logger.debug("sync finished Sync [numEntries=" + String.valueOf(syncResult.stats.numEntries) + ", updates=" + String.valueOf(syncResult.stats.numUpdates) + ", inserts=" + String.valueOf(syncResult.stats.numInserts) + ", deletes=" + String.valueOf(syncResult.stats.numDeletes) + "]");
            }
        }
    }

    @Override
    public void onSyncCanceled() {
        if (!ListenerUtil.mutListener.listen(8944)) {
            logger.info("onSyncCanceled");
        }
        if (!ListenerUtil.mutListener.listen(8945)) {
            super.onSyncCanceled();
        }
    }

    @Override
    public void onSyncCanceled(Thread thread) {
        if (!ListenerUtil.mutListener.listen(8946)) {
            logger.info("onSyncCanceled");
        }
        if (!ListenerUtil.mutListener.listen(8947)) {
            super.onSyncCanceled(thread);
        }
    }
}
