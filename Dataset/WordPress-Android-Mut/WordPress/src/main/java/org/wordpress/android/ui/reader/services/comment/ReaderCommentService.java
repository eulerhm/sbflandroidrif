package org.wordpress.android.ui.reader.services.comment;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderCommentTable;
import org.wordpress.android.datasets.ReaderDatabase;
import org.wordpress.android.datasets.ReaderLikeTable;
import org.wordpress.android.datasets.ReaderUserTable;
import org.wordpress.android.models.ReaderComment;
import org.wordpress.android.models.ReaderCommentList;
import org.wordpress.android.models.ReaderUserList;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.ReaderEvents.UpdateCommentsScenario;
import org.wordpress.android.ui.reader.actions.ReaderActions;
import org.wordpress.android.ui.reader.actions.ReaderActions.UpdateResult;
import org.wordpress.android.ui.reader.actions.ReaderActions.UpdateResultListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.JSONUtils;
import static org.wordpress.android.ui.reader.ReaderEvents.UpdateCommentsScenario.COMMENT_SNIPPET;
import static org.wordpress.android.ui.reader.ReaderEvents.UpdateCommentsScenario.GENERIC;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderCommentService extends Service {

    private static final String ARG_POST_ID = "post_id";

    private static final String ARG_BLOG_ID = "blog_id";

    private static final String ARG_COMMENT_ID = "comment_id";

    private static final String ARG_PAGE_INFO = "page_info";

    private enum PageInfo {

        FIRST_PAGE, NEXT_PAGE, COMMENTS_SNIPPET_PAGE
    }

    private static int mCurrentPage;

    public static void startService(Context context, long blogId, long postId, boolean requestNextPage) {
        if (!ListenerUtil.mutListener.listen(19120)) {
            if (context == null) {
                return;
            }
        }
        Intent intent = new Intent(context, ReaderCommentService.class);
        if (!ListenerUtil.mutListener.listen(19121)) {
            intent.putExtra(ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(19122)) {
            intent.putExtra(ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(19123)) {
            intent.putExtra(ARG_PAGE_INFO, requestNextPage ? PageInfo.NEXT_PAGE : PageInfo.FIRST_PAGE);
        }
        if (!ListenerUtil.mutListener.listen(19124)) {
            context.startService(intent);
        }
    }

    // Requests comments until the passed commentId is found
    public static void startServiceForComment(Context context, long blogId, long postId, long commentId) {
        if (!ListenerUtil.mutListener.listen(19125)) {
            if (context == null) {
                return;
            }
        }
        Intent intent = new Intent(context, ReaderCommentService.class);
        if (!ListenerUtil.mutListener.listen(19126)) {
            intent.putExtra(ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(19127)) {
            intent.putExtra(ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(19128)) {
            intent.putExtra(ARG_COMMENT_ID, commentId);
        }
        if (!ListenerUtil.mutListener.listen(19129)) {
            intent.putExtra(ARG_PAGE_INFO, PageInfo.FIRST_PAGE);
        }
        if (!ListenerUtil.mutListener.listen(19130)) {
            context.startService(intent);
        }
    }

    public static void startServiceForCommentSnippet(Context context, long blogId, long postId) {
        if (!ListenerUtil.mutListener.listen(19131)) {
            if (context == null) {
                return;
            }
        }
        Intent intent = new Intent(context, ReaderCommentService.class);
        if (!ListenerUtil.mutListener.listen(19132)) {
            intent.putExtra(ARG_BLOG_ID, blogId);
        }
        if (!ListenerUtil.mutListener.listen(19133)) {
            intent.putExtra(ARG_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(19134)) {
            intent.putExtra(ARG_PAGE_INFO, PageInfo.COMMENTS_SNIPPET_PAGE);
        }
        if (!ListenerUtil.mutListener.listen(19135)) {
            context.startService(intent);
        }
    }

    public static void stopService(Context context) {
        if (!ListenerUtil.mutListener.listen(19136)) {
            if (context == null) {
                return;
            }
        }
        Intent intent = new Intent(context, ReaderCommentService.class);
        if (!ListenerUtil.mutListener.listen(19137)) {
            context.stopService(intent);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(19138)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(19139)) {
            AppLog.i(AppLog.T.READER, "reader comment service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(19140)) {
            AppLog.i(AppLog.T.READER, "reader comment service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(19141)) {
            super.onDestroy();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(19142)) {
            if (intent == null) {
                return START_NOT_STICKY;
            }
        }
        final long blogId = intent.getLongExtra(ARG_BLOG_ID, 0);
        final long postId = intent.getLongExtra(ARG_POST_ID, 0);
        final long commentId = intent.getLongExtra(ARG_COMMENT_ID, 0);
        PageInfo pageInfo = (PageInfo) intent.getSerializableExtra(ARG_PAGE_INFO);
        UpdateCommentsScenario commentsScenario = pageInfo == PageInfo.COMMENTS_SNIPPET_PAGE ? COMMENT_SNIPPET : GENERIC;
        if (!ListenerUtil.mutListener.listen(19143)) {
            EventBus.getDefault().post(new ReaderEvents.UpdateCommentsStarted(commentsScenario, blogId, postId));
        }
        final int commentsToRequest = pageInfo == PageInfo.COMMENTS_SNIPPET_PAGE ? ReaderConstants.READER_COMMENTS_TO_REQUEST_FOR_POST_SNIPPET : ReaderConstants.READER_MAX_COMMENTS_TO_REQUEST;
        if (!ListenerUtil.mutListener.listen(19150)) {
            switch(pageInfo) {
                case NEXT_PAGE:
                    int prevPage = ReaderCommentTable.getLastPageNumberForPost(blogId, postId);
                    if (!ListenerUtil.mutListener.listen(19148)) {
                        mCurrentPage = (ListenerUtil.mutListener.listen(19147) ? (prevPage % 1) : (ListenerUtil.mutListener.listen(19146) ? (prevPage / 1) : (ListenerUtil.mutListener.listen(19145) ? (prevPage * 1) : (ListenerUtil.mutListener.listen(19144) ? (prevPage - 1) : (prevPage + 1)))));
                    }
                    break;
                case FIRST_PAGE:
                case COMMENTS_SNIPPET_PAGE:
                default:
                    if (!ListenerUtil.mutListener.listen(19149)) {
                        mCurrentPage = 1;
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(19165)) {
            updateCommentsForPost(blogId, postId, mCurrentPage, commentsToRequest, new UpdateResultListener() {

                @Override
                public void onUpdateResult(UpdateResult result) {
                    if (!ListenerUtil.mutListener.listen(19164)) {
                        if ((ListenerUtil.mutListener.listen(19155) ? (commentId >= 0) : (ListenerUtil.mutListener.listen(19154) ? (commentId <= 0) : (ListenerUtil.mutListener.listen(19153) ? (commentId < 0) : (ListenerUtil.mutListener.listen(19152) ? (commentId != 0) : (ListenerUtil.mutListener.listen(19151) ? (commentId == 0) : (commentId > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(19163)) {
                                if ((ListenerUtil.mutListener.listen(19158) ? (ReaderCommentTable.commentExists(blogId, postId, commentId) && !result.isNewOrChanged()) : (ReaderCommentTable.commentExists(blogId, postId, commentId) || !result.isNewOrChanged()))) {
                                    if (!ListenerUtil.mutListener.listen(19161)) {
                                        EventBus.getDefault().post(new ReaderEvents.UpdateCommentsEnded(result, commentsScenario, blogId, postId));
                                    }
                                    if (!ListenerUtil.mutListener.listen(19162)) {
                                        stopSelf();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(19159)) {
                                        // Comment not found yet, request the next page
                                        mCurrentPage++;
                                    }
                                    if (!ListenerUtil.mutListener.listen(19160)) {
                                        updateCommentsForPost(blogId, postId, mCurrentPage, commentsToRequest, this);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(19156)) {
                                EventBus.getDefault().post(new ReaderEvents.UpdateCommentsEnded(result, commentsScenario, blogId, postId));
                            }
                            if (!ListenerUtil.mutListener.listen(19157)) {
                                stopSelf();
                            }
                        }
                    }
                }
            });
        }
        return START_NOT_STICKY;
    }

    private static void updateCommentsForPost(final long blogId, final long postId, final int pageNumber, final int commentsToRequest, final ReaderActions.UpdateResultListener resultListener) {
        String path = "sites/" + blogId + "/posts/" + postId + "/replies/" + "?number=" + Integer.toString(commentsToRequest) + "&meta=likes" + "&force=wpcom" + "&hierarchical=true" + "&order=ASC" + "&page=" + pageNumber;
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(19166)) {
                    handleUpdateCommentsResponse(jsonObject, blogId, postId, pageNumber, resultListener);
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(19167)) {
                    AppLog.e(AppLog.T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(19168)) {
                    resultListener.onUpdateResult(ReaderActions.UpdateResult.FAILED);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(19169)) {
            AppLog.d(AppLog.T.READER, "updating comments");
        }
        if (!ListenerUtil.mutListener.listen(19170)) {
            WordPress.getRestClientUtilsV1_1().get(path, null, null, listener, errorListener);
        }
    }

    private static void handleUpdateCommentsResponse(final JSONObject jsonObject, final long blogId, final long postId, final int pageNumber, final ReaderActions.UpdateResultListener resultListener) {
        if (!ListenerUtil.mutListener.listen(19172)) {
            if (jsonObject == null) {
                if (!ListenerUtil.mutListener.listen(19171)) {
                    resultListener.onUpdateResult(ReaderActions.UpdateResult.FAILED);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(19202)) {
            new Thread() {

                @Override
                public void run() {
                    final boolean hasNewComments;
                    if (!ListenerUtil.mutListener.listen(19173)) {
                        ReaderDatabase.getWritableDb().beginTransaction();
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(19181)) {
                            // purge existing comments if this was a request for the first page of comments
                            if ((ListenerUtil.mutListener.listen(19179) ? (pageNumber >= 1) : (ListenerUtil.mutListener.listen(19178) ? (pageNumber <= 1) : (ListenerUtil.mutListener.listen(19177) ? (pageNumber > 1) : (ListenerUtil.mutListener.listen(19176) ? (pageNumber < 1) : (ListenerUtil.mutListener.listen(19175) ? (pageNumber != 1) : (pageNumber == 1))))))) {
                                if (!ListenerUtil.mutListener.listen(19180)) {
                                    ReaderCommentTable.purgeCommentsForPost(blogId, postId);
                                }
                            }
                        }
                        ReaderCommentList serverComments = new ReaderCommentList();
                        JSONArray jsonCommentList = jsonObject.optJSONArray("comments");
                        if (!ListenerUtil.mutListener.listen(19193)) {
                            if (jsonCommentList != null) {
                                if (!ListenerUtil.mutListener.listen(19192)) {
                                    {
                                        long _loopCounter307 = 0;
                                        for (int i = 0; (ListenerUtil.mutListener.listen(19191) ? (i >= jsonCommentList.length()) : (ListenerUtil.mutListener.listen(19190) ? (i <= jsonCommentList.length()) : (ListenerUtil.mutListener.listen(19189) ? (i > jsonCommentList.length()) : (ListenerUtil.mutListener.listen(19188) ? (i != jsonCommentList.length()) : (ListenerUtil.mutListener.listen(19187) ? (i == jsonCommentList.length()) : (i < jsonCommentList.length())))))); i++) {
                                            ListenerUtil.loopListener.listen("_loopCounter307", ++_loopCounter307);
                                            JSONObject jsonComment = jsonCommentList.optJSONObject(i);
                                            // extract this comment and add it to the list
                                            ReaderComment comment = ReaderComment.fromJson(jsonComment, blogId);
                                            if (!ListenerUtil.mutListener.listen(19182)) {
                                                comment.pageNumber = pageNumber;
                                            }
                                            if (!ListenerUtil.mutListener.listen(19183)) {
                                                serverComments.add(comment);
                                            }
                                            // extract and save likes for this comment
                                            JSONObject jsonLikes = JSONUtils.getJSONChild(jsonComment, "meta/data/likes");
                                            if (!ListenerUtil.mutListener.listen(19186)) {
                                                if (jsonLikes != null) {
                                                    ReaderUserList likingUsers = ReaderUserList.fromJsonLikes(jsonLikes);
                                                    if (!ListenerUtil.mutListener.listen(19184)) {
                                                        ReaderUserTable.addOrUpdateUsers(likingUsers);
                                                    }
                                                    if (!ListenerUtil.mutListener.listen(19185)) {
                                                        ReaderLikeTable.setLikesForComment(comment, likingUsers.getUserIds());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        hasNewComments = ((ListenerUtil.mutListener.listen(19198) ? (serverComments.size() >= 0) : (ListenerUtil.mutListener.listen(19197) ? (serverComments.size() <= 0) : (ListenerUtil.mutListener.listen(19196) ? (serverComments.size() < 0) : (ListenerUtil.mutListener.listen(19195) ? (serverComments.size() != 0) : (ListenerUtil.mutListener.listen(19194) ? (serverComments.size() == 0) : (serverComments.size() > 0)))))));
                        if (!ListenerUtil.mutListener.listen(19199)) {
                            // save to db regardless of whether any are new so changes to likes are stored
                            ReaderCommentTable.addOrUpdateComments(serverComments);
                        }
                        if (!ListenerUtil.mutListener.listen(19200)) {
                            ReaderDatabase.getWritableDb().setTransactionSuccessful();
                        }
                    } finally {
                        if (!ListenerUtil.mutListener.listen(19174)) {
                            ReaderDatabase.getWritableDb().endTransaction();
                        }
                    }
                    ReaderActions.UpdateResult result = (hasNewComments ? ReaderActions.UpdateResult.HAS_NEW : ReaderActions.UpdateResult.UNCHANGED);
                    if (!ListenerUtil.mutListener.listen(19201)) {
                        resultListener.onUpdateResult(result);
                    }
                }
            }.start();
        }
    }
}
