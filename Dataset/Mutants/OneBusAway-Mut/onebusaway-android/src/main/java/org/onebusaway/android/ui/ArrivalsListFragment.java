/*
 * Copyright (C) 2012-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida,
 * Benjamin Du (bendu@me.com),
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentQueryMap;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.fragment.app.DialogFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.elements.ObaReferences;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaSituation;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.elements.ObaTrip;
import org.onebusaway.android.io.elements.Occupancy;
import org.onebusaway.android.io.elements.OccupancyState;
import org.onebusaway.android.io.request.ObaArrivalInfoResponse;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.report.ui.InfrastructureIssueActivity;
import org.onebusaway.android.travelbehavior.TravelBehaviorManager;
import org.onebusaway.android.util.ArrayAdapterWithIcon;
import org.onebusaway.android.util.ArrivalInfoUtils;
import org.onebusaway.android.util.BuildFlavorUtils;
import org.onebusaway.android.util.DBUtil;
import org.onebusaway.android.util.FragmentUtils;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

// 
public class ArrivalsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<ObaArrivalInfoResponse>, ArrivalsListHeader.Controller {

    private static final String TAG = "ArrivalsListFragment";

    public static final String STOP_NAME = ".StopName";

    public static final String STOP_CODE = ".StopCode";

    public static final String STOP_DIRECTION = ".StopDir";

    /**
     * Comma-delimited set of routes that serve this stop
     * See {@link UIUtils#serializeRouteDisplayNames(ObaStop,
     * HashMap)}
     */
    public static final String STOP_ROUTES = ".StopRoutes";

    public static final String STOP_LAT = ".StopLatitude";

    public static final String STOP_LON = ".StopLongitude";

    /**
     * If set to true, the fragment is using a header external to this layout, and shouldn't
     * instantiate its own header view
     */
    public static final String EXTERNAL_HEADER = ".ExternalHeader";

    private static final long RefreshPeriod = 60 * 1000;

    private static int TRIPS_FOR_STOP_LOADER = 1;

    private static int ARRIVALS_LIST_LOADER = 2;

    private ArrivalsListAdapterBase mAdapter;

    private ArrivalsListHeader mHeader;

    private View mHeaderView;

    private View mFooter;

    private View mEmptyList;

    private AlertList mAlertList;

    private ObaStop mStop;

    private String mStopId;

    private Uri mStopUri;

    private ObaReferences mObaReferences;

    // The list of route_ids that should have their arrival info and alerts displayed. (All if empty or null)
    private ArrayList<String> mRoutesFilter;

    // Keep copy locally, since loader overwrites
    private int mLastResponseLength = -1;

    // encapsulated info before onLoadFinished() is called
    private boolean mLoadedMoreArrivals = false;

    private boolean mFavorite = false;

    private String mStopUserName;

    private TripsForStopCallback mTripsForStopCallback;

    // The list of situation alerts
    private ArrayList<SituationAlert> mSituationAlerts;

    // Set to true if we're using an external header not in this layout (e.g., if this fragment is in a sliding panel)
    private boolean mExternalHeader = false;

    private Listener mListener;

    ObaArrivalInfo[] mArrivalInfo;

    private FirebaseAnalytics mFirebaseAnalytics;

    public interface Listener {

        /**
         * Called when the ListView has been created
         *
         * @param listView the ListView that was just created
         */
        void onListViewCreated(ListView listView);

        /**
         * Called when new arrival times have been retrieved
         *
         * @param response new arrival information
         */
        void onArrivalTimesUpdated(final ObaArrivalInfoResponse response);

        /**
         * Called when the user selects the "Show route on map" for a particular route/trip
         *
         * @param arrivalInfo The arrival information for the route/trip that the user selected
         * @return true if the listener has consumed the event, false otherwise
         */
        boolean onShowRouteOnMapSelected(ArrivalInfo arrivalInfo);

        /**
         * Called when the user selects the "Sort by" option
         */
        void onSortBySelected();
    }

    /**
     * Builds an intent used to set the stop for the ArrivalListFragment directly
     * (i.e., when ArrivalsListActivity is not used)
     */
    public static class IntentBuilder {

        private Intent mIntent;

        public IntentBuilder(Context context, String stopId) {
            if (!ListenerUtil.mutListener.listen(191)) {
                mIntent = new Intent(context, ArrivalsListFragment.class);
            }
            if (!ListenerUtil.mutListener.listen(192)) {
                mIntent.setData(Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, stopId));
            }
        }

        /**
         * @param stop   ObaStop to be set
         * @param routes a HashMap of all route display names that may serve this stop - key is
         *               routeId
         */
        public IntentBuilder(Context context, ObaStop stop, HashMap<String, ObaRoute> routes) {
            if (!ListenerUtil.mutListener.listen(193)) {
                mIntent = new Intent(context, ArrivalsListFragment.class);
            }
            if (!ListenerUtil.mutListener.listen(194)) {
                mIntent.setData(Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, stop.getId()));
            }
            if (!ListenerUtil.mutListener.listen(195)) {
                setStopName(stop.getName());
            }
            if (!ListenerUtil.mutListener.listen(196)) {
                setStopCode(stop.getStopCode());
            }
            if (!ListenerUtil.mutListener.listen(197)) {
                setStopDirection(stop.getDirection());
            }
            if (!ListenerUtil.mutListener.listen(198)) {
                setStopRoutes(UIUtils.serializeRouteDisplayNames(stop, routes));
            }
            if (!ListenerUtil.mutListener.listen(199)) {
                setStopLocation(stop.getLocation());
            }
        }

        public IntentBuilder setStopName(String stopName) {
            if (!ListenerUtil.mutListener.listen(200)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_NAME, stopName);
            }
            return this;
        }

        public IntentBuilder setStopCode(String stopCode) {
            if (!ListenerUtil.mutListener.listen(201)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_CODE, stopCode);
            }
            return this;
        }

        public IntentBuilder setStopDirection(String stopDir) {
            if (!ListenerUtil.mutListener.listen(202)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_DIRECTION, stopDir);
            }
            return this;
        }

        public IntentBuilder setStopLocation(Location stopLocation) {
            if (!ListenerUtil.mutListener.listen(203)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_LAT, stopLocation.getLatitude());
            }
            if (!ListenerUtil.mutListener.listen(204)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_LON, stopLocation.getLongitude());
            }
            return this;
        }

        /**
         * Sets the routes that serve this stop via a comma-delimited set of route display names
         * <p/>
         * See {@link UIUtils#serializeRouteDisplayNames(ObaStop,
         * HashMap)}
         *
         * @param routes comma-delimited list of route display names that serve this stop
         */
        public IntentBuilder setStopRoutes(String routes) {
            if (!ListenerUtil.mutListener.listen(205)) {
                mIntent.putExtra(ArrivalsListFragment.STOP_ROUTES, routes);
            }
            return this;
        }

        public Intent build() {
            return mIntent;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(206)) {
            if (root == null) {
                // reason to create our view.
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(207)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        }
        if (!ListenerUtil.mutListener.listen(208)) {
            initArrivalInfoViews(inflater);
        }
        return inflater.inflate(R.layout.fragment_arrivals_list, null);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(209)) {
            super.onViewStateRestored(savedInstanceState);
        }
        // their phone while setting a route favorite - see #480
        RouteFavoriteDialogFragment dialogFragment = (RouteFavoriteDialogFragment) getFragmentManager().findFragmentByTag(RouteFavoriteDialogFragment.TAG);
        if (!ListenerUtil.mutListener.listen(211)) {
            if (dialogFragment != null) {
                if (!ListenerUtil.mutListener.listen(210)) {
                    setCallbackToDialogFragment(dialogFragment);
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(212)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(213)) {
            // Set the list view properties for Style B
            setListViewProperties(BuildFlavorUtils.getArrivalInfoStyleFromPreferences());
        }
        if (!ListenerUtil.mutListener.listen(214)) {
            // We have a menu item to show in action bar.
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(215)) {
            mAlertList = new AlertList(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(216)) {
            mAlertList.initView(getView().findViewById(R.id.arrivals_alert_list));
        }
        if (!ListenerUtil.mutListener.listen(217)) {
            setupHeader(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(218)) {
            setupFooter();
        }
        if (!ListenerUtil.mutListener.listen(219)) {
            setupEmptyList(null);
        }
        if (!ListenerUtil.mutListener.listen(220)) {
            // This sets the stopId and uri
            setStopId();
        }
        if (!ListenerUtil.mutListener.listen(221)) {
            setUserInfo();
        }
        if (!ListenerUtil.mutListener.listen(222)) {
            // Create an empty adapter we will use to display the loaded data
            instantiateAdapter(BuildFlavorUtils.getArrivalInfoStyleFromPreferences());
        }
        if (!ListenerUtil.mutListener.listen(223)) {
            // Start out with a progress indicator.
            setListShown(false);
        }
        if (!ListenerUtil.mutListener.listen(224)) {
            mRoutesFilter = ObaContract.StopRouteFilters.get(getActivity(), mStopId);
        }
        if (!ListenerUtil.mutListener.listen(225)) {
            mTripsForStopCallback = new TripsForStopCallback();
        }
        // LoaderManager.enableDebugLogging(true);
        LoaderManager mgr = getLoaderManager();
        if (!ListenerUtil.mutListener.listen(226)) {
            mgr.initLoader(TRIPS_FOR_STOP_LOADER, null, mTripsForStopCallback);
        }
        if (!ListenerUtil.mutListener.listen(227)) {
            mgr.initLoader(ARRIVALS_LIST_LOADER, getArguments(), this);
        }
        if (!ListenerUtil.mutListener.listen(228)) {
            // Set initial minutesAfter value in the empty list view
            setEmptyText(UIUtils.getNoArrivalsMessage(getActivity(), getArrivalsLoader().getMinutesAfter(), false, false));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(229)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(230)) {
            outState.putBoolean(EXTERNAL_HEADER, mExternalHeader);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(231)) {
            mRefreshHandler.removeCallbacks(mRefresh);
        }
        if (!ListenerUtil.mutListener.listen(233)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(232)) {
                    mHeader.onPause();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(234)) {
            super.onPause();
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(235)) {
            // after the Fragment was initialized
            checkAdapterStylePreference();
        }
        if (!ListenerUtil.mutListener.listen(237)) {
            // Notify listener that ListView is now created
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(236)) {
                    mListener.onListViewCreated(getListView());
                }
            }
        }
        // Try to show any old data just in case we're coming out of sleep
        ArrivalsListLoader loader = getArrivalsLoader();
        if (!ListenerUtil.mutListener.listen(240)) {
            if (loader != null) {
                ObaArrivalInfoResponse lastGood = loader.getLastGoodResponse();
                if (!ListenerUtil.mutListener.listen(239)) {
                    if (lastGood != null) {
                        if (!ListenerUtil.mutListener.listen(238)) {
                            setResponseData(lastGood.getArrivalInfo(), UIUtils.getAllSituations(lastGood, mRoutesFilter), lastGood.getRefs());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(241)) {
            getLoaderManager().restartLoader(TRIPS_FOR_STOP_LOADER, null, mTripsForStopCallback);
        }
        // If our timer would have gone off, then refresh.
        long lastResponseTime = getArrivalsLoader().getLastResponseTime();
        long newPeriod = Math.min(RefreshPeriod, (ListenerUtil.mutListener.listen(249) ? (((ListenerUtil.mutListener.listen(245) ? (lastResponseTime % RefreshPeriod) : (ListenerUtil.mutListener.listen(244) ? (lastResponseTime / RefreshPeriod) : (ListenerUtil.mutListener.listen(243) ? (lastResponseTime * RefreshPeriod) : (ListenerUtil.mutListener.listen(242) ? (lastResponseTime - RefreshPeriod) : (lastResponseTime + RefreshPeriod)))))) % System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(248) ? (((ListenerUtil.mutListener.listen(245) ? (lastResponseTime % RefreshPeriod) : (ListenerUtil.mutListener.listen(244) ? (lastResponseTime / RefreshPeriod) : (ListenerUtil.mutListener.listen(243) ? (lastResponseTime * RefreshPeriod) : (ListenerUtil.mutListener.listen(242) ? (lastResponseTime - RefreshPeriod) : (lastResponseTime + RefreshPeriod)))))) / System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(247) ? (((ListenerUtil.mutListener.listen(245) ? (lastResponseTime % RefreshPeriod) : (ListenerUtil.mutListener.listen(244) ? (lastResponseTime / RefreshPeriod) : (ListenerUtil.mutListener.listen(243) ? (lastResponseTime * RefreshPeriod) : (ListenerUtil.mutListener.listen(242) ? (lastResponseTime - RefreshPeriod) : (lastResponseTime + RefreshPeriod)))))) * System.currentTimeMillis()) : (ListenerUtil.mutListener.listen(246) ? (((ListenerUtil.mutListener.listen(245) ? (lastResponseTime % RefreshPeriod) : (ListenerUtil.mutListener.listen(244) ? (lastResponseTime / RefreshPeriod) : (ListenerUtil.mutListener.listen(243) ? (lastResponseTime * RefreshPeriod) : (ListenerUtil.mutListener.listen(242) ? (lastResponseTime - RefreshPeriod) : (lastResponseTime + RefreshPeriod)))))) + System.currentTimeMillis()) : (((ListenerUtil.mutListener.listen(245) ? (lastResponseTime % RefreshPeriod) : (ListenerUtil.mutListener.listen(244) ? (lastResponseTime / RefreshPeriod) : (ListenerUtil.mutListener.listen(243) ? (lastResponseTime * RefreshPeriod) : (ListenerUtil.mutListener.listen(242) ? (lastResponseTime - RefreshPeriod) : (lastResponseTime + RefreshPeriod)))))) - System.currentTimeMillis()))))));
        if (!ListenerUtil.mutListener.listen(257)) {
            // Log.d(TAG, "Refresh period:" + newPeriod);
            if ((ListenerUtil.mutListener.listen(254) ? (newPeriod >= 0) : (ListenerUtil.mutListener.listen(253) ? (newPeriod > 0) : (ListenerUtil.mutListener.listen(252) ? (newPeriod < 0) : (ListenerUtil.mutListener.listen(251) ? (newPeriod != 0) : (ListenerUtil.mutListener.listen(250) ? (newPeriod == 0) : (newPeriod <= 0))))))) {
                if (!ListenerUtil.mutListener.listen(256)) {
                    refresh();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(255)) {
                    mRefreshHandler.postDelayed(mRefresh, newPeriod);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(258)) {
            // Refresh the favorite status and stop name, in case we're returning from another view
            setUserInfo();
        }
        if (!ListenerUtil.mutListener.listen(260)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(259)) {
                    mHeader.refresh();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(261)) {
            super.onResume();
        }
    }

    @Override
    public Loader<ObaArrivalInfoResponse> onCreateLoader(int id, Bundle args) {
        return new ArrivalsListLoader(getActivity(), mStopId);
    }

    // 
    @Override
    public void onLoadFinished(Loader<ObaArrivalInfoResponse> loader, final ObaArrivalInfoResponse result) {
        if (!ListenerUtil.mutListener.listen(262)) {
            showProgress(false);
        }
        ObaArrivalInfo[] info = null;
        List<ObaSituation> situations = null;
        ObaReferences refs = null;
        if (!ListenerUtil.mutListener.listen(276)) {
            if (result.getCode() == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(270)) {
                    if (mStop == null) {
                        if (!ListenerUtil.mutListener.listen(268)) {
                            mStop = result.getStop();
                        }
                        if (!ListenerUtil.mutListener.listen(269)) {
                            DBUtil.addToDB(mStop);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(271)) {
                    info = result.getArrivalInfo();
                }
                if (!ListenerUtil.mutListener.listen(272)) {
                    situations = UIUtils.getAllSituations(result, mRoutesFilter);
                }
                if (!ListenerUtil.mutListener.listen(273)) {
                    refs = result.getRefs();
                }
                if (!ListenerUtil.mutListener.listen(274)) {
                    TravelBehaviorManager.saveArrivalInfo(info, result.getUrl(), result.getCurrentTime(), mStopId);
                }
                // Report Stop distance metric
                Location stopLocation = mStop.getLocation();
                Location myLocation = Application.getLastKnownLocation(getActivity(), null);
                if (!ListenerUtil.mutListener.listen(275)) {
                    ObaAnalytics.reportViewStopEvent(mFirebaseAnalytics, mStop.getId(), mStop.getName(), myLocation, stopLocation);
                }
            } else {
                // page load and we want to display the error in the empty text.
                ObaArrivalInfoResponse lastGood = getArrivalsLoader().getLastGoodResponse();
                if (!ListenerUtil.mutListener.listen(267)) {
                    if (lastGood != null) {
                        if (!ListenerUtil.mutListener.listen(264)) {
                            // Refresh error
                            Toast.makeText(getActivity(), R.string.generic_comm_error_toast, Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(265)) {
                            info = lastGood.getArrivalInfo();
                        }
                        if (!ListenerUtil.mutListener.listen(266)) {
                            situations = UIUtils.getAllSituations(lastGood, mRoutesFilter);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(263)) {
                            setEmptyText(UIUtils.getStopErrorString(getActivity(), result.getCode()));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(277)) {
            setResponseData(info, situations, refs);
        }
        if (!ListenerUtil.mutListener.listen(281)) {
            // The list should now be shown.
            if (isResumed()) {
                if (!ListenerUtil.mutListener.listen(280)) {
                    setListShown(true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(279)) {
                    if (isAdded()) {
                        if (!ListenerUtil.mutListener.listen(278)) {
                            setListShownNoAnimation(true);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(282)) {
            // Clear any pending refreshes
            mRefreshHandler.removeCallbacks(mRefresh);
        }
        if (!ListenerUtil.mutListener.listen(283)) {
            // Post an update
            mRefreshHandler.postDelayed(mRefresh, RefreshPeriod);
        }
        if (!ListenerUtil.mutListener.listen(290)) {
            // should show a Toast in the case where no additional arrivals were loaded
            if (mLoadedMoreArrivals) {
                if (!ListenerUtil.mutListener.listen(289)) {
                    if ((ListenerUtil.mutListener.listen(285) ? ((ListenerUtil.mutListener.listen(284) ? (info == null && info.length == 0) : (info == null || info.length == 0)) && mLastResponseLength != info.length) : ((ListenerUtil.mutListener.listen(284) ? (info == null && info.length == 0) : (info == null || info.length == 0)) || mLastResponseLength != info.length))) {
                        if (!ListenerUtil.mutListener.listen(288)) {
                            /*
                Don't show the toast, since:
                 1) an error occurred (and user has already seen the error message),
                 2) no records were returned (and empty list message is already shown), or
                 3) more arrivals were actually loaded
                */
                            mLoadedMoreArrivals = false;
                        }
                    } else if (mLastResponseLength == info.length) {
                        if (!ListenerUtil.mutListener.listen(286)) {
                            // No additional arrivals were included in the response, show a toast
                            Toast.makeText(getActivity(), UIUtils.getNoArrivalsMessage(getActivity(), getArrivalsLoader().getMinutesAfter(), true, false), Toast.LENGTH_LONG).show();
                        }
                        if (!ListenerUtil.mutListener.listen(287)) {
                            // Only show the toast once
                            mLoadedMoreArrivals = false;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(291)) {
            ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_ARRIVAL_SORT, (AppCompatActivity) getActivity(), null, false);
        }
        if (!ListenerUtil.mutListener.listen(293)) {
            // Notify listener that we have new arrival info
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(292)) {
                    mListener.onArrivalTimesUpdated(result);
                }
            }
        }
    }

    @Override
    protected void showProgress(boolean visibility) {
        if (!ListenerUtil.mutListener.listen(294)) {
            super.showProgress(visibility);
        }
        if (!ListenerUtil.mutListener.listen(296)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(295)) {
                    mHeader.showProgress(visibility);
                }
            }
        }
    }

    /**
     * Sets the header for this list to be instantiated in another layout, but still controlled by
     * this fragment
     *
     * @param header     header that will be controlled by this fragment
     * @param headerView View that contains this header
     */
    public void setHeader(ArrivalsListHeader header, View headerView) {
        if (!ListenerUtil.mutListener.listen(297)) {
            mHeader = header;
        }
        if (!ListenerUtil.mutListener.listen(298)) {
            mHeaderView = headerView;
        }
        if (!ListenerUtil.mutListener.listen(300)) {
            if (header != null) {
                if (!ListenerUtil.mutListener.listen(299)) {
                    mHeader.initView(mHeaderView);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(301)) {
            mExternalHeader = true;
        }
    }

    private void setResponseData(ObaArrivalInfo[] info, List<ObaSituation> situations, ObaReferences refs) {
        if (!ListenerUtil.mutListener.listen(302)) {
            mArrivalInfo = info;
        }
        if (!ListenerUtil.mutListener.listen(303)) {
            mObaReferences = refs;
        }
        if (!ListenerUtil.mutListener.listen(306)) {
            // Convert any stop situations into a list of alerts
            if (situations != null) {
                if (!ListenerUtil.mutListener.listen(305)) {
                    refreshSituations(situations);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(304)) {
                    refreshSituations(new ArrayList<>());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(309)) {
            if (info != null) {
                ArrivalsListLoader loader = getArrivalsLoader();
                int minutesAfter;
                if (loader != null) {
                    minutesAfter = loader.getMinutesAfter();
                } else {
                    minutesAfter = ArrivalsListLoader.DEFAULT_MINUTES_AFTER;
                }
                if (!ListenerUtil.mutListener.listen(307)) {
                    // Reset the empty text just in case there is no data.
                    setEmptyText(UIUtils.getNoArrivalsMessage(Application.get().getApplicationContext(), minutesAfter, false, false));
                }
                if (!ListenerUtil.mutListener.listen(308)) {
                    mAdapter.setData(info, mRoutesFilter, System.currentTimeMillis());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(311)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(310)) {
                    mHeader.refresh();
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<ObaArrivalInfoResponse> loader) {
        if (!ListenerUtil.mutListener.listen(312)) {
            showProgress(false);
        }
        if (!ListenerUtil.mutListener.listen(313)) {
            mAdapter.setData(null, mRoutesFilter, System.currentTimeMillis());
        }
        if (!ListenerUtil.mutListener.listen(314)) {
            mArrivalInfo = null;
        }
        if (!ListenerUtil.mutListener.listen(316)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(315)) {
                    mHeader.refresh();
                }
            }
        }
    }

    // 
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(317)) {
            inflater.inflate(R.menu.arrivals_list, menu);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(318)) {
            if (!isAdded()) {
                // not attached to an activity (possibly due to screen rotation)
                return;
            }
        }
        String title = mFavorite ? getString(R.string.stop_info_option_removestar) : getString(R.string.stop_info_option_addstar);
        if (!ListenerUtil.mutListener.listen(319)) {
            menu.findItem(R.id.toggle_favorite).setTitle(title).setTitleCondensed(title);
        }
        MenuItem menuItemHeaderArrivals = menu.findItem(R.id.show_header_arrivals);
        if (!ListenerUtil.mutListener.listen(323)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(320)) {
                    title = mHeader.isShowingArrivals() ? getString(R.string.stop_info_option_hide_header_arrivals) : getString(R.string.stop_info_option_show_header_arrivals);
                }
                if (!ListenerUtil.mutListener.listen(321)) {
                    menuItemHeaderArrivals.setTitle(title);
                }
                if (!ListenerUtil.mutListener.listen(322)) {
                    menuItemHeaderArrivals.setTitleCondensed(title);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(326)) {
            if (mExternalHeader) {
                if (!ListenerUtil.mutListener.listen(324)) {
                    // So, we can remove the "Show Map" option
                    menu.findItem(R.id.show_on_map).setVisible(false);
                }
                if (!ListenerUtil.mutListener.listen(325)) {
                    // We want to hide the "show/hide arrivals in header" setting if we are in the sliding panel
                    menuItemHeaderArrivals.setVisible(false);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(349)) {
            if (id == R.id.show_on_map) {
                if (!ListenerUtil.mutListener.listen(348)) {
                    if (mStop != null) {
                        if (!ListenerUtil.mutListener.listen(347)) {
                            HomeActivity.start(getActivity(), mStop);
                        }
                    }
                }
                return true;
            } else if (id == R.id.refresh) {
                if (!ListenerUtil.mutListener.listen(346)) {
                    refresh();
                }
                return true;
            } else if (id == R.id.sort_arrivals) {
                if (!ListenerUtil.mutListener.listen(344)) {
                    ShowcaseViewUtils.doNotShowTutorial(ShowcaseViewUtils.TUTORIAL_ARRIVAL_SORT);
                }
                if (!ListenerUtil.mutListener.listen(345)) {
                    showSortByDialog();
                }
            } else if (id == R.id.filter) {
                if (!ListenerUtil.mutListener.listen(343)) {
                    if (mStop != null) {
                        if (!ListenerUtil.mutListener.listen(342)) {
                            showRoutesFilterDialog();
                        }
                    }
                }
            } else if (id == R.id.show_header_arrivals) {
                if (!ListenerUtil.mutListener.listen(341)) {
                    doShowHideHeaderArrivals();
                }
            } else if (id == R.id.edit_name) {
                if (!ListenerUtil.mutListener.listen(340)) {
                    if (mHeader != null) {
                        if (!ListenerUtil.mutListener.listen(339)) {
                            mHeader.beginNameEdit(null);
                        }
                    }
                }
            } else if (id == R.id.toggle_favorite) {
                if (!ListenerUtil.mutListener.listen(336)) {
                    setFavoriteStop(!mFavorite);
                }
                if (!ListenerUtil.mutListener.listen(338)) {
                    if (mHeader != null) {
                        if (!ListenerUtil.mutListener.listen(337)) {
                            mHeader.refresh();
                        }
                    }
                }
            } else if (id == R.id.show_stop_details) {
                if (!ListenerUtil.mutListener.listen(335)) {
                    showStopDetailsDialog();
                }
            } else if (id == R.id.report_stop_problem) {
                if (!ListenerUtil.mutListener.listen(334)) {
                    if (mStop != null) {
                        Intent intent = makeIntent(getActivity(), mStop.getId(), mStop.getName(), mStop.getStopCode(), mStop.getLatitude(), mStop.getLongitude());
                        if (!ListenerUtil.mutListener.listen(333)) {
                            InfrastructureIssueActivity.startWithService(getActivity(), intent, getString(R.string.ri_selected_service_stop));
                        }
                    }
                }
            } else if (id == R.id.night_light) {
                if (!ListenerUtil.mutListener.listen(332)) {
                    NightLightActivity.start(getActivity());
                }
            } else if (id == R.id.hide_alerts) {
                if (!ListenerUtil.mutListener.listen(328)) {
                    if ((ListenerUtil.mutListener.listen(327) ? (mSituationAlerts == null && mSituationAlerts.isEmpty()) : (mSituationAlerts == null || mSituationAlerts.isEmpty()))) {
                        return false;
                    }
                }
                if (!ListenerUtil.mutListener.listen(330)) {
                    {
                        long _loopCounter2 = 0;
                        // Update the database to hide all currently active alerts at this stop
                        for (SituationAlert alert : mSituationAlerts) {
                            ListenerUtil.loopListener.listen("_loopCounter2", ++_loopCounter2);
                            if (!ListenerUtil.mutListener.listen(329)) {
                                ObaContract.ServiceAlerts.insertOrUpdate(alert.getId(), new ContentValues(), false, true);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(331)) {
                    refresh();
                }
            }
        }
        return false;
    }

    public static Intent makeIntent(Context context, String focusId, String stopName, String stopCode, double lat, double lon) {
        Intent myIntent = new Intent(context, HomeActivity.class);
        if (!ListenerUtil.mutListener.listen(350)) {
            myIntent.putExtra(MapParams.STOP_ID, focusId);
        }
        if (!ListenerUtil.mutListener.listen(351)) {
            myIntent.putExtra(MapParams.STOP_NAME, stopName);
        }
        if (!ListenerUtil.mutListener.listen(352)) {
            myIntent.putExtra(MapParams.STOP_CODE, stopCode);
        }
        if (!ListenerUtil.mutListener.listen(353)) {
            myIntent.putExtra(MapParams.CENTER_LAT, lat);
        }
        if (!ListenerUtil.mutListener.listen(354)) {
            myIntent.putExtra(MapParams.CENTER_LON, lon);
        }
        return myIntent;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        final ArrivalInfo stop = (ArrivalInfo) getListView().getItemAtPosition(position);
        if (!ListenerUtil.mutListener.listen(355)) {
            showListItemMenu(v, stop);
        }
    }

    public void showListItemMenu(View v, final ArrivalInfo arrivalInfo) {
        if (!ListenerUtil.mutListener.listen(356)) {
            if (arrivalInfo == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(357)) {
            Log.d(TAG, "Tapped on route=" + arrivalInfo.getInfo().getShortName() + ", tripId=" + arrivalInfo.getInfo().getTripId() + ", vehicleId=" + arrivalInfo.getInfo().getVehicleId());
        }
        ArrivalsListLoader loader = getArrivalsLoader();
        if (!ListenerUtil.mutListener.listen(358)) {
            if (loader == null) {
                return;
            }
        }
        final ObaArrivalInfoResponse response = loader.getLastGoodResponse();
        if (!ListenerUtil.mutListener.listen(359)) {
            if (response == null) {
                return;
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(360)) {
            builder.setTitle(R.string.stop_info_item_options_title);
        }
        final String routeId = arrivalInfo.getInfo().getRouteId();
        final ObaRoute route = response.getRoute(routeId);
        final String url = route != null ? route.getUrl() : null;
        final boolean hasUrl = !TextUtils.isEmpty(url);
        // (we don't have any other state, so this is good enough)
        View tripView = v.findViewById(R.id.reminder);
        boolean isReminderVisible = (ListenerUtil.mutListener.listen(361) ? (tripView != null || tripView.getVisibility() != View.GONE) : (tripView != null && tripView.getVisibility() != View.GONE));
        // Check route favorite, for whether we show "Add star" or "Remove star"
        final boolean isRouteFavorite = ObaContract.RouteHeadsignFavorites.isFavorite(routeId, arrivalInfo.getInfo().getHeadsign(), arrivalInfo.getInfo().getStopId());
        // Check to see if there is a route filter, for whether we show "Show all routes" or "Show only this route"
        boolean hasRouteFilter = !mRoutesFilter.isEmpty();
        final Occupancy occupancy;
        final OccupancyState occupancyState;
        if (arrivalInfo.getPredictedOccupancy() != null) {
            occupancy = arrivalInfo.getPredictedOccupancy();
            occupancyState = OccupancyState.PREDICTED;
        } else if (arrivalInfo.getHistoricalOccupancy() != null) {
            occupancy = arrivalInfo.getHistoricalOccupancy();
            occupancyState = OccupancyState.HISTORICAL;
        } else {
            occupancy = null;
            occupancyState = null;
        }
        List<String> items = UIUtils.buildTripOptions(getActivity(), isRouteFavorite, hasUrl, isReminderVisible, hasRouteFilter, occupancy, occupancyState);
        List<Integer> icons = UIUtils.buildTripOptionsIcons(isRouteFavorite, hasUrl, occupancy);
        ListAdapter adapter = new ArrayAdapterWithIcon(getActivity(), items, icons);
        if (!ListenerUtil.mutListener.listen(445)) {
            builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(444)) {
                        if ((ListenerUtil.mutListener.listen(366) ? (which >= 0) : (ListenerUtil.mutListener.listen(365) ? (which <= 0) : (ListenerUtil.mutListener.listen(364) ? (which > 0) : (ListenerUtil.mutListener.listen(363) ? (which < 0) : (ListenerUtil.mutListener.listen(362) ? (which != 0) : (which == 0))))))) {
                            // Show dialog for setting route favorite
                            RouteFavoriteDialogFragment routeDialog = new RouteFavoriteDialogFragment.Builder(route.getId(), arrivalInfo.getInfo().getHeadsign()).setRouteShortName(route.getShortName()).setRouteLongName(arrivalInfo.getInfo().getRouteLongName()).setRouteUrl(route.getUrl()).setStopId(arrivalInfo.getInfo().getStopId()).setFavorite(!isRouteFavorite).build();
                            if (!ListenerUtil.mutListener.listen(442)) {
                                setCallbackToDialogFragment(routeDialog);
                            }
                            if (!ListenerUtil.mutListener.listen(443)) {
                                routeDialog.show(getFragmentManager(), RouteFavoriteDialogFragment.TAG);
                            }
                        } else if ((ListenerUtil.mutListener.listen(371) ? (which >= 1) : (ListenerUtil.mutListener.listen(370) ? (which <= 1) : (ListenerUtil.mutListener.listen(369) ? (which > 1) : (ListenerUtil.mutListener.listen(368) ? (which < 1) : (ListenerUtil.mutListener.listen(367) ? (which != 1) : (which == 1))))))) {
                            if (!ListenerUtil.mutListener.listen(441)) {
                                showRouteOnMap(arrivalInfo);
                            }
                        } else if ((ListenerUtil.mutListener.listen(376) ? (which >= 2) : (ListenerUtil.mutListener.listen(375) ? (which <= 2) : (ListenerUtil.mutListener.listen(374) ? (which > 2) : (ListenerUtil.mutListener.listen(373) ? (which < 2) : (ListenerUtil.mutListener.listen(372) ? (which != 2) : (which == 2))))))) {
                            if (!ListenerUtil.mutListener.listen(440)) {
                                goToTripDetails(arrivalInfo);
                            }
                        } else if ((ListenerUtil.mutListener.listen(381) ? (which >= 3) : (ListenerUtil.mutListener.listen(380) ? (which <= 3) : (ListenerUtil.mutListener.listen(379) ? (which > 3) : (ListenerUtil.mutListener.listen(378) ? (which < 3) : (ListenerUtil.mutListener.listen(377) ? (which != 3) : (which == 3))))))) {
                            if (!ListenerUtil.mutListener.listen(439)) {
                                goToTrip(arrivalInfo);
                            }
                        } else if ((ListenerUtil.mutListener.listen(386) ? (which >= 4) : (ListenerUtil.mutListener.listen(385) ? (which <= 4) : (ListenerUtil.mutListener.listen(384) ? (which > 4) : (ListenerUtil.mutListener.listen(383) ? (which < 4) : (ListenerUtil.mutListener.listen(382) ? (which != 4) : (which == 4))))))) {
                            ArrayList<String> routes = new ArrayList<String>(1);
                            if (!ListenerUtil.mutListener.listen(427)) {
                                routes.add(arrivalInfo.getInfo().getRouteId());
                            }
                            if (!ListenerUtil.mutListener.listen(435)) {
                                // toggle route filter - if user selection equals existing route filter, clear it
                                if ((ListenerUtil.mutListener.listen(433) ? (routes.equals(mRoutesFilter) && (ListenerUtil.mutListener.listen(432) ? (mRoutesFilter.size() >= routes.size()) : (ListenerUtil.mutListener.listen(431) ? (mRoutesFilter.size() <= routes.size()) : (ListenerUtil.mutListener.listen(430) ? (mRoutesFilter.size() < routes.size()) : (ListenerUtil.mutListener.listen(429) ? (mRoutesFilter.size() != routes.size()) : (ListenerUtil.mutListener.listen(428) ? (mRoutesFilter.size() == routes.size()) : (mRoutesFilter.size() > routes.size()))))))) : (routes.equals(mRoutesFilter) || (ListenerUtil.mutListener.listen(432) ? (mRoutesFilter.size() >= routes.size()) : (ListenerUtil.mutListener.listen(431) ? (mRoutesFilter.size() <= routes.size()) : (ListenerUtil.mutListener.listen(430) ? (mRoutesFilter.size() < routes.size()) : (ListenerUtil.mutListener.listen(429) ? (mRoutesFilter.size() != routes.size()) : (ListenerUtil.mutListener.listen(428) ? (mRoutesFilter.size() == routes.size()) : (mRoutesFilter.size() > routes.size()))))))))) {
                                    if (!ListenerUtil.mutListener.listen(434)) {
                                        routes.clear();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(436)) {
                                setRoutesFilter(routes);
                            }
                            if (!ListenerUtil.mutListener.listen(438)) {
                                if (mHeader != null) {
                                    if (!ListenerUtil.mutListener.listen(437)) {
                                        mHeader.refresh();
                                    }
                                }
                            }
                        } else if ((ListenerUtil.mutListener.listen(392) ? (hasUrl || (ListenerUtil.mutListener.listen(391) ? (which >= 5) : (ListenerUtil.mutListener.listen(390) ? (which <= 5) : (ListenerUtil.mutListener.listen(389) ? (which > 5) : (ListenerUtil.mutListener.listen(388) ? (which < 5) : (ListenerUtil.mutListener.listen(387) ? (which != 5) : (which == 5))))))) : (hasUrl && (ListenerUtil.mutListener.listen(391) ? (which >= 5) : (ListenerUtil.mutListener.listen(390) ? (which <= 5) : (ListenerUtil.mutListener.listen(389) ? (which > 5) : (ListenerUtil.mutListener.listen(388) ? (which < 5) : (ListenerUtil.mutListener.listen(387) ? (which != 5) : (which == 5))))))))) {
                            if (!ListenerUtil.mutListener.listen(426)) {
                                UIUtils.goToUrl(getActivity(), url);
                            }
                        } else if ((ListenerUtil.mutListener.listen(405) ? (((ListenerUtil.mutListener.listen(398) ? (!hasUrl || (ListenerUtil.mutListener.listen(397) ? (which >= 5) : (ListenerUtil.mutListener.listen(396) ? (which <= 5) : (ListenerUtil.mutListener.listen(395) ? (which > 5) : (ListenerUtil.mutListener.listen(394) ? (which < 5) : (ListenerUtil.mutListener.listen(393) ? (which != 5) : (which == 5))))))) : (!hasUrl && (ListenerUtil.mutListener.listen(397) ? (which >= 5) : (ListenerUtil.mutListener.listen(396) ? (which <= 5) : (ListenerUtil.mutListener.listen(395) ? (which > 5) : (ListenerUtil.mutListener.listen(394) ? (which < 5) : (ListenerUtil.mutListener.listen(393) ? (which != 5) : (which == 5))))))))) && ((ListenerUtil.mutListener.listen(404) ? (hasUrl || (ListenerUtil.mutListener.listen(403) ? (which >= 6) : (ListenerUtil.mutListener.listen(402) ? (which <= 6) : (ListenerUtil.mutListener.listen(401) ? (which > 6) : (ListenerUtil.mutListener.listen(400) ? (which < 6) : (ListenerUtil.mutListener.listen(399) ? (which != 6) : (which == 6))))))) : (hasUrl && (ListenerUtil.mutListener.listen(403) ? (which >= 6) : (ListenerUtil.mutListener.listen(402) ? (which <= 6) : (ListenerUtil.mutListener.listen(401) ? (which > 6) : (ListenerUtil.mutListener.listen(400) ? (which < 6) : (ListenerUtil.mutListener.listen(399) ? (which != 6) : (which == 6)))))))))) : (((ListenerUtil.mutListener.listen(398) ? (!hasUrl || (ListenerUtil.mutListener.listen(397) ? (which >= 5) : (ListenerUtil.mutListener.listen(396) ? (which <= 5) : (ListenerUtil.mutListener.listen(395) ? (which > 5) : (ListenerUtil.mutListener.listen(394) ? (which < 5) : (ListenerUtil.mutListener.listen(393) ? (which != 5) : (which == 5))))))) : (!hasUrl && (ListenerUtil.mutListener.listen(397) ? (which >= 5) : (ListenerUtil.mutListener.listen(396) ? (which <= 5) : (ListenerUtil.mutListener.listen(395) ? (which > 5) : (ListenerUtil.mutListener.listen(394) ? (which < 5) : (ListenerUtil.mutListener.listen(393) ? (which != 5) : (which == 5))))))))) || ((ListenerUtil.mutListener.listen(404) ? (hasUrl || (ListenerUtil.mutListener.listen(403) ? (which >= 6) : (ListenerUtil.mutListener.listen(402) ? (which <= 6) : (ListenerUtil.mutListener.listen(401) ? (which > 6) : (ListenerUtil.mutListener.listen(400) ? (which < 6) : (ListenerUtil.mutListener.listen(399) ? (which != 6) : (which == 6))))))) : (hasUrl && (ListenerUtil.mutListener.listen(403) ? (which >= 6) : (ListenerUtil.mutListener.listen(402) ? (which <= 6) : (ListenerUtil.mutListener.listen(401) ? (which > 6) : (ListenerUtil.mutListener.listen(400) ? (which < 6) : (ListenerUtil.mutListener.listen(399) ? (which != 6) : (which == 6)))))))))))) {
                            // Find agency name
                            String routeId = arrivalInfo.getInfo().getRouteId();
                            String agencyName = null;
                            String blockId = null;
                            if (!ListenerUtil.mutListener.listen(424)) {
                                if (mObaReferences != null) {
                                    String agencyId = mObaReferences.getRoute(routeId).getAgencyId();
                                    if (!ListenerUtil.mutListener.listen(422)) {
                                        agencyName = mObaReferences.getAgency(agencyId).getName();
                                    }
                                    ObaTrip trip = mObaReferences.getTrip(arrivalInfo.getInfo().getTripId());
                                    if (!ListenerUtil.mutListener.listen(423)) {
                                        blockId = trip.getBlockId();
                                    }
                                }
                            }
                            Intent intent = makeIntent(getActivity(), mStop.getId(), mStop.getName(), mStop.getStopCode(), mStop.getLatitude(), mStop.getLongitude());
                            if (!ListenerUtil.mutListener.listen(425)) {
                                InfrastructureIssueActivity.startWithService(getActivity(), intent, getString(R.string.ri_selected_service_trip), arrivalInfo.getInfo(), agencyName, blockId);
                            }
                        } else if ((ListenerUtil.mutListener.listen(419) ? (occupancy != null || ((ListenerUtil.mutListener.listen(418) ? (((ListenerUtil.mutListener.listen(411) ? (!hasUrl || (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))) : (!hasUrl && (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))))) && ((ListenerUtil.mutListener.listen(417) ? (hasUrl || (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7))))))) : (hasUrl && (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7)))))))))) : (((ListenerUtil.mutListener.listen(411) ? (!hasUrl || (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))) : (!hasUrl && (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))))) || ((ListenerUtil.mutListener.listen(417) ? (hasUrl || (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7))))))) : (hasUrl && (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7))))))))))))) : (occupancy != null && ((ListenerUtil.mutListener.listen(418) ? (((ListenerUtil.mutListener.listen(411) ? (!hasUrl || (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))) : (!hasUrl && (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))))) && ((ListenerUtil.mutListener.listen(417) ? (hasUrl || (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7))))))) : (hasUrl && (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7)))))))))) : (((ListenerUtil.mutListener.listen(411) ? (!hasUrl || (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))) : (!hasUrl && (ListenerUtil.mutListener.listen(410) ? (which >= 6) : (ListenerUtil.mutListener.listen(409) ? (which <= 6) : (ListenerUtil.mutListener.listen(408) ? (which > 6) : (ListenerUtil.mutListener.listen(407) ? (which < 6) : (ListenerUtil.mutListener.listen(406) ? (which != 6) : (which == 6))))))))) || ((ListenerUtil.mutListener.listen(417) ? (hasUrl || (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7))))))) : (hasUrl && (ListenerUtil.mutListener.listen(416) ? (which >= 7) : (ListenerUtil.mutListener.listen(415) ? (which <= 7) : (ListenerUtil.mutListener.listen(414) ? (which > 7) : (ListenerUtil.mutListener.listen(413) ? (which < 7) : (ListenerUtil.mutListener.listen(412) ? (which != 7) : (which == 7))))))))))))))) {
                            if (!ListenerUtil.mutListener.listen(420)) {
                                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getActivity().getString(R.string.analytics_label_button_press_about_occupancy), null);
                            }
                            if (!ListenerUtil.mutListener.listen(421)) {
                                createOccupancyDialog().show();
                            }
                        }
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(446)) {
            dialog.setOwnerActivity(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(447)) {
            dialog.show();
        }
    }

    private void setCallbackToDialogFragment(RouteFavoriteDialogFragment routeDialog) {
        if (!ListenerUtil.mutListener.listen(450)) {
            routeDialog.setCallback(new RouteFavoriteDialogFragment.Callback() {

                @Override
                public void onSelectionComplete(boolean savedFavorite) {
                    if (!ListenerUtil.mutListener.listen(449)) {
                        if (savedFavorite) {
                            if (!ListenerUtil.mutListener.listen(448)) {
                                refreshLocal();
                            }
                        }
                    }
                }
            });
        }
    }

    public void showRouteOnMap(ArrivalInfo arrivalInfo) {
        boolean handled = false;
        if (!ListenerUtil.mutListener.listen(452)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(451)) {
                    handled = mListener.onShowRouteOnMapSelected(arrivalInfo);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(454)) {
            // If the event hasn't been handled by the listener, start a new activity
            if (!handled) {
                if (!ListenerUtil.mutListener.listen(453)) {
                    HomeActivity.start(getActivity(), arrivalInfo.getInfo().getRouteId());
                }
            }
        }
    }

    // 
    @Override
    public String getStopId() {
        return mStopId;
    }

    @Override
    public Location getStopLocation() {
        Location location = null;
        if (!ListenerUtil.mutListener.listen(469)) {
            if (mStop != null) {
                if (!ListenerUtil.mutListener.listen(468)) {
                    location = mStop.getLocation();
                }
            } else {
                // Check the arguments
                Bundle args = getArguments();
                double latitude = args.getDouble(STOP_LAT);
                double longitude = args.getDouble(STOP_LON);
                if (!ListenerUtil.mutListener.listen(467)) {
                    if ((ListenerUtil.mutListener.listen(465) ? ((ListenerUtil.mutListener.listen(459) ? (latitude >= 0) : (ListenerUtil.mutListener.listen(458) ? (latitude <= 0) : (ListenerUtil.mutListener.listen(457) ? (latitude > 0) : (ListenerUtil.mutListener.listen(456) ? (latitude < 0) : (ListenerUtil.mutListener.listen(455) ? (latitude == 0) : (latitude != 0)))))) || (ListenerUtil.mutListener.listen(464) ? (longitude >= 0) : (ListenerUtil.mutListener.listen(463) ? (longitude <= 0) : (ListenerUtil.mutListener.listen(462) ? (longitude > 0) : (ListenerUtil.mutListener.listen(461) ? (longitude < 0) : (ListenerUtil.mutListener.listen(460) ? (longitude == 0) : (longitude != 0))))))) : ((ListenerUtil.mutListener.listen(459) ? (latitude >= 0) : (ListenerUtil.mutListener.listen(458) ? (latitude <= 0) : (ListenerUtil.mutListener.listen(457) ? (latitude > 0) : (ListenerUtil.mutListener.listen(456) ? (latitude < 0) : (ListenerUtil.mutListener.listen(455) ? (latitude == 0) : (latitude != 0)))))) && (ListenerUtil.mutListener.listen(464) ? (longitude >= 0) : (ListenerUtil.mutListener.listen(463) ? (longitude <= 0) : (ListenerUtil.mutListener.listen(462) ? (longitude > 0) : (ListenerUtil.mutListener.listen(461) ? (longitude < 0) : (ListenerUtil.mutListener.listen(460) ? (longitude == 0) : (longitude != 0))))))))) {
                        if (!ListenerUtil.mutListener.listen(466)) {
                            location = LocationUtils.makeLocation(latitude, longitude);
                        }
                    }
                }
            }
        }
        return location;
    }

    @Override
    public String getStopName() {
        String name;
        if (mStop != null) {
            name = mStop.getName();
        } else {
            // Check the arguments
            Bundle args = getArguments();
            name = args.getString(STOP_NAME);
        }
        return UIUtils.formatDisplayText(name);
    }

    @Override
    public String getStopDirection() {
        if (mStop != null) {
            return mStop.getDirection();
        } else {
            // Check the arguments
            Bundle args = getArguments();
            return args.getString(STOP_DIRECTION);
        }
    }

    /**
     * Returns a sorted list (by ETA) of arrival times for the current stop
     *
     * @return a sorted list (by ETA) of arrival times for the current stop
     */
    @Override
    public ArrayList<ArrivalInfo> getArrivalInfo() {
        ArrayList<ArrivalInfo> list = null;
        if (!ListenerUtil.mutListener.listen(471)) {
            if (mArrivalInfo != null) {
                if (!ListenerUtil.mutListener.listen(470)) {
                    list = ArrivalInfoUtils.convertObaArrivalInfo(getActivity(), mArrivalInfo, mRoutesFilter, System.currentTimeMillis(), true);
                }
            }
        }
        return list;
    }

    /**
     * Returns the range of arrival times (i.e., for the next "minutesAfter" minutes), or -1 if
     * this information isn't available
     *
     * @return the range of arrival times (i.e., for the next "minutesAfter" minutes), or -1 if
     * this information isn't available
     */
    @Override
    public int getMinutesAfter() {
        ArrivalsListLoader loader = getArrivalsLoader();
        if (loader != null) {
            return loader.getMinutesAfter();
        } else {
            return -1;
        }
    }

    @Override
    public String getUserStopName() {
        return mStopUserName;
    }

    @Override
    public void setUserStopName(String name) {
        ContentResolver cr = getActivity().getContentResolver();
        ContentValues values = new ContentValues();
        if (!ListenerUtil.mutListener.listen(476)) {
            if (TextUtils.isEmpty(name)) {
                if (!ListenerUtil.mutListener.listen(474)) {
                    values.putNull(ObaContract.Stops.USER_NAME);
                }
                if (!ListenerUtil.mutListener.listen(475)) {
                    mStopUserName = null;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(472)) {
                    values.put(ObaContract.Stops.USER_NAME, name);
                }
                if (!ListenerUtil.mutListener.listen(473)) {
                    mStopUserName = name;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(477)) {
            cr.update(mStopUri, values, null, null);
        }
    }

    @Override
    public ArrayList<String> getRoutesFilter() {
        // a route filter at all.
        if (mStop != null) {
            return mRoutesFilter;
        } else {
            return null;
        }
    }

    @Override
    public void setRoutesFilter(ArrayList<String> routes) {
        if (!ListenerUtil.mutListener.listen(478)) {
            mRoutesFilter = routes;
        }
        if (!ListenerUtil.mutListener.listen(479)) {
            ObaContract.StopRouteFilters.set(getActivity(), mStopId, mRoutesFilter);
        }
        if (!ListenerUtil.mutListener.listen(480)) {
            refreshSituations(UIUtils.getAllSituations(getArrivalsLoader().getLastGoodResponse(), mRoutesFilter));
        }
        if (!ListenerUtil.mutListener.listen(481)) {
            refreshLocal();
        }
    }

    @Override
    public long getLastGoodResponseTime() {
        ArrivalsListLoader loader = getArrivalsLoader();
        if (!ListenerUtil.mutListener.listen(482)) {
            if (loader == null) {
                return 0;
            }
        }
        return loader.getLastGoodResponseTime();
    }

    @Override
    public List<String> getRouteDisplayNames() {
        if (!ListenerUtil.mutListener.listen(487)) {
            if ((ListenerUtil.mutListener.listen(483) ? (mStop != null || getArrivalsLoader() != null) : (mStop != null && getArrivalsLoader() != null))) {
                ObaArrivalInfoResponse response = getArrivalsLoader().getLastGoodResponse();
                List<ObaRoute> routes = response.getRoutes(mStop.getRouteIds());
                ArrayList<String> displayNames = new ArrayList<String>();
                if (!ListenerUtil.mutListener.listen(486)) {
                    {
                        long _loopCounter3 = 0;
                        for (ObaRoute r : routes) {
                            ListenerUtil.loopListener.listen("_loopCounter3", ++_loopCounter3);
                            if (!ListenerUtil.mutListener.listen(485)) {
                                displayNames.add(UIUtils.getRouteDisplayName(r));
                            }
                        }
                    }
                }
                return displayNames;
            } else {
                // Check the arguments
                Bundle args = getArguments();
                String serializedRoutes = args.getString(STOP_ROUTES);
                if (!ListenerUtil.mutListener.listen(484)) {
                    if (serializedRoutes != null) {
                        return UIUtils.deserializeRouteDisplayNames(serializedRoutes);
                    }
                }
            }
        }
        // If we've gotten this far, we don't have any routeIds to share
        return null;
    }

    @Override
    public int getNumRoutes() {
        if (mStop != null) {
            return mStop.getRouteIds().length;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isFavoriteStop() {
        return mFavorite;
    }

    @Override
    public boolean setFavoriteStop(boolean favorite) {
        if (!ListenerUtil.mutListener.listen(489)) {
            if (ObaContract.Stops.markAsFavorite(getActivity(), mStopUri, favorite)) {
                if (!ListenerUtil.mutListener.listen(488)) {
                    mFavorite = favorite;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(490)) {
            // menus like we did before...
            getActivity().supportInvalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(491)) {
            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_edit_field_bookmark), null);
        }
        return mFavorite;
    }

    @Override
    public AlertList getAlertList() {
        return mAlertList;
    }

    /**
     * Checks to see if the user has changed the arrival info style preference after this Fragment
     * was initialized
     */
    private void checkAdapterStylePreference() {
        int currentArrivalInfoStyle = BuildFlavorUtils.getArrivalInfoStyleFromPreferences();
        if (!ListenerUtil.mutListener.listen(496)) {
            if ((ListenerUtil.mutListener.listen(492) ? (currentArrivalInfoStyle == BuildFlavorUtils.ARRIVAL_INFO_STYLE_A || !(mAdapter instanceof ArrivalsListAdapterStyleA)) : (currentArrivalInfoStyle == BuildFlavorUtils.ARRIVAL_INFO_STYLE_A && !(mAdapter instanceof ArrivalsListAdapterStyleA)))) {
                if (!ListenerUtil.mutListener.listen(495)) {
                    // Change to Style A adapter
                    reinitAdapterStyleOnPreferenceChange(BuildFlavorUtils.ARRIVAL_INFO_STYLE_A);
                }
            } else if ((ListenerUtil.mutListener.listen(493) ? (currentArrivalInfoStyle == BuildFlavorUtils.ARRIVAL_INFO_STYLE_B || !(mAdapter instanceof ArrivalsListAdapterStyleB)) : (currentArrivalInfoStyle == BuildFlavorUtils.ARRIVAL_INFO_STYLE_B && !(mAdapter instanceof ArrivalsListAdapterStyleB)))) {
                if (!ListenerUtil.mutListener.listen(494)) {
                    // Change to Style B adapter
                    reinitAdapterStyleOnPreferenceChange(BuildFlavorUtils.ARRIVAL_INFO_STYLE_B);
                }
            }
        }
    }

    /**
     * Reinitializes the adapter style after there has been a preference changed
     *
     * @param arrivalInfoStyle the adapter style to change to - should be one of the
     *                         BuildFlavorUtil.ARRIVAL_INFO_STYLE_* contants
     */
    private void reinitAdapterStyleOnPreferenceChange(int arrivalInfoStyle) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        if (!ListenerUtil.mutListener.listen(498)) {
            // Remove any existing footer view
            if (mFooter != null) {
                if (!ListenerUtil.mutListener.listen(497)) {
                    getListView().removeFooterView(mFooter);
                }
            }
        }
        CharSequence emptyText = null;
        if (!ListenerUtil.mutListener.listen(502)) {
            // Remove any existing empty list view
            if (mEmptyList != null) {
                TextView noArrivals = (TextView) mEmptyList.findViewById(R.id.noArrivals);
                if (!ListenerUtil.mutListener.listen(500)) {
                    if (noArrivals != null) {
                        if (!ListenerUtil.mutListener.listen(499)) {
                            emptyText = noArrivals.getText();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(501)) {
                    ((ViewGroup) getListView().getParent()).removeView(mEmptyList);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(503)) {
            initArrivalInfoViews(inflater);
        }
        if (!ListenerUtil.mutListener.listen(504)) {
            setupFooter();
        }
        if (!ListenerUtil.mutListener.listen(505)) {
            setupEmptyList(emptyText);
        }
        if (!ListenerUtil.mutListener.listen(506)) {
            setListViewProperties(arrivalInfoStyle);
        }
        if (!ListenerUtil.mutListener.listen(507)) {
            instantiateAdapter(arrivalInfoStyle);
        }
    }

    /**
     * Initializes the adapter views
     *
     * @param inflater         inflater to use
     */
    private void initArrivalInfoViews(LayoutInflater inflater) {
        if (!ListenerUtil.mutListener.listen(508)) {
            // Use a card-styled footer
            mFooter = inflater.inflate(R.layout.arrivals_list_footer_style, null);
        }
        if (!ListenerUtil.mutListener.listen(509)) {
            mEmptyList = inflater.inflate(R.layout.arrivals_list_empty_style, null);
        }
    }

    /**
     * Sets up the footer with the load more arrivals button
     */
    private void setupFooter() {
        // Setup list footer button to load more arrivals (when arrivals are shown)
        Button loadMoreArrivals = (Button) mFooter.findViewById(R.id.load_more_arrivals);
        if (!ListenerUtil.mutListener.listen(511)) {
            loadMoreArrivals.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(510)) {
                        loadMoreArrivals();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(512)) {
            getListView().addFooterView(mFooter);
        }
        if (!ListenerUtil.mutListener.listen(513)) {
            mFooter.requestLayout();
        }
    }

    /**
     * Sets up the load more arrivals button in the empty list view
     *
     * @param currentText the text that should populate the empty list entry, or null if no text
     *                    should be set at this point
     */
    private void setupEmptyList(CharSequence currentText) {
        Button loadMoreArrivalsEmptyList = (Button) mEmptyList.findViewById(R.id.load_more_arrivals);
        if (!ListenerUtil.mutListener.listen(515)) {
            loadMoreArrivalsEmptyList.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(514)) {
                        loadMoreArrivals();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(516)) {
            // Set and add the view that is shown if no arrival information is returned by the REST API
            getListView().setEmptyView(mEmptyList);
        }
        if (!ListenerUtil.mutListener.listen(517)) {
            ((ViewGroup) getListView().getParent()).addView(mEmptyList);
        }
        if (!ListenerUtil.mutListener.listen(519)) {
            if (currentText != null) {
                if (!ListenerUtil.mutListener.listen(518)) {
                    setEmptyText(currentText);
                }
            }
        }
    }

    /**
     * Initializes the list view properties
     *
     * @param arrivalInfoStyle the adapter style to use - should be one of the
     *                         BuildFlavorUtil.ARRIVAL_INFO_STYLE_* contants
     */
    private void setListViewProperties(int arrivalInfoStyle) {
        ListView.MarginLayoutParams listParam = (ListView.MarginLayoutParams) getListView().getLayoutParams();
        if (!ListenerUtil.mutListener.listen(520)) {
            // Set margins for the CardViews
            listParam.bottomMargin = UIUtils.dpToPixels(getActivity(), 2);
        }
        if (!ListenerUtil.mutListener.listen(521)) {
            listParam.topMargin = UIUtils.dpToPixels(getActivity(), 3);
        }
        if (!ListenerUtil.mutListener.listen(522)) {
            listParam.leftMargin = UIUtils.dpToPixels(getActivity(), 5);
        }
        if (!ListenerUtil.mutListener.listen(523)) {
            listParam.rightMargin = UIUtils.dpToPixels(getActivity(), 5);
        }
        if (!ListenerUtil.mutListener.listen(524)) {
            // Set the listview background to give the cards more contrast
            getListView().getRootView().setBackgroundColor(getResources().getColor(R.color.stop_info_arrival_list_background));
        }
        if (!ListenerUtil.mutListener.listen(525)) {
            // Update the layout parameters
            getListView().setLayoutParams(listParam);
        }
    }

    /**
     * Instantiates the adapter based on the style to be used
     *
     * @param arrivalInfoStyle the adapter style to use - should be one of the
     *                         BuildFlavorUtil.ARRIVAL_INFO_STYLE_* contants
     */
    private void instantiateAdapter(int arrivalInfoStyle) {
        if (!ListenerUtil.mutListener.listen(531)) {
            if (UIUtils.canSupportArrivalInfoStyleB()) {
                if (!ListenerUtil.mutListener.listen(530)) {
                    switch(arrivalInfoStyle) {
                        case BuildFlavorUtils.ARRIVAL_INFO_STYLE_A:
                            if (!ListenerUtil.mutListener.listen(527)) {
                                mAdapter = new ArrivalsListAdapterStyleA(getActivity());
                            }
                            break;
                        case BuildFlavorUtils.ARRIVAL_INFO_STYLE_B:
                            if (!ListenerUtil.mutListener.listen(528)) {
                                mAdapter = new ArrivalsListAdapterStyleB(getActivity());
                            }
                            if (!ListenerUtil.mutListener.listen(529)) {
                                ((ArrivalsListAdapterStyleB) mAdapter).setFragment(this);
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(526)) {
                    // Always use Style A on Gingerbread
                    mAdapter = new ArrivalsListAdapterStyleA(getActivity());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(532)) {
            // We present arrivals as cards, so hide the divider in the listview
            getListView().setDivider(null);
        }
        if (!ListenerUtil.mutListener.listen(533)) {
            setListAdapter(mAdapter);
        }
    }

    private void showSortByDialog() {
        if (!ListenerUtil.mutListener.listen(535)) {
            // Do callback when user taps on button, so they can see result of dialog selection
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(534)) {
                    mListener.onSortBySelected();
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(536)) {
            builder.setTitle(R.string.menu_option_sort_by);
        }
        int currentArrivalInfoStyle = BuildFlavorUtils.getArrivalInfoStyleFromPreferences();
        if (!ListenerUtil.mutListener.listen(557)) {
            builder.setSingleChoiceItems(R.array.sort_arrivals, currentArrivalInfoStyle, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int index) {
                    if (!ListenerUtil.mutListener.listen(551)) {
                        if ((ListenerUtil.mutListener.listen(541) ? (index >= 0) : (ListenerUtil.mutListener.listen(540) ? (index <= 0) : (ListenerUtil.mutListener.listen(539) ? (index > 0) : (ListenerUtil.mutListener.listen(538) ? (index < 0) : (ListenerUtil.mutListener.listen(537) ? (index != 0) : (index == 0))))))) {
                            if (!ListenerUtil.mutListener.listen(549)) {
                                // Sort by eta
                                Log.d(TAG, "Sort by ETA");
                            }
                            if (!ListenerUtil.mutListener.listen(550)) {
                                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_eta_arrival), null);
                            }
                        } else if ((ListenerUtil.mutListener.listen(546) ? (index >= 1) : (ListenerUtil.mutListener.listen(545) ? (index <= 1) : (ListenerUtil.mutListener.listen(544) ? (index > 1) : (ListenerUtil.mutListener.listen(543) ? (index < 1) : (ListenerUtil.mutListener.listen(542) ? (index != 1) : (index == 1))))))) {
                            if (!ListenerUtil.mutListener.listen(547)) {
                                // Sort by route
                                Log.d(TAG, "Sort by route");
                            }
                            if (!ListenerUtil.mutListener.listen(548)) {
                                ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_route_arrival), null);
                            }
                        }
                    }
                    String[] styles = getResources().getStringArray(R.array.arrival_info_style_options);
                    if (!ListenerUtil.mutListener.listen(552)) {
                        PreferenceUtils.saveString(getResources().getString(R.string.preference_key_arrival_info_style), styles[index]);
                    }
                    if (!ListenerUtil.mutListener.listen(553)) {
                        checkAdapterStylePreference();
                    }
                    if (!ListenerUtil.mutListener.listen(554)) {
                        refreshLocal();
                    }
                    if (!ListenerUtil.mutListener.listen(555)) {
                        getLoaderManager().restartLoader(TRIPS_FOR_STOP_LOADER, null, mTripsForStopCallback);
                    }
                    if (!ListenerUtil.mutListener.listen(556)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(558)) {
            dialog.setOwnerActivity(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(559)) {
            dialog.show();
        }
    }

    /**
     * Toggle the visibility of arrivals in the header
     */
    private void doShowHideHeaderArrivals() {
        if (!ListenerUtil.mutListener.listen(560)) {
            if (mHeader == null) {
                return;
            }
        }
        boolean showArrivals = Application.getPrefs().getBoolean(getString(R.string.preference_key_show_header_arrivals), false);
        if (!ListenerUtil.mutListener.listen(565)) {
            if (showArrivals) {
                if (!ListenerUtil.mutListener.listen(563)) {
                    // Currently we're showing arrivals in header - we need to remove them
                    mHeader.showArrivals(false);
                }
                if (!ListenerUtil.mutListener.listen(564)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_hide_arrivals_in_header), null);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(561)) {
                    // Currently we're hiding arrivals - we need to show them
                    mHeader.showArrivals(true);
                }
                if (!ListenerUtil.mutListener.listen(562)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_show_arrivals_in_header), null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(566)) {
            PreferenceUtils.saveBoolean(getResources().getString(R.string.preference_key_show_header_arrivals), !showArrivals);
        }
        if (!ListenerUtil.mutListener.listen(567)) {
            mHeader.refresh();
        }
    }

    private void showRoutesFilterDialog() {
        ObaArrivalInfoResponse response = getArrivalsLoader().getLastGoodResponse();
        final List<ObaRoute> routes = response.getRoutes(mStop.getRouteIds());
        final int len = routes.size();
        final ArrayList<String> filter = mRoutesFilter;
        // mRouteIds = new ArrayList<String>(len);
        String[] items = new String[len];
        boolean[] checks = new boolean[len];
        if (!ListenerUtil.mutListener.listen(576)) {
            {
                long _loopCounter4 = 0;
                // For each stop, if it is in the enabled list, mark it as checked.
                for (int i = 0; (ListenerUtil.mutListener.listen(575) ? (i >= len) : (ListenerUtil.mutListener.listen(574) ? (i <= len) : (ListenerUtil.mutListener.listen(573) ? (i > len) : (ListenerUtil.mutListener.listen(572) ? (i != len) : (ListenerUtil.mutListener.listen(571) ? (i == len) : (i < len)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter4", ++_loopCounter4);
                    final ObaRoute route = routes.get(i);
                    if (!ListenerUtil.mutListener.listen(568)) {
                        // mRouteIds.add(i, id);
                        items[i] = UIUtils.getRouteDisplayName(route);
                    }
                    if (!ListenerUtil.mutListener.listen(570)) {
                        if (filter.contains(route.getId())) {
                            if (!ListenerUtil.mutListener.listen(569)) {
                                checks[i] = true;
                            }
                        }
                    }
                }
            }
        }
        // Arguments
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(577)) {
            args.putStringArray(RoutesFilterDialog.ITEMS, items);
        }
        if (!ListenerUtil.mutListener.listen(578)) {
            args.putBooleanArray(RoutesFilterDialog.CHECKS, checks);
        }
        RoutesFilterDialog frag = new RoutesFilterDialog();
        if (!ListenerUtil.mutListener.listen(579)) {
            frag.setArguments(args);
        }
        if (!ListenerUtil.mutListener.listen(580)) {
            frag.show(getActivity().getSupportFragmentManager(), ".RoutesFilterDialog");
        }
    }

    private void showStopDetailsDialog() {
        // Create dialog contents
        String stopCode = null;
        if (!ListenerUtil.mutListener.listen(582)) {
            if (mStop != null) {
                if (!ListenerUtil.mutListener.listen(581)) {
                    stopCode = mStop.getStopCode();
                }
            }
        }
        Pair stopDetails = UIUtils.createStopDetailsDialogText(getContext(), getStopName(), getUserStopName(), stopCode, getStopDirection(), getRouteDisplayNames());
        if (!ListenerUtil.mutListener.listen(583)) {
            UIUtils.buildAlertDialog(getContext(), (String) stopDetails.first, (String) stopDetails.second).show();
        }
        if (!ListenerUtil.mutListener.listen(584)) {
            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getActivity().getString(R.string.analytics_label_button_press_stop_details), null);
        }
    }

    /**
     * Sets the listener
     *
     * @param listener the listener
     */
    public void setListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(585)) {
            this.mListener = listener;
        }
    }

    private void setupHeader(Bundle bundle) {
        if (!ListenerUtil.mutListener.listen(587)) {
            if (bundle != null) {
                if (!ListenerUtil.mutListener.listen(586)) {
                    mExternalHeader = bundle.getBoolean(EXTERNAL_HEADER);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(596)) {
            if ((ListenerUtil.mutListener.listen(588) ? (mHeader == null || !mExternalHeader) : (mHeader == null && !mExternalHeader))) {
                if (!ListenerUtil.mutListener.listen(590)) {
                    // by the Activity via setHeader()
                    mHeader = new ArrivalsListHeader(getActivity(), this, getFragmentManager());
                }
                if (!ListenerUtil.mutListener.listen(591)) {
                    mHeaderView = getView().findViewById(R.id.arrivals_list_header);
                }
                if (!ListenerUtil.mutListener.listen(592)) {
                    mHeader.initView(mHeaderView);
                }
                if (!ListenerUtil.mutListener.listen(593)) {
                    mHeader.showExpandCollapseIndicator(false);
                }
                if (!ListenerUtil.mutListener.listen(594)) {
                    // Header is not in a sliding panel, so set collapsed state to false
                    mHeader.setSlidingPanelCollapsed(false);
                }
                // Show or hide the header arrivals based on user preference
                boolean showArrivals = Application.getPrefs().getBoolean(getString(R.string.preference_key_show_header_arrivals), false);
                if (!ListenerUtil.mutListener.listen(595)) {
                    mHeader.showArrivals(showArrivals);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(589)) {
                    // The header is in another layout (e.g., sliding panel), so we need to remove the header in this layout
                    getView().findViewById(R.id.arrivals_list_header).setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(598)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(597)) {
                    mHeader.refresh();
                }
            }
        }
    }

    public static class RoutesFilterDialog extends DialogFragment implements OnMultiChoiceClickListener, DialogInterface.OnClickListener {

        static final String ITEMS = ".items";

        static final String CHECKS = ".checks";

        private boolean[] mChecks;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Bundle args = getArguments();
            String[] items = args.getStringArray(ITEMS);
            if (!ListenerUtil.mutListener.listen(599)) {
                mChecks = args.getBooleanArray(CHECKS);
            }
            if (!ListenerUtil.mutListener.listen(601)) {
                if (savedInstanceState != null) {
                    if (!ListenerUtil.mutListener.listen(600)) {
                        mChecks = args.getBooleanArray(CHECKS);
                    }
                }
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            return builder.setTitle(R.string.stop_info_filter_title).setMultiChoiceItems(items, mChecks, this).setPositiveButton(R.string.stop_info_save, this).setNegativeButton(R.string.stop_info_cancel, null).create();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            if (!ListenerUtil.mutListener.listen(602)) {
                outState.putBooleanArray(CHECKS, mChecks);
            }
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Activity act = getActivity();
            ArrivalsListFragment frag = null;
            if (!ListenerUtil.mutListener.listen(605)) {
                // Get the fragment we want...
                if (act instanceof ArrivalsListActivity) {
                    if (!ListenerUtil.mutListener.listen(604)) {
                        frag = ((ArrivalsListActivity) act).getArrivalsListFragment();
                    }
                } else if (act instanceof HomeActivity) {
                    if (!ListenerUtil.mutListener.listen(603)) {
                        frag = ((HomeActivity) act).getArrivalsListFragment();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(606)) {
                frag.setRoutesFilter(mChecks);
            }
            if (!ListenerUtil.mutListener.listen(607)) {
                dialog.dismiss();
            }
        }

        @Override
        public void onClick(DialogInterface arg0, int which, boolean isChecked) {
            if (!ListenerUtil.mutListener.listen(608)) {
                mChecks[which] = isChecked;
            }
        }
    }

    private void setRoutesFilter(boolean[] checks) {
        final int len = checks.length;
        final ArrayList<String> newFilter = new ArrayList<String>(len);
        ObaArrivalInfoResponse response = getArrivalsLoader().getLastGoodResponse();
        final List<ObaRoute> routes = response.getRoutes(mStop.getRouteIds());
        if (!ListenerUtil.mutListener.listen(609)) {
            if (routes.size() != len) {
                throw new IllegalArgumentException("checks.length must be equal to routes.size()");
            }
        }
        if (!ListenerUtil.mutListener.listen(617)) {
            {
                long _loopCounter5 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(616) ? (i >= len) : (ListenerUtil.mutListener.listen(615) ? (i <= len) : (ListenerUtil.mutListener.listen(614) ? (i > len) : (ListenerUtil.mutListener.listen(613) ? (i != len) : (ListenerUtil.mutListener.listen(612) ? (i == len) : (i < len)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter5", ++_loopCounter5);
                    final ObaRoute route = routes.get(i);
                    if (!ListenerUtil.mutListener.listen(611)) {
                        if (checks[i]) {
                            if (!ListenerUtil.mutListener.listen(610)) {
                                newFilter.add(route.getId());
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(624)) {
            // don't select any.
            if ((ListenerUtil.mutListener.listen(622) ? (newFilter.size() >= len) : (ListenerUtil.mutListener.listen(621) ? (newFilter.size() <= len) : (ListenerUtil.mutListener.listen(620) ? (newFilter.size() > len) : (ListenerUtil.mutListener.listen(619) ? (newFilter.size() < len) : (ListenerUtil.mutListener.listen(618) ? (newFilter.size() != len) : (newFilter.size() == len))))))) {
                if (!ListenerUtil.mutListener.listen(623)) {
                    newFilter.clear();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(625)) {
            setRoutesFilter(newFilter);
        }
    }

    // 
    private void goToTrip(ArrivalInfo stop) {
        ObaArrivalInfo stopInfo = stop.getInfo();
        if (!ListenerUtil.mutListener.listen(626)) {
            TripInfoActivity.start(getActivity(), stopInfo.getTripId(), mStopId, stopInfo.getRouteId(), stopInfo.getShortName(), mStop.getName(), stopInfo.getScheduledDepartureTime(), stopInfo.getHeadsign());
        }
    }

    private void goToTripDetails(ArrivalInfo stop) {
        if (!ListenerUtil.mutListener.listen(627)) {
            TripDetailsActivity.start(getActivity(), stop.getInfo().getTripId(), stop.getInfo().getStopId(), TripDetailsListFragment.SCROLL_MODE_STOP);
        }
    }

    private void goToRoute(ArrivalInfo stop) {
        if (!ListenerUtil.mutListener.listen(628)) {
            RouteInfoActivity.start(getActivity(), stop.getInfo().getRouteId());
        }
    }

    // 
    private ArrivalsListLoader getArrivalsLoader() {
        if (!ListenerUtil.mutListener.listen(629)) {
            // If the Fragment hasn't been attached to an Activity yet, return null
            if (!isAdded()) {
                return null;
            }
        }
        Loader<ObaArrivalInfoResponse> l = getLoaderManager().getLoader(ARRIVALS_LIST_LOADER);
        return (ArrivalsListLoader) l;
    }

    @Override
    public void setEmptyText(CharSequence text) {
        TextView noArrivals = (TextView) mEmptyList.findViewById(R.id.noArrivals);
        if (!ListenerUtil.mutListener.listen(630)) {
            noArrivals.setText(text);
        }
    }

    private void loadMoreArrivals() {
        if (!ListenerUtil.mutListener.listen(631)) {
            getArrivalsLoader().incrementMinutesAfter();
        }
        if (!ListenerUtil.mutListener.listen(632)) {
            mLoadedMoreArrivals = true;
        }
        if (!ListenerUtil.mutListener.listen(633)) {
            refresh();
        }
        if (!ListenerUtil.mutListener.listen(634)) {
            ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getActivity().getString(R.string.analytics_label_load_more_arrivals), null);
        }
    }

    /**
     * Full refresh of data from the OBA server
     */
    public void refresh() {
        if (!ListenerUtil.mutListener.listen(639)) {
            if (isAdded()) {
                if (!ListenerUtil.mutListener.listen(635)) {
                    showProgress(true);
                }
                // ArrivalsListLoader before onLoadFinished() is called
                ObaArrivalInfoResponse lastGood = getArrivalsLoader().getLastGoodResponse();
                if (!ListenerUtil.mutListener.listen(637)) {
                    if (lastGood != null) {
                        if (!ListenerUtil.mutListener.listen(636)) {
                            mLastResponseLength = lastGood.getArrivalInfo().length;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(638)) {
                    getArrivalsLoader().onContentChanged();
                }
            }
        }
    }

    /**
     * Refreshes ListFragment content using the most recent server response.  Does not trigger
     * another call to the OBA server.
     */
    public void refreshLocal() {
        ArrivalsListLoader loader = getArrivalsLoader();
        if (!ListenerUtil.mutListener.listen(642)) {
            if (loader != null) {
                ObaArrivalInfoResponse response = loader.getLastGoodResponse();
                if (!ListenerUtil.mutListener.listen(640)) {
                    if (response == null) {
                        // Nothing to refresh yet
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(641)) {
                    mAdapter.setData(response.getArrivalInfo(), mRoutesFilter, System.currentTimeMillis());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(644)) {
            if (mHeader != null) {
                if (!ListenerUtil.mutListener.listen(643)) {
                    mHeader.refresh();
                }
            }
        }
    }

    private final Handler mRefreshHandler = new Handler();

    private final Runnable mRefresh = new Runnable() {

        public void run() {
            if (!ListenerUtil.mutListener.listen(645)) {
                refresh();
            }
        }
    };

    private void setStopId() {
        Uri uri = (Uri) getArguments().getParcelable(FragmentUtils.URI);
        if (!ListenerUtil.mutListener.listen(647)) {
            if (uri == null) {
                if (!ListenerUtil.mutListener.listen(646)) {
                    Log.e(TAG, "No URI in arguments");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(648)) {
            mStopId = uri.getLastPathSegment();
        }
        if (!ListenerUtil.mutListener.listen(649)) {
            mStopUri = uri;
        }
    }

    private static final String[] USER_PROJECTION = { ObaContract.Stops.FAVORITE, ObaContract.Stops.USER_NAME };

    private void setUserInfo() {
        ContentResolver cr = getActivity().getContentResolver();
        Cursor c = cr.query(mStopUri, USER_PROJECTION, null, null, null);
        if (!ListenerUtil.mutListener.listen(654)) {
            if (c != null) {
                try {
                    if (!ListenerUtil.mutListener.listen(653)) {
                        if (c.moveToNext()) {
                            if (!ListenerUtil.mutListener.listen(651)) {
                                mFavorite = (c.getInt(0) == 1);
                            }
                            if (!ListenerUtil.mutListener.listen(652)) {
                                mStopUserName = c.getString(1);
                            }
                        }
                    }
                } finally {
                    if (!ListenerUtil.mutListener.listen(650)) {
                        c.close();
                    }
                }
            }
        }
    }

    private static final String[] TRIPS_PROJECTION = { ObaContract.Trips._ID, ObaContract.Trips.NAME };

    // 
    private class TripsForStopCallback implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getActivity(), ObaContract.Trips.CONTENT_URI, TRIPS_PROJECTION, ObaContract.Trips.STOP_ID + "=?", new String[] { mStopId }, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
            ContentQueryMap map = new ContentQueryMap(c, ObaContract.Trips._ID, true, null);
            if (!ListenerUtil.mutListener.listen(655)) {
                // Call back into the adapter and header and say we've finished this.
                mAdapter.setTripsForStop(map);
            }
            if (!ListenerUtil.mutListener.listen(657)) {
                if (mHeader != null) {
                    if (!ListenerUtil.mutListener.listen(656)) {
                        mHeader.setTripsForStop(map);
                    }
                }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        }
    }

    // 
    private class SituationAlert implements AlertList.Alert {

        private final ObaSituation mSituation;

        SituationAlert(ObaSituation situation) {
            mSituation = situation;
        }

        @Override
        public String getId() {
            return mSituation.getId();
        }

        @Override
        public int getType() {
            if (ObaSituation.SEVERITY_NO_IMPACT.equals(mSituation.getSeverity())) {
                return TYPE_INFO;
            } else if ((ListenerUtil.mutListener.listen(658) ? (ObaSituation.SEVERITY_SEVERE.equals(mSituation.getSeverity()) && ObaSituation.SEVERITY_VERY_SEVERE.equals(mSituation.getSeverity())) : (ObaSituation.SEVERITY_SEVERE.equals(mSituation.getSeverity()) || ObaSituation.SEVERITY_VERY_SEVERE.equals(mSituation.getSeverity())))) {
                return TYPE_ERROR;
            } else {
                // Treat all other ObaSituation.SEVERITY_* types as a warning
                return TYPE_WARNING;
            }
        }

        @Override
        public int getFlags() {
            return FLAG_HASMORE;
        }

        @Override
        public CharSequence getString() {
            return mSituation.getSummary();
        }

        @Override
        public void onClick() {
            SituationDialogFragment dialog = SituationDialogFragment.newInstance(mSituation);
            if (!ListenerUtil.mutListener.listen(662)) {
                dialog.setListener(new SituationDialogFragment.Listener() {

                    @Override
                    public void onDismiss(boolean isAlertHidden) {
                        if (!ListenerUtil.mutListener.listen(660)) {
                            if (isAlertHidden) {
                                if (!ListenerUtil.mutListener.listen(659)) {
                                    // TODO - refreshLocal() should support refreshing local situations
                                    refresh();
                                }
                            }
                        }
                    }

                    @Override
                    public void onUndo() {
                        if (!ListenerUtil.mutListener.listen(661)) {
                            // TODO - refreshLocal() should support refreshing local situations
                            refresh();
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(663)) {
                dialog.show(getFragmentManager(), SituationDialogFragment.TAG);
            }
            if (!ListenerUtil.mutListener.listen(664)) {
                reportAnalytics(mSituation);
            }
        }

        @Override
        public int hashCode() {
            return getId().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!ListenerUtil.mutListener.listen(665)) {
                if (this == obj) {
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(666)) {
                if (obj == null) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(667)) {
                if (getClass() != obj.getClass()) {
                    return false;
                }
            }
            SituationAlert other = (SituationAlert) obj;
            if (!ListenerUtil.mutListener.listen(668)) {
                if (!getId().equals(other.getId())) {
                    return false;
                }
            }
            return true;
        }
    }

    private void reportAnalytics(ObaSituation situation) {
        ObaSituation.AllAffects[] allAffects = situation.getAllAffects();
        Set<String> agencyIds = new HashSet<>();
        if (!ListenerUtil.mutListener.listen(675)) {
            {
                long _loopCounter6 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(674) ? (i >= allAffects.length) : (ListenerUtil.mutListener.listen(673) ? (i <= allAffects.length) : (ListenerUtil.mutListener.listen(672) ? (i > allAffects.length) : (ListenerUtil.mutListener.listen(671) ? (i != allAffects.length) : (ListenerUtil.mutListener.listen(670) ? (i == allAffects.length) : (i < allAffects.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter6", ++_loopCounter6);
                    if (!ListenerUtil.mutListener.listen(669)) {
                        agencyIds.add(allAffects[i].getAgencyId());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(677)) {
            {
                long _loopCounter7 = 0;
                for (String agencyId : agencyIds) {
                    ListenerUtil.loopListener.listen("_loopCounter7", ++_loopCounter7);
                    if (!ListenerUtil.mutListener.listen(676)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_service_alerts), getString(R.string.analytics_label_service_alerts) + agencyId);
                    }
                }
            }
        }
    }

    private void refreshSituations(List<ObaSituation> situations) {
        if (!ListenerUtil.mutListener.listen(680)) {
            // First, remove any existing situations
            if (mSituationAlerts != null) {
                if (!ListenerUtil.mutListener.listen(679)) {
                    {
                        long _loopCounter8 = 0;
                        for (SituationAlert alert : mSituationAlerts) {
                            ListenerUtil.loopListener.listen("_loopCounter8", ++_loopCounter8);
                            if (!ListenerUtil.mutListener.listen(678)) {
                                mAlertList.remove(alert);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(681)) {
            mAlertList.setAlertHidden(false);
        }
        if (!ListenerUtil.mutListener.listen(682)) {
            mSituationAlerts = null;
        }
        if (!ListenerUtil.mutListener.listen(683)) {
            if (situations.isEmpty()) {
                // The normal scenario
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(684)) {
            mSituationAlerts = new ArrayList<>();
        }
        ContentValues values = new ContentValues();
        int hiddenCount = 0;
        if (!ListenerUtil.mutListener.listen(693)) {
            {
                long _loopCounter9 = 0;
                for (ObaSituation situation : situations) {
                    ListenerUtil.loopListener.listen("_loopCounter9", ++_loopCounter9);
                    if (!ListenerUtil.mutListener.listen(685)) {
                        values.clear();
                    }
                    if (!ListenerUtil.mutListener.listen(686)) {
                        // Make sure this situation is added to the database
                        ObaContract.ServiceAlerts.insertOrUpdate(situation.getId(), values, false, null);
                    }
                    boolean isActive = UIUtils.isActiveWindowForSituation(situation, System.currentTimeMillis());
                    boolean isHidden = ObaContract.ServiceAlerts.isHidden(situation.getId());
                    if (!ListenerUtil.mutListener.listen(689)) {
                        if ((ListenerUtil.mutListener.listen(687) ? (isActive || !isHidden) : (isActive && !isHidden))) {
                            SituationAlert alert = new SituationAlert(situation);
                            if (!ListenerUtil.mutListener.listen(688)) {
                                mSituationAlerts.add(alert);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(692)) {
                        if (isHidden) {
                            if (!ListenerUtil.mutListener.listen(690)) {
                                mAlertList.setAlertHidden(true);
                            }
                            if (!ListenerUtil.mutListener.listen(691)) {
                                hiddenCount++;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(694)) {
            mAlertList.setHiddenAlertCount(hiddenCount);
        }
        if (!ListenerUtil.mutListener.listen(695)) {
            mAlertList.addAll(mSituationAlerts);
        }
    }

    /**
     * Creates the dialog that will be shown to the user to explain the occupancy feature
     *
     * @return the dialog that will be shown to the user to explain the occupancy feature
     */
    private Dialog createOccupancyDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        if (!ListenerUtil.mutListener.listen(696)) {
            builder.setTitle(R.string.menu_title_about_occupancy);
        }
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View occupancyDialogView = inflater.inflate(R.layout.occupancy_dialog, null);
        if (!ListenerUtil.mutListener.listen(697)) {
            builder.setView(occupancyDialogView);
        }
        ViewGroup realtimeOccupancy = occupancyDialogView.findViewById(R.id.realtime_occupancy);
        ViewGroup historicalOccupancy = occupancyDialogView.findViewById(R.id.historical_occupancy);
        if (!ListenerUtil.mutListener.listen(698)) {
            UIUtils.setOccupancyVisibilityAndColor(realtimeOccupancy, Occupancy.FULL, OccupancyState.REALTIME);
        }
        if (!ListenerUtil.mutListener.listen(699)) {
            UIUtils.setOccupancyVisibilityAndColor(historicalOccupancy, Occupancy.FULL, OccupancyState.HISTORICAL);
        }
        if (!ListenerUtil.mutListener.listen(700)) {
            builder.setNeutralButton(R.string.main_help_close, (dialogInterface, i) -> dialogInterface.dismiss());
        }
        return builder.create();
    }
}
