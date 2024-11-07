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
package com.github.pockethub.android.util;

import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class InfoUtils {

    public static Repository createRepoFromUrl(String url) {
        if ((ListenerUtil.mutListener.listen(1806) ? (url == null && (ListenerUtil.mutListener.listen(1805) ? (url.length() >= 0) : (ListenerUtil.mutListener.listen(1804) ? (url.length() <= 0) : (ListenerUtil.mutListener.listen(1803) ? (url.length() > 0) : (ListenerUtil.mutListener.listen(1802) ? (url.length() < 0) : (ListenerUtil.mutListener.listen(1801) ? (url.length() != 0) : (url.length() == 0))))))) : (url == null || (ListenerUtil.mutListener.listen(1805) ? (url.length() >= 0) : (ListenerUtil.mutListener.listen(1804) ? (url.length() <= 0) : (ListenerUtil.mutListener.listen(1803) ? (url.length() > 0) : (ListenerUtil.mutListener.listen(1802) ? (url.length() < 0) : (ListenerUtil.mutListener.listen(1801) ? (url.length() != 0) : (url.length() == 0))))))))) {
            return null;
        }
        String owner = null;
        String name = null;
        if (!ListenerUtil.mutListener.listen(1816)) {
            {
                long _loopCounter49 = 0;
                for (// $NON-NLS-1$
                String segment : // $NON-NLS-1$
                url.split("/")) {
                    ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                    if (!ListenerUtil.mutListener.listen(1815)) {
                        if ((ListenerUtil.mutListener.listen(1811) ? (segment.length() >= 0) : (ListenerUtil.mutListener.listen(1810) ? (segment.length() <= 0) : (ListenerUtil.mutListener.listen(1809) ? (segment.length() < 0) : (ListenerUtil.mutListener.listen(1808) ? (segment.length() != 0) : (ListenerUtil.mutListener.listen(1807) ? (segment.length() == 0) : (segment.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(1814)) {
                                if (owner == null) {
                                    if (!ListenerUtil.mutListener.listen(1813)) {
                                        owner = segment;
                                    }
                                } else if (name == null) {
                                    if (!ListenerUtil.mutListener.listen(1812)) {
                                        name = segment;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((ListenerUtil.mutListener.listen(1829) ? ((ListenerUtil.mutListener.listen(1823) ? ((ListenerUtil.mutListener.listen(1822) ? (owner != null || (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0)))))))) || name != null) : ((ListenerUtil.mutListener.listen(1822) ? (owner != null || (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0)))))))) && name != null)) || (ListenerUtil.mutListener.listen(1828) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(1827) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(1826) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(1825) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(1824) ? (name.length() == 0) : (name.length() > 0))))))) : ((ListenerUtil.mutListener.listen(1823) ? ((ListenerUtil.mutListener.listen(1822) ? (owner != null || (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0)))))))) || name != null) : ((ListenerUtil.mutListener.listen(1822) ? (owner != null || (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0))))))) : (owner != null && (ListenerUtil.mutListener.listen(1821) ? (owner.length() >= 0) : (ListenerUtil.mutListener.listen(1820) ? (owner.length() <= 0) : (ListenerUtil.mutListener.listen(1819) ? (owner.length() < 0) : (ListenerUtil.mutListener.listen(1818) ? (owner.length() != 0) : (ListenerUtil.mutListener.listen(1817) ? (owner.length() == 0) : (owner.length() > 0)))))))) && name != null)) && (ListenerUtil.mutListener.listen(1828) ? (name.length() >= 0) : (ListenerUtil.mutListener.listen(1827) ? (name.length() <= 0) : (ListenerUtil.mutListener.listen(1826) ? (name.length() < 0) : (ListenerUtil.mutListener.listen(1825) ? (name.length() != 0) : (ListenerUtil.mutListener.listen(1824) ? (name.length() == 0) : (name.length() > 0))))))))) {
            return createRepoFromData(owner, name);
        } else {
            return null;
        }
    }

    public static String createRepoId(Repository repo) {
        if (repo.name().contains("/")) {
            return repo.name();
        } else {
            return createRepoId(repo.owner().login(), repo.name());
        }
    }

    public static String createRepoId(String owner, String name) {
        return owner + "/" + name;
    }

    public static Repository createRepoFromData(String repoOwner, String repoName) {
        User user = User.builder().login(repoOwner).build();
        return Repository.builder().owner(user).name(repoName).build();
    }
}
