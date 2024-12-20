/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2014-2021 Threema GmbH
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
import ch.threema.storage.factories.ModelFactory;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion15 implements UpdateSystemService.SystemUpdate {

    private final DatabaseServiceNew databaseService;

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion15(DatabaseServiceNew databaseService, SQLiteDatabase sqLiteDatabase) {
        this.databaseService = databaseService;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() throws SQLException {
        if (!ListenerUtil.mutListener.listen(36117)) {
            {
                long _loopCounter326 = 0;
                for (ModelFactory f : new ModelFactory[] { this.databaseService.getDistributionListModelFactory(), this.databaseService.getDistributionListMemberModelFactory(), this.databaseService.getDistributionListMessageModelFactory() }) {
                    ListenerUtil.loopListener.listen("_loopCounter326", ++_loopCounter326);
                    if (!ListenerUtil.mutListener.listen(36116)) {
                        {
                            long _loopCounter325 = 0;
                            for (String s : f.getStatements()) {
                                ListenerUtil.loopListener.listen("_loopCounter325", ++_loopCounter325);
                                if (!ListenerUtil.mutListener.listen(36115)) {
                                    this.sqLiteDatabase.execSQL(s);
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
        return "version 15";
    }
}
