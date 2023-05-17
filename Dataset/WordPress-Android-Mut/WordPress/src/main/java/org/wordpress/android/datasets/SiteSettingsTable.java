package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import androidx.collection.SparseArrayCompat;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.CategoryModel;
import org.wordpress.android.models.SiteSettingsModel;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SqlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class SiteSettingsTable {

    private static final String CATEGORIES_TABLE_NAME = "site_categories";

    private static final String CREATE_CATEGORIES_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + CATEGORIES_TABLE_NAME + " (" + CategoryModel.ID_COLUMN_NAME + " INTEGER PRIMARY KEY, " + CategoryModel.NAME_COLUMN_NAME + " TEXT, " + CategoryModel.SLUG_COLUMN_NAME + " TEXT, " + CategoryModel.DESC_COLUMN_NAME + " TEXT, " + CategoryModel.PARENT_ID_COLUMN_NAME + " INTEGER, " + CategoryModel.POST_COUNT_COLUMN_NAME + " INTEGER" + ");";

    public static void createTable(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1233)) {
            if (db != null) {
                if (!ListenerUtil.mutListener.listen(1231)) {
                    db.execSQL(SiteSettingsModel.CREATE_SETTINGS_TABLE_SQL);
                }
                if (!ListenerUtil.mutListener.listen(1232)) {
                    db.execSQL(CREATE_CATEGORIES_TABLE_SQL);
                }
            }
        }
    }

    public static void addSharingColumnsToSiteSettingsTable(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1240)) {
            if (db != null) {
                if (!ListenerUtil.mutListener.listen(1234)) {
                    db.execSQL(SiteSettingsModel.ADD_SHARING_LABEL);
                }
                if (!ListenerUtil.mutListener.listen(1235)) {
                    db.execSQL(SiteSettingsModel.ADD_SHARING_BUTTON_STYLE);
                }
                if (!ListenerUtil.mutListener.listen(1236)) {
                    db.execSQL(SiteSettingsModel.ADD_ALLOW_REBLOG_BUTTON);
                }
                if (!ListenerUtil.mutListener.listen(1237)) {
                    db.execSQL(SiteSettingsModel.ADD_ALLOW_LIKE_BUTTON);
                }
                if (!ListenerUtil.mutListener.listen(1238)) {
                    db.execSQL(SiteSettingsModel.ADD_ALLOW_COMMENT_LIKES);
                }
                if (!ListenerUtil.mutListener.listen(1239)) {
                    db.execSQL(SiteSettingsModel.ADD_TWITTER_USERNAME);
                }
            }
        }
    }

    public static SparseArrayCompat<CategoryModel> getAllCategories() {
        String sqlCommand = sqlSelectAllCategories() + ";";
        Cursor cursor = WordPress.wpDB.getDatabase().rawQuery(sqlCommand, null);
        try {
            if ((ListenerUtil.mutListener.listen(1245) ? ((ListenerUtil.mutListener.listen(1244) ? (cursor == null && !cursor.moveToFirst()) : (cursor == null || !cursor.moveToFirst())) && cursor.getCount() == 0) : ((ListenerUtil.mutListener.listen(1244) ? (cursor == null && !cursor.moveToFirst()) : (cursor == null || !cursor.moveToFirst())) || cursor.getCount() == 0))) {
                return null;
            }
            SparseArrayCompat<CategoryModel> models = new SparseArrayCompat<>();
            if (!ListenerUtil.mutListener.listen(1254)) {
                {
                    long _loopCounter44 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(1253) ? (i >= cursor.getCount()) : (ListenerUtil.mutListener.listen(1252) ? (i <= cursor.getCount()) : (ListenerUtil.mutListener.listen(1251) ? (i > cursor.getCount()) : (ListenerUtil.mutListener.listen(1250) ? (i != cursor.getCount()) : (ListenerUtil.mutListener.listen(1249) ? (i == cursor.getCount()) : (i < cursor.getCount())))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter44", ++_loopCounter44);
                        CategoryModel model = new CategoryModel();
                        if (!ListenerUtil.mutListener.listen(1246)) {
                            model.deserializeFromDatabase(cursor);
                        }
                        if (!ListenerUtil.mutListener.listen(1247)) {
                            models.put(model.id, model);
                        }
                        if (!ListenerUtil.mutListener.listen(1248)) {
                            cursor.moveToNext();
                        }
                    }
                }
            }
            return models;
        } finally {
            if (!ListenerUtil.mutListener.listen(1243)) {
                if ((ListenerUtil.mutListener.listen(1241) ? (cursor != null || !cursor.isClosed()) : (cursor != null && !cursor.isClosed()))) {
                    if (!ListenerUtil.mutListener.listen(1242)) {
                        cursor.close();
                    }
                }
            }
        }
    }

    public static Cursor getCategory(long id) {
        if (!ListenerUtil.mutListener.listen(1260)) {
            if ((ListenerUtil.mutListener.listen(1259) ? (id >= 0) : (ListenerUtil.mutListener.listen(1258) ? (id <= 0) : (ListenerUtil.mutListener.listen(1257) ? (id > 0) : (ListenerUtil.mutListener.listen(1256) ? (id != 0) : (ListenerUtil.mutListener.listen(1255) ? (id == 0) : (id < 0))))))) {
                return null;
            }
        }
        String sqlCommand = sqlSelectAllCategories() + sqlWhere(CategoryModel.ID_COLUMN_NAME, Long.toString(id)) + ";";
        return WordPress.wpDB.getDatabase().rawQuery(sqlCommand, null);
    }

    public static Cursor getSettings(long id) {
        if (!ListenerUtil.mutListener.listen(1266)) {
            if ((ListenerUtil.mutListener.listen(1265) ? (id >= 0) : (ListenerUtil.mutListener.listen(1264) ? (id <= 0) : (ListenerUtil.mutListener.listen(1263) ? (id > 0) : (ListenerUtil.mutListener.listen(1262) ? (id != 0) : (ListenerUtil.mutListener.listen(1261) ? (id == 0) : (id < 0))))))) {
                return null;
            }
        }
        String whereClause = sqlWhere(SiteSettingsModel.ID_COLUMN_NAME, Long.toString(id));
        String sqlCommand = sqlSelectAllSettings() + whereClause + ";";
        return WordPress.wpDB.getDatabase().rawQuery(sqlCommand, null);
    }

    public static void saveCategory(CategoryModel category) {
        if (!ListenerUtil.mutListener.listen(1267)) {
            if (category == null) {
                return;
            }
        }
        ContentValues values = category.serializeToDatabase();
        if (!ListenerUtil.mutListener.listen(1268)) {
            category.isInLocalTable = WordPress.wpDB.getDatabase().insertWithOnConflict(CATEGORIES_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE) != -1;
        }
    }

    public static void saveCategories(CategoryModel[] categories) {
        if (!ListenerUtil.mutListener.listen(1269)) {
            if (categories == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1271)) {
            {
                long _loopCounter45 = 0;
                for (CategoryModel category : categories) {
                    ListenerUtil.loopListener.listen("_loopCounter45", ++_loopCounter45);
                    if (!ListenerUtil.mutListener.listen(1270)) {
                        saveCategory(category);
                    }
                }
            }
        }
    }

    public static void saveSettings(SiteSettingsModel settings) {
        if (!ListenerUtil.mutListener.listen(1272)) {
            if (settings == null) {
                return;
            }
        }
        ContentValues values = settings.serializeToDatabase();
        if (!ListenerUtil.mutListener.listen(1273)) {
            settings.isInLocalTable = WordPress.wpDB.getDatabase().insertWithOnConflict(SiteSettingsModel.SETTINGS_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE) != -1;
        }
        if (!ListenerUtil.mutListener.listen(1274)) {
            saveCategories(settings.categories);
        }
    }

    private static String sqlSelectAllCategories() {
        return "SELECT * FROM " + CATEGORIES_TABLE_NAME + " ";
    }

    private static String sqlSelectAllSettings() {
        return "SELECT * FROM " + SiteSettingsModel.SETTINGS_TABLE_NAME + " ";
    }

    private static String sqlWhere(String variable, String value) {
        return "WHERE " + variable + "=\"" + value + "\" ";
    }

    public static boolean migrateMediaOptimizeSettings(SQLiteDatabase db) {
        Cursor cursor = null;
        try {
            String sqlCommand = "SELECT * FROM " + SiteSettingsModel.SETTINGS_TABLE_NAME + ";";
            if (!ListenerUtil.mutListener.listen(1277)) {
                cursor = db.rawQuery(sqlCommand, null);
            }
            if (!ListenerUtil.mutListener.listen(1280)) {
                if ((ListenerUtil.mutListener.listen(1279) ? ((ListenerUtil.mutListener.listen(1278) ? (cursor == null && cursor.getCount() == 0) : (cursor == null || cursor.getCount() == 0)) && !cursor.moveToFirst()) : ((ListenerUtil.mutListener.listen(1278) ? (cursor == null && cursor.getCount() == 0) : (cursor == null || cursor.getCount() == 0)) || !cursor.moveToFirst()))) {
                    return false;
                }
            }
            int columnIndex = cursor.getColumnIndex("optimizedImage");
            if (!ListenerUtil.mutListener.listen(1286)) {
                if ((ListenerUtil.mutListener.listen(1285) ? (columnIndex >= -1) : (ListenerUtil.mutListener.listen(1284) ? (columnIndex <= -1) : (ListenerUtil.mutListener.listen(1283) ? (columnIndex > -1) : (ListenerUtil.mutListener.listen(1282) ? (columnIndex < -1) : (ListenerUtil.mutListener.listen(1281) ? (columnIndex != -1) : (columnIndex == -1))))))) {
                    // No old columns for media optimization settings
                    return false;
                }
            }
            // we're safe to read all the settings now since all the columns must be there
            int optimizeImageOldSettings = cursor.getInt(columnIndex);
            if (!ListenerUtil.mutListener.listen(1292)) {
                AppPrefs.setImageOptimize((ListenerUtil.mutListener.listen(1291) ? (optimizeImageOldSettings >= 1) : (ListenerUtil.mutListener.listen(1290) ? (optimizeImageOldSettings <= 1) : (ListenerUtil.mutListener.listen(1289) ? (optimizeImageOldSettings > 1) : (ListenerUtil.mutListener.listen(1288) ? (optimizeImageOldSettings < 1) : (ListenerUtil.mutListener.listen(1287) ? (optimizeImageOldSettings != 1) : (optimizeImageOldSettings == 1)))))));
            }
            if (!ListenerUtil.mutListener.listen(1293)) {
                AppPrefs.setImageOptimizeMaxSize(cursor.getInt(cursor.getColumnIndexOrThrow("maxImageWidth")));
            }
            if (!ListenerUtil.mutListener.listen(1294)) {
                AppPrefs.setImageOptimizeQuality(cursor.getInt(cursor.getColumnIndexOrThrow("imageEncoderQuality")));
            }
            if (!ListenerUtil.mutListener.listen(1295)) {
                AppPrefs.setVideoOptimize(cursor.getInt(cursor.getColumnIndexOrThrow("optimizedVideo")) == 1);
            }
            if (!ListenerUtil.mutListener.listen(1296)) {
                AppPrefs.setVideoOptimizeWidth(cursor.getInt(cursor.getColumnIndexOrThrow("maxVideoWidth")));
            }
            if (!ListenerUtil.mutListener.listen(1297)) {
                AppPrefs.setVideoOptimizeQuality(cursor.getInt(cursor.getColumnIndexOrThrow("videoEncoderBitrate")));
            }
            return true;
        } catch (SQLException e) {
            if (!ListenerUtil.mutListener.listen(1275)) {
                AppLog.e(AppLog.T.DB, "Failed to copy media optimization settings", e);
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1276)) {
                SqlUtils.closeCursor(cursor);
            }
        }
        return false;
    }
}
