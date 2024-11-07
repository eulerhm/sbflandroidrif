package fr.free.nrw.commons.bookmarks.locations;

import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import fr.free.nrw.commons.nearby.NearbyController;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.nearby.Label;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.nearby.Sitelinks;
import timber.log.Timber;
import static fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsContentProvider.BASE_URI;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BookmarkLocationsDao {

    private final Provider<ContentProviderClient> clientProvider;

    @Inject
    public BookmarkLocationsDao(@Named("bookmarksLocation") Provider<ContentProviderClient> clientProvider) {
        this.clientProvider = clientProvider;
    }

    /**
     *  Find all persisted locations bookmarks on database
     *
     * @return list of Place
     */
    @NonNull
    public List<Place> getAllBookmarksLocations() {
        List<Place> items = new ArrayList<>();
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4863)) {
                cursor = db.query(BookmarkLocationsContentProvider.BASE_URI, Table.ALL_FIELDS, null, new String[] {}, null);
            }
            if (!ListenerUtil.mutListener.listen(4866)) {
                {
                    long _loopCounter69 = 0;
                    while ((ListenerUtil.mutListener.listen(4865) ? (cursor != null || cursor.moveToNext()) : (cursor != null && cursor.moveToNext()))) {
                        ListenerUtil.loopListener.listen("_loopCounter69", ++_loopCounter69);
                        if (!ListenerUtil.mutListener.listen(4864)) {
                            items.add(fromCursor(cursor));
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4861)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(4860)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4862)) {
                db.release();
            }
        }
        return items;
    }

    /**
     * Look for a place in bookmarks table in order to insert or delete it
     *
     * @param bookmarkLocation : Place object
     * @return is Place now fav ?
     */
    public boolean updateBookmarkLocation(Place bookmarkLocation) {
        boolean bookmarkExists = findBookmarkLocation(bookmarkLocation);
        if (!ListenerUtil.mutListener.listen(4871)) {
            if (bookmarkExists) {
                if (!ListenerUtil.mutListener.listen(4869)) {
                    deleteBookmarkLocation(bookmarkLocation);
                }
                if (!ListenerUtil.mutListener.listen(4870)) {
                    NearbyController.updateMarkerLabelListBookmark(bookmarkLocation, false);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4867)) {
                    addBookmarkLocation(bookmarkLocation);
                }
                if (!ListenerUtil.mutListener.listen(4868)) {
                    NearbyController.updateMarkerLabelListBookmark(bookmarkLocation, true);
                }
            }
        }
        return !bookmarkExists;
    }

    /**
     * Add a Place to bookmarks table
     *
     * @param bookmarkLocation : Place to add
     */
    private void addBookmarkLocation(Place bookmarkLocation) {
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4873)) {
                db.insert(BASE_URI, toContentValues(bookmarkLocation));
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4872)) {
                db.release();
            }
        }
    }

    /**
     * Delete a Place from bookmarks table
     *
     * @param bookmarkLocation : Place to delete
     */
    private void deleteBookmarkLocation(Place bookmarkLocation) {
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4875)) {
                db.delete(BookmarkLocationsContentProvider.uriForName(bookmarkLocation.name), null, null);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4874)) {
                db.release();
            }
        }
    }

    /**
     * Find a Place from database based on its name
     *
     * @param bookmarkLocation : Place to find
     * @return boolean : is Place in database ?
     */
    public boolean findBookmarkLocation(Place bookmarkLocation) {
        Cursor cursor = null;
        ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(4879)) {
                cursor = db.query(BookmarkLocationsContentProvider.BASE_URI, Table.ALL_FIELDS, Table.COLUMN_NAME + "=?", new String[] { bookmarkLocation.name }, null);
            }
            if (!ListenerUtil.mutListener.listen(4881)) {
                if ((ListenerUtil.mutListener.listen(4880) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    return true;
                }
            }
        } catch (RemoteException e) {
            // This feels lazy, but to hell with checked exceptions. :)
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(4877)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(4876)) {
                        cursor.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4878)) {
                db.release();
            }
        }
        return false;
    }

    @NonNull
    Place fromCursor(final Cursor cursor) {
        final LatLng location = new LatLng(cursor.getDouble(cursor.getColumnIndex(Table.COLUMN_LAT)), cursor.getDouble(cursor.getColumnIndex(Table.COLUMN_LONG)), 1F);
        final Sitelinks.Builder builder = new Sitelinks.Builder();
        if (!ListenerUtil.mutListener.listen(4882)) {
            builder.setWikipediaLink(cursor.getString(cursor.getColumnIndex(Table.COLUMN_WIKIPEDIA_LINK)));
        }
        if (!ListenerUtil.mutListener.listen(4883)) {
            builder.setWikidataLink(cursor.getString(cursor.getColumnIndex(Table.COLUMN_WIKIDATA_LINK)));
        }
        if (!ListenerUtil.mutListener.listen(4884)) {
            builder.setCommonsLink(cursor.getString(cursor.getColumnIndex(Table.COLUMN_COMMONS_LINK)));
        }
        return new Place(cursor.getString(cursor.getColumnIndex(Table.COLUMN_LANGUAGE)), cursor.getString(cursor.getColumnIndex(Table.COLUMN_NAME)), Label.fromText((cursor.getString(cursor.getColumnIndex(Table.COLUMN_LABEL_TEXT)))), cursor.getString(cursor.getColumnIndex(Table.COLUMN_DESCRIPTION)), location, cursor.getString(cursor.getColumnIndex(Table.COLUMN_CATEGORY)), builder.build(), cursor.getString(cursor.getColumnIndex(Table.COLUMN_PIC)), Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Table.COLUMN_EXISTS))));
    }

    private ContentValues toContentValues(Place bookmarkLocation) {
        ContentValues cv = new ContentValues();
        if (!ListenerUtil.mutListener.listen(4885)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_NAME, bookmarkLocation.getName());
        }
        if (!ListenerUtil.mutListener.listen(4886)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_LANGUAGE, bookmarkLocation.getLanguage());
        }
        if (!ListenerUtil.mutListener.listen(4887)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_DESCRIPTION, bookmarkLocation.getLongDescription());
        }
        if (!ListenerUtil.mutListener.listen(4888)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_CATEGORY, bookmarkLocation.getCategory());
        }
        if (!ListenerUtil.mutListener.listen(4889)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_LABEL_TEXT, bookmarkLocation.getLabel() != null ? bookmarkLocation.getLabel().getText() : "");
        }
        if (!ListenerUtil.mutListener.listen(4890)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_LABEL_ICON, bookmarkLocation.getLabel() != null ? bookmarkLocation.getLabel().getIcon() : null);
        }
        if (!ListenerUtil.mutListener.listen(4891)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_WIKIPEDIA_LINK, bookmarkLocation.siteLinks.getWikipediaLink().toString());
        }
        if (!ListenerUtil.mutListener.listen(4892)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_WIKIDATA_LINK, bookmarkLocation.siteLinks.getWikidataLink().toString());
        }
        if (!ListenerUtil.mutListener.listen(4893)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_COMMONS_LINK, bookmarkLocation.siteLinks.getCommonsLink().toString());
        }
        if (!ListenerUtil.mutListener.listen(4894)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_LAT, bookmarkLocation.location.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(4895)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_LONG, bookmarkLocation.location.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(4896)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_PIC, bookmarkLocation.pic);
        }
        if (!ListenerUtil.mutListener.listen(4897)) {
            cv.put(BookmarkLocationsDao.Table.COLUMN_EXISTS, bookmarkLocation.exists.toString());
        }
        return cv;
    }

    public static class Table {

        public static final String TABLE_NAME = "bookmarksLocations";

        static final String COLUMN_NAME = "location_name";

        static final String COLUMN_LANGUAGE = "location_language";

        static final String COLUMN_DESCRIPTION = "location_description";

        static final String COLUMN_LAT = "location_lat";

        static final String COLUMN_LONG = "location_long";

        static final String COLUMN_CATEGORY = "location_category";

        static final String COLUMN_LABEL_TEXT = "location_label_text";

        static final String COLUMN_LABEL_ICON = "location_label_icon";

        static final String COLUMN_IMAGE_URL = "location_image_url";

        static final String COLUMN_WIKIPEDIA_LINK = "location_wikipedia_link";

        static final String COLUMN_WIKIDATA_LINK = "location_wikidata_link";

        static final String COLUMN_COMMONS_LINK = "location_commons_link";

        static final String COLUMN_PIC = "location_pic";

        static final String COLUMN_EXISTS = "location_exists";

        // NOTE! KEEP IN SAME ORDER AS THEY ARE DEFINED UP THERE. HELPS HARD CODE COLUMN INDICES.
        public static final String[] ALL_FIELDS = { COLUMN_NAME, COLUMN_LANGUAGE, COLUMN_DESCRIPTION, COLUMN_CATEGORY, COLUMN_LABEL_TEXT, COLUMN_LABEL_ICON, COLUMN_LAT, COLUMN_LONG, COLUMN_IMAGE_URL, COLUMN_WIKIPEDIA_LINK, COLUMN_WIKIDATA_LINK, COLUMN_COMMONS_LINK, COLUMN_PIC, COLUMN_EXISTS };

        static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        static final String CREATE_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NAME + " STRING PRIMARY KEY," + COLUMN_LANGUAGE + " STRING," + COLUMN_DESCRIPTION + " STRING," + COLUMN_CATEGORY + " STRING," + COLUMN_LABEL_TEXT + " STRING," + COLUMN_LABEL_ICON + " INTEGER," + COLUMN_LAT + " DOUBLE," + COLUMN_LONG + " DOUBLE," + COLUMN_IMAGE_URL + " STRING," + COLUMN_WIKIPEDIA_LINK + " STRING," + COLUMN_WIKIDATA_LINK + " STRING," + COLUMN_COMMONS_LINK + " STRING," + COLUMN_PIC + " STRING," + COLUMN_EXISTS + " STRING" + ");";

        public static void onCreate(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(4898)) {
                db.execSQL(CREATE_TABLE_STATEMENT);
            }
        }

        public static void onDelete(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(4899)) {
                db.execSQL(DROP_TABLE_STATEMENT);
            }
            if (!ListenerUtil.mutListener.listen(4900)) {
                onCreate(db);
            }
        }

        public static void onUpdate(final SQLiteDatabase db, int from, final int to) {
            if (!ListenerUtil.mutListener.listen(4901)) {
                Timber.d("bookmarksLocations db is updated from:" + from + ", to:" + to);
            }
            if (!ListenerUtil.mutListener.listen(4907)) {
                if ((ListenerUtil.mutListener.listen(4906) ? (from >= to) : (ListenerUtil.mutListener.listen(4905) ? (from <= to) : (ListenerUtil.mutListener.listen(4904) ? (from > to) : (ListenerUtil.mutListener.listen(4903) ? (from < to) : (ListenerUtil.mutListener.listen(4902) ? (from != to) : (from == to))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4915)) {
                if ((ListenerUtil.mutListener.listen(4912) ? (from >= 7) : (ListenerUtil.mutListener.listen(4911) ? (from <= 7) : (ListenerUtil.mutListener.listen(4910) ? (from > 7) : (ListenerUtil.mutListener.listen(4909) ? (from != 7) : (ListenerUtil.mutListener.listen(4908) ? (from == 7) : (from < 7))))))) {
                    if (!ListenerUtil.mutListener.listen(4913)) {
                        // doesn't exist yet
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4914)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4924)) {
                if ((ListenerUtil.mutListener.listen(4920) ? (from >= 7) : (ListenerUtil.mutListener.listen(4919) ? (from <= 7) : (ListenerUtil.mutListener.listen(4918) ? (from > 7) : (ListenerUtil.mutListener.listen(4917) ? (from < 7) : (ListenerUtil.mutListener.listen(4916) ? (from != 7) : (from == 7))))))) {
                    if (!ListenerUtil.mutListener.listen(4921)) {
                        // table added in version 8
                        onCreate(db);
                    }
                    if (!ListenerUtil.mutListener.listen(4922)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4923)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4932)) {
                if ((ListenerUtil.mutListener.listen(4929) ? (from >= 10) : (ListenerUtil.mutListener.listen(4928) ? (from <= 10) : (ListenerUtil.mutListener.listen(4927) ? (from > 10) : (ListenerUtil.mutListener.listen(4926) ? (from != 10) : (ListenerUtil.mutListener.listen(4925) ? (from == 10) : (from < 10))))))) {
                    if (!ListenerUtil.mutListener.listen(4930)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(4931)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4940)) {
                if ((ListenerUtil.mutListener.listen(4937) ? (from >= 10) : (ListenerUtil.mutListener.listen(4936) ? (from <= 10) : (ListenerUtil.mutListener.listen(4935) ? (from > 10) : (ListenerUtil.mutListener.listen(4934) ? (from < 10) : (ListenerUtil.mutListener.listen(4933) ? (from != 10) : (from == 10))))))) {
                    // We are anyways switching to room, these things won't be necessary then
                    try {
                        if (!ListenerUtil.mutListener.listen(4939)) {
                            db.execSQL("ALTER TABLE bookmarksLocations ADD COLUMN location_pic STRING;");
                        }
                    } catch (SQLiteException exception) {
                        if (!ListenerUtil.mutListener.listen(4938)) {
                            // 
                            Timber.e(exception);
                        }
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(4948)) {
                if ((ListenerUtil.mutListener.listen(4945) ? (from <= 12) : (ListenerUtil.mutListener.listen(4944) ? (from > 12) : (ListenerUtil.mutListener.listen(4943) ? (from < 12) : (ListenerUtil.mutListener.listen(4942) ? (from != 12) : (ListenerUtil.mutListener.listen(4941) ? (from == 12) : (from >= 12))))))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(4947)) {
                            db.execSQL("ALTER TABLE bookmarksLocations ADD COLUMN location_destroyed STRING;");
                        }
                    } catch (SQLiteException exception) {
                        if (!ListenerUtil.mutListener.listen(4946)) {
                            Timber.e(exception);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4956)) {
                if ((ListenerUtil.mutListener.listen(4953) ? (from <= 13) : (ListenerUtil.mutListener.listen(4952) ? (from > 13) : (ListenerUtil.mutListener.listen(4951) ? (from < 13) : (ListenerUtil.mutListener.listen(4950) ? (from != 13) : (ListenerUtil.mutListener.listen(4949) ? (from == 13) : (from >= 13))))))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(4955)) {
                            db.execSQL("ALTER TABLE bookmarksLocations ADD COLUMN location_language STRING;");
                        }
                    } catch (SQLiteException exception) {
                        if (!ListenerUtil.mutListener.listen(4954)) {
                            Timber.e(exception);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4964)) {
                if ((ListenerUtil.mutListener.listen(4961) ? (from <= 14) : (ListenerUtil.mutListener.listen(4960) ? (from > 14) : (ListenerUtil.mutListener.listen(4959) ? (from < 14) : (ListenerUtil.mutListener.listen(4958) ? (from != 14) : (ListenerUtil.mutListener.listen(4957) ? (from == 14) : (from >= 14))))))) {
                    try {
                        if (!ListenerUtil.mutListener.listen(4963)) {
                            db.execSQL("ALTER TABLE bookmarksLocations ADD COLUMN location_exists STRING;");
                        }
                    } catch (SQLiteException exception) {
                        if (!ListenerUtil.mutListener.listen(4962)) {
                            Timber.e(exception);
                        }
                    }
                }
            }
        }
    }
}
