package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/SelectedSectionManager;", "", "sharedPrefs", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "_liveSelectedSection", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "liveSelectedSection", "Landroidx/lifecycle/LiveData;", "getLiveSelectedSection", "()Landroidx/lifecycle/LiveData;", "getSelectedSection", "setSelectedSection", "", "selectedSection", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SelectedSectionManager {
    private final android.content.SharedPreferences sharedPrefs = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection> _liveSelectedSection = null;
    
    @javax.inject.Inject()
    public SelectedSectionManager(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPrefs) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection> getLiveSelectedSection() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection getSelectedSection() {
        return null;
    }
    
    public final void setSelectedSection(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection selectedSection) {
    }
}