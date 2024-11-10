package org.wordpress.android.ui.stats.refresh.lists.widget.alltime;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.stats.insights.AllTimeInsightsStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class AllTimeWidgetBlockListViewModel_Factory implements Factory<AllTimeWidgetBlockListViewModel> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AllTimeInsightsStore> allTimeStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public AllTimeWidgetBlockListViewModel_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<AllTimeInsightsStore> allTimeStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<StatsUtils> statsUtilsProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.allTimeStoreProvider = allTimeStoreProvider;
    this.resourceProvider = resourceProvider;
    this.allTimeWidgetUpdaterProvider = allTimeWidgetUpdaterProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  @Override
  public AllTimeWidgetBlockListViewModel get() {
    return newInstance(siteStoreProvider.get(), allTimeStoreProvider.get(), resourceProvider.get(), allTimeWidgetUpdaterProvider.get(), appPrefsWrapperProvider.get(), statsUtilsProvider.get());
  }

  public static AllTimeWidgetBlockListViewModel_Factory create(
      Provider<SiteStore> siteStoreProvider, Provider<AllTimeInsightsStore> allTimeStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<StatsUtils> statsUtilsProvider) {
    return new AllTimeWidgetBlockListViewModel_Factory(siteStoreProvider, allTimeStoreProvider, resourceProvider, allTimeWidgetUpdaterProvider, appPrefsWrapperProvider, statsUtilsProvider);
  }

  public static AllTimeWidgetBlockListViewModel newInstance(SiteStore siteStore,
      AllTimeInsightsStore allTimeStore, ResourceProvider resourceProvider,
      AllTimeWidgetUpdater allTimeWidgetUpdater, AppPrefsWrapper appPrefsWrapper,
      StatsUtils statsUtils) {
    return new AllTimeWidgetBlockListViewModel(siteStore, allTimeStore, resourceProvider, allTimeWidgetUpdater, appPrefsWrapper, statsUtils);
  }
}
