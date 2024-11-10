package org.wordpress.android.ui.sitecreation.services;

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
public final class FetchWpComSiteUseCase_Factory implements Factory<FetchWpComSiteUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public FetchWpComSiteUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public FetchWpComSiteUseCase get() {
    return newInstance(dispatcherProvider.get(), siteStoreProvider.get());
  }

  public static FetchWpComSiteUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new FetchWpComSiteUseCase_Factory(dispatcherProvider, siteStoreProvider);
  }

  public static FetchWpComSiteUseCase newInstance(Dispatcher dispatcher, SiteStore siteStore) {
    return new FetchWpComSiteUseCase(dispatcher, siteStore);
  }
}
