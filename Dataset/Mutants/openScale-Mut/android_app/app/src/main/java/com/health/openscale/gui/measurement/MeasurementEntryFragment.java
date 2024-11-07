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
package com.health.openscale.gui.measurement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MeasurementEntryFragment extends Fragment {

    public enum DATA_ENTRY_MODE {

        ADD, EDIT, VIEW
    }

    private static final String PREF_EXPAND = "expandEvaluator";

    private DATA_ENTRY_MODE mode = DATA_ENTRY_MODE.ADD;

    private MeasurementView.MeasurementViewMode measurementViewMode;

    private List<MeasurementView> dataEntryMeasurements;

    private TextView txtDataNr;

    private Button btnLeft;

    private Button btnRight;

    private MenuItem saveButton;

    private MenuItem editButton;

    private MenuItem expandButton;

    private MenuItem deleteButton;

    private ScaleMeasurement scaleMeasurement;

    private ScaleMeasurement previousMeasurement;

    private ScaleMeasurement nextMeasurement;

    private boolean isDirty;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dataentry, container, false);
        if (!ListenerUtil.mutListener.listen(7543)) {
            setHasOptionsMenu(true);
        }
        if (!ListenerUtil.mutListener.listen(7544)) {
            context = getContext();
        }
        TableLayout tableLayoutDataEntry = root.findViewById(R.id.tableLayoutDataEntry);
        if (!ListenerUtil.mutListener.listen(7545)) {
            dataEntryMeasurements = MeasurementView.getMeasurementList(context, MeasurementView.DateTimeOrder.LAST);
        }
        if (!ListenerUtil.mutListener.listen(7546)) {
            txtDataNr = root.findViewById(R.id.txtDataNr);
        }
        if (!ListenerUtil.mutListener.listen(7547)) {
            btnLeft = root.findViewById(R.id.btnLeft);
        }
        if (!ListenerUtil.mutListener.listen(7548)) {
            btnRight = root.findViewById(R.id.btnRight);
        }
        if (!ListenerUtil.mutListener.listen(7549)) {
            btnLeft.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(7550)) {
            btnRight.setVisibility(View.INVISIBLE);
        }
        if (!ListenerUtil.mutListener.listen(7552)) {
            btnLeft.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7551)) {
                        moveLeft();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7554)) {
            btnRight.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7553)) {
                        moveRight();
                    }
                }
            });
        }
        MeasurementView.MeasurementViewMode measurementMode = MeasurementView.MeasurementViewMode.ADD;
        if (!ListenerUtil.mutListener.listen(7555)) {
            mode = MeasurementEntryFragmentArgs.fromBundle(getArguments()).getMode();
        }
        if (!ListenerUtil.mutListener.listen(7558)) {
            switch(mode) {
                case ADD:
                    if (!ListenerUtil.mutListener.listen(7556)) {
                        measurementMode = MeasurementView.MeasurementViewMode.ADD;
                    }
                    break;
                case EDIT:
                    break;
                case VIEW:
                    if (!ListenerUtil.mutListener.listen(7557)) {
                        measurementMode = MeasurementView.MeasurementViewMode.VIEW;
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(7560)) {
            {
                long _loopCounter79 = 0;
                for (MeasurementView measurement : dataEntryMeasurements) {
                    ListenerUtil.loopListener.listen("_loopCounter79", ++_loopCounter79);
                    if (!ListenerUtil.mutListener.listen(7559)) {
                        measurement.setEditMode(measurementMode);
                    }
                }
            }
        }
        int id = MeasurementEntryFragmentArgs.fromBundle(getArguments()).getMeasurementId();
        if (!ListenerUtil.mutListener.listen(7561)) {
            updateOnView(id);
        }
        onMeasurementViewUpdateListener updateListener = new onMeasurementViewUpdateListener();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final boolean expand = mode == DATA_ENTRY_MODE.ADD ? false : prefs.getBoolean(PREF_EXPAND, false);
        if (!ListenerUtil.mutListener.listen(7565)) {
            {
                long _loopCounter80 = 0;
                for (MeasurementView measurement : dataEntryMeasurements) {
                    ListenerUtil.loopListener.listen("_loopCounter80", ++_loopCounter80);
                    if (!ListenerUtil.mutListener.listen(7562)) {
                        tableLayoutDataEntry.addView(measurement);
                    }
                    if (!ListenerUtil.mutListener.listen(7563)) {
                        measurement.setOnUpdateListener(updateListener);
                    }
                    if (!ListenerUtil.mutListener.listen(7564)) {
                        measurement.setExpand(expand);
                    }
                }
            }
        }
        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!ListenerUtil.mutListener.listen(7566)) {
            menu.clear();
        }
        if (!ListenerUtil.mutListener.listen(7567)) {
            inflater.inflate(R.menu.dataentry_menu, menu);
        }
        if (!ListenerUtil.mutListener.listen(7580)) {
            {
                long _loopCounter81 = 0;
                // Apply a tint to all icons in the toolbar
                for (int i = 0; (ListenerUtil.mutListener.listen(7579) ? (i >= menu.size()) : (ListenerUtil.mutListener.listen(7578) ? (i <= menu.size()) : (ListenerUtil.mutListener.listen(7577) ? (i > menu.size()) : (ListenerUtil.mutListener.listen(7576) ? (i != menu.size()) : (ListenerUtil.mutListener.listen(7575) ? (i == menu.size()) : (i < menu.size())))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter81", ++_loopCounter81);
                    MenuItem item = menu.getItem(i);
                    final Drawable drawable = item.getIcon();
                    if (!ListenerUtil.mutListener.listen(7568)) {
                        if (drawable == null) {
                            continue;
                        }
                    }
                    final Drawable wrapped = DrawableCompat.wrap(drawable.mutate());
                    if (!ListenerUtil.mutListener.listen(7573)) {
                        if (item.getItemId() == R.id.saveButton) {
                            if (!ListenerUtil.mutListener.listen(7572)) {
                                DrawableCompat.setTint(wrapped, Color.parseColor("#FFFFFF"));
                            }
                        } else if (item.getItemId() == R.id.editButton) {
                            if (!ListenerUtil.mutListener.listen(7571)) {
                                DrawableCompat.setTint(wrapped, Color.parseColor("#99CC00"));
                            }
                        } else if (item.getItemId() == R.id.expandButton) {
                            if (!ListenerUtil.mutListener.listen(7570)) {
                                DrawableCompat.setTint(wrapped, Color.parseColor("#FFBB33"));
                            }
                        } else if (item.getItemId() == R.id.deleteButton) {
                            if (!ListenerUtil.mutListener.listen(7569)) {
                                DrawableCompat.setTint(wrapped, Color.parseColor("#FF4444"));
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7574)) {
                        item.setIcon(wrapped);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7581)) {
            saveButton = menu.findItem(R.id.saveButton);
        }
        if (!ListenerUtil.mutListener.listen(7582)) {
            editButton = menu.findItem(R.id.editButton);
        }
        if (!ListenerUtil.mutListener.listen(7583)) {
            expandButton = menu.findItem(R.id.expandButton);
        }
        if (!ListenerUtil.mutListener.listen(7584)) {
            deleteButton = menu.findItem(R.id.deleteButton);
        }
        if (!ListenerUtil.mutListener.listen(7588)) {
            // Hide/show icons as appropriate for the view mode
            switch(mode) {
                case ADD:
                    if (!ListenerUtil.mutListener.listen(7585)) {
                        setViewMode(MeasurementView.MeasurementViewMode.ADD);
                    }
                    break;
                case EDIT:
                    if (!ListenerUtil.mutListener.listen(7586)) {
                        setViewMode(MeasurementView.MeasurementViewMode.EDIT);
                    }
                    break;
                case VIEW:
                    if (!ListenerUtil.mutListener.listen(7587)) {
                        setViewMode(MeasurementView.MeasurementViewMode.VIEW);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(7589)) {
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!ListenerUtil.mutListener.listen(7604)) {
            switch(item.getItemId()) {
                case R.id.saveButton:
                    final boolean isEdit = (ListenerUtil.mutListener.listen(7594) ? (scaleMeasurement.getId() >= 0) : (ListenerUtil.mutListener.listen(7593) ? (scaleMeasurement.getId() <= 0) : (ListenerUtil.mutListener.listen(7592) ? (scaleMeasurement.getId() < 0) : (ListenerUtil.mutListener.listen(7591) ? (scaleMeasurement.getId() != 0) : (ListenerUtil.mutListener.listen(7590) ? (scaleMeasurement.getId() == 0) : (scaleMeasurement.getId() > 0))))));
                    if (!ListenerUtil.mutListener.listen(7595)) {
                        saveScaleData();
                    }
                    if (!ListenerUtil.mutListener.listen(7598)) {
                        if (isEdit) {
                            if (!ListenerUtil.mutListener.listen(7597)) {
                                setViewMode(MeasurementView.MeasurementViewMode.VIEW);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(7596)) {
                                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigateUp();
                            }
                        }
                    }
                    return true;
                case R.id.expandButton:
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    final boolean expand = !prefs.getBoolean(PREF_EXPAND, false);
                    if (!ListenerUtil.mutListener.listen(7599)) {
                        prefs.edit().putBoolean(PREF_EXPAND, expand).apply();
                    }
                    if (!ListenerUtil.mutListener.listen(7601)) {
                        {
                            long _loopCounter82 = 0;
                            for (MeasurementView measurement : dataEntryMeasurements) {
                                ListenerUtil.loopListener.listen("_loopCounter82", ++_loopCounter82);
                                if (!ListenerUtil.mutListener.listen(7600)) {
                                    measurement.setExpand(expand);
                                }
                            }
                        }
                    }
                    return true;
                case R.id.editButton:
                    if (!ListenerUtil.mutListener.listen(7602)) {
                        setViewMode(MeasurementView.MeasurementViewMode.EDIT);
                    }
                    return true;
                case R.id.deleteButton:
                    if (!ListenerUtil.mutListener.listen(7603)) {
                        deleteMeasurement();
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateOnView(int id) {
        if (!ListenerUtil.mutListener.listen(7610)) {
            if ((ListenerUtil.mutListener.listen(7605) ? (scaleMeasurement == null && scaleMeasurement.getId() != id) : (scaleMeasurement == null || scaleMeasurement.getId() != id))) {
                if (!ListenerUtil.mutListener.listen(7606)) {
                    isDirty = false;
                }
                if (!ListenerUtil.mutListener.listen(7607)) {
                    scaleMeasurement = null;
                }
                if (!ListenerUtil.mutListener.listen(7608)) {
                    previousMeasurement = null;
                }
                if (!ListenerUtil.mutListener.listen(7609)) {
                    nextMeasurement = null;
                }
            }
        }
        OpenScale openScale = OpenScale.getInstance();
        if (!ListenerUtil.mutListener.listen(7633)) {
            if ((ListenerUtil.mutListener.listen(7615) ? (id >= 0) : (ListenerUtil.mutListener.listen(7614) ? (id <= 0) : (ListenerUtil.mutListener.listen(7613) ? (id < 0) : (ListenerUtil.mutListener.listen(7612) ? (id != 0) : (ListenerUtil.mutListener.listen(7611) ? (id == 0) : (id > 0))))))) {
                if (!ListenerUtil.mutListener.listen(7632)) {
                    // Show selected scale data
                    if (scaleMeasurement == null) {
                        ScaleMeasurement[] tupleScaleData = openScale.getTupleOfScaleMeasurement(id);
                        if (!ListenerUtil.mutListener.listen(7627)) {
                            previousMeasurement = tupleScaleData[0];
                        }
                        if (!ListenerUtil.mutListener.listen(7628)) {
                            scaleMeasurement = tupleScaleData[1].clone();
                        }
                        if (!ListenerUtil.mutListener.listen(7629)) {
                            nextMeasurement = tupleScaleData[2];
                        }
                        if (!ListenerUtil.mutListener.listen(7630)) {
                            btnLeft.setEnabled(previousMeasurement != null);
                        }
                        if (!ListenerUtil.mutListener.listen(7631)) {
                            btnRight.setEnabled(nextMeasurement != null);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7622)) {
                    if (openScale.isScaleMeasurementListEmpty()) {
                        if (!ListenerUtil.mutListener.listen(7620)) {
                            // Show default values
                            scaleMeasurement = new ScaleMeasurement();
                        }
                        if (!ListenerUtil.mutListener.listen(7621)) {
                            scaleMeasurement.setWeight(openScale.getSelectedScaleUser().getInitialWeight());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(7616)) {
                            // Show the last scale data as default
                            scaleMeasurement = openScale.getLastScaleMeasurement().clone();
                        }
                        if (!ListenerUtil.mutListener.listen(7617)) {
                            scaleMeasurement.setId(0);
                        }
                        if (!ListenerUtil.mutListener.listen(7618)) {
                            scaleMeasurement.setDateTime(new Date());
                        }
                        if (!ListenerUtil.mutListener.listen(7619)) {
                            scaleMeasurement.setComment("");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(7623)) {
                    isDirty = true;
                }
                if (!ListenerUtil.mutListener.listen(7626)) {
                    {
                        long _loopCounter83 = 0;
                        // clears these values.
                        for (MeasurementView measurement : dataEntryMeasurements) {
                            ListenerUtil.loopListener.listen("_loopCounter83", ++_loopCounter83);
                            if (!ListenerUtil.mutListener.listen(7625)) {
                                if (!measurement.isVisible()) {
                                    if (!ListenerUtil.mutListener.listen(7624)) {
                                        measurement.clearIn(scaleMeasurement);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7635)) {
            {
                long _loopCounter84 = 0;
                for (MeasurementView measurement : dataEntryMeasurements) {
                    ListenerUtil.loopListener.listen("_loopCounter84", ++_loopCounter84);
                    if (!ListenerUtil.mutListener.listen(7634)) {
                        measurement.loadFrom(scaleMeasurement, previousMeasurement);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7636)) {
            txtDataNr.setMinWidth(txtDataNr.getWidth());
        }
        if (!ListenerUtil.mutListener.listen(7637)) {
            txtDataNr.setText(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(scaleMeasurement.getDateTime()));
        }
    }

    private void setViewMode(MeasurementView.MeasurementViewMode viewMode) {
        if (!ListenerUtil.mutListener.listen(7638)) {
            measurementViewMode = viewMode;
        }
        int dateTimeVisibility = View.VISIBLE;
        if (!ListenerUtil.mutListener.listen(7663)) {
            switch(viewMode) {
                case VIEW:
                    if (!ListenerUtil.mutListener.listen(7639)) {
                        saveButton.setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7640)) {
                        editButton.setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7641)) {
                        expandButton.setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7642)) {
                        deleteButton.setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7643)) {
                        ((LinearLayout) txtDataNr.getParent()).setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7644)) {
                        btnLeft.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7645)) {
                        btnRight.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7646)) {
                        btnLeft.setEnabled(previousMeasurement != null);
                    }
                    if (!ListenerUtil.mutListener.listen(7647)) {
                        btnRight.setEnabled(nextMeasurement != null);
                    }
                    if (!ListenerUtil.mutListener.listen(7648)) {
                        dateTimeVisibility = View.GONE;
                    }
                    break;
                case EDIT:
                    if (!ListenerUtil.mutListener.listen(7649)) {
                        saveButton.setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7650)) {
                        editButton.setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7651)) {
                        expandButton.setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7652)) {
                        deleteButton.setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7653)) {
                        ((LinearLayout) txtDataNr.getParent()).setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7654)) {
                        btnLeft.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7655)) {
                        btnRight.setVisibility(View.VISIBLE);
                    }
                    if (!ListenerUtil.mutListener.listen(7656)) {
                        btnLeft.setEnabled(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7657)) {
                        btnRight.setEnabled(false);
                    }
                    break;
                case ADD:
                    if (!ListenerUtil.mutListener.listen(7658)) {
                        saveButton.setVisible(true);
                    }
                    if (!ListenerUtil.mutListener.listen(7659)) {
                        editButton.setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7660)) {
                        expandButton.setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7661)) {
                        deleteButton.setVisible(false);
                    }
                    if (!ListenerUtil.mutListener.listen(7662)) {
                        ((LinearLayout) txtDataNr.getParent()).setVisibility(View.GONE);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(7669)) {
            {
                long _loopCounter85 = 0;
                for (MeasurementView measurement : dataEntryMeasurements) {
                    ListenerUtil.loopListener.listen("_loopCounter85", ++_loopCounter85);
                    if (!ListenerUtil.mutListener.listen(7667)) {
                        if ((ListenerUtil.mutListener.listen(7665) ? ((ListenerUtil.mutListener.listen(7664) ? (measurement instanceof DateMeasurementView && measurement instanceof TimeMeasurementView) : (measurement instanceof DateMeasurementView || measurement instanceof TimeMeasurementView)) && measurement instanceof UserMeasurementView) : ((ListenerUtil.mutListener.listen(7664) ? (measurement instanceof DateMeasurementView && measurement instanceof TimeMeasurementView) : (measurement instanceof DateMeasurementView || measurement instanceof TimeMeasurementView)) || measurement instanceof UserMeasurementView))) {
                            if (!ListenerUtil.mutListener.listen(7666)) {
                                measurement.setVisibility(dateTimeVisibility);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(7668)) {
                        measurement.setEditMode(viewMode);
                    }
                }
            }
        }
    }

    private void saveScaleData() {
        if (!ListenerUtil.mutListener.listen(7670)) {
            if (!isDirty) {
                return;
            }
        }
        OpenScale openScale = OpenScale.getInstance();
        if (!ListenerUtil.mutListener.listen(7671)) {
            if (openScale.getSelectedScaleUserId() == -1) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(7679)) {
            if ((ListenerUtil.mutListener.listen(7676) ? (scaleMeasurement.getId() >= 0) : (ListenerUtil.mutListener.listen(7675) ? (scaleMeasurement.getId() <= 0) : (ListenerUtil.mutListener.listen(7674) ? (scaleMeasurement.getId() < 0) : (ListenerUtil.mutListener.listen(7673) ? (scaleMeasurement.getId() != 0) : (ListenerUtil.mutListener.listen(7672) ? (scaleMeasurement.getId() == 0) : (scaleMeasurement.getId() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(7678)) {
                    openScale.updateScaleMeasurement(scaleMeasurement);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7677)) {
                    openScale.addScaleMeasurement(scaleMeasurement);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7680)) {
            isDirty = false;
        }
    }

    private void deleteMeasurement() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean deleteConfirmationEnable = prefs.getBoolean("deleteConfirmationEnable", true);
        if (!ListenerUtil.mutListener.listen(7688)) {
            if (deleteConfirmationEnable) {
                AlertDialog.Builder deleteAllDialog = new AlertDialog.Builder(context);
                if (!ListenerUtil.mutListener.listen(7682)) {
                    deleteAllDialog.setMessage(getResources().getString(R.string.question_really_delete));
                }
                if (!ListenerUtil.mutListener.listen(7684)) {
                    deleteAllDialog.setPositiveButton(getResources().getString(R.string.label_yes), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            if (!ListenerUtil.mutListener.listen(7683)) {
                                doDeleteMeasurement();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(7686)) {
                    deleteAllDialog.setNegativeButton(getResources().getString(R.string.label_no), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            if (!ListenerUtil.mutListener.listen(7685)) {
                                dialog.dismiss();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(7687)) {
                    deleteAllDialog.show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7681)) {
                    doDeleteMeasurement();
                }
            }
        }
    }

    private void doDeleteMeasurement() {
        if (!ListenerUtil.mutListener.listen(7689)) {
            OpenScale.getInstance().deleteScaleMeasurement(scaleMeasurement.getId());
        }
        if (!ListenerUtil.mutListener.listen(7690)) {
            Toast.makeText(context, getResources().getString(R.string.info_data_deleted), Toast.LENGTH_SHORT).show();
        }
        final boolean hasNext = (ListenerUtil.mutListener.listen(7691) ? (moveLeft() && moveRight()) : (moveLeft() || moveRight()));
        if (!ListenerUtil.mutListener.listen(7694)) {
            if (!hasNext) {
                if (!ListenerUtil.mutListener.listen(7693)) {
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigateUp();
                }
            } else if (measurementViewMode == MeasurementView.MeasurementViewMode.EDIT) {
                if (!ListenerUtil.mutListener.listen(7692)) {
                    setViewMode(MeasurementView.MeasurementViewMode.VIEW);
                }
            }
        }
    }

    private boolean moveLeft() {
        if (!ListenerUtil.mutListener.listen(7696)) {
            if (previousMeasurement != null) {
                if (!ListenerUtil.mutListener.listen(7695)) {
                    updateOnView(previousMeasurement.getId());
                }
                return true;
            }
        }
        return false;
    }

    private boolean moveRight() {
        if (!ListenerUtil.mutListener.listen(7698)) {
            if (nextMeasurement != null) {
                if (!ListenerUtil.mutListener.listen(7697)) {
                    updateOnView(nextMeasurement.getId());
                }
                return true;
            }
        }
        return false;
    }

    private class onMeasurementViewUpdateListener implements MeasurementViewUpdateListener {

        @Override
        public void onMeasurementViewUpdate(MeasurementView view) {
            if (!ListenerUtil.mutListener.listen(7699)) {
                view.saveTo(scaleMeasurement);
            }
            if (!ListenerUtil.mutListener.listen(7700)) {
                isDirty = true;
            }
            if (!ListenerUtil.mutListener.listen(7704)) {
                // Otherwise that measurement (e.g. fat) may change when weight is updated.
                if (view instanceof WeightMeasurementView) {
                    if (!ListenerUtil.mutListener.listen(7703)) {
                        {
                            long _loopCounter86 = 0;
                            for (MeasurementView measurement : dataEntryMeasurements) {
                                ListenerUtil.loopListener.listen("_loopCounter86", ++_loopCounter86);
                                if (!ListenerUtil.mutListener.listen(7702)) {
                                    if (measurement != view) {
                                        if (!ListenerUtil.mutListener.listen(7701)) {
                                            measurement.saveTo(scaleMeasurement);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(7705)) {
                txtDataNr.setText(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(scaleMeasurement.getDateTime()));
            }
            if (!ListenerUtil.mutListener.listen(7708)) {
                {
                    long _loopCounter87 = 0;
                    for (MeasurementView measurement : dataEntryMeasurements) {
                        ListenerUtil.loopListener.listen("_loopCounter87", ++_loopCounter87);
                        if (!ListenerUtil.mutListener.listen(7707)) {
                            if (measurement != view) {
                                if (!ListenerUtil.mutListener.listen(7706)) {
                                    measurement.loadFrom(scaleMeasurement, previousMeasurement);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
