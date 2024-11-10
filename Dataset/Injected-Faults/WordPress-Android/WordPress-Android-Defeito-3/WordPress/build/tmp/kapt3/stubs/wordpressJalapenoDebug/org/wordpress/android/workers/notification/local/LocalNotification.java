package org.wordpress.android.workers.notification.local;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u00018Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\t\u0012\b\b\u0001\u0010\u000b\u001a\u00020\t\u0012\b\b\u0001\u0010\f\u001a\u00020\t\u0012\b\b\u0001\u0010\r\u001a\u00020\t\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\u0011J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u0010\u0010\'\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0007H\u00c6\u0003J\t\u0010*\u001a\u00020\tH\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\t\u0010-\u001a\u00020\tH\u00c6\u0003J\t\u0010.\u001a\u00020\tH\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u0082\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\t2\b\b\u0003\u0010\u000b\u001a\u00020\t2\b\b\u0003\u0010\f\u001a\u00020\t2\b\b\u0003\u0010\r\u001a\u00020\t2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\tH\u00c6\u0001\u00a2\u0006\u0002\u00101J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00105\u001a\u00020\tH\u00d6\u0001J\t\u00106\u001a\u000207H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\u000f\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001f\u0010\u001dR\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b$\u0010\u001d\u00a8\u00069"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotification;", "", "type", "Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;", "delay", "", "delayUnits", "Ljava/util/concurrent/TimeUnit;", "title", "", "text", "icon", "firstActionIcon", "firstActionTitle", "secondActionIcon", "secondActionTitle", "uniqueId", "(Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;JLjava/util/concurrent/TimeUnit;IIIIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getDelay", "()J", "getDelayUnits", "()Ljava/util/concurrent/TimeUnit;", "getFirstActionIcon", "()I", "getFirstActionTitle", "getIcon", "id", "getId", "getSecondActionIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSecondActionTitle", "getText", "getTitle", "getType", "()Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;", "getUniqueId", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;JLjava/util/concurrent/TimeUnit;IIIIILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lorg/wordpress/android/workers/notification/local/LocalNotification;", "equals", "", "other", "hashCode", "toString", "", "Type", "org.wordpress.android_wordpressJalapenoDebug"})
public final class LocalNotification {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.workers.notification.local.LocalNotification.Type type = null;
    private final long delay = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.concurrent.TimeUnit delayUnits = null;
    private final int title = 0;
    private final int text = 0;
    private final int icon = 0;
    private final int firstActionIcon = 0;
    private final int firstActionTitle = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer secondActionIcon = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer secondActionTitle = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer uniqueId = null;
    private final int id = 0;
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.notification.local.LocalNotification copy(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotification.Type type, long delay, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.TimeUnit delayUnits, @androidx.annotation.StringRes()
    int title, @androidx.annotation.StringRes()
    int text, @androidx.annotation.DrawableRes()
    int icon, @androidx.annotation.DrawableRes()
    int firstActionIcon, @androidx.annotation.StringRes()
    int firstActionTitle, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.DrawableRes()
    java.lang.Integer secondActionIcon, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.StringRes()
    java.lang.Integer secondActionTitle, @org.jetbrains.annotations.Nullable()
    java.lang.Integer uniqueId) {
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
    
    public LocalNotification(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.notification.local.LocalNotification.Type type, long delay, @org.jetbrains.annotations.NotNull()
    java.util.concurrent.TimeUnit delayUnits, @androidx.annotation.StringRes()
    int title, @androidx.annotation.StringRes()
    int text, @androidx.annotation.DrawableRes()
    int icon, @androidx.annotation.DrawableRes()
    int firstActionIcon, @androidx.annotation.StringRes()
    int firstActionTitle, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.DrawableRes()
    java.lang.Integer secondActionIcon, @org.jetbrains.annotations.Nullable()
    @androidx.annotation.StringRes()
    java.lang.Integer secondActionTitle, @org.jetbrains.annotations.Nullable()
    java.lang.Integer uniqueId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.notification.local.LocalNotification.Type component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.notification.local.LocalNotification.Type getType() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long getDelay() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.concurrent.TimeUnit component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.concurrent.TimeUnit getDelayUnits() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getTitle() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int getText() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int getIcon() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int getFirstActionIcon() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int getFirstActionTitle() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getSecondActionIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getSecondActionTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getUniqueId() {
        return null;
    }
    
    public final int getId() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;", "", "tag", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTag", "()Ljava/lang/String;", "CREATE_SITE", "BLOGGING_PROMPTS_ONBOARDING", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum Type {
        /*public static final*/ CREATE_SITE /* = new CREATE_SITE(null) */,
        /*public static final*/ BLOGGING_PROMPTS_ONBOARDING /* = new BLOGGING_PROMPTS_ONBOARDING(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String tag = null;
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.workers.notification.local.LocalNotification.Type.Companion Companion = null;
        
        Type(java.lang.String tag) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTag() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/workers/notification/local/LocalNotification$Type$Companion;", "", "()V", "fromTag", "Lorg/wordpress/android/workers/notification/local/LocalNotification$Type;", "tag", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.workers.notification.local.LocalNotification.Type fromTag(@org.jetbrains.annotations.Nullable()
            java.lang.String tag) {
                return null;
            }
        }
    }
}