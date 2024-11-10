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
public final class MonthsListViewModel_Factory implements Factory<MonthsListViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BaseListUseCase> statsUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider;

  public MonthsListViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> statsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsUseCaseProvider = statsUseCaseProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.dateSelectorFactoryProvider = dateSelectorFactoryProvider;
  }

  @Override
  public MonthsListViewModel get() {
    return newInstance(mainDispatcherProvider.get(), statsUseCaseProvider.get(), analyticsTrackerProvider.get(), dateSelectorFactoryProvider.get());
  }

  public static MonthsListViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> statsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    return new MonthsListViewModel_Factory(mainDispatcherProvider, statsUseCaseProvider, analyticsTrackerProvider, dateSelectorFactoryProvider);
  }

  public static MonthsListViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BaseListUseCase statsUseCase, AnalyticsTrackerWrapper analyticsTracker,
      StatsDateSelector.Factory dateSelectorFactory) {
    return new MonthsListViewModel(mainDispatcher, statsUseCase, analyticsTracker, dateSelectorFactory);
  }
}
