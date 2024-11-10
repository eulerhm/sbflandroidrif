package com.google.android.gnd.persistence.local.room.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.AuditInfoEntity;
import com.google.android.gnd.persistence.local.room.entity.FeatureEntity;
import com.google.android.gnd.persistence.local.room.models.Coordinates;
import com.google.android.gnd.persistence.local.room.models.EntityState;
import com.google.android.gnd.persistence.local.room.models.UserDetails;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FeatureDao_Impl implements FeatureDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FeatureEntity> __insertionAdapterOfFeatureEntity;

  private final EntityDeletionOrUpdateAdapter<FeatureEntity> __deletionAdapterOfFeatureEntity;

  private final EntityDeletionOrUpdateAdapter<FeatureEntity> __updateAdapterOfFeatureEntity;

  public FeatureDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFeatureEntity = new EntityInsertionAdapter<FeatureEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `feature` (`id`,`survey_id`,`job_id`,`geo_json`,`polygon_vertices`,`state`,`lat`,`lng`,`created_clientTimestamp`,`created_serverTimestamp`,`created_user_id`,`created_user_email`,`created_user_display_name`,`modified_clientTimestamp`,`modified_serverTimestamp`,`modified_user_id`,`modified_user_email`,`modified_user_display_name`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FeatureEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getSurveyId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSurveyId());
        }
        if (value.getJobId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getJobId());
        }
        if (value.getGeoJson() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGeoJson());
        }
        if (value.getPolygonVertices() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPolygonVertices());
        }
        final int _tmp;
        _tmp = EntityState.toInt(value.getState());
        stmt.bindLong(6, _tmp);
        final Coordinates _tmpLocation = value.getLocation();
        if(_tmpLocation != null) {
          stmt.bindDouble(7, _tmpLocation.getLatitude());
          stmt.bindDouble(8, _tmpLocation.getLongitude());
        } else {
          stmt.bindNull(7);
          stmt.bindNull(8);
        }
        final AuditInfoEntity _tmpCreated = value.getCreated();
        if(_tmpCreated != null) {
          if (_tmpCreated.getClientTimestamp() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindLong(9, _tmpCreated.getClientTimestamp());
          }
          if (_tmpCreated.getServerTimestamp() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindLong(10, _tmpCreated.getServerTimestamp());
          }
          final UserDetails _tmpUser = _tmpCreated.getUser();
          if(_tmpUser != null) {
            if (_tmpUser.getId() == null) {
              stmt.bindNull(11);
            } else {
              stmt.bindString(11, _tmpUser.getId());
            }
            if (_tmpUser.getEmail() == null) {
              stmt.bindNull(12);
            } else {
              stmt.bindString(12, _tmpUser.getEmail());
            }
            if (_tmpUser.getDisplayName() == null) {
              stmt.bindNull(13);
            } else {
              stmt.bindString(13, _tmpUser.getDisplayName());
            }
          } else {
            stmt.bindNull(11);
            stmt.bindNull(12);
            stmt.bindNull(13);
          }
        } else {
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
        }
        final AuditInfoEntity _tmpLastModified = value.getLastModified();
        if(_tmpLastModified != null) {
          if (_tmpLastModified.getClientTimestamp() == null) {
            stmt.bindNull(14);
          } else {
            stmt.bindLong(14, _tmpLastModified.getClientTimestamp());
          }
          if (_tmpLastModified.getServerTimestamp() == null) {
            stmt.bindNull(15);
          } else {
            stmt.bindLong(15, _tmpLastModified.getServerTimestamp());
          }
          final UserDetails _tmpUser_1 = _tmpLastModified.getUser();
          if(_tmpUser_1 != null) {
            if (_tmpUser_1.getId() == null) {
              stmt.bindNull(16);
            } else {
              stmt.bindString(16, _tmpUser_1.getId());
            }
            if (_tmpUser_1.getEmail() == null) {
              stmt.bindNull(17);
            } else {
              stmt.bindString(17, _tmpUser_1.getEmail());
            }
            if (_tmpUser_1.getDisplayName() == null) {
              stmt.bindNull(18);
            } else {
              stmt.bindString(18, _tmpUser_1.getDisplayName());
            }
          } else {
            stmt.bindNull(16);
            stmt.bindNull(17);
            stmt.bindNull(18);
          }
        } else {
          stmt.bindNull(14);
          stmt.bindNull(15);
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
        }
      }
    };
    this.__deletionAdapterOfFeatureEntity = new EntityDeletionOrUpdateAdapter<FeatureEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `feature` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FeatureEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfFeatureEntity = new EntityDeletionOrUpdateAdapter<FeatureEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `feature` SET `id` = ?,`survey_id` = ?,`job_id` = ?,`geo_json` = ?,`polygon_vertices` = ?,`state` = ?,`lat` = ?,`lng` = ?,`created_clientTimestamp` = ?,`created_serverTimestamp` = ?,`created_user_id` = ?,`created_user_email` = ?,`created_user_display_name` = ?,`modified_clientTimestamp` = ?,`modified_serverTimestamp` = ?,`modified_user_id` = ?,`modified_user_email` = ?,`modified_user_display_name` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FeatureEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getSurveyId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSurveyId());
        }
        if (value.getJobId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getJobId());
        }
        if (value.getGeoJson() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGeoJson());
        }
        if (value.getPolygonVertices() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPolygonVertices());
        }
        final int _tmp;
        _tmp = EntityState.toInt(value.getState());
        stmt.bindLong(6, _tmp);
        final Coordinates _tmpLocation = value.getLocation();
        if(_tmpLocation != null) {
          stmt.bindDouble(7, _tmpLocation.getLatitude());
          stmt.bindDouble(8, _tmpLocation.getLongitude());
        } else {
          stmt.bindNull(7);
          stmt.bindNull(8);
        }
        final AuditInfoEntity _tmpCreated = value.getCreated();
        if(_tmpCreated != null) {
          if (_tmpCreated.getClientTimestamp() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindLong(9, _tmpCreated.getClientTimestamp());
          }
          if (_tmpCreated.getServerTimestamp() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindLong(10, _tmpCreated.getServerTimestamp());
          }
          final UserDetails _tmpUser = _tmpCreated.getUser();
          if(_tmpUser != null) {
            if (_tmpUser.getId() == null) {
              stmt.bindNull(11);
            } else {
              stmt.bindString(11, _tmpUser.getId());
            }
            if (_tmpUser.getEmail() == null) {
              stmt.bindNull(12);
            } else {
              stmt.bindString(12, _tmpUser.getEmail());
            }
            if (_tmpUser.getDisplayName() == null) {
              stmt.bindNull(13);
            } else {
              stmt.bindString(13, _tmpUser.getDisplayName());
            }
          } else {
            stmt.bindNull(11);
            stmt.bindNull(12);
            stmt.bindNull(13);
          }
        } else {
          stmt.bindNull(9);
          stmt.bindNull(10);
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
        }
        final AuditInfoEntity _tmpLastModified = value.getLastModified();
        if(_tmpLastModified != null) {
          if (_tmpLastModified.getClientTimestamp() == null) {
            stmt.bindNull(14);
          } else {
            stmt.bindLong(14, _tmpLastModified.getClientTimestamp());
          }
          if (_tmpLastModified.getServerTimestamp() == null) {
            stmt.bindNull(15);
          } else {
            stmt.bindLong(15, _tmpLastModified.getServerTimestamp());
          }
          final UserDetails _tmpUser_1 = _tmpLastModified.getUser();
          if(_tmpUser_1 != null) {
            if (_tmpUser_1.getId() == null) {
              stmt.bindNull(16);
            } else {
              stmt.bindString(16, _tmpUser_1.getId());
            }
            if (_tmpUser_1.getEmail() == null) {
              stmt.bindNull(17);
            } else {
              stmt.bindString(17, _tmpUser_1.getEmail());
            }
            if (_tmpUser_1.getDisplayName() == null) {
              stmt.bindNull(18);
            } else {
              stmt.bindString(18, _tmpUser_1.getDisplayName());
            }
          } else {
            stmt.bindNull(16);
            stmt.bindNull(17);
            stmt.bindNull(18);
          }
        } else {
          stmt.bindNull(14);
          stmt.bindNull(15);
          stmt.bindNull(16);
          stmt.bindNull(17);
          stmt.bindNull(18);
        }
        if (value.getId() == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.getId());
        }
      }
    };
  }

  @Override
  public Completable insert(final FeatureEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFeatureEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final FeatureEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFeatureEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final FeatureEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfFeatureEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<FeatureEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFeatureEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Flowable<List<FeatureEntity>> findOnceAndStream(final String surveyId,
      final EntityState state) {
    final String _sql = "SELECT * FROM feature WHERE survey_id = ? AND state = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (surveyId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, surveyId);
    }
    _argIndex = 2;
    final int _tmp;
    _tmp = EntityState.toInt(state);
    _statement.bindLong(_argIndex, _tmp);
    return RxRoom.createFlowable(__db, false, new String[]{"feature"}, new Callable<List<FeatureEntity>>() {
      @Override
      public List<FeatureEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfGeoJson = CursorUtil.getColumnIndexOrThrow(_cursor, "geo_json");
          final int _cursorIndexOfPolygonVertices = CursorUtil.getColumnIndexOrThrow(_cursor, "polygon_vertices");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
          final int _cursorIndexOfClientTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "created_clientTimestamp");
          final int _cursorIndexOfServerTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "created_serverTimestamp");
          final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "created_user_id");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "created_user_email");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "created_user_display_name");
          final int _cursorIndexOfClientTimestamp_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_clientTimestamp");
          final int _cursorIndexOfServerTimestamp_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_serverTimestamp");
          final int _cursorIndexOfId_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_user_id");
          final int _cursorIndexOfEmail_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_user_email");
          final int _cursorIndexOfDisplayName_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_user_display_name");
          final List<FeatureEntity> _result = new ArrayList<FeatureEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FeatureEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSurveyId;
            if (_cursor.isNull(_cursorIndexOfSurveyId)) {
              _tmpSurveyId = null;
            } else {
              _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
            }
            final String _tmpJobId;
            if (_cursor.isNull(_cursorIndexOfJobId)) {
              _tmpJobId = null;
            } else {
              _tmpJobId = _cursor.getString(_cursorIndexOfJobId);
            }
            final String _tmpGeoJson;
            if (_cursor.isNull(_cursorIndexOfGeoJson)) {
              _tmpGeoJson = null;
            } else {
              _tmpGeoJson = _cursor.getString(_cursorIndexOfGeoJson);
            }
            final String _tmpPolygonVertices;
            if (_cursor.isNull(_cursorIndexOfPolygonVertices)) {
              _tmpPolygonVertices = null;
            } else {
              _tmpPolygonVertices = _cursor.getString(_cursorIndexOfPolygonVertices);
            }
            final EntityState _tmpState;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfState);
            _tmpState = EntityState.fromInt(_tmp_1);
            final Coordinates _tmpLocation;
            if (! (_cursor.isNull(_cursorIndexOfLatitude) && _cursor.isNull(_cursorIndexOfLongitude))) {
              final double _tmpLatitude;
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
              final double _tmpLongitude;
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
              _tmpLocation = Coordinates.create(_tmpLatitude,_tmpLongitude);
            }  else  {
              _tmpLocation = null;
            }
            final AuditInfoEntity _tmpCreated;
            final Long _tmpClientTimestamp;
            if (_cursor.isNull(_cursorIndexOfClientTimestamp)) {
              _tmpClientTimestamp = null;
            } else {
              _tmpClientTimestamp = _cursor.getLong(_cursorIndexOfClientTimestamp);
            }
            final Long _tmpServerTimestamp;
            if (_cursor.isNull(_cursorIndexOfServerTimestamp)) {
              _tmpServerTimestamp = null;
            } else {
              _tmpServerTimestamp = _cursor.getLong(_cursorIndexOfServerTimestamp);
            }
            final UserDetails _tmpUser;
            final String _tmpId_1;
            if (_cursor.isNull(_cursorIndexOfId_1)) {
              _tmpId_1 = null;
            } else {
              _tmpId_1 = _cursor.getString(_cursorIndexOfId_1);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpDisplayName;
            if (_cursor.isNull(_cursorIndexOfDisplayName)) {
              _tmpDisplayName = null;
            } else {
              _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            }
            _tmpUser = UserDetails.create(_tmpId_1,_tmpEmail,_tmpDisplayName);
            _tmpCreated = AuditInfoEntity.create(_tmpUser,_tmpClientTimestamp,_tmpServerTimestamp);
            final AuditInfoEntity _tmpLastModified;
            final Long _tmpClientTimestamp_1;
            if (_cursor.isNull(_cursorIndexOfClientTimestamp_1)) {
              _tmpClientTimestamp_1 = null;
            } else {
              _tmpClientTimestamp_1 = _cursor.getLong(_cursorIndexOfClientTimestamp_1);
            }
            final Long _tmpServerTimestamp_1;
            if (_cursor.isNull(_cursorIndexOfServerTimestamp_1)) {
              _tmpServerTimestamp_1 = null;
            } else {
              _tmpServerTimestamp_1 = _cursor.getLong(_cursorIndexOfServerTimestamp_1);
            }
            final UserDetails _tmpUser_1;
            final String _tmpId_2;
            if (_cursor.isNull(_cursorIndexOfId_2)) {
              _tmpId_2 = null;
            } else {
              _tmpId_2 = _cursor.getString(_cursorIndexOfId_2);
            }
            final String _tmpEmail_1;
            if (_cursor.isNull(_cursorIndexOfEmail_1)) {
              _tmpEmail_1 = null;
            } else {
              _tmpEmail_1 = _cursor.getString(_cursorIndexOfEmail_1);
            }
            final String _tmpDisplayName_1;
            if (_cursor.isNull(_cursorIndexOfDisplayName_1)) {
              _tmpDisplayName_1 = null;
            } else {
              _tmpDisplayName_1 = _cursor.getString(_cursorIndexOfDisplayName_1);
            }
            _tmpUser_1 = UserDetails.create(_tmpId_2,_tmpEmail_1,_tmpDisplayName_1);
            _tmpLastModified = AuditInfoEntity.create(_tmpUser_1,_tmpClientTimestamp_1,_tmpServerTimestamp_1);
            _item = FeatureEntity.create(_tmpId,_tmpSurveyId,_tmpJobId,_tmpGeoJson,_tmpPolygonVertices,_tmpState,_tmpLocation,_tmpCreated,_tmpLastModified);
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
  public Maybe<FeatureEntity> findById(final String id) {
    final String _sql = "SELECT * FROM feature WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return Maybe.fromCallable(new Callable<FeatureEntity>() {
      @Override
      public FeatureEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
          final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
          final int _cursorIndexOfGeoJson = CursorUtil.getColumnIndexOrThrow(_cursor, "geo_json");
          final int _cursorIndexOfPolygonVertices = CursorUtil.getColumnIndexOrThrow(_cursor, "polygon_vertices");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
          final int _cursorIndexOfClientTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "created_clientTimestamp");
          final int _cursorIndexOfServerTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "created_serverTimestamp");
          final int _cursorIndexOfId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "created_user_id");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "created_user_email");
          final int _cursorIndexOfDisplayName = CursorUtil.getColumnIndexOrThrow(_cursor, "created_user_display_name");
          final int _cursorIndexOfClientTimestamp_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_clientTimestamp");
          final int _cursorIndexOfServerTimestamp_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_serverTimestamp");
          final int _cursorIndexOfId_2 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_user_id");
          final int _cursorIndexOfEmail_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_user_email");
          final int _cursorIndexOfDisplayName_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modified_user_display_name");
          final FeatureEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpSurveyId;
            if (_cursor.isNull(_cursorIndexOfSurveyId)) {
              _tmpSurveyId = null;
            } else {
              _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
            }
            final String _tmpJobId;
            if (_cursor.isNull(_cursorIndexOfJobId)) {
              _tmpJobId = null;
            } else {
              _tmpJobId = _cursor.getString(_cursorIndexOfJobId);
            }
            final String _tmpGeoJson;
            if (_cursor.isNull(_cursorIndexOfGeoJson)) {
              _tmpGeoJson = null;
            } else {
              _tmpGeoJson = _cursor.getString(_cursorIndexOfGeoJson);
            }
            final String _tmpPolygonVertices;
            if (_cursor.isNull(_cursorIndexOfPolygonVertices)) {
              _tmpPolygonVertices = null;
            } else {
              _tmpPolygonVertices = _cursor.getString(_cursorIndexOfPolygonVertices);
            }
            final EntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = EntityState.fromInt(_tmp);
            final Coordinates _tmpLocation;
            if (! (_cursor.isNull(_cursorIndexOfLatitude) && _cursor.isNull(_cursorIndexOfLongitude))) {
              final double _tmpLatitude;
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
              final double _tmpLongitude;
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
              _tmpLocation = Coordinates.create(_tmpLatitude,_tmpLongitude);
            }  else  {
              _tmpLocation = null;
            }
            final AuditInfoEntity _tmpCreated;
            final Long _tmpClientTimestamp;
            if (_cursor.isNull(_cursorIndexOfClientTimestamp)) {
              _tmpClientTimestamp = null;
            } else {
              _tmpClientTimestamp = _cursor.getLong(_cursorIndexOfClientTimestamp);
            }
            final Long _tmpServerTimestamp;
            if (_cursor.isNull(_cursorIndexOfServerTimestamp)) {
              _tmpServerTimestamp = null;
            } else {
              _tmpServerTimestamp = _cursor.getLong(_cursorIndexOfServerTimestamp);
            }
            final UserDetails _tmpUser;
            final String _tmpId_1;
            if (_cursor.isNull(_cursorIndexOfId_1)) {
              _tmpId_1 = null;
            } else {
              _tmpId_1 = _cursor.getString(_cursorIndexOfId_1);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpDisplayName;
            if (_cursor.isNull(_cursorIndexOfDisplayName)) {
              _tmpDisplayName = null;
            } else {
              _tmpDisplayName = _cursor.getString(_cursorIndexOfDisplayName);
            }
            _tmpUser = UserDetails.create(_tmpId_1,_tmpEmail,_tmpDisplayName);
            _tmpCreated = AuditInfoEntity.create(_tmpUser,_tmpClientTimestamp,_tmpServerTimestamp);
            final AuditInfoEntity _tmpLastModified;
            final Long _tmpClientTimestamp_1;
            if (_cursor.isNull(_cursorIndexOfClientTimestamp_1)) {
              _tmpClientTimestamp_1 = null;
            } else {
              _tmpClientTimestamp_1 = _cursor.getLong(_cursorIndexOfClientTimestamp_1);
            }
            final Long _tmpServerTimestamp_1;
            if (_cursor.isNull(_cursorIndexOfServerTimestamp_1)) {
              _tmpServerTimestamp_1 = null;
            } else {
              _tmpServerTimestamp_1 = _cursor.getLong(_cursorIndexOfServerTimestamp_1);
            }
            final UserDetails _tmpUser_1;
            final String _tmpId_2;
            if (_cursor.isNull(_cursorIndexOfId_2)) {
              _tmpId_2 = null;
            } else {
              _tmpId_2 = _cursor.getString(_cursorIndexOfId_2);
            }
            final String _tmpEmail_1;
            if (_cursor.isNull(_cursorIndexOfEmail_1)) {
              _tmpEmail_1 = null;
            } else {
              _tmpEmail_1 = _cursor.getString(_cursorIndexOfEmail_1);
            }
            final String _tmpDisplayName_1;
            if (_cursor.isNull(_cursorIndexOfDisplayName_1)) {
              _tmpDisplayName_1 = null;
            } else {
              _tmpDisplayName_1 = _cursor.getString(_cursorIndexOfDisplayName_1);
            }
            _tmpUser_1 = UserDetails.create(_tmpId_2,_tmpEmail_1,_tmpDisplayName_1);
            _tmpLastModified = AuditInfoEntity.create(_tmpUser_1,_tmpClientTimestamp_1,_tmpServerTimestamp_1);
            _result = FeatureEntity.create(_tmpId,_tmpSurveyId,_tmpJobId,_tmpGeoJson,_tmpPolygonVertices,_tmpState,_tmpLocation,_tmpCreated,_tmpLastModified);
          } else {
            _result = null;
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
