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
package com.github.pockethub.android.core.user;

import android.net.Uri;
import com.github.pockethub.android.core.repo.RepositoryUtils;
import com.meisolsson.githubsdk.model.User;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Parses a {@link User} from a {@link Uri}
 */
public class UserUriMatcher {

    /**
     * Attempt to parse a {@link User} from the given {@link Uri}
     *
     * @param uri
     * @return {@link User} or null if unparseable
     */
    public static User getUser(Uri uri) {
        List<String> segments = uri.getPathSegments();
        if (!ListenerUtil.mutListener.listen(554)) {
            if ((ListenerUtil.mutListener.listen(553) ? (segments.size() >= 1) : (ListenerUtil.mutListener.listen(552) ? (segments.size() <= 1) : (ListenerUtil.mutListener.listen(551) ? (segments.size() > 1) : (ListenerUtil.mutListener.listen(550) ? (segments.size() != 1) : (ListenerUtil.mutListener.listen(549) ? (segments.size() == 1) : (segments.size() < 1))))))) {
                return null;
            }
        }
        String login = segments.get(0);
        if (!ListenerUtil.mutListener.listen(555)) {
            if (!RepositoryUtils.isValidOwner(login)) {
                return null;
            }
        }
        return User.builder().login(login).build();
    }
}
