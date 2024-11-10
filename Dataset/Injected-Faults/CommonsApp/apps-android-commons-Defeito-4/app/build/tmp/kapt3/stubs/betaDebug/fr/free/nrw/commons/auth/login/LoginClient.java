package fr.free.nrw.commons.auth.login;

import java.lang.System;

/**
 * Responsible for making login related requests to the server.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ(\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006JD\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015J \u0010\u001c\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000bJ\u001e\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015R\u0018\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginClient;", "", "loginInterface", "Lfr/free/nrw/commons/auth/login/LoginInterface;", "(Lfr/free/nrw/commons/auth/login/LoginInterface;)V", "loginCall", "Lretrofit2/Call;", "Lfr/free/nrw/commons/auth/login/LoginResponse;", "tokenCall", "Lorg/wikipedia/dataclient/mwapi/MwQueryResponse;", "userLanguage", "", "cancel", "", "getExtendedInfo", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "userName", "loginResult", "Lfr/free/nrw/commons/auth/login/LoginResult;", "cb", "Lfr/free/nrw/commons/auth/login/LoginCallback;", "getLoginToken", "login", "password", "retypedPassword", "twoFactorCode", "loginToken", "loginBlocking", "request", "app-commons-v4.2.1-main_betaDebug"})
public final class LoginClient {
    private final fr.free.nrw.commons.auth.login.LoginInterface loginInterface = null;
    private retrofit2.Call<org.wikipedia.dataclient.mwapi.MwQueryResponse> tokenCall;
    private retrofit2.Call<fr.free.nrw.commons.auth.login.LoginResponse> loginCall;
    
    /**
     * userLanguage
     * It holds the value of the user's device language code.
     * For example, if user's device language is English it will hold En
     * The value will be fetched when the user clicks Login Button in the LoginActivity
     */
    private java.lang.String userLanguage = "";
    
    public LoginClient(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.login.LoginInterface loginInterface) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final retrofit2.Call<org.wikipedia.dataclient.mwapi.MwQueryResponse> getLoginToken() {
        return null;
    }
    
    public final void request(@org.jetbrains.annotations.NotNull
    java.lang.String userName, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.login.LoginCallback cb) {
    }
    
    public final void login(@org.jetbrains.annotations.NotNull
    java.lang.String userName, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.Nullable
    java.lang.String retypedPassword, @org.jetbrains.annotations.Nullable
    java.lang.String twoFactorCode, @org.jetbrains.annotations.Nullable
    java.lang.String loginToken, @org.jetbrains.annotations.NotNull
    java.lang.String userLanguage, @org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.login.LoginCallback cb) {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {java.lang.Throwable.class})
    public final void loginBlocking(@org.jetbrains.annotations.NotNull
    java.lang.String userName, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.Nullable
    java.lang.String twoFactorCode) throws java.lang.Throwable {
    }
    
    private final io.reactivex.disposables.Disposable getExtendedInfo(java.lang.String userName, fr.free.nrw.commons.auth.login.LoginResult loginResult, fr.free.nrw.commons.auth.login.LoginCallback cb) {
        return null;
    }
    
    public final void cancel() {
    }
}