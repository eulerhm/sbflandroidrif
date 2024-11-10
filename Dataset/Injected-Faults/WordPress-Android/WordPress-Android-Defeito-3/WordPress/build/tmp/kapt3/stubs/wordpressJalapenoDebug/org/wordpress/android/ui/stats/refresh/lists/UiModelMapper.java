package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J(\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\rJ(\u0010\u000f\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\rJ2\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\rH\u0002J(\u0010\u0013\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\rJ(\u0010\u0014\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/UiModelMapper;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;)V", "getErrorMessage", "", "mapDetailStats", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "useCaseModels", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel;", "showError", "Lkotlin/Function1;", "", "mapInsights", "mapStatsWithOverview", "overViewType", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "mapTimeStats", "mapViewsVisitorsDetailStats", "org.wordpress.android_wordpressJalapenoDebug"})
public final class UiModelMapper {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    
    @javax.inject.Inject()
    public UiModelMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel mapInsights(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> useCaseModels, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> showError) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel mapTimeStats(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> useCaseModels, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> showError) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel mapDetailStats(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> useCaseModels, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> showError) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel mapViewsVisitorsDetailStats(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> useCaseModels, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> showError) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel mapStatsWithOverview(org.wordpress.android.fluxc.store.StatsStore.StatsType overViewType, java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> useCaseModels, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> showError) {
        return null;
    }
    
    private final int getErrorMessage() {
        return 0;
    }
}