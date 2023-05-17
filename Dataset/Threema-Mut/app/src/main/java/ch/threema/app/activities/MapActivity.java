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
package ch.threema.app.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
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
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import ch.threema.app.BuildConfig;
import ch.threema.app.R;
import ch.threema.app.ThreemaApplication;
import ch.threema.app.dialogs.GenericAlertDialog;
import ch.threema.app.locationpicker.NearbyPoiUtil;
import ch.threema.app.locationpicker.Poi;
import ch.threema.app.services.PreferenceService;
import ch.threema.app.ui.SingleToast;
import ch.threema.app.utils.BitmapUtil;
import ch.threema.app.utils.ConfigUtils;
import ch.threema.app.utils.GeoLocationUtil;
import ch.threema.app.utils.LocationUtil;
import ch.threema.app.utils.RuntimeUtil;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_LAT;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_LNG;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_NAME;
import static ch.threema.app.utils.IntentDataUtil.INTENT_DATA_LOCATION_PROVIDER;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MapActivity extends ThreemaActivity implements GenericAlertDialog.DialogClickListener {

    private static final Logger logger = LoggerFactory.getLogger(MapActivity.class);

    private static final String DIALOG_TAG_ENABLE_LOCATION_SERVICES = "lss";

    private static final String DIALOG_TAG_PRIVACY_POLICY_40_ACCEPT = "40acc";

    private static final int REQUEST_CODE_LOCATION_SETTINGS = 22229;

    private static final int PERMISSION_REQUEST_LOCATION = 49;

    private static final int MAX_POI_COUNT = 50;

    // URLs for Threema Map server
    public static final String MAP_STYLE_URL = "https://map.threema.ch/styles/streets/style.json";

    private MapView mapView;

    private MapboxMap mapboxMap;

    private FrameLayout parentView;

    private Style mapStyle;

    private LocationManager locationManager;

    private LocationComponent locationComponent;

    private LatLng markerPosition;

    private String markerName, markerProvider;

    private PreferenceService preferenceService;

    private int insetTop = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1478)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1480)) {
            if (BuildConfig.DEBUG) {
                if (!ListenerUtil.mutListener.listen(1479)) {
                    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1481)) {
            ConfigUtils.configureActivityTheme(this);
        }
        if (!ListenerUtil.mutListener.listen(1482)) {
            setContentView(R.layout.activity_map);
        }
        if (!ListenerUtil.mutListener.listen(1500)) {
            if ((ListenerUtil.mutListener.listen(1487) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1486) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1485) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1484) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1483) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                if (!ListenerUtil.mutListener.listen(1489)) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
                if (!ListenerUtil.mutListener.listen(1490)) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
                if (!ListenerUtil.mutListener.listen(1491)) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
                if (!ListenerUtil.mutListener.listen(1492)) {
                    getWindow().setStatusBarColor(Color.TRANSPARENT);
                }
                if (!ListenerUtil.mutListener.listen(1499)) {
                    if ((ListenerUtil.mutListener.listen(1497) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1496) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1495) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1494) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1493) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(1498)) {
                            // we want dark icons, i.e. a light status bar
                            getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1488)) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
        }
        try {
            if (!ListenerUtil.mutListener.listen(1503)) {
                preferenceService = ThreemaApplication.getServiceManager().getPreferenceService();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1501)) {
                logger.error("Exception", e);
            }
            if (!ListenerUtil.mutListener.listen(1502)) {
                finish();
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(1505)) {
            if (preferenceService == null) {
                if (!ListenerUtil.mutListener.listen(1504)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1508)) {
            if ((ListenerUtil.mutListener.listen(1506) ? (BuildConfig.DEBUG || preferenceService.getPoiServerHostOverride() != null) : (BuildConfig.DEBUG && preferenceService.getPoiServerHostOverride() != null))) {
                if (!ListenerUtil.mutListener.listen(1507)) {
                    Toast.makeText(this, "Using POI host override", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1509)) {
            parentView = findViewById(R.id.coordinator);
        }
        if (!ListenerUtil.mutListener.listen(1510)) {
            mapView = findViewById(R.id.map);
        }
        if (!ListenerUtil.mutListener.listen(1511)) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        if (!ListenerUtil.mutListener.listen(1513)) {
            if (locationManager == null) {
                if (!ListenerUtil.mutListener.listen(1512)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1514)) {
            mapView.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1516)) {
            ViewCompat.setOnApplyWindowInsetsListener(parentView, new OnApplyWindowInsetsListener() {

                @Override
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    if (!ListenerUtil.mutListener.listen(1515)) {
                        insetTop = insets.getSystemWindowInsetTop();
                    }
                    return insets;
                }
            });
        }
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(1517)) {
            markerPosition = new LatLng(intent.getDoubleExtra(INTENT_DATA_LOCATION_LAT, 0), intent.getDoubleExtra(INTENT_DATA_LOCATION_LNG, 0));
        }
        if (!ListenerUtil.mutListener.listen(1518)) {
            markerName = intent.getStringExtra(INTENT_DATA_LOCATION_NAME);
        }
        if (!ListenerUtil.mutListener.listen(1519)) {
            markerProvider = intent.getStringExtra(INTENT_DATA_LOCATION_PROVIDER);
        }
        if (!ListenerUtil.mutListener.listen(1528)) {
            if ((ListenerUtil.mutListener.listen(1524) ? (preferenceService.getPrivacyPolicyAcceptedVersion() >= 4.0f) : (ListenerUtil.mutListener.listen(1523) ? (preferenceService.getPrivacyPolicyAcceptedVersion() <= 4.0f) : (ListenerUtil.mutListener.listen(1522) ? (preferenceService.getPrivacyPolicyAcceptedVersion() > 4.0f) : (ListenerUtil.mutListener.listen(1521) ? (preferenceService.getPrivacyPolicyAcceptedVersion() != 4.0f) : (ListenerUtil.mutListener.listen(1520) ? (preferenceService.getPrivacyPolicyAcceptedVersion() == 4.0f) : (preferenceService.getPrivacyPolicyAcceptedVersion() < 4.0f))))))) {
                GenericAlertDialog alertDialog = GenericAlertDialog.newInstanceHtml(R.string.privacy_policy, getString(R.string.send_location_privacy_policy_v4_0, getString(R.string.app_name), ConfigUtils.getPrivacyPolicyURL(this)), R.string.prefs_title_accept_privacy_policy, R.string.cancel, false);
                if (!ListenerUtil.mutListener.listen(1527)) {
                    alertDialog.show(getSupportFragmentManager(), DIALOG_TAG_PRIVACY_POLICY_40_ACCEPT);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1525)) {
                    initUi();
                }
                if (!ListenerUtil.mutListener.listen(1526)) {
                    initMap();
                }
            }
        }
    }

    private void initUi() {
        if (!ListenerUtil.mutListener.listen(1529)) {
            findViewById(R.id.coordinator).setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1530)) {
            findViewById(R.id.center_map).setOnClickListener((it -> zoomToCenter()));
        }
        if (!ListenerUtil.mutListener.listen(1531)) {
            findViewById(R.id.open_chip).setOnClickListener((it -> openExternal()));
        }
        TextView locationName = findViewById(R.id.location_name);
        TextView locationCoordinates = findViewById(R.id.location_coordinates);
        if (!ListenerUtil.mutListener.listen(1532)) {
            locationName.setText(markerName);
        }
        if (!ListenerUtil.mutListener.listen(1533)) {
            locationCoordinates.setText(String.format(Locale.US, "%f, %f", markerPosition.getLatitude(), markerPosition.getLongitude()));
        }
    }

    private void openExternal() {
        // todo: address
        Intent intent = new Intent(Intent.ACTION_VIEW, GeoLocationUtil.getLocationUri(markerPosition.getLatitude(), markerPosition.getLongitude(), markerName, markerProvider));
        try {
            if (!ListenerUtil.mutListener.listen(1535)) {
                startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(1534)) {
                SingleToast.getInstance().showShortText(getString(R.string.no_app_for_location));
            }
        }
    }

    private void initMap() {
        if (!ListenerUtil.mutListener.listen(1553)) {
            mapView.getMapAsync(new OnMapReadyCallback() {

                @Override
                public void onMapReady(@NonNull MapboxMap mapboxMap1) {
                    if (!ListenerUtil.mutListener.listen(1536)) {
                        mapboxMap = mapboxMap1;
                    }
                    if (!ListenerUtil.mutListener.listen(1552)) {
                        mapboxMap.setStyle(new Style.Builder().fromUrl(MAP_STYLE_URL), new Style.OnStyleLoaded() {

                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                if (!ListenerUtil.mutListener.listen(1537)) {
                                    // Map is set up and the style has loaded. Now you can add data or make other mapView adjustments
                                    mapStyle = style;
                                }
                                if (!ListenerUtil.mutListener.listen(1539)) {
                                    if (checkLocationEnabled(locationManager)) {
                                        if (!ListenerUtil.mutListener.listen(1538)) {
                                            setupLocationComponent(style);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1540)) {
                                    mapboxMap.addMarker(getMarker(markerPosition, markerName, markerProvider));
                                }
                                if (!ListenerUtil.mutListener.listen(1547)) {
                                    if ((ListenerUtil.mutListener.listen(1545) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1544) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1543) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1542) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(1541) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                                        int marginTop = getResources().getDimensionPixelSize(R.dimen.map_compass_margin_top) + insetTop;
                                        int marginRight = getResources().getDimensionPixelSize(R.dimen.map_compass_margin_right);
                                        if (!ListenerUtil.mutListener.listen(1546)) {
                                            mapboxMap.getUiSettings().setCompassMargins(0, marginTop, marginRight, 0);
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(1548)) {
                                    moveCamera(markerPosition, false, -1);
                                }
                                if (!ListenerUtil.mutListener.listen(1550)) {
                                    mapView.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(1549)) {
                                                moveCamera(markerPosition, true, 15);
                                            }
                                        }
                                    }, 1200);
                                }
                                if (!ListenerUtil.mutListener.listen(1551)) {
                                    showNearbyPOIs(markerPosition);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void showNearbyPOIs(LatLng markerPosition) {
        if (!ListenerUtil.mutListener.listen(1564)) {
            new AsyncTask<LatLng, Void, List<MarkerOptions>>() {

                @Override
                protected List<MarkerOptions> doInBackground(LatLng... latLngs) {
                    LatLng latLng = latLngs[0];
                    List<Poi> pois = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(1554)) {
                        NearbyPoiUtil.getPOIs(latLng, pois, MAX_POI_COUNT, preferenceService);
                    }
                    List<MarkerOptions> markerOptions = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(1556)) {
                        {
                            long _loopCounter12 = 0;
                            for (Poi poi : pois) {
                                ListenerUtil.loopListener.listen("_loopCounter12", ++_loopCounter12);
                                if (!ListenerUtil.mutListener.listen(1555)) {
                                    markerOptions.add(new MarkerOptions().position(poi.getLatLng()).title(poi.getName()).setIcon(LocationUtil.getMarkerIcon(MapActivity.this, poi)).setSnippet(poi.getDescription()));
                                }
                            }
                        }
                    }
                    return markerOptions;
                }

                @Override
                protected void onPostExecute(List<MarkerOptions> markerOptions) {
                    if (!ListenerUtil.mutListener.listen(1563)) {
                        if ((ListenerUtil.mutListener.listen(1561) ? (markerOptions.size() >= 0) : (ListenerUtil.mutListener.listen(1560) ? (markerOptions.size() <= 0) : (ListenerUtil.mutListener.listen(1559) ? (markerOptions.size() < 0) : (ListenerUtil.mutListener.listen(1558) ? (markerOptions.size() != 0) : (ListenerUtil.mutListener.listen(1557) ? (markerOptions.size() == 0) : (markerOptions.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(1562)) {
                                mapboxMap.addMarkers(markerOptions);
                            }
                        }
                    }
                }
            }.execute(markerPosition);
        }
    }

    private void setupLocationComponent(Style style) {
        if (!ListenerUtil.mutListener.listen(1565)) {
            logger.debug("setupLocationComponent");
        }
        if (!ListenerUtil.mutListener.listen(1566)) {
            locationComponent = mapboxMap.getLocationComponent();
        }
        if (!ListenerUtil.mutListener.listen(1567)) {
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, style).build());
        }
        if (!ListenerUtil.mutListener.listen(1568)) {
            locationComponent.setCameraMode(CameraMode.NONE);
        }
        if (!ListenerUtil.mutListener.listen(1569)) {
            locationComponent.setRenderMode(RenderMode.COMPASS);
        }
        if (!ListenerUtil.mutListener.listen(1570)) {
            locationComponent.setLocationComponentEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        if (!ListenerUtil.mutListener.listen(1571)) {
            logger.debug("onStart");
        }
        if (!ListenerUtil.mutListener.listen(1572)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(1574)) {
            if (mapView != null) {
                if (!ListenerUtil.mutListener.listen(1573)) {
                    mapView.onStart();
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(1575)) {
            logger.debug("onResume");
        }
        if (!ListenerUtil.mutListener.listen(1576)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1577)) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(1578)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1579)) {
            mapView.onPause();
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(1580)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(1581)) {
            mapView.onStop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(1582)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(1583)) {
            mapView.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onLowMemory() {
        if (!ListenerUtil.mutListener.listen(1584)) {
            super.onLowMemory();
        }
        if (!ListenerUtil.mutListener.listen(1585)) {
            mapView.onLowMemory();
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(1586)) {
            super.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(1587)) {
            mapView.onDestroy();
        }
    }

    private boolean checkLocationEnabled(LocationManager locationManager) {
        if (!ListenerUtil.mutListener.listen(1590)) {
            if ((ListenerUtil.mutListener.listen(1588) ? (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) : (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED))) {
                return (ListenerUtil.mutListener.listen(1589) ? (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) : (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)));
            }
        }
        return false;
    }

    private boolean requestLocationEnabled(LocationManager locationManager) {
        if (!ListenerUtil.mutListener.listen(1595)) {
            if (ConfigUtils.requestLocationPermissions(this, null, PERMISSION_REQUEST_LOCATION)) {
                if (!ListenerUtil.mutListener.listen(1593)) {
                    if ((ListenerUtil.mutListener.listen(1591) ? (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) : (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)))) {
                        if (!ListenerUtil.mutListener.listen(1592)) {
                            setupLocationComponent(mapStyle);
                        }
                        return true;
                    }
                }
                if (!ListenerUtil.mutListener.listen(1594)) {
                    GenericAlertDialog.newInstance(R.string.your_location, R.string.location_services_disabled, R.string.yes, R.string.no).show(getSupportFragmentManager(), DIALOG_TAG_ENABLE_LOCATION_SERVICES);
                }
                return false;
            }
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    private void zoomToCenter() {
        if (!ListenerUtil.mutListener.listen(1599)) {
            if (requestLocationEnabled(locationManager)) {
                if (!ListenerUtil.mutListener.listen(1596)) {
                    locationComponent.setLocationComponentEnabled(true);
                }
                Location location = locationComponent.getLastKnownLocation();
                if (!ListenerUtil.mutListener.listen(1598)) {
                    // TODO: Wait for a fix if there's no last known location
                    if (location != null) {
                        if (!ListenerUtil.mutListener.listen(1597)) {
                            moveCamera(new LatLng(location.getLatitude(), location.getLongitude()), true, -1);
                        }
                    }
                }
            }
        }
    }

    private void moveCamera(LatLng latLng, boolean animate, int zoomLevel) {
        long time = System.currentTimeMillis();
        if (!ListenerUtil.mutListener.listen(1600)) {
            logger.debug("moveCamera to " + latLng.toString());
        }
        if (!ListenerUtil.mutListener.listen(1601)) {
            mapboxMap.cancelTransitions();
        }
        if (!ListenerUtil.mutListener.listen(1609)) {
            mapboxMap.addOnCameraIdleListener(new MapboxMap.OnCameraIdleListener() {

                @Override
                public void onCameraIdle() {
                    if (!ListenerUtil.mutListener.listen(1602)) {
                        mapboxMap.removeOnCameraIdleListener(this);
                    }
                    if (!ListenerUtil.mutListener.listen(1608)) {
                        RuntimeUtil.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (!ListenerUtil.mutListener.listen(1607)) {
                                    logger.debug("camera has been moved. Time in ms = " + ((ListenerUtil.mutListener.listen(1606) ? (System.currentTimeMillis() % time) : (ListenerUtil.mutListener.listen(1605) ? (System.currentTimeMillis() / time) : (ListenerUtil.mutListener.listen(1604) ? (System.currentTimeMillis() * time) : (ListenerUtil.mutListener.listen(1603) ? (System.currentTimeMillis() + time) : (System.currentTimeMillis() - time)))))));
                                }
                            }
                        });
                    }
                }
            });
        }
        CameraUpdate cameraUpdate = (ListenerUtil.mutListener.listen(1614) ? (zoomLevel >= -1) : (ListenerUtil.mutListener.listen(1613) ? (zoomLevel <= -1) : (ListenerUtil.mutListener.listen(1612) ? (zoomLevel > -1) : (ListenerUtil.mutListener.listen(1611) ? (zoomLevel < -1) : (ListenerUtil.mutListener.listen(1610) ? (zoomLevel == -1) : (zoomLevel != -1)))))) ? CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel) : CameraUpdateFactory.newLatLng(latLng);
        if (!ListenerUtil.mutListener.listen(1617)) {
            if (animate) {
                if (!ListenerUtil.mutListener.listen(1616)) {
                    mapboxMap.animateCamera(cameraUpdate);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1615)) {
                    mapboxMap.moveCamera(cameraUpdate);
                }
            }
        }
    }

    private MarkerOptions getMarker(LatLng latLng, String name, String provider) {
        Bitmap bitmap = BitmapUtil.getBitmapFromVectorDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_map_center_marker), null);
        return new MarkerOptions().position(latLng).title(name).setIcon(IconFactory.getInstance(this).fromBitmap(LocationUtil.moveMarker(bitmap))).setSnippet(provider);
    }

    @Override
    public void onYes(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1622)) {
            switch(tag) {
                case DIALOG_TAG_PRIVACY_POLICY_40_ACCEPT:
                    if (!ListenerUtil.mutListener.listen(1618)) {
                        preferenceService.setPrivacyPolicyAcceptedVersion(ConfigUtils.getAppVersionFloat(this));
                    }
                    if (!ListenerUtil.mutListener.listen(1619)) {
                        initUi();
                    }
                    if (!ListenerUtil.mutListener.listen(1620)) {
                        initMap();
                    }
                    break;
                case DIALOG_TAG_ENABLE_LOCATION_SERVICES:
                    if (!ListenerUtil.mutListener.listen(1621)) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_CODE_LOCATION_SETTINGS);
                    }
                    break;
            }
        }
    }

    @Override
    public void onNo(String tag, Object data) {
        if (!ListenerUtil.mutListener.listen(1624)) {
            switch(tag) {
                case DIALOG_TAG_PRIVACY_POLICY_40_ACCEPT:
                    if (!ListenerUtil.mutListener.listen(1623)) {
                        finish();
                    }
                    break;
                case DIALOG_TAG_ENABLE_LOCATION_SERVICES:
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(1632)) {
            if ((ListenerUtil.mutListener.listen(1629) ? (requestCode >= REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(1628) ? (requestCode <= REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(1627) ? (requestCode > REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(1626) ? (requestCode < REQUEST_CODE_LOCATION_SETTINGS) : (ListenerUtil.mutListener.listen(1625) ? (requestCode != REQUEST_CODE_LOCATION_SETTINGS) : (requestCode == REQUEST_CODE_LOCATION_SETTINGS))))))) {
                if (!ListenerUtil.mutListener.listen(1631)) {
                    zoomToCenter();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1630)) {
                    super.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(1646)) {
            if ((ListenerUtil.mutListener.listen(1638) ? ((ListenerUtil.mutListener.listen(1637) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1636) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1635) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1634) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1633) ? (grantResults.length == 0) : (grantResults.length > 0)))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(1637) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1636) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1635) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1634) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1633) ? (grantResults.length == 0) : (grantResults.length > 0)))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(1645)) {
                    if ((ListenerUtil.mutListener.listen(1643) ? (requestCode >= PERMISSION_REQUEST_LOCATION) : (ListenerUtil.mutListener.listen(1642) ? (requestCode <= PERMISSION_REQUEST_LOCATION) : (ListenerUtil.mutListener.listen(1641) ? (requestCode > PERMISSION_REQUEST_LOCATION) : (ListenerUtil.mutListener.listen(1640) ? (requestCode < PERMISSION_REQUEST_LOCATION) : (ListenerUtil.mutListener.listen(1639) ? (requestCode != PERMISSION_REQUEST_LOCATION) : (requestCode == PERMISSION_REQUEST_LOCATION))))))) {
                        if (!ListenerUtil.mutListener.listen(1644)) {
                            requestLocationEnabled(locationManager);
                        }
                    }
                }
            }
        }
    }
}
