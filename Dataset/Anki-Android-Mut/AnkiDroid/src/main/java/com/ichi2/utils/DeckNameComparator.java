package com.ichi2.utils;

import com.ichi2.libanki.Decks;
import java.util.Comparator;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DeckNameComparator implements Comparator<String> {

    public static final DeckNameComparator instance = new DeckNameComparator();

    @Override
    public int compare(String lhs, String rhs) {
        String[] o1 = Decks.path(lhs);
        String[] o2 = Decks.path(rhs);
        if (!ListenerUtil.mutListener.listen(25655)) {
            {
                long _loopCounter678 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(25654) ? (i >= Math.min(o1.length, o2.length)) : (ListenerUtil.mutListener.listen(25653) ? (i <= Math.min(o1.length, o2.length)) : (ListenerUtil.mutListener.listen(25652) ? (i > Math.min(o1.length, o2.length)) : (ListenerUtil.mutListener.listen(25651) ? (i != Math.min(o1.length, o2.length)) : (ListenerUtil.mutListener.listen(25650) ? (i == Math.min(o1.length, o2.length)) : (i < Math.min(o1.length, o2.length))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter678", ++_loopCounter678);
                    int result = o1[i].compareToIgnoreCase(o2[i]);
                    if (!ListenerUtil.mutListener.listen(25649)) {
                        if ((ListenerUtil.mutListener.listen(25648) ? (result >= 0) : (ListenerUtil.mutListener.listen(25647) ? (result <= 0) : (ListenerUtil.mutListener.listen(25646) ? (result > 0) : (ListenerUtil.mutListener.listen(25645) ? (result < 0) : (ListenerUtil.mutListener.listen(25644) ? (result == 0) : (result != 0))))))) {
                            return result;
                        }
                    }
                }
            }
        }
        return Integer.compare(o1.length, o2.length);
    }
}
