package org.wordpress.android.ui.mysite;

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
public final class CurrentAvatarSource_Factory implements Factory<CurrentAvatarSource> {
  private final Provider<AccountStore> accountStoreProvider;

  public CurrentAvatarSource_Factory(Provider<AccountStore> accountStoreProvider) {
    this.accountStoreProvider = accountStoreProvider;
  }

  @Override
  public CurrentAvatarSource get() {
    return newInstance(accountStoreProvider.get());
  }

  public static CurrentAvatarSource_Factory create(Provider<AccountStore> accountStoreProvider) {
    return new CurrentAvatarSource_Factory(accountStoreProvider);
  }

  public static CurrentAvatarSource newInstance(AccountStore accountStore) {
    return new CurrentAvatarSource(accountStore);
  }
}
