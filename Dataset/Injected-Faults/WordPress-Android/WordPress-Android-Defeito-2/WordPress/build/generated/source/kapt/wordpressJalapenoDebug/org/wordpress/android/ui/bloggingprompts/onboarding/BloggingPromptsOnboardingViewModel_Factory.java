package org.wordpress.android.ui.bloggingprompts.onboarding;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;

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
public final class BloggingPromptsOnboardingViewModel_Factory implements Factory<BloggingPromptsOnboardingViewModel> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<BloggingPromptsOnboardingUiStateMapper> uiStateMapperProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<BloggingPromptsStore> bloggingPromptsStoreProvider;

  private final Provider<BloggingPromptsOnboardingAnalyticsTracker> analyticsTrackerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public BloggingPromptsOnboardingViewModel_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<BloggingPromptsOnboardingUiStateMapper> uiStateMapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<BloggingPromptsOnboardingAnalyticsTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.uiStateMapperProvider = uiStateMapperProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.bloggingPromptsStoreProvider = bloggingPromptsStoreProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public BloggingPromptsOnboardingViewModel get() {
    return newInstance(siteStoreProvider.get(), uiStateMapperProvider.get(), selectedSiteRepositoryProvider.get(), bloggingPromptsStoreProvider.get(), analyticsTrackerProvider.get(), bgDispatcherProvider.get());
  }

  public static BloggingPromptsOnboardingViewModel_Factory create(
      Provider<SiteStore> siteStoreProvider,
      Provider<BloggingPromptsOnboardingUiStateMapper> uiStateMapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<BloggingPromptsOnboardingAnalyticsTracker> analyticsTrackerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new BloggingPromptsOnboardingViewModel_Factory(siteStoreProvider, uiStateMapperProvider, selectedSiteRepositoryProvider, bloggingPromptsStoreProvider, analyticsTrackerProvider, bgDispatcherProvider);
  }

  public static BloggingPromptsOnboardingViewModel newInstance(SiteStore siteStore,
      BloggingPromptsOnboardingUiStateMapper uiStateMapper,
      SelectedSiteRepository selectedSiteRepository, BloggingPromptsStore bloggingPromptsStore,
      BloggingPromptsOnboardingAnalyticsTracker analyticsTracker,
      CoroutineDispatcher bgDispatcher) {
    return new BloggingPromptsOnboardingViewModel(siteStore, uiStateMapper, selectedSiteRepository, bloggingPromptsStore, analyticsTracker, bgDispatcher);
  }
}
