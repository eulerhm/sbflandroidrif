package org.owntracks.android.geocoding;

import androidx.collection.LruCache;
import timber.log.Timber;
import java.math.BigDecimal;
import java.math.RoundingMode;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H$J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\fH\u0016R(\u0010\u0003\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/owntracks/android/geocoding/CachingGeocoder;", "Lorg/owntracks/android/geocoding/Geocoder;", "()V", "cache", "Landroidx/collection/LruCache;", "Lkotlin/Pair;", "Ljava/math/BigDecimal;", "", "doLookup", "latitude", "longitude", "reverse", "", "app_debug"})
public abstract class CachingGeocoder implements org.owntracks.android.geocoding.Geocoder {
    private final androidx.collection.LruCache<kotlin.Pair<java.math.BigDecimal, java.math.BigDecimal>, java.lang.String> cache = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String reverse(double latitude, double longitude) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    protected abstract java.lang.String doLookup(@org.jetbrains.annotations.NotNull()
    java.math.BigDecimal latitude, @org.jetbrains.annotations.NotNull()
    java.math.BigDecimal longitude);
    
    public CachingGeocoder() {
        super();
    }
}