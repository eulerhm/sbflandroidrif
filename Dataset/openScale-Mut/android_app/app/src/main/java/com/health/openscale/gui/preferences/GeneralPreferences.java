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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.health.openscale.R;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GeneralPreferences extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(8371)) {
            setPreferencesFromResource(R.xml.general_preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(8372)) {
            setHasOptionsMenu(true);
        }
        final ListPreference prefTheme = findPreference("app_theme");
        if (!ListenerUtil.mutListener.listen(8376)) {
            prefTheme.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(8375)) {
                        if (newValue.equals("Dark")) {
                            if (!ListenerUtil.mutListener.listen(8374)) {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(8373)) {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            }
                        }
                    }
                    return true;
                }
            });
        }
        final ListPreference prefLanguage = findPreference("language");
        if (!ListenerUtil.mutListener.listen(8378)) {
            prefLanguage.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!ListenerUtil.mutListener.listen(8377)) {
                        getActivity().recreate();
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8379)) {
            menu.clear();
        }
    }
}
