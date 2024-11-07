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
package com.health.openscale.gui.overview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.gui.measurement.ChartActionBarView;
import com.health.openscale.gui.measurement.ChartMeasurementView;
import com.health.openscale.gui.measurement.MeasurementEntryFragment;
import com.health.openscale.gui.measurement.MeasurementView;
import com.health.openscale.gui.utils.ColorUtil;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class OverviewFragment extends Fragment {

    private View overviewView;

    private TextView txtTitleUser;

    private List<MeasurementView> lastMeasurementViews;

    private ChartMeasurementView chartView;

    private ChartActionBarView chartActionBarView;

    private Spinner spinUser;

    private PopupMenu rangePopupMenu;

    private ImageView showEntry;

    private ImageView editEntry;

    private ImageView deleteEntry;

    private ScaleUser currentScaleUser;

    private ArrayAdapter<String> spinUserAdapter;

    private SharedPreferences prefs;

    private ScaleMeasurement markedMeasurement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(7973)) {
            overviewView = inflater.inflate(R.layout.fragment_overview, container, false);
        }
        if (!ListenerUtil.mutListener.listen(7974)) {
            prefs = PreferenceManager.getDefaultSharedPreferences(overviewView.getContext());
        }
        if (!ListenerUtil.mutListener.listen(7975)) {
            txtTitleUser = overviewView.findViewById(R.id.txtTitleUser);
        }
        if (!ListenerUtil.mutListener.listen(7976)) {
            chartView = overviewView.findViewById(R.id.chartView);
        }
        if (!ListenerUtil.mutListener.listen(7977)) {
            chartView.setOnChartValueSelectedListener(new onChartSelectedListener());
        }
        if (!ListenerUtil.mutListener.listen(7978)) {
            chartView.setProgressBar(overviewView.findViewById(R.id.progressBar));
        }
        if (!ListenerUtil.mutListener.listen(7979)) {
            chartView.setIsInGraphKey(false);
        }
        if (!ListenerUtil.mutListener.listen(7980)) {
            chartActionBarView = overviewView.findViewById(R.id.chartActionBar);
        }
        if (!ListenerUtil.mutListener.listen(7981)) {
            chartActionBarView.setIsInGraphKey(false);
        }
        if (!ListenerUtil.mutListener.listen(7984)) {
            chartActionBarView.setOnActionClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7982)) {
                        chartView.refreshMeasurementList();
                    }
                    if (!ListenerUtil.mutListener.listen(7983)) {
                        updateChartView();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7985)) {
            spinUser = overviewView.findViewById(R.id.spinUser);
        }
        ImageView optionMenu = overviewView.findViewById(R.id.rangeOptionMenu);
        if (!ListenerUtil.mutListener.listen(7987)) {
            optionMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(7986)) {
                        rangePopupMenu.show();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(7988)) {
            rangePopupMenu = new PopupMenu(getContext(), optionMenu);
        }
        if (!ListenerUtil.mutListener.listen(8003)) {
            rangePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (!ListenerUtil.mutListener.listen(8000)) {
                        switch(item.getItemId()) {
                            case R.id.enableChartActionBar:
                                if (!ListenerUtil.mutListener.listen(7995)) {
                                    if (item.isChecked()) {
                                        if (!ListenerUtil.mutListener.listen(7992)) {
                                            item.setChecked(false);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7993)) {
                                            prefs.edit().putBoolean("enableOverviewChartActionBar", false).apply();
                                        }
                                        if (!ListenerUtil.mutListener.listen(7994)) {
                                            chartActionBarView.setVisibility(View.GONE);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(7989)) {
                                            item.setChecked(true);
                                        }
                                        if (!ListenerUtil.mutListener.listen(7990)) {
                                            prefs.edit().putBoolean("enableOverviewChartActionBar", true).apply();
                                        }
                                        if (!ListenerUtil.mutListener.listen(7991)) {
                                            chartActionBarView.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                return true;
                            case R.id.menu_range_day:
                                if (!ListenerUtil.mutListener.listen(7996)) {
                                    prefs.edit().putInt("selectRangeMode", ChartMeasurementView.ViewMode.DAY_OF_ALL.ordinal()).commit();
                                }
                                break;
                            case R.id.menu_range_week:
                                if (!ListenerUtil.mutListener.listen(7997)) {
                                    prefs.edit().putInt("selectRangeMode", ChartMeasurementView.ViewMode.WEEK_OF_ALL.ordinal()).commit();
                                }
                                break;
                            case R.id.menu_range_month:
                                if (!ListenerUtil.mutListener.listen(7998)) {
                                    prefs.edit().putInt("selectRangeMode", ChartMeasurementView.ViewMode.MONTH_OF_ALL.ordinal()).commit();
                                }
                                break;
                            case R.id.menu_range_year:
                                if (!ListenerUtil.mutListener.listen(7999)) {
                                    prefs.edit().putInt("selectRangeMode", ChartMeasurementView.ViewMode.YEAR_OF_ALL.ordinal()).commit();
                                }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(8001)) {
                        item.setChecked(true);
                    }
                    if (!ListenerUtil.mutListener.listen(8002)) {
                        // TODO HACK to refresh graph; graph.invalidate and notfiydatachange is not enough!?
                        getActivity().recreate();
                    }
                    return true;
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8004)) {
            rangePopupMenu.getMenuInflater().inflate(R.menu.overview_menu, rangePopupMenu.getMenu());
        }
        ChartMeasurementView.ViewMode selectedRangePos = ChartMeasurementView.ViewMode.values()[prefs.getInt("selectRangeMode", ChartMeasurementView.ViewMode.DAY_OF_ALL.ordinal())];
        if (!ListenerUtil.mutListener.listen(8009)) {
            switch(selectedRangePos) {
                case DAY_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(8005)) {
                        rangePopupMenu.getMenu().findItem(R.id.menu_range_day).setChecked(true);
                    }
                    break;
                case WEEK_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(8006)) {
                        rangePopupMenu.getMenu().findItem(R.id.menu_range_week).setChecked(true);
                    }
                    break;
                case MONTH_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(8007)) {
                        rangePopupMenu.getMenu().findItem(R.id.menu_range_month).setChecked(true);
                    }
                    break;
                case YEAR_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(8008)) {
                        rangePopupMenu.getMenu().findItem(R.id.menu_range_year).setChecked(true);
                    }
                    break;
            }
        }
        MenuItem enableMeasurementBar = rangePopupMenu.getMenu().findItem(R.id.enableChartActionBar);
        if (!ListenerUtil.mutListener.listen(8010)) {
            enableMeasurementBar.setChecked(prefs.getBoolean("enableOverviewChartActionBar", false));
        }
        if (!ListenerUtil.mutListener.listen(8013)) {
            if (enableMeasurementBar.isChecked()) {
                if (!ListenerUtil.mutListener.listen(8012)) {
                    chartActionBarView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8011)) {
                    chartActionBarView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8014)) {
            lastMeasurementViews = MeasurementView.getMeasurementList(getContext(), MeasurementView.DateTimeOrder.LAST);
        }
        TableLayout tableOverviewLayout = overviewView.findViewById(R.id.tableLayoutMeasurements);
        if (!ListenerUtil.mutListener.listen(8016)) {
            {
                long _loopCounter95 = 0;
                for (MeasurementView measurement : lastMeasurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter95", ++_loopCounter95);
                    if (!ListenerUtil.mutListener.listen(8015)) {
                        tableOverviewLayout.addView(measurement);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8017)) {
            spinUserAdapter = new ArrayAdapter<>(overviewView.getContext(), R.layout.spinner_item, new ArrayList<String>());
        }
        if (!ListenerUtil.mutListener.listen(8018)) {
            spinUser.setAdapter(spinUserAdapter);
        }
        if (!ListenerUtil.mutListener.listen(8021)) {
            // Set item select listener after spinner is created because otherwise item listener fires a lot!?!?
            spinUser.post(new Runnable() {

                public void run() {
                    if (!ListenerUtil.mutListener.listen(8019)) {
                        spinUser.setOnItemSelectedListener(new spinUserSelectionListener());
                    }
                    if (!ListenerUtil.mutListener.listen(8020)) {
                        updateUserSelection();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8022)) {
            showEntry = overviewView.findViewById(R.id.showEntry);
        }
        if (!ListenerUtil.mutListener.listen(8026)) {
            showEntry.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    OverviewFragmentDirections.ActionNavOverviewToNavDataentry action = OverviewFragmentDirections.actionNavOverviewToNavDataentry();
                    if (!ListenerUtil.mutListener.listen(8023)) {
                        action.setMeasurementId(markedMeasurement.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(8024)) {
                        action.setMode(MeasurementEntryFragment.DATA_ENTRY_MODE.VIEW);
                    }
                    if (!ListenerUtil.mutListener.listen(8025)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8027)) {
            editEntry = overviewView.findViewById(R.id.editEntry);
        }
        if (!ListenerUtil.mutListener.listen(8031)) {
            editEntry.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    OverviewFragmentDirections.ActionNavOverviewToNavDataentry action = OverviewFragmentDirections.actionNavOverviewToNavDataentry();
                    if (!ListenerUtil.mutListener.listen(8028)) {
                        action.setMeasurementId(markedMeasurement.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(8029)) {
                        action.setMode(MeasurementEntryFragment.DATA_ENTRY_MODE.EDIT);
                    }
                    if (!ListenerUtil.mutListener.listen(8030)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8032)) {
            deleteEntry = overviewView.findViewById(R.id.deleteEntry);
        }
        if (!ListenerUtil.mutListener.listen(8034)) {
            deleteEntry.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(8033)) {
                        deleteMeasurement();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(8035)) {
            showEntry.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8036)) {
            editEntry.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8037)) {
            deleteEntry.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8038)) {
            chartView.animateY(700);
        }
        if (!ListenerUtil.mutListener.listen(8040)) {
            OpenScale.getInstance().getScaleMeasurementsLiveData().observe(getViewLifecycleOwner(), new Observer<List<ScaleMeasurement>>() {

                @Override
                public void onChanged(List<ScaleMeasurement> scaleMeasurements) {
                    if (!ListenerUtil.mutListener.listen(8039)) {
                        updateOnView(scaleMeasurements);
                    }
                }
            });
        }
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                if (!ListenerUtil.mutListener.listen(8041)) {
                    requireActivity().finish();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(8042)) {
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);
        }
        return overviewView;
    }

    public void updateOnView(List<ScaleMeasurement> scaleMeasurementList) {
        if (!ListenerUtil.mutListener.listen(8045)) {
            if (scaleMeasurementList.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(8044)) {
                    markedMeasurement = new ScaleMeasurement();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8043)) {
                    markedMeasurement = scaleMeasurementList.get(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8046)) {
            updateUserSelection();
        }
        if (!ListenerUtil.mutListener.listen(8047)) {
            updateMesurementViews(markedMeasurement);
        }
        if (!ListenerUtil.mutListener.listen(8048)) {
            chartView.updateMeasurementList(scaleMeasurementList);
        }
        if (!ListenerUtil.mutListener.listen(8049)) {
            updateChartView();
        }
    }

    private void updateChartView() {
        ChartMeasurementView.ViewMode selectedRangeMode = ChartMeasurementView.ViewMode.values()[prefs.getInt("selectRangeMode", ChartMeasurementView.ViewMode.DAY_OF_ALL.ordinal())];
        if (!ListenerUtil.mutListener.listen(8050)) {
            chartView.setViewRange(selectedRangeMode);
        }
    }

    private void updateMesurementViews(ScaleMeasurement selectedMeasurement) {
        ScaleMeasurement[] tupleScaleData = OpenScale.getInstance().getTupleOfScaleMeasurement(selectedMeasurement.getId());
        ScaleMeasurement prevScaleMeasurement = tupleScaleData[0];
        if (!ListenerUtil.mutListener.listen(8052)) {
            {
                long _loopCounter96 = 0;
                for (MeasurementView measurement : lastMeasurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter96", ++_loopCounter96);
                    if (!ListenerUtil.mutListener.listen(8051)) {
                        measurement.loadFrom(selectedMeasurement, prevScaleMeasurement);
                    }
                }
            }
        }
    }

    private void updateUserSelection() {
        if (!ListenerUtil.mutListener.listen(8053)) {
            currentScaleUser = OpenScale.getInstance().getSelectedScaleUser();
        }
        if (!ListenerUtil.mutListener.listen(8054)) {
            spinUserAdapter.clear();
        }
        List<ScaleUser> scaleUserList = OpenScale.getInstance().getScaleUserList();
        int posUser = 0;
        if (!ListenerUtil.mutListener.listen(8062)) {
            {
                long _loopCounter97 = 0;
                for (ScaleUser scaleUser : scaleUserList) {
                    ListenerUtil.loopListener.listen("_loopCounter97", ++_loopCounter97);
                    if (!ListenerUtil.mutListener.listen(8055)) {
                        spinUserAdapter.add(scaleUser.getUserName());
                    }
                    if (!ListenerUtil.mutListener.listen(8061)) {
                        if (scaleUser.getId() == currentScaleUser.getId()) {
                            if (!ListenerUtil.mutListener.listen(8060)) {
                                posUser = (ListenerUtil.mutListener.listen(8059) ? (spinUserAdapter.getCount() % 1) : (ListenerUtil.mutListener.listen(8058) ? (spinUserAdapter.getCount() / 1) : (ListenerUtil.mutListener.listen(8057) ? (spinUserAdapter.getCount() * 1) : (ListenerUtil.mutListener.listen(8056) ? (spinUserAdapter.getCount() + 1) : (spinUserAdapter.getCount() - 1)))));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8063)) {
            spinUser.setSelection(posUser, true);
        }
        // Hide user selector when there is only one user
        int visibility = (ListenerUtil.mutListener.listen(8068) ? (spinUserAdapter.getCount() >= 2) : (ListenerUtil.mutListener.listen(8067) ? (spinUserAdapter.getCount() <= 2) : (ListenerUtil.mutListener.listen(8066) ? (spinUserAdapter.getCount() > 2) : (ListenerUtil.mutListener.listen(8065) ? (spinUserAdapter.getCount() != 2) : (ListenerUtil.mutListener.listen(8064) ? (spinUserAdapter.getCount() == 2) : (spinUserAdapter.getCount() < 2)))))) ? View.GONE : View.VISIBLE;
        if (!ListenerUtil.mutListener.listen(8069)) {
            txtTitleUser.setVisibility(visibility);
        }
        if (!ListenerUtil.mutListener.listen(8070)) {
            spinUser.setVisibility(visibility);
        }
    }

    private class onChartSelectedListener implements OnChartValueSelectedListener {

        @Override
        public void onValueSelected(Entry e, Highlight h) {
            Object[] extraData = (Object[]) e.getData();
            if (!ListenerUtil.mutListener.listen(8071)) {
                markedMeasurement = (ScaleMeasurement) extraData[0];
            }
            if (!ListenerUtil.mutListener.listen(8072)) {
                showEntry.setEnabled(true);
            }
            if (!ListenerUtil.mutListener.listen(8073)) {
                editEntry.setEnabled(true);
            }
            if (!ListenerUtil.mutListener.listen(8074)) {
                deleteEntry.setEnabled(true);
            }
            if (!ListenerUtil.mutListener.listen(8075)) {
                showEntry.setColorFilter(ColorUtil.COLOR_BLUE);
            }
            if (!ListenerUtil.mutListener.listen(8076)) {
                editEntry.setColorFilter(ColorUtil.COLOR_GREEN);
            }
            if (!ListenerUtil.mutListener.listen(8077)) {
                deleteEntry.setColorFilter(ColorUtil.COLOR_RED);
            }
            if (!ListenerUtil.mutListener.listen(8078)) {
                updateMesurementViews(markedMeasurement);
            }
        }

        @Override
        public void onNothingSelected() {
            if (!ListenerUtil.mutListener.listen(8079)) {
                showEntry.setEnabled(false);
            }
            if (!ListenerUtil.mutListener.listen(8080)) {
                editEntry.setEnabled(false);
            }
            if (!ListenerUtil.mutListener.listen(8081)) {
                deleteEntry.setEnabled(false);
            }
            if (!ListenerUtil.mutListener.listen(8082)) {
                showEntry.setColorFilter(ColorUtil.COLOR_GRAY);
            }
            if (!ListenerUtil.mutListener.listen(8083)) {
                editEntry.setColorFilter(ColorUtil.COLOR_GRAY);
            }
            if (!ListenerUtil.mutListener.listen(8084)) {
                deleteEntry.setColorFilter(ColorUtil.COLOR_GRAY);
            }
        }
    }

    private class spinUserSelectionListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (!ListenerUtil.mutListener.listen(8093)) {
                if ((ListenerUtil.mutListener.listen(8089) ? (parent.getChildCount() >= 0) : (ListenerUtil.mutListener.listen(8088) ? (parent.getChildCount() <= 0) : (ListenerUtil.mutListener.listen(8087) ? (parent.getChildCount() < 0) : (ListenerUtil.mutListener.listen(8086) ? (parent.getChildCount() != 0) : (ListenerUtil.mutListener.listen(8085) ? (parent.getChildCount() == 0) : (parent.getChildCount() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(8090)) {
                        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
                    }
                    OpenScale openScale = OpenScale.getInstance();
                    List<ScaleUser> scaleUserList = openScale.getScaleUserList();
                    ScaleUser scaleUser = scaleUserList.get(position);
                    if (!ListenerUtil.mutListener.listen(8091)) {
                        openScale.selectScaleUser(scaleUser.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(8092)) {
                        updateOnView(openScale.getScaleMeasurementList());
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    private void deleteMeasurement() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(overviewView.getContext());
        boolean deleteConfirmationEnable = prefs.getBoolean("deleteConfirmationEnable", true);
        if (!ListenerUtil.mutListener.listen(8101)) {
            if (deleteConfirmationEnable) {
                AlertDialog.Builder deleteAllDialog = new AlertDialog.Builder(overviewView.getContext());
                if (!ListenerUtil.mutListener.listen(8095)) {
                    deleteAllDialog.setMessage(getResources().getString(R.string.question_really_delete));
                }
                if (!ListenerUtil.mutListener.listen(8097)) {
                    deleteAllDialog.setPositiveButton(getResources().getString(R.string.label_yes), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            if (!ListenerUtil.mutListener.listen(8096)) {
                                doDeleteMeasurement();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(8099)) {
                    deleteAllDialog.setNegativeButton(getResources().getString(R.string.label_no), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            if (!ListenerUtil.mutListener.listen(8098)) {
                                dialog.dismiss();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(8100)) {
                    deleteAllDialog.show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8094)) {
                    doDeleteMeasurement();
                }
            }
        }
    }

    private void doDeleteMeasurement() {
        if (!ListenerUtil.mutListener.listen(8102)) {
            OpenScale.getInstance().deleteScaleMeasurement(markedMeasurement.getId());
        }
        if (!ListenerUtil.mutListener.listen(8103)) {
            Toast.makeText(overviewView.getContext(), getResources().getString(R.string.info_data_deleted), Toast.LENGTH_SHORT).show();
        }
        if (!ListenerUtil.mutListener.listen(8104)) {
            showEntry.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8105)) {
            editEntry.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8106)) {
            deleteEntry.setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8107)) {
            showEntry.setColorFilter(ColorUtil.COLOR_GRAY);
        }
        if (!ListenerUtil.mutListener.listen(8108)) {
            editEntry.setColorFilter(ColorUtil.COLOR_GRAY);
        }
        if (!ListenerUtil.mutListener.listen(8109)) {
            deleteEntry.setColorFilter(ColorUtil.COLOR_GRAY);
        }
    }
}
