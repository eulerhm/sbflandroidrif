package com.github.pockethub.android.persistence;

import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Named;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AccountDataManager_MembersInjector implements MembersInjector<AccountDataManager> {
  private final Provider<Context> contextProvider;

  private final Provider<DatabaseCache> dbCacheProvider;

  private final Provider<OrganizationRepositoriesFactory> allReposProvider;

  private final Provider<Organizations> userAndOrgsResourceProvider;

  private final Provider<File> rootProvider;

  public AccountDataManager_MembersInjector(Provider<Context> contextProvider,
      Provider<DatabaseCache> dbCacheProvider,
      Provider<OrganizationRepositoriesFactory> allReposProvider,
      Provider<Organizations> userAndOrgsResourceProvider, Provider<File> rootProvider) {
    this.contextProvider = contextProvider;
    this.dbCacheProvider = dbCacheProvider;
    this.allReposProvider = allReposProvider;
    this.userAndOrgsResourceProvider = userAndOrgsResourceProvider;
    this.rootProvider = rootProvider;
  }

  public static MembersInjector<AccountDataManager> create(Provider<Context> contextProvider,
      Provider<DatabaseCache> dbCacheProvider,
      Provider<OrganizationRepositoriesFactory> allReposProvider,
      Provider<Organizations> userAndOrgsResourceProvider, Provider<File> rootProvider) {
    return new AccountDataManager_MembersInjector(contextProvider, dbCacheProvider, allReposProvider, userAndOrgsResourceProvider, rootProvider);}

  @Override
  public void injectMembers(AccountDataManager instance) {
    injectContext(instance, contextProvider.get());
    injectDbCache(instance, dbCacheProvider.get());
    injectAllRepos(instance, allReposProvider.get());
    injectUserAndOrgsResource(instance, userAndOrgsResourceProvider.get());
    injectRoot(instance, rootProvider.get());
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.AccountDataManager.context")
  public static void injectContext(AccountDataManager instance, Context context) {
    instance.context = context;
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.AccountDataManager.dbCache")
  public static void injectDbCache(AccountDataManager instance, DatabaseCache dbCache) {
    instance.dbCache = dbCache;
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.AccountDataManager.allRepos")
  public static void injectAllRepos(AccountDataManager instance,
      OrganizationRepositoriesFactory allRepos) {
    instance.allRepos = allRepos;
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.AccountDataManager.userAndOrgsResource")
  public static void injectUserAndOrgsResource(AccountDataManager instance,
      Organizations userAndOrgsResource) {
    instance.userAndOrgsResource = userAndOrgsResource;
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.AccountDataManager.root")
  @Named("cacheDir")
  public static void injectRoot(AccountDataManager instance, File root) {
    instance.root = root;
  }
}
