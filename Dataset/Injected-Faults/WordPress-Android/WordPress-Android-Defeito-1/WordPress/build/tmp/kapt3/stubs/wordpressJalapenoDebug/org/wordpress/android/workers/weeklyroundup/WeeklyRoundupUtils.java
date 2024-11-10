package org.wordpress.android.workers.weeklyroundup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0004J\u0010\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0004J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupUtils;", "", "()V", "STANDARD_DATE_PATTERN", "", "WEEK_PERIOD_DATE_PATTERN", "standardFormatter", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "weekPeriodFormatter", "parseStandardDate", "Ljava/time/LocalDate;", "date", "parseWeekPeriodDate", "safelyParseDate", "formatter", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WeeklyRoundupUtils {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupUtils INSTANCE = null;
    private static final java.lang.String STANDARD_DATE_PATTERN = "yyyy-MM-dd";
    private static final java.lang.String WEEK_PERIOD_DATE_PATTERN = "yyyy\'W\'MM\'W\'dd";
    private static final java.time.format.DateTimeFormatter standardFormatter = null;
    private static final java.time.format.DateTimeFormatter weekPeriodFormatter = null;
    
    private WeeklyRoundupUtils() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate parseStandardDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.LocalDate parseWeekPeriodDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    private final java.time.LocalDate safelyParseDate(java.lang.String date, java.time.format.DateTimeFormatter formatter) {
        return null;
    }
}