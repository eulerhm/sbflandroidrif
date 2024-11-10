package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.PublicizeStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.ServiceMapper;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class PublicizeUseCase_Factory implements Factory<PublicizeUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<PublicizeStore> publicizeStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ServiceMapper> mapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  private final Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider;

  public PublicizeUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PublicizeStore> publicizeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ServiceMapper> mapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.publicizeStoreProvider = publicizeStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.mapperProvider = mapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
    this.useCaseModeProvider = useCaseModeProvider;
  }

  @Override
  public PublicizeUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), publicizeStoreProvider.get(), statsSiteProvider.get(), mapperProvider.get(), analyticsTrackerProvider.get(), popupMenuHandlerProvider.get(), useCaseModeProvider.get());
  }

  public static PublicizeUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PublicizeStore> publicizeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ServiceMapper> mapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    return new PublicizeUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, publicizeStoreProvider, statsSiteProvider, mapperProvider, analyticsTrackerProvider, popupMenuHandlerProvider, useCaseModeProvider);
  }

  public static PublicizeUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, PublicizeStore publicizeStore,
      StatsSiteProvider statsSiteProvider, ServiceMapper mapper,
      AnalyticsTrackerWrapper analyticsTracker, ItemPopupMenuHandler popupMenuHandler,
      BaseStatsUseCase.UseCaseMode useCaseMode) {
    return new PublicizeUseCase(mainDispatcher, backgroundDispatcher, publicizeStore, statsSiteProvider, mapper, analyticsTracker, popupMenuHandler, useCaseMode);
  }
}
