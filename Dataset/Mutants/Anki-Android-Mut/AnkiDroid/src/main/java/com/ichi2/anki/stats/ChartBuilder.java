/**
 * *************************************************************************************
 *  Copyright (c) 2014 Michael Goldbach <michael@m-goldbach.net>                         *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.ichi2.anki.stats;

import android.graphics.Paint;
import com.ichi2.anki.R;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.stats.Stats;
import com.ichi2.themes.Themes;
import com.wildplot.android.rendering.BarGraph;
import com.wildplot.android.rendering.LegendDrawable;
import com.wildplot.android.rendering.Lines;
import com.wildplot.android.rendering.PieChart;
import com.wildplot.android.rendering.PlotSheet;
import com.wildplot.android.rendering.XAxis;
import com.wildplot.android.rendering.XGrid;
import com.wildplot.android.rendering.YAxis;
import com.wildplot.android.rendering.YGrid;
import com.wildplot.android.rendering.graphics.wrapper.ColorWrap;
import com.wildplot.android.rendering.graphics.wrapper.RectangleWrap;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ChartBuilder {

    private static final float BAR_OPACITY = 0.7f;

    private static final double STARTING_BAR_THICKNESS = 0.6;

    private static final double Y_AXIS_STRETCH_FACTOR = 1.05;

    private final Stats.ChartType mChartType;

    private final long mDeckId;

    private final ChartView mChartView;

    private final Collection mCollectionData;

    int mMaxCards = 0;

    private boolean mBackwards;

    private int[] mValueLabels;

    private int[] mColors;

    private int[] mAxisTitles;

    private double[][] mSeriesList;

    private double mLastElement = 0;

    private double[][] mCumulative = null;

    private double mFirstElement;

    private boolean mHasColoredCumulative;

    private double mMcount;

    private boolean mDynamicAxis;

    public ChartBuilder(ChartView chartView, Collection collectionData, long deckId, Stats.ChartType chartType) {
        mChartView = chartView;
        mCollectionData = collectionData;
        mDeckId = deckId;
        mChartType = chartType;
    }

    private void calcStats(Stats.AxisType type) {
        Stats stats = new Stats(mCollectionData, mDeckId);
        if (!ListenerUtil.mutListener.listen(3311)) {
            switch(mChartType) {
                case FORECAST:
                    if (!ListenerUtil.mutListener.listen(3303)) {
                        stats.calculateDue(mChartView.getContext(), type);
                    }
                    break;
                case REVIEW_COUNT:
                    if (!ListenerUtil.mutListener.listen(3304)) {
                        stats.calculateReviewCount(type);
                    }
                    break;
                case REVIEW_TIME:
                    if (!ListenerUtil.mutListener.listen(3305)) {
                        stats.calculateReviewTime(type);
                    }
                    break;
                case INTERVALS:
                    if (!ListenerUtil.mutListener.listen(3306)) {
                        stats.calculateIntervals(mChartView.getContext(), type);
                    }
                    break;
                case HOURLY_BREAKDOWN:
                    if (!ListenerUtil.mutListener.listen(3307)) {
                        stats.calculateBreakdown(type);
                    }
                    break;
                case WEEKLY_BREAKDOWN:
                    if (!ListenerUtil.mutListener.listen(3308)) {
                        stats.calculateWeeklyBreakdown(type);
                    }
                    break;
                case ANSWER_BUTTONS:
                    if (!ListenerUtil.mutListener.listen(3309)) {
                        stats.calculateAnswerButtons(type);
                    }
                    break;
                case CARDS_TYPES:
                    if (!ListenerUtil.mutListener.listen(3310)) {
                        stats.calculateCardTypes(type);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(3312)) {
            mCumulative = stats.getCumulative();
        }
        if (!ListenerUtil.mutListener.listen(3313)) {
            mSeriesList = stats.getSeriesList();
        }
        Object[] metaData = stats.getMetaInfo();
        if (!ListenerUtil.mutListener.listen(3314)) {
            mBackwards = (Boolean) metaData[2];
        }
        if (!ListenerUtil.mutListener.listen(3315)) {
            mValueLabels = (int[]) metaData[3];
        }
        if (!ListenerUtil.mutListener.listen(3316)) {
            mColors = (int[]) metaData[4];
        }
        if (!ListenerUtil.mutListener.listen(3317)) {
            mAxisTitles = (int[]) metaData[5];
        }
        if (!ListenerUtil.mutListener.listen(3318)) {
            mMaxCards = (Integer) metaData[7];
        }
        if (!ListenerUtil.mutListener.listen(3319)) {
            mLastElement = (Double) metaData[10];
        }
        if (!ListenerUtil.mutListener.listen(3320)) {
            mFirstElement = (Double) metaData[9];
        }
        if (!ListenerUtil.mutListener.listen(3321)) {
            mHasColoredCumulative = (Boolean) metaData[19];
        }
        if (!ListenerUtil.mutListener.listen(3322)) {
            mMcount = (Double) metaData[18];
        }
        if (!ListenerUtil.mutListener.listen(3323)) {
            mDynamicAxis = (Boolean) metaData[20];
        }
    }

    public PlotSheet renderChart(Stats.AxisType type) {
        if (!ListenerUtil.mutListener.listen(3324)) {
            calcStats(type);
        }
        Paint paint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        if (!ListenerUtil.mutListener.listen(3325)) {
            paint.setStyle(Paint.Style.STROKE);
        }
        int height = mChartView.getMeasuredHeight();
        int width = mChartView.getMeasuredWidth();
        if (!ListenerUtil.mutListener.listen(3326)) {
            Timber.d("height: %d, width: %d, %d", height, width, mChartView.getWidth());
        }
        if (!ListenerUtil.mutListener.listen(3338)) {
            if ((ListenerUtil.mutListener.listen(3337) ? ((ListenerUtil.mutListener.listen(3331) ? (height >= 0) : (ListenerUtil.mutListener.listen(3330) ? (height > 0) : (ListenerUtil.mutListener.listen(3329) ? (height < 0) : (ListenerUtil.mutListener.listen(3328) ? (height != 0) : (ListenerUtil.mutListener.listen(3327) ? (height == 0) : (height <= 0)))))) && (ListenerUtil.mutListener.listen(3336) ? (width >= 0) : (ListenerUtil.mutListener.listen(3335) ? (width > 0) : (ListenerUtil.mutListener.listen(3334) ? (width < 0) : (ListenerUtil.mutListener.listen(3333) ? (width != 0) : (ListenerUtil.mutListener.listen(3332) ? (width == 0) : (width <= 0))))))) : ((ListenerUtil.mutListener.listen(3331) ? (height >= 0) : (ListenerUtil.mutListener.listen(3330) ? (height > 0) : (ListenerUtil.mutListener.listen(3329) ? (height < 0) : (ListenerUtil.mutListener.listen(3328) ? (height != 0) : (ListenerUtil.mutListener.listen(3327) ? (height == 0) : (height <= 0)))))) || (ListenerUtil.mutListener.listen(3336) ? (width >= 0) : (ListenerUtil.mutListener.listen(3335) ? (width > 0) : (ListenerUtil.mutListener.listen(3334) ? (width < 0) : (ListenerUtil.mutListener.listen(3333) ? (width != 0) : (ListenerUtil.mutListener.listen(3332) ? (width == 0) : (width <= 0))))))))) {
                return null;
            }
        }
        RectangleWrap rect = new RectangleWrap(width, height);
        float textSize = (ListenerUtil.mutListener.listen(3342) ? (AnkiStatsTaskHandler.getInstance().getmStandardTextSize() % 0.85f) : (ListenerUtil.mutListener.listen(3341) ? (AnkiStatsTaskHandler.getInstance().getmStandardTextSize() / 0.85f) : (ListenerUtil.mutListener.listen(3340) ? (AnkiStatsTaskHandler.getInstance().getmStandardTextSize() - 0.85f) : (ListenerUtil.mutListener.listen(3339) ? (AnkiStatsTaskHandler.getInstance().getmStandardTextSize() + 0.85f) : (AnkiStatsTaskHandler.getInstance().getmStandardTextSize() * 0.85f)))));
        if (!ListenerUtil.mutListener.listen(3343)) {
            paint.setTextSize(textSize);
        }
        float FontHeight = paint.getTextSize();
        int desiredPixelDistanceBetweenTicks = Math.round((ListenerUtil.mutListener.listen(3347) ? (paint.measureText("100000") % 2.6f) : (ListenerUtil.mutListener.listen(3346) ? (paint.measureText("100000") / 2.6f) : (ListenerUtil.mutListener.listen(3345) ? (paint.measureText("100000") - 2.6f) : (ListenerUtil.mutListener.listen(3344) ? (paint.measureText("100000") + 2.6f) : (paint.measureText("100000") * 2.6f))))));
        int frameThickness = Math.round((ListenerUtil.mutListener.listen(3351) ? (FontHeight % 4.0f) : (ListenerUtil.mutListener.listen(3350) ? (FontHeight / 4.0f) : (ListenerUtil.mutListener.listen(3349) ? (FontHeight - 4.0f) : (ListenerUtil.mutListener.listen(3348) ? (FontHeight + 4.0f) : (FontHeight * 4.0f))))));
        PlotSheet plotSheet = new PlotSheet((ListenerUtil.mutListener.listen(3355) ? (mFirstElement % 0.5) : (ListenerUtil.mutListener.listen(3354) ? (mFirstElement / 0.5) : (ListenerUtil.mutListener.listen(3353) ? (mFirstElement * 0.5) : (ListenerUtil.mutListener.listen(3352) ? (mFirstElement + 0.5) : (mFirstElement - 0.5))))), (ListenerUtil.mutListener.listen(3359) ? (mLastElement % 0.5) : (ListenerUtil.mutListener.listen(3358) ? (mLastElement / 0.5) : (ListenerUtil.mutListener.listen(3357) ? (mLastElement * 0.5) : (ListenerUtil.mutListener.listen(3356) ? (mLastElement - 0.5) : (mLastElement + 0.5))))), 0, (ListenerUtil.mutListener.listen(3363) ? (mMaxCards % Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3362) ? (mMaxCards / Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3361) ? (mMaxCards - Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3360) ? (mMaxCards + Y_AXIS_STRETCH_FACTOR) : (mMaxCards * Y_AXIS_STRETCH_FACTOR))))));
        if (!ListenerUtil.mutListener.listen(3376)) {
            plotSheet.setFrameThickness((ListenerUtil.mutListener.listen(3367) ? (frameThickness % 0.66f) : (ListenerUtil.mutListener.listen(3366) ? (frameThickness / 0.66f) : (ListenerUtil.mutListener.listen(3365) ? (frameThickness - 0.66f) : (ListenerUtil.mutListener.listen(3364) ? (frameThickness + 0.66f) : (frameThickness * 0.66f))))), (ListenerUtil.mutListener.listen(3371) ? (frameThickness % 0.66f) : (ListenerUtil.mutListener.listen(3370) ? (frameThickness / 0.66f) : (ListenerUtil.mutListener.listen(3369) ? (frameThickness - 0.66f) : (ListenerUtil.mutListener.listen(3368) ? (frameThickness + 0.66f) : (frameThickness * 0.66f))))), frameThickness, (ListenerUtil.mutListener.listen(3375) ? (frameThickness % 0.9f) : (ListenerUtil.mutListener.listen(3374) ? (frameThickness / 0.9f) : (ListenerUtil.mutListener.listen(3373) ? (frameThickness - 0.9f) : (ListenerUtil.mutListener.listen(3372) ? (frameThickness + 0.9f) : (frameThickness * 0.9f))))));
        }
        if (!ListenerUtil.mutListener.listen(3377)) {
            plotSheet.setFontSize(textSize);
        }
        int backgroundColor = Themes.getColorFromAttr(mChartView.getContext(), android.R.attr.colorBackground);
        if (!ListenerUtil.mutListener.listen(3378)) {
            plotSheet.setBackgroundColor(new ColorWrap(backgroundColor));
        }
        int textColor = Themes.getColorFromAttr(mChartView.getContext(), android.R.attr.textColor);
        if (!ListenerUtil.mutListener.listen(3379)) {
            plotSheet.setTextColor(new ColorWrap(textColor));
        }
        if (!ListenerUtil.mutListener.listen(3380)) {
            plotSheet.setIsBackwards(mBackwards);
        }
        if (!ListenerUtil.mutListener.listen(3381)) {
            if (mChartType == Stats.ChartType.CARDS_TYPES) {
                return createPieChart(plotSheet);
            }
        }
        // for second y-axis
        PlotSheet hiddenPlotSheet = new PlotSheet((ListenerUtil.mutListener.listen(3385) ? (mFirstElement % 0.5) : (ListenerUtil.mutListener.listen(3384) ? (mFirstElement / 0.5) : (ListenerUtil.mutListener.listen(3383) ? (mFirstElement * 0.5) : (ListenerUtil.mutListener.listen(3382) ? (mFirstElement + 0.5) : (mFirstElement - 0.5))))), (ListenerUtil.mutListener.listen(3389) ? (mLastElement % 0.5) : (ListenerUtil.mutListener.listen(3388) ? (mLastElement / 0.5) : (ListenerUtil.mutListener.listen(3387) ? (mLastElement * 0.5) : (ListenerUtil.mutListener.listen(3386) ? (mLastElement - 0.5) : (mLastElement + 0.5))))), 0, (ListenerUtil.mutListener.listen(3393) ? (mMcount % Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3392) ? (mMcount / Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3391) ? (mMcount - Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3390) ? (mMcount + Y_AXIS_STRETCH_FACTOR) : (mMcount * Y_AXIS_STRETCH_FACTOR))))));
        if (!ListenerUtil.mutListener.listen(3406)) {
            hiddenPlotSheet.setFrameThickness((ListenerUtil.mutListener.listen(3397) ? (frameThickness % 0.66f) : (ListenerUtil.mutListener.listen(3396) ? (frameThickness / 0.66f) : (ListenerUtil.mutListener.listen(3395) ? (frameThickness - 0.66f) : (ListenerUtil.mutListener.listen(3394) ? (frameThickness + 0.66f) : (frameThickness * 0.66f))))), (ListenerUtil.mutListener.listen(3401) ? (frameThickness % 0.66f) : (ListenerUtil.mutListener.listen(3400) ? (frameThickness / 0.66f) : (ListenerUtil.mutListener.listen(3399) ? (frameThickness - 0.66f) : (ListenerUtil.mutListener.listen(3398) ? (frameThickness + 0.66f) : (frameThickness * 0.66f))))), frameThickness, (ListenerUtil.mutListener.listen(3405) ? (frameThickness % 0.9f) : (ListenerUtil.mutListener.listen(3404) ? (frameThickness / 0.9f) : (ListenerUtil.mutListener.listen(3403) ? (frameThickness - 0.9f) : (ListenerUtil.mutListener.listen(3402) ? (frameThickness + 0.9f) : (frameThickness * 0.9f))))));
        }
        if (!ListenerUtil.mutListener.listen(3407)) {
            setupCumulative(plotSheet, hiddenPlotSheet);
        }
        if (!ListenerUtil.mutListener.listen(3408)) {
            setupBarGraphs(plotSheet, hiddenPlotSheet);
        }
        double xTicks = ticksCalcX(desiredPixelDistanceBetweenTicks, rect, mFirstElement, mLastElement);
        if (!ListenerUtil.mutListener.listen(3409)) {
            setupXaxis(plotSheet, xTicks, true);
        }
        double yTicks = ticksCalcY(desiredPixelDistanceBetweenTicks, rect, 0, (ListenerUtil.mutListener.listen(3413) ? (mMaxCards % Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3412) ? (mMaxCards / Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3411) ? (mMaxCards - Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3410) ? (mMaxCards + Y_AXIS_STRETCH_FACTOR) : (mMaxCards * Y_AXIS_STRETCH_FACTOR))))));
        if (!ListenerUtil.mutListener.listen(3414)) {
            setupYaxis(plotSheet, hiddenPlotSheet, yTicks, mAxisTitles[1], false, true);
        }
        if (!ListenerUtil.mutListener.listen(3425)) {
            // 2 = Y-axis title right (optional)
            if ((ListenerUtil.mutListener.listen(3419) ? (mAxisTitles.length >= 3) : (ListenerUtil.mutListener.listen(3418) ? (mAxisTitles.length <= 3) : (ListenerUtil.mutListener.listen(3417) ? (mAxisTitles.length > 3) : (ListenerUtil.mutListener.listen(3416) ? (mAxisTitles.length < 3) : (ListenerUtil.mutListener.listen(3415) ? (mAxisTitles.length != 3) : (mAxisTitles.length == 3))))))) {
                double rightYtics = ticsCalc(desiredPixelDistanceBetweenTicks, rect, (ListenerUtil.mutListener.listen(3423) ? (mMcount % Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3422) ? (mMcount / Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3421) ? (mMcount - Y_AXIS_STRETCH_FACTOR) : (ListenerUtil.mutListener.listen(3420) ? (mMcount + Y_AXIS_STRETCH_FACTOR) : (mMcount * Y_AXIS_STRETCH_FACTOR))))));
                if (!ListenerUtil.mutListener.listen(3424)) {
                    setupYaxis(plotSheet, hiddenPlotSheet, rightYtics, mAxisTitles[2], true, true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3434)) {
            setupGrid(plotSheet, (ListenerUtil.mutListener.listen(3429) ? (yTicks % 0.5) : (ListenerUtil.mutListener.listen(3428) ? (yTicks / 0.5) : (ListenerUtil.mutListener.listen(3427) ? (yTicks - 0.5) : (ListenerUtil.mutListener.listen(3426) ? (yTicks + 0.5) : (yTicks * 0.5))))), (ListenerUtil.mutListener.listen(3433) ? (xTicks % 0.5) : (ListenerUtil.mutListener.listen(3432) ? (xTicks / 0.5) : (ListenerUtil.mutListener.listen(3431) ? (xTicks - 0.5) : (ListenerUtil.mutListener.listen(3430) ? (xTicks + 0.5) : (xTicks * 0.5))))));
        }
        return plotSheet;
    }

    private PlotSheet createPieChart(PlotSheet plotSheet) {
        ColorWrap[] colors = { new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[0])), new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[1])), new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[2])), new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[3])), new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[4])) };
        PieChart pieChart = new PieChart(plotSheet, mSeriesList[0], colors);
        if (!ListenerUtil.mutListener.listen(3435)) {
            pieChart.setName(mChartView.getResources().getString(mValueLabels[0]) + ": " + (int) mSeriesList[0][0]);
        }
        LegendDrawable legendDrawable1 = new LegendDrawable();
        LegendDrawable legendDrawable2 = new LegendDrawable();
        LegendDrawable legendDrawable3 = new LegendDrawable();
        LegendDrawable legendDrawable4 = new LegendDrawable();
        if (!ListenerUtil.mutListener.listen(3436)) {
            legendDrawable1.setColor(new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[1])));
        }
        if (!ListenerUtil.mutListener.listen(3437)) {
            legendDrawable2.setColor(new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[2])));
        }
        if (!ListenerUtil.mutListener.listen(3438)) {
            legendDrawable3.setColor(new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[3])));
        }
        if (!ListenerUtil.mutListener.listen(3439)) {
            legendDrawable4.setColor(new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[4])));
        }
        if (!ListenerUtil.mutListener.listen(3440)) {
            legendDrawable1.setName(mChartView.getResources().getString(mValueLabels[1]) + ": " + (int) mSeriesList[0][1]);
        }
        if (!ListenerUtil.mutListener.listen(3441)) {
            legendDrawable2.setName(mChartView.getResources().getString(mValueLabels[2]) + ": " + (int) mSeriesList[0][2]);
        }
        if (!ListenerUtil.mutListener.listen(3442)) {
            legendDrawable3.setName(mChartView.getResources().getString(mValueLabels[3]) + ": " + (int) mSeriesList[0][3]);
        }
        if (!ListenerUtil.mutListener.listen(3443)) {
            legendDrawable4.setName(mChartView.getResources().getString(mValueLabels[4]) + ": " + (int) mSeriesList[0][4]);
        }
        if (!ListenerUtil.mutListener.listen(3444)) {
            plotSheet.unsetBorder();
        }
        if (!ListenerUtil.mutListener.listen(3445)) {
            plotSheet.addDrawable(pieChart);
        }
        if (!ListenerUtil.mutListener.listen(3446)) {
            plotSheet.addDrawable(legendDrawable1);
        }
        if (!ListenerUtil.mutListener.listen(3447)) {
            plotSheet.addDrawable(legendDrawable2);
        }
        if (!ListenerUtil.mutListener.listen(3448)) {
            plotSheet.addDrawable(legendDrawable3);
        }
        if (!ListenerUtil.mutListener.listen(3449)) {
            plotSheet.addDrawable(legendDrawable4);
        }
        return plotSheet;
    }

    private void setupBarGraphs(PlotSheet plotSheet, PlotSheet hiddenPlotSheet) {
        int length = mSeriesList.length;
        if (!ListenerUtil.mutListener.listen(3452)) {
            if ((ListenerUtil.mutListener.listen(3450) ? (mChartType == Stats.ChartType.HOURLY_BREAKDOWN && mChartType == Stats.ChartType.WEEKLY_BREAKDOWN) : (mChartType == Stats.ChartType.HOURLY_BREAKDOWN || mChartType == Stats.ChartType.WEEKLY_BREAKDOWN))) {
                if (!ListenerUtil.mutListener.listen(3451)) {
                    // there is data in hourly breakdown that is never used (even in Anki-Desktop)
                    length--;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3500)) {
            {
                long _loopCounter85 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(3499) ? (i >= length) : (ListenerUtil.mutListener.listen(3498) ? (i <= length) : (ListenerUtil.mutListener.listen(3497) ? (i > length) : (ListenerUtil.mutListener.listen(3496) ? (i != length) : (ListenerUtil.mutListener.listen(3495) ? (i == length) : (i < length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter85", ++_loopCounter85);
                    double[][] bars = new double[2][];
                    if (!ListenerUtil.mutListener.listen(3453)) {
                        bars[0] = mSeriesList[0];
                    }
                    if (!ListenerUtil.mutListener.listen(3454)) {
                        bars[1] = mSeriesList[i];
                    }
                    PlotSheet usedPlotSheet = plotSheet;
                    double barThickness = STARTING_BAR_THICKNESS;
                    if (!ListenerUtil.mutListener.listen(3465)) {
                        if (((ListenerUtil.mutListener.listen(3455) ? (mChartType == Stats.ChartType.HOURLY_BREAKDOWN && mChartType == Stats.ChartType.WEEKLY_BREAKDOWN) : (mChartType == Stats.ChartType.HOURLY_BREAKDOWN || mChartType == Stats.ChartType.WEEKLY_BREAKDOWN)))) {
                            if (!ListenerUtil.mutListener.listen(3456)) {
                                barThickness = 0.8;
                            }
                            if (!ListenerUtil.mutListener.listen(3464)) {
                                if ((ListenerUtil.mutListener.listen(3461) ? (i >= 2) : (ListenerUtil.mutListener.listen(3460) ? (i <= 2) : (ListenerUtil.mutListener.listen(3459) ? (i > 2) : (ListenerUtil.mutListener.listen(3458) ? (i < 2) : (ListenerUtil.mutListener.listen(3457) ? (i != 2) : (i == 2))))))) {
                                    if (!ListenerUtil.mutListener.listen(3462)) {
                                        usedPlotSheet = hiddenPlotSheet;
                                    }
                                    if (!ListenerUtil.mutListener.listen(3463)) {
                                        barThickness = 0.2;
                                    }
                                }
                            }
                        }
                    }
                    ColorWrap color;
                    switch(mChartType) {
                        case ANSWER_BUTTONS:
                        case HOURLY_BREAKDOWN:
                        case WEEKLY_BREAKDOWN:
                        case INTERVALS:
                            color = new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[(ListenerUtil.mutListener.listen(3469) ? (i % 1) : (ListenerUtil.mutListener.listen(3468) ? (i / 1) : (ListenerUtil.mutListener.listen(3467) ? (i * 1) : (ListenerUtil.mutListener.listen(3466) ? (i + 1) : (i - 1)))))]), BAR_OPACITY);
                            break;
                        case REVIEW_COUNT:
                        case REVIEW_TIME:
                        case FORECAST:
                            if ((ListenerUtil.mutListener.listen(3474) ? (i >= 1) : (ListenerUtil.mutListener.listen(3473) ? (i <= 1) : (ListenerUtil.mutListener.listen(3472) ? (i > 1) : (ListenerUtil.mutListener.listen(3471) ? (i < 1) : (ListenerUtil.mutListener.listen(3470) ? (i != 1) : (i == 1))))))) {
                                color = new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[(ListenerUtil.mutListener.listen(3478) ? (i % 1) : (ListenerUtil.mutListener.listen(3477) ? (i / 1) : (ListenerUtil.mutListener.listen(3476) ? (i * 1) : (ListenerUtil.mutListener.listen(3475) ? (i + 1) : (i - 1)))))]), BAR_OPACITY);
                                break;
                            }
                            color = new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[(ListenerUtil.mutListener.listen(3482) ? (i % 1) : (ListenerUtil.mutListener.listen(3481) ? (i / 1) : (ListenerUtil.mutListener.listen(3480) ? (i * 1) : (ListenerUtil.mutListener.listen(3479) ? (i + 1) : (i - 1)))))]));
                            break;
                        default:
                            color = new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[(ListenerUtil.mutListener.listen(3486) ? (i % 1) : (ListenerUtil.mutListener.listen(3485) ? (i / 1) : (ListenerUtil.mutListener.listen(3484) ? (i * 1) : (ListenerUtil.mutListener.listen(3483) ? (i + 1) : (i - 1)))))]));
                    }
                    BarGraph barGraph = new BarGraph(usedPlotSheet, barThickness, bars, color);
                    if (!ListenerUtil.mutListener.listen(3487)) {
                        barGraph.setFilling(true);
                    }
                    if (!ListenerUtil.mutListener.listen(3492)) {
                        barGraph.setName(mChartView.getResources().getString(mValueLabels[(ListenerUtil.mutListener.listen(3491) ? (i % 1) : (ListenerUtil.mutListener.listen(3490) ? (i / 1) : (ListenerUtil.mutListener.listen(3489) ? (i * 1) : (ListenerUtil.mutListener.listen(3488) ? (i + 1) : (i - 1)))))]));
                    }
                    if (!ListenerUtil.mutListener.listen(3493)) {
                        // barGraph.setFillColor(Color.GREEN.darker());
                        barGraph.setFillColor(color);
                    }
                    if (!ListenerUtil.mutListener.listen(3494)) {
                        plotSheet.addDrawable(barGraph);
                    }
                }
            }
        }
    }

    private void setupCumulative(PlotSheet plotSheet, PlotSheet hiddenPlotSheet) {
        if (!ListenerUtil.mutListener.listen(3501)) {
            if (mCumulative == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(3520)) {
            {
                long _loopCounter86 = 0;
                for (int i = 1; (ListenerUtil.mutListener.listen(3519) ? (i >= mCumulative.length) : (ListenerUtil.mutListener.listen(3518) ? (i <= mCumulative.length) : (ListenerUtil.mutListener.listen(3517) ? (i > mCumulative.length) : (ListenerUtil.mutListener.listen(3516) ? (i != mCumulative.length) : (ListenerUtil.mutListener.listen(3515) ? (i == mCumulative.length) : (i < mCumulative.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter86", ++_loopCounter86);
                    double[][] cumulative = { mCumulative[0], mCumulative[i] };
                    ColorWrap usedColor = new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), R.attr.stats_cumulative));
                    String name = mChartView.getResources().getString(R.string.stats_cumulative);
                    if (!ListenerUtil.mutListener.listen(3509)) {
                        if (mHasColoredCumulative) {
                            if (!ListenerUtil.mutListener.listen(3508)) {
                                // also non colored Cumulatives have names!
                                usedColor = new ColorWrap(Themes.getColorFromAttr(mChartView.getContext(), mColors[(ListenerUtil.mutListener.listen(3507) ? (i % 1) : (ListenerUtil.mutListener.listen(3506) ? (i / 1) : (ListenerUtil.mutListener.listen(3505) ? (i * 1) : (ListenerUtil.mutListener.listen(3504) ? (i + 1) : (i - 1)))))]));
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(3503)) {
                                if (mChartType == Stats.ChartType.INTERVALS) {
                                    if (!ListenerUtil.mutListener.listen(3502)) {
                                        name = mChartView.getResources().getString(R.string.stats_cumulative_percentage);
                                    }
                                }
                            }
                        }
                    }
                    Lines lines = new Lines(hiddenPlotSheet, cumulative, usedColor);
                    if (!ListenerUtil.mutListener.listen(3510)) {
                        lines.setSize(3f);
                    }
                    if (!ListenerUtil.mutListener.listen(3511)) {
                        lines.setShadow(2f, 2f, ColorWrap.BLACK);
                    }
                    if (!ListenerUtil.mutListener.listen(3513)) {
                        if (!mHasColoredCumulative) {
                            if (!ListenerUtil.mutListener.listen(3512)) {
                                lines.setName(name);
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(3514)) {
                        plotSheet.addDrawable(lines);
                    }
                }
            }
        }
    }

    private void setupXaxis(PlotSheet plotSheet, double xTicks, boolean hasName) {
        XAxis xAxis = new XAxis(plotSheet, 0, xTicks, (ListenerUtil.mutListener.listen(3524) ? (xTicks % 2.0) : (ListenerUtil.mutListener.listen(3523) ? (xTicks * 2.0) : (ListenerUtil.mutListener.listen(3522) ? (xTicks - 2.0) : (ListenerUtil.mutListener.listen(3521) ? (xTicks + 2.0) : (xTicks / 2.0))))));
        if (!ListenerUtil.mutListener.listen(3525)) {
            xAxis.setOnFrame();
        }
        if (!ListenerUtil.mutListener.listen(3529)) {
            if (hasName) {
                if (!ListenerUtil.mutListener.listen(3528)) {
                    if (mDynamicAxis) {
                        if (!ListenerUtil.mutListener.listen(3527)) {
                            xAxis.setName(mChartView.getResources().getStringArray(R.array.due_x_axis_title)[mAxisTitles[0]]);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(3526)) {
                            xAxis.setName(mChartView.getResources().getString(mAxisTitles[0]));
                        }
                    }
                }
            }
        }
        double[] timePositions;
        // some explicit x-axis naming:
        switch(mChartType) {
            case ANSWER_BUTTONS:
                if ((ListenerUtil.mutListener.listen(3534) ? (mCollectionData.schedVer() >= 1) : (ListenerUtil.mutListener.listen(3533) ? (mCollectionData.schedVer() <= 1) : (ListenerUtil.mutListener.listen(3532) ? (mCollectionData.schedVer() > 1) : (ListenerUtil.mutListener.listen(3531) ? (mCollectionData.schedVer() < 1) : (ListenerUtil.mutListener.listen(3530) ? (mCollectionData.schedVer() != 1) : (mCollectionData.schedVer() == 1))))))) {
                    timePositions = new double[] { 1, 2, 3, 6, 7, 8, 9, 11, 12, 13, 14 };
                    if (!ListenerUtil.mutListener.listen(3536)) {
                        xAxis.setExplicitTicks(timePositions, mChartView.getResources().getStringArray(R.array.stats_eases_ticks));
                    }
                } else {
                    timePositions = new double[] { 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14 };
                    if (!ListenerUtil.mutListener.listen(3535)) {
                        xAxis.setExplicitTicks(timePositions, mChartView.getResources().getStringArray(R.array.stats_eases_ticks_schedv2));
                    }
                }
                break;
            case HOURLY_BREAKDOWN:
                timePositions = new double[] { 0, 6, 12, 18, 23 };
                if (!ListenerUtil.mutListener.listen(3537)) {
                    xAxis.setExplicitTicks(timePositions, mChartView.getResources().getStringArray(R.array.stats_day_time_strings));
                }
                break;
            case WEEKLY_BREAKDOWN:
                timePositions = new double[] { 0, 1, 2, 3, 4, 5, 6 };
                if (!ListenerUtil.mutListener.listen(3538)) {
                    xAxis.setExplicitTicks(timePositions, mChartView.getResources().getStringArray(R.array.stats_week_days));
                }
                break;
        }
        if (!ListenerUtil.mutListener.listen(3539)) {
            xAxis.setIntegerNumbering(true);
        }
        if (!ListenerUtil.mutListener.listen(3540)) {
            plotSheet.addDrawable(xAxis);
        }
    }

    private void setupYaxis(PlotSheet plotSheet, PlotSheet hiddenPlotSheet, double yTicks, int title, boolean isOnRight, boolean hasName) {
        YAxis yAxis;
        if ((ListenerUtil.mutListener.listen(3541) ? (isOnRight || hiddenPlotSheet != null) : (isOnRight && hiddenPlotSheet != null))) {
            yAxis = new YAxis(hiddenPlotSheet, 0, yTicks, (ListenerUtil.mutListener.listen(3549) ? (yTicks % 2.0) : (ListenerUtil.mutListener.listen(3548) ? (yTicks * 2.0) : (ListenerUtil.mutListener.listen(3547) ? (yTicks - 2.0) : (ListenerUtil.mutListener.listen(3546) ? (yTicks + 2.0) : (yTicks / 2.0))))));
        } else {
            yAxis = new YAxis(plotSheet, 0, yTicks, (ListenerUtil.mutListener.listen(3545) ? (yTicks % 2.0) : (ListenerUtil.mutListener.listen(3544) ? (yTicks * 2.0) : (ListenerUtil.mutListener.listen(3543) ? (yTicks - 2.0) : (ListenerUtil.mutListener.listen(3542) ? (yTicks + 2.0) : (yTicks / 2.0))))));
        }
        if (!ListenerUtil.mutListener.listen(3550)) {
            yAxis.setIntegerNumbering(true);
        }
        if (!ListenerUtil.mutListener.listen(3552)) {
            if (hasName) {
                if (!ListenerUtil.mutListener.listen(3551)) {
                    yAxis.setName(mChartView.getResources().getString(title));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3555)) {
            if (isOnRight) {
                if (!ListenerUtil.mutListener.listen(3554)) {
                    yAxis.setOnRightSideFrame();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(3553)) {
                    yAxis.setOnFrame();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3556)) {
            yAxis.setHasNumbersRotated();
        }
        if (!ListenerUtil.mutListener.listen(3557)) {
            plotSheet.addDrawable(yAxis);
        }
    }

    private void setupGrid(PlotSheet plotSheet, double yTicks, double xTicks) {
        int red = ColorWrap.LIGHT_GRAY.getRed();
        int green = ColorWrap.LIGHT_GRAY.getGreen();
        int blue = ColorWrap.LIGHT_GRAY.getBlue();
        ColorWrap newGridColor = new ColorWrap(red, green, blue, 222);
        // ticks are not wrong, xgrid is vertical to yaxis -> yticks
        XGrid xGrid = new XGrid(plotSheet, 0, yTicks);
        YGrid yGrid = new YGrid(plotSheet, 0, xTicks);
        double[] timePositions;
        // some explicit x-axis naming:
        switch(mChartType) {
            case ANSWER_BUTTONS:
                if ((ListenerUtil.mutListener.listen(3562) ? (mCollectionData.schedVer() >= 1) : (ListenerUtil.mutListener.listen(3561) ? (mCollectionData.schedVer() <= 1) : (ListenerUtil.mutListener.listen(3560) ? (mCollectionData.schedVer() > 1) : (ListenerUtil.mutListener.listen(3559) ? (mCollectionData.schedVer() < 1) : (ListenerUtil.mutListener.listen(3558) ? (mCollectionData.schedVer() != 1) : (mCollectionData.schedVer() == 1))))))) {
                    timePositions = new double[] { 1, 2, 3, 6, 7, 8, 9, 11, 12, 13, 14 };
                } else {
                    timePositions = new double[] { 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14 };
                }
                if (!ListenerUtil.mutListener.listen(3563)) {
                    yGrid.setExplicitTicks(timePositions);
                }
                break;
            case HOURLY_BREAKDOWN:
                timePositions = new double[] { 0, 6, 12, 18, 23 };
                if (!ListenerUtil.mutListener.listen(3564)) {
                    yGrid.setExplicitTicks(timePositions);
                }
                break;
            case WEEKLY_BREAKDOWN:
                timePositions = new double[] { 0, 1, 2, 3, 4, 5, 6 };
                if (!ListenerUtil.mutListener.listen(3565)) {
                    yGrid.setExplicitTicks(timePositions);
                }
                break;
        }
        if (!ListenerUtil.mutListener.listen(3566)) {
            xGrid.setColor(newGridColor);
        }
        if (!ListenerUtil.mutListener.listen(3567)) {
            yGrid.setColor(newGridColor);
        }
        if (!ListenerUtil.mutListener.listen(3568)) {
            plotSheet.addDrawable(xGrid);
        }
        if (!ListenerUtil.mutListener.listen(3569)) {
            plotSheet.addDrawable(yGrid);
        }
    }

    public double ticksCalcX(int pixelDistance, RectangleWrap field, double start, double end) {
        double deltaRange = (ListenerUtil.mutListener.listen(3573) ? (end % start) : (ListenerUtil.mutListener.listen(3572) ? (end / start) : (ListenerUtil.mutListener.listen(3571) ? (end * start) : (ListenerUtil.mutListener.listen(3570) ? (end + start) : (end - start)))));
        int ticlimit = (ListenerUtil.mutListener.listen(3577) ? (field.width % pixelDistance) : (ListenerUtil.mutListener.listen(3576) ? (field.width * pixelDistance) : (ListenerUtil.mutListener.listen(3575) ? (field.width - pixelDistance) : (ListenerUtil.mutListener.listen(3574) ? (field.width + pixelDistance) : (field.width / pixelDistance)))));
        double tics = Math.pow(10, (int) Math.log10((ListenerUtil.mutListener.listen(3581) ? (deltaRange % ticlimit) : (ListenerUtil.mutListener.listen(3580) ? (deltaRange * ticlimit) : (ListenerUtil.mutListener.listen(3579) ? (deltaRange - ticlimit) : (ListenerUtil.mutListener.listen(3578) ? (deltaRange + ticlimit) : (deltaRange / ticlimit)))))));
        if (!ListenerUtil.mutListener.listen(3596)) {
            {
                long _loopCounter87 = 0;
                while ((ListenerUtil.mutListener.listen(3595) ? ((ListenerUtil.mutListener.listen(3590) ? (2.0 % ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3589) ? (2.0 / ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3588) ? (2.0 - ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3587) ? (2.0 + ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (2.0 * ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))))))) >= ticlimit) : (ListenerUtil.mutListener.listen(3594) ? ((ListenerUtil.mutListener.listen(3590) ? (2.0 % ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3589) ? (2.0 / ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3588) ? (2.0 - ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3587) ? (2.0 + ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (2.0 * ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))))))) > ticlimit) : (ListenerUtil.mutListener.listen(3593) ? ((ListenerUtil.mutListener.listen(3590) ? (2.0 % ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3589) ? (2.0 / ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3588) ? (2.0 - ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3587) ? (2.0 + ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (2.0 * ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))))))) < ticlimit) : (ListenerUtil.mutListener.listen(3592) ? ((ListenerUtil.mutListener.listen(3590) ? (2.0 % ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3589) ? (2.0 / ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3588) ? (2.0 - ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3587) ? (2.0 + ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (2.0 * ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))))))) != ticlimit) : (ListenerUtil.mutListener.listen(3591) ? ((ListenerUtil.mutListener.listen(3590) ? (2.0 % ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3589) ? (2.0 / ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3588) ? (2.0 - ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3587) ? (2.0 + ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (2.0 * ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))))))) == ticlimit) : ((ListenerUtil.mutListener.listen(3590) ? (2.0 % ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3589) ? (2.0 / ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3588) ? (2.0 - ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (ListenerUtil.mutListener.listen(3587) ? (2.0 + ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))) : (2.0 * ((ListenerUtil.mutListener.listen(3586) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3585) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3584) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3583) ? (deltaRange + (tics)) : (deltaRange / (tics)))))))))))) <= ticlimit))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter87", ++_loopCounter87);
                    if (!ListenerUtil.mutListener.listen(3582)) {
                        tics /= 2.0;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(3611)) {
            {
                long _loopCounter88 = 0;
                while ((ListenerUtil.mutListener.listen(3610) ? ((ListenerUtil.mutListener.listen(3605) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) % 2) : (ListenerUtil.mutListener.listen(3604) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) * 2) : (ListenerUtil.mutListener.listen(3603) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) - 2) : (ListenerUtil.mutListener.listen(3602) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) + 2) : (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) / 2))))) <= ticlimit) : (ListenerUtil.mutListener.listen(3609) ? ((ListenerUtil.mutListener.listen(3605) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) % 2) : (ListenerUtil.mutListener.listen(3604) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) * 2) : (ListenerUtil.mutListener.listen(3603) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) - 2) : (ListenerUtil.mutListener.listen(3602) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) + 2) : (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) / 2))))) > ticlimit) : (ListenerUtil.mutListener.listen(3608) ? ((ListenerUtil.mutListener.listen(3605) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) % 2) : (ListenerUtil.mutListener.listen(3604) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) * 2) : (ListenerUtil.mutListener.listen(3603) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) - 2) : (ListenerUtil.mutListener.listen(3602) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) + 2) : (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) / 2))))) < ticlimit) : (ListenerUtil.mutListener.listen(3607) ? ((ListenerUtil.mutListener.listen(3605) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) % 2) : (ListenerUtil.mutListener.listen(3604) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) * 2) : (ListenerUtil.mutListener.listen(3603) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) - 2) : (ListenerUtil.mutListener.listen(3602) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) + 2) : (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) / 2))))) != ticlimit) : (ListenerUtil.mutListener.listen(3606) ? ((ListenerUtil.mutListener.listen(3605) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) % 2) : (ListenerUtil.mutListener.listen(3604) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) * 2) : (ListenerUtil.mutListener.listen(3603) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) - 2) : (ListenerUtil.mutListener.listen(3602) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) + 2) : (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) / 2))))) == ticlimit) : ((ListenerUtil.mutListener.listen(3605) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) % 2) : (ListenerUtil.mutListener.listen(3604) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) * 2) : (ListenerUtil.mutListener.listen(3603) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) - 2) : (ListenerUtil.mutListener.listen(3602) ? (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) + 2) : (((ListenerUtil.mutListener.listen(3601) ? (deltaRange % (tics)) : (ListenerUtil.mutListener.listen(3600) ? (deltaRange * (tics)) : (ListenerUtil.mutListener.listen(3599) ? (deltaRange - (tics)) : (ListenerUtil.mutListener.listen(3598) ? (deltaRange + (tics)) : (deltaRange / (tics))))))) / 2))))) >= ticlimit))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter88", ++_loopCounter88);
                    if (!ListenerUtil.mutListener.listen(3597)) {
                        tics *= 2.0;
                    }
                }
            }
        }
        return tics;
    }

    public double ticksCalcY(int pixelDistance, RectangleWrap field, double start, double end) {
        double size = ticsCalc(pixelDistance, field, (ListenerUtil.mutListener.listen(3615) ? (end % start) : (ListenerUtil.mutListener.listen(3614) ? (end / start) : (ListenerUtil.mutListener.listen(3613) ? (end * start) : (ListenerUtil.mutListener.listen(3612) ? (end + start) : (end - start))))));
        if (!ListenerUtil.mutListener.listen(3616)) {
            Timber.d("ChartBuilder ticksCalcY: pixelDistance: %d, ticks: %,.2f, start: %,.2f, end: %,.2f, height: %d", pixelDistance, size, start, end, field.height);
        }
        return size;
    }

    public double ticsCalc(int pixelDistance, RectangleWrap field, double deltaRange) {
        // Make approximation of number of ticks based on desired number of pixels per tick
        double numTicks = (ListenerUtil.mutListener.listen(3620) ? (field.height % pixelDistance) : (ListenerUtil.mutListener.listen(3619) ? (field.height * pixelDistance) : (ListenerUtil.mutListener.listen(3618) ? (field.height - pixelDistance) : (ListenerUtil.mutListener.listen(3617) ? (field.height + pixelDistance) : (field.height / pixelDistance)))));
        // Compute size of one tick in graph-units
        double delta = (ListenerUtil.mutListener.listen(3624) ? (deltaRange % numTicks) : (ListenerUtil.mutListener.listen(3623) ? (deltaRange * numTicks) : (ListenerUtil.mutListener.listen(3622) ? (deltaRange - numTicks) : (ListenerUtil.mutListener.listen(3621) ? (deltaRange + numTicks) : (deltaRange / numTicks)))));
        // Write size of one tick in the form norm * magn
        double dec = Math.floor((ListenerUtil.mutListener.listen(3628) ? (Math.log(delta) % Math.log(10)) : (ListenerUtil.mutListener.listen(3627) ? (Math.log(delta) * Math.log(10)) : (ListenerUtil.mutListener.listen(3626) ? (Math.log(delta) - Math.log(10)) : (ListenerUtil.mutListener.listen(3625) ? (Math.log(delta) + Math.log(10)) : (Math.log(delta) / Math.log(10)))))));
        double magn = Math.pow(10, dec);
        // norm is between 1.0 and 10.0
        double norm = (ListenerUtil.mutListener.listen(3632) ? (delta % magn) : (ListenerUtil.mutListener.listen(3631) ? (delta * magn) : (ListenerUtil.mutListener.listen(3630) ? (delta - magn) : (ListenerUtil.mutListener.listen(3629) ? (delta + magn) : (delta / magn)))));
        // Where size in (1, 2, 2.5, 5, 10)
        double size;
        if ((ListenerUtil.mutListener.listen(3637) ? (norm >= 1.5) : (ListenerUtil.mutListener.listen(3636) ? (norm <= 1.5) : (ListenerUtil.mutListener.listen(3635) ? (norm > 1.5) : (ListenerUtil.mutListener.listen(3634) ? (norm != 1.5) : (ListenerUtil.mutListener.listen(3633) ? (norm == 1.5) : (norm < 1.5))))))) {
            size = 1;
        } else if ((ListenerUtil.mutListener.listen(3642) ? (norm >= 3) : (ListenerUtil.mutListener.listen(3641) ? (norm <= 3) : (ListenerUtil.mutListener.listen(3640) ? (norm > 3) : (ListenerUtil.mutListener.listen(3639) ? (norm != 3) : (ListenerUtil.mutListener.listen(3638) ? (norm == 3) : (norm < 3))))))) {
            size = 2;
            // special case for 2.5, requires an extra decimal
            if ((ListenerUtil.mutListener.listen(3652) ? (norm >= 2.25) : (ListenerUtil.mutListener.listen(3651) ? (norm <= 2.25) : (ListenerUtil.mutListener.listen(3650) ? (norm < 2.25) : (ListenerUtil.mutListener.listen(3649) ? (norm != 2.25) : (ListenerUtil.mutListener.listen(3648) ? (norm == 2.25) : (norm > 2.25))))))) {
                size = 2.5;
            }
        } else if ((ListenerUtil.mutListener.listen(3647) ? (norm >= 7.5) : (ListenerUtil.mutListener.listen(3646) ? (norm <= 7.5) : (ListenerUtil.mutListener.listen(3645) ? (norm > 7.5) : (ListenerUtil.mutListener.listen(3644) ? (norm != 7.5) : (ListenerUtil.mutListener.listen(3643) ? (norm == 7.5) : (norm < 7.5))))))) {
            size = 5;
        } else {
            size = 10;
        }
        if (!ListenerUtil.mutListener.listen(3653)) {
            // Compute size * magn so that we return one number
            size *= magn;
        }
        if (!ListenerUtil.mutListener.listen(3654)) {
            Timber.d("ChartBuilder ticksCalc : pixelDistance: %d, ticks: %,.2f, deltaRange: %,.2f, height: %d", pixelDistance, size, deltaRange, field.height);
        }
        return size;
    }
}
