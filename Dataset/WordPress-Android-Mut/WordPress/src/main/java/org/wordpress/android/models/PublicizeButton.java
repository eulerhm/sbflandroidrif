package org.wordpress.android.models;

import org.json.JSONException;
import org.json.JSONObject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeButton {

    private static final String ID_KEY = "ID";

    private static final String NAME_KEY = "name";

    private static final String SHORT_NAME_KEY = "shortname";

    private static final String CUSTOM_KEY = "custom";

    private static final String ENABLED_KEY = "enabled";

    private static final String VISIBILITY_KEY = "visibility";

    private static final String GENERICON_KEY = "genericon";

    public static final String VISIBLE = "visible";

    public static final String HIDDEN = "hidden";

    private String mId;

    private String mName;

    private String mShortName;

    private boolean mIsCustom;

    private boolean mIsEnabled;

    private String mVisibility;

    private String mGenericon;

    public PublicizeButton(JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(1714)) {
            mId = jsonObject.optString(ID_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(1715)) {
            mName = jsonObject.optString(NAME_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(1716)) {
            mShortName = jsonObject.optString(SHORT_NAME_KEY, "");
        }
        if (!ListenerUtil.mutListener.listen(1717)) {
            mIsCustom = jsonObject.optBoolean(CUSTOM_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(1718)) {
            mIsEnabled = jsonObject.optBoolean(ENABLED_KEY, false);
        }
        if (!ListenerUtil.mutListener.listen(1719)) {
            mVisibility = jsonObject.optString(VISIBILITY_KEY, VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1720)) {
            mGenericon = jsonObject.optString(GENERICON_KEY, "");
        }
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            if (!ListenerUtil.mutListener.listen(1722)) {
                jsonObject.put(ID_KEY, mId);
            }
            if (!ListenerUtil.mutListener.listen(1723)) {
                jsonObject.put(NAME_KEY, mName);
            }
            if (!ListenerUtil.mutListener.listen(1724)) {
                jsonObject.put(SHORT_NAME_KEY, mShortName);
            }
            if (!ListenerUtil.mutListener.listen(1725)) {
                jsonObject.put(CUSTOM_KEY, mIsCustom);
            }
            if (!ListenerUtil.mutListener.listen(1726)) {
                jsonObject.put(ENABLED_KEY, mIsEnabled);
            }
            if (!ListenerUtil.mutListener.listen(1727)) {
                jsonObject.put(VISIBILITY_KEY, mVisibility);
            }
            if (!ListenerUtil.mutListener.listen(1728)) {
                jsonObject.put(GENERICON_KEY, mGenericon);
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(1721)) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        if (!ListenerUtil.mutListener.listen(1729)) {
            this.mId = id;
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(1730)) {
            this.mName = name;
        }
    }

    public String getShortName() {
        return mShortName;
    }

    public void setShortName(String shortName) {
        if (!ListenerUtil.mutListener.listen(1731)) {
            this.mShortName = shortName;
        }
    }

    public boolean isCustom() {
        return mIsCustom;
    }

    public void setCustom(boolean custom) {
        if (!ListenerUtil.mutListener.listen(1732)) {
            mIsCustom = custom;
        }
    }

    public boolean isEnabled() {
        return mIsEnabled;
    }

    public void setEnabled(boolean enabled) {
        if (!ListenerUtil.mutListener.listen(1733)) {
            mIsEnabled = enabled;
        }
    }

    public String getVisibility() {
        return mVisibility;
    }

    public boolean isVisible() {
        return mVisibility.equals(VISIBLE);
    }

    public void setVisibility(boolean isVisible) {
        if (!ListenerUtil.mutListener.listen(1736)) {
            if (isVisible) {
                if (!ListenerUtil.mutListener.listen(1735)) {
                    mVisibility = VISIBLE;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1734)) {
                    mVisibility = HIDDEN;
                }
            }
        }
    }

    public void setVisibility(String visibility) {
        if (!ListenerUtil.mutListener.listen(1737)) {
            mVisibility = visibility;
        }
    }

    public String getGenericon() {
        return mGenericon;
    }

    public void setGenericon(String genericon) {
        if (!ListenerUtil.mutListener.listen(1738)) {
            this.mGenericon = genericon;
        }
    }
}
