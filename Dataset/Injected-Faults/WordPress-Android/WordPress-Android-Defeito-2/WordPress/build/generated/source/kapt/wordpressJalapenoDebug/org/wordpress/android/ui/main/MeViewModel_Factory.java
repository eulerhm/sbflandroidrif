package org.wordpress.android.ui.main;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.models.recommend.RecommendApiCallsProvider;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class MeViewModel_Factory implements Factory<MeViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<RecommendApiCallsProvider> recommendApiCallsProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public MeViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<RecommendApiCallsProvider> recommendApiCallsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.recommendApiCallsProvider = recommendApiCallsProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public MeViewModel get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), selectedSiteRepositoryProvider.get(), recommendApiCallsProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static MeViewModel_Factory create(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<RecommendApiCallsProvider> recommendApiCallsProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new MeViewModel_Factory(mainDispatcherProvider, bgDispatcherProvider, selectedSiteRepositoryProvider, recommendApiCallsProvider, analyticsUtilsWrapperProvider);
  }

  public static MeViewModel newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, SelectedSiteRepository selectedSiteRepository,
      RecommendApiCallsProvider recommendApiCallsProvider,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new MeViewModel(mainDispatcher, bgDispatcher, selectedSiteRepository, recommendApiCallsProvider, analyticsUtilsWrapper);
  }
}
