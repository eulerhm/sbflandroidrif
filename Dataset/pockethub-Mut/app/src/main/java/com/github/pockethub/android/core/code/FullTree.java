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
package com.github.pockethub.android.core.code;

import android.text.TextUtils;
import com.github.pockethub.android.core.commit.CommitUtils;
import com.github.pockethub.android.core.ref.RefUtils;
import com.meisolsson.githubsdk.model.git.GitEntryType;
import com.meisolsson.githubsdk.model.git.GitReference;
import com.meisolsson.githubsdk.model.git.GitTree;
import com.meisolsson.githubsdk.model.git.GitTreeEntry;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static java.lang.String.CASE_INSENSITIVE_ORDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * {@link GitTree} with additional information
 */
public class FullTree {

    /**
     * Entry in a tree
     */
    public static class Entry implements Comparable<Entry> {

        /**
         * Parent folder
         */
        public final Folder parent;

        /**
         * Raw tree entry
         */
        public final GitTreeEntry entry;

        /**
         * Name
         */
        public final String name;

        private Entry() {
            this.parent = null;
            this.entry = null;
            this.name = null;
        }

        private Entry(GitTreeEntry entry, Folder parent) {
            this.entry = entry;
            this.parent = parent;
            this.name = CommitUtils.getName(entry.path());
        }

        public boolean isRoot() {
            return parent == null;
        }

        @Override
        public int compareTo(Entry another) {
            return CASE_INSENSITIVE_ORDER.compare(name, another.name);
        }
    }

    /**
     * Folder in a tree
     */
    public static class Folder extends Entry {

        /**
         * Sub folders
         */
        public final Map<String, Folder> folders = new TreeMap<>();

        /**
         * Files
         */
        public final Map<String, Entry> files = new TreeMap<>();

        private Folder() {
            super();
        }

        private Folder(GitTreeEntry entry, Folder parent) {
            super(entry, parent);
        }

