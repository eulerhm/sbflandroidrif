package org.wordpress.android.util.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\bH\u0002J\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\bJ\f\u0010\u0012\u001a\u00020\n*\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/util/config/ManualFeatureConfig;", "", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "buildConfigWrapper", "Lorg/wordpress/android/util/BuildConfigWrapper;", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/BuildConfigWrapper;)V", "hasManualSetup", "", "featureKey", "", "feature", "Lorg/wordpress/android/util/config/FeatureConfig;", "isConfigEnabled", "isManuallyEnabled", "setManuallyEnabled", "", "enabled", "toFeatureKey", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ManualFeatureConfig {
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper = null;
    
    @javax.inject.Inject()
    public ManualFeatureConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.BuildConfigWrapper buildConfigWrapper) {
        super();
    }
    
    public final boolean hasManualSetup(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.FeatureConfig feature) {
        return false;
    }
    
    public final boolean hasManualSetup(@org.jetbrains.annotations.NotNull()
    java.lang.String featureKey) {
        return false;
    }
    
    public final boolean isManuallyEnabled(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.FeatureConfig feature) {
        return false;
    }
    
    public final boolean isManuallyEnabled(@org.jetbrains.annotations.NotNull()
    java.lang.String featureKey) {
        return false;
    }
    
    public final void setManuallyEnabled(@org.jetbrains.annotations.NotNull()
    java.lang.String featureKey, boolean enabled) {
    }
    
    private final boolean isConfigEnabled() {
        return false;
    }
    
    private final java.lang.String toFeatureKey(org.wordpress.android.util.config.FeatureConfig $this$toFeatureKey) {
        return null;
    }
}