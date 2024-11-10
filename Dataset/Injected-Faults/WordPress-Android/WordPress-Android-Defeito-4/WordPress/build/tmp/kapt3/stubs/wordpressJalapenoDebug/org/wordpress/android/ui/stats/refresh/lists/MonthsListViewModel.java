package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/MonthsListViewModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "statsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "dateSelectorFactory", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector$Factory;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector$Factory;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MonthsListViewModel extends org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel {
    
    @javax.inject.Inject()
    public MonthsListViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "MonthStatsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase statsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector.Factory dateSelectorFactory) {
        super(null, null, null, null, null, null, null);
    }
}