package org.wordpress.android.ui.stories.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.stories.StoryRepositoryWrapper;
import org.wordpress.android.ui.stories.prefs.StoriesPrefs;

@ScopeMetadata("dagger.Reusable")
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
public final class LoadStoryFromStoriesPrefsUseCase_Factory implements Factory<LoadStoryFromStoriesPrefsUseCase> {
  private final Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider;

  private final Provider<StoriesPrefs> storiesPrefsProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  public LoadStoryFromStoriesPrefsUseCase_Factory(
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<StoriesPrefs> storiesPrefsProvider, Provider<MediaStore> mediaStoreProvider) {
    this.storyRepositoryWrapperProvider = storyRepositoryWrapperProvider;
    this.storiesPrefsProvider = storiesPrefsProvider;
    this.mediaStoreProvider = mediaStoreProvider;
  }

  @Override
  public LoadStoryFromStoriesPrefsUseCase get() {
    return newInstance(storyRepositoryWrapperProvider.get(), storiesPrefsProvider.get(), mediaStoreProvider.get());
  }

  public static LoadStoryFromStoriesPrefsUseCase_Factory create(
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<StoriesPrefs> storiesPrefsProvider, Provider<MediaStore> mediaStoreProvider) {
    return new LoadStoryFromStoriesPrefsUseCase_Factory(storyRepositoryWrapperProvider, storiesPrefsProvider, mediaStoreProvider);
  }

  public static LoadStoryFromStoriesPrefsUseCase newInstance(
      StoryRepositoryWrapper storyRepositoryWrapper, StoriesPrefs storiesPrefs,
      MediaStore mediaStore) {
    return new LoadStoryFromStoriesPrefsUseCase(storyRepositoryWrapper, storiesPrefs, mediaStore);
  }
}
