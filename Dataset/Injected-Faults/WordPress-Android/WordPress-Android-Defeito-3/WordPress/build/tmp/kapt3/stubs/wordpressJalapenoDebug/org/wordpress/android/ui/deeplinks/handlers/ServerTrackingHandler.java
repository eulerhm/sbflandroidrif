package org.wordpress.android.ui.deeplinks.handlers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/ServerTrackingHandler;", "Lkotlinx/coroutines/CoroutineScope;", "appLogWrapper", "Lorg/wordpress/android/fluxc/utils/AppLogWrapper;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/utils/AppLogWrapper;Lorg/wordpress/android/viewmodel/ContextProvider;Lkotlinx/coroutines/CoroutineDispatcher;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/CompletableJob;", "queue", "Lcom/android/volley/RequestQueue;", "clear", "", "request", "uri", "Lorg/wordpress/android/util/UriWrapper;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ServerTrackingHandler implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final com.android.volley.RequestQueue queue = null;
    private final kotlinx.coroutines.CompletableJob job = null;
    
    @javax.inject.Inject()
    public ServerTrackingHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.AppLogWrapper appLogWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    public final void request(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
}