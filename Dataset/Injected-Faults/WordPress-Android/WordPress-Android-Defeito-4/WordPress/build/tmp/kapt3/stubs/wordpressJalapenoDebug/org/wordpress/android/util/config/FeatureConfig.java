package org.wordpress.android.util.config;

import java.lang.System;

/**
 * A class that represents a feature configuration which enables the feature to be remotely turned on or off.
 * To add a feature don't forget to update the remote_config_defaults.xml file.
 * @param appConfig class that loads the feature configuration
 * @param buildConfigValue is the field in the BuildConfig file. This flag overrides the remote value. Use this field
 * to enable the feature to a certain build (debug, test build) so it doesn't have to rely on remote configuration.
 * @param remoteField is the key of the feature flag in the remote config file, only set this field when there is a
 * remote flag available
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u00070\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/util/config/FeatureConfig;", "", "appConfig", "Lorg/wordpress/android/util/config/AppConfig;", "buildConfigValue", "", "remoteField", "", "(Lorg/wordpress/android/util/config/AppConfig;ZLjava/lang/String;)V", "getBuildConfigValue", "()Z", "getRemoteField", "()Ljava/lang/String;", "featureState", "Lorg/wordpress/android/util/config/AppConfig$FeatureState;", "isEnabled", "name", "kotlin.jvm.PlatformType", "org.wordpress.android_wordpressJalapenoDebug"})
public class FeatureConfig {
    private final org.wordpress.android.util.config.AppConfig appConfig = null;
    private final boolean buildConfigValue = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String remoteField = null;
    
    public FeatureConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.AppConfig appConfig, boolean buildConfigValue, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteField) {
        super();
    }
    
    public final boolean getBuildConfigValue() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRemoteField() {
        return null;
    }
    
    public boolean isEnabled() {
        return false;
    }
    
    public java.lang.String name() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.AppConfig.FeatureState featureState() {
        return null;
    }
}