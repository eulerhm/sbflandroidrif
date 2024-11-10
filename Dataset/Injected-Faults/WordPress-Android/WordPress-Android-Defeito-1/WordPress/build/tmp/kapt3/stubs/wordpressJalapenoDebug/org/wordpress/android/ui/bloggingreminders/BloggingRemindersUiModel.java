package org.wordpress.android.ui.bloggingreminders;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\nH\u00c6\u0003JA\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersUiModel;", "", "siteId", "", "enabledDays", "", "Ljava/time/DayOfWeek;", "hour", "minute", "isPromptIncluded", "", "(ILjava/util/Set;IIZ)V", "getEnabledDays", "()Ljava/util/Set;", "getHour", "()I", "()Z", "getMinute", "getSiteId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "getNotificationTime", "", "getNotificationTime24hour", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingRemindersUiModel {
    private final int siteId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.time.DayOfWeek> enabledDays = null;
    private final int hour = 0;
    private final int minute = 0;
    private final boolean isPromptIncluded = false;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersUiModel copy(int siteId, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends java.time.DayOfWeek> enabledDays, int hour, int minute, boolean isPromptIncluded) {
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
    
    public BloggingRemindersUiModel(int siteId, @org.jetbrains.annotations.NotNull()
    java.util.Set<? extends java.time.DayOfWeek> enabledDays, int hour, int minute, boolean isPromptIncluded) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getSiteId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.time.DayOfWeek> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.time.DayOfWeek> getEnabledDays() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getHour() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getMinute() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean isPromptIncluded() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.CharSequence getNotificationTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.CharSequence getNotificationTime24hour() {
        return null;
    }
}