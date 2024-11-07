package org.wordpress.android.datasets;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagList;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.reader.ReaderConstants;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.SqlUtils;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * tbl_tags stores the list of tags the user subscribed to or has by default
 */
public class ReaderTagTable {

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1044)) {
            db.execSQL("CREATE TABLE tbl_tags (" + " tag_slug TEXT COLLATE NOCASE," + " tag_display_name TEXT COLLATE NOCASE," + " tag_title TEXT COLLATE NOCASE," + " tag_type INTEGER DEFAULT 0," + " endpoint TEXT," + " date_updated TEXT," + " PRIMARY KEY (tag_slug, tag_type)" + ")");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1045)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_tags");
        }
    }

    /*
     * returns true if tbl_tags is empty
     */
    public static boolean isEmpty() {
        return (SqlUtils.getRowCount(ReaderDatabase.getReadableDb(), "tbl_tags") == 0);
    }

    /*
     * replaces all tags with the passed list
     */
    public static void replaceTags(ReaderTagList tags) {
        if (!ListenerUtil.mutListener.listen(1047)) {
            if ((ListenerUtil.mutListener.listen(1046) ? (tags == null && tags.size() == 0) : (tags == null || tags.size() == 0))) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(1048)) {
            db.beginTransaction();
        }
        try {
            try {
                if (!ListenerUtil.mutListener.listen(1051)) {
                    // first delete all existing tags, then insert the passed ones
                    db.execSQL("DELETE FROM tbl_tags");
                }
                if (!ListenerUtil.mutListener.listen(1052)) {
                    addOrUpdateTags(tags);
                }
                if (!ListenerUtil.mutListener.listen(1053)) {
                    db.setTransactionSuccessful();
                }
            } catch (SQLException e) {
                if (!ListenerUtil.mutListener.listen(1050)) {
                    AppLog.e(T.READER, e);
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1049)) {
                db.endTransaction();
            }
        }
    }

    /*
     * similar to the above but only replaces followed tags
     */
    public static void replaceFollowedTags(ReaderTagList tags) {
        if (!ListenerUtil.mutListener.listen(1055)) {
            if ((ListenerUtil.mutListener.listen(1054) ? (tags == null && tags.size() == 0) : (tags == null || tags.size() == 0))) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(1056)) {
            db.beginTransaction();
        }
        try {
            try {
                // first delete all existing followed tags, then insert the passed ones
                String[] args = { Integer.toString(ReaderTagType.FOLLOWED.toInt()) };
                if (!ListenerUtil.mutListener.listen(1059)) {
                    db.execSQL("DELETE FROM tbl_tags WHERE tag_type=?", args);
                }
                if (!ListenerUtil.mutListener.listen(1060)) {
                    addOrUpdateTags(tags);
                }
                if (!ListenerUtil.mutListener.listen(1061)) {
                    db.setTransactionSuccessful();
                }
            } catch (SQLException e) {
                if (!ListenerUtil.mutListener.listen(1058)) {
                    AppLog.e(T.READER, e);
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1057)) {
                db.endTransaction();
            }
        }
    }

    public static void addOrUpdateTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1062)) {
            if (tag == null) {
                return;
            }
        }
        ReaderTagList tags = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(1063)) {
            tags.add(tag);
        }
        if (!ListenerUtil.mutListener.listen(1064)) {
            addOrUpdateTags(tags);
        }
    }

    public static void addOrUpdateTags(ReaderTagList tagList) {
        if (!ListenerUtil.mutListener.listen(1066)) {
            if ((ListenerUtil.mutListener.listen(1065) ? (tagList == null && tagList.size() == 0) : (tagList == null || tagList.size() == 0))) {
                return;
            }
        }
        SQLiteStatement stmt = null;
        try {
            if (!ListenerUtil.mutListener.listen(1068)) {
                stmt = ReaderDatabase.getWritableDb().compileStatement("INSERT OR REPLACE INTO tbl_tags (tag_slug, tag_display_name, tag_title, tag_type, endpoint) " + "VALUES (?1,?2,?3,?4,?5)");
            }
            if (!ListenerUtil.mutListener.listen(1075)) {
                {
                    long _loopCounter34 = 0;
                    for (ReaderTag tag : tagList) {
                        ListenerUtil.loopListener.listen("_loopCounter34", ++_loopCounter34);
                        if (!ListenerUtil.mutListener.listen(1069)) {
                            stmt.bindString(1, tag.getTagSlug());
                        }
                        if (!ListenerUtil.mutListener.listen(1070)) {
                            stmt.bindString(2, tag.getTagDisplayName());
                        }
                        if (!ListenerUtil.mutListener.listen(1071)) {
                            stmt.bindString(3, tag.getTagTitle());
                        }
                        if (!ListenerUtil.mutListener.listen(1072)) {
                            stmt.bindLong(4, tag.tagType.toInt());
                        }
                        if (!ListenerUtil.mutListener.listen(1073)) {
                            stmt.bindString(5, tag.getEndpoint());
                        }
                        if (!ListenerUtil.mutListener.listen(1074)) {
                            stmt.execute();
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1067)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    /*
     * returns true if the passed tag exists, regardless of type
     */
    public static boolean tagExists(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1076)) {
            if (tag == null) {
                return false;
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT 1 FROM tbl_tags WHERE tag_slug=?1 AND tag_type=?2", args);
    }

    /*
     * returns true if the passed tag exists and it has the passed type
     */
    private static boolean tagExistsOfType(String tagSlug, ReaderTagType tagType) {
        if (!ListenerUtil.mutListener.listen(1078)) {
            if ((ListenerUtil.mutListener.listen(1077) ? (TextUtils.isEmpty(tagSlug) && tagType == null) : (TextUtils.isEmpty(tagSlug) || tagType == null))) {
                return false;
            }
        }
        String[] args = { tagSlug, Integer.toString(tagType.toInt()) };
        return SqlUtils.boolForQuery(ReaderDatabase.getReadableDb(), "SELECT 1 FROM tbl_tags WHERE tag_slug=?1 AND tag_type=?2", args);
    }

    public static boolean isFollowedTagName(String tagSlug) {
        return tagExistsOfType(tagSlug, ReaderTagType.FOLLOWED);
    }

    private static ReaderTag getTagFromCursor(Cursor c) {
        if (!ListenerUtil.mutListener.listen(1079)) {
            if (c == null) {
                throw new IllegalArgumentException("null tag cursor");
            }
        }
        String tagSlug = c.getString(c.getColumnIndexOrThrow("tag_slug"));
        String tagDisplayName = c.getString(c.getColumnIndexOrThrow("tag_display_name"));
        String tagTitle = c.getString(c.getColumnIndexOrThrow("tag_title"));
        String endpoint = c.getString(c.getColumnIndexOrThrow("endpoint"));
        ReaderTagType tagType = ReaderTagType.fromInt(c.getInt(c.getColumnIndexOrThrow("tag_type")));
        return new ReaderTag(tagSlug, tagDisplayName, tagTitle, endpoint, tagType);
    }

    public static ReaderTag getTag(String tagSlug, ReaderTagType tagType) {
        if (TextUtils.isEmpty(tagSlug)) {
            return null;
        }
        String[] args = { tagSlug, Integer.toString(tagType.toInt()) };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_tags WHERE tag_slug=? AND tag_type=? LIMIT 1", args);
        try {
            if (!c.moveToFirst()) {
                return null;
            }
            return getTagFromCursor(c);
        } finally {
            if (!ListenerUtil.mutListener.listen(1080)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static ReaderTag getTagFromEndpoint(String endpoint) {
        if (TextUtils.isEmpty(endpoint)) {
            return null;
        }
        String[] args = { "%" + endpoint };
        String query = "SELECT * FROM tbl_tags WHERE endpoint LIKE ? LIMIT 1";
        Cursor cursor = ReaderDatabase.getReadableDb().rawQuery(query, args);
        try {
            return cursor.moveToFirst() ? getTagFromCursor(cursor) : null;
        } finally {
            if (!ListenerUtil.mutListener.listen(1081)) {
                SqlUtils.closeCursor(cursor);
            }
        }
    }

    public static String getEndpointForTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1082)) {
            if (tag == null) {
                return null;
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT endpoint FROM tbl_tags WHERE tag_slug=? AND tag_type=?", args);
    }

    public static ReaderTagList getDefaultTags() {
        return getTagsOfType(ReaderTagType.DEFAULT);
    }

    public static ReaderTagList getFollowedTags() {
        return getTagsOfType(ReaderTagType.FOLLOWED);
    }

    public static ReaderTagList getCustomListTags() {
        return getTagsOfType(ReaderTagType.CUSTOM_LIST);
    }

    public static ReaderTagList getBookmarkTags() {
        return getTagsOfType(ReaderTagType.BOOKMARKED);
    }

    private static ReaderTagList getTagsOfType(ReaderTagType tagType) {
        String[] args = { Integer.toString(tagType.toInt()) };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_tags WHERE tag_type=? ORDER BY tag_slug", args);
        try {
            ReaderTagList tagList = new ReaderTagList();
            if (!ListenerUtil.mutListener.listen(1086)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(1085)) {
                        {
                            long _loopCounter35 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                                if (!ListenerUtil.mutListener.listen(1084)) {
                                    tagList.add(getTagFromCursor(c));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return tagList;
        } finally {
            if (!ListenerUtil.mutListener.listen(1083)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    static ReaderTagList getAllTags() {
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_tags ORDER BY tag_slug", null);
        try {
            ReaderTagList tagList = new ReaderTagList();
            if (!ListenerUtil.mutListener.listen(1090)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(1089)) {
                        {
                            long _loopCounter36 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                                if (!ListenerUtil.mutListener.listen(1088)) {
                                    tagList.add(getTagFromCursor(c));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return tagList;
        } finally {
            if (!ListenerUtil.mutListener.listen(1087)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    @Nullable
    public static ReaderTag getFirstTag() {
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_tags ORDER BY tag_slug LIMIT 1", null);
        try {
            if (c.moveToFirst()) {
                return getTagFromCursor(c);
            }
            return null;
        } finally {
            if (!ListenerUtil.mutListener.listen(1091)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static void deleteTag(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1092)) {
            if (tag == null) {
                return;
            }
        }
        ReaderTagList tags = new ReaderTagList();
        if (!ListenerUtil.mutListener.listen(1093)) {
            tags.add(tag);
        }
        if (!ListenerUtil.mutListener.listen(1094)) {
            deleteTags(tags);
        }
    }

    public static void deleteTags(ReaderTagList tagList) {
        if (!ListenerUtil.mutListener.listen(1096)) {
            if ((ListenerUtil.mutListener.listen(1095) ? (tagList == null && tagList.size() == 0) : (tagList == null || tagList.size() == 0))) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(1097)) {
            db.beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(1100)) {
                {
                    long _loopCounter37 = 0;
                    for (ReaderTag tag : tagList) {
                        ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
                        if (!ListenerUtil.mutListener.listen(1099)) {
                            ReaderDatabase.getWritableDb().delete("tbl_tags", "tag_slug=? AND tag_type=?", args);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1101)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1098)) {
                db.endTransaction();
            }
        }
    }

    public static String getTagLastUpdated(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1102)) {
            if (tag == null) {
                return "";
            }
        }
        String[] args = { tag.getTagSlug(), Integer.toString(tag.tagType.toInt()) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT date_updated FROM tbl_tags WHERE tag_slug=? AND tag_type=?", args);
    }

    public static void setTagLastUpdated(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1103)) {
            updateTagLastUpdated(tag, new Date());
        }
    }

    public static void clearTagLastUpdated(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1104)) {
            updateTagLastUpdated(tag, new Date(0));
        }
    }

    private static void updateTagLastUpdated(ReaderTag tag, Date newDate) {
        if (!ListenerUtil.mutListener.listen(1105)) {
            if (tag == null) {
                return;
            }
        }
        String date = DateTimeUtils.iso8601FromDate(newDate);
        String sql = "UPDATE tbl_tags SET date_updated=?1 WHERE tag_slug=?2 AND tag_type=?3";
        SQLiteStatement stmt = ReaderDatabase.getWritableDb().compileStatement(sql);
        try {
            if (!ListenerUtil.mutListener.listen(1107)) {
                stmt.bindString(1, date);
            }
            if (!ListenerUtil.mutListener.listen(1108)) {
                stmt.bindString(2, tag.getTagSlug());
            }
            if (!ListenerUtil.mutListener.listen(1109)) {
                stmt.bindLong(3, tag.tagType.toInt());
            }
            if (!ListenerUtil.mutListener.listen(1110)) {
                stmt.execute();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1106)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    /*
     * determine whether the passed tag should be auto-updated based on when it was last updated
     */
    public static boolean shouldAutoUpdateTag(ReaderTag tag) {
        int minutes = minutesSinceLastUpdate(tag);
        if (!ListenerUtil.mutListener.listen(1116)) {
            if ((ListenerUtil.mutListener.listen(1115) ? (minutes >= NEVER_UPDATED) : (ListenerUtil.mutListener.listen(1114) ? (minutes <= NEVER_UPDATED) : (ListenerUtil.mutListener.listen(1113) ? (minutes > NEVER_UPDATED) : (ListenerUtil.mutListener.listen(1112) ? (minutes < NEVER_UPDATED) : (ListenerUtil.mutListener.listen(1111) ? (minutes != NEVER_UPDATED) : (minutes == NEVER_UPDATED))))))) {
                return true;
            }
        }
        return ((ListenerUtil.mutListener.listen(1121) ? (minutes <= ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(1120) ? (minutes > ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(1119) ? (minutes < ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(1118) ? (minutes != ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (ListenerUtil.mutListener.listen(1117) ? (minutes == ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES) : (minutes >= ReaderConstants.READER_AUTO_UPDATE_DELAY_MINUTES)))))));
    }

    private static final int NEVER_UPDATED = -1;

    private static int minutesSinceLastUpdate(ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(1122)) {
            if (tag == null) {
                return 0;
            }
        }
        String updated = getTagLastUpdated(tag);
        if (!ListenerUtil.mutListener.listen(1123)) {
            if (TextUtils.isEmpty(updated)) {
                return NEVER_UPDATED;
            }
        }
        Date dtUpdated = DateTimeUtils.dateFromIso8601(updated);
        if (!ListenerUtil.mutListener.listen(1124)) {
            if (dtUpdated == null) {
                return 0;
            }
        }
        Date dtNow = new Date();
        return DateTimeUtils.minutesBetween(dtUpdated, dtNow);
    }
}
