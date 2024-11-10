package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001:\u00014BK\b\u0007\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J7\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0014J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\"\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016J3\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010(J+\u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020#2\u0006\u0010$\u001a\u00020%H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0003H\u0002J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0003H\u0002J\u001f\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0094@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0002R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/FollowerTotalsUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase;", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/FollowerTotalsUseCase$FollowerType;", "", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "followersStore", "Lorg/wordpress/android/fluxc/store/stats/insights/FollowersStore;", "publicizeStore", "Lorg/wordpress/android/fluxc/store/stats/insights/PublicizeStore;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/stats/insights/FollowersStore;Lorg/wordpress/android/fluxc/store/stats/insights/PublicizeStore;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;)V", "buildDataModel", "wpComTotals", "emailTotals", "socialTotals", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/util/Map;", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildLoadingItem", "buildTitle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Title;", "buildUiModel", "domainModel", "fetchData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "forced", "", "fetchMode", "Lorg/wordpress/android/fluxc/model/stats/PagedMode;", "(ZLorg/wordpress/android/fluxc/model/stats/PagedMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchRemoteData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIcon", "type", "getTitle", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onMenuClick", "", "view", "Landroid/view/View;", "FollowerType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FollowerTotalsUseCase extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase<java.util.Map<org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType, ? extends java.lang.Integer>> {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.store.stats.insights.FollowersStore followersStore = null;
    private final org.wordpress.android.fluxc.store.stats.insights.PublicizeStore publicizeStore = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler = null;
    
    @javax.inject.Inject()
    public FollowerTotalsUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.FollowersStore followersStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.insights.PublicizeStore publicizeStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper, @org.jetbrains.annotations.NotNull()
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
    kotlin.coroutines.Continuation<? super java.util.Map<org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType, java.lang.Integer>> continuation) {
        return null;
    }
    
    private final java.util.Map<org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType, java.lang.Integer> buildDataModel(java.lang.Integer wpComTotals, java.lang.Integer emailTotals, java.lang.Integer socialTotals) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<java.util.Map<org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType, java.lang.Integer>>> continuation) {
        return null;
    }
    
    private final java.lang.Object fetchData(boolean forced, org.wordpress.android.fluxc.model.stats.PagedMode fetchMode, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<java.util.Map<org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType, java.lang.Integer>>> continuation) {
        return null;
    }
    
    private final int getIcon(org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType type) {
        return 0;
    }
    
    private final int getTitle(org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType type) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(@org.jetbrains.annotations.NotNull()
    java.util.Map<org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase.FollowerType, java.lang.Integer> domainModel) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Title buildTitle() {
        return null;
    }
    
    private final void onMenuClick(android.view.View view) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/FollowerTotalsUseCase$FollowerType;", "", "(Ljava/lang/String;I)V", "WP_COM", "EMAIL", "SOCIAL", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum FollowerType {
        /*public static final*/ WP_COM /* = new WP_COM() */,
        /*public static final*/ EMAIL /* = new EMAIL() */,
        /*public static final*/ SOCIAL /* = new SOCIAL() */;
        
        FollowerType() {
        }
    }
}