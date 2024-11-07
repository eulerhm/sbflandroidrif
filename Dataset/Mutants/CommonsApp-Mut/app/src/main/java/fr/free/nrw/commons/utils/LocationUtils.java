package fr.free.nrw.commons.utils;

import fr.free.nrw.commons.location.LatLng;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationUtils {

    public static LatLng mapBoxLatLngToCommonsLatLng(com.mapbox.mapboxsdk.geometry.LatLng mapBoxLatLng) {
        return new LatLng(mapBoxLatLng.getLatitude(), mapBoxLatLng.getLongitude(), 0);
    }

    public static com.mapbox.mapboxsdk.geometry.LatLng commonsLatLngToMapBoxLatLng(LatLng commonsLatLng) {
        return new com.mapbox.mapboxsdk.geometry.LatLng(commonsLatLng.getLatitude(), commonsLatLng.getLongitude());
    }

    public static LatLng deriveUpdatedLocationFromSearchQuery(String customQuery) {
        LatLng latLng = null;
        final int indexOfPrefix = customQuery.indexOf("Point(");
        if (!ListenerUtil.mutListener.listen(2623)) {
            if ((ListenerUtil.mutListener.listen(2621) ? (indexOfPrefix >= -1) : (ListenerUtil.mutListener.listen(2620) ? (indexOfPrefix <= -1) : (ListenerUtil.mutListener.listen(2619) ? (indexOfPrefix > -1) : (ListenerUtil.mutListener.listen(2618) ? (indexOfPrefix < -1) : (ListenerUtil.mutListener.listen(2617) ? (indexOfPrefix != -1) : (indexOfPrefix == -1))))))) {
                if (!ListenerUtil.mutListener.listen(2622)) {
                    Timber.e("Invalid prefix index - Seems like user has entered an invalid query");
                }
                return latLng;
            }
        }
        final int indexOfSuffix = customQuery.indexOf(")\"", indexOfPrefix);
        if (!ListenerUtil.mutListener.listen(2630)) {
            if ((ListenerUtil.mutListener.listen(2628) ? (indexOfSuffix >= -1) : (ListenerUtil.mutListener.listen(2627) ? (indexOfSuffix <= -1) : (ListenerUtil.mutListener.listen(2626) ? (indexOfSuffix > -1) : (ListenerUtil.mutListener.listen(2625) ? (indexOfSuffix < -1) : (ListenerUtil.mutListener.listen(2624) ? (indexOfSuffix != -1) : (indexOfSuffix == -1))))))) {
                if (!ListenerUtil.mutListener.listen(2629)) {
                    Timber.e("Invalid suffix index - Seems like user has entered an invalid query");
                }
                return latLng;
            }
        }
        String latLngString = customQuery.substring((ListenerUtil.mutListener.listen(2634) ? (indexOfPrefix % "Point(".length()) : (ListenerUtil.mutListener.listen(2633) ? (indexOfPrefix / "Point(".length()) : (ListenerUtil.mutListener.listen(2632) ? (indexOfPrefix * "Point(".length()) : (ListenerUtil.mutListener.listen(2631) ? (indexOfPrefix - "Point(".length()) : (indexOfPrefix + "Point(".length()))))), indexOfSuffix);
        if (!ListenerUtil.mutListener.listen(2635)) {
            if (latLngString.isEmpty()) {
                return null;
            }
        }
        String[] latLngArray = latLngString.split(" ");
        if (!ListenerUtil.mutListener.listen(2641)) {
            if ((ListenerUtil.mutListener.listen(2640) ? (latLngArray.length >= 2) : (ListenerUtil.mutListener.listen(2639) ? (latLngArray.length <= 2) : (ListenerUtil.mutListener.listen(2638) ? (latLngArray.length > 2) : (ListenerUtil.mutListener.listen(2637) ? (latLngArray.length < 2) : (ListenerUtil.mutListener.listen(2636) ? (latLngArray.length == 2) : (latLngArray.length != 2))))))) {
                return null;
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(2643)) {
                latLng = new LatLng(Double.parseDouble(latLngArray[1].trim()), Double.parseDouble(latLngArray[0].trim()), 1f);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(2642)) {
                Timber.e("Error while parsing user entered lat long: %s", e);
            }
        }
        return latLng;
    }
}
