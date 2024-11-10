package org.wordpress.android.ui.domains;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewClient;", "Lorg/wordpress/android/util/ErrorManagedWebViewClient;", "listener", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewClient$DomainRegistrationCheckoutWebViewClientListener;", "(Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewClient$DomainRegistrationCheckoutWebViewClientListener;)V", "navigationDelegate", "Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewNavigationDelegate;", "canNavigateTo", "", "uri", "Landroid/net/Uri;", "doUpdateVisitedHistory", "", "view", "Landroid/webkit/WebView;", "url", "", "isReload", "isCheckoutSuccessPage", "shouldOverrideUrlLoading", "request", "Landroid/webkit/WebResourceRequest;", "Companion", "DomainRegistrationCheckoutWebViewClientListener", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DomainRegistrationCheckoutWebViewClient extends org.wordpress.android.util.ErrorManagedWebViewClient {
    private final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewClient.DomainRegistrationCheckoutWebViewClientListener listener = null;
    private final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewNavigationDelegate navigationDelegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewClient.Companion Companion = null;
    private static final java.lang.String CHECKOUT_SUCCESS_PATH_PREFIX = "/checkout/thank-you/";
    
    public DomainRegistrationCheckoutWebViewClient(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.domains.DomainRegistrationCheckoutWebViewClient.DomainRegistrationCheckoutWebViewClientListener listener) {
        super(null);
    }
    
    @java.lang.Override()
    public boolean shouldOverrideUrlLoading(@org.jetbrains.annotations.NotNull()
    android.webkit.WebView view, @org.jetbrains.annotations.NotNull()
    android.webkit.WebResourceRequest request) {
        return false;
    }
    
    private final boolean canNavigateTo(android.net.Uri uri) {
        return false;
    }
    
    @java.lang.Override()
    public void doUpdateVisitedHistory(@org.jetbrains.annotations.NotNull()
    android.webkit.WebView view, @org.jetbrains.annotations.NotNull()
    java.lang.String url, boolean isReload) {
    }
    
    private final boolean isCheckoutSuccessPage(android.net.Uri uri) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewClient$DomainRegistrationCheckoutWebViewClientListener;", "Lorg/wordpress/android/util/ErrorManagedWebViewClient$ErrorManagedWebViewClientListener;", "onCheckoutSuccess", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface DomainRegistrationCheckoutWebViewClientListener extends org.wordpress.android.util.ErrorManagedWebViewClient.ErrorManagedWebViewClientListener {
        
        public abstract void onCheckoutSuccess();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/domains/DomainRegistrationCheckoutWebViewClient$Companion;", "", "()V", "CHECKOUT_SUCCESS_PATH_PREFIX", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}