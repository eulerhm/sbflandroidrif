package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Suppress(names = {"MagicNumber"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\"\u0010\u0018\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\"\u0010\u0019\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u001c\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/LineChartMarkerView;", "Lcom/github/mikephil/charting/components/MarkerView;", "context", "Landroid/content/Context;", "selectedType", "", "(Landroid/content/Context;I)V", "changeView", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "countView", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "getStatsUtils", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "setStatsUtils", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;)V", "draw", "", "canvas", "Landroid/graphics/Canvas;", "posX", "", "posY", "drawDataPoint", "drawToolTip", "getOffsetForDrawingAtPoint", "Lcom/github/mikephil/charting/utils/MPPointF;", "refreshContent", "e", "Lcom/github/mikephil/charting/data/Entry;", "highlight", "Lcom/github/mikephil/charting/highlight/Highlight;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class LineChartMarkerView extends com.github.mikephil.charting.components.MarkerView {
    private final int selectedType = 0;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils;
    private final android.widget.TextView changeView = null;
    private final android.widget.TextView countView = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.LineChartMarkerView.Companion Companion = null;
    public static final float CORNER_RADIUS = 10.0F;
    public static final float ARROW_SIZE = 40.0F;
    public static final float STROKE_WIDTH = 5.0F;
    public static final float CIRCLE_OFFSET = 14.0F;
    public static final float CIRCLE_RADIUS = 12.0F;
    public static final float CIRCLE_SHADOW_RADIUS = 14.0F;
    public static final float CIRCLE_STROKE_WIDTH = 4.0F;
    
    @javax.inject.Inject()
    public LineChartMarkerView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int selectedType) {
        super(null, 0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.utils.StatsUtils getStatsUtils() {
        return null;
    }
    
    public final void setStatsUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils p0) {
    }
    
    @java.lang.Override()
    public void refreshContent(@org.jetbrains.annotations.Nullable()
    com.github.mikephil.charting.data.Entry e, @org.jetbrains.annotations.Nullable()
    com.github.mikephil.charting.highlight.Highlight highlight) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.mikephil.charting.utils.MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        return null;
    }
    
    @java.lang.Override()
    public void draw(@org.jetbrains.annotations.Nullable()
    android.graphics.Canvas canvas, float posX, float posY) {
    }
    
    @kotlin.Suppress(names = {"LongMethod"})
    private final void drawToolTip(android.graphics.Canvas canvas, float posX, float posY) {
    }
    
    private final void drawDataPoint(android.graphics.Canvas canvas, float posX, float posY) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/LineChartMarkerView$Companion;", "", "()V", "ARROW_SIZE", "", "CIRCLE_OFFSET", "CIRCLE_RADIUS", "CIRCLE_SHADOW_RADIUS", "CIRCLE_STROKE_WIDTH", "CORNER_RADIUS", "STROKE_WIDTH", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}