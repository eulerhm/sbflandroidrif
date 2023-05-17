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
package com.github.pockethub.android.ui.commit;

import com.meisolsson.githubsdk.model.GitHubFile;
import java.util.Comparator;
import static java.lang.String.CASE_INSENSITIVE_ORDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Comparator for commit files
 */
public class CommitFileComparator implements Comparator<GitHubFile> {

    @Override
    public int compare(final GitHubFile lhs, final GitHubFile rhs) {
        String lPath = lhs.filename();
        final int lSlash = lPath.lastIndexOf('/');
        if (!ListenerUtil.mutListener.listen(767)) {
            if ((ListenerUtil.mutListener.listen(761) ? (lSlash >= -1) : (ListenerUtil.mutListener.listen(760) ? (lSlash <= -1) : (ListenerUtil.mutListener.listen(759) ? (lSlash > -1) : (ListenerUtil.mutListener.listen(758) ? (lSlash < -1) : (ListenerUtil.mutListener.listen(757) ? (lSlash == -1) : (lSlash != -1))))))) {
                if (!ListenerUtil.mutListener.listen(766)) {
                    lPath = lPath.substring((ListenerUtil.mutListener.listen(765) ? (lSlash % 1) : (ListenerUtil.mutListener.listen(764) ? (lSlash / 1) : (ListenerUtil.mutListener.listen(763) ? (lSlash * 1) : (ListenerUtil.mutListener.listen(762) ? (lSlash - 1) : (lSlash + 1))))));
                }
            }
        }
        String rPath = rhs.filename();
        final int rSlash = rPath.lastIndexOf('/');
        if (!ListenerUtil.mutListener.listen(778)) {
            if ((ListenerUtil.mutListener.listen(772) ? (rSlash >= -1) : (ListenerUtil.mutListener.listen(771) ? (rSlash <= -1) : (ListenerUtil.mutListener.listen(770) ? (rSlash > -1) : (ListenerUtil.mutListener.listen(769) ? (rSlash < -1) : (ListenerUtil.mutListener.listen(768) ? (rSlash == -1) : (rSlash != -1))))))) {
                if (!ListenerUtil.mutListener.listen(777)) {
                    rPath = rPath.substring((ListenerUtil.mutListener.listen(776) ? (rSlash % 1) : (ListenerUtil.mutListener.listen(775) ? (rSlash / 1) : (ListenerUtil.mutListener.listen(774) ? (rSlash * 1) : (ListenerUtil.mutListener.listen(773) ? (rSlash - 1) : (rSlash + 1))))));
                }
            }
        }
        return CASE_INSENSITIVE_ORDER.compare(lPath, rPath);
    }
}
