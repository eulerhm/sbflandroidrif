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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.models.GroupMemberModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupMemberModelFactory extends ModelFactory {

    public GroupMemberModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, GroupMemberModel.TABLE);
    }

    public GroupMemberModel getByGroupIdAndIdentity(int groupId, String identity) {
        return getFirst(GroupMemberModel.COLUMN_GROUP_ID + "=? " + " AND " + GroupMemberModel.COLUMN_IDENTITY + "=?", new String[] { String.valueOf(groupId), identity });
    }

    public List<GroupMemberModel> getByGroupId(int groupId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, GroupMemberModel.COLUMN_GROUP_ID + "=?", new String[] { String.valueOf(groupId) }, null, null, null));
    }

    public long countMembers(int groupId) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + GroupMemberModel.COLUMN_GROUP_ID + "=?", new String[] { String.valueOf(groupId) }));
    }

    private GroupMemberModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70235)) {
            if ((ListenerUtil.mutListener.listen(70232) ? (cursor != null || (ListenerUtil.mutListener.listen(70231) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70230) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70229) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70228) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70227) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70231) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70230) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70229) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70228) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70227) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final GroupMemberModel groupMemberModel = new GroupMemberModel();
                if (!ListenerUtil.mutListener.listen(70234)) {
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70233)) {
                                groupMemberModel.setId(cursorHelper.getInt(GroupMemberModel.COLUMN_ID)).setGroupId(cursorHelper.getInt(GroupMemberModel.COLUMN_GROUP_ID)).setIdentity(cursorHelper.getString(GroupMemberModel.COLUMN_IDENTITY)).setActive(cursorHelper.getBoolean(GroupMemberModel.COLUMN_IS_ACTIVE));
                            }
                            return false;
                        }
                    });
                }
                return groupMemberModel;
            }
        }
        return null;
    }

    public List<GroupMemberModel> convertList(Cursor c) {
        List<GroupMemberModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70239)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70238)) {
                        {
                            long _loopCounter914 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter914", ++_loopCounter914);
                                if (!ListenerUtil.mutListener.listen(70237)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70236)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    public boolean createOrUpdate(GroupMemberModel groupMemberModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(70248)) {
            if ((ListenerUtil.mutListener.listen(70244) ? (groupMemberModel.getId() >= 0) : (ListenerUtil.mutListener.listen(70243) ? (groupMemberModel.getId() <= 0) : (ListenerUtil.mutListener.listen(70242) ? (groupMemberModel.getId() < 0) : (ListenerUtil.mutListener.listen(70241) ? (groupMemberModel.getId() != 0) : (ListenerUtil.mutListener.listen(70240) ? (groupMemberModel.getId() == 0) : (groupMemberModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, GroupMemberModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupMemberModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(70247)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(70246)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(70245)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(groupMemberModel);
        } else {
            return update(groupMemberModel);
        }
    }

    private ContentValues buildContentValues(GroupMemberModel groupMemberModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70249)) {
            contentValues.put(GroupMemberModel.COLUMN_GROUP_ID, groupMemberModel.getGroupId());
        }
        if (!ListenerUtil.mutListener.listen(70250)) {
            contentValues.put(GroupMemberModel.COLUMN_IDENTITY, groupMemberModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(70251)) {
            contentValues.put(GroupMemberModel.COLUMN_IS_ACTIVE, groupMemberModel.isActive());
        }
        return contentValues;
    }

    public boolean create(GroupMemberModel groupMemberModel) {
        ContentValues contentValues = buildContentValues(groupMemberModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70258)) {
            if ((ListenerUtil.mutListener.listen(70256) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70255) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70254) ? (newId < 0) : (ListenerUtil.mutListener.listen(70253) ? (newId != 0) : (ListenerUtil.mutListener.listen(70252) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70257)) {
                    groupMemberModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public boolean update(GroupMemberModel groupMemberModel) {
        ContentValues contentValues = buildContentValues(groupMemberModel);
        if (!ListenerUtil.mutListener.listen(70259)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, GroupMemberModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupMemberModel.getId()) });
        }
        return true;
    }

    private GroupMemberModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70262)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70261)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70260)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    public Map<String, Integer> getColors(int groupId) {
        // TODO use const instead fix table names!
        Cursor c = this.databaseService.getReadableDatabase().rawQuery("SELECT c.identity, c.color " + "FROM group_member gm " + "INNER JOIN contacts c " + "	ON c.identity = gm.identity " + "WHERE gm.groupId = ? AND LENGTH(c.identity) > 0 AND LENGTH(c.color) > 0", new String[] { String.valueOf(groupId) });
        Map<String, Integer> colors = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(70266)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70265)) {
                        {
                            long _loopCounter915 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter915", ++_loopCounter915);
                                if (!ListenerUtil.mutListener.listen(70264)) {
                                    colors.put(c.getString(0), c.getInt(1));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70263)) {
                        c.close();
                    }
                }
            }
        }
        return colors;
    }

    public List<Integer> getGroupIdsByIdentity(String identity) {
        Cursor c = this.databaseService.getReadableDatabase().query(this.getTableName(), new String[] { GroupMemberModel.COLUMN_GROUP_ID }, GroupMemberModel.COLUMN_IDENTITY + "=?", new String[] { identity }, null, null, null);
        List<Integer> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70270)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70269)) {
                        {
                            long _loopCounter916 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter916", ++_loopCounter916);
                                if (!ListenerUtil.mutListener.listen(70268)) {
                                    result.add(c.getInt(0));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70267)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    public int deleteByGroupId(int groupId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupMemberModel.COLUMN_GROUP_ID + "=?", new String[] { String.valueOf(groupId) });
    }

    public int delete(List<GroupMemberModel> modelsToRemove) {
        String[] args = new String[modelsToRemove.size()];
        if (!ListenerUtil.mutListener.listen(70277)) {
            {
                long _loopCounter917 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(70276) ? (n >= modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70275) ? (n <= modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70274) ? (n > modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70273) ? (n != modelsToRemove.size()) : (ListenerUtil.mutListener.listen(70272) ? (n == modelsToRemove.size()) : (n < modelsToRemove.size())))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter917", ++_loopCounter917);
                    if (!ListenerUtil.mutListener.listen(70271)) {
                        args[n] = String.valueOf(modelsToRemove.get(n).getId());
                    }
                }
            }
        }
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupMemberModel.COLUMN_ID + " IN (" + DatabaseUtil.makePlaceholders(args.length) + ")", args);
    }

    public int deleteByGroupIdAndIdentity(int groupId, String identity) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupMemberModel.COLUMN_GROUP_ID + "=?" + " AND " + GroupMemberModel.COLUMN_IDENTITY + "=?", new String[] { String.valueOf(groupId), identity });
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `group_member` (`id` INTEGER PRIMARY KEY AUTOINCREMENT , `identity` VARCHAR , `groupId` INTEGER , `isActive` SMALLINT NOT NULL )" };
    }
}
