package org.wordpress.android.ui.notifications;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.push.GCMMessageHandler;
import org.wordpress.android.ui.engagement.ListScenarioUtils;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.util.config.LikesEnhancementsFeatureConfig;

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
public final class NotificationsDetailActivity_MembersInjector implements MembersInjector<NotificationsDetailActivity> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<GCMMessageHandler> mGCMMessageHandlerProvider;

  private final Provider<ReaderTracker> mReaderTrackerProvider;

  private final Provider<LikesEnhancementsFeatureConfig> mLikesEnhancementsFeatureConfigProvider;

  private final Provider<ListScenarioUtils> mListScenarioUtilsProvider;

  public NotificationsDetailActivity_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<LikesEnhancementsFeatureConfig> mLikesEnhancementsFeatureConfigProvider,
      Provider<ListScenarioUtils> mListScenarioUtilsProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mGCMMessageHandlerProvider = mGCMMessageHandlerProvider;
    this.mReaderTrackerProvider = mReaderTrackerProvider;
    this.mLikesEnhancementsFeatureConfigProvider = mLikesEnhancementsFeatureConfigProvider;
    this.mListScenarioUtilsProvider = mListScenarioUtilsProvider;
  }

  public static MembersInjector<NotificationsDetailActivity> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider,
      Provider<ReaderTracker> mReaderTrackerProvider,
      Provider<LikesEnhancementsFeatureConfig> mLikesEnhancementsFeatureConfigProvider,
      Provider<ListScenarioUtils> mListScenarioUtilsProvider) {
    return new NotificationsDetailActivity_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mGCMMessageHandlerProvider, mReaderTrackerProvider, mLikesEnhancementsFeatureConfigProvider, mListScenarioUtilsProvider);
  }

  @Override
  public void injectMembers(NotificationsDetailActivity instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMGCMMessageHandler(instance, mGCMMessageHandlerProvider.get());
    injectMReaderTracker(instance, mReaderTrackerProvider.get());
    injectMLikesEnhancementsFeatureConfig(instance, mLikesEnhancementsFeatureConfigProvider.get());
    injectMListScenarioUtils(instance, mListScenarioUtilsProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailActivity.mAccountStore")
  public static void injectMAccountStore(NotificationsDetailActivity instance,
      AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailActivity.mSiteStore")
  public static void injectMSiteStore(NotificationsDetailActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailActivity.mGCMMessageHandler")
  public static void injectMGCMMessageHandler(NotificationsDetailActivity instance,
      GCMMessageHandler mGCMMessageHandler) {
    instance.mGCMMessageHandler = mGCMMessageHandler;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailActivity.mReaderTracker")
  public static void injectMReaderTracker(NotificationsDetailActivity instance,
      ReaderTracker mReaderTracker) {
    instance.mReaderTracker = mReaderTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailActivity.mLikesEnhancementsFeatureConfig")
  public static void injectMLikesEnhancementsFeatureConfig(NotificationsDetailActivity instance,
      LikesEnhancementsFeatureConfig mLikesEnhancementsFeatureConfig) {
    instance.mLikesEnhancementsFeatureConfig = mLikesEnhancementsFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.notifications.NotificationsDetailActivity.mListScenarioUtils")
  public static void injectMListScenarioUtils(NotificationsDetailActivity instance,
      ListScenarioUtils mListScenarioUtils) {
    instance.mListScenarioUtils = mListScenarioUtils;
  }
}
