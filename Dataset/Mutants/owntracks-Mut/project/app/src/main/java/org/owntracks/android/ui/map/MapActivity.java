package org.owntracks.android.ui.map;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import org.greenrobot.eventbus.EventBus;
import org.owntracks.android.R;
import org.owntracks.android.data.repos.LocationRepo;
import org.owntracks.android.databinding.UiMapBinding;
import org.owntracks.android.geocoding.GeocoderProvider;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.services.BackgroundService;
import org.owntracks.android.services.LocationProcessor;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.support.ContactImageProvider;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.RequirementsChecker;
import org.owntracks.android.support.RunThingsOnOtherThreads;
import org.owntracks.android.support.widgets.BindingConversions;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.base.navigator.Navigator;
import org.owntracks.android.ui.welcome.WelcomeActivity;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MapActivity extends BaseActivity<UiMapBinding, MapMvvm.ViewModel<MapMvvm.View>> implements MapMvvm.View, View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener, OnMapReadyCallback, Observer {

    public static final String BUNDLE_KEY_CONTACT_ID = "BUNDLE_KEY_CONTACT_ID";

    private static final long ZOOM_LEVEL_STREET = 15;

    private final int PERMISSIONS_REQUEST_CODE = 1;

    private final Map<String, Marker> markers = new HashMap<>();

    private GoogleMap googleMap;

    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;

    private boolean isMapReady = false;

    private Menu mMenu;

    private FusedLocationProviderClient fusedLocationClient;

    LocationCallback locationRepoUpdaterCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (!ListenerUtil.mutListener.listen(1537)) {
                Timber.d("Foreground location result received: %s", locationResult);
            }
            if (!ListenerUtil.mutListener.listen(1538)) {
                locationRepo.setCurrentLocation(locationResult.getLastLocation());
            }
            if (!ListenerUtil.mutListener.listen(1539)) {
                super.onLocationResult(locationResult);
            }
        }
    };

    @Inject
    LocationRepo locationRepo;

    @Inject
    RunThingsOnOtherThreads runThingsOnOtherThreads;

    @Inject
    ContactImageProvider contactImageProvider;

    @Inject
    EventBus eventBus;

    @Inject
    GeocoderProvider geocoderProvider;

    @Inject
    CountingIdlingResource countingIdlingResource;

    @Inject
    Navigator navigator;

    @Inject
    RequirementsChecker requirementsChecker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1540)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1543)) {
            if (!preferences.isSetupCompleted()) {
                if (!ListenerUtil.mutListener.listen(1541)) {
                    navigator.startActivity(WelcomeActivity.class);
                }
                if (!ListenerUtil.mutListener.listen(1542)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1544)) {
            bindAndAttachContentView(R.layout.ui_map, savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1545)) {
            setSupportToolbar(this.binding.toolbar, false, true);
        }
        if (!ListenerUtil.mutListener.listen(1546)) {
            setDrawer(this.binding.toolbar);
        }
        // Workaround for Google Maps crash on Android 6
        try {
            if (!ListenerUtil.mutListener.listen(1549)) {
                binding.mapView.onCreate(savedInstanceState);
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1547)) {
                Timber.e(e, "Failed to bind map to view.");
            }
            if (!ListenerUtil.mutListener.listen(1548)) {
                isMapReady = false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1550)) {
            this.bottomSheetBehavior = BottomSheetBehavior.from(this.binding.bottomSheetLayout);
        }
        if (!ListenerUtil.mutListener.listen(1551)) {
            this.binding.contactPeek.contactRow.setOnClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1552)) {
            this.binding.contactPeek.contactRow.setOnLongClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1553)) {
            this.binding.moreButton.setOnClickListener(this::showPopupMenu);
        }
        if (!ListenerUtil.mutListener.listen(1554)) {
            setBottomSheetHidden();
        }
        AppBarLayout appBarLayout = this.binding.appBarLayout;
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        if (!ListenerUtil.mutListener.listen(1555)) {
            behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {

                @Override
                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                    return false;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1556)) {
            params.setBehavior(behavior);
        }
        if (!ListenerUtil.mutListener.listen(1557)) {
            viewModel.getContact().observe(this, this);
        }
        if (!ListenerUtil.mutListener.listen(1558)) {
            viewModel.getBottomSheetHidden().observe(this, o -> {
                if ((Boolean) o) {
                    setBottomSheetHidden();
                } else {
                    setBottomSheetCollapsed();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1559)) {
            viewModel.getCenter().observe(this, o -> {
                if (o != null) {
                    updateCamera((LatLng) o);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1560)) {
            checkAndRequestLocationPermissions();
        }
        if (!ListenerUtil.mutListener.listen(1561)) {
            Timber.v("starting BackgroundService");
        }
        if (!ListenerUtil.mutListener.listen(1569)) {
            if ((ListenerUtil.mutListener.listen(1566) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1565) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1564) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1563) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(1562) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                if (!ListenerUtil.mutListener.listen(1568)) {
                    startForegroundService((new Intent(this, BackgroundService.class)));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1567)) {
                    startService((new Intent(this, BackgroundService.class)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1570)) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        }
    }

    private void checkAndRequestLocationPermissions() {
        if (!ListenerUtil.mutListener.listen(1581)) {
            if (!requirementsChecker.isPermissionCheckPassed()) {
                if (!ListenerUtil.mutListener.listen(1580)) {
                    if ((ListenerUtil.mutListener.listen(1575) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1574) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1573) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1572) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.M) : (ListenerUtil.mutListener.listen(1571) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))))))) {
                        if (!ListenerUtil.mutListener.listen(1579)) {
                            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                                Activity currentActivity = this;
                                if (!ListenerUtil.mutListener.listen(1578)) {
                                    new AlertDialog.Builder(this).setCancelable(true).setMessage(R.string.permissions_description).setPositiveButton("OK", (dialog, which) -> ActivityCompat.requestPermissions(currentActivity, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSIONS_REQUEST_CODE)).show();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1577)) {
                                    ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSIONS_REQUEST_CODE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1576)) {
                            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSIONS_REQUEST_CODE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onChanged(@Nullable Object activeContact) {
        if (!ListenerUtil.mutListener.listen(1600)) {
            if (activeContact != null) {
                FusedContact c = (FusedContact) activeContact;
                if (!ListenerUtil.mutListener.listen(1582)) {
                    Timber.v("for contact: %s", c.getId());
                }
                if (!ListenerUtil.mutListener.listen(1583)) {
                    binding.contactPeek.name.setText(c.getFusedName());
                }
                if (!ListenerUtil.mutListener.listen(1599)) {
                    if (c.hasLocation()) {
                        if (!ListenerUtil.mutListener.listen(1586)) {
                            ContactImageProvider.setImageViewAsync(binding.contactPeek.image, c);
                        }
                        if (!ListenerUtil.mutListener.listen(1587)) {
                            geocoderProvider.resolve(c.getMessageLocation().getValue(), binding.contactPeek.location);
                        }
                        if (!ListenerUtil.mutListener.listen(1588)) {
                            BindingConversions.setRelativeTimeSpanString(binding.contactPeek.locationDate, c.getTst());
                        }
                        if (!ListenerUtil.mutListener.listen(1589)) {
                            binding.acc.setText(String.format(Locale.getDefault(), "%s m", c.getFusedLocationAccuracy()));
                        }
                        if (!ListenerUtil.mutListener.listen(1590)) {
                            binding.tid.setText(c.getTrackerId());
                        }
                        if (!ListenerUtil.mutListener.listen(1591)) {
                            binding.id.setText(c.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(1598)) {
                            if (viewModel.hasLocation()) {
                                if (!ListenerUtil.mutListener.listen(1594)) {
                                    binding.distance.setVisibility(View.VISIBLE);
                                }
                                if (!ListenerUtil.mutListener.listen(1595)) {
                                    binding.distanceLabel.setVisibility(View.VISIBLE);
                                }
                                float[] distance = new float[2];
                                if (!ListenerUtil.mutListener.listen(1596)) {
                                    Location.distanceBetween(viewModel.getCurrentLocation().latitude, viewModel.getCurrentLocation().longitude, c.getLatLng().latitude, c.getLatLng().longitude, distance);
                                }
                                if (!ListenerUtil.mutListener.listen(1597)) {
                                    binding.distance.setText(String.format(Locale.getDefault(), "%d m", Math.round(distance[0])));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(1592)) {
                                    binding.distance.setVisibility(View.GONE);
                                }
                                if (!ListenerUtil.mutListener.listen(1593)) {
                                    binding.distanceLabel.setVisibility(View.GONE);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1584)) {
                            binding.contactPeek.location.setText(R.string.na);
                        }
                        if (!ListenerUtil.mutListener.listen(1585)) {
                            binding.contactPeek.locationDate.setText(R.string.na);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(1601)) {
            super.onSaveInstanceState(bundle);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1603)) {
                binding.mapView.onSaveInstanceState(bundle);
            }
        } catch (Exception ignored) {
            if (!ListenerUtil.mutListener.listen(1602)) {
                isMapReady = false;
            }
        }
    }

    @Override
    public void onDestroy() {
        try {
            if (!ListenerUtil.mutListener.listen(1605)) {
                binding.mapView.onDestroy();
            }
        } catch (Exception ignored) {
            if (!ListenerUtil.mutListener.listen(1604)) {
                isMapReady = false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1606)) {
            super.onDestroy();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(1607)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1608)) {
            this.isMapReady = false;
        }
        try {
            if (!ListenerUtil.mutListener.listen(1611)) {
                binding.mapView.onResume();
            }
            if (!ListenerUtil.mutListener.listen(1618)) {
                if (googleMap == null) {
                    if (!ListenerUtil.mutListener.listen(1615)) {
                        Timber.v("map not ready. Running initDelayed()");
                    }
                    if (!ListenerUtil.mutListener.listen(1616)) {
                        this.isMapReady = false;
                    }
                    if (!ListenerUtil.mutListener.listen(1617)) {
                        initMapDelayed();
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1612)) {
                        Timber.v("map ready. Running onMapReady()");
                    }
                    if (!ListenerUtil.mutListener.listen(1613)) {
                        this.isMapReady = true;
                    }
                    if (!ListenerUtil.mutListener.listen(1614)) {
                        viewModel.onMapReady();
                    }
                }
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1609)) {
                Timber.e(e, "Not showing map due to crash in Google Maps library");
            }
            if (!ListenerUtil.mutListener.listen(1610)) {
                isMapReady = false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1619)) {
            handleIntentExtras(getIntent());
        }
        if (!ListenerUtil.mutListener.listen(1622)) {
            if ((ListenerUtil.mutListener.listen(1620) ? (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) : (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(1621)) {
                    checkAndRequestLocationPermissions();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1623)) {
            fusedLocationClient.requestLocationUpdates(new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(TimeUnit.SECONDS.toMillis(5)), locationRepoUpdaterCallback, null).addOnCompleteListener(task -> Timber.i("Requested foreground location updates. isSuccessful: %s isCancelled: %s", task.isSuccessful(), task.isCanceled()));
        }
        if (!ListenerUtil.mutListener.listen(1624)) {
            updateMonitoringModeMenu();
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(1625)) {
            super.onPause();
        }
        try {
            if (!ListenerUtil.mutListener.listen(1627)) {
                binding.mapView.onPause();
            }
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(1626)) {
                isMapReady = false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1628)) {
            fusedLocationClient.removeLocationUpdates(locationRepoUpdaterCallback).addOnCompleteListener(task -> Timber.i("Removed foreground location updates. isSuccessful: %s isCancelled: %s", task.isSuccessful(), task.isCanceled()));
        }
    }

    private void handleIntentExtras(Intent intent) {
        if (!ListenerUtil.mutListener.listen(1629)) {
            Timber.v("handleIntentExtras");
        }
        Bundle b = navigator.getExtrasBundle(intent);
        if (!ListenerUtil.mutListener.listen(1633)) {
            if (b != null) {
                if (!ListenerUtil.mutListener.listen(1630)) {
                    Timber.v("intent has extras from drawerProvider");
                }
                String contactId = b.getString(BUNDLE_KEY_CONTACT_ID);
                if (!ListenerUtil.mutListener.listen(1632)) {
                    if (contactId != null) {
                        if (!ListenerUtil.mutListener.listen(1631)) {
                            viewModel.restore(contactId);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onLowMemory() {
        if (!ListenerUtil.mutListener.listen(1634)) {
            super.onLowMemory();
        }
        try {
            if (!ListenerUtil.mutListener.listen(1636)) {
                binding.mapView.onLowMemory();
            }
        } catch (Exception ignored) {
            if (!ListenerUtil.mutListener.listen(1635)) {
                isMapReady = false;
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(1637)) {
            super.onNewIntent(intent);
        }
        if (!ListenerUtil.mutListener.listen(1638)) {
            handleIntentExtras(intent);
        }
        try {
            if (!ListenerUtil.mutListener.listen(1640)) {
                binding.mapView.onLowMemory();
            }
        } catch (Exception ignored) {
            if (!ListenerUtil.mutListener.listen(1639)) {
                isMapReady = false;
            }
        }
    }

    private void initMapDelayed() {
        if (!ListenerUtil.mutListener.listen(1641)) {
            isMapReady = false;
        }
        if (!ListenerUtil.mutListener.listen(1642)) {
            runThingsOnOtherThreads.postOnMainHandlerDelayed(this::initMap, 500);
        }
    }

    private void initMap() {
        if (!ListenerUtil.mutListener.listen(1643)) {
            isMapReady = false;
        }
        try {
            if (!ListenerUtil.mutListener.listen(1644)) {
                binding.mapView.getMapAsync(this);
            }
        } catch (Exception ignored) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(1645)) {
            inflater.inflate(R.menu.activity_map, menu);
        }
        if (!ListenerUtil.mutListener.listen(1646)) {
            this.mMenu = menu;
        }
        if (!ListenerUtil.mutListener.listen(1649)) {
            if (viewModel.hasLocation()) {
                if (!ListenerUtil.mutListener.listen(1648)) {
                    enableLocationMenus();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1647)) {
                    disableLocationMenus();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1650)) {
            updateMonitoringModeMenu();
        }
        return true;
    }

    public void updateMonitoringModeMenu() {
        if (!ListenerUtil.mutListener.listen(1651)) {
            if (this.mMenu == null) {
                return;
            }
        }
        MenuItem item = this.mMenu.findItem(R.id.menu_monitoring);
        if (!ListenerUtil.mutListener.listen(1660)) {
            switch(preferences.getMonitoring()) {
                case LocationProcessor.MONITORING_QUIET:
                    if (!ListenerUtil.mutListener.listen(1652)) {
                        item.setIcon(R.drawable.ic_baseline_stop_36);
                    }
                    if (!ListenerUtil.mutListener.listen(1653)) {
                        item.setTitle(R.string.monitoring_quiet);
                    }
                    break;
                case LocationProcessor.MONITORING_MANUAL:
                    if (!ListenerUtil.mutListener.listen(1654)) {
                        item.setIcon(R.drawable.ic_baseline_pause_36);
                    }
                    if (!ListenerUtil.mutListener.listen(1655)) {
                        item.setTitle(R.string.monitoring_manual);
                    }
                    break;
                case LocationProcessor.MONITORING_SIGNIFICANT:
                    if (!ListenerUtil.mutListener.listen(1656)) {
                        item.setIcon(R.drawable.ic_baseline_play_arrow_36);
                    }
                    if (!ListenerUtil.mutListener.listen(1657)) {
                        item.setTitle(R.string.monitoring_significant);
                    }
                    break;
                case LocationProcessor.MONITORING_MOVE:
                    if (!ListenerUtil.mutListener.listen(1658)) {
                        item.setIcon(R.drawable.ic_step_forward_2);
                    }
                    if (!ListenerUtil.mutListener.listen(1659)) {
                        item.setTitle(R.string.monitoring_move);
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(1665)) {
            if (itemId == R.id.menu_report) {
                if (!ListenerUtil.mutListener.listen(1664)) {
                    viewModel.sendLocation();
                }
                return true;
            } else if (itemId == R.id.menu_mylocation) {
                if (!ListenerUtil.mutListener.listen(1663)) {
                    viewModel.onMenuCenterDeviceClicked();
                }
                return true;
            } else if (itemId == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(1662)) {
                    finish();
                }
                return true;
            } else if (itemId == R.id.menu_monitoring) {
                if (!ListenerUtil.mutListener.listen(1661)) {
                    stepMonitoringModeMenu();
                }
            }
        }
        return false;
    }

    private void stepMonitoringModeMenu() {
        if (!ListenerUtil.mutListener.listen(1666)) {
            preferences.setMonitoringNext();
        }
        int newmode = preferences.getMonitoring();
        if (!ListenerUtil.mutListener.listen(1671)) {
            if (newmode == LocationProcessor.MONITORING_QUIET) {
                if (!ListenerUtil.mutListener.listen(1670)) {
                    Toast.makeText(this, R.string.monitoring_quiet, Toast.LENGTH_SHORT).show();
                }
            } else if (newmode == LocationProcessor.MONITORING_MANUAL) {
                if (!ListenerUtil.mutListener.listen(1669)) {
                    Toast.makeText(this, R.string.monitoring_manual, Toast.LENGTH_SHORT).show();
                }
            } else if (newmode == LocationProcessor.MONITORING_SIGNIFICANT) {
                if (!ListenerUtil.mutListener.listen(1668)) {
                    Toast.makeText(this, R.string.monitoring_significant, Toast.LENGTH_SHORT).show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1667)) {
                    Toast.makeText(this, R.string.monitoring_move, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void disableLocationMenus() {
        if (!ListenerUtil.mutListener.listen(1674)) {
            if (this.mMenu != null) {
                if (!ListenerUtil.mutListener.listen(1672)) {
                    this.mMenu.findItem(R.id.menu_mylocation).setEnabled(false).getIcon().setAlpha(128);
                }
                if (!ListenerUtil.mutListener.listen(1673)) {
                    this.mMenu.findItem(R.id.menu_report).setEnabled(false).getIcon().setAlpha(128);
                }
            }
        }
    }

    public void enableLocationMenus() {
        if (!ListenerUtil.mutListener.listen(1677)) {
            if (this.mMenu != null) {
                if (!ListenerUtil.mutListener.listen(1675)) {
                    this.mMenu.findItem(R.id.menu_mylocation).setEnabled(true).getIcon().setAlpha(255);
                }
                if (!ListenerUtil.mutListener.listen(1676)) {
                    this.mMenu.findItem(R.id.menu_report).setEnabled(true).getIcon().setAlpha(255);
                }
            }
        }
    }

    // MAP CALLBACKS
    @SuppressWarnings("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (!ListenerUtil.mutListener.listen(1678)) {
            this.googleMap = googleMap;
        }
        if (!ListenerUtil.mutListener.listen(1679)) {
            this.googleMap.setIndoorEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(1680)) {
            this.googleMap.setLocationSource(viewModel.getMapLocationSource());
        }
        if (!ListenerUtil.mutListener.listen(1681)) {
            this.googleMap.setMyLocationEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(1682)) {
            this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(1683)) {
            this.googleMap.setOnMapClickListener(viewModel.getOnMapClickListener());
        }
        if (!ListenerUtil.mutListener.listen(1684)) {
            this.googleMap.setOnCameraMoveStartedListener(viewModel.getOnMapCameraMoveStartedListener());
        }
        if (!ListenerUtil.mutListener.listen(1685)) {
            this.googleMap.setOnMarkerClickListener(viewModel.getOnMarkerClickListener());
        }
        if (!ListenerUtil.mutListener.listen(1686)) {
            this.googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1687)) {
            this.isMapReady = true;
        }
        if (!ListenerUtil.mutListener.listen(1688)) {
            viewModel.onMenuCenterDeviceClicked();
        }
        if (!ListenerUtil.mutListener.listen(1689)) {
            viewModel.onMapReady();
        }
    }

    private void updateCamera(@NonNull LatLng latLng) {
        if (!ListenerUtil.mutListener.listen(1691)) {
            if (isMapReady)
                if (!ListenerUtil.mutListener.listen(1690)) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL_STREET));
                }
        }
    }

    @Override
    public void clearMarkers() {
        if (!ListenerUtil.mutListener.listen(1693)) {
            if (isMapReady)
                if (!ListenerUtil.mutListener.listen(1692)) {
                    googleMap.clear();
                }
        }
        if (!ListenerUtil.mutListener.listen(1694)) {
            markers.clear();
        }
    }

    @Override
    public void removeMarker(@Nullable FusedContact contact) {
        if (!ListenerUtil.mutListener.listen(1695)) {
            if (contact == null)
                return;
        }
        Marker m = markers.get(contact.getId());
        if (!ListenerUtil.mutListener.listen(1697)) {
            if (m != null)
                if (!ListenerUtil.mutListener.listen(1696)) {
                    m.remove();
                }
        }
    }

    @Override
    public void updateMarker(@Nullable FusedContact contact) {
        if (!ListenerUtil.mutListener.listen(1702)) {
            if ((ListenerUtil.mutListener.listen(1699) ? ((ListenerUtil.mutListener.listen(1698) ? (contact == null && !contact.hasLocation()) : (contact == null || !contact.hasLocation())) && !isMapReady) : ((ListenerUtil.mutListener.listen(1698) ? (contact == null && !contact.hasLocation()) : (contact == null || !contact.hasLocation())) || !isMapReady))) {
                if (!ListenerUtil.mutListener.listen(1701)) {
                    Timber.v("unable to update marker. null:%s, location:%s, mapReady:%s", contact == null, (ListenerUtil.mutListener.listen(1700) ? (contact == null && contact.hasLocation()) : (contact == null || contact.hasLocation())), isMapReady);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1703)) {
            Timber.v("updating marker for contact: %s", contact.getId());
        }
        Marker marker = markers.get(contact.getId());
        if (!ListenerUtil.mutListener.listen(1711)) {
            if ((ListenerUtil.mutListener.listen(1704) ? (marker != null || marker.getTag() != null) : (marker != null && marker.getTag() != null))) {
                if (!ListenerUtil.mutListener.listen(1710)) {
                    marker.setPosition(contact.getLatLng());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1706)) {
                    // If a marker has been removed, its tag will be null. Doing anything with it will make it explode
                    if (marker != null) {
                        if (!ListenerUtil.mutListener.listen(1705)) {
                            markers.remove(contact.getId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1707)) {
                    marker = googleMap.addMarker(new MarkerOptions().position(contact.getLatLng()).anchor(0.5f, 0.5f).visible(false));
                }
                if (!ListenerUtil.mutListener.listen(1708)) {
                    marker.setTag(contact.getId());
                }
                if (!ListenerUtil.mutListener.listen(1709)) {
                    markers.put(contact.getId(), marker);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1712)) {
            contactImageProvider.setMarkerAsync(marker, contact);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_navigate:
                FusedContact c = viewModel.getActiveContact();
                if (!ListenerUtil.mutListener.listen(1718)) {
                    if ((ListenerUtil.mutListener.listen(1713) ? (c != null || c.hasLocation()) : (c != null && c.hasLocation()))) {
                        try {
                            LatLng l = c.getLatLng();
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + l.latitude + "," + l.longitude));
                            if (!ListenerUtil.mutListener.listen(1716)) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                            if (!ListenerUtil.mutListener.listen(1717)) {
                                startActivity(intent);
                            }
                        } catch (ActivityNotFoundException e) {
                            if (!ListenerUtil.mutListener.listen(1715)) {
                                Toast.makeText(this, getString(R.string.noNavigationApp), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1714)) {
                            Toast.makeText(this, getString(R.string.contactLocationUnknown), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                return true;
            case R.id.menu_clear:
                if (!ListenerUtil.mutListener.listen(1719)) {
                    viewModel.onClearContactClicked();
                }
            default:
                return false;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (!ListenerUtil.mutListener.listen(1720)) {
            viewModel.onBottomSheetLongClick();
        }
        return true;
    }

    @Override
    public void setBottomSheetExpanded() {
        if (!ListenerUtil.mutListener.listen(1721)) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    // BOTTOM SHEET CALLBACKS
    @Override
    public void onClick(View view) {
        if (!ListenerUtil.mutListener.listen(1722)) {
            viewModel.onBottomSheetClick();
        }
    }

    @Override
    public void setBottomSheetCollapsed() {
        if (!ListenerUtil.mutListener.listen(1723)) {
            Timber.v("vm contact: %s", binding.getVm().getActiveContact());
        }
        if (!ListenerUtil.mutListener.listen(1724)) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void setBottomSheetHidden() {
        if (!ListenerUtil.mutListener.listen(1725)) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(1727)) {
            if (mMenu != null)
                if (!ListenerUtil.mutListener.listen(1726)) {
                    mMenu.close();
                }
        }
    }

    private void showPopupMenu(View v) {
        // new PopupMenu(this, v);
        PopupMenu popupMenu = new PopupMenu(this, v, Gravity.START);
        if (!ListenerUtil.mutListener.listen(1728)) {
            popupMenu.getMenuInflater().inflate(R.menu.menu_popup_contacts, popupMenu.getMenu());
        }
        if (!ListenerUtil.mutListener.listen(1729)) {
            popupMenu.setOnMenuItemClickListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1731)) {
            if (preferences.getMode() == MessageProcessorEndpointHttp.MODE_ID)
                if (!ListenerUtil.mutListener.listen(1730)) {
                    popupMenu.getMenu().removeItem(R.id.menu_clear);
                }
        }
        if (!ListenerUtil.mutListener.listen(1732)) {
            popupMenu.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(1746)) {
            if ((ListenerUtil.mutListener.listen(1744) ? ((ListenerUtil.mutListener.listen(1743) ? ((ListenerUtil.mutListener.listen(1737) ? (requestCode >= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1736) ? (requestCode <= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1735) ? (requestCode > PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1734) ? (requestCode < PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1733) ? (requestCode != PERMISSIONS_REQUEST_CODE) : (requestCode == PERMISSIONS_REQUEST_CODE)))))) || (ListenerUtil.mutListener.listen(1742) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1741) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1740) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1739) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1738) ? (grantResults.length == 0) : (grantResults.length > 0))))))) : ((ListenerUtil.mutListener.listen(1737) ? (requestCode >= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1736) ? (requestCode <= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1735) ? (requestCode > PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1734) ? (requestCode < PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1733) ? (requestCode != PERMISSIONS_REQUEST_CODE) : (requestCode == PERMISSIONS_REQUEST_CODE)))))) && (ListenerUtil.mutListener.listen(1742) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1741) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1740) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1739) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1738) ? (grantResults.length == 0) : (grantResults.length > 0)))))))) || grantResults[0] == PackageManager.PERMISSION_GRANTED) : ((ListenerUtil.mutListener.listen(1743) ? ((ListenerUtil.mutListener.listen(1737) ? (requestCode >= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1736) ? (requestCode <= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1735) ? (requestCode > PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1734) ? (requestCode < PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1733) ? (requestCode != PERMISSIONS_REQUEST_CODE) : (requestCode == PERMISSIONS_REQUEST_CODE)))))) || (ListenerUtil.mutListener.listen(1742) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1741) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1740) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1739) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1738) ? (grantResults.length == 0) : (grantResults.length > 0))))))) : ((ListenerUtil.mutListener.listen(1737) ? (requestCode >= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1736) ? (requestCode <= PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1735) ? (requestCode > PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1734) ? (requestCode < PERMISSIONS_REQUEST_CODE) : (ListenerUtil.mutListener.listen(1733) ? (requestCode != PERMISSIONS_REQUEST_CODE) : (requestCode == PERMISSIONS_REQUEST_CODE)))))) && (ListenerUtil.mutListener.listen(1742) ? (grantResults.length >= 0) : (ListenerUtil.mutListener.listen(1741) ? (grantResults.length <= 0) : (ListenerUtil.mutListener.listen(1740) ? (grantResults.length < 0) : (ListenerUtil.mutListener.listen(1739) ? (grantResults.length != 0) : (ListenerUtil.mutListener.listen(1738) ? (grantResults.length == 0) : (grantResults.length > 0)))))))) && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(1745)) {
                    eventBus.postSticky(new Events.PermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION));
                }
            }
        }
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getLocationIdlingResource() {
        return binding.getVm().getLocationIdlingResource();
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getOutgoingQueueIdlingResource() {
        return countingIdlingResource;
    }
}
