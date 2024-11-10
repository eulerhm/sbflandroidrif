package org.wordpress.android.ui.uploads;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.utils.UiHelpers;

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
public final class PostUploadHandler_MembersInjector implements MembersInjector<PostUploadHandler> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<UploadActionUseCase> mUploadActionUseCaseProvider;

  private final Provider<AutoSavePostIfNotDraftUseCase> mAutoSavePostIfNotDraftUseCaseProvider;

  private final Provider<PostMediaHandler> mPostMediaHandlerProvider;

  public PostUploadHandler_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<MediaStore> mMediaStoreProvider, Provider<UiHelpers> mUiHelpersProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<AutoSavePostIfNotDraftUseCase> mAutoSavePostIfNotDraftUseCaseProvider,
      Provider<PostMediaHandler> mPostMediaHandlerProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mPostStoreProvider = mPostStoreProvider;
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mUploadActionUseCaseProvider = mUploadActionUseCaseProvider;
    this.mAutoSavePostIfNotDraftUseCaseProvider = mAutoSavePostIfNotDraftUseCaseProvider;
    this.mPostMediaHandlerProvider = mPostMediaHandlerProvider;
  }

  public static MembersInjector<PostUploadHandler> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<MediaStore> mMediaStoreProvider, Provider<UiHelpers> mUiHelpersProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<AutoSavePostIfNotDraftUseCase> mAutoSavePostIfNotDraftUseCaseProvider,
      Provider<PostMediaHandler> mPostMediaHandlerProvider) {
    return new PostUploadHandler_MembersInjector(mDispatcherProvider, mSiteStoreProvider, mPostStoreProvider, mMediaStoreProvider, mUiHelpersProvider, mUploadActionUseCaseProvider, mAutoSavePostIfNotDraftUseCaseProvider, mPostMediaHandlerProvider);
  }

  @Override
  public void injectMembers(PostUploadHandler instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMUploadActionUseCase(instance, mUploadActionUseCaseProvider.get());
    injectMAutoSavePostIfNotDraftUseCase(instance, mAutoSavePostIfNotDraftUseCaseProvider.get());
    injectMPostMediaHandler(instance, mPostMediaHandlerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mDispatcher")
  public static void injectMDispatcher(PostUploadHandler instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mSiteStore")
  public static void injectMSiteStore(PostUploadHandler instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mPostStore")
  public static void injectMPostStore(PostUploadHandler instance, PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mMediaStore")
  public static void injectMMediaStore(PostUploadHandler instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mUiHelpers")
  public static void injectMUiHelpers(PostUploadHandler instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mUploadActionUseCase")
  public static void injectMUploadActionUseCase(PostUploadHandler instance,
      UploadActionUseCase mUploadActionUseCase) {
    instance.mUploadActionUseCase = mUploadActionUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mAutoSavePostIfNotDraftUseCase")
  public static void injectMAutoSavePostIfNotDraftUseCase(PostUploadHandler instance,
      AutoSavePostIfNotDraftUseCase mAutoSavePostIfNotDraftUseCase) {
    instance.mAutoSavePostIfNotDraftUseCase = mAutoSavePostIfNotDraftUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.PostUploadHandler.mPostMediaHandler")
  public static void injectMPostMediaHandler(PostUploadHandler instance,
      PostMediaHandler mPostMediaHandler) {
    instance.mPostMediaHandler = mPostMediaHandler;
  }
}
