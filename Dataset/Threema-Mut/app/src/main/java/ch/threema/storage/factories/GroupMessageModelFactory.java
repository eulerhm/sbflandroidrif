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
import ch.threema.app.services.MessageService;
import ch.threema.client.MessageId;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.GroupModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GroupMessageModelFactory extends AbstractMessageModelFactory {

    public GroupMessageModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, GroupMessageModel.TABLE);
    }

    public List<GroupMessageModel> getAll() {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null));
    }

    public GroupMessageModel getByApiMessageIdAndIdentity(MessageId apiMessageId, String identity) {
        return getFirst(GroupMessageModel.COLUMN_API_MESSAGE_ID + "=?" + "AND " + GroupMessageModel.COLUMN_IDENTITY + "=?", new String[] { apiMessageId.toString(), identity });
    }

    public GroupMessageModel getByApiMessageIdAndIsOutbox(MessageId apiMessageId, boolean isOutbox) {
        return getFirst(GroupMessageModel.COLUMN_API_MESSAGE_ID + "=?" + "AND " + GroupMessageModel.COLUMN_OUTBOX + "=?", new String[] { apiMessageId.toString(), String.valueOf(isOutbox) });
    }

    public GroupMessageModel getByApiMessageId(MessageId apiMessageId) {
        return getFirst(GroupMessageModel.COLUMN_API_MESSAGE_ID + "=?", new String[] { apiMessageId.toString() });
    }

    public GroupMessageModel getById(int id) {
        return getFirst(GroupMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(id) });
    }

    public GroupMessageModel getByUid(String uid) {
        return getFirst(GroupMessageModel.COLUMN_UID + "=?", new String[] { uid });
    }

    public List<AbstractMessageModel> getMessagesByText(String text, boolean includeArchived) {
        if (includeArchived) {
            return convertAbstractList(this.databaseService.getReadableDatabase().rawQuery("SELECT * FROM " + GroupMessageModel.TABLE + " WHERE ( ( body LIKE ? " + " AND type IN (" + MessageType.TEXT.ordinal() + "," + MessageType.LOCATION.ordinal() + "," + MessageType.BALLOT.ordinal() + ") )" + " OR ( caption LIKE ? " + " AND type IN (" + MessageType.IMAGE.ordinal() + "," + MessageType.FILE.ordinal() + ") ) )" + " AND isStatusMessage = 0" + " ORDER BY createdAtUtc DESC" + " LIMIT 200", new String[] { "%" + text + "%", "%" + text + "%" }));
        } else {
            return convertAbstractList(this.databaseService.getReadableDatabase().rawQuery("SELECT * FROM " + GroupMessageModel.TABLE + " m" + " INNER JOIN " + GroupModel.TABLE + " g ON g.id = m.groupId" + " WHERE g.isArchived = 0" + " AND ( ( m.body LIKE ? " + " AND m.type IN (" + MessageType.TEXT.ordinal() + "," + MessageType.LOCATION.ordinal() + "," + MessageType.BALLOT.ordinal() + ") )" + " OR ( m.caption LIKE ? " + " AND m.type IN (" + MessageType.IMAGE.ordinal() + "," + MessageType.FILE.ordinal() + ") ) )" + " AND m.isStatusMessage = 0" + " ORDER BY m.createdAtUtc DESC" + " LIMIT 200", new String[] { "%" + text + "%", "%" + text + "%" }));
        }
    }

    private List<AbstractMessageModel> convertAbstractList(Cursor cursor) {
        List<AbstractMessageModel> result = new ArrayList<AbstractMessageModel>();
        if (!ListenerUtil.mutListener.listen(70281)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70280)) {
                        {
                            long _loopCounter918 = 0;
                            while (cursor.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter918", ++_loopCounter918);
                                if (!ListenerUtil.mutListener.listen(70279)) {
                                    result.add(convert(cursor));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70278)) {
                        cursor.close();
                    }
                }
            }
        }
        return result;
    }

    private List<GroupMessageModel> convertList(Cursor c) {
        List<GroupMessageModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70285)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70284)) {
                        {
                            long _loopCounter919 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter919", ++_loopCounter919);
                                if (!ListenerUtil.mutListener.listen(70283)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70282)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    private GroupMessageModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70294)) {
            if ((ListenerUtil.mutListener.listen(70291) ? (cursor != null || (ListenerUtil.mutListener.listen(70290) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70289) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70288) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70287) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70286) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70290) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70289) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70288) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70287) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70286) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final GroupMessageModel c = new GroupMessageModel();
                if (!ListenerUtil.mutListener.listen(70293)) {
                    // convert default
                    super.convert(c, new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70292)) {
                                c.setGroupId(cursorHelper.getInt(GroupMessageModel.COLUMN_GROUP_ID));
                            }
                            return false;
                        }
                    }));
                }
                return c;
            }
        }
        return null;
    }

    public long countMessages(int groupId) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + GroupMessageModel.COLUMN_GROUP_ID + "=?", new String[] { String.valueOf(groupId) }));
    }

    public long countUnreadMessages(int groupId) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + GroupMessageModel.COLUMN_GROUP_ID + "=?" + " AND " + GroupMessageModel.COLUMN_OUTBOX + "=0" + " AND " + GroupMessageModel.COLUMN_IS_SAVED + "=1" + " AND " + GroupMessageModel.COLUMN_IS_READ + "=0" + " AND " + GroupMessageModel.COLUMN_IS_STATUS_MESSAGE + "=0", new String[] { String.valueOf(groupId) }));
    }

    public List<GroupMessageModel> getUnreadMessages(int groupId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, GroupMessageModel.COLUMN_GROUP_ID + "=?" + " AND " + GroupMessageModel.COLUMN_OUTBOX + "=0" + " AND " + GroupMessageModel.COLUMN_IS_SAVED + "=1" + " AND " + GroupMessageModel.COLUMN_IS_READ + "=0" + " AND " + GroupMessageModel.COLUMN_IS_STATUS_MESSAGE + "=0", new String[] { String.valueOf(groupId) }, null, null, null));
    }

    public long countByTypes(MessageType[] messageTypes) {
        String[] args = new String[messageTypes.length];
        if (!ListenerUtil.mutListener.listen(70301)) {
            {
                long _loopCounter920 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(70300) ? (n >= messageTypes.length) : (ListenerUtil.mutListener.listen(70299) ? (n <= messageTypes.length) : (ListenerUtil.mutListener.listen(70298) ? (n > messageTypes.length) : (ListenerUtil.mutListener.listen(70297) ? (n != messageTypes.length) : (ListenerUtil.mutListener.listen(70296) ? (n == messageTypes.length) : (n < messageTypes.length)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter920", ++_loopCounter920);
                    if (!ListenerUtil.mutListener.listen(70295)) {
                        args[n] = String.valueOf(messageTypes[n].ordinal());
                    }
                }
            }
        }
        Cursor c = this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + GroupMessageModel.COLUMN_TYPE + " IN (" + DatabaseUtil.makePlaceholders(args.length) + ")", args);
        return DatabaseUtil.count(c);
    }

    public boolean createOrUpdate(GroupMessageModel groupMessageModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(70310)) {
            if ((ListenerUtil.mutListener.listen(70306) ? (groupMessageModel.getId() >= 0) : (ListenerUtil.mutListener.listen(70305) ? (groupMessageModel.getId() <= 0) : (ListenerUtil.mutListener.listen(70304) ? (groupMessageModel.getId() < 0) : (ListenerUtil.mutListener.listen(70303) ? (groupMessageModel.getId() != 0) : (ListenerUtil.mutListener.listen(70302) ? (groupMessageModel.getId() == 0) : (groupMessageModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, GroupMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupMessageModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(70309)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(70308)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(70307)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(groupMessageModel);
        } else {
            return update(groupMessageModel);
        }
    }

    public boolean create(GroupMessageModel groupMessageModel) {
        ContentValues contentValues = this.buildContentValues(groupMessageModel);
        if (!ListenerUtil.mutListener.listen(70311)) {
            contentValues.put(GroupMessageModel.COLUMN_GROUP_ID, groupMessageModel.getGroupId());
        }
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70318)) {
            if ((ListenerUtil.mutListener.listen(70316) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70315) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70314) ? (newId < 0) : (ListenerUtil.mutListener.listen(70313) ? (newId != 0) : (ListenerUtil.mutListener.listen(70312) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70317)) {
                    groupMessageModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public boolean update(GroupMessageModel groupMessageModel) {
        ContentValues contentValues = this.buildContentValues(groupMessageModel);
        if (!ListenerUtil.mutListener.listen(70319)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, GroupMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupMessageModel.getId()) });
        }
        return true;
    }

    public List<GroupMessageModel> find(int groupId, MessageService.MessageFilter filter) {
        QueryBuilder queryBuilder = new QueryBuilder();
        // sort by id!
        String orderBy = GroupMessageModel.COLUMN_ID + " DESC";
        List<String> placeholders = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70320)) {
            queryBuilder.appendWhere(GroupMessageModel.COLUMN_GROUP_ID + "=?");
        }
        if (!ListenerUtil.mutListener.listen(70321)) {
            placeholders.add(String.valueOf(groupId));
        }
        if (!ListenerUtil.mutListener.listen(70322)) {
            // default filters
            this.appendFilter(queryBuilder, filter, placeholders);
        }
        if (!ListenerUtil.mutListener.listen(70323)) {
            queryBuilder.setTables(this.getTableName());
        }
        List<GroupMessageModel> messageModels = convertList(queryBuilder.query(this.databaseService.getReadableDatabase(), null, null, placeholders.toArray(new String[placeholders.size()]), null, null, orderBy, this.limitFilter(filter)));
        if (!ListenerUtil.mutListener.listen(70324)) {
            this.postFilter(messageModels, filter);
        }
        return messageModels;
    }

    public List<GroupMessageModel> getByGroupIdUnsorted(int groupId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, GroupMessageModel.COLUMN_GROUP_ID + "=?", new String[] { String.valueOf(groupId) }, null, null, null));
    }

    public int delete(GroupMessageModel groupMessageModel) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupMessageModel.getId()) });
    }

    public int deleteByGroupId(int groupId) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), GroupMessageModel.COLUMN_GROUP_ID + "=?", new String[] { String.valueOf(groupId) });
    }

    private GroupMessageModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70327)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70326)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70325)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `" + GroupMessageModel.TABLE + "`" + "(" + "`" + GroupMessageModel.COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT , " + "`" + GroupMessageModel.COLUMN_UID + "` VARCHAR , " + "`" + GroupMessageModel.COLUMN_API_MESSAGE_ID + "` VARCHAR , " + "`" + GroupMessageModel.COLUMN_GROUP_ID + "` INTEGER NOT NULL , " + // TODO: remove field
        "`" + GroupMessageModel.COLUMN_IDENTITY + "` VARCHAR , " + // TODO: change to TINYINT
        "`" + GroupMessageModel.COLUMN_OUTBOX + "` SMALLINT , " + "`" + GroupMessageModel.COLUMN_TYPE + "` INTEGER ," + "`" + GroupMessageModel.COLUMN_CORRELATION_ID + "` VARCHAR ," + "`" + GroupMessageModel.COLUMN_BODY + "` VARCHAR ," + "`" + GroupMessageModel.COLUMN_CAPTION + "` VARCHAR ," + // TODO: change to TINYINT
        "`" + GroupMessageModel.COLUMN_IS_READ + "` SMALLINT ," + // TODO: change to TINYINT
        "`" + GroupMessageModel.COLUMN_IS_SAVED + "` SMALLINT ," + "`" + GroupMessageModel.COLUMN_IS_QUEUED + "` TINYINT ," + "`" + GroupMessageModel.COLUMN_STATE + "` VARCHAR , " + "`" + GroupMessageModel.COLUMN_POSTED_AT + "` BIGINT , " + "`" + GroupMessageModel.COLUMN_CREATED_AT + "` BIGINT , " + "`" + GroupMessageModel.COLUMN_MODIFIED_AT + "` BIGINT , " + // TODO: change to TINYINT
        "`" + GroupMessageModel.COLUMN_IS_STATUS_MESSAGE + "` SMALLINT ," + "`" + GroupMessageModel.COLUMN_QUOTED_MESSAGE_API_MESSAGE_ID + "` VARCHAR ," + "`" + GroupMessageModel.COLUMN_MESSAGE_CONTENTS_TYPE + "` TINYINT ," + "`" + GroupMessageModel.COLUMN_MESSAGE_FLAGS + "` INT );", // indices
        "CREATE INDEX `m_group_message_outbox_idx` ON `" + GroupMessageModel.TABLE + "` ( `" + GroupMessageModel.COLUMN_OUTBOX + "` );", "CREATE INDEX `groupMessageUidIdx` ON `" + GroupMessageModel.TABLE + "` ( `" + GroupMessageModel.COLUMN_UID + "` );", "CREATE INDEX `m_group_message_identity_idx` ON `" + GroupMessageModel.TABLE + "` ( `" + GroupMessageModel.COLUMN_IDENTITY + "` );", "CREATE INDEX `m_group_message_groupId_idx` ON `" + GroupMessageModel.TABLE + "` ( `" + GroupMessageModel.COLUMN_GROUP_ID + "` );", "CREATE INDEX `groupMessageApiMessageIdIdx` ON `" + GroupMessageModel.TABLE + "` ( `" + GroupMessageModel.COLUMN_API_MESSAGE_ID + "` );", "CREATE INDEX `groupMessageCorrelationIdIdx` ON `" + GroupMessageModel.TABLE + "` ( `" + GroupMessageModel.COLUMN_CORRELATION_ID + "` );" };
    }
}
