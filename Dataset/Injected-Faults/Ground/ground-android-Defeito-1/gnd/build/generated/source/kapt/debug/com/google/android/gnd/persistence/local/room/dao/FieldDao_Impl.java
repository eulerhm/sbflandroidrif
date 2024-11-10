package com.google.android.gnd.persistence.local.room.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.FieldEntity;
import com.google.android.gnd.persistence.local.room.models.FieldEntityType;
import com.google.android.gnd.persistence.local.room.models.StepEntityType;
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
public final class FieldDao_Impl implements FieldDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FieldEntity> __insertionAdapterOfFieldEntity;

  private final EntityDeletionOrUpdateAdapter<FieldEntity> __deletionAdapterOfFieldEntity;

  private final EntityDeletionOrUpdateAdapter<FieldEntity> __updateAdapterOfFieldEntity;

  public FieldDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFieldEntity = new EntityInsertionAdapter<FieldEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `field` (`id`,`index`,`step_type`,`field_type`,`label`,`is_required`,`task_id`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FieldEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getIndex());
        final int _tmp;
        _tmp = StepEntityType.toInt(value.getStepType());
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = FieldEntityType.toInt(value.getFieldType());
        stmt.bindLong(4, _tmp_1);
        if (value.getLabel() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLabel());
        }
        final int _tmp_2;
        _tmp_2 = value.isRequired() ? 1 : 0;
        stmt.bindLong(6, _tmp_2);
        if (value.getTaskId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTaskId());
        }
      }
    };
    this.__deletionAdapterOfFieldEntity = new EntityDeletionOrUpdateAdapter<FieldEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `field` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FieldEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfFieldEntity = new EntityDeletionOrUpdateAdapter<FieldEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `field` SET `id` = ?,`index` = ?,`step_type` = ?,`field_type` = ?,`label` = ?,`is_required` = ?,`task_id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FieldEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getIndex());
        final int _tmp;
        _tmp = StepEntityType.toInt(value.getStepType());
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = FieldEntityType.toInt(value.getFieldType());
        stmt.bindLong(4, _tmp_1);
        if (value.getLabel() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLabel());
        }
        final int _tmp_2;
        _tmp_2 = value.isRequired() ? 1 : 0;
        stmt.bindLong(6, _tmp_2);
        if (value.getTaskId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTaskId());
        }
        if (value.getId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getId());
        }
      }
    };
  }

  @Override
  public Completable insert(final FieldEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFieldEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final FieldEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFieldEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final FieldEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfFieldEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<FieldEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFieldEntity.handleMultiple(entities);
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
