package org.wordpress.android.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UserSuggestion {

    private static final String MENTION_TAXONOMY = "mention";

    public long siteID;

    private String mUserLogin;

    private String mDisplayName;

    private String mImageUrl;

    private String mTaxonomy;

    public UserSuggestion(long siteID, String userLogin, String displayName, String imageUrl, String taxonomy) {
        if (!ListenerUtil.mutListener.listen(2660)) {
            this.siteID = siteID;
        }
        if (!ListenerUtil.mutListener.listen(2661)) {
            mUserLogin = userLogin;
        }
        if (!ListenerUtil.mutListener.listen(2662)) {
            mDisplayName = displayName;
        }
        if (!ListenerUtil.mutListener.listen(2663)) {
            mImageUrl = imageUrl;
        }
        if (!ListenerUtil.mutListener.listen(2664)) {
            mTaxonomy = taxonomy;
        }
    }

    public static UserSuggestion fromJSON(JSONObject json, long siteID) {
        if (!ListenerUtil.mutListener.listen(2665)) {
            if (json == null) {
                return null;
            }
        }
        String userLogin = JSONUtils.getString(json, "user_login");
        String displayName = JSONUtils.getString(json, "display_name");
        String imageUrl = JSONUtils.getString(json, "image_URL");
        // the api currently doesn't return a taxonomy field but we want to be ready for when it does
        return new UserSuggestion(siteID, userLogin, displayName, imageUrl, MENTION_TAXONOMY);
    }

    public static List<UserSuggestion> suggestionListFromJSON(JSONArray jsonArray, long siteID) {
        if (!ListenerUtil.mutListener.listen(2666)) {
            if (jsonArray == null) {
                return null;
            }
        }
        ArrayList<UserSuggestion> suggestions = new ArrayList<UserSuggestion>(jsonArray.length());
        if (!ListenerUtil.mutListener.listen(2673)) {
            {
                long _loopCounter103 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2672) ? (i >= jsonArray.length()) : (ListenerUtil.mutListener.listen(2671) ? (i <= jsonArray.length()) : (ListenerUtil.mutListener.listen(2670) ? (i > jsonArray.length()) : (ListenerUtil.mutListener.listen(2669) ? (i != jsonArray.length()) : (ListenerUtil.mutListener.listen(2668) ? (i == jsonArray.length()) : (i < jsonArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter103", ++_loopCounter103);
                    UserSuggestion suggestion = UserSuggestion.fromJSON(jsonArray.optJSONObject(i), siteID);
                    if (!ListenerUtil.mutListener.listen(2667)) {
                        suggestions.add(suggestion);
                    }
                }
            }
        }
        return suggestions;
    }

    public String getUserLogin() {
        return StringUtils.notNullStr(mUserLogin);
    }

    public String getDisplayName() {
        return StringUtils.notNullStr(mDisplayName);
    }

    public String getImageUrl() {
        return StringUtils.notNullStr(mImageUrl);
    }

    public String getTaxonomy() {
        return StringUtils.notNullStr(mTaxonomy);
    }
}
