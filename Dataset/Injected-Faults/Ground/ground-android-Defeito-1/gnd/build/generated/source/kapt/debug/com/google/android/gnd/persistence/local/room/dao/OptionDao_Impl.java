package com.google.android.gnd.persistence.local.room.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.OptionEntity;
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
public final class OptionDao_Impl implements OptionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OptionEntity> __insertionAdapterOfOptionEntity;

  private final EntityDeletionOrUpdateAdapter<OptionEntity> __deletionAdapterOfOptionEntity;

  private final EntityDeletionOrUpdateAdapter<OptionEntity> __updateAdapterOfOptionEntity;

  public OptionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOptionEntity = new EntityInsertionAdapter<OptionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `option` (`id`,`code`,`label`,`field_id`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OptionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getCode() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCode());
        }
        if (value.getLabel() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLabel());
        }
        if (value.getFieldId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFieldId());
        }
      }
    };
    this.__deletionAdapterOfOptionEntity = new EntityDeletionOrUpdateAdapter<OptionEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `option` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OptionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfOptionEntity = new EntityDeletionOrUpdateAdapter<OptionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `option` SET `id` = ?,`code` = ?,`label` = ?,`field_id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OptionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getCode() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCode());
        }
        if (value.getLabel() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLabel());
        }
        if (value.getFieldId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFieldId());
        }
        if (value.getId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getId());
        }
      }
    };
  }

  @Override
  public Completable insert(final OptionEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfOptionEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final OptionEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfOptionEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final OptionEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfOptionEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<OptionEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfOptionEntity.handleMultiple(entities);
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
