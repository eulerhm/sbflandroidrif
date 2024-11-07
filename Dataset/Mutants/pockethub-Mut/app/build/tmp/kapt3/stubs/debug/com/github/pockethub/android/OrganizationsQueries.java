package com.github.pockethub.android;

import com.squareup.sqldelight.Transacter;
import kotlin.Any;
import kotlin.Long;
import kotlin.String;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0017\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&\u00a2\u0006\u0002\u0010\bJ5\u0010\t\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH&\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0088\u0001\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0010\"\b\b\u0000\u0010\u0012*\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00072f\u0010\u0014\u001ab\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u0002H\u00120\u0015H&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0010H&J\u0080\u0001\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0010\"\b\b\u0000\u0010\u0012*\u00020\u00132f\u0010\u0014\u001ab\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0016\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u0002H\u00120\u0015H&\u00a8\u0006\u0019"}, d2 = {"Lcom/github/pockethub/android/OrganizationsQueries;", "Lcom/squareup/sqldelight/Transacter;", "clearOrgs", "", "clearUsers", "insertOrg", "id", "", "(Ljava/lang/Long;)V", "replaceUser", "login", "", "name", "avatarurl", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "selectUser", "Lcom/squareup/sqldelight/Query;", "Lcom/github/pockethub/android/Users;", "T", "", "mapper", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "selectUserAndOrgs", "Lcom/github/pockethub/android/SelectUserAndOrgs;", "app_debug"})
public abstract interface OrganizationsQueries extends com.squareup.sqldelight.Transacter {
    
    @org.jetbrains.annotations.NotNull()
    public abstract <T extends java.lang.Object>com.squareup.sqldelight.Query<T> selectUserAndOrgs(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? extends T> mapper);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.squareup.sqldelight.Query<com.github.pockethub.android.SelectUserAndOrgs> selectUserAndOrgs();
    
    @org.jetbrains.annotations.NotNull()
    public abstract <T extends java.lang.Object>com.squareup.sqldelight.Query<T> selectUser(long id, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? extends T> mapper);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.squareup.sqldelight.Query<com.github.pockethub.android.Users> selectUser(long id);
    
    public abstract void clearOrgs();
    
    public abstract void clearUsers();
    
    public abstract void replaceUser(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.Nullable()
    java.lang.String login, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String avatarurl);
    
    public abstract void insertOrg(@org.jetbrains.annotations.Nullable()
    java.lang.Long id);
}