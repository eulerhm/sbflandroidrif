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

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.onebusaway.android.BuildConfig;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.directions.util.ConversionUtils;
import org.onebusaway.android.directions.util.CustomAddress;
import org.onebusaway.android.directions.util.OTPConstants;
import org.onebusaway.android.directions.util.PlacesAutoCompleteAdapter;
import org.onebusaway.android.directions.util.TripRequestBuilder;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaRegion;
import org.onebusaway.android.map.googlemapsv2.ProprietaryMapHelpV2;
import org.onebusaway.android.util.LocationUtils;
import org.onebusaway.android.util.PreferenceUtils;
import org.onebusaway.android.util.ShowcaseViewUtils;
import org.onebusaway.android.util.UIUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import static org.onebusaway.android.util.ShowcaseViewUtils.showTutorial;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TripPlanFragment extends Fragment {

    /**
     * Allows calling activity to register to know when to send request.
     */
    interface Listener {

        /**
         * Called when the fields have been populated and a trip plan can occur.
         */
        void onTripRequestReady();
    }

    public static final String TAG = "TripPlanFragment";

    private static final int USE_FROM_ADDRESS = 1;

    private static final int USE_TO_ADDRESS = 2;

    private AutoCompleteTextView mFromAddressTextArea;

    private AutoCompleteTextView mToAddressTextArea;

    private ImageButton mFromCurrentLocationImageButton;

    private ImageButton mToCurrentLocationImageButton;

    private Spinner mDate;

    private ArrayAdapter mDateAdapter;

    private Spinner mTime;

    private ArrayAdapter mTimeAdapter;

    private Spinner mLeavingChoice;

    ArrayAdapter<CharSequence> mLeavingChoiceAdapter;

    Calendar mMyCalendar;

    protected GoogleApiClient mGoogleApiClient;

    private CustomAddress mFromAddress, mToAddress;

    private TripRequestBuilder mBuilder;

    private Listener mListener;

    private String mPlanErrorUrl;

    private String mPlanRequestUrl;

    private FirebaseAnalytics mFirebaseAnalytics;

    // Create view, initialize state
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(4491)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(4492)) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        }
        if (!ListenerUtil.mutListener.listen(4495)) {
            // Init Google Play Services as early as possible in the Fragment lifecycle to give it time
            if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
                if (!ListenerUtil.mutListener.listen(4493)) {
                    mGoogleApiClient = LocationUtils.getGoogleApiClientWithCallbacks(getContext());
                }
                if (!ListenerUtil.mutListener.listen(4494)) {
                    mGoogleApiClient.connect();
                }
            }
        }
        Bundle bundle = getArguments();
        if (!ListenerUtil.mutListener.listen(4496)) {
            mBuilder = new TripRequestBuilder(bundle);
        }
        if (!ListenerUtil.mutListener.listen(4497)) {
            loadAndSetAdditionalTripPreferences();
        }
        final View view = inflater.inflate(R.layout.fragment_trip_plan, container, false);
        if (!ListenerUtil.mutListener.listen(4498)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(4499)) {
            mFromAddressTextArea = (AutoCompleteTextView) view.findViewById(R.id.fromAddressTextArea);
        }
        if (!ListenerUtil.mutListener.listen(4500)) {
            mToAddressTextArea = (AutoCompleteTextView) view.findViewById(R.id.toAddressTextArea);
        }
        if (!ListenerUtil.mutListener.listen(4501)) {
            mFromCurrentLocationImageButton = (ImageButton) view.findViewById(R.id.fromCurrentLocationImageButton);
        }
        if (!ListenerUtil.mutListener.listen(4502)) {
            mToCurrentLocationImageButton = (ImageButton) view.findViewById(R.id.toCurrentLocationImageButton);
        }
        if (!ListenerUtil.mutListener.listen(4503)) {
            mDate = (Spinner) view.findViewById(R.id.date);
        }
        if (!ListenerUtil.mutListener.listen(4504)) {
            mDateAdapter = new ArrayAdapter(getActivity(), R.layout.simple_list_item);
        }
        if (!ListenerUtil.mutListener.listen(4505)) {
            mDate.setAdapter(mDateAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4506)) {
            mTime = (Spinner) view.findViewById(R.id.time);
        }
        if (!ListenerUtil.mutListener.listen(4507)) {
            mTimeAdapter = new ArrayAdapter(getActivity(), R.layout.simple_list_item);
        }
        if (!ListenerUtil.mutListener.listen(4508)) {
            mTime.setAdapter(mTimeAdapter);
        }
        if (!ListenerUtil.mutListener.listen(4509)) {
            mLeavingChoice = (Spinner) view.findViewById(R.id.leavingChoiceSpinner);
        }
        if (!ListenerUtil.mutListener.listen(4510)) {
            mLeavingChoiceAdapter = ArrayAdapter.createFromResource(getContext(), R.array.trip_plan_leaving_arriving_array, R.layout.simple_list_item);
        }
        if (!ListenerUtil.mutListener.listen(4511)) {
            mLeavingChoiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(4512)) {
            mLeavingChoice.setAdapter(mLeavingChoiceAdapter);
        }
        // Set mLeavingChoice onclick adapter in onResume() so we do not fire it when setting it
        final TimePickerDialog.OnTimeSetListener timeCallback = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                if (!ListenerUtil.mutListener.listen(4513)) {
                    mMyCalendar.set(Calendar.HOUR_OF_DAY, hour);
                }
                if (!ListenerUtil.mutListener.listen(4514)) {
                    mMyCalendar.set(Calendar.MINUTE, minute);
                }
                if (!ListenerUtil.mutListener.listen(4515)) {
                    resetDateTimeLabels();
                }
                if (!ListenerUtil.mutListener.listen(4516)) {
                    mBuilder.setDateTime(mMyCalendar);
                }
                if (!ListenerUtil.mutListener.listen(4517)) {
                    checkRequestAndSubmit();
                }
            }
        };
        final DatePickerDialog.OnDateSetListener dateCallback = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (!ListenerUtil.mutListener.listen(4518)) {
                    mMyCalendar.set(Calendar.YEAR, year);
                }
                if (!ListenerUtil.mutListener.listen(4519)) {
                    mMyCalendar.set(Calendar.MONTH, monthOfYear);
                }
                if (!ListenerUtil.mutListener.listen(4520)) {
                    mMyCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                }
                if (!ListenerUtil.mutListener.listen(4521)) {
                    resetDateTimeLabels();
                }
                if (!ListenerUtil.mutListener.listen(4522)) {
                    mBuilder.setDateTime(mMyCalendar);
                }
                if (!ListenerUtil.mutListener.listen(4523)) {
                    checkRequestAndSubmit();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(4526)) {
            mDate.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!ListenerUtil.mutListener.listen(4525)) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            if (!ListenerUtil.mutListener.listen(4524)) {
                                new DatePickerDialog(view.getContext(), dateCallback, mMyCalendar.get(Calendar.YEAR), mMyCalendar.get(Calendar.MONTH), mMyCalendar.get(Calendar.DAY_OF_MONTH)).show();
                            }
                        }
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4529)) {
            mTime.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!ListenerUtil.mutListener.listen(4528)) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            if (!ListenerUtil.mutListener.listen(4527)) {
                                new TimePickerDialog(view.getContext(), timeCallback, mMyCalendar.get(Calendar.HOUR_OF_DAY), mMyCalendar.get(Calendar.MINUTE), false).show();
                            }
                        }
                    }
                    return true;
                }
            });
        }
        ImageButton resetTimeButton = (ImageButton) view.findViewById(R.id.resetTimeImageButton);
        if (!ListenerUtil.mutListener.listen(4534)) {
            resetTimeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(4530)) {
                        mMyCalendar = Calendar.getInstance();
                    }
                    if (!ListenerUtil.mutListener.listen(4531)) {
                        mBuilder.setDateTime(mMyCalendar);
                    }
                    if (!ListenerUtil.mutListener.listen(4532)) {
                        resetDateTimeLabels();
                    }
                    if (!ListenerUtil.mutListener.listen(4533)) {
                        checkRequestAndSubmit();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4535)) {
            setUpAutocomplete(mFromAddressTextArea, USE_FROM_ADDRESS);
        }
        if (!ListenerUtil.mutListener.listen(4536)) {
            setUpAutocomplete(mToAddressTextArea, USE_TO_ADDRESS);
        }
        if (!ListenerUtil.mutListener.listen(4541)) {
            mToCurrentLocationImageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(4537)) {
                        mToAddressTextArea.setText(getString(R.string.tripplanner_current_location));
                    }
                    if (!ListenerUtil.mutListener.listen(4538)) {
                        mToAddress = makeAddressFromLocation();
                    }
                    if (!ListenerUtil.mutListener.listen(4539)) {
                        mBuilder.setTo(mToAddress);
                    }
                    if (!ListenerUtil.mutListener.listen(4540)) {
                        checkRequestAndSubmit();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4546)) {
            mFromCurrentLocationImageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(4542)) {
                        mFromAddressTextArea.setText(getString(R.string.tripplanner_current_location));
                    }
                    if (!ListenerUtil.mutListener.listen(4543)) {
                        mFromAddress = makeAddressFromLocation();
                    }
                    if (!ListenerUtil.mutListener.listen(4544)) {
                        mBuilder.setFrom(mFromAddress);
                    }
                    if (!ListenerUtil.mutListener.listen(4545)) {
                        checkRequestAndSubmit();
                    }
                }
            });
        }
        // Start: default from address is Current Location, to address is unset
        return view;
    }

    public TripPlanFragment setPlanErrorUrl(String planErrorUrl) {
        if (!ListenerUtil.mutListener.listen(4547)) {
            mPlanErrorUrl = planErrorUrl;
        }
        return this;
    }

    public TripPlanFragment setPlanRequestUrl(String planRequestUrl) {
        if (!ListenerUtil.mutListener.listen(4548)) {
            mPlanRequestUrl = planRequestUrl;
        }
        return this;
    }

    private void resetDateTimeLabels() {
        String dateText = new SimpleDateFormat(OTPConstants.TRIP_PLAN_DATE_STRING_FORMAT, Locale.getDefault()).format(mMyCalendar.getTime());
        String timeText = new SimpleDateFormat(OTPConstants.TRIP_PLAN_TIME_STRING_FORMAT, Locale.getDefault()).format(mMyCalendar.getTime());
        if (!ListenerUtil.mutListener.listen(4549)) {
            mDateAdapter.insert(dateText, 0);
        }
        if (!ListenerUtil.mutListener.listen(4550)) {
            mDateAdapter.notifyDataSetChanged();
        }
        if (!ListenerUtil.mutListener.listen(4551)) {
            mTimeAdapter.insert(timeText, 0);
        }
        if (!ListenerUtil.mutListener.listen(4552)) {
            mTimeAdapter.notifyDataSetChanged();
        }
    }

    private void loadAndSetAdditionalTripPreferences() {
        int modeId = PreferenceUtils.getInt(getString(R.string.preference_key_trip_plan_travel_by), 0);
        double maxWalkDistance = PreferenceUtils.getDouble(getString(R.string.preference_key_trip_plan_maximum_walking_distance), 0);
        boolean optimizeTransfers = PreferenceUtils.getBoolean(getString(R.string.preference_key_trip_plan_minimize_transfers), false);
        boolean wheelchair = PreferenceUtils.getBoolean(getString(R.string.preference_key_trip_plan_avoid_stairs), false);
        if (!ListenerUtil.mutListener.listen(4553)) {
            mBuilder.setOptimizeTransfers(optimizeTransfers).setModeSetById(modeId).setWheelchairAccessible(wheelchair).setMaxWalkDistance(maxWalkDistance);
        }
    }

    private void checkRequestAndSubmit() {
        if (!ListenerUtil.mutListener.listen(4559)) {
            if ((ListenerUtil.mutListener.listen(4554) ? (mBuilder.ready() || mListener != null) : (mBuilder.ready() && mListener != null))) {
                if (!ListenerUtil.mutListener.listen(4555)) {
                    mFromAddressTextArea.dismissDropDown();
                }
                if (!ListenerUtil.mutListener.listen(4556)) {
                    mToAddressTextArea.dismissDropDown();
                }
                if (!ListenerUtil.mutListener.listen(4557)) {
                    UIUtils.closeKeyboard(getContext(), mFromAddressTextArea);
                }
                if (!ListenerUtil.mutListener.listen(4558)) {
                    mListener.onTripRequestReady();
                }
            }
        }
    }

    // Populate data fields
    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(4560)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(4561)) {
            mFromAddress = mBuilder.getFrom();
        }
        if (!ListenerUtil.mutListener.listen(4564)) {
            if (mFromAddress == null) {
                if (!ListenerUtil.mutListener.listen(4562)) {
                    mFromAddress = makeAddressFromLocation();
                }
                if (!ListenerUtil.mutListener.listen(4563)) {
                    mBuilder.setFrom(mFromAddress);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4565)) {
            setAddressText(mFromAddressTextArea, mFromAddress);
        }
        if (!ListenerUtil.mutListener.listen(4566)) {
            mToAddress = mBuilder.getTo();
        }
        if (!ListenerUtil.mutListener.listen(4569)) {
            if (mToAddress == null) {
                if (!ListenerUtil.mutListener.listen(4567)) {
                    mToAddress = CustomAddress.getEmptyAddress();
                }
                if (!ListenerUtil.mutListener.listen(4568)) {
                    mBuilder.setTo(mToAddress);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4570)) {
            setAddressText(mToAddressTextArea, mToAddress);
        }
        boolean arriving = mBuilder.getArriveBy();
        if (!ListenerUtil.mutListener.listen(4578)) {
            if (mMyCalendar == null) {
                Date date = mBuilder.getDateTime();
                if (!ListenerUtil.mutListener.listen(4572)) {
                    if (date == null) {
                        if (!ListenerUtil.mutListener.listen(4571)) {
                            date = new Date();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4573)) {
                    mMyCalendar = Calendar.getInstance();
                }
                if (!ListenerUtil.mutListener.listen(4574)) {
                    mMyCalendar.setTime(date);
                }
                if (!ListenerUtil.mutListener.listen(4577)) {
                    if (arriving) {
                        if (!ListenerUtil.mutListener.listen(4576)) {
                            mBuilder.setArrivalTime(mMyCalendar);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4575)) {
                            mBuilder.setDepartureTime(mMyCalendar);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4579)) {
            resetDateTimeLabels();
        }
        String leavingChoice = getString(arriving ? R.string.trip_plan_arriving : R.string.trip_plan_leaving);
        if (!ListenerUtil.mutListener.listen(4580)) {
            mLeavingChoice.setSelection(mLeavingChoiceAdapter.getPosition(leavingChoice), false);
        }
        if (!ListenerUtil.mutListener.listen(4585)) {
            mLeavingChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getItemAtPosition(position);
                    if (!ListenerUtil.mutListener.listen(4583)) {
                        if (item.equals(getString(R.string.trip_plan_arriving))) {
                            if (!ListenerUtil.mutListener.listen(4582)) {
                                mBuilder.setArrivalTime(mMyCalendar);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(4581)) {
                                mBuilder.setDepartureTime(mMyCalendar);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(4584)) {
                        checkRequestAndSubmit();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(4586)) {
            mFromAddressTextArea.dismissDropDown();
        }
        if (!ListenerUtil.mutListener.listen(4587)) {
            mToAddressTextArea.dismissDropDown();
        }
        if (!ListenerUtil.mutListener.listen(4589)) {
            if (BuildConfig.USE_PELIAS_GEOCODING) {
                if (!ListenerUtil.mutListener.listen(4588)) {
                    showTutorial(ShowcaseViewUtils.TUTORIAL_TRIP_PLAN_GEOCODER, (AppCompatActivity) getActivity(), null, true);
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(4590)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
        if (!ListenerUtil.mutListener.listen(4591)) {
            inflater.inflate(R.menu.menu_trip_plan, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (!ListenerUtil.mutListener.listen(4595)) {
            switch(id) {
                case R.id.action_settings:
                    if (!ListenerUtil.mutListener.listen(4592)) {
                        advancedSettings();
                    }
                    return true;
                case R.id.action_reverse:
                    if (!ListenerUtil.mutListener.listen(4593)) {
                        reverseTrip();
                    }
                    return true;
                case R.id.action_report_trip_problem:
                    if (!ListenerUtil.mutListener.listen(4594)) {
                        reportTripPlanProblem();
                    }
                    return true;
                default:
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set Listener of this fragment.
     */
    public void setListener(Listener listener) {
        if (!ListenerUtil.mutListener.listen(4596)) {
            mListener = listener;
        }
    }

    private void advancedSettings() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        final boolean unitsAreImperial = !PreferenceUtils.getUnitsAreMetricFromPreferences(getContext());
        if (!ListenerUtil.mutListener.listen(4597)) {
            dialogBuilder.setTitle(R.string.trip_plan_advanced_settings).setView(R.layout.trip_plan_advanced_settings_dialog);
        }
        if (!ListenerUtil.mutListener.listen(4612)) {
            dialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Dialog dialog = (Dialog) dialogInterface;
                    boolean optimizeTransfers = ((CheckBox) dialog.findViewById(R.id.checkbox_minimize_transfers)).isChecked();
                    Spinner spinnerTravelBy = (Spinner) dialog.findViewById(R.id.spinner_travel_by);
                    final TypedArray transitModeResource = getContext().getResources().obtainTypedArray(R.array.transit_mode_array);
                    String selectedItem = spinnerTravelBy.getSelectedItem().toString();
                    // We can't directly look up the resource ID by index because based on region we remove bikeshare options, so loop through instead
                    int resourceId = 0;
                    if (!ListenerUtil.mutListener.listen(4605)) {
                        {
                            long _loopCounter33 = 0;
                            for (int i = 0; (ListenerUtil.mutListener.listen(4604) ? (i >= transitModeResource.length()) : (ListenerUtil.mutListener.listen(4603) ? (i <= transitModeResource.length()) : (ListenerUtil.mutListener.listen(4602) ? (i > transitModeResource.length()) : (ListenerUtil.mutListener.listen(4601) ? (i != transitModeResource.length()) : (ListenerUtil.mutListener.listen(4600) ? (i == transitModeResource.length()) : (i < transitModeResource.length())))))); i++) {
                                ListenerUtil.loopListener.listen("_loopCounter33", ++_loopCounter33);
                                if (!ListenerUtil.mutListener.listen(4599)) {
                                    if (selectedItem.equals(transitModeResource.getString(i))) {
                                        if (!ListenerUtil.mutListener.listen(4598)) {
                                            resourceId = transitModeResource.getResourceId(i, 0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    int modeId = TripModes.getTripModeCodeFromSelection(resourceId);
                    boolean wheelchair = ((CheckBox) dialog.findViewById(R.id.checkbox_wheelchair_acccesible)).isChecked();
                    String maxWalkString = ((EditText) dialog.findViewById(R.id.number_maximum_walk_distance)).getText().toString();
                    double maxWalkDistance;
                    if (TextUtils.isEmpty(maxWalkString)) {
                        maxWalkDistance = Double.MAX_VALUE;
                    } else {
                        double d = Double.parseDouble(maxWalkString);
                        maxWalkDistance = unitsAreImperial ? ConversionUtils.feetToMeters(d) : d;
                    }
                    if (!ListenerUtil.mutListener.listen(4606)) {
                        mBuilder.setOptimizeTransfers(optimizeTransfers).setModeSetById(modeId).setWheelchairAccessible(wheelchair).setMaxWalkDistance(maxWalkDistance);
                    }
                    if (!ListenerUtil.mutListener.listen(4607)) {
                        // after app is closed
                        PreferenceUtils.saveInt(getString(R.string.preference_key_trip_plan_travel_by), modeId);
                    }
                    if (!ListenerUtil.mutListener.listen(4608)) {
                        PreferenceUtils.saveDouble(getString(R.string.preference_key_trip_plan_maximum_walking_distance), maxWalkDistance);
                    }
                    if (!ListenerUtil.mutListener.listen(4609)) {
                        PreferenceUtils.saveBoolean(getString(R.string.preference_key_trip_plan_minimize_transfers), optimizeTransfers);
                    }
                    if (!ListenerUtil.mutListener.listen(4610)) {
                        PreferenceUtils.saveBoolean(getString(R.string.preference_key_trip_plan_avoid_stairs), wheelchair);
                    }
                    if (!ListenerUtil.mutListener.listen(4611)) {
                        checkRequestAndSubmit();
                    }
                }
            });
        }
        final AlertDialog dialog = dialogBuilder.create();
        if (!ListenerUtil.mutListener.listen(4613)) {
            dialog.show();
        }
        CheckBox minimizeTransfersCheckbox = (CheckBox) dialog.findViewById(R.id.checkbox_minimize_transfers);
        Spinner spinnerTravelBy = (Spinner) dialog.findViewById(R.id.spinner_travel_by);
        CheckBox wheelchairCheckbox = (CheckBox) dialog.findViewById(R.id.checkbox_wheelchair_acccesible);
        EditText maxWalkEditText = (EditText) dialog.findViewById(R.id.number_maximum_walk_distance);
        if (!ListenerUtil.mutListener.listen(4614)) {
            minimizeTransfersCheckbox.setChecked(mBuilder.getOptimizeTransfers());
        }
        if (!ListenerUtil.mutListener.listen(4615)) {
            wheelchairCheckbox.setChecked(mBuilder.getWheelchairAccessible());
        }
        ArrayList<String> travelByOptions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.transit_mode_array)));
        if (!ListenerUtil.mutListener.listen(4618)) {
            // Remove opttions based on support of bikeshare enabled or not
            if (!Application.isBikeshareEnabled()) {
                if (!ListenerUtil.mutListener.listen(4616)) {
                    travelByOptions.remove(getString(R.string.transit_mode_bikeshare));
                }
                if (!ListenerUtil.mutListener.listen(4617)) {
                    travelByOptions.remove(getString(R.string.transit_mode_transit_and_bikeshare));
                }
            }
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, travelByOptions);
        if (!ListenerUtil.mutListener.listen(4619)) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(4620)) {
            spinnerTravelBy.setAdapter(adapter);
        }
        int modeSetId = mBuilder.getModeSetId();
        if (!ListenerUtil.mutListener.listen(4633)) {
            if ((ListenerUtil.mutListener.listen(4631) ? ((ListenerUtil.mutListener.listen(4625) ? (modeSetId >= -1) : (ListenerUtil.mutListener.listen(4624) ? (modeSetId <= -1) : (ListenerUtil.mutListener.listen(4623) ? (modeSetId > -1) : (ListenerUtil.mutListener.listen(4622) ? (modeSetId < -1) : (ListenerUtil.mutListener.listen(4621) ? (modeSetId == -1) : (modeSetId != -1)))))) || (ListenerUtil.mutListener.listen(4630) ? (modeSetId >= travelByOptions.size()) : (ListenerUtil.mutListener.listen(4629) ? (modeSetId <= travelByOptions.size()) : (ListenerUtil.mutListener.listen(4628) ? (modeSetId > travelByOptions.size()) : (ListenerUtil.mutListener.listen(4627) ? (modeSetId != travelByOptions.size()) : (ListenerUtil.mutListener.listen(4626) ? (modeSetId == travelByOptions.size()) : (modeSetId < travelByOptions.size()))))))) : ((ListenerUtil.mutListener.listen(4625) ? (modeSetId >= -1) : (ListenerUtil.mutListener.listen(4624) ? (modeSetId <= -1) : (ListenerUtil.mutListener.listen(4623) ? (modeSetId > -1) : (ListenerUtil.mutListener.listen(4622) ? (modeSetId < -1) : (ListenerUtil.mutListener.listen(4621) ? (modeSetId == -1) : (modeSetId != -1)))))) && (ListenerUtil.mutListener.listen(4630) ? (modeSetId >= travelByOptions.size()) : (ListenerUtil.mutListener.listen(4629) ? (modeSetId <= travelByOptions.size()) : (ListenerUtil.mutListener.listen(4628) ? (modeSetId > travelByOptions.size()) : (ListenerUtil.mutListener.listen(4627) ? (modeSetId != travelByOptions.size()) : (ListenerUtil.mutListener.listen(4626) ? (modeSetId == travelByOptions.size()) : (modeSetId < travelByOptions.size()))))))))) {
                if (!ListenerUtil.mutListener.listen(4632)) {
                    spinnerTravelBy.setSelection(TripModes.getSpinnerPositionFromSeledctedCode(modeSetId));
                }
            }
        }
        Double maxWalk = mBuilder.getMaxWalkDistance();
        if (!ListenerUtil.mutListener.listen(4643)) {
            if ((ListenerUtil.mutListener.listen(4639) ? (maxWalk != null || (ListenerUtil.mutListener.listen(4638) ? (Double.MAX_VALUE >= maxWalk) : (ListenerUtil.mutListener.listen(4637) ? (Double.MAX_VALUE <= maxWalk) : (ListenerUtil.mutListener.listen(4636) ? (Double.MAX_VALUE > maxWalk) : (ListenerUtil.mutListener.listen(4635) ? (Double.MAX_VALUE < maxWalk) : (ListenerUtil.mutListener.listen(4634) ? (Double.MAX_VALUE == maxWalk) : (Double.MAX_VALUE != maxWalk))))))) : (maxWalk != null && (ListenerUtil.mutListener.listen(4638) ? (Double.MAX_VALUE >= maxWalk) : (ListenerUtil.mutListener.listen(4637) ? (Double.MAX_VALUE <= maxWalk) : (ListenerUtil.mutListener.listen(4636) ? (Double.MAX_VALUE > maxWalk) : (ListenerUtil.mutListener.listen(4635) ? (Double.MAX_VALUE < maxWalk) : (ListenerUtil.mutListener.listen(4634) ? (Double.MAX_VALUE == maxWalk) : (Double.MAX_VALUE != maxWalk))))))))) {
                if (!ListenerUtil.mutListener.listen(4641)) {
                    if (unitsAreImperial) {
                        if (!ListenerUtil.mutListener.listen(4640)) {
                            maxWalk = ConversionUtils.metersToFeet(maxWalk);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4642)) {
                    maxWalkEditText.setText(String.format("%d", maxWalk.longValue()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4645)) {
            if (unitsAreImperial) {
                TextView label = (TextView) dialog.findViewById(R.id.label_minimum_walk_distance);
                if (!ListenerUtil.mutListener.listen(4644)) {
                    label.setText(getString(R.string.feet_abbreviation));
                }
            }
        }
    }

    private void reverseTrip() {
        if (!ListenerUtil.mutListener.listen(4646)) {
            mFromAddress = mBuilder.getTo();
        }
        if (!ListenerUtil.mutListener.listen(4647)) {
            mToAddress = mBuilder.getFrom();
        }
        if (!ListenerUtil.mutListener.listen(4648)) {
            mBuilder.setFrom(mFromAddress).setTo(mToAddress);
        }
        if (!ListenerUtil.mutListener.listen(4649)) {
            setAddressText(mFromAddressTextArea, mFromAddress);
        }
        if (!ListenerUtil.mutListener.listen(4650)) {
            setAddressText(mToAddressTextArea, mToAddress);
        }
        if (!ListenerUtil.mutListener.listen(4653)) {
            if ((ListenerUtil.mutListener.listen(4651) ? (mBuilder.ready() || mListener != null) : (mBuilder.ready() && mListener != null))) {
                if (!ListenerUtil.mutListener.listen(4652)) {
                    mListener.onTripRequestReady();
                }
            }
        }
    }

    private void reportTripPlanProblem() {
        String email = Application.get().getCurrentRegion().getOtpContactEmail();
        if (!ListenerUtil.mutListener.listen(4661)) {
            if (!TextUtils.isEmpty(email)) {
                Location loc = Application.getLastKnownLocation(getActivity().getApplicationContext(), null);
                String locString = null;
                if (!ListenerUtil.mutListener.listen(4655)) {
                    if (loc != null) {
                        if (!ListenerUtil.mutListener.listen(4654)) {
                            locString = LocationUtils.printLocationDetails(loc);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4659)) {
                    if (mPlanErrorUrl != null) {
                        if (!ListenerUtil.mutListener.listen(4658)) {
                            UIUtils.sendEmail(getActivity(), email, locString, mPlanErrorUrl, true);
                        }
                    } else if (mPlanRequestUrl != null) {
                        if (!ListenerUtil.mutListener.listen(4657)) {
                            UIUtils.sendEmail(getActivity(), email, locString, mPlanRequestUrl, false);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(4656)) {
                            UIUtils.sendEmail(getActivity(), email, locString, null, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(4660)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_label_app_feedback_otp), null);
                }
            }
        }
    }

    private void setAddressText(TextView tv, CustomAddress address) {
        if (!ListenerUtil.mutListener.listen(4665)) {
            if ((ListenerUtil.mutListener.listen(4662) ? (address != null || address.getAddressLine(0) != null) : (address != null && address.getAddressLine(0) != null))) {
                if (!ListenerUtil.mutListener.listen(4664)) {
                    tv.setText(address.toString());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4663)) {
                    tv.setText(null);
                }
            }
        }
    }

    private CustomAddress makeAddressFromLocation() {
        CustomAddress address = CustomAddress.getEmptyAddress();
        Location loc = Application.getLastKnownLocation(getContext(), mGoogleApiClient);
        if (!ListenerUtil.mutListener.listen(4670)) {
            if (loc == null) {
                if (!ListenerUtil.mutListener.listen(4669)) {
                    if (getContext() != null) {
                        if (!ListenerUtil.mutListener.listen(4668)) {
                            Toast.makeText(getContext(), getString(R.string.no_location_permission), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(4666)) {
                    address.setLatitude(loc.getLatitude());
                }
                if (!ListenerUtil.mutListener.listen(4667)) {
                    address.setLongitude(loc.getLongitude());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4671)) {
            address.setAddressLine(0, getString(R.string.tripplanner_current_location));
        }
        return address;
    }

    /**
     * Receives a geocoding result from the Google Places SDK
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!ListenerUtil.mutListener.listen(4678)) {
            if ((ListenerUtil.mutListener.listen(4676) ? (resultCode >= -1) : (ListenerUtil.mutListener.listen(4675) ? (resultCode <= -1) : (ListenerUtil.mutListener.listen(4674) ? (resultCode > -1) : (ListenerUtil.mutListener.listen(4673) ? (resultCode < -1) : (ListenerUtil.mutListener.listen(4672) ? (resultCode == -1) : (resultCode != -1))))))) {
                if (!ListenerUtil.mutListener.listen(4677)) {
                    Log.e(TAG, "Error getting geocoding results");
                }
                return;
            }
        }
        CustomAddress address = ProprietaryMapHelpV2.getCustomAddressFromPlacesIntent(Application.get().getApplicationContext(), intent);
        if (!ListenerUtil.mutListener.listen(4695)) {
            // Note that onResume will run after this function. We need to put new objects in the bundle.
            if ((ListenerUtil.mutListener.listen(4683) ? (requestCode >= USE_FROM_ADDRESS) : (ListenerUtil.mutListener.listen(4682) ? (requestCode <= USE_FROM_ADDRESS) : (ListenerUtil.mutListener.listen(4681) ? (requestCode > USE_FROM_ADDRESS) : (ListenerUtil.mutListener.listen(4680) ? (requestCode < USE_FROM_ADDRESS) : (ListenerUtil.mutListener.listen(4679) ? (requestCode != USE_FROM_ADDRESS) : (requestCode == USE_FROM_ADDRESS))))))) {
                if (!ListenerUtil.mutListener.listen(4692)) {
                    mFromAddress = address;
                }
                if (!ListenerUtil.mutListener.listen(4693)) {
                    mBuilder.setFrom(mFromAddress);
                }
                if (!ListenerUtil.mutListener.listen(4694)) {
                    mFromAddressTextArea.setText(mFromAddress.toString());
                }
            } else if ((ListenerUtil.mutListener.listen(4688) ? (requestCode >= USE_TO_ADDRESS) : (ListenerUtil.mutListener.listen(4687) ? (requestCode <= USE_TO_ADDRESS) : (ListenerUtil.mutListener.listen(4686) ? (requestCode > USE_TO_ADDRESS) : (ListenerUtil.mutListener.listen(4685) ? (requestCode < USE_TO_ADDRESS) : (ListenerUtil.mutListener.listen(4684) ? (requestCode != USE_TO_ADDRESS) : (requestCode == USE_TO_ADDRESS))))))) {
                if (!ListenerUtil.mutListener.listen(4689)) {
                    mToAddress = address;
                }
                if (!ListenerUtil.mutListener.listen(4690)) {
                    mBuilder.setTo(mToAddress);
                }
                if (!ListenerUtil.mutListener.listen(4691)) {
                    mToAddressTextArea.setText(mToAddress.toString());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4696)) {
            checkRequestAndSubmit();
        }
    }

    private void setUpAutocomplete(AutoCompleteTextView tv, final int use) {
        ObaRegion region = Application.get().getCurrentRegion();
        if (!ListenerUtil.mutListener.listen(4700)) {
            // Use Google Places widget if build config uses it and it's available
            if ((ListenerUtil.mutListener.listen(4697) ? (!BuildConfig.USE_PELIAS_GEOCODING || GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext()) == ConnectionResult.SUCCESS) : (!BuildConfig.USE_PELIAS_GEOCODING && GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext()) == ConnectionResult.SUCCESS))) {
                if (!ListenerUtil.mutListener.listen(4698)) {
                    tv.setFocusable(false);
                }
                if (!ListenerUtil.mutListener.listen(4699)) {
                    tv.setOnClickListener(new ProprietaryMapHelpV2.StartPlacesAutocompleteOnClick(use, this, region));
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(4701)) {
            // Set up autocomplete with Pelias geocoder
            tv.setAdapter(new PlacesAutoCompleteAdapter(getContext(), R.layout.geocode_result, region));
        }
        if (!ListenerUtil.mutListener.listen(4702)) {
            tv.setOnItemClickListener((parent, view, position, id) -> {
                CustomAddress addr = (CustomAddress) parent.getAdapter().getItem(position);
                if (use == USE_FROM_ADDRESS) {
                    mFromAddress = addr;
                    mBuilder.setFrom(mFromAddress);
                } else if (use == USE_TO_ADDRESS) {
                    mToAddress = addr;
                    mBuilder.setTo(mToAddress);
                }
                checkRequestAndSubmit();
            });
        }
        if (!ListenerUtil.mutListener.listen(4703)) {
            tv.dismissDropDown();
        }
    }
}
