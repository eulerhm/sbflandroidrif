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
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.request.ObaStopsForLocationRequest;
import org.onebusaway.android.io.request.ObaStopsForLocationResponse;
import org.onebusaway.android.util.ArrayAdapter;
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
import android.widget.ListView;
import android.widget.TextView;
import java.util.Arrays;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MySearchStopsFragment extends MySearchFragmentBase implements LoaderManager.LoaderCallbacks<ObaStopsForLocationResponse> {

    // private static final String TAG = "MySearchStopsFragment";
    private static final String QUERY_TEXT = "query_text";

    public static final String TAB_NAME = "search";

    private UIUtils.StopUserInfoMap mStopUserMap;

    private MyAdapter mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4771)) {
            mStopUserMap = new UIUtils.StopUserInfoMap(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(4772)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4773)) {
            mAdapter = new MyAdapter();
        }
        if (!ListenerUtil.mutListener.listen(4774)) {
            setListAdapter(mAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4775)) {
            if (root == null) {
                // reason to create our view.
                return null;
            }
        }
        return inflater.inflate(R.layout.my_search_stop_list, null);
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(4777)) {
            if (mStopUserMap != null) {
                if (!ListenerUtil.mutListener.listen(4776)) {
                    mStopUserMap.close();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4778)) {
            super.onDestroy();
        }
    }

    @Override
    public Loader<ObaStopsForLocationResponse> onCreateLoader(int id, Bundle args) {
        String query = args.getString(QUERY_TEXT);
        return new MyLoader(getActivity(), query, getSearchCenter());
    }

    @Override
    public void onLoadFinished(Loader<ObaStopsForLocationResponse> loader, ObaStopsForLocationResponse response) {
        if (!ListenerUtil.mutListener.listen(4779)) {
            UIUtils.showProgress(this, false);
        }
        // Log.d(TAG, "Loader finished");
        final int code = response.getCode();
        if (!ListenerUtil.mutListener.listen(4789)) {
            if (code == ObaApi.OBA_OK) {
                if (!ListenerUtil.mutListener.listen(4787)) {
                    setEmptyText(getString(R.string.find_hint_noresults));
                }
                if (!ListenerUtil.mutListener.listen(4788)) {
                    mAdapter.setData(Arrays.asList(response.getStops()));
                }
            } else if ((ListenerUtil.mutListener.listen(4784) ? (code >= 0) : (ListenerUtil.mutListener.listen(4783) ? (code <= 0) : (ListenerUtil.mutListener.listen(4782) ? (code > 0) : (ListenerUtil.mutListener.listen(4781) ? (code < 0) : (ListenerUtil.mutListener.listen(4780) ? (code == 0) : (code != 0))))))) {
                if (!ListenerUtil.mutListener.listen(4786)) {
                    // a 'communication' error. Just fake no results.
                    setEmptyText(getString(R.string.find_hint_noresults));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4785)) {
                    setEmptyText(getString(R.string.generic_comm_error));
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<ObaStopsForLocationResponse> loader) {
        if (!ListenerUtil.mutListener.listen(4790)) {
            mAdapter.clear();
        }
    }

    // 
    @Override
    protected void doSearch(String text) {
        if (!ListenerUtil.mutListener.listen(4791)) {
            UIUtils.showProgress(this, true);
        }
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(4792)) {
            args.putString(QUERY_TEXT, text);
        }
        Loader<?> loader = getLoaderManager().restartLoader(0, args, this);
        if (!ListenerUtil.mutListener.listen(4793)) {
            loader.onContentChanged();
        }
    }

    @Override
    protected int getEditBoxHintText() {
        return R.string.search_stop_hint;
    }

    @Override
    protected int getMinSearchLength() {
        return 5;
    }

    @Override
    protected CharSequence getHintText() {
        return getText(R.string.find_hint_nofavoritestops);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ObaStop stop = (ObaStop) l.getAdapter().getItem((ListenerUtil.mutListener.listen(4797) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(4796) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(4795) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(4794) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        ArrivalsListActivity.Builder b = new ArrivalsListActivity.Builder(getActivity(), stop.getId());
        if (!ListenerUtil.mutListener.listen(4798)) {
            b.setStopName(stop.getName());
        }
        if (!ListenerUtil.mutListener.listen(4799)) {
            b.setStopDirection(stop.getDirection());
        }
        if (!ListenerUtil.mutListener.listen(4804)) {
            if (isShortcutMode()) {
                final ShortcutInfoCompat shortcut = UIUtils.createStopShortcut(getContext(), stop.getName(), b);
                Activity activity = getActivity();
                if (!ListenerUtil.mutListener.listen(4802)) {
                    activity.setResult(Activity.RESULT_OK, shortcut.getIntent());
                }
                if (!ListenerUtil.mutListener.listen(4803)) {
                    activity.finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4800)) {
                    b.setUpMode(NavHelp.UP_MODE_BACK);
                }
                if (!ListenerUtil.mutListener.listen(4801)) {
                    b.start();
                }
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(4805)) {
            super.onCreateContextMenu(menu, v, menuInfo);
        }
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final TextView text = (TextView) info.targetView.findViewById(R.id.stop_name);
        if (!ListenerUtil.mutListener.listen(4806)) {
            menu.setHeaderTitle(UIUtils.formatDisplayText(text.getText().toString()));
        }
        if (!ListenerUtil.mutListener.listen(4809)) {
            if (isShortcutMode()) {
                if (!ListenerUtil.mutListener.listen(4808)) {
                    menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.my_context_create_shortcut);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4807)) {
                    menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.my_context_get_stop_info);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4810)) {
            menu.add(0, CONTEXT_MENU_SHOW_ON_MAP, 0, R.string.my_context_showonmap);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case CONTEXT_MENU_DEFAULT:
                if (!ListenerUtil.mutListener.listen(4811)) {
                    // Fake a click
                    onListItemClick(getListView(), info.targetView, info.position, info.id);
                }
                return true;
            case CONTEXT_MENU_SHOW_ON_MAP:
                if (!ListenerUtil.mutListener.listen(4812)) {
                    showOnMap(getListView(), info.position);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showOnMap(ListView l, int position) {
        ObaStop stop = (ObaStop) l.getAdapter().getItem((ListenerUtil.mutListener.listen(4816) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(4815) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(4814) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(4813) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        final String stopId = stop.getId();
        final double lat = stop.getLatitude();
        final double lon = stop.getLongitude();
        if (!ListenerUtil.mutListener.listen(4817)) {
            HomeActivity.start(getActivity(), stopId, lat, lon);
        }
    }

    // 
    private final class MyAdapter extends ArrayAdapter<ObaStop> {

        public MyAdapter() {
            super(getActivity(), R.layout.stop_list_item);
        }

        @Override
        protected void initView(View view, ObaStop stop) {
            if (!ListenerUtil.mutListener.listen(4818)) {
                mStopUserMap.setView(view, stop.getId(), stop.getName());
            }
            if (!ListenerUtil.mutListener.listen(4819)) {
                UIUtils.setStopDirection(view.findViewById(R.id.direction), stop.getDirection(), true);
            }
        }
    }

    // 
    private static final class MyLoader extends AsyncTaskLoader<ObaStopsForLocationResponse> {

        private final String mQueryText;

        private final Location mCenter;

        public MyLoader(Context context, String query, Location center) {
            super(context);
            mQueryText = query;
            mCenter = center;
        }

        @Override
        public ObaStopsForLocationResponse loadInBackground() {
            return new ObaStopsForLocationRequest.Builder(getContext(), mCenter).setQuery(mQueryText).build().call();
        }
    }
}
