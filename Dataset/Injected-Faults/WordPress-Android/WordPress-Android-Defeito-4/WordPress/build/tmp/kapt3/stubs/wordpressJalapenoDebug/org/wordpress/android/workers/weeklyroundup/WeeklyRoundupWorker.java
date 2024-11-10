package org.wordpress.android.workers.weeklyroundup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010\r\u001a\u00020\u000eH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "notifier", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;", "workerParameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "getNotifier", "()Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showNotification", "", "notification", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotification;", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WeeklyRoundupWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier notifier = null;
    
    public WeeklyRoundupWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier notifier, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParameters) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier getNotifier() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> continuation) {
        return null;
    }
    
    private final void showNotification(org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotification notification) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupWorker$Factory;", "Landroidx/work/WorkerFactory;", "notifier", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;", "(Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;)V", "createWorker", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory extends androidx.work.WorkerFactory {
        private final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier notifier = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier notifier) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public org.wordpress.android.workers.weeklyroundup.WeeklyRoundupWorker createWorker(@org.jetbrains.annotations.NotNull()
        android.content.Context appContext, @org.jetbrains.annotations.NotNull()
        java.lang.String workerClassName, @org.jetbrains.annotations.NotNull()
        androidx.work.WorkerParameters workerParameters) {
            return null;
        }
    }
}