package org.wordpress.android.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Tag {

    public long siteID;

    private String mTag;

    public Tag(long siteID, String tag) {
        if (!ListenerUtil.mutListener.listen(2649)) {
            this.siteID = siteID;
        }
        if (!ListenerUtil.mutListener.listen(2650)) {
            mTag = tag;
        }
    }

    public static Tag fromJSON(JSONObject json, long siteID) {
        if (!ListenerUtil.mutListener.listen(2651)) {
            if (json == null) {
                return null;
            }
        }
        String tag = JSONUtils.getString(json, "name");
        // the api currently doesn't return a taxonomy field but we want to be ready for when it does
        return new Tag(siteID, tag);
    }

    public static List<Tag> tagListFromJSON(JSONArray jsonArray, long siteID) {
        if (!ListenerUtil.mutListener.listen(2652)) {
            if (jsonArray == null) {
                return null;
            }
        }
        ArrayList<Tag> suggestions = new ArrayList<Tag>(jsonArray.length());
        if (!ListenerUtil.mutListener.listen(2659)) {
            {
                long _loopCounter102 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2658) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(2657) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(2656) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(2655) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(2654) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter102", ++_loopCounter102);
                    Tag suggestion = Tag.fromJSON(jsonArray.optJSONObject(i), siteID);
                    if (!ListenerUtil.mutListener.listen(2653)) {
                        suggestions.add(suggestion);
                    }
                }
            }
        }
        return suggestions;
    }

    public String getTag() {
        return StringUtils.notNullStr(mTag);
    }
}
