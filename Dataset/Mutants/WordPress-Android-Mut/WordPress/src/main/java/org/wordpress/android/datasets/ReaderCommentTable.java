package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.Nullable;
import org.wordpress.android.fluxc.model.CommentStatus;
import org.wordpress.android.models.ReaderComment;
import org.wordpress.android.models.ReaderCommentList;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.util.SqlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * stores comments on reader posts
 */
public class ReaderCommentTable {

    private static final String COLUMN_NAMES = " blog_id," + " post_id," + " comment_id," + " parent_id," + " author_name," + " author_avatar," + " author_url," + " author_id," + " author_blog_id," + " published," + " timestamp," + " status," + " text," + " num_likes," + " is_liked," + " page_number," + " short_url," + " author_email";

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(336)) {
            db.execSQL("CREATE TABLE tbl_comments (" + " blog_id INTEGER DEFAULT 0," + " post_id INTEGER DEFAULT 0," + " comment_id INTEGER DEFAULT 0," + " parent_id INTEGER DEFAULT 0," + " author_name TEXT," + " author_avatar TEXT," + " author_url TEXT," + " author_id INTEGER DEFAULT 0," + " author_blog_id INTEGER DEFAULT 0," + " published TEXT," + " timestamp INTEGER DEFAULT 0," + " status TEXT," + " text TEXT," + " num_likes INTEGER DEFAULT 0," + " is_liked INTEGER DEFAULT 0," + " page_number INTEGER DEFAULT 0," + " short_url TEXT," + " author_email TEXT," + " PRIMARY KEY (blog_id, post_id, comment_id))");
        }
        if (!ListenerUtil.mutListener.listen(337)) {
            db.execSQL("CREATE INDEX idx_page_number ON tbl_comments(page_number)");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(338)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_comments");
        }
    }

    protected static void reset(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(339)) {
            dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(340)) {
            createTables(db);
        }
    }

    protected static int purge(SQLiteDatabase db) {
        // purge comments attached to posts that no longer exist
        int numDeleted = db.delete("tbl_comments", "post_id NOT IN (SELECT DISTINCT post_id FROM tbl_posts)", null);
        if (!ListenerUtil.mutListener.listen(341)) {
            // purge all but the first page of comments
            numDeleted += db.delete("tbl_comments", "page_number != 1", null);
        }
        return numDeleted;
    }

    public static boolean isEmpty() {
        return ((ListenerUtil.mutListener.listen(346) ? (getNumComments() >= 0) : (ListenerUtil.mutListener.listen(345) ? (getNumComments() <= 0) : (ListenerUtil.mutListener.listen(344) ? (getNumComments() > 0) : (ListenerUtil.mutListener.listen(343) ? (getNumComments() < 0) : (ListenerUtil.mutListener.listen(342) ? (getNumComments() != 0) : (getNumComments() == 0)))))));
    }

    private static int getNumComments() {
        long count = SqlUtils.getRowCount(ReaderDatabase.getReadableDb(), "tbl_comments");
        return (int) count;
    }

    /*
     * returns the highest page_number for comments on the passed post
     */
    public static int getLastPageNumberForPost(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT MAX(page_number) FROM tbl_comments WHERE blog_id=? AND post_id=?", args);
    }

    /*
     * returns the page number for a specific comment
     */
    public static int getPageNumberForComment(long blogId, long postId, long commentId) {
        String[] args = { Long.toString(blogId), Long.toString(postId), Long.toString(commentId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT page_number FROM tbl_comments " + " WHERE blog_id=? AND post_id=? AND comment_id=?", args);
    }

    /*
     * removes all comments for the passed post
     */
    public static void purgeCommentsForPost(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        if (!ListenerUtil.mutListener.listen(347)) {
            ReaderDatabase.getWritableDb().delete("tbl_comments", "blog_id=? AND post_id=?", args);
        }
    }

    /*
     * returns the #comments stored locally for this post, which may differ from ReaderPostTable.getNumCommentsOnPost
     * (which is the #comments the server says exist for this post)
     */
    public static int getNumCommentsForPost(ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(348)) {
            if (post == null) {
                return 0;
            }
        }
        return getNumCommentsForPost(post.blogId, post.postId);
    }

    private static int getNumCommentsForPost(long blogId, long postId) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT count(*) FROM tbl_comments WHERE blog_id=? AND post_id=?", args);
    }

    public static ReaderCommentList getCommentsForPost(ReaderPost post) {
        if (post == null) {
            return new ReaderCommentList();
        }
        String[] args = { Long.toString(post.blogId), Long.toString(post.postId), CommentStatus.APPROVED.toString() };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_comments WHERE blog_id=? AND post_id=? AND status =? ORDER BY timestamp", args);
        try {
            ReaderCommentList comments = new ReaderCommentList();
            if (!ListenerUtil.mutListener.listen(352)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(351)) {
                        {
                            long _loopCounter12 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                                if (!ListenerUtil.mutListener.listen(350)) {
                                    comments.add(getCommentFromCursor(c));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return comments;
        } finally {
            if (!ListenerUtil.mutListener.listen(349)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    @Nullable
    public static ReaderCommentList getCommentsForPostSnippet(ReaderPost post, int limit) {
        if (post == null) {
            return new ReaderCommentList();
        }
        String[] args = { Long.toString(post.blogId), Long.toString(post.postId), Integer.toString(limit) };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_comments WHERE blog_id=? AND post_id=? AND parent_id=0 ORDER BY timestamp LIMIT ?", args);
        try {
            ReaderCommentList comments = new ReaderCommentList();
            if (!ListenerUtil.mutListener.listen(356)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(355)) {
                        {
                            long _loopCounter13 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter13", ++_loopCounter13);
                                if (!ListenerUtil.mutListener.listen(354)) {
                                    comments.add(getCommentFromCursor(c));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return comments;
        } finally {
            if (!ListenerUtil.mutListener.listen(353)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static void addOrUpdateComment(ReaderComment comment) {
        if (!ListenerUtil.mutListener.listen(357)) {
            if (comment == null) {
                return;
            }
        }
        ReaderCommentList comments = new ReaderCommentList();
        if (!ListenerUtil.mutListener.listen(358)) {
            comments.add(comment);
        }
        if (!ListenerUtil.mutListener.listen(359)) {
            addOrUpdateComments(comments);
        }
    }

    public static void addOrUpdateComments(ReaderCommentList comments) {
        if (!ListenerUtil.mutListener.listen(361)) {
            if ((ListenerUtil.mutListener.listen(360) ? (comments == null && comments.size() == 0) : (comments == null || comments.size() == 0))) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(362)) {
            db.beginTransaction();
        }
        SQLiteStatement stmt = db.compileStatement("INSERT OR REPLACE INTO tbl_comments (" + COLUMN_NAMES + ") " + "VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16," + "?17,?18)");
        try {
            if (!ListenerUtil.mutListener.listen(384)) {
                {
                    long _loopCounter14 = 0;
                    for (ReaderComment comment : comments) {
                        ListenerUtil.loopListener.listen("_loopCounter14", ++_loopCounter14);
                        if (!ListenerUtil.mutListener.listen(365)) {
                            stmt.bindLong(1, comment.blogId);
                        }
                        if (!ListenerUtil.mutListener.listen(366)) {
                            stmt.bindLong(2, comment.postId);
                        }
                        if (!ListenerUtil.mutListener.listen(367)) {
                            stmt.bindLong(3, comment.commentId);
                        }
                        if (!ListenerUtil.mutListener.listen(368)) {
                            stmt.bindLong(4, comment.parentId);
                        }
                        if (!ListenerUtil.mutListener.listen(369)) {
                            stmt.bindString(5, comment.getAuthorName());
                        }
                        if (!ListenerUtil.mutListener.listen(370)) {
                            stmt.bindString(6, comment.getAuthorAvatar());
                        }
                        if (!ListenerUtil.mutListener.listen(371)) {
                            stmt.bindString(7, comment.getAuthorUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(372)) {
                            stmt.bindLong(8, comment.authorId);
                        }
                        if (!ListenerUtil.mutListener.listen(373)) {
                            stmt.bindLong(9, comment.authorBlogId);
                        }
                        if (!ListenerUtil.mutListener.listen(374)) {
                            stmt.bindString(10, comment.getPublished());
                        }
                        if (!ListenerUtil.mutListener.listen(375)) {
                            stmt.bindLong(11, comment.timestamp);
                        }
                        if (!ListenerUtil.mutListener.listen(376)) {
                            stmt.bindString(12, comment.getStatus());
                        }
                        if (!ListenerUtil.mutListener.listen(377)) {
                            stmt.bindString(13, comment.getText());
                        }
                        if (!ListenerUtil.mutListener.listen(378)) {
                            stmt.bindLong(14, comment.numLikes);
                        }
                        if (!ListenerUtil.mutListener.listen(379)) {
                            stmt.bindLong(15, SqlUtils.boolToSql(comment.isLikedByCurrentUser));
                        }
                        if (!ListenerUtil.mutListener.listen(380)) {
                            stmt.bindLong(16, comment.pageNumber);
                        }
                        if (!ListenerUtil.mutListener.listen(381)) {
                            stmt.bindString(17, comment.getShortUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(382)) {
                            stmt.bindString(18, comment.getAuthorEmail());
                        }
                        if (!ListenerUtil.mutListener.listen(383)) {
                            stmt.execute();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(385)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(363)) {
                db.endTransaction();
            }
            if (!ListenerUtil.mutListener.listen(364)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    public static ReaderComment getComment(long blogId, long postId, long commentId) {
        String[] args = new String[] { Long.toString(blogId), Long.toString(postId), Long.toString(commentId) };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_comments WHERE blog_id=? AND post_id=? AND comment_id=? LIMIT 1", args);
        try {
            if (!c.moveToFirst()) {
                return null;
            }
            return getCommentFromCursor(c);
        } finally {
            if (!ListenerUtil.mutListener.listen(386)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static void deleteComment(ReaderPost post, long commentId) {
        if (!ListenerUtil.mutListener.listen(387)) {
            if (post == null) {
                return;
            }
        }
        String[] args = { Long.toString(post.blogId), Long.toString(post.postId), Long.toString(commentId) };
        if (!ListenerUtil.mutListener.listen(388)) {
            ReaderDatabase.getWritableDb().delete("tbl_comments", "blog_id=? AND post_id=? AND comment_id=?", args);
        }
    }

    /*
     * returns true if any of the passed comments don't already exist
     * IMPORTANT: assumes passed comments are all for the same post
     */
    public static boolean hasNewComments(ReaderCommentList comments) {
        if (!ListenerUtil.mutListener.listen(390)) {
            if ((ListenerUtil.mutListener.listen(389) ? (comments == null && comments.size() == 0) : (comments == null || comments.size() == 0))) {
                return false;
            }
        }
        StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM tbl_comments WHERE blog_id=? AND post_id=? AND comment_id IN (");
        boolean isFirst = true;
        if (!ListenerUtil.mutListener.listen(395)) {
            {
                long _loopCounter15 = 0;
                for (ReaderComment comment : comments) {
                    ListenerUtil.loopListener.listen("_loopCounter15", ++_loopCounter15);
                    if (!ListenerUtil.mutListener.listen(393)) {
                        if (isFirst) {
                            if (!ListenerUtil.mutListener.listen(392)) {
                                isFirst = false;
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(391)) {
                                sb.append(",");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(394)) {
                        sb.append(comment.commentId);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(396)) {
            sb.append(")");
        }
        String[] args = { Long.toString(comments.get(0).blogId), Long.toString(comments.get(0).postId) };
        int numExisting = SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), sb.toString(), args);
        return numExisting != comments.size();
    }

    /*
     * returns the #likes known to exist for this comment
     */
    public static int getNumLikesForComment(long blogId, long postId, long commentId) {
        String[] args = { Long.toString(blogId), Long.toString(postId), Long.toString(commentId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT num_likes FROM tbl_comments WHERE blog_id=? AND post_id=? AND comment_id=?", args);
    }

    /*
     * updates both the like count for a comment and whether it's liked by the current user
     */
    public static void setLikesForComment(ReaderComment comment, int numLikes, boolean isLikedByCurrentUser) {
        if (!ListenerUtil.mutListener.listen(397)) {
            if (comment == null) {
                return;
            }
        }
        String[] args = { Long.toString(comment.blogId), Long.toString(comment.postId), Long.toString(comment.commentId) };
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(398)) {
            values.put("num_likes", numLikes);
        }
        if (!ListenerUtil.mutListener.listen(399)) {
            values.put("is_liked", SqlUtils.boolToSql(isLikedByCurrentUser));
        }
        if (!ListenerUtil.mutListener.listen(400)) {
            ReaderDatabase.getWritableDb().update("tbl_comments", values, "blog_id=? AND post_id=? AND comment_id=?", args);
        }
    }

    public static boolean isCommentLikedByCurrentUser(ReaderComment comment) {
        if (!ListenerUtil.mutListener.listen(401)) {
            if (comment == null) {
                return false;
            }
        }
        return isCommentLikedByCurrentUser(comment.blogId, comment.postId, comment.commentId);
    }

    public static boolean isCommentLikedByCurrentUser(long blogId, long postId, long commentId) {
        String[] args = { Long.toString(blogId), Long.toString(postId), Long.toString(commentId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT is_liked FROM tbl_comments WHERE blog_id=? AND post_id=? and comment_id=?", args);
    }

    public static boolean commentExists(long blogId, long postId, long commentId) {
        String[] args = { Long.toString(blogId), Long.toString(postId), Long.toString(commentId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT 1 FROM tbl_comments WHERE blog_id=? AND post_id=? AND comment_id=?", args);
    }

    private static ReaderComment getCommentFromCursor(Cursor c) {
        if (!ListenerUtil.mutListener.listen(402)) {
            if (c == null) {
                throw new IllegalArgumentException("null comment cursor");
            }
        }
        ReaderComment comment = new ReaderComment();
        if (!ListenerUtil.mutListener.listen(403)) {
            comment.commentId = c.getLong(c.getColumnIndexOrThrow("comment_id"));
        }
        if (!ListenerUtil.mutListener.listen(404)) {
            comment.blogId = c.getLong(c.getColumnIndexOrThrow("blog_id"));
        }
        if (!ListenerUtil.mutListener.listen(405)) {
            comment.postId = c.getLong(c.getColumnIndexOrThrow("post_id"));
        }
        if (!ListenerUtil.mutListener.listen(406)) {
            comment.parentId = c.getLong(c.getColumnIndexOrThrow("parent_id"));
        }
        if (!ListenerUtil.mutListener.listen(407)) {
            comment.setPublished(c.getString(c.getColumnIndexOrThrow("published")));
        }
        if (!ListenerUtil.mutListener.listen(408)) {
            comment.timestamp = c.getLong(c.getColumnIndexOrThrow("timestamp"));
        }
        if (!ListenerUtil.mutListener.listen(409)) {
            comment.setAuthorAvatar(c.getString(c.getColumnIndexOrThrow("author_avatar")));
        }
        if (!ListenerUtil.mutListener.listen(410)) {
            comment.setAuthorName(c.getString(c.getColumnIndexOrThrow("author_name")));
        }
        if (!ListenerUtil.mutListener.listen(411)) {
            comment.setAuthorUrl(c.getString(c.getColumnIndexOrThrow("author_url")));
        }
        if (!ListenerUtil.mutListener.listen(412)) {
            comment.authorId = c.getLong(c.getColumnIndexOrThrow("author_id"));
        }
        if (!ListenerUtil.mutListener.listen(413)) {
            comment.authorBlogId = c.getLong(c.getColumnIndexOrThrow("author_blog_id"));
        }
        if (!ListenerUtil.mutListener.listen(414)) {
            comment.setStatus(c.getString(c.getColumnIndexOrThrow("status")));
        }
        if (!ListenerUtil.mutListener.listen(415)) {
            comment.setText(c.getString(c.getColumnIndexOrThrow("text")));
        }
        if (!ListenerUtil.mutListener.listen(416)) {
            comment.numLikes = c.getInt(c.getColumnIndexOrThrow("num_likes"));
        }
        if (!ListenerUtil.mutListener.listen(417)) {
            comment.isLikedByCurrentUser = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_liked")));
        }
        if (!ListenerUtil.mutListener.listen(418)) {
            comment.pageNumber = c.getInt(c.getColumnIndexOrThrow("page_number"));
        }
        if (!ListenerUtil.mutListener.listen(419)) {
            comment.setShortUrl(c.getString(c.getColumnIndexOrThrow("short_url")));
        }
        if (!ListenerUtil.mutListener.listen(420)) {
            comment.setAuthorEmail(c.getString(c.getColumnIndexOrThrow("author_email")));
        }
        return comment;
    }
}
