package org.wordpress.android.ui.prefs.accountsettings.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class GetAccountUseCase_Factory implements Factory<GetAccountUseCase> {
  private final Provider<AccountStore> accountStoreProvider;

  public GetAccountUseCase_Factory(Provider<AccountStore> accountStoreProvider) {
    this.accountStoreProvider = accountStoreProvider;
  }

  @Override
  public GetAccountUseCase get() {
    return newInstance(accountStoreProvider.get());
  }

  public static GetAccountUseCase_Factory create(Provider<AccountStore> accountStoreProvider) {
    return new GetAccountUseCase_Factory(accountStoreProvider);
  }

  public static GetAccountUseCase newInstance(AccountStore accountStore) {
    return new GetAccountUseCase(accountStore);
  }
}
