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
package com.github.pockethub.android.core;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Generic weak store of ids to items
 */
public abstract class ItemStore {

    private static class ItemReference<V> extends WeakReference<V> {

        private Object id;

        /**
         * Create item reference
         *
         * @param item
         * @param id
         * @param queue
         */
        public ItemReference(V item, Object id, ReferenceQueue<? super V> queue) {
            super(item, queue);
            if (!ListenerUtil.mutListener.listen(556)) {
                this.id = id;
            }
        }
    }

    /**
     * Generic reference store
     *
     * @param <V>
     */
    protected static class ItemReferences<V> {

        private final ReferenceQueue<V> queue;

        private final Map<Object, ItemReference<V>> items;

        /**
         * Create reference store
         */
        public ItemReferences() {
            queue = new ReferenceQueue<>();
            items = new ConcurrentHashMap<>();
        }

        @SuppressWarnings("rawtypes")
        private void expungeEntries() {
            ItemReference ref;
            if (!ListenerUtil.mutListener.listen(558)) {
                {
                    long _loopCounter14 = 0;
                    while ((ref = (ItemReference) queue.poll()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                        if (!ListenerUtil.mutListener.listen(557)) {
                            items.remove(ref.id);
                        }
                    }
                }
            }
        }

        /**
         * Get item with id from store
         *
         * @param id
         * @return item
         */
        public V get(final Object id) {
            if (!ListenerUtil.mutListener.listen(559)) {
                expungeEntries();
            }
            WeakReference<V> ref = items.get(id);
            return ref != null ? ref.get() : null;
        }

        /**
         * Insert item with id into store
         *
         * @param id
         * @param item
         */
        public void put(Object id, V item) {
            if (!ListenerUtil.mutListener.listen(560)) {
                expungeEntries();
            }
            if (!ListenerUtil.mutListener.listen(561)) {
                items.put(id, new ItemReference<>(item, id, queue));
            }
        }
    }
}
