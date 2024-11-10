package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import java.lang.System;

@kotlin.Suppress(names = {"LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 J2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003JKLBq\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u00a2\u0006\u0002\u0010\u001dJ!\u0010!\u001a\u0004\u0018\u00010\u001f2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%H\u0002\u00a2\u0006\u0002\u0010&J\u000e\u0010\'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0014J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0002H\u0002J\u0010\u0010-\u001a\u00020.2\u0006\u0010$\u001a\u00020%H\u0002J\u001e\u0010/\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010,\u001a\u00020\u00022\u0006\u00100\u001a\u00020\u0003H\u0014J/\u00101\u001a\b\u0012\u0004\u0012\u00020\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020%H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J#\u00109\u001a\u0004\u0018\u00010\u00022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010:J\u0019\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020#H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010>J\u0010\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020#H\u0002J\'\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002\u00a2\u0006\u0002\u0010FJ\u0010\u0010G\u001a\u00020<2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u000e\u0010H\u001a\u00020<2\u0006\u0010@\u001a\u00020#J\u0019\u0010I\u001a\u00020<2\u0006\u0010=\u001a\u00020#H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010>R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006M"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ReferrersUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularStatefulUseCase;", "Lorg/wordpress/android/fluxc/model/stats/time/ReferrersModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ReferrersUseCase$SelectedGroup;", "statsGranularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "referrersStore", "Lorg/wordpress/android/fluxc/store/stats/time/ReferrersStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ReferrerPopupMenuHandler;", "statsRevampV2FeatureConfig", "Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;", "(Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/time/ReferrersStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;Lorg/wordpress/android/ui/stats/refresh/utils/ReferrerPopupMenuHandler;Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;)V", "itemsToLoad", "", "itemsToShow", "buildIcon", "iconUrl", "", "spam", "", "(Ljava/lang/String;Z)Ljava/lang/Integer;", "buildLoadingItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildPieChartItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$PieChartItem;", "domainModel", "buildTextStyle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon$TextStyle;", "buildUiModel", "uiState", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "selectedDate", "Ljava/util/Date;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "forced", "(Ljava/util/Date;Lorg/wordpress/android/fluxc/model/SiteModel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Ljava/util/Date;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markReferrerAsSpam", "", "urlDomain", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onItemClick", "url", "onMenuClick", "view", "Landroid/view/View;", "group", "Lorg/wordpress/android/fluxc/model/stats/time/ReferrersModel$Group;", "(Landroid/view/View;Lorg/wordpress/android/fluxc/model/stats/time/ReferrersModel$Group;Ljava/lang/Boolean;)Z", "onViewMoreClicked", "openWebsite", "unmarkReferrerAsSpam", "Companion", "ReferrersUseCaseFactory", "SelectedGroup", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReferrersUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularStatefulUseCase<org.wordpress.android.fluxc.model.stats.time.ReferrersModel, org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase.SelectedGroup> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.time.ReferrersStore referrersStore = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ReferrerPopupMenuHandler popupMenuHandler = null;
    private final org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig = null;
    private final int itemsToLoad = 0;
    private final int itemsToShow = 0;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase.Companion Companion = null;
    private static final java.util.List<java.lang.Integer> COLOR_LIST = null;
    private static final java.lang.String GROUP_ID_WORDPRESS = "WordPress.com Reader";
    private static final java.lang.String GROUP_ID_SEARCH = "Search Engines";
    
    public ReferrersUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.time.ReferrersStore referrersStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ReferrerPopupMenuHandler popupMenuHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig) {
        super(null, null, null, null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildLoadingItem() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    java.util.Date selectedDate, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.stats.time.ReferrersModel> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object fetchRemoteData(@org.jetbrains.annotations.NotNull()
    java.util.Date selectedDate, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<org.wordpress.android.fluxc.model.stats.time.ReferrersModel>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.time.ReferrersModel domainModel, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase.SelectedGroup uiState) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.PieChartItem buildPieChartItem(org.wordpress.android.fluxc.model.stats.time.ReferrersModel domainModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon.TextStyle buildTextStyle(boolean spam) {
        return null;
    }
    
    private final java.lang.Integer buildIcon(java.lang.String iconUrl, boolean spam) {
        return null;
    }
    
    private final void onViewMoreClicked(org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
    }
    
    private final void onItemClick(java.lang.String url) {
    }
    
    public final void openWebsite(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    private final boolean onMenuClick(android.view.View view, org.wordpress.android.fluxc.model.stats.time.ReferrersModel.Group group, java.lang.Boolean spam) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object markReferrerAsSpam(@org.jetbrains.annotations.NotNull()
    java.lang.String urlDomain, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object unmarkReferrerAsSpam(@org.jetbrains.annotations.NotNull()
    java.lang.String urlDomain, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ReferrersUseCase$SelectedGroup;", "", "groupId", "", "(Ljava/lang/String;)V", "getGroupId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SelectedGroup {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String groupId = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase.SelectedGroup copy(@org.jetbrains.annotations.Nullable()
        java.lang.String groupId) {
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
        
        public SelectedGroup() {
            super();
        }
        
        public SelectedGroup(@org.jetbrains.annotations.Nullable()
        java.lang.String groupId) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getGroupId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bc\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ReferrersUseCase$ReferrersUseCaseFactory;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularUseCaseFactory;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "referrersStore", "Lorg/wordpress/android/fluxc/store/stats/time/ReferrersStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ReferrerPopupMenuHandler;", "statsRevampV2FeatureConfig", "Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/time/ReferrersStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/ReferrerPopupMenuHandler;Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ReferrersUseCase;", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ReferrersUseCaseFactory implements org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory {
        private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
        private final org.wordpress.android.fluxc.store.stats.time.ReferrersStore referrersStore = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
        private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
        private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
        private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
        private final org.wordpress.android.ui.stats.refresh.utils.ReferrerPopupMenuHandler popupMenuHandler = null;
        private final org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig = null;
        
        @javax.inject.Inject()
        public ReferrersUseCaseFactory(@org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "UI_THREAD")
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.time.ReferrersStore referrersStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.ReferrerPopupMenuHandler popupMenuHandler, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ReferrersUseCase$Companion;", "", "()V", "COLOR_LIST", "", "", "GROUP_ID_SEARCH", "", "GROUP_ID_WORDPRESS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}