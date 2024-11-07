package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import org.wordpress.android.models.ReaderComment;
import org.wordpress.android.models.ReaderPost;
import org.wordpress.android.models.ReaderUserIdList;
import org.wordpress.android.util.SqlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * stores likes for Reader posts and comments
 */
public class ReaderLikeTable {

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(564)) {
            db.execSQL("CREATE TABLE tbl_post_likes (" + " post_id INTEGER DEFAULT 0," + " blog_id INTEGER DEFAULT 0," + " user_id INTEGER DEFAULT 0," + " PRIMARY KEY (blog_id, post_id, user_id))");
        }
        if (!ListenerUtil.mutListener.listen(565)) {
            db.execSQL("CREATE TABLE tbl_comment_likes (" + " comment_id INTEGER DEFAULT 0," + " blog_id INTEGER DEFAULT 0," + " user_id INTEGER DEFAULT 0," + " PRIMARY KEY (blog_id, comment_id, user_id))");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(566)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_post_likes");
        }
        if (!ListenerUtil.mutListener.listen(567)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_comment_likes");
        }
    }

    protected static void reset(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(568)) {
            dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(569)) {
            createTables(db);
        }
    }

    /*
     * purge likes attached to posts/comments that no longer exist
     */
    protected static int purge(SQLiteDatabase db) {
        int numDeleted = db.delete("tbl_post_likes", "post_id NOT IN (SELECT DISTINCT post_id FROM tbl_posts)", null);
        if (!ListenerUtil.mutListener.listen(570)) {
            numDeleted += db.delete("tbl_comment_likes", "comment_id NOT IN (SELECT DISTINCT comment_id FROM tbl_comments)", null);
        }
        return numDeleted;
    }

    /*
     * returns userIds of users who like the passed post
     */
    public static ReaderUserIdList getLikesForPost(ReaderPost post) {
        ReaderUserIdList userIds = new ReaderUserIdList();
        if (post == null) {
            return userIds;
        }
        String[] args = { Long.toString(post.blogId), Long.toString(post.postId) };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT user_id FROM tbl_post_likes WHERE blog_id=? AND post_id=?", args);
        try {
            if (!ListenerUtil.mutListener.listen(574)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(573)) {
                        {
                            long _loopCounter17 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter17", ++_loopCounter17);
                                if (!ListenerUtil.mutListener.listen(572)) {
                                    userIds.add(c.getLong(0));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return userIds;
        } finally {
            if (!ListenerUtil.mutListener.listen(571)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static int getNumLikesForPost(ReaderPost post) {
        if (!ListenerUtil.mutListener.listen(575)) {
            if (post == null) {
                return 0;
            }
        }
        String[] args = { Long.toString(post.blogId), Long.toString(post.postId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT count(*) FROM tbl_post_likes WHERE blog_id=? AND post_id=?", args);
    }

    public static void setCurrentUserLikesPost(ReaderPost post, boolean isLiked, long wpComUserId) {
        if (!ListenerUtil.mutListener.listen(576)) {
            if (post == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(582)) {
            if (isLiked) {
                ContentValues values = new ContentValues();
                if (!ListenerUtil.mutListener.listen(578)) {
                    values.put("blog_id", post.blogId);
                }
                if (!ListenerUtil.mutListener.listen(579)) {
                    values.put("post_id", post.postId);
                }
                if (!ListenerUtil.mutListener.listen(580)) {
                    values.put("user_id", wpComUserId);
                }
                if (!ListenerUtil.mutListener.listen(581)) {
                    ReaderDatabase.getWritableDb().insert("tbl_post_likes", null, values);
                }
            } else {
                String[] args = { Long.toString(post.blogId), Long.toString(post.postId), Long.toString(wpComUserId) };
                if (!ListenerUtil.mutListener.listen(577)) {
                    ReaderDatabase.getWritableDb().delete("tbl_post_likes", "blog_id=? AND post_id=? AND user_id=?", args);
                }
            }
        }
    }

    public static void setLikesForPost(ReaderPost post, ReaderUserIdList userIds) {
        if (!ListenerUtil.mutListener.listen(583)) {
            if (post == null) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(584)) {
            db.beginTransaction();
        }
        SQLiteStatement stmt = db.compileStatement("INSERT INTO tbl_post_likes (blog_id, post_id, user_id) VALUES (?1,?2,?3)");
        try {
            // first delete all likes for this post
            String[] args = { Long.toString(post.blogId), Long.toString(post.postId) };
            if (!ListenerUtil.mutListener.listen(587)) {
                db.delete("tbl_post_likes", "blog_id=? AND post_id=?", args);
            }
            if (!ListenerUtil.mutListener.listen(593)) {
                // now insert the passed likes
                if (userIds != null) {
                    if (!ListenerUtil.mutListener.listen(588)) {
                        stmt.bindLong(1, post.blogId);
                    }
                    if (!ListenerUtil.mutListener.listen(589)) {
                        stmt.bindLong(2, post.postId);
                    }
                    if (!ListenerUtil.mutListener.listen(592)) {
                        {
                            long _loopCounter18 = 0;
                            for (Long userId : userIds) {
                                ListenerUtil.loopListener.listen("_loopCounter18", ++_loopCounter18);
                                if (!ListenerUtil.mutListener.listen(590)) {
                                    stmt.bindLong(3, userId);
                                }
                                if (!ListenerUtil.mutListener.listen(591)) {
                                    stmt.execute();
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(594)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(585)) {
                db.endTransaction();
            }
            if (!ListenerUtil.mutListener.listen(586)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    public static ReaderUserIdList getLikesForComment(ReaderComment comment) {
        ReaderUserIdList userIds = new ReaderUserIdList();
        if (comment == null) {
            return userIds;
        }
        String[] args = { Long.toString(comment.blogId), Long.toString(comment.commentId) };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT user_id FROM tbl_comment_likes WHERE blog_id=? AND comment_id=?", args);
        try {
            if (!ListenerUtil.mutListener.listen(598)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(597)) {
                        {
                            long _loopCounter19 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                                if (!ListenerUtil.mutListener.listen(596)) {
                                    userIds.add(c.getLong(0));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return userIds;
        } finally {
            if (!ListenerUtil.mutListener.listen(595)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static int getNumLikesForComment(ReaderComment comment) {
        if (!ListenerUtil.mutListener.listen(599)) {
            if (comment == null) {
                return 0;
            }
        }
        String[] args = { Long.toString(comment.blogId), Long.toString(comment.commentId) };
        return SqlUtils.intForQuery(ReaderDatabase.getReadableDb(), "SELECT count(*) FROM tbl_comment_likes WHERE blog_id=? AND comment_id=?", args);
    }

    public static void setCurrentUserLikesComment(ReaderComment comment, boolean isLiked, long wpComUserId) {
        if (!ListenerUtil.mutListener.listen(600)) {
            if (comment == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(606)) {
            if (isLiked) {
                ContentValues values = new ContentValues();
                if (!ListenerUtil.mutListener.listen(602)) {
                    values.put("blog_id", comment.blogId);
                }
                if (!ListenerUtil.mutListener.listen(603)) {
                    values.put("comment_id", comment.commentId);
                }
                if (!ListenerUtil.mutListener.listen(604)) {
                    values.put("user_id", wpComUserId);
                }
                if (!ListenerUtil.mutListener.listen(605)) {
                    ReaderDatabase.getWritableDb().insert("tbl_comment_likes", null, values);
                }
            } else {
                String[] args = { Long.toString(comment.blogId), Long.toString(comment.commentId), Long.toString(wpComUserId) };
                if (!ListenerUtil.mutListener.listen(601)) {
                    ReaderDatabase.getWritableDb().delete("tbl_comment_likes", "blog_id=? AND comment_id=? AND user_id=?", args);
                }
            }
        }
    }

    public static void setLikesForComment(ReaderComment comment, ReaderUserIdList userIds) {
        if (!ListenerUtil.mutListener.listen(607)) {
            if (comment == null) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(608)) {
            db.beginTransaction();
        }
        SQLiteStatement stmt = db.compileStatement("INSERT INTO tbl_comment_likes (blog_id, comment_id, user_id) VALUES (?1,?2,?3)");
        try {
            String[] args = { Long.toString(comment.blogId), Long.toString(comment.commentId) };
            if (!ListenerUtil.mutListener.listen(611)) {
                db.delete("tbl_comment_likes", "blog_id=? AND comment_id=?", args);
            }
            if (!ListenerUtil.mutListener.listen(617)) {
                if (userIds != null) {
                    if (!ListenerUtil.mutListener.listen(612)) {
                        stmt.bindLong(1, comment.blogId);
                    }
                    if (!ListenerUtil.mutListener.listen(613)) {
                        stmt.bindLong(2, comment.commentId);
                    }
                    if (!ListenerUtil.mutListener.listen(616)) {
                        {
                            long _loopCounter20 = 0;
                            for (Long userId : userIds) {
                                ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                                if (!ListenerUtil.mutListener.listen(614)) {
                                    stmt.bindLong(3, userId);
                                }
                                if (!ListenerUtil.mutListener.listen(615)) {
                                    stmt.execute();
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(618)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(609)) {
                db.endTransaction();
            }
            if (!ListenerUtil.mutListener.listen(610)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }
}
