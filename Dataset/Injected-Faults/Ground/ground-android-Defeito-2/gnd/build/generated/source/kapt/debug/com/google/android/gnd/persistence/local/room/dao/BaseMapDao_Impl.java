package com.google.android.gnd.persistence.local.room.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.BaseMapEntity;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BaseMapDao_Impl implements BaseMapDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BaseMapEntity> __insertionAdapterOfBaseMapEntity;

  private final EntityDeletionOrUpdateAdapter<BaseMapEntity> __deletionAdapterOfBaseMapEntity;

  private final EntityDeletionOrUpdateAdapter<BaseMapEntity> __updateAdapterOfBaseMapEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBySurveyId;

  public BaseMapDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBaseMapEntity = new EntityInsertionAdapter<BaseMapEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `offline_base_map_source` (`id`,`survey_id`,`url`,`type`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BaseMapEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getSurveyId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSurveyId());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, __BaseMapEntityType_enumToString(value.getType()));
        }
      }
    };
    this.__deletionAdapterOfBaseMapEntity = new EntityDeletionOrUpdateAdapter<BaseMapEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `offline_base_map_source` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BaseMapEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfBaseMapEntity = new EntityDeletionOrUpdateAdapter<BaseMapEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `offline_base_map_source` SET `id` = ?,`survey_id` = ?,`url` = ?,`type` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BaseMapEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getSurveyId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSurveyId());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUrl());
        }
        if (value.getType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, __BaseMapEntityType_enumToString(value.getType()));
        }
        if (value.getId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteBySurveyId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM offline_base_map_source WHERE survey_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Completable insert(final BaseMapEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBaseMapEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final BaseMapEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBaseMapEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final BaseMapEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfBaseMapEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<BaseMapEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfBaseMapEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable deleteBySurveyId(final String surveyId) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBySurveyId.acquire();
        int _argIndex = 1;
        if (surveyId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, surveyId);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteBySurveyId.release(_stmt);
        }
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __BaseMapEntityType_enumToString(final BaseMapEntity.BaseMapEntityType _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case GEOJSON: return "GEOJSON";
      case IMAGE: return "IMAGE";
      case UNKNOWN: return "UNKNOWN";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }
}
