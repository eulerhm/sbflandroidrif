package fr.free.nrw.commons.review;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ReviewDao_Impl implements ReviewDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReviewEntity> __insertionAdapterOfReviewEntity;

  public ReviewDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReviewEntity = new EntityInsertionAdapter<ReviewEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `reviewed-images` (`imageId`) VALUES (?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReviewEntity value) {
        if (value.imageId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.imageId);
        }
      }
    };
  }

  @Override
  public void insert(final ReviewEntity reviewEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReviewEntity.insert(reviewEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Boolean isReviewedAlready(final String imageId) {
    final String _sql = "SELECT EXISTS (SELECT * from `reviewed-images` where imageId = (?))";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (imageId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, imageId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Boolean _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmp;
        if (_cursor.isNull(0)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(0);
        }
        _result = _tmp == null ? null : _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
