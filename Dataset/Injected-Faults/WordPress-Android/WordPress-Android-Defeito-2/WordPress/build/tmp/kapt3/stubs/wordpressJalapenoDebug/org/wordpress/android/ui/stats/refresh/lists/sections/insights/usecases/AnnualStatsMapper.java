package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/AnnualStatsMapper;", "", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "(Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;)V", "mapItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithIcon;", "textResource", "", "value", "", "mapYearInBlock", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "selectedYear", "Lorg/wordpress/android/fluxc/model/stats/YearsInsightsModel$YearInsights;", "mapYearInViewAll", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AnnualStatsMapper {
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    
    @javax.inject.Inject()
    public AnnualStatsMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> mapYearInBlock(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.YearsInsightsModel.YearInsights selectedYear) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> mapYearInViewAll(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.YearsInsightsModel.YearInsights selectedYear) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithIcon mapItem(int textResource, java.lang.String value) {
        return null;
    }
}