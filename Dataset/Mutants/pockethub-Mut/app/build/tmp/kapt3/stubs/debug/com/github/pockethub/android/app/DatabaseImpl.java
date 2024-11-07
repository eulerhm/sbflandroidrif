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

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0016B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0017"}, d2 = {"Lcom/github/pockethub/android/app/DatabaseImpl;", "Lcom/squareup/sqldelight/TransacterImpl;", "Lcom/github/pockethub/android/Database;", "driver", "Lcom/squareup/sqldelight/db/SqlDriver;", "milestonesAdapter", "Lcom/github/pockethub/android/Milestones$Adapter;", "(Lcom/squareup/sqldelight/db/SqlDriver;Lcom/github/pockethub/android/Milestones$Adapter;)V", "issue_filterQueries", "Lcom/github/pockethub/android/app/Issue_filterQueriesImpl;", "getIssue_filterQueries", "()Lcom/github/pockethub/android/app/Issue_filterQueriesImpl;", "getMilestonesAdapter$app_debug", "()Lcom/github/pockethub/android/Milestones$Adapter;", "organizationsQueries", "Lcom/github/pockethub/android/app/OrganizationsQueriesImpl;", "getOrganizationsQueries", "()Lcom/github/pockethub/android/app/OrganizationsQueriesImpl;", "repositoriesQueries", "Lcom/github/pockethub/android/app/RepositoriesQueriesImpl;", "getRepositoriesQueries", "()Lcom/github/pockethub/android/app/RepositoriesQueriesImpl;", "Schema", "app_debug"})
final class DatabaseImpl extends com.squareup.sqldelight.TransacterImpl implements com.github.pockethub.android.Database {
    @org.jetbrains.annotations.NotNull()
    private final com.github.pockethub.android.app.Issue_filterQueriesImpl issue_filterQueries = null;
    @org.jetbrains.annotations.NotNull()
    private final com.github.pockethub.android.app.OrganizationsQueriesImpl organizationsQueries = null;
    @org.jetbrains.annotations.NotNull()
    private final com.github.pockethub.android.app.RepositoriesQueriesImpl repositoriesQueries = null;
    @org.jetbrains.annotations.NotNull()
    private final com.github.pockethub.android.Milestones.Adapter milestonesAdapter = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.pockethub.android.app.Issue_filterQueriesImpl getIssue_filterQueries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.pockethub.android.app.OrganizationsQueriesImpl getOrganizationsQueries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.pockethub.android.app.RepositoriesQueriesImpl getRepositoriesQueries() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.pockethub.android.Milestones.Adapter getMilestonesAdapter$app_debug() {
        return null;
    }
    
    public DatabaseImpl(@org.jetbrains.annotations.NotNull()
    com.squareup.sqldelight.db.SqlDriver driver, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Milestones.Adapter milestonesAdapter) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/github/pockethub/android/app/DatabaseImpl$Schema;", "Lcom/squareup/sqldelight/db/SqlDriver$Schema;", "()V", "version", "", "getVersion", "()I", "create", "", "driver", "Lcom/squareup/sqldelight/db/SqlDriver;", "migrate", "oldVersion", "newVersion", "app_debug"})
    public static final class Schema implements com.squareup.sqldelight.db.SqlDriver.Schema {
        public static final com.github.pockethub.android.app.DatabaseImpl.Schema INSTANCE = null;
        
        @java.lang.Override()
        public int getVersion() {
            return 0;
        }
        
        @java.lang.Override()
        public void create(@org.jetbrains.annotations.NotNull()
        com.squareup.sqldelight.db.SqlDriver driver) {
        }
        
        @java.lang.Override()
        public void migrate(@org.jetbrains.annotations.NotNull()
        com.squareup.sqldelight.db.SqlDriver driver, int oldVersion, int newVersion) {
        }
        
        private Schema() {
            super();
        }
    }
}