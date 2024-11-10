package org.wordpress.android.ui.posts.editor;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.posts.editor.media.EditorMedia;
import org.wordpress.android.ui.stories.StoryRepositoryWrapper;
import org.wordpress.android.ui.stories.prefs.StoriesPrefs;
import org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase;
import org.wordpress.android.util.EventBusWrapper;

@ScopeMetadata
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
public final class StoriesEventListener_Factory implements Factory<StoriesEventListener> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<EditorMedia> editorMediaProvider;

  private final Provider<LoadStoryFromStoriesPrefsUseCase> loadStoryFromStoriesPrefsUseCaseProvider;

  private final Provider<StoriesPrefs> storiesPrefsProvider;

  private final Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider;

  public StoriesEventListener_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<EditorMedia> editorMediaProvider,
      Provider<LoadStoryFromStoriesPrefsUseCase> loadStoryFromStoriesPrefsUseCaseProvider,
      Provider<StoriesPrefs> storiesPrefsProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.editorMediaProvider = editorMediaProvider;
    this.loadStoryFromStoriesPrefsUseCaseProvider = loadStoryFromStoriesPrefsUseCaseProvider;
    this.storiesPrefsProvider = storiesPrefsProvider;
    this.storyRepositoryWrapperProvider = storyRepositoryWrapperProvider;
  }

  @Override
  public StoriesEventListener get() {
    return newInstance(dispatcherProvider.get(), mediaStoreProvider.get(), eventBusWrapperProvider.get(), editorMediaProvider.get(), loadStoryFromStoriesPrefsUseCaseProvider.get(), storiesPrefsProvider.get(), storyRepositoryWrapperProvider.get());
  }

  public static StoriesEventListener_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<EditorMedia> editorMediaProvider,
      Provider<LoadStoryFromStoriesPrefsUseCase> loadStoryFromStoriesPrefsUseCaseProvider,
      Provider<StoriesPrefs> storiesPrefsProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider) {
    return new StoriesEventListener_Factory(dispatcherProvider, mediaStoreProvider, eventBusWrapperProvider, editorMediaProvider, loadStoryFromStoriesPrefsUseCaseProvider, storiesPrefsProvider, storyRepositoryWrapperProvider);
  }

  public static StoriesEventListener newInstance(Dispatcher dispatcher, MediaStore mediaStore,
      EventBusWrapper eventBusWrapper, EditorMedia editorMedia,
      LoadStoryFromStoriesPrefsUseCase loadStoryFromStoriesPrefsUseCase, StoriesPrefs storiesPrefs,
      StoryRepositoryWrapper storyRepositoryWrapper) {
    return new StoriesEventListener(dispatcher, mediaStore, eventBusWrapper, editorMedia, loadStoryFromStoriesPrefsUseCase, storiesPrefs, storyRepositoryWrapper);
  }
}
