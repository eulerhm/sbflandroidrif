package org.wordpress.android.ui.main;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.login.LoginAnalyticsListener;
import org.wordpress.android.push.GCMMessageHandler;
import org.wordpress.android.ui.ShortcutsNavigator;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.quickstart.QuickStartTracker;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.uploads.UploadActionUseCase;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.ShortcutUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.MySiteDashboardTodaysStatsCardFeatureConfig;
import org.wordpress.android.util.config.StatsRevampV2FeatureConfig;
import org.wordpress.android.workers.notification.createsite.CreateSiteNotificationScheduler;
import org.wordpress.android.workers.weeklyroundup.WeeklyRoundupScheduler;

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
public final class WPMainActivity_MembersInjector implements MembersInjector<WPMainActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<PostStore> mPostStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider;

  private final Provider<ShortcutsNavigator> mShortcutsNavigatorProvider;

  private final Provider<ShortcutUtils> mShortcutUtilsProvider;

  private final Provider<QuickStartStore> mQuickStartStoreProvider;

  private final Provider<UploadActionUseCase> mUploadActionUseCaseProvider;

  private final Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider;

  private final Provider<GCMMessageHandler> mGCMMessageHandlerProvider;

  private final Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider;

  private final Provider<ViewModelProvider.Factory> mViewModelFactoryProvider;

  private final Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  private final Provider<MediaPickerLauncher> mMediaPickerLauncherProvider;

  private final Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider;

  private final Provider<QuickStartRepository> mQuickStartRepositoryProvider;

  private final Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider;

  private final Provider<CreateSiteNotificationScheduler> mCreateSiteNotificationSchedulerProvider;

  private final Provider<WeeklyRoundupScheduler> mWeeklyRoundupSchedulerProvider;

  private final Provider<MySiteDashboardTodaysStatsCardFeatureConfig> mTodaysStatsCardFeatureConfigProvider;

  private final Provider<QuickStartTracker> mQuickStartTrackerProvider;

  private final Provider<StatsRevampV2FeatureConfig> mStatsRevampV2FeatureConfigProvider;

  private final Provider<BuildConfigWrapper> mBuildConfigWrapperProvider;

  public WPMainActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<Dispatcher> mDispatcherProvider,
      Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider,
      Provider<ShortcutsNavigator> mShortcutsNavigatorProvider,
      Provider<ShortcutUtils> mShortcutUtilsProvider,
      Provider<QuickStartStore> mQuickStartStoreProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider,
      Provider<CreateSiteNotificationScheduler> mCreateSiteNotificationSchedulerProvider,
      Provider<WeeklyRoundupScheduler> mWeeklyRoundupSchedulerProvider,
      Provider<MySiteDashboardTodaysStatsCardFeatureConfig> mTodaysStatsCardFeatureConfigProvider,
      Provider<QuickStartTracker> mQuickStartTrackerProvider,
      Provider<StatsRevampV2FeatureConfig> mStatsRevampV2FeatureConfigProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mPostStoreProvider = mPostStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mLoginAnalyticsListenerProvider = mLoginAnalyticsListenerProvider;
    this.mShortcutsNavigatorProvider = mShortcutsNavigatorProvider;
    this.mShortcutUtilsProvider = mShortcutUtilsProvider;
    this.mQuickStartStoreProvider = mQuickStartStoreProvider;
    this.mUploadActionUseCaseProvider = mUploadActionUseCaseProvider;
    this.mSystemNotificationsTrackerProvider = mSystemNotificationsTrackerProvider;
    this.mGCMMessageHandlerProvider = mGCMMessageHandlerProvider;
    this.mUploadUtilsWrapperProvider = mUploadUtilsWrapperProvider;
    this.mViewModelFactoryProvider = mViewModelFactoryProvider;
    this.mPrivateAtomicCookieProvider = mPrivateAtomicCookieProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
    this.mMediaPickerLauncherProvider = mMediaPickerLauncherProvider;
    this.mSelectedSiteRepositoryProvider = mSelectedSiteRepositoryProvider;
    this.mQuickStartRepositoryProvider = mQuickStartRepositoryProvider;
    this.mQuickStartUtilsWrapperProvider = mQuickStartUtilsWrapperProvider;
    this.mAnalyticsTrackerWrapperProvider = mAnalyticsTrackerWrapperProvider;
    this.mCreateSiteNotificationSchedulerProvider = mCreateSiteNotificationSchedulerProvider;
    this.mWeeklyRoundupSchedulerProvider = mWeeklyRoundupSchedulerProvider;
    this.mTodaysStatsCardFeatureConfigProvider = mTodaysStatsCardFeatureConfigProvider;
    this.mQuickStartTrackerProvider = mQuickStartTrackerProvider;
    this.mStatsRevampV2FeatureConfigProvider = mStatsRevampV2FeatureConfigProvider;
    this.mBuildConfigWrapperProvider = mBuildConfigWrapperProvider;
  }

  public static MembersInjector<WPMainActivity> create(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<PostStore> mPostStoreProvider,
      Provider<Dispatcher> mDispatcherProvider,
      Provider<LoginAnalyticsListener> mLoginAnalyticsListenerProvider,
      Provider<ShortcutsNavigator> mShortcutsNavigatorProvider,
      Provider<ShortcutUtils> mShortcutUtilsProvider,
      Provider<QuickStartStore> mQuickStartStoreProvider,
      Provider<UploadActionUseCase> mUploadActionUseCaseProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider,
      Provider<UploadUtilsWrapper> mUploadUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> mViewModelFactoryProvider,
      Provider<PrivateAtomicCookie> mPrivateAtomicCookieProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<MediaPickerLauncher> mMediaPickerLauncherProvider,
      Provider<SelectedSiteRepository> mSelectedSiteRepositoryProvider,
      Provider<QuickStartRepository> mQuickStartRepositoryProvider,
      Provider<QuickStartUtilsWrapper> mQuickStartUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> mAnalyticsTrackerWrapperProvider,
      Provider<CreateSiteNotificationScheduler> mCreateSiteNotificationSchedulerProvider,
      Provider<WeeklyRoundupScheduler> mWeeklyRoundupSchedulerProvider,
      Provider<MySiteDashboardTodaysStatsCardFeatureConfig> mTodaysStatsCardFeatureConfigProvider,
      Provider<QuickStartTracker> mQuickStartTrackerProvider,
      Provider<StatsRevampV2FeatureConfig> mStatsRevampV2FeatureConfigProvider,
      Provider<BuildConfigWrapper> mBuildConfigWrapperProvider) {
    return new WPMainActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mPostStoreProvider, mDispatcherProvider, mLoginAnalyticsListenerProvider, mShortcutsNavigatorProvider, mShortcutUtilsProvider, mQuickStartStoreProvider, mUploadActionUseCaseProvider, mSystemNotificationsTrackerProvider, mGCMMessageHandlerProvider, mUploadUtilsWrapperProvider, mViewModelFactoryProvider, mPrivateAtomicCookieProvider, mReaderTrackerProvider, mMediaPickerLauncherProvider, mSelectedSiteRepositoryProvider, mQuickStartRepositoryProvider, mQuickStartUtilsWrapperProvider, mAnalyticsTrackerWrapperProvider, mCreateSiteNotificationSchedulerProvider, mWeeklyRoundupSchedulerProvider, mTodaysStatsCardFeatureConfigProvider, mQuickStartTrackerProvider, mStatsRevampV2FeatureConfigProvider, mBuildConfigWrapperProvider);
  }

  @Override
  public void injectMembers(WPMainActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMPostStore(instance, mPostStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMLoginAnalyticsListener(instance, mLoginAnalyticsListenerProvider.get());
    injectMShortcutsNavigator(instance, mShortcutsNavigatorProvider.get());
    injectMShortcutUtils(instance, mShortcutUtilsProvider.get());
    injectMQuickStartStore(instance, mQuickStartStoreProvider.get());
    injectMUploadActionUseCase(instance, mUploadActionUseCaseProvider.get());
    injectMSystemNotificationsTracker(instance, mSystemNotificationsTrackerProvider.get());
    injectMGCMMessageHandler(instance, mGCMMessageHandlerProvider.get());
    injectMUploadUtilsWrapper(instance, mUploadUtilsWrapperProvider.get());
    injectMViewModelFactory(instance, mViewModelFactoryProvider.get());
    injectMPrivateAtomicCookie(instance, mPrivateAtomicCookieProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
    injectMMediaPickerLauncher(instance, mMediaPickerLauncherProvider.get());
    injectMSelectedSiteRepository(instance, mSelectedSiteRepositoryProvider.get());
    injectMQuickStartRepository(instance, mQuickStartRepositoryProvider.get());
    injectMQuickStartUtilsWrapper(instance, mQuickStartUtilsWrapperProvider.get());
    injectMAnalyticsTrackerWrapper(instance, mAnalyticsTrackerWrapperProvider.get());
    injectMCreateSiteNotificationScheduler(instance, mCreateSiteNotificationSchedulerProvider.get());
    injectMWeeklyRoundupScheduler(instance, mWeeklyRoundupSchedulerProvider.get());
    injectMTodaysStatsCardFeatureConfig(instance, mTodaysStatsCardFeatureConfigProvider.get());
    injectMQuickStartTracker(instance, mQuickStartTrackerProvider.get());
    injectMStatsRevampV2FeatureConfig(instance, mStatsRevampV2FeatureConfigProvider.get());
    injectMBuildConfigWrapper(instance, mBuildConfigWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mAccountStore")
  public static void injectMAccountStore(WPMainActivity instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mSiteStore")
  public static void injectMSiteStore(WPMainActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mPostStore")
  public static void injectMPostStore(WPMainActivity instance, PostStore mPostStore) {
    instance.mPostStore = mPostStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mDispatcher")
  public static void injectMDispatcher(WPMainActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mLoginAnalyticsListener")
  public static void injectMLoginAnalyticsListener(WPMainActivity instance,
      LoginAnalyticsListener mLoginAnalyticsListener) {
    instance.mLoginAnalyticsListener = mLoginAnalyticsListener;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mShortcutsNavigator")
  public static void injectMShortcutsNavigator(WPMainActivity instance,
      ShortcutsNavigator mShortcutsNavigator) {
    instance.mShortcutsNavigator = mShortcutsNavigator;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mShortcutUtils")
  public static void injectMShortcutUtils(WPMainActivity instance, ShortcutUtils mShortcutUtils) {
    instance.mShortcutUtils = mShortcutUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mQuickStartStore")
  public static void injectMQuickStartStore(WPMainActivity instance,
      QuickStartStore mQuickStartStore) {
    instance.mQuickStartStore = mQuickStartStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mUploadActionUseCase")
  public static void injectMUploadActionUseCase(WPMainActivity instance,
      UploadActionUseCase mUploadActionUseCase) {
    instance.mUploadActionUseCase = mUploadActionUseCase;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mSystemNotificationsTracker")
  public static void injectMSystemNotificationsTracker(WPMainActivity instance,
      SystemNotificationsTracker mSystemNotificationsTracker) {
    instance.mSystemNotificationsTracker = mSystemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mGCMMessageHandler")
  public static void injectMGCMMessageHandler(WPMainActivity instance,
      GCMMessageHandler mGCMMessageHandler) {
    instance.mGCMMessageHandler = mGCMMessageHandler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mUploadUtilsWrapper")
  public static void injectMUploadUtilsWrapper(WPMainActivity instance,
      UploadUtilsWrapper mUploadUtilsWrapper) {
    instance.mUploadUtilsWrapper = mUploadUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mViewModelFactory")
  public static void injectMViewModelFactory(WPMainActivity instance,
      ViewModelProvider.Factory mViewModelFactory) {
    instance.mViewModelFactory = mViewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mPrivateAtomicCookie")
  public static void injectMPrivateAtomicCookie(WPMainActivity instance,
      PrivateAtomicCookie mPrivateAtomicCookie) {
    instance.mPrivateAtomicCookie = mPrivateAtomicCookie;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mReaderTracker")
  public static void injectMReaderTracker(WPMainActivity instance, ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mMediaPickerLauncher")
  public static void injectMMediaPickerLauncher(WPMainActivity instance,
      MediaPickerLauncher mMediaPickerLauncher) {
    instance.mMediaPickerLauncher = mMediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mSelectedSiteRepository")
  public static void injectMSelectedSiteRepository(WPMainActivity instance,
      SelectedSiteRepository mSelectedSiteRepository) {
    instance.mSelectedSiteRepository = mSelectedSiteRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mQuickStartRepository")
  public static void injectMQuickStartRepository(WPMainActivity instance,
      QuickStartRepository mQuickStartRepository) {
    instance.mQuickStartRepository = mQuickStartRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mQuickStartUtilsWrapper")
  public static void injectMQuickStartUtilsWrapper(WPMainActivity instance,
      QuickStartUtilsWrapper mQuickStartUtilsWrapper) {
    instance.mQuickStartUtilsWrapper = mQuickStartUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mAnalyticsTrackerWrapper")
  public static void injectMAnalyticsTrackerWrapper(WPMainActivity instance,
      AnalyticsTrackerWrapper mAnalyticsTrackerWrapper) {
    instance.mAnalyticsTrackerWrapper = mAnalyticsTrackerWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mCreateSiteNotificationScheduler")
  public static void injectMCreateSiteNotificationScheduler(WPMainActivity instance,
      CreateSiteNotificationScheduler mCreateSiteNotificationScheduler) {
    instance.mCreateSiteNotificationScheduler = mCreateSiteNotificationScheduler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mWeeklyRoundupScheduler")
  public static void injectMWeeklyRoundupScheduler(WPMainActivity instance,
      WeeklyRoundupScheduler mWeeklyRoundupScheduler) {
    instance.mWeeklyRoundupScheduler = mWeeklyRoundupScheduler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mTodaysStatsCardFeatureConfig")
  public static void injectMTodaysStatsCardFeatureConfig(WPMainActivity instance,
      MySiteDashboardTodaysStatsCardFeatureConfig mTodaysStatsCardFeatureConfig) {
    instance.mTodaysStatsCardFeatureConfig = mTodaysStatsCardFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mQuickStartTracker")
  public static void injectMQuickStartTracker(WPMainActivity instance,
      QuickStartTracker mQuickStartTracker) {
    instance.mQuickStartTracker = mQuickStartTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mStatsRevampV2FeatureConfig")
  public static void injectMStatsRevampV2FeatureConfig(WPMainActivity instance,
      StatsRevampV2FeatureConfig mStatsRevampV2FeatureConfig) {
    instance.mStatsRevampV2FeatureConfig = mStatsRevampV2FeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.WPMainActivity.mBuildConfigWrapper")
  public static void injectMBuildConfigWrapper(WPMainActivity instance,
      BuildConfigWrapper mBuildConfigWrapper) {
    instance.mBuildConfigWrapper = mBuildConfigWrapper;
  }
}
