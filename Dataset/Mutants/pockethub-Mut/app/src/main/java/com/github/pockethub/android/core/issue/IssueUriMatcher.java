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
package com.github.pockethub.android.core.issue;

import android.net.Uri;
import android.text.TextUtils;
import com.github.pockethub.android.core.repo.RepositoryUtils;
import com.github.pockethub.android.util.InfoUtils;
import com.meisolsson.githubsdk.model.Issue;
import com.meisolsson.githubsdk.model.Repository;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Parses a {@link Issue} from a {@link Uri}
 */
public class IssueUriMatcher {

    /**
     * Parse a {@link Issue} from a non-null {@link Uri}
     *
     * @param uri
     * @return {@link Issue} or null if none found in given
     *         {@link Uri}
     */
    public static Issue getIssue(Uri uri) {
        List<String> segments = uri.getPathSegments();
        if (!ListenerUtil.mutListener.listen(495)) {
            if ((ListenerUtil.mutListener.listen(494) ? (segments.size() >= 4) : (ListenerUtil.mutListener.listen(493) ? (segments.size() <= 4) : (ListenerUtil.mutListener.listen(492) ? (segments.size() > 4) : (ListenerUtil.mutListener.listen(491) ? (segments.size() != 4) : (ListenerUtil.mutListener.listen(490) ? (segments.size() == 4) : (segments.size() < 4))))))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(497)) {
            if ((ListenerUtil.mutListener.listen(496) ? (!"issues".equals(segments.get(2)) || !"pull".equals(segments.get(2))) : (!"issues".equals(segments.get(2)) && !"pull".equals(segments.get(2))))) {
                return null;
            }
        }
        String repoOwner = segments.get(0);
        if (!ListenerUtil.mutListener.listen(498)) {
            if (!RepositoryUtils.isValidOwner(repoOwner)) {
                return null;
            }
        }
        String repoName = segments.get(1);
        if (!ListenerUtil.mutListener.listen(499)) {
            if (!RepositoryUtils.isValidRepo(repoName)) {
                return null;
            }
        }
        String number = segments.get(3);
        if (!ListenerUtil.mutListener.listen(500)) {
            if (TextUtils.isEmpty(number)) {
                return null;
            }
        }
        int issueNumber;
        try {
            issueNumber = Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return null;
        }
        if (!ListenerUtil.mutListener.listen(506)) {
            if ((ListenerUtil.mutListener.listen(505) ? (issueNumber >= 1) : (ListenerUtil.mutListener.listen(504) ? (issueNumber <= 1) : (ListenerUtil.mutListener.listen(503) ? (issueNumber > 1) : (ListenerUtil.mutListener.listen(502) ? (issueNumber != 1) : (ListenerUtil.mutListener.listen(501) ? (issueNumber == 1) : (issueNumber < 1))))))) {
                return null;
            }
        }
        Repository repo = InfoUtils.createRepoFromData(repoOwner, repoName);
        return Issue.builder().repository(repo).number(issueNumber).build();
    }

    public static Issue getApiIssue(String url) {
        if (!ListenerUtil.mutListener.listen(507)) {
            url = url.replace("https://api.github.com/repos", "https://github.com/");
        }
        if (!ListenerUtil.mutListener.listen(508)) {
            url = url.replaceFirst("/pulls/(\\d+)$", "/pull/$1");
        }
        Uri uri = Uri.parse(url);
        return getIssue(uri);
    }
}
