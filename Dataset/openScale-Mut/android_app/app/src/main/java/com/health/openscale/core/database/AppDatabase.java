/* Copyright (C) 2018  olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.core.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Database(entities = { ScaleMeasurement.class, ScaleUser.class }, version = 5)
@TypeConverters({ Converters.class })
public abstract class AppDatabase extends RoomDatabase {

    public abstract ScaleMeasurementDAO measurementDAO();

    public abstract ScaleUserDAO userDAO();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            if (!ListenerUtil.mutListener.listen(4991)) {
                database.beginTransaction();
            }
            try {
                if (!ListenerUtil.mutListener.listen(4993)) {
                    // Drop old index on datetime only
                    database.execSQL("DROP INDEX index_scaleMeasurements_datetime");
                }
                if (!ListenerUtil.mutListener.listen(4994)) {
                    // Rename old table
                    database.execSQL("ALTER TABLE scaleMeasurements RENAME TO scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(4995)) {
                    // Create new table with foreign key
                    database.execSQL("CREATE TABLE scaleMeasurements" + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + " userId INTEGER NOT NULL, enabled INTEGER NOT NULL," + " datetime INTEGER, weight REAL NOT NULL, fat REAL NOT NULL," + " water REAL NOT NULL, muscle REAL NOT NULL, lbw REAL NOT NULL," + " waist REAL NOT NULL, hip REAL NOT NULL, bone REAL NOT NULL," + " comment TEXT, FOREIGN KEY(userId) REFERENCES scaleUsers(id)" + " ON UPDATE NO ACTION ON DELETE CASCADE)");
                }
                if (!ListenerUtil.mutListener.listen(4996)) {
                    // Create new index on datetime + userId
                    database.execSQL("CREATE UNIQUE INDEX index_scaleMeasurements_userId_datetime" + " ON scaleMeasurements (userId, datetime)");
                }
                if (!ListenerUtil.mutListener.listen(4997)) {
                    // Copy data from the old table, ignoring those with invalid userId (if any)
                    database.execSQL("INSERT INTO scaleMeasurements" + " SELECT * FROM scaleMeasurementsOld" + " WHERE userId IN (SELECT id from scaleUsers)");
                }
                if (!ListenerUtil.mutListener.listen(4998)) {
                    // Delete old table
                    database.execSQL("DROP TABLE scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(4999)) {
                    database.setTransactionSuccessful();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(4992)) {
                    database.endTransaction();
                }
            }
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            if (!ListenerUtil.mutListener.listen(5000)) {
                database.beginTransaction();
            }
            try {
                if (!ListenerUtil.mutListener.listen(5002)) {
                    // Drop old index
                    database.execSQL("DROP INDEX index_scaleMeasurements_userId_datetime");
                }
                if (!ListenerUtil.mutListener.listen(5003)) {
                    // Rename old table
                    database.execSQL("ALTER TABLE scaleMeasurements RENAME TO scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(5004)) {
                    database.execSQL("ALTER TABLE scaleUsers RENAME TO scaleUsersOld");
                }
                if (!ListenerUtil.mutListener.listen(5005)) {
                    // Create new table with foreign key
                    database.execSQL("CREATE TABLE scaleMeasurements" + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + " userId INTEGER NOT NULL, enabled INTEGER NOT NULL," + " datetime INTEGER, weight REAL NOT NULL, fat REAL NOT NULL," + " water REAL NOT NULL, muscle REAL NOT NULL, visceralFat REAL NOT NULL," + " lbm REAL NOT NULL, waist REAL NOT NULL, hip REAL NOT NULL," + " bone REAL NOT NULL, chest REAL NOT NULL, thigh REAL NOT NULL," + " biceps REAL NOT NULL, neck REAL NOT NULL, caliper1 REAL NOT NULL," + " caliper2 REAL NOT NULL, caliper3 REAL NOT NULL, comment TEXT," + " FOREIGN KEY(userId) REFERENCES scaleUsers(id)" + " ON UPDATE NO ACTION ON DELETE CASCADE)");
                }
                if (!ListenerUtil.mutListener.listen(5006)) {
                    database.execSQL("CREATE TABLE scaleUsers " + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "username TEXT NOT NULL, birthday INTEGER NOT NULL, bodyHeight REAL NOT NULL, " + "scaleUnit INTEGER NOT NULL, gender INTEGER NOT NULL, initialWeight REAL NOT NULL, " + "goalWeight REAL NOT NULL, goalDate INTEGER, measureUnit INTEGER NOT NULL, activityLevel INTEGER NOT NULL)");
                }
                if (!ListenerUtil.mutListener.listen(5007)) {
                    // Create new index on datetime + userId
                    database.execSQL("CREATE UNIQUE INDEX index_scaleMeasurements_userId_datetime" + " ON scaleMeasurements (userId, datetime)");
                }
                if (!ListenerUtil.mutListener.listen(5008)) {
                    // Copy data from the old table
                    database.execSQL("INSERT INTO scaleMeasurements" + " SELECT id, userId, enabled, datetime, weight, fat, water, muscle," + " 0 AS visceralFat, lbw AS lbm, waist, hip, bone, 0 AS chest," + " 0 as thigh, 0 as biceps, 0 as neck, 0 as caliper1," + " 0 as caliper2, 0 as caliper3, comment FROM scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(5009)) {
                    database.execSQL("INSERT INTO scaleUsers" + " SELECT id, username, birthday, bodyHeight, scaleUnit, gender, initialWeight, goalWeight," + " goalDate, 0 AS measureUnit, 0 AS activityLevel FROM scaleUsersOld");
                }
                if (!ListenerUtil.mutListener.listen(5010)) {
                    // Delete old table
                    database.execSQL("DROP TABLE scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(5011)) {
                    database.execSQL("DROP TABLE scaleUsersOld");
                }
                if (!ListenerUtil.mutListener.listen(5012)) {
                    database.setTransactionSuccessful();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(5001)) {
                    database.endTransaction();
                }
            }
        }
    };

    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            if (!ListenerUtil.mutListener.listen(5013)) {
                database.beginTransaction();
            }
            try {
                if (!ListenerUtil.mutListener.listen(5015)) {
                    // Drop old index
                    database.execSQL("DROP INDEX index_scaleMeasurements_userId_datetime");
                }
                if (!ListenerUtil.mutListener.listen(5016)) {
                    // Rename old table
                    database.execSQL("ALTER TABLE scaleMeasurements RENAME TO scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(5017)) {
                    // Create new table with foreign key
                    database.execSQL("CREATE TABLE scaleMeasurements" + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + " userId INTEGER NOT NULL, enabled INTEGER NOT NULL," + " datetime INTEGER, weight REAL NOT NULL, fat REAL NOT NULL," + " water REAL NOT NULL, muscle REAL NOT NULL, visceralFat REAL NOT NULL," + " lbm REAL NOT NULL, waist REAL NOT NULL, hip REAL NOT NULL," + " bone REAL NOT NULL, chest REAL NOT NULL, thigh REAL NOT NULL," + " biceps REAL NOT NULL, neck REAL NOT NULL, caliper1 REAL NOT NULL," + " caliper2 REAL NOT NULL, caliper3 REAL NOT NULL, calories REAL NOT NULL, comment TEXT," + " FOREIGN KEY(userId) REFERENCES scaleUsers(id)" + " ON UPDATE NO ACTION ON DELETE CASCADE)");
                }
                if (!ListenerUtil.mutListener.listen(5018)) {
                    // Create new index on datetime + userId
                    database.execSQL("CREATE UNIQUE INDEX index_scaleMeasurements_userId_datetime" + " ON scaleMeasurements (userId, datetime)");
                }
                if (!ListenerUtil.mutListener.listen(5019)) {
                    // Copy data from the old table
                    database.execSQL("INSERT INTO scaleMeasurements" + " SELECT id, userId, enabled, datetime, weight, fat, water, muscle," + " visceralFat, lbm, waist, hip, bone, chest," + " thigh, biceps, neck, caliper1," + " caliper2, caliper3, 0 as calories, comment FROM scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(5020)) {
                    // Delete old table
                    database.execSQL("DROP TABLE scaleMeasurementsOld");
                }
                if (!ListenerUtil.mutListener.listen(5021)) {
                    database.setTransactionSuccessful();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(5014)) {
                    database.endTransaction();
                }
            }
        }
    };

    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            if (!ListenerUtil.mutListener.listen(5022)) {
                database.beginTransaction();
            }
            try {
                if (!ListenerUtil.mutListener.listen(5024)) {
                    // Add assisted weighing and left/right amputation level to table
                    database.execSQL("ALTER TABLE scaleUsers ADD assistedWeighing INTEGER NOT NULL default 0");
                }
                if (!ListenerUtil.mutListener.listen(5025)) {
                    database.execSQL("ALTER TABLE scaleUsers ADD leftAmputationLevel INTEGER NOT NULL default 0");
                }
                if (!ListenerUtil.mutListener.listen(5026)) {
                    database.execSQL("ALTER TABLE scaleUsers ADD rightAmputationLevel INTEGER NOT NULL default 0");
                }
                if (!ListenerUtil.mutListener.listen(5027)) {
                    database.setTransactionSuccessful();
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(5023)) {
                    database.endTransaction();
                }
            }
        }
    };
}
