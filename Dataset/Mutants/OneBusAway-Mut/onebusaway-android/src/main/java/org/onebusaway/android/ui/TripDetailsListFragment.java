/*
 * Copyright (C) 2012-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida,
 * Benjamin Du (bendu@me.com), and individual contributors.
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.apache.commons.lang3.StringUtils;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaReferences;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.elements.ObaTrip;
import org.onebusaway.android.io.elements.ObaTripSchedule;
import org.onebusaway.android.io.elements.ObaTripStatus;
import org.onebusaway.android.io.elements.OccupancyState;
import org.onebusaway.android.io.elements.Status;
import org.onebusaway.android.io.request.ObaTripDetailsRequest;
import org.onebusaway.android.io.request.ObaTripDetailsResponse;
import org.onebusaway.android.nav.NavigationService;
import org.onebusaway.android.travelbehavior.TravelBehaviorManager;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.DBUtil;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripDetailsListFragment extends ListFragment {

    public static final String TAG = "TripDetailsListFragment";

    public static final String TRIP_ID = ".TripId";

    public static final String STOP_ID = ".StopId";

    public static final String ROUTE_ID = ".RouteId";

    public static final String SCROLL_MODE = ".ScrollMode";

    public static final String SCROLL_MODE_VEHICLE = "vehicle";

    public static final String SCROLL_MODE_STOP = "stop";

    public static final String TRIP_ACTIVE = ".TripActive";

    public static final String DEST_ID = ".DestinationId";

    public static final String ACTION_SERVICE_DESTROYED = "NavigationServiceDestroyed";

    private static final long REFRESH_PERIOD = 60 * 1000;

    private static final int TRIP_DETAILS_LOADER = 0;

    public static final int REQUEST_ENABLE_LOCATION = 1;

    private String mTripId;

    private String mRouteId;

    private String mStopId;

    private String mScrollMode;

    private String mDestinationId;

    private Integer mStopIndex;

    private Integer mDestinationIndex;

    private boolean mActiveTrip;

    private ObaTripDetailsResponse mTripInfo;

    private TripDetailsAdapter mAdapter;

    private final TripDetailsLoaderCallback mTripDetailsCallback = new TripDetailsLoaderCallback();

    private LocationRequest mLocationRequest;

    private Task<LocationSettingsResponse> mResult;

    private FirebaseAnalytics mFirebaseAnalytics;

    /**
     * Builds an intent used to set the trip and stop for the TripDetailsListFragment directly
     * (i.e., when TripDetailsActivity is not used)
     */
    public static class IntentBuilder {

        private Intent mIntent;

        public IntentBuilder(Context context, String tripId) {
            if (!ListenerUtil.mutListener.listen(3222)) {
                mIntent = new Intent(context, TripDetailsListFragment.class);
            }
            if (!ListenerUtil.mutListener.listen(3223)) {
                mIntent.putExtra(TripDetailsListFragment.TRIP_ID, tripId);
            }
        }

        public IntentBuilder setStopId(String stopId) {
            if (!ListenerUtil.mutListener.listen(3224)) {
                mIntent.putExtra(TripDetailsListFragment.STOP_ID, stopId);
            }
            return this;
        }

        public Intent build() {
            return mIntent;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3225)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3226)) {
            // Start out with a progress indicator.
            setListShown(false);
        }
        if (!ListenerUtil.mutListener.listen(3227)) {
            // We have a menu item to show in action bar.
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(3228)) {
            getListView().setOnItemClickListener(mClickListener);
        }
        if (!ListenerUtil.mutListener.listen(3229)) {
            getListView().setOnItemLongClickListener(mLongClickListener);
        }
        if (!ListenerUtil.mutListener.listen(3231)) {
            // Get saved routeId if it exists - avoids potential NPE in onOptionsItemSelected() (#515)
            if (savedInstanceState != null) {
                if (!ListenerUtil.mutListener.listen(3230)) {
                    mRouteId = savedInstanceState.getString(ROUTE_ID);
                }
            }
        }
        Bundle args = getArguments();
        if (!ListenerUtil.mutListener.listen(3232)) {
            mTripId = args.getString(TRIP_ID);
        }
        if (!ListenerUtil.mutListener.listen(3234)) {
            if (mTripId == null) {
                if (!ListenerUtil.mutListener.listen(3233)) {
                    Log.e(TAG, "TripId is null");
                }
                throw new RuntimeException("TripId should not be null");
            }
        }
        if (!ListenerUtil.mutListener.listen(3235)) {
            mStopId = args.getString(STOP_ID);
        }
        if (!ListenerUtil.mutListener.listen(3236)) {
            mScrollMode = args.getString(SCROLL_MODE);
        }
        if (!ListenerUtil.mutListener.listen(3237)) {
            mActiveTrip = args.getBoolean(TRIP_ACTIVE);
        }
        if (!ListenerUtil.mutListener.listen(3238)) {
            mDestinationId = args.getString(DEST_ID);
        }
        if (!ListenerUtil.mutListener.listen(3239)) {
            getLoaderManager().initLoader(TRIP_DETAILS_LOADER, null, mTripDetailsCallback);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3240)) {
            if (root == null) {
                // reason to create our view.
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(3241)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        }
        return inflater.inflate(R.layout.trip_details, null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(3242)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(3244)) {
            if (mRouteId != null) {
                if (!ListenerUtil.mutListener.listen(3243)) {
                    outState.putString(ROUTE_ID, mRouteId);
                }
            }
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(3245)) {
            mRefreshHandler.removeCallbacks(mRefresh);
        }
        if (!ListenerUtil.mutListener.listen(3246)) {
            super.onPause();
        }
    }

    @Override
    public void onResume() {
        // Try to show any old data just in case we're coming out of sleep
        TripDetailsLoader loader = getTripDetailsLoader();
        if (!ListenerUtil.mutListener.listen(3249)) {
            if (loader != null) {
                ObaTripDetailsResponse lastGood = loader.getLastGoodResponse();
                if (!ListenerUtil.mutListener.listen(3248)) {
                    if (lastGood != null) {
                        if (!ListenerUtil.mutListener.listen(3247)) {
                            setTripDetails(lastGood);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3250)) {
            getLoaderManager().restartLoader(TRIP_DETAILS_LOADER, null, mTripDetailsCallback);
        }
        // If our timer would have gone off, then refresh.
        long lastResponseTime = getTripDetailsLoader().getLastResponseTime();
        long newPeriod = Math.min(REFRESH_PERIOD, (ListenerUtil.mutListener.listen(3258) ? (((ListenerUtil.mutListener.listen(3254) ? (lastResponseTime % REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3253) ? (lastResponseTime / REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3252) ? (lastResponseTime * REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3251) ? (lastResponseTime - REFRESH_PERIOD) : (lastResponseTime + REFRESH_PERIOD)))))) % System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(3257) ? (((ListenerUtil.mutListener.listen(3254) ? (lastResponseTime % REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3253) ? (lastResponseTime / REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3252) ? (lastResponseTime * REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3251) ? (lastResponseTime - REFRESH_PERIOD) : (lastResponseTime + REFRESH_PERIOD)))))) / System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(3256) ? (((ListenerUtil.mutListener.listen(3254) ? (lastResponseTime % REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3253) ? (lastResponseTime / REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3252) ? (lastResponseTime * REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3251) ? (lastResponseTime - REFRESH_PERIOD) : (lastResponseTime + REFRESH_PERIOD)))))) * System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(3255) ? (((ListenerUtil.mutListener.listen(3254) ? (lastResponseTime % REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3253) ? (lastResponseTime / REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3252) ? (lastResponseTime * REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3251) ? (lastResponseTime - REFRESH_PERIOD) : (lastResponseTime + REFRESH_PERIOD)))))) + System.currentTimeMillis()) : (((ListenerUtil.mutListener.listen(3254) ? (lastResponseTime % REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3253) ? (lastResponseTime / REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3252) ? (lastResponseTime * REFRESH_PERIOD) : (ListenerUtil.mutListener.listen(3251) ? (lastResponseTime - REFRESH_PERIOD) : (lastResponseTime + REFRESH_PERIOD)))))) - System.currentTimeMillis()))))));
        if (!ListenerUtil.mutListener.listen(3266)) {
            // Log.d(TAG, "Refresh period:" + newPeriod);
            if ((ListenerUtil.mutListener.listen(3263) ? (newPeriod >= 0) : (ListenerUtil.mutListener.listen(3262) ? (newPeriod > 0) : (ListenerUtil.mutListener.listen(3261) ? (newPeriod < 0) : (ListenerUtil.mutListener.listen(3260) ? (newPeriod != 0) : (ListenerUtil.mutListener.listen(3259) ? (newPeriod == 0) : (newPeriod <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(3265)) {
                    refresh();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3264)) {
                    mRefreshHandler.postDelayed(mRefresh, newPeriod);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3267)) {
            super.onResume();
        }
    }

    // 
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(3268)) {
            inflater.inflate(R.menu.trip_details, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(3271)) {
            if (id == R.id.refresh) {
                if (!ListenerUtil.mutListener.listen(3270)) {
                    refresh();
                }
                return true;
            } else if (id == R.id.show_on_map) {
                if (!ListenerUtil.mutListener.listen(3269)) {
                    HomeActivity.start(getActivity(), mRouteId);
                }
                return true;
            }
        }
        return false;
    }

    private void setTripDetails(ObaTripDetailsResponse data) {
        if (!ListenerUtil.mutListener.listen(3272)) {
            mTripInfo = data;
        }
        final int code = mTripInfo.getCode();
        if (!ListenerUtil.mutListener.listen(3275)) {
            if (code == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(3274)) {
                    setEmptyText("");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3273)) {
                    setEmptyText(UIUtils.getRouteErrorString(getActivity(), code));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3276)) {
            setUpHeader();
        }
        final ListView listView = getListView();
        if (!ListenerUtil.mutListener.listen(3301)) {
            if (mAdapter == null) {
                if (!ListenerUtil.mutListener.listen(3279)) {
                    // first time displaying list
                    mAdapter = new TripDetailsAdapter();
                }
                if (!ListenerUtil.mutListener.listen(3280)) {
                    getListView().setDivider(null);
                }
                if (!ListenerUtil.mutListener.listen(3281)) {
                    setListAdapter(mAdapter);
                }
                if (!ListenerUtil.mutListener.listen(3282)) {
                    setScroller(listView);
                }
                if (!ListenerUtil.mutListener.listen(3294)) {
                    // Scroll to stop if we have the stopId available
                    if (mStopId != null) {
                        if (!ListenerUtil.mutListener.listen(3290)) {
                            mStopIndex = findIndexForStop(mTripInfo.getSchedule().getStopTimes(), mStopId);
                        }
                        if (!ListenerUtil.mutListener.listen(3293)) {
                            if (mStopIndex != null) {
                                if (!ListenerUtil.mutListener.listen(3292)) {
                                    listView.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(3291)) {
                                                listView.setSelection(mStopIndex);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    } else {
                        // If we don't have a stop, then scroll to the current position of the bus
                        final Integer nextStop = mAdapter.getNextStopIndex();
                        if (!ListenerUtil.mutListener.listen(3289)) {
                            if (nextStop != null) {
                                if (!ListenerUtil.mutListener.listen(3288)) {
                                    listView.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(3287)) {
                                                listView.setSelection((ListenerUtil.mutListener.listen(3286) ? (nextStop % 1) : (ListenerUtil.mutListener.listen(3285) ? (nextStop / 1) : (ListenerUtil.mutListener.listen(3284) ? (nextStop * 1) : (ListenerUtil.mutListener.listen(3283) ? (nextStop + 1) : (nextStop - 1))))));
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3299)) {
                    if (mDestinationId != null) {
                        if (!ListenerUtil.mutListener.listen(3295)) {
                            mDestinationIndex = findIndexForStop(mTripInfo.getSchedule().getStopTimes(), mDestinationId);
                        }
                        if (!ListenerUtil.mutListener.listen(3298)) {
                            if (mDestinationIndex != null) {
                                if (!ListenerUtil.mutListener.listen(3297)) {
                                    listView.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(3296)) {
                                                listView.setSelection(mDestinationIndex);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(3300)) {
                    mAdapter.notifyDataSetChanged();
                }
            } else {
                // refresh, keep scroll position
                int index = listView.getFirstVisiblePosition();
                View v = listView.getChildAt(0);
                int top = (v == null) ? 0 : v.getTop();
                if (!ListenerUtil.mutListener.listen(3277)) {
                    mAdapter.notifyDataSetChanged();
                }
                if (!ListenerUtil.mutListener.listen(3278)) {
                    listView.setSelectionFromTop(index, top);
                }
            }
        }
    }

    private void setUpHeader() {
        ObaTripStatus status = mTripInfo.getStatus();
        ObaReferences refs = mTripInfo.getRefs();
        Context context = getActivity();
        String tripId = mTripInfo.getId();
        ObaTrip trip = refs.getTrip(tripId);
        if (!ListenerUtil.mutListener.listen(3302)) {
            mRouteId = trip.getRouteId();
        }
        ObaRoute route = refs.getRoute(mRouteId);
        TextView routeShortName = (TextView) getView().findViewById(R.id.short_name);
        if (!ListenerUtil.mutListener.listen(3303)) {
            routeShortName.setText(route.getShortName());
        }
        TextView headsign = (TextView) getView().findViewById(R.id.long_name);
        if (!ListenerUtil.mutListener.listen(3304)) {
            headsign.setText(trip.getHeadsign());
        }
        TextView tripShortName = (TextView) getView().findViewById(R.id.trip_short_name);
        if (!ListenerUtil.mutListener.listen(3308)) {
            if (!StringUtils.isBlank(trip.getShortName())) {
                if (!ListenerUtil.mutListener.listen(3306)) {
                    tripShortName.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(3307)) {
                    tripShortName.setText(trip.getShortName());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3305)) {
                    tripShortName.setVisibility(View.GONE);
                }
            }
        }
        TextView agency = (TextView) getView().findViewById(R.id.agency);
        if (!ListenerUtil.mutListener.listen(3309)) {
            agency.setText(refs.getAgency(route.getAgencyId()).getName());
        }
        TextView vehicleView = (TextView) getView().findViewById(R.id.vehicle);
        TextView vehicleDeviation = (TextView) getView().findViewById(R.id.status);
        ViewGroup realtime = (ViewGroup) getView().findViewById(R.id.eta_realtime_indicator);
        if (!ListenerUtil.mutListener.listen(3310)) {
            realtime.setVisibility(View.GONE);
        }
        ViewGroup statusLayout = (ViewGroup) getView().findViewById(R.id.status_layout);
        ViewGroup occupancyView = getView().findViewById(R.id.occupancy);
        if (!ListenerUtil.mutListener.listen(3314)) {
            if (status == null) {
                if (!ListenerUtil.mutListener.listen(3311)) {
                    // Show schedule info only
                    vehicleView.setText(null);
                }
                if (!ListenerUtil.mutListener.listen(3312)) {
                    vehicleView.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(3313)) {
                    vehicleDeviation.setText(context.getString(R.string.trip_details_scheduled_data));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3318)) {
            if (!TextUtils.isEmpty(status.getVehicleId())) {
                if (!ListenerUtil.mutListener.listen(3316)) {
                    // Show vehicle info
                    vehicleView.setText(context.getString(R.string.trip_details_vehicle, status.getVehicleId()));
                }
                if (!ListenerUtil.mutListener.listen(3317)) {
                    vehicleView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3315)) {
                    vehicleView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3319)) {
            // Set status color in header
            statusLayout.setBackgroundResource(R.drawable.round_corners_style_b_header_status);
        }
        GradientDrawable d = (GradientDrawable) statusLayout.getBackground();
        int statusColor;
        if (!status.isPredicted()) {
            // We have only schedule info, but the bus position can still be interpolated
            statusColor = R.color.stop_info_scheduled_time;
            if (!ListenerUtil.mutListener.listen(3320)) {
                d.setColor(getResources().getColor(statusColor));
            }
            if (!ListenerUtil.mutListener.listen(3323)) {
                if (Status.CANCELED.equals(status.getStatus())) {
                    if (!ListenerUtil.mutListener.listen(3322)) {
                        // Canceled trip
                        vehicleDeviation.setText(context.getString(R.string.stop_info_canceled));
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3321)) {
                        // Scheduled trip
                        vehicleDeviation.setText(context.getString(R.string.trip_details_scheduled_data));
                    }
                }
            }
            return;
        }
        if (!ListenerUtil.mutListener.listen(3324)) {
            /**
             * We have real-time info
             */
            realtime.setVisibility(View.VISIBLE);
        }
        long deviation = status.getScheduleDeviation();
        long deviationMin = TimeUnit.SECONDS.toMinutes(status.getScheduleDeviation());
        long minutes = (ListenerUtil.mutListener.listen(3328) ? (Math.abs(deviation) % 60) : (ListenerUtil.mutListener.listen(3327) ? (Math.abs(deviation) * 60) : (ListenerUtil.mutListener.listen(3326) ? (Math.abs(deviation) - 60) : (ListenerUtil.mutListener.listen(3325) ? (Math.abs(deviation) + 60) : (Math.abs(deviation) / 60)))));
        long seconds = (ListenerUtil.mutListener.listen(3332) ? (Math.abs(deviation) / 60) : (ListenerUtil.mutListener.listen(3331) ? (Math.abs(deviation) * 60) : (ListenerUtil.mutListener.listen(3330) ? (Math.abs(deviation) - 60) : (ListenerUtil.mutListener.listen(3329) ? (Math.abs(deviation) + 60) : (Math.abs(deviation) % 60)))));
        String lastUpdate = DateUtils.formatDateTime(getActivity(), status.getLastUpdateTime(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_NO_NOON | DateUtils.FORMAT_NO_MIDNIGHT);
        statusColor = ArrivalInfoUtils.computeColorFromDeviation(deviationMin);
        if (!ListenerUtil.mutListener.listen(3335)) {
            if (statusColor != R.color.stop_info_ontime) {
                if (!ListenerUtil.mutListener.listen(3334)) {
                    // Show early/late/scheduled color
                    d.setColor(getResources().getColor(statusColor));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3333)) {
                    // For on-time, use header default color
                    d.setColor(getResources().getColor(R.color.theme_primary));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3357)) {
            if ((ListenerUtil.mutListener.listen(3340) ? (deviation <= 0) : (ListenerUtil.mutListener.listen(3339) ? (deviation > 0) : (ListenerUtil.mutListener.listen(3338) ? (deviation < 0) : (ListenerUtil.mutListener.listen(3337) ? (deviation != 0) : (ListenerUtil.mutListener.listen(3336) ? (deviation == 0) : (deviation >= 0))))))) {
                if (!ListenerUtil.mutListener.listen(3356)) {
                    if ((ListenerUtil.mutListener.listen(3353) ? (deviation >= 60) : (ListenerUtil.mutListener.listen(3352) ? (deviation <= 60) : (ListenerUtil.mutListener.listen(3351) ? (deviation > 60) : (ListenerUtil.mutListener.listen(3350) ? (deviation != 60) : (ListenerUtil.mutListener.listen(3349) ? (deviation == 60) : (deviation < 60))))))) {
                        if (!ListenerUtil.mutListener.listen(3355)) {
                            vehicleDeviation.setText(context.getString(R.string.trip_details_real_time_sec_late, seconds, lastUpdate));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3354)) {
                            vehicleDeviation.setText(context.getString(R.string.trip_details_real_time_min_sec_late, minutes, seconds, lastUpdate));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3348)) {
                    if ((ListenerUtil.mutListener.listen(3345) ? (deviation >= -60) : (ListenerUtil.mutListener.listen(3344) ? (deviation <= -60) : (ListenerUtil.mutListener.listen(3343) ? (deviation < -60) : (ListenerUtil.mutListener.listen(3342) ? (deviation != -60) : (ListenerUtil.mutListener.listen(3341) ? (deviation == -60) : (deviation > -60))))))) {
                        if (!ListenerUtil.mutListener.listen(3347)) {
                            vehicleDeviation.setText(context.getString(R.string.trip_details_real_time_sec_early, seconds, lastUpdate));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3346)) {
                            vehicleDeviation.setText(context.getString(R.string.trip_details_real_time_min_sec_early, minutes, seconds, lastUpdate));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3362)) {
            if (status.getOccupancyStatus() != null) {
                if (!ListenerUtil.mutListener.listen(3360)) {
                    // Real-time occupancy data
                    UIUtils.setOccupancyVisibilityAndColor(occupancyView, status.getOccupancyStatus(), OccupancyState.REALTIME);
                }
                if (!ListenerUtil.mutListener.listen(3361)) {
                    UIUtils.setOccupancyContentDescription(occupancyView, status.getOccupancyStatus(), OccupancyState.REALTIME);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3358)) {
                    // Hide occupancy by setting null value
                    UIUtils.setOccupancyVisibilityAndColor(occupancyView, null, OccupancyState.REALTIME);
                }
                if (!ListenerUtil.mutListener.listen(3359)) {
                    UIUtils.setOccupancyContentDescription(occupancyView, null, OccupancyState.REALTIME);
                }
            }
        }
    }

    private Integer findIndexForStop(ObaTripSchedule.StopTime[] stopTimes, String stopId) {
        if (!ListenerUtil.mutListener.listen(3363)) {
            if (stopId == null) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(3370)) {
            {
                long _loopCounter29 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(3369) ? (i >= stopTimes.length) : (ListenerUtil.mutListener.listen(3368) ? (i <= stopTimes.length) : (ListenerUtil.mutListener.listen(3367) ? (i > stopTimes.length) : (ListenerUtil.mutListener.listen(3366) ? (i != stopTimes.length) : (ListenerUtil.mutListener.listen(3365) ? (i == stopTimes.length) : (i < stopTimes.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter29", ++_loopCounter29);
                    if (!ListenerUtil.mutListener.listen(3364)) {
                        if (stopId.equals(stopTimes[i].getStopId())) {
                            return i;
                        }
                    }
                }
            }
        }
        return null;
    }

    private void showArrivals(String stopId, String stopName, String stopDirection) {
        if (!ListenerUtil.mutListener.listen(3371)) {
            new ArrivalsListActivity.Builder(getActivity(), stopId).setUpMode(NavHelp.UP_MODE_BACK).setStopName(stopName).setStopDirection(stopDirection).start();
        }
    }

    private final AdapterView.OnItemClickListener mClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (!ListenerUtil.mutListener.listen(3373)) {
                if ((ListenerUtil.mutListener.listen(3372) ? (mTripInfo == null && mTripInfo.getSchedule() == null) : (mTripInfo == null || mTripInfo.getSchedule() == null))) {
                    return;
                }
            }
            ObaTripSchedule.StopTime time = mTripInfo.getSchedule().getStopTimes()[position];
            ObaReferences refs = mTripInfo.getRefs();
            String stopId = time.getStopId();
            ObaStop stop = refs.getStop(stopId);
            if (!ListenerUtil.mutListener.listen(3374)) {
                showArrivals(stopId, stop.getName(), stop.getDirection());
            }
        }
    };

    private final AdapterView.OnItemLongClickListener mLongClickListener = new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            if (!ListenerUtil.mutListener.listen(3396)) {
                if ((ListenerUtil.mutListener.listen(3379) ? (position >= 1) : (ListenerUtil.mutListener.listen(3378) ? (position <= 1) : (ListenerUtil.mutListener.listen(3377) ? (position < 1) : (ListenerUtil.mutListener.listen(3376) ? (position != 1) : (ListenerUtil.mutListener.listen(3375) ? (position == 1) : (position > 1))))))) {
                    // Build AlertDialog
                    AlertDialog.Builder bldr = new AlertDialog.Builder(getActivity());
                    if (!ListenerUtil.mutListener.listen(3380)) {
                        bldr.setMessage(R.string.destination_reminder_dialog_msg).setTitle(R.string.destination_reminder_dialog_title);
                    }
                    if (!ListenerUtil.mutListener.listen(3392)) {
                        // Confirmation button
                        bldr.setPositiveButton(R.string.destination_reminder_confirm, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!ListenerUtil.mutListener.listen(3382)) {
                                    if (!LocationUtils.isLocationEnabled(getContext())) {
                                        if (!ListenerUtil.mutListener.listen(3381)) {
                                            askUserToTurnLocationOn();
                                        }
                                        return;
                                    }
                                }
                                int locMode = LocationUtils.getLocationMode(getContext());
                                // If the user hasn't opted out of "Change Location Mode" dialog, show it to them
                                SharedPreferences prefs = Application.getPrefs();
                                if (!ListenerUtil.mutListener.listen(3385)) {
                                    if ((ListenerUtil.mutListener.listen(3383) ? (!(prefs.getBoolean(getString(R.string.preference_key_never_show_change_location_mode_dialog), false)) || locMode != Settings.Secure.LOCATION_MODE_HIGH_ACCURACY) : (!(prefs.getBoolean(getString(R.string.preference_key_never_show_change_location_mode_dialog), false)) && locMode != Settings.Secure.LOCATION_MODE_HIGH_ACCURACY))) {
                                        if (!ListenerUtil.mutListener.listen(3384)) {
                                            getDialogForLocationModeChanges().show();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(3387)) {
                                    // Warn users that destination reminders are in beta
                                    if (!(prefs.getBoolean(getString(R.string.preference_key_never_show_destination_reminder_beta_dialog), false))) {
                                        if (!ListenerUtil.mutListener.listen(3386)) {
                                            createDestinationReminderBetaDialog().show();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(3388)) {
                                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_destination_reminder), getString(R.string.analytics_label_destination_reminder_variant_started));
                                }
                                if (!ListenerUtil.mutListener.listen(3389)) {
                                    startNavigationService(setUpNavigationService(position));
                                }
                                if (!ListenerUtil.mutListener.listen(3390)) {
                                    Toast.makeText(Application.get(), Application.get().getString(R.string.destination_reminder_title), Toast.LENGTH_LONG).show();
                                }
                                if (!ListenerUtil.mutListener.listen(3391)) {
                                    TravelBehaviorManager.saveDestinationReminders(mStopId, mDestinationId, mTripId, mRouteId, mTripInfo.getCurrentTime());
                                }
                            }
                        });
                    }
                    if (!ListenerUtil.mutListener.listen(3393)) {
                        // Cancellation Button
                        bldr.setNegativeButton(R.string.destination_reminder_cancel, (dialog, which) -> {
                        });
                    }
                    // Display
                    AlertDialog dialog = bldr.create();
                    if (!ListenerUtil.mutListener.listen(3394)) {
                        dialog.setOwnerActivity(getActivity());
                    }
                    if (!ListenerUtil.mutListener.listen(3395)) {
                        dialog.show();
                    }
                }
            }
            return true;
        }

        /**
         * @param pId Position of a stop for which we started Destination alert
         * @return Intent object to be used for starting navigation service
         */
        private Intent setUpNavigationService(int pId) {
            ObaTripSchedule.StopTime timeLast = mTripInfo.getSchedule().getStopTimes()[(ListenerUtil.mutListener.listen(3400) ? (pId % 1) : (ListenerUtil.mutListener.listen(3399) ? (pId / 1) : (ListenerUtil.mutListener.listen(3398) ? (pId * 1) : (ListenerUtil.mutListener.listen(3397) ? (pId + 1) : (pId - 1)))))];
            ObaTripSchedule.StopTime timeDest = mTripInfo.getSchedule().getStopTimes()[pId];
            ObaReferences refs = mTripInfo.getRefs();
            String destStopId = timeDest.getStopId();
            String lastStopId = timeLast.getStopId();
            ObaStop destStop = refs.getStop(destStopId);
            ObaStop lastStop = refs.getStop(lastStopId);
            if (!ListenerUtil.mutListener.listen(3401)) {
                DBUtil.addToDB(lastStop);
            }
            if (!ListenerUtil.mutListener.listen(3402)) {
                DBUtil.addToDB(destStop);
            }
            Intent serviceIntent = new Intent(getContext(), NavigationService.class);
            if (!ListenerUtil.mutListener.listen(3403)) {
                mDestinationId = destStop.getId();
            }
            if (!ListenerUtil.mutListener.listen(3404)) {
                serviceIntent.putExtra(NavigationService.DESTINATION_ID, mDestinationId);
            }
            if (!ListenerUtil.mutListener.listen(3405)) {
                serviceIntent.putExtra(NavigationService.BEFORE_STOP_ID, lastStop.getId());
            }
            if (!ListenerUtil.mutListener.listen(3406)) {
                serviceIntent.putExtra(NavigationService.TRIP_ID, mTripId);
            }
            if (!ListenerUtil.mutListener.listen(3408)) {
                // update UI
                if (mDestinationId != null) {
                    if (!ListenerUtil.mutListener.listen(3407)) {
                        mDestinationIndex = findIndexForStop(mTripInfo.getSchedule().getStopTimes(), mDestinationId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3409)) {
                mAdapter.notifyDataSetChanged();
            }
            if (!ListenerUtil.mutListener.listen(3410)) {
                // Save Intent so that we can call up startNavigationService() from onActivityResult() later
                getActivity().setIntent(serviceIntent);
            }
            return serviceIntent;
        }

        /**
         * This will allow user to turn on location within application context
         */
        private void askUserToTurnLocationOn() {
            if (!ListenerUtil.mutListener.listen(3411)) {
                mLocationRequest = LocationRequest.create();
            }
            if (!ListenerUtil.mutListener.listen(3412)) {
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            }
            // Specifies the types of location services the client is interested in using.
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            if (!ListenerUtil.mutListener.listen(3413)) {
                builder.addLocationRequest(mLocationRequest);
            }
            if (!ListenerUtil.mutListener.listen(3414)) {
                mResult = LocationServices.getSettingsClient(getActivity()).checkLocationSettings(builder.build());
            }
            if (!ListenerUtil.mutListener.listen(3419)) {
                // by looking at the status code from the LocationSettingsResponse object.
                mResult.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {

                    @Override
                    public void onComplete(Task<LocationSettingsResponse> task) {
                        try {
                            LocationSettingsResponse response = task.getResult(ApiException.class);
                        } catch (ApiException APIexc) {
                            if (!ListenerUtil.mutListener.listen(3418)) {
                                switch(APIexc.getStatusCode()) {
                                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                        // user a dialog.
                                        try {
                                            ResolvableApiException resolvable = (ResolvableApiException) APIexc;
                                            if (!ListenerUtil.mutListener.listen(3415)) {
                                                // and check the result in onActivityResult()
                                                Log.d(TAG, "Showing dialog to user for desired location settings...");
                                            }
                                            if (!ListenerUtil.mutListener.listen(3416)) {
                                                resolvable.startResolutionForResult(getActivity(), REQUEST_ENABLE_LOCATION);
                                            }
                                        } catch (IntentSender.SendIntentException sendIntExc) {
                                        } catch (ClassCastException classCastExc) {
                                        }
                                        break;
                                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                        if (!ListenerUtil.mutListener.listen(3417)) {
                                            // settings so we won't show the dialog.
                                            Log.d(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                                        }
                                        break;
                                }
                            }
                        }
                    }
                });
            }
        }

        /**
         * Create "High Accuracy GPS needed alert" dialog
         *
         * @return dialog for "High Accuracy GPS needed alert"
         */
        private Dialog getDialogForLocationModeChanges() {
            View view = getActivity().getLayoutInflater().inflate(R.layout.change_locationmode_dialog, null);
            CheckBox neverShowDialog = view.findViewById(R.id.change_locationmode_never_ask_again);
            if (!ListenerUtil.mutListener.listen(3420)) {
                neverShowDialog.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                    // Save the preference
                    PreferenceUtils.saveBoolean(getString(R.string.preference_key_never_show_change_location_mode_dialog), isChecked);
                });
            }
            Drawable icon = getResources().getDrawable(android.R.drawable.ic_dialog_map);
            if (!ListenerUtil.mutListener.listen(3421)) {
                DrawableCompat.setTint(icon, getResources().getColor(R.color.theme_primary));
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle(R.string.main_changelocationmode_title).setIcon(icon).setCancelable(false).setView(view).setPositiveButton(R.string.rt_yes, (dialog, which) -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))).setNegativeButton(R.string.rt_no, (dialog, which) -> {
            });
            return builder.create();
        }
    };

    /**
     * Create dialog reminding user that destination reminders are in beta
     *
     * @return dialog reminding user that destination reminders are in beta
     */
    private Dialog createDestinationReminderBetaDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.destination_reminder_beta_dialog, null);
        CheckBox neverShowDialog = view.findViewById(R.id.destination_reminder_beta_never_show_again);
        if (!ListenerUtil.mutListener.listen(3422)) {
            neverShowDialog.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                // Save the preference
                PreferenceUtils.saveBoolean(getString(R.string.preference_key_never_show_destination_reminder_beta_dialog), isChecked);
            });
        }
        Drawable icon = getResources().getDrawable(android.R.drawable.ic_dialog_alert);
        if (!ListenerUtil.mutListener.listen(3423)) {
            DrawableCompat.setTint(icon, getResources().getColor(R.color.theme_primary));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle(R.string.destination_reminder_beta_title).setIcon(icon).setCancelable(false).setView(view).setPositiveButton(R.string.ok, (dialogInterface, i) -> {
        });
        return builder.create();
    }

    private final Handler mRefreshHandler = new Handler();

    private final Runnable mRefresh = new Runnable() {

        public void run() {
            if (!ListenerUtil.mutListener.listen(3424)) {
                refresh();
            }
        }
    };

    private TripDetailsLoader getTripDetailsLoader() {
        if (!ListenerUtil.mutListener.listen(3425)) {
            // If the Fragment hasn't been attached to an Activity yet, return null
            if (!isAdded()) {
                return null;
            }
        }
        Loader<ObaTripDetailsResponse> l = getLoaderManager().getLoader(TRIP_DETAILS_LOADER);
        return (TripDetailsLoader) l;
    }

    private void refresh() {
        if (!ListenerUtil.mutListener.listen(3428)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(3426)) {
                    UIUtils.showProgress(this, true);
                }
                if (!ListenerUtil.mutListener.listen(3427)) {
                    getTripDetailsLoader().onContentChanged();
                }
            }
        }
    }

    private final class TripDetailsLoaderCallback implements LoaderManager.LoaderCallbacks<ObaTripDetailsResponse> {

        @Override
        public Loader<ObaTripDetailsResponse> onCreateLoader(int id, Bundle args) {
            return new TripDetailsLoader(getActivity(), mTripId);
        }

        @Override
        public void onLoadFinished(Loader<ObaTripDetailsResponse> loader, ObaTripDetailsResponse data) {
            if (!ListenerUtil.mutListener.listen(3429)) {
                setTripDetails(data);
            }
            if (!ListenerUtil.mutListener.listen(3432)) {
                // The list should now be shown.
                if (isResumed()) {
                    if (!ListenerUtil.mutListener.listen(3431)) {
                        setListShown(true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3430)) {
                        setListShownNoAnimation(true);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3433)) {
                // Clear any pending refreshes
                mRefreshHandler.removeCallbacks(mRefresh);
            }
            if (!ListenerUtil.mutListener.listen(3434)) {
                // Post an update
                mRefreshHandler.postDelayed(mRefresh, REFRESH_PERIOD);
            }
        }

        @Override
        public void onLoaderReset(Loader<ObaTripDetailsResponse> loader) {
        }
    }

    private static final class TripDetailsLoader extends AsyncTaskLoader<ObaTripDetailsResponse> {

        private final String mTripId;

        private ObaTripDetailsResponse mLastGoodResponse;

        private long mLastResponseTime = 0;

        private long mLastGoodResponseTime = 0;

        TripDetailsLoader(Context context, String tripId) {
            super(context);
            mTripId = tripId;
        }

        @Override
        public ObaTripDetailsResponse loadInBackground() {
            return ObaTripDetailsRequest.newRequest(getContext(), mTripId).call();
        }

        @Override
        public void deliverResult(ObaTripDetailsResponse data) {
            if (!ListenerUtil.mutListener.listen(3435)) {
                mLastResponseTime = System.currentTimeMillis();
            }
            if (!ListenerUtil.mutListener.listen(3438)) {
                if (data.getCode() == ObaApi.OBA_OK) {
                    if (!ListenerUtil.mutListener.listen(3436)) {
                        mLastGoodResponse = data;
                    }
                    if (!ListenerUtil.mutListener.listen(3437)) {
                        mLastGoodResponseTime = mLastResponseTime;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3439)) {
                super.deliverResult(data);
            }
        }

        public long getLastResponseTime() {
            return mLastResponseTime;
        }

        public ObaTripDetailsResponse getLastGoodResponse() {
            return mLastGoodResponse;
        }

        public long getLastGoodResponseTime() {
            return mLastGoodResponseTime;
        }
    }

    private final class TripDetailsAdapter extends BaseAdapter {

        LayoutInflater mInflater;

        ObaTripSchedule mSchedule;

        ObaReferences mRefs;

        ObaTripStatus mStatus;

        Integer mNextStopIndex;

        public TripDetailsAdapter() {
            if (!ListenerUtil.mutListener.listen(3440)) {
                this.mInflater = LayoutInflater.from(TripDetailsListFragment.this.getActivity());
            }
            if (!ListenerUtil.mutListener.listen(3441)) {
                updateData();
            }
        }

        private void updateData() {
            if (!ListenerUtil.mutListener.listen(3442)) {
                this.mSchedule = mTripInfo.getSchedule();
            }
            if (!ListenerUtil.mutListener.listen(3443)) {
                this.mRefs = mTripInfo.getRefs();
            }
            if (!ListenerUtil.mutListener.listen(3444)) {
                this.mStatus = mTripInfo.getStatus();
            }
            if (!ListenerUtil.mutListener.listen(3445)) {
                mNextStopIndex = null;
            }
            if (!ListenerUtil.mutListener.listen(3446)) {
                if (mStatus == null) {
                    // We don't have real-time data - clear next stop index
                    return;
                }
            }
            // Based on real-time data, set the index for the next stop
            String stopId = mStatus.getNextStop();
            int i;
            if (!ListenerUtil.mutListener.listen(3454)) {
                {
                    long _loopCounter30 = 0;
                    for (i = 0; (ListenerUtil.mutListener.listen(3453) ? (i >= mSchedule.getStopTimes().length) : (ListenerUtil.mutListener.listen(3452) ? (i <= mSchedule.getStopTimes().length) : (ListenerUtil.mutListener.listen(3451) ? (i > mSchedule.getStopTimes().length) : (ListenerUtil.mutListener.listen(3450) ? (i != mSchedule.getStopTimes().length) : (ListenerUtil.mutListener.listen(3449) ? (i == mSchedule.getStopTimes().length) : (i < mSchedule.getStopTimes().length)))))); i++) {
                        ListenerUtil.loopListener.listen("_loopCounter30", ++_loopCounter30);
                        ObaTripSchedule.StopTime time = mSchedule.getStopTimes()[i];
                        if (!ListenerUtil.mutListener.listen(3448)) {
                            if (time.getStopId().equals(stopId)) {
                                if (!ListenerUtil.mutListener.listen(3447)) {
                                    mNextStopIndex = i;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void notifyDataSetChanged() {
            if (!ListenerUtil.mutListener.listen(3455)) {
                updateData();
            }
            if (!ListenerUtil.mutListener.listen(3456)) {
                super.notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return mSchedule.getStopTimes().length;
        }

        @Override
        public Object getItem(int position) {
            return mSchedule.getStopTimes()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (!ListenerUtil.mutListener.listen(3458)) {
                if (convertView == null) {
                    if (!ListenerUtil.mutListener.listen(3457)) {
                        convertView = mInflater.inflate(R.layout.trip_details_listitem, parent, false);
                    }
                }
            }
            ObaTripSchedule.StopTime stopTime = mSchedule.getStopTimes()[position];
            ObaStop stop = mRefs.getStop(stopTime.getStopId());
            ObaTrip trip = mRefs.getTrip(mTripId);
            ObaRoute route = mRefs.getRoute(trip.getRouteId());
            TextView stopName = (TextView) convertView.findViewById(R.id.stop_name);
            if (!ListenerUtil.mutListener.listen(3459)) {
                stopName.setText(UIUtils.formatDisplayText(stop.getName()));
            }
            TextView time = (TextView) convertView.findViewById(R.id.time);
            ViewGroup realtime = (ViewGroup) convertView.findViewById(R.id.eta_realtime_indicator);
            if (!ListenerUtil.mutListener.listen(3460)) {
                realtime.setVisibility(View.GONE);
            }
            ViewGroup occupancyView = convertView.findViewById(R.id.occupancy);
            long date;
            long deviation = 0;
            int statusColor;
            if (mStatus != null) {
                // If we have real-time info, use that
                date = mStatus.getServiceDate();
                if (!ListenerUtil.mutListener.listen(3465)) {
                    deviation = mStatus.getScheduleDeviation();
                }
                long deviationMin = TimeUnit.SECONDS.toMinutes(mStatus.getScheduleDeviation());
                if (mStatus.isPredicted()) {
                    statusColor = ArrivalInfoUtils.computeColorFromDeviation(deviationMin);
                } else {
                    statusColor = R.color.stop_info_scheduled_time;
                }
            } else {
                // Use current date - its only to offset time correctly from midnight
                Calendar cal = new GregorianCalendar();
                if (!ListenerUtil.mutListener.listen(3461)) {
                    // Reset to midnight of today
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                }
                if (!ListenerUtil.mutListener.listen(3462)) {
                    cal.set(Calendar.MINUTE, 0);
                }
                if (!ListenerUtil.mutListener.listen(3463)) {
                    cal.set(Calendar.SECOND, 0);
                }
                if (!ListenerUtil.mutListener.listen(3464)) {
                    cal.set(Calendar.MILLISECOND, 0);
                }
                date = cal.getTime().getTime();
                statusColor = R.color.stop_info_scheduled_time;
            }
            if (!ListenerUtil.mutListener.listen(3474)) {
                time.setText(DateUtils.formatDateTime(getActivity(), date + (ListenerUtil.mutListener.listen(3469) ? (stopTime.getArrivalTime() % 1000) : (ListenerUtil.mutListener.listen(3468) ? (stopTime.getArrivalTime() / 1000) : (ListenerUtil.mutListener.listen(3467) ? (stopTime.getArrivalTime() - 1000) : (ListenerUtil.mutListener.listen(3466) ? (stopTime.getArrivalTime() + 1000) : (stopTime.getArrivalTime() * 1000))))) + (ListenerUtil.mutListener.listen(3473) ? (deviation % 1000) : (ListenerUtil.mutListener.listen(3472) ? (deviation / 1000) : (ListenerUtil.mutListener.listen(3471) ? (deviation - 1000) : (ListenerUtil.mutListener.listen(3470) ? (deviation + 1000) : (deviation * 1000))))), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_NO_NOON | DateUtils.FORMAT_NO_MIDNIGHT));
            }
            if (!ListenerUtil.mutListener.listen(3478)) {
                if ((ListenerUtil.mutListener.listen(3475) ? (mStatus != null || Status.CANCELED.equals(mStatus.getStatus())) : (mStatus != null && Status.CANCELED.equals(mStatus.getStatus())))) {
                    if (!ListenerUtil.mutListener.listen(3476)) {
                        // CANCELED trip - Strike through the text fields
                        stopName.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    if (!ListenerUtil.mutListener.listen(3477)) {
                        time.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3483)) {
                if (stopTime.getPredictedOccupancy() != null) {
                    if (!ListenerUtil.mutListener.listen(3481)) {
                        // Predicted occupancy data
                        UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopTime.getPredictedOccupancy(), OccupancyState.PREDICTED);
                    }
                    if (!ListenerUtil.mutListener.listen(3482)) {
                        UIUtils.setOccupancyContentDescription(occupancyView, stopTime.getPredictedOccupancy(), OccupancyState.PREDICTED);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3479)) {
                        // Historical occupancy data
                        UIUtils.setOccupancyVisibilityAndColor(occupancyView, stopTime.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                    }
                    if (!ListenerUtil.mutListener.listen(3480)) {
                        UIUtils.setOccupancyContentDescription(occupancyView, stopTime.getHistoricalOccupancy(), OccupancyState.HISTORICAL);
                    }
                }
            }
            ImageView bus = (ImageView) convertView.findViewById(R.id.bus_icon);
            ImageView stopIcon = (ImageView) convertView.findViewById(R.id.stop_icon);
            ImageView flagIcon = (ImageView) convertView.findViewById(R.id.destination_icon);
            ImageView topLine = (ImageView) convertView.findViewById(R.id.top_line);
            ImageView bottomLine = (ImageView) convertView.findViewById(R.id.bottom_line);
            ImageView transitStop = (ImageView) convertView.findViewById(R.id.transit_stop);
            // Set bus and realtime indicator to match status color
            int id;
            switch(route.getType()) {
                case ObaRoute.TYPE_FERRY:
                    id = R.drawable.ic_ferry;
                    break;
                case ObaRoute.TYPE_SUBWAY:
                    id = R.drawable.ic_subway;
                    break;
                case ObaRoute.TYPE_CABLECAR:
                    id = R.drawable.ic_tram;
                    break;
                case ObaRoute.TYPE_TRAM:
                    id = R.drawable.ic_tram;
                    break;
                case ObaRoute.TYPE_RAIL:
                    id = R.drawable.ic_train;
                    break;
                case ObaRoute.TYPE_BUS:
                    id = R.drawable.ic_bus;
                    break;
                default:
                    id = R.drawable.ic_bus;
                    break;
            }
            if (!ListenerUtil.mutListener.listen(3484)) {
                bus.setImageDrawable(getResources().getDrawable(id));
            }
            if (!ListenerUtil.mutListener.listen(3485)) {
                // Make the icon itself white
                bus.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }
            if (!ListenerUtil.mutListener.listen(3492)) {
                if ((ListenerUtil.mutListener.listen(3490) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3489) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3488) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3487) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.LOLLIPOP) : (ListenerUtil.mutListener.listen(3486) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP))))))) {
                    if (!ListenerUtil.mutListener.listen(3491)) {
                        // Change the circle's color
                        bus.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(statusColor)));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3493)) {
                UIUtils.setRealtimeIndicatorColor(realtime, getResources().getColor(statusColor), android.R.color.transparent);
            }
            int routeColor;
            // If a route color (other than the default white) is included in the API response, then use it
            if ((ListenerUtil.mutListener.listen(3494) ? (route.getColor() != null || route.getColor() != android.R.color.white) : (route.getColor() != null && route.getColor() != android.R.color.white))) {
                routeColor = route.getColor();
            } else {
                // Use the theme color
                routeColor = getResources().getColor(R.color.theme_primary);
            }
            if (!ListenerUtil.mutListener.listen(3495)) {
                stopIcon.setColorFilter(routeColor);
            }
            if (!ListenerUtil.mutListener.listen(3496)) {
                topLine.setColorFilter(routeColor);
            }
            if (!ListenerUtil.mutListener.listen(3497)) {
                bottomLine.setColorFilter(routeColor);
            }
            if (!ListenerUtil.mutListener.listen(3498)) {
                transitStop.setColorFilter(routeColor);
            }
            if (!ListenerUtil.mutListener.listen(3499)) {
                flagIcon.setColorFilter(routeColor);
            }
            if (!ListenerUtil.mutListener.listen(3515)) {
                if ((ListenerUtil.mutListener.listen(3504) ? (position >= 0) : (ListenerUtil.mutListener.listen(3503) ? (position <= 0) : (ListenerUtil.mutListener.listen(3502) ? (position > 0) : (ListenerUtil.mutListener.listen(3501) ? (position < 0) : (ListenerUtil.mutListener.listen(3500) ? (position != 0) : (position == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(3513)) {
                        // First stop in trip - hide the top half of the transit line
                        topLine.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3514)) {
                        bottomLine.setVisibility(View.VISIBLE);
                    }
                } else if (position == (ListenerUtil.mutListener.listen(3508) ? (mSchedule.getStopTimes().length % 1) : (ListenerUtil.mutListener.listen(3507) ? (mSchedule.getStopTimes().length / 1) : (ListenerUtil.mutListener.listen(3506) ? (mSchedule.getStopTimes().length * 1) : (ListenerUtil.mutListener.listen(3505) ? (mSchedule.getStopTimes().length + 1) : (mSchedule.getStopTimes().length - 1)))))) {
                    if (!ListenerUtil.mutListener.listen(3511)) {
                        // Last stop in trip - hide the bottom half of the transit line
                        topLine.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3512)) {
                        bottomLine.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3509)) {
                        // Middle of trip - show top and bottom transit lines
                        topLine.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3510)) {
                        bottomLine.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3534)) {
                if ((ListenerUtil.mutListener.listen(3521) ? (mDestinationIndex != null || (ListenerUtil.mutListener.listen(3520) ? (mDestinationIndex >= position) : (ListenerUtil.mutListener.listen(3519) ? (mDestinationIndex <= position) : (ListenerUtil.mutListener.listen(3518) ? (mDestinationIndex > position) : (ListenerUtil.mutListener.listen(3517) ? (mDestinationIndex < position) : (ListenerUtil.mutListener.listen(3516) ? (mDestinationIndex != position) : (mDestinationIndex == position))))))) : (mDestinationIndex != null && (ListenerUtil.mutListener.listen(3520) ? (mDestinationIndex >= position) : (ListenerUtil.mutListener.listen(3519) ? (mDestinationIndex <= position) : (ListenerUtil.mutListener.listen(3518) ? (mDestinationIndex > position) : (ListenerUtil.mutListener.listen(3517) ? (mDestinationIndex < position) : (ListenerUtil.mutListener.listen(3516) ? (mDestinationIndex != position) : (mDestinationIndex == position))))))))) {
                    if (!ListenerUtil.mutListener.listen(3532)) {
                        stopIcon.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(3533)) {
                        flagIcon.setVisibility(View.VISIBLE);
                    }
                } else if ((ListenerUtil.mutListener.listen(3527) ? (mStopIndex != null || (ListenerUtil.mutListener.listen(3526) ? (mStopIndex >= position) : (ListenerUtil.mutListener.listen(3525) ? (mStopIndex <= position) : (ListenerUtil.mutListener.listen(3524) ? (mStopIndex > position) : (ListenerUtil.mutListener.listen(3523) ? (mStopIndex < position) : (ListenerUtil.mutListener.listen(3522) ? (mStopIndex != position) : (mStopIndex == position))))))) : (mStopIndex != null && (ListenerUtil.mutListener.listen(3526) ? (mStopIndex >= position) : (ListenerUtil.mutListener.listen(3525) ? (mStopIndex <= position) : (ListenerUtil.mutListener.listen(3524) ? (mStopIndex > position) : (ListenerUtil.mutListener.listen(3523) ? (mStopIndex < position) : (ListenerUtil.mutListener.listen(3522) ? (mStopIndex != position) : (mStopIndex == position))))))))) {
                    if (!ListenerUtil.mutListener.listen(3530)) {
                        stopIcon.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3531)) {
                        flagIcon.setVisibility(View.GONE);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3528)) {
                        flagIcon.setVisibility(View.GONE);
                    }
                    if (!ListenerUtil.mutListener.listen(3529)) {
                        stopIcon.setVisibility(View.GONE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(3576)) {
                if (mNextStopIndex != null) {
                    if (!ListenerUtil.mutListener.listen(3552)) {
                        if ((ListenerUtil.mutListener.listen(3546) ? (position >= (ListenerUtil.mutListener.listen(3541) ? (mNextStopIndex % 1) : (ListenerUtil.mutListener.listen(3540) ? (mNextStopIndex / 1) : (ListenerUtil.mutListener.listen(3539) ? (mNextStopIndex * 1) : (ListenerUtil.mutListener.listen(3538) ? (mNextStopIndex + 1) : (mNextStopIndex - 1)))))) : (ListenerUtil.mutListener.listen(3545) ? (position <= (ListenerUtil.mutListener.listen(3541) ? (mNextStopIndex % 1) : (ListenerUtil.mutListener.listen(3540) ? (mNextStopIndex / 1) : (ListenerUtil.mutListener.listen(3539) ? (mNextStopIndex * 1) : (ListenerUtil.mutListener.listen(3538) ? (mNextStopIndex + 1) : (mNextStopIndex - 1)))))) : (ListenerUtil.mutListener.listen(3544) ? (position > (ListenerUtil.mutListener.listen(3541) ? (mNextStopIndex % 1) : (ListenerUtil.mutListener.listen(3540) ? (mNextStopIndex / 1) : (ListenerUtil.mutListener.listen(3539) ? (mNextStopIndex * 1) : (ListenerUtil.mutListener.listen(3538) ? (mNextStopIndex + 1) : (mNextStopIndex - 1)))))) : (ListenerUtil.mutListener.listen(3543) ? (position < (ListenerUtil.mutListener.listen(3541) ? (mNextStopIndex % 1) : (ListenerUtil.mutListener.listen(3540) ? (mNextStopIndex / 1) : (ListenerUtil.mutListener.listen(3539) ? (mNextStopIndex * 1) : (ListenerUtil.mutListener.listen(3538) ? (mNextStopIndex + 1) : (mNextStopIndex - 1)))))) : (ListenerUtil.mutListener.listen(3542) ? (position != (ListenerUtil.mutListener.listen(3541) ? (mNextStopIndex % 1) : (ListenerUtil.mutListener.listen(3540) ? (mNextStopIndex / 1) : (ListenerUtil.mutListener.listen(3539) ? (mNextStopIndex * 1) : (ListenerUtil.mutListener.listen(3538) ? (mNextStopIndex + 1) : (mNextStopIndex - 1)))))) : (position == (ListenerUtil.mutListener.listen(3541) ? (mNextStopIndex % 1) : (ListenerUtil.mutListener.listen(3540) ? (mNextStopIndex / 1) : (ListenerUtil.mutListener.listen(3539) ? (mNextStopIndex * 1) : (ListenerUtil.mutListener.listen(3538) ? (mNextStopIndex + 1) : (mNextStopIndex - 1)))))))))))) {
                            if (!ListenerUtil.mutListener.listen(3548)) {
                                // The bus just passed this stop - show the bus icon here
                                bus.setVisibility(View.VISIBLE);
                            }
                            if (!ListenerUtil.mutListener.listen(3551)) {
                                if ((ListenerUtil.mutListener.listen(3549) ? (mStatus != null || mStatus.isPredicted()) : (mStatus != null && mStatus.isPredicted()))) {
                                    if (!ListenerUtil.mutListener.listen(3550)) {
                                        // Show realtime indicator
                                        realtime.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3547)) {
                                bus.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3575)) {
                        if ((ListenerUtil.mutListener.listen(3557) ? (position >= mNextStopIndex) : (ListenerUtil.mutListener.listen(3556) ? (position <= mNextStopIndex) : (ListenerUtil.mutListener.listen(3555) ? (position > mNextStopIndex) : (ListenerUtil.mutListener.listen(3554) ? (position != mNextStopIndex) : (ListenerUtil.mutListener.listen(3553) ? (position == mNextStopIndex) : (position < mNextStopIndex))))))) {
                            if (!ListenerUtil.mutListener.listen(3560)) {
                                // Bus passed stop - fade out views for these stops
                                stopName.setTextColor(getResources().getColor(R.color.trip_details_passed));
                            }
                            if (!ListenerUtil.mutListener.listen(3561)) {
                                time.setTextColor(getResources().getColor(R.color.trip_details_passed));
                            }
                            // X percent transparency
                            int alpha = (int) ((ListenerUtil.mutListener.listen(3565) ? (.35f % 255) : (ListenerUtil.mutListener.listen(3564) ? (.35f / 255) : (ListenerUtil.mutListener.listen(3563) ? (.35f - 255) : (ListenerUtil.mutListener.listen(3562) ? (.35f + 255) : (.35f * 255))))));
                            if (!ListenerUtil.mutListener.listen(3566)) {
                                occupancyView.setBackgroundResource(R.drawable.occupancy_background);
                            }
                            GradientDrawable d = (GradientDrawable) occupancyView.getBackground();
                            if (!ListenerUtil.mutListener.listen(3567)) {
                                d.setAlpha(alpha);
                            }
                            if (!ListenerUtil.mutListener.listen(3574)) {
                                {
                                    long _loopCounter31 = 0;
                                    // Set alpha for occupancy person icons
                                    for (int index = 0; (ListenerUtil.mutListener.listen(3573) ? (index >= occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(3572) ? (index <= occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(3571) ? (index > occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(3570) ? (index != occupancyView.getChildCount()) : (ListenerUtil.mutListener.listen(3569) ? (index == occupancyView.getChildCount()) : (index < occupancyView.getChildCount())))))); ++index) {
                                        ListenerUtil.loopListener.listen("_loopCounter31", ++_loopCounter31);
                                        if (!ListenerUtil.mutListener.listen(3568)) {
                                            ((ImageView) occupancyView.getChildAt(index)).setAlpha(alpha);
                                        }
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3558)) {
                                // Bus hasn't yet passed this stop - leave full color
                                stopName.setTextColor(getResources().getColor(R.color.trip_details_not_passed));
                            }
                            if (!ListenerUtil.mutListener.listen(3559)) {
                                time.setTextColor(getResources().getColor(R.color.trip_details_not_passed));
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(3535)) {
                        // No "next stop" info - hide the bus icon
                        bus.setVisibility(View.INVISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(3536)) {
                        // Bus hasn't passed this stop - leave full color
                        stopName.setTextColor(getResources().getColor(R.color.trip_details_not_passed));
                    }
                    if (!ListenerUtil.mutListener.listen(3537)) {
                        time.setTextColor(getResources().getColor(R.color.trip_details_not_passed));
                    }
                }
            }
            return convertView;
        }

        /**
         * Returns the next stop index based on real-time info, or null no estimated next stop
         * exists
         *
         * @return the next stop index based on real-time info, or null no estimated next stop
         * exists
         */
        public Integer getNextStopIndex() {
            return mNextStopIndex;
        }
    }

    private void setScroller(final ListView listView) {
        if (!ListenerUtil.mutListener.listen(3577)) {
            if (mScrollMode == null) {
                // Don't automatically scroll to stop or vehicle icon
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3601)) {
            switch(mScrollMode) {
                case SCROLL_MODE_VEHICLE:
                    {
                        // Scroll to bus marker if scroll mode is set to vehicle & we have index.
                        final Integer nextStop = mAdapter.getNextStopIndex();
                        if (!ListenerUtil.mutListener.listen(3588)) {
                            if (nextStop != null) {
                                if (!ListenerUtil.mutListener.listen(3587)) {
                                    listView.post(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (!ListenerUtil.mutListener.listen(3586)) {
                                                listView.setSelection((ListenerUtil.mutListener.listen(3585) ? (nextStop % 1) : (ListenerUtil.mutListener.listen(3584) ? (nextStop / 1) : (ListenerUtil.mutListener.listen(3583) ? (nextStop * 1) : (ListenerUtil.mutListener.listen(3582) ? (nextStop + 1) : (nextStop - 1))))));
                                            }
                                        }
                                    });
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(3578)) {
                                    // If we couldn't get the bus marker, fall back to stop.
                                    mStopIndex = findIndexForStop(mTripInfo.getSchedule().getStopTimes(), mStopId);
                                }
                                if (!ListenerUtil.mutListener.listen(3581)) {
                                    if (mStopIndex != null) {
                                        if (!ListenerUtil.mutListener.listen(3580)) {
                                            listView.post(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (!ListenerUtil.mutListener.listen(3579)) {
                                                        listView.setSelection(mStopIndex);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                case SCROLL_MODE_STOP:
                    {
                        if (!ListenerUtil.mutListener.listen(3600)) {
                            // Scroll to stop if we have the stopId available
                            if (mStopId != null) {
                                if (!ListenerUtil.mutListener.listen(3596)) {
                                    mStopIndex = findIndexForStop(mTripInfo.getSchedule().getStopTimes(), mStopId);
                                }
                                if (!ListenerUtil.mutListener.listen(3599)) {
                                    if (mStopIndex != null) {
                                        if (!ListenerUtil.mutListener.listen(3598)) {
                                            listView.post(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (!ListenerUtil.mutListener.listen(3597)) {
                                                        listView.setSelection(mStopIndex);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            } else {
                                // If we don't have a stop, then scroll to the current position of the bus
                                final Integer nextStop = mAdapter.getNextStopIndex();
                                if (!ListenerUtil.mutListener.listen(3595)) {
                                    if (nextStop != null) {
                                        if (!ListenerUtil.mutListener.listen(3594)) {
                                            listView.post(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (!ListenerUtil.mutListener.listen(3593)) {
                                                        listView.setSelection((ListenerUtil.mutListener.listen(3592) ? (nextStop % 1) : (ListenerUtil.mutListener.listen(3591) ? (nextStop / 1) : (ListenerUtil.mutListener.listen(3590) ? (nextStop * 1) : (ListenerUtil.mutListener.listen(3589) ? (nextStop + 1) : (nextStop - 1))))));
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
            }
        }
        if (!ListenerUtil.mutListener.listen(3602)) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(3612)) {
            if ((ListenerUtil.mutListener.listen(3607) ? (requestCode >= REQUEST_ENABLE_LOCATION) : (ListenerUtil.mutListener.listen(3606) ? (requestCode <= REQUEST_ENABLE_LOCATION) : (ListenerUtil.mutListener.listen(3605) ? (requestCode > REQUEST_ENABLE_LOCATION) : (ListenerUtil.mutListener.listen(3604) ? (requestCode < REQUEST_ENABLE_LOCATION) : (ListenerUtil.mutListener.listen(3603) ? (requestCode != REQUEST_ENABLE_LOCATION) : (requestCode == REQUEST_ENABLE_LOCATION))))))) {
                if (!ListenerUtil.mutListener.listen(3611)) {
                    switch(resultCode) {
                        case Activity.RESULT_OK:
                            if (!ListenerUtil.mutListener.listen(3608)) {
                                // All required changes were successfully made
                                Log.d(TAG, "Location enabled");
                            }
                            if (!ListenerUtil.mutListener.listen(3609)) {
                                startNavigationService(getActivity().getIntent());
                            }
                            break;
                        case Activity.RESULT_CANCELED:
                            if (!ListenerUtil.mutListener.listen(3610)) {
                                // The user was asked to change settings but chose not to
                                Log.d(TAG, "Location not enabled");
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * Starts the navigation service for destination reminders
     * @param serviceIntent
     */
    private void startNavigationService(Intent serviceIntent) {
        if (!ListenerUtil.mutListener.listen(3620)) {
            if ((ListenerUtil.mutListener.listen(3617) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(3616) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(3615) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(3614) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(3613) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                if (!ListenerUtil.mutListener.listen(3619)) {
                    Application.get().getApplicationContext().startForegroundService(serviceIntent);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3618)) {
                    Application.get().getApplicationContext().startService(serviceIntent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3621)) {
            // Register receiver
            registerReceiver();
        }
    }

    /**
     * Register receiver so that when trip is cancelled by user then flag will be removed from screen
     */
    private void registerReceiver() {
        // filter specifies which event Receiver should listen to
        IntentFilter filter = new IntentFilter();
        if (!ListenerUtil.mutListener.listen(3622)) {
            filter.addAction(ACTION_SERVICE_DESTROYED);
        }
        if (!ListenerUtil.mutListener.listen(3623)) {
            getActivity().registerReceiver(new TripEndReceiver(), filter);
        }
    }

    /**
     * When user cancels trip from "Notification menu" then destination flag in trip details screen
     * should be removed.
     *
     * From {@link NavigationService 's} onDestroy(), we send broadcast action
     */
    class TripEndReceiver extends BroadcastReceiver {

        private static final String TAG = "TripEndReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (!ListenerUtil.mutListener.listen(3629)) {
                if (intent.getAction().equals(ACTION_SERVICE_DESTROYED)) {
                    if (!ListenerUtil.mutListener.listen(3624)) {
                        Log.d(TAG, "Action received in BroadcastReceiver, trip is destroyed");
                    }
                    if (!ListenerUtil.mutListener.listen(3625)) {
                        // This is used to set flagIcon on trip details screen so setting it to null
                        mDestinationId = null;
                    }
                    if (!ListenerUtil.mutListener.listen(3626)) {
                        mDestinationIndex = null;
                    }
                    if (!ListenerUtil.mutListener.listen(3627)) {
                        // It will call up getView() so that flag icon gets unset
                        mAdapter.notifyDataSetChanged();
                    }
                    if (!ListenerUtil.mutListener.listen(3628)) {
                        // Unregister this receiver now
                        getActivity().unregisterReceiver(this);
                    }
                }
            }
        }
    }
}
