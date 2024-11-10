package org.wordpress.android.models.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.CommentsStore;

@ScopeMetadata("dagger.Reusable")
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
public final class ModerateCommentsResourceProvider_Factory implements Factory<ModerateCommentsResourceProvider> {
  private final Provider<CommentsStore> commentsStoreProvider;

  private final Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ModerateCommentsResourceProvider_Factory(Provider<CommentsStore> commentsStoreProvider,
      Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.commentsStoreProvider = commentsStoreProvider;
    this.localCommentCacheUpdateHandlerProvider = localCommentCacheUpdateHandlerProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ModerateCommentsResourceProvider get() {
    return newInstance(commentsStoreProvider.get(), localCommentCacheUpdateHandlerProvider.get(), bgDispatcherProvider.get());
  }

  public static ModerateCommentsResourceProvider_Factory create(
      Provider<CommentsStore> commentsStoreProvider,
      Provider<LocalCommentCacheUpdateHandler> localCommentCacheUpdateHandlerProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ModerateCommentsResourceProvider_Factory(commentsStoreProvider, localCommentCacheUpdateHandlerProvider, bgDispatcherProvider);
  }

  public static ModerateCommentsResourceProvider newInstance(CommentsStore commentsStore,
      LocalCommentCacheUpdateHandler localCommentCacheUpdateHandler,
      CoroutineDispatcher bgDispatcher) {
    return new ModerateCommentsResourceProvider(commentsStore, localCommentCacheUpdateHandler, bgDispatcher);
  }
}
