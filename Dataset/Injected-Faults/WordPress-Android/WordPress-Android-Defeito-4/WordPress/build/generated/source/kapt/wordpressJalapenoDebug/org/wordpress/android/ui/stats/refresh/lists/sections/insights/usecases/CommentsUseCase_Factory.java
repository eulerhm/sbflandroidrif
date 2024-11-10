package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.CommentsStore;
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
public final class CommentsUseCase_Factory implements Factory<CommentsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<CommentsStore> commentsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public CommentsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.commentsStoreProvider = commentsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public CommentsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), commentsStoreProvider.get(), statsSiteProvider.get(), popupMenuHandlerProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static CommentsUseCase_Factory create(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider,
      Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new CommentsUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, commentsStoreProvider, statsSiteProvider, popupMenuHandlerProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static CommentsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, CommentsStore commentsStore,
      StatsSiteProvider statsSiteProvider, ItemPopupMenuHandler popupMenuHandler,
      StatsUtils statsUtils, ContentDescriptionHelper contentDescriptionHelper) {
    return new CommentsUseCase(mainDispatcher, backgroundDispatcher, commentsStore, statsSiteProvider, popupMenuHandler, statsUtils, contentDescriptionHelper);
  }
}
