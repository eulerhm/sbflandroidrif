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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.models.GroupMessagePendingMessageIdModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupMessagePendingMessageIdModelFactory extends ModelFactory {

    private static final Logger logger = LoggerFactory.getLogger(GroupMessagePendingMessageIdModel.class);

    public GroupMessagePendingMessageIdModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, GroupMessagePendingMessageIdModel.TABLE);
    }

    private GroupMessagePendingMessageIdModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70334)) {
            if ((ListenerUtil.mutListener.listen(70333) ? (cursor != null || (ListenerUtil.mutListener.listen(70332) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70331) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70330) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70329) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70328) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70332) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70331) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70330) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70329) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70328) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                // convert default
                return new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.CallbackInstance<GroupMessagePendingMessageIdModel>() {

                    @Override
                    public GroupMessagePendingMessageIdModel next(CursorHelper cursorHelper) {
                        return new GroupMessagePendingMessageIdModel(cursorHelper.getInt(GroupMessagePendingMessageIdModel.COLUMN_GROUP_MESSAGE_ID), cursorHelper.getString(GroupMessagePendingMessageIdModel.COLUMN_API_MESSAGE_ID));
                    }
                });
            }
        }
        return null;
    }

    private ContentValues buildContentValues(GroupMessagePendingMessageIdModel groupMessagePendingMessageIdModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70335)) {
            contentValues.put(GroupMessagePendingMessageIdModel.COLUMN_GROUP_MESSAGE_ID, groupMessagePendingMessageIdModel.getGroupMessageId());
        }
        if (!ListenerUtil.mutListener.listen(70336)) {
            contentValues.put(GroupMessagePendingMessageIdModel.COLUMN_API_MESSAGE_ID, groupMessagePendingMessageIdModel.getApiMessageId());
        }
        return contentValues;
    }

    public boolean create(GroupMessagePendingMessageIdModel groupMessagePendingMessageIdModel) {
        ContentValues contentValues = buildContentValues(groupMessagePendingMessageIdModel);
        if (!ListenerUtil.mutListener.listen(70337)) {
            this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        }
        if (!ListenerUtil.mutListener.listen(70338)) {
            logger.debug("created " + groupMessagePendingMessageIdModel.getApiMessageId());
        }
        return true;
    }

    public int delete(GroupMessagePendingMessageIdModel groupMessagePendingMessageIdModel) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupMessagePendingMessageIdModel.COLUMN_GROUP_MESSAGE_ID + " = ? " + "AND " + GroupMessagePendingMessageIdModel.COLUMN_API_MESSAGE_ID + " = ?", new String[] { String.valueOf(groupMessagePendingMessageIdModel.getGroupMessageId()), groupMessagePendingMessageIdModel.getApiMessageId() });
    }

    public int delete(int groupMessageId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupMessagePendingMessageIdModel.COLUMN_GROUP_MESSAGE_ID + " = ? ", new String[] { String.valueOf(groupMessageId) });
    }

    public long countByGroupMessage(int groupMessageId) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " " + "WHERE " + GroupMessagePendingMessageIdModel.COLUMN_GROUP_MESSAGE_ID + " = ? ", new String[] { String.valueOf(groupMessageId) }));
    }

    private GroupMessagePendingMessageIdModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70341)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70340)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70339)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    public GroupMessagePendingMessageIdModel get(int groupMessageId, String apiMessageId) {
        return this.getFirst(GroupMessagePendingMessageIdModel.COLUMN_GROUP_MESSAGE_ID + " = ? " + "AND " + GroupMessagePendingMessageIdModel.COLUMN_API_MESSAGE_ID + " = ?", new String[] { String.valueOf(groupMessageId), apiMessageId });
    }

    /**
     *  Select a group message identity model by a api message id
     */
    public GroupMessagePendingMessageIdModel get(String apiMessageId) {
        return this.getFirst(GroupMessagePendingMessageIdModel.COLUMN_API_MESSAGE_ID + " = ?", new String[] { apiMessageId });
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE " + GroupMessagePendingMessageIdModel.TABLE + "(" + "`" + GroupMessagePendingMessageIdModel.COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT," + "`" + GroupMessagePendingMessageIdModel.COLUMN_GROUP_MESSAGE_ID + "` INTEGER," + "`" + GroupMessagePendingMessageIdModel.COLUMN_API_MESSAGE_ID + "` VARCHAR" + ")" };
    }
}
