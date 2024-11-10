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
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.AuditInfoEntity;
import com.google.android.gnd.persistence.local.room.entity.SubmissionEntity;
import com.google.android.gnd.persistence.local.room.models.EntityState;
import com.google.android.gnd.persistence.local.room.models.UserDetails;
import io.reactivex.Completable;
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
public final class SubmissionDao_Impl implements SubmissionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SubmissionEntity> __insertionAdapterOfSubmissionEntity;

  private final EntityDeletionOrUpdateAdapter<SubmissionEntity> __deletionAdapterOfSubmissionEntity;

  private final EntityDeletionOrUpdateAdapter<SubmissionEntity> __updateAdapterOfSubmissionEntity;

  public SubmissionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSubmissionEntity = new EntityInsertionAdapter<SubmissionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `submission` (`id`,`feature_id`,`task_id`,`state`,`responses`,`created_clientTimestamp`,`created_serverTimestamp`,`created_user_id`,`created_user_email`,`created_user_display_name`,`modified_clientTimestamp`,`modified_serverTimestamp`,`modified_user_id`,`modified_user_email`,`modified_user_display_name`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubmissionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getFeatureId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFeatureId());
        }
        if (value.getTaskId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskId());
        }
        final int _tmp;
        _tmp = EntityState.toInt(value.getState());
        stmt.bindLong(4, _tmp);
        if (value.getResponses() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getResponses());
        }
        final AuditInfoEntity _tmpCreated = value.getCreated();
        if(_tmpCreated != null) {
          if (_tmpCreated.getClientTimestamp() == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindLong(6, _tmpCreated.getClientTimestamp());
          }
          if (_tmpCreated.getServerTimestamp() == null) {
            stmt.bindNull(7);
          } else {
            stmt.bindLong(7, _tmpCreated.getServerTimestamp());
          }
          final UserDetails _tmpUser = _tmpCreated.getUser();
          if(_tmpUser != null) {
            if (_tmpUser.getId() == null) {
              stmt.bindNull(8);
            } else {
              stmt.bindString(8, _tmpUser.getId());
            }
            if (_tmpUser.getEmail() == null) {
              stmt.bindNull(9);
            } else {
              stmt.bindString(9, _tmpUser.getEmail());
            }
            if (_tmpUser.getDisplayName() == null) {
              stmt.bindNull(10);
            } else {
              stmt.bindString(10, _tmpUser.getDisplayName());
            }
          } else {
            stmt.bindNull(8);
            stmt.bindNull(9);
            stmt.bindNull(10);
          }
        } else {
          stmt.bindNull(6);
          stmt.bindNull(7);
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
        }
        final AuditInfoEntity _tmpLastModified = value.getLastModified();
        if(_tmpLastModified != null) {
          if (_tmpLastModified.getClientTimestamp() == null) {
            stmt.bindNull(11);
          } else {
            stmt.bindLong(11, _tmpLastModified.getClientTimestamp());
          }
          if (_tmpLastModified.getServerTimestamp() == null) {
            stmt.bindNull(12);
          } else {
            stmt.bindLong(12, _tmpLastModified.getServerTimestamp());
          }
          final UserDetails _tmpUser_1 = _tmpLastModified.getUser();
          if(_tmpUser_1 != null) {
            if (_tmpUser_1.getId() == null) {
              stmt.bindNull(13);
            } else {
              stmt.bindString(13, _tmpUser_1.getId());
            }
            if (_tmpUser_1.getEmail() == null) {
              stmt.bindNull(14);
            } else {
              stmt.bindString(14, _tmpUser_1.getEmail());
            }
            if (_tmpUser_1.getDisplayName() == null) {
              stmt.bindNull(15);
            } else {
              stmt.bindString(15, _tmpUser_1.getDisplayName());
            }
          } else {
            stmt.bindNull(13);
            stmt.bindNull(14);
            stmt.bindNull(15);
          }
        } else {
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
          stmt.bindNull(14);
          stmt.bindNull(15);
        }
      }
    };
    this.__deletionAdapterOfSubmissionEntity = new EntityDeletionOrUpdateAdapter<SubmissionEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `submission` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubmissionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfSubmissionEntity = new EntityDeletionOrUpdateAdapter<SubmissionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `submission` SET `id` = ?,`feature_id` = ?,`task_id` = ?,`state` = ?,`responses` = ?,`created_clientTimestamp` = ?,`created_serverTimestamp` = ?,`created_user_id` = ?,`created_user_email` = ?,`created_user_display_name` = ?,`modified_clientTimestamp` = ?,`modified_serverTimestamp` = ?,`modified_user_id` = ?,`modified_user_email` = ?,`modified_user_display_name` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SubmissionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getFeatureId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFeatureId());
        }
        if (value.getTaskId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTaskId());
        }
        final int _tmp;
        _tmp = EntityState.toInt(value.getState());
        stmt.bindLong(4, _tmp);
        if (value.getResponses() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getResponses());
        }
        final AuditInfoEntity _tmpCreated = value.getCreated();
        if(_tmpCreated != null) {
          if (_tmpCreated.getClientTimestamp() == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindLong(6, _tmpCreated.getClientTimestamp());
          }
          if (_tmpCreated.getServerTimestamp() == null) {
            stmt.bindNull(7);
          } else {
            stmt.bindLong(7, _tmpCreated.getServerTimestamp());
          }
          final UserDetails _tmpUser = _tmpCreated.getUser();
          if(_tmpUser != null) {
            if (_tmpUser.getId() == null) {
              stmt.bindNull(8);
            } else {
              stmt.bindString(8, _tmpUser.getId());
            }
            if (_tmpUser.getEmail() == null) {
              stmt.bindNull(9);
            } else {
              stmt.bindString(9, _tmpUser.getEmail());
            }
            if (_tmpUser.getDisplayName() == null) {
              stmt.bindNull(10);
            } else {
              stmt.bindString(10, _tmpUser.getDisplayName());
            }
          } else {
            stmt.bindNull(8);
            stmt.bindNull(9);
            stmt.bindNull(10);
          }
        } else {
          stmt.bindNull(6);
          stmt.bindNull(7);
          stmt.bindNull(8);
          stmt.bindNull(9);
          stmt.bindNull(10);
        }
        final AuditInfoEntity _tmpLastModified = value.getLastModified();
        if(_tmpLastModified != null) {
          if (_tmpLastModified.getClientTimestamp() == null) {
            stmt.bindNull(11);
          } else {
            stmt.bindLong(11, _tmpLastModified.getClientTimestamp());
          }
          if (_tmpLastModified.getServerTimestamp() == null) {
            stmt.bindNull(12);
          } else {
            stmt.bindLong(12, _tmpLastModified.getServerTimestamp());
          }
          final UserDetails _tmpUser_1 = _tmpLastModified.getUser();
          if(_tmpUser_1 != null) {
            if (_tmpUser_1.getId() == null) {
              stmt.bindNull(13);
            } else {
              stmt.bindString(13, _tmpUser_1.getId());
            }
            if (_tmpUser_1.getEmail() == null) {
              stmt.bindNull(14);
            } else {
              stmt.bindString(14, _tmpUser_1.getEmail());
            }
            if (_tmpUser_1.getDisplayName() == null) {
              stmt.bindNull(15);
            } else {
              stmt.bindString(15, _tmpUser_1.getDisplayName());
            }
          } else {
            stmt.bindNull(13);
            stmt.bindNull(14);
            stmt.bindNull(15);
          }
        } else {
          stmt.bindNull(11);
          stmt.bindNull(12);
          stmt.bindNull(13);
          stmt.bindNull(14);
          stmt.bindNull(15);
        }
        if (value.getId() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getId());
        }
      }
    };
  }

  @Override
  public Completable insert(final SubmissionEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSubmissionEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final SubmissionEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSubmissionEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final SubmissionEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfSubmissionEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<SubmissionEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSubmissionEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Maybe<SubmissionEntity> findById(final String submissionId) {
    final String _sql = "SELECT * FROM submission WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (submissionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, submissionId);
    }
    return Maybe.fromCallable(new Callable<SubmissionEntity>() {
      @Override
      public SubmissionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "task_id");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfResponses = CursorUtil.getColumnIndexOrThrow(_cursor, "responses");
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
          final SubmissionEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpFeatureId;
            if (_cursor.isNull(_cursorIndexOfFeatureId)) {
              _tmpFeatureId = null;
            } else {
              _tmpFeatureId = _cursor.getString(_cursorIndexOfFeatureId);
            }
            final String _tmpTaskId;
            if (_cursor.isNull(_cursorIndexOfTaskId)) {
              _tmpTaskId = null;
            } else {
              _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            }
            final EntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = EntityState.fromInt(_tmp);
            final String _tmpResponses;
            if (_cursor.isNull(_cursorIndexOfResponses)) {
              _tmpResponses = null;
            } else {
              _tmpResponses = _cursor.getString(_cursorIndexOfResponses);
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
            _result = SubmissionEntity.create(_tmpId,_tmpFeatureId,_tmpTaskId,_tmpState,_tmpResponses,_tmpCreated,_tmpLastModified);
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

  @Override
  public Single<List<SubmissionEntity>> findByFeatureId(final String featureId, final String taskId,
      final EntityState state) {
    final String _sql = "SELECT * FROM submission WHERE feature_id = ? AND task_id = ? AND state = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (featureId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, featureId);
    }
    _argIndex = 2;
    if (taskId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, taskId);
    }
    _argIndex = 3;
    final int _tmp;
    _tmp = EntityState.toInt(state);
    _statement.bindLong(_argIndex, _tmp);
    return RxRoom.createSingle(new Callable<List<SubmissionEntity>>() {
      @Override
      public List<SubmissionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFeatureId = CursorUtil.getColumnIndexOrThrow(_cursor, "feature_id");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "task_id");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfResponses = CursorUtil.getColumnIndexOrThrow(_cursor, "responses");
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
          final List<SubmissionEntity> _result = new ArrayList<SubmissionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SubmissionEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpFeatureId;
            if (_cursor.isNull(_cursorIndexOfFeatureId)) {
              _tmpFeatureId = null;
            } else {
              _tmpFeatureId = _cursor.getString(_cursorIndexOfFeatureId);
            }
            final String _tmpTaskId;
            if (_cursor.isNull(_cursorIndexOfTaskId)) {
              _tmpTaskId = null;
            } else {
              _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            }
            final EntityState _tmpState;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfState);
            _tmpState = EntityState.fromInt(_tmp_1);
            final String _tmpResponses;
            if (_cursor.isNull(_cursorIndexOfResponses)) {
              _tmpResponses = null;
            } else {
              _tmpResponses = _cursor.getString(_cursorIndexOfResponses);
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
            _item = SubmissionEntity.create(_tmpId,_tmpFeatureId,_tmpTaskId,_tmpState,_tmpResponses,_tmpCreated,_tmpLastModified);
            _result.add(_item);
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
