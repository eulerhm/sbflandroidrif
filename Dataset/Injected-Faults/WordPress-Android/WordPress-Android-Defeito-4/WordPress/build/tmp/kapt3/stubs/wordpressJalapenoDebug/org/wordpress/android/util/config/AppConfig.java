package org.wordpress.android.util.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u001c\u001a\u00020\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/util/config/AppConfig;", "", "remoteConfig", "Lorg/wordpress/android/util/config/RemoteConfig;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "manualFeatureConfig", "Lorg/wordpress/android/util/config/ManualFeatureConfig;", "(Lorg/wordpress/android/util/config/RemoteConfig;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/util/config/ManualFeatureConfig;)V", "experimentValues", "", "", "remoteConfigCheck", "error/NonExistentClass", "Lerror/NonExistentClass;", "buildFeatureState", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "feature", "Lorg/wordpress/android/util/config/FeatureConfig;", "featureState", "getCurrentVariant", "Lorg/wordpress/android/util/config/ExperimentConfig$Variant;", "experiment", "Lorg/wordpress/android/util/config/ExperimentConfig;", "init", "", "isEnabled", "", "refresh", "FeatureState", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class AppConfig {
    private final org.wordpress.android.util.config.RemoteConfig remoteConfig = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.util.config.ManualFeatureConfig manualFeatureConfig = null;
    
    /**
     * We need to keep the value of an already loaded feature flag to make sure the value is not changed while using the app.
     * We should only reload the flags when the application is created.
     */
    private final java.util.Map<java.lang.String, java.lang.String> experimentValues = null;
    private final error.NonExistentClass remoteConfigCheck = null;
    
    @javax.inject.Inject()
    public AppConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.RemoteConfig remoteConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.ManualFeatureConfig manualFeatureConfig) {
        super();
    }
    
    /**
     * This method initialized the config
     */
    public final void init() {
    }
    
    /**
     * This method triggers refresh of remote configuration.
     */
    public final void refresh() {
    }
    
    /**
     * Get the enabled state of a feature flag. If the flag is enabled in the BuildConfig file, it overrides the
     * remote value. The correct approach is to disable a feature flag for a release version and only enable it remotely.
     * Once the feature is ready to be fully released, we can enable the BuildConfig value.
     * @param feature feature which we're checking remotely
     */
    public final boolean isEnabled(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.FeatureConfig feature) {
        return false;
    }
    
    /**
     * Get the enabled flag and the source where it came from.
     * @param feature feature we're checking remotely
     */
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.AppConfig.FeatureState featureState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.FeatureConfig feature) {
        return null;
    }
    
    private final org.wordpress.android.util.config.AppConfig.FeatureState buildFeatureState(org.wordpress.android.util.config.FeatureConfig feature) {
        return null;
    }
    
    /**
     * Get the currently selected variant for a given experiment. This function returns null if there is no variant
     * for the current user (and the user is in the control group).
     */
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.ExperimentConfig.Variant getCurrentVariant(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.ExperimentConfig experiment) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\n\u000b\f\r\u000eB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0001\u0005\u000f\u0010\u0011\u0012\u0013\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "", "isEnabled", "", "name", "", "(ZLjava/lang/String;)V", "()Z", "getName", "()Ljava/lang/String;", "BuildConfigValue", "DefaultValue", "ManuallyOverriden", "RemoteValue", "StaticValue", "Lorg/wordpress/android/util/config/AppConfig$FeatureState$ManuallyOverriden;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState$BuildConfigValue;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState$RemoteValue;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState$StaticValue;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState$DefaultValue;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FeatureState {
        private final boolean isEnabled = false;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        
        private FeatureState(boolean isEnabled, java.lang.String name) {
            super();
        }
        
        public boolean isEnabled() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/config/AppConfig$FeatureState$ManuallyOverriden;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "isEnabled", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ManuallyOverriden extends org.wordpress.android.util.config.AppConfig.FeatureState {
            private final boolean isEnabled = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.config.AppConfig.FeatureState.ManuallyOverriden copy(boolean isEnabled) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public ManuallyOverriden(boolean isEnabled) {
                super(false, null);
            }
            
            public final boolean component1() {
                return false;
            }
            
            @java.lang.Override()
            public boolean isEnabled() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/config/AppConfig$FeatureState$BuildConfigValue;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "isEnabled", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class BuildConfigValue extends org.wordpress.android.util.config.AppConfig.FeatureState {
            private final boolean isEnabled = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.config.AppConfig.FeatureState.BuildConfigValue copy(boolean isEnabled) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public BuildConfigValue(boolean isEnabled) {
                super(false, null);
            }
            
            public final boolean component1() {
                return false;
            }
            
            @java.lang.Override()
            public boolean isEnabled() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/config/AppConfig$FeatureState$RemoteValue;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "isEnabled", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RemoteValue extends org.wordpress.android.util.config.AppConfig.FeatureState {
            private final boolean isEnabled = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.config.AppConfig.FeatureState.RemoteValue copy(boolean isEnabled) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public RemoteValue(boolean isEnabled) {
                super(false, null);
            }
            
            public final boolean component1() {
                return false;
            }
            
            @java.lang.Override()
            public boolean isEnabled() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/config/AppConfig$FeatureState$StaticValue;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "isEnabled", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class StaticValue extends org.wordpress.android.util.config.AppConfig.FeatureState {
            private final boolean isEnabled = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.config.AppConfig.FeatureState.StaticValue copy(boolean isEnabled) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public StaticValue(boolean isEnabled) {
                super(false, null);
            }
            
            public final boolean component1() {
                return false;
            }
            
            @java.lang.Override()
            public boolean isEnabled() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/config/AppConfig$FeatureState$DefaultValue;", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "isEnabled", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class DefaultValue extends org.wordpress.android.util.config.AppConfig.FeatureState {
            private final boolean isEnabled = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.util.config.AppConfig.FeatureState.DefaultValue copy(boolean isEnabled) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public DefaultValue(boolean isEnabled) {
                super(false, null);
            }
            
            public final boolean component1() {
                return false;
            }
            
            @java.lang.Override()
            public boolean isEnabled() {
                return false;
            }
        }
    }
}