/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2013-2021 Threema GmbH
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

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Functional functionality (map / filter / select etc)
 */
public class Functional {

    /**
     *  Filter a collection using the predicate.
     *  Null values are always retained and not passed to the predicate.
     */
    public static <T> Collection<T> filter(Collection<T> target, IPredicateNonNull<T> predicate) {
        Collection<T> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(13050)) {
            {
                long _loopCounter119 = 0;
                for (T element : target) {
                    ListenerUtil.loopListener.listen("_loopCounter119", ++_loopCounter119);
                    if (!ListenerUtil.mutListener.listen(13049)) {
                        if ((ListenerUtil.mutListener.listen(13047) ? (element == null && predicate.apply(element)) : (element == null || predicate.apply(element)))) {
                            if (!ListenerUtil.mutListener.listen(13048)) {
                                result.add(element);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     *  Filter a collection using the predicate.
     */
    public static <T> Collection<T> filter(Collection<T> target, IPredicate<T> predicate) {
        Collection<T> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(13053)) {
            {
                long _loopCounter120 = 0;
                for (T element : target) {
                    ListenerUtil.loopListener.listen("_loopCounter120", ++_loopCounter120);
                    if (!ListenerUtil.mutListener.listen(13052)) {
                        if (predicate.apply(element)) {
                            if (!ListenerUtil.mutListener.listen(13051)) {
                                result.add(element);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     *  Filter a collection using the predicate.
     *  Null values are always retained and not passed to the predicate.
     */
    public static <T> List<T> filter(List<T> target, IPredicateNonNull<T> predicate) {
        return (List<T>) Functional.filter((Collection<T>) target, predicate);
    }

    /**
     *  Filter a collection using the predicate.
     */
    public static <T> List<T> filter(List<T> target, IPredicate<T> predicate) {
        return (List<T>) Functional.filter((Collection<T>) target, predicate);
    }

    public static <T> T select(Collection<T> target, IPredicateNonNull<T> predicate) {
        T result = null;
        if (!ListenerUtil.mutListener.listen(13057)) {
            {
                long _loopCounter121 = 0;
                for (T element : target) {
                    ListenerUtil.loopListener.listen("_loopCounter121", ++_loopCounter121);
                    if (!ListenerUtil.mutListener.listen(13055)) {
                        if ((ListenerUtil.mutListener.listen(13054) ? (element == null && !predicate.apply(element)) : (element == null || !predicate.apply(element))))
                            continue;
                    }
                    if (!ListenerUtil.mutListener.listen(13056)) {
                        result = element;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public static <K, T> T select(Map<K, T> target, IPredicateNonNull<T> predicate) {
        if (!ListenerUtil.mutListener.listen(13060)) {
            {
                long _loopCounter122 = 0;
                for (Map.Entry<K, T> cursor : target.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter122", ++_loopCounter122);
                    if (!ListenerUtil.mutListener.listen(13059)) {
                        if ((ListenerUtil.mutListener.listen(13058) ? (cursor.getValue() != null || predicate.apply(cursor.getValue())) : (cursor.getValue() != null && predicate.apply(cursor.getValue())))) {
                            return cursor.getValue();
                        }
                    }
                }
            }
        }
        return null;
    }

    public static <T> T select(SparseArray<T> target, IPredicateNonNull<T> predicate) {
        if (!ListenerUtil.mutListener.listen(13068)) {
            {
                long _loopCounter123 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(13067) ? (n >= target.size()) : (ListenerUtil.mutListener.listen(13066) ? (n <= target.size()) : (ListenerUtil.mutListener.listen(13065) ? (n > target.size()) : (ListenerUtil.mutListener.listen(13064) ? (n != target.size()) : (ListenerUtil.mutListener.listen(13063) ? (n == target.size()) : (n < target.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter123", ++_loopCounter123);
                    int key = target.keyAt(n);
                    T object = target.get(key);
                    if (!ListenerUtil.mutListener.listen(13062)) {
                        if ((ListenerUtil.mutListener.listen(13061) ? (object != null || predicate.apply(object)) : (object != null && predicate.apply(object)))) {
                            return object;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static <T> T select(Collection<T> target, IPredicateNonNull<T> predicate, T defaultValue) {
        T result = defaultValue;
        if (!ListenerUtil.mutListener.listen(13072)) {
            {
                long _loopCounter124 = 0;
                for (T element : target) {
                    ListenerUtil.loopListener.listen("_loopCounter124", ++_loopCounter124);
                    if (!ListenerUtil.mutListener.listen(13070)) {
                        if ((ListenerUtil.mutListener.listen(13069) ? (element == null && !predicate.apply(element)) : (element == null || !predicate.apply(element))))
                            continue;
                    }
                    if (!ListenerUtil.mutListener.listen(13071)) {
                        result = element;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public static <T> T select(T[] target, IPredicateNonNull<T> predicate, T defaultValue) {
        T result = defaultValue;
        if (!ListenerUtil.mutListener.listen(13076)) {
            {
                long _loopCounter125 = 0;
                for (T element : target) {
                    ListenerUtil.loopListener.listen("_loopCounter125", ++_loopCounter125);
                    if (!ListenerUtil.mutListener.listen(13074)) {
                        if ((ListenerUtil.mutListener.listen(13073) ? (element == null && !predicate.apply(element)) : (element == null || !predicate.apply(element))))
                            continue;
                    }
                    if (!ListenerUtil.mutListener.listen(13075)) {
                        result = element;
                    }
                    break;
                }
            }
        }
        return result;
    }

    /**
     *  Apply a mapping function to all elements of a collection.
     *  Return a new collection with those elements.
     */
    public static <T, U> Collection<U> map(Collection<T> target, IMap<T, U> mapping) {
        Collection<U> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(13078)) {
            {
                long _loopCounter126 = 0;
                for (T element : target) {
                    ListenerUtil.loopListener.listen("_loopCounter126", ++_loopCounter126);
                    if (!ListenerUtil.mutListener.listen(13077)) {
                        result.add(mapping.apply(element));
                    }
                }
            }
        }
        return result;
    }

    /**
     *  Apply a mapping function to all elements of a list.
     *  Return a new list with those elements.
     */
    public static <T, U> List<U> map(List<T> target, IMap<T, U> mapping) {
        return (List<U>) Functional.map((Collection<T>) target, mapping);
    }
}
