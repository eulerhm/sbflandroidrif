package org.wordpress.android.models;

import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderTagList extends ArrayList<ReaderTag> {

    public int indexOfTagName(String tagName) {
        if (!ListenerUtil.mutListener.listen(2543)) {
            if ((ListenerUtil.mutListener.listen(2542) ? (tagName == null && isEmpty()) : (tagName == null || isEmpty()))) {
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(2550)) {
            {
                long _loopCounter93 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2549) ? (i >= size()) : (ListenerUtil.mutListener.listen(2548) ? (i <= size()) : (ListenerUtil.mutListener.listen(2547) ? (i > size()) : (ListenerUtil.mutListener.listen(2546) ? (i != size()) : (ListenerUtil.mutListener.listen(2545) ? (i == size()) : (i < size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter93", ++_loopCounter93);
                    if (!ListenerUtil.mutListener.listen(2544)) {
                        if (tagName.equals(this.get(i).getTagSlug())) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int indexOfTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(2552)) {
            if ((ListenerUtil.mutListener.listen(2551) ? (tag == null && isEmpty()) : (tag == null || isEmpty()))) {
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(2559)) {
            {
                long _loopCounter94 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2558) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(2557) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(2556) ? (i > this.size()) : (ListenerUtil.mutListener.listen(2555) ? (i != this.size()) : (ListenerUtil.mutListener.listen(2554) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter94", ++_loopCounter94);
                    if (!ListenerUtil.mutListener.listen(2553)) {
                        if (ReaderTag.isSameTag(tag, this.get(i))) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public boolean isSameList(ReaderTagList otherList) {
        if (!ListenerUtil.mutListener.listen(2566)) {
            if ((ListenerUtil.mutListener.listen(2565) ? (otherList == null && (ListenerUtil.mutListener.listen(2564) ? (otherList.size() >= this.size()) : (ListenerUtil.mutListener.listen(2563) ? (otherList.size() <= this.size()) : (ListenerUtil.mutListener.listen(2562) ? (otherList.size() > this.size()) : (ListenerUtil.mutListener.listen(2561) ? (otherList.size() < this.size()) : (ListenerUtil.mutListener.listen(2560) ? (otherList.size() == this.size()) : (otherList.size() != this.size()))))))) : (otherList == null || (ListenerUtil.mutListener.listen(2564) ? (otherList.size() >= this.size()) : (ListenerUtil.mutListener.listen(2563) ? (otherList.size() <= this.size()) : (ListenerUtil.mutListener.listen(2562) ? (otherList.size() > this.size()) : (ListenerUtil.mutListener.listen(2561) ? (otherList.size() < this.size()) : (ListenerUtil.mutListener.listen(2560) ? (otherList.size() == this.size()) : (otherList.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2573)) {
            {
                long _loopCounter95 = 0;
                for (ReaderTag otherTag : otherList) {
                    ListenerUtil.loopListener.listen("_loopCounter95", ++_loopCounter95);
                    int i = this.indexOfTag(otherTag);
                    if (!ListenerUtil.mutListener.listen(2572)) {
                        if ((ListenerUtil.mutListener.listen(2571) ? (i >= -1) : (ListenerUtil.mutListener.listen(2570) ? (i <= -1) : (ListenerUtil.mutListener.listen(2569) ? (i > -1) : (ListenerUtil.mutListener.listen(2568) ? (i < -1) : (ListenerUtil.mutListener.listen(2567) ? (i != -1) : (i == -1))))))) {
                            return false;
                        } else if (!otherTag.getEndpoint().equals(this.get(i).getEndpoint())) {
                            return false;
                        } else if (!otherTag.getTagTitle().equals(this.get(i).getTagTitle())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
     * returns a list of tags that are in this list but not in the passed list
     */
    public ReaderTagList getDeletions(ReaderTagList otherList) {
        ReaderTagList deletions = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(2574)) {
            if (otherList == null) {
                return deletions;
            }
        }
        if (!ListenerUtil.mutListener.listen(2577)) {
            {
                long _loopCounter96 = 0;
                for (ReaderTag thisTag : this) {
                    ListenerUtil.loopListener.listen("_loopCounter96", ++_loopCounter96);
                    if (!ListenerUtil.mutListener.listen(2576)) {
                        if (otherList.indexOfTag(thisTag) == -1) {
                            if (!ListenerUtil.mutListener.listen(2575)) {
                                deletions.add(thisTag);
                            }
                        }
                    }
                }
            }
        }
        return deletions;
    }

    public boolean containsFollowingTag() {
        boolean containsFollowing = false;
        if (!ListenerUtil.mutListener.listen(2581)) {
            {
                long _loopCounter97 = 0;
                for (ReaderTag tag : this) {
                    ListenerUtil.loopListener.listen("_loopCounter97", ++_loopCounter97);
                    if (!ListenerUtil.mutListener.listen(2580)) {
                        if ((ListenerUtil.mutListener.listen(2578) ? (tag.isFollowedSites() && tag.isDefaultInMemoryTag()) : (tag.isFollowedSites() || tag.isDefaultInMemoryTag()))) {
                            if (!ListenerUtil.mutListener.listen(2579)) {
                                containsFollowing = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return containsFollowing;
    }
}
