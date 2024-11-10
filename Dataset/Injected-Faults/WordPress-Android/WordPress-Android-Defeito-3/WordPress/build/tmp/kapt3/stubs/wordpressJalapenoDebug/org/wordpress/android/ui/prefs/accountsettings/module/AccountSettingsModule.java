package org.wordpress.android.ui.prefs.accountsettings.module;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/prefs/accountsettings/module/AccountSettingsModule;", "", "()V", "provideConcurrentContinuationWrapper", "Lorg/wordpress/android/ui/utils/ContinuationWrapper;", "Lorg/wordpress/android/fluxc/store/AccountStore$OnAccountChanged;", "provideDefaultContinuationWrapper", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Module()
public final class AccountSettingsModule {
    
    public AccountSettingsModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "DEFAULT_CONTINUATION")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.utils.ContinuationWrapper<org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> provideDefaultContinuationWrapper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CONCURRENT_CONTINUATION")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.utils.ContinuationWrapper<org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged> provideConcurrentContinuationWrapper() {
        return null;
    }
}