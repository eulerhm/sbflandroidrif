/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2015-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.storage.factories;

import android.content.ContentValues;
import net.sqlcipher.Cursor;
import java.util.ArrayList;
import java.util.List;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.models.ballot.BallotChoiceModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotChoiceModelFactory extends ModelFactory {

    public BallotChoiceModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, BallotChoiceModel.TABLE);
    }

    public List<BallotChoiceModel> getAll() {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null));
    }

    public List<BallotChoiceModel> getByBallotId(int ballotId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, BallotChoiceModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotId) }, null, null, "`" + BallotChoiceModel.COLUMN_ORDER + "` ASC"));
    }

    public BallotChoiceModel getByBallotIdAndApiChoiceId(int ballotId, int apiChoiceId) {
        return getFirst(BallotChoiceModel.COLUMN_BALLOT_ID + "=? " + "AND " + BallotChoiceModel.COLUMN_API_CHOICE_ID + "=?", new String[] { String.valueOf(ballotId), String.valueOf(apiChoiceId) });
    }

    public BallotChoiceModel getById(int id) {
        return getFirst(BallotChoiceModel.COLUMN_ID + "=?", new String[] { String.valueOf(id) });
    }

    public List<BallotChoiceModel> convert(QueryBuilder queryBuilder, String[] args, String orderBy) {
        if (!ListenerUtil.mutListener.listen(69824)) {
            queryBuilder.setTables(this.getTableName());
        }
        return convertList(queryBuilder.query(this.databaseService.getReadableDatabase(), null, null, args, null, null, orderBy));
    }

    private List<BallotChoiceModel> convertList(Cursor c) {
        List<BallotChoiceModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(69828)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(69827)) {
                        {
                            long _loopCounter901 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter901", ++_loopCounter901);
                                if (!ListenerUtil.mutListener.listen(69826)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(69825)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    private BallotChoiceModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(69839)) {
            if ((ListenerUtil.mutListener.listen(69834) ? (cursor != null || (ListenerUtil.mutListener.listen(69833) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(69832) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(69831) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(69830) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(69829) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(69833) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(69832) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(69831) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(69830) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(69829) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final BallotChoiceModel c = new BallotChoiceModel();
                if (!ListenerUtil.mutListener.listen(69838)) {
                    // convert default
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorFactory) {
                            if (!ListenerUtil.mutListener.listen(69835)) {
                                c.setId(cursorFactory.getInt(BallotChoiceModel.COLUMN_ID)).setBallotId(cursorFactory.getInt(BallotChoiceModel.COLUMN_BALLOT_ID)).setApiBallotChoiceId(cursorFactory.getInt(BallotChoiceModel.COLUMN_API_CHOICE_ID)).setName(cursorFactory.getString(BallotChoiceModel.COLUMN_NAME)).setVoteCount(cursorFactory.getInt(BallotChoiceModel.COLUMN_VOTE_COUNT)).setOrder(cursorFactory.getInt(BallotChoiceModel.COLUMN_ORDER)).setCreatedAt(cursorFactory.getDate(BallotChoiceModel.COLUMN_CREATED_AT)).setModifiedAt(cursorFactory.getDate(BallotChoiceModel.COLUMN_MODIFIED_AT));
                            }
                            String type = cursorFactory.getString(BallotChoiceModel.COLUMN_TYPE);
                            if (!ListenerUtil.mutListener.listen(69837)) {
                                if (!TestUtil.empty(type)) {
                                    if (!ListenerUtil.mutListener.listen(69836)) {
                                        c.setType(BallotChoiceModel.Type.valueOf(type));
                                    }
                                }
                            }
                            return false;
                        }
                    });
                }
                return c;
            }
        }
        return null;
    }

    public boolean createOrUpdate(BallotChoiceModel ballotChoiceModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(69848)) {
            if ((ListenerUtil.mutListener.listen(69844) ? (ballotChoiceModel.getId() >= 0) : (ListenerUtil.mutListener.listen(69843) ? (ballotChoiceModel.getId() <= 0) : (ListenerUtil.mutListener.listen(69842) ? (ballotChoiceModel.getId() < 0) : (ListenerUtil.mutListener.listen(69841) ? (ballotChoiceModel.getId() != 0) : (ListenerUtil.mutListener.listen(69840) ? (ballotChoiceModel.getId() == 0) : (ballotChoiceModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, BallotChoiceModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotChoiceModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(69847)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(69846)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(69845)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(ballotChoiceModel);
        } else {
            return update(ballotChoiceModel);
        }
    }

    private ContentValues buildContentValues(BallotChoiceModel ballotChoiceModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(69849)) {
            contentValues.put(BallotChoiceModel.COLUMN_BALLOT_ID, ballotChoiceModel.getBallotId());
        }
        if (!ListenerUtil.mutListener.listen(69850)) {
            contentValues.put(BallotChoiceModel.COLUMN_API_CHOICE_ID, ballotChoiceModel.getApiBallotChoiceId());
        }
        if (!ListenerUtil.mutListener.listen(69851)) {
            contentValues.put(BallotChoiceModel.COLUMN_TYPE, ballotChoiceModel.getType() != null ? ballotChoiceModel.getType().toString() : null);
        }
        if (!ListenerUtil.mutListener.listen(69852)) {
            contentValues.put(BallotChoiceModel.COLUMN_NAME, ballotChoiceModel.getName());
        }
        if (!ListenerUtil.mutListener.listen(69853)) {
            contentValues.put(BallotChoiceModel.COLUMN_VOTE_COUNT, ballotChoiceModel.getVoteCount());
        }
        if (!ListenerUtil.mutListener.listen(69854)) {
            contentValues.put("`" + BallotChoiceModel.COLUMN_ORDER + "`", ballotChoiceModel.getOrder());
        }
        if (!ListenerUtil.mutListener.listen(69855)) {
            contentValues.put(BallotChoiceModel.COLUMN_CREATED_AT, ballotChoiceModel.getCreatedAt() != null ? ballotChoiceModel.getCreatedAt().getTime() : null);
        }
        if (!ListenerUtil.mutListener.listen(69856)) {
            contentValues.put(BallotChoiceModel.COLUMN_MODIFIED_AT, ballotChoiceModel.getModifiedAt() != null ? ballotChoiceModel.getModifiedAt().getTime() : null);
        }
        return contentValues;
    }

    public boolean create(BallotChoiceModel ballotChoiceModel) {
        ContentValues contentValues = buildContentValues(ballotChoiceModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(69863)) {
            if ((ListenerUtil.mutListener.listen(69861) ? (newId >= 0) : (ListenerUtil.mutListener.listen(69860) ? (newId <= 0) : (ListenerUtil.mutListener.listen(69859) ? (newId < 0) : (ListenerUtil.mutListener.listen(69858) ? (newId != 0) : (ListenerUtil.mutListener.listen(69857) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(69862)) {
                    ballotChoiceModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    private boolean update(BallotChoiceModel ballotChoiceModel) {
        ContentValues contentValues = buildContentValues(ballotChoiceModel);
        if (!ListenerUtil.mutListener.listen(69864)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, BallotChoiceModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotChoiceModel.getId()) });
        }
        return true;
    }

    public int delete(BallotChoiceModel ballotChoiceModel) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), BallotChoiceModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotChoiceModel.getId()) });
    }

    public int deleteByBallotId(int ballotId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), BallotChoiceModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotId) });
    }

    private BallotChoiceModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(69867)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(69866)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(69865)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `ballot_choice` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `ballotId` INTEGER , `apiBallotChoiceId` INTEGER , `type` VARCHAR , `name` VARCHAR , `voteCount` INTEGER , `order` INTEGER NOT NULL , `createdAt` BIGINT , `modifiedAt` BIGINT )", // indices
        "CREATE UNIQUE INDEX `apiBallotChoiceId` ON `ballot_choice` ( `ballotId`, `apiBallotChoiceId` )" };
    }
}
