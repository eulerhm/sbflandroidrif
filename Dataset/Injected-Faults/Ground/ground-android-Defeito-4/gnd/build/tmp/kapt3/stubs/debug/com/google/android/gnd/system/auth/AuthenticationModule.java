package com.google.android.gnd.system.auth;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/google/android/gnd/system/auth/AuthenticationModule;", "", "()V", "googleAuthenticationManager", "Lcom/google/android/gnd/system/auth/AuthenticationManager;", "gam", "Lcom/google/android/gnd/system/auth/GoogleAuthenticationManager;", "gnd_debug"})
@dagger.Module()
public abstract class AuthenticationModule {
    
    public AuthenticationModule() {
        super();
    }
    
    /**
     * Provides the Google implementation of authentication manager.
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Binds()
    public abstract com.google.android.gnd.system.auth.AuthenticationManager googleAuthenticationManager(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.auth.GoogleAuthenticationManager gam);
}