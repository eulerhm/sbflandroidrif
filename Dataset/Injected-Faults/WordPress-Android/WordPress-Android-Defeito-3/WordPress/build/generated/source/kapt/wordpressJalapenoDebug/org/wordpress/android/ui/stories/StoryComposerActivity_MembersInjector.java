package org.wordpress.android.ui.stories;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.posts.EditPostRepository;
import org.wordpress.android.ui.posts.ProgressDialogHelper;
import org.wordpress.android.ui.stories.media.StoryEditorMedia;
import org.wordpress.android.ui.stories.prefs.StoriesPrefs;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.FluxCUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class StoryComposerActivity_MembersInjector implements MembersInjector<StoryComposerActivity> {
  private final Provider<StoryEditorMedia> storyEditorMediaProvider;

  private final Provider<ProgressDialogHelper> progressDialogHelperProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<AuthenticationUtils> authenticationUtilsProvider;

  private final Provider<EditPostRepository> editPostRepositoryProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<MediaPickerLauncher> mediaPickerLauncherProvider;

  private final Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider;

  private final Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider;

  private final Provider<StoriesPrefs> storiesPrefsProvider;

  public StoryComposerActivity_MembersInjector(Provider<StoryEditorMedia> storyEditorMediaProvider,
      Provider<ProgressDialogHelper> progressDialogHelperProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<PostStore> postStoreProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider,
      Provider<MediaStore> mediaStoreProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<StoriesPrefs> storiesPrefsProvider) {
    this.storyEditorMediaProvider = storyEditorMediaProvider;
    this.progressDialogHelperProvider = progressDialogHelperProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.postStoreProvider = postStoreProvider;
    this.authenticationUtilsProvider = authenticationUtilsProvider;
    this.editPostRepositoryProvider = editPostRepositoryProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.mediaPickerLauncherProvider = mediaPickerLauncherProvider;
    this.saveStoryGutenbergBlockUseCaseProvider = saveStoryGutenbergBlockUseCaseProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.fluxCUtilsWrapperProvider = fluxCUtilsWrapperProvider;
    this.storyRepositoryWrapperProvider = storyRepositoryWrapperProvider;
    this.storiesPrefsProvider = storiesPrefsProvider;
  }

  public static MembersInjector<StoryComposerActivity> create(
      Provider<StoryEditorMedia> storyEditorMediaProvider,
      Provider<ProgressDialogHelper> progressDialogHelperProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<PostStore> postStoreProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<SaveStoryGutenbergBlockUseCase> saveStoryGutenbergBlockUseCaseProvider,
      Provider<MediaStore> mediaStoreProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<StoryRepositoryWrapper> storyRepositoryWrapperProvider,
      Provider<StoriesPrefs> storiesPrefsProvider) {
    return new StoryComposerActivity_MembersInjector(storyEditorMediaProvider, progressDialogHelperProvider, uiHelpersProvider, postStoreProvider, authenticationUtilsProvider, editPostRepositoryProvider, analyticsTrackerWrapperProvider, analyticsUtilsWrapperProvider, viewModelFactoryProvider, mediaPickerLauncherProvider, saveStoryGutenbergBlockUseCaseProvider, mediaStoreProvider, fluxCUtilsWrapperProvider, storyRepositoryWrapperProvider, storiesPrefsProvider);
  }

  @Override
  public void injectMembers(StoryComposerActivity instance) {
    injectStoryEditorMedia(instance, storyEditorMediaProvider.get());
    injectProgressDialogHelper(instance, progressDialogHelperProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectPostStore(instance, postStoreProvider.get());
    injectAuthenticationUtils(instance, authenticationUtilsProvider.get());
    injectEditPostRepository(instance, editPostRepositoryProvider.get());
    injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
    injectAnalyticsUtilsWrapper(instance, analyticsUtilsWrapperProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectMediaPickerLauncher(instance, mediaPickerLauncherProvider.get());
    injectSaveStoryGutenbergBlockUseCase(instance, saveStoryGutenbergBlockUseCaseProvider.get());
    injectMediaStore(instance, mediaStoreProvider.get());
    injectFluxCUtilsWrapper(instance, fluxCUtilsWrapperProvider.get());
    injectStoryRepositoryWrapper(instance, storyRepositoryWrapperProvider.get());
    injectStoriesPrefs(instance, storiesPrefsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.storyEditorMedia")
  public static void injectStoryEditorMedia(StoryComposerActivity instance,
      StoryEditorMedia storyEditorMedia) {
    instance.storyEditorMedia = storyEditorMedia;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.progressDialogHelper")
  public static void injectProgressDialogHelper(StoryComposerActivity instance,
      ProgressDialogHelper progressDialogHelper) {
    instance.progressDialogHelper = progressDialogHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.uiHelpers")
  public static void injectUiHelpers(StoryComposerActivity instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.postStore")
  public static void injectPostStore(StoryComposerActivity instance, PostStore postStore) {
    instance.postStore = postStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.authenticationUtils")
  public static void injectAuthenticationUtils(StoryComposerActivity instance,
      AuthenticationUtils authenticationUtils) {
    instance.authenticationUtils = authenticationUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.editPostRepository")
  public static void injectEditPostRepository(StoryComposerActivity instance,
      EditPostRepository editPostRepository) {
    instance.editPostRepository = editPostRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.analyticsTrackerWrapper")
  public static void injectAnalyticsTrackerWrapper(StoryComposerActivity instance,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    instance.analyticsTrackerWrapper = analyticsTrackerWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.analyticsUtilsWrapper")
  public static void injectAnalyticsUtilsWrapper(StoryComposerActivity instance,
      AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    instance.analyticsUtilsWrapper = analyticsUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.viewModelFactory")
  public static void injectViewModelFactory(StoryComposerActivity instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.mediaPickerLauncher")
  public static void injectMediaPickerLauncher(StoryComposerActivity instance,
      MediaPickerLauncher mediaPickerLauncher) {
    instance.mediaPickerLauncher = mediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.saveStoryGutenbergBlockUseCase")
  public static void injectSaveStoryGutenbergBlockUseCase(StoryComposerActivity instance,
      SaveStoryGutenbergBlockUseCase saveStoryGutenbergBlockUseCase) {
    instance.saveStoryGutenbergBlockUseCase = saveStoryGutenbergBlockUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.mediaStore")
  public static void injectMediaStore(StoryComposerActivity instance, MediaStore mediaStore) {
    instance.mediaStore = mediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.fluxCUtilsWrapper")
  public static void injectFluxCUtilsWrapper(StoryComposerActivity instance,
      FluxCUtilsWrapper fluxCUtilsWrapper) {
    instance.fluxCUtilsWrapper = fluxCUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.storyRepositoryWrapper")
  public static void injectStoryRepositoryWrapper(StoryComposerActivity instance,
      StoryRepositoryWrapper storyRepositoryWrapper) {
    instance.storyRepositoryWrapper = storyRepositoryWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stories.StoryComposerActivity.storiesPrefs")
  public static void injectStoriesPrefs(StoryComposerActivity instance, StoriesPrefs storiesPrefs) {
    instance.storiesPrefs = storiesPrefs;
  }
}
