package org.wordpress.android.workers.reminder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00112\u00020\u0001:\u0004\u0011\u0012\u0013\u0014B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bJ\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bJ\u001c\u0010\r\u001a\u0004\u0018\u00010\b*\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\u0015\u0016\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderConfig;", "", "type", "Lorg/wordpress/android/workers/reminder/ReminderConfig$ReminderType;", "(Lorg/wordpress/android/workers/reminder/ReminderConfig$ReminderType;)V", "getType", "()Lorg/wordpress/android/workers/reminder/ReminderConfig$ReminderType;", "calculateNext", "Ljava/time/LocalDate;", "from", "toMap", "", "", "withNextDayOfWeekFrom", "days", "", "Ljava/time/DayOfWeek;", "Companion", "DailyReminder", "ReminderType", "WeeklyReminder", "Lorg/wordpress/android/workers/reminder/ReminderConfig$DailyReminder;", "Lorg/wordpress/android/workers/reminder/ReminderConfig$WeeklyReminder;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReminderConfig {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.reminder.ReminderConfig.ReminderType type = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.workers.reminder.ReminderConfig.Companion Companion = null;
    private static final java.lang.String REMINDER_TYPE = "reminder_type";
    private static final java.lang.String REMINDER_DAYS = "reminder_days";
    private static final java.lang.String DAYS_SEPARATOR = ",";
    
    private ReminderConfig(org.wordpress.android.workers.reminder.ReminderConfig.ReminderType type) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.reminder.ReminderConfig.ReminderType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.time.LocalDate calculateNext(@org.jetbrains.annotations.NotNull()
    java.time.LocalDate from) {
        return null;
    }
    
    private final java.time.LocalDate withNextDayOfWeekFrom(java.time.LocalDate $this$withNextDayOfWeekFrom, java.util.Set<? extends java.time.DayOfWeek> days) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> toMap() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderConfig$DailyReminder;", "Lorg/wordpress/android/workers/reminder/ReminderConfig;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class DailyReminder extends org.wordpress.android.workers.reminder.ReminderConfig {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.workers.reminder.ReminderConfig.DailyReminder INSTANCE = null;
        
        private DailyReminder() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderConfig$WeeklyReminder;", "Lorg/wordpress/android/workers/reminder/ReminderConfig;", "days", "", "Ljava/time/DayOfWeek;", "(Ljava/util/Set;)V", "getDays", "()Ljava/util/Set;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class WeeklyReminder extends org.wordpress.android.workers.reminder.ReminderConfig {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<java.time.DayOfWeek> days = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.workers.reminder.ReminderConfig.WeeklyReminder copy(@org.jetbrains.annotations.NotNull()
        java.util.Set<? extends java.time.DayOfWeek> days) {
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
        
        public WeeklyReminder(@org.jetbrains.annotations.NotNull()
        java.util.Set<? extends java.time.DayOfWeek> days) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.time.DayOfWeek> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<java.time.DayOfWeek> getDays() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderConfig$ReminderType;", "", "(Ljava/lang/String;I)V", "DAILY", "WEEKLY", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ReminderType {
        /*public static final*/ DAILY /* = new DAILY() */,
        /*public static final*/ WEEKLY /* = new WEEKLY() */;
        
        ReminderType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0007\u001a\u00020\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderConfig$Companion;", "", "()V", "DAYS_SEPARATOR", "", "REMINDER_DAYS", "REMINDER_TYPE", "fromMap", "Lorg/wordpress/android/workers/reminder/ReminderConfig;", "map", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.workers.reminder.ReminderConfig fromMap(@org.jetbrains.annotations.NotNull()
        java.util.Map<java.lang.String, ? extends java.lang.Object> map) {
            return null;
        }
    }
}