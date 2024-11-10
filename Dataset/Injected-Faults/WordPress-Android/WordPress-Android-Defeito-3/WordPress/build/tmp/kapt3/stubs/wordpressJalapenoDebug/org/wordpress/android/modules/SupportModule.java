package org.wordpress.android.modules;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/modules/SupportModule;", "", "()V", "provideSupportHelper", "Lorg/wordpress/android/support/SupportHelper;", "provideZendeskHelper", "Lorg/wordpress/android/support/ZendeskHelper;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "supportHelper", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Module()
public final class SupportModule {
    
    public SupportModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final org.wordpress.android.support.ZendeskHelper provideZendeskHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.support.SupportHelper supportHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    @javax.inject.Singleton()
    public final org.wordpress.android.support.SupportHelper provideSupportHelper() {
        return null;
    }
}