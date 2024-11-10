package org.wordpress.android.ui.plans;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001)B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\u001fH\u0014J\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u000eJ\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0007J\u0006\u0010\'\u001a\u00020\u001fJ\u0006\u0010(\u001a\u00020\u001fR\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001f\u0010\u001a\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r0\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/plans/PlansViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "plansStore", "Lorg/wordpress/android/fluxc/store/PlanOffersStore;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/PlanOffersStore;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "_cachedPlans", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/wordpress/android/fluxc/model/plans/PlanOffersModel;", "_listStatus", "Lorg/wordpress/android/ui/plans/PlansViewModel$PlansListStatus;", "_plans", "_showDialog", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "isStarted", "", "listStatus", "Landroidx/lifecycle/LiveData;", "getListStatus", "()Landroidx/lifecycle/LiveData;", "plans", "getPlans", "showDialog", "getShowDialog", "create", "", "fetchPlans", "onCleared", "onItemClicked", "item", "onPlanOffersFetched", "event", "Lorg/wordpress/android/fluxc/store/PlanOffersStore$OnPlanOffersFetched;", "onPullToRefresh", "onShowCachedPlansButtonClicked", "PlansListStatus", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PlansViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private org.wordpress.android.fluxc.store.PlanOffersStore plansStore;
    private final kotlinx.coroutines.CoroutineDispatcher uiDispatcher = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.plans.PlansViewModel.PlansListStatus> _listStatus = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.fluxc.model.plans.PlanOffersModel>> _plans = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.fluxc.model.plans.PlanOffersModel>> _cachedPlans = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.fluxc.model.plans.PlanOffersModel> _showDialog = null;
    private boolean isStarted = false;
    
    @javax.inject.Inject()
    public PlansViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.PlanOffersStore plansStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.plans.PlansViewModel.PlansListStatus> getListStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.fluxc.model.plans.PlanOffersModel>> getPlans() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.fluxc.model.plans.PlanOffersModel> getShowDialog() {
        return null;
    }
    
    public final void create() {
    }
    
    private final void fetchPlans() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void onItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.plans.PlanOffersModel item) {
    }
    
    public final void onPullToRefresh() {
    }
    
    public final void onShowCachedPlansButtonClicked() {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onPlanOffersFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PlanOffersStore.OnPlanOffersFetched event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/plans/PlansViewModel$PlansListStatus;", "", "(Ljava/lang/String;I)V", "DONE", "ERROR", "ERROR_WITH_CACHE", "FETCHING", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PlansListStatus {
        /*public static final*/ DONE /* = new DONE() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ ERROR_WITH_CACHE /* = new ERROR_WITH_CACHE() */,
        /*public static final*/ FETCHING /* = new FETCHING() */;
        
        PlansListStatus() {
        }
    }
}