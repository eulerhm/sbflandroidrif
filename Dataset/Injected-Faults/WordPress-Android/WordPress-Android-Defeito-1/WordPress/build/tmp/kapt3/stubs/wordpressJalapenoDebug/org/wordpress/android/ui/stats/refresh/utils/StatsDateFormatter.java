package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0016\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0018H\u0002J\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0018J\u0016\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0018J\u0010\u0010$\u001a\u0004\u0018\u00010\u001e2\u0006\u0010%\u001a\u00020&J\"\u0010\'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\b\b\u0002\u0010+\u001a\u00020,H\u0002J(\u0010\'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010-\u001a\u00020,2\u0006\u0010+\u001a\u00020,H\u0002J\u0016\u0010\'\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u0018R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u0014\u0010\u0011\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\nR\u0014\u0010\u0013\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateFormatter;", "", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "inputFormat", "Ljava/text/SimpleDateFormat;", "getInputFormat", "()Ljava/text/SimpleDateFormat;", "outputFormat", "Ljava/text/DateFormat;", "getOutputFormat", "()Ljava/text/DateFormat;", "outputFormatWithoutYear", "getOutputFormatWithoutYear", "outputMonthFormat", "getOutputMonthFormat", "outputYearFormat", "getOutputYearFormat", "getResourceProvider", "()Lorg/wordpress/android/viewmodel/ResourceProvider;", "dateToWeekDate", "Ljava/util/Date;", "parsedDate", "parseStatsDate", "granularity", "Lorg/wordpress/android/fluxc/network/utils/StatsGranularity;", "date", "", "printDate", "period", "printDayWithoutYear", "printGranularDate", "printStatsDate", "printTimeZone", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "printWeek", "startCalendar", "Ljava/util/Calendar;", "endCalendar", "showSecondYear", "", "showFirstYear", "startPeriod", "endPeriod", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsDateFormatter {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    
    @javax.inject.Inject()
    public StatsDateFormatter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ResourceProvider getResourceProvider() {
        return null;
    }
    
    private final java.text.SimpleDateFormat getInputFormat() {
        return null;
    }
    
    private final java.text.SimpleDateFormat getOutputMonthFormat() {
        return null;
    }
    
    private final java.text.SimpleDateFormat getOutputYearFormat() {
        return null;
    }
    
    private final java.text.DateFormat getOutputFormat() {
        return null;
    }
    
    private final java.text.SimpleDateFormat getOutputFormatWithoutYear() {
        return null;
    }
    
    /**
     * Parses the stats date and prints it in localizes readable format.
     * @param period in this format yyyy-MM-dd
     * @return localized date in the medium format, in English - Jan 5, 2019
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String printDate(@org.jetbrains.annotations.NotNull()
    java.lang.String period) {
        return null;
    }
    
    /**
     * Prints a date in the stats format - yyyy-MM-dd
     * @param date
     * @return date in stats string format
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String printStatsDate(@org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    private final java.lang.String printDate(java.util.Date date) {
        return null;
    }
    
    /**
     * Prints the given date in a localized format according to the StatsGranularity:
     * DAYS - returns Jan 1, 2019
     * WEEKS - returns Jan 1 - Jan 8
     * MONTHS - returns Jan 2019
     * YEARS - returns 2019
     * @param date to be printed
     * @param granularity defines the output format
     * @return printed date
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String printGranularDate(@org.jetbrains.annotations.NotNull()
    java.util.Date date, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity granularity) {
        return null;
    }
    
    /**
     * Prints a date in the Medium format but strips the year. For example prints only Jan 1 instead of Jan 1, 2019
     * @param date
     * @return printed date
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String printDayWithoutYear(@org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    /**
     * Prints a week with a start and end date in the week format - Jan 1 - Jan 8, 2019.
     * It also adds both years when the week is overlapping - Dec 31, 2018 - Jan 7, 2019
     * @param startPeriod First day of the week
     * @param endPeriod Last day of the week
     * @return printed week
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String printWeek(@org.jetbrains.annotations.NotNull()
    java.util.Date startPeriod, @org.jetbrains.annotations.NotNull()
    java.util.Date endPeriod) {
        return null;
    }
    
    private final java.lang.String printWeek(java.util.Calendar startCalendar, java.util.Calendar endCalendar, boolean showSecondYear) {
        return null;
    }
    
    private final java.lang.String printWeek(java.util.Calendar startCalendar, java.util.Calendar endCalendar, boolean showFirstYear, boolean showSecondYear) {
        return null;
    }
    
    /**
     * Parses the date coming from an endpoint and print the granular result.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String printGranularDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity granularity) {
        return null;
    }
    
    /**
     * Parses date coming from the endpoint in format specific for the stats granularity
     * DAYS -> the input format is yyyy-MM-dd, output is the selected date
     * WEEKS -> the input format is yyyy'W'MM'W'dd, output is the last day of the week
     * MONTHS -> the input format is yyyy-MM, output is the last day of the month
     * YEARS -> the input format is yyyy-MM-dd, output is the last day of the year
     * @param granularity selected granularity
     * @param date string date coming from the endpoints
     * @return parsed Date
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.Date parseStatsDate(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.utils.StatsGranularity granularity, @org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    private final java.util.Date dateToWeekDate(java.util.Date parsedDate) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String printTimeZone(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
}