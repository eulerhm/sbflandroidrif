/*
 * Copyright (C) 2011-2014 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com), and individual contributors.
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
package org.onebusaway.android.map.bike;

import org.onebusaway.android.app.Application;
import org.onebusaway.android.map.BaseMapController;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.util.LayerUtils;
import org.opentripplanner.api.model.Itinerary;
import org.opentripplanner.api.model.Leg;
import org.opentripplanner.api.model.VertexType;
import org.opentripplanner.routing.core.TraverseMode;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BikeshareMapController extends BaseMapController {

    private static final String TAG = "BikeshareMapController";

    private List<String> selectedBikeStationIds;

    private String mapMode;

    // Bike Station loader
    private static final int BIKE_STATIONS_LOADER = 8736;

    private BikeLoaderCallbacks bikeLoaderCallbacks;

    private BikeStationLoader bikeLoader;

    public BikeshareMapController(Callback callback) {
        if (!ListenerUtil.mutListener.listen(9994)) {
            // super(callback);
            super.mCallback = callback;
        }
        if (!ListenerUtil.mutListener.listen(9995)) {
            createLoader();
        }
    }

    @Override
    protected void createLoader() {
        if (!ListenerUtil.mutListener.listen(9996)) {
            updateData();
        }
    }

    public void showBikes(boolean showBikes) {
        if (!ListenerUtil.mutListener.listen(10017)) {
            if (showBikes) {
                if (!ListenerUtil.mutListener.listen(10016)) {
                    // DIRECTIONS and there are bike stations to display
                    if (mapMode != null) {
                        if (!ListenerUtil.mutListener.listen(10015)) {
                            if ((ListenerUtil.mutListener.listen(10009) ? (!mapMode.equals(MapParams.MODE_DIRECTIONS) && ((ListenerUtil.mutListener.listen(10008) ? (mapMode.equals(MapParams.MODE_DIRECTIONS) || ((ListenerUtil.mutListener.listen(10007) ? (selectedBikeStationIds != null && (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))) : (selectedBikeStationIds != null || (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0)))))))))) : (mapMode.equals(MapParams.MODE_DIRECTIONS) && ((ListenerUtil.mutListener.listen(10007) ? (selectedBikeStationIds != null && (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))) : (selectedBikeStationIds != null || (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))))))))) : (!mapMode.equals(MapParams.MODE_DIRECTIONS) || ((ListenerUtil.mutListener.listen(10008) ? (mapMode.equals(MapParams.MODE_DIRECTIONS) || ((ListenerUtil.mutListener.listen(10007) ? (selectedBikeStationIds != null && (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))) : (selectedBikeStationIds != null || (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0)))))))))) : (mapMode.equals(MapParams.MODE_DIRECTIONS) && ((ListenerUtil.mutListener.listen(10007) ? (selectedBikeStationIds != null && (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))) : (selectedBikeStationIds != null || (ListenerUtil.mutListener.listen(10006) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10005) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10004) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10003) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10002) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))))))))))) {
                                if (!ListenerUtil.mutListener.listen(10010)) {
                                    bikeLoaderCallbacks = new BikeLoaderCallbacks(mCallback);
                                }
                                if (!ListenerUtil.mutListener.listen(10011)) {
                                    bikeLoaderCallbacks.setBikeStationFilter(selectedBikeStationIds);
                                }
                                if (!ListenerUtil.mutListener.listen(10012)) {
                                    bikeLoader = bikeLoaderCallbacks.onCreateLoader(BIKE_STATIONS_LOADER, null);
                                }
                                if (!ListenerUtil.mutListener.listen(10013)) {
                                    bikeLoader.registerListener(0, bikeLoaderCallbacks);
                                }
                                if (!ListenerUtil.mutListener.listen(10014)) {
                                    bikeLoader.startLoading();
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10001)) {
                    if (bikeLoader != null) {
                        if (!ListenerUtil.mutListener.listen(9997)) {
                            mCallback.clearBikeStations();
                        }
                        if (!ListenerUtil.mutListener.listen(9998)) {
                            bikeLoader.stopLoading();
                        }
                        if (!ListenerUtil.mutListener.listen(9999)) {
                            bikeLoader = null;
                        }
                        if (!ListenerUtil.mutListener.listen(10000)) {
                            bikeLoaderCallbacks = null;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getMode() {
        return mapMode;
    }

    public void setMode(String mode) {
        if (!ListenerUtil.mutListener.listen(10018)) {
            mapMode = mode;
        }
    }

    @Override
    public void onHidden(boolean hidden) {
    }

    @Override
    protected Loader getLoader() {
        return bikeLoader;
    }

    @Override
    protected void updateData() {
        boolean isBikeActivated = Application.isBikeshareEnabled();
        if (!ListenerUtil.mutListener.listen(10030)) {
            if (isBikeActivated) {
                if (!ListenerUtil.mutListener.listen(10029)) {
                    if (mapMode != null) {
                        if (!ListenerUtil.mutListener.listen(10028)) {
                            if ((ListenerUtil.mutListener.listen(10025) ? (mapMode.equals(MapParams.MODE_DIRECTIONS) || ((ListenerUtil.mutListener.listen(10024) ? (selectedBikeStationIds != null || (ListenerUtil.mutListener.listen(10023) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10022) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10021) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10020) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10019) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))) : (selectedBikeStationIds != null && (ListenerUtil.mutListener.listen(10023) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10022) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10021) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10020) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10019) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0)))))))))) : (mapMode.equals(MapParams.MODE_DIRECTIONS) && ((ListenerUtil.mutListener.listen(10024) ? (selectedBikeStationIds != null || (ListenerUtil.mutListener.listen(10023) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10022) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10021) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10020) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10019) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0))))))) : (selectedBikeStationIds != null && (ListenerUtil.mutListener.listen(10023) ? (selectedBikeStationIds.size() >= 0) : (ListenerUtil.mutListener.listen(10022) ? (selectedBikeStationIds.size() <= 0) : (ListenerUtil.mutListener.listen(10021) ? (selectedBikeStationIds.size() < 0) : (ListenerUtil.mutListener.listen(10020) ? (selectedBikeStationIds.size() != 0) : (ListenerUtil.mutListener.listen(10019) ? (selectedBikeStationIds.size() == 0) : (selectedBikeStationIds.size() > 0)))))))))))) {
                                if (!ListenerUtil.mutListener.listen(10027)) {
                                    showBikes(true);
                                }
                            } else {
                                boolean isBikeSelected = LayerUtils.isBikeshareLayerVisible();
                                if (!ListenerUtil.mutListener.listen(10026)) {
                                    showBikes(isBikeSelected);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setState(Bundle args) {
        // stations that are part of the directions to display only them.
        Itinerary itinerary = (Itinerary) args.getSerializable(MapParams.ITINERARY);
        if (!ListenerUtil.mutListener.listen(10032)) {
            if (itinerary != null) {
                if (!ListenerUtil.mutListener.listen(10031)) {
                    selectedBikeStationIds = getBikeStationIdsFromItinerary(itinerary);
                }
            }
        }
    }

    private List<String> getBikeStationIdsFromItinerary(Itinerary itinerary) {
        List<String> bikeStationIds = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(10038)) {
            {
                long _loopCounter133 = 0;
                for (Leg leg : itinerary.legs) {
                    ListenerUtil.loopListener.listen("_loopCounter133", ++_loopCounter133);
                    if (!ListenerUtil.mutListener.listen(10037)) {
                        if (TraverseMode.BICYCLE.toString().equals(leg.mode)) {
                            if (!ListenerUtil.mutListener.listen(10034)) {
                                if (VertexType.BIKESHARE.equals(leg.from.vertexType)) {
                                    if (!ListenerUtil.mutListener.listen(10033)) {
                                        bikeStationIds.add(leg.from.bikeShareId);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(10036)) {
                                if (VertexType.BIKESHARE.equals(leg.to.vertexType)) {
                                    if (!ListenerUtil.mutListener.listen(10035)) {
                                        bikeStationIds.add(leg.to.bikeShareId);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return bikeStationIds;
    }
}
