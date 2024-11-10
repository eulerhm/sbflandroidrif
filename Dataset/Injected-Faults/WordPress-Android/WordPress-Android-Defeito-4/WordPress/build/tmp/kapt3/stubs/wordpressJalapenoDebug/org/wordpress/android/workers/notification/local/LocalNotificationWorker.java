package org.wordpress.android.workers.notification.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotificationWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "localNotificationHandlerFactory", "Lorg/wordpress/android/workers/notification/local/LocalNotificationHandlerFactory;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lorg/wordpress/android/workers/notification/local/LocalNotificationHandlerFactory;)V", "getContext", "()Landroid/content/Context;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFirstActionPendingIntent", "Landroid/app/PendingIntent;", "notificationId", "", "getSecondActionPendingIntent", "localNotificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "Companion", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LocalNotificationWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final org.wordpress.android.workers.notification.local.LocalNotificationHandlerFactory localNotificationHandlerFactory = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.notification.local.LocalNotificationWorker.Companion Companion = null;
    private static final java.lang.String TYPE = "key_type";
    private static final java.lang.String ID = "key_id";
    private static final java.lang.String TITLE = "key_title";
    private static final java.lang.String TEXT = "key_text";
    private static final java.lang.String ICON = "key_icon";
    private static final java.lang.String FIRST_ACTION_ICON = "key_first_action_icon";
    private static final java.lang.String FIRST_ACTION_TITLE = "key_first_action_title";
    private static final java.lang.String SECOND_ACTION_ICON = "key_second_action_icon";
    private static final java.lang.String SECOND_ACTION_TITLE = "key_second_action_title";
    
    public LocalNotificationWorker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters params, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotificationHandlerFactory localNotificationHandlerFactory) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> continuation) {
        return null;
    }
    
    private final androidx.core.app.NotificationCompat.Builder localNotificationBuilder(int notificationId) {
        return null;
    }
    
    private final android.app.PendingIntent getFirstActionPendingIntent(int notificationId) {
        return null;
    }
    
    private final android.app.PendingIntent getSecondActionPendingIntent(int notificationId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotificationWorker$Factory;", "Landroidx/work/WorkerFactory;", "localNotificationHandlerFactory", "Lorg/wordpress/android/workers/notification/local/LocalNotificationHandlerFactory;", "(Lorg/wordpress/android/workers/notification/local/LocalNotificationHandlerFactory;)V", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", "", "workerParameters", "Landroidx/work/WorkerParameters;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory extends androidx.work.WorkerFactory {
        private final org.wordpress.android.workers.notification.local.LocalNotificationHandlerFactory localNotificationHandlerFactory = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.workers.notification.local.LocalNotificationHandlerFactory localNotificationHandlerFactory) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public androidx.work.ListenableWorker createWorker(@org.jetbrains.annotations.NotNull()
        android.content.Context appContext, @org.jetbrains.annotations.NotNull()
        java.lang.String workerClassName, @org.jetbrains.annotations.NotNull()
        androidx.work.WorkerParameters workerParameters) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotificationWorker$Companion;", "", "()V", "FIRST_ACTION_ICON", "", "FIRST_ACTION_TITLE", "ICON", "ID", "SECOND_ACTION_ICON", "SECOND_ACTION_TITLE", "TEXT", "TITLE", "TYPE", "buildData", "Landroidx/work/Data;", "localNotification", "Lorg/wordpress/android/workers/notification/local/LocalNotification;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.work.Data buildData(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.workers.notification.local.LocalNotification localNotification) {
            return null;
        }
    }
}