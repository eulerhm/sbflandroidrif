package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0014J\u0018\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0014H\u0014J\"\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/BarChartAccessibilityHelper;", "Landroidx/customview/widget/ExploreByTouchHelper;", "barChart", "Lcom/github/mikephil/charting/charts/BarChart;", "contentDescriptions", "", "", "accessibilityEvent", "Lorg/wordpress/android/ui/stats/refresh/utils/BarChartAccessibilityHelper$BarChartAccessibilityEvent;", "(Lcom/github/mikephil/charting/charts/BarChart;Ljava/util/List;Lorg/wordpress/android/ui/stats/refresh/utils/BarChartAccessibilityHelper$BarChartAccessibilityEvent;)V", "dataSet", "Lcom/github/mikephil/charting/interfaces/datasets/IBarDataSet;", "getVirtualViewAt", "", "x", "", "y", "getVisibleVirtualViews", "", "virtualViewIds", "", "onPerformActionForVirtualView", "", "virtualViewId", "action", "arguments", "Landroid/os/Bundle;", "onPopulateNodeForVirtualView", "node", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "BarChartAccessibilityEvent", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BarChartAccessibilityHelper extends androidx.customview.widget.ExploreByTouchHelper {
    private final com.github.mikephil.charting.charts.BarChart barChart = null;
    private final java.util.List<java.lang.String> contentDescriptions = null;
    private final org.wordpress.android.ui.stats.refresh.utils.BarChartAccessibilityHelper.BarChartAccessibilityEvent accessibilityEvent = null;
    private final com.github.mikephil.charting.interfaces.datasets.IBarDataSet dataSet = null;
    
    public BarChartAccessibilityHelper(@org.jetbrains.annotations.NotNull()
    com.github.mikephil.charting.charts.BarChart barChart, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> contentDescriptions, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.BarChartAccessibilityHelper.BarChartAccessibilityEvent accessibilityEvent) {
        super(null);
    }
    
    @java.lang.Override()
    protected int getVirtualViewAt(float x, float y) {
        return 0;
    }
    
    @java.lang.Override()
    protected void getVisibleVirtualViews(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> virtualViewIds) {
    }
    
    @java.lang.Override()
    protected boolean onPerformActionForVirtualView(int virtualViewId, int action, @org.jetbrains.annotations.Nullable()
    android.os.Bundle arguments) {
        return false;
    }
    
    @java.lang.Override()
    protected void onPopulateNodeForVirtualView(int virtualViewId, @org.jetbrains.annotations.NotNull()
    androidx.core.view.accessibility.AccessibilityNodeInfoCompat node) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/BarChartAccessibilityHelper$BarChartAccessibilityEvent;", "", "onHighlight", "", "entry", "Lcom/github/mikephil/charting/data/BarEntry;", "index", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface BarChartAccessibilityEvent {
        
        public abstract void onHighlight(@org.jetbrains.annotations.NotNull()
        com.github.mikephil.charting.data.BarEntry entry, int index);
    }
}