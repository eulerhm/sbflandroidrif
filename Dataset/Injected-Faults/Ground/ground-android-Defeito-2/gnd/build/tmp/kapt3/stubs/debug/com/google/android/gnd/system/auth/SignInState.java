package com.google.android.gnd.system.auth;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0010B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0011"}, d2 = {"Lcom/google/android/gnd/system/auth/SignInState;", "Lcom/google/android/gnd/rx/ValueOrError;", "Lcom/google/android/gnd/model/User;", "state", "Lcom/google/android/gnd/system/auth/SignInState$State;", "(Lcom/google/android/gnd/system/auth/SignInState$State;)V", "user", "(Lcom/google/android/gnd/model/User;)V", "error", "", "(Ljava/lang/Throwable;)V", "getState", "()Lcom/google/android/gnd/system/auth/SignInState$State;", "Ljava8/util/Optional;", "getUser", "()Ljava8/util/Optional;", "State", "gnd_debug"})
public final class SignInState extends com.google.android.gnd.rx.ValueOrError<com.google.android.gnd.model.User> {
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gnd.system.auth.SignInState.State state = null;
    @org.jetbrains.annotations.NotNull()
    private final java8.util.Optional<com.google.android.gnd.model.User> user = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.system.auth.SignInState.State getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java8.util.Optional<com.google.android.gnd.model.User> getUser() {
        return null;
    }
    
    public SignInState(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.auth.SignInState.State state) {
        super(null, null);
    }
    
    public SignInState(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.User user) {
        super(null, null);
    }
    
    public SignInState(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable error) {
        super(null, null);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/google/android/gnd/system/auth/SignInState$State;", "", "(Ljava/lang/String;I)V", "SIGNED_OUT", "SIGNING_IN", "SIGNED_IN", "ERROR", "gnd_debug"})
    public static enum State {
        /*public static final*/ SIGNED_OUT /* = new SIGNED_OUT() */,
        /*public static final*/ SIGNING_IN /* = new SIGNING_IN() */,
        /*public static final*/ SIGNED_IN /* = new SIGNED_IN() */,
        /*public static final*/ ERROR /* = new ERROR() */;
        
        State() {
        }
    }
}