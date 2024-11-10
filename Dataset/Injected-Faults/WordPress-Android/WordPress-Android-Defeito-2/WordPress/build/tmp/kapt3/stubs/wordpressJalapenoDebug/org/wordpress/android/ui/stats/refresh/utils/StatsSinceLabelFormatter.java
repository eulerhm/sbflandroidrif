package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

/**
 * ┌──────────────────────────────┬───────────────────────────┐
 * │ Interval                     │ Label                     │
 * ├──────────────────────────────┼───────────────────────────┤
 * │ 0 to 44 seconds              │ seconds ago               │
 * │ 45 to 89 seconds             │ a minute ago              │
 * │ 90 seconds to 44 minutes     │ 2 minutes ... 45 minutes  │
 * │ 45 to 90 minutes             │ an hour ago               │
 * │ 90 minutes to 21 hours       │ 2 hours ... 22 hours      │
 * │ 22 to 35 hours               │ a day                     │
 * │ 36 hours to 25 days          │ 2 days ... 25 days        │
 * │ 26 to 44 days                │ a month                   │
 * │ 45 days to 319 days          │ 2 months ... 11 months    │
 * │ 320 to 547 days (1.5 years)  │ a year                    │
 * │ 548 days+                    │ 2 years ... 20 years      │
 * └──────────────────────────────┴───────────────────────────┘
 *
 * Based on http://momentjs.com/docs/#/displaying/fromnow/
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0018B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J#\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0012J\u0016\u0010\u0015\u001a\u00020\f*\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsSinceLabelFormatter;", "", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "(Lorg/wordpress/android/viewmodel/ResourceProvider;)V", "getResourceProvider", "()Lorg/wordpress/android/viewmodel/ResourceProvider;", "getLabelString", "", "labelId", "", "labelTime", "", "(ILjava/lang/Long;)Ljava/lang/String;", "getPublishedSinceLabel", "date", "Ljava/time/ZonedDateTime;", "now", "Ljava/util/Date;", "getSinceLabel", "getSinceLabelLowerCase", "roundUp", "increment", "", "Interval", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsSinceLabelFormatter {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    
    @javax.inject.Inject()
    public StatsSinceLabelFormatter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.viewmodel.ResourceProvider getResourceProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSinceLabelLowerCase(@org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSinceLabel(@org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPublishedSinceLabel(@org.jetbrains.annotations.NotNull()
    java.util.Date date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPublishedSinceLabel(@org.jetbrains.annotations.NotNull()
    java.time.ZonedDateTime date, @org.jetbrains.annotations.NotNull()
    java.time.ZonedDateTime now) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSinceLabel(@org.jetbrains.annotations.NotNull()
    java.time.ZonedDateTime date, @org.jetbrains.annotations.NotNull()
    java.time.ZonedDateTime now) {
        return null;
    }
    
    private final java.lang.String getLabelString(@androidx.annotation.StringRes()
    int labelId, java.lang.Long labelTime) {
        return null;
    }
    
    private final long roundUp(long $this$roundUp, double increment) {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0082\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsSinceLabelFormatter$Interval;", "", "threshold", "Ljava/time/Duration;", "(Ljava/lang/String;ILjava/time/Duration;)V", "getThreshold", "()Ljava/time/Duration;", "SECONDS_AGO", "ONE_MINUTE_AGO", "X_MINUTES_AGO", "ONE_HOUR_AGO", "X_HOURS_AGO", "ONE_DAY_AGO", "X_DAYS_AGO", "ONE_MONTH_AGO", "X_MONTHS_AGO", "ONE_YEAR_AGO", "X_YEARS_AGO", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum Interval {
        /*public static final*/ SECONDS_AGO /* = new SECONDS_AGO(null) */,
        /*public static final*/ ONE_MINUTE_AGO /* = new ONE_MINUTE_AGO(null) */,
        /*public static final*/ X_MINUTES_AGO /* = new X_MINUTES_AGO(null) */,
        /*public static final*/ ONE_HOUR_AGO /* = new ONE_HOUR_AGO(null) */,
        /*public static final*/ X_HOURS_AGO /* = new X_HOURS_AGO(null) */,
        /*public static final*/ ONE_DAY_AGO /* = new ONE_DAY_AGO(null) */,
        /*public static final*/ X_DAYS_AGO /* = new X_DAYS_AGO(null) */,
        /*public static final*/ ONE_MONTH_AGO /* = new ONE_MONTH_AGO(null) */,
        /*public static final*/ X_MONTHS_AGO /* = new X_MONTHS_AGO(null) */,
        /*public static final*/ ONE_YEAR_AGO /* = new ONE_YEAR_AGO(null) */,
        /*public static final*/ X_YEARS_AGO /* = new X_YEARS_AGO(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.time.Duration threshold = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.stats.refresh.utils.StatsSinceLabelFormatter.Interval.Companion Companion = null;
        
        Interval(java.time.Duration threshold) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.time.Duration getThreshold() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsSinceLabelFormatter$Interval$Companion;", "", "()V", "between", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSinceLabelFormatter$Interval;", "startDate", "Ljava/time/ZonedDateTime;", "endDate", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.utils.StatsSinceLabelFormatter.Interval between(@org.jetbrains.annotations.NotNull()
            java.time.ZonedDateTime startDate, @org.jetbrains.annotations.NotNull()
            java.time.ZonedDateTime endDate) {
                return null;
            }
        }
    }
}