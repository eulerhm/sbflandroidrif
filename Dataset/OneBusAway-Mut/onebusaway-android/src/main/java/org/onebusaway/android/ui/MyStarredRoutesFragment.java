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
 *
 * Modifications copyright (C) 2023 Millan Philipose, University of Washington.
 * This file is adapted from MyStarredStopsFragment, to display starred routes rather than stops.
 */
package org.onebusaway.android.ui;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
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
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MyStarredRoutesFragment extends MyRouteListFragmentBase {

    public static final String TAG = "MyStarredRoutesFragment";

    public static final String TAB_NAME = "starred_rts";

    private static String sortBy;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(707)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // We use the same sorting preference (name vs most recent) as is used for sorting routes.
        final int currentRouteOrder = PreferenceUtils.getStopSortOrderFromPreferences();
        if (!ListenerUtil.mutListener.listen(708)) {
            setSortByClause(currentRouteOrder);
        }
        return new CursorLoader(getActivity(), ObaContract.Routes.CONTENT_URI, PROJECTION, ObaContract.Routes.FAVORITE + "=1" + (Application.get().getCurrentRegion() == null ? "" : " AND " + QueryUtils.getRegionWhere(ObaContract.Routes.REGION_ID, Application.get().getCurrentRegion().getId())), null, sortBy);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(709)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(710)) {
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(711)) {
            super.onCreateContextMenu(menu, v, menuInfo);
        }
        if (!ListenerUtil.mutListener.listen(712)) {
            menu.add(0, CONTEXT_MENU_DELETE, 0, R.string.my_context_remove_star);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case CONTEXT_MENU_DELETE:
                final String id = QueryUtils.RouteList.getId(getListView(), info.position);
                final Uri uri = Uri.withAppendedPath(ObaContract.Routes.CONTENT_URI, id);
                if (!ListenerUtil.mutListener.listen(713)) {
                    ObaContract.Routes.markAsFavorite(getActivity(), uri, false);
                }
                if (!ListenerUtil.mutListener.listen(714)) {
                    ObaContract.RouteHeadsignFavorites.markAsFavorite(getActivity(), id, null, null, false);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(715)) {
            inflater.inflate(R.menu.my_starred_route_options, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(719)) {
            if (id == R.id.clear_starred) {
                if (!ListenerUtil.mutListener.listen(718)) {
                    new ClearDialog().show(getActivity().getSupportFragmentManager(), "confirm_clear_starred_routes");
                }
                return true;
            } else if (id == R.id.sort_stops) {
                if (!ListenerUtil.mutListener.listen(716)) {
                    ShowcaseViewUtils.doNotShowTutorial(ShowcaseViewUtils.TUTORIAL_STARRED_STOPS_SORT);
                }
                if (!ListenerUtil.mutListener.listen(717)) {
                    showSortByDialog();
                }
            }
        }
        return false;
    }

    private void showSortByDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(720)) {
            builder.setTitle(R.string.menu_option_sort_by);
        }
        final int currentRouteOrder = PreferenceUtils.getStopSortOrderFromPreferences();
        if (!ListenerUtil.mutListener.listen(725)) {
            builder.setSingleChoiceItems(R.array.sort_stops, currentRouteOrder, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int index) {
                    if (!ListenerUtil.mutListener.listen(723)) {
                        // If the user picked a different option, change the sort order
                        if (currentRouteOrder != index) {
                            if (!ListenerUtil.mutListener.listen(721)) {
                                setSortByClause(index);
                            }
                            if (!ListenerUtil.mutListener.listen(722)) {
                                // Restart the loader with the new sorting
                                getLoaderManager().restartLoader(0, null, MyStarredRoutesFragment.this);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(724)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(726)) {
            dialog.setOwnerActivity(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(727)) {
            dialog.show();
        }
    }

    /**
     * Sets the "sort by" string for ordering the routes, based on the given index of
     * R.array.sort_stops.  It also saves the sort by order to preferences.
     *
     * @param index the index of R.array.sort_stops that should be set
     */
    private void setSortByClause(int index) {
        if (!ListenerUtil.mutListener.listen(734)) {
            switch(index) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(728)) {
                        // Sort by name
                        Log.d(TAG, "Sort by name");
                    }
                    if (!ListenerUtil.mutListener.listen(729)) {
                        sortBy = "length(" + ObaContract.Routes.SHORTNAME + "), " + ObaContract.Routes.SHORTNAME + " asc";
                    }
                    if (!ListenerUtil.mutListener.listen(730)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_name_stops), null);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(731)) {
                        // Sort by frequently used
                        Log.d(TAG, "Sort by frequently used");
                    }
                    if (!ListenerUtil.mutListener.listen(732)) {
                        sortBy = ObaContract.Routes.USE_COUNT + " desc";
                    }
                    if (!ListenerUtil.mutListener.listen(733)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_mfu_stops), null);
                    }
                    break;
            }
        }
        // Set the sort option to preferences
        final String[] sortOptions = getResources().getStringArray(R.array.sort_stops);
        if (!ListenerUtil.mutListener.listen(735)) {
            PreferenceUtils.saveString(getResources().getString(R.string.preference_key_default_stop_sort), sortOptions[index]);
        }
    }

    @Override
    protected int getEmptyText() {
        return R.string.my_no_starred_routes;
    }

    public static class ClearDialog extends ClearConfirmDialog {

        @Override
        protected void doClear() {
            if (!ListenerUtil.mutListener.listen(736)) {
                ObaContract.Routes.markAsFavorite(getActivity(), ObaContract.Routes.CONTENT_URI, false);
            }
            if (!ListenerUtil.mutListener.listen(737)) {
                ObaContract.RouteHeadsignFavorites.clearAllFavorites(getActivity());
            }
            if (!ListenerUtil.mutListener.listen(738)) {
                ObaAnalytics.reportUiEvent(FirebaseAnalytics.getInstance(getContext()), getString(R.string.analytics_label_edit_field_bookmark_delete), null);
            }
        }
    }
}
