/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
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
package com.health.openscale.gui.preferences;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.fragment.app.DialogFragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.health.openscale.R;
import com.health.openscale.core.alarm.AlarmHandler;
import com.health.openscale.core.alarm.ReminderBootReceiver;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReminderPreferences extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String PREFERENCE_KEY_REMINDER_NOTIFY_TEXT = "reminderNotifyText";

    public static final String PREFERENCE_KEY_REMINDER_WEEKDAYS = "reminderWeekdays";

    public static final String PREFERENCE_KEY_REMINDER_TIME = "reminderTime";

    private static final String PREFERENCE_KEY_REMINDER_ENABLE = "reminderEnable";

    private CheckBoxPreference reminderEnable;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(8515)) {
            setPreferencesFromResource(R.xml.reminder_preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(8516)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(8517)) {
            reminderEnable = (CheckBoxPreference) findPreference(PREFERENCE_KEY_REMINDER_ENABLE);
        }
        final MultiSelectListPreference prefDays = findPreference("reminderWeekdays");
        if (!ListenerUtil.mutListener.listen(8518)) {
            prefDays.setSummaryProvider(new Preference.SummaryProvider<MultiSelectListPreference>() {

                @Override
                public CharSequence provideSummary(MultiSelectListPreference preference) {
                    final String[] values = getResources().getStringArray(R.array.weekdays_values);
                    final String[] translated = getResources().getStringArray(R.array.weekdays_entries);
                    return IntStream.range(0, values.length).mapToObj(i -> new Pair<>(values[i], translated[i])).filter(p -> preference.getValues().contains(p.first)).map(p -> p.second).collect(Collectors.joining(", "));
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8519)) {
            updateAlarmPreferences();
        }
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        DialogFragment dialogFragment = null;
        if (!ListenerUtil.mutListener.listen(8521)) {
            if (preference instanceof TimePreference) {
                if (!ListenerUtil.mutListener.listen(8520)) {
                    dialogFragment = TimePreferenceDialog.newInstance(preference.getKey());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8525)) {
            if (dialogFragment != null) {
                if (!ListenerUtil.mutListener.listen(8523)) {
                    dialogFragment.setTargetFragment(this, 0);
                }
                if (!ListenerUtil.mutListener.listen(8524)) {
                    dialogFragment.show(getParentFragmentManager(), "timePreferenceDialog");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8522)) {
                    super.onDisplayPreferenceDialog(preference);
                }
            }
        }
    }

    @Override
    public void onResume() {
        if (!ListenerUtil.mutListener.listen(8526)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(8527)) {
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    public void onPause() {
        if (!ListenerUtil.mutListener.listen(8528)) {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }
        if (!ListenerUtil.mutListener.listen(8529)) {
            super.onPause();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!ListenerUtil.mutListener.listen(8530)) {
            updateAlarmPreferences();
        }
    }

    private void updateAlarmPreferences() {
        ComponentName receiver = new ComponentName(getActivity().getApplicationContext(), ReminderBootReceiver.class);
        PackageManager pm = getActivity().getApplicationContext().getPackageManager();
        AlarmHandler alarmHandler = new AlarmHandler();
        if (!ListenerUtil.mutListener.listen(8535)) {
            if (reminderEnable.isChecked()) {
                if (!ListenerUtil.mutListener.listen(8533)) {
                    alarmHandler.scheduleAlarms(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(8534)) {
                    pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8531)) {
                    alarmHandler.disableAllAlarms(getActivity());
                }
                if (!ListenerUtil.mutListener.listen(8532)) {
                    pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8536)) {
            menu.clear();
        }
    }
}
