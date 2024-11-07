/*
 * Copyright (C) 2010-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com),
 * Benjamin Du (bendu@me.com),
 * Microsoft Corporation.
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

import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.io.elements.ObaRegionElement;
import org.onebusaway.android.nav.model.PathLink;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * The contract between clients and the ObaProvider.
 *
 * This really needs to be documented better.
 *
 * NOTE: The AUTHORITY names in this class cannot be changed.  They need to stay under the
 * BuildConfig.DATABASE_AUTHORITY namespace (for the original OBA brand, "com.joulespersecond.oba")
 * namespace to support backwards compatibility with existing installed apps
 *
 * @author paulw
 */
public final class ObaContract {

    public static final String TAG = "ObaContract";

    /**
     * The authority portion of the URI - defined in build.gradle
     */
    public static final String AUTHORITY = BuildConfig.DATABASE_AUTHORITY;

    /**
     * The base URI for the Oba provider
     */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    protected interface StopsColumns {

        /**
         * The code for the stop, e.g. 001_123
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String CODE = "code";

        /**
         * The user specified name of the stop, e.g. "13th Ave E & John St"
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String NAME = "name";

        /**
         * The stop direction, one of: N, NE, NW, E, SE, SW, S, W or null
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String DIRECTION = "direction";

        /**
         * The latitude of the stop location.
         * <P>
         * Type: DOUBLE
         * </P>
         */
        public static final String LATITUDE = "latitude";

        /**
         * The longitude of the stop location.
         * <P>
         * Type: DOUBLE
         * </P>
         */
        public static final String LONGITUDE = "longitude";

        /**
         * The region ID
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String REGION_ID = "region_id";
    }

    protected interface RoutesColumns {

        /**
         * The short name of the route, e.g. "10"
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String SHORTNAME = "short_name";

        /**
         * The long name of the route, e.g "Downtown to U-District"
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String LONGNAME = "long_name";

        /**
         * Returns the URL of the route schedule.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String URL = "url";

        /**
         * The region ID
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String REGION_ID = "region_id";
    }

    protected interface StopRouteKeyColumns {

        /**
         * The referenced Stop ID. This may or may not represent a key in the
         * Stops table.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String STOP_ID = "stop_id";

        /**
         * The referenced Route ID. This may or may not represent a key in the
         * Routes table.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String ROUTE_ID = "route_id";
    }

    protected interface StopRouteFilterColumns extends StopRouteKeyColumns {
    }

    protected interface TripsColumns extends StopRouteKeyColumns {

        /**
         * The scheduled departure time for the trip in milliseconds.
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String DEPARTURE = "departure";

        /**
         * The headsign of the trip, e.g., "Capitol Hill"
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String HEADSIGN = "headsign";

        /**
         * The user specified name of the trip.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String NAME = "name";

        /**
         * The number of minutes before the arrival to notify the user
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String REMINDER = "reminder";

        /**
         * A bitmask representing the days the reminder should be used.
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String DAYS = "days";
    }

    protected interface TripAlertsColumns {

        /**
         * The trip_id key of the corresponding trip.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String TRIP_ID = "trip_id";

        /**
         * The stop_id key of the corresponding trip.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String STOP_ID = "stop_id";

        /**
         * The time in milliseconds to begin the polling. Unlike the "reminder"
         * time in the Trips columns, this represents a specific time.
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String START_TIME = "start_time";

        /**
         * The state of the the alert. Can be SCHEDULED, POLLING, NOTIFY,
         * CANCELED
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String STATE = "state";
    }

    protected interface UserColumns {

        /**
         * The number of times this resource has been accessed by the user.
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String USE_COUNT = "use_count";

        /**
         * The user specified name given to this resource.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String USER_NAME = "user_name";

        /**
         * The last time the user accessed the resource.
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String ACCESS_TIME = "access_time";

        /**
         * Whether or not the resource is marked as a favorite (starred)
         * <P>
         * Type: INTEGER (1 or 0)
         * </P>
         */
        public static final String FAVORITE = "favorite";

        /**
         * This returns the user specified name, or the default name.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String UI_NAME = "ui_name";
    }

    protected interface ServiceAlertsColumns {

        /**
         * The time it was marked as read.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String MARKED_READ_TIME = "marked_read_time";

        /**
         * Whether or not the alert has been hidden by the user.
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String HIDDEN = "hidden";
    }

    protected interface RegionsColumns {

        /**
         * The name of the region.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String NAME = "name";

        /**
         * The base OBA URL.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String OBA_BASE_URL = "oba_base_url";

        /**
         * The base SIRI URL.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String SIRI_BASE_URL = "siri_base_url";

        /**
         * The locale of the API server.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String LANGUAGE = "lang";

        /**
         * The email of the person responsible for this server.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String CONTACT_EMAIL = "contact_email";

        /**
         * Whether or not the server supports OBA discovery APIs.
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String SUPPORTS_OBA_DISCOVERY = "supports_api_discovery";

        /**
         * Whether or not the server supports OBA realtime APIs.
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String SUPPORTS_OBA_REALTIME = "supports_api_realtime";

        /**
         * Whether or not the server supports SIRI realtime APIs.
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String SUPPORTS_SIRI_REALTIME = "supports_siri_realtime";

        /**
         * The Twitter URL for the region.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String TWITTER_URL = "twitter_url";

        /**
         * Whether or not the server is experimental (i.e., not production).
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String EXPERIMENTAL = "experimental";

        /**
         * The StopInfo URL for the region (see #103)
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String STOP_INFO_URL = "stop_info_url";

        /**
         * The OpenTripPlanner URL for the region
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String OTP_BASE_URL = "otp_base_url";

        /**
         * The email of the person responsible for the OTP server.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String OTP_CONTACT_EMAIL = "otp_contact_email";

        /**
         * Whether or not the region supports bikeshare information through integration with OTP
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String SUPPORTS_OTP_BIKESHARE = "supports_otp_bikeshare";

        /**
         * Whether or not the server supports Embedded Social
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String SUPPORTS_EMBEDDED_SOCIAL = "supports_embedded_social";

        /**
         * The Android App ID for the payment app used in the region
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String PAYMENT_ANDROID_APP_ID = "payment_android_app_id";

        /**
         * The title of a warning dialog that should be shown to the user the first time they select the fare payment option, or empty if no warning should be shown to the user.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String PAYMENT_WARNING_TITLE = "payment_warning_title";

        /**
         * The body text of a warning dialog that should be shown to the user the first time they select the fare payment option, or empty if no warning should be shown to the user.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String PAYMENT_WARNING_BODY = "payment_warning_body";

        public static final String TRAVEL_BEHAVIOR_DATA_COLLECTION = "travel_behavior_data_collection";

        public static final String ENROLL_PARTICIPANTS_IN_STUDY = "enroll_participants_in_study";
    }

    protected interface RegionBoundsColumns {

        /**
         * The region ID
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String REGION_ID = "region_id";

        /**
         * The latitude center of the agencies coverage area
         * <P>
         * Type: REAL
         * </P>
         */
        public static final String LATITUDE = "lat";

        /**
         * The longitude center of the agencies coverage area
         * <P>
         * Type: REAL
         * </P>
         */
        public static final String LONGITUDE = "lon";

        /**
         * The height of the agencies bounding box
         * <P>
         * Type: REAL
         * </P>
         */
        public static final String LAT_SPAN = "lat_span";

        /**
         * The width of the agencies bounding box
         * <P>
         * Type: REAL
         * </P>
         */
        public static final String LON_SPAN = "lon_span";
    }

    protected interface RegionOpen311ServersColumns {

        /**
         * The region ID
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String REGION_ID = "region_id";

        /**
         * The jurisdiction id of the open311 server
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String JURISDICTION = "jurisdiction";

        /**
         * The api key of the open311 server
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String API_KEY = "api_key";

        /**
         * The url of the open311 server
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String BASE_URL = "open311_base_url";
    }

    protected interface RouteHeadsignKeyColumns extends StopRouteKeyColumns {

        /**
         * The referenced headsign. This may or may not represent a value in the
         * Trips table.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String HEADSIGN = "headsign";

        /**
         * Whether or not this stop should be excluded as a favorite.  This is to allow a user to
         * star a route/headsign for all stops, and then remove the star from selected stops.
         * <P>
         * Type: BOOLEAN
         * </P>
         */
        public static final String EXCLUDE = "exclude";
    }

    protected interface NavigationColumns {

        /**
         * The Navigation ID for destination reminder
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String NAV_ID = "nav_id";

        /**
         * The start time for the navigation of this path link
         * <P>
         * Type: INTEGER
         * </P>
         */
        public static final String START_TIME = "start_time";

        /**
         * The Trip Id
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String TRIP_ID = "trip_id";

        /**
         * The Stop ID of the destination.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String DESTINATION_ID = "destination_id";

        /**
         * The Stop ID of the stop before the user's destination.
         * <P>
         * Type: TEXT
         * </P>
         */
        public static final String BEFORE_ID = "before_id";

        /**
         * Sequence Number of the Segment.
         * <P>
         * Type: Integer
         * </P>
         */
        public static final String SEQUENCE = "seq_num";

        /**
         * Whether this leg of the trip is still active.
         * <P>
         * Type: Integer (1 or 0)
         * </P>
         */
        public static final String ACTIVE = "is_active";
    }

    public static class Stops implements BaseColumns, StopsColumns, UserColumns {

        // Cannot be instantiated
        private Stops() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "stops";

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".stop";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".stop";

