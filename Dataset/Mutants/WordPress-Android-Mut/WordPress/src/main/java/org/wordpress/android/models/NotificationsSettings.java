package org.wordpress.android.models;

import androidx.collection.LongSparseArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.JSONUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// Maps to notification settings returned from the /me/notifications/settings endpoint on wp.com
public class NotificationsSettings {

    public static final String KEY_BLOGS = "blogs";

    public static final String KEY_OTHER = "other";

    public static final String KEY_WPCOM = "wpcom";

    public static final String KEY_DEVICES = "devices";

    public static final String KEY_DEVICE_ID = "device_id";

    public static final String KEY_BLOG_ID = "blog_id";

    private JSONObject mOtherSettings;

    private JSONObject mWPComSettings;

    private LongSparseArray<JSONObject> mBlogSettings;

    // The main notification settings channels (displayed at root of NoticationsSettingsFragment)
    public enum Channel {

        OTHER, BLOGS, WPCOM
    }

    // The notification setting type, used in BLOGS and OTHER channels
    public enum Type {

        TIMELINE, EMAIL, DEVICE;

        public String toString() {
            switch(this) {
                case TIMELINE:
                    return "timeline";
                case EMAIL:
                    return "email";
                case DEVICE:
                    return "device";
                default:
                    return "";
            }
        }
    }

    public NotificationsSettings(JSONObject json) {
        if (!ListenerUtil.mutListener.listen(1636)) {
            updateJson(json);
        }
    }

