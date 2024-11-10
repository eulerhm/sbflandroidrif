package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003<=>B[\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0014J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002H\u0002J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0014J\u0018\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0002H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u0002H\u0002J\b\u0010#\u001a\u00020$H\u0002J\u0012\u0010%\u001a\u00020&2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002H\u0002J\u0016\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010 \u001a\u00020\u0002H\u0016J\u001f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020)2\u0006\u0010*\u001a\u00020+H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010,J\u0013\u0010-\u001a\u0004\u0018\u00010\u0002H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J\b\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u0002002\u0006\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u0002002\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u0002002\u0006\u00102\u001a\u000208H\u0002J\u0010\u00109\u001a\u0002002\u0006\u00102\u001a\u00020:H\u0002J\f\u0010;\u001a\u00020+*\u00020\u0002H\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006?"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase;", "Lorg/wordpress/android/fluxc/model/stats/InsightsLatestPostModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "latestPostStore", "Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "latestPostSummaryMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryMapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "statsRevampV2Feature", "Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/insights/LatestPostInsightsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryMapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;)V", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildLink", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Link;", "model", "buildLoadingItem", "buildNullableUiModel", "", "domainModel", "buildQuickScanItems", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$QuickScanItem;", "buildTitle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "buildTitleViewMore", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$TitleWithMore;", "buildUiModel", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "forced", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAddNewPostClick", "", "onLinkClicked", "params", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$LinkClickParams;", "onMenuClick", "view", "Landroid/view/View;", "onSharePost", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$SharePostParams;", "onViewMore", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$ViewMoreParams;", "hasData", "LinkClickParams", "SharePostParams", "ViewMoreParams", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LatestPostSummaryUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase<org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryMapper latestPostSummaryMapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2Feature = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    
    @javax.inject.Inject()
    public LatestPostSummaryUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore latestPostStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryMapper latestPostSummaryMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2Feature, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel>> continuation) {
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
    org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel domainModel) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildNullableUiModel(org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel domainModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Title buildTitle() {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.TitleWithMore buildTitleViewMore(org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel model) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.QuickScanItem buildQuickScanItems(org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel domainModel) {
        return null;
    }
    
    private final boolean hasData(org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel $this$hasData) {
        return false;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Link buildLink(org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel model) {
        return null;
    }
    
    private final void onAddNewPostClick() {
    }
    
    private final void onViewMore(org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.ViewMoreParams params) {
    }
    
    private final void onSharePost(org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.SharePostParams params) {
    }
    
    private final void onLinkClicked(org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.LinkClickParams params) {
    }
    
    private final void onMenuClick(android.view.View view) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$LinkClickParams;", "", "postId", "", "postUrl", "", "(JLjava/lang/String;)V", "getPostId", "()J", "getPostUrl", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class LinkClickParams {
        private final long postId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String postUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.LinkClickParams copy(long postId, @org.jetbrains.annotations.NotNull()
        java.lang.String postUrl) {
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
        
        public LinkClickParams(long postId, @org.jetbrains.annotations.NotNull()
        java.lang.String postUrl) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPostUrl() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$SharePostParams;", "", "postUrl", "", "postTitle", "(Ljava/lang/String;Ljava/lang/String;)V", "getPostTitle", "()Ljava/lang/String;", "getPostUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SharePostParams {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String postUrl = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String postTitle = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.SharePostParams copy(@org.jetbrains.annotations.NotNull()
        java.lang.String postUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String postTitle) {
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
        
        public SharePostParams(@org.jetbrains.annotations.NotNull()
        java.lang.String postUrl, @org.jetbrains.annotations.NotNull()
        java.lang.String postTitle) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPostUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPostTitle() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$ViewMoreParams;", "", "postId", "", "postTitle", "", "postUrl", "(JLjava/lang/String;Ljava/lang/String;)V", "getPostId", "()J", "getPostTitle", "()Ljava/lang/String;", "getPostUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ViewMoreParams {
        private final long postId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String postTitle = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String postUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.ViewMoreParams copy(long postId, @org.jetbrains.annotations.NotNull()
        java.lang.String postTitle, @org.jetbrains.annotations.NotNull()
        java.lang.String postUrl) {
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
        
        public ViewMoreParams(long postId, @org.jetbrains.annotations.NotNull()
        java.lang.String postTitle, @org.jetbrains.annotations.NotNull()
        java.lang.String postUrl) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPostTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPostUrl() {
            return null;
        }
    }
}