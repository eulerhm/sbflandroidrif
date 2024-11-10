package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\b\u0010\u0019\u001a\u00020\u0018H\u0007J\b\u0010\u001a\u001a\u00020\u0018H\u0007J\b\u0010\u001b\u001a\u00020\u0018H\u0007J\b\u0010\u001c\u001a\u00020\u0018H\u0007R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u00020\n8G\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u00020\u000e8GX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/util/UploadWorkerTest;", "", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "hiltRule", "Ldagger/hilt/android/testing/HiltAndroidRule;", "getHiltRule", "()Ldagger/hilt/android/testing/HiltAndroidRule;", "initializationRule", "Lorg/wordpress/android/InitializationRule;", "getInitializationRule", "()Lorg/wordpress/android/InitializationRule;", "setInitializationRule", "(Lorg/wordpress/android/InitializationRule;)V", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "setUp", "", "testOneTimeUploadWorker", "testOneTimeUploadWorkerWithUnmetConstraints", "testPeriodicUploadWorkerWithMetConstraints", "testPeriodicUploadWorkerWithMetConstraintsCalledTwice", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.hilt.android.testing.HiltAndroidTest()
public final class UploadWorkerTest {
    @org.jetbrains.annotations.NotNull()
    private final dagger.hilt.android.testing.HiltAndroidRule hiltRule = null;
    @org.jetbrains.annotations.NotNull()
    private org.wordpress.android.InitializationRule initializationRule;
    @javax.inject.Inject()
    public android.content.Context context;
    private final org.wordpress.android.ui.uploads.UploadStarter uploadStarter = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    
    public UploadWorkerTest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.junit.Rule()
    public final dagger.hilt.android.testing.HiltAndroidRule getHiltRule() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.junit.Rule()
    public final org.wordpress.android.InitializationRule getInitializationRule() {
        return null;
    }
    
    public final void setInitializationRule(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.InitializationRule p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    @org.junit.Before()
    public final void setUp() {
    }
    
    @org.junit.Test()
    public final void testOneTimeUploadWorker() {
    }
    
    @org.junit.Test()
    public final void testOneTimeUploadWorkerWithUnmetConstraints() {
    }
    
    @org.junit.Test()
    public final void testPeriodicUploadWorkerWithMetConstraints() {
    }
    
    @org.junit.Test()
    public final void testPeriodicUploadWorkerWithMetConstraintsCalledTwice() {
    }
}