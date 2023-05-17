package org.wordpress.android.models;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderUserList extends ArrayList<ReaderUser> {

    /*
     * returns all userIds in this list
     */
    public ReaderUserIdList getUserIds() {
        ReaderUserIdList ids = new ReaderUserIdList();
        if (!ListenerUtil.mutListener.listen(2626)) {
            {
                long _loopCounter98 = 0;
                for (ReaderUser user : this) {
                    ListenerUtil.loopListener.listen("_loopCounter98", ++_loopCounter98);
                    if (!ListenerUtil.mutListener.listen(2625)) {
                        ids.add(user.userId);
                    }
                }
            }
        }
        return ids;
    }

    public int indexOfUserId(long userId) {
        if (!ListenerUtil.mutListener.listen(2633)) {
            {
                long _loopCounter99 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2632) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(2631) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(2630) ? (i > this.size()) : (ListenerUtil.mutListener.listen(2629) ? (i != this.size()) : (ListenerUtil.mutListener.listen(2628) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter99", ++_loopCounter99);
                    if (!ListenerUtil.mutListener.listen(2627)) {
                        if (userId == this.get(i).userId) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /*
     * passed json is response from getting likes for a post
     */
    public static ReaderUserList fromJsonLikes(JSONObject json) {
        ReaderUserList users = new ReaderUserList();
        if (!ListenerUtil.mutListener.listen(2634)) {
            if (json == null) {
                return users;
            }
        }
        JSONArray jsonLikes = json.optJSONArray("likes");
        if (!ListenerUtil.mutListener.listen(2642)) {
            if (jsonLikes != null) {
                if (!ListenerUtil.mutListener.listen(2641)) {
                    {
                        long _loopCounter100 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(2640) ? (i >= jsonLikes.length()) : (ListenerUtil.mutListener.listen(2639) ? (i <= jsonLikes.length()) : (ListenerUtil.mutListener.listen(2638) ? (i > jsonLikes.length()) : (ListenerUtil.mutListener.listen(2637) ? (i != jsonLikes.length()) : (ListenerUtil.mutListener.listen(2636) ? (i == jsonLikes.length()) : (i < jsonLikes.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter100", ++_loopCounter100);
                            if (!ListenerUtil.mutListener.listen(2635)) {
                                users.add(ReaderUser.fromJson(jsonLikes.optJSONObject(i)));
                            }
                        }
                    }
                }
            }
        }
        return users;
    }
}
