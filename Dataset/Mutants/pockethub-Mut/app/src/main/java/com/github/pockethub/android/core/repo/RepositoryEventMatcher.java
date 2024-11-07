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
package com.github.pockethub.android.core.repo;

import android.text.TextUtils;
import com.github.pockethub.android.util.ConvertUtils;
import com.meisolsson.githubsdk.model.GitHubEvent;
import com.meisolsson.githubsdk.model.GitHubEventType;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.payload.ForkPayload;
import static com.meisolsson.githubsdk.model.GitHubEventType.CreateEvent;
import static com.meisolsson.githubsdk.model.GitHubEventType.ForkEvent;
import static com.meisolsson.githubsdk.model.GitHubEventType.PublicEvent;
import static com.meisolsson.githubsdk.model.GitHubEventType.WatchEvent;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helper to find a {@link RepositoryEventMatcher} to open for an event
 */
public class RepositoryEventMatcher {

    /**
     * Get {@link Repository} from event
     *
     * @param event
     * @return gist or null if event doesn't apply
     */
    public static Repository getRepository(final GitHubEvent event) {
        if (!ListenerUtil.mutListener.listen(525)) {
            if ((ListenerUtil.mutListener.listen(524) ? (event == null && event.payload() == null) : (event == null || event.payload() == null))) {
                return null;
            }
        }
        GitHubEventType type = event.type();
        if (!ListenerUtil.mutListener.listen(530)) {
            if (ForkEvent.equals(type)) {
                Repository repository = ((ForkPayload) event.payload()).forkee();
                if (!ListenerUtil.mutListener.listen(529)) {
                    // Verify repository has valid name and owner
                    if ((ListenerUtil.mutListener.listen(528) ? ((ListenerUtil.mutListener.listen(527) ? ((ListenerUtil.mutListener.listen(526) ? (repository != null || !TextUtils.isEmpty(repository.name())) : (repository != null && !TextUtils.isEmpty(repository.name()))) || repository.owner() != null) : ((ListenerUtil.mutListener.listen(526) ? (repository != null || !TextUtils.isEmpty(repository.name())) : (repository != null && !TextUtils.isEmpty(repository.name()))) && repository.owner() != null)) || !TextUtils.isEmpty(repository.owner().login())) : ((ListenerUtil.mutListener.listen(527) ? ((ListenerUtil.mutListener.listen(526) ? (repository != null || !TextUtils.isEmpty(repository.name())) : (repository != null && !TextUtils.isEmpty(repository.name()))) || repository.owner() != null) : ((ListenerUtil.mutListener.listen(526) ? (repository != null || !TextUtils.isEmpty(repository.name())) : (repository != null && !TextUtils.isEmpty(repository.name()))) && repository.owner() != null)) && !TextUtils.isEmpty(repository.owner().login())))) {
                        return repository;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(533)) {
            if ((ListenerUtil.mutListener.listen(532) ? ((ListenerUtil.mutListener.listen(531) ? (CreateEvent.equals(type) && WatchEvent.equals(type)) : (CreateEvent.equals(type) || WatchEvent.equals(type))) && PublicEvent.equals(type)) : ((ListenerUtil.mutListener.listen(531) ? (CreateEvent.equals(type) && WatchEvent.equals(type)) : (CreateEvent.equals(type) || WatchEvent.equals(type))) || PublicEvent.equals(type)))) {
                return ConvertUtils.eventRepoToRepo(event.repo());
            }
        }
        return null;
    }
}
