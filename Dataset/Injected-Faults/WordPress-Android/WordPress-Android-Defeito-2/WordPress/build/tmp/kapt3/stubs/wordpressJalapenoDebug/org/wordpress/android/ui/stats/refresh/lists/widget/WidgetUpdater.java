package org.wordpress.android.ui.stats.refresh.lists.widget;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\rJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J$\u0010\n\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH&\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater;", "", "componentName", "Landroid/content/ComponentName;", "context", "Landroid/content/Context;", "delete", "", "appWidgetId", "", "updateAppWidget", "appWidgetManager", "Landroid/appwidget/AppWidgetManager;", "StatsWidgetUpdaters", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface WidgetUpdater {
    
    public abstract void updateAppWidget(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int appWidgetId, @org.jetbrains.annotations.Nullable()
    android.appwidget.AppWidgetManager appWidgetManager);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.content.ComponentName componentName(@org.jetbrains.annotations.NotNull()
    android.content.Context context);
    
    public abstract void delete(int appWidgetId);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "", "viewsWidgetUpdater", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetUpdater;", "allTimeWidgetUpdater", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/alltime/AllTimeWidgetUpdater;", "todayWidgetUpdater", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/today/TodayWidgetUpdater;", "minifiedWidgetUpdater", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/MinifiedWidgetUpdater;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "context", "Landroid/content/Context;", "(Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetUpdater;Lorg/wordpress/android/ui/stats/refresh/lists/widget/alltime/AllTimeWidgetUpdater;Lorg/wordpress/android/ui/stats/refresh/lists/widget/today/TodayWidgetUpdater;Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/MinifiedWidgetUpdater;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Landroid/content/Context;)V", "widgetUpdaters", "", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater;", "update", "", "updateAllTimeWidget", "siteId", "", "updateTodayWidget", "updateViewsWidget", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class StatsWidgetUpdaters {
        private final org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetUpdater viewsWidgetUpdater = null;
        private final org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetUpdater allTimeWidgetUpdater = null;
        private final org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetUpdater todayWidgetUpdater = null;
        private final org.wordpress.android.ui.stats.refresh.lists.widget.minified.MinifiedWidgetUpdater minifiedWidgetUpdater = null;
        private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
        private final android.content.Context context = null;
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater> widgetUpdaters = null;
        
        @javax.inject.Inject()
        public StatsWidgetUpdaters(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetUpdater viewsWidgetUpdater, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetUpdater allTimeWidgetUpdater, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetUpdater todayWidgetUpdater, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.minified.MinifiedWidgetUpdater minifiedWidgetUpdater, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            super();
        }
        
        public final void update(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        public final void updateViewsWidget(long siteId) {
        }
        
        public final void updateTodayWidget(long siteId) {
        }
        
        public final void updateAllTimeWidget(long siteId) {
        }
        
        private final void update(org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater $this$update, long siteId) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
}