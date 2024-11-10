package org.wordpress.android.util.config;

import java.lang.System;

/**
 * Configuration of the 'My Site - Default tab Experiment' -
 * Identifies if this is variant Dashboard with a isEnabled=true, and indicates site_menu isEnabled=false
 */
@org.wordpress.android.annotation.Feature(remoteField = "my_site_default_tab_experiment_variant_dashboard", defaultValue = false)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentVariationDashboardFeatureConfig;", "Lorg/wordpress/android/util/config/FeatureConfig;", "appConfig", "Lorg/wordpress/android/util/config/AppConfig;", "(Lorg/wordpress/android/util/config/AppConfig;)V", "isDashboardVariant", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteDefaultTabExperimentVariationDashboardFeatureConfig extends org.wordpress.android.util.config.FeatureConfig {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.config.MySiteDefaultTabExperimentVariationDashboardFeatureConfig.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MY_SITE_DEFAULT_TAB_EXPERIMENT_VARIANT_DASHBOARD = "my_site_default_tab_experiment_variant_dashboard";
    
    @javax.inject.Inject()
    public MySiteDefaultTabExperimentVariationDashboardFeatureConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.AppConfig appConfig) {
        super(null, false, null);
    }
    
    public final boolean isDashboardVariant() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentVariationDashboardFeatureConfig$Companion;", "", "()V", "MY_SITE_DEFAULT_TAB_EXPERIMENT_VARIANT_DASHBOARD", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}