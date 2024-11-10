package com.github.pockethub.android;

import com.squareup.sqldelight.Transacter;
import kotlin.Any;
import kotlin.Boolean;
import kotlin.Int;
import kotlin.Long;
import kotlin.String;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0017\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&\u00a2\u0006\u0002\u0010\u0007J\u00a3\u0001\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000eH&\u00a2\u0006\u0002\u0010\u001aJ\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&\u00a2\u0006\u0002\u0010\u001eJ\u00be\u0003\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u001c\"\b\b\u0000\u0010\u001f*\u00020 2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0094\u0003\u0010!\u001a\u008f\u0003\u0012\u0015\u0012\u0013\u0018\u00010\u0006\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0006\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b($\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(&\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\'\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b#\u0012\b\b\n\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u0002H\u001f0\"H&\u00a2\u0006\u0002\u0010(\u00a8\u0006)"}, d2 = {"Lcom/github/pockethub/android/RepositoriesQueries;", "Lcom/squareup/sqldelight/Transacter;", "clearRepositories", "", "clearUserRepositories", "orgId", "", "(Ljava/lang/Long;)V", "replaceRepo", "repoId", "name", "", "ownerId", "private", "", "fork", "description", "forks", "", "watchers", "language", "hasIssues", "mirrorUrl", "permissions_admin", "permissions_pull", "permissions_push", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "selectUserRepositories", "Lcom/squareup/sqldelight/Query;", "Lcom/github/pockethub/android/SelectUserRepositories;", "(Ljava/lang/Long;)Lcom/squareup/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function17;", "Lkotlin/ParameterName;", "id", "login", "name_", "avatarurl", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function17;)Lcom/squareup/sqldelight/Query;", "app_debug"})
public abstract interface RepositoriesQueries extends com.squareup.sqldelight.Transacter {
    
    @org.jetbrains.annotations.NotNull()
    public abstract <T extends java.lang.Object>com.squareup.sqldelight.Query<T> selectUserRepositories(@org.jetbrains.annotations.Nullable()
    java.lang.Long orgId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function17<? super java.lang.Long, ? super java.lang.String, ? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.Boolean, ? super java.lang.Boolean, ? super java.lang.String, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.String, ? super java.lang.Boolean, ? super java.lang.String, ? super java.lang.Boolean, ? super java.lang.Boolean, ? super java.lang.Boolean, ? extends T> mapper);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.squareup.sqldelight.Query<com.github.pockethub.android.SelectUserRepositories> selectUserRepositories(@org.jetbrains.annotations.Nullable()
    java.lang.Long orgId);
    
    public abstract void clearUserRepositories(@org.jetbrains.annotations.Nullable()
    java.lang.Long orgId);
    
    public abstract void clearRepositories();
    
    public abstract void replaceRepo(@org.jetbrains.annotations.Nullable()
    java.lang.Long repoId, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.Long orgId, @org.jetbrains.annotations.Nullable()
    java.lang.Long ownerId, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean p4_1990234817, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean fork, @org.jetbrains.annotations.Nullable()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    java.lang.Integer forks, @org.jetbrains.annotations.Nullable()
    java.lang.Integer watchers, @org.jetbrains.annotations.Nullable()
    java.lang.String language, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean hasIssues, @org.jetbrains.annotations.Nullable()
    java.lang.String mirrorUrl, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean permissions_admin, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean permissions_pull, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean permissions_push);
}