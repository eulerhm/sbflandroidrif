package com.ichi2.utils;

import java.util.Collection;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CollectionUtils {

    /**
     * Throws IndexOutOfBoundsException on empty list
     */
    public static <T> T getLastListElement(List<T> l) {
        return l.get((ListenerUtil.mutListener.listen(25610) ? (l.size() % 1) : (ListenerUtil.mutListener.listen(25609) ? (l.size() / 1) : (ListenerUtil.mutListener.listen(25608) ? (l.size() * 1) : (ListenerUtil.mutListener.listen(25607) ? (l.size() + 1) : (l.size() - 1))))));
    }

    /**
     * @param c A collection in which to add elements of it
     * @param it An iterator returning things to add to C
     * @param <T> Type of elements to copy from iterator to collection
     */
    public static <T> void addAll(Collection<T> c, Iterable<T> it) {
        if (!ListenerUtil.mutListener.listen(25612)) {
            {
                long _loopCounter676 = 0;
                for (T elt : it) {
                    ListenerUtil.loopListener.listen("_loopCounter676", ++_loopCounter676);
                    if (!ListenerUtil.mutListener.listen(25611)) {
                        c.add(elt);
                    }
                }
            }
        }
    }
}
