package org.wordpress.android.ui.sitecreation.usecases;

import java.lang.System;

/**
 * Transforms newSuggestDomainsAction EventBus event to a coroutine.
 *
 * The client may dispatch multiple requests, but we want to accept only the latest one and ignore all others.
 * We can't rely just on job.cancel() as the OnSuggestedDomains may have already been dispatched and FluxC will
 * return a result.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JC\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/usecases/FetchDomainsUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "pair", "Lkotlin/Pair;", "", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSuggestedDomains;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "fetchDomains", "query", "segmentId", "", "includeVendorDot", "", "includeDotBlog", "size", "", "(Ljava/lang/String;Ljava/lang/Long;ZZILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSuggestedDomains", "", "event", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchDomainsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    
    /**
     * Query - Continuation pair
     */
    private kotlin.Pair<java.lang.String, ? extends kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.SiteStore.OnSuggestedDomains>> pair;
    
    @javax.inject.Inject()
    public FetchDomainsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchDomains(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.Nullable()
    java.lang.Long segmentId, boolean includeVendorDot, boolean includeDotBlog, int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.SiteStore.OnSuggestedDomains> continuation) {
        return null;
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onSuggestedDomains(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnSuggestedDomains event) {
    }
}