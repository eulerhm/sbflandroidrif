package org.wordpress.android.ui.stats.refresh.lists.detail;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ1\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0002\u0010\u0011J]\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0014\u0010\u0018\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u001a0\u00192!\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\f\u00a2\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001a0\u0019J \u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00162\b\u0010\"\u001a\u0004\u0018\u00010\u00162\u0006\u0010#\u001a\u00020\u000fJ\u0018\u0010$\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDayViewsMapper;", "", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;)V", "buildChange", "", "previousValue", "", "value", "positive", "", "isFormattedNumber", "(Ljava/lang/Integer;IZZ)Ljava/lang/String;", "buildChart", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "dayViews", "Lorg/wordpress/android/fluxc/model/stats/PostDetailStatsModel$Day;", "selectedDay", "onBarSelected", "Lkotlin/Function1;", "", "onBarChartDrawn", "Lkotlin/ParameterName;", "name", "visibleBarCount", "buildTitle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValueItem;", "selectedItem", "previousItem", "isLast", "mapIntToString", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostDayViewsMapper {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    
    @javax.inject.Inject()
    public PostDayViewsMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValueItem buildTitle(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.PostDetailStatsModel.Day selectedItem, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.stats.PostDetailStatsModel.Day previousItem, boolean isLast) {
        return null;
    }
    
    private final java.lang.String buildChange(java.lang.Integer previousValue, int value, boolean positive, boolean isFormattedNumber) {
        return null;
    }
    
    private final java.lang.String mapIntToString(int value, boolean isFormattedNumber) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildChart(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.PostDetailStatsModel.Day> dayViews, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedDay, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onBarSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onBarChartDrawn) {
        return null;
    }
}