package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.AllTimeInsightsStore;
import org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;

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
public final class AllTimeStatsUseCase_Factory implements Factory<AllTimeStatsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<AllTimeInsightsStore> allTimeStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  public AllTimeStatsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<AllTimeInsightsStore> allTimeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.allTimeStoreProvider = allTimeStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
  }

  @Override
  public AllTimeStatsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), allTimeStoreProvider.get(), statsSiteProvider.get(), statsDateFormatterProvider.get(), statsWidgetUpdatersProvider.get(), statsUtilsProvider.get(), popupMenuHandlerProvider.get());
  }

  public static AllTimeStatsUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<AllTimeInsightsStore> allTimeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    return new AllTimeStatsUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, allTimeStoreProvider, statsSiteProvider, statsDateFormatterProvider, statsWidgetUpdatersProvider, statsUtilsProvider, popupMenuHandlerProvider);
  }

  public static AllTimeStatsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, AllTimeInsightsStore allTimeStore,
      StatsSiteProvider statsSiteProvider, StatsDateFormatter statsDateFormatter,
      WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters, StatsUtils statsUtils,
      ItemPopupMenuHandler popupMenuHandler) {
    return new AllTimeStatsUseCase(mainDispatcher, backgroundDispatcher, allTimeStore, statsSiteProvider, statsDateFormatter, statsWidgetUpdaters, statsUtils, popupMenuHandler);
  }
}
