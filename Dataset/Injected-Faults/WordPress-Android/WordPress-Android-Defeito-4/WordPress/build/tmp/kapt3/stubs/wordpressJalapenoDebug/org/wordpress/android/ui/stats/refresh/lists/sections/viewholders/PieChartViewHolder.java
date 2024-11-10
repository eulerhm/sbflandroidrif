package org.wordpress.android.ui.stats.refresh.lists.sections.viewholders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/PieChartViewHolder;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/BlockListItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "chart", "Lcom/github/mikephil/charting/charts/PieChart;", "kotlin.jvm.PlatformType", "legends", "Landroid/widget/LinearLayout;", "totalText", "Landroid/widget/TextView;", "addLegends", "", "item", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$PieChartItem;", "bind", "getDataSet", "Lcom/github/mikephil/charting/data/PieDataSet;", "mapToPieEntry", "", "Lcom/github/mikephil/charting/data/PieEntry;", "entries", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$PieChartItem$Pie;", "setHoleRadius", "sliceWidth", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PieChartViewHolder extends org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.BlockListItemViewHolder {
    private final com.github.mikephil.charting.charts.PieChart chart = null;
    private final android.widget.TextView totalText = null;
    private final android.widget.LinearLayout legends = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.lists.sections.viewholders.PieChartViewHolder.Companion Companion = null;
    private static final int PERCENT_HUNDRED = 100;
    private static final float MIN_PIE_CHART_RATIO = 0.039F;
    
    public PieChartViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        super(null, 0);
    }
    
    public final void bind(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem item) {
    }
    
    private final java.util.List<com.github.mikephil.charting.data.PieEntry> mapToPieEntry(java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem.Pie> entries) {
        return null;
    }
    
    private final void setHoleRadius(float sliceWidth) {
    }
    
    private final com.github.mikephil.charting.data.PieDataSet getDataSet(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem item) {
        return null;
    }
    
    private final void addLegends(org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem item) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/viewholders/PieChartViewHolder$Companion;", "", "()V", "MIN_PIE_CHART_RATIO", "", "PERCENT_HUNDRED", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}