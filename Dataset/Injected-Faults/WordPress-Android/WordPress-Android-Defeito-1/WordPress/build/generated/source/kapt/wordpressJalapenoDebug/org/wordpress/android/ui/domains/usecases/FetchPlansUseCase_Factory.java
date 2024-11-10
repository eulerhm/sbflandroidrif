package org.wordpress.android.ui.domains.usecases;

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
public final class FetchPlansUseCase_Factory implements Factory<FetchPlansUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public FetchPlansUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public FetchPlansUseCase get() {
    return newInstance(dispatcherProvider.get(), siteStoreProvider.get());
  }

  public static FetchPlansUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new FetchPlansUseCase_Factory(dispatcherProvider, siteStoreProvider);
  }

  public static FetchPlansUseCase newInstance(Dispatcher dispatcher, SiteStore siteStore) {
    return new FetchPlansUseCase(dispatcher, siteStore);
  }
}
