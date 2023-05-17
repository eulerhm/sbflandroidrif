package org.wordpress.android.models;

import android.text.TextUtils;
import org.json.JSONObject;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.UrlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderUser {

    public long userId;

    public long blogId;

    private String mUserName;

    private String mDisplayName;

    private String mUrl;

    private String mProfileUrl;

    private String mAvatarUrl;

    public static ReaderUser fromJson(JSONObject json) {
        ReaderUser user = new ReaderUser();
        if (!ListenerUtil.mutListener.listen(2582)) {
            if (json == null) {
                return user;
            }
        }
        if (!ListenerUtil.mutListener.listen(2583)) {
            user.userId = json.optLong("ID");
        }
        if (!ListenerUtil.mutListener.listen(2584)) {
            user.blogId = json.optLong("site_ID");
        }
        if (!ListenerUtil.mutListener.listen(2585)) {
            user.mUserName = JSONUtils.getString(json, "username");
        }
        if (!ListenerUtil.mutListener.listen(2586)) {
            // <-- this isn't necessarily a wp blog
            user.mUrl = JSONUtils.getString(json, "URL");
        }
        if (!ListenerUtil.mutListener.listen(2587)) {
            user.mProfileUrl = JSONUtils.getString(json, "profile_URL");
        }
        if (!ListenerUtil.mutListener.listen(2588)) {
            user.mAvatarUrl = JSONUtils.getString(json, "avatar_URL");
        }
        if (!ListenerUtil.mutListener.listen(2591)) {
            // "me" api call (current user) has "display_name", others have "name"
            if (json.has("display_name")) {
                if (!ListenerUtil.mutListener.listen(2590)) {
                    user.mDisplayName = JSONUtils.getStringDecoded(json, "display_name");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2589)) {
                    user.mDisplayName = JSONUtils.getStringDecoded(json, "name");
                }
            }
        }
        return user;
    }

    public String getUserName() {
        return StringUtils.notNullStr(mUserName);
    }

    public void setUserName(String userName) {
        if (!ListenerUtil.mutListener.listen(2592)) {
            this.mUserName = StringUtils.notNullStr(userName);
        }
    }

    public String getDisplayName() {
        return StringUtils.notNullStr(mDisplayName);
    }

    public void setDisplayName(String displayName) {
        if (!ListenerUtil.mutListener.listen(2593)) {
            this.mDisplayName = StringUtils.notNullStr(displayName);
        }
    }

    public String getUrl() {
        return StringUtils.notNullStr(mUrl);
    }

    public void setUrl(String url) {
        if (!ListenerUtil.mutListener.listen(2594)) {
            this.mUrl = StringUtils.notNullStr(url);
        }
    }

    public String getProfileUrl() {
        return StringUtils.notNullStr(mProfileUrl);
    }

    public void setProfileUrl(String profileUrl) {
        if (!ListenerUtil.mutListener.listen(2595)) {
            this.mProfileUrl = StringUtils.notNullStr(profileUrl);
        }
    }

    public String getAvatarUrl() {
        return StringUtils.notNullStr(mAvatarUrl);
    }

    public void setAvatarUrl(String avatarUrl) {
        if (!ListenerUtil.mutListener.listen(2596)) {
            this.mAvatarUrl = StringUtils.notNullStr(avatarUrl);
        }
    }

    public boolean hasUrl() {
        return !TextUtils.isEmpty(mUrl);
    }

    public boolean hasAvatarUrl() {
        return !TextUtils.isEmpty(mAvatarUrl);
    }

    public boolean hasBlogId() {
        return ((ListenerUtil.mutListener.listen(2601) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(2600) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(2599) ? (blogId > 0) : (ListenerUtil.mutListener.listen(2598) ? (blogId < 0) : (ListenerUtil.mutListener.listen(2597) ? (blogId == 0) : (blogId != 0)))))));
    }

    /*
     * not stored - used by ReaderUserAdapter for performance
     */
    private transient String mUrlDomain;

    public String getUrlDomain() {
        if (!ListenerUtil.mutListener.listen(2605)) {
            if (mUrlDomain == null) {
                if (!ListenerUtil.mutListener.listen(2604)) {
                    if (hasUrl()) {
                        if (!ListenerUtil.mutListener.listen(2603)) {
                            mUrlDomain = UrlUtils.getHost(getUrl());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2602)) {
                            mUrlDomain = "";
                        }
                    }
                }
            }
        }
        return mUrlDomain;
    }

    public boolean isSameUser(ReaderUser user) {
        if (!ListenerUtil.mutListener.listen(2606)) {
            if (user == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2612)) {
            if ((ListenerUtil.mutListener.listen(2611) ? (this.userId >= user.userId) : (ListenerUtil.mutListener.listen(2610) ? (this.userId <= user.userId) : (ListenerUtil.mutListener.listen(2609) ? (this.userId > user.userId) : (ListenerUtil.mutListener.listen(2608) ? (this.userId < user.userId) : (ListenerUtil.mutListener.listen(2607) ? (this.userId == user.userId) : (this.userId != user.userId))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2613)) {
            if (!this.getAvatarUrl().equals(user.getAvatarUrl())) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2614)) {
            if (!this.getDisplayName().equals(user.getDisplayName())) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2615)) {
            if (!this.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2616)) {
            if (!this.getUrl().equals(user.getUrl())) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2617)) {
            if (!this.getProfileUrl().equals(user.getProfileUrl())) {
                return false;
            }
        }
        return true;
    }
}
