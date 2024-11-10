package com.github.pockethub.android;

import kotlin.Boolean;
import kotlin.Int;
import kotlin.Long;
import kotlin.String;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u001b\bf\u0018\u00002\u00020\u0001:\u0001+R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\tR\u0012\u0010\u0010\u001a\u00020\u0011X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u0011X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u0011X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\tR\u0014\u0010!\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\tR\u0014\u0010#\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b$\u0010\tR\u0014\u0010%\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010\tR\u0014\u0010\'\u001a\u0004\u0018\u00010\u0011X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u001cR\u0014\u0010)\u001a\u0004\u0018\u00010\u000bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b*\u0010\r\u00a8\u0006,"}, d2 = {"Lcom/github/pockethub/android/Repositories;", "", "description", "", "getDescription", "()Ljava/lang/String;", "fork", "", "getFork", "()Ljava/lang/Boolean;", "forks", "", "getForks", "()Ljava/lang/Integer;", "hasIssues", "getHasIssues", "id", "", "getId", "()J", "language", "getLanguage", "mirrorUrl", "getMirrorUrl", "name", "getName", "orgId", "getOrgId", "()Ljava/lang/Long;", "ownerId", "getOwnerId", "permissions_admin", "getPermissions_admin", "permissions_pull", "getPermissions_pull", "permissions_push", "getPermissions_push", "private", "getPrivate", "repoId", "getRepoId", "watchers", "getWatchers", "Impl", "app_debug"})
public abstract interface Repositories {
    
    public abstract long getId();
    
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
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b4\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u00a3\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u0016J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u000b\u00101\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u00103\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u00105\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u00106\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u00107\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u0010\u00108\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u000b\u00109\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u0010\u0010;\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010<\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001aJ\u000b\u0010=\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010>\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001dJ\u00cc\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\nH\u00c6\u0001\u00a2\u0006\u0002\u0010@J\u0013\u0010A\u001a\u00020\n2\b\u0010B\u001a\u0004\u0018\u00010CH\u00d6\u0003J\t\u0010D\u001a\u00020\u000eH\u00d6\u0001J\b\u0010E\u001a\u00020\u0007H\u0016R\u0016\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001f\u0010\u001aR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b%\u0010&R\u0018\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b(\u0010&R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\nX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b)\u0010\u001aR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\nX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b*\u0010\u001aR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\nX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b+\u0010\u001aR\u0018\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b,\u0010\u001aR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b-\u0010&R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b.\u0010\u001d\u00a8\u0006F"}, d2 = {"Lcom/github/pockethub/android/Repositories$Impl;", "Lcom/github/pockethub/android/Repositories;", "id", "", "repoId", "orgId", "name", "", "ownerId", "private", "", "fork", "description", "forks", "", "watchers", "language", "hasIssues", "mirrorUrl", "permissions_admin", "permissions_pull", "permissions_push", "(JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getDescription", "()Ljava/lang/String;", "getFork", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getForks", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHasIssues", "getId", "()J", "getLanguage", "getMirrorUrl", "getName", "getOrgId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOwnerId", "getPermissions_admin", "getPermissions_pull", "getPermissions_push", "getPrivate", "getRepoId", "getWatchers", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/github/pockethub/android/Repositories$Impl;", "equals", "other", "", "hashCode", "toString", "app_debug"})
    public static final class Impl implements com.github.pockethub.android.Repositories {
        private final long id = 0L;
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
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public long getId() {
            return 0L;
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
        
        public Impl(long id, @org.jetbrains.annotations.Nullable()
        java.lang.Long repoId, @org.jetbrains.annotations.Nullable()
        java.lang.Long orgId, @org.jetbrains.annotations.Nullable()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.Long ownerId, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean p5_1990234817, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean fork, @org.jetbrains.annotations.Nullable()
        java.lang.String description, @org.jetbrains.annotations.Nullable()
        java.lang.Integer forks, @org.jetbrains.annotations.Nullable()
        java.lang.Integer watchers, @org.jetbrains.annotations.Nullable()
        java.lang.String language, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean hasIssues, @org.jetbrains.annotations.Nullable()
        java.lang.String mirrorUrl, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_admin, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_pull, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_push) {
            super();
        }
        
        public final long component1() {
            return 0L;
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
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component9() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component10() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component11() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component12() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component14() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component15() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component16() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.github.pockethub.android.Repositories.Impl copy(long id, @org.jetbrains.annotations.Nullable()
        java.lang.Long repoId, @org.jetbrains.annotations.Nullable()
        java.lang.Long orgId, @org.jetbrains.annotations.Nullable()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        java.lang.Long ownerId, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean p5_1990234817, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean fork, @org.jetbrains.annotations.Nullable()
        java.lang.String description, @org.jetbrains.annotations.Nullable()
        java.lang.Integer forks, @org.jetbrains.annotations.Nullable()
        java.lang.Integer watchers, @org.jetbrains.annotations.Nullable()
        java.lang.String language, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean hasIssues, @org.jetbrains.annotations.Nullable()
        java.lang.String mirrorUrl, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_admin, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_pull, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permissions_push) {
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