package org.owntracks.android.geocoding;

import android.content.Context;
import android.location.Address;
import org.owntracks.android.injection.qualifier.AppContext;
import timber.log.Timber;
import java.math.BigDecimal;
import java.util.*;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/owntracks/android/geocoding/GoogleGeocoder;", "Lorg/owntracks/android/geocoding/CachingGeocoder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "geocoder", "Landroid/location/Geocoder;", "doLookup", "", "latitude", "Ljava/math/BigDecimal;", "longitude", "geocoderAvailable", "", "reverse", "", "app_debug"})
public final class GoogleGeocoder extends org.owntracks.android.geocoding.CachingGeocoder {
    private final android.location.Geocoder geocoder = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String reverse(double latitude, double longitude) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected java.lang.String doLookup(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal latitude, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal longitude) {
        return null;
    }
    
    private final boolean geocoderAvailable() {
        return false;
    }
    
    public GoogleGeocoder(@org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.qualifier.AppContext()
    android.content.Context context) {
        super();
    }
}