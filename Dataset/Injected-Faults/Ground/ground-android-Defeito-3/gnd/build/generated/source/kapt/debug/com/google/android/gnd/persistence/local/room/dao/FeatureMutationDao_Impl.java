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
import com.google.android.gnd.persistence.local.room.entity.FeatureMutationEntity;
import com.google.android.gnd.persistence.local.room.models.Coordinates;
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
public final class FeatureMutationDao_Impl implements FeatureMutationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FeatureMutationEntity> __insertionAdapterOfFeatureMutationEntity;

  private final EntityDeletionOrUpdateAdapter<FeatureMutationEntity> __deletionAdapterOfFeatureMutationEntity;

  private final EntityDeletionOrUpdateAdapter<FeatureMutationEntity> __updateAdapterOfFeatureMutationEntity;

  public FeatureMutationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFeatureMutationEntity = new EntityInsertionAdapter<FeatureMutationEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `feature_mutation` (`id`,`survey_id`,`type`,`state`,`retry_count`,`last_error`,`user_id`,`client_timestamp`,`feature_id`,`job_id`,`polygon_vertices`,`lat`,`lng`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FeatureMutationEntity value) {
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
        if (value.getFeatureId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFeatureId());
        }
        if (value.getJobId() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getJobId());
        }
        if (value.getNewPolygonVertices() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getNewPolygonVertices());
        }
        final Coordinates _tmpNewLocation = value.getNewLocation();
        if(_tmpNewLocation != null) {
          stmt.bindDouble(12, _tmpNewLocation.getLatitude());
          stmt.bindDouble(13, _tmpNewLocation.getLongitude());
        } else {
          stmt.bindNull(12);
          stmt.bindNull(13);
        }
      }
    };
    this.__deletionAdapterOfFeatureMutationEntity = new EntityDeletionOrUpdateAdapter<FeatureMutationEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `feature_mutation` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FeatureMutationEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfFeatureMutationEntity = new EntityDeletionOrUpdateAdapter<FeatureMutationEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `feature_mutation` SET `id` = ?,`survey_id` = ?,`type` = ?,`state` = ?,`retry_count` = ?,`last_error` = ?,`user_id` = ?,`client_timestamp` = ?,`feature_id` = ?,`job_id` = ?,`polygon_vertices` = ?,`lat` = ?,`lng` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FeatureMutationEntity value) {
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
        if (value.getFeatureId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFeatureId());
        }
        if (value.getJobId() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getJobId());
        }
        if (value.getNewPolygonVertices() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getNewPolygonVertices());
        }
        final Coordinates _tmpNewLocation = value.getNewLocation();
        if(_tmpNewLocation != null) {
          stmt.bindDouble(12, _tmpNewLocation.getLatitude());
          stmt.bindDouble(13, _tmpNewLocation.getLongitude());
        } else {
          stmt.bindNull(12);
          stmt.bindNull(13);
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
  public Completable insert(final FeatureMutationEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFeatureMutationEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final FeatureMutationEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFeatureMutationEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final FeatureMutationEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfFeatureMutationEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<FeatureMutationEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFeatureMutationEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Flowable<List<FeatureMutationEntity>> loadAllOnceAndStream() {
    final String _sql = "SELECT * FROM feature_mutation";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"feature_mutation"}, new Callable<List<FeatureMutationEntity>>() {
      @Override
      public List<FeatureMutationEntity> call() throws Exception {
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
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfNewPolygonVertices = CursorUtil.getColumnIndexOrThrow(_cursor, "polygon_vertices");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
          final List<FeatureMutationEntity> _result = new ArrayList<FeatureMutationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FeatureMutationEntity _item;
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
            final String _tmpNewPolygonVertices;
            if (_cursor.isNull(_cursorIndexOfNewPolygonVertices)) {
              _tmpNewPolygonVertices = null;
            } else {
              _tmpNewPolygonVertices = _cursor.getString(_cursorIndexOfNewPolygonVertices);
            }
            final Coordinates _tmpNewLocation;
            if (! (_cursor.isNull(_cursorIndexOfLatitude) && _cursor.isNull(_cursorIndexOfLongitude))) {
              final double _tmpLatitude;
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
              final double _tmpLongitude;
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
              _tmpNewLocation = Coordinates.create(_tmpLatitude,_tmpLongitude);
            }  else  {
              _tmpNewLocation = null;
            }
            _item = FeatureMutationEntity.create(_tmpId,_tmpSurveyId,_tmpFeatureId,_tmpJobId,_tmpType,_tmpSyncStatus,_tmpNewLocation,_tmpNewPolygonVertices,_tmpRetryCount,_tmpLastError,_tmpUserId,_tmpClientTimestamp);
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
  public Single<List<FeatureMutationEntity>> findByFeatureId(final String featureId,
      final MutationEntitySyncStatus... allowedStates) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM feature_mutation WHERE feature_id = ");
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
    return RxRoom.createSingle(new Callable<List<FeatureMutationEntity>>() {
      @Override
      public List<FeatureMutationEntity> call() throws Exception {
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
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfNewPolygonVertices = CursorUtil.getColumnIndexOrThrow(_cursor, "polygon_vertices");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
          final List<FeatureMutationEntity> _result = new ArrayList<FeatureMutationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FeatureMutationEntity _item_1;
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
            final String _tmpNewPolygonVertices;
            if (_cursor.isNull(_cursorIndexOfNewPolygonVertices)) {
              _tmpNewPolygonVertices = null;
            } else {
              _tmpNewPolygonVertices = _cursor.getString(_cursorIndexOfNewPolygonVertices);
            }
            final Coordinates _tmpNewLocation;
            if (! (_cursor.isNull(_cursorIndexOfLatitude) && _cursor.isNull(_cursorIndexOfLongitude))) {
              final double _tmpLatitude;
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
              final double _tmpLongitude;
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
              _tmpNewLocation = Coordinates.create(_tmpLatitude,_tmpLongitude);
            }  else  {
              _tmpNewLocation = null;
            }
            _item_1 = FeatureMutationEntity.create(_tmpId,_tmpSurveyId,_tmpFeatureId,_tmpJobId,_tmpType,_tmpSyncStatus,_tmpNewLocation,_tmpNewPolygonVertices,_tmpRetryCount,_tmpLastError,_tmpUserId,_tmpClientTimestamp);
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
  public Flowable<List<FeatureMutationEntity>> findByFeatureIdOnceAndStream(final String featureId,
      final MutationEntitySyncStatus... allowedStates) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM feature_mutation WHERE feature_id = ");
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
    return RxRoom.createFlowable(__db, false, new String[]{"feature_mutation"}, new Callable<List<FeatureMutationEntity>>() {
      @Override
      public List<FeatureMutationEntity> call() throws Exception {
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
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfNewPolygonVertices = CursorUtil.getColumnIndexOrThrow(_cursor, "polygon_vertices");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
          final List<FeatureMutationEntity> _result = new ArrayList<FeatureMutationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FeatureMutationEntity _item_1;
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
            final String _tmpNewPolygonVertices;
            if (_cursor.isNull(_cursorIndexOfNewPolygonVertices)) {
              _tmpNewPolygonVertices = null;
            } else {
              _tmpNewPolygonVertices = _cursor.getString(_cursorIndexOfNewPolygonVertices);
            }
            final Coordinates _tmpNewLocation;
            if (! (_cursor.isNull(_cursorIndexOfLatitude) && _cursor.isNull(_cursorIndexOfLongitude))) {
              final double _tmpLatitude;
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
              final double _tmpLongitude;
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
              _tmpNewLocation = Coordinates.create(_tmpLatitude,_tmpLongitude);
            }  else  {
              _tmpNewLocation = null;
            }
            _item_1 = FeatureMutationEntity.create(_tmpId,_tmpSurveyId,_tmpFeatureId,_tmpJobId,_tmpType,_tmpSyncStatus,_tmpNewLocation,_tmpNewPolygonVertices,_tmpRetryCount,_tmpLastError,_tmpUserId,_tmpClientTimestamp);
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
