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
package org.onebusaway.android.map;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.UIUtils;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class BaseMapController implements MapModeController, MapWatcher.Listener {

    private static final String TAG = "BaseMapController";

    protected Callback mCallback;

    private MapWatcher mMapWatcher;

    /**
     * GoogleApiClient being used for Location Services
     */
    private GoogleApiClient mGoogleApiClient;

    public BaseMapController() {
    }

    public BaseMapController(Callback callback) {
        if (!ListenerUtil.mutListener.listen(10359)) {
            mCallback = callback;
        }
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(10362)) {
            // Init Google Play Services as early as possible in the Fragment lifecycle to give it time
            if (api.isGooglePlayServicesAvailable(mCallback.getActivity()) == ConnectionResult.SUCCESS) {
                Context context = mCallback.getActivity();
                if (!ListenerUtil.mutListener.listen(10360)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(context);
                }
                if (!ListenerUtil.mutListener.listen(10361)) {
                    mGoogleApiClient.connect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10363)) {
            createLoader();
        }
    }

    protected abstract void createLoader();

    /**
     * Sets the initial state of where the map is focused, and it's zoom level
     */
    @Override
    public void setState(Bundle args) {
        if (!ListenerUtil.mutListener.listen(10374)) {
            if (args != null) {
                Location center = UIUtils.getMapCenter(args);
                // If the STOP_ID was set in the bundle, then we should focus on that stop
                String stopId = args.getString(MapParams.STOP_ID);
                if (!ListenerUtil.mutListener.listen(10368)) {
                    if ((ListenerUtil.mutListener.listen(10365) ? (stopId != null || center != null) : (stopId != null && center != null))) {
                        if (!ListenerUtil.mutListener.listen(10366)) {
                            mCallback.getMapView().setZoom(MapParams.DEFAULT_ZOOM);
                        }
                        if (!ListenerUtil.mutListener.listen(10367)) {
                            setMapCenter(center);
                        }
                        return;
                    }
                }
                boolean dontCenterOnLocation = args.getBoolean(MapParams.DO_N0T_CENTER_ON_LOCATION);
                if (!ListenerUtil.mutListener.listen(10370)) {
                    // Try to set map based on real-time location, unless state says no
                    if (!dontCenterOnLocation) {
                        boolean setLocation = mCallback.setMyLocation(true, false);
                        if (!ListenerUtil.mutListener.listen(10369)) {
                            if (setLocation) {
                                return;
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10373)) {
                    // If we have a previous map view, center map on that
                    if (center != null) {
                        float mapZoom = args.getFloat(MapParams.ZOOM, MapParams.DEFAULT_ZOOM);
                        if (!ListenerUtil.mutListener.listen(10371)) {
                            mCallback.getMapView().setZoom(mapZoom);
                        }
                        if (!ListenerUtil.mutListener.listen(10372)) {
                            setMapCenter(center);
                        }
                        return;
                    }
                }
            } else {
                // We don't have any state info - just center on last known location
                boolean setLocation = mCallback.setMyLocation(false, false);
                if (!ListenerUtil.mutListener.listen(10364)) {
                    if (setLocation) {
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10375)) {
            // If all else fails, just center on the region
            mCallback.zoomToRegion();
        }
    }

    /**
     * Sets the map center and loads stops for the new map view
     *
     * @param center new coordinates for the map to center on
     */
    private void setMapCenter(Location center) {
        if (!ListenerUtil.mutListener.listen(10376)) {
            mCallback.getMapView().setMapCenter(center, false, false);
        }
        if (!ListenerUtil.mutListener.listen(10377)) {
            onLocation();
        }
    }

    @Override
    public void destroy() {
        if (!ListenerUtil.mutListener.listen(10379)) {
            if (getLoader() != null) {
                if (!ListenerUtil.mutListener.listen(10378)) {
                    getLoader().reset();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10380)) {
            watchMap(false);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(10381)) {
            watchMap(false);
        }
        if (!ListenerUtil.mutListener.listen(10384)) {
            // Tear down GoogleApiClient
            if ((ListenerUtil.mutListener.listen(10382) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(10383)) {
                    mGoogleApiClient.disconnect();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(10385)) {
            watchMap(true);
        }
        if (!ListenerUtil.mutListener.listen(10388)) {
            // Make sure GoogleApiClient is connected, if available
            if ((ListenerUtil.mutListener.listen(10386) ? (mGoogleApiClient != null || !mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && !mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(10387)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
    }

    @Override
    public void onLocation() {
        if (!ListenerUtil.mutListener.listen(10389)) {
            refresh();
        }
    }

    @Override
    public void onNoLocation() {
    }

    protected abstract Loader getLoader();

    private void refresh() {
        if (!ListenerUtil.mutListener.listen(10393)) {
            // Otherwise, we need to restart the loader with the new request.
            if (mCallback != null) {
                Activity a = mCallback.getActivity();
                if (!ListenerUtil.mutListener.listen(10392)) {
                    if (a != null) {
                        if (!ListenerUtil.mutListener.listen(10391)) {
                            a.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!ListenerUtil.mutListener.listen(10390)) {
                                        updateData();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    protected abstract void updateData();

    // 
    private void watchMap(boolean watch) {
        if (!ListenerUtil.mutListener.listen(10401)) {
            // Only instantiate our own map watcher if the mapView isn't capable of watching itself
            if ((ListenerUtil.mutListener.listen(10394) ? (watch || !mCallback.getMapView().canWatchMapChanges()) : (watch && !mCallback.getMapView().canWatchMapChanges()))) {
                if (!ListenerUtil.mutListener.listen(10399)) {
                    if (mMapWatcher == null) {
                        if (!ListenerUtil.mutListener.listen(10398)) {
                            mMapWatcher = new MapWatcher(mCallback.getMapView(), this);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10400)) {
                    mMapWatcher.start();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10396)) {
                    if (mMapWatcher != null) {
                        if (!ListenerUtil.mutListener.listen(10395)) {
                            mMapWatcher.stop();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10397)) {
                    mMapWatcher = null;
                }
            }
        }
    }

    @Override
    public void onMapZoomChanging() {
    }

    @Override
    public void onMapZoomChanged() {
        if (!ListenerUtil.mutListener.listen(10402)) {
            // Log.d(TAG, "Map zoom changed");
            refresh();
        }
    }

    @Override
    public void onMapCenterChanging() {
    }

    @Override
    public void onMapCenterChanged() {
        if (!ListenerUtil.mutListener.listen(10403)) {
            // Log.d(TAG, "Map center changed.");
            refresh();
        }
    }

    @Override
    public void notifyMapChanged() {
        if (!ListenerUtil.mutListener.listen(10404)) {
            Log.d(TAG, "Map changed (called by MapView)");
        }
        if (!ListenerUtil.mutListener.listen(10405)) {
            refresh();
        }
    }
}
