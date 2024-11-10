package fr.free.nrw.commons.auth.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fR\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginResponse;", "", "()V", "clientLogin", "Lfr/free/nrw/commons/auth/login/ClientLogin;", "error", "Lorg/wikipedia/dataclient/mwapi/MwServiceError;", "getError", "()Lorg/wikipedia/dataclient/mwapi/MwServiceError;", "toLoginResult", "Lfr/free/nrw/commons/auth/login/LoginResult;", "password", "", "app-commons-v4.2.1-main_betaDebug"})
public final class LoginResponse {
    @org.jetbrains.annotations.Nullable
    @com.google.gson.annotations.SerializedName(value = "error")
    private final org.wikipedia.dataclient.mwapi.MwServiceError error = null;
    @com.google.gson.annotations.SerializedName(value = "clientlogin")
    private final fr.free.nrw.commons.auth.login.ClientLogin clientLogin = null;
    
    public LoginResponse() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final org.wikipedia.dataclient.mwapi.MwServiceError getError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.auth.login.LoginResult toLoginResult(@org.jetbrains.annotations.NotNull
    java.lang.String password) {
        return null;
    }
}