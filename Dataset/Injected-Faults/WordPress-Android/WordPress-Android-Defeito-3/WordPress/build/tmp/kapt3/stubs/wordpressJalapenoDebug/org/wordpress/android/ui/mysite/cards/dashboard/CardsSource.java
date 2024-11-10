package org.wordpress.android.ui.mysite.cards.dashboard;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\"\u0010\u001c\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002J\"\u0010!\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0012\u0010\"\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001eH\u0002J\"\u0010#\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J3\u0010#\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0002\u0010%R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\r0\r0\u000fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$CardsUpdate;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "cardsStore", "Lorg/wordpress/android/fluxc/store/dashboard/CardsStore;", "todaysStatsCardFeatureConfig", "Lorg/wordpress/android/util/config/MySiteDashboardTodaysStatsCardFeatureConfig;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/fluxc/store/dashboard/CardsStore;Lorg/wordpress/android/util/config/MySiteDashboardTodaysStatsCardFeatureConfig;Lkotlinx/coroutines/CoroutineDispatcher;)V", "isTodaysStatsCardFeatureConfigEnabled", "", "refresh", "Landroidx/lifecycle/MutableLiveData;", "kotlin.jvm.PlatformType", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "", "getCardTypes", "", "Lorg/wordpress/android/fluxc/model/dashboard/CardModel$Type;", "fetchCardsAndPostErrorIfAvailable", "", "Landroidx/lifecycle/MediatorLiveData;", "selectedSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getData", "postErrorState", "refreshData", "isRefresh", "(Landroidx/lifecycle/MediatorLiveData;Lkotlinx/coroutines/CoroutineScope;ILjava/lang/Boolean;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CardsSource implements org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> {
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.fluxc.store.dashboard.CardsStore cardsStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final boolean isTodaysStatsCardFeatureConfigEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> refresh = null;
    
    @javax.inject.Inject()
    public CardsSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.dashboard.CardsStore cardsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig todaysStatsCardFeatureConfig, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.MutableLiveData<java.lang.Boolean> getRefresh() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
    
    private final void getData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> $this$getData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId, java.lang.Boolean isRefresh) {
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
    }
    
    private final void fetchCardsAndPostErrorIfAvailable(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> $this$fetchCardsAndPostErrorIfAvailable, kotlinx.coroutines.CoroutineScope coroutineScope, org.wordpress.android.fluxc.model.SiteModel selectedSite) {
    }
    
    private final java.util.List<org.wordpress.android.fluxc.model.dashboard.CardModel.Type> getCardTypes() {
        return null;
    }
    
    private final void postErrorState(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> $this$postErrorState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate getState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate value) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Boolean isRefreshing() {
        return null;
    }
    
    public void onRefreshedBackgroundThread() {
    }
    
    public void onRefreshedMainThread() {
    }
    
    public void postState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> $this$postState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate value) {
    }
    
    public void refresh() {
    }
    
    public void setState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate> $this$setState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.CardsUpdate value) {
    }
}