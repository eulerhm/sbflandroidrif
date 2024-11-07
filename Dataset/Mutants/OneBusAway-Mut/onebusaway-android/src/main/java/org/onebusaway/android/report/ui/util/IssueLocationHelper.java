/*
* Copyright (C) 2014-2015 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.report.ui.util;

import org.onebusaway.android.io.elements.ObaStop;
import android.location.Location;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * IssueLocationHelper is responsible to handle multiple markers on the map
 * BaseMapFragment only adds markers when a bus stop clicked. However, we need to add
 * markers if a user clicks on the map
 */
public class IssueLocationHelper {

    private int markerId = -1;

    private ObaStop obaStop;

    private Location markerPosition;

    private Callback callback;

    public interface Callback {

        /**
         * Called when the marker is going to be cleared from the map
         * @param markerId the created marker id from BaseMapFragment.addMarker method
         */
        void onClearMarker(int markerId);
    }

    public IssueLocationHelper(Location markerPosition, Callback callback) {
        if (!ListenerUtil.mutListener.listen(10888)) {
            this.markerPosition = markerPosition;
        }
        if (!ListenerUtil.mutListener.listen(10889)) {
            this.callback = callback;
        }
    }

    public void handleMarkerUpdate(int markerId) {
        if (!ListenerUtil.mutListener.listen(10890)) {
            clearMarkers();
        }
        if (!ListenerUtil.mutListener.listen(10891)) {
            this.setMarkerId(markerId);
        }
    }

    public void updateMarkerPosition(Location markerPosition, ObaStop obaStop) {
        if (!ListenerUtil.mutListener.listen(10892)) {
            clearMarkers();
        }
        if (!ListenerUtil.mutListener.listen(10893)) {
            setMarkerPosition(markerPosition);
        }
        if (!ListenerUtil.mutListener.listen(10894)) {
            setObaStop(obaStop);
        }
    }

    public Location getIssueLocation() {
        if (obaStop != null) {
            return obaStop.getLocation();
        } else {
            return markerPosition;
        }
    }

    public void clearMarkers() {
        if (!ListenerUtil.mutListener.listen(10902)) {
            if ((ListenerUtil.mutListener.listen(10899) ? (markerId >= -1) : (ListenerUtil.mutListener.listen(10898) ? (markerId <= -1) : (ListenerUtil.mutListener.listen(10897) ? (markerId > -1) : (ListenerUtil.mutListener.listen(10896) ? (markerId < -1) : (ListenerUtil.mutListener.listen(10895) ? (markerId == -1) : (markerId != -1))))))) {
                if (!ListenerUtil.mutListener.listen(10900)) {
                    callback.onClearMarker(markerId);
                }
                if (!ListenerUtil.mutListener.listen(10901)) {
                    markerId = -1;
                }
            }
        }
    }

    public void setMarkerId(int markerId) {
        if (!ListenerUtil.mutListener.listen(10903)) {
            this.markerId = markerId;
        }
    }

    public ObaStop getObaStop() {
        return obaStop;
    }

    public void setObaStop(ObaStop obaStop) {
        if (!ListenerUtil.mutListener.listen(10904)) {
            this.obaStop = obaStop;
        }
    }

    public void setMarkerPosition(Location markerPosition) {
        if (!ListenerUtil.mutListener.listen(10905)) {
            this.markerPosition = markerPosition;
        }
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        if (!ListenerUtil.mutListener.listen(10906)) {
            this.callback = callback;
        }
    }
}
