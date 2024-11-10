package fr.free.nrw.commons.wikidata.cookies;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J.\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH\u0016J\u0014\u0010\u0013\u001a\u00020\f*\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\f\u0010\u0014\u001a\u00020\u0015*\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lfr/free/nrw/commons/wikidata/cookies/CommonsCookieJar;", "Lokhttp3/CookieJar;", "cookieStorage", "Lfr/free/nrw/commons/wikidata/cookies/CommonsCookieStorage;", "(Lfr/free/nrw/commons/wikidata/cookies/CommonsCookieStorage;)V", "buildCookieList", "", "outList", "", "Lokhttp3/Cookie;", "inList", "prefix", "", "loadForRequest", "", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "cookies", "domainSpec", "expiredOrDeleted", "", "app-commons-v4.2.1-main_betaDebug"})
public final class CommonsCookieJar implements okhttp3.CookieJar {
    private final fr.free.nrw.commons.wikidata.cookies.CommonsCookieStorage cookieStorage = null;
    
    public CommonsCookieJar(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.wikidata.cookies.CommonsCookieStorage cookieStorage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.util.List<okhttp3.Cookie> loadForRequest(@org.jetbrains.annotations.NotNull
    okhttp3.HttpUrl url) {
        return null;
    }
    
    @java.lang.Override
    public void saveFromResponse(@org.jetbrains.annotations.NotNull
    okhttp3.HttpUrl url, @org.jetbrains.annotations.NotNull
    java.util.List<okhttp3.Cookie> cookies) {
    }
    
    private final void buildCookieList(java.util.List<okhttp3.Cookie> outList, java.util.List<okhttp3.Cookie> inList, java.lang.String prefix) {
    }
    
    private final boolean expiredOrDeleted(okhttp3.Cookie $this$expiredOrDeleted) {
        return false;
    }
    
    private final java.lang.String domainSpec(okhttp3.Cookie $this$domainSpec, okhttp3.HttpUrl url) {
        return null;
    }
}