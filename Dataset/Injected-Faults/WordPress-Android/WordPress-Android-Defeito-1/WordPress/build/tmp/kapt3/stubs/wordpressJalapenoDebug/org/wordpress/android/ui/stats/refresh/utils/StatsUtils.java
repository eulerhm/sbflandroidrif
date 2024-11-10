package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J/\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0013JG\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00152\b\b\u0001\u0010\u0016\u001a\u00020\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00152\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0015\u00a2\u0006\u0002\u0010\u001bJ$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u00152\b\b\u0001\u0010\u0016\u001a\u00020\u000b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0015J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0011H\u0002J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J3\u0010\"\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u00112\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010$\u001a\u00020\u0011H\u0002\u00a2\u0006\u0002\u0010%J\u0018\u0010&\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\'2\b\b\u0002\u0010(\u001a\u00020\u000bJ!\u0010&\u001a\u0004\u0018\u00010\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\'2\b\b\u0002\u0010(\u001a\u00020\u000b\u00a2\u0006\u0002\u0010)J\'\u0010&\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\'2\b\b\u0002\u0010(\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\r\u00a2\u0006\u0002\u0010+J\u0018\u0010&\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010(\u001a\u00020\u000bJ!\u0010&\u001a\u0004\u0018\u00010\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b\u00a2\u0006\u0002\u0010,J\'\u0010&\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010(\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\r\u00a2\u0006\u0002\u0010-J\u0018\u0010&\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010(\u001a\u00020\u000bJ!\u0010&\u001a\u0004\u0018\u00010\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010(\u001a\u00020\u000b\u00a2\u0006\u0002\u0010.J\'\u0010&\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010(\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\r\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t\u0012\f\u0012\n \n*\u0004\u0018\u00010\u000b0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsUtils;", "", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "localeManager", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/util/LocaleManagerWrapper;)V", "suffixes", "Ljava/util/TreeMap;", "", "kotlin.jvm.PlatformType", "", "buildChange", "", "previousValue", "value", "positive", "", "isFormattedNumber", "(Ljava/lang/Long;JZZ)Ljava/lang/String;", "getBarChartEntryContentDescriptions", "", "entryType", "entries", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$BarChartItem$Bar;", "overlappingEntryType", "overlappingEntries", "(ILjava/util/List;Ljava/lang/Integer;Ljava/util/List;)Ljava/util/List;", "getLineChartEntryContentDescriptions", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem$LineChartItem$Line;", "handleNegativeNumber", "number", "isNegative", "mapLongToString", "printNumber", "suffix", "hasDecimal", "(JZLjava/lang/Integer;Z)Ljava/lang/String;", "toFormattedString", "", "startValue", "(Ljava/lang/Double;I)Ljava/lang/String;", "defaultValue", "(Ljava/lang/Double;ILjava/lang/String;)Ljava/lang/String;", "(Ljava/lang/Integer;I)Ljava/lang/String;", "(Ljava/lang/Integer;ILjava/lang/String;)Ljava/lang/String;", "(Ljava/lang/Long;I)Ljava/lang/String;", "(Ljava/lang/Long;ILjava/lang/String;)Ljava/lang/String;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsUtils {
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.util.LocaleManagerWrapper localeManager = null;
    private final java.util.TreeMap<java.lang.Long, java.lang.Integer> suffixes = null;
    
    @javax.inject.Inject()
    public StatsUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManager) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String toFormattedString(@org.jetbrains.annotations.Nullable()
    java.lang.Long number, int startValue) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toFormattedString(@org.jetbrains.annotations.Nullable()
    java.lang.Long number, int startValue, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultValue) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String toFormattedString(@org.jetbrains.annotations.Nullable()
    java.lang.Double number, int startValue) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toFormattedString(@org.jetbrains.annotations.Nullable()
    java.lang.Double number, int startValue, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultValue) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toFormattedString(double number, int startValue) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String toFormattedString(@org.jetbrains.annotations.Nullable()
    java.lang.Integer number, int startValue) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toFormattedString(@org.jetbrains.annotations.Nullable()
    java.lang.Integer number, int startValue, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultValue) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toFormattedString(int number, int startValue) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toFormattedString(long number, int startValue) {
        return null;
    }
    
    private final java.lang.String printNumber(long number, boolean isNegative, java.lang.Integer suffix, boolean hasDecimal) {
        return null;
    }
    
    private final java.lang.String handleNegativeNumber(java.lang.String number, boolean isNegative) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getBarChartEntryContentDescriptions(@androidx.annotation.StringRes()
    int entryType, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> entries, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.StringRes()
    java.lang.Integer overlappingEntryType, @org.jetbrains.annotations.Nullable()
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.BarChartItem.Bar> overlappingEntries) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLineChartEntryContentDescriptions(@androidx.annotation.StringRes()
    int entryType, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem.LineChartItem.Line> entries) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String buildChange(@org.jetbrains.annotations.Nullable()
    java.lang.Long previousValue, long value, boolean positive, boolean isFormattedNumber) {
        return null;
    }
    
    private final java.lang.String mapLongToString(long value, boolean isFormattedNumber) {
        return null;
    }
}