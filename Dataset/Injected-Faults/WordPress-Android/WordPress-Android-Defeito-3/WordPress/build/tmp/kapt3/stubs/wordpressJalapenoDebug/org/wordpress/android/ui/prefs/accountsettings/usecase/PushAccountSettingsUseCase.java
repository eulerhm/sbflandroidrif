package org.wordpress.android.ui.prefs.accountsettings.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\n\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0007J\u0006\u0010\u000f\u001a\u00020\rJ%\u0010\u0010\u001a\u00020\u00062\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\r0\u0012H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/usecase/PushAccountSettingsUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "continuationWrapper", "Lorg/wordpress/android/ui/utils/ContinuationWrapper;", "Lorg/wordpress/android/fluxc/store/AccountStore$OnAccountChanged;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/utils/ContinuationWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "cancelPendingEmailChange", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAccountChanged", "", "event", "onCleared", "updateAccountSettings", "addPayload", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/store/AccountStore$PushAccountSettingsPayload;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateEmail", "newEmail", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePassword", "newPassword", "updatePrimaryBlog", "blogId", "updateWebAddress", "newWebAddress", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PushAccountSettingsUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.utils.ContinuationWrapper<org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuationWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    
    @javax.inject.Inject()
    public PushAccountSettingsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CONCURRENT_CONTINUATION")
    org.wordpress.android.ui.utils.ContinuationWrapper<org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuationWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePrimaryBlog(@org.jetbrains.annotations.NotNull()
    java.lang.String blogId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object cancelPendingEmailChange(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String newEmail, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateWebAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String newWebAddress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePassword(@org.jetbrains.annotations.NotNull()
    java.lang.String newPassword, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuation) {
        return null;
    }
    
    private final java.lang.Object updateAccountSettings(kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.AccountStore.PushAccountSettingsPayload, kotlin.Unit> addPayload, kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> continuation) {
        return null;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onAccountChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged event) {
    }
    
    public final void onCleared() {
    }
}