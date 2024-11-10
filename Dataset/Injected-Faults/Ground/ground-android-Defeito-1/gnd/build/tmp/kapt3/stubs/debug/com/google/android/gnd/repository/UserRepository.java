package com.google.android.gnd.repository;

import java.lang.System;

/**
 * Coordinates persistence of [User] instance in local data store. For more details on this
 * pattern and overall architecture, see * https://developer.android.com/jetpack/docs/guide.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0019\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\f0\u0012\u00a2\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00070\u001b\u00a2\u0006\u0002\b\u00132\u0006\u0010\u001c\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/google/android/gnd/repository/UserRepository;", "", "authenticationManager", "Lcom/google/android/gnd/system/auth/AuthenticationManager;", "localDataStore", "Lcom/google/android/gnd/persistence/local/LocalDataStore;", "localValueStore", "Lcom/google/android/gnd/persistence/local/LocalValueStore;", "schedulers", "Lcom/google/android/gnd/rx/Schedulers;", "(Lcom/google/android/gnd/system/auth/AuthenticationManager;Lcom/google/android/gnd/persistence/local/LocalDataStore;Lcom/google/android/gnd/persistence/local/LocalValueStore;Lcom/google/android/gnd/rx/Schedulers;)V", "currentUser", "Lcom/google/android/gnd/model/User;", "getCurrentUser", "()Lcom/google/android/gnd/model/User;", "clearUserPreferences", "", "getUser", "Lio/reactivex/Single;", "Lcom/google/android/gnd/rx/annotations/Cold;", "userId", "", "getUserRole", "Lcom/google/android/gnd/model/Role;", "survey", "Lcom/google/android/gnd/model/Survey;", "saveUser", "Lio/reactivex/Completable;", "user", "gnd_debug"})
public final class UserRepository {
    private final com.google.android.gnd.system.auth.AuthenticationManager authenticationManager = null;
    private final com.google.android.gnd.persistence.local.LocalDataStore localDataStore = null;
    private final com.google.android.gnd.persistence.local.LocalValueStore localValueStore = null;
    private final com.google.android.gnd.rx.Schedulers schedulers = null;
    
    @javax.inject.Inject()
    public UserRepository(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.auth.AuthenticationManager authenticationManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.local.LocalDataStore localDataStore, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.persistence.local.LocalValueStore localValueStore, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.rx.Schedulers schedulers) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.model.User getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.model.Role getUserRole(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.Survey survey) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable saveUser(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.model.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.google.android.gnd.model.User> getUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    /**
     * Clears all user-specific preferences and settings.
     */
    public final void clearUserPreferences() {
    }
}