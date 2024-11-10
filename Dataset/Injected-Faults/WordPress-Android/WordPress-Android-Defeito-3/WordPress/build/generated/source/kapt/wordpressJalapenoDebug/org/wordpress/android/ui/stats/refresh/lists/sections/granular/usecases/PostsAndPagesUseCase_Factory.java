package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.network.utils.StatsGranularity;
import org.wordpress.android.fluxc.store.stats.time.PostAndPageViewsStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
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
public final class PostsAndPagesUseCase_Factory implements Factory<PostsAndPagesUseCase> {
  private final Provider<StatsGranularity> statsGranularityProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<PostAndPageViewsStore> postsAndPageViewsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider;

  public PostsAndPagesUseCase_Factory(Provider<StatsGranularity> statsGranularityProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostAndPageViewsStore> postsAndPageViewsStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    this.statsGranularityProvider = statsGranularityProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.postsAndPageViewsStoreProvider = postsAndPageViewsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.useCaseModeProvider = useCaseModeProvider;
  }

  @Override
  public PostsAndPagesUseCase get() {
    return newInstance(statsGranularityProvider.get(), mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), postsAndPageViewsStoreProvider.get(), statsSiteProvider.get(), selectedDateProvider.get(), analyticsTrackerProvider.get(), contentDescriptionHelperProvider.get(), statsUtilsProvider.get(), useCaseModeProvider.get());
  }

  public static PostsAndPagesUseCase_Factory create(
      Provider<StatsGranularity> statsGranularityProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostAndPageViewsStore> postsAndPageViewsStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<BaseStatsUseCase.UseCaseMode> useCaseModeProvider) {
    return new PostsAndPagesUseCase_Factory(statsGranularityProvider, mainDispatcherProvider, backgroundDispatcherProvider, postsAndPageViewsStoreProvider, statsSiteProvider, selectedDateProvider, analyticsTrackerProvider, contentDescriptionHelperProvider, statsUtilsProvider, useCaseModeProvider);
  }

  public static PostsAndPagesUseCase newInstance(StatsGranularity statsGranularity,
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      PostAndPageViewsStore postsAndPageViewsStore, StatsSiteProvider statsSiteProvider,
      SelectedDateProvider selectedDateProvider, AnalyticsTrackerWrapper analyticsTracker,
      ContentDescriptionHelper contentDescriptionHelper, StatsUtils statsUtils,
      BaseStatsUseCase.UseCaseMode useCaseMode) {
    return new PostsAndPagesUseCase(statsGranularity, mainDispatcher, backgroundDispatcher, postsAndPageViewsStore, statsSiteProvider, selectedDateProvider, analyticsTracker, contentDescriptionHelper, statsUtils, useCaseMode);
  }
}
