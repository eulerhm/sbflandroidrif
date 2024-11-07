package fr.free.nrw.commons.bookmarks.pictures;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import fr.free.nrw.commons.bookmarks.models.Bookmark;
import static fr.free.nrw.commons.bookmarks.pictures.BookmarkPicturesContentProvider.BASE_URI;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Singleton
public class BookmarkPicturesDao {

    private final Provider<ContentProviderClient> clientProvider;

    @Inject
    public BookmarkPicturesDao(@Named("bookmarks") Provider<ContentProviderClient> clientProvider) {
        this.clientProvider = clientProvider;
    }

    /**
     * Find all persisted pictures bookmarks on database
     *
     * @return list of bookmarks
     */
    @NonNull
    public List<Bookmark> getAllBookmarks() {
        List<Bookmark> items = new ArrayList<>();
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4743)) {
                cursor = db.query(BookmarkPicturesContentProvider.BASE_URI, Table.ALL_FIELDS, null, new String[] {}, null);
            }
            if (!ListenerUtil.mutListener.listen(4746)) {
                {
                    long _loopCounter68 = 0;
                    while ((ListenerUtil.mutListener.listen(4745) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext()))) {
                        ListenerUtil.loopListener.listen("_loopCounter68", ++_loopCounter68);
                        if (!ListenerUtil.mutListener.listen(4744)) {
                            items.add(fromCursor(cursor));
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4741)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(4740)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4742)) {
                db.release();
            }
        }
        return items;
    }

    /**
     * Look for a bookmark in database and in order to insert or delete it
     *
     * @param bookmark : Bookmark object
     * @return boolean : is bookmark now fav ?
     */
    public boolean updateBookmark(Bookmark bookmark) {
        boolean bookmarkExists = findBookmark(bookmark);
        if (!ListenerUtil.mutListener.listen(4749)) {
            if (bookmarkExists) {
                if (!ListenerUtil.mutListener.listen(4748)) {
                    deleteBookmark(bookmark);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4747)) {
                    addBookmark(bookmark);
                }
            }
        }
        return !bookmarkExists;
    }

    /**
     * Add a Bookmark to database
     *
     * @param bookmark : Bookmark to add
     */
    private void addBookmark(Bookmark bookmark) {
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4751)) {
                db.insert(BASE_URI, toContentValues(bookmark));
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4750)) {
                db.release();
            }
        }
    }

    /**
     * Delete a bookmark from database
     *
     * @param bookmark : Bookmark to delete
     */
    private void deleteBookmark(Bookmark bookmark) {
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4754)) {
                if (bookmark.getContentUri() == null) {
                    throw new RuntimeException("tried to delete item with no content URI");
                } else {
                    if (!ListenerUtil.mutListener.listen(4753)) {
                        db.delete(bookmark.getContentUri(), null, null);
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4752)) {
                db.release();
            }
        }
    }

    /**
     * Find a bookmark from database based on its name
     *
     * @param bookmark : Bookmark to find
     * @return boolean : is bookmark in database ?
     */
    public boolean findBookmark(Bookmark bookmark) {
        if (!ListenerUtil.mutListener.listen(4755)) {
            if (bookmark == null) {
                // Avoiding NPE's
                return false;
            }
        }
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4759)) {
                cursor = db.query(BookmarkPicturesContentProvider.BASE_URI, Table.ALL_FIELDS, Table.COLUMN_MEDIA_NAME + "=?", new String[] { bookmark.getMediaName() }, null);
            }
            if (!ListenerUtil.mutListener.listen(4761)) {
                if ((ListenerUtil.mutListener.listen(4760) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    return true;
                }
            }
        } catch (RemoteException e) {
            // This feels lazy, but to hell with checked exceptions. :)
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4757)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(4756)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4758)) {
                db.release();
            }
        }
        return false;
    }

    @NonNull
    Bookmark fromCursor(Cursor cursor) {
        String fileName = cursor.getString(cursor.getColumnIndex(Table.COLUMN_MEDIA_NAME));
        return new Bookmark(fileName, cursor.getString(cursor.getColumnIndex(Table.COLUMN_CREATOR)), BookmarkPicturesContentProvider.uriForName(fileName));
    }

    private ContentValues toContentValues(Bookmark bookmark) {
        ContentValues cv = new ContentValues();
        if (!ListenerUtil.mutListener.listen(4762)) {
            cv.put(BookmarkPicturesDao.Table.COLUMN_MEDIA_NAME, bookmark.getMediaName());
        }
        if (!ListenerUtil.mutListener.listen(4763)) {
            cv.put(BookmarkPicturesDao.Table.COLUMN_CREATOR, bookmark.getMediaCreator());
        }
        return cv;
    }

    public static class Table {

        public static final String TABLE_NAME = "bookmarks";

        public static final String COLUMN_MEDIA_NAME = "media_name";

        public static final String COLUMN_CREATOR = "media_creator";

        // NOTE! KEEP IN SAME ORDER AS THEY ARE DEFINED UP THERE. HELPS HARD CODE COLUMN INDICES.
        public static final String[] ALL_FIELDS = { COLUMN_MEDIA_NAME, COLUMN_CREATOR };

        public static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_MEDIA_NAME + " STRING PRIMARY KEY," + COLUMN_CREATOR + " STRING" + ");";

        public static void onCreate(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(4764)) {
                db.execSQL(CREATE_TABLE_STATEMENT);
            }
        }

        public static void onDelete(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(4765)) {
                db.execSQL(DROP_TABLE_STATEMENT);
            }
            if (!ListenerUtil.mutListener.listen(4766)) {
                onCreate(db);
            }
        }

        public static void onUpdate(SQLiteDatabase db, int from, int to) {
            if (!ListenerUtil.mutListener.listen(4772)) {
                if ((ListenerUtil.mutListener.listen(4771) ? (from >= to) : (ListenerUtil.mutListener.listen(4770) ? (from <= to) : (ListenerUtil.mutListener.listen(4769) ? (from > to) : (ListenerUtil.mutListener.listen(4768) ? (from < to) : (ListenerUtil.mutListener.listen(4767) ? (from != to) : (from == to))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4780)) {
                if ((ListenerUtil.mutListener.listen(4777) ? (from >= 7) : (ListenerUtil.mutListener.listen(4776) ? (from <= 7) : (ListenerUtil.mutListener.listen(4775) ? (from > 7) : (ListenerUtil.mutListener.listen(4774) ? (from != 7) : (ListenerUtil.mutListener.listen(4773) ? (from == 7) : (from < 7))))))) {
                    if (!ListenerUtil.mutListener.listen(4778)) {
                        // doesn't exist yet
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4779)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4789)) {
                if ((ListenerUtil.mutListener.listen(4785) ? (from >= 7) : (ListenerUtil.mutListener.listen(4784) ? (from <= 7) : (ListenerUtil.mutListener.listen(4783) ? (from > 7) : (ListenerUtil.mutListener.listen(4782) ? (from < 7) : (ListenerUtil.mutListener.listen(4781) ? (from != 7) : (from == 7))))))) {
                    if (!ListenerUtil.mutListener.listen(4786)) {
                        // table added in version 8
                        onCreate(db);
                    }
                    if (!ListenerUtil.mutListener.listen(4787)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4788)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4797)) {
                if ((ListenerUtil.mutListener.listen(4794) ? (from >= 8) : (ListenerUtil.mutListener.listen(4793) ? (from <= 8) : (ListenerUtil.mutListener.listen(4792) ? (from > 8) : (ListenerUtil.mutListener.listen(4791) ? (from < 8) : (ListenerUtil.mutListener.listen(4790) ? (from != 8) : (from == 8))))))) {
                    if (!ListenerUtil.mutListener.listen(4795)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4796)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
        }
    }
}
