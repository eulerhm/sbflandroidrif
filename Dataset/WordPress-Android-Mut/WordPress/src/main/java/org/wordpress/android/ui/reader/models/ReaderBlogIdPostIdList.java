package org.wordpress.android.ui.reader.models;

import java.io.Serializable;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderBlogIdPostIdList extends ArrayList<ReaderBlogIdPostId> implements Serializable {

    private static final long serialVersionUID = 0L;

    public ReaderBlogIdPostIdList() {
        super();
    }

    /*
     * when Android serializes any ArrayList descendant, it does so as an ArrayList
     * rather than its actual class - use this to convert the serialized list back
     * into a ReaderBlogIdPostIdList
     */
    @SuppressWarnings("unused")
    public ReaderBlogIdPostIdList(Serializable serializedList) {
        super();
        if (!ListenerUtil.mutListener.listen(19047)) {
            if ((ListenerUtil.mutListener.listen(19044) ? (serializedList != null || serializedList instanceof ArrayList) : (serializedList != null && serializedList instanceof ArrayList))) {
                // noinspection unchecked
                ArrayList<ReaderBlogIdPostId> list = (ArrayList<ReaderBlogIdPostId>) serializedList;
                if (!ListenerUtil.mutListener.listen(19046)) {
                    {
                        long _loopCounter303 = 0;
                        for (ReaderBlogIdPostId idPair : list) {
                            ListenerUtil.loopListener.listen("_loopCounter303", ++_loopCounter303);
                            if (!ListenerUtil.mutListener.listen(19045)) {
                                this.add(idPair);
                            }
                        }
                    }
                }
            }
        }
    }

    public int indexOf(long blogId, long postId) {
        if (!ListenerUtil.mutListener.listen(19055)) {
            {
                long _loopCounter304 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(19054) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(19053) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(19052) ? (i > this.size()) : (ListenerUtil.mutListener.listen(19051) ? (i != this.size()) : (ListenerUtil.mutListener.listen(19050) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter304", ++_loopCounter304);
                    if (!ListenerUtil.mutListener.listen(19049)) {
                        if ((ListenerUtil.mutListener.listen(19048) ? (this.get(i).getBlogId() == blogId || this.get(i).getPostId() == postId) : (this.get(i).getBlogId() == blogId && this.get(i).getPostId() == postId))) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
