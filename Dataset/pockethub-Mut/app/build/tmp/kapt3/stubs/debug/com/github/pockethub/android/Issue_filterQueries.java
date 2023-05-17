package com.github.pockethub.android;

import com.meisolsson.githubsdk.model.IssueState;
import com.squareup.sqldelight.Transacter;
import kotlin.Any;
import kotlin.Boolean;
import kotlin.Long;
import kotlin.String;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u00bc\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\f0\b\"\b\b\u0000\u0010\f*\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0097\u0001\u0010\u000e\u001a\u0092\u0001\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00a2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00a2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u0002H\f0\u000fH&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\bH&JQ\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u000bH&\u00a2\u0006\u0002\u0010!J+\u0010\"\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u000bH&\u00a2\u0006\u0002\u0010#J+\u0010$\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000bH&\u00a2\u0006\u0002\u0010%J?\u0010&\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\'\u001a\u0004\u0018\u00010\u000b2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00122\b\u0010*\u001a\u0004\u0018\u00010\u0012H&\u00a2\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000bH&J+\u0010-\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u000bH&\u00a2\u0006\u0002\u0010#\u00a8\u0006."}, d2 = {"Lcom/github/pockethub/android/Issue_filterQueries;", "Lcom/squareup/sqldelight/Transacter;", "clearIssueFilterLabels", "", "clearIssueFilters", "clearLabels", "clearMilestones", "getFilterLabels", "Lcom/squareup/sqldelight/Query;", "Lcom/github/pockethub/android/GetFilterLabels;", "issue_filter_id", "", "T", "", "mapper", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "", "repo_id", "repo_id_", "name_", "color", "getFilters", "Lcom/github/pockethub/android/GetFilters;", "insertOrReplaceIssueFilter", "id", "milestone_id", "assignee_id", "open", "", "direction", "sort_type", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "insertOrReplaceIssueFilterLabel", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "insertOrReplaceLabel", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "insertOrReplaceMilestone", "title", "state", "Lcom/meisolsson/githubsdk/model/IssueState;", "number", "(Ljava/lang/Long;Ljava/lang/String;Lcom/meisolsson/githubsdk/model/IssueState;Ljava/lang/Long;Ljava/lang/Long;)V", "removeIssueFilter", "removeIssueFilterLabel", "app_debug"})
public abstract interface Issue_filterQueries extends com.squareup.sqldelight.Transacter {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.squareup.sqldelight.Query<com.github.pockethub.android.GetFilters> getFilters();
    
    @org.jetbrains.annotations.NotNull()
    public abstract <T extends java.lang.Object>com.squareup.sqldelight.Query<T> getFilterLabels(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function6<? super java.lang.String, ? super java.lang.Long, ? super java.lang.String, ? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? extends T> mapper);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.squareup.sqldelight.Query<com.github.pockethub.android.GetFilterLabels> getFilterLabels(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id);
    
    public abstract void insertOrReplaceIssueFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long assignee_id, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean open, @org.jetbrains.annotations.Nullable()
    java.lang.String direction, @org.jetbrains.annotations.Nullable()
    java.lang.String sort_type);
    
    public abstract void insertOrReplaceLabel(@org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String color);
    
    public abstract void insertOrReplaceIssueFilterLabel(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String name);
    
    public abstract void insertOrReplaceMilestone(@org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.IssueState state, @org.jetbrains.annotations.Nullable()
    java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long number);
    
    public abstract void removeIssueFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    public abstract void removeIssueFilterLabel(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String name);
    
    public abstract void clearIssueFilters();
    
    public abstract void clearMilestones();
    
    public abstract void clearLabels();
    
    public abstract void clearIssueFilterLabels();
}