package fr.free.nrw.commons.utils;

import android.content.Context;
import android.content.res.Configuration;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.location.LocationUpdateListener;
import fr.free.nrw.commons.nearby.Place;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MapUtils {

    public static final float ZOOM_LEVEL = 14f;

    public static final double CAMERA_TARGET_SHIFT_FACTOR_PORTRAIT = 0.005;

    public static final double CAMERA_TARGET_SHIFT_FACTOR_LANDSCAPE = 0.004;

    public static final String NETWORK_INTENT_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public static final float ZOOM_OUT = 0f;

    public static final LatLng defaultLatLng = new fr.free.nrw.commons.location.LatLng(51.50550, -0.07520, 1f);

    public static void centerMapToPlace(Place placeToCenter, MapboxMap mapBox, Place lastPlaceToCenter, Context context) {
        if (!ListenerUtil.mutListener.listen(2092)) {
            Timber.d("Map is centered to place");
        }
        final double cameraShift;
        if (!ListenerUtil.mutListener.listen(2094)) {
            if (null != placeToCenter) {
                if (!ListenerUtil.mutListener.listen(2093)) {
                    lastPlaceToCenter = placeToCenter;
                }
            }
        }
        if (null != lastPlaceToCenter) {
            final Configuration configuration = context.getResources().getConfiguration();
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                cameraShift = CAMERA_TARGET_SHIFT_FACTOR_PORTRAIT;
            } else {
                cameraShift = CAMERA_TARGET_SHIFT_FACTOR_LANDSCAPE;
            }
            final CameraPosition position = new CameraPosition.Builder().target(LocationUtils.commonsLatLngToMapBoxLatLng(new fr.free.nrw.commons.location.LatLng((ListenerUtil.mutListener.listen(2098) ? (lastPlaceToCenter.location.getLatitude() % cameraShift) : (ListenerUtil.mutListener.listen(2097) ? (lastPlaceToCenter.location.getLatitude() / cameraShift) : (ListenerUtil.mutListener.listen(2096) ? (lastPlaceToCenter.location.getLatitude() * cameraShift) : (ListenerUtil.mutListener.listen(2095) ? (lastPlaceToCenter.location.getLatitude() + cameraShift) : (lastPlaceToCenter.location.getLatitude() - cameraShift))))), lastPlaceToCenter.getLocation().getLongitude(), // Sets the new camera position
            0))).zoom(// Same zoom level
            ZOOM_LEVEL).build();
            if (!ListenerUtil.mutListener.listen(2099)) {
                mapBox.animateCamera(CameraUpdateFactory.newCameraPosition(position), 1000);
            }
        }
    }

    public static void centerMapToDefaultLatLng(MapboxMap mapBox) {
        final CameraPosition position = new CameraPosition.Builder().target(LocationUtils.commonsLatLngToMapBoxLatLng(defaultLatLng)).zoom(MapUtils.ZOOM_OUT).build();
        if (!ListenerUtil.mutListener.listen(2101)) {
            if (mapBox != null) {
                if (!ListenerUtil.mutListener.listen(2100)) {
                    mapBox.moveCamera(CameraUpdateFactory.newCameraPosition(position));
                }
            }
        }
    }

    public static void registerUnregisterLocationListener(final boolean removeLocationListener, LocationServiceManager locationManager, LocationUpdateListener locationUpdateListener) {
        try {
            if (!ListenerUtil.mutListener.listen(2109)) {
                if (removeLocationListener) {
                    if (!ListenerUtil.mutListener.listen(2106)) {
                        locationManager.unregisterLocationManager();
                    }
                    if (!ListenerUtil.mutListener.listen(2107)) {
                        locationManager.removeLocationListener(locationUpdateListener);
                    }
                    if (!ListenerUtil.mutListener.listen(2108)) {
                        Timber.d("Location service manager unregistered and removed");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2103)) {
                        locationManager.addLocationListener(locationUpdateListener);
                    }
                    if (!ListenerUtil.mutListener.listen(2104)) {
                        locationManager.registerLocationManager();
                    }
                    if (!ListenerUtil.mutListener.listen(2105)) {
                        Timber.d("Location service manager added and registered");
                    }
                }
            }
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(2102)) {
                Timber.e(e);
            }
        }
    }
}
