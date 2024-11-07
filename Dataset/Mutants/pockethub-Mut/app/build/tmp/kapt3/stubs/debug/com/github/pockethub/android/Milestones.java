package com.github.pockethub.android;

import com.meisolsson.githubsdk.model.IssueState;
import com.squareup.sqldelight.ColumnAdapter;
import kotlin.Long;
import kotlin.String;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0002\u0012\u0013R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0014"}, d2 = {"Lcom/github/pockethub/android/Milestones;", "", "milestone_id", "", "getMilestone_id", "()Ljava/lang/Long;", "number", "getNumber", "repo_id", "getRepo_id", "state", "Lcom/meisolsson/githubsdk/model/IssueState;", "getState", "()Lcom/meisolsson/githubsdk/model/IssueState;", "title", "", "getTitle", "()Ljava/lang/String;", "Adapter", "Impl", "app_debug"})
public abstract interface Milestones {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getRepo_id();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String getTitle();
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.meisolsson.githubsdk.model.IssueState getState();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getMilestone_id();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Long getNumber();
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\u0002\u0010\u0006R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/github/pockethub/android/Milestones$Adapter;", "", "stateAdapter", "Lcom/squareup/sqldelight/ColumnAdapter;", "Lcom/meisolsson/githubsdk/model/IssueState;", "", "(Lcom/squareup/sqldelight/ColumnAdapter;)V", "getStateAdapter", "()Lcom/squareup/sqldelight/ColumnAdapter;", "app_debug"})
    public static final class Adapter {
        @org.jetbrains.annotations.NotNull()
        private final com.squareup.sqldelight.ColumnAdapter<com.meisolsson.githubsdk.model.IssueState, java.lang.String> stateAdapter = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.squareup.sqldelight.ColumnAdapter<com.meisolsson.githubsdk.model.IssueState, java.lang.String> getStateAdapter() {
            return null;
        }
        
        public Adapter(@org.jetbrains.annotations.NotNull()
        com.squareup.sqldelight.ColumnAdapter<com.meisolsson.githubsdk.model.IssueState, java.lang.String> stateAdapter) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJJ\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\b\u0010!\u001a\u00020\u0005H\u0016R\u0018\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\""}, d2 = {"Lcom/github/pockethub/android/Milestones$Impl;", "Lcom/github/pockethub/android/Milestones;", "repo_id", "", "title", "", "state", "Lcom/meisolsson/githubsdk/model/IssueState;", "milestone_id", "number", "(Ljava/lang/Long;Ljava/lang/String;Lcom/meisolsson/githubsdk/model/IssueState;Ljava/lang/Long;Ljava/lang/Long;)V", "getMilestone_id", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getNumber", "getRepo_id", "getState", "()Lcom/meisolsson/githubsdk/model/IssueState;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Long;Ljava/lang/String;Lcom/meisolsson/githubsdk/model/IssueState;Ljava/lang/Long;Ljava/lang/Long;)Lcom/github/pockethub/android/Milestones$Impl;", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Impl implements com.github.pockethub.android.Milestones {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long repo_id = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.Nullable()
        private final com.meisolsson.githubsdk.model.IssueState state = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long milestone_id = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long number = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getRepo_id() {
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
        public java.lang.Long getMilestone_id() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public java.lang.Long getNumber() {
            return null;
        }
        
        public Impl(@org.jetbrains.annotations.Nullable()
        java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        com.meisolsson.githubsdk.model.IssueState state, @org.jetbrains.annotations.Nullable()
        java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long number) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.meisolsson.githubsdk.model.IssueState component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.github.pockethub.android.Milestones.Impl copy(@org.jetbrains.annotations.Nullable()
        java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
        java.lang.String title, @org.jetbrains.annotations.Nullable()
        com.meisolsson.githubsdk.model.IssueState state, @org.jetbrains.annotations.Nullable()
        java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
        java.lang.Long number) {
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