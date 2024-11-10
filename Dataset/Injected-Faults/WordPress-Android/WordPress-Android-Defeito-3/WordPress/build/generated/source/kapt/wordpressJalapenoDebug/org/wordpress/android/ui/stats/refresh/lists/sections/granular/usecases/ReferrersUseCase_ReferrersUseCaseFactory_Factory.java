package org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.time.ReferrersStore;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.ReferrerPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
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
public final class ReferrersUseCase_ReferrersUseCaseFactory_Factory implements Factory<ReferrersUseCase.ReferrersUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<ReferrersStore> referrersStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<SelectedDateProvider> selectedDateProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ReferrerPopupMenuHandler> popupMenuHandlerProvider;

  private final Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider;

  public ReferrersUseCase_ReferrersUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<ReferrersStore> referrersStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider, Provider<ResourceProvider> resourceProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ReferrerPopupMenuHandler> popupMenuHandlerProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.referrersStoreProvider = referrersStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.selectedDateProvider = selectedDateProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.resourceProvider = resourceProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
    this.statsRevampV2FeatureConfigProvider = statsRevampV2FeatureConfigProvider;
  }

  @Override
  public ReferrersUseCase.ReferrersUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), referrersStoreProvider.get(), statsSiteProvider.get(), selectedDateProvider.get(), contentDescriptionHelperProvider.get(), statsUtilsProvider.get(), resourceProvider.get(), analyticsTrackerProvider.get(), popupMenuHandlerProvider.get(), statsRevampV2FeatureConfigProvider.get());
  }

  public static ReferrersUseCase_ReferrersUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<ReferrersStore> referrersStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<SelectedDateProvider> selectedDateProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider, Provider<ResourceProvider> resourceProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ReferrerPopupMenuHandler> popupMenuHandlerProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider) {
    return new ReferrersUseCase_ReferrersUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, referrersStoreProvider, statsSiteProvider, selectedDateProvider, contentDescriptionHelperProvider, statsUtilsProvider, resourceProvider, analyticsTrackerProvider, popupMenuHandlerProvider, statsRevampV2FeatureConfigProvider);
  }

  public static ReferrersUseCase.ReferrersUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      ReferrersStore referrersStore, StatsSiteProvider statsSiteProvider,
      SelectedDateProvider selectedDateProvider, ContentDescriptionHelper contentDescriptionHelper,
      StatsUtils statsUtils, ResourceProvider resourceProvider,
      AnalyticsTrackerWrapper analyticsTracker, ReferrerPopupMenuHandler popupMenuHandler,
      StatsRevampV2FeatureConfig statsRevampV2FeatureConfig) {
    return new ReferrersUseCase.ReferrersUseCaseFactory(mainDispatcher, backgroundDispatcher, referrersStore, statsSiteProvider, selectedDateProvider, contentDescriptionHelper, statsUtils, resourceProvider, analyticsTracker, popupMenuHandler, statsRevampV2FeatureConfig);
  }
}
