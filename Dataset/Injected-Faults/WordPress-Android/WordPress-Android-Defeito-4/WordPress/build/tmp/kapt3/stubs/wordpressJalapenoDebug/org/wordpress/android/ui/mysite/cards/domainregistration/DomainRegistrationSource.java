package org.wordpress.android.ui.mysite.cards.domainregistration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0010H\u0016J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u0012H\u0007J\u0010\u0010%\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0002J+\u0010&\u001a\u00020\u001f*\b\u0012\u0004\u0012\u00020\u00020\'2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\"H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J*\u0010*\u001a\u00020\u001f*\b\u0012\u0004\u0012\u00020\u00020\'2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\"H\u0002J3\u0010+\u001a\u00020\u001f*\b\u0012\u0004\u0012\u00020\u00020\'2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0015H\u0002\u00a2\u0006\u0002\u0010-J,\u0010+\u001a\u00020\u001f*\b\u0012\u0004\u0012\u00020\u00020\'2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010(\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u0014X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/domainregistration/DomainRegistrationSource;", "Lorg/wordpress/android/ui/mysite/MySiteSource$MySiteRefreshSource;", "Lorg/wordpress/android/ui/mysite/MySiteUiState$PartialState$DomainCreditAvailable;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "siteUtils", "Lorg/wordpress/android/util/SiteUtilsWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/util/SiteUtilsWrapper;)V", "continuations", "", "", "Lkotlinx/coroutines/CancellableContinuation;", "Lorg/wordpress/android/fluxc/store/SiteStore$OnPlansFetched;", "refresh", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getRefresh", "()Landroidx/lifecycle/MutableLiveData;", "build", "Landroidx/lifecycle/LiveData;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "siteLocalId", "clear", "", "dispatchFetchPlans", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "onPlansFetched", "event", "shouldFetchPlans", "fetchPlans", "Landroidx/lifecycle/MediatorLiveData;", "selectedSite", "(Landroidx/lifecycle/MediatorLiveData;ILorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchPlansAndRefreshData", "refreshData", "isRefresh", "(Landroidx/lifecycle/MediatorLiveData;Lkotlinx/coroutines/CoroutineScope;ILjava/lang/Boolean;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainRegistrationSource implements org.wordpress.android.ui.mysite.MySiteSource.MySiteRefreshSource<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    private final org.wordpress.android.util.SiteUtilsWrapper siteUtils = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> refresh = null;
    private final java.util.Map<java.lang.Integer, kotlinx.coroutines.CancellableContinuation<org.wordpress.android.fluxc.store.SiteStore.OnPlansFetched>> continuations = null;
    
    @javax.inject.Inject()
    public DomainRegistrationSource(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SiteUtilsWrapper siteUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.MutableLiveData<java.lang.Boolean> getRefresh() {
        return null;
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.lifecycle.LiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> build(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId) {
        return null;
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId, java.lang.Boolean isRefresh) {
    }
    
    private final void refreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> $this$refreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId, org.wordpress.android.fluxc.model.SiteModel selectedSite) {
    }
    
    private final void fetchPlansAndRefreshData(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> $this$fetchPlansAndRefreshData, kotlinx.coroutines.CoroutineScope coroutineScope, int siteLocalId, org.wordpress.android.fluxc.model.SiteModel selectedSite) {
    }
    
    @kotlin.Suppress(names = {"SwallowedException"})
    private final java.lang.Object fetchPlans(androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> $this$fetchPlans, int siteLocalId, org.wordpress.android.fluxc.model.SiteModel selectedSite, kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    private final boolean shouldFetchPlans(org.wordpress.android.fluxc.model.SiteModel site) {
        return false;
    }
    
    private final void dispatchFetchPlans(org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onPlansFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnPlansFetched event) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable getState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable value) {
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
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> $this$postState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable value) {
    }
    
    public void refresh() {
    }
    
    public void setState(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable> $this$setState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteUiState.PartialState.DomainCreditAvailable value) {
    }
}