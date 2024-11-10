package org.wordpress.android.ui.sitecreation.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
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
public final class FetchDomainsUseCase_Factory implements Factory<FetchDomainsUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public FetchDomainsUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public FetchDomainsUseCase get() {
    return newInstance(dispatcherProvider.get(), siteStoreProvider.get());
  }

  public static FetchDomainsUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new FetchDomainsUseCase_Factory(dispatcherProvider, siteStoreProvider);
  }

  public static FetchDomainsUseCase newInstance(Dispatcher dispatcher, SiteStore siteStore) {
    return new FetchDomainsUseCase(dispatcher, siteStore);
  }
}
