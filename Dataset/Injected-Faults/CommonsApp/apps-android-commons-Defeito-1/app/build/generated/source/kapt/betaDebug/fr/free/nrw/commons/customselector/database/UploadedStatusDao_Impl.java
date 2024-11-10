package fr.free.nrw.commons.customselector.database;

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
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UploadedStatusDao_Impl extends UploadedStatusDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UploadedStatus> __insertionAdapterOfUploadedStatus;

  private final EntityDeletionOrUpdateAdapter<UploadedStatus> __deletionAdapterOfUploadedStatus;

  private final EntityDeletionOrUpdateAdapter<UploadedStatus> __updateAdapterOfUploadedStatus;

  public UploadedStatusDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUploadedStatus = new EntityInsertionAdapter<UploadedStatus>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `uploaded_table` (`imageSHA1`,`modifiedImageSHA1`,`imageResult`,`modifiedImageResult`,`lastUpdated`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UploadedStatus value) {
        if (value.getImageSHA1() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getImageSHA1());
        }
        if (value.getModifiedImageSHA1() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getModifiedImageSHA1());
        }
        final int _tmp = value.getImageResult() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final int _tmp_1 = value.getModifiedImageResult() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        final Long _tmp_2 = Converters.dateToTimestamp(value.getLastUpdated());
        if (_tmp_2 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp_2);
        }
      }
    };
    this.__deletionAdapterOfUploadedStatus = new EntityDeletionOrUpdateAdapter<UploadedStatus>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `uploaded_table` WHERE `imageSHA1` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UploadedStatus value) {
        if (value.getImageSHA1() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getImageSHA1());
        }
      }
    };
    this.__updateAdapterOfUploadedStatus = new EntityDeletionOrUpdateAdapter<UploadedStatus>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `uploaded_table` SET `imageSHA1` = ?,`modifiedImageSHA1` = ?,`imageResult` = ?,`modifiedImageResult` = ?,`lastUpdated` = ? WHERE `imageSHA1` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UploadedStatus value) {
        if (value.getImageSHA1() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getImageSHA1());
        }
        if (value.getModifiedImageSHA1() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getModifiedImageSHA1());
        }
        final int _tmp = value.getImageResult() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final int _tmp_1 = value.getModifiedImageResult() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        final Long _tmp_2 = Converters.dateToTimestamp(value.getLastUpdated());
        if (_tmp_2 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp_2);
        }
        if (value.getImageSHA1() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getImageSHA1());
        }
      }
    };
  }

  @Override
  public Object insert(final UploadedStatus uploadedStatus,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUploadedStatus.insert(uploadedStatus);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final UploadedStatus uploadedStatus,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUploadedStatus.handle(uploadedStatus);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object update(final UploadedStatus uploadedStatus,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUploadedStatus.handle(uploadedStatus);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object getFromImageSHA1(final String imageSHA1,
      final Continuation<? super UploadedStatus> continuation) {
    final String _sql = "SELECT * FROM uploaded_table WHERE imageSHA1 = (?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (imageSHA1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, imageSHA1);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UploadedStatus>() {
      @Override
      public UploadedStatus call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "imageSHA1");
          final int _cursorIndexOfModifiedImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modifiedImageSHA1");
          final int _cursorIndexOfImageResult = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResult");
          final int _cursorIndexOfModifiedImageResult = CursorUtil.getColumnIndexOrThrow(_cursor, "modifiedImageResult");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final UploadedStatus _result;
          if(_cursor.moveToFirst()) {
            final String _tmpImageSHA1;
            if (_cursor.isNull(_cursorIndexOfImageSHA1)) {
              _tmpImageSHA1 = null;
            } else {
              _tmpImageSHA1 = _cursor.getString(_cursorIndexOfImageSHA1);
            }
            final String _tmpModifiedImageSHA1;
            if (_cursor.isNull(_cursorIndexOfModifiedImageSHA1)) {
              _tmpModifiedImageSHA1 = null;
            } else {
              _tmpModifiedImageSHA1 = _cursor.getString(_cursorIndexOfModifiedImageSHA1);
            }
            final boolean _tmpImageResult;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfImageResult);
            _tmpImageResult = _tmp != 0;
            final boolean _tmpModifiedImageResult;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfModifiedImageResult);
            _tmpModifiedImageResult = _tmp_1 != 0;
            final Date _tmpLastUpdated;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfLastUpdated);
            }
            _tmpLastUpdated = Converters.fromTimestamp(_tmp_2);
            _result = new UploadedStatus(_tmpImageSHA1,_tmpModifiedImageSHA1,_tmpImageResult,_tmpModifiedImageResult,_tmpLastUpdated);
          } else {
            _result = null;
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
  public Object getFromModifiedImageSHA1(final String modifiedImageSHA1,
      final Continuation<? super UploadedStatus> continuation) {
    final String _sql = "SELECT * FROM uploaded_table WHERE modifiedImageSHA1 = (?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (modifiedImageSHA1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, modifiedImageSHA1);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UploadedStatus>() {
      @Override
      public UploadedStatus call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "imageSHA1");
          final int _cursorIndexOfModifiedImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "modifiedImageSHA1");
          final int _cursorIndexOfImageResult = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResult");
          final int _cursorIndexOfModifiedImageResult = CursorUtil.getColumnIndexOrThrow(_cursor, "modifiedImageResult");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final UploadedStatus _result;
          if(_cursor.moveToFirst()) {
            final String _tmpImageSHA1;
            if (_cursor.isNull(_cursorIndexOfImageSHA1)) {
              _tmpImageSHA1 = null;
            } else {
              _tmpImageSHA1 = _cursor.getString(_cursorIndexOfImageSHA1);
            }
            final String _tmpModifiedImageSHA1;
            if (_cursor.isNull(_cursorIndexOfModifiedImageSHA1)) {
              _tmpModifiedImageSHA1 = null;
            } else {
              _tmpModifiedImageSHA1 = _cursor.getString(_cursorIndexOfModifiedImageSHA1);
            }
            final boolean _tmpImageResult;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfImageResult);
            _tmpImageResult = _tmp != 0;
            final boolean _tmpModifiedImageResult;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfModifiedImageResult);
            _tmpModifiedImageResult = _tmp_1 != 0;
            final Date _tmpLastUpdated;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfLastUpdated);
            }
            _tmpLastUpdated = Converters.fromTimestamp(_tmp_2);
            _result = new UploadedStatus(_tmpImageSHA1,_tmpModifiedImageSHA1,_tmpImageResult,_tmpModifiedImageResult,_tmpLastUpdated);
          } else {
            _result = null;
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
  public Object findByImageSHA1(final String imageSHA1, final boolean imageResult,
      final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT() FROM uploaded_table WHERE imageSHA1 = (?) AND imageResult = (?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (imageSHA1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, imageSHA1);
    }
    _argIndex = 2;
    final int _tmp = imageResult ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp_1;
            if (_cursor.isNull(0)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(0);
            }
            _result = _tmp_1;
          } else {
            _result = null;
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
  public Object findByModifiedImageSHA1(final String modifiedImageSHA1,
      final boolean modifiedImageResult, final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT() FROM uploaded_table WHERE modifiedImageSHA1 = (?) AND modifiedImageResult = (?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (modifiedImageSHA1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, modifiedImageSHA1);
    }
    _argIndex = 2;
    final int _tmp = modifiedImageResult ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp_1;
            if (_cursor.isNull(0)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(0);
            }
            _result = _tmp_1;
          } else {
            _result = null;
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
