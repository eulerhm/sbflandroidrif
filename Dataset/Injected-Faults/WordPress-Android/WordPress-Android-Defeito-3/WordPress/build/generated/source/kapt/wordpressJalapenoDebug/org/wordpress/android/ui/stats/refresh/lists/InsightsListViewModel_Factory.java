package org.wordpress.android.ui.stats.refresh.lists;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.utils.ActionCardHandler;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.NewsCardHandler;
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
public final class InsightsListViewModel_Factory implements Factory<InsightsListViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BaseListUseCase> insightsUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  private final Provider<NewsCardHandler> newsCardHandlerProvider;

  private final Provider<ActionCardHandler> actionCardHandlerProvider;

  public InsightsListViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> insightsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.insightsUseCaseProvider = insightsUseCaseProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.dateSelectorFactoryProvider = dateSelectorFactoryProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
    this.newsCardHandlerProvider = newsCardHandlerProvider;
    this.actionCardHandlerProvider = actionCardHandlerProvider;
  }

  @Override
  public InsightsListViewModel get() {
    return newInstance(mainDispatcherProvider.get(), insightsUseCaseProvider.get(), analyticsTrackerProvider.get(), dateSelectorFactoryProvider.get(), popupMenuHandlerProvider.get(), newsCardHandlerProvider.get(), actionCardHandlerProvider.get());
  }

  public static InsightsListViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> insightsUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider) {
    return new InsightsListViewModel_Factory(mainDispatcherProvider, insightsUseCaseProvider, analyticsTrackerProvider, dateSelectorFactoryProvider, popupMenuHandlerProvider, newsCardHandlerProvider, actionCardHandlerProvider);
  }

  public static InsightsListViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BaseListUseCase insightsUseCase, AnalyticsTrackerWrapper analyticsTracker,
      StatsDateSelector.Factory dateSelectorFactory, ItemPopupMenuHandler popupMenuHandler,
      NewsCardHandler newsCardHandler, ActionCardHandler actionCardHandler) {
    return new InsightsListViewModel(mainDispatcher, insightsUseCase, analyticsTracker, dateSelectorFactory, popupMenuHandler, newsCardHandler, actionCardHandler);
  }
}
