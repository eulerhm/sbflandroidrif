package org.wordpress.android.ui.featureintroduction;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ$\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00072\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bJ\b\u0010\u0011\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/featureintroduction/FeatureIntroductionViewModel;", "Landroidx/lifecycle/ViewModel;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "dismissAnalyticsEvent", "Lkotlin/Pair;", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "", "", "", "onBackButtonClick", "", "onCloseButtonClick", "setDismissAnalyticsEvent", "stat", "properties", "trackDismissAnalyticsEvent", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FeatureIntroductionViewModel extends androidx.lifecycle.ViewModel {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    private kotlin.Pair<? extends org.wordpress.android.analytics.AnalyticsTracker.Stat, ? extends java.util.Map<java.lang.String, ? extends java.lang.Object>> dismissAnalyticsEvent;
    
    @javax.inject.Inject()
    public FeatureIntroductionViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker) {
        super();
    }
    
    public final void setDismissAnalyticsEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    public final void onCloseButtonClick() {
    }
    
    public final void onBackButtonClick() {
    }
    
    private final void trackDismissAnalyticsEvent() {
    }
}