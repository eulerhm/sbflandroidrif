/**
 * Copyright (C) 2016-2017 Cambridge Systematics, Inc.,
 * University of South Florida (sjbarbeau@gmail.com),
 * Microsoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.android.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sothree.slidinguppanel.ScrollableViewHelper;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.directions.tasks.TripRequest;
import org.onebusaway.android.directions.util.OTPConstants;
import org.onebusaway.android.directions.util.TripRequestBuilder;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.travelbehavior.TravelBehaviorManager;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.UIUtils;
import org.opentripplanner.api.model.Itinerary;
import org.opentripplanner.api.model.TripPlan;
import org.opentripplanner.api.ws.Message;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripPlanActivity extends AppCompatActivity implements TripRequest.Callback, TripResultsFragment.Listener, TripPlanFragment.Listener {

    private static final String TAG = "TripPlanActivity";

    TripRequestBuilder mBuilder;

    TripRequest mTripRequest = null;

    SlidingUpPanelLayout mPanel;

    AlertDialog mFeedbackDialog;

    private static final String PLAN_ERROR_CODE = "org.onebusaway.android.PLAN_ERROR_CODE";

    private static final String PLAN_ERROR_URL = "org.onebusaway.android.PLAN_ERROR_URL";

    private static final String PLAN_REQUEST_URL = "org.onebusaway.android.PLAN_REQUEST_URL";

    private static final String PANEL_STATE_EXPANDED = "org.onebusaway.android.PANEL_STATE_EXPANDED";

    private static final String SHOW_ERROR_DIALOG = "org.onebusaway.android.SHOW_ERROR_DIALOG";

    private static final String REQUEST_LOADING = "org.onebusaway.android.REQUEST_LOADING";

    TripResultsFragment mResultsFragment;

    private ProgressDialog mProgressDialog;

    boolean mRequestLoading = false;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1450)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1451)) {
            setContentView(R.layout.activity_trip_plan);
        }
        if (!ListenerUtil.mutListener.listen(1452)) {
            UIUtils.setupActionBar(this);
        }
        if (!ListenerUtil.mutListener.listen(1453)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        }
        Bundle bundle = (savedInstanceState == null) ? new Bundle() : savedInstanceState;
        if (!ListenerUtil.mutListener.listen(1454)) {
            mBuilder = new TripRequestBuilder(bundle);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(1455)) {
            super.onResume();
        }
        Bundle bundle = mBuilder.getBundle();
        boolean newItineraries = false;
        // see if there is data from intent
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(1470)) {
            if ((ListenerUtil.mutListener.listen(1456) ? (intent != null || intent.getExtras() != null) : (intent != null && intent.getExtras() != null))) {
                OTPConstants.Source source = (OTPConstants.Source) intent.getSerializableExtra(OTPConstants.INTENT_SOURCE);
                if (!ListenerUtil.mutListener.listen(1469)) {
                    if (source != null) {
                        if (!ListenerUtil.mutListener.listen(1458)) {
                            // Copy planning params - necessary if this intent came from a notification.
                            if (source == OTPConstants.Source.NOTIFICATION) {
                                if (!ListenerUtil.mutListener.listen(1457)) {
                                    new TripRequestBuilder(intent.getExtras()).copyIntoBundle(bundle);
                                }
                            }
                        }
                        ArrayList<Itinerary> itineraries = (ArrayList<Itinerary>) intent.getExtras().getSerializable(OTPConstants.ITINERARIES);
                        if (!ListenerUtil.mutListener.listen(1461)) {
                            if (itineraries != null) {
                                if (!ListenerUtil.mutListener.listen(1459)) {
                                    bundle.putSerializable(OTPConstants.ITINERARIES, itineraries);
                                }
                                if (!ListenerUtil.mutListener.listen(1460)) {
                                    newItineraries = true;
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(1465)) {
                            if (intent.getIntExtra(PLAN_ERROR_CODE, -1) != -1) {
                                if (!ListenerUtil.mutListener.listen(1462)) {
                                    bundle.putSerializable(SHOW_ERROR_DIALOG, true);
                                }
                                if (!ListenerUtil.mutListener.listen(1463)) {
                                    bundle.putInt(PLAN_ERROR_CODE, intent.getIntExtra(PLAN_ERROR_CODE, 0));
                                }
                                if (!ListenerUtil.mutListener.listen(1464)) {
                                    bundle.putString(PLAN_ERROR_URL, intent.getStringExtra(PLAN_ERROR_URL));
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(1466)) {
                            setIntent(null);
                        }
                        if (!ListenerUtil.mutListener.listen(1467)) {
                            bundle.putBoolean(REQUEST_LOADING, false);
                        }
                        if (!ListenerUtil.mutListener.listen(1468)) {
                            mRequestLoading = false;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1473)) {
            if ((ListenerUtil.mutListener.listen(1471) ? (mRequestLoading && bundle.getBoolean(REQUEST_LOADING)) : (mRequestLoading || bundle.getBoolean(REQUEST_LOADING)))) {
                if (!ListenerUtil.mutListener.listen(1472)) {
                    showProgressDialog();
                }
            }
        }
        // Check which fragment to create
        boolean haveTripPlan = bundle.getSerializable(OTPConstants.ITINERARIES) != null;
        TripPlanFragment fragment = (TripPlanFragment) getSupportFragmentManager().findFragmentById(R.id.trip_plan_fragment_container);
        if (!ListenerUtil.mutListener.listen(1478)) {
            if (fragment == null) {
                if (!ListenerUtil.mutListener.listen(1474)) {
                    fragment = new TripPlanFragment();
                }
                if (!ListenerUtil.mutListener.listen(1475)) {
                    fragment.setArguments(bundle);
                }
                if (!ListenerUtil.mutListener.listen(1476)) {
                    fragment.setListener(this);
                }
                if (!ListenerUtil.mutListener.listen(1477)) {
                    getSupportFragmentManager().beginTransaction().add(R.id.trip_plan_fragment_container, fragment, TripPlanFragment.TAG).commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1479)) {
            mPanel = (SlidingUpPanelLayout) findViewById(R.id.trip_plan_sliding_layout);
        }
        if (!ListenerUtil.mutListener.listen(1480)) {
            mPanel.setEnabled(haveTripPlan);
        }
        if (!ListenerUtil.mutListener.listen(1485)) {
            if (haveTripPlan) {
                if (!ListenerUtil.mutListener.listen(1481)) {
                    initResultsFragment();
                }
                if (!ListenerUtil.mutListener.listen(1484)) {
                    if ((ListenerUtil.mutListener.listen(1482) ? (bundle.getBoolean(PANEL_STATE_EXPANDED) && newItineraries) : (bundle.getBoolean(PANEL_STATE_EXPANDED) || newItineraries))) {
                        if (!ListenerUtil.mutListener.listen(1483)) {
                            mPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1490)) {
            // show error dialog if necessary
            if (bundle.getBoolean(SHOW_ERROR_DIALOG)) {
                int planErrorCode = 0;
                String planErrorUrl = null;
                if (!ListenerUtil.mutListener.listen(1488)) {
                    if (intent != null) {
                        if (!ListenerUtil.mutListener.listen(1486)) {
                            planErrorCode = intent.getIntExtra(PLAN_ERROR_CODE, 0);
                        }
                        if (!ListenerUtil.mutListener.listen(1487)) {
                            planErrorUrl = intent.getStringExtra(PLAN_ERROR_URL);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1489)) {
                    showFeedbackDialog(planErrorCode, planErrorUrl);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1494)) {
            if ((ListenerUtil.mutListener.listen(1491) ? (fragment != null || intent != null) : (fragment != null && intent != null))) {
                if (!ListenerUtil.mutListener.listen(1492)) {
                    fragment.setPlanErrorUrl(intent.getStringExtra(PLAN_ERROR_URL));
                }
                if (!ListenerUtil.mutListener.listen(1493)) {
                    fragment.setPlanRequestUrl(intent.getStringExtra(PLAN_REQUEST_URL));
                }
            }
        }
        // Set the height of the panel after drawing occurs.
        final ViewGroup layout = (ViewGroup) findViewById(R.id.trip_plan_fragment_container);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        if (!ListenerUtil.mutListener.listen(1501)) {
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (!ListenerUtil.mutListener.listen(1495)) {
                        layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    int viewHeight = mPanel.getHeight();
                    int height = layout.getMeasuredHeight();
                    if (!ListenerUtil.mutListener.listen(1500)) {
                        mPanel.setPanelHeight((ListenerUtil.mutListener.listen(1499) ? (viewHeight % height) : (ListenerUtil.mutListener.listen(1498) ? (viewHeight / height) : (ListenerUtil.mutListener.listen(1497) ? (viewHeight * height) : (ListenerUtil.mutListener.listen(1496) ? (viewHeight + height) : (viewHeight - height))))));
                    }
                }
            });
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (!ListenerUtil.mutListener.listen(1502)) {
            setIntent(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(1511)) {
            if (mBuilder != null) {
                if (!ListenerUtil.mutListener.listen(1503)) {
                    mBuilder.copyIntoBundle(bundle);
                }
                // We also saved the itineraries and the results state in this bundle
                Bundle source = mBuilder.getBundle();
                ArrayList<Itinerary> itineraries = (ArrayList<Itinerary>) source.getSerializable(OTPConstants.ITINERARIES);
                if (!ListenerUtil.mutListener.listen(1505)) {
                    if (itineraries != null) {
                        if (!ListenerUtil.mutListener.listen(1504)) {
                            bundle.putSerializable(OTPConstants.ITINERARIES, itineraries);
                        }
                    }
                }
                int rank = source.getInt(OTPConstants.SELECTED_ITINERARY);
                if (!ListenerUtil.mutListener.listen(1506)) {
                    bundle.putInt(OTPConstants.SELECTED_ITINERARY, rank);
                }
                boolean showMap = source.getBoolean(OTPConstants.SHOW_MAP);
                if (!ListenerUtil.mutListener.listen(1507)) {
                    bundle.putBoolean(OTPConstants.SHOW_MAP, showMap);
                }
                boolean panelStateExpanded = (mPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED);
                if (!ListenerUtil.mutListener.listen(1508)) {
                    bundle.putBoolean(PANEL_STATE_EXPANDED, panelStateExpanded);
                }
                boolean showError = source.getBoolean(SHOW_ERROR_DIALOG);
                if (!ListenerUtil.mutListener.listen(1509)) {
                    bundle.putBoolean(SHOW_ERROR_DIALOG, showError);
                }
                if (!ListenerUtil.mutListener.listen(1510)) {
                    bundle.putBoolean(REQUEST_LOADING, mRequestLoading);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1512)) {
            super.onSaveInstanceState(bundle);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(1513)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(1515)) {
            // Close possible progress dialog.
            if (mProgressDialog != null) {
                if (!ListenerUtil.mutListener.listen(1514)) {
                    mProgressDialog.dismiss();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1517)) {
            if (mFeedbackDialog != null) {
                if (!ListenerUtil.mutListener.listen(1516)) {
                    mFeedbackDialog.dismiss();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(1521)) {
            if ((ListenerUtil.mutListener.listen(1518) ? (mPanel != null || mPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) : (mPanel != null && mPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED))) {
                if (!ListenerUtil.mutListener.listen(1520)) {
                    mPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1519)) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(1523)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(1522)) {
                    // Ensure the software and hardware back buttons have the same behavior
                    onBackPressed();
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTripRequestReady() {
        // Remove results fragment if it exists
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.trip_results_fragment_container);
        if (!ListenerUtil.mutListener.listen(1525)) {
            if (fragment != null) {
                if (!ListenerUtil.mutListener.listen(1524)) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1526)) {
            mTripRequest = mBuilder.setListener(this).execute(this);
        }
        // clear out selected itinerary from bundle
        Bundle bundle = mBuilder.getBundle();
        if (!ListenerUtil.mutListener.listen(1527)) {
            bundle.remove(OTPConstants.ITINERARIES);
        }
        if (!ListenerUtil.mutListener.listen(1528)) {
            bundle.remove(OTPConstants.SELECTED_ITINERARY);
        }
        if (!ListenerUtil.mutListener.listen(1529)) {
            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_trip_plan), null);
        }
        if (!ListenerUtil.mutListener.listen(1530)) {
            showProgressDialog();
        }
    }

    private void initResultsFragment() {
        if (!ListenerUtil.mutListener.listen(1531)) {
            mResultsFragment = (TripResultsFragment) getSupportFragmentManager().findFragmentById(R.id.trip_results_fragment_container);
        }
        if (!ListenerUtil.mutListener.listen(1533)) {
            if (mResultsFragment != null) {
                if (!ListenerUtil.mutListener.listen(1532)) {
                    // bundle arguments already set
                    mResultsFragment.displayNewResults();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1534)) {
            mResultsFragment = new TripResultsFragment();
        }
        if (!ListenerUtil.mutListener.listen(1535)) {
            mResultsFragment.setListener(this);
        }
        if (!ListenerUtil.mutListener.listen(1536)) {
            mResultsFragment.setArguments(mBuilder.getBundle());
        }
        if (!ListenerUtil.mutListener.listen(1537)) {
            getSupportFragmentManager().beginTransaction().add(R.id.trip_results_fragment_container, mResultsFragment).commit();
        }
        if (!ListenerUtil.mutListener.listen(1538)) {
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    @Override
    public void onTripRequestComplete(TripPlan tripPlan, String url) {
        if (!ListenerUtil.mutListener.listen(1539)) {
            TravelBehaviorManager.saveTripPlan(tripPlan, url, getApplicationContext());
        }
        // Send intent to ourselves...
        Intent intent = new Intent(this, TripPlanActivity.class).setAction(Intent.ACTION_MAIN).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).putExtra(OTPConstants.ITINERARIES, (ArrayList<Itinerary>) tripPlan.getItinerary()).putExtra(OTPConstants.INTENT_SOURCE, OTPConstants.Source.ACTIVITY).putExtra(PLAN_REQUEST_URL, url);
        if (!ListenerUtil.mutListener.listen(1540)) {
            startActivity(intent);
        }
    }

    @Override
    public void onTripRequestFailure(int errorCode, String url) {
        Intent intent = new Intent(this, TripPlanActivity.class).setAction(Intent.ACTION_MAIN).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).putExtra(PLAN_ERROR_CODE, errorCode).putExtra(PLAN_ERROR_URL, url).putExtra(OTPConstants.INTENT_SOURCE, OTPConstants.Source.ACTIVITY);
        if (!ListenerUtil.mutListener.listen(1541)) {
            startActivity(intent);
        }
    }

    private void showFeedbackDialog(int errorCode, final String url) {
        String msg = getString(R.string.tripplanner_error_not_defined);
        if (!ListenerUtil.mutListener.listen(1549)) {
            if ((ListenerUtil.mutListener.listen(1547) ? ((ListenerUtil.mutListener.listen(1546) ? (errorCode >= 0) : (ListenerUtil.mutListener.listen(1545) ? (errorCode <= 0) : (ListenerUtil.mutListener.listen(1544) ? (errorCode < 0) : (ListenerUtil.mutListener.listen(1543) ? (errorCode != 0) : (ListenerUtil.mutListener.listen(1542) ? (errorCode == 0) : (errorCode > 0)))))) || errorCode != Message.PLAN_OK.getId()) : ((ListenerUtil.mutListener.listen(1546) ? (errorCode >= 0) : (ListenerUtil.mutListener.listen(1545) ? (errorCode <= 0) : (ListenerUtil.mutListener.listen(1544) ? (errorCode < 0) : (ListenerUtil.mutListener.listen(1543) ? (errorCode != 0) : (ListenerUtil.mutListener.listen(1542) ? (errorCode == 0) : (errorCode > 0)))))) && errorCode != Message.PLAN_OK.getId()))) {
                if (!ListenerUtil.mutListener.listen(1548)) {
                    msg = getErrorMessage(errorCode);
                }
            }
        }
        final Bundle bundle = mBuilder.getBundle();
        AlertDialog.Builder feedback = new AlertDialog.Builder(this).setTitle(R.string.tripplanner_error_dialog_title).setMessage(msg);
        if (!ListenerUtil.mutListener.listen(1552)) {
            feedback.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(1550)) {
                        bundle.putBoolean(SHOW_ERROR_DIALOG, false);
                    }
                    if (!ListenerUtil.mutListener.listen(1551)) {
                        clearBundleErrors();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(1563)) {
            if ((ListenerUtil.mutListener.listen(1553) ? (errorCode != Message.SYSTEM_ERROR.getId() || errorCode != Message.REQUEST_TIMEOUT.getId()) : (errorCode != Message.SYSTEM_ERROR.getId() && errorCode != Message.REQUEST_TIMEOUT.getId()))) {
                if (!ListenerUtil.mutListener.listen(1562)) {
                    // Only add the report button if we get a server response (see #747)
                    feedback.setNegativeButton(R.string.report_problem_report, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String email = Application.get().getCurrentRegion().getOtpContactEmail();
                            if (!ListenerUtil.mutListener.listen(1559)) {
                                if (!TextUtils.isEmpty(email)) {
                                    Location loc = Application.getLastKnownLocation(getApplicationContext(), null);
                                    String locString = null;
                                    if (!ListenerUtil.mutListener.listen(1556)) {
                                        if (loc != null) {
                                            if (!ListenerUtil.mutListener.listen(1555)) {
                                                locString = LocationUtils.printLocationDetails(loc);
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(1557)) {
                                        UIUtils.sendEmail(TripPlanActivity.this, email, locString, url, true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(1558)) {
                                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_app_feedback_otp), null);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(1554)) {
                                        Toast.makeText(TripPlanActivity.this, getString(R.string.tripplanner_no_contact), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1560)) {
                                bundle.putBoolean(SHOW_ERROR_DIALOG, false);
                            }
                            if (!ListenerUtil.mutListener.listen(1561)) {
                                clearBundleErrors();
                            }
                        }
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1564)) {
            mFeedbackDialog = feedback.create();
        }
        if (!ListenerUtil.mutListener.listen(1565)) {
            mFeedbackDialog.show();
        }
    }

    private void showProgressDialog() {
        if (!ListenerUtil.mutListener.listen(1566)) {
            mRequestLoading = true;
        }
        if (!ListenerUtil.mutListener.listen(1569)) {
            if ((ListenerUtil.mutListener.listen(1567) ? (mProgressDialog == null && !mProgressDialog.isShowing()) : (mProgressDialog == null || !mProgressDialog.isShowing()))) {
                if (!ListenerUtil.mutListener.listen(1568)) {
                    mProgressDialog = ProgressDialog.show(this, "", getResources().getText(R.string.task_progress_tripplanner_progress), true);
                }
            }
        }
    }

    private String getErrorMessage(int errorCode) {
        if (errorCode == Message.SYSTEM_ERROR.getId()) {
            return (getString(R.string.tripplanner_error_system));
        } else if (errorCode == Message.OUTSIDE_BOUNDS.getId()) {
            return (getString(R.string.tripplanner_error_outside_bounds));
        } else if (errorCode == Message.PATH_NOT_FOUND.getId()) {
            return (getString(R.string.tripplanner_error_path_not_found));
        } else if (errorCode == Message.NO_TRANSIT_TIMES.getId()) {
            return (getString(R.string.tripplanner_error_no_transit_times));
        } else if (errorCode == Message.REQUEST_TIMEOUT.getId()) {
            return (getString(R.string.tripplanner_error_request_timeout));
        } else if (errorCode == Message.BOGUS_PARAMETER.getId()) {
            return (getString(R.string.tripplanner_error_bogus_parameter));
        } else if (errorCode == Message.GEOCODE_FROM_NOT_FOUND.getId()) {
            return (getString(R.string.tripplanner_error_geocode_from_not_found));
        } else if (errorCode == Message.GEOCODE_TO_NOT_FOUND.getId()) {
            return (getString(R.string.tripplanner_error_geocode_to_not_found));
        } else if (errorCode == Message.GEOCODE_FROM_TO_NOT_FOUND.getId()) {
            return (getString(R.string.tripplanner_error_geocode_from_to_not_found));
        } else if (errorCode == Message.TOO_CLOSE.getId()) {
            return (getString(R.string.tripplanner_error_too_close));
        } else if (errorCode == Message.LOCATION_NOT_ACCESSIBLE.getId()) {
            return (getString(R.string.tripplanner_error_location_not_accessible));
        } else if (errorCode == Message.GEOCODE_FROM_AMBIGUOUS.getId()) {
            return (getString(R.string.tripplanner_error_geocode_from_ambiguous));
        } else if (errorCode == Message.GEOCODE_TO_AMBIGUOUS.getId()) {
            return (getString(R.string.tripplanner_error_geocode_to_ambiguous));
        } else if (errorCode == Message.GEOCODE_FROM_TO_AMBIGUOUS.getId()) {
            return (getString(R.string.tripplanner_error_geocode_from_to_ambiguous));
        } else if ((ListenerUtil.mutListener.listen(1572) ? ((ListenerUtil.mutListener.listen(1571) ? ((ListenerUtil.mutListener.listen(1570) ? (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() && errorCode == Message.TRIANGLE_NOT_AFFINE.getId()) : (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() || errorCode == Message.TRIANGLE_NOT_AFFINE.getId())) && errorCode == Message.TRIANGLE_OPTIMIZE_TYPE_NOT_SET.getId()) : ((ListenerUtil.mutListener.listen(1570) ? (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() && errorCode == Message.TRIANGLE_NOT_AFFINE.getId()) : (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() || errorCode == Message.TRIANGLE_NOT_AFFINE.getId())) || errorCode == Message.TRIANGLE_OPTIMIZE_TYPE_NOT_SET.getId())) && errorCode == Message.TRIANGLE_VALUES_NOT_SET.getId()) : ((ListenerUtil.mutListener.listen(1571) ? ((ListenerUtil.mutListener.listen(1570) ? (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() && errorCode == Message.TRIANGLE_NOT_AFFINE.getId()) : (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() || errorCode == Message.TRIANGLE_NOT_AFFINE.getId())) && errorCode == Message.TRIANGLE_OPTIMIZE_TYPE_NOT_SET.getId()) : ((ListenerUtil.mutListener.listen(1570) ? (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() && errorCode == Message.TRIANGLE_NOT_AFFINE.getId()) : (errorCode == Message.UNDERSPECIFIED_TRIANGLE.getId() || errorCode == Message.TRIANGLE_NOT_AFFINE.getId())) || errorCode == Message.TRIANGLE_OPTIMIZE_TYPE_NOT_SET.getId())) || errorCode == Message.TRIANGLE_VALUES_NOT_SET.getId()))) {
            return (getString(R.string.tripplanner_error_triangle));
        } else if (errorCode == TripRequest.NO_SERVER_SELECTED) {
            return getString(R.string.tripplanner_no_server_selected_error);
        } else {
            return null;
        }
    }

    private void clearBundleErrors() {
        if (!ListenerUtil.mutListener.listen(1573)) {
            mBuilder.getBundle().remove(PLAN_ERROR_CODE);
        }
        if (!ListenerUtil.mutListener.listen(1574)) {
            mBuilder.getBundle().remove(PLAN_ERROR_URL);
        }
    }

    // Handle the sliding panel's interactions with the list view and the map view.
    @Override
    public void onResultViewCreated(View container, final ListView listView, View mapView) {
        if (!ListenerUtil.mutListener.listen(1577)) {
            if (mPanel != null) {
                if (!ListenerUtil.mutListener.listen(1575)) {
                    mPanel.setScrollableViewHelper(new ScrollableViewHelper() {

                        @Override
                        public int getScrollableViewScrollPosition(View scrollableView, boolean isSlidingUp) {
                            if (mResultsFragment.isMapShowing()) {
                                // Map can scroll infinitely, so return a positive value
                                return 1;
                            } else {
                                return super.getScrollableViewScrollPosition(listView, isSlidingUp);
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(1576)) {
                    mPanel.setScrollableView(container);
                }
            }
        }
    }
}
