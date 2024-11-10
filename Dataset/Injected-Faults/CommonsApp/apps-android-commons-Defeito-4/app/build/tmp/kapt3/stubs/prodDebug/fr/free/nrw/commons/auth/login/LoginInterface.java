package fr.free.nrw.commons.auth.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\'J\u001a\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'JL\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\bH\'Jb\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\'\u00a8\u0006\u0014"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginInterface;", "", "getLoginToken", "Lretrofit2/Call;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "getUserInfo", "Lio/reactivex/Observable;", "userName", "", "postLogIn", "Lfr/free/nrw/commons/auth/login/LoginResponse;", "user", "pass", "token", "userLanguage", "url", "retypedPass", "twoFactorCode", "loginContinue", "", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface LoginInterface {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=query&meta=tokens&type=login")
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    public abstract retrofit2.Call<org.wikipedia.dataclient.mwapi.MwQueryResponse> getLoginToken();
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=clientlogin&rememberMe=")
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    public abstract retrofit2.Call<fr.free.nrw.commons.auth.login.LoginResponse> postLogIn(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "username")
    java.lang.String user, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "password")
    java.lang.String pass, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "logintoken")
    java.lang.String token, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "uselang")
    java.lang.String userLanguage, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "loginreturnurl")
    java.lang.String url);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=clientlogin&rememberMe=")
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.Headers(value = {"Cache-Control: no-cache"})
    public abstract retrofit2.Call<fr.free.nrw.commons.auth.login.LoginResponse> postLogIn(@org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "username")
    java.lang.String user, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "password")
    java.lang.String pass, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "retype")
    java.lang.String retypedPass, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "OATHToken")
    java.lang.String twoFactorCode, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "logintoken")
    java.lang.String token, @org.jetbrains.annotations.Nullable
    @retrofit2.http.Field(value = "uselang")
    java.lang.String userLanguage, @retrofit2.http.Field(value = "logincontinue")
    boolean loginContinue);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "w/api.php?format=json&formatversion=2&errorformat=plaintext&action=query&meta=userinfo&list=users&usprop=groups|cancreate")
    public abstract io.reactivex.Observable<org.wikipedia.dataclient.mwapi.MwQueryResponse> getUserInfo(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "ususers")
    java.lang.String userName);
}