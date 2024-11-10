package org.wordpress.android.ui.sitecreation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fR\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0014\u0010\n\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/sitecreation/SiteCreationStepsProvider;", "", "siteIntentQuestionFeatureConfig", "Lorg/wordpress/android/util/config/SiteIntentQuestionFeatureConfig;", "siteNameFeatureConfig", "Lorg/wordpress/android/util/config/SiteNameFeatureConfig;", "(Lorg/wordpress/android/util/config/SiteIntentQuestionFeatureConfig;Lorg/wordpress/android/util/config/SiteNameFeatureConfig;)V", "isIntentsEnabled", "", "()Z", "isSiteNameEnabled", "getSteps", "", "Lorg/wordpress/android/ui/sitecreation/SiteCreationStep;", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class SiteCreationStepsProvider {
    private final org.wordpress.android.util.config.SiteIntentQuestionFeatureConfig siteIntentQuestionFeatureConfig = null;
    private final org.wordpress.android.util.config.SiteNameFeatureConfig siteNameFeatureConfig = null;
    
    @javax.inject.Inject()
    public SiteCreationStepsProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SiteIntentQuestionFeatureConfig siteIntentQuestionFeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.SiteNameFeatureConfig siteNameFeatureConfig) {
        super();
    }
    
    private final boolean isSiteNameEnabled() {
        return false;
    }
    
    private final boolean isIntentsEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.sitecreation.SiteCreationStep> getSteps() {
        return null;
    }
}