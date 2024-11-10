package org.wordpress.android.util.config;

import java.lang.System;

/**
 * An example of how to create and use an experiment.
 * The experiment defines a list of variants.
 */
@org.wordpress.android.annotation.Experiment(remoteField = "example_experiment_remote_field", defaultVariant = "control_group")
@java.lang.SuppressWarnings(value = {"Unused"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/util/config/ExampleExperimentConfig;", "Lorg/wordpress/android/util/config/ExperimentConfig;", "appConfig", "Lorg/wordpress/android/util/config/AppConfig;", "(Lorg/wordpress/android/util/config/AppConfig;)V", "controlGroup", "Lorg/wordpress/android/util/config/ExperimentConfig$Variant;", "variantA", "variantB", "variants", "", "getVariants", "()Ljava/util/List;", "isVariantA", "", "isVariantB", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ExampleExperimentConfig extends org.wordpress.android.util.config.ExperimentConfig {
    private final org.wordpress.android.util.config.ExperimentConfig.Variant variantA = null;
    private final org.wordpress.android.util.config.ExperimentConfig.Variant variantB = null;
    private final org.wordpress.android.util.config.ExperimentConfig.Variant controlGroup = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.util.config.ExampleExperimentConfig.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXAMPLE_EXPERIMENT_REMOTE_FIELD = "example_experiment_remote_field";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VARIANT_A = "variant_A";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VARIANT_B = "variant_B";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CONTROL_GROUP = "control_group";
    
    @javax.inject.Inject()
    public ExampleExperimentConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.AppConfig appConfig) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<org.wordpress.android.util.config.ExperimentConfig.Variant> getVariants() {
        return null;
    }
    
    /**
     * Define the methods you need
     */
    public final boolean isVariantA() {
        return false;
    }
    
    public final boolean isVariantB() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/util/config/ExampleExperimentConfig$Companion;", "", "()V", "CONTROL_GROUP", "", "EXAMPLE_EXPERIMENT_REMOTE_FIELD", "VARIANT_A", "VARIANT_B", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}