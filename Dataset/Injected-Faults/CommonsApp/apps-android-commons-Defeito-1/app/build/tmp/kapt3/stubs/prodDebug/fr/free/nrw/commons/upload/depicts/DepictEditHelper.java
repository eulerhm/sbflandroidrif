package fr.free.nrw.commons.upload.depicts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J*\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ \u0010 \u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0017H\u0002R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\""}, d2 = {"Lfr/free/nrw/commons/upload/depicts/DepictEditHelper;", "", "notificationHelper", "Lfr/free/nrw/commons/notification/NotificationHelper;", "wikidataEditService", "Lfr/free/nrw/commons/wikidata/WikidataEditService;", "viewUtilWrapper", "Lfr/free/nrw/commons/utils/ViewUtilWrapper;", "(Lfr/free/nrw/commons/notification/NotificationHelper;Lfr/free/nrw/commons/wikidata/WikidataEditService;Lfr/free/nrw/commons/utils/ViewUtilWrapper;)V", "getNotificationHelper", "()Lfr/free/nrw/commons/notification/NotificationHelper;", "setNotificationHelper", "(Lfr/free/nrw/commons/notification/NotificationHelper;)V", "getViewUtilWrapper", "()Lfr/free/nrw/commons/utils/ViewUtilWrapper;", "setViewUtilWrapper", "(Lfr/free/nrw/commons/utils/ViewUtilWrapper;)V", "getWikidataEditService", "()Lfr/free/nrw/commons/wikidata/WikidataEditService;", "setWikidataEditService", "(Lfr/free/nrw/commons/wikidata/WikidataEditService;)V", "addDepiction", "Lio/reactivex/Observable;", "", "media", "Lfr/free/nrw/commons/Media;", "depictions", "", "", "makeDepictionEdit", "context", "Landroid/content/Context;", "showDepictionEditNotification", "result", "app-commons-v4.2.1-master_prodDebug"})
public final class DepictEditHelper {
    
    /**
     * Class for making post operations
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.wikidata.WikidataEditService wikidataEditService;
    
    /**
     * Class for creating notification
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.notification.NotificationHelper notificationHelper;
    
    /**
     * Class for showing toast
     */
    @javax.inject.Inject
    public fr.free.nrw.commons.utils.ViewUtilWrapper viewUtilWrapper;
    
    @javax.inject.Inject
    public DepictEditHelper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.notification.NotificationHelper notificationHelper, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.wikidata.WikidataEditService wikidataEditService, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.utils.ViewUtilWrapper viewUtilWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.wikidata.WikidataEditService getWikidataEditService() {
        return null;
    }
    
    public final void setWikidataEditService(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.wikidata.WikidataEditService p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.notification.NotificationHelper getNotificationHelper() {
        return null;
    }
    
    public final void setNotificationHelper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.notification.NotificationHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final fr.free.nrw.commons.utils.ViewUtilWrapper getViewUtilWrapper() {
        return null;
    }
    
    public final void setViewUtilWrapper(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.utils.ViewUtilWrapper p0) {
    }
    
    /**
     * Public interface to edit depictions
     *
     * @param context context
     * @param media media
     * @param depictions selected depictions to be added ex: ["Q12", "Q234"]
     * @return Single<Boolean>
     */
    @org.jetbrains.annotations.NotNull
    public final io.reactivex.Observable<java.lang.Boolean> makeDepictionEdit(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.Media media, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> depictions) {
        return null;
    }
    
    /**
     * Appends new depictions
     *
     * @param media media
     * @param depictions to be added
     * @return Observable<Boolean>
     */
    private final io.reactivex.Observable<java.lang.Boolean> addDepiction(fr.free.nrw.commons.Media media, java.util.List<java.lang.String> depictions) {
        return null;
    }
    
    /**
     * Helps to create notification about condition of editing depictions
     *
     * @param context context
     * @param media media
     * @param result response of result
     * @return Single<Boolean>
     */
    private final boolean showDepictionEditNotification(android.content.Context context, fr.free.nrw.commons.Media media, boolean result) {
        return false;
    }
}