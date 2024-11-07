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

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.request.ObaStopsForRouteRequest;
import org.onebusaway.android.io.request.ObaStopsForRouteResponse;
import org.onebusaway.android.io.request.ObaTripsForRouteRequest;
import org.onebusaway.android.io.request.ObaTripsForRouteResponse;
import org.onebusaway.android.map.googlemapsv2.BaseMapFragment;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.UIUtils;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RouteMapController implements MapModeController {

    private static final String TAG = "RouteMapController";

    private static final int ROUTES_LOADER = 5677;

    private static final int VEHICLES_LOADER = 5678;

    private final Callback mFragment;

    private String mRouteId;

    private boolean mZoomToRoute;

    private boolean mZoomIncludeClosestVehicle;

    private int mLineOverlayColor;

    private RoutePopup mRoutePopup;

    private int mShortAnimationDuration;

    // available in SherlockMapActivity
    private Loader<ObaStopsForRouteResponse> mRouteLoader;

    private RouteLoaderListener mRouteLoaderListener;

    private Loader<ObaTripsForRouteResponse> mVehiclesLoader;

    private VehicleLoaderListener mVehicleLoaderListener;

    private long mLastUpdatedTimeVehicles;

    public RouteMapController(Callback callback) {
        mFragment = callback;
        if (!ListenerUtil.mutListener.listen(10046)) {
            mLineOverlayColor = mFragment.getActivity().getResources().getColor(R.color.route_line_color_default);
        }
        if (!ListenerUtil.mutListener.listen(10047)) {
            mShortAnimationDuration = mFragment.getActivity().getResources().getInteger(android.R.integer.config_shortAnimTime);
        }
        if (!ListenerUtil.mutListener.listen(10048)) {
            mRoutePopup = new RoutePopup();
        }
        if (!ListenerUtil.mutListener.listen(10049)) {
            mRouteLoaderListener = new RouteLoaderListener();
        }
        if (!ListenerUtil.mutListener.listen(10050)) {
            mVehicleLoaderListener = new VehicleLoaderListener();
        }
    }

    @Override
    public void setState(Bundle args) {
        if (!ListenerUtil.mutListener.listen(10051)) {
            if (args == null) {
                throw new IllegalArgumentException("args cannot be null");
            }
        }
        String routeId = args.getString(MapParams.ROUTE_ID);
        // If the previous map zoom isn't the default, then zoom to that level as a start
        float mapZoom = args.getFloat(MapParams.ZOOM, MapParams.DEFAULT_ZOOM);
        if (!ListenerUtil.mutListener.listen(10053)) {
            if (mapZoom != MapParams.DEFAULT_ZOOM) {
                if (!ListenerUtil.mutListener.listen(10052)) {
                    mFragment.getMapView().setZoom(mapZoom);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(10054)) {
            mZoomToRoute = args.getBoolean(MapParams.ZOOM_TO_ROUTE, false);
        }
        if (!ListenerUtil.mutListener.listen(10055)) {
            mZoomIncludeClosestVehicle = args.getBoolean(MapParams.ZOOM_INCLUDE_CLOSEST_VEHICLE, false);
        }
        if (!ListenerUtil.mutListener.listen(10068)) {
            if (!routeId.equals(mRouteId)) {
                if (!ListenerUtil.mutListener.listen(10058)) {
                    if (mRouteId != null) {
                        if (!ListenerUtil.mutListener.listen(10057)) {
                            clearCurrentState();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(10059)) {
                    // Set up the new route
                    mRouteId = routeId;
                }
                if (!ListenerUtil.mutListener.listen(10060)) {
                    mRoutePopup.showLoading();
                }
                if (!ListenerUtil.mutListener.listen(10061)) {
                    mFragment.showProgress(true);
                }
                if (!ListenerUtil.mutListener.listen(10062)) {
                    // mFragment.getLoaderManager().restartLoader(ROUTES_LOADER, null, this);
                    mRouteLoader = mRouteLoaderListener.onCreateLoader(ROUTES_LOADER, null);
                }
                if (!ListenerUtil.mutListener.listen(10063)) {
                    mRouteLoader.registerListener(0, mRouteLoaderListener);
                }
                if (!ListenerUtil.mutListener.listen(10064)) {
                    mRouteLoader.startLoading();
                }
                if (!ListenerUtil.mutListener.listen(10065)) {
                    mVehiclesLoader = mVehicleLoaderListener.onCreateLoader(VEHICLES_LOADER, null);
                }
                if (!ListenerUtil.mutListener.listen(10066)) {
                    mVehiclesLoader.registerListener(0, mVehicleLoaderListener);
                }
                if (!ListenerUtil.mutListener.listen(10067)) {
                    mVehiclesLoader.startLoading();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10056)) {
                    // We are returning to the route view with the route already set, so show the header
                    mRoutePopup.show();
                }
            }
        }
    }

    /**
     * Clears the current state of the controller, so a new route can be loaded
     */
    private void clearCurrentState() {
        if (!ListenerUtil.mutListener.listen(10069)) {
            // Stop loaders and refresh handler
            mRouteLoader.stopLoading();
        }
        if (!ListenerUtil.mutListener.listen(10070)) {
            mRouteLoader.reset();
        }
        if (!ListenerUtil.mutListener.listen(10071)) {
            mVehiclesLoader.stopLoading();
        }
        if (!ListenerUtil.mutListener.listen(10072)) {
            mVehiclesLoader.reset();
        }
        if (!ListenerUtil.mutListener.listen(10073)) {
            mVehicleRefreshHandler.removeCallbacks(mVehicleRefresh);
        }
        if (!ListenerUtil.mutListener.listen(10074)) {
            // Clear the existing route and vehicle overlays
            mFragment.getMapView().removeRouteOverlay();
        }
        if (!ListenerUtil.mutListener.listen(10075)) {
            mFragment.getMapView().removeVehicleOverlay();
        }
        if (!ListenerUtil.mutListener.listen(10076)) {
            // Clear the existing stop icons, but leave the currently focused stop
            mFragment.getMapView().removeStopOverlay(false);
        }
    }

    @Override
    public String getMode() {
        return MapParams.MODE_ROUTE;
    }

    @Override
    public void destroy() {
        if (!ListenerUtil.mutListener.listen(10077)) {
            mRoutePopup.hide();
        }
        if (!ListenerUtil.mutListener.listen(10078)) {
            mFragment.getMapView().removeRouteOverlay();
        }
        if (!ListenerUtil.mutListener.listen(10079)) {
            mVehicleRefreshHandler.removeCallbacks(mVehicleRefresh);
        }
        if (!ListenerUtil.mutListener.listen(10080)) {
            mFragment.getMapView().removeVehicleOverlay();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(10081)) {
            mVehicleRefreshHandler.removeCallbacks(mVehicleRefresh);
        }
    }

    /**
     * This is called when fm.beginTransaction().hide() or fm.beginTransaction().show() is called
     *
     * @param hidden True if the fragment is now hidden, false if it is not visible.
     */
    @Override
    public void onHidden(boolean hidden) {
        if (!ListenerUtil.mutListener.listen(10084)) {
            // If the fragment is no longer visible, hide the route header - otherwise, show it
            if (hidden) {
                if (!ListenerUtil.mutListener.listen(10083)) {
                    mRoutePopup.hide();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(10082)) {
                    mRoutePopup.show();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(10085)) {
            // Make sure we schedule a future update for vehicles
            mVehicleRefreshHandler.removeCallbacks(mVehicleRefresh);
        }
        if (!ListenerUtil.mutListener.listen(10092)) {
            if ((ListenerUtil.mutListener.listen(10090) ? (mLastUpdatedTimeVehicles >= 0) : (ListenerUtil.mutListener.listen(10089) ? (mLastUpdatedTimeVehicles <= 0) : (ListenerUtil.mutListener.listen(10088) ? (mLastUpdatedTimeVehicles > 0) : (ListenerUtil.mutListener.listen(10087) ? (mLastUpdatedTimeVehicles < 0) : (ListenerUtil.mutListener.listen(10086) ? (mLastUpdatedTimeVehicles != 0) : (mLastUpdatedTimeVehicles == 0))))))) {
                if (!ListenerUtil.mutListener.listen(10091)) {
                    // to the loader to reschedule when load is complete
                    mVehicleRefreshHandler.postDelayed(mVehicleRefresh, VEHICLE_REFRESH_PERIOD);
                }
                return;
            }
        }
        long elapsedTimeMillis = TimeUnit.NANOSECONDS.toMillis((ListenerUtil.mutListener.listen(10096) ? (UIUtils.getCurrentTimeForComparison() % mLastUpdatedTimeVehicles) : (ListenerUtil.mutListener.listen(10095) ? (UIUtils.getCurrentTimeForComparison() / mLastUpdatedTimeVehicles) : (ListenerUtil.mutListener.listen(10094) ? (UIUtils.getCurrentTimeForComparison() * mLastUpdatedTimeVehicles) : (ListenerUtil.mutListener.listen(10093) ? (UIUtils.getCurrentTimeForComparison() + mLastUpdatedTimeVehicles) : (UIUtils.getCurrentTimeForComparison() - mLastUpdatedTimeVehicles))))));
        long refreshPeriod;
        if ((ListenerUtil.mutListener.listen(10101) ? (elapsedTimeMillis >= VEHICLE_REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(10100) ? (elapsedTimeMillis <= VEHICLE_REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(10099) ? (elapsedTimeMillis < VEHICLE_REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(10098) ? (elapsedTimeMillis != VEHICLE_REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(10097) ? (elapsedTimeMillis == VEHICLE_REFRESH_PERIOD) : (elapsedTimeMillis > VEHICLE_REFRESH_PERIOD))))))) {
            // Schedule an immediate update, if we're past the normal period after a load
            refreshPeriod = 100;
        } else {
            // Schedule an update so a total of VEHICLE_REFRESH_PERIOD has elapsed since the last update
            refreshPeriod = (ListenerUtil.mutListener.listen(10105) ? (VEHICLE_REFRESH_PERIOD % elapsedTimeMillis) : (ListenerUtil.mutListener.listen(10104) ? (VEHICLE_REFRESH_PERIOD / elapsedTimeMillis) : (ListenerUtil.mutListener.listen(10103) ? (VEHICLE_REFRESH_PERIOD * elapsedTimeMillis) : (ListenerUtil.mutListener.listen(10102) ? (VEHICLE_REFRESH_PERIOD + elapsedTimeMillis) : (VEHICLE_REFRESH_PERIOD - elapsedTimeMillis)))));
        }
        if (!ListenerUtil.mutListener.listen(10106)) {
            mVehicleRefreshHandler.postDelayed(mVehicleRefresh, refreshPeriod);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(10107)) {
            outState.putString(MapParams.ROUTE_ID, mRouteId);
        }
        if (!ListenerUtil.mutListener.listen(10108)) {
            outState.putBoolean(MapParams.ZOOM_TO_ROUTE, mZoomToRoute);
        }
        if (!ListenerUtil.mutListener.listen(10109)) {
            outState.putBoolean(MapParams.ZOOM_INCLUDE_CLOSEST_VEHICLE, mZoomIncludeClosestVehicle);
        }
        Location centerLocation = mFragment.getMapView().getMapCenterAsLocation();
        if (!ListenerUtil.mutListener.listen(10110)) {
            outState.putDouble(MapParams.CENTER_LAT, centerLocation.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(10111)) {
            outState.putDouble(MapParams.CENTER_LON, centerLocation.getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(10112)) {
            outState.putFloat(MapParams.ZOOM, mFragment.getMapView().getZoomLevelAsFloat());
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(10113)) {
            if (savedInstanceState == null) {
                return;
            }
        }
        String stopId = savedInstanceState.getString(MapParams.STOP_ID);
        if (!ListenerUtil.mutListener.listen(10129)) {
            if (stopId == null) {
                float mapZoom = savedInstanceState.getFloat(MapParams.ZOOM, MapParams.DEFAULT_ZOOM);
                if (!ListenerUtil.mutListener.listen(10115)) {
                    if (mapZoom != MapParams.DEFAULT_ZOOM) {
                        if (!ListenerUtil.mutListener.listen(10114)) {
                            mFragment.getMapView().setZoom(mapZoom);
                        }
                    }
                }
                double lat = savedInstanceState.getDouble(MapParams.CENTER_LAT);
                double lon = savedInstanceState.getDouble(MapParams.CENTER_LON);
                if (!ListenerUtil.mutListener.listen(10128)) {
                    if ((ListenerUtil.mutListener.listen(10126) ? ((ListenerUtil.mutListener.listen(10120) ? (lat >= 0.0d) : (ListenerUtil.mutListener.listen(10119) ? (lat <= 0.0d) : (ListenerUtil.mutListener.listen(10118) ? (lat > 0.0d) : (ListenerUtil.mutListener.listen(10117) ? (lat < 0.0d) : (ListenerUtil.mutListener.listen(10116) ? (lat == 0.0d) : (lat != 0.0d)))))) || (ListenerUtil.mutListener.listen(10125) ? (lon >= 0.0d) : (ListenerUtil.mutListener.listen(10124) ? (lon <= 0.0d) : (ListenerUtil.mutListener.listen(10123) ? (lon > 0.0d) : (ListenerUtil.mutListener.listen(10122) ? (lon < 0.0d) : (ListenerUtil.mutListener.listen(10121) ? (lon == 0.0d) : (lon != 0.0d))))))) : ((ListenerUtil.mutListener.listen(10120) ? (lat >= 0.0d) : (ListenerUtil.mutListener.listen(10119) ? (lat <= 0.0d) : (ListenerUtil.mutListener.listen(10118) ? (lat > 0.0d) : (ListenerUtil.mutListener.listen(10117) ? (lat < 0.0d) : (ListenerUtil.mutListener.listen(10116) ? (lat == 0.0d) : (lat != 0.0d)))))) && (ListenerUtil.mutListener.listen(10125) ? (lon >= 0.0d) : (ListenerUtil.mutListener.listen(10124) ? (lon <= 0.0d) : (ListenerUtil.mutListener.listen(10123) ? (lon > 0.0d) : (ListenerUtil.mutListener.listen(10122) ? (lon < 0.0d) : (ListenerUtil.mutListener.listen(10121) ? (lon == 0.0d) : (lon != 0.0d))))))))) {
                        Location location = LocationUtils.makeLocation(lat, lon);
                        if (!ListenerUtil.mutListener.listen(10127)) {
                            mFragment.getMapView().setMapCenter(location, false, false);
                        }
                    }
                }
            }
        }
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

    // 
    private class RoutePopup {

        private final Activity mActivity;

        private final View mView;

        private final TextView mRouteShortName;

        private final TextView mRouteLongName;

        private final TextView mAgencyName;

        private final ProgressBar mProgressBar;

        // Prevents completely hiding vehicle markers at top of route
        private int VEHICLE_MARKER_PADDING;

        RoutePopup() {
            mActivity = mFragment.getActivity();
            float paddingDp = (ListenerUtil.mutListener.listen(10133) ? (mActivity.getResources().getDimension(R.dimen.map_route_vehicle_markers_padding) % mActivity.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(10132) ? (mActivity.getResources().getDimension(R.dimen.map_route_vehicle_markers_padding) * mActivity.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(10131) ? (mActivity.getResources().getDimension(R.dimen.map_route_vehicle_markers_padding) - mActivity.getResources().getDisplayMetrics().density) : (ListenerUtil.mutListener.listen(10130) ? (mActivity.getResources().getDimension(R.dimen.map_route_vehicle_markers_padding) + mActivity.getResources().getDisplayMetrics().density) : (mActivity.getResources().getDimension(R.dimen.map_route_vehicle_markers_padding) / mActivity.getResources().getDisplayMetrics().density)))));
            if (!ListenerUtil.mutListener.listen(10134)) {
                VEHICLE_MARKER_PADDING = UIUtils.dpToPixels(mActivity, paddingDp);
            }
            mView = mActivity.findViewById(R.id.route_info);
            if (!ListenerUtil.mutListener.listen(10135)) {
                mFragment.getMapView().setPadding(null, mView.getHeight() + VEHICLE_MARKER_PADDING, null, null);
            }
            mRouteShortName = (TextView) mView.findViewById(R.id.short_name);
            mRouteLongName = (TextView) mView.findViewById(R.id.long_name);
            mAgencyName = (TextView) mView.findViewById(R.id.agency);
            mProgressBar = (ProgressBar) mView.findViewById(R.id.route_info_loading_spinner);
            // Make sure the cancel button is shown
            View cancel = mView.findViewById(R.id.cancel_route_mode);
            if (!ListenerUtil.mutListener.listen(10136)) {
                cancel.setVisibility(View.VISIBLE);
            }
            if (!ListenerUtil.mutListener.listen(10142)) {
                cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ObaMapView obaMapView = mFragment.getMapView();
                        // We want to preserve the current zoom and center.
                        Bundle bundle = new Bundle();
                        if (!ListenerUtil.mutListener.listen(10137)) {
                            bundle.putBoolean(MapParams.DO_N0T_CENTER_ON_LOCATION, true);
                        }
                        if (!ListenerUtil.mutListener.listen(10138)) {
                            bundle.putFloat(MapParams.ZOOM, obaMapView.getZoomLevelAsFloat());
                        }
                        Location point = obaMapView.getMapCenterAsLocation();
                        if (!ListenerUtil.mutListener.listen(10139)) {
                            bundle.putDouble(MapParams.CENTER_LAT, point.getLatitude());
                        }
                        if (!ListenerUtil.mutListener.listen(10140)) {
                            bundle.putDouble(MapParams.CENTER_LON, point.getLongitude());
                        }
                        if (!ListenerUtil.mutListener.listen(10141)) {
                            mFragment.setMapMode(MapParams.MODE_STOP, bundle);
                        }
                    }
                });
            }
        }

        void showLoading() {
            if (!ListenerUtil.mutListener.listen(10143)) {
                mFragment.getMapView().setPadding(null, mView.getHeight() + VEHICLE_MARKER_PADDING, null, null);
            }
            if (!ListenerUtil.mutListener.listen(10144)) {
                UIUtils.hideViewWithoutAnimation(mRouteShortName);
            }
            if (!ListenerUtil.mutListener.listen(10145)) {
                UIUtils.hideViewWithoutAnimation(mRouteLongName);
            }
            if (!ListenerUtil.mutListener.listen(10146)) {
                UIUtils.showViewWithoutAnimation(mView);
            }
            if (!ListenerUtil.mutListener.listen(10147)) {
                UIUtils.showViewWithoutAnimation(mProgressBar);
            }
        }

        /**
         * Show the route header and populate it with the provided information
         * @param route route information to show in the header
         * @param agencyName agency name to show in the header
         */
        void show(ObaRoute route, String agencyName) {
            if (!ListenerUtil.mutListener.listen(10148)) {
                mRouteShortName.setText(UIUtils.formatDisplayText(UIUtils.getRouteDisplayName(route)));
            }
            if (!ListenerUtil.mutListener.listen(10149)) {
                mRouteLongName.setText(UIUtils.formatDisplayText(UIUtils.getRouteDescription(route)));
            }
            if (!ListenerUtil.mutListener.listen(10150)) {
                mAgencyName.setText(agencyName);
            }
            if (!ListenerUtil.mutListener.listen(10151)) {
                show();
            }
        }

        /**
         * Show the route header with the existing route information
         */
        void show() {
            if (!ListenerUtil.mutListener.listen(10152)) {
                UIUtils.hideViewWithAnimation(mProgressBar, mShortAnimationDuration);
            }
            if (!ListenerUtil.mutListener.listen(10153)) {
                UIUtils.showViewWithAnimation(mRouteShortName, mShortAnimationDuration);
            }
            if (!ListenerUtil.mutListener.listen(10154)) {
                UIUtils.showViewWithAnimation(mRouteLongName, mShortAnimationDuration);
            }
            if (!ListenerUtil.mutListener.listen(10155)) {
                UIUtils.showViewWithAnimation(mView, mShortAnimationDuration);
            }
            if (!ListenerUtil.mutListener.listen(10156)) {
                mFragment.getMapView().setPadding(null, mView.getHeight() + VEHICLE_MARKER_PADDING, null, null);
            }
        }

        void hide() {
            if (!ListenerUtil.mutListener.listen(10157)) {
                mFragment.getMapView().setPadding(null, 0, null, null);
            }
            if (!ListenerUtil.mutListener.listen(10158)) {
                UIUtils.hideViewWithAnimation(mView, mShortAnimationDuration);
            }
        }
    }

    private static final long VEHICLE_REFRESH_PERIOD = TimeUnit.SECONDS.toMillis(10);

    private final Handler mVehicleRefreshHandler = new Handler();

    private final Runnable mVehicleRefresh = new Runnable() {

        public void run() {
            if (!ListenerUtil.mutListener.listen(10159)) {
                refresh();
            }
        }
    };

    /**
     * Refresh vehicle data from the OBA server
     */
    private void refresh() {
        if (!ListenerUtil.mutListener.listen(10161)) {
            if (mVehiclesLoader != null) {
                if (!ListenerUtil.mutListener.listen(10160)) {
                    mVehiclesLoader.onContentChanged();
                }
            }
        }
    }

    private static class RoutesLoader extends AsyncTaskLoader<ObaStopsForRouteResponse> {

        private final String mRouteId;

        public RoutesLoader(Context context, String routeId) {
            super(context);
            mRouteId = routeId;
        }

        @Override
        public ObaStopsForRouteResponse loadInBackground() {
            if (!ListenerUtil.mutListener.listen(10164)) {
                if ((ListenerUtil.mutListener.listen(10162) ? (Application.get().getCurrentRegion() == null || TextUtils.isEmpty(Application.get().getCustomApiUrl())) : (Application.get().getCurrentRegion() == null && TextUtils.isEmpty(Application.get().getCustomApiUrl())))) {
                    if (!ListenerUtil.mutListener.listen(10163)) {
                        // We don't have region info or manually entered API to know what server to contact
                        Log.d(TAG, "Trying to load stops for route from server " + "without OBA REST API endpoint, aborting...");
                    }
                    return null;
                }
            }
            // Make OBA REST API call to the server and return result
            return new ObaStopsForRouteRequest.Builder(getContext(), mRouteId).setIncludeShapes(true).build().call();
        }

        @Override
        public void deliverResult(ObaStopsForRouteResponse data) {
            if (!ListenerUtil.mutListener.listen(10165)) {
                // mResponse = data;
                super.deliverResult(data);
            }
        }

        @Override
        public void onStartLoading() {
            if (!ListenerUtil.mutListener.listen(10166)) {
                forceLoad();
            }
        }
    }

    class RouteLoaderListener implements LoaderManager.LoaderCallbacks<ObaStopsForRouteResponse>, Loader.OnLoadCompleteListener<ObaStopsForRouteResponse> {

        @Override
        public Loader<ObaStopsForRouteResponse> onCreateLoader(int id, Bundle args) {
            return new RoutesLoader(mFragment.getActivity(), mRouteId);
        }

        @Override
        public void onLoadFinished(Loader<ObaStopsForRouteResponse> loader, ObaStopsForRouteResponse response) {
            ObaMapView obaMapView = mFragment.getMapView();
            if (!ListenerUtil.mutListener.listen(10169)) {
                if ((ListenerUtil.mutListener.listen(10167) ? (response == null && response.getCode() != ObaApi.OBA_OK) : (response == null || response.getCode() != ObaApi.OBA_OK))) {
                    if (!ListenerUtil.mutListener.listen(10168)) {
                        BaseMapFragment.showMapError(response);
                    }
                    return;
                }
            }
            ObaRoute route = response.getRoute(response.getRouteId());
            if (!ListenerUtil.mutListener.listen(10170)) {
                mRoutePopup.show(route, response.getAgency(route.getAgencyId()).getName());
            }
            if (!ListenerUtil.mutListener.listen(10172)) {
                if (route.getColor() != null) {
                    if (!ListenerUtil.mutListener.listen(10171)) {
                        mLineOverlayColor = route.getColor();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10173)) {
                obaMapView.setRouteOverlay(mLineOverlayColor, response.getShapes());
            }
            // Set the stops for this route
            List<ObaStop> stops = response.getStops();
            if (!ListenerUtil.mutListener.listen(10174)) {
                mFragment.showStops(stops, response);
            }
            if (!ListenerUtil.mutListener.listen(10175)) {
                mFragment.showProgress(false);
            }
            if (!ListenerUtil.mutListener.listen(10178)) {
                if (mZoomToRoute) {
                    if (!ListenerUtil.mutListener.listen(10176)) {
                        obaMapView.zoomToRoute();
                    }
                    if (!ListenerUtil.mutListener.listen(10177)) {
                        mZoomToRoute = false;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10179)) {
                // wait to zoom till we have the right response
                obaMapView.postInvalidate();
            }
        }

        @Override
        public void onLoaderReset(Loader<ObaStopsForRouteResponse> loader) {
            if (!ListenerUtil.mutListener.listen(10180)) {
                mFragment.getMapView().removeRouteOverlay();
            }
            if (!ListenerUtil.mutListener.listen(10181)) {
                mFragment.getMapView().removeVehicleOverlay();
            }
        }

        @Override
        public void onLoadComplete(Loader<ObaStopsForRouteResponse> loader, ObaStopsForRouteResponse response) {
            if (!ListenerUtil.mutListener.listen(10182)) {
                onLoadFinished(loader, response);
            }
        }
    }

    private static class VehiclesLoader extends AsyncTaskLoader<ObaTripsForRouteResponse> {

        private final String mRouteId;

        public VehiclesLoader(Context context, String routeId) {
            super(context);
            mRouteId = routeId;
        }

        @Override
        public ObaTripsForRouteResponse loadInBackground() {
            if (!ListenerUtil.mutListener.listen(10185)) {
                if ((ListenerUtil.mutListener.listen(10183) ? (Application.get().getCurrentRegion() == null || TextUtils.isEmpty(Application.get().getCustomApiUrl())) : (Application.get().getCurrentRegion() == null && TextUtils.isEmpty(Application.get().getCustomApiUrl())))) {
                    if (!ListenerUtil.mutListener.listen(10184)) {
                        // We don't have region info or manually entered API to know what server to contact
                        Log.d(TAG, "Trying to load trips (vehicles) for route from server " + "without OBA REST API endpoint, aborting...");
                    }
                    return null;
                }
            }
            // Make OBA REST API call to the server and return result
            return new ObaTripsForRouteRequest.Builder(getContext(), mRouteId).setIncludeStatus(true).build().call();
        }

        @Override
        public void deliverResult(ObaTripsForRouteResponse data) {
            if (!ListenerUtil.mutListener.listen(10186)) {
                super.deliverResult(data);
            }
        }

        @Override
        public void onStartLoading() {
            if (!ListenerUtil.mutListener.listen(10187)) {
                forceLoad();
            }
        }
    }

    class VehicleLoaderListener implements LoaderManager.LoaderCallbacks<ObaTripsForRouteResponse>, Loader.OnLoadCompleteListener<ObaTripsForRouteResponse> {

        HashSet<String> routes = new HashSet<>(1);

        @Override
        public Loader<ObaTripsForRouteResponse> onCreateLoader(int id, Bundle args) {
            return new VehiclesLoader(mFragment.getActivity(), mRouteId);
        }

        @Override
        public void onLoadFinished(Loader<ObaTripsForRouteResponse> loader, ObaTripsForRouteResponse response) {
            ObaMapView obaMapView = mFragment.getMapView();
            if (!ListenerUtil.mutListener.listen(10190)) {
                if ((ListenerUtil.mutListener.listen(10188) ? (response == null && response.getCode() != ObaApi.OBA_OK) : (response == null || response.getCode() != ObaApi.OBA_OK))) {
                    if (!ListenerUtil.mutListener.listen(10189)) {
                        BaseMapFragment.showMapError(response);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(10191)) {
                routes.clear();
            }
            if (!ListenerUtil.mutListener.listen(10192)) {
                routes.add(mRouteId);
            }
            if (!ListenerUtil.mutListener.listen(10193)) {
                obaMapView.updateVehicles(routes, response);
            }
            if (!ListenerUtil.mutListener.listen(10196)) {
                if (mZoomIncludeClosestVehicle) {
                    if (!ListenerUtil.mutListener.listen(10194)) {
                        obaMapView.zoomIncludeClosestVehicle(routes, response);
                    }
                    if (!ListenerUtil.mutListener.listen(10195)) {
                        mZoomIncludeClosestVehicle = false;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(10197)) {
                mLastUpdatedTimeVehicles = UIUtils.getCurrentTimeForComparison();
            }
            if (!ListenerUtil.mutListener.listen(10198)) {
                // Clear any pending refreshes
                mVehicleRefreshHandler.removeCallbacks(mVehicleRefresh);
            }
            if (!ListenerUtil.mutListener.listen(10199)) {
                // Post an update
                mVehicleRefreshHandler.postDelayed(mVehicleRefresh, VEHICLE_REFRESH_PERIOD);
            }
        }

        @Override
        public void onLoaderReset(Loader<ObaTripsForRouteResponse> loader) {
            if (!ListenerUtil.mutListener.listen(10200)) {
                mFragment.getMapView().removeVehicleOverlay();
            }
        }

        @Override
        public void onLoadComplete(Loader<ObaTripsForRouteResponse> loader, ObaTripsForRouteResponse response) {
            if (!ListenerUtil.mutListener.listen(10201)) {
                onLoadFinished(loader, response);
            }
        }
    }
}
