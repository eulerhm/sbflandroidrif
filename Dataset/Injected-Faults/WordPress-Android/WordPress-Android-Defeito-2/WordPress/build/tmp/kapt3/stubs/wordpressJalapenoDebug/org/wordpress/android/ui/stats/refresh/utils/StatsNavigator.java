package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsNavigator;", "", "siteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "navigate", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "target", "Lorg/wordpress/android/ui/stats/refresh/NavigationTarget;", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class StatsNavigator {
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    
    @javax.inject.Inject()
    public StatsNavigator(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
        super();
    }
    
    public final void navigate(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.NavigationTarget target) {
    }
}