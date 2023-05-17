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
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.models.DistributionListMemberModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListMemberModelFactory extends ModelFactory {

    public DistributionListMemberModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, DistributionListMemberModel.TABLE);
    }

    public DistributionListMemberModel getByDistributionListIdAndIdentity(int groupId, String identity) {
        if (!ListenerUtil.mutListener.listen(70076)) {
            if (identity == null) {
                return null;
            }
        }
        return getFirst(DistributionListMemberModel.COLUMN_DISTRIBUTION_LIST_ID + "=? " + " AND " + DistributionListMemberModel.COLUMN_IDENTITY + "=?", new String[] { String.valueOf(groupId), identity });
    }

    public List<DistributionListMemberModel> getByDistributionListId(int groupId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, DistributionListMemberModel.COLUMN_DISTRIBUTION_LIST_ID + "=?", new String[] { String.valueOf(groupId) }, null, null, null));
    }

    private DistributionListMemberModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70085)) {
            if ((ListenerUtil.mutListener.listen(70082) ? (cursor != null || (ListenerUtil.mutListener.listen(70081) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70080) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70079) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70078) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70077) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70081) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70080) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70079) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70078) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70077) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final DistributionListMemberModel distributionListMemberModel = new DistributionListMemberModel();
                if (!ListenerUtil.mutListener.listen(70084)) {
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70083)) {
                                distributionListMemberModel.setId(cursorHelper.getInt(DistributionListMemberModel.COLUMN_ID)).setDistributionListId(cursorHelper.getInt(DistributionListMemberModel.COLUMN_DISTRIBUTION_LIST_ID)).setIdentity(cursorHelper.getString(DistributionListMemberModel.COLUMN_IDENTITY)).setActive(cursorHelper.getBoolean(DistributionListMemberModel.COLUMN_IS_ACTIVE));
                            }
                            return false;
                        }
                    });
                }
                return distributionListMemberModel;
            }
        }
        return null;
    }

    private List<DistributionListMemberModel> convertList(Cursor c) {
        List<DistributionListMemberModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70089)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70088)) {
                        {
                            long _loopCounter909 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter909", ++_loopCounter909);
                                if (!ListenerUtil.mutListener.listen(70087)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70086)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    public boolean createOrUpdate(DistributionListMemberModel distributionListMemberModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(70098)) {
            if ((ListenerUtil.mutListener.listen(70094) ? (distributionListMemberModel.getId() >= 0) : (ListenerUtil.mutListener.listen(70093) ? (distributionListMemberModel.getId() <= 0) : (ListenerUtil.mutListener.listen(70092) ? (distributionListMemberModel.getId() < 0) : (ListenerUtil.mutListener.listen(70091) ? (distributionListMemberModel.getId() != 0) : (ListenerUtil.mutListener.listen(70090) ? (distributionListMemberModel.getId() == 0) : (distributionListMemberModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, DistributionListMemberModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListMemberModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(70097)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(70096)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(70095)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(distributionListMemberModel);
        } else {
            return update(distributionListMemberModel);
        }
    }

    private ContentValues buildContentValues(DistributionListMemberModel distributionListMemberModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70099)) {
            contentValues.put(DistributionListMemberModel.COLUMN_DISTRIBUTION_LIST_ID, distributionListMemberModel.getDistributionListId());
        }
        if (!ListenerUtil.mutListener.listen(70100)) {
            contentValues.put(DistributionListMemberModel.COLUMN_IDENTITY, distributionListMemberModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(70101)) {
            contentValues.put(DistributionListMemberModel.COLUMN_IS_ACTIVE, distributionListMemberModel.isActive());
        }
        return contentValues;
    }

    public boolean create(DistributionListMemberModel distributionListMemberModel) {
        ContentValues contentValues = buildContentValues(distributionListMemberModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70108)) {
            if ((ListenerUtil.mutListener.listen(70106) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70105) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70104) ? (newId < 0) : (ListenerUtil.mutListener.listen(70103) ? (newId != 0) : (ListenerUtil.mutListener.listen(70102) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70107)) {
                    distributionListMemberModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public boolean update(DistributionListMemberModel distributionListMemberModel) {
        ContentValues contentValues = buildContentValues(distributionListMemberModel);
        if (!ListenerUtil.mutListener.listen(70109)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, DistributionListMemberModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListMemberModel.getId()) });
        }
        return true;
    }

    private DistributionListMemberModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70112)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70111)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70110)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    public int deleteByDistributionListId(int distributionListId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), DistributionListMemberModel.COLUMN_DISTRIBUTION_LIST_ID + "=?", new String[] { String.valueOf(distributionListId) });
    }

    public int delete(List<DistributionListMemberModel> modelsToRemove) {
        String[] args = new String[modelsToRemove.size()];
        if (!ListenerUtil.mutListener.listen(70119)) {
            {
                long _loopCounter910 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(70118) ? (n >= modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70117) ? (n <= modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70116) ? (n > modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70115) ? (n != modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70114) ? (n == modelsToRemove.size()) : (n < modelsToRemove.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter910", ++_loopCounter910);
                    if (!ListenerUtil.mutListener.listen(70113)) {
                        args[n] = String.valueOf(modelsToRemove.get(n).getId());
                    }
                }
            }
        }
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), DistributionListMemberModel.COLUMN_ID + " IN (" + DatabaseUtil.makePlaceholders(args.length) + ")", args);
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `" + DistributionListMemberModel.TABLE + "`(" + "`" + DistributionListMemberModel.COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT , " + "`" + DistributionListMemberModel.COLUMN_IDENTITY + "` VARCHAR , " + "`" + DistributionListMemberModel.COLUMN_DISTRIBUTION_LIST_ID + "` INTEGER , " + "`" + DistributionListMemberModel.COLUMN_IS_ACTIVE + "` SMALLINT NOT NULL" + ")", "CREATE INDEX `distribution_list_member_dis_idx`" + " ON `" + DistributionListMemberModel.TABLE + "`(`" + DistributionListMemberModel.COLUMN_DISTRIBUTION_LIST_ID + "`)" };
    }
}
