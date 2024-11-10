package org.wordpress.android.workers.notification.createsite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/workers/notification/createsite/CreateSiteNotificationHandler;", "Lorg/wordpress/android/workers/notification/local/LocalNotificationHandler;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "notificationsTracker", "Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "(Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;)V", "buildFirstActionPendingIntent", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "notificationId", "", "onNotificationShown", "", "shouldShowNotification", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CreateSiteNotificationHandler implements org.wordpress.android.workers.notification.local.LocalNotificationHandler {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.notifications.SystemNotificationsTracker notificationsTracker = null;
    
    @javax.inject.Inject()
    public CreateSiteNotificationHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.SystemNotificationsTracker notificationsTracker) {
        super();
    }
    
    @java.lang.Override()
    public boolean shouldShowNotification() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.PendingIntent buildFirstActionPendingIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int notificationId) {
        return null;
    }
    
    @java.lang.Override()
    public void onNotificationShown() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public android.app.PendingIntent buildSecondActionPendingIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int notificationId) {
        return null;
    }
}