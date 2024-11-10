package org.wordpress.android.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n2\u0006\u0010\f\u001a\u00020\bJ\u0016\u0010\r\u001a\n \u000b*\u0004\u0018\u00010\n0\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nJ\u0010\u0010\u0014\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/wordpress/android/util/DateTimeUtilsWrapper;", "", "localeManagerWrapper", "Lorg/wordpress/android/util/LocaleManagerWrapper;", "appContext", "Landroid/content/Context;", "(Lorg/wordpress/android/util/LocaleManagerWrapper;Landroid/content/Context;)V", "currentTimeInIso8601", "", "dateFromIso8601", "Ljava/util/Date;", "kotlin.jvm.PlatformType", "date", "dateFromTimestamp", "timestamp", "", "daysBetween", "", "start", "end", "javaDateToTimeSpan", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DateTimeUtilsWrapper {
    private final org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper = null;
    private final android.content.Context appContext = null;
    
    @javax.inject.Inject()
    public DateTimeUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.LocaleManagerWrapper localeManagerWrapper, @org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentTimeInIso8601() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String javaDateToTimeSpan(@org.jetbrains.annotations.Nullable()
    java.util.Date date) {
        return null;
    }
    
    public final java.util.Date dateFromIso8601(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    public final int daysBetween(@org.jetbrains.annotations.NotNull()
    java.util.Date start, @org.jetbrains.annotations.NotNull()
    java.util.Date end) {
        return 0;
    }
    
    public final java.util.Date dateFromTimestamp(long timestamp) {
        return null;
    }
}