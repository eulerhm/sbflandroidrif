package fr.free.nrw.commons.explore.recentsearches;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fr.free.nrw.commons.explore.models.RecentSearch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This class doesn't execute queries in database directly instead it contains the logic behind
 * inserting, deleting, searching data from recent searches database.
 */
public class RecentSearchesDao {

    private final Provider<ContentProviderClient> clientProvider;

    @Inject
    public RecentSearchesDao(@Named("recentsearch") Provider<ContentProviderClient> clientProvider) {
        this.clientProvider = clientProvider;
    }

    /**
     * This method is called on click of media/ categories for storing them in recent searches
     * @param recentSearch a recent searches object that is to be added in SqLite DB
     */
    public void save(RecentSearch recentSearch) {
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4348)) {
                if (recentSearch.getContentUri() == null) {
                    if (!ListenerUtil.mutListener.listen(4347)) {
                        recentSearch.setContentUri(db.insert(RecentSearchesContentProvider.BASE_URI, toContentValues(recentSearch)));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(4346)) {
                        db.update(recentSearch.getContentUri(), toContentValues(recentSearch), null, null);
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4345)) {
                db.release();
            }
        }
    }

    /**
     * This method is called on confirmation of delete recent searches.
     * It deletes all recent searches from the database
     */
    public void deleteAll() {
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4351)) {
                cursor = db.query(RecentSearchesContentProvider.BASE_URI, Table.ALL_FIELDS, null, new String[] {}, Table.COLUMN_LAST_USED + " DESC");
            }
            if (!ListenerUtil.mutListener.listen(4359)) {
                {
                    long _loopCounter65 = 0;
                    while ((ListenerUtil.mutListener.listen(4358) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext()))) {
                        ListenerUtil.loopListener.listen("_loopCounter65", ++_loopCounter65);
                        try {
                            RecentSearch recentSearch = find(fromCursor(cursor).getQuery());
                            if (!ListenerUtil.mutListener.listen(4357)) {
                                if (recentSearch.getContentUri() == null) {
                                    throw new RuntimeException("tried to delete item with no content URI");
                                } else {
                                    if (!ListenerUtil.mutListener.listen(4354)) {
                                        Timber.d("QUERY_NAME %s - delete tried", recentSearch.getContentUri());
                                    }
                                    if (!ListenerUtil.mutListener.listen(4355)) {
                                        db.delete(recentSearch.getContentUri(), null, null);
                                    }
                                    if (!ListenerUtil.mutListener.listen(4356)) {
                                        Timber.d("QUERY_NAME %s - query deleted", recentSearch.getQuery());
                                    }
                                }
                            }
                        } catch (RemoteException e) {
                            if (!ListenerUtil.mutListener.listen(4352)) {
                                Timber.e(e, "query deleted");
                            }
                            throw new RuntimeException(e);
                        } finally {
                            if (!ListenerUtil.mutListener.listen(4353)) {
                                db.release();
                            }
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4350)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(4349)) {
                        cursor.close();
                    }
                }
            }
        }
    }

    /**
     * Deletes a recent search from the database
     */
    public void delete(RecentSearch recentSearch) {
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4362)) {
                if (recentSearch.getContentUri() == null) {
                    throw new RuntimeException("tried to delete item with no content URI");
                } else {
                    if (!ListenerUtil.mutListener.listen(4361)) {
                        db.delete(recentSearch.getContentUri(), null, null);
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4360)) {
                db.release();
            }
        }
    }

    /**
     * Find persisted search query in database, based on its name.
     * @param name Search query  Ex- "butterfly"
     * @return recently searched query from database, or null if not found
     */
    @Nullable
    public RecentSearch find(String name) {
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4366)) {
                cursor = db.query(RecentSearchesContentProvider.BASE_URI, Table.ALL_FIELDS, Table.COLUMN_NAME + "=?", new String[] { name }, null);
            }
            if (!ListenerUtil.mutListener.listen(4368)) {
                if ((ListenerUtil.mutListener.listen(4367) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    return fromCursor(cursor);
                }
            }
        } catch (RemoteException e) {
            // This feels lazy, but to hell with checked exceptions. :)
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4364)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(4363)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4365)) {
                db.release();
            }
        }
        return null;
    }

    /**
     * Retrieve recently-searched queries, ordered by descending date.
     * @return a list containing recent searches
     */
    @NonNull
    public List<String> recentSearches(int limit) {
        List<String> items = new ArrayList<>();
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4372)) {
                cursor = db.query(RecentSearchesContentProvider.BASE_URI, Table.ALL_FIELDS, null, new String[] {}, Table.COLUMN_LAST_USED + " DESC");
            }
            if (!ListenerUtil.mutListener.listen(4381)) {
                {
                    long _loopCounter66 = 0;
                    // fixme add a limit on the original query instead of falling out of the loop?
                    while ((ListenerUtil.mutListener.listen(4380) ? ((ListenerUtil.mutListener.listen(4374) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext())) || (ListenerUtil.mutListener.listen(4379) ? (cursor.getPosition() >= limit) : (ListenerUtil.mutListener.listen(4378) ? (cursor.getPosition() <= limit) : (ListenerUtil.mutListener.listen(4377) ? (cursor.getPosition() > limit) : (ListenerUtil.mutListener.listen(4376) ? (cursor.getPosition() != limit) : (ListenerUtil.mutListener.listen(4375) ? (cursor.getPosition() == limit) : (cursor.getPosition() < limit))))))) : ((ListenerUtil.mutListener.listen(4374) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext())) && (ListenerUtil.mutListener.listen(4379) ? (cursor.getPosition() >= limit) : (ListenerUtil.mutListener.listen(4378) ? (cursor.getPosition() <= limit) : (ListenerUtil.mutListener.listen(4377) ? (cursor.getPosition() > limit) : (ListenerUtil.mutListener.listen(4376) ? (cursor.getPosition() != limit) : (ListenerUtil.mutListener.listen(4375) ? (cursor.getPosition() == limit) : (cursor.getPosition() < limit))))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter66", ++_loopCounter66);
                        if (!ListenerUtil.mutListener.listen(4373)) {
                            items.add(fromCursor(cursor).getQuery());
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4370)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(4369)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4371)) {
                db.release();
            }
        }
        return items;
    }

    /**
     * It creates an Recent Searches object from data stored in the SQLite DB by using cursor
     * @param cursor
     * @return RecentSearch object
     */
    @NonNull
    RecentSearch fromCursor(Cursor cursor) {
        // Hardcoding column positions!
        return new RecentSearch(RecentSearchesContentProvider.uriForId(cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ID))), cursor.getString(cursor.getColumnIndex(Table.COLUMN_NAME)), new Date(cursor.getLong(cursor.getColumnIndex(Table.COLUMN_LAST_USED))));
    }

    /**
     * This class contains the database table architechture for recent searches,
     * It also contains queries and logic necessary to the create, update, delete this table.
     */
    private ContentValues toContentValues(RecentSearch recentSearch) {
        ContentValues cv = new ContentValues();
        if (!ListenerUtil.mutListener.listen(4382)) {
            cv.put(RecentSearchesDao.Table.COLUMN_NAME, recentSearch.getQuery());
        }
        if (!ListenerUtil.mutListener.listen(4383)) {
            cv.put(RecentSearchesDao.Table.COLUMN_LAST_USED, recentSearch.getLastSearched().getTime());
        }
        return cv;
    }

    /**
     * This class contains the database table architechture for recent searches,
     * It also contains queries and logic necessary to the create, update, delete this table.
     */
    public static class Table {

        public static final String TABLE_NAME = "recent_searches";

        public static final String COLUMN_ID = "_id";

        static final String COLUMN_NAME = "name";

        static final String COLUMN_LAST_USED = "last_used";

        // NOTE! KEEP IN SAME ORDER AS THEY ARE DEFINED UP THERE. HELPS HARD CODE COLUMN INDICES.
        public static final String[] ALL_FIELDS = { COLUMN_ID, COLUMN_NAME, COLUMN_LAST_USED };

        static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        static final String CREATE_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " STRING," + COLUMN_LAST_USED + " INTEGER" + ");";

        /**
         * This method creates a RecentSearchesTable in SQLiteDatabase
         * @param db SQLiteDatabase
         */
        public static void onCreate(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(4384)) {
                db.execSQL(CREATE_TABLE_STATEMENT);
            }
        }

        /**
         * This method deletes RecentSearchesTable from SQLiteDatabase
         * @param db SQLiteDatabase
         */
        public static void onDelete(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(4385)) {
                db.execSQL(DROP_TABLE_STATEMENT);
            }
            if (!ListenerUtil.mutListener.listen(4386)) {
                onCreate(db);
            }
        }

        /**
         * This method is called on migrating from a older version to a newer version
         * @param db SQLiteDatabase
         * @param from Version from which we are migrating
         * @param to Version to which we are migrating
         */
        public static void onUpdate(SQLiteDatabase db, int from, int to) {
            if (!ListenerUtil.mutListener.listen(4392)) {
                if ((ListenerUtil.mutListener.listen(4391) ? (from >= to) : (ListenerUtil.mutListener.listen(4390) ? (from <= to) : (ListenerUtil.mutListener.listen(4389) ? (from > to) : (ListenerUtil.mutListener.listen(4388) ? (from < to) : (ListenerUtil.mutListener.listen(4387) ? (from != to) : (from == to))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4400)) {
                if ((ListenerUtil.mutListener.listen(4397) ? (from >= 6) : (ListenerUtil.mutListener.listen(4396) ? (from <= 6) : (ListenerUtil.mutListener.listen(4395) ? (from > 6) : (ListenerUtil.mutListener.listen(4394) ? (from != 6) : (ListenerUtil.mutListener.listen(4393) ? (from == 6) : (from < 6))))))) {
                    if (!ListenerUtil.mutListener.listen(4398)) {
                        // doesn't exist yet
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4399)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4409)) {
                if ((ListenerUtil.mutListener.listen(4405) ? (from >= 6) : (ListenerUtil.mutListener.listen(4404) ? (from <= 6) : (ListenerUtil.mutListener.listen(4403) ? (from > 6) : (ListenerUtil.mutListener.listen(4402) ? (from < 6) : (ListenerUtil.mutListener.listen(4401) ? (from != 6) : (from == 6))))))) {
                    if (!ListenerUtil.mutListener.listen(4406)) {
                        // table added in version 7
                        onCreate(db);
                    }
                    if (!ListenerUtil.mutListener.listen(4407)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4408)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4417)) {
                if ((ListenerUtil.mutListener.listen(4414) ? (from >= 7) : (ListenerUtil.mutListener.listen(4413) ? (from <= 7) : (ListenerUtil.mutListener.listen(4412) ? (from > 7) : (ListenerUtil.mutListener.listen(4411) ? (from < 7) : (ListenerUtil.mutListener.listen(4410) ? (from != 7) : (from == 7))))))) {
                    if (!ListenerUtil.mutListener.listen(4415)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4416)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
        }
    }
}
