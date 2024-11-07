/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*  Copyright (C) 2018 Erik Johansson <erik@ejohansson.se>
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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceViewHolder;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.gui.measurement.MeasurementView;
import com.health.openscale.gui.measurement.WeightMeasurementView;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MeasurementPreferences extends PreferenceFragmentCompat {

    private static final String PREFERENCE_KEY_DELETE_ALL = "deleteAll";

    private static final String PREFERENCE_KEY_RESET_ORDER = "resetOrder";

    private static final String PREFERENCE_KEY_MEASUREMENTS = "measurements";

    private PreferenceCategory measurementCategory;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        if (!ListenerUtil.mutListener.listen(8420)) {
            setPreferencesFromResource(R.xml.measurement_preferences, rootKey);
        }
        if (!ListenerUtil.mutListener.listen(8421)) {
            setHasOptionsMenu(true);
        }
        Preference deleteAll = findPreference(PREFERENCE_KEY_DELETE_ALL);
        if (!ListenerUtil.mutListener.listen(8422)) {
            deleteAll.setOnPreferenceClickListener(new onClickListenerDeleteAll());
        }
        if (!ListenerUtil.mutListener.listen(8423)) {
            measurementCategory = (PreferenceCategory) findPreference(PREFERENCE_KEY_MEASUREMENTS);
        }
        Preference resetOrder = findPreference(PREFERENCE_KEY_RESET_ORDER);
        if (!ListenerUtil.mutListener.listen(8426)) {
            resetOrder.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (!ListenerUtil.mutListener.listen(8424)) {
                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().remove(MeasurementView.PREF_MEASUREMENT_ORDER).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(8425)) {
                        updateMeasurementPreferences();
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8427)) {
            updateMeasurementPreferences();
        }
    }

    private void updateMeasurementPreferences() {
        if (!ListenerUtil.mutListener.listen(8428)) {
            measurementCategory.removeAll();
        }
        List<MeasurementView> measurementViews = MeasurementView.getMeasurementList(getActivity(), MeasurementView.DateTimeOrder.NONE);
        if (!ListenerUtil.mutListener.listen(8430)) {
            {
                long _loopCounter100 = 0;
                for (MeasurementView measurement : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter100", ++_loopCounter100);
                    Preference preference = new MeasurementOrderPreference(getActivity(), measurementCategory, measurement);
                    if (!ListenerUtil.mutListener.listen(8429)) {
                        measurementCategory.addPreference(preference);
                    }
                }
            }
        }
    }

    private class onClickListenerDeleteAll implements Preference.OnPreferenceClickListener {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            AlertDialog.Builder deleteAllDialog = new AlertDialog.Builder(getActivity());
            if (!ListenerUtil.mutListener.listen(8431)) {
                deleteAllDialog.setMessage(getResources().getString(R.string.question_really_delete_all));
            }
            if (!ListenerUtil.mutListener.listen(8434)) {
                deleteAllDialog.setPositiveButton(getResources().getString(R.string.label_yes), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        OpenScale openScale = OpenScale.getInstance();
                        int selectedUserId = openScale.getSelectedScaleUserId();
                        if (!ListenerUtil.mutListener.listen(8432)) {
                            openScale.clearScaleMeasurements(selectedUserId);
                        }
                        if (!ListenerUtil.mutListener.listen(8433)) {
                            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.info_data_all_deleted), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(8436)) {
                deleteAllDialog.setNegativeButton(getResources().getString(R.string.label_no), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        if (!ListenerUtil.mutListener.listen(8435)) {
                            dialog.dismiss();
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(8437)) {
                deleteAllDialog.show();
            }
            return false;
        }
    }

    private class MeasurementOrderPreference extends Preference implements GestureDetector.OnGestureListener {

        PreferenceGroup parentGroup;

        MeasurementView measurement;

        GestureDetector gestureDetector;

        View boundView;

        ImageView iconView;

        TextView textView;

        TextView summaryView;

        Switch switchView;

        ImageView reorderView;

        ImageView settingsView;

        MeasurementOrderPreference(Context context, PreferenceGroup parent, MeasurementView measurementView) {
            super(context);
            if (!ListenerUtil.mutListener.listen(8438)) {
                parentGroup = parent;
            }
            if (!ListenerUtil.mutListener.listen(8439)) {
                measurement = measurementView;
            }
            if (!ListenerUtil.mutListener.listen(8440)) {
                gestureDetector = new GestureDetector(getContext(), this);
            }
            if (!ListenerUtil.mutListener.listen(8441)) {
                gestureDetector.setIsLongpressEnabled(true);
            }
            if (!ListenerUtil.mutListener.listen(8442)) {
                setLayoutResource(R.layout.preference_measurement_order);
            }
        }

        @Override
        public PreferenceGroup getParent() {
            return parentGroup;
        }

        @Override
        public void onBindViewHolder(PreferenceViewHolder holder) {
            if (!ListenerUtil.mutListener.listen(8443)) {
                super.onBindViewHolder(holder);
            }
            if (!ListenerUtil.mutListener.listen(8444)) {
                boundView = holder.itemView;
            }
            if (!ListenerUtil.mutListener.listen(8445)) {
                textView = (TextView) holder.findViewById(R.id.textView);
            }
            if (!ListenerUtil.mutListener.listen(8446)) {
                summaryView = (TextView) holder.findViewById(R.id.summaryView);
            }
            if (!ListenerUtil.mutListener.listen(8447)) {
                iconView = (ImageView) holder.findViewById(R.id.iconView);
            }
            if (!ListenerUtil.mutListener.listen(8448)) {
                switchView = (Switch) holder.findViewById(R.id.switchView);
            }
            if (!ListenerUtil.mutListener.listen(8449)) {
                reorderView = (ImageView) holder.findViewById(R.id.reorderView);
            }
            if (!ListenerUtil.mutListener.listen(8450)) {
                settingsView = (ImageView) holder.findViewById(R.id.settingsView);
            }
            if (!ListenerUtil.mutListener.listen(8451)) {
                textView.setText(measurement.getName());
            }
            if (!ListenerUtil.mutListener.listen(8452)) {
                summaryView.setText(measurement.getPreferenceSummary());
            }
            Drawable icon = measurement.getIcon();
            if (!ListenerUtil.mutListener.listen(8453)) {
                icon.setColorFilter(measurement.getForegroundColor(), PorterDuff.Mode.SRC_IN);
            }
            if (!ListenerUtil.mutListener.listen(8454)) {
                iconView.setImageDrawable(icon);
            }
            if (!ListenerUtil.mutListener.listen(8455)) {
                switchView.setChecked(measurement.getSettings().isEnabledIgnoringDependencies());
            }
            if (!ListenerUtil.mutListener.listen(8456)) {
                setKey(measurement.getSettings().getEnabledKey());
            }
            if (!ListenerUtil.mutListener.listen(8457)) {
                setDefaultValue(measurement.getSettings().isEnabledIgnoringDependencies());
            }
            if (!ListenerUtil.mutListener.listen(8458)) {
                setPersistent(true);
            }
            if (!ListenerUtil.mutListener.listen(8460)) {
                setEnableView((ListenerUtil.mutListener.listen(8459) ? (measurement.getSettings().areDependenciesEnabled() || switchView.isChecked()) : (measurement.getSettings().areDependenciesEnabled() && switchView.isChecked())));
            }
            if (!ListenerUtil.mutListener.listen(8463)) {
                if (measurement instanceof WeightMeasurementView) {
                    if (!ListenerUtil.mutListener.listen(8462)) {
                        switchView.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8461)) {
                        switchView.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(8474)) {
                switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!ListenerUtil.mutListener.listen(8473)) {
                            if (buttonView.isPressed()) {
                                if (!ListenerUtil.mutListener.listen(8464)) {
                                    persistBoolean(isChecked);
                                }
                                if (!ListenerUtil.mutListener.listen(8465)) {
                                    setEnableView(isChecked);
                                }
                                if (!ListenerUtil.mutListener.listen(8472)) {
                                    {
                                        long _loopCounter101 = 0;
                                        for (int i = 0; (ListenerUtil.mutListener.listen(8471) ? (i >= getParent().getPreferenceCount()) : (ListenerUtil.mutListener.listen(8470) ? (i <= getParent().getPreferenceCount()) : (ListenerUtil.mutListener.listen(8469) ? (i > getParent().getPreferenceCount()) : (ListenerUtil.mutListener.listen(8468) ? (i != getParent().getPreferenceCount()) : (ListenerUtil.mutListener.listen(8467) ? (i == getParent().getPreferenceCount()) : (i < getParent().getPreferenceCount())))))); ++i) {
                                            ListenerUtil.loopListener.listen("_loopCounter101", ++_loopCounter101);
                                            MeasurementOrderPreference preference = (MeasurementOrderPreference) getParent().getPreference(i);
                                            if (!ListenerUtil.mutListener.listen(8466)) {
                                                preference.setEnabled(preference.measurement.getSettings().areDependenciesEnabled());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(8475)) {
                boundView.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return gestureDetector.onTouchEvent(event);
                    }
                });
            }
            if (!ListenerUtil.mutListener.listen(8476)) {
                boundView.setOnDragListener(new onDragListener());
            }
        }

        private void setEnableView(boolean status) {
            if (!ListenerUtil.mutListener.listen(8485)) {
                if (status) {
                    if (!ListenerUtil.mutListener.listen(8481)) {
                        textView.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(8482)) {
                        summaryView.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(8483)) {
                        reorderView.setEnabled(true);
                    }
                    if (!ListenerUtil.mutListener.listen(8484)) {
                        settingsView.setEnabled(true);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(8477)) {
                        textView.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(8478)) {
                        summaryView.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(8479)) {
                        reorderView.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(8480)) {
                        settingsView.setEnabled(false);
                    }
                }
            }
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return isEnabled();
        }

        @Override
        public void onShowPress(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(8486)) {
                boundView.setPressed(true);
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (!ListenerUtil.mutListener.listen(8487)) {
                boundView.setPressed(false);
            }
            if (!ListenerUtil.mutListener.listen(8490)) {
                if (!measurement.hasExtraPreferences()) {
                    if (!ListenerUtil.mutListener.listen(8489)) {
                        if (switchView.getVisibility() == View.VISIBLE) {
                            if (!ListenerUtil.mutListener.listen(8488)) {
                                switchView.toggle();
                            }
                        }
                    }
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(8491)) {
                // Must be enabled to show extra preferences screen
                if (!measurement.getSettings().isEnabled()) {
                    return true;
                }
            }
            if (!ListenerUtil.mutListener.listen(8492)) {
                // HACK to pass an object using navigation controller
                MeasurementDetailPreferences.setMeasurementView(measurement);
            }
            NavDirections action = MeasurementPreferencesDirections.actionNavMeasurementPreferencesToNavMeasurementDetailPreferences();
            if (!ListenerUtil.mutListener.listen(8493)) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
            }
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent event) {
            int x = Math.round(event.getX());
            int y = Math.round(event.getY());
            if (!ListenerUtil.mutListener.listen(8494)) {
                boundView.startDrag(null, new dragShadowBuilder(boundView, x, y), this, 0);
            }
        }

        private class dragShadowBuilder extends View.DragShadowBuilder {

            private int x;

            private int y;

            public dragShadowBuilder(View view, int x, int y) {
                super(view);
                if (!ListenerUtil.mutListener.listen(8495)) {
                    this.x = x;
                }
                if (!ListenerUtil.mutListener.listen(8496)) {
                    this.y = y;
                }
            }

            @Override
            public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                if (!ListenerUtil.mutListener.listen(8497)) {
                    super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
                }
                if (!ListenerUtil.mutListener.listen(8498)) {
                    outShadowTouchPoint.set(x, y);
                }
            }
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        private class onDragListener implements View.OnDragListener {

            @Override
            public boolean onDrag(View view, DragEvent event) {
                if (!ListenerUtil.mutListener.listen(8513)) {
                    switch(event.getAction()) {
                        case DragEvent.ACTION_DROP:
                            MeasurementOrderPreference draggedPref = (MeasurementOrderPreference) event.getLocalState();
                            ArrayList<MeasurementView> measurementViews = new ArrayList<>();
                            if (!ListenerUtil.mutListener.listen(8508)) {
                                {
                                    long _loopCounter102 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(8507) ? (i >= measurementCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8506) ? (i <= measurementCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8505) ? (i > measurementCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8504) ? (i != measurementCategory.getPreferenceCount()) : (ListenerUtil.mutListener.listen(8503) ? (i == measurementCategory.getPreferenceCount()) : (i < measurementCategory.getPreferenceCount())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter102", ++_loopCounter102);
                                        MeasurementOrderPreference pref = (MeasurementOrderPreference) measurementCategory.getPreference(i);
                                        if (!ListenerUtil.mutListener.listen(8500)) {
                                            if (pref != draggedPref) {
                                                if (!ListenerUtil.mutListener.listen(8499)) {
                                                    measurementViews.add(pref.measurement);
                                                }
                                            }
                                        }
                                        if (!ListenerUtil.mutListener.listen(8502)) {
                                            if (pref.boundView == view) {
                                                if (!ListenerUtil.mutListener.listen(8501)) {
                                                    measurementViews.add(draggedPref.measurement);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8509)) {
                                measurementCategory.removeAll();
                            }
                            if (!ListenerUtil.mutListener.listen(8511)) {
                                {
                                    long _loopCounter103 = 0;
                                    for (MeasurementView measurement : measurementViews) {
                                        ListenerUtil.loopListener.listen("_loopCounter103", ++_loopCounter103);
                                        Preference preference = new MeasurementOrderPreference(getActivity(), measurementCategory, measurement);
                                        if (!ListenerUtil.mutListener.listen(8510)) {
                                            measurementCategory.addPreference(preference);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(8512)) {
                                MeasurementView.saveMeasurementViewsOrder(getContext(), measurementViews);
                            }
                            break;
                    }
                }
                return true;
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(8514)) {
            menu.clear();
        }
    }
}
