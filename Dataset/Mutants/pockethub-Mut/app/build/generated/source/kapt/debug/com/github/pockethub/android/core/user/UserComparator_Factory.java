package com.github.pockethub.android.core.user;

import android.accounts.Account;
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
public final class UserComparator_Factory implements Factory<UserComparator> {
  private final Provider<Account> accountProvider;

  public UserComparator_Factory(Provider<Account> accountProvider) {
    this.accountProvider = accountProvider;
  }

  @Override
  public UserComparator get() {
    return newInstance(accountProvider.get());
  }

  public static UserComparator_Factory create(Provider<Account> accountProvider) {
    return new UserComparator_Factory(accountProvider);
  }

  public static UserComparator newInstance(Account account) {
    return new UserComparator(account);
  }
}
