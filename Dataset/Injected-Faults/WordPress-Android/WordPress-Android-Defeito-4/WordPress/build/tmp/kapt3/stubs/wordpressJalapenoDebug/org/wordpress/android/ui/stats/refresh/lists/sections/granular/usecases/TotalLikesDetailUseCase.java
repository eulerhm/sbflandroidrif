package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u00019Bc\b\u0007\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0014J9\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0 2!\u0010!\u001a\u001d\u0012\u0013\u0012\u00110#\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u001e0\"H\u0002J\u000e\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0\u001bH\u0014J\b\u0010)\u001a\u00020(H\u0002J\u001e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u0003H\u0014J/\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105J#\u00106\u001a\u0004\u0018\u00010\u00022\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00107J\u0010\u00108\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020#H\u0002R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006:"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/TotalLikesDetailUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularStatefulUseCase;", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ViewsAndVisitorsUseCase$UiState;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "statsGranularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "latestPostStore", "Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "totalStatsMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;)V", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildLatestPostGuideCard", "", "items", "", "navigationAction", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$LinkClickParams;", "Lkotlin/ParameterName;", "name", "params", "buildLoadingItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TitleWithMore;", "buildTitle", "buildUiModel", "domainModel", "uiState", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "selectedDate", "Ljava/util/Date;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "forced", "", "(Ljava/util/Date;Lorg/wordpress/android/fluxc/model/SiteModel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Ljava/util/Date;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLinkClicked", "TotalLikesGranularUseCaseFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class TotalLikesDetailUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularStatefulUseCase<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel, org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsUseCase.UiState> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
    private final org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters = null;
    
    @javax.inject.Inject()
    public TotalLikesDetailUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters) {
        super(null, null, null, null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.TitleWithMore> buildLoadingItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildEmptyItem() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    java.util.Date selectedDate, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object fetchRemoteData(@org.jetbrains.annotations.NotNull()
    java.util.Date selectedDate, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel domainModel, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsUseCase.UiState uiState) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.TitleWithMore buildTitle() {
        return null;
    }
    
    private final void buildLatestPostGuideCard(java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> items, kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.LinkClickParams, kotlin.Unit> navigationAction) {
    }
    
    private final void onLinkClicked(org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.LinkClickParams params) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B[\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/TotalLikesDetailUseCase$TotalLikesGranularUseCaseFactory;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularUseCaseFactory;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "latestPostStore", "Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "totalStatsMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/TotalLikesDetailUseCase;", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TotalLikesGranularUseCaseFactory implements org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory {
        private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
        private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
        private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
        private final org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
        private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
        private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper = null;
        private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
        private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters = null;
        
        @javax.inject.Inject()
        public TotalLikesGranularUseCaseFactory(@org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "UI_THREAD")
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.TotalLikesDetailUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
            return null;
        }
    }
}