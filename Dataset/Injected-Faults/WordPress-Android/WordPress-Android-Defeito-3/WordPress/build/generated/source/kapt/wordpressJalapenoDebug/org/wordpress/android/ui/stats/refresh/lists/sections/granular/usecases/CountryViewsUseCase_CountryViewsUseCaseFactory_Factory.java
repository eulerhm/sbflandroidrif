package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.CountryViewsStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
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
public final class CountryViewsUseCase_CountryViewsUseCaseFactory_Factory implements Factory<CountryViewsUseCase.CountryViewsUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<CountryViewsStore> storeProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public CountryViewsUseCase_CountryViewsUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CountryViewsStore> storeProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.storeProvider = storeProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public CountryViewsUseCase.CountryViewsUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), storeProvider.get(), statsSiteProvider.get(), selectedDateProvider.get(), analyticsTrackerProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static CountryViewsUseCase_CountryViewsUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CountryViewsStore> storeProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new CountryViewsUseCase_CountryViewsUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, storeProvider, statsSiteProvider, selectedDateProvider, analyticsTrackerProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static CountryViewsUseCase.CountryViewsUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      CountryViewsStore store, StatsSiteProvider statsSiteProvider,
      SelectedDateProvider selectedDateProvider, AnalyticsTrackerWrapper analyticsTracker,
      StatsUtils statsUtils, ContentDescriptionHelper contentDescriptionHelper) {
    return new CountryViewsUseCase.CountryViewsUseCaseFactory(mainDispatcher, backgroundDispatcher, store, statsSiteProvider, selectedDateProvider, analyticsTracker, statsUtils, contentDescriptionHelper);
  }
}
