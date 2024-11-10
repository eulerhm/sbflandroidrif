package org.wordpress.android.ui.prefs.accountsettings.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.SiteStore;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class GetSitesUseCase_Factory implements Factory<GetSitesUseCase> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  public GetSitesUseCase_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
  }

  @Override
  public GetSitesUseCase get() {
    return newInstance(ioDispatcherProvider.get(), siteStoreProvider.get());
  }

  public static GetSitesUseCase_Factory create(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<SiteStore> siteStoreProvider) {
    return new GetSitesUseCase_Factory(ioDispatcherProvider, siteStoreProvider);
  }

  public static GetSitesUseCase newInstance(CoroutineDispatcher ioDispatcher, SiteStore siteStore) {
    return new GetSitesUseCase(ioDispatcher, siteStore);
  }
}
