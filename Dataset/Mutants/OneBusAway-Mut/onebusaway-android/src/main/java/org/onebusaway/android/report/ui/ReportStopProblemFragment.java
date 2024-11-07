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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.onebusaway.android.R;
import org.onebusaway.android.app.Application;
import org.onebusaway.android.io.ObaAnalytics;
import org.onebusaway.android.io.elements.ObaStop;
import org.onebusaway.android.io.request.ObaReportProblemWithStopRequest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReportStopProblemFragment extends ReportProblemFragmentBase {

    public static final String STOP_ID = ".StopId";

    public static final String STOP_NAME = ".StopName";

    public static final String CODE = ".Code";

    public static final String USER_COMMENT = ".UserComment";

    public static final String TAG = "RprtStopProblemFragment";

    public static void show(AppCompatActivity activity, ObaStop stop, Integer containerViewId) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Bundle args = new Bundle();
        if (!ListenerUtil.mutListener.listen(11030)) {
            args.putString(STOP_ID, stop.getId());
        }
        if (!ListenerUtil.mutListener.listen(11031)) {
            // We don't use the stop name map here...we want the actual stop name.
            args.putString(STOP_NAME, stop.getName());
        }
        // Create the list fragment and add it as our sole content.
        ReportStopProblemFragment content = new ReportStopProblemFragment();
        if (!ListenerUtil.mutListener.listen(11032)) {
            content.setArguments(args);
        }
        FragmentTransaction ft = fm.beginTransaction();
        if (!ListenerUtil.mutListener.listen(11035)) {
            if (containerViewId == null) {
                if (!ListenerUtil.mutListener.listen(11034)) {
                    ft.replace(android.R.id.content, content, TAG);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11033)) {
                    ft.replace(containerViewId, content, TAG);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11036)) {
            ft.addToBackStack(null);
        }
        try {
            if (!ListenerUtil.mutListener.listen(11038)) {
                ft.commit();
            }
        } catch (IllegalStateException e) {
            if (!ListenerUtil.mutListener.listen(11037)) {
                Log.e(TAG, "Cannot show ReportStopProblemFragment after onSaveInstanceState has been called");
            }
        }
    }

    private TextView mUserComment;

    @Override
    protected int getLayoutId() {
        return R.layout.report_stop_problem;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11039)) {
            // The code spinner
            mCodeView = (Spinner) view.findViewById(R.id.report_problem_code);
        }
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.report_stop_problem_code, android.R.layout.simple_spinner_item);
        if (!ListenerUtil.mutListener.listen(11040)) {
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(11041)) {
            mCodeView.setAdapter(adapter);
        }
        if (!ListenerUtil.mutListener.listen(11042)) {
            // Comment
            mUserComment = (TextView) view.findViewById(R.id.report_problem_comment);
        }
        if (!ListenerUtil.mutListener.listen(11045)) {
            if (savedInstanceState != null) {
                int position = savedInstanceState.getInt(CODE);
                if (!ListenerUtil.mutListener.listen(11043)) {
                    mCodeView.setSelection(position);
                }
                CharSequence comment = savedInstanceState.getCharSequence(USER_COMMENT);
                if (!ListenerUtil.mutListener.listen(11044)) {
                    mUserComment.setText(comment);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11046)) {
            SPINNER_TO_CODE = new String[] { null, ObaReportProblemWithStopRequest.NAME_WRONG, ObaReportProblemWithStopRequest.NUMBER_WRONG, ObaReportProblemWithStopRequest.LOCATION_WRONG, ObaReportProblemWithStopRequest.ROUTE_OR_TRIP_MISSING, ObaReportProblemWithStopRequest.OTHER };
        }
        if (!ListenerUtil.mutListener.listen(11047)) {
            // Dynamically change the color of the small icons
            setupIconColors();
        }
    }

    private void setupIconColors() {
        if (!ListenerUtil.mutListener.listen(11048)) {
            ((ImageView) getActivity().findViewById(R.id.ic_category)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
        if (!ListenerUtil.mutListener.listen(11049)) {
            ((ImageView) getActivity().findViewById(R.id.ic_action_info)).setColorFilter(getResources().getColor(R.color.material_gray));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(11050)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(11051)) {
            outState.putInt(CODE, mCodeView.getSelectedItemPosition());
        }
        if (!ListenerUtil.mutListener.listen(11052)) {
            outState.putCharSequence(USER_COMMENT, mUserComment.getText());
        }
    }

    @Override
    protected void sendReport() {
        // Hide the soft keyboard.
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!ListenerUtil.mutListener.listen(11053)) {
            imm.hideSoftInputFromWindow(mUserComment.getWindowToken(), 0);
        }
        if (!ListenerUtil.mutListener.listen(11057)) {
            if (isReportArgumentsValid()) {
                if (!ListenerUtil.mutListener.listen(11055)) {
                    ObaAnalytics.reportUiEvent(mFirebaseAnalytics, getString(R.string.analytics_problem), getString(R.string.analytics_label_report_stop_problem));
                }
                if (!ListenerUtil.mutListener.listen(11056)) {
                    super.sendReport();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11054)) {
                    // Show error message if report arguments is not valid
                    Toast.makeText(getActivity(), getString(R.string.report_problem_invalid_argument), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected ReportLoader createLoader(Bundle args) {
        String stopId = args.getString(STOP_ID);
        ObaReportProblemWithStopRequest.Builder builder = new ObaReportProblemWithStopRequest.Builder(getActivity(), stopId);
        // Code
        String code = SPINNER_TO_CODE[mCodeView.getSelectedItemPosition()];
        if (!ListenerUtil.mutListener.listen(11059)) {
            if (code != null) {
                if (!ListenerUtil.mutListener.listen(11058)) {
                    builder.setCode(code);
                }
            }
        }
        // Comment
        CharSequence comment = mUserComment.getText();
        if (!ListenerUtil.mutListener.listen(11061)) {
            if (!TextUtils.isEmpty(comment)) {
                if (!ListenerUtil.mutListener.listen(11060)) {
                    builder.setUserComment(comment.toString());
                }
            }
        }
        // Location / Location accuracy
        Location location = Application.getLastKnownLocation(getActivity(), mGoogleApiClient);
        if (!ListenerUtil.mutListener.listen(11065)) {
            if (location != null) {
                if (!ListenerUtil.mutListener.listen(11062)) {
                    builder.setUserLocation(location.getLatitude(), location.getLongitude());
                }
                if (!ListenerUtil.mutListener.listen(11064)) {
                    if (location.hasAccuracy()) {
                        if (!ListenerUtil.mutListener.listen(11063)) {
                            builder.setUserLocationAccuracy((int) location.getAccuracy());
                        }
                    }
                }
            }
        }
        return new ReportLoader(getActivity(), builder.build());
    }
}
