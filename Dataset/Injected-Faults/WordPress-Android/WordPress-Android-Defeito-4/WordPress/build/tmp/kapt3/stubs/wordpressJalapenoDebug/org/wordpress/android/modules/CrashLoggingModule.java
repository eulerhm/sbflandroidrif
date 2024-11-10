package org.wordpress.android.modules;

import java.lang.System;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/modules/CrashLoggingModule;", "", "()V", "bindCrashLoggingDataProvider", "Lcom/automattic/android/tracks/crashlogging/CrashLoggingDataProvider;", "dataProvider", "Lorg/wordpress/android/util/crashlogging/WPCrashLoggingDataProvider;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Module()
public abstract class CrashLoggingModule {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.modules.CrashLoggingModule.Companion Companion = null;
    
    public CrashLoggingModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract com.automattic.android.tracks.crashlogging.CrashLoggingDataProvider bindCrashLoggingDataProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.crashlogging.WPCrashLoggingDataProvider dataProvider);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/modules/CrashLoggingModule$Companion;", "", "()V", "provideCrashLogging", "Lcom/automattic/android/tracks/crashlogging/CrashLogging;", "context", "Landroid/content/Context;", "crashLoggingDataProvider", "Lcom/automattic/android/tracks/crashlogging/CrashLoggingDataProvider;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final com.automattic.android.tracks.crashlogging.CrashLogging provideCrashLogging(@org.jetbrains.annotations.NotNull()
        @dagger.hilt.android.qualifiers.ApplicationContext()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        com.automattic.android.tracks.crashlogging.CrashLoggingDataProvider crashLoggingDataProvider) {
            return null;
        }
    }
}