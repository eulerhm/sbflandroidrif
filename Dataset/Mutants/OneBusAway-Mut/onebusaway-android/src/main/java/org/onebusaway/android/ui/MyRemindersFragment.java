/*
 * Copyright (C) 2014 Paul Watts (paulcwatts@gmail.com),
 * University of South Florida (sjbarbeau@gmail.com)
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

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.R;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.tripservice.TripService;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.UIUtils;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class MyRemindersFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "MyRemindersFragment";

    private static final String[] PROJECTION = { ObaContract.Trips._ID, ObaContract.Trips.NAME, ObaContract.Trips.HEADSIGN, ObaContract.Trips.DEPARTURE, ObaContract.Trips.ROUTE_ID, ObaContract.Trips.STOP_ID };

    private static final int COL_ID = 0;

    private static final int COL_NAME = 1;

    private static final int COL_HEADSIGN = 2;

    private static final int COL_DEPARTURE = 3;

    private static final int COL_ROUTE_ID = 4;

    private static final int COL_STOP_ID = 5;

    private static final Handler mHandler = new Handler();

    private class Observer extends ContentObserver {

        Observer() {
            super(mHandler);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return false;
        }

        public void onChange(boolean selfChange) {
            if (!ListenerUtil.mutListener.listen(3631)) {
                if (isAdded()) {
                    if (!ListenerUtil.mutListener.listen(3630)) {
                        getLoaderManager().restartLoader(0, null, MyRemindersFragment.this);
                    }
                }
            }
        }
    }

    private SimpleCursorAdapter mAdapter;

    private Observer mObserver;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3632)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3633)) {
            super.onActivityCreated(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(3634)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(3635)) {
            setReminderOrder();
        }
        if (!ListenerUtil.mutListener.listen(3636)) {
            // Set empty text
            setEmptyText(getString(R.string.trip_list_notrips));
        }
        if (!ListenerUtil.mutListener.listen(3637)) {
            registerForContextMenu(getListView());
        }
        if (!ListenerUtil.mutListener.listen(3638)) {
            // Create our generic adapter
            mAdapter = newAdapter();
        }
        if (!ListenerUtil.mutListener.listen(3639)) {
            setListAdapter(mAdapter);
        }
        ContentResolver cr = getActivity().getContentResolver();
        if (!ListenerUtil.mutListener.listen(3640)) {
            mObserver = new Observer();
        }
        if (!ListenerUtil.mutListener.listen(3641)) {
            cr.registerContentObserver(ObaContract.Trips.CONTENT_URI, true, mObserver);
        }
        if (!ListenerUtil.mutListener.listen(3642)) {
            // Prepare the loader
            getLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(3643)) {
            super.onViewCreated(view, savedInstanceState);
        }
        ListView listView = getListView();
        if (!ListenerUtil.mutListener.listen(3644)) {
            listView.setBackgroundColor(getResources().getColor(R.color.listview_background));
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(3647)) {
            if (mObserver != null) {
                ContentResolver cr = getActivity().getContentResolver();
                if (!ListenerUtil.mutListener.listen(3645)) {
                    cr.unregisterContentObserver(mObserver);
                }
                if (!ListenerUtil.mutListener.listen(3646)) {
                    mObserver = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3648)) {
            super.onDestroy();
        }
    }

    private int mCurrentSortOrder;

    private static String sortBy;

    private void setReminderOrder() {
        if (!ListenerUtil.mutListener.listen(3649)) {
            mCurrentSortOrder = PreferenceUtils.getReminderSortOrderFromPreferences();
        }
        if (!ListenerUtil.mutListener.listen(3652)) {
            switch(mCurrentSortOrder) {
                case // Sort by name
                0:
                    if (!ListenerUtil.mutListener.listen(3650)) {
                        sortBy = ObaContract.Trips.NAME + " asc";
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(3651)) {
                        sortBy = ObaContract.Trips.DEPARTURE + " asc";
                    }
                    break;
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), ObaContract.Trips.CONTENT_URI, PROJECTION, null, null, sortBy);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (!ListenerUtil.mutListener.listen(3653)) {
            mAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (!ListenerUtil.mutListener.listen(3654)) {
            mAdapter.swapCursor(null);
        }
    }

    private SimpleCursorAdapter newAdapter() {
        final String[] from = { ObaContract.Trips.NAME, ObaContract.Trips.HEADSIGN, ObaContract.Trips.DEPARTURE, ObaContract.Trips.ROUTE_ID };
        final int[] to = { R.id.name, R.id.headsign, R.id.departure_time, R.id.route_name };
        SimpleCursorAdapter simpleAdapter = new SimpleCursorAdapter(getActivity(), R.layout.trip_list_listitem, null, from, to, 0);
        if (!ListenerUtil.mutListener.listen(3663)) {
            simpleAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {

                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    if (!ListenerUtil.mutListener.listen(3662)) {
                        if (columnIndex == COL_NAME) {
                            TextView text = (TextView) view;
                            String name = cursor.getString(columnIndex);
                            if (!ListenerUtil.mutListener.listen(3660)) {
                                if (name.length() == 0) {
                                    if (!ListenerUtil.mutListener.listen(3659)) {
                                        name = getString(R.string.trip_info_noname);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(3661)) {
                                text.setText(name);
                            }
                            return true;
                        } else if (columnIndex == COL_HEADSIGN) {
                            String headSign = cursor.getString(columnIndex);
                            TextView text = (TextView) view;
                            if (!ListenerUtil.mutListener.listen(3658)) {
                                text.setText(UIUtils.formatDisplayText(headSign));
                            }
                            return true;
                        } else if (columnIndex == COL_DEPARTURE) {
                            TextView text = (TextView) view;
                            if (!ListenerUtil.mutListener.listen(3657)) {
                                text.setText(TripInfoActivity.getDepartureTime(getActivity(), ObaContract.Trips.convertDBToTime(cursor.getInt(columnIndex))));
                            }
                            return true;
                        } else if (columnIndex == COL_ROUTE_ID) {
                            // 
                            TextView text = (TextView) view;
                            final String routeId = cursor.getString(columnIndex);
                            final String routeName = TripService.getRouteShortName(getActivity(), routeId);
                            if (!ListenerUtil.mutListener.listen(3656)) {
                                if (routeName != null) {
                                    if (!ListenerUtil.mutListener.listen(3655)) {
                                        text.setText(getString(R.string.trip_info_route, routeName));
                                    }
                                }
                            }
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        return simpleAdapter;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String[] ids = getIds(l, position);
        if (!ListenerUtil.mutListener.listen(3664)) {
            TripInfoActivity.start(getActivity(), ids[0], ids[1]);
        }
    }

    private static final int CONTEXT_MENU_DEFAULT = 1;

    private static final int CONTEXT_MENU_DELETE = 2;

    private static final int CONTEXT_MENU_SHOWSTOP = 3;

    private static final int CONTEXT_MENU_SHOWROUTE = 4;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (!ListenerUtil.mutListener.listen(3665)) {
            super.onCreateContextMenu(menu, v, menuInfo);
        }
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final TextView text = (TextView) info.targetView.findViewById(R.id.name);
        if (!ListenerUtil.mutListener.listen(3666)) {
            menu.setHeaderTitle(text.getText());
        }
        if (!ListenerUtil.mutListener.listen(3667)) {
            menu.add(0, CONTEXT_MENU_DEFAULT, 0, R.string.trip_list_context_edit);
        }
        if (!ListenerUtil.mutListener.listen(3668)) {
            menu.add(0, CONTEXT_MENU_DELETE, 0, R.string.trip_list_context_delete);
        }
        if (!ListenerUtil.mutListener.listen(3669)) {
            menu.add(0, CONTEXT_MENU_SHOWSTOP, 0, R.string.trip_list_context_showstop);
        }
        if (!ListenerUtil.mutListener.listen(3670)) {
            menu.add(0, CONTEXT_MENU_SHOWROUTE, 0, R.string.trip_list_context_showroute);
        }
    }

    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case CONTEXT_MENU_DEFAULT:
                if (!ListenerUtil.mutListener.listen(3671)) {
                    // Fake a click
                    onListItemClick(getListView(), info.targetView, info.position, info.id);
                }
                return true;
            case CONTEXT_MENU_DELETE:
                if (!ListenerUtil.mutListener.listen(3672)) {
                    deleteTrip(getListView(), info.position);
                }
                return true;
            case CONTEXT_MENU_SHOWSTOP:
                if (!ListenerUtil.mutListener.listen(3673)) {
                    goToStop(getListView(), info.position);
                }
                return true;
            case CONTEXT_MENU_SHOWROUTE:
                if (!ListenerUtil.mutListener.listen(3674)) {
                    goToRoute(getListView(), info.position);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteTrip(ListView l, int position) {
        String[] ids = getIds(l, position);
        // TODO: Confirmation dialog?
        ContentResolver cr = getActivity().getContentResolver();
        if (!ListenerUtil.mutListener.listen(3675)) {
            cr.delete(ObaContract.Trips.buildUri(ids[0], ids[1]), null, null);
        }
        if (!ListenerUtil.mutListener.listen(3676)) {
            TripService.scheduleAll(getActivity(), true);
        }
        if (!ListenerUtil.mutListener.listen(3677)) {
            getLoaderManager().getLoader(0).onContentChanged();
        }
    }

    private void goToStop(ListView l, int position) {
        String[] ids = getIds(l, position);
        if (!ListenerUtil.mutListener.listen(3678)) {
            ArrivalsListActivity.start(getActivity(), ids[1]);
        }
    }

    private void goToRoute(ListView l, int position) {
        String[] ids = getIds(l, position);
        if (!ListenerUtil.mutListener.listen(3679)) {
            RouteInfoActivity.start(getActivity(), ids[2]);
        }
    }

    private String[] getIds(ListView l, int position) {
        // Get the cursor and fetch the stop ID from that.
        SimpleCursorAdapter cursorAdapter = (SimpleCursorAdapter) l.getAdapter();
        final Cursor c = cursorAdapter.getCursor();
        if (!ListenerUtil.mutListener.listen(3684)) {
            c.moveToPosition((ListenerUtil.mutListener.listen(3683) ? (position % l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3682) ? (position / l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3681) ? (position * l.getHeaderViewsCount()) : (ListenerUtil.mutListener.listen(3680) ? (position + l.getHeaderViewsCount()) : (position - l.getHeaderViewsCount()))))));
        }
        final String[] result = new String[] { c.getString(COL_ID), c.getString(COL_STOP_ID), c.getString(COL_ROUTE_ID) };
        return result;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(3685)) {
            menu.add(R.string.menu_option_sort_by).setIcon(R.drawable.ic_action_content_sort).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final CharSequence menuId = item.getTitle();
        if (!ListenerUtil.mutListener.listen(3687)) {
            if (menuId.equals(getResources().getString(R.string.menu_option_sort_by))) {
                if (!ListenerUtil.mutListener.listen(3686)) {
                    showSortByDialog();
                }
            }
        }
        return false;
    }

    private void showSortByDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!ListenerUtil.mutListener.listen(3688)) {
            builder.setTitle(R.string.menu_option_sort_by);
        }
        if (!ListenerUtil.mutListener.listen(3689)) {
            mCurrentSortOrder = PreferenceUtils.getReminderSortOrderFromPreferences();
        }
        if (!ListenerUtil.mutListener.listen(3694)) {
            builder.setSingleChoiceItems(R.array.sort_reminders, mCurrentSortOrder, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!ListenerUtil.mutListener.listen(3692)) {
                        // If selected option is different, change option
                        if (mCurrentSortOrder != which) {
                            if (!ListenerUtil.mutListener.listen(3690)) {
                                setSortByClause(which);
                            }
                            if (!ListenerUtil.mutListener.listen(3691)) {
                                // Restart with new order
                                getLoaderManager().restartLoader(0, null, MyRemindersFragment.this);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3693)) {
                        dialog.dismiss();
                    }
                }
            });
        }
        AlertDialog dialog = builder.create();
        if (!ListenerUtil.mutListener.listen(3695)) {
            dialog.setOwnerActivity(getActivity());
        }
        if (!ListenerUtil.mutListener.listen(3696)) {
            dialog.show();
        }
    }

    /**
     * Sets the sortBy string for the loader and save it to preference
     *
     * @param index the index of R.array.sort_reminder that should be set
     */
    private void setSortByClause(int index) {
        if (!ListenerUtil.mutListener.listen(3703)) {
            switch(index) {
                case 0:
                    if (!ListenerUtil.mutListener.listen(3697)) {
                        Log.d(TAG, "setSortByClause: sort by name");
                    }
                    if (!ListenerUtil.mutListener.listen(3698)) {
                        sortBy = ObaContract.Trips.NAME + " asc";
                    }
                    if (!ListenerUtil.mutListener.listen(3699)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_name_reminder), null);
                    }
                    break;
                case 1:
                    if (!ListenerUtil.mutListener.listen(3700)) {
                        Log.d(TAG, "setSortByClause: sort by time");
                    }
                    if (!ListenerUtil.mutListener.listen(3701)) {
                        sortBy = ObaContract.Trips.DEPARTURE + " asc";
                    }
                    if (!ListenerUtil.mutListener.listen(3702)) {
                        ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_sort_by_departure_time_reminder), null);
                    }
                    break;
            }
        }
        final String[] sortOptions = getResources().getStringArray(R.array.sort_reminders);
        if (!ListenerUtil.mutListener.listen(3704)) {
            PreferenceUtils.saveString(getString(R.string.preference_key_default_reminder_sort), sortOptions[index]);
        }
    }
}
