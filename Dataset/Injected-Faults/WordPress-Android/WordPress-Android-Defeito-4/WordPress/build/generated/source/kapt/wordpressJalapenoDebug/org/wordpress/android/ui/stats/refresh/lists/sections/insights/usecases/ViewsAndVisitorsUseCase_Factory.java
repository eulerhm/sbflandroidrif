package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.network.utils.StatsGranularity;
import org.wordpress.android.fluxc.store.StatsStore;
import org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
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
public final class ViewsAndVisitorsUseCase_Factory implements Factory<ViewsAndVisitorsUseCase> {
  private final Provider<StatsStore.StatsType> statsTypeProvider;

  private final Provider<StatsGranularity> statsGranularityProvider;

  private final Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<ViewsAndVisitorsMapper> viewsAndVisitorsMapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider;

  public ViewsAndVisitorsUseCase_Factory(Provider<StatsStore.StatsType> statsTypeProvider,
      Provider<StatsGranularity> statsGranularityProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<ViewsAndVisitorsMapper> viewsAndVisitorsMapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    this.statsTypeProvider = statsTypeProvider;
    this.statsGranularityProvider = statsGranularityProvider;
    this.visitsAndViewsStoreProvider = visitsAndViewsStoreProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.viewsAndVisitorsMapperProvider = viewsAndVisitorsMapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.resourceProvider = resourceProvider;
    this.useCaseModeProvider = useCaseModeProvider;
  }

  @Override
  public ViewsAndVisitorsUseCase get() {
    return newInstance(statsTypeProvider.get(), statsGranularityProvider.get(), visitsAndViewsStoreProvider.get(), selectedDateProvider.get(), statsSiteProvider.get(), statsDateFormatterProvider.get(), viewsAndVisitorsMapperProvider.get(), mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), analyticsTrackerProvider.get(), statsWidgetUpdatersProvider.get(), localeManagerWrapperProvider.get(), resourceProvider.get(), useCaseModeProvider.get());
  }

  public static ViewsAndVisitorsUseCase_Factory create(
      Provider<StatsStore.StatsType> statsTypeProvider,
      Provider<StatsGranularity> statsGranularityProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<ViewsAndVisitorsMapper> viewsAndVisitorsMapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    return new ViewsAndVisitorsUseCase_Factory(statsTypeProvider, statsGranularityProvider, visitsAndViewsStoreProvider, selectedDateProvider, statsSiteProvider, statsDateFormatterProvider, viewsAndVisitorsMapperProvider, mainDispatcherProvider, backgroundDispatcherProvider, analyticsTrackerProvider, statsWidgetUpdatersProvider, localeManagerWrapperProvider, resourceProvider, useCaseModeProvider);
  }

  public static ViewsAndVisitorsUseCase newInstance(StatsStore.StatsType statsType,
      StatsGranularity statsGranularity, VisitsAndViewsStore visitsAndViewsStore,
      SelectedDateProvider selectedDateProvider, StatsSiteProvider statsSiteProvider,
      StatsDateFormatter statsDateFormatter, ViewsAndVisitorsMapper viewsAndVisitorsMapper,
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      AnalyticsTrackerWrapper analyticsTracker,
      WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters,
      LocaleManagerWrapper localeManagerWrapper, ResourceProvider resourceProvider,
      BaseStatsUseCase.UseCaseMode useCaseMode) {
    return new ViewsAndVisitorsUseCase(statsType, statsGranularity, visitsAndViewsStore, selectedDateProvider, statsSiteProvider, statsDateFormatter, viewsAndVisitorsMapper, mainDispatcher, backgroundDispatcher, analyticsTracker, statsWidgetUpdaters, localeManagerWrapper, resourceProvider, useCaseMode);
  }
}
