/* Copyright (C) 2019  olie.xdev <olie.xdev@googlemail.com>
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

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.RectF;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.health.openscale.R;
import com.health.openscale.core.OpenScale;
import com.health.openscale.core.datatypes.ScaleMeasurement;
import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import com.health.openscale.core.utils.PolynomialFitter;
import com.health.openscale.gui.utils.ColorUtil;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import static java.time.temporal.ChronoUnit.DAYS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ChartMeasurementView extends LineChart {

    public enum ViewMode {

        DAY_OF_MONTH,
        WEEK_OF_MONTH,
        WEEK_OF_YEAR,
        MONTH_OF_YEAR,
        DAY_OF_YEAR,
        DAY_OF_ALL,
        WEEK_OF_ALL,
        MONTH_OF_ALL,
        YEAR_OF_ALL
    }

    private OpenScale openScale;

    private SharedPreferences prefs;

    private List<MeasurementView> measurementViews;

    private List<ScaleMeasurement> scaleMeasurementList;

    private ViewMode viewMode;

    private boolean isInGraphKey;

    private ProgressBar progressBar;

    public ChartMeasurementView(Context context) {
        super(context);
        if (!ListenerUtil.mutListener.listen(6505)) {
            initChart();
        }
    }

    public ChartMeasurementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!ListenerUtil.mutListener.listen(6506)) {
            initChart();
        }
    }

    public ChartMeasurementView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!ListenerUtil.mutListener.listen(6507)) {
            initChart();
        }
    }

    public void setViewRange(final ViewMode mode) {
        if (!ListenerUtil.mutListener.listen(6508)) {
            viewMode = mode;
        }
        if (!ListenerUtil.mutListener.listen(6509)) {
            setGranularityAndRange(1980, 1);
        }
        if (!ListenerUtil.mutListener.listen(6510)) {
            setXValueFormat(viewMode);
        }
        if (!ListenerUtil.mutListener.listen(6512)) {
            if (openScale.getLastScaleMeasurement() != null) {
                if (!ListenerUtil.mutListener.listen(6511)) {
                    moveViewToX(convertDateToInt(openScale.getLastScaleMeasurement().getDateTime()));
                }
            }
        }
    }

    public void setViewRange(int year, final ViewMode mode) {
        if (!ListenerUtil.mutListener.listen(6513)) {
            viewMode = mode;
        }
        if (!ListenerUtil.mutListener.listen(6514)) {
            setGranularityAndRange(year, 1);
        }
        if (!ListenerUtil.mutListener.listen(6515)) {
            setXValueFormat(viewMode);
        }
        LocalDate startDate = LocalDate.of(year, 1, 1);
        if (!ListenerUtil.mutListener.listen(6516)) {
            moveViewToX(convertDateToInt(startDate));
        }
    }

    public void setViewRange(int year, int month, final ViewMode mode) {
        if (!ListenerUtil.mutListener.listen(6517)) {
            viewMode = mode;
        }
        if (!ListenerUtil.mutListener.listen(6518)) {
            setGranularityAndRange(year, month);
        }
        if (!ListenerUtil.mutListener.listen(6519)) {
            setXValueFormat(viewMode);
        }
        LocalDate startDate = LocalDate.of(year, month, 1);
        if (!ListenerUtil.mutListener.listen(6520)) {
            moveViewToX(convertDateToInt(startDate));
        }
    }

    private void setGranularityAndRange(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, 1);
        int range = 0;
        int granularity = 0;
        if (!ListenerUtil.mutListener.listen(6548)) {
            switch(viewMode) {
                case DAY_OF_MONTH:
                    if (!ListenerUtil.mutListener.listen(6521)) {
                        endDate = startDate.plusMonths(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6522)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6523)) {
                        granularity = 1;
                    }
                    break;
                case WEEK_OF_MONTH:
                    if (!ListenerUtil.mutListener.listen(6524)) {
                        endDate = startDate.plusMonths(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6525)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6526)) {
                        granularity = 7;
                    }
                    break;
                case WEEK_OF_YEAR:
                    if (!ListenerUtil.mutListener.listen(6527)) {
                        endDate = startDate.plusYears(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6528)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6529)) {
                        granularity = 7;
                    }
                    break;
                case MONTH_OF_YEAR:
                    if (!ListenerUtil.mutListener.listen(6530)) {
                        endDate = startDate.plusYears(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6531)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6532)) {
                        granularity = 30;
                    }
                    break;
                case DAY_OF_YEAR:
                    if (!ListenerUtil.mutListener.listen(6533)) {
                        endDate = startDate.plusYears(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6534)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6535)) {
                        granularity = 1;
                    }
                    break;
                case DAY_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(6536)) {
                        endDate = startDate.plusMonths(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6537)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6538)) {
                        granularity = 1;
                    }
                    break;
                case WEEK_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(6539)) {
                        endDate = startDate.plusMonths(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6540)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6541)) {
                        granularity = 7;
                    }
                    break;
                case MONTH_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(6542)) {
                        endDate = startDate.plusMonths(3);
                    }
                    if (!ListenerUtil.mutListener.listen(6543)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6544)) {
                        granularity = 30;
                    }
                    break;
                case YEAR_OF_ALL:
                    if (!ListenerUtil.mutListener.listen(6545)) {
                        endDate = startDate.plusYears(1);
                    }
                    if (!ListenerUtil.mutListener.listen(6546)) {
                        range = (int) DAYS.between(startDate, endDate);
                    }
                    if (!ListenerUtil.mutListener.listen(6547)) {
                        granularity = 365;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("view mode not implemented");
            }
        }
        if (!ListenerUtil.mutListener.listen(6549)) {
            getXAxis().setGranularity(granularity);
        }
        if (!ListenerUtil.mutListener.listen(6550)) {
            setVisibleXRangeMaximum(range);
        }
        if (!ListenerUtil.mutListener.listen(6551)) {
            // set custom viewPortOffsets to avoid jitter on translating while auto scale is on
            setCustomViewPortOffsets();
        }
    }

    public void setIsInGraphKey(boolean status) {
        if (!ListenerUtil.mutListener.listen(6552)) {
            isInGraphKey = status;
        }
    }

    public void setProgressBar(ProgressBar bar) {
        if (!ListenerUtil.mutListener.listen(6553)) {
            progressBar = bar;
        }
    }

    private void initChart() {
        if (!ListenerUtil.mutListener.listen(6554)) {
            prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        }
        if (!ListenerUtil.mutListener.listen(6555)) {
            openScale = OpenScale.getInstance();
        }
        if (!ListenerUtil.mutListener.listen(6556)) {
            measurementViews = MeasurementView.getMeasurementList(getContext(), MeasurementView.DateTimeOrder.NONE);
        }
        if (!ListenerUtil.mutListener.listen(6557)) {
            isInGraphKey = true;
        }
        if (!ListenerUtil.mutListener.listen(6558)) {
            progressBar = null;
        }
        if (!ListenerUtil.mutListener.listen(6559)) {
            setHardwareAccelerationEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6560)) {
            setAutoScaleMinMaxEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6561)) {
            setMarker(new ChartMarkerView(getContext(), R.layout.chart_markerview));
        }
        if (!ListenerUtil.mutListener.listen(6562)) {
            setDoubleTapToZoomEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6563)) {
            setHighlightPerTapEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6564)) {
            getLegend().setEnabled(prefs.getBoolean("legendEnable", true));
        }
        if (!ListenerUtil.mutListener.listen(6565)) {
            getLegend().setWordWrapEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6566)) {
            getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        }
        if (!ListenerUtil.mutListener.listen(6567)) {
            getLegend().setTextColor(ColorUtil.getTintColor(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6568)) {
            getDescription().setEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6569)) {
            getAxisLeft().setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6570)) {
            getAxisRight().setEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6571)) {
            getAxisLeft().setTextColor(ColorUtil.getTintColor(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6572)) {
            getAxisRight().setTextColor(ColorUtil.getTintColor(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6573)) {
            getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        }
        if (!ListenerUtil.mutListener.listen(6574)) {
            getXAxis().setTextColor(ColorUtil.getTintColor(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6575)) {
            getXAxis().setGranularityEnabled(true);
        }
    }

    private int convertDateToInt(LocalDate date) {
        return (int) date.toEpochDay();
    }

    private int convertDateToInt(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) localDate.toEpochDay();
    }

    private LocalDate convertIntToDate(int shortDate) {
        return LocalDate.ofEpochDay(shortDate);
    }

    private void setXValueFormat(final ViewMode mode) {
        if (!ListenerUtil.mutListener.listen(6576)) {
            getXAxis().setValueFormatter(new ValueFormatter() {

                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    DateTimeFormatter formatter;
                    switch(mode) {
                        case DAY_OF_MONTH:
                            formatter = DateTimeFormatter.ofPattern("dd");
                            break;
                        case WEEK_OF_MONTH:
                            formatter = DateTimeFormatter.ofPattern("'W'W");
                            break;
                        case WEEK_OF_YEAR:
                            formatter = DateTimeFormatter.ofPattern("'W'w");
                            break;
                        case MONTH_OF_YEAR:
                            formatter = DateTimeFormatter.ofPattern("MMM");
                            break;
                        case DAY_OF_YEAR:
                            formatter = DateTimeFormatter.ofPattern("D");
                            break;
                        case DAY_OF_ALL:
                            formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
                            break;
                        case WEEK_OF_ALL:
                            formatter = DateTimeFormatter.ofPattern("'W'w yyyy");
                            break;
                        case MONTH_OF_ALL:
                            formatter = DateTimeFormatter.ofPattern("MMM yyyy");
                            break;
                        case YEAR_OF_ALL:
                            formatter = DateTimeFormatter.ofPattern("yyyy");
                            break;
                        default:
                            throw new IllegalArgumentException("view mode not implemented");
                    }
                    return formatter.format(convertIntToDate((int) value));
                }
            });
        }
    }

    private void setCustomViewPortOffsets() {
        float offsetLeft = 0f, offsetRight = 0f, offsetTop = 0f, offsetBottom = 0f;
        RectF mOffsetsBuffer = new RectF();
        if (!ListenerUtil.mutListener.listen(6577)) {
            calculateLegendOffsets(mOffsetsBuffer);
        }
        if (!ListenerUtil.mutListener.listen(6578)) {
            offsetLeft += mOffsetsBuffer.left;
        }
        if (!ListenerUtil.mutListener.listen(6579)) {
            offsetTop += mOffsetsBuffer.top;
        }
        if (!ListenerUtil.mutListener.listen(6580)) {
            offsetRight += mOffsetsBuffer.right;
        }
        if (!ListenerUtil.mutListener.listen(6581)) {
            offsetBottom += Math.max(70f, mOffsetsBuffer.bottom);
        }
        if (!ListenerUtil.mutListener.listen(6583)) {
            // offsets for y-labels
            if (mAxisLeft.needsOffset()) {
                if (!ListenerUtil.mutListener.listen(6582)) {
                    offsetLeft += mAxisLeft.getRequiredWidthSpace(mAxisRendererLeft.getPaintAxisLabels());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6585)) {
            if (mAxisRight.needsOffset()) {
                if (!ListenerUtil.mutListener.listen(6584)) {
                    offsetRight += mAxisRight.getRequiredWidthSpace(mAxisRendererRight.getPaintAxisLabels());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6592)) {
            if ((ListenerUtil.mutListener.listen(6586) ? (mXAxis.isEnabled() || mXAxis.isDrawLabelsEnabled()) : (mXAxis.isEnabled() && mXAxis.isDrawLabelsEnabled()))) {
                float xLabelHeight = mXAxis.mLabelRotatedHeight + mXAxis.getYOffset();
                if (!ListenerUtil.mutListener.listen(6591)) {
                    // offsets for x-labels
                    if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTTOM) {
                        if (!ListenerUtil.mutListener.listen(6590)) {
                            offsetBottom += xLabelHeight;
                        }
                    } else if (mXAxis.getPosition() == XAxis.XAxisPosition.TOP) {
                        if (!ListenerUtil.mutListener.listen(6589)) {
                            offsetTop += xLabelHeight;
                        }
                    } else if (mXAxis.getPosition() == XAxis.XAxisPosition.BOTH_SIDED) {
                        if (!ListenerUtil.mutListener.listen(6587)) {
                            offsetBottom += xLabelHeight;
                        }
                        if (!ListenerUtil.mutListener.listen(6588)) {
                            offsetTop += xLabelHeight;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6593)) {
            offsetTop += getExtraTopOffset();
        }
        if (!ListenerUtil.mutListener.listen(6594)) {
            offsetRight += getExtraRightOffset();
        }
        if (!ListenerUtil.mutListener.listen(6595)) {
            offsetBottom += getExtraBottomOffset();
        }
        if (!ListenerUtil.mutListener.listen(6596)) {
            offsetLeft += getExtraLeftOffset();
        }
        float minOffset = Utils.convertDpToPixel(mMinOffset);
        if (!ListenerUtil.mutListener.listen(6597)) {
            setViewPortOffsets(Math.max(minOffset, offsetLeft), Math.max(minOffset, offsetTop), Math.max(minOffset, offsetRight), Math.max(minOffset, offsetBottom));
        }
    }

    public void updateMeasurementList(final List<ScaleMeasurement> scaleMeasurementList) {
        if (!ListenerUtil.mutListener.listen(6598)) {
            clear();
        }
        if (!ListenerUtil.mutListener.listen(6600)) {
            if (scaleMeasurementList.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(6599)) {
                    progressBar.setVisibility(GONE);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6601)) {
            Collections.reverse(scaleMeasurementList);
        }
        if (!ListenerUtil.mutListener.listen(6602)) {
            this.scaleMeasurementList = scaleMeasurementList;
        }
        if (!ListenerUtil.mutListener.listen(6603)) {
            refreshMeasurementList();
        }
    }

    public void refreshMeasurementList() {
        if (!ListenerUtil.mutListener.listen(6604)) {
            // deselect any highlighted value
            highlightValue(null, false);
        }
        if (!ListenerUtil.mutListener.listen(6606)) {
            if (scaleMeasurementList == null) {
                if (!ListenerUtil.mutListener.listen(6605)) {
                    progressBar.setVisibility(GONE);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(6607)) {
            progressBar.setVisibility(VISIBLE);
        }
        List<ILineDataSet> lineDataSets;
        lineDataSets = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(6639)) {
            {
                long _loopCounter70 = 0;
                for (MeasurementView view : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter70", ++_loopCounter70);
                    if (!ListenerUtil.mutListener.listen(6638)) {
                        if ((ListenerUtil.mutListener.listen(6608) ? (view instanceof FloatMeasurementView || view.isVisible()) : (view instanceof FloatMeasurementView && view.isVisible()))) {
                            final FloatMeasurementView measurementView = (FloatMeasurementView) view;
                            final List<Entry> lineEntries = new ArrayList<>();
                            if (!ListenerUtil.mutListener.listen(6636)) {
                                {
                                    long _loopCounter69 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(6635) ? (i >= scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6634) ? (i <= scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6633) ? (i > scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6632) ? (i != scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6631) ? (i == scaleMeasurementList.size()) : (i < scaleMeasurementList.size())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter69", ++_loopCounter69);
                                        ScaleMeasurement measurement = scaleMeasurementList.get(i);
                                        float value = measurementView.getConvertedMeasurementValue(measurement);
                                        if (!ListenerUtil.mutListener.listen(6614)) {
                                            if ((ListenerUtil.mutListener.listen(6613) ? (value >= 0.0f) : (ListenerUtil.mutListener.listen(6612) ? (value <= 0.0f) : (ListenerUtil.mutListener.listen(6611) ? (value > 0.0f) : (ListenerUtil.mutListener.listen(6610) ? (value < 0.0f) : (ListenerUtil.mutListener.listen(6609) ? (value != 0.0f) : (value == 0.0f))))))) {
                                                continue;
                                            }
                                        }
                                        Entry entry = new Entry();
                                        if (!ListenerUtil.mutListener.listen(6615)) {
                                            entry.setX(convertDateToInt(measurement.getDateTime()));
                                        }
                                        if (!ListenerUtil.mutListener.listen(6616)) {
                                            entry.setY(value);
                                        }
                                        Object[] extraData = new Object[3];
                                        if (!ListenerUtil.mutListener.listen(6617)) {
                                            extraData[0] = measurement;
                                        }
                                        if (!ListenerUtil.mutListener.listen(6627)) {
                                            extraData[1] = ((ListenerUtil.mutListener.listen(6622) ? (i >= 0) : (ListenerUtil.mutListener.listen(6621) ? (i <= 0) : (ListenerUtil.mutListener.listen(6620) ? (i > 0) : (ListenerUtil.mutListener.listen(6619) ? (i < 0) : (ListenerUtil.mutListener.listen(6618) ? (i != 0) : (i == 0))))))) ? null : scaleMeasurementList.get((ListenerUtil.mutListener.listen(6626) ? (i % 1) : (ListenerUtil.mutListener.listen(6625) ? (i / 1) : (ListenerUtil.mutListener.listen(6624) ? (i * 1) : (ListenerUtil.mutListener.listen(6623) ? (i + 1) : (i - 1))))));
                                        }
                                        if (!ListenerUtil.mutListener.listen(6628)) {
                                            extraData[2] = measurementView;
                                        }
                                        if (!ListenerUtil.mutListener.listen(6629)) {
                                            entry.setData(extraData);
                                        }
                                        if (!ListenerUtil.mutListener.listen(6630)) {
                                            lineEntries.add(entry);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6637)) {
                                addMeasurementLine(lineDataSets, lineEntries, measurementView);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6641)) {
            if (prefs.getBoolean("trendLine", false)) {
                if (!ListenerUtil.mutListener.listen(6640)) {
                    addTrendLine(lineDataSets);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6644)) {
            if (!lineDataSets.isEmpty()) {
                LineData data = new LineData(lineDataSets);
                if (!ListenerUtil.mutListener.listen(6643)) {
                    setData(data);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(6642)) {
                    setData(null);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6646)) {
            if (prefs.getBoolean("goalLine", false)) {
                if (!ListenerUtil.mutListener.listen(6645)) {
                    addGoalLine(lineDataSets);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6647)) {
            progressBar.setVisibility(GONE);
        }
    }

    private void addMeasurementLine(List<ILineDataSet> lineDataSets, List<Entry> lineEntries, FloatMeasurementView measurementView) {
        LineDataSet measurementLine = new LineDataSet(lineEntries, measurementView.getName().toString());
        if (!ListenerUtil.mutListener.listen(6648)) {
            measurementLine.setLineWidth(1.5f);
        }
        if (!ListenerUtil.mutListener.listen(6649)) {
            measurementLine.setValueTextSize(10.0f);
        }
        if (!ListenerUtil.mutListener.listen(6650)) {
            measurementLine.setColor(measurementView.getColor());
        }
        if (!ListenerUtil.mutListener.listen(6651)) {
            measurementLine.setValueTextColor(ColorUtil.getTintColor(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6652)) {
            measurementLine.setCircleColor(measurementView.getColor());
        }
        if (!ListenerUtil.mutListener.listen(6653)) {
            measurementLine.setCircleHoleColor(measurementView.getColor());
        }
        if (!ListenerUtil.mutListener.listen(6654)) {
            measurementLine.setAxisDependency(measurementView.getSettings().isOnRightAxis() ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT);
        }
        if (!ListenerUtil.mutListener.listen(6655)) {
            measurementLine.setHighlightEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6656)) {
            measurementLine.setDrawHighlightIndicators(true);
        }
        if (!ListenerUtil.mutListener.listen(6657)) {
            measurementLine.setHighlightLineWidth(1.5f);
        }
        if (!ListenerUtil.mutListener.listen(6658)) {
            measurementLine.setDrawHorizontalHighlightIndicator(false);
        }
        if (!ListenerUtil.mutListener.listen(6659)) {
            measurementLine.setHighLightColor(Color.RED);
        }
        if (!ListenerUtil.mutListener.listen(6660)) {
            measurementLine.setDrawCircles(prefs.getBoolean("pointsEnable", true));
        }
        if (!ListenerUtil.mutListener.listen(6661)) {
            measurementLine.setDrawValues(prefs.getBoolean("labelsEnable", false));
        }
        if (!ListenerUtil.mutListener.listen(6662)) {
            measurementLine.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        }
        if (!ListenerUtil.mutListener.listen(6664)) {
            if (prefs.getBoolean("trendLine", false)) {
                if (!ListenerUtil.mutListener.listen(6663)) {
                    // show only data point if trend line is enabled
                    measurementLine.enableDashedLine(0, 1, 0);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(6671)) {
            if ((ListenerUtil.mutListener.listen(6665) ? (measurementView.isVisible() || !lineEntries.isEmpty()) : (measurementView.isVisible() && !lineEntries.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(6670)) {
                    if (isInGraphKey) {
                        if (!ListenerUtil.mutListener.listen(6669)) {
                            if (measurementView.getSettings().isInGraph()) {
                                if (!ListenerUtil.mutListener.listen(6668)) {
                                    lineDataSets.add(measurementLine);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6667)) {
                            if (measurementView.getSettings().isInOverviewGraph()) {
                                if (!ListenerUtil.mutListener.listen(6666)) {
                                    lineDataSets.add(measurementLine);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addGoalLine(List<ILineDataSet> lineDataSets) {
        List<Entry> valuesGoalLine = new Stack<>();
        ScaleUser user = OpenScale.getInstance().getSelectedScaleUser();
        float goalWeight = Converters.fromKilogram(user.getGoalWeight(), user.getScaleUnit());
        if (!ListenerUtil.mutListener.listen(6672)) {
            valuesGoalLine.add(new Entry(getXChartMin(), goalWeight));
        }
        if (!ListenerUtil.mutListener.listen(6673)) {
            valuesGoalLine.add(new Entry(getXChartMax(), goalWeight));
        }
        LineDataSet goalLine = new LineDataSet(valuesGoalLine, getContext().getString(R.string.label_goal_line));
        if (!ListenerUtil.mutListener.listen(6674)) {
            goalLine.setLineWidth(1.5f);
        }
        if (!ListenerUtil.mutListener.listen(6675)) {
            goalLine.setColor(ColorUtil.COLOR_GREEN);
        }
        if (!ListenerUtil.mutListener.listen(6676)) {
            goalLine.setAxisDependency(prefs.getBoolean("weightOnRightAxis", true) ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT);
        }
        if (!ListenerUtil.mutListener.listen(6677)) {
            goalLine.setDrawValues(false);
        }
        if (!ListenerUtil.mutListener.listen(6678)) {
            goalLine.setDrawCircles(false);
        }
        if (!ListenerUtil.mutListener.listen(6679)) {
            goalLine.setHighlightEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6680)) {
            goalLine.enableDashedLine(10, 30, 0);
        }
        if (!ListenerUtil.mutListener.listen(6681)) {
            lineDataSets.add(goalLine);
        }
    }

    private List<ScaleMeasurement> getScaleMeasurementsAsTrendline(List<ScaleMeasurement> measurementList) {
        List<ScaleMeasurement> trendlineList = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(6682)) {
            // exponentially smoothed moving average with 10% smoothing
            trendlineList.add(measurementList.get(0));
        }
        if (!ListenerUtil.mutListener.listen(6696)) {
            {
                long _loopCounter71 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(6695) ? (i >= measurementList.size()) : (ListenerUtil.mutListener.listen(6694) ? (i <= measurementList.size()) : (ListenerUtil.mutListener.listen(6693) ? (i > measurementList.size()) : (ListenerUtil.mutListener.listen(6692) ? (i != measurementList.size()) : (ListenerUtil.mutListener.listen(6691) ? (i == measurementList.size()) : (i < measurementList.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter71", ++_loopCounter71);
                    ScaleMeasurement entry = measurementList.get(i).clone();
                    ScaleMeasurement trendPreviousEntry = trendlineList.get((ListenerUtil.mutListener.listen(6686) ? (i % 1) : (ListenerUtil.mutListener.listen(6685) ? (i / 1) : (ListenerUtil.mutListener.listen(6684) ? (i * 1) : (ListenerUtil.mutListener.listen(6683) ? (i + 1) : (i - 1))))));
                    if (!ListenerUtil.mutListener.listen(6687)) {
                        entry.subtract(trendPreviousEntry);
                    }
                    if (!ListenerUtil.mutListener.listen(6688)) {
                        entry.multiply(0.1f);
                    }
                    if (!ListenerUtil.mutListener.listen(6689)) {
                        entry.add(trendPreviousEntry);
                    }
                    if (!ListenerUtil.mutListener.listen(6690)) {
                        trendlineList.add(entry);
                    }
                }
            }
        }
        return trendlineList;
    }

    private void addTrendLine(List<ILineDataSet> lineDataSets) {
        if (!ListenerUtil.mutListener.listen(6737)) {
            {
                long _loopCounter74 = 0;
                for (MeasurementView view : measurementViews) {
                    ListenerUtil.loopListener.listen("_loopCounter74", ++_loopCounter74);
                    if (!ListenerUtil.mutListener.listen(6736)) {
                        if ((ListenerUtil.mutListener.listen(6697) ? (view instanceof FloatMeasurementView || view.isVisible()) : (view instanceof FloatMeasurementView && view.isVisible()))) {
                            final FloatMeasurementView measurementView = (FloatMeasurementView) view;
                            final List<Entry> lineEntries = new ArrayList<>();
                            ArrayList<ScaleMeasurement> nonZeroScaleMeasurementList = new ArrayList<>();
                            if (!ListenerUtil.mutListener.listen(6710)) {
                                {
                                    long _loopCounter72 = 0;
                                    // filter first all zero measurements out, so that the follow-up trendline calculations are not based on them
                                    for (int i = 0; (ListenerUtil.mutListener.listen(6709) ? (i >= scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6708) ? (i <= scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6707) ? (i > scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6706) ? (i != scaleMeasurementList.size()) : (ListenerUtil.mutListener.listen(6705) ? (i == scaleMeasurementList.size()) : (i < scaleMeasurementList.size())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter72", ++_loopCounter72);
                                        ScaleMeasurement measurement = scaleMeasurementList.get(i);
                                        float value = measurementView.getMeasurementValue(measurement);
                                        if (!ListenerUtil.mutListener.listen(6704)) {
                                            if ((ListenerUtil.mutListener.listen(6702) ? (value >= 0.0f) : (ListenerUtil.mutListener.listen(6701) ? (value <= 0.0f) : (ListenerUtil.mutListener.listen(6700) ? (value > 0.0f) : (ListenerUtil.mutListener.listen(6699) ? (value < 0.0f) : (ListenerUtil.mutListener.listen(6698) ? (value == 0.0f) : (value != 0.0f))))))) {
                                                if (!ListenerUtil.mutListener.listen(6703)) {
                                                    nonZeroScaleMeasurementList.add(measurement);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6711)) {
                                // check if we have some data left otherwise skip the measurement
                                if (nonZeroScaleMeasurementList.isEmpty()) {
                                    continue;
                                }
                            }
                            // calculate the trendline from the non-zero scale measurement list
                            List<ScaleMeasurement> scaleMeasurementsAsTrendlineList = getScaleMeasurementsAsTrendline(nonZeroScaleMeasurementList);
                            if (!ListenerUtil.mutListener.listen(6733)) {
                                {
                                    long _loopCounter73 = 0;
                                    for (int i = 0; (ListenerUtil.mutListener.listen(6732) ? (i >= scaleMeasurementsAsTrendlineList.size()) : (ListenerUtil.mutListener.listen(6731) ? (i <= scaleMeasurementsAsTrendlineList.size()) : (ListenerUtil.mutListener.listen(6730) ? (i > scaleMeasurementsAsTrendlineList.size()) : (ListenerUtil.mutListener.listen(6729) ? (i != scaleMeasurementsAsTrendlineList.size()) : (ListenerUtil.mutListener.listen(6728) ? (i == scaleMeasurementsAsTrendlineList.size()) : (i < scaleMeasurementsAsTrendlineList.size())))))); i++) {
                                        ListenerUtil.loopListener.listen("_loopCounter73", ++_loopCounter73);
                                        ScaleMeasurement measurement = scaleMeasurementsAsTrendlineList.get(i);
                                        float value = measurementView.getConvertedMeasurementValue(measurement);
                                        Entry entry = new Entry();
                                        if (!ListenerUtil.mutListener.listen(6712)) {
                                            entry.setX(convertDateToInt(measurement.getDateTime()));
                                        }
                                        if (!ListenerUtil.mutListener.listen(6713)) {
                                            entry.setY(value);
                                        }
                                        Object[] extraData = new Object[3];
                                        if (!ListenerUtil.mutListener.listen(6714)) {
                                            extraData[0] = measurement;
                                        }
                                        if (!ListenerUtil.mutListener.listen(6724)) {
                                            extraData[1] = ((ListenerUtil.mutListener.listen(6719) ? (i >= 0) : (ListenerUtil.mutListener.listen(6718) ? (i <= 0) : (ListenerUtil.mutListener.listen(6717) ? (i > 0) : (ListenerUtil.mutListener.listen(6716) ? (i < 0) : (ListenerUtil.mutListener.listen(6715) ? (i != 0) : (i == 0))))))) ? null : scaleMeasurementsAsTrendlineList.get((ListenerUtil.mutListener.listen(6723) ? (i % 1) : (ListenerUtil.mutListener.listen(6722) ? (i / 1) : (ListenerUtil.mutListener.listen(6721) ? (i * 1) : (ListenerUtil.mutListener.listen(6720) ? (i + 1) : (i - 1))))));
                                        }
                                        if (!ListenerUtil.mutListener.listen(6725)) {
                                            extraData[2] = measurementView;
                                        }
                                        if (!ListenerUtil.mutListener.listen(6726)) {
                                            entry.setData(extraData);
                                        }
                                        if (!ListenerUtil.mutListener.listen(6727)) {
                                            lineEntries.add(entry);
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(6734)) {
                                addMeasurementLineTrend(lineDataSets, lineEntries, measurementView);
                            }
                            if (!ListenerUtil.mutListener.listen(6735)) {
                                addPredictionLine(lineDataSets, lineEntries, measurementView);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addPredictionLine(List<ILineDataSet> lineDataSets, List<Entry> lineEntries, FloatMeasurementView measurementView) {
        if (!ListenerUtil.mutListener.listen(6743)) {
            if ((ListenerUtil.mutListener.listen(6742) ? (lineEntries.size() >= 2) : (ListenerUtil.mutListener.listen(6741) ? (lineEntries.size() <= 2) : (ListenerUtil.mutListener.listen(6740) ? (lineEntries.size() > 2) : (ListenerUtil.mutListener.listen(6739) ? (lineEntries.size() != 2) : (ListenerUtil.mutListener.listen(6738) ? (lineEntries.size() == 2) : (lineEntries.size() < 2))))))) {
                return;
            }
        }
        PolynomialFitter polyFitter = new PolynomialFitter(lineEntries.size() == 2 ? 2 : 3);
        // add last point to polynomial fitter first
        int lastPos = (ListenerUtil.mutListener.listen(6747) ? (lineEntries.size() % 1) : (ListenerUtil.mutListener.listen(6746) ? (lineEntries.size() / 1) : (ListenerUtil.mutListener.listen(6745) ? (lineEntries.size() * 1) : (ListenerUtil.mutListener.listen(6744) ? (lineEntries.size() + 1) : (lineEntries.size() - 1)))));
        Entry lastEntry = lineEntries.get(lastPos);
        if (!ListenerUtil.mutListener.listen(6748)) {
            polyFitter.addPoint((double) lastEntry.getX(), (double) lastEntry.getY());
        }
        if (!ListenerUtil.mutListener.listen(6770)) {
            {
                long _loopCounter75 = 0;
                // use only the last 30 values for the polynomial fitter
                for (int i = 2; (ListenerUtil.mutListener.listen(6769) ? (i >= 30) : (ListenerUtil.mutListener.listen(6768) ? (i <= 30) : (ListenerUtil.mutListener.listen(6767) ? (i > 30) : (ListenerUtil.mutListener.listen(6766) ? (i != 30) : (ListenerUtil.mutListener.listen(6765) ? (i == 30) : (i < 30)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter75", ++_loopCounter75);
                    int pos = (ListenerUtil.mutListener.listen(6752) ? (lineEntries.size() % i) : (ListenerUtil.mutListener.listen(6751) ? (lineEntries.size() / i) : (ListenerUtil.mutListener.listen(6750) ? (lineEntries.size() * i) : (ListenerUtil.mutListener.listen(6749) ? (lineEntries.size() + i) : (lineEntries.size() - i)))));
                    if (!ListenerUtil.mutListener.listen(6764)) {
                        if ((ListenerUtil.mutListener.listen(6757) ? (pos <= 0) : (ListenerUtil.mutListener.listen(6756) ? (pos > 0) : (ListenerUtil.mutListener.listen(6755) ? (pos < 0) : (ListenerUtil.mutListener.listen(6754) ? (pos != 0) : (ListenerUtil.mutListener.listen(6753) ? (pos == 0) : (pos >= 0))))))) {
                            Entry entry = lineEntries.get(pos);
                            Entry prevEntry = lineEntries.get((ListenerUtil.mutListener.listen(6761) ? (pos % 1) : (ListenerUtil.mutListener.listen(6760) ? (pos / 1) : (ListenerUtil.mutListener.listen(6759) ? (pos * 1) : (ListenerUtil.mutListener.listen(6758) ? (pos - 1) : (pos + 1))))));
                            if (!ListenerUtil.mutListener.listen(6763)) {
                                // check if x position is different otherwise that point is useless for the polynomial calculation.
                                if (entry.getX() != prevEntry.getX()) {
                                    if (!ListenerUtil.mutListener.listen(6762)) {
                                        polyFitter.addPoint((double) entry.getX(), (double) entry.getY());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        PolynomialFitter.Polynomial polynomial = polyFitter.getBestFit();
        int maxX = (ListenerUtil.mutListener.listen(6774) ? ((int) lastEntry.getX() % 1) : (ListenerUtil.mutListener.listen(6773) ? ((int) lastEntry.getX() / 1) : (ListenerUtil.mutListener.listen(6772) ? ((int) lastEntry.getX() * 1) : (ListenerUtil.mutListener.listen(6771) ? ((int) lastEntry.getX() - 1) : ((int) lastEntry.getX() + 1)))));
        List<Entry> predictionValues = new Stack<>();
        if (!ListenerUtil.mutListener.listen(6775)) {
            predictionValues.add(lastEntry);
        }
        if (!ListenerUtil.mutListener.listen(6786)) {
            {
                long _loopCounter76 = 0;
                // predict 30 days into the future
                for (int i = maxX; (ListenerUtil.mutListener.listen(6785) ? (i >= (ListenerUtil.mutListener.listen(6780) ? (maxX % 30) : (ListenerUtil.mutListener.listen(6779) ? (maxX / 30) : (ListenerUtil.mutListener.listen(6778) ? (maxX * 30) : (ListenerUtil.mutListener.listen(6777) ? (maxX - 30) : (maxX + 30)))))) : (ListenerUtil.mutListener.listen(6784) ? (i <= (ListenerUtil.mutListener.listen(6780) ? (maxX % 30) : (ListenerUtil.mutListener.listen(6779) ? (maxX / 30) : (ListenerUtil.mutListener.listen(6778) ? (maxX * 30) : (ListenerUtil.mutListener.listen(6777) ? (maxX - 30) : (maxX + 30)))))) : (ListenerUtil.mutListener.listen(6783) ? (i > (ListenerUtil.mutListener.listen(6780) ? (maxX % 30) : (ListenerUtil.mutListener.listen(6779) ? (maxX / 30) : (ListenerUtil.mutListener.listen(6778) ? (maxX * 30) : (ListenerUtil.mutListener.listen(6777) ? (maxX - 30) : (maxX + 30)))))) : (ListenerUtil.mutListener.listen(6782) ? (i != (ListenerUtil.mutListener.listen(6780) ? (maxX % 30) : (ListenerUtil.mutListener.listen(6779) ? (maxX / 30) : (ListenerUtil.mutListener.listen(6778) ? (maxX * 30) : (ListenerUtil.mutListener.listen(6777) ? (maxX - 30) : (maxX + 30)))))) : (ListenerUtil.mutListener.listen(6781) ? (i == (ListenerUtil.mutListener.listen(6780) ? (maxX % 30) : (ListenerUtil.mutListener.listen(6779) ? (maxX / 30) : (ListenerUtil.mutListener.listen(6778) ? (maxX * 30) : (ListenerUtil.mutListener.listen(6777) ? (maxX - 30) : (maxX + 30)))))) : (i < (ListenerUtil.mutListener.listen(6780) ? (maxX % 30) : (ListenerUtil.mutListener.listen(6779) ? (maxX / 30) : (ListenerUtil.mutListener.listen(6778) ? (maxX * 30) : (ListenerUtil.mutListener.listen(6777) ? (maxX - 30) : (maxX + 30))))))))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter76", ++_loopCounter76);
                    double yPredictionValue = polynomial.getY(i);
                    if (!ListenerUtil.mutListener.listen(6776)) {
                        predictionValues.add(new Entry((float) i, (float) yPredictionValue));
                    }
                }
            }
        }
        LineDataSet predictionLine = new LineDataSet(predictionValues, measurementView.getName().toString() + "-" + getContext().getString(R.string.label_prediction));
        if (!ListenerUtil.mutListener.listen(6787)) {
            predictionLine.setLineWidth(1.5f);
        }
        if (!ListenerUtil.mutListener.listen(6788)) {
            predictionLine.setColor(measurementView.getColor());
        }
        if (!ListenerUtil.mutListener.listen(6789)) {
            predictionLine.setAxisDependency(measurementView.getSettings().isOnRightAxis() ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT);
        }
        if (!ListenerUtil.mutListener.listen(6790)) {
            predictionLine.setDrawValues(false);
        }
        if (!ListenerUtil.mutListener.listen(6791)) {
            predictionLine.setDrawCircles(false);
        }
        if (!ListenerUtil.mutListener.listen(6792)) {
            predictionLine.setHighlightEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(6793)) {
            predictionLine.enableDashedLine(10, 30, 0);
        }
        if (!ListenerUtil.mutListener.listen(6799)) {
            if (measurementView.isVisible()) {
                if (!ListenerUtil.mutListener.listen(6798)) {
                    if (isInGraphKey) {
                        if (!ListenerUtil.mutListener.listen(6797)) {
                            if (measurementView.getSettings().isInGraph()) {
                                if (!ListenerUtil.mutListener.listen(6796)) {
                                    lineDataSets.add(predictionLine);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6795)) {
                            if (measurementView.getSettings().isInOverviewGraph()) {
                                if (!ListenerUtil.mutListener.listen(6794)) {
                                    lineDataSets.add(predictionLine);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addMeasurementLineTrend(List<ILineDataSet> lineDataSets, List<Entry> lineEntries, FloatMeasurementView measurementView) {
        LineDataSet measurementLine = new LineDataSet(lineEntries, measurementView.getName().toString() + "-" + getContext().getString(R.string.label_trend_line));
        if (!ListenerUtil.mutListener.listen(6800)) {
            measurementLine.setLineWidth(1.5f);
        }
        if (!ListenerUtil.mutListener.listen(6801)) {
            measurementLine.setValueTextSize(10.0f);
        }
        if (!ListenerUtil.mutListener.listen(6802)) {
            measurementLine.setColor(measurementView.getColor());
        }
        if (!ListenerUtil.mutListener.listen(6803)) {
            measurementLine.setValueTextColor(ColorUtil.getTintColor(getContext()));
        }
        if (!ListenerUtil.mutListener.listen(6804)) {
            measurementLine.setCircleColor(measurementView.getColor());
        }
        if (!ListenerUtil.mutListener.listen(6805)) {
            measurementLine.setCircleHoleColor(measurementView.getColor());
        }
        if (!ListenerUtil.mutListener.listen(6806)) {
            measurementLine.setAxisDependency(measurementView.getSettings().isOnRightAxis() ? YAxis.AxisDependency.RIGHT : YAxis.AxisDependency.LEFT);
        }
        if (!ListenerUtil.mutListener.listen(6807)) {
            measurementLine.setHighlightEnabled(true);
        }
        if (!ListenerUtil.mutListener.listen(6808)) {
            measurementLine.setDrawHighlightIndicators(true);
        }
        if (!ListenerUtil.mutListener.listen(6809)) {
            measurementLine.setHighlightLineWidth(1.5f);
        }
        if (!ListenerUtil.mutListener.listen(6810)) {
            measurementLine.setDrawHorizontalHighlightIndicator(false);
        }
        if (!ListenerUtil.mutListener.listen(6811)) {
            measurementLine.setHighLightColor(Color.RED);
        }
        if (!ListenerUtil.mutListener.listen(6812)) {
            // prefs.getBoolean("pointsEnable", true));
            measurementLine.setDrawCircles(false);
        }
        if (!ListenerUtil.mutListener.listen(6813)) {
            measurementLine.setDrawValues(prefs.getBoolean("labelsEnable", false));
        }
        if (!ListenerUtil.mutListener.listen(6819)) {
            if (measurementView.isVisible()) {
                if (!ListenerUtil.mutListener.listen(6818)) {
                    if (isInGraphKey) {
                        if (!ListenerUtil.mutListener.listen(6817)) {
                            if (measurementView.getSettings().isInGraph()) {
                                if (!ListenerUtil.mutListener.listen(6816)) {
                                    lineDataSets.add(measurementLine);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(6815)) {
                            if (measurementView.getSettings().isInOverviewGraph()) {
                                if (!ListenerUtil.mutListener.listen(6814)) {
                                    lineDataSets.add(measurementLine);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
