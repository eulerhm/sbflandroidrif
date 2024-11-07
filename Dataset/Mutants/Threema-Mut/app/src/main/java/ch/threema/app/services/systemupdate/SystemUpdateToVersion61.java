/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2020-2021 Threema GmbH
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
package ch.threema.app.services.systemupdate;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import java.sql.SQLException;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.app.utils.MimeUtil;
import ch.threema.storage.models.MessageType;
import ch.threema.storage.models.data.MessageContentsType;
import ch.threema.storage.models.data.media.FileDataModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * add caption field to normal, group and distribution list message models
 */
public class SystemUpdateToVersion61 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion61(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() throws SQLException {
        if (!ListenerUtil.mutListener.listen(36426)) {
            {
                long _loopCounter349 = 0;
                // add new messageContentsType field to message model table
                for (String table : new String[] { "message", "m_group_message", "distribution_list_message" }) {
                    ListenerUtil.loopListener.listen("_loopCounter349", ++_loopCounter349);
                    if (!ListenerUtil.mutListener.listen(36407)) {
                        if (!this.fieldExist(this.sqLiteDatabase, table, "messageContentsType")) {
                            if (!ListenerUtil.mutListener.listen(36406)) {
                                sqLiteDatabase.rawExecSQL("ALTER TABLE " + table + " ADD COLUMN messageContentsType TINYINT DEFAULT " + MessageContentsType.UNDEFINED);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(36408)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.TEXT + " WHERE type = " + MessageType.TEXT.ordinal());
                    }
                    if (!ListenerUtil.mutListener.listen(36409)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.IMAGE + " WHERE type = " + MessageType.IMAGE.ordinal());
                    }
                    if (!ListenerUtil.mutListener.listen(36410)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.VIDEO + " WHERE type = " + MessageType.VIDEO.ordinal());
                    }
                    if (!ListenerUtil.mutListener.listen(36411)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.VOICE_MESSAGE + " WHERE type = " + MessageType.VOICEMESSAGE.ordinal());
                    }
                    if (!ListenerUtil.mutListener.listen(36412)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.BALLOT + " WHERE type = " + MessageType.BALLOT.ordinal());
                    }
                    if (!ListenerUtil.mutListener.listen(36413)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.LOCATION + " WHERE type = " + MessageType.LOCATION.ordinal());
                    }
                    if (!ListenerUtil.mutListener.listen(36414)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.STATUS + " WHERE type = " + MessageType.STATUS.ordinal());
                    }
                    if (!ListenerUtil.mutListener.listen(36415)) {
                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MessageContentsType.VOIP_STATUS + " WHERE type = " + MessageType.VOIP_STATUS.ordinal());
                    }
                    // check all file messages, extract mime type from json, add correct messagecontentstype
                    try (Cursor fileMessages = sqLiteDatabase.rawQuery("SELECT id, body FROM " + table + " WHERE type = " + MessageType.FILE.ordinal(), null)) {
                        if (!ListenerUtil.mutListener.listen(36425)) {
                            if (fileMessages != null) {
                                if (!ListenerUtil.mutListener.listen(36424)) {
                                    {
                                        long _loopCounter348 = 0;
                                        while (fileMessages.moveToNext()) {
                                            ListenerUtil.loopListener.listen("_loopCounter348", ++_loopCounter348);
                                            final int id = fileMessages.getInt(0);
                                            final String body = fileMessages.getString(1);
                                            if (!ListenerUtil.mutListener.listen(36423)) {
                                                if ((ListenerUtil.mutListener.listen(36421) ? (body != null || (ListenerUtil.mutListener.listen(36420) ? (body.length() >= 0) : (ListenerUtil.mutListener.listen(36419) ? (body.length() <= 0) : (ListenerUtil.mutListener.listen(36418) ? (body.length() < 0) : (ListenerUtil.mutListener.listen(36417) ? (body.length() != 0) : (ListenerUtil.mutListener.listen(36416) ? (body.length() == 0) : (body.length() > 0))))))) : (body != null && (ListenerUtil.mutListener.listen(36420) ? (body.length() >= 0) : (ListenerUtil.mutListener.listen(36419) ? (body.length() <= 0) : (ListenerUtil.mutListener.listen(36418) ? (body.length() < 0) : (ListenerUtil.mutListener.listen(36417) ? (body.length() != 0) : (ListenerUtil.mutListener.listen(36416) ? (body.length() == 0) : (body.length() > 0))))))))) {
                                                    FileDataModel fileDataModel = FileDataModel.create(body);
                                                    if (!ListenerUtil.mutListener.listen(36422)) {
                                                        sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET messageContentsType = " + MimeUtil.getContentTypeFromFileData(fileDataModel) + " WHERE id = " + id);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean runASync() {
        return true;
    }

    @Override
    public String getText() {
        return "version 61 (add mime type column)";
    }
}
