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
import ch.threema.storage.models.ballot.IdentityBallotModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class IdentityBallotModelFactory extends ModelFactory {

    public IdentityBallotModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, IdentityBallotModel.TABLE);
    }

    public IdentityBallotModel getByIdentityAndBallotId(String identity, int ballotId) {
        return getFirst(IdentityBallotModel.COLUMN_IDENTITY + "=? " + "AND " + IdentityBallotModel.COLUMN_BALLOT_ID + "=?", new String[] { identity, String.valueOf(ballotId) });
    }

    public IdentityBallotModel getByBallotId(int ballotModelId) {
        return getFirst(IdentityBallotModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotModelId) });
    }

    private IdentityBallotModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70433)) {
            if ((ListenerUtil.mutListener.listen(70430) ? (cursor != null || (ListenerUtil.mutListener.listen(70429) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70428) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70427) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70426) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70425) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70429) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70428) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70427) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70426) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70425) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final IdentityBallotModel c = new IdentityBallotModel();
                if (!ListenerUtil.mutListener.listen(70432)) {
                    // convert default
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70431)) {
                                c.setId(cursorHelper.getInt(IdentityBallotModel.COLUMN_ID)).setBallotId(cursorHelper.getInt(IdentityBallotModel.COLUMN_BALLOT_ID)).setIdentity(cursorHelper.getString(IdentityBallotModel.COLUMN_IDENTITY));
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

    private ContentValues buildContentValues(IdentityBallotModel identityBallotModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70434)) {
            contentValues.put(IdentityBallotModel.COLUMN_IDENTITY, identityBallotModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(70435)) {
            contentValues.put(IdentityBallotModel.COLUMN_BALLOT_ID, identityBallotModel.getBallotId());
        }
        return contentValues;
    }

    public boolean create(IdentityBallotModel identityBallotModel) {
        ContentValues contentValues = buildContentValues(identityBallotModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70442)) {
            if ((ListenerUtil.mutListener.listen(70440) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70439) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70438) ? (newId < 0) : (ListenerUtil.mutListener.listen(70437) ? (newId != 0) : (ListenerUtil.mutListener.listen(70436) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70441)) {
                    identityBallotModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public int deleteByBallotId(int ballotId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), IdentityBallotModel.COLUMN_BALLOT_ID + "=?", new String[] { String.valueOf(ballotId) });
    }

    private IdentityBallotModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70445)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70444)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70443)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `identity_ballot` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `identity` VARCHAR NOT NULL , `ballotId` INTEGER NOT NULL )", "CREATE UNIQUE INDEX `identityBallotId` ON `identity_ballot` ( `identity`, `ballotId` )" };
    }
}
