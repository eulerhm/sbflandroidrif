package org.wordpress.android.ui.deeplinks;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 22\u00020\u0001:\u00012BA\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010&\u001a\u0004\u0018\u00010\u00162\u0006\u0010\'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020\u0018H\u0002J\u001a\u0010)\u001a\u0004\u0018\u00010\u00162\u0006\u0010\'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u0018H\u0002J\u001c\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00182\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0002J\b\u0010/\u001a\u00020\u0014H\u0014J\u0006\u00100\u001a\u00020\u0014J\u001a\u00101\u001a\u00020\u00142\b\u0010-\u001a\u0004\u0018\u00010.2\b\u0010\'\u001a\u0004\u0018\u00010\u0018R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00130\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u00130\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkingIntentReceiverViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "uiDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "deepLinkHandlers", "Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandlers;", "deepLinkUriUtils", "Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "serverTrackingHandler", "Lorg/wordpress/android/ui/deeplinks/handlers/ServerTrackingHandler;", "deepLinkTrackingUtils", "Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandlers;Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/deeplinks/handlers/ServerTrackingHandler;Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;)V", "_finish", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "_navigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "cachedUri", "Lorg/wordpress/android/util/UriWrapper;", "getCachedUri", "()Lorg/wordpress/android/util/UriWrapper;", "setCachedUri", "(Lorg/wordpress/android/util/UriWrapper;)V", "finish", "Landroidx/lifecycle/LiveData;", "getFinish", "()Landroidx/lifecycle/LiveData;", "navigateAction", "getNavigateAction", "toast", "", "getToast", "buildNavigateAction", "uri", "rootUri", "getRedirectUriAndBuildNavigateAction", "handleUrl", "", "uriWrapper", "action", "", "onCleared", "onSuccessfulLogin", "start", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeepLinkingIntentReceiverViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher uiDispatcher = null;
    private final org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandlers deepLinkHandlers = null;
    private final org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.deeplinks.handlers.ServerTrackingHandler serverTrackingHandler = null;
    private final org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils deepLinkTrackingUtils = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction>> _navigateAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction>> navigateAction = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _finish = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> finish = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast = null;
    @org.jetbrains.annotations.Nullable()
    private org.wordpress.android.util.UriWrapper cachedUri;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.deeplinks.DeepLinkingIntentReceiverViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOST_WORDPRESS_COM = "wordpress.com";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String APPLINK_SCHEME = "wordpress://";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SITE_DOMAIN = "domain";
    private static final java.lang.String REGULAR_TRACKING_PATH = "bar";
    
    @javax.inject.Inject()
    public DeepLinkingIntentReceiverViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher uiDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandlers deepLinkHandlers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.ServerTrackingHandler serverTrackingHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils deepLinkTrackingUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction>> getNavigateAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getFinish() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> getToast() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.util.UriWrapper getCachedUri() {
        return null;
    }
    
    public final void setCachedUri(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.UriWrapper p0) {
    }
    
    public final void start(@org.jetbrains.annotations.Nullable()
    java.lang.String action, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.UriWrapper uri) {
    }
    
    public final void onSuccessfulLogin() {
    }
    
    /**
     * Handles the following URLs
     * `wordpress.com/post...`
     * `wordpress.com/stats...`
     * `public-api.wordpress.com/mbar`
     * and builds the navigation action based on them
     */
    private final boolean handleUrl(org.wordpress.android.util.UriWrapper uriWrapper, java.lang.String action) {
        return false;
    }
    
    private final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction buildNavigateAction(org.wordpress.android.util.UriWrapper uri, org.wordpress.android.util.UriWrapper rootUri) {
        return null;
    }
    
    private final org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction getRedirectUriAndBuildNavigateAction(org.wordpress.android.util.UriWrapper uri, org.wordpress.android.util.UriWrapper rootUri) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkingIntentReceiverViewModel$Companion;", "", "()V", "APPLINK_SCHEME", "", "HOST_WORDPRESS_COM", "REGULAR_TRACKING_PATH", "SITE_DOMAIN", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}