/*
 * Copyright (C) 2012-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com),
 * Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.ui;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.request.ObaArrivalInfoResponse;
import org.onebusaway.android.map.MapModeController;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.map.googlemapsv2.BaseMapFragment;
import org.onebusaway.android.map.googlemapsv2.LayerInfo;
import org.onebusaway.android.region.ObaRegionsTask;
import org.onebusaway.android.report.ui.ReportActivity;
import org.onebusaway.android.travelbehavior.TravelBehaviorManager;
import org.onebusaway.android.travelbehavior.constants.TravelBehaviorConstants;
import org.onebusaway.android.travelbehavior.utils.TravelBehaviorUtils;
import org.onebusaway.android.tripservice.TripService;
import org.onebusaway.android.util.FragmentUtils;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PermissionUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.RegionUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
import org.onebusaway.android.util.UIUtils;
import org.opentripplanner.routing.bike_rental.BikeRentalStation;
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_ACTIVITY_FEED;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_HELP;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_MY_REMINDERS;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_NEARBY;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_OPEN_SOURCE;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_PAY_FARE;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_PINS;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_PLAN_TRIP;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_PROFILE;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_SEND_FEEDBACK;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_SETTINGS;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_SIGN_IN;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_STARRED_ROUTES;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NAVDRAWER_ITEM_STARRED_STOPS;
import static org.onebusaway.android.ui.NavigationDrawerFragment.NavigationDrawerCallbacks;
import static org.onebusaway.android.util.PermissionUtils.BACKGROUND_LOCATION_PERMISSION_REQUEST;
import static org.onebusaway.android.util.PermissionUtils.LOCATION_PERMISSIONS;
import static uk.co.markormesher.android_fab.FloatingActionButton.POSITION_BOTTOM;
import static uk.co.markormesher.android_fab.FloatingActionButton.POSITION_END;
import static uk.co.markormesher.android_fab.FloatingActionButton.POSITION_START;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class HomeActivity extends AppCompatActivity implements BaseMapFragment.OnFocusChangedListener, BaseMapFragment.OnProgressBarChangedListener, ArrivalsListFragment.Listener, NavigationDrawerCallbacks, ObaRegionsTask.Callback {

    interface SlidingPanelController {

        /**
         * Sets the height of the sliding panel in pixels
         *
         * @param heightInPixels height of panel in pixels
         */
        void setPanelHeightPixels(int heightInPixels);

        /**
         * Returns the current height of the sliding panel in pixels, or -1 if the panel isn't yet
         * initialized
         *
         * @return the current height of the sliding panel in pixels, or -1 if the panel isn't yet
         * initialized
         */
        int getPanelHeightPixels();
    }

    public static final String TWITTER_URL = "http://mobile.twitter.com/onebusaway";

    private static final String WHATS_NEW_VER = "whatsNewVer";

    private static final String CHECK_REGION_VER = "checkRegionVer";

    private static final int HELP_DIALOG = 1;

    private static final int WHATSNEW_DIALOG = 2;

    private static final int LEGEND_DIALOG = 3;

    // One week, in milliseconds
    private static final long REGION_UPDATE_THRESHOLD = 1000 * 60 * 60 * 24 * 7;

    private static final String TAG = "HomeActivity";

    WeakReference<AppCompatActivity> mActivityWeakRef;

    ArrivalsListFragment mArrivalsListFragment;

    ArrivalsListHeader mArrivalsListHeader;

    View mArrivalsListHeaderView;

    View mArrivalsListHeaderSubView;

    private FloatingActionButton mFabMyLocation;

    uk.co.markormesher.android_fab.FloatingActionButton mLayersFab;

    private static int MY_LOC_DEFAULT_BOTTOM_MARGIN;

    private static int LAYERS_FAB_DEFAULT_BOTTOM_MARGIN;

    // ms
    private static final int MY_LOC_BTN_ANIM_DURATION = 100;

    Animation mMyLocationAnimation;

    /**
     * GoogleApiClient being used for Location Services
     */
    protected GoogleApiClient mGoogleApiClient;

    // Bottom Sliding panel
    SlidingUpPanelLayout mSlidingPanel;

    public static final int BATTERY_OPTIMIZATIONS_PERMISSION_REQUEST = 111;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Currently selected navigation drawer position (so we don't unnecessarily swap fragments
     * if the same item is selected).  Initialized to -1 so the initial callback from
     * NavigationDrawerFragment always instantiates the fragments
     */
    private int mCurrentNavDrawerPosition = -1;

    /**
     * Fragments that can be selected as main content via the NavigationDrawer
     */
    MyStarredStopsFragment mMyStarredStopsFragment;

    MyStarredRoutesFragment mMyStarredRoutesFragment;

    BaseMapFragment mMapFragment;

    MyRemindersFragment mMyRemindersFragment;

    /**
     * Control which menu options are shown per fragment menu groups
     */
    private boolean mShowStarredStopsMenu = false;

    private boolean mShowStarredRoutesMenu = false;

    private boolean mShowArrivalsMenu = false;

    /**
     * Stop that has current focus on the map.  We retain a reference to the StopId,
     * since during rapid rotations its possible that a reference to a ObaStop object in
     * mFocusedStop can still be null, and we don't want to lose the state of which stopId is in
     * focus.  We also need access to the focused stop properties, hence why we also have
     * mFocusedStop
     */
    String mFocusedStopId = null;

    /**
     * Bike rental station ID that has the focus currently.
     */
    String mBikeRentalStationId = null;

    ObaStop mFocusedStop = null;

    ImageView mExpandCollapse = null;

    ProgressBar mMapProgressBar = null;

    boolean mLastMapProgressBarState = true;

    private static final String INITIAL_STARTUP = "initialStartup";

    boolean mInitialStartup = true;

    private FirebaseAnalytics mFirebaseAnalytics;

    private ActivityResultLauncher<String> travelBehaviorPermissionsLauncher;

    /**
     * Starts the MapActivity with a particular stop focused with the center of
     * the map at a particular point.
     *
     * @param context The context of the activity.
     * @param focusId The stop to focus.
     * @param lat     The latitude of the map center.
     * @param lon     The longitude of the map center.
     */
    public static void start(Context context, String focusId, double lat, double lon) {
        if (!ListenerUtil.mutListener.listen(3860)) {
            context.startActivity(makeIntent(context, focusId, lat, lon));
        }
    }

    /**
     * Starts the MapActivity with a particular stop focused with the center of
     * the map at a particular point.
     *
     * @param context The context of the activity.
     * @param stop    The stop to focus on.
     */
    public static void start(Context context, ObaStop stop) {
        if (!ListenerUtil.mutListener.listen(3861)) {
            context.startActivity(makeIntent(context, stop));
        }
    }

    /**
     * Starts the MapActivity in "RouteMode", which shows stops along a route,
     * and does not get new stops when the user pans the map.
     *
     * @param context The context of the activity.
     * @param routeId The route to show.
     */
    public static void start(Context context, String routeId) {
        if (!ListenerUtil.mutListener.listen(3862)) {
            context.startActivity(makeIntent(context, routeId));
        }
    }

    /**
     * Returns an intent that will start the MapActivity with a particular stop
     * focused with the center of the map at a particular point.
     *
     * @param context The context of the activity.
     * @param focusId The stop to focus.
     * @param lat     The latitude of the map center.
     * @param lon     The longitude of the map center.
     */
    public static Intent makeIntent(Context context, String focusId, double lat, double lon) {
        Intent myIntent = new Intent(context, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(3863)) {
            myIntent.putExtra(MapParams.STOP_ID, focusId);
        }
        if (!ListenerUtil.mutListener.listen(3864)) {
            myIntent.putExtra(MapParams.CENTER_LAT, lat);
        }
        if (!ListenerUtil.mutListener.listen(3865)) {
            myIntent.putExtra(MapParams.CENTER_LON, lon);
        }
        return myIntent;
    }

    /**
     * Returns an intent that will start the MapActivity with a particular stop
     * focused with the center of the map at a particular point.
     *
     * @param context The context of the activity.
     * @param stop    The stop to focus on.
     */
    public static Intent makeIntent(Context context, ObaStop stop) {
        Intent myIntent = new Intent(context, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(3866)) {
            myIntent.putExtra(MapParams.STOP_ID, stop.getId());
        }
        if (!ListenerUtil.mutListener.listen(3867)) {
            myIntent.putExtra(MapParams.STOP_NAME, stop.getName());
        }
        if (!ListenerUtil.mutListener.listen(3868)) {
            myIntent.putExtra(MapParams.STOP_CODE, stop.getStopCode());
        }
        if (!ListenerUtil.mutListener.listen(3869)) {
            myIntent.putExtra(MapParams.CENTER_LAT, stop.getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(3870)) {
            myIntent.putExtra(MapParams.CENTER_LON, stop.getLongitude());
        }
        return myIntent;
    }

    /**
     * Returns an intent that starts the MapActivity in "RouteMode", which shows
     * stops along a route, and does not get new stops when the user pans the
     * map.
     *
     * @param context The context of the activity.
     * @param routeId The route to show.
     */
    public static Intent makeIntent(Context context, String routeId) {
        Intent myIntent = new Intent(context, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(3871)) {
            myIntent.putExtra(MapParams.MODE, MapParams.MODE_ROUTE);
        }
        if (!ListenerUtil.mutListener.listen(3872)) {
            myIntent.putExtra(MapParams.ZOOM_TO_ROUTE, true);
        }
        if (!ListenerUtil.mutListener.listen(3873)) {
            myIntent.putExtra(MapParams.ROUTE_ID, routeId);
        }
        return myIntent;
    }

    SlidingPanelController mSlidingPanelController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3874)) {
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(3875)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3876)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        if (!ListenerUtil.mutListener.listen(3877)) {
            setContentView(R.layout.main);
        }
        if (!ListenerUtil.mutListener.listen(3878)) {
            mActivityWeakRef = new WeakReference<>(this);
        }
        if (!ListenerUtil.mutListener.listen(3879)) {
            mInitialStartup = Application.getPrefs().getBoolean(INITIAL_STARTUP, true);
        }
        if (!ListenerUtil.mutListener.listen(3880)) {
            setupNavigationDrawer();
        }
        if (!ListenerUtil.mutListener.listen(3881)) {
            setupSlidingPanel();
        }
        if (!ListenerUtil.mutListener.listen(3882)) {
            setupMapState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3883)) {
            setupLayersSpeedDial();
        }
        if (!ListenerUtil.mutListener.listen(3884)) {
            setupMyLocationButton();
        }
        if (!ListenerUtil.mutListener.listen(3885)) {
            setupZoomButtons();
        }
        if (!ListenerUtil.mutListener.listen(3886)) {
            setupGooglePlayServices();
        }
        if (!ListenerUtil.mutListener.listen(3887)) {
            setupPermissions(this);
        }
        if (!ListenerUtil.mutListener.listen(3888)) {
            UIUtils.setupActionBar(this);
        }
        if (!ListenerUtil.mutListener.listen(3889)) {
            new TravelBehaviorManager(this, getApplicationContext()).registerTravelBehaviorParticipant();
        }
        if (!ListenerUtil.mutListener.listen(3892)) {
            if ((ListenerUtil.mutListener.listen(3890) ? (!mInitialStartup && PermissionUtils.hasGrantedAtLeastOnePermission(this, LOCATION_PERMISSIONS)) : (!mInitialStartup || PermissionUtils.hasGrantedAtLeastOnePermission(this, LOCATION_PERMISSIONS)))) {
                if (!ListenerUtil.mutListener.listen(3891)) {
                    // Otherwise, wait for a permission callback from the BaseMapFragment before checking the region status
                    checkRegionStatus();
                }
            }
        }
        // Check to see if we should show the welcome tutorial
        Bundle b = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(3895)) {
            if (b != null) {
                if (!ListenerUtil.mutListener.listen(3894)) {
                    if (b.getBoolean(ShowcaseViewUtils.TUTORIAL_WELCOME)) {
                        if (!ListenerUtil.mutListener.listen(3893)) {
                            // Show the welcome tutorial
                            ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_WELCOME, this, null, false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(3896)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(3899)) {
            // Make sure GoogleApiClient is connected, if available
            if ((ListenerUtil.mutListener.listen(3897) ? (mGoogleApiClient != null || !mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && !mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(3898)) {
                    mGoogleApiClient.connect();
                }
            }
        }
        AccessibilityManager am = (AccessibilityManager) getSystemService(ACCESSIBILITY_SERVICE);
        Boolean isTalkBackEnabled = am.isTouchExplorationEnabled();
        if (!ListenerUtil.mutListener.listen(3900)) {
            ObaAnalytics.setAccessibility(mFirebaseAnalytics, isTalkBackEnabled);
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(3901)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(3904)) {
            // Make sure header has sliding panel state
            if ((ListenerUtil.mutListener.listen(3902) ? (mArrivalsListHeader != null || mSlidingPanel != null) : (mArrivalsListHeader != null && mSlidingPanel != null))) {
                if (!ListenerUtil.mutListener.listen(3903)) {
                    mArrivalsListHeader.setSlidingPanelCollapsed(isSlidingPanelCollapsed());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3907)) {
            // Check if the map zoom controls should be displayed
            if (mCurrentNavDrawerPosition == NAVDRAWER_ITEM_NEARBY) {
                if (!ListenerUtil.mutListener.listen(3906)) {
                    checkDisplayZoomControls();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3905)) {
                    showZoomControls(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3908)) {
            checkLeftHandMode();
        }
        if (!ListenerUtil.mutListener.listen(3909)) {
            updateLayersFab();
        }
        if (!ListenerUtil.mutListener.listen(3910)) {
            mFabMyLocation.requestLayout();
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(3911)) {
            ShowcaseViewUtils.hideShowcaseView();
        }
        if (!ListenerUtil.mutListener.listen(3912)) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(3915)) {
            // Tear down GoogleApiClient
            if ((ListenerUtil.mutListener.listen(3913) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(3914)) {
                    mGoogleApiClient.disconnect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3916)) {
            mLayersFab.closeSpeedDialMenu();
        }
        if (!ListenerUtil.mutListener.listen(3917)) {
            super.onStop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(3918)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(3923)) {
            if (mFocusedStopId != null) {
                if (!ListenerUtil.mutListener.listen(3919)) {
                    outState.putString(MapParams.STOP_ID, mFocusedStopId);
                }
                if (!ListenerUtil.mutListener.listen(3922)) {
                    if (mFocusedStop != null) {
                        if (!ListenerUtil.mutListener.listen(3920)) {
                            outState.putString(MapParams.STOP_CODE, mFocusedStop.getStopCode());
                        }
                        if (!ListenerUtil.mutListener.listen(3921)) {
                            outState.putString(MapParams.STOP_NAME, mFocusedStop.getName());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3925)) {
            if (mBikeRentalStationId != null) {
                if (!ListenerUtil.mutListener.listen(3924)) {
                    outState.putString(MapParams.BIKE_STATION_ID, mBikeRentalStationId);
                }
            }
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (!ListenerUtil.mutListener.listen(3926)) {
            goToNavDrawerItem(position);
        }
    }

    private void goToNavDrawerItem(int item) {
        if (!ListenerUtil.mutListener.listen(3955)) {
            // Update the main content by replacing fragments
            switch(item) {
                case NAVDRAWER_ITEM_STARRED_STOPS:
                    if (!ListenerUtil.mutListener.listen(3930)) {
                        if (mCurrentNavDrawerPosition != NAVDRAWER_ITEM_STARRED_STOPS) {
                            if (!ListenerUtil.mutListener.listen(3927)) {
                                showStarredStopsFragment();
                            }
                            if (!ListenerUtil.mutListener.listen(3928)) {
                                mCurrentNavDrawerPosition = item;
                            }
                            if (!ListenerUtil.mutListener.listen(3929)) {
                                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_star), null);
                            }
                        }
                    }
                    break;
                case NAVDRAWER_ITEM_STARRED_ROUTES:
                    if (!ListenerUtil.mutListener.listen(3934)) {
                        if (mCurrentNavDrawerPosition != NAVDRAWER_ITEM_STARRED_ROUTES) {
                            if (!ListenerUtil.mutListener.listen(3931)) {
                                showStarredRoutesFragment();
                            }
                            if (!ListenerUtil.mutListener.listen(3932)) {
                                mCurrentNavDrawerPosition = item;
                            }
                            if (!ListenerUtil.mutListener.listen(3933)) {
                                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_star), null);
                            }
                        }
                    }
                    break;
                // below values are deprecated; fall through to NAVDRAWER_ITEM_NEARBY
                case NAVDRAWER_ITEM_SIGN_IN:
                case NAVDRAWER_ITEM_PROFILE:
                case NAVDRAWER_ITEM_PINS:
                case NAVDRAWER_ITEM_ACTIVITY_FEED:
                case NAVDRAWER_ITEM_NEARBY:
                    if (!ListenerUtil.mutListener.listen(3938)) {
                        if (mCurrentNavDrawerPosition != NAVDRAWER_ITEM_NEARBY) {
                            if (!ListenerUtil.mutListener.listen(3935)) {
                                showMapFragment();
                            }
                            if (!ListenerUtil.mutListener.listen(3936)) {
                                mCurrentNavDrawerPosition = NAVDRAWER_ITEM_NEARBY;
                            }
                            if (!ListenerUtil.mutListener.listen(3937)) {
                                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_nearby), null);
                            }
                        }
                    }
                    break;
                case NAVDRAWER_ITEM_MY_REMINDERS:
                    if (!ListenerUtil.mutListener.listen(3942)) {
                        if (mCurrentNavDrawerPosition != NAVDRAWER_ITEM_MY_REMINDERS) {
                            if (!ListenerUtil.mutListener.listen(3939)) {
                                showMyRemindersFragment();
                            }
                            if (!ListenerUtil.mutListener.listen(3940)) {
                                mCurrentNavDrawerPosition = item;
                            }
                            if (!ListenerUtil.mutListener.listen(3941)) {
                                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_reminders), null);
                            }
                        }
                    }
                    break;
                case NAVDRAWER_ITEM_PLAN_TRIP:
                    Intent planTrip = new Intent(HomeActivity.this, TripPlanActivity.class);
                    if (!ListenerUtil.mutListener.listen(3943)) {
                        startActivity(planTrip);
                    }
                    if (!ListenerUtil.mutListener.listen(3944)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_trip_plan), null);
                    }
                    break;
                case NAVDRAWER_ITEM_PAY_FARE:
                    if (!ListenerUtil.mutListener.listen(3945)) {
                        UIUtils.launchPayMyFareApp(this);
                    }
                    break;
                case NAVDRAWER_ITEM_SETTINGS:
                    Intent preferences = new Intent(HomeActivity.this, PreferencesActivity.class);
                    if (!ListenerUtil.mutListener.listen(3946)) {
                        startActivity(preferences);
                    }
                    if (!ListenerUtil.mutListener.listen(3947)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_settings), null);
                    }
                    break;
                case NAVDRAWER_ITEM_HELP:
                    if (!ListenerUtil.mutListener.listen(3948)) {
                        showDialog(HELP_DIALOG);
                    }
                    if (!ListenerUtil.mutListener.listen(3949)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_help), null);
                    }
                    break;
                case NAVDRAWER_ITEM_SEND_FEEDBACK:
                    if (!ListenerUtil.mutListener.listen(3950)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_feedback), null);
                    }
                    if (!ListenerUtil.mutListener.listen(3951)) {
                        goToSendFeedBack();
                    }
                    break;
                case NAVDRAWER_ITEM_OPEN_SOURCE:
                    if (!ListenerUtil.mutListener.listen(3952)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_open_source), null);
                    }
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    if (!ListenerUtil.mutListener.listen(3953)) {
                        i.setData(Uri.parse(getString(R.string.open_source_github)));
                    }
                    if (!ListenerUtil.mutListener.listen(3954)) {
                        startActivity(i);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(3956)) {
            invalidateOptionsMenu();
        }
    }

    private void handleNearbySelection() {
    }

    private void showMapFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(3957)) {
            /**
             * Hide everything that shouldn't be shown
             */
            hideStarredRoutesFragment();
        }
        if (!ListenerUtil.mutListener.listen(3958)) {
            hideStarredStopsFragment();
        }
        if (!ListenerUtil.mutListener.listen(3959)) {
            hideReminderFragment();
        }
        if (!ListenerUtil.mutListener.listen(3960)) {
            mShowStarredStopsMenu = false;
        }
        if (!ListenerUtil.mutListener.listen(3967)) {
            /**
             * Show fragment (we use show instead of replace to keep the map state)
             */
            if (mMapFragment == null) {
                if (!ListenerUtil.mutListener.listen(3961)) {
                    // First check to see if an instance of BaseMapFragment already exists (see #356)
                    mMapFragment = (BaseMapFragment) fm.findFragmentByTag(BaseMapFragment.TAG);
                }
                if (!ListenerUtil.mutListener.listen(3966)) {
                    if (mMapFragment == null) {
                        if (!ListenerUtil.mutListener.listen(3962)) {
                            // No existing fragment was found, so create a new one
                            Log.d(TAG, "Creating new BaseMapFragment");
                        }
                        if (!ListenerUtil.mutListener.listen(3963)) {
                            mMapFragment = BaseMapFragment.newInstance();
                        }
                        if (!ListenerUtil.mutListener.listen(3964)) {
                            mMapFragment.setOnLocationPermissionResultListener(result -> {
                                if (mInitialStartup) {
                                    // (they'll be asked to manually pick region if they denied)
                                    mInitialStartup = false;
                                    PreferenceUtils.saveBoolean(INITIAL_STARTUP, false);
                                    checkRegionStatus();
                                }
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(3965)) {
                            fm.beginTransaction().add(R.id.main_fragment_container, mMapFragment, BaseMapFragment.TAG).commit();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3968)) {
            // Register listener for map focus callbacks
            mMapFragment.setOnFocusChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(3969)) {
            mMapFragment.setOnProgressBarChangedListener(this);
        }
        if (!ListenerUtil.mutListener.listen(3970)) {
            getSupportFragmentManager().beginTransaction().show(mMapFragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(3971)) {
            showFloatingActionButtons();
        }
        if (!ListenerUtil.mutListener.listen(3973)) {
            if (mLastMapProgressBarState) {
                if (!ListenerUtil.mutListener.listen(3972)) {
                    showMapProgressBar();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3974)) {
            mShowArrivalsMenu = true;
        }
        if (!ListenerUtil.mutListener.listen(3977)) {
            if ((ListenerUtil.mutListener.listen(3975) ? (mFocusedStopId != null || mSlidingPanel != null) : (mFocusedStopId != null && mSlidingPanel != null))) {
                if (!ListenerUtil.mutListener.listen(3976)) {
                    // if we've focused on a stop, then show the panel that was previously hidden
                    mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3978)) {
            setTitle(getResources().getString(R.string.navdrawer_item_nearby));
        }
        if (!ListenerUtil.mutListener.listen(3979)) {
            checkDisplayZoomControls();
        }
    }

    private void showStarredStopsFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(3980)) {
            /**
             * Hide everything that shouldn't be shown
             */
            hideFloatingActionButtons();
        }
        if (!ListenerUtil.mutListener.listen(3981)) {
            hideMapProgressBar();
        }
        if (!ListenerUtil.mutListener.listen(3982)) {
            hideMapFragment();
        }
        if (!ListenerUtil.mutListener.listen(3983)) {
            hideReminderFragment();
        }
        if (!ListenerUtil.mutListener.listen(3984)) {
            hideStarredRoutesFragment();
        }
        if (!ListenerUtil.mutListener.listen(3985)) {
            hideSlidingPanel();
        }
        if (!ListenerUtil.mutListener.listen(3986)) {
            mShowArrivalsMenu = false;
        }
        if (!ListenerUtil.mutListener.listen(3987)) {
            showZoomControls(false);
        }
        if (!ListenerUtil.mutListener.listen(3988)) {
            /**
             * Show fragment (we use show instead of replace to keep the map state)
             */
            mShowStarredStopsMenu = true;
        }
        if (!ListenerUtil.mutListener.listen(3994)) {
            if (mMyStarredStopsFragment == null) {
                if (!ListenerUtil.mutListener.listen(3989)) {
                    // First check to see if an instance of MyStarredStopsFragment already exists (see #356)
                    mMyStarredStopsFragment = (MyStarredStopsFragment) fm.findFragmentByTag(MyStarredStopsFragment.TAG);
                }
                if (!ListenerUtil.mutListener.listen(3993)) {
                    if (mMyStarredStopsFragment == null) {
                        if (!ListenerUtil.mutListener.listen(3990)) {
                            // No existing fragment was found, so create a new one
                            Log.d(TAG, "Creating new MyStarredStopsFragment");
                        }
                        if (!ListenerUtil.mutListener.listen(3991)) {
                            mMyStarredStopsFragment = new MyStarredStopsFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(3992)) {
                            fm.beginTransaction().add(R.id.main_fragment_container, mMyStarredStopsFragment, MyStarredStopsFragment.TAG).commit();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3995)) {
            fm.beginTransaction().show(mMyStarredStopsFragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(3996)) {
            setTitle(getResources().getString(R.string.navdrawer_item_starred_stops));
        }
    }

    private void showStarredRoutesFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(3997)) {
            /**
             * Hide everything that shouldn't be shown
             */
            hideFloatingActionButtons();
        }
        if (!ListenerUtil.mutListener.listen(3998)) {
            hideMapProgressBar();
        }
        if (!ListenerUtil.mutListener.listen(3999)) {
            hideMapFragment();
        }
        if (!ListenerUtil.mutListener.listen(4000)) {
            hideReminderFragment();
        }
        if (!ListenerUtil.mutListener.listen(4001)) {
            hideSlidingPanel();
        }
        if (!ListenerUtil.mutListener.listen(4002)) {
            hideStarredStopsFragment();
        }
        if (!ListenerUtil.mutListener.listen(4003)) {
            mShowArrivalsMenu = false;
        }
        if (!ListenerUtil.mutListener.listen(4004)) {
            showZoomControls(false);
        }
        if (!ListenerUtil.mutListener.listen(4005)) {
            /**
             * Show fragment (we use show instead of replace to keep the map state)
             */
            mShowStarredRoutesMenu = true;
        }
        if (!ListenerUtil.mutListener.listen(4011)) {
            if (mMyStarredRoutesFragment == null) {
                if (!ListenerUtil.mutListener.listen(4006)) {
                    // First check to see if an instance of MyStarredRoutesFragment already exists
                    mMyStarredRoutesFragment = (MyStarredRoutesFragment) fm.findFragmentByTag(MyStarredRoutesFragment.TAG);
                }
                if (!ListenerUtil.mutListener.listen(4010)) {
                    if (mMyStarredRoutesFragment == null) {
                        if (!ListenerUtil.mutListener.listen(4007)) {
                            // No existing fragment was found, so create a new one
                            Log.d(TAG, "Creating new MyStarredRoutesFragment");
                        }
                        if (!ListenerUtil.mutListener.listen(4008)) {
                            mMyStarredRoutesFragment = new MyStarredRoutesFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(4009)) {
                            fm.beginTransaction().add(R.id.main_fragment_container, mMyStarredRoutesFragment, MyStarredRoutesFragment.TAG).commit();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4012)) {
            fm.beginTransaction().show(mMyStarredRoutesFragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(4013)) {
            setTitle(getResources().getString(R.string.navdrawer_item_starred_routes));
        }
    }

    private void showMyRemindersFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(4014)) {
            /**
             * Hide everything that shouldn't be shown
             */
            hideFloatingActionButtons();
        }
        if (!ListenerUtil.mutListener.listen(4015)) {
            hideMapProgressBar();
        }
        if (!ListenerUtil.mutListener.listen(4016)) {
            hideStarredRoutesFragment();
        }
        if (!ListenerUtil.mutListener.listen(4017)) {
            hideStarredStopsFragment();
        }
        if (!ListenerUtil.mutListener.listen(4018)) {
            hideMapFragment();
        }
        if (!ListenerUtil.mutListener.listen(4019)) {
            hideSlidingPanel();
        }
        if (!ListenerUtil.mutListener.listen(4020)) {
            mShowArrivalsMenu = false;
        }
        if (!ListenerUtil.mutListener.listen(4021)) {
            mShowStarredStopsMenu = false;
        }
        if (!ListenerUtil.mutListener.listen(4022)) {
            showZoomControls(false);
        }
        if (!ListenerUtil.mutListener.listen(4028)) {
            /**
             * Show fragment (we use show instead of replace to keep the map state)
             */
            if (mMyRemindersFragment == null) {
                if (!ListenerUtil.mutListener.listen(4023)) {
                    // First check to see if an instance of MyRemindersFragment already exists (see #356)
                    mMyRemindersFragment = (MyRemindersFragment) fm.findFragmentByTag(MyRemindersFragment.TAG);
                }
                if (!ListenerUtil.mutListener.listen(4027)) {
                    if (mMyRemindersFragment == null) {
                        if (!ListenerUtil.mutListener.listen(4024)) {
                            // No existing fragment was found, so create a new one
                            Log.d(TAG, "Creating new MyRemindersFragment");
                        }
                        if (!ListenerUtil.mutListener.listen(4025)) {
                            mMyRemindersFragment = new MyRemindersFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(4026)) {
                            fm.beginTransaction().add(R.id.main_fragment_container, mMyRemindersFragment, MyRemindersFragment.TAG).commit();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4029)) {
            fm.beginTransaction().show(mMyRemindersFragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(4030)) {
            setTitle(getResources().getString(R.string.navdrawer_item_my_reminders));
        }
    }

    private void hideMapFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(4031)) {
            mMapFragment = (BaseMapFragment) fm.findFragmentByTag(BaseMapFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(4034)) {
            if ((ListenerUtil.mutListener.listen(4032) ? (mMapFragment != null || !mMapFragment.isHidden()) : (mMapFragment != null && !mMapFragment.isHidden()))) {
                if (!ListenerUtil.mutListener.listen(4033)) {
                    fm.beginTransaction().hide(mMapFragment).commit();
                }
            }
        }
    }

    private void hideStarredStopsFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(4035)) {
            mMyStarredStopsFragment = (MyStarredStopsFragment) fm.findFragmentByTag(MyStarredStopsFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(4038)) {
            if ((ListenerUtil.mutListener.listen(4036) ? (mMyStarredStopsFragment != null || !mMyStarredStopsFragment.isHidden()) : (mMyStarredStopsFragment != null && !mMyStarredStopsFragment.isHidden()))) {
                if (!ListenerUtil.mutListener.listen(4037)) {
                    fm.beginTransaction().hide(mMyStarredStopsFragment).commit();
                }
            }
        }
    }

    private void hideStarredRoutesFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(4039)) {
            mMyStarredRoutesFragment = (MyStarredRoutesFragment) fm.findFragmentByTag(MyStarredRoutesFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(4042)) {
            if ((ListenerUtil.mutListener.listen(4040) ? (mMyStarredRoutesFragment != null || !mMyStarredRoutesFragment.isHidden()) : (mMyStarredRoutesFragment != null && !mMyStarredRoutesFragment.isHidden()))) {
                if (!ListenerUtil.mutListener.listen(4041)) {
                    fm.beginTransaction().hide(mMyStarredRoutesFragment).commit();
                }
            }
        }
    }

    private void hideReminderFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(4043)) {
            mMyRemindersFragment = (MyRemindersFragment) fm.findFragmentByTag(MyRemindersFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(4046)) {
            if ((ListenerUtil.mutListener.listen(4044) ? (mMyRemindersFragment != null || !mMyRemindersFragment.isHidden()) : (mMyRemindersFragment != null && !mMyRemindersFragment.isHidden()))) {
                if (!ListenerUtil.mutListener.listen(4045)) {
                    fm.beginTransaction().hide(mMyRemindersFragment).commit();
                }
            }
        }
    }

    private void hideSlidingPanel() {
        if (!ListenerUtil.mutListener.listen(4048)) {
            if (mSlidingPanel != null) {
                if (!ListenerUtil.mutListener.listen(4047)) {
                    mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4049)) {
            getMenuInflater().inflate(R.menu.main_options, menu);
        }
        if (!ListenerUtil.mutListener.listen(4050)) {
            UIUtils.setupSearch(this, menu);
        }
        if (!ListenerUtil.mutListener.listen(4051)) {
            // Initialize fragment menu visibility here, so we don't have overlap between the various fragments
            setupOptionsMenu(menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4052)) {
            super.onPrepareOptionsMenu(menu);
        }
        if (!ListenerUtil.mutListener.listen(4053)) {
            // Manage fragment menu visibility here, so we don't have overlap between the various fragments
            setupOptionsMenu(menu);
        }
        return true;
    }

    private void setupOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(4054)) {
            menu.setGroupVisible(R.id.main_options_menu_group, true);
        }
        if (!ListenerUtil.mutListener.listen(4055)) {
            menu.setGroupVisible(R.id.arrival_list_menu_group, mShowArrivalsMenu);
        }
        if (!ListenerUtil.mutListener.listen(4056)) {
            menu.setGroupVisible(R.id.starred_stop_menu_group, mShowStarredStopsMenu);
        }
        if (!ListenerUtil.mutListener.listen(4057)) {
            menu.setGroupVisible(R.id.starred_route_menu_group, mShowStarredRoutesMenu);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(4058)) {
            Log.d(TAG, "onOptionsItemSelected");
        }
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(4063)) {
            if (id == R.id.search) {
                if (!ListenerUtil.mutListener.listen(4061)) {
                    onSearchRequested();
                }
                if (!ListenerUtil.mutListener.listen(4062)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_search_box), null);
                }
                return true;
            } else if (id == R.id.recent_stops_routes) {
                if (!ListenerUtil.mutListener.listen(4059)) {
                    ShowcaseViewUtils.doNotShowTutorial(ShowcaseViewUtils.TUTORIAL_RECENT_STOPS_ROUTES);
                }
                Intent myIntent = new Intent(this, MyRecentStopsAndRoutesActivity.class);
                if (!ListenerUtil.mutListener.listen(4060)) {
                    startActivity(myIntent);
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (!ListenerUtil.mutListener.listen(4064)) {
            switch(id) {
                case HELP_DIALOG:
                    return createHelpDialog();
                case WHATSNEW_DIALOG:
                    return createWhatsNewDialog();
                case LEGEND_DIALOG:
                    return createLegendDialog();
            }
        }
        return super.onCreateDialog(id);
    }

    @SuppressWarnings("deprecation")
    private Dialog createHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!ListenerUtil.mutListener.listen(4065)) {
            builder.setTitle(R.string.main_help_title);
        }
        // If a custom API URL is set, hide Contact Us, as we don't have a contact email to use
        int options;
        if (TextUtils.isEmpty(Application.get().getCustomApiUrl())) {
            options = R.array.main_help_options;
        } else {
            // Hide "Contact Us"
            options = R.array.main_help_options_no_contact_us;
        }
        if (!ListenerUtil.mutListener.listen(4078)) {
            builder.setItems(options, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(4077)) {
                        switch(which) {
                            case 0:
                                if (!ListenerUtil.mutListener.listen(4066)) {
                                    ShowcaseViewUtils.resetAllTutorials(HomeActivity.this);
                                }
                                if (!ListenerUtil.mutListener.listen(4067)) {
                                    NavHelp.goHome(HomeActivity.this, true);
                                }
                                break;
                            case 1:
                                if (!ListenerUtil.mutListener.listen(4068)) {
                                    showDialog(LEGEND_DIALOG);
                                }
                                break;
                            case 2:
                                if (!ListenerUtil.mutListener.listen(4069)) {
                                    showDialog(WHATSNEW_DIALOG);
                                }
                                break;
                            case 3:
                                if (!ListenerUtil.mutListener.listen(4070)) {
                                    AgenciesActivity.start(HomeActivity.this);
                                }
                                break;
                            case 4:
                                String twitterUrl = TWITTER_URL;
                                if (!ListenerUtil.mutListener.listen(4073)) {
                                    if ((ListenerUtil.mutListener.listen(4071) ? (Application.get().getCurrentRegion() != null || !TextUtils.isEmpty(Application.get().getCurrentRegion().getTwitterUrl())) : (Application.get().getCurrentRegion() != null && !TextUtils.isEmpty(Application.get().getCurrentRegion().getTwitterUrl())))) {
                                        if (!ListenerUtil.mutListener.listen(4072)) {
                                            twitterUrl = Application.get().getCurrentRegion().getTwitterUrl();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(4074)) {
                                    UIUtils.goToUrl(HomeActivity.this, twitterUrl);
                                }
                                if (!ListenerUtil.mutListener.listen(4075)) {
                                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_twitter), null);
                                }
                                break;
                            case 5:
                                if (!ListenerUtil.mutListener.listen(4076)) {
                                    // Contact us
                                    goToSendFeedBack();
                                }
                                break;
                        }
                    }
                }
            });
        }
        return builder.create();
    }

    @SuppressWarnings("deprecation")
    private Dialog createWhatsNewDialog() {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.whats_new_dialog, null);
        if (!ListenerUtil.mutListener.listen(4079)) {
            textView.setText(R.string.main_help_whatsnew);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!ListenerUtil.mutListener.listen(4080)) {
            builder.setTitle(R.string.main_help_whatsnew_title);
        }
        if (!ListenerUtil.mutListener.listen(4081)) {
            builder.setIcon(R.mipmap.ic_launcher);
        }
        if (!ListenerUtil.mutListener.listen(4082)) {
            builder.setView(textView);
        }
        if (!ListenerUtil.mutListener.listen(4083)) {
            builder.setNeutralButton(R.string.main_help_close, (dialog, which) -> dismissDialog(WHATSNEW_DIALOG));
        }
        if (!ListenerUtil.mutListener.listen(4086)) {
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    boolean showOptOut = Application.getPrefs().getBoolean(ShowcaseViewUtils.TUTORIAL_OPT_OUT_DIALOG, true);
                    if (!ListenerUtil.mutListener.listen(4085)) {
                        if (showOptOut) {
                            if (!ListenerUtil.mutListener.listen(4084)) {
                                ShowcaseViewUtils.showOptOutDialog(HomeActivity.this);
                            }
                        }
                    }
                }
            });
        }
        return builder.create();
    }

    @SuppressWarnings("deprecation")
    private Dialog createLegendDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!ListenerUtil.mutListener.listen(4087)) {
            builder.setTitle(R.string.main_help_legend_title);
        }
        Resources resources = getResources();
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View legendDialogView = inflater.inflate(R.layout.legend_dialog, null);
        final float etaTextFontSize = 30;
        // On time view
        View etaAndMin = legendDialogView.findViewById(R.id.eta_view_ontime);
        GradientDrawable d1 = (GradientDrawable) etaAndMin.getBackground();
        if (!ListenerUtil.mutListener.listen(4088)) {
            d1.setColor(resources.getColor(R.color.stop_info_ontime));
        }
        if (!ListenerUtil.mutListener.listen(4089)) {
            etaAndMin.findViewById(R.id.eta_realtime_indicator).setVisibility(View.VISIBLE);
        }
        TextView etaTextView = etaAndMin.findViewById(R.id.eta);
        if (!ListenerUtil.mutListener.listen(4090)) {
            etaTextView.setTextSize(etaTextFontSize);
        }
        if (!ListenerUtil.mutListener.listen(4091)) {
            etaTextView.setText("5");
        }
        if (!ListenerUtil.mutListener.listen(4092)) {
            // Early View
            etaAndMin = legendDialogView.findViewById(R.id.eta_view_early);
        }
        if (!ListenerUtil.mutListener.listen(4093)) {
            d1 = (GradientDrawable) etaAndMin.getBackground();
        }
        if (!ListenerUtil.mutListener.listen(4094)) {
            d1.setColor(resources.getColor(R.color.stop_info_early));
        }
        if (!ListenerUtil.mutListener.listen(4095)) {
            etaAndMin.findViewById(R.id.eta_realtime_indicator).setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4096)) {
            etaTextView = etaAndMin.findViewById(R.id.eta);
        }
        if (!ListenerUtil.mutListener.listen(4097)) {
            etaTextView.setTextSize(etaTextFontSize);
        }
        if (!ListenerUtil.mutListener.listen(4098)) {
            etaTextView.setText("5");
        }
        if (!ListenerUtil.mutListener.listen(4099)) {
            // Delayed View
            etaAndMin = legendDialogView.findViewById(R.id.eta_view_delayed);
        }
        if (!ListenerUtil.mutListener.listen(4100)) {
            d1 = (GradientDrawable) etaAndMin.getBackground();
        }
        if (!ListenerUtil.mutListener.listen(4101)) {
            d1.setColor(resources.getColor(R.color.stop_info_delayed));
        }
        if (!ListenerUtil.mutListener.listen(4102)) {
            etaAndMin.findViewById(R.id.eta_realtime_indicator).setVisibility(View.VISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4103)) {
            etaTextView = etaAndMin.findViewById(R.id.eta);
        }
        if (!ListenerUtil.mutListener.listen(4104)) {
            etaTextView.setTextSize(etaTextFontSize);
        }
        if (!ListenerUtil.mutListener.listen(4105)) {
            etaTextView.setText("5");
        }
        if (!ListenerUtil.mutListener.listen(4106)) {
            // Scheduled View
            etaAndMin = legendDialogView.findViewById(R.id.eta_view_scheduled);
        }
        if (!ListenerUtil.mutListener.listen(4107)) {
            d1 = (GradientDrawable) etaAndMin.getBackground();
        }
        if (!ListenerUtil.mutListener.listen(4108)) {
            d1.setColor(resources.getColor(R.color.stop_info_scheduled_time));
        }
        if (!ListenerUtil.mutListener.listen(4109)) {
            etaAndMin.findViewById(R.id.eta_realtime_indicator).setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4110)) {
            etaTextView = etaAndMin.findViewById(R.id.eta);
        }
        if (!ListenerUtil.mutListener.listen(4111)) {
            etaTextView.setTextSize(etaTextFontSize);
        }
        if (!ListenerUtil.mutListener.listen(4112)) {
            etaTextView.setText("5");
        }
        if (!ListenerUtil.mutListener.listen(4113)) {
            // Canceled View
            etaAndMin = legendDialogView.findViewById(R.id.eta_view_canceled);
        }
        if (!ListenerUtil.mutListener.listen(4114)) {
            d1 = (GradientDrawable) etaAndMin.getBackground();
        }
        if (!ListenerUtil.mutListener.listen(4115)) {
            d1.setColor(resources.getColor(R.color.stop_info_scheduled_time));
        }
        if (!ListenerUtil.mutListener.listen(4116)) {
            etaAndMin.findViewById(R.id.eta_realtime_indicator).setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(4117)) {
            etaTextView = etaAndMin.findViewById(R.id.eta);
        }
        if (!ListenerUtil.mutListener.listen(4118)) {
            etaTextView.setTextSize(etaTextFontSize);
        }
        if (!ListenerUtil.mutListener.listen(4119)) {
            etaTextView.setText("5");
        }
        if (!ListenerUtil.mutListener.listen(4120)) {
            etaTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        TextView etaMin = etaAndMin.findViewById(R.id.eta_min);
        if (!ListenerUtil.mutListener.listen(4121)) {
            etaMin.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (!ListenerUtil.mutListener.listen(4122)) {
            builder.setView(legendDialogView);
        }
        if (!ListenerUtil.mutListener.listen(4123)) {
            builder.setNeutralButton(R.string.main_help_close, (dialog, which) -> dismissDialog(LEGEND_DIALOG));
        }
        return builder.create();
    }

    /**
     * Show the "What's New" message if a new version was just installed
     *
     * @return true if a new version was just installed, false if not
     */
    @SuppressWarnings("deprecation")
    private boolean autoShowWhatsNew() {
        SharedPreferences settings = Application.getPrefs();
        // Get the current app version.
        PackageManager pm = getPackageManager();
        PackageInfo appInfo = null;
        try {
            if (!ListenerUtil.mutListener.listen(4124)) {
                appInfo = pm.getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
            }
        } catch (NameNotFoundException e) {
            // Do nothing, perhaps we'll get to show it again? Or never.
            return false;
        }
        final int oldVer = settings.getInt(WHATS_NEW_VER, 0);
        final int newVer = appInfo.versionCode;
        if (!ListenerUtil.mutListener.listen(4135)) {
            if ((ListenerUtil.mutListener.listen(4131) ? ((ListenerUtil.mutListener.listen(4130) ? ((ListenerUtil.mutListener.listen(4129) ? (oldVer >= newVer) : (ListenerUtil.mutListener.listen(4128) ? (oldVer <= newVer) : (ListenerUtil.mutListener.listen(4127) ? (oldVer > newVer) : (ListenerUtil.mutListener.listen(4126) ? (oldVer != newVer) : (ListenerUtil.mutListener.listen(4125) ? (oldVer == newVer) : (oldVer < newVer)))))) || mActivityWeakRef.get() != null) : ((ListenerUtil.mutListener.listen(4129) ? (oldVer >= newVer) : (ListenerUtil.mutListener.listen(4128) ? (oldVer <= newVer) : (ListenerUtil.mutListener.listen(4127) ? (oldVer > newVer) : (ListenerUtil.mutListener.listen(4126) ? (oldVer != newVer) : (ListenerUtil.mutListener.listen(4125) ? (oldVer == newVer) : (oldVer < newVer)))))) && mActivityWeakRef.get() != null)) || !mActivityWeakRef.get().isFinishing()) : ((ListenerUtil.mutListener.listen(4130) ? ((ListenerUtil.mutListener.listen(4129) ? (oldVer >= newVer) : (ListenerUtil.mutListener.listen(4128) ? (oldVer <= newVer) : (ListenerUtil.mutListener.listen(4127) ? (oldVer > newVer) : (ListenerUtil.mutListener.listen(4126) ? (oldVer != newVer) : (ListenerUtil.mutListener.listen(4125) ? (oldVer == newVer) : (oldVer < newVer)))))) || mActivityWeakRef.get() != null) : ((ListenerUtil.mutListener.listen(4129) ? (oldVer >= newVer) : (ListenerUtil.mutListener.listen(4128) ? (oldVer <= newVer) : (ListenerUtil.mutListener.listen(4127) ? (oldVer > newVer) : (ListenerUtil.mutListener.listen(4126) ? (oldVer != newVer) : (ListenerUtil.mutListener.listen(4125) ? (oldVer == newVer) : (oldVer < newVer)))))) && mActivityWeakRef.get() != null)) && !mActivityWeakRef.get().isFinishing()))) {
                if (!ListenerUtil.mutListener.listen(4132)) {
                    mActivityWeakRef.get().showDialog(WHATSNEW_DIALOG);
                }
                if (!ListenerUtil.mutListener.listen(4133)) {
                    // having the app run again).
                    TripService.scheduleAll(this, true);
                }
                if (!ListenerUtil.mutListener.listen(4134)) {
                    PreferenceUtils.saveInt(WHATS_NEW_VER, appInfo.versionCode);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Called by the BaseMapFragment when a stop obtains focus, or no stops have focus
     *
     * @param stop     the ObaStop that obtained focus, or null if no stop is in focus
     * @param routes   a HashMap of all route display names that serve this stop - key is routeId
     * @param location the user touch location on the map, or null if the focus was otherwise
     *                 cleared programmatically
     */
    @Override
    public void onFocusChanged(ObaStop stop, HashMap<String, ObaRoute> routes, Location location) {
        if (!ListenerUtil.mutListener.listen(4138)) {
            // Check to see if we're already focused on this same stop - if so, we shouldn't do anything
            if ((ListenerUtil.mutListener.listen(4137) ? ((ListenerUtil.mutListener.listen(4136) ? (mFocusedStopId != null || stop != null) : (mFocusedStopId != null && stop != null)) || mFocusedStopId.equalsIgnoreCase(stop.getId())) : ((ListenerUtil.mutListener.listen(4136) ? (mFocusedStopId != null || stop != null) : (mFocusedStopId != null && stop != null)) && mFocusedStopId.equalsIgnoreCase(stop.getId())))) {
                return;
            }
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(4139)) {
            // If the fragment's state has already been saved, then don't change the state (return)
            if (fm.isStateSaved()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4140)) {
            mFocusedStop = stop;
        }
        if (!ListenerUtil.mutListener.listen(4151)) {
            if (stop != null) {
                if (!ListenerUtil.mutListener.listen(4147)) {
                    mBikeRentalStationId = null;
                }
                if (!ListenerUtil.mutListener.listen(4148)) {
                    mFocusedStopId = stop.getId();
                }
                if (!ListenerUtil.mutListener.listen(4149)) {
                    // A stop on the map was just tapped, show it in the sliding panel
                    updateArrivalListFragment(stop.getId(), stop.getName(), stop.getStopCode(), stop, routes);
                }
                if (!ListenerUtil.mutListener.listen(4150)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_map_icon), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4141)) {
                    // and clear the currently focused stopId
                    mFocusedStopId = null;
                }
                if (!ListenerUtil.mutListener.listen(4142)) {
                    moveFabsLocation();
                }
                if (!ListenerUtil.mutListener.listen(4143)) {
                    mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                }
                if (!ListenerUtil.mutListener.listen(4145)) {
                    if (mArrivalsListFragment != null) {
                        if (!ListenerUtil.mutListener.listen(4144)) {
                            fm.beginTransaction().remove(mArrivalsListFragment).commit();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4146)) {
                    mShowArrivalsMenu = false;
                }
            }
        }
    }

    /**
     * Called from the BaseMapFragment when a BikeRentalStation is clicked.
     *
     * @param bikeRentalStation the bike rental station that was clicked.
     */
    @Override
    public void onFocusChanged(BikeRentalStation bikeRentalStation) {
        if (!ListenerUtil.mutListener.listen(4152)) {
            Log.d(TAG, "Bike Station Clicked on map");
        }
        if (!ListenerUtil.mutListener.listen(4155)) {
            // Check to see if we're already focused on this same bike rental station - if so, we shouldn't do anything
            if ((ListenerUtil.mutListener.listen(4154) ? ((ListenerUtil.mutListener.listen(4153) ? (mBikeRentalStationId != null || bikeRentalStation != null) : (mBikeRentalStationId != null && bikeRentalStation != null)) || mBikeRentalStationId.equalsIgnoreCase(bikeRentalStation.id)) : ((ListenerUtil.mutListener.listen(4153) ? (mBikeRentalStationId != null || bikeRentalStation != null) : (mBikeRentalStationId != null && bikeRentalStation != null)) && mBikeRentalStationId.equalsIgnoreCase(bikeRentalStation.id)))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4158)) {
            if (bikeRentalStation == null) {
                if (!ListenerUtil.mutListener.listen(4157)) {
                    mBikeRentalStationId = null;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4156)) {
                    mBikeRentalStationId = bikeRentalStation.id;
                }
            }
        }
    }

    @Override
    public void onProgressBarChanged(boolean showProgressBar) {
        if (!ListenerUtil.mutListener.listen(4159)) {
            mLastMapProgressBarState = showProgressBar;
        }
        if (!ListenerUtil.mutListener.listen(4162)) {
            if (showProgressBar) {
                if (!ListenerUtil.mutListener.listen(4161)) {
                    showMapProgressBar();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4160)) {
                    hideMapProgressBar();
                }
            }
        }
    }

    /**
     * Called by the ArrivalsListFragment when the ListView is created
     *
     * @param listView the ListView that was just created
     */
    @Override
    public void onListViewCreated(ListView listView) {
        if (!ListenerUtil.mutListener.listen(4163)) {
            // Set the scrollable view in the sliding panel
            mSlidingPanel.setScrollableView(listView);
        }
    }

    /**
     * Called by the ArrivalsListFragment when we have new updated arrival information
     *
     * @param response new arrival information
     */
    @Override
    public void onArrivalTimesUpdated(ObaArrivalInfoResponse response) {
        if (!ListenerUtil.mutListener.listen(4165)) {
            if ((ListenerUtil.mutListener.listen(4164) ? (response == null && response.getStop() == null) : (response == null || response.getStop() == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4167)) {
            // If we're missing any local references (e.g., if orientation just changed), store the values
            if (mFocusedStopId == null) {
                if (!ListenerUtil.mutListener.listen(4166)) {
                    mFocusedStopId = response.getStop().getId();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4174)) {
            if (mFocusedStop == null) {
                if (!ListenerUtil.mutListener.listen(4168)) {
                    mFocusedStop = response.getStop();
                }
                if (!ListenerUtil.mutListener.listen(4171)) {
                    // Since mFocusedStop was null, the layout changed, and we should recenter map on stop
                    if ((ListenerUtil.mutListener.listen(4169) ? (mMapFragment != null || mSlidingPanel != null) : (mMapFragment != null && mSlidingPanel != null))) {
                        if (!ListenerUtil.mutListener.listen(4170)) {
                            mMapFragment.setMapCenter(mFocusedStop.getLocation(), false, mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4173)) {
                    // ...and we should add a focus marker for this stop
                    if (mMapFragment != null) {
                        if (!ListenerUtil.mutListener.listen(4172)) {
                            mMapFragment.setFocusStop(mFocusedStop, response.getRoutes());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4175)) {
            // Header might have changed height, so make sure my location button is set above the header
            moveFabsLocation();
        }
        if (!ListenerUtil.mutListener.listen(4176)) {
            // Show arrival info related tutorials
            showArrivalInfoTutorials(response);
        }
    }

    /**
     * Triggers the various tutorials related to arrival info and the sliding panel header
     *
     * @param response arrival info, which is required for some tutorials
     */
    private void showArrivalInfoTutorials(ObaArrivalInfoResponse response) {
        if (!ListenerUtil.mutListener.listen(4177)) {
            // If we're already showing a ShowcaseView, we don't want to stack another on top
            if (ShowcaseViewUtils.isShowcaseViewShowing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4180)) {
            // If we can't see the map or sliding panel, we can't see the arrival info, so return
            if ((ListenerUtil.mutListener.listen(4179) ? ((ListenerUtil.mutListener.listen(4178) ? (mMapFragment.isHidden() && !mMapFragment.isVisible()) : (mMapFragment.isHidden() || !mMapFragment.isVisible())) && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) : ((ListenerUtil.mutListener.listen(4178) ? (mMapFragment.isHidden() && !mMapFragment.isVisible()) : (mMapFragment.isHidden() || !mMapFragment.isVisible())) || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4181)) {
            // Show the tutorial explaining arrival times
            ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_ARRIVAL_HEADER_ARRIVAL_INFO, this, response, false);
        }
        if (!ListenerUtil.mutListener.listen(4186)) {
            // Make sure the panel is stationary before showing the starred routes tutorial
            if ((ListenerUtil.mutListener.listen(4184) ? (mSlidingPanel != null || ((ListenerUtil.mutListener.listen(4183) ? ((ListenerUtil.mutListener.listen(4182) ? (isSlidingPanelCollapsed() && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED) : (isSlidingPanelCollapsed() || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) : ((ListenerUtil.mutListener.listen(4182) ? (isSlidingPanelCollapsed() && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED) : (isSlidingPanelCollapsed() || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)))) : (mSlidingPanel != null && ((ListenerUtil.mutListener.listen(4183) ? ((ListenerUtil.mutListener.listen(4182) ? (isSlidingPanelCollapsed() && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED) : (isSlidingPanelCollapsed() || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) : ((ListenerUtil.mutListener.listen(4182) ? (isSlidingPanelCollapsed() && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED) : (isSlidingPanelCollapsed() || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED)))))) {
                if (!ListenerUtil.mutListener.listen(4185)) {
                    ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_ARRIVAL_HEADER_STAR_ROUTE, this, response, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4187)) {
            ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_RECENT_STOPS_ROUTES, this, null, false);
        }
    }

    /**
     * Called by the ArrivalListFragment when the user selects the "Show route on map" for a
     * particular route/trip
     *
     * @param arrivalInfo The arrival information for the route/trip that the user selected
     * @return true if the listener has consumed the event, false otherwise
     */
    @Override
    public boolean onShowRouteOnMapSelected(ArrivalInfo arrivalInfo) {
        if (!ListenerUtil.mutListener.listen(4189)) {
            // If the panel is fully expanded, change it to anchored so the user can see the map
            if (mSlidingPanel != null) {
                if (!ListenerUtil.mutListener.listen(4188)) {
                    mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
            }
        }
        Bundle bundle = new Bundle();
        if (!ListenerUtil.mutListener.listen(4190)) {
            bundle.putBoolean(MapParams.ZOOM_TO_ROUTE, false);
        }
        if (!ListenerUtil.mutListener.listen(4191)) {
            bundle.putBoolean(MapParams.ZOOM_INCLUDE_CLOSEST_VEHICLE, true);
        }
        if (!ListenerUtil.mutListener.listen(4192)) {
            bundle.putString(MapParams.ROUTE_ID, arrivalInfo.getInfo().getRouteId());
        }
        if (!ListenerUtil.mutListener.listen(4193)) {
            mMapFragment.setMapMode(MapParams.MODE_ROUTE, bundle);
        }
        return true;
    }

    /**
     * Called when the user selects the "Sort by" option in ArrivalsListFragment
     */
    @Override
    public void onSortBySelected() {
        if (!ListenerUtil.mutListener.listen(4196)) {
            // If the sliding panel isn't open, then open it to show what we're sorting
            if (mSlidingPanel != null) {
                if (!ListenerUtil.mutListener.listen(4195)) {
                    if (isSlidingPanelCollapsed()) {
                        if (!ListenerUtil.mutListener.listen(4194)) {
                            mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(4202)) {
            // Collapse the panel when the user presses the back button
            if (mSlidingPanel != null) {
                if (!ListenerUtil.mutListener.listen(4199)) {
                    // Collapse the sliding panel if its anchored or expanded
                    if ((ListenerUtil.mutListener.listen(4197) ? (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED) : (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED))) {
                        if (!ListenerUtil.mutListener.listen(4198)) {
                            mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(4201)) {
                    // Clear focused stop and close the sliding panel if its collapsed
                    if (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                        if (!ListenerUtil.mutListener.listen(4200)) {
                            // panel via BaseMapFragment.OnFocusChangedListener in this.onFocusChanged()
                            mMapFragment.setFocusStop(null, null);
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4203)) {
            super.onBackPressed();
        }
    }

    /**
     * Redraw navigation drawer. This is necessary because we do not know whether to draw the
     * "Plan A Trip" option until a region is selected.
     */
    private void redrawNavigationDrawerFragment() {
        if (!ListenerUtil.mutListener.listen(4205)) {
            if (mNavigationDrawerFragment != null) {
                if (!ListenerUtil.mutListener.listen(4204)) {
                    mNavigationDrawerFragment.populateNavDrawer();
                }
            }
        }
    }

    /**
     * Create a new fragment to show the arrivals list for the given stop.  An ObaStop object
     * should
     * be passed in if available.  In all cases a stopId, stopName, and stopCode must be provided.
     *
     * @param stopId   Stop ID of the stop to show arrivals for
     * @param stopName Stop name of the stop to show arrivals for
     * @param stopCode Stop Code (rider-facing ID) of the stop to show arrivals for
     * @param stop     The ObaStop object for the stop to show arrivals for, or null if we don't
     *                 have
     *                 this yet.
     * @param routes   A HashMap of all route display names that serve this stop - key is routeId,
     *                 or
     *                 null if we don't have this yet.
     */
    private void updateArrivalListFragment(@NonNull String stopId, @NonNull String stopName, @NonNull String stopCode, ObaStop stop, HashMap<String, ObaRoute> routes) {
        FragmentManager fm = getSupportFragmentManager();
        Intent intent;
        if (!ListenerUtil.mutListener.listen(4206)) {
            mArrivalsListFragment = new ArrivalsListFragment();
        }
        if (!ListenerUtil.mutListener.listen(4207)) {
            mArrivalsListFragment.setListener(this);
        }
        if (!ListenerUtil.mutListener.listen(4208)) {
            // Set the header for the arrival list to be the top of the sliding panel
            mArrivalsListHeader = new ArrivalsListHeader(this, mArrivalsListFragment, getSupportFragmentManager());
        }
        if (!ListenerUtil.mutListener.listen(4209)) {
            mArrivalsListFragment.setHeader(mArrivalsListHeader, mArrivalsListHeaderView);
        }
        if (!ListenerUtil.mutListener.listen(4210)) {
            mArrivalsListHeader.setSlidingPanelController(mSlidingPanelController);
        }
        if (!ListenerUtil.mutListener.listen(4211)) {
            mArrivalsListHeader.setSlidingPanelCollapsed(isSlidingPanelCollapsed());
        }
        if (!ListenerUtil.mutListener.listen(4212)) {
            mShowArrivalsMenu = true;
        }
        if (!ListenerUtil.mutListener.listen(4213)) {
            mExpandCollapse = mArrivalsListHeaderView.findViewById(R.id.expand_collapse);
        }
        if ((ListenerUtil.mutListener.listen(4214) ? (stop != null || routes != null) : (stop != null && routes != null))) {
            // before getting an API response
            intent = new ArrivalsListFragment.IntentBuilder(this, stop, routes).build();
        } else {
            // Some fields will be blank until we get an API response
            intent = new ArrivalsListFragment.IntentBuilder(this, stopId).setStopName(stopName).setStopCode(stopCode).build();
        }
        if (!ListenerUtil.mutListener.listen(4215)) {
            mArrivalsListFragment.setArguments(FragmentUtils.getIntentArgs(intent));
        }
        if (!ListenerUtil.mutListener.listen(4216)) {
            fm.beginTransaction().replace(R.id.slidingFragment, mArrivalsListFragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(4217)) {
            showSlidingPanel();
        }
        if (!ListenerUtil.mutListener.listen(4218)) {
            moveFabsLocation();
        }
    }

    private void showSlidingPanel() {
        if (!ListenerUtil.mutListener.listen(4220)) {
            if (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                if (!ListenerUtil.mutListener.listen(4219)) {
                    mSlidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
            }
        }
    }

    private void goToSendFeedBack() {
        if (!ListenerUtil.mutListener.listen(4225)) {
            if (mFocusedStop != null) {
                if (!ListenerUtil.mutListener.listen(4224)) {
                    ReportActivity.start(this, mFocusedStopId, mFocusedStop.getName(), mFocusedStop.getStopCode(), mFocusedStop.getLatitude(), mFocusedStop.getLongitude(), mGoogleApiClient);
                }
            } else {
                Location loc = Application.getLastKnownLocation(this, mGoogleApiClient);
                if (!ListenerUtil.mutListener.listen(4223)) {
                    if (loc != null) {
                        if (!ListenerUtil.mutListener.listen(4222)) {
                            ReportActivity.start(this, loc.getLatitude(), loc.getLongitude(), mGoogleApiClient);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4221)) {
                            ReportActivity.start(this, mGoogleApiClient);
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks region status, which can potentially including forcing a reload of region
     * info from the server.  Also includes auto-selection of closest region.
     */
    private void checkRegionStatus() {
        if (!ListenerUtil.mutListener.listen(4226)) {
            // First check for custom API URL set by user via Preferences, since if that is set we don't need region info from the REST API
            if (!TextUtils.isEmpty(Application.get().getCustomApiUrl())) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4230)) {
            // Check if region is hard-coded for this build flavor
            if (BuildConfig.USE_FIXED_REGION) {
                ObaRegion r = RegionUtils.getRegionFromBuildFlavor();
                if (!ListenerUtil.mutListener.listen(4227)) {
                    // Set the hard-coded region
                    RegionUtils.saveToProvider(this, Collections.singletonList(r));
                }
                if (!ListenerUtil.mutListener.listen(4228)) {
                    Application.get().setCurrentRegion(r);
                }
                if (!ListenerUtil.mutListener.listen(4229)) {
                    // Disable any region auto-selection in preferences
                    PreferenceUtils.saveBoolean(getString(R.string.preference_key_auto_select_region), false);
                }
                return;
            }
        }
        boolean forceReload = false;
        boolean showProgressDialog = true;
        if (!ListenerUtil.mutListener.listen(4243)) {
            // force contacting the server again
            if ((ListenerUtil.mutListener.listen(4240) ? (Application.get().getCurrentRegion() == null && (ListenerUtil.mutListener.listen(4239) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) >= REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4238) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) <= REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4237) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) < REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4236) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) != REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4235) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) == REGION_UPDATE_THRESHOLD) : ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) > REGION_UPDATE_THRESHOLD))))))) : (Application.get().getCurrentRegion() == null || (ListenerUtil.mutListener.listen(4239) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) >= REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4238) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) <= REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4237) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) < REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4236) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) != REGION_UPDATE_THRESHOLD) : (ListenerUtil.mutListener.listen(4235) ? ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) == REGION_UPDATE_THRESHOLD) : ((ListenerUtil.mutListener.listen(4234) ? (new Date().getTime() % Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4233) ? (new Date().getTime() / Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4232) ? (new Date().getTime() * Application.get().getLastRegionUpdateDate()) : (ListenerUtil.mutListener.listen(4231) ? (new Date().getTime() + Application.get().getLastRegionUpdateDate()) : (new Date().getTime() - Application.get().getLastRegionUpdateDate()))))) > REGION_UPDATE_THRESHOLD))))))))) {
                if (!ListenerUtil.mutListener.listen(4241)) {
                    forceReload = true;
                }
                if (!ListenerUtil.mutListener.listen(4242)) {
                    Log.d(TAG, "Region info has expired (or does not exist), forcing a reload from the server...");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4245)) {
            if (Application.get().getCurrentRegion() != null) {
                if (!ListenerUtil.mutListener.listen(4244)) {
                    // We already have region info locally, so just check current region status quietly in the background
                    showProgressDialog = false;
                }
            }
        }
        try {
            PackageInfo appInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
            SharedPreferences settings = Application.getPrefs();
            final int oldVer = settings.getInt(CHECK_REGION_VER, 0);
            final int newVer = appInfo.versionCode;
            if (!ListenerUtil.mutListener.listen(4252)) {
                if ((ListenerUtil.mutListener.listen(4250) ? (oldVer >= newVer) : (ListenerUtil.mutListener.listen(4249) ? (oldVer <= newVer) : (ListenerUtil.mutListener.listen(4248) ? (oldVer > newVer) : (ListenerUtil.mutListener.listen(4247) ? (oldVer != newVer) : (ListenerUtil.mutListener.listen(4246) ? (oldVer == newVer) : (oldVer < newVer))))))) {
                    if (!ListenerUtil.mutListener.listen(4251)) {
                        forceReload = true;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(4253)) {
                PreferenceUtils.saveInt(CHECK_REGION_VER, appInfo.versionCode);
            }
        } catch (NameNotFoundException e) {
        }
        // Check region status, possibly forcing a reload from server and checking proximity to current region
        List<ObaRegionsTask.Callback> callbacks = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(4254)) {
            callbacks.add(mMapFragment);
        }
        if (!ListenerUtil.mutListener.listen(4255)) {
            callbacks.add(this);
        }
        ObaRegionsTask task = new ObaRegionsTask(this, callbacks, forceReload, showProgressDialog);
        if (!ListenerUtil.mutListener.listen(4256)) {
            task.execute();
        }
    }

    // 
    @Override
    public void onRegionTaskFinished(boolean currentRegionChanged) {
        // Show "What's New" (which might need refreshed Regions API contents)
        boolean update = autoShowWhatsNew();
        if (!ListenerUtil.mutListener.listen(4259)) {
            // Redraw nav drawer if the region changed, or if we just installed a new version
            if ((ListenerUtil.mutListener.listen(4257) ? (currentRegionChanged && update) : (currentRegionChanged || update))) {
                if (!ListenerUtil.mutListener.listen(4258)) {
                    redrawNavigationDrawerFragment();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4264)) {
            // If region changed and was auto-selected, show user what region we're using
            if ((ListenerUtil.mutListener.listen(4262) ? ((ListenerUtil.mutListener.listen(4261) ? ((ListenerUtil.mutListener.listen(4260) ? (currentRegionChanged || Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true)) : (currentRegionChanged && Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true))) || Application.get().getCurrentRegion() != null) : ((ListenerUtil.mutListener.listen(4260) ? (currentRegionChanged || Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true)) : (currentRegionChanged && Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true))) && Application.get().getCurrentRegion() != null)) || UIUtils.canManageDialog(this)) : ((ListenerUtil.mutListener.listen(4261) ? ((ListenerUtil.mutListener.listen(4260) ? (currentRegionChanged || Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true)) : (currentRegionChanged && Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true))) || Application.get().getCurrentRegion() != null) : ((ListenerUtil.mutListener.listen(4260) ? (currentRegionChanged || Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true)) : (currentRegionChanged && Application.getPrefs().getBoolean(getString(R.string.preference_key_auto_select_region), true))) && Application.get().getCurrentRegion() != null)) && UIUtils.canManageDialog(this)))) {
                if (!ListenerUtil.mutListener.listen(4263)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.region_region_found, Application.get().getCurrentRegion().getName()), Toast.LENGTH_LONG).show();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4265)) {
            updateLayersFab();
        }
    }

    private void setupZoomButtons() {
        ImageButton mZoomInBtn = findViewById(R.id.btnZoomIn);
        ImageButton mZoomOutBtn = findViewById(R.id.btnZoomOut);
        if (!ListenerUtil.mutListener.listen(4266)) {
            mZoomInBtn.setOnClickListener(view -> mMapFragment.zoomIn());
        }
        if (!ListenerUtil.mutListener.listen(4267)) {
            mZoomOutBtn.setOnClickListener(view -> mMapFragment.zoomOut());
        }
    }

    private void setupMyLocationButton() {
        if (!ListenerUtil.mutListener.listen(4268)) {
            // Initialize the My Location button
            mFabMyLocation = findViewById(R.id.btnMyLocation);
        }
        if (!ListenerUtil.mutListener.listen(4269)) {
            mFabMyLocation.setOnClickListener(view -> {
                if (mMapFragment != null) {
                    // Reset the preference to ask user to enable location
                    PreferenceUtils.saveBoolean(getString(R.string.preference_key_never_show_location_dialog), false);
                    PreferenceUtils.setUserDeniedLocationPermissions(false);
                    mMapFragment.setMyLocation(true, true);
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_button_press_location), null);
                }
            });
        }
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mFabMyLocation.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(4270)) {
            MY_LOC_DEFAULT_BOTTOM_MARGIN = p.bottomMargin;
        }
        if (!ListenerUtil.mutListener.listen(4271)) {
            checkLeftHandMode();
        }
        if (!ListenerUtil.mutListener.listen(4276)) {
            if (mCurrentNavDrawerPosition == NAVDRAWER_ITEM_NEARBY) {
                if (!ListenerUtil.mutListener.listen(4274)) {
                    showFloatingActionButtons();
                }
                if (!ListenerUtil.mutListener.listen(4275)) {
                    showMapProgressBar();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4272)) {
                    hideFloatingActionButtons();
                }
                if (!ListenerUtil.mutListener.listen(4273)) {
                    hideMapProgressBar();
                }
            }
        }
    }

    private void checkDisplayZoomControls() {
        boolean displayZoom = Application.getPrefs().getBoolean(getString(R.string.preference_key_show_zoom_controls), false);
        if (!ListenerUtil.mutListener.listen(4277)) {
            showZoomControls(displayZoom);
        }
    }

    /**
     * Shows zoom controls if state is true, hides the zoom controls if state is false
     * @param showZoom true if the zoom controls should be visible, false if they should be hidden
     */
    private void showZoomControls(boolean showZoom) {
        LinearLayout zoomLayout = findViewById(R.id.zoom_buttons_layout);
        if (!ListenerUtil.mutListener.listen(4281)) {
            if (zoomLayout != null) {
                if (!ListenerUtil.mutListener.listen(4280)) {
                    if (showZoom) {
                        if (!ListenerUtil.mutListener.listen(4279)) {
                            zoomLayout.setVisibility(LinearLayout.VISIBLE);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4278)) {
                            zoomLayout.setVisibility(LinearLayout.GONE);
                        }
                    }
                }
            }
        }
    }

    private void checkLeftHandMode() {
        boolean leftHandMode = Application.getPrefs().getBoolean(getString(R.string.preference_key_left_hand_mode), false);
        if (!ListenerUtil.mutListener.listen(4282)) {
            setFABLocation(leftHandMode);
        }
    }

    private void setFABLocation(boolean leftHandMode) {
        if (!ListenerUtil.mutListener.listen(4300)) {
            if (mFabMyLocation != null) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mFabMyLocation.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(4299)) {
                    if (leftHandMode) {
                        if (!ListenerUtil.mutListener.listen(4291)) {
                            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                        }
                        if (!ListenerUtil.mutListener.listen(4298)) {
                            if ((ListenerUtil.mutListener.listen(4296) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4295) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4294) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4293) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4292) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                                if (!ListenerUtil.mutListener.listen(4297)) {
                                    layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4283)) {
                            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        }
                        if (!ListenerUtil.mutListener.listen(4290)) {
                            if ((ListenerUtil.mutListener.listen(4288) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4287) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4286) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4285) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.JELLY_BEAN_MR1) : (ListenerUtil.mutListener.listen(4284) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1))))))) {
                                if (!ListenerUtil.mutListener.listen(4289)) {
                                    layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4304)) {
            if (mLayersFab != null) {
                if (!ListenerUtil.mutListener.listen(4303)) {
                    if (leftHandMode) {
                        if (!ListenerUtil.mutListener.listen(4302)) {
                            mLayersFab.setButtonPosition(POSITION_BOTTOM | POSITION_START);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4301)) {
                            mLayersFab.setButtonPosition(POSITION_BOTTOM | POSITION_END);
                        }
                    }
                }
            }
        }
    }

    /**
     * Moves both Floating Action Buttons as response to sliding panel height changes.
     * <p>
     * Currently there are two FAB that can be moved, the My location button and the Layers button.
     */
    private synchronized void moveFabsLocation() {
        if (!ListenerUtil.mutListener.listen(4305)) {
            moveFabLocation(mFabMyLocation, MY_LOC_DEFAULT_BOTTOM_MARGIN);
        }
        if (!ListenerUtil.mutListener.listen(4306)) {
            moveFabLocation(mLayersFab, LAYERS_FAB_DEFAULT_BOTTOM_MARGIN);
        }
    }

    private void moveFabLocation(final View fab, final int initialMargin) {
        if (!ListenerUtil.mutListener.listen(4307)) {
            if (fab == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4310)) {
            if ((ListenerUtil.mutListener.listen(4309) ? (mMyLocationAnimation != null || ((ListenerUtil.mutListener.listen(4308) ? (mMyLocationAnimation.hasStarted() || !mMyLocationAnimation.hasEnded()) : (mMyLocationAnimation.hasStarted() && !mMyLocationAnimation.hasEnded())))) : (mMyLocationAnimation != null && ((ListenerUtil.mutListener.listen(4308) ? (mMyLocationAnimation.hasStarted() || !mMyLocationAnimation.hasEnded()) : (mMyLocationAnimation.hasStarted() && !mMyLocationAnimation.hasEnded())))))) {
            }
        }
        if (!ListenerUtil.mutListener.listen(4312)) {
            if (mMyLocationAnimation != null) {
                if (!ListenerUtil.mutListener.listen(4311)) {
                    mMyLocationAnimation.reset();
                }
            }
        }
        // Post this to a handler to allow the header to settle before animating the button
        final Handler h = new Handler();
        if (!ListenerUtil.mutListener.listen(4347)) {
            h.postDelayed(new Runnable() {

                @Override
                public void run() {
                    final ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) fab.getLayoutParams();
                    int tempMargin = initialMargin;
                    if (!ListenerUtil.mutListener.listen(4317)) {
                        if (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                            if (!ListenerUtil.mutListener.listen(4315)) {
                                tempMargin += mSlidingPanel.getPanelHeight();
                            }
                            if (!ListenerUtil.mutListener.listen(4316)) {
                                if (p.bottomMargin == tempMargin) {
                                    // Button is already in the right position, do nothing
                                    return;
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4314)) {
                                if (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
                                    if (!ListenerUtil.mutListener.listen(4313)) {
                                        if (p.bottomMargin == tempMargin) {
                                            // Button is already in the right position, do nothing
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    final int goalMargin = tempMargin;
                    final int currentMargin = p.bottomMargin;
                    if (!ListenerUtil.mutListener.listen(4344)) {
                        mMyLocationAnimation = new Animation() {

                            @Override
                            protected void applyTransformation(float interpolatedTime, Transformation t) {
                                int bottom;
                                if ((ListenerUtil.mutListener.listen(4322) ? (goalMargin >= currentMargin) : (ListenerUtil.mutListener.listen(4321) ? (goalMargin <= currentMargin) : (ListenerUtil.mutListener.listen(4320) ? (goalMargin < currentMargin) : (ListenerUtil.mutListener.listen(4319) ? (goalMargin != currentMargin) : (ListenerUtil.mutListener.listen(4318) ? (goalMargin == currentMargin) : (goalMargin > currentMargin))))))) {
                                    bottom = currentMargin + (int) ((ListenerUtil.mutListener.listen(4342) ? (Math.abs((ListenerUtil.mutListener.listen(4338) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4337) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4336) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4335) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) % interpolatedTime) : (ListenerUtil.mutListener.listen(4341) ? (Math.abs((ListenerUtil.mutListener.listen(4338) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4337) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4336) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4335) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) / interpolatedTime) : (ListenerUtil.mutListener.listen(4340) ? (Math.abs((ListenerUtil.mutListener.listen(4338) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4337) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4336) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4335) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) - interpolatedTime) : (ListenerUtil.mutListener.listen(4339) ? (Math.abs((ListenerUtil.mutListener.listen(4338) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4337) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4336) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4335) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) + interpolatedTime) : (Math.abs((ListenerUtil.mutListener.listen(4338) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4337) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4336) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4335) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) * interpolatedTime))))));
                                } else {
                                    bottom = (ListenerUtil.mutListener.listen(4334) ? (currentMargin % (int) ((ListenerUtil.mutListener.listen(4330) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) % interpolatedTime) : (ListenerUtil.mutListener.listen(4329) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) / interpolatedTime) : (ListenerUtil.mutListener.listen(4328) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) - interpolatedTime) : (ListenerUtil.mutListener.listen(4327) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) + interpolatedTime) : (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) * interpolatedTime))))))) : (ListenerUtil.mutListener.listen(4333) ? (currentMargin / (int) ((ListenerUtil.mutListener.listen(4330) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) % interpolatedTime) : (ListenerUtil.mutListener.listen(4329) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) / interpolatedTime) : (ListenerUtil.mutListener.listen(4328) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) - interpolatedTime) : (ListenerUtil.mutListener.listen(4327) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) + interpolatedTime) : (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) * interpolatedTime))))))) : (ListenerUtil.mutListener.listen(4332) ? (currentMargin * (int) ((ListenerUtil.mutListener.listen(4330) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) % interpolatedTime) : (ListenerUtil.mutListener.listen(4329) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) / interpolatedTime) : (ListenerUtil.mutListener.listen(4328) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) - interpolatedTime) : (ListenerUtil.mutListener.listen(4327) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) + interpolatedTime) : (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) * interpolatedTime))))))) : (ListenerUtil.mutListener.listen(4331) ? (currentMargin + (int) ((ListenerUtil.mutListener.listen(4330) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) % interpolatedTime) : (ListenerUtil.mutListener.listen(4329) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) / interpolatedTime) : (ListenerUtil.mutListener.listen(4328) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) - interpolatedTime) : (ListenerUtil.mutListener.listen(4327) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) + interpolatedTime) : (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) * interpolatedTime))))))) : (currentMargin - (int) ((ListenerUtil.mutListener.listen(4330) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) % interpolatedTime) : (ListenerUtil.mutListener.listen(4329) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) / interpolatedTime) : (ListenerUtil.mutListener.listen(4328) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) - interpolatedTime) : (ListenerUtil.mutListener.listen(4327) ? (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) + interpolatedTime) : (Math.abs((ListenerUtil.mutListener.listen(4326) ? (currentMargin % goalMargin) : (ListenerUtil.mutListener.listen(4325) ? (currentMargin / goalMargin) : (ListenerUtil.mutListener.listen(4324) ? (currentMargin * goalMargin) : (ListenerUtil.mutListener.listen(4323) ? (currentMargin + goalMargin) : (currentMargin - goalMargin)))))) * interpolatedTime)))))))))));
                                }
                                if (!ListenerUtil.mutListener.listen(4343)) {
                                    UIUtils.setMargins(fab, p.leftMargin, p.topMargin, p.rightMargin, bottom);
                                }
                            }
                        };
                    }
                    if (!ListenerUtil.mutListener.listen(4345)) {
                        mMyLocationAnimation.setDuration(MY_LOC_BTN_ANIM_DURATION);
                    }
                    if (!ListenerUtil.mutListener.listen(4346)) {
                        fab.startAnimation(mMyLocationAnimation);
                    }
                }
            }, 100);
        }
    }

    private void showFloatingActionButtons() {
        if (!ListenerUtil.mutListener.listen(4349)) {
            if ((ListenerUtil.mutListener.listen(4348) ? (mFabMyLocation == null || mLayersFab == null) : (mFabMyLocation == null && mLayersFab == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4352)) {
            if ((ListenerUtil.mutListener.listen(4350) ? (mFabMyLocation != null || mFabMyLocation.getVisibility() != View.VISIBLE) : (mFabMyLocation != null && mFabMyLocation.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(4351)) {
                    mFabMyLocation.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4356)) {
            if ((ListenerUtil.mutListener.listen(4353) ? (mLayersFab != null || mLayersFab.getVisibility() != View.VISIBLE) : (mLayersFab != null && mLayersFab.getVisibility() != View.VISIBLE))) {
                if (!ListenerUtil.mutListener.listen(4355)) {
                    if (Application.isBikeshareEnabled()) {
                        if (!ListenerUtil.mutListener.listen(4354)) {
                            mLayersFab.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    }

    private void hideFloatingActionButtons() {
        if (!ListenerUtil.mutListener.listen(4358)) {
            if ((ListenerUtil.mutListener.listen(4357) ? (mFabMyLocation == null || mLayersFab == null) : (mFabMyLocation == null && mLayersFab == null))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4361)) {
            if ((ListenerUtil.mutListener.listen(4359) ? (mFabMyLocation != null || mFabMyLocation.getVisibility() != View.GONE) : (mFabMyLocation != null && mFabMyLocation.getVisibility() != View.GONE))) {
                if (!ListenerUtil.mutListener.listen(4360)) {
                    mFabMyLocation.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4364)) {
            if ((ListenerUtil.mutListener.listen(4362) ? (mLayersFab != null || mLayersFab.getVisibility() != View.GONE) : (mLayersFab != null && mLayersFab.getVisibility() != View.GONE))) {
                if (!ListenerUtil.mutListener.listen(4363)) {
                    mLayersFab.setVisibility(View.GONE);
                }
            }
        }
    }

    private void showMapProgressBar() {
        if (!ListenerUtil.mutListener.listen(4365)) {
            if (mMapProgressBar == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4367)) {
            if (mMapProgressBar.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(4366)) {
                    mMapProgressBar.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void hideMapProgressBar() {
        if (!ListenerUtil.mutListener.listen(4368)) {
            if (mMapProgressBar == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4370)) {
            if (mMapProgressBar.getVisibility() != View.GONE) {
                if (!ListenerUtil.mutListener.listen(4369)) {
                    mMapProgressBar.setVisibility(View.GONE);
                }
            }
        }
    }

    private void setupNavigationDrawer() {
        if (!ListenerUtil.mutListener.listen(4371)) {
            mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        }
        if (!ListenerUtil.mutListener.listen(4372)) {
            // Set up the drawer.
            mNavigationDrawerFragment.setUp(R.id.navigation_drawer, findViewById(R.id.nav_drawer_left_pane));
        }
        // Was this activity started to show a route or stop on the map? If so, switch to MapFragment
        Bundle bundle = getIntent().getExtras();
        if (!ListenerUtil.mutListener.listen(4376)) {
            if (bundle != null) {
                String routeId = bundle.getString(MapParams.ROUTE_ID);
                String stopId = bundle.getString(MapParams.STOP_ID);
                if (!ListenerUtil.mutListener.listen(4375)) {
                    if ((ListenerUtil.mutListener.listen(4373) ? (routeId != null && stopId != null) : (routeId != null || stopId != null))) {
                        if (!ListenerUtil.mutListener.listen(4374)) {
                            mNavigationDrawerFragment.selectItem(NAVDRAWER_ITEM_NEARBY);
                        }
                    }
                }
            }
        }
    }

    private void setupGooglePlayServices() {
        // Init Google Play Services as early as possible in the Fragment lifecycle to give it time
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(4379)) {
            if (api.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(4377)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(this);
                }
                if (!ListenerUtil.mutListener.listen(4378)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    private void setupLayersSpeedDial() {
        if (!ListenerUtil.mutListener.listen(4380)) {
            mLayersFab = findViewById(R.id.layersSpeedDial);
        }
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) mLayersFab.getLayoutParams();
        if (!ListenerUtil.mutListener.listen(4381)) {
            LAYERS_FAB_DEFAULT_BOTTOM_MARGIN = p.bottomMargin;
        }
        if (!ListenerUtil.mutListener.listen(4382)) {
            mLayersFab.setButtonIconResource(R.drawable.ic_layers_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(4383)) {
            mLayersFab.setButtonBackgroundColour(ContextCompat.getColor(this, R.color.theme_accent));
        }
        LayersSpeedDialAdapter adapter = new LayersSpeedDialAdapter(this);
        if (!ListenerUtil.mutListener.listen(4384)) {
            // Add the BaseMapFragment listener to activate the layer on the map
            adapter.addLayerActivationListener(mMapFragment);
        }
        if (!ListenerUtil.mutListener.listen(4387)) {
            // reference to it only in the main activity.
            adapter.addLayerActivationListener(new LayersSpeedDialAdapter.LayerActivationListener() {

                @Override
                public void onActivateLayer(LayerInfo layer) {
                    Handler h = new Handler(getMainLooper());
                    if (!ListenerUtil.mutListener.listen(4385)) {
                        h.postDelayed(() -> mLayersFab.rebuildSpeedDialMenu(), 100);
                    }
                }

                @Override
                public void onDeactivateLayer(LayerInfo layer) {
                    Handler h = new Handler(getMainLooper());
                    if (!ListenerUtil.mutListener.listen(4386)) {
                        h.postDelayed(() -> mLayersFab.rebuildSpeedDialMenu(), 100);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4388)) {
            mLayersFab.setSpeedDialMenuAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(4389)) {
            mLayersFab.setOnSpeedDialMenuOpenListener(v -> mLayersFab.setButtonIconResource(R.drawable.ic_add_white_24dp));
        }
        if (!ListenerUtil.mutListener.listen(4390)) {
            mLayersFab.setOnSpeedDialMenuCloseListener(v -> mLayersFab.setButtonIconResource(R.drawable.ic_layers_white_24dp));
        }
        if (!ListenerUtil.mutListener.listen(4391)) {
            mLayersFab.setContentCoverEnabled(false);
        }
    }

    /**
     * Method used to (re)display the layers FAB button when the activity restarts or regions data
     * is updated
     */
    private void updateLayersFab() {
        if (!ListenerUtil.mutListener.listen(4395)) {
            if ((ListenerUtil.mutListener.listen(4392) ? (Application.isBikeshareEnabled() || mCurrentNavDrawerPosition == NAVDRAWER_ITEM_NEARBY) : (Application.isBikeshareEnabled() && mCurrentNavDrawerPosition == NAVDRAWER_ITEM_NEARBY))) {
                if (!ListenerUtil.mutListener.listen(4394)) {
                    mLayersFab.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4393)) {
                    mLayersFab.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4396)) {
            mLayersFab.rebuildSpeedDialMenu();
        }
    }

    private void setupSlidingPanel() {
        if (!ListenerUtil.mutListener.listen(4397)) {
            mSlidingPanel = findViewById(R.id.bottom_sliding_layout);
        }
        if (!ListenerUtil.mutListener.listen(4398)) {
            mArrivalsListHeaderView = findViewById(R.id.arrivals_list_header);
        }
        if (!ListenerUtil.mutListener.listen(4399)) {
            mArrivalsListHeaderSubView = mArrivalsListHeaderView.findViewById(R.id.main_header_content);
        }
        if (!ListenerUtil.mutListener.listen(4400)) {
            mSlidingPanel.setPanelState(// Don't show the panel until we have content
            SlidingUpPanelLayout.PanelState.HIDDEN);
        }
        if (!ListenerUtil.mutListener.listen(4401)) {
            mSlidingPanel.setOverlayed(true);
        }
        if (!ListenerUtil.mutListener.listen(4402)) {
            mSlidingPanel.setAnchorPoint(MapModeController.OVERLAY_PERCENTAGE);
        }
        if (!ListenerUtil.mutListener.listen(4443)) {
            mSlidingPanel.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

                @Override
                public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                    if (!ListenerUtil.mutListener.listen(4403)) {
                        if (previousState == SlidingUpPanelLayout.PanelState.HIDDEN) {
                            return;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4408)) {
                        switch(newState) {
                            case EXPANDED:
                                if (!ListenerUtil.mutListener.listen(4404)) {
                                    onPanelExpanded(panel);
                                }
                                break;
                            case COLLAPSED:
                                if (!ListenerUtil.mutListener.listen(4405)) {
                                    onPanelCollapsed(panel);
                                }
                                break;
                            case ANCHORED:
                                if (!ListenerUtil.mutListener.listen(4406)) {
                                    onPanelAnchored(panel);
                                }
                                break;
                            case HIDDEN:
                                if (!ListenerUtil.mutListener.listen(4407)) {
                                    onPanelHidden(panel);
                                }
                                break;
                        }
                    }
                }

                @Override
                public void onPanelSlide(View panel, float slideOffset) {
                    if (!ListenerUtil.mutListener.listen(4409)) {
                        Log.d(TAG, "onPanelSlide, offset " + slideOffset);
                    }
                    if (!ListenerUtil.mutListener.listen(4411)) {
                        if (mArrivalsListHeader != null) {
                            if (!ListenerUtil.mutListener.listen(4410)) {
                                mArrivalsListHeader.closeStatusPopups();
                            }
                        }
                    }
                }

                public void onPanelExpanded(View panel) {
                    if (!ListenerUtil.mutListener.listen(4412)) {
                        Log.d(TAG, "onPanelExpanded");
                    }
                    if (!ListenerUtil.mutListener.listen(4415)) {
                        if (mArrivalsListHeader != null) {
                            if (!ListenerUtil.mutListener.listen(4413)) {
                                mArrivalsListHeader.setSlidingPanelCollapsed(false);
                            }
                            if (!ListenerUtil.mutListener.listen(4414)) {
                                mArrivalsListHeader.refresh();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4417)) {
                        // Accessibility
                        if (mExpandCollapse != null) {
                            if (!ListenerUtil.mutListener.listen(4416)) {
                                mExpandCollapse.setContentDescription(Application.get().getResources().getString(R.string.stop_header_sliding_panel_open));
                            }
                        }
                    }
                }

                public void onPanelCollapsed(View panel) {
                    if (!ListenerUtil.mutListener.listen(4418)) {
                        Log.d(TAG, "onPanelCollapsed");
                    }
                    if (!ListenerUtil.mutListener.listen(4420)) {
                        if (mMapFragment != null) {
                            if (!ListenerUtil.mutListener.listen(4419)) {
                                mMapFragment.getMapView().setPadding(null, null, null, mSlidingPanel.getPanelHeight());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4423)) {
                        if (mArrivalsListHeader != null) {
                            if (!ListenerUtil.mutListener.listen(4421)) {
                                mArrivalsListHeader.setSlidingPanelCollapsed(true);
                            }
                            if (!ListenerUtil.mutListener.listen(4422)) {
                                mArrivalsListHeader.refresh();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4424)) {
                        moveFabsLocation();
                    }
                    if (!ListenerUtil.mutListener.listen(4426)) {
                        // Accessibility
                        if (mExpandCollapse != null) {
                            if (!ListenerUtil.mutListener.listen(4425)) {
                                mExpandCollapse.setContentDescription(Application.get().getResources().getString(R.string.stop_header_sliding_panel_collapsed));
                            }
                        }
                    }
                }

                public void onPanelAnchored(View panel) {
                    if (!ListenerUtil.mutListener.listen(4427)) {
                        Log.d(TAG, "onPanelAnchored");
                    }
                    if (!ListenerUtil.mutListener.listen(4429)) {
                        if (mMapFragment != null) {
                            if (!ListenerUtil.mutListener.listen(4428)) {
                                mMapFragment.getMapView().setPadding(null, null, null, mSlidingPanel.getPanelHeight());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4432)) {
                        if ((ListenerUtil.mutListener.listen(4430) ? (mFocusedStop != null || mMapFragment != null) : (mFocusedStop != null && mMapFragment != null))) {
                            if (!ListenerUtil.mutListener.listen(4431)) {
                                mMapFragment.setMapCenter(mFocusedStop.getLocation(), true, true);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4435)) {
                        if (mArrivalsListHeader != null) {
                            if (!ListenerUtil.mutListener.listen(4433)) {
                                mArrivalsListHeader.setSlidingPanelCollapsed(false);
                            }
                            if (!ListenerUtil.mutListener.listen(4434)) {
                                mArrivalsListHeader.refresh();
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4437)) {
                        // Accessibility
                        if (mExpandCollapse != null) {
                            if (!ListenerUtil.mutListener.listen(4436)) {
                                mExpandCollapse.setContentDescription(Application.get().getResources().getString(R.string.stop_header_sliding_panel_open));
                            }
                        }
                    }
                }

                public void onPanelHidden(View panel) {
                    if (!ListenerUtil.mutListener.listen(4438)) {
                        Log.d(TAG, "onPanelHidden");
                    }
                    if (!ListenerUtil.mutListener.listen(4440)) {
                        // MapFragment or the ArrivalListFragment (e.g., removing the ArrivalListFragment)
                        if (mMapFragment != null) {
                            if (!ListenerUtil.mutListener.listen(4439)) {
                                mMapFragment.getMapView().setPadding(null, null, null, 0);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4442)) {
                        // Accessibility - reset it here so its ready for next showing
                        if (mExpandCollapse != null) {
                            if (!ListenerUtil.mutListener.listen(4441)) {
                                mExpandCollapse.setContentDescription(Application.get().getResources().getString(R.string.stop_header_sliding_panel_collapsed));
                            }
                        }
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4452)) {
            mSlidingPanelController = new SlidingPanelController() {

                @Override
                public void setPanelHeightPixels(int heightInPixels) {
                    if (!ListenerUtil.mutListener.listen(4450)) {
                        if (mSlidingPanel != null) {
                            if (!ListenerUtil.mutListener.listen(4445)) {
                                if ((ListenerUtil.mutListener.listen(4444) ? (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.DRAGGING && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) : (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.DRAGGING || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN))) {
                                    // Don't resize header yet - see #294 - header size will be refreshed on panel state change
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(4449)) {
                                if (mSlidingPanel.getPanelHeight() != heightInPixels) {
                                    if (!ListenerUtil.mutListener.listen(4446)) {
                                        mSlidingPanel.setPanelHeight(heightInPixels);
                                    }
                                    if (!ListenerUtil.mutListener.listen(4447)) {
                                        mArrivalsListHeaderView.getLayoutParams().height = heightInPixels;
                                    }
                                    if (!ListenerUtil.mutListener.listen(4448)) {
                                        mArrivalsListHeaderSubView.getLayoutParams().height = heightInPixels;
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public int getPanelHeightPixels() {
                    if (!ListenerUtil.mutListener.listen(4451)) {
                        if (mSlidingPanel != null) {
                            return mSlidingPanel.getPanelHeight();
                        }
                    }
                    return -1;
                }
            };
        }
    }

    /**
     * Sets up the initial map state, based on a previous savedInstanceState for this activity,
     * or an Intent that was passed into this activity
     */
    private void setupMapState(Bundle savedInstanceState) {
        String stopId;
        String stopName;
        String stopCode;
        // Check savedInstanceState to see if there is a previous state for this activity
        if (savedInstanceState != null) {
            // We're recreating an instance with a previous state, so show the focused stop in panel
            stopId = savedInstanceState.getString(MapParams.STOP_ID);
            stopName = savedInstanceState.getString(MapParams.STOP_NAME);
            stopCode = savedInstanceState.getString(MapParams.STOP_CODE);
            if (!ListenerUtil.mutListener.listen(4470)) {
                if (stopId != null) {
                    if (!ListenerUtil.mutListener.listen(4468)) {
                        mFocusedStopId = stopId;
                    }
                    if (!ListenerUtil.mutListener.listen(4469)) {
                        // We don't have an ObaStop or ObaRoute mapping, so just pass in null for those
                        updateArrivalListFragment(stopId, stopName, stopCode, null, null);
                    }
                }
            }
        } else {
            // Check intent passed into Activity
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                // Did this activity start to focus on a stop?  If so, set focus and show arrival info
                stopId = bundle.getString(MapParams.STOP_ID);
                stopName = bundle.getString(MapParams.STOP_NAME);
                stopCode = bundle.getString(MapParams.STOP_CODE);
                double lat = bundle.getDouble(MapParams.CENTER_LAT);
                double lon = bundle.getDouble(MapParams.CENTER_LON);
                if (!ListenerUtil.mutListener.listen(4467)) {
                    if ((ListenerUtil.mutListener.listen(4464) ? ((ListenerUtil.mutListener.listen(4458) ? (stopId != null || (ListenerUtil.mutListener.listen(4457) ? (lat >= 0.0) : (ListenerUtil.mutListener.listen(4456) ? (lat <= 0.0) : (ListenerUtil.mutListener.listen(4455) ? (lat > 0.0) : (ListenerUtil.mutListener.listen(4454) ? (lat < 0.0) : (ListenerUtil.mutListener.listen(4453) ? (lat == 0.0) : (lat != 0.0))))))) : (stopId != null && (ListenerUtil.mutListener.listen(4457) ? (lat >= 0.0) : (ListenerUtil.mutListener.listen(4456) ? (lat <= 0.0) : (ListenerUtil.mutListener.listen(4455) ? (lat > 0.0) : (ListenerUtil.mutListener.listen(4454) ? (lat < 0.0) : (ListenerUtil.mutListener.listen(4453) ? (lat == 0.0) : (lat != 0.0)))))))) || (ListenerUtil.mutListener.listen(4463) ? (lon >= 0.0) : (ListenerUtil.mutListener.listen(4462) ? (lon <= 0.0) : (ListenerUtil.mutListener.listen(4461) ? (lon > 0.0) : (ListenerUtil.mutListener.listen(4460) ? (lon < 0.0) : (ListenerUtil.mutListener.listen(4459) ? (lon == 0.0) : (lon != 0.0))))))) : ((ListenerUtil.mutListener.listen(4458) ? (stopId != null || (ListenerUtil.mutListener.listen(4457) ? (lat >= 0.0) : (ListenerUtil.mutListener.listen(4456) ? (lat <= 0.0) : (ListenerUtil.mutListener.listen(4455) ? (lat > 0.0) : (ListenerUtil.mutListener.listen(4454) ? (lat < 0.0) : (ListenerUtil.mutListener.listen(4453) ? (lat == 0.0) : (lat != 0.0))))))) : (stopId != null && (ListenerUtil.mutListener.listen(4457) ? (lat >= 0.0) : (ListenerUtil.mutListener.listen(4456) ? (lat <= 0.0) : (ListenerUtil.mutListener.listen(4455) ? (lat > 0.0) : (ListenerUtil.mutListener.listen(4454) ? (lat < 0.0) : (ListenerUtil.mutListener.listen(4453) ? (lat == 0.0) : (lat != 0.0)))))))) && (ListenerUtil.mutListener.listen(4463) ? (lon >= 0.0) : (ListenerUtil.mutListener.listen(4462) ? (lon <= 0.0) : (ListenerUtil.mutListener.listen(4461) ? (lon > 0.0) : (ListenerUtil.mutListener.listen(4460) ? (lon < 0.0) : (ListenerUtil.mutListener.listen(4459) ? (lon == 0.0) : (lon != 0.0))))))))) {
                        if (!ListenerUtil.mutListener.listen(4465)) {
                            mFocusedStopId = stopId;
                        }
                        if (!ListenerUtil.mutListener.listen(4466)) {
                            updateArrivalListFragment(stopId, stopName, stopCode, null, null);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4471)) {
            mMapProgressBar = findViewById(R.id.progress_horizontal);
        }
    }

    /**
     * Setup permissions that are only requested if the user joins the travel behavior study. This
     * method must be called from #onCreate().
     *
     * A call to #requestPhysicalActivityPermission() invokes the permission request, and should only
     * be called in the case when the user opts into the study.
     * @param activity
     */
    private void setupPermissions(AppCompatActivity activity) {
        if (!ListenerUtil.mutListener.listen(4472)) {
            travelBehaviorPermissionsLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // User opt-ed into study and granted physical activity tracking - now request background location permissions (when targeting Android 11 we can't request both simultaneously)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        activity.requestPermissions(TravelBehaviorConstants.BACKGROUND_LOCATION_PERMISSION, BACKGROUND_LOCATION_PERMISSION_REQUEST);
                    }
                }
            });
        }
    }

    /**
     * Requests physical activity permissions, and then subsequently background location
     * permissions (based on the initialization in #setupPermissions() if the user grants physical
     * activity permissions. This method should only be called after the user opts into the travel behavior study.
     */
    public void requestPhysicalActivityPermission() {
        if (!ListenerUtil.mutListener.listen(4474)) {
            if (travelBehaviorPermissionsLauncher != null) {
                if (!ListenerUtil.mutListener.listen(4473)) {
                    travelBehaviorPermissionsLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION);
                }
            }
        }
    }

    /**
     * Our definition of collapsed is consistent with SlidingPanel pre-v3.0.0 definition - we don't
     * consider the panel changing from the hidden state to the collapsed state to be a "collapsed"
     * event.  v3.0.0 and higher fire the "collapsed" event when coming from the hidden state.
     * This method provides us with a collapsed state that is consistent with the pre-v3.0.0
     * definition
     * of a collapse event, to make our event model consistent with post v3.0.0 SlidingPanel.
     *
     * @return true if the panel isn't expanded or anchored, false if it is not
     */
    private boolean isSlidingPanelCollapsed() {
        return !((ListenerUtil.mutListener.listen(4475) ? (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED && mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED) : (mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mSlidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)));
    }

    public ArrivalsListFragment getArrivalsListFragment() {
        return mArrivalsListFragment;
    }

    private void checkBatteryOptimizations() {
        if (!ListenerUtil.mutListener.listen(4477)) {
            if ((ListenerUtil.mutListener.listen(4476) ? (PreferenceUtils.getBoolean(getString(R.string.not_request_battery_optimizations_key), false) && !TravelBehaviorUtils.isUserParticipatingInStudy()) : (PreferenceUtils.getBoolean(getString(R.string.not_request_battery_optimizations_key), false) || !TravelBehaviorUtils.isUserParticipatingInStudy()))) {
                return;
            }
        }
        Boolean ignoringBatteryOptimizations = Application.isIgnoringBatteryOptimizations(getApplicationContext());
        if (!ListenerUtil.mutListener.listen(4480)) {
            if ((ListenerUtil.mutListener.listen(4478) ? (ignoringBatteryOptimizations != null || !ignoringBatteryOptimizations) : (ignoringBatteryOptimizations != null && !ignoringBatteryOptimizations))) {
                if (!ListenerUtil.mutListener.listen(4479)) {
                    showIgnoreBatteryOptimizationDialog();
                }
            }
        }
    }

    private void showIgnoreBatteryOptimizationDialog() {
        if (!ListenerUtil.mutListener.listen(4481)) {
            new android.app.AlertDialog.Builder(this).setMessage(R.string.application_ignoring_battery_opt_message).setTitle(R.string.application_ignoring_battery_opt_title).setIcon(R.drawable.ic_alert_warning).setCancelable(false).setPositiveButton(R.string.travel_behavior_dialog_yes, (dialog, which) -> {
                if (PermissionUtils.hasGrantedAllPermissions(this, new String[] { Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS })) {
                    UIUtils.openBatteryIgnoreIntent(this);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[] { Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS }, BATTERY_OPTIMIZATIONS_PERMISSION_REQUEST);
                    }
                }
                PreferenceUtils.saveBoolean(getString(R.string.not_request_battery_optimizations_key), true);
            }).setNegativeButton(R.string.travel_behavior_dialog_no, (dialog, which) -> {
                PreferenceUtils.saveBoolean(getString(R.string.not_request_battery_optimizations_key), true);
            }).create().show();
        }
    }
}
