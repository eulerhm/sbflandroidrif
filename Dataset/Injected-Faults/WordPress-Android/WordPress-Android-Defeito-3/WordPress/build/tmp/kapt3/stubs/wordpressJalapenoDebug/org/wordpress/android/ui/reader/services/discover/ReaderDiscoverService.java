package org.wordpress.android.ui.reader.services.discover;

import java.lang.System;

/**
 * Service which updates data for discover tab in Reader, relies on EventBus to notify of changes.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\"\u0010 \u001a\u00020!2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverService;", "Landroid/app/Service;", "Lorg/wordpress/android/ui/reader/services/ServiceCompletionListener;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getIoDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "setIoDispatcher", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "job", "Lkotlinx/coroutines/Job;", "readerDiscoverLogic", "Lorg/wordpress/android/ui/reader/services/discover/ReaderDiscoverLogic;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCompleted", "companion", "", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderDiscoverService extends android.app.Service implements org.wordpress.android.ui.reader.services.ServiceCompletionListener, kotlinx.coroutines.CoroutineScope {
    @javax.inject.Named(value = "IO_THREAD")
    @javax.inject.Inject()
    public kotlinx.coroutines.CoroutineDispatcher ioDispatcher;
    private org.wordpress.android.ui.reader.services.discover.ReaderDiscoverLogic readerDiscoverLogic;
    private kotlinx.coroutines.Job job;
    
    public ReaderDiscoverService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getIoDispatcher() {
        return null;
    }
    
    public final void setIoDispatcher(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    protected void attachBaseContext(@org.jetbrains.annotations.NotNull()
    android.content.Context newBase) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onCompleted(@org.jetbrains.annotations.Nullable()
    java.lang.Object companion) {
    }
}