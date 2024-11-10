package org.wordpress.android.testing;

import java.lang.System;

/**
 * Annotate a class with [OpenForTesting] if you want it to be extendable in debug builds.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0097\u0002\u0018\u00002\u00020\u0001B\u0000\u00a8\u0006\u0002"}, d2 = {"Lorg/wordpress/android/testing/OpenForTesting;", "", "org.wordpress.android_wordpressJalapenoDebug"})
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@kotlin.annotation.Target(allowedTargets = {kotlin.annotation.AnnotationTarget.CLASS})
@OpenClassAnnotation()
public abstract @interface OpenForTesting {
}