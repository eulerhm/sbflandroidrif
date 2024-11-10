package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Suppress(names = {"TooManyFunctions", "LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00de\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0002cdB\u0091\u0001\b\u0007\u0012\u0014\b\u0001\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020 \u00a2\u0006\u0002\u0010!J\b\u0010<\u001a\u00020*H\u0002J\b\u0010=\u001a\u00020*H\u0002J\b\u0010>\u001a\u00020*H\u0002J\u0012\u0010?\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020AH\u0002J\b\u0010B\u001a\u00020$H\u0002J.\u0010C\u001a\u00020D2\u001c\u0010E\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020H0G\u0012\u0006\u0012\u0004\u0018\u00010I0FH\u0002\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010JJ\b\u0010K\u001a\u00020HH\u0015J\u0006\u0010L\u001a\u00020HJ\b\u0010M\u001a\u00020HH\u0007J\u0010\u0010N\u001a\u00020H2\b\u0010O\u001a\u0004\u0018\u00010PJ\u000e\u0010Q\u001a\u00020H2\u0006\u0010R\u001a\u00020PJ\u000e\u0010S\u001a\u00020H2\u0006\u0010T\u001a\u00020\u0004J\u0006\u0010U\u001a\u00020HJ\u0018\u0010V\u001a\u00020H2\u0006\u0010@\u001a\u00020A2\b\b\u0002\u0010W\u001a\u00020$J>\u0010V\u001a\u00020H2\u0006\u0010X\u001a\u00020Y2\b\u0010Z\u001a\u0004\u0018\u00010[2\b\u0010\\\u001a\u0004\u0018\u00010\u00042\b\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010W\u001a\u00020$2\b\u0010_\u001a\u0004\u0018\u00010`J\u0010\u0010a\u001a\u00020H2\u0006\u0010T\u001a\u00020\u0004H\u0002J\b\u0010b\u001a\u00020HH\u0002R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0)0,\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u000e\u0010/\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020$0,\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u00040,\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010.R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\'0,\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010.R\u001d\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002060)0,\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010.R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0,\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010.R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020$0,\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006e"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "listUseCases", "", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "defaultDispatcher", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "statsSectionManager", "Lorg/wordpress/android/ui/stats/refresh/utils/SelectedSectionManager;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsStore", "Lorg/wordpress/android/fluxc/store/StatsStore;", "newsCardHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/NewsCardHandler;", "statsModuleActivateUseCase", "Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateUseCase;", "notificationsTracker", "Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "todaysStatsCardFeatureConfig", "Lorg/wordpress/android/util/config/MySiteDashboardTodaysStatsCardFeatureConfig;", "statsRevampV2FeatureConfig", "Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;", "(Ljava/util/Map;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/stats/refresh/utils/SelectedSectionManager;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/fluxc/store/StatsStore;Lorg/wordpress/android/ui/stats/refresh/utils/NewsCardHandler;Lorg/wordpress/android/ui/stats/refresh/StatsModuleActivateUseCase;Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;Lorg/wordpress/android/util/config/MySiteDashboardTodaysStatsCardFeatureConfig;Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;)V", "_isRefreshing", "Landroidx/lifecycle/MutableLiveData;", "", "_showSnackbarMessage", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_statsModuleUiModel", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/stats/refresh/StatsViewModel$StatsModuleUiModel;", "hideToolbar", "Landroidx/lifecycle/LiveData;", "getHideToolbar", "()Landroidx/lifecycle/LiveData;", "isInitialized", "isRefreshing", "selectedSection", "getSelectedSection", "showSnackbarMessage", "getShowSnackbarMessage", "siteChanged", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider$SiteUpdateResult;", "getSiteChanged", "statsModuleUiModel", "getStatsModuleUiModel", "toolbarHasShadow", "getToolbarHasShadow", "buildShowStatsActivatingViewUiModel", "buildShowStatsDisabledViewUiModel", "buildShowStatsEnabledViewUiModel", "getInitialTimeFrame", "intent", "Landroid/content/Intent;", "isStatsModuleEnabled", "loadData", "Lkotlinx/coroutines/Job;", "executeLoading", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Job;", "onCleared", "onEnableStatsModuleClick", "onPullToRefresh", "onRestoreInstanceState", "savedState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onSectionSelected", "statsSection", "onSiteChanged", "start", "restart", "localSiteId", "", "launchedFrom", "Ljava/io/Serializable;", "initialSection", "initialSelectedPeriod", "", "notificationType", "Lorg/wordpress/android/push/NotificationType;", "trackSectionSelected", "updateRevampedInsights", "DateSelectorUiModel", "StatsModuleUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final java.util.Map<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection, org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase> listUseCases = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher defaultDispatcher = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.SelectedSectionManager statsSectionManager = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final org.wordpress.android.fluxc.store.StatsStore statsStore = null;
    private final org.wordpress.android.ui.stats.refresh.StatsModuleActivateUseCase statsModuleActivateUseCase = null;
    private final org.wordpress.android.ui.notifications.SystemNotificationsTracker notificationsTracker = null;
    private final org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig todaysStatsCardFeatureConfig = null;
    private final org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isRefreshing = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isRefreshing = null;
    private boolean isInitialized = false;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> _showSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> showSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult>> siteChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> toolbarHasShadow = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> hideToolbar = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection> selectedSection = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel>> _statsModuleUiModel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel>> statsModuleUiModel = null;
    
    @javax.inject.Inject()
    public StatsViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "ListStatsUseCases")
    java.util.Map<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection, org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase> listUseCases, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.SelectedSectionManager statsSectionManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.NewsCardHandler newsCardHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.StatsModuleActivateUseCase statsModuleActivateUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.SystemNotificationsTracker notificationsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig todaysStatsCardFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isRefreshing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> getShowSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider.SiteUpdateResult>> getSiteChanged() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getToolbarHasShadow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Boolean>> getHideToolbar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection> getSelectedSection() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel>> getStatsModuleUiModel() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent, boolean restart) {
    }
    
    public final void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void onRestoreInstanceState(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedState) {
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection getInitialTimeFrame(android.content.Intent intent) {
        return null;
    }
    
    @kotlin.Suppress(names = {"ComplexMethod"})
    public final void start(int localSiteId, @org.jetbrains.annotations.Nullable()
    java.io.Serializable launchedFrom, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection initialSection, @org.jetbrains.annotations.Nullable()
    java.lang.String initialSelectedPeriod, boolean restart, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.push.NotificationType notificationType) {
    }
    
    private final void updateRevampedInsights() {
    }
    
    private final boolean isStatsModuleEnabled() {
        return false;
    }
    
    private final kotlinx.coroutines.Job loadData(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> executeLoading) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    public final void onPullToRefresh() {
    }
    
    public final void onSiteChanged() {
    }
    
    public final void onSectionSelected(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    private final void trackSectionSelected(org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void onEnableStatsModuleClick() {
    }
    
    private final org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel buildShowStatsEnabledViewUiModel() {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel buildShowStatsDisabledViewUiModel() {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel buildShowStatsActivatingViewUiModel() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J?\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0005H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewModel$DateSelectorUiModel;", "", "isVisible", "", "date", "", "timeZone", "enableSelectPrevious", "enableSelectNext", "(ZLjava/lang/String;Ljava/lang/String;ZZ)V", "getDate", "()Ljava/lang/String;", "getEnableSelectNext", "()Z", "getEnableSelectPrevious", "getTimeZone", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DateSelectorUiModel {
        private final boolean isVisible = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String date = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String timeZone = null;
        private final boolean enableSelectPrevious = false;
        private final boolean enableSelectNext = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel copy(boolean isVisible, @org.jetbrains.annotations.Nullable()
        java.lang.String date, @org.jetbrains.annotations.Nullable()
        java.lang.String timeZone, boolean enableSelectPrevious, boolean enableSelectNext) {
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
        
        public DateSelectorUiModel() {
            super();
        }
        
        public DateSelectorUiModel(boolean isVisible, @org.jetbrains.annotations.Nullable()
        java.lang.String date, @org.jetbrains.annotations.Nullable()
        java.lang.String timeZone, boolean enableSelectPrevious, boolean enableSelectNext) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isVisible() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getDate() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getTimeZone() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getEnableSelectPrevious() {
            return false;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean getEnableSelectNext() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsViewModel$StatsModuleUiModel;", "", "disabledStatsViewVisible", "", "disabledStatsProgressVisible", "(ZZ)V", "getDisabledStatsProgressVisible", "()Z", "getDisabledStatsViewVisible", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class StatsModuleUiModel {
        private final boolean disabledStatsViewVisible = false;
        private final boolean disabledStatsProgressVisible = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.StatsViewModel.StatsModuleUiModel copy(boolean disabledStatsViewVisible, boolean disabledStatsProgressVisible) {
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
        
        public StatsModuleUiModel() {
            super();
        }
        
        public StatsModuleUiModel(boolean disabledStatsViewVisible, boolean disabledStatsProgressVisible) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getDisabledStatsViewVisible() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getDisabledStatsProgressVisible() {
            return false;
        }
    }
}