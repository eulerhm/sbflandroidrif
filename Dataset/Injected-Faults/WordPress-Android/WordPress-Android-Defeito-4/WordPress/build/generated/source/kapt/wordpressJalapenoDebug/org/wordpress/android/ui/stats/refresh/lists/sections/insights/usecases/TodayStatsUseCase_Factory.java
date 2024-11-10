package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.TodayInsightsStore;
import org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
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
public final class TodayStatsUseCase_Factory implements Factory<TodayStatsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<TodayInsightsStore> todayStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  public TodayStatsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<TodayInsightsStore> todayStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.todayStoreProvider = todayStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
  }

  @Override
  public TodayStatsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), todayStoreProvider.get(), statsSiteProvider.get(), statsWidgetUpdatersProvider.get(), statsUtilsProvider.get(), popupMenuHandlerProvider.get());
  }

  public static TodayStatsUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<TodayInsightsStore> todayStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    return new TodayStatsUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, todayStoreProvider, statsSiteProvider, statsWidgetUpdatersProvider, statsUtilsProvider, popupMenuHandlerProvider);
  }

  public static TodayStatsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, TodayInsightsStore todayStore,
      StatsSiteProvider statsSiteProvider, WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters,
      StatsUtils statsUtils, ItemPopupMenuHandler popupMenuHandler) {
    return new TodayStatsUseCase(mainDispatcher, backgroundDispatcher, todayStore, statsSiteProvider, statsWidgetUpdaters, statsUtils, popupMenuHandler);
  }
}
