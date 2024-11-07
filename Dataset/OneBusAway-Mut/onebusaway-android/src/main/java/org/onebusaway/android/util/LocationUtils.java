/*
 * Copyright (C) 2014 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.directions.util.CustomAddress;
import org.onebusaway.android.io.elements.ObaRegion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import edu.usf.cutr.pelias.AutocompleteRequest;
import edu.usf.cutr.pelias.PeliasRequest;
import edu.usf.cutr.pelias.PeliasResponse;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Utilities to help obtain and process location data
 *
 * @author barbeau
 */
public class LocationUtils {

    public static final String TAG = "LocationUtil";

    public static final int DEFAULT_SEARCH_RADIUS = 40000;

    private static final float FUZZY_EQUALS_THRESHOLD = 15.0f;

    // 50 meters
    public static final float ACC_THRESHOLD = 50f;

    // 10 minutes
    public static final long TIME_THRESHOLD = TimeUnit.MINUTES.toMillis(10);

    private static final int GEOCODER_MAX_RESULTS = 5;

    // in meters
    private static final int GEOCODING_MAX_ERROR = 100;

    public static Location getDefaultSearchCenter() {
        ObaRegion region = Application.get().getCurrentRegion();
        if (region != null) {
            double[] results = new double[4];
            if (!ListenerUtil.mutListener.listen(7703)) {
                RegionUtils.getRegionSpan(region, results);
            }
            return LocationUtils.makeLocation(results[2], results[3]);
        } else {
            return null;
        }
    }

    /**
     * Compares Location A to Location B - prefers a non-null location that is more recent.  Does
     * NOT take estimated accuracy into account.
     * @param a first location to compare
     * @param b second location to compare
     * @return true if Location a is "better" than b, or false if b is "better" than a
     */
    public static boolean compareLocationsByTime(Location a, Location b) {
        return ((ListenerUtil.mutListener.listen(7710) ? (a != null || ((ListenerUtil.mutListener.listen(7709) ? (b == null && (ListenerUtil.mutListener.listen(7708) ? (a.getTime() >= b.getTime()) : (ListenerUtil.mutListener.listen(7707) ? (a.getTime() <= b.getTime()) : (ListenerUtil.mutListener.listen(7706) ? (a.getTime() < b.getTime()) : (ListenerUtil.mutListener.listen(7705) ? (a.getTime() != b.getTime()) : (ListenerUtil.mutListener.listen(7704) ? (a.getTime() == b.getTime()) : (a.getTime() > b.getTime()))))))) : (b == null || (ListenerUtil.mutListener.listen(7708) ? (a.getTime() >= b.getTime()) : (ListenerUtil.mutListener.listen(7707) ? (a.getTime() <= b.getTime()) : (ListenerUtil.mutListener.listen(7706) ? (a.getTime() < b.getTime()) : (ListenerUtil.mutListener.listen(7705) ? (a.getTime() != b.getTime()) : (ListenerUtil.mutListener.listen(7704) ? (a.getTime() == b.getTime()) : (a.getTime() > b.getTime())))))))))) : (a != null && ((ListenerUtil.mutListener.listen(7709) ? (b == null && (ListenerUtil.mutListener.listen(7708) ? (a.getTime() >= b.getTime()) : (ListenerUtil.mutListener.listen(7707) ? (a.getTime() <= b.getTime()) : (ListenerUtil.mutListener.listen(7706) ? (a.getTime() < b.getTime()) : (ListenerUtil.mutListener.listen(7705) ? (a.getTime() != b.getTime()) : (ListenerUtil.mutListener.listen(7704) ? (a.getTime() == b.getTime()) : (a.getTime() > b.getTime()))))))) : (b == null || (ListenerUtil.mutListener.listen(7708) ? (a.getTime() >= b.getTime()) : (ListenerUtil.mutListener.listen(7707) ? (a.getTime() <= b.getTime()) : (ListenerUtil.mutListener.listen(7706) ? (a.getTime() < b.getTime()) : (ListenerUtil.mutListener.listen(7705) ? (a.getTime() != b.getTime()) : (ListenerUtil.mutListener.listen(7704) ? (a.getTime() == b.getTime()) : (a.getTime() > b.getTime()))))))))))));
    }

