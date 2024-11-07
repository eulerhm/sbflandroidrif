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

import org.onebusaway.android.map.MapModeController.Callback;
import org.opentripplanner.routing.bike_rental.BikeRentalStation;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BikeLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BikeRentalStation>>, Loader.OnLoadCompleteListener<List<BikeRentalStation>> {

    private Callback mMapFragment;

    private List<String> mBikeStationIds;

    public BikeLoaderCallbacks(Callback mapFragment) {
        if (!ListenerUtil.mutListener.listen(9976)) {
            mMapFragment = mapFragment;
        }
    }

    @Override
    public BikeStationLoader onCreateLoader(int id, Bundle args) {
        return new BikeStationLoader(mMapFragment.getActivity(), mMapFragment.getSouthWest(), mMapFragment.getNorthEast());
    }

    @Override
    public void onLoadFinished(Loader<List<BikeRentalStation>> loader, List<BikeRentalStation> response) {
        if (!ListenerUtil.mutListener.listen(9989)) {
            if (response != null) {
                if (!ListenerUtil.mutListener.listen(9988)) {
                    if (mBikeStationIds != null) {
                        if (!ListenerUtil.mutListener.listen(9987)) {
                            if ((ListenerUtil.mutListener.listen(9982) ? (mBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(9981) ? (mBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(9980) ? (mBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(9979) ? (mBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(9978) ? (mBikeStationIds.size() == 0) : (mBikeStationIds.size() > 0))))))) {
                                List<BikeRentalStation> selectedBikeStations = new ArrayList<>();
                                if (!ListenerUtil.mutListener.listen(9985)) {
                                    {
                                        long _loopCounter132 = 0;
                                        for (BikeRentalStation station : response) {
                                            ListenerUtil.loopListener.listen("_loopCounter132", ++_loopCounter132);
                                            if (!ListenerUtil.mutListener.listen(9984)) {
                                                if (mBikeStationIds.contains(station.id)) {
                                                    if (!ListenerUtil.mutListener.listen(9983)) {
                                                        selectedBikeStations.add(station);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(9986)) {
                                    mMapFragment.showBikeStations(selectedBikeStations);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(9977)) {
                            mMapFragment.showBikeStations(response);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BikeRentalStation>> loader) {
        if (!ListenerUtil.mutListener.listen(9990)) {
            mMapFragment.getMapView().removeRouteOverlay();
        }
        if (!ListenerUtil.mutListener.listen(9991)) {
            mMapFragment.getMapView().removeVehicleOverlay();
        }
    }

    @Override
    public void onLoadComplete(Loader<List<BikeRentalStation>> loader, List<BikeRentalStation> response) {
        if (!ListenerUtil.mutListener.listen(9992)) {
            onLoadFinished(loader, response);
        }
    }

    /**
     * Set a list of bike stations ids to display. All other bike stations will be igonered if this
     * list is set. This is used when showing directions that include bike rental, so that only
     * the stations that are part of the direction are displayed.
     *
     * @param ids list of bike stations id to display
     */
    public void setBikeStationFilter(List<String> ids) {
        if (!ListenerUtil.mutListener.listen(9993)) {
            mBikeStationIds = ids;
        }
    }
}
