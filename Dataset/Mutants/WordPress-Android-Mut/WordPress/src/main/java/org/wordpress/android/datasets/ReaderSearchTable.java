package org.wordpress.android.datasets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.SqlUtils;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * search suggestion table - populated by user's reader search history
 */
public class ReaderSearchTable {

    public static final String COL_ID = "_id";

    public static final String COL_QUERY = "query_string";

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1027)) {
            db.execSQL("CREATE TABLE tbl_search_suggestions (" + " _id INTEGER PRIMARY KEY AUTOINCREMENT," + " query_string TEXT NOT NULL COLLATE NOCASE," + " date_used TEXT)");
        }
        if (!ListenerUtil.mutListener.listen(1028)) {
            db.execSQL("CREATE UNIQUE INDEX idx_search_suggestions_query ON tbl_search_suggestions(query_string)");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1029)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_search_suggestions");
        }
    }

    /*
     * adds the passed query string, updating the usage date
     */
    public static void addOrUpdateQueryString(@NonNull String query) {
        String date = DateTimeUtils.iso8601FromDate(new Date());
        SQLiteStatement stmt = ReaderDatabase.getWritableDb().compileStatement("INSERT OR REPLACE INTO tbl_search_suggestions (query_string, date_used) VALUES (?1,?2)");
        try {
            if (!ListenerUtil.mutListener.listen(1031)) {
                stmt.bindString(1, query);
            }
            if (!ListenerUtil.mutListener.listen(1032)) {
                stmt.bindString(2, date);
            }
            if (!ListenerUtil.mutListener.listen(1033)) {
                stmt.execute();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1030)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    public static void deleteQueryString(@NonNull String query) {
        String[] args = new String[] { query };
        if (!ListenerUtil.mutListener.listen(1034)) {
            ReaderDatabase.getWritableDb().delete("tbl_search_suggestions", "query_string=?", args);
        }
    }

    public static void deleteAllQueries() {
        if (!ListenerUtil.mutListener.listen(1035)) {
            SqlUtils.deleteAllRowsInTable(ReaderDatabase.getWritableDb(), "tbl_search_suggestions");
        }
    }

    /**
     * Returns a cursor containing query strings previously typed by the user
     *
     * @param filter - filters the list using LIKE syntax (pass null for no filter)
     * @param max - limit the list to this many items (pass zero for no limit)
     */
    public static Cursor getQueryStringCursor(String filter, int max) {
        String sql;
        String[] args;
        if (TextUtils.isEmpty(filter)) {
            sql = "SELECT * FROM tbl_search_suggestions";
            args = null;
        } else {
            sql = "SELECT * FROM tbl_search_suggestions WHERE query_string LIKE ?";
            args = new String[] { filter + "%" };
        }
        if (!ListenerUtil.mutListener.listen(1036)) {
            sql += " ORDER BY date_used DESC";
        }
        if (!ListenerUtil.mutListener.listen(1043)) {
            if ((ListenerUtil.mutListener.listen(1041) ? (max >= 0) : (ListenerUtil.mutListener.listen(1040) ? (max <= 0) : (ListenerUtil.mutListener.listen(1039) ? (max < 0) : (ListenerUtil.mutListener.listen(1038) ? (max != 0) : (ListenerUtil.mutListener.listen(1037) ? (max == 0) : (max > 0))))))) {
                if (!ListenerUtil.mutListener.listen(1042)) {
                    sql += " LIMIT " + max;
                }
            }
        }
        return ReaderDatabase.getReadableDb().rawQuery(sql, args);
    }
}
