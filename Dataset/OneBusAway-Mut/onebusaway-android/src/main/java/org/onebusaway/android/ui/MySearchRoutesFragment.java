/*
 * Copyright (C) 2010-2017 Paul Watts (paulcwatts@gmail.com),
 * University of South  Florida (sjbarbeau@gmail.com)
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
import org.onebusaway.android.io.ObaApi;
import org.onebusaway.android.io.elements.ObaRoute;
import org.onebusaway.android.io.request.ObaRoutesForLocationRequest;
import org.onebusaway.android.io.request.ObaRoutesForLocationResponse;
import org.onebusaway.android.util.ArrayAdapter;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.UIUtils;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Arrays;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MySearchRoutesFragment extends MySearchFragmentBase implements LoaderManager.LoaderCallbacks<ObaRoutesForLocationResponse> {

    // private static final String TAG = "MySearchRoutesActivity";
    private static final String QUERY_TEXT = "query_text";

    public static final String TAB_NAME = "search";

    private MyAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3074)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3075)) {
            mAdapter = new MyAdapter(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(3076)) {
            setListAdapter(mAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3077)) {
            if (root == null) {
                // reason to create our view.
                return null;
            }
        }
        return inflater.inflate(R.layout.my_search_route_list, null);
    }

    @Override
    public Loader<ObaRoutesForLocationResponse> onCreateLoader(int id, Bundle args) {
        String query = args.getString(QUERY_TEXT);
        return new MyLoader(getActivity(), query, getSearchCenter());
    }

    @Override
    public void onLoadFinished(Loader<ObaRoutesForLocationResponse> loader, ObaRoutesForLocationResponse response) {
        if (!ListenerUtil.mutListener.listen(3078)) {
            UIUtils.showProgress(this, false);
        }
        // Log.d(TAG, "Loader finished");
        final int code = response.getCode();
        if (!ListenerUtil.mutListener.listen(3088)) {
            if (code == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(3086)) {
                    setEmptyText(getString(R.string.find_hint_noresults));
                }
                if (!ListenerUtil.mutListener.listen(3087)) {
                    mAdapter.setData(Arrays.asList(response.getRoutesForLocation()));
                }
            } else if ((ListenerUtil.mutListener.listen(3083) ? (code >= 0) : (ListenerUtil.mutListener.listen(3082) ? (code <= 0) : (ListenerUtil.mutListener.listen(3081) ? (code > 0) : (ListenerUtil.mutListener.listen(3080) ? (code < 0) : (ListenerUtil.mutListener.listen(3079) ? (code == 0) : (code != 0))))))) {
                if (!ListenerUtil.mutListener.listen(3085)) {
                    // a 'communication' error. Just fake no results.
                    setEmptyText(getString(R.string.find_hint_noresults));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3084)) {
                    setEmptyText(getString(R.string.generic_comm_error));
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<ObaRoutesForLocationResponse> loader) {
        if (!ListenerUtil.mutListener.listen(3089)) {
            mAdapter.clear();
        }
    }

    // 
    @Override
    protected void doSearch(String text) {
        if (!ListenerUtil.mutListener.listen(3090)) {
            UIUtils.showProgress(this, true);
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(3091)) {
            args.putString(QUERY_TEXT, text);
        }
        Loader<?> loader = getLoaderManager().restartLoader(0, args, this);
        if (!ListenerUtil.mutListener.listen(3092)) {
            loader.onContentChanged();
        }
    }

    @Override
    protected int getEditBoxHintText() {
        return R.string.search_route_hint;
    }

    @Override
    protected int getMinSearchLength() {
        return 1;
    }

    @Override
    protected CharSequence getHintText() {
        return getString(R.string.find_hint_nofavoriteroutes);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Get the adapter (this may or may not be a SimpleCursorAdapter)
        ListAdapter adapter = l.getAdapter();
        ObaRoute route = (ObaRoute) adapter.getItem((ListenerUtil.mutListener.listen(3096) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3095) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3094) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3093) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        final String routeId = route.getId();
        final String routeName = UIUtils.getRouteDisplayName(route);
        if (!ListenerUtil.mutListener.listen(3100)) {
            if (isShortcutMode()) {
                final ShortcutInfoCompat shortcut = UIUtils.createRouteShortcut(getContext(), routeId, routeName);
                Activity activity = getActivity();
                if (!ListenerUtil.mutListener.listen(3098)) {
                    activity.setResult(Activity.RESULT_OK, shortcut.getIntent());
                }
                if (!ListenerUtil.mutListener.listen(3099)) {
                    activity.finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3097)) {
                    RouteInfoActivity.start(getActivity(), routeId);
                }
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(3101)) {
            super.onCreateContextMenu(menu, v, menuInfo);
        }
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final TextView text = (TextView) info.targetView.findViewById(R.id.short_name);
        if (!ListenerUtil.mutListener.listen(3102)) {
            menu.setHeaderTitle(getString(R.string.route_name, text.getText()));
        }
        if (!ListenerUtil.mutListener.listen(3105)) {
            if (isShortcutMode()) {
                if (!ListenerUtil.mutListener.listen(3104)) {
                    menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.my_context_create_shortcut);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3103)) {
                    menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.my_context_get_route_info);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3106)) {
            menu.add(0, CONTEXT_MENU_SHOW_ON_MAP, 0, R.string.my_context_showonmap);
        }
        final String url = getUrl(getListView(), info.position);
        if (!ListenerUtil.mutListener.listen(3108)) {
            if (url != null) {
                if (!ListenerUtil.mutListener.listen(3107)) {
                    menu.add(0, CONTEXT_MENU_SHOW_URL, 0, R.string.my_context_show_schedule);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3109)) {
            menu.add(0, CONTEXT_MENU_CREATE_SHORTCUT, 0, R.string.my_context_create_shortcut);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case CONTEXT_MENU_DEFAULT:
                if (!ListenerUtil.mutListener.listen(3110)) {
                    // Fake a click
                    onListItemClick(getListView(), info.targetView, info.position, info.id);
                }
                return true;
            case CONTEXT_MENU_SHOW_ON_MAP:
                if (!ListenerUtil.mutListener.listen(3111)) {
                    HomeActivity.start(getActivity(), getId(getListView(), info.position));
                }
                return true;
            case CONTEXT_MENU_SHOW_URL:
                if (!ListenerUtil.mutListener.listen(3112)) {
                    UIUtils.goToUrl(getActivity(), getUrl(getListView(), info.position));
                }
                return true;
            case CONTEXT_MENU_CREATE_SHORTCUT:
                String id = QueryUtils.RouteList.getId(getListView(), info.position);
                String shortName = QueryUtils.RouteList.getShortName(getListView(), info.position);
                if (!ListenerUtil.mutListener.listen(3113)) {
                    UIUtils.createRouteShortcut(getContext(), id, shortName);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private String getId(ListView l, int position) {
        ListAdapter adapter = l.getAdapter();
        ObaRoute route = (ObaRoute) adapter.getItem((ListenerUtil.mutListener.listen(3117) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3116) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3115) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3114) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        return route.getId();
    }

    private String getUrl(ListView l, int position) {
        ListAdapter adapter = l.getAdapter();
        ObaRoute route = (ObaRoute) adapter.getItem((ListenerUtil.mutListener.listen(3121) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3120) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3119) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3118) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        return route.getUrl();
    }

    // 
    private static final class MyAdapter extends ArrayAdapter<ObaRoute> {

        public MyAdapter(Context context) {
            super(context, R.layout.route_list_item);
        }

        @Override
        protected void initView(View view, ObaRoute route) {
            if (!ListenerUtil.mutListener.listen(3122)) {
                UIUtils.setRouteView(view, route);
            }
        }
    }

    // 
    private static final class MyLoader extends AsyncTaskLoader<ObaRoutesForLocationResponse> {

        private final String mQueryText;

        private final Location mCenter;

        public MyLoader(Context context, String query, Location center) {
            super(context);
            mQueryText = query;
            mCenter = center;
        }

        @Override
        public ObaRoutesForLocationResponse loadInBackground() {
            ObaRoutesForLocationResponse response = new ObaRoutesForLocationRequest.Builder(getContext(), mCenter).setQuery(mQueryText).build().call();
            if (!ListenerUtil.mutListener.listen(3124)) {
                // Log.d(TAG, "Server returns: " + response.getCode());
                if (response.getCode() == ObaApi.OBA_OK) {
                    ObaRoute[] routes = response.getRoutesForLocation();
                    if (!ListenerUtil.mutListener.listen(3123)) {
                        if (routes.length != 0) {
                            return response;
                        }
                    }
                }
            }
            Location center = LocationUtils.getDefaultSearchCenter();
            if (!ListenerUtil.mutListener.listen(3125)) {
                if (center != null) {
                    return new ObaRoutesForLocationRequest.Builder(getContext(), center).setRadius(LocationUtils.DEFAULT_SEARCH_RADIUS).setQuery(mQueryText).build().call();
                }
            }
            return response;
        }
    }
}
