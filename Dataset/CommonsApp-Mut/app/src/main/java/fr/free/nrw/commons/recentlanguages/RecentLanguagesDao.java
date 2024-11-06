package fr.free.nrw.commons.recentlanguages;

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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Handles database operations for recently used languages
 */
@Singleton
public class RecentLanguagesDao {

    private final Provider<ContentProviderClient> clientProvider;

    @Inject
    public RecentLanguagesDao(@Named("recent_languages") final Provider<ContentProviderClient> clientProvider) {
        this.clientProvider = clientProvider;
    }

    /**
     * Find all persisted recently used languages on database
     * @return list of recently used languages
     */
    public List<Language> getRecentLanguages() {
        final List<Language> languages = new ArrayList<>();
        final ContentProviderClient db = clientProvider.get();
        try (final Cursor cursor = db.query(RecentLanguagesContentProvider.BASE_URI, RecentLanguagesDao.Table.ALL_FIELDS, null, new String[] {}, null)) {
            if (!ListenerUtil.mutListener.listen(5669)) {
                if ((ListenerUtil.mutListener.listen(5666) ? (cursor != null || cursor.moveToLast()) : (cursor != null && cursor.moveToLast()))) {
                    if (!ListenerUtil.mutListener.listen(5668)) {
                        {
                            long _loopCounter79 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter79", ++_loopCounter79);
                                if (!ListenerUtil.mutListener.listen(5667)) {
                                    languages.add(fromCursor(cursor));
                                }
                            } while (cursor.moveToPrevious());
                        }
                    }
                }
            }
        } catch (final RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(5665)) {
                db.release();
            }
        }
        return languages;
    }

    /**
     * Add a Language to database
     * @param language : Language to add
     */
    public void addRecentLanguage(final Language language) {
        final ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(5671)) {
                db.insert(RecentLanguagesContentProvider.BASE_URI, toContentValues(language));
            }
        } catch (final RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(5670)) {
                db.release();
            }
        }
    }

    /**
     * Delete a language from database
     * @param languageCode : code of the Language to delete
     */
    public void deleteRecentLanguage(final String languageCode) {
        final ContentProviderClient db = clientProvider.get();
        try {
            if (!ListenerUtil.mutListener.listen(5673)) {
                db.delete(RecentLanguagesContentProvider.uriForCode(languageCode), null, null);
            }
        } catch (final RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(5672)) {
                db.release();
            }
        }
    }

    /**
     * Find a language from database based on its name
     * @param languageCode : code of the Language to find
     * @return boolean : is language in database ?
     */
    public boolean findRecentLanguage(final String languageCode) {
        if (!ListenerUtil.mutListener.listen(5674)) {
            if (languageCode == null) {
                // Avoiding NPE's
                return false;
            }
        }
        final ContentProviderClient db = clientProvider.get();
        try (final Cursor cursor = db.query(RecentLanguagesContentProvider.BASE_URI, RecentLanguagesDao.Table.ALL_FIELDS, Table.COLUMN_CODE + "=?", new String[] { languageCode }, null)) {
            if (!ListenerUtil.mutListener.listen(5677)) {
                if ((ListenerUtil.mutListener.listen(5676) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    return true;
                }
            }
        } catch (final RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            if (!ListenerUtil.mutListener.listen(5675)) {
                db.release();
            }
        }
        return false;
    }

    /**
     * It creates an Recent Language object from data stored in the SQLite DB by using cursor
     * @param cursor cursor
     * @return Language object
     */
    @NonNull
    Language fromCursor(final Cursor cursor) {
        // Hardcoding column positions!
        final String languageName = cursor.getString(cursor.getColumnIndex(Table.COLUMN_NAME));
        final String languageCode = cursor.getString(cursor.getColumnIndex(Table.COLUMN_CODE));
        return new Language(languageName, languageCode);
    }

    /**
     * Takes data from Language and create a content value object
     * @param recentLanguage recently used language
     * @return ContentValues
     */
    private ContentValues toContentValues(final Language recentLanguage) {
        final ContentValues cv = new ContentValues();
        if (!ListenerUtil.mutListener.listen(5678)) {
            cv.put(Table.COLUMN_NAME, recentLanguage.getLanguageName());
        }
        if (!ListenerUtil.mutListener.listen(5679)) {
            cv.put(Table.COLUMN_CODE, recentLanguage.getLanguageCode());
        }
        return cv;
    }

    /**
     * This class contains the database table architecture for recently used languages,
     * It also contains queries and logic necessary to the create, update, delete this table.
     */
    public static final class Table {

        public static final String TABLE_NAME = "recent_languages";

        static final String COLUMN_NAME = "language_name";

        static final String COLUMN_CODE = "language_code";

        // NOTE! KEEP IN SAME ORDER AS THEY ARE DEFINED UP THERE. HELPS HARD CODE COLUMN INDICES.
        public static final String[] ALL_FIELDS = { COLUMN_NAME, COLUMN_CODE };

        static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;

        static final String CREATE_TABLE_STATEMENT = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_NAME + " STRING," + COLUMN_CODE + " STRING PRIMARY KEY" + ");";

        /**
         * This method creates a LanguagesTable in SQLiteDatabase
         * @param db SQLiteDatabase
         */
        public static void onCreate(final SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(5680)) {
                db.execSQL(CREATE_TABLE_STATEMENT);
            }
        }

        /**
         * This method deletes LanguagesTable from SQLiteDatabase
         * @param db SQLiteDatabase
         */
        public static void onDelete(final SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(5681)) {
                db.execSQL(DROP_TABLE_STATEMENT);
            }
            if (!ListenerUtil.mutListener.listen(5682)) {
                onCreate(db);
            }
        }

        /**
         * This method is called on migrating from a older version to a newer version
         * @param db SQLiteDatabase
         * @param from Version from which we are migrating
         * @param to Version to which we are migrating
         */
        public static void onUpdate(final SQLiteDatabase db, int from, final int to) {
            if (!ListenerUtil.mutListener.listen(5688)) {
                if ((ListenerUtil.mutListener.listen(5687) ? (from >= to) : (ListenerUtil.mutListener.listen(5686) ? (from <= to) : (ListenerUtil.mutListener.listen(5685) ? (from > to) : (ListenerUtil.mutListener.listen(5684) ? (from < to) : (ListenerUtil.mutListener.listen(5683) ? (from != to) : (from == to))))))) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(5696)) {
                if ((ListenerUtil.mutListener.listen(5693) ? (from >= 19) : (ListenerUtil.mutListener.listen(5692) ? (from <= 19) : (ListenerUtil.mutListener.listen(5691) ? (from > 19) : (ListenerUtil.mutListener.listen(5690) ? (from != 19) : (ListenerUtil.mutListener.listen(5689) ? (from == 19) : (from < 19))))))) {
                    if (!ListenerUtil.mutListener.listen(5694)) {
                        // doesn't exist yet
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(5695)) {
                        onUpdate(db, from, to);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(5705)) {
                if ((ListenerUtil.mutListener.listen(5701) ? (from >= 19) : (ListenerUtil.mutListener.listen(5700) ? (from <= 19) : (ListenerUtil.mutListener.listen(5699) ? (from > 19) : (ListenerUtil.mutListener.listen(5698) ? (from < 19) : (ListenerUtil.mutListener.listen(5697) ? (from != 19) : (from == 19))))))) {
                    if (!ListenerUtil.mutListener.listen(5702)) {
                        // table added in version 20
                        onCreate(db);
                    }
                    if (!ListenerUtil.mutListener.listen(5703)) {
                        from++;
                    }
                    if (!ListenerUtil.mutListener.listen(5704)) {
                        onUpdate(db, from, to);
                    }
                }
            }
        }
    }
}
