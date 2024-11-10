package org.wordpress.android.ui.reader.viewmodels;

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
public final class SubfilterPageViewModel_Factory implements Factory<SubfilterPageViewModel> {
  private final Provider<AccountStore> accountStoreProvider;

  public SubfilterPageViewModel_Factory(Provider<AccountStore> accountStoreProvider) {
    this.accountStoreProvider = accountStoreProvider;
  }

  @Override
  public SubfilterPageViewModel get() {
    return newInstance(accountStoreProvider.get());
  }

  public static SubfilterPageViewModel_Factory create(Provider<AccountStore> accountStoreProvider) {
    return new SubfilterPageViewModel_Factory(accountStoreProvider);
  }

  public static SubfilterPageViewModel newInstance(AccountStore accountStore) {
    return new SubfilterPageViewModel(accountStore);
  }
}
