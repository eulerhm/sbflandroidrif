package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.PostDetailStore;
import org.wordpress.android.ui.stats.refresh.utils.StatsPostProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;

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
public final class PostAverageViewsPerDayUseCase_PostAverageViewsPerDayUseCaseFactory_Factory implements Factory<PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsPostProvider> statsPostProvider;

  private final Provider<PostDetailMapper> postDetailMapperProvider;

  private final Provider<PostDetailStore> postDetailStoreProvider;

  public PostAverageViewsPerDayUseCase_PostAverageViewsPerDayUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsPostProvider> statsPostProvider,
      Provider<PostDetailMapper> postDetailMapperProvider,
      Provider<PostDetailStore> postDetailStoreProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsPostProvider = statsPostProvider;
    this.postDetailMapperProvider = postDetailMapperProvider;
    this.postDetailStoreProvider = postDetailStoreProvider;
  }

  @Override
  public PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), statsSiteProvider.get(), statsPostProvider.get(), postDetailMapperProvider.get(), postDetailStoreProvider.get());
  }

  public static PostAverageViewsPerDayUseCase_PostAverageViewsPerDayUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsPostProvider> statsPostProvider,
      Provider<PostDetailMapper> postDetailMapperProvider,
      Provider<PostDetailStore> postDetailStoreProvider) {
    return new PostAverageViewsPerDayUseCase_PostAverageViewsPerDayUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, statsSiteProvider, statsPostProvider, postDetailMapperProvider, postDetailStoreProvider);
  }

  public static PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      StatsSiteProvider statsSiteProvider, StatsPostProvider statsPostProvider,
      PostDetailMapper postDetailMapper, PostDetailStore postDetailStore) {
    return new PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory(mainDispatcher, backgroundDispatcher, statsSiteProvider, statsPostProvider, postDetailMapper, postDetailStore);
  }
}
