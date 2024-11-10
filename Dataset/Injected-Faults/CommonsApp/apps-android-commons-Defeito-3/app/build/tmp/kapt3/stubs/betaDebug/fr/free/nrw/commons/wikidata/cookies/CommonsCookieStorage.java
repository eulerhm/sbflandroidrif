package fr.free.nrw.commons.wikidata.cookies;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007J\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00172\u0006\u0010\u0015\u001a\u00020\u0007H\u0086\u0002J\u0006\u0010\u0018\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u0012J\'\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u00072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u0017H\u0086\u0002R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lfr/free/nrw/commons/wikidata/cookies/CommonsCookieStorage;", "", "preferences", "Lfr/free/nrw/commons/kvstore/JsonKvStore;", "(Lfr/free/nrw/commons/kvstore/JsonKvStore;)V", "cookieMap", "", "", "", "Lokhttp3/Cookie;", "domains", "", "getDomains", "()Ljava/util/Set;", "gson", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "clear", "", "contains", "", "domainSpec", "get", "", "load", "save", "set", "cookies", "Companion", "app-commons-v4.2.1-main_betaDebug"})
public final class CommonsCookieStorage {
    private final fr.free.nrw.commons.kvstore.JsonKvStore preferences = null;
    private final com.google.gson.Gson gson = null;
    private final java.util.Map<java.lang.String, java.util.List<okhttp3.Cookie>> cookieMap = null;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.wikidata.cookies.CommonsCookieStorage.Companion Companion = null;
    
    public CommonsCookieStorage() {
        super();
    }
    
    public CommonsCookieStorage(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.kvstore.JsonKvStore preferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Set<java.lang.String> getDomains() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<okhttp3.Cookie> set(@org.jetbrains.annotations.NotNull
    java.lang.String domainSpec, @org.jetbrains.annotations.NotNull
    java.util.List<okhttp3.Cookie> cookies) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<okhttp3.Cookie> get(@org.jetbrains.annotations.NotNull
    java.lang.String domainSpec) {
        return null;
    }
    
    public final void clear() {
    }
    
    public final void load() {
    }
    
    public final void save() {
    }
    
    public final boolean contains(@org.jetbrains.annotations.NotNull
    java.lang.String domainSpec) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0006\u00a8\u0006\n"}, d2 = {"Lfr/free/nrw/commons/wikidata/cookies/CommonsCookieStorage$Companion;", "", "()V", "from", "Lfr/free/nrw/commons/wikidata/cookies/CommonsCookieStorage;", "map", "", "", "", "Lokhttp3/Cookie;", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final fr.free.nrw.commons.wikidata.cookies.CommonsCookieStorage from(@org.jetbrains.annotations.NotNull
        java.util.Map<java.lang.String, ? extends java.util.List<okhttp3.Cookie>> map) {
            return null;
        }
    }
}