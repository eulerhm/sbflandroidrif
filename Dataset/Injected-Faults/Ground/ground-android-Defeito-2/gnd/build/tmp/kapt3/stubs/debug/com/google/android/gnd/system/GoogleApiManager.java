package com.google.android.gnd.system;

import java.lang.System;

@dagger.hilt.android.scopes.ActivityScoped()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\f\u001a\u00020\nH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/google/android/gnd/system/GoogleApiManager;", "", "context", "Landroid/content/Context;", "googleApiAvailability", "Lcom/google/android/gms/common/GoogleApiAvailability;", "activityStreams", "Lcom/google/android/gnd/system/ActivityStreams;", "(Landroid/content/Context;Lcom/google/android/gms/common/GoogleApiAvailability;Lcom/google/android/gnd/system/ActivityStreams;)V", "getNextInstallApiResult", "Lio/reactivex/Completable;", "installGooglePlayServices", "requestInstallOrComplete", "gnd_debug"})
public final class GoogleApiManager {
    private final android.content.Context context = null;
    private final com.google.android.gms.common.GoogleApiAvailability googleApiAvailability = null;
    private final com.google.android.gnd.system.ActivityStreams activityStreams = null;
    
    @javax.inject.Inject()
    public GoogleApiManager(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.common.GoogleApiAvailability googleApiAvailability, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.ActivityStreams activityStreams) {
        super();
    }
    
    /**
     * Returns a stream that either completes immediately if Google Play Services are already
     * installed, otherwise shows install dialog. Terminates with error if install not possible or
     * cancelled.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable installGooglePlayServices() {
        return null;
    }
    
    private final io.reactivex.Completable requestInstallOrComplete() {
        return null;
    }
    
    private final io.reactivex.Completable getNextInstallApiResult() {
        return null;
    }
}