package org.wordpress.android.ui.mysite.tabs;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J\u0014\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\u0006\u0010\u0019\u001a\u00020\u000eJ\u0006\u0010\u001a\u001a\u00020\u000eJ\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0011H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;", "", "mySiteDefaultTabExperimentFeatureConfig", "Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentFeatureConfig;", "mySiteDefaultTabExperimentVariationDashboardFeatureConfig", "Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentVariationDashboardFeatureConfig;", "mySiteDashboardTabsFeatureConfig", "Lorg/wordpress/android/util/config/MySiteDashboardTabsFeatureConfig;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentFeatureConfig;Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentVariationDashboardFeatureConfig;Lorg/wordpress/android/util/config/MySiteDashboardTabsFeatureConfig;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "isMySiteDashboardTabsFeatureConfigEnabled", "", "isMySiteDefaultTabExperimentFeatureConfigEnabled", "changeExperimentVariantAssignmentIfNeeded", "", "toVariant", "", "checkAndSetTrackingPropertiesIfNeeded", "checkAndSetVariantIfNeeded", "getVariantMapForTracking", "", "getVariantTrackingLabel", "isExperimentRunning", "isVariantAssigned", "setExperimentVariant", "variant", "setVariantAssigned", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteDefaultTabExperiment {
    private final org.wordpress.android.util.config.MySiteDefaultTabExperimentVariationDashboardFeatureConfig mySiteDefaultTabExperimentVariationDashboardFeatureConfig = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final boolean isMySiteDashboardTabsFeatureConfigEnabled = false;
    private final boolean isMySiteDefaultTabExperimentFeatureConfigEnabled = false;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment.Companion Companion = null;
    private static final java.lang.String DEFAULT_TAB_EXPERIMENT = "default_tab_experiment";
    private static final java.lang.String NONEXISTENT = "nonexistent";
    
    @javax.inject.Inject()
    public MySiteDefaultTabExperiment(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.MySiteDefaultTabExperimentFeatureConfig mySiteDefaultTabExperimentFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.MySiteDefaultTabExperimentVariationDashboardFeatureConfig mySiteDefaultTabExperimentVariationDashboardFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig mySiteDashboardTabsFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    public final void checkAndSetVariantIfNeeded() {
    }
    
    public final void checkAndSetTrackingPropertiesIfNeeded() {
    }
    
    public final void changeExperimentVariantAssignmentIfNeeded(@org.jetbrains.annotations.NotNull()
    java.lang.String toVariant) {
    }
    
    public final boolean isExperimentRunning() {
        return false;
    }
    
    public final boolean isVariantAssigned() {
        return false;
    }
    
    private final void setVariantAssigned() {
    }
    
    private final void setExperimentVariant(java.lang.String variant) {
    }
    
    private final java.util.Map<java.lang.String, java.lang.String> getVariantMapForTracking() {
        return null;
    }
    
    private final java.lang.String getVariantTrackingLabel() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment$Companion;", "", "()V", "DEFAULT_TAB_EXPERIMENT", "", "NONEXISTENT", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}