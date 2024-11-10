package fr.free.nrw.commons.customselector.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NotForUploadStatusDao_Impl extends NotForUploadStatusDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NotForUploadStatus> __insertionAdapterOfNotForUploadStatus;

  private final EntityDeletionOrUpdateAdapter<NotForUploadStatus> __deletionAdapterOfNotForUploadStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWithImageSHA1;

  public NotForUploadStatusDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotForUploadStatus = new EntityInsertionAdapter<NotForUploadStatus>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `images_not_for_upload_table` (`imageSHA1`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotForUploadStatus value) {
        if (value.getImageSHA1() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getImageSHA1());
        }
      }
    };
    this.__deletionAdapterOfNotForUploadStatus = new EntityDeletionOrUpdateAdapter<NotForUploadStatus>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `images_not_for_upload_table` WHERE `imageSHA1` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotForUploadStatus value) {
        if (value.getImageSHA1() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getImageSHA1());
        }
      }
    };
    this.__preparedStmtOfDeleteWithImageSHA1 = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM images_not_for_upload_table WHERE imageSHA1 = (?) ";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final NotForUploadStatus notForUploadStatus,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNotForUploadStatus.insert(notForUploadStatus);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final NotForUploadStatus notForUploadStatus,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfNotForUploadStatus.handle(notForUploadStatus);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteWithImageSHA1(final String imageSHA1,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWithImageSHA1.acquire();
        int _argIndex = 1;
        if (imageSHA1 == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, imageSHA1);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteWithImageSHA1.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getFromImageSHA1(final String imageSHA1,
      final Continuation<? super NotForUploadStatus> continuation) {
    final String _sql = "SELECT * FROM images_not_for_upload_table WHERE imageSHA1 = (?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (imageSHA1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, imageSHA1);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<NotForUploadStatus>() {
      @Override
      public NotForUploadStatus call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "imageSHA1");
          final NotForUploadStatus _result;
          if(_cursor.moveToFirst()) {
            final String _tmpImageSHA1;
            if (_cursor.isNull(_cursorIndexOfImageSHA1)) {
              _tmpImageSHA1 = null;
            } else {
              _tmpImageSHA1 = _cursor.getString(_cursorIndexOfImageSHA1);
            }
            _result = new NotForUploadStatus(_tmpImageSHA1);
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
  public Object find(final String imageSHA1, final Continuation<? super Integer> continuation) {
    final String _sql = "SELECT COUNT() FROM images_not_for_upload_table WHERE imageSHA1 = (?) ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (imageSHA1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, imageSHA1);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if(_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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
