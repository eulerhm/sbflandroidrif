package org.wordpress.android.ui.prefs.homepage;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteOptionsStore;
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
public final class HomepageSettingsViewModel_Factory implements Factory<HomepageSettingsViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<HomepageSettingsDataLoader> homepageSettingsDataLoaderProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<SiteOptionsStore> siteOptionsStoreProvider;

  public HomepageSettingsViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<HomepageSettingsDataLoader> homepageSettingsDataLoaderProvider,
      Provider<SiteStore> siteStoreProvider, Provider<SiteOptionsStore> siteOptionsStoreProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.homepageSettingsDataLoaderProvider = homepageSettingsDataLoaderProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.siteOptionsStoreProvider = siteOptionsStoreProvider;
  }

  @Override
  public HomepageSettingsViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), dispatcherProvider.get(), homepageSettingsDataLoaderProvider.get(), siteStoreProvider.get(), siteOptionsStoreProvider.get());
  }

  public static HomepageSettingsViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<HomepageSettingsDataLoader> homepageSettingsDataLoaderProvider,
      Provider<SiteStore> siteStoreProvider, Provider<SiteOptionsStore> siteOptionsStoreProvider) {
    return new HomepageSettingsViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, dispatcherProvider, homepageSettingsDataLoaderProvider, siteStoreProvider, siteOptionsStoreProvider);
  }

  public static HomepageSettingsViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, Dispatcher dispatcher,
      HomepageSettingsDataLoader homepageSettingsDataLoader, SiteStore siteStore,
      SiteOptionsStore siteOptionsStore) {
    return new HomepageSettingsViewModel(mainDispatcher, bgDispatcher, dispatcher, homepageSettingsDataLoader, siteStore, siteOptionsStore);
  }
}
