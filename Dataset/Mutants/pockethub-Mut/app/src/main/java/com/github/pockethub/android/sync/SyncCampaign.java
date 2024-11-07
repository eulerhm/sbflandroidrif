/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.sync;

import android.content.SyncResult;
import android.database.SQLException;
import android.util.Log;
import com.github.pockethub.android.persistence.DatabaseCache;
import com.github.pockethub.android.persistence.OrganizationRepositoriesFactory;
import com.github.pockethub.android.persistence.Organizations;
import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;
import com.meisolsson.githubsdk.model.User;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A cancelable sync operation to synchronize data for a given account
 */
@AutoFactory
public class SyncCampaign implements Runnable {

    private static final String TAG = "SyncCampaign";

    protected DatabaseCache cache;

    protected OrganizationRepositoriesFactory repos;

    protected Organizations persistedOrgs;

    private final SyncResult syncResult;

    private boolean cancelled = false;

    /**
     * Create campaign for result
     *
     * @param syncResult
     */
    public SyncCampaign(@Provided DatabaseCache cache, @Provided OrganizationRepositoriesFactory repos, @Provided Organizations persistedOrgs, SyncResult syncResult) {
        if (!ListenerUtil.mutListener.listen(662)) {
            this.cache = cache;
        }
        if (!ListenerUtil.mutListener.listen(663)) {
            this.repos = repos;
        }
        if (!ListenerUtil.mutListener.listen(664)) {
            this.persistedOrgs = persistedOrgs;
        }
        this.syncResult = syncResult;
    }

    @Override
    public void run() {
        List<User> orgs;
        try {
            orgs = cache.requestAndStore(persistedOrgs);
            if (!ListenerUtil.mutListener.listen(667)) {
                syncResult.stats.numUpdates++;
            }
        } catch (SQLException e) {
            if (!ListenerUtil.mutListener.listen(665)) {
                syncResult.stats.numIoExceptions++;
            }
            if (!ListenerUtil.mutListener.listen(666)) {
                Log.d(TAG, "Exception requesting users and orgs", e);
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(668)) {
            Log.d(TAG, "Syncing " + orgs.size() + " users and orgs");
        }
        if (!ListenerUtil.mutListener.listen(675)) {
            {
                long _loopCounter19 = 0;
                for (User org : orgs) {
                    ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                    if (!ListenerUtil.mutListener.listen(669)) {
                        if (cancelled) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(670)) {
                        Log.d(TAG, "Syncing repos for " + org.login());
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(673)) {
                            cache.requestAndStore(repos.create(org));
                        }
                        if (!ListenerUtil.mutListener.listen(674)) {
                            syncResult.stats.numUpdates++;
                        }
                    } catch (SQLException e) {
                        if (!ListenerUtil.mutListener.listen(671)) {
                            syncResult.stats.numIoExceptions++;
                        }
                        if (!ListenerUtil.mutListener.listen(672)) {
                            Log.d(TAG, "Exception requesting repositories", e);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(676)) {
            Log.d(TAG, "Sync campaign finished");
        }
    }

    /**
     * Cancel campaign
     */
    public void cancel() {
        if (!ListenerUtil.mutListener.listen(677)) {
            cancelled = true;
        }
        if (!ListenerUtil.mutListener.listen(678)) {
            Log.d(TAG, "Cancelled");
        }
    }
}
