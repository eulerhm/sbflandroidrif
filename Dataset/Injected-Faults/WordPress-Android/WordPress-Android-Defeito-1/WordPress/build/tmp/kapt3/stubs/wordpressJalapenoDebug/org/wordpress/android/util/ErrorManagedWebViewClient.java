package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J&\u0010\u000e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J&\u0010\u0011\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/util/ErrorManagedWebViewClient;", "Landroid/webkit/WebViewClient;", "baseWebViewClientListener", "Lorg/wordpress/android/util/ErrorManagedWebViewClient$ErrorManagedWebViewClientListener;", "(Lorg/wordpress/android/util/ErrorManagedWebViewClient$ErrorManagedWebViewClientListener;)V", "errorReceived", "", "requestedUrl", "", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "onPageStarted", "favicon", "Landroid/graphics/Bitmap;", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "ErrorManagedWebViewClientListener", "org.wordpress.android_wordpressJalapenoDebug"})
public class ErrorManagedWebViewClient extends android.webkit.WebViewClient {
    private final org.wordpress.android.util.ErrorManagedWebViewClient.ErrorManagedWebViewClientListener baseWebViewClientListener = null;
    private boolean errorReceived = false;
    private java.lang.String requestedUrl;
    
    public ErrorManagedWebViewClient(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.ErrorManagedWebViewClient.ErrorManagedWebViewClientListener baseWebViewClientListener) {
        super();
    }
    
    @java.lang.Override()
    public void onPageStarted(@org.jetbrains.annotations.Nullable()
    android.webkit.WebView view, @org.jetbrains.annotations.Nullable()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap favicon) {
    }
    
    @java.lang.Override()
    public void onPageFinished(@org.jetbrains.annotations.NotNull()
    android.webkit.WebView view, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    @java.lang.Override()
    public void onReceivedError(@org.jetbrains.annotations.Nullable()
    android.webkit.WebView view, @org.jetbrains.annotations.Nullable()
    android.webkit.WebResourceRequest request, @org.jetbrains.annotations.Nullable()
    android.webkit.WebResourceError error) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/util/ErrorManagedWebViewClient$ErrorManagedWebViewClientListener;", "", "onWebViewPageLoaded", "", "onWebViewReceivedError", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface ErrorManagedWebViewClientListener {
        
        public abstract void onWebViewPageLoaded();
        
        public abstract void onWebViewReceivedError();
    }
}