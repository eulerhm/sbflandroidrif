package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import java.lang.System;

@kotlin.Suppress(names = {"MagicNumber"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u0000 32\u00020\u0001:\u000234B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJk\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0014\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u00170\u00152!\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00170\u00152\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0016J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!J1\u0010\"\u001a\u00020#2!\u0010$\u001a\u001d\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u00170\u00152\u0006\u0010&\u001a\u00020\rJ0\u0010\'\u001a\u00020(2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\u0006\u0010&\u001a\u00020\r2\u0012\b\u0002\u0010)\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0018\u00010*J8\u0010+\u001a\u00020,2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\r2\b\b\u0002\u0010.\u001a\u00020\rJ*\u0010/\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\u0006\u0010&\u001a\u00020\rH\u0002J\u0014\u00102\u001a\u000201*\u00020\u00112\u0006\u0010&\u001a\u00020\rH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ViewsAndVisitorsMapper;", "", "statsDateFormatter", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "statsUtils", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "contentDescriptionHelper", "Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;", "(Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;Lorg/wordpress/android/ui/stats/refresh/utils/ContentDescriptionHelper;)V", "units", "", "", "buildChart", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "dates", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel$PeriodData;", "statsGranularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "onLineSelected", "Lkotlin/Function1;", "", "", "onLineChartDrawn", "Lkotlin/ParameterName;", "name", "visibleBarCount", "selectedType", "selectedItemPeriod", "buildChartLegendsBlue", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegendsBlue;", "buildChartLegendsPurple", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ChartLegendsPurple;", "buildChips", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Chips;", "onChipSelected", "position", "selectedPosition", "buildInformation", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$Text;", "navigationAction", "Lkotlin/Function0;", "buildTitle", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$ValuesItem;", "selectedItem", "startValue", "mapDatesToWeeks", "Lkotlin/Pair;", "", "getValue", "Companion", "SelectedType", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ViewsAndVisitorsMapper {
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils = null;
    private final org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper = null;
    private final java.util.List<java.lang.Integer> units = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsMapper.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTERNAL_LINK_ICON_TOKEN = "ICON";
    
    @javax.inject.Inject()
    public ViewsAndVisitorsMapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter statsDateFormatter, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsUtils statsUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper contentDescriptionHelper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ChartLegendsBlue buildChartLegendsBlue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ChartLegendsPurple buildChartLegendsPurple() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.ValuesItem buildTitle(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData selectedItem, int selectedPosition, int startValue) {
        return null;
    }
    
    private final long getValue(org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData $this$getValue, int selectedPosition) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"LongParameterList"})
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildChart(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity statsGranularity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onLineSelected, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onLineChartDrawn, int selectedType, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedItemPeriod) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Text buildInformation(@org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, int selectedPosition, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> navigationAction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.Chips buildChips(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onChipSelected, int selectedPosition) {
        return null;
    }
    
    private final kotlin.Pair<java.lang.Long, java.lang.Long> mapDatesToWeeks(java.util.List<org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData> dates, int selectedPosition) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ViewsAndVisitorsMapper$SelectedType;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "Views", "Visitors", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum SelectedType {
        /*public static final*/ Views /* = new Views(0) */,
        /*public static final*/ Visitors /* = new Visitors(0) */;
        private final int value = 0;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsMapper.SelectedType.Companion Companion = null;
        
        SelectedType(int value) {
        }
        
        public final int getValue() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u0004\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ViewsAndVisitorsMapper$SelectedType$Companion;", "", "()V", "getColor", "", "selectedType", "getFillDrawable", "valueOf", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ViewsAndVisitorsMapper$SelectedType;", "value", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsMapper.SelectedType valueOf(int value) {
                return null;
            }
            
            public final int getColor(int selectedType) {
                return 0;
            }
            
            public final int getFillDrawable(int selectedType) {
                return 0;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ViewsAndVisitorsMapper$Companion;", "", "()V", "EXTERNAL_LINK_ICON_TOKEN", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}