package org.wordpress.android.workers.reminder;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u000bH\u00c6\u0003J\t\u0010.\u001a\u00020\u000bH\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u0011\u00102\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u000bH\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u000eH\u00c6\u0003J\t\u00108\u001a\u00020\u000eH\u00c6\u0003J\u009d\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u00c6\u0001J\u0013\u0010:\u001a\u00020\u000e2\b\u0010;\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010<\u001a\u00020\u000bH\u00d6\u0001J\t\u0010=\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0011\u0010\u0011\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001c\u00a8\u0006>"}, d2 = {"Lorg/wordpress/android/workers/reminder/ReminderNotification;", "", "channel", "", "contentIntentBuilder", "Lkotlin/Function0;", "Landroid/app/PendingIntent;", "deleteIntentBuilder", "contentTitle", "contentText", "priority", "", "category", "autoCancel", "", "colorized", "color", "smallIcon", "firstAction", "Landroidx/core/app/NotificationCompat$Action;", "secondAction", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZZIILandroidx/core/app/NotificationCompat$Action;Landroidx/core/app/NotificationCompat$Action;)V", "getAutoCancel", "()Z", "getCategory", "()Ljava/lang/String;", "getChannel", "getColor", "()I", "getColorized", "getContentIntentBuilder", "()Lkotlin/jvm/functions/Function0;", "getContentText", "getContentTitle", "getDeleteIntentBuilder", "getFirstAction", "()Landroidx/core/app/NotificationCompat$Action;", "getPriority", "getSecondAction", "getSmallIcon", "asNotificationCompatBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "context", "Landroid/content/Context;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReminderNotification {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String channel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<android.app.PendingIntent> contentIntentBuilder = null;
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function0<android.app.PendingIntent> deleteIntentBuilder = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contentTitle = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contentText = null;
    private final int priority = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String category = null;
    private final boolean autoCancel = false;
    private final boolean colorized = false;
    private final int color = 0;
    private final int smallIcon = 0;
    @org.jetbrains.annotations.Nullable()
    private final androidx.core.app.NotificationCompat.Action firstAction = null;
    @org.jetbrains.annotations.Nullable()
    private final androidx.core.app.NotificationCompat.Action secondAction = null;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.reminder.ReminderNotification copy(@org.jetbrains.annotations.NotNull()
    java.lang.String channel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<android.app.PendingIntent> contentIntentBuilder, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<android.app.PendingIntent> deleteIntentBuilder, @org.jetbrains.annotations.NotNull()
    java.lang.String contentTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String contentText, int priority, @org.jetbrains.annotations.NotNull()
    java.lang.String category, boolean autoCancel, boolean colorized, int color, int smallIcon, @org.jetbrains.annotations.Nullable()
    androidx.core.app.NotificationCompat.Action firstAction, @org.jetbrains.annotations.Nullable()
    androidx.core.app.NotificationCompat.Action secondAction) {
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
    
    public ReminderNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String channel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<android.app.PendingIntent> contentIntentBuilder, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<android.app.PendingIntent> deleteIntentBuilder, @org.jetbrains.annotations.NotNull()
    java.lang.String contentTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String contentText, int priority, @org.jetbrains.annotations.NotNull()
    java.lang.String category, boolean autoCancel, boolean colorized, int color, int smallIcon, @org.jetbrains.annotations.Nullable()
    androidx.core.app.NotificationCompat.Action firstAction, @org.jetbrains.annotations.Nullable()
    androidx.core.app.NotificationCompat.Action secondAction) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function0<android.app.PendingIntent> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function0<android.app.PendingIntent> getContentIntentBuilder() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<android.app.PendingIntent> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<android.app.PendingIntent> getDeleteIntentBuilder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContentTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContentText() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int getPriority() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategory() {
        return null;
    }
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean getAutoCancel() {
        return false;
    }
    
    public final boolean component9() {
        return false;
    }
    
    public final boolean getColorized() {
        return false;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int getColor() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int getSmallIcon() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.core.app.NotificationCompat.Action component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.core.app.NotificationCompat.Action getFirstAction() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.core.app.NotificationCompat.Action component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.core.app.NotificationCompat.Action getSecondAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.core.app.NotificationCompat.Builder asNotificationCompatBuilder(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}