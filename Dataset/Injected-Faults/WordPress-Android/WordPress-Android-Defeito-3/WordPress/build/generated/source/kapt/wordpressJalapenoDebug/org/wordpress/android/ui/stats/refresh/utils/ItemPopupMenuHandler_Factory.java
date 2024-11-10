package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.StatsStore;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ItemPopupMenuHandler_Factory implements Factory<ItemPopupMenuHandler> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<StatsStore> statsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public ItemPopupMenuHandler_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider, Provider<StatsStore> statsStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsStoreProvider = statsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public ItemPopupMenuHandler get() {
    return newInstance(bgDispatcherProvider.get(), mainDispatcherProvider.get(), statsStoreProvider.get(), statsSiteProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static ItemPopupMenuHandler_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider, Provider<StatsStore> statsStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new ItemPopupMenuHandler_Factory(bgDispatcherProvider, mainDispatcherProvider, statsStoreProvider, statsSiteProvider, analyticsTrackerWrapperProvider);
  }

  public static ItemPopupMenuHandler newInstance(CoroutineDispatcher bgDispatcher,
      CoroutineDispatcher mainDispatcher, StatsStore statsStore,
      StatsSiteProvider statsSiteProvider, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new ItemPopupMenuHandler(bgDispatcher, mainDispatcher, statsStore, statsSiteProvider, analyticsTrackerWrapper);
  }
}
