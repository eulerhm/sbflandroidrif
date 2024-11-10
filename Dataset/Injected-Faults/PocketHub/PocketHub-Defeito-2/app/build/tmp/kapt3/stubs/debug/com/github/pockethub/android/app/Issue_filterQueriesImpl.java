package com.github.pockethub.android.app;

import com.github.pockethub.android.Database;
import com.github.pockethub.android.GetFilters;
import com.github.pockethub.android.Issue_filterQueries;
import com.github.pockethub.android.Milestones;
import com.github.pockethub.android.OrganizationsQueries;
import com.github.pockethub.android.RepositoriesQueries;
import com.github.pockethub.android.SelectUserAndOrgs;
import com.github.pockethub.android.Users;
import com.meisolsson.githubsdk.model.IssueState;
import com.squareup.sqldelight.TransacterImpl;
import com.squareup.sqldelight.db.SqlCursor;
import com.squareup.sqldelight.db.SqlDriver;
import kotlin.Any;
import kotlin.Boolean;
import kotlin.Int;
import kotlin.Long;
import kotlin.String;
import kotlin.jvm.JvmField;
import kotlin.reflect.KClass;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00018B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u00bc\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00170\n\"\b\b\u0000\u0010\u0017*\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0097\u0001\u0010\u0019\u001a\u0092\u0001\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u001d\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001c\u0012\u0015\u0012\u0013\u0018\u00010\u001d\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b( \u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(!\u0012\u0004\u0012\u0002H\u00170\u001aH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\"0\nH\u0016JQ\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010%\u001a\u0004\u0018\u00010\u001d2\b\u0010&\u001a\u0004\u0018\u00010\u001d2\b\u0010\'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010\u00162\b\u0010*\u001a\u0004\u0018\u00010\u0016H\u0016\u00a2\u0006\u0002\u0010+J+\u0010,\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0016H\u0016\u00a2\u0006\u0002\u0010-J+\u0010.\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00162\b\u0010!\u001a\u0004\u0018\u00010\u0016H\u0016\u00a2\u0006\u0002\u0010/J?\u00100\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u00101\u001a\u0004\u0018\u00010\u00162\b\u00102\u001a\u0004\u0018\u0001032\b\u0010%\u001a\u0004\u0018\u00010\u001d2\b\u00104\u001a\u0004\u0018\u00010\u001dH\u0016\u00a2\u0006\u0002\u00105J\u0010\u00106\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0016H\u0016J+\u00107\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0016H\u0016\u00a2\u0006\u0002\u0010-R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u00069"}, d2 = {"Lcom/github/pockethub/android/app/Issue_filterQueriesImpl;", "Lcom/squareup/sqldelight/TransacterImpl;", "Lcom/github/pockethub/android/Issue_filterQueries;", "database", "Lcom/github/pockethub/android/app/DatabaseImpl;", "driver", "Lcom/squareup/sqldelight/db/SqlDriver;", "(Lcom/github/pockethub/android/app/DatabaseImpl;Lcom/squareup/sqldelight/db/SqlDriver;)V", "getFilterLabels", "", "Lcom/squareup/sqldelight/Query;", "getGetFilterLabels$app_debug", "()Ljava/util/List;", "getFilters", "getGetFilters$app_debug", "clearIssueFilterLabels", "", "clearIssueFilters", "clearLabels", "clearMilestones", "Lcom/github/pockethub/android/GetFilterLabels;", "issue_filter_id", "", "T", "", "mapper", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "", "repo_id", "repo_id_", "name_", "color", "Lcom/github/pockethub/android/GetFilters;", "insertOrReplaceIssueFilter", "id", "milestone_id", "assignee_id", "open", "", "direction", "sort_type", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "insertOrReplaceIssueFilterLabel", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "insertOrReplaceLabel", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "insertOrReplaceMilestone", "title", "state", "Lcom/meisolsson/githubsdk/model/IssueState;", "number", "(Ljava/lang/Long;Ljava/lang/String;Lcom/meisolsson/githubsdk/model/IssueState;Ljava/lang/Long;Ljava/lang/Long;)V", "removeIssueFilter", "removeIssueFilterLabel", "GetFilterLabels", "app_debug"})
final class Issue_filterQueriesImpl extends com.squareup.sqldelight.TransacterImpl implements com.github.pockethub.android.Issue_filterQueries {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.squareup.sqldelight.Query<?>> getFilters = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.squareup.sqldelight.Query<?>> getFilterLabels = null;
    private final com.github.pockethub.android.app.DatabaseImpl database = null;
    private final com.squareup.sqldelight.db.SqlDriver driver = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.squareup.sqldelight.Query<?>> getGetFilters$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.squareup.sqldelight.Query<?>> getGetFilterLabels$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.sqldelight.Query<com.github.pockethub.android.GetFilters> getFilters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <T extends java.lang.Object>com.squareup.sqldelight.Query<T> getFilterLabels(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function6<? super java.lang.String, ? super java.lang.Long, ? super java.lang.String, ? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? extends T> mapper) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.sqldelight.Query<com.github.pockethub.android.GetFilterLabels> getFilterLabels(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id) {
        return null;
    }
    
    @java.lang.Override()
    public void insertOrReplaceIssueFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long assignee_id, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean open, @org.jetbrains.annotations.Nullable()
    java.lang.String direction, @org.jetbrains.annotations.Nullable()
    java.lang.String sort_type) {
    }
    
    @java.lang.Override()
    public void insertOrReplaceLabel(@org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String color) {
    }
    
    @java.lang.Override()
    public void insertOrReplaceIssueFilterLabel(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String name) {
    }
    
    @java.lang.Override()
    public void insertOrReplaceMilestone(@org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    com.meisolsson.githubsdk.model.IssueState state, @org.jetbrains.annotations.Nullable()
    java.lang.Long milestone_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long number) {
    }
    
    @java.lang.Override()
    public void removeIssueFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @java.lang.Override()
    public void removeIssueFilterLabel(@org.jetbrains.annotations.Nullable()
    java.lang.String issue_filter_id, @org.jetbrains.annotations.Nullable()
    java.lang.Long repo_id, @org.jetbrains.annotations.Nullable()
    java.lang.String name) {
    }
    
    @java.lang.Override()
    public void clearIssueFilters() {
    }
    
    @java.lang.Override()
    public void clearMilestones() {
    }
    
    @java.lang.Override()
    public void clearLabels() {
    }
    
    @java.lang.Override()
    public void clearIssueFilterLabels() {
    }
    
    public Issue_filterQueriesImpl(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.app.DatabaseImpl database, @org.jetbrains.annotations.NotNull()
    com.squareup.sqldelight.db.SqlDriver driver) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/github/pockethub/android/app/Issue_filterQueriesImpl$GetFilterLabels;", "T", "", "Lcom/squareup/sqldelight/Query;", "issue_filter_id", "", "mapper", "Lkotlin/Function1;", "Lcom/squareup/sqldelight/db/SqlCursor;", "(Lcom/github/pockethub/android/app/Issue_filterQueriesImpl;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "execute", "toString", "app_debug"})
    final class GetFilterLabels<T extends java.lang.Object> extends com.squareup.sqldelight.Query<T> {
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String issue_filter_id = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.squareup.sqldelight.db.SqlCursor execute() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public GetFilterLabels(@org.jetbrains.annotations.Nullable()
        java.lang.String issue_filter_id, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.squareup.sqldelight.db.SqlCursor, ? extends T> mapper) {
            super(null, null);
        }
    }
}