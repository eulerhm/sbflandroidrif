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

import android.net.Uri;
import com.github.pockethub.android.util.InfoUtils;
import com.meisolsson.githubsdk.model.Repository;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Parses a {@link Repository} from a {@link Uri}
 */
public class RepositoryUriMatcher {

    /**
     * Attempt to parse a {@link Repository} from the given {@link Uri}
     *
     * @param uri
     * @return {@link Repository} or null if unparseable
     */
    public static Repository getRepository(Uri uri) {
        List<String> segments = uri.getPathSegments();
        if (!ListenerUtil.mutListener.listen(539)) {
            if ((ListenerUtil.mutListener.listen(538) ? (segments.size() >= 2) : (ListenerUtil.mutListener.listen(537) ? (segments.size() <= 2) : (ListenerUtil.mutListener.listen(536) ? (segments.size() > 2) : (ListenerUtil.mutListener.listen(535) ? (segments.size() != 2) : (ListenerUtil.mutListener.listen(534) ? (segments.size() == 2) : (segments.size() < 2))))))) {
                return null;
            }
        }
        String repoOwner = segments.get(0);
        if (!ListenerUtil.mutListener.listen(540)) {
            if (!RepositoryUtils.isValidOwner(repoOwner)) {
                return null;
            }
        }
        String repoName = segments.get(1);
        if (!ListenerUtil.mutListener.listen(541)) {
            if (!RepositoryUtils.isValidRepo(repoName)) {
                return null;
            }
        }
        return InfoUtils.createRepoFromData(repoOwner, repoName);
    }
}
