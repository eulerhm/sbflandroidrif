package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.stats.insights.MostPopularInsightsStore;
import org.wordpress.android.ui.stats.refresh.utils.ActionCardHandler;
import org.wordpress.android.ui.stats.refresh.utils.DateUtils;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
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
public final class MostPopularInsightsUseCase_Factory implements Factory<MostPopularInsightsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<MostPopularInsightsStore> mostPopularStoreProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<DateUtils> dateUtilsProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  private final Provider<ActionCardHandler> actionCardHandlerProvider;

  public MostPopularInsightsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<MostPopularInsightsStore> mostPopularStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<DateUtils> dateUtilsProvider, Provider<ResourceProvider> resourceProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.mostPopularStoreProvider = mostPopularStoreProvider;
    this.postStoreProvider = postStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.dateUtilsProvider = dateUtilsProvider;
    this.resourceProvider = resourceProvider;
    this.statsRevampV2FeatureConfigProvider = statsRevampV2FeatureConfigProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
    this.actionCardHandlerProvider = actionCardHandlerProvider;
  }

  @Override
  public MostPopularInsightsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), mostPopularStoreProvider.get(), postStoreProvider.get(), statsSiteProvider.get(), dateUtilsProvider.get(), resourceProvider.get(), statsRevampV2FeatureConfigProvider.get(), popupMenuHandlerProvider.get(), actionCardHandlerProvider.get());
  }

  public static MostPopularInsightsUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<MostPopularInsightsStore> mostPopularStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<DateUtils> dateUtilsProvider, Provider<ResourceProvider> resourceProvider,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<ActionCardHandler> actionCardHandlerProvider) {
    return new MostPopularInsightsUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, mostPopularStoreProvider, postStoreProvider, statsSiteProvider, dateUtilsProvider, resourceProvider, statsRevampV2FeatureConfigProvider, popupMenuHandlerProvider, actionCardHandlerProvider);
  }

  public static MostPopularInsightsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, MostPopularInsightsStore mostPopularStore,
      PostStore postStore, StatsSiteProvider statsSiteProvider, DateUtils dateUtils,
      ResourceProvider resourceProvider, StatsRevampV2FeatureConfig statsRevampV2FeatureConfig,
      ItemPopupMenuHandler popupMenuHandler, ActionCardHandler actionCardHandler) {
    return new MostPopularInsightsUseCase(mainDispatcher, backgroundDispatcher, mostPopularStore, postStore, statsSiteProvider, dateUtils, resourceProvider, statsRevampV2FeatureConfig, popupMenuHandler, actionCardHandler);
  }
}
