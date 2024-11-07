package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.UserSuggestion;
import org.wordpress.android.models.Tag;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SqlUtils;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UserSuggestionTable {

    private static final String SUGGESTIONS_TABLE = "suggestions";

    private static final String TAXONOMY_TABLE = "taxonomy";

    public static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1298)) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + SUGGESTIONS_TABLE + " (" + " site_id INTEGER DEFAULT 0," + " user_login TEXT," + " display_name TEXT," + " image_url TEXT," + " taxonomy TEXT," + " PRIMARY KEY (user_login)" + " );");
        }
        if (!ListenerUtil.mutListener.listen(1299)) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TAXONOMY_TABLE + " (" + " site_id INTEGER DEFAULT 0," + " tag TEXT," + " PRIMARY KEY (site_id, tag)" + " );");
        }
    }

    private static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1300)) {
            db.execSQL("DROP TABLE IF EXISTS " + SUGGESTIONS_TABLE);
        }
        if (!ListenerUtil.mutListener.listen(1301)) {
            db.execSQL("DROP TABLE IF EXISTS " + TAXONOMY_TABLE);
        }
    }

    public static void reset(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1302)) {
            AppLog.i(AppLog.T.SUGGESTION, "resetting suggestion tables");
        }
        if (!ListenerUtil.mutListener.listen(1303)) {
            dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(1304)) {
            createTables(db);
        }
    }

    private static SQLiteDatabase getReadableDb() {
        return WordPress.wpDB.getDatabase();
    }

    private static SQLiteDatabase getWritableDb() {
        return WordPress.wpDB.getDatabase();
    }

    public static void insertSuggestionsForSite(final long siteId, final List<UserSuggestion> suggestions) {
        if (!ListenerUtil.mutListener.listen(1305)) {
            // we want to delete the current suggestions, so that removed users will not show up as a suggestion
            deleteSuggestionsForSite(siteId);
        }
        if (!ListenerUtil.mutListener.listen(1306)) {
            // performance when there are a lot of suggestions
            getWritableDb().beginTransaction();
        }
        if (!ListenerUtil.mutListener.listen(1309)) {
            if (suggestions != null) {
                if (!ListenerUtil.mutListener.listen(1308)) {
                    {
                        long _loopCounter46 = 0;
                        for (UserSuggestion suggestion : suggestions) {
                            ListenerUtil.loopListener.listen("_loopCounter46", ++_loopCounter46);
                            if (!ListenerUtil.mutListener.listen(1307)) {
                                addSuggestion(suggestion);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1310)) {
            getWritableDb().setTransactionSuccessful();
        }
        if (!ListenerUtil.mutListener.listen(1311)) {
            getWritableDb().endTransaction();
        }
    }

    public static void addSuggestion(final UserSuggestion suggestion) {
        if (!ListenerUtil.mutListener.listen(1312)) {
            if (suggestion == null) {
                return;
            }
        }
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(1313)) {
            values.put("site_id", suggestion.siteID);
        }
        if (!ListenerUtil.mutListener.listen(1314)) {
            values.put("user_login", suggestion.getUserLogin());
        }
        if (!ListenerUtil.mutListener.listen(1315)) {
            values.put("display_name", suggestion.getDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(1316)) {
            values.put("image_url", suggestion.getImageUrl());
        }
        if (!ListenerUtil.mutListener.listen(1317)) {
            values.put("taxonomy", suggestion.getTaxonomy());
        }
        if (!ListenerUtil.mutListener.listen(1318)) {
            getWritableDb().insertWithOnConflict(SUGGESTIONS_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public static List<UserSuggestion> getSuggestionsForSite(long siteId) {
        List<UserSuggestion> suggestions = new ArrayList<UserSuggestion>();
        String[] args = { Long.toString(siteId) };
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + SUGGESTIONS_TABLE + " WHERE site_id=? ORDER BY user_login ASC", args);
        try {
            if (!ListenerUtil.mutListener.listen(1322)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(1321)) {
                        {
                            long _loopCounter47 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter47", ++_loopCounter47);
                                UserSuggestion comment = getSuggestionFromCursor(c);
                                if (!ListenerUtil.mutListener.listen(1320)) {
                                    suggestions.add(comment);
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return suggestions;
        } finally {
            if (!ListenerUtil.mutListener.listen(1319)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static int deleteSuggestionsForSite(long siteId) {
        return getWritableDb().delete(SUGGESTIONS_TABLE, "site_id=?", new String[] { Long.toString(siteId) });
    }

    private static UserSuggestion getSuggestionFromCursor(Cursor c) {
        final String userLogin = c.getString(c.getColumnIndexOrThrow("user_login"));
        final String displayName = c.getString(c.getColumnIndexOrThrow("display_name"));
        final String imageUrl = c.getString(c.getColumnIndexOrThrow("image_url"));
        final String taxonomy = c.getString(c.getColumnIndexOrThrow("taxonomy"));
        long siteId = c.getLong(c.getColumnIndexOrThrow("site_id"));
        return new UserSuggestion(siteId, userLogin, displayName, imageUrl, taxonomy);
    }

    public static void insertTagsForSite(final long siteId, final List<Tag> tags) {
        if (!ListenerUtil.mutListener.listen(1323)) {
            // we want to delete the current tags, so that removed tags will not show up
            deleteTagsForSite(siteId);
        }
        if (!ListenerUtil.mutListener.listen(1326)) {
            if (tags != null) {
                if (!ListenerUtil.mutListener.listen(1325)) {
                    {
                        long _loopCounter48 = 0;
                        for (Tag tag : tags) {
                            ListenerUtil.loopListener.listen("_loopCounter48", ++_loopCounter48);
                            if (!ListenerUtil.mutListener.listen(1324)) {
                                addTag(tag);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void addTag(final Tag tag) {
        if (!ListenerUtil.mutListener.listen(1327)) {
            if (tag == null) {
                return;
            }
        }
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(1328)) {
            values.put("site_id", tag.siteID);
        }
        if (!ListenerUtil.mutListener.listen(1329)) {
            values.put("tag", tag.getTag());
        }
        if (!ListenerUtil.mutListener.listen(1330)) {
            getWritableDb().insertWithOnConflict(TAXONOMY_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public static List<Tag> getTagsForSite(long siteId) {
        List<Tag> tags = new ArrayList<Tag>();
        String[] args = { Long.toString(siteId) };
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + TAXONOMY_TABLE + " WHERE site_id=? ORDER BY tag ASC", args);
        try {
            if (!ListenerUtil.mutListener.listen(1334)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(1333)) {
                        {
                            long _loopCounter49 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                                Tag comment = getTagFromCursor(c);
                                if (!ListenerUtil.mutListener.listen(1332)) {
                                    tags.add(comment);
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return tags;
        } finally {
            if (!ListenerUtil.mutListener.listen(1331)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static int deleteTagsForSite(long siteId) {
        return getWritableDb().delete(TAXONOMY_TABLE, "site_id=?", new String[] { Long.toString(siteId) });
    }

    private static Tag getTagFromCursor(Cursor c) {
        final String tag = c.getString(c.getColumnIndexOrThrow("tag"));
        long siteId = c.getLong(c.getColumnIndexOrThrow("site_id"));
        return new Tag(siteId, tag);
    }
}
