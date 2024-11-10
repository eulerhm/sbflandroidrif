package org.wordpress.android.ui.stats.refresh.lists.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0004()*+B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ0\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002JF\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00172\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020 0\u001fJ0\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J>\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020#0\u00172\u0006\u0010&\u001a\u00020\'2\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020 0\u001fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper;", "", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;)V", "mapWeek", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "week", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$WeekUiModel;", "index", "", "size", "isNextNotExpanded", "", "header", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Header;", "mapWeeks", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "weeks", "Lorg/wordpress/android/fluxc/model/stats/PostDetailStatsModel$Week;", "visibleCount", "uiState", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$ExpandedWeekUiState;", "onUiState", "Lkotlin/Function1;", "", "mapYear", "year", "Lorg/wordpress/android/fluxc/model/stats/PostDetailStatsModel$Year;", "mapYears", "shownYears", "expandedYearUiState", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$ExpandedYearUiState;", "DayUiModel", "ExpandedWeekUiState", "ExpandedYearUiState", "WeekUiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostDetailMapper {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    
    @javax.inject.Inject()
    public PostDetailMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> mapYears(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.PostDetailStatsModel.Year> shownYears, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.ExpandedYearUiState expandedYearUiState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.ExpandedYearUiState, kotlin.Unit> onUiState) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon mapYear(org.wordpress.android.fluxc.model.stats.PostDetailStatsModel.Year year, int index, int size, boolean isNextNotExpanded, org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> mapWeeks(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.PostDetailStatsModel.Week> weeks, int visibleCount, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.ExpandedWeekUiState uiState, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.ExpandedWeekUiState, kotlin.Unit> onUiState) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon mapWeek(org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.WeekUiModel week, int index, int size, boolean isNextNotExpanded, org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Header header) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$DayUiModel;", "", "date", "Ljava/util/Date;", "average", "", "(Ljava/util/Date;I)V", "getAverage", "()I", "getDate", "()Ljava/util/Date;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class DayUiModel {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date date = null;
        private final int average = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.DayUiModel copy(@org.jetbrains.annotations.NotNull()
        java.util.Date date, int average) {
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
        
        public DayUiModel(@org.jetbrains.annotations.NotNull()
        java.util.Date date, int average) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date getDate() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getAverage() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\tH\u00c6\u0003J9\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$WeekUiModel;", "", "firstDay", "Ljava/util/Date;", "lastDay", "days", "", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$DayUiModel;", "weekAverage", "", "(Ljava/util/Date;Ljava/util/Date;Ljava/util/List;I)V", "getDays", "()Ljava/util/List;", "getFirstDay", "()Ljava/util/Date;", "getLastDay", "getWeekAverage", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    static final class WeekUiModel {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Date firstDay = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date lastDay = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.DayUiModel> days = null;
        private final int weekAverage = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.WeekUiModel copy(@org.jetbrains.annotations.NotNull()
        java.util.Date firstDay, @org.jetbrains.annotations.Nullable()
        java.util.Date lastDay, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.DayUiModel> days, int weekAverage) {
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
        
        public WeekUiModel(@org.jetbrains.annotations.NotNull()
        java.util.Date firstDay, @org.jetbrains.annotations.Nullable()
        java.util.Date lastDay, @org.jetbrains.annotations.NotNull()
        java.util.List<org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.DayUiModel> days, int weekAverage) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Date getFirstDay() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getLastDay() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.DayUiModel> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.DayUiModel> getDays() {
            return null;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getWeekAverage() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$ExpandedYearUiState;", "", "expandedYear", "", "(Ljava/lang/Integer;)V", "getExpandedYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "copy", "(Ljava/lang/Integer;)Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$ExpandedYearUiState;", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ExpandedYearUiState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer expandedYear = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.ExpandedYearUiState copy(@org.jetbrains.annotations.Nullable()
        java.lang.Integer expandedYear) {
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
        
        public ExpandedYearUiState() {
            super();
        }
        
        public ExpandedYearUiState(@org.jetbrains.annotations.Nullable()
        java.lang.Integer expandedYear) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getExpandedYear() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDetailMapper$ExpandedWeekUiState;", "", "expandedWeekFirstDay", "Ljava/util/Date;", "(Ljava/util/Date;)V", "getExpandedWeekFirstDay", "()Ljava/util/Date;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ExpandedWeekUiState {
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date expandedWeekFirstDay = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.detail.PostDetailMapper.ExpandedWeekUiState copy(@org.jetbrains.annotations.Nullable()
        java.util.Date expandedWeekFirstDay) {
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
        
        public ExpandedWeekUiState() {
            super();
        }
        
        public ExpandedWeekUiState(@org.jetbrains.annotations.Nullable()
        java.util.Date expandedWeekFirstDay) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getExpandedWeekFirstDay() {
            return null;
        }
    }
}