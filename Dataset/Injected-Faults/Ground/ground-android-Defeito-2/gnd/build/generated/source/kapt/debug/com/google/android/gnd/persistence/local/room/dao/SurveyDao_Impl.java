package com.google.android.gnd.persistence.local.room.dao;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gnd.persistence.local.room.converter.JsonObjectTypeConverter;
import com.google.android.gnd.persistence.local.room.entity.BaseMapEntity;
import com.google.android.gnd.persistence.local.room.entity.FieldEntity;
import com.google.android.gnd.persistence.local.room.entity.JobEntity;
import com.google.android.gnd.persistence.local.room.entity.MultipleChoiceEntity;
import com.google.android.gnd.persistence.local.room.entity.OptionEntity;
import com.google.android.gnd.persistence.local.room.entity.SurveyEntity;
import com.google.android.gnd.persistence.local.room.entity.TaskEntity;
import com.google.android.gnd.persistence.local.room.models.FieldEntityType;
import com.google.android.gnd.persistence.local.room.models.MultipleChoiceEntityType;
import com.google.android.gnd.persistence.local.room.models.StepEntityType;
import com.google.android.gnd.persistence.local.room.relations.FieldEntityAndRelations;
import com.google.android.gnd.persistence.local.room.relations.JobEntityAndRelations;
import com.google.android.gnd.persistence.local.room.relations.SurveyEntityAndRelations;
import com.google.android.gnd.persistence.local.room.relations.TaskEntityAndRelations;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import org.json.JSONObject;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SurveyDao_Impl implements SurveyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SurveyEntity> __insertionAdapterOfSurveyEntity;

  private final EntityDeletionOrUpdateAdapter<SurveyEntity> __deletionAdapterOfSurveyEntity;

  private final EntityDeletionOrUpdateAdapter<SurveyEntity> __updateAdapterOfSurveyEntity;

  public SurveyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSurveyEntity = new EntityInsertionAdapter<SurveyEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `survey` (`id`,`title`,`description`,`acl`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SurveyEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        final String _tmp;
        _tmp = JsonObjectTypeConverter.toString(value.getAcl());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp);
        }
      }
    };
    this.__deletionAdapterOfSurveyEntity = new EntityDeletionOrUpdateAdapter<SurveyEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `survey` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SurveyEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfSurveyEntity = new EntityDeletionOrUpdateAdapter<SurveyEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `survey` SET `id` = ?,`title` = ?,`description` = ?,`acl` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SurveyEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        final String _tmp;
        _tmp = JsonObjectTypeConverter.toString(value.getAcl());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp);
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
  public Completable insert(final SurveyEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSurveyEntity.insert(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final SurveyEntity entity) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSurveyEntity.handle(entity);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final SurveyEntity entity) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfSurveyEntity.handle(entity);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable updateAll(final List<SurveyEntity> entities) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSurveyEntity.handleMultiple(entities);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<SurveyEntityAndRelations>> getAllSurveys() {
    final String _sql = "SELECT * FROM survey";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createSingle(new Callable<List<SurveyEntityAndRelations>>() {
      @Override
      public List<SurveyEntityAndRelations> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfAcl = CursorUtil.getColumnIndexOrThrow(_cursor, "acl");
            final ArrayMap<String, ArrayList<JobEntityAndRelations>> _collectionJobEntityAndRelations = new ArrayMap<String, ArrayList<JobEntityAndRelations>>();
            final ArrayMap<String, ArrayList<BaseMapEntity>> _collectionBaseMapEntityAndRelations = new ArrayMap<String, ArrayList<BaseMapEntity>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey = _cursor.getString(_cursorIndexOfId);
                ArrayList<JobEntityAndRelations> _tmpJobEntityAndRelationsCollection = _collectionJobEntityAndRelations.get(_tmpKey);
                if (_tmpJobEntityAndRelationsCollection == null) {
                  _tmpJobEntityAndRelationsCollection = new ArrayList<JobEntityAndRelations>();
                  _collectionJobEntityAndRelations.put(_tmpKey, _tmpJobEntityAndRelationsCollection);
                }
              }
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                ArrayList<BaseMapEntity> _tmpBaseMapEntityAndRelationsCollection = _collectionBaseMapEntityAndRelations.get(_tmpKey_1);
                if (_tmpBaseMapEntityAndRelationsCollection == null) {
                  _tmpBaseMapEntityAndRelationsCollection = new ArrayList<BaseMapEntity>();
                  _collectionBaseMapEntityAndRelations.put(_tmpKey_1, _tmpBaseMapEntityAndRelationsCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipjobAscomGoogleAndroidGndPersistenceLocalRoomRelationsJobEntityAndRelations(_collectionJobEntityAndRelations);
            __fetchRelationshipofflineBaseMapSourceAscomGoogleAndroidGndPersistenceLocalRoomEntityBaseMapEntity(_collectionBaseMapEntityAndRelations);
            final List<SurveyEntityAndRelations> _result = new ArrayList<SurveyEntityAndRelations>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final SurveyEntityAndRelations _item;
              final SurveyEntity _tmpSurveyEntity;
              if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfTitle) && _cursor.isNull(_cursorIndexOfDescription) && _cursor.isNull(_cursorIndexOfAcl))) {
                final String _tmpId;
                if (_cursor.isNull(_cursorIndexOfId)) {
                  _tmpId = null;
                } else {
                  _tmpId = _cursor.getString(_cursorIndexOfId);
                }
                final String _tmpTitle;
                if (_cursor.isNull(_cursorIndexOfTitle)) {
                  _tmpTitle = null;
                } else {
                  _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                }
                final String _tmpDescription;
                if (_cursor.isNull(_cursorIndexOfDescription)) {
                  _tmpDescription = null;
                } else {
                  _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
                }
                final JSONObject _tmpAcl;
                final String _tmp;
                if (_cursor.isNull(_cursorIndexOfAcl)) {
                  _tmp = null;
                } else {
                  _tmp = _cursor.getString(_cursorIndexOfAcl);
                }
                _tmpAcl = JsonObjectTypeConverter.fromString(_tmp);
                _tmpSurveyEntity = SurveyEntity.create(_tmpId,_tmpTitle,_tmpDescription,_tmpAcl);
              }  else  {
                _tmpSurveyEntity = null;
              }
              ArrayList<JobEntityAndRelations> _tmpJobEntityAndRelationsCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                _tmpJobEntityAndRelationsCollection_1 = _collectionJobEntityAndRelations.get(_tmpKey_2);
              }
              if (_tmpJobEntityAndRelationsCollection_1 == null) {
                _tmpJobEntityAndRelationsCollection_1 = new ArrayList<JobEntityAndRelations>();
              }
              ArrayList<BaseMapEntity> _tmpBaseMapEntityAndRelationsCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                _tmpBaseMapEntityAndRelationsCollection_1 = _collectionBaseMapEntityAndRelations.get(_tmpKey_3);
              }
              if (_tmpBaseMapEntityAndRelationsCollection_1 == null) {
                _tmpBaseMapEntityAndRelationsCollection_1 = new ArrayList<BaseMapEntity>();
              }
              _item = new SurveyEntityAndRelations();
              _item.surveyEntity = _tmpSurveyEntity;
              _item.jobEntityAndRelations = _tmpJobEntityAndRelationsCollection_1;
              _item.baseMapEntityAndRelations = _tmpBaseMapEntityAndRelationsCollection_1;
              _result.add(_item);
            }
            if(_result == null) {
              throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Maybe<SurveyEntityAndRelations> getSurveyById(final String id) {
    final String _sql = "SELECT * FROM survey WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return Maybe.fromCallable(new Callable<SurveyEntityAndRelations>() {
      @Override
      public SurveyEntityAndRelations call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfAcl = CursorUtil.getColumnIndexOrThrow(_cursor, "acl");
            final ArrayMap<String, ArrayList<JobEntityAndRelations>> _collectionJobEntityAndRelations = new ArrayMap<String, ArrayList<JobEntityAndRelations>>();
            final ArrayMap<String, ArrayList<BaseMapEntity>> _collectionBaseMapEntityAndRelations = new ArrayMap<String, ArrayList<BaseMapEntity>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey = _cursor.getString(_cursorIndexOfId);
                ArrayList<JobEntityAndRelations> _tmpJobEntityAndRelationsCollection = _collectionJobEntityAndRelations.get(_tmpKey);
                if (_tmpJobEntityAndRelationsCollection == null) {
                  _tmpJobEntityAndRelationsCollection = new ArrayList<JobEntityAndRelations>();
                  _collectionJobEntityAndRelations.put(_tmpKey, _tmpJobEntityAndRelationsCollection);
                }
              }
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
                ArrayList<BaseMapEntity> _tmpBaseMapEntityAndRelationsCollection = _collectionBaseMapEntityAndRelations.get(_tmpKey_1);
                if (_tmpBaseMapEntityAndRelationsCollection == null) {
                  _tmpBaseMapEntityAndRelationsCollection = new ArrayList<BaseMapEntity>();
                  _collectionBaseMapEntityAndRelations.put(_tmpKey_1, _tmpBaseMapEntityAndRelationsCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipjobAscomGoogleAndroidGndPersistenceLocalRoomRelationsJobEntityAndRelations(_collectionJobEntityAndRelations);
            __fetchRelationshipofflineBaseMapSourceAscomGoogleAndroidGndPersistenceLocalRoomEntityBaseMapEntity(_collectionBaseMapEntityAndRelations);
            final SurveyEntityAndRelations _result;
            if(_cursor.moveToFirst()) {
              final SurveyEntity _tmpSurveyEntity;
              if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfTitle) && _cursor.isNull(_cursorIndexOfDescription) && _cursor.isNull(_cursorIndexOfAcl))) {
                final String _tmpId;
                if (_cursor.isNull(_cursorIndexOfId)) {
                  _tmpId = null;
                } else {
                  _tmpId = _cursor.getString(_cursorIndexOfId);
                }
                final String _tmpTitle;
                if (_cursor.isNull(_cursorIndexOfTitle)) {
                  _tmpTitle = null;
                } else {
                  _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                }
                final String _tmpDescription;
                if (_cursor.isNull(_cursorIndexOfDescription)) {
                  _tmpDescription = null;
                } else {
                  _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
                }
                final JSONObject _tmpAcl;
                final String _tmp;
                if (_cursor.isNull(_cursorIndexOfAcl)) {
                  _tmp = null;
                } else {
                  _tmp = _cursor.getString(_cursorIndexOfAcl);
                }
                _tmpAcl = JsonObjectTypeConverter.fromString(_tmp);
                _tmpSurveyEntity = SurveyEntity.create(_tmpId,_tmpTitle,_tmpDescription,_tmpAcl);
              }  else  {
                _tmpSurveyEntity = null;
              }
              ArrayList<JobEntityAndRelations> _tmpJobEntityAndRelationsCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
                _tmpJobEntityAndRelationsCollection_1 = _collectionJobEntityAndRelations.get(_tmpKey_2);
              }
              if (_tmpJobEntityAndRelationsCollection_1 == null) {
                _tmpJobEntityAndRelationsCollection_1 = new ArrayList<JobEntityAndRelations>();
              }
              ArrayList<BaseMapEntity> _tmpBaseMapEntityAndRelationsCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfId)) {
                final String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
                _tmpBaseMapEntityAndRelationsCollection_1 = _collectionBaseMapEntityAndRelations.get(_tmpKey_3);
              }
              if (_tmpBaseMapEntityAndRelationsCollection_1 == null) {
                _tmpBaseMapEntityAndRelationsCollection_1 = new ArrayList<BaseMapEntity>();
              }
              _result = new SurveyEntityAndRelations();
              _result.surveyEntity = _tmpSurveyEntity;
              _result.jobEntityAndRelations = _tmpJobEntityAndRelationsCollection_1;
              _result.baseMapEntityAndRelations = _tmpBaseMapEntityAndRelationsCollection_1;
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipmultipleChoiceAscomGoogleAndroidGndPersistenceLocalRoomEntityMultipleChoiceEntity(
      final ArrayMap<String, ArrayList<MultipleChoiceEntity>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<MultipleChoiceEntity>> _tmpInnerMap = new ArrayMap<String, ArrayList<MultipleChoiceEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipmultipleChoiceAscomGoogleAndroidGndPersistenceLocalRoomEntityMultipleChoiceEntity(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<MultipleChoiceEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipmultipleChoiceAscomGoogleAndroidGndPersistenceLocalRoomEntityMultipleChoiceEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `type`,`field_id` FROM `multiple_choice` WHERE `field_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "field_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfFieldId = CursorUtil.getColumnIndexOrThrow(_cursor, "field_id");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey = _cursor.getString(_itemKeyIndex);
          ArrayList<MultipleChoiceEntity> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final MultipleChoiceEntity _item_1;
            final MultipleChoiceEntityType _tmpType;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfType);
            _tmpType = MultipleChoiceEntityType.fromInt(_tmp);
            final String _tmpFieldId;
            if (_cursor.isNull(_cursorIndexOfFieldId)) {
              _tmpFieldId = null;
            } else {
              _tmpFieldId = _cursor.getString(_cursorIndexOfFieldId);
            }
            _item_1 = MultipleChoiceEntity.create(_tmpType,_tmpFieldId);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipoptionAscomGoogleAndroidGndPersistenceLocalRoomEntityOptionEntity(
      final ArrayMap<String, ArrayList<OptionEntity>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<OptionEntity>> _tmpInnerMap = new ArrayMap<String, ArrayList<OptionEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipoptionAscomGoogleAndroidGndPersistenceLocalRoomEntityOptionEntity(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<OptionEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipoptionAscomGoogleAndroidGndPersistenceLocalRoomEntityOptionEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`code`,`label`,`field_id` FROM `option` WHERE `field_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "field_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
      final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
      final int _cursorIndexOfFieldId = CursorUtil.getColumnIndexOrThrow(_cursor, "field_id");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey = _cursor.getString(_itemKeyIndex);
          ArrayList<OptionEntity> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final OptionEntity _item_1;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpCode;
            if (_cursor.isNull(_cursorIndexOfCode)) {
              _tmpCode = null;
            } else {
              _tmpCode = _cursor.getString(_cursorIndexOfCode);
            }
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            final String _tmpFieldId;
            if (_cursor.isNull(_cursorIndexOfFieldId)) {
              _tmpFieldId = null;
            } else {
              _tmpFieldId = _cursor.getString(_cursorIndexOfFieldId);
            }
            _item_1 = OptionEntity.create(_tmpId,_tmpCode,_tmpLabel,_tmpFieldId);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipfieldAscomGoogleAndroidGndPersistenceLocalRoomRelationsFieldEntityAndRelations(
      final ArrayMap<String, ArrayList<FieldEntityAndRelations>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<FieldEntityAndRelations>> _tmpInnerMap = new ArrayMap<String, ArrayList<FieldEntityAndRelations>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipfieldAscomGoogleAndroidGndPersistenceLocalRoomRelationsFieldEntityAndRelations(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<FieldEntityAndRelations>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipfieldAscomGoogleAndroidGndPersistenceLocalRoomRelationsFieldEntityAndRelations(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`index`,`step_type`,`field_type`,`label`,`is_required`,`task_id` FROM `field` WHERE `task_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, true, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "task_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "index");
      final int _cursorIndexOfStepType = CursorUtil.getColumnIndexOrThrow(_cursor, "step_type");
      final int _cursorIndexOfFieldType = CursorUtil.getColumnIndexOrThrow(_cursor, "field_type");
      final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
      final int _cursorIndexOfRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "is_required");
      final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "task_id");
      final ArrayMap<String, ArrayList<MultipleChoiceEntity>> _collectionMultipleChoiceEntities = new ArrayMap<String, ArrayList<MultipleChoiceEntity>>();
      final ArrayMap<String, ArrayList<OptionEntity>> _collectionOptionEntities = new ArrayMap<String, ArrayList<OptionEntity>>();
      while (_cursor.moveToNext()) {
        if (!_cursor.isNull(_cursorIndexOfId)) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfId);
          ArrayList<MultipleChoiceEntity> _tmpMultipleChoiceEntitiesCollection = _collectionMultipleChoiceEntities.get(_tmpKey);
          if (_tmpMultipleChoiceEntitiesCollection == null) {
            _tmpMultipleChoiceEntitiesCollection = new ArrayList<MultipleChoiceEntity>();
            _collectionMultipleChoiceEntities.put(_tmpKey, _tmpMultipleChoiceEntitiesCollection);
          }
        }
        if (!_cursor.isNull(_cursorIndexOfId)) {
          final String _tmpKey_1 = _cursor.getString(_cursorIndexOfId);
          ArrayList<OptionEntity> _tmpOptionEntitiesCollection = _collectionOptionEntities.get(_tmpKey_1);
          if (_tmpOptionEntitiesCollection == null) {
            _tmpOptionEntitiesCollection = new ArrayList<OptionEntity>();
            _collectionOptionEntities.put(_tmpKey_1, _tmpOptionEntitiesCollection);
          }
        }
      }
      _cursor.moveToPosition(-1);
      __fetchRelationshipmultipleChoiceAscomGoogleAndroidGndPersistenceLocalRoomEntityMultipleChoiceEntity(_collectionMultipleChoiceEntities);
      __fetchRelationshipoptionAscomGoogleAndroidGndPersistenceLocalRoomEntityOptionEntity(_collectionOptionEntities);
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey_2 = _cursor.getString(_itemKeyIndex);
          ArrayList<FieldEntityAndRelations> _tmpRelation = _map.get(_tmpKey_2);
          if (_tmpRelation != null) {
            final FieldEntityAndRelations _item_1;
            final FieldEntity _tmpFieldEntity;
            if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfIndex) && _cursor.isNull(_cursorIndexOfStepType) && _cursor.isNull(_cursorIndexOfFieldType) && _cursor.isNull(_cursorIndexOfLabel) && _cursor.isNull(_cursorIndexOfRequired) && _cursor.isNull(_cursorIndexOfTaskId))) {
              final String _tmpId;
              if (_cursor.isNull(_cursorIndexOfId)) {
                _tmpId = null;
              } else {
                _tmpId = _cursor.getString(_cursorIndexOfId);
              }
              final int _tmpIndex_1;
              _tmpIndex_1 = _cursor.getInt(_cursorIndexOfIndex);
              final StepEntityType _tmpStepType;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfStepType);
              _tmpStepType = StepEntityType.fromInt(_tmp);
              final FieldEntityType _tmpFieldType;
              final int _tmp_1;
              _tmp_1 = _cursor.getInt(_cursorIndexOfFieldType);
              _tmpFieldType = FieldEntityType.fromInt(_tmp_1);
              final String _tmpLabel;
              if (_cursor.isNull(_cursorIndexOfLabel)) {
                _tmpLabel = null;
              } else {
                _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
              }
              final boolean _tmpRequired;
              final int _tmp_2;
              _tmp_2 = _cursor.getInt(_cursorIndexOfRequired);
              _tmpRequired = _tmp_2 != 0;
              final String _tmpTaskId;
              if (_cursor.isNull(_cursorIndexOfTaskId)) {
                _tmpTaskId = null;
              } else {
                _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
              }
              _tmpFieldEntity = FieldEntity.create(_tmpId,_tmpIndex_1,_tmpStepType,_tmpFieldType,_tmpLabel,_tmpRequired,_tmpTaskId);
            }  else  {
              _tmpFieldEntity = null;
            }
            ArrayList<MultipleChoiceEntity> _tmpMultipleChoiceEntitiesCollection_1 = null;
            if (!_cursor.isNull(_cursorIndexOfId)) {
              final String _tmpKey_3 = _cursor.getString(_cursorIndexOfId);
              _tmpMultipleChoiceEntitiesCollection_1 = _collectionMultipleChoiceEntities.get(_tmpKey_3);
            }
            if (_tmpMultipleChoiceEntitiesCollection_1 == null) {
              _tmpMultipleChoiceEntitiesCollection_1 = new ArrayList<MultipleChoiceEntity>();
            }
            ArrayList<OptionEntity> _tmpOptionEntitiesCollection_1 = null;
            if (!_cursor.isNull(_cursorIndexOfId)) {
              final String _tmpKey_4 = _cursor.getString(_cursorIndexOfId);
              _tmpOptionEntitiesCollection_1 = _collectionOptionEntities.get(_tmpKey_4);
            }
            if (_tmpOptionEntitiesCollection_1 == null) {
              _tmpOptionEntitiesCollection_1 = new ArrayList<OptionEntity>();
            }
            _item_1 = new FieldEntityAndRelations();
            _item_1.fieldEntity = _tmpFieldEntity;
            _item_1.multipleChoiceEntities = _tmpMultipleChoiceEntitiesCollection_1;
            _item_1.optionEntities = _tmpOptionEntitiesCollection_1;
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshiptaskAscomGoogleAndroidGndPersistenceLocalRoomRelationsTaskEntityAndRelations(
      final ArrayMap<String, ArrayList<TaskEntityAndRelations>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<TaskEntityAndRelations>> _tmpInnerMap = new ArrayMap<String, ArrayList<TaskEntityAndRelations>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshiptaskAscomGoogleAndroidGndPersistenceLocalRoomRelationsTaskEntityAndRelations(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<TaskEntityAndRelations>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshiptaskAscomGoogleAndroidGndPersistenceLocalRoomRelationsTaskEntityAndRelations(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`title`,`job_id` FROM `task` WHERE `job_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, true, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "job_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfJobId = CursorUtil.getColumnIndexOrThrow(_cursor, "job_id");
      final ArrayMap<String, ArrayList<FieldEntityAndRelations>> _collectionFieldEntityAndRelations = new ArrayMap<String, ArrayList<FieldEntityAndRelations>>();
      while (_cursor.moveToNext()) {
        if (!_cursor.isNull(_cursorIndexOfId)) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfId);
          ArrayList<FieldEntityAndRelations> _tmpFieldEntityAndRelationsCollection = _collectionFieldEntityAndRelations.get(_tmpKey);
          if (_tmpFieldEntityAndRelationsCollection == null) {
            _tmpFieldEntityAndRelationsCollection = new ArrayList<FieldEntityAndRelations>();
            _collectionFieldEntityAndRelations.put(_tmpKey, _tmpFieldEntityAndRelationsCollection);
          }
        }
      }
      _cursor.moveToPosition(-1);
      __fetchRelationshipfieldAscomGoogleAndroidGndPersistenceLocalRoomRelationsFieldEntityAndRelations(_collectionFieldEntityAndRelations);
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey_1 = _cursor.getString(_itemKeyIndex);
          ArrayList<TaskEntityAndRelations> _tmpRelation = _map.get(_tmpKey_1);
          if (_tmpRelation != null) {
            final TaskEntityAndRelations _item_1;
            final TaskEntity _tmpTaskEntity;
            if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfTitle) && _cursor.isNull(_cursorIndexOfJobId))) {
              final String _tmpId;
              if (_cursor.isNull(_cursorIndexOfId)) {
                _tmpId = null;
              } else {
                _tmpId = _cursor.getString(_cursorIndexOfId);
              }
              final String _tmpTitle;
              if (_cursor.isNull(_cursorIndexOfTitle)) {
                _tmpTitle = null;
              } else {
                _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              }
              final String _tmpJobId;
              if (_cursor.isNull(_cursorIndexOfJobId)) {
                _tmpJobId = null;
              } else {
                _tmpJobId = _cursor.getString(_cursorIndexOfJobId);
              }
              _tmpTaskEntity = TaskEntity.create(_tmpId,_tmpTitle,_tmpJobId);
            }  else  {
              _tmpTaskEntity = null;
            }
            ArrayList<FieldEntityAndRelations> _tmpFieldEntityAndRelationsCollection_1 = null;
            if (!_cursor.isNull(_cursorIndexOfId)) {
              final String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
              _tmpFieldEntityAndRelationsCollection_1 = _collectionFieldEntityAndRelations.get(_tmpKey_2);
            }
            if (_tmpFieldEntityAndRelationsCollection_1 == null) {
              _tmpFieldEntityAndRelationsCollection_1 = new ArrayList<FieldEntityAndRelations>();
            }
            _item_1 = new TaskEntityAndRelations();
            _item_1.taskEntity = _tmpTaskEntity;
            _item_1.fieldEntityAndRelations = _tmpFieldEntityAndRelationsCollection_1;
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipjobAscomGoogleAndroidGndPersistenceLocalRoomRelationsJobEntityAndRelations(
      final ArrayMap<String, ArrayList<JobEntityAndRelations>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<JobEntityAndRelations>> _tmpInnerMap = new ArrayMap<String, ArrayList<JobEntityAndRelations>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipjobAscomGoogleAndroidGndPersistenceLocalRoomRelationsJobEntityAndRelations(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<JobEntityAndRelations>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipjobAscomGoogleAndroidGndPersistenceLocalRoomRelationsJobEntityAndRelations(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`name`,`survey_id` FROM `job` WHERE `survey_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, true, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "survey_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
      final ArrayMap<String, ArrayList<TaskEntityAndRelations>> _collectionTaskEntityAndRelations = new ArrayMap<String, ArrayList<TaskEntityAndRelations>>();
      while (_cursor.moveToNext()) {
        if (!_cursor.isNull(_cursorIndexOfId)) {
          final String _tmpKey = _cursor.getString(_cursorIndexOfId);
          ArrayList<TaskEntityAndRelations> _tmpTaskEntityAndRelationsCollection = _collectionTaskEntityAndRelations.get(_tmpKey);
          if (_tmpTaskEntityAndRelationsCollection == null) {
            _tmpTaskEntityAndRelationsCollection = new ArrayList<TaskEntityAndRelations>();
            _collectionTaskEntityAndRelations.put(_tmpKey, _tmpTaskEntityAndRelationsCollection);
          }
        }
      }
      _cursor.moveToPosition(-1);
      __fetchRelationshiptaskAscomGoogleAndroidGndPersistenceLocalRoomRelationsTaskEntityAndRelations(_collectionTaskEntityAndRelations);
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey_1 = _cursor.getString(_itemKeyIndex);
          ArrayList<JobEntityAndRelations> _tmpRelation = _map.get(_tmpKey_1);
          if (_tmpRelation != null) {
            final JobEntityAndRelations _item_1;
            final JobEntity _tmpJobEntity;
            if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfSurveyId))) {
              final String _tmpId;
              if (_cursor.isNull(_cursorIndexOfId)) {
                _tmpId = null;
              } else {
                _tmpId = _cursor.getString(_cursorIndexOfId);
              }
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpSurveyId;
              if (_cursor.isNull(_cursorIndexOfSurveyId)) {
                _tmpSurveyId = null;
              } else {
                _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
              }
              _tmpJobEntity = JobEntity.create(_tmpId,_tmpName,_tmpSurveyId);
            }  else  {
              _tmpJobEntity = null;
            }
            ArrayList<TaskEntityAndRelations> _tmpTaskEntityAndRelationsCollection_1 = null;
            if (!_cursor.isNull(_cursorIndexOfId)) {
              final String _tmpKey_2 = _cursor.getString(_cursorIndexOfId);
              _tmpTaskEntityAndRelationsCollection_1 = _collectionTaskEntityAndRelations.get(_tmpKey_2);
            }
            if (_tmpTaskEntityAndRelationsCollection_1 == null) {
              _tmpTaskEntityAndRelationsCollection_1 = new ArrayList<TaskEntityAndRelations>();
            }
            _item_1 = new JobEntityAndRelations();
            _item_1.jobEntity = _tmpJobEntity;
            _item_1.taskEntityAndRelations = _tmpTaskEntityAndRelationsCollection_1;
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private BaseMapEntity.BaseMapEntityType __BaseMapEntityType_stringToEnum(final String _value) {
    if (_value == null) {
      return null;
    } switch (_value) {
      case "GEOJSON": return BaseMapEntity.BaseMapEntityType.GEOJSON;
      case "IMAGE": return BaseMapEntity.BaseMapEntityType.IMAGE;
      case "UNKNOWN": return BaseMapEntity.BaseMapEntityType.UNKNOWN;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private void __fetchRelationshipofflineBaseMapSourceAscomGoogleAndroidGndPersistenceLocalRoomEntityBaseMapEntity(
      final ArrayMap<String, ArrayList<BaseMapEntity>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<BaseMapEntity>> _tmpInnerMap = new ArrayMap<String, ArrayList<BaseMapEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipofflineBaseMapSourceAscomGoogleAndroidGndPersistenceLocalRoomEntityBaseMapEntity(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<BaseMapEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipofflineBaseMapSourceAscomGoogleAndroidGndPersistenceLocalRoomEntityBaseMapEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`survey_id`,`url`,`type` FROM `offline_base_map_source` WHERE `survey_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "survey_id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSurveyId = CursorUtil.getColumnIndexOrThrow(_cursor, "survey_id");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey = _cursor.getString(_itemKeyIndex);
          ArrayList<BaseMapEntity> _tmpRelation = _map.get(_tmpKey);
          if (_tmpRelation != null) {
            final BaseMapEntity _item_1;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpSurveyId;
            if (_cursor.isNull(_cursorIndexOfSurveyId)) {
              _tmpSurveyId = null;
            } else {
              _tmpSurveyId = _cursor.getString(_cursorIndexOfSurveyId);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final BaseMapEntity.BaseMapEntityType _tmpType;
            _tmpType = __BaseMapEntityType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            _item_1 = BaseMapEntity.create(_tmpId,_tmpSurveyId,_tmpUrl,_tmpType);
            _tmpRelation.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
