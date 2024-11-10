package com.google.android.gnd.ui.map;

import java.lang.System;

/**
 * MapType refers to the basemap shown below map features and offline satellite imagery. It's called
 * "map styles" in Mapbox and "basemaps" in Leaflet.
 */
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u001c"}, d2 = {"Lcom/google/android/gnd/ui/map/MapType;", "Landroid/os/Parcelable;", "type", "", "labelId", "imageId", "(III)V", "getImageId", "()I", "getLabelId", "getType", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "gnd_debug"})
public final class MapType implements android.os.Parcelable {
    private final int type = 0;
    @androidx.annotation.StringRes()
    private final int labelId = 0;
    @androidx.annotation.DrawableRes()
    private final int imageId = 0;
    public static final android.os.Parcelable.Creator<com.google.android.gnd.ui.map.MapType> CREATOR = null;
    
    /**
     * MapType refers to the basemap shown below map features and offline satellite imagery. It's called
     * "map styles" in Mapbox and "basemaps" in Leaflet.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.ui.map.MapType copy(int type, @androidx.annotation.StringRes()
    int labelId, @androidx.annotation.DrawableRes()
    int imageId) {
        return null;
    }
    
    /**
     * MapType refers to the basemap shown below map features and offline satellite imagery. It's called
     * "map styles" in Mapbox and "basemaps" in Leaflet.
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    /**
     * MapType refers to the basemap shown below map features and offline satellite imagery. It's called
     * "map styles" in Mapbox and "basemaps" in Leaflet.
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * MapType refers to the basemap shown below map features and offline satellite imagery. It's called
     * "map styles" in Mapbox and "basemaps" in Leaflet.
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public MapType(int type, @androidx.annotation.StringRes()
    int labelId, @androidx.annotation.DrawableRes()
    int imageId) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getType() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getLabelId() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getImageId() {
        return 0;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<com.google.android.gnd.ui.map.MapType> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.google.android.gnd.ui.map.MapType createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.google.android.gnd.ui.map.MapType[] newArray(int size) {
            return null;
        }
    }
}