package com.ichi2.anki;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Pair;
import com.ichi2.anki.model.WhiteboardPenColor;
import com.ichi2.libanki.Sound;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Used to store additional information besides what is stored in the deck itself.
 * <p>
 * Currently it used to store:
 * <ul>
 * <li>The languages associated with questions and answers.</li>
 * <li>The state of the whiteboard.</li>
 * <li>The cached state of the widget.</li>
 * </ul>
 */
public class MetaDB {

    /**
     * The name of the file storing the meta-db.
     */
    private static final String DATABASE_NAME = "ankidroid.db";

    /**
     * The Database Version, increase if you want updates to happen on next upgrade.
     */
    private static final int DATABASE_VERSION = 6;

    /**
     * The language refers to the question.
     */
    public static final int LANGUAGES_QA_QUESTION = 0;

    /**
     * The language refers to the answer.
     */
    public static final int LANGUAGES_QA_ANSWER = 1;

    /**
     * The language does not refer to either the question or answer.
     */
    public static final int LANGUAGES_QA_UNDEFINED = 2;

    /**
     * The pattern used to remove quotes from file names.
     */
    private static final Pattern quotePattern = Pattern.compile("[\"']");

    /**
     * The database object used by the meta-db.
     */
    private static SQLiteDatabase mMetaDb = null;

    /**
     * Remove any pairs of quotes from the given text.
     */
    private static String stripQuotes(String text) {
        Matcher matcher = quotePattern.matcher(text);
        if (!ListenerUtil.mutListener.listen(8680)) {
            text = matcher.replaceAll("");
        }
        return text;
    }

    /**
     * Open the meta-db
     */
    private static void openDB(Context context) {
        try {
            if (!ListenerUtil.mutListener.listen(8682)) {
                mMetaDb = context.openOrCreateDatabase(DATABASE_NAME, 0, null);
            }
            if (!ListenerUtil.mutListener.listen(8684)) {
                if (mMetaDb.needUpgrade(DATABASE_VERSION)) {
                    if (!ListenerUtil.mutListener.listen(8683)) {
                        mMetaDb = upgradeDB(mMetaDb, DATABASE_VERSION);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8685)) {
                Timber.v("Opening MetaDB");
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8681)) {
                Timber.e(e, "Error opening MetaDB ");
            }
        }
    }

