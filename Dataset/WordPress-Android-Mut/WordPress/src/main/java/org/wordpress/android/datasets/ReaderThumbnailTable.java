package org.wordpress.android.datasets;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import org.wordpress.android.util.SqlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * stores thumbnail urls for videos embedded in Reader posts
 */
public class ReaderThumbnailTable {

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1125)) {
            db.execSQL("CREATE TABLE tbl_thumbnails (" + " full_url TEXT COLLATE NOCASE PRIMARY KEY," + " thumbnail_url TEXT NOT NULL," + " post_id INTEGER DEFAULT 0)");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1126)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_thumbnails");
        }
    }

    /*
     * purge table of thumbnails attached to posts that no longer exist
     */
    protected static int purge(SQLiteDatabase db) {
        return db.delete("tbl_thumbnails", "post_id NOT IN (SELECT DISTINCT post_id FROM tbl_posts)", null);
    }

    public static void addThumbnail(long postId, String fullUrl, String thumbnailUrl) {
        if (!ListenerUtil.mutListener.listen(1128)) {
            if ((ListenerUtil.mutListener.listen(1127) ? (TextUtils.isEmpty(fullUrl) && TextUtils.isEmpty(thumbnailUrl)) : (TextUtils.isEmpty(fullUrl) || TextUtils.isEmpty(thumbnailUrl)))) {
                return;
            }
        }
        SQLiteStatement stmt = ReaderDatabase.getWritableDb().compileStatement("INSERT OR REPLACE INTO tbl_thumbnails (full_url, thumbnail_url, post_id) VALUES (?1,?2,?3)");
        try {
            if (!ListenerUtil.mutListener.listen(1130)) {
                stmt.bindString(1, fullUrl);
            }
            if (!ListenerUtil.mutListener.listen(1131)) {
                stmt.bindString(2, thumbnailUrl);
            }
            if (!ListenerUtil.mutListener.listen(1132)) {
                stmt.bindLong(3, postId);
            }
            if (!ListenerUtil.mutListener.listen(1133)) {
                stmt.execute();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1129)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    public static String getThumbnailUrl(String fullUrl) {
        if (!ListenerUtil.mutListener.listen(1134)) {
            if (TextUtils.isEmpty(fullUrl)) {
                return null;
            }
        }
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT thumbnail_url FROM tbl_thumbnails WHERE full_url=?", new String[] { fullUrl });
    }
}
