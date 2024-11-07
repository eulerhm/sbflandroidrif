package org.wordpress.android.datasets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import org.wordpress.android.models.ReaderUser;
import org.wordpress.android.models.ReaderUserIdList;
import org.wordpress.android.models.ReaderUserList;
import org.wordpress.android.util.GravatarUtils;
import org.wordpress.android.util.SqlUtils;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * stores info about the current user and liking users
 */
public class ReaderUserTable {

    protected static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1135)) {
            db.execSQL("CREATE TABLE tbl_users (" + " user_id INTEGER PRIMARY KEY," + " blog_id INTEGER DEFAULT 0," + " user_name TEXT," + " display_name TEXT COLLATE NOCASE," + " url TEXT," + " profile_url TEXT," + " avatar_url TEXT)");
        }
    }

    protected static void dropTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(1136)) {
            db.execSQL("DROP TABLE IF EXISTS tbl_users");
        }
    }

    public static void addOrUpdateUser(ReaderUser user) {
        if (!ListenerUtil.mutListener.listen(1137)) {
            if (user == null) {
                return;
            }
        }
        ReaderUserList users = new ReaderUserList();
        if (!ListenerUtil.mutListener.listen(1138)) {
            users.add(user);
        }
        if (!ListenerUtil.mutListener.listen(1139)) {
            addOrUpdateUsers(users);
        }
    }

    private static final String COLUMN_NAMES = // 1
    " user_id," + // 2
    " blog_id," + // 3
    " user_name," + // 4
    " display_name," + // 5
    " url," + // 6
    " profile_url," + // 7
    " avatar_url";

    public static void addOrUpdateUsers(ReaderUserList users) {
        if (!ListenerUtil.mutListener.listen(1141)) {
            if ((ListenerUtil.mutListener.listen(1140) ? (users == null && users.size() == 0) : (users == null || users.size() == 0))) {
                return;
            }
        }
        SQLiteDatabase db = ReaderDatabase.getWritableDb();
        if (!ListenerUtil.mutListener.listen(1142)) {
            db.beginTransaction();
        }
        SQLiteStatement stmt = db.compileStatement("INSERT OR REPLACE INTO tbl_users (" + COLUMN_NAMES + ") VALUES (?1,?2,?3,?4,?5,?6,?7)");
        try {
            if (!ListenerUtil.mutListener.listen(1153)) {
                {
                    long _loopCounter38 = 0;
                    for (ReaderUser user : users) {
                        ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                        if (!ListenerUtil.mutListener.listen(1145)) {
                            stmt.bindLong(1, user.userId);
                        }
                        if (!ListenerUtil.mutListener.listen(1146)) {
                            stmt.bindLong(2, user.blogId);
                        }
                        if (!ListenerUtil.mutListener.listen(1147)) {
                            stmt.bindString(3, user.getUserName());
                        }
                        if (!ListenerUtil.mutListener.listen(1148)) {
                            stmt.bindString(4, user.getDisplayName());
                        }
                        if (!ListenerUtil.mutListener.listen(1149)) {
                            stmt.bindString(5, user.getUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(1150)) {
                            stmt.bindString(6, user.getProfileUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(1151)) {
                            stmt.bindString(7, user.getAvatarUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(1152)) {
                            stmt.execute();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1154)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(1143)) {
                db.endTransaction();
            }
            if (!ListenerUtil.mutListener.listen(1144)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    /*
     * returns avatar urls for the passed user ids - used by post detail to show avatars for liking users
     */
    public static ArrayList<String> getAvatarUrls(ReaderUserIdList userIds, int max, int avatarSz, long wpComUserId) {
        ArrayList<String> avatars = new ArrayList<String>();
        if ((ListenerUtil.mutListener.listen(1155) ? (userIds == null && userIds.size() == 0) : (userIds == null || userIds.size() == 0))) {
            return avatars;
        }
        StringBuilder sb = new StringBuilder("SELECT user_id, avatar_url FROM tbl_users WHERE user_id IN (");
        // the current user to appear first in post detail when they like a post
        boolean containsCurrentUser = userIds.contains(wpComUserId);
        if (!ListenerUtil.mutListener.listen(1157)) {
            if (containsCurrentUser) {
                if (!ListenerUtil.mutListener.listen(1156)) {
                    sb.append(wpComUserId);
                }
            }
        }
        int numAdded = (containsCurrentUser ? 1 : 0);
        if (!ListenerUtil.mutListener.listen(1185)) {
            {
                long _loopCounter39 = 0;
                for (Long id : userIds) {
                    ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                    if (!ListenerUtil.mutListener.listen(1184)) {
                        // skip current user since we added them already
                        if ((ListenerUtil.mutListener.listen(1162) ? (id >= wpComUserId) : (ListenerUtil.mutListener.listen(1161) ? (id <= wpComUserId) : (ListenerUtil.mutListener.listen(1160) ? (id > wpComUserId) : (ListenerUtil.mutListener.listen(1159) ? (id < wpComUserId) : (ListenerUtil.mutListener.listen(1158) ? (id == wpComUserId) : (id != wpComUserId))))))) {
                            if (!ListenerUtil.mutListener.listen(1169)) {
                                if ((ListenerUtil.mutListener.listen(1167) ? (numAdded >= 0) : (ListenerUtil.mutListener.listen(1166) ? (numAdded <= 0) : (ListenerUtil.mutListener.listen(1165) ? (numAdded < 0) : (ListenerUtil.mutListener.listen(1164) ? (numAdded != 0) : (ListenerUtil.mutListener.listen(1163) ? (numAdded == 0) : (numAdded > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(1168)) {
                                        sb.append(",");
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1170)) {
                                sb.append(id);
                            }
                            if (!ListenerUtil.mutListener.listen(1171)) {
                                numAdded++;
                            }
                            if (!ListenerUtil.mutListener.listen(1183)) {
                                if ((ListenerUtil.mutListener.listen(1182) ? ((ListenerUtil.mutListener.listen(1176) ? (max >= 0) : (ListenerUtil.mutListener.listen(1175) ? (max <= 0) : (ListenerUtil.mutListener.listen(1174) ? (max < 0) : (ListenerUtil.mutListener.listen(1173) ? (max != 0) : (ListenerUtil.mutListener.listen(1172) ? (max == 0) : (max > 0)))))) || (ListenerUtil.mutListener.listen(1181) ? (numAdded <= max) : (ListenerUtil.mutListener.listen(1180) ? (numAdded > max) : (ListenerUtil.mutListener.listen(1179) ? (numAdded < max) : (ListenerUtil.mutListener.listen(1178) ? (numAdded != max) : (ListenerUtil.mutListener.listen(1177) ? (numAdded == max) : (numAdded >= max))))))) : ((ListenerUtil.mutListener.listen(1176) ? (max >= 0) : (ListenerUtil.mutListener.listen(1175) ? (max <= 0) : (ListenerUtil.mutListener.listen(1174) ? (max < 0) : (ListenerUtil.mutListener.listen(1173) ? (max != 0) : (ListenerUtil.mutListener.listen(1172) ? (max == 0) : (max > 0)))))) && (ListenerUtil.mutListener.listen(1181) ? (numAdded <= max) : (ListenerUtil.mutListener.listen(1180) ? (numAdded > max) : (ListenerUtil.mutListener.listen(1179) ? (numAdded < max) : (ListenerUtil.mutListener.listen(1178) ? (numAdded != max) : (ListenerUtil.mutListener.listen(1177) ? (numAdded == max) : (numAdded >= max))))))))) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1186)) {
            sb.append(")");
        }
        Cursor c = ReaderDatabase.getReadableDb().rawQuery(sb.toString(), null);
        try {
            if (!ListenerUtil.mutListener.listen(1197)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(1196)) {
                        {
                            long _loopCounter40 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter40", ++_loopCounter40);
                                long userId = c.getLong(0);
                                String url = GravatarUtils.fixGravatarUrl(c.getString(1), avatarSz);
                                if (!ListenerUtil.mutListener.listen(1195)) {
                                    // add current user to the top
                                    if ((ListenerUtil.mutListener.listen(1192) ? (userId >= wpComUserId) : (ListenerUtil.mutListener.listen(1191) ? (userId <= wpComUserId) : (ListenerUtil.mutListener.listen(1190) ? (userId > wpComUserId) : (ListenerUtil.mutListener.listen(1189) ? (userId < wpComUserId) : (ListenerUtil.mutListener.listen(1188) ? (userId != wpComUserId) : (userId == wpComUserId))))))) {
                                        if (!ListenerUtil.mutListener.listen(1194)) {
                                            avatars.add(0, url);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(1193)) {
                                            avatars.add(url);
                                        }
                                    }
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return avatars;
        } finally {
            if (!ListenerUtil.mutListener.listen(1187)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static ReaderUser getCurrentUser(final long wpComUserId) {
        return getUser(wpComUserId);
    }

    private static ReaderUser getUser(long userId) {
        String[] args = { Long.toString(userId) };
        Cursor c = ReaderDatabase.getReadableDb().rawQuery("SELECT * FROM tbl_users WHERE user_id=?", args);
        try {
            if (!c.moveToFirst()) {
                return null;
            }
            return getUserFromCursor(c);
        } finally {
            if (!ListenerUtil.mutListener.listen(1198)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    private static String getAvatarForUser(long userId) {
        String[] args = { Long.toString(userId) };
        return SqlUtils.stringForQuery(ReaderDatabase.getReadableDb(), "SELECT avatar_url FROM tbl_users WHERE user_id=?", args);
    }

    public static ReaderUserList getUsersWhoLikePost(long blogId, long postId, int max) {
        String[] args = { Long.toString(blogId), Long.toString(postId) };
        String sql = "SELECT * from tbl_users WHERE user_id IN " + "(SELECT user_id FROM tbl_post_likes WHERE blog_id=? AND post_id=?) ORDER BY display_name";
        if (!ListenerUtil.mutListener.listen(1205)) {
            if ((ListenerUtil.mutListener.listen(1203) ? (max >= 0) : (ListenerUtil.mutListener.listen(1202) ? (max <= 0) : (ListenerUtil.mutListener.listen(1201) ? (max < 0) : (ListenerUtil.mutListener.listen(1200) ? (max != 0) : (ListenerUtil.mutListener.listen(1199) ? (max == 0) : (max > 0))))))) {
                if (!ListenerUtil.mutListener.listen(1204)) {
                    sql += " LIMIT " + Integer.toString(max);
                }
            }
        }
        Cursor c = ReaderDatabase.getReadableDb().rawQuery(sql, args);
        try {
            ReaderUserList users = new ReaderUserList();
            if (!ListenerUtil.mutListener.listen(1209)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(1208)) {
                        {
                            long _loopCounter41 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter41", ++_loopCounter41);
                                if (!ListenerUtil.mutListener.listen(1207)) {
                                    users.add(getUserFromCursor(c));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return users;
        } finally {
            if (!ListenerUtil.mutListener.listen(1206)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static ReaderUserList getUsersWhoLikeComment(long blogId, long commentId, int max) {
        String[] args = { Long.toString(blogId), Long.toString(commentId) };
        String sql = "SELECT * from tbl_users WHERE user_id IN" + " (SELECT user_id FROM tbl_comment_likes WHERE blog_id=? AND comment_id=?)" + " ORDER BY display_name";
        if (!ListenerUtil.mutListener.listen(1216)) {
            if ((ListenerUtil.mutListener.listen(1214) ? (max >= 0) : (ListenerUtil.mutListener.listen(1213) ? (max <= 0) : (ListenerUtil.mutListener.listen(1212) ? (max < 0) : (ListenerUtil.mutListener.listen(1211) ? (max != 0) : (ListenerUtil.mutListener.listen(1210) ? (max == 0) : (max > 0))))))) {
                if (!ListenerUtil.mutListener.listen(1215)) {
                    sql += " LIMIT " + Integer.toString(max);
                }
            }
        }
        Cursor c = ReaderDatabase.getReadableDb().rawQuery(sql, args);
        try {
            ReaderUserList users = new ReaderUserList();
            if (!ListenerUtil.mutListener.listen(1220)) {
                if (c.moveToFirst()) {
                    if (!ListenerUtil.mutListener.listen(1219)) {
                        {
                            long _loopCounter42 = 0;
                            do {
                                ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                                if (!ListenerUtil.mutListener.listen(1218)) {
                                    users.add(getUserFromCursor(c));
                                }
                            } while (c.moveToNext());
                        }
                    }
                }
            }
            return users;
        } finally {
            if (!ListenerUtil.mutListener.listen(1217)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    private static ReaderUser getUserFromCursor(Cursor c) {
        ReaderUser user = new ReaderUser();
        if (!ListenerUtil.mutListener.listen(1221)) {
            user.userId = c.getLong(c.getColumnIndexOrThrow("user_id"));
        }
        if (!ListenerUtil.mutListener.listen(1222)) {
            user.blogId = c.getLong(c.getColumnIndexOrThrow("blog_id"));
        }
        if (!ListenerUtil.mutListener.listen(1223)) {
            user.setUserName(c.getString(c.getColumnIndexOrThrow("user_name")));
        }
        if (!ListenerUtil.mutListener.listen(1224)) {
            user.setDisplayName(c.getString(c.getColumnIndexOrThrow("display_name")));
        }
        if (!ListenerUtil.mutListener.listen(1225)) {
            user.setUrl(c.getString(c.getColumnIndexOrThrow("url")));
        }
        if (!ListenerUtil.mutListener.listen(1226)) {
            user.setProfileUrl(c.getString(c.getColumnIndexOrThrow("profile_url")));
        }
        if (!ListenerUtil.mutListener.listen(1227)) {
            user.setAvatarUrl(c.getString(c.getColumnIndexOrThrow("avatar_url")));
        }
        return user;
    }
}
