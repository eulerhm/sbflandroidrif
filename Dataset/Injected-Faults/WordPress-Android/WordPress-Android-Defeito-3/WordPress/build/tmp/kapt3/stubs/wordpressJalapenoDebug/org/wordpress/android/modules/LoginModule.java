package org.wordpress.android.modules;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/modules/LoginModule;", "", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Module(includes = {org.wordpress.android.login.di.LoginFragmentModule.class, org.wordpress.android.login.di.LoginServiceModule.class})
public abstract class LoginModule {
    
    public LoginModule() {
        super();
    }
}