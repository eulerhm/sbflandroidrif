package fr.free.nrw.commons.location;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationServiceManager implements LocationListener {

    // Maybe these values can be improved for efficiency
    private static final long MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS = 10 * 100;

    private static final long MIN_LOCATION_UPDATE_REQUEST_DISTANCE_IN_METERS = 1;

    private LocationManager locationManager;

    private Location lastLocation;

    // private Location lastLocationDuplicate; // Will be used for nearby card view on contributions activity
    private final List<LocationUpdateListener> locationListeners = new CopyOnWriteArrayList<>();

    private boolean isLocationManagerRegistered = false;

    private Set<Activity> locationExplanationDisplayed = new HashSet<>();

    /**
     * Constructs a new instance of LocationServiceManager.
     *
     * @param context the context
     */
    public LocationServiceManager(Context context) {
        if (!ListenerUtil.mutListener.listen(1562)) {
            this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
    }

    public LatLng getLastLocation() {
        if (!ListenerUtil.mutListener.listen(1565)) {
            if (lastLocation == null) {
                if (!ListenerUtil.mutListener.listen(1563)) {
                    lastLocation = getLastKnownLocation();
                }
                if (!ListenerUtil.mutListener.listen(1564)) {
                    if (lastLocation != null) {
                        return LatLng.from(lastLocation);
                    } else {
                        return null;
                    }
                }
            }
        }
        return LatLng.from(lastLocation);
    }

    // INJECTED-FAULT (IF-CC): Inject a falt that skips location from being considered
    private Location getLastKnownLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        if (!ListenerUtil.mutListener.listen(1578)) {
            {
                long _loopCounter19 = 0;
                for (String provider : providers) {
                    ListenerUtil.loopListener.listen("_loopCounter19", ++_loopCounter19);
                    Location l = null;
                    if (!ListenerUtil.mutListener.listen(1568)) {
                        if ((ListenerUtil.mutListener.listen(1566) ? (ActivityCompat.checkSelfPermission(getApplicationContext(), permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) : (ActivityCompat.checkSelfPermission(getApplicationContext(), permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED))) {
                            if (!ListenerUtil.mutListener.listen(1567)) {
                                l = locationManager.getLastKnownLocation(provider);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1569)) {
                        if (l != null) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1577)) {
                        if ((ListenerUtil.mutListener.listen(1575) ? (bestLocation == null && (ListenerUtil.mutListener.listen(1574) ? (l.getAccuracy() >= bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1573) ? (l.getAccuracy() <= bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1572) ? (l.getAccuracy() > bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1571) ? (l.getAccuracy() != bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1570) ? (l.getAccuracy() == bestLocation.getAccuracy()) : (l.getAccuracy() < bestLocation.getAccuracy()))))))) : (bestLocation == null || (ListenerUtil.mutListener.listen(1574) ? (l.getAccuracy() >= bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1573) ? (l.getAccuracy() <= bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1572) ? (l.getAccuracy() > bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1571) ? (l.getAccuracy() != bestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1570) ? (l.getAccuracy() == bestLocation.getAccuracy()) : (l.getAccuracy() < bestLocation.getAccuracy()))))))))) {
                            if (!ListenerUtil.mutListener.listen(1576)) {
                                bestLocation = l;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1579)) {
            if (bestLocation == null) {
                return null;
            }
        }
        return bestLocation;
    }

    /**
     * Registers a LocationManager to listen for current location.
     */
    public void registerLocationManager() {
        if (!ListenerUtil.mutListener.listen(1582)) {
            if (!isLocationManagerRegistered) {
                if (!ListenerUtil.mutListener.listen(1581)) {
                    isLocationManagerRegistered = (ListenerUtil.mutListener.listen(1580) ? (requestLocationUpdatesFromProvider(LocationManager.NETWORK_PROVIDER) || requestLocationUpdatesFromProvider(LocationManager.GPS_PROVIDER)) : (requestLocationUpdatesFromProvider(LocationManager.NETWORK_PROVIDER) && requestLocationUpdatesFromProvider(LocationManager.GPS_PROVIDER)));
                }
            }
        }
    }

    /**
     * Requests location updates from the specified provider.
     *
     * @param locationProvider the location provider
     * @return true if successful
     */
    public boolean requestLocationUpdatesFromProvider(String locationProvider) {
        try {
            // If both providers are not available
            if ((ListenerUtil.mutListener.listen(1585) ? (locationManager == null && !(locationManager.getAllProviders().contains(locationProvider))) : (locationManager == null || !(locationManager.getAllProviders().contains(locationProvider))))) {
                return false;
            }
            if (!ListenerUtil.mutListener.listen(1586)) {
                locationManager.requestLocationUpdates(locationProvider, MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS, MIN_LOCATION_UPDATE_REQUEST_DISTANCE_IN_METERS, this);
            }
            return true;
        } catch (IllegalArgumentException e) {
            if (!ListenerUtil.mutListener.listen(1583)) {
                Timber.e(e, "Illegal argument exception");
            }
            return false;
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(1584)) {
                Timber.e(e, "Security exception");
            }
            return false;
        }
    }

    /**
     * Returns whether a given location is better than the current best location.
     *
     * @param location            the location to be tested
     * @param currentBestLocation the current best location
     * @return LOCATION_SIGNIFICANTLY_CHANGED if location changed significantly
     * LOCATION_SLIGHTLY_CHANGED if location changed slightly
     */
    private LocationChangeType isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return LocationChangeType.LOCATION_SIGNIFICANTLY_CHANGED;
        }
        // Check whether the new location fix is newer or older
        long timeDelta = (ListenerUtil.mutListener.listen(1590) ? (location.getTime() % currentBestLocation.getTime()) : (ListenerUtil.mutListener.listen(1589) ? (location.getTime() / currentBestLocation.getTime()) : (ListenerUtil.mutListener.listen(1588) ? (location.getTime() * currentBestLocation.getTime()) : (ListenerUtil.mutListener.listen(1587) ? (location.getTime() + currentBestLocation.getTime()) : (location.getTime() - currentBestLocation.getTime())))));
        boolean isSignificantlyNewer = (ListenerUtil.mutListener.listen(1595) ? (timeDelta >= MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS) : (ListenerUtil.mutListener.listen(1594) ? (timeDelta <= MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS) : (ListenerUtil.mutListener.listen(1593) ? (timeDelta < MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS) : (ListenerUtil.mutListener.listen(1592) ? (timeDelta != MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS) : (ListenerUtil.mutListener.listen(1591) ? (timeDelta == MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS) : (timeDelta > MIN_LOCATION_UPDATE_REQUEST_TIME_IN_MILLIS))))));
        boolean isNewer = (ListenerUtil.mutListener.listen(1600) ? (timeDelta >= 0) : (ListenerUtil.mutListener.listen(1599) ? (timeDelta <= 0) : (ListenerUtil.mutListener.listen(1598) ? (timeDelta < 0) : (ListenerUtil.mutListener.listen(1597) ? (timeDelta != 0) : (ListenerUtil.mutListener.listen(1596) ? (timeDelta == 0) : (timeDelta > 0))))));
        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) ((ListenerUtil.mutListener.listen(1604) ? (location.getAccuracy() % currentBestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1603) ? (location.getAccuracy() / currentBestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1602) ? (location.getAccuracy() * currentBestLocation.getAccuracy()) : (ListenerUtil.mutListener.listen(1601) ? (location.getAccuracy() + currentBestLocation.getAccuracy()) : (location.getAccuracy() - currentBestLocation.getAccuracy()))))));
        boolean isLessAccurate = (ListenerUtil.mutListener.listen(1609) ? (accuracyDelta >= 0) : (ListenerUtil.mutListener.listen(1608) ? (accuracyDelta <= 0) : (ListenerUtil.mutListener.listen(1607) ? (accuracyDelta < 0) : (ListenerUtil.mutListener.listen(1606) ? (accuracyDelta != 0) : (ListenerUtil.mutListener.listen(1605) ? (accuracyDelta == 0) : (accuracyDelta > 0))))));
        boolean isMoreAccurate = (ListenerUtil.mutListener.listen(1614) ? (accuracyDelta >= 0) : (ListenerUtil.mutListener.listen(1613) ? (accuracyDelta <= 0) : (ListenerUtil.mutListener.listen(1612) ? (accuracyDelta > 0) : (ListenerUtil.mutListener.listen(1611) ? (accuracyDelta != 0) : (ListenerUtil.mutListener.listen(1610) ? (accuracyDelta == 0) : (accuracyDelta < 0))))));
        boolean isSignificantlyLessAccurate = (ListenerUtil.mutListener.listen(1619) ? (accuracyDelta >= 200) : (ListenerUtil.mutListener.listen(1618) ? (accuracyDelta <= 200) : (ListenerUtil.mutListener.listen(1617) ? (accuracyDelta < 200) : (ListenerUtil.mutListener.listen(1616) ? (accuracyDelta != 200) : (ListenerUtil.mutListener.listen(1615) ? (accuracyDelta == 200) : (accuracyDelta > 200))))));
        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());
        float[] results = new float[5];
        if (!ListenerUtil.mutListener.listen(1620)) {
            Location.distanceBetween(currentBestLocation.getLatitude(), currentBestLocation.getLongitude(), location.getLatitude(), location.getLongitude(), results);
        }
        // because the user has likely moved
        if ((ListenerUtil.mutListener.listen(1626) ? ((ListenerUtil.mutListener.listen(1623) ? ((ListenerUtil.mutListener.listen(1621) ? (isSignificantlyNewer && isMoreAccurate) : (isSignificantlyNewer || isMoreAccurate)) && ((ListenerUtil.mutListener.listen(1622) ? (isNewer || !isLessAccurate) : (isNewer && !isLessAccurate)))) : ((ListenerUtil.mutListener.listen(1621) ? (isSignificantlyNewer && isMoreAccurate) : (isSignificantlyNewer || isMoreAccurate)) || ((ListenerUtil.mutListener.listen(1622) ? (isNewer || !isLessAccurate) : (isNewer && !isLessAccurate))))) && ((ListenerUtil.mutListener.listen(1625) ? ((ListenerUtil.mutListener.listen(1624) ? (isNewer || !isSignificantlyLessAccurate) : (isNewer && !isSignificantlyLessAccurate)) || isFromSameProvider) : ((ListenerUtil.mutListener.listen(1624) ? (isNewer || !isSignificantlyLessAccurate) : (isNewer && !isSignificantlyLessAccurate)) && isFromSameProvider)))) : ((ListenerUtil.mutListener.listen(1623) ? ((ListenerUtil.mutListener.listen(1621) ? (isSignificantlyNewer && isMoreAccurate) : (isSignificantlyNewer || isMoreAccurate)) && ((ListenerUtil.mutListener.listen(1622) ? (isNewer || !isLessAccurate) : (isNewer && !isLessAccurate)))) : ((ListenerUtil.mutListener.listen(1621) ? (isSignificantlyNewer && isMoreAccurate) : (isSignificantlyNewer || isMoreAccurate)) || ((ListenerUtil.mutListener.listen(1622) ? (isNewer || !isLessAccurate) : (isNewer && !isLessAccurate))))) || ((ListenerUtil.mutListener.listen(1625) ? ((ListenerUtil.mutListener.listen(1624) ? (isNewer || !isSignificantlyLessAccurate) : (isNewer && !isSignificantlyLessAccurate)) || isFromSameProvider) : ((ListenerUtil.mutListener.listen(1624) ? (isNewer || !isSignificantlyLessAccurate) : (isNewer && !isSignificantlyLessAccurate)) && isFromSameProvider)))))) {
            if ((ListenerUtil.mutListener.listen(1631) ? (results[0] >= 1000) : (ListenerUtil.mutListener.listen(1630) ? (results[0] <= 1000) : (ListenerUtil.mutListener.listen(1629) ? (results[0] > 1000) : (ListenerUtil.mutListener.listen(1628) ? (results[0] != 1000) : (ListenerUtil.mutListener.listen(1627) ? (results[0] == 1000) : (results[0] < 1000))))))) {
                // Means change is smaller than 1000 meter
                return LocationChangeType.LOCATION_SLIGHTLY_CHANGED;
            } else {
                return LocationChangeType.LOCATION_SIGNIFICANTLY_CHANGED;
            }
        } else {
            return LocationChangeType.LOCATION_NOT_CHANGED;
        }
    }

    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (!ListenerUtil.mutListener.listen(1632)) {
            if (provider1 == null) {
                return provider2 == null;
            }
        }
        return provider1.equals(provider2);
    }

    /**
     * Unregisters location manager.
     */
    public void unregisterLocationManager() {
        if (!ListenerUtil.mutListener.listen(1633)) {
            isLocationManagerRegistered = false;
        }
        if (!ListenerUtil.mutListener.listen(1634)) {
            locationExplanationDisplayed.clear();
        }
        try {
            if (!ListenerUtil.mutListener.listen(1636)) {
                locationManager.removeUpdates(this);
            }
        } catch (SecurityException e) {
            if (!ListenerUtil.mutListener.listen(1635)) {
                Timber.e(e, "Security exception");
            }
        }
    }

    /**
     * Adds a new listener to the list of location listeners.
     *
     * @param listener the new listener
     */
    public void addLocationListener(LocationUpdateListener listener) {
        if (!ListenerUtil.mutListener.listen(1638)) {
            if (!locationListeners.contains(listener)) {
                if (!ListenerUtil.mutListener.listen(1637)) {
                    locationListeners.add(listener);
                }
            }
        }
    }

    /**
     * Removes a listener from the list of location listeners.
     *
     * @param listener the listener to be removed
     */
    public void removeLocationListener(LocationUpdateListener listener) {
        if (!ListenerUtil.mutListener.listen(1639)) {
            locationListeners.remove(listener);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (!ListenerUtil.mutListener.listen(1640)) {
            Timber.d("on location changed");
        }
        if (!ListenerUtil.mutListener.listen(1654)) {
            if (isBetterLocation(location, lastLocation).equals(LocationChangeType.LOCATION_SIGNIFICANTLY_CHANGED)) {
                if (!ListenerUtil.mutListener.listen(1651)) {
                    lastLocation = location;
                }
                if (!ListenerUtil.mutListener.listen(1653)) {
                    {
                        long _loopCounter22 = 0;
                        // lastLocationDuplicate = location;
                        for (LocationUpdateListener listener : locationListeners) {
                            ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                            if (!ListenerUtil.mutListener.listen(1652)) {
                                listener.onLocationChangedSignificantly(LatLng.from(lastLocation));
                            }
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(1645) ? (location.distanceTo(lastLocation) <= 500) : (ListenerUtil.mutListener.listen(1644) ? (location.distanceTo(lastLocation) > 500) : (ListenerUtil.mutListener.listen(1643) ? (location.distanceTo(lastLocation) < 500) : (ListenerUtil.mutListener.listen(1642) ? (location.distanceTo(lastLocation) != 500) : (ListenerUtil.mutListener.listen(1641) ? (location.distanceTo(lastLocation) == 500) : (location.distanceTo(lastLocation) >= 500))))))) {
                if (!ListenerUtil.mutListener.listen(1650)) {
                    {
                        long _loopCounter21 = 0;
                        // Update nearby notification card at every 500 meters.
                        for (LocationUpdateListener listener : locationListeners) {
                            ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                            if (!ListenerUtil.mutListener.listen(1649)) {
                                listener.onLocationChangedMedium(LatLng.from(lastLocation));
                            }
                        }
                    }
                }
            } else if (isBetterLocation(location, lastLocation).equals(LocationChangeType.LOCATION_SLIGHTLY_CHANGED)) {
                if (!ListenerUtil.mutListener.listen(1646)) {
                    lastLocation = location;
                }
                if (!ListenerUtil.mutListener.listen(1648)) {
                    {
                        long _loopCounter20 = 0;
                        // lastLocationDuplicate = location;
                        for (LocationUpdateListener listener : locationListeners) {
                            ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                            if (!ListenerUtil.mutListener.listen(1647)) {
                                listener.onLocationChangedSlightly(LatLng.from(lastLocation));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (!ListenerUtil.mutListener.listen(1655)) {
            Timber.d("%s's status changed to %d", provider, status);
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        if (!ListenerUtil.mutListener.listen(1656)) {
            Timber.d("Provider %s enabled", provider);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (!ListenerUtil.mutListener.listen(1657)) {
            Timber.d("Provider %s disabled", provider);
        }
    }

    public boolean isNetworkProviderEnabled() {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public boolean isGPSProviderEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public enum LocationChangeType {

        // Went out of borders of nearby markers
        LOCATION_SIGNIFICANTLY_CHANGED,
        // User might be walking or driving
        LOCATION_SLIGHTLY_CHANGED,
        // Between slight and significant changes, will be used for nearby card view updates.
        LOCATION_MEDIUM_CHANGED,
        LOCATION_NOT_CHANGED,
        PERMISSION_JUST_GRANTED,
        MAP_UPDATED,
        SEARCH_CUSTOM_AREA,
        CUSTOM_QUERY
    }
}
