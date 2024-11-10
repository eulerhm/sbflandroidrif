package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.PublicizeStore;
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
public final class PublicizeUseCase_PublicizeUseCaseFactory_Factory implements Factory<PublicizeUseCase.PublicizeUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<PublicizeStore> publicizeStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ServiceMapper> mapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  public PublicizeUseCase_PublicizeUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PublicizeStore> publicizeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ServiceMapper> mapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.publicizeStoreProvider = publicizeStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.mapperProvider = mapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
  }

  @Override
  public PublicizeUseCase.PublicizeUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), publicizeStoreProvider.get(), statsSiteProvider.get(), mapperProvider.get(), analyticsTrackerProvider.get(), popupMenuHandlerProvider.get());
  }

  public static PublicizeUseCase_PublicizeUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PublicizeStore> publicizeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<ServiceMapper> mapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    return new PublicizeUseCase_PublicizeUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, publicizeStoreProvider, statsSiteProvider, mapperProvider, analyticsTrackerProvider, popupMenuHandlerProvider);
  }

  public static PublicizeUseCase.PublicizeUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      PublicizeStore publicizeStore, StatsSiteProvider statsSiteProvider, ServiceMapper mapper,
      AnalyticsTrackerWrapper analyticsTracker, ItemPopupMenuHandler popupMenuHandler) {
    return new PublicizeUseCase.PublicizeUseCaseFactory(mainDispatcher, backgroundDispatcher, publicizeStore, statsSiteProvider, mapper, analyticsTracker, popupMenuHandler);
  }
}
