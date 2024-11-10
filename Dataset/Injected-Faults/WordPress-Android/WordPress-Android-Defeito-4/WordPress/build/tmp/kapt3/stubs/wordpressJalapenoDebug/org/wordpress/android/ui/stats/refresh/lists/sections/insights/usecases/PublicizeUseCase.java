package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001)BK\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0014J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J\u001f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u0004\u0018\u00010\u0002H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\b\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020%2\u0006\u0010\'\u001a\u00020(H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/PublicizeUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase;", "Lorg/wordpress/android/fluxc/model/stats/PublicizeModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "publicizeStore", "Lorg/wordpress/android/fluxc/store/stats/insights/PublicizeStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "mapper", "Lorg/wordpress/android/ui/stats/refresh/utils/ServiceMapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/insights/PublicizeStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/ServiceMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;)V", "itemsToLoad", "", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildLoadingItem", "buildTitle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "buildUiModel", "domainModel", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "forced", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLinkClick", "", "onMenuClick", "view", "Landroid/view/View;", "PublicizeUseCaseFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PublicizeUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase<org.wordpress.android.fluxc.model.stats.PublicizeModel> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.insights.PublicizeStore publicizeStore = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ServiceMapper mapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode = null;
    private final int itemsToLoad = 0;
    
    @javax.inject.Inject()
    public PublicizeUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.PublicizeStore publicizeStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ServiceMapper mapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.stats.PublicizeModel> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<org.wordpress.android.fluxc.model.stats.PublicizeModel>> continuation) {
        return null;
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
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.PublicizeModel domainModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Title buildTitle() {
        return null;
    }
    
    private final void onLinkClick() {
    }
    
    private final void onMenuClick(android.view.View view) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/PublicizeUseCase$PublicizeUseCaseFactory;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/InsightUseCaseFactory;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "publicizeStore", "Lorg/wordpress/android/fluxc/store/stats/insights/PublicizeStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "mapper", "Lorg/wordpress/android/ui/stats/refresh/utils/ServiceMapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/insights/PublicizeStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/ServiceMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/PublicizeUseCase;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PublicizeUseCaseFactory implements org.wordpress.android.ui.stats.refresh.lists.sections.insights.InsightUseCaseFactory {
        private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
        private final org.wordpress.android.fluxc.store.stats.insights.PublicizeStore publicizeStore = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.ServiceMapper mapper = null;
        private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
        private final org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler = null;
        
        @javax.inject.Inject()
        public PublicizeUseCaseFactory(@org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "UI_THREAD")
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.insights.PublicizeStore publicizeStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.ServiceMapper mapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PublicizeUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
            return null;
        }
    }
}