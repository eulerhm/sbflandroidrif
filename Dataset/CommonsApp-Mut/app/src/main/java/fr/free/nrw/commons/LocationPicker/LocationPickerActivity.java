package fr.free.nrw.commons.LocationPicker;

import static fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailFragment.LAST_LOCATION;
import static fr.free.nrw.commons.upload.mediaDetails.UploadMediaDetailFragment.LAST_ZOOM;
import static fr.free.nrw.commons.utils.MapUtils.ZOOM_LEVEL;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.filepicker.Constants;
import fr.free.nrw.commons.kvstore.JsonKvStore;
import fr.free.nrw.commons.location.LocationPermissionsHelper;
import fr.free.nrw.commons.location.LocationPermissionsHelper.Dialog;
import fr.free.nrw.commons.location.LocationPermissionsHelper.LocationPermissionCallback;
import fr.free.nrw.commons.location.LocationServiceManager;
import fr.free.nrw.commons.theme.BaseActivity;
import fr.free.nrw.commons.utils.SystemThemeUtils;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.constants.GeoConstants;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.ScaleDiskOverlay;
import org.osmdroid.views.overlay.TilesOverlay;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Helps to pick location and return the result with an intent
 */
public class LocationPickerActivity extends BaseActivity implements LocationPermissionCallback {

    /**
     * cameraPosition : position of picker
     */
    private CameraPosition cameraPosition;

    /**
     * markerImage : picker image
     */
    private ImageView markerImage;

    /**
     * mapView : OSM Map
     */
    private org.osmdroid.views.MapView mapView;

    /**
     * tvAttribution : credit
     */
    private AppCompatTextView tvAttribution;

    /**
     * activity : activity key
     */
    private String activity;

    /**
     * modifyLocationButton : button for start editing location
     */
    Button modifyLocationButton;

    /**
     * showInMapButton : button for showing in map
     */
    TextView showInMapButton;

    /**
     * placeSelectedButton : fab for selecting location
     */
    FloatingActionButton placeSelectedButton;

    /**
     * fabCenterOnLocation: button for center on location;
     */
    FloatingActionButton fabCenterOnLocation;

    /**
     * shadow : imageview of shadow
     */
    private ImageView shadow;

    /**
     * largeToolbarText : textView of shadow
     */
    private TextView largeToolbarText;

    /**
     * smallToolbarText : textView of shadow
     */
    private TextView smallToolbarText;

    /**
     * applicationKvStore : for storing values
     */
    @Inject
    @Named("default_preferences")
    public JsonKvStore applicationKvStore;

    /**
     * isDarkTheme: for keeping a track of the device theme and modifying the map theme accordingly
     */
    @Inject
    SystemThemeUtils systemThemeUtils;

    private boolean isDarkTheme;

    private boolean moveToCurrentLocation;

