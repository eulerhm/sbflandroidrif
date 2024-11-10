package org.wordpress.android.ui.uploads;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\b\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/uploads/ProgressEvent;", "", "media", "Lorg/wordpress/android/fluxc/model/MediaModel;", "progress", "", "(Lorg/wordpress/android/fluxc/model/MediaModel;F)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ProgressEvent {
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmField()
    public final org.wordpress.android.fluxc.model.MediaModel media = null;
    @kotlin.jvm.JvmField()
    public final float progress = 0.0F;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.ProgressEvent copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel media, float progress) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public ProgressEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel media, float progress) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.model.MediaModel component1() {
        return null;
    }
    
    public final float component2() {
        return 0.0F;
    }
}