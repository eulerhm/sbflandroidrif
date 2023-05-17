package org.wordpress.android.datasets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import org.wordpress.android.WordPress;
import org.wordpress.android.models.PublicizeConnection;
import org.wordpress.android.models.PublicizeConnectionList;
import org.wordpress.android.models.PublicizeService;
import org.wordpress.android.models.PublicizeServiceList;
import org.wordpress.android.util.SqlUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeTable {

    private static final String SERVICES_TABLE = "tbl_publicize_services";

    private static final String CONNECTIONS_TABLE = "tbl_publicize_connections";

    public static void createTables(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(143)) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + SERVICES_TABLE + " (" + " id TEXT NOT NULL COLLATE NOCASE," + " label TEXT NOT NULL COLLATE NOCASE," + " description TEXT NOT NULL," + " genericon TEXT NOT NULL," + " icon_url TEXT NOT NULL," + " connect_url TEXT NOT NULL," + " is_jetpack_supported INTEGER DEFAULT 0," + " is_multi_user_id_supported INTEGER DEFAULT 0," + " is_external_users_only INTEGER DEFAULT 0," + " PRIMARY KEY (id))");
        }
        if (!ListenerUtil.mutListener.listen(144)) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + CONNECTIONS_TABLE + " (" + " id INTEGER DEFAULT 0," + " site_id INTEGER DEFAULT 0," + " user_id INTEGER DEFAULT 0," + " keyring_connection_id INTEGER DEFAULT 0," + " keyring_connection_user_id INTEGER DEFAULT 0," + " is_shared INTEGER DEFAULT 0," + " service TEXT NOT NULL COLLATE NOCASE," + " label TEXT NOT NULL COLLATE NOCASE," + " external_id TEXT NOT NULL," + " external_name TEXT NOT NULL," + " external_display TEXT NOT NULL," + " external_profile_picture TEXT NOT NULL," + " refresh_url TEXT NOT NULL," + " status TEXT NOT NULL," + " PRIMARY KEY (id))");
        }
    }

    private static SQLiteDatabase getReadableDb() {
        return WordPress.wpDB.getDatabase();
    }

    private static SQLiteDatabase getWritableDb() {
        return WordPress.wpDB.getDatabase();
    }

    public static void resetServicesTable(SQLiteDatabase db) {
        if (!ListenerUtil.mutListener.listen(145)) {
            db.execSQL("DROP TABLE IF EXISTS " + SERVICES_TABLE);
        }
    }

    public static PublicizeService getService(String serviceId) {
        if (TextUtils.isEmpty(serviceId)) {
            return null;
        }
        String[] args = { serviceId };
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + SERVICES_TABLE + " WHERE id=?", args);
        try {
            if (c.moveToFirst()) {
                return getServiceFromCursor(c);
            } else {
                return null;
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(146)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static PublicizeServiceList getServiceList() {
        PublicizeServiceList serviceList = new PublicizeServiceList();
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + SERVICES_TABLE + " ORDER BY label", null);
        try {
            if (!ListenerUtil.mutListener.listen(149)) {
                {
                    long _loopCounter5 = 0;
                    while (c.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                        if (!ListenerUtil.mutListener.listen(148)) {
                            serviceList.add(getServiceFromCursor(c));
                        }
                    }
                }
            }
            return serviceList;
        } finally {
            if (!ListenerUtil.mutListener.listen(147)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static void setServiceList(final PublicizeServiceList serviceList) {
        SQLiteStatement stmt = null;
        SQLiteDatabase db = getWritableDb();
        if (!ListenerUtil.mutListener.listen(150)) {
            db.beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(153)) {
                db.delete(SERVICES_TABLE, null, null);
            }
            if (!ListenerUtil.mutListener.listen(154)) {
                stmt = db.compileStatement("INSERT INTO " + SERVICES_TABLE + // 1
                " (id," + // 2
                " label," + // 3
                " description," + // 4
                " genericon," + // 5
                " icon_url," + // 6
                " connect_url," + // 7
                " is_jetpack_supported," + // 8
                " is_multi_user_id_supported," + // 9
                " is_external_users_only)" + " VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)");
            }
            if (!ListenerUtil.mutListener.listen(165)) {
                {
                    long _loopCounter6 = 0;
                    for (PublicizeService service : serviceList) {
                        ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                        if (!ListenerUtil.mutListener.listen(155)) {
                            stmt.bindString(1, service.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(156)) {
                            stmt.bindString(2, service.getLabel());
                        }
                        if (!ListenerUtil.mutListener.listen(157)) {
                            stmt.bindString(3, service.getDescription());
                        }
                        if (!ListenerUtil.mutListener.listen(158)) {
                            stmt.bindString(4, service.getGenericon());
                        }
                        if (!ListenerUtil.mutListener.listen(159)) {
                            stmt.bindString(5, service.getIconUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(160)) {
                            stmt.bindString(6, service.getConnectUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(161)) {
                            stmt.bindLong(7, SqlUtils.boolToSql(service.isJetpackSupported()));
                        }
                        if (!ListenerUtil.mutListener.listen(162)) {
                            stmt.bindLong(8, SqlUtils.boolToSql(service.isMultiExternalUserIdSupported()));
                        }
                        if (!ListenerUtil.mutListener.listen(163)) {
                            stmt.bindLong(9, SqlUtils.boolToSql(service.isExternalUsersOnly()));
                        }
                        if (!ListenerUtil.mutListener.listen(164)) {
                            stmt.executeInsert();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(166)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(151)) {
                db.endTransaction();
            }
            if (!ListenerUtil.mutListener.listen(152)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    private static boolean getBooleanFromCursor(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return (ListenerUtil.mutListener.listen(172) ? ((ListenerUtil.mutListener.listen(171) ? (columnIndex >= -1) : (ListenerUtil.mutListener.listen(170) ? (columnIndex <= -1) : (ListenerUtil.mutListener.listen(169) ? (columnIndex > -1) : (ListenerUtil.mutListener.listen(168) ? (columnIndex < -1) : (ListenerUtil.mutListener.listen(167) ? (columnIndex == -1) : (columnIndex != -1)))))) || cursor.getInt(columnIndex) != 0) : ((ListenerUtil.mutListener.listen(171) ? (columnIndex >= -1) : (ListenerUtil.mutListener.listen(170) ? (columnIndex <= -1) : (ListenerUtil.mutListener.listen(169) ? (columnIndex > -1) : (ListenerUtil.mutListener.listen(168) ? (columnIndex < -1) : (ListenerUtil.mutListener.listen(167) ? (columnIndex == -1) : (columnIndex != -1)))))) && cursor.getInt(columnIndex) != 0));
    }

    private static PublicizeService getServiceFromCursor(Cursor c) {
        PublicizeService service = new PublicizeService();
        if (!ListenerUtil.mutListener.listen(173)) {
            service.setId(c.getString(c.getColumnIndexOrThrow("id")));
        }
        if (!ListenerUtil.mutListener.listen(174)) {
            service.setLabel(c.getString(c.getColumnIndexOrThrow("label")));
        }
        if (!ListenerUtil.mutListener.listen(175)) {
            service.setDescription(c.getString(c.getColumnIndexOrThrow("description")));
        }
        if (!ListenerUtil.mutListener.listen(176)) {
            service.setGenericon(c.getString(c.getColumnIndexOrThrow("genericon")));
        }
        if (!ListenerUtil.mutListener.listen(177)) {
            service.setIconUrl(c.getString(c.getColumnIndexOrThrow("icon_url")));
        }
        if (!ListenerUtil.mutListener.listen(178)) {
            service.setConnectUrl(c.getString(c.getColumnIndexOrThrow("connect_url")));
        }
        if (!ListenerUtil.mutListener.listen(179)) {
            service.setIsJetpackSupported(getBooleanFromCursor(c, "is_jetpack_supported"));
        }
        if (!ListenerUtil.mutListener.listen(180)) {
            service.setIsMultiExternalUserIdSupported(getBooleanFromCursor(c, "is_multi_user_id_supported"));
        }
        if (!ListenerUtil.mutListener.listen(181)) {
            service.setIsExternalUsersOnly(getBooleanFromCursor(c, "is_external_users_only"));
        }
        return service;
    }

    public static boolean onlyExternalConnections(String serviceId) {
        if (!ListenerUtil.mutListener.listen(183)) {
            if ((ListenerUtil.mutListener.listen(182) ? (serviceId == null || serviceId.isEmpty()) : (serviceId == null && serviceId.isEmpty()))) {
                return false;
            }
        }
        String sql = "SELECT is_external_users_only FROM " + SERVICES_TABLE + " WHERE id=?";
        String[] args = { serviceId };
        return SqlUtils.boolForQuery(getReadableDb(), sql, args);
    }

    public static String getConnectUrlForService(String serviceId) {
        if (!ListenerUtil.mutListener.listen(184)) {
            if (TextUtils.isEmpty(serviceId)) {
                return "";
            }
        }
        String sql = "SELECT connect_url FROM " + SERVICES_TABLE + " WHERE id=?";
        String[] args = { serviceId };
        return SqlUtils.stringForQuery(getReadableDb(), sql, args);
    }

    public static long getNumServices() {
        return SqlUtils.getRowCount(getReadableDb(), SERVICES_TABLE);
    }

    public static PublicizeConnection getConnection(int connectionId) {
        String[] args = { Integer.toString(connectionId) };
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + CONNECTIONS_TABLE + " WHERE id=?", args);
        try {
            if (c.moveToFirst()) {
                return getConnectionFromCursor(c);
            } else {
                return null;
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(185)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static String getRefreshUrlForConnection(int connectionId) {
        String sql = "SELECT refresh_url FROM " + CONNECTIONS_TABLE + " WHERE id=?";
        String[] args = { Integer.toString(connectionId) };
        return SqlUtils.stringForQuery(getReadableDb(), sql, args);
    }

    public static boolean deleteConnection(int connectionId) {
        String[] args = { Integer.toString(connectionId) };
        int numDeleted = getReadableDb().delete(CONNECTIONS_TABLE, "id=?", args);
        return (ListenerUtil.mutListener.listen(190) ? (numDeleted >= 0) : (ListenerUtil.mutListener.listen(189) ? (numDeleted <= 0) : (ListenerUtil.mutListener.listen(188) ? (numDeleted < 0) : (ListenerUtil.mutListener.listen(187) ? (numDeleted != 0) : (ListenerUtil.mutListener.listen(186) ? (numDeleted == 0) : (numDeleted > 0))))));
    }

    public static void addOrUpdateConnection(PublicizeConnection connection) {
        if (!ListenerUtil.mutListener.listen(191)) {
            if (connection == null) {
                return;
            }
        }
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(192)) {
            values.put("id", connection.connectionId);
        }
        if (!ListenerUtil.mutListener.listen(193)) {
            values.put("site_id", connection.siteId);
        }
        if (!ListenerUtil.mutListener.listen(194)) {
            values.put("user_id", connection.userId);
        }
        if (!ListenerUtil.mutListener.listen(195)) {
            values.put("keyring_connection_id", connection.keyringConnectionId);
        }
        if (!ListenerUtil.mutListener.listen(196)) {
            values.put("keyring_connection_user_id", connection.keyringConnectionUserId);
        }
        if (!ListenerUtil.mutListener.listen(197)) {
            values.put("is_shared", connection.isShared);
        }
        if (!ListenerUtil.mutListener.listen(198)) {
            values.put("service", connection.getService());
        }
        if (!ListenerUtil.mutListener.listen(199)) {
            values.put("label", connection.getLabel());
        }
        if (!ListenerUtil.mutListener.listen(200)) {
            values.put("external_id", connection.getExternalId());
        }
        if (!ListenerUtil.mutListener.listen(201)) {
            values.put("external_name", connection.getExternalName());
        }
        if (!ListenerUtil.mutListener.listen(202)) {
            values.put("external_display", connection.getExternalDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(203)) {
            values.put("external_profile_picture", connection.getExternalProfilePictureUrl());
        }
        if (!ListenerUtil.mutListener.listen(204)) {
            values.put("refresh_url", connection.getRefreshUrl());
        }
        if (!ListenerUtil.mutListener.listen(205)) {
            values.put("status", connection.getStatus());
        }
        if (!ListenerUtil.mutListener.listen(206)) {
            getReadableDb().insertWithOnConflict(CONNECTIONS_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public static PublicizeConnectionList getConnectionsForSite(long siteId) {
        PublicizeConnectionList connectionList = new PublicizeConnectionList();
        String[] args = { Long.toString(siteId) };
        Cursor c = getReadableDb().rawQuery("SELECT * FROM " + CONNECTIONS_TABLE + " WHERE site_id=?", args);
        try {
            if (!ListenerUtil.mutListener.listen(209)) {
                {
                    long _loopCounter7 = 0;
                    while (c.moveToNext()) {
                        ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                        if (!ListenerUtil.mutListener.listen(208)) {
                            connectionList.add(getConnectionFromCursor(c));
                        }
                    }
                }
            }
            return connectionList;
        } finally {
            if (!ListenerUtil.mutListener.listen(207)) {
                SqlUtils.closeCursor(c);
            }
        }
    }

    public static void setConnectionsForSite(long siteId, PublicizeConnectionList connectionList) {
        SQLiteStatement stmt = null;
        SQLiteDatabase db = getWritableDb();
        if (!ListenerUtil.mutListener.listen(210)) {
            db.beginTransaction();
        }
        try {
            if (!ListenerUtil.mutListener.listen(213)) {
                db.delete(CONNECTIONS_TABLE, "site_id=?", new String[] { Long.toString(siteId) });
            }
            if (!ListenerUtil.mutListener.listen(214)) {
                stmt = db.compileStatement("INSERT INTO " + CONNECTIONS_TABLE + // 1
                " (id," + // 2
                " site_id," + // 3
                " user_id," + // 4
                " keyring_connection_id," + // 5
                " keyring_connection_user_id," + // 6
                " is_shared," + // 7
                " service," + // 8
                " label," + // 9
                " external_id," + // 10
                " external_name," + // 11
                " external_display," + // 12
                " external_profile_picture," + // 13
                " refresh_url," + // 14
                " status)" + " VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14)");
            }
            if (!ListenerUtil.mutListener.listen(230)) {
                {
                    long _loopCounter8 = 0;
                    for (PublicizeConnection connection : connectionList) {
                        ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                        if (!ListenerUtil.mutListener.listen(215)) {
                            stmt.bindLong(1, connection.connectionId);
                        }
                        if (!ListenerUtil.mutListener.listen(216)) {
                            stmt.bindLong(2, connection.siteId);
                        }
                        if (!ListenerUtil.mutListener.listen(217)) {
                            stmt.bindLong(3, connection.userId);
                        }
                        if (!ListenerUtil.mutListener.listen(218)) {
                            stmt.bindLong(4, connection.keyringConnectionId);
                        }
                        if (!ListenerUtil.mutListener.listen(219)) {
                            stmt.bindLong(5, connection.keyringConnectionUserId);
                        }
                        if (!ListenerUtil.mutListener.listen(220)) {
                            stmt.bindLong(6, SqlUtils.boolToSql(connection.isShared));
                        }
                        if (!ListenerUtil.mutListener.listen(221)) {
                            stmt.bindString(7, connection.getService());
                        }
                        if (!ListenerUtil.mutListener.listen(222)) {
                            stmt.bindString(8, connection.getLabel());
                        }
                        if (!ListenerUtil.mutListener.listen(223)) {
                            stmt.bindString(9, connection.getExternalId());
                        }
                        if (!ListenerUtil.mutListener.listen(224)) {
                            stmt.bindString(10, connection.getExternalName());
                        }
                        if (!ListenerUtil.mutListener.listen(225)) {
                            stmt.bindString(11, connection.getExternalDisplayName());
                        }
                        if (!ListenerUtil.mutListener.listen(226)) {
                            stmt.bindString(12, connection.getExternalProfilePictureUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(227)) {
                            stmt.bindString(13, connection.getRefreshUrl());
                        }
                        if (!ListenerUtil.mutListener.listen(228)) {
                            stmt.bindString(14, connection.getStatus());
                        }
                        if (!ListenerUtil.mutListener.listen(229)) {
                            stmt.executeInsert();
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(231)) {
                db.setTransactionSuccessful();
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(211)) {
                db.endTransaction();
            }
            if (!ListenerUtil.mutListener.listen(212)) {
                SqlUtils.closeStatement(stmt);
            }
        }
    }

    private static PublicizeConnection getConnectionFromCursor(Cursor c) {
        PublicizeConnection connection = new PublicizeConnection();
        if (!ListenerUtil.mutListener.listen(232)) {
            connection.siteId = c.getLong(c.getColumnIndexOrThrow("site_id"));
        }
        if (!ListenerUtil.mutListener.listen(233)) {
            connection.connectionId = c.getInt(c.getColumnIndexOrThrow("id"));
        }
        if (!ListenerUtil.mutListener.listen(234)) {
            connection.userId = c.getInt(c.getColumnIndexOrThrow("user_id"));
        }
        if (!ListenerUtil.mutListener.listen(235)) {
            connection.keyringConnectionId = c.getInt(c.getColumnIndexOrThrow("keyring_connection_id"));
        }
        if (!ListenerUtil.mutListener.listen(236)) {
            connection.keyringConnectionUserId = c.getInt(c.getColumnIndexOrThrow("keyring_connection_user_id"));
        }
        if (!ListenerUtil.mutListener.listen(237)) {
            connection.isShared = SqlUtils.sqlToBool(c.getInt(c.getColumnIndexOrThrow("is_shared")));
        }
        if (!ListenerUtil.mutListener.listen(238)) {
            connection.setService(c.getString(c.getColumnIndexOrThrow("service")));
        }
        if (!ListenerUtil.mutListener.listen(239)) {
            connection.setLabel(c.getString(c.getColumnIndexOrThrow("label")));
        }
        if (!ListenerUtil.mutListener.listen(240)) {
            connection.setExternalId(c.getString(c.getColumnIndexOrThrow("external_id")));
        }
        if (!ListenerUtil.mutListener.listen(241)) {
            connection.setExternalName(c.getString(c.getColumnIndexOrThrow("external_name")));
        }
        if (!ListenerUtil.mutListener.listen(242)) {
            connection.setExternalDisplayName(c.getString(c.getColumnIndexOrThrow("external_display")));
        }
        if (!ListenerUtil.mutListener.listen(243)) {
            connection.setExternalProfilePictureUrl(c.getString(c.getColumnIndexOrThrow("external_profile_picture")));
        }
        if (!ListenerUtil.mutListener.listen(244)) {
            connection.setRefreshUrl(c.getString(c.getColumnIndexOrThrow("refresh_url")));
        }
        if (!ListenerUtil.mutListener.listen(245)) {
            connection.setStatus(c.getString(c.getColumnIndexOrThrow("status")));
        }
        return connection;
    }
}