    @Inject
    LocationServiceManager locationManager;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1764)) {
            getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        }
        if (!ListenerUtil.mutListener.listen(1765)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1766)) {
            isDarkTheme = systemThemeUtils.isDeviceInNightMode();
        }
        if (!ListenerUtil.mutListener.listen(1767)) {
            moveToCurrentLocation = false;
        }
        if (!ListenerUtil.mutListener.listen(1768)) {
            getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(1770)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(1769)) {
                    actionBar.hide();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1771)) {
            setContentView(R.layout.activity_location_picker);
        }
        if (!ListenerUtil.mutListener.listen(1774)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(1772)) {
                    cameraPosition = getIntent().getParcelableExtra(LocationPickerConstants.MAP_CAMERA_POSITION);
                }
                if (!ListenerUtil.mutListener.listen(1773)) {
                    activity = getIntent().getStringExtra(LocationPickerConstants.ACTIVITY_KEY);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1775)) {
            bindViews();
        }
        if (!ListenerUtil.mutListener.listen(1776)) {
            addBackButtonListener();
        }
        if (!ListenerUtil.mutListener.listen(1777)) {
            addPlaceSelectedButton();
        }
        if (!ListenerUtil.mutListener.listen(1778)) {
            addCredits();
        }
        if (!ListenerUtil.mutListener.listen(1779)) {
            getToolbarUI();
        }
        if (!ListenerUtil.mutListener.listen(1780)) {
            addCenterOnGPSButton();
        }
        if (!ListenerUtil.mutListener.listen(1781)) {
            org.osmdroid.config.Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        }
        if (!ListenerUtil.mutListener.listen(1782)) {
            mapView.setTileSource(TileSourceFactory.WIKIMEDIA);
        }
        if (!ListenerUtil.mutListener.listen(1783)) {
            mapView.setTilesScaledToDpi(true);
        }
        if (!ListenerUtil.mutListener.listen(1784)) {
            mapView.setMultiTouchControls(true);
        }
        if (!ListenerUtil.mutListener.listen(1785)) {
            org.osmdroid.config.Configuration.getInstance().getAdditionalHttpRequestProperties().put("Referer", "http://maps.wikimedia.org/");
        }
        if (!ListenerUtil.mutListener.listen(1786)) {
            mapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.NEVER);
        }
        if (!ListenerUtil.mutListener.listen(1787)) {
            mapView.getController().setZoom(ZOOM_LEVEL);
        }
        if (!ListenerUtil.mutListener.listen(1788)) {
            mapView.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (markerImage.getTranslationY() == 0) {
                        markerImage.animate().translationY(-75).setInterpolator(new OvershootInterpolator()).setDuration(250).start();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    markerImage.animate().translationY(0).setInterpolator(new OvershootInterpolator()).setDuration(250).start();
                }
                return false;
            });
        }
        if (!ListenerUtil.mutListener.listen(1798)) {
            if ("UploadActivity".equals(activity)) {
                if (!ListenerUtil.mutListener.listen(1789)) {
                    placeSelectedButton.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1790)) {
                    modifyLocationButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1791)) {
                    showInMapButton.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(1792)) {
                    largeToolbarText.setText(getResources().getString(R.string.image_location));
                }
                if (!ListenerUtil.mutListener.listen(1793)) {
                    smallToolbarText.setText(getResources().getString(R.string.check_whether_location_is_correct));
                }
                if (!ListenerUtil.mutListener.listen(1794)) {
                    fabCenterOnLocation.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1795)) {
                    markerImage.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(1796)) {
                    shadow.setVisibility(View.GONE);
                }
                assert cameraPosition.target != null;
                if (!ListenerUtil.mutListener.listen(1797)) {
                    showSelectedLocationMarker(new GeoPoint(cameraPosition.target.getLatitude(), cameraPosition.target.getLongitude()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1799)) {
            setupMapView();
        }
    }

    /**
     * For showing credits
     */
    private void addCredits() {
        if (!ListenerUtil.mutListener.listen(1800)) {
            tvAttribution.setText(Html.fromHtml(getString(R.string.map_attribution)));
        }
        if (!ListenerUtil.mutListener.listen(1801)) {
            tvAttribution.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    /**
     * For setting up Dark Theme
     */
    private void darkThemeSetup() {
        if (!ListenerUtil.mutListener.listen(1804)) {
            if (isDarkTheme) {
                if (!ListenerUtil.mutListener.listen(1802)) {
                    shadow.setColorFilter(Color.argb(255, 255, 255, 255));
                }
                if (!ListenerUtil.mutListener.listen(1803)) {
                    mapView.getOverlayManager().getTilesOverlay().setColorFilter(TilesOverlay.INVERT_COLORS);
                }
            }
        }
    }

    /**
     * Clicking back button destroy locationPickerActivity
     */
    private void addBackButtonListener() {
        final ImageView backButton = findViewById(R.id.maplibre_place_picker_toolbar_back_button);
        if (!ListenerUtil.mutListener.listen(1805)) {
            backButton.setOnClickListener(view -> finish());
        }
    }

    /**
     * Binds mapView and location picker icon
     */
    private void bindViews() {
        if (!ListenerUtil.mutListener.listen(1806)) {
            mapView = findViewById(R.id.map_view);
        }
        if (!ListenerUtil.mutListener.listen(1807)) {
            markerImage = findViewById(R.id.location_picker_image_view_marker);
        }
        if (!ListenerUtil.mutListener.listen(1808)) {
            tvAttribution = findViewById(R.id.tv_attribution);
        }
        if (!ListenerUtil.mutListener.listen(1809)) {
            modifyLocationButton = findViewById(R.id.modify_location);
        }
        if (!ListenerUtil.mutListener.listen(1810)) {
            showInMapButton = findViewById(R.id.show_in_map);
        }
        if (!ListenerUtil.mutListener.listen(1811)) {
            showInMapButton.setText(getResources().getString(R.string.show_in_map_app).toUpperCase());
        }
        if (!ListenerUtil.mutListener.listen(1812)) {
            shadow = findViewById(R.id.location_picker_image_view_shadow);
        }
    }

    /**
     * Gets toolbar color
     */
    private void getToolbarUI() {
        final ConstraintLayout toolbar = findViewById(R.id.location_picker_toolbar);
        if (!ListenerUtil.mutListener.listen(1813)) {
            largeToolbarText = findViewById(R.id.location_picker_toolbar_primary_text_view);
        }
        if (!ListenerUtil.mutListener.listen(1814)) {
            smallToolbarText = findViewById(R.id.location_picker_toolbar_secondary_text_view);
        }
        if (!ListenerUtil.mutListener.listen(1815)) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        }
    }

    private void setupMapView() {
        if (!ListenerUtil.mutListener.listen(1816)) {
            adjustCameraBasedOnOptions();
        }
        if (!ListenerUtil.mutListener.listen(1817)) {
            modifyLocationButton.setOnClickListener(v -> onClickModifyLocation());
        }
        if (!ListenerUtil.mutListener.listen(1818)) {
            showInMapButton.setOnClickListener(v -> showInMap());
        }
        if (!ListenerUtil.mutListener.listen(1819)) {
            darkThemeSetup();
        }
        if (!ListenerUtil.mutListener.listen(1820)) {
            requestLocationPermissions();
        }
    }

    /**
     * Handles onclick event of modifyLocationButton
     */
    private void onClickModifyLocation() {
        if (!ListenerUtil.mutListener.listen(1821)) {
            placeSelectedButton.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1822)) {
            modifyLocationButton.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1823)) {
            showInMapButton.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(1824)) {
            markerImage.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1825)) {
            shadow.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1826)) {
            largeToolbarText.setText(getResources().getString(R.string.choose_a_location));
        }
        if (!ListenerUtil.mutListener.listen(1827)) {
            smallToolbarText.setText(getResources().getString(R.string.pan_and_zoom_to_adjust));
        }
        if (!ListenerUtil.mutListener.listen(1828)) {
            fabCenterOnLocation.setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(1829)) {
            removeSelectedLocationMarker();
        }
        if (!ListenerUtil.mutListener.listen(1831)) {
            if (cameraPosition.target != null) {
                if (!ListenerUtil.mutListener.listen(1830)) {
                    mapView.getController().animateTo(new GeoPoint(cameraPosition.target.getLatitude(), cameraPosition.target.getLongitude()));
                }
            }
        }
    }

    /**
     * Show the location in map app
     */
    public void showInMap() {
        if (!ListenerUtil.mutListener.listen(1832)) {
            Utils.handleGeoCoordinates(this, new fr.free.nrw.commons.location.LatLng(mapView.getMapCenter().getLatitude(), mapView.getMapCenter().getLongitude(), 0.0f));
        }
    }

    /**
     * move the location to the current media coordinates
     */
    private void adjustCameraBasedOnOptions() {
        if (!ListenerUtil.mutListener.listen(1834)) {
            if (cameraPosition.target != null) {
                if (!ListenerUtil.mutListener.listen(1833)) {
                    mapView.getController().setCenter(new GeoPoint(cameraPosition.target.getLatitude(), cameraPosition.target.getLongitude()));
                }
            }
        }
    }

    /**
     * Select the preferable location
     */
    private void addPlaceSelectedButton() {
        if (!ListenerUtil.mutListener.listen(1835)) {
            placeSelectedButton = findViewById(R.id.location_chosen_button);
        }
        if (!ListenerUtil.mutListener.listen(1836)) {
            placeSelectedButton.setOnClickListener(view -> placeSelected());
        }
    }

    /**
     * Return the intent with required data
     */
    void placeSelected() {
        if (!ListenerUtil.mutListener.listen(1839)) {
            if (activity.equals("NoLocationUploadActivity")) {
                if (!ListenerUtil.mutListener.listen(1837)) {
                    applicationKvStore.putString(LAST_LOCATION, mapView.getMapCenter().getLatitude() + "," + mapView.getMapCenter().getLongitude());
                }
                if (!ListenerUtil.mutListener.listen(1838)) {
                    applicationKvStore.putString(LAST_ZOOM, mapView.getZoomLevel() + "");
                }
            }
        }
        final Intent returningIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(1840)) {
            returningIntent.putExtra(LocationPickerConstants.MAP_CAMERA_POSITION, new CameraPosition(new LatLng(mapView.getMapCenter().getLatitude(), mapView.getMapCenter().getLongitude()), 14f, 0, 0));
        }
        if (!ListenerUtil.mutListener.listen(1841)) {
            setResult(AppCompatActivity.RESULT_OK, returningIntent);
        }
        if (!ListenerUtil.mutListener.listen(1842)) {
            finish();
        }
    }

    /**
     * Center the camera on the last saved location
     */
    private void addCenterOnGPSButton() {
        if (!ListenerUtil.mutListener.listen(1843)) {
            fabCenterOnLocation = findViewById(R.id.center_on_gps);
        }
        if (!ListenerUtil.mutListener.listen(1844)) {
            fabCenterOnLocation.setOnClickListener(view -> {
                moveToCurrentLocation = true;
                requestLocationPermissions();
            });
        }
    }

    /**
     * Adds selected location marker on the map
     */
    private void showSelectedLocationMarker(GeoPoint point) {
        Drawable icon = ContextCompat.getDrawable(this, R.drawable.map_default_map_marker);
        Marker marker = new Marker(mapView);
        if (!ListenerUtil.mutListener.listen(1845)) {
            marker.setPosition(point);
        }
        if (!ListenerUtil.mutListener.listen(1846)) {
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        }
        if (!ListenerUtil.mutListener.listen(1847)) {
            marker.setIcon(icon);
        }
        if (!ListenerUtil.mutListener.listen(1848)) {
            marker.setInfoWindow(null);
        }
        if (!ListenerUtil.mutListener.listen(1849)) {
            mapView.getOverlays().add(marker);
        }
        if (!ListenerUtil.mutListener.listen(1850)) {
            mapView.invalidate();
        }
    }

    /**
     * Removes selected location marker from the map
     */
    private void removeSelectedLocationMarker() {
        List<Overlay> overlays = mapView.getOverlays();
        if (!ListenerUtil.mutListener.listen(1861)) {
            {
                long _loopCounter23 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1860) ? (i >= overlays.size()) : (ListenerUtil.mutListener.listen(1859) ? (i <= overlays.size()) : (ListenerUtil.mutListener.listen(1858) ? (i > overlays.size()) : (ListenerUtil.mutListener.listen(1857) ? (i != overlays.size()) : (ListenerUtil.mutListener.listen(1856) ? (i == overlays.size()) : (i < overlays.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                    if (!ListenerUtil.mutListener.listen(1855)) {
                        if (overlays.get(i) instanceof Marker) {
                            Marker item = (Marker) overlays.get(i);
                            if (!ListenerUtil.mutListener.listen(1854)) {
                                if ((ListenerUtil.mutListener.listen(1851) ? (cameraPosition.target.getLatitude() == item.getPosition().getLatitude() || cameraPosition.target.getLongitude() == item.getPosition().getLongitude()) : (cameraPosition.target.getLatitude() == item.getPosition().getLatitude() && cameraPosition.target.getLongitude() == item.getPosition().getLongitude()))) {
                                    if (!ListenerUtil.mutListener.listen(1852)) {
                                        mapView.getOverlays().remove(i);
                                    }
                                    if (!ListenerUtil.mutListener.listen(1853)) {
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

    /**
     * Center the map at user's current location
     */
    private void requestLocationPermissions() {
        LocationPermissionsHelper.Dialog locationAccessDialog = new Dialog(R.string.location_permission_title, R.string.upload_map_location_access);
        LocationPermissionsHelper.Dialog locationOffDialog = new Dialog(R.string.ask_to_turn_location_on, R.string.upload_map_location_access);
        LocationPermissionsHelper locationPermissionsHelper = new LocationPermissionsHelper(this, locationManager, this);
        if (!ListenerUtil.mutListener.listen(1862)) {
            locationPermissionsHelper.handleLocationPermissions(locationAccessDialog, locationOffDialog);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(1866)) {
            if ((ListenerUtil.mutListener.listen(1863) ? (requestCode == Constants.RequestCodes.LOCATION || grantResults[0] == PackageManager.PERMISSION_GRANTED) : (requestCode == Constants.RequestCodes.LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED))) {
                if (!ListenerUtil.mutListener.listen(1865)) {
                    onLocationPermissionGranted();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1864)) {
                    onLocationPermissionDenied("");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1867)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(1868)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(1869)) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(1870)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1871)) {
            mapView.onPause();
        }
    }

    @Override
    public void onLocationPermissionDenied(String toastMessage) {
    }

    @Override
    public void onLocationPermissionGranted() {
        fr.free.nrw.commons.location.LatLng currLocation = locationManager.getLastLocation();
        if (!ListenerUtil.mutListener.listen(1878)) {
            if (currLocation != null) {
                GeoPoint currLocationGeopoint = new GeoPoint(currLocation.getLatitude(), currLocation.getLongitude());
                if (!ListenerUtil.mutListener.listen(1872)) {
                    addLocationMarker(currLocationGeopoint);
                }
                if (!ListenerUtil.mutListener.listen(1876)) {
                    if (moveToCurrentLocation) {
                        if (!ListenerUtil.mutListener.listen(1873)) {
                            mapView.getController().setCenter(currLocationGeopoint);
                        }
                        if (!ListenerUtil.mutListener.listen(1874)) {
                            mapView.getController().animateTo(currLocationGeopoint);
                        }
                        if (!ListenerUtil.mutListener.listen(1875)) {
                            moveToCurrentLocation = false;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1877)) {
                    markerImage.setTranslationY(0);
                }
            }
        }
    }

    private void addLocationMarker(GeoPoint geoPoint) {
        if (!ListenerUtil.mutListener.listen(1880)) {
            if (moveToCurrentLocation) {
                if (!ListenerUtil.mutListener.listen(1879)) {
                    mapView.getOverlays().clear();
                }
            }
        }
        ScaleDiskOverlay diskOverlay = new ScaleDiskOverlay(this, geoPoint, 2000, GeoConstants.UnitOfMeasure.foot);
        Paint circlePaint = new Paint();
        if (!ListenerUtil.mutListener.listen(1881)) {
            circlePaint.setColor(Color.rgb(128, 128, 128));
        }
        if (!ListenerUtil.mutListener.listen(1882)) {
            circlePaint.setStyle(Paint.Style.STROKE);
        }
        if (!ListenerUtil.mutListener.listen(1883)) {
            circlePaint.setStrokeWidth(2f);
        }
        if (!ListenerUtil.mutListener.listen(1884)) {
            diskOverlay.setCirclePaint2(circlePaint);
        }
        Paint diskPaint = new Paint();
        if (!ListenerUtil.mutListener.listen(1885)) {
            diskPaint.setColor(Color.argb(40, 128, 128, 128));
        }
        if (!ListenerUtil.mutListener.listen(1886)) {
            diskPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        if (!ListenerUtil.mutListener.listen(1887)) {
            diskOverlay.setCirclePaint1(diskPaint);
        }
        if (!ListenerUtil.mutListener.listen(1888)) {
            diskOverlay.setDisplaySizeMin(900);
        }
        if (!ListenerUtil.mutListener.listen(1889)) {
            diskOverlay.setDisplaySizeMax(1700);
        }
        if (!ListenerUtil.mutListener.listen(1890)) {
            mapView.getOverlays().add(diskOverlay);
        }
        org.osmdroid.views.overlay.Marker startMarker = new org.osmdroid.views.overlay.Marker(mapView);
        if (!ListenerUtil.mutListener.listen(1891)) {
            startMarker.setPosition(geoPoint);
        }
        if (!ListenerUtil.mutListener.listen(1892)) {
            startMarker.setAnchor(org.osmdroid.views.overlay.Marker.ANCHOR_CENTER, org.osmdroid.views.overlay.Marker.ANCHOR_BOTTOM);
        }
        if (!ListenerUtil.mutListener.listen(1893)) {
            startMarker.setIcon(ContextCompat.getDrawable(this, R.drawable.current_location_marker));
        }
        if (!ListenerUtil.mutListener.listen(1894)) {
            startMarker.setTitle("Your Location");
        }
        if (!ListenerUtil.mutListener.listen(1895)) {
            startMarker.setTextLabelFontSize(24);
        }
        if (!ListenerUtil.mutListener.listen(1896)) {
            mapView.getOverlays().add(startMarker);
        }
    }
}
