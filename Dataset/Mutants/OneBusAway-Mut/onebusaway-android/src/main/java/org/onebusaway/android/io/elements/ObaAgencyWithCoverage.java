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
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class ObaAgencyWithCoverage implements ObaElement {

    public static final ObaAgencyWithCoverage[] EMPTY_ARRAY = new ObaAgencyWithCoverage[] {};

    private final String agencyId;

    private final double lat;

    private final double lon;

    private final double latSpan;

    private final double lonSpan;

    public ObaAgencyWithCoverage() {
        agencyId = "";
        lat = 0;
        lon = 0;
        latSpan = 0;
        lonSpan = 0;
    }

    @Override
    public String getId() {
        return agencyId;
    }

    /**
     * @return The center point of the agency's coverage area.
     */
    public Location getPoint() {
        return LocationUtils.makeLocation(lat, lon);
    }

    /**
     * @return The latitude center of the coverage bounding box.
     */
    public double getLatitude() {
        return lat;
    }

    /**
     * @return The longitude center of the coverage bounding box.
     */
    public double getLongitude() {
        return lon;
    }

    /**
     * @return The latitude height of the coverage bounding box.
     */
    public double getLatitudeSpan() {
        return latSpan;
    }

    /**
     * @return The longitude hight of the coverage bounding box.
     */
    public double getLongitudeSpan() {
        return lonSpan;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if (!ListenerUtil.mutListener.listen(8237)) {
            result = (ListenerUtil.mutListener.listen(8236) ? ((ListenerUtil.mutListener.listen(8232) ? (prime % result) : (ListenerUtil.mutListener.listen(8231) ? (prime / result) : (ListenerUtil.mutListener.listen(8230) ? (prime - result) : (ListenerUtil.mutListener.listen(8229) ? (prime + result) : (prime * result))))) % ((agencyId == null) ? 0 : agencyId.hashCode())) : (ListenerUtil.mutListener.listen(8235) ? ((ListenerUtil.mutListener.listen(8232) ? (prime % result) : (ListenerUtil.mutListener.listen(8231) ? (prime / result) : (ListenerUtil.mutListener.listen(8230) ? (prime - result) : (ListenerUtil.mutListener.listen(8229) ? (prime + result) : (prime * result))))) / ((agencyId == null) ? 0 : agencyId.hashCode())) : (ListenerUtil.mutListener.listen(8234) ? ((ListenerUtil.mutListener.listen(8232) ? (prime % result) : (ListenerUtil.mutListener.listen(8231) ? (prime / result) : (ListenerUtil.mutListener.listen(8230) ? (prime - result) : (ListenerUtil.mutListener.listen(8229) ? (prime + result) : (prime * result))))) * ((agencyId == null) ? 0 : agencyId.hashCode())) : (ListenerUtil.mutListener.listen(8233) ? ((ListenerUtil.mutListener.listen(8232) ? (prime % result) : (ListenerUtil.mutListener.listen(8231) ? (prime / result) : (ListenerUtil.mutListener.listen(8230) ? (prime - result) : (ListenerUtil.mutListener.listen(8229) ? (prime + result) : (prime * result))))) - ((agencyId == null) ? 0 : agencyId.hashCode())) : ((ListenerUtil.mutListener.listen(8232) ? (prime % result) : (ListenerUtil.mutListener.listen(8231) ? (prime / result) : (ListenerUtil.mutListener.listen(8230) ? (prime - result) : (ListenerUtil.mutListener.listen(8229) ? (prime + result) : (prime * result))))) + ((agencyId == null) ? 0 : agencyId.hashCode()))))));
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!ListenerUtil.mutListener.listen(8238)) {
            if (this == obj) {
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(8239)) {
            if (obj == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(8240)) {
            if (!(obj instanceof ObaAgencyWithCoverage)) {
                return false;
            }
        }
        ObaAgencyWithCoverage other = (ObaAgencyWithCoverage) obj;
        if (!ListenerUtil.mutListener.listen(8242)) {
            if (agencyId == null) {
                if (!ListenerUtil.mutListener.listen(8241)) {
                    if (other.agencyId != null) {
                        return false;
                    }
                }
            } else if (!agencyId.equals(other.agencyId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObaAgencyWithCoverage [agencyId=" + agencyId + "]";
    }
}
