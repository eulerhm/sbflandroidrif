package fr.free.nrw.commons.notification;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\f\u0010\u0011\u001a\u00020\n*\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lfr/free/nrw/commons/notification/NotificationClient;", "", "csrfTokenClient", "Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient;", "service", "Lfr/free/nrw/commons/notification/NotificationInterface;", "(Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient;Lfr/free/nrw/commons/notification/NotificationInterface;)V", "getNotifications", "Lio/reactivex/Single;", "", "Lfr/free/nrw/commons/notification/models/Notification;", "archived", "", "markNotificationAsRead", "Lio/reactivex/Observable;", "notificationId", "", "toCommonsNotification", "Lorg/wikipedia/notifications/Notification;", "app-commons-v4.2.1-master_prodDebug"})
@javax.inject.Singleton
public final class NotificationClient {
    private final fr.free.nrw.commons.auth.csrf.CsrfTokenClient csrfTokenClient = null;
    private final fr.free.nrw.commons.notification.NotificationInterface service = null;
    
    @javax.inject.Inject
    public NotificationClient(@org.jetbrains.annotations.NotNull
    @javax.inject.Named(value = "commons-csrf")
    fr.free.nrw.commons.auth.csrf.CsrfTokenClient csrfTokenClient, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.notification.NotificationInterface service) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Single<java.util.List<fr.free.nrw.commons.notification.models.Notification>> getNotifications(boolean archived) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.lang.Boolean> markNotificationAsRead(@org.jetbrains.annotations.Nullable
    java.lang.String notificationId) {
        return null;
    }
    
    private final fr.free.nrw.commons.notification.models.Notification toCommonsNotification(org.wikipedia.notifications.Notification $this$toCommonsNotification) {
        return null;
    }
}