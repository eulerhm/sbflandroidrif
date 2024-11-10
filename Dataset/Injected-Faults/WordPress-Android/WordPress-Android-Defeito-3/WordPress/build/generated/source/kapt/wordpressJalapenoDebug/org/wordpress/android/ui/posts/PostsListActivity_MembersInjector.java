package org.wordpress.android.ui.posts;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.stories.StoriesMediaPickerResultHandler;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.SnackbarSequencer;

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
public final class PostsListActivity_MembersInjector implements MembersInjector<PostsListActivity> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<RemotePreviewLogicHelper> remotePreviewLogicHelperProvider;

  private final Provider<PreviewStateHelper> previewStateHelperProvider;

  private final Provider<ProgressDialogHelper> progressDialogHelperProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<UploadActionUseCase> uploadActionUseCaseProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  private final Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider;

  private final Provider<SystemNotificationsTracker> systemNotificationTrackerProvider;

  private final Provider<EditPostRepository> editPostRepositoryProvider;

  private final Provider<MediaPickerLauncher> mediaPickerLauncherProvider;

  private final Provider<StoriesMediaPickerResultHandler> storiesMediaPickerResultHandlerProvider;

  private final Provider<BloggingRemindersViewModel> bloggingRemindersViewModelProvider;

  public PostsListActivity_MembersInjector(Provider<SiteStore> siteStoreProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<RemotePreviewLogicHelper> remotePreviewLogicHelperProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<ProgressDialogHelper> progressDialogHelperProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider,
      Provider<SystemNotificationsTracker> systemNotificationTrackerProvider,
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<StoriesMediaPickerResultHandler> storiesMediaPickerResultHandlerProvider,
      Provider<BloggingRemindersViewModel> bloggingRemindersViewModelProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.remotePreviewLogicHelperProvider = remotePreviewLogicHelperProvider;
    this.previewStateHelperProvider = previewStateHelperProvider;
    this.progressDialogHelperProvider = progressDialogHelperProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.uploadActionUseCaseProvider = uploadActionUseCaseProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
    this.uploadUtilsWrapperProvider = uploadUtilsWrapperProvider;
    this.systemNotificationTrackerProvider = systemNotificationTrackerProvider;
    this.editPostRepositoryProvider = editPostRepositoryProvider;
    this.mediaPickerLauncherProvider = mediaPickerLauncherProvider;
    this.storiesMediaPickerResultHandlerProvider = storiesMediaPickerResultHandlerProvider;
    this.bloggingRemindersViewModelProvider = bloggingRemindersViewModelProvider;
  }

  public static MembersInjector<PostsListActivity> create(Provider<SiteStore> siteStoreProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider,
      Provider<RemotePreviewLogicHelper> remotePreviewLogicHelperProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<ProgressDialogHelper> progressDialogHelperProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<UploadUtilsWrapper> uploadUtilsWrapperProvider,
      Provider<SystemNotificationsTracker> systemNotificationTrackerProvider,
      Provider<EditPostRepository> editPostRepositoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<StoriesMediaPickerResultHandler> storiesMediaPickerResultHandlerProvider,
      Provider<BloggingRemindersViewModel> bloggingRemindersViewModelProvider) {
    return new PostsListActivity_MembersInjector(siteStoreProvider, viewModelFactoryProvider, uiHelpersProvider, remotePreviewLogicHelperProvider, previewStateHelperProvider, progressDialogHelperProvider, dispatcherProvider, uploadActionUseCaseProvider, snackbarSequencerProvider, uploadUtilsWrapperProvider, systemNotificationTrackerProvider, editPostRepositoryProvider, mediaPickerLauncherProvider, storiesMediaPickerResultHandlerProvider, bloggingRemindersViewModelProvider);
  }

  @Override
  public void injectMembers(PostsListActivity instance) {
    injectSiteStore(instance, siteStoreProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectRemotePreviewLogicHelper(instance, remotePreviewLogicHelperProvider.get());
    injectPreviewStateHelper(instance, previewStateHelperProvider.get());
    injectProgressDialogHelper(instance, progressDialogHelperProvider.get());
    injectDispatcher(instance, dispatcherProvider.get());
    injectUploadActionUseCase(instance, uploadActionUseCaseProvider.get());
    injectSnackbarSequencer(instance, snackbarSequencerProvider.get());
    injectUploadUtilsWrapper(instance, uploadUtilsWrapperProvider.get());
    injectSystemNotificationTracker(instance, systemNotificationTrackerProvider.get());
    injectEditPostRepository(instance, editPostRepositoryProvider.get());
    injectMediaPickerLauncher(instance, mediaPickerLauncherProvider.get());
    injectStoriesMediaPickerResultHandler(instance, storiesMediaPickerResultHandlerProvider.get());
    injectBloggingRemindersViewModel(instance, bloggingRemindersViewModelProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.siteStore")
  public static void injectSiteStore(PostsListActivity instance, SiteStore siteStore) {
    instance.siteStore = siteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.viewModelFactory")
  public static void injectViewModelFactory(PostsListActivity instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.uiHelpers")
  public static void injectUiHelpers(PostsListActivity instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.remotePreviewLogicHelper")
  public static void injectRemotePreviewLogicHelper(PostsListActivity instance,
      RemotePreviewLogicHelper remotePreviewLogicHelper) {
    instance.remotePreviewLogicHelper = remotePreviewLogicHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.previewStateHelper")
  public static void injectPreviewStateHelper(PostsListActivity instance,
      PreviewStateHelper previewStateHelper) {
    instance.previewStateHelper = previewStateHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.progressDialogHelper")
  public static void injectProgressDialogHelper(PostsListActivity instance,
      ProgressDialogHelper progressDialogHelper) {
    instance.progressDialogHelper = progressDialogHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.dispatcher")
  public static void injectDispatcher(PostsListActivity instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.uploadActionUseCase")
  public static void injectUploadActionUseCase(PostsListActivity instance,
      UploadActionUseCase uploadActionUseCase) {
    instance.uploadActionUseCase = uploadActionUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.snackbarSequencer")
  public static void injectSnackbarSequencer(PostsListActivity instance,
      SnackbarSequencer snackbarSequencer) {
    instance.snackbarSequencer = snackbarSequencer;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.uploadUtilsWrapper")
  public static void injectUploadUtilsWrapper(PostsListActivity instance,
      UploadUtilsWrapper uploadUtilsWrapper) {
    instance.uploadUtilsWrapper = uploadUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.systemNotificationTracker")
  public static void injectSystemNotificationTracker(PostsListActivity instance,
      SystemNotificationsTracker systemNotificationTracker) {
    instance.systemNotificationTracker = systemNotificationTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.editPostRepository")
  public static void injectEditPostRepository(PostsListActivity instance,
      EditPostRepository editPostRepository) {
    instance.editPostRepository = editPostRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.mediaPickerLauncher")
  public static void injectMediaPickerLauncher(PostsListActivity instance,
      MediaPickerLauncher mediaPickerLauncher) {
    instance.mediaPickerLauncher = mediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.storiesMediaPickerResultHandler")
  public static void injectStoriesMediaPickerResultHandler(PostsListActivity instance,
      StoriesMediaPickerResultHandler storiesMediaPickerResultHandler) {
    instance.storiesMediaPickerResultHandler = storiesMediaPickerResultHandler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.posts.PostsListActivity.bloggingRemindersViewModel")
  public static void injectBloggingRemindersViewModel(PostsListActivity instance,
      BloggingRemindersViewModel bloggingRemindersViewModel) {
    instance.bloggingRemindersViewModel = bloggingRemindersViewModel;
  }
}
