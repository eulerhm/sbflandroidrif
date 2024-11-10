package org.wordpress.android.workers.weeklyroundup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!BO\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00160\u001aJ\u0006\u0010\u001f\u001a\u00020 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier;", "", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "weeklyRoundupScheduler", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupScheduler;", "notificationsTracker", "Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "siteUtils", "Lorg/wordpress/android/util/SiteUtilsWrapper;", "weeklyRoundupRepository", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupRepository;", "appPrefs", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "(Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupScheduler;Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;Lorg/wordpress/android/util/SiteUtilsWrapper;Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupRepository;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "buildNotification", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotification;", "data", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupData;", "buildNotifications", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onNotificationsShown", "", "notifications", "shouldShowNotifications", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WeeklyRoundupNotifier {
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupScheduler weeklyRoundupScheduler = null;
    private final org.wordpress.android.ui.notifications.SystemNotificationsTracker notificationsTracker = null;
    private final org.wordpress.android.util.SiteUtilsWrapper siteUtils = null;
    private final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupRepository weeklyRoundupRepository = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefs = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotifier.Companion Companion = null;
    private static final int TOP_FIVE_SITES = 5;
    private static final int MIN_SITE_VIEWS = 5;
    
    @javax.inject.Inject()
    public WeeklyRoundupNotifier(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.weeklyroundup.WeeklyRoundupScheduler weeklyRoundupScheduler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.SystemNotificationsTracker notificationsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SiteUtilsWrapper siteUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.weeklyroundup.WeeklyRoundupRepository weeklyRoundupRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefs) {
        super();
    }
    
    public final boolean shouldShowNotifications() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object buildNotifications(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotification>> continuation) {
        return null;
    }
    
    public final void onNotificationsShown(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotification> notifications) {
    }
    
    private final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotification buildNotification(org.wordpress.android.workers.weeklyroundup.WeeklyRoundupData data) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotifier$Companion;", "", "()V", "MIN_SITE_VIEWS", "", "TOP_FIVE_SITES", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}