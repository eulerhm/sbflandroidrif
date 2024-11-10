package org.wordpress.android.ui.posts.reactnative;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J8\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\f0\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\f0\u0015H\u0002J:\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/posts/reactnative/ReactNativeRequestHandler;", "Lkotlinx/coroutines/CoroutineScope;", "reactNativeStore", "Lorg/wordpress/android/fluxc/store/ReactNativeStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/ReactNativeStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "destroy", "", "extractErrorMessage", "", "networkError", "Lorg/wordpress/android/fluxc/network/BaseRequest$BaseNetworkError;", "handleResponse", "response", "Lorg/wordpress/android/fluxc/store/ReactNativeFetchResponse;", "onSuccess", "Lkotlin/Function1;", "onError", "Landroid/os/Bundle;", "performGetRequest", "pathWithParams", "mSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "enableCaching", "", "Landroidx/core/util/Consumer;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReactNativeRequestHandler implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.fluxc.store.ReactNativeStore reactNativeStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.coroutines.CoroutineContext coroutineContext = null;
    
    @javax.inject.Inject()
    public ReactNativeRequestHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ReactNativeStore reactNativeStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    public final void performGetRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String pathWithParams, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel mSite, boolean enableCaching, @org.jetbrains.annotations.NotNull()
    androidx.core.util.Consumer<java.lang.String> onSuccess, @org.jetbrains.annotations.NotNull()
    androidx.core.util.Consumer<android.os.Bundle> onError) {
    }
    
    /**
     * A given instance of this class may not be used after [destroy] is called because:
     *    (1) this class's coroutineContext has a single job instance that is created on initialization;
     *    (2) calling `destroy()` cancels that job; and
     *    (3) jobs cannot be reused once cancelled.
     */
    public final void destroy() {
    }
    
    private final void handleResponse(org.wordpress.android.fluxc.store.ReactNativeFetchResponse response, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSuccess, kotlin.jvm.functions.Function1<? super android.os.Bundle, kotlin.Unit> onError) {
    }
    
    private final java.lang.String extractErrorMessage(org.wordpress.android.fluxc.network.BaseRequest.BaseNetworkError networkError) {
        return null;
    }
}