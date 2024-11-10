package org.wordpress.android.ui.comments.unified;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.model.comments.CommentsMapper;
import org.wordpress.android.fluxc.store.CommentsStore;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CommentsStoreAdapter_Factory implements Factory<CommentsStoreAdapter> {
  private final Provider<CommentsStore> unifiedStoreProvider;

  private final Provider<CommentsMapper> commentsMapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public CommentsStoreAdapter_Factory(Provider<CommentsStore> unifiedStoreProvider,
      Provider<CommentsMapper> commentsMapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider, Provider<Dispatcher> dispatcherProvider) {
    this.unifiedStoreProvider = unifiedStoreProvider;
    this.commentsMapperProvider = commentsMapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public CommentsStoreAdapter get() {
    return newInstance(unifiedStoreProvider.get(), commentsMapperProvider.get(), mainDispatcherProvider.get(), bgDispatcherProvider.get(), dispatcherProvider.get());
  }

  public static CommentsStoreAdapter_Factory create(Provider<CommentsStore> unifiedStoreProvider,
      Provider<CommentsMapper> commentsMapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider, Provider<Dispatcher> dispatcherProvider) {
    return new CommentsStoreAdapter_Factory(unifiedStoreProvider, commentsMapperProvider, mainDispatcherProvider, bgDispatcherProvider, dispatcherProvider);
  }

  public static CommentsStoreAdapter newInstance(CommentsStore unifiedStore,
      CommentsMapper commentsMapper, CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, Dispatcher dispatcher) {
    return new CommentsStoreAdapter(unifiedStore, commentsMapper, mainDispatcher, bgDispatcher, dispatcher);
  }
}
