package fr.free.nrw.commons.upload.depicts;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import fr.free.nrw.commons.db.Converters;
import fr.free.nrw.commons.upload.structure.depictions.DepictedItem;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DepictsDao_Impl extends DepictsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Depicts> __insertionAdapterOfDepicts;

  private final EntityDeletionOrUpdateAdapter<Depicts> __deletionAdapterOfDepicts;

  public DepictsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDepicts = new EntityInsertionAdapter<Depicts>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `depicts_table` (`item`,`lastUsed`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Depicts value) {
        final String _tmp = Converters.depictsItemToString(value.getItem());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
        final Long _tmp_1 = Converters.dateToTimestamp(value.getLastUsed());
        if (_tmp_1 == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfDepicts = new EntityDeletionOrUpdateAdapter<Depicts>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `depicts_table` WHERE `item` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Depicts value) {
        final String _tmp = Converters.depictsItemToString(value.getItem());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
      }
    };
  }

  @Override
  public Object insert(final Depicts depictedItem, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDepicts.insert(depictedItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final Depicts depicts, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfDepicts.handle(depicts);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllDepict(final Continuation<? super List<Depicts>> continuation) {
    final String _sql = "Select * From depicts_table order by lastUsed DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Depicts>>() {
      @Override
      public List<Depicts> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItem = CursorUtil.getColumnIndexOrThrow(_cursor, "item");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final List<Depicts> _result = new ArrayList<Depicts>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Depicts _item;
            final DepictedItem _tmpItem;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItem)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItem);
            }
            _tmpItem = Converters.stringToDepicts(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = Converters.fromTimestamp(_tmp_1);
            _item = new Depicts(_tmpItem,_tmpLastUsed);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getItemToDelete(final int n,
      final Continuation<? super List<Depicts>> continuation) {
    final String _sql = "Select * From depicts_table order by lastUsed DESC LIMIT ? OFFSET 10";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, n);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Depicts>>() {
      @Override
      public List<Depicts> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItem = CursorUtil.getColumnIndexOrThrow(_cursor, "item");
          final int _cursorIndexOfLastUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsed");
          final List<Depicts> _result = new ArrayList<Depicts>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Depicts _item;
            final DepictedItem _tmpItem;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItem)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItem);
            }
            _tmpItem = Converters.stringToDepicts(_tmp);
            final Date _tmpLastUsed;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfLastUsed)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfLastUsed);
            }
            _tmpLastUsed = Converters.fromTimestamp(_tmp_1);
            _item = new Depicts(_tmpItem,_tmpLastUsed);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
