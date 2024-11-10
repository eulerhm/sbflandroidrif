package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.SearchTermsStore;
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
public final class SearchTermsUseCase_SearchTermsUseCaseFactory_Factory implements Factory<SearchTermsUseCase.SearchTermsUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<SearchTermsStore> storeProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public SearchTermsUseCase_SearchTermsUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<SearchTermsStore> storeProvider, Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.storeProvider = storeProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public SearchTermsUseCase.SearchTermsUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), storeProvider.get(), selectedDateProvider.get(), statsSiteProvider.get(), analyticsTrackerProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static SearchTermsUseCase_SearchTermsUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<SearchTermsStore> storeProvider, Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new SearchTermsUseCase_SearchTermsUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, storeProvider, selectedDateProvider, statsSiteProvider, analyticsTrackerProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static SearchTermsUseCase.SearchTermsUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      SearchTermsStore store, SelectedDateProvider selectedDateProvider,
      StatsSiteProvider statsSiteProvider, AnalyticsTrackerWrapper analyticsTracker,
      StatsUtils statsUtils, ContentDescriptionHelper contentDescriptionHelper) {
    return new SearchTermsUseCase.SearchTermsUseCaseFactory(mainDispatcher, backgroundDispatcher, store, selectedDateProvider, statsSiteProvider, analyticsTracker, statsUtils, contentDescriptionHelper);
  }
}
