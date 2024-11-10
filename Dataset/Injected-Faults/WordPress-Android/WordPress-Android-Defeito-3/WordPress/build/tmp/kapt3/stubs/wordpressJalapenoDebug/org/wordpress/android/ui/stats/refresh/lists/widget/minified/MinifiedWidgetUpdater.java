package org.wordpress.android.ui.stats.refresh.lists.widget.minified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001BY\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J8\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J8\u0010,\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010&\u001a\u00020\'2\u0006\u0010$\u001a\u00020%2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\"\u0010-\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/MinifiedWidgetUpdater;", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater;", "defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "todayInsightsStore", "Lorg/wordpress/android/fluxc/store/stats/insights/TodayInsightsStore;", "widgetUtils", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/utils/WidgetUtils;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/fluxc/store/stats/insights/TodayInsightsStore;Lorg/wordpress/android/ui/stats/refresh/lists/widget/utils/WidgetUtils;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "componentName", "Landroid/content/ComponentName;", "context", "Landroid/content/Context;", "delete", "", "appWidgetId", "", "loadValue", "appWidgetManager", "Landroid/appwidget/AppWidgetManager;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "views", "Landroid/widget/RemoteViews;", "dataType", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsDataTypeSelectionViewModel$DataType;", "isWideView", "", "showValue", "updateAppWidget", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MinifiedWidgetUpdater implements org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater {
    private final kotlinx.coroutines.CoroutineDispatcher defaultDispatcher = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.fluxc.store.stats.insights.TodayInsightsStore todayInsightsStore = null;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.utils.WidgetUtils widgetUtils = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    
    @javax.inject.Inject()
    public MinifiedWidgetUpdater(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.TodayInsightsStore todayInsightsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.utils.WidgetUtils widgetUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    @java.lang.Override()
    public void updateAppWidget(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int appWidgetId, @org.jetbrains.annotations.Nullable()
    android.appwidget.AppWidgetManager appWidgetManager) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.content.ComponentName componentName(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    private final void showValue(android.appwidget.AppWidgetManager appWidgetManager, int appWidgetId, android.widget.RemoteViews views, org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsDataTypeSelectionViewModel.DataType dataType, boolean isWideView) {
    }
    
    private final void loadValue(android.appwidget.AppWidgetManager appWidgetManager, int appWidgetId, org.wordpress.android.fluxc.model.SiteModel site, android.widget.RemoteViews views, org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsDataTypeSelectionViewModel.DataType dataType, boolean isWideView) {
    }
    
    @java.lang.Override()
    public void delete(int appWidgetId) {
    }
}