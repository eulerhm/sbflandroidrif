package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsMapper;
import org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
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
public final class ViewsAndVisitorsDetailUseCase_ViewsAndVisitorsGranularUseCaseFactory_Factory implements Factory<ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<ViewsAndVisitorsMapper> viewsAndVisitorsMapperProvider;

  private final Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public ViewsAndVisitorsDetailUseCase_ViewsAndVisitorsGranularUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<ViewsAndVisitorsMapper> viewsAndVisitorsMapperProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.viewsAndVisitorsMapperProvider = viewsAndVisitorsMapperProvider;
    this.visitsAndViewsStoreProvider = visitsAndViewsStoreProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), statsSiteProvider.get(), selectedDateProvider.get(), statsDateFormatterProvider.get(), viewsAndVisitorsMapperProvider.get(), visitsAndViewsStoreProvider.get(), analyticsTrackerProvider.get(), statsWidgetUpdatersProvider.get(), resourceProvider.get());
  }

  public static ViewsAndVisitorsDetailUseCase_ViewsAndVisitorsGranularUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<ViewsAndVisitorsMapper> viewsAndVisitorsMapperProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new ViewsAndVisitorsDetailUseCase_ViewsAndVisitorsGranularUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, statsSiteProvider, selectedDateProvider, statsDateFormatterProvider, viewsAndVisitorsMapperProvider, visitsAndViewsStoreProvider, analyticsTrackerProvider, statsWidgetUpdatersProvider, resourceProvider);
  }

  public static ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      StatsSiteProvider statsSiteProvider, SelectedDateProvider selectedDateProvider,
      StatsDateFormatter statsDateFormatter, ViewsAndVisitorsMapper viewsAndVisitorsMapper,
      VisitsAndViewsStore visitsAndViewsStore, AnalyticsTrackerWrapper analyticsTracker,
      WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters, ResourceProvider resourceProvider) {
    return new ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory(mainDispatcher, backgroundDispatcher, statsSiteProvider, selectedDateProvider, statsDateFormatter, viewsAndVisitorsMapper, visitsAndViewsStore, analyticsTracker, statsWidgetUpdaters, resourceProvider);
  }
}
