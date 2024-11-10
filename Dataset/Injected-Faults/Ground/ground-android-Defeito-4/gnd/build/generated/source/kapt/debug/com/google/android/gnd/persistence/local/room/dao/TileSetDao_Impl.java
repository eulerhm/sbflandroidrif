package com.google.android.gnd.persistence.local.room.dao;

import android.database.Cursor;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.TileSetEntity;
import com.google.android.gnd.persistence.local.room.models.TileSetEntityState;
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
public final class TileSetDao_Impl implements TileSetDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TileSetEntity> __insertionAdapterOfTileSetEntity;

  private final EntityDeletionOrUpdateAdapter<TileSetEntity> __deletionAdapterOfTileSetEntity;

  private final EntityDeletionOrUpdateAdapter<TileSetEntity> __updateAdapterOfTileSetEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBasemapReferenceCount;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByUrl;

  public TileSetDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTileSetEntity = new EntityInsertionAdapter<TileSetEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `tile_sources` (`id`,`path`,`url`,`state`,`basemap_count`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TileSetEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getPath() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPath());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        final int _tmp;
        _tmp = TileSetEntityState.toInt(value.getState());
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.getOfflineAreaReferenceCount());
      }
    };
    this.__deletionAdapterOfTileSetEntity = new EntityDeletionOrUpdateAdapter<TileSetEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tile_sources` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TileSetEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfTileSetEntity = new EntityDeletionOrUpdateAdapter<TileSetEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `tile_sources` SET `id` = ?,`path` = ?,`url` = ?,`state` = ?,`basemap_count` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TileSetEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getPath() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPath());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        final int _tmp;
        _tmp = TileSetEntityState.toInt(value.getState());
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.getOfflineAreaReferenceCount());
        if (value.getId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateBasemapReferenceCount = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE tile_sources SET basemap_count=? WHERE url = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByUrl = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tile_sources WHERE url = ?";
        return _query;
      }
    };
  }

  @Override
  public Completable insert(final TileSetEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTileSetEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final TileSetEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTileSetEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final TileSetEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfTileSetEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<TileSetEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTileSetEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> updateBasemapReferenceCount(final int newCount, final String url) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBasemapReferenceCount.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, newCount);
        _argIndex = 2;
        if (url == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, url);
        }
        __db.beginTransaction();
        try {
          final java.lang.Integer _result = _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateBasemapReferenceCount.release(_stmt);
        }
      }
    });
  }

  @Override
  public Maybe<Integer> deleteByUrl(final String url) {
    return Maybe.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByUrl.acquire();
        int _argIndex = 1;
        if (url == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, url);
        }
        __db.beginTransaction();
        try {
          final java.lang.Integer _result = _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteByUrl.release(_stmt);
        }
      }
    });
  }

  @Override
  public Flowable<List<TileSetEntity>> findAllOnceAndStream() {
    final String _sql = "SELECT * FROM tile_sources";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"tile_sources"}, new Callable<List<TileSetEntity>>() {
      @Override
      public List<TileSetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfOfflineAreaReferenceCount = CursorUtil.getColumnIndexOrThrow(_cursor, "basemap_count");
          final List<TileSetEntity> _result = new ArrayList<TileSetEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TileSetEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final TileSetEntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = TileSetEntityState.fromInt(_tmp);
            final int _tmpOfflineAreaReferenceCount;
            _tmpOfflineAreaReferenceCount = _cursor.getInt(_cursorIndexOfOfflineAreaReferenceCount);
            _item = TileSetEntity.create(_tmpId,_tmpPath,_tmpState,_tmpUrl,_tmpOfflineAreaReferenceCount);
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
  public Single<List<TileSetEntity>> findByState(final int state) {
    final String _sql = "SELECT * FROM tile_sources WHERE state = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, state);
    return RxRoom.createSingle(new Callable<List<TileSetEntity>>() {
      @Override
      public List<TileSetEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfOfflineAreaReferenceCount = CursorUtil.getColumnIndexOrThrow(_cursor, "basemap_count");
          final List<TileSetEntity> _result = new ArrayList<TileSetEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TileSetEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final TileSetEntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = TileSetEntityState.fromInt(_tmp);
            final int _tmpOfflineAreaReferenceCount;
            _tmpOfflineAreaReferenceCount = _cursor.getInt(_cursorIndexOfOfflineAreaReferenceCount);
            _item = TileSetEntity.create(_tmpId,_tmpPath,_tmpState,_tmpUrl,_tmpOfflineAreaReferenceCount);
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

  @Override
  public Maybe<TileSetEntity> findById(final String id) {
    final String _sql = "SELECT * FROM tile_sources WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return Maybe.fromCallable(new Callable<TileSetEntity>() {
      @Override
      public TileSetEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfOfflineAreaReferenceCount = CursorUtil.getColumnIndexOrThrow(_cursor, "basemap_count");
          final TileSetEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final TileSetEntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = TileSetEntityState.fromInt(_tmp);
            final int _tmpOfflineAreaReferenceCount;
            _tmpOfflineAreaReferenceCount = _cursor.getInt(_cursorIndexOfOfflineAreaReferenceCount);
            _result = TileSetEntity.create(_tmpId,_tmpPath,_tmpState,_tmpUrl,_tmpOfflineAreaReferenceCount);
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
  public Maybe<TileSetEntity> findByUrl(final String url) {
    final String _sql = "SELECT * FROM tile_sources WHERE url = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    return Maybe.fromCallable(new Callable<TileSetEntity>() {
      @Override
      public TileSetEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfOfflineAreaReferenceCount = CursorUtil.getColumnIndexOrThrow(_cursor, "basemap_count");
          final TileSetEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final TileSetEntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = TileSetEntityState.fromInt(_tmp);
            final int _tmpOfflineAreaReferenceCount;
            _tmpOfflineAreaReferenceCount = _cursor.getInt(_cursorIndexOfOfflineAreaReferenceCount);
            _result = TileSetEntity.create(_tmpId,_tmpPath,_tmpState,_tmpUrl,_tmpOfflineAreaReferenceCount);
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
  public Maybe<TileSetEntity> findByPath(final String path) {
    final String _sql = "SELECT * FROM tile_sources WHERE path = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (path == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, path);
    }
    return Maybe.fromCallable(new Callable<TileSetEntity>() {
      @Override
      public TileSetEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfOfflineAreaReferenceCount = CursorUtil.getColumnIndexOrThrow(_cursor, "basemap_count");
          final TileSetEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final TileSetEntityState _tmpState;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfState);
            _tmpState = TileSetEntityState.fromInt(_tmp);
            final int _tmpOfflineAreaReferenceCount;
            _tmpOfflineAreaReferenceCount = _cursor.getInt(_cursorIndexOfOfflineAreaReferenceCount);
            _result = TileSetEntity.create(_tmpId,_tmpPath,_tmpState,_tmpUrl,_tmpOfflineAreaReferenceCount);
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
