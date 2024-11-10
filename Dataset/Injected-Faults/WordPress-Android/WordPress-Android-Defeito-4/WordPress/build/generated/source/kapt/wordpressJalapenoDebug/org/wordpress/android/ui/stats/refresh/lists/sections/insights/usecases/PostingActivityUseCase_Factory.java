package org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.stats.insights.PostingActivityStore;
import org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;

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
public final class PostingActivityUseCase_Factory implements Factory<PostingActivityUseCase> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> backgroundDispatcherProvider;

  private final Provider<PostingActivityStore> storeProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<PostingActivityMapper> postingActivityMapperProvider;

  private final Provider<ItemPopupMenuHandler> popupMenuHandlerProvider;

  public PostingActivityUseCase_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostingActivityStore> storeProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<PostingActivityMapper> postingActivityMapperProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.backgroundDispatcherProvider = backgroundDispatcherProvider;
    this.storeProvider = storeProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.postingActivityMapperProvider = postingActivityMapperProvider;
    this.popupMenuHandlerProvider = popupMenuHandlerProvider;
  }

  @Override
  public PostingActivityUseCase get() {
    return newInstance(mainDispatcherProvider.get(), backgroundDispatcherProvider.get(), storeProvider.get(), statsSiteProvider.get(), postingActivityMapperProvider.get(), popupMenuHandlerProvider.get());
  }

  public static PostingActivityUseCase_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> backgroundDispatcherProvider,
      Provider<PostingActivityStore> storeProvider, Provider<StatsSiteProvider> statsSiteProvider,
      Provider<PostingActivityMapper> postingActivityMapperProvider,
      Provider<ItemPopupMenuHandler> popupMenuHandlerProvider) {
    return new PostingActivityUseCase_Factory(mainDispatcherProvider, backgroundDispatcherProvider, storeProvider, statsSiteProvider, postingActivityMapperProvider, popupMenuHandlerProvider);
  }

  public static PostingActivityUseCase newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher backgroundDispatcher, PostingActivityStore store,
      StatsSiteProvider statsSiteProvider, PostingActivityMapper postingActivityMapper,
      ItemPopupMenuHandler popupMenuHandler) {
    return new PostingActivityUseCase(mainDispatcher, backgroundDispatcher, store, statsSiteProvider, postingActivityMapper, popupMenuHandler);
  }
}
