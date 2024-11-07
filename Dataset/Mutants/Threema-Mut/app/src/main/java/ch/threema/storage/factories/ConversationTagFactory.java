/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
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
import java.util.ArrayList;
import java.util.List;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.models.ConversationTagModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConversationTagFactory extends ModelFactory {

    private static final Logger logger = LoggerFactory.getLogger(ConversationTagFactory.class);

    public ConversationTagFactory(DatabaseServiceNew databaseService) {
        super(databaseService, ConversationTagModel.TABLE);
    }

    public List<ConversationTagModel> getAll() {
        Cursor cursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(70052)) {
                cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, null, null, null, null, null);
            }
            return convertList(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(70051)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(70050)) {
                        cursor.close();
                    }
                }
            }
        }
    }

    public List<ConversationTagModel> getByConversationUid(String conversationUid) {
        Cursor cursor = null;
        try {
            if (!ListenerUtil.mutListener.listen(70055)) {
                cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, ConversationTagModel.COLUMN_CONVERSATION_UID + "=?", new String[] { conversationUid }, null, null, null);
            }
            return convertList(cursor);
        } finally {
            if (!ListenerUtil.mutListener.listen(70054)) {
                if (cursor != null) {
                    if (!ListenerUtil.mutListener.listen(70053)) {
                        cursor.close();
                    }
                }
            }
        }
    }

    public ConversationTagModel getByConversationUidAndTag(String conversationUid, String tag) {
        return getFirst(ConversationTagModel.COLUMN_CONVERSATION_UID + "=? AND " + ConversationTagModel.COLUMN_TAG + "=? ", new String[] { conversationUid, tag });
    }

    public long countByTag(String tag) {
        return DatabaseUtil.count(this.databaseService.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE " + ConversationTagModel.COLUMN_TAG + "=?", new String[] { tag }));
    }

    private List<ConversationTagModel> convertList(Cursor cursor) {
        List<ConversationTagModel> result = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(70058)) {
            if (cursor != null) {
                if (!ListenerUtil.mutListener.listen(70057)) {
                    {
                        long _loopCounter908 = 0;
                        while (cursor.moveToNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter908", ++_loopCounter908);
                            if (!ListenerUtil.mutListener.listen(70056)) {
                                result.add(convert(cursor));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private ConversationTagModel convert(Cursor cursor) {
        if (!ListenerUtil.mutListener.listen(70067)) {
            if ((ListenerUtil.mutListener.listen(70064) ? (cursor != null || (ListenerUtil.mutListener.listen(70063) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70062) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70061) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70060) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70059) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))) : (cursor != null && (ListenerUtil.mutListener.listen(70063) ? (cursor.getPosition() <= 0) : (ListenerUtil.mutListener.listen(70062) ? (cursor.getPosition() > 0) : (ListenerUtil.mutListener.listen(70061) ? (cursor.getPosition() < 0) : (ListenerUtil.mutListener.listen(70060) ? (cursor.getPosition() != 0) : (ListenerUtil.mutListener.listen(70059) ? (cursor.getPosition() == 0) : (cursor.getPosition() >= 0))))))))) {
                final ConversationTagModel c = new ConversationTagModel();
                if (!ListenerUtil.mutListener.listen(70066)) {
                    // convert default
                    new CursorHelper(cursor, columnIndexCache).current(new CursorHelper.Callback() {

                        @Override
                        public boolean next(CursorHelper cursorHelper) {
                            if (!ListenerUtil.mutListener.listen(70065)) {
                                c.setConversationUid(cursorHelper.getString(ConversationTagModel.COLUMN_CONVERSATION_UID)).setTag(cursorHelper.getString(ConversationTagModel.COLUMN_TAG)).setCreatedAt(cursorHelper.getDate(ConversationTagModel.COLUMN_CREATED_AT));
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

    private ContentValues buildContentValues(ConversationTagModel model) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(70068)) {
            contentValues.put(ConversationTagModel.COLUMN_CONVERSATION_UID, model.getConversationUid());
        }
        if (!ListenerUtil.mutListener.listen(70069)) {
            contentValues.put(ConversationTagModel.COLUMN_TAG, model.getTag());
        }
        if (!ListenerUtil.mutListener.listen(70070)) {
            contentValues.put(ConversationTagModel.COLUMN_CREATED_AT, model.getCreatedAt() != null ? model.getCreatedAt().getTime() : null);
        }
        return contentValues;
    }

    public void create(ConversationTagModel model) {
        if (!ListenerUtil.mutListener.listen(70071)) {
            logger.debug("create conversation tag " + model.getConversationUid() + " " + model.getTag());
        }
        ContentValues contentValues = buildContentValues(model);
        if (!ListenerUtil.mutListener.listen(70072)) {
            this.databaseService.getWritableDatabase().insertOrThrow(this.getTableName(), null, contentValues);
        }
    }

    public int delete(ConversationTagModel model) {
        return this.deleteByConversationUidAndTag(model.getConversationUid(), model.getTag());
    }

    public int deleteByConversationUidAndTag(String conversationUid, String tag) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), ConversationTagModel.COLUMN_CONVERSATION_UID + "=? AND " + ConversationTagModel.COLUMN_TAG + "=? ", new String[] { conversationUid, tag });
    }

    public int deleteByConversationUid(String conversationUid) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), ConversationTagModel.COLUMN_CONVERSATION_UID + "=?", new String[] { conversationUid });
    }

    public int deleteByConversationTag(String tag) {
        return this.databaseService.getWritableDatabase().delete(this.getTableName(), ConversationTagModel.COLUMN_TAG + "=?", new String[] { tag });
    }

    private ConversationTagModel getFirst(String selection, String[] selectionArgs) {
        Cursor cursor = this.databaseService.getReadableDatabase().query(this.getTableName(), null, selection, selectionArgs, null, null, null);
        if (!ListenerUtil.mutListener.listen(70075)) {
            if (cursor != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(70074)) {
                        if (cursor.moveToFirst()) {
                            return convert(cursor);
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(70073)) {
                        cursor.close();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String[] getStatements() {
        return new String[] { "CREATE TABLE IF NOT EXISTS `" + ConversationTagModel.TABLE + "` (" + "`" + ConversationTagModel.COLUMN_CONVERSATION_UID + "` VARCHAR NOT NULL, " + "`" + ConversationTagModel.COLUMN_TAG + "` BLOB NULL," + "`" + ConversationTagModel.COLUMN_CREATED_AT + "` BIGINT, " + "PRIMARY KEY (`" + ConversationTagModel.COLUMN_CONVERSATION_UID + "`, `" + ConversationTagModel.COLUMN_TAG + "`) " + ");", "CREATE UNIQUE INDEX IF NOT EXISTS `conversationTagKeyConversationTag` ON `" + ConversationTagModel.TABLE + "` ( `" + ConversationTagModel.COLUMN_CONVERSATION_UID + "`, `" + ConversationTagModel.COLUMN_TAG + "` );", "CREATE INDEX IF NOT EXISTS `conversationTagConversation` ON `" + ConversationTagModel.TABLE + "` ( `" + ConversationTagModel.COLUMN_CONVERSATION_UID + "` );", "CREATE INDEX IF NOT EXISTS`conversationTagTag` ON `" + ConversationTagModel.TABLE + "` ( `" + ConversationTagModel.COLUMN_TAG + "` );" };
    }
}
