package com.google.android.gnd.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/google/android/gnd/ui/MarkerIconFactory;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getMarkerBitmap", "Landroid/graphics/Bitmap;", "color", "", "currentZoomLevel", "", "getMarkerIcon", "Lcom/google/android/gms/maps/model/BitmapDescriptor;", "gnd_debug"})
@javax.inject.Singleton()
public final class MarkerIconFactory {
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public MarkerIconFactory(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap getMarkerBitmap(int color, float currentZoomLevel) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.maps.model.BitmapDescriptor getMarkerIcon(@androidx.annotation.ColorInt()
    int color, float currentZoomLevel) {
        return null;
    }
}