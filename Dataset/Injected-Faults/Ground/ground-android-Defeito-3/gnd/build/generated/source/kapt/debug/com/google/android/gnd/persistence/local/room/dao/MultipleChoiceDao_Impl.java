package com.google.android.gnd.persistence.local.room.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.MultipleChoiceEntity;
import com.google.android.gnd.persistence.local.room.models.MultipleChoiceEntityType;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MultipleChoiceDao_Impl implements MultipleChoiceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MultipleChoiceEntity> __insertionAdapterOfMultipleChoiceEntity;

  private final EntityDeletionOrUpdateAdapter<MultipleChoiceEntity> __deletionAdapterOfMultipleChoiceEntity;

  private final EntityDeletionOrUpdateAdapter<MultipleChoiceEntity> __updateAdapterOfMultipleChoiceEntity;

  public MultipleChoiceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMultipleChoiceEntity = new EntityInsertionAdapter<MultipleChoiceEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `multiple_choice` (`type`,`field_id`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MultipleChoiceEntity value) {
        final int _tmp;
        _tmp = MultipleChoiceEntityType.toInt(value.getType());
        stmt.bindLong(1, _tmp);
        if (value.getFieldId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFieldId());
        }
      }
    };
    this.__deletionAdapterOfMultipleChoiceEntity = new EntityDeletionOrUpdateAdapter<MultipleChoiceEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `multiple_choice` WHERE `field_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MultipleChoiceEntity value) {
        if (value.getFieldId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getFieldId());
        }
      }
    };
    this.__updateAdapterOfMultipleChoiceEntity = new EntityDeletionOrUpdateAdapter<MultipleChoiceEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `multiple_choice` SET `type` = ?,`field_id` = ? WHERE `field_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MultipleChoiceEntity value) {
        final int _tmp;
        _tmp = MultipleChoiceEntityType.toInt(value.getType());
        stmt.bindLong(1, _tmp);
        if (value.getFieldId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFieldId());
        }
        if (value.getFieldId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFieldId());
        }
      }
    };
  }

  @Override
  public Completable insert(final MultipleChoiceEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMultipleChoiceEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final MultipleChoiceEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMultipleChoiceEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final MultipleChoiceEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfMultipleChoiceEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<MultipleChoiceEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMultipleChoiceEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
