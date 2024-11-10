package org.wordpress.android.ui.stats.refresh.lists.widget.minified;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.stats.insights.TodayInsightsStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.lists.widget.utils.WidgetUtils;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class MinifiedWidgetUpdater_Factory implements Factory<MinifiedWidgetUpdater> {
  private final Provider<CoroutineDispatcher> defaultDispatcherProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<TodayInsightsStore> todayInsightsStoreProvider;

  private final Provider<WidgetUtils> widgetUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public MinifiedWidgetUpdater_Factory(Provider<CoroutineDispatcher> defaultDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<TodayInsightsStore> todayInsightsStoreProvider,
      Provider<WidgetUtils> widgetUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.defaultDispatcherProvider = defaultDispatcherProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.resourceProvider = resourceProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.todayInsightsStoreProvider = todayInsightsStoreProvider;
    this.widgetUtilsProvider = widgetUtilsProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public MinifiedWidgetUpdater get() {
    return newInstance(defaultDispatcherProvider.get(), appPrefsWrapperProvider.get(), siteStoreProvider.get(), accountStoreProvider.get(), networkUtilsWrapperProvider.get(), resourceProvider.get(), statsUtilsProvider.get(), todayInsightsStoreProvider.get(), widgetUtilsProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static MinifiedWidgetUpdater_Factory create(
      Provider<CoroutineDispatcher> defaultDispatcherProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<TodayInsightsStore> todayInsightsStoreProvider,
      Provider<WidgetUtils> widgetUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new MinifiedWidgetUpdater_Factory(defaultDispatcherProvider, appPrefsWrapperProvider, siteStoreProvider, accountStoreProvider, networkUtilsWrapperProvider, resourceProvider, statsUtilsProvider, todayInsightsStoreProvider, widgetUtilsProvider, analyticsTrackerWrapperProvider);
  }

  public static MinifiedWidgetUpdater newInstance(CoroutineDispatcher defaultDispatcher,
      AppPrefsWrapper appPrefsWrapper, SiteStore siteStore, AccountStore accountStore,
      NetworkUtilsWrapper networkUtilsWrapper, ResourceProvider resourceProvider,
      StatsUtils statsUtils, TodayInsightsStore todayInsightsStore, WidgetUtils widgetUtils,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new MinifiedWidgetUpdater(defaultDispatcher, appPrefsWrapper, siteStore, accountStore, networkUtilsWrapper, resourceProvider, statsUtils, todayInsightsStore, widgetUtils, analyticsTrackerWrapper);
  }
}
