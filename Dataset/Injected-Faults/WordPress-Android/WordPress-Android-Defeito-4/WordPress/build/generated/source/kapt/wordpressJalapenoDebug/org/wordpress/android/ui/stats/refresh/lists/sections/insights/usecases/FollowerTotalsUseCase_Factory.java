package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.FollowersStore;
import org.wordpress.android.fluxc.store.stats.insights.PublicizeStore;
import org.wordpress.android.ui.stats.refresh.utils.ContentDescriptionHelper;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;

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
public final class FollowerTotalsUseCase_Factory implements Factory<FollowerTotalsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<FollowersStore> followersStoreProvider;

  private final Provider<PublicizeStore> publicizeStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  public FollowerTotalsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<FollowersStore> followersStoreProvider,
      Provider<PublicizeStore> publicizeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.followersStoreProvider = followersStoreProvider;
    this.publicizeStoreProvider = publicizeStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
  }

  @Override
  public FollowerTotalsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), followersStoreProvider.get(), publicizeStoreProvider.get(), statsSiteProvider.get(), contentDescriptionHelperProvider.get(), statsUtilsProvider.get(), popupMenuHandlerProvider.get());
  }

  public static FollowerTotalsUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<FollowersStore> followersStoreProvider,
      Provider<PublicizeStore> publicizeStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    return new FollowerTotalsUseCase_Factory(mainDispatcherProvider, bgDispatcherProvider, followersStoreProvider, publicizeStoreProvider, statsSiteProvider, contentDescriptionHelperProvider, statsUtilsProvider, popupMenuHandlerProvider);
  }

  public static FollowerTotalsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, FollowersStore followersStore,
      PublicizeStore publicizeStore, StatsSiteProvider statsSiteProvider,
      ContentDescriptionHelper contentDescriptionHelper, StatsUtils statsUtils,
      ItemPopupMenuHandler popupMenuHandler) {
    return new FollowerTotalsUseCase(mainDispatcher, bgDispatcher, followersStore, publicizeStore, statsSiteProvider, contentDescriptionHelper, statsUtils, popupMenuHandler);
  }
}
