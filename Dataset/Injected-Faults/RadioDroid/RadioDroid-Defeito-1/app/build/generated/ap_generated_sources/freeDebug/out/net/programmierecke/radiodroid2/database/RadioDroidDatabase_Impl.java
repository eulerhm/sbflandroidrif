package net.programmierecke.radiodroid2.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import net.programmierecke.radiodroid2.history.TrackHistoryDao;
import net.programmierecke.radiodroid2.history.TrackHistoryDao_Impl;

@SuppressWarnings({"unchecked", "deprecation"})
public final class RadioDroidDatabase_Impl extends RadioDroidDatabase {
  private volatile TrackHistoryDao _trackHistoryDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `track_history` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `station_uuid` TEXT NOT NULL, `station_icon_url` TEXT NOT NULL, `track` TEXT NOT NULL, `artist` TEXT NOT NULL, `title` TEXT NOT NULL, `art_url` TEXT, `start_time` INTEGER NOT NULL, `end_time` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6fc4d5378833de9178dc24caf82bba53')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `track_history`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTrackHistory = new HashMap<String, TableInfo.Column>(9);
        _columnsTrackHistory.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("station_uuid", new TableInfo.Column("station_uuid", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("station_icon_url", new TableInfo.Column("station_icon_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("track", new TableInfo.Column("track", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("artist", new TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("art_url", new TableInfo.Column("art_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackHistory.put("end_time", new TableInfo.Column("end_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrackHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrackHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrackHistory = new TableInfo("track_history", _columnsTrackHistory, _foreignKeysTrackHistory, _indicesTrackHistory);
        final TableInfo _existingTrackHistory = TableInfo.read(_db, "track_history");
        if (! _infoTrackHistory.equals(_existingTrackHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "track_history(net.programmierecke.radiodroid2.history.TrackHistoryEntry).\n"
                  + " Expected:\n" + _infoTrackHistory + "\n"
                  + " Found:\n" + _existingTrackHistory);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6fc4d5378833de9178dc24caf82bba53", "65206c6bd3fd68e01610355640f8704d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "track_history");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `track_history`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TrackHistoryDao songHistoryDao() {
    if (_trackHistoryDao != null) {
      return _trackHistoryDao;
    } else {
      synchronized(this) {
        if(_trackHistoryDao == null) {
          _trackHistoryDao = new TrackHistoryDao_Impl(this);
        }
        return _trackHistoryDao;
      }
    }
  }
}
