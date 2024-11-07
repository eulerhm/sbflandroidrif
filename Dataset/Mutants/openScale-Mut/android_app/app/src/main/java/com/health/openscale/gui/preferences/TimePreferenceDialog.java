/*
 * Copyright (C) 2020 olie.xdev <olie.xdev@googlemail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.health.openscale.gui.preferences;

import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;
import com.health.openscale.R;
import java.util.Calendar;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TimePreferenceDialog extends PreferenceDialogFragmentCompat {

    private Calendar calendar;

    private TimePicker timePicker;

    public static TimePreferenceDialog newInstance(String key) {
        final TimePreferenceDialog fragment = new TimePreferenceDialog();
        final Bundle b = new Bundle(1);
        if (!ListenerUtil.mutListener.listen(8543)) {
            b.putString(ARG_KEY, key);
        }
        if (!ListenerUtil.mutListener.listen(8544)) {
            fragment.setArguments(b);
        }
        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        if (!ListenerUtil.mutListener.listen(8545)) {
            super.onBindDialogView(view);
        }
        if (!ListenerUtil.mutListener.listen(8546)) {
            timePicker = view.findViewById(R.id.timePicker);
        }
        if (!ListenerUtil.mutListener.listen(8547)) {
            calendar = Calendar.getInstance();
        }
        Long timeInMillis = null;
        DialogPreference preference = getPreference();
        if (!ListenerUtil.mutListener.listen(8549)) {
            if (preference instanceof TimePreference) {
                TimePreference timePreference = (TimePreference) preference;
                if (!ListenerUtil.mutListener.listen(8548)) {
                    timeInMillis = timePreference.getTimeInMillis();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8554)) {
            if (timeInMillis != null) {
                if (!ListenerUtil.mutListener.listen(8550)) {
                    calendar.setTimeInMillis(timeInMillis);
                }
                boolean is24hour = DateFormat.is24HourFormat(getContext());
                if (!ListenerUtil.mutListener.listen(8551)) {
                    timePicker.setIs24HourView(is24hour);
                }
                if (!ListenerUtil.mutListener.listen(8552)) {
                    timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
                }
                if (!ListenerUtil.mutListener.listen(8553)) {
                    timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
                }
            }
        }
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (!ListenerUtil.mutListener.listen(8566)) {
            if (positiveResult) {
                int hours;
                int minutes;
                if ((ListenerUtil.mutListener.listen(8559) ? (Build.VERSION.SDK_INT <= 23) : (ListenerUtil.mutListener.listen(8558) ? (Build.VERSION.SDK_INT > 23) : (ListenerUtil.mutListener.listen(8557) ? (Build.VERSION.SDK_INT < 23) : (ListenerUtil.mutListener.listen(8556) ? (Build.VERSION.SDK_INT != 23) : (ListenerUtil.mutListener.listen(8555) ? (Build.VERSION.SDK_INT == 23) : (Build.VERSION.SDK_INT >= 23))))))) {
                    hours = timePicker.getHour();
                    minutes = timePicker.getMinute();
                } else {
                    hours = timePicker.getCurrentHour();
                    minutes = timePicker.getCurrentMinute();
                }
                if (!ListenerUtil.mutListener.listen(8560)) {
                    calendar.set(Calendar.HOUR_OF_DAY, hours);
                }
                if (!ListenerUtil.mutListener.listen(8561)) {
                    calendar.set(Calendar.MINUTE, minutes);
                }
                long timeInMillis = calendar.getTimeInMillis();
                DialogPreference preference = getPreference();
                if (!ListenerUtil.mutListener.listen(8565)) {
                    if (preference instanceof TimePreference) {
                        TimePreference timePreference = ((TimePreference) preference);
                        if (!ListenerUtil.mutListener.listen(8564)) {
                            if (timePreference.callChangeListener(timeInMillis)) {
                                if (!ListenerUtil.mutListener.listen(8562)) {
                                    timePreference.setTimeInMillis(timeInMillis);
                                }
                                if (!ListenerUtil.mutListener.listen(8563)) {
                                    timePreference.setSummary(DateFormat.getTimeFormat(getContext()).format(calendar.getTime()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
