package com.health.openscale.core.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.utils.Converters;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ScaleMeasurementDAO_Impl implements ScaleMeasurementDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ScaleMeasurement> __insertionAdapterOfScaleMeasurement;

  private final EntityDeletionOrUpdateAdapter<ScaleMeasurement> __updateAdapterOfScaleMeasurement;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ScaleMeasurementDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScaleMeasurement = new EntityInsertionAdapter<ScaleMeasurement>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `scaleMeasurements` (`id`,`userId`,`enabled`,`datetime`,`weight`,`fat`,`water`,`muscle`,`visceralFat`,`lbm`,`waist`,`hip`,`bone`,`chest`,`thigh`,`biceps`,`neck`,`caliper1`,`caliper2`,`caliper3`,`calories`,`comment`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ScaleMeasurement value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        final int _tmp;
        _tmp = value.getEnabled() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.getDateTime());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        stmt.bindDouble(5, value.getWeight());
        stmt.bindDouble(6, value.getFat());
        stmt.bindDouble(7, value.getWater());
        stmt.bindDouble(8, value.getMuscle());
        stmt.bindDouble(9, value.getVisceralFat());
        stmt.bindDouble(10, value.getLbm());
        stmt.bindDouble(11, value.getWaist());
        stmt.bindDouble(12, value.getHip());
        stmt.bindDouble(13, value.getBone());
        stmt.bindDouble(14, value.getChest());
        stmt.bindDouble(15, value.getThigh());
        stmt.bindDouble(16, value.getBiceps());
        stmt.bindDouble(17, value.getNeck());
        stmt.bindDouble(18, value.getCaliper1());
        stmt.bindDouble(19, value.getCaliper2());
        stmt.bindDouble(20, value.getCaliper3());
        stmt.bindDouble(21, value.getCalories());
        if (value.getComment() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getComment());
        }
      }
    };
    this.__updateAdapterOfScaleMeasurement = new EntityDeletionOrUpdateAdapter<ScaleMeasurement>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `scaleMeasurements` SET `id` = ?,`userId` = ?,`enabled` = ?,`datetime` = ?,`weight` = ?,`fat` = ?,`water` = ?,`muscle` = ?,`visceralFat` = ?,`lbm` = ?,`waist` = ?,`hip` = ?,`bone` = ?,`chest` = ?,`thigh` = ?,`biceps` = ?,`neck` = ?,`caliper1` = ?,`caliper2` = ?,`caliper3` = ?,`calories` = ?,`comment` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ScaleMeasurement value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        final int _tmp;
        _tmp = value.getEnabled() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.getDateTime());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        stmt.bindDouble(5, value.getWeight());
        stmt.bindDouble(6, value.getFat());
        stmt.bindDouble(7, value.getWater());
        stmt.bindDouble(8, value.getMuscle());
        stmt.bindDouble(9, value.getVisceralFat());
        stmt.bindDouble(10, value.getLbm());
        stmt.bindDouble(11, value.getWaist());
        stmt.bindDouble(12, value.getHip());
        stmt.bindDouble(13, value.getBone());
        stmt.bindDouble(14, value.getChest());
        stmt.bindDouble(15, value.getThigh());
        stmt.bindDouble(16, value.getBiceps());
        stmt.bindDouble(17, value.getNeck());
        stmt.bindDouble(18, value.getCaliper1());
        stmt.bindDouble(19, value.getCaliper2());
        stmt.bindDouble(20, value.getCaliper3());
        stmt.bindDouble(21, value.getCalories());
        if (value.getComment() == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.getComment());
        }
        stmt.bindLong(23, value.getId());
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE scaleMeasurements SET enabled = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM scaleMeasurements WHERE userId = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final ScaleMeasurement measurement) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfScaleMeasurement.insertAndReturnId(measurement);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<ScaleMeasurement> measurementList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfScaleMeasurement.insert(measurementList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final ScaleMeasurement measurement) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfScaleMeasurement.handle(measurement);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public void deleteAll(final int userId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, userId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public ScaleMeasurement get(final Date datetime, final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE datetime = ? AND userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = Converters.dateToTimestamp(datetime);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final ScaleMeasurement _result;
      if(_cursor.moveToFirst()) {
        _result = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp_1 != 0;
        _result.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_2);
        _result.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _result.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _result.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _result.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _result.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _result.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _result.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _result.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _result.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _result.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _result.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _result.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _result.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _result.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _result.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _result.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _result.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _result.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _result.setComment(_tmpComment);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ScaleMeasurement get(final int id) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final ScaleMeasurement _result;
      if(_cursor.moveToFirst()) {
        _result = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _result.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_1);
        _result.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _result.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _result.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _result.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _result.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _result.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _result.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _result.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _result.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _result.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _result.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _result.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _result.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _result.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _result.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _result.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _result.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _result.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _result.setComment(_tmpComment);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ScaleMeasurement getPrevious(final int id, final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE datetime < (SELECT datetime FROM scaleMeasurements WHERE id = ?) AND userId = ? AND enabled = 1 ORDER BY datetime DESC LIMIT 0,1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final ScaleMeasurement _result;
      if(_cursor.moveToFirst()) {
        _result = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _result.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_1);
        _result.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _result.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _result.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _result.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _result.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _result.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _result.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _result.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _result.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _result.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _result.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _result.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _result.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _result.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _result.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _result.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _result.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _result.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _result.setComment(_tmpComment);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ScaleMeasurement getNext(final int id, final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE datetime > (SELECT datetime FROM scaleMeasurements WHERE id = ?) AND userId = ? AND enabled = 1 LIMIT 0,1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final ScaleMeasurement _result;
      if(_cursor.moveToFirst()) {
        _result = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _result.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_1);
        _result.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _result.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _result.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _result.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _result.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _result.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _result.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _result.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _result.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _result.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _result.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _result.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _result.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _result.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _result.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _result.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _result.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _result.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _result.setComment(_tmpComment);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public long getCount(final int userId) {
    final String _sql = "SELECT count(id) FROM scaleMeasurements WHERE userId = ? AND enabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final long _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getLong(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ScaleMeasurement> getAll(final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE userId = ? AND enabled = 1 ORDER BY datetime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final List<ScaleMeasurement> _result = new ArrayList<ScaleMeasurement>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ScaleMeasurement _item;
        _item = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _item.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_1);
        _item.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _item.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _item.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _item.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _item.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _item.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _item.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _item.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _item.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _item.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _item.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _item.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _item.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _item.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _item.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _item.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _item.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _item.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _item.setComment(_tmpComment);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<ScaleMeasurement>> getAllAsLiveData(final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE userId = ? AND enabled = 1 ORDER BY datetime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"scaleMeasurements"}, false, new Callable<List<ScaleMeasurement>>() {
      @Override
      public List<ScaleMeasurement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
          final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
          final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
          final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
          final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
          final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
          final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
          final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
          final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
          final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
          final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
          final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
          final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
          final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
          final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
          final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
          final List<ScaleMeasurement> _result = new ArrayList<ScaleMeasurement>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ScaleMeasurement _item;
            _item = new ScaleMeasurement();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            _item.setUserId(_tmpUserId);
            final boolean _tmpEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEnabled);
            _tmpEnabled = _tmp != 0;
            _item.setEnabled(_tmpEnabled);
            final Date _tmpDateTime;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDateTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDateTime);
            }
            _tmpDateTime = Converters.fromTimestamp(_tmp_1);
            _item.setDateTime(_tmpDateTime);
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            _item.setWeight(_tmpWeight);
            final float _tmpFat;
            _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
            _item.setFat(_tmpFat);
            final float _tmpWater;
            _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
            _item.setWater(_tmpWater);
            final float _tmpMuscle;
            _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
            _item.setMuscle(_tmpMuscle);
            final float _tmpVisceralFat;
            _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
            _item.setVisceralFat(_tmpVisceralFat);
            final float _tmpLbm;
            _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
            _item.setLbm(_tmpLbm);
            final float _tmpWaist;
            _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
            _item.setWaist(_tmpWaist);
            final float _tmpHip;
            _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
            _item.setHip(_tmpHip);
            final float _tmpBone;
            _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
            _item.setBone(_tmpBone);
            final float _tmpChest;
            _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
            _item.setChest(_tmpChest);
            final float _tmpThigh;
            _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
            _item.setThigh(_tmpThigh);
            final float _tmpBiceps;
            _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
            _item.setBiceps(_tmpBiceps);
            final float _tmpNeck;
            _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
            _item.setNeck(_tmpNeck);
            final float _tmpCaliper1;
            _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
            _item.setCaliper1(_tmpCaliper1);
            final float _tmpCaliper2;
            _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
            _item.setCaliper2(_tmpCaliper2);
            final float _tmpCaliper3;
            _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
            _item.setCaliper3(_tmpCaliper3);
            final float _tmpCalories;
            _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
            _item.setCalories(_tmpCalories);
            final String _tmpComment;
            _tmpComment = _cursor.getString(_cursorIndexOfComment);
            _item.setComment(_tmpComment);
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
  public List<ScaleMeasurement> getAllInRange(final Date startYear, final Date endYear,
      final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE datetime >= ? AND datetime < ? AND userId = ? AND enabled = 1 ORDER BY datetime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    final Long _tmp;
    _tmp = Converters.dateToTimestamp(startYear);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp);
    }
    _argIndex = 2;
    final Long _tmp_1;
    _tmp_1 = Converters.dateToTimestamp(endYear);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, _tmp_1);
    }
    _argIndex = 3;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final List<ScaleMeasurement> _result = new ArrayList<ScaleMeasurement>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ScaleMeasurement _item;
        _item = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp_2 != 0;
        _item.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_3;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_3);
        _item.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _item.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _item.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _item.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _item.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _item.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _item.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _item.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _item.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _item.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _item.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _item.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _item.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _item.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _item.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _item.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _item.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _item.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _item.setComment(_tmpComment);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ScaleMeasurement getLatest(final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE userId = ? AND enabled = 1 ORDER BY datetime DESC LIMIT 0,1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final ScaleMeasurement _result;
      if(_cursor.moveToFirst()) {
        _result = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _result.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_1);
        _result.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _result.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _result.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _result.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _result.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _result.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _result.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _result.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _result.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _result.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _result.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _result.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _result.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _result.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _result.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _result.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _result.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _result.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _result.setComment(_tmpComment);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ScaleMeasurement getFirst(final int userId) {
    final String _sql = "SELECT * FROM scaleMeasurements WHERE userId = ? AND enabled = 1 ORDER BY datetime ASC LIMIT 0,1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "enabled");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "datetime");
      final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
      final int _cursorIndexOfFat = CursorUtil.getColumnIndexOrThrow(_cursor, "fat");
      final int _cursorIndexOfWater = CursorUtil.getColumnIndexOrThrow(_cursor, "water");
      final int _cursorIndexOfMuscle = CursorUtil.getColumnIndexOrThrow(_cursor, "muscle");
      final int _cursorIndexOfVisceralFat = CursorUtil.getColumnIndexOrThrow(_cursor, "visceralFat");
      final int _cursorIndexOfLbm = CursorUtil.getColumnIndexOrThrow(_cursor, "lbm");
      final int _cursorIndexOfWaist = CursorUtil.getColumnIndexOrThrow(_cursor, "waist");
      final int _cursorIndexOfHip = CursorUtil.getColumnIndexOrThrow(_cursor, "hip");
      final int _cursorIndexOfBone = CursorUtil.getColumnIndexOrThrow(_cursor, "bone");
      final int _cursorIndexOfChest = CursorUtil.getColumnIndexOrThrow(_cursor, "chest");
      final int _cursorIndexOfThigh = CursorUtil.getColumnIndexOrThrow(_cursor, "thigh");
      final int _cursorIndexOfBiceps = CursorUtil.getColumnIndexOrThrow(_cursor, "biceps");
      final int _cursorIndexOfNeck = CursorUtil.getColumnIndexOrThrow(_cursor, "neck");
      final int _cursorIndexOfCaliper1 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper1");
      final int _cursorIndexOfCaliper2 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper2");
      final int _cursorIndexOfCaliper3 = CursorUtil.getColumnIndexOrThrow(_cursor, "caliper3");
      final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final ScaleMeasurement _result;
      if(_cursor.moveToFirst()) {
        _result = new ScaleMeasurement();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final boolean _tmpEnabled;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEnabled);
        _tmpEnabled = _tmp != 0;
        _result.setEnabled(_tmpEnabled);
        final Date _tmpDateTime;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDateTime);
        }
        _tmpDateTime = Converters.fromTimestamp(_tmp_1);
        _result.setDateTime(_tmpDateTime);
        final float _tmpWeight;
        _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
        _result.setWeight(_tmpWeight);
        final float _tmpFat;
        _tmpFat = _cursor.getFloat(_cursorIndexOfFat);
        _result.setFat(_tmpFat);
        final float _tmpWater;
        _tmpWater = _cursor.getFloat(_cursorIndexOfWater);
        _result.setWater(_tmpWater);
        final float _tmpMuscle;
        _tmpMuscle = _cursor.getFloat(_cursorIndexOfMuscle);
        _result.setMuscle(_tmpMuscle);
        final float _tmpVisceralFat;
        _tmpVisceralFat = _cursor.getFloat(_cursorIndexOfVisceralFat);
        _result.setVisceralFat(_tmpVisceralFat);
        final float _tmpLbm;
        _tmpLbm = _cursor.getFloat(_cursorIndexOfLbm);
        _result.setLbm(_tmpLbm);
        final float _tmpWaist;
        _tmpWaist = _cursor.getFloat(_cursorIndexOfWaist);
        _result.setWaist(_tmpWaist);
        final float _tmpHip;
        _tmpHip = _cursor.getFloat(_cursorIndexOfHip);
        _result.setHip(_tmpHip);
        final float _tmpBone;
        _tmpBone = _cursor.getFloat(_cursorIndexOfBone);
        _result.setBone(_tmpBone);
        final float _tmpChest;
        _tmpChest = _cursor.getFloat(_cursorIndexOfChest);
        _result.setChest(_tmpChest);
        final float _tmpThigh;
        _tmpThigh = _cursor.getFloat(_cursorIndexOfThigh);
        _result.setThigh(_tmpThigh);
        final float _tmpBiceps;
        _tmpBiceps = _cursor.getFloat(_cursorIndexOfBiceps);
        _result.setBiceps(_tmpBiceps);
        final float _tmpNeck;
        _tmpNeck = _cursor.getFloat(_cursorIndexOfNeck);
        _result.setNeck(_tmpNeck);
        final float _tmpCaliper1;
        _tmpCaliper1 = _cursor.getFloat(_cursorIndexOfCaliper1);
        _result.setCaliper1(_tmpCaliper1);
        final float _tmpCaliper2;
        _tmpCaliper2 = _cursor.getFloat(_cursorIndexOfCaliper2);
        _result.setCaliper2(_tmpCaliper2);
        final float _tmpCaliper3;
        _tmpCaliper3 = _cursor.getFloat(_cursorIndexOfCaliper3);
        _result.setCaliper3(_tmpCaliper3);
        final float _tmpCalories;
        _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
        _result.setCalories(_tmpCalories);
        final String _tmpComment;
        _tmpComment = _cursor.getString(_cursorIndexOfComment);
        _result.setComment(_tmpComment);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Cursor selectAll(final long userId) {
    final String _sql = "SELECT id as _ID, datetime, weight, fat, water, muscle FROM scaleMeasurements WHERE userId = ? AND enabled = 1 ORDER BY datetime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final Cursor _tmpResult = __db.query(_statement);
    return _tmpResult;
  }
}
