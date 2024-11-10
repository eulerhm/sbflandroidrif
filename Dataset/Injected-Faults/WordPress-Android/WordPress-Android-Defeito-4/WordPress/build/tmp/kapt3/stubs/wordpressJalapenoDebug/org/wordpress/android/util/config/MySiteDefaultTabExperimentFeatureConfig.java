package org.wordpress.android.util.config;

import java.lang.System;

/**
 * Configuration of the 'My Site - Default Tab Experiment' - will guard the experiment
 */
@org.wordpress.android.annotation.Feature(remoteField = "my_site_default_tab_experiment", defaultValue = false)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentFeatureConfig;", "Lorg/wordpress/android/util/config/FeatureConfig;", "appConfig", "Lorg/wordpress/android/util/config/AppConfig;", "(Lorg/wordpress/android/util/config/AppConfig;)V", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteDefaultTabExperimentFeatureConfig extends org.wordpress.android.util.config.FeatureConfig {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.config.MySiteDefaultTabExperimentFeatureConfig.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MY_SITE_DEFAULT_TAB_EXPERIMENT = "my_site_default_tab_experiment";
    
    @javax.inject.Inject()
    public MySiteDefaultTabExperimentFeatureConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.AppConfig appConfig) {
        super(null, false, null);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/util/config/MySiteDefaultTabExperimentFeatureConfig$Companion;", "", "()V", "MY_SITE_DEFAULT_TAB_EXPERIMENT", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}