package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u0006\u0010%\u001a\u000200J!\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105J.\u00101\u001a\u0002062\u001c\u00107\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020209\u0012\u0006\u0012\u0004\u0018\u00010:08H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;J\b\u0010<\u001a\u000202H\u0015J\u0006\u0010=\u001a\u000202J\u0006\u0010>\u001a\u000202J\u0006\u0010?\u001a\u000202J\b\u0010@\u001a\u000202H\u0007J\u0006\u0010A\u001a\u000202J\b\u0010B\u001a\u000202H\u0002J\u0010\u0010C\u001a\u0002022\b\u0010D\u001a\u0004\u0018\u000100R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00120\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100+\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0019\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006E"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewAllViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "useCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "dateSelector", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;", "title", "", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;I)V", "_isRefreshing", "Landroidx/lifecycle/MutableLiveData;", "", "_showSnackbarMessage", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "getBgDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "data", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "getData", "()Landroidx/lifecycle/LiveData;", "dateSelectorData", "Lorg/wordpress/android/ui/stats/refresh/StatsViewModel$DateSelectorUiModel;", "getDateSelectorData", "isRefreshing", "getMainDispatcher", "navigationTarget", "Lorg/wordpress/android/ui/stats/refresh/NavigationTarget;", "getNavigationTarget", "selectedDate", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SectionChange;", "getSelectedDate", "showSnackbarMessage", "getShowSnackbarMessage", "getTitle", "()I", "toolbarHasShadow", "Landroidx/lifecycle/MediatorLiveData;", "getToolbarHasShadow", "()Landroidx/lifecycle/MediatorLiveData;", "getUseCase", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;", "loadData", "", "refresh", "forced", "(ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/Job;", "executeLoading", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "onCleared", "onDateChanged", "onNextDateSelected", "onPreviousDateSelected", "onPullToRefresh", "onRetryClick", "refreshData", "start", "startDate", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsViewAllViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?> useCase = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector dateSelector = null;
    private final int title = 0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> selectedDate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel> dateSelectorData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> navigationTarget = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.StatsBlock> data = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isRefreshing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isRefreshing = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _showSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> showSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> toolbarHasShadow = null;
    
    public StatsViewAllViewModel(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?> useCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector dateSelector, @androidx.annotation.StringRes()
    int title) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getMainDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getBgDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?> getUseCase() {
        return null;
    }
    
    public final int getTitle() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> getSelectedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel> getDateSelectorData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> getNavigationTarget() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.StatsBlock> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isRefreshing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getShowSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<java.lang.Boolean> getToolbarHasShadow() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate startDate) {
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    public final void onPullToRefresh() {
    }
    
    private final kotlinx.coroutines.Job loadData(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> executeLoading) {
        return null;
    }
    
    private final java.lang.Object loadData(boolean refresh, boolean forced, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void onRetryClick() {
    }
    
    public final void onNextDateSelected() {
    }
    
    public final void onPreviousDateSelected() {
    }
    
    public final void onDateChanged() {
    }
    
    private final void refreshData() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate getSelectedDate() {
        return null;
    }
}