    // Parses the json response from /me/notifications/settings endpoint and updates the instance variables
    public void updateJson(JSONObject json) {
        if (!ListenerUtil.mutListener.listen(1637)) {
            mBlogSettings = new LongSparseArray<>();
        }
        if (!ListenerUtil.mutListener.listen(1638)) {
            mOtherSettings = JSONUtils.queryJSON(json, KEY_OTHER, new JSONObject());
        }
        if (!ListenerUtil.mutListener.listen(1639)) {
            mWPComSettings = JSONUtils.queryJSON(json, KEY_WPCOM, new JSONObject());
        }
        JSONArray siteSettingsArray = JSONUtils.queryJSON(json, KEY_BLOGS, new JSONArray());
        if (!ListenerUtil.mutListener.listen(1647)) {
            {
                long _loopCounter63 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1646) ? (i >= siteSettingsArray.length()) : (ListenerUtil.mutListener.listen(1645) ? (i <= siteSettingsArray.length()) : (ListenerUtil.mutListener.listen(1644) ? (i > siteSettingsArray.length()) : (ListenerUtil.mutListener.listen(1643) ? (i != siteSettingsArray.length()) : (ListenerUtil.mutListener.listen(1642) ? (i == siteSettingsArray.length()) : (i < siteSettingsArray.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter63", ++_loopCounter63);
                    try {
                        JSONObject siteSetting = siteSettingsArray.getJSONObject(i);
                        if (!ListenerUtil.mutListener.listen(1641)) {
                            mBlogSettings.put(siteSetting.optLong(KEY_BLOG_ID), siteSetting);
                        }
                    } catch (JSONException e) {
                        if (!ListenerUtil.mutListener.listen(1640)) {
                            AppLog.e(AppLog.T.NOTIFS, "Could not parse blog JSON in notification settings");
                        }
                    }
                }
            }
        }
    }

    // Updates a specific notification setting after a user makes a change
    public void updateSettingForChannelAndType(Channel channel, Type type, String settingName, boolean newValue, long blogId) {
        String typeName = type.toString();
        try {
            if (!ListenerUtil.mutListener.listen(1656)) {
                switch(channel) {
                    case BLOGS:
                        JSONObject blogJson = getBlogSettings().get(blogId);
                        if (!ListenerUtil.mutListener.listen(1652)) {
                            if (blogJson != null) {
                                JSONObject blogSetting = JSONUtils.queryJSON(blogJson, typeName, new JSONObject());
                                if (!ListenerUtil.mutListener.listen(1649)) {
                                    blogSetting.put(settingName, newValue);
                                }
                                if (!ListenerUtil.mutListener.listen(1650)) {
                                    blogJson.put(typeName, blogSetting);
                                }
                                if (!ListenerUtil.mutListener.listen(1651)) {
                                    getBlogSettings().put(blogId, blogJson);
                                }
                            }
                        }
                        break;
                    case OTHER:
                        JSONObject otherSetting = JSONUtils.queryJSON(getOtherSettings(), typeName, new JSONObject());
                        if (!ListenerUtil.mutListener.listen(1653)) {
                            otherSetting.put(settingName, newValue);
                        }
                        if (!ListenerUtil.mutListener.listen(1654)) {
                            getOtherSettings().put(typeName, otherSetting);
                        }
                        break;
                    case WPCOM:
                        if (!ListenerUtil.mutListener.listen(1655)) {
                            getWPComSettings().put(settingName, newValue);
                        }
                }
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(1648)) {
                AppLog.e(AppLog.T.NOTIFS, "Could not update notifications settings JSON");
            }
        }
    }

    public JSONObject getOtherSettings() {
        return mOtherSettings;
    }

    public LongSparseArray<JSONObject> getBlogSettings() {
        return mBlogSettings;
    }

    public JSONObject getWPComSettings() {
        return mWPComSettings;
    }

    // Returns settings json for the given {@link Channel}, {@link Type} and optional blog id
    public JSONObject getSettingsJsonForChannelAndType(Channel channel, Type type, long blogId) {
        JSONObject settingsJson = null;
        String typeString = type.toString();
        if (!ListenerUtil.mutListener.listen(1666)) {
            switch(channel) {
                case BLOGS:
                    if (!ListenerUtil.mutListener.listen(1663)) {
                        if ((ListenerUtil.mutListener.listen(1661) ? (blogId >= -1) : (ListenerUtil.mutListener.listen(1660) ? (blogId <= -1) : (ListenerUtil.mutListener.listen(1659) ? (blogId > -1) : (ListenerUtil.mutListener.listen(1658) ? (blogId < -1) : (ListenerUtil.mutListener.listen(1657) ? (blogId == -1) : (blogId != -1))))))) {
                            if (!ListenerUtil.mutListener.listen(1662)) {
                                settingsJson = JSONUtils.queryJSON(getBlogSettings().get(blogId), typeString, new JSONObject());
                            }
                        }
                    }
                    break;
                case OTHER:
                    if (!ListenerUtil.mutListener.listen(1664)) {
                        settingsJson = JSONUtils.queryJSON(getOtherSettings(), typeString, new JSONObject());
                    }
                    break;
                case WPCOM:
                    if (!ListenerUtil.mutListener.listen(1665)) {
                        settingsJson = getWPComSettings();
                    }
                    break;
            }
        }
        return settingsJson;
    }

    /**
     * Determines if the main switch should be displayed on a notifications settings preference screen
     * for the given {@link Channel} and {@link Type}
     *
     * @param channel The {@link Channel}
     * @param type The {@link Type}
     * @return A flag indicating whether main switch should be displayed.
     */
    public boolean shouldDisplayMainSwitch(Channel channel, Type type) {
        boolean displayMainSwitch = false;
        if (!ListenerUtil.mutListener.listen(1669)) {
            switch(channel) {
                case BLOGS:
                    if (!ListenerUtil.mutListener.listen(1668)) {
                        if (type == Type.TIMELINE) {
                            if (!ListenerUtil.mutListener.listen(1667)) {
                                displayMainSwitch = true;
                            }
                        }
                    }
                    break;
                case OTHER:
                case WPCOM:
                default:
                    break;
            }
        }
        return displayMainSwitch;
    }

    /**
     * Finds if at least one notifications settings value is enabled in the given json
     *
     * @param settingsJson The settings json
     * @param settingsArray The string array of settings display names
     * @param settingsValues The string array of settings json keys
     * @return A flag indicating if at least one settings option is enabled.
     */
    public boolean isAtLeastOneSettingsEnabled(JSONObject settingsJson, String[] settingsArray, String[] settingsValues) {
        if (!ListenerUtil.mutListener.listen(1683)) {
            if ((ListenerUtil.mutListener.listen(1675) ? (settingsJson != null || (ListenerUtil.mutListener.listen(1674) ? (settingsArray.length >= settingsValues.length) : (ListenerUtil.mutListener.listen(1673) ? (settingsArray.length <= settingsValues.length) : (ListenerUtil.mutListener.listen(1672) ? (settingsArray.length > settingsValues.length) : (ListenerUtil.mutListener.listen(1671) ? (settingsArray.length < settingsValues.length) : (ListenerUtil.mutListener.listen(1670) ? (settingsArray.length != settingsValues.length) : (settingsArray.length == settingsValues.length))))))) : (settingsJson != null && (ListenerUtil.mutListener.listen(1674) ? (settingsArray.length >= settingsValues.length) : (ListenerUtil.mutListener.listen(1673) ? (settingsArray.length <= settingsValues.length) : (ListenerUtil.mutListener.listen(1672) ? (settingsArray.length > settingsValues.length) : (ListenerUtil.mutListener.listen(1671) ? (settingsArray.length < settingsValues.length) : (ListenerUtil.mutListener.listen(1670) ? (settingsArray.length != settingsValues.length) : (settingsArray.length == settingsValues.length))))))))) {
                if (!ListenerUtil.mutListener.listen(1682)) {
                    {
                        long _loopCounter64 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(1681) ? (i >= settingsArray.length) : (ListenerUtil.mutListener.listen(1680) ? (i <= settingsArray.length) : (ListenerUtil.mutListener.listen(1679) ? (i > settingsArray.length) : (ListenerUtil.mutListener.listen(1678) ? (i != settingsArray.length) : (ListenerUtil.mutListener.listen(1677) ? (i == settingsArray.length) : (i < settingsArray.length)))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter64", ++_loopCounter64);
                            String settingValue = settingsValues[i];
                            boolean isChecked = JSONUtils.queryJSON(settingsJson, settingValue, true);
                            if (!ListenerUtil.mutListener.listen(1676)) {
                                if (isChecked) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
