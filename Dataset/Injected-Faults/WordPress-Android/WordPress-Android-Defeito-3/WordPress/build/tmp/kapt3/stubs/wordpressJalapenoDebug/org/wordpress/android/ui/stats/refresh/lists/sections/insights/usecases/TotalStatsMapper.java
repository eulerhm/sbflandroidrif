package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0002#$B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0014\u0010\f\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ \u0010\u000e\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0014\u0010\u0012\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0014\u0010\u0017\u001a\u00020\u00182\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u0014\u0010\u001c\u001a\u00020\u00182\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0018\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0016\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00140\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper;", "", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;)V", "buildTotalCommentsInformation", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text;", "dates", "", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel$PeriodData;", "buildTotalCommentsValue", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueWithChartItem;", "buildTotalInformation", "type", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper$TotalStatsType;", "buildTotalLikesInformation", "buildTotalLikesValue", "getCurrentWeekDays", "", "getPreviousWeekDays", "mapToStatsType", "shouldShowCommentsGuideCard", "", "shouldShowFollowersGuideCard", "domainModel", "", "shouldShowLikesGuideCard", "shouldShowTotalInformation", "currentWeekSum", "previousWeekSum", "sum", "", "list", "Companion", "TotalStatsType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class TotalStatsMapper {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper.Companion Companion = null;
    private static final int DAY_COUNT_FOR_CURRENT_WEEK = 7;
    private static final int DAY_COUNT_FOR_PREVIOUS_WEEK = 7;
    public static final int DAY_COUNT_TOTAL = 14;
    
    @javax.inject.Inject()
    public TotalStatsMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueWithChartItem buildTotalLikesValue(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueWithChartItem buildTotalCommentsValue(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates) {
        return null;
    }
    
    private final boolean shouldShowTotalInformation(long currentWeekSum, long previousWeekSum) {
        return false;
    }
    
    public final boolean shouldShowCommentsGuideCard(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates) {
        return false;
    }
    
    public final boolean shouldShowFollowersGuideCard(int domainModel) {
        return false;
    }
    
    public final boolean shouldShowLikesGuideCard(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text buildTotalLikesInformation(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text buildTotalCommentsInformation(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text buildTotalInformation(java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper.TotalStatsType type) {
        return null;
    }
    
    private final java.lang.String sum(java.util.List<java.lang.Long> list) {
        return null;
    }
    
    /**
     * Gives list of data with StatsType for the current week.
     */
    private final java.util.List<java.lang.Long> getCurrentWeekDays(java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper.TotalStatsType type) {
        return null;
    }
    
    /**
     * Gives list of data with StatsType for previous week.
     */
    private final java.util.List<java.lang.Long> getPreviousWeekDays(java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper.TotalStatsType type) {
        return null;
    }
    
    private final java.util.List<java.lang.Long> mapToStatsType(java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper.TotalStatsType type) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper$TotalStatsType;", "", "(Ljava/lang/String;I)V", "LIKES", "COMMENTS", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum TotalStatsType {
        /*public static final*/ LIKES /* = new LIKES() */,
        /*public static final*/ COMMENTS /* = new COMMENTS() */;
        
        TotalStatsType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalStatsMapper$Companion;", "", "()V", "DAY_COUNT_FOR_CURRENT_WEEK", "", "DAY_COUNT_FOR_PREVIOUS_WEEK", "DAY_COUNT_TOTAL", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}