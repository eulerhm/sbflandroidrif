package org.wordpress.android.ui.reader;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderPostSeenStatusWrapper;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.util.UrlUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
import org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig;

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
public final class ReaderPostPagerActivity_MembersInjector implements MembersInjector<ReaderPostPagerActivity> {
  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  private final Provider<AnalyticsUtilsWrapper> mAnalyticsUtilsWrapperProvider;

  private final Provider<ReaderPostTableWrapper> mReaderPostTableWrapperProvider;

  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<UploadActionUseCase> mUploadActionUseCaseProvider;

  private final Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider;

  private final Provider<ReaderPostSeenStatusWrapper> mPostSeenStatusWrapperProvider;

  private final Provider<SeenUnseenWithCounterFeatureConfig> mSeenUnseenWithCounterFeatureConfigProvider;

  private final Provider<UrlUtilsWrapper> mUrlUtilsWrapperProvider;

  private final Provider<DeepLinkTrackingUtils> mDeepLinkTrackingUtilsProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  public ReaderPostPagerActivity_MembersInjector(Provider<SiteStore> mSiteStoreProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<AnalyticsUtilsWrapper> mAnalyticsUtilsWrapperProvider,
      Provider<ReaderPostTableWrapper> mReaderPostTableWrapperProvider,
      Provider<PostStore> mPostStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<ReaderPostSeenStatusWrapper> mPostSeenStatusWrapperProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> mSeenUnseenWithCounterFeatureConfigProvider,
      Provider<UrlUtilsWrapper> mUrlUtilsWrapperProvider,
      Provider<DeepLinkTrackingUtils> mDeepLinkTrackingUtilsProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
    this.mAnalyticsUtilsWrapperProvider = mAnalyticsUtilsWrapperProvider;
    this.mReaderPostTableWrapperProvider = mReaderPostTableWrapperProvider;
    this.mPostStoreProvider = mPostStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mUploadActionUseCaseProvider = mUploadActionUseCaseProvider;
    this.mUploadUtilsWrapperProvider = mUploadUtilsWrapperProvider;
    this.mPostSeenStatusWrapperProvider = mPostSeenStatusWrapperProvider;
    this.mSeenUnseenWithCounterFeatureConfigProvider = mSeenUnseenWithCounterFeatureConfigProvider;
    this.mUrlUtilsWrapperProvider = mUrlUtilsWrapperProvider;
    this.mDeepLinkTrackingUtilsProvider = mDeepLinkTrackingUtilsProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
  }

  public static MembersInjector<ReaderPostPagerActivity> create(
      Provider<SiteStore> mSiteStoreProvider, Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<AnalyticsUtilsWrapper> mAnalyticsUtilsWrapperProvider,
      Provider<ReaderPostTableWrapper> mReaderPostTableWrapperProvider,
      Provider<PostStore> mPostStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<ReaderPostSeenStatusWrapper> mPostSeenStatusWrapperProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> mSeenUnseenWithCounterFeatureConfigProvider,
      Provider<UrlUtilsWrapper> mUrlUtilsWrapperProvider,
      Provider<DeepLinkTrackingUtils> mDeepLinkTrackingUtilsProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider) {
    return new ReaderPostPagerActivity_MembersInjector(mSiteStoreProvider, mReaderTrackerProvider, mAnalyticsUtilsWrapperProvider, mReaderPostTableWrapperProvider, mPostStoreProvider, mDispatcherProvider, mUploadActionUseCaseProvider, mUploadUtilsWrapperProvider, mPostSeenStatusWrapperProvider, mSeenUnseenWithCounterFeatureConfigProvider, mUrlUtilsWrapperProvider, mDeepLinkTrackingUtilsProvider, mSelectedSiteRepositoryProvider);
  }

  @Override
  public void injectMembers(ReaderPostPagerActivity instance) {
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
    injectMAnalyticsUtilsWrapper(instance, mAnalyticsUtilsWrapperProvider.get());
    injectMReaderPostTableWrapper(instance, mReaderPostTableWrapperProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMUploadActionUseCase(instance, mUploadActionUseCaseProvider.get());
    injectMUploadUtilsWrapper(instance, mUploadUtilsWrapperProvider.get());
    injectMPostSeenStatusWrapper(instance, mPostSeenStatusWrapperProvider.get());
    injectMSeenUnseenWithCounterFeatureConfig(instance, mSeenUnseenWithCounterFeatureConfigProvider.get());
    injectMUrlUtilsWrapper(instance, mUrlUtilsWrapperProvider.get());
    injectMDeepLinkTrackingUtils(instance, mDeepLinkTrackingUtilsProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mSiteStore")
  public static void injectMSiteStore(ReaderPostPagerActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mReaderTracker")
  public static void injectMReaderTracker(ReaderPostPagerActivity instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mAnalyticsUtilsWrapper")
  public static void injectMAnalyticsUtilsWrapper(ReaderPostPagerActivity instance,
      AnalyticsUtilsWrapper mAnalyticsUtilsWrapper) {
    instance.mAnalyticsUtilsWrapper = mAnalyticsUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mReaderPostTableWrapper")
  public static void injectMReaderPostTableWrapper(ReaderPostPagerActivity instance,
      ReaderPostTableWrapper mReaderPostTableWrapper) {
    instance.mReaderPostTableWrapper = mReaderPostTableWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mPostStore")
  public static void injectMPostStore(ReaderPostPagerActivity instance, PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mDispatcher")
  public static void injectMDispatcher(ReaderPostPagerActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mUploadActionUseCase")
  public static void injectMUploadActionUseCase(ReaderPostPagerActivity instance,
      UploadActionUseCase mUploadActionUseCase) {
    instance.mUploadActionUseCase = mUploadActionUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mUploadUtilsWrapper")
  public static void injectMUploadUtilsWrapper(ReaderPostPagerActivity instance,
      UploadUtilsWrapper mUploadUtilsWrapper) {
    instance.mUploadUtilsWrapper = mUploadUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mPostSeenStatusWrapper")
  public static void injectMPostSeenStatusWrapper(ReaderPostPagerActivity instance,
      ReaderPostSeenStatusWrapper mPostSeenStatusWrapper) {
    instance.mPostSeenStatusWrapper = mPostSeenStatusWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mSeenUnseenWithCounterFeatureConfig")
  public static void injectMSeenUnseenWithCounterFeatureConfig(ReaderPostPagerActivity instance,
      SeenUnseenWithCounterFeatureConfig mSeenUnseenWithCounterFeatureConfig) {
    instance.mSeenUnseenWithCounterFeatureConfig = mSeenUnseenWithCounterFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mUrlUtilsWrapper")
  public static void injectMUrlUtilsWrapper(ReaderPostPagerActivity instance,
      UrlUtilsWrapper mUrlUtilsWrapper) {
    instance.mUrlUtilsWrapper = mUrlUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mDeepLinkTrackingUtils")
  public static void injectMDeepLinkTrackingUtils(ReaderPostPagerActivity instance,
      DeepLinkTrackingUtils mDeepLinkTrackingUtils) {
    instance.mDeepLinkTrackingUtils = mDeepLinkTrackingUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostPagerActivity.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(ReaderPostPagerActivity instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }
}
