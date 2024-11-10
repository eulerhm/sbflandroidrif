package org.wordpress.android.util.config;

import java.lang.System;

/**
 * This class represents an abstract experiment configuration. An experiment has a list of variants.
 * To add an experiment don't forget to update the `remote_config_defaults.xml` file.
 * @param appConfig class that loads the feature configuration
 * @param remoteField is the key of the feature flag in the remote config file
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/util/config/ExperimentConfig;", "", "appConfig", "Lorg/wordpress/android/util/config/AppConfig;", "remoteField", "", "(Lorg/wordpress/android/util/config/AppConfig;Ljava/lang/String;)V", "getRemoteField", "()Ljava/lang/String;", "variants", "", "Lorg/wordpress/android/util/config/ExperimentConfig$Variant;", "getVariants", "()Ljava/util/List;", "getVariant", "isInVariant", "", "variant", "Variant", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ExperimentConfig {
    private final org.wordpress.android.util.config.AppConfig appConfig = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String remoteField = null;
    
    public ExperimentConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.AppConfig appConfig, @org.jetbrains.annotations.NotNull()
    java.lang.String remoteField) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRemoteField() {
        return null;
    }
    
    /**
     * List of all the variants in an experiment
     */
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<org.wordpress.android.util.config.ExperimentConfig.Variant> getVariants();
    
    /**
     * Gets the current variant for the experiment
     */
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.ExperimentConfig.Variant getVariant() {
        return null;
    }
    
    /**
     * Returns true if the variant is the current variant for the experiment
     */
    public final boolean isInVariant(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.ExperimentConfig.Variant variant) {
        return false;
    }
    
    /**
     * This class represents a single variant of the experiment
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/util/config/ExperimentConfig$Variant;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Variant {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        /**
         * This class represents a single variant of the experiment
         */
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.config.ExperimentConfig.Variant copy(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            return null;
        }
        
        /**
         * This class represents a single variant of the experiment
         */
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        /**
         * This class represents a single variant of the experiment
         */
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        /**
         * This class represents a single variant of the experiment
         */
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public Variant(@org.jetbrains.annotations.NotNull()
        java.lang.String value) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
}