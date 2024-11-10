package org.wordpress.android.ui.reader;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.ReaderStore;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.reader.services.update.TagUpdateClientUtilsProvider;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.config.SeenUnseenWithCounterFeatureConfig;
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
public final class ReaderPostListFragment_MembersInjector implements MembersInjector<ReaderPostListFragment> {
  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<ReaderStore> mReaderStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  private final Provider<UiHelpers> mUiHelpersProvider;

  private final Provider<TagUpdateClientUtilsProvider> mTagUpdateClientUtilsProvider;

  private final Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider;

  private final Provider<SeenUnseenWithCounterFeatureConfig> mSeenUnseenWithCounterFeatureConfigProvider;

  private final Provider<QuickStartRepository> mQuickStartRepositoryProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  private final Provider<SnackbarSequencer> mSnackbarSequencerProvider;

  public ReaderPostListFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<ReaderStore> mReaderStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<TagUpdateClientUtilsProvider> mTagUpdateClientUtilsProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> mSeenUnseenWithCounterFeatureConfigProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<SnackbarSequencer> mSnackbarSequencerProvider) {
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mReaderStoreProvider = mReaderStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mImageManagerProvider = mImageManagerProvider;
    this.mUiHelpersProvider = mUiHelpersProvider;
    this.mTagUpdateClientUtilsProvider = mTagUpdateClientUtilsProvider;
    this.mQuickStartUtilsWrapperProvider = mQuickStartUtilsWrapperProvider;
    this.mSeenUnseenWithCounterFeatureConfigProvider = mSeenUnseenWithCounterFeatureConfigProvider;
    this.mQuickStartRepositoryProvider = mQuickStartRepositoryProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
    this.mSnackbarSequencerProvider = mSnackbarSequencerProvider;
  }

  public static MembersInjector<ReaderPostListFragment> create(
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<AccountStore> mAccountStoreProvider, Provider<ReaderStore> mReaderStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<ImageManager> mImageManagerProvider,
      Provider<UiHelpers> mUiHelpersProvider,
      Provider<TagUpdateClientUtilsProvider> mTagUpdateClientUtilsProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<SeenUnseenWithCounterFeatureConfig> mSeenUnseenWithCounterFeatureConfigProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<SnackbarSequencer> mSnackbarSequencerProvider) {
    return new ReaderPostListFragment_MembersInjector(mViewModelFactoryProvider, mAccountStoreProvider, mReaderStoreProvider, mDispatcherProvider, mImageManagerProvider, mUiHelpersProvider, mTagUpdateClientUtilsProvider, mQuickStartUtilsWrapperProvider, mSeenUnseenWithCounterFeatureConfigProvider, mQuickStartRepositoryProvider, mReaderTrackerProvider, mSnackbarSequencerProvider);
  }

  @Override
  public void injectMembers(ReaderPostListFragment instance) {
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMReaderStore(instance, mReaderStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
    injectMUiHelpers(instance, mUiHelpersProvider.get());
    injectMTagUpdateClientUtilsProvider(instance, mTagUpdateClientUtilsProvider.get());
    injectMQuickStartUtilsWrapper(instance, mQuickStartUtilsWrapperProvider.get());
    injectMSeenUnseenWithCounterFeatureConfig(instance, mSeenUnseenWithCounterFeatureConfigProvider.get());
    injectMQuickStartRepository(instance, mQuickStartRepositoryProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
    injectMSnackbarSequencer(instance, mSnackbarSequencerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mViewModelFactory")
  public static void injectMViewModelFactory(ReaderPostListFragment instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mAccountStore")
  public static void injectMAccountStore(ReaderPostListFragment instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mReaderStore")
  public static void injectMReaderStore(ReaderPostListFragment instance, ReaderStore mReaderStore) {
    instance.mReaderStore = mReaderStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mDispatcher")
  public static void injectMDispatcher(ReaderPostListFragment instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mImageManager")
  public static void injectMImageManager(ReaderPostListFragment instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mUiHelpers")
  public static void injectMUiHelpers(ReaderPostListFragment instance, UiHelpers mUiHelpers) {
    instance.mUiHelpers = mUiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mTagUpdateClientUtilsProvider")
  public static void injectMTagUpdateClientUtilsProvider(ReaderPostListFragment instance,
      TagUpdateClientUtilsProvider mTagUpdateClientUtilsProvider) {
    instance.mTagUpdateClientUtilsProvider = mTagUpdateClientUtilsProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mQuickStartUtilsWrapper")
  public static void injectMQuickStartUtilsWrapper(ReaderPostListFragment instance,
      QuickStartUtilsWrapper mQuickStartUtilsWrapper) {
    instance.mQuickStartUtilsWrapper = mQuickStartUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mSeenUnseenWithCounterFeatureConfig")
  public static void injectMSeenUnseenWithCounterFeatureConfig(ReaderPostListFragment instance,
      SeenUnseenWithCounterFeatureConfig mSeenUnseenWithCounterFeatureConfig) {
    instance.mSeenUnseenWithCounterFeatureConfig = mSeenUnseenWithCounterFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mQuickStartRepository")
  public static void injectMQuickStartRepository(ReaderPostListFragment instance,
      QuickStartRepository mQuickStartRepository) {
    instance.mQuickStartRepository = mQuickStartRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mReaderTracker")
  public static void injectMReaderTracker(ReaderPostListFragment instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostListFragment.mSnackbarSequencer")
  public static void injectMSnackbarSequencer(ReaderPostListFragment instance,
      SnackbarSequencer mSnackbarSequencer) {
    instance.mSnackbarSequencer = mSnackbarSequencer;
  }
}
