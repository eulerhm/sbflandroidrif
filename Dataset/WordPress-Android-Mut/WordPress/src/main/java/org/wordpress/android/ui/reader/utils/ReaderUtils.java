package org.wordpress.android.ui.reader.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.datasets.ReaderCommentTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.FilteredRecyclerView;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.services.update.TagUpdateClientUtilsProvider;
import org.wordpress.android.util.FormatUtils;
import org.wordpress.android.util.LocaleManager;
import org.wordpress.android.util.PhotonUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.UrlUtils;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderUtils {

    public static String getResizedImageUrl(final String imageUrl, int width, int height, boolean isPrivate, boolean isPrivateAtomic) {
        return getResizedImageUrl(imageUrl, width, height, isPrivate, isPrivateAtomic, PhotonUtils.Quality.MEDIUM);
    }

    public static String getResizedImageUrl(final String imageUrl, int width, int height, boolean isPrivate, boolean isPrivateAtomic, PhotonUtils.Quality quality) {
        final String unescapedUrl = StringEscapeUtils.unescapeHtml4(imageUrl);
        if ((ListenerUtil.mutListener.listen(19758) ? (isPrivate || !isPrivateAtomic) : (isPrivate && !isPrivateAtomic))) {
            return getImageForDisplayWithoutPhoton(unescapedUrl, width, height, true);
        } else {
            return PhotonUtils.getPhotonImageUrl(unescapedUrl, width, height, quality, isPrivateAtomic);
        }
    }

    public static String getResizedImageUrl(final String imageUrl, int width, int height, SiteAccessibilityInfo siteAccessibilityInfo) {
        return getResizedImageUrl(imageUrl, width, height, siteAccessibilityInfo, PhotonUtils.Quality.MEDIUM);
    }

    public static String getResizedImageUrl(final String imageUrl, int width, int height, SiteAccessibilityInfo siteAccessibilityInfo, PhotonUtils.Quality quality) {
        final String unescapedUrl = StringEscapeUtils.unescapeHtml4(imageUrl);
        if (siteAccessibilityInfo.isPhotonCapable()) {
            return PhotonUtils.getPhotonImageUrl(unescapedUrl, width, height, quality, siteAccessibilityInfo.getSiteVisibility() == SiteVisibility.PRIVATE_ATOMIC);
        } else {
            return getImageForDisplayWithoutPhoton(unescapedUrl, width, height, siteAccessibilityInfo.getSiteVisibility() == SiteVisibility.PRIVATE);
        }
    }

    /*
     * use this to request a reduced size image from not photon capable sites
     * (i.e. a private post - images in private posts can't use photon
     * but these are usually wp images so they support the h= and w= query params)
     */
    private static String getImageForDisplayWithoutPhoton(final String imageUrl, int width, int height, boolean forceHttps) {
        if (TextUtils.isEmpty(imageUrl)) {
            return "";
        }
        final String query;
        if ((ListenerUtil.mutListener.listen(19769) ? ((ListenerUtil.mutListener.listen(19763) ? (width >= 0) : (ListenerUtil.mutListener.listen(19762) ? (width <= 0) : (ListenerUtil.mutListener.listen(19761) ? (width < 0) : (ListenerUtil.mutListener.listen(19760) ? (width != 0) : (ListenerUtil.mutListener.listen(19759) ? (width == 0) : (width > 0)))))) || (ListenerUtil.mutListener.listen(19768) ? (height >= 0) : (ListenerUtil.mutListener.listen(19767) ? (height <= 0) : (ListenerUtil.mutListener.listen(19766) ? (height < 0) : (ListenerUtil.mutListener.listen(19765) ? (height != 0) : (ListenerUtil.mutListener.listen(19764) ? (height == 0) : (height > 0))))))) : ((ListenerUtil.mutListener.listen(19763) ? (width >= 0) : (ListenerUtil.mutListener.listen(19762) ? (width <= 0) : (ListenerUtil.mutListener.listen(19761) ? (width < 0) : (ListenerUtil.mutListener.listen(19760) ? (width != 0) : (ListenerUtil.mutListener.listen(19759) ? (width == 0) : (width > 0)))))) && (ListenerUtil.mutListener.listen(19768) ? (height >= 0) : (ListenerUtil.mutListener.listen(19767) ? (height <= 0) : (ListenerUtil.mutListener.listen(19766) ? (height < 0) : (ListenerUtil.mutListener.listen(19765) ? (height != 0) : (ListenerUtil.mutListener.listen(19764) ? (height == 0) : (height > 0))))))))) {
            query = "?w=" + width + "&h=" + height;
        } else if ((ListenerUtil.mutListener.listen(19774) ? (width >= 0) : (ListenerUtil.mutListener.listen(19773) ? (width <= 0) : (ListenerUtil.mutListener.listen(19772) ? (width < 0) : (ListenerUtil.mutListener.listen(19771) ? (width != 0) : (ListenerUtil.mutListener.listen(19770) ? (width == 0) : (width > 0))))))) {
            query = "?w=" + width;
        } else if ((ListenerUtil.mutListener.listen(19779) ? (height >= 0) : (ListenerUtil.mutListener.listen(19778) ? (height <= 0) : (ListenerUtil.mutListener.listen(19777) ? (height < 0) : (ListenerUtil.mutListener.listen(19776) ? (height != 0) : (ListenerUtil.mutListener.listen(19775) ? (height == 0) : (height > 0))))))) {
            query = "?h=" + height;
        } else {
            query = "";
        }
        if (forceHttps) {
            // remove the existing query string, add the new one, and make sure the url is https:
            return UrlUtils.removeQuery(UrlUtils.makeHttps(imageUrl)) + query;
        } else {
            // remove the existing query string, add the new one
            return UrlUtils.removeQuery(imageUrl) + query;
        }
    }

    /*
     * returns the passed string formatted for use with our API - see sanitize_title_with_dashes
     * https://git.io/JqUEP
     * http://stackoverflow.com/a/1612015/1673548
     */
    public static String sanitizeWithDashes(final String title) {
        if (title == null) {
            return "";
        }
        String trimmedTitle = title.trim();
        if (isValidUrlEncodedString(trimmedTitle)) {
            return trimmedTitle;
        } else {
            return trimmedTitle.replaceAll("&[^\\s]*;", // remove html entities
            "").replaceAll("[\\.\\s]+", // replace periods and whitespace with a dash
            "-").replaceAll("[^\\p{L}\\p{Nd}\\-]+", // remove remaining non-alphanum/non-dash chars (Unicode aware)
            "").replaceAll("--", // reduce double dashes potentially added above
            "-");
        }
    }

    @NonNull
    private static boolean isValidUrlEncodedString(String title) {
        try {
            if (!ListenerUtil.mutListener.listen(19780)) {
                URI.create(title);
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /*
     * returns the long text to use for a like label ("Liked by 3 people", etc.)
     */
    public static String getLongLikeLabelText(Context context, int numLikes, boolean isLikedByCurrentUser) {
        if (isLikedByCurrentUser) {
            switch(numLikes) {
                case 1:
                    return context.getString(R.string.reader_likes_only_you);
                case 2:
                    return context.getString(R.string.reader_likes_you_and_one);
                default:
                    String youAndMultiLikes = context.getString(R.string.reader_likes_you_and_multi);
                    return String.format(LocaleManager.getSafeLocale(context), youAndMultiLikes, (ListenerUtil.mutListener.listen(19789) ? (numLikes % 1) : (ListenerUtil.mutListener.listen(19788) ? (numLikes / 1) : (ListenerUtil.mutListener.listen(19787) ? (numLikes * 1) : (ListenerUtil.mutListener.listen(19786) ? (numLikes + 1) : (numLikes - 1))))));
            }
        } else {
            if ((ListenerUtil.mutListener.listen(19785) ? (numLikes >= 1) : (ListenerUtil.mutListener.listen(19784) ? (numLikes <= 1) : (ListenerUtil.mutListener.listen(19783) ? (numLikes > 1) : (ListenerUtil.mutListener.listen(19782) ? (numLikes < 1) : (ListenerUtil.mutListener.listen(19781) ? (numLikes != 1) : (numLikes == 1))))))) {
                return context.getString(R.string.reader_likes_one);
            } else {
                String likes = context.getString(R.string.reader_likes_multi);
                return String.format(LocaleManager.getSafeLocale(context), likes, numLikes);
            }
        }
    }

    /*
     * short like text ("1 like," "5 likes," etc.)
     */
    public static String getShortLikeLabelText(Context context, int numLikes) {
        switch(numLikes) {
            case 0:
                return context.getString(R.string.reader_short_like_count_none);
            case 1:
                return context.getString(R.string.reader_short_like_count_one);
            default:
                String count = FormatUtils.formatInt(numLikes);
                return String.format(context.getString(R.string.reader_short_like_count_multi), count);
        }
    }

    public static String getShortCommentLabelText(Context context, int numComments) {
        switch(numComments) {
            case 1:
                return context.getString(R.string.reader_short_comment_count_one);
            default:
                String count = FormatUtils.formatInt(numComments);
                return String.format(context.getString(R.string.reader_short_comment_count_multi), count);
        }
    }

    public static String getTextForCommentSnippet(Context context, int numComments) {
        switch(numComments) {
            case 0:
                return context.getString(R.string.comments);
            case 1:
                return context.getString(R.string.reader_short_comment_count_one);
            default:
                String count = FormatUtils.formatInt(numComments);
                return String.format(context.getString(R.string.reader_short_comment_count_multi), count);
        }
    }

    /*
     * returns true if a ReaderPost and ReaderComment exist for the passed Ids
     */
    public static boolean postAndCommentExists(long blogId, long postId, long commentId) {
        return (ListenerUtil.mutListener.listen(19790) ? (ReaderPostTable.postExists(blogId, postId) || ReaderCommentTable.commentExists(blogId, postId, commentId)) : (ReaderPostTable.postExists(blogId, postId) && ReaderCommentTable.commentExists(blogId, postId, commentId)));
    }

    /*
     * used by Discover site picks to add a "Visit [BlogName]" link which shows the
     * native blog preview for that blog
     */
    public static String makeBlogPreviewUrl(long blogId) {
        return "wordpress://blogpreview?blogId=" + Long.toString(blogId);
    }

    public static boolean isBlogPreviewUrl(String url) {
        return ((ListenerUtil.mutListener.listen(19791) ? (url != null || url.startsWith("wordpress://blogpreview")) : (url != null && url.startsWith("wordpress://blogpreview"))));
    }

    public static long getBlogIdFromBlogPreviewUrl(String url) {
        if (isBlogPreviewUrl(url)) {
            String strBlogId = Uri.parse(url).getQueryParameter("blogId");
            return StringUtils.stringToLong(strBlogId);
        } else {
            return 0;
        }
    }

    /*
     * returns the passed string prefixed with a "#" if it's non-empty and isn't already
     * prefixed with a "#"
     */
    public static String makeHashTag(String tagName) {
        if (TextUtils.isEmpty(tagName)) {
            return "";
        } else if (tagName.startsWith("#")) {
            return tagName;
        } else {
            return "#" + tagName;
        }
    }

    /*
     * set the background of the passed view to the round ripple drawable - only works on
     * Lollipop or later, does nothing on earlier Android versions
     */
    public static void setBackgroundToRoundRipple(View view) {
        if (!ListenerUtil.mutListener.listen(19793)) {
            if (view != null) {
                if (!ListenerUtil.mutListener.listen(19792)) {
                    view.setBackgroundResource(R.drawable.ripple_oval);
                }
            }
        }
    }

    /*
     * returns a tag object from the passed endpoint if tag is in database, otherwise null
     */
    public static ReaderTag getTagFromEndpoint(String endpoint) {
        return ReaderTagTable.getTagFromEndpoint(endpoint);
    }

    /*
     * returns a tag object from the passed tag name - first checks for it in the tag db
     * (so we can also get its title & endpoint), returns a new tag if that fails
     */
    public static ReaderTag getTagFromTagName(String tagName, ReaderTagType tagType) {
        return getTagFromTagName(tagName, tagType, false);
    }

    public static ReaderTag getTagFromTagName(String tagName, ReaderTagType tagType, boolean markDefaultIfInMemory) {
        ReaderTag tag = ReaderTagTable.getTag(tagName, tagType);
        if (tag != null) {
            return tag;
        } else {
            return createTagFromTagName(tagName, tagType, markDefaultIfInMemory);
        }
    }

    public static ReaderTag createTagFromTagName(String tagName, ReaderTagType tagType) {
        return createTagFromTagName(tagName, tagType, false);
    }

    public static ReaderTag createTagFromTagName(String tagName, ReaderTagType tagType, boolean isDefaultInMemoryTag) {
        String tagSlug = sanitizeWithDashes(tagName).toLowerCase(Locale.ROOT);
        String tagDisplayName = tagType == ReaderTagType.DEFAULT ? tagName : tagSlug;
        return new ReaderTag(tagSlug, tagDisplayName, tagName, null, tagType, isDefaultInMemoryTag);
    }

    /*
     * returns the default tag, which is the one selected by default in the reader when
     * the user hasn't already chosen one
     */
    public static ReaderTag getDefaultTag() {
        ReaderTag defaultTag = getTagFromEndpoint(ReaderTag.TAG_ENDPOINT_DEFAULT);
        if (!ListenerUtil.mutListener.listen(19795)) {
            if (defaultTag == null) {
                if (!ListenerUtil.mutListener.listen(19794)) {
                    defaultTag = getTagFromTagName(ReaderTag.TAG_TITLE_DEFAULT, ReaderTagType.DEFAULT, true);
                }
            }
        }
        return defaultTag;
    }

    @NonNull
    public static ReaderTag getDefaultTagFromDbOrCreateInMemory(@NonNull Context context, TagUpdateClientUtilsProvider clientUtilsProvider) {
        // In case it cannot get the default tag from db, it creates it in memory with createTagFromTagName
        ReaderTag tag = getDefaultTag();
        if (!ListenerUtil.mutListener.listen(19805)) {
            if (tag.isDefaultInMemoryTag()) {
                if (!ListenerUtil.mutListener.listen(19796)) {
                    // we need to set some fields as below before to use it
                    tag.setTagTitle(context.getString(R.string.reader_following_display_name));
                }
                if (!ListenerUtil.mutListener.listen(19797)) {
                    tag.setTagDisplayName(context.getString(R.string.reader_following_display_name));
                }
                String baseUrl = clientUtilsProvider.getTagUpdateEndpointURL();
                if (!ListenerUtil.mutListener.listen(19803)) {
                    if (baseUrl.endsWith("/")) {
                        if (!ListenerUtil.mutListener.listen(19802)) {
                            baseUrl = baseUrl.substring(0, (ListenerUtil.mutListener.listen(19801) ? (baseUrl.length() % 1) : (ListenerUtil.mutListener.listen(19800) ? (baseUrl.length() / 1) : (ListenerUtil.mutListener.listen(19799) ? (baseUrl.length() * 1) : (ListenerUtil.mutListener.listen(19798) ? (baseUrl.length() + 1) : (baseUrl.length() - 1))))));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(19804)) {
                    tag.setEndpoint(baseUrl + ReaderTag.FOLLOWING_PATH);
                }
            }
        }
        return tag;
    }

    /*
     * used when storing search results in the reader post table
     */
    public static ReaderTag getTagForSearchQuery(@NonNull String query) {
        String trimQuery = query != null ? query.trim() : "";
        String slug = ReaderUtils.sanitizeWithDashes(trimQuery);
        return new ReaderTag(slug, trimQuery, trimQuery, null, ReaderTagType.SEARCH);
    }

    public static Map<String, TagInfo> getDefaultTagInfo() {
        // (see usage in prependDefaults)
        Map<String, TagInfo> defaultTagInfo = new LinkedHashMap<>();
        if (!ListenerUtil.mutListener.listen(19806)) {
            defaultTagInfo.put(ReaderConstants.KEY_FOLLOWING, new TagInfo(ReaderTagType.DEFAULT, ReaderTag.FOLLOWING_PATH));
        }
        if (!ListenerUtil.mutListener.listen(19807)) {
            defaultTagInfo.put(ReaderConstants.KEY_DISCOVER, new TagInfo(ReaderTagType.DEFAULT, ReaderTag.DISCOVER_PATH));
        }
        if (!ListenerUtil.mutListener.listen(19808)) {
            defaultTagInfo.put(ReaderConstants.KEY_LIKES, new TagInfo(ReaderTagType.DEFAULT, ReaderTag.LIKED_PATH));
        }
        if (!ListenerUtil.mutListener.listen(19809)) {
            defaultTagInfo.put(ReaderConstants.KEY_SAVED, new TagInfo(ReaderTagType.BOOKMARKED, ""));
        }
        return defaultTagInfo;
    }

    private static boolean putIfAbsentDone(Map<String, ReaderTag> defaultTags, String key, ReaderTag tag) {
        boolean insertionDone = false;
        if (!ListenerUtil.mutListener.listen(19812)) {
            if (defaultTags.get(key) == null) {
                if (!ListenerUtil.mutListener.listen(19810)) {
                    defaultTags.put(key, tag);
                }
                if (!ListenerUtil.mutListener.listen(19811)) {
                    insertionDone = true;
                }
            }
        }
        return insertionDone;
    }

    private static void prependDefaults(Map<String, ReaderTag> defaultTags, ReaderTagList orderedTagList, Map<String, TagInfo> defaultTagInfo) {
        if (!ListenerUtil.mutListener.listen(19813)) {
            if (defaultTags.isEmpty())
                return;
        }
        List<String> reverseOrderedKeys = new ArrayList<>(defaultTagInfo.keySet());
        if (!ListenerUtil.mutListener.listen(19814)) {
            Collections.reverse(reverseOrderedKeys);
        }
        if (!ListenerUtil.mutListener.listen(19817)) {
            {
                long _loopCounter328 = 0;
                for (String key : reverseOrderedKeys) {
                    ListenerUtil.loopListener.listen("_loopCounter328", ++_loopCounter328);
                    if (!ListenerUtil.mutListener.listen(19816)) {
                        if (defaultTags.containsKey(key)) {
                            ReaderTag tag = defaultTags.get(key);
                            if (!ListenerUtil.mutListener.listen(19815)) {
                                orderedTagList.add(0, tag);
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean defaultTagFoundAndAdded(Map<String, TagInfo> defaultTagInfos, ReaderTag tag, Map<String, ReaderTag> defaultTags) {
        boolean foundAndAdded = false;
        if (!ListenerUtil.mutListener.listen(19821)) {
            {
                long _loopCounter329 = 0;
                for (String key : defaultTagInfos.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter329", ++_loopCounter329);
                    if (!ListenerUtil.mutListener.listen(19820)) {
                        if (defaultTagInfos.get(key).isDesiredTag(tag)) {
                            if (!ListenerUtil.mutListener.listen(19819)) {
                                if (putIfAbsentDone(defaultTags, key, tag)) {
                                    if (!ListenerUtil.mutListener.listen(19818)) {
                                        foundAndAdded = true;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        return foundAndAdded;
    }

    public static ReaderTagList getOrderedTagsList(ReaderTagList tagList, Map<String, TagInfo> defaultTagInfos) {
        ReaderTagList orderedTagList = new ReaderTagList();
        Map<String, ReaderTag> defaultTags = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(19824)) {
            {
                long _loopCounter330 = 0;
                for (ReaderTag tag : tagList) {
                    ListenerUtil.loopListener.listen("_loopCounter330", ++_loopCounter330);
                    if (!ListenerUtil.mutListener.listen(19822)) {
                        if (defaultTagFoundAndAdded(defaultTagInfos, tag, defaultTags))
                            continue;
                    }
                    if (!ListenerUtil.mutListener.listen(19823)) {
                        orderedTagList.add(tag);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19825)) {
            prependDefaults(defaultTags, orderedTagList, defaultTagInfos);
        }
        return orderedTagList;
    }

    public static boolean isTagManagedInFollowingTab(ReaderTag tag, boolean isTopLevelReader, FilteredRecyclerView recyclerView) {
        if (isTopLevelReader) {
            if (ReaderUtils.isDefaultInMemoryTag(tag)) {
                return true;
            }
            boolean isSpecialTag = (ListenerUtil.mutListener.listen(19829) ? (tag != null || ((ListenerUtil.mutListener.listen(19828) ? ((ListenerUtil.mutListener.listen(19827) ? (tag.isDiscover() && tag.isPostsILike()) : (tag.isDiscover() || tag.isPostsILike())) && tag.isBookmarked()) : ((ListenerUtil.mutListener.listen(19827) ? (tag.isDiscover() && tag.isPostsILike()) : (tag.isDiscover() || tag.isPostsILike())) || tag.isBookmarked())))) : (tag != null && ((ListenerUtil.mutListener.listen(19828) ? ((ListenerUtil.mutListener.listen(19827) ? (tag.isDiscover() && tag.isPostsILike()) : (tag.isDiscover() || tag.isPostsILike())) && tag.isBookmarked()) : ((ListenerUtil.mutListener.listen(19827) ? (tag.isDiscover() && tag.isPostsILike()) : (tag.isDiscover() || tag.isPostsILike())) || tag.isBookmarked())))));
            boolean tabsInitializingNow = (ListenerUtil.mutListener.listen(19830) ? (recyclerView != null || recyclerView.getCurrentFilter() == null) : (recyclerView != null && recyclerView.getCurrentFilter() == null));
            boolean tagIsFollowedSitesOrAFollowedTag = (ListenerUtil.mutListener.listen(19832) ? (tag != null || ((ListenerUtil.mutListener.listen(19831) ? (tag.isFollowedSites() && tag.tagType == ReaderTagType.FOLLOWED) : (tag.isFollowedSites() || tag.tagType == ReaderTagType.FOLLOWED)))) : (tag != null && ((ListenerUtil.mutListener.listen(19831) ? (tag.isFollowedSites() && tag.tagType == ReaderTagType.FOLLOWED) : (tag.isFollowedSites() || tag.tagType == ReaderTagType.FOLLOWED)))));
            if (isSpecialTag) {
                return false;
            } else if (tabsInitializingNow) {
                return tagIsFollowedSitesOrAFollowedTag;
            } else if ((ListenerUtil.mutListener.listen(19833) ? (recyclerView != null || recyclerView.getCurrentFilter() instanceof ReaderTag) : (recyclerView != null && recyclerView.getCurrentFilter() instanceof ReaderTag))) {
                if (recyclerView.isValidFilter(tag)) {
                    return tag.isFollowedSites();
                } else {
                    // If we reach here it means we are setting a followed tag or site in the Following tab
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return (ListenerUtil.mutListener.listen(19826) ? (tag != null || tag.isFollowedSites()) : (tag != null && tag.isFollowedSites()));
        }
    }

    @NonNull
    public static ReaderTag getValidTagForSharedPrefs(@NonNull ReaderTag tag, boolean isTopLevelReader, FilteredRecyclerView recyclerView, @NonNull ReaderTag defaultTag) {
        if (!ListenerUtil.mutListener.listen(19834)) {
            if (!isTopLevelReader) {
                return tag;
            }
        }
        boolean isValidFilter = ((ListenerUtil.mutListener.listen(19835) ? (recyclerView != null || recyclerView.isValidFilter(tag)) : (recyclerView != null && recyclerView.isValidFilter(tag))));
        boolean isSpecialTag = (ListenerUtil.mutListener.listen(19837) ? ((ListenerUtil.mutListener.listen(19836) ? (tag.isDiscover() && tag.isPostsILike()) : (tag.isDiscover() || tag.isPostsILike())) && tag.isBookmarked()) : ((ListenerUtil.mutListener.listen(19836) ? (tag.isDiscover() && tag.isPostsILike()) : (tag.isDiscover() || tag.isPostsILike())) || tag.isBookmarked()));
        if (!ListenerUtil.mutListener.listen(19840)) {
            if ((ListenerUtil.mutListener.listen(19839) ? ((ListenerUtil.mutListener.listen(19838) ? (!isSpecialTag || !isValidFilter) : (!isSpecialTag && !isValidFilter)) || isTagManagedInFollowingTab(tag, isTopLevelReader, recyclerView)) : ((ListenerUtil.mutListener.listen(19838) ? (!isSpecialTag || !isValidFilter) : (!isSpecialTag && !isValidFilter)) && isTagManagedInFollowingTab(tag, isTopLevelReader, recyclerView)))) {
                return defaultTag;
            }
        }
        return tag;
    }

    public static boolean isDefaultInMemoryTag(ReaderTag tag) {
        return (ListenerUtil.mutListener.listen(19841) ? (tag != null || tag.isDefaultInMemoryTag()) : (tag != null && tag.isDefaultInMemoryTag()));
    }

    public static String getCommaSeparatedTagSlugs(ReaderTagList tags) {
        StringBuilder slugs = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(19850)) {
            {
                long _loopCounter331 = 0;
                for (ReaderTag tag : tags) {
                    ListenerUtil.loopListener.listen("_loopCounter331", ++_loopCounter331);
                    if (!ListenerUtil.mutListener.listen(19848)) {
                        if ((ListenerUtil.mutListener.listen(19846) ? (slugs.length() >= 0) : (ListenerUtil.mutListener.listen(19845) ? (slugs.length() <= 0) : (ListenerUtil.mutListener.listen(19844) ? (slugs.length() < 0) : (ListenerUtil.mutListener.listen(19843) ? (slugs.length() != 0) : (ListenerUtil.mutListener.listen(19842) ? (slugs.length() == 0) : (slugs.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(19847)) {
                                slugs.append(",");
                            }
                        }
                    }
                    final String tagNameForApi = ReaderUtils.sanitizeWithDashes(tag.getTagSlug());
                    if (!ListenerUtil.mutListener.listen(19849)) {
                        slugs.append(tagNameForApi);
                    }
                }
            }
        }
        return slugs.toString();
    }

    public static ReaderTagList getTagsFromCommaSeparatedSlugs(@NotNull String commaSeparatedTagSlugs) {
        ReaderTagList tags = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(19853)) {
            if (!commaSeparatedTagSlugs.trim().equals("")) {
                if (!ListenerUtil.mutListener.listen(19852)) {
                    {
                        long _loopCounter332 = 0;
                        for (String slug : commaSeparatedTagSlugs.split(",", -1)) {
                            ListenerUtil.loopListener.listen("_loopCounter332", ++_loopCounter332);
                            ReaderTag tag = ReaderUtils.getTagFromTagName(slug, ReaderTagType.DEFAULT);
                            if (!ListenerUtil.mutListener.listen(19851)) {
                                tags.add(tag);
                            }
                        }
                    }
                }
            }
        }
        return tags;
    }

    /**
     * isExternalFeed identifies an external RSS feed
     * blogId will be empty for feeds and in some instances, it is explicitly
     * setting blogId equal to the feedId
     */
    public static boolean isExternalFeed(long blogId, long feedId) {
        return (ListenerUtil.mutListener.listen(19870) ? (((ListenerUtil.mutListener.listen(19864) ? ((ListenerUtil.mutListener.listen(19858) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(19857) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(19856) ? (blogId > 0) : (ListenerUtil.mutListener.listen(19855) ? (blogId < 0) : (ListenerUtil.mutListener.listen(19854) ? (blogId != 0) : (blogId == 0)))))) || (ListenerUtil.mutListener.listen(19863) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(19862) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(19861) ? (feedId > 0) : (ListenerUtil.mutListener.listen(19860) ? (feedId < 0) : (ListenerUtil.mutListener.listen(19859) ? (feedId == 0) : (feedId != 0))))))) : ((ListenerUtil.mutListener.listen(19858) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(19857) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(19856) ? (blogId > 0) : (ListenerUtil.mutListener.listen(19855) ? (blogId < 0) : (ListenerUtil.mutListener.listen(19854) ? (blogId != 0) : (blogId == 0)))))) && (ListenerUtil.mutListener.listen(19863) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(19862) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(19861) ? (feedId > 0) : (ListenerUtil.mutListener.listen(19860) ? (feedId < 0) : (ListenerUtil.mutListener.listen(19859) ? (feedId == 0) : (feedId != 0))))))))) && (ListenerUtil.mutListener.listen(19869) ? (blogId >= feedId) : (ListenerUtil.mutListener.listen(19868) ? (blogId <= feedId) : (ListenerUtil.mutListener.listen(19867) ? (blogId > feedId) : (ListenerUtil.mutListener.listen(19866) ? (blogId < feedId) : (ListenerUtil.mutListener.listen(19865) ? (blogId != feedId) : (blogId == feedId))))))) : (((ListenerUtil.mutListener.listen(19864) ? ((ListenerUtil.mutListener.listen(19858) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(19857) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(19856) ? (blogId > 0) : (ListenerUtil.mutListener.listen(19855) ? (blogId < 0) : (ListenerUtil.mutListener.listen(19854) ? (blogId != 0) : (blogId == 0)))))) || (ListenerUtil.mutListener.listen(19863) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(19862) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(19861) ? (feedId > 0) : (ListenerUtil.mutListener.listen(19860) ? (feedId < 0) : (ListenerUtil.mutListener.listen(19859) ? (feedId == 0) : (feedId != 0))))))) : ((ListenerUtil.mutListener.listen(19858) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(19857) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(19856) ? (blogId > 0) : (ListenerUtil.mutListener.listen(19855) ? (blogId < 0) : (ListenerUtil.mutListener.listen(19854) ? (blogId != 0) : (blogId == 0)))))) && (ListenerUtil.mutListener.listen(19863) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(19862) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(19861) ? (feedId > 0) : (ListenerUtil.mutListener.listen(19860) ? (feedId < 0) : (ListenerUtil.mutListener.listen(19859) ? (feedId == 0) : (feedId != 0))))))))) || (ListenerUtil.mutListener.listen(19869) ? (blogId >= feedId) : (ListenerUtil.mutListener.listen(19868) ? (blogId <= feedId) : (ListenerUtil.mutListener.listen(19867) ? (blogId > feedId) : (ListenerUtil.mutListener.listen(19866) ? (blogId < feedId) : (ListenerUtil.mutListener.listen(19865) ? (blogId != feedId) : (blogId == feedId))))))));
    }

    public static String getReportPostUrl(String blogUrl) {
        return "https://wordpress.com/abuse/?report_url=" + blogUrl;
    }

    public static boolean postExists(long blogId, long postId) {
        return ReaderPostTable.postExists(blogId, postId);
    }

    public static boolean commentExists(long blogId, long postId, long commentId) {
        return ReaderCommentTable.commentExists(blogId, postId, commentId);
    }
}
