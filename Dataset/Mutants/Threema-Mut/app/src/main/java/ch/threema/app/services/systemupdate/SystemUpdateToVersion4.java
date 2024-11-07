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

import androidx.annotation.NonNull;
import net.sqlcipher.database.SQLiteDatabase;
import java.util.Arrays;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.services.UpdateSystemService;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class SystemUpdateToVersion4 implements UpdateSystemService.SystemUpdate {

    private final SQLiteDatabase sqLiteDatabase;

    public SystemUpdateToVersion4(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public boolean runDirectly() {
        String[] messageTableColumnNames = this.sqLiteDatabase.rawQuery("SELECT * FROM message LIMIT 0", null).getColumnNames();
        boolean fieldPostedAtExists = Functional.select(Arrays.asList(messageTableColumnNames), new IPredicateNonNull<String>() {

            @Override
            public boolean apply(@NonNull String type) {
                return type.equals("postedAt");
            }
        }) != null;
        if (!ListenerUtil.mutListener.listen(36562)) {
            // create postedAt field if not exists
            if (!fieldPostedAtExists) {
                if (!ListenerUtil.mutListener.listen(36560)) {
                    this.sqLiteDatabase.rawExecSQL("ALTER TABLE message ADD COLUMN postedAt DATETIME DEFAULT NULL");
                }
                if (!ListenerUtil.mutListener.listen(36561)) {
                    this.sqLiteDatabase.rawExecSQL("UPDATE message SET postedAt = createdAt");
                }
            }
        }
        boolean fieldIsSavedExists = Functional.select(Arrays.asList(messageTableColumnNames), new IPredicateNonNull<String>() {

            @Override
            public boolean apply(@NonNull String type) {
                return type.equals("isSaved");
            }
        }) != null;
        if (!ListenerUtil.mutListener.listen(36565)) {
            // create isSaved field if not exists
            if (!fieldIsSavedExists) {
                if (!ListenerUtil.mutListener.listen(36563)) {
                    this.sqLiteDatabase.rawExecSQL("ALTER TABLE message ADD COLUMN isSaved INT DEFAULT 0");
                }
                if (!ListenerUtil.mutListener.listen(36564)) {
                    this.sqLiteDatabase.rawExecSQL("UPDATE message SET isSaved = 1");
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
        return "version 4";
    }
}
