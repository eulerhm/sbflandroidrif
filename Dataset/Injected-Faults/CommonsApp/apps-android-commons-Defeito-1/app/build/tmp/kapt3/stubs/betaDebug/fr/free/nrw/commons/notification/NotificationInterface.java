package fr.free.nrw.commons.notification;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006H\'J2\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0006H\'\u00a8\u0006\r"}, d2 = {"Lfr/free/nrw/commons/notification/NotificationInterface;", "", "getAllNotifications", "Lio/reactivex/Observable;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "wikiList", "", "filter", "continueStr", "markRead", "token", "readList", "unreadList", "app-commons-v4.2.1-main_betaDebug"})
public abstract interface NotificationInterface {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=query&meta=notifications&notformat=model&notlimit=max")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    public abstract io.reactivex.Observable<org.wikipedia.dataclient.mwapi.MwQueryResponse> getAllNotifications(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Query(value = "notwikis")
    java.lang.String wikiList, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Query(value = "notfilter")
    java.lang.String filter, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Query(value = "notcontinue")
    java.lang.String continueStr);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=echomarkread")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    @retrofit2.http.FormUrlEncoded
    public abstract io.reactivex.Observable<org.wikipedia.dataclient.mwapi.MwQueryResponse> markRead(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "token")
    java.lang.String token, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "list")
    java.lang.String readList, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "unreadlist")
    java.lang.String unreadList);
}