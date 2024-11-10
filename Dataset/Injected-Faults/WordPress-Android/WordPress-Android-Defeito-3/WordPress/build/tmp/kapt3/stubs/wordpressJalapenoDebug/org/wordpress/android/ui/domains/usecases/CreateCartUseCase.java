package org.wordpress.android.ui.domains.usecases;

import java.lang.System;

/**
 * Wraps an [OnShoppingCartCreated] into a coroutine.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ9\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\tH\u0007R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/domains/usecases/CreateCartUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "transactionsStore", "Lorg/wordpress/android/fluxc/store/TransactionsStore;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/TransactionsStore;)V", "continuation", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/TransactionsStore$OnShoppingCartCreated;", "clear", "", "execute", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "productId", "", "domainName", "", "isPrivacyEnabled", "", "isTemporary", "(Lorg/wordpress/android/fluxc/model/SiteModel;ILjava/lang/String;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onShoppingCartCreated", "event", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CreateCartUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.TransactionsStore transactionsStore = null;
    private kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.TransactionsStore.OnShoppingCartCreated> continuation;
    
    @javax.inject.Inject()
    public CreateCartUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TransactionsStore transactionsStore) {
        super();
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object execute(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, int productId, @org.jetbrains.annotations.NotNull()
    java.lang.String domainName, boolean isPrivacyEnabled, boolean isTemporary, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.TransactionsStore.OnShoppingCartCreated> continuation) {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void onShoppingCartCreated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.TransactionsStore.OnShoppingCartCreated event) {
    }
}