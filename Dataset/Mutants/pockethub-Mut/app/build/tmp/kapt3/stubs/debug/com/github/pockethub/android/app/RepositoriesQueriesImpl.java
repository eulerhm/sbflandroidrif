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

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00012B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0017\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016\u00a2\u0006\u0002\u0010\u0012J\u00a3\u0001\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00162\b\u0010 \u001a\u0004\u0018\u00010\u00192\b\u0010!\u001a\u0004\u0018\u00010\u00162\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010#\u001a\u0004\u0018\u00010\u00192\b\u0010$\u001a\u0004\u0018\u00010\u0019H\u0016\u00a2\u0006\u0002\u0010%J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020&0\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016\u00a2\u0006\u0002\u0010\'J\u00be\u0003\u0010\b\u001a\b\u0012\u0004\u0012\u0002H(0\n\"\b\b\u0000\u0010(*\u00020)2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0094\u0003\u0010*\u001a\u008f\u0003\u0012\u0015\u0012\u0013\u0018\u00010\u0011\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0011\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(-\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(.\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(/\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(0\u0012\u0015\u0012\u0013\u0018\u00010\u0019\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0019\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\u001d\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u001c\u0012\u0015\u0012\u0013\u0018\u00010\u001d\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0019\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b( \u0012\u0015\u0012\u0013\u0018\u00010\u0016\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(!\u0012\u0015\u0012\u0013\u0018\u00010\u0019\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(\"\u0012\u0015\u0012\u0013\u0018\u00010\u0019\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b(#\u0012\u0015\u0012\u0013\u0018\u00010\u0019\u00a2\u0006\f\b,\u0012\b\b\u0015\u0012\u0004\b\b($\u0012\u0004\u0012\u0002H(0+H\u0016\u00a2\u0006\u0002\u00101R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u00063"}, d2 = {"Lcom/github/pockethub/android/app/RepositoriesQueriesImpl;", "Lcom/squareup/sqldelight/TransacterImpl;", "Lcom/github/pockethub/android/RepositoriesQueries;", "database", "Lcom/github/pockethub/android/app/DatabaseImpl;", "driver", "Lcom/squareup/sqldelight/db/SqlDriver;", "(Lcom/github/pockethub/android/app/DatabaseImpl;Lcom/squareup/sqldelight/db/SqlDriver;)V", "selectUserRepositories", "", "Lcom/squareup/sqldelight/Query;", "getSelectUserRepositories$app_debug", "()Ljava/util/List;", "clearRepositories", "", "clearUserRepositories", "orgId", "", "(Ljava/lang/Long;)V", "replaceRepo", "repoId", "name", "", "ownerId", "private", "", "fork", "description", "forks", "", "watchers", "language", "hasIssues", "mirrorUrl", "permissions_admin", "permissions_pull", "permissions_push", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "Lcom/github/pockethub/android/SelectUserRepositories;", "(Ljava/lang/Long;)Lcom/squareup/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function17;", "Lkotlin/ParameterName;", "id", "login", "name_", "avatarurl", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function17;)Lcom/squareup/sqldelight/Query;", "SelectUserRepositories", "app_debug"})
final class RepositoriesQueriesImpl extends com.squareup.sqldelight.TransacterImpl implements com.github.pockethub.android.RepositoriesQueries {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.squareup.sqldelight.Query<?>> selectUserRepositories = null;
    private final com.github.pockethub.android.app.DatabaseImpl database = null;
    private final com.squareup.sqldelight.db.SqlDriver driver = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.squareup.sqldelight.Query<?>> getSelectUserRepositories$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <T extends java.lang.Object>com.squareup.sqldelight.Query<T> selectUserRepositories(@org.jetbrains.annotations.Nullable()
    java.lang.Long orgId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function17<? super java.lang.Long, ? super java.lang.String, ? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.Boolean, ? super java.lang.Boolean, ? super java.lang.String, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.String, ? super java.lang.Boolean, ? super java.lang.String, ? super java.lang.Boolean, ? super java.lang.Boolean, ? super java.lang.Boolean, ? extends T> mapper) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.sqldelight.Query<com.github.pockethub.android.SelectUserRepositories> selectUserRepositories(@org.jetbrains.annotations.Nullable()
    java.lang.Long orgId) {
        return null;
    }
    
    @java.lang.Override()
    public void clearUserRepositories(@org.jetbrains.annotations.Nullable()
    java.lang.Long orgId) {
    }
    
    @java.lang.Override()
    public void clearRepositories() {
    }
    
    @java.lang.Override()
    public void replaceRepo(@org.jetbrains.annotations.Nullable()
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
    java.lang.Boolean permissions_push) {
    }
    
    public RepositoriesQueriesImpl(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.app.DatabaseImpl database, @org.jetbrains.annotations.NotNull()
    com.squareup.sqldelight.db.SqlDriver driver) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\u0004\n\u0002\u0010\n\u00a8\u0006\u000e"}, d2 = {"Lcom/github/pockethub/android/app/RepositoriesQueriesImpl$SelectUserRepositories;", "T", "", "Lcom/squareup/sqldelight/Query;", "orgId", "", "mapper", "Lkotlin/Function1;", "Lcom/squareup/sqldelight/db/SqlCursor;", "(Lcom/github/pockethub/android/app/RepositoriesQueriesImpl;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "Ljava/lang/Long;", "execute", "toString", "", "app_debug"})
    final class SelectUserRepositories<T extends java.lang.Object> extends com.squareup.sqldelight.Query<T> {
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long orgId = null;
        
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
        
        public SelectUserRepositories(@org.jetbrains.annotations.Nullable()
        java.lang.Long orgId, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.squareup.sqldelight.db.SqlCursor, ? extends T> mapper) {
            super(null, null);
        }
    }
}