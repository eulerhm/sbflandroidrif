package org.wordpress.android.workers.notification.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotificationHandler;", "", "buildFirstActionPendingIntent", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "notificationId", "", "buildSecondActionPendingIntent", "onNotificationShown", "", "shouldShowNotification", "", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface LocalNotificationHandler {
    
    public abstract boolean shouldShowNotification();
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.app.PendingIntent buildFirstActionPendingIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int notificationId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract android.app.PendingIntent buildSecondActionPendingIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int notificationId);
    
    public abstract void onNotificationShown();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable()
        public static android.app.PendingIntent buildSecondActionPendingIntent(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.workers.notification.local.LocalNotificationHandler $this, @org.jetbrains.annotations.NotNull()
        android.content.Context context, int notificationId) {
            return null;
        }
    }
}