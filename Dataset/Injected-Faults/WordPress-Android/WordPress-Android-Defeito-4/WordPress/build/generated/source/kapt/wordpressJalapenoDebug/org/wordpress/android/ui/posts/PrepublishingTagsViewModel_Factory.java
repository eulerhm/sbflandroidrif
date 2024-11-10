package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class PrepublishingTagsViewModel_Factory implements Factory<PrepublishingTagsViewModel> {
  private final Provider<GetPostTagsUseCase> getPostTagsUseCaseProvider;

  private final Provider<UpdatePostTagsUseCase> updatePostTagsUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public PrepublishingTagsViewModel_Factory(Provider<GetPostTagsUseCase> getPostTagsUseCaseProvider,
      Provider<UpdatePostTagsUseCase> updatePostTagsUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.getPostTagsUseCaseProvider = getPostTagsUseCaseProvider;
    this.updatePostTagsUseCaseProvider = updatePostTagsUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public PrepublishingTagsViewModel get() {
    return newInstance(getPostTagsUseCaseProvider.get(), updatePostTagsUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static PrepublishingTagsViewModel_Factory create(
      Provider<GetPostTagsUseCase> getPostTagsUseCaseProvider,
      Provider<UpdatePostTagsUseCase> updatePostTagsUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new PrepublishingTagsViewModel_Factory(getPostTagsUseCaseProvider, updatePostTagsUseCaseProvider, bgDispatcherProvider);
  }

  public static PrepublishingTagsViewModel newInstance(GetPostTagsUseCase getPostTagsUseCase,
      UpdatePostTagsUseCase updatePostTagsUseCase, CoroutineDispatcher bgDispatcher) {
    return new PrepublishingTagsViewModel(getPostTagsUseCase, updatePostTagsUseCase, bgDispatcher);
  }
}
