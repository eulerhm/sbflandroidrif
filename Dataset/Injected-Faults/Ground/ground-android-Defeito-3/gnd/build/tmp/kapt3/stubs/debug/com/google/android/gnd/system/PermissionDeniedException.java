package com.google.android.gnd.system;

import java.lang.System;

/**
 * Indicates a request to grant the app permissions was denied. More specifically, it indicates
 * the requested permission was not in the list of granted permissions in the system's response.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/google/android/gnd/system/PermissionDeniedException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "gnd_debug"})
public final class PermissionDeniedException extends java.lang.Exception {
    
    public PermissionDeniedException() {
        super();
    }
}