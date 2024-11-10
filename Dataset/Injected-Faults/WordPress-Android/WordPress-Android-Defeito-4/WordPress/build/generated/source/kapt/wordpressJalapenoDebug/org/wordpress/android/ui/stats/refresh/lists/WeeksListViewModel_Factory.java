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
public final class WeeksListViewModel_Factory implements Factory<WeeksListViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BaseListUseCase> statsUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider;

  public WeeksListViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> statsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsUseCaseProvider = statsUseCaseProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.dateSelectorFactoryProvider = dateSelectorFactoryProvider;
  }

  @Override
  public WeeksListViewModel get() {
    return newInstance(mainDispatcherProvider.get(), statsUseCaseProvider.get(), analyticsTrackerProvider.get(), dateSelectorFactoryProvider.get());
  }

  public static WeeksListViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> statsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    return new WeeksListViewModel_Factory(mainDispatcherProvider, statsUseCaseProvider, analyticsTrackerProvider, dateSelectorFactoryProvider);
  }

  public static WeeksListViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BaseListUseCase statsUseCase, AnalyticsTrackerWrapper analyticsTracker,
      StatsDateSelector.Factory dateSelectorFactory) {
    return new WeeksListViewModel(mainDispatcher, statsUseCase, analyticsTracker, dateSelectorFactory);
  }
}
