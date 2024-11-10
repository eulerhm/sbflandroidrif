package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.ClicksStore;
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
public final class ClicksUseCase_ClicksUseCaseFactory_Factory implements Factory<ClicksUseCase.ClicksUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<ClicksStore> storeProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public ClicksUseCase_ClicksUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<ClicksStore> storeProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.storeProvider = storeProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public ClicksUseCase.ClicksUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), storeProvider.get(), statsSiteProvider.get(), selectedDateProvider.get(), contentDescriptionHelperProvider.get(), statsUtilsProvider.get(), analyticsTrackerProvider.get());
  }

  public static ClicksUseCase_ClicksUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<ClicksStore> storeProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new ClicksUseCase_ClicksUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, storeProvider, statsSiteProvider, selectedDateProvider, contentDescriptionHelperProvider, statsUtilsProvider, analyticsTrackerProvider);
  }

  public static ClicksUseCase.ClicksUseCaseFactory newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, ClicksStore store,
      StatsSiteProvider statsSiteProvider, SelectedDateProvider selectedDateProvider,
      ContentDescriptionHelper contentDescriptionHelper, StatsUtils statsUtils,
      AnalyticsTrackerWrapper analyticsTracker) {
    return new ClicksUseCase.ClicksUseCaseFactory(mainDispatcher, backgroundDispatcher, store, statsSiteProvider, selectedDateProvider, contentDescriptionHelper, statsUtils, analyticsTracker);
  }
}
