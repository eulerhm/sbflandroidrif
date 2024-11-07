package com.github.pockethub.android;

import com.meisolsson.githubsdk.model.IssueState;
import kotlin.Boolean;
import kotlin.Int;
import kotlin.Long;
import kotlin.String;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0001HR\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\tR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0011R\u0012\u0010\u0018\u001a\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\tR\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0005R\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\tR\u0014\u0010 \u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\tR\u0014\u0010\"\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010\u0005R\u0014\u0010$\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010\tR\u0014\u0010&\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\'\u0010\tR\u0014\u0010(\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010\tR\u0014\u0010*\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b+\u0010\u0005R\u0014\u0010,\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b-\u0010\u0011R\u0014\u0010.\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b/\u0010\u0005R\u0014\u00100\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b1\u0010\u0005R\u0014\u00102\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b3\u0010\u0011R\u0014\u00104\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b5\u0010\u0011R\u0014\u00106\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b7\u0010\u0011R\u0014\u00108\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b9\u0010\u0011R\u0014\u0010:\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b;\u0010\u0005R\u0014\u0010<\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b=\u0010\u0005R\u0014\u0010>\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b?\u0010\tR\u0014\u0010@\u001a\u0004\u0018\u00010AX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\bB\u0010CR\u0014\u0010D\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\bE\u0010\tR\u0014\u0010F\u001a\u0004\u0018\u00010\u0013X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\bG\u0010\u0015\u00a8\u0006I"}, d2 = {"Lcom/github/pockethub/android/GetFilters;", "", "assignee_id", "", "getAssignee_id", "()Ljava/lang/Long;", "avatarurl", "", "getAvatarurl", "()Ljava/lang/String;", "description", "getDescription", "direction", "getDirection", "fork", "", "getFork", "()Ljava/lang/Boolean;", "forks", "", "getForks", "()Ljava/lang/Integer;", "hasIssues", "getHasIssues", "id", "getId", "id_", "getId_", "id__", "getId__", "language", "getLanguage", "login", "getLogin", "milestone_id", "getMilestone_id", "mirrorUrl", "getMirrorUrl", "name", "getName", "name_", "getName_", "number", "getNumber", "open", "getOpen", "orgId", "getOrgId", "ownerId", "getOwnerId", "permissions_admin", "getPermissions_admin", "permissions_pull", "getPermissions_pull", "permissions_push", "getPermissions_push", "private", "getPrivate", "repoId", "getRepoId", "repo_id", "getRepo_id", "sort_type", "getSort_type", "state", "Lcom/meisolsson/githubsdk/model/IssueState;", "getState", "()Lcom/meisolsson/githubsdk/model/IssueState;", "title", "getTitle", "watchers", "getWatchers", "Impl", "app_debug"})
public abstract interface GetFilters {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getId();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getRepo_id();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getMilestone_id();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getAssignee_id();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Boolean getOpen();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getDirection();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getSort_type();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getTitle();
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.meisolsson.githubsdk.model.IssueState getState();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getNumber();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getId_();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getRepoId();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getOrgId();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getName();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getOwnerId();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Boolean getPrivate();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Boolean getFork();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getDescription();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Integer getForks();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Integer getWatchers();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getLanguage();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Boolean getHasIssues();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getMirrorUrl();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Boolean getPermissions_admin();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Boolean getPermissions_pull();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Boolean getPermissions_push();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getId__();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getLogin();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getName_();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getAvatarurl();
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\bT\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u00af\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\t\u0012\b\u0010 \u001a\u0004\u0018\u00010\t\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010%J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010M\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010N\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010O\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010P\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010S\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u0010\u0010T\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010V\u001a\u0004\u0018\u00010\u0019H\u00c6\u0003\u00a2\u0006\u0002\u00101J\u0010\u0010W\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010X\u001a\u0004\u0018\u00010\u0019H\u00c6\u0003\u00a2\u0006\u0002\u00101J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010Z\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\\\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u0010\u0010]\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u0010\u0010^\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u0010\u0010_\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u000b\u0010`\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010d\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\'J\u0010\u0010e\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010.J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\u00f4\u0002\u0010j\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010kJ\u0013\u0010l\u001a\u00020\t2\b\u0010m\u001a\u0004\u0018\u00010nH\u00d6\u0003J\t\u0010o\u001a\u00020\u0019H\u00d6\u0001J\b\u0010p\u001a\u00020\u0003H\u0016R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b&\u0010\'R\u0016\u0010$\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0016\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\b-\u0010.R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0096\u0004\u00a2\u0006\n\n\u0002\u00102\u001a\u0004\b0\u00101R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\b3\u0010.R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010*R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b5\u0010\'R\u0018\u0010!\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b6\u0010\'R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010*R\u0016\u0010\"\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010*R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b9\u0010\'R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010*R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010*R\u0016\u0010#\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010*R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b=\u0010\'R\u0018\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\b>\u0010.R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b?\u0010\'R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\b@\u0010\'R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\bA\u0010.R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\bB\u0010.R\u0018\u0010 \u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\bC\u0010.R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010/\u001a\u0004\bD\u0010.R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\bE\u0010\'R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010(\u001a\u0004\bF\u0010\'R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010*R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0016\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010*R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u0019X\u0096\u0004\u00a2\u0006\n\n\u0002\u00102\u001a\u0004\bK\u00101\u00a8\u0006q"}, d2 = {"Lcom/github/pockethub/android/GetFilters$Impl;", "Lcom/github/pockethub/android/GetFilters;", "id", "", "repo_id", "", "milestone_id", "assignee_id", "open", "", "direction", "sort_type", "title", "state", "Lcom/meisolsson/githubsdk/model/IssueState;", "number", "id_", "repoId", "orgId", "name", "ownerId", "private", "fork", "description", "forks", "", "watchers", "language", "hasIssues", "mirrorUrl", "permissions_admin", "permissions_pull", "permissions_push", "id__", "login", "name_", "avatarurl", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/meisolsson/githubsdk/model/IssueState;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAssignee_id", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAvatarurl", "()Ljava/lang/String;", "getDescription", "getDirection", "getFork", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getForks", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHasIssues", "getId", "getId_", "getId__", "getLanguage", "getLogin", "getMilestone_id", "getMirrorUrl", "getName", "getName_", "getNumber", "getOpen", "getOrgId", "getOwnerId", "getPermissions_admin", "getPermissions_pull", "getPermissions_push", "getPrivate", "getRepoId", "getRepo_id", "getSort_type", "getState", "()Lcom/meisolsson/githubsdk/model/IssueState;", "getTitle", "getWatchers", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/meisolsson/githubsdk/model/IssueState;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/github/pockethub/android/GetFilters$Impl;", "equals", "other", "", "hashCode", "toString", "app_debug"})
    public static final class Impl implements com.github.pockethub.android.GetFilters {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String id = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long repo_id = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long milestone_id = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long assignee_id = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Boolean open = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String direction = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String sort_type = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final com.meisolsson.githubsdk.model.IssueState state = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long number = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long id_ = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long repoId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long orgId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long ownerId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Boolean fork = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String description = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer forks = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer watchers = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String language = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Boolean hasIssues = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String mirrorUrl = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Boolean permissions_admin = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Boolean permissions_pull = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Boolean permissions_push = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long id__ = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String login = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String name_ = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String avatarurl = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String getId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getRepo_id() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getMilestone_id() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getAssignee_id() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Boolean getOpen() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getDirection() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getSort_type() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public com.meisolsson.githubsdk.model.IssueState getState() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getNumber() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getId_() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getRepoId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getOrgId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getOwnerId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Boolean getPrivate() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Boolean getFork() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getForks() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Integer getWatchers() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getLanguage() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Boolean getHasIssues() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getMirrorUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Boolean getPermissions_admin() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Boolean getPermissions_pull() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Boolean getPermissions_push() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getId__() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getLogin() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getName_() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.String getAvatarurl() {
            return null;
        }
        
        public Impl(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.Nullable()
        java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long assignee_id, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean open, @org.jetbrains.annotations.Nullable()
        java.lang.String direction, @org.jetbrains.annotations.Nullable()
        java.lang.String sort_type, @org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        com.meisolsson.githubsdk.model.IssueState state, @org.jetbrains.annotations.Nullable()
        java.lang.Long number, @org.jetbrains.annotations.Nullable()
        java.lang.Long id_, @org.jetbrains.annotations.Nullable()
        java.lang.Long repoId, @org.jetbrains.annotations.Nullable()
        java.lang.Long orgId, @org.jetbrains.annotations.Nullable()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.Long ownerId, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean p15_1990234817, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean fork, @org.jetbrains.annotations.Nullable()
        java.lang.String description, @org.jetbrains.annotations.Nullable()
        java.lang.Integer forks, @org.jetbrains.annotations.Nullable()
        java.lang.Integer watchers, @org.jetbrains.annotations.Nullable()
        java.lang.String language, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean hasIssues, @org.jetbrains.annotations.Nullable()
        java.lang.String mirrorUrl, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_admin, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_pull, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_push, @org.jetbrains.annotations.Nullable()
        java.lang.Long id__, @org.jetbrains.annotations.Nullable()
        java.lang.String login, @org.jetbrains.annotations.Nullable()
        java.lang.String name_, @org.jetbrains.annotations.Nullable()
        java.lang.String avatarurl) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.meisolsson.githubsdk.model.IssueState component9() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component10() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component11() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component12() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component14() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component15() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component16() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component17() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component18() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component19() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component20() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component21() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component22() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component23() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component24() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component25() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component26() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component27() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component28() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component29() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component30() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.github.pockethub.android.GetFilters.Impl copy(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.Nullable()
        java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long assignee_id, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean open, @org.jetbrains.annotations.Nullable()
        java.lang.String direction, @org.jetbrains.annotations.Nullable()
        java.lang.String sort_type, @org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        com.meisolsson.githubsdk.model.IssueState state, @org.jetbrains.annotations.Nullable()
        java.lang.Long number, @org.jetbrains.annotations.Nullable()
        java.lang.Long id_, @org.jetbrains.annotations.Nullable()
        java.lang.Long repoId, @org.jetbrains.annotations.Nullable()
        java.lang.Long orgId, @org.jetbrains.annotations.Nullable()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.Long ownerId, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean p15_1990234817, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean fork, @org.jetbrains.annotations.Nullable()
        java.lang.String description, @org.jetbrains.annotations.Nullable()
        java.lang.Integer forks, @org.jetbrains.annotations.Nullable()
        java.lang.Integer watchers, @org.jetbrains.annotations.Nullable()
        java.lang.String language, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean hasIssues, @org.jetbrains.annotations.Nullable()
        java.lang.String mirrorUrl, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_admin, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_pull, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_push, @org.jetbrains.annotations.Nullable()
        java.lang.Long id__, @org.jetbrains.annotations.Nullable()
        java.lang.String login, @org.jetbrains.annotations.Nullable()
        java.lang.String name_, @org.jetbrains.annotations.Nullable()
        java.lang.String avatarurl) {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
}