/*
 * Copyright (C) 2012 Paul Watts (paulcwatts@gmail.com)
 * and individual contributors.
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
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaElement;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.request.ObaRoutesForLocationRequest;
import org.onebusaway.android.io.request.ObaRoutesForLocationResponse;
import org.onebusaway.android.io.request.ObaStopsForLocationRequest;
import org.onebusaway.android.io.request.ObaStopsForLocationResponse;
import org.onebusaway.android.util.ArrayAdapter;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * This implements the response that returns both stops and routes in one list.
 *
 * @author paulw
 */
final class SearchResponse {

    private final int mCode;

    private final List<ObaElement> mResults;

    SearchResponse(int code, List<ObaElement> r) {
        mCode = code;
        mResults = r;
    }

    int getCode() {
        return mCode;
    }

    List<ObaElement> getResults() {
        return mResults;
    }
}

public class SearchResultsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<SearchResponse> {

    // private static final String TAG = "SearchResultsFragment";
    public static final String QUERY_TEXT = "query_text";

    private MyAdapter mAdapter;

    /**
     * GoogleApiClient being used for Location Services
     */
    GoogleApiClient mGoogleApiClient;

    @Override
    public void onAttach(Activity activity) {
        if (!ListenerUtil.mutListener.listen(30)) {
            super.onAttach(activity);
        }
        // Init Google Play Services as early as possible in the Fragment lifecycle to give it time
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        if (!ListenerUtil.mutListener.listen(33)) {
            if (api.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(31)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(32)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(34)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(35)) {
            mAdapter = new MyAdapter(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(36)) {
            setListAdapter(mAdapter);
        }
        if (!ListenerUtil.mutListener.listen(37)) {
            search();
        }
    }

    @Override
    public void onStart() {
        if (!ListenerUtil.mutListener.listen(38)) {
            super.onStart();
        }
        if (!ListenerUtil.mutListener.listen(41)) {
            // Make sure GoogleApiClient is connected, if available
            if ((ListenerUtil.mutListener.listen(39) ? (mGoogleApiClient != null || !mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && !mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(40)) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    public void onStop() {
        if (!ListenerUtil.mutListener.listen(44)) {
            // Tear down GoogleApiClient
            if ((ListenerUtil.mutListener.listen(42) ? (mGoogleApiClient != null || mGoogleApiClient.isConnected()) : (mGoogleApiClient != null && mGoogleApiClient.isConnected()))) {
                if (!ListenerUtil.mutListener.listen(43)) {
                    mGoogleApiClient.disconnect();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(45)) {
            super.onStop();
        }
    }

    private void search() {
        if (!ListenerUtil.mutListener.listen(46)) {
            UIUtils.showProgress(this, true);
        }
        Loader<?> loader = getLoaderManager().restartLoader(0, getArguments(), this);
        if (!ListenerUtil.mutListener.listen(47)) {
            // loader.onContentChanged();
            loader.forceLoad();
        }
    }

    @Override
    public Loader<SearchResponse> onCreateLoader(int id, Bundle args) {
        String query = args.getString(QUERY_TEXT);
        Location location = Application.getLastKnownLocation(getActivity(), mGoogleApiClient);
        if (!ListenerUtil.mutListener.listen(49)) {
            if (location == null) {
                if (!ListenerUtil.mutListener.listen(48)) {
                    location = LocationUtils.getDefaultSearchCenter();
                }
            }
        }
        return new MyLoader(getActivity(), query, location);
    }

    @Override
    public void onLoadFinished(Loader<SearchResponse> loader, SearchResponse response) {
        if (!ListenerUtil.mutListener.listen(50)) {
            UIUtils.showProgress(this, false);
        }
        // Log.d(TAG, "Loader finished");
        final int code = response.getCode();
        if (!ListenerUtil.mutListener.listen(60)) {
            if (code == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(58)) {
                    setEmptyText(getString(R.string.find_hint_noresults));
                }
                if (!ListenerUtil.mutListener.listen(59)) {
                    mAdapter.setData(response.getResults());
                }
            } else if ((ListenerUtil.mutListener.listen(55) ? (code >= 0) : (ListenerUtil.mutListener.listen(54) ? (code <= 0) : (ListenerUtil.mutListener.listen(53) ? (code > 0) : (ListenerUtil.mutListener.listen(52) ? (code < 0) : (ListenerUtil.mutListener.listen(51) ? (code == 0) : (code != 0))))))) {
                if (!ListenerUtil.mutListener.listen(57)) {
                    // a 'communication' error. Just fake no results.
                    setEmptyText(getString(R.string.find_hint_noresults));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(56)) {
                    setEmptyText(getString(R.string.generic_comm_error));
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<SearchResponse> loader) {
        if (!ListenerUtil.mutListener.listen(61)) {
            mAdapter.clear();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ListAdapter adapter = l.getAdapter();
        ObaElement e = (ObaElement) adapter.getItem((ListenerUtil.mutListener.listen(65) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(64) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(63) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(62) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        if (!ListenerUtil.mutListener.listen(68)) {
            if (e instanceof ObaRoute) {
                if (!ListenerUtil.mutListener.listen(67)) {
                    clickRoute((ObaRoute) e);
                }
            } else if (e instanceof ObaStop) {
                if (!ListenerUtil.mutListener.listen(66)) {
                    clickStop((ObaStop) e);
                }
            }
        }
    }

    private void clickRoute(ObaRoute route) {
        final String routeId = route.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(69)) {
            builder.setTitle(UIUtils.getRouteDescription(route));
        }
        if (!ListenerUtil.mutListener.listen(73)) {
            builder.setItems(R.array.search_route_options, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(72)) {
                        switch(which) {
                            case 0:
                                if (!ListenerUtil.mutListener.listen(70)) {
                                    // Show on list
                                    RouteInfoActivity.start(getActivity(), routeId);
                                }
                                break;
                            case 1:
                                if (!ListenerUtil.mutListener.listen(71)) {
                                    // Show on map
                                    HomeActivity.start(getActivity(), routeId);
                                }
                                break;
                        }
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(74)) {
            dialog.setOwnerActivity(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(75)) {
            dialog.show();
        }
    }

    private void clickStop(final ObaStop stop) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(76)) {
            builder.setTitle(UIUtils.formatDisplayText(stop.getName()));
        }
        if (!ListenerUtil.mutListener.listen(80)) {
            builder.setItems(R.array.search_stop_options, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(79)) {
                        switch(which) {
                            case 0:
                                if (!ListenerUtil.mutListener.listen(77)) {
                                    ArrivalsListActivity.start(getActivity(), stop.getId());
                                }
                                break;
                            case 1:
                                if (!ListenerUtil.mutListener.listen(78)) {
                                    HomeActivity.start(getActivity(), stop.getId(), stop.getLatitude(), stop.getLongitude());
                                }
                                break;
                        }
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(81)) {
            dialog.setOwnerActivity(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(82)) {
            dialog.show();
        }
    }

    // 
    private static final class MyAdapter extends ArrayAdapter<ObaElement> {

        public MyAdapter(Context context) {
            super(context, R.layout.route_list_item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            LayoutInflater inflater = getLayoutInflater();
            ObaElement elem = getItem(position);
            // so unfortunately we can't actually re-use views...
            if (elem instanceof ObaRoute) {
                view = inflater.inflate(R.layout.route_list_item, parent, false);
                if (!ListenerUtil.mutListener.listen(84)) {
                    UIUtils.setRouteView(view, (ObaRoute) elem);
                }
            } else if (elem instanceof ObaStop) {
                view = inflater.inflate(R.layout.stop_list_item, parent, false);
                if (!ListenerUtil.mutListener.listen(83)) {
                    initStop(view, (ObaStop) elem);
                }
            } else {
                view = null;
            }
            return view;
        }

        private void initStop(View view, ObaStop stop) {
            TextView nameView = (TextView) view.findViewById(R.id.stop_name);
            if (!ListenerUtil.mutListener.listen(85)) {
                nameView.setText(UIUtils.formatDisplayText(stop.getName()));
            }
            if (!ListenerUtil.mutListener.listen(86)) {
                UIUtils.setStopDirection(view.findViewById(R.id.direction), stop.getDirection(), true);
            }
        }

        @Override
        protected void initView(View view, ObaElement t) {
            throw new AssertionError("Should never be called");
        }
    }

    // 
    private static final class MyLoader extends AsyncTaskLoader<SearchResponse> {

        private final String mQueryText;

        private final Location mCenter;

        public MyLoader(Context context, String query, Location center) {
            super(context);
            mQueryText = query;
            mCenter = center;
        }

        private ObaRoutesForLocationResponse getRoutes() {
            ObaRoutesForLocationResponse response = new ObaRoutesForLocationRequest.Builder(getContext(), mCenter).setRadius(LocationUtils.DEFAULT_SEARCH_RADIUS).setQuery(mQueryText).build().call();
            if (!ListenerUtil.mutListener.listen(88)) {
                // Log.d(TAG, "Server returns: " + response.getCode());
                if (response.getCode() == ObaApi.OBA_OK) {
                    ObaRoute[] routes = response.getRoutesForLocation();
                    if (!ListenerUtil.mutListener.listen(87)) {
                        if (routes.length != 0) {
                            return response;
                        }
                    }
                }
            }
            Location center = LocationUtils.getDefaultSearchCenter();
            if (!ListenerUtil.mutListener.listen(89)) {
                if (center != null) {
                    return new ObaRoutesForLocationRequest.Builder(getContext(), center).setRadius(LocationUtils.DEFAULT_SEARCH_RADIUS).setQuery(mQueryText).build().call();
                }
            }
            // I suppose we just return what was there...
            return response;
        }

        private ObaStopsForLocationResponse getStops() {
            return new ObaStopsForLocationRequest.Builder(getContext(), mCenter).setRadius(LocationUtils.DEFAULT_SEARCH_RADIUS).setQuery(mQueryText).build().call();
        }

        @Override
        public SearchResponse loadInBackground() {
            ArrayList<ObaElement> results = new ArrayList<ObaElement>();
            ObaRoutesForLocationResponse routes = getRoutes();
            ObaStopsForLocationResponse stops = getStops();
            int routeCode = routes.getCode();
            int stopCode = stops.getCode();
            int code = ObaApi.OBA_OK;
            if (!ListenerUtil.mutListener.listen(92)) {
                // if neither of them are OK, return one of them.
                if ((ListenerUtil.mutListener.listen(90) ? (routeCode != ObaApi.OBA_OK || stopCode != ObaApi.OBA_OK) : (routeCode != ObaApi.OBA_OK && stopCode != ObaApi.OBA_OK))) {
                    if (!ListenerUtil.mutListener.listen(91)) {
                        code = routeCode;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(95)) {
                if (code == ObaApi.OBA_OK) {
                    if (!ListenerUtil.mutListener.listen(93)) {
                        results.addAll(Arrays.asList(routes.getRoutesForLocation()));
                    }
                    if (!ListenerUtil.mutListener.listen(94)) {
                        results.addAll(Arrays.asList(stops.getStops()));
                    }
                }
            }
            return new SearchResponse(code, results);
        }
    }
}
