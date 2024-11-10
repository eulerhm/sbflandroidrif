package org.wordpress.android.ui.sitecreation.theme;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.sitecreation.misc.SiteCreationTracker;
import org.wordpress.android.ui.sitecreation.usecases.FetchHomePageLayoutsUseCase;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class HomePagePickerViewModel_Factory implements Factory<HomePagePickerViewModel> {
  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<FetchHomePageLayoutsUseCase> fetchHomePageLayoutsUseCaseProvider;

  private final Provider<SiteCreationTracker> analyticsTrackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<SiteDesignRecommendationProvider> recommendationProvider;

  public HomePagePickerViewModel_Factory(Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<FetchHomePageLayoutsUseCase> fetchHomePageLayoutsUseCaseProvider,
      Provider<SiteCreationTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<SiteDesignRecommendationProvider> recommendationProvider) {
    this.networkUtilsProvider = networkUtilsProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.fetchHomePageLayoutsUseCaseProvider = fetchHomePageLayoutsUseCaseProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.recommendationProvider = recommendationProvider;
  }

  @Override
  public HomePagePickerViewModel get() {
    return newInstance(networkUtilsProvider.get(), dispatcherProvider.get(), fetchHomePageLayoutsUseCaseProvider.get(), analyticsTrackerProvider.get(), bgDispatcherProvider.get(), mainDispatcherProvider.get(), recommendationProvider.get());
  }

  public static HomePagePickerViewModel_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<FetchHomePageLayoutsUseCase> fetchHomePageLayoutsUseCaseProvider,
      Provider<SiteCreationTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<SiteDesignRecommendationProvider> recommendationProvider) {
    return new HomePagePickerViewModel_Factory(networkUtilsProvider, dispatcherProvider, fetchHomePageLayoutsUseCaseProvider, analyticsTrackerProvider, bgDispatcherProvider, mainDispatcherProvider, recommendationProvider);
  }

  public static HomePagePickerViewModel newInstance(NetworkUtilsWrapper networkUtils,
      Dispatcher dispatcher, FetchHomePageLayoutsUseCase fetchHomePageLayoutsUseCase,
      SiteCreationTracker analyticsTracker, CoroutineDispatcher bgDispatcher,
      CoroutineDispatcher mainDispatcher, SiteDesignRecommendationProvider recommendationProvider) {
    return new HomePagePickerViewModel(networkUtils, dispatcher, fetchHomePageLayoutsUseCase, analyticsTracker, bgDispatcher, mainDispatcher, recommendationProvider);
  }
}
