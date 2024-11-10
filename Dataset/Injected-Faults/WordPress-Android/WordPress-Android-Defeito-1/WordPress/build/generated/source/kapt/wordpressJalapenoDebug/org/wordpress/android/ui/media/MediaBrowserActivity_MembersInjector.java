package org.wordpress.android.ui.media;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.util.MediaUtilsWrapper;

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
public final class MediaBrowserActivity_MembersInjector implements MembersInjector<MediaBrowserActivity> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<MediaStore> mMediaStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider;

  private final Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider;

  private final Provider<MediaPickerLauncher> mMediaPickerLauncherProvider;

  private final Provider<MediaUtilsWrapper> mMediaUtilsWrapperProvider;

  private final Provider<QuickStartRepository> mQuickStartRepositoryProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  public MediaBrowserActivity_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<MediaUtilsWrapper> mMediaUtilsWrapperProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mMediaStoreProvider = mMediaStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mUploadUtilsWrapperProvider = mUploadUtilsWrapperProvider;
    this.mSystemNotificationsTrackerProvider = mSystemNotificationsTrackerProvider;
    this.mMediaPickerLauncherProvider = mMediaPickerLauncherProvider;
    this.mMediaUtilsWrapperProvider = mMediaUtilsWrapperProvider;
    this.mQuickStartRepositoryProvider = mQuickStartRepositoryProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
  }

  public static MembersInjector<MediaBrowserActivity> create(
      Provider<Dispatcher> mDispatcherProvider, Provider<MediaStore> mMediaStoreProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<MediaUtilsWrapper> mMediaUtilsWrapperProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    return new MediaBrowserActivity_MembersInjector(mDispatcherProvider, mMediaStoreProvider, mSiteStoreProvider, mUploadUtilsWrapperProvider, mSystemNotificationsTrackerProvider, mMediaPickerLauncherProvider, mMediaUtilsWrapperProvider, mQuickStartRepositoryProvider, mSelectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(MediaBrowserActivity instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMMediaStore(instance, mMediaStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMUploadUtilsWrapper(instance, mUploadUtilsWrapperProvider.get());
    injectMSystemNotificationsTracker(instance, mSystemNotificationsTrackerProvider.get());
    injectMMediaPickerLauncher(instance, mMediaPickerLauncherProvider.get());
    injectMMediaUtilsWrapper(instance, mMediaUtilsWrapperProvider.get());
    injectMQuickStartRepository(instance, mQuickStartRepositoryProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mDispatcher")
  public static void injectMDispatcher(MediaBrowserActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mMediaStore")
  public static void injectMMediaStore(MediaBrowserActivity instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mSiteStore")
  public static void injectMSiteStore(MediaBrowserActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mUploadUtilsWrapper")
  public static void injectMUploadUtilsWrapper(MediaBrowserActivity instance,
      UploadUtilsWrapper mUploadUtilsWrapper) {
    instance.mUploadUtilsWrapper = mUploadUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mSystemNotificationsTracker")
  public static void injectMSystemNotificationsTracker(MediaBrowserActivity instance,
      SystemNotificationsTracker mSystemNotificationsTracker) {
    instance.mSystemNotificationsTracker = mSystemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mMediaPickerLauncher")
  public static void injectMMediaPickerLauncher(MediaBrowserActivity instance,
      MediaPickerLauncher mMediaPickerLauncher) {
    instance.mMediaPickerLauncher = mMediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mMediaUtilsWrapper")
  public static void injectMMediaUtilsWrapper(MediaBrowserActivity instance,
      MediaUtilsWrapper mMediaUtilsWrapper) {
    instance.mMediaUtilsWrapper = mMediaUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mQuickStartRepository")
  public static void injectMQuickStartRepository(MediaBrowserActivity instance,
      QuickStartRepository mQuickStartRepository) {
    instance.mQuickStartRepository = mQuickStartRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.MediaBrowserActivity.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(MediaBrowserActivity instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }
}
