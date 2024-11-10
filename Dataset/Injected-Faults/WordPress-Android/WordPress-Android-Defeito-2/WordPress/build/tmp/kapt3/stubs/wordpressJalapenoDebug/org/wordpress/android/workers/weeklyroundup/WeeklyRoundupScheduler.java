package org.wordpress.android.workers.weeklyroundup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ0\u0010\r\u001a,\u0012(\u0012&\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u0010 \u0011*\u0012\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u0010\u0018\u00010\u00120\u000f0\u000eJ\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupScheduler;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "workManager", "Landroidx/work/WorkManager;", "getWorkManager", "()Landroidx/work/WorkManager;", "workManager$delegate", "Lkotlin/Lazy;", "cancel", "Landroidx/work/Operation;", "getAll", "Landroidx/lifecycle/LiveData;", "", "Landroidx/work/WorkInfo;", "kotlin.jvm.PlatformType", "", "schedule", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WeeklyRoundupScheduler {
    private final android.content.Context context = null;
    private final kotlin.Lazy workManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupScheduler.Companion Companion = null;
    private static final java.lang.String TAG = "weekly_roundup";
    private static final java.time.LocalTime DEFAULT_START_TIME = null;
    
    @javax.inject.Inject()
    public WeeklyRoundupScheduler(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final androidx.work.WorkManager getWorkManager() {
        return null;
    }
    
    public final void schedule() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.Operation cancel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<androidx.work.WorkInfo>> getAll() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupScheduler$Companion;", "", "()V", "DEFAULT_START_TIME", "Ljava/time/LocalTime;", "kotlin.jvm.PlatformType", "TAG", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}