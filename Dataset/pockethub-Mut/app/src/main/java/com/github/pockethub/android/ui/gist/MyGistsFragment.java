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
package com.github.pockethub.android.ui.gist;

import android.accounts.Account;
import android.content.Intent;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Gist;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.service.gists.GistService;
import io.reactivex.Single;
import retrofit2.Response;
import javax.inject.Inject;
import javax.inject.Provider;
import static android.app.Activity.RESULT_OK;
import static com.github.pockethub.android.RequestCodes.GIST_CREATE;
import static com.github.pockethub.android.RequestCodes.GIST_VIEW;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Fragment to display a list of Gists
 */
public class MyGistsFragment extends GistsFragment {

    GistService service = ServiceGenerator.createService(getActivity(), GistService.class);

    @Inject
    protected Provider<Account> accountProvider;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(874)) {
            if ((ListenerUtil.mutListener.listen(872) ? (((ListenerUtil.mutListener.listen(871) ? (requestCode == GIST_CREATE && requestCode == GIST_VIEW) : (requestCode == GIST_CREATE || requestCode == GIST_VIEW))) || RESULT_OK == resultCode) : (((ListenerUtil.mutListener.listen(871) ? (requestCode == GIST_CREATE && requestCode == GIST_VIEW) : (requestCode == GIST_CREATE || requestCode == GIST_VIEW))) && RESULT_OK == resultCode))) {
                if (!ListenerUtil.mutListener.listen(873)) {
                    pagedListFetcher.refresh();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(875)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected Single<Response<Page<Gist>>> loadData(int page) {
        return service.getUserGists(page);
    }
}
