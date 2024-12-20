package fr.free.nrw.commons.notification.models;

import java.lang.System;

/**
 * Created by root on 18.12.2017.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020\u0005H\u00d6\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006("}, d2 = {"Lfr/free/nrw/commons/notification/models/Notification;", "", "notificationType", "Lfr/free/nrw/commons/notification/models/NotificationType;", "notificationText", "", "date", "link", "iconUrl", "notificationId", "(Lfr/free/nrw/commons/notification/models/NotificationType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "getIconUrl", "setIconUrl", "getLink", "setLink", "getNotificationId", "setNotificationId", "getNotificationText", "setNotificationText", "getNotificationType", "()Lfr/free/nrw/commons/notification/models/NotificationType;", "setNotificationType", "(Lfr/free/nrw/commons/notification/models/NotificationType;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app-commons-v4.2.1-master_prodDebug"})
public final class Notification {
    @org.jetbrains.annotations.NotNull
    private fr.free.nrw.commons.notification.models.NotificationType notificationType;
    @org.jetbrains.annotations.NotNull
    private java.lang.String notificationText;
    @org.jetbrains.annotations.NotNull
    private java.lang.String date;
    @org.jetbrains.annotations.NotNull
    private java.lang.String link;
    @org.jetbrains.annotations.NotNull
    private java.lang.String iconUrl;
    @org.jetbrains.annotations.NotNull
    private java.lang.String notificationId;
    
    /**
     * Created by root on 18.12.2017.
     */
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.notification.models.Notification copy(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.notification.models.NotificationType notificationType, @org.jetbrains.annotations.NotNull
    java.lang.String notificationText, @org.jetbrains.annotations.NotNull
    java.lang.String date, @org.jetbrains.annotations.NotNull
    java.lang.String link, @org.jetbrains.annotations.NotNull
    java.lang.String iconUrl, @org.jetbrains.annotations.NotNull
    java.lang.String notificationId) {
        return null;
    }
    
    /**
     * Created by root on 18.12.2017.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Created by root on 18.12.2017.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Created by root on 18.12.2017.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Notification(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.notification.models.NotificationType notificationType, @org.jetbrains.annotations.NotNull
    java.lang.String notificationText, @org.jetbrains.annotations.NotNull
    java.lang.String date, @org.jetbrains.annotations.NotNull
    java.lang.String link, @org.jetbrains.annotations.NotNull
    java.lang.String iconUrl, @org.jetbrains.annotations.NotNull
    java.lang.String notificationId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.notification.models.NotificationType component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.notification.models.NotificationType getNotificationType() {
        return null;
    }
    
    public final void setNotificationType(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.notification.models.NotificationType p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNotificationText() {
        return null;
    }
    
    public final void setNotificationText(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDate() {
        return null;
    }
    
    public final void setDate(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLink() {
        return null;
    }
    
    public final void setLink(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getIconUrl() {
        return null;
    }
    
    public final void setIconUrl(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNotificationId() {
        return null;
    }
    
    public final void setNotificationId(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
}