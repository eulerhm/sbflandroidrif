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

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0006\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000\"\u001e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00028@X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u000b"}, d2 = {"schema", "Lcom/squareup/sqldelight/db/SqlDriver$Schema;", "Lkotlin/reflect/KClass;", "Lcom/github/pockethub/android/Database;", "getSchema", "(Lkotlin/reflect/KClass;)Lcom/squareup/sqldelight/db/SqlDriver$Schema;", "newInstance", "driver", "Lcom/squareup/sqldelight/db/SqlDriver;", "milestonesAdapter", "Lcom/github/pockethub/android/Milestones$Adapter;", "app_debug"})
public final class DatabaseImplKt {
    
    @org.jetbrains.annotations.NotNull()
    public static final com.squareup.sqldelight.db.SqlDriver.Schema getSchema(@org.jetbrains.annotations.NotNull()
    kotlin.reflect.KClass<com.github.pockethub.android.Database> $this$schema) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final com.github.pockethub.android.Database newInstance(@org.jetbrains.annotations.NotNull()
    kotlin.reflect.KClass<com.github.pockethub.android.Database> $this$newInstance, @org.jetbrains.annotations.NotNull()
    com.squareup.sqldelight.db.SqlDriver driver, @org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Milestones.Adapter milestonesAdapter) {
        return null;
    }
}