package org.wordpress.android.processor;

import java.lang.System;

@com.google.auto.service.AutoService(value = {javax.annotation.processing.Processor.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u0016\u0010\b\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u0002J\u001c\u0010\n\u001a\u00020\u00042\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\fH\u0002J$\u0010\r\u001a\u00020\u000e2\u0010\u0010\u000f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0011\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/processor/RemoteConfigProcessor;", "Ljavax/annotation/processing/AbstractProcessor;", "()V", "generateFeaturesInDevelopment", "", "remoteFeatureNames", "", "", "generateRemoteConfigCheck", "Lcom/squareup/kotlinpoet/TypeName;", "generateRemoteConfigDefaults", "remoteConfigDefaults", "", "process", "", "p0", "", "Ljavax/lang/model/element/TypeElement;", "roundEnvironment", "Ljavax/annotation/processing/RoundEnvironment;", "Companion", "processors"})
@javax.annotation.processing.SupportedAnnotationTypes(value = {"org.wordpress.android.annotation.Experiment", "org.wordpress.android.annotation.Feature", "org.wordpress.android.annotation.FeatureInDevelopment"})
@javax.annotation.processing.SupportedSourceVersion(value = javax.lang.model.SourceVersion.RELEASE_8)
public final class RemoteConfigProcessor extends javax.annotation.processing.AbstractProcessor {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.processor.RemoteConfigProcessor.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated";
    
    public RemoteConfigProcessor() {
        super();
    }
    
    @java.lang.Override()
    public boolean process(@org.jetbrains.annotations.Nullable()
    java.util.Set<? extends javax.lang.model.element.TypeElement> p0, @org.jetbrains.annotations.Nullable()
    javax.annotation.processing.RoundEnvironment roundEnvironment) {
        return false;
    }
    
    private final void generateRemoteConfigDefaults(java.util.Map<java.lang.String, java.lang.String> remoteConfigDefaults) {
    }
    
    private final void generateRemoteConfigCheck(java.util.List<? extends com.squareup.kotlinpoet.TypeName> remoteFeatureNames) {
    }
    
    private final void generateFeaturesInDevelopment(java.util.List<java.lang.String> remoteFeatureNames) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/processor/RemoteConfigProcessor$Companion;", "", "()V", "KAPT_KOTLIN_GENERATED_OPTION_NAME", "", "processors"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}