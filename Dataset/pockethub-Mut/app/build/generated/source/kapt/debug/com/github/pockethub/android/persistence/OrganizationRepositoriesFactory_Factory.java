package com.github.pockethub.android.persistence;

import android.accounts.Account;
import android.content.Context;
import dagger.internal.Factory;
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
public final class OrganizationRepositoriesFactory_Factory implements Factory<OrganizationRepositoriesFactory> {
  private final Provider<Context> contextProvider;

  private final Provider<Account> accountProvider;

  public OrganizationRepositoriesFactory_Factory(Provider<Context> contextProvider,
      Provider<Account> accountProvider) {
    this.contextProvider = contextProvider;
    this.accountProvider = accountProvider;
  }

  @Override
  public OrganizationRepositoriesFactory get() {
    OrganizationRepositoriesFactory instance = newInstance();
    OrganizationRepositoriesFactory_MembersInjector.injectContext(instance, contextProvider.get());
    OrganizationRepositoriesFactory_MembersInjector.injectAccountProvider(instance, accountProvider);
    return instance;
  }

  public static OrganizationRepositoriesFactory_Factory create(Provider<Context> contextProvider,
      Provider<Account> accountProvider) {
    return new OrganizationRepositoriesFactory_Factory(contextProvider, accountProvider);
  }

  public static OrganizationRepositoriesFactory newInstance() {
    return new OrganizationRepositoriesFactory();
  }
}
