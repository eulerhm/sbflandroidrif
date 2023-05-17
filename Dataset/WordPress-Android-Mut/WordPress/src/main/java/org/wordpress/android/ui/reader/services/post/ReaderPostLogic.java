package org.wordpress.android.ui.reader.services.post;

import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderTagTable;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderPostList;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.models.ReaderBlogIdPostId;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.UpdateAction;
import org.wordpress.android.ui.reader.utils.ReaderUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.UrlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPostLogic {

    private ServiceCompletionListener mCompletionListener;

    private Object mListenerCompanion;

    public ReaderPostLogic(ServiceCompletionListener listener) {
        if (!ListenerUtil.mutListener.listen(19220)) {
            mCompletionListener = listener;
        }
    }

    public void performTask(Object companion, UpdateAction action, ReaderTag tag, long blogId, long feedId) {
        if (!ListenerUtil.mutListener.listen(19221)) {
            mListenerCompanion = companion;
        }
        if (!ListenerUtil.mutListener.listen(19222)) {
            EventBus.getDefault().post(new ReaderEvents.UpdatePostsStarted(action, tag));
        }
        if (!ListenerUtil.mutListener.listen(19236)) {
            if (tag != null) {
                if (!ListenerUtil.mutListener.listen(19235)) {
                    updatePostsWithTag(tag, action);
                }
            } else if ((ListenerUtil.mutListener.listen(19227) ? (blogId >= -1) : (ListenerUtil.mutListener.listen(19226) ? (blogId <= -1) : (ListenerUtil.mutListener.listen(19225) ? (blogId < -1) : (ListenerUtil.mutListener.listen(19224) ? (blogId != -1) : (ListenerUtil.mutListener.listen(19223) ? (blogId == -1) : (blogId > -1))))))) {
                if (!ListenerUtil.mutListener.listen(19234)) {
                    updatePostsInBlog(blogId, action);
                }
            } else if ((ListenerUtil.mutListener.listen(19232) ? (feedId >= -1) : (ListenerUtil.mutListener.listen(19231) ? (feedId <= -1) : (ListenerUtil.mutListener.listen(19230) ? (feedId < -1) : (ListenerUtil.mutListener.listen(19229) ? (feedId != -1) : (ListenerUtil.mutListener.listen(19228) ? (feedId == -1) : (feedId > -1))))))) {
                if (!ListenerUtil.mutListener.listen(19233)) {
                    updatePostsInFeed(feedId, action);
                }
            }
        }
    }

    private void updatePostsWithTag(final ReaderTag tag, final UpdateAction action) {
        if (!ListenerUtil.mutListener.listen(19239)) {
            requestPostsWithTag(tag, action, new ReaderActions.UpdateResultListener() {

                @Override
                public void onUpdateResult(ReaderActions.UpdateResult result) {
                    if (!ListenerUtil.mutListener.listen(19237)) {
                        EventBus.getDefault().post(new ReaderEvents.UpdatePostsEnded(tag, result, action));
                    }
                    if (!ListenerUtil.mutListener.listen(19238)) {
                        mCompletionListener.onCompleted(mListenerCompanion);
                    }
                }
            });
        }
    }

    private void updatePostsInBlog(long blogId, final UpdateAction action) {
        ReaderActions.UpdateResultListener listener = new ReaderActions.UpdateResultListener() {

            @Override
            public void onUpdateResult(ReaderActions.UpdateResult result) {
                if (!ListenerUtil.mutListener.listen(19240)) {
                    EventBus.getDefault().post(new ReaderEvents.UpdatePostsEnded(result, action));
                }
                if (!ListenerUtil.mutListener.listen(19241)) {
                    mCompletionListener.onCompleted(mListenerCompanion);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19242)) {
            requestPostsForBlog(blogId, action, listener);
        }
    }

    private void updatePostsInFeed(long feedId, final UpdateAction action) {
        ReaderActions.UpdateResultListener listener = new ReaderActions.UpdateResultListener() {

            @Override
            public void onUpdateResult(ReaderActions.UpdateResult result) {
                if (!ListenerUtil.mutListener.listen(19243)) {
                    EventBus.getDefault().post(new ReaderEvents.UpdatePostsEnded(result, action));
                }
                if (!ListenerUtil.mutListener.listen(19244)) {
                    mCompletionListener.onCompleted(mListenerCompanion);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19245)) {
            requestPostsForFeed(feedId, action, listener);
        }
    }

    private static void requestPostsWithTag(final ReaderTag tag, final UpdateAction updateAction, final ReaderActions.UpdateResultListener resultListener) {
        String path = getRelativeEndpointForTag(tag);
        if (!ListenerUtil.mutListener.listen(19247)) {
            if (TextUtils.isEmpty(path)) {
                if (!ListenerUtil.mutListener.listen(19246)) {
                    resultListener.onUpdateResult(ReaderActions.UpdateResult.FAILED);
                }
                return;
            }
        }
        StringBuilder sb = new StringBuilder(path);
        if (!ListenerUtil.mutListener.listen(19248)) {
            // append #posts to retrieve
            sb.append("?number=").append(ReaderConstants.READER_MAX_POSTS_TO_REQUEST);
        }
        if (!ListenerUtil.mutListener.listen(19249)) {
            // return newest posts first (this is the default, but make it explicit since it's important)
            sb.append("&order=DESC");
        }
        String beforeDate;
        switch(updateAction) {
            case REQUEST_OLDER:
                // request posts older than the oldest existing post with this tag
                beforeDate = ReaderPostTable.getOldestDateWithTag(tag);
                break;
            case REQUEST_OLDER_THAN_GAP:
                // request posts older than the post with the gap marker for this tag
                beforeDate = ReaderPostTable.getGapMarkerDateForTag(tag);
                break;
            case REQUEST_NEWER:
            case REQUEST_REFRESH:
            default:
                beforeDate = null;
                break;
        }
        if (!ListenerUtil.mutListener.listen(19251)) {
            if (!TextUtils.isEmpty(beforeDate)) {
                if (!ListenerUtil.mutListener.listen(19250)) {
                    sb.append("&before=").append(UrlUtils.urlEncode(beforeDate));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(19252)) {
            sb.append("&meta=site,likes");
        }
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(19255)) {
                    // remember when this tag was updated if newer posts were requested
                    if ((ListenerUtil.mutListener.listen(19253) ? (updateAction == UpdateAction.REQUEST_NEWER && updateAction == UpdateAction.REQUEST_REFRESH) : (updateAction == UpdateAction.REQUEST_NEWER || updateAction == UpdateAction.REQUEST_REFRESH))) {
                        if (!ListenerUtil.mutListener.listen(19254)) {
                            ReaderTagTable.setTagLastUpdated(tag);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(19256)) {
                    handleUpdatePostsResponse(tag, jsonObject, updateAction, resultListener);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19257)) {
                    AppLog.e(AppLog.T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19258)) {
                    resultListener.onUpdateResult(ReaderActions.UpdateResult.FAILED);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19259)) {
            WordPress.getRestClientUtilsV1_2().get(sb.toString(), null, null, listener, errorListener);
        }
    }

    private static void requestPostsForBlog(final long blogId, final UpdateAction updateAction, final ReaderActions.UpdateResultListener resultListener) {
        String path = "read/sites/" + blogId + "/posts/?meta=site,likes";
        if (!ListenerUtil.mutListener.listen(19262)) {
            // append the date of the oldest cached post in this blog when requesting older posts
            if (updateAction == UpdateAction.REQUEST_OLDER) {
                String dateOldest = ReaderPostTable.getOldestPubDateInBlog(blogId);
                if (!ListenerUtil.mutListener.listen(19261)) {
                    if (!TextUtils.isEmpty(dateOldest)) {
                        if (!ListenerUtil.mutListener.listen(19260)) {
                            path += "&before=" + UrlUtils.urlEncode(dateOldest);
                        }
                    }
                }
            }
        }
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(19263)) {
                    handleUpdatePostsResponse(null, jsonObject, updateAction, resultListener);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19264)) {
                    AppLog.e(AppLog.T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19265)) {
                    resultListener.onUpdateResult(ReaderActions.UpdateResult.FAILED);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19266)) {
            AppLog.d(AppLog.T.READER, "updating posts in blog " + blogId);
        }
        if (!ListenerUtil.mutListener.listen(19267)) {
            WordPress.getRestClientUtilsV1_2().get(path, null, null, listener, errorListener);
        }
    }

    private static void requestPostsForFeed(final long feedId, final UpdateAction updateAction, final ReaderActions.UpdateResultListener resultListener) {
        String path = "read/feed/" + feedId + "/posts/?meta=site,likes";
        if (!ListenerUtil.mutListener.listen(19270)) {
            if (updateAction == UpdateAction.REQUEST_OLDER) {
                String dateOldest = ReaderPostTable.getOldestPubDateInFeed(feedId);
                if (!ListenerUtil.mutListener.listen(19269)) {
                    if (!TextUtils.isEmpty(dateOldest)) {
                        if (!ListenerUtil.mutListener.listen(19268)) {
                            path += "&before=" + UrlUtils.urlEncode(dateOldest);
                        }
                    }
                }
            }
        }
        com.wordpress.rest.RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(19271)) {
                    handleUpdatePostsResponse(null, jsonObject, updateAction, resultListener);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19272)) {
                    AppLog.e(AppLog.T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19273)) {
                    resultListener.onUpdateResult(ReaderActions.UpdateResult.FAILED);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19274)) {
            AppLog.d(AppLog.T.READER, "updating posts in feed " + feedId);
        }
        if (!ListenerUtil.mutListener.listen(19275)) {
            WordPress.getRestClientUtilsV1_2().get(path, null, null, listener, errorListener);
        }
    }

    /*
     * called after requesting posts with a specific tag or in a specific blog/feed
     */
    private static void handleUpdatePostsResponse(final ReaderTag tag, final JSONObject jsonObject, final UpdateAction updateAction, final ReaderActions.UpdateResultListener resultListener) {
        if (!ListenerUtil.mutListener.listen(19277)) {
            if (jsonObject == null) {
                if (!ListenerUtil.mutListener.listen(19276)) {
                    resultListener.onUpdateResult(ReaderActions.UpdateResult.FAILED);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(19322)) {
            new Thread() {

                @Override
                public void run() {
                    ReaderPostList serverPosts = ReaderPostList.fromJson(jsonObject);
                    ReaderActions.UpdateResult updateResult = ReaderPostTable.comparePosts(serverPosts);
                    if (!ListenerUtil.mutListener.listen(19319)) {
                        if (updateResult.isNewOrChanged()) {
                            // gap detection - only applies to posts with a specific tag
                            ReaderPost postWithGap = null;
                            if (!ListenerUtil.mutListener.listen(19311)) {
                                if (tag != null) {
                                    if (!ListenerUtil.mutListener.listen(19310)) {
                                        switch(updateAction) {
                                            case REQUEST_NEWER:
                                                // provided that local posts exist
                                                int numServerPosts = serverPosts.size();
                                                if (!ListenerUtil.mutListener.listen(19306)) {
                                                    if ((ListenerUtil.mutListener.listen(19292) ? ((ListenerUtil.mutListener.listen(19291) ? ((ListenerUtil.mutListener.listen(19285) ? (numServerPosts <= 2) : (ListenerUtil.mutListener.listen(19284) ? (numServerPosts > 2) : (ListenerUtil.mutListener.listen(19283) ? (numServerPosts < 2) : (ListenerUtil.mutListener.listen(19282) ? (numServerPosts != 2) : (ListenerUtil.mutListener.listen(19281) ? (numServerPosts == 2) : (numServerPosts >= 2)))))) || (ListenerUtil.mutListener.listen(19290) ? (ReaderPostTable.getNumPostsWithTag(tag) >= 0) : (ListenerUtil.mutListener.listen(19289) ? (ReaderPostTable.getNumPostsWithTag(tag) <= 0) : (ListenerUtil.mutListener.listen(19288) ? (ReaderPostTable.getNumPostsWithTag(tag) < 0) : (ListenerUtil.mutListener.listen(19287) ? (ReaderPostTable.getNumPostsWithTag(tag) != 0) : (ListenerUtil.mutListener.listen(19286) ? (ReaderPostTable.getNumPostsWithTag(tag) == 0) : (ReaderPostTable.getNumPostsWithTag(tag) > 0))))))) : ((ListenerUtil.mutListener.listen(19285) ? (numServerPosts <= 2) : (ListenerUtil.mutListener.listen(19284) ? (numServerPosts > 2) : (ListenerUtil.mutListener.listen(19283) ? (numServerPosts < 2) : (ListenerUtil.mutListener.listen(19282) ? (numServerPosts != 2) : (ListenerUtil.mutListener.listen(19281) ? (numServerPosts == 2) : (numServerPosts >= 2)))))) && (ListenerUtil.mutListener.listen(19290) ? (ReaderPostTable.getNumPostsWithTag(tag) >= 0) : (ListenerUtil.mutListener.listen(19289) ? (ReaderPostTable.getNumPostsWithTag(tag) <= 0) : (ListenerUtil.mutListener.listen(19288) ? (ReaderPostTable.getNumPostsWithTag(tag) < 0) : (ListenerUtil.mutListener.listen(19287) ? (ReaderPostTable.getNumPostsWithTag(tag) != 0) : (ListenerUtil.mutListener.listen(19286) ? (ReaderPostTable.getNumPostsWithTag(tag) == 0) : (ReaderPostTable.getNumPostsWithTag(tag) > 0)))))))) || !ReaderPostTable.hasOverlap(serverPosts, tag)) : ((ListenerUtil.mutListener.listen(19291) ? ((ListenerUtil.mutListener.listen(19285) ? (numServerPosts <= 2) : (ListenerUtil.mutListener.listen(19284) ? (numServerPosts > 2) : (ListenerUtil.mutListener.listen(19283) ? (numServerPosts < 2) : (ListenerUtil.mutListener.listen(19282) ? (numServerPosts != 2) : (ListenerUtil.mutListener.listen(19281) ? (numServerPosts == 2) : (numServerPosts >= 2)))))) || (ListenerUtil.mutListener.listen(19290) ? (ReaderPostTable.getNumPostsWithTag(tag) >= 0) : (ListenerUtil.mutListener.listen(19289) ? (ReaderPostTable.getNumPostsWithTag(tag) <= 0) : (ListenerUtil.mutListener.listen(19288) ? (ReaderPostTable.getNumPostsWithTag(tag) < 0) : (ListenerUtil.mutListener.listen(19287) ? (ReaderPostTable.getNumPostsWithTag(tag) != 0) : (ListenerUtil.mutListener.listen(19286) ? (ReaderPostTable.getNumPostsWithTag(tag) == 0) : (ReaderPostTable.getNumPostsWithTag(tag) > 0))))))) : ((ListenerUtil.mutListener.listen(19285) ? (numServerPosts <= 2) : (ListenerUtil.mutListener.listen(19284) ? (numServerPosts > 2) : (ListenerUtil.mutListener.listen(19283) ? (numServerPosts < 2) : (ListenerUtil.mutListener.listen(19282) ? (numServerPosts != 2) : (ListenerUtil.mutListener.listen(19281) ? (numServerPosts == 2) : (numServerPosts >= 2)))))) && (ListenerUtil.mutListener.listen(19290) ? (ReaderPostTable.getNumPostsWithTag(tag) >= 0) : (ListenerUtil.mutListener.listen(19289) ? (ReaderPostTable.getNumPostsWithTag(tag) <= 0) : (ListenerUtil.mutListener.listen(19288) ? (ReaderPostTable.getNumPostsWithTag(tag) < 0) : (ListenerUtil.mutListener.listen(19287) ? (ReaderPostTable.getNumPostsWithTag(tag) != 0) : (ListenerUtil.mutListener.listen(19286) ? (ReaderPostTable.getNumPostsWithTag(tag) == 0) : (ReaderPostTable.getNumPostsWithTag(tag) > 0)))))))) && !ReaderPostTable.hasOverlap(serverPosts, tag)))) {
                                                        if (!ListenerUtil.mutListener.listen(19297)) {
                                                            // treat the second to last server post as having a gap
                                                            postWithGap = serverPosts.get((ListenerUtil.mutListener.listen(19296) ? (numServerPosts % 2) : (ListenerUtil.mutListener.listen(19295) ? (numServerPosts / 2) : (ListenerUtil.mutListener.listen(19294) ? (numServerPosts * 2) : (ListenerUtil.mutListener.listen(19293) ? (numServerPosts + 2) : (numServerPosts - 2))))));
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(19302)) {
                                                            // there actually not being a gap between local & server
                                                            serverPosts.remove((ListenerUtil.mutListener.listen(19301) ? (numServerPosts % 1) : (ListenerUtil.mutListener.listen(19300) ? (numServerPosts / 1) : (ListenerUtil.mutListener.listen(19299) ? (numServerPosts * 1) : (ListenerUtil.mutListener.listen(19298) ? (numServerPosts + 1) : (numServerPosts - 1))))));
                                                        }
                                                        ReaderBlogIdPostId gapMarker = ReaderPostTable.getGapMarkerIdsForTag(tag);
                                                        if (!ListenerUtil.mutListener.listen(19305)) {
                                                            if (gapMarker != null) {
                                                                if (!ListenerUtil.mutListener.listen(19303)) {
                                                                    // delete all posts before the current gapMarker and clear the gapMarker flag.
                                                                    ReaderPostTable.deletePostsBeforeGapMarkerForTag(tag);
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(19304)) {
                                                                    ReaderPostTable.removeGapMarkerForTag(tag);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case REQUEST_OLDER_THAN_GAP:
                                                if (!ListenerUtil.mutListener.listen(19307)) {
                                                    // before the one with the gap marker, then remove the existing gap marker
                                                    ReaderPostTable.deletePostsBeforeGapMarkerForTag(tag);
                                                }
                                                if (!ListenerUtil.mutListener.listen(19308)) {
                                                    ReaderPostTable.removeGapMarkerForTag(tag);
                                                }
                                                break;
                                            case REQUEST_REFRESH:
                                                if (!ListenerUtil.mutListener.listen(19309)) {
                                                    ReaderPostTable.deletePostsWithTag(tag);
                                                }
                                                break;
                                            case REQUEST_OLDER:
                                                // no-op
                                                break;
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(19312)) {
                                ReaderPostTable.addOrUpdatePosts(tag, serverPosts);
                            }
                            if (!ListenerUtil.mutListener.listen(19315)) {
                                if (AppPrefs.shouldUpdateBookmarkPostsPseudoIds(tag)) {
                                    if (!ListenerUtil.mutListener.listen(19313)) {
                                        ReaderPostTable.updateBookmarkedPostPseudoId(serverPosts);
                                    }
                                    if (!ListenerUtil.mutListener.listen(19314)) {
                                        AppPrefs.setBookmarkPostsPseudoIdsUpdated();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(19318)) {
                                // gap marker must be set after saving server posts
                                if (postWithGap != null) {
                                    if (!ListenerUtil.mutListener.listen(19316)) {
                                        ReaderPostTable.setGapMarkerForTag(postWithGap.blogId, postWithGap.postId, tag);
                                    }
                                    if (!ListenerUtil.mutListener.listen(19317)) {
                                        AppLog.d(AppLog.T.READER, "added gap marker to tag " + tag.getTagNameForLog());
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(19278) ? (updateResult == ReaderActions.UpdateResult.UNCHANGED || updateAction == UpdateAction.REQUEST_OLDER_THAN_GAP) : (updateResult == ReaderActions.UpdateResult.UNCHANGED && updateAction == UpdateAction.REQUEST_OLDER_THAN_GAP))) {
                            if (!ListenerUtil.mutListener.listen(19279)) {
                                // edge case - request to fill gap returned nothing new, so remove the gap marker
                                ReaderPostTable.removeGapMarkerForTag(tag);
                            }
                            if (!ListenerUtil.mutListener.listen(19280)) {
                                AppLog.w(AppLog.T.READER, "attempt to fill gap returned nothing new");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(19320)) {
                        AppLog.d(AppLog.T.READER, "requested posts response = " + updateResult.toString());
                    }
                    if (!ListenerUtil.mutListener.listen(19321)) {
                        resultListener.onUpdateResult(updateResult);
                    }
                }
            }.start();
        }
    }

    /*
     * returns the endpoint to use when requesting posts with the passed tag
     */
    private static String getRelativeEndpointForTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(19323)) {
            if (tag == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(19324)) {
            // if passed tag has an assigned endpoint, return it and be done
            if (!TextUtils.isEmpty(tag.getEndpoint())) {
                return getRelativeEndpoint(tag.getEndpoint());
            }
        }
        // check the db for the endpoint
        String endpoint = ReaderTagTable.getEndpointForTag(tag);
        if (!ListenerUtil.mutListener.listen(19325)) {
            if (!TextUtils.isEmpty(endpoint)) {
                return getRelativeEndpoint(endpoint);
            }
        }
        if (!ListenerUtil.mutListener.listen(19326)) {
            // using their stored endpoints
            if (tag.tagType == ReaderTagType.DEFAULT) {
                return null;
            }
        }
        return String.format("read/tags/%s/posts", ReaderUtils.sanitizeWithDashes(tag.getTagSlug()));
    }

    /*
     * returns the passed endpoint without the unnecessary path - this is
     * needed because as of 20-Feb-2015 the /read/menu/ call returns the
     * full path but we don't want to use the full path since it may change
     * between API versions (as it did when we moved from v1 to v1.1)
     *
     * ex: https://public-api.wordpress.com/rest/v1/read/tags/fitness/posts
     * becomes just read/tags/fitness/posts
     */
    private static String getRelativeEndpoint(final String endpoint) {
        if (!ListenerUtil.mutListener.listen(19349)) {
            if ((ListenerUtil.mutListener.listen(19327) ? (endpoint != null || endpoint.startsWith("http")) : (endpoint != null && endpoint.startsWith("http")))) {
                int pos = endpoint.indexOf("/read/");
                if (!ListenerUtil.mutListener.listen(19337)) {
                    if ((ListenerUtil.mutListener.listen(19332) ? (pos >= -1) : (ListenerUtil.mutListener.listen(19331) ? (pos <= -1) : (ListenerUtil.mutListener.listen(19330) ? (pos < -1) : (ListenerUtil.mutListener.listen(19329) ? (pos != -1) : (ListenerUtil.mutListener.listen(19328) ? (pos == -1) : (pos > -1))))))) {
                        return endpoint.substring((ListenerUtil.mutListener.listen(19336) ? (pos % 1) : (ListenerUtil.mutListener.listen(19335) ? (pos / 1) : (ListenerUtil.mutListener.listen(19334) ? (pos * 1) : (ListenerUtil.mutListener.listen(19333) ? (pos - 1) : (pos + 1))))), endpoint.length());
                    }
                }
                if (!ListenerUtil.mutListener.listen(19338)) {
                    pos = endpoint.indexOf("/v1/");
                }
                if (!ListenerUtil.mutListener.listen(19348)) {
                    if ((ListenerUtil.mutListener.listen(19343) ? (pos >= -1) : (ListenerUtil.mutListener.listen(19342) ? (pos <= -1) : (ListenerUtil.mutListener.listen(19341) ? (pos < -1) : (ListenerUtil.mutListener.listen(19340) ? (pos != -1) : (ListenerUtil.mutListener.listen(19339) ? (pos == -1) : (pos > -1))))))) {
                        return endpoint.substring((ListenerUtil.mutListener.listen(19347) ? (pos % 4) : (ListenerUtil.mutListener.listen(19346) ? (pos / 4) : (ListenerUtil.mutListener.listen(19345) ? (pos * 4) : (ListenerUtil.mutListener.listen(19344) ? (pos - 4) : (pos + 4))))), endpoint.length());
                    }
                }
            }
        }
        return StringUtils.notNullStr(endpoint);
    }
}
