package org.wordpress.android.ui.accounts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0006\u0010\u0017\u001a\u00020\u0014J\u0006\u0010\u0018\u001a\u00020\u0014J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fR\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginEpilogueViewModel;", "Landroidx/lifecycle/ViewModel;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "mySiteDefaultTabExperiment", "Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/BuildConfigWrapper;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;)V", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "navigationEvents", "Landroidx/lifecycle/LiveData;", "getNavigationEvents", "()Landroidx/lifecycle/LiveData;", "checkAndSetVariantForMySiteDefaultTabExperiment", "", "handleNoSitesFound", "handleSitesFound", "onContinue", "onCreateNewSite", "onLoginEpilogueResume", "doLoginUpdate", "", "onLoginFinished", "onSiteClick", "localId", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LoginEpilogueViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment mySiteDefaultTabExperiment = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> navigationEvents = null;
    
    @javax.inject.Inject()
    public LoginEpilogueViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment mySiteDefaultTabExperiment) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    public final void onSiteClick(int localId) {
    }
    
    public final void onCreateNewSite() {
    }
    
    public final void onContinue() {
    }
    
    public final void checkAndSetVariantForMySiteDefaultTabExperiment() {
    }
    
    private final void handleNoSitesFound() {
    }
    
    private final void handleSitesFound() {
    }
    
    public final void onLoginEpilogueResume(boolean doLoginUpdate) {
    }
    
    public final void onLoginFinished(boolean doLoginUpdate) {
    }
}