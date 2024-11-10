package org.wordpress.android;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/InitializationRule;", "Lorg/junit/rules/TestRule;", "()V", "apply", "Lorg/junit/runners/model/Statement;", "base", "description", "Lorg/junit/runner/Description;", "AppInitializerEntryPoint", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InitializationRule implements org.junit.rules.TestRule {
    
    public InitializationRule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.junit.runners.model.Statement apply(@org.jetbrains.annotations.Nullable()
    org.junit.runners.model.Statement base, @org.jetbrains.annotations.Nullable()
    org.junit.runner.Description description) {
        return null;
    }
    
    @dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
    @dagger.hilt.EntryPoint()
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/InitializationRule$AppInitializerEntryPoint;", "", "appInitializer", "Lorg/wordpress/android/AppInitializer;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract interface AppInitializerEntryPoint {
        
        @org.jetbrains.annotations.NotNull()
        public abstract org.wordpress.android.AppInitializer appInitializer();
    }
}