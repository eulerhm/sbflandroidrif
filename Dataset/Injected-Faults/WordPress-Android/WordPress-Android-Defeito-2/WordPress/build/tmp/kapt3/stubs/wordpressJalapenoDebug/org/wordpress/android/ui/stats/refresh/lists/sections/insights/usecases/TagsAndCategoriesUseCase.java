package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002EFB[\b\u0007\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0002J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0014J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0 H\u0014J\b\u0010#\u001a\u00020$H\u0002J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010&\u001a\u00020\u00022\u0006\u0010\'\u001a\u00020\u0003H\u0014J\u001f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020)2\u0006\u0010*\u001a\u00020\u001bH\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020.H\u0002J\u0013\u0010/\u001a\u0004\u0018\u00010\u0002H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J0\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u00020\u00192\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0002J\u0018\u0010:\u001a\u0002022\u0006\u0010;\u001a\u00020<2\u0006\u00108\u001a\u000209H\u0002J0\u0010=\u001a\u0002022\u0006\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u00020\u00192\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0002J\b\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020?2\u0006\u0010D\u001a\u00020.H\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006G"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TagsAndCategoriesUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "Lorg/wordpress/android/fluxc/model/stats/TagsModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TagsAndCategoriesUseCase$TagsAndCategoriesUiState;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "tagsStore", "Lorg/wordpress/android/fluxc/store/stats/insights/TagsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/insights/TagsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;)V", "itemsToLoad", "", "areTagsEqual", "", "tagA", "Lorg/wordpress/android/fluxc/model/stats/TagsModel$TagModel;", "tagB", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildLoadingItem", "buildTitle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "buildUiModel", "domainModel", "uiState", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "forced", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIcon", "type", "", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapCategory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "tag", "index", "listSize", "maxViews", "", "header", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Header;", "mapItem", "item", "Lorg/wordpress/android/fluxc/model/stats/TagsModel$TagModel$Item;", "mapTag", "onLinkClick", "", "onMenuClick", "view", "Landroid/view/View;", "onTagClick", "link", "TagsAndCategoriesUiState", "TagsAndCategoriesUseCaseFactory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class TagsAndCategoriesUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<org.wordpress.android.fluxc.model.stats.TagsModel, org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase.TagsAndCategoriesUiState> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.insights.TagsStore tagsStore = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode = null;
    private final int itemsToLoad = 0;
    
    @javax.inject.Inject()
    public TagsAndCategoriesUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.TagsStore tagsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
        super(null, null, null, null, null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<org.wordpress.android.fluxc.model.stats.TagsModel>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.model.stats.TagsModel> continuation) {
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
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.TagsModel domainModel, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase.TagsAndCategoriesUiState uiState) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Title buildTitle() {
        return null;
    }
    
    private final boolean areTagsEqual(org.wordpress.android.fluxc.model.stats.TagsModel.TagModel tagA, org.wordpress.android.fluxc.model.stats.TagsModel.TagModel tagB) {
        return false;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon mapTag(org.wordpress.android.fluxc.model.stats.TagsModel.TagModel tag, int index, int listSize, long maxViews, org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon mapCategory(org.wordpress.android.fluxc.model.stats.TagsModel.TagModel tag, int index, int listSize, long maxViews, org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon mapItem(org.wordpress.android.fluxc.model.stats.TagsModel.TagModel.Item item, org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header) {
        return null;
    }
    
    private final int getIcon(java.lang.String type) {
        return 0;
    }
    
    private final void onLinkClick() {
    }
    
    private final void onTagClick(java.lang.String link) {
    }
    
    private final void onMenuClick(android.view.View view) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TagsAndCategoriesUseCase$TagsAndCategoriesUiState;", "", "expandedTag", "Lorg/wordpress/android/fluxc/model/stats/TagsModel$TagModel;", "(Lorg/wordpress/android/fluxc/model/stats/TagsModel$TagModel;)V", "getExpandedTag", "()Lorg/wordpress/android/fluxc/model/stats/TagsModel$TagModel;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TagsAndCategoriesUiState {
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.fluxc.model.stats.TagsModel.TagModel expandedTag = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase.TagsAndCategoriesUiState copy(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.stats.TagsModel.TagModel expandedTag) {
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
        
        public TagsAndCategoriesUiState() {
            super();
        }
        
        public TagsAndCategoriesUiState(@org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.model.stats.TagsModel.TagModel expandedTag) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.stats.TagsModel.TagModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.fluxc.model.stats.TagsModel.TagModel getExpandedTag() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BS\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TagsAndCategoriesUseCase$TagsAndCategoriesUseCaseFactory;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/InsightUseCaseFactory;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "tagsStore", "Lorg/wordpress/android/fluxc/store/stats/insights/TagsStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/insights/TagsStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TagsAndCategoriesUseCase;", "useCaseMode", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TagsAndCategoriesUseCaseFactory implements org.wordpress.android.ui.stats.refresh.lists.sections.insights.InsightUseCaseFactory {
        private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
        private final org.wordpress.android.fluxc.store.stats.insights.TagsStore tagsStore = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
        private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
        private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
        private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
        private final org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler = null;
        
        @javax.inject.Inject()
        public TagsAndCategoriesUseCaseFactory(@org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "UI_THREAD")
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.stats.insights.TagsStore tagsStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseMode useCaseMode) {
            return null;
        }
    }
}