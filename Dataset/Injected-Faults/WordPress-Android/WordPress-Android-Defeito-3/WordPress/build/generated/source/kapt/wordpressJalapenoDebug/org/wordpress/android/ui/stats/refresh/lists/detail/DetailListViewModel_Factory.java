package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase;
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
public final class DetailListViewModel_Factory implements Factory<DetailListViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BaseListUseCase> detailUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider;

  public DetailListViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> detailUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.detailUseCaseProvider = detailUseCaseProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.dateSelectorFactoryProvider = dateSelectorFactoryProvider;
  }

  @Override
  public DetailListViewModel get() {
    return newInstance(mainDispatcherProvider.get(), detailUseCaseProvider.get(), analyticsTrackerProvider.get(), dateSelectorFactoryProvider.get());
  }

  public static DetailListViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> detailUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    return new DetailListViewModel_Factory(mainDispatcherProvider, detailUseCaseProvider, analyticsTrackerProvider, dateSelectorFactoryProvider);
  }

  public static DetailListViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BaseListUseCase detailUseCase, AnalyticsTrackerWrapper analyticsTracker,
      StatsDateSelector.Factory dateSelectorFactory) {
    return new DetailListViewModel(mainDispatcher, detailUseCase, analyticsTracker, dateSelectorFactory);
  }
}
