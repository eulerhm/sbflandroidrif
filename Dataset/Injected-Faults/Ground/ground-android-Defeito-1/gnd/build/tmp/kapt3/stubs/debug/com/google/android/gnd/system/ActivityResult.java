package com.google.android.gnd.system;

import java.lang.System;

/**
 * Represents the arguments of an [Activity.onActivityResult] event.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\u0006\u0010\u0015\u001a\u00020\u0012J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0018"}, d2 = {"Lcom/google/android/gnd/system/ActivityResult;", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "(IILandroid/content/Intent;)V", "getData", "()Landroid/content/Intent;", "getRequestCode", "()I", "getResultCode", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "isOk", "toString", "", "gnd_debug"})
public final class ActivityResult {
    private final int requestCode = 0;
    private final int resultCode = 0;
    @org.jetbrains.annotations.Nullable()
    private final android.content.Intent data = null;
    
    /**
     * Represents the arguments of an [Activity.onActivityResult] event.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gnd.system.ActivityResult copy(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
        return null;
    }
    
    /**
     * Represents the arguments of an [Activity.onActivityResult] event.
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Represents the arguments of an [Activity.onActivityResult] event.
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Represents the arguments of an [Activity.onActivityResult] event.
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public ActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getRequestCode() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getResultCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.Intent component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.Intent getData() {
        return null;
    }
    
    /**
     * Returns true iff the system provided a result of [Activity.RESULT_OK] to the `onActivityResult` callback.
     */
    public final boolean isOk() {
        return false;
    }
}