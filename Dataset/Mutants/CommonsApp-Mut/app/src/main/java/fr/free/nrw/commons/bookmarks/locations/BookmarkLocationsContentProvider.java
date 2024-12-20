package fr.free.nrw.commons.bookmarks.locations;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
// We can get uri using java.Net.Uri, but andoid implimentation is faster (but it's forgiving with handling exceptions though)
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import javax.inject.Inject;
import fr.free.nrw.commons.BuildConfig;
import fr.free.nrw.commons.data.DBOpenHelper;
import fr.free.nrw.commons.di.CommonsDaggerContentProvider;
import timber.log.Timber;
import static fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao.Table.COLUMN_NAME;
import static fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao.Table.TABLE_NAME;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handles private storage for Bookmark locations
 */
public class BookmarkLocationsContentProvider extends CommonsDaggerContentProvider {

    private static final String BASE_PATH = "bookmarksLocations";

    public static final Uri BASE_URI = Uri.parse("content://" + BuildConfig.BOOKMARK_LOCATIONS_AUTHORITY + "/" + BASE_PATH);

    /**
     * Append bookmark locations name to the base uri
     */
    public static Uri uriForName(String name) {
        return Uri.parse(BASE_URI.toString() + "/" + name);
    }

    @Inject
    DBOpenHelper dbOpenHelper;

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    /**
     * Queries the SQLite database for the bookmark locations
     * @param uri : contains the uri for bookmark locations
     * @param projection
     * @param selection : handles Where
     * @param selectionArgs : the condition of Where clause
     * @param sortOrder : ascending or descending
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        if (!ListenerUtil.mutListener.listen(4993)) {
            queryBuilder.setTables(TABLE_NAME);
        }
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        if (!ListenerUtil.mutListener.listen(4994)) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    /**
     * Handles the update query of local SQLite Database
     * @param uri : contains the uri for bookmark locations
     * @param contentValues : new values to be entered to db
     * @param selection : handles Where
     * @param selectionArgs : the condition of Where clause
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDB = dbOpenHelper.getWritableDatabase();
        int rowsUpdated;
        if (TextUtils.isEmpty(selection)) {
            int id = Integer.valueOf(uri.getLastPathSegment());
            rowsUpdated = sqlDB.update(TABLE_NAME, contentValues, COLUMN_NAME + " = ?", new String[] { String.valueOf(id) });
        } else {
            throw new IllegalArgumentException("Parameter `selection` should be empty when updating an ID");
        }
        if (!ListenerUtil.mutListener.listen(4995)) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    /**
     * Handles the insertion of new bookmark locations record to local SQLite Database
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        SQLiteDatabase sqlDB = dbOpenHelper.getWritableDatabase();
        long id = sqlDB.insert(BookmarkLocationsDao.Table.TABLE_NAME, null, contentValues);
        if (!ListenerUtil.mutListener.listen(4996)) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(BASE_URI + "/" + id);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        int rows;
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        if (!ListenerUtil.mutListener.listen(4997)) {
            Timber.d("Deleting bookmark name %s", uri.getLastPathSegment());
        }
        rows = db.delete(TABLE_NAME, "location_name = ?", new String[] { uri.getLastPathSegment() });
        if (!ListenerUtil.mutListener.listen(4998)) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows;
    }
}
