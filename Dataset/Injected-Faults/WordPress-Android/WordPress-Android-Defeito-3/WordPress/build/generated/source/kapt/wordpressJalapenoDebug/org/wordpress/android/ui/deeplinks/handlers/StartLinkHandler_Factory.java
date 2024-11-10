package org.wordpress.android.ui.deeplinks.handlers;

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
public final class StartLinkHandler_Factory implements Factory<StartLinkHandler> {
  private final Provider<AccountStore> accountStoreProvider;

  public StartLinkHandler_Factory(Provider<AccountStore> accountStoreProvider) {
    this.accountStoreProvider = accountStoreProvider;
  }

  @Override
  public StartLinkHandler get() {
    return newInstance(accountStoreProvider.get());
  }

  public static StartLinkHandler_Factory create(Provider<AccountStore> accountStoreProvider) {
    return new StartLinkHandler_Factory(accountStoreProvider);
  }

  public static StartLinkHandler newInstance(AccountStore accountStore) {
    return new StartLinkHandler(accountStore);
  }
}
