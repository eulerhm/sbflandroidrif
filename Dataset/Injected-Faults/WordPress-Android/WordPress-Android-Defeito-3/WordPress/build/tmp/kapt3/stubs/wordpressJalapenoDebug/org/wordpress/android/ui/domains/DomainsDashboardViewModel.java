package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ.\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u0012H\u0002J$\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00142\u0006\u0010(\u001a\u00020\u0012H\u0002J,\u0010.\u001a\u00020/2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00142\f\u00102\u001a\b\u0012\u0004\u0012\u00020-0\u0014H\u0002J\u001a\u00103\u001a\n 4*\u0004\u0018\u00010\'0\'2\b\u00105\u001a\u0004\u0018\u00010\'H\u0002J\u0010\u00106\u001a\u00020/2\u0006\u0010)\u001a\u00020\u0012H\u0002J\u0010\u00107\u001a\u00020\u00122\u0006\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020/H\u0002J\b\u0010;\u001a\u00020/H\u0014J\b\u0010<\u001a\u00020/H\u0002J\b\u0010=\u001a\u00020/H\u0002J\u0006\u0010>\u001a\u00020/J\u0010\u0010?\u001a\u00020@2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010A\u001a\u00020/2\u0006\u0010\u001d\u001a\u00020\u001eR\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001a\u00a8\u0006B"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainsDashboardViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "htmlMessageUtils", "Lorg/wordpress/android/ui/utils/HtmlMessageUtils;", "fetchPlansUseCase", "Lorg/wordpress/android/ui/domains/usecases/FetchPlansUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/utils/HtmlMessageUtils;Lorg/wordpress/android/ui/domains/usecases/FetchPlansUseCase;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onNavigation", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/domains/DomainsDashboardNavigationAction;", "_showProgressSpinner", "", "_uiModel", "", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem;", "isStarted", "onNavigation", "Landroidx/lifecycle/LiveData;", "getOnNavigation", "()Landroidx/lifecycle/LiveData;", "progressBar", "getProgressBar", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "getSite", "()Lorg/wordpress/android/fluxc/model/SiteModel;", "setSite", "(Lorg/wordpress/android/fluxc/model/SiteModel;)V", "uiModel", "getUiModel", "buildCtaItems", "freeDomainUrl", "", "hasCustomDomains", "hasDomainCredit", "hasPaidPlan", "buildCustomDomainItems", "customDomains", "Lorg/wordpress/android/fluxc/network/rest/wpcom/site/Domain;", "buildDashboardItems", "", "plans", "Lorg/wordpress/android/fluxc/model/PlanModel;", "domains", "getCleanUrl", "kotlin.jvm.PlatformType", "url", "onAddDomainClick", "onChangeSiteClick", "action", "Lorg/wordpress/android/ui/domains/DomainsDashboardItem$Action;", "onClaimDomainClick", "onCleared", "onGetDomainClick", "onManageDomainClick", "onSuccessfulDomainRegistration", "refresh", "Lkotlinx/coroutines/Job;", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainsDashboardViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils = null;
    private final org.wordpress.android.ui.domains.usecases.FetchPlansUseCase fetchPlansUseCase = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    public org.wordpress.android.fluxc.model.SiteModel site;
    private boolean isStarted = false;
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showProgressSpinner = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> progressBar = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainsDashboardNavigationAction>> _onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainsDashboardNavigationAction>> onNavigation = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.domains.DomainsDashboardItem>> _uiModel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.domains.DomainsDashboardItem>> uiModel = null;
    
    @javax.inject.Inject()
    public DomainsDashboardViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.HtmlMessageUtils htmlMessageUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.usecases.FetchPlansUseCase fetchPlansUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    public final void setSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getProgressBar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainsDashboardNavigationAction>> getOnNavigation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.domains.DomainsDashboardItem>> getUiModel() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final kotlinx.coroutines.Job refresh(org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    private final void buildDashboardItems(org.wordpress.android.fluxc.model.SiteModel site, java.util.List<org.wordpress.android.fluxc.model.PlanModel> plans, java.util.List<org.wordpress.android.fluxc.network.rest.wpcom.site.Domain> domains) {
    }
    
    private final java.util.List<org.wordpress.android.ui.domains.DomainsDashboardItem> buildCtaItems(java.lang.String freeDomainUrl, boolean hasCustomDomains, boolean hasDomainCredit, boolean hasPaidPlan) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.domains.DomainsDashboardItem> buildCustomDomainItems(java.util.List<org.wordpress.android.fluxc.network.rest.wpcom.site.Domain> customDomains, boolean hasCustomDomains) {
        return null;
    }
    
    private final java.lang.String getCleanUrl(java.lang.String url) {
        return null;
    }
    
    private final void onGetDomainClick() {
    }
    
    private final void onClaimDomainClick() {
    }
    
    private final void onAddDomainClick(boolean hasDomainCredit) {
    }
    
    private final void onManageDomainClick() {
    }
    
    private final boolean onChangeSiteClick(org.wordpress.android.ui.domains.DomainsDashboardItem.Action action) {
        return false;
    }
    
    public final void onSuccessfulDomainRegistration() {
    }
}