package org.wordpress.android.ui.stats.refresh.lists.sections.granular;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001:\u000212B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\tJ\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u001b2\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010 \u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020#J$\u0010&\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00150)2\u0006\u0010\u0017\u001a\u00020\u0018J$\u0010&\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020\u00152\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00150)2\u0006\u0010\u0013\u001a\u00020\tJ\u0016\u0010&\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010&\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010+\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010,\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tJ\u0016\u0010-\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u000fJ\u0016\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider;", "", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "mutableDates", "", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;", "selectedDateChanged", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SectionChange;", "buildStateKey", "", "key", "clear", "", "statsSection", "getCurrentDate", "Ljava/util/Date;", "getSelectedDate", "statsGranularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "getSelectedDateState", "granularSelectedDateChanged", "Landroidx/lifecycle/LiveData;", "hasNextDate", "", "hasPreviousDate", "onDateLoadingFailed", "onDateLoadingSucceeded", "onRestoreInstanceState", "savedState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "selectDate", "updatedDate", "availableDates", "", "date", "selectNextDate", "selectPreviousDate", "setInitialSelectedPeriod", "period", "updateSelectedDate", "selectedDate", "SectionChange", "SelectedDate", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class SelectedDateProvider {
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final java.util.Map<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection, org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate> mutableDates = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> selectedDateChanged = null;
    
    @javax.inject.Inject()
    public SelectedDateProvider(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> granularSelectedDateChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
        return null;
    }
    
    public final void selectDate(@org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    public final void selectDate(@org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
    }
    
    public final void selectDate(@org.jetbrains.annotations.NotNull()
    java.util.Date updatedDate, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Date> availableDates, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    public final void selectDate(@org.jetbrains.annotations.NotNull()
    java.util.Date updatedDate, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.Date> availableDates, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
    }
    
    public final void updateSelectedDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate selectedDate, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    public final void setInitialSelectedPeriod(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity, @org.jetbrains.annotations.NotNull()
    java.lang.String period) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getSelectedDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date getSelectedDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate getSelectedDateState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate getSelectedDateState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
        return null;
    }
    
    public final boolean hasPreviousDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
        return false;
    }
    
    public final boolean hasNextDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
        return false;
    }
    
    public final void selectPreviousDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    public final void selectNextDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    public final void onDateLoadingFailed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
    }
    
    public final void onDateLoadingFailed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    public final void onDateLoadingSucceeded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity) {
    }
    
    public final void onDateLoadingSucceeded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Date getCurrentDate() {
        return null;
    }
    
    public final void clear() {
    }
    
    public final void clear(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
    }
    
    public final void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void onRestoreInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle savedState) {
    }
    
    private final java.lang.String buildStateKey(org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection key) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"ParcelCreator"})
    @kotlinx.parcelize.Parcelize()
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0001(B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J9\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007H\u00c6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\u0006\u0010\u001b\u001a\u00020\u0003J\u0006\u0010\u001c\u001a\u00020\u0017J\b\u0010\u001d\u001a\u0004\u0018\u00010\u0003J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u001f\u001a\u00020\u0007J\t\u0010 \u001a\u00020\u0017H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u0017H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;", "Landroid/os/Parcelable;", "dateValue", "Ljava/util/Date;", "availableDates", "", "loading", "", "error", "(Ljava/util/Date;Ljava/util/List;ZZ)V", "getAvailableDates", "()Ljava/util/List;", "getDateValue", "()Ljava/util/Date;", "getError", "()Z", "getLoading", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "getDate", "getDateIndex", "getNextDate", "getPreviousDate", "hasData", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SelectedDate implements android.os.Parcelable {
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date dateValue = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<java.util.Date> availableDates = null;
        private final boolean loading = false;
        private final boolean error = false;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate.Companion Companion = null;
        public static final android.os.Parcelable.Creator<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate> CREATOR = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate copy(@org.jetbrains.annotations.Nullable()
        java.util.Date dateValue, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends java.util.Date> availableDates, boolean loading, boolean error) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SelectedDate() {
            super();
        }
        
        public SelectedDate(@org.jetbrains.annotations.Nullable()
        java.util.Date dateValue, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends java.util.Date> availableDates, boolean loading, boolean error) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getDateValue() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.util.Date> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.util.Date> getAvailableDates() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean getLoading() {
            return false;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getError() {
            return false;
        }
        
        public final boolean hasData() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date getDate() {
            return null;
        }
        
        public final int getDateIndex() {
            return 0;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getPreviousDate() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getNextDate() {
            return null;
        }
        
        @java.lang.Override()
        public int describeContents() {
            return 0;
        }
        
        @java.lang.Override()
        public void writeToParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel parcel, int flags) {
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
        public static final class Creator implements android.os.Parcelable.Creator<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate> {
            
            public Creator() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate createFromParcel(@org.jetbrains.annotations.NotNull()
            android.os.Parcel in) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate[] newArray(int size) {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0017J\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate$Companion;", "Lkotlinx/parcelize/Parceler;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SelectedDate;", "()V", "create", "parcel", "Landroid/os/Parcel;", "write", "", "flags", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion implements kotlinx.parcelize.Parceler<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate> {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            @android.annotation.SuppressLint(value = {"ParcelClassLoader"})
            @java.lang.Override()
            public org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate create(@org.jetbrains.annotations.NotNull()
            android.os.Parcel parcel) {
                return null;
            }
            
            @java.lang.Override()
            public void write(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate $this$write, @org.jetbrains.annotations.NotNull()
            android.os.Parcel parcel, int flags) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SelectedDate[] newArray(int size) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SectionChange;", "", "selectedSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "(Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;)V", "getSelectedSection", "()Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SectionChange {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection selectedSection = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection selectedSection) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public SectionChange(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection selectedSection) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection getSelectedSection() {
            return null;
        }
    }
}