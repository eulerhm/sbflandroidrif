package com.google.android.gnd.system;

import java.lang.System;

/**
 * Manages enabling of settings and related flows to/from the Activity.
 *
 *
 * Note: Currently only supports location settings, but could be expanded to support other
 * settings types in the future.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/google/android/gnd/system/SettingsManager;", "", "activityStreams", "Lcom/google/android/gnd/system/ActivityStreams;", "settingsClient", "Lcom/google/android/gnd/system/rx/RxSettingsClient;", "(Lcom/google/android/gnd/system/ActivityStreams;Lcom/google/android/gnd/system/rx/RxSettingsClient;)V", "enableLocationSettings", "Lio/reactivex/Completable;", "locationRequest", "Lcom/google/android/gms/location/LocationRequest;", "getNextResult", "requestCode", "", "onCheckSettingsFailure", "throwable", "", "startResolution", "resolvableException", "Lcom/google/android/gms/common/api/ResolvableApiException;", "gnd_debug"})
@javax.inject.Singleton()
public final class SettingsManager {
    private final com.google.android.gnd.system.ActivityStreams activityStreams = null;
    private final com.google.android.gnd.system.rx.RxSettingsClient settingsClient = null;
    
    @javax.inject.Inject()
    public SettingsManager(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.ActivityStreams activityStreams, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.rx.RxSettingsClient settingsClient) {
        super();
    }
    
    /**
     * Try to enable location settings. If location settings are already enabled, this will complete
     * immediately on subscribe.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable enableLocationSettings(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.location.LocationRequest locationRequest) {
        return null;
    }
    
    private final io.reactivex.Completable onCheckSettingsFailure(java.lang.Throwable throwable) {
        return null;
    }
    
    private final io.reactivex.Completable startResolution(int requestCode, com.google.android.gms.common.api.ResolvableApiException resolvableException) {
        return null;
    }
    
    private final io.reactivex.Completable getNextResult(int requestCode) {
        return null;
    }
}