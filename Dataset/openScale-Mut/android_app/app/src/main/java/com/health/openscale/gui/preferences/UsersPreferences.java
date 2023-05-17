/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*                2018  Erik Johansson <erik@ejohansson.se>
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

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceViewHolder;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleUser;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UsersPreferences extends PreferenceFragmentCompat {

    private static final String PREFERENCE_KEY_ADD_USER = "addUser";

    private static final String PREFERENCE_KEY_USERS = "users";

    private PreferenceCategory users;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(8751)) {
            setPreferencesFromResource(R.xml.users_preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(8752)) {
            setHasOptionsMenu(true);
        }
        Preference addUser = findPreference(PREFERENCE_KEY_ADD_USER);
        if (!ListenerUtil.mutListener.listen(8756)) {
            addUser.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    UsersPreferencesDirections.ActionNavUserPreferencesToNavUsersettings action = UsersPreferencesDirections.actionNavUserPreferencesToNavUsersettings();
                    if (!ListenerUtil.mutListener.listen(8753)) {
                        action.setMode(UserSettingsFragment.USER_SETTING_MODE.ADD);
                    }
                    if (!ListenerUtil.mutListener.listen(8754)) {
                        action.setTitle(getString(R.string.label_add_user));
                    }
                    if (!ListenerUtil.mutListener.listen(8755)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8757)) {
            users = (PreferenceCategory) findPreference(PREFERENCE_KEY_USERS);
        }
        if (!ListenerUtil.mutListener.listen(8758)) {
            updateUserPreferences();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (!ListenerUtil.mutListener.listen(8761)) {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).getCurrentBackStackEntry().getSavedStateHandle().getLiveData("update", false).observe(getViewLifecycleOwner(), new Observer<Boolean>() {

                @Override
                public void onChanged(Boolean aBoolean) {
                    if (!ListenerUtil.mutListener.listen(8760)) {
                        if (aBoolean) {
                            if (!ListenerUtil.mutListener.listen(8759)) {
                                updateUserPreferences();
                            }
                        }
                    }
                }
            });
        }
        return view;
    }

    private void updateUserPreferences() {
        if (!ListenerUtil.mutListener.listen(8762)) {
            users.removeAll();
        }
        if (!ListenerUtil.mutListener.listen(8764)) {
            {
                long _loopCounter105 = 0;
                for (ScaleUser scaleUser : OpenScale.getInstance().getScaleUserList()) {
                    ListenerUtil.loopListener.listen("_loopCounter105", ++_loopCounter105);
                    if (!ListenerUtil.mutListener.listen(8763)) {
                        users.addPreference(new UserPreference(getActivity(), users, scaleUser));
                    }
                }
            }
        }
    }

    class UserPreference extends Preference {

        PreferenceCategory preferenceCategory;

        ScaleUser scaleUser;

        RadioButton radioButton;

        UserPreference(Context context, PreferenceCategory category, ScaleUser scaleUser) {
            super(context);
            if (!ListenerUtil.mutListener.listen(8765)) {
                preferenceCategory = category;
            }
            if (!ListenerUtil.mutListener.listen(8766)) {
                this.scaleUser = scaleUser;
            }
            if (!ListenerUtil.mutListener.listen(8767)) {
                setTitle(scaleUser.getUserName());
            }
            if (!ListenerUtil.mutListener.listen(8768)) {
                setWidgetLayoutResource(R.layout.user_preference_widget_layout);
            }
        }

        @Override
        public void onBindViewHolder(PreferenceViewHolder holder) {
            if (!ListenerUtil.mutListener.listen(8769)) {
                super.onBindViewHolder(holder);
            }
            if (!ListenerUtil.mutListener.listen(8774)) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        UsersPreferencesDirections.ActionNavUserPreferencesToNavUsersettings action = UsersPreferencesDirections.actionNavUserPreferencesToNavUsersettings();
                        if (!ListenerUtil.mutListener.listen(8770)) {
                            action.setMode(UserSettingsFragment.USER_SETTING_MODE.EDIT);
                        }
                        if (!ListenerUtil.mutListener.listen(8771)) {
                            action.setTitle(scaleUser.getUserName());
                        }
                        if (!ListenerUtil.mutListener.listen(8772)) {
                            action.setUserId(scaleUser.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(8773)) {
                            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                        }
                    }
                });
            }
            TypedValue outValue = new TypedValue();
            if (!ListenerUtil.mutListener.listen(8775)) {
                getActivity().getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
            }
            if (!ListenerUtil.mutListener.listen(8776)) {
                holder.itemView.setBackgroundResource(outValue.resourceId);
            }
            if (!ListenerUtil.mutListener.listen(8777)) {
                radioButton = holder.itemView.findViewById(R.id.user_radio_button);
            }
            if (!ListenerUtil.mutListener.listen(8778)) {
                radioButton.setChecked(scaleUser.getId() == OpenScale.getInstance().getSelectedScaleUserId());
            }
            if (!ListenerUtil.mutListener.listen(8788)) {
                radioButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (!ListenerUtil.mutListener.listen(8785)) {
                            {
                                long _loopCounter106 = 0;
                                for (int i = 0; (ListenerUtil.mutListener.listen(8784) ? (i >= preferenceCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8783) ? (i <= preferenceCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8782) ? (i > preferenceCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8781) ? (i != preferenceCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8780) ? (i == preferenceCategory.getPreferenceCount()) : (i < preferenceCategory.getPreferenceCount())))))); ++i) {
                                    ListenerUtil.loopListener.listen("_loopCounter106", ++_loopCounter106);
                                    UserPreference pref = (UserPreference) preferenceCategory.getPreference(i);
                                    if (!ListenerUtil.mutListener.listen(8779)) {
                                        pref.setChecked(false);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(8786)) {
                            radioButton.setChecked(true);
                        }
                        if (!ListenerUtil.mutListener.listen(8787)) {
                            OpenScale.getInstance().selectScaleUser(scaleUser.getId());
                        }
                    }
                });
            }
        }

        public void setChecked(boolean checked) {
            if (!ListenerUtil.mutListener.listen(8789)) {
                radioButton.setChecked(checked);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8790)) {
            menu.clear();
        }
    }
}
