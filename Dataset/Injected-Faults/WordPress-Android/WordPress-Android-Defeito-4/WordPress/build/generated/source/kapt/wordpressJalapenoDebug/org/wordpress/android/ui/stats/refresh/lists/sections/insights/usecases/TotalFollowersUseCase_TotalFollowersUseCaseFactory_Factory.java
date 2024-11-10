package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.SummaryStore;
import org.wordpress.android.ui.stats.refresh.utils.ActionCardHandler;
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
public final class TotalFollowersUseCase_TotalFollowersUseCaseFactory_Factory implements Factory<TotalFollowersUseCase.TotalFollowersUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<SummaryStore> summaryStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<TotalStatsMapper> totalStatsMapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ActionCardHandler> actionCardHandlerProvider;

  public TotalFollowersUseCase_TotalFollowersUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<SummaryStore> summaryStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.summaryStoreProvider = summaryStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.resourceProvider = resourceProvider;
    this.totalStatsMapperProvider = totalStatsMapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.actionCardHandlerProvider = actionCardHandlerProvider;
  }

  @Override
  public TotalFollowersUseCase.TotalFollowersUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), summaryStoreProvider.get(), statsSiteProvider.get(), resourceProvider.get(), totalStatsMapperProvider.get(), analyticsTrackerProvider.get(), actionCardHandlerProvider.get());
  }

  public static TotalFollowersUseCase_TotalFollowersUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<SummaryStore> summaryStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider) {
    return new TotalFollowersUseCase_TotalFollowersUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, summaryStoreProvider, statsSiteProvider, resourceProvider, totalStatsMapperProvider, analyticsTrackerProvider, actionCardHandlerProvider);
  }

  public static TotalFollowersUseCase.TotalFollowersUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      SummaryStore summaryStore, StatsSiteProvider statsSiteProvider,
      ResourceProvider resourceProvider, TotalStatsMapper totalStatsMapper,
      AnalyticsTrackerWrapper analyticsTracker, ActionCardHandler actionCardHandler) {
    return new TotalFollowersUseCase.TotalFollowersUseCaseFactory(mainDispatcher, backgroundDispatcher, summaryStore, statsSiteProvider, resourceProvider, totalStatsMapper, analyticsTracker, actionCardHandler);
  }
}
