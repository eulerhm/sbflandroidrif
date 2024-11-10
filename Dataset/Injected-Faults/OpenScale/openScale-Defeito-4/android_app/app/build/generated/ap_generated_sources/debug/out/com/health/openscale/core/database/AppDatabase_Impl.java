package com.health.openscale.core.database;

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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ScaleMeasurementDAO _scaleMeasurementDAO;

  private volatile ScaleUserDAO _scaleUserDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `scaleMeasurements` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `enabled` INTEGER NOT NULL, `datetime` INTEGER, `weight` REAL NOT NULL, `fat` REAL NOT NULL, `water` REAL NOT NULL, `muscle` REAL NOT NULL, `visceralFat` REAL NOT NULL, `lbm` REAL NOT NULL, `waist` REAL NOT NULL, `hip` REAL NOT NULL, `bone` REAL NOT NULL, `chest` REAL NOT NULL, `thigh` REAL NOT NULL, `biceps` REAL NOT NULL, `neck` REAL NOT NULL, `caliper1` REAL NOT NULL, `caliper2` REAL NOT NULL, `caliper3` REAL NOT NULL, `calories` REAL NOT NULL, `comment` TEXT, FOREIGN KEY(`userId`) REFERENCES `scaleUsers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_scaleMeasurements_userId_datetime` ON `scaleMeasurements` (`userId`, `datetime`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `scaleUsers` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `birthday` INTEGER NOT NULL, `bodyHeight` REAL NOT NULL, `scaleUnit` INTEGER NOT NULL, `gender` INTEGER NOT NULL, `initialWeight` REAL NOT NULL, `goalWeight` REAL NOT NULL, `goalDate` INTEGER, `measureUnit` INTEGER NOT NULL, `activityLevel` INTEGER NOT NULL, `assistedWeighing` INTEGER NOT NULL, `leftAmputationLevel` INTEGER NOT NULL, `rightAmputationLevel` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd66fc1fc2752b2d6f41700fa2102492a')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `scaleMeasurements`");
        _db.execSQL("DROP TABLE IF EXISTS `scaleUsers`");
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
        _db.execSQL("PRAGMA foreign_keys = ON");
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
        final HashMap<String, TableInfo.Column> _columnsScaleMeasurements = new HashMap<String, TableInfo.Column>(22);
        _columnsScaleMeasurements.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("enabled", new TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("datetime", new TableInfo.Column("datetime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("fat", new TableInfo.Column("fat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("water", new TableInfo.Column("water", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("muscle", new TableInfo.Column("muscle", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("visceralFat", new TableInfo.Column("visceralFat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("lbm", new TableInfo.Column("lbm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("waist", new TableInfo.Column("waist", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("hip", new TableInfo.Column("hip", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("bone", new TableInfo.Column("bone", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("chest", new TableInfo.Column("chest", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("thigh", new TableInfo.Column("thigh", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("biceps", new TableInfo.Column("biceps", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("neck", new TableInfo.Column("neck", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("caliper1", new TableInfo.Column("caliper1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("caliper2", new TableInfo.Column("caliper2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("caliper3", new TableInfo.Column("caliper3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleMeasurements.put("comment", new TableInfo.Column("comment", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScaleMeasurements = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysScaleMeasurements.add(new TableInfo.ForeignKey("scaleUsers", "CASCADE", "NO ACTION",Arrays.asList("userId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesScaleMeasurements = new HashSet<TableInfo.Index>(1);
        _indicesScaleMeasurements.add(new TableInfo.Index("index_scaleMeasurements_userId_datetime", true, Arrays.asList("userId","datetime")));
        final TableInfo _infoScaleMeasurements = new TableInfo("scaleMeasurements", _columnsScaleMeasurements, _foreignKeysScaleMeasurements, _indicesScaleMeasurements);
        final TableInfo _existingScaleMeasurements = TableInfo.read(_db, "scaleMeasurements");
        if (! _infoScaleMeasurements.equals(_existingScaleMeasurements)) {
          return new RoomOpenHelper.ValidationResult(false, "scaleMeasurements(com.health.openscale.core.datatypes.ScaleMeasurement).\n"
                  + " Expected:\n" + _infoScaleMeasurements + "\n"
                  + " Found:\n" + _existingScaleMeasurements);
        }
        final HashMap<String, TableInfo.Column> _columnsScaleUsers = new HashMap<String, TableInfo.Column>(14);
        _columnsScaleUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("birthday", new TableInfo.Column("birthday", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("bodyHeight", new TableInfo.Column("bodyHeight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("scaleUnit", new TableInfo.Column("scaleUnit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("gender", new TableInfo.Column("gender", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("initialWeight", new TableInfo.Column("initialWeight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("goalWeight", new TableInfo.Column("goalWeight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("goalDate", new TableInfo.Column("goalDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("measureUnit", new TableInfo.Column("measureUnit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("activityLevel", new TableInfo.Column("activityLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("assistedWeighing", new TableInfo.Column("assistedWeighing", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("leftAmputationLevel", new TableInfo.Column("leftAmputationLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScaleUsers.put("rightAmputationLevel", new TableInfo.Column("rightAmputationLevel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScaleUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScaleUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoScaleUsers = new TableInfo("scaleUsers", _columnsScaleUsers, _foreignKeysScaleUsers, _indicesScaleUsers);
        final TableInfo _existingScaleUsers = TableInfo.read(_db, "scaleUsers");
        if (! _infoScaleUsers.equals(_existingScaleUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "scaleUsers(com.health.openscale.core.datatypes.ScaleUser).\n"
                  + " Expected:\n" + _infoScaleUsers + "\n"
                  + " Found:\n" + _existingScaleUsers);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d66fc1fc2752b2d6f41700fa2102492a", "037b44564338a87ee4d3aadc1ad09005");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "scaleMeasurements","scaleUsers");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `scaleMeasurements`");
      _db.execSQL("DELETE FROM `scaleUsers`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ScaleMeasurementDAO measurementDAO() {
    if (_scaleMeasurementDAO != null) {
      return _scaleMeasurementDAO;
    } else {
      synchronized(this) {
        if(_scaleMeasurementDAO == null) {
          _scaleMeasurementDAO = new ScaleMeasurementDAO_Impl(this);
        }
        return _scaleMeasurementDAO;
      }
    }
  }

  @Override
  public ScaleUserDAO userDAO() {
    if (_scaleUserDAO != null) {
      return _scaleUserDAO;
    } else {
      synchronized(this) {
        if(_scaleUserDAO == null) {
          _scaleUserDAO = new ScaleUserDAO_Impl(this);
        }
        return _scaleUserDAO;
      }
    }
  }
}
