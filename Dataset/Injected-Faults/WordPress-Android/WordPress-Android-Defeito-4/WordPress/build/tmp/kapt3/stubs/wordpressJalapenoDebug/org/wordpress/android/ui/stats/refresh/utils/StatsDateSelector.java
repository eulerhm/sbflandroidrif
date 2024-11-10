package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001$B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0015\u001a\u00020\u0016J\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\u0006\u0010\u0014\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0016J\u0006\u0010\u001e\u001a\u00020\u0016J\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u001cJ\b\u0010!\u001a\u00020\"H\u0002J\u0006\u0010#\u001a\u00020\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;", "", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "siteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;)V", "_dateSelectorUiModel", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/stats/refresh/StatsViewModel$DateSelectorUiModel;", "dateSelectorData", "Landroidx/lifecycle/LiveData;", "getDateSelectorData", "()Landroidx/lifecycle/LiveData;", "selectedDate", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SectionChange;", "getSelectedDate", "clear", "", "emitValue", "currentState", "updatedState", "getDateLabelForSection", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;", "onNextDateSelected", "onPreviousDateSelected", "start", "startDate", "toStatsGranularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "updateDateSelector", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsDateSelector {
    private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider = null;
    private final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel> _dateSelectorUiModel = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel> dateSelectorData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> selectedDate = null;
    
    public StatsDateSelector(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel> getDateSelectorData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> getSelectedDate() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate startDate) {
    }
    
    public final void updateDateSelector() {
    }
    
    private final void emitValue(org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel currentState, org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel updatedState) {
    }
    
    private final java.lang.String getDateLabelForSection() {
        return null;
    }
    
    private final org.wordpress.android.fluxc.network.utils.StatsGranularity toStatsGranularity() {
        return null;
    }
    
    public final void onNextDateSelected() {
    }
    
    public final void onPreviousDateSelected() {
    }
    
    public final void clear() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate getSelectedDate() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector$Factory;", "", "selectedDateProvider", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "siteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;)V", "build", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;", "statsSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory {
        private final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider = null;
        private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
        
        @javax.inject.Inject()
        public Factory(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider selectedDateProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider siteProvider, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector build(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
            return null;
        }
    }
}