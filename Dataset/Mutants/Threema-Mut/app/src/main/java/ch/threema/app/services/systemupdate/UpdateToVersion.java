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
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import ch.threema.app.collections.Functional;
import ch.threema.app.collections.IPredicateNonNull;
import ch.threema.app.utils.LogUtil;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class UpdateToVersion {

    private static final Logger logger = LoggerFactory.getLogger(UpdateToVersion.class);

    protected boolean fieldExist(SQLiteDatabase sqLiteDatabase, final String table, final String fieldName) {
        boolean success = false;
        if (!ListenerUtil.mutListener.listen(36526)) {
            if ((ListenerUtil.mutListener.listen(36517) ? ((ListenerUtil.mutListener.listen(36511) ? ((ListenerUtil.mutListener.listen(36510) ? ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) || (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0))))))) : ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) && (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0)))))))) || fieldName != null) : ((ListenerUtil.mutListener.listen(36510) ? ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) || (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0))))))) : ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) && (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0)))))))) && fieldName != null)) || (ListenerUtil.mutListener.listen(36516) ? (fieldName.length() >= 0) : (ListenerUtil.mutListener.listen(36515) ? (fieldName.length() <= 0) : (ListenerUtil.mutListener.listen(36514) ? (fieldName.length() < 0) : (ListenerUtil.mutListener.listen(36513) ? (fieldName.length() != 0) : (ListenerUtil.mutListener.listen(36512) ? (fieldName.length() == 0) : (fieldName.length() > 0))))))) : ((ListenerUtil.mutListener.listen(36511) ? ((ListenerUtil.mutListener.listen(36510) ? ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) || (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0))))))) : ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) && (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0)))))))) || fieldName != null) : ((ListenerUtil.mutListener.listen(36510) ? ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) || (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0))))))) : ((ListenerUtil.mutListener.listen(36504) ? (sqLiteDatabase != null || table != null) : (sqLiteDatabase != null && table != null)) && (ListenerUtil.mutListener.listen(36509) ? (table.length() >= 0) : (ListenerUtil.mutListener.listen(36508) ? (table.length() <= 0) : (ListenerUtil.mutListener.listen(36507) ? (table.length() < 0) : (ListenerUtil.mutListener.listen(36506) ? (table.length() != 0) : (ListenerUtil.mutListener.listen(36505) ? (table.length() == 0) : (table.length() > 0)))))))) && fieldName != null)) && (ListenerUtil.mutListener.listen(36516) ? (fieldName.length() >= 0) : (ListenerUtil.mutListener.listen(36515) ? (fieldName.length() <= 0) : (ListenerUtil.mutListener.listen(36514) ? (fieldName.length() < 0) : (ListenerUtil.mutListener.listen(36513) ? (fieldName.length() != 0) : (ListenerUtil.mutListener.listen(36512) ? (fieldName.length() == 0) : (fieldName.length() > 0))))))))) {
                Cursor cursor = null;
                try {
                    if (!ListenerUtil.mutListener.listen(36522)) {
                        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + table + " LIMIT 0", null);
                    }
                    if (!ListenerUtil.mutListener.listen(36525)) {
                        if (cursor != null) {
                            String[] messageTableColumnNames = cursor.getColumnNames();
                            if (!ListenerUtil.mutListener.listen(36523)) {
                                cursor.close();
                            }
                            if (!ListenerUtil.mutListener.listen(36524)) {
                                success = Functional.select(Arrays.asList(messageTableColumnNames), new IPredicateNonNull<String>() {

                                    @Override
                                    public boolean apply(@NonNull String type) {
                                        return type.equals(fieldName);
                                    }
                                }) != null;
                            }
                        }
                    }
                } catch (Exception x) {
                    if (!ListenerUtil.mutListener.listen(36518)) {
                        logger.error("Exception", x);
                    }
                    if (!ListenerUtil.mutListener.listen(36519)) {
                        success = false;
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(36521)) {
                        if (cursor != null) {
                            if (!ListenerUtil.mutListener.listen(36520)) {
                                // always close the cursor
                                cursor.close();
                            }
                        }
                    }
                }
            }
        }
        return success;
    }
}
