package fr.free.nrw.commons.explore.map;

import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.LOCATION_SIGNIFICANTLY_CHANGED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.LOCATION_SLIGHTLY_CHANGED;
import static fr.free.nrw.commons.utils.MapUtils.ZOOM_LEVEL;
import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.mapboxsdk.annotations.Marker;
import fr.free.nrw.commons.MapController;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.explore.ExploreMapRootFragment;
import fr.free.nrw.commons.explore.paging.LiveDataConverter;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LatLng;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.location.LocationUpdateListener;
import fr.free.nrw.commons.media.MediaClient;
import fr.free.nrw.commons.nearby.NearbyBaseMarker;
import fr.free.nrw.commons.nearby.NearbyMarker;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.MapUtils;
import fr.free.nrw.commons.utils.NetworkUtils;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.SystemThemeUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.constants.GeoConstants;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.ScaleDiskOverlay;
import org.osmdroid.views.overlay.TilesOverlay;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ExploreMapFragment extends CommonsDaggerSupportFragment implements ExploreMapContract.View, LocationUpdateListener {

    private BottomSheetBehavior bottomSheetDetailsBehavior;

    private BroadcastReceiver broadcastReceiver;

    private boolean isNetworkErrorOccurred;

    private Snackbar snackbar;

    private boolean isDarkTheme;

    private boolean isPermissionDenied;

    // last location of user
    private fr.free.nrw.commons.location.LatLng lastKnownLocation;

    // last location that map is focused
    private fr.free.nrw.commons.location.LatLng lastFocusLocation;

    public List<Media> mediaList;

    // true is recenter is needed (ie. when current location is in visible map boundaries)
    private boolean recenterToUserLocation;

    private NearbyBaseMarker clickedMarker;

    // the marker that user selected
    private Marker selectedMarker;

    private GeoPoint mapCenter;

    private GeoPoint lastMapFocus;

    IntentFilter intentFilter = new IntentFilter(MapUtils.NETWORK_INTENT_ACTION);

    @Inject
    LiveDataConverter liveDataConverter;

    @Inject
    MediaClient mediaClient;

    @Inject
    LocationServiceManager locationManager;

    @Inject
    ExploreMapController exploreMapController;

    @Inject
    @Named("default_preferences")
    JsonKvStore applicationKvStore;

    @Inject
    BookmarkLocationsDao // May be needed in future if we want to integrate bookmarking explore places
    bookmarkLocationDao;

    @Inject
    SystemThemeUtils systemThemeUtils;

    private ExploreMapPresenter presenter;

    @BindView(R.id.map_view)
    org.osmdroid.views.MapView mapView;

    @BindView(R.id.bottom_sheet_details)
    View bottomSheetDetails;

    @BindView(R.id.map_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.fab_recenter)
    FloatingActionButton fabRecenter;

    @BindView(R.id.search_this_area_button)
    Button searchThisAreaButton;

    @BindView(R.id.tv_attribution)
    AppCompatTextView tvAttribution;

    @BindView(R.id.directionsButton)
    LinearLayout directionsButton;

    @BindView(R.id.commonsButton)
    LinearLayout commonsButton;

    @BindView(R.id.mediaDetailsButton)
    LinearLayout mediaDetailsButton;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.category)
    TextView distance;

    private ActivityResultLauncher<String[]> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean areAllGranted = true;
            if (!ListenerUtil.mutListener.listen(3919)) {
                {
                    long _loopCounter57 = 0;
                    for (final boolean b : result.values()) {
                        ListenerUtil.loopListener.listen("_loopCounter57", ++_loopCounter57);
                        if (!ListenerUtil.mutListener.listen(3918)) {
                            areAllGranted = (ListenerUtil.mutListener.listen(3917) ? (areAllGranted || b) : (areAllGranted && b));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3924)) {
                if (areAllGranted) {
                    if (!ListenerUtil.mutListener.listen(3923)) {
                        locationPermissionGranted();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3922)) {
                        if (shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION)) {
                            if (!ListenerUtil.mutListener.listen(3921)) {
                                DialogUtil.showAlertDialog(getActivity(), getActivity().getString(R.string.location_permission_title), getActivity().getString(R.string.location_permission_rationale_nearby), getActivity().getString(android.R.string.ok), getActivity().getString(android.R.string.cancel), () -> {
                                    if (!(locationManager.isNetworkProviderEnabled() || locationManager.isGPSProviderEnabled())) {
                                        showLocationOffDialog();
                                    }
                                }, () -> isPermissionDenied = true, null, false);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3920)) {
                                isPermissionDenied = true;
                            }
                        }
                    }
                }
            }
        }
    });

    @NonNull
    public static ExploreMapFragment newInstance() {
        ExploreMapFragment fragment = new ExploreMapFragment();
        if (!ListenerUtil.mutListener.listen(3925)) {
            fragment.setRetainInstance(true);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3926)) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_explore_map, container, false);
        if (!ListenerUtil.mutListener.listen(3927)) {
            ButterKnife.bind(this, v);
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3928)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3929)) {
            setSearchThisAreaButtonVisibility(false);
        }
        if (!ListenerUtil.mutListener.listen(3930)) {
            tvAttribution.setText(Html.fromHtml(getString(R.string.map_attribution)));
        }
        if (!ListenerUtil.mutListener.listen(3931)) {
            initNetworkBroadCastReceiver();
        }
        if (!ListenerUtil.mutListener.listen(3933)) {
            if (presenter == null) {
                if (!ListenerUtil.mutListener.listen(3932)) {
                    presenter = new ExploreMapPresenter(bookmarkLocationDao);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3934)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(3935)) {
            isDarkTheme = systemThemeUtils.isDeviceInNightMode();
        }
        if (!ListenerUtil.mutListener.listen(3936)) {
            isPermissionDenied = false;
        }
        if (!ListenerUtil.mutListener.listen(3937)) {
            presenter.attachView(this);
        }
        if (!ListenerUtil.mutListener.listen(3938)) {
            initViews();
        }
        if (!ListenerUtil.mutListener.listen(3939)) {
            presenter.setActionListeners(applicationKvStore);
        }
        if (!ListenerUtil.mutListener.listen(3940)) {
            org.osmdroid.config.Configuration.getInstance().load(this.getContext(), PreferenceManager.getDefaultSharedPreferences(this.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(3941)) {
            mapView.setTileSource(TileSourceFactory.WIKIMEDIA);
        }
        if (!ListenerUtil.mutListener.listen(3942)) {
            mapView.setTilesScaledToDpi(true);
        }
        if (!ListenerUtil.mutListener.listen(3943)) {
            org.osmdroid.config.Configuration.getInstance().getAdditionalHttpRequestProperties().put("Referer", "http://maps.wikimedia.org/");
        }
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        if (!ListenerUtil.mutListener.listen(3944)) {
            scaleBarOverlay.setScaleBarOffset(15, 25);
        }
        Paint barPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(3945)) {
            barPaint.setARGB(200, 255, 250, 250);
        }
        if (!ListenerUtil.mutListener.listen(3946)) {
            scaleBarOverlay.setBackgroundPaint(barPaint);
        }
        if (!ListenerUtil.mutListener.listen(3947)) {
            scaleBarOverlay.enableScaleBar();
        }
        if (!ListenerUtil.mutListener.listen(3948)) {
            mapView.getOverlays().add(scaleBarOverlay);
        }
        if (!ListenerUtil.mutListener.listen(3949)) {
            mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        }
        if (!ListenerUtil.mutListener.listen(3950)) {
            mapView.setMultiTouchControls(true);
        }
        if (!ListenerUtil.mutListener.listen(3951)) {
            mapView.getController().setZoom(ZOOM_LEVEL);
        }
        if (!ListenerUtil.mutListener.listen(3952)) {
            performMapReadyActions();
        }
        if (!ListenerUtil.mutListener.listen(3961)) {
            mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {

                @Override
                public boolean singleTapConfirmedHelper(GeoPoint p) {
                    if (!ListenerUtil.mutListener.listen(3957)) {
                        if (clickedMarker != null) {
                            if (!ListenerUtil.mutListener.listen(3954)) {
                                removeMarker(clickedMarker);
                            }
                            if (!ListenerUtil.mutListener.listen(3955)) {
                                addMarkerToMap(clickedMarker);
                            }
                            if (!ListenerUtil.mutListener.listen(3956)) {
                                mapView.invalidate();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3953)) {
                                Timber.e("CLICKED MARKER IS NULL");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3960)) {
                        if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            if (!ListenerUtil.mutListener.listen(3959)) {
                                // Back should first hide the bottom sheet if it is expanded
                                bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            }
                        } else if (isDetailsBottomSheetVisible()) {
                            if (!ListenerUtil.mutListener.listen(3958)) {
                                hideBottomDetailsSheet();
                            }
                        }
                    }
                    return true;
                }

                @Override
                public boolean longPressHelper(GeoPoint p) {
                    return false;
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(3990)) {
            mapView.addMapListener(new MapListener() {

                @Override
                public boolean onScroll(ScrollEvent event) {
                    if (!ListenerUtil.mutListener.listen(3989)) {
                        if (getLastMapFocus() != null) {
                            Location mylocation = new Location("");
                            Location dest_location = new Location("");
                            if (!ListenerUtil.mutListener.listen(3962)) {
                                dest_location.setLatitude(mapView.getMapCenter().getLatitude());
                            }
                            if (!ListenerUtil.mutListener.listen(3963)) {
                                dest_location.setLongitude(mapView.getMapCenter().getLongitude());
                            }
                            if (!ListenerUtil.mutListener.listen(3964)) {
                                mylocation.setLatitude(getLastMapFocus().getLatitude());
                            }
                            if (!ListenerUtil.mutListener.listen(3965)) {
                                mylocation.setLongitude(getLastMapFocus().getLongitude());
                            }
                            // in meters
                            Float distance = mylocation.distanceTo(dest_location);
                            if (!ListenerUtil.mutListener.listen(3988)) {
                                if (getLastMapFocus() != null) {
                                    if (!ListenerUtil.mutListener.listen(3987)) {
                                        if ((ListenerUtil.mutListener.listen(3978) ? (isNetworkConnectionEstablished() || ((ListenerUtil.mutListener.listen(3977) ? ((ListenerUtil.mutListener.listen(3971) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(3970) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(3969) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(3968) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(3967) ? (event.getX() == 0) : (event.getX() > 0)))))) && (ListenerUtil.mutListener.listen(3976) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(3975) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(3974) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(3973) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(3972) ? (event.getY() == 0) : (event.getY() > 0))))))) : ((ListenerUtil.mutListener.listen(3971) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(3970) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(3969) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(3968) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(3967) ? (event.getX() == 0) : (event.getX() > 0)))))) || (ListenerUtil.mutListener.listen(3976) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(3975) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(3974) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(3973) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(3972) ? (event.getY() == 0) : (event.getY() > 0)))))))))) : (isNetworkConnectionEstablished() && ((ListenerUtil.mutListener.listen(3977) ? ((ListenerUtil.mutListener.listen(3971) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(3970) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(3969) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(3968) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(3967) ? (event.getX() == 0) : (event.getX() > 0)))))) && (ListenerUtil.mutListener.listen(3976) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(3975) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(3974) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(3973) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(3972) ? (event.getY() == 0) : (event.getY() > 0))))))) : ((ListenerUtil.mutListener.listen(3971) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(3970) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(3969) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(3968) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(3967) ? (event.getX() == 0) : (event.getX() > 0)))))) || (ListenerUtil.mutListener.listen(3976) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(3975) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(3974) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(3973) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(3972) ? (event.getY() == 0) : (event.getY() > 0)))))))))))) {
                                            if (!ListenerUtil.mutListener.listen(3986)) {
                                                if ((ListenerUtil.mutListener.listen(3983) ? (distance >= 2000.0) : (ListenerUtil.mutListener.listen(3982) ? (distance <= 2000.0) : (ListenerUtil.mutListener.listen(3981) ? (distance < 2000.0) : (ListenerUtil.mutListener.listen(3980) ? (distance != 2000.0) : (ListenerUtil.mutListener.listen(3979) ? (distance == 2000.0) : (distance > 2000.0))))))) {
                                                    if (!ListenerUtil.mutListener.listen(3985)) {
                                                        setSearchThisAreaButtonVisibility(true);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(3984)) {
                                                        setSearchThisAreaButtonVisibility(false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(3966)) {
                                        setSearchThisAreaButtonVisibility(false);
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }

                @Override
                public boolean onZoom(ZoomEvent event) {
                    return false;
                }
            });
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(3991)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(3992)) {
            mapView.onResume();
        }
        if (!ListenerUtil.mutListener.listen(3993)) {
            presenter.attachView(this);
        }
        if (!ListenerUtil.mutListener.listen(3994)) {
            registerNetworkReceiver();
        }
        if (!ListenerUtil.mutListener.listen(3999)) {
            if (isResumed()) {
                if (!ListenerUtil.mutListener.listen(3998)) {
                    if ((ListenerUtil.mutListener.listen(3995) ? (!isPermissionDenied || !applicationKvStore.getBoolean("doNotAskForLocationPermission", false)) : (!isPermissionDenied && !applicationKvStore.getBoolean("doNotAskForLocationPermission", false)))) {
                        if (!ListenerUtil.mutListener.listen(3997)) {
                            performMapReadyActions();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3996)) {
                            startMapWithoutPermission();
                        }
                    }
                }
            }
        }
    }

    private void startMapWithoutPermission() {
        if (!ListenerUtil.mutListener.listen(4000)) {
            applicationKvStore.putBoolean("doNotAskForLocationPermission", true);
        }
        if (!ListenerUtil.mutListener.listen(4001)) {
            lastKnownLocation = MapUtils.defaultLatLng;
        }
        if (!ListenerUtil.mutListener.listen(4002)) {
            moveCameraToPosition(new GeoPoint(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()));
        }
        if (!ListenerUtil.mutListener.listen(4003)) {
            presenter.onMapReady(exploreMapController);
        }
    }

    private void registerNetworkReceiver() {
        if (!ListenerUtil.mutListener.listen(4005)) {
            if (getActivity() != null) {
                if (!ListenerUtil.mutListener.listen(4004)) {
                    getActivity().registerReceiver(broadcastReceiver, intentFilter);
                }
            }
        }
    }

    private void performMapReadyActions() {
        if (!ListenerUtil.mutListener.listen(4007)) {
            if (isDarkTheme) {
                if (!ListenerUtil.mutListener.listen(4006)) {
                    mapView.getOverlayManager().getTilesOverlay().setColorFilter(TilesOverlay.INVERT_COLORS);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4011)) {
            if ((ListenerUtil.mutListener.listen(4008) ? (!applicationKvStore.getBoolean("doNotAskForLocationPermission", false) && PermissionUtils.hasPermission(getActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION })) : (!applicationKvStore.getBoolean("doNotAskForLocationPermission", false) || PermissionUtils.hasPermission(getActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION })))) {
                if (!ListenerUtil.mutListener.listen(4010)) {
                    checkPermissionsAndPerformAction();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4009)) {
                    isPermissionDenied = true;
                }
            }
        }
    }

    private void initViews() {
        if (!ListenerUtil.mutListener.listen(4012)) {
            Timber.d("init views called");
        }
        if (!ListenerUtil.mutListener.listen(4013)) {
            initBottomSheets();
        }
        if (!ListenerUtil.mutListener.listen(4014)) {
            setBottomSheetCallbacks();
        }
    }

    /**
     * a) Creates bottom sheet behaviours from bottom sheet, sets initial states and visibility
     * b) Gets the touch event on the map to perform following actions:
     *      if bottom sheet details are expanded or collapsed hide the bottom sheet details.
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initBottomSheets() {
        if (!ListenerUtil.mutListener.listen(4015)) {
            bottomSheetDetailsBehavior = BottomSheetBehavior.from(bottomSheetDetails);
        }
        if (!ListenerUtil.mutListener.listen(4016)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(4017)) {
            bottomSheetDetails.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Defines how bottom sheets will act on click
     */
    private void setBottomSheetCallbacks() {
        if (!ListenerUtil.mutListener.listen(4018)) {
            bottomSheetDetails.setOnClickListener(v -> {
                if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });
        }
    }

    @Override
    public void onLocationChangedSignificantly(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(4019)) {
            Timber.d("Location significantly changed");
        }
        if (!ListenerUtil.mutListener.listen(4021)) {
            if (latLng != null) {
                if (!ListenerUtil.mutListener.listen(4020)) {
                    handleLocationUpdate(latLng, LOCATION_SIGNIFICANTLY_CHANGED);
                }
            }
        }
    }

    @Override
    public void onLocationChangedSlightly(LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(4022)) {
            Timber.d("Location slightly changed");
        }
        if (!ListenerUtil.mutListener.listen(4024)) {
            if (latLng != null) {
                if (!ListenerUtil.mutListener.listen(4023)) {
                    // If the map has never ever shown the current location, lets do it know
                    handleLocationUpdate(latLng, LOCATION_SLIGHTLY_CHANGED);
                }
            }
        }
    }

    private void handleLocationUpdate(final fr.free.nrw.commons.location.LatLng latLng, final LocationServiceManager.LocationChangeType locationChangeType) {
        if (!ListenerUtil.mutListener.listen(4025)) {
            lastKnownLocation = latLng;
        }
        if (!ListenerUtil.mutListener.listen(4026)) {
            exploreMapController.currentLocation = lastKnownLocation;
        }
        if (!ListenerUtil.mutListener.listen(4027)) {
            presenter.updateMap(locationChangeType);
        }
    }

    @Override
    public void onLocationChangedMedium(LatLng latLng) {
    }

    @Override
    public boolean isNetworkConnectionEstablished() {
        return NetworkUtils.isInternetConnectionEstablished(getActivity());
    }

    @Override
    public void populatePlaces(LatLng curLatLng) {
        final Observable<MapController.ExplorePlacesInfo> nearbyPlacesInfoObservable;
        if (!ListenerUtil.mutListener.listen(4029)) {
            if (curLatLng == null) {
                if (!ListenerUtil.mutListener.listen(4028)) {
                    checkPermissionsAndPerformAction();
                }
                return;
            }
        }
        if (curLatLng.equals(getLastMapFocus())) {
            // Means we are checking around current location
            nearbyPlacesInfoObservable = presenter.loadAttractionsFromLocation(curLatLng, getLastMapFocus(), true);
        } else {
            nearbyPlacesInfoObservable = presenter.loadAttractionsFromLocation(getLastMapFocus(), curLatLng, false);
        }
        if (!ListenerUtil.mutListener.listen(4030)) {
            compositeDisposable.add(nearbyPlacesInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(explorePlacesInfo -> {
                mediaList = explorePlacesInfo.mediaList;
                updateMapMarkers(explorePlacesInfo);
                lastMapFocus = new GeoPoint(curLatLng.getLatitude(), curLatLng.getLongitude());
            }, throwable -> {
                Timber.d(throwable);
                showErrorMessage(getString(R.string.error_fetching_nearby_places) + throwable.getLocalizedMessage());
                setProgressBarVisibility(false);
                presenter.lockUnlockNearby(false);
            }));
        }
        if (!ListenerUtil.mutListener.listen(4032)) {
            if (recenterToUserLocation) {
                if (!ListenerUtil.mutListener.listen(4031)) {
                    recenterToUserLocation = false;
                }
            }
        }
    }

    /**
     * Updates map markers according to latest situation
     *
     * @param explorePlacesInfo holds several information as current location, marker list etc.
     */
    private void updateMapMarkers(final MapController.ExplorePlacesInfo explorePlacesInfo) {
        if (!ListenerUtil.mutListener.listen(4033)) {
            presenter.updateMapMarkers(explorePlacesInfo, selectedMarker);
        }
    }

    private void showErrorMessage(final String message) {
        if (!ListenerUtil.mutListener.listen(4034)) {
            ViewUtil.showLongToast(getActivity(), message);
        }
    }

    @Override
    public void checkPermissionsAndPerformAction() {
        if (!ListenerUtil.mutListener.listen(4035)) {
            Timber.d("Checking permission and perfoming action");
        }
        if (!ListenerUtil.mutListener.listen(4036)) {
            activityResultLauncher.launch(new String[] { permission.ACCESS_FINE_LOCATION });
        }
    }

    private void locationPermissionGranted() {
        if (!ListenerUtil.mutListener.listen(4037)) {
            isPermissionDenied = false;
        }
        if (!ListenerUtil.mutListener.listen(4038)) {
            applicationKvStore.putBoolean("doNotAskForLocationPermission", false);
        }
        if (!ListenerUtil.mutListener.listen(4039)) {
            lastKnownLocation = locationManager.getLastLocation();
        }
        fr.free.nrw.commons.location.LatLng target = lastKnownLocation;
        if (!ListenerUtil.mutListener.listen(4049)) {
            if (lastKnownLocation != null) {
                GeoPoint targetP = new GeoPoint(target.getLatitude(), target.getLongitude());
                if (!ListenerUtil.mutListener.listen(4045)) {
                    mapCenter = targetP;
                }
                if (!ListenerUtil.mutListener.listen(4046)) {
                    mapView.getController().setCenter(targetP);
                }
                if (!ListenerUtil.mutListener.listen(4047)) {
                    recenterMarkerToPosition(targetP);
                }
                if (!ListenerUtil.mutListener.listen(4048)) {
                    moveCameraToPosition(targetP);
                }
            } else if ((ListenerUtil.mutListener.listen(4040) ? (locationManager.isGPSProviderEnabled() && locationManager.isNetworkProviderEnabled()) : (locationManager.isGPSProviderEnabled() || locationManager.isNetworkProviderEnabled()))) {
                if (!ListenerUtil.mutListener.listen(4042)) {
                    locationManager.requestLocationUpdatesFromProvider(LocationManager.NETWORK_PROVIDER);
                }
                if (!ListenerUtil.mutListener.listen(4043)) {
                    locationManager.requestLocationUpdatesFromProvider(LocationManager.GPS_PROVIDER);
                }
                if (!ListenerUtil.mutListener.listen(4044)) {
                    setProgressBarVisibility(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4041)) {
                    Toast.makeText(getContext(), getString(R.string.nearby_location_not_available), Toast.LENGTH_LONG).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4050)) {
            presenter.onMapReady(exploreMapController);
        }
        if (!ListenerUtil.mutListener.listen(4051)) {
            registerUnregisterLocationListener(false);
        }
    }

    public void registerUnregisterLocationListener(final boolean removeLocationListener) {
        if (!ListenerUtil.mutListener.listen(4052)) {
            MapUtils.registerUnregisterLocationListener(removeLocationListener, locationManager, this);
        }
    }

    @Override
    public void recenterMap(LatLng curLatLng) {
        if (!ListenerUtil.mutListener.listen(4060)) {
            if ((ListenerUtil.mutListener.listen(4053) ? (isPermissionDenied && curLatLng == null) : (isPermissionDenied || curLatLng == null))) {
                if (!ListenerUtil.mutListener.listen(4054)) {
                    recenterToUserLocation = true;
                }
                if (!ListenerUtil.mutListener.listen(4055)) {
                    checkPermissionsAndPerformAction();
                }
                if (!ListenerUtil.mutListener.listen(4059)) {
                    if ((ListenerUtil.mutListener.listen(4057) ? (!isPermissionDenied || !((ListenerUtil.mutListener.listen(4056) ? (locationManager.isNetworkProviderEnabled() && locationManager.isGPSProviderEnabled()) : (locationManager.isNetworkProviderEnabled() || locationManager.isGPSProviderEnabled())))) : (!isPermissionDenied && !((ListenerUtil.mutListener.listen(4056) ? (locationManager.isNetworkProviderEnabled() && locationManager.isGPSProviderEnabled()) : (locationManager.isNetworkProviderEnabled() || locationManager.isGPSProviderEnabled())))))) {
                        if (!ListenerUtil.mutListener.listen(4058)) {
                            showLocationOffDialog();
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4061)) {
            recenterMarkerToPosition(new GeoPoint(curLatLng.getLatitude(), curLatLng.getLongitude()));
        }
        if (!ListenerUtil.mutListener.listen(4062)) {
            mapView.getController().animateTo(new GeoPoint(curLatLng.getLatitude(), curLatLng.getLongitude()));
        }
        if (!ListenerUtil.mutListener.listen(4078)) {
            if (lastMapFocus != null) {
                Location mylocation = new Location("");
                Location dest_location = new Location("");
                if (!ListenerUtil.mutListener.listen(4063)) {
                    dest_location.setLatitude(mapView.getMapCenter().getLatitude());
                }
                if (!ListenerUtil.mutListener.listen(4064)) {
                    dest_location.setLongitude(mapView.getMapCenter().getLongitude());
                }
                if (!ListenerUtil.mutListener.listen(4065)) {
                    mylocation.setLatitude(lastMapFocus.getLatitude());
                }
                if (!ListenerUtil.mutListener.listen(4066)) {
                    mylocation.setLongitude(lastMapFocus.getLongitude());
                }
                // in meters
                Float distance = mylocation.distanceTo(dest_location);
                if (!ListenerUtil.mutListener.listen(4077)) {
                    if (lastMapFocus != null) {
                        if (!ListenerUtil.mutListener.listen(4076)) {
                            if (isNetworkConnectionEstablished()) {
                                if (!ListenerUtil.mutListener.listen(4075)) {
                                    if ((ListenerUtil.mutListener.listen(4072) ? (distance >= 2000.0) : (ListenerUtil.mutListener.listen(4071) ? (distance <= 2000.0) : (ListenerUtil.mutListener.listen(4070) ? (distance < 2000.0) : (ListenerUtil.mutListener.listen(4069) ? (distance != 2000.0) : (ListenerUtil.mutListener.listen(4068) ? (distance == 2000.0) : (distance > 2000.0))))))) {
                                        if (!ListenerUtil.mutListener.listen(4074)) {
                                            setSearchThisAreaButtonVisibility(true);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(4073)) {
                                            setSearchThisAreaButtonVisibility(false);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4067)) {
                            setSearchThisAreaButtonVisibility(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void showLocationOffDialog() {
        if (!ListenerUtil.mutListener.listen(4079)) {
            // This creates a dialog box that prompts the user to enable location
            DialogUtil.showAlertDialog(getActivity(), getString(R.string.ask_to_turn_location_on), getString(R.string.nearby_needs_location), getString(R.string.yes), getString(R.string.no), this::openLocationSettings, null);
        }
    }

    @Override
    public void openLocationSettings() {
        // This method opens the location settings of the device along with a followup toast.
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        final PackageManager packageManager = getActivity().getPackageManager();
        if (!ListenerUtil.mutListener.listen(4083)) {
            if (intent.resolveActivity(packageManager) != null) {
                if (!ListenerUtil.mutListener.listen(4081)) {
                    startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(4082)) {
                    Toast.makeText(getContext(), R.string.recommend_high_accuracy_mode, Toast.LENGTH_LONG).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4080)) {
                    Toast.makeText(getContext(), R.string.cannot_open_location_settings, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void hideBottomDetailsSheet() {
        if (!ListenerUtil.mutListener.listen(4084)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void displayBottomSheetWithInfo(final Marker marker) {
        if (!ListenerUtil.mutListener.listen(4085)) {
            selectedMarker = marker;
        }
        final NearbyMarker nearbyMarker = (NearbyMarker) marker;
        final Place place = nearbyMarker.getNearbyBaseMarker().getPlace();
        if (!ListenerUtil.mutListener.listen(4086)) {
            passInfoToSheet(place);
        }
        if (!ListenerUtil.mutListener.listen(4087)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    /**
     * Same bottom sheet carries information for all nearby places, so we need to pass information
     * (title, description, distance and links) to view on nearby marker click
     *
     * @param place Place of clicked nearby marker
     */
    private void passInfoToSheet(final Place place) {
        if (!ListenerUtil.mutListener.listen(4088)) {
            directionsButton.setOnClickListener(view -> Utils.handleGeoCoordinates(getActivity(), place.getLocation()));
        }
        if (!ListenerUtil.mutListener.listen(4089)) {
            commonsButton.setVisibility(place.hasCommonsLink() ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(4090)) {
            commonsButton.setOnClickListener(view -> Utils.handleWebUrl(getContext(), place.siteLinks.getCommonsLink()));
        }
        int index = 0;
        if (!ListenerUtil.mutListener.listen(4094)) {
            {
                long _loopCounter58 = 0;
                for (Media media : mediaList) {
                    ListenerUtil.loopListener.listen("_loopCounter58", ++_loopCounter58);
                    if (!ListenerUtil.mutListener.listen(4092)) {
                        if (media.getFilename().equals(place.name)) {
                            int finalIndex = index;
                            if (!ListenerUtil.mutListener.listen(4091)) {
                                mediaDetailsButton.setOnClickListener(view -> {
                                    ((ExploreMapRootFragment) getParentFragment()).onMediaClicked(finalIndex);
                                });
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4093)) {
                        index++;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4095)) {
            title.setText(place.name.substring(5, place.name.lastIndexOf(".")));
        }
        if (!ListenerUtil.mutListener.listen(4096)) {
            distance.setText(place.distance);
        }
        // Remove label since it is double information
        String descriptionText = place.getLongDescription().replace(place.getName() + " (", "");
        if (!ListenerUtil.mutListener.listen(4097)) {
            descriptionText = (descriptionText.equals(place.getLongDescription()) ? descriptionText : descriptionText.replaceFirst(".$", ""));
        }
        if (!ListenerUtil.mutListener.listen(4098)) {
            // Set the short description after we remove place name from long description
            description.setText(descriptionText);
        }
    }

    @Override
    public void addSearchThisAreaButtonAction() {
        if (!ListenerUtil.mutListener.listen(4099)) {
            searchThisAreaButton.setOnClickListener(presenter.onSearchThisAreaClicked());
        }
    }

    @Override
    public void setSearchThisAreaButtonVisibility(boolean isVisible) {
        if (!ListenerUtil.mutListener.listen(4102)) {
            if (isVisible) {
                if (!ListenerUtil.mutListener.listen(4101)) {
                    searchThisAreaButton.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4100)) {
                    searchThisAreaButton.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        if (!ListenerUtil.mutListener.listen(4105)) {
            if (isVisible) {
                if (!ListenerUtil.mutListener.listen(4104)) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4103)) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public boolean isDetailsBottomSheetVisible() {
        if (bottomSheetDetails.getVisibility() == View.VISIBLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isSearchThisAreaButtonVisible() {
        if (searchThisAreaButton.getVisibility() == View.VISIBLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public LatLng getLastLocation() {
        if (!ListenerUtil.mutListener.listen(4107)) {
            if (lastKnownLocation == null) {
                if (!ListenerUtil.mutListener.listen(4106)) {
                    lastKnownLocation = locationManager.getLastLocation();
                }
            }
        }
        return lastKnownLocation;
    }

    @Override
    public void disableFABRecenter() {
        if (!ListenerUtil.mutListener.listen(4108)) {
            fabRecenter.setEnabled(false);
        }
    }

    @Override
    public void enableFABRecenter() {
        if (!ListenerUtil.mutListener.listen(4109)) {
            fabRecenter.setEnabled(true);
        }
    }

    /**
     * Adds a markers to the map based on the list of NearbyBaseMarker.
     *
     * @param nearbyBaseMarkers The NearbyBaseMarker object representing the markers to be added.
     */
    @Override
    public void addMarkersToMap(List<NearbyBaseMarker> nearbyBaseMarkers) {
        if (!ListenerUtil.mutListener.listen(4110)) {
            clearAllMarkers();
        }
        if (!ListenerUtil.mutListener.listen(4117)) {
            {
                long _loopCounter59 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(4116) ? (i >= nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(4115) ? (i <= nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(4114) ? (i > nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(4113) ? (i != nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(4112) ? (i == nearbyBaseMarkers.size()) : (i < nearbyBaseMarkers.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter59", ++_loopCounter59);
                    if (!ListenerUtil.mutListener.listen(4111)) {
                        addMarkerToMap(nearbyBaseMarkers.get(i));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4118)) {
            mapView.invalidate();
        }
    }

    /**
     * Adds a marker to the map based on the specified NearbyBaseMarker.
     *
     * @param nearbyBaseMarker The NearbyBaseMarker object representing the marker to be added.
     */
    private void addMarkerToMap(NearbyBaseMarker nearbyBaseMarker) {
        ArrayList<OverlayItem> items = new ArrayList<>();
        Bitmap icon = nearbyBaseMarker.getMarker().getIcon().getBitmap();
        Drawable d = new BitmapDrawable(getResources(), icon);
        GeoPoint point = new GeoPoint(nearbyBaseMarker.getPlace().location.getLatitude(), nearbyBaseMarker.getPlace().location.getLongitude());
        OverlayItem item = new OverlayItem(nearbyBaseMarker.getPlace().name, null, point);
        if (!ListenerUtil.mutListener.listen(4119)) {
            item.setMarker(d);
        }
        if (!ListenerUtil.mutListener.listen(4120)) {
            items.add(item);
        }
        ItemizedOverlayWithFocus overlay = new ItemizedOverlayWithFocus(items, new OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                final Place place = nearbyBaseMarker.getPlace();
                if (!ListenerUtil.mutListener.listen(4125)) {
                    if (clickedMarker != null) {
                        if (!ListenerUtil.mutListener.listen(4121)) {
                            removeMarker(clickedMarker);
                        }
                        if (!ListenerUtil.mutListener.listen(4122)) {
                            addMarkerToMap(clickedMarker);
                        }
                        if (!ListenerUtil.mutListener.listen(4123)) {
                            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }
                        if (!ListenerUtil.mutListener.listen(4124)) {
                            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4126)) {
                    clickedMarker = nearbyBaseMarker;
                }
                if (!ListenerUtil.mutListener.listen(4127)) {
                    passInfoToSheet(place);
                }
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        }, getContext());
        if (!ListenerUtil.mutListener.listen(4128)) {
            overlay.setFocusItemsOnTap(true);
        }
        if (!ListenerUtil.mutListener.listen(4129)) {
            // Add the overlay to the map
            mapView.getOverlays().add(overlay);
        }
    }

    /**
     * Removes a marker from the map based on the specified NearbyBaseMarker.
     *
     * @param nearbyBaseMarker The NearbyBaseMarker object representing the marker to be removed.
     */
    private void removeMarker(NearbyBaseMarker nearbyBaseMarker) {
        Place place = nearbyBaseMarker.getPlace();
        List<Overlay> overlays = mapView.getOverlays();
        ItemizedOverlayWithFocus item;
        {
            long _loopCounter60 = 0;
            for (int i = 0; (ListenerUtil.mutListener.listen(4138) ? (i >= overlays.size()) : (ListenerUtil.mutListener.listen(4137) ? (i <= overlays.size()) : (ListenerUtil.mutListener.listen(4136) ? (i > overlays.size()) : (ListenerUtil.mutListener.listen(4135) ? (i != overlays.size()) : (ListenerUtil.mutListener.listen(4134) ? (i == overlays.size()) : (i < overlays.size())))))); i++) {
                ListenerUtil.loopListener.listen("_loopCounter60", ++_loopCounter60);
                if (overlays.get(i) instanceof ItemizedOverlayWithFocus) {
                    item = (ItemizedOverlayWithFocus) overlays.get(i);
                    OverlayItem overlayItem = item.getItem(0);
                    if (!ListenerUtil.mutListener.listen(4133)) {
                        if ((ListenerUtil.mutListener.listen(4130) ? (place.location.getLatitude() == overlayItem.getPoint().getLatitude() || place.location.getLongitude() == overlayItem.getPoint().getLongitude()) : (place.location.getLatitude() == overlayItem.getPoint().getLatitude() && place.location.getLongitude() == overlayItem.getPoint().getLongitude()))) {
                            if (!ListenerUtil.mutListener.listen(4131)) {
                                mapView.getOverlays().remove(i);
                            }
                            if (!ListenerUtil.mutListener.listen(4132)) {
                                mapView.invalidate();
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Clears all markers from the map and resets certain map overlays and gestures. After clearing
     * markers, it re-adds a scale bar overlay and rotation gesture overlay to the map.
     */
    @Override
    public void clearAllMarkers() {
        if (!ListenerUtil.mutListener.listen(4139)) {
            mapView.getOverlayManager().clear();
        }
        GeoPoint geoPoint = mapCenter;
        if (!ListenerUtil.mutListener.listen(4156)) {
            if (geoPoint != null) {
                List<Overlay> overlays = mapView.getOverlays();
                ScaleDiskOverlay diskOverlay = new ScaleDiskOverlay(this.getContext(), geoPoint, 2000, GeoConstants.UnitOfMeasure.foot);
                Paint circlePaint = new Paint();
                if (!ListenerUtil.mutListener.listen(4140)) {
                    circlePaint.setColor(Color.rgb(128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(4141)) {
                    circlePaint.setStyle(Paint.Style.STROKE);
                }
                if (!ListenerUtil.mutListener.listen(4142)) {
                    circlePaint.setStrokeWidth(2f);
                }
                if (!ListenerUtil.mutListener.listen(4143)) {
                    diskOverlay.setCirclePaint2(circlePaint);
                }
                Paint diskPaint = new Paint();
                if (!ListenerUtil.mutListener.listen(4144)) {
                    diskPaint.setColor(Color.argb(40, 128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(4145)) {
                    diskPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                }
                if (!ListenerUtil.mutListener.listen(4146)) {
                    diskOverlay.setCirclePaint1(diskPaint);
                }
                if (!ListenerUtil.mutListener.listen(4147)) {
                    diskOverlay.setDisplaySizeMin(900);
                }
                if (!ListenerUtil.mutListener.listen(4148)) {
                    diskOverlay.setDisplaySizeMax(1700);
                }
                if (!ListenerUtil.mutListener.listen(4149)) {
                    mapView.getOverlays().add(diskOverlay);
                }
                org.osmdroid.views.overlay.Marker startMarker = new org.osmdroid.views.overlay.Marker(mapView);
                if (!ListenerUtil.mutListener.listen(4150)) {
                    startMarker.setPosition(geoPoint);
                }
                if (!ListenerUtil.mutListener.listen(4151)) {
                    startMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
                }
                if (!ListenerUtil.mutListener.listen(4152)) {
                    startMarker.setIcon(ContextCompat.getDrawable(this.getContext(), R.drawable.current_location_marker));
                }
                if (!ListenerUtil.mutListener.listen(4153)) {
                    startMarker.setTitle("Your Location");
                }
                if (!ListenerUtil.mutListener.listen(4154)) {
                    startMarker.setTextLabelFontSize(24);
                }
                if (!ListenerUtil.mutListener.listen(4155)) {
                    mapView.getOverlays().add(startMarker);
                }
            }
        }
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        if (!ListenerUtil.mutListener.listen(4157)) {
            scaleBarOverlay.setScaleBarOffset(15, 25);
        }
        Paint barPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(4158)) {
            barPaint.setARGB(200, 255, 250, 250);
        }
        if (!ListenerUtil.mutListener.listen(4159)) {
            scaleBarOverlay.setBackgroundPaint(barPaint);
        }
        if (!ListenerUtil.mutListener.listen(4160)) {
            scaleBarOverlay.enableScaleBar();
        }
        if (!ListenerUtil.mutListener.listen(4161)) {
            mapView.getOverlays().add(scaleBarOverlay);
        }
        if (!ListenerUtil.mutListener.listen(4170)) {
            mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {

                @Override
                public boolean singleTapConfirmedHelper(GeoPoint p) {
                    if (!ListenerUtil.mutListener.listen(4166)) {
                        if (clickedMarker != null) {
                            if (!ListenerUtil.mutListener.listen(4163)) {
                                removeMarker(clickedMarker);
                            }
                            if (!ListenerUtil.mutListener.listen(4164)) {
                                addMarkerToMap(clickedMarker);
                            }
                            if (!ListenerUtil.mutListener.listen(4165)) {
                                mapView.invalidate();
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4162)) {
                                Timber.e("CLICKED MARKER IS NULL");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4169)) {
                        if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            if (!ListenerUtil.mutListener.listen(4168)) {
                                // Back should first hide the bottom sheet if it is expanded
                                bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            }
                        } else if (isDetailsBottomSheetVisible()) {
                            if (!ListenerUtil.mutListener.listen(4167)) {
                                hideBottomDetailsSheet();
                            }
                        }
                    }
                    return true;
                }

                @Override
                public boolean longPressHelper(GeoPoint p) {
                    return false;
                }
            }));
        }
        if (!ListenerUtil.mutListener.listen(4171)) {
            mapView.setMultiTouchControls(true);
        }
    }

    /**
     * Recenters the map view to the specified GeoPoint and updates the marker to indicate the new
     * position.
     *
     * @param geoPoint The GeoPoint representing the new center position for the map.
     */
    private void recenterMarkerToPosition(GeoPoint geoPoint) {
        if (!ListenerUtil.mutListener.listen(4198)) {
            if (geoPoint != null) {
                if (!ListenerUtil.mutListener.listen(4172)) {
                    mapView.getController().setCenter(geoPoint);
                }
                List<Overlay> overlays = mapView.getOverlays();
                if (!ListenerUtil.mutListener.listen(4181)) {
                    {
                        long _loopCounter61 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(4180) ? (i >= overlays.size()) : (ListenerUtil.mutListener.listen(4179) ? (i <= overlays.size()) : (ListenerUtil.mutListener.listen(4178) ? (i > overlays.size()) : (ListenerUtil.mutListener.listen(4177) ? (i != overlays.size()) : (ListenerUtil.mutListener.listen(4176) ? (i == overlays.size()) : (i < overlays.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter61", ++_loopCounter61);
                            if (!ListenerUtil.mutListener.listen(4175)) {
                                if (overlays.get(i) instanceof org.osmdroid.views.overlay.Marker) {
                                    if (!ListenerUtil.mutListener.listen(4174)) {
                                        mapView.getOverlays().remove(i);
                                    }
                                } else if (overlays.get(i) instanceof ScaleDiskOverlay) {
                                    if (!ListenerUtil.mutListener.listen(4173)) {
                                        mapView.getOverlays().remove(i);
                                    }
                                }
                            }
                        }
                    }
                }
                ScaleDiskOverlay diskOverlay = new ScaleDiskOverlay(this.getContext(), geoPoint, 2000, GeoConstants.UnitOfMeasure.foot);
                Paint circlePaint = new Paint();
                if (!ListenerUtil.mutListener.listen(4182)) {
                    circlePaint.setColor(Color.rgb(128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(4183)) {
                    circlePaint.setStyle(Paint.Style.STROKE);
                }
                if (!ListenerUtil.mutListener.listen(4184)) {
                    circlePaint.setStrokeWidth(2f);
                }
                if (!ListenerUtil.mutListener.listen(4185)) {
                    diskOverlay.setCirclePaint2(circlePaint);
                }
                Paint diskPaint = new Paint();
                if (!ListenerUtil.mutListener.listen(4186)) {
                    diskPaint.setColor(Color.argb(40, 128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(4187)) {
                    diskPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                }
                if (!ListenerUtil.mutListener.listen(4188)) {
                    diskOverlay.setCirclePaint1(diskPaint);
                }
                if (!ListenerUtil.mutListener.listen(4189)) {
                    diskOverlay.setDisplaySizeMin(900);
                }
                if (!ListenerUtil.mutListener.listen(4190)) {
                    diskOverlay.setDisplaySizeMax(1700);
                }
                if (!ListenerUtil.mutListener.listen(4191)) {
                    mapView.getOverlays().add(diskOverlay);
                }
                org.osmdroid.views.overlay.Marker startMarker = new org.osmdroid.views.overlay.Marker(mapView);
                if (!ListenerUtil.mutListener.listen(4192)) {
                    startMarker.setPosition(geoPoint);
                }
                if (!ListenerUtil.mutListener.listen(4193)) {
                    startMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
                }
                if (!ListenerUtil.mutListener.listen(4194)) {
                    startMarker.setIcon(ContextCompat.getDrawable(this.getContext(), R.drawable.current_location_marker));
                }
                if (!ListenerUtil.mutListener.listen(4195)) {
                    startMarker.setTitle("Your Location");
                }
                if (!ListenerUtil.mutListener.listen(4196)) {
                    startMarker.setTextLabelFontSize(24);
                }
                if (!ListenerUtil.mutListener.listen(4197)) {
                    mapView.getOverlays().add(startMarker);
                }
            }
        }
    }

    /**
     * Moves the camera of the map view to the specified GeoPoint using an animation.
     *
     * @param geoPoint The GeoPoint representing the new camera position for the map.
     */
    private void moveCameraToPosition(GeoPoint geoPoint) {
        if (!ListenerUtil.mutListener.listen(4199)) {
            mapView.getController().animateTo(geoPoint);
        }
    }

    @Override
    public fr.free.nrw.commons.location.LatLng getLastMapFocus() {
        return lastMapFocus == null ? getMapCenter() : new fr.free.nrw.commons.location.LatLng(lastMapFocus.getLatitude(), lastMapFocus.getLongitude(), 100);
    }

    @Override
    public fr.free.nrw.commons.location.LatLng getMapCenter() {
        fr.free.nrw.commons.location.LatLng latLnge = null;
        if (!ListenerUtil.mutListener.listen(4201)) {
            if (mapCenter != null) {
                if (!ListenerUtil.mutListener.listen(4200)) {
                    latLnge = new fr.free.nrw.commons.location.LatLng(mapCenter.getLatitude(), mapCenter.getLongitude(), 100);
                }
            }
        }
        return latLnge;
    }

    @Override
    public fr.free.nrw.commons.location.LatLng getMapFocus() {
        fr.free.nrw.commons.location.LatLng mapFocusedLatLng = new fr.free.nrw.commons.location.LatLng(mapView.getMapCenter().getLatitude(), mapView.getMapCenter().getLongitude(), 100);
        return mapFocusedLatLng;
    }

    @Override
    public void setFABRecenterAction(OnClickListener onClickListener) {
        if (!ListenerUtil.mutListener.listen(4202)) {
            fabRecenter.setOnClickListener(onClickListener);
        }
    }

    @Override
    public boolean backButtonClicked() {
        if (!(bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN)) {
            if (!ListenerUtil.mutListener.listen(4203)) {
                bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds network broadcast receiver to recognize connection established
     */
    private void initNetworkBroadCastReceiver() {
        if (!ListenerUtil.mutListener.listen(4218)) {
            broadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(final Context context, final Intent intent) {
                    if (!ListenerUtil.mutListener.listen(4217)) {
                        if (getActivity() != null) {
                            if (!ListenerUtil.mutListener.listen(4216)) {
                                if (NetworkUtils.isInternetConnectionEstablished(getActivity())) {
                                    if (!ListenerUtil.mutListener.listen(4212)) {
                                        if (isNetworkErrorOccurred) {
                                            if (!ListenerUtil.mutListener.listen(4210)) {
                                                presenter.updateMap(LOCATION_SIGNIFICANTLY_CHANGED);
                                            }
                                            if (!ListenerUtil.mutListener.listen(4211)) {
                                                isNetworkErrorOccurred = false;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(4215)) {
                                        if (snackbar != null) {
                                            if (!ListenerUtil.mutListener.listen(4213)) {
                                                snackbar.dismiss();
                                            }
                                            if (!ListenerUtil.mutListener.listen(4214)) {
                                                snackbar = null;
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(4207)) {
                                        if (snackbar == null) {
                                            if (!ListenerUtil.mutListener.listen(4204)) {
                                                snackbar = Snackbar.make(getView(), R.string.no_internet, Snackbar.LENGTH_INDEFINITE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(4205)) {
                                                setSearchThisAreaButtonVisibility(false);
                                            }
                                            if (!ListenerUtil.mutListener.listen(4206)) {
                                                setProgressBarVisibility(false);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(4208)) {
                                        isNetworkErrorOccurred = true;
                                    }
                                    if (!ListenerUtil.mutListener.listen(4209)) {
                                        snackbar.show();
                                    }
                                }
                            }
                        }
                    }
                }
            };
        }
    }
}
