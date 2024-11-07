package fr.free.nrw.commons.category;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class CategoryDao {

    private final Provider<ContentProviderClient> clientProvider;

    @Inject
    public CategoryDao(@Named("category") Provider<ContentProviderClient> clientProvider) {
        this.clientProvider = clientProvider;
    }

    public void save(Category category) {
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(431)) {
                if (category.getContentUri() == null) {
                    if (!ListenerUtil.mutListener.listen(430)) {
                        category.setContentUri(db.insert(CategoryContentProvider.BASE_URI, toContentValues(category)));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(429)) {
                        db.update(category.getContentUri(), toContentValues(category), null, null);
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(428)) {
                db.release();
            }
        }
    }

    /**
     * Find persisted category in database, based on its name.
     *
     * @param name Category's name
     * @return category from database, or null if not found
     */
    @Nullable
    Category find(String name) {
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(435)) {
                cursor = db.query(CategoryContentProvider.BASE_URI, Table.ALL_FIELDS, Table.COLUMN_NAME + "=?", new String[] { name }, null);
            }
            if (!ListenerUtil.mutListener.listen(437)) {
                if ((ListenerUtil.mutListener.listen(436) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    return fromCursor(cursor);
                }
            }
        } catch (RemoteException e) {
            // This feels lazy, but to hell with checked exceptions. :)
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(433)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(432)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(434)) {
                db.release();
            }
        }
        return null;
    }

    /**
     * Retrieve recently-used categories, ordered by descending date.
     *
     * @return a list containing recent categories
     */
    @NonNull
    List<CategoryItem> recentCategories(int limit) {
        List<CategoryItem> items = new ArrayList<>();
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(441)) {
                cursor = db.query(CategoryContentProvider.BASE_URI, Table.ALL_FIELDS, null, new String[] {}, Table.COLUMN_LAST_USED + " DESC");
            }
            if (!ListenerUtil.mutListener.listen(451)) {
                {
                    long _loopCounter10 = 0;
                    // fixme add a limit on the original query instead of falling out of the loop?
                    while ((ListenerUtil.mutListener.listen(450) ? ((ListenerUtil.mutListener.listen(444) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext())) || (ListenerUtil.mutListener.listen(449) ? (cursor.getPosition() >= limit) : (ListenerUtil.mutListener.listen(448) ? (cursor.getPosition() <= limit) : (ListenerUtil.mutListener.listen(447) ? (cursor.getPosition() > limit) : (ListenerUtil.mutListener.listen(446) ? (cursor.getPosition() != limit) : (ListenerUtil.mutListener.listen(445) ? (cursor.getPosition() == limit) : (cursor.getPosition() < limit))))))) : ((ListenerUtil.mutListener.listen(444) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext())) && (ListenerUtil.mutListener.listen(449) ? (cursor.getPosition() >= limit) : (ListenerUtil.mutListener.listen(448) ? (cursor.getPosition() <= limit) : (ListenerUtil.mutListener.listen(447) ? (cursor.getPosition() > limit) : (ListenerUtil.mutListener.listen(446) ? (cursor.getPosition() != limit) : (ListenerUtil.mutListener.listen(445) ? (cursor.getPosition() == limit) : (cursor.getPosition() < limit))))))))) {
                        ListenerUtil.loopListener.listen("_loopCounter10", ++_loopCounter10);
                        if (!ListenerUtil.mutListener.listen(443)) {
                            if (fromCursor(cursor).getName() != null) {
                                if (!ListenerUtil.mutListener.listen(442)) {
                                    items.add(new CategoryItem(fromCursor(cursor).getName(), fromCursor(cursor).getDescription(), fromCursor(cursor).getThumbnail(), false));
                                }
                            }
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(439)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(438)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(440)) {
                db.release();
            }
        }
        return items;
    }

    @NonNull
    Category fromCursor(Cursor cursor) {
        // Hardcoding column positions!
        return new Category(CategoryContentProvider.uriForId(cursor.getInt(cursor.getColumnIndex(Table.COLUMN_ID))), cursor.getString(cursor.getColumnIndex(Table.COLUMN_NAME)), cursor.getString(cursor.getColumnIndex(Table.COLUMN_DESCRIPTION)), cursor.getString(cursor.getColumnIndex(Table.COLUMN_THUMBNAIL)), new Date(cursor.getLong(cursor.getColumnIndex(Table.COLUMN_LAST_USED))), cursor.getInt(cursor.getColumnIndex(Table.COLUMN_TIMES_USED)));
    }

    private ContentValues toContentValues(Category category) {
        ContentValues cv = new ContentValues();
        if (!ListenerUtil.mutListener.listen(452)) {
            cv.put(CategoryDao.Table.COLUMN_NAME, category.getName());
        }
        if (!ListenerUtil.mutListener.listen(453)) {
            cv.put(Table.COLUMN_DESCRIPTION, category.getDescription());
        }
        if (!ListenerUtil.mutListener.listen(454)) {
            cv.put(Table.COLUMN_THUMBNAIL, category.getThumbnail());
        }
        if (!ListenerUtil.mutListener.listen(455)) {
            cv.put(CategoryDao.Table.COLUMN_LAST_USED, category.getLastUsed().getTime());
        }
        if (!ListenerUtil.mutListener.listen(456)) {
            cv.put(CategoryDao.Table.COLUMN_TIMES_USED, category.getTimesUsed());
        }
        return cv;
    }

    public static class Table {

        public static final String TABLE_NAME = "categories";

        public static final String COLUMN_ID = "_id";

        static final String COLUMN_NAME = "name";

        static final String COLUMN_DESCRIPTION = "description";

        static final String COLUMN_THUMBNAIL = "thumbnail";

        static final String COLUMN_LAST_USED = "last_used";

        static final String COLUMN_TIMES_USED = "times_used";

        // NOTE! KEEP IN SAME ORDER AS THEY ARE DEFINED UP THERE. HELPS HARD CODE COLUMN INDICES.
        public static final String[] ALL_FIELDS = { COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_THUMBNAIL, COLUMN_LAST_USED, COLUMN_TIMES_USED };

        static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        static final String CREATE_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " STRING," + COLUMN_DESCRIPTION + " STRING," + COLUMN_THUMBNAIL + " STRING," + COLUMN_LAST_USED + " INTEGER," + COLUMN_TIMES_USED + " INTEGER" + ");";

        public static void onCreate(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(457)) {
                db.execSQL(CREATE_TABLE_STATEMENT);
            }
        }

        public static void onDelete(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(458)) {
                db.execSQL(DROP_TABLE_STATEMENT);
            }
            if (!ListenerUtil.mutListener.listen(459)) {
                onCreate(db);
            }
        }

        public static void onUpdate(SQLiteDatabase db, int from, int to) {
            if (!ListenerUtil.mutListener.listen(465)) {
                if ((ListenerUtil.mutListener.listen(464) ? (from >= to) : (ListenerUtil.mutListener.listen(463) ? (from <= to) : (ListenerUtil.mutListener.listen(462) ? (from > to) : (ListenerUtil.mutListener.listen(461) ? (from < to) : (ListenerUtil.mutListener.listen(460) ? (from != to) : (from == to))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(473)) {
                if ((ListenerUtil.mutListener.listen(470) ? (from >= 4) : (ListenerUtil.mutListener.listen(469) ? (from <= 4) : (ListenerUtil.mutListener.listen(468) ? (from > 4) : (ListenerUtil.mutListener.listen(467) ? (from != 4) : (ListenerUtil.mutListener.listen(466) ? (from == 4) : (from < 4))))))) {
                    if (!ListenerUtil.mutListener.listen(471)) {
                        // doesn't exist yet
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(472)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(482)) {
                if ((ListenerUtil.mutListener.listen(478) ? (from >= 4) : (ListenerUtil.mutListener.listen(477) ? (from <= 4) : (ListenerUtil.mutListener.listen(476) ? (from > 4) : (ListenerUtil.mutListener.listen(475) ? (from < 4) : (ListenerUtil.mutListener.listen(474) ? (from != 4) : (from == 4))))))) {
                    if (!ListenerUtil.mutListener.listen(479)) {
                        // table added in version 5
                        onCreate(db);
                    }
                    if (!ListenerUtil.mutListener.listen(480)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(481)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(490)) {
                if ((ListenerUtil.mutListener.listen(487) ? (from >= 5) : (ListenerUtil.mutListener.listen(486) ? (from <= 5) : (ListenerUtil.mutListener.listen(485) ? (from > 5) : (ListenerUtil.mutListener.listen(484) ? (from < 5) : (ListenerUtil.mutListener.listen(483) ? (from != 5) : (from == 5))))))) {
                    if (!ListenerUtil.mutListener.listen(488)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(489)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(500)) {
                if ((ListenerUtil.mutListener.listen(495) ? (from >= 17) : (ListenerUtil.mutListener.listen(494) ? (from <= 17) : (ListenerUtil.mutListener.listen(493) ? (from > 17) : (ListenerUtil.mutListener.listen(492) ? (from < 17) : (ListenerUtil.mutListener.listen(491) ? (from != 17) : (from == 17))))))) {
                    if (!ListenerUtil.mutListener.listen(496)) {
                        db.execSQL("ALTER TABLE categories ADD COLUMN description STRING;");
                    }
                    if (!ListenerUtil.mutListener.listen(497)) {
                        db.execSQL("ALTER TABLE categories ADD COLUMN thumbnail STRING;");
                    }
                    if (!ListenerUtil.mutListener.listen(498)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(499)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
        }
    }
}
