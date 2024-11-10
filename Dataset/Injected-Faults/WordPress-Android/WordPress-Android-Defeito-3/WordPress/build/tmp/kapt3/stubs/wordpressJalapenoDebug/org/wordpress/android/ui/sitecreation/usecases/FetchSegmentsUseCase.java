package org.wordpress.android.ui.sitecreation.usecases;

import java.lang.System;

/**
 * Transforms EventBus event to a coroutines.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u000e\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0007R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/usecases/FetchSegmentsUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "verticalStore", "Lorg/wordpress/android/fluxc/store/VerticalStore;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/VerticalStore;)V", "continuation", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/VerticalStore$OnSegmentsFetched;", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "getVerticalStore", "()Lorg/wordpress/android/fluxc/store/VerticalStore;", "fetchCategories", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSiteCategoriesFetched", "", "event", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchSegmentsUseCase {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.VerticalStore verticalStore = null;
    private kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.VerticalStore.OnSegmentsFetched> continuation;
    
    @javax.inject.Inject()
    public FetchSegmentsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"unused"})
    org.wordpress.android.fluxc.store.VerticalStore verticalStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.VerticalStore getVerticalStore() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.VerticalStore.OnSegmentsFetched> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSiteCategoriesFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.VerticalStore.OnSegmentsFetched event) {
    }
}