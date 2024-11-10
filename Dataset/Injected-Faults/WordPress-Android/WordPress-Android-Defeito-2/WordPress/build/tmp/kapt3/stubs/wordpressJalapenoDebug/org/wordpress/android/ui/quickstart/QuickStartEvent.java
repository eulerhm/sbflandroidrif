package org.wordpress.android.ui.quickstart;

import java.lang.System;

/**
 * Container for passing around QuickStartTask to destinations and retaining it there
 */
@android.annotation.SuppressLint(value = {"ParcelCreator"})
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0012\u0012\u000b\u0010\u0002\u001a\u00070\u0003\u00a2\u0006\u0002\b\u0004\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\b\u001a\u00070\u0003\u00a2\u0006\u0002\b\u0004H\u00c6\u0003J\u0018\u0010\t\u001a\u00020\u00002\r\b\u0002\u0010\u0002\u001a\u00070\u0003\u00a2\u0006\u0002\b\u0004H\u00c6\u0001J\t\u0010\n\u001a\u00020\u000bH\u00d6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u000bH\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bH\u00d6\u0001R\u0016\u0010\u0002\u001a\u00070\u0003\u00a2\u0006\u0002\b\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartEvent;", "Landroid/os/Parcelable;", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "Lkotlinx/parcelize/RawValue;", "(Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;)V", "getTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartEvent implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.quickstart.QuickStartEvent.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY = "quick_start_event";
    public static final android.os.Parcelable.Creator<org.wordpress.android.ui.quickstart.QuickStartEvent> CREATOR = null;
    
    /**
     * Container for passing around QuickStartTask to destinations and retaining it there
     */
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.quickstart.QuickStartEvent copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        return null;
    }
    
    /**
     * Container for passing around QuickStartTask to destinations and retaining it there
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Container for passing around QuickStartTask to destinations and retaining it there
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Container for passing around QuickStartTask to destinations and retaining it there
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public QuickStartEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getTask() {
        return null;
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
    public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.quickstart.QuickStartEvent> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.quickstart.QuickStartEvent createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final org.wordpress.android.ui.quickstart.QuickStartEvent[] newArray(int size) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartEvent$Companion;", "", "()V", "KEY", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}