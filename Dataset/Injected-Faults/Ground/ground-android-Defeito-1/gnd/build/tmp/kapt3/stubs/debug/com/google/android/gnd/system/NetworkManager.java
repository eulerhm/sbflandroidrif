package com.google.android.gnd.system;

import java.lang.System;

/**
 * Abstracts access to network state.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\t"}, d2 = {"Lcom/google/android/gnd/system/NetworkManager;", "", "()V", "isNetworkAvailable", "", "context", "Landroid/content/Context;", "requireActiveNetwork", "Lio/reactivex/Completable;", "gnd_debug"})
public final class NetworkManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.system.NetworkManager INSTANCE = null;
    
    private NetworkManager() {
        super();
    }
    
    /**
     * Returns true iff the device has internet connectivity, false otherwise.
     */
    @androidx.annotation.RequiresPermission(value = "android.permission.ACCESS_NETWORK_STATE")
    private final boolean isNetworkAvailable(android.content.Context context) {
        return false;
    }
    
    /**
     * Returns a Completable that completes immediately on subscribe if network is available, or fails
     * in error if not.
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final io.reactivex.Completable requireActiveNetwork(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}