/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2019-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.locationpicker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.card.MaterialCardView;
import com.mapbox.android.gestures.MoveGestureDetector;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdate;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.activities.ThreemaActivity;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.managers.ServiceManager;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.DebouncedOnClickListener;
import ch.threema.app.ui.EmptyRecyclerView;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.IntentDataUtil;
import ch.threema.app.utils.LocationUtil;
import ch.threema.app.utils.RuntimeUtil;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_LAT;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_LNG;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LocationPickerActivity extends ThreemaActivity implements GenericAlertDialog.DialogClickListener, LocationPickerAdapter.OnClickItemListener, LocationPickerConfirmDialog.LocationConfirmDialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(LocationPickerActivity.class);

    private static final String DIALOG_TAG_ENABLE_LOCATION_SERVICES = "lss";

    private static final String DIALOG_TAG_CONFIRM_PLACE = "conf";

    private static final int REQUEST_CODE_PLACES = 22228;

    private static final int REQUEST_CODE_LOCATION_SETTINGS = 22229;

    private static final int APPBAR_HEIGHT_PERCENT = 68;

    // URLs for Threema Map server
    public static final String MAP_STYLE_URL = "https://map.threema.ch/styles/streets/style.json";

    // meters
    public static final int POI_RADIUS = 750;

    private static final int MAX_POI_COUNT = 30;

    // Threema services
    private PreferenceService preferenceService;

    private MapView mapView;

    private MapboxMap mapboxMap;

    private LocationManager locationManager;

    private LocationComponent locationComponent;

    private List<Poi> places;

    private EmptyRecyclerView recyclerView;

    private TextView poilistDescription;

    MaterialCardView searchView;

    AppBarLayout appBarLayout;

    private LatLng lastPosition = new LatLng(0, 0);

    private LocationPickerAdapter locationPickerAdapter;

    private ContentLoadingProgressBar loadingProgressBar;

    @SuppressLint("StaticFieldLeak")
    private class NearbyPOITask extends AsyncTask<LatLng, Void, List<Poi>> {

        @Override
        protected void onPreExecute() {
            if (!ListenerUtil.mutListener.listen(28618)) {
                loadingProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected List<Poi> doInBackground(LatLng... latLngs) {
            LatLng latLng = latLngs[0];
            if (!ListenerUtil.mutListener.listen(28619)) {
                logger.debug("NearbyPoiTask: get POIs for " + latLng.toString());
            }
            List<Poi> pois = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(28620)) {
                NearbyPoiUtil.getPOIs(latLng, pois, MAX_POI_COUNT, preferenceService);
            }
            return pois;
        }

        @Override
        protected void onPostExecute(List<Poi> pois) {
            if (!ListenerUtil.mutListener.listen(28621)) {
                loadingProgressBar.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(28631)) {
                if (pois != null) {
                    if (!ListenerUtil.mutListener.listen(28622)) {
                        // update markers and list
                        bindPlaces(pois);
                    }
                    if (!ListenerUtil.mutListener.listen(28630)) {
                        if ((ListenerUtil.mutListener.listen(28627) ? (pois.size() >= 0) : (ListenerUtil.mutListener.listen(28626) ? (pois.size() <= 0) : (ListenerUtil.mutListener.listen(28625) ? (pois.size() < 0) : (ListenerUtil.mutListener.listen(28624) ? (pois.size() != 0) : (ListenerUtil.mutListener.listen(28623) ? (pois.size() == 0) : (pois.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(28628)) {
                                poilistDescription.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(28629)) {
                                places = pois;
                            }
                            return;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(28632)) {
                places = null;
            }
            if (!ListenerUtil.mutListener.listen(28634)) {
                if (locationPickerAdapter != null) {
                    if (!ListenerUtil.mutListener.listen(28633)) {
                        locationPickerAdapter.setPois(new ArrayList<>());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(28635)) {
                poilistDescription.setVisibility(View.INVISIBLE);
            }
        }
    }

    private NearbyPOITask nearbyPOITask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(28636)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(28637)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(28638)) {
            setContentView(R.layout.activity_location_picker);
        }
        if (!ListenerUtil.mutListener.listen(28639)) {
            ConfigUtils.configureTransparentStatusBar(this);
        }
        if (!ListenerUtil.mutListener.listen(28640)) {
            ((CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout)).setStatusBarScrimColor(ConfigUtils.getColorFromAttribute(this, R.attr.colorAccent));
        }
        if (!ListenerUtil.mutListener.listen(28641)) {
            appBarLayout = findViewById(R.id.appbar_layout);
        }
        if (!ListenerUtil.mutListener.listen(28642)) {
            mapView = findViewById(R.id.map);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (!ListenerUtil.mutListener.listen(28643)) {
            setSupportActionBar(toolbar);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(28645)) {
            if (actionBar == null) {
                if (!ListenerUtil.mutListener.listen(28644)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28646)) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(28648)) {
            if (toolbar.getNavigationIcon() != null) {
                if (!ListenerUtil.mutListener.listen(28647)) {
                    toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }
        }
        // Get Threema services
        final ServiceManager serviceManager = ThreemaApplication.getServiceManager();
        if (!ListenerUtil.mutListener.listen(28651)) {
            if (serviceManager == null) {
                if (!ListenerUtil.mutListener.listen(28649)) {
                    logger.error("Could not obtain service manager");
                }
                if (!ListenerUtil.mutListener.listen(28650)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28652)) {
            this.preferenceService = serviceManager.getPreferenceService();
        }
        if (!ListenerUtil.mutListener.listen(28655)) {
            if (this.preferenceService == null) {
                if (!ListenerUtil.mutListener.listen(28653)) {
                    logger.error("Could not obtain preference service");
                }
                if (!ListenerUtil.mutListener.listen(28654)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28658)) {
            if ((ListenerUtil.mutListener.listen(28656) ? (BuildConfig.DEBUG || this.preferenceService.getPoiServerHostOverride() != null) : (BuildConfig.DEBUG && this.preferenceService.getPoiServerHostOverride() != null))) {
                if (!ListenerUtil.mutListener.listen(28657)) {
                    Toast.makeText(this, "Using POI host override", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28659)) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(28661)) {
            if (locationManager == null) {
                if (!ListenerUtil.mutListener.listen(28660)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28662)) {
            mapView.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(28663)) {
            initUi();
        }
        if (!ListenerUtil.mutListener.listen(28664)) {
            initMap();
        }
    }

    private void initUi() {
        if (!ListenerUtil.mutListener.listen(28665)) {
            recyclerView = findViewById(R.id.poi_list);
        }
        if (!ListenerUtil.mutListener.listen(28666)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (!ListenerUtil.mutListener.listen(28668)) {
            /*
		EmptyView emptyView = new EmptyView(this, 0);
		emptyView.setup(R.string.lp_no_nearby_places_found);
		((ViewGroup) recyclerView.getParent()).addView(emptyView);
		recyclerView.setEmptyView(emptyView);
*/
            findViewById(R.id.center_map).setOnClickListener(new DebouncedOnClickListener(1000) {

                @Override
                public void onDebouncedClick(View v) {
                    if (!ListenerUtil.mutListener.listen(28667)) {
                        zoomToCenter();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28670)) {
            findViewById(R.id.search_container).setOnClickListener(new DebouncedOnClickListener(1000) {

                @Override
                public void onDebouncedClick(View v) {
                    if (!ListenerUtil.mutListener.listen(28669)) {
                        requestPlacesSearch();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28672)) {
            findViewById(R.id.send_location_container).setOnClickListener(new DebouncedOnClickListener(1000) {

                @Override
                public void onDebouncedClick(View v) {
                    if (!ListenerUtil.mutListener.listen(28671)) {
                        returnData(null);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28673)) {
            loadingProgressBar = findViewById(R.id.loading_progressbar);
        }
        if (!ListenerUtil.mutListener.listen(28674)) {
            poilistDescription = findViewById(R.id.poi_list_description);
        }
        if (!ListenerUtil.mutListener.listen(28675)) {
            searchView = findViewById(R.id.search_container);
        }
        if (!ListenerUtil.mutListener.listen(28676)) {
            searchView.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(28677)) {
            ((AppBarLayout) findViewById(R.id.appbar_layout)).addOnOffsetChangedListener(((appBarLayout, verticalOffset) -> {
                Toolbar toolbar = findViewById(R.id.toolbar);
                float verticalOffset1 = (float) verticalOffset;
                toolbar.setAlpha(Math.abs(verticalOffset1 / (float) appBarLayout.getTotalScrollRange()));
            }));
        }
        if (!ListenerUtil.mutListener.listen(28678)) {
            adjustAppBarHeight();
        }
    }

    private void adjustAppBarHeight() {
        ViewGroup.LayoutParams layoutParams = appBarLayout.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(28706)) {
            if (layoutParams != null) {
                CoordinatorLayout.LayoutParams appBarLayoutParams = (CoordinatorLayout.LayoutParams) layoutParams;
                if (!ListenerUtil.mutListener.listen(28679)) {
                    appBarLayoutParams.setBehavior((new AppBarLayout.Behavior()));
                }
                CoordinatorLayout.Behavior appBarLayoutParamsBehavior = appBarLayoutParams.getBehavior();
                if (!ListenerUtil.mutListener.listen(28705)) {
                    if (appBarLayoutParamsBehavior != null) {
                        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) appBarLayoutParamsBehavior;
                        if (!ListenerUtil.mutListener.listen(28680)) {
                            behavior.setDragCallback((new AppBarLayout.Behavior.DragCallback() {

                                @Override
                                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                                    return false;
                                }
                            }));
                        }
                        // Set the size of AppBarLayout to 68% of the total height
                        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinator);
                        if (!ListenerUtil.mutListener.listen(28704)) {
                            if ((ListenerUtil.mutListener.listen(28681) ? (ViewCompat.isLaidOut(coordinatorLayout) || !coordinatorLayout.isLayoutRequested()) : (ViewCompat.isLaidOut(coordinatorLayout) && !coordinatorLayout.isLayoutRequested()))) {
                                if (!ListenerUtil.mutListener.listen(28702)) {
                                    appBarLayoutParams.height = (ListenerUtil.mutListener.listen(28701) ? ((ListenerUtil.mutListener.listen(28697) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28696) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28695) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28694) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) % 100) : (ListenerUtil.mutListener.listen(28700) ? ((ListenerUtil.mutListener.listen(28697) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28696) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28695) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28694) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) * 100) : (ListenerUtil.mutListener.listen(28699) ? ((ListenerUtil.mutListener.listen(28697) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28696) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28695) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28694) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) - 100) : (ListenerUtil.mutListener.listen(28698) ? ((ListenerUtil.mutListener.listen(28697) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28696) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28695) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28694) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) + 100) : ((ListenerUtil.mutListener.listen(28697) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28696) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28695) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28694) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) / 100)))));
                                }
                                if (!ListenerUtil.mutListener.listen(28703)) {
                                    appBarLayout.setLayoutParams(appBarLayoutParams);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(28693)) {
                                    coordinatorLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

                                        @Override
                                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                                            if (!ListenerUtil.mutListener.listen(28682)) {
                                                coordinatorLayout.removeOnLayoutChangeListener(this);
                                            }
                                            if (!ListenerUtil.mutListener.listen(28691)) {
                                                appBarLayoutParams.height = (ListenerUtil.mutListener.listen(28690) ? ((ListenerUtil.mutListener.listen(28686) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28685) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28684) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28683) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) % 100) : (ListenerUtil.mutListener.listen(28689) ? ((ListenerUtil.mutListener.listen(28686) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28685) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28684) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28683) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) * 100) : (ListenerUtil.mutListener.listen(28688) ? ((ListenerUtil.mutListener.listen(28686) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28685) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28684) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28683) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) - 100) : (ListenerUtil.mutListener.listen(28687) ? ((ListenerUtil.mutListener.listen(28686) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28685) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28684) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28683) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) + 100) : ((ListenerUtil.mutListener.listen(28686) ? (coordinatorLayout.getHeight() % APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28685) ? (coordinatorLayout.getHeight() / APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28684) ? (coordinatorLayout.getHeight() - APPBAR_HEIGHT_PERCENT) : (ListenerUtil.mutListener.listen(28683) ? (coordinatorLayout.getHeight() + APPBAR_HEIGHT_PERCENT) : (coordinatorLayout.getHeight() * APPBAR_HEIGHT_PERCENT))))) / 100)))));
                                            }
                                            if (!ListenerUtil.mutListener.listen(28692)) {
                                                appBarLayout.setLayoutParams(appBarLayoutParams);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28707)) {
            searchView.setVisibility(ConfigUtils.isLandscape(this) ? View.GONE : View.VISIBLE);
        }
    }

    private void requestPlacesSearch() {
        Intent intent = new Intent(this, LocationAutocompleteActivity.class);
        if (!ListenerUtil.mutListener.listen(28708)) {
            intent.putExtra(INTENT_DATA_LOCATION_LAT, getMapCenterPosition().getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(28709)) {
            intent.putExtra(INTENT_DATA_LOCATION_LNG, getMapCenterPosition().getLongitude());
        }
        if (!ListenerUtil.mutListener.listen(28710)) {
            startActivityForResult(intent, REQUEST_CODE_PLACES);
        }
        if (!ListenerUtil.mutListener.listen(28711)) {
            overridePendingTransition(R.anim.slide_in_right_short, R.anim.slide_out_left_short);
        }
    }

    @SuppressLint("MissingPermission")
    private void initMap() {
        if (!ListenerUtil.mutListener.listen(28714)) {
            if ((ListenerUtil.mutListener.listen(28712) ? (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) : (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(28713)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28726)) {
            mapView.getMapAsync(new OnMapReadyCallback() {

                @Override
                public void onMapReady(@NonNull MapboxMap mapboxMap1) {
                    if (!ListenerUtil.mutListener.listen(28715)) {
                        mapboxMap = mapboxMap1;
                    }
                    if (!ListenerUtil.mutListener.listen(28718)) {
                        mapboxMap.setStyle(new Style.Builder().fromUrl(MAP_STYLE_URL), new Style.OnStyleLoaded() {

                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                if (!ListenerUtil.mutListener.listen(28716)) {
                                    // Map is set up and the style has loaded. Now you can add data or make other mapView adjustments
                                    setupLocationComponent(style);
                                }
                                if (!ListenerUtil.mutListener.listen(28717)) {
                                    zoomToCenter();
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(28719)) {
                        mapboxMap.getUiSettings().setAttributionEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(28720)) {
                        mapboxMap.getUiSettings().setLogoEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(28721)) {
                        mapboxMap.setOnMarkerClickListener(marker -> {
                            {
                                long _loopCounter181 = 0;
                                for (Poi poi : places) {
                                    ListenerUtil.loopListener.listen("_loopCounter181", ++_loopCounter181);
                                    if (poi.getId() == Long.valueOf(marker.getSnippet())) {
                                        returnData(poi);
                                        return true;
                                    }
                                }
                            }
                            return false;
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(28723)) {
                        mapboxMap.addOnMoveListener(new MapboxMap.OnMoveListener() {

                            @Override
                            public void onMoveBegin(@NonNull MoveGestureDetector detector) {
                            }

                            @Override
                            public void onMove(@NonNull MoveGestureDetector detector) {
                            }

                            @Override
                            public void onMoveEnd(@NonNull MoveGestureDetector detector) {
                                if (!ListenerUtil.mutListener.listen(28722)) {
                                    updatePois();
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(28725)) {
                        mapboxMap.addOnCameraIdleListener(new MapboxMap.OnCameraIdleListener() {

                            @Override
                            public void onCameraIdle() {
                                if (!ListenerUtil.mutListener.listen(28724)) {
                                    updatePois();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    @SuppressLint("MissingPermission")
    private void setupLocationComponent(Style style) {
        if (!ListenerUtil.mutListener.listen(28727)) {
            logger.debug("setupLocationComponent");
        }
        if (!ListenerUtil.mutListener.listen(28728)) {
            if (style == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(28729)) {
            locationComponent = mapboxMap.getLocationComponent();
        }
        if (!ListenerUtil.mutListener.listen(28730)) {
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, style).build());
        }
        if (!ListenerUtil.mutListener.listen(28731)) {
            locationComponent.setCameraMode(CameraMode.TRACKING);
        }
        if (!ListenerUtil.mutListener.listen(28732)) {
            locationComponent.setRenderMode(RenderMode.COMPASS);
        }
        if (!ListenerUtil.mutListener.listen(28733)) {
            locationComponent.setLocationComponentEnabled(true);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void bindPlaces(List<Poi> newPois) {
        if (!ListenerUtil.mutListener.listen(28737)) {
            if (locationPickerAdapter == null) {
                if (!ListenerUtil.mutListener.listen(28734)) {
                    locationPickerAdapter = new LocationPickerAdapter(this);
                }
                if (!ListenerUtil.mutListener.listen(28735)) {
                    recyclerView.setAdapter(locationPickerAdapter);
                }
                if (!ListenerUtil.mutListener.listen(28736)) {
                    locationPickerAdapter.setOnClickItemListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28738)) {
            locationPickerAdapter.setPois(newPois);
        }
        if (!ListenerUtil.mutListener.listen(28757)) {
            new AsyncTask<Void, Void, List<MarkerOptions>>() {

                HashMap<Long, Marker> poiMarkerMap = new HashMap<>();

                List<Marker> markerList;

                long startTime;

                @Override
                protected void onPreExecute() {
                    if (!ListenerUtil.mutListener.listen(28739)) {
                        startTime = System.currentTimeMillis();
                    }
                    if (!ListenerUtil.mutListener.listen(28740)) {
                        markerList = mapboxMap.getMarkers();
                    }
                }

                @Override
                protected List<MarkerOptions> doInBackground(Void... voids) {
                    if (!ListenerUtil.mutListener.listen(28741)) {
                        startTime = System.currentTimeMillis();
                    }
                    if (!ListenerUtil.mutListener.listen(28743)) {
                        {
                            long _loopCounter182 = 0;
                            for (Marker marker : markerList) {
                                ListenerUtil.loopListener.listen("_loopCounter182", ++_loopCounter182);
                                if (!ListenerUtil.mutListener.listen(28742)) {
                                    poiMarkerMap.put(Long.valueOf(marker.getSnippet()), marker);
                                }
                            }
                        }
                    }
                    List<MarkerOptions> markerOptions = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(28749)) {
                        {
                            long _loopCounter183 = 0;
                            for (Poi poi : newPois) {
                                ListenerUtil.loopListener.listen("_loopCounter183", ++_loopCounter183);
                                if (!ListenerUtil.mutListener.listen(28748)) {
                                    if (!poiMarkerMap.containsKey(poi.getId())) {
                                        if (!ListenerUtil.mutListener.listen(28746)) {
                                            markerOptions.add(new MarkerOptions().position(poi.getLatLng()).title(poi.getName()).setIcon(LocationUtil.getMarkerIcon(LocationPickerActivity.this, poi)).setSnippet(String.valueOf(poi.getId())));
                                        }
                                        if (!ListenerUtil.mutListener.listen(28747)) {
                                            logger.debug("Add marker " + poi.getName());
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(28744)) {
                                            logger.debug("Retain marker " + poi.getName());
                                        }
                                        if (!ListenerUtil.mutListener.listen(28745)) {
                                            poiMarkerMap.remove(poi.getId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28750)) {
                        startTime = System.currentTimeMillis();
                    }
                    return markerOptions;
                }

                @Override
                protected void onPostExecute(List<MarkerOptions> markerOptionsList) {
                    if (!ListenerUtil.mutListener.listen(28751)) {
                        startTime = System.currentTimeMillis();
                    }
                    if (!ListenerUtil.mutListener.listen(28754)) {
                        {
                            long _loopCounter184 = 0;
                            for (Map.Entry<Long, Marker> marker : poiMarkerMap.entrySet()) {
                                ListenerUtil.loopListener.listen("_loopCounter184", ++_loopCounter184);
                                if (!ListenerUtil.mutListener.listen(28752)) {
                                    logger.debug("Remove marker " + marker.getValue().getTitle());
                                }
                                if (!ListenerUtil.mutListener.listen(28753)) {
                                    mapboxMap.removeMarker(marker.getValue());
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(28755)) {
                        startTime = System.currentTimeMillis();
                    }
                    if (!ListenerUtil.mutListener.listen(28756)) {
                        mapboxMap.addMarkers(markerOptionsList);
                    }
                }
            }.execute();
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(28758)) {
            logger.debug("onStart");
        }
        if (!ListenerUtil.mutListener.listen(28759)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(28761)) {
            if (mapView != null) {
                if (!ListenerUtil.mutListener.listen(28760)) {
                    mapView.onStart();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(28762)) {
            logger.debug("onResume");
        }
        if (!ListenerUtil.mutListener.listen(28763)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(28764)) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(28765)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(28766)) {
            mapView.onPause();
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(28767)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(28768)) {
            mapView.onStop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(28769)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(28770)) {
            mapView.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onLowMemory() {
        if (!ListenerUtil.mutListener.listen(28771)) {
            super.onLowMemory();
        }
        if (!ListenerUtil.mutListener.listen(28772)) {
            mapView.onLowMemory();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(28773)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(28774)) {
            mapView.onDestroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(28775)) {
            getMenuInflater().inflate(R.menu.activity_location_picker, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(28778)) {
            switch(item.getItemId()) {
                case android.R.id.home:
                    if (!ListenerUtil.mutListener.listen(28776)) {
                        finish();
                    }
                    break;
                case R.id.action_search:
                    if (!ListenerUtil.mutListener.listen(28777)) {
                        requestPlacesSearch();
                    }
                    break;
            }
        }
        return true;
    }

    private boolean checkLocationEnabled(LocationManager locationManager) {
        if (!ListenerUtil.mutListener.listen(28782)) {
            if ((ListenerUtil.mutListener.listen(28779) ? (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) : (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)))) {
                if (!ListenerUtil.mutListener.listen(28780)) {
                    setMapWithLocationFallback();
                }
                if (!ListenerUtil.mutListener.listen(28781)) {
                    GenericAlertDialog.newInstance(R.string.send_location, R.string.location_services_disabled, R.string.yes, R.string.no).show(getSupportFragmentManager(), DIALOG_TAG_ENABLE_LOCATION_SERVICES);
                }
                return false;
            }
        }
        return true;
    }

    private void returnData(Poi poi) {
        String name = poi != null ? poi.getName() : null;
        LatLng latLng = poi != null ? poi.getLatLng() : getMapCenterPosition();
        LocationPickerConfirmDialog dialog = LocationPickerConfirmDialog.newInstance(getString(R.string.lp_use_this_location), name, latLng, mapboxMap.getProjection().getVisibleRegion().latLngBounds);
        if (!ListenerUtil.mutListener.listen(28783)) {
            dialog.setData(poi);
        }
        if (!ListenerUtil.mutListener.listen(28784)) {
            dialog.show(getSupportFragmentManager(), DIALOG_TAG_CONFIRM_PLACE);
        }
    }

    private void reallyReturnData(Poi poi) {
        Intent data = new Intent();
        if (!ListenerUtil.mutListener.listen(28787)) {
            if (poi != null) {
                if (!ListenerUtil.mutListener.listen(28786)) {
                    IntentDataUtil.append(poi.getLatLng(), getString(R.string.app_name), poi.getName(), null, data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28785)) {
                    IntentDataUtil.append(getMapCenterPosition(), getString(R.string.app_name), null, null, data);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(28788)) {
            setResult(RESULT_OK, data);
        }
        if (!ListenerUtil.mutListener.listen(28789)) {
            finish();
        }
    }

    private LatLng getMapCenterPosition() {
        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
        if (!ListenerUtil.mutListener.listen(28791)) {
            if ((ListenerUtil.mutListener.listen(28790) ? (cameraPosition != null || cameraPosition.target != null) : (cameraPosition != null && cameraPosition.target != null))) {
                return new LatLng(cameraPosition.target.getLatitude(), cameraPosition.target.getLongitude());
            }
        }
        return new LatLng(0, 0);
    }

    @SuppressLint("StaticFieldLeak")
    private void updatePois() {
        if (!ListenerUtil.mutListener.listen(28792)) {
            logger.debug("updatePOIs");
        }
        LatLng latLng = getMapCenterPosition();
        if (!ListenerUtil.mutListener.listen(28805)) {
            if ((ListenerUtil.mutListener.listen(28797) ? (latLng.distanceTo(lastPosition) >= 30) : (ListenerUtil.mutListener.listen(28796) ? (latLng.distanceTo(lastPosition) <= 30) : (ListenerUtil.mutListener.listen(28795) ? (latLng.distanceTo(lastPosition) < 30) : (ListenerUtil.mutListener.listen(28794) ? (latLng.distanceTo(lastPosition) != 30) : (ListenerUtil.mutListener.listen(28793) ? (latLng.distanceTo(lastPosition) == 30) : (latLng.distanceTo(lastPosition) > 30))))))) {
                if (!ListenerUtil.mutListener.listen(28799)) {
                    logger.debug("...updating");
                }
                if (!ListenerUtil.mutListener.listen(28800)) {
                    lastPosition = latLng;
                }
                if (!ListenerUtil.mutListener.listen(28802)) {
                    if (nearbyPOITask != null) {
                        if (!ListenerUtil.mutListener.listen(28801)) {
                            nearbyPOITask.cancel(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(28803)) {
                    nearbyPOITask = new NearbyPOITask();
                }
                if (!ListenerUtil.mutListener.listen(28804)) {
                    nearbyPOITask.execute(latLng);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28798)) {
                    logger.debug("...no update necessary");
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void zoomToCenter() {
        if (!ListenerUtil.mutListener.listen(28811)) {
            if (checkLocationEnabled(locationManager)) {
                if (!ListenerUtil.mutListener.listen(28810)) {
                    if (locationComponent != null) {
                        Location location = locationComponent.getLastKnownLocation();
                        if (!ListenerUtil.mutListener.listen(28809)) {
                            if (location != null) {
                                if (!ListenerUtil.mutListener.listen(28808)) {
                                    moveCameraAndUpdatePOIs(new LatLng(location.getLatitude(), location.getLongitude()), true, -1);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(28806)) {
                                    setMapWithLocationFallback();
                                }
                                if (!ListenerUtil.mutListener.listen(28807)) {
                                    showLocationNotAvailable();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void showLocationNotAvailable() {
        if (!ListenerUtil.mutListener.listen(28812)) {
            RuntimeUtil.runOnUiThread(() -> Toast.makeText(LocationPickerActivity.this, R.string.unable_to_get_current_location, Toast.LENGTH_LONG).show());
        }
    }

    @Override
    public void onClick(Poi poi, View view) {
        if (!ListenerUtil.mutListener.listen(28813)) {
            returnData(poi);
        }
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(28814)) {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_CODE_LOCATION_SETTINGS);
        }
    }

    @Override
    public void onNo(String tag, Object data) {
    }

    @Override
    public void onOK(String tag, Object object) {
        if (!ListenerUtil.mutListener.listen(28815)) {
            reallyReturnData((Poi) object);
        }
    }

    @Override
    public void onCancel(String tag) {
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(28840)) {
            if ((ListenerUtil.mutListener.listen(28820) ? (requestCode >= REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(28819) ? (requestCode <= REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(28818) ? (requestCode > REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(28817) ? (requestCode < REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(28816) ? (requestCode != REQUEST_CODE_LOCATION_SETTINGS) : (requestCode == REQUEST_CODE_LOCATION_SETTINGS))))))) {
                if (!ListenerUtil.mutListener.listen(28839)) {
                    if (checkLocationEnabled(locationManager)) {
                        if (!ListenerUtil.mutListener.listen(28838)) {
                            // init map again as it was skipped first time around in onCreate() without location permissions
                            initMap();
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(28825) ? (requestCode >= REQUEST_CODE_PLACES) : (ListenerUtil.mutListener.listen(28824) ? (requestCode <= REQUEST_CODE_PLACES) : (ListenerUtil.mutListener.listen(28823) ? (requestCode > REQUEST_CODE_PLACES) : (ListenerUtil.mutListener.listen(28822) ? (requestCode < REQUEST_CODE_PLACES) : (ListenerUtil.mutListener.listen(28821) ? (requestCode != REQUEST_CODE_PLACES) : (requestCode == REQUEST_CODE_PLACES))))))) {
                if (!ListenerUtil.mutListener.listen(28837)) {
                    if (resultCode == RESULT_OK) {
                        if (!ListenerUtil.mutListener.listen(28827)) {
                            logger.debug("onActivityResult");
                        }
                        if (!ListenerUtil.mutListener.listen(28829)) {
                            if (locationComponent != null) {
                                if (!ListenerUtil.mutListener.listen(28828)) {
                                    locationComponent.setLocationComponentEnabled(false);
                                }
                            }
                        }
                        Location newLocation = IntentDataUtil.getLocation(data);
                        if (!ListenerUtil.mutListener.listen(28836)) {
                            if (mapboxMap != null) {
                                int zoom = (int) ((ListenerUtil.mutListener.listen(28834) ? (mapboxMap.getCameraPosition().zoom >= 12) : (ListenerUtil.mutListener.listen(28833) ? (mapboxMap.getCameraPosition().zoom <= 12) : (ListenerUtil.mutListener.listen(28832) ? (mapboxMap.getCameraPosition().zoom > 12) : (ListenerUtil.mutListener.listen(28831) ? (mapboxMap.getCameraPosition().zoom != 12) : (ListenerUtil.mutListener.listen(28830) ? (mapboxMap.getCameraPosition().zoom == 12) : (mapboxMap.getCameraPosition().zoom < 12)))))) ? 12 : mapboxMap.getCameraPosition().zoom);
                                if (!ListenerUtil.mutListener.listen(28835)) {
                                    moveCameraAndUpdatePOIs(new LatLng(newLocation.getLatitude(), newLocation.getLongitude()), false, zoom);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28826)) {
                    super.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    private void moveCameraAndUpdatePOIs(LatLng latLng, boolean animate, int zoomLevel) {
        long time = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(28841)) {
            logger.debug("moveCamera to " + latLng.toString());
        }
        if (!ListenerUtil.mutListener.listen(28842)) {
            mapboxMap.cancelTransitions();
        }
        if (!ListenerUtil.mutListener.listen(28851)) {
            mapboxMap.addOnCameraIdleListener(new MapboxMap.OnCameraIdleListener() {

                @Override
                public void onCameraIdle() {
                    if (!ListenerUtil.mutListener.listen(28843)) {
                        mapboxMap.removeOnCameraIdleListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(28850)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(28848)) {
                                    logger.debug("camera has been moved. Time in ms = " + ((ListenerUtil.mutListener.listen(28847) ? (System.currentTimeMillis() % time) : (ListenerUtil.mutListener.listen(28846) ? (System.currentTimeMillis() / time) : (ListenerUtil.mutListener.listen(28845) ? (System.currentTimeMillis() * time) : (ListenerUtil.mutListener.listen(28844) ? (System.currentTimeMillis() + time) : (System.currentTimeMillis() - time)))))));
                                }
                                if (!ListenerUtil.mutListener.listen(28849)) {
                                    updatePois();
                                }
                            }
                        });
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(28852)) {
            moveCamera(latLng, animate, zoomLevel);
        }
    }

    private void moveCamera(LatLng latLng, boolean animate, int zoomLevel) {
        CameraUpdate cameraUpdate = (ListenerUtil.mutListener.listen(28857) ? (zoomLevel >= -1) : (ListenerUtil.mutListener.listen(28856) ? (zoomLevel <= -1) : (ListenerUtil.mutListener.listen(28855) ? (zoomLevel > -1) : (ListenerUtil.mutListener.listen(28854) ? (zoomLevel < -1) : (ListenerUtil.mutListener.listen(28853) ? (zoomLevel == -1) : (zoomLevel != -1)))))) ? CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel) : CameraUpdateFactory.newLatLng(latLng);
        if (!ListenerUtil.mutListener.listen(28860)) {
            if (animate) {
                if (!ListenerUtil.mutListener.listen(28859)) {
                    mapboxMap.animateCamera(cameraUpdate);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(28858)) {
                    mapboxMap.moveCamera(cameraUpdate);
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(28861)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(28864)) {
            if (appBarLayout != null) {
                if (!ListenerUtil.mutListener.listen(28863)) {
                    appBarLayout.post(new Runnable() {

                        @Override
                        public void run() {
                            if (!ListenerUtil.mutListener.listen(28862)) {
                                adjustAppBarHeight();
                            }
                        }
                    });
                }
            }
        }
    }

    private void setMapWithLocationFallback() {
        if (!ListenerUtil.mutListener.listen(28865)) {
            mapView.post(() -> {
                // try to get a last location from gps and network provider, else update POIS around Zuerich
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location == null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                if (location == null) {
                    lastPosition = new LatLng(47.367302, 8.544616);
                } else {
                    lastPosition = new LatLng(location.getLatitude(), location.getLongitude());
                }
                moveCamera(lastPosition, true, 9);
                updatePois();
            });
        }
    }
}
