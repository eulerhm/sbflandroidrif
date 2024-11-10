package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\r\n\u0002\b\u0006\u0018\u0000 ,2\u00020\u0001:\u0004+,-.B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0015\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ(\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012H\u0002J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0006\u0010\u0019\u001a\u00020\nJ\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0016J\u000e\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\"\u001a\u00020\nJ\u0006\u0010#\u001a\u00020\nJ\u000e\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020 J\u0016\u0010&\u001a\u00020\n2\u0006\u0010\'\u001a\u00020\f2\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;", "", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/fluxc/store/SiteStore;)V", "siteType", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$SiteType;", "setSite", "", "siteId", "", "(I)Lkotlin/Unit;", "track", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "properties", "", "", "trackButtonPressed", "screen", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel$Screen;", "button", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$Button;", "trackFlowCompleted", "trackFlowDismissed", "source", "trackFlowStart", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$Source;", "trackNotificationReceived", "promptIncluded", "", "trackPrimaryButtonPressed", "trackRemindersCancelled", "trackRemindersIncludePromptHelpPressed", "trackRemindersIncludePromptPressed", "promptEnabled", "trackRemindersScheduled", "daysCount", "timeSelected", "", "trackScreenShown", "Button", "Companion", "SiteType", "Source", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingRemindersAnalyticsTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker.SiteType siteType;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker.Companion Companion = null;
    private static final java.lang.String BLOG_TYPE_KEY = "blog_type";
    private static final java.lang.String SCREEN_KEY = "screen";
    private static final java.lang.String BUTTON_KEY = "button";
    private static final java.lang.String SOURCE_KEY = "source";
    private static final java.lang.String PROMPT_ENABLED_KEY = "enabled";
    private static final java.lang.String DAYS_OF_WEEK_COUNT_KEY = "days_of_week_count";
    private static final java.lang.String SELECTED_TIME_KEY = "selected_time";
    private static final java.lang.String PROMPT_INCLUDED = "prompt_included";
    
    @javax.inject.Inject()
    public BloggingRemindersAnalyticsTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.Unit setSite(int siteId) {
        return null;
    }
    
    public final void trackScreenShown(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen screen) {
    }
    
    public final void trackPrimaryButtonPressed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen screen) {
    }
    
    private final void trackButtonPressed(org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen screen, org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker.Button button) {
    }
    
    public final void trackFlowStart(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker.Source source) {
    }
    
    public final void trackFlowDismissed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.Screen source) {
    }
    
    public final void trackFlowCompleted() {
    }
    
    public final void trackRemindersScheduled(int daysCount, @org.jetbrains.annotations.NotNull()
    java.lang.CharSequence timeSelected) {
    }
    
    public final void trackRemindersCancelled() {
    }
    
    public final void trackNotificationReceived(boolean promptIncluded) {
    }
    
    public final void trackRemindersIncludePromptPressed(boolean promptEnabled) {
    }
    
    public final void trackRemindersIncludePromptHelpPressed() {
    }
    
    private final void track(org.wordpress.android.analytics.AnalyticsTracker.Stat stat, java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$SiteType;", "", "trackingName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTrackingName", "()Ljava/lang/String;", "WORDPRESS_COM", "SELF_HOSTED", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum SiteType {
        /*public static final*/ WORDPRESS_COM /* = new WORDPRESS_COM(null) */,
        /*public static final*/ SELF_HOSTED /* = new SELF_HOSTED(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String trackingName = null;
        
        SiteType(java.lang.String trackingName) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTrackingName() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$Button;", "", "trackingName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTrackingName", "()Ljava/lang/String;", "CONTINUE", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum Button {
        /*public static final*/ CONTINUE /* = new CONTINUE(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String trackingName = null;
        
        Button(java.lang.String trackingName) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTrackingName() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$Source;", "", "trackingName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTrackingName", "()Ljava/lang/String;", "PUBLISH_FLOW", "BLOG_SETTINGS", "NOTIFICATION_SETTINGS", "BLOGGING_PROMPTS_ONBOARDING", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Source {
        /*public static final*/ PUBLISH_FLOW /* = new PUBLISH_FLOW(null) */,
        /*public static final*/ BLOG_SETTINGS /* = new BLOG_SETTINGS(null) */,
        /*public static final*/ NOTIFICATION_SETTINGS /* = new NOTIFICATION_SETTINGS(null) */,
        /*public static final*/ BLOGGING_PROMPTS_ONBOARDING /* = new BLOGGING_PROMPTS_ONBOARDING(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String trackingName = null;
        
        Source(java.lang.String trackingName) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTrackingName() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker$Companion;", "", "()V", "BLOG_TYPE_KEY", "", "BUTTON_KEY", "DAYS_OF_WEEK_COUNT_KEY", "PROMPT_ENABLED_KEY", "PROMPT_INCLUDED", "SCREEN_KEY", "SELECTED_TIME_KEY", "SOURCE_KEY", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}