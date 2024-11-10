package com.google.android.gnd.system.auth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\fH&J\b\u0010\u000e\u001a\u00020\fH&R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000f"}, d2 = {"Lcom/google/android/gnd/system/auth/AuthenticationManager;", "", "currentUser", "Lcom/google/android/gnd/model/User;", "getCurrentUser", "()Lcom/google/android/gnd/model/User;", "signInState", "Lio/reactivex/Observable;", "Lcom/google/android/gnd/system/auth/SignInState;", "getSignInState", "()Lio/reactivex/Observable;", "init", "", "signIn", "signOut", "gnd_debug"})
public abstract interface AuthenticationManager {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Observable<com.google.android.gnd.system.auth.SignInState> getSignInState();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.google.android.gnd.model.User getCurrentUser();
    
    public abstract void init();
    
    public abstract void signIn();
    
    public abstract void signOut();
}