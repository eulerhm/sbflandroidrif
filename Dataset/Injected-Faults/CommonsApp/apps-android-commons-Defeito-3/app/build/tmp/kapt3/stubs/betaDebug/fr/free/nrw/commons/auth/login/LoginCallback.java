package fr.free.nrw.commons.auth.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u001a\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&\u00a8\u0006\r"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginCallback;", "", "error", "", "caught", "", "passwordResetPrompt", "token", "", "success", "loginResult", "Lfr/free/nrw/commons/auth/login/LoginResult;", "twoFactorPrompt", "app-commons-v4.2.1-main_betaDebug"})
public abstract interface LoginCallback {
    
    public abstract void success(@org.jetbrains.annotations.NotNull
    fr.free.nrw.commons.auth.login.LoginResult loginResult);
    
    public abstract void twoFactorPrompt(@org.jetbrains.annotations.NotNull
    java.lang.Throwable caught, @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    public abstract void passwordResetPrompt(@org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    public abstract void error(@org.jetbrains.annotations.NotNull
    java.lang.Throwable caught);
}