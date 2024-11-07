/**
 * Copyright (C) 2016 Cambridge Systematics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.map;

import org.onebusaway.android.io.elements.ObaShape;
import org.onebusaway.android.io.elements.ObaShapeElement;
import org.onebusaway.android.util.LocationUtils;
import org.opentripplanner.api.model.EncodedPolylineBean;
import org.opentripplanner.api.model.Itinerary;
import org.opentripplanner.api.model.Leg;
import org.opentripplanner.routing.core.TraverseMode;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Used to show trip plan results on the map
 */
public class DirectionsMapController implements MapModeController {

    private static final String TAG = "DirectionsMapController";

    private final Callback mFragment;

    private Itinerary mItinerary;

    private boolean mHasRoute = false;

    private Location mCenter;

    private Set<Integer> mMarkerIds;

    public DirectionsMapController(Callback callback) {
        mFragment = callback;
        if (!ListenerUtil.mutListener.listen(10307)) {
            mMarkerIds = new HashSet<>();
        }
    }

    @Override
    public void setState(Bundle args) {
        if (!ListenerUtil.mutListener.listen(10309)) {
            if (args != null) {
                if (!ListenerUtil.mutListener.listen(10308)) {
                    mItinerary = (Itinerary) args.getSerializable(MapParams.ITINERARY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10310)) {
            setMapState();
        }
    }

    private void setMapState() {
        if (!ListenerUtil.mutListener.listen(10311)) {
            clearCurrentState();
        }
        if (!ListenerUtil.mutListener.listen(10312)) {
            if (mItinerary == null) {
                return;
            }
        }
        Leg firstLeg = mItinerary.legs.get(0);
        Leg lastLeg = mItinerary.legs.get((ListenerUtil.mutListener.listen(10316) ? (mItinerary.legs.size() % 1) : (ListenerUtil.mutListener.listen(10315) ? (mItinerary.legs.size() / 1) : (ListenerUtil.mutListener.listen(10314) ? (mItinerary.legs.size() * 1) : (ListenerUtil.mutListener.listen(10313) ? (mItinerary.legs.size() + 1) : (mItinerary.legs.size() - 1))))));
        Location start = LocationUtils.makeLocation(firstLeg.from.getLat(), firstLeg.from.getLon());
        Location end = LocationUtils.makeLocation(lastLeg.to.getLat(), lastLeg.to.getLon());
        if (!ListenerUtil.mutListener.listen(10317)) {
            mCenter = start;
        }
        if (!ListenerUtil.mutListener.listen(10326)) {
            {
                long _loopCounter134 = 0;
                for (Leg leg : mItinerary.legs) {
                    ListenerUtil.loopListener.listen("_loopCounter134", ++_loopCounter134);
                    LegShape shape = new LegShape(leg.legGeometry);
                    if (!ListenerUtil.mutListener.listen(10325)) {
                        if ((ListenerUtil.mutListener.listen(10322) ? (shape.getLength() >= 0) : (ListenerUtil.mutListener.listen(10321) ? (shape.getLength() <= 0) : (ListenerUtil.mutListener.listen(10320) ? (shape.getLength() < 0) : (ListenerUtil.mutListener.listen(10319) ? (shape.getLength() != 0) : (ListenerUtil.mutListener.listen(10318) ? (shape.getLength() == 0) : (shape.getLength() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(10323)) {
                                mHasRoute = true;
                            }
                            int color = resolveColor(leg);
                            if (!ListenerUtil.mutListener.listen(10324)) {
                                mFragment.getMapView().setRouteOverlay(color, new LegShape[] { shape }, false);
                            }
                        }
                    }
                }
            }
        }
        // but we can't use the constants directly because we can't import Google Maps classes here
        float HUE_GREEN = 120.0f;
        float HUE_RED = 0.0f;
        // Add beginning marker
        int markerId = mFragment.getMapView().addMarker(start, HUE_GREEN);
        if (!ListenerUtil.mutListener.listen(10333)) {
            if ((ListenerUtil.mutListener.listen(10331) ? (markerId >= -1) : (ListenerUtil.mutListener.listen(10330) ? (markerId <= -1) : (ListenerUtil.mutListener.listen(10329) ? (markerId > -1) : (ListenerUtil.mutListener.listen(10328) ? (markerId < -1) : (ListenerUtil.mutListener.listen(10327) ? (markerId == -1) : (markerId != -1))))))) {
                if (!ListenerUtil.mutListener.listen(10332)) {
                    // If marker was successfully added, keep track of ID so we can clear it later
                    mMarkerIds.add(markerId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10334)) {
            // Add end marker
            markerId = mFragment.getMapView().addMarker(end, HUE_RED);
        }
        if (!ListenerUtil.mutListener.listen(10341)) {
            if ((ListenerUtil.mutListener.listen(10339) ? (markerId >= -1) : (ListenerUtil.mutListener.listen(10338) ? (markerId <= -1) : (ListenerUtil.mutListener.listen(10337) ? (markerId > -1) : (ListenerUtil.mutListener.listen(10336) ? (markerId < -1) : (ListenerUtil.mutListener.listen(10335) ? (markerId == -1) : (markerId != -1))))))) {
                if (!ListenerUtil.mutListener.listen(10340)) {
                    // If marker was successfully added, keep track of ID so we can clear it later
                    mMarkerIds.add(markerId);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10342)) {
            zoom();
        }
    }

    /**
     * Clears the current state of the controller, so a new route can be loaded
     */
    private void clearCurrentState() {
        if (!ListenerUtil.mutListener.listen(10343)) {
            // Clear the existing route and vehicle overlays
            mFragment.getMapView().removeRouteOverlay();
        }
        if (!ListenerUtil.mutListener.listen(10344)) {
            mFragment.getMapView().removeVehicleOverlay();
        }
        if (!ListenerUtil.mutListener.listen(10345)) {
            mFragment.getMapView().removeStopOverlay(false);
        }
        if (!ListenerUtil.mutListener.listen(10347)) {
            {
                long _loopCounter135 = 0;
                // Clear start/end markers
                for (int i : mMarkerIds) {
                    ListenerUtil.loopListener.listen("_loopCounter135", ++_loopCounter135);
                    if (!ListenerUtil.mutListener.listen(10346)) {
                        mFragment.getMapView().removeMarker(i);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10348)) {
            mMarkerIds.clear();
        }
    }

    @Override
    public String getMode() {
        return MapParams.MODE_DIRECTIONS;
    }

    @Override
    public void destroy() {
        if (!ListenerUtil.mutListener.listen(10349)) {
            clearCurrentState();
        }
    }

    @Override
    public void onPause() {
    }

    /**
     * This is called when fm.beginTransaction().hide() or fm.beginTransaction().show() is called
     *
     * @param hidden True if the fragment is now hidden, false if it is not visible.
     */
    @Override
    public void onHidden(boolean hidden) {
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(10350)) {
            setMapState();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public void onLocation() {
    }

    @Override
    public void onNoLocation() {
    }

    @Override
    public void notifyMapChanged() {
    }

    @Override
    public void onViewStateRestored(Bundle bundle) {
    }

    private void zoom() {
        ObaMapView view = mFragment.getMapView();
        if (!ListenerUtil.mutListener.listen(10354)) {
            if (mHasRoute) {
                if (!ListenerUtil.mutListener.listen(10353)) {
                    view.zoomToItinerary();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10351)) {
                    view.setMapCenter(mCenter, false, false);
                }
                if (!ListenerUtil.mutListener.listen(10352)) {
                    view.setZoom(MapParams.DEFAULT_ZOOM);
                }
            }
        }
    }

    private static int resolveColor(Leg leg) {
        if (!ListenerUtil.mutListener.listen(10356)) {
            if (leg.routeColor != null) {
                try {
                    return Long.decode("0xFF" + leg.routeColor).intValue();
                } catch (Exception ex) {
                    if (!ListenerUtil.mutListener.listen(10355)) {
                        Log.e(TAG, "Error parsing color=" + leg.routeColor + ": " + ex.getMessage());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10357)) {
            if (TraverseMode.valueOf(leg.mode).isTransit()) {
                return Color.BLUE;
            }
        }
        return Color.GRAY;
    }

    class LegShape implements ObaShape {

        private EncodedPolylineBean bean;

        LegShape(EncodedPolylineBean bean) {
            if (!ListenerUtil.mutListener.listen(10358)) {
                this.bean = bean;
            }
        }

        @Override
        public int getLength() {
            return bean.getLength();
        }

        @Override
        public String getRawLevels() {
            return bean.getLevels();
        }

        @Override
        public List<Integer> getLevels() {
            return ObaShapeElement.decodeLevels(bean.getLevels(), bean.getLength());
        }

        @Override
        public List<Location> getPoints() {
            return ObaShapeElement.decodeLine(bean.getPoints(), bean.getLength());
        }

        @Override
        public String getRawPoints() {
            return bean.getPoints();
        }
    }
}
