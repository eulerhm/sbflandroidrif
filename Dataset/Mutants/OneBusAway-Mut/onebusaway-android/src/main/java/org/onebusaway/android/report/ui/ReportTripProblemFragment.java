/*
 * Copyright (C) 2012 Paul Watts (paulcwatts@gmail.com)
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
package org.onebusaway.android.report.ui;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaArrivalInfo;
import org.onebusaway.android.io.request.ObaReportProblemWithTripRequest;
import org.onebusaway.android.util.UIUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReportTripProblemFragment extends ReportProblemFragmentBase {

    public static final String TRIP_ID = ".TripId";

    public static final String STOP_ID = ".StopId";

    public static final String TRIP_HEADSIGN = ".TripHeadsign";

    public static final String TRIP_SERVICE_DATE = ".ServiceDate";

    public static final String TRIP_VEHICLE_ID = ".VehicleId";

    public static final String CODE = ".Code";

    public static final String USER_COMMENT = ".UserComment";

    public static final String USER_ON_VEHICLE = ".UserOnVehicle";

    public static final String USER_VEHICLE_NUM = ".UserVehicleNum";

    public static final String TAG = "RprtTripProblemFragment";

    public static void show(AppCompatActivity activity, ObaArrivalInfo arrival) {
        if (!ListenerUtil.mutListener.listen(11174)) {
            show(activity, arrival, null);
        }
    }

    public static void show(AppCompatActivity activity, ObaArrivalInfo arrival, Integer containerViewId) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(11175)) {
            args.putString(TRIP_ID, arrival.getTripId());
        }
        if (!ListenerUtil.mutListener.listen(11176)) {
            args.putString(STOP_ID, arrival.getStopId());
        }
        if (!ListenerUtil.mutListener.listen(11177)) {
            args.putString(TRIP_HEADSIGN, arrival.getHeadsign());
        }
        if (!ListenerUtil.mutListener.listen(11178)) {
            args.putLong(TRIP_SERVICE_DATE, arrival.getServiceDate());
        }
        if (!ListenerUtil.mutListener.listen(11179)) {
            args.putString(TRIP_VEHICLE_ID, arrival.getVehicleId());
        }
        // Create the list fragment and add it as our sole content.
        ReportTripProblemFragment content = new ReportTripProblemFragment();
        if (!ListenerUtil.mutListener.listen(11180)) {
            content.setArguments(args);
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (!ListenerUtil.mutListener.listen(11183)) {
            if (containerViewId == null) {
                if (!ListenerUtil.mutListener.listen(11182)) {
                    ft.replace(android.R.id.content, content, TAG);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11181)) {
                    ft.replace(containerViewId, content, TAG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11184)) {
            ft.addToBackStack(null);
        }
        try {
            if (!ListenerUtil.mutListener.listen(11186)) {
                ft.commit();
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(11185)) {
                Log.e(TAG, "Cannot show ReportTripProblemFragment after onSaveInstanceState has been called");
            }
        }
    }

    private TextView mUserComment;

    private CheckBox mUserOnVehicle;

    private TextView mUserVehicle;

    @Override
    protected int getLayoutId() {
        return R.layout.report_trip_problem;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Set the stop name.
        Bundle args = getArguments();
        final TextView tripHeadsign = (TextView) view.findViewById(R.id.report_problem_headsign);
        if (!ListenerUtil.mutListener.listen(11187)) {
            tripHeadsign.setText(UIUtils.formatDisplayText(args.getString(TRIP_HEADSIGN)));
        }
        // TODO: Switch this based on the trip mode
        final int tripArray = R.array.report_trip_problem_code_bus;
        if (!ListenerUtil.mutListener.listen(11188)) {
            // 
            mCodeView = (Spinner) view.findViewById(R.id.report_problem_code);
        }
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity(), tripArray, android.R.layout.simple_spinner_item);
        if (!ListenerUtil.mutListener.listen(11189)) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(11190)) {
            mCodeView.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(11191)) {
            // Comment
            mUserComment = (TextView) view.findViewById(R.id.report_problem_comment);
        }
        if (!ListenerUtil.mutListener.listen(11192)) {
            // On vehicle
            mUserOnVehicle = (CheckBox) view.findViewById(R.id.report_problem_onvehicle);
        }
        if (!ListenerUtil.mutListener.listen(11193)) {
            mUserVehicle = (EditText) view.findViewById(R.id.report_problem_uservehicle);
        }
        if (!ListenerUtil.mutListener.listen(11194)) {
            // Disabled by default
            mUserVehicle.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(11196)) {
            mUserOnVehicle.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    boolean checked = mUserOnVehicle.isChecked();
                    if (!ListenerUtil.mutListener.listen(11195)) {
                        mUserVehicle.setEnabled(checked);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11202)) {
            if (savedInstanceState != null) {
                int position = savedInstanceState.getInt(CODE);
                if (!ListenerUtil.mutListener.listen(11197)) {
                    mCodeView.setSelection(position);
                }
                CharSequence comment = savedInstanceState.getCharSequence(USER_COMMENT);
                if (!ListenerUtil.mutListener.listen(11198)) {
                    mUserComment.setText(comment);
                }
                boolean onVehicle = savedInstanceState.getBoolean(USER_ON_VEHICLE);
                if (!ListenerUtil.mutListener.listen(11199)) {
                    mUserOnVehicle.setChecked(onVehicle);
                }
                CharSequence num = savedInstanceState.getCharSequence(USER_VEHICLE_NUM);
                if (!ListenerUtil.mutListener.listen(11200)) {
                    mUserVehicle.setText(num);
                }
                if (!ListenerUtil.mutListener.listen(11201)) {
                    mUserVehicle.setEnabled(onVehicle);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11203)) {
            SPINNER_TO_CODE = new String[] { null, ObaReportProblemWithTripRequest.VEHICLE_NEVER_CAME, ObaReportProblemWithTripRequest.VEHICLE_CAME_EARLY, ObaReportProblemWithTripRequest.VEHICLE_CAME_LATE, ObaReportProblemWithTripRequest.WRONG_HEADSIGN, ObaReportProblemWithTripRequest.VEHICLE_DOES_NOT_STOP_HERE, ObaReportProblemWithTripRequest.OTHER };
        }
        if (!ListenerUtil.mutListener.listen(11204)) {
            setupIconColors();
        }
    }

    private void setupIconColors() {
        if (!ListenerUtil.mutListener.listen(11205)) {
            ((ImageView) getActivity().findViewById(R.id.ic_category)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11206)) {
            ((ImageView) getActivity().findViewById(R.id.ic_trip_info)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11207)) {
            ((ImageView) getActivity().findViewById(R.id.ic_headsign_info)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(11208)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(11209)) {
            outState.putInt(CODE, mCodeView.getSelectedItemPosition());
        }
        if (!ListenerUtil.mutListener.listen(11210)) {
            outState.putCharSequence(USER_COMMENT, mUserComment.getText());
        }
        if (!ListenerUtil.mutListener.listen(11211)) {
            outState.putBoolean(USER_ON_VEHICLE, mUserOnVehicle.isChecked());
        }
        if (!ListenerUtil.mutListener.listen(11212)) {
            outState.putCharSequence(USER_VEHICLE_NUM, mUserVehicle.getText());
        }
    }

    @Override
    protected void sendReport() {
        // Hide the soft keyboard.
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(11213)) {
            imm.hideSoftInputFromWindow(mUserComment.getWindowToken(), 0);
        }
        if (!ListenerUtil.mutListener.listen(11217)) {
            if (isReportArgumentsValid()) {
                if (!ListenerUtil.mutListener.listen(11215)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_report_trip_problem));
                }
                if (!ListenerUtil.mutListener.listen(11216)) {
                    super.sendReport();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11214)) {
                    // Show error message if report arguments is not valid
                    Toast.makeText(getActivity(), getString(R.string.report_problem_invalid_argument), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected ReportLoader createLoader(Bundle args) {
        // Trip ID
        String tripId = args.getString(TRIP_ID);
        ObaReportProblemWithTripRequest.Builder builder = new ObaReportProblemWithTripRequest.Builder(getActivity(), tripId);
        if (!ListenerUtil.mutListener.listen(11218)) {
            builder.setStopId(args.getString(STOP_ID));
        }
        if (!ListenerUtil.mutListener.listen(11219)) {
            builder.setVehicleId(args.getString(TRIP_VEHICLE_ID));
        }
        if (!ListenerUtil.mutListener.listen(11220)) {
            builder.setServiceDate(args.getLong(TRIP_SERVICE_DATE));
        }
        // Code
        String code = SPINNER_TO_CODE[mCodeView.getSelectedItemPosition()];
        if (!ListenerUtil.mutListener.listen(11222)) {
            if (code != null) {
                if (!ListenerUtil.mutListener.listen(11221)) {
                    builder.setCode(code);
                }
            }
        }
        // Comment
        CharSequence comment = mUserComment.getText();
        if (!ListenerUtil.mutListener.listen(11224)) {
            if (!TextUtils.isEmpty(comment)) {
                if (!ListenerUtil.mutListener.listen(11223)) {
                    builder.setUserComment(comment.toString());
                }
            }
        }
        // Location / Location accuracy
        Location location = Application.getLastKnownLocation(getActivity(), mGoogleApiClient);
        if (!ListenerUtil.mutListener.listen(11228)) {
            if (location != null) {
                if (!ListenerUtil.mutListener.listen(11225)) {
                    builder.setUserLocation(location.getLatitude(), location.getLongitude());
                }
                if (!ListenerUtil.mutListener.listen(11227)) {
                    if (location.hasAccuracy()) {
                        if (!ListenerUtil.mutListener.listen(11226)) {
                            builder.setUserLocationAccuracy((int) location.getAccuracy());
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11229)) {
            // User on vehicle?
            builder.setUserOnVehicle(mUserOnVehicle.isChecked());
        }
        // User Vehicle Number
        CharSequence vehicleNum = mUserVehicle.getText();
        if (!ListenerUtil.mutListener.listen(11231)) {
            if (!TextUtils.isEmpty(vehicleNum)) {
                if (!ListenerUtil.mutListener.listen(11230)) {
                    builder.setUserVehicleNumber(vehicleNum.toString());
                }
            }
        }
        return new ReportLoader(getActivity(), builder.build());
    }
}
