package com.github.pockethub.android;

import kotlin.Boolean;
import kotlin.Long;
import kotlin.String;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0001\u0016R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/github/pockethub/android/Issue_filters;", "", "assignee_id", "", "getAssignee_id", "()Ljava/lang/Long;", "direction", "", "getDirection", "()Ljava/lang/String;", "id", "getId", "milestone_id", "getMilestone_id", "open", "", "getOpen", "()Ljava/lang/Boolean;", "repo_id", "getRepo_id", "sort_type", "getSort_type", "Impl", "app_debug"})
public abstract interface Issue_filters {
    
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
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u001d\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J`\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010$H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\b\u0010\'\u001a\u00020\u0003H\u0016R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0013\u0010\u000eR\u0018\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0017\u0010\u000eR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011\u00a8\u0006("}, d2 = {"Lcom/github/pockethub/android/Issue_filters$Impl;", "Lcom/github/pockethub/android/Issue_filters;", "id", "", "repo_id", "", "milestone_id", "assignee_id", "open", "", "direction", "sort_type", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getAssignee_id", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDirection", "()Ljava/lang/String;", "getId", "getMilestone_id", "getOpen", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getRepo_id", "getSort_type", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lcom/github/pockethub/android/Issue_filters$Impl;", "equals", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Impl implements com.github.pockethub.android.Issue_filters {
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
        
        public Impl(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.Nullable()
        java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long assignee_id, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean open, @org.jetbrains.annotations.Nullable()
        java.lang.String direction, @org.jetbrains.annotations.Nullable()
        java.lang.String sort_type) {
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
        
        @org.jetbrains.annotations.NotNull()
        public final com.github.pockethub.android.Issue_filters.Impl copy(@org.jetbrains.annotations.NotNull()
        java.lang.String id, @org.jetbrains.annotations.Nullable()
        java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long assignee_id, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean open, @org.jetbrains.annotations.Nullable()
        java.lang.String direction, @org.jetbrains.annotations.Nullable()
        java.lang.String sort_type) {
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