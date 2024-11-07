package fr.free.nrw.commons.nearby;

import static fr.free.nrw.commons.utils.LengthUtils.computeDistanceBetween;
import static fr.free.nrw.commons.utils.LengthUtils.formatDistanceBetween;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import fr.free.nrw.commons.MapController;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.utils.UiUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import timber.log.Timber;
import static fr.free.nrw.commons.utils.LengthUtils.computeDistanceBetween;
import static fr.free.nrw.commons.utils.LengthUtils.formatDistanceBetween;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NearbyController extends MapController {

    private static final int MAX_RESULTS = 1000;

    private final NearbyPlaces nearbyPlaces;

    // in kilometers
    public static double currentLocationSearchRadius = 10.0;

    // Users latest fetched location
    public static LatLng currentLocation;

    // Can be current and camera target on search this area button is used
    public static LatLng latestSearchLocation;

    // Any last search radius except closest result search
    public static double latestSearchRadius = 10.0;

    public static List<MarkerPlaceGroup> markerLabelList = new ArrayList<>();

    public static Map<Boolean, Marker> markerExistsMap;

    public static Map<Boolean, Marker> markerNeedPicMap;

    @Inject
    public NearbyController(NearbyPlaces nearbyPlaces) {
        this.nearbyPlaces = nearbyPlaces;
    }

    /**
     * Prepares Place list to make their distance information update later.
     *
     * @param curLatLng current location for user
     * @param searchLatLng the location user wants to search around
     * @param returnClosestResult if this search is done to find closest result or all results
     * @param customQuery if this search is done via an advanced query
     * @return NearbyPlacesInfo a variable holds Place list without distance information
     * and boundary coordinates of current Place List
     */
    public NearbyPlacesInfo loadAttractionsFromLocation(final LatLng curLatLng, final LatLng searchLatLng, final boolean returnClosestResult, final boolean checkingAroundCurrentLocation, final boolean shouldQueryForMonuments, @Nullable final String customQuery) throws Exception {
        if (!ListenerUtil.mutListener.listen(3462)) {
            Timber.d("Loading attractions near %s", searchLatLng);
        }
        NearbyPlacesInfo nearbyPlacesInfo = new NearbyPlacesInfo();
        if (!ListenerUtil.mutListener.listen(3464)) {
            if (searchLatLng == null) {
                if (!ListenerUtil.mutListener.listen(3463)) {
                    Timber.d("Loading attractions nearby, but curLatLng is null");
                }
                return null;
            }
        }
        List<Place> places = nearbyPlaces.radiusExpander(searchLatLng, Locale.getDefault().getLanguage(), returnClosestResult, shouldQueryForMonuments, customQuery);
        if (!ListenerUtil.mutListener.listen(3522)) {
            if ((ListenerUtil.mutListener.listen(3470) ? (null != places || (ListenerUtil.mutListener.listen(3469) ? (places.size() >= 0) : (ListenerUtil.mutListener.listen(3468) ? (places.size() <= 0) : (ListenerUtil.mutListener.listen(3467) ? (places.size() < 0) : (ListenerUtil.mutListener.listen(3466) ? (places.size() != 0) : (ListenerUtil.mutListener.listen(3465) ? (places.size() == 0) : (places.size() > 0))))))) : (null != places && (ListenerUtil.mutListener.listen(3469) ? (places.size() >= 0) : (ListenerUtil.mutListener.listen(3468) ? (places.size() <= 0) : (ListenerUtil.mutListener.listen(3467) ? (places.size() < 0) : (ListenerUtil.mutListener.listen(3466) ? (places.size() != 0) : (ListenerUtil.mutListener.listen(3465) ? (places.size() == 0) : (places.size() > 0))))))))) {
                LatLng[] boundaryCoordinates = { // south
                places.get(0).location, // north
                places.get(0).location, // west
                places.get(0).location, // east, init with a random location
                places.get(0).location };
                if (!ListenerUtil.mutListener.listen(3503)) {
                    if (curLatLng != null) {
                        if (!ListenerUtil.mutListener.listen(3471)) {
                            Timber.d("Sorting places by distance...");
                        }
                        final Map<Place, Double> distances = new HashMap<>();
                        if (!ListenerUtil.mutListener.listen(3501)) {
                            {
                                long _loopCounter50 = 0;
                                for (Place place : places) {
                                    ListenerUtil.loopListener.listen("_loopCounter50", ++_loopCounter50);
                                    if (!ListenerUtil.mutListener.listen(3472)) {
                                        distances.put(place, computeDistanceBetween(place.location, curLatLng));
                                    }
                                    if (!ListenerUtil.mutListener.listen(3479)) {
                                        // Find boundaries with basic find max approach
                                        if ((ListenerUtil.mutListener.listen(3477) ? (place.location.getLatitude() >= boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(3476) ? (place.location.getLatitude() <= boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(3475) ? (place.location.getLatitude() > boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(3474) ? (place.location.getLatitude() != boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(3473) ? (place.location.getLatitude() == boundaryCoordinates[0].getLatitude()) : (place.location.getLatitude() < boundaryCoordinates[0].getLatitude()))))))) {
                                            if (!ListenerUtil.mutListener.listen(3478)) {
                                                boundaryCoordinates[0] = place.location;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(3486)) {
                                        if ((ListenerUtil.mutListener.listen(3484) ? (place.location.getLatitude() >= boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(3483) ? (place.location.getLatitude() <= boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(3482) ? (place.location.getLatitude() < boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(3481) ? (place.location.getLatitude() != boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(3480) ? (place.location.getLatitude() == boundaryCoordinates[1].getLatitude()) : (place.location.getLatitude() > boundaryCoordinates[1].getLatitude()))))))) {
                                            if (!ListenerUtil.mutListener.listen(3485)) {
                                                boundaryCoordinates[1] = place.location;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(3493)) {
                                        if ((ListenerUtil.mutListener.listen(3491) ? (place.location.getLongitude() >= boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(3490) ? (place.location.getLongitude() <= boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(3489) ? (place.location.getLongitude() > boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(3488) ? (place.location.getLongitude() != boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(3487) ? (place.location.getLongitude() == boundaryCoordinates[2].getLongitude()) : (place.location.getLongitude() < boundaryCoordinates[2].getLongitude()))))))) {
                                            if (!ListenerUtil.mutListener.listen(3492)) {
                                                boundaryCoordinates[2] = place.location;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(3500)) {
                                        if ((ListenerUtil.mutListener.listen(3498) ? (place.location.getLongitude() >= boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(3497) ? (place.location.getLongitude() <= boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(3496) ? (place.location.getLongitude() < boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(3495) ? (place.location.getLongitude() != boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(3494) ? (place.location.getLongitude() == boundaryCoordinates[3].getLongitude()) : (place.location.getLongitude() > boundaryCoordinates[3].getLongitude()))))))) {
                                            if (!ListenerUtil.mutListener.listen(3499)) {
                                                boundaryCoordinates[3] = place.location;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(3502)) {
                            Collections.sort(places, (lhs, rhs) -> {
                                double lhsDistance = distances.get(lhs);
                                double rhsDistance = distances.get(rhs);
                                return (int) (lhsDistance - rhsDistance);
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3504)) {
                    nearbyPlacesInfo.curLatLng = curLatLng;
                }
                if (!ListenerUtil.mutListener.listen(3505)) {
                    nearbyPlacesInfo.searchLatLng = searchLatLng;
                }
                if (!ListenerUtil.mutListener.listen(3506)) {
                    nearbyPlacesInfo.placeList = places;
                }
                if (!ListenerUtil.mutListener.listen(3507)) {
                    nearbyPlacesInfo.boundaryCoordinates = boundaryCoordinates;
                }
                if (!ListenerUtil.mutListener.listen(3521)) {
                    // Returning closes result means we use the controller for nearby card. So no need to set search this area flags
                    if (!returnClosestResult) {
                        if (!ListenerUtil.mutListener.listen(3508)) {
                            // To remember latest search either around user or any point on map
                            latestSearchLocation = searchLatLng;
                        }
                        if (!ListenerUtil.mutListener.listen(3513)) {
                            // to meter
                            latestSearchRadius = (ListenerUtil.mutListener.listen(3512) ? (nearbyPlaces.radius % 1000) : (ListenerUtil.mutListener.listen(3511) ? (nearbyPlaces.radius / 1000) : (ListenerUtil.mutListener.listen(3510) ? (nearbyPlaces.radius - 1000) : (ListenerUtil.mutListener.listen(3509) ? (nearbyPlaces.radius + 1000) : (nearbyPlaces.radius * 1000)))));
                        }
                        if (!ListenerUtil.mutListener.listen(3520)) {
                            // Our radius searched around us, will be used to understand when user search their own location, we will follow them
                            if (checkingAroundCurrentLocation) {
                                if (!ListenerUtil.mutListener.listen(3518)) {
                                    // to meter
                                    currentLocationSearchRadius = (ListenerUtil.mutListener.listen(3517) ? (nearbyPlaces.radius % 1000) : (ListenerUtil.mutListener.listen(3516) ? (nearbyPlaces.radius / 1000) : (ListenerUtil.mutListener.listen(3515) ? (nearbyPlaces.radius - 1000) : (ListenerUtil.mutListener.listen(3514) ? (nearbyPlaces.radius + 1000) : (nearbyPlaces.radius * 1000)))));
                                }
                                if (!ListenerUtil.mutListener.listen(3519)) {
                                    currentLocation = curLatLng;
                                }
                            }
                        }
                    }
                }
            }
        }
        return nearbyPlacesInfo;
    }

    /**
     * Prepares Place list to make their distance information update later.
     *
     * @param curLatLng           current location for user
     * @param searchLatLng        the location user wants to search around
     * @param returnClosestResult if this search is done to find closest result or all results
     * @return NearbyPlacesInfo a variable holds Place list without distance information and
     * boundary coordinates of current Place List
     */
    public NearbyPlacesInfo loadAttractionsFromLocation(final LatLng curLatLng, final LatLng searchLatLng, final boolean returnClosestResult, final boolean checkingAroundCurrentLocation, final boolean shouldQueryForMonuments) throws Exception {
        return loadAttractionsFromLocation(curLatLng, searchLatLng, returnClosestResult, checkingAroundCurrentLocation, shouldQueryForMonuments, null);
    }

    /**
     * Loads attractions from location for map view, we need to return BaseMarkerOption data type.
     *
     * @param curLatLng users current location
     * @param placeList list of nearby places in Place data type
     * @return BaseMarkerOptions list that holds nearby places
     */
    public static List<NearbyBaseMarker> loadAttractionsFromLocationToBaseMarkerOptions(LatLng curLatLng, List<Place> placeList, Context context, List<Place> bookmarkplacelist) {
        List<NearbyBaseMarker> baseMarkerOptions = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(3523)) {
            if (placeList == null) {
                return baseMarkerOptions;
            }
        }
        if (!ListenerUtil.mutListener.listen(3524)) {
            placeList = placeList.subList(0, Math.min(placeList.size(), MAX_RESULTS));
        }
        VectorDrawableCompat vectorDrawable = null;
        VectorDrawableCompat vectorDrawableGreen = null;
        VectorDrawableCompat vectorDrawableGrey = null;
        VectorDrawableCompat vectorDrawableMonuments = null;
        if (!ListenerUtil.mutListener.listen(3525)) {
            vectorDrawable = null;
        }
        try {
            if (!ListenerUtil.mutListener.listen(3526)) {
                vectorDrawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_custom_map_marker, context.getTheme());
            }
            if (!ListenerUtil.mutListener.listen(3527)) {
                vectorDrawableGreen = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_custom_map_marker_green, context.getTheme());
            }
            if (!ListenerUtil.mutListener.listen(3528)) {
                vectorDrawableGrey = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_custom_map_marker_grey, context.getTheme());
            }
            if (!ListenerUtil.mutListener.listen(3529)) {
                vectorDrawableMonuments = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_custom_map_marker_monuments, context.getTheme());
            }
        } catch (Resources.NotFoundException e) {
        }
        if (!ListenerUtil.mutListener.listen(3543)) {
            if (vectorDrawable != null) {
                Bitmap icon = UiUtils.getBitmap(vectorDrawable);
                Bitmap iconGreen = UiUtils.getBitmap(vectorDrawableGreen);
                Bitmap iconGrey = UiUtils.getBitmap(vectorDrawableGrey);
                Bitmap iconMonuments = UiUtils.getBitmap(vectorDrawableMonuments);
                if (!ListenerUtil.mutListener.listen(3542)) {
                    {
                        long _loopCounter51 = 0;
                        for (Place place : placeList) {
                            ListenerUtil.loopListener.listen("_loopCounter51", ++_loopCounter51);
                            NearbyBaseMarker nearbyBaseMarker = new NearbyBaseMarker();
                            String distance = formatDistanceBetween(curLatLng, place.location);
                            if (!ListenerUtil.mutListener.listen(3530)) {
                                place.setDistance(distance);
                            }
                            if (!ListenerUtil.mutListener.listen(3531)) {
                                nearbyBaseMarker.title(place.name);
                            }
                            if (!ListenerUtil.mutListener.listen(3532)) {
                                nearbyBaseMarker.position(new com.mapbox.mapboxsdk.geometry.LatLng(place.location.getLatitude(), place.location.getLongitude()));
                            }
                            if (!ListenerUtil.mutListener.listen(3533)) {
                                nearbyBaseMarker.place(place);
                            }
                            if (!ListenerUtil.mutListener.listen(3540)) {
                                if (place.isMonument()) {
                                    if (!ListenerUtil.mutListener.listen(3539)) {
                                        nearbyBaseMarker.icon(IconFactory.getInstance(context).fromBitmap(iconMonuments));
                                    }
                                } else if (!place.pic.trim().isEmpty()) {
                                    if (!ListenerUtil.mutListener.listen(3538)) {
                                        if (iconGreen != null) {
                                            if (!ListenerUtil.mutListener.listen(3537)) {
                                                nearbyBaseMarker.icon(IconFactory.getInstance(context).fromBitmap(iconGreen));
                                            }
                                        }
                                    }
                                } else if (!place.exists) {
                                    if (!ListenerUtil.mutListener.listen(3536)) {
                                        // Means that the topic of the Wikidata item does not exist in the real world anymore, for instance it is a past event, or a place that was destroyed
                                        if (iconGrey != null) {
                                            if (!ListenerUtil.mutListener.listen(3535)) {
                                                nearbyBaseMarker.icon(IconFactory.getInstance(context).fromBitmap(iconGrey));
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3534)) {
                                        nearbyBaseMarker.icon(IconFactory.getInstance(context).fromBitmap(icon));
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3541)) {
                                baseMarkerOptions.add(nearbyBaseMarker);
                            }
                        }
                    }
                }
            }
        }
        return baseMarkerOptions;
    }

    /**
     * Updates makerLabelList item isBookmarked value
     * @param place place which is bookmarked
     * @param isBookmarked true is bookmarked, false if bookmark removed
     */
    @MainThread
    public static void updateMarkerLabelListBookmark(Place place, boolean isBookmarked) {
        if (!ListenerUtil.mutListener.listen(3546)) {
            {
                long _loopCounter52 = 0;
                for (ListIterator<MarkerPlaceGroup> iter = markerLabelList.listIterator(); iter.hasNext(); ) {
                    ListenerUtil.loopListener.listen("_loopCounter52", ++_loopCounter52);
                    MarkerPlaceGroup markerPlaceGroup = iter.next();
                    if (!ListenerUtil.mutListener.listen(3545)) {
                        if (markerPlaceGroup.getPlace().getWikiDataEntityId().equals(place.getWikiDataEntityId())) {
                            if (!ListenerUtil.mutListener.listen(3544)) {
                                iter.set(new MarkerPlaceGroup(markerPlaceGroup.getMarker(), isBookmarked, place));
                            }
                        }
                    }
                }
            }
        }
    }
}
