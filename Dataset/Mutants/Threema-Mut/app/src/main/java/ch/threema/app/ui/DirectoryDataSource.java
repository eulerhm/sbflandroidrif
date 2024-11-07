/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2018-2021 Threema GmbH
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
package ch.threema.app.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.Toast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.stores.IdentityStore;
import ch.threema.app.utils.RuntimeUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.client.APIConnector;
import ch.threema.client.work.WorkDirectory;
import ch.threema.client.work.WorkDirectoryCategory;
import ch.threema.client.work.WorkDirectoryContact;
import ch.threema.client.work.WorkDirectoryFilter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DirectoryDataSource extends PageKeyedDataSource<WorkDirectory, WorkDirectoryContact> {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryDataSource.class);

    private PreferenceService preferenceService;

    private APIConnector apiConnector;

    private IdentityStore identityStore;

    private boolean sortByFirstName;

    private static String queryText;

    private static List<WorkDirectoryCategory> queryCategories = new ArrayList<>();

    public DirectoryDataSource() {
        super();
        ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        try {
            if (!ListenerUtil.mutListener.listen(45006)) {
                this.preferenceService = serviceManager.getPreferenceService();
            }
            if (!ListenerUtil.mutListener.listen(45007)) {
                this.apiConnector = serviceManager.getAPIConnector();
            }
            if (!ListenerUtil.mutListener.listen(45008)) {
                this.identityStore = serviceManager.getIdentityStore();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(45005)) {
                logger.error("Exception", e);
            }
        }
        if (!ListenerUtil.mutListener.listen(45009)) {
            this.sortByFirstName = preferenceService.isContactListSortingFirstName();
        }
    }

    public void setQueryText(String query) {
        if (!ListenerUtil.mutListener.listen(45010)) {
            queryText = query;
        }
    }

    public void setQueryCategories(List<WorkDirectoryCategory> categories) {
        if (!ListenerUtil.mutListener.listen(45011)) {
            queryCategories = categories;
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<WorkDirectory> params, @NonNull LoadInitialCallback<WorkDirectory, WorkDirectoryContact> callback) {
        if (!ListenerUtil.mutListener.listen(45012)) {
            logger.debug("*** loadInitial");
        }
        if (!ListenerUtil.mutListener.listen(45014)) {
            if (!TestUtil.empty(queryText)) {
                if (!ListenerUtil.mutListener.listen(45013)) {
                    fetchInitialData(callback);
                }
            }
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<WorkDirectory> params, @NonNull LoadCallback<WorkDirectory, WorkDirectoryContact> callback) {
        if (!ListenerUtil.mutListener.listen(45015)) {
            fetchData(params.key.previousFilter, callback);
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<WorkDirectory> params, @NonNull LoadCallback<WorkDirectory, WorkDirectoryContact> callback) {
        if (!ListenerUtil.mutListener.listen(45016)) {
            logger.debug("*** loadAfter: " + params.key.nextFilter.getPage());
        }
        if (!ListenerUtil.mutListener.listen(45017)) {
            fetchData(params.key.nextFilter, callback);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchData(final WorkDirectoryFilter workDirectoryFilter, final LoadCallback<WorkDirectory, WorkDirectoryContact> callback) {
        if (!ListenerUtil.mutListener.listen(45018)) {
            if (workDirectoryFilter == null) {
                // no more data
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(45024)) {
            new AsyncTask<Void, Void, WorkDirectory>() {

                @Override
                protected WorkDirectory doInBackground(Void... voids) {
                    WorkDirectory workDirectory;
                    try {
                        workDirectory = apiConnector.fetchWorkDirectory(preferenceService.getLicenseUsername(), preferenceService.getLicensePassword(), identityStore, workDirectoryFilter);
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(45019)) {
                            logger.error("Exception", e);
                        }
                        if (!ListenerUtil.mutListener.listen(45021)) {
                            RuntimeUtil.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(45020)) {
                                        Toast.makeText(ThreemaApplication.getAppContext(), "Unable to fetch directory: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        return null;
                    }
                    return workDirectory;
                }

                @Override
                protected void onPostExecute(WorkDirectory workDirectory) {
                    if (!ListenerUtil.mutListener.listen(45023)) {
                        if (workDirectory != null) {
                            if (!ListenerUtil.mutListener.listen(45022)) {
                                callback.onResult(workDirectory.workContacts, workDirectory);
                            }
                        }
                    }
                }
            }.execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchInitialData(final LoadInitialCallback<WorkDirectory, WorkDirectoryContact> callback) {
        if (!ListenerUtil.mutListener.listen(45035)) {
            new AsyncTask<Void, Void, WorkDirectory>() {

                @Override
                protected WorkDirectory doInBackground(Void... voids) {
                    WorkDirectory workDirectory;
                    WorkDirectoryFilter workDirectoryFilter = new WorkDirectoryFilter();
                    if (!ListenerUtil.mutListener.listen(45026)) {
                        {
                            long _loopCounter529 = 0;
                            for (WorkDirectoryCategory queryCategory : queryCategories) {
                                ListenerUtil.loopListener.listen("_loopCounter529", ++_loopCounter529);
                                if (!ListenerUtil.mutListener.listen(45025)) {
                                    workDirectoryFilter.addCategory(queryCategory);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(45027)) {
                        workDirectoryFilter.page(0);
                    }
                    if (!ListenerUtil.mutListener.listen(45028)) {
                        workDirectoryFilter.sortBy(sortByFirstName ? WorkDirectoryFilter.SORT_BY_FIRST_NAME : WorkDirectoryFilter.SORT_BY_LAST_NAME, true);
                    }
                    if (!ListenerUtil.mutListener.listen(45029)) {
                        workDirectoryFilter.query(queryText);
                    }
                    try {
                        workDirectory = apiConnector.fetchWorkDirectory(preferenceService.getLicenseUsername(), preferenceService.getLicensePassword(), identityStore, workDirectoryFilter);
                    } catch (Exception e) {
                        if (!ListenerUtil.mutListener.listen(45030)) {
                            logger.error("Exception", e);
                        }
                        if (!ListenerUtil.mutListener.listen(45032)) {
                            RuntimeUtil.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(45031)) {
                                        Toast.makeText(ThreemaApplication.getAppContext(), "Unable to fetch directory: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        return null;
                    }
                    return workDirectory;
                }

                @Override
                protected void onPostExecute(WorkDirectory workDirectory) {
                    if (!ListenerUtil.mutListener.listen(45034)) {
                        if (workDirectory != null) {
                            if (!ListenerUtil.mutListener.listen(45033)) {
                                callback.onResult(workDirectory.workContacts, workDirectory, workDirectory);
                            }
                        }
                    }
                }
            }.execute();
        }
    }
}
