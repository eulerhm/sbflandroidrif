package org.wordpress.android.ui.stats.refresh.lists.sections.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u0018\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J \u0010\u001e\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0002J*\u0010\"\u001a\b\u0012\u0004\u0012\u0002H#0\u0018\"\u0004\b\u0000\u0010#2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0\u0018H\u0002J\u0018\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u001cH\u0002J(\u0010)\u001a\u00060\u001cj\u0002`**\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001c\u0010+\u001a\u00020\u0010*\u00020\b2\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020-H\u0002J\f\u0010.\u001a\u00020\u0010*\u00020\bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \t*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \t*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/BarChartViewHolder;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/BlockListItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "accessibilityHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/BarChartAccessibilityHelper;", "chart", "Lcom/github/mikephil/charting/charts/BarChart;", "kotlin.jvm.PlatformType", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "labelEnd", "Landroid/widget/TextView;", "labelStart", "bind", "", "item", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem;", "buildDataSet", "Lcom/github/mikephil/charting/data/BarDataSet;", "context", "Landroid/content/Context;", "cut", "", "Lcom/github/mikephil/charting/data/BarEntry;", "buildEmptyDataSet", "count", "", "buildOverlappingDataSet", "getHighlightDataSet", "roundUp", "", "input", "takeEntriesWithinGraphWidth", "T", "entries", "toBarEntry", "bar", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem$Bar;", "index", "draw", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/BarCount;", "highlightColumn", "hasOverlappingColumns", "", "resetChart", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BarChartViewHolder extends org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.BlockListItemViewHolder {
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private final com.github.mikephil.charting.charts.BarChart chart = null;
    private final android.widget.TextView labelStart = null;
    private final android.widget.TextView labelEnd = null;
    private org.wordpress.android.ui.stats.refresh.utils.BarChartAccessibilityHelper accessibilityHelper;
    
    public BarChartViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        super(null, 0);
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem item) {
    }
    
    private final int draw(com.github.mikephil.charting.charts.BarChart $this$draw, org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem item, android.widget.TextView labelStart, android.widget.TextView labelEnd) {
        return 0;
    }
    
    private final void highlightColumn(com.github.mikephil.charting.charts.BarChart $this$highlightColumn, int index, boolean hasOverlappingColumns) {
    }
    
    private final com.github.mikephil.charting.data.BarDataSet buildEmptyDataSet(android.content.Context context, int count) {
        return null;
    }
    
    private final com.github.mikephil.charting.data.BarDataSet buildDataSet(android.content.Context context, java.util.List<? extends com.github.mikephil.charting.data.BarEntry> cut) {
        return null;
    }
    
    private final com.github.mikephil.charting.data.BarDataSet buildOverlappingDataSet(android.content.Context context, java.util.List<? extends com.github.mikephil.charting.data.BarEntry> cut) {
        return null;
    }
    
    private final com.github.mikephil.charting.data.BarDataSet getHighlightDataSet(android.content.Context context, java.util.List<? extends com.github.mikephil.charting.data.BarEntry> cut) {
        return null;
    }
    
    private final <T extends java.lang.Object>java.util.List<T> takeEntriesWithinGraphWidth(int count, java.util.List<? extends T> entries) {
        return null;
    }
    
    private final void resetChart(com.github.mikephil.charting.charts.BarChart $this$resetChart) {
    }
    
    private final com.github.mikephil.charting.data.BarEntry toBarEntry(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar bar, int index) {
        return null;
    }
    
    private final float roundUp(float input) {
        return 0.0F;
    }
}