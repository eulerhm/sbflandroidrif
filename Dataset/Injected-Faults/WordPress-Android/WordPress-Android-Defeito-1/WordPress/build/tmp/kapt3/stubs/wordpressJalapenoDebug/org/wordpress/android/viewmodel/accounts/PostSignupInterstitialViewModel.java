package org.wordpress.android.viewmodel.accounts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0015B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u000fH\u0002J\u0006\u0010\u0013\u001a\u00020\u000fJ\u0006\u0010\u0014\u001a\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/viewmodel/accounts/PostSignupInterstitialViewModel;", "Landroidx/lifecycle/ViewModel;", "appPrefs", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "unifiedLoginTracker", "Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/accounts/UnifiedLoginTracker;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "navigationAction", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/accounts/PostSignupInterstitialViewModel$NavigationAction;", "getNavigationAction", "()Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "onAddSelfHostedSiteButtonPressed", "", "onBackButtonPressed", "onCreateNewSiteButtonPressed", "onDismiss", "onDismissButtonPressed", "onInterstitialShown", "NavigationAction", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostSignupInterstitialViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefs = null;
    private final org.wordpress.android.ui.accounts.UnifiedLoginTracker unifiedLoginTracker = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.accounts.PostSignupInterstitialViewModel.NavigationAction> navigationAction = null;
    
    @javax.inject.Inject()
    public PostSignupInterstitialViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefs, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.accounts.UnifiedLoginTracker unifiedLoginTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.accounts.PostSignupInterstitialViewModel.NavigationAction> getNavigationAction() {
        return null;
    }
    
    public final void onInterstitialShown() {
    }
    
    public final void onCreateNewSiteButtonPressed() {
    }
    
    public final void onAddSelfHostedSiteButtonPressed() {
    }
    
    public final void onDismissButtonPressed() {
    }
    
    public final void onBackButtonPressed() {
    }
    
    private final void onDismiss() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/viewmodel/accounts/PostSignupInterstitialViewModel$NavigationAction;", "", "(Ljava/lang/String;I)V", "START_SITE_CREATION_FLOW", "START_SITE_CONNECTION_FLOW", "DISMISS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum NavigationAction {
        /*public static final*/ START_SITE_CREATION_FLOW /* = new START_SITE_CREATION_FLOW() */,
        /*public static final*/ START_SITE_CONNECTION_FLOW /* = new START_SITE_CONNECTION_FLOW() */,
        /*public static final*/ DISMISS /* = new DISMISS() */;
        
        NavigationAction() {
        }
    }
}