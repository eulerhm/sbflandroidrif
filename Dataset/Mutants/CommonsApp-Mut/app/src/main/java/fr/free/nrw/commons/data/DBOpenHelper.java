package fr.free.nrw.commons.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import fr.free.nrw.commons.bookmarks.items.BookmarkItemsDao;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao;
import fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesDao;
import fr.free.nrw.commons.category.CategoryDao;
import fr.free.nrw.commons.explore.recentsearches.RecentSearchesDao;
import fr.free.nrw.commons.recentlanguages.RecentLanguagesDao;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "commons.db";

    private static final int DATABASE_VERSION = 20;

    public static final String CONTRIBUTIONS_TABLE = "contributions";

    private final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS %s";

    /**
     * Do not use directly - @Inject an instance where it's needed and let
     * dependency injection take care of managing this as a singleton.
     */
    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (!ListenerUtil.mutListener.listen(6248)) {
            CategoryDao.Table.onCreate(sqLiteDatabase);
        }
        if (!ListenerUtil.mutListener.listen(6249)) {
            BookmarkPicturesDao.Table.onCreate(sqLiteDatabase);
        }
        if (!ListenerUtil.mutListener.listen(6250)) {
            BookmarkLocationsDao.Table.onCreate(sqLiteDatabase);
        }
        if (!ListenerUtil.mutListener.listen(6251)) {
            BookmarkItemsDao.Table.onCreate(sqLiteDatabase);
        }
        if (!ListenerUtil.mutListener.listen(6252)) {
            RecentSearchesDao.Table.onCreate(sqLiteDatabase);
        }
        if (!ListenerUtil.mutListener.listen(6253)) {
            RecentLanguagesDao.Table.onCreate(sqLiteDatabase);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int from, int to) {
        if (!ListenerUtil.mutListener.listen(6254)) {
            CategoryDao.Table.onUpdate(sqLiteDatabase, from, to);
        }
        if (!ListenerUtil.mutListener.listen(6255)) {
            BookmarkPicturesDao.Table.onUpdate(sqLiteDatabase, from, to);
        }
        if (!ListenerUtil.mutListener.listen(6256)) {
            BookmarkLocationsDao.Table.onUpdate(sqLiteDatabase, from, to);
        }
        if (!ListenerUtil.mutListener.listen(6257)) {
            BookmarkItemsDao.Table.onUpdate(sqLiteDatabase, from, to);
        }
        if (!ListenerUtil.mutListener.listen(6258)) {
            RecentSearchesDao.Table.onUpdate(sqLiteDatabase, from, to);
        }
        if (!ListenerUtil.mutListener.listen(6259)) {
            RecentLanguagesDao.Table.onUpdate(sqLiteDatabase, from, to);
        }
        if (!ListenerUtil.mutListener.listen(6260)) {
            deleteTable(sqLiteDatabase, CONTRIBUTIONS_TABLE);
        }
    }

    /**
     * Delete table in the given db
     * @param db
     * @param tableName
     */
    public void deleteTable(SQLiteDatabase db, String tableName) {
        try {
            if (!ListenerUtil.mutListener.listen(6262)) {
                db.execSQL(String.format(DROP_TABLE_STATEMENT, tableName));
            }
            if (!ListenerUtil.mutListener.listen(6263)) {
                onCreate(db);
            }
        } catch (SQLiteException e) {
            if (!ListenerUtil.mutListener.listen(6261)) {
                e.printStackTrace();
            }
        }
    }
}
