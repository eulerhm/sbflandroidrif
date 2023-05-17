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
import ch.threema.storage.models.ContactModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.MessageModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MessageModelFactory extends AbstractMessageModelFactory {

    public MessageModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, MessageModel.TABLE);
    }

    public List<MessageModel> getAll() {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null));
    }

    public MessageModel getByApiMessageIdAndIdentity(MessageId apiMessageId, String identity) {
        return getFirst(MessageModel.COLUMN_API_MESSAGE_ID + "=?" + " AND " + MessageModel.COLUMN_IDENTITY + "=?", new String[] { apiMessageId.toString(), identity });
    }

    public MessageModel getByApiMessageIdAndIsOutbox(MessageId apiMessageId, boolean isOutbox) {
        return getFirst(MessageModel.COLUMN_API_MESSAGE_ID + "=?" + " AND " + MessageModel.COLUMN_OUTBOX + "=?", new String[] { apiMessageId.toString(), isOutbox ? "1" : "0" });
    }

    public MessageModel getByApiMessageId(MessageId apiMessageId) {
        return getFirst(GroupMessageModel.COLUMN_API_MESSAGE_ID + "=?", new String[] { apiMessageId.toString() });
    }

    public MessageModel getById(int id) {
        return getFirst(MessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(id) });
    }

    public MessageModel getByUid(String uid) {
        return getFirst(MessageModel.COLUMN_UID + "=?", new String[] { uid });
    }

    public List<AbstractMessageModel> getMessagesByText(String text, boolean includeArchived) {
        if (includeArchived) {
            return convertAbstractList(this.databaseService.getReadableDatabase().rawQuery("SELECT * FROM " + MessageModel.TABLE + " WHERE ( ( body LIKE ? " + " AND type IN (" + MessageType.TEXT.ordinal() + "," + MessageType.LOCATION.ordinal() + "," + MessageType.BALLOT.ordinal() + ") )" + " OR ( caption LIKE ? " + " AND type IN (" + MessageType.IMAGE.ordinal() + "," + MessageType.FILE.ordinal() + ") ) )" + " AND isStatusMessage = 0" + " ORDER BY createdAtUtc DESC" + " LIMIT 200", new String[] { "%" + text + "%", "%" + text + "%" }));
        } else {
            return convertAbstractList(this.databaseService.getReadableDatabase().rawQuery("SELECT * FROM " + MessageModel.TABLE + " m" + " INNER JOIN " + ContactModel.TABLE + " c ON c.identity = m.identity" + " WHERE c.isArchived = 0" + " AND ( ( m.body LIKE ? " + " AND m.type IN (" + MessageType.TEXT.ordinal() + "," + MessageType.LOCATION.ordinal() + "," + MessageType.BALLOT.ordinal() + ") )" + " OR ( m.caption LIKE ? " + " AND m.type IN (" + MessageType.IMAGE.ordinal() + "," + MessageType.FILE.ordinal() + ") ) )" + " AND m.isStatusMessage = 0" + " ORDER BY m.createdAtUtc DESC" + " LIMIT 200", new String[] { "%" + text + "%", "%" + text + "%" }));
        }
    }

    private List<AbstractMessageModel> convertAbstractList(Cursor cursor) {
        List<AbstractMessageModel> result = new ArrayList<AbstractMessageModel>();
        if (!ListenerUtil.mutListener.listen(70449)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70448)) {
                        {
                            long _loopCounter922 = 0;
                            while (cursor.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter922", ++_loopCounter922);
                                if (!ListenerUtil.mutListener.listen(70447)) {
                                    result.add(convert(cursor));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70446)) {
                        cursor.close();
                    }
                }
            }
        }
        return result;
    }

    private List<MessageModel> convertList(Cursor c) {
        List<MessageModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70453)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70452)) {
                        {
                            long _loopCounter923 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter923", ++_loopCounter923);
                                if (!ListenerUtil.mutListener.listen(70451)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70450)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    private MessageModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70461)) {
            if ((ListenerUtil.mutListener.listen(70459) ? (cursor != null || (ListenerUtil.mutListener.listen(70458) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70457) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70456) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70455) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70454) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70458) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70457) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70456) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70455) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70454) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                MessageModel c = new MessageModel();
                if (!ListenerUtil.mutListener.listen(70460)) {
                    // convert default
                    super.convert(c, new CursorHelper(cursor, columnIndexCache));
                }
                return c;
            }
        }
        return null;
    }

    public long countMessages(String identity) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + MessageModel.COLUMN_IDENTITY + "=?", new String[] { identity }));
    }

    public long countUnreadMessages(String identity) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + MessageModel.COLUMN_IDENTITY + "=?" + " AND " + MessageModel.COLUMN_OUTBOX + "=0" + " AND " + MessageModel.COLUMN_IS_SAVED + "=1" + " AND " + MessageModel.COLUMN_IS_READ + "=0" + " AND " + MessageModel.COLUMN_IS_STATUS_MESSAGE + "=0", new String[] { identity }));
    }

    public List<MessageModel> getUnreadMessages(String identity) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, MessageModel.COLUMN_IDENTITY + "=?" + " AND " + MessageModel.COLUMN_OUTBOX + "=0" + " AND " + MessageModel.COLUMN_IS_SAVED + "=1" + " AND " + MessageModel.COLUMN_IS_READ + "=0" + " AND " + MessageModel.COLUMN_IS_STATUS_MESSAGE + "=0", new String[] { identity }, null, null, null));
    }

    public MessageModel getLastMessage(String identity) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, MessageModel.COLUMN_IDENTITY + "=?", new String[] { identity }, null, null, MessageModel.COLUMN_ID + " DESC", "1");
        if (!ListenerUtil.mutListener.listen(70464)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70463)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70462)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    public long countByTypes(MessageType[] messageTypes) {
        String[] args = new String[messageTypes.length];
        if (!ListenerUtil.mutListener.listen(70471)) {
            {
                long _loopCounter924 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(70470) ? (n >= messageTypes.length) : (ListenerUtil.mutListener.listen(70469) ? (n <= messageTypes.length) : (ListenerUtil.mutListener.listen(70468) ? (n > messageTypes.length) : (ListenerUtil.mutListener.listen(70467) ? (n != messageTypes.length) : (ListenerUtil.mutListener.listen(70466) ? (n == messageTypes.length) : (n < messageTypes.length)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter924", ++_loopCounter924);
                    if (!ListenerUtil.mutListener.listen(70465)) {
                        args[n] = String.valueOf(messageTypes[n].ordinal());
                    }
                }
            }
        }
        Cursor c = this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + MessageModel.COLUMN_TYPE + " IN (" + DatabaseUtil.makePlaceholders(args.length) + ")", args);
        return DatabaseUtil.count(c);
    }

    public boolean createOrUpdate(MessageModel messageModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(70480)) {
            if ((ListenerUtil.mutListener.listen(70476) ? (messageModel.getId() >= 0) : (ListenerUtil.mutListener.listen(70475) ? (messageModel.getId() <= 0) : (ListenerUtil.mutListener.listen(70474) ? (messageModel.getId() < 0) : (ListenerUtil.mutListener.listen(70473) ? (messageModel.getId() != 0) : (ListenerUtil.mutListener.listen(70472) ? (messageModel.getId() == 0) : (messageModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, MessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(messageModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(70479)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(70478)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(70477)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(messageModel);
        } else {
            return update(messageModel);
        }
    }

    public boolean create(MessageModel messageModel) {
        ContentValues contentValues = this.buildContentValues(messageModel);
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70487)) {
            if ((ListenerUtil.mutListener.listen(70485) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70484) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70483) ? (newId < 0) : (ListenerUtil.mutListener.listen(70482) ? (newId != 0) : (ListenerUtil.mutListener.listen(70481) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70486)) {
                    messageModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    public boolean update(MessageModel messageModel) {
        ContentValues contentValues = this.buildContentValues(messageModel);
        if (!ListenerUtil.mutListener.listen(70488)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, MessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(messageModel.getId()) });
        }
        return true;
    }

    public int delete(MessageModel messageModel) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), MessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(messageModel.getId()) });
    }

    public List<MessageModel> find(String identity, MessageService.MessageFilter filter) {
        QueryBuilder queryBuilder = new QueryBuilder();
        // sort by id!
        String orderBy = MessageModel.COLUMN_ID + " DESC";
        List<String> placeholders = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70489)) {
            queryBuilder.appendWhere(MessageModel.COLUMN_IDENTITY + "=?");
        }
        if (!ListenerUtil.mutListener.listen(70490)) {
            placeholders.add(identity);
        }
        if (!ListenerUtil.mutListener.listen(70491)) {
            // default filters
            this.appendFilter(queryBuilder, filter, placeholders);
        }
        if (!ListenerUtil.mutListener.listen(70492)) {
            queryBuilder.setTables(this.getTableName());
        }
        List<MessageModel> messageModels = convertList(queryBuilder.query(this.databaseService.getReadableDatabase(), null, null, placeholders.toArray(new String[placeholders.size()]), null, null, orderBy, this.limitFilter(filter)));
        if (!ListenerUtil.mutListener.listen(70493)) {
            this.postFilter(messageModels, filter);
        }
        return messageModels;
    }

    public List<MessageModel> getByIdentityUnsorted(String identity) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, MessageModel.COLUMN_IDENTITY + "=?", new String[] { identity }, null, null, null));
    }

    private MessageModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(70496)) {
                cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
            }
            if (!ListenerUtil.mutListener.listen(70498)) {
                if ((ListenerUtil.mutListener.listen(70497) ? (cursor != null || cursor.moveToFirst()) : (cursor != null && cursor.moveToFirst()))) {
                    return convert(cursor);
                }
            }
        } finally {
            if (!ListenerUtil.mutListener.listen(70495)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(70494)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String[] getStatements() {
        return new String[] { // create table
        "CREATE TABLE `" + MessageModel.TABLE + "`(" + "`" + MessageModel.COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT , " + "`" + MessageModel.COLUMN_UID + "` VARCHAR , " + "`" + MessageModel.COLUMN_API_MESSAGE_ID + "` VARCHAR , " + "`" + MessageModel.COLUMN_IDENTITY + "` VARCHAR , " + // TODO: change to TINYINT
        "`" + MessageModel.COLUMN_OUTBOX + "` SMALLINT , " + "`" + MessageModel.COLUMN_TYPE + "` INTEGER , " + "`" + MessageModel.COLUMN_BODY + "` VARCHAR , " + "`" + MessageModel.COLUMN_CORRELATION_ID + "` VARCHAR , " + "`" + MessageModel.COLUMN_CAPTION + "` VARCHAR , " + // TODO: change to TINYINT
        "`" + MessageModel.COLUMN_IS_READ + "` SMALLINT , " + // TODO: change to TINYINT
        "`" + MessageModel.COLUMN_IS_SAVED + "` SMALLINT , " + "`" + MessageModel.COLUMN_IS_QUEUED + "` TINYINT , " + "`" + MessageModel.COLUMN_STATE + "` VARCHAR , " + "`" + MessageModel.COLUMN_POSTED_AT + "` BIGINT , " + "`" + MessageModel.COLUMN_CREATED_AT + "` BIGINT , " + "`" + MessageModel.COLUMN_MODIFIED_AT + "` BIGINT , " + // TODO: change to TINYINT
        "`" + MessageModel.COLUMN_IS_STATUS_MESSAGE + "` SMALLINT ," + "`" + MessageModel.COLUMN_QUOTED_MESSAGE_API_MESSAGE_ID + "` VARCHAR ," + "`" + MessageModel.COLUMN_MESSAGE_CONTENTS_TYPE + "` TINYINT ," + "`" + MessageModel.COLUMN_MESSAGE_FLAGS + "` INT )", // indices
        "CREATE INDEX `messageUidIdx` ON `" + MessageModel.TABLE + "` ( `" + MessageModel.COLUMN_UID + "` )", "CREATE INDEX `message_identity_idx` ON `" + MessageModel.TABLE + "` ( `" + MessageModel.COLUMN_IDENTITY + "` )", "CREATE INDEX `messageApiMessageIdIdx` ON `" + MessageModel.TABLE + "` ( `" + MessageModel.COLUMN_API_MESSAGE_ID + "` )", "CREATE INDEX `message_outbox_idx` ON `" + MessageModel.TABLE + "` ( `" + MessageModel.COLUMN_OUTBOX + "` )", "CREATE INDEX `messageCorrelationIdIx` ON `" + MessageModel.TABLE + "` ( `" + MessageModel.COLUMN_CORRELATION_ID + "` )", "CREATE INDEX `message_count_idx` ON `" + MessageModel.TABLE + "`(`" + MessageModel.COLUMN_IDENTITY + "`, `" + MessageModel.COLUMN_OUTBOX + "`, `" + MessageModel.COLUMN_IS_SAVED + "`, `" + MessageModel.COLUMN_IS_READ + "`, `" + MessageModel.COLUMN_IS_STATUS_MESSAGE + "`)", "CREATE INDEX `message_queue_idx` ON `" + MessageModel.TABLE + "`(`" + MessageModel.COLUMN_TYPE + "`, `" + MessageModel.COLUMN_IS_QUEUED + "`, `" + MessageModel.COLUMN_OUTBOX + "`)" };
    }
}
