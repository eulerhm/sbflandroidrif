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
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsUtils;
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
public final class PostsCommentsUseCase_Factory implements Factory<PostsCommentsUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<CommentsStore> commentsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsUtils> statsUtilsProvider;

  private final Provider<ContentDescriptionHelper> contentDescriptionHelperProvider;

  public PostsCommentsUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.commentsStoreProvider = commentsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.resourceProvider = resourceProvider;
    this.statsUtilsProvider = statsUtilsProvider;
    this.contentDescriptionHelperProvider = contentDescriptionHelperProvider;
  }

  @Override
  public PostsCommentsUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), commentsStoreProvider.get(), statsSiteProvider.get(), resourceProvider.get(), statsUtilsProvider.get(), contentDescriptionHelperProvider.get());
  }

  public static PostsCommentsUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<CommentsStore> commentsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<ResourceProvider> resourceProvider, Provider<StatsUtils> statsUtilsProvider,
      Provider<ContentDescriptionHelper> contentDescriptionHelperProvider) {
    return new PostsCommentsUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, commentsStoreProvider, statsSiteProvider, resourceProvider, statsUtilsProvider, contentDescriptionHelperProvider);
  }

  public static PostsCommentsUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, CommentsStore commentsStore,
      StatsSiteProvider statsSiteProvider, ResourceProvider resourceProvider, StatsUtils statsUtils,
      ContentDescriptionHelper contentDescriptionHelper) {
    return new PostsCommentsUseCase(mainDispatcher, backgroundDispatcher, commentsStore, statsSiteProvider, resourceProvider, statsUtils, contentDescriptionHelper);
  }
}
