package fr.free.nrw.commons.contributions;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import androidx.paging.DataSource;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.db.Converters;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.upload.WikidataPlace;
import fr.free.nrw.commons.upload.structure.depictions.DepictedItem;
import io.reactivex.Single;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ContributionDao_Impl extends ContributionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Contribution> __insertionAdapterOfContribution;

  private final EntityDeletionOrUpdateAdapter<Contribution> __deletionAdapterOfContribution;

  private final EntityDeletionOrUpdateAdapter<Contribution> __updateAdapterOfContribution;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ContributionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContribution = new EntityInsertionAdapter<Contribution>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `contribution` (`pageId`,`state`,`transferred`,`decimalCoords`,`dateCreatedSource`,`wikidataPlace`,`chunkInfo`,`depictedItems`,`mimeType`,`localUri`,`dataLength`,`dateCreated`,`dateCreatedString`,`dateModified`,`hasInvalidLocation`,`contentUri`,`countryCode`,`imageSHA1`,`retries`,`media_pageId`,`media_thumbUrl`,`media_imageUrl`,`media_filename`,`media_fallbackDescription`,`media_dateUploaded`,`media_license`,`media_licenseUrl`,`media_author`,`media_user`,`media_categories`,`media_coordinates`,`media_captions`,`media_descriptions`,`media_depictionIds`,`media_categoriesHiddenStatus`,`media_addedCategories`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contribution value) {
        if (value.getPageId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPageId());
        }
        stmt.bindLong(2, value.getState());
        stmt.bindLong(3, value.getTransferred());
        if (value.getDecimalCoords() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDecimalCoords());
        }
        if (value.getDateCreatedSource() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDateCreatedSource());
        }
        final String _tmp = Converters.wikidataPlaceToString(value.getWikidataPlace());
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp);
        }
        final String _tmp_1 = Converters.chunkInfoToString(value.getChunkInfo());
        if (_tmp_1 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_1);
        }
        final String _tmp_2 = Converters.depictionListToString(value.getDepictedItems());
        if (_tmp_2 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp_2);
        }
        if (value.getMimeType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMimeType());
        }
        final String _tmp_3 = Converters.uriToString(value.getLocalUri());
        if (_tmp_3 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp_3);
        }
        stmt.bindLong(11, value.getDataLength());
        final Long _tmp_4 = Converters.dateToTimestamp(value.getDateCreated());
        if (_tmp_4 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, _tmp_4);
        }
        if (value.getDateCreatedString() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getDateCreatedString());
        }
        final Long _tmp_5 = Converters.dateToTimestamp(value.getDateModified());
        if (_tmp_5 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindLong(14, _tmp_5);
        }
        stmt.bindLong(15, value.getHasInvalidLocation());
        final String _tmp_6 = Converters.uriToString(value.getContentUri());
        if (_tmp_6 == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, _tmp_6);
        }
        if (value.getCountryCode() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getCountryCode());
        }
        if (value.getImageSHA1() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getImageSHA1());
        }
        stmt.bindLong(19, value.getRetries());
        final Media _tmpMedia = value.getMedia();
        if (_tmpMedia != null) {
          if (_tmpMedia.getPageId() == null) {
            stmt.bindNull(20);
          } else {
            stmt.bindString(20, _tmpMedia.getPageId());
          }
          if (_tmpMedia.getThumbUrl() == null) {
            stmt.bindNull(21);
          } else {
            stmt.bindString(21, _tmpMedia.getThumbUrl());
          }
          if (_tmpMedia.getImageUrl() == null) {
            stmt.bindNull(22);
          } else {
            stmt.bindString(22, _tmpMedia.getImageUrl());
          }
          if (_tmpMedia.getFilename() == null) {
            stmt.bindNull(23);
          } else {
            stmt.bindString(23, _tmpMedia.getFilename());
          }
          if (_tmpMedia.getFallbackDescription() == null) {
            stmt.bindNull(24);
          } else {
            stmt.bindString(24, _tmpMedia.getFallbackDescription());
          }
          final Long _tmp_7 = Converters.dateToTimestamp(_tmpMedia.getDateUploaded());
          if (_tmp_7 == null) {
            stmt.bindNull(25);
          } else {
            stmt.bindLong(25, _tmp_7);
          }
          if (_tmpMedia.getLicense() == null) {
            stmt.bindNull(26);
          } else {
            stmt.bindString(26, _tmpMedia.getLicense());
          }
          if (_tmpMedia.getLicenseUrl() == null) {
            stmt.bindNull(27);
          } else {
            stmt.bindString(27, _tmpMedia.getLicenseUrl());
          }
          if (_tmpMedia.getAuthor() == null) {
            stmt.bindNull(28);
          } else {
            stmt.bindString(28, _tmpMedia.getAuthor());
          }
          if (_tmpMedia.getUser() == null) {
            stmt.bindNull(29);
          } else {
            stmt.bindString(29, _tmpMedia.getUser());
          }
          final String _tmp_8 = Converters.listObjectToString(_tmpMedia.getCategories());
          if (_tmp_8 == null) {
            stmt.bindNull(30);
          } else {
            stmt.bindString(30, _tmp_8);
          }
          final String _tmp_9 = Converters.latlngObjectToString(_tmpMedia.getCoordinates());
          if (_tmp_9 == null) {
            stmt.bindNull(31);
          } else {
            stmt.bindString(31, _tmp_9);
          }
          final String _tmp_10 = Converters.mapObjectToString(_tmpMedia.getCaptions());
          if (_tmp_10 == null) {
            stmt.bindNull(32);
          } else {
            stmt.bindString(32, _tmp_10);
          }
          final String _tmp_11 = Converters.mapObjectToString(_tmpMedia.getDescriptions());
          if (_tmp_11 == null) {
            stmt.bindNull(33);
          } else {
            stmt.bindString(33, _tmp_11);
          }
          final String _tmp_12 = Converters.listObjectToString(_tmpMedia.getDepictionIds());
          if (_tmp_12 == null) {
            stmt.bindNull(34);
          } else {
            stmt.bindString(34, _tmp_12);
          }
          final String _tmp_13 = Converters.mapObjectToString2(_tmpMedia.getCategoriesHiddenStatus());
          if (_tmp_13 == null) {
            stmt.bindNull(35);
          } else {
            stmt.bindString(35, _tmp_13);
          }
          final String _tmp_14 = Converters.listObjectToString(_tmpMedia.getAddedCategories());
          if (_tmp_14 == null) {
            stmt.bindNull(36);
          } else {
            stmt.bindString(36, _tmp_14);
          }
        } else {
          stmt.bindNull(20);
          stmt.bindNull(21);
          stmt.bindNull(22);
          stmt.bindNull(23);
          stmt.bindNull(24);
          stmt.bindNull(25);
          stmt.bindNull(26);
          stmt.bindNull(27);
          stmt.bindNull(28);
          stmt.bindNull(29);
          stmt.bindNull(30);
          stmt.bindNull(31);
          stmt.bindNull(32);
          stmt.bindNull(33);
          stmt.bindNull(34);
          stmt.bindNull(35);
          stmt.bindNull(36);
        }
      }
    };
    this.__deletionAdapterOfContribution = new EntityDeletionOrUpdateAdapter<Contribution>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `contribution` WHERE `pageId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contribution value) {
        if (value.getPageId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPageId());
        }
      }
    };
    this.__updateAdapterOfContribution = new EntityDeletionOrUpdateAdapter<Contribution>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `contribution` SET `pageId` = ?,`state` = ?,`transferred` = ?,`decimalCoords` = ?,`dateCreatedSource` = ?,`wikidataPlace` = ?,`chunkInfo` = ?,`depictedItems` = ?,`mimeType` = ?,`localUri` = ?,`dataLength` = ?,`dateCreated` = ?,`dateCreatedString` = ?,`dateModified` = ?,`hasInvalidLocation` = ?,`contentUri` = ?,`countryCode` = ?,`imageSHA1` = ?,`retries` = ?,`media_pageId` = ?,`media_thumbUrl` = ?,`media_imageUrl` = ?,`media_filename` = ?,`media_fallbackDescription` = ?,`media_dateUploaded` = ?,`media_license` = ?,`media_licenseUrl` = ?,`media_author` = ?,`media_user` = ?,`media_categories` = ?,`media_coordinates` = ?,`media_captions` = ?,`media_descriptions` = ?,`media_depictionIds` = ?,`media_categoriesHiddenStatus` = ?,`media_addedCategories` = ? WHERE `pageId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contribution value) {
        if (value.getPageId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPageId());
        }
        stmt.bindLong(2, value.getState());
        stmt.bindLong(3, value.getTransferred());
        if (value.getDecimalCoords() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDecimalCoords());
        }
        if (value.getDateCreatedSource() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDateCreatedSource());
        }
        final String _tmp = Converters.wikidataPlaceToString(value.getWikidataPlace());
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp);
        }
        final String _tmp_1 = Converters.chunkInfoToString(value.getChunkInfo());
        if (_tmp_1 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_1);
        }
        final String _tmp_2 = Converters.depictionListToString(value.getDepictedItems());
        if (_tmp_2 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp_2);
        }
        if (value.getMimeType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMimeType());
        }
        final String _tmp_3 = Converters.uriToString(value.getLocalUri());
        if (_tmp_3 == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, _tmp_3);
        }
        stmt.bindLong(11, value.getDataLength());
        final Long _tmp_4 = Converters.dateToTimestamp(value.getDateCreated());
        if (_tmp_4 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, _tmp_4);
        }
        if (value.getDateCreatedString() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getDateCreatedString());
        }
        final Long _tmp_5 = Converters.dateToTimestamp(value.getDateModified());
        if (_tmp_5 == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindLong(14, _tmp_5);
        }
        stmt.bindLong(15, value.getHasInvalidLocation());
        final String _tmp_6 = Converters.uriToString(value.getContentUri());
        if (_tmp_6 == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, _tmp_6);
        }
        if (value.getCountryCode() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getCountryCode());
        }
        if (value.getImageSHA1() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getImageSHA1());
        }
        stmt.bindLong(19, value.getRetries());
        final Media _tmpMedia = value.getMedia();
        if (_tmpMedia != null) {
          if (_tmpMedia.getPageId() == null) {
            stmt.bindNull(20);
          } else {
            stmt.bindString(20, _tmpMedia.getPageId());
          }
          if (_tmpMedia.getThumbUrl() == null) {
            stmt.bindNull(21);
          } else {
            stmt.bindString(21, _tmpMedia.getThumbUrl());
          }
          if (_tmpMedia.getImageUrl() == null) {
            stmt.bindNull(22);
          } else {
            stmt.bindString(22, _tmpMedia.getImageUrl());
          }
          if (_tmpMedia.getFilename() == null) {
            stmt.bindNull(23);
          } else {
            stmt.bindString(23, _tmpMedia.getFilename());
          }
          if (_tmpMedia.getFallbackDescription() == null) {
            stmt.bindNull(24);
          } else {
            stmt.bindString(24, _tmpMedia.getFallbackDescription());
          }
          final Long _tmp_7 = Converters.dateToTimestamp(_tmpMedia.getDateUploaded());
          if (_tmp_7 == null) {
            stmt.bindNull(25);
          } else {
            stmt.bindLong(25, _tmp_7);
          }
          if (_tmpMedia.getLicense() == null) {
            stmt.bindNull(26);
          } else {
            stmt.bindString(26, _tmpMedia.getLicense());
          }
          if (_tmpMedia.getLicenseUrl() == null) {
            stmt.bindNull(27);
          } else {
            stmt.bindString(27, _tmpMedia.getLicenseUrl());
          }
          if (_tmpMedia.getAuthor() == null) {
            stmt.bindNull(28);
          } else {
            stmt.bindString(28, _tmpMedia.getAuthor());
          }
          if (_tmpMedia.getUser() == null) {
            stmt.bindNull(29);
          } else {
            stmt.bindString(29, _tmpMedia.getUser());
          }
          final String _tmp_8 = Converters.listObjectToString(_tmpMedia.getCategories());
          if (_tmp_8 == null) {
            stmt.bindNull(30);
          } else {
            stmt.bindString(30, _tmp_8);
          }
          final String _tmp_9 = Converters.latlngObjectToString(_tmpMedia.getCoordinates());
          if (_tmp_9 == null) {
            stmt.bindNull(31);
          } else {
            stmt.bindString(31, _tmp_9);
          }
          final String _tmp_10 = Converters.mapObjectToString(_tmpMedia.getCaptions());
          if (_tmp_10 == null) {
            stmt.bindNull(32);
          } else {
            stmt.bindString(32, _tmp_10);
          }
          final String _tmp_11 = Converters.mapObjectToString(_tmpMedia.getDescriptions());
          if (_tmp_11 == null) {
            stmt.bindNull(33);
          } else {
            stmt.bindString(33, _tmp_11);
          }
          final String _tmp_12 = Converters.listObjectToString(_tmpMedia.getDepictionIds());
          if (_tmp_12 == null) {
            stmt.bindNull(34);
          } else {
            stmt.bindString(34, _tmp_12);
          }
          final String _tmp_13 = Converters.mapObjectToString2(_tmpMedia.getCategoriesHiddenStatus());
          if (_tmp_13 == null) {
            stmt.bindNull(35);
          } else {
            stmt.bindString(35, _tmp_13);
          }
          final String _tmp_14 = Converters.listObjectToString(_tmpMedia.getAddedCategories());
          if (_tmp_14 == null) {
            stmt.bindNull(36);
          } else {
            stmt.bindString(36, _tmp_14);
          }
        } else {
          stmt.bindNull(20);
          stmt.bindNull(21);
          stmt.bindNull(22);
          stmt.bindNull(23);
          stmt.bindNull(24);
          stmt.bindNull(25);
          stmt.bindNull(26);
          stmt.bindNull(27);
          stmt.bindNull(28);
          stmt.bindNull(29);
          stmt.bindNull(30);
          stmt.bindNull(31);
          stmt.bindNull(32);
          stmt.bindNull(33);
          stmt.bindNull(34);
          stmt.bindNull(35);
          stmt.bindNull(36);
        }
        if (value.getPageId() == null) {
          stmt.bindNull(37);
        } else {
          stmt.bindString(37, value.getPageId());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Delete FROM contribution";
        return _query;
      }
    };
  }

  @Override
  public void saveSynchronous(final Contribution contribution) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContribution.insert(contribution);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Single<List<Long>> save(final List<Contribution> contribution) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfContribution.insertAndReturnIdsList(contribution);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public void deleteSynchronous(final Contribution contribution) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfContribution.handle(contribution);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateSynchronous(final Contribution contribution) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfContribution.handle(contribution);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAndSaveContribution(final Contribution oldContribution,
      final Contribution newContribution) {
    __db.beginTransaction();
    try {
      ContributionDao_Impl.super.deleteAndSaveContribution(oldContribution, newContribution);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() throws SQLiteException {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
  DataSource.Factory<Integer, Contribution> fetchContributions() {
    final String _sql = "SELECT * FROM contribution order by media_dateUploaded DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new DataSource.Factory<Integer, Contribution>() {
      @Override
      public LimitOffsetDataSource<Contribution> create() {
        return new LimitOffsetDataSource<Contribution>(__db, _statement, false, true , "contribution") {
          @Override
          protected List<Contribution> convertRows(Cursor cursor) {
            final int _cursorIndexOfPageId = CursorUtil.getColumnIndexOrThrow(cursor, "pageId");
            final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(cursor, "state");
            final int _cursorIndexOfTransferred = CursorUtil.getColumnIndexOrThrow(cursor, "transferred");
            final int _cursorIndexOfDecimalCoords = CursorUtil.getColumnIndexOrThrow(cursor, "decimalCoords");
            final int _cursorIndexOfDateCreatedSource = CursorUtil.getColumnIndexOrThrow(cursor, "dateCreatedSource");
            final int _cursorIndexOfWikidataPlace = CursorUtil.getColumnIndexOrThrow(cursor, "wikidataPlace");
            final int _cursorIndexOfChunkInfo = CursorUtil.getColumnIndexOrThrow(cursor, "chunkInfo");
            final int _cursorIndexOfDepictedItems = CursorUtil.getColumnIndexOrThrow(cursor, "depictedItems");
            final int _cursorIndexOfMimeType = CursorUtil.getColumnIndexOrThrow(cursor, "mimeType");
            final int _cursorIndexOfLocalUri = CursorUtil.getColumnIndexOrThrow(cursor, "localUri");
            final int _cursorIndexOfDataLength = CursorUtil.getColumnIndexOrThrow(cursor, "dataLength");
            final int _cursorIndexOfDateCreated = CursorUtil.getColumnIndexOrThrow(cursor, "dateCreated");
            final int _cursorIndexOfDateCreatedString = CursorUtil.getColumnIndexOrThrow(cursor, "dateCreatedString");
            final int _cursorIndexOfDateModified = CursorUtil.getColumnIndexOrThrow(cursor, "dateModified");
            final int _cursorIndexOfHasInvalidLocation = CursorUtil.getColumnIndexOrThrow(cursor, "hasInvalidLocation");
            final int _cursorIndexOfContentUri = CursorUtil.getColumnIndexOrThrow(cursor, "contentUri");
            final int _cursorIndexOfCountryCode = CursorUtil.getColumnIndexOrThrow(cursor, "countryCode");
            final int _cursorIndexOfImageSHA1 = CursorUtil.getColumnIndexOrThrow(cursor, "imageSHA1");
            final int _cursorIndexOfRetries = CursorUtil.getColumnIndexOrThrow(cursor, "retries");
            final int _cursorIndexOfPageId_1 = CursorUtil.getColumnIndexOrThrow(cursor, "media_pageId");
            final int _cursorIndexOfThumbUrl = CursorUtil.getColumnIndexOrThrow(cursor, "media_thumbUrl");
            final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(cursor, "media_imageUrl");
            final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(cursor, "media_filename");
            final int _cursorIndexOfFallbackDescription = CursorUtil.getColumnIndexOrThrow(cursor, "media_fallbackDescription");
            final int _cursorIndexOfDateUploaded = CursorUtil.getColumnIndexOrThrow(cursor, "media_dateUploaded");
            final int _cursorIndexOfLicense = CursorUtil.getColumnIndexOrThrow(cursor, "media_license");
            final int _cursorIndexOfLicenseUrl = CursorUtil.getColumnIndexOrThrow(cursor, "media_licenseUrl");
            final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(cursor, "media_author");
            final int _cursorIndexOfUser = CursorUtil.getColumnIndexOrThrow(cursor, "media_user");
            final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(cursor, "media_categories");
            final int _cursorIndexOfCoordinates = CursorUtil.getColumnIndexOrThrow(cursor, "media_coordinates");
            final int _cursorIndexOfCaptions = CursorUtil.getColumnIndexOrThrow(cursor, "media_captions");
            final int _cursorIndexOfDescriptions = CursorUtil.getColumnIndexOrThrow(cursor, "media_descriptions");
            final int _cursorIndexOfDepictionIds = CursorUtil.getColumnIndexOrThrow(cursor, "media_depictionIds");
            final int _cursorIndexOfCategoriesHiddenStatus = CursorUtil.getColumnIndexOrThrow(cursor, "media_categoriesHiddenStatus");
            final int _cursorIndexOfAddedCategories = CursorUtil.getColumnIndexOrThrow(cursor, "media_addedCategories");
            final List<Contribution> _res = new ArrayList<Contribution>(cursor.getCount());
            while(cursor.moveToNext()) {
              final Contribution _item;
              final String _tmpPageId;
              if (cursor.isNull(_cursorIndexOfPageId)) {
                _tmpPageId = null;
              } else {
                _tmpPageId = cursor.getString(_cursorIndexOfPageId);
              }
              final int _tmpState;
              _tmpState = cursor.getInt(_cursorIndexOfState);
              final long _tmpTransferred;
              _tmpTransferred = cursor.getLong(_cursorIndexOfTransferred);
              final String _tmpDecimalCoords;
              if (cursor.isNull(_cursorIndexOfDecimalCoords)) {
                _tmpDecimalCoords = null;
              } else {
                _tmpDecimalCoords = cursor.getString(_cursorIndexOfDecimalCoords);
              }
              final String _tmpDateCreatedSource;
              if (cursor.isNull(_cursorIndexOfDateCreatedSource)) {
                _tmpDateCreatedSource = null;
              } else {
                _tmpDateCreatedSource = cursor.getString(_cursorIndexOfDateCreatedSource);
              }
              final WikidataPlace _tmpWikidataPlace;
              final String _tmp;
              if (cursor.isNull(_cursorIndexOfWikidataPlace)) {
                _tmp = null;
              } else {
                _tmp = cursor.getString(_cursorIndexOfWikidataPlace);
              }
              _tmpWikidataPlace = Converters.stringToWikidataPlace(_tmp);
              final ChunkInfo _tmpChunkInfo;
              final String _tmp_1;
              if (cursor.isNull(_cursorIndexOfChunkInfo)) {
                _tmp_1 = null;
              } else {
                _tmp_1 = cursor.getString(_cursorIndexOfChunkInfo);
              }
              _tmpChunkInfo = Converters.stringToChunkInfo(_tmp_1);
              final List<DepictedItem> _tmpDepictedItems;
              final String _tmp_2;
              if (cursor.isNull(_cursorIndexOfDepictedItems)) {
                _tmp_2 = null;
              } else {
                _tmp_2 = cursor.getString(_cursorIndexOfDepictedItems);
              }
              _tmpDepictedItems = Converters.stringToList(_tmp_2);
              final String _tmpMimeType;
              if (cursor.isNull(_cursorIndexOfMimeType)) {
                _tmpMimeType = null;
              } else {
                _tmpMimeType = cursor.getString(_cursorIndexOfMimeType);
              }
              final Uri _tmpLocalUri;
              final String _tmp_3;
              if (cursor.isNull(_cursorIndexOfLocalUri)) {
                _tmp_3 = null;
              } else {
                _tmp_3 = cursor.getString(_cursorIndexOfLocalUri);
              }
              _tmpLocalUri = Converters.fromString(_tmp_3);
              final long _tmpDataLength;
              _tmpDataLength = cursor.getLong(_cursorIndexOfDataLength);
              final Date _tmpDateCreated;
              final Long _tmp_4;
              if (cursor.isNull(_cursorIndexOfDateCreated)) {
                _tmp_4 = null;
              } else {
                _tmp_4 = cursor.getLong(_cursorIndexOfDateCreated);
              }
              _tmpDateCreated = Converters.fromTimestamp(_tmp_4);
              final String _tmpDateCreatedString;
              if (cursor.isNull(_cursorIndexOfDateCreatedString)) {
                _tmpDateCreatedString = null;
              } else {
                _tmpDateCreatedString = cursor.getString(_cursorIndexOfDateCreatedString);
              }
              final Date _tmpDateModified;
              final Long _tmp_5;
              if (cursor.isNull(_cursorIndexOfDateModified)) {
                _tmp_5 = null;
              } else {
                _tmp_5 = cursor.getLong(_cursorIndexOfDateModified);
              }
              _tmpDateModified = Converters.fromTimestamp(_tmp_5);
              final int _tmpHasInvalidLocation;
              _tmpHasInvalidLocation = cursor.getInt(_cursorIndexOfHasInvalidLocation);
              final Uri _tmpContentUri;
              final String _tmp_6;
              if (cursor.isNull(_cursorIndexOfContentUri)) {
                _tmp_6 = null;
              } else {
                _tmp_6 = cursor.getString(_cursorIndexOfContentUri);
              }
              _tmpContentUri = Converters.fromString(_tmp_6);
              final String _tmpCountryCode;
              if (cursor.isNull(_cursorIndexOfCountryCode)) {
                _tmpCountryCode = null;
              } else {
                _tmpCountryCode = cursor.getString(_cursorIndexOfCountryCode);
              }
              final String _tmpImageSHA1;
              if (cursor.isNull(_cursorIndexOfImageSHA1)) {
                _tmpImageSHA1 = null;
              } else {
                _tmpImageSHA1 = cursor.getString(_cursorIndexOfImageSHA1);
              }
              final int _tmpRetries;
              _tmpRetries = cursor.getInt(_cursorIndexOfRetries);
              final Media _tmpMedia;
              final String _tmpPageId_1;
              if (cursor.isNull(_cursorIndexOfPageId_1)) {
                _tmpPageId_1 = null;
              } else {
                _tmpPageId_1 = cursor.getString(_cursorIndexOfPageId_1);
              }
              final String _tmpThumbUrl;
              if (cursor.isNull(_cursorIndexOfThumbUrl)) {
                _tmpThumbUrl = null;
              } else {
                _tmpThumbUrl = cursor.getString(_cursorIndexOfThumbUrl);
              }
              final String _tmpImageUrl;
              if (cursor.isNull(_cursorIndexOfImageUrl)) {
                _tmpImageUrl = null;
              } else {
                _tmpImageUrl = cursor.getString(_cursorIndexOfImageUrl);
              }
              final String _tmpFilename;
              if (cursor.isNull(_cursorIndexOfFilename)) {
                _tmpFilename = null;
              } else {
                _tmpFilename = cursor.getString(_cursorIndexOfFilename);
              }
              final String _tmpFallbackDescription;
              if (cursor.isNull(_cursorIndexOfFallbackDescription)) {
                _tmpFallbackDescription = null;
              } else {
                _tmpFallbackDescription = cursor.getString(_cursorIndexOfFallbackDescription);
              }
              final Date _tmpDateUploaded;
              final Long _tmp_7;
              if (cursor.isNull(_cursorIndexOfDateUploaded)) {
                _tmp_7 = null;
              } else {
                _tmp_7 = cursor.getLong(_cursorIndexOfDateUploaded);
              }
              _tmpDateUploaded = Converters.fromTimestamp(_tmp_7);
              final String _tmpLicense;
              if (cursor.isNull(_cursorIndexOfLicense)) {
                _tmpLicense = null;
              } else {
                _tmpLicense = cursor.getString(_cursorIndexOfLicense);
              }
              final String _tmpLicenseUrl;
              if (cursor.isNull(_cursorIndexOfLicenseUrl)) {
                _tmpLicenseUrl = null;
              } else {
                _tmpLicenseUrl = cursor.getString(_cursorIndexOfLicenseUrl);
              }
              final String _tmpAuthor;
              if (cursor.isNull(_cursorIndexOfAuthor)) {
                _tmpAuthor = null;
              } else {
                _tmpAuthor = cursor.getString(_cursorIndexOfAuthor);
              }
              final String _tmpUser;
              if (cursor.isNull(_cursorIndexOfUser)) {
                _tmpUser = null;
              } else {
                _tmpUser = cursor.getString(_cursorIndexOfUser);
              }
              final List<String> _tmpCategories;
              final String _tmp_8;
              if (cursor.isNull(_cursorIndexOfCategories)) {
                _tmp_8 = null;
              } else {
                _tmp_8 = cursor.getString(_cursorIndexOfCategories);
              }
              _tmpCategories = Converters.stringToListObject(_tmp_8);
              final LatLng _tmpCoordinates;
              final String _tmp_9;
              if (cursor.isNull(_cursorIndexOfCoordinates)) {
                _tmp_9 = null;
              } else {
                _tmp_9 = cursor.getString(_cursorIndexOfCoordinates);
              }
              _tmpCoordinates = Converters.stringToLatLng(_tmp_9);
              final Map<String, String> _tmpCaptions;
              final String _tmp_10;
              if (cursor.isNull(_cursorIndexOfCaptions)) {
                _tmp_10 = null;
              } else {
                _tmp_10 = cursor.getString(_cursorIndexOfCaptions);
              }
              _tmpCaptions = Converters.stringToMap(_tmp_10);
              final Map<String, String> _tmpDescriptions;
              final String _tmp_11;
              if (cursor.isNull(_cursorIndexOfDescriptions)) {
                _tmp_11 = null;
              } else {
                _tmp_11 = cursor.getString(_cursorIndexOfDescriptions);
              }
              _tmpDescriptions = Converters.stringToMap(_tmp_11);
              final List<String> _tmpDepictionIds;
              final String _tmp_12;
              if (cursor.isNull(_cursorIndexOfDepictionIds)) {
                _tmp_12 = null;
              } else {
                _tmp_12 = cursor.getString(_cursorIndexOfDepictionIds);
              }
              _tmpDepictionIds = Converters.stringToListObject(_tmp_12);
              final Map<String, Boolean> _tmpCategoriesHiddenStatus;
              final String _tmp_13;
              if (cursor.isNull(_cursorIndexOfCategoriesHiddenStatus)) {
                _tmp_13 = null;
              } else {
                _tmp_13 = cursor.getString(_cursorIndexOfCategoriesHiddenStatus);
              }
              _tmpCategoriesHiddenStatus = Converters.stringToMap2(_tmp_13);
              _tmpMedia = new Media(_tmpPageId_1,_tmpThumbUrl,_tmpImageUrl,_tmpFilename,_tmpFallbackDescription,_tmpDateUploaded,_tmpLicense,_tmpLicenseUrl,_tmpAuthor,_tmpUser,_tmpCategories,_tmpCoordinates,_tmpCaptions,_tmpDescriptions,_tmpDepictionIds,_tmpCategoriesHiddenStatus);
              final List<String> _tmpAddedCategories;
              final String _tmp_14;
              if (cursor.isNull(_cursorIndexOfAddedCategories)) {
                _tmp_14 = null;
              } else {
                _tmp_14 = cursor.getString(_cursorIndexOfAddedCategories);
              }
              _tmpAddedCategories = Converters.stringToListObject(_tmp_14);
              _tmpMedia.setAddedCategories(_tmpAddedCategories);
              _item = new Contribution(_tmpMedia,_tmpPageId,_tmpState,_tmpTransferred,_tmpDecimalCoords,_tmpDateCreatedSource,_tmpWikidataPlace,_tmpChunkInfo,_tmpDepictedItems,_tmpMimeType,_tmpLocalUri,_tmpDataLength,_tmpDateCreated,_tmpDateCreatedString,_tmpDateModified,_tmpHasInvalidLocation,_tmpContentUri,_tmpCountryCode,_tmpImageSHA1,_tmpRetries);
              _res.add(_item);
            }
            return _res;
          }
        };
      }
    };
  }

  @Override
  public List<Contribution> getContributionWithTitle(final String fileName) {
    final String _sql = "SELECT * from contribution WHERE media_filename=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (fileName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, fileName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPageId = CursorUtil.getColumnIndexOrThrow(_cursor, "pageId");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
      final int _cursorIndexOfTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "transferred");
      final int _cursorIndexOfDecimalCoords = CursorUtil.getColumnIndexOrThrow(_cursor, "decimalCoords");
      final int _cursorIndexOfDateCreatedSource = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreatedSource");
      final int _cursorIndexOfWikidataPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "wikidataPlace");
      final int _cursorIndexOfChunkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "chunkInfo");
      final int _cursorIndexOfDepictedItems = CursorUtil.getColumnIndexOrThrow(_cursor, "depictedItems");
      final int _cursorIndexOfMimeType = CursorUtil.getColumnIndexOrThrow(_cursor, "mimeType");
      final int _cursorIndexOfLocalUri = CursorUtil.getColumnIndexOrThrow(_cursor, "localUri");
      final int _cursorIndexOfDataLength = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLength");
      final int _cursorIndexOfDateCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreated");
      final int _cursorIndexOfDateCreatedString = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreatedString");
      final int _cursorIndexOfDateModified = CursorUtil.getColumnIndexOrThrow(_cursor, "dateModified");
      final int _cursorIndexOfHasInvalidLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "hasInvalidLocation");
      final int _cursorIndexOfContentUri = CursorUtil.getColumnIndexOrThrow(_cursor, "contentUri");
      final int _cursorIndexOfCountryCode = CursorUtil.getColumnIndexOrThrow(_cursor, "countryCode");
      final int _cursorIndexOfImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "imageSHA1");
      final int _cursorIndexOfRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "retries");
      final int _cursorIndexOfPageId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "media_pageId");
      final int _cursorIndexOfThumbUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_thumbUrl");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_imageUrl");
      final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "media_filename");
      final int _cursorIndexOfFallbackDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "media_fallbackDescription");
      final int _cursorIndexOfDateUploaded = CursorUtil.getColumnIndexOrThrow(_cursor, "media_dateUploaded");
      final int _cursorIndexOfLicense = CursorUtil.getColumnIndexOrThrow(_cursor, "media_license");
      final int _cursorIndexOfLicenseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_licenseUrl");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "media_author");
      final int _cursorIndexOfUser = CursorUtil.getColumnIndexOrThrow(_cursor, "media_user");
      final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "media_categories");
      final int _cursorIndexOfCoordinates = CursorUtil.getColumnIndexOrThrow(_cursor, "media_coordinates");
      final int _cursorIndexOfCaptions = CursorUtil.getColumnIndexOrThrow(_cursor, "media_captions");
      final int _cursorIndexOfDescriptions = CursorUtil.getColumnIndexOrThrow(_cursor, "media_descriptions");
      final int _cursorIndexOfDepictionIds = CursorUtil.getColumnIndexOrThrow(_cursor, "media_depictionIds");
      final int _cursorIndexOfCategoriesHiddenStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "media_categoriesHiddenStatus");
      final int _cursorIndexOfAddedCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "media_addedCategories");
      final List<Contribution> _result = new ArrayList<Contribution>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Contribution _item;
        final String _tmpPageId;
        if (_cursor.isNull(_cursorIndexOfPageId)) {
          _tmpPageId = null;
        } else {
          _tmpPageId = _cursor.getString(_cursorIndexOfPageId);
        }
        final int _tmpState;
        _tmpState = _cursor.getInt(_cursorIndexOfState);
        final long _tmpTransferred;
        _tmpTransferred = _cursor.getLong(_cursorIndexOfTransferred);
        final String _tmpDecimalCoords;
        if (_cursor.isNull(_cursorIndexOfDecimalCoords)) {
          _tmpDecimalCoords = null;
        } else {
          _tmpDecimalCoords = _cursor.getString(_cursorIndexOfDecimalCoords);
        }
        final String _tmpDateCreatedSource;
        if (_cursor.isNull(_cursorIndexOfDateCreatedSource)) {
          _tmpDateCreatedSource = null;
        } else {
          _tmpDateCreatedSource = _cursor.getString(_cursorIndexOfDateCreatedSource);
        }
        final WikidataPlace _tmpWikidataPlace;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfWikidataPlace)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfWikidataPlace);
        }
        _tmpWikidataPlace = Converters.stringToWikidataPlace(_tmp);
        final ChunkInfo _tmpChunkInfo;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfChunkInfo)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfChunkInfo);
        }
        _tmpChunkInfo = Converters.stringToChunkInfo(_tmp_1);
        final List<DepictedItem> _tmpDepictedItems;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDepictedItems)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfDepictedItems);
        }
        _tmpDepictedItems = Converters.stringToList(_tmp_2);
        final String _tmpMimeType;
        if (_cursor.isNull(_cursorIndexOfMimeType)) {
          _tmpMimeType = null;
        } else {
          _tmpMimeType = _cursor.getString(_cursorIndexOfMimeType);
        }
        final Uri _tmpLocalUri;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfLocalUri)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfLocalUri);
        }
        _tmpLocalUri = Converters.fromString(_tmp_3);
        final long _tmpDataLength;
        _tmpDataLength = _cursor.getLong(_cursorIndexOfDataLength);
        final Date _tmpDateCreated;
        final Long _tmp_4;
        if (_cursor.isNull(_cursorIndexOfDateCreated)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getLong(_cursorIndexOfDateCreated);
        }
        _tmpDateCreated = Converters.fromTimestamp(_tmp_4);
        final String _tmpDateCreatedString;
        if (_cursor.isNull(_cursorIndexOfDateCreatedString)) {
          _tmpDateCreatedString = null;
        } else {
          _tmpDateCreatedString = _cursor.getString(_cursorIndexOfDateCreatedString);
        }
        final Date _tmpDateModified;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfDateModified)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfDateModified);
        }
        _tmpDateModified = Converters.fromTimestamp(_tmp_5);
        final int _tmpHasInvalidLocation;
        _tmpHasInvalidLocation = _cursor.getInt(_cursorIndexOfHasInvalidLocation);
        final Uri _tmpContentUri;
        final String _tmp_6;
        if (_cursor.isNull(_cursorIndexOfContentUri)) {
          _tmp_6 = null;
        } else {
          _tmp_6 = _cursor.getString(_cursorIndexOfContentUri);
        }
        _tmpContentUri = Converters.fromString(_tmp_6);
        final String _tmpCountryCode;
        if (_cursor.isNull(_cursorIndexOfCountryCode)) {
          _tmpCountryCode = null;
        } else {
          _tmpCountryCode = _cursor.getString(_cursorIndexOfCountryCode);
        }
        final String _tmpImageSHA1;
        if (_cursor.isNull(_cursorIndexOfImageSHA1)) {
          _tmpImageSHA1 = null;
        } else {
          _tmpImageSHA1 = _cursor.getString(_cursorIndexOfImageSHA1);
        }
        final int _tmpRetries;
        _tmpRetries = _cursor.getInt(_cursorIndexOfRetries);
        final Media _tmpMedia;
        final String _tmpPageId_1;
        if (_cursor.isNull(_cursorIndexOfPageId_1)) {
          _tmpPageId_1 = null;
        } else {
          _tmpPageId_1 = _cursor.getString(_cursorIndexOfPageId_1);
        }
        final String _tmpThumbUrl;
        if (_cursor.isNull(_cursorIndexOfThumbUrl)) {
          _tmpThumbUrl = null;
        } else {
          _tmpThumbUrl = _cursor.getString(_cursorIndexOfThumbUrl);
        }
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final String _tmpFilename;
        if (_cursor.isNull(_cursorIndexOfFilename)) {
          _tmpFilename = null;
        } else {
          _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
        }
        final String _tmpFallbackDescription;
        if (_cursor.isNull(_cursorIndexOfFallbackDescription)) {
          _tmpFallbackDescription = null;
        } else {
          _tmpFallbackDescription = _cursor.getString(_cursorIndexOfFallbackDescription);
        }
        final Date _tmpDateUploaded;
        final Long _tmp_7;
        if (_cursor.isNull(_cursorIndexOfDateUploaded)) {
          _tmp_7 = null;
        } else {
          _tmp_7 = _cursor.getLong(_cursorIndexOfDateUploaded);
        }
        _tmpDateUploaded = Converters.fromTimestamp(_tmp_7);
        final String _tmpLicense;
        if (_cursor.isNull(_cursorIndexOfLicense)) {
          _tmpLicense = null;
        } else {
          _tmpLicense = _cursor.getString(_cursorIndexOfLicense);
        }
        final String _tmpLicenseUrl;
        if (_cursor.isNull(_cursorIndexOfLicenseUrl)) {
          _tmpLicenseUrl = null;
        } else {
          _tmpLicenseUrl = _cursor.getString(_cursorIndexOfLicenseUrl);
        }
        final String _tmpAuthor;
        if (_cursor.isNull(_cursorIndexOfAuthor)) {
          _tmpAuthor = null;
        } else {
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        }
        final String _tmpUser;
        if (_cursor.isNull(_cursorIndexOfUser)) {
          _tmpUser = null;
        } else {
          _tmpUser = _cursor.getString(_cursorIndexOfUser);
        }
        final List<String> _tmpCategories;
        final String _tmp_8;
        if (_cursor.isNull(_cursorIndexOfCategories)) {
          _tmp_8 = null;
        } else {
          _tmp_8 = _cursor.getString(_cursorIndexOfCategories);
        }
        _tmpCategories = Converters.stringToListObject(_tmp_8);
        final LatLng _tmpCoordinates;
        final String _tmp_9;
        if (_cursor.isNull(_cursorIndexOfCoordinates)) {
          _tmp_9 = null;
        } else {
          _tmp_9 = _cursor.getString(_cursorIndexOfCoordinates);
        }
        _tmpCoordinates = Converters.stringToLatLng(_tmp_9);
        final Map<String, String> _tmpCaptions;
        final String _tmp_10;
        if (_cursor.isNull(_cursorIndexOfCaptions)) {
          _tmp_10 = null;
        } else {
          _tmp_10 = _cursor.getString(_cursorIndexOfCaptions);
        }
        _tmpCaptions = Converters.stringToMap(_tmp_10);
        final Map<String, String> _tmpDescriptions;
        final String _tmp_11;
        if (_cursor.isNull(_cursorIndexOfDescriptions)) {
          _tmp_11 = null;
        } else {
          _tmp_11 = _cursor.getString(_cursorIndexOfDescriptions);
        }
        _tmpDescriptions = Converters.stringToMap(_tmp_11);
        final List<String> _tmpDepictionIds;
        final String _tmp_12;
        if (_cursor.isNull(_cursorIndexOfDepictionIds)) {
          _tmp_12 = null;
        } else {
          _tmp_12 = _cursor.getString(_cursorIndexOfDepictionIds);
        }
        _tmpDepictionIds = Converters.stringToListObject(_tmp_12);
        final Map<String, Boolean> _tmpCategoriesHiddenStatus;
        final String _tmp_13;
        if (_cursor.isNull(_cursorIndexOfCategoriesHiddenStatus)) {
          _tmp_13 = null;
        } else {
          _tmp_13 = _cursor.getString(_cursorIndexOfCategoriesHiddenStatus);
        }
        _tmpCategoriesHiddenStatus = Converters.stringToMap2(_tmp_13);
        _tmpMedia = new Media(_tmpPageId_1,_tmpThumbUrl,_tmpImageUrl,_tmpFilename,_tmpFallbackDescription,_tmpDateUploaded,_tmpLicense,_tmpLicenseUrl,_tmpAuthor,_tmpUser,_tmpCategories,_tmpCoordinates,_tmpCaptions,_tmpDescriptions,_tmpDepictionIds,_tmpCategoriesHiddenStatus);
        final List<String> _tmpAddedCategories;
        final String _tmp_14;
        if (_cursor.isNull(_cursorIndexOfAddedCategories)) {
          _tmp_14 = null;
        } else {
          _tmp_14 = _cursor.getString(_cursorIndexOfAddedCategories);
        }
        _tmpAddedCategories = Converters.stringToListObject(_tmp_14);
        _tmpMedia.setAddedCategories(_tmpAddedCategories);
        _item = new Contribution(_tmpMedia,_tmpPageId,_tmpState,_tmpTransferred,_tmpDecimalCoords,_tmpDateCreatedSource,_tmpWikidataPlace,_tmpChunkInfo,_tmpDepictedItems,_tmpMimeType,_tmpLocalUri,_tmpDataLength,_tmpDateCreated,_tmpDateCreatedString,_tmpDateModified,_tmpHasInvalidLocation,_tmpContentUri,_tmpCountryCode,_tmpImageSHA1,_tmpRetries);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Contribution getContribution(final String pageId) {
    final String _sql = "SELECT * from contribution WHERE pageId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (pageId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pageId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPageId = CursorUtil.getColumnIndexOrThrow(_cursor, "pageId");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
      final int _cursorIndexOfTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "transferred");
      final int _cursorIndexOfDecimalCoords = CursorUtil.getColumnIndexOrThrow(_cursor, "decimalCoords");
      final int _cursorIndexOfDateCreatedSource = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreatedSource");
      final int _cursorIndexOfWikidataPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "wikidataPlace");
      final int _cursorIndexOfChunkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "chunkInfo");
      final int _cursorIndexOfDepictedItems = CursorUtil.getColumnIndexOrThrow(_cursor, "depictedItems");
      final int _cursorIndexOfMimeType = CursorUtil.getColumnIndexOrThrow(_cursor, "mimeType");
      final int _cursorIndexOfLocalUri = CursorUtil.getColumnIndexOrThrow(_cursor, "localUri");
      final int _cursorIndexOfDataLength = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLength");
      final int _cursorIndexOfDateCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreated");
      final int _cursorIndexOfDateCreatedString = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreatedString");
      final int _cursorIndexOfDateModified = CursorUtil.getColumnIndexOrThrow(_cursor, "dateModified");
      final int _cursorIndexOfHasInvalidLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "hasInvalidLocation");
      final int _cursorIndexOfContentUri = CursorUtil.getColumnIndexOrThrow(_cursor, "contentUri");
      final int _cursorIndexOfCountryCode = CursorUtil.getColumnIndexOrThrow(_cursor, "countryCode");
      final int _cursorIndexOfImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "imageSHA1");
      final int _cursorIndexOfRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "retries");
      final int _cursorIndexOfPageId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "media_pageId");
      final int _cursorIndexOfThumbUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_thumbUrl");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_imageUrl");
      final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "media_filename");
      final int _cursorIndexOfFallbackDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "media_fallbackDescription");
      final int _cursorIndexOfDateUploaded = CursorUtil.getColumnIndexOrThrow(_cursor, "media_dateUploaded");
      final int _cursorIndexOfLicense = CursorUtil.getColumnIndexOrThrow(_cursor, "media_license");
      final int _cursorIndexOfLicenseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_licenseUrl");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "media_author");
      final int _cursorIndexOfUser = CursorUtil.getColumnIndexOrThrow(_cursor, "media_user");
      final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "media_categories");
      final int _cursorIndexOfCoordinates = CursorUtil.getColumnIndexOrThrow(_cursor, "media_coordinates");
      final int _cursorIndexOfCaptions = CursorUtil.getColumnIndexOrThrow(_cursor, "media_captions");
      final int _cursorIndexOfDescriptions = CursorUtil.getColumnIndexOrThrow(_cursor, "media_descriptions");
      final int _cursorIndexOfDepictionIds = CursorUtil.getColumnIndexOrThrow(_cursor, "media_depictionIds");
      final int _cursorIndexOfCategoriesHiddenStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "media_categoriesHiddenStatus");
      final int _cursorIndexOfAddedCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "media_addedCategories");
      final Contribution _result;
      if(_cursor.moveToFirst()) {
        final String _tmpPageId;
        if (_cursor.isNull(_cursorIndexOfPageId)) {
          _tmpPageId = null;
        } else {
          _tmpPageId = _cursor.getString(_cursorIndexOfPageId);
        }
        final int _tmpState;
        _tmpState = _cursor.getInt(_cursorIndexOfState);
        final long _tmpTransferred;
        _tmpTransferred = _cursor.getLong(_cursorIndexOfTransferred);
        final String _tmpDecimalCoords;
        if (_cursor.isNull(_cursorIndexOfDecimalCoords)) {
          _tmpDecimalCoords = null;
        } else {
          _tmpDecimalCoords = _cursor.getString(_cursorIndexOfDecimalCoords);
        }
        final String _tmpDateCreatedSource;
        if (_cursor.isNull(_cursorIndexOfDateCreatedSource)) {
          _tmpDateCreatedSource = null;
        } else {
          _tmpDateCreatedSource = _cursor.getString(_cursorIndexOfDateCreatedSource);
        }
        final WikidataPlace _tmpWikidataPlace;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfWikidataPlace)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfWikidataPlace);
        }
        _tmpWikidataPlace = Converters.stringToWikidataPlace(_tmp);
        final ChunkInfo _tmpChunkInfo;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfChunkInfo)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfChunkInfo);
        }
        _tmpChunkInfo = Converters.stringToChunkInfo(_tmp_1);
        final List<DepictedItem> _tmpDepictedItems;
        final String _tmp_2;
        if (_cursor.isNull(_cursorIndexOfDepictedItems)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getString(_cursorIndexOfDepictedItems);
        }
        _tmpDepictedItems = Converters.stringToList(_tmp_2);
        final String _tmpMimeType;
        if (_cursor.isNull(_cursorIndexOfMimeType)) {
          _tmpMimeType = null;
        } else {
          _tmpMimeType = _cursor.getString(_cursorIndexOfMimeType);
        }
        final Uri _tmpLocalUri;
        final String _tmp_3;
        if (_cursor.isNull(_cursorIndexOfLocalUri)) {
          _tmp_3 = null;
        } else {
          _tmp_3 = _cursor.getString(_cursorIndexOfLocalUri);
        }
        _tmpLocalUri = Converters.fromString(_tmp_3);
        final long _tmpDataLength;
        _tmpDataLength = _cursor.getLong(_cursorIndexOfDataLength);
        final Date _tmpDateCreated;
        final Long _tmp_4;
        if (_cursor.isNull(_cursorIndexOfDateCreated)) {
          _tmp_4 = null;
        } else {
          _tmp_4 = _cursor.getLong(_cursorIndexOfDateCreated);
        }
        _tmpDateCreated = Converters.fromTimestamp(_tmp_4);
        final String _tmpDateCreatedString;
        if (_cursor.isNull(_cursorIndexOfDateCreatedString)) {
          _tmpDateCreatedString = null;
        } else {
          _tmpDateCreatedString = _cursor.getString(_cursorIndexOfDateCreatedString);
        }
        final Date _tmpDateModified;
        final Long _tmp_5;
        if (_cursor.isNull(_cursorIndexOfDateModified)) {
          _tmp_5 = null;
        } else {
          _tmp_5 = _cursor.getLong(_cursorIndexOfDateModified);
        }
        _tmpDateModified = Converters.fromTimestamp(_tmp_5);
        final int _tmpHasInvalidLocation;
        _tmpHasInvalidLocation = _cursor.getInt(_cursorIndexOfHasInvalidLocation);
        final Uri _tmpContentUri;
        final String _tmp_6;
        if (_cursor.isNull(_cursorIndexOfContentUri)) {
          _tmp_6 = null;
        } else {
          _tmp_6 = _cursor.getString(_cursorIndexOfContentUri);
        }
        _tmpContentUri = Converters.fromString(_tmp_6);
        final String _tmpCountryCode;
        if (_cursor.isNull(_cursorIndexOfCountryCode)) {
          _tmpCountryCode = null;
        } else {
          _tmpCountryCode = _cursor.getString(_cursorIndexOfCountryCode);
        }
        final String _tmpImageSHA1;
        if (_cursor.isNull(_cursorIndexOfImageSHA1)) {
          _tmpImageSHA1 = null;
        } else {
          _tmpImageSHA1 = _cursor.getString(_cursorIndexOfImageSHA1);
        }
        final int _tmpRetries;
        _tmpRetries = _cursor.getInt(_cursorIndexOfRetries);
        final Media _tmpMedia;
        final String _tmpPageId_1;
        if (_cursor.isNull(_cursorIndexOfPageId_1)) {
          _tmpPageId_1 = null;
        } else {
          _tmpPageId_1 = _cursor.getString(_cursorIndexOfPageId_1);
        }
        final String _tmpThumbUrl;
        if (_cursor.isNull(_cursorIndexOfThumbUrl)) {
          _tmpThumbUrl = null;
        } else {
          _tmpThumbUrl = _cursor.getString(_cursorIndexOfThumbUrl);
        }
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final String _tmpFilename;
        if (_cursor.isNull(_cursorIndexOfFilename)) {
          _tmpFilename = null;
        } else {
          _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
        }
        final String _tmpFallbackDescription;
        if (_cursor.isNull(_cursorIndexOfFallbackDescription)) {
          _tmpFallbackDescription = null;
        } else {
          _tmpFallbackDescription = _cursor.getString(_cursorIndexOfFallbackDescription);
        }
        final Date _tmpDateUploaded;
        final Long _tmp_7;
        if (_cursor.isNull(_cursorIndexOfDateUploaded)) {
          _tmp_7 = null;
        } else {
          _tmp_7 = _cursor.getLong(_cursorIndexOfDateUploaded);
        }
        _tmpDateUploaded = Converters.fromTimestamp(_tmp_7);
        final String _tmpLicense;
        if (_cursor.isNull(_cursorIndexOfLicense)) {
          _tmpLicense = null;
        } else {
          _tmpLicense = _cursor.getString(_cursorIndexOfLicense);
        }
        final String _tmpLicenseUrl;
        if (_cursor.isNull(_cursorIndexOfLicenseUrl)) {
          _tmpLicenseUrl = null;
        } else {
          _tmpLicenseUrl = _cursor.getString(_cursorIndexOfLicenseUrl);
        }
        final String _tmpAuthor;
        if (_cursor.isNull(_cursorIndexOfAuthor)) {
          _tmpAuthor = null;
        } else {
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        }
        final String _tmpUser;
        if (_cursor.isNull(_cursorIndexOfUser)) {
          _tmpUser = null;
        } else {
          _tmpUser = _cursor.getString(_cursorIndexOfUser);
        }
        final List<String> _tmpCategories;
        final String _tmp_8;
        if (_cursor.isNull(_cursorIndexOfCategories)) {
          _tmp_8 = null;
        } else {
          _tmp_8 = _cursor.getString(_cursorIndexOfCategories);
        }
        _tmpCategories = Converters.stringToListObject(_tmp_8);
        final LatLng _tmpCoordinates;
        final String _tmp_9;
        if (_cursor.isNull(_cursorIndexOfCoordinates)) {
          _tmp_9 = null;
        } else {
          _tmp_9 = _cursor.getString(_cursorIndexOfCoordinates);
        }
        _tmpCoordinates = Converters.stringToLatLng(_tmp_9);
        final Map<String, String> _tmpCaptions;
        final String _tmp_10;
        if (_cursor.isNull(_cursorIndexOfCaptions)) {
          _tmp_10 = null;
        } else {
          _tmp_10 = _cursor.getString(_cursorIndexOfCaptions);
        }
        _tmpCaptions = Converters.stringToMap(_tmp_10);
        final Map<String, String> _tmpDescriptions;
        final String _tmp_11;
        if (_cursor.isNull(_cursorIndexOfDescriptions)) {
          _tmp_11 = null;
        } else {
          _tmp_11 = _cursor.getString(_cursorIndexOfDescriptions);
        }
        _tmpDescriptions = Converters.stringToMap(_tmp_11);
        final List<String> _tmpDepictionIds;
        final String _tmp_12;
        if (_cursor.isNull(_cursorIndexOfDepictionIds)) {
          _tmp_12 = null;
        } else {
          _tmp_12 = _cursor.getString(_cursorIndexOfDepictionIds);
        }
        _tmpDepictionIds = Converters.stringToListObject(_tmp_12);
        final Map<String, Boolean> _tmpCategoriesHiddenStatus;
        final String _tmp_13;
        if (_cursor.isNull(_cursorIndexOfCategoriesHiddenStatus)) {
          _tmp_13 = null;
        } else {
          _tmp_13 = _cursor.getString(_cursorIndexOfCategoriesHiddenStatus);
        }
        _tmpCategoriesHiddenStatus = Converters.stringToMap2(_tmp_13);
        _tmpMedia = new Media(_tmpPageId_1,_tmpThumbUrl,_tmpImageUrl,_tmpFilename,_tmpFallbackDescription,_tmpDateUploaded,_tmpLicense,_tmpLicenseUrl,_tmpAuthor,_tmpUser,_tmpCategories,_tmpCoordinates,_tmpCaptions,_tmpDescriptions,_tmpDepictionIds,_tmpCategoriesHiddenStatus);
        final List<String> _tmpAddedCategories;
        final String _tmp_14;
        if (_cursor.isNull(_cursorIndexOfAddedCategories)) {
          _tmp_14 = null;
        } else {
          _tmp_14 = _cursor.getString(_cursorIndexOfAddedCategories);
        }
        _tmpAddedCategories = Converters.stringToListObject(_tmp_14);
        _tmpMedia.setAddedCategories(_tmpAddedCategories);
        _result = new Contribution(_tmpMedia,_tmpPageId,_tmpState,_tmpTransferred,_tmpDecimalCoords,_tmpDateCreatedSource,_tmpWikidataPlace,_tmpChunkInfo,_tmpDepictedItems,_tmpMimeType,_tmpLocalUri,_tmpDataLength,_tmpDateCreated,_tmpDateCreatedString,_tmpDateModified,_tmpHasInvalidLocation,_tmpContentUri,_tmpCountryCode,_tmpImageSHA1,_tmpRetries);
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
  public Single<List<Contribution>> getContribution(final List<Integer> states) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * from contribution WHERE state IN (");
    final int _inputSize = states.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") order by media_dateUploaded DESC");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Integer _item : states) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    return RxRoom.createSingle(new Callable<List<Contribution>>() {
      @Override
      public List<Contribution> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPageId = CursorUtil.getColumnIndexOrThrow(_cursor, "pageId");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "transferred");
          final int _cursorIndexOfDecimalCoords = CursorUtil.getColumnIndexOrThrow(_cursor, "decimalCoords");
          final int _cursorIndexOfDateCreatedSource = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreatedSource");
          final int _cursorIndexOfWikidataPlace = CursorUtil.getColumnIndexOrThrow(_cursor, "wikidataPlace");
          final int _cursorIndexOfChunkInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "chunkInfo");
          final int _cursorIndexOfDepictedItems = CursorUtil.getColumnIndexOrThrow(_cursor, "depictedItems");
          final int _cursorIndexOfMimeType = CursorUtil.getColumnIndexOrThrow(_cursor, "mimeType");
          final int _cursorIndexOfLocalUri = CursorUtil.getColumnIndexOrThrow(_cursor, "localUri");
          final int _cursorIndexOfDataLength = CursorUtil.getColumnIndexOrThrow(_cursor, "dataLength");
          final int _cursorIndexOfDateCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreated");
          final int _cursorIndexOfDateCreatedString = CursorUtil.getColumnIndexOrThrow(_cursor, "dateCreatedString");
          final int _cursorIndexOfDateModified = CursorUtil.getColumnIndexOrThrow(_cursor, "dateModified");
          final int _cursorIndexOfHasInvalidLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "hasInvalidLocation");
          final int _cursorIndexOfContentUri = CursorUtil.getColumnIndexOrThrow(_cursor, "contentUri");
          final int _cursorIndexOfCountryCode = CursorUtil.getColumnIndexOrThrow(_cursor, "countryCode");
          final int _cursorIndexOfImageSHA1 = CursorUtil.getColumnIndexOrThrow(_cursor, "imageSHA1");
          final int _cursorIndexOfRetries = CursorUtil.getColumnIndexOrThrow(_cursor, "retries");
          final int _cursorIndexOfPageId_1 = CursorUtil.getColumnIndexOrThrow(_cursor, "media_pageId");
          final int _cursorIndexOfThumbUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_thumbUrl");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_imageUrl");
          final int _cursorIndexOfFilename = CursorUtil.getColumnIndexOrThrow(_cursor, "media_filename");
          final int _cursorIndexOfFallbackDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "media_fallbackDescription");
          final int _cursorIndexOfDateUploaded = CursorUtil.getColumnIndexOrThrow(_cursor, "media_dateUploaded");
          final int _cursorIndexOfLicense = CursorUtil.getColumnIndexOrThrow(_cursor, "media_license");
          final int _cursorIndexOfLicenseUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "media_licenseUrl");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "media_author");
          final int _cursorIndexOfUser = CursorUtil.getColumnIndexOrThrow(_cursor, "media_user");
          final int _cursorIndexOfCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "media_categories");
          final int _cursorIndexOfCoordinates = CursorUtil.getColumnIndexOrThrow(_cursor, "media_coordinates");
          final int _cursorIndexOfCaptions = CursorUtil.getColumnIndexOrThrow(_cursor, "media_captions");
          final int _cursorIndexOfDescriptions = CursorUtil.getColumnIndexOrThrow(_cursor, "media_descriptions");
          final int _cursorIndexOfDepictionIds = CursorUtil.getColumnIndexOrThrow(_cursor, "media_depictionIds");
          final int _cursorIndexOfCategoriesHiddenStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "media_categoriesHiddenStatus");
          final int _cursorIndexOfAddedCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "media_addedCategories");
          final List<Contribution> _result = new ArrayList<Contribution>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contribution _item_1;
            final String _tmpPageId;
            if (_cursor.isNull(_cursorIndexOfPageId)) {
              _tmpPageId = null;
            } else {
              _tmpPageId = _cursor.getString(_cursorIndexOfPageId);
            }
            final int _tmpState;
            _tmpState = _cursor.getInt(_cursorIndexOfState);
            final long _tmpTransferred;
            _tmpTransferred = _cursor.getLong(_cursorIndexOfTransferred);
            final String _tmpDecimalCoords;
            if (_cursor.isNull(_cursorIndexOfDecimalCoords)) {
              _tmpDecimalCoords = null;
            } else {
              _tmpDecimalCoords = _cursor.getString(_cursorIndexOfDecimalCoords);
            }
            final String _tmpDateCreatedSource;
            if (_cursor.isNull(_cursorIndexOfDateCreatedSource)) {
              _tmpDateCreatedSource = null;
            } else {
              _tmpDateCreatedSource = _cursor.getString(_cursorIndexOfDateCreatedSource);
            }
            final WikidataPlace _tmpWikidataPlace;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfWikidataPlace)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfWikidataPlace);
            }
            _tmpWikidataPlace = Converters.stringToWikidataPlace(_tmp);
            final ChunkInfo _tmpChunkInfo;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfChunkInfo)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfChunkInfo);
            }
            _tmpChunkInfo = Converters.stringToChunkInfo(_tmp_1);
            final List<DepictedItem> _tmpDepictedItems;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDepictedItems)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfDepictedItems);
            }
            _tmpDepictedItems = Converters.stringToList(_tmp_2);
            final String _tmpMimeType;
            if (_cursor.isNull(_cursorIndexOfMimeType)) {
              _tmpMimeType = null;
            } else {
              _tmpMimeType = _cursor.getString(_cursorIndexOfMimeType);
            }
            final Uri _tmpLocalUri;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLocalUri)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLocalUri);
            }
            _tmpLocalUri = Converters.fromString(_tmp_3);
            final long _tmpDataLength;
            _tmpDataLength = _cursor.getLong(_cursorIndexOfDataLength);
            final Date _tmpDateCreated;
            final Long _tmp_4;
            if (_cursor.isNull(_cursorIndexOfDateCreated)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getLong(_cursorIndexOfDateCreated);
            }
            _tmpDateCreated = Converters.fromTimestamp(_tmp_4);
            final String _tmpDateCreatedString;
            if (_cursor.isNull(_cursorIndexOfDateCreatedString)) {
              _tmpDateCreatedString = null;
            } else {
              _tmpDateCreatedString = _cursor.getString(_cursorIndexOfDateCreatedString);
            }
            final Date _tmpDateModified;
            final Long _tmp_5;
            if (_cursor.isNull(_cursorIndexOfDateModified)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getLong(_cursorIndexOfDateModified);
            }
            _tmpDateModified = Converters.fromTimestamp(_tmp_5);
            final int _tmpHasInvalidLocation;
            _tmpHasInvalidLocation = _cursor.getInt(_cursorIndexOfHasInvalidLocation);
            final Uri _tmpContentUri;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfContentUri)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfContentUri);
            }
            _tmpContentUri = Converters.fromString(_tmp_6);
            final String _tmpCountryCode;
            if (_cursor.isNull(_cursorIndexOfCountryCode)) {
              _tmpCountryCode = null;
            } else {
              _tmpCountryCode = _cursor.getString(_cursorIndexOfCountryCode);
            }
            final String _tmpImageSHA1;
            if (_cursor.isNull(_cursorIndexOfImageSHA1)) {
              _tmpImageSHA1 = null;
            } else {
              _tmpImageSHA1 = _cursor.getString(_cursorIndexOfImageSHA1);
            }
            final int _tmpRetries;
            _tmpRetries = _cursor.getInt(_cursorIndexOfRetries);
            final Media _tmpMedia;
            final String _tmpPageId_1;
            if (_cursor.isNull(_cursorIndexOfPageId_1)) {
              _tmpPageId_1 = null;
            } else {
              _tmpPageId_1 = _cursor.getString(_cursorIndexOfPageId_1);
            }
            final String _tmpThumbUrl;
            if (_cursor.isNull(_cursorIndexOfThumbUrl)) {
              _tmpThumbUrl = null;
            } else {
              _tmpThumbUrl = _cursor.getString(_cursorIndexOfThumbUrl);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpFilename;
            if (_cursor.isNull(_cursorIndexOfFilename)) {
              _tmpFilename = null;
            } else {
              _tmpFilename = _cursor.getString(_cursorIndexOfFilename);
            }
            final String _tmpFallbackDescription;
            if (_cursor.isNull(_cursorIndexOfFallbackDescription)) {
              _tmpFallbackDescription = null;
            } else {
              _tmpFallbackDescription = _cursor.getString(_cursorIndexOfFallbackDescription);
            }
            final Date _tmpDateUploaded;
            final Long _tmp_7;
            if (_cursor.isNull(_cursorIndexOfDateUploaded)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getLong(_cursorIndexOfDateUploaded);
            }
            _tmpDateUploaded = Converters.fromTimestamp(_tmp_7);
            final String _tmpLicense;
            if (_cursor.isNull(_cursorIndexOfLicense)) {
              _tmpLicense = null;
            } else {
              _tmpLicense = _cursor.getString(_cursorIndexOfLicense);
            }
            final String _tmpLicenseUrl;
            if (_cursor.isNull(_cursorIndexOfLicenseUrl)) {
              _tmpLicenseUrl = null;
            } else {
              _tmpLicenseUrl = _cursor.getString(_cursorIndexOfLicenseUrl);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpUser;
            if (_cursor.isNull(_cursorIndexOfUser)) {
              _tmpUser = null;
            } else {
              _tmpUser = _cursor.getString(_cursorIndexOfUser);
            }
            final List<String> _tmpCategories;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfCategories)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfCategories);
            }
            _tmpCategories = Converters.stringToListObject(_tmp_8);
            final LatLng _tmpCoordinates;
            final String _tmp_9;
            if (_cursor.isNull(_cursorIndexOfCoordinates)) {
              _tmp_9 = null;
            } else {
              _tmp_9 = _cursor.getString(_cursorIndexOfCoordinates);
            }
            _tmpCoordinates = Converters.stringToLatLng(_tmp_9);
            final Map<String, String> _tmpCaptions;
            final String _tmp_10;
            if (_cursor.isNull(_cursorIndexOfCaptions)) {
              _tmp_10 = null;
            } else {
              _tmp_10 = _cursor.getString(_cursorIndexOfCaptions);
            }
            _tmpCaptions = Converters.stringToMap(_tmp_10);
            final Map<String, String> _tmpDescriptions;
            final String _tmp_11;
            if (_cursor.isNull(_cursorIndexOfDescriptions)) {
              _tmp_11 = null;
            } else {
              _tmp_11 = _cursor.getString(_cursorIndexOfDescriptions);
            }
            _tmpDescriptions = Converters.stringToMap(_tmp_11);
            final List<String> _tmpDepictionIds;
            final String _tmp_12;
            if (_cursor.isNull(_cursorIndexOfDepictionIds)) {
              _tmp_12 = null;
            } else {
              _tmp_12 = _cursor.getString(_cursorIndexOfDepictionIds);
            }
            _tmpDepictionIds = Converters.stringToListObject(_tmp_12);
            final Map<String, Boolean> _tmpCategoriesHiddenStatus;
            final String _tmp_13;
            if (_cursor.isNull(_cursorIndexOfCategoriesHiddenStatus)) {
              _tmp_13 = null;
            } else {
              _tmp_13 = _cursor.getString(_cursorIndexOfCategoriesHiddenStatus);
            }
            _tmpCategoriesHiddenStatus = Converters.stringToMap2(_tmp_13);
            _tmpMedia = new Media(_tmpPageId_1,_tmpThumbUrl,_tmpImageUrl,_tmpFilename,_tmpFallbackDescription,_tmpDateUploaded,_tmpLicense,_tmpLicenseUrl,_tmpAuthor,_tmpUser,_tmpCategories,_tmpCoordinates,_tmpCaptions,_tmpDescriptions,_tmpDepictionIds,_tmpCategoriesHiddenStatus);
            final List<String> _tmpAddedCategories;
            final String _tmp_14;
            if (_cursor.isNull(_cursorIndexOfAddedCategories)) {
              _tmp_14 = null;
            } else {
              _tmp_14 = _cursor.getString(_cursorIndexOfAddedCategories);
            }
            _tmpAddedCategories = Converters.stringToListObject(_tmp_14);
            _tmpMedia.setAddedCategories(_tmpAddedCategories);
            _item_1 = new Contribution(_tmpMedia,_tmpPageId,_tmpState,_tmpTransferred,_tmpDecimalCoords,_tmpDateCreatedSource,_tmpWikidataPlace,_tmpChunkInfo,_tmpDepictedItems,_tmpMimeType,_tmpLocalUri,_tmpDataLength,_tmpDateCreated,_tmpDateCreatedString,_tmpDateModified,_tmpHasInvalidLocation,_tmpContentUri,_tmpCountryCode,_tmpImageSHA1,_tmpRetries);
            _result.add(_item_1);
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
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
  public Single<Integer> getPendingUploads(final int[] toUpdateStates) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT COUNT(*) from contribution WHERE state in (");
    final int _inputSize = toUpdateStates.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int _item : toUpdateStates) {
      _statement.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    return RxRoom.createSingle(new Callable<Integer>() {
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
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
