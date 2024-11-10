package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BK\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0014J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u001a\u001a\u00020\u0002H\u0016J\u001f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u0004\u0018\u00010\u0002H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\f\u0010&\u001a\u00020\u001e*\u00020\u0002H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/AllTimeStatsUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase;", "Lorg/wordpress/android/fluxc/model/stats/InsightsAllTimeModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "allTimeStore", "Lorg/wordpress/android/fluxc/store/stats/insights/AllTimeInsightsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/insights/AllTimeInsightsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;)V", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildLoadingItem", "buildTitle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "buildUiModel", "domainModel", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "forced", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onMenuClick", "", "view", "Landroid/view/View;", "hasData", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AllTimeStatsUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase<org.wordpress.android.fluxc.model.stats.InsightsAllTimeModel> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.insights.AllTimeInsightsStore allTimeStore = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler = null;
    
    @javax.inject.Inject()
    public AllTimeStatsUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.AllTimeInsightsStore allTimeStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildLoadingItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildEmptyItem() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.stats.InsightsAllTimeModel> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<org.wordpress.android.fluxc.model.stats.InsightsAllTimeModel>> continuation) {
        return null;
    }
    
    private final boolean hasData(org.wordpress.android.fluxc.model.stats.InsightsAllTimeModel $this$hasData) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.InsightsAllTimeModel domainModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Title buildTitle() {
        return null;
    }
    
    private final void onMenuClick(android.view.View view) {
    }
}