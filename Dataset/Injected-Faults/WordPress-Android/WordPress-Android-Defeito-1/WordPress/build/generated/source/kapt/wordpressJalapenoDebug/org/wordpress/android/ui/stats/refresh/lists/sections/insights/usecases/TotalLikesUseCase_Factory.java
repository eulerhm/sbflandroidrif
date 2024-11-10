package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore;
import org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore;
import org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.util.LocaleManagerWrapper;
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
public final class TotalLikesUseCase_Factory implements Factory<TotalLikesUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider;

  private final Provider<LatestPostInsightsStore> latestPostStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<TotalStatsMapper> totalStatsMapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public TotalLikesUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<LatestPostInsightsStore> latestPostStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ResourceProvider> resourceProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.visitsAndViewsStoreProvider = visitsAndViewsStoreProvider;
    this.latestPostStoreProvider = latestPostStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.resourceProvider = resourceProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.totalStatsMapperProvider = totalStatsMapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  @Override
  public TotalLikesUseCase get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), visitsAndViewsStoreProvider.get(), latestPostStoreProvider.get(), statsSiteProvider.get(), resourceProvider.get(), statsDateFormatterProvider.get(), totalStatsMapperProvider.get(), analyticsTrackerProvider.get(), statsWidgetUpdatersProvider.get(), localeManagerWrapperProvider.get());
  }

  public static TotalLikesUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<LatestPostInsightsStore> latestPostStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ResourceProvider> resourceProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new TotalLikesUseCase_Factory(mainDispatcherProvider, bgDispatcherProvider, visitsAndViewsStoreProvider, latestPostStoreProvider, statsSiteProvider, resourceProvider, statsDateFormatterProvider, totalStatsMapperProvider, analyticsTrackerProvider, statsWidgetUpdatersProvider, localeManagerWrapperProvider);
  }

  public static TotalLikesUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, VisitsAndViewsStore visitsAndViewsStore,
      LatestPostInsightsStore latestPostStore, StatsSiteProvider statsSiteProvider,
      ResourceProvider resourceProvider, StatsDateFormatter statsDateFormatter,
      TotalStatsMapper totalStatsMapper, AnalyticsTrackerWrapper analyticsTracker,
      WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters,
      LocaleManagerWrapper localeManagerWrapper) {
    return new TotalLikesUseCase(mainDispatcher, bgDispatcher, visitsAndViewsStore, latestPostStore, statsSiteProvider, resourceProvider, statsDateFormatter, totalStatsMapper, analyticsTracker, statsWidgetUpdaters, localeManagerWrapper);
  }
}
