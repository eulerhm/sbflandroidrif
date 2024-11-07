package org.wordpress.android.ui.reader.actions;

import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.wordpress.rest.RestRequest;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.datasets.ReaderCommentTable;
import org.wordpress.android.datasets.ReaderLikeTable;
import org.wordpress.android.datasets.ReaderPostTable;
import org.wordpress.android.datasets.ReaderUserTable;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.models.ReaderComment;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderUser;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.JSONUtils;
import org.wordpress.android.util.VolleyUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderCommentActions {

    /*
     * used by post detail to generate a temporary "fake" comment id (see below)
     */
    public static long generateFakeCommentId() {
        return System.currentTimeMillis();
    }

    /*
     * add the passed comment text to the passed post - caller must pass a unique "fake" comment id
     * to give the comment that's generated locally
     */
    public static ReaderComment submitPostComment(final ReaderPost post, final long fakeCommentId, final String commentText, final long replyToCommentId, final ReaderActions.CommentActionListener actionListener, final long wpComUserId) {
        if (!ListenerUtil.mutListener.listen(18014)) {
            if ((ListenerUtil.mutListener.listen(18013) ? (post == null && TextUtils.isEmpty(commentText)) : (post == null || TextUtils.isEmpty(commentText)))) {
                return null;
            }
        }
        // determine which page this new comment should be assigned to
        final int pageNumber;
        if ((ListenerUtil.mutListener.listen(18019) ? (replyToCommentId >= 0) : (ListenerUtil.mutListener.listen(18018) ? (replyToCommentId <= 0) : (ListenerUtil.mutListener.listen(18017) ? (replyToCommentId > 0) : (ListenerUtil.mutListener.listen(18016) ? (replyToCommentId < 0) : (ListenerUtil.mutListener.listen(18015) ? (replyToCommentId == 0) : (replyToCommentId != 0))))))) {
            pageNumber = ReaderCommentTable.getPageNumberForComment(post.blogId, post.postId, replyToCommentId);
        } else {
            pageNumber = ReaderCommentTable.getLastPageNumberForPost(post.blogId, post.postId);
        }
        // to be posted
        ReaderComment newComment = new ReaderComment();
        if (!ListenerUtil.mutListener.listen(18020)) {
            newComment.commentId = fakeCommentId;
        }
        if (!ListenerUtil.mutListener.listen(18021)) {
            newComment.postId = post.postId;
        }
        if (!ListenerUtil.mutListener.listen(18022)) {
            newComment.blogId = post.blogId;
        }
        if (!ListenerUtil.mutListener.listen(18023)) {
            newComment.parentId = replyToCommentId;
        }
        if (!ListenerUtil.mutListener.listen(18024)) {
            newComment.pageNumber = pageNumber;
        }
        if (!ListenerUtil.mutListener.listen(18025)) {
            newComment.setStatus(CommentStatus.APPROVED.toString());
        }
        if (!ListenerUtil.mutListener.listen(18026)) {
            newComment.setText(commentText);
        }
        Date dtPublished = new Date();
        if (!ListenerUtil.mutListener.listen(18027)) {
            newComment.setPublished(DateTimeUtils.iso8601UTCFromDate(dtPublished));
        }
        if (!ListenerUtil.mutListener.listen(18028)) {
            newComment.timestamp = dtPublished.getTime();
        }
        ReaderUser currentUser = ReaderUserTable.getCurrentUser(wpComUserId);
        if (!ListenerUtil.mutListener.listen(18031)) {
            if (currentUser != null) {
                if (!ListenerUtil.mutListener.listen(18029)) {
                    newComment.setAuthorAvatar(currentUser.getAvatarUrl());
                }
                if (!ListenerUtil.mutListener.listen(18030)) {
                    newComment.setAuthorName(currentUser.getDisplayName());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(18032)) {
            ReaderCommentTable.addOrUpdateComment(newComment);
        }
        // different endpoint depending on whether the new comment is a reply to another comment
        final String path;
        if ((ListenerUtil.mutListener.listen(18037) ? (replyToCommentId >= 0) : (ListenerUtil.mutListener.listen(18036) ? (replyToCommentId <= 0) : (ListenerUtil.mutListener.listen(18035) ? (replyToCommentId > 0) : (ListenerUtil.mutListener.listen(18034) ? (replyToCommentId < 0) : (ListenerUtil.mutListener.listen(18033) ? (replyToCommentId != 0) : (replyToCommentId == 0))))))) {
            path = "sites/" + post.blogId + "/posts/" + post.postId + "/replies/new";
        } else {
            path = "sites/" + post.blogId + "/comments/" + Long.toString(replyToCommentId) + "/replies/new";
        }
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(18038)) {
            params.put("content", commentText);
        }
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if (!ListenerUtil.mutListener.listen(18039)) {
                    ReaderCommentTable.deleteComment(post, fakeCommentId);
                }
                if (!ListenerUtil.mutListener.listen(18040)) {
                    AppLog.i(T.READER, "comment succeeded");
                }
                ReaderComment newComment = ReaderComment.fromJson(jsonObject, post.blogId);
                if (!ListenerUtil.mutListener.listen(18041)) {
                    newComment.pageNumber = pageNumber;
                }
                if (!ListenerUtil.mutListener.listen(18042)) {
                    ReaderCommentTable.addOrUpdateComment(newComment);
                }
                if (!ListenerUtil.mutListener.listen(18043)) {
                    ReaderPostTable.incNumCommentsForPost(post.blogId, post.postId);
                }
                if (!ListenerUtil.mutListener.listen(18045)) {
                    if (actionListener != null) {
                        if (!ListenerUtil.mutListener.listen(18044)) {
                            actionListener.onActionResult(true, newComment);
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (!ListenerUtil.mutListener.listen(18046)) {
                    ReaderCommentTable.deleteComment(post, fakeCommentId);
                }
                if (!ListenerUtil.mutListener.listen(18047)) {
                    AppLog.w(T.READER, "comment failed");
                }
                if (!ListenerUtil.mutListener.listen(18048)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(18050)) {
                    if (actionListener != null) {
                        if (!ListenerUtil.mutListener.listen(18049)) {
                            actionListener.onActionResult(false, null);
                        }
                    }
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(18051)) {
            AppLog.i(T.READER, "submitting comment");
        }
        if (!ListenerUtil.mutListener.listen(18052)) {
            WordPress.getRestClientUtilsV1_1().post(path, params, null, listener, errorListener);
        }
        return newComment;
    }

    /*
     * like or unlike the passed comment
     */
    public static boolean performLikeAction(final ReaderComment comment, boolean isAskingToLike, final long wpComUserId) {
        if (!ListenerUtil.mutListener.listen(18053)) {
            if (comment == null) {
                return false;
            }
        }
        // make sure like status is changing
        boolean isCurrentlyLiked = ReaderCommentTable.isCommentLikedByCurrentUser(comment);
        if (!ListenerUtil.mutListener.listen(18055)) {
            if (isCurrentlyLiked == isAskingToLike) {
                if (!ListenerUtil.mutListener.listen(18054)) {
                    AppLog.w(T.READER, "comment like unchanged");
                }
                return false;
            }
        }
        // update like status and like count in local db
        int newNumLikes = (isAskingToLike ? comment.numLikes + 1 : (ListenerUtil.mutListener.listen(18059) ? (comment.numLikes % 1) : (ListenerUtil.mutListener.listen(18058) ? (comment.numLikes / 1) : (ListenerUtil.mutListener.listen(18057) ? (comment.numLikes * 1) : (ListenerUtil.mutListener.listen(18056) ? (comment.numLikes + 1) : (comment.numLikes - 1))))));
        if (!ListenerUtil.mutListener.listen(18060)) {
            ReaderCommentTable.setLikesForComment(comment, newNumLikes, isAskingToLike);
        }
        if (!ListenerUtil.mutListener.listen(18061)) {
            ReaderLikeTable.setCurrentUserLikesComment(comment, isAskingToLike, wpComUserId);
        }
        // sites/$site/comments/$comment_ID/likes/new
        final String actionName = isAskingToLike ? "like" : "unlike";
        String path = "sites/" + comment.blogId + "/comments/" + comment.commentId + "/likes/";
        if (!ListenerUtil.mutListener.listen(18064)) {
            if (isAskingToLike) {
                if (!ListenerUtil.mutListener.listen(18063)) {
                    path += "new";
                }
            } else {
                if (!ListenerUtil.mutListener.listen(18062)) {
                    path += "mine/delete";
                }
            }
        }
        RestRequest.Listener listener = new RestRequest.Listener() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                boolean success = ((ListenerUtil.mutListener.listen(18065) ? (jsonObject != null || JSONUtils.getBool(jsonObject, "success")) : (jsonObject != null && JSONUtils.getBool(jsonObject, "success"))));
                if (!ListenerUtil.mutListener.listen(18070)) {
                    if (success) {
                        if (!ListenerUtil.mutListener.listen(18069)) {
                            AppLog.d(T.READER, String.format("comment %s succeeded", actionName));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(18066)) {
                            AppLog.w(T.READER, String.format("comment %s failed", actionName));
                        }
                        if (!ListenerUtil.mutListener.listen(18067)) {
                            ReaderCommentTable.setLikesForComment(comment, comment.numLikes, comment.isLikedByCurrentUser);
                        }
                        if (!ListenerUtil.mutListener.listen(18068)) {
                            ReaderLikeTable.setCurrentUserLikesComment(comment, comment.isLikedByCurrentUser, wpComUserId);
                        }
                    }
                }
            }
        };
        RestRequest.ErrorListener errorListener = new RestRequest.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String error = VolleyUtils.errStringFromVolleyError(volleyError);
                if (!ListenerUtil.mutListener.listen(18073)) {
                    if (TextUtils.isEmpty(error)) {
                        if (!ListenerUtil.mutListener.listen(18072)) {
                            AppLog.w(T.READER, String.format("comment %s failed", actionName));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(18071)) {
                            AppLog.w(T.READER, String.format("comment %s failed (%s)", actionName, error));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(18074)) {
                    AppLog.e(T.READER, volleyError);
                }
                if (!ListenerUtil.mutListener.listen(18075)) {
                    ReaderCommentTable.setLikesForComment(comment, comment.numLikes, comment.isLikedByCurrentUser);
                }
                if (!ListenerUtil.mutListener.listen(18076)) {
                    ReaderLikeTable.setCurrentUserLikesComment(comment, comment.isLikedByCurrentUser, wpComUserId);
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(18077)) {
            WordPress.getRestClientUtilsV1_1().post(path, listener, errorListener);
        }
        return true;
    }

    public static void moderateComment(final ReaderComment comment, final CommentStatus newStatus) {
        if (!ListenerUtil.mutListener.listen(18078)) {
            if (comment == null) {
                return;
            }
        }
        Map<String, String> params = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(18079)) {
            params.put("content", comment.getText());
        }
        if (!ListenerUtil.mutListener.listen(18080)) {
            params.put("date", comment.getPublished());
        }
        if (!ListenerUtil.mutListener.listen(18081)) {
            params.put("status", newStatus.toString());
        }
        final String path = "sites/" + comment.blogId + "/comments/" + comment.commentId;
        RestRequest.Listener listener = jsonObject -> {
            ReaderComment newComment = ReaderComment.fromJson(jsonObject, comment.blogId);
            ReaderCommentTable.addOrUpdateComment(newComment);
            if (CommentStatus.fromString(newComment.getStatus()) != CommentStatus.APPROVED) {
                ReaderPostTable.decrementNumCommentsForPost(comment.blogId, comment.postId);
            }
            EventBus.getDefault().post(new ReaderEvents.CommentModerated(true, comment.commentId));
        };
        RestRequest.ErrorListener errorListener = volleyError -> {
            AppLog.w(T.READER, "comment moderation failed");
            AppLog.e(T.READER, volleyError);
            EventBus.getDefault().post(new ReaderEvents.CommentModerated(false, comment.commentId));
        };
        if (!ListenerUtil.mutListener.listen(18082)) {
            AppLog.i(T.READER, "moderating comment");
        }
        if (!ListenerUtil.mutListener.listen(18083)) {
            WordPress.getRestClientUtilsV1_1().post(path, params, null, listener, errorListener);
        }
    }
}
