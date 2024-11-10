package org.wordpress.android.ui.stats.refresh.lists.sections.granular;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0014J/\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u001f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0084@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u0004\u0018\u00018\u0000H\u0084@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J#\u0010\"\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularStatelessUseCase;", "DOMAIN_MODEL", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase;", "type", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsGranularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;)V", "getSelectedDateProvider", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "getStatsGranularity", "()Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "getStatsSiteProvider", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "buildEmptyItem", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "fetchRemoteData", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "selectedDate", "Ljava/util/Date;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "forced", "", "(Ljava/util/Date;Lorg/wordpress/android/fluxc/model/SiteModel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/util/Date;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class GranularStatelessUseCase<DOMAIN_MODEL extends java.lang.Object> extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase<DOMAIN_MODEL> {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity = null;
    
    public GranularStatelessUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore.StatsType type, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
        super(null, null, null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider getSelectedDateProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider getStatsSiteProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.network.utils.StatsGranularity getStatsGranularity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    java.util.Date selectedDate, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super DOMAIN_MODEL> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected final java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super DOMAIN_MODEL> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object fetchRemoteData(@org.jetbrains.annotations.NotNull()
    java.util.Date selectedDate, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected final java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildEmptyItem() {
        return null;
    }
}