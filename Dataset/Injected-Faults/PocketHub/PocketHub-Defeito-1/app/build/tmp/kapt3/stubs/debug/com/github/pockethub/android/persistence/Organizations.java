package com.github.pockethub.android.persistence;

import android.content.Context;
import com.github.pockethub.android.Database;
import com.meisolsson.githubsdk.core.ServiceGenerator;
import com.meisolsson.githubsdk.model.User;
import com.meisolsson.githubsdk.service.organizations.OrganizationService;
import com.meisolsson.githubsdk.service.users.UserService;
import java.io.IOException;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Cache of organization under an account
 */
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0016J\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/github/pockethub/android/persistence/Organizations;", "Lcom/github/pockethub/android/persistence/PersistableResource;", "Lcom/meisolsson/githubsdk/model/User;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "allOrgs", "", "getAllOrgs", "()Ljava/util/List;", "loadItems", "", "database", "Lcom/github/pockethub/android/Database;", "request", "store", "", "db", "orgs", "app_debug"})
public final class Organizations implements com.github.pockethub.android.persistence.PersistableResource<com.meisolsson.githubsdk.model.User> {
    private final android.content.Context context = null;
    
    private final java.util.List<com.meisolsson.githubsdk.model.User> getAllOrgs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<com.meisolsson.githubsdk.model.User> loadItems(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Database database) {
        return null;
    }
    
    @java.lang.Override()
    public void store(@org.jetbrains.annotations.NotNull()
    com.github.pockethub.android.Database db, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.meisolsson.githubsdk.model.User> orgs) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<com.meisolsson.githubsdk.model.User> request() {
        return null;
    }
    
    @javax.inject.Inject()
    public Organizations(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}