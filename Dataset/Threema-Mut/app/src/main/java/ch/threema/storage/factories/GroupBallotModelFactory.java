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
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.ballot.GroupBallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupBallotModelFactory extends ModelFactory {

    public GroupBallotModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, GroupBallotModel.TABLE);
    }

    public GroupBallotModel getByGroupIdAndBallotId(int groupId, int ballotId) {
        return getFirst(GroupBallotModel.COLUMN_GROUP_ID + "=? " + "AND " + GroupBallotModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(groupId), String.valueOf(ballotId) });
    }

    public GroupBallotModel getByBallotId(int ballotModelId) {
        return getFirst(GroupBallotModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotModelId) });
    }

    private GroupBallotModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70214)) {
            if ((ListenerUtil.mutListener.listen(70211) ? (cursor != null || (ListenerUtil.mutListener.listen(70210) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70209) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70208) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70207) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70206) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70210) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70209) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70208) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70207) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70206) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final GroupBallotModel c = new GroupBallotModel();
                if (!ListenerUtil.mutListener.listen(70213)) {
                    // convert default
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70212)) {
                                c.setId(cursorHelper.getInt(GroupBallotModel.COLUMN_ID)).setBallotId(cursorHelper.getInt(GroupBallotModel.COLUMN_BALLOT_ID)).setGroupId(cursorHelper.getInt(GroupBallotModel.COLUMN_GROUP_ID));
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

    private ContentValues buildContentValues(GroupBallotModel groupBallotModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70215)) {
            contentValues.put(GroupBallotModel.COLUMN_GROUP_ID, groupBallotModel.getGroupId());
        }
        if (!ListenerUtil.mutListener.listen(70216)) {
            contentValues.put(GroupBallotModel.COLUMN_BALLOT_ID, groupBallotModel.getBallotId());
        }
        return contentValues;
    }

    public boolean create(GroupBallotModel groupBallotModel) {
        ContentValues contentValues = buildContentValues(groupBallotModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70223)) {
            if ((ListenerUtil.mutListener.listen(70221) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70220) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70219) ? (newId < 0) : (ListenerUtil.mutListener.listen(70218) ? (newId != 0) : (ListenerUtil.mutListener.listen(70217) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70222)) {
                    groupBallotModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public int deleteByBallotId(int ballotId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupBallotModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotId) });
    }

    private GroupBallotModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70226)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70225)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70224)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `group_ballot` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `groupId` INTEGER NOT NULL , `ballotId` INTEGER NOT NULL )", "CREATE UNIQUE INDEX `groupBallotId` ON `group_ballot` ( `groupId`, `ballotId` )" };
    }
}
