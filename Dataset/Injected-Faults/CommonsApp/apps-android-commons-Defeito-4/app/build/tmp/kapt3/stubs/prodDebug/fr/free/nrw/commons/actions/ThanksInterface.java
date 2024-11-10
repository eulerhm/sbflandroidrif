package fr.free.nrw.commons.actions;

import java.lang.System;

/**
 * Thanks API.
 * Context:
 * The Commons Android app lets you thank another contributor who has uploaded a great picture.
 * See https://www.mediawiki.org/wiki/Extension:Thanks
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J>\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006H\'\u00a8\u0006\n"}, d2 = {"Lfr/free/nrw/commons/actions/ThanksInterface;", "", "thank", "Lio/reactivex/Observable;", "Lfr/free/nrw/commons/actions/MwThankPostResponse;", "rev", "", "log", "token", "source", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface ThanksInterface {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=thank")
    @retrofit2.http.FormUrlEncoded
    public abstract io.reactivex.Observable<fr.free.nrw.commons.actions.MwThankPostResponse> thank(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "rev")
    java.lang.String rev, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "log")
    java.lang.String log, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Field(value = "token")
    java.lang.String token, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "source")
    java.lang.String source);
}