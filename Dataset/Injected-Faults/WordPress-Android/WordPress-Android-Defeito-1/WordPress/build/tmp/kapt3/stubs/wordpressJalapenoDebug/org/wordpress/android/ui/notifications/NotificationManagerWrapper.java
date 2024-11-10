package org.wordpress.android.ui.notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/notifications/NotificationManagerWrapper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "areNotificationsEnabled", "", "notify", "", "id", "", "notification", "Landroid/app/Notification;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class NotificationManagerWrapper {
    private final android.content.Context context = null;
    
    @javax.inject.Inject()
    public NotificationManagerWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean areNotificationsEnabled() {
        return false;
    }
    
    public final void notify(int id, @org.jetbrains.annotations.NotNull()
    android.app.Notification notification) {
    }
}