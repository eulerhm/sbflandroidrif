package org.wordpress.android.util.analytics;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0014\u0010\n\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010J$\u0010\u0012\u001a\u00020\b2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/util/analytics/ActivityLogTracker;", "", "tracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "dateTimeUtilsWrapper", "Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/util/DateTimeUtilsWrapper;)V", "trackActivityTypeFilterButtonClicked", "", "trackActivityTypeFilterCleared", "trackActivityTypeFilterSelected", "selectedTypes", "", "Lorg/wordpress/android/fluxc/model/activity/ActivityTypeModel;", "trackDateRangeFilterButtonClicked", "rewindableOnly", "", "trackDateRangeFilterCleared", "trackDateRangeFilterSelected", "dateRange", "Landroidx/core/util/Pair;", "", "trackDownloadBackupDismissButtonClicked", "trackDownloadBackupDownloadButtonClicked", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class ActivityLogTracker {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker = null;
    private final org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper = null;
    
    @javax.inject.Inject()
    public ActivityLogTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.DateTimeUtilsWrapper dateTimeUtilsWrapper) {
        super();
    }
    
    public final void trackDateRangeFilterButtonClicked(boolean rewindableOnly) {
    }
    
    public final void trackDateRangeFilterSelected(@org.jetbrains.annotations.Nullable()
    androidx.core.util.Pair<java.lang.Long, java.lang.Long> dateRange, boolean rewindableOnly) {
    }
    
    public final void trackDateRangeFilterCleared(boolean rewindableOnly) {
    }
    
    public final void trackActivityTypeFilterButtonClicked() {
    }
    
    public final void trackActivityTypeFilterSelected(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.activity.ActivityTypeModel> selectedTypes) {
    }
    
    public final void trackActivityTypeFilterCleared() {
    }
    
    public final void trackDownloadBackupDownloadButtonClicked(boolean rewindableOnly) {
    }
    
    public final void trackDownloadBackupDismissButtonClicked(boolean rewindableOnly) {
    }
}