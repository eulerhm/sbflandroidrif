package fr.free.nrw.commons.nearby.fragments;

import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.CUSTOM_QUERY;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.LOCATION_SIGNIFICANTLY_CHANGED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.LOCATION_SLIGHTLY_CHANGED;
import static fr.free.nrw.commons.location.LocationServiceManager.LocationChangeType.MAP_UPDATED;
import static fr.free.nrw.commons.wikidata.WikidataConstants.PLACE_OBJECT;
import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding3.appcompat.RxSearchView;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import fr.free.nrw.commons.CommonsApplication;
import fr.free.nrw.commons.MapController.NearbyPlacesInfo;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.auth.LoginActivity;
import fr.free.nrw.commons.bookmarks.locations.BookmarkLocationsDao;
import fr.free.nrw.commons.contributions.ContributionController;
import fr.free.nrw.commons.contributions.MainActivity;
import fr.free.nrw.commons.contributions.MainActivity.ActiveFragment;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.location.LocationUpdateListener;
import fr.free.nrw.commons.nearby.CheckBoxTriStates;
import fr.free.nrw.commons.nearby.Label;
import fr.free.nrw.commons.nearby.MarkerPlaceGroup;
import fr.free.nrw.commons.nearby.NearbyBaseMarker;
import fr.free.nrw.commons.nearby.NearbyController;
import fr.free.nrw.commons.nearby.NearbyFilterSearchRecyclerViewAdapter;
import fr.free.nrw.commons.nearby.NearbyFilterState;
import fr.free.nrw.commons.nearby.NearbyMarker;
import fr.free.nrw.commons.nearby.Place;
import fr.free.nrw.commons.nearby.contract.NearbyParentFragmentContract;
import fr.free.nrw.commons.nearby.fragments.AdvanceQueryFragment.Callback;
import fr.free.nrw.commons.nearby.presenter.NearbyParentFragmentPresenter;
import fr.free.nrw.commons.upload.FileUtils;
import fr.free.nrw.commons.utils.DialogUtil;
import fr.free.nrw.commons.utils.ExecutorUtils;
import fr.free.nrw.commons.utils.LayoutUtils;
import fr.free.nrw.commons.utils.LocationUtils;
import fr.free.nrw.commons.utils.NearbyFABUtils;
import fr.free.nrw.commons.utils.NetworkUtils;
import fr.free.nrw.commons.utils.PermissionUtils;
import fr.free.nrw.commons.utils.SystemThemeUtils;
import fr.free.nrw.commons.utils.ViewUtil;
import fr.free.nrw.commons.wikidata.WikidataEditListener;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
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

public class NearbyParentFragment extends CommonsDaggerSupportFragment implements NearbyParentFragmentContract.View, WikidataEditListener.WikidataP18EditListener, LocationUpdateListener {

    @BindView(R.id.bottom_sheet)
    RelativeLayout rlBottomSheet;

    @BindView(R.id.bottom_sheet_details)
    View bottomSheetDetails;

    @BindView(R.id.transparentView)
    View transparentView;

    @BindView(R.id.directionsButtonText)
    TextView directionsButtonText;

    @BindView(R.id.wikipediaButtonText)
    TextView wikipediaButtonText;

    @BindView(R.id.wikidataButtonText)
    TextView wikidataButtonText;

    @BindView(R.id.commonsButtonText)
    TextView commonsButtonText;

    @BindView(R.id.fab_plus)
    FloatingActionButton fabPlus;

    @BindView(R.id.fab_camera)
    FloatingActionButton fabCamera;

    @BindView(R.id.fab_gallery)
    FloatingActionButton fabGallery;

    @BindView(R.id.fab_recenter)
    FloatingActionButton fabRecenter;

    @BindView(R.id.bookmarkButtonImage)
    ImageView bookmarkButtonImage;

    @BindView(R.id.bookmarkButton)
    LinearLayout bookmarkButton;

    @BindView(R.id.wikipediaButton)
    LinearLayout wikipediaButton;

    @BindView(R.id.wikidataButton)
    LinearLayout wikidataButton;

    @BindView(R.id.directionsButton)
    LinearLayout directionsButton;

