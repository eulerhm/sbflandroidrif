package org.wordpress.android.workers.reminder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderNotifier;", "", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "reminderNotificationManager", "Lorg/wordpress/android/workers/reminder/ReminderNotificationManager;", "analyticsTracker", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;", "(Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/workers/reminder/ReminderNotificationManager;Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;)V", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "getAnalyticsTracker", "()Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersAnalyticsTracker;", "getContextProvider", "()Lorg/wordpress/android/viewmodel/ContextProvider;", "getReminderNotificationManager", "()Lorg/wordpress/android/workers/reminder/ReminderNotificationManager;", "getResourceProvider", "()Lorg/wordpress/android/viewmodel/ResourceProvider;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "notify", "", "siteId", "", "shouldNotify", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReminderNotifier {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.reminder.ReminderNotificationManager reminderNotificationManager = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker analyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.reminder.ReminderNotifier.Companion Companion = null;
    public static final int NO_SITE_ID = -1;
    
    @javax.inject.Inject()
    public ReminderNotifier(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.reminder.ReminderNotificationManager reminderNotificationManager, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker analyticsTracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ContextProvider getContextProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ResourceProvider getResourceProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.reminder.ReminderNotificationManager getReminderNotificationManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersAnalyticsTracker getAnalyticsTracker() {
        return null;
    }
    
    public final void notify(int siteId) {
    }
    
    public final boolean shouldNotify(int siteId) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderNotifier$Companion;", "", "()V", "NO_SITE_ID", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}