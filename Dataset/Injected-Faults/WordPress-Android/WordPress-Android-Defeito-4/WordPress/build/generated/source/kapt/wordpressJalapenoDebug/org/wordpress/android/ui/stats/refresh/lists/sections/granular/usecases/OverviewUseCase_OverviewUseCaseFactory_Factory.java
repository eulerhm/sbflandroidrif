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
public final class OverviewUseCase_OverviewUseCaseFactory_Factory implements Factory<OverviewUseCase.OverviewUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<OverviewMapper> overviewMapperProvider;

  private final Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public OverviewUseCase_OverviewUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<OverviewMapper> overviewMapperProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.overviewMapperProvider = overviewMapperProvider;
    this.visitsAndViewsStoreProvider = visitsAndViewsStoreProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public OverviewUseCase.OverviewUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), statsSiteProvider.get(), selectedDateProvider.get(), statsDateFormatterProvider.get(), overviewMapperProvider.get(), visitsAndViewsStoreProvider.get(), analyticsTrackerProvider.get(), statsWidgetUpdatersProvider.get(), localeManagerWrapperProvider.get(), resourceProvider.get());
  }

  public static OverviewUseCase_OverviewUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<OverviewMapper> overviewMapperProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new OverviewUseCase_OverviewUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, statsSiteProvider, selectedDateProvider, statsDateFormatterProvider, overviewMapperProvider, visitsAndViewsStoreProvider, analyticsTrackerProvider, statsWidgetUpdatersProvider, localeManagerWrapperProvider, resourceProvider);
  }

  public static OverviewUseCase.OverviewUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      StatsSiteProvider statsSiteProvider, SelectedDateProvider selectedDateProvider,
      StatsDateFormatter statsDateFormatter, OverviewMapper overviewMapper,
      VisitsAndViewsStore visitsAndViewsStore, AnalyticsTrackerWrapper analyticsTracker,
      WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters,
      LocaleManagerWrapper localeManagerWrapper, ResourceProvider resourceProvider) {
    return new OverviewUseCase.OverviewUseCaseFactory(mainDispatcher, backgroundDispatcher, statsSiteProvider, selectedDateProvider, statsDateFormatter, overviewMapper, visitsAndViewsStore, analyticsTracker, statsWidgetUpdaters, localeManagerWrapper, resourceProvider);
  }
}
