package org.wordpress.android.ui.reader.models;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.HtmlUtils;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.PhotonUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * simplified version of a reader post
 */
public class ReaderSimplePost {

    private long mPostId;

    private long mSiteId;

    private String mTitle;

    private String mExcerpt;

    private String mSiteName;

    private String mFeaturedImageUrl;

    private transient String mFeaturedImageForDisplay;

    private String mRailcarJson;

    /*
       these are the specific fields we should ask for when requesting simple posts from
       the endpoint - note that we want to avoid ever requesting the post content, since
       that makes the call much heavier
    */
    public static final String SIMPLE_POST_FIELDS = "ID,site_ID,title,excerpt,site_name,featured_image,featured_media,railcar";

    public static ReaderSimplePost fromJson(JSONObject json) {
        if (!ListenerUtil.mutListener.listen(19077)) {
            if (json == null) {
                throw new IllegalArgumentException("ReaderSimplePost requires a non-null JSONObject");
            }
        }
        ReaderSimplePost post = new ReaderSimplePost();
        // ID and site_ID are required, so make sure we have them
        try {
            if (!ListenerUtil.mutListener.listen(19079)) {
                post.mPostId = json.getLong("ID");
            }
            if (!ListenerUtil.mutListener.listen(19080)) {
                post.mSiteId = json.getLong("site_ID");
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(19078)) {
                AppLog.e(AppLog.T.READER, e);
            }
            return null;
        }
        if (!ListenerUtil.mutListener.listen(19081)) {
            post.mTitle = JSONUtils.getStringDecoded(json, "title");
        }
        if (!ListenerUtil.mutListener.listen(19082)) {
            post.mExcerpt = HtmlUtils.fastStripHtml(JSONUtils.getString(json, "excerpt")).trim();
        }
        if (!ListenerUtil.mutListener.listen(19083)) {
            post.mSiteName = JSONUtils.getStringDecoded(json, "site_name");
        }
        if (!ListenerUtil.mutListener.listen(19084)) {
            post.mFeaturedImageUrl = JSONUtils.getString(json, "featured_image");
        }
        if (!ListenerUtil.mutListener.listen(19088)) {
            // if there's no featured image, check if featured media has been set to an image
            if ((ListenerUtil.mutListener.listen(19085) ? (!post.hasFeaturedImageUrl() || json.has("featured_media")) : (!post.hasFeaturedImageUrl() && json.has("featured_media")))) {
                JSONObject jsonMedia = json.optJSONObject("featured_media");
                String type = JSONUtils.getString(jsonMedia, "type");
                if (!ListenerUtil.mutListener.listen(19087)) {
                    if (type.equals("image")) {
                        if (!ListenerUtil.mutListener.listen(19086)) {
                            post.mFeaturedImageUrl = JSONUtils.getString(jsonMedia, "uri");
                        }
                    }
                }
            }
        }
        JSONObject jsonRailcar = json.optJSONObject("railcar");
        if (!ListenerUtil.mutListener.listen(19090)) {
            if (jsonRailcar != null) {
                if (!ListenerUtil.mutListener.listen(19089)) {
                    post.mRailcarJson = jsonRailcar.toString();
                }
            }
        }
        return post;
    }

    public long getPostId() {
        return mPostId;
    }

    public long getSiteId() {
        return mSiteId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getExcerpt() {
        return mExcerpt;
    }

    public String getSiteName() {
        return mSiteName;
    }

    public String getFeaturedImageUrl() {
        return mFeaturedImageUrl;
    }

    public String getRailcarJson() {
        return mRailcarJson;
    }

    public boolean hasExcerpt() {
        return !TextUtils.isEmpty(mExcerpt);
    }

    public boolean hasTitle() {
        return !TextUtils.isEmpty(mTitle);
    }

    public boolean hasFeaturedImageUrl() {
        return !TextUtils.isEmpty(mFeaturedImageUrl);
    }

    public String getFeaturedImageForDisplay(int width, int height) {
        if (!ListenerUtil.mutListener.listen(19094)) {
            if (mFeaturedImageForDisplay == null) {
                if (!ListenerUtil.mutListener.listen(19093)) {
                    if (!hasFeaturedImageUrl()) {
                        if (!ListenerUtil.mutListener.listen(19092)) {
                            mFeaturedImageForDisplay = "";
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(19091)) {
                            mFeaturedImageForDisplay = PhotonUtils.getPhotonImageUrl(getFeaturedImageUrl(), width, height);
                        }
                    }
                }
            }
        }
        return mFeaturedImageForDisplay;
    }
}
