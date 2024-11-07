/*
 * Copyright (C) 2011 Paul Watts (paulcwatts@gmail.com)
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

import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.elements.ObaStopGroup;
import org.onebusaway.android.io.elements.ObaStopGrouping;
import org.onebusaway.android.io.request.ObaRouteResponse;
import org.onebusaway.android.io.request.ObaStopsForRouteRequest;
import org.onebusaway.android.io.request.ObaStopsForRouteResponse;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.FragmentUtils;
import org.onebusaway.android.util.UIUtils;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RouteInfoListFragment extends ListFragment {

    private static final String TAG = "RouteInfoListFragment";

    private static final int ROUTE_INFO_LOADER = 0;

    private static final int ROUTE_STOPS_LOADER = 1;

    private String mRouteId;

    private ObaRouteResponse mRouteInfo;

    private StopsForRouteInfo mStopsForRoute;

    private SimpleExpandableListAdapter mAdapter;

    private final RouteLoaderCallback mRouteCallback = new RouteLoaderCallback();

    private final StopsLoaderCallback mStopsCallback = new StopsLoaderCallback();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4820)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4821)) {
            // We have a menu item to show in action bar.
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(4822)) {
            registerForContextMenu(getListView());
        }
        if (!ListenerUtil.mutListener.listen(4823)) {
            // Start out with a progress indicator.
            setListShown(false);
        }
        // Initialize the expandable list
        ExpandableListView list = (ExpandableListView) getListView();
        if (!ListenerUtil.mutListener.listen(4824)) {
            list.setOnChildClickListener(mChildClick);
        }
        // Get the route ID from the "uri" argument
        Uri uri = (Uri) getArguments().getParcelable(FragmentUtils.URI);
        if (!ListenerUtil.mutListener.listen(4826)) {
            if (uri == null) {
                if (!ListenerUtil.mutListener.listen(4825)) {
                    Log.e(TAG, "No URI in arguments");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4827)) {
            mRouteId = uri.getLastPathSegment();
        }
        if (!ListenerUtil.mutListener.listen(4828)) {
            getLoaderManager().initLoader(ROUTE_INFO_LOADER, null, mRouteCallback);
        }
        if (!ListenerUtil.mutListener.listen(4829)) {
            getLoaderManager().initLoader(ROUTE_STOPS_LOADER, null, mStopsCallback);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4830)) {
            if (root == null) {
                // reason to create our view.
                return null;
            }
        }
        return inflater.inflate(R.layout.route_info, null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(4831)) {
            inflater.inflate(R.menu.route_info_options, menu);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        boolean hasUrl = false;
        if (!ListenerUtil.mutListener.listen(4833)) {
            if (mRouteInfo != null) {
                if (!ListenerUtil.mutListener.listen(4832)) {
                    hasUrl = !TextUtils.isEmpty(mRouteInfo.getUrl());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4834)) {
            menu.findItem(R.id.goto_url).setEnabled(hasUrl).setVisible(hasUrl);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(4837)) {
            if (id == R.id.show_on_map) {
                if (!ListenerUtil.mutListener.listen(4836)) {
                    HomeActivity.start(getActivity(), mRouteId);
                }
                return true;
            } else if (id == R.id.goto_url) {
                if (!ListenerUtil.mutListener.listen(4835)) {
                    UIUtils.goToUrl(getActivity(), mRouteInfo.getUrl());
                }
                return true;
            }
        }
        return false;
    }

    private final ExpandableListView.OnChildClickListener mChildClick = new ExpandableListView.OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            if (!ListenerUtil.mutListener.listen(4838)) {
                showArrivals(v);
            }
            return true;
        }
    };

    private static final int CONTEXT_MENU_DEFAULT = 1;

    private static final int CONTEXT_MENU_SHOWONMAP = 2;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(4839)) {
            super.onCreateContextMenu(menu, v, menuInfo);
        }
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        if (!ListenerUtil.mutListener.listen(4840)) {
            if (ExpandableListView.getPackedPositionType(info.packedPosition) != ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                return;
            }
        }
        final TextView text = (TextView) info.targetView.findViewById(R.id.name);
        if (!ListenerUtil.mutListener.listen(4841)) {
            menu.setHeaderTitle(text.getText());
        }
        if (!ListenerUtil.mutListener.listen(4842)) {
            menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.route_info_context_get_stop_info);
        }
        if (!ListenerUtil.mutListener.listen(4843)) {
            menu.add(0, CONTEXT_MENU_SHOWONMAP, 0, R.string.route_info_context_showonmap);
        }
    }

    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case CONTEXT_MENU_DEFAULT:
                if (!ListenerUtil.mutListener.listen(4844)) {
                    showArrivals(info.targetView);
                }
                return true;
            case CONTEXT_MENU_SHOWONMAP:
                if (!ListenerUtil.mutListener.listen(4845)) {
                    showOnMap(info.targetView);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showArrivals(View v) {
        final TextView text = (TextView) v.findViewById(R.id.stop_id);
        final String stopId = (String) text.getText();
        ObaStop stop = null;
        if (!ListenerUtil.mutListener.listen(4847)) {
            if (mStopsForRoute != null) {
                if (!ListenerUtil.mutListener.listen(4846)) {
                    stop = mStopsForRoute.getStopMap().get(stopId);
                }
            }
        }
        ArrivalsListActivity.Builder b = new ArrivalsListActivity.Builder(getActivity(), stopId);
        if (!ListenerUtil.mutListener.listen(4850)) {
            if (stop != null) {
                if (!ListenerUtil.mutListener.listen(4848)) {
                    b.setStopName(stop.getName());
                }
                if (!ListenerUtil.mutListener.listen(4849)) {
                    b.setStopDirection(stop.getDirection());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4851)) {
            b.setUpMode(NavHelp.UP_MODE_BACK);
        }
        if (!ListenerUtil.mutListener.listen(4852)) {
            b.start();
        }
    }

    private void showOnMap(View v) {
        final TextView text = (TextView) v.findViewById(R.id.stop_id);
        final String stopId = (String) text.getText();
        // we need to know it's lat/lon
        ObaStop stop = mStopsForRoute.getStopMap().get(stopId);
        if (!ListenerUtil.mutListener.listen(4853)) {
            if (stop == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4854)) {
            HomeActivity.start(getActivity(), stopId, stop.getLatitude(), stop.getLongitude());
        }
    }

    // 
    private final class RouteLoaderCallback implements LoaderManager.LoaderCallbacks<ObaRouteResponse> {

        @Override
        public Loader<ObaRouteResponse> onCreateLoader(int id, Bundle args) {
            return new QueryUtils.RouteInfoLoader(getActivity(), mRouteId);
        }

        @Override
        public void onLoadFinished(Loader<ObaRouteResponse> loader, ObaRouteResponse data) {
            if (!ListenerUtil.mutListener.listen(4855)) {
                setHeader(data, true);
            }
        }

        @Override
        public void onLoaderReset(Loader<ObaRouteResponse> loader) {
        }
    }

    private final class StopsLoaderCallback implements LoaderManager.LoaderCallbacks<StopsForRouteInfo> {

        @Override
        public Loader<StopsForRouteInfo> onCreateLoader(int id, Bundle args) {
            return new StopsForRouteLoader(getActivity(), mRouteId);
        }

        @Override
        public void onLoadFinished(Loader<StopsForRouteInfo> loader, StopsForRouteInfo data) {
            if (!ListenerUtil.mutListener.listen(4856)) {
                setStopsForRoute(data);
            }
        }

        @Override
        public void onLoaderReset(Loader<StopsForRouteInfo> loader) {
        }
    }

    private static final class StopsForRouteLoader extends AsyncTaskLoader<StopsForRouteInfo> {

        private final String mRouteId;

        StopsForRouteLoader(Context context, String routeId) {
            super(context);
            mRouteId = routeId;
        }

        @Override
        public void onStartLoading() {
            if (!ListenerUtil.mutListener.listen(4857)) {
                forceLoad();
            }
        }

        @Override
        public StopsForRouteInfo loadInBackground() {
            final ObaStopsForRouteResponse response = new ObaStopsForRouteRequest.Builder(getContext(), mRouteId).setIncludeShapes(false).build().call();
            return new StopsForRouteInfo(getContext(), response);
        }
    }

    private static final class StopsForRouteInfo {

        private final int mResultCode;

        private final ArrayList<HashMap<String, String>> mStopGroups;

        private final ArrayList<ArrayList<HashMap<String, String>>> mStops;

        private final HashMap<String, ObaStop> mStopMap;

        public StopsForRouteInfo(Context cxt, ObaStopsForRouteResponse response) {
            mStopGroups = new ArrayList<HashMap<String, String>>();
            mStops = new ArrayList<ArrayList<HashMap<String, String>>>();
            mStopMap = new HashMap<String, ObaStop>();
            mResultCode = response.getCode();
            if (!ListenerUtil.mutListener.listen(4858)) {
                initMaps(cxt, response);
            }
        }

        private static Map<String, ObaStop> getStopMap(List<ObaStop> stops) {
            final int len = stops.size();
            HashMap<String, ObaStop> result = new HashMap<String, ObaStop>(len);
            if (!ListenerUtil.mutListener.listen(4865)) {
                {
                    long _loopCounter35 = 0;
                    for (int i = 0; (ListenerUtil.mutListener.listen(4864) ? (i >= len) : (ListenerUtil.mutListener.listen(4863) ? (i <= len) : (ListenerUtil.mutListener.listen(4862) ? (i > len) : (ListenerUtil.mutListener.listen(4861) ? (i != len) : (ListenerUtil.mutListener.listen(4860) ? (i == len) : (i < len)))))); ++i) {
                        ListenerUtil.loopListener.listen("_loopCounter35", ++_loopCounter35);
                        ObaStop stop = stops.get(i);
                        if (!ListenerUtil.mutListener.listen(4859)) {
                            result.put(stop.getId(), stop);
                        }
                    }
                }
            }
            return result;
        }

        private void initMaps(Context cxt, ObaStopsForRouteResponse response) {
            if (!ListenerUtil.mutListener.listen(4896)) {
                // and should include all the entries specified in "childFrom"
                if (response.getCode() == ObaApi.OBA_OK) {
                    final List<ObaStop> stops = response.getStops();
                    final Map<String, ObaStop> stopMap = getStopMap(stops);
                    final ObaStopGrouping[] groupings = response.getStopGroupings();
                    final int groupingsLen = groupings.length;
                    if (!ListenerUtil.mutListener.listen(4895)) {
                        {
                            long _loopCounter38 = 0;
                            for (int groupingIndex = 0; (ListenerUtil.mutListener.listen(4894) ? (groupingIndex >= groupingsLen) : (ListenerUtil.mutListener.listen(4893) ? (groupingIndex <= groupingsLen) : (ListenerUtil.mutListener.listen(4892) ? (groupingIndex > groupingsLen) : (ListenerUtil.mutListener.listen(4891) ? (groupingIndex != groupingsLen) : (ListenerUtil.mutListener.listen(4890) ? (groupingIndex == groupingsLen) : (groupingIndex < groupingsLen)))))); ++groupingIndex) {
                                ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                                final ObaStopGrouping grouping = groupings[groupingIndex];
                                final ObaStopGroup[] groups = grouping.getStopGroups();
                                final int groupsLen = groups.length;
                                if (!ListenerUtil.mutListener.listen(4889)) {
                                    {
                                        long _loopCounter37 = 0;
                                        for (int i = 0; (ListenerUtil.mutListener.listen(4888) ? (i >= groupsLen) : (ListenerUtil.mutListener.listen(4887) ? (i <= groupsLen) : (ListenerUtil.mutListener.listen(4886) ? (i > groupsLen) : (ListenerUtil.mutListener.listen(4885) ? (i != groupsLen) : (ListenerUtil.mutListener.listen(4884) ? (i == groupsLen) : (i < groupsLen)))))); ++i) {
                                            ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                                            final HashMap<String, String> groupMap = new HashMap<String, String>(1);
                                            final ObaStopGroup group = groups[i];
                                            if (!ListenerUtil.mutListener.listen(4866)) {
                                                // We can initialize the stop grouping values.
                                                groupMap.put("name", UIUtils.formatDisplayText(group.getName()));
                                            }
                                            // Create the sub list (the list of stops in the group)
                                            final String[] stopIds = group.getStopIds();
                                            final int stopIdLen = stopIds.length;
                                            final ArrayList<HashMap<String, String>> childList = new ArrayList<HashMap<String, String>>(stopIdLen);
                                            if (!ListenerUtil.mutListener.listen(4881)) {
                                                {
                                                    long _loopCounter36 = 0;
                                                    for (int j = 0; (ListenerUtil.mutListener.listen(4880) ? (j >= stopIdLen) : (ListenerUtil.mutListener.listen(4879) ? (j <= stopIdLen) : (ListenerUtil.mutListener.listen(4878) ? (j > stopIdLen) : (ListenerUtil.mutListener.listen(4877) ? (j != stopIdLen) : (ListenerUtil.mutListener.listen(4876) ? (j == stopIdLen) : (j < stopIdLen)))))); ++j) {
                                                        ListenerUtil.loopListener.listen("_loopCounter36", ++_loopCounter36);
                                                        final String stopId = stopIds[j];
                                                        final ObaStop stop = stopMap.get(stopId);
                                                        HashMap<String, String> groupStopMap = new HashMap<String, String>(2);
                                                        if (!ListenerUtil.mutListener.listen(4874)) {
                                                            if (stop != null) {
                                                                if (!ListenerUtil.mutListener.listen(4870)) {
                                                                    groupStopMap.put("name", UIUtils.formatDisplayText(stop.getName()));
                                                                }
                                                                String dir = cxt.getString(UIUtils.getStopDirectionText(stop.getDirection()));
                                                                if (!ListenerUtil.mutListener.listen(4871)) {
                                                                    groupStopMap.put("direction", dir);
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(4872)) {
                                                                    groupStopMap.put("id", stopId);
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(4873)) {
                                                                    mStopMap.put(stopId, stop);
                                                                }
                                                            } else {
                                                                if (!ListenerUtil.mutListener.listen(4867)) {
                                                                    groupStopMap.put("name", "");
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(4868)) {
                                                                    groupStopMap.put("direction", "");
                                                                }
                                                                if (!ListenerUtil.mutListener.listen(4869)) {
                                                                    groupStopMap.put("id", stopId);
                                                                }
                                                            }
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(4875)) {
                                                            childList.add(groupStopMap);
                                                        }
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(4882)) {
                                                mStopGroups.add(groupMap);
                                            }
                                            if (!ListenerUtil.mutListener.listen(4883)) {
                                                mStops.add(childList);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        public int getResultCode() {
            return mResultCode;
        }

        public ArrayList<HashMap<String, String>> getStopGroups() {
            return mStopGroups;
        }

        public ArrayList<ArrayList<HashMap<String, String>>> getStops() {
            return mStops;
        }

        public HashMap<String, ObaStop> getStopMap() {
            return mStopMap;
        }
    }

    // 
    private void setHeader(ObaRouteResponse routeInfo, boolean addToDb) {
        if (!ListenerUtil.mutListener.listen(4897)) {
            mRouteInfo = routeInfo;
        }
        View view = getView();
        if (!ListenerUtil.mutListener.listen(4914)) {
            if (routeInfo.getCode() == ObaApi.OBA_OK) {
                TextView shortNameText = (TextView) view.findViewById(R.id.short_name);
                TextView longNameText = (TextView) view.findViewById(R.id.long_name);
                TextView agencyText = (TextView) view.findViewById(R.id.agency);
                String url = mRouteInfo.getUrl();
                String shortName = routeInfo.getShortName();
                String longName = routeInfo.getLongName();
                if (!ListenerUtil.mutListener.listen(4900)) {
                    if (TextUtils.isEmpty(shortName)) {
                        if (!ListenerUtil.mutListener.listen(4899)) {
                            shortName = longName;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4903)) {
                    if ((ListenerUtil.mutListener.listen(4901) ? (TextUtils.isEmpty(longName) && shortName.equals(longName)) : (TextUtils.isEmpty(longName) || shortName.equals(longName)))) {
                        if (!ListenerUtil.mutListener.listen(4902)) {
                            longName = routeInfo.getDescription();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4904)) {
                    shortNameText.setText(UIUtils.formatDisplayText(shortName));
                }
                if (!ListenerUtil.mutListener.listen(4905)) {
                    longNameText.setText(UIUtils.formatDisplayText(longName));
                }
                if (!ListenerUtil.mutListener.listen(4906)) {
                    agencyText.setText(mRouteInfo.getAgency().getName());
                }
                if (!ListenerUtil.mutListener.listen(4913)) {
                    if (addToDb) {
                        ContentValues values = new ContentValues();
                        if (!ListenerUtil.mutListener.listen(4907)) {
                            values.put(ObaContract.Routes.SHORTNAME, shortName);
                        }
                        if (!ListenerUtil.mutListener.listen(4908)) {
                            values.put(ObaContract.Routes.LONGNAME, longName);
                        }
                        if (!ListenerUtil.mutListener.listen(4909)) {
                            values.put(ObaContract.Routes.URL, url);
                        }
                        if (!ListenerUtil.mutListener.listen(4911)) {
                            if (Application.get().getCurrentRegion() != null) {
                                if (!ListenerUtil.mutListener.listen(4910)) {
                                    values.put(ObaContract.Routes.REGION_ID, Application.get().getCurrentRegion().getId());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(4912)) {
                            ObaContract.Routes.insertOrUpdate(getActivity(), mRouteInfo.getId(), values, true);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4898)) {
                    setEmptyText(UIUtils.getRouteErrorString(getActivity(), routeInfo.getCode()));
                }
            }
        }
    }

    private void setStopsForRoute(StopsForRouteInfo result) {
        if (!ListenerUtil.mutListener.listen(4915)) {
            mStopsForRoute = result;
        }
        final int code = mStopsForRoute.getResultCode();
        if (!ListenerUtil.mutListener.listen(4918)) {
            if (code == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(4917)) {
                    setEmptyText("");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4916)) {
                    setEmptyText(UIUtils.getRouteErrorString(getActivity(), code));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4919)) {
            mAdapter = new SimpleExpandableListAdapter(getActivity(), result.getStopGroups(), android.R.layout.simple_expandable_list_item_1, new String[] { "name" }, new int[] { android.R.id.text1 }, result.getStops(), R.layout.route_info_listitem, new String[] { "name", "direction", "id" }, new int[] { R.id.name, R.id.direction, R.id.stop_id });
        }
        if (!ListenerUtil.mutListener.listen(4920)) {
            setListAdapter(mAdapter);
        }
    }

    public void setListAdapter(SimpleExpandableListAdapter adapter) {
        ExpandableListView list = (ExpandableListView) getListView();
        if (!ListenerUtil.mutListener.listen(4923)) {
            if (list != null) {
                if (!ListenerUtil.mutListener.listen(4921)) {
                    list.setAdapter(adapter);
                }
                if (!ListenerUtil.mutListener.listen(4922)) {
                    setListShown(true);
                }
            }
        }
    }
}
