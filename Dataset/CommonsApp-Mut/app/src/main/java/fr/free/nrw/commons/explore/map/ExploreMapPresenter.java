package fr.free.nrw.commons.explore.map;

import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.LOCATION_SIGNIFICANTLY_CHANGED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.MAP_UPDATED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.SEARCH_CUSTOM_AREA;
import android.location.Location;
import android.view.View;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import fr.free.nrw.commons.MapController;
import fr.free.nrw.commons.MapController.ExplorePlacesInfo;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao;
import fr.free.nrw.commons.explore.map.ExploreMapController.NearbyBaseMarkerThumbCallback;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType;
import fr.free.nrw.commons.nearby.NearbyBaseMarker;
import fr.free.nrw.commons.utils.LocationUtils;
import io.reactivex.Observable;
import java.lang.reflect.Proxy;
import java.util.List;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExploreMapPresenter implements ExploreMapContract.UserActions, NearbyBaseMarkerThumbCallback {

    BookmarkLocationsDao bookmarkLocationDao;

    private boolean isNearbyLocked;

    private LatLng curLatLng;

    private ExploreMapController exploreMapController;

    private static final ExploreMapContract.View DUMMY = (ExploreMapContract.View) Proxy.newProxyInstance(ExploreMapContract.View.class.getClassLoader(), new Class[] { ExploreMapContract.View.class }, (proxy, method, args) -> {
        if (method.getName().equals("onMyEvent")) {
            return null;
        } else if (String.class == method.getReturnType()) {
            return "";
        } else if (Integer.class == method.getReturnType()) {
            return Integer.valueOf(0);
        } else if (int.class == method.getReturnType()) {
            return 0;
        } else if (Boolean.class == method.getReturnType()) {
            return Boolean.FALSE;
        } else if (boolean.class == method.getReturnType()) {
            return false;
        } else {
            return null;
        }
    });

    private ExploreMapContract.View exploreMapFragmentView = DUMMY;

    public ExploreMapPresenter(BookmarkLocationsDao bookmarkLocationDao) {
        if (!ListenerUtil.mutListener.listen(4219)) {
            this.bookmarkLocationDao = bookmarkLocationDao;
        }
    }

    @Override
    public void updateMap(LocationChangeType locationChangeType) {
        if (!ListenerUtil.mutListener.listen(4220)) {
            Timber.d("Presenter updates map and list" + locationChangeType.toString());
        }
        if (!ListenerUtil.mutListener.listen(4222)) {
            if (isNearbyLocked) {
                if (!ListenerUtil.mutListener.listen(4221)) {
                    Timber.d("Nearby is locked, so updateMapAndList returns");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4224)) {
            if (!exploreMapFragmentView.isNetworkConnectionEstablished()) {
                if (!ListenerUtil.mutListener.listen(4223)) {
                    Timber.d("Network connection is not established");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4234)) {
            /**
             * Significant changed - Markers and current location will be updated together
             * Slightly changed - Only current position marker will be updated
             */
            if (locationChangeType.equals(LOCATION_SIGNIFICANTLY_CHANGED)) {
                if (!ListenerUtil.mutListener.listen(4230)) {
                    Timber.d("LOCATION_SIGNIFICANTLY_CHANGED");
                }
                if (!ListenerUtil.mutListener.listen(4231)) {
                    lockUnlockNearby(true);
                }
                if (!ListenerUtil.mutListener.listen(4232)) {
                    exploreMapFragmentView.setProgressBarVisibility(true);
                }
                if (!ListenerUtil.mutListener.listen(4233)) {
                    exploreMapFragmentView.populatePlaces(exploreMapFragmentView.getMapCenter());
                }
            } else if (locationChangeType.equals(SEARCH_CUSTOM_AREA)) {
                if (!ListenerUtil.mutListener.listen(4226)) {
                    Timber.d("SEARCH_CUSTOM_AREA");
                }
                if (!ListenerUtil.mutListener.listen(4227)) {
                    lockUnlockNearby(true);
                }
                if (!ListenerUtil.mutListener.listen(4228)) {
                    exploreMapFragmentView.setProgressBarVisibility(true);
                }
                if (!ListenerUtil.mutListener.listen(4229)) {
                    exploreMapFragmentView.populatePlaces(exploreMapFragmentView.getMapFocus());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4225)) {
                    // Means location changed slightly, ie user is walking or driving.
                    Timber.d("Means location changed slightly");
                }
            }
        }
    }

    /**
     * Nearby updates takes time, since they are network operations. During update time, we don't
     * want to get any other calls from user. So locking nearby.
     *
     * @param isNearbyLocked true means lock, false means unlock
     */
    @Override
    public void lockUnlockNearby(boolean isNearbyLocked) {
        if (!ListenerUtil.mutListener.listen(4235)) {
            this.isNearbyLocked = isNearbyLocked;
        }
        if (!ListenerUtil.mutListener.listen(4238)) {
            if (isNearbyLocked) {
                if (!ListenerUtil.mutListener.listen(4237)) {
                    exploreMapFragmentView.disableFABRecenter();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4236)) {
                    exploreMapFragmentView.enableFABRecenter();
                }
            }
        }
    }

    @Override
    public void attachView(ExploreMapContract.View view) {
        if (!ListenerUtil.mutListener.listen(4239)) {
            exploreMapFragmentView = view;
        }
    }

    @Override
    public void detachView() {
        if (!ListenerUtil.mutListener.listen(4240)) {
            exploreMapFragmentView = DUMMY;
        }
    }

    /**
     * Sets click listener of FAB
     */
    @Override
    public void setActionListeners(JsonKvStore applicationKvStore) {
        if (!ListenerUtil.mutListener.listen(4241)) {
            exploreMapFragmentView.setFABRecenterAction(v -> {
                exploreMapFragmentView.recenterMap(curLatLng);
            });
        }
    }

    @Override
    public boolean backButtonClicked() {
        return exploreMapFragmentView.backButtonClicked();
    }

    public void onMapReady(ExploreMapController exploreMapController) {
        if (!ListenerUtil.mutListener.listen(4242)) {
            this.exploreMapController = exploreMapController;
        }
        if (!ListenerUtil.mutListener.listen(4243)) {
            exploreMapFragmentView.addSearchThisAreaButtonAction();
        }
        if (!ListenerUtil.mutListener.listen(4246)) {
            if (null != exploreMapFragmentView) {
                if (!ListenerUtil.mutListener.listen(4244)) {
                    exploreMapFragmentView.addSearchThisAreaButtonAction();
                }
                if (!ListenerUtil.mutListener.listen(4245)) {
                    initializeMapOperations();
                }
            }
        }
    }

    public void initializeMapOperations() {
        if (!ListenerUtil.mutListener.listen(4247)) {
            lockUnlockNearby(false);
        }
        if (!ListenerUtil.mutListener.listen(4248)) {
            updateMap(LOCATION_SIGNIFICANTLY_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(4249)) {
            exploreMapFragmentView.addSearchThisAreaButtonAction();
        }
    }

    public Observable<ExplorePlacesInfo> loadAttractionsFromLocation(LatLng curLatLng, LatLng searchLatLng, boolean checkingAroundCurrent) {
        return Observable.fromCallable(() -> exploreMapController.loadAttractionsFromLocation(curLatLng, searchLatLng, checkingAroundCurrent));
    }

    /**
     * Populates places for custom location, should be used for finding nearby places around a
     * location where you are not at.
     *
     * @param explorePlacesInfo This variable has placeToCenter list information and distances.
     */
    public void updateMapMarkers(MapController.ExplorePlacesInfo explorePlacesInfo, Marker selectedMarker) {
        if (!ListenerUtil.mutListener.listen(4253)) {
            if (explorePlacesInfo.mediaList != null) {
                if (!ListenerUtil.mutListener.listen(4252)) {
                    prepareNearbyBaseMarkers(explorePlacesInfo, selectedMarker);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4250)) {
                    // So that new location updates wont come
                    lockUnlockNearby(false);
                }
                if (!ListenerUtil.mutListener.listen(4251)) {
                    exploreMapFragmentView.setProgressBarVisibility(false);
                }
            }
        }
    }

    void prepareNearbyBaseMarkers(MapController.ExplorePlacesInfo explorePlacesInfo, Marker selectedMarker) {
        if (!ListenerUtil.mutListener.listen(4254)) {
            exploreMapController.loadAttractionsFromLocationToBaseMarkerOptions(explorePlacesInfo.curLatLng, // Curlatlang will be used to calculate distances
            explorePlacesInfo.explorePlaceList, exploreMapFragmentView.getContext(), this, selectedMarker, explorePlacesInfo);
        }
    }

    @Override
    public void onNearbyBaseMarkerThumbsReady(List<NearbyBaseMarker> baseMarkers, ExplorePlacesInfo explorePlacesInfo, Marker selectedMarker) {
        if (!ListenerUtil.mutListener.listen(4258)) {
            if (null != exploreMapFragmentView) {
                if (!ListenerUtil.mutListener.listen(4255)) {
                    exploreMapFragmentView.addMarkersToMap(baseMarkers);
                }
                if (!ListenerUtil.mutListener.listen(4256)) {
                    // So that new location updates wont come
                    lockUnlockNearby(false);
                }
                if (!ListenerUtil.mutListener.listen(4257)) {
                    exploreMapFragmentView.setProgressBarVisibility(false);
                }
            }
        }
    }

    public View.OnClickListener onSearchThisAreaClicked() {
        return v -> {
            // Lock map operations during search this area operation
            exploreMapFragmentView.setSearchThisAreaButtonVisibility(false);
            if (searchCloseToCurrentLocation()) {
                updateMap(LOCATION_SIGNIFICANTLY_CHANGED);
            } else {
                updateMap(SEARCH_CUSTOM_AREA);
            }
        };
    }

    /**
     * Returns true if search this area button is used around our current location, so that we can
     * continue following our current location again
     *
     * @return Returns true if search this area button is used around our current location
     */
    public boolean searchCloseToCurrentLocation() {
        if (null == exploreMapFragmentView.getLastMapFocus()) {
            return true;
        }
        Location mylocation = new Location("");
        Location dest_location = new Location("");
        if (!ListenerUtil.mutListener.listen(4259)) {
            dest_location.setLatitude(exploreMapFragmentView.getMapFocus().getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(4260)) {
            dest_location.setLongitude(exploreMapFragmentView.getMapFocus().getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(4261)) {
            mylocation.setLatitude(exploreMapFragmentView.getLastMapFocus().getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(4262)) {
            mylocation.setLongitude(exploreMapFragmentView.getLastMapFocus().getLongitude());
        }
        Float distance = mylocation.distanceTo(dest_location);
        if ((ListenerUtil.mutListener.listen(4275) ? (distance >= (ListenerUtil.mutListener.listen(4270) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(4269) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(4268) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(4267) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(4274) ? (distance <= (ListenerUtil.mutListener.listen(4270) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(4269) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(4268) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(4267) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(4273) ? (distance < (ListenerUtil.mutListener.listen(4270) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(4269) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(4268) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(4267) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(4272) ? (distance != (ListenerUtil.mutListener.listen(4270) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(4269) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(4268) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(4267) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(4271) ? (distance == (ListenerUtil.mutListener.listen(4270) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(4269) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(4268) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(4267) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (distance > (ListenerUtil.mutListener.listen(4270) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(4269) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(4268) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(4267) ? ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(4266) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(4265) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(4264) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(4263) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))))))))) {
            return false;
        } else {
            return true;
        }
    }
}
