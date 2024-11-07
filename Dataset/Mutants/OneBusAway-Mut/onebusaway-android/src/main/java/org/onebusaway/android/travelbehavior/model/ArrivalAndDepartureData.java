/*
 * Copyright (C) 2019 University of South Florida
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
package org.onebusaway.android.travelbehavior.model;

import org.onebusaway.android.io.elements.ObaArrivalInfo;
import android.location.Location;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ArrivalAndDepartureData {

    public TravelBehaviorInfo.LocationInfo locationInfo;

    public List<ObaArrivalInfoPojo> arrivalList;

    public Long localElapsedRealtimeNanos;

    public Long localSystemCurrMillis;

    public Long obaServerTimestamp;

    public String stopId;

    public Long regionId;

    public String url;

    public ArrivalAndDepartureData() {
    }

    public ArrivalAndDepartureData(ObaArrivalInfo[] info, String stopId, Long regionId, String url, Long localElapsedRealtimeNanos, Long localSystemCurrMillis, Long obaServerTimestamp) {
        if (!ListenerUtil.mutListener.listen(9889)) {
            this.stopId = stopId;
        }
        if (!ListenerUtil.mutListener.listen(9890)) {
            this.regionId = regionId;
        }
        if (!ListenerUtil.mutListener.listen(9891)) {
            this.url = url;
        }
        if (!ListenerUtil.mutListener.listen(9892)) {
            this.localElapsedRealtimeNanos = localElapsedRealtimeNanos;
        }
        if (!ListenerUtil.mutListener.listen(9893)) {
            this.localSystemCurrMillis = localSystemCurrMillis;
        }
        if (!ListenerUtil.mutListener.listen(9894)) {
            this.obaServerTimestamp = obaServerTimestamp;
        }
        if (!ListenerUtil.mutListener.listen(9895)) {
            arrivalList = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(9899)) {
            if ((ListenerUtil.mutListener.listen(9896) ? (info != null || info.length != 0) : (info != null && info.length != 0))) {
                if (!ListenerUtil.mutListener.listen(9898)) {
                    {
                        long _loopCounter129 = 0;
                        for (ObaArrivalInfo oai : info) {
                            ListenerUtil.loopListener.listen("_loopCounter129", ++_loopCounter129);
                            if (!ListenerUtil.mutListener.listen(9897)) {
                                arrivalList.add(new ObaArrivalInfoPojo(oai));
                            }
                        }
                    }
                }
            }
        }
    }

    public List<ObaArrivalInfoPojo> getArrivalList() {
        return arrivalList;
    }

    public Long getLocalElapsedRealtimeNanos() {
        return localElapsedRealtimeNanos;
    }

    public Long getLocalSystemCurrMillis() {
        return localSystemCurrMillis;
    }

    public Long getObaServerTimestamp() {
        return obaServerTimestamp;
    }

    public String getStopId() {
        return stopId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public String getUrl() {
        return url;
    }

    public void setLocation(Location location) {
        if (!ListenerUtil.mutListener.listen(9900)) {
            locationInfo = new TravelBehaviorInfo.LocationInfo(location);
        }
    }
}
