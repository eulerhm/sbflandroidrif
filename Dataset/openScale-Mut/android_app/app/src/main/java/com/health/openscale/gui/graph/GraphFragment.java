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
package com.health.openscale.gui.graph;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.gui.measurement.ChartActionBarView;
import com.health.openscale.gui.measurement.ChartMeasurementView;
import com.health.openscale.gui.measurement.MeasurementEntryFragment;
import com.health.openscale.gui.utils.ColorUtil;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class GraphFragment extends Fragment {

    private View graphView;

    private ChartMeasurementView chartView;

    private ChartActionBarView chartActionBarView;

    private BarChart chartTop;

    private TextView txtYear;

    private Button btnLeftYear;

    private Button btnRightYear;

    private PopupMenu popup;

    private FloatingActionButton showMenu;

    private FloatingActionButton editMenu;

    private FloatingActionButton deleteMenu;

    private SharedPreferences prefs;

    private OpenScale openScale;

    private LocalDate calYears;

    private LocalDate calLastSelected;

    private ScaleMeasurement markedMeasurement;

    private static final String CAL_YEARS_KEY = "calYears";

    private static final String CAL_LAST_SELECTED_KEY = "calLastSelected";

    public GraphFragment() {
        if (!ListenerUtil.mutListener.listen(6230)) {
            calYears = LocalDate.now();
        }
        if (!ListenerUtil.mutListener.listen(6231)) {
            calLastSelected = LocalDate.now();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(6232)) {
            openScale = OpenScale.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(6238)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(6237)) {
                    if (!openScale.isScaleMeasurementListEmpty()) {
                        if (!ListenerUtil.mutListener.listen(6235)) {
                            calYears = openScale.getLastScaleMeasurement().getDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        }
                        ;
                        if (!ListenerUtil.mutListener.listen(6236)) {
                            calLastSelected = openScale.getLastScaleMeasurement().getDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6233)) {
                    calYears = LocalDate.ofEpochDay(savedInstanceState.getLong(CAL_YEARS_KEY));
                }
                if (!ListenerUtil.mutListener.listen(6234)) {
                    calLastSelected = LocalDate.ofEpochDay(savedInstanceState.getLong(CAL_LAST_SELECTED_KEY));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6239)) {
            graphView = inflater.inflate(R.layout.fragment_graph, container, false);
        }
        if (!ListenerUtil.mutListener.listen(6240)) {
            prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        }
        if (!ListenerUtil.mutListener.listen(6241)) {
            chartView = graphView.findViewById(R.id.chartView);
        }
        if (!ListenerUtil.mutListener.listen(6242)) {
            chartView.setOnChartValueSelectedListener(new onChartValueSelectedListener());
        }
        if (!ListenerUtil.mutListener.listen(6243)) {
            chartView.setProgressBar(graphView.findViewById(R.id.progressBar));
        }
        if (!ListenerUtil.mutListener.listen(6244)) {
            chartTop = graphView.findViewById(R.id.chart_top);
        }
        if (!ListenerUtil.mutListener.listen(6245)) {
            chartTop.setDoubleTapToZoomEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6246)) {
            chartTop.setDrawGridBackground(false);
        }
        if (!ListenerUtil.mutListener.listen(6247)) {
            chartTop.getLegend().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6248)) {
            chartTop.getAxisLeft().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6249)) {
            chartTop.getAxisRight().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6250)) {
            chartTop.getDescription().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6251)) {
            chartTop.setOnChartValueSelectedListener(new chartTopValueTouchListener());
        }
        XAxis chartTopxAxis = chartTop.getXAxis();
        if (!ListenerUtil.mutListener.listen(6252)) {
            chartTopxAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        }
        if (!ListenerUtil.mutListener.listen(6253)) {
            chartTopxAxis.setDrawGridLines(false);
        }
        if (!ListenerUtil.mutListener.listen(6254)) {
            chartTopxAxis.setTextColor(ColorUtil.getTintColor(graphView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6256)) {
            chartTopxAxis.setValueFormatter(new ValueFormatter() {

                private final SimpleDateFormat mFormat = new SimpleDateFormat("MMM", Locale.getDefault());

                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    Calendar calendar = Calendar.getInstance();
                    if (!ListenerUtil.mutListener.listen(6255)) {
                        calendar.set(Calendar.MONTH, (int) value);
                    }
                    return mFormat.format(calendar.getTime());
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6257)) {
            txtYear = graphView.findViewById(R.id.txtYear);
        }
        if (!ListenerUtil.mutListener.listen(6258)) {
            txtYear.setText(Integer.toString(calYears.getYear()));
        }
        if (!ListenerUtil.mutListener.listen(6259)) {
            chartActionBarView = graphView.findViewById(R.id.chartActionBar);
        }
        if (!ListenerUtil.mutListener.listen(6261)) {
            chartActionBarView.setOnActionClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6260)) {
                        generateGraphs();
                    }
                }
            });
        }
        ImageView optionMenu = graphView.findViewById(R.id.optionMenu);
        if (!ListenerUtil.mutListener.listen(6263)) {
            optionMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6262)) {
                        popup.show();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6264)) {
            btnLeftYear = graphView.findViewById(R.id.btnLeftYear);
        }
        if (!ListenerUtil.mutListener.listen(6268)) {
            btnLeftYear.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(6265)) {
                        calYears = calYears.minusYears(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6266)) {
                        txtYear.setText(Integer.toString(calYears.getYear()));
                    }
                    if (!ListenerUtil.mutListener.listen(6267)) {
                        generateGraphs();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6269)) {
            btnRightYear = graphView.findViewById(R.id.btnRightYear);
        }
        if (!ListenerUtil.mutListener.listen(6273)) {
            btnRightYear.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    if (!ListenerUtil.mutListener.listen(6270)) {
                        calYears = calYears.plusYears(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6271)) {
                        txtYear.setText(Integer.toString(calYears.getYear()));
                    }
                    if (!ListenerUtil.mutListener.listen(6272)) {
                        generateGraphs();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6274)) {
            popup = new PopupMenu(getContext(), optionMenu);
        }
        if (!ListenerUtil.mutListener.listen(6296)) {
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch(item.getItemId()) {
                        case R.id.enableChartActionBar:
                            if (!ListenerUtil.mutListener.listen(6281)) {
                                if (item.isChecked()) {
                                    if (!ListenerUtil.mutListener.listen(6278)) {
                                        item.setChecked(false);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6279)) {
                                        prefs.edit().putBoolean("enableGraphChartActionBar", false).apply();
                                    }
                                    if (!ListenerUtil.mutListener.listen(6280)) {
                                        chartActionBarView.setVisibility(View.GONE);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6275)) {
                                        item.setChecked(true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6276)) {
                                        prefs.edit().putBoolean("enableGraphChartActionBar", true).apply();
                                    }
                                    if (!ListenerUtil.mutListener.listen(6277)) {
                                        chartActionBarView.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            return true;
                        case R.id.enableMonth:
                            if (!ListenerUtil.mutListener.listen(6286)) {
                                if (item.isChecked()) {
                                    if (!ListenerUtil.mutListener.listen(6284)) {
                                        item.setChecked(false);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6285)) {
                                        prefs.edit().putBoolean("showMonth", false).apply();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6282)) {
                                        item.setChecked(true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6283)) {
                                        prefs.edit().putBoolean("showMonth", true).apply();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6287)) {
                                // TODO HACK to refresh graph; graph.invalidate and notfiydatachange is not enough!?
                                getActivity().recreate();
                            }
                            if (!ListenerUtil.mutListener.listen(6288)) {
                                generateGraphs();
                            }
                            return true;
                        case R.id.enableWeek:
                            if (!ListenerUtil.mutListener.listen(6293)) {
                                if (item.isChecked()) {
                                    if (!ListenerUtil.mutListener.listen(6291)) {
                                        item.setChecked(false);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6292)) {
                                        prefs.edit().putBoolean("showWeek", false).apply();
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(6289)) {
                                        item.setChecked(true);
                                    }
                                    if (!ListenerUtil.mutListener.listen(6290)) {
                                        prefs.edit().putBoolean("showWeek", true).apply();
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6294)) {
                                // TODO HACK to refresh graph; graph.invalidate and notfiydatachange is not enough!?
                                getActivity().recreate();
                            }
                            if (!ListenerUtil.mutListener.listen(6295)) {
                                generateGraphs();
                            }
                            return true;
                        default:
                            return false;
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6297)) {
            popup.getMenuInflater().inflate(R.menu.graph_menu, popup.getMenu());
        }
        MenuItem enableMonth = popup.getMenu().findItem(R.id.enableMonth);
        if (!ListenerUtil.mutListener.listen(6298)) {
            enableMonth.setChecked(prefs.getBoolean("showMonth", true));
        }
        MenuItem enableWeek = popup.getMenu().findItem(R.id.enableWeek);
        if (!ListenerUtil.mutListener.listen(6299)) {
            enableWeek.setChecked(prefs.getBoolean("showWeek", false));
        }
        MenuItem enableMeasurementBar = popup.getMenu().findItem(R.id.enableChartActionBar);
        if (!ListenerUtil.mutListener.listen(6300)) {
            enableMeasurementBar.setChecked(prefs.getBoolean("enableGraphChartActionBar", true));
        }
        if (!ListenerUtil.mutListener.listen(6303)) {
            if (enableMeasurementBar.isChecked()) {
                if (!ListenerUtil.mutListener.listen(6302)) {
                    chartActionBarView.setVisibility(View.VISIBLE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6301)) {
                    chartActionBarView.setVisibility(View.GONE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6304)) {
            showMenu = graphView.findViewById(R.id.showMenu);
        }
        if (!ListenerUtil.mutListener.listen(6308)) {
            showMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    GraphFragmentDirections.ActionNavGraphToNavDataentry action = GraphFragmentDirections.actionNavGraphToNavDataentry();
                    if (!ListenerUtil.mutListener.listen(6305)) {
                        action.setMeasurementId(markedMeasurement.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(6306)) {
                        action.setMode(MeasurementEntryFragment.DATA_ENTRY_MODE.VIEW);
                    }
                    if (!ListenerUtil.mutListener.listen(6307)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6309)) {
            editMenu = graphView.findViewById(R.id.editMenu);
        }
        if (!ListenerUtil.mutListener.listen(6313)) {
            editMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    GraphFragmentDirections.ActionNavGraphToNavDataentry action = GraphFragmentDirections.actionNavGraphToNavDataentry();
                    if (!ListenerUtil.mutListener.listen(6310)) {
                        action.setMeasurementId(markedMeasurement.getId());
                    }
                    if (!ListenerUtil.mutListener.listen(6311)) {
                        action.setMode(MeasurementEntryFragment.DATA_ENTRY_MODE.EDIT);
                    }
                    if (!ListenerUtil.mutListener.listen(6312)) {
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(action);
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6314)) {
            deleteMenu = graphView.findViewById(R.id.deleteMenu);
        }
        if (!ListenerUtil.mutListener.listen(6316)) {
            deleteMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!ListenerUtil.mutListener.listen(6315)) {
                        deleteMeasurement();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(6319)) {
            OpenScale.getInstance().getScaleMeasurementsLiveData().observe(getViewLifecycleOwner(), new Observer<List<ScaleMeasurement>>() {

                @Override
                public void onChanged(List<ScaleMeasurement> scaleMeasurements) {
                    if (!ListenerUtil.mutListener.listen(6317)) {
                        chartView.updateMeasurementList(scaleMeasurements);
                    }
                    if (!ListenerUtil.mutListener.listen(6318)) {
                        generateGraphs();
                    }
                }
            });
        }
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                if (!ListenerUtil.mutListener.listen(6320)) {
                    requireActivity().finish();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(6321)) {
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);
        }
        return graphView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(6322)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(6323)) {
            outState.putLong(CAL_YEARS_KEY, calYears.toEpochDay());
        }
        if (!ListenerUtil.mutListener.listen(6324)) {
            outState.putLong(CAL_LAST_SELECTED_KEY, calLastSelected.toEpochDay());
        }
    }

    private void generateColumnData() {
        int[] numOfMonth = openScale.getCountsOfMonth(calYears.getYear());
        LocalDate calMonths = LocalDate.of(calYears.getYear(), 1, 1);
        List<IBarDataSet> dataSets = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(6344)) {
            {
                long _loopCounter67 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(6343) ? (i >= 12) : (ListenerUtil.mutListener.listen(6342) ? (i <= 12) : (ListenerUtil.mutListener.listen(6341) ? (i > 12) : (ListenerUtil.mutListener.listen(6340) ? (i != 12) : (ListenerUtil.mutListener.listen(6339) ? (i == 12) : (i < 12)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter67", ++_loopCounter67);
                    List<BarEntry> entries = new ArrayList<>();
                    if (!ListenerUtil.mutListener.listen(6329)) {
                        entries.add(new BarEntry((ListenerUtil.mutListener.listen(6328) ? (calMonths.getMonthValue() % 1) : (ListenerUtil.mutListener.listen(6327) ? (calMonths.getMonthValue() / 1) : (ListenerUtil.mutListener.listen(6326) ? (calMonths.getMonthValue() * 1) : (ListenerUtil.mutListener.listen(6325) ? (calMonths.getMonthValue() + 1) : (calMonths.getMonthValue() - 1))))), numOfMonth[i]));
                    }
                    if (!ListenerUtil.mutListener.listen(6330)) {
                        calMonths = calMonths.plusMonths(1);
                    }
                    BarDataSet set = new BarDataSet(entries, "month " + i);
                    if (!ListenerUtil.mutListener.listen(6335)) {
                        set.setColor(ColorUtil.COLORS[(ListenerUtil.mutListener.listen(6334) ? (i / 4) : (ListenerUtil.mutListener.listen(6333) ? (i * 4) : (ListenerUtil.mutListener.listen(6332) ? (i - 4) : (ListenerUtil.mutListener.listen(6331) ? (i + 4) : (i % 4)))))]);
                    }
                    if (!ListenerUtil.mutListener.listen(6336)) {
                        set.setDrawValues(false);
                    }
                    if (!ListenerUtil.mutListener.listen(6337)) {
                        set.setValueFormatter(new StackedValueFormatter(true, "", 0));
                    }
                    if (!ListenerUtil.mutListener.listen(6338)) {
                        dataSets.add(set);
                    }
                }
            }
        }
        BarData data = new BarData(dataSets);
        if (!ListenerUtil.mutListener.listen(6345)) {
            chartTop.setData(data);
        }
        if (!ListenerUtil.mutListener.listen(6346)) {
            chartTop.setFitBars(true);
        }
        if (!ListenerUtil.mutListener.listen(6347)) {
            chartTop.invalidate();
        }
    }

    private void generateGraphs() {
        final int selectedYear = calYears.getYear();
        int firstYear = selectedYear;
        int lastYear = selectedYear;
        if (!ListenerUtil.mutListener.listen(6352)) {
            if (!openScale.isScaleMeasurementListEmpty()) {
                Calendar cal = Calendar.getInstance();
                if (!ListenerUtil.mutListener.listen(6348)) {
                    cal.setTime(openScale.getFirstScaleMeasurement().getDateTime());
                }
                if (!ListenerUtil.mutListener.listen(6349)) {
                    firstYear = cal.get(Calendar.YEAR);
                }
                if (!ListenerUtil.mutListener.listen(6350)) {
                    cal.setTime(openScale.getLastScaleMeasurement().getDateTime());
                }
                if (!ListenerUtil.mutListener.listen(6351)) {
                    lastYear = cal.get(Calendar.YEAR);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6358)) {
            btnLeftYear.setEnabled((ListenerUtil.mutListener.listen(6357) ? (selectedYear >= firstYear) : (ListenerUtil.mutListener.listen(6356) ? (selectedYear <= firstYear) : (ListenerUtil.mutListener.listen(6355) ? (selectedYear < firstYear) : (ListenerUtil.mutListener.listen(6354) ? (selectedYear != firstYear) : (ListenerUtil.mutListener.listen(6353) ? (selectedYear == firstYear) : (selectedYear > firstYear)))))));
        }
        if (!ListenerUtil.mutListener.listen(6364)) {
            btnRightYear.setEnabled((ListenerUtil.mutListener.listen(6363) ? (selectedYear >= lastYear) : (ListenerUtil.mutListener.listen(6362) ? (selectedYear <= lastYear) : (ListenerUtil.mutListener.listen(6361) ? (selectedYear > lastYear) : (ListenerUtil.mutListener.listen(6360) ? (selectedYear != lastYear) : (ListenerUtil.mutListener.listen(6359) ? (selectedYear == lastYear) : (selectedYear < lastYear)))))));
        }
        if (!ListenerUtil.mutListener.listen(6380)) {
            if ((ListenerUtil.mutListener.listen(6375) ? ((ListenerUtil.mutListener.listen(6369) ? (selectedYear >= firstYear) : (ListenerUtil.mutListener.listen(6368) ? (selectedYear <= firstYear) : (ListenerUtil.mutListener.listen(6367) ? (selectedYear > firstYear) : (ListenerUtil.mutListener.listen(6366) ? (selectedYear < firstYear) : (ListenerUtil.mutListener.listen(6365) ? (selectedYear != firstYear) : (selectedYear == firstYear)))))) || (ListenerUtil.mutListener.listen(6374) ? (selectedYear >= lastYear) : (ListenerUtil.mutListener.listen(6373) ? (selectedYear <= lastYear) : (ListenerUtil.mutListener.listen(6372) ? (selectedYear > lastYear) : (ListenerUtil.mutListener.listen(6371) ? (selectedYear < lastYear) : (ListenerUtil.mutListener.listen(6370) ? (selectedYear != lastYear) : (selectedYear == lastYear))))))) : ((ListenerUtil.mutListener.listen(6369) ? (selectedYear >= firstYear) : (ListenerUtil.mutListener.listen(6368) ? (selectedYear <= firstYear) : (ListenerUtil.mutListener.listen(6367) ? (selectedYear > firstYear) : (ListenerUtil.mutListener.listen(6366) ? (selectedYear < firstYear) : (ListenerUtil.mutListener.listen(6365) ? (selectedYear != firstYear) : (selectedYear == firstYear)))))) && (ListenerUtil.mutListener.listen(6374) ? (selectedYear >= lastYear) : (ListenerUtil.mutListener.listen(6373) ? (selectedYear <= lastYear) : (ListenerUtil.mutListener.listen(6372) ? (selectedYear > lastYear) : (ListenerUtil.mutListener.listen(6371) ? (selectedYear < lastYear) : (ListenerUtil.mutListener.listen(6370) ? (selectedYear != lastYear) : (selectedYear == lastYear))))))))) {
                if (!ListenerUtil.mutListener.listen(6378)) {
                    btnLeftYear.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6379)) {
                    btnRightYear.setVisibility(View.GONE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6376)) {
                    btnLeftYear.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6377)) {
                    btnRightYear.setVisibility(View.VISIBLE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6392)) {
            // show monthly diagram
            if (prefs.getBoolean("showMonth", true)) {
                if (!ListenerUtil.mutListener.listen(6386)) {
                    chartTop.setVisibility(View.VISIBLE);
                }
                if (!ListenerUtil.mutListener.listen(6387)) {
                    chartView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.7f));
                }
                if (!ListenerUtil.mutListener.listen(6388)) {
                    generateColumnData();
                }
                if (!ListenerUtil.mutListener.listen(6391)) {
                    if (prefs.getBoolean("showWeek", false)) {
                        if (!ListenerUtil.mutListener.listen(6390)) {
                            chartView.setViewRange(selectedYear, calLastSelected.getMonthValue(), ChartMeasurementView.ViewMode.WEEK_OF_MONTH);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6389)) {
                            chartView.setViewRange(selectedYear, calLastSelected.getMonthValue(), ChartMeasurementView.ViewMode.DAY_OF_MONTH);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6381)) {
                    // show only yearly diagram and hide monthly diagram
                    chartTop.setVisibility(View.GONE);
                }
                if (!ListenerUtil.mutListener.listen(6382)) {
                    chartView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.9f));
                }
                if (!ListenerUtil.mutListener.listen(6385)) {
                    if (prefs.getBoolean("showWeek", false)) {
                        if (!ListenerUtil.mutListener.listen(6384)) {
                            chartView.setViewRange(selectedYear, ChartMeasurementView.ViewMode.WEEK_OF_YEAR);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6383)) {
                            chartView.setViewRange(selectedYear, ChartMeasurementView.ViewMode.MONTH_OF_YEAR);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6393)) {
            chartView.refreshMeasurementList();
        }
    }

    private class chartTopValueTouchListener implements OnChartValueSelectedListener {

        @Override
        public void onValueSelected(Entry e, Highlight h) {
            if (!ListenerUtil.mutListener.listen(6398)) {
                calLastSelected = calLastSelected.withMonth((ListenerUtil.mutListener.listen(6397) ? ((int) e.getX() % 1) : (ListenerUtil.mutListener.listen(6396) ? ((int) e.getX() / 1) : (ListenerUtil.mutListener.listen(6395) ? ((int) e.getX() * 1) : (ListenerUtil.mutListener.listen(6394) ? ((int) e.getX() - 1) : ((int) e.getX() + 1))))));
            }
            if (!ListenerUtil.mutListener.listen(6399)) {
                generateGraphs();
            }
            if (!ListenerUtil.mutListener.listen(6400)) {
                showMenu.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(6401)) {
                editMenu.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(6402)) {
                deleteMenu.setVisibility(View.GONE);
            }
        }

        @Override
        public void onNothingSelected() {
        }
    }

    private class onChartValueSelectedListener implements OnChartValueSelectedListener {

        @Override
        public void onValueSelected(Entry e, Highlight h) {
            Object[] extraData = (Object[]) e.getData();
            if (!ListenerUtil.mutListener.listen(6403)) {
                if (extraData == null) {
                    return;
                }
            }
            if (!ListenerUtil.mutListener.listen(6404)) {
                markedMeasurement = (ScaleMeasurement) extraData[0];
            }
            if (!ListenerUtil.mutListener.listen(6405)) {
                showMenu.setVisibility(View.VISIBLE);
            }
            if (!ListenerUtil.mutListener.listen(6406)) {
                editMenu.setVisibility(View.VISIBLE);
            }
            if (!ListenerUtil.mutListener.listen(6407)) {
                deleteMenu.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onNothingSelected() {
            if (!ListenerUtil.mutListener.listen(6408)) {
                showMenu.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(6409)) {
                editMenu.setVisibility(View.GONE);
            }
            if (!ListenerUtil.mutListener.listen(6410)) {
                deleteMenu.setVisibility(View.GONE);
            }
        }
    }

    private void deleteMeasurement() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(graphView.getContext());
        boolean deleteConfirmationEnable = prefs.getBoolean("deleteConfirmationEnable", true);
        if (!ListenerUtil.mutListener.listen(6418)) {
            if (deleteConfirmationEnable) {
                AlertDialog.Builder deleteAllDialog = new AlertDialog.Builder(graphView.getContext());
                if (!ListenerUtil.mutListener.listen(6412)) {
                    deleteAllDialog.setMessage(getResources().getString(R.string.question_really_delete));
                }
                if (!ListenerUtil.mutListener.listen(6414)) {
                    deleteAllDialog.setPositiveButton(getResources().getString(R.string.label_yes), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            if (!ListenerUtil.mutListener.listen(6413)) {
                                doDeleteMeasurement();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6416)) {
                    deleteAllDialog.setNegativeButton(getResources().getString(R.string.label_no), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                            if (!ListenerUtil.mutListener.listen(6415)) {
                                dialog.dismiss();
                            }
                        }
                    });
                }
                if (!ListenerUtil.mutListener.listen(6417)) {
                    deleteAllDialog.show();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6411)) {
                    doDeleteMeasurement();
                }
            }
        }
    }

    private void doDeleteMeasurement() {
        if (!ListenerUtil.mutListener.listen(6419)) {
            OpenScale.getInstance().deleteScaleMeasurement(markedMeasurement.getId());
        }
        if (!ListenerUtil.mutListener.listen(6420)) {
            Toast.makeText(graphView.getContext(), getResources().getString(R.string.info_data_deleted), Toast.LENGTH_SHORT).show();
        }
        if (!ListenerUtil.mutListener.listen(6421)) {
            showMenu.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6422)) {
            editMenu.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6423)) {
            deleteMenu.setVisibility(View.GONE);
        }
        if (!ListenerUtil.mutListener.listen(6424)) {
            chartTop.invalidate();
        }
        if (!ListenerUtil.mutListener.listen(6425)) {
            chartView.invalidate();
        }
    }
}
