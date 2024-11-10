package org.wordpress.android.widgets;

import java.lang.System;

/**
 * PieChartRenderer draws outer round to the end of the slice and inner round to the start of the slice.
 * PieChartRenderer#drawDataSet is copied here and edited for a feature which outer round can be drew both start and end
 * of slices.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/widgets/RoundedSlicesPieChartRenderer;", "Lcom/github/mikephil/charting/renderer/PieChartRenderer;", "chart", "Lcom/github/mikephil/charting/charts/PieChart;", "(Lcom/github/mikephil/charting/charts/PieChart;)V", "drawDataSet", "", "c", "Landroid/graphics/Canvas;", "dataSet", "Lcom/github/mikephil/charting/interfaces/datasets/IPieDataSet;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RoundedSlicesPieChartRenderer extends com.github.mikephil.charting.renderer.PieChartRenderer {
    
    public RoundedSlicesPieChartRenderer(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.charts.PieChart chart) {
        super(null, null, null);
    }
    
    @kotlin.Suppress(names = {"LongMethod", "ComplexMethod", "NestedBlockDepth", "MagicNumber"})
    @java.lang.Override()
    protected void drawDataSet(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas c, @org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.interfaces.datasets.IPieDataSet dataSet) {
    }
}