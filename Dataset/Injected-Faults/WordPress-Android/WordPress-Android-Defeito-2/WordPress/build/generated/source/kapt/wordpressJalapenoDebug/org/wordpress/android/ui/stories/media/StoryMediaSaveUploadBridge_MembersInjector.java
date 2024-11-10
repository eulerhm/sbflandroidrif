package org.wordpress.android.ui.stories.media;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.posts.EditPostRepository;
import org.wordpress.android.ui.stories.SaveStoryGutenbergBlockUseCase;
import org.wordpress.android.ui.stories.StoriesTrackerHelper;

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
public final class StoryMediaSaveUploadBridge_MembersInjector implements MembersInjector<StoryMediaSaveUploadBridge> {
  private final Provider<EditPostRepository> editPostRepositoryProvider;

  private final Provider<StoriesTrackerHelper> storiesTrackerHelperProvider;

  private final Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider;

  public StoryMediaSaveUploadBridge_MembersInjector(
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<StoriesTrackerHelper> storiesTrackerHelperProvider,
      Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider) {
    this.editPostRepositoryProvider = editPostRepositoryProvider;
    this.storiesTrackerHelperProvider = storiesTrackerHelperProvider;
    this.saveStoryGutenbergBlockUseCaseProvider = saveStoryGutenbergBlockUseCaseProvider;
  }

  public static MembersInjector<StoryMediaSaveUploadBridge> create(
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<StoriesTrackerHelper> storiesTrackerHelperProvider,
      Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider) {
    return new StoryMediaSaveUploadBridge_MembersInjector(editPostRepositoryProvider, storiesTrackerHelperProvider, saveStoryGutenbergBlockUseCaseProvider);
  }

  @Override
  public void injectMembers(StoryMediaSaveUploadBridge instance) {
    injectEditPostRepository(instance, editPostRepositoryProvider.get());
    injectStoriesTrackerHelper(instance, storiesTrackerHelperProvider.get());
    injectSaveStoryGutenbergBlockUseCase(instance, saveStoryGutenbergBlockUseCaseProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge.editPostRepository")
  public static void injectEditPostRepository(StoryMediaSaveUploadBridge instance,
      EditPostRepository editPostRepository) {
    instance.editPostRepository = editPostRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge.storiesTrackerHelper")
  public static void injectStoriesTrackerHelper(StoryMediaSaveUploadBridge instance,
      StoriesTrackerHelper storiesTrackerHelper) {
    instance.storiesTrackerHelper = storiesTrackerHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge.saveStoryGutenbergBlockUseCase")
  public static void injectSaveStoryGutenbergBlockUseCase(StoryMediaSaveUploadBridge instance,
      SaveStoryGutenbergBlockUseCase saveStoryGutenbergBlockUseCase) {
    instance.saveStoryGutenbergBlockUseCase = saveStoryGutenbergBlockUseCase;
  }
}
