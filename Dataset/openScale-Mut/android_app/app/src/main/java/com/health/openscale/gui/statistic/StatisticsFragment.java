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
package com.health.openscale.gui.statistic;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import com.health.openscale.core.utils.DateTimeHelpers;
import com.health.openscale.gui.measurement.BMIMeasurementView;
import com.health.openscale.gui.measurement.BoneMeasurementView;
import com.health.openscale.gui.measurement.ChartMarkerView;
import com.health.openscale.gui.measurement.FatMeasurementView;
import com.health.openscale.gui.measurement.FloatMeasurementView;
import com.health.openscale.gui.measurement.MeasurementView;
import com.health.openscale.gui.measurement.MeasurementViewSettings;
import com.health.openscale.gui.measurement.MuscleMeasurementView;
import com.health.openscale.gui.measurement.WaterMeasurementView;
import com.health.openscale.gui.measurement.WeightMeasurementView;
import com.health.openscale.gui.utils.ColorUtil;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StatisticsFragment extends Fragment {

    private View statisticsView;

    private TextView txtGoalWeight;

    private TextView txtGoalDiff;

    private TextView txtGoalDayLeft;

    private TextView txtLabelGoalWeight;

    private TextView txtLabelGoalDiff;

    private TextView txtLabelDayLeft;

    private RadarChart radarChartWeek;

    private RadarChart radarChartMonth;

    private ScaleUser currentScaleUser;

    private ScaleMeasurement lastScaleMeasurement;

    private ArrayList<MeasurementView> viewMeasurementsStatistics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(8929)) {
            statisticsView = inflater.inflate(R.layout.fragment_statistics, container, false);
        }
        if (!ListenerUtil.mutListener.listen(8930)) {
            txtGoalWeight = statisticsView.findViewById(R.id.txtGoalWeight);
        }
        if (!ListenerUtil.mutListener.listen(8931)) {
            txtGoalWeight.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8932)) {
            txtGoalDiff = statisticsView.findViewById(R.id.txtGoalDiff);
        }
        if (!ListenerUtil.mutListener.listen(8933)) {
            txtGoalDiff.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8934)) {
            txtGoalDayLeft = statisticsView.findViewById(R.id.txtGoalDayLeft);
        }
        if (!ListenerUtil.mutListener.listen(8935)) {
            txtGoalDayLeft.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8936)) {
            txtLabelGoalWeight = statisticsView.findViewById(R.id.txtLabelGoalWeight);
        }
        if (!ListenerUtil.mutListener.listen(8937)) {
            txtLabelGoalWeight.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8938)) {
            txtLabelGoalDiff = statisticsView.findViewById(R.id.txtLabelGoalDiff);
        }
        if (!ListenerUtil.mutListener.listen(8939)) {
            txtLabelGoalDiff.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8940)) {
            txtLabelDayLeft = statisticsView.findViewById(R.id.txtLabelDayLeft);
        }
        if (!ListenerUtil.mutListener.listen(8941)) {
            txtLabelDayLeft.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8942)) {
            viewMeasurementsStatistics = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(8943)) {
            viewMeasurementsStatistics.add(new WeightMeasurementView(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8944)) {
            viewMeasurementsStatistics.add(new WaterMeasurementView(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8945)) {
            viewMeasurementsStatistics.add(new MuscleMeasurementView(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8946)) {
            viewMeasurementsStatistics.add(new FatMeasurementView(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8947)) {
            viewMeasurementsStatistics.add(new BoneMeasurementView(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8948)) {
            viewMeasurementsStatistics.add(new BMIMeasurementView(statisticsView.getContext()));
        }
        ArrayList<LegendEntry> legendEntriesWeek = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(8956)) {
            {
                long _loopCounter108 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(8955) ? (i >= viewMeasurementsStatistics.size()) : (ListenerUtil.mutListener.listen(8954) ? (i <= viewMeasurementsStatistics.size()) : (ListenerUtil.mutListener.listen(8953) ? (i > viewMeasurementsStatistics.size()) : (ListenerUtil.mutListener.listen(8952) ? (i != viewMeasurementsStatistics.size()) : (ListenerUtil.mutListener.listen(8951) ? (i == viewMeasurementsStatistics.size()) : (i < viewMeasurementsStatistics.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter108", ++_loopCounter108);
                    LegendEntry legendEntry = new LegendEntry();
                    if (!ListenerUtil.mutListener.listen(8949)) {
                        legendEntry.label = i + " - " + viewMeasurementsStatistics.get(i).getName().toString();
                    }
                    if (!ListenerUtil.mutListener.listen(8950)) {
                        legendEntriesWeek.add(legendEntry);
                    }
                }
            }
        }
        MarkerView mv = new ChartMarkerView(statisticsView.getContext(), R.layout.chart_markerview);
        if (!ListenerUtil.mutListener.listen(8957)) {
            radarChartWeek = statisticsView.findViewById(R.id.radarPastWeek);
        }
        if (!ListenerUtil.mutListener.listen(8958)) {
            radarChartWeek.getXAxis().setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8959)) {
            radarChartWeek.getDescription().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8960)) {
            radarChartWeek.getYAxis().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8961)) {
            radarChartWeek.setExtraTopOffset(10);
        }
        if (!ListenerUtil.mutListener.listen(8962)) {
            radarChartWeek.setRotationEnabled(false);
        }
        Legend weekLegend = radarChartWeek.getLegend();
        if (!ListenerUtil.mutListener.listen(8963)) {
            weekLegend.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8964)) {
            weekLegend.setWordWrapEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(8965)) {
            weekLegend.setExtra(legendEntriesWeek);
        }
        if (!ListenerUtil.mutListener.listen(8966)) {
            weekLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(8967)) {
            mv.setChartView(radarChartWeek);
        }
        if (!ListenerUtil.mutListener.listen(8968)) {
            radarChartWeek.setMarker(mv);
        }
        if (!ListenerUtil.mutListener.listen(8969)) {
            radarChartMonth = statisticsView.findViewById(R.id.radarPastMonth);
        }
        if (!ListenerUtil.mutListener.listen(8970)) {
            radarChartMonth.getXAxis().setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8971)) {
            radarChartMonth.getDescription().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8972)) {
            radarChartMonth.getYAxis().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(8973)) {
            radarChartMonth.setExtraTopOffset(10);
        }
        if (!ListenerUtil.mutListener.listen(8974)) {
            radarChartMonth.setRotationEnabled(false);
        }
        Legend monthLegend = radarChartMonth.getLegend();
        if (!ListenerUtil.mutListener.listen(8975)) {
            monthLegend.setTextColor(ColorUtil.getTintColor(statisticsView.getContext()));
        }
        if (!ListenerUtil.mutListener.listen(8976)) {
            monthLegend.setWordWrapEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(8977)) {
            monthLegend.setExtra(legendEntriesWeek);
        }
        if (!ListenerUtil.mutListener.listen(8978)) {
            monthLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(8979)) {
            mv.setChartView(radarChartMonth);
        }
        if (!ListenerUtil.mutListener.listen(8980)) {
            radarChartMonth.setMarker(mv);
        }
        if (!ListenerUtil.mutListener.listen(8982)) {
            OpenScale.getInstance().getScaleMeasurementsLiveData().observe(getViewLifecycleOwner(), new Observer<List<ScaleMeasurement>>() {

                @Override
                public void onChanged(List<ScaleMeasurement> scaleMeasurements) {
                    if (!ListenerUtil.mutListener.listen(8981)) {
                        updateOnView(scaleMeasurements);
                    }
                }
            });
        }
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {

            @Override
            public void handleOnBackPressed() {
                if (!ListenerUtil.mutListener.listen(8983)) {
                    requireActivity().finish();
                }
            }
        };
        if (!ListenerUtil.mutListener.listen(8984)) {
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);
        }
        return statisticsView;
    }

    public void updateOnView(List<ScaleMeasurement> scaleMeasurementList) {
        if (!ListenerUtil.mutListener.listen(8985)) {
            currentScaleUser = OpenScale.getInstance().getSelectedScaleUser();
        }
        if (!ListenerUtil.mutListener.listen(8990)) {
            if (scaleMeasurementList.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(8987)) {
                    lastScaleMeasurement = new ScaleMeasurement();
                }
                if (!ListenerUtil.mutListener.listen(8988)) {
                    lastScaleMeasurement.setUserId(currentScaleUser.getId());
                }
                if (!ListenerUtil.mutListener.listen(8989)) {
                    lastScaleMeasurement.setWeight(currentScaleUser.getInitialWeight());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(8986)) {
                    lastScaleMeasurement = scaleMeasurementList.get(0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8991)) {
            updateStatistics(scaleMeasurementList);
        }
        if (!ListenerUtil.mutListener.listen(8992)) {
            updateGoal();
        }
    }

    private void updateGoal() {
        final Converters.WeightUnit unit = currentScaleUser.getScaleUnit();
        ScaleMeasurement goalScaleMeasurement = new ScaleMeasurement();
        if (!ListenerUtil.mutListener.listen(8993)) {
            goalScaleMeasurement.setUserId(currentScaleUser.getId());
        }
        if (!ListenerUtil.mutListener.listen(8994)) {
            goalScaleMeasurement.setWeight(currentScaleUser.getGoalWeight());
        }
        if (!ListenerUtil.mutListener.listen(8995)) {
            txtGoalWeight.setText(String.format("%.1f %s", Converters.fromKilogram(goalScaleMeasurement.getWeight(), unit), unit.toString()));
        }
        if (!ListenerUtil.mutListener.listen(9000)) {
            txtGoalDiff.setText(String.format("%.1f %s", Converters.fromKilogram((ListenerUtil.mutListener.listen(8999) ? (goalScaleMeasurement.getWeight() % lastScaleMeasurement.getWeight()) : (ListenerUtil.mutListener.listen(8998) ? (goalScaleMeasurement.getWeight() / lastScaleMeasurement.getWeight()) : (ListenerUtil.mutListener.listen(8997) ? (goalScaleMeasurement.getWeight() * lastScaleMeasurement.getWeight()) : (ListenerUtil.mutListener.listen(8996) ? (goalScaleMeasurement.getWeight() + lastScaleMeasurement.getWeight()) : (goalScaleMeasurement.getWeight() - lastScaleMeasurement.getWeight()))))), unit), unit.toString()));
        }
        Calendar goalCalendar = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(9001)) {
            goalCalendar.setTime(currentScaleUser.getGoalDate());
        }
        int days = Math.max(0, DateTimeHelpers.daysBetween(Calendar.getInstance(), goalCalendar));
        if (!ListenerUtil.mutListener.listen(9002)) {
            txtGoalDayLeft.setText(getResources().getQuantityString(R.plurals.label_days, days, days));
        }
        boolean isBmiEnabled = new MeasurementViewSettings(PreferenceManager.getDefaultSharedPreferences(getActivity()), BMIMeasurementView.KEY).isEnabled();
        final float goalBmi = goalScaleMeasurement.getBMI(currentScaleUser.getBodyHeight());
        if (!ListenerUtil.mutListener.listen(9003)) {
            txtLabelGoalWeight.setText(isBmiEnabled ? Html.fromHtml(String.format("%s<br><font color='grey'><small>%s: %.1f</small></font>", getResources().getString(R.string.label_goal_weight), getResources().getString(R.string.label_bmi), goalBmi)) : getResources().getString(R.string.label_goal_weight));
        }
        if (!ListenerUtil.mutListener.listen(9008)) {
            txtLabelGoalDiff.setText(isBmiEnabled ? Html.fromHtml(String.format("%s<br><font color='grey'><small>%s: %.1f</small></font>", getResources().getString(R.string.label_weight_difference), getResources().getString(R.string.label_bmi), (ListenerUtil.mutListener.listen(9007) ? (lastScaleMeasurement.getBMI(currentScaleUser.getBodyHeight()) % goalBmi) : (ListenerUtil.mutListener.listen(9006) ? (lastScaleMeasurement.getBMI(currentScaleUser.getBodyHeight()) / goalBmi) : (ListenerUtil.mutListener.listen(9005) ? (lastScaleMeasurement.getBMI(currentScaleUser.getBodyHeight()) * goalBmi) : (ListenerUtil.mutListener.listen(9004) ? (lastScaleMeasurement.getBMI(currentScaleUser.getBodyHeight()) + goalBmi) : (lastScaleMeasurement.getBMI(currentScaleUser.getBodyHeight()) - goalBmi))))))) : getResources().getString(R.string.label_weight_difference));
        }
        if (!ListenerUtil.mutListener.listen(9009)) {
            txtLabelDayLeft.setText(Html.fromHtml(String.format("%s<br><font color='grey'><small>%s %s</small></font>", getResources().getString(R.string.label_days_left), getResources().getString(R.string.label_goal_date_is), DateFormat.getDateInstance(DateFormat.LONG).format(currentScaleUser.getGoalDate()))));
        }
    }

    private void updateStatistics(List<ScaleMeasurement> scaleMeasurementList) {
        if (!ListenerUtil.mutListener.listen(9010)) {
            radarChartWeek.clear();
        }
        if (!ListenerUtil.mutListener.listen(9011)) {
            radarChartMonth.clear();
        }
        Calendar histDate = Calendar.getInstance();
        Calendar weekPastDate = Calendar.getInstance();
        Calendar monthPastDate = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(9012)) {
            weekPastDate.setTime(lastScaleMeasurement.getDateTime());
        }
        if (!ListenerUtil.mutListener.listen(9013)) {
            weekPastDate.add(Calendar.DATE, -7);
        }
        if (!ListenerUtil.mutListener.listen(9014)) {
            monthPastDate.setTime(lastScaleMeasurement.getDateTime());
        }
        if (!ListenerUtil.mutListener.listen(9015)) {
            monthPastDate.add(Calendar.DATE, -30);
        }
        ScaleMeasurement averageWeek = new ScaleMeasurement();
        ScaleMeasurement averageMonth = new ScaleMeasurement();
        ArrayList<RadarEntry> entriesLastMeasurement = new ArrayList<>();
        ArrayList<RadarEntry> entriesAvgWeek = new ArrayList<>();
        ArrayList<RadarEntry> entriesAvgMonth = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9021)) {
            {
                long _loopCounter109 = 0;
                for (ScaleMeasurement measurement : scaleMeasurementList) {
                    ListenerUtil.loopListener.listen("_loopCounter109", ++_loopCounter109);
                    if (!ListenerUtil.mutListener.listen(9016)) {
                        histDate.setTime(measurement.getDateTime());
                    }
                    if (!ListenerUtil.mutListener.listen(9018)) {
                        if (weekPastDate.before(histDate)) {
                            if (!ListenerUtil.mutListener.listen(9017)) {
                                averageWeek.add(measurement);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(9020)) {
                        if (monthPastDate.before(histDate)) {
                            if (!ListenerUtil.mutListener.listen(9019)) {
                                averageMonth.add(measurement);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(9022)) {
            averageWeek.divide(averageWeek.count());
        }
        if (!ListenerUtil.mutListener.listen(9023)) {
            averageMonth.divide(averageMonth.count());
        }
        if (!ListenerUtil.mutListener.listen(9033)) {
            {
                long _loopCounter110 = 0;
                for (MeasurementView view : viewMeasurementsStatistics) {
                    ListenerUtil.loopListener.listen("_loopCounter110", ++_loopCounter110);
                    final FloatMeasurementView measurementView = (FloatMeasurementView) view;
                    Object[] extraData = new Object[3];
                    if (!ListenerUtil.mutListener.listen(9024)) {
                        // not needed
                        extraData[0] = null;
                    }
                    if (!ListenerUtil.mutListener.listen(9025)) {
                        // not needed
                        extraData[1] = null;
                    }
                    if (!ListenerUtil.mutListener.listen(9026)) {
                        extraData[2] = measurementView;
                    }
                    if (!ListenerUtil.mutListener.listen(9027)) {
                        measurementView.loadFrom(averageMonth, null);
                    }
                    if (!ListenerUtil.mutListener.listen(9028)) {
                        entriesAvgMonth.add(new RadarEntry(measurementView.getValue(), extraData));
                    }
                    if (!ListenerUtil.mutListener.listen(9029)) {
                        measurementView.loadFrom(averageWeek, null);
                    }
                    if (!ListenerUtil.mutListener.listen(9030)) {
                        entriesAvgWeek.add(new RadarEntry(measurementView.getValue(), extraData));
                    }
                    if (!ListenerUtil.mutListener.listen(9031)) {
                        measurementView.loadFrom(lastScaleMeasurement, null);
                    }
                    if (!ListenerUtil.mutListener.listen(9032)) {
                        entriesLastMeasurement.add(new RadarEntry(measurementView.getValue(), extraData));
                    }
                }
            }
        }
        RadarDataSet setLastMeasurement = new RadarDataSet(entriesLastMeasurement, getString(R.string.label_title_last_measurement));
        if (!ListenerUtil.mutListener.listen(9034)) {
            setLastMeasurement.setColor(ColorUtil.COLOR_BLUE);
        }
        if (!ListenerUtil.mutListener.listen(9035)) {
            setLastMeasurement.setFillColor(ColorUtil.COLOR_BLUE);
        }
        if (!ListenerUtil.mutListener.listen(9036)) {
            setLastMeasurement.setDrawFilled(true);
        }
        if (!ListenerUtil.mutListener.listen(9037)) {
            setLastMeasurement.setFillAlpha(180);
        }
        if (!ListenerUtil.mutListener.listen(9038)) {
            setLastMeasurement.setLineWidth(2f);
        }
        if (!ListenerUtil.mutListener.listen(9039)) {
            setLastMeasurement.setDrawHighlightCircleEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(9040)) {
            setLastMeasurement.setDrawHighlightIndicators(false);
        }
        RadarDataSet setAvgWeek = new RadarDataSet(entriesAvgWeek, getString(R.string.label_last_week));
        if (!ListenerUtil.mutListener.listen(9041)) {
            setAvgWeek.setColor(ColorUtil.COLOR_GREEN);
        }
        if (!ListenerUtil.mutListener.listen(9042)) {
            setAvgWeek.setFillColor(ColorUtil.COLOR_GREEN);
        }
        if (!ListenerUtil.mutListener.listen(9043)) {
            setAvgWeek.setDrawFilled(true);
        }
        if (!ListenerUtil.mutListener.listen(9044)) {
            setAvgWeek.setFillAlpha(180);
        }
        if (!ListenerUtil.mutListener.listen(9045)) {
            setAvgWeek.setLineWidth(2f);
        }
        if (!ListenerUtil.mutListener.listen(9046)) {
            setAvgWeek.setDrawHighlightCircleEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(9047)) {
            setAvgWeek.setDrawHighlightIndicators(false);
        }
        RadarDataSet setAvgMonth = new RadarDataSet(entriesAvgMonth, getString(R.string.label_last_month));
        if (!ListenerUtil.mutListener.listen(9048)) {
            setAvgMonth.setColor(ColorUtil.COLOR_GREEN);
        }
        if (!ListenerUtil.mutListener.listen(9049)) {
            setAvgMonth.setFillColor(ColorUtil.COLOR_GREEN);
        }
        if (!ListenerUtil.mutListener.listen(9050)) {
            setAvgMonth.setDrawFilled(true);
        }
        if (!ListenerUtil.mutListener.listen(9051)) {
            setAvgMonth.setFillAlpha(180);
        }
        if (!ListenerUtil.mutListener.listen(9052)) {
            setAvgMonth.setLineWidth(2f);
        }
        if (!ListenerUtil.mutListener.listen(9053)) {
            setAvgMonth.setDrawHighlightCircleEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(9054)) {
            setAvgMonth.setDrawHighlightIndicators(false);
        }
        ArrayList<IRadarDataSet> setsAvgWeek = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9055)) {
            setsAvgWeek.add(setAvgWeek);
        }
        if (!ListenerUtil.mutListener.listen(9056)) {
            setsAvgWeek.add(setLastMeasurement);
        }
        ArrayList<IRadarDataSet> setsAvgMonth = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(9057)) {
            setsAvgMonth.add(setAvgMonth);
        }
        if (!ListenerUtil.mutListener.listen(9058)) {
            setsAvgMonth.add(setLastMeasurement);
        }
        RadarData dataAvgWeek = new RadarData(setsAvgWeek);
        if (!ListenerUtil.mutListener.listen(9059)) {
            dataAvgWeek.setValueTextSize(8f);
        }
        if (!ListenerUtil.mutListener.listen(9060)) {
            dataAvgWeek.setDrawValues(false);
        }
        if (!ListenerUtil.mutListener.listen(9061)) {
            dataAvgWeek.setValueFormatter(new ValueFormatter() {

                @Override
                public String getRadarLabel(RadarEntry radarEntry) {
                    FloatMeasurementView measurementView = (FloatMeasurementView) radarEntry.getData();
                    return measurementView.getValueAsString(true);
                }
            });
        }
        RadarData dataAvgMonth = new RadarData(setsAvgMonth);
        if (!ListenerUtil.mutListener.listen(9062)) {
            dataAvgMonth.setValueTextSize(8f);
        }
        if (!ListenerUtil.mutListener.listen(9063)) {
            dataAvgMonth.setDrawValues(false);
        }
        if (!ListenerUtil.mutListener.listen(9064)) {
            dataAvgMonth.setValueFormatter(new ValueFormatter() {

                @Override
                public String getRadarLabel(RadarEntry radarEntry) {
                    FloatMeasurementView measurementView = (FloatMeasurementView) radarEntry.getData();
                    return measurementView.getValueAsString(true);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(9065)) {
            radarChartWeek.setData(dataAvgWeek);
        }
        if (!ListenerUtil.mutListener.listen(9066)) {
            radarChartMonth.setData(dataAvgMonth);
        }
        if (!ListenerUtil.mutListener.listen(9067)) {
            radarChartWeek.animateXY(1000, 1000);
        }
        if (!ListenerUtil.mutListener.listen(9068)) {
            radarChartMonth.animateXY(1000, 1000);
        }
        if (!ListenerUtil.mutListener.listen(9069)) {
            radarChartWeek.invalidate();
        }
        if (!ListenerUtil.mutListener.listen(9070)) {
            radarChartMonth.invalidate();
        }
    }
}
