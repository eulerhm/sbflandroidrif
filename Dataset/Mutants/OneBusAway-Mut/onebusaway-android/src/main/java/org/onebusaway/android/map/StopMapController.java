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

import com.google.android.gms.common.api.GoogleApiClient;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.request.ObaStopsForLocationRequest;
import org.onebusaway.android.io.request.ObaStopsForLocationResponse;
import org.onebusaway.android.map.googlemapsv2.BaseMapFragment;
import org.onebusaway.android.util.RegionUtils;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.List;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

final class StopsRequest {

    private final Location mCenter;

    private final double mLatSpan;

    private final double mLonSpan;

    private final double mZoomLevel;

    StopsRequest(MapModeController.ObaMapView view) {
        mCenter = view.getMapCenterAsLocation();
        mLatSpan = view.getLatitudeSpanInDecDegrees();
        mLonSpan = view.getLongitudeSpanInDecDegrees();
        mZoomLevel = view.getZoomLevelAsFloat();
    }

    Location getCenter() {
        return mCenter;
    }

    double getLatSpan() {
        return mLatSpan;
    }

    double getLonSpan() {
        return mLonSpan;
    }

    double getZoomLevel() {
        return mZoomLevel;
    }
}

final class StopsResponse {

    private final StopsRequest mRequest;

    private final ObaStopsForLocationResponse mResponse;

    StopsResponse(StopsRequest req, ObaStopsForLocationResponse response) {
        mRequest = req;
        mResponse = response;
    }

    StopsRequest getRequest() {
        return mRequest;
    }

    ObaStopsForLocationResponse getResponse() {
        return mResponse;
    }

