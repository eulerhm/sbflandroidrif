/*
 * Copyright (C) 2012-2017 Paul Watts (paulcwatts@gmail.com),
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
package org.onebusaway.android.util;

import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.io.elements.ObaRegionElement;
import org.onebusaway.android.io.request.ObaRegionsRequest;
import org.onebusaway.android.io.request.ObaRegionsResponse;
import org.onebusaway.android.provider.ObaContract;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A class containing utility methods related to handling multiple regions in OneBusAway
 */
public class RegionUtils {

    private static final String TAG = "RegionUtils";

    public static final int TAMPA_REGION_ID = 0;

    public static final int PUGET_SOUND_REGION_ID = 1;

    public static final int ATLANTA_REGION_ID = 3;

    public static final double METERS_TO_MILES = 0.000621371;

    // miles
    private static final int DISTANCE_LIMITER = 100;

    /**
     * Get the closest region from a list of regions and a given location
     *
     * This method also enforces the constraints in isRegionUsable() to
     * ensure the returned region is actually usable by the app
     *
     * @param regions list of regions
     * @param loc     location
     * @param enforceThreshold true if the DISTANCE_LIMITER threshold should be enforced, false if
     *                         it should not
     * @return the closest region to the given location from the list of regions, or null if a
     * enforceThreshold is true and the closest region exceeded DISTANCE_LIMITER threshold or a
     * region couldn't be found
     */
    public static ObaRegion getClosestRegion(ArrayList<ObaRegion> regions, Location loc, boolean enforceThreshold) {
        if (!ListenerUtil.mutListener.listen(6575)) {
            if (loc == null) {
                return null;
            }
        }
        float minDist = Float.MAX_VALUE;
        ObaRegion closestRegion = null;
        Float distToRegion;
        NumberFormat fmt = NumberFormat.getInstance();
        if (!ListenerUtil.mutListener.listen(6577)) {
            if (fmt instanceof DecimalFormat) {
                if (!ListenerUtil.mutListener.listen(6576)) {
                    ((DecimalFormat) fmt).setMaximumFractionDigits(1);
                }
            }
        }
        double miles;
        if (!ListenerUtil.mutListener.listen(6578)) {
            Log.d(TAG, "Finding region closest to " + loc.getLatitude() + "," + loc.getLongitude());
        }
        {
            long _loopCounter68 = 0;
            for (ObaRegion region : regions) {
                ListenerUtil.loopListener.listen("_loopCounter68", ++_loopCounter68);
                if (!ListenerUtil.mutListener.listen(6580)) {
                    if (!isRegionUsable(region)) {
                        if (!ListenerUtil.mutListener.listen(6579)) {
                            Log.d(TAG, "Excluding '" + region.getName() + "' from 'closest region' consideration");
                        }
                        continue;
                    }
                }
                distToRegion = getDistanceAway(region, loc.getLatitude(), loc.getLongitude());
                if (!ListenerUtil.mutListener.listen(6582)) {
                    if (distToRegion == null) {
                        if (!ListenerUtil.mutListener.listen(6581)) {
                            Log.e(TAG, "Couldn't measure distance to region '" + region.getName() + "'");
                        }
                        continue;
                    }
                }
                miles = (ListenerUtil.mutListener.listen(6586) ? (distToRegion % METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6585) ? (distToRegion / METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6584) ? (distToRegion - METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6583) ? (distToRegion + METERS_TO_MILES) : (distToRegion * METERS_TO_MILES)))));
                if (!ListenerUtil.mutListener.listen(6587)) {
                    Log.d(TAG, "Region '" + region.getName() + "' is " + fmt.format(miles) + " miles away");
                }
                if (!ListenerUtil.mutListener.listen(6595)) {
                    if ((ListenerUtil.mutListener.listen(6592) ? (distToRegion >= minDist) : (ListenerUtil.mutListener.listen(6591) ? (distToRegion <= minDist) : (ListenerUtil.mutListener.listen(6590) ? (distToRegion > minDist) : (ListenerUtil.mutListener.listen(6589) ? (distToRegion != minDist) : (ListenerUtil.mutListener.listen(6588) ? (distToRegion == minDist) : (distToRegion < minDist))))))) {
                        if (!ListenerUtil.mutListener.listen(6593)) {
                            closestRegion = region;
                        }
                        if (!ListenerUtil.mutListener.listen(6594)) {
                            minDist = distToRegion;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6606)) {
            if (enforceThreshold) {
                if (!ListenerUtil.mutListener.listen(6605)) {
                    if ((ListenerUtil.mutListener.listen(6604) ? ((ListenerUtil.mutListener.listen(6599) ? (minDist % METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6598) ? (minDist / METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6597) ? (minDist - METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6596) ? (minDist + METERS_TO_MILES) : (minDist * METERS_TO_MILES))))) >= DISTANCE_LIMITER) : (ListenerUtil.mutListener.listen(6603) ? ((ListenerUtil.mutListener.listen(6599) ? (minDist % METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6598) ? (minDist / METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6597) ? (minDist - METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6596) ? (minDist + METERS_TO_MILES) : (minDist * METERS_TO_MILES))))) <= DISTANCE_LIMITER) : (ListenerUtil.mutListener.listen(6602) ? ((ListenerUtil.mutListener.listen(6599) ? (minDist % METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6598) ? (minDist / METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6597) ? (minDist - METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6596) ? (minDist + METERS_TO_MILES) : (minDist * METERS_TO_MILES))))) > DISTANCE_LIMITER) : (ListenerUtil.mutListener.listen(6601) ? ((ListenerUtil.mutListener.listen(6599) ? (minDist % METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6598) ? (minDist / METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6597) ? (minDist - METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6596) ? (minDist + METERS_TO_MILES) : (minDist * METERS_TO_MILES))))) != DISTANCE_LIMITER) : (ListenerUtil.mutListener.listen(6600) ? ((ListenerUtil.mutListener.listen(6599) ? (minDist % METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6598) ? (minDist / METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6597) ? (minDist - METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6596) ? (minDist + METERS_TO_MILES) : (minDist * METERS_TO_MILES))))) == DISTANCE_LIMITER) : ((ListenerUtil.mutListener.listen(6599) ? (minDist % METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6598) ? (minDist / METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6597) ? (minDist - METERS_TO_MILES) : (ListenerUtil.mutListener.listen(6596) ? (minDist + METERS_TO_MILES) : (minDist * METERS_TO_MILES))))) < DISTANCE_LIMITER))))))) {
                        return closestRegion;
                    } else {
                        return null;
                    }
                }
            }
        }
        return closestRegion;
    }

    /**
     * Get the region name if it is available. If there is a custom url instead of a region from
     * the region api, then hash the custom url and return it.
     *
     * @return regionName
     */
    public static String getObaRegionName() {
        String regionName = null;
        ObaRegion region = Application.get().getCurrentRegion();
        if (!ListenerUtil.mutListener.listen(6610)) {
            if ((ListenerUtil.mutListener.listen(6607) ? (region != null || region.getName() != null) : (region != null && region.getName() != null))) {
                if (!ListenerUtil.mutListener.listen(6609)) {
                    regionName = region.getName();
                }
            } else if (Application.get().getCustomApiUrl() != null) {
                if (!ListenerUtil.mutListener.listen(6608)) {
                    regionName = createHashCode(Application.get().getCustomApiUrl().getBytes());
                }
            }
        }
        return regionName;
    }

    private static String createHashCode(byte[] bytes) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            if (!ListenerUtil.mutListener.listen(6611)) {
                digest.update(bytes);
            }
            return Application.get().getString(R.string.analytics_label_custom_url) + ": " + Application.getHex(digest.digest());
        } catch (Exception e) {
            return Application.get().getString(R.string.analytics_label_custom_url);
        }
    }

    /**
     * Returns the distance from the specified location
     * to the center of the closest bound in this region.
     *
     * @return distance from the specified location to the center of the closest bound in this
     * region, in meters
     */
    public static Float getDistanceAway(ObaRegion region, double lat, double lon) {
        ObaRegion.Bounds[] bounds = region.getBounds();
        if (!ListenerUtil.mutListener.listen(6612)) {
            if (bounds == null) {
                return null;
            }
        }
        float[] results = new float[1];
        float minDistance = Float.MAX_VALUE;
        if (!ListenerUtil.mutListener.listen(6621)) {
            {
                long _loopCounter69 = 0;
                for (ObaRegion.Bounds bound : bounds) {
                    ListenerUtil.loopListener.listen("_loopCounter69", ++_loopCounter69);
                    if (!ListenerUtil.mutListener.listen(6613)) {
                        Location.distanceBetween(lat, lon, bound.getLat(), bound.getLon(), results);
                    }
                    if (!ListenerUtil.mutListener.listen(6620)) {
                        if ((ListenerUtil.mutListener.listen(6618) ? (results[0] >= minDistance) : (ListenerUtil.mutListener.listen(6617) ? (results[0] <= minDistance) : (ListenerUtil.mutListener.listen(6616) ? (results[0] > minDistance) : (ListenerUtil.mutListener.listen(6615) ? (results[0] != minDistance) : (ListenerUtil.mutListener.listen(6614) ? (results[0] == minDistance) : (results[0] < minDistance))))))) {
                            if (!ListenerUtil.mutListener.listen(6619)) {
                                minDistance = results[0];
                            }
                        }
                    }
                }
            }
        }
        return minDistance;
    }

    public static Float getDistanceAway(ObaRegion region, Location loc) {
        return getDistanceAway(region, loc.getLatitude(), loc.getLongitude());
    }

    /**
     * Returns the center and lat/lon span for the entire region.
     *
     * @param results Array to receive results.
     *                results[0] == latSpan of region
     *                results[1] == lonSpan of region
     *                results[2] == lat center of region
     *                results[3] == lon center of region
     */
    public static void getRegionSpan(ObaRegion region, double[] results) {
        if (!ListenerUtil.mutListener.listen(6627)) {
            if ((ListenerUtil.mutListener.listen(6626) ? (results.length >= 4) : (ListenerUtil.mutListener.listen(6625) ? (results.length <= 4) : (ListenerUtil.mutListener.listen(6624) ? (results.length > 4) : (ListenerUtil.mutListener.listen(6623) ? (results.length != 4) : (ListenerUtil.mutListener.listen(6622) ? (results.length == 4) : (results.length < 4))))))) {
                throw new IllegalArgumentException("Results array is < 4");
            }
        }
        if (!ListenerUtil.mutListener.listen(6628)) {
            if (region == null) {
                throw new IllegalArgumentException("Region is null");
            }
        }
        double latMin = 90;
        double latMax = -90;
        double lonMin = 180;
        double lonMax = -180;
        if (!ListenerUtil.mutListener.listen(6681)) {
            {
                long _loopCounter70 = 0;
                // This is fairly simplistic
                for (ObaRegion.Bounds bound : region.getBounds()) {
                    ListenerUtil.loopListener.listen("_loopCounter70", ++_loopCounter70);
                    // Get the top bound
                    double lat = bound.getLat();
                    double latSpanHalf = (ListenerUtil.mutListener.listen(6632) ? (bound.getLatSpan() % 2.0) : (ListenerUtil.mutListener.listen(6631) ? (bound.getLatSpan() * 2.0) : (ListenerUtil.mutListener.listen(6630) ? (bound.getLatSpan() - 2.0) : (ListenerUtil.mutListener.listen(6629) ? (bound.getLatSpan() + 2.0) : (bound.getLatSpan() / 2.0)))));
                    double lat1 = (ListenerUtil.mutListener.listen(6636) ? (lat % latSpanHalf) : (ListenerUtil.mutListener.listen(6635) ? (lat / latSpanHalf) : (ListenerUtil.mutListener.listen(6634) ? (lat * latSpanHalf) : (ListenerUtil.mutListener.listen(6633) ? (lat + latSpanHalf) : (lat - latSpanHalf)))));
                    double lat2 = (ListenerUtil.mutListener.listen(6640) ? (lat % latSpanHalf) : (ListenerUtil.mutListener.listen(6639) ? (lat / latSpanHalf) : (ListenerUtil.mutListener.listen(6638) ? (lat * latSpanHalf) : (ListenerUtil.mutListener.listen(6637) ? (lat - latSpanHalf) : (lat + latSpanHalf)))));
                    if (!ListenerUtil.mutListener.listen(6647)) {
                        if ((ListenerUtil.mutListener.listen(6645) ? (lat1 >= latMin) : (ListenerUtil.mutListener.listen(6644) ? (lat1 <= latMin) : (ListenerUtil.mutListener.listen(6643) ? (lat1 > latMin) : (ListenerUtil.mutListener.listen(6642) ? (lat1 != latMin) : (ListenerUtil.mutListener.listen(6641) ? (lat1 == latMin) : (lat1 < latMin))))))) {
                            if (!ListenerUtil.mutListener.listen(6646)) {
                                latMin = lat1;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6654)) {
                        if ((ListenerUtil.mutListener.listen(6652) ? (lat2 >= latMax) : (ListenerUtil.mutListener.listen(6651) ? (lat2 <= latMax) : (ListenerUtil.mutListener.listen(6650) ? (lat2 < latMax) : (ListenerUtil.mutListener.listen(6649) ? (lat2 != latMax) : (ListenerUtil.mutListener.listen(6648) ? (lat2 == latMax) : (lat2 > latMax))))))) {
                            if (!ListenerUtil.mutListener.listen(6653)) {
                                latMax = lat2;
                            }
                        }
                    }
                    double lon = bound.getLon();
                    double lonSpanHalf = (ListenerUtil.mutListener.listen(6658) ? (bound.getLonSpan() % 2.0) : (ListenerUtil.mutListener.listen(6657) ? (bound.getLonSpan() * 2.0) : (ListenerUtil.mutListener.listen(6656) ? (bound.getLonSpan() - 2.0) : (ListenerUtil.mutListener.listen(6655) ? (bound.getLonSpan() + 2.0) : (bound.getLonSpan() / 2.0)))));
                    double lon1 = (ListenerUtil.mutListener.listen(6662) ? (lon % lonSpanHalf) : (ListenerUtil.mutListener.listen(6661) ? (lon / lonSpanHalf) : (ListenerUtil.mutListener.listen(6660) ? (lon * lonSpanHalf) : (ListenerUtil.mutListener.listen(6659) ? (lon + lonSpanHalf) : (lon - lonSpanHalf)))));
                    double lon2 = (ListenerUtil.mutListener.listen(6666) ? (lon % lonSpanHalf) : (ListenerUtil.mutListener.listen(6665) ? (lon / lonSpanHalf) : (ListenerUtil.mutListener.listen(6664) ? (lon * lonSpanHalf) : (ListenerUtil.mutListener.listen(6663) ? (lon - lonSpanHalf) : (lon + lonSpanHalf)))));
                    if (!ListenerUtil.mutListener.listen(6673)) {
                        if ((ListenerUtil.mutListener.listen(6671) ? (lon1 >= lonMin) : (ListenerUtil.mutListener.listen(6670) ? (lon1 <= lonMin) : (ListenerUtil.mutListener.listen(6669) ? (lon1 > lonMin) : (ListenerUtil.mutListener.listen(6668) ? (lon1 != lonMin) : (ListenerUtil.mutListener.listen(6667) ? (lon1 == lonMin) : (lon1 < lonMin))))))) {
                            if (!ListenerUtil.mutListener.listen(6672)) {
                                lonMin = lon1;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6680)) {
                        if ((ListenerUtil.mutListener.listen(6678) ? (lon2 >= lonMax) : (ListenerUtil.mutListener.listen(6677) ? (lon2 <= lonMax) : (ListenerUtil.mutListener.listen(6676) ? (lon2 < lonMax) : (ListenerUtil.mutListener.listen(6675) ? (lon2 != lonMax) : (ListenerUtil.mutListener.listen(6674) ? (lon2 == lonMax) : (lon2 > lonMax))))))) {
                            if (!ListenerUtil.mutListener.listen(6679)) {
                                lonMax = lon2;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6686)) {
            results[0] = (ListenerUtil.mutListener.listen(6685) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6684) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6683) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6682) ? (latMax + latMin) : (latMax - latMin)))));
        }
        if (!ListenerUtil.mutListener.listen(6691)) {
            results[1] = (ListenerUtil.mutListener.listen(6690) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6689) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6688) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6687) ? (lonMax + lonMin) : (lonMax - lonMin)))));
        }
        if (!ListenerUtil.mutListener.listen(6704)) {
            results[2] = (ListenerUtil.mutListener.listen(6703) ? (latMin % ((ListenerUtil.mutListener.listen(6699) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6698) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6697) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6696) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) / 2.0))))))) : (ListenerUtil.mutListener.listen(6702) ? (latMin / ((ListenerUtil.mutListener.listen(6699) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6698) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6697) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6696) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) / 2.0))))))) : (ListenerUtil.mutListener.listen(6701) ? (latMin * ((ListenerUtil.mutListener.listen(6699) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6698) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6697) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6696) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) / 2.0))))))) : (ListenerUtil.mutListener.listen(6700) ? (latMin - ((ListenerUtil.mutListener.listen(6699) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6698) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6697) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6696) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) / 2.0))))))) : (latMin + ((ListenerUtil.mutListener.listen(6699) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6698) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6697) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6696) ? (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6695) ? (latMax % latMin) : (ListenerUtil.mutListener.listen(6694) ? (latMax / latMin) : (ListenerUtil.mutListener.listen(6693) ? (latMax * latMin) : (ListenerUtil.mutListener.listen(6692) ? (latMax + latMin) : (latMax - latMin)))))) / 2.0)))))))))));
        }
        if (!ListenerUtil.mutListener.listen(6717)) {
            results[3] = (ListenerUtil.mutListener.listen(6716) ? (lonMin % ((ListenerUtil.mutListener.listen(6712) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6711) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6710) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6709) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) / 2.0))))))) : (ListenerUtil.mutListener.listen(6715) ? (lonMin / ((ListenerUtil.mutListener.listen(6712) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6711) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6710) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6709) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) / 2.0))))))) : (ListenerUtil.mutListener.listen(6714) ? (lonMin * ((ListenerUtil.mutListener.listen(6712) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6711) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6710) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6709) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) / 2.0))))))) : (ListenerUtil.mutListener.listen(6713) ? (lonMin - ((ListenerUtil.mutListener.listen(6712) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6711) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6710) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6709) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) / 2.0))))))) : (lonMin + ((ListenerUtil.mutListener.listen(6712) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) % 2.0) : (ListenerUtil.mutListener.listen(6711) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) * 2.0) : (ListenerUtil.mutListener.listen(6710) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) - 2.0) : (ListenerUtil.mutListener.listen(6709) ? (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) + 2.0) : (((ListenerUtil.mutListener.listen(6708) ? (lonMax % lonMin) : (ListenerUtil.mutListener.listen(6707) ? (lonMax / lonMin) : (ListenerUtil.mutListener.listen(6706) ? (lonMax * lonMin) : (ListenerUtil.mutListener.listen(6705) ? (lonMax + lonMin) : (lonMax - lonMin)))))) / 2.0)))))))))));
        }
    }

    /**
     * Determines if the provided location is within the provided region span
     *
     * Note: This does not handle cases when the region span crosses the
     * International Date Line properly
     *
     * @param location   that will be compared to the provided regionSpan
     * @param regionSpan span information for the region
     *                   regionSpan[0] == latSpan of region
     *                   regionSpan[1] == lonSpan of region
     *                   regionSpan[2] == lat center of region
     *                   regionSpan[3] == lon center of region
     * @return true if the location is within the region span, false if it is not
     */
    public static boolean isLocationWithinRegion(Location location, double[] regionSpan) {
        if (!ListenerUtil.mutListener.listen(6724)) {
            if ((ListenerUtil.mutListener.listen(6723) ? (regionSpan == null && (ListenerUtil.mutListener.listen(6722) ? (regionSpan.length >= 4) : (ListenerUtil.mutListener.listen(6721) ? (regionSpan.length <= 4) : (ListenerUtil.mutListener.listen(6720) ? (regionSpan.length > 4) : (ListenerUtil.mutListener.listen(6719) ? (regionSpan.length != 4) : (ListenerUtil.mutListener.listen(6718) ? (regionSpan.length == 4) : (regionSpan.length < 4))))))) : (regionSpan == null || (ListenerUtil.mutListener.listen(6722) ? (regionSpan.length >= 4) : (ListenerUtil.mutListener.listen(6721) ? (regionSpan.length <= 4) : (ListenerUtil.mutListener.listen(6720) ? (regionSpan.length > 4) : (ListenerUtil.mutListener.listen(6719) ? (regionSpan.length != 4) : (ListenerUtil.mutListener.listen(6718) ? (regionSpan.length == 4) : (regionSpan.length < 4))))))))) {
                throw new IllegalArgumentException("regionSpan is null or has length < 4");
            }
        }
        if (!ListenerUtil.mutListener.listen(6749)) {
            if ((ListenerUtil.mutListener.listen(6748) ? ((ListenerUtil.mutListener.listen(6742) ? ((ListenerUtil.mutListener.listen(6736) ? ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) && (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0))))))) : ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) || (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0)))))))) && (ListenerUtil.mutListener.listen(6741) ? (location.getLatitude() >= 90) : (ListenerUtil.mutListener.listen(6740) ? (location.getLatitude() <= 90) : (ListenerUtil.mutListener.listen(6739) ? (location.getLatitude() < 90) : (ListenerUtil.mutListener.listen(6738) ? (location.getLatitude() != 90) : (ListenerUtil.mutListener.listen(6737) ? (location.getLatitude() == 90) : (location.getLatitude() > 90))))))) : ((ListenerUtil.mutListener.listen(6736) ? ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) && (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0))))))) : ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) || (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0)))))))) || (ListenerUtil.mutListener.listen(6741) ? (location.getLatitude() >= 90) : (ListenerUtil.mutListener.listen(6740) ? (location.getLatitude() <= 90) : (ListenerUtil.mutListener.listen(6739) ? (location.getLatitude() < 90) : (ListenerUtil.mutListener.listen(6738) ? (location.getLatitude() != 90) : (ListenerUtil.mutListener.listen(6737) ? (location.getLatitude() == 90) : (location.getLatitude() > 90)))))))) && (ListenerUtil.mutListener.listen(6747) ? (location.getLatitude() >= -90) : (ListenerUtil.mutListener.listen(6746) ? (location.getLatitude() <= -90) : (ListenerUtil.mutListener.listen(6745) ? (location.getLatitude() > -90) : (ListenerUtil.mutListener.listen(6744) ? (location.getLatitude() != -90) : (ListenerUtil.mutListener.listen(6743) ? (location.getLatitude() == -90) : (location.getLatitude() < -90))))))) : ((ListenerUtil.mutListener.listen(6742) ? ((ListenerUtil.mutListener.listen(6736) ? ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) && (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0))))))) : ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) || (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0)))))))) && (ListenerUtil.mutListener.listen(6741) ? (location.getLatitude() >= 90) : (ListenerUtil.mutListener.listen(6740) ? (location.getLatitude() <= 90) : (ListenerUtil.mutListener.listen(6739) ? (location.getLatitude() < 90) : (ListenerUtil.mutListener.listen(6738) ? (location.getLatitude() != 90) : (ListenerUtil.mutListener.listen(6737) ? (location.getLatitude() == 90) : (location.getLatitude() > 90))))))) : ((ListenerUtil.mutListener.listen(6736) ? ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) && (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0))))))) : ((ListenerUtil.mutListener.listen(6730) ? (location == null && (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0))))))) : (location == null || (ListenerUtil.mutListener.listen(6729) ? (location.getLongitude() >= 180.0) : (ListenerUtil.mutListener.listen(6728) ? (location.getLongitude() <= 180.0) : (ListenerUtil.mutListener.listen(6727) ? (location.getLongitude() < 180.0) : (ListenerUtil.mutListener.listen(6726) ? (location.getLongitude() != 180.0) : (ListenerUtil.mutListener.listen(6725) ? (location.getLongitude() == 180.0) : (location.getLongitude() > 180.0)))))))) || (ListenerUtil.mutListener.listen(6735) ? (location.getLongitude() >= -180.0) : (ListenerUtil.mutListener.listen(6734) ? (location.getLongitude() <= -180.0) : (ListenerUtil.mutListener.listen(6733) ? (location.getLongitude() > -180.0) : (ListenerUtil.mutListener.listen(6732) ? (location.getLongitude() != -180.0) : (ListenerUtil.mutListener.listen(6731) ? (location.getLongitude() == -180.0) : (location.getLongitude() < -180.0)))))))) || (ListenerUtil.mutListener.listen(6741) ? (location.getLatitude() >= 90) : (ListenerUtil.mutListener.listen(6740) ? (location.getLatitude() <= 90) : (ListenerUtil.mutListener.listen(6739) ? (location.getLatitude() < 90) : (ListenerUtil.mutListener.listen(6738) ? (location.getLatitude() != 90) : (ListenerUtil.mutListener.listen(6737) ? (location.getLatitude() == 90) : (location.getLatitude() > 90)))))))) || (ListenerUtil.mutListener.listen(6747) ? (location.getLatitude() >= -90) : (ListenerUtil.mutListener.listen(6746) ? (location.getLatitude() <= -90) : (ListenerUtil.mutListener.listen(6745) ? (location.getLatitude() > -90) : (ListenerUtil.mutListener.listen(6744) ? (location.getLatitude() != -90) : (ListenerUtil.mutListener.listen(6743) ? (location.getLatitude() == -90) : (location.getLatitude() < -90))))))))) {
                throw new IllegalArgumentException("Location must be a valid location");
            }
        }
        double minLat = (ListenerUtil.mutListener.listen(6757) ? (regionSpan[2] % ((ListenerUtil.mutListener.listen(6753) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6752) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6751) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6750) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(6756) ? (regionSpan[2] / ((ListenerUtil.mutListener.listen(6753) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6752) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6751) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6750) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(6755) ? (regionSpan[2] * ((ListenerUtil.mutListener.listen(6753) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6752) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6751) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6750) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(6754) ? (regionSpan[2] + ((ListenerUtil.mutListener.listen(6753) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6752) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6751) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6750) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (regionSpan[2] - ((ListenerUtil.mutListener.listen(6753) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6752) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6751) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6750) ? (regionSpan[0] + 2) : (regionSpan[0] / 2)))))))))));
        double minLon = (ListenerUtil.mutListener.listen(6765) ? (regionSpan[3] % ((ListenerUtil.mutListener.listen(6761) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6760) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6759) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6758) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(6764) ? (regionSpan[3] / ((ListenerUtil.mutListener.listen(6761) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6760) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6759) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6758) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(6763) ? (regionSpan[3] * ((ListenerUtil.mutListener.listen(6761) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6760) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6759) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6758) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(6762) ? (regionSpan[3] + ((ListenerUtil.mutListener.listen(6761) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6760) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6759) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6758) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (regionSpan[3] - ((ListenerUtil.mutListener.listen(6761) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6760) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6759) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6758) ? (regionSpan[1] + 2) : (regionSpan[1] / 2)))))))))));
        double maxLat = (ListenerUtil.mutListener.listen(6773) ? (regionSpan[2] % ((ListenerUtil.mutListener.listen(6769) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6768) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6767) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6766) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(6772) ? (regionSpan[2] / ((ListenerUtil.mutListener.listen(6769) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6768) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6767) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6766) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(6771) ? (regionSpan[2] * ((ListenerUtil.mutListener.listen(6769) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6768) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6767) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6766) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (ListenerUtil.mutListener.listen(6770) ? (regionSpan[2] - ((ListenerUtil.mutListener.listen(6769) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6768) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6767) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6766) ? (regionSpan[0] + 2) : (regionSpan[0] / 2))))))) : (regionSpan[2] + ((ListenerUtil.mutListener.listen(6769) ? (regionSpan[0] % 2) : (ListenerUtil.mutListener.listen(6768) ? (regionSpan[0] * 2) : (ListenerUtil.mutListener.listen(6767) ? (regionSpan[0] - 2) : (ListenerUtil.mutListener.listen(6766) ? (regionSpan[0] + 2) : (regionSpan[0] / 2)))))))))));
        double maxLon = (ListenerUtil.mutListener.listen(6781) ? (regionSpan[3] % ((ListenerUtil.mutListener.listen(6777) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6776) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6775) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6774) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(6780) ? (regionSpan[3] / ((ListenerUtil.mutListener.listen(6777) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6776) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6775) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6774) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(6779) ? (regionSpan[3] * ((ListenerUtil.mutListener.listen(6777) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6776) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6775) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6774) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (ListenerUtil.mutListener.listen(6778) ? (regionSpan[3] - ((ListenerUtil.mutListener.listen(6777) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6776) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6775) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6774) ? (regionSpan[1] + 2) : (regionSpan[1] / 2))))))) : (regionSpan[3] + ((ListenerUtil.mutListener.listen(6777) ? (regionSpan[1] % 2) : (ListenerUtil.mutListener.listen(6776) ? (regionSpan[1] * 2) : (ListenerUtil.mutListener.listen(6775) ? (regionSpan[1] - 2) : (ListenerUtil.mutListener.listen(6774) ? (regionSpan[1] + 2) : (regionSpan[1] / 2)))))))))));
        return (ListenerUtil.mutListener.listen(6804) ? ((ListenerUtil.mutListener.listen(6798) ? ((ListenerUtil.mutListener.listen(6792) ? ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) || (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat))))))) : ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) && (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat)))))))) || (ListenerUtil.mutListener.listen(6797) ? (minLon >= location.getLongitude()) : (ListenerUtil.mutListener.listen(6796) ? (minLon > location.getLongitude()) : (ListenerUtil.mutListener.listen(6795) ? (minLon < location.getLongitude()) : (ListenerUtil.mutListener.listen(6794) ? (minLon != location.getLongitude()) : (ListenerUtil.mutListener.listen(6793) ? (minLon == location.getLongitude()) : (minLon <= location.getLongitude()))))))) : ((ListenerUtil.mutListener.listen(6792) ? ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) || (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat))))))) : ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) && (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat)))))))) && (ListenerUtil.mutListener.listen(6797) ? (minLon >= location.getLongitude()) : (ListenerUtil.mutListener.listen(6796) ? (minLon > location.getLongitude()) : (ListenerUtil.mutListener.listen(6795) ? (minLon < location.getLongitude()) : (ListenerUtil.mutListener.listen(6794) ? (minLon != location.getLongitude()) : (ListenerUtil.mutListener.listen(6793) ? (minLon == location.getLongitude()) : (minLon <= location.getLongitude())))))))) || (ListenerUtil.mutListener.listen(6803) ? (location.getLongitude() >= maxLon) : (ListenerUtil.mutListener.listen(6802) ? (location.getLongitude() > maxLon) : (ListenerUtil.mutListener.listen(6801) ? (location.getLongitude() < maxLon) : (ListenerUtil.mutListener.listen(6800) ? (location.getLongitude() != maxLon) : (ListenerUtil.mutListener.listen(6799) ? (location.getLongitude() == maxLon) : (location.getLongitude() <= maxLon))))))) : ((ListenerUtil.mutListener.listen(6798) ? ((ListenerUtil.mutListener.listen(6792) ? ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) || (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat))))))) : ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) && (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat)))))))) || (ListenerUtil.mutListener.listen(6797) ? (minLon >= location.getLongitude()) : (ListenerUtil.mutListener.listen(6796) ? (minLon > location.getLongitude()) : (ListenerUtil.mutListener.listen(6795) ? (minLon < location.getLongitude()) : (ListenerUtil.mutListener.listen(6794) ? (minLon != location.getLongitude()) : (ListenerUtil.mutListener.listen(6793) ? (minLon == location.getLongitude()) : (minLon <= location.getLongitude()))))))) : ((ListenerUtil.mutListener.listen(6792) ? ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) || (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat))))))) : ((ListenerUtil.mutListener.listen(6786) ? (minLat >= location.getLatitude()) : (ListenerUtil.mutListener.listen(6785) ? (minLat > location.getLatitude()) : (ListenerUtil.mutListener.listen(6784) ? (minLat < location.getLatitude()) : (ListenerUtil.mutListener.listen(6783) ? (minLat != location.getLatitude()) : (ListenerUtil.mutListener.listen(6782) ? (minLat == location.getLatitude()) : (minLat <= location.getLatitude())))))) && (ListenerUtil.mutListener.listen(6791) ? (location.getLatitude() >= maxLat) : (ListenerUtil.mutListener.listen(6790) ? (location.getLatitude() > maxLat) : (ListenerUtil.mutListener.listen(6789) ? (location.getLatitude() < maxLat) : (ListenerUtil.mutListener.listen(6788) ? (location.getLatitude() != maxLat) : (ListenerUtil.mutListener.listen(6787) ? (location.getLatitude() == maxLat) : (location.getLatitude() <= maxLat)))))))) && (ListenerUtil.mutListener.listen(6797) ? (minLon >= location.getLongitude()) : (ListenerUtil.mutListener.listen(6796) ? (minLon > location.getLongitude()) : (ListenerUtil.mutListener.listen(6795) ? (minLon < location.getLongitude()) : (ListenerUtil.mutListener.listen(6794) ? (minLon != location.getLongitude()) : (ListenerUtil.mutListener.listen(6793) ? (minLon == location.getLongitude()) : (minLon <= location.getLongitude())))))))) && (ListenerUtil.mutListener.listen(6803) ? (location.getLongitude() >= maxLon) : (ListenerUtil.mutListener.listen(6802) ? (location.getLongitude() > maxLon) : (ListenerUtil.mutListener.listen(6801) ? (location.getLongitude() < maxLon) : (ListenerUtil.mutListener.listen(6800) ? (location.getLongitude() != maxLon) : (ListenerUtil.mutListener.listen(6799) ? (location.getLongitude() == maxLon) : (location.getLongitude() <= maxLon))))))));
    }

    /**
     * Determines if the provided location is within the provided region
     *
     * Note: This does not handle cases when the region span crosses the
     * International Date Line properly
     *
     * @param location that will be compared to the provided region
     * @param region   provided region
     * @return true if the location is within the region, false if it is not
     */
    public static boolean isLocationWithinRegion(Location location, ObaRegion region) {
        double[] regionSpan = new double[4];
        if (!ListenerUtil.mutListener.listen(6805)) {
            getRegionSpan(region, regionSpan);
        }
        return isLocationWithinRegion(location, regionSpan);
    }

    /**
     * Checks if the given region is usable by the app, based on what this app supports
     * - Is the region active?
     * - Does the region support the OBA Discovery APIs?
     * - Does the region support the OBA Realtime APIs?
     * - Is the region experimental, and if so, did the user opt-in via preferences?
     *
     * @param region region to be checked
     * @return true if the region is usable by this application, false if it is not
     */
    public static boolean isRegionUsable(ObaRegion region) {
        if (!ListenerUtil.mutListener.listen(6807)) {
            if (!region.getActive()) {
                if (!ListenerUtil.mutListener.listen(6806)) {
                    Log.d(TAG, "Region '" + region.getName() + "' is not active.");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(6809)) {
            if (!region.getSupportsObaDiscoveryApis()) {
                if (!ListenerUtil.mutListener.listen(6808)) {
                    Log.d(TAG, "Region '" + region.getName() + "' does not support OBA Discovery APIs.");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(6811)) {
            if (!region.getSupportsObaRealtimeApis()) {
                if (!ListenerUtil.mutListener.listen(6810)) {
                    Log.d(TAG, "Region '" + region.getName() + "' does not support OBA Realtime APIs.");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(6814)) {
            if ((ListenerUtil.mutListener.listen(6812) ? (region.getExperimental() || !Application.getPrefs().getBoolean(Application.get().getString(R.string.preference_key_experimental_regions), false)) : (region.getExperimental() && !Application.getPrefs().getBoolean(Application.get().getString(R.string.preference_key_experimental_regions), false)))) {
                if (!ListenerUtil.mutListener.listen(6813)) {
                    Log.d(TAG, "Region '" + region.getName() + "' is experimental and user hasn't opted in.");
                }
                return false;
            }
        }
        return true;
    }

    /**
     * Format the OTP base URL so query parameters can be added safely.
     *
     * @param baseUrl OpenTripPlanner base URL from the Region
     * @return OTP server URL with trailing slash trimmed.
     */
    public static String formatOtpBaseUrl(String baseUrl) {
        return baseUrl.replaceFirst("/$", "");
    }

    /**
     * Gets regions from either the server, local provider, or if both fails the regions file
     * packaged
     * with the APK.  Includes fail-over logic to prefer sources in above order, with server being
     * the first preference.
     *
     * @param forceReload true if a reload from the server should be forced, false if it should not
     * @return a list of regions from either the server, the local provider, or the packaged
     * resource file
     */
    public static synchronized ArrayList<ObaRegion> getRegions(Context context, boolean forceReload) {
        ArrayList<ObaRegion> results;
        if (!forceReload) {
            // 
            results = RegionUtils.getRegionsFromProvider(context);
            if (!ListenerUtil.mutListener.listen(6816)) {
                if (results != null) {
                    if (!ListenerUtil.mutListener.listen(6815)) {
                        Log.d(TAG, "Retrieved regions from database.");
                    }
                    return results;
                }
            }
            if (!ListenerUtil.mutListener.listen(6817)) {
                Log.d(TAG, "Regions list retrieved from database was null.");
            }
        }
        results = RegionUtils.getRegionsFromServer(context);
        if ((ListenerUtil.mutListener.listen(6818) ? (results == null && results.isEmpty()) : (results == null || results.isEmpty()))) {
            if (!ListenerUtil.mutListener.listen(6821)) {
                Log.d(TAG, "Regions list retrieved from server was null or empty.");
            }
            if (forceReload) {
                // If we tried to force a reload from the server, then we haven't tried to reload from local provider yet
                results = RegionUtils.getRegionsFromProvider(context);
                if (!ListenerUtil.mutListener.listen(6824)) {
                    if (results != null) {
                        if (!ListenerUtil.mutListener.listen(6823)) {
                            Log.d(TAG, "Retrieved regions from database.");
                        }
                        return results;
                    } else {
                        if (!ListenerUtil.mutListener.listen(6822)) {
                            Log.d(TAG, "Regions list retrieved from database was null.");
                        }
                    }
                }
            }
            // Fetch regions from local resource file as last resort (otherwise user can't use app)
            results = RegionUtils.getRegionsFromResources(context);
            if (!ListenerUtil.mutListener.listen(6826)) {
                if (results == null) {
                    if (!ListenerUtil.mutListener.listen(6825)) {
                        // This is a complete failure to load region info from all sources, app will be useless
                        Log.d(TAG, "Regions list retrieved from local resource file was null.");
                    }
                    return results;
                }
            }
            if (!ListenerUtil.mutListener.listen(6827)) {
                Log.d(TAG, "Retrieved regions from local resource file.");
            }
        } else {
            if (!ListenerUtil.mutListener.listen(6819)) {
                Log.d(TAG, "Retrieved regions list from server.");
            }
            if (!ListenerUtil.mutListener.listen(6820)) {
                // Update local time for when the last region info was retrieved from the server
                Application.get().setLastRegionUpdateDate(new Date().getTime());
            }
        }
        if (!ListenerUtil.mutListener.listen(6828)) {
            // If the region info came from the server or local resource file, we need to save it to the local provider
            RegionUtils.saveToProvider(context, results);
        }
        return results;
    }

    public static ArrayList<ObaRegion> getRegionsFromProvider(Context context) {
        // Prefetch the bounds to limit the number of DB calls.
        HashMap<Long, ArrayList<ObaRegionElement.Bounds>> allBounds = getBoundsFromProvider(context);
        HashMap<Long, ArrayList<ObaRegionElement.Open311Server>> allOpen311Servers = getOpen311ServersFromProvider(context);
        Cursor c = null;
        try {
            final String[] PROJECTION = { ObaContract.Regions._ID, ObaContract.Regions.NAME, ObaContract.Regions.OBA_BASE_URL, ObaContract.Regions.SIRI_BASE_URL, ObaContract.Regions.LANGUAGE, ObaContract.Regions.CONTACT_EMAIL, ObaContract.Regions.SUPPORTS_OBA_DISCOVERY, ObaContract.Regions.SUPPORTS_OBA_REALTIME, ObaContract.Regions.SUPPORTS_SIRI_REALTIME, ObaContract.Regions.TWITTER_URL, ObaContract.Regions.EXPERIMENTAL, ObaContract.Regions.STOP_INFO_URL, ObaContract.Regions.OTP_BASE_URL, ObaContract.Regions.OTP_CONTACT_EMAIL, ObaContract.Regions.SUPPORTS_OTP_BIKESHARE, ObaContract.Regions.SUPPORTS_EMBEDDED_SOCIAL, ObaContract.Regions.PAYMENT_ANDROID_APP_ID, ObaContract.Regions.PAYMENT_WARNING_TITLE, ObaContract.Regions.PAYMENT_WARNING_BODY, ObaContract.Regions.TRAVEL_BEHAVIOR_DATA_COLLECTION, ObaContract.Regions.ENROLL_PARTICIPANTS_IN_STUDY };
            ContentResolver cr = context.getContentResolver();
            if (!ListenerUtil.mutListener.listen(6831)) {
                c = cr.query(ObaContract.Regions.CONTENT_URI, PROJECTION, null, null, ObaContract.Regions._ID);
            }
            if (c == null) {
                return null;
            }
            if (c.getCount() == 0) {
                if (!ListenerUtil.mutListener.listen(6832)) {
                    c.close();
                }
                return null;
            }
            ArrayList<ObaRegion> results = new ArrayList<ObaRegion>();
            if (!ListenerUtil.mutListener.listen(6833)) {
                c.moveToFirst();
            }
            if (!ListenerUtil.mutListener.listen(6875)) {
                {
                    long _loopCounter71 = 0;
                    do {
                        ListenerUtil.loopListener.listen("_loopCounter71", ++_loopCounter71);
                        long id = c.getLong(0);
                        ArrayList<ObaRegionElement.Bounds> bounds = allBounds.get(id);
                        ObaRegionElement.Bounds[] bounds2 = (bounds != null) ? bounds.toArray(new ObaRegionElement.Bounds[] {}) : null;
                        ArrayList<ObaRegionElement.Open311Server> open311Servers = allOpen311Servers.get(id);
                        ObaRegionElement.Open311Server[] open311Servers2 = (open311Servers != null) ? open311Servers.toArray(new ObaRegionElement.Open311Server[] {}) : null;
                        if (!ListenerUtil.mutListener.listen(6874)) {
                            results.add(new // id
                            ObaRegionElement(// id
                            id, // Name
                            c.getString(1), // Active
                            true, // OBA Base URL
                            c.getString(2), // SIRI Base URL
                            c.getString(3), // Bounds
                            bounds2, // Open311 servers
                            open311Servers2, // Lang
                            c.getString(4), // Contact Email
                            c.getString(5), (ListenerUtil.mutListener.listen(6838) ? (// Supports Oba Discovery
                            c.getInt(6) >= 0) : (ListenerUtil.mutListener.listen(6837) ? (// Supports Oba Discovery
                            c.getInt(6) <= 0) : (ListenerUtil.mutListener.listen(6836) ? (// Supports Oba Discovery
                            c.getInt(6) < 0) : (ListenerUtil.mutListener.listen(6835) ? (// Supports Oba Discovery
                            c.getInt(6) != 0) : (ListenerUtil.mutListener.listen(6834) ? (// Supports Oba Discovery
                            c.getInt(6) == 0) : (// Supports Oba Discovery
                            c.getInt(6) > 0)))))), (ListenerUtil.mutListener.listen(6843) ? (// Supports Oba Realtime
                            c.getInt(7) >= 0) : (ListenerUtil.mutListener.listen(6842) ? (// Supports Oba Realtime
                            c.getInt(7) <= 0) : (ListenerUtil.mutListener.listen(6841) ? (// Supports Oba Realtime
                            c.getInt(7) < 0) : (ListenerUtil.mutListener.listen(6840) ? (// Supports Oba Realtime
                            c.getInt(7) != 0) : (ListenerUtil.mutListener.listen(6839) ? (// Supports Oba Realtime
                            c.getInt(7) == 0) : (// Supports Oba Realtime
                            c.getInt(7) > 0)))))), (ListenerUtil.mutListener.listen(6848) ? (// Supports Siri Realtime
                            c.getInt(8) >= 0) : (ListenerUtil.mutListener.listen(6847) ? (// Supports Siri Realtime
                            c.getInt(8) <= 0) : (ListenerUtil.mutListener.listen(6846) ? (// Supports Siri Realtime
                            c.getInt(8) < 0) : (ListenerUtil.mutListener.listen(6845) ? (// Supports Siri Realtime
                            c.getInt(8) != 0) : (ListenerUtil.mutListener.listen(6844) ? (// Supports Siri Realtime
                            c.getInt(8) == 0) : (// Supports Siri Realtime
                            c.getInt(8) > 0)))))), // Twitter URL
                            c.getString(9), (ListenerUtil.mutListener.listen(6853) ? (// Experimental
                            c.getInt(10) >= 0) : (ListenerUtil.mutListener.listen(6852) ? (// Experimental
                            c.getInt(10) <= 0) : (ListenerUtil.mutListener.listen(6851) ? (// Experimental
                            c.getInt(10) < 0) : (ListenerUtil.mutListener.listen(6850) ? (// Experimental
                            c.getInt(10) != 0) : (ListenerUtil.mutListener.listen(6849) ? (// Experimental
                            c.getInt(10) == 0) : (// Experimental
                            c.getInt(10) > 0)))))), // StopInfoUrl
                            c.getString(11), // OTP Base URL
                            c.getString(12), // OTP Contact Email
                            c.getString(13), (ListenerUtil.mutListener.listen(6858) ? (// Supports Otp Bikeshare
                            c.getInt(14) >= 0) : (ListenerUtil.mutListener.listen(6857) ? (// Supports Otp Bikeshare
                            c.getInt(14) <= 0) : (ListenerUtil.mutListener.listen(6856) ? (// Supports Otp Bikeshare
                            c.getInt(14) < 0) : (ListenerUtil.mutListener.listen(6855) ? (// Supports Otp Bikeshare
                            c.getInt(14) != 0) : (ListenerUtil.mutListener.listen(6854) ? (// Supports Otp Bikeshare
                            c.getInt(14) == 0) : (// Supports Otp Bikeshare
                            c.getInt(14) > 0)))))), (ListenerUtil.mutListener.listen(6863) ? (// Supports Embedded Social
                            c.getInt(15) >= 0) : (ListenerUtil.mutListener.listen(6862) ? (// Supports Embedded Social
                            c.getInt(15) <= 0) : (ListenerUtil.mutListener.listen(6861) ? (// Supports Embedded Social
                            c.getInt(15) < 0) : (ListenerUtil.mutListener.listen(6860) ? (// Supports Embedded Social
                            c.getInt(15) != 0) : (ListenerUtil.mutListener.listen(6859) ? (// Supports Embedded Social
                            c.getInt(15) == 0) : (// Supports Embedded Social
                            c.getInt(15) > 0)))))), // Android App ID for mobile fare payment app of region
                            c.getString(16), // Payment Warning Title
                            c.getString(17), // Payment Warning Body
                            c.getString(18), (ListenerUtil.mutListener.listen(6868) ? (// travel behavior data collection enabled for region
                            c.getInt(19) >= 0) : (ListenerUtil.mutListener.listen(6867) ? (// travel behavior data collection enabled for region
                            c.getInt(19) <= 0) : (ListenerUtil.mutListener.listen(6866) ? (// travel behavior data collection enabled for region
                            c.getInt(19) < 0) : (ListenerUtil.mutListener.listen(6865) ? (// travel behavior data collection enabled for region
                            c.getInt(19) != 0) : (ListenerUtil.mutListener.listen(6864) ? (// travel behavior data collection enabled for region
                            c.getInt(19) == 0) : (// travel behavior data collection enabled for region
                            c.getInt(19) > 0)))))), (ListenerUtil.mutListener.listen(6873) ? (// enrolling participants for travel behavior data collection
                            c.getInt(20) >= 0) : (ListenerUtil.mutListener.listen(6872) ? (// enrolling participants for travel behavior data collection
                            c.getInt(20) <= 0) : (ListenerUtil.mutListener.listen(6871) ? (// enrolling participants for travel behavior data collection
                            c.getInt(20) < 0) : (ListenerUtil.mutListener.listen(6870) ? (// enrolling participants for travel behavior data collection
                            c.getInt(20) != 0) : (ListenerUtil.mutListener.listen(6869) ? (// enrolling participants for travel behavior data collection
                            c.getInt(20) == 0) : (// enrolling participants for travel behavior data collection
                            c.getInt(20) > 0))))))));
                        }
                    } while (c.moveToNext());
                }
            }
            return results;
        } finally {
            if (!ListenerUtil.mutListener.listen(6830)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(6829)) {
                        c.close();
                    }
                }
            }
        }
    }

    private static HashMap<Long, ArrayList<ObaRegionElement.Bounds>> getBoundsFromProvider(Context context) {
        // Prefetch the bounds to limit the number of DB calls.
        Cursor c = null;
        try {
            final String[] PROJECTION = { ObaContract.RegionBounds.REGION_ID, ObaContract.RegionBounds.LATITUDE, ObaContract.RegionBounds.LONGITUDE, ObaContract.RegionBounds.LAT_SPAN, ObaContract.RegionBounds.LON_SPAN };
            HashMap<Long, ArrayList<ObaRegionElement.Bounds>> results = new HashMap<Long, ArrayList<ObaRegionElement.Bounds>>();
            ContentResolver cr = context.getContentResolver();
            if (!ListenerUtil.mutListener.listen(6878)) {
                c = cr.query(ObaContract.RegionBounds.CONTENT_URI, PROJECTION, null, null, null);
            }
            if (c == null) {
                return results;
            }
            if (c.getCount() == 0) {
                if (!ListenerUtil.mutListener.listen(6879)) {
                    c.close();
                }
                return results;
            }
            if (!ListenerUtil.mutListener.listen(6880)) {
                c.moveToFirst();
            }
            if (!ListenerUtil.mutListener.listen(6886)) {
                {
                    long _loopCounter72 = 0;
                    do {
                        ListenerUtil.loopListener.listen("_loopCounter72", ++_loopCounter72);
                        long regionId = c.getLong(0);
                        ArrayList<ObaRegionElement.Bounds> bounds = results.get(regionId);
                        ObaRegionElement.Bounds b = new ObaRegionElement.Bounds(c.getDouble(1), c.getDouble(2), c.getDouble(3), c.getDouble(4));
                        if (!ListenerUtil.mutListener.listen(6885)) {
                            if (bounds != null) {
                                if (!ListenerUtil.mutListener.listen(6884)) {
                                    bounds.add(b);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(6881)) {
                                    bounds = new ArrayList<ObaRegionElement.Bounds>();
                                }
                                if (!ListenerUtil.mutListener.listen(6882)) {
                                    bounds.add(b);
                                }
                                if (!ListenerUtil.mutListener.listen(6883)) {
                                    results.put(regionId, bounds);
                                }
                            }
                        }
                    } while (c.moveToNext());
                }
            }
            return results;
        } finally {
            if (!ListenerUtil.mutListener.listen(6877)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(6876)) {
                        c.close();
                    }
                }
            }
        }
    }

    private static HashMap<Long, ArrayList<ObaRegionElement.Open311Server>> getOpen311ServersFromProvider(Context context) {
        // Prefetch the bounds to limit the number of DB calls.
        Cursor c = null;
        try {
            final String[] PROJECTION = { ObaContract.RegionOpen311Servers.REGION_ID, ObaContract.RegionOpen311Servers.JURISDICTION, ObaContract.RegionOpen311Servers.API_KEY, ObaContract.RegionOpen311Servers.BASE_URL };
            HashMap<Long, ArrayList<ObaRegionElement.Open311Server>> results = new HashMap<Long, ArrayList<ObaRegionElement.Open311Server>>();
            ContentResolver cr = context.getContentResolver();
            if (!ListenerUtil.mutListener.listen(6889)) {
                c = cr.query(ObaContract.RegionOpen311Servers.CONTENT_URI, PROJECTION, null, null, null);
            }
            if (c == null) {
                return results;
            }
            if (c.getCount() == 0) {
                if (!ListenerUtil.mutListener.listen(6890)) {
                    c.close();
                }
                return results;
            }
            if (!ListenerUtil.mutListener.listen(6891)) {
                c.moveToFirst();
            }
            if (!ListenerUtil.mutListener.listen(6897)) {
                {
                    long _loopCounter73 = 0;
                    do {
                        ListenerUtil.loopListener.listen("_loopCounter73", ++_loopCounter73);
                        long regionId = c.getLong(0);
                        ArrayList<ObaRegionElement.Open311Server> open311Servers = results.get(regionId);
                        ObaRegionElement.Open311Server b = new ObaRegionElement.Open311Server(c.getString(1), c.getString(2), c.getString(3));
                        if (!ListenerUtil.mutListener.listen(6896)) {
                            if (open311Servers != null) {
                                if (!ListenerUtil.mutListener.listen(6895)) {
                                    open311Servers.add(b);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(6892)) {
                                    open311Servers = new ArrayList<ObaRegionElement.Open311Server>();
                                }
                                if (!ListenerUtil.mutListener.listen(6893)) {
                                    open311Servers.add(b);
                                }
                                if (!ListenerUtil.mutListener.listen(6894)) {
                                    results.put(regionId, open311Servers);
                                }
                            }
                        }
                    } while (c.moveToNext());
                }
            }
            return results;
        } finally {
            if (!ListenerUtil.mutListener.listen(6888)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(6887)) {
                        c.close();
                    }
                }
            }
        }
    }

    private static synchronized ArrayList<ObaRegion> getRegionsFromServer(Context context) {
        ObaRegionsResponse response = ObaRegionsRequest.newRequest(context).call();
        return new ArrayList<ObaRegion>(Arrays.asList(response.getRegions()));
    }

    /**
     * Retrieves region information from a regions file bundled within the app APK
     *
     * IMPORTANT - this should be a last resort, and we should always try to pull regions
     * info from the local provider or Regions REST API instead of from the bundled file.
     *
     * This method is only intended to be a fail-safe in case the Regions REST API goes
     * offline and a user downloads and installs OBA Android during that period
     * (i.e., local OBA servers are available, but Regions REST API failure would block initial
     * execution of the app).  This avoids a potential central point of failure for OBA
     * Android installations on devices in multiple regions.
     *
     * @return list of regions retrieved from the regions file in app resources
     */
    public static ArrayList<ObaRegion> getRegionsFromResources(Context context) {
        final Uri.Builder builder = new Uri.Builder();
        if (!ListenerUtil.mutListener.listen(6898)) {
            builder.scheme(ContentResolver.SCHEME_ANDROID_RESOURCE);
        }
        if (!ListenerUtil.mutListener.listen(6899)) {
            builder.authority(context.getPackageName());
        }
        if (!ListenerUtil.mutListener.listen(6900)) {
            builder.path(Integer.toString(R.raw.regions_v3));
        }
        ObaRegionsResponse response = ObaRegionsRequest.newRequest(context, builder.build()).call();
        return new ArrayList<ObaRegion>(Arrays.asList(response.getRegions()));
    }

    /**
     * Retrieves hard-coded region information from the build flavor defined in build.gradle.
     * If a fixed region is defined in a build flavor, it does not allow region roaming.
     *
     * @return hard-coded region information from the build flavor defined in build.gradle
     */
    public static ObaRegion getRegionFromBuildFlavor() {
        // This doesn't get used, but needs to be positive
        final int regionId = Integer.MAX_VALUE;
        ObaRegionElement.Bounds[] boundsArray = new ObaRegionElement.Bounds[1];
        ObaRegionElement.Bounds bounds = new ObaRegionElement.Bounds(BuildConfig.FIXED_REGION_BOUNDS_LAT, BuildConfig.FIXED_REGION_BOUNDS_LON, BuildConfig.FIXED_REGION_BOUNDS_LAT_SPAN, BuildConfig.FIXED_REGION_BOUNDS_LON_SPAN);
        if (!ListenerUtil.mutListener.listen(6901)) {
            boundsArray[0] = bounds;
        }
        ObaRegionElement.Open311Server[] open311Array = new ObaRegionElement.Open311Server[1];
        ObaRegionElement.Open311Server open311Server;
        if (BuildConfig.FIXED_REGION_OPEN311_BASE_URL != null) {
            open311Server = new ObaRegionElement.Open311Server(BuildConfig.FIXED_REGION_OPEN311_JURISDICTION_ID, BuildConfig.FIXED_REGION_OPEN311_API_KEY, BuildConfig.FIXED_REGION_OPEN311_BASE_URL);
            if (!ListenerUtil.mutListener.listen(6903)) {
                open311Array[0] = open311Server;
            }
        } else {
            if (!ListenerUtil.mutListener.listen(6902)) {
                open311Array = null;
            }
        }
        ObaRegionElement region = new ObaRegionElement(regionId, BuildConfig.FIXED_REGION_NAME, true, BuildConfig.FIXED_REGION_OBA_BASE_URL, BuildConfig.FIXED_REGION_SIRI_BASE_URL, boundsArray, open311Array, BuildConfig.FIXED_REGION_LANG, BuildConfig.FIXED_REGION_CONTACT_EMAIL, BuildConfig.FIXED_REGION_SUPPORTS_OBA_DISCOVERY_APIS, BuildConfig.FIXED_REGION_SUPPORTS_OBA_REALTIME_APIS, BuildConfig.FIXED_REGION_SUPPORTS_SIRI_REALTIME_APIS, BuildConfig.FIXED_REGION_TWITTER_URL, false, BuildConfig.FIXED_REGION_STOP_INFO_URL, BuildConfig.FIXED_REGION_OTP_BASE_URL, BuildConfig.FIXED_REGION_OTP_CONTACT_EMAIL, BuildConfig.FIXED_REGION_SUPPORTS_OTP_BIKESHARE, false, BuildConfig.FIXED_REGION_PAYMENT_ANDROID_APP_ID, BuildConfig.FIXED_REGION_PAYMENT_WARNING_TITLE, BuildConfig.FIXED_REGION_PAYMENT_WARNING_BODY, BuildConfig.FIXED_REGION_TRAVEL_BEHAVIOR_DATA_COLLECTION, BuildConfig.FIXED_REGION_ENROLL_PARTICIPANTS_IN_STUDY);
        return region;
    }

    // 
    public static synchronized void saveToProvider(Context context, List<ObaRegion> regions) {
        // Delete all the existing regions
        ContentResolver cr = context.getContentResolver();
        if (!ListenerUtil.mutListener.listen(6904)) {
            cr.delete(ObaContract.Regions.CONTENT_URI, null, null);
        }
        if (!ListenerUtil.mutListener.listen(6905)) {
            // Should be a no-op?
            cr.delete(ObaContract.RegionBounds.CONTENT_URI, null, null);
        }
        if (!ListenerUtil.mutListener.listen(6906)) {
            // Delete all existing open311 endpoints
            cr.delete(ObaContract.RegionOpen311Servers.CONTENT_URI, null, null);
        }
        if (!ListenerUtil.mutListener.listen(6929)) {
            {
                long _loopCounter76 = 0;
                for (ObaRegion region : regions) {
                    ListenerUtil.loopListener.listen("_loopCounter76", ++_loopCounter76);
                    if (!ListenerUtil.mutListener.listen(6908)) {
                        if (!isRegionUsable(region)) {
                            if (!ListenerUtil.mutListener.listen(6907)) {
                                Log.d(TAG, "Skipping insert of '" + region.getName() + "' to provider...");
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(6909)) {
                        cr.insert(ObaContract.Regions.CONTENT_URI, toContentValues(region));
                    }
                    if (!ListenerUtil.mutListener.listen(6910)) {
                        Log.d(TAG, "Saved region '" + region.getName() + "' to provider");
                    }
                    long regionId = region.getId();
                    // Bulk insert the bounds
                    ObaRegion.Bounds[] bounds = region.getBounds();
                    if (!ListenerUtil.mutListener.listen(6919)) {
                        if (bounds != null) {
                            ContentValues[] values = new ContentValues[bounds.length];
                            if (!ListenerUtil.mutListener.listen(6917)) {
                                {
                                    long _loopCounter74 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(6916) ? (i >= bounds.length) : (ListenerUtil.mutListener.listen(6915) ? (i <= bounds.length) : (ListenerUtil.mutListener.listen(6914) ? (i > bounds.length) : (ListenerUtil.mutListener.listen(6913) ? (i != bounds.length) : (ListenerUtil.mutListener.listen(6912) ? (i == bounds.length) : (i < bounds.length)))))); ++i) {
                                        ListenerUtil.loopListener.listen("_loopCounter74", ++_loopCounter74);
                                        if (!ListenerUtil.mutListener.listen(6911)) {
                                            values[i] = toContentValues(regionId, bounds[i]);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6918)) {
                                cr.bulkInsert(ObaContract.RegionBounds.CONTENT_URI, values);
                            }
                        }
                    }
                    ObaRegion.Open311Server[] open311Servers = region.getOpen311Servers();
                    if (!ListenerUtil.mutListener.listen(6928)) {
                        if (open311Servers != null) {
                            ContentValues[] values = new ContentValues[open311Servers.length];
                            if (!ListenerUtil.mutListener.listen(6926)) {
                                {
                                    long _loopCounter75 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(6925) ? (i >= open311Servers.length) : (ListenerUtil.mutListener.listen(6924) ? (i <= open311Servers.length) : (ListenerUtil.mutListener.listen(6923) ? (i > open311Servers.length) : (ListenerUtil.mutListener.listen(6922) ? (i != open311Servers.length) : (ListenerUtil.mutListener.listen(6921) ? (i == open311Servers.length) : (i < open311Servers.length)))))); ++i) {
                                        ListenerUtil.loopListener.listen("_loopCounter75", ++_loopCounter75);
                                        if (!ListenerUtil.mutListener.listen(6920)) {
                                            values[i] = toContentValues(regionId, open311Servers[i]);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6927)) {
                                cr.bulkInsert(ObaContract.RegionOpen311Servers.CONTENT_URI, values);
                            }
                        }
                    }
                }
            }
        }
    }

    private static ContentValues toContentValues(ObaRegion region) {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(6930)) {
            values.put(ObaContract.Regions._ID, region.getId());
        }
        if (!ListenerUtil.mutListener.listen(6931)) {
            values.put(ObaContract.Regions.NAME, region.getName());
        }
        String obaUrl = region.getObaBaseUrl();
        if (!ListenerUtil.mutListener.listen(6932)) {
            values.put(ObaContract.Regions.OBA_BASE_URL, obaUrl != null ? obaUrl : "");
        }
        String siriUrl = region.getSiriBaseUrl();
        if (!ListenerUtil.mutListener.listen(6933)) {
            values.put(ObaContract.Regions.SIRI_BASE_URL, siriUrl != null ? siriUrl : "");
        }
        if (!ListenerUtil.mutListener.listen(6934)) {
            values.put(ObaContract.Regions.LANGUAGE, region.getLanguage());
        }
        if (!ListenerUtil.mutListener.listen(6935)) {
            values.put(ObaContract.Regions.CONTACT_EMAIL, region.getContactEmail());
        }
        if (!ListenerUtil.mutListener.listen(6936)) {
            values.put(ObaContract.Regions.SUPPORTS_OBA_DISCOVERY, region.getSupportsObaDiscoveryApis() ? 1 : 0);
        }
        if (!ListenerUtil.mutListener.listen(6937)) {
            values.put(ObaContract.Regions.SUPPORTS_OBA_REALTIME, region.getSupportsObaRealtimeApis() ? 1 : 0);
        }
        if (!ListenerUtil.mutListener.listen(6938)) {
            values.put(ObaContract.Regions.SUPPORTS_SIRI_REALTIME, region.getSupportsSiriRealtimeApis() ? 1 : 0);
        }
        if (!ListenerUtil.mutListener.listen(6939)) {
            values.put(ObaContract.Regions.TWITTER_URL, region.getTwitterUrl());
        }
        if (!ListenerUtil.mutListener.listen(6940)) {
            values.put(ObaContract.Regions.EXPERIMENTAL, region.getExperimental());
        }
        if (!ListenerUtil.mutListener.listen(6941)) {
            values.put(ObaContract.Regions.STOP_INFO_URL, region.getStopInfoUrl());
        }
        if (!ListenerUtil.mutListener.listen(6942)) {
            values.put(ObaContract.Regions.OTP_BASE_URL, region.getOtpBaseUrl());
        }
        if (!ListenerUtil.mutListener.listen(6943)) {
            values.put(ObaContract.Regions.OTP_CONTACT_EMAIL, region.getOtpContactEmail());
        }
        if (!ListenerUtil.mutListener.listen(6944)) {
            values.put(ObaContract.Regions.SUPPORTS_OTP_BIKESHARE, region.getSupportsOtpBikeshare() ? 1 : 0);
        }
        if (!ListenerUtil.mutListener.listen(6945)) {
            values.put(ObaContract.Regions.SUPPORTS_EMBEDDED_SOCIAL, region.getSupportsEmbeddedSocial() ? 1 : 0);
        }
        if (!ListenerUtil.mutListener.listen(6946)) {
            values.put(ObaContract.Regions.PAYMENT_ANDROID_APP_ID, region.getPaymentAndroidAppId());
        }
        if (!ListenerUtil.mutListener.listen(6947)) {
            values.put(ObaContract.Regions.PAYMENT_WARNING_TITLE, region.getPaymentWarningTitle());
        }
        if (!ListenerUtil.mutListener.listen(6948)) {
            values.put(ObaContract.Regions.PAYMENT_WARNING_BODY, region.getPaymentWarningBody());
        }
        if (!ListenerUtil.mutListener.listen(6949)) {
            values.put(ObaContract.Regions.TRAVEL_BEHAVIOR_DATA_COLLECTION, region.isTravelBehaviorDataCollectionEnabled() ? 1 : 0);
        }
        if (!ListenerUtil.mutListener.listen(6950)) {
            values.put(ObaContract.Regions.ENROLL_PARTICIPANTS_IN_STUDY, region.isEnrollParticipantsInStudy() ? 1 : 0);
        }
        return values;
    }

    private static ContentValues toContentValues(long region, ObaRegion.Bounds bounds) {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(6951)) {
            values.put(ObaContract.RegionBounds.REGION_ID, region);
        }
        if (!ListenerUtil.mutListener.listen(6952)) {
            values.put(ObaContract.RegionBounds.LATITUDE, bounds.getLat());
        }
        if (!ListenerUtil.mutListener.listen(6953)) {
            values.put(ObaContract.RegionBounds.LONGITUDE, bounds.getLon());
        }
        if (!ListenerUtil.mutListener.listen(6954)) {
            values.put(ObaContract.RegionBounds.LAT_SPAN, bounds.getLatSpan());
        }
        if (!ListenerUtil.mutListener.listen(6955)) {
            values.put(ObaContract.RegionBounds.LON_SPAN, bounds.getLonSpan());
        }
        return values;
    }

    private static ContentValues toContentValues(long region, ObaRegion.Open311Server open311Server) {
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(6956)) {
            values.put(ObaContract.RegionOpen311Servers.REGION_ID, region);
        }
        if (!ListenerUtil.mutListener.listen(6957)) {
            values.put(ObaContract.RegionOpen311Servers.BASE_URL, open311Server.getBaseUrl());
        }
        if (!ListenerUtil.mutListener.listen(6958)) {
            values.put(ObaContract.RegionOpen311Servers.JURISDICTION, open311Server.getJuridisctionId());
        }
        if (!ListenerUtil.mutListener.listen(6959)) {
            values.put(ObaContract.RegionOpen311Servers.API_KEY, open311Server.getApiKey());
        }
        return values;
    }
}
