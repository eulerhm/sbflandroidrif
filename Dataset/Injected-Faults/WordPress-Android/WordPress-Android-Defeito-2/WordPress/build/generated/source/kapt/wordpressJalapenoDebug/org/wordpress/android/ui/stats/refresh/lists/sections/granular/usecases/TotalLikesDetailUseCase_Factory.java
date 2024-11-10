package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.network.utils.StatsGranularity;
import org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore;
import org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalStatsMapper;
import org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
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
public final class TotalLikesDetailUseCase_Factory implements Factory<TotalLikesDetailUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<StatsGranularity> statsGranularityProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider;

  private final Provider<LatestPostInsightsStore> latestPostStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<TotalStatsMapper> totalStatsMapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  public TotalLikesDetailUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<StatsGranularity> statsGranularityProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<LatestPostInsightsStore> latestPostStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ResourceProvider> resourceProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.statsGranularityProvider = statsGranularityProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.visitsAndViewsStoreProvider = visitsAndViewsStoreProvider;
    this.latestPostStoreProvider = latestPostStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.resourceProvider = resourceProvider;
    this.totalStatsMapperProvider = totalStatsMapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
  }

  @Override
  public TotalLikesDetailUseCase get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), statsGranularityProvider.get(), selectedDateProvider.get(), visitsAndViewsStoreProvider.get(), latestPostStoreProvider.get(), statsSiteProvider.get(), resourceProvider.get(), totalStatsMapperProvider.get(), analyticsTrackerProvider.get(), statsWidgetUpdatersProvider.get());
  }

  public static TotalLikesDetailUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<StatsGranularity> statsGranularityProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<LatestPostInsightsStore> latestPostStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ResourceProvider> resourceProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider) {
    return new TotalLikesDetailUseCase_Factory(mainDispatcherProvider, bgDispatcherProvider, statsGranularityProvider, selectedDateProvider, visitsAndViewsStoreProvider, latestPostStoreProvider, statsSiteProvider, resourceProvider, totalStatsMapperProvider, analyticsTrackerProvider, statsWidgetUpdatersProvider);
  }

  public static TotalLikesDetailUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, StatsGranularity statsGranularity,
      SelectedDateProvider selectedDateProvider, VisitsAndViewsStore visitsAndViewsStore,
      LatestPostInsightsStore latestPostStore, StatsSiteProvider statsSiteProvider,
      ResourceProvider resourceProvider, TotalStatsMapper totalStatsMapper,
      AnalyticsTrackerWrapper analyticsTracker,
      WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters) {
    return new TotalLikesDetailUseCase(mainDispatcher, bgDispatcher, statsGranularity, selectedDateProvider, visitsAndViewsStore, latestPostStore, statsSiteProvider, resourceProvider, totalStatsMapper, analyticsTracker, statsWidgetUpdaters);
  }
}
