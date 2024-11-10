package org.wordpress.android.push;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;

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
public final class GCMMessageService_MembersInjector implements MembersInjector<GCMMessageService> {
  private final Provider<AccountStore> mAccountStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<ZendeskHelper> mZendeskHelperProvider;

  private final Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider;

  private final Provider<GCMMessageHandler> mGCMMessageHandlerProvider;

  public GCMMessageService_MembersInjector(Provider<AccountStore> mAccountStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider) {
    this.mAccountStoreProvider = mAccountStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mZendeskHelperProvider = mZendeskHelperProvider;
    this.mSystemNotificationsTrackerProvider = mSystemNotificationsTrackerProvider;
    this.mGCMMessageHandlerProvider = mGCMMessageHandlerProvider;
  }

  public static MembersInjector<GCMMessageService> create(
      Provider<AccountStore> mAccountStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<ZendeskHelper> mZendeskHelperProvider,
      Provider<SystemNotificationsTracker> mSystemNotificationsTrackerProvider,
      Provider<GCMMessageHandler> mGCMMessageHandlerProvider) {
    return new GCMMessageService_MembersInjector(mAccountStoreProvider, mSiteStoreProvider, mZendeskHelperProvider, mSystemNotificationsTrackerProvider, mGCMMessageHandlerProvider);
  }

  @Override
  public void injectMembers(GCMMessageService instance) {
    injectMAccountStore(instance, mAccountStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMZendeskHelper(instance, mZendeskHelperProvider.get());
    injectMSystemNotificationsTracker(instance, mSystemNotificationsTrackerProvider.get());
    injectMGCMMessageHandler(instance, mGCMMessageHandlerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.push.GCMMessageService.mAccountStore")
  public static void injectMAccountStore(GCMMessageService instance, AccountStore mAccountStore) {
    instance.mAccountStore = mAccountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.push.GCMMessageService.mSiteStore")
  public static void injectMSiteStore(GCMMessageService instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.push.GCMMessageService.mZendeskHelper")
  public static void injectMZendeskHelper(GCMMessageService instance,
      ZendeskHelper mZendeskHelper) {
    instance.mZendeskHelper = mZendeskHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.push.GCMMessageService.mSystemNotificationsTracker")
  public static void injectMSystemNotificationsTracker(GCMMessageService instance,
      SystemNotificationsTracker mSystemNotificationsTracker) {
    instance.mSystemNotificationsTracker = mSystemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.push.GCMMessageService.mGCMMessageHandler")
  public static void injectMGCMMessageHandler(GCMMessageService instance,
      GCMMessageHandler mGCMMessageHandler) {
    instance.mGCMMessageHandler = mGCMMessageHandler;
  }
}
