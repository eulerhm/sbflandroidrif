package org.wordpress.android.ui.reader;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;

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
public final class ReaderPostListActivity_MembersInjector implements MembersInjector<ReaderPostListActivity> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<UploadActionUseCase> mUploadActionUseCaseProvider;

  private final Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  public ReaderPostListActivity_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<PostStore> mPostStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mPostStoreProvider = mPostStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mUploadActionUseCaseProvider = mUploadActionUseCaseProvider;
    this.mUploadUtilsWrapperProvider = mUploadUtilsWrapperProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
  }

  public static MembersInjector<ReaderPostListActivity> create(
      Provider<SiteStore> mSiteStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<Dispatcher> mDispatcherProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    return new ReaderPostListActivity_MembersInjector(mSiteStoreProvider, mPostStoreProvider, mDispatcherProvider, mUploadActionUseCaseProvider, mUploadUtilsWrapperProvider, mReaderTrackerProvider, mSelectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(ReaderPostListActivity instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMUploadActionUseCase(instance, mUploadActionUseCaseProvider.get());
    injectMUploadUtilsWrapper(instance, mUploadUtilsWrapperProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListActivity.mSiteStore")
  public static void injectMSiteStore(ReaderPostListActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListActivity.mPostStore")
  public static void injectMPostStore(ReaderPostListActivity instance, PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListActivity.mDispatcher")
  public static void injectMDispatcher(ReaderPostListActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListActivity.mUploadActionUseCase")
  public static void injectMUploadActionUseCase(ReaderPostListActivity instance,
      UploadActionUseCase mUploadActionUseCase) {
    instance.mUploadActionUseCase = mUploadActionUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListActivity.mUploadUtilsWrapper")
  public static void injectMUploadUtilsWrapper(ReaderPostListActivity instance,
      UploadUtilsWrapper mUploadUtilsWrapper) {
    instance.mUploadUtilsWrapper = mUploadUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListActivity.mReaderTracker")
  public static void injectMReaderTracker(ReaderPostListActivity instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListActivity.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(ReaderPostListActivity instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }
}
