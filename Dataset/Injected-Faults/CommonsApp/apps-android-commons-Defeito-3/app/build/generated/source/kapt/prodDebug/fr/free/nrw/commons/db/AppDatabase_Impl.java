package fr.free.nrw.commons.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import fr.free.nrw.commons.contributions.ContributionDao;
import fr.free.nrw.commons.contributions.ContributionDao_Impl;
import fr.free.nrw.commons.customselector.database.NotForUploadStatusDao;
import fr.free.nrw.commons.customselector.database.NotForUploadStatusDao_Impl;
import fr.free.nrw.commons.customselector.database.UploadedStatusDao;
import fr.free.nrw.commons.customselector.database.UploadedStatusDao_Impl;
import fr.free.nrw.commons.review.ReviewDao;
import fr.free.nrw.commons.review.ReviewDao_Impl;
import fr.free.nrw.commons.upload.depicts.DepictsDao;
import fr.free.nrw.commons.upload.depicts.DepictsDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ContributionDao _contributionDao;

  private volatile DepictsDao _depictsDao;

  private volatile UploadedStatusDao _uploadedStatusDao;

  private volatile NotForUploadStatusDao _notForUploadStatusDao;

  private volatile ReviewDao _reviewDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(16) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `contribution` (`pageId` TEXT NOT NULL, `state` INTEGER NOT NULL, `transferred` INTEGER NOT NULL, `decimalCoords` TEXT, `dateCreatedSource` TEXT, `wikidataPlace` TEXT, `chunkInfo` TEXT, `depictedItems` TEXT NOT NULL, `mimeType` TEXT, `localUri` TEXT, `dataLength` INTEGER NOT NULL, `dateCreated` INTEGER, `dateCreatedString` TEXT, `dateModified` INTEGER, `hasInvalidLocation` INTEGER NOT NULL, `contentUri` TEXT, `countryCode` TEXT, `imageSHA1` TEXT, `retries` INTEGER NOT NULL, `media_pageId` TEXT NOT NULL, `media_thumbUrl` TEXT, `media_imageUrl` TEXT, `media_filename` TEXT, `media_fallbackDescription` TEXT, `media_dateUploaded` INTEGER, `media_license` TEXT, `media_licenseUrl` TEXT, `media_author` TEXT, `media_user` TEXT, `media_categories` TEXT, `media_coordinates` TEXT, `media_captions` TEXT NOT NULL, `media_descriptions` TEXT NOT NULL, `media_depictionIds` TEXT NOT NULL, `media_categoriesHiddenStatus` TEXT NOT NULL, `media_addedCategories` TEXT, PRIMARY KEY(`pageId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `depicts_table` (`item` TEXT NOT NULL, `lastUsed` INTEGER NOT NULL, PRIMARY KEY(`item`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `uploaded_table` (`imageSHA1` TEXT NOT NULL, `modifiedImageSHA1` TEXT NOT NULL, `imageResult` INTEGER NOT NULL, `modifiedImageResult` INTEGER NOT NULL, `lastUpdated` INTEGER, PRIMARY KEY(`imageSHA1`))");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_uploaded_table_modifiedImageSHA1` ON `uploaded_table` (`modifiedImageSHA1`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `images_not_for_upload_table` (`imageSHA1` TEXT NOT NULL, PRIMARY KEY(`imageSHA1`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `reviewed-images` (`imageId` TEXT NOT NULL, PRIMARY KEY(`imageId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b695c4418b419999c2371b795cceac17')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `contribution`");
        _db.execSQL("DROP TABLE IF EXISTS `depicts_table`");
        _db.execSQL("DROP TABLE IF EXISTS `uploaded_table`");
        _db.execSQL("DROP TABLE IF EXISTS `images_not_for_upload_table`");
        _db.execSQL("DROP TABLE IF EXISTS `reviewed-images`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
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
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsContribution = new HashMap<String, TableInfo.Column>(36);
        _columnsContribution.put("pageId", new TableInfo.Column("pageId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("transferred", new TableInfo.Column("transferred", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("decimalCoords", new TableInfo.Column("decimalCoords", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("dateCreatedSource", new TableInfo.Column("dateCreatedSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("wikidataPlace", new TableInfo.Column("wikidataPlace", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("chunkInfo", new TableInfo.Column("chunkInfo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("depictedItems", new TableInfo.Column("depictedItems", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("mimeType", new TableInfo.Column("mimeType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("localUri", new TableInfo.Column("localUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("dataLength", new TableInfo.Column("dataLength", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("dateCreated", new TableInfo.Column("dateCreated", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("dateCreatedString", new TableInfo.Column("dateCreatedString", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("dateModified", new TableInfo.Column("dateModified", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("hasInvalidLocation", new TableInfo.Column("hasInvalidLocation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("contentUri", new TableInfo.Column("contentUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("countryCode", new TableInfo.Column("countryCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("imageSHA1", new TableInfo.Column("imageSHA1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("retries", new TableInfo.Column("retries", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_pageId", new TableInfo.Column("media_pageId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_thumbUrl", new TableInfo.Column("media_thumbUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_imageUrl", new TableInfo.Column("media_imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_filename", new TableInfo.Column("media_filename", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_fallbackDescription", new TableInfo.Column("media_fallbackDescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_dateUploaded", new TableInfo.Column("media_dateUploaded", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_license", new TableInfo.Column("media_license", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_licenseUrl", new TableInfo.Column("media_licenseUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_author", new TableInfo.Column("media_author", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_user", new TableInfo.Column("media_user", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_categories", new TableInfo.Column("media_categories", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_coordinates", new TableInfo.Column("media_coordinates", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_captions", new TableInfo.Column("media_captions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_descriptions", new TableInfo.Column("media_descriptions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_depictionIds", new TableInfo.Column("media_depictionIds", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_categoriesHiddenStatus", new TableInfo.Column("media_categoriesHiddenStatus", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContribution.put("media_addedCategories", new TableInfo.Column("media_addedCategories", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContribution = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContribution = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContribution = new TableInfo("contribution", _columnsContribution, _foreignKeysContribution, _indicesContribution);
        final TableInfo _existingContribution = TableInfo.read(_db, "contribution");
        if (! _infoContribution.equals(_existingContribution)) {
          return new RoomOpenHelper.ValidationResult(false, "contribution(fr.free.nrw.commons.contributions.Contribution).\n"
                  + " Expected:\n" + _infoContribution + "\n"
                  + " Found:\n" + _existingContribution);
        }
        final HashMap<String, TableInfo.Column> _columnsDepictsTable = new HashMap<String, TableInfo.Column>(2);
        _columnsDepictsTable.put("item", new TableInfo.Column("item", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDepictsTable.put("lastUsed", new TableInfo.Column("lastUsed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDepictsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDepictsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDepictsTable = new TableInfo("depicts_table", _columnsDepictsTable, _foreignKeysDepictsTable, _indicesDepictsTable);
        final TableInfo _existingDepictsTable = TableInfo.read(_db, "depicts_table");
        if (! _infoDepictsTable.equals(_existingDepictsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "depicts_table(fr.free.nrw.commons.upload.depicts.Depicts).\n"
                  + " Expected:\n" + _infoDepictsTable + "\n"
                  + " Found:\n" + _existingDepictsTable);
        }
        final HashMap<String, TableInfo.Column> _columnsUploadedTable = new HashMap<String, TableInfo.Column>(5);
        _columnsUploadedTable.put("imageSHA1", new TableInfo.Column("imageSHA1", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUploadedTable.put("modifiedImageSHA1", new TableInfo.Column("modifiedImageSHA1", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUploadedTable.put("imageResult", new TableInfo.Column("imageResult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUploadedTable.put("modifiedImageResult", new TableInfo.Column("modifiedImageResult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUploadedTable.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUploadedTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUploadedTable = new HashSet<TableInfo.Index>(1);
        _indicesUploadedTable.add(new TableInfo.Index("index_uploaded_table_modifiedImageSHA1", true, Arrays.asList("modifiedImageSHA1"), Arrays.asList("ASC")));
        final TableInfo _infoUploadedTable = new TableInfo("uploaded_table", _columnsUploadedTable, _foreignKeysUploadedTable, _indicesUploadedTable);
        final TableInfo _existingUploadedTable = TableInfo.read(_db, "uploaded_table");
        if (! _infoUploadedTable.equals(_existingUploadedTable)) {
          return new RoomOpenHelper.ValidationResult(false, "uploaded_table(fr.free.nrw.commons.customselector.database.UploadedStatus).\n"
                  + " Expected:\n" + _infoUploadedTable + "\n"
                  + " Found:\n" + _existingUploadedTable);
        }
        final HashMap<String, TableInfo.Column> _columnsImagesNotForUploadTable = new HashMap<String, TableInfo.Column>(1);
        _columnsImagesNotForUploadTable.put("imageSHA1", new TableInfo.Column("imageSHA1", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysImagesNotForUploadTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesImagesNotForUploadTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoImagesNotForUploadTable = new TableInfo("images_not_for_upload_table", _columnsImagesNotForUploadTable, _foreignKeysImagesNotForUploadTable, _indicesImagesNotForUploadTable);
        final TableInfo _existingImagesNotForUploadTable = TableInfo.read(_db, "images_not_for_upload_table");
        if (! _infoImagesNotForUploadTable.equals(_existingImagesNotForUploadTable)) {
          return new RoomOpenHelper.ValidationResult(false, "images_not_for_upload_table(fr.free.nrw.commons.customselector.database.NotForUploadStatus).\n"
                  + " Expected:\n" + _infoImagesNotForUploadTable + "\n"
                  + " Found:\n" + _existingImagesNotForUploadTable);
        }
        final HashMap<String, TableInfo.Column> _columnsReviewedImages = new HashMap<String, TableInfo.Column>(1);
        _columnsReviewedImages.put("imageId", new TableInfo.Column("imageId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReviewedImages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReviewedImages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReviewedImages = new TableInfo("reviewed-images", _columnsReviewedImages, _foreignKeysReviewedImages, _indicesReviewedImages);
        final TableInfo _existingReviewedImages = TableInfo.read(_db, "reviewed-images");
        if (! _infoReviewedImages.equals(_existingReviewedImages)) {
          return new RoomOpenHelper.ValidationResult(false, "reviewed-images(fr.free.nrw.commons.review.ReviewEntity).\n"
                  + " Expected:\n" + _infoReviewedImages + "\n"
                  + " Found:\n" + _existingReviewedImages);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "b695c4418b419999c2371b795cceac17", "675958ab7438063d1c03abef9610399e");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "contribution","depicts_table","uploaded_table","images_not_for_upload_table","reviewed-images");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `contribution`");
      _db.execSQL("DELETE FROM `depicts_table`");
      _db.execSQL("DELETE FROM `uploaded_table`");
      _db.execSQL("DELETE FROM `images_not_for_upload_table`");
      _db.execSQL("DELETE FROM `reviewed-images`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ContributionDao.class, ContributionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DepictsDao.class, DepictsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UploadedStatusDao.class, UploadedStatusDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(NotForUploadStatusDao.class, NotForUploadStatusDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReviewDao.class, ReviewDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public ContributionDao contributionDao() {
    if (_contributionDao != null) {
      return _contributionDao;
    } else {
      synchronized(this) {
        if(_contributionDao == null) {
          _contributionDao = new ContributionDao_Impl(this);
        }
        return _contributionDao;
      }
    }
  }

  @Override
  public DepictsDao DepictsDao() {
    if (_depictsDao != null) {
      return _depictsDao;
    } else {
      synchronized(this) {
        if(_depictsDao == null) {
          _depictsDao = new DepictsDao_Impl(this);
        }
        return _depictsDao;
      }
    }
  }

  @Override
  public UploadedStatusDao UploadedStatusDao() {
    if (_uploadedStatusDao != null) {
      return _uploadedStatusDao;
    } else {
      synchronized(this) {
        if(_uploadedStatusDao == null) {
          _uploadedStatusDao = new UploadedStatusDao_Impl(this);
        }
        return _uploadedStatusDao;
      }
    }
  }

  @Override
  public NotForUploadStatusDao NotForUploadStatusDao() {
    if (_notForUploadStatusDao != null) {
      return _notForUploadStatusDao;
    } else {
      synchronized(this) {
        if(_notForUploadStatusDao == null) {
          _notForUploadStatusDao = new NotForUploadStatusDao_Impl(this);
        }
        return _notForUploadStatusDao;
      }
    }
  }

  @Override
  public ReviewDao ReviewDao() {
    if (_reviewDao != null) {
      return _reviewDao;
    } else {
      synchronized(this) {
        if(_reviewDao == null) {
          _reviewDao = new ReviewDao_Impl(this);
        }
        return _reviewDao;
      }
    }
  }
}
