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
public final class TodayWidgetListViewModel_Factory implements Factory<TodayWidgetListViewModel> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<TodayInsightsStore> todayInsightsStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  public TodayWidgetListViewModel_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<TodayInsightsStore> todayInsightsStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<StatsUtils> statsUtilsProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.todayInsightsStoreProvider = todayInsightsStoreProvider;
    this.resourceProvider = resourceProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
  }

  @Override
  public TodayWidgetListViewModel get() {
    return newInstance(siteStoreProvider.get(), todayInsightsStoreProvider.get(), resourceProvider.get(), appPrefsWrapperProvider.get(), statsUtilsProvider.get());
  }

  public static TodayWidgetListViewModel_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<TodayInsightsStore> todayInsightsStoreProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<StatsUtils> statsUtilsProvider) {
    return new TodayWidgetListViewModel_Factory(siteStoreProvider, todayInsightsStoreProvider, resourceProvider, appPrefsWrapperProvider, statsUtilsProvider);
  }

  public static TodayWidgetListViewModel newInstance(SiteStore siteStore,
      TodayInsightsStore todayInsightsStore, ResourceProvider resourceProvider,
      AppPrefsWrapper appPrefsWrapper, StatsUtils statsUtils) {
    return new TodayWidgetListViewModel(siteStore, todayInsightsStore, resourceProvider, appPrefsWrapper, statsUtils);
  }
}
