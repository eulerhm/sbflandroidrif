package org.wordpress.android.ui.reader.actions;

import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderLikeTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.datasets.ReaderUserTable;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderPostList;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.models.ReaderUserIdList;
import org.wordpress.android.models.ReaderUserList;
import org.wordpress.android.networking.RestClientUtils;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.actions.ReaderActions.ActionListener;
import org.wordpress.android.ui.reader.actions.ReaderActions.UpdateResult;
import org.wordpress.android.ui.reader.actions.ReaderActions.UpdateResultListener;
import org.wordpress.android.ui.reader.models.ReaderSimplePost;
import org.wordpress.android.ui.reader.models.ReaderSimplePostList;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.VolleyUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPostActions {

    private static final String TRACKING_REFERRER = "https://wordpress.com/";

    private static final Random RANDOM = new Random();

    private static final int NUM_RELATED_POSTS_TO_REQUEST = 2;

    private ReaderPostActions() {
        throw new AssertionError();
    }

    /**
     * like/unlike the passed post
     */
    public static boolean performLikeAction(final ReaderPost post, final boolean isAskingToLike, final long wpComUserId) {
        // do nothing if post's like state is same as passed
        boolean updateLocalDb = performLikeActionLocal(post, isAskingToLike, wpComUserId);
        if (!ListenerUtil.mutListener.listen(18084)) {
            if (!updateLocalDb)
                return false;
        }
        if (!ListenerUtil.mutListener.listen(18085)) {
            performLikeActionRemote(post, isAskingToLike, wpComUserId, null);
        }
        return true;
    }

    public static boolean performLikeActionLocal(final ReaderPost post, final boolean isAskingToLike, final long wpComUserId) {
        // do nothing if post's like state is same as passed
        boolean isCurrentlyLiked = ReaderPostTable.isPostLikedByCurrentUser(post);
        if (!ListenerUtil.mutListener.listen(18087)) {
            if (isCurrentlyLiked == isAskingToLike) {
                if (!ListenerUtil.mutListener.listen(18086)) {
                    AppLog.w(T.READER, "post like unchanged");
                }
                return false;
            }
        }
        // update like status and like count in local db
        int numCurrentLikes = ReaderPostTable.getNumLikesForPost(post.blogId, post.postId);
        int newNumLikes = (isAskingToLike ? (ListenerUtil.mutListener.listen(18095) ? (numCurrentLikes % 1) : (ListenerUtil.mutListener.listen(18094) ? (numCurrentLikes / 1) : (ListenerUtil.mutListener.listen(18093) ? (numCurrentLikes * 1) : (ListenerUtil.mutListener.listen(18092) ? (numCurrentLikes - 1) : (numCurrentLikes + 1))))) : (ListenerUtil.mutListener.listen(18091) ? (numCurrentLikes % 1) : (ListenerUtil.mutListener.listen(18090) ? (numCurrentLikes / 1) : (ListenerUtil.mutListener.listen(18089) ? (numCurrentLikes * 1) : (ListenerUtil.mutListener.listen(18088) ? (numCurrentLikes + 1) : (numCurrentLikes - 1))))));
        if (!ListenerUtil.mutListener.listen(18102)) {
            if ((ListenerUtil.mutListener.listen(18100) ? (newNumLikes >= 0) : (ListenerUtil.mutListener.listen(18099) ? (newNumLikes <= 0) : (ListenerUtil.mutListener.listen(18098) ? (newNumLikes > 0) : (ListenerUtil.mutListener.listen(18097) ? (newNumLikes != 0) : (ListenerUtil.mutListener.listen(18096) ? (newNumLikes == 0) : (newNumLikes < 0))))))) {
                if (!ListenerUtil.mutListener.listen(18101)) {
                    newNumLikes = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18103)) {
            ReaderPostTable.setLikesForPost(post, newNumLikes, isAskingToLike);
        }
        if (!ListenerUtil.mutListener.listen(18104)) {
            ReaderLikeTable.setCurrentUserLikesPost(post, isAskingToLike, wpComUserId);
        }
        return true;
    }

    public static void performLikeActionRemote(final ReaderPost post, final boolean isAskingToLike, final long wpComUserId, final ActionListener actionListener) {
        final String actionName = isAskingToLike ? "like" : "unlike";
        String path = "sites/" + post.blogId + "/posts/" + post.postId + "/likes/";
        if (!ListenerUtil.mutListener.listen(18107)) {
            if (isAskingToLike) {
                if (!ListenerUtil.mutListener.listen(18106)) {
                    path += "new";
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18105)) {
                    path += "mine/delete";
                }
            }
        }
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(18108)) {
                    AppLog.d(T.READER, String.format("post %s succeeded", actionName));
                }
                if (!ListenerUtil.mutListener.listen(18110)) {
                    if (actionListener != null) {
                        if (!ListenerUtil.mutListener.listen(18109)) {
                            ReaderActions.callActionListener(actionListener, true);
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String error = VolleyUtils.errStringFromVolleyError(volleyError);
                if (!ListenerUtil.mutListener.listen(18113)) {
                    if (TextUtils.isEmpty(error)) {
                        if (!ListenerUtil.mutListener.listen(18112)) {
                            AppLog.w(T.READER, String.format("post %s failed", actionName));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(18111)) {
                            AppLog.w(T.READER, String.format("post %s failed (%s)", actionName, error));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(18114)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(18115)) {
                    ReaderPostTable.setLikesForPost(post, post.numLikes, post.isLikedByCurrentUser);
                }
                if (!ListenerUtil.mutListener.listen(18116)) {
                    ReaderLikeTable.setCurrentUserLikesPost(post, post.isLikedByCurrentUser, wpComUserId);
                }
                if (!ListenerUtil.mutListener.listen(18118)) {
                    if (actionListener != null) {
                        if (!ListenerUtil.mutListener.listen(18117)) {
                            ReaderActions.callActionListener(actionListener, false);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(18119)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
    }

    /*
     * get the latest version of this post - note that the post is only considered changed if the
     * like/comment count has changed, or if the current user's like/follow status has changed
     */
    public static void updatePost(final ReaderPost localPost, final UpdateResultListener resultListener) {
        String path = "read/sites/" + localPost.blogId + "/posts/" + localPost.postId + "/?meta=site,likes";
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(18120)) {
                    handleUpdatePostResponse(localPost, jsonObject, resultListener);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(18121)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(18123)) {
                    if (resultListener != null) {
                        if (!ListenerUtil.mutListener.listen(18122)) {
                            resultListener.onUpdateResult(UpdateResult.FAILED);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(18124)) {
            AppLog.d(T.READER, "updating post");
        }
        if (!ListenerUtil.mutListener.listen(18125)) {
            WordPress.getRestClientUtilsV1_2().get(path, null, null, listener, errorListener);
        }
    }

    private static void handleUpdatePostResponse(final ReaderPost localPost, final JSONObject jsonObject, final UpdateResultListener resultListener) {
        if (!ListenerUtil.mutListener.listen(18128)) {
            if (jsonObject == null) {
                if (!ListenerUtil.mutListener.listen(18127)) {
                    if (resultListener != null) {
                        if (!ListenerUtil.mutListener.listen(18126)) {
                            resultListener.onUpdateResult(UpdateResult.FAILED);
                        }
                    }
                }
                return;
            }
        }
        final Handler handler = new Handler();
        if (!ListenerUtil.mutListener.listen(18152)) {
            new Thread() {

                @Override
                public void run() {
                    ReaderPost serverPost = ReaderPost.fromJson(jsonObject);
                    if (!ListenerUtil.mutListener.listen(18131)) {
                        // before calling isSamePost (since the difference in those IDs causes it to return false)
                        if ((ListenerUtil.mutListener.listen(18129) ? (serverPost.feedId == 0 || localPost.feedId != 0) : (serverPost.feedId == 0 && localPost.feedId != 0))) {
                            if (!ListenerUtil.mutListener.listen(18130)) {
                                serverPost.feedId = localPost.feedId;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(18134)) {
                        if ((ListenerUtil.mutListener.listen(18132) ? (serverPost.feedItemId == 0 || localPost.feedItemId != 0) : (serverPost.feedItemId == 0 && localPost.feedItemId != 0))) {
                            if (!ListenerUtil.mutListener.listen(18133)) {
                                serverPost.feedItemId = localPost.feedItemId;
                            }
                        }
                    }
                    boolean hasChanges = !serverPost.isSamePost(localPost);
                    if (!ListenerUtil.mutListener.listen(18146)) {
                        if (hasChanges) {
                            if (!ListenerUtil.mutListener.listen(18135)) {
                                AppLog.d(T.READER, "post updated");
                            }
                            if (!ListenerUtil.mutListener.listen(18136)) {
                                // https://github.com/wordpress-mobile/WordPress-Android/issues/3164
                                localPost.numReplies = serverPost.numReplies;
                            }
                            if (!ListenerUtil.mutListener.listen(18137)) {
                                localPost.numLikes = serverPost.numLikes;
                            }
                            if (!ListenerUtil.mutListener.listen(18138)) {
                                localPost.isFollowedByCurrentUser = serverPost.isFollowedByCurrentUser;
                            }
                            if (!ListenerUtil.mutListener.listen(18139)) {
                                localPost.isLikedByCurrentUser = serverPost.isLikedByCurrentUser;
                            }
                            if (!ListenerUtil.mutListener.listen(18140)) {
                                localPost.isCommentsOpen = serverPost.isCommentsOpen;
                            }
                            if (!ListenerUtil.mutListener.listen(18141)) {
                                localPost.useExcerpt = serverPost.useExcerpt;
                            }
                            if (!ListenerUtil.mutListener.listen(18142)) {
                                localPost.setTitle(serverPost.getTitle());
                            }
                            if (!ListenerUtil.mutListener.listen(18143)) {
                                localPost.setText(serverPost.getText());
                            }
                            if (!ListenerUtil.mutListener.listen(18144)) {
                                localPost.setExcerpt(serverPost.getExcerpt());
                            }
                            if (!ListenerUtil.mutListener.listen(18145)) {
                                ReaderPostTable.updatePost(localPost);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(18148)) {
                        // ensures that the liking avatars are immediately available to post detail
                        if (handlePostLikes(serverPost, jsonObject)) {
                            if (!ListenerUtil.mutListener.listen(18147)) {
                                hasChanges = true;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(18151)) {
                        if (resultListener != null) {
                            final UpdateResult result = (hasChanges ? UpdateResult.CHANGED : UpdateResult.UNCHANGED);
                            if (!ListenerUtil.mutListener.listen(18150)) {
                                handler.post(new Runnable() {

                                    public void run() {
                                        if (!ListenerUtil.mutListener.listen(18149)) {
                                            resultListener.onUpdateResult(result);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }.start();
        }
    }

    /*
     * updates local liking users based on the "likes" meta section of the post's json - requires
     * using the /sites/ endpoint with ?meta=likes - returns true if likes have changed
     */
    private static boolean handlePostLikes(final ReaderPost post, JSONObject jsonPost) {
        if (!ListenerUtil.mutListener.listen(18154)) {
            if ((ListenerUtil.mutListener.listen(18153) ? (post == null && jsonPost == null) : (post == null || jsonPost == null))) {
                return false;
            }
        }
        JSONObject jsonLikes = JSONUtils.getJSONChild(jsonPost, "meta/data/likes");
        if (!ListenerUtil.mutListener.listen(18155)) {
            if (jsonLikes == null) {
                return false;
            }
        }
        ReaderUserList likingUsers = ReaderUserList.fromJsonLikes(jsonLikes);
        ReaderUserIdList likingUserIds = likingUsers.getUserIds();
        ReaderUserIdList existingIds = ReaderLikeTable.getLikesForPost(post);
        if (!ListenerUtil.mutListener.listen(18156)) {
            if (likingUserIds.isSameList(existingIds)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(18157)) {
            ReaderUserTable.addOrUpdateUsers(likingUsers);
        }
        if (!ListenerUtil.mutListener.listen(18158)) {
            ReaderLikeTable.setLikesForPost(post, likingUserIds);
        }
        return true;
    }

    /**
     * similar to updatePost, but used when post doesn't already exist in local db
     */
    public static void requestBlogPost(final long blogId, final long postId, final ReaderActions.OnRequestListener requestListener) {
        String path = "read/sites/" + blogId + "/posts/" + postId + "/?meta=site,likes";
        if (!ListenerUtil.mutListener.listen(18159)) {
            requestPost(WordPress.getRestClientUtilsV1_1(), path, requestListener);
        }
    }

    /**
     * similar to updatePost, but used when post doesn't already exist in local db
     */
    public static void requestFeedPost(final long feedId, final long feedItemId, final ReaderActions.OnRequestListener<String> requestListener) {
        String path = "read/feed/" + feedId + "/posts/" + feedItemId + "/?meta=site,likes";
        if (!ListenerUtil.mutListener.listen(18160)) {
            requestPost(WordPress.getRestClientUtilsV1_3(), path, requestListener);
        }
    }

    /**
     * similar to updatePost, but used when post doesn't already exist in local db
     */
    public static void requestBlogPost(final String blogSlug, final String postSlug, final ReaderActions.OnRequestListener<String> requestListener) {
        String path = "sites/" + blogSlug + "/posts/slug:" + postSlug + "/?meta=site,likes";
        if (!ListenerUtil.mutListener.listen(18161)) {
            requestPost(WordPress.getRestClientUtilsV1_1(), path, requestListener);
        }
    }

    private static void requestPost(RestClientUtils restClientUtils, String path, final ReaderActions.OnRequestListener<String> requestListener) {
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                ReaderPost post = ReaderPost.fromJson(jsonObject);
                if (!ListenerUtil.mutListener.listen(18162)) {
                    ReaderPostTable.addPost(post);
                }
                if (!ListenerUtil.mutListener.listen(18163)) {
                    handlePostLikes(post, jsonObject);
                }
                if (!ListenerUtil.mutListener.listen(18165)) {
                    if (requestListener != null) {
                        if (!ListenerUtil.mutListener.listen(18164)) {
                            requestListener.onSuccess(post.getBlogUrl());
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(18166)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(18178)) {
                    if (requestListener != null) {
                        int statusCode = 0;
                        // "body":{"error":"unauthorized","message":"User cannot access this private blog."}}
                        JSONObject jsonObject = VolleyUtils.volleyErrorToJSON(volleyError);
                        if (!ListenerUtil.mutListener.listen(18169)) {
                            if ((ListenerUtil.mutListener.listen(18167) ? (jsonObject != null || jsonObject.has("code")) : (jsonObject != null && jsonObject.has("code")))) {
                                if (!ListenerUtil.mutListener.listen(18168)) {
                                    statusCode = jsonObject.optInt("code");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(18176)) {
                            if ((ListenerUtil.mutListener.listen(18174) ? (statusCode >= 0) : (ListenerUtil.mutListener.listen(18173) ? (statusCode <= 0) : (ListenerUtil.mutListener.listen(18172) ? (statusCode > 0) : (ListenerUtil.mutListener.listen(18171) ? (statusCode < 0) : (ListenerUtil.mutListener.listen(18170) ? (statusCode != 0) : (statusCode == 0))))))) {
                                if (!ListenerUtil.mutListener.listen(18175)) {
                                    statusCode = VolleyUtils.statusCodeFromVolleyError(volleyError);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(18177)) {
                            requestListener.onFailure(statusCode);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(18179)) {
            AppLog.d(T.READER, "requesting post");
        }
        if (!ListenerUtil.mutListener.listen(18180)) {
            restClientUtils.get(path, null, null, listener, errorListener);
        }
    }

    private static String getTrackingPixelForPost(@NonNull ReaderPost post) {
        return "https://pixel.wp.com/g.gif?v=wpcom&reader=1" + "&blog=" + post.blogId + "&post=" + post.postId + "&host=" + UrlUtils.urlEncode(UrlUtils.getHost(post.getBlogUrl())) + "&ref=" + UrlUtils.urlEncode(TRACKING_REFERRER) + "&t=" + RANDOM.nextInt();
    }

    public static void bumpPageViewForPost(SiteStore siteStore, long blogId, long postId) {
        if (!ListenerUtil.mutListener.listen(18181)) {
            bumpPageViewForPost(siteStore, ReaderPostTable.getBlogPost(blogId, postId, true));
        }
    }

    public static void bumpPageViewForPost(SiteStore siteStore, ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(18182)) {
            if (post == null) {
                return;
            }
        }
        // this is a private post since we count views for private posts from owner or member
        SiteModel site = siteStore.getSiteBySiteId(post.blogId);
        if (!ListenerUtil.mutListener.listen(18185)) {
            // site will be null here if the user is not the owner or a member of the site
            if ((ListenerUtil.mutListener.listen(18183) ? (site != null || !post.isPrivate) : (site != null && !post.isPrivate))) {
                if (!ListenerUtil.mutListener.listen(18184)) {
                    AppLog.d(T.READER, "skipped bump page view - user is admin");
                }
                return;
            }
        }
        Response.Listener<String> listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (!ListenerUtil.mutListener.listen(18186)) {
                    AppLog.d(T.READER, "bump page view succeeded");
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(18187)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(18188)) {
                    AppLog.w(T.READER, "bump page view failed");
                }
            }
        };
        Request request = new StringRequest(Request.Method.GET, getTrackingPixelForPost(post), listener, errorListener) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // call will fail without correct refer(r) er
                Map<String, String> headers = new HashMap<>();
                if (!ListenerUtil.mutListener.listen(18189)) {
                    headers.put("Referer", TRACKING_REFERRER);
                }
                return headers;
            }
        };
        if (!ListenerUtil.mutListener.listen(18190)) {
            WordPress.requestQueue.add(request);
        }
    }

    /*
     * request posts related to the passed one, endpoint returns a combined list of related posts
     * posts from across wp.com and related posts from the same site as the passed post
     */
    public static void requestRelatedPosts(final ReaderPost sourcePost) {
        if (!ListenerUtil.mutListener.listen(18191)) {
            if (sourcePost == null) {
                return;
            }
        }
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(18192)) {
                    handleRelatedPostsResponse(sourcePost, jsonObject);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(18193)) {
                    AppLog.w(T.READER, "updateRelatedPosts failed");
                }
                if (!ListenerUtil.mutListener.listen(18194)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(18195)) {
                    EventBus.getDefault().post(new ReaderEvents.RelatedPostsUpdated(sourcePost, new ReaderSimplePostList(), new ReaderSimplePostList(), false));
                }
            }
        };
        String path = "/read/site/" + sourcePost.blogId + "/post/" + sourcePost.postId + "/related" + "?size_local=" + NUM_RELATED_POSTS_TO_REQUEST + "&size_global=" + NUM_RELATED_POSTS_TO_REQUEST + "&fields=" + ReaderSimplePost.SIMPLE_POST_FIELDS;
        if (!ListenerUtil.mutListener.listen(18196)) {
            WordPress.getRestClientUtilsV1_2().get(path, null, null, listener, errorListener);
        }
    }

    private static void handleRelatedPostsResponse(final ReaderPost sourcePost, final JSONObject jsonObject) {
        if (!ListenerUtil.mutListener.listen(18197)) {
            if (jsonObject == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(18204)) {
            new Thread() {

                @Override
                public void run() {
                    JSONArray jsonPosts = jsonObject.optJSONArray("posts");
                    if (!ListenerUtil.mutListener.listen(18203)) {
                        if (jsonPosts != null) {
                            ReaderSimplePostList allRelatedPosts = ReaderSimplePostList.fromJsonPosts(jsonPosts);
                            // split into posts from the passed site (local) and from across wp.com (global)
                            ReaderSimplePostList localRelatedPosts = new ReaderSimplePostList();
                            ReaderSimplePostList globalRelatedPosts = new ReaderSimplePostList();
                            if (!ListenerUtil.mutListener.listen(18201)) {
                                {
                                    long _loopCounter295 = 0;
                                    for (ReaderSimplePost relatedPost : allRelatedPosts) {
                                        ListenerUtil.loopListener.listen("_loopCounter295", ++_loopCounter295);
                                        if (!ListenerUtil.mutListener.listen(18200)) {
                                            if (relatedPost.getSiteId() == sourcePost.blogId) {
                                                if (!ListenerUtil.mutListener.listen(18199)) {
                                                    localRelatedPosts.add(relatedPost);
                                                }
                                            } else {
                                                if (!ListenerUtil.mutListener.listen(18198)) {
                                                    globalRelatedPosts.add(relatedPost);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(18202)) {
                                EventBus.getDefault().post(new ReaderEvents.RelatedPostsUpdated(sourcePost, localRelatedPosts, globalRelatedPosts, true));
                            }
                        }
                    }
                }
            }.start();
        }
    }

    public static void addToBookmarked(@NonNull final ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(18212)) {
            if (!post.isBookmarked) {
                ReaderPostList readerPosts = new ReaderPostList();
                if (!ListenerUtil.mutListener.listen(18207)) {
                    readerPosts.add(post);
                }
                ReaderTagList bookmarkTags = ReaderTagTable.getBookmarkTags();
                if (!ListenerUtil.mutListener.listen(18210)) {
                    {
                        long _loopCounter296 = 0;
                        for (ReaderTag tag : bookmarkTags) {
                            ListenerUtil.loopListener.listen("_loopCounter296", ++_loopCounter296);
                            if (!ListenerUtil.mutListener.listen(18208)) {
                                post.setDateTagged(DateTimeUtils.iso8601UTCFromDate(new Date()));
                            }
                            if (!ListenerUtil.mutListener.listen(18209)) {
                                ReaderPostTable.addOrUpdatePosts(tag, readerPosts);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(18211)) {
                    ReaderPostTable.setBookmarkFlag(post.blogId, post.postId, true);
                }
            } else {
                String msg = "addToBookmarked called on an already bookmarked post.";
                if (!ListenerUtil.mutListener.listen(18205)) {
                    AppLog.w(T.READER, msg);
                }
                if (!ListenerUtil.mutListener.listen(18206)) {
                    if (BuildConfig.DEBUG) {
                        throw new RuntimeException(msg);
                    }
                }
            }
        }
    }

    public static void removeFromBookmarked(@NonNull final ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(18216)) {
            if (post.isBookmarked) {
                if (!ListenerUtil.mutListener.listen(18215)) {
                    ReaderPostTable.setBookmarkFlag(post.blogId, post.postId, false);
                }
            } else {
                String msg = "removeFromBookmarked called on a post which wasn't bookmarked.";
                if (!ListenerUtil.mutListener.listen(18213)) {
                    AppLog.w(T.READER, msg);
                }
                if (!ListenerUtil.mutListener.listen(18214)) {
                    if (BuildConfig.DEBUG) {
                        throw new RuntimeException(msg);
                    }
                }
            }
        }
    }
}
