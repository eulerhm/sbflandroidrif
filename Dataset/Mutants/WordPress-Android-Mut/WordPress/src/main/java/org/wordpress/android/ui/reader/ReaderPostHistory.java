package org.wordpress.android.ui.reader;

import android.os.Bundle;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostId;
import org.wordpress.android.util.StringUtils;
import java.util.ArrayList;
import java.util.Stack;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * used to maintain a history of posts viewed in the detail fragment so we can navigate back
 * through them when the user hits the back button - currently used only for related posts
 */
class ReaderPostHistory extends Stack<ReaderBlogIdPostId> {

    private static final String HISTORY_KEY_NAME = "reader_post_history";

    void restoreInstance(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(20954)) {
            clear();
        }
        if (!ListenerUtil.mutListener.listen(20958)) {
            if ((ListenerUtil.mutListener.listen(20955) ? (bundle != null || bundle.containsKey(HISTORY_KEY_NAME)) : (bundle != null && bundle.containsKey(HISTORY_KEY_NAME)))) {
                ArrayList<String> history = bundle.getStringArrayList(HISTORY_KEY_NAME);
                if (!ListenerUtil.mutListener.listen(20957)) {
                    if (history != null) {
                        if (!ListenerUtil.mutListener.listen(20956)) {
                            this.fromArrayList(history);
                        }
                    }
                }
            }
        }
    }

    void saveInstance(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(20961)) {
            if ((ListenerUtil.mutListener.listen(20959) ? (bundle != null || !isEmpty()) : (bundle != null && !isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(20960)) {
                    bundle.putStringArrayList(HISTORY_KEY_NAME, this.toArrayList());
                }
            }
        }
    }

    private ArrayList<String> toArrayList() {
        ArrayList<String> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(20963)) {
            {
                long _loopCounter335 = 0;
                for (ReaderBlogIdPostId ids : this) {
                    ListenerUtil.loopListener.listen("_loopCounter335", ++_loopCounter335);
                    if (!ListenerUtil.mutListener.listen(20962)) {
                        list.add(ids.getBlogId() + ":" + ids.getPostId());
                    }
                }
            }
        }
        return list;
    }

    private void fromArrayList(ArrayList<String> list) {
        if (!ListenerUtil.mutListener.listen(20965)) {
            if ((ListenerUtil.mutListener.listen(20964) ? (list == null && list.isEmpty()) : (list == null || list.isEmpty()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(20967)) {
            {
                long _loopCounter336 = 0;
                for (String idPair : list) {
                    ListenerUtil.loopListener.listen("_loopCounter336", ++_loopCounter336);
                    String[] split = idPair.split(":");
                    long blogId = StringUtils.stringToLong(split[0]);
                    long postId = StringUtils.stringToLong(split[1]);
                    if (!ListenerUtil.mutListener.listen(20966)) {
                        this.add(new ReaderBlogIdPostId(blogId, postId));
                    }
                }
            }
        }
    }
}
