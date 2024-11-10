package net.programmierecke.radiodroid2.history;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.DataSource.Factory;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import net.programmierecke.radiodroid2.database.Converters;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TrackHistoryDao_Impl implements TrackHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TrackHistoryEntry> __insertionAdapterOfTrackHistoryEntry;

  private final EntityDeletionOrUpdateAdapter<TrackHistoryEntry> __updateAdapterOfTrackHistoryEntry;

  private final SharedSQLiteStatement __preparedStmtOfSetCurrentPlayingTrackEndTime;

  private final SharedSQLiteStatement __preparedStmtOfSetLastHistoryItemEndTimeRelative;

  private final SharedSQLiteStatement __preparedStmtOfSetTrackArtUrl;

  private final SharedSQLiteStatement __preparedStmtOfTruncateHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteHistory;

  public TrackHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrackHistoryEntry = new EntityInsertionAdapter<TrackHistoryEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `track_history` (`uid`,`station_uuid`,`station_icon_url`,`track`,`artist`,`title`,`art_url`,`start_time`,`end_time`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrackHistoryEntry value) {
        stmt.bindLong(1, value.uid);
        if (value.stationUuid == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.stationUuid);
        }
        if (value.stationIconUrl == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.stationIconUrl);
        }
        if (value.track == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.track);
        }
        if (value.artist == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.artist);
        }
        if (value.title == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.title);
        }
        if (value.artUrl == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.artUrl);
        }
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.startTime);
        if (_tmp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.endTime);
        if (_tmp_1 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, _tmp_1);
        }
      }
    };
    this.__updateAdapterOfTrackHistoryEntry = new EntityDeletionOrUpdateAdapter<TrackHistoryEntry>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `track_history` SET `uid` = ?,`station_uuid` = ?,`station_icon_url` = ?,`track` = ?,`artist` = ?,`title` = ?,`art_url` = ?,`start_time` = ?,`end_time` = ? WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TrackHistoryEntry value) {
        stmt.bindLong(1, value.uid);
        if (value.stationUuid == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.stationUuid);
        }
        if (value.stationIconUrl == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.stationIconUrl);
        }
        if (value.track == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.track);
        }
        if (value.artist == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.artist);
        }
        if (value.title == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.title);
        }
        if (value.artUrl == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.artUrl);
        }
        final Long _tmp;
        _tmp = Converters.dateToTimestamp(value.startTime);
        if (_tmp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = Converters.dateToTimestamp(value.endTime);
        if (_tmp_1 == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, _tmp_1);
        }
        stmt.bindLong(10, value.uid);
      }
    };
    this.__preparedStmtOfSetCurrentPlayingTrackEndTime = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE track_history SET end_time = ? WHERE end_time = 0";
        return _query;
      }
    };
    this.__preparedStmtOfSetLastHistoryItemEndTimeRelative = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE track_history SET end_time = start_time + ? WHERE end_time = 0";
        return _query;
      }
    };
    this.__preparedStmtOfSetTrackArtUrl = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE track_history SET art_url = ? WHERE uid = ?";
        return _query;
      }
    };
    this.__preparedStmtOfTruncateHistory = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM track_history WHERE uid < (SELECT MIN(uid) FROM (SELECT uid FROM track_history ORDER BY uid DESC LIMIT ?))";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteHistory = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM track_history";
        return _query;
      }
    };
  }

  @Override
  public void insert(final TrackHistoryEntry historyEntry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTrackHistoryEntry.insert(historyEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final TrackHistoryEntry historyEntry) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTrackHistoryEntry.handle(historyEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setCurrentPlayingTrackEndTime(final Date time) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetCurrentPlayingTrackEndTime.acquire();
    int _argIndex = 1;
    final Long _tmp;
    _tmp = Converters.dateToTimestamp(time);
    if (_tmp == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindLong(_argIndex, _tmp);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetCurrentPlayingTrackEndTime.release(_stmt);
    }
  }

  @Override
  public void setLastHistoryItemEndTimeRelative(final int deltaSeconds) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetLastHistoryItemEndTimeRelative.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, deltaSeconds);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetLastHistoryItemEndTimeRelative.release(_stmt);
    }
  }

  @Override
  public void setTrackArtUrl(final int id, final String artUrl) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetTrackArtUrl.acquire();
    int _argIndex = 1;
    if (artUrl == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, artUrl);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetTrackArtUrl.release(_stmt);
    }
  }

  @Override
  public void truncateHistory(final int limit) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfTruncateHistory.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, limit);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfTruncateHistory.release(_stmt);
    }
  }

  @Override
  public void deleteHistory() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteHistory.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteHistory.release(_stmt);
    }
  }

  @Override
  public LiveData<List<TrackHistoryEntry>> getAllHistory() {
    final String _sql = "SELECT * from track_history ORDER BY uid DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"track_history"}, false, new Callable<List<TrackHistoryEntry>>() {
      @Override
      public List<TrackHistoryEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final int _cursorIndexOfStationUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "station_uuid");
          final int _cursorIndexOfStationIconUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "station_icon_url");
          final int _cursorIndexOfTrack = CursorUtil.getColumnIndexOrThrow(_cursor, "track");
          final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfArtUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "art_url");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time");
          final List<TrackHistoryEntry> _result = new ArrayList<TrackHistoryEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TrackHistoryEntry _item;
            _item = new TrackHistoryEntry();
            _item.uid = _cursor.getInt(_cursorIndexOfUid);
            _item.stationUuid = _cursor.getString(_cursorIndexOfStationUuid);
            _item.stationIconUrl = _cursor.getString(_cursorIndexOfStationIconUrl);
            _item.track = _cursor.getString(_cursorIndexOfTrack);
            _item.artist = _cursor.getString(_cursorIndexOfArtist);
            _item.title = _cursor.getString(_cursorIndexOfTitle);
            _item.artUrl = _cursor.getString(_cursorIndexOfArtUrl);
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfStartTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfStartTime);
            }
            _item.startTime = Converters.fromTimestamp(_tmp);
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfEndTime)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfEndTime);
            }
            _item.endTime = Converters.fromTimestamp(_tmp_1);
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
  public DataSource.Factory<Integer, TrackHistoryEntry> getAllHistoryPositional() {
    final String _sql = "SELECT * FROM track_history ORDER BY uid DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, TrackHistoryEntry>() {
      @Override
      public LimitOffsetDataSource<TrackHistoryEntry> create() {
        return new LimitOffsetDataSource<TrackHistoryEntry>(__db, _statement, false , "track_history") {
          @Override
          protected List<TrackHistoryEntry> convertRows(Cursor cursor) {
            final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(cursor, "uid");
            final int _cursorIndexOfStationUuid = CursorUtil.getColumnIndexOrThrow(cursor, "station_uuid");
            final int _cursorIndexOfStationIconUrl = CursorUtil.getColumnIndexOrThrow(cursor, "station_icon_url");
            final int _cursorIndexOfTrack = CursorUtil.getColumnIndexOrThrow(cursor, "track");
            final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(cursor, "artist");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(cursor, "title");
            final int _cursorIndexOfArtUrl = CursorUtil.getColumnIndexOrThrow(cursor, "art_url");
            final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(cursor, "start_time");
            final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(cursor, "end_time");
            final List<TrackHistoryEntry> _res = new ArrayList<TrackHistoryEntry>(cursor.getCount());
            while(cursor.moveToNext()) {
              final TrackHistoryEntry _item;
              _item = new TrackHistoryEntry();
              _item.uid = cursor.getInt(_cursorIndexOfUid);
              _item.stationUuid = cursor.getString(_cursorIndexOfStationUuid);
              _item.stationIconUrl = cursor.getString(_cursorIndexOfStationIconUrl);
              _item.track = cursor.getString(_cursorIndexOfTrack);
              _item.artist = cursor.getString(_cursorIndexOfArtist);
              _item.title = cursor.getString(_cursorIndexOfTitle);
              _item.artUrl = cursor.getString(_cursorIndexOfArtUrl);
              final Long _tmp;
              if (cursor.isNull(_cursorIndexOfStartTime)) {
                _tmp = null;
              } else {
                _tmp = cursor.getLong(_cursorIndexOfStartTime);
              }
              _item.startTime = Converters.fromTimestamp(_tmp);
              final Long _tmp_1;
              if (cursor.isNull(_cursorIndexOfEndTime)) {
                _tmp_1 = null;
              } else {
                _tmp_1 = cursor.getLong(_cursorIndexOfEndTime);
              }
              _item.endTime = Converters.fromTimestamp(_tmp_1);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public TrackHistoryEntry getLastInsertedHistoryItem() {
    final String _sql = "SELECT * FROM track_history ORDER BY uid DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfStationUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "station_uuid");
      final int _cursorIndexOfStationIconUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "station_icon_url");
      final int _cursorIndexOfTrack = CursorUtil.getColumnIndexOrThrow(_cursor, "track");
      final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfArtUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "art_url");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "start_time");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "end_time");
      final TrackHistoryEntry _result;
      if(_cursor.moveToFirst()) {
        _result = new TrackHistoryEntry();
        _result.uid = _cursor.getInt(_cursorIndexOfUid);
        _result.stationUuid = _cursor.getString(_cursorIndexOfStationUuid);
        _result.stationIconUrl = _cursor.getString(_cursorIndexOfStationIconUrl);
        _result.track = _cursor.getString(_cursorIndexOfTrack);
        _result.artist = _cursor.getString(_cursorIndexOfArtist);
        _result.title = _cursor.getString(_cursorIndexOfTitle);
        _result.artUrl = _cursor.getString(_cursorIndexOfArtUrl);
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfStartTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfStartTime);
        }
        _result.startTime = Converters.fromTimestamp(_tmp);
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfEndTime)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfEndTime);
        }
        _result.endTime = Converters.fromTimestamp(_tmp_1);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
