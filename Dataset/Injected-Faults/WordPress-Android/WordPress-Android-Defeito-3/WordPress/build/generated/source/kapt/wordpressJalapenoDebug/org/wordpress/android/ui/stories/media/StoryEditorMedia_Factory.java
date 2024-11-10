package org.wordpress.android.ui.stories.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.posts.editor.media.AddExistingMediaToPostUseCase;
import org.wordpress.android.ui.posts.editor.media.AddLocalMediaToPostUseCase;
import org.wordpress.android.util.MediaUtilsWrapper;

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
public final class StoryEditorMedia_Factory implements Factory<StoryEditorMedia> {
  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider;

  private final Provider<AddExistingMediaToPostUseCase> addExistingMediaToPostUseCaseProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public StoryEditorMedia_Factory(Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider,
      Provider<AddExistingMediaToPostUseCase> addExistingMediaToPostUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.addLocalMediaToPostUseCaseProvider = addLocalMediaToPostUseCaseProvider;
    this.addExistingMediaToPostUseCaseProvider = addExistingMediaToPostUseCaseProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public StoryEditorMedia get() {
    return newInstance(mediaUtilsWrapperProvider.get(), addLocalMediaToPostUseCaseProvider.get(), addExistingMediaToPostUseCaseProvider.get(), mainDispatcherProvider.get());
  }

  public static StoryEditorMedia_Factory create(
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider,
      Provider<AddExistingMediaToPostUseCase> addExistingMediaToPostUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new StoryEditorMedia_Factory(mediaUtilsWrapperProvider, addLocalMediaToPostUseCaseProvider, addExistingMediaToPostUseCaseProvider, mainDispatcherProvider);
  }

  public static StoryEditorMedia newInstance(MediaUtilsWrapper mediaUtilsWrapper,
      AddLocalMediaToPostUseCase addLocalMediaToPostUseCase,
      AddExistingMediaToPostUseCase addExistingMediaToPostUseCase,
      CoroutineDispatcher mainDispatcher) {
    return new StoryEditorMedia(mediaUtilsWrapper, addLocalMediaToPostUseCase, addExistingMediaToPostUseCase, mainDispatcher);
  }
}
