package org.wordpress.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import org.wordpress.android.datasets.NotificationsTable;
import org.wordpress.android.datasets.PeopleTable;
import org.wordpress.android.datasets.PublicizeTable;
import org.wordpress.android.datasets.SiteSettingsTable;
import org.wordpress.android.datasets.UserSuggestionTable;
import org.wordpress.android.models.SiteSettingsModel;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WordPressDB {

    private static final int DATABASE_VERSION = 67;

    // Warning renaming DATABASE_NAME could break previous App backups (see: xml/backup_scheme.xml)
    private static final String DATABASE_NAME = "wordpress";

    private static final String NOTES_TABLE = "notes";

    private static final String THEMES_TABLE = "themes";

    // add new table for QuickPress homescreen shortcuts
    private static final String CREATE_TABLE_QUICKPRESS_SHORTCUTS = "create table if not exists quickpress_shortcuts (id integer primary key autoincrement, " + "accountId text, name text);";

    private static final String QUICKPRESS_SHORTCUTS_TABLE = "quickpress_shortcuts";

    private static final String DROP_TABLE_PREFIX = "DROP TABLE IF EXISTS ";

    private SQLiteDatabase mDb;

    @SuppressWarnings({ "FallThrough" })
    public WordPressDB(Context ctx) {
        if (!ListenerUtil.mutListener.listen(29553)) {
            mDb = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        }
        if (!ListenerUtil.mutListener.listen(29554)) {
            // Create tables if they don't exist
            mDb.execSQL(CREATE_TABLE_QUICKPRESS_SHORTCUTS);
        }
        if (!ListenerUtil.mutListener.listen(29555)) {
            SiteSettingsTable.createTable(mDb);
        }
        if (!ListenerUtil.mutListener.listen(29556)) {
            UserSuggestionTable.createTables(mDb);
        }
        if (!ListenerUtil.mutListener.listen(29557)) {
            NotificationsTable.createTables(mDb);
        }
        // Update tables for new installs and app updates
        int currentVersion = mDb.getVersion();
        boolean isNewInstall = ((ListenerUtil.mutListener.listen(29562) ? (currentVersion >= 0) : (ListenerUtil.mutListener.listen(29561) ? (currentVersion <= 0) : (ListenerUtil.mutListener.listen(29560) ? (currentVersion > 0) : (ListenerUtil.mutListener.listen(29559) ? (currentVersion < 0) : (ListenerUtil.mutListener.listen(29558) ? (currentVersion != 0) : (currentVersion == 0)))))));
        if (!ListenerUtil.mutListener.listen(29570)) {
            if ((ListenerUtil.mutListener.listen(29568) ? (!isNewInstall || (ListenerUtil.mutListener.listen(29567) ? (currentVersion >= DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29566) ? (currentVersion <= DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29565) ? (currentVersion > DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29564) ? (currentVersion < DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29563) ? (currentVersion == DATABASE_VERSION) : (currentVersion != DATABASE_VERSION))))))) : (!isNewInstall && (ListenerUtil.mutListener.listen(29567) ? (currentVersion >= DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29566) ? (currentVersion <= DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29565) ? (currentVersion > DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29564) ? (currentVersion < DATABASE_VERSION) : (ListenerUtil.mutListener.listen(29563) ? (currentVersion == DATABASE_VERSION) : (currentVersion != DATABASE_VERSION))))))))) {
                if (!ListenerUtil.mutListener.listen(29569)) {
                    AppLog.d(T.DB, "upgrading database from version " + currentVersion + " to " + DATABASE_VERSION);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(29594)) {
            switch(currentVersion) {
                case 0:
                // New install
                case 1:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                // No longer used (preferences migration)
                case 16:
                case 17:
                case 18:
                case 19:
                // revision 20: create table "notes"
                case 20:
                case 21:
                // version 23 added CommentTable.java, version 24 changed the comment table schema
                case 22:
                case 23:
                case 24:
                case 25:
                // constraints from a table.
                case 26:
                    if (!ListenerUtil.mutListener.listen(29571)) {
                        // Drop the notes table, no longer needed with Simperium.
                        mDb.execSQL(DROP_TABLE_PREFIX + NOTES_TABLE);
                    }
                case 27:
                case 28:
                // NOPE: removeWPComCredentials();
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                    if (!ListenerUtil.mutListener.listen(29572)) {
                        // Fix an issue when note id > MAX_INT
                        ctx.deleteDatabase("simperium-store");
                    }
                case 36:
                    if (!ListenerUtil.mutListener.listen(29573)) {
                        // Fix a sync issue happening for users who have both wpios and wpandroid active clients
                        ctx.deleteDatabase("simperium-store");
                    }
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                    if (!ListenerUtil.mutListener.listen(29574)) {
                        PeopleTable.createTables(mDb);
                    }
                case 45:
                case 46:
                case 47:
                    if (!ListenerUtil.mutListener.listen(29575)) {
                        PeopleTable.reset(mDb);
                    }
                case 48:
                    if (!ListenerUtil.mutListener.listen(29576)) {
                        PeopleTable.createViewersTable(mDb);
                    }
                case 49:
                    if (!ListenerUtil.mutListener.listen(29577)) {
                        // Delete simperium DB since we're removing Simperium from the app.
                        ctx.deleteDatabase("simperium-store");
                    }
                case 50:
                // fix #5373 - no op
                case 51:
                // no op - was SiteSettingsTable.addOptimizedImageToSiteSettingsTable(db);
                case 52:
                // no op - was used for old image optimization settings
                case 53:
                    if (!ListenerUtil.mutListener.listen(29578)) {
                        // Clean up empty cache files caused by #5417
                        clearEmptyCacheFiles(ctx);
                    }
                case 54:
                // no op - was used for old image optimization settings
                case 55:
                    if (!ListenerUtil.mutListener.listen(29579)) {
                        SiteSettingsTable.addSharingColumnsToSiteSettingsTable(mDb);
                    }
                case 56:
                // no op - was used for old video optimization settings
                case 57:
                    if (!ListenerUtil.mutListener.listen(29580)) {
                        // Migrate media optimization settings
                        SiteSettingsTable.migrateMediaOptimizeSettings(mDb);
                    }
                case 58:
                    if (!ListenerUtil.mutListener.listen(29581)) {
                        // ThemeStore merged, remove deprecated themes tables
                        mDb.execSQL(DROP_TABLE_PREFIX + THEMES_TABLE);
                    }
                case 59:
                    if (!ListenerUtil.mutListener.listen(29582)) {
                        // Enable Aztec for all users
                        AppPrefs.setAztecEditorEnabled(true);
                    }
                case 60:
                    if (!ListenerUtil.mutListener.listen(29583)) {
                        // add Start of Week site setting as part of #betterjetpackxp
                        mDb.execSQL(SiteSettingsModel.ADD_START_OF_WEEK);
                    }
                case 61:
                    if (!ListenerUtil.mutListener.listen(29584)) {
                        // add date & time format site setting as part of #betterjetpackxp
                        mDb.execSQL(SiteSettingsModel.ADD_TIME_FORMAT);
                    }
                    if (!ListenerUtil.mutListener.listen(29585)) {
                        mDb.execSQL(SiteSettingsModel.ADD_DATE_FORMAT);
                    }
                case 62:
                    if (!ListenerUtil.mutListener.listen(29586)) {
                        // add timezone and posts per page site setting as part of #betterjetpackxp
                        mDb.execSQL(SiteSettingsModel.ADD_TIMEZONE);
                    }
                    if (!ListenerUtil.mutListener.listen(29587)) {
                        mDb.execSQL(SiteSettingsModel.ADD_POSTS_PER_PAGE);
                    }
                case 63:
                    if (!ListenerUtil.mutListener.listen(29588)) {
                        // add AMP site setting as part of #betterjetpackxp
                        mDb.execSQL(SiteSettingsModel.ADD_AMP_SUPPORTED);
                    }
                    if (!ListenerUtil.mutListener.listen(29589)) {
                        mDb.execSQL(SiteSettingsModel.ADD_AMP_ENABLED);
                    }
                case 64:
                    if (!ListenerUtil.mutListener.listen(29590)) {
                        // add site icon
                        mDb.execSQL(SiteSettingsModel.ADD_SITE_ICON);
                    }
                case 65:
                    if (!ListenerUtil.mutListener.listen(29591)) {
                        // add external users only to publicize services table
                        PublicizeTable.resetServicesTable(mDb);
                    }
                case 66:
                    if (!ListenerUtil.mutListener.listen(29592)) {
                        // add Jetpack search site setting
                        mDb.execSQL(SiteSettingsModel.ADD_JETPACK_SEARCH_SUPPORTED);
                    }
                    if (!ListenerUtil.mutListener.listen(29593)) {
                        mDb.execSQL(SiteSettingsModel.ADD_JETPACK_SEARCH_ENABLED);
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(29595)) {
            mDb.setVersion(DATABASE_VERSION);
        }
    }

    public SQLiteDatabase getDatabase() {
        return mDb;
    }

    public static void deleteDatabase(Context ctx) {
        if (!ListenerUtil.mutListener.listen(29596)) {
            ctx.deleteDatabase(DATABASE_NAME);
        }
    }

    public boolean addQuickPressShortcut(int blogId, String name) {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(29597)) {
            values.put("accountId", blogId);
        }
        if (!ListenerUtil.mutListener.listen(29598)) {
            values.put("name", name);
        }
        boolean returnValue = false;
        synchronized (this) {
            if (!ListenerUtil.mutListener.listen(29604)) {
                returnValue = (ListenerUtil.mutListener.listen(29603) ? (mDb.insert(QUICKPRESS_SHORTCUTS_TABLE, null, values) >= 0) : (ListenerUtil.mutListener.listen(29602) ? (mDb.insert(QUICKPRESS_SHORTCUTS_TABLE, null, values) <= 0) : (ListenerUtil.mutListener.listen(29601) ? (mDb.insert(QUICKPRESS_SHORTCUTS_TABLE, null, values) < 0) : (ListenerUtil.mutListener.listen(29600) ? (mDb.insert(QUICKPRESS_SHORTCUTS_TABLE, null, values) != 0) : (ListenerUtil.mutListener.listen(29599) ? (mDb.insert(QUICKPRESS_SHORTCUTS_TABLE, null, values) == 0) : (mDb.insert(QUICKPRESS_SHORTCUTS_TABLE, null, values) > 0))))));
            }
        }
        return (returnValue);
    }

    /*
     * used during development to copy database to SD card so we can access it via DDMS
     */
    protected void copyDatabase() {
        String copyFrom = mDb.getPath();
        String copyTo = WordPress.getContext().getExternalFilesDir(null).getAbsolutePath() + "/" + DATABASE_NAME + ".db";
        try {
            InputStream input = new FileInputStream(copyFrom);
            OutputStream output = new FileOutputStream(copyTo);
            byte[] buffer = new byte[1024];
            int length;
            if (!ListenerUtil.mutListener.listen(29612)) {
                {
                    long _loopCounter445 = 0;
                    while ((ListenerUtil.mutListener.listen(29611) ? ((length = input.read(buffer)) >= 0) : (ListenerUtil.mutListener.listen(29610) ? ((length = input.read(buffer)) <= 0) : (ListenerUtil.mutListener.listen(29609) ? ((length = input.read(buffer)) < 0) : (ListenerUtil.mutListener.listen(29608) ? ((length = input.read(buffer)) != 0) : (ListenerUtil.mutListener.listen(29607) ? ((length = input.read(buffer)) == 0) : ((length = input.read(buffer)) > 0))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter445", ++_loopCounter445);
                        if (!ListenerUtil.mutListener.listen(29606)) {
                            output.write(buffer, 0, length);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(29613)) {
                output.flush();
            }
            if (!ListenerUtil.mutListener.listen(29614)) {
                output.close();
            }
            if (!ListenerUtil.mutListener.listen(29615)) {
                input.close();
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(29605)) {
                AppLog.e(T.DB, "failed to copy database", e);
            }
        }
    }

    private void clearEmptyCacheFiles(Context context) {
        if (!ListenerUtil.mutListener.listen(29619)) {
            if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                File imageCacheDir = new File(android.os.Environment.getExternalStorageDirectory() + "/WordPress/images");
                File videoCacheDir = new File(android.os.Environment.getExternalStorageDirectory() + "/WordPress/video");
                if (!ListenerUtil.mutListener.listen(29617)) {
                    deleteEmptyFilesInDirectory(imageCacheDir);
                }
                if (!ListenerUtil.mutListener.listen(29618)) {
                    deleteEmptyFilesInDirectory(videoCacheDir);
                }
            } else {
                File cacheDir = context.getApplicationContext().getCacheDir();
                if (!ListenerUtil.mutListener.listen(29616)) {
                    deleteEmptyFilesInDirectory(cacheDir);
                }
            }
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void deleteEmptyFilesInDirectory(File directory) {
        if (!ListenerUtil.mutListener.listen(29622)) {
            if ((ListenerUtil.mutListener.listen(29621) ? ((ListenerUtil.mutListener.listen(29620) ? (directory == null && !directory.exists()) : (directory == null || !directory.exists())) && directory.listFiles() == null) : ((ListenerUtil.mutListener.listen(29620) ? (directory == null && !directory.exists()) : (directory == null || !directory.exists())) || directory.listFiles() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(29631)) {
            {
                long _loopCounter446 = 0;
                for (File file : directory.listFiles()) {
                    ListenerUtil.loopListener.listen("_loopCounter446", ++_loopCounter446);
                    if (!ListenerUtil.mutListener.listen(29630)) {
                        if ((ListenerUtil.mutListener.listen(29628) ? (file != null || (ListenerUtil.mutListener.listen(29627) ? (file.length() >= 0) : (ListenerUtil.mutListener.listen(29626) ? (file.length() <= 0) : (ListenerUtil.mutListener.listen(29625) ? (file.length() > 0) : (ListenerUtil.mutListener.listen(29624) ? (file.length() < 0) : (ListenerUtil.mutListener.listen(29623) ? (file.length() != 0) : (file.length() == 0))))))) : (file != null && (ListenerUtil.mutListener.listen(29627) ? (file.length() >= 0) : (ListenerUtil.mutListener.listen(29626) ? (file.length() <= 0) : (ListenerUtil.mutListener.listen(29625) ? (file.length() > 0) : (ListenerUtil.mutListener.listen(29624) ? (file.length() < 0) : (ListenerUtil.mutListener.listen(29623) ? (file.length() != 0) : (file.length() == 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(29629)) {
                                file.delete();
                            }
                        }
                    }
                }
            }
        }
    }
}
