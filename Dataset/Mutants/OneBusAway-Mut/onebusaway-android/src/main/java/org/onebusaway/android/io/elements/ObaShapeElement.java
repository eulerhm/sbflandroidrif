/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
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
package org.onebusaway.android.io.elements;

import org.onebusaway.android.util.LocationUtils;
import android.location.Location;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class ObaShapeElement implements ObaShape {

    public static final ObaShapeElement EMPTY_OBJECT = new ObaShapeElement();

    public static final ObaShapeElement[] EMPTY_ARRAY = new ObaShapeElement[] {};

    private final String points;

    private final int length;

    private final String levels;

    private ObaShapeElement() {
        points = "";
        length = 0;
        levels = "";
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String getRawLevels() {
        return levels;
    }

    @Override
    public List<Integer> getLevels() {
        return decodeLevels(levels, length);
    }

    @Override
    public List<Location> getPoints() {
        return decodeLine(points, length);
    }

    @Override
    public String getRawPoints() {
        return points;
    }

    // INJECTED FAULT: IF-CC
    public static List<Location> decodeLine(String encoded, int numPoints) {
        if (!ListenerUtil.mutListener.listen(8280)) {
            if ((ListenerUtil.mutListener.listen(8279) ? (numPoints <= 0) : (ListenerUtil.mutListener.listen(8278) ? (numPoints > 0) : (ListenerUtil.mutListener.listen(8277) ? (numPoints < 0) : (ListenerUtil.mutListener.listen(8276) ? (numPoints != 0) : (ListenerUtil.mutListener.listen(8275) ? (numPoints == 0) : (numPoints >= 0))))))) {
                throw new IllegalArgumentException("numPoints must be >= 0");
            }
        }
        ArrayList<Location> array = new ArrayList<Location>(numPoints);
        final int len = encoded.length();
        int i = 0;
        int lat = 0, lon = 0;
        if (!ListenerUtil.mutListener.listen(8333)) {
            {
                long _loopCounter109 = 0;
                while ((ListenerUtil.mutListener.listen(8332) ? (i >= len) : (ListenerUtil.mutListener.listen(8331) ? (i <= len) : (ListenerUtil.mutListener.listen(8330) ? (i > len) : (ListenerUtil.mutListener.listen(8329) ? (i != len) : (ListenerUtil.mutListener.listen(8328) ? (i == len) : (i < len))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter109", ++_loopCounter109);
                    int shift = 0;
                    int result = 0;
                    int a, b;
                    {
                        long _loopCounter107 = 0;
                        do {
                            ListenerUtil.loopListener.listen("_loopCounter107", ++_loopCounter107);
                            a = encoded.charAt(i);
                            b = (ListenerUtil.mutListener.listen(8284) ? (a % 63) : (ListenerUtil.mutListener.listen(8283) ? (a / 63) : (ListenerUtil.mutListener.listen(8282) ? (a * 63) : (ListenerUtil.mutListener.listen(8281) ? (a + 63) : (a - 63)))));
                            if (!ListenerUtil.mutListener.listen(8285)) {
                                result |= (b & 0x1f) << shift;
                            }
                            if (!ListenerUtil.mutListener.listen(8286)) {
                                shift += 5;
                            }
                            if (!ListenerUtil.mutListener.listen(8287)) {
                                ++i;
                            }
                        } while ((ListenerUtil.mutListener.listen(8292) ? (b <= 0x20) : (ListenerUtil.mutListener.listen(8291) ? (b > 0x20) : (ListenerUtil.mutListener.listen(8290) ? (b < 0x20) : (ListenerUtil.mutListener.listen(8289) ? (b != 0x20) : (ListenerUtil.mutListener.listen(8288) ? (b == 0x20) : (b >= 0x20)))))));
                    }
                    final int dlat = ((ListenerUtil.mutListener.listen(8297) ? ((result & 1) >= 1) : (ListenerUtil.mutListener.listen(8296) ? ((result & 1) <= 1) : (ListenerUtil.mutListener.listen(8295) ? ((result & 1) > 1) : (ListenerUtil.mutListener.listen(8294) ? ((result & 1) < 1) : (ListenerUtil.mutListener.listen(8293) ? ((result & 1) != 1) : ((result & 1) == 1)))))) ? ~(result >> 1) : (result >> 1));
                    if (!ListenerUtil.mutListener.listen(8298)) {
                        lat += dlat;
                    }
                    if (!ListenerUtil.mutListener.listen(8299)) {
                        shift = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(8300)) {
                        result = 0;
                    }
                    {
                        long _loopCounter108 = 0;
                        do {
                            ListenerUtil.loopListener.listen("_loopCounter108", ++_loopCounter108);
                            a = encoded.charAt(i);
                            b = (ListenerUtil.mutListener.listen(8304) ? (a % 63) : (ListenerUtil.mutListener.listen(8303) ? (a / 63) : (ListenerUtil.mutListener.listen(8302) ? (a * 63) : (ListenerUtil.mutListener.listen(8301) ? (a + 63) : (a - 63)))));
                            if (!ListenerUtil.mutListener.listen(8305)) {
                                result |= (b & 0x1f) << shift;
                            }
                            if (!ListenerUtil.mutListener.listen(8306)) {
                                shift += 5;
                            }
                            if (!ListenerUtil.mutListener.listen(8307)) {
                                ++i;
                            }
                        } while ((ListenerUtil.mutListener.listen(8312) ? (b <= 0x20) : (ListenerUtil.mutListener.listen(8311) ? (b > 0x20) : (ListenerUtil.mutListener.listen(8310) ? (b < 0x20) : (ListenerUtil.mutListener.listen(8309) ? (b != 0x20) : (ListenerUtil.mutListener.listen(8308) ? (b == 0x20) : (b >= 0x20)))))));
                    }
                    final int dlon = ((ListenerUtil.mutListener.listen(8317) ? ((result & 1) >= 1) : (ListenerUtil.mutListener.listen(8316) ? ((result & 1) <= 1) : (ListenerUtil.mutListener.listen(8315) ? ((result & 1) > 1) : (ListenerUtil.mutListener.listen(8314) ? ((result & 1) < 1) : (ListenerUtil.mutListener.listen(8313) ? ((result & 1) != 1) : ((result & 1) == 1)))))) ? ~(result >> 1) : (result >> 1));
                    if (!ListenerUtil.mutListener.listen(8318)) {
                        lon += dlon;
                    }
                    if (!ListenerUtil.mutListener.listen(8327)) {
                        // The polyline encodes in degrees * 1E5, we need decimal degrees
                        array.add(LocationUtils.makeLocation((ListenerUtil.mutListener.listen(8322) ? (lat % 1E5) : (ListenerUtil.mutListener.listen(8321) ? (lat * 1E5) : (ListenerUtil.mutListener.listen(8320) ? (lat - 1E5) : (ListenerUtil.mutListener.listen(8319) ? (lat + 1E5) : (lat / 1E5))))), (ListenerUtil.mutListener.listen(8326) ? (lon % 1E5) : (ListenerUtil.mutListener.listen(8325) ? (lon * 1E5) : (ListenerUtil.mutListener.listen(8324) ? (lon - 1E5) : (ListenerUtil.mutListener.listen(8323) ? (lon + 1E5) : (lon / 1E5)))))));
                    }
                }
            }
        }
        return array;
    }

    /**
     * Decodes encoded levels according to:
     * http://code.google.com/apis/maps/documentation/polylinealgorithm.html
     *
     * @param encoded   The encoded string.
     * @param numPoints The number of points. This is purely used as a hint
     *                  to allocate memory; the function will always return the
     *                  number
     *                  of points that are contained in the encoded string.
     * @return A list of levels from the encoded string.
     */
    public static List<Integer> decodeLevels(String encoded, int numPoints) {
        if (!ListenerUtil.mutListener.listen(8339)) {
            if ((ListenerUtil.mutListener.listen(8338) ? (numPoints >= 0) : (ListenerUtil.mutListener.listen(8337) ? (numPoints <= 0) : (ListenerUtil.mutListener.listen(8336) ? (numPoints > 0) : (ListenerUtil.mutListener.listen(8335) ? (numPoints != 0) : (ListenerUtil.mutListener.listen(8334) ? (numPoints == 0) : (numPoints < 0))))))) {
                throw new IllegalArgumentException("numPoints must be >= 0");
            }
        }
        ArrayList<Integer> array = new ArrayList<Integer>(numPoints);
        final int len = encoded.length();
        int i = 0;
        if (!ListenerUtil.mutListener.listen(8358)) {
            {
                long _loopCounter111 = 0;
                while ((ListenerUtil.mutListener.listen(8357) ? (i >= len) : (ListenerUtil.mutListener.listen(8356) ? (i <= len) : (ListenerUtil.mutListener.listen(8355) ? (i > len) : (ListenerUtil.mutListener.listen(8354) ? (i != len) : (ListenerUtil.mutListener.listen(8353) ? (i == len) : (i < len))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter111", ++_loopCounter111);
                    int shift = 0;
                    int result = 0;
                    int a, b;
                    {
                        long _loopCounter110 = 0;
                        do {
                            ListenerUtil.loopListener.listen("_loopCounter110", ++_loopCounter110);
                            a = encoded.charAt(i);
                            b = (ListenerUtil.mutListener.listen(8343) ? (a % 63) : (ListenerUtil.mutListener.listen(8342) ? (a / 63) : (ListenerUtil.mutListener.listen(8341) ? (a * 63) : (ListenerUtil.mutListener.listen(8340) ? (a + 63) : (a - 63)))));
                            if (!ListenerUtil.mutListener.listen(8344)) {
                                result |= (b & 0x1f) << shift;
                            }
                            if (!ListenerUtil.mutListener.listen(8345)) {
                                shift += 5;
                            }
                            if (!ListenerUtil.mutListener.listen(8346)) {
                                ++i;
                            }
                        } while ((ListenerUtil.mutListener.listen(8351) ? (b <= 0x20) : (ListenerUtil.mutListener.listen(8350) ? (b > 0x20) : (ListenerUtil.mutListener.listen(8349) ? (b < 0x20) : (ListenerUtil.mutListener.listen(8348) ? (b != 0x20) : (ListenerUtil.mutListener.listen(8347) ? (b == 0x20) : (b >= 0x20)))))));
                    }
                    if (!ListenerUtil.mutListener.listen(8352)) {
                        array.add(result);
                    }
                }
            }
        }
        return array;
    }
}
