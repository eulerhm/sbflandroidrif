package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.TagsStore;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
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
public final class TagsAndCategoriesUseCase_TagsAndCategoriesUseCaseFactory_Factory implements Factory<TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<TagsStore> tagsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  public TagsAndCategoriesUseCase_TagsAndCategoriesUseCaseFactory_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<TagsStore> tagsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.tagsStoreProvider = tagsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.resourceProvider = resourceProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
  }

  @Override
  public TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), tagsStoreProvider.get(), statsSiteProvider.get(), resourceProvider.get(), statsUtilsProvider.get(), analyticsTrackerProvider.get(), contentDescriptionHelperProvider.get(), popupMenuHandlerProvider.get());
  }

  public static TagsAndCategoriesUseCase_TagsAndCategoriesUseCaseFactory_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<TagsStore> tagsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    return new TagsAndCategoriesUseCase_TagsAndCategoriesUseCaseFactory_Factory(mainDispatcherProvider, backgroundDispatcherProvider, tagsStoreProvider, statsSiteProvider, resourceProvider, statsUtilsProvider, analyticsTrackerProvider, contentDescriptionHelperProvider, popupMenuHandlerProvider);
  }

  public static TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory newInstance(
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher backgroundDispatcher,
      TagsStore tagsStore, StatsSiteProvider statsSiteProvider, ResourceProvider resourceProvider,
      StatsUtils statsUtils, AnalyticsTrackerWrapper analyticsTracker,
      ContentDescriptionHelper contentDescriptionHelper, ItemPopupMenuHandler popupMenuHandler) {
    return new TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory(mainDispatcher, backgroundDispatcher, tagsStore, statsSiteProvider, resourceProvider, statsUtils, analyticsTracker, contentDescriptionHelper, popupMenuHandler);
  }
}
