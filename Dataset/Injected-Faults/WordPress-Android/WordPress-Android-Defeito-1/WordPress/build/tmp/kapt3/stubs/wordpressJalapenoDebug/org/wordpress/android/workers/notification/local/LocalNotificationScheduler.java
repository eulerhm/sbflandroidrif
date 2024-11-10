package org.wordpress.android.workers.notification.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001f\u0010\u0010\u001a\u00020\r2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0012\"\u00020\u000b\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotificationScheduler;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "workManager", "Landroidx/work/WorkManager;", "(Landroidx/work/WorkManager;)V", "buildOneTimeWorkRequest", "Landroidx/work/OneTimeWorkRequest;", "localNotification", "Lorg/wordpress/android/workers/notification/local/LocalNotification;", "cancelScheduledNotification", "", "notificationType", "Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;", "scheduleOneTimeNotification", "localNotifications", "", "([Lorg/wordpress/android/workers/notification/local/LocalNotification;)V", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LocalNotificationScheduler {
    private final androidx.work.WorkManager workManager = null;
    
    public LocalNotificationScheduler(@org.jetbrains.annotations.NotNull()
    androidx.work.WorkManager workManager) {
        super();
    }
    
    @javax.inject.Inject()
    public LocalNotificationScheduler(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void scheduleOneTimeNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotification... localNotifications) {
    }
    
    public final void cancelScheduledNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotification.Type notificationType) {
    }
    
    private final androidx.work.OneTimeWorkRequest buildOneTimeWorkRequest(org.wordpress.android.workers.notification.local.LocalNotification localNotification) {
        return null;
    }
}