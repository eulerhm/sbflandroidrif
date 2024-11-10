package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.MostPopularInsightsStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.util.LocaleManagerWrapper;

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
public final class AnnualSiteStatsUseCase_AnnualSiteStatsUseCaseFactory_Factory implements Factory<AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<MostPopularInsightsStore> mostPopularStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<AnnualStatsMapper> annualStatsMapperProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  public AnnualSiteStatsUseCase_AnnualSiteStatsUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<MostPopularInsightsStore> mostPopularStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnnualStatsMapper> annualStatsMapperProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.mostPopularStoreProvider = mostPopularStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.annualStatsMapperProvider = annualStatsMapperProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
  }

  @Override
  public AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), mostPopularStoreProvider.get(), statsSiteProvider.get(), annualStatsMapperProvider.get(), localeManagerWrapperProvider.get(), selectedDateProvider.get(), popupMenuHandlerProvider.get());
  }

  public static AnnualSiteStatsUseCase_AnnualSiteStatsUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<MostPopularInsightsStore> mostPopularStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnnualStatsMapper> annualStatsMapperProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    return new AnnualSiteStatsUseCase_AnnualSiteStatsUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, mostPopularStoreProvider, statsSiteProvider, annualStatsMapperProvider, localeManagerWrapperProvider, selectedDateProvider, popupMenuHandlerProvider);
  }

  public static AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      MostPopularInsightsStore mostPopularStore, StatsSiteProvider statsSiteProvider,
      AnnualStatsMapper annualStatsMapper, LocaleManagerWrapper localeManagerWrapper,
      SelectedDateProvider selectedDateProvider, ItemPopupMenuHandler popupMenuHandler) {
    return new AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory(mainDispatcher, backgroundDispatcher, mostPopularStore, statsSiteProvider, annualStatsMapper, localeManagerWrapper, selectedDateProvider, popupMenuHandler);
  }
}
