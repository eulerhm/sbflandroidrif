package org.wordpress.android.workers.weeklyroundup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\tH\u00c6\u0003J\t\u0010/\u001a\u00020\u000eH\u00c6\u0003J\t\u00100\u001a\u00020\u000eH\u00c6\u0003J}\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0003\u0010\u0010\u001a\u00020\u00032\b\b\u0003\u0010\u0011\u001a\u00020\u0003H\u00c6\u0001J\u0013\u00102\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\u0003H\u00d6\u0001J\t\u00105\u001a\u00020\tH\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018\u00a8\u00066"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupNotification;", "", "id", "", "channel", "contentIntentBuilder", "Lkotlin/Function0;", "Landroid/app/PendingIntent;", "contentTitle", "", "contentText", "priority", "category", "autoCancel", "", "colorized", "color", "smallIcon", "(IILkotlin/jvm/functions/Function0;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZZII)V", "getAutoCancel", "()Z", "getCategory", "()Ljava/lang/String;", "getChannel", "()I", "getColor", "getColorized", "getContentIntentBuilder", "()Lkotlin/jvm/functions/Function0;", "getContentText", "getContentTitle", "getId", "getPriority", "getSmallIcon", "asNotificationCompatBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "context", "Landroid/content/Context;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WeeklyRoundupNotification {
    private final int id = 0;
    private final int channel = 0;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function0<android.app.PendingIntent> contentIntentBuilder = null;
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
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.weeklyroundup.WeeklyRoundupNotification copy(int id, @androidx.annotation.StringRes()
    int channel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<android.app.PendingIntent> contentIntentBuilder, @org.jetbrains.annotations.NotNull()
    java.lang.String contentTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String contentText, int priority, @org.jetbrains.annotations.NotNull()
    java.lang.String category, boolean autoCancel, boolean colorized, @androidx.annotation.ColorRes()
    int color, @androidx.annotation.DrawableRes()
    int smallIcon) {
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
    
    public WeeklyRoundupNotification(int id, @androidx.annotation.StringRes()
    int channel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<android.app.PendingIntent> contentIntentBuilder, @org.jetbrains.annotations.NotNull()
    java.lang.String contentTitle, @org.jetbrains.annotations.NotNull()
    java.lang.String contentText, int priority, @org.jetbrains.annotations.NotNull()
    java.lang.String category, boolean autoCancel, boolean colorized, @androidx.annotation.ColorRes()
    int color, @androidx.annotation.DrawableRes()
    int smallIcon) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getChannel() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function0<android.app.PendingIntent> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function0<android.app.PendingIntent> getContentIntentBuilder() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.core.app.NotificationCompat.Builder asNotificationCompatBuilder(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}