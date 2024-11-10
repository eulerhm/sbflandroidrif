package org.wordpress.android.ui.stats.refresh.lists.widget.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\fH\u0002J$\u0010\u0018\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\fJ \u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010 \u001a\u00020\fJ(\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001b\u001a\u00020\fJJ\u0010\'\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aJF\u0010,\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020.2\u0006\u0010/\u001a\u00020\u001dR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/utils/WidgetUtils;", "", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/image/ImageManager;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "getLayout", "", "colorMode", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "getPendingSelfIntent", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "localSiteId", "statsTimeframe", "Lorg/wordpress/android/ui/stats/StatsTimeframe;", "getPendingTemplate", "getRandomId", "getRetryIntent", "widgetType", "Ljava/lang/Class;", "appWidgetId", "isWidgetWiderThanLimit", "", "appWidgetManager", "Landroid/appwidget/AppWidgetManager;", "minWidthLimit", "setSiteIcon", "", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "views", "Landroid/widget/RemoteViews;", "showError", "networkAvailable", "hasAccessToken", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "showList", "siteId", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsWidgetConfigureFragment$WidgetType;", "isWideView", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WidgetUtils {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.image.ImageManager imageManager = null;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    
    @javax.inject.Inject()
    public WidgetUtils(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager imageManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final boolean isWidgetWiderThanLimit(@org.jetbrains.annotations.NotNull()
    android.appwidget.AppWidgetManager appWidgetManager, int appWidgetId, int minWidthLimit) {
        return false;
    }
    
    public final int getLayout(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode) {
        return 0;
    }
    
    public final void setSiteIcon(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.widget.RemoteViews views, int appWidgetId) {
    }
    
    public final void showError(@org.jetbrains.annotations.NotNull()
    android.appwidget.AppWidgetManager appWidgetManager, @org.jetbrains.annotations.NotNull()
    android.widget.RemoteViews views, int appWidgetId, boolean networkAvailable, boolean hasAccessToken, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.Class<?> widgetType) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.app.PendingIntent getRetryIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.Class<?> widgetType, int appWidgetId) {
        return null;
    }
    
    public final void showList(@org.jetbrains.annotations.NotNull()
    android.appwidget.AppWidgetManager appWidgetManager, @org.jetbrains.annotations.NotNull()
    android.widget.RemoteViews views, @org.jetbrains.annotations.NotNull()
    android.content.Context context, int appWidgetId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode, int siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.WidgetType widgetType, boolean isWideView) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.PendingIntent getPendingSelfIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int localSiteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.StatsTimeframe statsTimeframe) {
        return null;
    }
    
    private final android.app.PendingIntent getPendingTemplate(android.content.Context context) {
        return null;
    }
    
    private final int getRandomId() {
        return 0;
    }
}