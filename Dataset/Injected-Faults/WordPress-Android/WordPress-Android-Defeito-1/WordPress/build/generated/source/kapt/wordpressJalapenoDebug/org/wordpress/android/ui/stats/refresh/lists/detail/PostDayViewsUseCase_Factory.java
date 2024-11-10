package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.PostDetailStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
import org.wordpress.android.ui.stats.refresh.utils.StatsPostProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class PostDayViewsUseCase_Factory implements Factory<PostDayViewsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<PostDayViewsMapper> postDayViewsMapperProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsPostProvider> statsPostProvider;

  private final Provider<PostDetailStore> postDetailStoreProvider;

  private final Provider<ResourceProvider> resourceProvider;

  public PostDayViewsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostDayViewsMapper> postDayViewsMapperProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsPostProvider> statsPostProvider,
      Provider<PostDetailStore> postDetailStoreProvider,
      Provider<ResourceProvider> resourceProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.postDayViewsMapperProvider = postDayViewsMapperProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsPostProvider = statsPostProvider;
    this.postDetailStoreProvider = postDetailStoreProvider;
    this.resourceProvider = resourceProvider;
  }

  @Override
  public PostDayViewsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), postDayViewsMapperProvider.get(), statsDateFormatterProvider.get(), selectedDateProvider.get(), statsSiteProvider.get(), statsPostProvider.get(), postDetailStoreProvider.get(), resourceProvider.get());
  }

  public static PostDayViewsUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostDayViewsMapper> postDayViewsMapperProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsPostProvider> statsPostProvider,
      Provider<PostDetailStore> postDetailStoreProvider,
      Provider<ResourceProvider> resourceProvider) {
    return new PostDayViewsUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, postDayViewsMapperProvider, statsDateFormatterProvider, selectedDateProvider, statsSiteProvider, statsPostProvider, postDetailStoreProvider, resourceProvider);
  }

  public static PostDayViewsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, PostDayViewsMapper postDayViewsMapper,
      StatsDateFormatter statsDateFormatter, SelectedDateProvider selectedDateProvider,
      StatsSiteProvider statsSiteProvider, StatsPostProvider statsPostProvider,
      PostDetailStore postDetailStore, ResourceProvider resourceProvider) {
    return new PostDayViewsUseCase(mainDispatcher, backgroundDispatcher, postDayViewsMapper, statsDateFormatter, selectedDateProvider, statsSiteProvider, statsPostProvider, postDetailStore, resourceProvider);
  }
}
