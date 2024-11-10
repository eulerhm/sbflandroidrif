package org.wordpress.android.ui.notifications;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010J\f\u0010\u0013\u001a\u00020\u0014*\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "appPrefs", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "notificationManager", "Lorg/wordpress/android/ui/notifications/NotificationManagerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/notifications/NotificationManagerWrapper;)V", "checkSystemNotificationsState", "", "track", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "trackDismissedNotification", "notificationType", "Lorg/wordpress/android/push/NotificationType;", "trackShownNotification", "trackTappedNotification", "toTypeValue", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SystemNotificationsTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefs = null;
    private final org.wordpress.android.ui.notifications.NotificationManagerWrapper notificationManager = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.notifications.SystemNotificationsTracker.Companion Companion = null;
    private static final java.lang.String NOTIFICATION_TYPE_KEY = "notification_type";
    private static final java.lang.String COMMENT_VALUE = "comment";
    private static final java.lang.String LIKE_VALUE = "like";
    private static final java.lang.String COMMENT_LIKE_VALUE = "comment_like";
    private static final java.lang.String AUTOMATTCHER_VALUE = "automattcher";
    private static final java.lang.String FOLLOW_VALUE = "follow";
    private static final java.lang.String REBLOG_VALUE = "reblog";
    private static final java.lang.String BADGE_RESET_VALUE = "badge_reset";
    private static final java.lang.String NOTE_DELETE_VALUE = "note_delete";
    private static final java.lang.String TEST_NOTE_VALUE = "test_note";
    private static final java.lang.String UNKNOWN_NOTE_VALUE = "unknown_note";
    private static final java.lang.String AUTHENTICATION_TYPE_VALUE = "authentication";
    private static final java.lang.String GROUP_NOTES_TYPE_VALUE = "group_notes";
    private static final java.lang.String ACTIONS_RESULT_TYPE_VALUE = "actions_result";
    private static final java.lang.String ACTIONS_PROGRESS_TYPE_VALUE = "actions_progress";
    private static final java.lang.String QUICK_START_REMINDER_TYPE_VALUE = "quick_start_reminder";
    private static final java.lang.String POST_UPLOAD_SUCCESS_TYPE_VALUE = "post_upload_success";
    private static final java.lang.String POST_UPLOAD_ERROR_TYPE_VALUE = "post_upload_error";
    private static final java.lang.String MEDIA_UPLOAD_SUCCESS_TYPE_VALUE = "media_upload_success";
    private static final java.lang.String MEDIA_UPLOAD_ERROR_TYPE_VALUE = "media_upload_error";
    private static final java.lang.String POST_PUBLISHED_TYPE_VALUE = "post_published";
    private static final java.lang.String STORY_SAVE_SUCCESS_TYPE_VALUE = "story_save_success";
    private static final java.lang.String STORY_SAVE_ERROR_TYPE_VALUE = "story_save_error";
    private static final java.lang.String STORY_FRAME_SAVE_SUCCESS_TYPE_VALUE = "story_frame_save_success";
    private static final java.lang.String STORY_FRAME_SAVE_ERROR_TYPE_VALUE = "story_frame_save_error";
    private static final java.lang.String PENDING_DRAFT_TYPE_VALUE = "pending_draft";
    private static final java.lang.String ZENDESK_MESSAGE_TYPE_VALUE = "zendesk_message";
    private static final java.lang.String BLOGGING_REMINDERS_TYPE_VALUE = "blogging_reminders";
    private static final java.lang.String CREATE_SITE_TYPE_VALUE = "create_site";
    private static final java.lang.String WEEKLY_ROUNDUP_TYPE_VALUE = "weekly_roundup";
    private static final java.lang.String BLOGGING_PROMPTS_ONBOARDING_VALUE = "blogging_prompts_onboarding";
    
    @javax.inject.Inject()
    public SystemNotificationsTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefs, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.NotificationManagerWrapper notificationManager) {
        super();
    }
    
    public final void checkSystemNotificationsState() {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
    }
    
    public final void trackShownNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.push.NotificationType notificationType) {
    }
    
    public final void trackTappedNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.push.NotificationType notificationType) {
    }
    
    public final void trackDismissedNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.push.NotificationType notificationType) {
    }
    
    private final java.lang.String toTypeValue(org.wordpress.android.push.NotificationType $this$toTypeValue) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker$Companion;", "", "()V", "ACTIONS_PROGRESS_TYPE_VALUE", "", "ACTIONS_RESULT_TYPE_VALUE", "AUTHENTICATION_TYPE_VALUE", "AUTOMATTCHER_VALUE", "BADGE_RESET_VALUE", "BLOGGING_PROMPTS_ONBOARDING_VALUE", "BLOGGING_REMINDERS_TYPE_VALUE", "COMMENT_LIKE_VALUE", "COMMENT_VALUE", "CREATE_SITE_TYPE_VALUE", "FOLLOW_VALUE", "GROUP_NOTES_TYPE_VALUE", "LIKE_VALUE", "MEDIA_UPLOAD_ERROR_TYPE_VALUE", "MEDIA_UPLOAD_SUCCESS_TYPE_VALUE", "NOTE_DELETE_VALUE", "NOTIFICATION_TYPE_KEY", "PENDING_DRAFT_TYPE_VALUE", "POST_PUBLISHED_TYPE_VALUE", "POST_UPLOAD_ERROR_TYPE_VALUE", "POST_UPLOAD_SUCCESS_TYPE_VALUE", "QUICK_START_REMINDER_TYPE_VALUE", "REBLOG_VALUE", "STORY_FRAME_SAVE_ERROR_TYPE_VALUE", "STORY_FRAME_SAVE_SUCCESS_TYPE_VALUE", "STORY_SAVE_ERROR_TYPE_VALUE", "STORY_SAVE_SUCCESS_TYPE_VALUE", "TEST_NOTE_VALUE", "UNKNOWN_NOTE_VALUE", "WEEKLY_ROUNDUP_TYPE_VALUE", "ZENDESK_MESSAGE_TYPE_VALUE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}