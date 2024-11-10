package com.google.android.gnd.system;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015J\u0010\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017H\u0002J\u0010\u0010\u0019\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u000b\u001a\u0017\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\u0007\u0010\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/google/android/gnd/system/LocationManager;", "", "permissionsManager", "Lcom/google/android/gnd/system/PermissionsManager;", "settingsManager", "Lcom/google/android/gnd/system/SettingsManager;", "locationClient", "Lcom/google/android/gnd/system/rx/RxFusedLocationProviderClient;", "(Lcom/google/android/gnd/system/PermissionsManager;Lcom/google/android/gnd/system/SettingsManager;Lcom/google/android/gnd/system/rx/RxFusedLocationProviderClient;)V", "locationUpdateCallback", "Lcom/google/android/gnd/system/rx/RxLocationCallback;", "locationUpdates", "Lio/reactivex/subjects/Subject;", "Landroid/location/Location;", "Lcom/google/android/gnd/rx/annotations/Hot;", "replays", "disableLocationUpdates", "Lio/reactivex/Single;", "Lcom/google/android/gnd/rx/BooleanOrError;", "enableLocationUpdates", "getLocationUpdates", "Lio/reactivex/Flowable;", "removeLocationUpdates", "Lio/reactivex/Completable;", "kotlin.jvm.PlatformType", "requestLocationUpdates", "gnd_debug"})
@javax.inject.Singleton()
public final class LocationManager {
    private final com.google.android.gnd.system.PermissionsManager permissionsManager = null;
    private final com.google.android.gnd.system.SettingsManager settingsManager = null;
    private final com.google.android.gnd.system.rx.RxFusedLocationProviderClient locationClient = null;
    private final io.reactivex.subjects.Subject<android.location.Location> locationUpdates = null;
    private final com.google.android.gnd.system.rx.RxLocationCallback locationUpdateCallback = null;
    
    @javax.inject.Inject()
    public LocationManager(@org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.PermissionsManager permissionsManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.SettingsManager settingsManager, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.rx.RxFusedLocationProviderClient locationClient) {
        super();
    }
    
    /**
     * Returns the location update stream. New subscribers and downstream subscribers that can't keep
     * up will only see the latest location.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Flowable<android.location.Location> getLocationUpdates() {
        return null;
    }
    
    /**
     * Asynchronously try to enable location permissions and settings, and if successful, turns on
     * location updates exposed by [.getLocationUpdates].
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.Synchronized()
    public final synchronized io.reactivex.Single<com.google.android.gnd.rx.BooleanOrError> enableLocationUpdates() {
        return null;
    }
    
    private final io.reactivex.Completable requestLocationUpdates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.Synchronized()
    public final synchronized io.reactivex.Single<com.google.android.gnd.rx.BooleanOrError> disableLocationUpdates() {
        return null;
    }
    
    private final io.reactivex.Completable removeLocationUpdates() {
        return null;
    }
}