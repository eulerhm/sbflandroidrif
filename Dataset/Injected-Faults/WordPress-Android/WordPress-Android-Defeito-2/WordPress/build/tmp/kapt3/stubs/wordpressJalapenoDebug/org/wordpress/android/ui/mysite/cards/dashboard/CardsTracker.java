package org.wordpress.android.ui.mysite.cards.dashboard;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000  2\u00020\u0001:\u0005 !\"#$B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\nJ\u0006\u0010\u001e\u001a\u00020\nJ\u0006\u0010\u001f\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker;", "", "cardsShownTracker", "Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsShownTracker;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "quickStartTracker", "Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "(Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsShownTracker;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/quickstart/QuickStartTracker;)V", "resetShown", "", "trackCardFooterLinkClicked", "type", "", "subtype", "trackCardItemClicked", "trackPostCardFooterLinkClicked", "postCardType", "Lorg/wordpress/android/ui/mysite/cards/dashboard/posts/PostCardType;", "trackPostItemClicked", "trackQuickStartCardItemClicked", "quickStartTaskType", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTaskType;", "trackQuickStartCardShown", "quickStartType", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "trackShown", "dashboardCards", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards;", "trackTodaysStatsCardClicked", "trackTodaysStatsCardFooterLinkClicked", "trackTodaysStatsCardGetMoreViewsNudgeClicked", "Companion", "PostSubtype", "QuickStartSubtype", "StatsSubtype", "Type", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CardsTracker {
    private final org.wordpress.android.ui.mysite.cards.dashboard.CardsShownTracker cardsShownTracker = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.cards.dashboard.CardsTracker.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "type";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBTYPE = "subtype";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String STATS = "stats";
    
    @javax.inject.Inject()
    public CardsTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.CardsShownTracker cardsShownTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker) {
        super();
    }
    
    public final void trackQuickStartCardItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTaskType quickStartTaskType) {
    }
    
    public final void trackTodaysStatsCardGetMoreViewsNudgeClicked() {
    }
    
    public final void trackTodaysStatsCardFooterLinkClicked() {
    }
    
    public final void trackTodaysStatsCardClicked() {
    }
    
    public final void trackPostCardFooterLinkClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType) {
    }
    
    public final void trackPostItemClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.posts.PostCardType postCardType) {
    }
    
    private final void trackCardFooterLinkClicked(java.lang.String type, java.lang.String subtype) {
    }
    
    private final void trackCardItemClicked(java.lang.String type, java.lang.String subtype) {
    }
    
    public final void resetShown() {
    }
    
    public final void trackShown(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards dashboardCards) {
    }
    
    public final void trackQuickStartCardShown(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker$Type;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "ERROR", "QUICK_START", "STATS", "POST", "BLOGGING_PROMPT", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ ERROR /* = new ERROR(null) */,
        /*public static final*/ QUICK_START /* = new QUICK_START(null) */,
        /*public static final*/ STATS /* = new STATS(null) */,
        /*public static final*/ POST /* = new POST(null) */,
        /*public static final*/ BLOGGING_PROMPT /* = new BLOGGING_PROMPT(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        
        Type(java.lang.String label) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker$QuickStartSubtype;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "CUSTOMIZE", "GROW", "GET_TO_KNOW_APP", "UNKNOWN", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum QuickStartSubtype {
        /*public static final*/ CUSTOMIZE /* = new CUSTOMIZE(null) */,
        /*public static final*/ GROW /* = new GROW(null) */,
        /*public static final*/ GET_TO_KNOW_APP /* = new GET_TO_KNOW_APP(null) */,
        /*public static final*/ UNKNOWN /* = new UNKNOWN(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        
        QuickStartSubtype(java.lang.String label) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker$StatsSubtype;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "TODAYS_STATS", "TODAYS_STATS_NUDGE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum StatsSubtype {
        /*public static final*/ TODAYS_STATS /* = new TODAYS_STATS(null) */,
        /*public static final*/ TODAYS_STATS_NUDGE /* = new TODAYS_STATS_NUDGE(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        
        StatsSubtype(java.lang.String label) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker$PostSubtype;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "CREATE_FIRST", "CREATE_NEXT", "DRAFT", "SCHEDULED", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum PostSubtype {
        /*public static final*/ CREATE_FIRST /* = new CREATE_FIRST(null) */,
        /*public static final*/ CREATE_NEXT /* = new CREATE_NEXT(null) */,
        /*public static final*/ DRAFT /* = new DRAFT(null) */,
        /*public static final*/ SCHEDULED /* = new SCHEDULED(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String label = null;
        
        PostSubtype(java.lang.String label) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLabel() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsTracker$Companion;", "", "()V", "STATS", "", "SUBTYPE", "TYPE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}