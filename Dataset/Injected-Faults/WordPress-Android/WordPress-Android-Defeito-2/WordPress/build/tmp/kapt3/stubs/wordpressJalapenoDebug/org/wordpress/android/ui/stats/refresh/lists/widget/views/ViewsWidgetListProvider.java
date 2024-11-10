package org.wordpress.android.ui.stats.refresh.lists.widget.views;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001c\u001a\u00020\bH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\bH\u0016J\n\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020\bH\u0016J\b\u0010#\u001a\u00020\bH\u0016J\b\u0010$\u001a\u00020\u000eH\u0016J\b\u0010%\u001a\u00020&H\u0016J\b\u0010\'\u001a\u00020&H\u0016J\b\u0010(\u001a\u00020&H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetListProvider;", "Landroid/widget/RemoteViewsService$RemoteViewsFactory;", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "(Landroid/content/Context;Landroid/content/Intent;)V", "appWidgetId", "", "colorMode", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "getContext", "()Landroid/content/Context;", "isWideView", "", "siteId", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetListViewModel;", "getViewModel", "()Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetListViewModel;", "setViewModel", "(Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetListViewModel;)V", "viewsWidgetUpdater", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetUpdater;", "getViewsWidgetUpdater", "()Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetUpdater;", "setViewsWidgetUpdater", "(Lorg/wordpress/android/ui/stats/refresh/lists/widget/views/ViewsWidgetUpdater;)V", "getCount", "getItemId", "", "position", "getLoadingView", "Landroid/widget/RemoteViews;", "getViewAt", "getViewTypeCount", "hasStableIds", "onCreate", "", "onDataSetChanged", "onDestroy", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ViewsWidgetListProvider implements android.widget.RemoteViewsService.RemoteViewsFactory {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel viewModel;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetUpdater viewsWidgetUpdater;
    private final boolean isWideView = false;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode = null;
    private final int siteId = 0;
    private final int appWidgetId = 0;
    
    public ViewsWidgetListProvider(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel getViewModel() {
        return null;
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetListViewModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetUpdater getViewsWidgetUpdater() {
        return null;
    }
    
    public final void setViewsWidgetUpdater(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetUpdater p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.widget.RemoteViews getLoadingView() {
        return null;
    }
    
    @java.lang.Override()
    public void onDataSetChanged() {
    }
    
    @java.lang.Override()
    public boolean hasStableIds() {
        return false;
    }
    
    @java.lang.Override()
    public int getViewTypeCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public int getCount() {
        return 0;
    }
    
    @java.lang.Override()
    public long getItemId(int position) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.widget.RemoteViews getViewAt(int position) {
        return null;
    }
}