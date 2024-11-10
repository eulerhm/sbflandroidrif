package com.google.android.gnd.system;

import java.lang.System;

/**
 * Abstracts native geocoding facilities.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/google/android/gnd/system/GeocodingManager;", "", "geocoder", "Landroid/location/Geocoder;", "schedulers", "Lcom/google/android/gnd/rx/Schedulers;", "resources", "Landroid/content/res/Resources;", "(Landroid/location/Geocoder;Lcom/google/android/gnd/rx/Schedulers;Landroid/content/res/Resources;)V", "defaultAreaName", "", "getAreaName", "Lio/reactivex/Single;", "Lcom/google/android/gnd/rx/annotations/Cold;", "bounds", "Lcom/google/android/gms/maps/model/LatLngBounds;", "getAreaNameInternal", "gnd_debug"})
@javax.inject.Singleton()
public final class GeocodingManager {
    private final android.location.Geocoder geocoder = null;
    private final com.google.android.gnd.rx.Schedulers schedulers = null;
    private final java.lang.String defaultAreaName = null;
    
    @javax.inject.Inject()
    public GeocodingManager(@org.jetbrains.annotations.NotNull()
    android.location.Geocoder geocoder, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.rx.Schedulers schedulers, @org.jetbrains.annotations.NotNull()
    android.content.res.Resources resources) {
        super();
    }
    
    /**
     * Retrieve a human readable name for the region bounded by the provided {@param bounds}.
     *
     *
     * If no area name is found for the given area, returns a default value.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<java.lang.String> getAreaName(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.model.LatLngBounds bounds) {
        return null;
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {com.google.android.gnd.system.AddressNotFoundException.class, java.io.IOException.class})
    private final java.lang.String getAreaNameInternal(com.google.android.gms.maps.model.LatLngBounds bounds) throws com.google.android.gnd.system.AddressNotFoundException, java.io.IOException {
        return null;
    }
}