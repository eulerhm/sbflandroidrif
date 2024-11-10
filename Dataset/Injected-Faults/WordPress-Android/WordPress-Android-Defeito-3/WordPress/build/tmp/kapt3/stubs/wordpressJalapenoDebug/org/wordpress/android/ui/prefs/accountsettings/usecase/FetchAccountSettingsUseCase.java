package org.wordpress.android.ui.prefs.accountsettings.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\n\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/usecase/FetchAccountSettingsUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "continuationWrapper", "Lorg/wordpress/android/ui/utils/ContinuationWrapper;", "Lorg/wordpress/android/fluxc/store/AccountStore$OnAccountChanged;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/utils/ContinuationWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "fetchNewSettings", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAccountChanged", "", "event", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchAccountSettingsUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.utils.ContinuationWrapper<org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuationWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    
    @javax.inject.Inject()
    public FetchAccountSettingsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "DEFAULT_CONTINUATION")
    org.wordpress.android.ui.utils.ContinuationWrapper<org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuationWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchNewSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuation) {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onAccountChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged event) {
    }
}