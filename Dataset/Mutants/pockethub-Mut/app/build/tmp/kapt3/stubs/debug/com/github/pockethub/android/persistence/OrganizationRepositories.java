package com.github.pockethub.android.persistence;

import android.accounts.Account;
import android.content.Context;
import com.github.pockethub.android.Database;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.Page;
import com.meisolsson.githubsdk.model.Permissions;
import com.meisolsson.githubsdk.model.Repository;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.service.activity.WatchingService;
import com.meisolsson.githubsdk.service.repositories.RepositoryService;
import io.reactivex.Single;
import retrofit2.Response;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.inject.Provider;

/**
 * Create repositories cache for a given organization
 *
 * @param org
 * @param context
 * @param accountProvider
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nJ4\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2$\u0010\u0010\u001a \u0012\u0004\u0012\u00020\u0012\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00150\u00140\u00130\u0011H\u0002J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fH\u0016J\u001e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/github/pockethub/android/persistence/OrganizationRepositories;", "Lcom/github/pockethub/android/persistence/PersistableResource;", "Lcom/meisolsson/githubsdk/model/Repository;", "org", "Lcom/meisolsson/githubsdk/model/User;", "context", "Landroid/content/Context;", "accountProvider", "Ljavax/inject/Provider;", "Landroid/accounts/Account;", "(Lcom/meisolsson/githubsdk/model/User;Landroid/content/Context;Ljavax/inject/Provider;)V", "isAuthenticatedUser", "", "()Z", "getAllItems", "", "request", "Lkotlin/Function1;", "", "Lio/reactivex/Single;", "Lretrofit2/Response;", "Lcom/meisolsson/githubsdk/model/Page;", "loadItems", "database", "Lcom/github/pockethub/android/Database;", "store", "", "repos", "toString", "", "app_debug"})
public final class OrganizationRepositories implements com.github.pockethub.android.persistence.PersistableResource<com.meisolsson.githubsdk.model.Repository> {
    private final com.meisolsson.githubsdk.model.User org = null;
    private final android.content.Context context = null;
    private final javax.inject.Provider<android.accounts.Account> accountProvider = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<com.meisolsson.githubsdk.model.Repository> loadItems(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Database database) {
        return null;
    }
    
    private final boolean isAuthenticatedUser() {
        return false;
    }
    
    @java.lang.Override()
    public void store(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Database database, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.meisolsson.githubsdk.model.Repository> repos) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<com.meisolsson.githubsdk.model.Repository> request() {
        return null;
    }
    
    private final java.util.List<com.meisolsson.githubsdk.model.Repository> getAllItems(kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends io.reactivex.Single<retrofit2.Response<com.meisolsson.githubsdk.model.Page<com.meisolsson.githubsdk.model.Repository>>>> request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public OrganizationRepositories(@org.jetbrains.annotations.NotNull()
    com.meisolsson.githubsdk.model.User org, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    javax.inject.Provider<android.accounts.Account> accountProvider) {
        super();
    }
}