package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.SummaryStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
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
public final class TotalFollowersUseCase_Factory implements Factory<TotalFollowersUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<SummaryStore> summaryStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<TotalStatsMapper> totalStatsMapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ActionCardHandler> actionCardHandlerProvider;

  private final Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider;

  public TotalFollowersUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<SummaryStore> summaryStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.summaryStoreProvider = summaryStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.resourceProvider = resourceProvider;
    this.totalStatsMapperProvider = totalStatsMapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.actionCardHandlerProvider = actionCardHandlerProvider;
    this.useCaseModeProvider = useCaseModeProvider;
  }

  @Override
  public TotalFollowersUseCase get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), summaryStoreProvider.get(), statsSiteProvider.get(), resourceProvider.get(), totalStatsMapperProvider.get(), analyticsTrackerProvider.get(), actionCardHandlerProvider.get(), useCaseModeProvider.get());
  }

  public static TotalFollowersUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<SummaryStore> summaryStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<TotalStatsMapper> totalStatsMapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    return new TotalFollowersUseCase_Factory(mainDispatcherProvider, bgDispatcherProvider, summaryStoreProvider, statsSiteProvider, resourceProvider, totalStatsMapperProvider, analyticsTrackerProvider, actionCardHandlerProvider, useCaseModeProvider);
  }

  public static TotalFollowersUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, SummaryStore summaryStore,
      StatsSiteProvider statsSiteProvider, ResourceProvider resourceProvider,
      TotalStatsMapper totalStatsMapper, AnalyticsTrackerWrapper analyticsTracker,
      ActionCardHandler actionCardHandler, BaseStatsUseCase.UseCaseMode useCaseMode) {
    return new TotalFollowersUseCase(mainDispatcher, bgDispatcher, summaryStore, statsSiteProvider, resourceProvider, totalStatsMapper, analyticsTracker, actionCardHandler, useCaseMode);
  }
}
