package org.wordpress.android.ui.accounts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/accounts/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "(Lorg/wordpress/android/util/BuildConfigWrapper;)V", "_navigationEvents", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/accounts/LoginNavigationEvents;", "navigationEvents", "Landroidx/lifecycle/LiveData;", "getNavigationEvents", "()Landroidx/lifecycle/LiveData;", "getMagicLinkScheme", "Lorg/wordpress/android/fluxc/store/AccountStore$AuthEmailPayloadScheme;", "onHandleNoJetpackSites", "", "onHandleSiteAddressError", "siteInfo", "Lorg/wordpress/android/fluxc/store/SiteStore$ConnectSiteInfoPayload;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> _navigationEvents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> navigationEvents = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.accounts.LoginNavigationEvents>> getNavigationEvents() {
        return null;
    }
    
    public final void onHandleSiteAddressError(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.ConnectSiteInfoPayload siteInfo) {
    }
    
    public final void onHandleNoJetpackSites() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore.AuthEmailPayloadScheme getMagicLinkScheme() {
        return null;
    }
}