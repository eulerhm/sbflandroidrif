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
package ch.threema.app.services.systemupdate;

import net.sqlcipher.database.SQLiteDatabase;
import java.sql.SQLException;
import ch.threema.app.services.UpdateSystemService;
import ch.threema.storage.DatabaseServiceNew;
import ch.threema.storage.models.DistributionListMessageModel;
import ch.threema.storage.models.GroupMessageModel;
import ch.threema.storage.models.MessageModel;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 */
public class SystemUpdateToVersion33 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private final DatabaseServiceNew databaseService;

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion33(DatabaseServiceNew databaseService, SQLiteDatabase sqLiteDatabase) {
        this.databaseService = databaseService;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() throws SQLException {
        if (!ListenerUtil.mutListener.listen(36176)) {
            {
                long _loopCounter333 = 0;
                for (String s : this.databaseService.getGroupMessagePendingMessageIdModelFactory().getStatements()) {
                    ListenerUtil.loopListener.listen("_loopCounter333", ++_loopCounter333);
                    if (!ListenerUtil.mutListener.listen(36175)) {
                        this.sqLiteDatabase.execSQL(s);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36180)) {
            {
                long _loopCounter334 = 0;
                // add new isQueued field to message model fields
                for (String table : new String[] { MessageModel.TABLE, GroupMessageModel.TABLE, DistributionListMessageModel.TABLE }) {
                    ListenerUtil.loopListener.listen("_loopCounter334", ++_loopCounter334);
                    if (!ListenerUtil.mutListener.listen(36179)) {
                        if (!this.fieldExist(this.sqLiteDatabase, table, "isQueued")) {
                            if (!ListenerUtil.mutListener.listen(36177)) {
                                sqLiteDatabase.rawExecSQL("ALTER TABLE " + table + " ADD COLUMN isQueued TINYINT NOT NULL DEFAULT 0");
                            }
                            if (!ListenerUtil.mutListener.listen(36178)) {
                                // update the existing records
                                sqLiteDatabase.rawExecSQL("UPDATE " + table + " SET isQueued=1");
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
        return "version 33";
    }
}
