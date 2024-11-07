/*
* Copyright (C) Sean J. Barbeau (sjbarbeau@gmail.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.onebusaway.android.map.bike;

import org.onebusaway.android.io.request.bike.OtpBikeStationRequest;
import org.onebusaway.android.io.request.bike.OtpBikeStationResponse;
import org.opentripplanner.routing.bike_rental.BikeRentalStation;
import android.content.Context;
import android.location.Location;
import java.util.List;
import androidx.loader.content.AsyncTaskLoader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BikeStationLoader extends AsyncTaskLoader<List<BikeRentalStation>> {

    private Location lowerLeft, upperRight;

    /**
     * @param context
     * @param southWest southwest corner on the map in lat long
     * @param northEast northeast corner on the map in lat long
     */
    public BikeStationLoader(Context context, Location southWest, Location northEast) {
        super(context);
        if (!ListenerUtil.mutListener.listen(10039)) {
            updateCoordinates(southWest, northEast);
        }
    }

    @Override
    public List<BikeRentalStation> loadInBackground() {
        OtpBikeStationResponse list = OtpBikeStationRequest.newRequest(getContext(), lowerLeft, upperRight).call();
        return list.stations;
    }

    @Override
    public void deliverResult(List<BikeRentalStation> data) {
        if (!ListenerUtil.mutListener.listen(10040)) {
            super.deliverResult(data);
        }
    }

    @Override
    public void onStartLoading() {
        if (!ListenerUtil.mutListener.listen(10041)) {
            forceLoad();
        }
    }

    /**
     * Update the bounding box to be used to load the bike stations.  This method is usually called
     * as a result of a map changing its location and/or zoom.
     *
     * Calls to this method forces the data to be reloaded.
     *
     * @param southWest south west corner of the bounding box
     * @param northEast north east corder of the bounding box
     */
    public void update(Location southWest, Location northEast) {
        if (!ListenerUtil.mutListener.listen(10042)) {
            updateCoordinates(southWest, northEast);
        }
        if (!ListenerUtil.mutListener.listen(10043)) {
            onContentChanged();
        }
    }

    /**
     * Updates the bounding box, converting the names from southWest/northEast to
     * lowerLeft/upperRight
     *
     * @param southWest
     * @param northEast
     */
    private void updateCoordinates(Location southWest, Location northEast) {
        if (!ListenerUtil.mutListener.listen(10044)) {
            this.lowerLeft = southWest;
        }
        if (!ListenerUtil.mutListener.listen(10045)) {
            this.upperRight = northEast;
        }
    }
}
