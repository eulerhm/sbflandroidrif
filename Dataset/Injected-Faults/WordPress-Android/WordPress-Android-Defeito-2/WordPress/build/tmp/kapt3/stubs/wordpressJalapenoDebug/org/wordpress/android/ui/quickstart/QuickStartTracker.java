package org.wordpress.android.ui.quickstart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J*\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0018\b\u0002\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\rH\u0007J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R,\u0010\t\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "cardsShownTracked", "", "Lkotlin/Pair;", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Type;", "", "", "getLastSelectedQuickStartType", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "resetShown", "", "track", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "properties", "trackQuickStartListDismissed", "tasksType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "trackQuickStartListViewed", "trackShown", "itemType", "tabType", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class QuickStartTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final java.util.List<kotlin.Pair<org.wordpress.android.ui.mysite.MySiteCardAndItem.Type, java.util.Map<java.lang.String, java.lang.String>>> cardsShownTracked = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.quickstart.QuickStartTracker.Companion Companion = null;
    private static final java.lang.String SITE_TYPE = "site_type";
    private static final java.lang.String TAB = "tab";
    
    @javax.inject.Inject()
    public QuickStartTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    public final void trackShown(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem.Type itemType, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteTabType tabType) {
    }
    
    public final void trackQuickStartListViewed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType tasksType) {
    }
    
    public final void trackQuickStartListDismissed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType tasksType) {
    }
    
    public final void resetShown() {
    }
    
    private final org.wordpress.android.ui.quickstart.QuickStartType getLastSelectedQuickStartType() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/quickstart/QuickStartTracker$Companion;", "", "()V", "SITE_TYPE", "", "TAB", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}