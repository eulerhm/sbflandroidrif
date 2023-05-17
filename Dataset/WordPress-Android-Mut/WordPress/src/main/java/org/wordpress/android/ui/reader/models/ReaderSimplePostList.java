package org.wordpress.android.ui.reader.models;

import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderSimplePostList extends ArrayList<ReaderSimplePost> {

    public static ReaderSimplePostList fromJsonPosts(@NonNull JSONArray jsonPosts) {
        ReaderSimplePostList posts = new ReaderSimplePostList();
        if (!ListenerUtil.mutListener.listen(19103)) {
            {
                long _loopCounter306 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(19102) ? (i >= jsonPosts.length()) : (ListenerUtil.mutListener.listen(19101) ? (i <= jsonPosts.length()) : (ListenerUtil.mutListener.listen(19100) ? (i > jsonPosts.length()) : (ListenerUtil.mutListener.listen(19099) ? (i != jsonPosts.length()) : (ListenerUtil.mutListener.listen(19098) ? (i == jsonPosts.length()) : (i < jsonPosts.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter306", ++_loopCounter306);
                    JSONObject jsonRelatedPost = jsonPosts.optJSONObject(i);
                    if (!ListenerUtil.mutListener.listen(19097)) {
                        if (jsonRelatedPost != null) {
                            ReaderSimplePost relatedPost = ReaderSimplePost.fromJson(jsonRelatedPost);
                            if (!ListenerUtil.mutListener.listen(19096)) {
                                if (relatedPost != null) {
                                    if (!ListenerUtil.mutListener.listen(19095)) {
                                        posts.add(relatedPost);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return posts;
    }
}
