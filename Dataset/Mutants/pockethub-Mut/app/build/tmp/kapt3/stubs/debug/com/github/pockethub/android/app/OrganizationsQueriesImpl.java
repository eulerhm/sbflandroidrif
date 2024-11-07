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

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001#B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0017\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016\u00a2\u0006\u0002\u0010\u0015J5\u0010\u0016\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u0018H\u0016\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u001c0\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0088\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u001d0\n\"\b\b\u0000\u0010\u001d*\u00020\u001e2\u0006\u0010\u0013\u001a\u00020\u00142f\u0010\u001f\u001ab\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0018\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u0018\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0018\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u0002H\u001d0 H\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\"0\nH\u0016J\u0080\u0001\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u001d0\n\"\b\b\u0000\u0010\u001d*\u00020\u001e2f\u0010\u001f\u001ab\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0018\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u0018\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0018\u00a2\u0006\f\b!\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u0002H\u001d0 H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f\u00a8\u0006$"}, d2 = {"Lcom/github/pockethub/android/app/OrganizationsQueriesImpl;", "Lcom/squareup/sqldelight/TransacterImpl;", "Lcom/github/pockethub/android/OrganizationsQueries;", "database", "Lcom/github/pockethub/android/app/DatabaseImpl;", "driver", "Lcom/squareup/sqldelight/db/SqlDriver;", "(Lcom/github/pockethub/android/app/DatabaseImpl;Lcom/squareup/sqldelight/db/SqlDriver;)V", "selectUser", "", "Lcom/squareup/sqldelight/Query;", "getSelectUser$app_debug", "()Ljava/util/List;", "selectUserAndOrgs", "getSelectUserAndOrgs$app_debug", "clearOrgs", "", "clearUsers", "insertOrg", "id", "", "(Ljava/lang/Long;)V", "replaceUser", "login", "", "name", "avatarurl", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/github/pockethub/android/Users;", "T", "", "mapper", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "Lcom/github/pockethub/android/SelectUserAndOrgs;", "SelectUser", "app_debug"})
final class OrganizationsQueriesImpl extends com.squareup.sqldelight.TransacterImpl implements com.github.pockethub.android.OrganizationsQueries {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.squareup.sqldelight.Query<?>> selectUserAndOrgs = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.squareup.sqldelight.Query<?>> selectUser = null;
    private final com.github.pockethub.android.app.DatabaseImpl database = null;
    private final com.squareup.sqldelight.db.SqlDriver driver = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.squareup.sqldelight.Query<?>> getSelectUserAndOrgs$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.squareup.sqldelight.Query<?>> getSelectUser$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <T extends java.lang.Object>com.squareup.sqldelight.Query<T> selectUserAndOrgs(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? extends T> mapper) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.sqldelight.Query<com.github.pockethub.android.SelectUserAndOrgs> selectUserAndOrgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public <T extends java.lang.Object>com.squareup.sqldelight.Query<T> selectUser(long id, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.Long, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? extends T> mapper) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.squareup.sqldelight.Query<com.github.pockethub.android.Users> selectUser(long id) {
        return null;
    }
    
    @java.lang.Override()
    public void clearOrgs() {
    }
    
    @java.lang.Override()
    public void clearUsers() {
    }
    
    @java.lang.Override()
    public void replaceUser(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.Nullable()
    java.lang.String login, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String avatarurl) {
    }
    
    @java.lang.Override()
    public void insertOrg(@org.jetbrains.annotations.Nullable()
    java.lang.Long id) {
    }
    
    public OrganizationsQueriesImpl(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.app.DatabaseImpl database, @org.jetbrains.annotations.NotNull()
    com.squareup.sqldelight.db.SqlDriver driver) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/github/pockethub/android/app/OrganizationsQueriesImpl$SelectUser;", "T", "", "Lcom/squareup/sqldelight/Query;", "id", "", "mapper", "Lkotlin/Function1;", "Lcom/squareup/sqldelight/db/SqlCursor;", "(Lcom/github/pockethub/android/app/OrganizationsQueriesImpl;JLkotlin/jvm/functions/Function1;)V", "execute", "toString", "", "app_debug"})
    final class SelectUser<T extends java.lang.Object> extends com.squareup.sqldelight.Query<T> {
        public final long id = 0L;
        
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
        
        public SelectUser(long id, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.squareup.sqldelight.db.SqlCursor, ? extends T> mapper) {
            super(null, null);
        }
    }
}