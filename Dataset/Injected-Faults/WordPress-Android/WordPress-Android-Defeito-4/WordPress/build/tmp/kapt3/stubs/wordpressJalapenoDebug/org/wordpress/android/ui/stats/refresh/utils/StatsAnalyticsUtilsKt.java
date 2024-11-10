package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b\u001a\u0012\u0010\u001c\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0012\u0010\u001d\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f\u001a\u001a\u0010 \u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"\u001a\u001a\u0010#\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%\u001a \u0010&\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020%0(\u001a\u001a\u0010)\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"ALL_TIME_WIDGET_PROPERTY", "", "ANNUAL_STATS_PROPERTY", "CHIP_VIEWS_PROPERTY", "CHIP_VISITORS__PROPERTY", "DAYS_PROPERTY", "DETAIL_PROPERTY", "GRANULARITY_PROPERTY", "INSIGHTS_PROPERTY", "MINIFIED_WIDGET_PROPERTY", "MONTHS_PROPERTY", "TODAY_WIDGET_PROPERTY", "TOTAL_COMMENTS_PROPERTY", "TOTAL_FOLLOWERS_PROPERTY", "TOTAL_LIKES_PROPERTY", "TYPE", "TYPES", "WEEKLY_VIEWS_WIDGET_PROPERTY", "WEEKS_PROPERTY", "WIDGET_TYPE", "YEARS_PROPERTY", "trackGranular", "", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "trackMinifiedWidget", "trackViewsVisitorsChips", "position", "", "trackWithSection", "section", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "trackWithType", "insightType", "Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;", "trackWithTypes", "insightTypes", "", "trackWithWidgetType", "widgetType", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsWidgetConfigureFragment$WidgetType;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsAnalyticsUtilsKt {
    private static final java.lang.String GRANULARITY_PROPERTY = "granularity";
    private static final java.lang.String DAYS_PROPERTY = "days";
    private static final java.lang.String WEEKS_PROPERTY = "weeks";
    private static final java.lang.String MONTHS_PROPERTY = "months";
    private static final java.lang.String YEARS_PROPERTY = "years";
    private static final java.lang.String INSIGHTS_PROPERTY = "insights";
    private static final java.lang.String DETAIL_PROPERTY = "detail";
    private static final java.lang.String ANNUAL_STATS_PROPERTY = "annual_stats";
    private static final java.lang.String TYPE = "type";
    private static final java.lang.String TYPES = "types";
    private static final java.lang.String WIDGET_TYPE = "widget_type";
    private static final java.lang.String TODAY_WIDGET_PROPERTY = "today";
    private static final java.lang.String WEEKLY_VIEWS_WIDGET_PROPERTY = "weekly_views";
    private static final java.lang.String ALL_TIME_WIDGET_PROPERTY = "all_time";
    private static final java.lang.String MINIFIED_WIDGET_PROPERTY = "minified";
    private static final java.lang.String TOTAL_LIKES_PROPERTY = "total_likes_detail";
    private static final java.lang.String TOTAL_COMMENTS_PROPERTY = "total_comments_detail";
    private static final java.lang.String TOTAL_FOLLOWERS_PROPERTY = "total_followers_detail";
    private static final java.lang.String CHIP_VIEWS_PROPERTY = "views";
    private static final java.lang.String CHIP_VISITORS__PROPERTY = "visitors";
    
    public static final void trackGranular(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper $this$trackGranular, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity granularity) {
    }
    
    public static final void trackViewsVisitorsChips(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper $this$trackViewsVisitorsChips, int position) {
    }
    
    public static final void trackWithSection(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper $this$trackWithSection, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection section) {
    }
    
    public static final void trackWithType(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper $this$trackWithType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore.InsightType insightType) {
    }
    
    public static final void trackWithTypes(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper $this$trackWithTypes, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends org.wordpress.android.fluxc.store.StatsStore.InsightType> insightTypes) {
    }
    
    public static final void trackWithWidgetType(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper $this$trackWithWidgetType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.WidgetType widgetType) {
    }
    
    public static final void trackMinifiedWidget(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper $this$trackMinifiedWidget, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
    }
}