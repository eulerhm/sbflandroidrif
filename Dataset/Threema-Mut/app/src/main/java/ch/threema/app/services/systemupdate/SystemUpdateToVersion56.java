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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import ch.threema.app.services.UpdateSystemService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * add contact restore state field to contact models
 */
public class SystemUpdateToVersion56 extends UpdateToVersion implements UpdateSystemService.SystemUpdate {

    private static final Logger logger = LoggerFactory.getLogger(SystemUpdateToVersion56.class);

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion56(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() throws SQLException {
        if (!ListenerUtil.mutListener.listen(36364)) {
            logger.info("runDirectly");
        }
        if (!ListenerUtil.mutListener.listen(36366)) {
            if (!this.fieldExist(this.sqLiteDatabase, "contacts", "isArchived")) {
                if (!ListenerUtil.mutListener.listen(36365)) {
                    sqLiteDatabase.rawExecSQL("ALTER TABLE contacts ADD COLUMN isArchived TINYINT DEFAULT 0");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36368)) {
            if (!this.fieldExist(this.sqLiteDatabase, "m_group", "isArchived")) {
                if (!ListenerUtil.mutListener.listen(36367)) {
                    sqLiteDatabase.rawExecSQL("ALTER TABLE m_group ADD COLUMN isArchived TINYINT DEFAULT 0");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(36370)) {
            if (!this.fieldExist(this.sqLiteDatabase, "distribution_list", "isArchived")) {
                if (!ListenerUtil.mutListener.listen(36369)) {
                    sqLiteDatabase.rawExecSQL("ALTER TABLE distribution_list ADD COLUMN isArchived TINYINT DEFAULT 0");
                }
            }
        }
        return true;
    }

    @Override
    public boolean runASync() {
        if (!ListenerUtil.mutListener.listen(36371)) {
            logger.info("runASync");
        }
        return true;
    }

    @Override
    public String getText() {
        return "version 56";
    }
}