    @BindView(R.id.commonsButton)
    LinearLayout commonsButton;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.category)
    TextView distance;

    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.search_this_area_button)
    Button searchThisAreaButton;

    @BindView(R.id.map_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.choice_chip_exists)
    Chip chipExists;

    @BindView(R.id.choice_chip_wlm)
    Chip chipWlm;

    @BindView(R.id.choice_chip_needs_photo)
    Chip chipNeedsPhoto;

    @BindView(R.id.choice_chip_group)
    ChipGroup choiceChipGroup;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.search_list_view)
    RecyclerView recyclerView;

    @BindView(R.id.nearby_filter_list)
    View nearbyFilterList;

    @BindView(R.id.checkbox_tri_states)
    CheckBoxTriStates checkBoxTriStates;

    @BindView(R.id.map)
    org.osmdroid.views.MapView mapView;

    @BindView(R.id.rv_nearby_list)
    RecyclerView rvNearbyList;

    @BindView(R.id.no_results_message)
    TextView noResultsView;

    @BindView(R.id.tv_attribution)
    AppCompatTextView tvAttribution;

    @BindView(R.id.rl_container_wlm_month_message)
    RelativeLayout rlContainerWLMMonthMessage;

    @BindView(R.id.tv_learn_more)
    AppCompatTextView tvLearnMore;

    @BindView(R.id.iv_toggle_chips)
    AppCompatImageView ivToggleChips;

    @BindView(R.id.chip_view)
    View llContainerChips;

    @BindView(R.id.btn_advanced_options)
    AppCompatButton btnAdvancedOptions;

    @BindView(R.id.fl_container_nearby_children)
    FrameLayout flConainerNearbyChildren;

    @Inject
    LocationServiceManager locationManager;

    @Inject
    NearbyController nearbyController;

    @Inject
    @Named("default_preferences")
    JsonKvStore applicationKvStore;

    @Inject
    BookmarkLocationsDao bookmarkLocationDao;

    @Inject
    ContributionController controller;

    @Inject
    WikidataEditListener wikidataEditListener;

    @Inject
    SystemThemeUtils systemThemeUtils;

    @Inject
    CommonPlaceClickActions commonPlaceClickActions;

    private NearbyFilterSearchRecyclerViewAdapter nearbyFilterSearchRecyclerViewAdapter;

    private BottomSheetBehavior bottomSheetListBehavior;

    private BottomSheetBehavior bottomSheetDetailsBehavior;

    private Animation rotate_backward;

    private Animation fab_close;

    private Animation fab_open;

    private Animation rotate_forward;

    private static final float ZOOM_LEVEL = 14f;

    private final String NETWORK_INTENT_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private BroadcastReceiver broadcastReceiver;

    private boolean isNetworkErrorOccurred;

    private Snackbar snackbar;

    private View view;

    private NearbyParentFragmentPresenter presenter;

    private boolean isDarkTheme;

    private boolean isFABsExpanded;

    private Marker selectedMarker;

    private Place selectedPlace;

    private Place clickedMarkerPlace;

    private boolean isClickedMarkerBookmarked;

    private final double CAMERA_TARGET_SHIFT_FACTOR_PORTRAIT = 0.005;

    private final double CAMERA_TARGET_SHIFT_FACTOR_LANDSCAPE = 0.004;

    private boolean isPermissionDenied;

    private boolean recenterToUserLocation;

    private GeoPoint mapCenter;

    IntentFilter intentFilter = new IntentFilter(NETWORK_INTENT_ACTION);

    private Marker currentLocationMarker;

    private Place lastPlaceToCenter;

    private fr.free.nrw.commons.location.LatLng lastKnownLocation;

    private boolean isVisibleToUser;

    private fr.free.nrw.commons.location.LatLng lastFocusLocation;

    private LatLngBounds latLngBounds;

    private PlaceAdapter adapter;

    private GeoPoint lastMapFocus;

    private NearbyParentFragmentInstanceReadyCallback nearbyParentFragmentInstanceReadyCallback;

    private boolean isAdvancedQueryFragmentVisible = false;

    private Place nearestPlace;

    private ActivityResultLauncher<String[]> inAppCameraLocationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {

        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            boolean areAllGranted = true;
            if (!ListenerUtil.mutListener.listen(2673)) {
                {
                    long _loopCounter38 = 0;
                    for (final boolean b : result.values()) {
                        ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                        if (!ListenerUtil.mutListener.listen(2672)) {
                            areAllGranted = (ListenerUtil.mutListener.listen(2671) ? (areAllGranted || b) : (areAllGranted && b));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2678)) {
                if (areAllGranted) {
                    if (!ListenerUtil.mutListener.listen(2677)) {
                        controller.locationPermissionCallback.onLocationPermissionGranted();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2676)) {
                        if (shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION)) {
                            if (!ListenerUtil.mutListener.listen(2675)) {
                                controller.handleShowRationaleFlowCameraLocation(getActivity());
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2674)) {
                                controller.locationPermissionCallback.onLocationPermissionDenied(getActivity().getString(R.string.in_app_camera_location_permission_denied));
                            }
                        }
                    }
                }
            }
        }
    });

    private ActivityResultLauncher<String> locationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            locationPermissionGranted();
        } else {
            if (shouldShowRequestPermissionRationale(permission.ACCESS_FINE_LOCATION)) {
                DialogUtil.showAlertDialog(getActivity(), getActivity().getString(R.string.location_permission_title), getActivity().getString(R.string.location_permission_rationale_nearby), getActivity().getString(android.R.string.ok), getActivity().getString(android.R.string.cancel), () -> {
                    if (!(locationManager.isNetworkProviderEnabled() || locationManager.isGPSProviderEnabled())) {
                        showLocationOffDialog();
                    }
                }, () -> isPermissionDenied = true, null, false);
            } else {
                isPermissionDenied = true;
            }
        }
    });

    /**
     * WLM URL
     */
    public static final String WLM_URL = "https://commons.wikimedia.org/wiki/Commons:Mobile_app/Contributing_to_WLM_using_the_app";

    /**
     * Saves response of list of places for the first time
     */
    private List<Place> places = new ArrayList<>();

    @NonNull
    public static NearbyParentFragment newInstance() {
        NearbyParentFragment fragment = new NearbyParentFragment();
        if (!ListenerUtil.mutListener.listen(2679)) {
            fragment.setRetainInstance(true);
        }
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2680)) {
            view = inflater.inflate(R.layout.fragment_nearby_parent, container, false);
        }
        if (!ListenerUtil.mutListener.listen(2681)) {
            ButterKnife.bind(this, view);
        }
        if (!ListenerUtil.mutListener.listen(2682)) {
            initNetworkBroadCastReceiver();
        }
        if (!ListenerUtil.mutListener.listen(2683)) {
            presenter = new NearbyParentFragmentPresenter(bookmarkLocationDao);
        }
        if (!ListenerUtil.mutListener.listen(2684)) {
            setHasOptionsMenu(true);
        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(2685)) {
            inflater.inflate(R.menu.nearby_fragment_menu, menu);
        }
        MenuItem listMenu = menu.findItem(R.id.list_sheet);
        if (!ListenerUtil.mutListener.listen(2687)) {
            listMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(2686)) {
                        listOptionMenuItemClicked();
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2688)) {
            super.onViewCreated(view, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2689)) {
            isDarkTheme = systemThemeUtils.isDeviceInNightMode();
        }
        if (!ListenerUtil.mutListener.listen(2692)) {
            if (Utils.isMonumentsEnabled(new Date())) {
                if (!ListenerUtil.mutListener.listen(2691)) {
                    rlContainerWLMMonthMessage.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2690)) {
                    rlContainerWLMMonthMessage.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2693)) {
            presenter.attachView(this);
        }
        if (!ListenerUtil.mutListener.listen(2694)) {
            isPermissionDenied = false;
        }
        if (!ListenerUtil.mutListener.listen(2695)) {
            recenterToUserLocation = false;
        }
        if (!ListenerUtil.mutListener.listen(2696)) {
            initThemePreferences();
        }
        if (!ListenerUtil.mutListener.listen(2697)) {
            initViews();
        }
        if (!ListenerUtil.mutListener.listen(2698)) {
            presenter.setActionListeners(applicationKvStore);
        }
        if (!ListenerUtil.mutListener.listen(2699)) {
            org.osmdroid.config.Configuration.getInstance().load(this.getContext(), PreferenceManager.getDefaultSharedPreferences(this.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(2700)) {
            // restrictions that we do not satisfy.
            mapView.setTileSource(TileSourceFactory.WIKIMEDIA);
        }
        if (!ListenerUtil.mutListener.listen(2701)) {
            mapView.setTilesScaledToDpi(true);
        }
        if (!ListenerUtil.mutListener.listen(2702)) {
            // This was suggested by Dmitry Brant within an email thread between us and WMF.
            org.osmdroid.config.Configuration.getInstance().getAdditionalHttpRequestProperties().put("Referer", "http://maps.wikimedia.org/");
        }
        if (!ListenerUtil.mutListener.listen(2705)) {
            if (applicationKvStore.getString("LastLocation") != null) {
                // Checking for last searched location
                String[] locationLatLng = applicationKvStore.getString("LastLocation").split(",");
                if (!ListenerUtil.mutListener.listen(2704)) {
                    lastMapFocus = new GeoPoint(Double.valueOf(locationLatLng[0]), Double.valueOf(locationLatLng[1]));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2703)) {
                    lastMapFocus = new GeoPoint(51.50550, -0.07520);
                }
            }
        }
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        if (!ListenerUtil.mutListener.listen(2706)) {
            scaleBarOverlay.setScaleBarOffset(15, 25);
        }
        Paint barPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(2707)) {
            barPaint.setARGB(200, 255, 250, 250);
        }
        if (!ListenerUtil.mutListener.listen(2708)) {
            scaleBarOverlay.setBackgroundPaint(barPaint);
        }
        if (!ListenerUtil.mutListener.listen(2709)) {
            scaleBarOverlay.enableScaleBar();
        }
        if (!ListenerUtil.mutListener.listen(2710)) {
            mapView.getOverlays().add(scaleBarOverlay);
        }
        if (!ListenerUtil.mutListener.listen(2711)) {
            mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        }
        if (!ListenerUtil.mutListener.listen(2712)) {
            mapView.getController().setZoom(ZOOM_LEVEL);
        }
        if (!ListenerUtil.mutListener.listen(2720)) {
            mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {

                @Override
                public boolean singleTapConfirmedHelper(GeoPoint p) {
                    if (!ListenerUtil.mutListener.listen(2716)) {
                        if (clickedMarkerPlace != null) {
                            if (!ListenerUtil.mutListener.listen(2714)) {
                                removeMarker(clickedMarkerPlace);
                            }
                            if (!ListenerUtil.mutListener.listen(2715)) {
                                addMarkerToMap(clickedMarkerPlace, isClickedMarkerBookmarked);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2713)) {
                                Timber.e("CLICKED MARKER IS NULL");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2719)) {
                        if (isListBottomSheetExpanded()) {
                            if (!ListenerUtil.mutListener.listen(2718)) {
                                // Back should first hide the bottom sheet if it is expanded
                                hideBottomSheet();
                            }
                        } else if (isDetailsBottomSheetVisible()) {
                            if (!ListenerUtil.mutListener.listen(2717)) {
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
        if (!ListenerUtil.mutListener.listen(2749)) {
            mapView.addMapListener(new MapListener() {

                @Override
                public boolean onScroll(ScrollEvent event) {
                    if (!ListenerUtil.mutListener.listen(2748)) {
                        if (lastMapFocus != null) {
                            Location mylocation = new Location("");
                            Location dest_location = new Location("");
                            if (!ListenerUtil.mutListener.listen(2721)) {
                                dest_location.setLatitude(mapView.getMapCenter().getLatitude());
                            }
                            if (!ListenerUtil.mutListener.listen(2722)) {
                                dest_location.setLongitude(mapView.getMapCenter().getLongitude());
                            }
                            if (!ListenerUtil.mutListener.listen(2723)) {
                                mylocation.setLatitude(lastMapFocus.getLatitude());
                            }
                            if (!ListenerUtil.mutListener.listen(2724)) {
                                mylocation.setLongitude(lastMapFocus.getLongitude());
                            }
                            // in meters
                            Float distance = mylocation.distanceTo(dest_location);
                            if (!ListenerUtil.mutListener.listen(2747)) {
                                if (lastMapFocus != null) {
                                    if (!ListenerUtil.mutListener.listen(2746)) {
                                        if ((ListenerUtil.mutListener.listen(2737) ? (isNetworkConnectionEstablished() || ((ListenerUtil.mutListener.listen(2736) ? ((ListenerUtil.mutListener.listen(2730) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(2729) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(2728) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(2727) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(2726) ? (event.getX() == 0) : (event.getX() > 0)))))) && (ListenerUtil.mutListener.listen(2735) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(2734) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(2733) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(2732) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(2731) ? (event.getY() == 0) : (event.getY() > 0))))))) : ((ListenerUtil.mutListener.listen(2730) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(2729) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(2728) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(2727) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(2726) ? (event.getX() == 0) : (event.getX() > 0)))))) || (ListenerUtil.mutListener.listen(2735) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(2734) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(2733) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(2732) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(2731) ? (event.getY() == 0) : (event.getY() > 0)))))))))) : (isNetworkConnectionEstablished() && ((ListenerUtil.mutListener.listen(2736) ? ((ListenerUtil.mutListener.listen(2730) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(2729) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(2728) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(2727) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(2726) ? (event.getX() == 0) : (event.getX() > 0)))))) && (ListenerUtil.mutListener.listen(2735) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(2734) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(2733) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(2732) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(2731) ? (event.getY() == 0) : (event.getY() > 0))))))) : ((ListenerUtil.mutListener.listen(2730) ? (event.getX() >= 0) : (ListenerUtil.mutListener.listen(2729) ? (event.getX() <= 0) : (ListenerUtil.mutListener.listen(2728) ? (event.getX() < 0) : (ListenerUtil.mutListener.listen(2727) ? (event.getX() != 0) : (ListenerUtil.mutListener.listen(2726) ? (event.getX() == 0) : (event.getX() > 0)))))) || (ListenerUtil.mutListener.listen(2735) ? (event.getY() >= 0) : (ListenerUtil.mutListener.listen(2734) ? (event.getY() <= 0) : (ListenerUtil.mutListener.listen(2733) ? (event.getY() < 0) : (ListenerUtil.mutListener.listen(2732) ? (event.getY() != 0) : (ListenerUtil.mutListener.listen(2731) ? (event.getY() == 0) : (event.getY() > 0)))))))))))) {
                                            if (!ListenerUtil.mutListener.listen(2745)) {
                                                if ((ListenerUtil.mutListener.listen(2742) ? (distance >= 2000.0) : (ListenerUtil.mutListener.listen(2741) ? (distance <= 2000.0) : (ListenerUtil.mutListener.listen(2740) ? (distance < 2000.0) : (ListenerUtil.mutListener.listen(2739) ? (distance != 2000.0) : (ListenerUtil.mutListener.listen(2738) ? (distance == 2000.0) : (distance > 2000.0))))))) {
                                                    if (!ListenerUtil.mutListener.listen(2744)) {
                                                        setSearchThisAreaButtonVisibility(true);
                                                    }
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(2743)) {
                                                        setSearchThisAreaButtonVisibility(false);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2725)) {
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
        if (!ListenerUtil.mutListener.listen(2750)) {
            mapView.setMultiTouchControls(true);
        }
        if (!ListenerUtil.mutListener.listen(2752)) {
            if (nearbyParentFragmentInstanceReadyCallback != null) {
                if (!ListenerUtil.mutListener.listen(2751)) {
                    nearbyParentFragmentInstanceReadyCallback.onReady();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2753)) {
            initNearbyFilter();
        }
        if (!ListenerUtil.mutListener.listen(2754)) {
            addCheckBoxCallback();
        }
        if (!ListenerUtil.mutListener.listen(2755)) {
            performMapReadyActions();
        }
        if (!ListenerUtil.mutListener.listen(2756)) {
            moveCameraToPosition(lastMapFocus);
        }
        if (!ListenerUtil.mutListener.listen(2757)) {
            initRvNearbyList();
        }
        if (!ListenerUtil.mutListener.listen(2758)) {
            onResume();
        }
        if (!ListenerUtil.mutListener.listen(2759)) {
            tvAttribution.setText(Html.fromHtml(getString(R.string.map_attribution)));
        }
        if (!ListenerUtil.mutListener.listen(2760)) {
            tvAttribution.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (!ListenerUtil.mutListener.listen(2761)) {
            btnAdvancedOptions.setOnClickListener(v -> {
                searchView.clearFocus();
                showHideAdvancedQueryFragment(true);
                final AdvanceQueryFragment fragment = new AdvanceQueryFragment();
                final Bundle bundle = new Bundle();
                try {
                    bundle.putString("query", FileUtils.readFromResource("/queries/nearby_query.rq"));
                } catch (IOException e) {
                    Timber.e(e);
                }
                fragment.setArguments(bundle);
                fragment.callback = new Callback() {

                    @Override
                    public void close() {
                        showHideAdvancedQueryFragment(false);
                    }

                    @Override
                    public void reset() {
                        presenter.setAdvancedQuery(null);
                        presenter.updateMapAndList(LOCATION_SIGNIFICANTLY_CHANGED);
                        showHideAdvancedQueryFragment(false);
                    }

                    @Override
                    public void apply(@NotNull final String query) {
                        presenter.setAdvancedQuery(query);
                        presenter.updateMapAndList(CUSTOM_QUERY);
                        showHideAdvancedQueryFragment(false);
                    }
                };
                getChildFragmentManager().beginTransaction().replace(R.id.fl_container_nearby_children, fragment).commit();
            });
        }
    }

    /**
     * Initialise background based on theme, this should be doe ideally via styles, that would need
     * another refactor
     */
    private void initThemePreferences() {
        if (!ListenerUtil.mutListener.listen(2771)) {
            if (isDarkTheme) {
                if (!ListenerUtil.mutListener.listen(2766)) {
                    rvNearbyList.setBackgroundColor(getContext().getResources().getColor(R.color.contributionListDarkBackground));
                }
                if (!ListenerUtil.mutListener.listen(2767)) {
                    checkBoxTriStates.setTextColor(getContext().getResources().getColor(android.R.color.white));
                }
                if (!ListenerUtil.mutListener.listen(2768)) {
                    checkBoxTriStates.setTextColor(getContext().getResources().getColor(android.R.color.white));
                }
                if (!ListenerUtil.mutListener.listen(2769)) {
                    nearbyFilterList.setBackgroundColor(getContext().getResources().getColor(R.color.contributionListDarkBackground));
                }
                if (!ListenerUtil.mutListener.listen(2770)) {
                    mapView.getOverlayManager().getTilesOverlay().setColorFilter(TilesOverlay.INVERT_COLORS);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2762)) {
                    rvNearbyList.setBackgroundColor(getContext().getResources().getColor(android.R.color.white));
                }
                if (!ListenerUtil.mutListener.listen(2763)) {
                    checkBoxTriStates.setTextColor(getContext().getResources().getColor(R.color.contributionListDarkBackground));
                }
                if (!ListenerUtil.mutListener.listen(2764)) {
                    nearbyFilterList.setBackgroundColor(getContext().getResources().getColor(android.R.color.white));
                }
                if (!ListenerUtil.mutListener.listen(2765)) {
                    nearbyFilterList.setBackgroundColor(getContext().getResources().getColor(android.R.color.white));
                }
            }
        }
    }

    private void initRvNearbyList() {
        if (!ListenerUtil.mutListener.listen(2772)) {
            rvNearbyList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(2773)) {
            adapter = new PlaceAdapter(bookmarkLocationDao, place -> {
                moveCameraToPosition(new GeoPoint(place.location.getLatitude(), place.location.getLongitude()));
                return Unit.INSTANCE;
            }, (place, isBookmarked) -> {
                updateMarker(isBookmarked, place, null);
                mapView.invalidate();
                return Unit.INSTANCE;
            }, commonPlaceClickActions, inAppCameraLocationPermissionLauncher);
        }
        if (!ListenerUtil.mutListener.listen(2774)) {
            rvNearbyList.setAdapter(adapter);
        }
    }

    private void addCheckBoxCallback() {
        if (!ListenerUtil.mutListener.listen(2775)) {
            checkBoxTriStates.setCallback((o, state, b, b1) -> presenter.filterByMarkerType(o, state, b, b1));
        }
    }

    private void performMapReadyActions() {
        if (!ListenerUtil.mutListener.listen(2780)) {
            if (((MainActivity) getActivity()).activeFragment == ActiveFragment.NEARBY) {
                if (!ListenerUtil.mutListener.listen(2779)) {
                    if ((ListenerUtil.mutListener.listen(2776) ? (!applicationKvStore.getBoolean("doNotAskForLocationPermission", false) && PermissionUtils.hasPermission(getActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION })) : (!applicationKvStore.getBoolean("doNotAskForLocationPermission", false) || PermissionUtils.hasPermission(getActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION })))) {
                        if (!ListenerUtil.mutListener.listen(2778)) {
                            checkPermissionsAndPerformAction();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2777)) {
                            isPermissionDenied = true;
                        }
                    }
                }
            }
        }
    }

    private void locationPermissionGranted() {
        if (!ListenerUtil.mutListener.listen(2781)) {
            isPermissionDenied = false;
        }
        if (!ListenerUtil.mutListener.listen(2782)) {
            applicationKvStore.putBoolean("doNotAskForLocationPermission", false);
        }
        if (!ListenerUtil.mutListener.listen(2783)) {
            lastKnownLocation = locationManager.getLastLocation();
        }
        fr.free.nrw.commons.location.LatLng target = lastKnownLocation;
        if (!ListenerUtil.mutListener.listen(2793)) {
            if (lastKnownLocation != null) {
                GeoPoint targetP = new GeoPoint(target.getLatitude(), target.getLongitude());
                if (!ListenerUtil.mutListener.listen(2789)) {
                    mapCenter = targetP;
                }
                if (!ListenerUtil.mutListener.listen(2790)) {
                    mapView.getController().setCenter(targetP);
                }
                if (!ListenerUtil.mutListener.listen(2791)) {
                    recenterMarkerToPosition(targetP);
                }
                if (!ListenerUtil.mutListener.listen(2792)) {
                    moveCameraToPosition(targetP);
                }
            } else if ((ListenerUtil.mutListener.listen(2784) ? (locationManager.isGPSProviderEnabled() && locationManager.isNetworkProviderEnabled()) : (locationManager.isGPSProviderEnabled() || locationManager.isNetworkProviderEnabled()))) {
                if (!ListenerUtil.mutListener.listen(2786)) {
                    locationManager.requestLocationUpdatesFromProvider(LocationManager.NETWORK_PROVIDER);
                }
                if (!ListenerUtil.mutListener.listen(2787)) {
                    locationManager.requestLocationUpdatesFromProvider(LocationManager.GPS_PROVIDER);
                }
                if (!ListenerUtil.mutListener.listen(2788)) {
                    setProgressBarVisibility(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2785)) {
                    Toast.makeText(getContext(), getString(R.string.nearby_location_not_available), Toast.LENGTH_LONG).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2794)) {
            presenter.onMapReady();
        }
        if (!ListenerUtil.mutListener.listen(2795)) {
            registerUnregisterLocationListener(false);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(2796)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(2797)) {
            mapView.onResume();
        }
        if (!ListenerUtil.mutListener.listen(2798)) {
            presenter.attachView(this);
        }
        if (!ListenerUtil.mutListener.listen(2799)) {
            registerNetworkReceiver();
        }
        if (!ListenerUtil.mutListener.listen(2807)) {
            if ((ListenerUtil.mutListener.listen(2800) ? (isResumed() || ((MainActivity) getActivity()).activeFragment == ActiveFragment.NEARBY) : (isResumed() && ((MainActivity) getActivity()).activeFragment == ActiveFragment.NEARBY))) {
                if (!ListenerUtil.mutListener.listen(2806)) {
                    if ((ListenerUtil.mutListener.listen(2801) ? (!isPermissionDenied || !applicationKvStore.getBoolean("doNotAskForLocationPermission", false)) : (!isPermissionDenied && !applicationKvStore.getBoolean("doNotAskForLocationPermission", false)))) {
                        if (!ListenerUtil.mutListener.listen(2805)) {
                            if (!locationManager.isGPSProviderEnabled()) {
                                if (!ListenerUtil.mutListener.listen(2804)) {
                                    startMapWithCondition("Without GPS");
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2803)) {
                                    startTheMap();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2802)) {
                            startMapWithCondition("Without Permission");
                        }
                    }
                }
            }
        }
    }

    /**
     * Starts the map without GPS and without permission By default it points to 51.50550,-0.07520
     * coordinates, other than that it points to the last known location which can be get by the key
     * "LastLocation" from applicationKvStore
     *
     * @param condition : for which condition the map should start
     */
    private void startMapWithCondition(final String condition) {
        if (!ListenerUtil.mutListener.listen(2809)) {
            if (condition.equals("Without Permission")) {
                if (!ListenerUtil.mutListener.listen(2808)) {
                    applicationKvStore.putBoolean("doNotAskForLocationPermission", true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2812)) {
            if (applicationKvStore.getString("LastLocation") != null) {
                final String[] locationLatLng = applicationKvStore.getString("LastLocation").split(",");
                if (!ListenerUtil.mutListener.listen(2811)) {
                    lastKnownLocation = new fr.free.nrw.commons.location.LatLng(Double.parseDouble(locationLatLng[0]), Double.parseDouble(locationLatLng[1]), 1f);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2810)) {
                    lastKnownLocation = new fr.free.nrw.commons.location.LatLng(51.50550, -0.07520, 1f);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2814)) {
            if (mapView != null) {
                if (!ListenerUtil.mutListener.listen(2813)) {
                    recenterMap(lastKnownLocation);
                }
            }
        }
    }

    private void registerNetworkReceiver() {
        if (!ListenerUtil.mutListener.listen(2816)) {
            if (getActivity() != null) {
                if (!ListenerUtil.mutListener.listen(2815)) {
                    getActivity().registerReceiver(broadcastReceiver, intentFilter);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(2817)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(2818)) {
            mapView.onPause();
        }
        if (!ListenerUtil.mutListener.listen(2819)) {
            compositeDisposable.clear();
        }
        if (!ListenerUtil.mutListener.listen(2820)) {
            presenter.detachView();
        }
        if (!ListenerUtil.mutListener.listen(2821)) {
            registerUnregisterLocationListener(true);
        }
        try {
            if (!ListenerUtil.mutListener.listen(2825)) {
                if ((ListenerUtil.mutListener.listen(2823) ? (broadcastReceiver != null || getActivity() != null) : (broadcastReceiver != null && getActivity() != null))) {
                    if (!ListenerUtil.mutListener.listen(2824)) {
                        getContext().unregisterReceiver(broadcastReceiver);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2829)) {
                if ((ListenerUtil.mutListener.listen(2826) ? (locationManager != null || presenter != null) : (locationManager != null && presenter != null))) {
                    if (!ListenerUtil.mutListener.listen(2827)) {
                        locationManager.removeLocationListener(presenter);
                    }
                    if (!ListenerUtil.mutListener.listen(2828)) {
                        locationManager.unregisterLocationManager();
                    }
                }
            }
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(2822)) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void onDestroyView() {
        if (!ListenerUtil.mutListener.listen(2830)) {
            super.onDestroyView();
        }
        if (!ListenerUtil.mutListener.listen(2831)) {
            presenter.removeNearbyPreferences(applicationKvStore);
        }
    }

    private void initViews() {
        if (!ListenerUtil.mutListener.listen(2832)) {
            Timber.d("init views called");
        }
        if (!ListenerUtil.mutListener.listen(2833)) {
            initBottomSheets();
        }
        if (!ListenerUtil.mutListener.listen(2834)) {
            loadAnimations();
        }
        if (!ListenerUtil.mutListener.listen(2835)) {
            setBottomSheetCallbacks();
        }
        if (!ListenerUtil.mutListener.listen(2836)) {
            decideButtonVisibilities();
        }
        if (!ListenerUtil.mutListener.listen(2837)) {
            addActionToTitle();
        }
        if (!ListenerUtil.mutListener.listen(2839)) {
            if (!Utils.isMonumentsEnabled(new Date())) {
                if (!ListenerUtil.mutListener.listen(2838)) {
                    chipWlm.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * a) Creates bottom sheet behaviours from bottom sheets, sets initial states and visibility b)
     * Gets the touch event on the map to perform following actions: if fab is open then close fab.
     * if bottom sheet details are expanded then collapse bottom sheet details. if bottom sheet
     * details are collapsed then hide the bottom sheet details. if listBottomSheet is open then
     * hide the list bottom sheet.
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initBottomSheets() {
        if (!ListenerUtil.mutListener.listen(2840)) {
            bottomSheetListBehavior = BottomSheetBehavior.from(rlBottomSheet);
        }
        if (!ListenerUtil.mutListener.listen(2841)) {
            bottomSheetDetailsBehavior = BottomSheetBehavior.from(bottomSheetDetails);
        }
        if (!ListenerUtil.mutListener.listen(2842)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(2843)) {
            bottomSheetDetails.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(2844)) {
            bottomSheetListBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    public void initNearbyFilter() {
        if (!ListenerUtil.mutListener.listen(2845)) {
            nearbyFilterList.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2846)) {
            hideBottomSheet();
        }
        if (!ListenerUtil.mutListener.listen(2847)) {
            searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
                LayoutUtils.setLayoutHeightAllignedToWidth(1.25, nearbyFilterList);
                if (hasFocus) {
                    nearbyFilterList.setVisibility(View.VISIBLE);
                    presenter.searchViewGainedFocus();
                } else {
                    nearbyFilterList.setVisibility(View.GONE);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2848)) {
            recyclerView.setHasFixedSize(true);
        }
        if (!ListenerUtil.mutListener.listen(2849)) {
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        if (!ListenerUtil.mutListener.listen(2850)) {
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        }
        if (!ListenerUtil.mutListener.listen(2851)) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        if (!ListenerUtil.mutListener.listen(2852)) {
            nearbyFilterSearchRecyclerViewAdapter = new NearbyFilterSearchRecyclerViewAdapter(getContext(), new ArrayList<>(Label.valuesAsList()), recyclerView);
        }
        if (!ListenerUtil.mutListener.listen(2855)) {
            nearbyFilterSearchRecyclerViewAdapter.setCallback(new NearbyFilterSearchRecyclerViewAdapter.Callback() {

                @Override
                public void setCheckboxUnknown() {
                    if (!ListenerUtil.mutListener.listen(2853)) {
                        presenter.setCheckboxUnknown();
                    }
                }

                @Override
                public void filterByMarkerType(final ArrayList<Label> selectedLabels, final int i, final boolean b, final boolean b1) {
                    if (!ListenerUtil.mutListener.listen(2854)) {
                        presenter.filterByMarkerType(selectedLabels, i, b, b1);
                    }
                }

                @Override
                public boolean isDarkTheme() {
                    return isDarkTheme;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2856)) {
            nearbyFilterList.getLayoutParams().width = (int) LayoutUtils.getScreenWidth(getActivity(), 0.75);
        }
        if (!ListenerUtil.mutListener.listen(2857)) {
            recyclerView.setAdapter(nearbyFilterSearchRecyclerViewAdapter);
        }
        if (!ListenerUtil.mutListener.listen(2858)) {
            LayoutUtils.setLayoutHeightAllignedToWidth(1.25, nearbyFilterList);
        }
        if (!ListenerUtil.mutListener.listen(2859)) {
            compositeDisposable.add(RxSearchView.queryTextChanges(searchView).takeUntil(RxView.detaches(searchView)).debounce(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(query -> {
                ((NearbyFilterSearchRecyclerViewAdapter) recyclerView.getAdapter()).getFilter().filter(query.toString());
            }));
        }
        if (!ListenerUtil.mutListener.listen(2860)) {
            initFilterChips();
        }
    }

    @Override
    public void setCheckBoxAction() {
        if (!ListenerUtil.mutListener.listen(2861)) {
            checkBoxTriStates.addAction();
        }
        if (!ListenerUtil.mutListener.listen(2862)) {
            checkBoxTriStates.setState(CheckBoxTriStates.UNKNOWN);
        }
    }

    @Override
    public void setCheckBoxState(final int state) {
        if (!ListenerUtil.mutListener.listen(2863)) {
            checkBoxTriStates.setState(state);
        }
    }

    @Override
    public void setFilterState() {
        if (!ListenerUtil.mutListener.listen(2864)) {
            chipNeedsPhoto.setChecked(NearbyFilterState.getInstance().isNeedPhotoSelected());
        }
        if (!ListenerUtil.mutListener.listen(2865)) {
            chipExists.setChecked(NearbyFilterState.getInstance().isExistsSelected());
        }
        if (!ListenerUtil.mutListener.listen(2866)) {
            chipWlm.setChecked(NearbyFilterState.getInstance().isWlmSelected());
        }
        if (!ListenerUtil.mutListener.listen(2868)) {
            if (NearbyController.currentLocation != null) {
                if (!ListenerUtil.mutListener.listen(2867)) {
                    presenter.filterByMarkerType(nearbyFilterSearchRecyclerViewAdapter.selectedLabels, checkBoxTriStates.getState(), true, false);
                }
            }
        }
    }

    private void initFilterChips() {
        if (!ListenerUtil.mutListener.listen(2869)) {
            chipNeedsPhoto.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (NearbyController.currentLocation != null) {
                    checkBoxTriStates.setState(CheckBoxTriStates.CHECKED);
                    NearbyFilterState.setNeedPhotoSelected(isChecked);
                    presenter.filterByMarkerType(nearbyFilterSearchRecyclerViewAdapter.selectedLabels, checkBoxTriStates.getState(), true, true);
                    updatePlaceList(chipNeedsPhoto.isChecked(), chipExists.isChecked(), chipWlm.isChecked());
                } else {
                    chipNeedsPhoto.setChecked(!isChecked);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2870)) {
            chipExists.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (NearbyController.currentLocation != null) {
                    checkBoxTriStates.setState(CheckBoxTriStates.CHECKED);
                    NearbyFilterState.setExistsSelected(isChecked);
                    presenter.filterByMarkerType(nearbyFilterSearchRecyclerViewAdapter.selectedLabels, checkBoxTriStates.getState(), true, true);
                    updatePlaceList(chipNeedsPhoto.isChecked(), chipExists.isChecked(), chipWlm.isChecked());
                } else {
                    chipExists.setChecked(!isChecked);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2871)) {
            chipWlm.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (NearbyController.currentLocation != null) {
                    checkBoxTriStates.setState(CheckBoxTriStates.CHECKED);
                    NearbyFilterState.setWlmSelected(isChecked);
                    presenter.filterByMarkerType(nearbyFilterSearchRecyclerViewAdapter.selectedLabels, checkBoxTriStates.getState(), true, true);
                    updatePlaceList(chipNeedsPhoto.isChecked(), chipExists.isChecked(), chipWlm.isChecked());
                } else {
                    chipWlm.setChecked(!isChecked);
                }
            });
        }
    }

    /**
     * Updates Nearby place list according to available chip states
     *
     * @param needsPhoto is chipNeedsPhoto checked
     * @param exists     is chipExists checked
     * @param isWlm      is chipWlm checked
     */
    private void updatePlaceList(final boolean needsPhoto, final boolean exists, final boolean isWlm) {
        final List<Place> updatedPlaces = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(2877)) {
            if (needsPhoto) {
                if (!ListenerUtil.mutListener.listen(2876)) {
                    {
                        long _loopCounter39 = 0;
                        for (final Place place : places) {
                            ListenerUtil.loopListener.listen("_loopCounter39", ++_loopCounter39);
                            if (!ListenerUtil.mutListener.listen(2875)) {
                                if ((ListenerUtil.mutListener.listen(2873) ? (place.pic.trim().isEmpty() || !updatedPlaces.contains(place)) : (place.pic.trim().isEmpty() && !updatedPlaces.contains(place)))) {
                                    if (!ListenerUtil.mutListener.listen(2874)) {
                                        updatedPlaces.add(place);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2872)) {
                    updatedPlaces.addAll(places);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2881)) {
            if (exists) {
                if (!ListenerUtil.mutListener.listen(2880)) {
                    {
                        long _loopCounter40 = 0;
                        for (final Iterator<Place> placeIterator = updatedPlaces.iterator(); placeIterator.hasNext(); ) {
                            ListenerUtil.loopListener.listen("_loopCounter40", ++_loopCounter40);
                            final Place place = placeIterator.next();
                            if (!ListenerUtil.mutListener.listen(2879)) {
                                if (!place.exists) {
                                    if (!ListenerUtil.mutListener.listen(2878)) {
                                        placeIterator.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2890)) {
            if (!isWlm) {
                if (!ListenerUtil.mutListener.listen(2889)) {
                    {
                        long _loopCounter42 = 0;
                        for (final Place place : places) {
                            ListenerUtil.loopListener.listen("_loopCounter42", ++_loopCounter42);
                            if (!ListenerUtil.mutListener.listen(2888)) {
                                if ((ListenerUtil.mutListener.listen(2886) ? (place.isMonument() || updatedPlaces.contains(place)) : (place.isMonument() && updatedPlaces.contains(place)))) {
                                    if (!ListenerUtil.mutListener.listen(2887)) {
                                        updatedPlaces.remove(place);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2885)) {
                    {
                        long _loopCounter41 = 0;
                        for (final Place place : places) {
                            ListenerUtil.loopListener.listen("_loopCounter41", ++_loopCounter41);
                            if (!ListenerUtil.mutListener.listen(2884)) {
                                if ((ListenerUtil.mutListener.listen(2882) ? (place.isMonument() || !updatedPlaces.contains(place)) : (place.isMonument() && !updatedPlaces.contains(place)))) {
                                    if (!ListenerUtil.mutListener.listen(2883)) {
                                        updatedPlaces.add(place);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2891)) {
            adapter.setItems(updatedPlaces);
        }
        if (!ListenerUtil.mutListener.listen(2892)) {
            noResultsView.setVisibility(updatedPlaces.isEmpty() ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Defines how bottom sheets will act on click
     */
    private void setBottomSheetCallbacks() {
        if (!ListenerUtil.mutListener.listen(2894)) {
            bottomSheetDetailsBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull final View bottomSheet, final int newState) {
                    if (!ListenerUtil.mutListener.listen(2893)) {
                        prepareViewsForSheetPosition(newState);
                    }
                }

                @Override
                public void onSlide(@NonNull final View bottomSheet, final float slideOffset) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2895)) {
            bottomSheetDetails.setOnClickListener(v -> {
                if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2904)) {
            rlBottomSheet.getLayoutParams().height = (ListenerUtil.mutListener.listen(2903) ? ((ListenerUtil.mutListener.listen(2899) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(2898) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(2897) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(2896) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) % 9) : (ListenerUtil.mutListener.listen(2902) ? ((ListenerUtil.mutListener.listen(2899) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(2898) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(2897) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(2896) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) / 9) : (ListenerUtil.mutListener.listen(2901) ? ((ListenerUtil.mutListener.listen(2899) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(2898) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(2897) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(2896) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) - 9) : (ListenerUtil.mutListener.listen(2900) ? ((ListenerUtil.mutListener.listen(2899) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(2898) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(2897) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(2896) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) + 9) : ((ListenerUtil.mutListener.listen(2899) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(2898) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(2897) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(2896) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) * 9)))));
        }
        if (!ListenerUtil.mutListener.listen(2905)) {
            bottomSheetListBehavior = BottomSheetBehavior.from(rlBottomSheet);
        }
        if (!ListenerUtil.mutListener.listen(2906)) {
            bottomSheetListBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        if (!ListenerUtil.mutListener.listen(2909)) {
            bottomSheetListBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull final View bottomSheet, final int newState) {
                    if (!ListenerUtil.mutListener.listen(2908)) {
                        if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                            if (!ListenerUtil.mutListener.listen(2907)) {
                                bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            }
                        }
                    }
                }

                @Override
                public void onSlide(@NonNull final View bottomSheet, final float slideOffset) {
                }
            });
        }
    }

    /**
     * Loads animations will be used for FABs
     */
    private void loadAnimations() {
        if (!ListenerUtil.mutListener.listen(2910)) {
            fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        }
        if (!ListenerUtil.mutListener.listen(2911)) {
            fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
        }
        if (!ListenerUtil.mutListener.listen(2912)) {
            rotate_forward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_forward);
        }
        if (!ListenerUtil.mutListener.listen(2913)) {
            rotate_backward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_backward);
        }
    }

    /**
     * Fits buttons according to our layout
     */
    private void decideButtonVisibilities() {
        if (!ListenerUtil.mutListener.listen(2924)) {
            // Only need to check for directions button because it is the longest
            if ((ListenerUtil.mutListener.listen(2919) ? ((ListenerUtil.mutListener.listen(2918) ? (directionsButtonText.getLineCount() >= 1) : (ListenerUtil.mutListener.listen(2917) ? (directionsButtonText.getLineCount() <= 1) : (ListenerUtil.mutListener.listen(2916) ? (directionsButtonText.getLineCount() < 1) : (ListenerUtil.mutListener.listen(2915) ? (directionsButtonText.getLineCount() != 1) : (ListenerUtil.mutListener.listen(2914) ? (directionsButtonText.getLineCount() == 1) : (directionsButtonText.getLineCount() > 1)))))) && directionsButtonText.getLineCount() == 0) : ((ListenerUtil.mutListener.listen(2918) ? (directionsButtonText.getLineCount() >= 1) : (ListenerUtil.mutListener.listen(2917) ? (directionsButtonText.getLineCount() <= 1) : (ListenerUtil.mutListener.listen(2916) ? (directionsButtonText.getLineCount() < 1) : (ListenerUtil.mutListener.listen(2915) ? (directionsButtonText.getLineCount() != 1) : (ListenerUtil.mutListener.listen(2914) ? (directionsButtonText.getLineCount() == 1) : (directionsButtonText.getLineCount() > 1)))))) || directionsButtonText.getLineCount() == 0))) {
                if (!ListenerUtil.mutListener.listen(2920)) {
                    wikipediaButtonText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(2921)) {
                    wikidataButtonText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(2922)) {
                    commonsButtonText.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(2923)) {
                    directionsButtonText.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     */
    private void addActionToTitle() {
        if (!ListenerUtil.mutListener.listen(2925)) {
            title.setOnLongClickListener(view -> {
                Utils.copy("place", title.getText().toString(), getContext());
                Toast.makeText(getContext(), R.string.text_copy, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(2926)) {
            title.setOnClickListener(view -> {
                bottomSheetListBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });
        }
    }

    /**
     * Centers the map in nearby fragment to a given place and updates nearestPlace
     *
     * @param place is new center of the map
     */
    @Override
    public void centerMapToPlace(@Nullable final Place place) {
        if (!ListenerUtil.mutListener.listen(2927)) {
            Timber.d("Map is centered to place");
        }
        final double cameraShift;
        if (!ListenerUtil.mutListener.listen(2930)) {
            if (null != place) {
                if (!ListenerUtil.mutListener.listen(2928)) {
                    lastPlaceToCenter = place;
                }
                if (!ListenerUtil.mutListener.listen(2929)) {
                    nearestPlace = place;
                }
            }
        }
        if (null != lastPlaceToCenter) {
            final Configuration configuration = getActivity().getResources().getConfiguration();
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                cameraShift = CAMERA_TARGET_SHIFT_FACTOR_PORTRAIT;
            } else {
                cameraShift = CAMERA_TARGET_SHIFT_FACTOR_LANDSCAPE;
            }
            if (!ListenerUtil.mutListener.listen(2935)) {
                recenterMap(new fr.free.nrw.commons.location.LatLng((ListenerUtil.mutListener.listen(2934) ? (lastPlaceToCenter.location.getLatitude() % cameraShift) : (ListenerUtil.mutListener.listen(2933) ? (lastPlaceToCenter.location.getLatitude() / cameraShift) : (ListenerUtil.mutListener.listen(2932) ? (lastPlaceToCenter.location.getLatitude() * cameraShift) : (ListenerUtil.mutListener.listen(2931) ? (lastPlaceToCenter.location.getLatitude() + cameraShift) : (lastPlaceToCenter.location.getLatitude() - cameraShift))))), lastPlaceToCenter.getLocation().getLongitude(), 0));
            }
        }
    }

    @Override
    public void updateListFragment(final List<Place> placeList) {
        if (!ListenerUtil.mutListener.listen(2936)) {
            places = placeList;
        }
        if (!ListenerUtil.mutListener.listen(2937)) {
            adapter.setItems(placeList);
        }
        if (!ListenerUtil.mutListener.listen(2938)) {
            noResultsView.setVisibility(placeList.isEmpty() ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public fr.free.nrw.commons.location.LatLng getLastLocation() {
        return lastKnownLocation;
    }

    @Override
    public fr.free.nrw.commons.location.LatLng getLastMapFocus() {
        fr.free.nrw.commons.location.LatLng latLng = new fr.free.nrw.commons.location.LatLng(lastMapFocus.getLatitude(), lastMapFocus.getLongitude(), 100);
        return latLng;
    }

    /**
     * Computes location where map should be centered
     *
     * @return returns the last location, if available, else returns default location
     */
    @Override
    public fr.free.nrw.commons.location.LatLng getMapCenter() {
        if (!ListenerUtil.mutListener.listen(2941)) {
            if (applicationKvStore.getString("LastLocation") != null) {
                final String[] locationLatLng = applicationKvStore.getString("LastLocation").split(",");
                if (!ListenerUtil.mutListener.listen(2940)) {
                    lastKnownLocation = new fr.free.nrw.commons.location.LatLng(Double.parseDouble(locationLatLng[0]), Double.parseDouble(locationLatLng[1]), 1f);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2939)) {
                    lastKnownLocation = new fr.free.nrw.commons.location.LatLng(51.50550, -0.07520, 1f);
                }
            }
        }
        fr.free.nrw.commons.location.LatLng latLnge = lastKnownLocation;
        if (!ListenerUtil.mutListener.listen(2943)) {
            if (mapCenter != null) {
                if (!ListenerUtil.mutListener.listen(2942)) {
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
    public LatLng getLastFocusLocation() {
        return lastFocusLocation == null ? null : LocationUtils.commonsLatLngToMapBoxLatLng(lastFocusLocation);
    }

    @Override
    public boolean isCurrentLocationMarkerVisible() {
        if ((ListenerUtil.mutListener.listen(2944) ? (latLngBounds == null && currentLocationMarker == null) : (latLngBounds == null || currentLocationMarker == null))) {
            if (!ListenerUtil.mutListener.listen(2945)) {
                Timber.d("Map projection bounds are null");
            }
            return false;
        } else {
            return latLngBounds.contains(currentLocationMarker.getPosition());
        }
    }

    @Override
    public boolean isAdvancedQueryFragmentVisible() {
        return isAdvancedQueryFragmentVisible;
    }

    @Override
    public void showHideAdvancedQueryFragment(final boolean shouldShow) {
        if (!ListenerUtil.mutListener.listen(2946)) {
            setHasOptionsMenu(!shouldShow);
        }
        if (!ListenerUtil.mutListener.listen(2947)) {
            flConainerNearbyChildren.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(2948)) {
            isAdvancedQueryFragmentVisible = shouldShow;
        }
    }

    @Override
    public void centerMapToPosition(fr.free.nrw.commons.location.LatLng searchLatLng) {
        if (!ListenerUtil.mutListener.listen(2952)) {
            if ((ListenerUtil.mutListener.listen(2950) ? (null != searchLatLng || !((ListenerUtil.mutListener.listen(2949) ? (mapView.getMapCenter().getLatitude() == searchLatLng.getLatitude() || mapView.getMapCenter().getLongitude() == searchLatLng.getLongitude()) : (mapView.getMapCenter().getLatitude() == searchLatLng.getLatitude() && mapView.getMapCenter().getLongitude() == searchLatLng.getLongitude())))) : (null != searchLatLng && !((ListenerUtil.mutListener.listen(2949) ? (mapView.getMapCenter().getLatitude() == searchLatLng.getLatitude() || mapView.getMapCenter().getLongitude() == searchLatLng.getLongitude()) : (mapView.getMapCenter().getLatitude() == searchLatLng.getLatitude() && mapView.getMapCenter().getLongitude() == searchLatLng.getLongitude())))))) {
                if (!ListenerUtil.mutListener.listen(2951)) {
                    recenterMarkerToPosition(new GeoPoint(searchLatLng.getLatitude(), searchLatLng.getLongitude()));
                }
            }
        }
    }

    @Override
    public boolean isNetworkConnectionEstablished() {
        return NetworkUtils.isInternetConnectionEstablished(getActivity());
    }

    /**
     * Adds network broadcast receiver to recognize connection established
     */
    private void initNetworkBroadCastReceiver() {
        if (!ListenerUtil.mutListener.listen(2967)) {
            broadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(final Context context, final Intent intent) {
                    if (!ListenerUtil.mutListener.listen(2966)) {
                        if (getActivity() != null) {
                            if (!ListenerUtil.mutListener.listen(2965)) {
                                if (NetworkUtils.isInternetConnectionEstablished(getActivity())) {
                                    if (!ListenerUtil.mutListener.listen(2961)) {
                                        if (isNetworkErrorOccurred) {
                                            if (!ListenerUtil.mutListener.listen(2959)) {
                                                presenter.updateMapAndList(LOCATION_SIGNIFICANTLY_CHANGED);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2960)) {
                                                isNetworkErrorOccurred = false;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2964)) {
                                        if (snackbar != null) {
                                            if (!ListenerUtil.mutListener.listen(2962)) {
                                                snackbar.dismiss();
                                            }
                                            if (!ListenerUtil.mutListener.listen(2963)) {
                                                snackbar = null;
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(2956)) {
                                        if (snackbar == null) {
                                            if (!ListenerUtil.mutListener.listen(2953)) {
                                                snackbar = Snackbar.make(view, R.string.no_internet, Snackbar.LENGTH_INDEFINITE);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2954)) {
                                                setSearchThisAreaButtonVisibility(false);
                                            }
                                            if (!ListenerUtil.mutListener.listen(2955)) {
                                                setProgressBarVisibility(false);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2957)) {
                                        isNetworkErrorOccurred = true;
                                    }
                                    if (!ListenerUtil.mutListener.listen(2958)) {
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

    /**
     * Hide or expand bottom sheet according to states of all sheets
     */
    @Override
    public void listOptionMenuItemClicked() {
        if (!ListenerUtil.mutListener.listen(2968)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(2972)) {
            if ((ListenerUtil.mutListener.listen(2969) ? (bottomSheetListBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED && bottomSheetListBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) : (bottomSheetListBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED || bottomSheetListBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN))) {
                if (!ListenerUtil.mutListener.listen(2971)) {
                    bottomSheetListBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            } else if (bottomSheetListBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                if (!ListenerUtil.mutListener.listen(2970)) {
                    bottomSheetListBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        }
    }

    @Override
    public void populatePlaces(final fr.free.nrw.commons.location.LatLng curlatLng) {
        if (!ListenerUtil.mutListener.listen(2975)) {
            if (curlatLng.equals(getLastMapFocus())) {
                if (!ListenerUtil.mutListener.listen(2974)) {
                    // Means we are checking around current location
                    populatePlacesForCurrentLocation(getLastMapFocus(), curlatLng, null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2973)) {
                    populatePlacesForAnotherLocation(getLastMapFocus(), curlatLng, null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2977)) {
            if (recenterToUserLocation) {
                if (!ListenerUtil.mutListener.listen(2976)) {
                    recenterToUserLocation = false;
                }
            }
        }
    }

    @Override
    public void populatePlaces(final fr.free.nrw.commons.location.LatLng curlatLng, @Nullable final String customQuery) {
        if (!ListenerUtil.mutListener.listen(2980)) {
            if ((ListenerUtil.mutListener.listen(2978) ? (customQuery == null && customQuery.isEmpty()) : (customQuery == null || customQuery.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(2979)) {
                    populatePlaces(curlatLng);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2985)) {
            if ((ListenerUtil.mutListener.listen(2982) ? ((ListenerUtil.mutListener.listen(2981) ? (curlatLng.equals(lastFocusLocation) && lastFocusLocation == null) : (curlatLng.equals(lastFocusLocation) || lastFocusLocation == null)) && recenterToUserLocation) : ((ListenerUtil.mutListener.listen(2981) ? (curlatLng.equals(lastFocusLocation) && lastFocusLocation == null) : (curlatLng.equals(lastFocusLocation) || lastFocusLocation == null)) || recenterToUserLocation))) {
                if (!ListenerUtil.mutListener.listen(2984)) {
                    // Means we are checking around current location
                    populatePlacesForCurrentLocation(lastKnownLocation, curlatLng, customQuery);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2983)) {
                    populatePlacesForAnotherLocation(lastKnownLocation, curlatLng, customQuery);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2987)) {
            if (recenterToUserLocation) {
                if (!ListenerUtil.mutListener.listen(2986)) {
                    recenterToUserLocation = false;
                }
            }
        }
    }

    private void populatePlacesForCurrentLocation(final fr.free.nrw.commons.location.LatLng curlatLng, final fr.free.nrw.commons.location.LatLng searchLatLng, @Nullable final String customQuery) {
        final Observable<NearbyController.NearbyPlacesInfo> nearbyPlacesInfoObservable = Observable.fromCallable(() -> nearbyController.loadAttractionsFromLocation(curlatLng, searchLatLng, false, true, Utils.isMonumentsEnabled(new Date()), customQuery));
        if (!ListenerUtil.mutListener.listen(2988)) {
            compositeDisposable.add(nearbyPlacesInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(nearbyPlacesInfo -> {
                if (nearbyPlacesInfo.placeList == null || nearbyPlacesInfo.placeList.isEmpty()) {
                    showErrorMessage(getString(R.string.no_nearby_places_around));
                } else {
                    updateMapMarkers(nearbyPlacesInfo, true);
                    lastFocusLocation = searchLatLng;
                    lastMapFocus = new GeoPoint(searchLatLng.getLatitude(), searchLatLng.getLongitude());
                }
            }, throwable -> {
                Timber.d(throwable);
                showErrorMessage(getString(R.string.error_fetching_nearby_places) + throwable.getLocalizedMessage());
                setProgressBarVisibility(false);
                presenter.lockUnlockNearby(false);
                setFilterState();
            }));
        }
    }

    private void populatePlacesForAnotherLocation(final fr.free.nrw.commons.location.LatLng curlatLng, final fr.free.nrw.commons.location.LatLng searchLatLng, @Nullable final String customQuery) {
        final Observable<NearbyPlacesInfo> nearbyPlacesInfoObservable = Observable.fromCallable(() -> nearbyController.loadAttractionsFromLocation(curlatLng, searchLatLng, false, true, Utils.isMonumentsEnabled(new Date()), customQuery));
        if (!ListenerUtil.mutListener.listen(2989)) {
            compositeDisposable.add(nearbyPlacesInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(nearbyPlacesInfo -> {
                if (nearbyPlacesInfo.placeList == null || nearbyPlacesInfo.placeList.isEmpty()) {
                    showErrorMessage(getString(R.string.no_nearby_places_around));
                } else {
                    // Updating last searched location
                    applicationKvStore.putString("LastLocation", searchLatLng.getLatitude() + "," + searchLatLng.getLongitude());
                    updateMapMarkers(nearbyPlacesInfo, false);
                    lastMapFocus = new GeoPoint(searchLatLng.getLatitude(), searchLatLng.getLongitude());
                }
            }, throwable -> {
                Timber.e(throwable);
                showErrorMessage(getString(R.string.error_fetching_nearby_places) + throwable.getLocalizedMessage());
                setProgressBarVisibility(false);
                presenter.lockUnlockNearby(false);
                setFilterState();
            }));
        }
    }

    /**
     * Populates places for your location, should be used for finding nearby places around a
     * location where you are.
     *
     * @param nearbyPlacesInfo This variable has place list information and distances.
     */
    private void updateMapMarkers(final NearbyController.NearbyPlacesInfo nearbyPlacesInfo, final boolean shouldUpdateSelectedMarker) {
        if (!ListenerUtil.mutListener.listen(2990)) {
            presenter.updateMapMarkers(nearbyPlacesInfo, selectedMarker, shouldUpdateSelectedMarker);
        }
        if (!ListenerUtil.mutListener.listen(2991)) {
            // TODO
            setFilterState();
        }
    }

    @Override
    public boolean isListBottomSheetExpanded() {
        return bottomSheetListBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED;
    }

    @Override
    public boolean isDetailsBottomSheetVisible() {
        return !(bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void setBottomSheetDetailsSmaller() {
        if (!ListenerUtil.mutListener.listen(2994)) {
            if (bottomSheetDetailsBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                if (!ListenerUtil.mutListener.listen(2993)) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2992)) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        }
    }

    @Override
    public void addSearchThisAreaButtonAction() {
        if (!ListenerUtil.mutListener.listen(2995)) {
            searchThisAreaButton.setOnClickListener(presenter.onSearchThisAreaClicked());
        }
    }

    @Override
    public void setSearchThisAreaButtonVisibility(final boolean isVisible) {
        if (!ListenerUtil.mutListener.listen(2998)) {
            if (isVisible) {
                if (!ListenerUtil.mutListener.listen(2997)) {
                    searchThisAreaButton.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2996)) {
                    searchThisAreaButton.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void setRecyclerViewAdapterAllSelected() {
        if (!ListenerUtil.mutListener.listen(3001)) {
            if ((ListenerUtil.mutListener.listen(2999) ? (nearbyFilterSearchRecyclerViewAdapter != null || NearbyController.currentLocation != null) : (nearbyFilterSearchRecyclerViewAdapter != null && NearbyController.currentLocation != null))) {
                if (!ListenerUtil.mutListener.listen(3000)) {
                    nearbyFilterSearchRecyclerViewAdapter.setRecyclerViewAdapterAllSelected();
                }
            }
        }
    }

    @Override
    public void setRecyclerViewAdapterItemsGreyedOut() {
        if (!ListenerUtil.mutListener.listen(3004)) {
            if ((ListenerUtil.mutListener.listen(3002) ? (nearbyFilterSearchRecyclerViewAdapter != null || NearbyController.currentLocation != null) : (nearbyFilterSearchRecyclerViewAdapter != null && NearbyController.currentLocation != null))) {
                if (!ListenerUtil.mutListener.listen(3003)) {
                    nearbyFilterSearchRecyclerViewAdapter.setRecyclerViewAdapterItemsGreyedOut();
                }
            }
        }
    }

    @Override
    public void setProgressBarVisibility(final boolean isVisible) {
        if (!ListenerUtil.mutListener.listen(3007)) {
            if (isVisible) {
                if (!ListenerUtil.mutListener.listen(3006)) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3005)) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void setTabItemContributions() {
        if (!ListenerUtil.mutListener.listen(3008)) {
            ((MainActivity) getActivity()).viewPager.setCurrentItem(0);
        }
    }

    @Override
    public void checkPermissionsAndPerformAction() {
        if (!ListenerUtil.mutListener.listen(3009)) {
            Timber.d("Checking permission and perfoming action");
        }
        if (!ListenerUtil.mutListener.listen(3010)) {
            locationPermissionLauncher.launch(permission.ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Starts animation of fab plus (turning on opening) and other FABs
     */
    @Override
    public void animateFABs() {
        if (!ListenerUtil.mutListener.listen(3014)) {
            if (fabPlus.isShown()) {
                if (!ListenerUtil.mutListener.listen(3013)) {
                    if (isFABsExpanded) {
                        if (!ListenerUtil.mutListener.listen(3012)) {
                            collapseFABs(isFABsExpanded);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3011)) {
                            expandFABs(isFABsExpanded);
                        }
                    }
                }
            }
        }
    }

    private void showFABs() {
        if (!ListenerUtil.mutListener.listen(3015)) {
            NearbyFABUtils.addAnchorToBigFABs(fabPlus, bottomSheetDetails.getId());
        }
        if (!ListenerUtil.mutListener.listen(3016)) {
            fabPlus.show();
        }
        if (!ListenerUtil.mutListener.listen(3017)) {
            NearbyFABUtils.addAnchorToSmallFABs(fabGallery, getView().findViewById(R.id.empty_view).getId());
        }
        if (!ListenerUtil.mutListener.listen(3018)) {
            NearbyFABUtils.addAnchorToSmallFABs(fabCamera, getView().findViewById(R.id.empty_view1).getId());
        }
    }

    /**
     * Expands camera and gallery FABs, turn forward plus FAB
     *
     * @param isFABsExpanded true if they are already expanded
     */
    private void expandFABs(final boolean isFABsExpanded) {
        if (!ListenerUtil.mutListener.listen(3026)) {
            if (!isFABsExpanded) {
                if (!ListenerUtil.mutListener.listen(3019)) {
                    showFABs();
                }
                if (!ListenerUtil.mutListener.listen(3020)) {
                    fabPlus.startAnimation(rotate_forward);
                }
                if (!ListenerUtil.mutListener.listen(3021)) {
                    fabCamera.startAnimation(fab_open);
                }
                if (!ListenerUtil.mutListener.listen(3022)) {
                    fabGallery.startAnimation(fab_open);
                }
                if (!ListenerUtil.mutListener.listen(3023)) {
                    fabCamera.show();
                }
                if (!ListenerUtil.mutListener.listen(3024)) {
                    fabGallery.show();
                }
                if (!ListenerUtil.mutListener.listen(3025)) {
                    this.isFABsExpanded = true;
                }
            }
        }
    }

    /**
     * Hides all fabs
     */
    private void hideFABs() {
        if (!ListenerUtil.mutListener.listen(3027)) {
            NearbyFABUtils.removeAnchorFromFAB(fabPlus);
        }
        if (!ListenerUtil.mutListener.listen(3028)) {
            fabPlus.hide();
        }
        if (!ListenerUtil.mutListener.listen(3029)) {
            NearbyFABUtils.removeAnchorFromFAB(fabCamera);
        }
        if (!ListenerUtil.mutListener.listen(3030)) {
            fabCamera.hide();
        }
        if (!ListenerUtil.mutListener.listen(3031)) {
            NearbyFABUtils.removeAnchorFromFAB(fabGallery);
        }
        if (!ListenerUtil.mutListener.listen(3032)) {
            fabGallery.hide();
        }
    }

    /**
     * Collapses camera and gallery FABs, turn back plus FAB
     *
     * @param isFABsExpanded
     */
    private void collapseFABs(final boolean isFABsExpanded) {
        if (!ListenerUtil.mutListener.listen(3039)) {
            if (isFABsExpanded) {
                if (!ListenerUtil.mutListener.listen(3033)) {
                    fabPlus.startAnimation(rotate_backward);
                }
                if (!ListenerUtil.mutListener.listen(3034)) {
                    fabCamera.startAnimation(fab_close);
                }
                if (!ListenerUtil.mutListener.listen(3035)) {
                    fabGallery.startAnimation(fab_close);
                }
                if (!ListenerUtil.mutListener.listen(3036)) {
                    fabCamera.hide();
                }
                if (!ListenerUtil.mutListener.listen(3037)) {
                    fabGallery.hide();
                }
                if (!ListenerUtil.mutListener.listen(3038)) {
                    this.isFABsExpanded = false;
                }
            }
        }
    }

    @Override
    public void displayLoginSkippedWarning() {
        if (!ListenerUtil.mutListener.listen(3041)) {
            if (applicationKvStore.getBoolean("login_skipped", false)) {
                if (!ListenerUtil.mutListener.listen(3040)) {
                    // prompt the user to login
                    new AlertDialog.Builder(getContext()).setMessage(R.string.login_alert_message).setPositiveButton(R.string.login, (dialog, which) -> {
                        // logout of the app
                        BaseLogoutListener logoutListener = new BaseLogoutListener();
                        CommonsApplication app = (CommonsApplication) getActivity().getApplication();
                        app.clearApplicationData(getContext(), logoutListener);
                    }).show();
                }
            }
        }
    }

    private void handleLocationUpdate(final fr.free.nrw.commons.location.LatLng latLng, final LocationServiceManager.LocationChangeType locationChangeType) {
        if (!ListenerUtil.mutListener.listen(3042)) {
            lastKnownLocation = latLng;
        }
        if (!ListenerUtil.mutListener.listen(3043)) {
            NearbyController.currentLocation = lastKnownLocation;
        }
        if (!ListenerUtil.mutListener.listen(3044)) {
            presenter.updateMapAndList(locationChangeType);
        }
    }

    @Override
    public void onLocationChangedSignificantly(final fr.free.nrw.commons.location.LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(3045)) {
            Timber.d("Location significantly changed");
        }
        if (!ListenerUtil.mutListener.listen(3047)) {
            if (latLng != null) {
                if (!ListenerUtil.mutListener.listen(3046)) {
                    handleLocationUpdate(latLng, LOCATION_SIGNIFICANTLY_CHANGED);
                }
            }
        }
    }

    @Override
    public void onLocationChangedSlightly(final fr.free.nrw.commons.location.LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(3048)) {
            Timber.d("Location slightly changed");
        }
        if (!ListenerUtil.mutListener.listen(3050)) {
            if (latLng != null) {
                if (!ListenerUtil.mutListener.listen(3049)) {
                    // If the map has never ever shown the current location, lets do it know
                    handleLocationUpdate(latLng, LOCATION_SLIGHTLY_CHANGED);
                }
            }
        }
    }

    @Override
    public void onLocationChangedMedium(final fr.free.nrw.commons.location.LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(3051)) {
            Timber.d("Location changed medium");
        }
        if (!ListenerUtil.mutListener.listen(3053)) {
            if (latLng != null) {
                if (!ListenerUtil.mutListener.listen(3052)) {
                    // If the map has never ever shown the current location, lets do it know
                    handleLocationUpdate(latLng, LOCATION_SIGNIFICANTLY_CHANGED);
                }
            }
        }
    }

    public boolean backButtonClicked() {
        return presenter.backButtonClicked();
    }

    /**
     * onLogoutComplete is called after shared preferences and data stored in local database are
     * cleared.
     */
    private class BaseLogoutListener implements CommonsApplication.LogoutListener {

        @Override
        public void onLogoutComplete() {
            if (!ListenerUtil.mutListener.listen(3054)) {
                Timber.d("Logout complete callback received.");
            }
            final Intent nearbyIntent = new Intent(getActivity(), LoginActivity.class);
            if (!ListenerUtil.mutListener.listen(3055)) {
                nearbyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            if (!ListenerUtil.mutListener.listen(3056)) {
                nearbyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (!ListenerUtil.mutListener.listen(3057)) {
                startActivity(nearbyIntent);
            }
            if (!ListenerUtil.mutListener.listen(3058)) {
                getActivity().finish();
            }
        }
    }

    @Override
    public void setFABPlusAction(final View.OnClickListener onClickListener) {
        if (!ListenerUtil.mutListener.listen(3059)) {
            fabPlus.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void setFABRecenterAction(final View.OnClickListener onClickListener) {
        if (!ListenerUtil.mutListener.listen(3060)) {
            fabRecenter.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void disableFABRecenter() {
        if (!ListenerUtil.mutListener.listen(3061)) {
            fabRecenter.setEnabled(false);
        }
    }

    @Override
    public void enableFABRecenter() {
        if (!ListenerUtil.mutListener.listen(3062)) {
            fabRecenter.setEnabled(true);
        }
    }

    /**
     * Adds a marker for the user's current position. Adds a circle which uses the accuracy * 2, to
     * draw a circle which represents the user's position with an accuracy of 95%.
     * <p>
     * Should be called only on creation of mapboxMap, there is other method to update markers
     * location with users move.
     *
     * @param curLatLng current location
     */
    @Override
    public void addCurrentLocationMarker(final fr.free.nrw.commons.location.LatLng curLatLng) {
        if (!ListenerUtil.mutListener.listen(3067)) {
            if ((ListenerUtil.mutListener.listen(3064) ? ((ListenerUtil.mutListener.listen(3063) ? (null != curLatLng || !isPermissionDenied) : (null != curLatLng && !isPermissionDenied)) || locationManager.isGPSProviderEnabled()) : ((ListenerUtil.mutListener.listen(3063) ? (null != curLatLng || !isPermissionDenied) : (null != curLatLng && !isPermissionDenied)) && locationManager.isGPSProviderEnabled()))) {
                if (!ListenerUtil.mutListener.listen(3066)) {
                    ExecutorUtils.get().submit(() -> {
                        Timber.d("Adds current location marker");
                        recenterMarkerToPosition(new GeoPoint(curLatLng.getLatitude(), curLatLng.getLongitude()));
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3065)) {
                    Timber.d("not adding current location marker..current location is null");
                }
            }
        }
    }

    /**
     * Makes map camera follow users location with animation
     *
     * @param curLatLng current location of user
     */
    @Override
    public void updateMapToTrackPosition(final fr.free.nrw.commons.location.LatLng curLatLng) {
        if (!ListenerUtil.mutListener.listen(3068)) {
            Timber.d("Updates map camera to track user position");
        }
        if (!ListenerUtil.mutListener.listen(3070)) {
            if (null != mapView) {
                if (!ListenerUtil.mutListener.listen(3069)) {
                    recenterMap(curLatLng);
                }
            }
        }
    }

    @Override
    public void updateMapMarkers(final List<NearbyBaseMarker> nearbyBaseMarkers, final Marker selectedMarker) {
        if (!ListenerUtil.mutListener.listen(3072)) {
            if (mapView != null) {
                if (!ListenerUtil.mutListener.listen(3071)) {
                    presenter.updateMapMarkersToController(nearbyBaseMarkers);
                }
            }
        }
    }

    @Override
    public void filterOutAllMarkers() {
        if (!ListenerUtil.mutListener.listen(3073)) {
            clearAllMarkers();
        }
    }

    /**
     * Displays all markers
     */
    @Override
    public void displayAllMarkers() {
        if (!ListenerUtil.mutListener.listen(3075)) {
            {
                long _loopCounter43 = 0;
                for (final MarkerPlaceGroup markerPlaceGroup : NearbyController.markerLabelList) {
                    ListenerUtil.loopListener.listen("_loopCounter43", ++_loopCounter43);
                    if (!ListenerUtil.mutListener.listen(3074)) {
                        updateMarker(markerPlaceGroup.getIsBookmarked(), markerPlaceGroup.getPlace(), NearbyController.currentLocation);
                    }
                }
            }
        }
    }

    /**
     * Filters markers based on selectedLabels and chips
     *
     * @param selectedLabels       label list that user clicked
     * @param displayExists        chip for displaying only existing places
     * @param displayNeedsPhoto    chip for displaying only places need photos
     * @param filterForPlaceState  true if we filter places for place state
     * @param filterForAllNoneType true if we filter places with all none button
     */
    @Override
    public void filterMarkersByLabels(final List<Label> selectedLabels, final boolean displayExists, final boolean displayNeedsPhoto, final boolean displayWlm, final boolean filterForPlaceState, final boolean filterForAllNoneType) {
        if (!ListenerUtil.mutListener.listen(3076)) {
            // Remove the previous markers before updating them
            clearAllMarkers();
        }
        if (!ListenerUtil.mutListener.listen(3102)) {
            {
                long _loopCounter44 = 0;
                for (final MarkerPlaceGroup markerPlaceGroup : NearbyController.markerLabelList) {
                    ListenerUtil.loopListener.listen("_loopCounter44", ++_loopCounter44);
                    final Place place = markerPlaceGroup.getPlace();
                    if (!ListenerUtil.mutListener.listen(3082)) {
                        // then compare it against place's label
                        if ((ListenerUtil.mutListener.listen(3081) ? ((ListenerUtil.mutListener.listen(3078) ? (selectedLabels != null || ((ListenerUtil.mutListener.listen(3077) ? (selectedLabels.size() != 0 && !filterForPlaceState) : (selectedLabels.size() != 0 || !filterForPlaceState)))) : (selectedLabels != null && ((ListenerUtil.mutListener.listen(3077) ? (selectedLabels.size() != 0 && !filterForPlaceState) : (selectedLabels.size() != 0 || !filterForPlaceState))))) || ((ListenerUtil.mutListener.listen(3080) ? (!selectedLabels.contains(place.getLabel()) || !((ListenerUtil.mutListener.listen(3079) ? (selectedLabels.contains(Label.BOOKMARKS) || markerPlaceGroup.getIsBookmarked()) : (selectedLabels.contains(Label.BOOKMARKS) && markerPlaceGroup.getIsBookmarked())))) : (!selectedLabels.contains(place.getLabel()) && !((ListenerUtil.mutListener.listen(3079) ? (selectedLabels.contains(Label.BOOKMARKS) || markerPlaceGroup.getIsBookmarked()) : (selectedLabels.contains(Label.BOOKMARKS) && markerPlaceGroup.getIsBookmarked()))))))) : ((ListenerUtil.mutListener.listen(3078) ? (selectedLabels != null || ((ListenerUtil.mutListener.listen(3077) ? (selectedLabels.size() != 0 && !filterForPlaceState) : (selectedLabels.size() != 0 || !filterForPlaceState)))) : (selectedLabels != null && ((ListenerUtil.mutListener.listen(3077) ? (selectedLabels.size() != 0 && !filterForPlaceState) : (selectedLabels.size() != 0 || !filterForPlaceState))))) && ((ListenerUtil.mutListener.listen(3080) ? (!selectedLabels.contains(place.getLabel()) || !((ListenerUtil.mutListener.listen(3079) ? (selectedLabels.contains(Label.BOOKMARKS) || markerPlaceGroup.getIsBookmarked()) : (selectedLabels.contains(Label.BOOKMARKS) && markerPlaceGroup.getIsBookmarked())))) : (!selectedLabels.contains(place.getLabel()) && !((ListenerUtil.mutListener.listen(3079) ? (selectedLabels.contains(Label.BOOKMARKS) || markerPlaceGroup.getIsBookmarked()) : (selectedLabels.contains(Label.BOOKMARKS) && markerPlaceGroup.getIsBookmarked()))))))))) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3084)) {
                        if ((ListenerUtil.mutListener.listen(3083) ? (!displayWlm || place.isMonument()) : (!displayWlm && place.isMonument()))) {
                            continue;
                        }
                    }
                    boolean shouldUpdateMarker = false;
                    if (!ListenerUtil.mutListener.listen(3099)) {
                        if ((ListenerUtil.mutListener.listen(3085) ? (displayWlm || place.isMonument()) : (displayWlm && place.isMonument()))) {
                            if (!ListenerUtil.mutListener.listen(3098)) {
                                shouldUpdateMarker = true;
                            }
                        } else if ((ListenerUtil.mutListener.listen(3086) ? (displayExists || displayNeedsPhoto) : (displayExists && displayNeedsPhoto))) {
                            if (!ListenerUtil.mutListener.listen(3097)) {
                                // Exists and needs photo
                                if ((ListenerUtil.mutListener.listen(3095) ? (place.exists || place.pic.trim().isEmpty()) : (place.exists && place.pic.trim().isEmpty()))) {
                                    if (!ListenerUtil.mutListener.listen(3096)) {
                                        shouldUpdateMarker = true;
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(3087) ? (displayExists || !displayNeedsPhoto) : (displayExists && !displayNeedsPhoto))) {
                            if (!ListenerUtil.mutListener.listen(3094)) {
                                // Exists and all included needs and doesn't needs photo
                                if (place.exists) {
                                    if (!ListenerUtil.mutListener.listen(3093)) {
                                        shouldUpdateMarker = true;
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(3088) ? (!displayExists || displayNeedsPhoto) : (!displayExists && displayNeedsPhoto))) {
                            if (!ListenerUtil.mutListener.listen(3092)) {
                                // All and only needs photo
                                if (place.pic.trim().isEmpty()) {
                                    if (!ListenerUtil.mutListener.listen(3091)) {
                                        shouldUpdateMarker = true;
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(3089) ? (!displayExists || !displayNeedsPhoto) : (!displayExists && !displayNeedsPhoto))) {
                            if (!ListenerUtil.mutListener.listen(3090)) {
                                // all
                                shouldUpdateMarker = true;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3101)) {
                        if (shouldUpdateMarker) {
                            if (!ListenerUtil.mutListener.listen(3100)) {
                                updateMarker(markerPlaceGroup.getIsBookmarked(), place, NearbyController.currentLocation);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3108)) {
            if ((ListenerUtil.mutListener.listen(3103) ? (selectedLabels == null && selectedLabels.size() == 0) : (selectedLabels == null || selectedLabels.size() == 0))) {
                ArrayList<NearbyBaseMarker> markerArrayList = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(3106)) {
                    {
                        long _loopCounter45 = 0;
                        for (final MarkerPlaceGroup markerPlaceGroup : NearbyController.markerLabelList) {
                            ListenerUtil.loopListener.listen("_loopCounter45", ++_loopCounter45);
                            NearbyBaseMarker nearbyBaseMarker = new NearbyBaseMarker();
                            if (!ListenerUtil.mutListener.listen(3104)) {
                                nearbyBaseMarker.place(markerPlaceGroup.getPlace());
                            }
                            if (!ListenerUtil.mutListener.listen(3105)) {
                                markerArrayList.add(nearbyBaseMarker);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3107)) {
                    addMarkersToMap(markerArrayList, null);
                }
            }
        }
    }

    @Override
    public fr.free.nrw.commons.location.LatLng getCameraTarget() {
        return mapView == null ? null : getMapFocus();
    }

    /**
     * Sets marker icon according to marker status. Sets title and distance.
     *
     * @param isBookmarked true if place is bookmarked
     * @param place
     * @param curLatLng    current location
     */
    public void updateMarker(final boolean isBookmarked, final Place place, @Nullable final fr.free.nrw.commons.location.LatLng curLatLng) {
        if (!ListenerUtil.mutListener.listen(3109)) {
            addMarkerToMap(place, isBookmarked);
        }
    }

    /**
     * Highlights nearest place when user clicks on home nearby banner
     *
     * @param nearestPlace nearest place, which has to be highlighted
     */
    private void highlightNearestPlace(Place nearestPlace) {
        if (!ListenerUtil.mutListener.listen(3110)) {
            passInfoToSheet(nearestPlace);
        }
        if (!ListenerUtil.mutListener.listen(3111)) {
            hideBottomSheet();
        }
        if (!ListenerUtil.mutListener.listen(3112)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    /**
     * Returns drawable of marker icon for given place
     *
     * @param place where marker is to be added
     * @param isBookmarked true if place is bookmarked
     * @return returns the drawable of marker according to the place information
     */
    @DrawableRes
    private int getIconFor(Place place, Boolean isBookmarked) {
        if (nearestPlace != null) {
            if (place.name.equals(nearestPlace.name)) {
                if (!ListenerUtil.mutListener.listen(3113)) {
                    // Highlight nearest place only when user clicks on the home nearby banner
                    highlightNearestPlace(place);
                }
                return (isBookmarked ? R.drawable.ic_custom_map_marker_purple_bookmarked : R.drawable.ic_custom_map_marker_purple);
            }
        }
        if (place.isMonument()) {
            return R.drawable.ic_custom_map_marker_monuments;
        } else if (!place.pic.trim().isEmpty()) {
            return (isBookmarked ? R.drawable.ic_custom_map_marker_green_bookmarked : R.drawable.ic_custom_map_marker_green);
        } else if (!place.exists) {
            // Means that the topic of the Wikidata item does not exist in the real world anymore, for instance it is a past event, or a place that was destroyed
            return (isBookmarked ? R.drawable.ic_custom_map_marker_grey_bookmarked : R.drawable.ic_custom_map_marker_grey);
        } else {
            return (isBookmarked ? R.drawable.ic_custom_map_marker_blue_bookmarked : R.drawable.ic_custom_map_marker);
        }
    }

    /**
     * Adds a marker representing a place to the map with optional bookmark icon.
     *
     * @param place        The Place object containing information about the location.
     * @param isBookMarked A Boolean flag indicating whether the place is bookmarked or not.
     */
    private void addMarkerToMap(Place place, Boolean isBookMarked) {
        ArrayList<OverlayItem> items = new ArrayList<>();
        Drawable icon = ContextCompat.getDrawable(getContext(), getIconFor(place, isBookMarked));
        GeoPoint point = new GeoPoint(place.location.getLatitude(), place.location.getLongitude());
        OverlayItem item = new OverlayItem(place.name, null, point);
        if (!ListenerUtil.mutListener.listen(3114)) {
            item.setMarker(icon);
        }
        if (!ListenerUtil.mutListener.listen(3115)) {
            items.add(item);
        }
        ItemizedOverlayWithFocus overlay = new ItemizedOverlayWithFocus(items, new OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                if (!ListenerUtil.mutListener.listen(3116)) {
                    passInfoToSheet(place);
                }
                if (!ListenerUtil.mutListener.listen(3117)) {
                    hideBottomSheet();
                }
                if (!ListenerUtil.mutListener.listen(3120)) {
                    if (clickedMarkerPlace != null) {
                        if (!ListenerUtil.mutListener.listen(3118)) {
                            removeMarker(clickedMarkerPlace);
                        }
                        if (!ListenerUtil.mutListener.listen(3119)) {
                            addMarkerToMap(clickedMarkerPlace, isClickedMarkerBookmarked);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3121)) {
                    clickedMarkerPlace = place;
                }
                if (!ListenerUtil.mutListener.listen(3122)) {
                    isClickedMarkerBookmarked = isBookMarked;
                }
                if (!ListenerUtil.mutListener.listen(3123)) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        }, getContext());
        if (!ListenerUtil.mutListener.listen(3124)) {
            overlay.setFocusItemsOnTap(true);
        }
        if (!ListenerUtil.mutListener.listen(3125)) {
            // Add the overlay to the map
            mapView.getOverlays().add(overlay);
        }
    }

    /**
     * Adds multiple markers representing places to the map and handles item gestures.
     *
     * @param nearbyBaseMarkers The list of Place objects containing information about the
     *                          locations.
     */
    private void addMarkersToMap(List<NearbyBaseMarker> nearbyBaseMarkers, final Marker selectedMarker) {
        ArrayList<OverlayItem> items = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(3133)) {
            {
                long _loopCounter46 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3132) ? (i >= nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3131) ? (i <= nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3130) ? (i > nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3129) ? (i != nearbyBaseMarkers.size()) : (ListenerUtil.mutListener.listen(3128) ? (i == nearbyBaseMarkers.size()) : (i < nearbyBaseMarkers.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter46", ++_loopCounter46);
                    Drawable icon = ContextCompat.getDrawable(getContext(), getIconFor(nearbyBaseMarkers.get(i).getPlace(), false));
                    GeoPoint point = new GeoPoint(nearbyBaseMarkers.get(i).getPlace().location.getLatitude(), nearbyBaseMarkers.get(i).getPlace().location.getLongitude());
                    OverlayItem item = new OverlayItem(nearbyBaseMarkers.get(i).getPlace().name, null, point);
                    if (!ListenerUtil.mutListener.listen(3126)) {
                        item.setMarker(icon);
                    }
                    if (!ListenerUtil.mutListener.listen(3127)) {
                        items.add(item);
                    }
                }
            }
        }
        ItemizedOverlayWithFocus overlay = new ItemizedOverlayWithFocus(items, new OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                final Place place = nearbyBaseMarkers.get(index).getPlace();
                if (!ListenerUtil.mutListener.listen(3134)) {
                    passInfoToSheet(place);
                }
                if (!ListenerUtil.mutListener.listen(3135)) {
                    hideBottomSheet();
                }
                if (!ListenerUtil.mutListener.listen(3138)) {
                    if (clickedMarkerPlace != null) {
                        if (!ListenerUtil.mutListener.listen(3136)) {
                            removeMarker(clickedMarkerPlace);
                        }
                        if (!ListenerUtil.mutListener.listen(3137)) {
                            addMarkerToMap(clickedMarkerPlace, isClickedMarkerBookmarked);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3139)) {
                    clickedMarkerPlace = place;
                }
                if (!ListenerUtil.mutListener.listen(3140)) {
                    isClickedMarkerBookmarked = false;
                }
                if (!ListenerUtil.mutListener.listen(3141)) {
                    bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        }, getContext());
        if (!ListenerUtil.mutListener.listen(3142)) {
            overlay.setFocusItemsOnTap(true);
        }
        if (!ListenerUtil.mutListener.listen(3143)) {
            mapView.getOverlays().add(overlay);
        }
    }

    private void removeMarker(Place place) {
        List<Overlay> overlays = mapView.getOverlays();
        if (!ListenerUtil.mutListener.listen(3154)) {
            {
                long _loopCounter47 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3153) ? (i >= overlays.size()) : (ListenerUtil.mutListener.listen(3152) ? (i <= overlays.size()) : (ListenerUtil.mutListener.listen(3151) ? (i > overlays.size()) : (ListenerUtil.mutListener.listen(3150) ? (i != overlays.size()) : (ListenerUtil.mutListener.listen(3149) ? (i == overlays.size()) : (i < overlays.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter47", ++_loopCounter47);
                    if (!ListenerUtil.mutListener.listen(3148)) {
                        if (overlays.get(i) instanceof ItemizedOverlayWithFocus) {
                            ItemizedOverlayWithFocus item = (ItemizedOverlayWithFocus) overlays.get(i);
                            OverlayItem overlayItem = item.getItem(0);
                            fr.free.nrw.commons.location.LatLng diffLatLang = new fr.free.nrw.commons.location.LatLng(overlayItem.getPoint().getLatitude(), overlayItem.getPoint().getLongitude(), 100);
                            if (!ListenerUtil.mutListener.listen(3147)) {
                                if ((ListenerUtil.mutListener.listen(3144) ? (place.location.getLatitude() == overlayItem.getPoint().getLatitude() || place.location.getLongitude() == overlayItem.getPoint().getLongitude()) : (place.location.getLatitude() == overlayItem.getPoint().getLatitude() && place.location.getLongitude() == overlayItem.getPoint().getLongitude()))) {
                                    if (!ListenerUtil.mutListener.listen(3145)) {
                                        mapView.getOverlays().remove(i);
                                    }
                                    if (!ListenerUtil.mutListener.listen(3146)) {
                                        mapView.invalidate();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void recenterMap(fr.free.nrw.commons.location.LatLng curLatLng) {
        if (!ListenerUtil.mutListener.listen(3162)) {
            if ((ListenerUtil.mutListener.listen(3155) ? (isPermissionDenied && curLatLng == null) : (isPermissionDenied || curLatLng == null))) {
                if (!ListenerUtil.mutListener.listen(3156)) {
                    recenterToUserLocation = true;
                }
                if (!ListenerUtil.mutListener.listen(3157)) {
                    checkPermissionsAndPerformAction();
                }
                if (!ListenerUtil.mutListener.listen(3161)) {
                    if ((ListenerUtil.mutListener.listen(3159) ? (!isPermissionDenied || !((ListenerUtil.mutListener.listen(3158) ? (locationManager.isNetworkProviderEnabled() && locationManager.isGPSProviderEnabled()) : (locationManager.isNetworkProviderEnabled() || locationManager.isGPSProviderEnabled())))) : (!isPermissionDenied && !((ListenerUtil.mutListener.listen(3158) ? (locationManager.isNetworkProviderEnabled() && locationManager.isGPSProviderEnabled()) : (locationManager.isNetworkProviderEnabled() || locationManager.isGPSProviderEnabled())))))) {
                        if (!ListenerUtil.mutListener.listen(3160)) {
                            showLocationOffDialog();
                        }
                    }
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3163)) {
            addCurrentLocationMarker(curLatLng);
        }
        if (!ListenerUtil.mutListener.listen(3164)) {
            mapView.getController().animateTo(new GeoPoint(curLatLng.getLatitude(), curLatLng.getLongitude()));
        }
        if (!ListenerUtil.mutListener.listen(3180)) {
            if (lastMapFocus != null) {
                Location mylocation = new Location("");
                Location dest_location = new Location("");
                if (!ListenerUtil.mutListener.listen(3165)) {
                    dest_location.setLatitude(mapView.getMapCenter().getLatitude());
                }
                if (!ListenerUtil.mutListener.listen(3166)) {
                    dest_location.setLongitude(mapView.getMapCenter().getLongitude());
                }
                if (!ListenerUtil.mutListener.listen(3167)) {
                    mylocation.setLatitude(lastMapFocus.getLatitude());
                }
                if (!ListenerUtil.mutListener.listen(3168)) {
                    mylocation.setLongitude(lastMapFocus.getLongitude());
                }
                // in meters
                Float distance = mylocation.distanceTo(dest_location);
                if (!ListenerUtil.mutListener.listen(3179)) {
                    if (lastMapFocus != null) {
                        if (!ListenerUtil.mutListener.listen(3178)) {
                            if (isNetworkConnectionEstablished()) {
                                if (!ListenerUtil.mutListener.listen(3177)) {
                                    if ((ListenerUtil.mutListener.listen(3174) ? (distance >= 2000.0) : (ListenerUtil.mutListener.listen(3173) ? (distance <= 2000.0) : (ListenerUtil.mutListener.listen(3172) ? (distance < 2000.0) : (ListenerUtil.mutListener.listen(3171) ? (distance != 2000.0) : (ListenerUtil.mutListener.listen(3170) ? (distance == 2000.0) : (distance > 2000.0))))))) {
                                        if (!ListenerUtil.mutListener.listen(3176)) {
                                            setSearchThisAreaButtonVisibility(true);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(3175)) {
                                            setSearchThisAreaButtonVisibility(false);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3169)) {
                            setSearchThisAreaButtonVisibility(false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void showLocationOffDialog() {
        if (!ListenerUtil.mutListener.listen(3181)) {
            // This creates a dialog box that prompts the user to enable location
            DialogUtil.showAlertDialog(getActivity(), getString(R.string.ask_to_turn_location_on), getString(R.string.nearby_needs_location), getString(R.string.yes), getString(R.string.no), this::openLocationSettings, null);
        }
    }

    @Override
    public void openLocationSettings() {
        // This method opens the location settings of the device along with a followup toast.
        final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        final PackageManager packageManager = getActivity().getPackageManager();
        if (!ListenerUtil.mutListener.listen(3185)) {
            if (intent.resolveActivity(packageManager) != null) {
                if (!ListenerUtil.mutListener.listen(3183)) {
                    startActivity(intent);
                }
                if (!ListenerUtil.mutListener.listen(3184)) {
                    Toast.makeText(getContext(), R.string.recommend_high_accuracy_mode, Toast.LENGTH_LONG).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3182)) {
                    Toast.makeText(getContext(), R.string.cannot_open_location_settings, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void hideBottomSheet() {
        if (!ListenerUtil.mutListener.listen(3186)) {
            bottomSheetListBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void hideBottomDetailsSheet() {
        if (!ListenerUtil.mutListener.listen(3187)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void displayBottomSheetWithInfo(final Marker marker) {
        if (!ListenerUtil.mutListener.listen(3188)) {
            selectedMarker = marker;
        }
        final NearbyMarker nearbyMarker = (NearbyMarker) marker;
        final Place place = nearbyMarker.getNearbyBaseMarker().getPlace();
        if (!ListenerUtil.mutListener.listen(3189)) {
            passInfoToSheet(place);
        }
        if (!ListenerUtil.mutListener.listen(3190)) {
            hideBottomSheet();
        }
        if (!ListenerUtil.mutListener.listen(3191)) {
            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    /**
     * If nearby details bottom sheet state is collapsed: show fab plus If nearby details bottom
     * sheet state is expanded: show fab plus If nearby details bottom sheet state is hidden: hide
     * all fabs
     *
     * @param bottomSheetState see bottom sheet states
     */
    public void prepareViewsForSheetPosition(final int bottomSheetState) {
        if (!ListenerUtil.mutListener.listen(3199)) {
            switch(bottomSheetState) {
                case (BottomSheetBehavior.STATE_COLLAPSED):
                    if (!ListenerUtil.mutListener.listen(3192)) {
                        collapseFABs(isFABsExpanded);
                    }
                    if (!ListenerUtil.mutListener.listen(3194)) {
                        if (!fabPlus.isShown()) {
                            if (!ListenerUtil.mutListener.listen(3193)) {
                                showFABs();
                            }
                        }
                    }
                    break;
                case (BottomSheetBehavior.STATE_HIDDEN):
                    if (!ListenerUtil.mutListener.listen(3195)) {
                        transparentView.setClickable(false);
                    }
                    if (!ListenerUtil.mutListener.listen(3196)) {
                        transparentView.setAlpha(0);
                    }
                    if (!ListenerUtil.mutListener.listen(3197)) {
                        collapseFABs(isFABsExpanded);
                    }
                    if (!ListenerUtil.mutListener.listen(3198)) {
                        hideFABs();
                    }
                    break;
            }
        }
    }

    /**
     * Same bottom sheet carries information for all nearby places, so we need to pass information
     * (title, description, distance and links) to view on nearby marker click
     *
     * @param place Place of clicked nearby marker
     */
    private void passInfoToSheet(final Place place) {
        if (!ListenerUtil.mutListener.listen(3200)) {
            selectedPlace = place;
        }
        if (!ListenerUtil.mutListener.listen(3201)) {
            updateBookmarkButtonImage(selectedPlace);
        }
        if (!ListenerUtil.mutListener.listen(3202)) {
            bookmarkButton.setOnClickListener(view -> {
                final boolean isBookmarked = bookmarkLocationDao.updateBookmarkLocation(selectedPlace);
                updateBookmarkButtonImage(selectedPlace);
                updateMarker(isBookmarked, selectedPlace, locationManager.getLastLocation());
                mapView.invalidate();
            });
        }
        if (!ListenerUtil.mutListener.listen(3203)) {
            bookmarkButton.setOnLongClickListener(view -> {
                Toast.makeText(getContext(), R.string.menu_bookmark, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(3204)) {
            wikipediaButton.setVisibility(place.hasWikipediaLink() ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3205)) {
            wikipediaButton.setOnClickListener(view -> Utils.handleWebUrl(getContext(), selectedPlace.siteLinks.getWikipediaLink()));
        }
        if (!ListenerUtil.mutListener.listen(3206)) {
            wikipediaButton.setOnLongClickListener(view -> {
                Toast.makeText(getContext(), R.string.nearby_wikipedia, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(3207)) {
            wikidataButton.setVisibility(place.hasWikidataLink() ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3208)) {
            wikidataButton.setOnClickListener(view -> Utils.handleWebUrl(getContext(), selectedPlace.siteLinks.getWikidataLink()));
        }
        if (!ListenerUtil.mutListener.listen(3209)) {
            wikidataButton.setOnLongClickListener(view -> {
                Toast.makeText(getContext(), R.string.nearby_wikidata, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(3210)) {
            directionsButton.setOnClickListener(view -> Utils.handleGeoCoordinates(getActivity(), selectedPlace.getLocation()));
        }
        if (!ListenerUtil.mutListener.listen(3211)) {
            directionsButton.setOnLongClickListener(view -> {
                Toast.makeText(getContext(), R.string.nearby_directions, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(3212)) {
            commonsButton.setVisibility(selectedPlace.hasCommonsLink() ? View.VISIBLE : View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(3213)) {
            commonsButton.setOnClickListener(view -> Utils.handleWebUrl(getContext(), selectedPlace.siteLinks.getCommonsLink()));
        }
        if (!ListenerUtil.mutListener.listen(3214)) {
            commonsButton.setOnLongClickListener(view -> {
                Toast.makeText(getContext(), R.string.nearby_commons, Toast.LENGTH_SHORT).show();
                return true;
            });
        }
        if (!ListenerUtil.mutListener.listen(3215)) {
            icon.setImageResource(selectedPlace.getLabel().getIcon());
        }
        if (!ListenerUtil.mutListener.listen(3216)) {
            title.setText(selectedPlace.name);
        }
        if (!ListenerUtil.mutListener.listen(3217)) {
            distance.setText(selectedPlace.distance);
        }
        // Remove label since it is double information
        String descriptionText = selectedPlace.getLongDescription().replace(selectedPlace.getName() + " (", "");
        if (!ListenerUtil.mutListener.listen(3218)) {
            descriptionText = (descriptionText.equals(selectedPlace.getLongDescription()) ? descriptionText : descriptionText.replaceFirst(".$", ""));
        }
        if (!ListenerUtil.mutListener.listen(3219)) {
            // Set the short description after we remove place name from long description
            description.setText(descriptionText);
        }
        if (!ListenerUtil.mutListener.listen(3220)) {
            fabCamera.setOnClickListener(view -> {
                if (fabCamera.isShown()) {
                    Timber.d("Camera button tapped. Place: %s", selectedPlace.toString());
                    storeSharedPrefs(selectedPlace);
                    controller.initiateCameraPick(getActivity(), inAppCameraLocationPermissionLauncher);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(3221)) {
            fabGallery.setOnClickListener(view -> {
                if (fabGallery.isShown()) {
                    Timber.d("Gallery button tapped. Place: %s", selectedPlace.toString());
                    storeSharedPrefs(selectedPlace);
                    controller.initiateGalleryPick(getActivity(), chipWlm.isChecked());
                }
            });
        }
    }

    private void storeSharedPrefs(final Place selectedPlace) {
        if (!ListenerUtil.mutListener.listen(3222)) {
            applicationKvStore.putJson(PLACE_OBJECT, selectedPlace);
        }
        Place place = applicationKvStore.getJson(PLACE_OBJECT, Place.class);
        if (!ListenerUtil.mutListener.listen(3223)) {
            Timber.d("Stored place object %s", place.toString());
        }
    }

    private void updateBookmarkButtonImage(final Place place) {
        final int bookmarkIcon;
        if (bookmarkLocationDao.findBookmarkLocation(place)) {
            bookmarkIcon = R.drawable.ic_round_star_filled_24px;
        } else {
            bookmarkIcon = R.drawable.ic_round_star_border_24px;
        }
        if (!ListenerUtil.mutListener.listen(3225)) {
            if (bookmarkButtonImage != null) {
                if (!ListenerUtil.mutListener.listen(3224)) {
                    bookmarkButtonImage.setImageResource(bookmarkIcon);
                }
            }
        }
    }

    @Override
    public void onAttach(final Context context) {
        if (!ListenerUtil.mutListener.listen(3226)) {
            super.onAttach(context);
        }
        if (!ListenerUtil.mutListener.listen(3227)) {
            wikidataEditListener.setAuthenticationStateListener(this);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3228)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(3229)) {
            wikidataEditListener.setAuthenticationStateListener(null);
        }
    }

    @Override
    public void onWikidataEditSuccessful() {
        if (!ListenerUtil.mutListener.listen(3232)) {
            if ((ListenerUtil.mutListener.listen(3230) ? (presenter != null || locationManager != null) : (presenter != null && locationManager != null))) {
                if (!ListenerUtil.mutListener.listen(3231)) {
                    presenter.updateMapAndList(MAP_UPDATED);
                }
            }
        }
    }

    private void showErrorMessage(final String message) {
        if (!ListenerUtil.mutListener.listen(3233)) {
            ViewUtil.showLongToast(getActivity(), message);
        }
    }

    public void registerUnregisterLocationListener(final boolean removeLocationListener) {
        try {
            if (!ListenerUtil.mutListener.listen(3241)) {
                if (removeLocationListener) {
                    if (!ListenerUtil.mutListener.listen(3238)) {
                        locationManager.unregisterLocationManager();
                    }
                    if (!ListenerUtil.mutListener.listen(3239)) {
                        locationManager.removeLocationListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(3240)) {
                        Timber.d("Location service manager unregistered and removed");
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3235)) {
                        locationManager.addLocationListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(3236)) {
                        locationManager.registerLocationManager();
                    }
                    if (!ListenerUtil.mutListener.listen(3237)) {
                        Timber.d("Location service manager added and registered");
                    }
                }
            }
        } catch (final Exception e) {
            if (!ListenerUtil.mutListener.listen(3234)) {
                Timber.e(e);
            }
        }
    }

    @Override
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        if (!ListenerUtil.mutListener.listen(3242)) {
            super.setUserVisibleHint(isVisibleToUser);
        }
        if (!ListenerUtil.mutListener.listen(3243)) {
            this.isVisibleToUser = isVisibleToUser;
        }
        if (!ListenerUtil.mutListener.listen(3250)) {
            if ((ListenerUtil.mutListener.listen(3244) ? (isResumed() || isVisibleToUser) : (isResumed() && isVisibleToUser))) {
                if (!ListenerUtil.mutListener.listen(3249)) {
                    startTheMap();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3246)) {
                    if (null != bottomSheetListBehavior) {
                        if (!ListenerUtil.mutListener.listen(3245)) {
                            bottomSheetListBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3248)) {
                    if (null != bottomSheetDetailsBehavior) {
                        if (!ListenerUtil.mutListener.listen(3247)) {
                            bottomSheetDetailsBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }
                    }
                }
            }
        }
    }

    private void startTheMap() {
        if (!ListenerUtil.mutListener.listen(3251)) {
            performMapReadyActions();
        }
    }

    /**
     * Clears all markers from the map and resets certain map overlays and gestures. After clearing
     * markers, it re-adds a scale bar overlay and rotation gesture overlay to the map.
     */
    @Override
    public void clearAllMarkers() {
        if (!ListenerUtil.mutListener.listen(3252)) {
            mapView.getOverlayManager().clear();
        }
        if (!ListenerUtil.mutListener.listen(3253)) {
            mapView.invalidate();
        }
        GeoPoint geoPoint = mapCenter;
        if (!ListenerUtil.mutListener.listen(3270)) {
            if (geoPoint != null) {
                List<Overlay> overlays = mapView.getOverlays();
                ScaleDiskOverlay diskOverlay = new ScaleDiskOverlay(this.getContext(), geoPoint, 2000, GeoConstants.UnitOfMeasure.foot);
                Paint circlePaint = new Paint();
                if (!ListenerUtil.mutListener.listen(3254)) {
                    circlePaint.setColor(Color.rgb(128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(3255)) {
                    circlePaint.setStyle(Paint.Style.STROKE);
                }
                if (!ListenerUtil.mutListener.listen(3256)) {
                    circlePaint.setStrokeWidth(2f);
                }
                if (!ListenerUtil.mutListener.listen(3257)) {
                    diskOverlay.setCirclePaint2(circlePaint);
                }
                Paint diskPaint = new Paint();
                if (!ListenerUtil.mutListener.listen(3258)) {
                    diskPaint.setColor(Color.argb(40, 128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(3259)) {
                    diskPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                }
                if (!ListenerUtil.mutListener.listen(3260)) {
                    diskOverlay.setCirclePaint1(diskPaint);
                }
                if (!ListenerUtil.mutListener.listen(3261)) {
                    diskOverlay.setDisplaySizeMin(900);
                }
                if (!ListenerUtil.mutListener.listen(3262)) {
                    diskOverlay.setDisplaySizeMax(1700);
                }
                if (!ListenerUtil.mutListener.listen(3263)) {
                    mapView.getOverlays().add(diskOverlay);
                }
                org.osmdroid.views.overlay.Marker startMarker = new org.osmdroid.views.overlay.Marker(mapView);
                if (!ListenerUtil.mutListener.listen(3264)) {
                    startMarker.setPosition(geoPoint);
                }
                if (!ListenerUtil.mutListener.listen(3265)) {
                    startMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
                }
                if (!ListenerUtil.mutListener.listen(3266)) {
                    startMarker.setIcon(ContextCompat.getDrawable(this.getContext(), R.drawable.current_location_marker));
                }
                if (!ListenerUtil.mutListener.listen(3267)) {
                    startMarker.setTitle("Your Location");
                }
                if (!ListenerUtil.mutListener.listen(3268)) {
                    startMarker.setTextLabelFontSize(24);
                }
                if (!ListenerUtil.mutListener.listen(3269)) {
                    mapView.getOverlays().add(startMarker);
                }
            }
        }
        ScaleBarOverlay scaleBarOverlay = new ScaleBarOverlay(mapView);
        if (!ListenerUtil.mutListener.listen(3271)) {
            scaleBarOverlay.setScaleBarOffset(15, 25);
        }
        Paint barPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(3272)) {
            barPaint.setARGB(200, 255, 250, 250);
        }
        if (!ListenerUtil.mutListener.listen(3273)) {
            scaleBarOverlay.setBackgroundPaint(barPaint);
        }
        if (!ListenerUtil.mutListener.listen(3274)) {
            scaleBarOverlay.enableScaleBar();
        }
        if (!ListenerUtil.mutListener.listen(3275)) {
            mapView.getOverlays().add(scaleBarOverlay);
        }
        if (!ListenerUtil.mutListener.listen(3283)) {
            mapView.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {

                @Override
                public boolean singleTapConfirmedHelper(GeoPoint p) {
                    if (!ListenerUtil.mutListener.listen(3279)) {
                        if (clickedMarkerPlace != null) {
                            if (!ListenerUtil.mutListener.listen(3277)) {
                                removeMarker(clickedMarkerPlace);
                            }
                            if (!ListenerUtil.mutListener.listen(3278)) {
                                addMarkerToMap(clickedMarkerPlace, isClickedMarkerBookmarked);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3276)) {
                                Timber.e("CLICKED MARKER IS NULL");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3282)) {
                        if (isListBottomSheetExpanded()) {
                            if (!ListenerUtil.mutListener.listen(3281)) {
                                // Back should first hide the bottom sheet if it is expanded
                                hideBottomSheet();
                            }
                        } else if (isDetailsBottomSheetVisible()) {
                            if (!ListenerUtil.mutListener.listen(3280)) {
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
        if (!ListenerUtil.mutListener.listen(3284)) {
            mapView.setMultiTouchControls(true);
        }
    }

    /**
     * Recenters the map to the Center and adds a scale disk overlay and a marker at the position.
     *
     * @param geoPoint The GeoPoint representing the new center position of the map.
     */
    private void recenterMarkerToPosition(GeoPoint geoPoint) {
        if (!ListenerUtil.mutListener.listen(3311)) {
            if (geoPoint != null) {
                if (!ListenerUtil.mutListener.listen(3285)) {
                    mapView.getController().setCenter(geoPoint);
                }
                List<Overlay> overlays = mapView.getOverlays();
                if (!ListenerUtil.mutListener.listen(3294)) {
                    {
                        long _loopCounter48 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(3293) ? (i >= overlays.size()) : (ListenerUtil.mutListener.listen(3292) ? (i <= overlays.size()) : (ListenerUtil.mutListener.listen(3291) ? (i > overlays.size()) : (ListenerUtil.mutListener.listen(3290) ? (i != overlays.size()) : (ListenerUtil.mutListener.listen(3289) ? (i == overlays.size()) : (i < overlays.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter48", ++_loopCounter48);
                            if (!ListenerUtil.mutListener.listen(3288)) {
                                if (overlays.get(i) instanceof org.osmdroid.views.overlay.Marker) {
                                    if (!ListenerUtil.mutListener.listen(3287)) {
                                        mapView.getOverlays().remove(i);
                                    }
                                } else if (overlays.get(i) instanceof ScaleDiskOverlay) {
                                    if (!ListenerUtil.mutListener.listen(3286)) {
                                        mapView.getOverlays().remove(i);
                                    }
                                }
                            }
                        }
                    }
                }
                ScaleDiskOverlay diskOverlay = new ScaleDiskOverlay(this.getContext(), geoPoint, 2000, GeoConstants.UnitOfMeasure.foot);
                Paint circlePaint = new Paint();
                if (!ListenerUtil.mutListener.listen(3295)) {
                    circlePaint.setColor(Color.rgb(128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(3296)) {
                    circlePaint.setStyle(Paint.Style.STROKE);
                }
                if (!ListenerUtil.mutListener.listen(3297)) {
                    circlePaint.setStrokeWidth(2f);
                }
                if (!ListenerUtil.mutListener.listen(3298)) {
                    diskOverlay.setCirclePaint2(circlePaint);
                }
                Paint diskPaint = new Paint();
                if (!ListenerUtil.mutListener.listen(3299)) {
                    diskPaint.setColor(Color.argb(40, 128, 128, 128));
                }
                if (!ListenerUtil.mutListener.listen(3300)) {
                    diskPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                }
                if (!ListenerUtil.mutListener.listen(3301)) {
                    diskOverlay.setCirclePaint1(diskPaint);
                }
                if (!ListenerUtil.mutListener.listen(3302)) {
                    diskOverlay.setDisplaySizeMin(900);
                }
                if (!ListenerUtil.mutListener.listen(3303)) {
                    diskOverlay.setDisplaySizeMax(1700);
                }
                if (!ListenerUtil.mutListener.listen(3304)) {
                    mapView.getOverlays().add(diskOverlay);
                }
                org.osmdroid.views.overlay.Marker startMarker = new org.osmdroid.views.overlay.Marker(mapView);
                if (!ListenerUtil.mutListener.listen(3305)) {
                    startMarker.setPosition(geoPoint);
                }
                if (!ListenerUtil.mutListener.listen(3306)) {
                    startMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
                }
                if (!ListenerUtil.mutListener.listen(3307)) {
                    startMarker.setIcon(ContextCompat.getDrawable(this.getContext(), R.drawable.current_location_marker));
                }
                if (!ListenerUtil.mutListener.listen(3308)) {
                    startMarker.setTitle("Your Location");
                }
                if (!ListenerUtil.mutListener.listen(3309)) {
                    startMarker.setTextLabelFontSize(24);
                }
                if (!ListenerUtil.mutListener.listen(3310)) {
                    mapView.getOverlays().add(startMarker);
                }
            }
        }
    }

    private void moveCameraToPosition(GeoPoint geoPoint) {
        if (!ListenerUtil.mutListener.listen(3312)) {
            mapView.getController().animateTo(geoPoint);
        }
    }

    public interface NearbyParentFragmentInstanceReadyCallback {

        void onReady();
    }

    public void setNearbyParentFragmentInstanceReadyCallback(NearbyParentFragmentInstanceReadyCallback nearbyParentFragmentInstanceReadyCallback) {
        if (!ListenerUtil.mutListener.listen(3313)) {
            this.nearbyParentFragmentInstanceReadyCallback = nearbyParentFragmentInstanceReadyCallback;
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull final Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(3314)) {
            super.onConfigurationChanged(newConfig);
        }
        ViewGroup.LayoutParams rlBottomSheetLayoutParams = rlBottomSheet.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(3323)) {
            rlBottomSheetLayoutParams.height = (ListenerUtil.mutListener.listen(3322) ? ((ListenerUtil.mutListener.listen(3318) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(3317) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(3316) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(3315) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) % 9) : (ListenerUtil.mutListener.listen(3321) ? ((ListenerUtil.mutListener.listen(3318) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(3317) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(3316) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(3315) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) / 9) : (ListenerUtil.mutListener.listen(3320) ? ((ListenerUtil.mutListener.listen(3318) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(3317) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(3316) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(3315) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) - 9) : (ListenerUtil.mutListener.listen(3319) ? ((ListenerUtil.mutListener.listen(3318) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(3317) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(3316) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(3315) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) + 9) : ((ListenerUtil.mutListener.listen(3318) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() % 16) : (ListenerUtil.mutListener.listen(3317) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() * 16) : (ListenerUtil.mutListener.listen(3316) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() - 16) : (ListenerUtil.mutListener.listen(3315) ? (getActivity().getWindowManager().getDefaultDisplay().getHeight() + 16) : (getActivity().getWindowManager().getDefaultDisplay().getHeight() / 16))))) * 9)))));
        }
        if (!ListenerUtil.mutListener.listen(3324)) {
            rlBottomSheet.setLayoutParams(rlBottomSheetLayoutParams);
        }
    }

    @OnClick(R.id.tv_learn_more)
    public void onLearnMoreClicked() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (!ListenerUtil.mutListener.listen(3325)) {
            intent.setData(Uri.parse(WLM_URL));
        }
        if (!ListenerUtil.mutListener.listen(3326)) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.iv_toggle_chips)
    public void onToggleChipsClicked() {
        if (!ListenerUtil.mutListener.listen(3329)) {
            if (llContainerChips.getVisibility() == View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(3328)) {
                    llContainerChips.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3327)) {
                    llContainerChips.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3330)) {
            ivToggleChips.setRotation(ivToggleChips.getRotation() + 180);
        }
    }
}
