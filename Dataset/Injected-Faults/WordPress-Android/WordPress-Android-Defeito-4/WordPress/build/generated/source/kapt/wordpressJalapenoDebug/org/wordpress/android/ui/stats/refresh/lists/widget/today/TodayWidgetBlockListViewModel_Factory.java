package org.wordpress.android.ui.stats.refresh.lists.widget.today;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.stats.insights.TodayInsightsStore;
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
public final class TodayWidgetBlockListViewModel_Factory implements Factory<TodayWidgetBlockListViewModel> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<TodayInsightsStore> todayInsightsStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public TodayWidgetBlockListViewModel_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<TodayInsightsStore> todayInsightsStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<StatsUtils> statsUtilsProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.todayInsightsStoreProvider = todayInsightsStoreProvider;
    this.resourceProvider = resourceProvider;
    this.todayWidgetUpdaterProvider = todayWidgetUpdaterProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  @Override
  public TodayWidgetBlockListViewModel get() {
    return newInstance(siteStoreProvider.get(), todayInsightsStoreProvider.get(), resourceProvider.get(), todayWidgetUpdaterProvider.get(), appPrefsWrapperProvider.get(), statsUtilsProvider.get());
  }

  public static TodayWidgetBlockListViewModel_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<TodayInsightsStore> todayInsightsStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<StatsUtils> statsUtilsProvider) {
    return new TodayWidgetBlockListViewModel_Factory(siteStoreProvider, todayInsightsStoreProvider, resourceProvider, todayWidgetUpdaterProvider, appPrefsWrapperProvider, statsUtilsProvider);
  }

  public static TodayWidgetBlockListViewModel newInstance(SiteStore siteStore,
      TodayInsightsStore todayInsightsStore, ResourceProvider resourceProvider,
      TodayWidgetUpdater todayWidgetUpdater, AppPrefsWrapper appPrefsWrapper,
      StatsUtils statsUtils) {
    return new TodayWidgetBlockListViewModel(siteStore, todayInsightsStore, resourceProvider, todayWidgetUpdater, appPrefsWrapper, statsUtils);
  }
}
