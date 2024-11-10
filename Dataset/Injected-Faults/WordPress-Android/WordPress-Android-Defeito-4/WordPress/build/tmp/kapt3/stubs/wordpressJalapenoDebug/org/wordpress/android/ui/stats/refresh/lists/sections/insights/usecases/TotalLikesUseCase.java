package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00019Bc\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0014J9\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001f2!\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\"\u00a2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u001d0!H\u0002J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u001aH\u0014J\b\u0010(\u001a\u00020\'H\u0002J\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010*\u001a\u00020\u0002H\u0016J\u001f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00020,2\u0006\u0010-\u001a\u00020.H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0013\u00100\u001a\u0004\u0018\u00010\u0002H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J \u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020.H\u0002J\u0010\u00107\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\"H\u0002J\b\u00108\u001a\u00020\u001dH\u0002R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006:"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalLikesUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase;", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "latestPostStore", "Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "totalStatsMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildLatestPostGuideCard", "", "items", "", "navigationAction", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$LinkClickParams;", "Lkotlin/ParameterName;", "name", "params", "buildLoadingItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TitleWithMore;", "buildTitle", "buildUiModel", "domainModel", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "forced", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logIfIncorrectData", "model", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "fetched", "onLinkClicked", "onViewMoreClick", "TotalLikesUseCaseFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class TotalLikesUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
    private final org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    
    @javax.inject.Inject()
    public TotalLikesUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper) {
        super(null, null, null, null);
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
    protected java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel>> continuation) {
        return null;
    }
    
    /**
     * Track the incorrect data shown for some users
     * see https://github.com/wordpress-mobile/WordPress-Android/issues/11412
     */
    @kotlin.Suppress(names = {"MagicNumber"})
    private final void logIfIncorrectData(org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel model, org.wordpress.android.fluxc.model.SiteModel site, boolean fetched) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel domainModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.TitleWithMore buildTitle() {
        return null;
    }
    
    private final void buildLatestPostGuideCard(java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> items, kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.LinkClickParams, kotlin.Unit> navigationAction) {
    }
    
    private final void onLinkClicked(org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.LinkClickParams params) {
    }
    
    private final void onViewMoreClick() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bc\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalLikesUseCase$TotalLikesUseCaseFactory;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/InsightUseCaseFactory;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "latestPostStore", "Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "totalStatsMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalLikesUseCase;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TotalLikesUseCaseFactory implements org.wordpress.android.ui.stats.refresh.lists.sections.insights.InsightUseCaseFactory {
        private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
        private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
        private final org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
        private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
        private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper = null;
        private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
        private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters = null;
        private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
        
        @javax.inject.Inject()
        public TotalLikesUseCaseFactory(@org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "UI_THREAD")
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper totalStatsMapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalLikesUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
            return null;
        }
    }
}