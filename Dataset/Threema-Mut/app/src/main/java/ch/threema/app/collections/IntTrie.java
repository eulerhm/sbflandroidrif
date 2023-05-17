/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A trie data structure with integers as path elements that can store data in the leaf nodes.
 *
 * Note that the path element type (int in this case) could easily be made generic, but that would
 * have the drawback of having to use the non-primitive path type `Integer[]` instead of `int[]`.
 * This creates the situation that path elements could be null, requiring runtime-checks.
 * Additionally, storage of node children would have to be done with a HashMap instead of a
 * SparseArray, leading to higher memory consumption.
 */
public class IntTrie<T> {

    private class Node {

        @NonNull
        final SparseArrayCompat<Node> children;

        @Nullable
        T value;

        Node(@Nullable T value) {
            this.children = new SparseArrayCompat<>();
            if (!ListenerUtil.mutListener.listen(13079)) {
                this.value = value;
            }
        }

        @Override
        public String toString() {
            return "Node{" + "value=" + value + ", children=" + children + '}';
        }
    }

    /**
     *  Wrapper around a value.
     *
     *  It contains a value of type V, plus the information whether
     *  the value node is a leaf or not.
     *
     *  The value may be null if a node exists for the chosen path,
     *  but it does not contain a value.
     */
    public static class Value<V> {

        @Nullable
        private V value;

        private boolean isLeaf;

        public Value(@Nullable V value, boolean isLeaf) {
            if (!ListenerUtil.mutListener.listen(13080)) {
                this.value = value;
            }
            if (!ListenerUtil.mutListener.listen(13081)) {
                this.isLeaf = isLeaf;
            }
        }

        @Nullable
        public V getValue() {
            return value;
        }

        public boolean isLeaf() {
            return isLeaf;
        }
    }

    @NonNull
    private Node root;

    public IntTrie() {
        if (!ListenerUtil.mutListener.listen(13082)) {
            this.root = new Node(null);
        }
    }

    /**
     *  Insert a new value into the trie.
     *  @param path The path to that value.
     *  @param value The value to be stored at the end of the path.
     */
    public void insert(@NonNull int[] path, @NonNull T value) {
        if (!ListenerUtil.mutListener.listen(13088)) {
            // Do not insert empty arrays
            if ((ListenerUtil.mutListener.listen(13087) ? (path.length >= 0) : (ListenerUtil.mutListener.listen(13086) ? (path.length <= 0) : (ListenerUtil.mutListener.listen(13085) ? (path.length > 0) : (ListenerUtil.mutListener.listen(13084) ? (path.length < 0) : (ListenerUtil.mutListener.listen(13083) ? (path.length != 0) : (path.length == 0))))))) {
                return;
            }
        }
        Node currentNode = this.root;
        if (!ListenerUtil.mutListener.listen(13093)) {
            {
                long _loopCounter127 = 0;
                for (int p : path) {
                    ListenerUtil.loopListener.listen("_loopCounter127", ++_loopCounter127);
                    Node foundNode = currentNode.children.get(p);
                    if (!ListenerUtil.mutListener.listen(13092)) {
                        if (foundNode != null) {
                            if (!ListenerUtil.mutListener.listen(13091)) {
                                currentNode = foundNode;
                            }
                        } else {
                            final Node newNode = new Node(null);
                            if (!ListenerUtil.mutListener.listen(13089)) {
                                currentNode.children.put(p, newNode);
                            }
                            if (!ListenerUtil.mutListener.listen(13090)) {
                                currentNode = newNode;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(13094)) {
            currentNode.value = value;
        }
    }

    /**
     *  Return the value at the specified path, or null.
     */
    @Nullable
    public Value<T> get(@NonNull int[] path) {
        if (!ListenerUtil.mutListener.listen(13100)) {
            // No need for checking empty arrays
            if ((ListenerUtil.mutListener.listen(13099) ? (path.length >= 0) : (ListenerUtil.mutListener.listen(13098) ? (path.length <= 0) : (ListenerUtil.mutListener.listen(13097) ? (path.length > 0) : (ListenerUtil.mutListener.listen(13096) ? (path.length < 0) : (ListenerUtil.mutListener.listen(13095) ? (path.length != 0) : (path.length == 0))))))) {
                return null;
            }
        }
        Node currentNode = this.root;
        if (!ListenerUtil.mutListener.listen(13103)) {
            {
                long _loopCounter128 = 0;
                for (int p : path) {
                    ListenerUtil.loopListener.listen("_loopCounter128", ++_loopCounter128);
                    final Node foundNode = currentNode.children.get(p);
                    if (!ListenerUtil.mutListener.listen(13101)) {
                        if (foundNode == null) {
                            return null;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13102)) {
                        currentNode = foundNode;
                    }
                }
            }
        }
        return new Value<>(currentNode.value, currentNode.children.size() == 0);
    }

    /**
     *  Return the value at the specified path, or null.
     *  The path may not contain any null values!
     */
    @Nullable
    public Value<T> get(@NonNull Iterable<Integer> path) {
        Node currentNode = this.root;
        if (!ListenerUtil.mutListener.listen(13106)) {
            {
                long _loopCounter129 = 0;
                for (int p : path) {
                    ListenerUtil.loopListener.listen("_loopCounter129", ++_loopCounter129);
                    final Node foundNode = currentNode.children.get(p);
                    if (!ListenerUtil.mutListener.listen(13104)) {
                        if (foundNode == null) {
                            return null;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(13105)) {
                        currentNode = foundNode;
                    }
                }
            }
        }
        return new Value<>(currentNode.value, currentNode.children.size() == 0);
    }

    /**
     *  Return the trie contains an element at the specified path.
     */
    public boolean contains(@NonNull int[] path) {
        final Value value = this.get(path);
        return (ListenerUtil.mutListener.listen(13107) ? (value != null || value.getValue() != null) : (value != null && value.getValue() != null));
    }

    /**
     *  Return the trie contains an element at the specified path.
     *  The path may not contain any null values!
     */
    public boolean contains(@NonNull Iterable<Integer> path) {
        final Value value = this.get(path);
        return (ListenerUtil.mutListener.listen(13108) ? (value != null || value.getValue() != null) : (value != null && value.getValue() != null));
    }
}
