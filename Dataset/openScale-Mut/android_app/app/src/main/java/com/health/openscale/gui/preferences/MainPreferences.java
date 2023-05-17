/* Copyright (C) 2020  olie.xdev <olie.xdev@googlemail.com>
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

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import com.health.openscale.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MainPreferences extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(8383)) {
            setPreferencesFromResource(R.xml.main_preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(8384)) {
            setHasOptionsMenu(true);
        }
        TypedValue typedValue = new TypedValue();
        if (!ListenerUtil.mutListener.listen(8385)) {
            getContext().getTheme().resolveAttribute(R.attr.colorControlNormal, typedValue, true);
        }
        int color = ContextCompat.getColor(getContext(), typedValue.resourceId);
        if (!ListenerUtil.mutListener.listen(8386)) {
            tintIcons(getPreferenceScreen(), color);
        }
        final Preference prefBackup = findPreference("backup");
        if (!ListenerUtil.mutListener.listen(8388)) {
            prefBackup.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavBackupPreferences();
                    if (!ListenerUtil.mutListener.listen(8387)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        final Preference prefBluetooth = findPreference("bluetooth");
        if (!ListenerUtil.mutListener.listen(8390)) {
            prefBluetooth.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavBluetoothPreferences();
                    if (!ListenerUtil.mutListener.listen(8389)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        final Preference prefGeneral = findPreference("general");
        if (!ListenerUtil.mutListener.listen(8392)) {
            prefGeneral.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavGeneralPreferences();
                    if (!ListenerUtil.mutListener.listen(8391)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        final Preference prefGraph = findPreference("graph");
        if (!ListenerUtil.mutListener.listen(8394)) {
            prefGraph.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavGraphPreferences();
                    if (!ListenerUtil.mutListener.listen(8393)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        final Preference prefMeasurements = findPreference("measurements");
        if (!ListenerUtil.mutListener.listen(8396)) {
            prefMeasurements.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavMeasurementPreferences();
                    if (!ListenerUtil.mutListener.listen(8395)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        final Preference prefReminder = findPreference("reminder");
        if (!ListenerUtil.mutListener.listen(8398)) {
            prefReminder.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavReminderPreferences();
                    if (!ListenerUtil.mutListener.listen(8397)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        final Preference prefUsers = findPreference("users");
        if (!ListenerUtil.mutListener.listen(8400)) {
            prefUsers.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavUserPreferences();
                    if (!ListenerUtil.mutListener.listen(8399)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        final Preference prefAbout = findPreference("about");
        if (!ListenerUtil.mutListener.listen(8402)) {
            prefAbout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    NavDirections action = MainPreferencesDirections.actionNavMainPreferencesToNavAboutPreferences();
                    if (!ListenerUtil.mutListener.listen(8401)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8403)) {
            menu.clear();
        }
    }

    private static void tintIcons(Preference preference, int color) {
        if (!ListenerUtil.mutListener.listen(8413)) {
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup group = ((PreferenceGroup) preference);
                if (!ListenerUtil.mutListener.listen(8412)) {
                    {
                        long _loopCounter99 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(8411) ? (i >= group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8410) ? (i <= group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8409) ? (i > group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8408) ? (i != group.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8407) ? (i == group.getPreferenceCount()) : (i < group.getPreferenceCount())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter99", ++_loopCounter99);
                            if (!ListenerUtil.mutListener.listen(8406)) {
                                tintIcons(group.getPreference(i), color);
                            }
                        }
                    }
                }
            } else {
                Drawable icon = preference.getIcon();
                if (!ListenerUtil.mutListener.listen(8405)) {
                    if (icon != null) {
                        if (!ListenerUtil.mutListener.listen(8404)) {
                            DrawableCompat.setTint(icon, color);
                        }
                    }
                }
            }
        }
    }
}
