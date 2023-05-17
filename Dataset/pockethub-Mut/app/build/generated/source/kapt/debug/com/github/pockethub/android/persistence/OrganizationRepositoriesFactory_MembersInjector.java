package com.github.pockethub.android.persistence;

import android.accounts.Account;
import android.content.Context;
import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
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
public final class OrganizationRepositoriesFactory_MembersInjector implements MembersInjector<OrganizationRepositoriesFactory> {
  private final Provider<Context> contextProvider;

  private final Provider<Account> accountProvider;

  public OrganizationRepositoriesFactory_MembersInjector(Provider<Context> contextProvider,
      Provider<Account> accountProvider) {
    this.contextProvider = contextProvider;
    this.accountProvider = accountProvider;
  }

  public static MembersInjector<OrganizationRepositoriesFactory> create(
      Provider<Context> contextProvider, Provider<Account> accountProvider) {
    return new OrganizationRepositoriesFactory_MembersInjector(contextProvider, accountProvider);}

  @Override
  public void injectMembers(OrganizationRepositoriesFactory instance) {
    injectContext(instance, contextProvider.get());
    injectAccountProvider(instance, accountProvider);
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.OrganizationRepositoriesFactory.context")
  public static void injectContext(OrganizationRepositoriesFactory instance, Context context) {
    instance.context = context;
  }

  @InjectedFieldSignature("com.github.pockethub.android.persistence.OrganizationRepositoriesFactory.accountProvider")
  public static void injectAccountProvider(OrganizationRepositoriesFactory instance,
      Provider<Account> accountProvider) {
    instance.accountProvider = accountProvider;
  }
}
