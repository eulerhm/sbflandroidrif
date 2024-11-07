/*
 * Copyright (C) 2014 Sean J. Barbeau (sjbarbeau@gmail.com), University of South Florida
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
package org.onebusaway.android.util;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import org.onebusaway.android.app.Application;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;
import static org.onebusaway.android.util.PermissionUtils.LOCATION_PERMISSIONS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * A helper class that keeps listeners updated with the best location available from
 * multiple providers
 */
public class LocationHelper implements com.google.android.gms.location.LocationListener, android.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public interface Listener {

        /**
         * Called every time there is an update to the best location available
         */
        void onLocationChanged(Location location);
    }

    static final String TAG = "LocationHelper";

    Context mContext;

    LocationManager mLocationManager;

    ArrayList<Listener> mListeners = new ArrayList<Listener>();

    /**
     * GoogleApiClient being used for Location Services
     */
    protected GoogleApiClient mGoogleApiClient;

    LocationRequest mLocationRequest;

    LocationCallback mLocationCallback;

    private static final int MILLISECONDS_PER_SECOND = 1000;

    private static final int UPDATE_INTERVAL_IN_SECONDS = 5;

    private long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;

    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;

    private static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;

    public LocationHelper(Context context) {
        if (!ListenerUtil.mutListener.listen(8077)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(8078)) {
            mLocationManager = (LocationManager) Application.get().getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(8079)) {
            // Create the LocationRequest object
            mLocationRequest = LocationRequest.create();
        }
        if (!ListenerUtil.mutListener.listen(8080)) {
            // Use high accuracy
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
        if (!ListenerUtil.mutListener.listen(8081)) {
            // Set the update interval to 5 seconds
            mLocationRequest.setInterval(UPDATE_INTERVAL);
        }
        if (!ListenerUtil.mutListener.listen(8082)) {
            // Set the fastest update interval to 1 second
            mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        }
    }

    /**
     * @param context
     * @param interval Faster interval in seconds.
     */
    public LocationHelper(Context context, int interval) {
        if (!ListenerUtil.mutListener.listen(8083)) {
            mContext = context;
        }
        if (!ListenerUtil.mutListener.listen(8088)) {
            UPDATE_INTERVAL = (ListenerUtil.mutListener.listen(8087) ? (interval % MILLISECONDS_PER_SECOND) : (ListenerUtil.mutListener.listen(8086) ? (interval / MILLISECONDS_PER_SECOND) : (ListenerUtil.mutListener.listen(8085) ? (interval - MILLISECONDS_PER_SECOND) : (ListenerUtil.mutListener.listen(8084) ? (interval + MILLISECONDS_PER_SECOND) : (interval * MILLISECONDS_PER_SECOND)))));
        }
        if (!ListenerUtil.mutListener.listen(8089)) {
            mLocationManager = (LocationManager) Application.get().getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(8090)) {
            setupGooglePlayServices();
        }
    }

    /**
     * Registers the provided listener for location updates, but first checks to see if Location
     * permissions are granted.  If permissions haven't been granted, returns false and does not
     * register any listeners.  After the caller has received permission from the user it can
     * call this method again.
     * @param listener listener for updates
     * @return true if permissions have been granted and the listener was registered, false if
     * permissions have not been granted and no listener was registered
     */
    public synchronized boolean registerListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(8091)) {
            if (!PermissionUtils.hasGrantedAtLeastOnePermission(mContext, LOCATION_PERMISSIONS)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(8093)) {
            // User has granted permissions - continue to register listener for location updates
            if (!mListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(8092)) {
                    mListeners.add(listener);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8100)) {
            // If this is the first listener, make sure we're monitoring the sensors to provide updates
            if ((ListenerUtil.mutListener.listen(8098) ? (mListeners.size() >= 1) : (ListenerUtil.mutListener.listen(8097) ? (mListeners.size() <= 1) : (ListenerUtil.mutListener.listen(8096) ? (mListeners.size() > 1) : (ListenerUtil.mutListener.listen(8095) ? (mListeners.size() < 1) : (ListenerUtil.mutListener.listen(8094) ? (mListeners.size() != 1) : (mListeners.size() == 1))))))) {
                if (!ListenerUtil.mutListener.listen(8099)) {
                    // Listen for location
                    registerAllProviders();
                }
            }
        }
        return true;
    }

    public synchronized void unregisterListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(8101)) {
            mListeners.remove(listener);
        }
        if (!ListenerUtil.mutListener.listen(8109)) {
            if ((ListenerUtil.mutListener.listen(8106) ? (mListeners.size() >= 0) : (ListenerUtil.mutListener.listen(8105) ? (mListeners.size() <= 0) : (ListenerUtil.mutListener.listen(8104) ? (mListeners.size() > 0) : (ListenerUtil.mutListener.listen(8103) ? (mListeners.size() < 0) : (ListenerUtil.mutListener.listen(8102) ? (mListeners.size() != 0) : (mListeners.size() == 0))))))) {
                try {
                    if (!ListenerUtil.mutListener.listen(8108)) {
                        mLocationManager.removeUpdates(this);
                    }
                } catch (SecurityException e) {
                    if (!ListenerUtil.mutListener.listen(8107)) {
                        // permissions after the listener was registered
                        Log.w(TAG, "User may have denied location permission - " + e);
                    }
                }
            }
        }
    }

    /**
     * Returns the GoogleApiClient being used for fused provider location updates
     *
     * @return the GoogleApiClient being used for fused provider location updates
     */
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public synchronized void onResume() {
        try {
            if (!ListenerUtil.mutListener.listen(8111)) {
                registerAllProviders();
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(8110)) {
                // If we resume after the user has denied location permissions, log the warning and continue
                Log.w(TAG, "User may have denied location permission - " + e);
            }
        }
    }

    public synchronized void onPause() {
        try {
            if (!ListenerUtil.mutListener.listen(8113)) {
                mLocationManager.removeUpdates(this);
            }
            if (!ListenerUtil.mutListener.listen(8118)) {
                // Tear down GoogleApiClient
                if ((ListenerUtil.mutListener.listen(8115) ? ((ListenerUtil.mutListener.listen(8114) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected())) || mLocationCallback != null) : ((ListenerUtil.mutListener.listen(8114) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected())) && mLocationCallback != null))) {
                    FusedLocationProviderClient client = getFusedLocationProviderClient(mContext);
                    if (!ListenerUtil.mutListener.listen(8116)) {
                        client.removeLocationUpdates(mLocationCallback);
                    }
                    if (!ListenerUtil.mutListener.listen(8117)) {
                        mGoogleApiClient.disconnect();
                    }
                }
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(8112)) {
                // permissions after the listener was registered
                Log.w(TAG, "User may have denied location permission - " + e);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (!ListenerUtil.mutListener.listen(8119)) {
            // stored location
            Application.setLastKnownLocation(location);
        }
        // that was just generated above)
        Location lastLocation = Application.getLastKnownLocation(mContext, mGoogleApiClient);
        if (!ListenerUtil.mutListener.listen(8123)) {
            if (lastLocation != null) {
                // We need to copy the location, it case this object is reset in Application
                Location locationForListeners = new Location("for listeners");
                if (!ListenerUtil.mutListener.listen(8120)) {
                    locationForListeners.set(lastLocation);
                }
                if (!ListenerUtil.mutListener.listen(8122)) {
                    {
                        long _loopCounter103 = 0;
                        for (Listener l : mListeners) {
                            ListenerUtil.loopListener.listen("_loopCounter103", ++_loopCounter103);
                            if (!ListenerUtil.mutListener.listen(8121)) {
                                l.onLocationChanged(locationForListeners);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    private void registerAllProviders() throws SecurityException {
        // Register the network and GPS provider (and anything else available)
        List<String> providers = mLocationManager.getProviders(true);
        if (!ListenerUtil.mutListener.listen(8125)) {
            {
                long _loopCounter104 = 0;
                for (Iterator<String> i = providers.iterator(); i.hasNext(); ) {
                    ListenerUtil.loopListener.listen("_loopCounter104", ++_loopCounter104);
                    if (!ListenerUtil.mutListener.listen(8124)) {
                        mLocationManager.requestLocationUpdates(i.next(), 0, 0, this);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8126)) {
            setupGooglePlayServices();
        }
    }

    /**
     * Request connection to Google Play Services for the fused location provider.
     * onConnected() will be called after connection.
     */
    private void setupGooglePlayServices() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(8129)) {
            if (api.isGooglePlayServicesAvailable(mContext) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(8127)) {
                    mGoogleApiClient = new GoogleApiClient.Builder(mContext).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
                }
                if (!ListenerUtil.mutListener.listen(8128)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(8130)) {
            Log.d(TAG, "Location Services connected");
        }
        // Request location updates from the fused location provider
        FusedLocationProviderClient client = getFusedLocationProviderClient(mContext);
        if (!ListenerUtil.mutListener.listen(8133)) {
            if (mLocationCallback == null) {
                if (!ListenerUtil.mutListener.listen(8132)) {
                    mLocationCallback = new LocationCallback() {

                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            if (!ListenerUtil.mutListener.listen(8131)) {
                                onLocationChanged(locationResult.getLastLocation());
                            }
                        }
                    };
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(8135)) {
                client.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(8134)) {
                // in between log the warning and continue
                Log.w(TAG, "User may have denied location permission - " + e);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
}
