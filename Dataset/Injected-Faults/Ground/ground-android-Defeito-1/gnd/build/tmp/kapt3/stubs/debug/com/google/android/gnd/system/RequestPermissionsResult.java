package com.google.android.gnd.system;

import java.lang.System;

/**
 * Represents the arguments of an [Activity.onActivityResult] event.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0011"}, d2 = {"Lcom/google/android/gnd/system/RequestPermissionsResult;", "", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "permissionGrantResults", "", "getRequestCode", "()I", "isGranted", "", "permission", "gnd_debug"})
public final class RequestPermissionsResult {
    private final int requestCode = 0;
    private final java.util.Map<java.lang.String, java.lang.Integer> permissionGrantResults = null;
    
    public RequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
        super();
    }
    
    public final int getRequestCode() {
        return 0;
    }
    
    /**
     * Returns true iff the event indicated the specified permission is granted to the application.
     */
    public final boolean isGranted(@org.jetbrains.annotations.NotNull()
    java.lang.String permission) {
        return false;
    }
}