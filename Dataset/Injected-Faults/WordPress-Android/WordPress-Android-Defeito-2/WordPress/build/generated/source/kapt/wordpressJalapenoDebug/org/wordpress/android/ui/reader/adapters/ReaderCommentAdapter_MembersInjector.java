package org.wordpress.android.ui.reader.adapters;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ThreadedCommentsUtils;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.config.ReaderCommentsModerationFeatureConfig;
import org.wordpress.android.util.image.ImageManager;

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
public final class ReaderCommentAdapter_MembersInjector implements MembersInjector<ReaderCommentAdapter> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  private final Provider<ThreadedCommentsUtils> mThreadedCommentsUtilsProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<ReaderCommentsModerationFeatureConfig> mReaderCommentsModerationFeatureConfigProvider;

  public ReaderCommentAdapter_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<ThreadedCommentsUtils> mThreadedCommentsUtilsProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<ReaderCommentsModerationFeatureConfig> mReaderCommentsModerationFeatureConfigProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
    this.mThreadedCommentsUtilsProvider = mThreadedCommentsUtilsProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mReaderCommentsModerationFeatureConfigProvider = mReaderCommentsModerationFeatureConfigProvider;
  }

  public static MembersInjector<ReaderCommentAdapter> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<ImageManager> mImageManagerProvider, Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<ThreadedCommentsUtils> mThreadedCommentsUtilsProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<ReaderCommentsModerationFeatureConfig> mReaderCommentsModerationFeatureConfigProvider) {
    return new ReaderCommentAdapter_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mImageManagerProvider, mReaderTrackerProvider, mThreadedCommentsUtilsProvider, mSelectedSiteRepositoryProvider, mUiHelpersProvider, mReaderCommentsModerationFeatureConfigProvider);
  }

  @Override
  public void injectMembers(ReaderCommentAdapter instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
    injectMThreadedCommentsUtils(instance, mThreadedCommentsUtilsProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMReaderCommentsModerationFeatureConfig(instance, mReaderCommentsModerationFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mAccountStore")
  public static void injectMAccountStore(ReaderCommentAdapter instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mSiteStore")
  public static void injectMSiteStore(ReaderCommentAdapter instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mImageManager")
  public static void injectMImageManager(ReaderCommentAdapter instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mReaderTracker")
  public static void injectMReaderTracker(ReaderCommentAdapter instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mThreadedCommentsUtils")
  public static void injectMThreadedCommentsUtils(ReaderCommentAdapter instance,
      ThreadedCommentsUtils mThreadedCommentsUtils) {
    instance.mThreadedCommentsUtils = mThreadedCommentsUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(ReaderCommentAdapter instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mUiHelpers")
  public static void injectMUiHelpers(ReaderCommentAdapter instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.adapters.ReaderCommentAdapter.mReaderCommentsModerationFeatureConfig")
  public static void injectMReaderCommentsModerationFeatureConfig(ReaderCommentAdapter instance,
      ReaderCommentsModerationFeatureConfig mReaderCommentsModerationFeatureConfig) {
    instance.mReaderCommentsModerationFeatureConfig = mReaderCommentsModerationFeatureConfig;
  }
}
