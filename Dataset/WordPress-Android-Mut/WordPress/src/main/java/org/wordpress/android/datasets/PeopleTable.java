package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import androidx.annotation.Nullable;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.Person;
import org.wordpress.android.ui.people.utils.PeopleUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.SqlUtils;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PeopleTable {

    private static final String TEAM_TABLE = "people_team";

    private static final String FOLLOWERS_TABLE = "people_followers";

    private static final String EMAIL_FOLLOWERS_TABLE = "people_email_followers";

    private static final String VIEWERS_TABLE = "people_viewers";

    private static SQLiteDatabase getReadableDb() {
        return WordPress.wpDB.getDatabase();
    }

    private static SQLiteDatabase getWritableDb() {
        return WordPress.wpDB.getDatabase();
    }

    public static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(64)) {
            db.execSQL("CREATE TABLE " + TEAM_TABLE + " (" + "person_id INTEGER DEFAULT 0," + "local_blog_id INTEGER DEFAULT 0," + "user_name TEXT," + "display_name TEXT," + "avatar_url TEXT," + "role TEXT," + "PRIMARY KEY (person_id, local_blog_id)" + ");");
        }
        if (!ListenerUtil.mutListener.listen(65)) {
            db.execSQL("CREATE TABLE " + FOLLOWERS_TABLE + " (" + "person_id INTEGER DEFAULT 0," + "local_blog_id INTEGER DEFAULT 0," + "user_name TEXT," + "display_name TEXT," + "avatar_url TEXT," + "subscribed TEXT," + "PRIMARY KEY (person_id, local_blog_id)" + ");");
        }
        if (!ListenerUtil.mutListener.listen(66)) {
            db.execSQL("CREATE TABLE " + EMAIL_FOLLOWERS_TABLE + " (" + "person_id INTEGER DEFAULT 0," + "local_blog_id INTEGER DEFAULT 0," + "display_name TEXT," + "avatar_url TEXT," + "subscribed TEXT," + "PRIMARY KEY (person_id, local_blog_id)" + ");");
        }
    }

    public static void createViewersTable(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(67)) {
            db.execSQL("CREATE TABLE " + VIEWERS_TABLE + " (" + "person_id INTEGER DEFAULT 0," + "local_blog_id INTEGER DEFAULT 0," + "user_name TEXT," + "display_name TEXT," + "avatar_url TEXT," + "PRIMARY KEY (person_id, local_blog_id)" + ");");
        }
    }

    private static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(68)) {
            // People table is not used anymore, each filter now has it's own table
            db.execSQL("DROP TABLE IF EXISTS people");
        }
        if (!ListenerUtil.mutListener.listen(69)) {
            db.execSQL("DROP TABLE IF EXISTS " + TEAM_TABLE);
        }
        if (!ListenerUtil.mutListener.listen(70)) {
            db.execSQL("DROP TABLE IF EXISTS " + FOLLOWERS_TABLE);
        }
        if (!ListenerUtil.mutListener.listen(71)) {
            db.execSQL("DROP TABLE IF EXISTS " + EMAIL_FOLLOWERS_TABLE);
        }
        if (!ListenerUtil.mutListener.listen(72)) {
            db.execSQL("DROP TABLE IF EXISTS " + VIEWERS_TABLE);
        }
    }

    public static void reset(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(73)) {
            AppLog.i(AppLog.T.PEOPLE, "resetting people table");
        }
        if (!ListenerUtil.mutListener.listen(74)) {
            dropTables(db);
        }
        if (!ListenerUtil.mutListener.listen(75)) {
            createTables(db);
        }
    }

    public static void saveUser(Person person) {
        if (!ListenerUtil.mutListener.listen(76)) {
            save(TEAM_TABLE, person, getWritableDb());
        }
    }

    private static void save(String table, Person person, SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(77)) {
            values.put("person_id", person.getPersonID());
        }
        if (!ListenerUtil.mutListener.listen(78)) {
            values.put("local_blog_id", person.getLocalTableBlogId());
        }
        if (!ListenerUtil.mutListener.listen(79)) {
            values.put("display_name", person.getDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(80)) {
            values.put("avatar_url", person.getAvatarUrl());
        }
        if (!ListenerUtil.mutListener.listen(88)) {
            switch(table) {
                case TEAM_TABLE:
                    if (!ListenerUtil.mutListener.listen(81)) {
                        values.put("user_name", person.getUsername());
                    }
                    if (!ListenerUtil.mutListener.listen(83)) {
                        if (person.getRole() != null) {
                            if (!ListenerUtil.mutListener.listen(82)) {
                                values.put("role", person.getRole());
                            }
                        }
                    }
                    break;
                case FOLLOWERS_TABLE:
                    if (!ListenerUtil.mutListener.listen(84)) {
                        values.put("user_name", person.getUsername());
                    }
                    if (!ListenerUtil.mutListener.listen(85)) {
                        values.put("subscribed", person.getSubscribed());
                    }
                    break;
                case EMAIL_FOLLOWERS_TABLE:
                    if (!ListenerUtil.mutListener.listen(86)) {
                        values.put("subscribed", person.getSubscribed());
                    }
                    break;
                case VIEWERS_TABLE:
                    if (!ListenerUtil.mutListener.listen(87)) {
                        values.put("user_name", person.getUsername());
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(89)) {
            database.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public static void saveUsers(List<Person> peopleList, int localTableBlogId, boolean isFreshList) {
        if (!ListenerUtil.mutListener.listen(90)) {
            savePeople(TEAM_TABLE, peopleList, localTableBlogId, isFreshList);
        }
    }

    public static void saveFollowers(List<Person> peopleList, int localTableBlogId, boolean isFreshList) {
        if (!ListenerUtil.mutListener.listen(91)) {
            savePeople(FOLLOWERS_TABLE, peopleList, localTableBlogId, isFreshList);
        }
    }

    public static void saveEmailFollowers(List<Person> peopleList, int localTableBlogId, boolean isFreshList) {
        if (!ListenerUtil.mutListener.listen(92)) {
            savePeople(EMAIL_FOLLOWERS_TABLE, peopleList, localTableBlogId, isFreshList);
        }
    }

    public static void saveViewers(List<Person> peopleList, int localTableBlogId, boolean isFreshList) {
        if (!ListenerUtil.mutListener.listen(93)) {
            savePeople(VIEWERS_TABLE, peopleList, localTableBlogId, isFreshList);
        }
    }

    private static void savePeople(String table, List<Person> peopleList, int localTableBlogId, boolean isFreshList) {
        if (!ListenerUtil.mutListener.listen(94)) {
            getWritableDb().beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(97)) {
                // We have a fresh list, remove the previous list of people in case it was deleted on remote
                if (isFreshList) {
                    if (!ListenerUtil.mutListener.listen(96)) {
                        PeopleTable.deletePeople(table, localTableBlogId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(99)) {
                {
                    long _loopCounter2 = 0;
                    for (Person person : peopleList) {
                        ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                        if (!ListenerUtil.mutListener.listen(98)) {
                            PeopleTable.save(table, person, getWritableDb());
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(100)) {
                getWritableDb().setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(95)) {
                getWritableDb().endTransaction();
            }
        }
    }

    public static void deletePeopleForLocalBlogId(int localTableBlogId) {
        if (!ListenerUtil.mutListener.listen(101)) {
            deletePeople(TEAM_TABLE, localTableBlogId);
        }
        if (!ListenerUtil.mutListener.listen(102)) {
            deletePeople(FOLLOWERS_TABLE, localTableBlogId);
        }
        if (!ListenerUtil.mutListener.listen(103)) {
            deletePeople(EMAIL_FOLLOWERS_TABLE, localTableBlogId);
        }
        if (!ListenerUtil.mutListener.listen(104)) {
            deletePeople(VIEWERS_TABLE, localTableBlogId);
        }
    }

    private static void deletePeople(String table, int localTableBlogId) {
        String[] args = new String[] { Integer.toString(localTableBlogId) };
        if (!ListenerUtil.mutListener.listen(105)) {
            getWritableDb().delete(table, "local_blog_id=?1", args);
        }
    }

    /**
     * In order to avoid syncing issues, this method will be called when People page is created. We only keep
     * the first page of users, so we don't show an empty screen. When fresh data is received, it'll replace
     * the existing page.
     *
     * @param localTableBlogId - the local blog id people will be deleted from
     */
    public static void deletePeopleExceptForFirstPage(int localTableBlogId) {
        int fetchLimit = PeopleUtils.FETCH_LIMIT;
        String[] tables = { TEAM_TABLE, FOLLOWERS_TABLE, EMAIL_FOLLOWERS_TABLE, VIEWERS_TABLE };
        if (!ListenerUtil.mutListener.listen(106)) {
            getWritableDb().beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(119)) {
                {
                    long _loopCounter3 = 0;
                    for (String table : tables) {
                        ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                        int size = getPeopleCountForLocalBlogId(table, localTableBlogId);
                        if (!ListenerUtil.mutListener.listen(118)) {
                            if ((ListenerUtil.mutListener.listen(112) ? (size >= fetchLimit) : (ListenerUtil.mutListener.listen(111) ? (size <= fetchLimit) : (ListenerUtil.mutListener.listen(110) ? (size < fetchLimit) : (ListenerUtil.mutListener.listen(109) ? (size != fetchLimit) : (ListenerUtil.mutListener.listen(108) ? (size == fetchLimit) : (size > fetchLimit))))))) {
                                String where = "local_blog_id=" + localTableBlogId;
                                String[] columns = { "person_id" };
                                String limit = Integer.toString((ListenerUtil.mutListener.listen(116) ? (size % fetchLimit) : (ListenerUtil.mutListener.listen(115) ? (size / fetchLimit) : (ListenerUtil.mutListener.listen(114) ? (size * fetchLimit) : (ListenerUtil.mutListener.listen(113) ? (size + fetchLimit) : (size - fetchLimit))))));
                                String orderBy;
                                if (shouldOrderAlphabetically(table)) {
                                    orderBy = "lower(display_name) DESC, lower(user_name) DESC";
                                } else {
                                    orderBy = "ROWID DESC";
                                }
                                String inQuery = SQLiteQueryBuilder.buildQueryString(false, table, columns, where, null, null, orderBy, limit);
                                String[] args = new String[] { Integer.toString(localTableBlogId) };
                                if (!ListenerUtil.mutListener.listen(117)) {
                                    getWritableDb().delete(table, "local_blog_id=?1 AND person_id IN (" + inQuery + ")", args);
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(120)) {
                getWritableDb().setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(107)) {
                getWritableDb().endTransaction();
            }
        }
    }

    public static int getUsersCountForLocalBlogId(int localTableBlogId) {
        return getPeopleCountForLocalBlogId(TEAM_TABLE, localTableBlogId);
    }

    public static int getViewersCountForLocalBlogId(int localTableBlogId) {
        return getPeopleCountForLocalBlogId(VIEWERS_TABLE, localTableBlogId);
    }

    private static int getPeopleCountForLocalBlogId(String table, int localTableBlogId) {
        String[] args = new String[] { Integer.toString(localTableBlogId) };
        String sql = "SELECT COUNT(*) FROM " + table + " WHERE local_blog_id=?";
        return SqlUtils.intForQuery(getReadableDb(), sql, args);
    }

    public static void deletePerson(long personID, int localTableBlogId, Person.PersonType personType) {
        String table = getTableForPersonType(personType);
        if (!ListenerUtil.mutListener.listen(122)) {
            if (table != null) {
                if (!ListenerUtil.mutListener.listen(121)) {
                    deletePerson(table, personID, localTableBlogId);
                }
            }
        }
    }

    private static void deletePerson(String table, long personID, int localTableBlogId) {
        String[] args = new String[] { Long.toString(personID), Integer.toString(localTableBlogId) };
        if (!ListenerUtil.mutListener.listen(123)) {
            getWritableDb().delete(table, "person_id=? AND local_blog_id=?", args);
        }
    }

    public static List<Person> getUsers(int localTableBlogId) {
        return PeopleTable.getPeople(TEAM_TABLE, localTableBlogId);
    }

    public static List<Person> getFollowers(int localTableBlogId) {
        return PeopleTable.getPeople(FOLLOWERS_TABLE, localTableBlogId);
    }

    public static List<Person> getEmailFollowers(int localTableBlogId) {
        return PeopleTable.getPeople(EMAIL_FOLLOWERS_TABLE, localTableBlogId);
    }

    public static List<Person> getViewers(int localTableBlogId) {
        return PeopleTable.getPeople(VIEWERS_TABLE, localTableBlogId);
    }

    private static List<Person> getPeople(String table, int localTableBlogId) {
        String[] args = { Integer.toString(localTableBlogId) };
        String orderBy;
        if (shouldOrderAlphabetically(table)) {
            orderBy = " ORDER BY lower(display_name), lower(user_name)";
        } else {
            // we want the server-side order for followers & viewers
            orderBy = " ORDER BY ROWID";
        }
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + table + " WHERE local_blog_id=?" + orderBy, args);
        List<Person> people = new ArrayList<>();
        try {
            if (!ListenerUtil.mutListener.listen(126)) {
                {
                    long _loopCounter4 = 0;
                    while (c.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                        Person person = getPersonFromCursor(c, table, localTableBlogId);
                        if (!ListenerUtil.mutListener.listen(125)) {
                            people.add(person);
                        }
                    }
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(124)) {
                SqlUtils.closeCursor(c);
            }
        }
        return people;
    }

    @Nullable
    public static Person getPerson(long personId, int localTableBlogId, Person.PersonType personType) {
        String table = getTableForPersonType(personType);
        if (!ListenerUtil.mutListener.listen(127)) {
            if (table != null) {
                return getPerson(table, personId, localTableBlogId);
            }
        }
        return null;
    }

    public static Person getUser(long personId, int localTableBlogId) {
        return getPerson(TEAM_TABLE, personId, localTableBlogId);
    }

    /**
     * retrieve a person
     *
     * @param table - sql table the person record is in
     * @param personId - id of a person in a particular blog
     * @param localTableBlogId - the local blog id the user belongs to
     * @return Person if found, null otherwise
     */
    private static Person getPerson(String table, long personId, int localTableBlogId) {
        String[] args = { Long.toString(personId), Integer.toString(localTableBlogId) };
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + table + " WHERE person_id=? AND local_blog_id=?", args);
        try {
            if (!c.moveToFirst()) {
                return null;
            }
            return getPersonFromCursor(c, table, localTableBlogId);
        } finally {
            if (!ListenerUtil.mutListener.listen(128)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    private static Person getPersonFromCursor(Cursor c, String table, int localTableBlogId) {
        long personId = c.getInt(c.getColumnIndexOrThrow("person_id"));
        Person person = new Person(personId, localTableBlogId);
        if (!ListenerUtil.mutListener.listen(129)) {
            person.setDisplayName(c.getString(c.getColumnIndexOrThrow("display_name")));
        }
        if (!ListenerUtil.mutListener.listen(130)) {
            person.setAvatarUrl(c.getString(c.getColumnIndexOrThrow("avatar_url")));
        }
        if (!ListenerUtil.mutListener.listen(141)) {
            switch(table) {
                case TEAM_TABLE:
                    if (!ListenerUtil.mutListener.listen(131)) {
                        person.setUsername(c.getString(c.getColumnIndexOrThrow("user_name")));
                    }
                    String role = c.getString(c.getColumnIndexOrThrow("role"));
                    if (!ListenerUtil.mutListener.listen(132)) {
                        person.setRole(role);
                    }
                    if (!ListenerUtil.mutListener.listen(133)) {
                        person.setPersonType(Person.PersonType.USER);
                    }
                    break;
                case FOLLOWERS_TABLE:
                    if (!ListenerUtil.mutListener.listen(134)) {
                        person.setUsername(c.getString(c.getColumnIndexOrThrow("user_name")));
                    }
                    if (!ListenerUtil.mutListener.listen(135)) {
                        person.setSubscribed(c.getString(c.getColumnIndexOrThrow("subscribed")));
                    }
                    if (!ListenerUtil.mutListener.listen(136)) {
                        person.setPersonType(Person.PersonType.FOLLOWER);
                    }
                    break;
                case EMAIL_FOLLOWERS_TABLE:
                    if (!ListenerUtil.mutListener.listen(137)) {
                        person.setSubscribed(c.getString(c.getColumnIndexOrThrow("subscribed")));
                    }
                    if (!ListenerUtil.mutListener.listen(138)) {
                        person.setPersonType(Person.PersonType.EMAIL_FOLLOWER);
                    }
                    break;
                case VIEWERS_TABLE:
                    if (!ListenerUtil.mutListener.listen(139)) {
                        person.setUsername(c.getString(c.getColumnIndexOrThrow("user_name")));
                    }
                    if (!ListenerUtil.mutListener.listen(140)) {
                        person.setPersonType(Person.PersonType.VIEWER);
                    }
                    break;
            }
        }
        return person;
    }

    // order is disabled for followers & viewers for now since the API is not supporting it
    private static boolean shouldOrderAlphabetically(String table) {
        return table.equals(TEAM_TABLE);
    }

    @Nullable
    private static String getTableForPersonType(Person.PersonType personType) {
        if (!ListenerUtil.mutListener.listen(142)) {
            switch(personType) {
                case USER:
                    return TEAM_TABLE;
                case FOLLOWER:
                    return FOLLOWERS_TABLE;
                case EMAIL_FOLLOWER:
                    return EMAIL_FOLLOWERS_TABLE;
                case VIEWER:
                    return VIEWERS_TABLE;
            }
        }
        return null;
    }
}
