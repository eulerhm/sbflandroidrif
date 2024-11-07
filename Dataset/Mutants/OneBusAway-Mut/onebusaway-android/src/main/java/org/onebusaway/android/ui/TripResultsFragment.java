/*
 * Copyright (C) 2012-2013 Paul Watts (paulcwatts@gmail.com)
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

import com.google.android.material.tabs.TabLayout;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.directions.model.Direction;
import org.onebusaway.android.directions.realtime.RealtimeService;
import org.onebusaway.android.directions.util.ConversionUtils;
import org.onebusaway.android.directions.util.DirectionExpandableListAdapter;
import org.onebusaway.android.directions.util.DirectionsGenerator;
import org.onebusaway.android.directions.util.OTPConstants;
import org.onebusaway.android.map.MapParams;
import org.onebusaway.android.map.googlemapsv2.BaseMapFragment;
import org.opentripplanner.api.model.Itinerary;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripResultsFragment extends Fragment {

    private static final String TAG = "TripResultsFragment";

    private static final int LIST_TAB_POSITION = 0;

    private static final int MAP_TAB_POSITION = 1;

    private View mDirectionsFrame;

    private BaseMapFragment mMapFragment;

    private ExpandableListView mDirectionsListView;

    private View mMapFragmentFrame;

    private boolean mShowingMap = false;

    private RoutingOptionPicker[] mOptions = new RoutingOptionPicker[3];

    private Listener mListener;

    private Bundle mMapBundle = new Bundle();

    /**
     * This listener is a helper for the parent activity to handle the sliding panel,
     * which interacts with sliding views (i.e., list view and map view) in subtle ways.
     */
    public interface Listener {

        /**
         * Called when the result views have been created
         *
         * @param containerView the view which contains the directions list and the map
         * @param listView the directions list view
         * @param mapView the map frame
         */
        void onResultViewCreated(View containerView, ListView listView, View mapView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2167)) {
            super.onCreate(savedInstanceState);
        }
        final View view = inflater.inflate(R.layout.fragment_trip_plan_results, container, false);
        if (!ListenerUtil.mutListener.listen(2168)) {
            mDirectionsFrame = view.findViewById(R.id.directionsFrame);
        }
        if (!ListenerUtil.mutListener.listen(2169)) {
            mDirectionsListView = (ExpandableListView) view.findViewById(R.id.directionsListView);
        }
        if (!ListenerUtil.mutListener.listen(2170)) {
            mMapFragmentFrame = view.findViewById(R.id.mapFragment);
        }
        if (!ListenerUtil.mutListener.listen(2171)) {
            mOptions[0] = new RoutingOptionPicker(view, R.id.option1LinearLayout, R.id.option1Title, R.id.option1Duration, R.id.option1Interval);
        }
        if (!ListenerUtil.mutListener.listen(2172)) {
            mOptions[1] = new RoutingOptionPicker(view, R.id.option2LinearLayout, R.id.option2Title, R.id.option2Duration, R.id.option2Interval);
        }
        if (!ListenerUtil.mutListener.listen(2173)) {
            mOptions[2] = new RoutingOptionPicker(view, R.id.option3LinearLayout, R.id.option3Title, R.id.option3Duration, R.id.option3Interval);
        }
        // defaults to 0
        int rank = getArguments().getInt(OTPConstants.SELECTED_ITINERARY);
        if (!ListenerUtil.mutListener.listen(2174)) {
            mShowingMap = getArguments().getBoolean(OTPConstants.SHOW_MAP);
        }
        if (!ListenerUtil.mutListener.listen(2175)) {
            initInfoAndMap(rank);
        }
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_switch_view);
        if (!ListenerUtil.mutListener.listen(2177)) {
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    boolean show = (tab.getPosition() == MAP_TAB_POSITION);
                    if (!ListenerUtil.mutListener.listen(2176)) {
                        showMap(show);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(2178)) {
            setTabDrawable(tabLayout.getTabAt(LIST_TAB_POSITION), R.drawable.ic_list);
        }
        if (!ListenerUtil.mutListener.listen(2179)) {
            setTabDrawable(tabLayout.getTabAt(MAP_TAB_POSITION), R.drawable.ic_arrivals_styleb_action_map);
        }
        if (!ListenerUtil.mutListener.listen(2181)) {
            if (mShowingMap) {
                if (!ListenerUtil.mutListener.listen(2180)) {
                    tabLayout.getTabAt(MAP_TAB_POSITION).select();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2183)) {
            if (mListener != null) {
                if (!ListenerUtil.mutListener.listen(2182)) {
                    mListener.onResultViewCreated(mDirectionsFrame, mDirectionsListView, mMapFragmentFrame);
                }
            }
        }
        return view;
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(2184)) {
            super.onDestroy();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(2186)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(2185)) {
                    NavHelp.goUp(getActivity());
                }
                return true;
            }
        }
        if (!ListenerUtil.mutListener.listen(2188)) {
            if (item.getItemId() == R.id.show_on_map) {
                if (!ListenerUtil.mutListener.listen(2187)) {
                    showMap(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2190)) {
            if (item.getItemId() == R.id.list) {
                if (!ListenerUtil.mutListener.listen(2189)) {
                    showMap(false);
                }
            }
        }
        return false;
    }

    /**
     * Set the listener for this fragment.
     *
     * @param listener the new listener
     */
    public void setListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(2191)) {
            mListener = listener;
        }
    }

    /**
     * Get whether map is showing.
     *
     * @return true if map is showing, false otherwise
     */
    public boolean isMapShowing() {
        return mShowingMap;
    }

    private void setTabDrawable(TabLayout.Tab tab, @DrawableRes int res) {
        View view = tab.getCustomView();
        TextView tv = ((TextView) view.findViewById(android.R.id.text1));
        Drawable drawable = getResources().getDrawable(res);
        int dp = (int) getResources().getDimension(R.dimen.trip_results_icon_size);
        if (!ListenerUtil.mutListener.listen(2192)) {
            drawable.setBounds(0, 0, dp, dp);
        }
        if (!ListenerUtil.mutListener.listen(2193)) {
            drawable.setColorFilter(getResources().getColor(R.color.trip_option_icon_tint), PorterDuff.Mode.SRC_IN);
        }
        if (!ListenerUtil.mutListener.listen(2194)) {
            tv.setCompoundDrawables(drawable, null, null, null);
        }
    }

    private void initMap(int trip) {
        Itinerary itinerary = getItineraries().get(trip);
        if (!ListenerUtil.mutListener.listen(2195)) {
            mMapBundle.putString(MapParams.MODE, MapParams.MODE_DIRECTIONS);
        }
        if (!ListenerUtil.mutListener.listen(2196)) {
            mMapBundle.putSerializable(MapParams.ITINERARY, itinerary);
        }
        Intent intent = new Intent().putExtras(mMapBundle);
        if (!ListenerUtil.mutListener.listen(2197)) {
            getActivity().setIntent(intent);
        }
        FragmentManager fm = getChildFragmentManager();
        if (!ListenerUtil.mutListener.listen(2203)) {
            if (mMapFragment == null) {
                if (!ListenerUtil.mutListener.listen(2198)) {
                    // First check to see if an instance of BaseMapFragment already exists
                    mMapFragment = (BaseMapFragment) fm.findFragmentByTag(BaseMapFragment.TAG);
                }
                if (!ListenerUtil.mutListener.listen(2202)) {
                    if (mMapFragment == null) {
                        if (!ListenerUtil.mutListener.listen(2199)) {
                            // No existing fragment was found, so create a new one
                            Log.d(TAG, "Creating new BaseMapFragment");
                        }
                        if (!ListenerUtil.mutListener.listen(2200)) {
                            mMapFragment = BaseMapFragment.newInstance();
                        }
                        if (!ListenerUtil.mutListener.listen(2201)) {
                            fm.beginTransaction().add(R.id.mapFragment, mMapFragment, BaseMapFragment.TAG).commit();
                        }
                    }
                }
            }
        }
    }

    private void showMap(boolean show) {
        if (!ListenerUtil.mutListener.listen(2204)) {
            mShowingMap = show;
        }
        if (!ListenerUtil.mutListener.listen(2208)) {
            if (show) {
                if (!ListenerUtil.mutListener.listen(2206)) {
                    mMapFragmentFrame.bringToFront();
                }
                if (!ListenerUtil.mutListener.listen(2207)) {
                    mMapFragment.setMapMode(MapParams.MODE_DIRECTIONS, mMapBundle);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2205)) {
                    mDirectionsListView.bringToFront();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2209)) {
            getArguments().putBoolean(OTPConstants.SHOW_MAP, mShowingMap);
        }
    }

    private void initInfoAndMap(int trip) {
        if (!ListenerUtil.mutListener.listen(2210)) {
            initMap(trip);
        }
        if (!ListenerUtil.mutListener.listen(2217)) {
            {
                long _loopCounter26 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2216) ? (i >= mOptions.length) : (ListenerUtil.mutListener.listen(2215) ? (i <= mOptions.length) : (ListenerUtil.mutListener.listen(2214) ? (i > mOptions.length) : (ListenerUtil.mutListener.listen(2213) ? (i != mOptions.length) : (ListenerUtil.mutListener.listen(2212) ? (i == mOptions.length) : (i < mOptions.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter26", ++_loopCounter26);
                    if (!ListenerUtil.mutListener.listen(2211)) {
                        mOptions[i].setItinerary(i);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2218)) {
            mOptions[trip].select();
        }
        if (!ListenerUtil.mutListener.listen(2219)) {
            showMap(mShowingMap);
        }
    }

    public void displayNewResults() {
        int rank = getArguments().getInt(OTPConstants.SELECTED_ITINERARY);
        if (!ListenerUtil.mutListener.listen(2220)) {
            showMap(mShowingMap);
        }
        if (!ListenerUtil.mutListener.listen(2221)) {
            initInfoAndMap(rank);
        }
    }

    private String toDateFmt(long ms) {
        Date d = new Date(ms);
        String s = new SimpleDateFormat(OTPConstants.TRIP_RESULTS_TIME_STRING_FORMAT_SUMMARY, Locale.getDefault()).format(d);
        return s.substring(0, 6).toLowerCase();
    }

    private String formatTimeString(String ms, double durationSec) {
        long start = Long.parseLong(ms);
        String fromString = toDateFmt(start);
        String toString = toDateFmt((ListenerUtil.mutListener.listen(2225) ? (start % (long) durationSec) : (ListenerUtil.mutListener.listen(2224) ? (start / (long) durationSec) : (ListenerUtil.mutListener.listen(2223) ? (start * (long) durationSec) : (ListenerUtil.mutListener.listen(2222) ? (start - (long) durationSec) : (start + (long) durationSec))))));
        return fromString + " - " + toString;
    }

    private List<Itinerary> getItineraries() {
        return (List<Itinerary>) getArguments().getSerializable(OTPConstants.ITINERARIES);
    }

    private class RoutingOptionPicker {

        LinearLayout linearLayout;

        TextView titleView;

        TextView durationView;

        TextView intervalView;

        Itinerary itinerary;

        int rank;

        RoutingOptionPicker(View view, int linearLayout, int titleView, int durationView, int intervalView) {
            if (!ListenerUtil.mutListener.listen(2226)) {
                this.linearLayout = (LinearLayout) view.findViewById(linearLayout);
            }
            if (!ListenerUtil.mutListener.listen(2227)) {
                this.titleView = (TextView) view.findViewById(titleView);
            }
            if (!ListenerUtil.mutListener.listen(2228)) {
                this.durationView = (TextView) view.findViewById(durationView);
            }
            if (!ListenerUtil.mutListener.listen(2229)) {
                this.intervalView = (TextView) view.findViewById(intervalView);
            }
            if (!ListenerUtil.mutListener.listen(2231)) {
                this.linearLayout.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(2230)) {
                            RoutingOptionPicker.this.select();
                        }
                    }
                });
            }
        }

        void select() {
            if (!ListenerUtil.mutListener.listen(2233)) {
                {
                    long _loopCounter27 = 0;
                    for (RoutingOptionPicker picker : mOptions) {
                        ListenerUtil.loopListener.listen("_loopCounter27", ++_loopCounter27);
                        if (!ListenerUtil.mutListener.listen(2232)) {
                            picker.linearLayout.setBackgroundColor(getResources().getColor(R.color.trip_option_background));
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2234)) {
                linearLayout.setBackgroundResource(R.drawable.trip_option_selected_item);
            }
            if (!ListenerUtil.mutListener.listen(2235)) {
                getArguments().putInt(OTPConstants.SELECTED_ITINERARY, rank);
            }
            if (!ListenerUtil.mutListener.listen(2236)) {
                updateInfo();
            }
            if (!ListenerUtil.mutListener.listen(2237)) {
                updateMap();
            }
        }

        void setItinerary(int rank) {
            List<Itinerary> trips = getItineraries();
            if (!ListenerUtil.mutListener.listen(2245)) {
                if ((ListenerUtil.mutListener.listen(2242) ? (rank <= trips.size()) : (ListenerUtil.mutListener.listen(2241) ? (rank > trips.size()) : (ListenerUtil.mutListener.listen(2240) ? (rank < trips.size()) : (ListenerUtil.mutListener.listen(2239) ? (rank != trips.size()) : (ListenerUtil.mutListener.listen(2238) ? (rank == trips.size()) : (rank >= trips.size()))))))) {
                    if (!ListenerUtil.mutListener.listen(2243)) {
                        this.itinerary = null;
                    }
                    if (!ListenerUtil.mutListener.listen(2244)) {
                        linearLayout.setVisibility(View.GONE);
                    }
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(2246)) {
                this.itinerary = trips.get(rank);
            }
            if (!ListenerUtil.mutListener.listen(2247)) {
                this.rank = rank;
            }
            String title = new DirectionsGenerator(itinerary.legs, getContext()).getItineraryTitle();
            String duration = ConversionUtils.getFormattedDurationTextNoSeconds(itinerary.duration, false, getContext());
            String interval = formatTimeString(itinerary.startTime, (ListenerUtil.mutListener.listen(2251) ? (itinerary.duration % 1000) : (ListenerUtil.mutListener.listen(2250) ? (itinerary.duration / 1000) : (ListenerUtil.mutListener.listen(2249) ? (itinerary.duration - 1000) : (ListenerUtil.mutListener.listen(2248) ? (itinerary.duration + 1000) : (itinerary.duration * 1000))))));
            if (!ListenerUtil.mutListener.listen(2252)) {
                titleView.setText(title);
            }
            if (!ListenerUtil.mutListener.listen(2253)) {
                durationView.setText(duration);
            }
            if (!ListenerUtil.mutListener.listen(2254)) {
                intervalView.setText(interval);
            }
        }

        void updateInfo() {
            DirectionsGenerator gen = new DirectionsGenerator(itinerary.legs, getActivity().getApplicationContext());
            List<Direction> directions = gen.getDirections();
            Direction[] direction_data = directions.toArray(new Direction[directions.size()]);
            DirectionExpandableListAdapter adapter = new DirectionExpandableListAdapter(getActivity(), R.layout.list_direction_item, R.layout.list_subdirection_item, direction_data);
            if (!ListenerUtil.mutListener.listen(2255)) {
                mDirectionsListView.setAdapter(adapter);
            }
            if (!ListenerUtil.mutListener.listen(2256)) {
                mDirectionsListView.setGroupIndicator(null);
            }
            Context context = Application.get().getApplicationContext();
            if (!ListenerUtil.mutListener.listen(2266)) {
                if ((ListenerUtil.mutListener.listen(2261) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2260) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2259) ? (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2258) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(2257) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O))))))) {
                    NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    NotificationChannel channel = manager.getNotificationChannel(Application.CHANNEL_TRIP_PLAN_UPDATES_ID);
                    if (!ListenerUtil.mutListener.listen(2265)) {
                        if (channel.getImportance() != NotificationManager.IMPORTANCE_NONE) {
                            if (!ListenerUtil.mutListener.listen(2264)) {
                                RealtimeService.start(getActivity(), getArguments());
                            }
                        }
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2263)) {
                        if (Application.getPrefs().getBoolean(getString(R.string.preference_key_trip_plan_notifications), true)) {
                            if (!ListenerUtil.mutListener.listen(2262)) {
                                RealtimeService.start(getActivity(), getArguments());
                            }
                        }
                    }
                }
            }
        }

        void updateMap() {
            if (!ListenerUtil.mutListener.listen(2267)) {
                mMapBundle.putSerializable(MapParams.ITINERARY, itinerary);
            }
            if (!ListenerUtil.mutListener.listen(2268)) {
                mMapFragment.setMapMode(MapParams.MODE_DIRECTIONS, mMapBundle);
            }
        }
    }
}
