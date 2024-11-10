package com.google.android.gnd.system;

import java.lang.System;

/**
 * Provides access to obtain and check the app's permissions.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00070\b\u00a2\u0006\u0002\b\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0013\u0010\u000e\u001a\u00070\b\u00a2\u0006\u0002\b\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/google/android/gnd/system/PermissionsManager;", "", "context", "Landroid/content/Context;", "activityStreams", "Lcom/google/android/gnd/system/ActivityStreams;", "(Landroid/content/Context;Lcom/google/android/gnd/system/ActivityStreams;)V", "getPermissionsResult", "Lio/reactivex/Completable;", "Lcom/google/android/gnd/rx/annotations/Cold;", "permission", "", "isGranted", "", "obtainPermission", "requestPermission", "Companion", "gnd_debug"})
@javax.inject.Singleton()
public final class PermissionsManager {
    private final android.content.Context context = null;
    private final com.google.android.gnd.system.ActivityStreams activityStreams = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.system.PermissionsManager.Companion Companion = null;
    @kotlin.jvm.JvmField()
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    
    @javax.inject.Inject()
    public PermissionsManager(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.android.gnd.system.ActivityStreams activityStreams) {
        super();
    }
    
    /**
     * Asynchronously requests the app be granted the specified permission. If the permission has
     * already been granted, completes immediately, otherwise completes once the next permissions
     * result is received.
     */
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Completable obtainPermission(@org.jetbrains.annotations.NotNull()
    java.lang.String permission) {
        return null;
    }
    
    /**
     * Sends the system request that the app be granted the specified permission. Returns `true`
     * if the permission was already granted.
     */
    private final boolean requestPermission(java.lang.String permission) {
        return false;
    }
    
    /**
     * Returns `true` iff the app has been granted the specified permission.
     */
    private final boolean isGranted(java.lang.String permission) {
        return false;
    }
    
    /**
     * Returns a [Completable] that completes once the specified permission is granted or
     * terminates with error [PermissionDeniedException] if the requested permission was denied.
     */
    private final io.reactivex.Completable getPermissionsResult(java.lang.String permission) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/google/android/gnd/system/PermissionsManager$Companion;", "", "()V", "PERMISSIONS_REQUEST_CODE", "", "gnd_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}