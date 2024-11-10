package org.wordpress.android.ui.stats.refresh.lists.widget.alltime;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J \u0010$\u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/alltime/AllTimeWidgetBlockListViewModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$WidgetBlockListViewModel;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "allTimeStore", "Lorg/wordpress/android/fluxc/store/stats/insights/AllTimeInsightsStore;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "allTimeWidgetUpdater", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/alltime/AllTimeWidgetUpdater;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/stats/insights/AllTimeInsightsStore;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/lists/widget/alltime/AllTimeWidgetUpdater;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;)V", "appWidgetId", "", "Ljava/lang/Integer;", "colorMode", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel$Color;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetBlockListProvider$BlockItemUiModel;", "getData", "()Ljava/util/List;", "mutableData", "", "siteId", "buildListItemUiModel", "domainModel", "Lorg/wordpress/android/fluxc/model/stats/InsightsAllTimeModel;", "localSiteId", "onDataSetChanged", "", "context", "Landroid/content/Context;", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AllTimeWidgetBlockListViewModel implements org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.WidgetBlockListViewModel {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.store.stats.insights.AllTimeInsightsStore allTimeStore = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetUpdater allTimeWidgetUpdater = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private java.lang.Integer siteId;
    private org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode = org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color.LIGHT;
    private java.lang.Integer appWidgetId;
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.BlockItemUiModel> mutableData = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.BlockItemUiModel> data = null;
    
    @javax.inject.Inject()
    public AllTimeWidgetBlockListViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.AllTimeInsightsStore allTimeStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetUpdater allTimeWidgetUpdater, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.BlockItemUiModel> getData() {
        return null;
    }
    
    @java.lang.Override()
    public void start(int siteId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel.Color colorMode, int appWidgetId) {
    }
    
    @java.lang.Override()
    public void onDataSetChanged(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.widget.WidgetBlockListProvider.BlockItemUiModel> buildListItemUiModel(org.wordpress.android.fluxc.model.stats.InsightsAllTimeModel domainModel, int localSiteId) {
        return null;
    }
}