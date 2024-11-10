package org.wordpress.android.workers.notification.createsite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/workers/notification/createsite/CreateSiteNotificationScheduler;", "", "localNotificationScheduler", "Lorg/wordpress/android/workers/notification/local/LocalNotificationScheduler;", "createSiteNotificationHandler", "Lorg/wordpress/android/workers/notification/createsite/CreateSiteNotificationHandler;", "appsPrefs", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/workers/notification/local/LocalNotificationScheduler;Lorg/wordpress/android/workers/notification/createsite/CreateSiteNotificationHandler;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "cancelCreateSiteNotification", "", "scheduleCreateSiteNotificationIfNeeded", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CreateSiteNotificationScheduler {
    private final org.wordpress.android.workers.notification.local.LocalNotificationScheduler localNotificationScheduler = null;
    private final org.wordpress.android.workers.notification.createsite.CreateSiteNotificationHandler createSiteNotificationHandler = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appsPrefs = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    
    @javax.inject.Inject()
    public CreateSiteNotificationScheduler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotificationScheduler localNotificationScheduler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.createsite.CreateSiteNotificationHandler createSiteNotificationHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appsPrefs, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    public final void scheduleCreateSiteNotificationIfNeeded() {
    }
    
    public final void cancelCreateSiteNotification() {
    }
}