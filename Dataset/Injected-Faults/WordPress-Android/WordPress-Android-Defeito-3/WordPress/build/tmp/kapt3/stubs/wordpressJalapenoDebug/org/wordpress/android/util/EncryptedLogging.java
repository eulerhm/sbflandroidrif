package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0006\u0010\u0019\u001a\u00020\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/util/EncryptedLogging;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "encryptedLogStore", "Lorg/wordpress/android/fluxc/store/EncryptedLogStore;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/EncryptedLogStore;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "encryptAndUploadLogFile", "", "logFile", "Ljava/io/File;", "shouldStartUploadImmediately", "", "onEncryptedLogUploaded", "", "event", "Lorg/wordpress/android/fluxc/store/EncryptedLogStore$OnEncryptedLogUploaded;", "start", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class EncryptedLogging {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.EncryptedLogStore encryptedLogStore = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    
    @javax.inject.Inject()
    public EncryptedLogging(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.EncryptedLogStore encryptedLogStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    public final void start() {
    }
    
    /**
     * Dispatches a FluxC action that will queue the given log to be uploaded as soon as possible.
     *
     * @param logFile Log file to be uploaded
     * @param shouldStartUploadImmediately This parameter will decide whether we should try to upload the log file
     * immediately. After a crash, we are unlikely to have enough time to complete the upload, so we can use this
     * parameter to avoid the unnecessary upload failure.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String encryptAndUploadLogFile(@org.jetbrains.annotations.NotNull()
    java.io.File logFile, boolean shouldStartUploadImmediately) {
        return null;
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.ASYNC)
    public final void onEncryptedLogUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.EncryptedLogStore.OnEncryptedLogUploaded event) {
    }
}