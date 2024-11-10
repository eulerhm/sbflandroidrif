package org.wordpress.android.workers.reminder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014J0\u0010\u0015\u001a,\u0012(\u0012&\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u0018 \u0019*\u0012\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u0018\u0018\u00010\u001a0\u00170\u0016J\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u00180\u00162\u0006\u0010\u0010\u001a\u00020\u0011J8\u0010\u001c\u001a,\u0012(\u0012&\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u0018 \u0019*\u0012\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u0018\u0018\u00010\u001a0\u00170\u00162\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J&\u0010\u001f\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006&"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderScheduler;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "(Lorg/wordpress/android/viewmodel/ContextProvider;)V", "getContextProvider", "()Lorg/wordpress/android/viewmodel/ContextProvider;", "workManager", "Landroidx/work/WorkManager;", "getWorkManager", "()Landroidx/work/WorkManager;", "workManager$delegate", "Lkotlin/Lazy;", "cancelAll", "Landroidx/work/Operation;", "cancelById", "id", "Ljava/util/UUID;", "cancelBySiteId", "siteId", "", "getAll", "Landroidx/lifecycle/LiveData;", "", "Landroidx/work/WorkInfo;", "kotlin.jvm.PlatformType", "", "getById", "getBySiteId", "getUniqueName", "", "schedule", "", "hour", "minute", "reminderConfig", "Lorg/wordpress/android/workers/reminder/ReminderConfig;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReminderScheduler {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy workManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.reminder.ReminderScheduler.Companion Companion = null;
    private static final java.lang.String REMINDER_TAG = "reminder";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REMINDER_SITE_ID = "reminder_site_id";
    public static final int DEFAUlT_START_HOUR = 10;
    public static final int DEFAULT_START_MINUTE = 0;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REMINDER_HOUR = "reminder_hour";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REMINDER_MINUTE = "reminder_minute";
    
    @javax.inject.Inject()
    public ReminderScheduler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ContextProvider getContextProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.WorkManager getWorkManager() {
        return null;
    }
    
    public final void schedule(int siteId, int hour, int minute, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderConfig reminderConfig) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.Operation cancelById(@org.jetbrains.annotations.NotNull()
    java.util.UUID id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.Operation cancelBySiteId(int siteId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.Operation cancelAll() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<androidx.work.WorkInfo> getById(@org.jetbrains.annotations.NotNull()
    java.util.UUID id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<androidx.work.WorkInfo>> getBySiteId(int siteId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<androidx.work.WorkInfo>> getAll() {
        return null;
    }
    
    private final java.lang.String getUniqueName(int siteId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderScheduler$Companion;", "", "()V", "DEFAULT_START_MINUTE", "", "DEFAUlT_START_HOUR", "REMINDER_HOUR", "", "REMINDER_MINUTE", "REMINDER_SITE_ID", "REMINDER_TAG", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}