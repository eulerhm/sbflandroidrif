package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.PostAndPageViewsStore;
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
public final class PostsAndPagesUseCase_PostsAndPagesUseCaseFactory_Factory implements Factory<PostsAndPagesUseCase.PostsAndPagesUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<PostAndPageViewsStore> postsAndPageViewsStoreProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  public PostsAndPagesUseCase_PostsAndPagesUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostAndPageViewsStore> postsAndPageViewsStoreProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.postsAndPageViewsStoreProvider = postsAndPageViewsStoreProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
  }

  @Override
  public PostsAndPagesUseCase.PostsAndPagesUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), postsAndPageViewsStoreProvider.get(), selectedDateProvider.get(), statsSiteProvider.get(), contentDescriptionHelperProvider.get(), statsUtilsProvider.get(), analyticsTrackerProvider.get());
  }

  public static PostsAndPagesUseCase_PostsAndPagesUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostAndPageViewsStore> postsAndPageViewsStoreProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider) {
    return new PostsAndPagesUseCase_PostsAndPagesUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, postsAndPageViewsStoreProvider, selectedDateProvider, statsSiteProvider, contentDescriptionHelperProvider, statsUtilsProvider, analyticsTrackerProvider);
  }

  public static PostsAndPagesUseCase.PostsAndPagesUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      PostAndPageViewsStore postsAndPageViewsStore, SelectedDateProvider selectedDateProvider,
      StatsSiteProvider statsSiteProvider, ContentDescriptionHelper contentDescriptionHelper,
      StatsUtils statsUtils, AnalyticsTrackerWrapper analyticsTracker) {
    return new PostsAndPagesUseCase.PostsAndPagesUseCaseFactory(mainDispatcher, backgroundDispatcher, postsAndPageViewsStore, selectedDateProvider, statsSiteProvider, contentDescriptionHelper, statsUtils, analyticsTracker);
  }
}
