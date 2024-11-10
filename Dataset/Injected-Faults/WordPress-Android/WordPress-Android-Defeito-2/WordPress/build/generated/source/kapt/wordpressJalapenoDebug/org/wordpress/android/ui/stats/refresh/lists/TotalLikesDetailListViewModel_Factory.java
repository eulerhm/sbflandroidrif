package org.wordpress.android.ui.stats.refresh.lists;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector;
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
public final class TotalLikesDetailListViewModel_Factory implements Factory<TotalLikesDetailListViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BaseListUseCase> statsUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider;

  public TotalLikesDetailListViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> statsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsUseCaseProvider = statsUseCaseProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.dateSelectorFactoryProvider = dateSelectorFactoryProvider;
  }

  @Override
  public TotalLikesDetailListViewModel get() {
    return newInstance(mainDispatcherProvider.get(), statsUseCaseProvider.get(), analyticsTrackerProvider.get(), dateSelectorFactoryProvider.get());
  }

  public static TotalLikesDetailListViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> statsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    return new TotalLikesDetailListViewModel_Factory(mainDispatcherProvider, statsUseCaseProvider, analyticsTrackerProvider, dateSelectorFactoryProvider);
  }

  public static TotalLikesDetailListViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BaseListUseCase statsUseCase, AnalyticsTrackerWrapper analyticsTracker,
      StatsDateSelector.Factory dateSelectorFactory) {
    return new TotalLikesDetailListViewModel(mainDispatcher, statsUseCase, analyticsTracker, dateSelectorFactory);
  }
}
