package org.wordpress.android.models;

import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderCommentList extends ArrayList<ReaderComment> {

    public int indexOfCommentId(long commentId) {
        if (!ListenerUtil.mutListener.listen(2093)) {
            {
                long _loopCounter80 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2092) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(2091) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(2090) ? (i > this.size()) : (ListenerUtil.mutListener.listen(2089) ? (i != this.size()) : (ListenerUtil.mutListener.listen(2088) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter80", ++_loopCounter80);
                    if (!ListenerUtil.mutListener.listen(2087)) {
                        if (commentId == this.get(i).commentId) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /*
     * does passed list contain the same comments as this list?
     */
    public boolean isSameList(ReaderCommentList comments) {
        if (!ListenerUtil.mutListener.listen(2100)) {
            if ((ListenerUtil.mutListener.listen(2099) ? (comments == null && (ListenerUtil.mutListener.listen(2098) ? (comments.size() >= this.size()) : (ListenerUtil.mutListener.listen(2097) ? (comments.size() <= this.size()) : (ListenerUtil.mutListener.listen(2096) ? (comments.size() > this.size()) : (ListenerUtil.mutListener.listen(2095) ? (comments.size() < this.size()) : (ListenerUtil.mutListener.listen(2094) ? (comments.size() == this.size()) : (comments.size() != this.size()))))))) : (comments == null || (ListenerUtil.mutListener.listen(2098) ? (comments.size() >= this.size()) : (ListenerUtil.mutListener.listen(2097) ? (comments.size() <= this.size()) : (ListenerUtil.mutListener.listen(2096) ? (comments.size() > this.size()) : (ListenerUtil.mutListener.listen(2095) ? (comments.size() < this.size()) : (ListenerUtil.mutListener.listen(2094) ? (comments.size() == this.size()) : (comments.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2102)) {
            {
                long _loopCounter81 = 0;
                for (ReaderComment comment : comments) {
                    ListenerUtil.loopListener.listen("_loopCounter81", ++_loopCounter81);
                    if (!ListenerUtil.mutListener.listen(2101)) {
                        if (indexOf(comment) == -1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean replaceComment(long commentId, ReaderComment newComment) {
        if (!ListenerUtil.mutListener.listen(2103)) {
            if (newComment == null) {
                return false;
            }
        }
        int index = indexOfCommentId(commentId);
        if (!ListenerUtil.mutListener.listen(2109)) {
            if ((ListenerUtil.mutListener.listen(2108) ? (index >= -1) : (ListenerUtil.mutListener.listen(2107) ? (index <= -1) : (ListenerUtil.mutListener.listen(2106) ? (index > -1) : (ListenerUtil.mutListener.listen(2105) ? (index < -1) : (ListenerUtil.mutListener.listen(2104) ? (index != -1) : (index == -1))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2110)) {
            // make sure the new comment has the same level as the old one
            newComment.level = this.get(index).level;
        }
        if (!ListenerUtil.mutListener.listen(2111)) {
            this.set(index, newComment);
        }
        return true;
    }
}
