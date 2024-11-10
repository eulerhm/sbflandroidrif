package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u000289Bi\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0014J\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0003H\u0014J\u001f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#2\u0006\u0010$\u001a\u00020%H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u0013\u0010\'\u001a\u0004\u0018\u00010\u0002H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010(J(\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020%H\u0002J\u0010\u00100\u001a\u00020*2\u0006\u00101\u001a\u000202H\u0002J\u0012\u00103\u001a\u00020*2\b\u00104\u001a\u0004\u0018\u000105H\u0002J\u0010\u00106\u001a\u00020*2\u0006\u00107\u001a\u000202H\u0002R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006:"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewUseCase$UiState;", "statsGranularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "overviewMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewMapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewMapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "buildLoadingItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildUiModel", "domainModel", "uiState", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "forced", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logIfIncorrectData", "", "model", "granularity", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "fetched", "onBarChartDrawn", "visibleBarCount", "", "onBarSelected", "period", "", "onColumnSelected", "position", "OverviewUseCaseFactory", "UiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class OverviewUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel, org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewUseCase.UiState> {
    private final org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity = null;
    private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewMapper overviewMapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    
    public OverviewUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewMapper overviewMapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super(null, null, null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildLoadingItem() {
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
    private final void logIfIncorrectData(org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel model, org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, org.wordpress.android.fluxc.model.SiteModel site, boolean fetched) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel domainModel, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewUseCase.UiState uiState) {
        return null;
    }
    
    private final void onBarSelected(java.lang.String period) {
    }
    
    private final void onColumnSelected(int position) {
    }
    
    private final void onBarChartDrawn(int visibleBarCount) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ$\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewUseCase$UiState;", "", "selectedPosition", "", "visibleBarCount", "(ILjava/lang/Integer;)V", "getSelectedPosition", "()I", "getVisibleBarCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(ILjava/lang/Integer;)Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewUseCase$UiState;", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UiState {
        private final int selectedPosition = 0;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer visibleBarCount = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewUseCase.UiState copy(int selectedPosition, @org.jetbrains.annotations.Nullable()
        java.lang.Integer visibleBarCount) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public UiState() {
            super();
        }
        
        public UiState(int selectedPosition, @org.jetbrains.annotations.Nullable()
        java.lang.Integer visibleBarCount) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getSelectedPosition() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getVisibleBarCount() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bc\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewUseCase$OverviewUseCaseFactory;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularUseCaseFactory;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "overviewMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewMapper;", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewMapper;Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewUseCase;", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class OverviewUseCaseFactory implements org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory {
        private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
        private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
        private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewMapper overviewMapper = null;
        private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
        private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
        private final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters = null;
        private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
        private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
        
        @javax.inject.Inject()
        public OverviewUseCaseFactory(@org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "UI_THREAD")
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewMapper overviewMapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
            return null;
        }
    }
}