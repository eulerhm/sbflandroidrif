package fr.free.nrw.commons.auth.csrf;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0002\"#B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J.\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aH\u0002J \u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0018H\u0007J \u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0018H\u0007J \u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u000e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u001aH\u0002R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient;", "", "sessionManager", "Lfr/free/nrw/commons/auth/SessionManager;", "csrfTokenInterface", "Lfr/free/nrw/commons/auth/csrf/CsrfTokenInterface;", "loginClient", "Lfr/free/nrw/commons/auth/login/LoginClient;", "logoutClient", "Lfr/free/nrw/commons/auth/csrf/LogoutClient;", "(Lfr/free/nrw/commons/auth/SessionManager;Lfr/free/nrw/commons/auth/csrf/CsrfTokenInterface;Lfr/free/nrw/commons/auth/login/LoginClient;Lfr/free/nrw/commons/auth/csrf/LogoutClient;)V", "csrfTokenCall", "Lretrofit2/Call;", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "retries", "", "cancel", "", "getTokenBlocking", "", "login", "username", "password", "callback", "Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient$Callback;", "retryCallback", "Lkotlin/Function0;", "request", "service", "cb", "requestToken", "retryWithLogin", "caught", "", "Callback", "Companion", "app-commons-v4.2.1-main_betaDebug"})
public final class CsrfTokenClient {
    private final fr.free.nrw.commons.auth.SessionManager sessionManager = null;
    private final fr.free.nrw.commons.auth.csrf.CsrfTokenInterface csrfTokenInterface = null;
    private final fr.free.nrw.commons.auth.login.LoginClient loginClient = null;
    private final fr.free.nrw.commons.auth.csrf.LogoutClient logoutClient = null;
    private int retries = 0;
    private retrofit2.Call<org.wikipedia.dataclient.mwapi.MwQueryResponse> csrfTokenCall;
    @org.jetbrains.annotations.NotNull
    public static final fr.free.nrw.commons.auth.csrf.CsrfTokenClient.Companion Companion = null;
    private static final java.lang.String ANON_TOKEN = "+\\";
    private static final int MAX_RETRIES = 1;
    private static final int MAX_RETRIES_OF_LOGIN_BLOCKING = 2;
    
    public CsrfTokenClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.SessionManager sessionManager, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.csrf.CsrfTokenInterface csrfTokenInterface, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.login.LoginClient loginClient, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.csrf.LogoutClient logoutClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @kotlin.jvm.Throws(exceptionClasses = {java.lang.Throwable.class})
    public final java.lang.String getTokenBlocking() throws java.lang.Throwable {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.VisibleForTesting
    public final retrofit2.Call<org.wikipedia.dataclient.mwapi.MwQueryResponse> request(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.csrf.CsrfTokenInterface service, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.csrf.CsrfTokenClient.Callback cb) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.VisibleForTesting
    public final retrofit2.Call<org.wikipedia.dataclient.mwapi.MwQueryResponse> requestToken(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.csrf.CsrfTokenInterface service, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.csrf.CsrfTokenClient.Callback cb) {
        return null;
    }
    
    private final void retryWithLogin(fr.free.nrw.commons.auth.csrf.CsrfTokenClient.Callback callback, kotlin.jvm.functions.Function0<? extends java.lang.Throwable> caught) {
    }
    
    private final void login(java.lang.String username, java.lang.String password, fr.free.nrw.commons.auth.csrf.CsrfTokenClient.Callback callback, kotlin.jvm.functions.Function0<kotlin.Unit> retryCallback) {
    }
    
    private final void cancel() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\b\u0010\t\u001a\u00020\u0003H&\u00a8\u0006\n"}, d2 = {"Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient$Callback;", "", "failure", "", "caught", "", "success", "token", "", "twoFactorPrompt", "app-commons-v4.2.1-main_betaDebug"})
    public static abstract interface Callback {
        
        public abstract void success(@org.jetbrains.annotations.Nullable
        java.lang.String token);
        
        public abstract void failure(@org.jetbrains.annotations.Nullable
        java.lang.Throwable caught);
        
        public abstract void twoFactorPrompt();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lfr/free/nrw/commons/auth/csrf/CsrfTokenClient$Companion;", "", "()V", "ANON_TOKEN", "", "MAX_RETRIES", "", "MAX_RETRIES_OF_LOGIN_BLOCKING", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}