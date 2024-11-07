/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Create all correlationId fields
 */
public class SystemUpdateToVersion59 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion59(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() throws SQLException {
        if (!ListenerUtil.mutListener.listen(36377)) {
            {
                long _loopCounter345 = 0;
                // add new isQueued field to message model fields
                for (String table : new String[] { "message", "m_group_message", "distribution_list_message" }) {
                    ListenerUtil.loopListener.listen("_loopCounter345", ++_loopCounter345);
                    if (!ListenerUtil.mutListener.listen(36376)) {
                        if (!this.fieldExist(this.sqLiteDatabase, table, "correlationId")) {
                            if (!ListenerUtil.mutListener.listen(36375)) {
                                sqLiteDatabase.rawExecSQL("ALTER TABLE " + table + " ADD COLUMN correlationId VARCHAR NULL");
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36378)) {
            // Create index
            this.sqLiteDatabase.rawExecSQL("CREATE INDEX `messageCorrelationIdIx` ON `message` ( `correlationId` )");
        }
        if (!ListenerUtil.mutListener.listen(36379)) {
            this.sqLiteDatabase.rawExecSQL("CREATE INDEX `groupMessageCorrelationIdIdx` ON `m_group_message` ( `correlationId` )");
        }
        if (!ListenerUtil.mutListener.listen(36380)) {
            this.sqLiteDatabase.rawExecSQL("CREATE INDEX `distributionListCorrelationIdIdx` ON `distribution_list_message` ( `correlationId` )");
        }
        return true;
    }

    @Override
    public boolean runASync() {
        return true;
    }

    @Override
    public String getText() {
        return "version 59 (correlationId)";
    }
}
