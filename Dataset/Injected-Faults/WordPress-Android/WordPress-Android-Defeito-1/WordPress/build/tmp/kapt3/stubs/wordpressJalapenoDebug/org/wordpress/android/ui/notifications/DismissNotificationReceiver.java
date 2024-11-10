package org.wordpress.android.ui.notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u000f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/notifications/DismissNotificationReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "getAnalyticsTrackerWrapper", "()Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "setAnalyticsTrackerWrapper", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "dismissNotification", "", "intent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "onReceive", "trackAnalyticsEvent", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DismissNotificationReceiver extends android.content.BroadcastReceiver {
    @javax.inject.Inject()
    public org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.notifications.DismissNotificationReceiver.Companion Companion = null;
    private static final java.lang.String EXTRA_NOTIFICATION_ID = "EXTRA_NOTIFICATION_ID";
    private static final java.lang.String EXTRA_STAT_TO_TRACK = "EXTRA_STAT_TO_TRACK";
    
    public DismissNotificationReceiver() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper getAnalyticsTrackerWrapper() {
        return null;
    }
    
    public final void setAnalyticsTrackerWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper p0) {
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
    }
    
    private final void trackAnalyticsEvent(android.content.Intent intent) {
    }
    
    private final void dismissNotification(android.content.Intent intent, android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/notifications/DismissNotificationReceiver$Companion;", "", "()V", "EXTRA_NOTIFICATION_ID", "", "EXTRA_STAT_TO_TRACK", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "notificationId", "", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent newIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, int notificationId, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
            return null;
        }
    }
}