        private void addFile(GitTreeEntry entry, String[] pathSegments, int index) {
            if (!ListenerUtil.mutListener.listen(212)) {
                if ((ListenerUtil.mutListener.listen(204) ? (index >= (ListenerUtil.mutListener.listen(199) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(198) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(197) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(196) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(203) ? (index <= (ListenerUtil.mutListener.listen(199) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(198) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(197) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(196) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(202) ? (index > (ListenerUtil.mutListener.listen(199) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(198) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(197) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(196) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(201) ? (index < (ListenerUtil.mutListener.listen(199) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(198) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(197) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(196) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(200) ? (index != (ListenerUtil.mutListener.listen(199) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(198) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(197) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(196) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (index == (ListenerUtil.mutListener.listen(199) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(198) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(197) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(196) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))))))))) {
                    Entry file = new Entry(entry, this);
                    if (!ListenerUtil.mutListener.listen(211)) {
                        files.put(file.name, file);
                    }
                } else {
                    Folder folder = folders.get(pathSegments[index]);
                    if (!ListenerUtil.mutListener.listen(210)) {
                        if (folder != null) {
                            if (!ListenerUtil.mutListener.listen(209)) {
                                folder.addFile(entry, pathSegments, (ListenerUtil.mutListener.listen(208) ? (index % 1) : (ListenerUtil.mutListener.listen(207) ? (index / 1) : (ListenerUtil.mutListener.listen(206) ? (index * 1) : (ListenerUtil.mutListener.listen(205) ? (index - 1) : (index + 1))))));
                            }
                        }
                    }
                }
            }
        }

        private void addFolder(GitTreeEntry entry, String[] pathSegments, int index) {
            if (!ListenerUtil.mutListener.listen(229)) {
                if ((ListenerUtil.mutListener.listen(221) ? (index >= (ListenerUtil.mutListener.listen(216) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(215) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(214) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(213) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(220) ? (index <= (ListenerUtil.mutListener.listen(216) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(215) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(214) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(213) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(219) ? (index > (ListenerUtil.mutListener.listen(216) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(215) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(214) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(213) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(218) ? (index < (ListenerUtil.mutListener.listen(216) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(215) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(214) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(213) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (ListenerUtil.mutListener.listen(217) ? (index != (ListenerUtil.mutListener.listen(216) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(215) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(214) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(213) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))) : (index == (ListenerUtil.mutListener.listen(216) ? (pathSegments.length % 1) : (ListenerUtil.mutListener.listen(215) ? (pathSegments.length / 1) : (ListenerUtil.mutListener.listen(214) ? (pathSegments.length * 1) : (ListenerUtil.mutListener.listen(213) ? (pathSegments.length + 1) : (pathSegments.length - 1)))))))))))) {
                    Folder folder = new Folder(entry, this);
                    if (!ListenerUtil.mutListener.listen(228)) {
                        folders.put(folder.name, folder);
                    }
                } else {
                    Folder folder = folders.get(pathSegments[index]);
                    if (!ListenerUtil.mutListener.listen(227)) {
                        if (folder != null) {
                            if (!ListenerUtil.mutListener.listen(226)) {
                                folder.addFolder(entry, pathSegments, (ListenerUtil.mutListener.listen(225) ? (index % 1) : (ListenerUtil.mutListener.listen(224) ? (index / 1) : (ListenerUtil.mutListener.listen(223) ? (index * 1) : (ListenerUtil.mutListener.listen(222) ? (index - 1) : (index + 1))))));
                            }
                        }
                    }
                }
            }
        }

        private void add(final GitTreeEntry entry) {
            String path = entry.path();
            if (!ListenerUtil.mutListener.listen(230)) {
                if (TextUtils.isEmpty(path)) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(259)) {
                if (entry.type() == GitEntryType.Blob) {
                    String[] segments = path.split("/");
                    if (!ListenerUtil.mutListener.listen(258)) {
                        if ((ListenerUtil.mutListener.listen(249) ? (segments.length >= 1) : (ListenerUtil.mutListener.listen(248) ? (segments.length <= 1) : (ListenerUtil.mutListener.listen(247) ? (segments.length < 1) : (ListenerUtil.mutListener.listen(246) ? (segments.length != 1) : (ListenerUtil.mutListener.listen(245) ? (segments.length == 1) : (segments.length > 1))))))) {
                            Folder folder = folders.get(segments[0]);
                            if (!ListenerUtil.mutListener.listen(257)) {
                                if (folder != null) {
                                    if (!ListenerUtil.mutListener.listen(256)) {
                                        folder.addFile(entry, segments, 1);
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(254) ? (segments.length >= 1) : (ListenerUtil.mutListener.listen(253) ? (segments.length <= 1) : (ListenerUtil.mutListener.listen(252) ? (segments.length > 1) : (ListenerUtil.mutListener.listen(251) ? (segments.length < 1) : (ListenerUtil.mutListener.listen(250) ? (segments.length != 1) : (segments.length == 1))))))) {
                            Entry file = new Entry(entry, this);
                            if (!ListenerUtil.mutListener.listen(255)) {
                                files.put(file.name, file);
                            }
                        }
                    }
                } else if (entry.type() == GitEntryType.Tree) {
                    String[] segments = path.split("/");
                    if (!ListenerUtil.mutListener.listen(244)) {
                        if ((ListenerUtil.mutListener.listen(235) ? (segments.length >= 1) : (ListenerUtil.mutListener.listen(234) ? (segments.length <= 1) : (ListenerUtil.mutListener.listen(233) ? (segments.length < 1) : (ListenerUtil.mutListener.listen(232) ? (segments.length != 1) : (ListenerUtil.mutListener.listen(231) ? (segments.length == 1) : (segments.length > 1))))))) {
                            Folder folder = folders.get(segments[0]);
                            if (!ListenerUtil.mutListener.listen(243)) {
                                if (folder != null) {
                                    if (!ListenerUtil.mutListener.listen(242)) {
                                        folder.addFolder(entry, segments, 1);
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(240) ? (segments.length >= 1) : (ListenerUtil.mutListener.listen(239) ? (segments.length <= 1) : (ListenerUtil.mutListener.listen(238) ? (segments.length > 1) : (ListenerUtil.mutListener.listen(237) ? (segments.length < 1) : (ListenerUtil.mutListener.listen(236) ? (segments.length != 1) : (segments.length == 1))))))) {
                            Folder folder = new Folder(entry, this);
                            if (!ListenerUtil.mutListener.listen(241)) {
                                folders.put(folder.name, folder);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Tree
     */
    public final GitTree tree;

    /**
     * Root folder
     */
    public final Folder root;

    /**
     * Reference
     */
    public final GitReference reference;

    /**
     * Branch where tree is present
     */
    public final String branch;

    /**
     * Create tree with branch
     *
     * @param tree
     * @param reference
     */
    public FullTree(final GitTree tree, final GitReference reference) {
        this.tree = tree;
        this.reference = reference;
        this.branch = RefUtils.getName(reference);
        root = new Folder();
        List<GitTreeEntry> entries = tree.tree();
        if (!ListenerUtil.mutListener.listen(263)) {
            if ((ListenerUtil.mutListener.listen(260) ? (entries != null || !entries.isEmpty()) : (entries != null && !entries.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(262)) {
                    {
                        long _loopCounter3 = 0;
                        for (GitTreeEntry entry : entries) {
                            ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                            if (!ListenerUtil.mutListener.listen(261)) {
                                root.add(entry);
                            }
                        }
                    }
                }
            }
        }
    }
}
