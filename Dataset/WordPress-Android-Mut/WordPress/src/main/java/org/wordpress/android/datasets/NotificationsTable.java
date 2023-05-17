package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.Note;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SqlUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsTable {

    private static final String NOTIFICATIONS_TABLE = "tbl_notifications";

    private static SQLiteDatabase getDb() {
        return WordPress.wpDB.getDatabase();
    }

    public static final int NOTES_TO_RETRIEVE = 200;

    private static final Pattern STAT_ATTR_PATTERN = Pattern.compile("\"type\":\"stat\"", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

    private static final Pattern REWIND_DOWNLOAD_READY_ATTR_PATTERN = Pattern.compile("\"type\":\"rewind_download_ready\"", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private static final String REWIND_DOWNLOAD_READY_ATTR_SUBSTR = "\"type\":\"rewind_download_ready\"";

    public static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(0)) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + NOTIFICATIONS_TABLE + " (" + "id INTEGER PRIMARY KEY DEFAULT 0," + "note_id TEXT," + "type TEXT," + "raw_note_data TEXT," + "timestamp INTEGER," + " UNIQUE (note_id) ON CONFLICT REPLACE" + ")");
        }
    }

    private static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1)) {
            db.execSQL("DROP TABLE IF EXISTS " + NOTIFICATIONS_TABLE);
        }
    }

    public static ArrayList<Note> getLatestNotes() {
        return getLatestNotes(NOTES_TO_RETRIEVE);
    }

    public static ArrayList<Note> getLatestNotes(int limit) {
        Cursor cursor = getDb().query(NOTIFICATIONS_TABLE, new String[] { "note_id", "raw_note_data" }, null, null, null, null, "timestamp DESC", "" + limit);
        ArrayList<Note> notes = new ArrayList<Note>();
        if (!ListenerUtil.mutListener.listen(4)) {
            {
                long _loopCounter0 = 0;
                while (cursor.moveToNext()) {
                    ListenerUtil.loopListener.listen("_loopCounter0", ++_loopCounter0);
                    String noteId = cursor.getString(0);
                    String rawNoteData = cursor.getString(1);
                    try {
                        Note note = new Note(noteId, new JSONObject(rawNoteData));
                        if (!ListenerUtil.mutListener.listen(3)) {
                            notes.add(note);
                        }
                    } catch (JSONException e) {
                        if (!ListenerUtil.mutListener.listen(2)) {
                            AppLog.e(AppLog.T.DB, "Can't parse notification with noteId:" + noteId + ", exception:" + e);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5)) {
            cursor.close();
        }
        return notes;
    }

    private static boolean putNote(Note note, boolean checkBeforeInsert) {
        String rawNote = prepareNote(note.getId(), note.getJSON().toString());
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(6)) {
            values.put("type", note.getType());
        }
        if (!ListenerUtil.mutListener.listen(7)) {
            values.put("timestamp", note.getTimestamp());
        }
        if (!ListenerUtil.mutListener.listen(8)) {
            values.put("raw_note_data", rawNote);
        }
        long result;
        if ((ListenerUtil.mutListener.listen(9) ? (checkBeforeInsert || isNoteAvailable(note.getId())) : (checkBeforeInsert && isNoteAvailable(note.getId())))) {
            // Update
            String[] args = { note.getId() };
            result = getDb().update(NOTIFICATIONS_TABLE, values, "note_id=?", args);
            return (ListenerUtil.mutListener.listen(27) ? (result >= 1) : (ListenerUtil.mutListener.listen(26) ? (result <= 1) : (ListenerUtil.mutListener.listen(25) ? (result > 1) : (ListenerUtil.mutListener.listen(24) ? (result < 1) : (ListenerUtil.mutListener.listen(23) ? (result != 1) : (result == 1))))));
        } else {
            if (!ListenerUtil.mutListener.listen(10)) {
                // insert
                values.put("note_id", note.getId());
            }
            result = getDb().insertWithOnConflict(NOTIFICATIONS_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            if (!ListenerUtil.mutListener.listen(17)) {
                if ((ListenerUtil.mutListener.listen(15) ? (result >= -1) : (ListenerUtil.mutListener.listen(14) ? (result <= -1) : (ListenerUtil.mutListener.listen(13) ? (result > -1) : (ListenerUtil.mutListener.listen(12) ? (result < -1) : (ListenerUtil.mutListener.listen(11) ? (result != -1) : (result == -1))))))) {
                    if (!ListenerUtil.mutListener.listen(16)) {
                        AppLog.e(AppLog.T.DB, "An error occurred while saving the note into the DB - note_id:" + note.getId());
                    }
                }
            }
            return (ListenerUtil.mutListener.listen(22) ? (result >= -1) : (ListenerUtil.mutListener.listen(21) ? (result <= -1) : (ListenerUtil.mutListener.listen(20) ? (result > -1) : (ListenerUtil.mutListener.listen(19) ? (result < -1) : (ListenerUtil.mutListener.listen(18) ? (result == -1) : (result != -1))))));
        }
    }

    /**
     *  PrepareNote is used as a stop gap for handling rewind_download_ready notifications. As of this comment,
     *  rewind download ready notifications have a deep link to stats and until the API changes, we are going to
     *  swap "type""type":"stat" for "type""type":"rewind_download_ready" so that the generated link sends the
     *  user to the correct location in the app. The source remains the same if this is not a rewind_download_ready note.
     *  @param noteId
     *  @param noteSrc
     *  @return
     */
    private static String prepareNote(String noteId, String noteSrc) {
        final Matcher typeMatcher = REWIND_DOWNLOAD_READY_ATTR_PATTERN.matcher(noteSrc);
        if (!ListenerUtil.mutListener.listen(30)) {
            if (typeMatcher.find()) {
                if (!ListenerUtil.mutListener.listen(28)) {
                    AppLog.d(AppLog.T.DB, "Substituting " + REWIND_DOWNLOAD_READY_ATTR_SUBSTR + " in NoteID: " + noteId);
                }
                final Matcher matcher = STAT_ATTR_PATTERN.matcher(noteSrc);
                if (!ListenerUtil.mutListener.listen(29)) {
                    noteSrc = matcher.replaceAll(REWIND_DOWNLOAD_READY_ATTR_SUBSTR);
                }
            }
        }
        return noteSrc;
    }

    public static void saveNotes(List<Note> notes, boolean clearBeforeSaving) {
        if (!ListenerUtil.mutListener.listen(31)) {
            getDb().beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(34)) {
                if (clearBeforeSaving) {
                    if (!ListenerUtil.mutListener.listen(33)) {
                        clearNotes();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(36)) {
                {
                    long _loopCounter1 = 0;
                    for (Note note : notes) {
                        ListenerUtil.loopListener.listen("_loopCounter1", ++_loopCounter1);
                        if (!ListenerUtil.mutListener.listen(35)) {
                            // No need to check if the row already exists if we've just dropped the table.
                            putNote(note, !clearBeforeSaving);
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(37)) {
                getDb().setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(32)) {
                getDb().endTransaction();
            }
        }
    }

    public static boolean saveNote(Note note) {
        if (!ListenerUtil.mutListener.listen(38)) {
            getDb().beginTransaction();
        }
        boolean saved = false;
        try {
            if (!ListenerUtil.mutListener.listen(40)) {
                saved = putNote(note, true);
            }
            if (!ListenerUtil.mutListener.listen(41)) {
                getDb().setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(39)) {
                getDb().endTransaction();
            }
        }
        return saved;
    }

    private static boolean isNoteAvailable(String noteID) {
        if (!ListenerUtil.mutListener.listen(43)) {
            if (TextUtils.isEmpty(noteID)) {
                if (!ListenerUtil.mutListener.listen(42)) {
                    AppLog.e(AppLog.T.DB, "Asking for a note with null Id. Really?" + noteID);
                }
                return false;
            }
        }
        String[] args = { noteID };
        return SqlUtils.boolForQuery(getDb(), "SELECT 1 FROM " + NOTIFICATIONS_TABLE + " WHERE note_id=?1", args);
    }

    public static Note getNoteById(String noteID) {
        if (TextUtils.isEmpty(noteID)) {
            if (!ListenerUtil.mutListener.listen(44)) {
                AppLog.e(AppLog.T.DB, "Asking for a note with null Id. Really?" + noteID);
            }
            return null;
        }
        Cursor cursor = getDb().query(NOTIFICATIONS_TABLE, new String[] { "raw_note_data" }, "note_id=" + noteID, null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                JSONObject jsonNote = new JSONObject(cursor.getString(0));
                return new Note(noteID, jsonNote);
            } else {
                if (!ListenerUtil.mutListener.listen(48)) {
                    AppLog.v(AppLog.T.DB, "No Note found in the DB with this id: " + noteID);
                }
                return null;
            }
        } catch (JSONException e) {
            if (!ListenerUtil.mutListener.listen(45)) {
                AppLog.e(AppLog.T.DB, "Can't parse JSON Note: " + e);
            }
            return null;
        } catch (CursorIndexOutOfBoundsException e) {
            if (!ListenerUtil.mutListener.listen(46)) {
                AppLog.e(AppLog.T.DB, "An error with the cursor has occurred", e);
            }
            return null;
        } finally {
            if (!ListenerUtil.mutListener.listen(47)) {
                cursor.close();
            }
        }
    }

    public static boolean deleteNoteById(String noteID) {
        if (TextUtils.isEmpty(noteID)) {
            if (!ListenerUtil.mutListener.listen(49)) {
                AppLog.e(AppLog.T.DB, "Asking to delete a note with null Id. Really?" + noteID);
            }
            return false;
        }
        if (!ListenerUtil.mutListener.listen(50)) {
            getDb().beginTransaction();
        }
        try {
            String[] args = { noteID };
            int result = getDb().delete(NOTIFICATIONS_TABLE, "note_id=?", args);
            if (!ListenerUtil.mutListener.listen(52)) {
                getDb().setTransactionSuccessful();
            }
            return (ListenerUtil.mutListener.listen(57) ? (result >= 0) : (ListenerUtil.mutListener.listen(56) ? (result <= 0) : (ListenerUtil.mutListener.listen(55) ? (result > 0) : (ListenerUtil.mutListener.listen(54) ? (result < 0) : (ListenerUtil.mutListener.listen(53) ? (result == 0) : (result != 0))))));
        } finally {
            if (!ListenerUtil.mutListener.listen(51)) {
                getDb().endTransaction();
            }
        }
    }

    private static void clearNotes() {
        if (!ListenerUtil.mutListener.listen(58)) {
            getDb().delete(NOTIFICATIONS_TABLE, null, null);
        }
    }

    /*
     * drop & recreate notifications table
     */
    public static void reset() {
        SQLiteDatabase db = getDb();
        if (!ListenerUtil.mutListener.listen(59)) {
            db.beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(61)) {
                dropTables(db);
            }
            if (!ListenerUtil.mutListener.listen(62)) {
                createTables(db);
            }
            if (!ListenerUtil.mutListener.listen(63)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(60)) {
                db.endTransaction();
            }
        }
    }
}
