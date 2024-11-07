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
package com.github.pockethub.android.ui.user;

import android.os.Bundle;
import com.github.pockethub.android.core.user.UserEventMatcher.UserPair;
import com.github.pockethub.android.ui.NewsFragment;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import static com.github.pockethub.android.Intents.EXTRA_USER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Fragment to display a news feed for a given user/org
 */
public abstract class UserNewsFragment extends NewsFragment implements OrganizationSelectionListener {

    /**
     * Current organization/user
     */
    protected User org;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1178)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(1180)) {
            if (org != null) {
                if (!ListenerUtil.mutListener.listen(1179)) {
                    outState.putParcelable(EXTRA_USER, org);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1182)) {
            if (getActivity() instanceof OrganizationSelectionProvider) {
                if (!ListenerUtil.mutListener.listen(1181)) {
                    org = ((OrganizationSelectionProvider) getActivity()).addListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1185)) {
            if ((ListenerUtil.mutListener.listen(1183) ? (getArguments() != null || getArguments().containsKey("org")) : (getArguments() != null && getArguments().containsKey("org")))) {
                if (!ListenerUtil.mutListener.listen(1184)) {
                    org = getArguments().getParcelable("org");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1188)) {
            if ((ListenerUtil.mutListener.listen(1186) ? (org == null || savedInstanceState != null) : (org == null && savedInstanceState != null))) {
                if (!ListenerUtil.mutListener.listen(1187)) {
                    org = (User) savedInstanceState.get(EXTRA_USER);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1189)) {
            super.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onDetach() {
        if (!ListenerUtil.mutListener.listen(1192)) {
            if ((ListenerUtil.mutListener.listen(1190) ? (getActivity() != null || getActivity() instanceof OrganizationSelectionProvider) : (getActivity() != null && getActivity() instanceof OrganizationSelectionProvider))) {
                OrganizationSelectionProvider selectionProvider = (OrganizationSelectionProvider) getActivity();
                if (!ListenerUtil.mutListener.listen(1191)) {
                    selectionProvider.removeListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1193)) {
            super.onDetach();
        }
    }

    @Override
    protected void viewRepository(Repository repository) {
        User owner = repository.owner();
        if (!ListenerUtil.mutListener.listen(1196)) {
            if ((ListenerUtil.mutListener.listen(1194) ? (owner != null || org.login().equals(owner.login())) : (owner != null && org.login().equals(owner.login())))) {
                if (!ListenerUtil.mutListener.listen(1195)) {
                    repository = repository.toBuilder().owner(org).build();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1197)) {
            super.viewRepository(repository);
        }
    }

    @Override
    public void onOrganizationSelected(User organization) {
        int previousOrgId = org != null ? org.id().intValue() : -1;
        if (!ListenerUtil.mutListener.listen(1198)) {
            org = organization;
        }
        if (!ListenerUtil.mutListener.listen(1200)) {
            // Only hard refresh if view already created and org is changing
            if (previousOrgId != org.id()) {
                if (!ListenerUtil.mutListener.listen(1199)) {
                    pagedListFetcher.refresh();
                }
            }
        }
    }

    @Override
    protected boolean viewUser(User user) {
        if (!ListenerUtil.mutListener.listen(1202)) {
            if (org.id() != user.id()) {
                if (!ListenerUtil.mutListener.listen(1201)) {
                    startActivity(UserViewActivity.Companion.createIntent(user));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void viewUser(UserPair users) {
        if (!ListenerUtil.mutListener.listen(1204)) {
            if (!viewUser(users.from)) {
                if (!ListenerUtil.mutListener.listen(1203)) {
                    viewUser(users.to);
                }
            }
        }
    }
}