        public static Uri insertOrUpdate(String id, ContentValues values, boolean markAsUsed) {
            ContentResolver cr = Application.get().getContentResolver();
            final Uri uri = Uri.withAppendedPath(CONTENT_URI, id);
            Cursor c = cr.query(uri, new String[] { USE_COUNT }, null, null, null);
            Uri result;
            if ((ListenerUtil.mutListener.listen(9070) ? (c != null || (ListenerUtil.mutListener.listen(9069) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9068) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9067) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9066) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9065) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(9069) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9068) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9067) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9066) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9065) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9083)) {
                    // Update
                    if (markAsUsed) {
                        if (!ListenerUtil.mutListener.listen(9076)) {
                            c.moveToFirst();
                        }
                        int count = c.getInt(0);
                        if (!ListenerUtil.mutListener.listen(9081)) {
                            values.put(USE_COUNT, (ListenerUtil.mutListener.listen(9080) ? (count % 1) : (ListenerUtil.mutListener.listen(9079) ? (count / 1) : (ListenerUtil.mutListener.listen(9078) ? (count * 1) : (ListenerUtil.mutListener.listen(9077) ? (count - 1) : (count + 1))))));
                        }
                        if (!ListenerUtil.mutListener.listen(9082)) {
                            values.put(ACCESS_TIME, System.currentTimeMillis());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9084)) {
                    cr.update(uri, values, null, null);
                }
                result = uri;
            } else {
                if (!ListenerUtil.mutListener.listen(9074)) {
                    // Insert
                    if (markAsUsed) {
                        if (!ListenerUtil.mutListener.listen(9072)) {
                            values.put(USE_COUNT, 1);
                        }
                        if (!ListenerUtil.mutListener.listen(9073)) {
                            values.put(ACCESS_TIME, System.currentTimeMillis());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9071)) {
                            values.put(USE_COUNT, 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9075)) {
                    values.put(_ID, id);
                }
                result = cr.insert(CONTENT_URI, values);
            }
            if (!ListenerUtil.mutListener.listen(9086)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9085)) {
                        c.close();
                    }
                }
            }
            return result;
        }

        public static boolean isFavorite(Context context, String stopId) {
            final String[] PROJECTION = { FAVORITE };
            ContentResolver cr = context.getContentResolver();
            Cursor c = cr.query(CONTENT_URI, PROJECTION, _ID + "=?", new String[] { stopId }, null);
            if (!ListenerUtil.mutListener.listen(9090)) {
                if (c != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(9088)) {
                            if (c.getCount() == 0) {
                                return false;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9089)) {
                            c.moveToFirst();
                        }
                        return c.getInt(0) == 1;
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9087)) {
                            c.close();
                        }
                    }
                }
            }
            return false;
        }

        public static boolean markAsFavorite(Context context, Uri uri, boolean favorite) {
            ContentResolver cr = context.getContentResolver();
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9091)) {
                values.put(ObaContract.Stops.FAVORITE, favorite ? 1 : 0);
            }
            return (ListenerUtil.mutListener.listen(9096) ? (cr.update(uri, values, null, null) >= 0) : (ListenerUtil.mutListener.listen(9095) ? (cr.update(uri, values, null, null) <= 0) : (ListenerUtil.mutListener.listen(9094) ? (cr.update(uri, values, null, null) < 0) : (ListenerUtil.mutListener.listen(9093) ? (cr.update(uri, values, null, null) != 0) : (ListenerUtil.mutListener.listen(9092) ? (cr.update(uri, values, null, null) == 0) : (cr.update(uri, values, null, null) > 0))))));
        }

        public static boolean markAsUnused(Context context, Uri uri) {
            ContentResolver cr = context.getContentResolver();
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9097)) {
                values.put(ObaContract.Stops.USE_COUNT, 0);
            }
            if (!ListenerUtil.mutListener.listen(9098)) {
                values.putNull(ObaContract.Stops.ACCESS_TIME);
            }
            return (ListenerUtil.mutListener.listen(9103) ? (cr.update(uri, values, null, null) >= 0) : (ListenerUtil.mutListener.listen(9102) ? (cr.update(uri, values, null, null) <= 0) : (ListenerUtil.mutListener.listen(9101) ? (cr.update(uri, values, null, null) < 0) : (ListenerUtil.mutListener.listen(9100) ? (cr.update(uri, values, null, null) != 0) : (ListenerUtil.mutListener.listen(9099) ? (cr.update(uri, values, null, null) == 0) : (cr.update(uri, values, null, null) > 0))))));
        }

        public static Location getLocation(Context context, String id) {
            return getLocation(context.getContentResolver(), id);
        }

        private static Location getLocation(ContentResolver cr, String id) {
            final String[] PROJECTION = { LATITUDE, LONGITUDE };
            Cursor c = cr.query(CONTENT_URI, PROJECTION, _ID + "=?", new String[] { id }, null);
            if (!ListenerUtil.mutListener.listen(9109)) {
                if (c != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(9105)) {
                            if (c.getCount() == 0) {
                                return null;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9106)) {
                            c.moveToFirst();
                        }
                        Location l = new Location(LocationManager.GPS_PROVIDER);
                        if (!ListenerUtil.mutListener.listen(9107)) {
                            l.setLatitude(c.getDouble(0));
                        }
                        if (!ListenerUtil.mutListener.listen(9108)) {
                            l.setLongitude(c.getDouble(1));
                        }
                        return l;
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9104)) {
                            c.close();
                        }
                    }
                }
            }
            return null;
        }
    }

    public static class Routes implements BaseColumns, RoutesColumns, UserColumns {

        // Cannot be instantiated
        private Routes() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "routes";

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".route";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".route";

        public static Uri insertOrUpdate(Context context, String id, ContentValues values, boolean markAsUsed) {
            ContentResolver cr = context.getContentResolver();
            final Uri uri = Uri.withAppendedPath(CONTENT_URI, id);
            Cursor c = cr.query(uri, new String[] { USE_COUNT }, null, null, null);
            Uri result;
            if ((ListenerUtil.mutListener.listen(9115) ? (c != null || (ListenerUtil.mutListener.listen(9114) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9113) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9112) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9111) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9110) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(9114) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9113) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9112) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9111) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9110) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9128)) {
                    // Update
                    if (markAsUsed) {
                        if (!ListenerUtil.mutListener.listen(9121)) {
                            c.moveToFirst();
                        }
                        int count = c.getInt(0);
                        if (!ListenerUtil.mutListener.listen(9126)) {
                            values.put(USE_COUNT, (ListenerUtil.mutListener.listen(9125) ? (count % 1) : (ListenerUtil.mutListener.listen(9124) ? (count / 1) : (ListenerUtil.mutListener.listen(9123) ? (count * 1) : (ListenerUtil.mutListener.listen(9122) ? (count - 1) : (count + 1))))));
                        }
                        if (!ListenerUtil.mutListener.listen(9127)) {
                            values.put(ACCESS_TIME, System.currentTimeMillis());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9129)) {
                    cr.update(uri, values, null, null);
                }
                result = uri;
            } else {
                if (!ListenerUtil.mutListener.listen(9119)) {
                    // Insert
                    if (markAsUsed) {
                        if (!ListenerUtil.mutListener.listen(9117)) {
                            values.put(USE_COUNT, 1);
                        }
                        if (!ListenerUtil.mutListener.listen(9118)) {
                            values.put(ACCESS_TIME, System.currentTimeMillis());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9116)) {
                            values.put(USE_COUNT, 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9120)) {
                    values.put(_ID, id);
                }
                result = cr.insert(CONTENT_URI, values);
            }
            if (!ListenerUtil.mutListener.listen(9131)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9130)) {
                        c.close();
                    }
                }
            }
            return result;
        }

        public static boolean markAsFavorite(Context context, Uri uri, boolean favorite) {
            ContentResolver cr = context.getContentResolver();
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9132)) {
                values.put(ObaContract.Routes.FAVORITE, favorite ? 1 : 0);
            }
            return (ListenerUtil.mutListener.listen(9137) ? (cr.update(uri, values, null, null) >= 0) : (ListenerUtil.mutListener.listen(9136) ? (cr.update(uri, values, null, null) <= 0) : (ListenerUtil.mutListener.listen(9135) ? (cr.update(uri, values, null, null) < 0) : (ListenerUtil.mutListener.listen(9134) ? (cr.update(uri, values, null, null) != 0) : (ListenerUtil.mutListener.listen(9133) ? (cr.update(uri, values, null, null) == 0) : (cr.update(uri, values, null, null) > 0))))));
        }

        public static boolean markAsUnused(Context context, Uri uri) {
            ContentResolver cr = context.getContentResolver();
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9138)) {
                values.put(ObaContract.Routes.USE_COUNT, 0);
            }
            if (!ListenerUtil.mutListener.listen(9139)) {
                values.putNull(ObaContract.Routes.ACCESS_TIME);
            }
            return (ListenerUtil.mutListener.listen(9144) ? (cr.update(uri, values, null, null) >= 0) : (ListenerUtil.mutListener.listen(9143) ? (cr.update(uri, values, null, null) <= 0) : (ListenerUtil.mutListener.listen(9142) ? (cr.update(uri, values, null, null) < 0) : (ListenerUtil.mutListener.listen(9141) ? (cr.update(uri, values, null, null) != 0) : (ListenerUtil.mutListener.listen(9140) ? (cr.update(uri, values, null, null) == 0) : (cr.update(uri, values, null, null) > 0))))));
        }

        /**
         * Returns true if this route is a favorite, false if it does not
         *
         * Note that this is NOT specific to headsign.  If you want to know if a combination of a
         * routeId and headsign is a user favorite, see
         * RouteHeadsignFavorites.isFavorite(context, routeId, headsign).
         *
         * @param routeUri Uri for a route
         * @return true if this route is a favorite, false if it does not
         */
        public static boolean isFavorite(Context context, Uri routeUri) {
            ContentResolver cr = context.getContentResolver();
            String[] ROUTE_USER_PROJECTION = { ObaContract.Routes.FAVORITE };
            Cursor c = cr.query(routeUri, ROUTE_USER_PROJECTION, null, null, null);
            if (!ListenerUtil.mutListener.listen(9147)) {
                if (c != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(9146)) {
                            if (c.moveToNext()) {
                                return (c.getInt(0) == 1);
                            }
                        }
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9145)) {
                            c.close();
                        }
                    }
                }
            }
            // If we get this far, assume its not
            return false;
        }
    }

    public static class StopRouteFilters implements StopRouteFilterColumns {

        // Cannot be instantiated
        private StopRouteFilters() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "stop_routes_filter";

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".stoproutefilter";

        private static final String FILTER_WHERE = STOP_ID + "=?";

        /**
         * Gets the filter for the specified Stop ID.
         *
         * @param context The context.
         * @param stopId  The stop ID.
         * @return The filter. If there is no filter (or on error), it returns
         * an empty list.
         */
        public static ArrayList<String> get(Context context, String stopId) {
            final String[] selection = { ROUTE_ID };
            final String[] selectionArgs = { stopId };
            ContentResolver cr = context.getContentResolver();
            Cursor c = cr.query(CONTENT_URI, selection, FILTER_WHERE, selectionArgs, null);
            ArrayList<String> result = new ArrayList<String>();
            if (!ListenerUtil.mutListener.listen(9151)) {
                if (c != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(9150)) {
                            {
                                long _loopCounter114 = 0;
                                while (c.moveToNext()) {
                                    ListenerUtil.loopListener.listen("_loopCounter114", ++_loopCounter114);
                                    if (!ListenerUtil.mutListener.listen(9149)) {
                                        result.add(c.getString(0));
                                    }
                                }
                            }
                        }
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9148)) {
                            c.close();
                        }
                    }
                }
            }
            return result;
        }

        /**
         * Sets the filter for the particular stop ID.
         *
         * @param context The context.
         * @param stopId  The stop ID.
         * @param filter  An array of route IDs to filter.
         */
        public static void set(Context context, String stopId, ArrayList<String> filter) {
            if (!ListenerUtil.mutListener.listen(9152)) {
                if (context == null) {
                    return;
                }
            }
            // but it's not terribly important.
            final String[] selectionArgs = { stopId };
            ContentResolver cr = context.getContentResolver();
            if (!ListenerUtil.mutListener.listen(9153)) {
                cr.delete(CONTENT_URI, FILTER_WHERE, selectionArgs);
            }
            ContentValues args = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9154)) {
                args.put(STOP_ID, stopId);
            }
            final int len = filter.size();
            if (!ListenerUtil.mutListener.listen(9162)) {
                {
                    long _loopCounter115 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(9161) ? (i >= len) : (ListenerUtil.mutListener.listen(9160) ? (i <= len) : (ListenerUtil.mutListener.listen(9159) ? (i > len) : (ListenerUtil.mutListener.listen(9158) ? (i != len) : (ListenerUtil.mutListener.listen(9157) ? (i == len) : (i < len)))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter115", ++_loopCounter115);
                        if (!ListenerUtil.mutListener.listen(9155)) {
                            args.put(ROUTE_ID, filter.get(i));
                        }
                        if (!ListenerUtil.mutListener.listen(9156)) {
                            cr.insert(CONTENT_URI, args);
                        }
                    }
                }
            }
        }
    }

    public static class Trips implements BaseColumns, StopRouteKeyColumns, TripsColumns {

        // Cannot be instantiated
        private Trips() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "trips";

        /**
         * The content:// style URI for this table URI is of the form
         * content://<authority>/trips/<tripId>/<stopId>
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".trip";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".trip";

        public static final int DAY_MON = 0x1;

        public static final int DAY_TUE = 0x2;

        public static final int DAY_WED = 0x4;

        public static final int DAY_THU = 0x8;

        public static final int DAY_FRI = 0x10;

        public static final int DAY_SAT = 0x20;

        public static final int DAY_SUN = 0x40;

        public static final int DAY_WEEKDAY = DAY_MON | DAY_TUE | DAY_WED | DAY_THU | DAY_FRI;

        public static final int DAY_ALL = DAY_WEEKDAY | DAY_SUN | DAY_SAT;

        public static Uri buildUri(String tripId, String stopId) {
            return CONTENT_URI.buildUpon().appendPath(tripId).appendPath(stopId).build();
        }

        /**
         * Converts a days bitmask into a boolean[] array
         *
         * @param days A DB compatible days bitmask.
         * @return A boolean array representing the days set in the bitmask,
         * Mon=0 to Sun=6
         */
        public static boolean[] daysToArray(int days) {
            final boolean[] result = { (ListenerUtil.mutListener.listen(9167) ? ((days & ObaContract.Trips.DAY_MON) >= ObaContract.Trips.DAY_MON) : (ListenerUtil.mutListener.listen(9166) ? ((days & ObaContract.Trips.DAY_MON) <= ObaContract.Trips.DAY_MON) : (ListenerUtil.mutListener.listen(9165) ? ((days & ObaContract.Trips.DAY_MON) > ObaContract.Trips.DAY_MON) : (ListenerUtil.mutListener.listen(9164) ? ((days & ObaContract.Trips.DAY_MON) < ObaContract.Trips.DAY_MON) : (ListenerUtil.mutListener.listen(9163) ? ((days & ObaContract.Trips.DAY_MON) != ObaContract.Trips.DAY_MON) : ((days & ObaContract.Trips.DAY_MON) == ObaContract.Trips.DAY_MON)))))), (ListenerUtil.mutListener.listen(9172) ? ((days & ObaContract.Trips.DAY_TUE) >= ObaContract.Trips.DAY_TUE) : (ListenerUtil.mutListener.listen(9171) ? ((days & ObaContract.Trips.DAY_TUE) <= ObaContract.Trips.DAY_TUE) : (ListenerUtil.mutListener.listen(9170) ? ((days & ObaContract.Trips.DAY_TUE) > ObaContract.Trips.DAY_TUE) : (ListenerUtil.mutListener.listen(9169) ? ((days & ObaContract.Trips.DAY_TUE) < ObaContract.Trips.DAY_TUE) : (ListenerUtil.mutListener.listen(9168) ? ((days & ObaContract.Trips.DAY_TUE) != ObaContract.Trips.DAY_TUE) : ((days & ObaContract.Trips.DAY_TUE) == ObaContract.Trips.DAY_TUE)))))), (ListenerUtil.mutListener.listen(9177) ? ((days & ObaContract.Trips.DAY_WED) >= ObaContract.Trips.DAY_WED) : (ListenerUtil.mutListener.listen(9176) ? ((days & ObaContract.Trips.DAY_WED) <= ObaContract.Trips.DAY_WED) : (ListenerUtil.mutListener.listen(9175) ? ((days & ObaContract.Trips.DAY_WED) > ObaContract.Trips.DAY_WED) : (ListenerUtil.mutListener.listen(9174) ? ((days & ObaContract.Trips.DAY_WED) < ObaContract.Trips.DAY_WED) : (ListenerUtil.mutListener.listen(9173) ? ((days & ObaContract.Trips.DAY_WED) != ObaContract.Trips.DAY_WED) : ((days & ObaContract.Trips.DAY_WED) == ObaContract.Trips.DAY_WED)))))), (ListenerUtil.mutListener.listen(9182) ? ((days & ObaContract.Trips.DAY_THU) >= ObaContract.Trips.DAY_THU) : (ListenerUtil.mutListener.listen(9181) ? ((days & ObaContract.Trips.DAY_THU) <= ObaContract.Trips.DAY_THU) : (ListenerUtil.mutListener.listen(9180) ? ((days & ObaContract.Trips.DAY_THU) > ObaContract.Trips.DAY_THU) : (ListenerUtil.mutListener.listen(9179) ? ((days & ObaContract.Trips.DAY_THU) < ObaContract.Trips.DAY_THU) : (ListenerUtil.mutListener.listen(9178) ? ((days & ObaContract.Trips.DAY_THU) != ObaContract.Trips.DAY_THU) : ((days & ObaContract.Trips.DAY_THU) == ObaContract.Trips.DAY_THU)))))), (ListenerUtil.mutListener.listen(9187) ? ((days & ObaContract.Trips.DAY_FRI) >= ObaContract.Trips.DAY_FRI) : (ListenerUtil.mutListener.listen(9186) ? ((days & ObaContract.Trips.DAY_FRI) <= ObaContract.Trips.DAY_FRI) : (ListenerUtil.mutListener.listen(9185) ? ((days & ObaContract.Trips.DAY_FRI) > ObaContract.Trips.DAY_FRI) : (ListenerUtil.mutListener.listen(9184) ? ((days & ObaContract.Trips.DAY_FRI) < ObaContract.Trips.DAY_FRI) : (ListenerUtil.mutListener.listen(9183) ? ((days & ObaContract.Trips.DAY_FRI) != ObaContract.Trips.DAY_FRI) : ((days & ObaContract.Trips.DAY_FRI) == ObaContract.Trips.DAY_FRI)))))), (ListenerUtil.mutListener.listen(9192) ? ((days & ObaContract.Trips.DAY_SAT) >= ObaContract.Trips.DAY_SAT) : (ListenerUtil.mutListener.listen(9191) ? ((days & ObaContract.Trips.DAY_SAT) <= ObaContract.Trips.DAY_SAT) : (ListenerUtil.mutListener.listen(9190) ? ((days & ObaContract.Trips.DAY_SAT) > ObaContract.Trips.DAY_SAT) : (ListenerUtil.mutListener.listen(9189) ? ((days & ObaContract.Trips.DAY_SAT) < ObaContract.Trips.DAY_SAT) : (ListenerUtil.mutListener.listen(9188) ? ((days & ObaContract.Trips.DAY_SAT) != ObaContract.Trips.DAY_SAT) : ((days & ObaContract.Trips.DAY_SAT) == ObaContract.Trips.DAY_SAT)))))), (ListenerUtil.mutListener.listen(9197) ? ((days & ObaContract.Trips.DAY_SUN) >= ObaContract.Trips.DAY_SUN) : (ListenerUtil.mutListener.listen(9196) ? ((days & ObaContract.Trips.DAY_SUN) <= ObaContract.Trips.DAY_SUN) : (ListenerUtil.mutListener.listen(9195) ? ((days & ObaContract.Trips.DAY_SUN) > ObaContract.Trips.DAY_SUN) : (ListenerUtil.mutListener.listen(9194) ? ((days & ObaContract.Trips.DAY_SUN) < ObaContract.Trips.DAY_SUN) : (ListenerUtil.mutListener.listen(9193) ? ((days & ObaContract.Trips.DAY_SUN) != ObaContract.Trips.DAY_SUN) : ((days & ObaContract.Trips.DAY_SUN) == ObaContract.Trips.DAY_SUN)))))) };
            return result;
        }

        /**
         * Converts a boolean[] array to a DB compatible days bitmask
         *
         * @param days boolean array as returned by daysToArray
         * @return A DB compatible days bitmask
         */
        public static int arrayToDays(boolean[] days) {
            if (!ListenerUtil.mutListener.listen(9203)) {
                if ((ListenerUtil.mutListener.listen(9202) ? (days.length >= 7) : (ListenerUtil.mutListener.listen(9201) ? (days.length <= 7) : (ListenerUtil.mutListener.listen(9200) ? (days.length > 7) : (ListenerUtil.mutListener.listen(9199) ? (days.length < 7) : (ListenerUtil.mutListener.listen(9198) ? (days.length == 7) : (days.length != 7))))))) {
                    throw new IllegalArgumentException("days.length must be 7");
                }
            }
            int result = 0;
            if (!ListenerUtil.mutListener.listen(9210)) {
                {
                    long _loopCounter116 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(9209) ? (i >= days.length) : (ListenerUtil.mutListener.listen(9208) ? (i <= days.length) : (ListenerUtil.mutListener.listen(9207) ? (i > days.length) : (ListenerUtil.mutListener.listen(9206) ? (i != days.length) : (ListenerUtil.mutListener.listen(9205) ? (i == days.length) : (i < days.length)))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter116", ++_loopCounter116);
                        final int bit = days[i] ? 1 : 0;
                        if (!ListenerUtil.mutListener.listen(9204)) {
                            result |= bit << i;
                        }
                    }
                }
            }
            return result;
        }

        // Helper functions to convert the DB DepartureTime value
        public static long convertDBToTime(int minutes) {
            // day.
            Time t = new Time();
            if (!ListenerUtil.mutListener.listen(9211)) {
                t.setToNow();
            }
            if (!ListenerUtil.mutListener.listen(9212)) {
                t.set(0, minutes, 0, t.monthDay, t.month, t.year);
            }
            return t.toMillis(false);
        }

        /**
         * Converts a Unix time into a 'minutes-to-midnight' in UTC.
         *
         * @param departureTime A Unix time.
         * @return minutes from midnight in UTC.
         */
        public static int convertTimeToDB(long departureTime) {
            // This converts a time_t to minutes-to-midnight.
            Time t = new Time();
            if (!ListenerUtil.mutListener.listen(9213)) {
                t.set(departureTime);
            }
            return (ListenerUtil.mutListener.listen(9217) ? (t.hour % 60) : (ListenerUtil.mutListener.listen(9216) ? (t.hour / 60) : (ListenerUtil.mutListener.listen(9215) ? (t.hour - 60) : (ListenerUtil.mutListener.listen(9214) ? (t.hour + 60) : (t.hour * 60))))) + t.minute;
        }

        /**
         * Converts a weekday value from a android.text.format.Time to a bit.
         *
         * @param weekday The weekDay value from android.text.format.Time
         * @return A DB compatible bit.
         */
        public static int getDayBit(int weekday) {
            if (!ListenerUtil.mutListener.listen(9218)) {
                switch(weekday) {
                    case Time.MONDAY:
                        return ObaContract.Trips.DAY_MON;
                    case Time.TUESDAY:
                        return ObaContract.Trips.DAY_TUE;
                    case Time.WEDNESDAY:
                        return ObaContract.Trips.DAY_WED;
                    case Time.THURSDAY:
                        return ObaContract.Trips.DAY_THU;
                    case Time.FRIDAY:
                        return ObaContract.Trips.DAY_FRI;
                    case Time.SATURDAY:
                        return ObaContract.Trips.DAY_SAT;
                    case Time.SUNDAY:
                        return ObaContract.Trips.DAY_SUN;
                }
            }
            return 0;
        }
    }

    public static class TripAlerts implements BaseColumns, TripAlertsColumns {

        // Cannot be instantiated
        private TripAlerts() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "trip_alerts";

        /**
         * The content:// style URI for this table URI is of the form
         * content://<authority>/trip_alerts/<id>
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".trip_alert";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".trip_alert";

        public static final int STATE_SCHEDULED = 0;

        public static final int STATE_POLLING = 1;

        public static final int STATE_NOTIFY = 2;

        public static final int STATE_CANCELLED = 3;

        public static Uri buildUri(int id) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
        }

        public static Uri insertIfNotExists(Context context, String tripId, String stopId, long startTime) {
            return insertIfNotExists(context.getContentResolver(), tripId, stopId, startTime);
        }

        public static Uri insertIfNotExists(ContentResolver cr, String tripId, String stopId, long startTime) {
            Uri result;
            Cursor c = cr.query(CONTENT_URI, new String[] { _ID }, String.format("%s=? AND %s=? AND %s=?", TRIP_ID, STOP_ID, START_TIME), new String[] { tripId, stopId, String.valueOf(startTime) }, null);
            if ((ListenerUtil.mutListener.listen(9219) ? (c != null || c.moveToNext()) : (c != null && c.moveToNext()))) {
                result = buildUri(c.getInt(0));
            } else {
                ContentValues values = new ContentValues();
                if (!ListenerUtil.mutListener.listen(9220)) {
                    values.put(TRIP_ID, tripId);
                }
                if (!ListenerUtil.mutListener.listen(9221)) {
                    values.put(STOP_ID, stopId);
                }
                if (!ListenerUtil.mutListener.listen(9222)) {
                    values.put(START_TIME, startTime);
                }
                result = cr.insert(CONTENT_URI, values);
            }
            if (!ListenerUtil.mutListener.listen(9224)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9223)) {
                        c.close();
                    }
                }
            }
            return result;
        }

        public static void setState(Context context, Uri uri, int state) {
            if (!ListenerUtil.mutListener.listen(9225)) {
                setState(context.getContentResolver(), uri, state);
            }
        }

        public static void setState(ContentResolver cr, Uri uri, int state) {
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9226)) {
                values.put(STATE, state);
            }
            if (!ListenerUtil.mutListener.listen(9227)) {
                cr.update(uri, values, null, null);
            }
        }
    }

    public static class ServiceAlerts implements BaseColumns, ServiceAlertsColumns {

        // Cannot be instantiated
        private ServiceAlerts() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "service_alerts";

        /**
         * The content:// style URI for this table URI is of the form
         * content://<authority>/service_alerts/<id>
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".service_alert";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".service_alert";

        /**
         * @param markAsRead true if this alert should be marked as read with the timestamp of
         *                   System.currentTimeMillis(),
         *                   false if the alert should not be marked as read with the timestamp of
         *                   System.currentTimeMillis()
         * @param hidden  true if this alert should be marked as hidden by the user, false if
         *                   it should be marked as not
         *                   hidden by the user, or null if the hidden value shouldn't be
         *                   changed
         */
        public static Uri insertOrUpdate(String id, ContentValues values, boolean markAsRead, Boolean hidden) {
            if (!ListenerUtil.mutListener.listen(9228)) {
                if (id == null) {
                    return null;
                }
            }
            if (!ListenerUtil.mutListener.listen(9230)) {
                if (values == null) {
                    if (!ListenerUtil.mutListener.listen(9229)) {
                        values = new ContentValues();
                    }
                }
            }
            ContentResolver cr = Application.get().getContentResolver();
            final Uri uri = Uri.withAppendedPath(CONTENT_URI, id);
            Cursor c = cr.query(uri, new String[] {}, null, null, null);
            Uri result;
            if ((ListenerUtil.mutListener.listen(9236) ? (c != null || (ListenerUtil.mutListener.listen(9235) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9234) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9233) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9232) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9231) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(9235) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9234) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9233) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9232) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9231) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9249)) {
                    // Update
                    if (markAsRead) {
                        if (!ListenerUtil.mutListener.listen(9247)) {
                            c.moveToFirst();
                        }
                        if (!ListenerUtil.mutListener.listen(9248)) {
                            values.put(MARKED_READ_TIME, System.currentTimeMillis());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9254)) {
                    if (hidden != null) {
                        if (!ListenerUtil.mutListener.listen(9250)) {
                            c.moveToFirst();
                        }
                        if (!ListenerUtil.mutListener.listen(9253)) {
                            if (hidden) {
                                if (!ListenerUtil.mutListener.listen(9252)) {
                                    values.put(HIDDEN, 1);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9251)) {
                                    values.put(HIDDEN, 0);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9256)) {
                    if (values.size() != 0) {
                        if (!ListenerUtil.mutListener.listen(9255)) {
                            cr.update(uri, values, null, null);
                        }
                    }
                }
                result = uri;
            } else {
                if (!ListenerUtil.mutListener.listen(9238)) {
                    // Insert
                    if (markAsRead) {
                        if (!ListenerUtil.mutListener.listen(9237)) {
                            values.put(MARKED_READ_TIME, System.currentTimeMillis());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9241)) {
                    if (hidden == null) {
                        // If the user has selected to hide all alerts by default, mark this one as hidden when inserting
                        boolean hideAllAlerts = Application.getPrefs().getBoolean(Application.get().getResources().getString(R.string.preference_key_hide_alerts), false);
                        if (!ListenerUtil.mutListener.listen(9240)) {
                            if (hideAllAlerts) {
                                if (!ListenerUtil.mutListener.listen(9239)) {
                                    hidden = true;
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9245)) {
                    if (hidden != null) {
                        if (!ListenerUtil.mutListener.listen(9244)) {
                            if (hidden) {
                                if (!ListenerUtil.mutListener.listen(9243)) {
                                    values.put(HIDDEN, 1);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(9242)) {
                                    values.put(HIDDEN, 0);
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(9246)) {
                    values.put(_ID, id);
                }
                result = cr.insert(CONTENT_URI, values);
            }
            if (!ListenerUtil.mutListener.listen(9258)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9257)) {
                        c.close();
                    }
                }
            }
            return result;
        }

        /**
         * Returns true if this service alert (situation) has been previously hidden by the
         * user, false it if has not
         *
         * @param situationId The ID of the situation (service alert)
         * @return true if this service alert (situation) has been previously hidden by the user,
         * false it if has not
         */
        public static boolean isHidden(String situationId) {
            final String[] selection = { _ID, HIDDEN };
            final String[] selectionArgs = { situationId, Integer.toString(1) };
            final String WHERE = _ID + "=? AND " + HIDDEN + "=?";
            ContentResolver cr = Application.get().getContentResolver();
            Cursor c = cr.query(CONTENT_URI, selection, WHERE, selectionArgs, null);
            boolean hidden;
            if ((ListenerUtil.mutListener.listen(9264) ? (c != null || (ListenerUtil.mutListener.listen(9263) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9262) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9261) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9260) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9259) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(9263) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9262) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9261) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9260) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9259) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                hidden = true;
            } else {
                hidden = false;
            }
            if (!ListenerUtil.mutListener.listen(9266)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9265)) {
                        c.close();
                    }
                }
            }
            return hidden;
        }

        /**
         * Marks all alerts as not hidden, and therefore visible
         *
         * @return the number of rows updated
         */
        public static int showAllAlerts() {
            ContentResolver cr = Application.get().getContentResolver();
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9267)) {
                values.put(HIDDEN, 0);
            }
            return cr.update(CONTENT_URI, values, null, null);
        }

        /**
         * Marks all alerts as hidden, and therefore not visible
         *
         * @return the number of rows updated
         */
        public static int hideAllAlerts() {
            ContentResolver cr = Application.get().getContentResolver();
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9268)) {
                values.put(HIDDEN, 1);
            }
            return cr.update(CONTENT_URI, values, null, null);
        }
    }

    public static class Regions implements BaseColumns, RegionsColumns {

        // Cannot be instantiated
        private Regions() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "regions";

        /**
         * The content:// style URI for this table URI is of the form
         * content://<authority>/regions/<id>
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".region";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".region";

        public static Uri buildUri(int id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri insertOrUpdate(Context context, int id, ContentValues values) {
            return insertOrUpdate(context.getContentResolver(), id, values);
        }

        public static Uri insertOrUpdate(ContentResolver cr, int id, ContentValues values) {
            final Uri uri = Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
            Cursor c = cr.query(uri, new String[] {}, null, null, null);
            Uri result;
            if ((ListenerUtil.mutListener.listen(9274) ? (c != null || (ListenerUtil.mutListener.listen(9273) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9272) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9271) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9270) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9269) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(9273) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9272) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9271) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9270) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9269) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(9276)) {
                    cr.update(uri, values, null, null);
                }
                result = uri;
            } else {
                if (!ListenerUtil.mutListener.listen(9275)) {
                    values.put(_ID, id);
                }
                result = cr.insert(CONTENT_URI, values);
            }
            if (!ListenerUtil.mutListener.listen(9278)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9277)) {
                        c.close();
                    }
                }
            }
            return result;
        }

        public static ObaRegion get(Context context, int id) {
            return get(context.getContentResolver(), id);
        }

        public static ObaRegion get(ContentResolver cr, int id) {
            final String[] PROJECTION = { _ID, NAME, OBA_BASE_URL, SIRI_BASE_URL, LANGUAGE, CONTACT_EMAIL, SUPPORTS_OBA_DISCOVERY, SUPPORTS_OBA_REALTIME, SUPPORTS_SIRI_REALTIME, TWITTER_URL, EXPERIMENTAL, STOP_INFO_URL, OTP_BASE_URL, OTP_CONTACT_EMAIL, SUPPORTS_OTP_BIKESHARE, SUPPORTS_EMBEDDED_SOCIAL, PAYMENT_ANDROID_APP_ID, PAYMENT_WARNING_TITLE, PAYMENT_WARNING_BODY, TRAVEL_BEHAVIOR_DATA_COLLECTION, ENROLL_PARTICIPANTS_IN_STUDY };
            Cursor c = cr.query(buildUri((int) id), PROJECTION, null, null, null);
            if (!ListenerUtil.mutListener.listen(9322)) {
                if (c != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(9280)) {
                            if (c.getCount() == 0) {
                                return null;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9281)) {
                            c.moveToFirst();
                        }
                        return new // id
                        ObaRegionElement(// id
                        id, // Name
                        c.getString(1), // Active
                        true, // OBA Base URL
                        c.getString(2), // SIRI Base URL
                        c.getString(3), // Bounds
                        RegionBounds.getRegion(cr, id), // Open311 servers
                        RegionOpen311Servers.getOpen311Server(cr, id), // Lang
                        c.getString(4), // Contact Email
                        c.getString(5), (ListenerUtil.mutListener.listen(9286) ? (// Supports Oba Discovery
                        c.getInt(6) >= 0) : (ListenerUtil.mutListener.listen(9285) ? (// Supports Oba Discovery
                        c.getInt(6) <= 0) : (ListenerUtil.mutListener.listen(9284) ? (// Supports Oba Discovery
                        c.getInt(6) < 0) : (ListenerUtil.mutListener.listen(9283) ? (// Supports Oba Discovery
                        c.getInt(6) != 0) : (ListenerUtil.mutListener.listen(9282) ? (// Supports Oba Discovery
                        c.getInt(6) == 0) : (// Supports Oba Discovery
                        c.getInt(6) > 0)))))), (ListenerUtil.mutListener.listen(9291) ? (// Supports Oba Realtime
                        c.getInt(7) >= 0) : (ListenerUtil.mutListener.listen(9290) ? (// Supports Oba Realtime
                        c.getInt(7) <= 0) : (ListenerUtil.mutListener.listen(9289) ? (// Supports Oba Realtime
                        c.getInt(7) < 0) : (ListenerUtil.mutListener.listen(9288) ? (// Supports Oba Realtime
                        c.getInt(7) != 0) : (ListenerUtil.mutListener.listen(9287) ? (// Supports Oba Realtime
                        c.getInt(7) == 0) : (// Supports Oba Realtime
                        c.getInt(7) > 0)))))), (ListenerUtil.mutListener.listen(9296) ? (// Supports Siri Realtime
                        c.getInt(8) >= 0) : (ListenerUtil.mutListener.listen(9295) ? (// Supports Siri Realtime
                        c.getInt(8) <= 0) : (ListenerUtil.mutListener.listen(9294) ? (// Supports Siri Realtime
                        c.getInt(8) < 0) : (ListenerUtil.mutListener.listen(9293) ? (// Supports Siri Realtime
                        c.getInt(8) != 0) : (ListenerUtil.mutListener.listen(9292) ? (// Supports Siri Realtime
                        c.getInt(8) == 0) : (// Supports Siri Realtime
                        c.getInt(8) > 0)))))), // Twitter URL
                        c.getString(9), (ListenerUtil.mutListener.listen(9301) ? (// Experimental
                        c.getInt(10) >= 0) : (ListenerUtil.mutListener.listen(9300) ? (// Experimental
                        c.getInt(10) <= 0) : (ListenerUtil.mutListener.listen(9299) ? (// Experimental
                        c.getInt(10) < 0) : (ListenerUtil.mutListener.listen(9298) ? (// Experimental
                        c.getInt(10) != 0) : (ListenerUtil.mutListener.listen(9297) ? (// Experimental
                        c.getInt(10) == 0) : (// Experimental
                        c.getInt(10) > 0)))))), // StopInfoUrl
                        c.getString(11), // OtpBaseUrl
                        c.getString(12), // OtpContactEmail
                        c.getString(13), (ListenerUtil.mutListener.listen(9306) ? (// Supports OTP Bikeshare
                        c.getInt(14) >= 0) : (ListenerUtil.mutListener.listen(9305) ? (// Supports OTP Bikeshare
                        c.getInt(14) <= 0) : (ListenerUtil.mutListener.listen(9304) ? (// Supports OTP Bikeshare
                        c.getInt(14) < 0) : (ListenerUtil.mutListener.listen(9303) ? (// Supports OTP Bikeshare
                        c.getInt(14) != 0) : (ListenerUtil.mutListener.listen(9302) ? (// Supports OTP Bikeshare
                        c.getInt(14) == 0) : (// Supports OTP Bikeshare
                        c.getInt(14) > 0)))))), (ListenerUtil.mutListener.listen(9311) ? (// Supports Embedded Social
                        c.getInt(15) >= 0) : (ListenerUtil.mutListener.listen(9310) ? (// Supports Embedded Social
                        c.getInt(15) <= 0) : (ListenerUtil.mutListener.listen(9309) ? (// Supports Embedded Social
                        c.getInt(15) < 0) : (ListenerUtil.mutListener.listen(9308) ? (// Supports Embedded Social
                        c.getInt(15) != 0) : (ListenerUtil.mutListener.listen(9307) ? (// Supports Embedded Social
                        c.getInt(15) == 0) : (// Supports Embedded Social
                        c.getInt(15) > 0)))))), // Payment Android App ID
                        c.getString(16), // Payment Warning Title
                        c.getString(17), // Payment Warning Body
                        c.getString(18), (ListenerUtil.mutListener.listen(9316) ? (// Travel behavior data collection
                        c.getInt(19) >= 0) : (ListenerUtil.mutListener.listen(9315) ? (// Travel behavior data collection
                        c.getInt(19) <= 0) : (ListenerUtil.mutListener.listen(9314) ? (// Travel behavior data collection
                        c.getInt(19) < 0) : (ListenerUtil.mutListener.listen(9313) ? (// Travel behavior data collection
                        c.getInt(19) != 0) : (ListenerUtil.mutListener.listen(9312) ? (// Travel behavior data collection
                        c.getInt(19) == 0) : (// Travel behavior data collection
                        c.getInt(19) > 0)))))), (ListenerUtil.mutListener.listen(9321) ? (// Enroll participants in travel behavior study
                        c.getInt(20) >= 0) : (ListenerUtil.mutListener.listen(9320) ? (// Enroll participants in travel behavior study
                        c.getInt(20) <= 0) : (ListenerUtil.mutListener.listen(9319) ? (// Enroll participants in travel behavior study
                        c.getInt(20) < 0) : (ListenerUtil.mutListener.listen(9318) ? (// Enroll participants in travel behavior study
                        c.getInt(20) != 0) : (ListenerUtil.mutListener.listen(9317) ? (// Enroll participants in travel behavior study
                        c.getInt(20) == 0) : (// Enroll participants in travel behavior study
                        c.getInt(20) > 0)))))));
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9279)) {
                            c.close();
                        }
                    }
                }
            }
            return null;
        }
    }

    public static class RegionBounds implements BaseColumns, RegionBoundsColumns {

        // Cannot be instantiated
        private RegionBounds() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "region_bounds";

        /**
         * The content:// style URI for this table URI is of the form
         * content://<authority>/region_bounds/<id>
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".region_bounds";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".region_bounds";

        public static Uri buildUri(int id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static ObaRegionElement.Bounds[] getRegion(ContentResolver cr, int regionId) {
            final String[] PROJECTION = { LATITUDE, LONGITUDE, LAT_SPAN, LON_SPAN };
            Cursor c = cr.query(CONTENT_URI, PROJECTION, "(" + RegionBounds.REGION_ID + " = " + regionId + ")", null, null);
            if (!ListenerUtil.mutListener.listen(9329)) {
                if (c != null) {
                    try {
                        ObaRegionElement.Bounds[] results = new ObaRegionElement.Bounds[c.getCount()];
                        if (!ListenerUtil.mutListener.listen(9324)) {
                            if (c.getCount() == 0) {
                                return results;
                            }
                        }
                        int i = 0;
                        if (!ListenerUtil.mutListener.listen(9325)) {
                            c.moveToFirst();
                        }
                        if (!ListenerUtil.mutListener.listen(9328)) {
                            {
                                long _loopCounter117 = 0;
                                do {
                                    ListenerUtil.loopListener.listen("_loopCounter117", ++_loopCounter117);
                                    if (!ListenerUtil.mutListener.listen(9326)) {
                                        results[i] = new ObaRegionElement.Bounds(c.getDouble(0), c.getDouble(1), c.getDouble(2), c.getDouble(3));
                                    }
                                    if (!ListenerUtil.mutListener.listen(9327)) {
                                        i++;
                                    }
                                } while (c.moveToNext());
                            }
                        }
                        return results;
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9323)) {
                            c.close();
                        }
                    }
                }
            }
            return null;
        }
    }

    public static class RegionOpen311Servers implements BaseColumns, RegionOpen311ServersColumns {

        // Cannot be instantiated
        private RegionOpen311Servers() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "open311_servers";

        /**
         * The content:// style URI for this table URI is of the form
         * content://<authority>/region_open311_servers/<id>
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_TYPE = "vnd.android.cursor.item/" + BuildConfig.DATABASE_AUTHORITY + ".open311_servers";

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".open311_servers";

        public static Uri buildUri(int id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static ObaRegionElement.Open311Server[] getOpen311Server(ContentResolver cr, int regionId) {
            final String[] PROJECTION = { JURISDICTION, API_KEY, BASE_URL };
            Cursor c = cr.query(CONTENT_URI, PROJECTION, "(" + RegionOpen311Servers.REGION_ID + " = " + regionId + ")", null, null);
            if (!ListenerUtil.mutListener.listen(9336)) {
                if (c != null) {
                    try {
                        ObaRegionElement.Open311Server[] results = new ObaRegionElement.Open311Server[c.getCount()];
                        if (!ListenerUtil.mutListener.listen(9331)) {
                            if (c.getCount() == 0) {
                                return results;
                            }
                        }
                        int i = 0;
                        if (!ListenerUtil.mutListener.listen(9332)) {
                            c.moveToFirst();
                        }
                        if (!ListenerUtil.mutListener.listen(9335)) {
                            {
                                long _loopCounter118 = 0;
                                do {
                                    ListenerUtil.loopListener.listen("_loopCounter118", ++_loopCounter118);
                                    if (!ListenerUtil.mutListener.listen(9333)) {
                                        results[i] = new ObaRegionElement.Open311Server(c.getString(0), c.getString(1), c.getString(2));
                                    }
                                    if (!ListenerUtil.mutListener.listen(9334)) {
                                        i++;
                                    }
                                } while (c.moveToNext());
                            }
                        }
                        return results;
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9330)) {
                            c.close();
                        }
                    }
                }
            }
            return null;
        }
    }

    /**
     * Supports storing user-defined favorites for route/headsign/stop combinations.  This is
     * currently implemented without requiring knowledge of a full set of stops for a route.  This
     * allows some flexibility in terms of changes server-side without invalidating user's
     * favorites - as long as the routeId/headsign combination remains consistent (and stopId,
     * when a particular stop is referenced), then user favorites should survive changes in the
     * composition of stops for a route.
     *
     * When the user favorites a route/headsign combination in the ArrivalsListFragment/Header,
     * they are prompted if they would like to make it a favorite for the current stop, or for all
     * stops.  If they make it a favorite for the current stop, a record with
     * routeId/headsign/stopId is created, with "exclude" value of false (0).  If they make it a
     * favorite for all stops, a record with routeId/headsign/ALL_STOPS is created with exclude
     * value of false.  When arrival times are displayed for a given stopId, if a record in the
     * database with routeId/headsign/ALL_STOPS or routeId?headsign/stopId matches AND exclude is
     * set to false, then it is shown as a favorite.  Otherwise, it is not shown as a favorite.
     * If the user unstars a stop, then routeId/headsign/stopId is inserted with an exclude value of
     * true (1).S
     */
    public static class RouteHeadsignFavorites implements RouteHeadsignKeyColumns, UserColumns {

        // Cannot be instantiated
        private RouteHeadsignFavorites() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "route_headsign_favorites";

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".routeheadsignfavorites";

        // String used to indicate that a route/headsign combination is a favorite for all stops
        private static final String ALL_STOPS = "all";

        /**
         * Set the specified route and headsign combination as a favorite, optionally for a specific
         * stop.  Note that this will also handle the marking/unmarking of the designated route as
         * the favorite as well.  The route is marked as not a favorite when no more
         * routeId/headsign combinations remain.  If marking the route/headsign as favorite for
         * all stops, then stopId should be null.
         *
         * @param routeId  routeId to be marked as favorite, in combination with headsign
         * @param headsign headsign to be marked as favorite, in combination with routeId, or null
         *                 if all headsigns should be marked for this routeId/stopId combo
         * @param stopId stopId to be marked as a favorite, or null if all stopIds should be marked
         *               for this routeId/headsign combo.
         * @param favorite true if this route and headsign combination should be marked as a
         *                 favorite, false if it should not
         */
        public static void markAsFavorite(Context context, String routeId, String headsign, String stopId, boolean favorite) {
            if (!ListenerUtil.mutListener.listen(9337)) {
                if (context == null) {
                    return;
                }
            }
            final String WHERE;
            if (headsign == null) {
                WHERE = ROUTE_ID + "=? AND " + STOP_ID + "=?";
            } else {
                WHERE = ROUTE_ID + "=? AND " + HEADSIGN + "=? AND " + STOP_ID + "=?";
            }
            ContentResolver cr = context.getContentResolver();
            Uri routeUri = Uri.withAppendedPath(ObaContract.Routes.CONTENT_URI, routeId);
            String stopIdInternal;
            if (stopId != null) {
                stopIdInternal = stopId;
            } else {
                stopIdInternal = ALL_STOPS;
            }
            final String[] selectionArgs;
            if (headsign == null) {
                selectionArgs = new String[] { routeId, stopIdInternal };
            } else {
                selectionArgs = new String[] { routeId, headsign, stopIdInternal };
            }
            if (!ListenerUtil.mutListener.listen(9358)) {
                if (favorite) {
                    if (!ListenerUtil.mutListener.listen(9351)) {
                        if (stopIdInternal != ALL_STOPS) {
                            if (!ListenerUtil.mutListener.listen(9350)) {
                                // First, delete any potential exclusion records for this stop by removing all records
                                cr.delete(CONTENT_URI, WHERE, selectionArgs);
                            }
                        }
                    }
                    // Mark as favorite by inserting a record for this route/headsign combo
                    ContentValues values = new ContentValues();
                    if (!ListenerUtil.mutListener.listen(9352)) {
                        values.put(ROUTE_ID, routeId);
                    }
                    if (!ListenerUtil.mutListener.listen(9353)) {
                        values.put(HEADSIGN, headsign);
                    }
                    if (!ListenerUtil.mutListener.listen(9354)) {
                        values.put(STOP_ID, stopIdInternal);
                    }
                    if (!ListenerUtil.mutListener.listen(9355)) {
                        values.put(EXCLUDE, 0);
                    }
                    if (!ListenerUtil.mutListener.listen(9356)) {
                        cr.insert(CONTENT_URI, values);
                    }
                    if (!ListenerUtil.mutListener.listen(9357)) {
                        // Mark the route as a favorite also in the routes table
                        Routes.markAsFavorite(context, routeUri, true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9338)) {
                        // Deselect it as favorite by deleting all records for this route/headsign/stopId combo
                        cr.delete(CONTENT_URI, WHERE, selectionArgs);
                    }
                    if (!ListenerUtil.mutListener.listen(9340)) {
                        if (stopIdInternal == ALL_STOPS) {
                            // We don't have the stopId here, so we can just delete all records for this routeId/headsign
                            final String[] selectionArgs2;
                            final String WHERE2;
                            if (headsign == null) {
                                selectionArgs2 = new String[] { routeId };
                                WHERE2 = ROUTE_ID + "=?";
                            } else {
                                selectionArgs2 = new String[] { routeId, headsign };
                                WHERE2 = ROUTE_ID + "=? AND " + HEADSIGN + "=?";
                            }
                            if (!ListenerUtil.mutListener.listen(9339)) {
                                cr.delete(CONTENT_URI, WHERE2, selectionArgs2);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9342)) {
                        // then mark the route as not a favorite
                        if (!isFavorite(context, routeId)) {
                            if (!ListenerUtil.mutListener.listen(9341)) {
                                Routes.markAsFavorite(context, routeUri, false);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9349)) {
                        // stops, insert exclusion record
                        if ((ListenerUtil.mutListener.listen(9343) ? (stopIdInternal != ALL_STOPS || isFavorite(routeId, headsign, stopId)) : (stopIdInternal != ALL_STOPS && isFavorite(routeId, headsign, stopId)))) {
                            // after starring the entire route
                            ContentValues values = new ContentValues();
                            if (!ListenerUtil.mutListener.listen(9344)) {
                                values.put(ROUTE_ID, routeId);
                            }
                            if (!ListenerUtil.mutListener.listen(9345)) {
                                values.put(HEADSIGN, headsign);
                            }
                            if (!ListenerUtil.mutListener.listen(9346)) {
                                values.put(STOP_ID, stopIdInternal);
                            }
                            if (!ListenerUtil.mutListener.listen(9347)) {
                                values.put(EXCLUDE, 1);
                            }
                            if (!ListenerUtil.mutListener.listen(9348)) {
                                cr.insert(CONTENT_URI, values);
                            }
                        }
                    }
                }
            }
            StringBuilder analyticsEvent = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(9361)) {
                if (favorite) {
                    if (!ListenerUtil.mutListener.listen(9360)) {
                        analyticsEvent.append(context.getString(R.string.analytics_label_star_route));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9359)) {
                        analyticsEvent.append(context.getString(R.string.analytics_label_unstar_route));
                    }
                }
            }
            StringBuilder analyticsParam = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(9362)) {
                analyticsParam.append(routeId).append("_").append(headsign).append(" for ");
            }
            if (!ListenerUtil.mutListener.listen(9365)) {
                if (stopId != null) {
                    if (!ListenerUtil.mutListener.listen(9364)) {
                        analyticsParam.append(stopId);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9363)) {
                        analyticsParam.append("all stops");
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9366)) {
                ObaAnalytics.reportUiEvent(FirebaseAnalytics.getInstance(context), analyticsEvent.toString(), analyticsParam.toString());
            }
        }

        /**
         * Delete all saved route/headsign favorites at once.
         */
        public static void clearAllFavorites(Context context) {
            ContentResolver cr = context.getContentResolver();
            if (!ListenerUtil.mutListener.listen(9367)) {
                cr.delete(CONTENT_URI, null, null);
            }
        }

        /**
         * Returns true if this combination of routeId and headsign is a favorite for this stop
         * or all stops (and that stop is not excluded as a favorite), false if it is not
         *
         * @param routeId  The routeId to check for favorite
         * @param headsign The headsign to check for favorite
         * @param stopId The stopId to check for favorite
         * @return true if this combination of routeId and headsign is a favorite for this stop
         * or all stops (and that stop is not excluded as a favorite), false if it is not
         */
        public static boolean isFavorite(String routeId, String headsign, String stopId) {
            if (!ListenerUtil.mutListener.listen(9369)) {
                if (headsign == null) {
                    if (!ListenerUtil.mutListener.listen(9368)) {
                        headsign = "";
                    }
                }
            }
            final String[] selection = { ROUTE_ID, HEADSIGN, STOP_ID, EXCLUDE };
            final String[] selectionArgs = { routeId, headsign, stopId, Integer.toString(0) };
            ContentResolver cr = Application.get().getContentResolver();
            final String FILTER_WHERE_ALL_FIELDS = ROUTE_ID + "=? AND " + HEADSIGN + "=? AND " + STOP_ID + "=? AND " + EXCLUDE + "=?";
            Cursor c = cr.query(CONTENT_URI, selection, FILTER_WHERE_ALL_FIELDS, selectionArgs, null);
            boolean favorite;
            if ((ListenerUtil.mutListener.listen(9375) ? (c != null || (ListenerUtil.mutListener.listen(9374) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9373) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9372) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9371) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9370) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(9374) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9373) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9372) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9371) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9370) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                favorite = true;
            } else {
                // Check again to see if the user has favorited this route/headsign combo for all stops
                final String[] selectionArgs2 = { routeId, headsign, ALL_STOPS };
                String WHERE_PARTIAL = ROUTE_ID + "=? AND " + HEADSIGN + "=? AND " + STOP_ID + "=?";
                Cursor c2 = cr.query(CONTENT_URI, selection, WHERE_PARTIAL, selectionArgs2, null);
                favorite = (ListenerUtil.mutListener.listen(9381) ? (c2 != null || (ListenerUtil.mutListener.listen(9380) ? (c2.getCount() >= 0) : (ListenerUtil.mutListener.listen(9379) ? (c2.getCount() <= 0) : (ListenerUtil.mutListener.listen(9378) ? (c2.getCount() < 0) : (ListenerUtil.mutListener.listen(9377) ? (c2.getCount() != 0) : (ListenerUtil.mutListener.listen(9376) ? (c2.getCount() == 0) : (c2.getCount() > 0))))))) : (c2 != null && (ListenerUtil.mutListener.listen(9380) ? (c2.getCount() >= 0) : (ListenerUtil.mutListener.listen(9379) ? (c2.getCount() <= 0) : (ListenerUtil.mutListener.listen(9378) ? (c2.getCount() < 0) : (ListenerUtil.mutListener.listen(9377) ? (c2.getCount() != 0) : (ListenerUtil.mutListener.listen(9376) ? (c2.getCount() == 0) : (c2.getCount() > 0))))))));
                if (!ListenerUtil.mutListener.listen(9383)) {
                    if (c2 != null) {
                        if (!ListenerUtil.mutListener.listen(9382)) {
                            c2.close();
                        }
                    }
                }
                if (favorite) {
                    // Finally, make sure the user hasn't excluded this stop as a favorite
                    final String[] selectionArgs3 = { routeId, headsign, stopId, Integer.toString(1) };
                    Cursor c3 = cr.query(CONTENT_URI, selection, FILTER_WHERE_ALL_FIELDS, selectionArgs3, null);
                    // a favorite (i.e., the user explicitly de-selected it)
                    boolean isStopExcluded = (ListenerUtil.mutListener.listen(9389) ? (c3 != null || (ListenerUtil.mutListener.listen(9388) ? (c3.getCount() >= 0) : (ListenerUtil.mutListener.listen(9387) ? (c3.getCount() <= 0) : (ListenerUtil.mutListener.listen(9386) ? (c3.getCount() < 0) : (ListenerUtil.mutListener.listen(9385) ? (c3.getCount() != 0) : (ListenerUtil.mutListener.listen(9384) ? (c3.getCount() == 0) : (c3.getCount() > 0))))))) : (c3 != null && (ListenerUtil.mutListener.listen(9388) ? (c3.getCount() >= 0) : (ListenerUtil.mutListener.listen(9387) ? (c3.getCount() <= 0) : (ListenerUtil.mutListener.listen(9386) ? (c3.getCount() < 0) : (ListenerUtil.mutListener.listen(9385) ? (c3.getCount() != 0) : (ListenerUtil.mutListener.listen(9384) ? (c3.getCount() == 0) : (c3.getCount() > 0))))))));
                    favorite = !isStopExcluded;
                    if (!ListenerUtil.mutListener.listen(9391)) {
                        if (c3 != null) {
                            if (!ListenerUtil.mutListener.listen(9390)) {
                                c3.close();
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9393)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9392)) {
                        c.close();
                    }
                }
            }
            return favorite;
        }

        /**
         * Returns true if this routeId is listed as a favorite for at least one headsign with
         * EXCLUDED set to false, or false if it is not
         *
         * @param routeId The routeId to check for favorite
         * @return true if this routeId is listed as a favorite for at least one headsign without
         * EXCLUDE being set to true, or false if it is not
         */
        private static boolean isFavorite(Context context, String routeId) {
            final String[] selection = { ROUTE_ID, EXCLUDE };
            final String[] selectionArgs = { routeId, Integer.toString(0) };
            final String WHERE = ROUTE_ID + "=? AND " + EXCLUDE + "=?";
            ContentResolver cr = context.getContentResolver();
            Cursor c = cr.query(CONTENT_URI, selection, WHERE, selectionArgs, null);
            boolean favorite = false;
            if (!ListenerUtil.mutListener.listen(9402)) {
                if ((ListenerUtil.mutListener.listen(9399) ? (c != null || (ListenerUtil.mutListener.listen(9398) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9397) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9396) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9395) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9394) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(9398) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(9397) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(9396) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(9395) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(9394) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(9401)) {
                        favorite = true;
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(9400)) {
                        favorite = false;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(9404)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(9403)) {
                        c.close();
                    }
                }
            }
            return favorite;
        }
    }

    public static class NavStops implements BaseColumns, NavigationColumns {

        // Cannot be instantiated
        private NavStops() {
        }

        /**
         * The URI path portion for this table
         */
        public static final String PATH = "nav_stops";

        /**
         * The content:// style URI for this table
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);

        public static final String CONTENT_DIR_TYPE = "vnd.android.dir/" + BuildConfig.DATABASE_AUTHORITY + ".navstops";

        public static Uri insert(Context context, Long startTime, Integer navId, Integer seqNum, String tripId, String destId, String beforeId) {
            // TODO: Delete there since there's only one active trip.
            ContentResolver cr = context.getContentResolver();
            if (!ListenerUtil.mutListener.listen(9405)) {
                cr.delete(CONTENT_URI, null, null);
            }
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9406)) {
                values.put(NAV_ID, navId);
            }
            if (!ListenerUtil.mutListener.listen(9407)) {
                values.put(START_TIME, startTime);
            }
            if (!ListenerUtil.mutListener.listen(9408)) {
                values.put(TRIP_ID, tripId);
            }
            if (!ListenerUtil.mutListener.listen(9409)) {
                values.put(DESTINATION_ID, destId);
            }
            if (!ListenerUtil.mutListener.listen(9410)) {
                values.put(BEFORE_ID, beforeId);
            }
            if (!ListenerUtil.mutListener.listen(9411)) {
                values.put(SEQUENCE, seqNum);
            }
            if (!ListenerUtil.mutListener.listen(9412)) {
                values.put(ACTIVE, 1);
            }
            return cr.insert(CONTENT_URI, values);
        }

        /**
         * Inserts multi-leg trip into database.
         * @param context Context.
         * @param startTime the startTime in milliseconds for this PathLink instance
         * @param navId   Navigation ID for destination alert
         * @param tripId  Trip ID of route.
         * @param legs    Array of 2-string arrays, first element being stopId of second-to-last
         *                stop and second element being stopid of destination stop of leg.
         * @return
         */
        public static boolean insert(Context context, Long startTime, Integer navId, String tripId, String[][] legs) {
            ContentResolver cr = context.getContentResolver();
            if (!ListenerUtil.mutListener.listen(9413)) {
                // TODO: Delete there since there's only one active trip atm.
                cr.delete(CONTENT_URI, null, null);
            }
            if (!ListenerUtil.mutListener.listen(9425)) {
                // If inner array doesn't contain 2 values, can't insert.
                if ((ListenerUtil.mutListener.listen(9424) ? ((ListenerUtil.mutListener.listen(9418) ? (legs.length >= 1) : (ListenerUtil.mutListener.listen(9417) ? (legs.length <= 1) : (ListenerUtil.mutListener.listen(9416) ? (legs.length < 1) : (ListenerUtil.mutListener.listen(9415) ? (legs.length != 1) : (ListenerUtil.mutListener.listen(9414) ? (legs.length == 1) : (legs.length > 1)))))) || (ListenerUtil.mutListener.listen(9423) ? (legs[0].length >= 2) : (ListenerUtil.mutListener.listen(9422) ? (legs[0].length <= 2) : (ListenerUtil.mutListener.listen(9421) ? (legs[0].length > 2) : (ListenerUtil.mutListener.listen(9420) ? (legs[0].length != 2) : (ListenerUtil.mutListener.listen(9419) ? (legs[0].length == 2) : (legs[0].length < 2))))))) : ((ListenerUtil.mutListener.listen(9418) ? (legs.length >= 1) : (ListenerUtil.mutListener.listen(9417) ? (legs.length <= 1) : (ListenerUtil.mutListener.listen(9416) ? (legs.length < 1) : (ListenerUtil.mutListener.listen(9415) ? (legs.length != 1) : (ListenerUtil.mutListener.listen(9414) ? (legs.length == 1) : (legs.length > 1)))))) && (ListenerUtil.mutListener.listen(9423) ? (legs[0].length >= 2) : (ListenerUtil.mutListener.listen(9422) ? (legs[0].length <= 2) : (ListenerUtil.mutListener.listen(9421) ? (legs[0].length > 2) : (ListenerUtil.mutListener.listen(9420) ? (legs[0].length != 2) : (ListenerUtil.mutListener.listen(9419) ? (legs[0].length == 2) : (legs[0].length < 2))))))))) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(9442)) {
                {
                    long _loopCounter119 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(9441) ? (i >= legs.length) : (ListenerUtil.mutListener.listen(9440) ? (i <= legs.length) : (ListenerUtil.mutListener.listen(9439) ? (i > legs.length) : (ListenerUtil.mutListener.listen(9438) ? (i != legs.length) : (ListenerUtil.mutListener.listen(9437) ? (i == legs.length) : (i < legs.length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter119", ++_loopCounter119);
                        ContentValues values = new ContentValues();
                        if (!ListenerUtil.mutListener.listen(9426)) {
                            values.put(NAV_ID, navId);
                        }
                        if (!ListenerUtil.mutListener.listen(9427)) {
                            values.put(START_TIME, startTime);
                        }
                        if (!ListenerUtil.mutListener.listen(9428)) {
                            values.put(TRIP_ID, tripId);
                        }
                        if (!ListenerUtil.mutListener.listen(9429)) {
                            values.put(BEFORE_ID, legs[i][0]);
                        }
                        if (!ListenerUtil.mutListener.listen(9430)) {
                            values.put(DESTINATION_ID, legs[i][1]);
                        }
                        if (!ListenerUtil.mutListener.listen(9435)) {
                            values.put(SEQUENCE, (ListenerUtil.mutListener.listen(9434) ? (i % 1) : (ListenerUtil.mutListener.listen(9433) ? (i / 1) : (ListenerUtil.mutListener.listen(9432) ? (i * 1) : (ListenerUtil.mutListener.listen(9431) ? (i - 1) : (i + 1))))));
                        }
                        if (!ListenerUtil.mutListener.listen(9436)) {
                            values.put(ACTIVE, 1);
                        }
                    }
                }
            }
            return true;
        }

        public static boolean update(Context context, Uri uri, boolean active) {
            ContentResolver cr = context.getContentResolver();
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(9443)) {
                values.put(ACTIVE, active ? 1 : 0);
            }
            return (ListenerUtil.mutListener.listen(9448) ? (cr.update(uri, values, null, null) >= 0) : (ListenerUtil.mutListener.listen(9447) ? (cr.update(uri, values, null, null) <= 0) : (ListenerUtil.mutListener.listen(9446) ? (cr.update(uri, values, null, null) < 0) : (ListenerUtil.mutListener.listen(9445) ? (cr.update(uri, values, null, null) != 0) : (ListenerUtil.mutListener.listen(9444) ? (cr.update(uri, values, null, null) == 0) : (cr.update(uri, values, null, null) > 0))))));
        }

        public static String[] getDetails(Context context, String id) {
            final String[] PROJECTION = { TRIP_ID, DESTINATION_ID, BEFORE_ID };
            ContentResolver cr = context.getContentResolver();
            Cursor c = cr.query(CONTENT_URI, PROJECTION, NAV_ID + "=?", new String[] { id }, null);
            if (!ListenerUtil.mutListener.listen(9452)) {
                if (c != null) {
                    try {
                        if (!ListenerUtil.mutListener.listen(9450)) {
                            if (c.getCount() == 0) {
                                return null;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(9451)) {
                            c.moveToFirst();
                        }
                        return new String[] { c.getString(0), c.getString(1), c.getString(2) };
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9449)) {
                            c.close();
                        }
                    }
                }
            }
            return null;
        }

        public static PathLink[] get(Context context, String navId) {
            final String[] PROJECTION = { TRIP_ID, DESTINATION_ID, BEFORE_ID, START_TIME };
            ContentResolver cr = context.getContentResolver();
            Cursor c = cr.query(CONTENT_URI, PROJECTION, NAV_ID + "=?", new String[] { navId }, SEQUENCE + " ASC");
            if (!ListenerUtil.mutListener.listen(9459)) {
                if (c != null) {
                    try {
                        PathLink[] results = new PathLink[c.getCount()];
                        if (!ListenerUtil.mutListener.listen(9454)) {
                            if (c.getCount() == 0) {
                                return results;
                            }
                        }
                        int i = 0;
                        if (!ListenerUtil.mutListener.listen(9455)) {
                            c.moveToFirst();
                        }
                        if (!ListenerUtil.mutListener.listen(9458)) {
                            {
                                long _loopCounter120 = 0;
                                do {
                                    ListenerUtil.loopListener.listen("_loopCounter120", ++_loopCounter120);
                                    if (!ListenerUtil.mutListener.listen(9456)) {
                                        results[i] = new PathLink(c.getLong(3), null, Stops.getLocation(context, c.getString(2)), Stops.getLocation(context, c.getString(1)), c.getString(0));
                                    }
                                    if (!ListenerUtil.mutListener.listen(9457)) {
                                        i++;
                                    }
                                } while (c.moveToNext());
                            }
                        }
                        return results;
                    } finally {
                        if (!ListenerUtil.mutListener.listen(9453)) {
                            c.close();
                        }
                    }
                }
            }
            return null;
        }
    }
}
