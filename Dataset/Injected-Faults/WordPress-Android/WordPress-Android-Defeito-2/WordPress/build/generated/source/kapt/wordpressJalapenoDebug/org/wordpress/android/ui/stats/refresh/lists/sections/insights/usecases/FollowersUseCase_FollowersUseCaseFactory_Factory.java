package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.FollowersStore;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSinceLabelFormatter;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.StatsRevampV2FeatureConfig;
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
public final class FollowersUseCase_FollowersUseCaseFactory_Factory implements Factory<FollowersUseCase.FollowersUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<FollowersStore> followersStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsSinceLabelFormatter> statsSinceLabelFormatterProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider;

  public FollowersUseCase_FollowersUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<FollowersStore> followersStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsSinceLabelFormatter> statsSinceLabelFormatterProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.followersStoreProvider = followersStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsSinceLabelFormatterProvider = statsSinceLabelFormatterProvider;
    this.resourceProvider = resourceProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsRevampV2FeatureConfigProvider = statsRevampV2FeatureConfigProvider;
  }

  @Override
  public FollowersUseCase.FollowersUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), followersStoreProvider.get(), statsSiteProvider.get(), statsSinceLabelFormatterProvider.get(), resourceProvider.get(), popupMenuHandlerProvider.get(), analyticsTrackerProvider.get(), contentDescriptionHelperProvider.get(), statsRevampV2FeatureConfigProvider.get());
  }

  public static FollowersUseCase_FollowersUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<FollowersStore> followersStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsSinceLabelFormatter> statsSinceLabelFormatterProvider,
      Provider<ResourceProvider> resourceProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider) {
    return new FollowersUseCase_FollowersUseCaseFactory_Factory(mainDispatcherProvider, bgDispatcherProvider, followersStoreProvider, statsSiteProvider, statsSinceLabelFormatterProvider, resourceProvider, popupMenuHandlerProvider, analyticsTrackerProvider, contentDescriptionHelperProvider, statsRevampV2FeatureConfigProvider);
  }

  public static FollowersUseCase.FollowersUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher bgDispatcher,
      FollowersStore followersStore, StatsSiteProvider statsSiteProvider,
      StatsSinceLabelFormatter statsSinceLabelFormatter, ResourceProvider resourceProvider,
      ItemPopupMenuHandler popupMenuHandler, AnalyticsTrackerWrapper analyticsTracker,
      ContentDescriptionHelper contentDescriptionHelper,
      StatsRevampV2FeatureConfig statsRevampV2FeatureConfig) {
    return new FollowersUseCase.FollowersUseCaseFactory(mainDispatcher, bgDispatcher, followersStore, statsSiteProvider, statsSinceLabelFormatter, resourceProvider, popupMenuHandler, analyticsTracker, contentDescriptionHelper, statsRevampV2FeatureConfig);
  }
}
