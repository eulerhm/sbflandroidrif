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
package com.github.pockethub.android.core.commit;

import android.net.Uri;
import com.github.pockethub.android.core.repo.RepositoryUtils;
import com.github.pockethub.android.util.InfoUtils;
import com.meisolsson.githubsdk.model.Repository;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Parses a {@link CommitMatch} from a {@link Uri}
 */
public class CommitUriMatcher {

    /**
     * Attempt to parse a {@link CommitMatch} from the given {@link Uri}
     *
     * @param uri
     * @return {@link CommitMatch} or null if unparseable
     */
    public static CommitMatch getCommit(Uri uri) {
        List<String> segments = uri.getPathSegments();
        if (!ListenerUtil.mutListener.listen(285)) {
            if ((ListenerUtil.mutListener.listen(284) ? (segments.size() >= 4) : (ListenerUtil.mutListener.listen(283) ? (segments.size() <= 4) : (ListenerUtil.mutListener.listen(282) ? (segments.size() > 4) : (ListenerUtil.mutListener.listen(281) ? (segments.size() != 4) : (ListenerUtil.mutListener.listen(280) ? (segments.size() == 4) : (segments.size() < 4))))))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(286)) {
            if (!"commit".equals(segments.get(2))) {
                return null;
            }
        }
        String repoOwner = segments.get(0);
        if (!ListenerUtil.mutListener.listen(287)) {
            if (!RepositoryUtils.isValidOwner(repoOwner)) {
                return null;
            }
        }
        String repoName = segments.get(1);
        if (!ListenerUtil.mutListener.listen(288)) {
            if (!RepositoryUtils.isValidRepo(repoName)) {
                return null;
            }
        }
        String commit = segments.get(3);
        if (!ListenerUtil.mutListener.listen(289)) {
            if (!CommitUtils.isValidCommit(commit)) {
                return null;
            }
        }
        Repository repository = InfoUtils.createRepoFromData(repoOwner, repoName);
        return new CommitMatch(repository, commit);
    }
}
