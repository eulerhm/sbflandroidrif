package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.LatestPostInsightsStore;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.StatsRevampV2FeatureConfig;

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
public final class LatestPostSummaryUseCase_Factory implements Factory<LatestPostSummaryUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<LatestPostInsightsStore> latestPostStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<LatestPostSummaryMapper> latestPostSummaryMapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public LatestPostSummaryUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<LatestPostInsightsStore> latestPostStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<LatestPostSummaryMapper> latestPostSummaryMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.latestPostStoreProvider = latestPostStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.latestPostSummaryMapperProvider = latestPostSummaryMapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsRevampV2FeatureProvider = statsRevampV2FeatureProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public LatestPostSummaryUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), latestPostStoreProvider.get(), statsSiteProvider.get(), latestPostSummaryMapperProvider.get(), analyticsTrackerProvider.get(), statsRevampV2FeatureProvider.get(), popupMenuHandlerProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static LatestPostSummaryUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<LatestPostInsightsStore> latestPostStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<LatestPostSummaryMapper> latestPostSummaryMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new LatestPostSummaryUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, latestPostStoreProvider, statsSiteProvider, latestPostSummaryMapperProvider, analyticsTrackerProvider, statsRevampV2FeatureProvider, popupMenuHandlerProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static LatestPostSummaryUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, LatestPostInsightsStore latestPostStore,
      StatsSiteProvider statsSiteProvider, LatestPostSummaryMapper latestPostSummaryMapper,
      AnalyticsTrackerWrapper analyticsTracker, StatsRevampV2FeatureConfig statsRevampV2Feature,
      ItemPopupMenuHandler popupMenuHandler, StatsUtils statsUtils,
      ContentDescriptionHelper contentDescriptionHelper) {
    return new LatestPostSummaryUseCase(mainDispatcher, backgroundDispatcher, latestPostStore, statsSiteProvider, latestPostSummaryMapper, analyticsTracker, statsRevampV2Feature, popupMenuHandler, statsUtils, contentDescriptionHelper);
  }
}
