/*
 * Copyright (C) 2011-2015 Paul Watts (paulcwatts@gmail.com), University of South Florida
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
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.onebusaway.android.R;
import org.onebusaway.android.provider.ObaContract;
import org.onebusaway.android.tripservice.TripService;
import org.onebusaway.android.util.FragmentUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.UIUtils;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripInfoActivity extends AppCompatActivity {

    private static final String TAG = "TripInfoActivity";

    private static final String ROUTE_ID = ".RouteId";

    private static final String ROUTE_NAME = ".RouteName";

    private static final String STOP_NAME = ".StopName";

    private static final String HEADSIGN = ".Headsign";

    private static final String DEPARTURE_TIME = ".Depart";

    // Save/restore values
    private static final String TRIP_NAME = ".TripName";

    private static final String REMINDER_TIME = ".ReminderTime";

    private static final String REMINDER_DAYS = ".ReminderDays";

    public static void start(Context context, String tripId, String stopId) {
        Intent myIntent = new Intent(context, TripInfoActivity.class);
        if (!ListenerUtil.mutListener.listen(1941)) {
            myIntent.setData(ObaContract.Trips.buildUri(tripId, stopId));
        }
        if (!ListenerUtil.mutListener.listen(1942)) {
            context.startActivity(myIntent);
        }
    }

    public static void start(Context context, String tripId, String stopId, String routeId, String routeName, String stopName, long departureTime, String headsign) {
        Intent myIntent = new Intent(context, TripInfoActivity.class);
        if (!ListenerUtil.mutListener.listen(1943)) {
            myIntent.setData(ObaContract.Trips.buildUri(tripId, stopId));
        }
        if (!ListenerUtil.mutListener.listen(1944)) {
            myIntent.putExtra(ROUTE_ID, routeId);
        }
        if (!ListenerUtil.mutListener.listen(1945)) {
            myIntent.putExtra(ROUTE_NAME, routeName);
        }
        if (!ListenerUtil.mutListener.listen(1946)) {
            myIntent.putExtra(STOP_NAME, stopName);
        }
        if (!ListenerUtil.mutListener.listen(1947)) {
            myIntent.putExtra(DEPARTURE_TIME, departureTime);
        }
        if (!ListenerUtil.mutListener.listen(1948)) {
            myIntent.putExtra(HEADSIGN, headsign);
        }
        if (!ListenerUtil.mutListener.listen(1949)) {
            context.startActivity(myIntent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(1950)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(1951)) {
            UIUtils.setupActionBar(this);
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!ListenerUtil.mutListener.listen(1954)) {
            // Create the list fragment and add it as our sole content.
            if (fm.findFragmentById(android.R.id.content) == null) {
                TripInfoFragment content = new TripInfoFragment();
                if (!ListenerUtil.mutListener.listen(1952)) {
                    content.setArguments(FragmentUtils.getIntentArgs(getIntent()));
                }
                if (!ListenerUtil.mutListener.listen(1953)) {
                    fm.beginTransaction().add(android.R.id.content, content).commit();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(1956)) {
            if (item.getItemId() == android.R.id.home) {
                if (!ListenerUtil.mutListener.listen(1955)) {
                    // go up from here.
                    finish();
                }
                return true;
            }
        }
        return false;
    }

    TripInfoFragment getTripInfoFragment() {
        FragmentManager fm = getSupportFragmentManager();
        return (TripInfoFragment) fm.findFragmentById(android.R.id.content);
    }

    public static final class TripInfoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

        private static final String TAG_DELETE_DIALOG = ".DeleteDialog";

        private static final String[] PROJECTION = { ObaContract.Trips.NAME, ObaContract.Trips.REMINDER, ObaContract.Trips.DAYS, ObaContract.Trips.ROUTE_ID, ObaContract.Trips.HEADSIGN, ObaContract.Trips.DEPARTURE };

        private static final int COL_NAME = 0;

        private static final int COL_REMINDER = 1;

        private static final int COL_DAYS = 2;

        private static final int COL_ROUTE_ID = 3;

        private static final int COL_HEADSIGN = 4;

        private static final int COL_DEPARTURE = 5;

        private Uri mTripUri;

        private String mTripId;

        private String mRouteId;

        private String mRouteName;

        private String mStopId;

        private String mStopName;

        private String mHeadsign;

        private String mTripName;

        private long mDepartTime;

        // DB Value, not selection value
        private int mReminderTime;

        private int mReminderDays;

        private boolean mNewTrip = true;

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(1957)) {
                super.onActivityCreated(savedInstanceState);
            }
            if (!ListenerUtil.mutListener.listen(1958)) {
                setHasOptionsMenu(true);
            }
            if (!ListenerUtil.mutListener.listen(1963)) {
                if (savedInstanceState != null) {
                    if (!ListenerUtil.mutListener.listen(1961)) {
                        initFromBundle(savedInstanceState);
                    }
                    if (!ListenerUtil.mutListener.listen(1962)) {
                        initForm();
                    }
                } else if (initFromBundle(getArguments())) {
                    if (!ListenerUtil.mutListener.listen(1960)) {
                        getLoaderManager().initLoader(0, null, this);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(1959)) {
                        Log.e(TAG, "Information missing from intent");
                    }
                    return;
                }
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
            if (!ListenerUtil.mutListener.listen(1964)) {
                if (root == null) {
                    // reason to create our view.
                    return null;
                }
            }
            return inflater.inflate(R.layout.trip_info, null);
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new CursorLoader(getActivity(), mTripUri, PROJECTION, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (!ListenerUtil.mutListener.listen(1965)) {
                mNewTrip = !initFromCursor(data);
            }
            if (!ListenerUtil.mutListener.listen(1966)) {
                initForm();
            }
            if (!ListenerUtil.mutListener.listen(1967)) {
                getActivity().supportInvalidateOptionsMenu();
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        }

        private boolean initFromBundle(Bundle bundle) {
            final Uri data = bundle.getParcelable(FragmentUtils.URI);
            if (!ListenerUtil.mutListener.listen(1968)) {
                if (data == null) {
                    return false;
                }
            }
            List<String> segments = data.getPathSegments();
            if (!ListenerUtil.mutListener.listen(1969)) {
                mTripId = segments.get(1);
            }
            if (!ListenerUtil.mutListener.listen(1970)) {
                mStopId = segments.get(2);
            }
            if (!ListenerUtil.mutListener.listen(1971)) {
                mTripUri = data;
            }
            if (!ListenerUtil.mutListener.listen(1973)) {
                if ((ListenerUtil.mutListener.listen(1972) ? (mTripId == null && mStopId == null) : (mTripId == null || mStopId == null))) {
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(1974)) {
                mRouteId = bundle.getString(ROUTE_ID);
            }
            if (!ListenerUtil.mutListener.listen(1975)) {
                mHeadsign = bundle.getString(HEADSIGN);
            }
            if (!ListenerUtil.mutListener.listen(1976)) {
                mDepartTime = bundle.getLong(DEPARTURE_TIME);
            }
            if (!ListenerUtil.mutListener.listen(1977)) {
                mStopName = bundle.getString(STOP_NAME);
            }
            if (!ListenerUtil.mutListener.listen(1978)) {
                mRouteName = bundle.getString(ROUTE_NAME);
            }
            if (!ListenerUtil.mutListener.listen(1981)) {
                // If we get this, update it in the DB.
                if (mRouteName != null) {
                    ContentValues values = new ContentValues();
                    if (!ListenerUtil.mutListener.listen(1979)) {
                        values.put(ObaContract.Routes.SHORTNAME, mRouteName);
                    }
                    if (!ListenerUtil.mutListener.listen(1980)) {
                        ObaContract.Routes.insertOrUpdate(getActivity(), mRouteId, values, false);
                    }
                }
            }
            String name = bundle.getString(TRIP_NAME);
            if (!ListenerUtil.mutListener.listen(1983)) {
                if (name != null) {
                    if (!ListenerUtil.mutListener.listen(1982)) {
                        mTripName = name;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(1984)) {
                mReminderTime = bundle.getInt(REMINDER_TIME, mReminderTime);
            }
            if (!ListenerUtil.mutListener.listen(1985)) {
                mReminderDays = bundle.getInt(REMINDER_DAYS, mReminderDays);
            }
            return true;
        }

        private boolean initFromCursor(Cursor cursor) {
            if (!ListenerUtil.mutListener.listen(1993)) {
                if ((ListenerUtil.mutListener.listen(1991) ? (cursor == null && (ListenerUtil.mutListener.listen(1990) ? (cursor.getCount() >= 1) : (ListenerUtil.mutListener.listen(1989) ? (cursor.getCount() <= 1) : (ListenerUtil.mutListener.listen(1988) ? (cursor.getCount() > 1) : (ListenerUtil.mutListener.listen(1987) ? (cursor.getCount() != 1) : (ListenerUtil.mutListener.listen(1986) ? (cursor.getCount() == 1) : (cursor.getCount() < 1))))))) : (cursor == null || (ListenerUtil.mutListener.listen(1990) ? (cursor.getCount() >= 1) : (ListenerUtil.mutListener.listen(1989) ? (cursor.getCount() <= 1) : (ListenerUtil.mutListener.listen(1988) ? (cursor.getCount() > 1) : (ListenerUtil.mutListener.listen(1987) ? (cursor.getCount() != 1) : (ListenerUtil.mutListener.listen(1986) ? (cursor.getCount() == 1) : (cursor.getCount() < 1))))))))) {
                    if (!ListenerUtil.mutListener.listen(1992)) {
                        // Reminder defaults to 10 in the UI
                        mReminderTime = PreferenceUtils.getInt(getString(R.string.preference_key_default_reminder_time), 10);
                    }
                    return false;
                }
            }
            if (!ListenerUtil.mutListener.listen(1994)) {
                cursor.moveToFirst();
            }
            if (!ListenerUtil.mutListener.listen(1995)) {
                mTripName = cursor.getString(COL_NAME);
            }
            if (!ListenerUtil.mutListener.listen(1996)) {
                mReminderTime = cursor.getInt(COL_REMINDER);
            }
            if (!ListenerUtil.mutListener.listen(1997)) {
                mReminderDays = cursor.getInt(COL_DAYS);
            }
            if (!ListenerUtil.mutListener.listen(1999)) {
                // values in the db.
                if (mRouteId == null) {
                    if (!ListenerUtil.mutListener.listen(1998)) {
                        mRouteId = cursor.getString(COL_ROUTE_ID);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2001)) {
                if (mHeadsign == null) {
                    if (!ListenerUtil.mutListener.listen(2000)) {
                        mHeadsign = cursor.getString(COL_HEADSIGN);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2008)) {
                if ((ListenerUtil.mutListener.listen(2006) ? (mDepartTime >= 0) : (ListenerUtil.mutListener.listen(2005) ? (mDepartTime <= 0) : (ListenerUtil.mutListener.listen(2004) ? (mDepartTime > 0) : (ListenerUtil.mutListener.listen(2003) ? (mDepartTime < 0) : (ListenerUtil.mutListener.listen(2002) ? (mDepartTime != 0) : (mDepartTime == 0))))))) {
                    if (!ListenerUtil.mutListener.listen(2007)) {
                        mDepartTime = ObaContract.Trips.convertDBToTime(cursor.getInt(COL_DEPARTURE));
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2010)) {
                // If we don't have the route name, look it up in the DB
                if (mRouteName == null) {
                    if (!ListenerUtil.mutListener.listen(2009)) {
                        mRouteName = TripService.getRouteShortName(getActivity(), mRouteId);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2012)) {
                if (mStopName == null) {
                    if (!ListenerUtil.mutListener.listen(2011)) {
                        mStopName = UIUtils.stringForQuery(getActivity(), Uri.withAppendedPath(ObaContract.Stops.CONTENT_URI, mStopId), ObaContract.Stops.NAME);
                    }
                }
            }
            return true;
        }

        private void initForm() {
            View view = getView();
            final Spinner reminder = (Spinner) view.findViewById(R.id.trip_info_reminder_time);
            ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.reminder_time, android.R.layout.simple_spinner_item);
            if (!ListenerUtil.mutListener.listen(2013)) {
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            }
            if (!ListenerUtil.mutListener.listen(2014)) {
                reminder.setAdapter(adapter);
            }
            // 
            final TextView stopName = (TextView) view.findViewById(R.id.stop_name);
            if (!ListenerUtil.mutListener.listen(2015)) {
                stopName.setText(UIUtils.formatDisplayText(mStopName));
            }
            final TextView routeName = (TextView) view.findViewById(R.id.route_name);
            if (!ListenerUtil.mutListener.listen(2016)) {
                routeName.setText(UIUtils.formatDisplayText(getString(R.string.trip_info_route, mRouteName)));
            }
            final TextView headsign = (TextView) view.findViewById(R.id.headsign);
            if (!ListenerUtil.mutListener.listen(2017)) {
                headsign.setText(UIUtils.formatDisplayText(mHeadsign));
            }
            final TextView departText = (TextView) view.findViewById(R.id.departure_time);
            if (!ListenerUtil.mutListener.listen(2018)) {
                departText.setText(getDepartureTime(getActivity(), mDepartTime));
            }
            final TextView tripName = (TextView) view.findViewById(R.id.name);
            if (!ListenerUtil.mutListener.listen(2019)) {
                tripName.setText(mTripName);
            }
            if (!ListenerUtil.mutListener.listen(2020)) {
                reminder.setSelection(reminderToSelection(mReminderTime));
            }
            final Button repeats = (Button) view.findViewById(R.id.trip_info_reminder_days);
            if (!ListenerUtil.mutListener.listen(2021)) {
                repeats.setText(getRepeatText(getActivity(), mReminderDays));
            }
            if (!ListenerUtil.mutListener.listen(2023)) {
                // 
                repeats.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(2022)) {
                            showReminderDaysDialog();
                        }
                    }
                });
            }
        }

        void finish() {
            if (!ListenerUtil.mutListener.listen(2024)) {
                // But for now, the retains original, pre-fragment functionality.
                getActivity().finish();
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            if (!ListenerUtil.mutListener.listen(2025)) {
                super.onSaveInstanceState(outState);
            }
            if (!ListenerUtil.mutListener.listen(2026)) {
                outState.putParcelable(FragmentUtils.URI, mTripUri);
            }
            if (!ListenerUtil.mutListener.listen(2027)) {
                outState.putString(ROUTE_ID, mRouteId);
            }
            if (!ListenerUtil.mutListener.listen(2028)) {
                outState.putString(ROUTE_NAME, mRouteName);
            }
            if (!ListenerUtil.mutListener.listen(2029)) {
                outState.putString(STOP_NAME, mStopName);
            }
            if (!ListenerUtil.mutListener.listen(2030)) {
                outState.putString(HEADSIGN, mHeadsign);
            }
            if (!ListenerUtil.mutListener.listen(2031)) {
                outState.putLong(DEPARTURE_TIME, mDepartTime);
            }
            View view = getView();
            Spinner reminderView = (Spinner) view.findViewById(R.id.trip_info_reminder_time);
            TextView nameView = (TextView) view.findViewById(R.id.name);
            final int reminder = selectionToReminder(reminderView.getSelectedItemPosition());
            if (!ListenerUtil.mutListener.listen(2032)) {
                outState.putString(TRIP_NAME, nameView.getText().toString());
            }
            if (!ListenerUtil.mutListener.listen(2033)) {
                outState.putInt(REMINDER_TIME, reminder);
            }
            if (!ListenerUtil.mutListener.listen(2034)) {
                outState.putInt(REMINDER_DAYS, mReminderDays);
            }
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            if (!ListenerUtil.mutListener.listen(2035)) {
                inflater.inflate(R.menu.trip_info_options, menu);
            }
        }

        @Override
        public void onPrepareOptionsMenu(Menu menu) {
            if (!ListenerUtil.mutListener.listen(2036)) {
                menu.findItem(R.id.trip_info_delete).setVisible(!mNewTrip);
            }
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (!ListenerUtil.mutListener.listen(2043)) {
                if (id == R.id.trip_info_save) {
                    if (!ListenerUtil.mutListener.listen(2042)) {
                        saveTrip();
                    }
                } else if (id == R.id.trip_info_delete) {
                    Bundle args = new Bundle();
                    if (!ListenerUtil.mutListener.listen(2039)) {
                        args.putParcelable("uri", mTripUri);
                    }
                    DeleteDialog dialog = new DeleteDialog();
                    if (!ListenerUtil.mutListener.listen(2040)) {
                        dialog.setArguments(args);
                    }
                    if (!ListenerUtil.mutListener.listen(2041)) {
                        dialog.show(getActivity().getSupportFragmentManager(), TAG_DELETE_DIALOG);
                    }
                } else if (id == R.id.show_route) {
                    if (!ListenerUtil.mutListener.listen(2038)) {
                        RouteInfoActivity.start(getActivity(), mRouteId);
                    }
                    return true;
                } else if (id == R.id.show_stop) {
                    if (!ListenerUtil.mutListener.listen(2037)) {
                        new ArrivalsListActivity.Builder(getActivity(), mStopId).setStopName(mStopName).start();
                    }
                    return true;
                }
            }
            return false;
        }

        public void saveTrip() {
            // 
            View view = getView();
            final Spinner reminderView = (Spinner) view.findViewById(R.id.trip_info_reminder_time);
            final TextView nameView = (TextView) view.findViewById(R.id.name);
            final int reminder = selectionToReminder(reminderView.getSelectedItemPosition());
            ContentValues values = new ContentValues();
            if (!ListenerUtil.mutListener.listen(2044)) {
                values.put(ObaContract.Trips.ROUTE_ID, mRouteId);
            }
            if (!ListenerUtil.mutListener.listen(2045)) {
                values.put(ObaContract.Trips.DEPARTURE, ObaContract.Trips.convertTimeToDB(mDepartTime));
            }
            if (!ListenerUtil.mutListener.listen(2046)) {
                values.put(ObaContract.Trips.HEADSIGN, mHeadsign);
            }
            if (!ListenerUtil.mutListener.listen(2047)) {
                values.put(ObaContract.Trips.NAME, nameView.getText().toString());
            }
            if (!ListenerUtil.mutListener.listen(2048)) {
                values.put(ObaContract.Trips.REMINDER, reminder);
            }
            if (!ListenerUtil.mutListener.listen(2049)) {
                values.put(ObaContract.Trips.DAYS, mReminderDays);
            }
            // Insert or update?
            ContentResolver cr = getActivity().getContentResolver();
            Cursor c = cr.query(mTripUri, new String[] { ObaContract.Trips._ID }, null, null, null);
            if (!ListenerUtil.mutListener.listen(2060)) {
                if ((ListenerUtil.mutListener.listen(2055) ? (c != null || (ListenerUtil.mutListener.listen(2054) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(2053) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(2052) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(2051) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(2050) ? (c.getCount() == 0) : (c.getCount() > 0))))))) : (c != null && (ListenerUtil.mutListener.listen(2054) ? (c.getCount() >= 0) : (ListenerUtil.mutListener.listen(2053) ? (c.getCount() <= 0) : (ListenerUtil.mutListener.listen(2052) ? (c.getCount() < 0) : (ListenerUtil.mutListener.listen(2051) ? (c.getCount() != 0) : (ListenerUtil.mutListener.listen(2050) ? (c.getCount() == 0) : (c.getCount() > 0))))))))) {
                    if (!ListenerUtil.mutListener.listen(2059)) {
                        // Update
                        cr.update(mTripUri, values, null, null);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(2056)) {
                        values.put(ObaContract.Trips._ID, mTripId);
                    }
                    if (!ListenerUtil.mutListener.listen(2057)) {
                        values.put(ObaContract.Trips.STOP_ID, mStopId);
                    }
                    if (!ListenerUtil.mutListener.listen(2058)) {
                        cr.insert(ObaContract.Trips.CONTENT_URI, values);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2062)) {
                if (c != null) {
                    if (!ListenerUtil.mutListener.listen(2061)) {
                        c.close();
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(2063)) {
                TripService.scheduleAll(getActivity(), true);
            }
            if (!ListenerUtil.mutListener.listen(2064)) {
                PreferenceUtils.saveInt(getString(R.string.preference_key_default_reminder_time), reminder);
            }
            if (!ListenerUtil.mutListener.listen(2065)) {
                Toast.makeText(getActivity(), R.string.trip_info_saved, Toast.LENGTH_SHORT).show();
            }
            if (!ListenerUtil.mutListener.listen(2066)) {
                finish();
            }
        }

        void showReminderDaysDialog() {
            final boolean[] checks = ObaContract.Trips.daysToArray(mReminderDays);
            Bundle args = new Bundle();
            if (!ListenerUtil.mutListener.listen(2067)) {
                args.putBooleanArray(ReminderDaysDialog.CHECKS, checks);
            }
            ReminderDaysDialog frag = new ReminderDaysDialog();
            if (!ListenerUtil.mutListener.listen(2068)) {
                frag.setArguments(args);
            }
            if (!ListenerUtil.mutListener.listen(2069)) {
                frag.show(getActivity().getSupportFragmentManager(), ".ReminderDaysDialog");
            }
        }

        public static class ReminderDaysDialog extends DialogFragment implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnClickListener {

            static final String CHECKS = ".checks";

            private boolean[] mChecks;

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                Bundle args = getArguments();
                if (!ListenerUtil.mutListener.listen(2070)) {
                    mChecks = args.getBooleanArray(CHECKS);
                }
                if (!ListenerUtil.mutListener.listen(2072)) {
                    if (savedInstanceState != null) {
                        if (!ListenerUtil.mutListener.listen(2071)) {
                            mChecks = args.getBooleanArray(CHECKS);
                        }
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                return builder.setTitle(R.string.trip_info_reminder_repeat).setMultiChoiceItems(R.array.reminder_days, mChecks, this).setPositiveButton(R.string.trip_info_save, this).setNegativeButton(R.string.trip_info_dismiss, null).create();
            }

            @Override
            public void onSaveInstanceState(Bundle outState) {
                if (!ListenerUtil.mutListener.listen(2073)) {
                    outState.putBooleanArray(CHECKS, mChecks);
                }
            }

            @Override
            public void onClick(DialogInterface dialog, int which) {
                TripInfoActivity act = (TripInfoActivity) getActivity();
                // Get the fragment we want...
                TripInfoFragment frag = act.getTripInfoFragment();
                if (!ListenerUtil.mutListener.listen(2074)) {
                    frag.setReminderDays(mChecks);
                }
                if (!ListenerUtil.mutListener.listen(2075)) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onClick(DialogInterface arg0, int which, boolean isChecked) {
                if (!ListenerUtil.mutListener.listen(2076)) {
                    mChecks[which] = isChecked;
                }
            }
        }

        private void setReminderDays(boolean[] checks) {
            View view = getView();
            if (!ListenerUtil.mutListener.listen(2077)) {
                mReminderDays = ObaContract.Trips.arrayToDays(checks);
            }
            final Button repeats = (Button) view.findViewById(R.id.trip_info_reminder_days);
            if (!ListenerUtil.mutListener.listen(2078)) {
                repeats.setText(getRepeatText(getActivity(), mReminderDays));
            }
        }

        public static class DeleteDialog extends DialogFragment {

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                Bundle args = getArguments();
                final Uri tripUri = args.getParcelable("uri");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                if (!ListenerUtil.mutListener.listen(2083)) {
                    builder.setMessage(R.string.trip_info_delete_trip).setTitle(R.string.trip_info_delete).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            ContentResolver cr = getActivity().getContentResolver();
                            if (!ListenerUtil.mutListener.listen(2080)) {
                                cr.delete(tripUri, null, null);
                            }
                            if (!ListenerUtil.mutListener.listen(2081)) {
                                TripService.scheduleAll(getActivity(), true);
                            }
                            if (!ListenerUtil.mutListener.listen(2082)) {
                                getActivity().finish();
                            }
                        }
                    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            if (!ListenerUtil.mutListener.listen(2079)) {
                                dialog.dismiss();
                            }
                        }
                    });
                }
                return builder.create();
            }
        }

        // This converts what's in the database to what can be displayed in the spinner.
        private static int reminderToSelection(int reminder) {
            switch(reminder) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 3:
                    return 2;
                case 5:
                    return 3;
                case 10:
                    return 4;
                case 15:
                    return 5;
                case 20:
                    return 6;
                case 25:
                    return 7;
                case 30:
                    return 8;
                default:
                    if (!ListenerUtil.mutListener.listen(2084)) {
                        Log.e(TAG, "Invalid reminder value in DB: " + reminder);
                    }
                    return 0;
            }
        }

        private static int selectionToReminder(int selection) {
            switch(selection) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 3;
                case 3:
                    return 5;
                case 4:
                    return 10;
                case 5:
                    return 15;
                case 6:
                    return 20;
                case 7:
                    return 25;
                case 8:
                    return 30;
                default:
                    if (!ListenerUtil.mutListener.listen(2085)) {
                        Log.e(TAG, "Invalid selection: " + selection);
                    }
                    return 0;
            }
        }
    }

    static String getDepartureTime(Context ctx, long departure) {
        return ctx.getString(R.string.trip_info_depart, DateUtils.formatDateTime(ctx, departure, DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_NO_NOON | DateUtils.FORMAT_NO_MIDNIGHT));
    }

    static String getRepeatText(Context ctx, int days) {
        final Resources res = ctx.getResources();
        if (!ListenerUtil.mutListener.listen(2086)) {
            if ((days & ObaContract.Trips.DAY_ALL) == ObaContract.Trips.DAY_ALL) {
                return res.getString(R.string.trip_info_repeat_everyday);
            }
        }
        if (!ListenerUtil.mutListener.listen(2093)) {
            if ((ListenerUtil.mutListener.listen(2092) ? (((days & ObaContract.Trips.DAY_WEEKDAY) == ObaContract.Trips.DAY_WEEKDAY) || (ListenerUtil.mutListener.listen(2091) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) >= 0) : (ListenerUtil.mutListener.listen(2090) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) <= 0) : (ListenerUtil.mutListener.listen(2089) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) > 0) : (ListenerUtil.mutListener.listen(2088) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) < 0) : (ListenerUtil.mutListener.listen(2087) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) != 0) : ((days & ~ObaContract.Trips.DAY_WEEKDAY) == 0))))))) : (((days & ObaContract.Trips.DAY_WEEKDAY) == ObaContract.Trips.DAY_WEEKDAY) && (ListenerUtil.mutListener.listen(2091) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) >= 0) : (ListenerUtil.mutListener.listen(2090) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) <= 0) : (ListenerUtil.mutListener.listen(2089) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) > 0) : (ListenerUtil.mutListener.listen(2088) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) < 0) : (ListenerUtil.mutListener.listen(2087) ? ((days & ~ObaContract.Trips.DAY_WEEKDAY) != 0) : ((days & ~ObaContract.Trips.DAY_WEEKDAY) == 0))))))))) {
                return res.getString(R.string.trip_info_repeat_weekdays);
            }
        }
        if (!ListenerUtil.mutListener.listen(2099)) {
            if ((ListenerUtil.mutListener.listen(2098) ? (days >= 0) : (ListenerUtil.mutListener.listen(2097) ? (days <= 0) : (ListenerUtil.mutListener.listen(2096) ? (days > 0) : (ListenerUtil.mutListener.listen(2095) ? (days < 0) : (ListenerUtil.mutListener.listen(2094) ? (days != 0) : (days == 0))))))) {
                return res.getString(R.string.trip_info_repeat_norepeat);
            }
        }
        // Otherwise, it's not normal -- format a string
        final boolean[] array = ObaContract.Trips.daysToArray(days);
        final String[] dayNames = res.getStringArray(R.array.reminder_days);
        StringBuffer buf = new StringBuffer();
        // Find the first day
        int rangeStart = 0;
        if (!ListenerUtil.mutListener.listen(2155)) {
            {
                long _loopCounter25 = 0;
                while ((ListenerUtil.mutListener.listen(2154) ? (rangeStart >= 7) : (ListenerUtil.mutListener.listen(2153) ? (rangeStart <= 7) : (ListenerUtil.mutListener.listen(2152) ? (rangeStart > 7) : (ListenerUtil.mutListener.listen(2151) ? (rangeStart != 7) : (ListenerUtil.mutListener.listen(2150) ? (rangeStart == 7) : (rangeStart < 7))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter25", ++_loopCounter25);
                    if (!ListenerUtil.mutListener.listen(2106)) {
                        {
                            long _loopCounter23 = 0;
                            for (; (ListenerUtil.mutListener.listen(2105) ? ((ListenerUtil.mutListener.listen(2104) ? (rangeStart >= 7) : (ListenerUtil.mutListener.listen(2103) ? (rangeStart <= 7) : (ListenerUtil.mutListener.listen(2102) ? (rangeStart > 7) : (ListenerUtil.mutListener.listen(2101) ? (rangeStart != 7) : (ListenerUtil.mutListener.listen(2100) ? (rangeStart == 7) : (rangeStart < 7)))))) || !array[rangeStart]) : ((ListenerUtil.mutListener.listen(2104) ? (rangeStart >= 7) : (ListenerUtil.mutListener.listen(2103) ? (rangeStart <= 7) : (ListenerUtil.mutListener.listen(2102) ? (rangeStart > 7) : (ListenerUtil.mutListener.listen(2101) ? (rangeStart != 7) : (ListenerUtil.mutListener.listen(2100) ? (rangeStart == 7) : (rangeStart < 7)))))) && !array[rangeStart])); ++rangeStart) {
                                ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2112)) {
                        if ((ListenerUtil.mutListener.listen(2111) ? (rangeStart >= 7) : (ListenerUtil.mutListener.listen(2110) ? (rangeStart <= 7) : (ListenerUtil.mutListener.listen(2109) ? (rangeStart > 7) : (ListenerUtil.mutListener.listen(2108) ? (rangeStart < 7) : (ListenerUtil.mutListener.listen(2107) ? (rangeStart != 7) : (rangeStart == 7))))))) {
                            break;
                        }
                    }
                    int rangeEnd = (ListenerUtil.mutListener.listen(2116) ? (rangeStart % 1) : (ListenerUtil.mutListener.listen(2115) ? (rangeStart / 1) : (ListenerUtil.mutListener.listen(2114) ? (rangeStart * 1) : (ListenerUtil.mutListener.listen(2113) ? (rangeStart - 1) : (rangeStart + 1)))));
                    if (!ListenerUtil.mutListener.listen(2123)) {
                        {
                            long _loopCounter24 = 0;
                            for (; (ListenerUtil.mutListener.listen(2122) ? ((ListenerUtil.mutListener.listen(2121) ? (rangeEnd >= 7) : (ListenerUtil.mutListener.listen(2120) ? (rangeEnd <= 7) : (ListenerUtil.mutListener.listen(2119) ? (rangeEnd > 7) : (ListenerUtil.mutListener.listen(2118) ? (rangeEnd != 7) : (ListenerUtil.mutListener.listen(2117) ? (rangeEnd == 7) : (rangeEnd < 7)))))) || array[rangeEnd]) : ((ListenerUtil.mutListener.listen(2121) ? (rangeEnd >= 7) : (ListenerUtil.mutListener.listen(2120) ? (rangeEnd <= 7) : (ListenerUtil.mutListener.listen(2119) ? (rangeEnd > 7) : (ListenerUtil.mutListener.listen(2118) ? (rangeEnd != 7) : (ListenerUtil.mutListener.listen(2117) ? (rangeEnd == 7) : (rangeEnd < 7)))))) && array[rangeEnd])); ++rangeEnd) {
                                ListenerUtil.loopListener.listen("_loopCounter24", ++_loopCounter24);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2130)) {
                        if ((ListenerUtil.mutListener.listen(2128) ? (buf.length() >= 0) : (ListenerUtil.mutListener.listen(2127) ? (buf.length() <= 0) : (ListenerUtil.mutListener.listen(2126) ? (buf.length() > 0) : (ListenerUtil.mutListener.listen(2125) ? (buf.length() < 0) : (ListenerUtil.mutListener.listen(2124) ? (buf.length() == 0) : (buf.length() != 0))))))) {
                            if (!ListenerUtil.mutListener.listen(2129)) {
                                // TODO: Move to string table
                                buf.append(", ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2148)) {
                        // Single day?
                        if ((ListenerUtil.mutListener.listen(2139) ? (((ListenerUtil.mutListener.listen(2134) ? (rangeEnd % rangeStart) : (ListenerUtil.mutListener.listen(2133) ? (rangeEnd / rangeStart) : (ListenerUtil.mutListener.listen(2132) ? (rangeEnd * rangeStart) : (ListenerUtil.mutListener.listen(2131) ? (rangeEnd + rangeStart) : (rangeEnd - rangeStart)))))) >= 1) : (ListenerUtil.mutListener.listen(2138) ? (((ListenerUtil.mutListener.listen(2134) ? (rangeEnd % rangeStart) : (ListenerUtil.mutListener.listen(2133) ? (rangeEnd / rangeStart) : (ListenerUtil.mutListener.listen(2132) ? (rangeEnd * rangeStart) : (ListenerUtil.mutListener.listen(2131) ? (rangeEnd + rangeStart) : (rangeEnd - rangeStart)))))) <= 1) : (ListenerUtil.mutListener.listen(2137) ? (((ListenerUtil.mutListener.listen(2134) ? (rangeEnd % rangeStart) : (ListenerUtil.mutListener.listen(2133) ? (rangeEnd / rangeStart) : (ListenerUtil.mutListener.listen(2132) ? (rangeEnd * rangeStart) : (ListenerUtil.mutListener.listen(2131) ? (rangeEnd + rangeStart) : (rangeEnd - rangeStart)))))) > 1) : (ListenerUtil.mutListener.listen(2136) ? (((ListenerUtil.mutListener.listen(2134) ? (rangeEnd % rangeStart) : (ListenerUtil.mutListener.listen(2133) ? (rangeEnd / rangeStart) : (ListenerUtil.mutListener.listen(2132) ? (rangeEnd * rangeStart) : (ListenerUtil.mutListener.listen(2131) ? (rangeEnd + rangeStart) : (rangeEnd - rangeStart)))))) < 1) : (ListenerUtil.mutListener.listen(2135) ? (((ListenerUtil.mutListener.listen(2134) ? (rangeEnd % rangeStart) : (ListenerUtil.mutListener.listen(2133) ? (rangeEnd / rangeStart) : (ListenerUtil.mutListener.listen(2132) ? (rangeEnd * rangeStart) : (ListenerUtil.mutListener.listen(2131) ? (rangeEnd + rangeStart) : (rangeEnd - rangeStart)))))) != 1) : (((ListenerUtil.mutListener.listen(2134) ? (rangeEnd % rangeStart) : (ListenerUtil.mutListener.listen(2133) ? (rangeEnd / rangeStart) : (ListenerUtil.mutListener.listen(2132) ? (rangeEnd * rangeStart) : (ListenerUtil.mutListener.listen(2131) ? (rangeEnd + rangeStart) : (rangeEnd - rangeStart)))))) == 1))))))) {
                            if (!ListenerUtil.mutListener.listen(2147)) {
                                buf.append(dayNames[rangeStart]);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(2140)) {
                                buf.append(dayNames[rangeStart]);
                            }
                            if (!ListenerUtil.mutListener.listen(2141)) {
                                // TODO: Move to string table
                                buf.append(" - ");
                            }
                            if (!ListenerUtil.mutListener.listen(2146)) {
                                buf.append(dayNames[(ListenerUtil.mutListener.listen(2145) ? (rangeEnd % 1) : (ListenerUtil.mutListener.listen(2144) ? (rangeEnd / 1) : (ListenerUtil.mutListener.listen(2143) ? (rangeEnd * 1) : (ListenerUtil.mutListener.listen(2142) ? (rangeEnd + 1) : (rangeEnd - 1)))))]);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(2149)) {
                        rangeStart = rangeEnd;
                    }
                }
            }
        }
        return res.getString(R.string.trip_info_repeat_every, buf.toString());
    }
}
