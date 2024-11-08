package org.wordpress.android.models;

import androidx.annotation.Nullable;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.StringUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Person {

    public enum PersonType {

        USER, FOLLOWER, EMAIL_FOLLOWER, VIEWER
    }

    private long mPersonID;

    private int mLocalTableBlogId;

    private String mDisplayName;

    private String mAvatarUrl;

    private PersonType mPersonType;

    // Only users have a role
    private String mRole;

    // Users, followers & viewers has a username, email followers don't
    private String mUsername;

    // Only followers & email followers have a subscribed date
    private String mSubscribed;

    public Person(long personID, int localTableBlogId) {
        if (!ListenerUtil.mutListener.listen(1684)) {
            mPersonID = personID;
        }
        if (!ListenerUtil.mutListener.listen(1685)) {
            mLocalTableBlogId = localTableBlogId;
        }
    }

    @Nullable
    public static Person userFromJSON(JSONObject json, int localTableBlogId) throws JSONException {
        if (!ListenerUtil.mutListener.listen(1686)) {
            if (json == null) {
                return null;
            }
        }
        // Response parameters are in: https://developer.wordpress.com/docs/api/1.1/get/sites/%24site/users/%24user_id/
        try {
            long personID = Long.parseLong(json.getString("ID"));
            Person person = new Person(personID, localTableBlogId);
            if (!ListenerUtil.mutListener.listen(1688)) {
                person.setUsername(json.optString("login"));
            }
            if (!ListenerUtil.mutListener.listen(1689)) {
                person.setDisplayName(StringEscapeUtils.unescapeHtml4(json.optString("name")));
            }
            if (!ListenerUtil.mutListener.listen(1690)) {
                person.setAvatarUrl(json.optString("avatar_URL"));
            }
            if (!ListenerUtil.mutListener.listen(1691)) {
                person.mPersonType = PersonType.USER;
            }
            // We don't support multiple roles, so the first role is picked just as it's in Calypso
            String role = json.getJSONArray("roles").optString(0);
            if (!ListenerUtil.mutListener.listen(1692)) {
                person.setRole(role);
            }
            return person;
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(1687)) {
                AppLog.e(AppLog.T.PEOPLE, "The ID parsed from the JSON couldn't be converted to long: " + e);
            }
        }
        return null;
    }

    @Nullable
    public static Person followerFromJSON(JSONObject json, int localTableBlogId, boolean isEmailFollower) throws JSONException {
        if (!ListenerUtil.mutListener.listen(1693)) {
            if (json == null) {
                return null;
            }
        }
        // Response parameters are in: https://developer.wordpress.com/docs/api/1.1/get/sites/%24site/stats/followers/
        try {
            long personID = Long.parseLong(json.getString("ID"));
            Person person = new Person(personID, localTableBlogId);
            if (!ListenerUtil.mutListener.listen(1695)) {
                person.setDisplayName(StringEscapeUtils.unescapeHtml4(json.optString("label")));
            }
            if (!ListenerUtil.mutListener.listen(1696)) {
                person.setUsername(json.optString("login"));
            }
            if (!ListenerUtil.mutListener.listen(1697)) {
                person.setAvatarUrl(json.optString("avatar"));
            }
            if (!ListenerUtil.mutListener.listen(1698)) {
                person.setSubscribed(json.optString("date_subscribed"));
            }
            if (!ListenerUtil.mutListener.listen(1699)) {
                person.mPersonType = isEmailFollower ? PersonType.EMAIL_FOLLOWER : PersonType.FOLLOWER;
            }
            return person;
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(1694)) {
                AppLog.e(AppLog.T.PEOPLE, "The ID parsed from the JSON couldn't be converted to long: " + e);
            }
        }
        return null;
    }

    @Nullable
    public static Person viewerFromJSON(JSONObject json, int localTableBlogId) throws JSONException {
        if (!ListenerUtil.mutListener.listen(1700)) {
            if (json == null) {
                return null;
            }
        }
        // https://developer.wordpress.com/docs/api/1.1/get/sites/%24site/users/%24user_id/
        try {
            long personID = Long.parseLong(json.getString("ID"));
            Person person = new Person(personID, localTableBlogId);
            if (!ListenerUtil.mutListener.listen(1702)) {
                person.setUsername(json.optString("login"));
            }
            if (!ListenerUtil.mutListener.listen(1703)) {
                person.setDisplayName(StringEscapeUtils.unescapeHtml4(json.optString("name")));
            }
            if (!ListenerUtil.mutListener.listen(1704)) {
                person.setAvatarUrl(json.optString("avatar_URL"));
            }
            if (!ListenerUtil.mutListener.listen(1705)) {
                person.setPersonType(PersonType.VIEWER);
            }
            return person;
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(1701)) {
                AppLog.e(AppLog.T.PEOPLE, "The ID parsed from the JSON couldn't be converted to long: " + e);
            }
        }
        return null;
    }

    public long getPersonID() {
        return mPersonID;
    }

    public int getLocalTableBlogId() {
        return mLocalTableBlogId;
    }

    public String getUsername() {
        return StringUtils.notNullStr(mUsername);
    }

    public void setUsername(String username) {
        if (!ListenerUtil.mutListener.listen(1706)) {
            mUsername = username;
        }
    }

    public String getDisplayName() {
        return StringUtils.notNullStr(mDisplayName);
    }

    public void setDisplayName(String displayName) {
        if (!ListenerUtil.mutListener.listen(1707)) {
            mDisplayName = displayName;
        }
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        if (!ListenerUtil.mutListener.listen(1708)) {
            mRole = role;
        }
    }

    public String getAvatarUrl() {
        return StringUtils.notNullStr(mAvatarUrl);
    }

    public void setAvatarUrl(String avatarUrl) {
        if (!ListenerUtil.mutListener.listen(1709)) {
            mAvatarUrl = avatarUrl;
        }
    }

    public String getSubscribed() {
        return StringUtils.notNullStr(mSubscribed);
    }

    public void setSubscribed(String subscribed) {
        if (!ListenerUtil.mutListener.listen(1710)) {
            mSubscribed = StringUtils.notNullStr(subscribed);
        }
    }

    /*
     * converts iso8601 subscribed date to an actual java date
     */
    private transient java.util.Date mDateSubscribed;

    public java.util.Date getDateSubscribed() {
        if (!ListenerUtil.mutListener.listen(1712)) {
            if (mDateSubscribed == null) {
                if (!ListenerUtil.mutListener.listen(1711)) {
                    mDateSubscribed = DateTimeUtils.dateFromIso8601(mSubscribed);
                }
            }
        }
        return mDateSubscribed;
    }

    public PersonType getPersonType() {
        return mPersonType;
    }

    public void setPersonType(PersonType personType) {
        if (!ListenerUtil.mutListener.listen(1713)) {
            mPersonType = personType;
        }
    }
}
