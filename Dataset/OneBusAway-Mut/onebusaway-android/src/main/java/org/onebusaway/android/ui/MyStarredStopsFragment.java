/*
 * Copyright (C) 2012-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida
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

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyStarredStopsFragment extends MyStopListFragmentBase {

    public static final String TAG = "MyStarredStopsFragment";

    public static final String TAB_NAME = "starred";

    private static String sortBy;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2269)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Set the sort by clause, in case its the first execution and none is set
        final int currentStopOrder = PreferenceUtils.getStopSortOrderFromPreferences();
        if (!ListenerUtil.mutListener.listen(2270)) {
            setSortByClause(currentStopOrder);
        }
        return new CursorLoader(getActivity(), ObaContract.Stops.CONTENT_URI, PROJECTION, ObaContract.Stops.FAVORITE + "=1" + (Application.get().getCurrentRegion() == null ? "" : " AND " + QueryUtils.getRegionWhere(ObaContract.Stops.REGION_ID, Application.get().getCurrentRegion().getId())), null, sortBy);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(2271)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(2272)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(2273)) {
            showStarredStopsTutorials();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!ListenerUtil.mutListener.listen(2274)) {
            super.onHiddenChanged(hidden);
        }
        if (!ListenerUtil.mutListener.listen(2276)) {
            if (!hidden) {
                if (!ListenerUtil.mutListener.listen(2275)) {
                    showStarredStopsTutorials();
                }
            }
        }
    }

    /**
     * Show the sort starred stops tutorial if we have more than 1 starred stop
     */
    private void showStarredStopsTutorials() {
        if (!ListenerUtil.mutListener.listen(2277)) {
            if (!isVisible()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(2284)) {
            if ((ListenerUtil.mutListener.listen(2282) ? (mAdapter.getCount() >= 0) : (ListenerUtil.mutListener.listen(2281) ? (mAdapter.getCount() <= 0) : (ListenerUtil.mutListener.listen(2280) ? (mAdapter.getCount() < 0) : (ListenerUtil.mutListener.listen(2279) ? (mAdapter.getCount() != 0) : (ListenerUtil.mutListener.listen(2278) ? (mAdapter.getCount() == 0) : (mAdapter.getCount() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(2283)) {
                    ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_STARRED_STOPS_SHORTCUT, (AppCompatActivity) getActivity(), null, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2291)) {
            if ((ListenerUtil.mutListener.listen(2289) ? (mAdapter.getCount() >= 1) : (ListenerUtil.mutListener.listen(2288) ? (mAdapter.getCount() <= 1) : (ListenerUtil.mutListener.listen(2287) ? (mAdapter.getCount() < 1) : (ListenerUtil.mutListener.listen(2286) ? (mAdapter.getCount() != 1) : (ListenerUtil.mutListener.listen(2285) ? (mAdapter.getCount() == 1) : (mAdapter.getCount() > 1))))))) {
                if (!ListenerUtil.mutListener.listen(2290)) {
                    ShowcaseViewUtils.showTutorial(ShowcaseViewUtils.TUTORIAL_STARRED_STOPS_SORT, (AppCompatActivity) getActivity(), null, false);
                }
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(2292)) {
            super.onCreateContextMenu(menu, v, menuInfo);
        }
        if (!ListenerUtil.mutListener.listen(2293)) {
            menu.add(0, CONTEXT_MENU_DELETE, 0, R.string.my_context_remove_star);
        }
    }

    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case CONTEXT_MENU_DELETE:
                final String id = QueryUtils.StopList.getId(getListView(), info.position);
                final Uri uri = Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(2294)) {
                    ObaContract.Stops.markAsFavorite(getActivity(), uri, false);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(2295)) {
            inflater.inflate(R.menu.my_starred_stop_options, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(2299)) {
            if (id == R.id.clear_starred) {
                if (!ListenerUtil.mutListener.listen(2298)) {
                    new ClearDialog().show(getActivity().getSupportFragmentManager(), "confirm_clear_starred_stops");
                }
                return true;
            } else if (id == R.id.sort_stops) {
                if (!ListenerUtil.mutListener.listen(2296)) {
                    ShowcaseViewUtils.doNotShowTutorial(ShowcaseViewUtils.TUTORIAL_STARRED_STOPS_SORT);
                }
                if (!ListenerUtil.mutListener.listen(2297)) {
                    showSortByDialog();
                }
            }
        }
        return false;
    }

    private void showSortByDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(2300)) {
            builder.setTitle(R.string.menu_option_sort_by);
        }
        final int currentStopOrder = PreferenceUtils.getStopSortOrderFromPreferences();
        if (!ListenerUtil.mutListener.listen(2305)) {
            builder.setSingleChoiceItems(R.array.sort_stops, currentStopOrder, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int index) {
                    if (!ListenerUtil.mutListener.listen(2303)) {
                        // If the user picked a different option, change the sort order
                        if (currentStopOrder != index) {
                            if (!ListenerUtil.mutListener.listen(2301)) {
                                setSortByClause(index);
                            }
                            if (!ListenerUtil.mutListener.listen(2302)) {
                                // Restart the loader with the new sorting
                                getLoaderManager().restartLoader(0, null, MyStarredStopsFragment.this);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2304)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(2306)) {
            dialog.setOwnerActivity(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(2307)) {
            dialog.show();
        }
    }

    /**
     * Sets the "sort by" string for ordering the stops, based on the given index of
     * R.array.sort_stops.  It also saves the sort by order to preferences.
     *
     * @param index the index of R.array.sort_stops that should be set
     */
    private void setSortByClause(int index) {
        if (!ListenerUtil.mutListener.listen(2314)) {
            switch(index) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(2308)) {
                        // Sort by name
                        Log.d(TAG, "Sort by name");
                    }
                    if (!ListenerUtil.mutListener.listen(2309)) {
                        sortBy = ObaContract.Stops.UI_NAME + " asc";
                    }
                    if (!ListenerUtil.mutListener.listen(2310)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_name_stops), null);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(2311)) {
                        // Sort by frequently used
                        Log.d(TAG, "Sort by frequently used");
                    }
                    if (!ListenerUtil.mutListener.listen(2312)) {
                        sortBy = ObaContract.Stops.USE_COUNT + " desc";
                    }
                    if (!ListenerUtil.mutListener.listen(2313)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_mfu_stops), null);
                    }
                    break;
            }
        }
        // Set the sort option to preferences
        final String[] sortOptions = getResources().getStringArray(R.array.sort_stops);
        if (!ListenerUtil.mutListener.listen(2315)) {
            PreferenceUtils.saveString(getResources().getString(R.string.preference_key_default_stop_sort), sortOptions[index]);
        }
    }

    @Override
    protected int getEmptyText() {
        return R.string.my_no_starred_stops;
    }

    public static class ClearDialog extends ClearConfirmDialog {

        @Override
        protected void doClear() {
            if (!ListenerUtil.mutListener.listen(2316)) {
                ObaContract.Stops.markAsFavorite(getActivity(), ObaContract.Stops.CONTENT_URI, false);
            }
            if (!ListenerUtil.mutListener.listen(2317)) {
                ObaAnalytics.reportUiEvent(FirebaseAnalytics.getInstance(getContext()), getString(R.string.analytics_label_edit_field_bookmark_delete), null);
            }
        }
    }
}
