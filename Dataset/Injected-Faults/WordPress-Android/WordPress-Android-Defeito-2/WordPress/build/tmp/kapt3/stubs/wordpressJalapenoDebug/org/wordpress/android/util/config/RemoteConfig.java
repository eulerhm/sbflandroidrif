package org.wordpress.android.util.config;

import java.lang.System;

/**
 * Do not use this class outside of this package. Use [AppConfig] instead
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0006J\u001c\u0010\r\u001a\u00020\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0006\u0010\u0011\u001a\u00020\n\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/util/config/RemoteConfig;", "", "()V", "getFeatureState", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "remoteField", "", "getString", "field", "init", "", "isEnabled", "", "onComplete", "task", "Lcom/google/android/gms/tasks/Task;", "functionName", "refresh", "org.wordpress.android_wordpressJalapenoDebug"})
public final class RemoteConfig {
    
    @javax.inject.Inject()
    public RemoteConfig() {
        super();
    }
    
    public final void init() {
    }
    
    public final void refresh() {
    }
    
    private final void onComplete(com.google.android.gms.tasks.Task<?> task, java.lang.String functionName) {
    }
    
    public final boolean isEnabled(@org.jetbrains.annotations.NotNull()
    java.lang.String field) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String field) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.AppConfig.FeatureState getFeatureState(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteField) {
        return null;
    }
}