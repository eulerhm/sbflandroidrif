package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ \u0010\u000b\u001a\u00020\f2\u0018\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000eJ\u0010\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J3\u0010\u0016\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152!\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u001a\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryMapper;", "", "statsSinceLabelFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSinceLabelFormatter;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsSinceLabelFormatter;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;)V", "buildBarChartItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem;", "dayViews", "", "Lkotlin/Pair;", "", "", "buildLatestPostItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ListItemWithImage;", "model", "Lorg/wordpress/android/fluxc/model/stats/InsightsLatestPostModel;", "buildMessageItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text;", "navigationAction", "Lkotlin/Function1;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase$LinkClickParams;", "Lkotlin/ParameterName;", "name", "params", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LatestPostSummaryMapper {
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSinceLabelFormatter statsSinceLabelFormatter = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    
    @javax.inject.Inject()
    public LatestPostSummaryMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSinceLabelFormatter statsSinceLabelFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ListItemWithImage buildLatestPostItem(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel model) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text buildMessageItem(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.stats.InsightsLatestPostModel model, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase.LinkClickParams, kotlin.Unit> navigationAction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem buildBarChartItem(@org.jetbrains.annotations.NotNull()
    java.util.List<kotlin.Pair<java.lang.String, java.lang.Integer>> dayViews) {
        return null;
    }
}