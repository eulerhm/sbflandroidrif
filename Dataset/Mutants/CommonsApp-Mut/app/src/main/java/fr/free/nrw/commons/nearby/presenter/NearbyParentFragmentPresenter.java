package fr.free.nrw.commons.nearby.presenter;

import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.CUSTOM_QUERY;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.LOCATION_SIGNIFICANTLY_CHANGED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.LOCATION_SLIGHTLY_CHANGED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.MAP_UPDATED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.SEARCH_CUSTOM_AREA;
import static fr.free.nrw.commons.nearby.CheckBoxTriStates.CHECKED;
import static fr.free.nrw.commons.nearby.CheckBoxTriStates.UNCHECKED;
import static fr.free.nrw.commons.nearby.CheckBoxTriStates.UNKNOWN;
import static fr.free.nrw.commons.wikidata.WikidataConstants.PLACE_OBJECT;
import android.location.Location;
import android.view.View;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.mapbox.mapboxsdk.annotations.Marker;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType;
import fr.free.nrw.commons.location.LocationUpdateListener;
import fr.free.nrw.commons.nearby.CheckBoxTriStates;
import fr.free.nrw.commons.nearby.Label;
import fr.free.nrw.commons.nearby.MarkerPlaceGroup;
import fr.free.nrw.commons.nearby.NearbyBaseMarker;
import fr.free.nrw.commons.nearby.NearbyController;
import fr.free.nrw.commons.nearby.NearbyFilterState;
import fr.free.nrw.commons.nearby.contract.NearbyParentFragmentContract;
import fr.free.nrw.commons.utils.LocationUtils;
import fr.free.nrw.commons.wikidata.WikidataEditListener;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NearbyParentFragmentPresenter implements NearbyParentFragmentContract.UserActions, WikidataEditListener.WikidataP18EditListener, LocationUpdateListener {

    private boolean isNearbyLocked;

    private LatLng curLatLng;

    private boolean placesLoadedOnce;

    BookmarkLocationsDao bookmarkLocationDao;

    @Nullable
    private String customQuery;

    private static final NearbyParentFragmentContract.View DUMMY = (NearbyParentFragmentContract.View) Proxy.newProxyInstance(NearbyParentFragmentContract.View.class.getClassLoader(), new Class[] { NearbyParentFragmentContract.View.class }, (proxy, method, args) -> {
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

    private NearbyParentFragmentContract.View nearbyParentFragmentView = DUMMY;

    public NearbyParentFragmentPresenter(BookmarkLocationsDao bookmarkLocationDao) {
        if (!ListenerUtil.mutListener.listen(3331)) {
            this.bookmarkLocationDao = bookmarkLocationDao;
        }
    }

    @Override
    public void attachView(NearbyParentFragmentContract.View view) {
        if (!ListenerUtil.mutListener.listen(3332)) {
            this.nearbyParentFragmentView = view;
        }
    }

    @Override
    public void detachView() {
        if (!ListenerUtil.mutListener.listen(3333)) {
            this.nearbyParentFragmentView = DUMMY;
        }
    }

    @Override
    public void removeNearbyPreferences(JsonKvStore applicationKvStore) {
        if (!ListenerUtil.mutListener.listen(3334)) {
            Timber.d("Remove place objects");
        }
        if (!ListenerUtil.mutListener.listen(3335)) {
            applicationKvStore.remove(PLACE_OBJECT);
        }
    }

    public void initializeMapOperations() {
        if (!ListenerUtil.mutListener.listen(3336)) {
            lockUnlockNearby(false);
        }
        if (!ListenerUtil.mutListener.listen(3337)) {
            updateMapAndList(LOCATION_SIGNIFICANTLY_CHANGED);
        }
        if (!ListenerUtil.mutListener.listen(3338)) {
            this.nearbyParentFragmentView.addSearchThisAreaButtonAction();
        }
        if (!ListenerUtil.mutListener.listen(3339)) {
            nearbyParentFragmentView.setCheckBoxAction();
        }
    }

    /**
     * Sets click listeners of FABs, and 2 bottom sheets
     */
    @Override
    public void setActionListeners(JsonKvStore applicationKvStore) {
        if (!ListenerUtil.mutListener.listen(3340)) {
            nearbyParentFragmentView.setFABPlusAction(v -> {
                if (applicationKvStore.getBoolean("login_skipped", false)) {
                    // prompt the user to login
                    nearbyParentFragmentView.displayLoginSkippedWarning();
                } else {
                    nearbyParentFragmentView.animateFABs();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3341)) {
            nearbyParentFragmentView.setFABRecenterAction(v -> {
                nearbyParentFragmentView.recenterMap(curLatLng);
            });
        }
    }

    @Override
    public boolean backButtonClicked() {
        if (!ListenerUtil.mutListener.listen(3345)) {
            if (nearbyParentFragmentView.isAdvancedQueryFragmentVisible()) {
                if (!ListenerUtil.mutListener.listen(3344)) {
                    nearbyParentFragmentView.showHideAdvancedQueryFragment(false);
                }
                return true;
            } else if (nearbyParentFragmentView.isListBottomSheetExpanded()) {
                if (!ListenerUtil.mutListener.listen(3343)) {
                    // Back should first hide the bottom sheet if it is expanded
                    nearbyParentFragmentView.listOptionMenuItemClicked();
                }
                return true;
            } else if (nearbyParentFragmentView.isDetailsBottomSheetVisible()) {
                if (!ListenerUtil.mutListener.listen(3342)) {
                    nearbyParentFragmentView.setBottomSheetDetailsSmaller();
                }
                return true;
            }
        }
        return false;
    }

    public void markerUnselected() {
        if (!ListenerUtil.mutListener.listen(3346)) {
            nearbyParentFragmentView.hideBottomSheet();
        }
    }

    public void markerSelected(Marker marker) {
        if (!ListenerUtil.mutListener.listen(3347)) {
            nearbyParentFragmentView.displayBottomSheetWithInfo(marker);
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
        if (!ListenerUtil.mutListener.listen(3348)) {
            this.isNearbyLocked = isNearbyLocked;
        }
        if (!ListenerUtil.mutListener.listen(3351)) {
            if (isNearbyLocked) {
                if (!ListenerUtil.mutListener.listen(3350)) {
                    nearbyParentFragmentView.disableFABRecenter();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3349)) {
                    nearbyParentFragmentView.enableFABRecenter();
                }
            }
        }
    }

    /**
     * This method should be the single point to update Map and List. Triggered by location changes
     *
     * @param locationChangeType defines if location changed significantly or slightly
     */
    @Override
    public void updateMapAndList(LocationChangeType locationChangeType) {
        if (!ListenerUtil.mutListener.listen(3352)) {
            Timber.d("Presenter updates map and list");
        }
        if (!ListenerUtil.mutListener.listen(3354)) {
            if (isNearbyLocked) {
                if (!ListenerUtil.mutListener.listen(3353)) {
                    Timber.d("Nearby is locked, so updateMapAndList returns");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3356)) {
            if (!nearbyParentFragmentView.isNetworkConnectionEstablished()) {
                if (!ListenerUtil.mutListener.listen(3355)) {
                    Timber.d("Network connection is not established");
                }
                return;
            }
        }
        LatLng lastLocation = nearbyParentFragmentView.getLastMapFocus();
        if (!ListenerUtil.mutListener.listen(3359)) {
            if (nearbyParentFragmentView.getMapCenter() != null) {
                if (!ListenerUtil.mutListener.listen(3358)) {
                    curLatLng = nearbyParentFragmentView.getMapCenter();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3357)) {
                    curLatLng = lastLocation;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3361)) {
            if (curLatLng == null) {
                if (!ListenerUtil.mutListener.listen(3360)) {
                    Timber.d("Skipping update of nearby places as location is unavailable");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3379)) {
            /**
             * Significant changed - Markers and current location will be updated together
             * Slightly changed - Only current position marker will be updated
             */
            if (locationChangeType.equals(CUSTOM_QUERY)) {
                if (!ListenerUtil.mutListener.listen(3373)) {
                    Timber.d("ADVANCED_QUERY_SEARCH");
                }
                if (!ListenerUtil.mutListener.listen(3374)) {
                    lockUnlockNearby(true);
                }
                if (!ListenerUtil.mutListener.listen(3375)) {
                    nearbyParentFragmentView.setProgressBarVisibility(true);
                }
                LatLng updatedLocationByUser = LocationUtils.deriveUpdatedLocationFromSearchQuery(customQuery);
                if (!ListenerUtil.mutListener.listen(3377)) {
                    if (updatedLocationByUser == null) {
                        if (!ListenerUtil.mutListener.listen(3376)) {
                            updatedLocationByUser = lastLocation;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3378)) {
                    nearbyParentFragmentView.populatePlaces(updatedLocationByUser, customQuery);
                }
            } else if ((ListenerUtil.mutListener.listen(3362) ? (locationChangeType.equals(LOCATION_SIGNIFICANTLY_CHANGED) && locationChangeType.equals(MAP_UPDATED)) : (locationChangeType.equals(LOCATION_SIGNIFICANTLY_CHANGED) || locationChangeType.equals(MAP_UPDATED)))) {
                if (!ListenerUtil.mutListener.listen(3370)) {
                    lockUnlockNearby(true);
                }
                if (!ListenerUtil.mutListener.listen(3371)) {
                    nearbyParentFragmentView.setProgressBarVisibility(true);
                }
                if (!ListenerUtil.mutListener.listen(3372)) {
                    nearbyParentFragmentView.populatePlaces(nearbyParentFragmentView.getMapCenter());
                }
            } else if (locationChangeType.equals(SEARCH_CUSTOM_AREA)) {
                if (!ListenerUtil.mutListener.listen(3366)) {
                    Timber.d("SEARCH_CUSTOM_AREA");
                }
                if (!ListenerUtil.mutListener.listen(3367)) {
                    lockUnlockNearby(true);
                }
                if (!ListenerUtil.mutListener.listen(3368)) {
                    nearbyParentFragmentView.setProgressBarVisibility(true);
                }
                if (!ListenerUtil.mutListener.listen(3369)) {
                    nearbyParentFragmentView.populatePlaces(nearbyParentFragmentView.getMapFocus());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3363)) {
                    // Means location changed slightly, ie user is walking or driving.
                    Timber.d("Means location changed slightly");
                }
                if (!ListenerUtil.mutListener.listen(3365)) {
                    if (nearbyParentFragmentView.isCurrentLocationMarkerVisible()) {
                        if (!ListenerUtil.mutListener.listen(3364)) {
                            // Means user wants to see their live location
                            nearbyParentFragmentView.recenterMap(curLatLng);
                        }
                    }
                }
            }
        }
    }

    /**
     * Populates places for custom location, should be used for finding nearby places around a
     * location where you are not at.
     *
     * @param nearbyPlacesInfo This variable has placeToCenter list information and distances.
     */
    public void updateMapMarkers(NearbyController.NearbyPlacesInfo nearbyPlacesInfo, Marker selectedMarker, boolean shouldTrackPosition) {
        if (!ListenerUtil.mutListener.listen(3385)) {
            if (null != nearbyParentFragmentView) {
                if (!ListenerUtil.mutListener.listen(3380)) {
                    nearbyParentFragmentView.clearAllMarkers();
                }
                List<NearbyBaseMarker> nearbyBaseMarkers = NearbyController.loadAttractionsFromLocationToBaseMarkerOptions(nearbyPlacesInfo.curLatLng, // Curlatlang will be used to calculate distances
                nearbyPlacesInfo.placeList, nearbyParentFragmentView.getContext(), bookmarkLocationDao.getAllBookmarksLocations());
                if (!ListenerUtil.mutListener.listen(3381)) {
                    nearbyParentFragmentView.updateMapMarkers(nearbyBaseMarkers, selectedMarker);
                }
                if (!ListenerUtil.mutListener.listen(3382)) {
                    // So that new location updates wont come
                    lockUnlockNearby(false);
                }
                if (!ListenerUtil.mutListener.listen(3383)) {
                    nearbyParentFragmentView.setProgressBarVisibility(false);
                }
                if (!ListenerUtil.mutListener.listen(3384)) {
                    nearbyParentFragmentView.updateListFragment(nearbyPlacesInfo.placeList);
                }
            }
        }
    }

    /**
     * Some centering task may need to wait for map to be ready, if they are requested before map is
     * ready. So we will remember it when the map is ready
     */
    private void handleCenteringTaskIfAny() {
        if (!ListenerUtil.mutListener.listen(3388)) {
            if (!placesLoadedOnce) {
                if (!ListenerUtil.mutListener.listen(3386)) {
                    placesLoadedOnce = true;
                }
                if (!ListenerUtil.mutListener.listen(3387)) {
                    nearbyParentFragmentView.centerMapToPlace(null);
                }
            }
        }
    }

    @Override
    public void onWikidataEditSuccessful() {
        if (!ListenerUtil.mutListener.listen(3389)) {
            updateMapAndList(MAP_UPDATED);
        }
    }

    @Override
    public void onLocationChangedSignificantly(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(3390)) {
            Timber.d("Location significantly changed");
        }
        if (!ListenerUtil.mutListener.listen(3391)) {
            updateMapAndList(LOCATION_SIGNIFICANTLY_CHANGED);
        }
    }

    @Override
    public void onLocationChangedSlightly(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(3392)) {
            Timber.d("Location significantly changed");
        }
        if (!ListenerUtil.mutListener.listen(3393)) {
            updateMapAndList(LOCATION_SLIGHTLY_CHANGED);
        }
    }

    @Override
    public void onLocationChangedMedium(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(3394)) {
            Timber.d("Location changed medium");
        }
    }

    @Override
    public void onCameraMove(com.mapbox.mapboxsdk.geometry.LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(3404)) {
            // If our nearby markers are calculated at least once
            if (NearbyController.latestSearchLocation != null) {
                double distance = latLng.distanceTo(LocationUtils.commonsLatLngToMapBoxLatLng(NearbyController.latestSearchLocation));
                if (!ListenerUtil.mutListener.listen(3403)) {
                    if (nearbyParentFragmentView.isNetworkConnectionEstablished()) {
                        if (!ListenerUtil.mutListener.listen(3402)) {
                            if ((ListenerUtil.mutListener.listen(3400) ? (distance >= NearbyController.latestSearchRadius) : (ListenerUtil.mutListener.listen(3399) ? (distance <= NearbyController.latestSearchRadius) : (ListenerUtil.mutListener.listen(3398) ? (distance < NearbyController.latestSearchRadius) : (ListenerUtil.mutListener.listen(3397) ? (distance != NearbyController.latestSearchRadius) : (ListenerUtil.mutListener.listen(3396) ? (distance == NearbyController.latestSearchRadius) : (distance > NearbyController.latestSearchRadius))))))) {
                            } else {
                                if (!ListenerUtil.mutListener.listen(3401)) {
                                    nearbyParentFragmentView.setSearchThisAreaButtonVisibility(false);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3395)) {
                    nearbyParentFragmentView.setSearchThisAreaButtonVisibility(false);
                }
            }
        }
    }

    @Override
    public void filterByMarkerType(List<Label> selectedLabels, int state, boolean filterForPlaceState, boolean filterForAllNoneType) {
        if (!ListenerUtil.mutListener.listen(3411)) {
            if (filterForAllNoneType) {
                if (!ListenerUtil.mutListener.listen(3410)) {
                    // Means we will set labels based on states
                    switch(state) {
                        case UNKNOWN:
                            // Do nothing
                            break;
                        case UNCHECKED:
                            if (!ListenerUtil.mutListener.listen(3406)) {
                                // TODO
                                nearbyParentFragmentView.filterOutAllMarkers();
                            }
                            if (!ListenerUtil.mutListener.listen(3407)) {
                                nearbyParentFragmentView.setRecyclerViewAdapterItemsGreyedOut();
                            }
                            break;
                        case CHECKED:
                            if (!ListenerUtil.mutListener.listen(3408)) {
                                // Despite showing all labels NearbyFilterState still should be applied
                                nearbyParentFragmentView.filterMarkersByLabels(selectedLabels, NearbyFilterState.getInstance().isExistsSelected(), NearbyFilterState.getInstance().isNeedPhotoSelected(), NearbyFilterState.getInstance().isWlmSelected(), filterForPlaceState, false);
                            }
                            if (!ListenerUtil.mutListener.listen(3409)) {
                                nearbyParentFragmentView.setRecyclerViewAdapterAllSelected();
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3405)) {
                    nearbyParentFragmentView.filterMarkersByLabels(selectedLabels, NearbyFilterState.getInstance().isExistsSelected(), NearbyFilterState.getInstance().isNeedPhotoSelected(), NearbyFilterState.getInstance().isWlmSelected(), filterForPlaceState, false);
                }
            }
        }
    }

    @Override
    @MainThread
    public void updateMapMarkersToController(List<NearbyBaseMarker> nearbyBaseMarkers) {
        if (!ListenerUtil.mutListener.listen(3412)) {
            NearbyController.markerExistsMap = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(3413)) {
            NearbyController.markerNeedPicMap = new HashMap<>();
        }
        if (!ListenerUtil.mutListener.listen(3414)) {
            NearbyController.markerLabelList.clear();
        }
        if (!ListenerUtil.mutListener.listen(3423)) {
            {
                long _loopCounter49 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3422) ? (i >= nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3421) ? (i <= nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3420) ? (i > nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3419) ? (i != nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3418) ? (i == nearbyBaseMarkers.size()) : (i < nearbyBaseMarkers.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                    NearbyBaseMarker nearbyBaseMarker = nearbyBaseMarkers.get(i);
                    if (!ListenerUtil.mutListener.listen(3415)) {
                        NearbyController.markerLabelList.add(new MarkerPlaceGroup(nearbyBaseMarker.getMarker(), bookmarkLocationDao.findBookmarkLocation(nearbyBaseMarker.getPlace()), nearbyBaseMarker.getPlace()));
                    }
                    if (!ListenerUtil.mutListener.listen(3416)) {
                        // TODO: fix bookmark location
                        NearbyController.markerExistsMap.put((nearbyBaseMarkers.get(i).getPlace().hasWikidataLink()), nearbyBaseMarkers.get(i).getMarker());
                    }
                    if (!ListenerUtil.mutListener.listen(3417)) {
                        NearbyController.markerNeedPicMap.put(((nearbyBaseMarkers.get(i).getPlace().pic == null) ? true : false), nearbyBaseMarkers.get(i).getMarker());
                    }
                }
            }
        }
    }

    @Override
    public void setCheckboxUnknown() {
        if (!ListenerUtil.mutListener.listen(3424)) {
            nearbyParentFragmentView.setCheckBoxState(CheckBoxTriStates.UNKNOWN);
        }
    }

    @Override
    public void setAdvancedQuery(String query) {
        if (!ListenerUtil.mutListener.listen(3425)) {
            this.customQuery = query;
        }
    }

    @Override
    public void searchViewGainedFocus() {
        if (!ListenerUtil.mutListener.listen(3428)) {
            if (nearbyParentFragmentView.isListBottomSheetExpanded()) {
                if (!ListenerUtil.mutListener.listen(3427)) {
                    // Back should first hide the bottom sheet if it is expanded
                    nearbyParentFragmentView.hideBottomSheet();
                }
            } else if (nearbyParentFragmentView.isDetailsBottomSheetVisible()) {
                if (!ListenerUtil.mutListener.listen(3426)) {
                    nearbyParentFragmentView.hideBottomDetailsSheet();
                }
            }
        }
    }

    public View.OnClickListener onSearchThisAreaClicked() {
        return v -> {
            // nearbyParentFragmentView.setMapCenter();
            nearbyParentFragmentView.setSearchThisAreaButtonVisibility(false);
            if (searchCloseToCurrentLocation()) {
                updateMapAndList(LOCATION_SIGNIFICANTLY_CHANGED);
            } else {
                updateMapAndList(SEARCH_CUSTOM_AREA);
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
        if (null == nearbyParentFragmentView.getLastMapFocus()) {
            return true;
        }
        // TODO
        Location mylocation = new Location("");
        Location dest_location = new Location("");
        if (!ListenerUtil.mutListener.listen(3429)) {
            dest_location.setLatitude(nearbyParentFragmentView.getMapFocus().getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(3430)) {
            dest_location.setLongitude(nearbyParentFragmentView.getMapFocus().getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(3431)) {
            mylocation.setLatitude(nearbyParentFragmentView.getLastMapFocus().getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(3432)) {
            mylocation.setLongitude(nearbyParentFragmentView.getLastMapFocus().getLongitude());
        }
        Float distance = mylocation.distanceTo(dest_location);
        if ((ListenerUtil.mutListener.listen(3445) ? (distance >= (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(3437) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3444) ? (distance <= (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(3437) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3443) ? (distance < (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(3437) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3442) ? (distance != (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(3437) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3441) ? (distance == (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(3437) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))) : (distance > (ListenerUtil.mutListener.listen(3440) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) % 4) : (ListenerUtil.mutListener.listen(3439) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) * 4) : (ListenerUtil.mutListener.listen(3438) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) - 4) : (ListenerUtil.mutListener.listen(3437) ? ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3436) ? (2000.0 % 3) : (ListenerUtil.mutListener.listen(3435) ? (2000.0 / 3) : (ListenerUtil.mutListener.listen(3434) ? (2000.0 - 3) : (ListenerUtil.mutListener.listen(3433) ? (2000.0 + 3) : (2000.0 * 3))))) / 4)))))))))))) {
            return false;
        } else {
            return true;
        }
    }

    public void onMapReady() {
        if (!ListenerUtil.mutListener.listen(3448)) {
            if (null != nearbyParentFragmentView) {
                if (!ListenerUtil.mutListener.listen(3446)) {
                    nearbyParentFragmentView.addSearchThisAreaButtonAction();
                }
                if (!ListenerUtil.mutListener.listen(3447)) {
                    initializeMapOperations();
                }
            }
        }
    }

    public boolean areLocationsClose(LatLng cameraTarget, LatLng lastKnownLocation) {
        double distance = LocationUtils.commonsLatLngToMapBoxLatLng(cameraTarget).distanceTo(LocationUtils.commonsLatLngToMapBoxLatLng(lastKnownLocation));
        if ((ListenerUtil.mutListener.listen(3461) ? (distance >= (ListenerUtil.mutListener.listen(3456) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) % 4) : (ListenerUtil.mutListener.listen(3455) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) * 4) : (ListenerUtil.mutListener.listen(3454) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) - 4) : (ListenerUtil.mutListener.listen(3453) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3460) ? (distance <= (ListenerUtil.mutListener.listen(3456) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) % 4) : (ListenerUtil.mutListener.listen(3455) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) * 4) : (ListenerUtil.mutListener.listen(3454) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) - 4) : (ListenerUtil.mutListener.listen(3453) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3459) ? (distance < (ListenerUtil.mutListener.listen(3456) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) % 4) : (ListenerUtil.mutListener.listen(3455) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) * 4) : (ListenerUtil.mutListener.listen(3454) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) - 4) : (ListenerUtil.mutListener.listen(3453) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3458) ? (distance != (ListenerUtil.mutListener.listen(3456) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) % 4) : (ListenerUtil.mutListener.listen(3455) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) * 4) : (ListenerUtil.mutListener.listen(3454) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) - 4) : (ListenerUtil.mutListener.listen(3453) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) / 4)))))) : (ListenerUtil.mutListener.listen(3457) ? (distance == (ListenerUtil.mutListener.listen(3456) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) % 4) : (ListenerUtil.mutListener.listen(3455) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) * 4) : (ListenerUtil.mutListener.listen(3454) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) - 4) : (ListenerUtil.mutListener.listen(3453) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) / 4)))))) : (distance > (ListenerUtil.mutListener.listen(3456) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) % 4) : (ListenerUtil.mutListener.listen(3455) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) * 4) : (ListenerUtil.mutListener.listen(3454) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) - 4) : (ListenerUtil.mutListener.listen(3453) ? ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) + 4) : ((ListenerUtil.mutListener.listen(3452) ? (NearbyController.currentLocationSearchRadius % 3) : (ListenerUtil.mutListener.listen(3451) ? (NearbyController.currentLocationSearchRadius / 3) : (ListenerUtil.mutListener.listen(3450) ? (NearbyController.currentLocationSearchRadius - 3) : (ListenerUtil.mutListener.listen(3449) ? (NearbyController.currentLocationSearchRadius + 3) : (NearbyController.currentLocationSearchRadius * 3))))) / 4)))))))))))) {
            return false;
        } else {
            return true;
        }
    }
}
