package org.wordpress.android.ui.mysite.cards.dashboard;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.dashboard.CardsStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig;

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
public final class CardsSource_Factory implements Factory<CardsSource> {
  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<CardsStore> cardsStoreProvider;

  private final Provider<MySiteDashboardTodaysStatsCardFeatureConfig> todaysStatsCardFeatureConfigProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public CardsSource_Factory(Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<CardsStore> cardsStoreProvider,
      Provider<MySiteDashboardTodaysStatsCardFeatureConfig> todaysStatsCardFeatureConfigProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.cardsStoreProvider = cardsStoreProvider;
    this.todaysStatsCardFeatureConfigProvider = todaysStatsCardFeatureConfigProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public CardsSource get() {
    return newInstance(selectedSiteRepositoryProvider.get(), cardsStoreProvider.get(), todaysStatsCardFeatureConfigProvider.get(), bgDispatcherProvider.get());
  }

  public static CardsSource_Factory create(
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<CardsStore> cardsStoreProvider,
      Provider<MySiteDashboardTodaysStatsCardFeatureConfig> todaysStatsCardFeatureConfigProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new CardsSource_Factory(selectedSiteRepositoryProvider, cardsStoreProvider, todaysStatsCardFeatureConfigProvider, bgDispatcherProvider);
  }

  public static CardsSource newInstance(SelectedSiteRepository selectedSiteRepository,
      CardsStore cardsStore,
      MySiteDashboardTodaysStatsCardFeatureConfig todaysStatsCardFeatureConfig,
      CoroutineDispatcher bgDispatcher) {
    return new CardsSource(selectedSiteRepository, cardsStore, todaysStatsCardFeatureConfig, bgDispatcher);
  }
}
