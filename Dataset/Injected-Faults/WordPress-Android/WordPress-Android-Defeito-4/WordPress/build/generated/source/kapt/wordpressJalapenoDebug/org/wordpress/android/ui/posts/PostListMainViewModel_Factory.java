package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadStarter;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class PostListMainViewModel_Factory implements Factory<PostListMainViewModel> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<UploadActionUseCase> uploadActionUseCaseProvider;

  private final Provider<UploadStore> uploadStoreProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<AppPrefsWrapper> prefsProvider;

  private final Provider<PostListEventListener.Factory> postListEventListenerFactoryProvider;

  private final Provider<PreviewStateHelper> previewStateHelperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<UploadStarter> uploadStarterProvider;

  public PostListMainViewModel_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<PostStore> postStoreProvider, Provider<AccountStore> accountStoreProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<UploadStore> uploadStoreProvider, Provider<MediaStore> mediaStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AppPrefsWrapper> prefsProvider,
      Provider<PostListEventListener.Factory> postListEventListenerFactoryProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<UploadStarter> uploadStarterProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.postStoreProvider = postStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.uploadActionUseCaseProvider = uploadActionUseCaseProvider;
    this.uploadStoreProvider = uploadStoreProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.prefsProvider = prefsProvider;
    this.postListEventListenerFactoryProvider = postListEventListenerFactoryProvider;
    this.previewStateHelperProvider = previewStateHelperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.savePostToDbUseCaseProvider = savePostToDbUseCaseProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.uploadStarterProvider = uploadStarterProvider;
  }

  @Override
  public PostListMainViewModel get() {
    return newInstance(dispatcherProvider.get(), postStoreProvider.get(), accountStoreProvider.get(), uploadActionUseCaseProvider.get(), uploadStoreProvider.get(), mediaStoreProvider.get(), networkUtilsWrapperProvider.get(), prefsProvider.get(), postListEventListenerFactoryProvider.get(), previewStateHelperProvider.get(), analyticsTrackerProvider.get(), savePostToDbUseCaseProvider.get(), mainDispatcherProvider.get(), bgDispatcherProvider.get(), uploadStarterProvider.get());
  }

  public static PostListMainViewModel_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<PostStore> postStoreProvider, Provider<AccountStore> accountStoreProvider,
      Provider<UploadActionUseCase> uploadActionUseCaseProvider,
      Provider<UploadStore> uploadStoreProvider, Provider<MediaStore> mediaStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AppPrefsWrapper> prefsProvider,
      Provider<PostListEventListener.Factory> postListEventListenerFactoryProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<SavePostToDbUseCase> savePostToDbUseCaseProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<UploadStarter> uploadStarterProvider) {
    return new PostListMainViewModel_Factory(dispatcherProvider, postStoreProvider, accountStoreProvider, uploadActionUseCaseProvider, uploadStoreProvider, mediaStoreProvider, networkUtilsWrapperProvider, prefsProvider, postListEventListenerFactoryProvider, previewStateHelperProvider, analyticsTrackerProvider, savePostToDbUseCaseProvider, mainDispatcherProvider, bgDispatcherProvider, uploadStarterProvider);
  }

  public static PostListMainViewModel newInstance(Dispatcher dispatcher, PostStore postStore,
      AccountStore accountStore, UploadActionUseCase uploadActionUseCase, UploadStore uploadStore,
      MediaStore mediaStore, NetworkUtilsWrapper networkUtilsWrapper, AppPrefsWrapper prefs,
      PostListEventListener.Factory postListEventListenerFactory,
      PreviewStateHelper previewStateHelper, AnalyticsTrackerWrapper analyticsTracker,
      SavePostToDbUseCase savePostToDbUseCase, CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, UploadStarter uploadStarter) {
    return new PostListMainViewModel(dispatcher, postStore, accountStore, uploadActionUseCase, uploadStore, mediaStore, networkUtilsWrapper, prefs, postListEventListenerFactory, previewStateHelper, analyticsTracker, savePostToDbUseCase, mainDispatcher, bgDispatcher, uploadStarter);
  }
}
