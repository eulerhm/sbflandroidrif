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

import android.text.TextUtils;
import com.meisolsson.githubsdk.model.Commit;
import com.meisolsson.githubsdk.model.GitHubFile;
import com.meisolsson.githubsdk.model.git.GitComment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Commit model with comments
 */
public class FullCommit extends ArrayList<GitComment> implements Serializable {

    private static final long serialVersionUID = 2470370479577730822L;

    private final Commit commit;

    private final List<FullCommitFile> files;

    /**
     * Create commit with no comments
     *
     * @param commit
     */
    public FullCommit(final Commit commit) {
        this.commit = commit;
        List<GitHubFile> rawFiles = commit.files();
        if ((ListenerUtil.mutListener.listen(290) ? (rawFiles != null || !rawFiles.isEmpty()) : (rawFiles != null && !rawFiles.isEmpty()))) {
            files = new ArrayList<>(rawFiles.size());
            if (!ListenerUtil.mutListener.listen(292)) {
                {
                    long _loopCounter4 = 0;
                    for (GitHubFile file : rawFiles) {
                        ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                        if (!ListenerUtil.mutListener.listen(291)) {
                            files.add(new FullCommitFile(file));
                        }
                    }
                }
            }
        } else {
            files = Collections.emptyList();
        }
    }

    /**
     * Create commit with comments
     *
     * @param commit
     * @param comments
     */
    public FullCommit(final Commit commit, final Collection<GitComment> comments) {
        this.commit = commit;
        List<GitHubFile> rawFiles = commit.files();
        boolean hasComments = (ListenerUtil.mutListener.listen(293) ? (comments != null || !comments.isEmpty()) : (comments != null && !comments.isEmpty()));
        boolean hasFiles = (ListenerUtil.mutListener.listen(294) ? (rawFiles != null || !rawFiles.isEmpty()) : (rawFiles != null && !rawFiles.isEmpty()));
        if (hasFiles) {
            files = new ArrayList<>(rawFiles.size());
            if (!ListenerUtil.mutListener.listen(304)) {
                if (hasComments) {
                    if (!ListenerUtil.mutListener.listen(302)) {
                        {
                            long _loopCounter7 = 0;
                            for (GitHubFile file : rawFiles) {
                                ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                                Iterator<GitComment> iterator = comments.iterator();
                                FullCommitFile full = new FullCommitFile(file);
                                if (!ListenerUtil.mutListener.listen(300)) {
                                    {
                                        long _loopCounter6 = 0;
                                        while (iterator.hasNext()) {
                                            ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                                            GitComment comment = iterator.next();
                                            if (!ListenerUtil.mutListener.listen(299)) {
                                                if (file.filename().equals(comment.path())) {
                                                    if (!ListenerUtil.mutListener.listen(297)) {
                                                        full.add(comment);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(298)) {
                                                        iterator.remove();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(301)) {
                                    files.add(full);
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(303)) {
                        hasComments = !comments.isEmpty();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(296)) {
                        {
                            long _loopCounter5 = 0;
                            for (GitHubFile file : rawFiles) {
                                ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                                if (!ListenerUtil.mutListener.listen(295)) {
                                    files.add(new FullCommitFile(file));
                                }
                            }
                        }
                    }
                }
            }
        } else {
            files = Collections.emptyList();
        }
        if (!ListenerUtil.mutListener.listen(306)) {
            if (hasComments) {
                if (!ListenerUtil.mutListener.listen(305)) {
                    addAll(comments);
                }
            }
        }
    }

    @Override
    public boolean add(final GitComment comment) {
        String path = comment.path();
        if (TextUtils.isEmpty(path)) {
            return super.add(comment);
        } else {
            boolean added = false;
            if (!ListenerUtil.mutListener.listen(310)) {
                {
                    long _loopCounter8 = 0;
                    for (FullCommitFile file : files) {
                        ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                        if (!ListenerUtil.mutListener.listen(309)) {
                            if (path.equals(file.getFile().filename())) {
                                if (!ListenerUtil.mutListener.listen(307)) {
                                    file.add(comment);
                                }
                                if (!ListenerUtil.mutListener.listen(308)) {
                                    added = true;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(312)) {
                if (!added) {
                    if (!ListenerUtil.mutListener.listen(311)) {
                        added = super.add(comment);
                    }
                }
            }
            return added;
        }
    }

    /**
     * @return files
     */
    public List<FullCommitFile> getFiles() {
        return files;
    }

    /**
     * @return commit
     */
    public Commit getCommit() {
        return commit;
    }
}
