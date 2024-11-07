package com.github.pockethub.android.persistence;

import android.content.Context;
import dagger.internal.Factory;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AccountDataManager_Factory implements Factory<AccountDataManager> {
  private final Provider<Context> contextProvider;

  private final Provider<DatabaseCache> dbCacheProvider;

  private final Provider<OrganizationRepositoriesFactory> allReposProvider;

  private final Provider<Organizations> userAndOrgsResourceProvider;

  private final Provider<File> rootProvider;

  public AccountDataManager_Factory(Provider<Context> contextProvider,
      Provider<DatabaseCache> dbCacheProvider,
      Provider<OrganizationRepositoriesFactory> allReposProvider,
      Provider<Organizations> userAndOrgsResourceProvider, Provider<File> rootProvider) {
    this.contextProvider = contextProvider;
    this.dbCacheProvider = dbCacheProvider;
    this.allReposProvider = allReposProvider;
    this.userAndOrgsResourceProvider = userAndOrgsResourceProvider;
    this.rootProvider = rootProvider;
  }

  @Override
  public AccountDataManager get() {
    AccountDataManager instance = newInstance();
    AccountDataManager_MembersInjector.injectContext(instance, contextProvider.get());
    AccountDataManager_MembersInjector.injectDbCache(instance, dbCacheProvider.get());
    AccountDataManager_MembersInjector.injectAllRepos(instance, allReposProvider.get());
    AccountDataManager_MembersInjector.injectUserAndOrgsResource(instance, userAndOrgsResourceProvider.get());
    AccountDataManager_MembersInjector.injectRoot(instance, rootProvider.get());
    return instance;
  }

  public static AccountDataManager_Factory create(Provider<Context> contextProvider,
      Provider<DatabaseCache> dbCacheProvider,
      Provider<OrganizationRepositoriesFactory> allReposProvider,
      Provider<Organizations> userAndOrgsResourceProvider, Provider<File> rootProvider) {
    return new AccountDataManager_Factory(contextProvider, dbCacheProvider, allReposProvider, userAndOrgsResourceProvider, rootProvider);
  }

  public static AccountDataManager newInstance() {
    return new AccountDataManager();
  }
}
