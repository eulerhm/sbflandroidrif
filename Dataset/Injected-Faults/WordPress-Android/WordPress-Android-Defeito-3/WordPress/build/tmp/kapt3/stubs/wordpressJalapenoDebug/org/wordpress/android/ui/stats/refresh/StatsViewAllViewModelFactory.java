package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0014B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ%\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0012H\u0016\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewAllViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "useCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "dateSelector", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;", "titleResource", "", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;I)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Builder", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsViewAllViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?> useCase = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector dateSelector = null;
    private final int titleResource = 0;
    
    public StatsViewAllViewModelFactory(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?> useCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector dateSelector, @androidx.annotation.StringRes()
    int titleResource) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B]\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0013\b\u0001\u0010\u0005\u001a\r\u0012\t\u0012\u00070\u0007\u00a2\u0006\u0002\b\b0\u0006\u0012\u001b\b\u0001\u0010\t\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n\u00a2\u0006\u0002\b\b0\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J:\u0010\u0018\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J:\u0010\u001b\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0012\u001a\u00020\u00132\u0014\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n0\u0006H\u0002R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\r\u0012\t\u0012\u00070\u0007\u00a2\u0006\u0002\b\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R!\u0010\t\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n\u00a2\u0006\u0002\b\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewAllViewModelFactory$Builder;", "", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "granularFactories", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularUseCaseFactory;", "Lkotlin/jvm/JvmSuppressWildcards;", "insightsUseCases", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "dateSelectorFactory", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector$Factory;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Ljava/util/List;Ljava/util/List;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector$Factory;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/StatsViewAllViewModelFactory;", "type", "Lorg/wordpress/android/ui/stats/StatsViewType;", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "buildAnnualStatsFactory", "buildFactory", "getGranularUseCase", "Lkotlin/Pair;", "", "getInsightsUseCase", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Builder {
        private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
        private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> granularFactories = null;
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> insightsUseCases = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector.Factory dateSelectorFactory = null;
        
        @javax.inject.Inject()
        public Builder(@org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "UI_THREAD")
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "BG_THREAD")
        kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "GranularUseCaseFactories")
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> granularFactories, @org.jetbrains.annotations.NotNull()
        @javax.inject.Named(value = "ViewAllInsightsUseCases")
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> insightsUseCases, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector.Factory dateSelectorFactory) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.StatsViewAllViewModelFactory build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.StatsViewType type, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.fluxc.network.utils.StatsGranularity granularity) {
            return null;
        }
        
        private final org.wordpress.android.ui.stats.refresh.StatsViewAllViewModelFactory buildFactory(org.wordpress.android.ui.stats.StatsViewType type, org.wordpress.android.fluxc.network.utils.StatsGranularity granularity) {
            return null;
        }
        
        private final org.wordpress.android.ui.stats.refresh.StatsViewAllViewModelFactory buildFactory(org.wordpress.android.ui.stats.StatsViewType type) {
            return null;
        }
        
        private final org.wordpress.android.ui.stats.refresh.StatsViewAllViewModelFactory buildAnnualStatsFactory() {
            return null;
        }
        
        private final kotlin.Pair<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>, java.lang.Integer> getGranularUseCase(org.wordpress.android.ui.stats.StatsViewType type, org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> granularFactories) {
            return null;
        }
        
        private final kotlin.Pair<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>, java.lang.Integer> getInsightsUseCase(org.wordpress.android.ui.stats.StatsViewType type, java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> insightsUseCases) {
            return null;
        }
    }
}