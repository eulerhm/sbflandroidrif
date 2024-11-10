package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;

@ScopeMetadata("javax.inject.Singleton")
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
public final class StatsSiteProvider_Factory implements Factory<StatsSiteProvider> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public StatsSiteProvider_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<Dispatcher> dispatcherProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public StatsSiteProvider get() {
    return newInstance(siteStoreProvider.get(), selectedSiteRepositoryProvider.get(), dispatcherProvider.get());
  }

  public static StatsSiteProvider_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<Dispatcher> dispatcherProvider) {
    return new StatsSiteProvider_Factory(siteStoreProvider, selectedSiteRepositoryProvider, dispatcherProvider);
  }

  public static StatsSiteProvider newInstance(SiteStore siteStore,
      SelectedSiteRepository selectedSiteRepository, Dispatcher dispatcher) {
    return new StatsSiteProvider(siteStore, selectedSiteRepository, dispatcher);
  }
}
