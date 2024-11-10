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
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.posts.PostUtilsWrapper;

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
public final class UploadService_MembersInjector implements MembersInjector<UploadService> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<UploadStore> mUploadStoreProvider;

  private final Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider;

  private final Provider<PostUtilsWrapper> mPostUtilsWrapperProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  public UploadService_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<UploadStore> mUploadStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<PostUtilsWrapper> mPostUtilsWrapperProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mPostStoreProvider = mPostStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mUploadStoreProvider = mUploadStoreProvider;
    this.mSystemNotificationsTrackerProvider = mSystemNotificationsTrackerProvider;
    this.mPostUtilsWrapperProvider = mPostUtilsWrapperProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
  }

  public static MembersInjector<UploadService> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<UploadStore> mUploadStoreProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<PostUtilsWrapper> mPostUtilsWrapperProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    return new UploadService_MembersInjector(mDispatcherProvider, mMediaStoreProvider, mPostStoreProvider, mSiteStoreProvider, mUploadStoreProvider, mSystemNotificationsTrackerProvider, mPostUtilsWrapperProvider, mSelectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(UploadService instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMUploadStore(instance, mUploadStoreProvider.get());
    injectMSystemNotificationsTracker(instance, mSystemNotificationsTrackerProvider.get());
    injectMPostUtilsWrapper(instance, mPostUtilsWrapperProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mDispatcher")
  public static void injectMDispatcher(UploadService instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mMediaStore")
  public static void injectMMediaStore(UploadService instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mPostStore")
  public static void injectMPostStore(UploadService instance, PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mSiteStore")
  public static void injectMSiteStore(UploadService instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mUploadStore")
  public static void injectMUploadStore(UploadService instance, UploadStore mUploadStore) {
    instance.mUploadStore = mUploadStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mSystemNotificationsTracker")
  public static void injectMSystemNotificationsTracker(UploadService instance,
      SystemNotificationsTracker mSystemNotificationsTracker) {
    instance.mSystemNotificationsTracker = mSystemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mPostUtilsWrapper")
  public static void injectMPostUtilsWrapper(UploadService instance,
      PostUtilsWrapper mPostUtilsWrapper) {
    instance.mPostUtilsWrapper = mPostUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.UploadService.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(UploadService instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }
}
