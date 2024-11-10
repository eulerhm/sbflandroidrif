package com.google.android.gnd.persistence.local.room;

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
import com.google.android.gnd.persistence.local.room.dao.BaseMapDao;
import com.google.android.gnd.persistence.local.room.dao.BaseMapDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.FeatureDao;
import com.google.android.gnd.persistence.local.room.dao.FeatureDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.FeatureMutationDao;
import com.google.android.gnd.persistence.local.room.dao.FeatureMutationDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.FieldDao;
import com.google.android.gnd.persistence.local.room.dao.FieldDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.JobDao;
import com.google.android.gnd.persistence.local.room.dao.JobDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.MultipleChoiceDao;
import com.google.android.gnd.persistence.local.room.dao.MultipleChoiceDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.OfflineAreaDao;
import com.google.android.gnd.persistence.local.room.dao.OfflineAreaDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.OptionDao;
import com.google.android.gnd.persistence.local.room.dao.OptionDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.SubmissionDao;
import com.google.android.gnd.persistence.local.room.dao.SubmissionDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.SubmissionMutationDao;
import com.google.android.gnd.persistence.local.room.dao.SubmissionMutationDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.SurveyDao;
import com.google.android.gnd.persistence.local.room.dao.SurveyDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.TaskDao;
import com.google.android.gnd.persistence.local.room.dao.TaskDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.TileSetDao;
import com.google.android.gnd.persistence.local.room.dao.TileSetDao_Impl;
import com.google.android.gnd.persistence.local.room.dao.UserDao;
import com.google.android.gnd.persistence.local.room.dao.UserDao_Impl;
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
public final class LocalDatabase_Impl extends LocalDatabase {
  private volatile FeatureDao _featureDao;

  private volatile FeatureMutationDao _featureMutationDao;

  private volatile FieldDao _fieldDao;

  private volatile TaskDao _taskDao;

  private volatile JobDao _jobDao;

  private volatile MultipleChoiceDao _multipleChoiceDao;

  private volatile OptionDao _optionDao;

  private volatile SurveyDao _surveyDao;

  private volatile BaseMapDao _baseMapDao;

  private volatile SubmissionDao _submissionDao;

  private volatile SubmissionMutationDao _submissionMutationDao;

  private volatile TileSetDao _tileSetDao;

  private volatile OfflineAreaDao _offlineAreaDao;

