package org.wordpress.android.ui;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.JetpackStore;
import org.wordpress.android.fluxc.store.SiteStore;

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
public final class JetpackRemoteInstallViewModel_Factory implements Factory<JetpackRemoteInstallViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<JetpackStore> jetpackStoreProvider;

  public JetpackRemoteInstallViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<JetpackStore> jetpackStoreProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.jetpackStoreProvider = jetpackStoreProvider;
  }

  @Override
  public JetpackRemoteInstallViewModel get() {
    return newInstance(dispatcherProvider.get(), accountStoreProvider.get(), siteStoreProvider.get(), jetpackStoreProvider.get());
  }

  public static JetpackRemoteInstallViewModel_Factory create(
      Provider<Dispatcher> dispatcherProvider, Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider, Provider<JetpackStore> jetpackStoreProvider) {
    return new JetpackRemoteInstallViewModel_Factory(dispatcherProvider, accountStoreProvider, siteStoreProvider, jetpackStoreProvider);
  }

  public static JetpackRemoteInstallViewModel newInstance(Dispatcher dispatcher,
      AccountStore accountStore, SiteStore siteStore, JetpackStore jetpackStore) {
    return new JetpackRemoteInstallViewModel(dispatcher, accountStore, siteStore, jetpackStore);
  }
}
