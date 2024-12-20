package org.wordpress.android.models;

import android.text.TextUtils;
import org.wordpress.android.ui.Organization;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.StringUtils;
import java.io.Serializable;
import java.util.Locale;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderTag implements Serializable, FilterCriteria {

    public static final String FOLLOWING_PATH = "/read/following";

    public static final String LIKED_PATH = "/read/liked";

    public static final String DISCOVER_PATH = String.format(Locale.US, "read/sites/%d/posts", ReaderConstants.DISCOVER_SITE_ID);

    public static final String TAG_TITLE_FOLLOWED_SITES = "Followed Sites";

    public static final String TAG_SLUG_P2 = "p2";

    public static final String TAG_SLUG_BOOKMARKED = "bookmarked-posts";

    public static final String TAG_TITLE_DEFAULT = TAG_TITLE_FOLLOWED_SITES;

    public static final String TAG_ENDPOINT_DEFAULT = FOLLOWING_PATH;

    // tag for API calls
    private String mTagSlug;

    // tag for display, usually the same as the slug
    private String mTagDisplayName;

    // title, used for default tags
    private String mTagTitle;

    // endpoint for updating posts with this tag
    private String mEndpoint;

    private boolean mIsDefaultInMemoryTag;

    public final ReaderTagType tagType;

    public ReaderTag(String slug, String displayName, String title, String endpoint, ReaderTagType tagType) {
        this(slug, displayName, title, endpoint, tagType, false);
    }

    public ReaderTag(String slug, String displayName, String title, String endpoint, ReaderTagType tagType, boolean isDefaultInMemoryTag) {
        if (!ListenerUtil.mutListener.listen(2482)) {
            // primary key in the tag table)
            if (TextUtils.isEmpty(slug)) {
                if (!ListenerUtil.mutListener.listen(2481)) {
                    if (tagType == ReaderTagType.BOOKMARKED) {
                        if (!ListenerUtil.mutListener.listen(2480)) {
                            setTagSlug(TAG_SLUG_BOOKMARKED);
                        }
                    } else if (!TextUtils.isEmpty(title)) {
                        if (!ListenerUtil.mutListener.listen(2479)) {
                            setTagSlug(ReaderUtils.sanitizeWithDashes(title));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2478)) {
                            setTagSlug(getTagSlugFromEndpoint(endpoint));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2477)) {
                    setTagSlug(slug);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2483)) {
            setTagDisplayName(displayName);
        }
        if (!ListenerUtil.mutListener.listen(2484)) {
            setTagTitle(title);
        }
        if (!ListenerUtil.mutListener.listen(2485)) {
            setEndpoint(endpoint);
        }
        this.tagType = tagType;
        if (!ListenerUtil.mutListener.listen(2486)) {
            mIsDefaultInMemoryTag = isDefaultInMemoryTag;
        }
    }

    public String getEndpoint() {
        return StringUtils.notNullStr(mEndpoint);
    }

    public void setEndpoint(String endpoint) {
        if (!ListenerUtil.mutListener.listen(2487)) {
            this.mEndpoint = StringUtils.notNullStr(endpoint);
        }
    }

    public String getTagTitle() {
        return StringUtils.notNullStr(mTagTitle);
    }

    public void setTagTitle(String title) {
        if (!ListenerUtil.mutListener.listen(2488)) {
            this.mTagTitle = StringUtils.notNullStr(title);
        }
    }

    private boolean hasTagTitle() {
        return !TextUtils.isEmpty(mTagTitle);
    }

    public String getTagDisplayName() {
        return StringUtils.notNullStr(mTagDisplayName);
    }

    public void setTagDisplayName(String displayName) {
        if (!ListenerUtil.mutListener.listen(2489)) {
            this.mTagDisplayName = StringUtils.notNullStr(displayName);
        }
    }

    public String getTagSlug() {
        return StringUtils.notNullStr(mTagSlug);
    }

    private void setTagSlug(String slug) {
        if (!ListenerUtil.mutListener.listen(2490)) {
            this.mTagSlug = StringUtils.notNullStr(slug);
        }
    }

    /*
     * returns the tag name for use in the application log - if this is a default tag it returns
     * the full tag name, otherwise it abbreviates the tag name since exposing followed tags
     * in the log could be considered a privacy issue
     */
    public String getTagNameForLog() {
        String tagSlug = getTagSlug();
        if (tagType == ReaderTagType.DEFAULT) {
            return tagSlug;
        } else if (tagType == ReaderTagType.BOOKMARKED) {
            return ReaderTagType.BOOKMARKED.name();
        } else if ((ListenerUtil.mutListener.listen(2495) ? (tagSlug.length() <= 6) : (ListenerUtil.mutListener.listen(2494) ? (tagSlug.length() > 6) : (ListenerUtil.mutListener.listen(2493) ? (tagSlug.length() < 6) : (ListenerUtil.mutListener.listen(2492) ? (tagSlug.length() != 6) : (ListenerUtil.mutListener.listen(2491) ? (tagSlug.length() == 6) : (tagSlug.length() >= 6))))))) {
            return tagSlug.substring(0, 3) + "...";
        } else if ((ListenerUtil.mutListener.listen(2500) ? (tagSlug.length() <= 4) : (ListenerUtil.mutListener.listen(2499) ? (tagSlug.length() > 4) : (ListenerUtil.mutListener.listen(2498) ? (tagSlug.length() < 4) : (ListenerUtil.mutListener.listen(2497) ? (tagSlug.length() != 4) : (ListenerUtil.mutListener.listen(2496) ? (tagSlug.length() == 4) : (tagSlug.length() >= 4))))))) {
            return tagSlug.substring(0, 2) + "...";
        } else if ((ListenerUtil.mutListener.listen(2505) ? (tagSlug.length() <= 2) : (ListenerUtil.mutListener.listen(2504) ? (tagSlug.length() > 2) : (ListenerUtil.mutListener.listen(2503) ? (tagSlug.length() < 2) : (ListenerUtil.mutListener.listen(2502) ? (tagSlug.length() != 2) : (ListenerUtil.mutListener.listen(2501) ? (tagSlug.length() == 2) : (tagSlug.length() >= 2))))))) {
            return tagSlug.substring(0, 1) + "...";
        } else {
            return "...";
        }
    }

    /*
     * used to ensure a tag name is valid before adding it
     */
    @SuppressWarnings("RegExpRedundantEscape")
    private static final Pattern INVALID_CHARS = Pattern.compile("^.*[~#@*+%{}<>\\[\\]|\"\\_].*$");

    public static boolean isValidTagName(String tagName) {
        return (ListenerUtil.mutListener.listen(2506) ? (!TextUtils.isEmpty(tagName) || !INVALID_CHARS.matcher(tagName).matches()) : (!TextUtils.isEmpty(tagName) && !INVALID_CHARS.matcher(tagName).matches()));
    }

    /*
     * extracts the tag slug from a valid read/tags/[mTagSlug]/posts endpoint
     */
    private static String getTagSlugFromEndpoint(final String endpoint) {
        if (!ListenerUtil.mutListener.listen(2507)) {
            if (TextUtils.isEmpty(endpoint)) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(2508)) {
            // make sure passed endpoint is valid
            if (!endpoint.endsWith("/posts")) {
                return "";
            }
        }
        int start = endpoint.indexOf("/read/tags/");
        if (!ListenerUtil.mutListener.listen(2514)) {
            if ((ListenerUtil.mutListener.listen(2513) ? (start >= -1) : (ListenerUtil.mutListener.listen(2512) ? (start <= -1) : (ListenerUtil.mutListener.listen(2511) ? (start > -1) : (ListenerUtil.mutListener.listen(2510) ? (start < -1) : (ListenerUtil.mutListener.listen(2509) ? (start != -1) : (start == -1))))))) {
                return "";
            }
        }
        if (!ListenerUtil.mutListener.listen(2515)) {
            // skip "/read/tags/" then find the next "/"
            start += 11;
        }
        int end = endpoint.indexOf("/", start);
        if (!ListenerUtil.mutListener.listen(2521)) {
            if ((ListenerUtil.mutListener.listen(2520) ? (end >= -1) : (ListenerUtil.mutListener.listen(2519) ? (end <= -1) : (ListenerUtil.mutListener.listen(2518) ? (end > -1) : (ListenerUtil.mutListener.listen(2517) ? (end < -1) : (ListenerUtil.mutListener.listen(2516) ? (end != -1) : (end == -1))))))) {
                return "";
            }
        }
        return endpoint.substring(start, end);
    }

    public static boolean isSameTag(ReaderTag tag1, ReaderTag tag2) {
        return (ListenerUtil.mutListener.listen(2524) ? ((ListenerUtil.mutListener.listen(2523) ? ((ListenerUtil.mutListener.listen(2522) ? (tag1 != null || tag2 != null) : (tag1 != null && tag2 != null)) || tag1.tagType == tag2.tagType) : ((ListenerUtil.mutListener.listen(2522) ? (tag1 != null || tag2 != null) : (tag1 != null && tag2 != null)) && tag1.tagType == tag2.tagType)) || tag1.getTagSlug().equalsIgnoreCase(tag2.getTagSlug())) : ((ListenerUtil.mutListener.listen(2523) ? ((ListenerUtil.mutListener.listen(2522) ? (tag1 != null || tag2 != null) : (tag1 != null && tag2 != null)) || tag1.tagType == tag2.tagType) : ((ListenerUtil.mutListener.listen(2522) ? (tag1 != null || tag2 != null) : (tag1 != null && tag2 != null)) && tag1.tagType == tag2.tagType)) && tag1.getTagSlug().equalsIgnoreCase(tag2.getTagSlug())));
    }

    public boolean isPostsILike() {
        return (ListenerUtil.mutListener.listen(2525) ? (tagType == ReaderTagType.DEFAULT || getEndpoint().endsWith(LIKED_PATH)) : (tagType == ReaderTagType.DEFAULT && getEndpoint().endsWith(LIKED_PATH)));
    }

    public boolean isFollowedSites() {
        return (ListenerUtil.mutListener.listen(2526) ? (tagType == ReaderTagType.DEFAULT || getEndpoint().endsWith(FOLLOWING_PATH)) : (tagType == ReaderTagType.DEFAULT && getEndpoint().endsWith(FOLLOWING_PATH)));
    }

    public boolean isDefaultInMemoryTag() {
        return (ListenerUtil.mutListener.listen(2527) ? (tagType == ReaderTagType.DEFAULT || mIsDefaultInMemoryTag) : (tagType == ReaderTagType.DEFAULT && mIsDefaultInMemoryTag));
    }

    public boolean isBookmarked() {
        return tagType == ReaderTagType.BOOKMARKED;
    }

    public boolean isDiscover() {
        return (ListenerUtil.mutListener.listen(2528) ? (tagType == ReaderTagType.DEFAULT || getEndpoint().endsWith(DISCOVER_PATH)) : (tagType == ReaderTagType.DEFAULT && getEndpoint().endsWith(DISCOVER_PATH)));
    }

    public boolean isTagTopic() {
        String endpoint = getEndpoint();
        return endpoint.toLowerCase(Locale.ROOT).contains("/read/tags/");
    }

    public boolean isP2() {
        String endpoint = getEndpoint();
        return endpoint.toLowerCase(Locale.ROOT).contains("/read/following/p2");
    }

    public boolean isA8C() {
        String endpoint = getEndpoint();
        return endpoint.toLowerCase(Locale.ROOT).contains("/read/a8c");
    }

    public boolean isFilterable() {
        return (ListenerUtil.mutListener.listen(2530) ? ((ListenerUtil.mutListener.listen(2529) ? (this.isFollowedSites() && this.isA8C()) : (this.isFollowedSites() || this.isA8C())) && this.isP2()) : ((ListenerUtil.mutListener.listen(2529) ? (this.isFollowedSites() && this.isA8C()) : (this.isFollowedSites() || this.isA8C())) || this.isP2()));
    }

    public boolean isListTopic() {
        String endpoint = getEndpoint();
        return endpoint.toLowerCase(Locale.ROOT).contains("/read/list/");
    }

    public Organization getOrganization() {
        if (this.isA8C()) {
            return Organization.A8C;
        } else if (this.isP2()) {
            return Organization.P2;
        } else if ((ListenerUtil.mutListener.listen(2531) ? (this.isFollowedSites() && this.isDefaultInMemoryTag()) : (this.isFollowedSites() || this.isDefaultInMemoryTag()))) {
            return Organization.NO_ORGANIZATION;
        } else {
            return Organization.UNKNOWN;
        }
    }

    public String getKeyString() {
        return tagType.toInt() + getTagSlug();
    }

    /*
     * the label is the text displayed in the dropdown filter
     */
    @Override
    public String getLabel() {
        if (isTagDisplayNameAlphaNumeric()) {
            return getTagDisplayName().toLowerCase(Locale.ROOT);
        } else if (hasTagTitle()) {
            return getTagTitle();
        } else {
            return getTagDisplayName();
        }
    }

    /*
     * returns true if the tag display name contains only alpha-numeric characters or hyphens
     */
    private boolean isTagDisplayNameAlphaNumeric() {
        if (!ListenerUtil.mutListener.listen(2532)) {
            if (TextUtils.isEmpty(mTagDisplayName)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(2540)) {
            {
                long _loopCounter92 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2539) ? (i >= mTagDisplayName.length()) : (ListenerUtil.mutListener.listen(2538) ? (i <= mTagDisplayName.length()) : (ListenerUtil.mutListener.listen(2537) ? (i > mTagDisplayName.length()) : (ListenerUtil.mutListener.listen(2536) ? (i != mTagDisplayName.length()) : (ListenerUtil.mutListener.listen(2535) ? (i == mTagDisplayName.length()) : (i < mTagDisplayName.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter92", ++_loopCounter92);
                    char c = mTagDisplayName.charAt(i);
                    if (!ListenerUtil.mutListener.listen(2534)) {
                        if ((ListenerUtil.mutListener.listen(2533) ? (!Character.isLetterOrDigit(c) || c != '-') : (!Character.isLetterOrDigit(c) && c != '-'))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Local only tag used to identify post cards for the "discover" tab in the Reader.
     */
    public static ReaderTag createDiscoverPostCardsTag() {
        return new ReaderTag("", "", "", "", ReaderTagType.DISCOVER_POST_CARDS);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ReaderTag) {
            ReaderTag tag = (ReaderTag) object;
            return ((ListenerUtil.mutListener.listen(2541) ? (tag.tagType == this.tagType || tag.getLabel().equals(this.getLabel())) : (tag.tagType == this.tagType && tag.getLabel().equals(this.getLabel()))));
        } else {
            return false;
        }
    }
}
