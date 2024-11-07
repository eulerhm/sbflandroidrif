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
import net.sqlcipher.DatabaseUtils;
import java.util.ArrayList;
import java.util.List;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.models.ballot.BallotVoteModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BallotVoteModelFactory extends ModelFactory {

    public BallotVoteModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, BallotVoteModel.TABLE);
    }

    public List<BallotVoteModel> getAll() {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null));
    }

    public List<BallotVoteModel> getByBallotId(int ballotId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, BallotVoteModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotId) }, null, null, null));
    }

    public List<BallotVoteModel> getByBallotIdAndVotingIdentity(Integer ballotId, String fromIdentity) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, BallotVoteModel.COLUMN_BALLOT_ID + "=? " + " AND " + BallotVoteModel.COLUMN_VOTING_IDENTITY + "=?", new String[] { String.valueOf(ballotId), fromIdentity }, null, null, null));
    }

    public long countByBallotIdAndVotingIdentity(Integer ballotId, String fromIdentity) {
        return DatabaseUtils.longForQuery(this.databaseService.getReadableDatabase(), "SELECT COUNT(*) FROM " + this.getTableName() + " " + "WHERE " + BallotVoteModel.COLUMN_BALLOT_ID + "=?" + " AND " + BallotVoteModel.COLUMN_VOTING_IDENTITY + "=?", new String[] { String.valueOf(ballotId), String.valueOf(fromIdentity) });
    }

    public List<BallotVoteModel> getByBallotChoiceId(int ballotChoiceId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, BallotVoteModel.COLUMN_BALLOT_CHOICE_ID + "=?", new String[] { String.valueOf(ballotChoiceId) }, null, null, null));
    }

    public BallotVoteModel getById(int id) {
        return getFirst(BallotVoteModel.COLUMN_ID + "=?", new String[] { String.valueOf(id) });
    }

    public List<BallotVoteModel> convert(QueryBuilder queryBuilder, String[] args, String orderBy) {
        if (!ListenerUtil.mutListener.listen(69957)) {
            queryBuilder.setTables(this.getTableName());
        }
        return convertList(queryBuilder.query(this.databaseService.getReadableDatabase(), null, null, args, null, null, orderBy));
    }

    private List<BallotVoteModel> convertList(Cursor c) {
        List<BallotVoteModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(69961)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(69960)) {
                        {
                            long _loopCounter905 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter905", ++_loopCounter905);
                                if (!ListenerUtil.mutListener.listen(69959)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(69958)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    private BallotVoteModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(69970)) {
            if ((ListenerUtil.mutListener.listen(69967) ? (cursor != null || (ListenerUtil.mutListener.listen(69966) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(69965) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(69964) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(69963) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(69962) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(69966) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(69965) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(69964) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(69963) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(69962) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final BallotVoteModel c = new BallotVoteModel();
                if (!ListenerUtil.mutListener.listen(69969)) {
                    // convert default
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(69968)) {
                                c.setId(cursorHelper.getInt(BallotVoteModel.COLUMN_ID)).setBallotId(cursorHelper.getInt(BallotVoteModel.COLUMN_BALLOT_ID)).setBallotChoiceId(cursorHelper.getInt(BallotVoteModel.COLUMN_BALLOT_CHOICE_ID)).setVotingIdentity(cursorHelper.getString(BallotVoteModel.COLUMN_VOTING_IDENTITY)).setChoice(cursorHelper.getInt(BallotVoteModel.COLUMN_CHOICE)).setCreatedAt(cursorHelper.getDate(BallotVoteModel.COLUMN_CREATED_AT)).setModifiedAt(cursorHelper.getDate(BallotVoteModel.COLUMN_MODIFIED_AT));
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

    public boolean createOrUpdate(BallotVoteModel ballotVoteModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(69979)) {
            if ((ListenerUtil.mutListener.listen(69975) ? (ballotVoteModel.getId() >= 0) : (ListenerUtil.mutListener.listen(69974) ? (ballotVoteModel.getId() <= 0) : (ListenerUtil.mutListener.listen(69973) ? (ballotVoteModel.getId() < 0) : (ListenerUtil.mutListener.listen(69972) ? (ballotVoteModel.getId() != 0) : (ListenerUtil.mutListener.listen(69971) ? (ballotVoteModel.getId() == 0) : (ballotVoteModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, BallotVoteModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotVoteModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(69978)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(69977)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(69976)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(ballotVoteModel);
        } else {
            return update(ballotVoteModel);
        }
    }

    private ContentValues buildContentValues(BallotVoteModel ballotVoteModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(69980)) {
            contentValues.put(BallotVoteModel.COLUMN_BALLOT_ID, ballotVoteModel.getBallotId());
        }
        if (!ListenerUtil.mutListener.listen(69981)) {
            contentValues.put(BallotVoteModel.COLUMN_BALLOT_CHOICE_ID, ballotVoteModel.getBallotChoiceId());
        }
        if (!ListenerUtil.mutListener.listen(69982)) {
            contentValues.put(BallotVoteModel.COLUMN_VOTING_IDENTITY, ballotVoteModel.getVotingIdentity());
        }
        if (!ListenerUtil.mutListener.listen(69983)) {
            contentValues.put(BallotVoteModel.COLUMN_CHOICE, ballotVoteModel.getChoice());
        }
        if (!ListenerUtil.mutListener.listen(69984)) {
            contentValues.put(BallotVoteModel.COLUMN_CREATED_AT, ballotVoteModel.getCreatedAt() != null ? ballotVoteModel.getCreatedAt().getTime() : null);
        }
        if (!ListenerUtil.mutListener.listen(69985)) {
            contentValues.put(BallotVoteModel.COLUMN_MODIFIED_AT, ballotVoteModel.getModifiedAt() != null ? ballotVoteModel.getModifiedAt().getTime() : null);
        }
        return contentValues;
    }

    public boolean create(BallotVoteModel ballotVoteModel) {
        ContentValues contentValues = buildContentValues(ballotVoteModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(69992)) {
            if ((ListenerUtil.mutListener.listen(69990) ? (newId >= 0) : (ListenerUtil.mutListener.listen(69989) ? (newId <= 0) : (ListenerUtil.mutListener.listen(69988) ? (newId < 0) : (ListenerUtil.mutListener.listen(69987) ? (newId != 0) : (ListenerUtil.mutListener.listen(69986) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(69991)) {
                    ballotVoteModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    private boolean update(BallotVoteModel ballotVoteModel) {
        ContentValues contentValues = buildContentValues(ballotVoteModel);
        if (!ListenerUtil.mutListener.listen(69993)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, BallotVoteModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotVoteModel.getId()) });
        }
        return true;
    }

    public int delete(BallotVoteModel ballotVoteModel) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), BallotVoteModel.COLUMN_ID + "=?", new String[] { String.valueOf(ballotVoteModel.getId()) });
    }

    public int deleteByIds(int[] ids) {
        String[] params = new String[ids.length];
        if (!ListenerUtil.mutListener.listen(70000)) {
            {
                long _loopCounter906 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(69999) ? (n >= ids.length) : (ListenerUtil.mutListener.listen(69998) ? (n <= ids.length) : (ListenerUtil.mutListener.listen(69997) ? (n > ids.length) : (ListenerUtil.mutListener.listen(69996) ? (n != ids.length) : (ListenerUtil.mutListener.listen(69995) ? (n == ids.length) : (n < ids.length)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter906", ++_loopCounter906);
                    if (!ListenerUtil.mutListener.listen(69994)) {
                        params[n] = String.valueOf(ids[n]);
                    }
                }
            }
        }
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), BallotVoteModel.COLUMN_ID + " IN(" + DatabaseUtil.makePlaceholders(params.length) + ")", params);
    }

    public int deleteByBallotId(int ballotId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), BallotVoteModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotId) });
    }

    public int deleteByBallotIdAndVotingIdentity(int ballotId, String identity) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), BallotVoteModel.COLUMN_BALLOT_ID + "=? " + "AND " + BallotVoteModel.COLUMN_VOTING_IDENTITY + "=?", new String[] { String.valueOf(ballotId), identity });
    }

    private BallotVoteModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70003)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70002)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70001)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    public long countByBallotChoiceIdAndChoice(int ballotChoiceId, int choice) {
        return DatabaseUtils.longForQuery(this.databaseService.getReadableDatabase(), "SELECT COUNT(*) FROM " + this.getTableName() + " " + "WHERE " + BallotVoteModel.COLUMN_BALLOT_CHOICE_ID + "=? " + "AND " + BallotVoteModel.COLUMN_CHOICE + "=?", new String[] { String.valueOf(ballotChoiceId), String.valueOf(choice) });
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `ballot_vote` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `ballotId` INTEGER NOT NULL , `ballotChoiceId` INTEGER NOT NULL , `votingIdentity` VARCHAR NOT NULL , `choice` INTEGER , `createdAt` BIGINT NOT NULL , `modifiedAt` BIGINT NOT NULL );", // indices
        "CREATE INDEX `ballotVotingCount` ON `ballot_vote` ( `ballotChoiceId`, `choice` )", "CREATE UNIQUE INDEX `ballotVoteIdentity` ON `ballot_vote` ( `ballotId`, `ballotChoiceId`, `votingIdentity` );" };
    }
}
