package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BC\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/InsightsListViewModel;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "insightsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "dateSelectorFactory", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector$Factory;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "newsCardHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/NewsCardHandler;", "actionCardHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ActionCardHandler;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector$Factory;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;Lorg/wordpress/android/ui/stats/refresh/utils/NewsCardHandler;Lorg/wordpress/android/ui/stats/refresh/utils/ActionCardHandler;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InsightsListViewModel extends org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel {
    private final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase insightsUseCase = null;
    
    @javax.inject.Inject()
    public InsightsListViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "InsightsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase insightsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector.Factory dateSelectorFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.NewsCardHandler newsCardHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ActionCardHandler actionCardHandler) {
        super(null, null, null, null, null, null, null);
    }
}