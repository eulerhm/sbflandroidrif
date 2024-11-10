package com.google.android.gnd.persistence.local.room.dao;

import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.entity.JobEntity;
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
public final class JobDao_Impl implements JobDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<JobEntity> __insertionAdapterOfJobEntity;

  private final EntityDeletionOrUpdateAdapter<JobEntity> __deletionAdapterOfJobEntity;

  private final EntityDeletionOrUpdateAdapter<JobEntity> __updateAdapterOfJobEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBySurveyId;

  public JobDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfJobEntity = new EntityInsertionAdapter<JobEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `job` (`id`,`name`,`survey_id`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, JobEntity value) {
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
        if (value.getSurveyId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSurveyId());
        }
      }
    };
    this.__deletionAdapterOfJobEntity = new EntityDeletionOrUpdateAdapter<JobEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `job` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, JobEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfJobEntity = new EntityDeletionOrUpdateAdapter<JobEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `job` SET `id` = ?,`name` = ?,`survey_id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, JobEntity value) {
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
        if (value.getSurveyId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSurveyId());
        }
        if (value.getId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteBySurveyId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM job WHERE survey_id = ?";
        return _query;
      }
    };
  }

  @Override
  public Completable insert(final JobEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfJobEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final JobEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfJobEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final JobEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfJobEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<JobEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfJobEntity.handleMultiple(entities);
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
}
