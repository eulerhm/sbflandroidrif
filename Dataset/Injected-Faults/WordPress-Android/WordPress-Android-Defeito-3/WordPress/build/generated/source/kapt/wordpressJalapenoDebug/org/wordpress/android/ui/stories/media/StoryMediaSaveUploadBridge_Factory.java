package org.wordpress.android.ui.stories.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.posts.EditPostRepository;
import org.wordpress.android.ui.posts.PostUtilsWrapper;
import org.wordpress.android.ui.posts.SavePostToDbUseCase;
import org.wordpress.android.ui.posts.editor.media.AddLocalMediaToPostUseCase;
import org.wordpress.android.ui.stories.SaveStoryGutenbergBlockUseCase;
import org.wordpress.android.ui.stories.StoriesTrackerHelper;
import org.wordpress.android.ui.stories.StoryRepositoryWrapper;
import org.wordpress.android.ui.stories.prefs.StoriesPrefs;
import org.wordpress.android.ui.uploads.UploadServiceFacade;
import org.wordpress.android.util.EventBusWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class StoryMediaSaveUploadBridge_Factory implements Factory<StoryMediaSaveUploadBridge> {
  private final Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider;

  private final Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider;

  private final Provider<StoriesPrefs> storiesPrefsProvider;

  private final Provider<UploadServiceFacade> uploadServiceProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<PostUtilsWrapper> postUtilsProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<EditPostRepository> editPostRepositoryProvider;

  private final Provider<StoriesTrackerHelper> storiesTrackerHelperProvider;

  private final Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider;

  public StoryMediaSaveUploadBridge_Factory(
      Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<StoriesPrefs> storiesPrefsProvider,
      Provider<UploadServiceFacade> uploadServiceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<PostUtilsWrapper> postUtilsProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<StoriesTrackerHelper> storiesTrackerHelperProvider,
      Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider) {
    this.addLocalMediaToPostUseCaseProvider = addLocalMediaToPostUseCaseProvider;
    this.savePostToDbUseCaseProvider = savePostToDbUseCaseProvider;
    this.storiesPrefsProvider = storiesPrefsProvider;
    this.uploadServiceProvider = uploadServiceProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.postUtilsProvider = postUtilsProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.storyRepositoryWrapperProvider = storyRepositoryWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.editPostRepositoryProvider = editPostRepositoryProvider;
    this.storiesTrackerHelperProvider = storiesTrackerHelperProvider;
    this.saveStoryGutenbergBlockUseCaseProvider = saveStoryGutenbergBlockUseCaseProvider;
  }

  @Override
  public StoryMediaSaveUploadBridge get() {
    StoryMediaSaveUploadBridge instance = newInstance(addLocalMediaToPostUseCaseProvider.get(), savePostToDbUseCaseProvider.get(), storiesPrefsProvider.get(), uploadServiceProvider.get(), networkUtilsProvider.get(), postUtilsProvider.get(), eventBusWrapperProvider.get(), storyRepositoryWrapperProvider.get(), mainDispatcherProvider.get());
    StoryMediaSaveUploadBridge_MembersInjector.injectEditPostRepository(instance, editPostRepositoryProvider.get());
    StoryMediaSaveUploadBridge_MembersInjector.injectStoriesTrackerHelper(instance, storiesTrackerHelperProvider.get());
    StoryMediaSaveUploadBridge_MembersInjector.injectSaveStoryGutenbergBlockUseCase(instance, saveStoryGutenbergBlockUseCaseProvider.get());
    return instance;
  }

  public static StoryMediaSaveUploadBridge_Factory create(
      Provider<AddLocalMediaToPostUseCase> addLocalMediaToPostUseCaseProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<StoriesPrefs> storiesPrefsProvider,
      Provider<UploadServiceFacade> uploadServiceProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<PostUtilsWrapper> postUtilsProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<StoriesTrackerHelper> storiesTrackerHelperProvider,
      Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider) {
    return new StoryMediaSaveUploadBridge_Factory(addLocalMediaToPostUseCaseProvider, savePostToDbUseCaseProvider, storiesPrefsProvider, uploadServiceProvider, networkUtilsProvider, postUtilsProvider, eventBusWrapperProvider, storyRepositoryWrapperProvider, mainDispatcherProvider, editPostRepositoryProvider, storiesTrackerHelperProvider, saveStoryGutenbergBlockUseCaseProvider);
  }

  public static StoryMediaSaveUploadBridge newInstance(
      AddLocalMediaToPostUseCase addLocalMediaToPostUseCase,
      SavePostToDbUseCase savePostToDbUseCase, StoriesPrefs storiesPrefs,
      UploadServiceFacade uploadService, NetworkUtilsWrapper networkUtils,
      PostUtilsWrapper postUtils, EventBusWrapper eventBusWrapper,
      StoryRepositoryWrapper storyRepositoryWrapper, CoroutineDispatcher mainDispatcher) {
    return new StoryMediaSaveUploadBridge(addLocalMediaToPostUseCase, savePostToDbUseCase, storiesPrefs, uploadService, networkUtils, postUtils, eventBusWrapper, storyRepositoryWrapper, mainDispatcher);
  }
}
