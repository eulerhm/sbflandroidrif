package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.AuthorsStore;
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
public final class AuthorsUseCase_AuthorsUseCaseFactory_Factory implements Factory<AuthorsUseCase.AuthorsUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<AuthorsStore> authorsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public AuthorsUseCase_AuthorsUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<AuthorsStore> authorsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.authorsStoreProvider = authorsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public AuthorsUseCase.AuthorsUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), authorsStoreProvider.get(), statsSiteProvider.get(), selectedDateProvider.get(), analyticsTrackerProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static AuthorsUseCase_AuthorsUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<AuthorsStore> authorsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new AuthorsUseCase_AuthorsUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, authorsStoreProvider, statsSiteProvider, selectedDateProvider, analyticsTrackerProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static AuthorsUseCase.AuthorsUseCaseFactory newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, AuthorsStore authorsStore,
      StatsSiteProvider statsSiteProvider, SelectedDateProvider selectedDateProvider,
      AnalyticsTrackerWrapper analyticsTracker, StatsUtils statsUtils,
      ContentDescriptionHelper contentDescriptionHelper) {
    return new AuthorsUseCase.AuthorsUseCaseFactory(mainDispatcher, backgroundDispatcher, authorsStore, statsSiteProvider, selectedDateProvider, analyticsTracker, statsUtils, contentDescriptionHelper);
  }
}