    /**
     * Creating any table that missing and upgrading necessary tables.
     */
    private static SQLiteDatabase upgradeDB(SQLiteDatabase mMetaDb, int databaseVersion) {
        if (!ListenerUtil.mutListener.listen(8686)) {
            Timber.i("MetaDB:: Upgrading Internal Database..");
        }
        if (!ListenerUtil.mutListener.listen(8687)) {
            // if (mMetaDb.getVersion() == 0) {
            Timber.i("MetaDB:: Applying changes for version: 0");
        }
        if (!ListenerUtil.mutListener.listen(8696)) {
            if ((ListenerUtil.mutListener.listen(8692) ? (mMetaDb.getVersion() >= 4) : (ListenerUtil.mutListener.listen(8691) ? (mMetaDb.getVersion() <= 4) : (ListenerUtil.mutListener.listen(8690) ? (mMetaDb.getVersion() > 4) : (ListenerUtil.mutListener.listen(8689) ? (mMetaDb.getVersion() != 4) : (ListenerUtil.mutListener.listen(8688) ? (mMetaDb.getVersion() == 4) : (mMetaDb.getVersion() < 4))))))) {
                if (!ListenerUtil.mutListener.listen(8693)) {
                    mMetaDb.execSQL("DROP TABLE IF EXISTS languages;");
                }
                if (!ListenerUtil.mutListener.listen(8694)) {
                    mMetaDb.execSQL("DROP TABLE IF EXISTS customDictionary;");
                }
                if (!ListenerUtil.mutListener.listen(8695)) {
                    mMetaDb.execSQL("DROP TABLE IF EXISTS whiteboardState;");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8697)) {
            // Create tables if not exist
            mMetaDb.execSQL("CREATE TABLE IF NOT EXISTS languages (" + " _id INTEGER PRIMARY KEY AUTOINCREMENT, " + "did INTEGER NOT NULL, ord INTEGER, " + "qa INTEGER, " + "language TEXT)");
        }
        if (!ListenerUtil.mutListener.listen(8698)) {
            mMetaDb.execSQL("CREATE TABLE IF NOT EXISTS customDictionary (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "did INTEGER NOT NULL, " + "dictionary INTEGER)");
        }
        if (!ListenerUtil.mutListener.listen(8699)) {
            mMetaDb.execSQL("CREATE TABLE IF NOT EXISTS smallWidgetStatus (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "due INTEGER NOT NULL, eta INTEGER NOT NULL)");
        }
        if (!ListenerUtil.mutListener.listen(8700)) {
            updateWidgetStatus(mMetaDb);
        }
        if (!ListenerUtil.mutListener.listen(8701)) {
            updateWhiteboardState(mMetaDb);
        }
        if (!ListenerUtil.mutListener.listen(8702)) {
            mMetaDb.setVersion(databaseVersion);
        }
        if (!ListenerUtil.mutListener.listen(8703)) {
            Timber.i("MetaDB:: Upgrading Internal Database finished. New version: %d", databaseVersion);
        }
        return mMetaDb;
    }

    private static void updateWhiteboardState(SQLiteDatabase mMetaDb) {
        int columnCount = DatabaseUtil.getTableColumnCount(mMetaDb, "whiteboardState");
        if (!ListenerUtil.mutListener.listen(8710)) {
            if ((ListenerUtil.mutListener.listen(8708) ? (columnCount >= 0) : (ListenerUtil.mutListener.listen(8707) ? (columnCount > 0) : (ListenerUtil.mutListener.listen(8706) ? (columnCount < 0) : (ListenerUtil.mutListener.listen(8705) ? (columnCount != 0) : (ListenerUtil.mutListener.listen(8704) ? (columnCount == 0) : (columnCount <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(8709)) {
                    mMetaDb.execSQL("CREATE TABLE IF NOT EXISTS whiteboardState (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "did INTEGER NOT NULL, state INTEGER, visible INTEGER, lightpencolor INTEGER, darkpencolor INTEGER)");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8718)) {
            if ((ListenerUtil.mutListener.listen(8715) ? (columnCount >= 4) : (ListenerUtil.mutListener.listen(8714) ? (columnCount <= 4) : (ListenerUtil.mutListener.listen(8713) ? (columnCount > 4) : (ListenerUtil.mutListener.listen(8712) ? (columnCount != 4) : (ListenerUtil.mutListener.listen(8711) ? (columnCount == 4) : (columnCount < 4))))))) {
                if (!ListenerUtil.mutListener.listen(8716)) {
                    // Default to 1
                    mMetaDb.execSQL("ALTER TABLE whiteboardState ADD COLUMN visible INTEGER NOT NULL DEFAULT '1'");
                }
                if (!ListenerUtil.mutListener.listen(8717)) {
                    Timber.i("Added 'visible' column to whiteboardState");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8728)) {
            if ((ListenerUtil.mutListener.listen(8723) ? (columnCount >= 5) : (ListenerUtil.mutListener.listen(8722) ? (columnCount <= 5) : (ListenerUtil.mutListener.listen(8721) ? (columnCount > 5) : (ListenerUtil.mutListener.listen(8720) ? (columnCount != 5) : (ListenerUtil.mutListener.listen(8719) ? (columnCount == 5) : (columnCount < 5))))))) {
                if (!ListenerUtil.mutListener.listen(8724)) {
                    mMetaDb.execSQL("ALTER TABLE whiteboardState ADD COLUMN lightpencolor INTEGER DEFAULT NULL");
                }
                if (!ListenerUtil.mutListener.listen(8725)) {
                    Timber.i("Added 'lightpencolor' column to whiteboardState");
                }
                if (!ListenerUtil.mutListener.listen(8726)) {
                    mMetaDb.execSQL("ALTER TABLE whiteboardState ADD COLUMN darkpencolor INTEGER DEFAULT NULL");
                }
                if (!ListenerUtil.mutListener.listen(8727)) {
                    Timber.i("Added 'darkpencolor' column to whiteboardState");
                }
            }
        }
    }

    private static void updateWidgetStatus(SQLiteDatabase mMetaDb) {
        int columnCount = DatabaseUtil.getTableColumnCount(mMetaDb, "widgetStatus");
        if (!ListenerUtil.mutListener.listen(8743)) {
            if ((ListenerUtil.mutListener.listen(8733) ? (columnCount >= 0) : (ListenerUtil.mutListener.listen(8732) ? (columnCount <= 0) : (ListenerUtil.mutListener.listen(8731) ? (columnCount < 0) : (ListenerUtil.mutListener.listen(8730) ? (columnCount != 0) : (ListenerUtil.mutListener.listen(8729) ? (columnCount == 0) : (columnCount > 0))))))) {
                if (!ListenerUtil.mutListener.listen(8742)) {
                    if ((ListenerUtil.mutListener.listen(8739) ? (columnCount >= 7) : (ListenerUtil.mutListener.listen(8738) ? (columnCount <= 7) : (ListenerUtil.mutListener.listen(8737) ? (columnCount > 7) : (ListenerUtil.mutListener.listen(8736) ? (columnCount != 7) : (ListenerUtil.mutListener.listen(8735) ? (columnCount == 7) : (columnCount < 7))))))) {
                        if (!ListenerUtil.mutListener.listen(8740)) {
                            mMetaDb.execSQL("ALTER TABLE widgetStatus " + "ADD COLUMN eta INTEGER NOT NULL DEFAULT '0'");
                        }
                        if (!ListenerUtil.mutListener.listen(8741)) {
                            mMetaDb.execSQL("ALTER TABLE widgetStatus " + "ADD COLUMN time INTEGER NOT NULL DEFAULT '0'");
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8734)) {
                    mMetaDb.execSQL("CREATE TABLE IF NOT EXISTS widgetStatus (" + "deckId INTEGER NOT NULL PRIMARY KEY, " + "deckName TEXT NOT NULL, " + "newCards INTEGER NOT NULL, " + "lrnCards INTEGER NOT NULL, " + "dueCards INTEGER NOT NULL, " + "progress INTEGER NOT NULL, " + "eta INTEGER NOT NULL)");
                }
            }
        }
    }

    /**
     * Open the meta-db but only if it currently closed.
     */
    private static void openDBIfClosed(Context context) {
        if (!ListenerUtil.mutListener.listen(8746)) {
            if ((ListenerUtil.mutListener.listen(8744) ? (mMetaDb == null && !mMetaDb.isOpen()) : (mMetaDb == null || !mMetaDb.isOpen()))) {
                if (!ListenerUtil.mutListener.listen(8745)) {
                    openDB(context);
                }
            }
        }
    }

    /**
     * Close the meta-db.
     */
    public static void closeDB() {
        if (!ListenerUtil.mutListener.listen(8751)) {
            if ((ListenerUtil.mutListener.listen(8747) ? (mMetaDb != null || mMetaDb.isOpen()) : (mMetaDb != null && mMetaDb.isOpen()))) {
                if (!ListenerUtil.mutListener.listen(8748)) {
                    mMetaDb.close();
                }
                if (!ListenerUtil.mutListener.listen(8749)) {
                    mMetaDb = null;
                }
                if (!ListenerUtil.mutListener.listen(8750)) {
                    Timber.d("Closing MetaDB");
                }
            }
        }
    }

    /**
     * Reset the content of the meta-db, erasing all its content.
     */
    public static boolean resetDB(Context context) {
        if (!ListenerUtil.mutListener.listen(8752)) {
            openDBIfClosed(context);
        }
        try {
            if (!ListenerUtil.mutListener.listen(8754)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS languages;");
            }
            if (!ListenerUtil.mutListener.listen(8755)) {
                Timber.i("MetaDB:: Resetting all language assignment");
            }
            if (!ListenerUtil.mutListener.listen(8756)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS whiteboardState;");
            }
            if (!ListenerUtil.mutListener.listen(8757)) {
                Timber.i("MetaDB:: Resetting whiteboard state");
            }
            if (!ListenerUtil.mutListener.listen(8758)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS customDictionary;");
            }
            if (!ListenerUtil.mutListener.listen(8759)) {
                Timber.i("MetaDB:: Resetting custom Dictionary");
            }
            if (!ListenerUtil.mutListener.listen(8760)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS widgetStatus;");
            }
            if (!ListenerUtil.mutListener.listen(8761)) {
                Timber.i("MetaDB:: Resetting widget status");
            }
            if (!ListenerUtil.mutListener.listen(8762)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS smallWidgetStatus;");
            }
            if (!ListenerUtil.mutListener.listen(8763)) {
                Timber.i("MetaDB:: Resetting small widget status");
            }
            if (!ListenerUtil.mutListener.listen(8764)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS intentInformation;");
            }
            if (!ListenerUtil.mutListener.listen(8765)) {
                Timber.i("MetaDB:: Resetting intentInformation");
            }
            if (!ListenerUtil.mutListener.listen(8766)) {
                upgradeDB(mMetaDb, DATABASE_VERSION);
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8753)) {
                Timber.e(e, "Error resetting MetaDB ");
            }
        }
        return false;
    }

    /**
     * Reset the language associations for all the decks and card models.
     */
    public static boolean resetLanguages(Context context) {
        if (!ListenerUtil.mutListener.listen(8769)) {
            if ((ListenerUtil.mutListener.listen(8767) ? (mMetaDb == null && !mMetaDb.isOpen()) : (mMetaDb == null || !mMetaDb.isOpen()))) {
                if (!ListenerUtil.mutListener.listen(8768)) {
                    openDB(context);
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(8771)) {
                Timber.i("MetaDB:: Resetting all language assignments");
            }
            if (!ListenerUtil.mutListener.listen(8772)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS languages;");
            }
            if (!ListenerUtil.mutListener.listen(8773)) {
                upgradeDB(mMetaDb, DATABASE_VERSION);
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8770)) {
                Timber.e(e, "Error resetting MetaDB ");
            }
        }
        return false;
    }

    /**
     * Reset the widget status.
     */
    public static boolean resetWidget(Context context) {
        if (!ListenerUtil.mutListener.listen(8776)) {
            if ((ListenerUtil.mutListener.listen(8774) ? (mMetaDb == null && !mMetaDb.isOpen()) : (mMetaDb == null || !mMetaDb.isOpen()))) {
                if (!ListenerUtil.mutListener.listen(8775)) {
                    openDB(context);
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(8778)) {
                Timber.i("MetaDB:: Resetting widget status");
            }
            if (!ListenerUtil.mutListener.listen(8779)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS widgetStatus;");
            }
            if (!ListenerUtil.mutListener.listen(8780)) {
                mMetaDb.execSQL("DROP TABLE IF EXISTS smallWidgetStatus;");
            }
            if (!ListenerUtil.mutListener.listen(8781)) {
                upgradeDB(mMetaDb, DATABASE_VERSION);
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8777)) {
                Timber.e(e, "Error resetting widgetStatus and smallWidgetStatus");
            }
        }
        return false;
    }

    /**
     * Associates a language to a deck, model, and card model for a given type.
     *
     * @param qa the part of the card for which to store the association, {@link #LANGUAGES_QA_QUESTION},
     *            {@link #LANGUAGES_QA_ANSWER}, or {@link #LANGUAGES_QA_UNDEFINED}
     * @param language the language to associate, as a two-characters, lowercase string
     */
    public static void storeLanguage(Context context, long did, int ord, Sound.SoundSide qa, String language) {
        if (!ListenerUtil.mutListener.listen(8782)) {
            openDBIfClosed(context);
        }
        try {
            if (!ListenerUtil.mutListener.listen(8788)) {
                if ("".equals(getLanguage(context, did, ord, qa))) {
                    if (!ListenerUtil.mutListener.listen(8786)) {
                        mMetaDb.execSQL("INSERT INTO languages (did, ord, qa, language) " + " VALUES (?, ?, ?, ?);", new Object[] { did, ord, qa.getInt(), language });
                    }
                    if (!ListenerUtil.mutListener.listen(8787)) {
                        Timber.v("Store language for deck %d", did);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8784)) {
                        mMetaDb.execSQL("UPDATE languages SET language = ? WHERE did = ? AND ord = ? AND qa = ?;", new Object[] { language, did, ord, qa.getInt() });
                    }
                    if (!ListenerUtil.mutListener.listen(8785)) {
                        Timber.v("Update language for deck %d", did);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8783)) {
                Timber.e(e, "Error storing language in MetaDB ");
            }
        }
    }

    /**
     * Returns the language associated with the given deck, model and card model, for the given type.
     *
     * @param qa the part of the card for which to store the association, {@link #LANGUAGES_QA_QUESTION},
     *            {@link #LANGUAGES_QA_ANSWER}, or {@link #LANGUAGES_QA_UNDEFINED} return the language associate with
     *            the type, as a two-characters, lowercase string, or the empty string if no association is defined
     */
    public static String getLanguage(Context context, long did, int ord, Sound.SoundSide qa) {
        if (!ListenerUtil.mutListener.listen(8789)) {
            openDBIfClosed(context);
        }
        String language = "";
        String query = "SELECT language FROM languages WHERE did = ? AND ord = ? AND qa = ? LIMIT 1";
        try (Cursor cur = mMetaDb.rawQuery(query, new String[] { Long.toString(did), Integer.toString(ord), Integer.toString(qa.getInt()) })) {
            if (!ListenerUtil.mutListener.listen(8791)) {
                Timber.v("getLanguage: %s", query);
            }
            if (!ListenerUtil.mutListener.listen(8793)) {
                if (cur.moveToNext()) {
                    if (!ListenerUtil.mutListener.listen(8792)) {
                        language = cur.getString(0);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8790)) {
                Timber.e(e, "Error fetching language ");
            }
        }
        return language;
    }

    /**
     * Resets all the language associates for a given deck.
     *
     * @return whether an error occurred while resetting the language for the deck
     */
    public static boolean resetDeckLanguages(Context context, long did) {
        if (!ListenerUtil.mutListener.listen(8794)) {
            openDBIfClosed(context);
        }
        try {
            if (!ListenerUtil.mutListener.listen(8796)) {
                mMetaDb.execSQL("DELETE FROM languages WHERE did = ?;", new Long[] { did });
            }
            if (!ListenerUtil.mutListener.listen(8797)) {
                Timber.i("MetaDB:: Resetting language assignment for deck %d", did);
            }
            return true;
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8795)) {
                Timber.e(e, "Error resetting deck language");
            }
        }
        return false;
    }

    /**
     * Returns the state of the whiteboard for the given deck.
     *
     * @return 1 if the whiteboard should be shown, 0 otherwise
     */
    public static boolean getWhiteboardState(Context context, long did) {
        if (!ListenerUtil.mutListener.listen(8798)) {
            openDBIfClosed(context);
        }
        try (Cursor cur = mMetaDb.rawQuery("SELECT state FROM whiteboardState  WHERE did = ?", new String[] { Long.toString(did) })) {
            return DatabaseUtil.getScalarBoolean(cur);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8799)) {
                Timber.e(e, "Error retrieving whiteboard state from MetaDB ");
            }
            return false;
        }
    }

    /**
     * Stores the state of the whiteboard for a given deck.
     *
     * @param did deck id to store whiteboard state for
     * @param whiteboardState 1 if the whiteboard should be shown, 0 otherwise
     */
    public static void storeWhiteboardState(Context context, long did, boolean whiteboardState) {
        int state = (whiteboardState) ? 1 : 0;
        if (!ListenerUtil.mutListener.listen(8800)) {
            openDBIfClosed(context);
        }
        try (Cursor cur = mMetaDb.rawQuery("SELECT _id FROM whiteboardState WHERE did = ?", new String[] { Long.toString(did) })) {
            if (!ListenerUtil.mutListener.listen(8806)) {
                if (cur.moveToNext()) {
                    if (!ListenerUtil.mutListener.listen(8804)) {
                        mMetaDb.execSQL("UPDATE whiteboardState SET did = ?, state=? WHERE _id=?;", new Object[] { did, state, cur.getString(0) });
                    }
                    if (!ListenerUtil.mutListener.listen(8805)) {
                        Timber.d("Store whiteboard state (%d) for deck %d", state, did);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8802)) {
                        mMetaDb.execSQL("INSERT INTO whiteboardState (did, state) VALUES (?, ?)", new Object[] { did, state });
                    }
                    if (!ListenerUtil.mutListener.listen(8803)) {
                        Timber.d("Store whiteboard state (%d) for deck %d", state, did);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8801)) {
                Timber.e(e, "Error storing whiteboard state in MetaDB ");
            }
        }
    }

    /**
     * Returns the state of the whiteboard for the given deck.
     *
     * @return 1 if the whiteboard should be shown, 0 otherwise
     */
    public static boolean getWhiteboardVisibility(Context context, long did) {
        if (!ListenerUtil.mutListener.listen(8807)) {
            openDBIfClosed(context);
        }
        try (Cursor cur = mMetaDb.rawQuery("SELECT visible FROM whiteboardState WHERE did = ?", new String[] { Long.toString(did) })) {
            return DatabaseUtil.getScalarBoolean(cur);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8808)) {
                Timber.e(e, "Error retrieving whiteboard state from MetaDB ");
            }
            return false;
        }
    }

    /**
     * Stores the state of the whiteboard for a given deck.
     *
     * @param did deck id to store whiteboard state for
     * @param isVisible 1 if the whiteboard should be shown, 0 otherwise
     */
    public static void storeWhiteboardVisibility(Context context, long did, boolean isVisible) {
        int isVisibleState = (isVisible) ? 1 : 0;
        if (!ListenerUtil.mutListener.listen(8809)) {
            openDBIfClosed(context);
        }
        try (Cursor cur = mMetaDb.rawQuery("SELECT _id FROM whiteboardState WHERE did  = ?", new String[] { Long.toString(did) })) {
            if (!ListenerUtil.mutListener.listen(8815)) {
                if (cur.moveToNext()) {
                    if (!ListenerUtil.mutListener.listen(8813)) {
                        mMetaDb.execSQL("UPDATE whiteboardState SET did = ?, visible= ?  WHERE _id=?;", new Object[] { did, isVisibleState, cur.getString(0) });
                    }
                    if (!ListenerUtil.mutListener.listen(8814)) {
                        Timber.d("Store whiteboard visibility (%d) for deck %d", isVisibleState, did);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8811)) {
                        mMetaDb.execSQL("INSERT INTO whiteboardState (did, visible) VALUES (?, ?)", new Object[] { did, isVisibleState });
                    }
                    if (!ListenerUtil.mutListener.listen(8812)) {
                        Timber.d("Store whiteboard visibility (%d) for deck %d", isVisibleState, did);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8810)) {
                Timber.e(e, "Error storing whiteboard visibility in MetaDB ");
            }
        }
    }

    /**
     * Returns the pen color of the whiteboard for the given deck.
     */
    public static WhiteboardPenColor getWhiteboardPenColor(Context context, long did) {
        if (!ListenerUtil.mutListener.listen(8816)) {
            openDBIfClosed(context);
        }
        try (Cursor cur = mMetaDb.rawQuery("SELECT lightpencolor, darkpencolor FROM whiteboardState WHERE did = ?", new String[] { Long.toString(did) })) {
            if (!ListenerUtil.mutListener.listen(8818)) {
                cur.moveToFirst();
            }
            Integer light = DatabaseUtil.getInteger(cur, 0);
            Integer dark = DatabaseUtil.getInteger(cur, 1);
            return new WhiteboardPenColor(light, dark);
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8817)) {
                Timber.e(e, "Error retrieving whiteboard pen color from MetaDB ");
            }
            return WhiteboardPenColor.getDefault();
        }
    }

    /**
     * Stores the pen color of the whiteboard for a given deck.
     *
     * @param did deck id to store whiteboard state for
     * @param isLight if dark mode is disabled
     * @param value The new color code to store
     */
    public static void storeWhiteboardPenColor(Context context, long did, boolean isLight, Integer value) {
        if (!ListenerUtil.mutListener.listen(8819)) {
            openDBIfClosed(context);
        }
        String columnName = isLight ? "lightpencolor" : "darkpencolor";
        try (Cursor cur = mMetaDb.rawQuery("SELECT _id FROM whiteboardState WHERE did  = ?", new String[] { Long.toString(did) })) {
            if (!ListenerUtil.mutListener.listen(8823)) {
                if (cur.moveToNext()) {
                    if (!ListenerUtil.mutListener.listen(8822)) {
                        mMetaDb.execSQL("UPDATE whiteboardState SET did = ?, " + columnName + "= ? " + " WHERE _id=?;", new Object[] { did, value, cur.getString(0) });
                    }
                } else {
                    String sql = "INSERT INTO whiteboardState (did, " + columnName + ") VALUES (?, ?)";
                    if (!ListenerUtil.mutListener.listen(8821)) {
                        mMetaDb.execSQL(sql, new Object[] { did, value });
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8824)) {
                Timber.d("Store whiteboard %s (%d) for deck %d", columnName, value, did);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8820)) {
                Timber.w(e, "Error storing whiteboard color in MetaDB");
            }
        }
    }

    /**
     * Returns a custom dictionary associated to a deck
     *
     * @return integer number of dictionary, -1 if not set (standard dictionary will be used)
     */
    public static int getLookupDictionary(Context context, long did) {
        if (!ListenerUtil.mutListener.listen(8825)) {
            openDBIfClosed(context);
        }
        try (Cursor cur = mMetaDb.rawQuery("SELECT dictionary FROM customDictionary WHERE did = ?", new String[] { Long.toString(did) })) {
            if (cur.moveToNext()) {
                return cur.getInt(0);
            } else {
                return -1;
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8826)) {
                Timber.e(e, "Error retrieving custom dictionary from MetaDB ");
            }
            return -1;
        }
    }

    /**
     * Stores a custom dictionary for a given deck.
     *
     * @param dictionary integer number of dictionary, -1 if not set (standard dictionary will be used)
     */
    public static void storeLookupDictionary(Context context, long did, int dictionary) {
        if (!ListenerUtil.mutListener.listen(8827)) {
            openDBIfClosed(context);
        }
        try (Cursor cur = mMetaDb.rawQuery("SELECT _id FROM customDictionary WHERE did = ?", new String[] { Long.toString(did) })) {
            if (!ListenerUtil.mutListener.listen(8833)) {
                if (cur.moveToNext()) {
                    if (!ListenerUtil.mutListener.listen(8831)) {
                        mMetaDb.execSQL("UPDATE customDictionary SET did = ?, dictionary=? WHERE _id=?;", new Object[] { did, dictionary, cur.getString(0) });
                    }
                    if (!ListenerUtil.mutListener.listen(8832)) {
                        Timber.i("MetaDB:: Store custom dictionary (%d) for deck %d", dictionary, did);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8829)) {
                        mMetaDb.execSQL("INSERT INTO customDictionary (did, dictionary) VALUES (?, ?)", new Object[] { did, dictionary });
                    }
                    if (!ListenerUtil.mutListener.listen(8830)) {
                        Timber.i("MetaDB:: Store custom dictionary (%d) for deck %d", dictionary, did);
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(8828)) {
                Timber.e(e, "Error storing custom dictionary to MetaDB ");
            }
        }
    }

    /**
     * Return the current status of the widget.
     *
     * @return [due, eta]
     */
    public static int[] getWidgetSmallStatus(Context context) {
        if (!ListenerUtil.mutListener.listen(8834)) {
            openDBIfClosed(context);
        }
        Cursor cursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(8839)) {
                cursor = mMetaDb.query("smallWidgetStatus", new String[] { "due", "eta" }, null, null, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(8840)) {
                if (cursor.moveToNext()) {
                    return new int[] { cursor.getInt(0), cursor.getInt(1) };
                }
            }
        } catch (SQLiteException e) {
            if (!ListenerUtil.mutListener.listen(8835)) {
                Timber.e(e, "Error while querying widgetStatus");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(8838)) {
                if ((ListenerUtil.mutListener.listen(8836) ? (cursor != null || !cursor.isClosed()) : (cursor != null && !cursor.isClosed()))) {
                    if (!ListenerUtil.mutListener.listen(8837)) {
                        cursor.close();
                    }
                }
            }
        }
        return new int[] { 0, 0 };
    }

    public static int getNotificationStatus(Context context) {
        if (!ListenerUtil.mutListener.listen(8841)) {
            openDBIfClosed(context);
        }
        Cursor cursor = null;
        int due = 0;
        try {
            if (!ListenerUtil.mutListener.listen(8846)) {
                cursor = mMetaDb.query("smallWidgetStatus", new String[] { "due" }, null, null, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(8847)) {
                if (cursor.moveToFirst()) {
                    return cursor.getInt(0);
                }
            }
        } catch (SQLiteException e) {
            if (!ListenerUtil.mutListener.listen(8842)) {
                Timber.e(e, "Error while querying widgetStatus");
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(8845)) {
                if ((ListenerUtil.mutListener.listen(8843) ? (cursor != null || !cursor.isClosed()) : (cursor != null && !cursor.isClosed()))) {
                    if (!ListenerUtil.mutListener.listen(8844)) {
                        cursor.close();
                    }
                }
            }
        }
        return due;
    }

    public static void storeSmallWidgetStatus(Context context, Pair<Integer, Integer> status) {
        if (!ListenerUtil.mutListener.listen(8848)) {
            openDBIfClosed(context);
        }
        try {
            if (!ListenerUtil.mutListener.listen(8853)) {
                mMetaDb.beginTransaction();
            }
            try {
                if (!ListenerUtil.mutListener.listen(8855)) {
                    // First clear all the existing content.
                    mMetaDb.execSQL("DELETE FROM smallWidgetStatus");
                }
                if (!ListenerUtil.mutListener.listen(8856)) {
                    mMetaDb.execSQL("INSERT INTO smallWidgetStatus(due, eta) VALUES (?, ?)", new Object[] { status.first, status.second });
                }
                if (!ListenerUtil.mutListener.listen(8857)) {
                    mMetaDb.setTransactionSuccessful();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(8854)) {
                    mMetaDb.endTransaction();
                }
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(8849)) {
                Timber.e(e, "MetaDB.storeSmallWidgetStatus: failed");
            }
        } catch (SQLiteException e) {
            if (!ListenerUtil.mutListener.listen(8850)) {
                Timber.e(e, "MetaDB.storeSmallWidgetStatus: failed");
            }
            if (!ListenerUtil.mutListener.listen(8851)) {
                closeDB();
            }
            if (!ListenerUtil.mutListener.listen(8852)) {
                Timber.i("MetaDB:: Trying to reset Widget: %b", resetWidget(context));
            }
        }
    }

    public static void close() {
        if (!ListenerUtil.mutListener.listen(8860)) {
            if (mMetaDb != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(8859)) {
                        mMetaDb.close();
                    }
                } catch (Exception e) {
                    if (!ListenerUtil.mutListener.listen(8858)) {
                        Timber.w(e, "Failed to close MetaDB");
                    }
                }
            }
        }
    }

