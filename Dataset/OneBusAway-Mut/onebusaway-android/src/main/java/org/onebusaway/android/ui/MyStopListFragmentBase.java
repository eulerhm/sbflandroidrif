/*
 * Copyright (C) 2010-2012 Paul Watts (paulcwatts@gmail.com)
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
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.ShowcaseViewUtils;
import org.onebusaway.android.util.UIUtils;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

abstract class MyStopListFragmentBase extends MyListFragmentBase implements QueryUtils.StopList.Columns {

    @Override
    protected SimpleCursorAdapter newAdapter() {
        return QueryUtils.StopList.newAdapter(getActivity());
    }

    @Override
    protected Uri getContentUri() {
        return ObaContract.Stops.CONTENT_URI;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        StopData stopData = getStopData(l, position);
        ArrivalsListActivity.Builder b = stopData.getArrivalsList();
        if (!ListenerUtil.mutListener.listen(1908)) {
            if (isShortcutMode()) {
                final ShortcutInfoCompat shortcut = UIUtils.createStopShortcut(getContext(), stopData.getUiName(), b);
                Activity activity = getActivity();
                if (!ListenerUtil.mutListener.listen(1906)) {
                    activity.setResult(Activity.RESULT_OK, shortcut.getIntent());
                }
                if (!ListenerUtil.mutListener.listen(1907)) {
                    activity.finish();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1904)) {
                    b.setUpMode(NavHelp.UP_MODE_BACK);
                }
                if (!ListenerUtil.mutListener.listen(1905)) {
                    b.start();
                }
            }
        }
    }

    protected StopData getStopData(ListView l, int position) {
        // Get the cursor and fetch the stop ID from that.
        SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) l.getAdapter();
        return new StopData(cursorAdapter.getCursor(), (ListenerUtil.mutListener.listen(1912) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(1911) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(1910) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(1909) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(1913)) {
            super.onCreateContextMenu(menu, v, menuInfo);
        }
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final TextView text = (TextView) info.targetView.findViewById(R.id.stop_name);
        if (!ListenerUtil.mutListener.listen(1914)) {
            menu.setHeaderTitle(UIUtils.formatDisplayText(text.getText().toString()));
        }
        if (!ListenerUtil.mutListener.listen(1917)) {
            if (isShortcutMode()) {
                if (!ListenerUtil.mutListener.listen(1916)) {
                    menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.my_context_create_shortcut);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1915)) {
                    menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.my_context_get_stop_info);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1918)) {
            menu.add(0, CONTEXT_MENU_SHOW_ON_MAP, 0, R.string.my_context_showonmap);
        }
        if (!ListenerUtil.mutListener.listen(1920)) {
            if (!isShortcutMode()) {
                if (!ListenerUtil.mutListener.listen(1919)) {
                    menu.add(0, CONTEXT_MENU_CREATE_SHORTCUT, 0, R.string.my_context_create_shortcut);
                }
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case CONTEXT_MENU_DEFAULT:
                if (!ListenerUtil.mutListener.listen(1921)) {
                    // Fake a click
                    onListItemClick(getListView(), info.targetView, info.position, info.id);
                }
                return true;
            case CONTEXT_MENU_SHOW_ON_MAP:
                if (!ListenerUtil.mutListener.listen(1922)) {
                    showOnMap(getListView(), info.position);
                }
                return true;
            case CONTEXT_MENU_CREATE_SHORTCUT:
                if (!ListenerUtil.mutListener.listen(1923)) {
                    ShowcaseViewUtils.doNotShowTutorial(ShowcaseViewUtils.TUTORIAL_STARRED_STOPS_SHORTCUT);
                }
                StopData stopData = getStopData(getListView(), info.position);
                if (!ListenerUtil.mutListener.listen(1924)) {
                    UIUtils.createStopShortcut(getContext(), stopData.uiName, stopData.getArrivalsList());
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showOnMap(ListView l, int position) {
        // Get the cursor and fetch the stop ID from that.
        SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) l.getAdapter();
        Cursor c = cursorAdapter.getCursor();
        if (!ListenerUtil.mutListener.listen(1929)) {
            c.moveToPosition((ListenerUtil.mutListener.listen(1928) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(1927) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(1926) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(1925) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        }
        final String stopId = c.getString(COL_ID);
        final double lat = c.getDouble(COL_LATITUDE);
        final double lon = c.getDouble(COL_LONGITUDE);
        if (!ListenerUtil.mutListener.listen(1930)) {
            HomeActivity.start(getActivity(), stopId, lat, lon);
        }
    }

    protected class StopData {

        private final String id;

        private final String name;

        private final String dir;

        private final String uiName;

        public StopData(Cursor c, int row) {
            if (!ListenerUtil.mutListener.listen(1931)) {
                c.moveToPosition(row);
            }
            id = c.getString(COL_ID);
            name = c.getString(COL_NAME);
            dir = c.getString(COL_DIRECTION);
            uiName = c.getString(COL_UI_NAME);
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDir() {
            return dir;
        }

        public String getUiName() {
            return uiName;
        }

        public ArrivalsListActivity.Builder getArrivalsList() {
            return new ArrivalsListActivity.Builder(getActivity(), id).setStopName(name).setStopDirection(dir);
        }
    }
}
