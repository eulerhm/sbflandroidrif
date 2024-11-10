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
import com.google.android.gnd.persistence.local.room.entity.OfflineAreaEntity;
import com.google.android.gnd.persistence.local.room.models.OfflineAreaEntityState;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OfflineAreaDao_Impl implements OfflineAreaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OfflineAreaEntity> __insertionAdapterOfOfflineAreaEntity;

  private final EntityDeletionOrUpdateAdapter<OfflineAreaEntity> __deletionAdapterOfOfflineAreaEntity;

  private final EntityDeletionOrUpdateAdapter<OfflineAreaEntity> __updateAdapterOfOfflineAreaEntity;

  public OfflineAreaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOfflineAreaEntity = new EntityInsertionAdapter<OfflineAreaEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `offline_base_map` (`id`,`name`,`state`,`north`,`south`,`east`,`west`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OfflineAreaEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final int _tmp;
        _tmp = OfflineAreaEntityState.toInt(value.getState());
        stmt.bindLong(3, _tmp);
        stmt.bindDouble(4, value.getNorth());
        stmt.bindDouble(5, value.getSouth());
        stmt.bindDouble(6, value.getEast());
        stmt.bindDouble(7, value.getWest());
      }
    };
    this.__deletionAdapterOfOfflineAreaEntity = new EntityDeletionOrUpdateAdapter<OfflineAreaEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `offline_base_map` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OfflineAreaEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfOfflineAreaEntity = new EntityDeletionOrUpdateAdapter<OfflineAreaEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `offline_base_map` SET `id` = ?,`name` = ?,`state` = ?,`north` = ?,`south` = ?,`east` = ?,`west` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OfflineAreaEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final int _tmp;
        _tmp = OfflineAreaEntityState.toInt(value.getState());
        stmt.bindLong(3, _tmp);
        stmt.bindDouble(4, value.getNorth());
        stmt.bindDouble(5, value.getSouth());
        stmt.bindDouble(6, value.getEast());
        stmt.bindDouble(7, value.getWest());
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getId());
        }
      }
    };
  }

  @Override
  public Completable insert(final OfflineAreaEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfOfflineAreaEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final OfflineAreaEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfOfflineAreaEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final OfflineAreaEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfOfflineAreaEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<OfflineAreaEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfOfflineAreaEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Flowable<List<OfflineAreaEntity>> findAllOnceAndStream() {
    final String _sql = "SELECT * FROM offline_base_map";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"offline_base_map"}, new Callable<List<OfflineAreaEntity>>() {
      @Override
      public List<OfflineAreaEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfNorth = CursorUtil.getColumnIndexOrThrow(_cursor, "north");
          final int _cursorIndexOfSouth = CursorUtil.getColumnIndexOrThrow(_cursor, "south");
          final int _cursorIndexOfEast = CursorUtil.getColumnIndexOrThrow(_cursor, "east");
          final int _cursorIndexOfWest = CursorUtil.getColumnIndexOrThrow(_cursor, "west");
          final List<OfflineAreaEntity> _result = new ArrayList<OfflineAreaEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final OfflineAreaEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final OfflineAreaEntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = OfflineAreaEntityState.fromInt(_tmp);
            final double _tmpNorth;
            _tmpNorth = _cursor.getDouble(_cursorIndexOfNorth);
            final double _tmpSouth;
            _tmpSouth = _cursor.getDouble(_cursorIndexOfSouth);
            final double _tmpEast;
            _tmpEast = _cursor.getDouble(_cursorIndexOfEast);
            final double _tmpWest;
            _tmpWest = _cursor.getDouble(_cursorIndexOfWest);
            _item = OfflineAreaEntity.create(_tmpId,_tmpName,_tmpState,_tmpNorth,_tmpEast,_tmpSouth,_tmpWest);
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
  public Maybe<OfflineAreaEntity> findById(final String id) {
    final String _sql = "SELECT * FROM offline_base_map WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return Maybe.fromCallable(new Callable<OfflineAreaEntity>() {
      @Override
      public OfflineAreaEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfNorth = CursorUtil.getColumnIndexOrThrow(_cursor, "north");
          final int _cursorIndexOfSouth = CursorUtil.getColumnIndexOrThrow(_cursor, "south");
          final int _cursorIndexOfEast = CursorUtil.getColumnIndexOrThrow(_cursor, "east");
          final int _cursorIndexOfWest = CursorUtil.getColumnIndexOrThrow(_cursor, "west");
          final OfflineAreaEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final OfflineAreaEntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = OfflineAreaEntityState.fromInt(_tmp);
            final double _tmpNorth;
            _tmpNorth = _cursor.getDouble(_cursorIndexOfNorth);
            final double _tmpSouth;
            _tmpSouth = _cursor.getDouble(_cursorIndexOfSouth);
            final double _tmpEast;
            _tmpEast = _cursor.getDouble(_cursorIndexOfEast);
            final double _tmpWest;
            _tmpWest = _cursor.getDouble(_cursorIndexOfWest);
            _result = OfflineAreaEntity.create(_tmpId,_tmpName,_tmpState,_tmpNorth,_tmpEast,_tmpSouth,_tmpWest);
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