  private volatile UserDao _userDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(91) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `feature` (`id` TEXT NOT NULL, `survey_id` TEXT NOT NULL, `job_id` TEXT NOT NULL, `geo_json` TEXT, `polygon_vertices` TEXT, `state` INTEGER NOT NULL, `lat` REAL, `lng` REAL, `created_clientTimestamp` INTEGER NOT NULL, `created_serverTimestamp` INTEGER, `created_user_id` TEXT NOT NULL, `created_user_email` TEXT NOT NULL, `created_user_display_name` TEXT NOT NULL, `modified_clientTimestamp` INTEGER NOT NULL, `modified_serverTimestamp` INTEGER, `modified_user_id` TEXT NOT NULL, `modified_user_email` TEXT NOT NULL, `modified_user_display_name` TEXT NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_feature_survey_id` ON `feature` (`survey_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `feature_mutation` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `survey_id` TEXT, `type` INTEGER, `state` INTEGER, `retry_count` INTEGER NOT NULL, `last_error` TEXT, `user_id` TEXT, `client_timestamp` INTEGER NOT NULL, `feature_id` TEXT NOT NULL, `job_id` TEXT NOT NULL, `polygon_vertices` TEXT, `lat` REAL, `lng` REAL, FOREIGN KEY(`feature_id`) REFERENCES `feature`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_feature_mutation_feature_id` ON `feature_mutation` (`feature_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `field` (`id` TEXT NOT NULL, `index` INTEGER NOT NULL, `step_type` INTEGER NOT NULL, `field_type` INTEGER NOT NULL, `label` TEXT, `is_required` INTEGER NOT NULL, `task_id` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`task_id`) REFERENCES `task`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_field_task_id` ON `field` (`task_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `task` (`id` TEXT NOT NULL, `title` TEXT, `job_id` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`job_id`) REFERENCES `job`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_task_job_id` ON `task` (`job_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `job` (`id` TEXT NOT NULL, `name` TEXT, `survey_id` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`survey_id`) REFERENCES `survey`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_job_survey_id` ON `job` (`survey_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `multiple_choice` (`type` INTEGER NOT NULL, `field_id` TEXT NOT NULL, PRIMARY KEY(`field_id`), FOREIGN KEY(`field_id`) REFERENCES `field`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_multiple_choice_field_id` ON `multiple_choice` (`field_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `option` (`id` TEXT NOT NULL, `code` TEXT NOT NULL, `label` TEXT NOT NULL, `field_id` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`field_id`) REFERENCES `field`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_option_field_id` ON `option` (`field_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `survey` (`id` TEXT NOT NULL, `title` TEXT, `description` TEXT, `acl` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `offline_base_map_source` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `survey_id` TEXT NOT NULL, `url` TEXT NOT NULL, `type` TEXT NOT NULL, FOREIGN KEY(`survey_id`) REFERENCES `survey`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_offline_base_map_source_survey_id` ON `offline_base_map_source` (`survey_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `submission` (`id` TEXT NOT NULL, `feature_id` TEXT NOT NULL, `task_id` TEXT NOT NULL, `state` INTEGER NOT NULL, `responses` TEXT, `created_clientTimestamp` INTEGER NOT NULL, `created_serverTimestamp` INTEGER, `created_user_id` TEXT NOT NULL, `created_user_email` TEXT NOT NULL, `created_user_display_name` TEXT NOT NULL, `modified_clientTimestamp` INTEGER NOT NULL, `modified_serverTimestamp` INTEGER, `modified_user_id` TEXT NOT NULL, `modified_user_email` TEXT NOT NULL, `modified_user_display_name` TEXT NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`feature_id`) REFERENCES `feature`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_submission_feature_id_task_id_state` ON `submission` (`feature_id`, `task_id`, `state`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `submission_mutation` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `survey_id` TEXT, `type` INTEGER, `state` INTEGER, `retry_count` INTEGER NOT NULL, `last_error` TEXT, `user_id` TEXT, `client_timestamp` INTEGER NOT NULL, `task_id` TEXT, `feature_id` TEXT, `job_id` TEXT, `submission_id` TEXT, `response_deltas` TEXT, FOREIGN KEY(`feature_id`) REFERENCES `feature`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`submission_id`) REFERENCES `submission`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_submission_mutation_feature_id` ON `submission_mutation` (`feature_id`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_submission_mutation_submission_id` ON `submission_mutation` (`submission_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `tile_sources` (`id` TEXT NOT NULL, `path` TEXT NOT NULL, `url` TEXT NOT NULL, `state` INTEGER NOT NULL, `basemap_count` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `offline_base_map` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `state` INTEGER NOT NULL, `north` REAL NOT NULL, `south` REAL NOT NULL, `east` REAL NOT NULL, `west` REAL NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`id` TEXT NOT NULL, `email` TEXT NOT NULL, `display_name` TEXT NOT NULL, `photo_url` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '46d85c9339911d816a940e30749c43a5')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `feature`");
        _db.execSQL("DROP TABLE IF EXISTS `feature_mutation`");
        _db.execSQL("DROP TABLE IF EXISTS `field`");
        _db.execSQL("DROP TABLE IF EXISTS `task`");
        _db.execSQL("DROP TABLE IF EXISTS `job`");
        _db.execSQL("DROP TABLE IF EXISTS `multiple_choice`");
        _db.execSQL("DROP TABLE IF EXISTS `option`");
        _db.execSQL("DROP TABLE IF EXISTS `survey`");
        _db.execSQL("DROP TABLE IF EXISTS `offline_base_map_source`");
        _db.execSQL("DROP TABLE IF EXISTS `submission`");
        _db.execSQL("DROP TABLE IF EXISTS `submission_mutation`");
        _db.execSQL("DROP TABLE IF EXISTS `tile_sources`");
        _db.execSQL("DROP TABLE IF EXISTS `offline_base_map`");
        _db.execSQL("DROP TABLE IF EXISTS `user`");
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
        final HashMap<String, TableInfo.Column> _columnsFeature = new HashMap<String, TableInfo.Column>(18);
        _columnsFeature.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("survey_id", new TableInfo.Column("survey_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("job_id", new TableInfo.Column("job_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("geo_json", new TableInfo.Column("geo_json", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("polygon_vertices", new TableInfo.Column("polygon_vertices", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("lat", new TableInfo.Column("lat", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("lng", new TableInfo.Column("lng", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("created_clientTimestamp", new TableInfo.Column("created_clientTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("created_serverTimestamp", new TableInfo.Column("created_serverTimestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("created_user_id", new TableInfo.Column("created_user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("created_user_email", new TableInfo.Column("created_user_email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("created_user_display_name", new TableInfo.Column("created_user_display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("modified_clientTimestamp", new TableInfo.Column("modified_clientTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("modified_serverTimestamp", new TableInfo.Column("modified_serverTimestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("modified_user_id", new TableInfo.Column("modified_user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("modified_user_email", new TableInfo.Column("modified_user_email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeature.put("modified_user_display_name", new TableInfo.Column("modified_user_display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFeature = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFeature = new HashSet<TableInfo.Index>(1);
        _indicesFeature.add(new TableInfo.Index("index_feature_survey_id", false, Arrays.asList("survey_id")));
        final TableInfo _infoFeature = new TableInfo("feature", _columnsFeature, _foreignKeysFeature, _indicesFeature);
        final TableInfo _existingFeature = TableInfo.read(_db, "feature");
        if (! _infoFeature.equals(_existingFeature)) {
          return new RoomOpenHelper.ValidationResult(false, "feature(com.google.android.gnd.persistence.local.room.entity.FeatureEntity).\n"
                  + " Expected:\n" + _infoFeature + "\n"
                  + " Found:\n" + _existingFeature);
        }
        final HashMap<String, TableInfo.Column> _columnsFeatureMutation = new HashMap<String, TableInfo.Column>(13);
        _columnsFeatureMutation.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("survey_id", new TableInfo.Column("survey_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("type", new TableInfo.Column("type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("state", new TableInfo.Column("state", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("retry_count", new TableInfo.Column("retry_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("last_error", new TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("client_timestamp", new TableInfo.Column("client_timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("feature_id", new TableInfo.Column("feature_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("job_id", new TableInfo.Column("job_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("polygon_vertices", new TableInfo.Column("polygon_vertices", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("lat", new TableInfo.Column("lat", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFeatureMutation.put("lng", new TableInfo.Column("lng", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFeatureMutation = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysFeatureMutation.add(new TableInfo.ForeignKey("feature", "CASCADE", "NO ACTION",Arrays.asList("feature_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesFeatureMutation = new HashSet<TableInfo.Index>(1);
        _indicesFeatureMutation.add(new TableInfo.Index("index_feature_mutation_feature_id", false, Arrays.asList("feature_id")));
        final TableInfo _infoFeatureMutation = new TableInfo("feature_mutation", _columnsFeatureMutation, _foreignKeysFeatureMutation, _indicesFeatureMutation);
        final TableInfo _existingFeatureMutation = TableInfo.read(_db, "feature_mutation");
        if (! _infoFeatureMutation.equals(_existingFeatureMutation)) {
          return new RoomOpenHelper.ValidationResult(false, "feature_mutation(com.google.android.gnd.persistence.local.room.entity.FeatureMutationEntity).\n"
                  + " Expected:\n" + _infoFeatureMutation + "\n"
                  + " Found:\n" + _existingFeatureMutation);
        }
        final HashMap<String, TableInfo.Column> _columnsField = new HashMap<String, TableInfo.Column>(7);
        _columnsField.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsField.put("index", new TableInfo.Column("index", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsField.put("step_type", new TableInfo.Column("step_type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsField.put("field_type", new TableInfo.Column("field_type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsField.put("label", new TableInfo.Column("label", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsField.put("is_required", new TableInfo.Column("is_required", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsField.put("task_id", new TableInfo.Column("task_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysField = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysField.add(new TableInfo.ForeignKey("task", "CASCADE", "NO ACTION",Arrays.asList("task_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesField = new HashSet<TableInfo.Index>(1);
        _indicesField.add(new TableInfo.Index("index_field_task_id", false, Arrays.asList("task_id")));
        final TableInfo _infoField = new TableInfo("field", _columnsField, _foreignKeysField, _indicesField);
        final TableInfo _existingField = TableInfo.read(_db, "field");
        if (! _infoField.equals(_existingField)) {
          return new RoomOpenHelper.ValidationResult(false, "field(com.google.android.gnd.persistence.local.room.entity.FieldEntity).\n"
                  + " Expected:\n" + _infoField + "\n"
                  + " Found:\n" + _existingField);
        }
        final HashMap<String, TableInfo.Column> _columnsTask = new HashMap<String, TableInfo.Column>(3);
        _columnsTask.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTask.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTask.put("job_id", new TableInfo.Column("job_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTask = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTask.add(new TableInfo.ForeignKey("job", "CASCADE", "NO ACTION",Arrays.asList("job_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesTask = new HashSet<TableInfo.Index>(1);
        _indicesTask.add(new TableInfo.Index("index_task_job_id", false, Arrays.asList("job_id")));
        final TableInfo _infoTask = new TableInfo("task", _columnsTask, _foreignKeysTask, _indicesTask);
        final TableInfo _existingTask = TableInfo.read(_db, "task");
        if (! _infoTask.equals(_existingTask)) {
          return new RoomOpenHelper.ValidationResult(false, "task(com.google.android.gnd.persistence.local.room.entity.TaskEntity).\n"
                  + " Expected:\n" + _infoTask + "\n"
                  + " Found:\n" + _existingTask);
        }
        final HashMap<String, TableInfo.Column> _columnsJob = new HashMap<String, TableInfo.Column>(3);
        _columnsJob.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsJob.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsJob.put("survey_id", new TableInfo.Column("survey_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysJob = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysJob.add(new TableInfo.ForeignKey("survey", "CASCADE", "NO ACTION",Arrays.asList("survey_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesJob = new HashSet<TableInfo.Index>(1);
        _indicesJob.add(new TableInfo.Index("index_job_survey_id", false, Arrays.asList("survey_id")));
        final TableInfo _infoJob = new TableInfo("job", _columnsJob, _foreignKeysJob, _indicesJob);
        final TableInfo _existingJob = TableInfo.read(_db, "job");
        if (! _infoJob.equals(_existingJob)) {
          return new RoomOpenHelper.ValidationResult(false, "job(com.google.android.gnd.persistence.local.room.entity.JobEntity).\n"
                  + " Expected:\n" + _infoJob + "\n"
                  + " Found:\n" + _existingJob);
        }
        final HashMap<String, TableInfo.Column> _columnsMultipleChoice = new HashMap<String, TableInfo.Column>(2);
        _columnsMultipleChoice.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultipleChoice.put("field_id", new TableInfo.Column("field_id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMultipleChoice = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMultipleChoice.add(new TableInfo.ForeignKey("field", "CASCADE", "NO ACTION",Arrays.asList("field_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesMultipleChoice = new HashSet<TableInfo.Index>(1);
        _indicesMultipleChoice.add(new TableInfo.Index("index_multiple_choice_field_id", false, Arrays.asList("field_id")));
        final TableInfo _infoMultipleChoice = new TableInfo("multiple_choice", _columnsMultipleChoice, _foreignKeysMultipleChoice, _indicesMultipleChoice);
        final TableInfo _existingMultipleChoice = TableInfo.read(_db, "multiple_choice");
        if (! _infoMultipleChoice.equals(_existingMultipleChoice)) {
          return new RoomOpenHelper.ValidationResult(false, "multiple_choice(com.google.android.gnd.persistence.local.room.entity.MultipleChoiceEntity).\n"
                  + " Expected:\n" + _infoMultipleChoice + "\n"
                  + " Found:\n" + _existingMultipleChoice);
        }
        final HashMap<String, TableInfo.Column> _columnsOption = new HashMap<String, TableInfo.Column>(4);
        _columnsOption.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOption.put("code", new TableInfo.Column("code", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOption.put("label", new TableInfo.Column("label", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOption.put("field_id", new TableInfo.Column("field_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOption = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysOption.add(new TableInfo.ForeignKey("field", "CASCADE", "NO ACTION",Arrays.asList("field_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesOption = new HashSet<TableInfo.Index>(1);
        _indicesOption.add(new TableInfo.Index("index_option_field_id", false, Arrays.asList("field_id")));
        final TableInfo _infoOption = new TableInfo("option", _columnsOption, _foreignKeysOption, _indicesOption);
        final TableInfo _existingOption = TableInfo.read(_db, "option");
        if (! _infoOption.equals(_existingOption)) {
          return new RoomOpenHelper.ValidationResult(false, "option(com.google.android.gnd.persistence.local.room.entity.OptionEntity).\n"
                  + " Expected:\n" + _infoOption + "\n"
                  + " Found:\n" + _existingOption);
        }
        final HashMap<String, TableInfo.Column> _columnsSurvey = new HashMap<String, TableInfo.Column>(4);
        _columnsSurvey.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSurvey.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSurvey.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSurvey.put("acl", new TableInfo.Column("acl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSurvey = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSurvey = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSurvey = new TableInfo("survey", _columnsSurvey, _foreignKeysSurvey, _indicesSurvey);
        final TableInfo _existingSurvey = TableInfo.read(_db, "survey");
        if (! _infoSurvey.equals(_existingSurvey)) {
          return new RoomOpenHelper.ValidationResult(false, "survey(com.google.android.gnd.persistence.local.room.entity.SurveyEntity).\n"
                  + " Expected:\n" + _infoSurvey + "\n"
                  + " Found:\n" + _existingSurvey);
        }
        final HashMap<String, TableInfo.Column> _columnsOfflineBaseMapSource = new HashMap<String, TableInfo.Column>(4);
        _columnsOfflineBaseMapSource.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMapSource.put("survey_id", new TableInfo.Column("survey_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMapSource.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMapSource.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOfflineBaseMapSource = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysOfflineBaseMapSource.add(new TableInfo.ForeignKey("survey", "CASCADE", "NO ACTION",Arrays.asList("survey_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesOfflineBaseMapSource = new HashSet<TableInfo.Index>(1);
        _indicesOfflineBaseMapSource.add(new TableInfo.Index("index_offline_base_map_source_survey_id", false, Arrays.asList("survey_id")));
        final TableInfo _infoOfflineBaseMapSource = new TableInfo("offline_base_map_source", _columnsOfflineBaseMapSource, _foreignKeysOfflineBaseMapSource, _indicesOfflineBaseMapSource);
        final TableInfo _existingOfflineBaseMapSource = TableInfo.read(_db, "offline_base_map_source");
        if (! _infoOfflineBaseMapSource.equals(_existingOfflineBaseMapSource)) {
          return new RoomOpenHelper.ValidationResult(false, "offline_base_map_source(com.google.android.gnd.persistence.local.room.entity.BaseMapEntity).\n"
                  + " Expected:\n" + _infoOfflineBaseMapSource + "\n"
                  + " Found:\n" + _existingOfflineBaseMapSource);
        }
        final HashMap<String, TableInfo.Column> _columnsSubmission = new HashMap<String, TableInfo.Column>(15);
        _columnsSubmission.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("feature_id", new TableInfo.Column("feature_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("task_id", new TableInfo.Column("task_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("responses", new TableInfo.Column("responses", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("created_clientTimestamp", new TableInfo.Column("created_clientTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("created_serverTimestamp", new TableInfo.Column("created_serverTimestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("created_user_id", new TableInfo.Column("created_user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("created_user_email", new TableInfo.Column("created_user_email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("created_user_display_name", new TableInfo.Column("created_user_display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("modified_clientTimestamp", new TableInfo.Column("modified_clientTimestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("modified_serverTimestamp", new TableInfo.Column("modified_serverTimestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("modified_user_id", new TableInfo.Column("modified_user_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("modified_user_email", new TableInfo.Column("modified_user_email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmission.put("modified_user_display_name", new TableInfo.Column("modified_user_display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubmission = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysSubmission.add(new TableInfo.ForeignKey("feature", "CASCADE", "NO ACTION",Arrays.asList("feature_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesSubmission = new HashSet<TableInfo.Index>(1);
        _indicesSubmission.add(new TableInfo.Index("index_submission_feature_id_task_id_state", false, Arrays.asList("feature_id","task_id","state")));
        final TableInfo _infoSubmission = new TableInfo("submission", _columnsSubmission, _foreignKeysSubmission, _indicesSubmission);
        final TableInfo _existingSubmission = TableInfo.read(_db, "submission");
        if (! _infoSubmission.equals(_existingSubmission)) {
          return new RoomOpenHelper.ValidationResult(false, "submission(com.google.android.gnd.persistence.local.room.entity.SubmissionEntity).\n"
                  + " Expected:\n" + _infoSubmission + "\n"
                  + " Found:\n" + _existingSubmission);
        }
        final HashMap<String, TableInfo.Column> _columnsSubmissionMutation = new HashMap<String, TableInfo.Column>(13);
        _columnsSubmissionMutation.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("survey_id", new TableInfo.Column("survey_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("type", new TableInfo.Column("type", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("state", new TableInfo.Column("state", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("retry_count", new TableInfo.Column("retry_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("last_error", new TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("client_timestamp", new TableInfo.Column("client_timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("task_id", new TableInfo.Column("task_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("feature_id", new TableInfo.Column("feature_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("job_id", new TableInfo.Column("job_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("submission_id", new TableInfo.Column("submission_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubmissionMutation.put("response_deltas", new TableInfo.Column("response_deltas", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubmissionMutation = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysSubmissionMutation.add(new TableInfo.ForeignKey("feature", "CASCADE", "NO ACTION",Arrays.asList("feature_id"), Arrays.asList("id")));
        _foreignKeysSubmissionMutation.add(new TableInfo.ForeignKey("submission", "CASCADE", "NO ACTION",Arrays.asList("submission_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesSubmissionMutation = new HashSet<TableInfo.Index>(2);
        _indicesSubmissionMutation.add(new TableInfo.Index("index_submission_mutation_feature_id", false, Arrays.asList("feature_id")));
        _indicesSubmissionMutation.add(new TableInfo.Index("index_submission_mutation_submission_id", false, Arrays.asList("submission_id")));
        final TableInfo _infoSubmissionMutation = new TableInfo("submission_mutation", _columnsSubmissionMutation, _foreignKeysSubmissionMutation, _indicesSubmissionMutation);
        final TableInfo _existingSubmissionMutation = TableInfo.read(_db, "submission_mutation");
        if (! _infoSubmissionMutation.equals(_existingSubmissionMutation)) {
          return new RoomOpenHelper.ValidationResult(false, "submission_mutation(com.google.android.gnd.persistence.local.room.entity.SubmissionMutationEntity).\n"
                  + " Expected:\n" + _infoSubmissionMutation + "\n"
                  + " Found:\n" + _existingSubmissionMutation);
        }
        final HashMap<String, TableInfo.Column> _columnsTileSources = new HashMap<String, TableInfo.Column>(5);
        _columnsTileSources.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTileSources.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTileSources.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTileSources.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTileSources.put("basemap_count", new TableInfo.Column("basemap_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTileSources = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTileSources = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTileSources = new TableInfo("tile_sources", _columnsTileSources, _foreignKeysTileSources, _indicesTileSources);
        final TableInfo _existingTileSources = TableInfo.read(_db, "tile_sources");
        if (! _infoTileSources.equals(_existingTileSources)) {
          return new RoomOpenHelper.ValidationResult(false, "tile_sources(com.google.android.gnd.persistence.local.room.entity.TileSetEntity).\n"
                  + " Expected:\n" + _infoTileSources + "\n"
                  + " Found:\n" + _existingTileSources);
        }
        final HashMap<String, TableInfo.Column> _columnsOfflineBaseMap = new HashMap<String, TableInfo.Column>(7);
        _columnsOfflineBaseMap.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMap.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMap.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMap.put("north", new TableInfo.Column("north", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMap.put("south", new TableInfo.Column("south", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMap.put("east", new TableInfo.Column("east", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOfflineBaseMap.put("west", new TableInfo.Column("west", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOfflineBaseMap = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOfflineBaseMap = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOfflineBaseMap = new TableInfo("offline_base_map", _columnsOfflineBaseMap, _foreignKeysOfflineBaseMap, _indicesOfflineBaseMap);
        final TableInfo _existingOfflineBaseMap = TableInfo.read(_db, "offline_base_map");
        if (! _infoOfflineBaseMap.equals(_existingOfflineBaseMap)) {
          return new RoomOpenHelper.ValidationResult(false, "offline_base_map(com.google.android.gnd.persistence.local.room.entity.OfflineAreaEntity).\n"
                  + " Expected:\n" + _infoOfflineBaseMap + "\n"
                  + " Found:\n" + _existingOfflineBaseMap);
        }
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(4);
        _columnsUser.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("display_name", new TableInfo.Column("display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("photo_url", new TableInfo.Column("photo_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("user", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "user");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "user(com.google.android.gnd.persistence.local.room.entity.UserEntity).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "46d85c9339911d816a940e30749c43a5", "b1fd8e2d91763a5df07ed940e7b279d0");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "feature","feature_mutation","field","task","job","multiple_choice","option","survey","offline_base_map_source","submission","submission_mutation","tile_sources","offline_base_map","user");
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
      _db.execSQL("DELETE FROM `feature`");
      _db.execSQL("DELETE FROM `feature_mutation`");
      _db.execSQL("DELETE FROM `field`");
      _db.execSQL("DELETE FROM `task`");
      _db.execSQL("DELETE FROM `job`");
      _db.execSQL("DELETE FROM `multiple_choice`");
      _db.execSQL("DELETE FROM `option`");
      _db.execSQL("DELETE FROM `survey`");
      _db.execSQL("DELETE FROM `offline_base_map_source`");
      _db.execSQL("DELETE FROM `submission`");
      _db.execSQL("DELETE FROM `submission_mutation`");
      _db.execSQL("DELETE FROM `tile_sources`");
      _db.execSQL("DELETE FROM `offline_base_map`");
      _db.execSQL("DELETE FROM `user`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(FeatureDao.class, FeatureDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FeatureMutationDao.class, FeatureMutationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FieldDao.class, FieldDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TaskDao.class, TaskDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(JobDao.class, JobDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MultipleChoiceDao.class, MultipleChoiceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OptionDao.class, OptionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SurveyDao.class, SurveyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BaseMapDao.class, BaseMapDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SubmissionDao.class, SubmissionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SubmissionMutationDao.class, SubmissionMutationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TileSetDao.class, TileSetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(OfflineAreaDao.class, OfflineAreaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public FeatureDao featureDao() {
    if (_featureDao != null) {
      return _featureDao;
    } else {
      synchronized(this) {
        if(_featureDao == null) {
          _featureDao = new FeatureDao_Impl(this);
        }
        return _featureDao;
      }
    }
  }

  @Override
  public FeatureMutationDao featureMutationDao() {
    if (_featureMutationDao != null) {
      return _featureMutationDao;
    } else {
      synchronized(this) {
        if(_featureMutationDao == null) {
          _featureMutationDao = new FeatureMutationDao_Impl(this);
        }
        return _featureMutationDao;
      }
    }
  }

  @Override
  public FieldDao fieldDao() {
    if (_fieldDao != null) {
      return _fieldDao;
    } else {
      synchronized(this) {
        if(_fieldDao == null) {
          _fieldDao = new FieldDao_Impl(this);
        }
        return _fieldDao;
      }
    }
  }

  @Override
  public TaskDao taskDao() {
    if (_taskDao != null) {
      return _taskDao;
    } else {
      synchronized(this) {
        if(_taskDao == null) {
          _taskDao = new TaskDao_Impl(this);
        }
        return _taskDao;
      }
    }
  }

  @Override
  public JobDao jobDao() {
    if (_jobDao != null) {
      return _jobDao;
    } else {
      synchronized(this) {
        if(_jobDao == null) {
          _jobDao = new JobDao_Impl(this);
        }
        return _jobDao;
      }
    }
  }

  @Override
  public MultipleChoiceDao multipleChoiceDao() {
    if (_multipleChoiceDao != null) {
      return _multipleChoiceDao;
    } else {
      synchronized(this) {
        if(_multipleChoiceDao == null) {
          _multipleChoiceDao = new MultipleChoiceDao_Impl(this);
        }
        return _multipleChoiceDao;
      }
    }
  }

  @Override
  public OptionDao optionDao() {
    if (_optionDao != null) {
      return _optionDao;
    } else {
      synchronized(this) {
        if(_optionDao == null) {
          _optionDao = new OptionDao_Impl(this);
        }
        return _optionDao;
      }
    }
  }

  @Override
  public SurveyDao surveyDao() {
    if (_surveyDao != null) {
      return _surveyDao;
    } else {
      synchronized(this) {
        if(_surveyDao == null) {
          _surveyDao = new SurveyDao_Impl(this);
        }
        return _surveyDao;
      }
    }
  }

  @Override
  public BaseMapDao baseMapDao() {
    if (_baseMapDao != null) {
      return _baseMapDao;
    } else {
      synchronized(this) {
        if(_baseMapDao == null) {
          _baseMapDao = new BaseMapDao_Impl(this);
        }
        return _baseMapDao;
      }
    }
  }

  @Override
  public SubmissionDao submissionDao() {
    if (_submissionDao != null) {
      return _submissionDao;
    } else {
      synchronized(this) {
        if(_submissionDao == null) {
          _submissionDao = new SubmissionDao_Impl(this);
        }
        return _submissionDao;
      }
    }
  }

  @Override
  public SubmissionMutationDao submissionMutationDao() {
    if (_submissionMutationDao != null) {
      return _submissionMutationDao;
    } else {
      synchronized(this) {
        if(_submissionMutationDao == null) {
          _submissionMutationDao = new SubmissionMutationDao_Impl(this);
        }
        return _submissionMutationDao;
      }
    }
  }

  @Override
  public TileSetDao tileSetDao() {
    if (_tileSetDao != null) {
      return _tileSetDao;
    } else {
      synchronized(this) {
        if(_tileSetDao == null) {
          _tileSetDao = new TileSetDao_Impl(this);
        }
        return _tileSetDao;
      }
    }
  }

  @Override
  public OfflineAreaDao offlineAreaDao() {
    if (_offlineAreaDao != null) {
      return _offlineAreaDao;
    } else {
      synchronized(this) {
        if(_offlineAreaDao == null) {
          _offlineAreaDao = new OfflineAreaDao_Impl(this);
        }
        return _offlineAreaDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }
}
