package com.google.android.gnd.persistence.local.room.dao;

import android.database.Cursor;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.SubmissionMutationEntity;
import com.google.android.gnd.persistence.local.room.models.MutationEntitySyncStatus;
import com.google.android.gnd.persistence.local.room.models.MutationEntityType;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SubmissionMutationDao_Impl implements SubmissionMutationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SubmissionMutationEntity> __insertionAdapterOfSubmissionMutationEntity;

  private final EntityDeletionOrUpdateAdapter<SubmissionMutationEntity> __deletionAdapterOfSubmissionMutationEntity;

  private final EntityDeletionOrUpdateAdapter<SubmissionMutationEntity> __updateAdapterOfSubmissionMutationEntity;

  public SubmissionMutationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSubmissionMutationEntity = new EntityInsertionAdapter<SubmissionMutationEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `submission_mutation` (`id`,`survey_id`,`type`,`state`,`retry_count`,`last_error`,`user_id`,`client_timestamp`,`task_id`,`feature_id`,`job_id`,`submission_id`,`response_deltas`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubmissionMutationEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getSurveyId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSurveyId());
        }
        final int _tmp;
        _tmp = MutationEntityType.toInt(value.getType());
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = MutationEntitySyncStatus.toInt(value.getSyncStatus());
        stmt.bindLong(4, _tmp_1);
        stmt.bindLong(5, value.getRetryCount());
        if (value.getLastError() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getLastError());
        }
        if (value.getUserId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUserId());
        }
        stmt.bindLong(8, value.getClientTimestamp());
        if (value.getTaskId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTaskId());
        }
        if (value.getFeatureId() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getFeatureId());
        }
        if (value.getJobId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getJobId());
        }
        if (value.getSubmissionId() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getSubmissionId());
        }
        if (value.getResponseDeltas() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getResponseDeltas());
        }
      }
    };
    this.__deletionAdapterOfSubmissionMutationEntity = new EntityDeletionOrUpdateAdapter<SubmissionMutationEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `submission_mutation` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubmissionMutationEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfSubmissionMutationEntity = new EntityDeletionOrUpdateAdapter<SubmissionMutationEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `submission_mutation` SET `id` = ?,`survey_id` = ?,`type` = ?,`state` = ?,`retry_count` = ?,`last_error` = ?,`user_id` = ?,`client_timestamp` = ?,`task_id` = ?,`feature_id` = ?,`job_id` = ?,`submission_id` = ?,`response_deltas` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubmissionMutationEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getSurveyId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSurveyId());
        }
        final int _tmp;
        _tmp = MutationEntityType.toInt(value.getType());
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = MutationEntitySyncStatus.toInt(value.getSyncStatus());
        stmt.bindLong(4, _tmp_1);
        stmt.bindLong(5, value.getRetryCount());
        if (value.getLastError() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getLastError());
        }
        if (value.getUserId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUserId());
        }
        stmt.bindLong(8, value.getClientTimestamp());
        if (value.getTaskId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTaskId());
        }
        if (value.getFeatureId() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getFeatureId());
        }
        if (value.getJobId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getJobId());
        }
        if (value.getSubmissionId() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getSubmissionId());
        }
        if (value.getResponseDeltas() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getResponseDeltas());
        }
        if (value.getId() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindLong(14, value.getId());
        }
      }
    };
  }

  @Override
  public Completable insert(final SubmissionMutationEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSubmissionMutationEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final SubmissionMutationEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSubmissionMutationEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final SubmissionMutationEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfSubmissionMutationEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<SubmissionMutationEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSubmissionMutationEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Flowable<List<SubmissionMutationEntity>> loadAllOnceAndStream() {
    final String _sql = "SELECT * FROM submission_mutation";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"submission_mutation"}, new Callable<List<SubmissionMutationEntity>>() {
      @Override
      public List<SubmissionMutationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfRetryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retry_count");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfClientTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "client_timestamp");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "task_id");
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfSubmissionId = CursorUtil.getColumnIndexOrThrow(_cursor, "submission_id");
          final int _cursorIndexOfResponseDeltas = CursorUtil.getColumnIndexOrThrow(_cursor, "response_deltas");
          final List<SubmissionMutationEntity> _result = new ArrayList<SubmissionMutationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubmissionMutationEntity _item;
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            final String _tmpSurveyId;
            if (_cursor.isNull(_cursorIndexOfSurveyId)) {
              _tmpSurveyId = null;
            } else {
              _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
            }
            final MutationEntityType _tmpType;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfType);
            _tmpType = MutationEntityType.fromInt(_tmp);
            final MutationEntitySyncStatus _tmpSyncStatus;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = MutationEntitySyncStatus.fromInt(_tmp_1);
            final long _tmpRetryCount;
            _tmpRetryCount = _cursor.getLong(_cursorIndexOfRetryCount);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpClientTimestamp;
            _tmpClientTimestamp = _cursor.getLong(_cursorIndexOfClientTimestamp);
            final String _tmpTaskId;
            if (_cursor.isNull(_cursorIndexOfTaskId)) {
              _tmpTaskId = null;
            } else {
              _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            }
            final String _tmpFeatureId;
            if (_cursor.isNull(_cursorIndexOfFeatureId)) {
              _tmpFeatureId = null;
            } else {
              _tmpFeatureId = _cursor.getString(_cursorIndexOfFeatureId);
            }
            final String _tmpJobId;
            if (_cursor.isNull(_cursorIndexOfJobId)) {
              _tmpJobId = null;
            } else {
              _tmpJobId = _cursor.getString(_cursorIndexOfJobId);
            }
            final String _tmpSubmissionId;
            if (_cursor.isNull(_cursorIndexOfSubmissionId)) {
              _tmpSubmissionId = null;
            } else {
              _tmpSubmissionId = _cursor.getString(_cursorIndexOfSubmissionId);
            }
            final String _tmpResponseDeltas;
            if (_cursor.isNull(_cursorIndexOfResponseDeltas)) {
              _tmpResponseDeltas = null;
            } else {
              _tmpResponseDeltas = _cursor.getString(_cursorIndexOfResponseDeltas);
            }
            _item = SubmissionMutationEntity.create(_tmpId,_tmpSurveyId,_tmpFeatureId,_tmpJobId,_tmpTaskId,_tmpSubmissionId,_tmpType,_tmpSyncStatus,_tmpResponseDeltas,_tmpRetryCount,_tmpLastError,_tmpUserId,_tmpClientTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<List<SubmissionMutationEntity>> findByFeatureId(final String featureId,
      final MutationEntitySyncStatus... allowedStates) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM submission_mutation WHERE feature_id = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" AND state IN (");
    final int _inputSize = allowedStates.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 1 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    if (featureId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, featureId);
    }
    _argIndex = 2;
    for (MutationEntitySyncStatus _item : allowedStates) {
      final int _tmp;
      _tmp = MutationEntitySyncStatus.toInt(_item);
      _statement.bindLong(_argIndex, _tmp);
      _argIndex ++;
    }
    return RxRoom.createSingle(new Callable<List<SubmissionMutationEntity>>() {
      @Override
      public List<SubmissionMutationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfRetryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retry_count");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfClientTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "client_timestamp");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "task_id");
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfSubmissionId = CursorUtil.getColumnIndexOrThrow(_cursor, "submission_id");
          final int _cursorIndexOfResponseDeltas = CursorUtil.getColumnIndexOrThrow(_cursor, "response_deltas");
          final List<SubmissionMutationEntity> _result = new ArrayList<SubmissionMutationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubmissionMutationEntity _item_1;
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            final String _tmpSurveyId;
            if (_cursor.isNull(_cursorIndexOfSurveyId)) {
              _tmpSurveyId = null;
            } else {
              _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
            }
            final MutationEntityType _tmpType;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfType);
            _tmpType = MutationEntityType.fromInt(_tmp_1);
            final MutationEntitySyncStatus _tmpSyncStatus;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = MutationEntitySyncStatus.fromInt(_tmp_2);
            final long _tmpRetryCount;
            _tmpRetryCount = _cursor.getLong(_cursorIndexOfRetryCount);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpClientTimestamp;
            _tmpClientTimestamp = _cursor.getLong(_cursorIndexOfClientTimestamp);
            final String _tmpTaskId;
            if (_cursor.isNull(_cursorIndexOfTaskId)) {
              _tmpTaskId = null;
            } else {
              _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            }
            final String _tmpFeatureId;
            if (_cursor.isNull(_cursorIndexOfFeatureId)) {
              _tmpFeatureId = null;
            } else {
              _tmpFeatureId = _cursor.getString(_cursorIndexOfFeatureId);
            }
            final String _tmpJobId;
            if (_cursor.isNull(_cursorIndexOfJobId)) {
              _tmpJobId = null;
            } else {
              _tmpJobId = _cursor.getString(_cursorIndexOfJobId);
            }
            final String _tmpSubmissionId;
            if (_cursor.isNull(_cursorIndexOfSubmissionId)) {
              _tmpSubmissionId = null;
            } else {
              _tmpSubmissionId = _cursor.getString(_cursorIndexOfSubmissionId);
            }
            final String _tmpResponseDeltas;
            if (_cursor.isNull(_cursorIndexOfResponseDeltas)) {
              _tmpResponseDeltas = null;
            } else {
              _tmpResponseDeltas = _cursor.getString(_cursorIndexOfResponseDeltas);
            }
            _item_1 = SubmissionMutationEntity.create(_tmpId,_tmpSurveyId,_tmpFeatureId,_tmpJobId,_tmpTaskId,_tmpSubmissionId,_tmpType,_tmpSyncStatus,_tmpResponseDeltas,_tmpRetryCount,_tmpLastError,_tmpUserId,_tmpClientTimestamp);
            _result.add(_item_1);
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<List<SubmissionMutationEntity>> findBySubmissionId(final String submissionId,
      final MutationEntitySyncStatus... allowedStates) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM submission_mutation WHERE submission_id = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" AND state IN (");
    final int _inputSize = allowedStates.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 1 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    if (submissionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, submissionId);
    }
    _argIndex = 2;
    for (MutationEntitySyncStatus _item : allowedStates) {
      final int _tmp;
      _tmp = MutationEntitySyncStatus.toInt(_item);
      _statement.bindLong(_argIndex, _tmp);
      _argIndex ++;
    }
    return RxRoom.createSingle(new Callable<List<SubmissionMutationEntity>>() {
      @Override
      public List<SubmissionMutationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfRetryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retry_count");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfClientTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "client_timestamp");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "task_id");
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfSubmissionId = CursorUtil.getColumnIndexOrThrow(_cursor, "submission_id");
          final int _cursorIndexOfResponseDeltas = CursorUtil.getColumnIndexOrThrow(_cursor, "response_deltas");
          final List<SubmissionMutationEntity> _result = new ArrayList<SubmissionMutationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubmissionMutationEntity _item_1;
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            final String _tmpSurveyId;
            if (_cursor.isNull(_cursorIndexOfSurveyId)) {
              _tmpSurveyId = null;
            } else {
              _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
            }
            final MutationEntityType _tmpType;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfType);
            _tmpType = MutationEntityType.fromInt(_tmp_1);
            final MutationEntitySyncStatus _tmpSyncStatus;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = MutationEntitySyncStatus.fromInt(_tmp_2);
            final long _tmpRetryCount;
            _tmpRetryCount = _cursor.getLong(_cursorIndexOfRetryCount);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpClientTimestamp;
            _tmpClientTimestamp = _cursor.getLong(_cursorIndexOfClientTimestamp);
            final String _tmpTaskId;
            if (_cursor.isNull(_cursorIndexOfTaskId)) {
              _tmpTaskId = null;
            } else {
              _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            }
            final String _tmpFeatureId;
            if (_cursor.isNull(_cursorIndexOfFeatureId)) {
              _tmpFeatureId = null;
            } else {
              _tmpFeatureId = _cursor.getString(_cursorIndexOfFeatureId);
            }
            final String _tmpJobId;
            if (_cursor.isNull(_cursorIndexOfJobId)) {
              _tmpJobId = null;
            } else {
              _tmpJobId = _cursor.getString(_cursorIndexOfJobId);
            }
            final String _tmpSubmissionId;
            if (_cursor.isNull(_cursorIndexOfSubmissionId)) {
              _tmpSubmissionId = null;
            } else {
              _tmpSubmissionId = _cursor.getString(_cursorIndexOfSubmissionId);
            }
            final String _tmpResponseDeltas;
            if (_cursor.isNull(_cursorIndexOfResponseDeltas)) {
              _tmpResponseDeltas = null;
            } else {
              _tmpResponseDeltas = _cursor.getString(_cursorIndexOfResponseDeltas);
            }
            _item_1 = SubmissionMutationEntity.create(_tmpId,_tmpSurveyId,_tmpFeatureId,_tmpJobId,_tmpTaskId,_tmpSubmissionId,_tmpType,_tmpSyncStatus,_tmpResponseDeltas,_tmpRetryCount,_tmpLastError,_tmpUserId,_tmpClientTimestamp);
            _result.add(_item_1);
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<SubmissionMutationEntity>> findByFeatureIdOnceAndStream(
      final String featureId, final MutationEntitySyncStatus... allowedStates) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM submission_mutation WHERE feature_id = ");
    _stringBuilder.append("?");
    _stringBuilder.append(" AND state IN (");
    final int _inputSize = allowedStates.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 1 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    if (featureId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, featureId);
    }
    _argIndex = 2;
    for (MutationEntitySyncStatus _item : allowedStates) {
      final int _tmp;
      _tmp = MutationEntitySyncStatus.toInt(_item);
      _statement.bindLong(_argIndex, _tmp);
      _argIndex ++;
    }
    return RxRoom.createFlowable(__db, false, new String[]{"submission_mutation"}, new Callable<List<SubmissionMutationEntity>>() {
      @Override
      public List<SubmissionMutationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSyncStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfRetryCount = CursorUtil.getColumnIndexOrThrow(_cursor, "retry_count");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "last_error");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfClientTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "client_timestamp");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "task_id");
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfSubmissionId = CursorUtil.getColumnIndexOrThrow(_cursor, "submission_id");
          final int _cursorIndexOfResponseDeltas = CursorUtil.getColumnIndexOrThrow(_cursor, "response_deltas");
          final List<SubmissionMutationEntity> _result = new ArrayList<SubmissionMutationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubmissionMutationEntity _item_1;
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            final String _tmpSurveyId;
            if (_cursor.isNull(_cursorIndexOfSurveyId)) {
              _tmpSurveyId = null;
            } else {
              _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
            }
            final MutationEntityType _tmpType;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfType);
            _tmpType = MutationEntityType.fromInt(_tmp_1);
            final MutationEntitySyncStatus _tmpSyncStatus;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfSyncStatus);
            _tmpSyncStatus = MutationEntitySyncStatus.fromInt(_tmp_2);
            final long _tmpRetryCount;
            _tmpRetryCount = _cursor.getLong(_cursorIndexOfRetryCount);
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final long _tmpClientTimestamp;
            _tmpClientTimestamp = _cursor.getLong(_cursorIndexOfClientTimestamp);
            final String _tmpTaskId;
            if (_cursor.isNull(_cursorIndexOfTaskId)) {
              _tmpTaskId = null;
            } else {
              _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            }
            final String _tmpFeatureId;
            if (_cursor.isNull(_cursorIndexOfFeatureId)) {
              _tmpFeatureId = null;
            } else {
              _tmpFeatureId = _cursor.getString(_cursorIndexOfFeatureId);
            }
            final String _tmpJobId;
            if (_cursor.isNull(_cursorIndexOfJobId)) {
              _tmpJobId = null;
            } else {
              _tmpJobId = _cursor.getString(_cursorIndexOfJobId);
            }
            final String _tmpSubmissionId;
            if (_cursor.isNull(_cursorIndexOfSubmissionId)) {
              _tmpSubmissionId = null;
            } else {
              _tmpSubmissionId = _cursor.getString(_cursorIndexOfSubmissionId);
            }
            final String _tmpResponseDeltas;
            if (_cursor.isNull(_cursorIndexOfResponseDeltas)) {
              _tmpResponseDeltas = null;
            } else {
              _tmpResponseDeltas = _cursor.getString(_cursorIndexOfResponseDeltas);
            }
            _item_1 = SubmissionMutationEntity.create(_tmpId,_tmpSurveyId,_tmpFeatureId,_tmpJobId,_tmpTaskId,_tmpSubmissionId,_tmpType,_tmpSyncStatus,_tmpResponseDeltas,_tmpRetryCount,_tmpLastError,_tmpUserId,_tmpClientTimestamp);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
