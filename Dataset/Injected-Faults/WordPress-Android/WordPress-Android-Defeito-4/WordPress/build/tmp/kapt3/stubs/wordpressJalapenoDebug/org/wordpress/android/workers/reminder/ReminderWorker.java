package org.wordpress.android.workers.reminder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0018B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0011\u0010\u0015\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "scheduler", "Lorg/wordpress/android/workers/reminder/ReminderScheduler;", "reminderNotifier", "Lorg/wordpress/android/workers/reminder/ReminderNotifier;", "promptReminderNotifier", "Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;", "workerParameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Lorg/wordpress/android/workers/reminder/ReminderScheduler;Lorg/wordpress/android/workers/reminder/ReminderNotifier;Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "getPromptReminderNotifier", "()Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;", "getReminderNotifier", "()Lorg/wordpress/android/workers/reminder/ReminderNotifier;", "getScheduler", "()Lorg/wordpress/android/workers/reminder/ReminderScheduler;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReminderWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.reminder.ReminderScheduler scheduler = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.reminder.ReminderNotifier reminderNotifier = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier promptReminderNotifier = null;
    
    public ReminderWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderScheduler scheduler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderNotifier reminderNotifier, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier promptReminderNotifier, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParameters) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.reminder.ReminderScheduler getScheduler() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.reminder.ReminderNotifier getReminderNotifier() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier getPromptReminderNotifier() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\"\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderWorker$Factory;", "Landroidx/work/WorkerFactory;", "scheduler", "Lorg/wordpress/android/workers/reminder/ReminderScheduler;", "reminderNotifier", "Lorg/wordpress/android/workers/reminder/ReminderNotifier;", "promptReminderNotifier", "Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;", "(Lorg/wordpress/android/workers/reminder/ReminderScheduler;Lorg/wordpress/android/workers/reminder/ReminderNotifier;Lorg/wordpress/android/workers/reminder/prompt/PromptReminderNotifier;)V", "createWorker", "Lorg/wordpress/android/workers/reminder/ReminderWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory extends androidx.work.WorkerFactory {
        private final org.wordpress.android.workers.reminder.ReminderScheduler scheduler = null;
        private final org.wordpress.android.workers.reminder.ReminderNotifier reminderNotifier = null;
        private final org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier promptReminderNotifier = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.workers.reminder.ReminderScheduler scheduler, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.workers.reminder.ReminderNotifier reminderNotifier, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.workers.reminder.prompt.PromptReminderNotifier promptReminderNotifier) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public org.wordpress.android.workers.reminder.ReminderWorker createWorker(@org.jetbrains.annotations.NotNull()
        android.content.Context appContext, @org.jetbrains.annotations.NotNull()
        java.lang.String workerClassName, @org.jetbrains.annotations.NotNull()
        androidx.work.WorkerParameters workerParameters) {
            return null;
        }
    }
}