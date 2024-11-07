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
import ch.threema.storage.models.GroupRequestSyncLogModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupRequestSyncLogModelFactory extends ModelFactory {

    public GroupRequestSyncLogModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, GroupRequestSyncLogModel.TABLE);
    }

    public GroupRequestSyncLogModel get(String apiGroupId, String groupCreator) {
        return getFirst(GroupRequestSyncLogModel.COLUMN_API_GROUP_ID + "=?" + " AND " + GroupRequestSyncLogModel.COLUMN_CREATOR_IDENTITY + "=?", new String[] { apiGroupId, groupCreator });
    }

    public boolean createOrUpdate(GroupRequestSyncLogModel groupRequestSyncLogModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(70401)) {
            if ((ListenerUtil.mutListener.listen(70397) ? (groupRequestSyncLogModel.getId() >= 0) : (ListenerUtil.mutListener.listen(70396) ? (groupRequestSyncLogModel.getId() <= 0) : (ListenerUtil.mutListener.listen(70395) ? (groupRequestSyncLogModel.getId() < 0) : (ListenerUtil.mutListener.listen(70394) ? (groupRequestSyncLogModel.getId() != 0) : (ListenerUtil.mutListener.listen(70393) ? (groupRequestSyncLogModel.getId() == 0) : (groupRequestSyncLogModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, GroupRequestSyncLogModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupRequestSyncLogModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(70400)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(70399)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(70398)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(groupRequestSyncLogModel);
        } else {
            return update(groupRequestSyncLogModel);
        }
    }

    public boolean create(GroupRequestSyncLogModel groupRequestSyncLogModel) {
        ContentValues contentValues = buildValues(groupRequestSyncLogModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70408)) {
            if ((ListenerUtil.mutListener.listen(70406) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70405) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70404) ? (newId < 0) : (ListenerUtil.mutListener.listen(70403) ? (newId != 0) : (ListenerUtil.mutListener.listen(70402) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70407)) {
                    groupRequestSyncLogModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public boolean update(GroupRequestSyncLogModel groupRequestSyncLogModel) {
        ContentValues contentValues = buildValues(groupRequestSyncLogModel);
        if (!ListenerUtil.mutListener.listen(70409)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, GroupRequestSyncLogModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupRequestSyncLogModel.getId()) });
        }
        return true;
    }

    private ContentValues buildValues(GroupRequestSyncLogModel groupRequestSyncLogModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70410)) {
            contentValues.put(GroupRequestSyncLogModel.COLUMN_API_GROUP_ID, groupRequestSyncLogModel.getApiGroupId());
        }
        if (!ListenerUtil.mutListener.listen(70411)) {
            contentValues.put(GroupRequestSyncLogModel.COLUMN_CREATOR_IDENTITY, groupRequestSyncLogModel.getCreatorIdentity());
        }
        if (!ListenerUtil.mutListener.listen(70412)) {
            contentValues.put(GroupRequestSyncLogModel.COLUMN_LAST_REQUEST, groupRequestSyncLogModel.getLastRequest() != null ? CursorHelper.dateAsStringFormat.get().format(groupRequestSyncLogModel.getLastRequest()) : null);
        }
        return contentValues;
    }

    private GroupRequestSyncLogModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70415)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70414)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70413)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    private GroupRequestSyncLogModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70424)) {
            if ((ListenerUtil.mutListener.listen(70421) ? (cursor != null || (ListenerUtil.mutListener.listen(70420) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70419) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70418) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70417) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70416) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70420) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70419) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70418) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70417) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70416) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final GroupRequestSyncLogModel c = new GroupRequestSyncLogModel();
                if (!ListenerUtil.mutListener.listen(70423)) {
                    // convert default
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70422)) {
                                c.setId(cursorHelper.getInt(GroupRequestSyncLogModel.COLUMN_ID)).setAPIGroupId(cursorHelper.getString(GroupRequestSyncLogModel.COLUMN_API_GROUP_ID), cursorHelper.getString(GroupRequestSyncLogModel.COLUMN_CREATOR_IDENTITY)).setLastRequest(cursorHelper.getDateByString(GroupRequestSyncLogModel.COLUMN_LAST_REQUEST));
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

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `m_group_request_sync_log` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `apiGroupId` VARCHAR , `creatorIdentity` VARCHAR , `lastRequest` VARCHAR )", "CREATE UNIQUE INDEX `apiGroupIdAndCreatorGroupRequestSyncLogModel` ON `m_group_request_sync_log` ( `apiGroupId`, `creatorIdentity` );" };
    }
}
