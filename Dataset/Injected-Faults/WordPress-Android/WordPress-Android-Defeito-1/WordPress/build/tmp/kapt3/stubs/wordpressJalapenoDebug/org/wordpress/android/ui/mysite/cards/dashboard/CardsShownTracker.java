package org.wordpress.android.ui.mysite.cards.dashboard;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u000e\u001a\u00020\n2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/ui/mysite/cards/dashboard/CardsShownTracker;", "", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "cardsShownTracked", "", "Lkotlin/Pair;", "", "reset", "", "track", "dashboardCards", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards;", "trackCardShown", "pair", "card", "Lorg/wordpress/android/ui/mysite/MySiteCardAndItem$Card$DashboardCards$DashboardCard;", "trackQuickStartCardShown", "quickStartType", "Lorg/wordpress/android/ui/quickstart/QuickStartType;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class CardsShownTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> cardsShownTracked = null;
    
    @javax.inject.Inject()
    public CardsShownTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    public final void reset() {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards dashboardCards) {
    }
    
    private final void trackCardShown(org.wordpress.android.ui.mysite.MySiteCardAndItem.Card.DashboardCards.DashboardCard card) {
    }
    
    public final void trackQuickStartCardShown(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartType quickStartType) {
    }
    
    private final void trackCardShown(kotlin.Pair<java.lang.String, java.lang.String> pair) {
    }
}