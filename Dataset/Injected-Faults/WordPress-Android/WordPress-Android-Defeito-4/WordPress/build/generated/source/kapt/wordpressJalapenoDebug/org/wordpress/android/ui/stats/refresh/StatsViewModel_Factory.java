package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.StatsStore;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase;
import org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.utils.NewsCardHandler;
import org.wordpress.android.ui.stats.refresh.utils.SelectedSectionManager;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig;
import org.wordpress.android.util.config.StatsRevampV2FeatureConfig;

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
public final class StatsViewModel_Factory implements Factory<StatsViewModel> {
  private final Provider<Map<StatsListViewModel.StatsSection, BaseListUseCase>> listUseCasesProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> defaultDispatcherProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<SelectedSectionManager> statsSectionManagerProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsStore> statsStoreProvider;

  private final Provider<NewsCardHandler> newsCardHandlerProvider;

  private final Provider<StatsModuleActivateUseCase> statsModuleActivateUseCaseProvider;

  private final Provider<SystemNotificationsTracker> notificationsTrackerProvider;

  private final Provider<MySiteDashboardTodaysStatsCardFeatureConfig> todaysStatsCardFeatureConfigProvider;

  private final Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider;

  public StatsViewModel_Factory(
      Provider<Map<StatsListViewModel.StatsSection, BaseListUseCase>> listUseCasesProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<SelectedSectionManager> statsSectionManagerProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsStore> statsStoreProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<StatsModuleActivateUseCase> statsModuleActivateUseCaseProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider,
      Provider<MySiteDashboardTodaysStatsCardFeatureConfig> todaysStatsCardFeatureConfigProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider) {
    this.listUseCasesProvider = listUseCasesProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.defaultDispatcherProvider = defaultDispatcherProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.statsSectionManagerProvider = statsSectionManagerProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsStoreProvider = statsStoreProvider;
    this.newsCardHandlerProvider = newsCardHandlerProvider;
    this.statsModuleActivateUseCaseProvider = statsModuleActivateUseCaseProvider;
    this.notificationsTrackerProvider = notificationsTrackerProvider;
    this.todaysStatsCardFeatureConfigProvider = todaysStatsCardFeatureConfigProvider;
    this.statsRevampV2FeatureConfigProvider = statsRevampV2FeatureConfigProvider;
  }

  @Override
  public StatsViewModel get() {
    return newInstance(listUseCasesProvider.get(), mainDispatcherProvider.get(), defaultDispatcherProvider.get(), selectedDateProvider.get(), statsSectionManagerProvider.get(), appPrefsWrapperProvider.get(), analyticsTrackerProvider.get(), networkUtilsWrapperProvider.get(), statsSiteProvider.get(), statsStoreProvider.get(), newsCardHandlerProvider.get(), statsModuleActivateUseCaseProvider.get(), notificationsTrackerProvider.get(), todaysStatsCardFeatureConfigProvider.get(), statsRevampV2FeatureConfigProvider.get());
  }

  public static StatsViewModel_Factory create(
      Provider<Map<StatsListViewModel.StatsSection, BaseListUseCase>> listUseCasesProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<SelectedSectionManager> statsSectionManagerProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsStore> statsStoreProvider,
      Provider<NewsCardHandler> newsCardHandlerProvider,
      Provider<StatsModuleActivateUseCase> statsModuleActivateUseCaseProvider,
      Provider<SystemNotificationsTracker> notificationsTrackerProvider,
      Provider<MySiteDashboardTodaysStatsCardFeatureConfig> todaysStatsCardFeatureConfigProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider) {
    return new StatsViewModel_Factory(listUseCasesProvider, mainDispatcherProvider, defaultDispatcherProvider, selectedDateProvider, statsSectionManagerProvider, appPrefsWrapperProvider, analyticsTrackerProvider, networkUtilsWrapperProvider, statsSiteProvider, statsStoreProvider, newsCardHandlerProvider, statsModuleActivateUseCaseProvider, notificationsTrackerProvider, todaysStatsCardFeatureConfigProvider, statsRevampV2FeatureConfigProvider);
  }

  public static StatsViewModel newInstance(
      Map<StatsListViewModel.StatsSection, BaseListUseCase> listUseCases,
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher defaultDispatcher,
      SelectedDateProvider selectedDateProvider, SelectedSectionManager statsSectionManager,
      AppPrefsWrapper appPrefsWrapper, AnalyticsTrackerWrapper analyticsTracker,
      NetworkUtilsWrapper networkUtilsWrapper, StatsSiteProvider statsSiteProvider,
      StatsStore statsStore, NewsCardHandler newsCardHandler,
      StatsModuleActivateUseCase statsModuleActivateUseCase,
      SystemNotificationsTracker notificationsTracker,
      MySiteDashboardTodaysStatsCardFeatureConfig todaysStatsCardFeatureConfig,
      StatsRevampV2FeatureConfig statsRevampV2FeatureConfig) {
    return new StatsViewModel(listUseCases, mainDispatcher, defaultDispatcher, selectedDateProvider, statsSectionManager, appPrefsWrapper, analyticsTracker, networkUtilsWrapper, statsSiteProvider, statsStore, newsCardHandler, statsModuleActivateUseCase, notificationsTracker, todaysStatsCardFeatureConfig, statsRevampV2FeatureConfig);
  }
}
