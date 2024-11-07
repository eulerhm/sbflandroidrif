/*
* Copyright (C) 2014-2015 University of South Florida (sjbarbeau@gmail.com)
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
package org.onebusaway.android.report.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.elements.ObaStopElement;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.map.googlemapsv2.BaseMapFragment;
import org.onebusaway.android.report.connection.GeocoderTask;
import org.onebusaway.android.report.connection.ServiceListTask;
import org.onebusaway.android.report.constants.ReportConstants;
import org.onebusaway.android.report.ui.adapter.EntrySpinnerAdapter;
import org.onebusaway.android.report.ui.adapter.SectionItem;
import org.onebusaway.android.report.ui.adapter.ServiceSpinnerItem;
import org.onebusaway.android.report.ui.adapter.SpinnerItem;
import org.onebusaway.android.report.ui.dialog.ReportSuccessDialog;
import org.onebusaway.android.report.ui.util.IssueLocationHelper;
import org.onebusaway.android.report.ui.util.ServiceUtils;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
import org.opentripplanner.routing.bike_rental.BikeRentalStation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import edu.usf.cutr.open311client.Open311;
import edu.usf.cutr.open311client.Open311Manager;
import edu.usf.cutr.open311client.models.Service;
import edu.usf.cutr.open311client.models.ServiceListRequest;
import edu.usf.cutr.open311client.models.ServiceListResponse;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class InfrastructureIssueActivity extends BaseReportActivity implements BaseMapFragment.OnFocusChangedListener, ServiceListTask.Callback, ReportProblemFragmentCallback, IssueLocationHelper.Callback, SimpleArrivalListFragment.Callback, GeocoderTask.Callback {

    private static final int REQUEST_CODE = 0;

    private static final String SHOW_STOP_MARKER = ".showMarker";

    private static final String SELECTED_SERVICE = ".selectedService";

    private static final String SELECTED_SERVICE_TYPE = ".selectedServiceType";

    private static final String RESTORED_SERVICE = ".restoredService";

    private static final String SHOW_CATEGORIES = ".showCategories";

    private static final String SHOW_INFO = ".showInfo";

    private static final String HEURISTIC_MATCH = ".isHeuristicMatch";

    private static final String ARRIVAL_LIST = ".arrivalList";

    private static final String TRIP_INFO = ".tripInfo";

    private static final String AGENCY_NAME = ".agencyName";

    private static final String BLOCK_ID = ".blockId";

    private static final String ACTION_BAR_TITLE = ".actionBarTitle";

    private static final String TUTORIAL_COUNTER = "tutorial_counter";

    /**
     * UI Elements
     */
    private EditText mAddressEditText;

    private Spinner mServicesSpinner;

    private RelativeLayout mBusStopHeader;

    // Map Fragment
    private BaseMapFragment mMapFragment;

    // Services spinner container
    private FrameLayout mServicesSpinnerFrameLayout;

    // Location helper for tracking the issue location
    private IssueLocationHelper mIssueLocationHelper;

    /**
     * Open311 client
     */
    private Open311 mOpen311;

    /**
     * Store the transit service type if there is no bus stop selected
     * Then, when a user selects a bus stop, find the transit service by type and show
     */
    private String mTransitServiceIssueTypeWithoutStop;

    /**
     * Selected transit service object
     */
    private Service mSelectedTransitService;

    /**
     * Select this issue type when the activity starts
     * If a user selects "report stop or trip problem" from the main activity, set the default
     * value to this variable
     */
    private String mDefaultIssueType;

    /**
     * True if the transit services were matched heuristically, and false if they were not
     */
    private boolean mIsAllTransitHeuristicMatch;

    /**
     * Restore this issue on rotation
     */
    private String mRestoredServiceName;

    /**
     * Selected arrival information for trip problem reporting
     */
    private ObaArrivalInfo mArrivalInfo;

    /**
     * Block ID for the trip in mArrivalInfo
     */
    private String mBlockId;

    /**
     * Agency name for trip problem reporting
     */
    private String mAgencyName;

    /**
     * For rotation changes:
     * Save instance state vars
     * Restores old selected categories and marker position
     */
    private boolean mShowCategories = false;

    private boolean mShowStopMarker = false;

    private boolean mShowArrivalListFragment = false;

    /**
     * Starts the InfrastructureIssueActivity.
     *
     * startActivityForResult was used to close the calling activity. This used in BaseReportActivity
     * to close it when a user submits an issue.
     *
     * @param activity The parent activity.
     * @param intent   The Intent containing focusId, lat, lon of the map
     */
    public static void start(Activity activity, Intent intent) {
        if (!ListenerUtil.mutListener.listen(11654)) {
            activity.startActivityForResult(makeIntent(activity, intent), REQUEST_CODE);
        }
    }

    /**
     * Starts the InfrastructureIssueActivity with a given open311 service category selected
     *
     * startActivityForResult was used to close the calling activity. This used in BaseReportActivity
     * to close it when a user submits an issue.
     *
     * @param activity The parent activity.
     * @param intent   The Intent containing focusId, lat, lon of the map
     */
    public static void startWithService(Activity activity, Intent intent, String serviceKeyword) {
        if (!ListenerUtil.mutListener.listen(11655)) {
            intent = makeIntent(activity, intent);
        }
        if (!ListenerUtil.mutListener.listen(11656)) {
            intent.putExtra(SELECTED_SERVICE, serviceKeyword);
        }
        if (!ListenerUtil.mutListener.listen(11657)) {
            activity.startActivityForResult(intent, REQUEST_CODE);
        }
    }

    /**
     * Starts the InfrastructureIssueActivity with a given open311 service category selected
     *
     * @param context The context of the parent activity.
     * @param intent   The Intent containing focusId, lat, lon of the map
     */
    public static void startWithService(Context context, Intent intent, String serviceKeyword) {
        if (!ListenerUtil.mutListener.listen(11658)) {
            intent = makeIntent(context, intent);
        }
        if (!ListenerUtil.mutListener.listen(11659)) {
            intent.putExtra(SELECTED_SERVICE, serviceKeyword);
        }
        if (!ListenerUtil.mutListener.listen(11660)) {
            context.startActivity(intent);
        }
    }

    /**
     * Starts the InfrastructureIssueActivity with a given open311 service category selected
     *
     * startActivityForResult was used to close the calling activity. This used in BaseReportActivity
     * to close it when a user submits an issue.
     *
     * @param activity       The parent activity.
     * @param intent         The Intent containing focusId, lat, lon of the map
     * @param obaArrivalInfo Arrival info for trip problems
     * @param agencyName     Name of the agency that operates the service
     * @param blockId        Block ID for the trip in obaArrivalInfo
     */
    public static void startWithService(Activity activity, Intent intent, String serviceKeyword, ObaArrivalInfo obaArrivalInfo, String agencyName, String blockId) {
        if (!ListenerUtil.mutListener.listen(11661)) {
            intent = makeIntent(activity, intent);
        }
        if (!ListenerUtil.mutListener.listen(11662)) {
            // Put trip issue specific data
            intent.putExtra(SELECTED_SERVICE, serviceKeyword);
        }
        if (!ListenerUtil.mutListener.listen(11663)) {
            intent.putExtra(TRIP_INFO, obaArrivalInfo);
        }
        if (!ListenerUtil.mutListener.listen(11664)) {
            intent.putExtra(AGENCY_NAME, agencyName);
        }
        if (!ListenerUtil.mutListener.listen(11665)) {
            intent.putExtra(BLOCK_ID, blockId);
        }
        if (!ListenerUtil.mutListener.listen(11666)) {
            activity.startActivityForResult(intent, REQUEST_CODE);
        }
    }

    /**
     * Returns an intent that will start the MapActivity with a particular stop
     * focused with the center of the map at a particular point.
     *
     * @param context The context of the activity.
     * @param intent  The Intent containing focusId, lat, lon of the map
     */
    public static Intent makeIntent(Context context, Intent intent) {
        Intent myIntent = new Intent(context, InfrastructureIssueActivity.class);
        if (!ListenerUtil.mutListener.listen(11667)) {
            myIntent.putExtra(MapParams.STOP_ID, intent.getStringExtra(MapParams.STOP_ID));
        }
        if (!ListenerUtil.mutListener.listen(11668)) {
            myIntent.putExtra(MapParams.STOP_NAME, intent.getStringExtra(MapParams.STOP_NAME));
        }
        if (!ListenerUtil.mutListener.listen(11669)) {
            myIntent.putExtra(MapParams.STOP_CODE, intent.getStringExtra(MapParams.STOP_CODE));
        }
        if (!ListenerUtil.mutListener.listen(11670)) {
            myIntent.putExtra(MapParams.CENTER_LAT, intent.getDoubleExtra(MapParams.CENTER_LAT, 0));
        }
        if (!ListenerUtil.mutListener.listen(11671)) {
            myIntent.putExtra(MapParams.CENTER_LON, intent.getDoubleExtra(MapParams.CENTER_LON, 0));
        }
        return myIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11672)) {
            supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        }
        if (!ListenerUtil.mutListener.listen(11673)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11674)) {
            setContentView(R.layout.infrastructure_issue);
        }
        if (!ListenerUtil.mutListener.listen(11675)) {
            setUpOpen311();
        }
        if (!ListenerUtil.mutListener.listen(11676)) {
            setUpProgressBar();
        }
        if (!ListenerUtil.mutListener.listen(11677)) {
            setupMapFragment(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11678)) {
            setupLocationHelper(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11679)) {
            setupViews();
        }
        if (!ListenerUtil.mutListener.listen(11680)) {
            setupIntentData(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11681)) {
            setupIconColors();
        }
        if (!ListenerUtil.mutListener.listen(11682)) {
            initLocation();
        }
        if (!ListenerUtil.mutListener.listen(11683)) {
            setActionBarTitle(savedInstanceState);
        }
    }

    /**
     * Set the intent parameters when the activity starts
     * @param savedInstanceState
     */
    private void setupIntentData(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11692)) {
            if (savedInstanceState == null) {
                double lat = getIntent().getDoubleExtra(MapParams.CENTER_LAT, 0);
                double lon = getIntent().getDoubleExtra(MapParams.CENTER_LON, 0);
                // Set the focus to the current stop
                String stopId = getIntent().getStringExtra(MapParams.STOP_ID);
                String stopName = getIntent().getStringExtra(MapParams.STOP_NAME);
                String stopCode = getIntent().getStringExtra(MapParams.STOP_CODE);
                if (!ListenerUtil.mutListener.listen(11687)) {
                    if (stopId != null) {
                        if (!ListenerUtil.mutListener.listen(11684)) {
                            mIssueLocationHelper.setObaStop(new ObaStopElement(stopId, lat, lon, stopName, stopCode));
                        }
                        if (!ListenerUtil.mutListener.listen(11685)) {
                            showBusStopHeader(stopName);
                        }
                        if (!ListenerUtil.mutListener.listen(11686)) {
                            mShowStopMarker = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11688)) {
                    mArrivalInfo = (ObaArrivalInfo) getIntent().getSerializableExtra(TRIP_INFO);
                }
                if (!ListenerUtil.mutListener.listen(11689)) {
                    mAgencyName = getIntent().getStringExtra(AGENCY_NAME);
                }
                if (!ListenerUtil.mutListener.listen(11690)) {
                    mBlockId = getIntent().getStringExtra(BLOCK_ID);
                }
                if (!ListenerUtil.mutListener.listen(11691)) {
                    mDefaultIssueType = getIntent().getStringExtra(SELECTED_SERVICE);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(11693)) {
            ShowcaseViewUtils.hideShowcaseView();
        }
        if (!ListenerUtil.mutListener.listen(11694)) {
            super.onPause();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(11695)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(11696)) {
            outState.putFloat(MapParams.ZOOM, mMapFragment.getMapView().getZoomLevelAsFloat());
        }
        ObaStop obaStop = mIssueLocationHelper.getObaStop();
        if (!ListenerUtil.mutListener.listen(11702)) {
            if (obaStop != null) {
                String stopId = obaStop.getId();
                if (!ListenerUtil.mutListener.listen(11697)) {
                    getIntent().putExtra(MapParams.STOP_ID, stopId);
                }
                if (!ListenerUtil.mutListener.listen(11698)) {
                    outState.putString(MapParams.STOP_ID, stopId);
                }
                if (!ListenerUtil.mutListener.listen(11699)) {
                    outState.putString(MapParams.STOP_NAME, obaStop.getName());
                }
                if (!ListenerUtil.mutListener.listen(11700)) {
                    outState.putString(MapParams.STOP_CODE, obaStop.getStopCode());
                }
                if (!ListenerUtil.mutListener.listen(11701)) {
                    outState.putBoolean(SHOW_STOP_MARKER, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11703)) {
            outState.putDouble(MapParams.CENTER_LAT, mIssueLocationHelper.getIssueLocation().getLatitude());
        }
        if (!ListenerUtil.mutListener.listen(11704)) {
            outState.putDouble(MapParams.CENTER_LON, mIssueLocationHelper.getIssueLocation().getLongitude());
        }
        SpinnerItem spinnerItem = (SpinnerItem) mServicesSpinner.getSelectedItem();
        if (!ListenerUtil.mutListener.listen(11711)) {
            if ((ListenerUtil.mutListener.listen(11708) ? (spinnerItem != null || ((ListenerUtil.mutListener.listen(11707) ? ((ListenerUtil.mutListener.listen(11705) ? (!spinnerItem.isHint() || !spinnerItem.isSection()) : (!spinnerItem.isHint() && !spinnerItem.isSection())) && ((ListenerUtil.mutListener.listen(11706) ? (spinnerItem.isHint() || mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE) : (spinnerItem.isHint() && mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE)))) : ((ListenerUtil.mutListener.listen(11705) ? (!spinnerItem.isHint() || !spinnerItem.isSection()) : (!spinnerItem.isHint() && !spinnerItem.isSection())) || ((ListenerUtil.mutListener.listen(11706) ? (spinnerItem.isHint() || mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE) : (spinnerItem.isHint() && mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE))))))) : (spinnerItem != null && ((ListenerUtil.mutListener.listen(11707) ? ((ListenerUtil.mutListener.listen(11705) ? (!spinnerItem.isHint() || !spinnerItem.isSection()) : (!spinnerItem.isHint() && !spinnerItem.isSection())) && ((ListenerUtil.mutListener.listen(11706) ? (spinnerItem.isHint() || mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE) : (spinnerItem.isHint() && mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE)))) : ((ListenerUtil.mutListener.listen(11705) ? (!spinnerItem.isHint() || !spinnerItem.isSection()) : (!spinnerItem.isHint() && !spinnerItem.isSection())) || ((ListenerUtil.mutListener.listen(11706) ? (spinnerItem.isHint() || mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE) : (spinnerItem.isHint() && mServicesSpinnerFrameLayout.getVisibility() == View.VISIBLE))))))))) {
                Service service = ((ServiceSpinnerItem) spinnerItem).getService();
                if (!ListenerUtil.mutListener.listen(11709)) {
                    outState.putString(RESTORED_SERVICE, service.getService_name());
                }
                if (!ListenerUtil.mutListener.listen(11710)) {
                    outState.putBoolean(SHOW_CATEGORIES, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11713)) {
            if (isInfoVisible()) {
                String infoText = ((TextView) mInfoHeader.findViewById(R.id.ri_info_text)).getText().toString();
                if (!ListenerUtil.mutListener.listen(11712)) {
                    outState.putString(SHOW_INFO, infoText);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11714)) {
            outState.putBoolean(ARRIVAL_LIST, mShowArrivalListFragment);
        }
        if (!ListenerUtil.mutListener.listen(11715)) {
            outState.putBoolean(HEURISTIC_MATCH, mIsAllTransitHeuristicMatch);
        }
        if (!ListenerUtil.mutListener.listen(11716)) {
            outState.putString(AGENCY_NAME, mAgencyName);
        }
        if (!ListenerUtil.mutListener.listen(11717)) {
            outState.putString(BLOCK_ID, mBlockId);
        }
        if (!ListenerUtil.mutListener.listen(11718)) {
            outState.putString(SELECTED_SERVICE_TYPE, mTransitServiceIssueTypeWithoutStop);
        }
        if (!ListenerUtil.mutListener.listen(11719)) {
            outState.putString(ACTION_BAR_TITLE, getTitle().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11720)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11736)) {
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(11721)) {
                    mShowCategories = savedInstanceState.getBoolean(SHOW_CATEGORIES, false);
                }
                if (!ListenerUtil.mutListener.listen(11722)) {
                    mShowStopMarker = savedInstanceState.getBoolean(SHOW_STOP_MARKER, false);
                }
                if (!ListenerUtil.mutListener.listen(11723)) {
                    mShowArrivalListFragment = savedInstanceState.getBoolean(ARRIVAL_LIST, false);
                }
                if (!ListenerUtil.mutListener.listen(11724)) {
                    mRestoredServiceName = savedInstanceState.getString(RESTORED_SERVICE);
                }
                if (!ListenerUtil.mutListener.listen(11725)) {
                    mAgencyName = savedInstanceState.getString(AGENCY_NAME);
                }
                if (!ListenerUtil.mutListener.listen(11726)) {
                    mBlockId = savedInstanceState.getString(BLOCK_ID);
                }
                if (!ListenerUtil.mutListener.listen(11727)) {
                    mTransitServiceIssueTypeWithoutStop = savedInstanceState.getString(SELECTED_SERVICE_TYPE);
                }
                if (!ListenerUtil.mutListener.listen(11728)) {
                    mIsAllTransitHeuristicMatch = savedInstanceState.getBoolean(HEURISTIC_MATCH);
                }
                String bundleStopId = savedInstanceState.getString(MapParams.STOP_ID);
                String stopName = savedInstanceState.getString(MapParams.STOP_NAME);
                String stopCode = savedInstanceState.getString(MapParams.STOP_CODE);
                if (!ListenerUtil.mutListener.listen(11730)) {
                    if (bundleStopId != null) {
                        double lat = savedInstanceState.getDouble(MapParams.CENTER_LAT, 0);
                        double lon = savedInstanceState.getDouble(MapParams.CENTER_LON, 0);
                        Location location = LocationUtils.makeLocation(lat, lon);
                        if (!ListenerUtil.mutListener.listen(11729)) {
                            mIssueLocationHelper.updateMarkerPosition(location, new ObaStopElement(bundleStopId, lat, lon, stopName, stopCode));
                        }
                    }
                }
                String infoText = savedInstanceState.getString(SHOW_INFO);
                if (!ListenerUtil.mutListener.listen(11732)) {
                    if (infoText != null) {
                        if (!ListenerUtil.mutListener.listen(11731)) {
                            addInfoText(infoText);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11735)) {
                    if (mShowArrivalListFragment) {
                        if (!ListenerUtil.mutListener.listen(11733)) {
                            removeTripProblemFragment();
                        }
                        if (!ListenerUtil.mutListener.listen(11734)) {
                            showArrivalListFragment(mIssueLocationHelper.getObaStop());
                        }
                    }
                }
            }
        }
    }

    /**
     * Set default open311 client
     */
    private void setUpOpen311() {
        if (!ListenerUtil.mutListener.listen(11737)) {
            mOpen311 = Open311Manager.getDefaultOpen311();
        }
    }

    /**
     * Setting up the BaseMapFragment
     * BaseMapFragment was used to implement a map.
     */
    private void setupMapFragment(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(BaseMapFragment.TAG);
        if (!ListenerUtil.mutListener.listen(11740)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(11738)) {
                    mMapFragment = (BaseMapFragment) fragment;
                }
                if (!ListenerUtil.mutListener.listen(11739)) {
                    mMapFragment.setOnFocusChangeListener(this);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11745)) {
            if (mMapFragment == null) {
                if (!ListenerUtil.mutListener.listen(11741)) {
                    mMapFragment = new BaseMapFragment();
                }
                if (!ListenerUtil.mutListener.listen(11742)) {
                    mMapFragment.setArguments(bundle);
                }
                if (!ListenerUtil.mutListener.listen(11743)) {
                    // Register listener for map focus callbacks
                    mMapFragment.setOnFocusChangeListener(this);
                }
                if (!ListenerUtil.mutListener.listen(11744)) {
                    fm.beginTransaction().add(R.id.ri_frame_map_view, mMapFragment, BaseMapFragment.TAG).commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11746)) {
            fm.beginTransaction().show(mMapFragment).commit();
        }
    }

    /**
     * Setting up the location helper
     * IssueLocationHelper helps tracking the issue location and issue stop
     */
    private void setupLocationHelper(Bundle savedInstanceState) {
        double lat;
        double lon;
        if (savedInstanceState == null) {
            lat = getIntent().getDoubleExtra(MapParams.CENTER_LAT, 0);
            lon = getIntent().getDoubleExtra(MapParams.CENTER_LON, 0);
        } else {
            lat = savedInstanceState.getDouble(MapParams.CENTER_LAT, 0);
            lon = savedInstanceState.getDouble(MapParams.CENTER_LON, 0);
        }
        Location mapCenterLocation = LocationUtils.makeLocation(lat, lon);
        if (!ListenerUtil.mutListener.listen(11747)) {
            mIssueLocationHelper = new IssueLocationHelper(mapCenterLocation, this);
        }
        if (!ListenerUtil.mutListener.listen(11748)) {
            // Set map center location
            mMapFragment.setMapCenter(mapCenterLocation, true, false);
        }
    }

    /**
     * Initialize UI components
     */
    private void setupViews() {
        if (!ListenerUtil.mutListener.listen(11750)) {
            if (getSupportActionBar() != null) {
                if (!ListenerUtil.mutListener.listen(11749)) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11751)) {
            mServicesSpinnerFrameLayout = (FrameLayout) findViewById(R.id.ri_spinnerView);
        }
        if (!ListenerUtil.mutListener.listen(11752)) {
            mAddressEditText = (EditText) findViewById(R.id.ri_address_editText);
        }
        if (!ListenerUtil.mutListener.listen(11755)) {
            mAddressEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!ListenerUtil.mutListener.listen(11754)) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            if (!ListenerUtil.mutListener.listen(11753)) {
                                searchAddress();
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        CustomScrollView mainScrollView = (CustomScrollView) findViewById(R.id.ri_scrollView);
        if (!ListenerUtil.mutListener.listen(11756)) {
            mainScrollView.addInterceptScrollView(findViewById(R.id.ri_frame_map_view));
        }
        if (!ListenerUtil.mutListener.listen(11757)) {
            mServicesSpinner = (Spinner) findViewById(R.id.ri_spinnerServices);
        }
        if (!ListenerUtil.mutListener.listen(11764)) {
            mServicesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if (!ListenerUtil.mutListener.listen(11759)) {
                        if (mShowCategories) {
                            if (!ListenerUtil.mutListener.listen(11758)) {
                                mShowCategories = false;
                            }
                            return;
                        }
                    }
                    SpinnerItem spinnerItem = (SpinnerItem) mServicesSpinner.getSelectedItem();
                    if (!ListenerUtil.mutListener.listen(11763)) {
                        if ((ListenerUtil.mutListener.listen(11760) ? (!spinnerItem.isHint() || !spinnerItem.isSection()) : (!spinnerItem.isHint() && !spinnerItem.isSection()))) {
                            Service service = ((ServiceSpinnerItem) spinnerItem).getService();
                            if (!ListenerUtil.mutListener.listen(11762)) {
                                onSpinnerItemSelected(service);
                            }
                        } else if (spinnerItem.isHint()) {
                            if (!ListenerUtil.mutListener.listen(11761)) {
                                clearReportingFragments(false);
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11765)) {
            mBusStopHeader = (RelativeLayout) findViewById(R.id.bus_stop_header);
        }
    }

    private void setActionBarTitle(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11768)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(11767)) {
                    setActionBarTitle(mDefaultIssueType);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11766)) {
                    setTitle(savedInstanceState.getString(ACTION_BAR_TITLE));
                }
            }
        }
    }

    /**
     * Set action bar title by issue type
     *
     * @param issueType could be stop, trip, dynamic_stop or dynamic_trip
     */
    private void setActionBarTitle(String issueType) {
        if (!ListenerUtil.mutListener.listen(11772)) {
            if (ServiceUtils.isTransitStopServiceByType(issueType)) {
                if (!ListenerUtil.mutListener.listen(11771)) {
                    setTitle(getString(R.string.rt_stop_problem_title));
                }
            } else if (ServiceUtils.isTransitTripServiceByType(issueType)) {
                if (!ListenerUtil.mutListener.listen(11770)) {
                    setTitle(getString(R.string.rt_arrival_problem_title));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11769)) {
                    setTitle(getString(R.string.rt_infrastructure_problem_title));
                }
            }
        }
    }

    private void setupIconColors() {
        if (!ListenerUtil.mutListener.listen(11773)) {
            ((ImageView) findViewById(R.id.ri_ic_location)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11774)) {
            ((ImageView) findViewById(R.id.ri_ic_category)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11775)) {
            ((ImageView) findViewById(R.id.ri_header_location)).setColorFilter(getResources().getColor(android.R.color.white));
        }
    }

    /**
     * Show the current location on the map
     */
    private void initLocation() {
        if (!ListenerUtil.mutListener.listen(11776)) {
            syncAddress(mIssueLocationHelper.getIssueLocation());
        }
    }

    private void onSpinnerItemSelected(Service service) {
        if (!ListenerUtil.mutListener.listen(11777)) {
            // Set static issue type to null
            mTransitServiceIssueTypeWithoutStop = null;
        }
        if (!ListenerUtil.mutListener.listen(11778)) {
            mSelectedTransitService = null;
        }
        if (!ListenerUtil.mutListener.listen(11785)) {
            if ((ListenerUtil.mutListener.listen(11780) ? (service.getService_code() != null || !((ListenerUtil.mutListener.listen(11779) ? (service.getType() != null || service.getType().contains(ReportConstants.DYNAMIC_SERVICE)) : (service.getType() != null && service.getType().contains(ReportConstants.DYNAMIC_SERVICE))))) : (service.getService_code() != null && !((ListenerUtil.mutListener.listen(11779) ? (service.getType() != null || service.getType().contains(ReportConstants.DYNAMIC_SERVICE)) : (service.getType() != null && service.getType().contains(ReportConstants.DYNAMIC_SERVICE))))))) {
                if (!ListenerUtil.mutListener.listen(11784)) {
                    showOpen311Reporting(service);
                }
            } else if (!ReportConstants.DEFAULT_SERVICE.equalsIgnoreCase(service.getType())) {
                if (!ListenerUtil.mutListener.listen(11781)) {
                    mSelectedTransitService = service;
                }
                if (!ListenerUtil.mutListener.listen(11782)) {
                    // Remove the info text for select category
                    removeInfoText();
                }
                if (!ListenerUtil.mutListener.listen(11783)) {
                    showTransitService(service.getType());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11786)) {
            // Set action bar title based on the selected issue
            setActionBarTitle(service.getType());
        }
    }

    /**
     * Places a marker into the map from given Location
     *
     * @param location position for marker
     */
    private void updateMarkerPosition(Location location) {
        int markerId = mMapFragment.addMarker(location, null);
        if (!ListenerUtil.mutListener.listen(11787)) {
            mIssueLocationHelper.handleMarkerUpdate(markerId);
        }
    }

    /**
     * Called when the issue location changes
     * Retrieves Open311 services from current address
     *
     * @param location current issue location
     */
    private void syncAddress(Location location) {
        if (!ListenerUtil.mutListener.listen(11788)) {
            syncAddressString(location);
        }
        if (!ListenerUtil.mutListener.listen(11789)) {
            showProgress(Boolean.TRUE);
        }
        ServiceListRequest slr = new ServiceListRequest(location.getLatitude(), location.getLongitude());
        List<Open311> open311List = Open311Manager.getAllOpen311();
        ServiceListTask serviceListTask = new ServiceListTask(slr, open311List, this);
        if (!ListenerUtil.mutListener.listen(11790)) {
            serviceListTask.execute();
        }
    }

    /**
     * Search address string from mAddressEditText
     */
    private void searchAddress() {
        if (!ListenerUtil.mutListener.listen(11791)) {
            showProgress(Boolean.TRUE);
        }
        String addressString = mAddressEditText.getText().toString();
        Location location = getLocationByAddress(addressString);
        if (!ListenerUtil.mutListener.listen(11795)) {
            if (location != null) {
                if (!ListenerUtil.mutListener.listen(11793)) {
                    mMapFragment.setMapCenter(location, true, true);
                }
                if (!ListenerUtil.mutListener.listen(11794)) {
                    syncAddress(location);
                }
            } else {
                String message = getResources().getString(R.string.ri_address_not_found);
                if (!ListenerUtil.mutListener.listen(11792)) {
                    createToastMessage(message);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11796)) {
            showProgress(Boolean.FALSE);
        }
    }

    /**
     * Calculates the address of a given location
     *
     * @param location takes location object
     */
    private void syncAddressString(Location location) {
        GeocoderTask gct = new GeocoderTask(this, location, this);
        if (!ListenerUtil.mutListener.listen(11797)) {
            gct.execute();
        }
    }

    /**
     * Converts plain address string to Location object
     *
     * @param addressString takes address string
     * @return Location of given address String
     */
    public Location getLocationByAddress(String addressString) {
        Geocoder coder = new Geocoder(this);
        List<Address> addressList;
        Location location;
        try {
            addressList = coder.getFromLocationName(addressString, 3);
            if (!ListenerUtil.mutListener.listen(11800)) {
                if ((ListenerUtil.mutListener.listen(11799) ? (addressList == null && addressList.size() == 0) : (addressList == null || addressList.size() == 0))) {
                    return null;
                }
            }
            Address address = addressList.get(0);
            location = new Location("");
            if (!ListenerUtil.mutListener.listen(11801)) {
                location.setLatitude(address.getLatitude());
            }
            if (!ListenerUtil.mutListener.listen(11802)) {
                location.setLongitude(address.getLongitude());
            }
            return location;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(11798)) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onClearMarker(int markerId) {
        if (!ListenerUtil.mutListener.listen(11803)) {
            mMapFragment.removeMarker(markerId);
        }
    }

    /**
     * Called by the BaseMapFragment when a stop obtains focus, or no stops have focus
     *
     * @param stop   the ObaStop that obtained focus, or null if no stop is in focus
     * @param routes a HashMap of all route display names that serve this stop - key is routeId
     */
    @Override
    public void onFocusChanged(ObaStop stop, HashMap<String, ObaRoute> routes, Location location) {
        if (!ListenerUtil.mutListener.listen(11804)) {
            mIssueLocationHelper.updateMarkerPosition(location, stop);
        }
        if (!ListenerUtil.mutListener.listen(11808)) {
            // Don't call syncAddress if you are restoring the instance
            if (mShowStopMarker) {
                if (!ListenerUtil.mutListener.listen(11806)) {
                    if (stop != null) {
                        if (!ListenerUtil.mutListener.listen(11805)) {
                            // Show bus stop name on the header
                            showBusStopHeader(stop.getName());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11807)) {
                    mShowStopMarker = false;
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11809)) {
            // Clear all reporting fragments
            clearReportingFragments();
        }
        if (!ListenerUtil.mutListener.listen(11820)) {
            if (stop != null) {
                if (!ListenerUtil.mutListener.listen(11812)) {
                    // Clear manually added markers
                    mIssueLocationHelper.clearMarkers();
                }
                if (!ListenerUtil.mutListener.listen(11813)) {
                    // Show bus stop name on the header
                    showBusStopHeader(stop.getName());
                }
                if (!ListenerUtil.mutListener.listen(11814)) {
                    showServicesSpinner();
                }
                if (!ListenerUtil.mutListener.listen(11818)) {
                    if (mTransitServiceIssueTypeWithoutStop != null) {
                        if (!ListenerUtil.mutListener.listen(11816)) {
                            showTransitService(mTransitServiceIssueTypeWithoutStop);
                        }
                        if (!ListenerUtil.mutListener.listen(11817)) {
                            syncAddressString(location);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11815)) {
                            syncAddress(stop.getLocation());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11819)) {
                    // Clear static issue type
                    mTransitServiceIssueTypeWithoutStop = null;
                }
            } else if (location != null) {
                if (!ListenerUtil.mutListener.listen(11810)) {
                    hideBusStopHeader();
                }
                if (!ListenerUtil.mutListener.listen(11811)) {
                    syncAddress(location);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11821)) {
            // Set action bar title based on the selected issue
            setActionBarTitle("");
        }
    }

    @Override
    public void onFocusChanged(BikeRentalStation bikeRentalStation) {
    }

    @Override
    public void onReportSent() {
        if (!ListenerUtil.mutListener.listen(11822)) {
            (new ReportSuccessDialog()).show(getSupportFragmentManager(), ReportSuccessDialog.TAG);
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(11823)) {
            super.onBackPressed();
        }
        if (!ListenerUtil.mutListener.listen(11824)) {
            // If a fragment closes via back button, show 'Choose a Problem' category
            mServicesSpinner.setSelection(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                if (!ListenerUtil.mutListener.listen(11825)) {
                    finishActivityWithResult();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called by the ServicesTask when Open311 endpoint returns ServiceList
     *
     * @param services ServiceListResponse
     * @param open311  returns active open311
     */
    @Override
    public void onServicesTaskCompleted(ServiceListResponse services, Open311 open311) {
        if (!ListenerUtil.mutListener.listen(11826)) {
            // Close progress
            showProgress(Boolean.FALSE);
        }
        if (!ListenerUtil.mutListener.listen(11827)) {
            // Set main open311
            this.mOpen311 = open311;
        }
        if (!ListenerUtil.mutListener.listen(11828)) {
            prepareServiceList(services);
        }
    }

    /**
     * Called when geocoder converts the location to an address string
     *
     * @param address the address string from given location
     */
    @Override
    public void onGeocoderTaskCompleted(String address) {
        if (!ListenerUtil.mutListener.listen(11829)) {
            mAddressEditText.setText(address);
        }
    }

    /**
     * Prepares the service lists and shows as categories in the screen
     * Adds static service categories (stop and trip problems) if they are
     * not specified by open311 endpoint
     */
    private void prepareServiceList(ServiceListResponse services) {
        // Create the service list
        List<Service> serviceList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(11836)) {
            // Add services to list if service response is successful
            if ((ListenerUtil.mutListener.listen(11831) ? ((ListenerUtil.mutListener.listen(11830) ? (services != null || services.isSuccess()) : (services != null && services.isSuccess())) || Open311Manager.isAreaManagedByOpen311(services.getServiceList())) : ((ListenerUtil.mutListener.listen(11830) ? (services != null || services.isSuccess()) : (services != null && services.isSuccess())) && Open311Manager.isAreaManagedByOpen311(services.getServiceList())))) {
                if (!ListenerUtil.mutListener.listen(11835)) {
                    {
                        long _loopCounter162 = 0;
                        for (Service s : services.getServiceList()) {
                            ListenerUtil.loopListener.listen("_loopCounter162", ++_loopCounter162);
                            if (!ListenerUtil.mutListener.listen(11834)) {
                                if ((ListenerUtil.mutListener.listen(11832) ? (s.getService_name() != null || s.getService_code() != null) : (s.getService_name() != null && s.getService_code() != null))) {
                                    if (!ListenerUtil.mutListener.listen(11833)) {
                                        serviceList.add(s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11844)) {
            if (mShowStopMarker) {
                if (!ListenerUtil.mutListener.listen(11842)) {
                    mMapFragment.setFocusStop(mIssueLocationHelper.getObaStop(), new ArrayList<ObaRoute>());
                }
                if (!ListenerUtil.mutListener.listen(11843)) {
                    showServicesSpinner();
                }
            } else if (Open311Manager.isAreaManagedByOpen311(serviceList)) {
                if (!ListenerUtil.mutListener.listen(11840)) {
                    // Set marker on the map if there are open311 services
                    updateMarkerPosition(mIssueLocationHelper.getIssueLocation());
                }
                if (!ListenerUtil.mutListener.listen(11841)) {
                    showServicesSpinner();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11839)) {
                    // then hide the categories
                    if (mIssueLocationHelper.getObaStop() == null) {
                        if (!ListenerUtil.mutListener.listen(11837)) {
                            hideServicesSpinner();
                        }
                        if (!ListenerUtil.mutListener.listen(11838)) {
                            // Show information to the user if there is no error on location
                            addInfoText(getString(R.string.report_dialog_stop_header));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11845)) {
            // Mark the services that are transit-related
            mIsAllTransitHeuristicMatch = ServiceUtils.markTransitServices(getApplicationContext(), serviceList);
        }
        /**
         * Map the group names with service list
         */
        Map<String, List<Service>> serviceListMap = new TreeMap<>();
        if (!ListenerUtil.mutListener.listen(11851)) {
            {
                long _loopCounter163 = 0;
                for (Service s : serviceList) {
                    ListenerUtil.loopListener.listen("_loopCounter163", ++_loopCounter163);
                    String groupName = s.getGroup() == null ? getString(R.string.ri_others) : s.getGroup();
                    List<Service> mappedList = serviceListMap.get(groupName);
                    if (!ListenerUtil.mutListener.listen(11850)) {
                        if (mappedList != null) {
                            if (!ListenerUtil.mutListener.listen(11849)) {
                                mappedList.add(s);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(11846)) {
                                mappedList = new ArrayList<>();
                            }
                            if (!ListenerUtil.mutListener.listen(11847)) {
                                mappedList.add(s);
                            }
                            if (!ListenerUtil.mutListener.listen(11848)) {
                                serviceListMap.put(groupName, mappedList);
                            }
                        }
                    }
                }
            }
        }
        /**
         * Create Ordered Spinner items
         */
        ArrayList<SpinnerItem> spinnerItems = new ArrayList<>();
        ServiceSpinnerItem hintServiceSpinnerItem = new ServiceSpinnerItem(new Service(getString(R.string.ri_service_default), ReportConstants.DEFAULT_SERVICE));
        if (!ListenerUtil.mutListener.listen(11852)) {
            hintServiceSpinnerItem.setHint(true);
        }
        if (!ListenerUtil.mutListener.listen(11853)) {
            spinnerItems.add(hintServiceSpinnerItem);
        }
        if (!ListenerUtil.mutListener.listen(11857)) {
            // Create Transit categories first
            if (serviceListMap.get(ReportConstants.ISSUE_GROUP_TRANSIT) != null) {
                if (!ListenerUtil.mutListener.listen(11854)) {
                    spinnerItems.add(new SectionItem(ReportConstants.ISSUE_GROUP_TRANSIT));
                }
                if (!ListenerUtil.mutListener.listen(11856)) {
                    {
                        long _loopCounter164 = 0;
                        for (Service s : serviceListMap.get(ReportConstants.ISSUE_GROUP_TRANSIT)) {
                            ListenerUtil.loopListener.listen("_loopCounter164", ++_loopCounter164);
                            if (!ListenerUtil.mutListener.listen(11855)) {
                                spinnerItems.add(new ServiceSpinnerItem(s));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11862)) {
            {
                long _loopCounter166 = 0;
                // Create the rest of the categories
                for (String key : serviceListMap.keySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter166", ++_loopCounter166);
                    if (!ListenerUtil.mutListener.listen(11858)) {
                        // Skip if it is transit category
                        if (ReportConstants.ISSUE_GROUP_TRANSIT.equals(key)) {
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(11859)) {
                        spinnerItems.add(new SectionItem(key));
                    }
                    if (!ListenerUtil.mutListener.listen(11861)) {
                        {
                            long _loopCounter165 = 0;
                            for (Service s : serviceListMap.get(key)) {
                                ListenerUtil.loopListener.listen("_loopCounter165", ++_loopCounter165);
                                if (!ListenerUtil.mutListener.listen(11860)) {
                                    spinnerItems.add(new ServiceSpinnerItem(s));
                                }
                            }
                        }
                    }
                }
            }
        }
        EntrySpinnerAdapter adapter = new EntrySpinnerAdapter(this, spinnerItems);
        if (!ListenerUtil.mutListener.listen(11863)) {
            mServicesSpinner.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(11866)) {
            if (mDefaultIssueType != null) {
                if (!ListenerUtil.mutListener.listen(11865)) {
                    // Select an default issue category programmatically
                    selectDefaultTransitCategory(spinnerItems);
                }
            } else if (mRestoredServiceName != null) {
                if (!ListenerUtil.mutListener.listen(11864)) {
                    restoreSelection(spinnerItems);
                }
            }
        }
    }

    /**
     * Programmatically select a issue category on rotation
     *
     * @param spinnerItems the issue services loaded into the services spinner
     */
    private void restoreSelection(ArrayList<SpinnerItem> spinnerItems) {
        if (!ListenerUtil.mutListener.listen(11878)) {
            {
                long _loopCounter167 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(11877) ? (i >= spinnerItems.size()) : (ListenerUtil.mutListener.listen(11876) ? (i <= spinnerItems.size()) : (ListenerUtil.mutListener.listen(11875) ? (i > spinnerItems.size()) : (ListenerUtil.mutListener.listen(11874) ? (i != spinnerItems.size()) : (ListenerUtil.mutListener.listen(11873) ? (i == spinnerItems.size()) : (i < spinnerItems.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter167", ++_loopCounter167);
                    SpinnerItem item = spinnerItems.get(i);
                    if (!ListenerUtil.mutListener.listen(11872)) {
                        if (item instanceof ServiceSpinnerItem) {
                            Service service = ((ServiceSpinnerItem) item).getService();
                            if (!ListenerUtil.mutListener.listen(11871)) {
                                if (service.getService_name().equals(mRestoredServiceName)) {
                                    if (!ListenerUtil.mutListener.listen(11867)) {
                                        mServicesSpinner.setSelection(i, true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(11868)) {
                                        mRestoredServiceName = null;
                                    }
                                    if (!ListenerUtil.mutListener.listen(11870)) {
                                        if (ServiceUtils.isTransitServiceByType(service.getType())) {
                                            if (!ListenerUtil.mutListener.listen(11869)) {
                                                mSelectedTransitService = service;
                                            }
                                        }
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
     * Programmatically select a default issue category from the spinner
     *
     * @param spinnerItems the issue services loaded into the services spinner
     */
    private void selectDefaultTransitCategory(ArrayList<SpinnerItem> spinnerItems) {
        int selectPosition = -1;
        Service selectedService = null;
        if (!ListenerUtil.mutListener.listen(11897)) {
            {
                long _loopCounter168 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(11896) ? (i >= spinnerItems.size()) : (ListenerUtil.mutListener.listen(11895) ? (i <= spinnerItems.size()) : (ListenerUtil.mutListener.listen(11894) ? (i > spinnerItems.size()) : (ListenerUtil.mutListener.listen(11893) ? (i != spinnerItems.size()) : (ListenerUtil.mutListener.listen(11892) ? (i == spinnerItems.size()) : (i < spinnerItems.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter168", ++_loopCounter168);
                    SpinnerItem item = spinnerItems.get(i);
                    if (!ListenerUtil.mutListener.listen(11891)) {
                        if (item instanceof ServiceSpinnerItem) {
                            Service service = ((ServiceSpinnerItem) item).getService();
                            boolean transitServiceFound = false;
                            if (!ListenerUtil.mutListener.listen(11884)) {
                                if ((ListenerUtil.mutListener.listen(11880) ? ((ListenerUtil.mutListener.listen(11879) ? (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) || ServiceUtils.isTransitStopServiceByType(service.getType())) : (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) && ServiceUtils.isTransitStopServiceByType(service.getType()))) || !mIsAllTransitHeuristicMatch) : ((ListenerUtil.mutListener.listen(11879) ? (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) || ServiceUtils.isTransitStopServiceByType(service.getType())) : (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) && ServiceUtils.isTransitStopServiceByType(service.getType()))) && !mIsAllTransitHeuristicMatch))) {
                                    if (!ListenerUtil.mutListener.listen(11883)) {
                                        // We have an explicit (not heuristic-based) match for stop problem
                                        transitServiceFound = true;
                                    }
                                } else if ((ListenerUtil.mutListener.listen(11881) ? (getString(R.string.ri_selected_service_trip).equals(mDefaultIssueType) || ServiceUtils.isTransitTripServiceByType(service.getType())) : (getString(R.string.ri_selected_service_trip).equals(mDefaultIssueType) && ServiceUtils.isTransitTripServiceByType(service.getType())))) {
                                    if (!ListenerUtil.mutListener.listen(11882)) {
                                        transitServiceFound = true;
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11890)) {
                                if (transitServiceFound) {
                                    if (!ListenerUtil.mutListener.listen(11885)) {
                                        selectPosition = i;
                                    }
                                    if (!ListenerUtil.mutListener.listen(11886)) {
                                        selectedService = service;
                                    }
                                    if (!ListenerUtil.mutListener.listen(11889)) {
                                        // If transit service selected and no bus stop selected remove markers
                                        if ((ListenerUtil.mutListener.listen(11887) ? (ServiceUtils.isTransitServiceByType(service.getType()) || mIssueLocationHelper.getObaStop() == null) : (ServiceUtils.isTransitServiceByType(service.getType()) && mIssueLocationHelper.getObaStop() == null))) {
                                            if (!ListenerUtil.mutListener.listen(11888)) {
                                                mIssueLocationHelper.clearMarkers();
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11901)) {
            // If is a stop problem type, an all transit heuristic-based match, and no bus stop selected remove markers
            if ((ListenerUtil.mutListener.listen(11899) ? ((ListenerUtil.mutListener.listen(11898) ? (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) || mIsAllTransitHeuristicMatch) : (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) && mIsAllTransitHeuristicMatch)) || mIssueLocationHelper.getObaStop() == null) : ((ListenerUtil.mutListener.listen(11898) ? (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) || mIsAllTransitHeuristicMatch) : (getString(R.string.ri_selected_service_stop).equals(mDefaultIssueType) && mIsAllTransitHeuristicMatch)) && mIssueLocationHelper.getObaStop() == null))) {
                if (!ListenerUtil.mutListener.listen(11900)) {
                    mIssueLocationHelper.clearMarkers();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11902)) {
            // Set mDefaultIssueType = null to prevent auto selections
            mDefaultIssueType = null;
        }
        // Give the user a break from tutorials - allow at least one interaction without a tutorial
        int counter = Application.getPrefs().getInt(TUTORIAL_COUNTER, 0);
        if (!ListenerUtil.mutListener.listen(11903)) {
            counter++;
        }
        if (!ListenerUtil.mutListener.listen(11904)) {
            PreferenceUtils.saveInt(TUTORIAL_COUNTER, counter);
        }
        if (!ListenerUtil.mutListener.listen(11925)) {
            // Set selected category if it is in the spinner items list
            if ((ListenerUtil.mutListener.listen(11909) ? (selectPosition >= -1) : (ListenerUtil.mutListener.listen(11908) ? (selectPosition <= -1) : (ListenerUtil.mutListener.listen(11907) ? (selectPosition > -1) : (ListenerUtil.mutListener.listen(11906) ? (selectPosition < -1) : (ListenerUtil.mutListener.listen(11905) ? (selectPosition == -1) : (selectPosition != -1))))))) {
                if (!ListenerUtil.mutListener.listen(11910)) {
                    showServicesSpinner();
                }
                if (!ListenerUtil.mutListener.listen(11911)) {
                    mServicesSpinner.setSelection(selectPosition, true);
                }
                if (!ListenerUtil.mutListener.listen(11924)) {
                    // Show tutorial if this geographic area supports Open311 services
                    if ((ListenerUtil.mutListener.listen(11922) ? ((ListenerUtil.mutListener.listen(11921) ? ((ListenerUtil.mutListener.listen(11920) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) >= 0) : (ListenerUtil.mutListener.listen(11919) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) <= 0) : (ListenerUtil.mutListener.listen(11918) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) > 0) : (ListenerUtil.mutListener.listen(11917) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) < 0) : (ListenerUtil.mutListener.listen(11916) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) != 0) : ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) == 0)))))) || selectedService != null) : ((ListenerUtil.mutListener.listen(11920) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) >= 0) : (ListenerUtil.mutListener.listen(11919) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) <= 0) : (ListenerUtil.mutListener.listen(11918) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) > 0) : (ListenerUtil.mutListener.listen(11917) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) < 0) : (ListenerUtil.mutListener.listen(11916) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) != 0) : ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) == 0)))))) && selectedService != null)) || ServiceUtils.isTransitOpen311ServiceByType(selectedService.getType())) : ((ListenerUtil.mutListener.listen(11921) ? ((ListenerUtil.mutListener.listen(11920) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) >= 0) : (ListenerUtil.mutListener.listen(11919) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) <= 0) : (ListenerUtil.mutListener.listen(11918) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) > 0) : (ListenerUtil.mutListener.listen(11917) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) < 0) : (ListenerUtil.mutListener.listen(11916) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) != 0) : ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) == 0)))))) || selectedService != null) : ((ListenerUtil.mutListener.listen(11920) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) >= 0) : (ListenerUtil.mutListener.listen(11919) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) <= 0) : (ListenerUtil.mutListener.listen(11918) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) > 0) : (ListenerUtil.mutListener.listen(11917) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) < 0) : (ListenerUtil.mutListener.listen(11916) ? ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) != 0) : ((ListenerUtil.mutListener.listen(11915) ? (counter / 2) : (ListenerUtil.mutListener.listen(11914) ? (counter * 2) : (ListenerUtil.mutListener.listen(11913) ? (counter - 2) : (ListenerUtil.mutListener.listen(11912) ? (counter + 2) : (counter % 2))))) == 0)))))) && selectedService != null)) && ServiceUtils.isTransitOpen311ServiceByType(selectedService.getType())))) {
                        if (!ListenerUtil.mutListener.listen(11923)) {
                            ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_SEND_FEEDBACK_OPEN311_CATEGORIES, this, null, false);
                        }
                    }
                }
            }
        }
    }

    private void hideServicesSpinner() {
        if (!ListenerUtil.mutListener.listen(11926)) {
            mServicesSpinnerFrameLayout.setVisibility(View.GONE);
        }
    }

    private void showServicesSpinner() {
        if (!ListenerUtil.mutListener.listen(11927)) {
            mServicesSpinnerFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Disables open311 reporting if there is no category for region
     * Show static services
     */
    public void showTransitService(String issueType) {
        if (!ListenerUtil.mutListener.listen(11928)) {
            clearReportingFragments();
        }
        ObaStop obaStop = mIssueLocationHelper.getObaStop();
        if (!ListenerUtil.mutListener.listen(11940)) {
            if (obaStop != null) {
                if (!ListenerUtil.mutListener.listen(11939)) {
                    if (ReportConstants.STATIC_TRANSIT_SERVICE_STOP.equals(issueType)) {
                        if (!ListenerUtil.mutListener.listen(11938)) {
                            showStopProblemFragment(obaStop);
                        }
                    } else if (ReportConstants.STATIC_TRANSIT_SERVICE_TRIP.equals(issueType)) {
                        if (!ListenerUtil.mutListener.listen(11937)) {
                            showTripProblemFragment(obaStop);
                        }
                    } else if (ReportConstants.DYNAMIC_TRANSIT_SERVICE_STOP.equals(issueType)) {
                        if (!ListenerUtil.mutListener.listen(11936)) {
                            showOpen311Reporting(mSelectedTransitService);
                        }
                    } else if (ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP.equals(issueType)) {
                        if (!ListenerUtil.mutListener.listen(11935)) {
                            if (mArrivalInfo != null) {
                                if (!ListenerUtil.mutListener.listen(11933)) {
                                    showOpen311Reporting(mSelectedTransitService, mArrivalInfo);
                                }
                                if (!ListenerUtil.mutListener.listen(11934)) {
                                    mArrivalInfo = null;
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11932)) {
                                    showTripProblemFragment(obaStop);
                                }
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11929)) {
                    addInfoText(getString(R.string.report_dialog_stop_header));
                }
                if (!ListenerUtil.mutListener.listen(11930)) {
                    mTransitServiceIssueTypeWithoutStop = issueType;
                }
                if (!ListenerUtil.mutListener.listen(11931)) {
                    mIssueLocationHelper.clearMarkers();
                }
            }
        }
    }

    private void showOpen311Reporting(Service service) {
        if (!ListenerUtil.mutListener.listen(11941)) {
            clearReportingFragments();
        }
        if (!ListenerUtil.mutListener.listen(11942)) {
            showOpen311ProblemFragment(service);
        }
        if (!ListenerUtil.mutListener.listen(11943)) {
            updateMarkerPosition(mIssueLocationHelper.getIssueLocation());
        }
    }

    private void showOpen311Reporting(Service service, ObaArrivalInfo obaArrivalInfo) {
        if (!ListenerUtil.mutListener.listen(11944)) {
            clearReportingFragments();
        }
        if (!ListenerUtil.mutListener.listen(11945)) {
            showOpen311ProblemFragment(service, obaArrivalInfo);
        }
        if (!ListenerUtil.mutListener.listen(11946)) {
            updateMarkerPosition(mIssueLocationHelper.getIssueLocation());
        }
    }

    private void clearReportingFragments() {
        if (!ListenerUtil.mutListener.listen(11947)) {
            clearReportingFragments(true);
        }
    }

    private void clearReportingFragments(boolean removeInfo) {
        if (!ListenerUtil.mutListener.listen(11949)) {
            if (removeInfo) {
                if (!ListenerUtil.mutListener.listen(11948)) {
                    removeInfoText();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11950)) {
            removeOpen311ProblemFragment();
        }
        if (!ListenerUtil.mutListener.listen(11951)) {
            removeStopProblemFragment();
        }
        if (!ListenerUtil.mutListener.listen(11952)) {
            removeTripProblemFragment();
        }
    }

    private void showStopProblemFragment(ObaStop obaStop) {
        if (!ListenerUtil.mutListener.listen(11953)) {
            ReportStopProblemFragment.show(this, obaStop, R.id.ri_report_stop_problem);
        }
    }

    private void showTripProblemFragment(ObaStop obaStop) {
        if (!ListenerUtil.mutListener.listen(11957)) {
            if (mArrivalInfo == null) {
                if (!ListenerUtil.mutListener.listen(11956)) {
                    showArrivalListFragment(obaStop);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11954)) {
                    // Show default trip problem issue reporting
                    ReportTripProblemFragment.show(this, mArrivalInfo, R.id.ri_report_stop_problem);
                }
                if (!ListenerUtil.mutListener.listen(11955)) {
                    mArrivalInfo = null;
                }
            }
        }
    }

    private void showArrivalListFragment(ObaStop obaStop) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v = layoutInflater.inflate(R.layout.arrivals_list_header, null);
        if (!ListenerUtil.mutListener.listen(11958)) {
            v.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(11959)) {
            mShowArrivalListFragment = true;
        }
        if (!ListenerUtil.mutListener.listen(11960)) {
            SimpleArrivalListFragment.show(this, R.id.ri_report_stop_problem, obaStop, this);
        }
    }

    @Override
    public void onArrivalItemClicked(ObaArrivalInfo obaArrivalInfo, String agencyName, String blockId) {
        if (!ListenerUtil.mutListener.listen(11961)) {
            mShowArrivalListFragment = false;
        }
        if (!ListenerUtil.mutListener.listen(11962)) {
            mAgencyName = agencyName;
        }
        if (!ListenerUtil.mutListener.listen(11963)) {
            mBlockId = blockId;
        }
        if (!ListenerUtil.mutListener.listen(11964)) {
            removeFragmentByTag(SimpleArrivalListFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(11968)) {
            if ((ListenerUtil.mutListener.listen(11965) ? (mSelectedTransitService != null || ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP.equals(mSelectedTransitService.getType())) : (mSelectedTransitService != null && ReportConstants.DYNAMIC_TRANSIT_SERVICE_TRIP.equals(mSelectedTransitService.getType())))) {
                if (!ListenerUtil.mutListener.listen(11967)) {
                    // Show open311 defined issue reporting service
                    showOpen311ProblemFragment(mSelectedTransitService, obaArrivalInfo);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11966)) {
                    // Show default trip problem issue reporting
                    ReportTripProblemFragment.show(this, obaArrivalInfo, R.id.ri_report_stop_problem);
                }
            }
        }
    }

    private void showBusStopHeader(String text) {
        if (!ListenerUtil.mutListener.listen(11969)) {
            // First remove info text
            removeInfoText();
        }
        if (!ListenerUtil.mutListener.listen(11970)) {
            ((TextView) findViewById(R.id.ri_bus_stop_text)).setText(text);
        }
        if (!ListenerUtil.mutListener.listen(11972)) {
            if (mBusStopHeader.getVisibility() != View.VISIBLE) {
                if (!ListenerUtil.mutListener.listen(11971)) {
                    mBusStopHeader.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void hideBusStopHeader() {
        if (!ListenerUtil.mutListener.listen(11973)) {
            findViewById(R.id.bus_stop_header).setVisibility(View.GONE);
        }
    }

    private void showOpen311ProblemFragment(Service service) {
        if (!ListenerUtil.mutListener.listen(11974)) {
            Open311ProblemFragment.show(this, R.id.ri_report_stop_problem, mOpen311, service);
        }
    }

    private void showOpen311ProblemFragment(Service service, ObaArrivalInfo obaArrivalInfo) {
        if (!ListenerUtil.mutListener.listen(11975)) {
            Open311ProblemFragment.show(this, R.id.ri_report_stop_problem, mOpen311, service, obaArrivalInfo, mAgencyName, mBlockId);
        }
    }

    public void removeOpen311ProblemFragment() {
        if (!ListenerUtil.mutListener.listen(11976)) {
            removeFragmentByTag(Open311ProblemFragment.TAG);
        }
    }

    private void removeStopProblemFragment() {
        if (!ListenerUtil.mutListener.listen(11977)) {
            removeFragmentByTag(ReportStopProblemFragment.TAG);
        }
    }

    private void removeTripProblemFragment() {
        if (!ListenerUtil.mutListener.listen(11978)) {
            mShowArrivalListFragment = false;
        }
        if (!ListenerUtil.mutListener.listen(11979)) {
            removeFragmentByTag(ReportTripProblemFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(11980)) {
            removeFragmentByTag(SimpleArrivalListFragment.TAG);
        }
        if (!ListenerUtil.mutListener.listen(11981)) {
            ((LinearLayout) findViewById(R.id.ri_report_stop_problem)).removeAllViews();
        }
    }

    public IssueLocationHelper getIssueLocationHelper() {
        return mIssueLocationHelper;
    }

    public String getCurrentAddress() {
        return mAddressEditText.getText().toString();
    }

    public void finishActivityWithResult() {
        Intent returnIntent = new Intent();
        if (!ListenerUtil.mutListener.listen(11982)) {
            returnIntent.putExtra(BaseReportActivity.CLOSE_REQUEST, true);
        }
        if (!ListenerUtil.mutListener.listen(11983)) {
            setResult(RESULT_OK, returnIntent);
        }
        if (!ListenerUtil.mutListener.listen(11984)) {
            finish();
        }
    }
}
