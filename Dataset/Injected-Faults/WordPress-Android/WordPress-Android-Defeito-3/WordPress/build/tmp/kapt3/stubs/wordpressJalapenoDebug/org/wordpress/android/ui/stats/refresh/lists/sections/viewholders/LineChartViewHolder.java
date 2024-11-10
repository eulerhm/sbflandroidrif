package org.wordpress.android.ui.stats.refresh.lists.sections.viewholders;

import java.lang.System;

@kotlin.Suppress(names = {"MagicNumber", "TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0015\u001a\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u0013H\u0002J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u0013H\u0002J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180 2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0 H\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&H\u0002J\"\u0010(\u001a\b\u0012\u0004\u0012\u0002H)0 \"\u0004\b\u0000\u0010)2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H)0 H\u0002J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u0013H\u0002J\u0014\u0010.\u001a\u00020\r*\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\f\u0010/\u001a\u00020\r*\u00020\bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/LineChartViewHolder;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/BlockListItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "accessibilityHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/LineChartAccessibilityHelper;", "chart", "Lcom/github/mikephil/charting/charts/LineChart;", "kotlin.jvm.PlatformType", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "bind", "", "item", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LineChartItem;", "buildEmptyDataSet", "Lcom/github/mikephil/charting/data/LineDataSet;", "count", "", "configureChartView", "configureDataSets", "dataSets", "", "Lcom/github/mikephil/charting/interfaces/datasets/ILineDataSet;", "selectedType", "configureXAxis", "configureYAxis", "drawChartMarker", "h", "Lcom/github/mikephil/charting/highlight/Highlight;", "getData", "", "hasData", "", "entries", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LineChartItem$Line;", "roundUp", "", "input", "takeEntriesWithinGraphWidth", "T", "toLineEntry", "Lcom/github/mikephil/charting/data/Entry;", "line", "index", "draw", "resetChart", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LineChartViewHolder extends org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.BlockListItemViewHolder {
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private final com.github.mikephil.charting.charts.LineChart chart = null;
    private org.wordpress.android.ui.stats.refresh.utils.LineChartAccessibilityHelper accessibilityHelper;
    
    public LineChartViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        super(null, 0);
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem item) {
    }
    
    private final void draw(com.github.mikephil.charting.charts.LineChart $this$draw, org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem item) {
    }
    
    private final java.util.List<com.github.mikephil.charting.interfaces.datasets.ILineDataSet> getData(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem item) {
        return null;
    }
    
    private final boolean hasData(java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line> entries) {
        return false;
    }
    
    private final void configureChartView(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem item) {
    }
    
    private final void configureYAxis(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem item) {
    }
    
    private final void configureXAxis(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem item) {
    }
    
    private final void drawChartMarker(com.github.mikephil.charting.highlight.Highlight h, int selectedType) {
    }
    
    private final void configureDataSets(java.util.List<com.github.mikephil.charting.interfaces.datasets.ILineDataSet> dataSets, int selectedType) {
    }
    
    private final com.github.mikephil.charting.data.LineDataSet buildEmptyDataSet(int count) {
        return null;
    }
    
    private final <T extends java.lang.Object>java.util.List<T> takeEntriesWithinGraphWidth(java.util.List<? extends T> entries) {
        return null;
    }
    
    private final void resetChart(com.github.mikephil.charting.charts.LineChart $this$resetChart) {
    }
    
    private final com.github.mikephil.charting.data.Entry toLineEntry(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line line, int index) {
        return null;
    }
    
    private final float roundUp(float input) {
        return 0.0F;
    }
}