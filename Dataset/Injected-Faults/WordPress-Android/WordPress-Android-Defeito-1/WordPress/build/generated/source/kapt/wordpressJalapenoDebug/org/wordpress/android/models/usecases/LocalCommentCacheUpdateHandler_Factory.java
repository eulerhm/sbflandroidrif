package org.wordpress.android.models.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LocalCommentCacheUpdateHandler_Factory implements Factory<LocalCommentCacheUpdateHandler> {
  private final Provider<LocalCommentCacheUpdateUseCase> localCommentCacheUpdateUseCaseProvider;

  public LocalCommentCacheUpdateHandler_Factory(
      Provider<LocalCommentCacheUpdateUseCase> localCommentCacheUpdateUseCaseProvider) {
    this.localCommentCacheUpdateUseCaseProvider = localCommentCacheUpdateUseCaseProvider;
  }

  @Override
  public LocalCommentCacheUpdateHandler get() {
    return newInstance(localCommentCacheUpdateUseCaseProvider.get());
  }

  public static LocalCommentCacheUpdateHandler_Factory create(
      Provider<LocalCommentCacheUpdateUseCase> localCommentCacheUpdateUseCaseProvider) {
    return new LocalCommentCacheUpdateHandler_Factory(localCommentCacheUpdateUseCaseProvider);
  }

  public static LocalCommentCacheUpdateHandler newInstance(
      LocalCommentCacheUpdateUseCase localCommentCacheUpdateUseCase) {
    return new LocalCommentCacheUpdateHandler(localCommentCacheUpdateUseCase);
  }
}
