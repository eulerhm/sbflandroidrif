package org.wordpress.android.ui.reader.actions;

import android.text.TextUtils;
import android.util.Pair;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wordpress.rest.RestRequest;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.datasets.ReaderBlogTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.models.ReaderBlog;
import org.wordpress.android.models.ReaderPostList;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.reader.actions.ReaderActions.ActionListener;
import org.wordpress.android.ui.reader.actions.ReaderActions.UpdateBlogInfoListener;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.VolleyUtils;
import java.net.HttpURLConnection;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderBlogActions {

    public static class BlockedBlogResult {

        public long blogId;

        public long feedId;

        // Key: Pair<ReaderTagSlug, ReaderTagType>, Value: ReaderPostList
        public Map<Pair<String, ReaderTagType>, ReaderPostList> deletedRows;

        public boolean wasFollowing;
    }

    private static String jsonToString(JSONObject json) {
        return (json != null ? json.toString() : "");
    }

    public static boolean followBlogById(final long blogId, final long feedId, final boolean isAskingToFollow, final ActionListener actionListener, final String source, final ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(17854)) {
            if ((ListenerUtil.mutListener.listen(17852) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(17851) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(17850) ? (blogId > 0) : (ListenerUtil.mutListener.listen(17849) ? (blogId < 0) : (ListenerUtil.mutListener.listen(17848) ? (blogId != 0) : (blogId == 0))))))) {
                if (!ListenerUtil.mutListener.listen(17853)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(17855)) {
            ReaderBlogTable.setIsFollowedBlogId(blogId, isAskingToFollow);
        }
        if (!ListenerUtil.mutListener.listen(17856)) {
            ReaderPostTable.setFollowStatusForPostsInBlog(blogId, isAskingToFollow);
        }
        if (!ListenerUtil.mutListener.listen(17859)) {
            if (isAskingToFollow) {
                if (!ListenerUtil.mutListener.listen(17858)) {
                    readerTracker.trackBlog(AnalyticsTracker.Stat.READER_BLOG_FOLLOWED, blogId, feedId, source);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17857)) {
                    readerTracker.trackBlog(AnalyticsTracker.Stat.READER_BLOG_UNFOLLOWED, blogId, feedId, source);
                }
            }
        }
        final String actionName = (isAskingToFollow ? "follow" : "unfollow");
        final String path = "sites/" + blogId + "/follows/" + (isAskingToFollow ? "new?source=android" : "mine/delete");
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                boolean success = isFollowActionSuccessful(jsonObject, isAskingToFollow);
                if (!ListenerUtil.mutListener.listen(17863)) {
                    if (success) {
                        if (!ListenerUtil.mutListener.listen(17862)) {
                            AppLog.d(T.READER, "blog " + actionName + " succeeded");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17860)) {
                            AppLog.w(T.READER, "blog " + actionName + " failed - " + jsonToString(jsonObject) + " - " + path);
                        }
                        if (!ListenerUtil.mutListener.listen(17861)) {
                            localRevertFollowBlogId(blogId, isAskingToFollow);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17864)) {
                    ReaderActions.callActionListener(actionListener, success);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17865)) {
                    AppLog.w(T.READER, "blog " + actionName + " failed with error");
                }
                if (!ListenerUtil.mutListener.listen(17866)) {
                    AppLog.e(T.READER, volleyError);
                }
                // by url - note that the v1.2 endpoint will return a 404 in this situation
                int status = VolleyUtils.statusCodeFromVolleyError(volleyError);
                if (!ListenerUtil.mutListener.listen(17876)) {
                    if ((ListenerUtil.mutListener.listen(17872) ? ((ListenerUtil.mutListener.listen(17871) ? (status >= 403) : (ListenerUtil.mutListener.listen(17870) ? (status <= 403) : (ListenerUtil.mutListener.listen(17869) ? (status > 403) : (ListenerUtil.mutListener.listen(17868) ? (status < 403) : (ListenerUtil.mutListener.listen(17867) ? (status != 403) : (status == 403)))))) || !isAskingToFollow) : ((ListenerUtil.mutListener.listen(17871) ? (status >= 403) : (ListenerUtil.mutListener.listen(17870) ? (status <= 403) : (ListenerUtil.mutListener.listen(17869) ? (status > 403) : (ListenerUtil.mutListener.listen(17868) ? (status < 403) : (ListenerUtil.mutListener.listen(17867) ? (status != 403) : (status == 403)))))) && !isAskingToFollow))) {
                        if (!ListenerUtil.mutListener.listen(17875)) {
                            internalUnfollowBlogByUrl(blogId, actionListener);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17873)) {
                            localRevertFollowBlogId(blogId, isAskingToFollow);
                        }
                        if (!ListenerUtil.mutListener.listen(17874)) {
                            ReaderActions.callActionListener(actionListener, false);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(17877)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
        return true;
    }

    private static void internalUnfollowBlogByUrl(long blogId, final ActionListener actionListener) {
        String blogUrl = ReaderBlogTable.getBlogUrl(blogId);
        if (!ListenerUtil.mutListener.listen(17880)) {
            if (TextUtils.isEmpty(blogUrl)) {
                if (!ListenerUtil.mutListener.listen(17878)) {
                    AppLog.w(T.READER, "URL not found for blogId " + blogId);
                }
                if (!ListenerUtil.mutListener.listen(17879)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
                return;
            }
        }
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject response) {
                if (!ListenerUtil.mutListener.listen(17881)) {
                    ReaderActions.callActionListener(actionListener, true);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ListenerUtil.mutListener.listen(17882)) {
                    AppLog.e(T.READER, error);
                }
                if (!ListenerUtil.mutListener.listen(17883)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
            }
        };
        String path = "/read/following/mine/delete?url=" + UrlUtils.urlEncode(blogUrl);
        if (!ListenerUtil.mutListener.listen(17884)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    public static boolean followFeedById(@SuppressWarnings("unused") final long blogId, final long feedId, final boolean isAskingToFollow, final ActionListener actionListener, final String source, final ReaderTracker readerTracker) {
        ReaderBlog blogInfo = ReaderBlogTable.getFeedInfo(feedId);
        if (!ListenerUtil.mutListener.listen(17885)) {
            if (blogInfo != null) {
                return internalFollowFeed(blogInfo.blogId, blogInfo.feedId, blogInfo.getFeedUrl(), isAskingToFollow, actionListener, source, readerTracker);
            }
        }
        if (!ListenerUtil.mutListener.listen(17889)) {
            updateFeedInfo(feedId, null, new UpdateBlogInfoListener() {

                @Override
                public void onResult(ReaderBlog blogInfo) {
                    if (!ListenerUtil.mutListener.listen(17888)) {
                        if (blogInfo != null) {
                            if (!ListenerUtil.mutListener.listen(17887)) {
                                internalFollowFeed(blogInfo.blogId, blogInfo.feedId, blogInfo.getFeedUrl(), isAskingToFollow, actionListener, source, readerTracker);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(17886)) {
                                ReaderActions.callActionListener(actionListener, false);
                            }
                        }
                    }
                }
            });
        }
        return true;
    }

    public static void followFeedByUrl(final String feedUrl, final ActionListener actionListener, final String source, final ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(17891)) {
            if (TextUtils.isEmpty(feedUrl)) {
                if (!ListenerUtil.mutListener.listen(17890)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
                return;
            }
        }
        // use existing blog info if we can
        ReaderBlog blogInfo = ReaderBlogTable.getFeedInfo(ReaderBlogTable.getFeedIdFromUrl(feedUrl));
        if (!ListenerUtil.mutListener.listen(17893)) {
            if (blogInfo != null) {
                if (!ListenerUtil.mutListener.listen(17892)) {
                    internalFollowFeed(blogInfo.blogId, blogInfo.feedId, blogInfo.getFeedUrl(), true, actionListener, source, readerTracker);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(17896)) {
            // otherwise, look it up via the endpoint
            updateFeedInfo(0, feedUrl, new UpdateBlogInfoListener() {

                @Override
                public void onResult(ReaderBlog blogInfo) {
                    // endpoint doesn't perform feed discovery, whereas the endpoint to follow a feed does
                    long blogIdToFollow = blogInfo != null ? blogInfo.blogId : 0;
                    long feedIdToFollow = blogInfo != null ? blogInfo.feedId : 0;
                    String feedUrlToFollow = ((ListenerUtil.mutListener.listen(17894) ? (blogInfo != null || blogInfo.hasFeedUrl()) : (blogInfo != null && blogInfo.hasFeedUrl()))) ? blogInfo.getFeedUrl() : feedUrl;
                    if (!ListenerUtil.mutListener.listen(17895)) {
                        internalFollowFeed(blogIdToFollow, feedIdToFollow, feedUrlToFollow, true, actionListener, source, readerTracker);
                    }
                }
            });
        }
    }

    private static boolean internalFollowFeed(final long blogId, final long feedId, final String feedUrl, final boolean isAskingToFollow, final ActionListener actionListener, final String source, final ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(17898)) {
            // feedUrl is required
            if (TextUtils.isEmpty(feedUrl)) {
                if (!ListenerUtil.mutListener.listen(17897)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(17906)) {
            if ((ListenerUtil.mutListener.listen(17903) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(17902) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(17901) ? (feedId > 0) : (ListenerUtil.mutListener.listen(17900) ? (feedId < 0) : (ListenerUtil.mutListener.listen(17899) ? (feedId == 0) : (feedId != 0))))))) {
                if (!ListenerUtil.mutListener.listen(17904)) {
                    ReaderBlogTable.setIsFollowedFeedId(feedId, isAskingToFollow);
                }
                if (!ListenerUtil.mutListener.listen(17905)) {
                    ReaderPostTable.setFollowStatusForPostsInFeed(feedId, isAskingToFollow);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(17909)) {
            if (isAskingToFollow) {
                if (!ListenerUtil.mutListener.listen(17908)) {
                    readerTracker.trackBlog(AnalyticsTracker.Stat.READER_BLOG_FOLLOWED, blogId, feedId, source);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17907)) {
                    readerTracker.trackBlog(AnalyticsTracker.Stat.READER_BLOG_UNFOLLOWED, blogId, feedId, source);
                }
            }
        }
        final String actionName = (isAskingToFollow ? "follow" : "unfollow");
        final String path = "read/following/mine/" + (isAskingToFollow ? "new?source=android&url=" : "delete?url=") + UrlUtils.urlEncode(feedUrl);
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                boolean success = isFollowActionSuccessful(jsonObject, isAskingToFollow);
                if (!ListenerUtil.mutListener.listen(17913)) {
                    if (success) {
                        if (!ListenerUtil.mutListener.listen(17912)) {
                            AppLog.d(T.READER, "feed " + actionName + " succeeded");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17910)) {
                            AppLog.w(T.READER, "feed " + actionName + " failed - " + jsonToString(jsonObject) + " - " + path);
                        }
                        if (!ListenerUtil.mutListener.listen(17911)) {
                            localRevertFollowFeedId(feedId, isAskingToFollow);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17914)) {
                    ReaderActions.callActionListener(actionListener, success);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17915)) {
                    AppLog.w(T.READER, "feed " + actionName + " failed with error");
                }
                if (!ListenerUtil.mutListener.listen(17916)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17917)) {
                    localRevertFollowFeedId(feedId, isAskingToFollow);
                }
                if (!ListenerUtil.mutListener.listen(17918)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(17919)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
        return true;
    }

    /*
     * helper routine when following a blog from a post view
     */
    public static boolean followBlog(final Long blogId, final Long feedId, boolean isAskingToFollow, ActionListener actionListener, final String source, ReaderTracker readerTracker) {
        if (ReaderUtils.isExternalFeed(blogId, feedId)) {
            return followFeedById(blogId, feedId, isAskingToFollow, actionListener, source, readerTracker);
        } else {
            return followBlogById(blogId, feedId, isAskingToFollow, actionListener, source, readerTracker);
        }
    }

    /*
     * called when a follow/unfollow fails, restores local data to previous state
     */
    private static void localRevertFollowBlogId(long blogId, boolean isAskingToFollow) {
        if (!ListenerUtil.mutListener.listen(17920)) {
            ReaderBlogTable.setIsFollowedBlogId(blogId, !isAskingToFollow);
        }
        if (!ListenerUtil.mutListener.listen(17921)) {
            ReaderPostTable.setFollowStatusForPostsInBlog(blogId, !isAskingToFollow);
        }
    }

    private static void localRevertFollowFeedId(long feedId, boolean isAskingToFollow) {
        if (!ListenerUtil.mutListener.listen(17922)) {
            ReaderBlogTable.setIsFollowedFeedId(feedId, !isAskingToFollow);
        }
        if (!ListenerUtil.mutListener.listen(17923)) {
            ReaderPostTable.setFollowStatusForPostsInFeed(feedId, !isAskingToFollow);
        }
    }

    /*
     * returns whether a follow/unfollow was successful based on the response to:
     * read/follows/new
     * read/follows/delete
     * site/$site/follows/new
     * site/$site/follows/mine/delete
     */
    private static boolean isFollowActionSuccessful(JSONObject json, boolean isAskingToFollow) {
        if (!ListenerUtil.mutListener.listen(17924)) {
            if (json == null) {
                return false;
            }
        }
        boolean isSubscribed;
        if (json.has("subscribed")) {
            // read/follows/
            isSubscribed = json.optBoolean("subscribed", false);
        } else {
            // site/$site/follows/
            isSubscribed = (ListenerUtil.mutListener.listen(17925) ? (json.has("is_following") || json.optBoolean("is_following", false)) : (json.has("is_following") && json.optBoolean("is_following", false)));
        }
        return (isSubscribed == isAskingToFollow);
    }

    /*
     * request info about a specific blog
     */
    public static void updateBlogInfo(long blogId, final String blogUrl, final UpdateBlogInfoListener infoListener) {
        // must pass either a valid id or url
        final boolean hasBlogId = ((ListenerUtil.mutListener.listen(17930) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(17929) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(17928) ? (blogId > 0) : (ListenerUtil.mutListener.listen(17927) ? (blogId < 0) : (ListenerUtil.mutListener.listen(17926) ? (blogId == 0) : (blogId != 0)))))));
        final boolean hasBlogUrl = !TextUtils.isEmpty(blogUrl);
        if (!ListenerUtil.mutListener.listen(17935)) {
            if ((ListenerUtil.mutListener.listen(17931) ? (!hasBlogId || !hasBlogUrl) : (!hasBlogId && !hasBlogUrl))) {
                if (!ListenerUtil.mutListener.listen(17932)) {
                    AppLog.w(T.READER, "cannot get blog info without either id or url");
                }
                if (!ListenerUtil.mutListener.listen(17934)) {
                    if (infoListener != null) {
                        if (!ListenerUtil.mutListener.listen(17933)) {
                            infoListener.onResult(null);
                        }
                    }
                }
                return;
            }
        }
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(17936)) {
                    handleUpdateBlogInfoResponse(jsonObject, infoListener);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // authentication error may indicate that API access has been disabled for this blog
                int statusCode = VolleyUtils.statusCodeFromVolleyError(volleyError);
                boolean isAuthErr = (statusCode == HttpURLConnection.HTTP_FORBIDDEN);
                if (!ListenerUtil.mutListener.listen(17944)) {
                    // error, try again using just the domain
                    if ((ListenerUtil.mutListener.listen(17938) ? ((ListenerUtil.mutListener.listen(17937) ? (!isAuthErr || hasBlogId) : (!isAuthErr && hasBlogId)) || hasBlogUrl) : ((ListenerUtil.mutListener.listen(17937) ? (!isAuthErr || hasBlogId) : (!isAuthErr && hasBlogId)) && hasBlogUrl))) {
                        if (!ListenerUtil.mutListener.listen(17942)) {
                            AppLog.w(T.READER, "failed to get blog info by id, retrying with url");
                        }
                        if (!ListenerUtil.mutListener.listen(17943)) {
                            updateBlogInfo(0, blogUrl, infoListener);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17939)) {
                            AppLog.e(T.READER, volleyError);
                        }
                        if (!ListenerUtil.mutListener.listen(17941)) {
                            if (infoListener != null) {
                                if (!ListenerUtil.mutListener.listen(17940)) {
                                    infoListener.onResult(null);
                                }
                            }
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(17947)) {
            if (hasBlogId) {
                if (!ListenerUtil.mutListener.listen(17946)) {
                    WordPress.getRestClientUtilsV1_1().get("read/sites/" + blogId, listener, errorListener);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(17945)) {
                    WordPress.getRestClientUtilsV1_1().get("read/sites/" + UrlUtils.urlEncode(UrlUtils.getHost(blogUrl)), listener, errorListener);
                }
            }
        }
    }

    public static void updateFeedInfo(long feedId, String feedUrl, final UpdateBlogInfoListener infoListener) {
        // must pass either a valid id or url
        final boolean hasFeedId = ((ListenerUtil.mutListener.listen(17952) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(17951) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(17950) ? (feedId > 0) : (ListenerUtil.mutListener.listen(17949) ? (feedId < 0) : (ListenerUtil.mutListener.listen(17948) ? (feedId == 0) : (feedId != 0)))))));
        final boolean hasFeedUrl = !TextUtils.isEmpty(feedUrl);
        if (!ListenerUtil.mutListener.listen(17957)) {
            if ((ListenerUtil.mutListener.listen(17953) ? (!hasFeedId || !hasFeedUrl) : (!hasFeedId && !hasFeedUrl))) {
                if (!ListenerUtil.mutListener.listen(17954)) {
                    AppLog.w(T.READER, "cannot update Feed info without either id or url");
                }
                if (!ListenerUtil.mutListener.listen(17956)) {
                    if (infoListener != null) {
                        if (!ListenerUtil.mutListener.listen(17955)) {
                            infoListener.onResult(null);
                        }
                    }
                }
                return;
            }
        }
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(17958)) {
                    handleUpdateBlogInfoResponse(jsonObject, infoListener);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17959)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17961)) {
                    if (infoListener != null) {
                        if (!ListenerUtil.mutListener.listen(17960)) {
                            infoListener.onResult(null);
                        }
                    }
                }
            }
        };
        String path;
        if ((ListenerUtil.mutListener.listen(17966) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(17965) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(17964) ? (feedId > 0) : (ListenerUtil.mutListener.listen(17963) ? (feedId < 0) : (ListenerUtil.mutListener.listen(17962) ? (feedId == 0) : (feedId != 0))))))) {
            path = "read/feed/" + feedId;
        } else {
            path = "read/feed/" + UrlUtils.urlEncode(feedUrl);
        }
        if (!ListenerUtil.mutListener.listen(17967)) {
            WordPress.getRestClientUtilsV1_1().get(path, listener, errorListener);
        }
    }

    private static void handleUpdateBlogInfoResponse(JSONObject jsonObject, UpdateBlogInfoListener infoListener) {
        if (!ListenerUtil.mutListener.listen(17970)) {
            if (jsonObject == null) {
                if (!ListenerUtil.mutListener.listen(17969)) {
                    if (infoListener != null) {
                        if (!ListenerUtil.mutListener.listen(17968)) {
                            infoListener.onResult(null);
                        }
                    }
                }
                return;
            }
        }
        ReaderBlog blogInfo = ReaderBlog.fromJson(jsonObject);
        if (!ListenerUtil.mutListener.listen(17971)) {
            ReaderBlogTable.addOrUpdateBlog(blogInfo);
        }
        if (!ListenerUtil.mutListener.listen(17973)) {
            if (infoListener != null) {
                if (!ListenerUtil.mutListener.listen(17972)) {
                    infoListener.onResult(blogInfo);
                }
            }
        }
    }

    /*
     * tests whether the passed url can be reached - does NOT use authentication, and does not
     * account for 404 replacement pages used by ISPs such as Charter
     */
    public static void checkUrlReachable(final String blogUrl, final ReaderActions.OnRequestListener<Void> requestListener) {
        if (!ListenerUtil.mutListener.listen(17974)) {
            // listener is required
            if (requestListener == null) {
                return;
            }
        }
        Response.Listener<String> listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (!ListenerUtil.mutListener.listen(17975)) {
                    requestListener.onSuccess(null);
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17976)) {
                    AppLog.e(T.READER, volleyError);
                }
                int statusCode;
                // since a redirect to an unauthorized url may return a 301 rather than a 401
                if (volleyError instanceof com.android.volley.AuthFailureError) {
                    statusCode = 401;
                } else {
                    statusCode = VolleyUtils.statusCodeFromVolleyError(volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17984)) {
                    // success since it means the blog url is reachable
                    if ((ListenerUtil.mutListener.listen(17981) ? (statusCode >= 301) : (ListenerUtil.mutListener.listen(17980) ? (statusCode <= 301) : (ListenerUtil.mutListener.listen(17979) ? (statusCode > 301) : (ListenerUtil.mutListener.listen(17978) ? (statusCode < 301) : (ListenerUtil.mutListener.listen(17977) ? (statusCode != 301) : (statusCode == 301))))))) {
                        if (!ListenerUtil.mutListener.listen(17983)) {
                            requestListener.onSuccess(null);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(17982)) {
                            requestListener.onFailure(statusCode);
                        }
                    }
                }
            }
        };
        // using it results in "java.lang.IllegalStateException: Unknown method type"
        StringRequest request = new StringRequest(Request.Method.GET, blogUrl, listener, errorListener);
        if (!ListenerUtil.mutListener.listen(17985)) {
            WordPress.requestQueue.add(request);
        }
    }

    public static BlockedBlogResult blockBlogFromReaderLocal(final long blogId, final long feedId) {
        final BlockedBlogResult blockResult = new BlockedBlogResult();
        if (!ListenerUtil.mutListener.listen(17986)) {
            blockResult.blogId = blogId;
        }
        if (!ListenerUtil.mutListener.listen(17987)) {
            blockResult.feedId = feedId;
        }
        if (!ListenerUtil.mutListener.listen(17988)) {
            blockResult.deletedRows = ReaderPostTable.getTagPostMap(blogId);
        }
        if (!ListenerUtil.mutListener.listen(17989)) {
            blockResult.wasFollowing = ReaderBlogTable.isFollowedBlog(blogId);
        }
        if (!ListenerUtil.mutListener.listen(17990)) {
            ReaderPostTable.deletePostsInBlog(blockResult.blogId);
        }
        if (!ListenerUtil.mutListener.listen(17991)) {
            ReaderBlogTable.setIsFollowedBlogId(blockResult.blogId, false);
        }
        return blockResult;
    }

    /*
     * block a blog - result includes the list of posts that were deleted by the block so they
     * can be restored if the user undoes the block
     */
    public static void blockBlogFromReaderRemote(BlockedBlogResult blockResult, final ActionListener actionListener) {
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(17992)) {
                    ReaderActions.callActionListener(actionListener, true);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(17993)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(17994)) {
                    undoBlockBlogLocal(blockResult);
                }
                if (!ListenerUtil.mutListener.listen(17996)) {
                    if (blockResult.wasFollowing) {
                        if (!ListenerUtil.mutListener.listen(17995)) {
                            ReaderBlogTable.setIsFollowedBlogId(blockResult.blogId, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(17997)) {
                    ReaderActions.callActionListener(actionListener, false);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(17998)) {
            AppLog.i(T.READER, "blocking blog " + blockResult.blogId);
        }
        String path = "me/block/sites/" + blockResult.blogId + "/new";
        if (!ListenerUtil.mutListener.listen(17999)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    public static void undoBlockBlogFromReader(final BlockedBlogResult blockResult, final String source, final ReaderTracker readerTracker) {
        if (!ListenerUtil.mutListener.listen(18000)) {
            if (blockResult == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18001)) {
            undoBlockBlogLocal(blockResult);
        }
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                boolean success = ((ListenerUtil.mutListener.listen(18002) ? (jsonObject != null || jsonObject.optBoolean("success")) : (jsonObject != null && jsonObject.optBoolean("success"))));
                if (!ListenerUtil.mutListener.listen(18006)) {
                    // re-follow the blog if it was being followed prior to the block
                    if ((ListenerUtil.mutListener.listen(18003) ? (success || blockResult.wasFollowing) : (success && blockResult.wasFollowing))) {
                        if (!ListenerUtil.mutListener.listen(18005)) {
                            followBlogById(blockResult.blogId, blockResult.feedId, true, null, source, readerTracker);
                        }
                    } else if (!success) {
                        if (!ListenerUtil.mutListener.listen(18004)) {
                            AppLog.w(T.READER, "failed to unblock blog " + blockResult.blogId);
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(18007)) {
                    AppLog.e(T.READER, volleyError);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(18008)) {
            AppLog.i(T.READER, "unblocking blog " + blockResult.blogId);
        }
        String path = "me/block/sites/" + blockResult.blogId + "/delete";
        if (!ListenerUtil.mutListener.listen(18009)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    private static void undoBlockBlogLocal(final BlockedBlogResult blockResult) {
        if (!ListenerUtil.mutListener.listen(18012)) {
            if (blockResult.deletedRows != null) {
                if (!ListenerUtil.mutListener.listen(18011)) {
                    {
                        long _loopCounter294 = 0;
                        for (Pair<String, ReaderTagType> tagInfo : blockResult.deletedRows.keySet()) {
                            ListenerUtil.loopListener.listen("_loopCounter294", ++_loopCounter294);
                            ReaderTag tag = ReaderTagTable.getTag(tagInfo.first, tagInfo.second);
                            if (!ListenerUtil.mutListener.listen(18010)) {
                                ReaderPostTable.addOrUpdatePosts(tag, blockResult.deletedRows.get(tagInfo));
                            }
                        }
                    }
                }
            }
        }
    }
}
