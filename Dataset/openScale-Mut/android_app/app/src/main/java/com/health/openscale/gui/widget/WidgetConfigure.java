/* Copyright (C) 2018 Erik Johansson <erik@ejohansson.se>
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.health.openscale.gui.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableRow;
import androidx.appcompat.app.AppCompatActivity;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.gui.measurement.MeasurementView;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class WidgetConfigure extends AppCompatActivity {

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(9238)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(9239)) {
            setResult(RESULT_CANCELED);
        }
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (!ListenerUtil.mutListener.listen(9241)) {
            if (extras != null) {
                if (!ListenerUtil.mutListener.listen(9240)) {
                    appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9243)) {
            if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                if (!ListenerUtil.mutListener.listen(9242)) {
                    finish();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9244)) {
            setContentView(R.layout.widget_configuration);
        }
        OpenScale openScale = OpenScale.getInstance();
        // Set up user spinner
        final Spinner userSpinner = findViewById(R.id.widget_user_spinner);
        List<String> users = new ArrayList<>();
        final List<Integer> userIds = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9247)) {
            {
                long _loopCounter116 = 0;
                for (ScaleUser scaleUser : openScale.getScaleUserList()) {
                    ListenerUtil.loopListener.listen("_loopCounter116", ++_loopCounter116);
                    if (!ListenerUtil.mutListener.listen(9245)) {
                        users.add(scaleUser.getUserName());
                    }
                    if (!ListenerUtil.mutListener.listen(9246)) {
                        userIds.add(scaleUser.getId());
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9257)) {
            // Hide user selector when there's only one user
            if ((ListenerUtil.mutListener.listen(9252) ? (users.size() >= 1) : (ListenerUtil.mutListener.listen(9251) ? (users.size() <= 1) : (ListenerUtil.mutListener.listen(9250) ? (users.size() > 1) : (ListenerUtil.mutListener.listen(9249) ? (users.size() < 1) : (ListenerUtil.mutListener.listen(9248) ? (users.size() != 1) : (users.size() == 1))))))) {
                TableRow row = (TableRow) userSpinner.getParent();
                if (!ListenerUtil.mutListener.listen(9256)) {
                    row.setVisibility(View.GONE);
                }
            } else if (users.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(9253)) {
                    users.add(getResources().getString(R.string.info_no_selected_user));
                }
                if (!ListenerUtil.mutListener.listen(9254)) {
                    userIds.add(-1);
                }
                if (!ListenerUtil.mutListener.listen(9255)) {
                    findViewById(R.id.widget_save).setEnabled(false);
                }
            }
        }
        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users);
        if (!ListenerUtil.mutListener.listen(9258)) {
            userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(9259)) {
            userSpinner.setAdapter(userAdapter);
        }
        // Set up measurement spinner
        final Spinner measurementSpinner = findViewById(R.id.widget_measurement_spinner);
        List<String> measurements = new ArrayList<>();
        final List<String> measurementKeys = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9263)) {
            {
                long _loopCounter117 = 0;
                for (MeasurementView measurementView : MeasurementView.getMeasurementList(this, MeasurementView.DateTimeOrder.NONE)) {
                    ListenerUtil.loopListener.listen("_loopCounter117", ++_loopCounter117);
                    if (!ListenerUtil.mutListener.listen(9262)) {
                        if (measurementView.isVisible()) {
                            if (!ListenerUtil.mutListener.listen(9260)) {
                                measurements.add(measurementView.getName().toString());
                            }
                            if (!ListenerUtil.mutListener.listen(9261)) {
                                measurementKeys.add(measurementView.getKey());
                            }
                        }
                    }
                }
            }
        }
        ArrayAdapter<String> measurementAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, measurements);
        if (!ListenerUtil.mutListener.listen(9264)) {
            measurementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        if (!ListenerUtil.mutListener.listen(9265)) {
            measurementSpinner.setAdapter(measurementAdapter);
        }
        if (!ListenerUtil.mutListener.listen(9272)) {
            findViewById(R.id.widget_save).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int userId = userIds.get(userSpinner.getSelectedItemPosition());
                    String measurementKey = measurementKeys.get(measurementSpinner.getSelectedItemPosition());
                    if (!ListenerUtil.mutListener.listen(9266)) {
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt(WidgetProvider.getUserIdPreferenceName(appWidgetId), userId).putString(WidgetProvider.getMeasurementPreferenceName(appWidgetId), measurementKey).apply();
                    }
                    Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null);
                    if (!ListenerUtil.mutListener.listen(9267)) {
                        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[] { appWidgetId });
                    }
                    if (!ListenerUtil.mutListener.listen(9268)) {
                        sendBroadcast(intent);
                    }
                    Intent resultValue = new Intent();
                    if (!ListenerUtil.mutListener.listen(9269)) {
                        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                    }
                    if (!ListenerUtil.mutListener.listen(9270)) {
                        setResult(RESULT_OK, resultValue);
                    }
                    if (!ListenerUtil.mutListener.listen(9271)) {
                        finish();
                    }
                }
            });
        }
    }
}
