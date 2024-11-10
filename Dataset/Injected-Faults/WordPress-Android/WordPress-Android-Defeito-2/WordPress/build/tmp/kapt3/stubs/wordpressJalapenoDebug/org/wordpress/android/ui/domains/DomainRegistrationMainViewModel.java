package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationMainViewModel;", "Landroidx/lifecycle/ViewModel;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "_onNavigation", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/domains/DomainRegistrationNavigationAction;", "domainRegistrationPurpose", "Lorg/wordpress/android/ui/domains/DomainRegistrationActivity$DomainRegistrationPurpose;", "isStarted", "", "onNavigation", "Landroidx/lifecycle/LiveData;", "getOnNavigation", "()Landroidx/lifecycle/LiveData;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "completeDomainRegistration", "", "event", "Lorg/wordpress/android/ui/domains/DomainRegistrationCompletedEvent;", "finishDomainRegistration", "selectDomain", "domainProductDetails", "Lorg/wordpress/android/ui/domains/DomainProductDetails;", "start", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainRegistrationMainViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private boolean isStarted = false;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose domainRegistrationPurpose;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainRegistrationNavigationAction>> _onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainRegistrationNavigationAction>> onNavigation = null;
    
    @javax.inject.Inject()
    public DomainRegistrationMainViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.domains.DomainRegistrationNavigationAction>> getOnNavigation() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainRegistrationActivity.DomainRegistrationPurpose domainRegistrationPurpose) {
    }
    
    public final void selectDomain(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainProductDetails domainProductDetails) {
    }
    
    public final void completeDomainRegistration(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
    }
    
    public final void finishDomainRegistration(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainRegistrationCompletedEvent event) {
    }
}