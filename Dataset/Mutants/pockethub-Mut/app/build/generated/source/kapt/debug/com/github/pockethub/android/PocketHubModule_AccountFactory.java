package com.github.pockethub.android;

import android.accounts.Account;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class PocketHubModule_AccountFactory implements Factory<Account> {
  private final PocketHubModule module;

  private final Provider<Context> contextProvider;

  public PocketHubModule_AccountFactory(PocketHubModule module, Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public Account get() {
    return account(module, contextProvider.get());
  }

  public static PocketHubModule_AccountFactory create(PocketHubModule module,
      Provider<Context> contextProvider) {
    return new PocketHubModule_AccountFactory(module, contextProvider);
  }

  public static Account account(PocketHubModule instance, Context context) {
    return Preconditions.checkNotNull(instance.account(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}
