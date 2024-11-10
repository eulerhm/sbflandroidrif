package org.wordpress.android.ui.sitecreation.services;

import java.lang.System;

/**
 * Transforms FETCH_SITE -> UPDATE_SITE fluxC request-response pair to a coroutine.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0019\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ#\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0007R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/services/FetchWpComSiteUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "continuation", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSiteChanged;", "createWpComSiteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "siteId", "", "fetchSite", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchSiteWithRetry", "remoteSiteId", "numberOfRetries", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSiteFetched", "", "event", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchWpComSiteUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged> continuation;
    
    @javax.inject.Inject()
    public FetchWpComSiteUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchSiteWithRetry(long remoteSiteId, int numberOfRetries, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged> continuation) {
        return null;
    }
    
    private final java.lang.Object fetchSite(long siteId, kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged> continuation) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.SiteModel createWpComSiteModel(long siteId) {
        return null;
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onSiteFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged event) {
    }
}