package com.google.android.gnd;

import java.lang.System;

/**
 * Application configuration.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/google/android/gnd/Config;", "", "()V", "DB_NAME", "", "DB_VERSION", "", "FIRESTORE_LOGGING_ENABLED", "", "FIRESTORE_PERSISTENCE_ENABLED", "PHOTO_EXT", "SHARED_PREFS_MODE", "SHARED_PREFS_NAME", "gnd_debug"})
public final class Config {
    @org.jetbrains.annotations.NotNull()
    public static final com.google.android.gnd.Config INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SHARED_PREFS_NAME = "shared_prefs";
    public static final int SHARED_PREFS_MODE = android.content.Context.MODE_PRIVATE;
    public static final int DB_VERSION = 91;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DB_NAME = "gnd.db";
    public static final boolean FIRESTORE_PERSISTENCE_ENABLED = false;
    public static final boolean FIRESTORE_LOGGING_ENABLED = true;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PHOTO_EXT = ".jpg";
    
    private Config() {
        super();
    }
}