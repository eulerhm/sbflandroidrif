package org.wordpress.android.models;

import java.util.HashSet;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderUserIdList extends HashSet<Long> {

    /*
     * returns true if passed list contains the same userIds as this list
     */
    public boolean isSameList(ReaderUserIdList compareIds) {
        if (!ListenerUtil.mutListener.listen(2624)) {
            if ((ListenerUtil.mutListener.listen(2623) ? (compareIds == null && (ListenerUtil.mutListener.listen(2622) ? (compareIds.size() >= this.size()) : (ListenerUtil.mutListener.listen(2621) ? (compareIds.size() <= this.size()) : (ListenerUtil.mutListener.listen(2620) ? (compareIds.size() > this.size()) : (ListenerUtil.mutListener.listen(2619) ? (compareIds.size() < this.size()) : (ListenerUtil.mutListener.listen(2618) ? (compareIds.size() == this.size()) : (compareIds.size() != this.size()))))))) : (compareIds == null || (ListenerUtil.mutListener.listen(2622) ? (compareIds.size() >= this.size()) : (ListenerUtil.mutListener.listen(2621) ? (compareIds.size() <= this.size()) : (ListenerUtil.mutListener.listen(2620) ? (compareIds.size() > this.size()) : (ListenerUtil.mutListener.listen(2619) ? (compareIds.size() < this.size()) : (ListenerUtil.mutListener.listen(2618) ? (compareIds.size() == this.size()) : (compareIds.size() != this.size()))))))))) {
                return false;
            }
        }
        return this.containsAll(compareIds);
    }
}
