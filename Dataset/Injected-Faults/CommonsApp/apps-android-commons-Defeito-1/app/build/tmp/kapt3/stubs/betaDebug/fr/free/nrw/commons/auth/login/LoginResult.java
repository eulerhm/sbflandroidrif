package fr.free.nrw.commons.auth.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u001d\u001e\u001fB-\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0007R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000f\u0082\u0001\u0003 !\"\u00a8\u0006#"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginResult;", "", "status", "", "userName", "password", "message", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "groups", "", "getGroups", "()Ljava/util/Set;", "setGroups", "(Ljava/util/Set;)V", "getMessage", "()Ljava/lang/String;", "pass", "", "getPass", "()Z", "getPassword", "getStatus", "userId", "", "getUserId", "()I", "setUserId", "(I)V", "getUserName", "OAuthResult", "ResetPasswordResult", "Result", "Lfr/free/nrw/commons/auth/login/LoginResult$OAuthResult;", "Lfr/free/nrw/commons/auth/login/LoginResult$ResetPasswordResult;", "Lfr/free/nrw/commons/auth/login/LoginResult$Result;", "app-commons-v4.2.1-main_betaDebug"})
public abstract class LoginResult {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String status = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String userName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String password = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String message = null;
    private int userId = 0;
    @org.jetbrains.annotations.NotNull
    private java.util.Set<java.lang.String> groups;
    
    private LoginResult(java.lang.String status, java.lang.String userName, java.lang.String password, java.lang.String message) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPassword() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final int getUserId() {
        return 0;
    }
    
    public final void setUserId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Set<java.lang.String> getGroups() {
        return null;
    }
    
    public final void setGroups(@org.jetbrains.annotations.NotNull
    java.util.Set<java.lang.String> p0) {
    }
    
    public final boolean getPass() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginResult$Result;", "Lfr/free/nrw/commons/auth/login/LoginResult;", "status", "", "userName", "password", "message", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "app-commons-v4.2.1-main_betaDebug"})
    public static final class Result extends fr.free.nrw.commons.auth.login.LoginResult {
        
        public Result(@org.jetbrains.annotations.NotNull
        java.lang.String status, @org.jetbrains.annotations.Nullable
        java.lang.String userName, @org.jetbrains.annotations.Nullable
        java.lang.String password, @org.jetbrains.annotations.Nullable
        java.lang.String message) {
            super(null, null, null, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginResult$OAuthResult;", "Lfr/free/nrw/commons/auth/login/LoginResult;", "status", "", "userName", "password", "message", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "app-commons-v4.2.1-main_betaDebug"})
    public static final class OAuthResult extends fr.free.nrw.commons.auth.login.LoginResult {
        
        public OAuthResult(@org.jetbrains.annotations.NotNull
        java.lang.String status, @org.jetbrains.annotations.Nullable
        java.lang.String userName, @org.jetbrains.annotations.Nullable
        java.lang.String password, @org.jetbrains.annotations.Nullable
        java.lang.String message) {
            super(null, null, null, null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lfr/free/nrw/commons/auth/login/LoginResult$ResetPasswordResult;", "Lfr/free/nrw/commons/auth/login/LoginResult;", "status", "", "userName", "password", "message", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "app-commons-v4.2.1-main_betaDebug"})
    public static final class ResetPasswordResult extends fr.free.nrw.commons.auth.login.LoginResult {
        
        public ResetPasswordResult(@org.jetbrains.annotations.NotNull
        java.lang.String status, @org.jetbrains.annotations.Nullable
        java.lang.String userName, @org.jetbrains.annotations.Nullable
        java.lang.String password, @org.jetbrains.annotations.Nullable
        java.lang.String message) {
            super(null, null, null, null);
        }
    }
}