    private static class DatabaseUtil {

        private static boolean getScalarBoolean(Cursor cur) {
            if (cur.moveToNext()) {
                return (ListenerUtil.mutListener.listen(8865) ? (cur.getInt(0) >= 0) : (ListenerUtil.mutListener.listen(8864) ? (cur.getInt(0) <= 0) : (ListenerUtil.mutListener.listen(8863) ? (cur.getInt(0) < 0) : (ListenerUtil.mutListener.listen(8862) ? (cur.getInt(0) != 0) : (ListenerUtil.mutListener.listen(8861) ? (cur.getInt(0) == 0) : (cur.getInt(0) > 0))))));
            } else {
                return false;
            }
        }

        // API LEVEL
        @SuppressWarnings("TryFinallyCanBeTryWithResources")
        private static int getTableColumnCount(SQLiteDatabase mMetaDb, String tableName) {
            Cursor c = null;
            try {
                if (!ListenerUtil.mutListener.listen(8868)) {
                    c = mMetaDb.rawQuery("PRAGMA table_info(" + tableName + ")", null);
                }
                return c.getCount();
            } finally {
                if (!ListenerUtil.mutListener.listen(8867)) {
                    if (c != null) {
                        if (!ListenerUtil.mutListener.listen(8866)) {
                            c.close();
                        }
                    }
                }
            }
        }

        @Nullable
        public static Integer getInteger(@NonNull Cursor cur, int columnIndex) {
            return cur.isNull(columnIndex) ? null : cur.getInt(columnIndex);
        }
    }
}
