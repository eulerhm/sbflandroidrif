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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ch.threema.app.services.MessageService;
import ch.threema.app.utils.MessageUtil;
import ch.threema.app.utils.TestUtil;
import ch.threema.storage.CursorHelper;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.DatabaseUtil;
import ch.threema.storage.QueryBuilder;
import ch.threema.storage.models.AbstractMessageModel;
import ch.threema.storage.models.MessageState;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.media.AudioDataModel;
import ch.threema.storage.models.data.media.FileDataModel;
import ch.threema.storage.models.data.media.VideoDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class AbstractMessageModelFactory extends ModelFactory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractMessageModelFactory.class);

    AbstractMessageModelFactory(DatabaseServiceNew databaseServiceNew, String tableName) {
        super(databaseServiceNew, tableName);
    }

    void convert(final AbstractMessageModel messageModel, CursorHelper cursorFactory) {
        if (!ListenerUtil.mutListener.listen(69734)) {
            cursorFactory.current(new CursorHelper.Callback() {

                @Override
                public boolean next(CursorHelper cursorFactory) {
                    if (!ListenerUtil.mutListener.listen(69717)) {
                        messageModel.setId(cursorFactory.getInt(AbstractMessageModel.COLUMN_ID)).setUid(cursorFactory.getString(AbstractMessageModel.COLUMN_UID)).setApiMessageId(cursorFactory.getString(AbstractMessageModel.COLUMN_API_MESSAGE_ID)).setIdentity(cursorFactory.getString(AbstractMessageModel.COLUMN_IDENTITY)).setOutbox(cursorFactory.getBoolean(AbstractMessageModel.COLUMN_OUTBOX)).setCorrelationId(cursorFactory.getString(AbstractMessageModel.COLUMN_CORRELATION_ID)).setBody(cursorFactory.getString(AbstractMessageModel.COLUMN_BODY)).setRead(cursorFactory.getBoolean(AbstractMessageModel.COLUMN_IS_READ)).setSaved(cursorFactory.getBoolean(AbstractMessageModel.COLUMN_IS_SAVED)).setPostedAt(cursorFactory.getDate(AbstractMessageModel.COLUMN_POSTED_AT)).setCreatedAt(cursorFactory.getDate(AbstractMessageModel.COLUMN_CREATED_AT)).setModifiedAt(cursorFactory.getDate(AbstractMessageModel.COLUMN_MODIFIED_AT)).setIsStatusMessage(cursorFactory.getBoolean(AbstractMessageModel.COLUMN_IS_STATUS_MESSAGE)).setIsQueued(cursorFactory.getBoolean(AbstractMessageModel.COLUMN_IS_QUEUED)).setCaption(cursorFactory.getString(AbstractMessageModel.COLUMN_CAPTION)).setQuotedMessageId(cursorFactory.getString(AbstractMessageModel.COLUMN_QUOTED_MESSAGE_API_MESSAGE_ID)).setMessageContentsType(cursorFactory.getInt(AbstractMessageModel.COLUMN_MESSAGE_CONTENTS_TYPE)).setMessageFlags(cursorFactory.getInt(AbstractMessageModel.COLUMN_MESSAGE_FLAGS));
                    }
                    String stateString = cursorFactory.getString(AbstractMessageModel.COLUMN_STATE);
                    if (!ListenerUtil.mutListener.listen(69720)) {
                        if (!TestUtil.empty(stateString)) {
                            try {
                                if (!ListenerUtil.mutListener.listen(69719)) {
                                    messageModel.setState(MessageState.valueOf(stateString));
                                }
                            } catch (IllegalArgumentException x) {
                                if (!ListenerUtil.mutListener.listen(69718)) {
                                    logger.error("Invalid message state " + stateString + " - ignore", x);
                                }
                            }
                        }
                    }
                    int type = cursorFactory.getInt(AbstractMessageModel.COLUMN_TYPE);
                    MessageType[] types = MessageType.values();
                    if (!ListenerUtil.mutListener.listen(69733)) {
                        if ((ListenerUtil.mutListener.listen(69731) ? ((ListenerUtil.mutListener.listen(69725) ? (type <= 0) : (ListenerUtil.mutListener.listen(69724) ? (type > 0) : (ListenerUtil.mutListener.listen(69723) ? (type < 0) : (ListenerUtil.mutListener.listen(69722) ? (type != 0) : (ListenerUtil.mutListener.listen(69721) ? (type == 0) : (type >= 0)))))) || (ListenerUtil.mutListener.listen(69730) ? (type >= types.length) : (ListenerUtil.mutListener.listen(69729) ? (type <= types.length) : (ListenerUtil.mutListener.listen(69728) ? (type > types.length) : (ListenerUtil.mutListener.listen(69727) ? (type != types.length) : (ListenerUtil.mutListener.listen(69726) ? (type == types.length) : (type < types.length))))))) : ((ListenerUtil.mutListener.listen(69725) ? (type <= 0) : (ListenerUtil.mutListener.listen(69724) ? (type > 0) : (ListenerUtil.mutListener.listen(69723) ? (type < 0) : (ListenerUtil.mutListener.listen(69722) ? (type != 0) : (ListenerUtil.mutListener.listen(69721) ? (type == 0) : (type >= 0)))))) && (ListenerUtil.mutListener.listen(69730) ? (type >= types.length) : (ListenerUtil.mutListener.listen(69729) ? (type <= types.length) : (ListenerUtil.mutListener.listen(69728) ? (type > types.length) : (ListenerUtil.mutListener.listen(69727) ? (type != types.length) : (ListenerUtil.mutListener.listen(69726) ? (type == types.length) : (type < types.length))))))))) {
                            if (!ListenerUtil.mutListener.listen(69732)) {
                                messageModel.setType(types[type]);
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    ContentValues buildContentValues(AbstractMessageModel messageModel) {
        ContentValues contentValues = new ContentValues();
        if (!ListenerUtil.mutListener.listen(69735)) {
            contentValues.put(AbstractMessageModel.COLUMN_UID, messageModel.getUid());
        }
        if (!ListenerUtil.mutListener.listen(69736)) {
            contentValues.put(AbstractMessageModel.COLUMN_API_MESSAGE_ID, messageModel.getApiMessageId());
        }
        if (!ListenerUtil.mutListener.listen(69737)) {
            contentValues.put(AbstractMessageModel.COLUMN_IDENTITY, messageModel.getIdentity());
        }
        if (!ListenerUtil.mutListener.listen(69738)) {
            contentValues.put(AbstractMessageModel.COLUMN_OUTBOX, messageModel.isOutbox());
        }
        if (!ListenerUtil.mutListener.listen(69739)) {
            contentValues.put(AbstractMessageModel.COLUMN_TYPE, messageModel.getType() != null ? messageModel.getType().ordinal() : null);
        }
        if (!ListenerUtil.mutListener.listen(69740)) {
            contentValues.put(AbstractMessageModel.COLUMN_CORRELATION_ID, messageModel.getCorrelationId());
        }
        if (!ListenerUtil.mutListener.listen(69741)) {
            contentValues.put(AbstractMessageModel.COLUMN_BODY, messageModel.getBody());
        }
        if (!ListenerUtil.mutListener.listen(69742)) {
            contentValues.put(AbstractMessageModel.COLUMN_IS_READ, messageModel.isRead());
        }
        if (!ListenerUtil.mutListener.listen(69743)) {
            contentValues.put(AbstractMessageModel.COLUMN_IS_SAVED, messageModel.isSaved());
        }
        if (!ListenerUtil.mutListener.listen(69744)) {
            contentValues.put(AbstractMessageModel.COLUMN_STATE, messageModel.getState() != null ? messageModel.getState().toString() : null);
        }
        if (!ListenerUtil.mutListener.listen(69745)) {
            contentValues.put(AbstractMessageModel.COLUMN_POSTED_AT, DatabaseUtil.getDateTimeContentValue(messageModel.getPostedAt()));
        }
        if (!ListenerUtil.mutListener.listen(69746)) {
            contentValues.put(AbstractMessageModel.COLUMN_CREATED_AT, DatabaseUtil.getDateTimeContentValue(messageModel.getCreatedAt()));
        }
        if (!ListenerUtil.mutListener.listen(69747)) {
            contentValues.put(AbstractMessageModel.COLUMN_MODIFIED_AT, DatabaseUtil.getDateTimeContentValue(messageModel.getModifiedAt()));
        }
        if (!ListenerUtil.mutListener.listen(69748)) {
            contentValues.put(AbstractMessageModel.COLUMN_IS_STATUS_MESSAGE, messageModel.isStatusMessage());
        }
        if (!ListenerUtil.mutListener.listen(69749)) {
            contentValues.put(AbstractMessageModel.COLUMN_IS_QUEUED, messageModel.isQueued());
        }
        if (!ListenerUtil.mutListener.listen(69750)) {
            contentValues.put(AbstractMessageModel.COLUMN_CAPTION, messageModel.getCaption());
        }
        if (!ListenerUtil.mutListener.listen(69751)) {
            contentValues.put(AbstractMessageModel.COLUMN_QUOTED_MESSAGE_API_MESSAGE_ID, messageModel.getQuotedMessageId());
        }
        if (!ListenerUtil.mutListener.listen(69752)) {
            contentValues.put(AbstractMessageModel.COLUMN_MESSAGE_CONTENTS_TYPE, messageModel.getMessageContentsType());
        }
        if (!ListenerUtil.mutListener.listen(69753)) {
            contentValues.put(AbstractMessageModel.COLUMN_MESSAGE_FLAGS, messageModel.getMessageFlags());
        }
        return contentValues;
    }

    void appendFilter(QueryBuilder queryBuilder, MessageService.MessageFilter filter, List<String> placeholders) {
        if (!ListenerUtil.mutListener.listen(69791)) {
            if (filter != null) {
                if (!ListenerUtil.mutListener.listen(69755)) {
                    if (!filter.withStatusMessages()) {
                        if (!ListenerUtil.mutListener.listen(69754)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_IS_STATUS_MESSAGE + "=0");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69759)) {
                    if (filter.onlyUnread()) {
                        if (!ListenerUtil.mutListener.listen(69756)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_OUTBOX + "=0");
                        }
                        if (!ListenerUtil.mutListener.listen(69757)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_IS_READ + "=0");
                        }
                        if (!ListenerUtil.mutListener.listen(69758)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_IS_STATUS_MESSAGE + "=0");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69761)) {
                    if (!filter.withUnsaved()) {
                        if (!ListenerUtil.mutListener.listen(69760)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_IS_SAVED + "=1");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69771)) {
                    if ((ListenerUtil.mutListener.listen(69767) ? (filter.types() != null || (ListenerUtil.mutListener.listen(69766) ? (filter.types().length >= 0) : (ListenerUtil.mutListener.listen(69765) ? (filter.types().length <= 0) : (ListenerUtil.mutListener.listen(69764) ? (filter.types().length < 0) : (ListenerUtil.mutListener.listen(69763) ? (filter.types().length != 0) : (ListenerUtil.mutListener.listen(69762) ? (filter.types().length == 0) : (filter.types().length > 0))))))) : (filter.types() != null && (ListenerUtil.mutListener.listen(69766) ? (filter.types().length >= 0) : (ListenerUtil.mutListener.listen(69765) ? (filter.types().length <= 0) : (ListenerUtil.mutListener.listen(69764) ? (filter.types().length < 0) : (ListenerUtil.mutListener.listen(69763) ? (filter.types().length != 0) : (ListenerUtil.mutListener.listen(69762) ? (filter.types().length == 0) : (filter.types().length > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(69768)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_TYPE + " IN (" + DatabaseUtil.makePlaceholders(filter.types().length) + ")");
                        }
                        if (!ListenerUtil.mutListener.listen(69770)) {
                            {
                                long _loopCounter897 = 0;
                                for (MessageType f : filter.types()) {
                                    ListenerUtil.loopListener.listen("_loopCounter897", ++_loopCounter897);
                                    if (!ListenerUtil.mutListener.listen(69769)) {
                                        placeholders.add(String.valueOf(f.ordinal()));
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69781)) {
                    if ((ListenerUtil.mutListener.listen(69777) ? (filter.contentTypes() != null || (ListenerUtil.mutListener.listen(69776) ? (filter.contentTypes().length >= 0) : (ListenerUtil.mutListener.listen(69775) ? (filter.contentTypes().length <= 0) : (ListenerUtil.mutListener.listen(69774) ? (filter.contentTypes().length < 0) : (ListenerUtil.mutListener.listen(69773) ? (filter.contentTypes().length != 0) : (ListenerUtil.mutListener.listen(69772) ? (filter.contentTypes().length == 0) : (filter.contentTypes().length > 0))))))) : (filter.contentTypes() != null && (ListenerUtil.mutListener.listen(69776) ? (filter.contentTypes().length >= 0) : (ListenerUtil.mutListener.listen(69775) ? (filter.contentTypes().length <= 0) : (ListenerUtil.mutListener.listen(69774) ? (filter.contentTypes().length < 0) : (ListenerUtil.mutListener.listen(69773) ? (filter.contentTypes().length != 0) : (ListenerUtil.mutListener.listen(69772) ? (filter.contentTypes().length == 0) : (filter.contentTypes().length > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(69778)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_MESSAGE_CONTENTS_TYPE + " IN (" + DatabaseUtil.makePlaceholders(filter.contentTypes().length) + ")");
                        }
                        if (!ListenerUtil.mutListener.listen(69780)) {
                            {
                                long _loopCounter898 = 0;
                                for (@MessageContentsType int f : filter.contentTypes()) {
                                    ListenerUtil.loopListener.listen("_loopCounter898", ++_loopCounter898);
                                    if (!ListenerUtil.mutListener.listen(69779)) {
                                        placeholders.add(String.valueOf(f));
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(69790)) {
                    if ((ListenerUtil.mutListener.listen(69787) ? (filter.getPageReferenceId() != null || (ListenerUtil.mutListener.listen(69786) ? (filter.getPageReferenceId() >= 0) : (ListenerUtil.mutListener.listen(69785) ? (filter.getPageReferenceId() <= 0) : (ListenerUtil.mutListener.listen(69784) ? (filter.getPageReferenceId() < 0) : (ListenerUtil.mutListener.listen(69783) ? (filter.getPageReferenceId() != 0) : (ListenerUtil.mutListener.listen(69782) ? (filter.getPageReferenceId() == 0) : (filter.getPageReferenceId() > 0))))))) : (filter.getPageReferenceId() != null && (ListenerUtil.mutListener.listen(69786) ? (filter.getPageReferenceId() >= 0) : (ListenerUtil.mutListener.listen(69785) ? (filter.getPageReferenceId() <= 0) : (ListenerUtil.mutListener.listen(69784) ? (filter.getPageReferenceId() < 0) : (ListenerUtil.mutListener.listen(69783) ? (filter.getPageReferenceId() != 0) : (ListenerUtil.mutListener.listen(69782) ? (filter.getPageReferenceId() == 0) : (filter.getPageReferenceId() > 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(69788)) {
                            queryBuilder.appendWhere(AbstractMessageModel.COLUMN_ID + "<?");
                        }
                        if (!ListenerUtil.mutListener.listen(69789)) {
                            placeholders.add(String.valueOf(filter.getPageReferenceId()));
                        }
                    }
                }
            }
        }
    }

    <T> void postFilter(List<T> input, MessageService.MessageFilter filter) {
        if (!ListenerUtil.mutListener.listen(69803)) {
            if ((ListenerUtil.mutListener.listen(69792) ? (filter != null || filter.onlyDownloaded()) : (filter != null && filter.onlyDownloaded()))) {
                Iterator<T> i = input.iterator();
                if (!ListenerUtil.mutListener.listen(69802)) {
                    {
                        long _loopCounter899 = 0;
                        while (i.hasNext()) {
                            ListenerUtil.loopListener.listen("_loopCounter899", ++_loopCounter899);
                            AbstractMessageModel m = (AbstractMessageModel) i.next();
                            boolean remove = false;
                            if (!ListenerUtil.mutListener.listen(69799)) {
                                if (m.getType() == MessageType.VIDEO) {
                                    VideoDataModel d = m.getVideoData();
                                    if (!ListenerUtil.mutListener.listen(69798)) {
                                        remove = ((ListenerUtil.mutListener.listen(69797) ? (d == null && !d.isDownloaded()) : (d == null || !d.isDownloaded())));
                                    }
                                } else if (m.getType() == MessageType.VOICEMESSAGE) {
                                    AudioDataModel d = m.getAudioData();
                                    if (!ListenerUtil.mutListener.listen(69796)) {
                                        remove = ((ListenerUtil.mutListener.listen(69795) ? (d == null && !d.isDownloaded()) : (d == null || !d.isDownloaded())));
                                    }
                                } else if (m.getType() == MessageType.FILE) {
                                    FileDataModel d = m.getFileData();
                                    if (!ListenerUtil.mutListener.listen(69794)) {
                                        remove = ((ListenerUtil.mutListener.listen(69793) ? (d == null && !d.isDownloaded()) : (d == null || !d.isDownloaded())));
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(69801)) {
                                if (remove) {
                                    if (!ListenerUtil.mutListener.listen(69800)) {
                                        i.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    String limitFilter(MessageService.MessageFilter filter) {
        if (!ListenerUtil.mutListener.listen(69810)) {
            if ((ListenerUtil.mutListener.listen(69809) ? (filter != null || (ListenerUtil.mutListener.listen(69808) ? (filter.getPageSize() >= 0) : (ListenerUtil.mutListener.listen(69807) ? (filter.getPageSize() <= 0) : (ListenerUtil.mutListener.listen(69806) ? (filter.getPageSize() < 0) : (ListenerUtil.mutListener.listen(69805) ? (filter.getPageSize() != 0) : (ListenerUtil.mutListener.listen(69804) ? (filter.getPageSize() == 0) : (filter.getPageSize() > 0))))))) : (filter != null && (ListenerUtil.mutListener.listen(69808) ? (filter.getPageSize() >= 0) : (ListenerUtil.mutListener.listen(69807) ? (filter.getPageSize() <= 0) : (ListenerUtil.mutListener.listen(69806) ? (filter.getPageSize() < 0) : (ListenerUtil.mutListener.listen(69805) ? (filter.getPageSize() != 0) : (ListenerUtil.mutListener.listen(69804) ? (filter.getPageSize() == 0) : (filter.getPageSize() > 0))))))))) {
                return "" + filter.getPageSize();
            }
        }
        return null;
    }

    public void markUnqueuedMessagesAsFailed() {
        List<String> params = new ArrayList<>();
        int messageTypeSize = 0;
        if (!ListenerUtil.mutListener.listen(69813)) {
            {
                long _loopCounter900 = 0;
                for (MessageType t : MessageUtil.getFileTypes()) {
                    ListenerUtil.loopListener.listen("_loopCounter900", ++_loopCounter900);
                    if (!ListenerUtil.mutListener.listen(69811)) {
                        messageTypeSize++;
                    }
                    if (!ListenerUtil.mutListener.listen(69812)) {
                        params.add(String.valueOf(t.ordinal()));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(69814)) {
            params.add(MessageState.SENDFAILED.toString());
        }
        // select all unqueued!
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(69815)) {
            values.put(AbstractMessageModel.COLUMN_STATE, MessageState.SENDFAILED.toString());
        }
        try {
            int updated = this.databaseService.getWritableDatabase().update(this.getTableName(), values, AbstractMessageModel.COLUMN_TYPE + " IN (" + DatabaseUtil.makePlaceholders(messageTypeSize) + ") " + "AND " + AbstractMessageModel.COLUMN_IS_QUEUED + " = 0 " + "AND " + AbstractMessageModel.COLUMN_STATE + " != ? " + "AND " + AbstractMessageModel.COLUMN_OUTBOX + " = 1", DatabaseUtil.convertArguments(params));
            if (!ListenerUtil.mutListener.listen(69823)) {
                if ((ListenerUtil.mutListener.listen(69821) ? (updated >= 0) : (ListenerUtil.mutListener.listen(69820) ? (updated <= 0) : (ListenerUtil.mutListener.listen(69819) ? (updated < 0) : (ListenerUtil.mutListener.listen(69818) ? (updated != 0) : (ListenerUtil.mutListener.listen(69817) ? (updated == 0) : (updated > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(69822)) {
                        logger.info(updated + " messages in sending status were updated to sendfailed.");
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(69816)) {
                logger.error("Exception", e);
            }
        }
    }
}
