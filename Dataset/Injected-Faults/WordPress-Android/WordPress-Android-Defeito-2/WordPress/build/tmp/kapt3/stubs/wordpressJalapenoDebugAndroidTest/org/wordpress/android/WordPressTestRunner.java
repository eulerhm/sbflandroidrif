package org.wordpress.android;

import java.lang.System;

/**
 * Custom AndroidJUnitRunner that replaces the original application with [WordPressTest_Application].
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/WordPressTestRunner;", "Landroidx/test/runner/AndroidJUnitRunner;", "()V", "newApplication", "Landroid/app/Application;", "classLoader", "Ljava/lang/ClassLoader;", "className", "", "context", "Landroid/content/Context;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WordPressTestRunner extends androidx.test.runner.AndroidJUnitRunner {
    
    public WordPressTestRunner() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Application newApplication(@org.jetbrains.annotations.NotNull()
    java.lang.ClassLoader classLoader, @org.jetbrains.annotations.NotNull()
    java.lang.String className, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}