    /**
     * Returns true if newReq also fulfills response.
     */
    boolean fulfills(StopsRequest newReq) {
        if (!ListenerUtil.mutListener.listen(10256)) {
            if (mRequest.getCenter() == null) {
                // Log.d(TAG, "No center");
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(10257)) {
            if (!mRequest.getCenter().equals(newReq.getCenter())) {
                // Log.d(TAG, "Center not the same");
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(10270)) {
            if (mResponse != null) {
                if (!ListenerUtil.mutListener.listen(10269)) {
                    if ((ListenerUtil.mutListener.listen(10263) ? (((ListenerUtil.mutListener.listen(10262) ? (newReq.getZoomLevel() >= mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10261) ? (newReq.getZoomLevel() <= mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10260) ? (newReq.getZoomLevel() < mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10259) ? (newReq.getZoomLevel() != mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10258) ? (newReq.getZoomLevel() == mRequest.getZoomLevel()) : (newReq.getZoomLevel() > mRequest.getZoomLevel()))))))) || mResponse.getLimitExceeded()) : (((ListenerUtil.mutListener.listen(10262) ? (newReq.getZoomLevel() >= mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10261) ? (newReq.getZoomLevel() <= mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10260) ? (newReq.getZoomLevel() < mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10259) ? (newReq.getZoomLevel() != mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10258) ? (newReq.getZoomLevel() == mRequest.getZoomLevel()) : (newReq.getZoomLevel() > mRequest.getZoomLevel()))))))) && mResponse.getLimitExceeded()))) {
                        // Log.d(TAG, "Zooming in -- limit exceeded");
                        return false;
                    } else if ((ListenerUtil.mutListener.listen(10268) ? (newReq.getZoomLevel() >= mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10267) ? (newReq.getZoomLevel() <= mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10266) ? (newReq.getZoomLevel() > mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10265) ? (newReq.getZoomLevel() != mRequest.getZoomLevel()) : (ListenerUtil.mutListener.listen(10264) ? (newReq.getZoomLevel() == mRequest.getZoomLevel()) : (newReq.getZoomLevel() < mRequest.getZoomLevel()))))))) {
                        // Log.d(TAG, "Zooming out");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

public class StopMapController extends BaseMapController implements LoaderManager.LoaderCallbacks<StopsResponse>, Loader.OnLoadCompleteListener<StopsResponse> {

    private static final String TAG = "StopMapController";

    private static final int STOPS_LOADER = 5678;

    // available in SherlockMapActivity
    private Loader<StopsResponse> mLoader;

    private MapWatcher mMapWatcher;

    /**
     * GoogleApiClient being used for Location Services
     */
    GoogleApiClient mGoogleApiClient;

    public StopMapController(Callback callback) {
        super(callback);
    }

    @Override
    protected void createLoader() {
        if (!ListenerUtil.mutListener.listen(10271)) {
            mLoader = onCreateLoader(STOPS_LOADER, null);
        }
        if (!ListenerUtil.mutListener.listen(10272)) {
            mLoader.registerListener(0, this);
        }
        if (!ListenerUtil.mutListener.listen(10273)) {
            mLoader.startLoading();
        }
    }

    @Override
    public String getMode() {
        return MapParams.MODE_STOP;
    }

    @Override
    public void onHidden(boolean hidden) {
    }

    @Override
    public Loader<StopsResponse> onCreateLoader(int id, Bundle args) {
        StopsLoader loader = new StopsLoader(mCallback);
        StopsRequest req = new StopsRequest(mCallback.getMapView());
        if (!ListenerUtil.mutListener.listen(10274)) {
            loader.update(req);
        }
        return loader;
    }

    protected StopsLoader getLoader() {
        // return (StopsLoader)l;
        return (StopsLoader) mLoader;
    }

    @Override
    protected void updateData() {
        StopsLoader loader = getLoader();
        if (!ListenerUtil.mutListener.listen(10276)) {
            if (loader != null) {
                StopsRequest req = new StopsRequest(mCallback.getMapView());
                if (!ListenerUtil.mutListener.listen(10275)) {
                    loader.update(req);
                }
            }
        }
    }

    @Override
    public void onLoadFinished(Loader<StopsResponse> loader, StopsResponse _response) {
        if (!ListenerUtil.mutListener.listen(10277)) {
            mCallback.showProgress(false);
        }
        final ObaStopsForLocationResponse response = _response.getResponse();
        if (!ListenerUtil.mutListener.listen(10278)) {
            if (response == null) {
                // Initial install can generate a null response if all is still ok, so do nothing (#615)
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10280)) {
            if (response.getCode() != ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(10279)) {
                    BaseMapFragment.showMapError(response);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(10282)) {
            if (response.getOutOfRange()) {
                if (!ListenerUtil.mutListener.listen(10281)) {
                    mCallback.notifyOutOfRange();
                }
                return;
            }
        }
        // versions below the version number in which this is fixed.
        Location myLocation = Application.getLastKnownLocation(mCallback.getActivity(), mGoogleApiClient);
        if (!ListenerUtil.mutListener.listen(10290)) {
            if ((ListenerUtil.mutListener.listen(10283) ? (myLocation != null || Application.get().getCurrentRegion() != null) : (myLocation != null && Application.get().getCurrentRegion() != null))) {
                // Assume user is in region unless we detect otherwise
                boolean inRegion = true;
                try {
                    if (!ListenerUtil.mutListener.listen(10285)) {
                        inRegion = RegionUtils.isLocationWithinRegion(myLocation, Application.get().getCurrentRegion());
                    }
                } catch (IllegalArgumentException e) {
                    if (!ListenerUtil.mutListener.listen(10284)) {
                        // Issue #69 - some devices are providing invalid lat/long coordinates
                        Log.e(TAG, "Invalid latitude or longitude - lat = " + myLocation.getLatitude() + ", long = " + myLocation.getLongitude());
                    }
                }
                if (!ListenerUtil.mutListener.listen(10289)) {
                    if ((ListenerUtil.mutListener.listen(10286) ? (!inRegion || Arrays.asList(response.getStops()).isEmpty()) : (!inRegion && Arrays.asList(response.getStops()).isEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(10287)) {
                            Log.d(TAG, "Device location is outside region range, notifying...");
                        }
                        if (!ListenerUtil.mutListener.listen(10288)) {
                            mCallback.notifyOutOfRange();
                        }
                        return;
                    }
                }
            }
        }
        List<ObaStop> stops = Arrays.asList(response.getStops());
        if (!ListenerUtil.mutListener.listen(10291)) {
            mCallback.showStops(stops, response);
        }
    }

    @Override
    public void onLoaderReset(Loader<StopsResponse> loader) {
        if (!ListenerUtil.mutListener.listen(10292)) {
            // Clear the overlay.
            mCallback.showStops(null, null);
        }
    }

    // Remove when adding back LoaderManager help.
    @Override
    public void onLoadComplete(Loader<StopsResponse> loader, StopsResponse response) {
        if (!ListenerUtil.mutListener.listen(10293)) {
            onLoadFinished(loader, response);
        }
    }

    // 
    private static class StopsLoader extends AsyncTaskLoader<StopsResponse> {

        private final Callback mFragment;

        private StopsRequest mRequest;

        private StopsResponse mResponse;

        public StopsLoader(Callback fragment) {
            super(fragment.getActivity());
            mFragment = fragment;
        }

        @Override
        public StopsResponse loadInBackground() {
            StopsRequest req = mRequest;
            if (!ListenerUtil.mutListener.listen(10296)) {
                if ((ListenerUtil.mutListener.listen(10294) ? (Application.get().getCurrentRegion() == null || TextUtils.isEmpty(Application.get().getCustomApiUrl())) : (Application.get().getCurrentRegion() == null && TextUtils.isEmpty(Application.get().getCustomApiUrl())))) {
                    if (!ListenerUtil.mutListener.listen(10295)) {
                        // We don't have region info or manually entered API to know what server to contact
                        Log.d(TAG, "Trying to load stops from server without " + "OBA REST API endpoint, aborting...");
                    }
                    return new StopsResponse(req, null);
                }
            }
            // Make OBA REST API call to the server and return result
            ObaStopsForLocationResponse response = new ObaStopsForLocationRequest.Builder(getContext(), req.getCenter()).setSpan(req.getLatSpan(), req.getLonSpan()).build().call();
            return new StopsResponse(req, response);
        }

        @Override
        public void deliverResult(StopsResponse data) {
            if (!ListenerUtil.mutListener.listen(10297)) {
                mResponse = data;
            }
            if (!ListenerUtil.mutListener.listen(10298)) {
                super.deliverResult(data);
            }
        }

        @Override
        public void onStartLoading() {
            if (!ListenerUtil.mutListener.listen(10300)) {
                if (takeContentChanged()) {
                    if (!ListenerUtil.mutListener.listen(10299)) {
                        forceLoad();
                    }
                }
            }
        }

        @Override
        public void onForceLoad() {
            if (!ListenerUtil.mutListener.listen(10301)) {
                mFragment.showProgress(true);
            }
            if (!ListenerUtil.mutListener.listen(10302)) {
                super.onForceLoad();
            }
        }

        public void update(StopsRequest req) {
            if (!ListenerUtil.mutListener.listen(10306)) {
                if ((ListenerUtil.mutListener.listen(10303) ? (mResponse == null && !mResponse.fulfills(req)) : (mResponse == null || !mResponse.fulfills(req)))) {
                    if (!ListenerUtil.mutListener.listen(10304)) {
                        mRequest = req;
                    }
                    if (!ListenerUtil.mutListener.listen(10305)) {
                        onContentChanged();
                    }
                }
            }
        }
    }
}
