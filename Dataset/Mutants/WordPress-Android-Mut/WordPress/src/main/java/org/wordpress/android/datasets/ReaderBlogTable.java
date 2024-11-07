package org.wordpress.android.datasets;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import org.wordpress.android.models.ReaderBlog;
import org.wordpress.android.models.ReaderBlogList;
import org.wordpress.android.models.ReaderUrlList;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.SqlUtils;
import org.wordpress.android.util.UrlUtils;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * tbl_blog_info contains information about blogs viewed in the reader, and blogs the
 * user is following. Note that this table is populated from three endpoints:
 * <p>
 * 1. sites/{$siteId}
 * 2. read/following/mine?meta=site,feed
 * 3. read/feed/{$feedId}
 * <p>
 * The first endpoint is called when the user views blog preview, the second is called
 * to get the full list of blogs the user is following, the third is called when user views
 * by feed
 */
public class ReaderBlogTable {

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(246)) {
            db.execSQL("CREATE TABLE tbl_blog_info (" + // will be same as feedId for feeds
            " blog_id INTEGER DEFAULT 0," + // will be 0 for blogs
            " feed_id INTEGER DEFAULT 0," + " blog_url TEXT NOT NULL COLLATE NOCASE," + " image_url TEXT," + " feed_url TEXT," + " name TEXT," + " description TEXT," + " is_private INTEGER DEFAULT 0," + " is_jetpack INTEGER DEFAULT 0," + " is_following INTEGER DEFAULT 0," + " num_followers INTEGER DEFAULT 0," + " is_notifications_enabled INTEGER DEFAULT 0," + " date_updated TEXT," + " organization_id INTEGER DEFAULT 0," + " unseen_count INTEGER DEFAULT 0," + " PRIMARY KEY (blog_id)" + ")");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(247)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_blog_info");
        }
    }

    public static ReaderBlog getBlogInfo(long blogId) {
        if ((ListenerUtil.mutListener.listen(252) ? (blogId >= 0) : (ListenerUtil.mutListener.listen(251) ? (blogId <= 0) : (ListenerUtil.mutListener.listen(250) ? (blogId > 0) : (ListenerUtil.mutListener.listen(249) ? (blogId < 0) : (ListenerUtil.mutListener.listen(248) ? (blogId != 0) : (blogId == 0))))))) {
            return null;
        }
        String[] args = { Long.toString(blogId) };
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_blog_info WHERE blog_id=?", args);
        try {
            if (!cursor.moveToFirst()) {
                return null;
            }
            return getBlogInfoFromCursor(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(253)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    public static ReaderBlog getFeedInfo(long feedId) {
        if ((ListenerUtil.mutListener.listen(258) ? (feedId >= 0) : (ListenerUtil.mutListener.listen(257) ? (feedId <= 0) : (ListenerUtil.mutListener.listen(256) ? (feedId > 0) : (ListenerUtil.mutListener.listen(255) ? (feedId < 0) : (ListenerUtil.mutListener.listen(254) ? (feedId != 0) : (feedId == 0))))))) {
            return null;
        }
        String[] args = { Long.toString(feedId) };
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_blog_info WHERE feed_id=?", args);
        try {
            if (!cursor.moveToFirst()) {
                return null;
            }
            return getBlogInfoFromCursor(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(259)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    public static long getFeedIdFromUrl(String url) {
        if (!ListenerUtil.mutListener.listen(260)) {
            if (TextUtils.isEmpty(url)) {
                return 0;
            }
        }
        String[] args = { UrlUtils.normalizeUrl(url) };
        return SqlUtils.longForQuery(ReaderDatabase.getReadableDb(), "SELECT feed_id FROM tbl_blog_info WHERE feed_url=?", args);
    }

    private static ReaderBlog getBlogInfoFromCursor(Cursor c) {
        if (!ListenerUtil.mutListener.listen(261)) {
            if (c == null) {
                return null;
            }
        }
        ReaderBlog blogInfo = new ReaderBlog();
        if (!ListenerUtil.mutListener.listen(262)) {
            blogInfo.blogId = c.getLong(c.getColumnIndexOrThrow("blog_id"));
        }
        if (!ListenerUtil.mutListener.listen(263)) {
            blogInfo.feedId = c.getLong(c.getColumnIndexOrThrow("feed_id"));
        }
        if (!ListenerUtil.mutListener.listen(264)) {
            blogInfo.setUrl(c.getString(c.getColumnIndexOrThrow("blog_url")));
        }
        if (!ListenerUtil.mutListener.listen(265)) {
            blogInfo.setImageUrl(c.getString(c.getColumnIndexOrThrow("image_url")));
        }
        if (!ListenerUtil.mutListener.listen(266)) {
            blogInfo.setFeedUrl(c.getString(c.getColumnIndexOrThrow("feed_url")));
        }
        if (!ListenerUtil.mutListener.listen(267)) {
            blogInfo.setName(c.getString(c.getColumnIndexOrThrow("name")));
        }
        if (!ListenerUtil.mutListener.listen(268)) {
            blogInfo.setDescription(c.getString(c.getColumnIndexOrThrow("description")));
        }
        if (!ListenerUtil.mutListener.listen(269)) {
            blogInfo.isPrivate = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_private")));
        }
        if (!ListenerUtil.mutListener.listen(270)) {
            blogInfo.isJetpack = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_jetpack")));
        }
        if (!ListenerUtil.mutListener.listen(271)) {
            blogInfo.isFollowing = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_following")));
        }
        if (!ListenerUtil.mutListener.listen(272)) {
            blogInfo.isNotificationsEnabled = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_notifications_enabled")));
        }
        if (!ListenerUtil.mutListener.listen(273)) {
            blogInfo.numSubscribers = c.getInt(c.getColumnIndexOrThrow("num_followers"));
        }
        if (!ListenerUtil.mutListener.listen(274)) {
            blogInfo.organizationId = c.getInt(c.getColumnIndexOrThrow("organization_id"));
        }
        if (!ListenerUtil.mutListener.listen(275)) {
            blogInfo.numUnseenPosts = c.getInt(c.getColumnIndexOrThrow("unseen_count"));
        }
        return blogInfo;
    }

    public static void addOrUpdateBlog(ReaderBlog blogInfo) {
        if (!ListenerUtil.mutListener.listen(276)) {
            if (blogInfo == null) {
                return;
            }
        }
        String sql = "INSERT OR REPLACE INTO tbl_blog_info" + " (blog_id, feed_id, blog_url, image_url, feed_url, name, description, is_private, is_jetpack, " + "  is_following, is_notifications_enabled, num_followers, date_updated, " + "  organization_id, unseen_count)" + " VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15)";
        SQLiteStatement stmt = ReaderDatabase.getWritableDb().compileStatement(sql);
        try {
            if (!ListenerUtil.mutListener.listen(278)) {
                stmt.bindLong(1, blogInfo.blogId);
            }
            if (!ListenerUtil.mutListener.listen(279)) {
                stmt.bindLong(2, blogInfo.feedId);
            }
            if (!ListenerUtil.mutListener.listen(280)) {
                stmt.bindString(3, blogInfo.getUrl());
            }
            if (!ListenerUtil.mutListener.listen(281)) {
                stmt.bindString(4, blogInfo.getImageUrl());
            }
            if (!ListenerUtil.mutListener.listen(282)) {
                stmt.bindString(5, blogInfo.getFeedUrl());
            }
            if (!ListenerUtil.mutListener.listen(283)) {
                stmt.bindString(6, blogInfo.getName());
            }
            if (!ListenerUtil.mutListener.listen(284)) {
                stmt.bindString(7, blogInfo.getDescription());
            }
            if (!ListenerUtil.mutListener.listen(285)) {
                stmt.bindLong(8, SqlUtils.boolToSql(blogInfo.isPrivate));
            }
            if (!ListenerUtil.mutListener.listen(286)) {
                stmt.bindLong(9, SqlUtils.boolToSql(blogInfo.isJetpack));
            }
            if (!ListenerUtil.mutListener.listen(287)) {
                stmt.bindLong(10, SqlUtils.boolToSql(blogInfo.isFollowing));
            }
            if (!ListenerUtil.mutListener.listen(288)) {
                stmt.bindLong(11, SqlUtils.boolToSql(blogInfo.isNotificationsEnabled));
            }
            if (!ListenerUtil.mutListener.listen(289)) {
                stmt.bindLong(12, blogInfo.numSubscribers);
            }
            if (!ListenerUtil.mutListener.listen(290)) {
                stmt.bindString(13, DateTimeUtils.iso8601FromDate(new Date()));
            }
            if (!ListenerUtil.mutListener.listen(291)) {
                stmt.bindLong(14, blogInfo.organizationId);
            }
            if (!ListenerUtil.mutListener.listen(292)) {
                stmt.bindLong(15, blogInfo.numUnseenPosts);
            }
            if (!ListenerUtil.mutListener.listen(293)) {
                stmt.execute();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(277)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    /*
     * returns blogInfo for all followed blogs
     */
    public static ReaderBlogList getFollowedBlogs() {
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_blog_info WHERE is_following!=0 ORDER BY name COLLATE NOCASE, blog_url", null);
        try {
            ReaderBlogList blogs = new ReaderBlogList();
            if (!ListenerUtil.mutListener.listen(297)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(296)) {
                        {
                            long _loopCounter9 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter9", ++_loopCounter9);
                                ReaderBlog blogInfo = getBlogInfoFromCursor(c);
                                if (!ListenerUtil.mutListener.listen(295)) {
                                    blogs.add(blogInfo);
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return blogs;
        } finally {
            if (!ListenerUtil.mutListener.listen(294)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    /*
     * set followed blogs from the read/following/mine endpoint
     */
    public static void setFollowedBlogs(ReaderBlogList followedBlogs) {
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(298)) {
            db.beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(300)) {
                // first set all existing blogs to not followed
                db.execSQL("UPDATE tbl_blog_info SET is_following=0");
            }
            if (!ListenerUtil.mutListener.listen(303)) {
                // then insert passed ones
                if (followedBlogs != null) {
                    if (!ListenerUtil.mutListener.listen(302)) {
                        {
                            long _loopCounter10 = 0;
                            for (ReaderBlog blog : followedBlogs) {
                                ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                                if (!ListenerUtil.mutListener.listen(301)) {
                                    addOrUpdateBlog(blog);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(304)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(299)) {
                db.endTransaction();
            }
        }
    }

    /*
     * return list of URLs of followed blogs
     */
    public static ReaderUrlList getFollowedBlogUrls() {
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT DISTINCT blog_url FROM tbl_blog_info WHERE is_following!=0", null);
        try {
            ReaderUrlList urls = new ReaderUrlList();
            if (!ListenerUtil.mutListener.listen(308)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(307)) {
                        {
                            long _loopCounter11 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter11", ++_loopCounter11);
                                if (!ListenerUtil.mutListener.listen(306)) {
                                    urls.add(c.getString(0));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return urls;
        } finally {
            if (!ListenerUtil.mutListener.listen(305)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    /*
     * sets the follow state for passed blog without creating a record for it if it doesn't exist
     */
    public static void setIsFollowedBlogId(long blogId, boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(309)) {
            ReaderDatabase.getWritableDb().execSQL("UPDATE tbl_blog_info SET is_following=" + SqlUtils.boolToSql(isFollowed) + " WHERE blog_id=?", new String[] { Long.toString(blogId) });
        }
    }

    public static void setIsFollowedFeedId(long feedId, boolean isFollowed) {
        if (!ListenerUtil.mutListener.listen(310)) {
            ReaderDatabase.getWritableDb().execSQL("UPDATE tbl_blog_info SET is_following=" + SqlUtils.boolToSql(isFollowed) + " WHERE feed_id=?", new String[] { Long.toString(feedId) });
        }
    }

    public static boolean hasFollowedBlogs() {
        String sql = "SELECT 1 FROM tbl_blog_info WHERE is_following!=0 LIMIT 1";
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), sql, null);
    }

    public static boolean isFollowedBlogUrl(String blogUrl) {
        if (!ListenerUtil.mutListener.listen(311)) {
            if (TextUtils.isEmpty(blogUrl)) {
                return false;
            }
        }
        String sql = "SELECT 1 FROM tbl_blog_info WHERE is_following!=0 AND blog_url=?";
        String[] args = { UrlUtils.normalizeUrl(blogUrl) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), sql, args);
    }

    public static boolean isFollowedBlog(long blogId) {
        String sql = "SELECT 1 FROM tbl_blog_info WHERE is_following!=0 AND blog_id=?";
        String[] args = { Long.toString(blogId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), sql, args);
    }

    public static boolean isFollowedFeedUrl(String feedUrl) {
        if (!ListenerUtil.mutListener.listen(312)) {
            if (TextUtils.isEmpty(feedUrl)) {
                return false;
            }
        }
        String sql = "SELECT 1 FROM tbl_blog_info WHERE is_following!=0 AND feed_url=?";
        String[] args = { UrlUtils.normalizeUrl(feedUrl) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), sql, args);
    }

    public static boolean isFollowedFeed(long feedId) {
        String sql = "SELECT 1 FROM tbl_blog_info WHERE is_following!=0 AND feed_id=?";
        String[] args = { Long.toString(feedId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), sql, args);
    }

    public static boolean isNotificationsEnabled(long blogId) {
        String sql = "SELECT 1 FROM tbl_blog_info WHERE is_notifications_enabled!=0 AND blog_id=?";
        String[] args = { Long.toString(blogId) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), sql, args);
    }

    public static void setNotificationsEnabledByBlogId(long blogId, boolean isEnabled) {
        if (!ListenerUtil.mutListener.listen(313)) {
            ReaderDatabase.getWritableDb().execSQL("UPDATE tbl_blog_info SET is_notifications_enabled=" + SqlUtils.boolToSql(isEnabled) + " WHERE blog_id=?", new String[] { Long.toString(blogId) });
        }
    }

    public static String getBlogName(long blogId) {
        String[] args = { Long.toString(blogId) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT name FROM tbl_blog_info WHERE blog_id=?", args);
    }

    public static String getBlogUrl(long blogId) {
        String[] args = { Long.toString(blogId) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT blog_url FROM tbl_blog_info WHERE blog_id=?", args);
    }

    public static String getFeedName(long feedId) {
        String[] args = { Long.toString(feedId) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT name FROM tbl_blog_info WHERE feed_id=?", args);
    }

    public static void deleteBlogsWithIds(final List<Long> blogIds) {
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM tbl_blog_info" + " WHERE blog_id IN (" + TextUtils.join(",", blogIds) + ")");
        if (!ListenerUtil.mutListener.listen(314)) {
            db.beginTransaction();
        }
        try {
            try {
                if (!ListenerUtil.mutListener.listen(318)) {
                    stmt.execute();
                }
                if (!ListenerUtil.mutListener.listen(319)) {
                    db.setTransactionSuccessful();
                }
            } catch (SQLException e) {
                if (!ListenerUtil.mutListener.listen(317)) {
                    AppLog.e(AppLog.T.READER, e);
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(315)) {
                SqlUtils.closeStatement(stmt);
            }
            if (!ListenerUtil.mutListener.listen(316)) {
                db.endTransaction();
            }
        }
    }

    public static void incrementUnseenCount(long blogId) {
        if (!ListenerUtil.mutListener.listen(320)) {
            ReaderDatabase.getWritableDb().execSQL("UPDATE tbl_blog_info SET unseen_count = unseen_count+1" + " WHERE blog_id=?", new String[] { Long.toString(blogId) });
        }
    }

    public static void decrementUnseenCount(long blogId) {
        if (!ListenerUtil.mutListener.listen(321)) {
            ReaderDatabase.getWritableDb().execSQL("UPDATE tbl_blog_info SET unseen_count = unseen_count-1" + " WHERE blog_id=?", new String[] { Long.toString(blogId) });
        }
    }

    /*
     * determine whether the passed blog info should be updated based on when it was last updated
     */
    public static boolean isTimeToUpdateBlogInfo(ReaderBlog blogInfo) {
        int minutes = minutesSinceLastUpdate(blogInfo);
        if (!ListenerUtil.mutListener.listen(327)) {
            if ((ListenerUtil.mutListener.listen(326) ? (minutes >= NEVER_UPDATED) : (ListenerUtil.mutListener.listen(325) ? (minutes <= NEVER_UPDATED) : (ListenerUtil.mutListener.listen(324) ? (minutes > NEVER_UPDATED) : (ListenerUtil.mutListener.listen(323) ? (minutes < NEVER_UPDATED) : (ListenerUtil.mutListener.listen(322) ? (minutes != NEVER_UPDATED) : (minutes == NEVER_UPDATED))))))) {
                return true;
            }
        }
        return ((ListenerUtil.mutListener.listen(332) ? (minutes <= ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(331) ? (minutes > ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(330) ? (minutes < ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(329) ? (minutes != ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(328) ? (minutes == ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (minutes >= ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES)))))));
    }

    private static String getBlogInfoLastUpdated(ReaderBlog blogInfo) {
        if (blogInfo == null) {
            return "";
        }
        if (blogInfo.blogId != 0) {
            String[] args = { Long.toString(blogInfo.blogId) };
            return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT date_updated FROM tbl_blog_info WHERE blog_id=?", args);
        } else {
            String[] args = { Long.toString(blogInfo.feedId) };
            return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT date_updated FROM tbl_blog_info WHERE feed_id=?", args);
        }
    }

    private static final int NEVER_UPDATED = -1;

    private static int minutesSinceLastUpdate(ReaderBlog blogInfo) {
        if (!ListenerUtil.mutListener.listen(333)) {
            if (blogInfo == null) {
                return 0;
            }
        }
        String updated = getBlogInfoLastUpdated(blogInfo);
        if (!ListenerUtil.mutListener.listen(334)) {
            if (TextUtils.isEmpty(updated)) {
                return NEVER_UPDATED;
            }
        }
        Date dtUpdated = DateTimeUtils.dateFromIso8601(updated);
        if (!ListenerUtil.mutListener.listen(335)) {
            if (dtUpdated == null) {
                return 0;
            }
        }
        Date dtNow = new Date();
        return DateTimeUtils.minutesBetween(dtUpdated, dtNow);
    }
}
