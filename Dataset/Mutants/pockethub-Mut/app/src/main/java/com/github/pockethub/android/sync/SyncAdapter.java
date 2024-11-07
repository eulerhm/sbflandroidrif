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

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import javax.inject.Inject;
import javax.inject.Singleton;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Sync adapter
 */
@Singleton
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    @Inject
    protected SyncCampaignFactory campaignFactory;

    private SyncCampaign campaign = null;

    /**
     * Create sync adapter for context
     *
     * @param context
     */
    @Inject
    public SyncAdapter(final Context context) {
        super(context, true);
    }

    @Override
    public void onPerformSync(final Account account, final Bundle extras, final String authority, final ContentProviderClient provider, final SyncResult syncResult) {
        if (!ListenerUtil.mutListener.listen(656)) {
            cancelCampaign();
        }
        if (!ListenerUtil.mutListener.listen(657)) {
            campaign = campaignFactory.create(syncResult);
        }
        if (!ListenerUtil.mutListener.listen(658)) {
            campaign.run();
        }
    }

    @Override
    public void onSyncCanceled() {
        if (!ListenerUtil.mutListener.listen(659)) {
            cancelCampaign();
        }
    }

    private void cancelCampaign() {
        if (!ListenerUtil.mutListener.listen(661)) {
            if (campaign != null) {
                if (!ListenerUtil.mutListener.listen(660)) {
                    campaign.cancel();
                }
            }
        }
    }
}
