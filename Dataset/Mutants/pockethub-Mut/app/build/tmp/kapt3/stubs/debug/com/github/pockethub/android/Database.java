package com.github.pockethub.android;

import com.squareup.sqldelight.Transacter;
import com.squareup.sqldelight.db.SqlDriver;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eR\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2 = {"Lcom/github/pockethub/android/Database;", "Lcom/squareup/sqldelight/Transacter;", "issue_filterQueries", "Lcom/github/pockethub/android/Issue_filterQueries;", "getIssue_filterQueries", "()Lcom/github/pockethub/android/Issue_filterQueries;", "organizationsQueries", "Lcom/github/pockethub/android/OrganizationsQueries;", "getOrganizationsQueries", "()Lcom/github/pockethub/android/OrganizationsQueries;", "repositoriesQueries", "Lcom/github/pockethub/android/RepositoriesQueries;", "getRepositoriesQueries", "()Lcom/github/pockethub/android/RepositoriesQueries;", "Companion", "app_debug"})
public abstract interface Database extends com.squareup.sqldelight.Transacter {
    public static final com.github.pockethub.android.Database.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.github.pockethub.android.Issue_filterQueries getIssue_filterQueries();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.github.pockethub.android.OrganizationsQueries getOrganizationsQueries();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.github.pockethub.android.RepositoriesQueries getRepositoriesQueries();
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\r"}, d2 = {"Lcom/github/pockethub/android/Database$Companion;", "", "()V", "Schema", "Lcom/squareup/sqldelight/db/SqlDriver$Schema;", "getSchema", "()Lcom/squareup/sqldelight/db/SqlDriver$Schema;", "invoke", "Lcom/github/pockethub/android/Database;", "driver", "Lcom/squareup/sqldelight/db/SqlDriver;", "milestonesAdapter", "Lcom/github/pockethub/android/Milestones$Adapter;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.squareup.sqldelight.db.SqlDriver.Schema getSchema() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.github.pockethub.android.Database invoke(@org.jetbrains.annotations.NotNull()
        com.squareup.sqldelight.db.SqlDriver driver, @org.jetbrains.annotations.NotNull()
        com.github.pockethub.android.Milestones.Adapter milestonesAdapter) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}