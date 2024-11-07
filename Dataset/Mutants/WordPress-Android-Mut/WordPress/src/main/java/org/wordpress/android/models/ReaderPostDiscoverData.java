package org.wordpress.android.models;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.StringUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * additional data for "discover" posts in the reader - these are posts chosen by
 * Editorial which highlight other posts or sites - the reader shows an attribution
 * line for these posts, and when tapped they open the original post - the like
 * and comment counts come from the original post
 */
public class ReaderPostDiscoverData {

    public enum DiscoverType {

        EDITOR_PICK, SITE_PICK, OTHER
    }

    private String mAuthorName;

    private String mAuthorUrl;

    private String mBlogName;

    private String mBlogUrl;

    private String mAvatarUrl;

    private final String mPermaLink;

    private long mBlogId;

    private long mPostId;

    private int mNumLikes;

    private int mNumComments;

    private DiscoverType mDiscoverType = DiscoverType.OTHER;

    /*
     * passed JSONObject is the "discover_metadata" section of a reader post
     */
    public ReaderPostDiscoverData(@NonNull JSONObject json) {
        mPermaLink = json.optString("permalink");
        JSONObject jsonAttribution = json.optJSONObject("attribution");
        if (!ListenerUtil.mutListener.listen(2388)) {
            if (jsonAttribution != null) {
                if (!ListenerUtil.mutListener.listen(2383)) {
                    mAuthorName = jsonAttribution.optString("author_name");
                }
                if (!ListenerUtil.mutListener.listen(2384)) {
                    mAuthorUrl = jsonAttribution.optString("author_url");
                }
                if (!ListenerUtil.mutListener.listen(2385)) {
                    mBlogName = jsonAttribution.optString("blog_name");
                }
                if (!ListenerUtil.mutListener.listen(2386)) {
                    mBlogUrl = jsonAttribution.optString("blog_url");
                }
                if (!ListenerUtil.mutListener.listen(2387)) {
                    mAvatarUrl = jsonAttribution.optString("avatar_url");
                }
            }
        }
        JSONObject jsonWpcomData = json.optJSONObject("featured_post_wpcom_data");
        if (!ListenerUtil.mutListener.listen(2393)) {
            if (jsonWpcomData != null) {
                if (!ListenerUtil.mutListener.listen(2389)) {
                    mBlogId = jsonWpcomData.optLong("blog_id");
                }
                if (!ListenerUtil.mutListener.listen(2390)) {
                    mPostId = jsonWpcomData.optLong("post_id");
                }
                if (!ListenerUtil.mutListener.listen(2391)) {
                    mNumLikes = jsonWpcomData.optInt("like_count");
                }
                if (!ListenerUtil.mutListener.listen(2392)) {
                    mNumComments = jsonWpcomData.optInt("comment_count");
                }
            }
        }
        // - collection + feature can be ignored because those display the same as normal posts
        JSONArray jsonPostFormats = json.optJSONArray("discover_fp_post_formats");
        if (!ListenerUtil.mutListener.listen(2405)) {
            if (jsonPostFormats != null) {
                if (!ListenerUtil.mutListener.listen(2404)) {
                    {
                        long _loopCounter85 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(2403) ? (i >= jsonPostFormats.length()) : (ListenerUtil.mutListener.listen(2402) ? (i <= jsonPostFormats.length()) : (ListenerUtil.mutListener.listen(2401) ? (i > jsonPostFormats.length()) : (ListenerUtil.mutListener.listen(2400) ? (i != jsonPostFormats.length()) : (ListenerUtil.mutListener.listen(2399) ? (i == jsonPostFormats.length()) : (i < jsonPostFormats.length())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter85", ++_loopCounter85);
                            String slug = JSONUtils.getString(jsonPostFormats.optJSONObject(i), "slug");
                            if (!ListenerUtil.mutListener.listen(2398)) {
                                if (slug.equals("site-pick")) {
                                    if (!ListenerUtil.mutListener.listen(2397)) {
                                        mDiscoverType = DiscoverType.SITE_PICK;
                                    }
                                    break;
                                } else if ((ListenerUtil.mutListener.listen(2395) ? ((ListenerUtil.mutListener.listen(2394) ? (slug.equals("standard-pick") && slug.equals("image-pick")) : (slug.equals("standard-pick") || slug.equals("image-pick"))) && slug.equals("quote-pick")) : ((ListenerUtil.mutListener.listen(2394) ? (slug.equals("standard-pick") && slug.equals("image-pick")) : (slug.equals("standard-pick") || slug.equals("image-pick"))) || slug.equals("quote-pick")))) {
                                    if (!ListenerUtil.mutListener.listen(2396)) {
                                        mDiscoverType = DiscoverType.EDITOR_PICK;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public long getBlogId() {
        return mBlogId;
    }

    public long getPostId() {
        return mPostId;
    }

    private String getAuthorName() {
        return StringUtils.notNullStr(mAuthorName);
    }

    private String getAuthorUrl() {
        return StringUtils.notNullStr(mAuthorUrl);
    }

    public String getBlogName() {
        return StringUtils.notNullStr(mBlogName);
    }

    public String getBlogUrl() {
        return StringUtils.notNullStr(mBlogUrl);
    }

    public String getAvatarUrl() {
        return StringUtils.notNullStr(mAvatarUrl);
    }

    public String getPermaLink() {
        return StringUtils.notNullStr(mPermaLink);
    }

    public boolean hasBlogUrl() {
        return !TextUtils.isEmpty(mBlogUrl);
    }

    public boolean hasBlogName() {
        return !TextUtils.isEmpty(mBlogName);
    }

    private boolean hasAuthorName() {
        return !TextUtils.isEmpty(mAuthorName);
    }

    public boolean hasPermalink() {
        return !TextUtils.isEmpty(mPermaLink);
    }

    public boolean hasAvatarUrl() {
        return !TextUtils.isEmpty(mAvatarUrl);
    }

    public DiscoverType getDiscoverType() {
        return mDiscoverType;
    }

    /*
     * returns the spanned html for the attribution line
     */
    private transient Spanned mAttributionHtml;

    @NonNull
    public Spanned getAttributionHtml() {
        if (!ListenerUtil.mutListener.listen(2414)) {
            if (mAttributionHtml == null) {
                String html;
                String author = "<strong>" + getAuthorName() + "</strong>";
                String blog = "<strong>" + getBlogName() + "</strong>";
                Context context = WordPress.getContext();
                switch(getDiscoverType()) {
                    case EDITOR_PICK:
                        if ((ListenerUtil.mutListener.listen(2406) ? (hasBlogName() || hasAuthorName()) : (hasBlogName() && hasAuthorName()))) {
                            // "Originally posted by [AuthorName] on [BlogName]"
                            html = String.format(context.getString(R.string.reader_discover_attribution_author_and_blog), author, blog);
                        } else if (hasBlogName()) {
                            // "Originally posted on [BlogName]"
                            html = String.format(context.getString(R.string.reader_discover_attribution_blog), blog);
                        } else if (hasAuthorName()) {
                            // "Originally posted by [AuthorName]"
                            html = String.format(context.getString(R.string.reader_discover_attribution_author), author);
                        } else {
                            html = "";
                        }
                        break;
                    case SITE_PICK:
                        if ((ListenerUtil.mutListener.listen(2412) ? ((ListenerUtil.mutListener.listen(2411) ? (mBlogId >= 0) : (ListenerUtil.mutListener.listen(2410) ? (mBlogId <= 0) : (ListenerUtil.mutListener.listen(2409) ? (mBlogId > 0) : (ListenerUtil.mutListener.listen(2408) ? (mBlogId < 0) : (ListenerUtil.mutListener.listen(2407) ? (mBlogId == 0) : (mBlogId != 0)))))) || hasBlogName()) : ((ListenerUtil.mutListener.listen(2411) ? (mBlogId >= 0) : (ListenerUtil.mutListener.listen(2410) ? (mBlogId <= 0) : (ListenerUtil.mutListener.listen(2409) ? (mBlogId > 0) : (ListenerUtil.mutListener.listen(2408) ? (mBlogId < 0) : (ListenerUtil.mutListener.listen(2407) ? (mBlogId == 0) : (mBlogId != 0)))))) && hasBlogName()))) {
                            // "Visit [BlogName]" - opens blog preview when tapped
                            html = String.format(context.getString(R.string.reader_discover_visit_blog), blog);
                        } else {
                            html = "";
                        }
                        break;
                    default:
                        html = "";
                }
                if (!ListenerUtil.mutListener.listen(2413)) {
                    mAttributionHtml = Html.fromHtml(html);
                }
            }
        }
        return mAttributionHtml;
    }
}
