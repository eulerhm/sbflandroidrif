package fr.free.nrw.commons.explore.map;

import static fr.free.nrw.commons.utils.LengthUtils.computeDistanceBetween;
import static fr.free.nrw.commons.utils.LengthUtils.formatDistanceBetween;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import fr.free.nrw.commons.MapController;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.nearby.NearbyBaseMarker;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.utils.ImageUtils;
import fr.free.nrw.commons.utils.LocationUtils;
import fr.free.nrw.commons.utils.PlaceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExploreMapController extends MapController {

    private final ExploreMapCalls exploreMapCalls;

    // Can be current and camera target on search this area button is used
    public LatLng latestSearchLocation;

    // current location of user
    public LatLng currentLocation;

    // Any last search radius
    public double latestSearchRadius = 0;

    // Search radius of only searches around current location
    public double currentLocationSearchRadius = 0;

    @Inject
    public ExploreMapController(ExploreMapCalls explorePlaces) {
        this.exploreMapCalls = explorePlaces;
    }

    /**
     * Takes location as parameter and returns ExplorePlaces info that holds curLatLng, mediaList,
     * explorePlaceList and boundaryCoordinates
     *
     * @param curLatLng                     is current geolocation
     * @param searchLatLng                  is the location that we want to search around
     * @param checkingAroundCurrentLocation is a boolean flag. True if we want to check around
     *                                      current location, false if another location
     * @return explorePlacesInfo info that holds curLatLng, mediaList, explorePlaceList and
     * boundaryCoordinates
     */
    public ExplorePlacesInfo loadAttractionsFromLocation(LatLng curLatLng, LatLng searchLatLng, boolean checkingAroundCurrentLocation) {
        if (!ListenerUtil.mutListener.listen(4277)) {
            if (searchLatLng == null) {
                if (!ListenerUtil.mutListener.listen(4276)) {
                    Timber.d("Loading attractions explore map, but search is null");
                }
                return null;
            }
        }
        ExplorePlacesInfo explorePlacesInfo = new ExplorePlacesInfo();
        try {
            if (!ListenerUtil.mutListener.listen(4279)) {
                explorePlacesInfo.curLatLng = curLatLng;
            }
            if (!ListenerUtil.mutListener.listen(4280)) {
                latestSearchLocation = searchLatLng;
            }
            List<Media> mediaList = exploreMapCalls.callCommonsQuery(searchLatLng);
            LatLng[] boundaryCoordinates = { // south
            mediaList.get(0).getCoordinates(), // north
            mediaList.get(0).getCoordinates(), // west
            mediaList.get(0).getCoordinates(), // east, init with a random location
            mediaList.get(0).getCoordinates() };
            if (!ListenerUtil.mutListener.listen(4312)) {
                if (searchLatLng != null) {
                    if (!ListenerUtil.mutListener.listen(4281)) {
                        Timber.d("Sorting places by distance...");
                    }
                    final Map<Media, Double> distances = new HashMap<>();
                    if (!ListenerUtil.mutListener.listen(4311)) {
                        {
                            long _loopCounter62 = 0;
                            for (Media media : mediaList) {
                                ListenerUtil.loopListener.listen("_loopCounter62", ++_loopCounter62);
                                if (!ListenerUtil.mutListener.listen(4282)) {
                                    distances.put(media, computeDistanceBetween(media.getCoordinates(), searchLatLng));
                                }
                                if (!ListenerUtil.mutListener.listen(4289)) {
                                    // Find boundaries with basic find max approach
                                    if ((ListenerUtil.mutListener.listen(4287) ? (media.getCoordinates().getLatitude() >= boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(4286) ? (media.getCoordinates().getLatitude() <= boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(4285) ? (media.getCoordinates().getLatitude() > boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(4284) ? (media.getCoordinates().getLatitude() != boundaryCoordinates[0].getLatitude()) : (ListenerUtil.mutListener.listen(4283) ? (media.getCoordinates().getLatitude() == boundaryCoordinates[0].getLatitude()) : (media.getCoordinates().getLatitude() < boundaryCoordinates[0].getLatitude()))))))) {
                                        if (!ListenerUtil.mutListener.listen(4288)) {
                                            boundaryCoordinates[0] = media.getCoordinates();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(4296)) {
                                    if ((ListenerUtil.mutListener.listen(4294) ? (media.getCoordinates().getLatitude() >= boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(4293) ? (media.getCoordinates().getLatitude() <= boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(4292) ? (media.getCoordinates().getLatitude() < boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(4291) ? (media.getCoordinates().getLatitude() != boundaryCoordinates[1].getLatitude()) : (ListenerUtil.mutListener.listen(4290) ? (media.getCoordinates().getLatitude() == boundaryCoordinates[1].getLatitude()) : (media.getCoordinates().getLatitude() > boundaryCoordinates[1].getLatitude()))))))) {
                                        if (!ListenerUtil.mutListener.listen(4295)) {
                                            boundaryCoordinates[1] = media.getCoordinates();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(4303)) {
                                    if ((ListenerUtil.mutListener.listen(4301) ? (media.getCoordinates().getLongitude() >= boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(4300) ? (media.getCoordinates().getLongitude() <= boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(4299) ? (media.getCoordinates().getLongitude() > boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(4298) ? (media.getCoordinates().getLongitude() != boundaryCoordinates[2].getLongitude()) : (ListenerUtil.mutListener.listen(4297) ? (media.getCoordinates().getLongitude() == boundaryCoordinates[2].getLongitude()) : (media.getCoordinates().getLongitude() < boundaryCoordinates[2].getLongitude()))))))) {
                                        if (!ListenerUtil.mutListener.listen(4302)) {
                                            boundaryCoordinates[2] = media.getCoordinates();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(4310)) {
                                    if ((ListenerUtil.mutListener.listen(4308) ? (media.getCoordinates().getLongitude() >= boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(4307) ? (media.getCoordinates().getLongitude() <= boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(4306) ? (media.getCoordinates().getLongitude() < boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(4305) ? (media.getCoordinates().getLongitude() != boundaryCoordinates[3].getLongitude()) : (ListenerUtil.mutListener.listen(4304) ? (media.getCoordinates().getLongitude() == boundaryCoordinates[3].getLongitude()) : (media.getCoordinates().getLongitude() > boundaryCoordinates[3].getLongitude()))))))) {
                                        if (!ListenerUtil.mutListener.listen(4309)) {
                                            boundaryCoordinates[3] = media.getCoordinates();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4313)) {
                explorePlacesInfo.mediaList = mediaList;
            }
            if (!ListenerUtil.mutListener.listen(4314)) {
                explorePlacesInfo.explorePlaceList = PlaceUtils.mediaToExplorePlace(mediaList);
            }
            if (!ListenerUtil.mutListener.listen(4315)) {
                explorePlacesInfo.boundaryCoordinates = boundaryCoordinates;
            }
            if (!ListenerUtil.mutListener.listen(4323)) {
                {
                    long _loopCounter63 = 0;
                    // Sets latestSearchRadius to maximum distance among boundaries and search location
                    for (LatLng bound : boundaryCoordinates) {
                        ListenerUtil.loopListener.listen("_loopCounter63", ++_loopCounter63);
                        double distance = LocationUtils.commonsLatLngToMapBoxLatLng(bound).distanceTo(LocationUtils.commonsLatLngToMapBoxLatLng(latestSearchLocation));
                        if (!ListenerUtil.mutListener.listen(4322)) {
                            if ((ListenerUtil.mutListener.listen(4320) ? (distance >= latestSearchRadius) : (ListenerUtil.mutListener.listen(4319) ? (distance <= latestSearchRadius) : (ListenerUtil.mutListener.listen(4318) ? (distance < latestSearchRadius) : (ListenerUtil.mutListener.listen(4317) ? (distance != latestSearchRadius) : (ListenerUtil.mutListener.listen(4316) ? (distance == latestSearchRadius) : (distance > latestSearchRadius))))))) {
                                if (!ListenerUtil.mutListener.listen(4321)) {
                                    latestSearchRadius = distance;
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4326)) {
                // Our radius searched around us, will be used to understand when user search their own location, we will follow them
                if (checkingAroundCurrentLocation) {
                    if (!ListenerUtil.mutListener.listen(4324)) {
                        currentLocationSearchRadius = latestSearchRadius;
                    }
                    if (!ListenerUtil.mutListener.listen(4325)) {
                        currentLocation = curLatLng;
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(4278)) {
                e.printStackTrace();
            }
        }
        return explorePlacesInfo;
    }

    /**
     * Loads attractions from location for map view, we need to return places in Place data type
     *
     * @return baseMarkerOptions list that holds nearby places with their icons
     */
    public static List<NearbyBaseMarker> loadAttractionsFromLocationToBaseMarkerOptions(LatLng curLatLng, final List<Place> placeList, Context context, NearbyBaseMarkerThumbCallback callback, Marker selectedMarker, ExplorePlacesInfo explorePlacesInfo) {
        List<NearbyBaseMarker> baseMarkerOptions = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(4327)) {
            if (placeList == null) {
                return baseMarkerOptions;
            }
        }
        VectorDrawableCompat vectorDrawable = null;
        try {
            if (!ListenerUtil.mutListener.listen(4328)) {
                vectorDrawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_custom_map_marker, context.getTheme());
            }
        } catch (Resources.NotFoundException e) {
        }
        if (!ListenerUtil.mutListener.listen(4344)) {
            if (vectorDrawable != null) {
                if (!ListenerUtil.mutListener.listen(4343)) {
                    {
                        long _loopCounter64 = 0;
                        for (Place explorePlace : placeList) {
                            ListenerUtil.loopListener.listen("_loopCounter64", ++_loopCounter64);
                            final NearbyBaseMarker nearbyBaseMarker = new NearbyBaseMarker();
                            String distance = formatDistanceBetween(curLatLng, explorePlace.location);
                            if (!ListenerUtil.mutListener.listen(4329)) {
                                explorePlace.setDistance(distance);
                            }
                            if (!ListenerUtil.mutListener.listen(4330)) {
                                nearbyBaseMarker.title(explorePlace.name.substring(5, explorePlace.name.lastIndexOf(".")));
                            }
                            if (!ListenerUtil.mutListener.listen(4331)) {
                                nearbyBaseMarker.position(new com.mapbox.mapboxsdk.geometry.LatLng(explorePlace.location.getLatitude(), explorePlace.location.getLongitude()));
                            }
                            if (!ListenerUtil.mutListener.listen(4332)) {
                                nearbyBaseMarker.place(explorePlace);
                            }
                            if (!ListenerUtil.mutListener.listen(4342)) {
                                Glide.with(context).asBitmap().load(explorePlace.getThumb()).placeholder(R.drawable.image_placeholder_96).apply(new RequestOptions().override(96, 96).centerCrop()).into(new CustomTarget<Bitmap>() {

                                    // We add icons to markers when bitmaps are ready
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        if (!ListenerUtil.mutListener.listen(4333)) {
                                            nearbyBaseMarker.setIcon(IconFactory.getInstance(context).fromBitmap(ImageUtils.addRedBorder(resource, 6, context)));
                                        }
                                        if (!ListenerUtil.mutListener.listen(4334)) {
                                            baseMarkerOptions.add(nearbyBaseMarker);
                                        }
                                        if (!ListenerUtil.mutListener.listen(4336)) {
                                            if (baseMarkerOptions.size() == placeList.size()) {
                                                if (!ListenerUtil.mutListener.listen(4335)) {
                                                    // if true, we added all markers to list and can trigger thumbs ready callback
                                                    callback.onNearbyBaseMarkerThumbsReady(baseMarkerOptions, explorePlacesInfo, selectedMarker);
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }

                                    // We add thumbnail icon for images that couldn't be loaded
                                    @Override
                                    public void onLoadFailed(@Nullable final Drawable errorDrawable) {
                                        if (!ListenerUtil.mutListener.listen(4337)) {
                                            super.onLoadFailed(errorDrawable);
                                        }
                                        if (!ListenerUtil.mutListener.listen(4338)) {
                                            nearbyBaseMarker.setIcon(IconFactory.getInstance(context).fromResource(R.drawable.image_placeholder_96));
                                        }
                                        if (!ListenerUtil.mutListener.listen(4339)) {
                                            baseMarkerOptions.add(nearbyBaseMarker);
                                        }
                                        if (!ListenerUtil.mutListener.listen(4341)) {
                                            if (baseMarkerOptions.size() == placeList.size()) {
                                                if (!ListenerUtil.mutListener.listen(4340)) {
                                                    // if true, we added all markers to list and can trigger thumbs ready callback
                                                    callback.onNearbyBaseMarkerThumbsReady(baseMarkerOptions, explorePlacesInfo, selectedMarker);
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
        return baseMarkerOptions;
    }

    interface NearbyBaseMarkerThumbCallback {

        // Callback to notify thumbnails of explore markers are added as icons and ready
        void onNearbyBaseMarkerThumbsReady(List<NearbyBaseMarker> baseMarkers, ExplorePlacesInfo explorePlacesInfo, Marker selectedMarker);
    }
}
