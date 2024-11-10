package org.wordpress.android.util.analytics;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ&\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007J\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010J(\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0010\u0010\u0005\u001a\f\u0012\u0004\u0012\u00020\u0007\u0012\u0002\b\u00030\u00062\u0006\u0010\u0011\u001a\u00020\u0012J$\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006J4\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006H\u0007J \u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0017\u001a\u0016\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00070\u0007\u0012\u0004\u0012\u00020\u00190\u0006*\u00020\u0012H\u0002\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "", "()V", "setInjectExperimentProperties", "", "properties", "", "", "track", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "errorContext", "errorType", "errorDescription", "remoteField", "featureState", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "feature", "Lorg/wordpress/android/util/config/FeatureConfig;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "experimentConfig", "Lorg/wordpress/android/util/config/ExperimentConfig;", "toParams", "kotlin.jvm.PlatformType", "", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class AnalyticsTrackerWrapper {
    
    @javax.inject.Inject()
    public AnalyticsTrackerWrapper() {
        super();
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String remoteField, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.AppConfig.FeatureState featureState) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.ExperimentConfig experimentConfig) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ?> properties, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.FeatureConfig feature) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.FeatureConfig feature) {
    }
    
    /**
     * A convenience method for logging an error event with some additional meta data.
     * @param stat The stat to track.
     * @param errorContext A string providing additional context (if any) about the error.
     * @param errorType The type of error.
     * @param errorDescription The error text or other description.
     */
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String errorContext, @org.jetbrains.annotations.NotNull()
    java.lang.String errorType, @org.jetbrains.annotations.NotNull()
    java.lang.String errorDescription) {
    }
    
    public final void setInjectExperimentProperties(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    private final java.util.Map<java.lang.String, java.lang.Boolean> toParams(org.wordpress.android.util.config.FeatureConfig $this$toParams) {
        return null;
    }
}