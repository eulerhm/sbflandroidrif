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
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.MessageType;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class DistributionListMessageModelFactory extends AbstractMessageModelFactory {

    public DistributionListMessageModelFactory(DatabaseServiceNew databaseService) {
        super(databaseService, DistributionListMessageModel.TABLE);
    }

    public List<DistributionListMessageModel> getAll() {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null));
    }

    public DistributionListMessageModel getById(int id) {
        return getFirst(DistributionListMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(id) });
    }

    public DistributionListMessageModel getByApiMessageId(String apiMessageId) {
        return getFirst(DistributionListMessageModel.COLUMN_API_MESSAGE_ID + "=?", new String[] { apiMessageId });
    }

    private List<DistributionListMessageModel> convertList(Cursor c) {
        List<DistributionListMessageModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70123)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70122)) {
                        {
                            long _loopCounter911 = 0;
                            while (c.moveToNext()) {
                                ListenerUtil.loopListener.listen("_loopCounter911", ++_loopCounter911);
                                if (!ListenerUtil.mutListener.listen(70121)) {
                                    result.add(convert(c));
                                }
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70120)) {
                        c.close();
                    }
                }
            }
        }
        return result;
    }

    private DistributionListMessageModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70132)) {
            if ((ListenerUtil.mutListener.listen(70129) ? (cursor != null || (ListenerUtil.mutListener.listen(70128) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70127) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70126) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70125) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70124) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70128) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70127) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70126) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70125) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70124) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final DistributionListMessageModel c = new DistributionListMessageModel();
                if (!ListenerUtil.mutListener.listen(70131)) {
                    // convert default
                    super.convert(c, new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70130)) {
                                c.setDistributionListId(cursorHelper.getInt(DistributionListMessageModel.COLUMN_DISTRIBUTION_LIST_ID));
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

    public long countByTypes(MessageType[] messageTypes) {
        String[] args = new String[messageTypes.length];
        if (!ListenerUtil.mutListener.listen(70139)) {
            {
                long _loopCounter912 = 0;
                for (int n = 0; (ListenerUtil.mutListener.listen(70138) ? (n >= messageTypes.length) : (ListenerUtil.mutListener.listen(70137) ? (n <= messageTypes.length) : (ListenerUtil.mutListener.listen(70136) ? (n > messageTypes.length) : (ListenerUtil.mutListener.listen(70135) ? (n != messageTypes.length) : (ListenerUtil.mutListener.listen(70134) ? (n == messageTypes.length) : (n < messageTypes.length)))))); n++) {
                    ListenerUtil.loopListener.listen("_loopCounter912", ++_loopCounter912);
                    if (!ListenerUtil.mutListener.listen(70133)) {
                        args[n] = String.valueOf(messageTypes[n].ordinal());
                    }
                }
            }
        }
        Cursor c = this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " " + "WHERE " + DistributionListMessageModel.COLUMN_TYPE + " IN (" + DatabaseUtil.makePlaceholders(args.length) + ")", args);
        return DatabaseUtil.count(c);
    }

    public boolean createOrUpdate(DistributionListMessageModel distributionListMessageModel) {
        boolean insert = true;
        if (!ListenerUtil.mutListener.listen(70148)) {
            if ((ListenerUtil.mutListener.listen(70144) ? (distributionListMessageModel.getId() >= 0) : (ListenerUtil.mutListener.listen(70143) ? (distributionListMessageModel.getId() <= 0) : (ListenerUtil.mutListener.listen(70142) ? (distributionListMessageModel.getId() < 0) : (ListenerUtil.mutListener.listen(70141) ? (distributionListMessageModel.getId() != 0) : (ListenerUtil.mutListener.listen(70140) ? (distributionListMessageModel.getId() == 0) : (distributionListMessageModel.getId() > 0))))))) {
                Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, DistributionListMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListMessageModel.getId()) }, null, null, null);
                if (!ListenerUtil.mutListener.listen(70147)) {
                    if (cursor != null) {
                        try {
                            if (!ListenerUtil.mutListener.listen(70146)) {
                                insert = !cursor.moveToNext();
                            }
                        } finally {
                            if (!ListenerUtil.mutListener.listen(70145)) {
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        if (insert) {
            return create(distributionListMessageModel);
        } else {
            return update(distributionListMessageModel);
        }
    }

    private boolean create(DistributionListMessageModel distributionListMessageModel) {
        ContentValues contentValues = this.buildContentValues(distributionListMessageModel);
        if (!ListenerUtil.mutListener.listen(70149)) {
            contentValues.put(DistributionListMessageModel.COLUMN_DISTRIBUTION_LIST_ID, distributionListMessageModel.getDistributionListId());
        }
        long newId = this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        if (!ListenerUtil.mutListener.listen(70156)) {
            if ((ListenerUtil.mutListener.listen(70154) ? (newId >= 0) : (ListenerUtil.mutListener.listen(70153) ? (newId <= 0) : (ListenerUtil.mutListener.listen(70152) ? (newId < 0) : (ListenerUtil.mutListener.listen(70151) ? (newId != 0) : (ListenerUtil.mutListener.listen(70150) ? (newId == 0) : (newId > 0))))))) {
                if (!ListenerUtil.mutListener.listen(70155)) {
                    distributionListMessageModel.setId((int) newId);
                }
                return true;
            }
        }
        return false;
    }

    private boolean update(DistributionListMessageModel distributionListMessageModel) {
        ContentValues contentValues = this.buildContentValues(distributionListMessageModel);
        if (!ListenerUtil.mutListener.listen(70157)) {
            this.databaseService.getWritableDatabase().update(this.getTableName(), contentValues, DistributionListMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListMessageModel.getId()) });
        }
        return true;
    }

    public long countMessages(int groupId) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + DistributionListMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(groupId) }));
    }

    public List<DistributionListMessageModel> find(int distributionListId, MessageService.MessageFilter filter) {
        QueryBuilder queryBuilder = new QueryBuilder();
        // sort by id!
        String orderBy = DistributionListMessageModel.COLUMN_ID + " DESC";
        List<String> placeholders = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70158)) {
            queryBuilder.appendWhere(DistributionListMessageModel.COLUMN_DISTRIBUTION_LIST_ID + "=?");
        }
        if (!ListenerUtil.mutListener.listen(70159)) {
            placeholders.add(String.valueOf(distributionListId));
        }
        if (!ListenerUtil.mutListener.listen(70160)) {
            // default filters
            this.appendFilter(queryBuilder, filter, placeholders);
        }
        if (!ListenerUtil.mutListener.listen(70161)) {
            queryBuilder.setTables(this.getTableName());
        }
        List<DistributionListMessageModel> messageModels = convertList(queryBuilder.query(this.databaseService.getReadableDatabase(), null, null, placeholders.toArray(new String[placeholders.size()]), null, null, orderBy, this.limitFilter(filter)));
        if (!ListenerUtil.mutListener.listen(70162)) {
            this.postFilter(messageModels, filter);
        }
        return messageModels;
    }

    public List<DistributionListMessageModel> getByDistributionListIdUnsorted(int distributionListId) {
        return convertList(this.databaseService.getReadableDatabase().query(this.getTableName(), null, DistributionListMessageModel.COLUMN_DISTRIBUTION_LIST_ID + "=?", new String[] { String.valueOf(distributionListId) }, null, null, null));
    }

    public int delete(DistributionListMessageModel distributionListMessageModel) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), DistributionListMessageModel.COLUMN_ID + "=?", new String[] { String.valueOf(distributionListMessageModel.getId()) });
    }

    private DistributionListMessageModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70165)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70164)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70163)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE `" + DistributionListMessageModel.TABLE + "`" + "(" + "`" + DistributionListMessageModel.COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT ," + "`" + DistributionListMessageModel.COLUMN_UID + "` VARCHAR ," + "`" + DistributionListMessageModel.COLUMN_API_MESSAGE_ID + "` VARCHAR ," + "`" + DistributionListMessageModel.COLUMN_DISTRIBUTION_LIST_ID + "` INTEGER NOT NULL ," + // TODO: remove identity field
        "`" + DistributionListMessageModel.COLUMN_IDENTITY + "` VARCHAR ," + // TODO: change to TINYINT
        "`" + DistributionListMessageModel.COLUMN_OUTBOX + "` SMALLINT ," + "`" + DistributionListMessageModel.COLUMN_TYPE + "` INTEGER ," + "`" + DistributionListMessageModel.COLUMN_CORRELATION_ID + "` VARCHAR ," + "`" + DistributionListMessageModel.COLUMN_BODY + "` VARCHAR ," + "`" + DistributionListMessageModel.COLUMN_CAPTION + "` VARCHAR ," + // TODO: change to TINYINT
        "`" + DistributionListMessageModel.COLUMN_IS_READ + "` SMALLINT ," + // TODO: change to TINYINT
        "`" + DistributionListMessageModel.COLUMN_IS_SAVED + "` SMALLINT ," + "`" + DistributionListMessageModel.COLUMN_IS_QUEUED + "` TINYINT ," + "`" + DistributionListMessageModel.COLUMN_STATE + "` VARCHAR ," + "`" + DistributionListMessageModel.COLUMN_POSTED_AT + "` BIGINT ," + "`" + DistributionListMessageModel.COLUMN_CREATED_AT + "` BIGINT ," + "`" + DistributionListMessageModel.COLUMN_MODIFIED_AT + "` BIGINT ," + // TODO: change to TINYINT
        "`" + DistributionListMessageModel.COLUMN_IS_STATUS_MESSAGE + "` SMALLINT ," + "`" + DistributionListMessageModel.COLUMN_QUOTED_MESSAGE_API_MESSAGE_ID + "` VARCHAR ," + "`" + DistributionListMessageModel.COLUMN_MESSAGE_CONTENTS_TYPE + "` TINYINT ," + "`" + DistributionListMessageModel.COLUMN_MESSAGE_FLAGS + "` INT );", // indices
        "CREATE INDEX `distributionListDistributionListIdIdx` ON `" + DistributionListMessageModel.TABLE + "` ( `" + DistributionListMessageModel.COLUMN_DISTRIBUTION_LIST_ID + "` )", "CREATE INDEX `distribution_list_message_outbox_idx` ON `" + DistributionListMessageModel.TABLE + "` ( `" + DistributionListMessageModel.COLUMN_OUTBOX + "` )", "CREATE INDEX `distributionListMessageIdIdx` ON `" + DistributionListMessageModel.TABLE + "` ( `" + DistributionListMessageModel.COLUMN_API_MESSAGE_ID + "` )", "CREATE INDEX `distributionListMessageUidIdx` ON `" + DistributionListMessageModel.TABLE + "` ( `" + DistributionListMessageModel.COLUMN_UID + "` )", "CREATE INDEX `distribution_list_message_identity_idx` ON `" + DistributionListMessageModel.TABLE + "` ( `" + DistributionListMessageModel.COLUMN_IDENTITY + "` )", "CREATE INDEX `distributionListCorrelationIdIdx` ON `" + DistributionListMessageModel.TABLE + "` ( `" + DistributionListMessageModel.COLUMN_CORRELATION_ID + "` )" };
    }
}