    /**
     * Compares Location A to Location B, considering timestamps and accuracy of locations.
     * Typically
     * this is used to compare a new location delivered by a LocationListener (Location A) to
     * a previously saved location (Location B).
     *
     * @param a location to compare
     * @param b location to compare against
     * @return true if Location a is "better" than b, or false if b is "better" than a
     */
    public static boolean compareLocations(Location a, Location b) {
        if (!ListenerUtil.mutListener.listen(7711)) {
            if (a == null) {
                // New location isn't valid, return false
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(7712)) {
            // If the new location is the first location, save it
            if (b == null) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(7723)) {
            // save the new location, even if the accuracy for new location is worse
            if ((ListenerUtil.mutListener.listen(7722) ? ((ListenerUtil.mutListener.listen(7721) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) >= TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7720) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) <= TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7719) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) < TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7718) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) != TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7717) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) == TIME_THRESHOLD) : ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) > TIME_THRESHOLD)))))) || compareLocationsByTime(a, b)) : ((ListenerUtil.mutListener.listen(7721) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) >= TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7720) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) <= TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7719) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) < TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7718) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) != TIME_THRESHOLD) : (ListenerUtil.mutListener.listen(7717) ? ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) == TIME_THRESHOLD) : ((ListenerUtil.mutListener.listen(7716) ? (System.currentTimeMillis() % b.getTime()) : (ListenerUtil.mutListener.listen(7715) ? (System.currentTimeMillis() / b.getTime()) : (ListenerUtil.mutListener.listen(7714) ? (System.currentTimeMillis() * b.getTime()) : (ListenerUtil.mutListener.listen(7713) ? (System.currentTimeMillis() + b.getTime()) : (System.currentTimeMillis() - b.getTime()))))) > TIME_THRESHOLD)))))) && compareLocationsByTime(a, b)))) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(7730)) {
            // If the new location has an accuracy better than ACC_THRESHOLD and is newer than the last location, save it
            if ((ListenerUtil.mutListener.listen(7729) ? ((ListenerUtil.mutListener.listen(7728) ? (a.getAccuracy() >= ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7727) ? (a.getAccuracy() <= ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7726) ? (a.getAccuracy() > ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7725) ? (a.getAccuracy() != ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7724) ? (a.getAccuracy() == ACC_THRESHOLD) : (a.getAccuracy() < ACC_THRESHOLD)))))) || compareLocationsByTime(a, b)) : ((ListenerUtil.mutListener.listen(7728) ? (a.getAccuracy() >= ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7727) ? (a.getAccuracy() <= ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7726) ? (a.getAccuracy() > ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7725) ? (a.getAccuracy() != ACC_THRESHOLD) : (ListenerUtil.mutListener.listen(7724) ? (a.getAccuracy() == ACC_THRESHOLD) : (a.getAccuracy() < ACC_THRESHOLD)))))) && compareLocationsByTime(a, b)))) {
                return true;
            }
        }
        // If we get this far, A isn't better than B
        return false;
    }

    /**
     * Check if two locations are the exact same by comparing their timestamp, lat & lng.
     * @param a First location
     * @param b Second location
     * @return true if same, false otherwise.
     */
    public static boolean isDuplicate(Location a, Location b) {
        if (!ListenerUtil.mutListener.listen(7731)) {
            if (a.getTime() != b.getTime())
                return false;
        }
        if (!ListenerUtil.mutListener.listen(7732)) {
            if (a.getLatitude() != b.getLatitude())
                return false;
        }
        if (!ListenerUtil.mutListener.listen(7733)) {
            if (a.getLongitude() != b.getLongitude())
                return false;
        }
        return true;
    }

    /**
     * Converts a latitude/longitude to a Location.
     *
     * @param lat The latitude.
     * @param lon The longitude.
     * @return A Location representing this latitude/longitude.
     */
    public static Location makeLocation(double lat, double lon) {
        Location l = new Location("");
        if (!ListenerUtil.mutListener.listen(7734)) {
            l.setLatitude(lat);
        }
        if (!ListenerUtil.mutListener.listen(7735)) {
            l.setLongitude(lon);
        }
        return l;
    }

    /**
     * Returns true if the locations are approximately equal (i.e., within a certain distance
     * threshold)
     *
     * @param a first location
     * @param b second location
     * @return true if the locations are approximately equal, false if they are not
     */
    public static boolean fuzzyEquals(Location a, Location b) {
        return (ListenerUtil.mutListener.listen(7740) ? (a.distanceTo(b) >= FUZZY_EQUALS_THRESHOLD) : (ListenerUtil.mutListener.listen(7739) ? (a.distanceTo(b) > FUZZY_EQUALS_THRESHOLD) : (ListenerUtil.mutListener.listen(7738) ? (a.distanceTo(b) < FUZZY_EQUALS_THRESHOLD) : (ListenerUtil.mutListener.listen(7737) ? (a.distanceTo(b) != FUZZY_EQUALS_THRESHOLD) : (ListenerUtil.mutListener.listen(7736) ? (a.distanceTo(b) == FUZZY_EQUALS_THRESHOLD) : (a.distanceTo(b) <= FUZZY_EQUALS_THRESHOLD))))));
    }

    /**
     * Returns true if the user has enabled location services on their device, false if they have
     * not
     *
     * from http://stackoverflow.com/a/22980843/937715
     *
     * @return true if the user has enabled location services on their device, false if they have
     * not
     */
    public static boolean isLocationEnabled(Context context) {
        return getLocationMode(context) != Settings.Secure.LOCATION_MODE_OFF;
    }

    /**
     * This method is used to get Integer representation of location mode
     *
     * @param context
     * @return location mode for passed context
     */
    public static int getLocationMode(Context context) {
        int locationMode = Settings.Secure.LOCATION_MODE_OFF;
        String locationProviders;
        if ((ListenerUtil.mutListener.listen(7745) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(7744) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(7743) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(7742) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.KITKAT) : (ListenerUtil.mutListener.listen(7741) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT))))))) {
            try {
                if (!ListenerUtil.mutListener.listen(7751)) {
                    locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
                }
            } catch (Settings.SettingNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(7750)) {
                    e.printStackTrace();
                }
            }
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (!ListenerUtil.mutListener.listen(7749)) {
                if (TextUtils.isEmpty(locationProviders)) {
                    if (!ListenerUtil.mutListener.listen(7748)) {
                        locationMode = Settings.Secure.LOCATION_MODE_OFF;
                    }
                } else if (locationProviders.contains(LocationManager.GPS_PROVIDER)) {
                    if (!ListenerUtil.mutListener.listen(7747)) {
                        locationMode = Settings.Secure.LOCATION_MODE_HIGH_ACCURACY;
                    }
                } else if (locationProviders.contains(LocationManager.NETWORK_PROVIDER)) {
                    if (!ListenerUtil.mutListener.listen(7746)) {
                        locationMode = Settings.Secure.LOCATION_MODE_BATTERY_SAVING;
                    }
                }
            }
        }
        return locationMode;
    }

    /**
     * Returns the human-readable details of a Location (provider, lat/long, accuracy, timestamp)
     *
     * @return the details of a Location (provider, lat/long, accuracy, timestamp) in a string
     */
    public static String printLocationDetails(Location loc) {
        if (!ListenerUtil.mutListener.listen(7752)) {
            if (loc == null) {
                return "";
            }
        }
        long timeDiff;
        double timeDiffSec;
        if ((ListenerUtil.mutListener.listen(7757) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7756) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7755) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7754) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(7753) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
            timeDiff = (ListenerUtil.mutListener.listen(7769) ? (SystemClock.elapsedRealtimeNanos() % loc.getElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(7768) ? (SystemClock.elapsedRealtimeNanos() / loc.getElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(7767) ? (SystemClock.elapsedRealtimeNanos() * loc.getElapsedRealtimeNanos()) : (ListenerUtil.mutListener.listen(7766) ? (SystemClock.elapsedRealtimeNanos() + loc.getElapsedRealtimeNanos()) : (SystemClock.elapsedRealtimeNanos() - loc.getElapsedRealtimeNanos())))));
            // Convert to seconds
            timeDiffSec = (ListenerUtil.mutListener.listen(7773) ? (timeDiff % 1E9) : (ListenerUtil.mutListener.listen(7772) ? (timeDiff * 1E9) : (ListenerUtil.mutListener.listen(7771) ? (timeDiff - 1E9) : (ListenerUtil.mutListener.listen(7770) ? (timeDiff + 1E9) : (timeDiff / 1E9)))));
        } else {
            timeDiff = (ListenerUtil.mutListener.listen(7761) ? (System.currentTimeMillis() % loc.getTime()) : (ListenerUtil.mutListener.listen(7760) ? (System.currentTimeMillis() / loc.getTime()) : (ListenerUtil.mutListener.listen(7759) ? (System.currentTimeMillis() * loc.getTime()) : (ListenerUtil.mutListener.listen(7758) ? (System.currentTimeMillis() + loc.getTime()) : (System.currentTimeMillis() - loc.getTime())))));
            timeDiffSec = (ListenerUtil.mutListener.listen(7765) ? (timeDiff % 1E3) : (ListenerUtil.mutListener.listen(7764) ? (timeDiff * 1E3) : (ListenerUtil.mutListener.listen(7763) ? (timeDiff - 1E3) : (ListenerUtil.mutListener.listen(7762) ? (timeDiff + 1E3) : (timeDiff / 1E3)))));
        }
        StringBuilder sb = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(7774)) {
            sb.append(loc.getProvider());
        }
        if (!ListenerUtil.mutListener.listen(7775)) {
            sb.append(' ');
        }
        if (!ListenerUtil.mutListener.listen(7776)) {
            sb.append(loc.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(7777)) {
            sb.append(',');
        }
        if (!ListenerUtil.mutListener.listen(7778)) {
            sb.append(loc.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(7781)) {
            if (loc.hasAccuracy()) {
                if (!ListenerUtil.mutListener.listen(7779)) {
                    sb.append(' ');
                }
                if (!ListenerUtil.mutListener.listen(7780)) {
                    sb.append(loc.getAccuracy());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7782)) {
            sb.append(", ");
        }
        if (!ListenerUtil.mutListener.listen(7783)) {
            sb.append(String.format("%.0f", timeDiffSec) + " second(s) ago");
        }
        return sb.toString();
    }

    /**
     * Returns a new GoogleApiClient which includes LocationServicesCallbacks
     */
    public static GoogleApiClient getGoogleApiClientWithCallbacks(Context context) {
        LocationServicesCallback locCallback = new LocationServicesCallback();
        return new GoogleApiClient.Builder(context).addApi(LocationServices.API).addConnectionCallbacks(locCallback).addOnConnectionFailedListener(locCallback).build();
    }

    /**
     * Class to handle Google Play Location Services callbacks
     */
    public static class LocationServicesCallback implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

        private static final String TAG = "LocationServicesCallbck";

        @Override
        public void onConnected(Bundle bundle) {
            if (!ListenerUtil.mutListener.listen(7784)) {
                Log.d(TAG, "GoogleApiClient.onConnected");
            }
        }

        @Override
        public void onConnectionSuspended(int i) {
            if (!ListenerUtil.mutListener.listen(7785)) {
                Log.d(TAG, "GoogleApiClient.onConnectionSuspended");
            }
        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            if (!ListenerUtil.mutListener.listen(7786)) {
                Log.d(TAG, "GoogleApiClient.onConnectionFailed");
            }
        }
    }

    public static List<CustomAddress> processGooglePlacesGeocoding(Context context, ObaRegion region, String... reqs) {
        return processGeocoding(context, region, false, reqs);
    }

    public static List<CustomAddress> processGeocoding(Context context, ObaRegion region, boolean geocodingForMarker, String... reqs) {
        ArrayList<CustomAddress> addressesReturn = new ArrayList<CustomAddress>();
        String address = reqs[0];
        if (!ListenerUtil.mutListener.listen(7788)) {
            if ((ListenerUtil.mutListener.listen(7787) ? (address == null && address.equalsIgnoreCase("")) : (address == null || address.equalsIgnoreCase("")))) {
                return null;
            }
        }
        double latitude = 0, longitude = 0;
        boolean latLngSet = false;
        try {
            if (!ListenerUtil.mutListener.listen(7798)) {
                if ((ListenerUtil.mutListener.listen(7794) ? (reqs.length <= 3) : (ListenerUtil.mutListener.listen(7793) ? (reqs.length > 3) : (ListenerUtil.mutListener.listen(7792) ? (reqs.length < 3) : (ListenerUtil.mutListener.listen(7791) ? (reqs.length != 3) : (ListenerUtil.mutListener.listen(7790) ? (reqs.length == 3) : (reqs.length >= 3))))))) {
                    if (!ListenerUtil.mutListener.listen(7795)) {
                        latitude = Double.parseDouble(reqs[1]);
                    }
                    if (!ListenerUtil.mutListener.listen(7796)) {
                        longitude = Double.parseDouble(reqs[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(7797)) {
                        latLngSet = true;
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7789)) {
                Log.e(TAG, "Geocoding without reference latitude/longitude");
            }
        }
        if (!ListenerUtil.mutListener.listen(7804)) {
            if (address.equalsIgnoreCase(context.getString(R.string.tripplanner_current_location))) {
                if (!ListenerUtil.mutListener.listen(7803)) {
                    if (latLngSet) {
                        CustomAddress addressReturn = new CustomAddress(context.getResources().getConfiguration().locale);
                        if (!ListenerUtil.mutListener.listen(7799)) {
                            addressReturn.setLatitude(latitude);
                        }
                        if (!ListenerUtil.mutListener.listen(7800)) {
                            addressReturn.setLongitude(longitude);
                        }
                        if (!ListenerUtil.mutListener.listen(7801)) {
                            addressReturn.setAddressLine(addressReturn.getMaxAddressLineIndex() + 1, context.getString(R.string.tripplanner_current_location));
                        }
                        if (!ListenerUtil.mutListener.listen(7802)) {
                            addressesReturn.add(addressReturn);
                        }
                        return addressesReturn;
                    }
                }
                return null;
            }
        }
        List<CustomAddress> addresses = new ArrayList<>();
        Geocoder gc = new Geocoder(context);
        try {
            List<Address> androidTypeAddresses;
            if (region != null) {
                double[] regionSpan = new double[4];
                if (!ListenerUtil.mutListener.listen(7806)) {
                    RegionUtils.getRegionSpan(region, regionSpan);
                }
                double minLat = (ListenerUtil.mutListener.listen(7814) ? (regionSpan[2] % ((ListenerUtil.mutListener.listen(7810) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7809) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7808) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7807) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7813) ? (regionSpan[2] / ((ListenerUtil.mutListener.listen(7810) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7809) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7808) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7807) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7812) ? (regionSpan[2] * ((ListenerUtil.mutListener.listen(7810) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7809) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7808) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7807) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7811) ? (regionSpan[2] + ((ListenerUtil.mutListener.listen(7810) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7809) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7808) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7807) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (regionSpan[2] - ((ListenerUtil.mutListener.listen(7810) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7809) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7808) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7807) ? (regionSpan[0] + 2) : (regionSpan[0] / 2)))))))))));
                double minLon = (ListenerUtil.mutListener.listen(7822) ? (regionSpan[3] % ((ListenerUtil.mutListener.listen(7818) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7817) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7816) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7815) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7821) ? (regionSpan[3] / ((ListenerUtil.mutListener.listen(7818) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7817) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7816) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7815) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7820) ? (regionSpan[3] * ((ListenerUtil.mutListener.listen(7818) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7817) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7816) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7815) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7819) ? (regionSpan[3] + ((ListenerUtil.mutListener.listen(7818) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7817) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7816) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7815) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (regionSpan[3] - ((ListenerUtil.mutListener.listen(7818) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7817) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7816) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7815) ? (regionSpan[1] + 2) : (regionSpan[1] / 2)))))))))));
                double maxLat = (ListenerUtil.mutListener.listen(7830) ? (regionSpan[2] % ((ListenerUtil.mutListener.listen(7826) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7825) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7824) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7823) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7829) ? (regionSpan[2] / ((ListenerUtil.mutListener.listen(7826) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7825) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7824) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7823) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7828) ? (regionSpan[2] * ((ListenerUtil.mutListener.listen(7826) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7825) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7824) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7823) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7827) ? (regionSpan[2] - ((ListenerUtil.mutListener.listen(7826) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7825) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7824) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7823) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (regionSpan[2] + ((ListenerUtil.mutListener.listen(7826) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7825) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7824) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7823) ? (regionSpan[0] + 2) : (regionSpan[0] / 2)))))))))));
                double maxLon = (ListenerUtil.mutListener.listen(7838) ? (regionSpan[3] % ((ListenerUtil.mutListener.listen(7834) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7833) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7832) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7831) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7837) ? (regionSpan[3] / ((ListenerUtil.mutListener.listen(7834) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7833) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7832) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7831) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7836) ? (regionSpan[3] * ((ListenerUtil.mutListener.listen(7834) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7833) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7832) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7831) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7835) ? (regionSpan[3] - ((ListenerUtil.mutListener.listen(7834) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7833) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7832) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7831) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (regionSpan[3] + ((ListenerUtil.mutListener.listen(7834) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7833) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7832) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7831) ? (regionSpan[1] + 2) : (regionSpan[1] / 2)))))))))));
                androidTypeAddresses = gc.getFromLocationName(address, GEOCODER_MAX_RESULTS, minLat, minLon, maxLat, maxLon);
            } else {
                androidTypeAddresses = gc.getFromLocationName(address, GEOCODER_MAX_RESULTS);
            }
            if (!ListenerUtil.mutListener.listen(7840)) {
                {
                    long _loopCounter94 = 0;
                    for (Address androidTypeAddress : androidTypeAddresses) {
                        ListenerUtil.loopListener.listen("_loopCounter94", ++_loopCounter94);
                        if (!ListenerUtil.mutListener.listen(7839)) {
                            addresses.add(new CustomAddress(androidTypeAddress));
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(7805)) {
                e.printStackTrace();
            }
        }
        if (!ListenerUtil.mutListener.listen(7841)) {
            addresses = filterAddressesBBox(region, addresses);
        }
        boolean resultsCloseEnough = true;
        if (!ListenerUtil.mutListener.listen(7853)) {
            if ((ListenerUtil.mutListener.listen(7842) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet))) {
                float[] results = new float[1];
                if (!ListenerUtil.mutListener.listen(7843)) {
                    resultsCloseEnough = false;
                }
                if (!ListenerUtil.mutListener.listen(7852)) {
                    {
                        long _loopCounter95 = 0;
                        for (CustomAddress addressToCheck : addresses) {
                            ListenerUtil.loopListener.listen("_loopCounter95", ++_loopCounter95);
                            if (!ListenerUtil.mutListener.listen(7844)) {
                                Location.distanceBetween(latitude, longitude, addressToCheck.getLatitude(), addressToCheck.getLongitude(), results);
                            }
                            if (!ListenerUtil.mutListener.listen(7851)) {
                                if ((ListenerUtil.mutListener.listen(7849) ? (results[0] >= GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7848) ? (results[0] <= GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7847) ? (results[0] > GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7846) ? (results[0] != GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7845) ? (results[0] == GEOCODING_MAX_ERROR) : (results[0] < GEOCODING_MAX_ERROR))))))) {
                                    if (!ListenerUtil.mutListener.listen(7850)) {
                                        resultsCloseEnough = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7859)) {
            if ((ListenerUtil.mutListener.listen(7855) ? ((ListenerUtil.mutListener.listen(7854) ? ((addresses == null) && addresses.isEmpty()) : ((addresses == null) || addresses.isEmpty())) && !resultsCloseEnough) : ((ListenerUtil.mutListener.listen(7854) ? ((addresses == null) && addresses.isEmpty()) : ((addresses == null) || addresses.isEmpty())) || !resultsCloseEnough))) {
                if (!ListenerUtil.mutListener.listen(7857)) {
                    if (addresses == null) {
                        if (!ListenerUtil.mutListener.listen(7856)) {
                            addresses = new ArrayList<CustomAddress>();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7858)) {
                    Log.e(TAG, "Geocoder did not find enough addresses: " + addresses);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7860)) {
            addresses = filterAddressesBBox(region, addresses);
        }
        if (!ListenerUtil.mutListener.listen(7876)) {
            if ((ListenerUtil.mutListener.listen(7863) ? ((ListenerUtil.mutListener.listen(7862) ? ((ListenerUtil.mutListener.listen(7861) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) || addresses != null) : ((ListenerUtil.mutListener.listen(7861) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) && addresses != null)) || !addresses.isEmpty()) : ((ListenerUtil.mutListener.listen(7862) ? ((ListenerUtil.mutListener.listen(7861) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) || addresses != null) : ((ListenerUtil.mutListener.listen(7861) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) && addresses != null)) && !addresses.isEmpty()))) {
                float[] results = new float[1];
                float minDistanceToOriginalLatLon = Float.MAX_VALUE;
                CustomAddress closestAddress = addresses.get(0);
                if (!ListenerUtil.mutListener.listen(7874)) {
                    {
                        long _loopCounter96 = 0;
                        for (CustomAddress addressToCheck : addresses) {
                            ListenerUtil.loopListener.listen("_loopCounter96", ++_loopCounter96);
                            if (!ListenerUtil.mutListener.listen(7865)) {
                                Location.distanceBetween(latitude, longitude, addressToCheck.getLatitude(), addressToCheck.getLongitude(), results);
                            }
                            if (!ListenerUtil.mutListener.listen(7873)) {
                                if ((ListenerUtil.mutListener.listen(7870) ? (results[0] >= minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7869) ? (results[0] <= minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7868) ? (results[0] > minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7867) ? (results[0] != minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7866) ? (results[0] == minDistanceToOriginalLatLon) : (results[0] < minDistanceToOriginalLatLon))))))) {
                                    if (!ListenerUtil.mutListener.listen(7871)) {
                                        closestAddress = addressToCheck;
                                    }
                                    if (!ListenerUtil.mutListener.listen(7872)) {
                                        minDistanceToOriginalLatLon = results[0];
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7875)) {
                    addressesReturn.add(closestAddress);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7864)) {
                    addressesReturn.addAll(addresses);
                }
            }
        }
        return addressesReturn;
    }

    public static List<CustomAddress> processPeliasGeocoding(Context context, ObaRegion region, String... reqs) {
        return processPeliasGeocoding(context, region, false, reqs);
    }

    public static List<CustomAddress> processPeliasGeocoding(Context context, ObaRegion region, boolean geocodingForMarker, String... reqs) {
        ArrayList<CustomAddress> addressesReturn = new ArrayList<CustomAddress>();
        String address = reqs[0];
        if (!ListenerUtil.mutListener.listen(7878)) {
            if ((ListenerUtil.mutListener.listen(7877) ? (address == null && address.equalsIgnoreCase("")) : (address == null || address.equalsIgnoreCase("")))) {
                return null;
            }
        }
        double latitude = 0, longitude = 0;
        boolean latLngSet = false;
        try {
            if (!ListenerUtil.mutListener.listen(7888)) {
                if ((ListenerUtil.mutListener.listen(7884) ? (reqs.length <= 3) : (ListenerUtil.mutListener.listen(7883) ? (reqs.length > 3) : (ListenerUtil.mutListener.listen(7882) ? (reqs.length < 3) : (ListenerUtil.mutListener.listen(7881) ? (reqs.length != 3) : (ListenerUtil.mutListener.listen(7880) ? (reqs.length == 3) : (reqs.length >= 3))))))) {
                    if (!ListenerUtil.mutListener.listen(7885)) {
                        latitude = Double.parseDouble(reqs[1]);
                    }
                    if (!ListenerUtil.mutListener.listen(7886)) {
                        longitude = Double.parseDouble(reqs[2]);
                    }
                    if (!ListenerUtil.mutListener.listen(7887)) {
                        latLngSet = true;
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(7879)) {
                Log.e(TAG, "Geocoding without reference latitude/longitude");
            }
        }
        if (!ListenerUtil.mutListener.listen(7894)) {
            if (address.equalsIgnoreCase(context.getString(R.string.tripplanner_current_location))) {
                if (!ListenerUtil.mutListener.listen(7893)) {
                    if (latLngSet) {
                        CustomAddress addressReturn = new CustomAddress(context.getResources().getConfiguration().locale);
                        if (!ListenerUtil.mutListener.listen(7889)) {
                            addressReturn.setLatitude(latitude);
                        }
                        if (!ListenerUtil.mutListener.listen(7890)) {
                            addressReturn.setLongitude(longitude);
                        }
                        if (!ListenerUtil.mutListener.listen(7891)) {
                            addressReturn.setAddressLine(addressReturn.getMaxAddressLineIndex() + 1, context.getString(R.string.tripplanner_current_location));
                        }
                        if (!ListenerUtil.mutListener.listen(7892)) {
                            addressesReturn.add(addressReturn);
                        }
                        return addressesReturn;
                    }
                }
                return null;
            }
        }
        List<CustomAddress> addresses = new ArrayList<>();
        try {
            String apiKey = BuildConfig.PELIAS_API_KEY;
            PeliasRequest.Builder requestBuilder = new AutocompleteRequest.Builder(apiKey, address).setApiEndpoint(Application.get().getString(R.string.pelias_api_url));
            if (!ListenerUtil.mutListener.listen(7930)) {
                if (region != null) {
                    double[] regionSpan = new double[4];
                    if (!ListenerUtil.mutListener.listen(7896)) {
                        RegionUtils.getRegionSpan(region, regionSpan);
                    }
                    double minLat = (ListenerUtil.mutListener.listen(7904) ? (regionSpan[2] % ((ListenerUtil.mutListener.listen(7900) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7899) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7898) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7897) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7903) ? (regionSpan[2] / ((ListenerUtil.mutListener.listen(7900) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7899) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7898) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7897) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7902) ? (regionSpan[2] * ((ListenerUtil.mutListener.listen(7900) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7899) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7898) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7897) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7901) ? (regionSpan[2] + ((ListenerUtil.mutListener.listen(7900) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7899) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7898) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7897) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (regionSpan[2] - ((ListenerUtil.mutListener.listen(7900) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7899) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7898) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7897) ? (regionSpan[0] + 2) : (regionSpan[0] / 2)))))))))));
                    double minLon = (ListenerUtil.mutListener.listen(7912) ? (regionSpan[3] % ((ListenerUtil.mutListener.listen(7908) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7907) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7906) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7905) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7911) ? (regionSpan[3] / ((ListenerUtil.mutListener.listen(7908) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7907) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7906) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7905) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7910) ? (regionSpan[3] * ((ListenerUtil.mutListener.listen(7908) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7907) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7906) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7905) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7909) ? (regionSpan[3] + ((ListenerUtil.mutListener.listen(7908) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7907) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7906) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7905) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (regionSpan[3] - ((ListenerUtil.mutListener.listen(7908) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7907) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7906) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7905) ? (regionSpan[1] + 2) : (regionSpan[1] / 2)))))))))));
                    double maxLat = (ListenerUtil.mutListener.listen(7920) ? (regionSpan[2] % ((ListenerUtil.mutListener.listen(7916) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7915) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7914) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7913) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7919) ? (regionSpan[2] / ((ListenerUtil.mutListener.listen(7916) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7915) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7914) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7913) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7918) ? (regionSpan[2] * ((ListenerUtil.mutListener.listen(7916) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7915) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7914) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7913) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(7917) ? (regionSpan[2] - ((ListenerUtil.mutListener.listen(7916) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7915) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7914) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7913) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (regionSpan[2] + ((ListenerUtil.mutListener.listen(7916) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(7915) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(7914) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(7913) ? (regionSpan[0] + 2) : (regionSpan[0] / 2)))))))))));
                    double maxLon = (ListenerUtil.mutListener.listen(7928) ? (regionSpan[3] % ((ListenerUtil.mutListener.listen(7924) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7923) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7922) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7921) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7927) ? (regionSpan[3] / ((ListenerUtil.mutListener.listen(7924) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7923) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7922) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7921) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7926) ? (regionSpan[3] * ((ListenerUtil.mutListener.listen(7924) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7923) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7922) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7921) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(7925) ? (regionSpan[3] - ((ListenerUtil.mutListener.listen(7924) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7923) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7922) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7921) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (regionSpan[3] + ((ListenerUtil.mutListener.listen(7924) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(7923) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(7922) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(7921) ? (regionSpan[1] + 2) : (regionSpan[1] / 2)))))))))));
                    if (!ListenerUtil.mutListener.listen(7929)) {
                        requestBuilder.setBoundaryRect(minLat, minLon, maxLat, maxLon);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7931)) {
                // Includes categories so we know if it's transit-related
                requestBuilder.setCategories("");
            }
            // Call the Pelias API
            PeliasResponse response = requestBuilder.build().call();
            if (!ListenerUtil.mutListener.listen(7933)) {
                {
                    long _loopCounter97 = 0;
                    for (org.geojson.Feature feature : response.getFeatures()) {
                        ListenerUtil.loopListener.listen("_loopCounter97", ++_loopCounter97);
                        if (!ListenerUtil.mutListener.listen(7932)) {
                            addresses.add(new CustomAddress(feature));
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(7895)) {
                Log.e(TAG, e.toString());
            }
        }
        if (!ListenerUtil.mutListener.listen(7934)) {
            addresses = filterAddressesBBox(region, addresses);
        }
        boolean resultsCloseEnough = true;
        if (!ListenerUtil.mutListener.listen(7946)) {
            if ((ListenerUtil.mutListener.listen(7935) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet))) {
                float[] results = new float[1];
                if (!ListenerUtil.mutListener.listen(7936)) {
                    resultsCloseEnough = false;
                }
                if (!ListenerUtil.mutListener.listen(7945)) {
                    {
                        long _loopCounter98 = 0;
                        for (CustomAddress addressToCheck : addresses) {
                            ListenerUtil.loopListener.listen("_loopCounter98", ++_loopCounter98);
                            if (!ListenerUtil.mutListener.listen(7937)) {
                                Location.distanceBetween(latitude, longitude, addressToCheck.getLatitude(), addressToCheck.getLongitude(), results);
                            }
                            if (!ListenerUtil.mutListener.listen(7944)) {
                                if ((ListenerUtil.mutListener.listen(7942) ? (results[0] >= GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7941) ? (results[0] <= GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7940) ? (results[0] > GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7939) ? (results[0] != GEOCODING_MAX_ERROR) : (ListenerUtil.mutListener.listen(7938) ? (results[0] == GEOCODING_MAX_ERROR) : (results[0] < GEOCODING_MAX_ERROR))))))) {
                                    if (!ListenerUtil.mutListener.listen(7943)) {
                                        resultsCloseEnough = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7952)) {
            if ((ListenerUtil.mutListener.listen(7948) ? ((ListenerUtil.mutListener.listen(7947) ? ((addresses == null) && addresses.isEmpty()) : ((addresses == null) || addresses.isEmpty())) && !resultsCloseEnough) : ((ListenerUtil.mutListener.listen(7947) ? ((addresses == null) && addresses.isEmpty()) : ((addresses == null) || addresses.isEmpty())) || !resultsCloseEnough))) {
                if (!ListenerUtil.mutListener.listen(7950)) {
                    if (addresses == null) {
                        if (!ListenerUtil.mutListener.listen(7949)) {
                            addresses = new ArrayList<CustomAddress>();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7951)) {
                    Log.e(TAG, "Geocoder did not find enough addresses: " + addresses);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7953)) {
            addresses = filterAddressesBBox(region, addresses);
        }
        if (!ListenerUtil.mutListener.listen(7969)) {
            if ((ListenerUtil.mutListener.listen(7956) ? ((ListenerUtil.mutListener.listen(7955) ? ((ListenerUtil.mutListener.listen(7954) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) || addresses != null) : ((ListenerUtil.mutListener.listen(7954) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) && addresses != null)) || !addresses.isEmpty()) : ((ListenerUtil.mutListener.listen(7955) ? ((ListenerUtil.mutListener.listen(7954) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) || addresses != null) : ((ListenerUtil.mutListener.listen(7954) ? (geocodingForMarker || latLngSet) : (geocodingForMarker && latLngSet)) && addresses != null)) && !addresses.isEmpty()))) {
                float[] results = new float[1];
                float minDistanceToOriginalLatLon = Float.MAX_VALUE;
                CustomAddress closestAddress = addresses.get(0);
                if (!ListenerUtil.mutListener.listen(7967)) {
                    {
                        long _loopCounter99 = 0;
                        for (CustomAddress addressToCheck : addresses) {
                            ListenerUtil.loopListener.listen("_loopCounter99", ++_loopCounter99);
                            if (!ListenerUtil.mutListener.listen(7958)) {
                                Location.distanceBetween(latitude, longitude, addressToCheck.getLatitude(), addressToCheck.getLongitude(), results);
                            }
                            if (!ListenerUtil.mutListener.listen(7966)) {
                                if ((ListenerUtil.mutListener.listen(7963) ? (results[0] >= minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7962) ? (results[0] <= minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7961) ? (results[0] > minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7960) ? (results[0] != minDistanceToOriginalLatLon) : (ListenerUtil.mutListener.listen(7959) ? (results[0] == minDistanceToOriginalLatLon) : (results[0] < minDistanceToOriginalLatLon))))))) {
                                    if (!ListenerUtil.mutListener.listen(7964)) {
                                        closestAddress = addressToCheck;
                                    }
                                    if (!ListenerUtil.mutListener.listen(7965)) {
                                        minDistanceToOriginalLatLon = results[0];
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7968)) {
                    addressesReturn.add(closestAddress);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7957)) {
                    addressesReturn.addAll(addresses);
                }
            }
        }
        return addressesReturn;
    }

    /**
     * Filters the addresses obtained in geocoding process, removing the
     * results outside server limits.
     *
     * @param addresses list of addresses to filter
     * @return a new list filtered
     */
    private static List<CustomAddress> filterAddressesBBox(ObaRegion region, List<CustomAddress> addresses) {
        if (!ListenerUtil.mutListener.listen(7977)) {
            if ((ListenerUtil.mutListener.listen(7971) ? ((!((ListenerUtil.mutListener.listen(7970) ? (addresses == null && addresses.isEmpty()) : (addresses == null || addresses.isEmpty())))) || region != null) : ((!((ListenerUtil.mutListener.listen(7970) ? (addresses == null && addresses.isEmpty()) : (addresses == null || addresses.isEmpty())))) && region != null))) {
                if (!ListenerUtil.mutListener.listen(7976)) {
                    {
                        long _loopCounter100 = 0;
                        for (Iterator<CustomAddress> it = addresses.iterator(); it.hasNext(); ) {
                            ListenerUtil.loopListener.listen("_loopCounter100", ++_loopCounter100);
                            CustomAddress address = it.next();
                            Location loc = new Location("");
                            if (!ListenerUtil.mutListener.listen(7972)) {
                                loc.setLatitude(address.getLatitude());
                            }
                            if (!ListenerUtil.mutListener.listen(7973)) {
                                loc.setLongitude(address.getLongitude());
                            }
                            if (!ListenerUtil.mutListener.listen(7975)) {
                                if (!RegionUtils.isLocationWithinRegion(loc, region)) {
                                    if (!ListenerUtil.mutListener.listen(7974)) {
                                        it.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return addresses;
    }
}
