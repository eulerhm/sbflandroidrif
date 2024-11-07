/*
 * Copyright (C) 2010-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com),
 * Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.provider;

import org.onebusaway.android.BuildConfig;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ObaProvider extends ContentProvider {

    public static final String TAG = "ObaProvider";

    /**
     * The database name cannot be changed.  It needs to remain the same to support backwards
     * compatibility with existing installed apps
     */
    private static final String DATABASE_NAME = BuildConfig.APPLICATION_ID + ".db";

    private class OpenHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 30;

        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(8661)) {
                bootstrapDatabase(db);
            }
            if (!ListenerUtil.mutListener.listen(8662)) {
                onUpgrade(db, 12, DATABASE_VERSION);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (!ListenerUtil.mutListener.listen(8663)) {
                Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
            }
            if (!ListenerUtil.mutListener.listen(8672)) {
                if ((ListenerUtil.mutListener.listen(8668) ? (oldVersion >= 12) : (ListenerUtil.mutListener.listen(8667) ? (oldVersion <= 12) : (ListenerUtil.mutListener.listen(8666) ? (oldVersion > 12) : (ListenerUtil.mutListener.listen(8665) ? (oldVersion != 12) : (ListenerUtil.mutListener.listen(8664) ? (oldVersion == 12) : (oldVersion < 12))))))) {
                    if (!ListenerUtil.mutListener.listen(8669)) {
                        dropTables(db);
                    }
                    if (!ListenerUtil.mutListener.listen(8670)) {
                        bootstrapDatabase(db);
                    }
                    if (!ListenerUtil.mutListener.listen(8671)) {
                        oldVersion = 12;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8680)) {
                if ((ListenerUtil.mutListener.listen(8677) ? (oldVersion >= 12) : (ListenerUtil.mutListener.listen(8676) ? (oldVersion <= 12) : (ListenerUtil.mutListener.listen(8675) ? (oldVersion > 12) : (ListenerUtil.mutListener.listen(8674) ? (oldVersion < 12) : (ListenerUtil.mutListener.listen(8673) ? (oldVersion != 12) : (oldVersion == 12))))))) {
                    if (!ListenerUtil.mutListener.listen(8678)) {
                        db.execSQL("CREATE TABLE " + ObaContract.StopRouteFilters.PATH + " (" + ObaContract.StopRouteFilters.STOP_ID + " VARCHAR NOT NULL, " + ObaContract.StopRouteFilters.ROUTE_ID + " VARCHAR NOT NULL" + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8679)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8693)) {
                if ((ListenerUtil.mutListener.listen(8685) ? (oldVersion >= 13) : (ListenerUtil.mutListener.listen(8684) ? (oldVersion <= 13) : (ListenerUtil.mutListener.listen(8683) ? (oldVersion > 13) : (ListenerUtil.mutListener.listen(8682) ? (oldVersion < 13) : (ListenerUtil.mutListener.listen(8681) ? (oldVersion != 13) : (oldVersion == 13))))))) {
                    if (!ListenerUtil.mutListener.listen(8686)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Stops.PATH + " ADD COLUMN " + ObaContract.Stops.USER_NAME);
                    }
                    if (!ListenerUtil.mutListener.listen(8687)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Stops.PATH + " ADD COLUMN " + ObaContract.Stops.ACCESS_TIME);
                    }
                    if (!ListenerUtil.mutListener.listen(8688)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Stops.PATH + " ADD COLUMN " + ObaContract.Stops.FAVORITE);
                    }
                    if (!ListenerUtil.mutListener.listen(8689)) {
                        // (we don't allow people to rename routes)
                        db.execSQL("ALTER TABLE " + ObaContract.Routes.PATH + " ADD COLUMN " + ObaContract.Routes.USER_NAME);
                    }
                    if (!ListenerUtil.mutListener.listen(8690)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Routes.PATH + " ADD COLUMN " + ObaContract.Routes.ACCESS_TIME);
                    }
                    if (!ListenerUtil.mutListener.listen(8691)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Routes.PATH + " ADD COLUMN " + ObaContract.Routes.FAVORITE);
                    }
                    if (!ListenerUtil.mutListener.listen(8692)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8701)) {
                if ((ListenerUtil.mutListener.listen(8698) ? (oldVersion >= 14) : (ListenerUtil.mutListener.listen(8697) ? (oldVersion <= 14) : (ListenerUtil.mutListener.listen(8696) ? (oldVersion > 14) : (ListenerUtil.mutListener.listen(8695) ? (oldVersion < 14) : (ListenerUtil.mutListener.listen(8694) ? (oldVersion != 14) : (oldVersion == 14))))))) {
                    if (!ListenerUtil.mutListener.listen(8699)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Routes.PATH + " ADD COLUMN " + ObaContract.Routes.URL);
                    }
                    if (!ListenerUtil.mutListener.listen(8700)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8711)) {
                if ((ListenerUtil.mutListener.listen(8706) ? (oldVersion >= 15) : (ListenerUtil.mutListener.listen(8705) ? (oldVersion <= 15) : (ListenerUtil.mutListener.listen(8704) ? (oldVersion > 15) : (ListenerUtil.mutListener.listen(8703) ? (oldVersion < 15) : (ListenerUtil.mutListener.listen(8702) ? (oldVersion != 15) : (oldVersion == 15))))))) {
                    if (!ListenerUtil.mutListener.listen(8707)) {
                        db.execSQL("CREATE TABLE " + ObaContract.TripAlerts.PATH + " (" + ObaContract.TripAlerts._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ObaContract.TripAlerts.TRIP_ID + " VARCHAR NOT NULL, " + ObaContract.TripAlerts.STOP_ID + " VARCHAR NOT NULL, " + ObaContract.TripAlerts.START_TIME + " INTEGER NOT NULL, " + ObaContract.TripAlerts.STATE + " INTEGER NOT NULL DEFAULT " + ObaContract.TripAlerts.STATE_SCHEDULED + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8708)) {
                        db.execSQL("DROP TRIGGER IF EXISTS trip_alerts_cleanup");
                    }
                    if (!ListenerUtil.mutListener.listen(8709)) {
                        db.execSQL("CREATE TRIGGER trip_alerts_cleanup DELETE ON " + ObaContract.Trips.PATH + " BEGIN " + "DELETE FROM " + ObaContract.TripAlerts.PATH + " WHERE " + ObaContract.TripAlerts.TRIP_ID + " = old." + ObaContract.Trips._ID + " AND " + ObaContract.TripAlerts.STOP_ID + " = old." + ObaContract.Trips.STOP_ID + ";" + "END");
                    }
                    if (!ListenerUtil.mutListener.listen(8710)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8719)) {
                if ((ListenerUtil.mutListener.listen(8716) ? (oldVersion >= 16) : (ListenerUtil.mutListener.listen(8715) ? (oldVersion <= 16) : (ListenerUtil.mutListener.listen(8714) ? (oldVersion > 16) : (ListenerUtil.mutListener.listen(8713) ? (oldVersion < 16) : (ListenerUtil.mutListener.listen(8712) ? (oldVersion != 16) : (oldVersion == 16))))))) {
                    if (!ListenerUtil.mutListener.listen(8717)) {
                        db.execSQL("CREATE TABLE " + ObaContract.ServiceAlerts.PATH + " (" + ObaContract.ServiceAlerts._ID + " VARCHAR PRIMARY KEY, " + ObaContract.ServiceAlerts.MARKED_READ_TIME + " INTEGER " + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8718)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8732)) {
                if ((ListenerUtil.mutListener.listen(8724) ? (oldVersion >= 17) : (ListenerUtil.mutListener.listen(8723) ? (oldVersion <= 17) : (ListenerUtil.mutListener.listen(8722) ? (oldVersion > 17) : (ListenerUtil.mutListener.listen(8721) ? (oldVersion < 17) : (ListenerUtil.mutListener.listen(8720) ? (oldVersion != 17) : (oldVersion == 17))))))) {
                    if (!ListenerUtil.mutListener.listen(8725)) {
                        db.execSQL("CREATE TABLE " + ObaContract.Regions.PATH + " (" + ObaContract.Regions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ObaContract.Regions.NAME + " VARCHAR NOT NULL, " + ObaContract.Regions.OBA_BASE_URL + " VARCHAR NOT NULL, " + ObaContract.Regions.SIRI_BASE_URL + " VARCHAR NOT NULL, " + ObaContract.Regions.LANGUAGE + " VARCHAR NOT NULL, " + ObaContract.Regions.CONTACT_EMAIL + " VARCHAR NOT NULL, " + ObaContract.Regions.SUPPORTS_OBA_DISCOVERY + " INTEGER NOT NULL, " + ObaContract.Regions.SUPPORTS_OBA_REALTIME + " INTEGER NOT NULL, " + ObaContract.Regions.SUPPORTS_SIRI_REALTIME + " INTEGER NOT NULL " + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8726)) {
                        db.execSQL("CREATE TABLE " + ObaContract.RegionBounds.PATH + " (" + ObaContract.RegionBounds._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ObaContract.RegionBounds.REGION_ID + " INTEGER NOT NULL, " + ObaContract.RegionBounds.LATITUDE + " REAL NOT NULL, " + ObaContract.RegionBounds.LONGITUDE + " REAL NOT NULL, " + ObaContract.RegionBounds.LAT_SPAN + " REAL NOT NULL, " + ObaContract.RegionBounds.LON_SPAN + " REAL NOT NULL " + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8727)) {
                        db.execSQL("DROP TRIGGER IF EXISTS region_bounds_cleanup");
                    }
                    if (!ListenerUtil.mutListener.listen(8728)) {
                        db.execSQL("CREATE TRIGGER region_bounds_cleanup DELETE ON " + ObaContract.Regions.PATH + " BEGIN " + "DELETE FROM " + ObaContract.RegionBounds.PATH + " WHERE " + ObaContract.RegionBounds.REGION_ID + " = old." + ObaContract.Regions._ID + ";" + "END");
                    }
                    if (!ListenerUtil.mutListener.listen(8729)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Stops.PATH + " ADD COLUMN " + ObaContract.Stops.REGION_ID + " INTEGER");
                    }
                    if (!ListenerUtil.mutListener.listen(8730)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Routes.PATH + " ADD COLUMN " + ObaContract.Routes.REGION_ID + " INTEGER");
                    }
                    if (!ListenerUtil.mutListener.listen(8731)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8740)) {
                if ((ListenerUtil.mutListener.listen(8737) ? (oldVersion >= 18) : (ListenerUtil.mutListener.listen(8736) ? (oldVersion <= 18) : (ListenerUtil.mutListener.listen(8735) ? (oldVersion > 18) : (ListenerUtil.mutListener.listen(8734) ? (oldVersion < 18) : (ListenerUtil.mutListener.listen(8733) ? (oldVersion != 18) : (oldVersion == 18))))))) {
                    if (!ListenerUtil.mutListener.listen(8738)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.TWITTER_URL + " VARCHAR");
                    }
                    if (!ListenerUtil.mutListener.listen(8739)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8748)) {
                if ((ListenerUtil.mutListener.listen(8745) ? (oldVersion >= 19) : (ListenerUtil.mutListener.listen(8744) ? (oldVersion <= 19) : (ListenerUtil.mutListener.listen(8743) ? (oldVersion > 19) : (ListenerUtil.mutListener.listen(8742) ? (oldVersion < 19) : (ListenerUtil.mutListener.listen(8741) ? (oldVersion != 19) : (oldVersion == 19))))))) {
                    if (!ListenerUtil.mutListener.listen(8746)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.EXPERIMENTAL + " INTEGER");
                    }
                    if (!ListenerUtil.mutListener.listen(8747)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8756)) {
                if ((ListenerUtil.mutListener.listen(8753) ? (oldVersion >= 20) : (ListenerUtil.mutListener.listen(8752) ? (oldVersion <= 20) : (ListenerUtil.mutListener.listen(8751) ? (oldVersion > 20) : (ListenerUtil.mutListener.listen(8750) ? (oldVersion < 20) : (ListenerUtil.mutListener.listen(8749) ? (oldVersion != 20) : (oldVersion == 20))))))) {
                    if (!ListenerUtil.mutListener.listen(8754)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.STOP_INFO_URL + " VARCHAR");
                    }
                    if (!ListenerUtil.mutListener.listen(8755)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8764)) {
                if ((ListenerUtil.mutListener.listen(8761) ? (oldVersion >= 21) : (ListenerUtil.mutListener.listen(8760) ? (oldVersion <= 21) : (ListenerUtil.mutListener.listen(8759) ? (oldVersion > 21) : (ListenerUtil.mutListener.listen(8758) ? (oldVersion < 21) : (ListenerUtil.mutListener.listen(8757) ? (oldVersion != 21) : (oldVersion == 21))))))) {
                    if (!ListenerUtil.mutListener.listen(8762)) {
                        db.execSQL("CREATE TABLE " + ObaContract.RouteHeadsignFavorites.PATH + " (" + ObaContract.RouteHeadsignFavorites.ROUTE_ID + " VARCHAR NOT NULL, " + ObaContract.RouteHeadsignFavorites.HEADSIGN + " VARCHAR NOT NULL, " + ObaContract.RouteHeadsignFavorites.STOP_ID + " VARCHAR NOT NULL, " + ObaContract.RouteHeadsignFavorites.EXCLUDE + " INTEGER NOT NULL " + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8763)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8772)) {
                if ((ListenerUtil.mutListener.listen(8769) ? (oldVersion >= 22) : (ListenerUtil.mutListener.listen(8768) ? (oldVersion <= 22) : (ListenerUtil.mutListener.listen(8767) ? (oldVersion > 22) : (ListenerUtil.mutListener.listen(8766) ? (oldVersion < 22) : (ListenerUtil.mutListener.listen(8765) ? (oldVersion != 22) : (oldVersion == 22))))))) {
                    if (!ListenerUtil.mutListener.listen(8770)) {
                        db.execSQL("CREATE TABLE " + ObaContract.RegionOpen311Servers.PATH + " (" + ObaContract.RegionOpen311Servers._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ObaContract.RegionOpen311Servers.REGION_ID + " INTEGER NOT NULL, " + ObaContract.RegionOpen311Servers.JURISDICTION + " VARCHAR, " + ObaContract.RegionOpen311Servers.API_KEY + " VARCHAR NOT NULL, " + ObaContract.RegionOpen311Servers.BASE_URL + " VARCHAR NOT NULL " + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8771)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8781)) {
                if ((ListenerUtil.mutListener.listen(8777) ? (oldVersion >= 23) : (ListenerUtil.mutListener.listen(8776) ? (oldVersion <= 23) : (ListenerUtil.mutListener.listen(8775) ? (oldVersion > 23) : (ListenerUtil.mutListener.listen(8774) ? (oldVersion < 23) : (ListenerUtil.mutListener.listen(8773) ? (oldVersion != 23) : (oldVersion == 23))))))) {
                    if (!ListenerUtil.mutListener.listen(8778)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.OTP_BASE_URL + " VARCHAR");
                    }
                    if (!ListenerUtil.mutListener.listen(8779)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.OTP_CONTACT_EMAIL + " VARCHAR");
                    }
                    if (!ListenerUtil.mutListener.listen(8780)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8789)) {
                if ((ListenerUtil.mutListener.listen(8786) ? (oldVersion >= 24) : (ListenerUtil.mutListener.listen(8785) ? (oldVersion <= 24) : (ListenerUtil.mutListener.listen(8784) ? (oldVersion > 24) : (ListenerUtil.mutListener.listen(8783) ? (oldVersion < 24) : (ListenerUtil.mutListener.listen(8782) ? (oldVersion != 24) : (oldVersion == 24))))))) {
                    if (!ListenerUtil.mutListener.listen(8787)) {
                        db.execSQL("ALTER TABLE " + ObaContract.ServiceAlerts.PATH + " ADD COLUMN " + ObaContract.ServiceAlerts.HIDDEN + " INTEGER");
                    }
                    if (!ListenerUtil.mutListener.listen(8788)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8797)) {
                if ((ListenerUtil.mutListener.listen(8794) ? (oldVersion >= 25) : (ListenerUtil.mutListener.listen(8793) ? (oldVersion <= 25) : (ListenerUtil.mutListener.listen(8792) ? (oldVersion > 25) : (ListenerUtil.mutListener.listen(8791) ? (oldVersion < 25) : (ListenerUtil.mutListener.listen(8790) ? (oldVersion != 25) : (oldVersion == 25))))))) {
                    if (!ListenerUtil.mutListener.listen(8795)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.SUPPORTS_OTP_BIKESHARE + " INTEGER");
                    }
                    if (!ListenerUtil.mutListener.listen(8796)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8808)) {
                if ((ListenerUtil.mutListener.listen(8802) ? (oldVersion >= 26) : (ListenerUtil.mutListener.listen(8801) ? (oldVersion <= 26) : (ListenerUtil.mutListener.listen(8800) ? (oldVersion > 26) : (ListenerUtil.mutListener.listen(8799) ? (oldVersion < 26) : (ListenerUtil.mutListener.listen(8798) ? (oldVersion != 26) : (oldVersion == 26))))))) {
                    /**
                     * Bike share and Embedded Social both added columns in version 26;
                     * Bike share in Beta, Embedded Social in alpha
                     * We are adding extra logic here to prevent either group from breaking on update
                     */
                    try {
                        if (!ListenerUtil.mutListener.listen(8804)) {
                            db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.SUPPORTS_OTP_BIKESHARE + " INTEGER");
                        }
                    } catch (SQLiteException e) {
                        if (!ListenerUtil.mutListener.listen(8803)) {
                            Log.w(TAG, "Database already has bike share column - " + e);
                        }
                    }
                    try {
                        if (!ListenerUtil.mutListener.listen(8806)) {
                            db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.SUPPORTS_EMBEDDED_SOCIAL + " INTEGER");
                        }
                    } catch (SQLiteException e) {
                        if (!ListenerUtil.mutListener.listen(8805)) {
                            Log.w(TAG, "Database already has embedded social column - " + e);
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8807)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8818)) {
                if ((ListenerUtil.mutListener.listen(8813) ? (oldVersion >= 27) : (ListenerUtil.mutListener.listen(8812) ? (oldVersion <= 27) : (ListenerUtil.mutListener.listen(8811) ? (oldVersion > 27) : (ListenerUtil.mutListener.listen(8810) ? (oldVersion < 27) : (ListenerUtil.mutListener.listen(8809) ? (oldVersion != 27) : (oldVersion == 27))))))) {
                    if (!ListenerUtil.mutListener.listen(8814)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.PAYMENT_ANDROID_APP_ID + " VARCHAR");
                    }
                    if (!ListenerUtil.mutListener.listen(8815)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.PAYMENT_WARNING_TITLE + " VARCHAR");
                    }
                    if (!ListenerUtil.mutListener.listen(8816)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.PAYMENT_WARNING_BODY + " VARCHAR");
                    }
                    if (!ListenerUtil.mutListener.listen(8817)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8826)) {
                if ((ListenerUtil.mutListener.listen(8823) ? (oldVersion >= 28) : (ListenerUtil.mutListener.listen(8822) ? (oldVersion <= 28) : (ListenerUtil.mutListener.listen(8821) ? (oldVersion > 28) : (ListenerUtil.mutListener.listen(8820) ? (oldVersion < 28) : (ListenerUtil.mutListener.listen(8819) ? (oldVersion != 28) : (oldVersion == 28))))))) {
                    if (!ListenerUtil.mutListener.listen(8824)) {
                        db.execSQL("CREATE TABLE " + ObaContract.NavStops.PATH + " (" + ObaContract.NavStops._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ObaContract.NavStops.NAV_ID + " VARCHAR NOT NULL, " + ObaContract.NavStops.START_TIME + " INTEGER NOT NULL, " + ObaContract.NavStops.TRIP_ID + " VARCHAR NOT NULL, " + ObaContract.NavStops.DESTINATION_ID + " VARCHAR NOT NULL, " + ObaContract.NavStops.BEFORE_ID + " VARCHAR NOT NULL, " + ObaContract.NavStops.SEQUENCE + " INTEGER NOT NULL, " + ObaContract.NavStops.ACTIVE + " INTEGER NOT NULL " + ");");
                    }
                    if (!ListenerUtil.mutListener.listen(8825)) {
                        ++oldVersion;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8834)) {
                if ((ListenerUtil.mutListener.listen(8831) ? (oldVersion >= 29) : (ListenerUtil.mutListener.listen(8830) ? (oldVersion <= 29) : (ListenerUtil.mutListener.listen(8829) ? (oldVersion > 29) : (ListenerUtil.mutListener.listen(8828) ? (oldVersion < 29) : (ListenerUtil.mutListener.listen(8827) ? (oldVersion != 29) : (oldVersion == 29))))))) {
                    if (!ListenerUtil.mutListener.listen(8832)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.TRAVEL_BEHAVIOR_DATA_COLLECTION + " INTEGER");
                    }
                    if (!ListenerUtil.mutListener.listen(8833)) {
                        db.execSQL("ALTER TABLE " + ObaContract.Regions.PATH + " ADD COLUMN " + ObaContract.Regions.ENROLL_PARTICIPANTS_IN_STUDY + " INTEGER");
                    }
                }
            }
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (!ListenerUtil.mutListener.listen(8835)) {
                /**
                 * See #473 - This can happen if the user does a backup of stops/routes, updates to a
                 * new version of the app that includes a database upgrade, and then tries to restore
                 * the backup.  This is because our current backup/restore mechanism is simply copying
                 * the database. In this case, we should try to make sure the schema is current so no
                 * code fails on missing tables or columns.
                 */
                Log.d(TAG, "Downgrading database from version " + oldVersion + " to " + newVersion);
            }
            if (!ListenerUtil.mutListener.listen(8836)) {
                onUpgrade(db, newVersion, newVersion);
            }
        }

        private void bootstrapDatabase(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(8837)) {
                db.execSQL("CREATE TABLE " + ObaContract.Stops.PATH + " (" + ObaContract.Stops._ID + " VARCHAR PRIMARY KEY, " + ObaContract.Stops.CODE + " VARCHAR NOT NULL, " + ObaContract.Stops.NAME + " VARCHAR NOT NULL, " + ObaContract.Stops.DIRECTION + " CHAR[2] NOT NULL," + ObaContract.Stops.USE_COUNT + " INTEGER NOT NULL," + ObaContract.Stops.LATITUDE + " DOUBLE NOT NULL," + ObaContract.Stops.LONGITUDE + " DOUBLE NOT NULL" + ");");
            }
            if (!ListenerUtil.mutListener.listen(8838)) {
                db.execSQL("CREATE TABLE " + ObaContract.Routes.PATH + " (" + ObaContract.Routes._ID + " VARCHAR PRIMARY KEY, " + ObaContract.Routes.SHORTNAME + " VARCHAR NOT NULL, " + ObaContract.Routes.LONGNAME + " VARCHAR, " + ObaContract.Routes.USE_COUNT + " INTEGER NOT NULL" + ");");
            }
            if (!ListenerUtil.mutListener.listen(8839)) {
                db.execSQL("CREATE TABLE " + ObaContract.Trips.PATH + " (" + ObaContract.Trips._ID + " VARCHAR NOT NULL, " + ObaContract.Trips.STOP_ID + " VARCHAR NOT NULL, " + ObaContract.Trips.ROUTE_ID + " VARCHAR NOT NULL, " + ObaContract.Trips.DEPARTURE + " INTEGER NOT NULL, " + ObaContract.Trips.HEADSIGN + " VARCHAR NOT NULL, " + ObaContract.Trips.NAME + " VARCHAR NOT NULL, " + ObaContract.Trips.REMINDER + " INTEGER NOT NULL, " + ObaContract.Trips.DAYS + " INTEGER NOT NULL" + ");");
            }
        }

        private void dropTables(SQLiteDatabase db) {
            if (!ListenerUtil.mutListener.listen(8840)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.StopRouteFilters.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8841)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.Routes.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8842)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.Stops.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8843)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.Trips.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8844)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.TripAlerts.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8845)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.ServiceAlerts.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8846)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.Regions.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8847)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.RegionBounds.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8848)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.RegionOpen311Servers.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8849)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.RouteHeadsignFavorites.PATH);
            }
            if (!ListenerUtil.mutListener.listen(8850)) {
                db.execSQL("DROP TABLE IF EXISTS " + ObaContract.NavStops.PATH);
            }
        }
    }

    private static final int STOPS = 1;

    private static final int STOPS_ID = 2;

    private static final int ROUTES = 3;

    private static final int ROUTES_ID = 4;

    private static final int TRIPS = 5;

    private static final int TRIPS_ID = 6;

    private static final int TRIP_ALERTS = 7;

    private static final int TRIP_ALERTS_ID = 8;

    private static final int STOP_ROUTE_FILTERS = 9;

    private static final int SERVICE_ALERTS = 10;

    private static final int SERVICE_ALERTS_ID = 11;

    private static final int REGIONS = 12;

    private static final int REGIONS_ID = 13;

    private static final int REGION_BOUNDS = 14;

    private static final int REGION_BOUNDS_ID = 15;

    private static final int ROUTE_HEADSIGN_FAVORITES = 16;

    private static final int REGION_OPEN311_SERVERS = 17;

    private static final int REGION_OPEN311_SERVERS_ID = 18;

    private static final int NAV_STOPS = 19;

    private static final UriMatcher sUriMatcher;

    private static final HashMap<String, String> sStopsProjectionMap;

    private static final HashMap<String, String> sRoutesProjectionMap;

    private static final HashMap<String, String> sTripsProjectionMap;

    private static final HashMap<String, String> sTripAlertsProjectionMap;

    private static final HashMap<String, String> sServiceAlertsProjectionMap;

    private static final HashMap<String, String> sRegionsProjectionMap;

    private static final HashMap<String, String> sRegionBoundsProjectionMap;

    private static final HashMap<String, String> sRegionOpen311ProjectionMap;

    // Insert helpers are useful.
    private DatabaseUtils.InsertHelper mStopsInserter;

    private DatabaseUtils.InsertHelper mRoutesInserter;

    private DatabaseUtils.InsertHelper mTripsInserter;

    private DatabaseUtils.InsertHelper mTripAlertsInserter;

    private DatabaseUtils.InsertHelper mFilterInserter;

    private DatabaseUtils.InsertHelper mServiceAlertsInserter;

    private DatabaseUtils.InsertHelper mRegionsInserter;

    private DatabaseUtils.InsertHelper mRegionBoundsInserter;

    private DatabaseUtils.InsertHelper mRegionOpen311ServersInserter;

    private DatabaseUtils.InsertHelper mRouteHeadsignFavoritesInserter;

    private DatabaseUtils.InsertHelper mNavStopsInserter;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        if (!ListenerUtil.mutListener.listen(8851)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Stops.PATH, STOPS);
        }
        if (!ListenerUtil.mutListener.listen(8852)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Stops.PATH + "/*", STOPS_ID);
        }
        if (!ListenerUtil.mutListener.listen(8853)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Routes.PATH, ROUTES);
        }
        if (!ListenerUtil.mutListener.listen(8854)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Routes.PATH + "/*", ROUTES_ID);
        }
        if (!ListenerUtil.mutListener.listen(8855)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Trips.PATH, TRIPS);
        }
        if (!ListenerUtil.mutListener.listen(8856)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Trips.PATH + "/*/*", TRIPS_ID);
        }
        if (!ListenerUtil.mutListener.listen(8857)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.TripAlerts.PATH, TRIP_ALERTS);
        }
        if (!ListenerUtil.mutListener.listen(8858)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.TripAlerts.PATH + "/#", TRIP_ALERTS_ID);
        }
        if (!ListenerUtil.mutListener.listen(8859)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.StopRouteFilters.PATH, STOP_ROUTE_FILTERS);
        }
        if (!ListenerUtil.mutListener.listen(8860)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.ServiceAlerts.PATH, SERVICE_ALERTS);
        }
        if (!ListenerUtil.mutListener.listen(8861)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.ServiceAlerts.PATH + "/*", SERVICE_ALERTS_ID);
        }
        if (!ListenerUtil.mutListener.listen(8862)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Regions.PATH, REGIONS);
        }
        if (!ListenerUtil.mutListener.listen(8863)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.Regions.PATH + "/#", REGIONS_ID);
        }
        if (!ListenerUtil.mutListener.listen(8864)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.RegionBounds.PATH, REGION_BOUNDS);
        }
        if (!ListenerUtil.mutListener.listen(8865)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.RegionBounds.PATH + "/#", REGION_BOUNDS_ID);
        }
        if (!ListenerUtil.mutListener.listen(8866)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.RegionOpen311Servers.PATH, REGION_OPEN311_SERVERS);
        }
        if (!ListenerUtil.mutListener.listen(8867)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.RegionOpen311Servers.PATH + "/#", REGION_OPEN311_SERVERS_ID);
        }
        if (!ListenerUtil.mutListener.listen(8868)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.RouteHeadsignFavorites.PATH, ROUTE_HEADSIGN_FAVORITES);
        }
        if (!ListenerUtil.mutListener.listen(8869)) {
            sUriMatcher.addURI(ObaContract.AUTHORITY, ObaContract.NavStops.PATH, NAV_STOPS);
        }
        sStopsProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8870)) {
            sStopsProjectionMap.put(ObaContract.Stops._ID, ObaContract.Stops._ID);
        }
        if (!ListenerUtil.mutListener.listen(8871)) {
            sStopsProjectionMap.put(ObaContract.Stops.CODE, ObaContract.Stops.CODE);
        }
        if (!ListenerUtil.mutListener.listen(8872)) {
            sStopsProjectionMap.put(ObaContract.Stops.NAME, ObaContract.Stops.NAME);
        }
        if (!ListenerUtil.mutListener.listen(8873)) {
            sStopsProjectionMap.put(ObaContract.Stops.DIRECTION, ObaContract.Stops.DIRECTION);
        }
        if (!ListenerUtil.mutListener.listen(8874)) {
            sStopsProjectionMap.put(ObaContract.Stops.USE_COUNT, ObaContract.Stops.USE_COUNT);
        }
        if (!ListenerUtil.mutListener.listen(8875)) {
            sStopsProjectionMap.put(ObaContract.Stops.LATITUDE, ObaContract.Stops.LATITUDE);
        }
        if (!ListenerUtil.mutListener.listen(8876)) {
            sStopsProjectionMap.put(ObaContract.Stops.LONGITUDE, ObaContract.Stops.LONGITUDE);
        }
        if (!ListenerUtil.mutListener.listen(8877)) {
            sStopsProjectionMap.put(ObaContract.Stops.USER_NAME, ObaContract.Stops.USER_NAME);
        }
        if (!ListenerUtil.mutListener.listen(8878)) {
            sStopsProjectionMap.put(ObaContract.Stops.ACCESS_TIME, ObaContract.Stops.ACCESS_TIME);
        }
        if (!ListenerUtil.mutListener.listen(8879)) {
            sStopsProjectionMap.put(ObaContract.Stops.FAVORITE, ObaContract.Stops.FAVORITE);
        }
        if (!ListenerUtil.mutListener.listen(8880)) {
            sStopsProjectionMap.put(ObaContract.Stops._COUNT, "count(*)");
        }
        if (!ListenerUtil.mutListener.listen(8881)) {
            sStopsProjectionMap.put(ObaContract.Stops.UI_NAME, "CASE WHEN " + ObaContract.Stops.USER_NAME + " IS NOT NULL THEN " + ObaContract.Stops.USER_NAME + " ELSE " + ObaContract.Stops.NAME + " END AS " + ObaContract.Stops.UI_NAME);
        }
        sRoutesProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8882)) {
            sRoutesProjectionMap.put(ObaContract.Routes._ID, ObaContract.Routes._ID);
        }
        if (!ListenerUtil.mutListener.listen(8883)) {
            sRoutesProjectionMap.put(ObaContract.Routes.SHORTNAME, ObaContract.Routes.SHORTNAME);
        }
        if (!ListenerUtil.mutListener.listen(8884)) {
            sRoutesProjectionMap.put(ObaContract.Routes.LONGNAME, ObaContract.Routes.LONGNAME);
        }
        if (!ListenerUtil.mutListener.listen(8885)) {
            sRoutesProjectionMap.put(ObaContract.Routes.USE_COUNT, ObaContract.Routes.USE_COUNT);
        }
        if (!ListenerUtil.mutListener.listen(8886)) {
            sRoutesProjectionMap.put(ObaContract.Routes.USER_NAME, ObaContract.Routes.USER_NAME);
        }
        if (!ListenerUtil.mutListener.listen(8887)) {
            sRoutesProjectionMap.put(ObaContract.Routes.ACCESS_TIME, ObaContract.Routes.ACCESS_TIME);
        }
        if (!ListenerUtil.mutListener.listen(8888)) {
            sRoutesProjectionMap.put(ObaContract.Routes.FAVORITE, ObaContract.Routes.FAVORITE);
        }
        if (!ListenerUtil.mutListener.listen(8889)) {
            sRoutesProjectionMap.put(ObaContract.Routes.URL, ObaContract.Routes.URL);
        }
        if (!ListenerUtil.mutListener.listen(8890)) {
            sRoutesProjectionMap.put(ObaContract.Routes._COUNT, "count(*)");
        }
        sTripsProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8891)) {
            sTripsProjectionMap.put(ObaContract.Trips._ID, ObaContract.Trips._ID);
        }
        if (!ListenerUtil.mutListener.listen(8892)) {
            sTripsProjectionMap.put(ObaContract.Trips.STOP_ID, ObaContract.Trips.STOP_ID);
        }
        if (!ListenerUtil.mutListener.listen(8893)) {
            sTripsProjectionMap.put(ObaContract.Trips.ROUTE_ID, ObaContract.Trips.ROUTE_ID);
        }
        if (!ListenerUtil.mutListener.listen(8894)) {
            sTripsProjectionMap.put(ObaContract.Trips.DEPARTURE, ObaContract.Trips.DEPARTURE);
        }
        if (!ListenerUtil.mutListener.listen(8895)) {
            sTripsProjectionMap.put(ObaContract.Trips.HEADSIGN, ObaContract.Trips.HEADSIGN);
        }
        if (!ListenerUtil.mutListener.listen(8896)) {
            sTripsProjectionMap.put(ObaContract.Trips.NAME, ObaContract.Trips.NAME);
        }
        if (!ListenerUtil.mutListener.listen(8897)) {
            sTripsProjectionMap.put(ObaContract.Trips.REMINDER, ObaContract.Trips.REMINDER);
        }
        if (!ListenerUtil.mutListener.listen(8898)) {
            sTripsProjectionMap.put(ObaContract.Trips.DAYS, ObaContract.Trips.DAYS);
        }
        if (!ListenerUtil.mutListener.listen(8899)) {
            sTripsProjectionMap.put(ObaContract.Trips._COUNT, "count(*)");
        }
        sTripAlertsProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8900)) {
            sTripAlertsProjectionMap.put(ObaContract.TripAlerts._ID, ObaContract.TripAlerts._ID);
        }
        if (!ListenerUtil.mutListener.listen(8901)) {
            sTripAlertsProjectionMap.put(ObaContract.TripAlerts.TRIP_ID, ObaContract.TripAlerts.TRIP_ID);
        }
        if (!ListenerUtil.mutListener.listen(8902)) {
            sTripAlertsProjectionMap.put(ObaContract.TripAlerts.STOP_ID, ObaContract.TripAlerts.STOP_ID);
        }
        if (!ListenerUtil.mutListener.listen(8903)) {
            sTripAlertsProjectionMap.put(ObaContract.TripAlerts.START_TIME, ObaContract.TripAlerts.START_TIME);
        }
        if (!ListenerUtil.mutListener.listen(8904)) {
            sTripAlertsProjectionMap.put(ObaContract.TripAlerts.STATE, ObaContract.TripAlerts.STATE);
        }
        if (!ListenerUtil.mutListener.listen(8905)) {
            sTripAlertsProjectionMap.put(ObaContract.TripAlerts._COUNT, "count(*)");
        }
        sServiceAlertsProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8906)) {
            sServiceAlertsProjectionMap.put(ObaContract.ServiceAlerts._ID, ObaContract.ServiceAlerts._ID);
        }
        if (!ListenerUtil.mutListener.listen(8907)) {
            sServiceAlertsProjectionMap.put(ObaContract.ServiceAlerts.MARKED_READ_TIME, ObaContract.ServiceAlerts.MARKED_READ_TIME);
        }
        if (!ListenerUtil.mutListener.listen(8908)) {
            sServiceAlertsProjectionMap.put(ObaContract.ServiceAlerts.HIDDEN, ObaContract.ServiceAlerts.HIDDEN);
        }
        sRegionsProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8909)) {
            sRegionsProjectionMap.put(ObaContract.Regions._ID, ObaContract.Regions._ID);
        }
        if (!ListenerUtil.mutListener.listen(8910)) {
            sRegionsProjectionMap.put(ObaContract.Regions.NAME, ObaContract.Regions.NAME);
        }
        if (!ListenerUtil.mutListener.listen(8911)) {
            sRegionsProjectionMap.put(ObaContract.Regions.OBA_BASE_URL, ObaContract.Regions.OBA_BASE_URL);
        }
        if (!ListenerUtil.mutListener.listen(8912)) {
            sRegionsProjectionMap.put(ObaContract.Regions.SIRI_BASE_URL, ObaContract.Regions.SIRI_BASE_URL);
        }
        if (!ListenerUtil.mutListener.listen(8913)) {
            sRegionsProjectionMap.put(ObaContract.Regions.LANGUAGE, ObaContract.Regions.LANGUAGE);
        }
        if (!ListenerUtil.mutListener.listen(8914)) {
            sRegionsProjectionMap.put(ObaContract.Regions.CONTACT_EMAIL, ObaContract.Regions.CONTACT_EMAIL);
        }
        if (!ListenerUtil.mutListener.listen(8915)) {
            sRegionsProjectionMap.put(ObaContract.Regions.SUPPORTS_OBA_DISCOVERY, ObaContract.Regions.SUPPORTS_OBA_DISCOVERY);
        }
        if (!ListenerUtil.mutListener.listen(8916)) {
            sRegionsProjectionMap.put(ObaContract.Regions.SUPPORTS_OBA_REALTIME, ObaContract.Regions.SUPPORTS_OBA_REALTIME);
        }
        if (!ListenerUtil.mutListener.listen(8917)) {
            sRegionsProjectionMap.put(ObaContract.Regions.SUPPORTS_SIRI_REALTIME, ObaContract.Regions.SUPPORTS_SIRI_REALTIME);
        }
        if (!ListenerUtil.mutListener.listen(8918)) {
            sRegionsProjectionMap.put(ObaContract.Regions.SUPPORTS_EMBEDDED_SOCIAL, ObaContract.Regions.SUPPORTS_EMBEDDED_SOCIAL);
        }
        if (!ListenerUtil.mutListener.listen(8919)) {
            sRegionsProjectionMap.put(ObaContract.Regions.TWITTER_URL, ObaContract.Regions.TWITTER_URL);
        }
        if (!ListenerUtil.mutListener.listen(8920)) {
            sRegionsProjectionMap.put(ObaContract.Regions.EXPERIMENTAL, ObaContract.Regions.EXPERIMENTAL);
        }
        if (!ListenerUtil.mutListener.listen(8921)) {
            sRegionsProjectionMap.put(ObaContract.Regions.STOP_INFO_URL, ObaContract.Regions.STOP_INFO_URL);
        }
        if (!ListenerUtil.mutListener.listen(8922)) {
            sRegionsProjectionMap.put(ObaContract.Regions.OTP_BASE_URL, ObaContract.Regions.OTP_BASE_URL);
        }
        if (!ListenerUtil.mutListener.listen(8923)) {
            sRegionsProjectionMap.put(ObaContract.Regions.OTP_CONTACT_EMAIL, ObaContract.Regions.OTP_CONTACT_EMAIL);
        }
        if (!ListenerUtil.mutListener.listen(8924)) {
            sRegionsProjectionMap.put(ObaContract.Regions.SUPPORTS_OTP_BIKESHARE, ObaContract.Regions.SUPPORTS_OTP_BIKESHARE);
        }
        if (!ListenerUtil.mutListener.listen(8925)) {
            sRegionsProjectionMap.put(ObaContract.Regions.PAYMENT_ANDROID_APP_ID, ObaContract.Regions.PAYMENT_ANDROID_APP_ID);
        }
        if (!ListenerUtil.mutListener.listen(8926)) {
            sRegionsProjectionMap.put(ObaContract.Regions.PAYMENT_WARNING_TITLE, ObaContract.Regions.PAYMENT_WARNING_TITLE);
        }
        if (!ListenerUtil.mutListener.listen(8927)) {
            sRegionsProjectionMap.put(ObaContract.Regions.PAYMENT_WARNING_BODY, ObaContract.Regions.PAYMENT_WARNING_BODY);
        }
        if (!ListenerUtil.mutListener.listen(8928)) {
            sRegionsProjectionMap.put(ObaContract.Regions.TRAVEL_BEHAVIOR_DATA_COLLECTION, ObaContract.Regions.TRAVEL_BEHAVIOR_DATA_COLLECTION);
        }
        if (!ListenerUtil.mutListener.listen(8929)) {
            sRegionsProjectionMap.put(ObaContract.Regions.ENROLL_PARTICIPANTS_IN_STUDY, ObaContract.Regions.ENROLL_PARTICIPANTS_IN_STUDY);
        }
        sRegionBoundsProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8930)) {
            sRegionBoundsProjectionMap.put(ObaContract.RegionBounds._ID, ObaContract.RegionBounds._ID);
        }
        if (!ListenerUtil.mutListener.listen(8931)) {
            sRegionBoundsProjectionMap.put(ObaContract.RegionBounds.REGION_ID, ObaContract.RegionBounds.REGION_ID);
        }
        if (!ListenerUtil.mutListener.listen(8932)) {
            sRegionBoundsProjectionMap.put(ObaContract.RegionBounds.LATITUDE, ObaContract.RegionBounds.LATITUDE);
        }
        if (!ListenerUtil.mutListener.listen(8933)) {
            sRegionBoundsProjectionMap.put(ObaContract.RegionBounds.LONGITUDE, ObaContract.RegionBounds.LONGITUDE);
        }
        if (!ListenerUtil.mutListener.listen(8934)) {
            sRegionBoundsProjectionMap.put(ObaContract.RegionBounds.LAT_SPAN, ObaContract.RegionBounds.LAT_SPAN);
        }
        if (!ListenerUtil.mutListener.listen(8935)) {
            sRegionBoundsProjectionMap.put(ObaContract.RegionBounds.LON_SPAN, ObaContract.RegionBounds.LON_SPAN);
        }
        sRegionOpen311ProjectionMap = new HashMap<String, String>();
        if (!ListenerUtil.mutListener.listen(8936)) {
            sRegionOpen311ProjectionMap.put(ObaContract.RegionOpen311Servers._ID, ObaContract.RegionOpen311Servers._ID);
        }
        if (!ListenerUtil.mutListener.listen(8937)) {
            sRegionOpen311ProjectionMap.put(ObaContract.RegionOpen311Servers.REGION_ID, ObaContract.RegionOpen311Servers.REGION_ID);
        }
        if (!ListenerUtil.mutListener.listen(8938)) {
            sRegionOpen311ProjectionMap.put(ObaContract.RegionOpen311Servers.JURISDICTION, ObaContract.RegionOpen311Servers.JURISDICTION);
        }
        if (!ListenerUtil.mutListener.listen(8939)) {
            sRegionOpen311ProjectionMap.put(ObaContract.RegionOpen311Servers.API_KEY, ObaContract.RegionOpen311Servers.API_KEY);
        }
        if (!ListenerUtil.mutListener.listen(8940)) {
            sRegionOpen311ProjectionMap.put(ObaContract.RegionOpen311Servers.BASE_URL, ObaContract.RegionOpen311Servers.BASE_URL);
        }
    }

    private SQLiteDatabase mDb;

    private OpenHelper mOpenHelper;

    public static File getDatabasePath(Context context) {
        return context.getDatabasePath(DATABASE_NAME);
    }

    @Override
    public boolean onCreate() {
        if (!ListenerUtil.mutListener.listen(8941)) {
            mOpenHelper = new OpenHelper(getContext());
        }
        return true;
    }

    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        switch(match) {
            case STOPS:
                return ObaContract.Stops.CONTENT_DIR_TYPE;
            case STOPS_ID:
                return ObaContract.Stops.CONTENT_TYPE;
            case ROUTES:
                return ObaContract.Routes.CONTENT_DIR_TYPE;
            case ROUTES_ID:
                return ObaContract.Routes.CONTENT_TYPE;
            case TRIPS:
                return ObaContract.Trips.CONTENT_DIR_TYPE;
            case TRIPS_ID:
                return ObaContract.Trips.CONTENT_TYPE;
            case TRIP_ALERTS:
                return ObaContract.TripAlerts.CONTENT_DIR_TYPE;
            case TRIP_ALERTS_ID:
                return ObaContract.TripAlerts.CONTENT_TYPE;
            case STOP_ROUTE_FILTERS:
                return ObaContract.StopRouteFilters.CONTENT_DIR_TYPE;
            case SERVICE_ALERTS:
                return ObaContract.ServiceAlerts.CONTENT_DIR_TYPE;
            case SERVICE_ALERTS_ID:
                return ObaContract.ServiceAlerts.CONTENT_TYPE;
            case REGIONS:
                return ObaContract.Regions.CONTENT_DIR_TYPE;
            case REGIONS_ID:
                return ObaContract.Regions.CONTENT_TYPE;
            case REGION_BOUNDS:
                return ObaContract.RegionBounds.CONTENT_DIR_TYPE;
            case REGION_BOUNDS_ID:
                return ObaContract.RegionBounds.CONTENT_TYPE;
            case REGION_OPEN311_SERVERS:
                return ObaContract.RegionOpen311Servers.CONTENT_DIR_TYPE;
            case REGION_OPEN311_SERVERS_ID:
                return ObaContract.RegionOpen311Servers.CONTENT_TYPE;
            case ROUTE_HEADSIGN_FAVORITES:
                return ObaContract.RouteHeadsignFavorites.CONTENT_DIR_TYPE;
            case NAV_STOPS:
                return ObaContract.NavStops.CONTENT_DIR_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = getDatabase();
        if (!ListenerUtil.mutListener.listen(8942)) {
            db.beginTransaction();
        }
        try {
            Uri result = insertInternal(db, uri, values);
            if (!ListenerUtil.mutListener.listen(8944)) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            if (!ListenerUtil.mutListener.listen(8945)) {
                db.setTransactionSuccessful();
            }
            return result;
        } finally {
            if (!ListenerUtil.mutListener.listen(8943)) {
                db.endTransaction();
            }
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = getDatabase();
        return queryInternal(db, uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = getDatabase();
        if (!ListenerUtil.mutListener.listen(8946)) {
            db.beginTransaction();
        }
        try {
            int result = updateInternal(db, uri, values, selection, selectionArgs);
            if (!ListenerUtil.mutListener.listen(8954)) {
                if ((ListenerUtil.mutListener.listen(8952) ? (result >= 0) : (ListenerUtil.mutListener.listen(8951) ? (result <= 0) : (ListenerUtil.mutListener.listen(8950) ? (result < 0) : (ListenerUtil.mutListener.listen(8949) ? (result != 0) : (ListenerUtil.mutListener.listen(8948) ? (result == 0) : (result > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(8953)) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8955)) {
                db.setTransactionSuccessful();
            }
            return result;
        } finally {
            if (!ListenerUtil.mutListener.listen(8947)) {
                db.endTransaction();
            }
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = getDatabase();
        if (!ListenerUtil.mutListener.listen(8956)) {
            db.beginTransaction();
        }
        try {
            int result = deleteInternal(db, uri, selection, selectionArgs);
            if (!ListenerUtil.mutListener.listen(8964)) {
                if ((ListenerUtil.mutListener.listen(8962) ? (result >= 0) : (ListenerUtil.mutListener.listen(8961) ? (result <= 0) : (ListenerUtil.mutListener.listen(8960) ? (result < 0) : (ListenerUtil.mutListener.listen(8959) ? (result != 0) : (ListenerUtil.mutListener.listen(8958) ? (result == 0) : (result > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(8963)) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8965)) {
                db.setTransactionSuccessful();
            }
            return result;
        } finally {
            if (!ListenerUtil.mutListener.listen(8957)) {
                db.endTransaction();
            }
        }
    }

    private Uri insertInternal(SQLiteDatabase db, Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        String id;
        Uri result;
        long longId;
        switch(match) {
            case STOPS:
                // (And we'd better have a stop ID)
                id = values.getAsString(ObaContract.Stops._ID);
                if (!ListenerUtil.mutListener.listen(8966)) {
                    if (id == null) {
                        throw new IllegalArgumentException("Need a stop ID to insert! " + uri);
                    }
                }
                result = Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(8967)) {
                    mStopsInserter.insert(values);
                }
                return result;
            case ROUTES:
                // (And we'd better have a route ID)
                id = values.getAsString(ObaContract.Routes._ID);
                if (!ListenerUtil.mutListener.listen(8968)) {
                    if (id == null) {
                        throw new IllegalArgumentException("Need a routes ID to insert! " + uri);
                    }
                }
                result = Uri.withAppendedPath(ObaContract.Routes.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(8969)) {
                    mRoutesInserter.insert(values);
                }
                return result;
            case TRIPS:
                // (And we'd better have a trip ID)
                id = values.getAsString(ObaContract.Trips._ID);
                if (!ListenerUtil.mutListener.listen(8970)) {
                    if (id == null) {
                        throw new IllegalArgumentException("Need a trip ID to insert! " + uri);
                    }
                }
                result = Uri.withAppendedPath(ObaContract.Trips.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(8971)) {
                    mTripsInserter.insert(values);
                }
                return result;
            case TRIP_ALERTS:
                longId = mTripAlertsInserter.insert(values);
                result = ContentUris.withAppendedId(ObaContract.TripAlerts.CONTENT_URI, longId);
                return result;
            case STOP_ROUTE_FILTERS:
                // (And we'd better have a stop ID)
                id = values.getAsString(ObaContract.StopRouteFilters.STOP_ID);
                if (!ListenerUtil.mutListener.listen(8972)) {
                    if (id == null) {
                        throw new IllegalArgumentException("Need a stop ID to insert! " + uri);
                    }
                }
                result = Uri.withAppendedPath(ObaContract.StopRouteFilters.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(8973)) {
                    mFilterInserter.insert(values);
                }
                return result;
            case SERVICE_ALERTS:
                // (And we'd better have a situation ID)
                id = values.getAsString(ObaContract.ServiceAlerts._ID);
                if (!ListenerUtil.mutListener.listen(8974)) {
                    if (id == null) {
                        throw new IllegalArgumentException("Need a situation ID to insert! " + uri);
                    }
                }
                result = Uri.withAppendedPath(ObaContract.ServiceAlerts.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(8975)) {
                    mServiceAlertsInserter.insert(values);
                }
                return result;
            case REGIONS:
                longId = mRegionsInserter.insert(values);
                result = ContentUris.withAppendedId(ObaContract.Regions.CONTENT_URI, longId);
                return result;
            case REGION_BOUNDS:
                longId = mRegionBoundsInserter.insert(values);
                result = ContentUris.withAppendedId(ObaContract.RegionBounds.CONTENT_URI, longId);
                return result;
            case REGION_OPEN311_SERVERS:
                longId = mRegionOpen311ServersInserter.insert(values);
                result = ContentUris.withAppendedId(ObaContract.RegionOpen311Servers.CONTENT_URI, longId);
                return result;
            case ROUTE_HEADSIGN_FAVORITES:
                id = values.getAsString(ObaContract.RouteHeadsignFavorites.ROUTE_ID);
                if (!ListenerUtil.mutListener.listen(8976)) {
                    if (id == null) {
                        throw new IllegalArgumentException("Need a route ID to insert! " + uri);
                    }
                }
                result = Uri.withAppendedPath(ObaContract.RouteHeadsignFavorites.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(8977)) {
                    mRouteHeadsignFavoritesInserter.insert(values);
                }
                return result;
            case NAV_STOPS:
                longId = mNavStopsInserter.insert(values);
                result = ContentUris.withAppendedId(ObaContract.NavStops.CONTENT_URI, longId);
                return result;
            // What would these mean, anyway??
            case STOPS_ID:
            case ROUTES_ID:
            case TRIPS_ID:
            case TRIP_ALERTS_ID:
            case SERVICE_ALERTS_ID:
            case REGIONS_ID:
            case REGION_BOUNDS_ID:
                throw new UnsupportedOperationException("Cannot insert to this URI: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    private Cursor queryInternal(SQLiteDatabase db, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = sUriMatcher.match(uri);
        final String limit = uri.getQueryParameter("limit");
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch(match) {
            case STOPS:
                if (!ListenerUtil.mutListener.listen(8978)) {
                    qb.setTables(ObaContract.Stops.PATH);
                }
                if (!ListenerUtil.mutListener.listen(8979)) {
                    qb.setProjectionMap(sStopsProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case STOPS_ID:
                if (!ListenerUtil.mutListener.listen(8980)) {
                    qb.setTables(ObaContract.Stops.PATH);
                }
                if (!ListenerUtil.mutListener.listen(8981)) {
                    qb.setProjectionMap(sStopsProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(8982)) {
                    qb.appendWhere(ObaContract.Stops._ID);
                }
                if (!ListenerUtil.mutListener.listen(8983)) {
                    qb.appendWhere("=");
                }
                if (!ListenerUtil.mutListener.listen(8984)) {
                    qb.appendWhereEscapeString(uri.getLastPathSegment());
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case ROUTES:
                if (!ListenerUtil.mutListener.listen(8985)) {
                    qb.setTables(ObaContract.Routes.PATH);
                }
                if (!ListenerUtil.mutListener.listen(8986)) {
                    qb.setProjectionMap(sRoutesProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case ROUTES_ID:
                if (!ListenerUtil.mutListener.listen(8987)) {
                    qb.setTables(ObaContract.Routes.PATH);
                }
                if (!ListenerUtil.mutListener.listen(8988)) {
                    qb.setProjectionMap(sRoutesProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(8989)) {
                    qb.appendWhere(ObaContract.Routes._ID);
                }
                if (!ListenerUtil.mutListener.listen(8990)) {
                    qb.appendWhere("=");
                }
                if (!ListenerUtil.mutListener.listen(8991)) {
                    qb.appendWhereEscapeString(uri.getLastPathSegment());
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case TRIPS:
                if (!ListenerUtil.mutListener.listen(8992)) {
                    qb.setTables(ObaContract.Trips.PATH);
                }
                if (!ListenerUtil.mutListener.listen(8993)) {
                    qb.setProjectionMap(sTripsProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case TRIPS_ID:
                if (!ListenerUtil.mutListener.listen(8994)) {
                    qb.setTables(ObaContract.Trips.PATH);
                }
                if (!ListenerUtil.mutListener.listen(8995)) {
                    qb.setProjectionMap(sTripsProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(8996)) {
                    qb.appendWhere(tripWhere(uri));
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case TRIP_ALERTS:
                if (!ListenerUtil.mutListener.listen(8997)) {
                    qb.setTables(ObaContract.TripAlerts.PATH);
                }
                if (!ListenerUtil.mutListener.listen(8998)) {
                    qb.setProjectionMap(sTripAlertsProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case TRIP_ALERTS_ID:
                if (!ListenerUtil.mutListener.listen(8999)) {
                    qb.setTables(ObaContract.TripAlerts.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9000)) {
                    qb.setProjectionMap(sTripAlertsProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(9001)) {
                    qb.appendWhere(ObaContract.TripAlerts._ID);
                }
                if (!ListenerUtil.mutListener.listen(9002)) {
                    qb.appendWhere("=");
                }
                if (!ListenerUtil.mutListener.listen(9003)) {
                    qb.appendWhere(String.valueOf(ContentUris.parseId(uri)));
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case STOP_ROUTE_FILTERS:
                if (!ListenerUtil.mutListener.listen(9004)) {
                    qb.setTables(ObaContract.StopRouteFilters.PATH);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case SERVICE_ALERTS:
                if (!ListenerUtil.mutListener.listen(9005)) {
                    qb.setTables(ObaContract.ServiceAlerts.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9006)) {
                    qb.setProjectionMap(sServiceAlertsProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case SERVICE_ALERTS_ID:
                if (!ListenerUtil.mutListener.listen(9007)) {
                    qb.setTables(ObaContract.ServiceAlerts.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9008)) {
                    qb.setProjectionMap(sServiceAlertsProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(9009)) {
                    qb.appendWhere(ObaContract.ServiceAlerts._ID);
                }
                if (!ListenerUtil.mutListener.listen(9010)) {
                    qb.appendWhere("=");
                }
                if (!ListenerUtil.mutListener.listen(9011)) {
                    qb.appendWhereEscapeString(uri.getLastPathSegment());
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case REGIONS:
                if (!ListenerUtil.mutListener.listen(9012)) {
                    qb.setTables(ObaContract.Regions.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9013)) {
                    qb.setProjectionMap(sRegionsProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case REGIONS_ID:
                if (!ListenerUtil.mutListener.listen(9014)) {
                    qb.setTables(ObaContract.Regions.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9015)) {
                    qb.setProjectionMap(sRegionsProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(9016)) {
                    qb.appendWhere(ObaContract.Regions._ID);
                }
                if (!ListenerUtil.mutListener.listen(9017)) {
                    qb.appendWhere("=");
                }
                if (!ListenerUtil.mutListener.listen(9018)) {
                    qb.appendWhere(String.valueOf(ContentUris.parseId(uri)));
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case REGION_BOUNDS:
                if (!ListenerUtil.mutListener.listen(9019)) {
                    qb.setTables(ObaContract.RegionBounds.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9020)) {
                    qb.setProjectionMap(sRegionBoundsProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case REGION_BOUNDS_ID:
                if (!ListenerUtil.mutListener.listen(9021)) {
                    qb.setTables(ObaContract.RegionBounds.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9022)) {
                    qb.setProjectionMap(sRegionBoundsProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(9023)) {
                    qb.appendWhere(ObaContract.RegionBounds._ID);
                }
                if (!ListenerUtil.mutListener.listen(9024)) {
                    qb.appendWhere("=");
                }
                if (!ListenerUtil.mutListener.listen(9025)) {
                    qb.appendWhere(String.valueOf(ContentUris.parseId(uri)));
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case REGION_OPEN311_SERVERS:
                if (!ListenerUtil.mutListener.listen(9026)) {
                    qb.setTables(ObaContract.RegionOpen311Servers.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9027)) {
                    qb.setProjectionMap(sRegionOpen311ProjectionMap);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case REGION_OPEN311_SERVERS_ID:
                if (!ListenerUtil.mutListener.listen(9028)) {
                    qb.setTables(ObaContract.RegionOpen311Servers.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9029)) {
                    qb.setProjectionMap(sRegionOpen311ProjectionMap);
                }
                if (!ListenerUtil.mutListener.listen(9030)) {
                    qb.appendWhere(ObaContract.RegionOpen311Servers._ID);
                }
                if (!ListenerUtil.mutListener.listen(9031)) {
                    qb.appendWhere("=");
                }
                if (!ListenerUtil.mutListener.listen(9032)) {
                    qb.appendWhere(String.valueOf(ContentUris.parseId(uri)));
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case ROUTE_HEADSIGN_FAVORITES:
                if (!ListenerUtil.mutListener.listen(9033)) {
                    qb.setTables(ObaContract.RouteHeadsignFavorites.PATH);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            case NAV_STOPS:
                if (!ListenerUtil.mutListener.listen(9034)) {
                    qb.setTables(ObaContract.NavStops.PATH);
                }
                return qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder, limit);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    private int updateInternal(SQLiteDatabase db, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case STOPS:
                return db.update(ObaContract.Stops.PATH, values, selection, selectionArgs);
            case STOPS_ID:
                return db.update(ObaContract.Stops.PATH, values, where(ObaContract.Stops._ID, uri), selectionArgs);
            case ROUTES:
                return db.update(ObaContract.Routes.PATH, values, selection, selectionArgs);
            case ROUTES_ID:
                return db.update(ObaContract.Routes.PATH, values, where(ObaContract.Routes._ID, uri), selectionArgs);
            case TRIPS:
                return db.update(ObaContract.Trips.PATH, values, selection, selectionArgs);
            case TRIPS_ID:
                return db.update(ObaContract.Trips.PATH, values, tripWhere(uri), selectionArgs);
            case TRIP_ALERTS:
                return db.update(ObaContract.TripAlerts.PATH, values, selection, selectionArgs);
            case TRIP_ALERTS_ID:
                return db.update(ObaContract.TripAlerts.PATH, values, whereLong(ObaContract.TripAlerts._ID, uri), selectionArgs);
            // Can we do anything here??
            case STOP_ROUTE_FILTERS:
                return 0;
            case SERVICE_ALERTS:
                return db.update(ObaContract.ServiceAlerts.PATH, values, selection, selectionArgs);
            case SERVICE_ALERTS_ID:
                return db.update(ObaContract.ServiceAlerts.PATH, values, where(ObaContract.ServiceAlerts._ID, uri), selectionArgs);
            case REGIONS:
                return db.update(ObaContract.Regions.PATH, values, selection, selectionArgs);
            case REGIONS_ID:
                return db.update(ObaContract.Regions.PATH, values, whereLong(ObaContract.Regions._ID, uri), selectionArgs);
            case REGION_BOUNDS:
                return db.update(ObaContract.RegionBounds.PATH, values, selection, selectionArgs);
            case REGION_BOUNDS_ID:
                return db.update(ObaContract.RegionBounds.PATH, values, whereLong(ObaContract.RegionBounds._ID, uri), selectionArgs);
            case REGION_OPEN311_SERVERS:
                return db.update(ObaContract.RegionOpen311Servers.PATH, values, selection, selectionArgs);
            case REGION_OPEN311_SERVERS_ID:
                return db.update(ObaContract.RegionOpen311Servers.PATH, values, whereLong(ObaContract.RegionOpen311Servers._ID, uri), selectionArgs);
            case ROUTE_HEADSIGN_FAVORITES:
                return 0;
            case NAV_STOPS:
                return db.update(ObaContract.NavStops.PATH, values, where(ObaContract.NavStops._ID, uri), selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    private int deleteInternal(SQLiteDatabase db, Uri uri, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case STOPS:
                return db.delete(ObaContract.Stops.PATH, selection, selectionArgs);
            case STOPS_ID:
                return db.delete(ObaContract.Stops.PATH, where(ObaContract.Stops._ID, uri), selectionArgs);
            case ROUTES:
                return db.delete(ObaContract.Routes.PATH, selection, selectionArgs);
            case ROUTES_ID:
                return db.delete(ObaContract.Routes.PATH, where(ObaContract.Routes._ID, uri), selectionArgs);
            case TRIPS:
                return db.delete(ObaContract.Trips.PATH, selection, selectionArgs);
            case TRIPS_ID:
                return db.delete(ObaContract.Trips.PATH, tripWhere(uri), selectionArgs);
            case TRIP_ALERTS:
                return db.delete(ObaContract.TripAlerts.PATH, selection, selectionArgs);
            case TRIP_ALERTS_ID:
                return db.delete(ObaContract.TripAlerts.PATH, whereLong(ObaContract.TripAlerts._ID, uri), selectionArgs);
            case STOP_ROUTE_FILTERS:
                return db.delete(ObaContract.StopRouteFilters.PATH, selection, selectionArgs);
            case SERVICE_ALERTS:
                return db.delete(ObaContract.ServiceAlerts.PATH, selection, selectionArgs);
            case SERVICE_ALERTS_ID:
                return db.delete(ObaContract.ServiceAlerts.PATH, where(ObaContract.ServiceAlerts._ID, uri), selectionArgs);
            case REGIONS:
                return db.delete(ObaContract.Regions.PATH, selection, selectionArgs);
            case REGIONS_ID:
                return db.delete(ObaContract.Regions.PATH, whereLong(ObaContract.Regions._ID, uri), selectionArgs);
            case REGION_BOUNDS:
                return db.delete(ObaContract.RegionBounds.PATH, selection, selectionArgs);
            case REGION_BOUNDS_ID:
                return db.delete(ObaContract.RegionBounds.PATH, whereLong(ObaContract.RegionBounds._ID, uri), selectionArgs);
            case REGION_OPEN311_SERVERS:
                return db.delete(ObaContract.RegionOpen311Servers.PATH, selection, selectionArgs);
            case REGION_OPEN311_SERVERS_ID:
                return db.delete(ObaContract.RegionOpen311Servers.PATH, whereLong(ObaContract.RegionOpen311Servers._ID, uri), selectionArgs);
            case ROUTE_HEADSIGN_FAVORITES:
                return db.delete(ObaContract.RouteHeadsignFavorites.PATH, selection, selectionArgs);
            case NAV_STOPS:
                return db.delete(ObaContract.NavStops.PATH, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    private String where(String column, Uri uri) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(9035)) {
            sb.append(column);
        }
        if (!ListenerUtil.mutListener.listen(9036)) {
            sb.append('=');
        }
        if (!ListenerUtil.mutListener.listen(9037)) {
            DatabaseUtils.appendValueToSql(sb, uri.getLastPathSegment());
        }
        return sb.toString();
    }

    private String whereLong(String column, Uri uri) {
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(9038)) {
            sb.append(column);
        }
        if (!ListenerUtil.mutListener.listen(9039)) {
            sb.append('=');
        }
        if (!ListenerUtil.mutListener.listen(9040)) {
            sb.append(String.valueOf(ContentUris.parseId(uri)));
        }
        return sb.toString();
    }

    private String tripWhere(Uri uri) {
        List<String> segments = uri.getPathSegments();
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(9041)) {
            sb.append("(");
        }
        if (!ListenerUtil.mutListener.listen(9042)) {
            sb.append(ObaContract.Trips._ID);
        }
        if (!ListenerUtil.mutListener.listen(9043)) {
            sb.append("=");
        }
        if (!ListenerUtil.mutListener.listen(9044)) {
            DatabaseUtils.appendValueToSql(sb, segments.get(1));
        }
        if (!ListenerUtil.mutListener.listen(9045)) {
            sb.append(" AND ");
        }
        if (!ListenerUtil.mutListener.listen(9046)) {
            sb.append(ObaContract.Trips.STOP_ID);
        }
        if (!ListenerUtil.mutListener.listen(9047)) {
            sb.append("=");
        }
        if (!ListenerUtil.mutListener.listen(9048)) {
            DatabaseUtils.appendValueToSql(sb, segments.get(2));
        }
        if (!ListenerUtil.mutListener.listen(9049)) {
            sb.append(")");
        }
        return sb.toString();
    }

    private SQLiteDatabase getDatabase() {
        if (!ListenerUtil.mutListener.listen(9062)) {
            if (mDb == null) {
                if (!ListenerUtil.mutListener.listen(9050)) {
                    mDb = mOpenHelper.getWritableDatabase();
                }
                if (!ListenerUtil.mutListener.listen(9051)) {
                    // Initialize the insert helpers
                    mStopsInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.Stops.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9052)) {
                    mRoutesInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.Routes.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9053)) {
                    mTripsInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.Trips.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9054)) {
                    mTripAlertsInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.TripAlerts.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9055)) {
                    mFilterInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.StopRouteFilters.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9056)) {
                    mServiceAlertsInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.ServiceAlerts.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9057)) {
                    mRegionsInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.Regions.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9058)) {
                    mRegionBoundsInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.RegionBounds.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9059)) {
                    mRegionOpen311ServersInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.RegionOpen311Servers.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9060)) {
                    mRouteHeadsignFavoritesInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.RouteHeadsignFavorites.PATH);
                }
                if (!ListenerUtil.mutListener.listen(9061)) {
                    mNavStopsInserter = new DatabaseUtils.InsertHelper(mDb, ObaContract.NavStops.PATH);
                }
            }
        }
        return mDb;
    }

    // 
    public void closeDB() {
        if (!ListenerUtil.mutListener.listen(9063)) {
            mOpenHelper.close();
        }
        if (!ListenerUtil.mutListener.listen(9064)) {
            mDb = null;
        }
    }
}
